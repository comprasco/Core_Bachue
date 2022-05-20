package com.bachue.snr.prosnr01.web.bean.devolucionDinero;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.devolucionesDinero.DevolucionDineroRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.grabacion.GrabacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.trasladoMatriculas.TrasladoMatriculasRemote;

import com.bachue.snr.prosnr01.model.calificacion.DatosDelInteresado;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;

import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.bean.traslados.BeanTrasladosDetalle;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanResolucionPlaneacion.
 *
 * @author
 */
@SessionScoped
@ManagedBean(name = "beanActoAdministrativoDetalle")
public class BeanActoAdministrativoDetalle extends BeanTrasladosDetalle implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1905348128127656476L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanActoAdministrativoDetalle.class);

	/** Constante is_formularioProcesosGrabacion. */
	private static final String CS_ID_FORM = ":fProcesosTraslados";

	/** Constante is_globalMsg. */
	private static final String is_globalMsg = ":globalMsg";

	/** Constante is_messageProcesosGrabacion. */
	private static final String is_messages = CS_ID_FORM + is_globalMsg;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/**  Propiedad iddr_devolucionDineroRemote. */
	@EJB
	private DevolucionDineroRemote iddr_devolucionDineroRemote;

	/** Propiedad igr grabacion remote. */
	@EJB
	private GrabacionRemote igr_grabacionRemote;

	/** Propiedad ll data. */
	private List<Map<String, Object>> ll_data;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad itmr traslado matriculas remote. */
	@EJB
	private TrasladoMatriculasRemote itmr_trasladoMatriculasRemote;

	/** Propiedad ib bandejaentrada. */
	private boolean ib_bandejaentrada;

	/** Propiedad ib consulta individual. */
	private boolean ib_consultaIndividual;

	/** Propiedad il id etapa. */
	private long il_idEtapa;

	/**
	 * Instancia un nuevo objeto bean detalle antiguo sistema.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public BeanActoAdministrativoDetalle()
	    throws B2BException
	{
		getIdEtapa();
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de bandejaentrada.
	 *
	 * @param ab_b asigna el valor a la propiedad bandejaentrada
	 */
	public void setBandejaentrada(boolean ab_b)
	{
		ib_bandejaentrada = ab_b;
	}

	/**
	 * Valida la propiedad bandejaentrada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en bandejaentrada
	 */
	public boolean isBandejaentrada()
	{
		return ib_bandejaentrada;
	}

	/**
	 * Modifica el valor de consulta individual.
	 *
	 * @param ab_b asigna el valor a la propiedad consulta individual
	 */
	public void setConsultaIndividual(boolean ab_b)
	{
		ib_consultaIndividual = ab_b;
	}

	/**
	 * Valida la propiedad consulta individual.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en consulta individual
	 */
	public boolean isConsultaIndividual()
	{
		return ib_consultaIndividual;
	}

	/**
	 * Sets the data.
	 *
	 * @param all_ll correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setData(List<Map<String, Object>> all_ll)
	{
		ll_data = all_ll;
	}

	/**
	 * Retorna el valor de data.
	 *
	 * @return el valor de data
	 */
	public List<Map<String, Object>> getData()
	{
		return ll_data;
	}

	/**
	 * Modifica el valor de id etapa.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa
	 */
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna el valor de id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public long getIdEtapa()
	{
		if(il_idEtapa == 0)
		{
			String tmp = FacesUtils.getStringFacesParameter("il_idEtapa");

			if(StringUtils.isValidString(tmp))
				il_idEtapa = Long.parseLong(tmp);
		}

		return il_idEtapa;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param as_s asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de nir.
	 *
	 * @param as_s asigna el valor a la propiedad nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna el valor de nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
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
	 * Metodo encargado de cargar la información de acto administrativo.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarDatosActoAdministrativo()
	    throws B2BException
	{
		try
		{
			Suspension ls_parametros;

			ls_parametros = getParametros();

			if(ls_parametros != null)
			{
				TagPlantillaResolucion ltpr_actoAdministrativo;
				String                 ls_idTurno;

				ltpr_actoAdministrativo     = new TagPlantillaResolucion();
				ls_idTurno                  = getIdTurno();

				ltpr_actoAdministrativo.setIdTurno(ls_idTurno);
				ltpr_actoAdministrativo.setIdEtapa(getEtapa());
				ltpr_actoAdministrativo.setIdTurnoHistoria(getIdTurnoHistoria());
				ltpr_actoAdministrativo.setIdMotivo(NumericUtils.getLong(getMotivoTramite()));

				ltpr_actoAdministrativo = iddr_devolucionDineroRemote.cargarDatosActoAdministrativo(
					    ltpr_actoAdministrativo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(ltpr_actoAdministrativo != null)
				{
					byte[] lba_archivo;

					lba_archivo = ltpr_actoAdministrativo.getArchivo();

					ls_parametros.setOficiosTexto(ltpr_actoAdministrativo.getOficiosTexto());
					setTipoArchivo(ltpr_actoAdministrativo.getTipoArchivo());
					setIdTurno(ls_idTurno);
					setArchivo(lba_archivo);

					setImagenDocumento(
					    generarArchivosDescarga(
					        lba_archivo, TipoContenidoCommon.PDF,
					        ltpr_actoAdministrativo.getTipoArchivo() + ExtensionCommon.PDF_PUNTO
					    )
					);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDatosActoAdministrativo", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Consultar persona interesado.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultarPersonaInteresadoActo()
	    throws B2BException
	{
		TurnoHistoria lth_turnoHistoria;

		lth_turnoHistoria = new TurnoHistoria();
		lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

		lth_turnoHistoria = irr_calificacionRemote.findTurnoHistoriaById(lth_turnoHistoria);

		if(lth_turnoHistoria != null)
		{
			String ls_idSolicitud;

			ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

			if(StringUtils.isValidString(ls_idSolicitud))
			{
				Suspension ls_parametros;

				ls_parametros = new Suspension();

				{
					DatosDelInteresado lddi_data;

					lddi_data = igr_grabacionRemote.consultarPersonaInteresado(ls_idSolicitud, true);

					if(lddi_data != null)
					{
						BeanDireccion lbd_beanDireccion;

						lbd_beanDireccion = getBeanDireccion();

						lbd_beanDireccion.limpiarBeanDireccion();
						lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
						lbd_beanDireccion.setDeshabilitarResidencia(true);
						lbd_beanDireccion.setHabilitadoPorConsulta(true);
						lbd_beanDireccion.setForm(CS_ID_FORM);
						lbd_beanDireccion.setDireccionCorrespondencia(lddi_data.getDireccionCorrespondencia());
						lbd_beanDireccion.setDireccionResidencia(lddi_data.getDireccionResidencia());
					}
					else
						lddi_data = new DatosDelInteresado();

					ls_parametros.setDatosDelInteresado(lddi_data);
				}

				setParametros(ls_parametros);
			}
		}
	}

	/**
	 * Metodo encargado de consultar si ya se enviaron los documentos al OWCC
	 */
	public void documentosEnviadosOWCC()
	{
		try
		{
			documentosEnviadosOWCC(getIdTurnoHistoria());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("documentosEnviadosOWCC", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(CS_ID_FORM);
		}
	}

	/**
	 * Metodo encargado de enviar a etapa de aprobacion.
	 *
	 * @return el valor de string
	 */
	public String enviarEjecutor()
	{
		String ls_return;

		ls_return = null;

		try
		{
			{
				TagPlantillaResolucion laa_actoAdministrativo = new TagPlantillaResolucion();

				laa_actoAdministrativo.setIdTurnoHistoria(getIdTurnoHistoria());
				laa_actoAdministrativo.setObservaciones(getObservaciones());
				laa_actoAdministrativo.setIdTurno(getIdTurno());
				laa_actoAdministrativo.setTiposDocumentales(getTiposDocumentales());
				laa_actoAdministrativo.setIdEtapa(EtapaCommon.ID_ETAPA_EMISION_ACTO_ADMINISTRATIVO);

				iddr_devolucionDineroRemote.enviarResponsableActoAdministrativo(
				    laa_actoAdministrativo, NumericUtils.getLong(getMotivoTramite()), getUserId(), getLocalIpAddress(),
				    getRemoteIpAddress()
				);
			}

			{
				BeanActoAdministrativos lbt_bean;

				lbt_bean = (BeanActoAdministrativos)obtenerInstanciaBean(
					    BeanActoAdministrativos.class, BeanNameCommon.BEAN_TRASLADOS
					);

				lbt_bean.clear();
				lbt_bean.limpiarBandeja();
				lbt_bean.setIdEtapa(StringUtils.getString(EtapaCommon.ID_ETAPA_EMISION_ACTO_ADMINISTRATIVO));
				lbt_bean.generarDatosTramiteCantidad();
				lbt_bean.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_EMISION_ACTO_ADMINISTRATIVO, false);

				ls_return = NavegacionCommon.ACTO_ADMINISTRATIVO_BANDEJA;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			actualizarComponente(CS_ID_FORM);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection de InteresadoDocumentoTipo.
	 *
	 * @return devuelve el valor de Collection de InteresadoDocumentoTipo
	 *
	 */
	public Collection<String> findDecisionTramiteRegistral()
	{
		Collection<String> lcidt_datos;

		try
		{
			lcidt_datos = iddr_devolucionDineroRemote.findDecisionTramiteRegistral(getIdTurnoHistoria());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Metodo encargado de generar los documentos de actuaciones administrativas.
	 *
	 * @param ab_b de ab_b
	 */
	public void generarDocumentosActoAdministrativo()
	{
		try
		{
			Suspension ls_parametros;

			ls_parametros = getParametros();

			if(ls_parametros != null)
			{
				TagPlantillaResolucion laa_actoAdministrativo;
				long                   ll_idMotivo;
				String                 ls_tipoArchivo;

				laa_actoAdministrativo     = new TagPlantillaResolucion();
				ll_idMotivo                = NumericUtils.getLong(getMotivoTramite());
				ls_tipoArchivo             = StringUtils.getStringNotNull(getTipoArchivo());

				laa_actoAdministrativo.setOficiosTexto(convertirOficiosTexto(ls_parametros.getOficiosTexto()));
				laa_actoAdministrativo.setIdTurno(getIdTurno());
				laa_actoAdministrativo.setIdTurnoHistoria(getIdTurnoHistoria());
				laa_actoAdministrativo.setTiposDocumentales(getTiposDocumentales());
				laa_actoAdministrativo.setTipoArchivo(ls_tipoArchivo);
				laa_actoAdministrativo.getOficiosTexto();

				laa_actoAdministrativo = iddr_devolucionDineroRemote.generarDocumentosActoAdministrativo(
					    laa_actoAdministrativo, ll_idMotivo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(laa_actoAdministrativo != null)
				{
					PrimeFaces.current().executeScript("PF('wvPoll').start();");
					setFile(laa_actoAdministrativo.getArchivo());
					setMostrarDescargarZip(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDocumentos", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			setImagenDocumento(
			    generarArchivosDescarga(
			        getArchivo(), TipoContenidoCommon.PDF, IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
			    )
			);

			actualizarComponente(CS_ID_FORM);
		}
	}

	/**
	     * Metodo encargado de visualizar los cambios realizados en la resolución.
	     *
	     * @param ab_suspension de ab suspension
	     * @return Retorna a la misma pantalla que lo invocó.
	     */
	public String visualizarCambiosActoAdministrativo()
	{
		try
		{
			Suspension ls_data;

			ls_data = getParametros();

			if(ls_data != null)
			{
				TagPlantillaResolucion laa_actoAdministrativo;

				laa_actoAdministrativo = new TagPlantillaResolucion();

				laa_actoAdministrativo.setOficiosTexto(convertirOficiosTexto(ls_data.getOficiosTexto()));
				laa_actoAdministrativo.setIdTurnoHistoria(getIdTurnoHistoria());

				ls_data.setTurnoHistoria(getTurnoHistoria());
				laa_actoAdministrativo.setSuspension(ls_data);

				laa_actoAdministrativo = iddr_devolucionDineroRemote.visualizarCambiosResolucion(
					    laa_actoAdministrativo, NumericUtils.getLong(getMotivoTramite()), getUserId(),
					    getLocalIpAddress(), getRemoteIpAddress()
					);

				if(laa_actoAdministrativo != null)
				{
					setArchivo(laa_actoAdministrativo.getArchivo());

					setDatosGuardados(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("visualizarCambiosResolucion", lb2be_e);

			addMessage(lb2be_e);
			actualizarComponente(is_messages);

			{
				ExternalContext lec_externalContext;

				lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

				if(lec_externalContext != null)
				{
					Flash lf_flash;

					lf_flash = lec_externalContext.getFlash();

					if(lf_flash != null)
						lf_flash.setKeepMessages(true);
				}
			}
		}

		setImagenDocumento(
		    generarArchivosDescarga(
		        getArchivo(), TipoContenidoCommon.PDF, IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
		    )
		);

		return NavegacionCommon.ACTO_ADMINISTRATIVO_DETALLE;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String volver()
	{
		String ls_return;
		ls_return = NavegacionCommon.ACTO_ADMINISTRATIVO_BANDEJA;

		try
		{
			FacesContext            lfc_context;
			BeanActoAdministrativos lbde_bean;

			lfc_context     = FacesUtils.getFacesContext();
			lbde_bean       = lfc_context.getApplication()
					                         .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_ACTO_ADMINISTRATIVOS, BeanActoAdministrativos.class
					);

			if(lbde_bean != null)
			{
				lbde_bean.clear();
				lbde_bean.generarDatosTramiteCantidad();
			}
		}
		catch(B2BException e)
		{
			clh_LOGGER.error("volver", e);
			ls_return = null;
		}

		return ls_return;
	}
}
