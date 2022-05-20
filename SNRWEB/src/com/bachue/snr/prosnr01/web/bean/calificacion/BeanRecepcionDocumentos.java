package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.OperadorCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.calificacion.DatosDelInteresado;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import com.bachue.snr.prosnr01.web.bean.consulta.SGD.BeanConsultaSGD;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanRecepcionDocumentos.
 *
 * @author jpatino
 */
@ManagedBean(name = "beanRecepcionDocumentos")
@SessionScoped
public class BeanRecepcionDocumentos extends BeanSuspension implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5789370671139269418L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanRecepcionDocumentos.class);

	/** Constante is_idForm. */
	public static final String is_idForm = "fRecepcionDocumentos";

	/**
	 * Propiedad irr calificacion remote.
	 */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ictc causales desistimiento. */
	private Collection<TipoCausal> ictc_causalesDesistimiento;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is texto rechazo desistimiento. */
	private String is_textoRechazoDesistimiento;

	/** Atributo ib_documentosEnviados */
	private boolean ib_documentosEnviados;

	/** Propiedad ib pais inter correspondencia. */
	private boolean ib_paisInterCorrespondencia;

	/** Propiedad ib pais inter residencia. */
	private boolean ib_paisInterResidencia;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de causales desistimiento.
	 *
	 * @param actc_ctc asigna el valor a la propiedad causales desistimiento
	 */
	public void setCausalesDesistimiento(Collection<TipoCausal> actc_ctc)
	{
		ictc_causalesDesistimiento = actc_ctc;
	}

	/**
	 * Retorna el valor de causales desistimiento.
	 *
	 * @return el valor de causales desistimiento
	 */
	public Collection<TipoCausal> getCausalesDesistimiento()
	{
		return ictc_causalesDesistimiento;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param ab_b Argumento encargado de modificar la propiedad.
	 */
	public void setDocumentosEnviados(boolean ab_b)
	{
		ib_documentosEnviados = ab_b;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isDocumentosEnviados()
	{
		return ib_documentosEnviados;
	}

	/**
	 * Modifica el valor de pais inter correspondencia.
	 *
	 * @param ab_b asigna el valor a la propiedad pais inter correspondencia
	 */
	public void setPaisInterCorrespondencia(boolean ab_b)
	{
		ib_paisInterCorrespondencia = ab_b;
	}

	/**
	 * Valida la propiedad pais inter correspondencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pais inter correspondencia
	 */
	public boolean isPaisInterCorrespondencia()
	{
		return ib_paisInterCorrespondencia;
	}

	/**
	 * Modifica el valor de pais inter residencia.
	 *
	 * @param ab_b asigna el valor a la propiedad pais inter residencia
	 */
	public void setPaisInterResidencia(boolean ab_b)
	{
		ib_paisInterResidencia = ab_b;
	}

	/**
	 * Valida la propiedad pais inter residencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pais inter residencia
	 */
	public boolean isPaisInterResidencia()
	{
		return ib_paisInterResidencia;
	}

	/**
	 * Metodo para asignar un valor al atributo is_textoRechazoDesistimiento.
	 *
	 * @param as_s Objeto contenedor del valor a almacenar en la variable
	 */
	public void setTextoRechazoDesistimiento(String as_s)
	{
		is_textoRechazoDesistimiento = as_s;
	}

	/**
	 * Metodo para obtener el valor del atributo is_textoRechazoDesistimiento.
	 *
	 * @return Cadena de caracteres con el valor de la variable
	 */
	public String getTextoRechazoDesistimiento()
	{
		return is_textoRechazoDesistimiento;
	}

	/**
	 * Accion consulta SGD.
	 */
	public void accionConsultaSGD()
	{
		try
		{
			FacesContext lfc_context;

			lfc_context = FacesUtils.getFacesContext();

			String ls_url;

			BeanConsultaSGD lb_beanConsultaSGD;
			lb_beanConsultaSGD = (BeanConsultaSGD)lfc_context.getApplication()
					                                             .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_CONSULTA_SGD, BeanConsultaSGD.class
					);
			lb_beanConsultaSGD.setIdTurnoConsulta(getIdTurno());
			ls_url = lb_beanConsultaSGD.consultaSGD(OperadorCommon.OR);
			lb_beanConsultaSGD.setNewTab(true);
			setZipGenerado(true);

			if(StringUtils.isValidString(ls_url))
				PrimeFaces.current().executeScript("window.open('consultaSGD.jsf', '_newtab')");
		}
		catch(Exception le_e)
		{
			B2BException lb2be_e;

			lb2be_e = new B2BException("IOException", le_e);

			addMessage(lb2be_e);
			clh_LOGGER.error("accionConsultaSGD", lb2be_e);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			PrimeFaces.current().ajax().update("fRecepcionDocumentos:globalMsg");
		}
	}

	/**
	 * Método encargado de realizar la logica de navegacion de suspension de tramites de ART 19 y proceso de recepcion de documentos.
	 *
	 * @return Variable de tipo String que indica regla de navegación
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String accionTerminarProceso()
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		try
		{
			if(isZipGenerado())
			{
				ls_return = super.accionTerminarProceso(getEtapa());

				clear(true);

				{
					FacesContext     lfc_context;
					BeanCalificacion lb_beanCalificacion;

					lfc_context             = FacesUtils.getFacesContext();
					lb_beanCalificacion     = (BeanCalificacion)lfc_context.getApplication()
							                                                   .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
							);

					if(lb_beanCalificacion != null)
					{
						lb_beanCalificacion.clear();
						lb_beanCalificacion.generarDatosTramiteCantidad();
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("accionTerminarProceso", lb2be_e);
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/** {@inheritdoc} */
	public void addTipoDocumental()
	    throws B2BException
	{
		Collection<TipoDocumental> lctau_ctd;
		TipoDocumental             ltd_td;

		ltd_td        = new TipoDocumental();
		lctau_ctd     = getTiposDocumentales();

		ltd_td.setSeleccionado(true);

		if(!CollectionUtils.isValidCollection(lctau_ctd))
			lctau_ctd = new ArrayList<TipoDocumental>();

		if(isProceso45())
			ltd_td.setIdTipoDocumental("68");
		else
		{
			Collection<com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental> lctd_tiposDocs;
			lctd_tiposDocs = irr_referenceRemote.findAllTiposDocumentales(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress(), null
				);

			Iterator<com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental> litd_iterador;
			litd_iterador = lctd_tiposDocs.iterator();

			if(litd_iterador.hasNext())
			{
				com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental ltd_tipoDoc;
				ltd_tipoDoc = litd_iterador.next();

				ltd_td.setIdTipoDocumental(ltd_tipoDoc.getIdTipoDocumental());
			}
		}

		lctau_ctd.add(ltd_td);

		setTiposDocumentales(lctau_ctd);
	}

	/**
	 * Cambiar departamento correspondencia.
	 */
	public void cambiarDepartamentoCorrespondencia()
	{
		findMunicipioCorrespondencia();
	}

	/** {@inheritdoc} */
	public void cambiarDepartamentoResidencia()
	{
		findMunicipioResidencia();
	}

	/**
	 * Cambiar pais correspondencia.
	 */
	public void cambiarPaisCorrespondencia()
	{
		PersonaDireccion lpd_correspondencia;
		lpd_correspondencia = getParametros().getDatosDelInteresado().getDireccionCorrespondencia();

		if(lpd_correspondencia != null)
		{
			String ls_pais;
			ls_pais = lpd_correspondencia.getIdPais();

			if(
			    !StringUtils.isValidString(ls_pais)
				    || !ls_pais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
			)
			{
				setPaisInterCorrespondencia(true);
				lpd_correspondencia.setIdDepartamento(null);
			}
			else
				setPaisInterCorrespondencia(false);
		}

		findDepartamentosCorrespondencia();
		findMunicipioCorrespondencia();
	}

	/** {@inheritdoc} */
	public void cambiarPaisResidencia()
	{
		PersonaDireccion lpd_residencia;
		lpd_residencia = getParametros().getDatosDelInteresado().getDireccionResidencia();

		if(lpd_residencia != null)
		{
			String ls_pais;
			ls_pais = lpd_residencia.getIdPais();

			if(
			    !StringUtils.isValidString(ls_pais)
				    || !ls_pais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
			)
			{
				setPaisInterResidencia(true);
				lpd_residencia.setIdDepartamento(null);
			}
			else
				setPaisInterResidencia(false);
		}

		findDepartamentosResidencia();
		findMunicipioResidencia();
	}

	/**
	 * Cambiar pais telefono.
	 */
	public void cambiarPaisTelefono()
	{
		PersonaTelefono lpt_telefono;
		lpt_telefono = getParametros().getDatosDelInteresado().getTelefonoFijo();

		if(lpt_telefono != null)
		{
			String ls_pais;
			ls_pais = lpt_telefono.getIdPais();

			if(
			    !StringUtils.isValidString(ls_pais)
				    || !ls_pais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
			)
			{
				setPaisInterCorrespondencia(true);
				lpt_telefono.setIdDepartamento(null);
			}
		}

		findDepartamentosCorrespondencia();
		findMunicipioCorrespondencia();
	}

	/**
	 * Obtiene el texto por defecto cuando se elige 'N' en un desistimiento.
	 */
	public void cargarTextoRechazoDesistimiento()
	{
		String ls_respuestaDesistimiento;

		ls_respuestaDesistimiento = getRespuestaSolicitudDesistimiento();

		if(StringUtils.isValidString(ls_respuestaDesistimiento))
		{
			String ls_mensaje;

			ls_mensaje = null;

			if(ls_respuestaDesistimiento.equals(EstadoCommon.N))
			{
				String ls_textoRechazo;

				ls_textoRechazo = getTextoRechazoDesistimiento();

				if(!StringUtils.isValidString(ls_textoRechazo))
				{
					try
					{
						String     ls_idConstante;
						Constantes lc_constante;

						ls_idConstante     = (getEtapa() == EtapaCommon.ID_ETAPA_ANALISIS_DE_DESISTIMIENTO)
							? ConstanteCommon.TEXTO_RECHAZO_DESISTIMIENTO_NEGACION_FUERA_DE_TERMINO
							: ConstanteCommon.TEXTO_RECHAZO_DESISTIMIENTO;
						lc_constante       = irr_referenceRemote.findConstantesById(ls_idConstante);

						if(lc_constante != null)
						{
							ls_mensaje = lc_constante.getCaracter();

							setTextoRechazoDesistimiento(ls_mensaje);
						}
						else
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ls_idConstante;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}
					}
					catch(B2BException lb2be_e)
					{
						addMessage(lb2be_e);

						clh_LOGGER.error("cargarTextoRechazoDesistimiento", lb2be_e);
					}
				}
			}

			{
				Suspension ls_suspension;

				ls_suspension = getParametros();

				if(ls_suspension != null)
					ls_suspension.setConsideracion(ls_mensaje);
			}
		}
	}

	/**
	 * Carga los tipos causales para desistimientos.
	 */
	public void cargarTiposCausalesDesistimiento()
	{
		try
		{
			setCausalesDesistimiento(
			    irr_referenceRemote.findAllTiposCausalesActivosPorProcSubproc(
			        ProcesoCommon.ID_PROCESO_39, ProcesoCommon.ID_SUBPROCESO_6, getUserId(), getLocalIpAddress(),
			        getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarTiposCausalesDesistimiento", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Clear.
	 *
	 * @param ab_clearAll correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException
	 */
	public void clear(boolean ab_clearAll)
	    throws B2BException
	{
		super.clean(ab_clearAll);

		setTextoRechazoDesistimiento(null);
		setDeshabilitarCamposPorNit(false);
		setDeshabilitarTipoDocumento(false);
		setDeshabilitarTipoPersona(false);
		setDocumento(false);
		setPaisInterCorrespondencia(false);
		setPaisInterResidencia(false);
		setRazonSocial(false);
		setModificar(false);
		setCalidadActua(false);
		setDocumento(false);
		setDeshabilitarTipoDocumento(true);
		setCausalesDesistimiento(null);
		setRespuestaSolicitudDesistimiento(null);
		setCausalDevolucion(null);
		setDocumentosEnviados(false);
	}

	/** {@inheritdoc} */
	public void deleteTipoDocumental(TipoDocumental atd_tipoDoc)
	{
		Collection<TipoDocumental> lctd_tiposDocumentales;
		lctd_tiposDocumentales = getTiposDocumentales();

		if((lctd_tiposDocumentales != null) && (atd_tipoDoc != null))
			lctd_tiposDocumentales.remove(atd_tipoDoc);
	}

	/**
	 * Metodo encargado de consultar si ya se enviaron los documentos al OWCC
	 */
	public void documentosEnviadosOWCC()
	{
		try
		{
			String ls_idTurnoHistoria;

			ls_idTurnoHistoria = getIdTurnoHistoria();

			if(!StringUtils.isValidString(ls_idTurnoHistoria))
			{
				Suspension ls_parametros;

				ls_parametros = getParametros();

				if(ls_parametros != null)
				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = ls_parametros.getTurnoHistoria();

					if(lth_turnoHistoria != null)
						ls_idTurnoHistoria = StringUtils.getString(lth_turnoHistoria.getIdTurnoHistoria());
				}
			}

			boolean lb_enviados;

			lb_enviados = validarEnvioDocumentosOWCC(ls_idTurnoHistoria);

			if(lb_enviados)
				PrimeFaces.current().executeScript("PF('wvPoll').stop();");

			setZipGenerado(lb_enviados);
			setDocumentosEnviados(lb_enviados);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("documentosEnviadosOWCC", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente("fRecepcionDocumentos" + ":idBotonConsultaSGD:");
			actualizarComponente("fRecepcionDocumentos" + ":idCBEnviar:");
		}
	}

	/**
	 * Editar datos basicos.
	 */
	public void editarDatosBasicos()
	{
		setEditarDatosBasicos(true);
		super.setEditarDatosBasicos(true);
	}

	/**
	 * Editar tipo.
	 *
	 * @param atd_tipo correspondiente al valor del tipo de objeto TipoDocumental
	 */
	public void editarTipo(TipoDocumental atd_tipo)
	{
		atd_tipo.setEditar(false);
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentosCorrespondencia()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			PersonaDireccion lpd_correspondencia;
			lpd_correspondencia = getParametros().getDatosDelInteresado().getDireccionCorrespondencia();

			if(lpd_correspondencia != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(lpd_correspondencia.getIdPais());

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/** {@inheritdoc} */
	public Collection<Departamento> findDepartamentosResidencia()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			PersonaDireccion lpd_residencia;
			lpd_residencia = getParametros().getDatosDelInteresado().getDireccionResidencia();

			if(lpd_residencia != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(lpd_residencia.getIdPais());

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
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
	 * Find indicativo departamento.
	 */
	public void findIndicativoDepartamento()
	{
		try
		{
			PersonaTelefono lpt_telefono;
			lpt_telefono = getParametros().getDatosDelInteresado().getTelefonoFijo();

			if(lpt_telefono != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(lpt_telefono.getIdPais());
				ld_parametros.setIdDepartamento(lpt_telefono.getIdDepartamento());

				ld_parametros = irr_referenceRemote.findDepartamentById(ld_parametros);

				if(ld_parametros != null)
					lpt_telefono.setIndicativo(ld_parametros.getIndicativoTelefonico());
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipioCorrespondencia()
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		try
		{
			Suspension ls_suspension;

			ls_suspension = getParametros();

			if(ls_suspension != null)
			{
				DatosDelInteresado lddi_datosInteresado;

				lddi_datosInteresado = ls_suspension.getDatosDelInteresado();

				if(lddi_datosInteresado != null)
				{
					PersonaDireccion lpd_correspondencia;

					lpd_correspondencia = lddi_datosInteresado.getDireccionCorrespondencia();

					if(lpd_correspondencia != null)
					{
						Municipio lm_parametros;

						lm_parametros = new Municipio();

						lm_parametros.setIdPais(lpd_correspondencia.getIdPais());
						lm_parametros.setIdDepartamento(lpd_correspondencia.getIdDepartamento());

						lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
					}
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

	/** {@inheritdoc} */
	public Collection<Municipio> findMunicipioResidencia()
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		try
		{
			PersonaDireccion lpd_residencia;

			lpd_residencia = getParametros().getDatosDelInteresado().getDireccionResidencia();

			if(lpd_residencia != null)
			{
				Municipio lm_parametros;

				lm_parametros = new Municipio();

				lm_parametros.setIdPais(lpd_residencia.getIdPais());
				lm_parametros.setIdDepartamento(lpd_residencia.getIdDepartamento());

				lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
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
	 * Construye los documentos del tramite.
	 */
	public void generarDocumentos()
	{
		try
		{
			super.generarDocumentos(true, ":fRecepcionDocumentos");
			PrimeFaces.current().executeScript("PF('wvPoll').start();");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			setDocumentoGenerado(false);
			clh_LOGGER.error("generarDocumentos", lb2be_e);
		}
		finally
		{
			PrimeFaces.current().ajax().update("fRecepcionDocumentos:globalMsg");
		}
	}

	/** {@inheritdoc} */
	public String generateRandomIdForNotCaching()
	{
		return java.util.UUID.randomUUID().toString();
	}

	/**
	 * Termina el proceso de recepcion de documentos.
	 */
	public void terminarProceso()
	{
		try
		{
			if(isZipGenerado())
				super.terminarProceso(true, ":fRecepcionDocumentos");
			else
				throw new B2BException(ErrorKeys.ERROR_GENERAR_RESOLUCION_COMUNICADO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("accionTerminarProceso", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Verificar propiedad.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void verificarPropiedad()
	    throws B2BException
	{
		try
		{
			boolean lb_noPertenecePropiedad;
			lb_noPertenecePropiedad = irr_calificacionRemote.verificaPropiedad(
				    getIdTurnoHistoria(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lb_noPertenecePropiedad)
			{
				addMessage(MessagesKeys.PERSONA_NO_ES_TITULAR_DE_PROPIEDAD);

				ExternalContext lec_externalContext;

				lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

				if(lec_externalContext != null)
				{
					Flash lf_flash;

					lf_flash = lec_externalContext.getFlash();

					if(lf_flash != null)
						lf_flash.setKeepMessages(true);
				}

				actualizarComponente("fRecepcionDocumentos:globalMsg");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("verificarPropiedad", lb2be_e);
			addMessage(lb2be_e);
		}
	}
}
