package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.grabacion.GrabacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.UbicacionZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;

import com.bachue.snr.prosnr01.web.bean.antiguo.sistema.BeanAntSistema;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanGrabacionMatriculas.
 *
 * @author  Gabriel Arias
 * Fecha de Creacion: 25/07/2019
 */
@SessionScoped
@ManagedBean(name = "beanGrabacionMatriculas")
public class BeanGrabacionMatriculas extends BeanAntSistema implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8321188292343733504L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanGrabacionMatriculas.class);

	/** Propiedad igr grabacion remote. */
	@EJB
	private GrabacionRemote igr_grabacionRemote;

	/** Propiedad lhm predio. */
	private Map<String, Object> lhm_predio;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad ib rendered habilita secuencia. */
	private boolean ib_renderedHabilitaSecuencia;

	/**
	 * Sets the predio.
	 *
	 * @param ahm_predio correspondiente al valor del tipo de objeto Map<String,Object>
	 */
	public void setPredio(Map<String, Object> ahm_predio)
	{
		lhm_predio = ahm_predio;
	}

	/**
	 * Retorna el valor de predio.
	 *
	 * @return el valor de predio
	 */
	public Map<String, Object> getPredio()
	{
		return lhm_predio;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ab_b correspondiente al valor de ab b
	 */
	public void setRenderedHabilitaSecuencia(boolean ab_b)
	{
		ib_renderedHabilitaSecuencia = ab_b;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en rendered habilita secuencia
	 */
	public boolean isRenderedHabilitaSecuencia()
	{
		return ib_renderedHabilitaSecuencia;
	}

	/**
	 * Método para la actualización de los componentes de tipo oficina y tipo entidad.
	 *
	 * @return una colección del tipo de oficina con los datos solicitados
	 */
	public Collection<TipoOficina> actualizarTipoOficinaDocumentos()
	{
		Collection<TipoOficina> lcto_datos;
		DatosBasicos            ldb_datosBasicos;

		lcto_datos           = null;
		ldb_datosBasicos     = getDatosBasicos();

		if(ldb_datosBasicos != null)
		{
			try
			{
				Apertura la_apertura;

				la_apertura = ldb_datosBasicos.getApertura();

				if(la_apertura != null)
				{
					Documento ld_d;

					ld_d = la_apertura.getDocumento();

					if(ld_d != null)
						lcto_datos = irr_referenceRemote.findTipoOficina(ld_d);

					if(CollectionUtils.isValidCollection(lcto_datos))
					{
						if(lcto_datos.size() == 1)
						{
							for(TipoOficina lto_tmp : lcto_datos)
							{
								if(lto_tmp != null)
								{
									la_apertura.setIdTipoOficina(lto_tmp.getIdTipoOficina());
									la_apertura.setIdTipoEntidad(lto_tmp.getIdTipoEntidad());
								}
							}
						}
						else if(lcto_datos.size() >= 2)
						{
							Iterator<TipoOficina> lito_oficinas;

							lito_oficinas = lcto_datos.iterator();

							if(lito_oficinas.hasNext())
							{
								TipoOficina lto_oficina;

								lto_oficina = lito_oficinas.next();

								if(lto_oficina != null)
								{
									String ls_idTipoOficina;

									ls_idTipoOficina = lto_oficina.getIdTipoOficina();

									if(StringUtils.isValidString(ls_idTipoOficina))
										la_apertura.setIdTipoOficina(ls_idTipoOficina);

									la_apertura.setIdTipoEntidad(lto_oficina.getIdTipoEntidad());
								}
							}
						}
					}
					else
					{
						TipoOficina ltf_seleccione;
						ltf_seleccione     = new TipoOficina();
						lcto_datos         = new ArrayList<TipoOficina>();
						la_apertura.setIdTipoOficina(ltf_seleccione.getIdTipoEntidad());
						la_apertura.setIdTipoEntidad(ltf_seleccione.getIdTipoEntidad());
						ltf_seleccione.setNombre(ConstanteCommon.SELECCIONE);
						lcto_datos.add(ltf_seleccione);
					}

					{
						String ls_idPais;

						ls_idPais = la_apertura.getIdPais();

						if(!StringUtils.isValidString(ls_idPais))
							la_apertura.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				addMessage(lb2be_e);
				lcto_datos = null;
			}
		}

		return lcto_datos;
	}

	/**
	 * Cargar data.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarData()
	    throws B2BException
	{
		String ls_idTurnoHistoria;

		ls_idTurnoHistoria = getIdTurnoHistoria();

		if(StringUtils.isValidString(ls_idTurnoHistoria))
		{
			DatosBasicos  ldb_datosBasicos;
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria = new TurnoHistoria();

			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

			ldb_datosBasicos = igr_grabacionRemote.consultarDatosBasicos(lth_turnoHistoria);

			if(ldb_datosBasicos != null)
			{
				AccPredioRegistro lapr_predio;
				DatosAntSistema   ldas_data;

				ldas_data       = ldb_datosBasicos.getDatosAntSistema();
				lapr_predio     = ldb_datosBasicos.getAccPredioRegistro();

				if(lapr_predio != null)
				{
					String ls_idPredio;

					ls_idPredio = lapr_predio.getIdPredioRegistro();

					if(StringUtils.isValidString(ls_idPredio))
						cargarMatricula(lapr_predio);
					else
					{
						if(ldas_data != null)
						{
							lapr_predio.setIdCirculo(ldas_data.getIdCirculoGrabacion());
							lapr_predio.setIdMatricula(ldas_data.getIdMatriculaGrabacion());
						}

						setAccPredioRegistro(lapr_predio);
						setDatosBasicos(ldb_datosBasicos);
					}
				}
			}
		}
	}

	/**
	 * Cargar tipo entidad documento.
	 *
	 * @return el valor de collection
	 */
	public Collection<TipoEntidad> cargarTipoEntidadDocumento()
	{
		Collection<TipoEntidad> lcto_return;
		DatosBasicos            ldb_datosBasicos;

		lcto_return          = null;
		ldb_datosBasicos     = getDatosBasicos();

		if(ldb_datosBasicos != null)
		{
			try
			{
				Apertura la_apertura;

				la_apertura = ldb_datosBasicos.getApertura();

				if(la_apertura != null)
					lcto_return = irr_referenceRemote.findTipoEntidad(la_apertura);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
				clh_LOGGER.error("cargarTipoEntidadDocumento", lb2be_e);
			}
		}

		return lcto_return;
	}

	/**
	 * Cargar tipo oficina documento.
	 *
	 * @return el valor de collection
	 */
	public Collection<TipoOficina> cargarTipoOficinaDocumento()
	{
		Collection<TipoOficina> lcto_return;
		DatosBasicos            ldb_datosBasicos;

		lcto_return          = null;
		ldb_datosBasicos     = getDatosBasicos();

		if(ldb_datosBasicos != null)
		{
			try
			{
				Apertura la_apertura;

				la_apertura = ldb_datosBasicos.getApertura();

				if(la_apertura != null)
				{
					Documento ld_documento;

					ld_documento = la_apertura.getDocumento();

					if(ld_documento != null)
						lcto_return = irr_referenceRemote.findTipoOficina(ld_documento);
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
				clh_LOGGER.error("cargarTipoOficinaDocumento", lb2be_e);
			}
		}

		return lcto_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String enviarAprobador()
	{
		String ls_return;

		ls_return = null;

		try
		{
			if((getArchivoPdf() == null) || !isProcesoTerminadoCrearGrabar())
				throw new B2BException(ErrorKeys.NO_GENERO_PDF);

			if(
			    igr_grabacionRemote.enviarAprobador104(
				        getIdTurnoHistoria(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				    )
			)
			{
				BeanGrabacion lb_beanGrabacion;
				FacesContext  lfc_context;

				lfc_context          = FacesContext.getCurrentInstance();
				lb_beanGrabacion     = (BeanGrabacion)lfc_context.getApplication()
						                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_GRABACION, BeanGrabacion.class
						);

				lb_beanGrabacion.setIdTurnoConsulta(null);
				lb_beanGrabacion.setNirConsulta(null);
				lb_beanGrabacion.generarDatosTramiteCantidad();

				ls_return = NavegacionCommon.GRABACION_MATRICULAS;
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("enviarAprobador", lb2be_e);
			ls_return = null;
		}

		return ls_return;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de collection
	 */
	public Collection<InteresadoDocumentoTipo> findTipoDocumentoSecuencia()
	{
		Collection<InteresadoDocumentoTipo> lcidt_datos;

		try
		{
			if(isHabilitarSecuencia())
			{
				String ls_tipoDocInter;
				ls_tipoDocInter = getTipoDocInterviniente();

				if(ls_tipoDocInter != null)
				{
					if(ls_tipoDocInter.equalsIgnoreCase(EstadoCommon.SE))
					{
						lcidt_datos = irr_referenceRemote.findTipoDocumento();

						Constantes lc_constante;
						lc_constante = new Constantes();
						lc_constante.setIdConstante(ConstanteCommon.CONSECUTIVO_TIPO_DOCUMENTO_SECUENCIA);
						lc_constante = irr_registroRemote.findConstante(lc_constante);

						if(lc_constante != null)
						{
							BigInteger lbi_bi;

							lbi_bi = lc_constante.getEntero().add(NumericUtils.getBigInteger(1));

							setDocumentoInterviniente(StringUtils.getString(lbi_bi));
							setRenderedHabilitaSecuencia(true);
							getPersona().setIdDocumentoTipo(ls_tipoDocInter);
							getPersona().setNumeroDocumento(StringUtils.getString(lbi_bi));
						}
					}
					else
					{
						lcidt_datos = irr_referenceRemote.findTipoDocumento();
						setDocumentoInterviniente(null);
						setRenderedHabilitaSecuencia(false);
					}
				}
				else
				{
					lcidt_datos = irr_referenceRemote.findTipoDocumento();
					setDocumentoInterviniente(null);
					setRenderedHabilitaSecuencia(false);
				}
			}
			else
			{
				lcidt_datos = irr_referenceRemote.findTipoDocumentoActivo();
				setDocumentoInterviniente(null);
				setRenderedHabilitaSecuencia(false);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("findTipoDocumentoSecuencia", lb2be_e);
			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Validar fecha migracion.
	 *
	 * @param ad_fecha correspondiente al valor del tipo de objeto Date
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarFechaMigracion(Date ad_fecha, boolean ab_accion)
	    throws B2BException
	{
		if(ad_fecha != null)
		{
			DatosBasicos ldb_datosBasicos;

			ldb_datosBasicos = getDatosBasicos();

			if(ldb_datosBasicos != null)
			{
				UbicacionZonaRegistral lbzr_ubicacion;

				lbzr_ubicacion = ldb_datosBasicos.getUbicacion();

				if(lbzr_ubicacion != null)
				{
					CirculoRegistral lcr_circulo;

					lcr_circulo = lbzr_ubicacion.getCirculoRegistral();

					if(lcr_circulo != null)
					{
						lcr_circulo = ipr_parameterRemote.findCirculoRegistralById(
							    lcr_circulo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(lcr_circulo != null)
						{
							Date ld_fechaProduccion;

							ld_fechaProduccion = lcr_circulo.getFechaProduccion();

							if((ld_fechaProduccion != null) && (ld_fechaProduccion.compareTo(ad_fecha) > 0))
							{
								if(ab_accion)
									addMessage(MessagesKeys.FECHA_PRODUCCION_ANOTACION);
								else
									addMessage(MessagesKeys.FECHA_PRODUCCION_APERTURA);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Método para agregar anotación de grabación de matriculas
	 * @throws B2BException
	 */
	public void anotacionGrabacion()
	    throws B2BException
	{
		try
		{
			long ll_idAnotacion;
			ll_idAnotacion = getIdAnotacionGeneral();

			if(ll_idAnotacion > 0)
				agregarAnotacion();
			else
				agregarAnotacion(true, null, null);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("anotacionGrabacion", lb2be_e);
		}
	}
}
