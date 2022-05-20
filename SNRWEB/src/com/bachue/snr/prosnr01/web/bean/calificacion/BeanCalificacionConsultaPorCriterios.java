package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaAntSistema;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaPorCriterio;
import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacionAntiguoSistema;
import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacionDocumento;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanCalificacionConsultaPorCriterios.
 *
 * @author jpatino
 */
@SessionScoped
@ManagedBean(name = "beanConsultaPorCriterios")
public class BeanCalificacionConsultaPorCriterios extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1421979217837187090L;

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fconsultaPorCriteriosAnotacion:idGrowl";

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ic data consulta ant sistema. */
	private Collection<DataConsultaAntSistema> ic_dataConsultaAntSistema;

	/** Propiedad ic data consulta datos documento. */
	private Collection<DataConsultaDatosDocumento> ic_dataConsultaDatosDocumento;

	/** Propiedad icccas consulta criterios calificacion antiguo sistema. */
	private ConsultaCriteriosCalificacionAntiguoSistema icccas_consultaCriteriosCalificacionAntiguoSistema;

	/** Propiedad icccd consulta criterios calificacion documento. */
	private ConsultaCriteriosCalificacionDocumento icccd_consultaCriteriosCalificacionDocumento;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad ib consulta vacia. */
	private boolean ib_consultaVacia;

	/** {@inheritdoc} */
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de consulta criterios calificacion antiguo sistema.
	 *
	 * @param acccas_cccas asigna el valor a la propiedad consulta criterios calificacion antiguo sistema
	 */
	public void setConsultaCriteriosCalificacionAntiguoSistema(
	    ConsultaCriteriosCalificacionAntiguoSistema acccas_cccas
	)
	{
		icccas_consultaCriteriosCalificacionAntiguoSistema = acccas_cccas;
	}

	/**
	 * Retorna el valor de consulta criterios calificacion antiguo sistema.
	 *
	 * @return el valor de consulta criterios calificacion antiguo sistema
	 */
	public ConsultaCriteriosCalificacionAntiguoSistema getConsultaCriteriosCalificacionAntiguoSistema()
	{
		if(icccas_consultaCriteriosCalificacionAntiguoSistema == null)
			icccas_consultaCriteriosCalificacionAntiguoSistema = new ConsultaCriteriosCalificacionAntiguoSistema();

		return icccas_consultaCriteriosCalificacionAntiguoSistema;
	}

	/**
	 * Modifica el valor de consulta criterios calificacion documento.
	 *
	 * @param acccd_cccd asigna el valor a la propiedad consulta criterios calificacion documento
	 */
	public void setConsultaCriteriosCalificacionDocumento(ConsultaCriteriosCalificacionDocumento acccd_cccd)
	{
		icccd_consultaCriteriosCalificacionDocumento = acccd_cccd;
	}

	/**
	 * Retorna el valor de consulta criterios calificacion documento.
	 *
	 * @return el valor de consulta criterios calificacion documento
	 */
	public ConsultaCriteriosCalificacionDocumento getConsultaCriteriosCalificacionDocumento()
	{
		if(icccd_consultaCriteriosCalificacionDocumento == null)
			icccd_consultaCriteriosCalificacionDocumento = new ConsultaCriteriosCalificacionDocumento();

		return icccd_consultaCriteriosCalificacionDocumento;
	}

	/**
	 * Modifica el valor de consulta vacia.
	 *
	 * @param ab_b asigna el valor a la propiedad consulta vacia
	 */
	public void setConsultaVacia(boolean ab_b)
	{
		this.ib_consultaVacia = ab_b;
	}

	/**
	 * Valida la propiedad consulta vacia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en consulta vacia
	 */
	public boolean isConsultaVacia()
	{
		return ib_consultaVacia;
	}

	/**
	 * Modifica el valor de data consulta ant sistema.
	 *
	 * @param dataConsultaAntSistema asigna el valor a la propiedad data consulta ant sistema
	 */
	public void setDataConsultaAntSistema(Collection<DataConsultaAntSistema> dataConsultaAntSistema)
	{
		this.ic_dataConsultaAntSistema = dataConsultaAntSistema;
	}

	/**
	 * Retorna el valor de data consulta ant sistema.
	 *
	 * @return el valor de data consulta ant sistema
	 */
	public Collection<DataConsultaAntSistema> getDataConsultaAntSistema()
	{
		return ic_dataConsultaAntSistema;
	}

	/**
	 * Modifica el valor de data consulta datos documento.
	 *
	 * @param dataConsultaDatosDocumento asigna el valor a la propiedad data consulta datos documento
	 */
	public void setDataConsultaDatosDocumento(Collection<DataConsultaDatosDocumento> dataConsultaDatosDocumento)
	{
		this.ic_dataConsultaDatosDocumento = dataConsultaDatosDocumento;
	}

	/**
	 * Retorna el valor de data consulta datos documento.
	 *
	 * @return el valor de data consulta datos documento
	 */
	public Collection<DataConsultaDatosDocumento> getDataConsultaDatosDocumento()
	{
		return ic_dataConsultaDatosDocumento;
	}

	/**
	 * Sets the id circulo.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void setIdCirculo()
	    throws B2BException
	{
		ConsultaCriteriosCalificacionAntiguoSistema lcccas_dataAntSistema;
		ConsultaCriteriosCalificacionDocumento      lcccd_dataDocumento;
		Turno                                       lt_turno;

		lcccas_dataAntSistema     = new ConsultaCriteriosCalificacionAntiguoSistema();
		lcccd_dataDocumento       = new ConsultaCriteriosCalificacionDocumento();
		lt_turno                  = irr_calificacionRemote.findTurno(getIdTurno());

		if(lt_turno != null)
		{
			String ls_idCirculo;
			ls_idCirculo = lt_turno.getIdCirculo();

			if(StringUtils.isValidString(ls_idCirculo))
			{
				if(lcccas_dataAntSistema != null)
				{
					DatosAntSistema datosAntSistema;

					datosAntSistema = new DatosAntSistema();
					datosAntSistema.setIdCirculo(ls_idCirculo);

					lcccas_dataAntSistema.setDatosAntSistema(datosAntSistema);
					setConsultaCriteriosCalificacionAntiguoSistema(lcccas_dataAntSistema);
				}

				if(lcccd_dataDocumento != null)
				{
					AnotacionPredio anotacionPredio;

					anotacionPredio = new AnotacionPredio();
					anotacionPredio.setIdCirculo(ls_idCirculo);

					lcccd_dataDocumento.setAnotacionPredio(anotacionPredio);
					setConsultaCriteriosCalificacionDocumento(lcccd_dataDocumento);
				}
			}
		}
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param al_l asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna el valor de observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Retorna el valor de oficina origen.
	 *
	 * @return el valor de oficina origen
	 */
	public Collection<OficinaOrigen> getOficinaOrigen()
	{
		Collection<OficinaOrigen> lcidt_datos;
		lcidt_datos = null;

		try
		{
			ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosCalificacionDocumento;
			lcccd_consultaCriteriosCalificacionDocumento = getConsultaCriteriosCalificacionDocumento();

			if(lcccd_consultaCriteriosCalificacionDocumento != null)
			{
				Documento ld_documento;
				ld_documento = lcccd_consultaCriteriosCalificacionDocumento.getDocumento();

				if(ld_documento != null)
				{
					OficinaOrigen oficinaOrigen;

					oficinaOrigen = new OficinaOrigen();
					oficinaOrigen.setIdTipoOficina(ld_documento.getIdTipoOficina());
					oficinaOrigen.setIdPais(ld_documento.getIdPais());
					oficinaOrigen.setIdDepartamento(ld_documento.getIdDepartamento());
					oficinaOrigen.setIdMunicipio(ld_documento.getIdMunicipio());

					lcidt_datos = irr_referenceRemote.findOficinaOrigen(oficinaOrigen);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String accionVolver()
	    throws B2BException
	{
		BeanPredioDocumentoActo lbpda_bean;
		FacesContext            lfc_context;

		lfc_context     = FacesUtils.getFacesContext();
		lbpda_bean      = (BeanPredioDocumentoActo)lfc_context.getApplication()
				                                                  .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
				);

		if(lbpda_bean != null)
		{
			lbpda_bean.setOcultarPaneles(false);
			lbpda_bean.setIdTurno(getIdTurno());
			lbpda_bean.obtenerInformacionASEtapa101();
			lbpda_bean.generarDatosTramitesVinculados();

			lbpda_bean.getMatriculasRangos();
			lbpda_bean.getMatriculasIndividuales();
			lbpda_bean.getMatriculasTmpRangos();
			lbpda_bean.getMatriculasTmpIndividuales();
		}

		return NavegacionCommon.DETALLE_ACTO;
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
		setDataConsultaAntSistema(null);
		setDataConsultaDatosDocumento(null);
		setConsultaCriteriosCalificacionAntiguoSistema(null);
		setConsultaCriteriosCalificacionDocumento(null);
		setIdTurno(null);
		setIdTurnoHistoria(null);
		setConsultaVacia(false);
	}

	/**
	 * Clear consulta.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 */
	public void clearConsulta(String as_s)
	{
		if(as_s != null)
		{
			if(as_s.equalsIgnoreCase("AntSistema"))
			{
				setDataConsultaAntSistema(null);

				ConsultaCriteriosCalificacionAntiguoSistema lcccas_antiguoSistema;
				lcccas_antiguoSistema = getConsultaCriteriosCalificacionAntiguoSistema();

				if(lcccas_antiguoSistema != null)
				{
					DatosAntSistema ldas_datosAntSistema;
					ldas_datosAntSistema = lcccas_antiguoSistema.getDatosAntSistema();

					if(ldas_datosAntSistema != null)
					{
						String ls_idCirculo;
						ls_idCirculo = ldas_datosAntSistema.getIdCirculo();

						ConsultaCriteriosCalificacionAntiguoSistema lcccas_nuevo;
						lcccas_nuevo = new ConsultaCriteriosCalificacionAntiguoSistema();

						DatosAntSistema ldas_nuevo;
						ldas_nuevo = new DatosAntSistema();

						ldas_nuevo.setIdCirculo(ls_idCirculo);
						lcccas_nuevo.setDatosAntSistema(ldas_nuevo);

						setConsultaCriteriosCalificacionAntiguoSistema(lcccas_nuevo);
					}
				}
			}
			else if(ib_consultaVacia)
			{
				setDataConsultaDatosDocumento(null);

				ConsultaCriteriosCalificacionDocumento lcccd_documento;
				lcccd_documento = getConsultaCriteriosCalificacionDocumento();

				if(lcccd_documento != null)
				{
					AnotacionPredio lap_anotacionPredio;
					lap_anotacionPredio = lcccd_documento.getAnotacionPredio();

					if(lap_anotacionPredio != null)
					{
						String ls_idCirculo;
						ls_idCirculo = lap_anotacionPredio.getIdCirculo();

						ConsultaCriteriosCalificacionDocumento lcccd_nuevo;
						lcccd_nuevo = new ConsultaCriteriosCalificacionDocumento();

						AnotacionPredio lap_nuevo;
						lap_nuevo = new AnotacionPredio();

						lap_nuevo.setIdCirculo(ls_idCirculo);
						lcccd_nuevo.setAnotacionPredio(lap_nuevo);

						setConsultaCriteriosCalificacionDocumento(lcccd_nuevo);
					}
				}
			}
		}
	}

	/**
	 * Consulta antiguo sistema.
	 *
	 * @param lcccas_consultaCriteriosAntiguoSistema correspondiente al valor del tipo de objeto ConsultaCriteriosCalificacionAntiguoSistema
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaAntiguoSistema(
	    ConsultaCriteriosCalificacionAntiguoSistema lcccas_consultaCriteriosAntiguoSistema
	)
	    throws B2BException
	{
		try
		{
			Collection<DataConsultaAntSistema> lc_dataAntSistema;
			Collection<DataConsultaAntSistema> lc_dataAntSistemaReturn;

			lc_dataAntSistema           = irr_calificacionRemote.findByDatosAntSistema(
				    lcccas_consultaCriteriosAntiguoSistema
				);
			lc_dataAntSistemaReturn     = new ArrayList<DataConsultaAntSistema>();

			if(CollectionUtils.isValidCollection(lc_dataAntSistema))
			{
				lcccas_consultaCriteriosAntiguoSistema.setConsultado(true);

				for(DataConsultaAntSistema iterator : lc_dataAntSistema)
				{
					Collection<DataConsultaPorCriterio> lc_data;
					Collection<DataConsultaPorCriterio> ll_returnData;

					lc_data           = iterator.getDataConsultaPorCriterio();
					ll_returnData     = new ArrayList<DataConsultaPorCriterio>();

					for(DataConsultaPorCriterio iterador : lc_data)
					{
						StringBuilder lsb_direccionCompleta;
						lsb_direccionCompleta = new StringBuilder();

						{
							String ls_tipoEje;
							ls_tipoEje = iterador.getIdTipoEjePrincipal();

							if(StringUtils.isValidString(ls_tipoEje))
							{
								TipoEje lte_tmp;
								lte_tmp = new TipoEje();
								lte_tmp.setIdTipoEje(ls_tipoEje);

								lte_tmp = irr_referenceRemote.findTipoEjeById(lte_tmp);

								if(lte_tmp != null)
									lsb_direccionCompleta.append(
									    StringUtils.getStringNotNull(lte_tmp.getNombre()) + " "
									);
							}
						}

						lsb_direccionCompleta.append(
						    StringUtils.getStringNotNull(iterador.getDatoEjePrincipal()) + " "
						);

						{
							String ls_tipoEje1;
							ls_tipoEje1 = iterador.getIdTipoEjeSecundario();

							if(StringUtils.isValidString(ls_tipoEje1))
							{
								TipoEje lte_tmp;
								lte_tmp = new TipoEje();
								lte_tmp.setIdTipoEje(ls_tipoEje1);

								lte_tmp = irr_referenceRemote.findTipoEjeById(lte_tmp);

								if(lte_tmp != null)
									lsb_direccionCompleta.append(
									    StringUtils.getStringNotNull(lte_tmp.getNombre()) + " "
									);
							}
						}

						lsb_direccionCompleta.append(
						    StringUtils.getStringNotNull(iterador.getDatoEjeSecundario()) + " "
						);
						lsb_direccionCompleta.append(
						    StringUtils.getStringNotNull(iterador.getComplementoDireccion()) + " "
						);

						{
							String ls_tmp;
							ls_tmp = lsb_direccionCompleta.toString();

							if(StringUtils.isValidString(ls_tmp))
							{
								iterador.setDireccion(ls_tmp.trim());
								ll_returnData.add(iterador);
							}
						}
					}

					iterator.setDataConsultaPorCriterio(ll_returnData);
					lc_dataAntSistemaReturn.add(iterator);
				}

				setDataConsultaAntSistema(lc_dataAntSistemaReturn);

				addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}
		}
		catch(B2BException lb2be_e)
		{
			setDataConsultaAntSistema(null);
			throw lb2be_e;
		}
	}

	/**
	 * Consulta datos documento.
	 *
	 * @param consultaCriteriosCalificacionDocumento correspondiente al valor del tipo de objeto ConsultaCriteriosCalificacionDocumento
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDatosDocumento(ConsultaCriteriosCalificacionDocumento consultaCriteriosCalificacionDocumento)
	    throws B2BException
	{
		try
		{
			Collection<DataConsultaDatosDocumento> lcdcdd_dataDocumento;
			Collection<DataConsultaDatosDocumento> lc_dataDocuemntoReturn;

			lcdcdd_dataDocumento       = irr_calificacionRemote.findByDatosDocumento(
				    consultaCriteriosCalificacionDocumento
				);
			lc_dataDocuemntoReturn     = new ArrayList<DataConsultaDatosDocumento>();

			if(CollectionUtils.isValidCollection(lcdcdd_dataDocumento))
			{
				consultaCriteriosCalificacionDocumento.setConsultado(true);

				for(DataConsultaDatosDocumento iterator : lcdcdd_dataDocumento)
				{
					Collection<DataConsultaPorCriterio> lc_data;
					Collection<DataConsultaPorCriterio> ll_returnData;

					lc_data           = iterator.getDataConsultaPorCriterio();
					ll_returnData     = new ArrayList<DataConsultaPorCriterio>();

					if(CollectionUtils.isValidCollection(lc_data))
					{
						for(DataConsultaPorCriterio iterador : lc_data)
						{
							StringBuilder lsb_direccionCompleta;
							lsb_direccionCompleta = new StringBuilder();

							{
								String ls_tipoEje;
								ls_tipoEje = iterador.getIdTipoEjePrincipal();

								if(StringUtils.isValidString(ls_tipoEje))
								{
									TipoEje lte_tmp;
									lte_tmp = new TipoEje();
									lte_tmp.setIdTipoEje(ls_tipoEje);

									lte_tmp = irr_referenceRemote.findTipoEjeById(lte_tmp);

									if(lte_tmp != null)
										lsb_direccionCompleta.append(
										    StringUtils.getStringNotNull(lte_tmp.getNombre()) + " "
										);
								}
							}

							lsb_direccionCompleta.append(
							    StringUtils.getStringNotNull(iterador.getDatoEjePrincipal()) + " "
							);

							{
								String ls_tipoEje1;
								ls_tipoEje1 = iterador.getIdTipoEjeSecundario();

								if(StringUtils.isValidString(ls_tipoEje1))
								{
									TipoEje lte_tmp;
									lte_tmp = new TipoEje();
									lte_tmp.setIdTipoEje(ls_tipoEje1);

									lte_tmp = irr_referenceRemote.findTipoEjeById(lte_tmp);

									if(lte_tmp != null)
										lsb_direccionCompleta.append(
										    StringUtils.getStringNotNull(lte_tmp.getNombre()) + " "
										);
								}
							}

							lsb_direccionCompleta.append(
							    StringUtils.getStringNotNull(iterador.getDatoEjeSecundario()) + " "
							);
							lsb_direccionCompleta.append(
							    StringUtils.getStringNotNull(iterador.getComplementoDireccion()) + " "
							);

							{
								String ls_tmp;
								ls_tmp = lsb_direccionCompleta.toString();

								if(StringUtils.isValidString(ls_tmp))
								{
									iterador.setDireccion(ls_tmp.trim());
									ll_returnData.add(iterador);
								}
							}
						}
					}

					iterator.setDataConsultaPorCriterio(ll_returnData);
					lc_dataDocuemntoReturn.add(iterator);
				}

				Long                       ll_maxVersion;
				DataConsultaDatosDocumento dcdd_data;

				ll_maxVersion     = NumericUtils.getLongWrapper(0);
				dcdd_data         = null;

				for(DataConsultaDatosDocumento iterador : lc_dataDocuemntoReturn)
				{
					Long ll_version;
					ll_version = iterador.getVersion();

					if(ll_version.compareTo(ll_maxVersion) >= 0)
					{
						dcdd_data         = iterador;
						ll_maxVersion     = ll_version;
					}
				}

				lc_dataDocuemntoReturn = new ArrayList<DataConsultaDatosDocumento>();
				lc_dataDocuemntoReturn.add(dcdd_data);

				setDataConsultaDatosDocumento(lc_dataDocuemntoReturn);

				addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}
		}
		catch(B2BException lb2be_e)
		{
			setDataConsultaDatosDocumento(null);
			throw lb2be_e;
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String contar()
	{
		String ls_observaciones;
		char[] lca_arrayChar;
		int    li_contador;
		String as_result;

		ls_observaciones     = getObservaciones();
		li_contador          = 0;

		if(ls_observaciones != null)
		{
			lca_arrayChar     = ls_observaciones.toCharArray();
			li_contador       = lca_arrayChar.length;
		}

		as_result = Integer.toString(li_contador) + "/400";

		return as_result;
	}

	/**
	 * Departamento.
	 */
	public void departamento()
	{
		ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosCalificacionDocumento;
		lcccd_consultaCriteriosCalificacionDocumento = getConsultaCriteriosCalificacionDocumento();

		if(lcccd_consultaCriteriosCalificacionDocumento != null)
		{
			Documento ld_documento;
			ld_documento = lcccd_consultaCriteriosCalificacionDocumento.getDocumento();

			if(ld_documento != null)
			{
				String ls_idDepartamento;
				ls_idDepartamento = ld_documento.getIdDepartamento();

				if(StringUtils.isValidString(ls_idDepartamento))
					ld_documento.setIdDepartamento(ls_idDepartamento);

				ld_documento.setIdMunicipio(null);
				ld_documento.setIdOficinaOrigen(null);
			}
		}

		findMunicipios();
		getOficinaOrigen();
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentos()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosCalificacionDocumento;
			lcccd_consultaCriteriosCalificacionDocumento = getConsultaCriteriosCalificacionDocumento();

			if(lcccd_consultaCriteriosCalificacionDocumento != null)
			{
				Documento ld_documento;
				ld_documento = lcccd_consultaCriteriosCalificacionDocumento.getDocumento();

				if(ld_documento != null)
				{
					String ls_pais;
					ls_pais = ld_documento.getIdPais();

					if(StringUtils.isValidString(ls_pais))
					{
						Departamento ld_parametros;

						ld_parametros = new Departamento();
						ld_parametros.setIdPais(ls_pais);
						lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipioAntSistema()
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		try
		{
			ConsultaCriteriosCalificacionAntiguoSistema lcccas_consultaCriteriosAntSistema;
			DatosAntSistema                             ldas_datosAntSistema;

			lcccas_consultaCriteriosAntSistema     = getConsultaCriteriosCalificacionAntiguoSistema();
			ldas_datosAntSistema                   = null;

			if(lcccas_consultaCriteriosAntSistema != null)
				ldas_datosAntSistema = lcccas_consultaCriteriosAntSistema.getDatosAntSistema();

			if(ldas_datosAntSistema != null)
			{
				String ls_departamento;
				String ls_pais;

				ls_departamento     = ldas_datosAntSistema.getIdDepartamento();
				ls_pais             = ldas_datosAntSistema.getIdPais();

				if(!StringUtils.isValidString(ls_pais))
					ls_pais = IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT;

				if(StringUtils.isValidString(ls_departamento))
				{
					Municipio lm_parametros;
					lm_parametros = new Municipio();

					lm_parametros.setIdPais(ls_pais);
					lm_parametros.setIdDepartamento(ls_departamento);

					lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipios()
	{
		Collection<Municipio> lcm_municipios;
		lcm_municipios = null;

		ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosCalificacionDocumento;
		lcccd_consultaCriteriosCalificacionDocumento = getConsultaCriteriosCalificacionDocumento();

		if(lcccd_consultaCriteriosCalificacionDocumento != null)
		{
			Documento ld_documento;
			ld_documento = lcccd_consultaCriteriosCalificacionDocumento.getDocumento();

			if(ld_documento != null)
			{
				String ls_pais;
				String ls_departamento;

				ls_pais             = ld_documento.getIdPais();
				ls_departamento     = ld_documento.getIdDepartamento();

				if(StringUtils.isValidString(ls_pais) && StringUtils.isValidString(ls_departamento))
				{
					try
					{
						Municipio lm_parametros;
						lm_parametros = new Municipio();

						lm_parametros.setIdPais(ls_pais);
						lm_parametros.setIdDepartamento(ls_departamento);

						lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
					}
					catch(B2BException lb2be_e)
					{
						addMessage(lb2be_e);

						lcm_municipios = null;
					}
				}
			}
		}

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Pais> findPaises()
	{
		Collection<Pais> lcp_paises;

		try
		{
			lcp_paises = irr_referenceRemote.findPaises(true, getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcp_paises = null;
		}

		return lcp_paises;
	}

	/**
	 * Mostrar confirmacion.
	 */
	public void mostrarConfirmacion()
	{
		PrimeFaces.current().executeScript("PF('dlgConfirmacion').show();");
		PrimeFaces.current().ajax().update("dlgConfirmacion");
	}

	/**
	 * Oficina origen.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void oficinaOrigen()
	    throws B2BException
	{
		try
		{
			Collection<OficinaOrigen> lc_oficina;
			lc_oficina = getOficinaOrigen();

			if(lc_oficina == null)
				throw new B2BException(ErrorKeys.NO_OFICINA_ORIGEN);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fidDetalleActoAntiguoSistema:idGrowl");
		}
	}

	/**
	 * Pais.
	 */
	public void pais()
	{
		ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosCalificacionDocumento;
		lcccd_consultaCriteriosCalificacionDocumento = getConsultaCriteriosCalificacionDocumento();

		if(lcccd_consultaCriteriosCalificacionDocumento != null)
		{
			Documento ld_documento;
			ld_documento = lcccd_consultaCriteriosCalificacionDocumento.getDocumento();

			if(ld_documento != null)
			{
				String ls_pais;
				ls_pais = ld_documento.getIdPais();

				if(StringUtils.isValidString(ls_pais))
					ld_documento.setIdPais(ls_pais);

				ld_documento.setIdDepartamento(null);
				ld_documento.setIdMunicipio(null);
			}
		}

		findDepartamentos();
		findMunicipios();
		getOficinaOrigen();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvarAntSistema()
	{
		String ls_result;

		ls_result = null;

		try
		{
			{
				ConsultaCriteriosCalificacionAntiguoSistema lcccas_antiguoSistema;
				ConsultaCriteriosCalificacionDocumento      lcccd_documento;

				lcccas_antiguoSistema     = getConsultaCriteriosCalificacionAntiguoSistema();
				lcccd_documento           = getConsultaCriteriosCalificacionDocumento();

				if(lcccas_antiguoSistema.isConsultado() || lcccd_documento.isConsultado())
				{
					ls_result = salvarDatosConsulta(true);
					setConsultaVacia(false);
				}
				else
				{
					if(lcccas_antiguoSistema.isConsultaVacia() || lcccd_documento.isConsultaVacia())
					{
						setConsultaVacia(true);
						throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRARON_RESULTADOS);
					}
					else
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_CRITERIO_ANTIGUO_SISTEMA);
				}
			}
		}
		catch(B2BException lb2b_e)
		{
			addMessage(lb2b_e);
		}

		return ls_result;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String salvarDatosConsulta(boolean ab_accion)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		try
		{
			BeanCalificacion lbc_bean;
			FacesContext     lfc_context;

			lfc_context     = FacesUtils.getFacesContext();
			lbc_bean        = (BeanCalificacion)lfc_context.getApplication()
					                                           .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
					);

			if(lbc_bean != null)
			{
				boolean                                     lb_insert;
				boolean                                     lb_consultaVaciaDocumento;
				boolean                                     lb_consultaVaciaAntSistema;
				Collection<DataConsultaAntSistema>          lcdca_dataConsultaAntSistema;
				Collection<DataConsultaDatosDocumento>      lcdcdd_dataConsultaDatosDocumento;
				ConsultaCriteriosCalificacion               lccc_consultaCriteriosCalificacion;
				ConsultaCriteriosCalificacionAntiguoSistema lcccas_antiguoSistema;
				ConsultaCriteriosCalificacionDocumento      lcccd_documento;
				String                                      ls_observaciones;
				TurnoHistoria                               lth_turnoHistoria;
				String                                      ls_usuario;
				String                                      ls_ipRemota;

				lb_insert                              = false;
				lcdca_dataConsultaAntSistema           = getDataConsultaAntSistema();
				lcdcdd_dataConsultaDatosDocumento      = getDataConsultaDatosDocumento();
				lccc_consultaCriteriosCalificacion     = new ConsultaCriteriosCalificacion();
				lcccas_antiguoSistema                  = getConsultaCriteriosCalificacionAntiguoSistema();
				lcccd_documento                        = getConsultaCriteriosCalificacionDocumento();
				lb_consultaVaciaDocumento              = lcccd_documento.isConsultaVacia();
				lb_consultaVaciaAntSistema             = lcccas_antiguoSistema.isConsultaVacia();
				ls_observaciones                       = getObservaciones();
				lth_turnoHistoria                      = new TurnoHistoria();
				ls_usuario                             = getUserId();
				ls_ipRemota                            = getRemoteIpAddress();

				if(!StringUtils.isValidString(ls_observaciones))
					throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);

				lth_turnoHistoria.setIdTurnoHistoria(getIdTurnoHistoria());
				lth_turnoHistoria.setObservaciones(ls_observaciones);

				lccc_consultaCriteriosCalificacion.setAnotacionPredioAntSistema(
				    lcccas_antiguoSistema.getAnotacionPredio()
				);
				lccc_consultaCriteriosCalificacion.setAnotacionPredioDocumento(lcccd_documento.getAnotacionPredio());

				{
					Documento ld_documento;

					ld_documento = null;

					if(CollectionUtils.isValidCollection(lcdcdd_dataConsultaDatosDocumento))
					{
						for(DataConsultaDatosDocumento iterador : lcdcdd_dataConsultaDatosDocumento)
						{
							if(iterador != null)
								ld_documento = iterador.getDocumento();
						}
					}

					if(!ab_accion && lb_consultaVaciaDocumento)
					{
						ld_documento = lcccd_documento.getDocumento();

						ld_documento.setIdUsuarioCreacion(ls_usuario);
						ld_documento.setVersionDocumento(NumericUtils.getLongWrapper(1L));
					}

					if(ld_documento != null)
						lccc_consultaCriteriosCalificacion.setDocumento(ld_documento);
				}

				{
					DatosAntSistema ldas_datosAntSistema;

					ldas_datosAntSistema = null;

					if(CollectionUtils.isValidCollection(lcdca_dataConsultaAntSistema))
					{
						for(DataConsultaAntSistema iterador : lcdca_dataConsultaAntSistema)
						{
							if(iterador != null)
								ldas_datosAntSistema = iterador.getDatosAntSistema();
						}
					}

					if(!ab_accion && lb_consultaVaciaAntSistema)
					{
						ldas_datosAntSistema = lcccas_antiguoSistema.getDatosAntSistema();

						ldas_datosAntSistema.setAdquisicionPredio(IdentificadoresCommon.NO_APLICA);
						ldas_datosAntSistema.setIdUsuario(ls_usuario);
					}

					if(ldas_datosAntSistema != null)
						lccc_consultaCriteriosCalificacion.setDatosAntSistema(ldas_datosAntSistema);
				}

				if(!ab_accion)
				{
					if(!lb_consultaVaciaAntSistema && !lb_consultaVaciaDocumento)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_CRITERIO_ANTIGUO_SISTEMA);
				}

				lccc_consultaCriteriosCalificacion.setTurnoHistoria(lth_turnoHistoria);
				lccc_consultaCriteriosCalificacion.setIdUsuario(ls_usuario);
				lccc_consultaCriteriosCalificacion.setIpModificacion(ls_ipRemota);

				lb_insert = irr_calificacionRemote.salvarAntSistema(
					    lccc_consultaCriteriosCalificacion, ls_usuario, getLocalIpAddress(), ls_ipRemota
					);

				if(lb_insert)
				{
					clearConsulta("AntSistema");
					clearConsulta("Documento");
					setObservaciones(null);
					ls_return = NavegacionCommon.CALIFICACION;
					lbc_bean.clear();
					lbc_bean.generarDatosTramiteCantidad();
				}
			}
		}
		catch(B2BException lb2b_e)
		{
			if(ab_accion)
				throw lb2b_e;
			else
			{
				addMessage(lb2b_e);
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}
		}

		return ls_return;
	}

	/**
	 * Tipo oficina.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void tipoOficina()
	    throws B2BException
	{
		ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosCalificacionDocumento;
		lcccd_consultaCriteriosCalificacionDocumento = getConsultaCriteriosCalificacionDocumento();

		if(lcccd_consultaCriteriosCalificacionDocumento != null)
		{
			Documento ld_documento;
			ld_documento = lcccd_consultaCriteriosCalificacionDocumento.getDocumento();

			if(ld_documento != null)
			{
				String ls_tipoOficina;
				ls_tipoOficina = ld_documento.getIdTipoOficina();

				if(StringUtils.isValidString(ls_tipoOficina))
				{
					TipoOficina lto_to;

					lto_to = new TipoOficina();

					lto_to.setIdTipoOficina(ls_tipoOficina);

					lto_to = irr_referenceRemote.findTipoOficinaById(lto_to);

					if(lto_to != null)
					{
						TipoEntidad lte_te;

						lte_te = new TipoEntidad();

						lte_te.setIdTipoEntidad(lto_to.getIdTipoEntidad());

						lte_te = irr_referenceRemote.findTipoEntidadById(lte_te);

						ld_documento.setTipoEntidad(lte_te.getIdTipoEntidad());
					}

					ld_documento.setIdTipoOficina(ls_tipoOficina);
					ld_documento.setIdPais(null);
					ld_documento.setIdDepartamento(null);
					ld_documento.setIdMunicipio(null);
				}
			}
		}

		findPaises();
		findDepartamentos();
		findMunicipios();
		getOficinaOrigen();
	}

	/**
	 * Validar existencia antiguo sistema.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarExistenciaAntiguoSistema()
	    throws B2BException
	{
		try
		{
			ConsultaCriteriosCalificacionAntiguoSistema lcccas_consultaCriteriosAntiguoSistema;
			FacesContext                                lfc_context;
			boolean                                     lb_focus;

			lcccas_consultaCriteriosAntiguoSistema     = getConsultaCriteriosCalificacionAntiguoSistema();
			lfc_context                                = FacesContext.getCurrentInstance();
			lb_focus                                   = true;

			if(lcccas_consultaCriteriosAntiguoSistema != null)
			{
				AnotacionPredio anotacionPredio;
				anotacionPredio = lcccas_consultaCriteriosAntiguoSistema.getAnotacionPredio();

				Long ll_Matricula = null;
				ll_Matricula     = anotacionPredio.getIdMatricula();

				lb_focus = validateStyles(
					    "fconsultaPorCriteriosAnotacion:idMatriculaCalificacionAntSistema", lfc_context, ll_Matricula,
					    lb_focus
					);

				if(!NumericUtils.isValidLong(ll_Matricula))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_MATRICULA);

				if(anotacionPredio != null)
				{
					DatosAntSistema ldas_datosAntSistema;

					ldas_datosAntSistema = lcccas_consultaCriteriosAntiguoSistema.getDatosAntSistema();

					if(ldas_datosAntSistema != null)
					{
						Long   ll_libro;
						String ls_idTipoPredio;

						ll_libro            = ldas_datosAntSistema.getIdLibroAntSistema();
						ls_idTipoPredio     = ldas_datosAntSistema.getIdTipoPredio();

						lb_focus     = validateStyles(
							    "fconsultaPorCriteriosAnotacion:idTipoPredioAntSistema", lfc_context, ls_idTipoPredio,
							    lb_focus
							);

						lb_focus = validateStyles(
							    "fconsultaPorCriteriosAnotacion:idConsultaCriteriosAntSistemaLibro", lfc_context,
							    ll_libro, lb_focus
							);

						if(!NumericUtils.isValidLong(ll_libro))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_LIBRO);

						if(!StringUtils.isValidString(ls_idTipoPredio))
							throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);

						lcccas_consultaCriteriosAntiguoSistema.setConsultaVacia(true);
						setConsultaCriteriosCalificacionAntiguoSistema(lcccas_consultaCriteriosAntiguoSistema);
						consultaAntiguoSistema(lcccas_consultaCriteriosAntiguoSistema);
					}
				}
			}
		}
		catch(B2BException lb2b_e)
		{
			addMessage(lb2b_e);
		}
	}

	/**
	 * Validar existencia documento.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarExistenciaDocumento()
	    throws B2BException
	{
		try
		{
			ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosDocuemento;
			FacesContext                           lfc_context;
			boolean                                lb_focus;

			lcccd_consultaCriteriosDocuemento     = getConsultaCriteriosCalificacionDocumento();
			lfc_context                           = FacesContext.getCurrentInstance();
			lb_focus                              = true;

			if(lcccd_consultaCriteriosDocuemento != null)
			{
				Documento ld_documento;

				ld_documento = lcccd_consultaCriteriosDocuemento.getDocumento();

				if(ld_documento != null)
				{
					String ls_idDocumento;
					ls_idDocumento     = ld_documento.getIdTipoDocumento();

					lb_focus = validateStyles(
						    ":fconsultaPorCriteriosAnotacion:idConsultaCriteriosDocumentoTipoDocumento", lfc_context,
						    ls_idDocumento, lb_focus
						);

					String ls_numeroDocumento;
					ls_numeroDocumento     = ld_documento.getNumero();

					lb_focus = validateStyles(
						    ":fconsultaPorCriteriosAnotacion:idConsultaCriteriosDocumentoNumeroDocumento", lfc_context,
						    ls_numeroDocumento, lb_focus
						);

					Date ld_fechaDocumento;
					ld_fechaDocumento     = ld_documento.getFechaDocumento();

					lb_focus = validateStyles(
						    ":fconsultaPorCriteriosAnotacion:idConsultaCriteriosDocumentoFechaDocumento", lfc_context,
						    ld_fechaDocumento, lb_focus
						);

					String ls_tipoOficina;
					ls_tipoOficina     = ld_documento.getIdTipoOficina();

					lb_focus = validateStyles(
						    ":fconsultaPorCriteriosAnotacion:idConsultaCriteriosDocumentoTipoOficina", lfc_context,
						    ls_tipoOficina, lb_focus
						);

					String ls_tipoEntidad;
					ls_tipoEntidad     = ld_documento.getTipoEntidad();

					lb_focus = validateStyles(
						    "fconsultaPorCriteriosAnotacion:idConsultaCriteriosDocumentoTipoEntidad", lfc_context,
						    ls_tipoEntidad, lb_focus
						);

					String ls_idpais;
					ls_idpais     = ld_documento.getIdPais();

					lb_focus = validateStyles(
						    "fconsultaPorCriteriosAnotacion:idConsultaCriteriosDocumentoPaisDocumento", lfc_context,
						    ls_idpais, lb_focus
						);

					String ls_idDepartamento;
					ls_idDepartamento     = ld_documento.getIdDepartamento();

					lb_focus = validateStyles(
						    "fconsultaPorCriteriosAnotacion:idConsultaCriteriosDocumentoDepartamentoDocumento",
						    lfc_context, ls_idDepartamento, lb_focus
						);

					String ls_idMunicipio;
					ls_idMunicipio     = ld_documento.getIdMunicipio();

					lb_focus = validateStyles(
						    "fconsultaPorCriteriosAnotacion:idConsultaCriteriosDocumentoMunicipioDocumento", lfc_context,
						    ls_idMunicipio, lb_focus
						);

					String ls_oficinaOrigen;
					ls_oficinaOrigen     = ld_documento.getIdOficinaOrigen();

					lb_focus = validateStyles(
						    "fconsultaPorCriteriosAnotacion:idConsultaCriteriosDocumentoOficinaOrigen", lfc_context,
						    ls_oficinaOrigen, lb_focus
						);

					if(!StringUtils.isValidString(ls_idDocumento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

					if(!StringUtils.isValidString(ls_numeroDocumento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NUMERO_DOC);

					if(ld_fechaDocumento == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);

					if(!StringUtils.isValidString(ls_tipoOficina))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_OFICINA);

					if(!StringUtils.isValidString(ls_tipoEntidad))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_ENTIDAD);

					if(!StringUtils.isValidString(ls_idpais))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);

					if(!StringUtils.isValidString(ls_idDepartamento))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);

					if(!StringUtils.isValidString(ls_idMunicipio))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);

					if(!StringUtils.isValidString(ls_oficinaOrigen))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN);
				}

				lcccd_consultaCriteriosDocuemento.setConsultaVacia(true);
				setConsultaCriteriosCalificacionDocumento(lcccd_consultaCriteriosDocuemento);
				consultaDatosDocumento(lcccd_consultaCriteriosDocuemento);
			}
		}
		catch(B2BException lb2b_e)
		{
			addMessage(lb2b_e);
		}
	}
}
