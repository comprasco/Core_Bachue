package com.bachue.snr.prosnr01.web.bean.cuentaCupos;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.cuentaCupos.CuentaCuposRemote;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.cuentaCupos.AprobacionCuentaCupos;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de eventos de la pantalla de cuenta cupos.
 *
 * @author Manuel Blanco
 */
@SessionScoped
@ManagedBean(name = "beanCuentaCupos")
public class BeanCuentaCupos extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3458125273605008569L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanCuentaCupos.class);

	/** Constante is_idForm. */
	private static final String is_idForm = "fCuentaCupos";

	/** Constante is_idForm. */
	private static final String is_idObservaciones = "fCuentaCupos:idITAObservaciones";

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fCuentaCupos:idGrowl";

	/** Propiedad ii tamanio archivo 5 MB. */
	//FORMATO Comentar antes de formatear
	private final int ii_tamanioArchivo5MB = 5_000_000;

	/** Propiedad iacc datos aprobacion. */
	private AprobacionCuentaCupos iacc_datosAprobacion;

	/** Propiedad ibd nuevo valor maximo. */
	private BigDecimal ibd_nuevoValorMaximo;

	/** Propiedad ibd nuevo valor minimo. */
	private BigDecimal ibd_nuevoValorMinimo;

	/** Propiedad icsc documentos cargados. */
	private Collection<StreamedContent> icsc_documentosCargados;

	/** Propiedad ictc datos bandeja detalle. */
	private Collection<TramiteCantidad> ictc_datosBandejaDetalle;

	/** Propiedad ictc datos bandeja pricipal. */
	private Collection<TramiteCantidad> ictc_datosBandejaPricipal;

	/** Propiedad iccr cuenta cupos remote. */
	@EJB
	private CuentaCuposRemote iccr_cuentaCuposRemote;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad imsba archivos cargados. */
	private Map<String, byte[]> imsba_archivosCargados;

	/** Propiedad idsc documento constancia. */
	private StreamedContent isc_documentoConstancia;

	/** Propiedad is id cuenta cupo. */
	private String is_idCuentaCupo;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is nir consulta bandeja. */
	private String is_nirConsultaBandeja;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is subproceso seleccionado. */
	private String is_subprocesoSeleccionado;

	/** Propiedad is turno consulta bandeja. */
	private String is_turnoConsultaBandeja;

	/** Propiedad ib aprobar solicitud. */
	private boolean ib_aprobarSolicitud;

	/** Propiedad ib solicitud cancelacion. */
	private boolean ib_solicitudCancelacion;

	/** Propiedad ib solicitud modificacion. */
	private boolean ib_solicitudModificacion;

	/** Propiedad ii total bandeja. */
	private int ii_totalBandeja;

	/**
	 * Limpia las variables de la sesión.
	 */
	public void clean()
	{
		setNirConsultaBandeja(null);
		setTurnoConsultaBandeja(null);
		setDatosBandejaPricipal(null);
		setDatosBandejaDetalle(null);
		setSubprocesoSeleccionado(null);
		setObservaciones(null);
		setIdTurno(null);
		setIdSolicitud(null);
		setIdCuentaCupo(null);
		setIdTurnoHistoria(null);
		setArchivosCargados(null);
		setDocumentosCargados(null);
		setNuevoValorMaximo(null);
		setNuevoValorMinimo(null);
		setDatosAprobacion(null);
		setAprobarSolicitud(false);
		setSolicitudCancelacion(false);
		setTotalBandeja(0);
		setSolicitudModificacion(false);
		setDocumentoConstancia(null);
	}

	/**
	 * Obtiene el valor de documento constancia.
	 *
	 * @return el valor de documento constancia
	 */
	public StreamedContent getDocumentoConstancia()
	{
		return isc_documentoConstancia;
	}

	/**
	 * Cambia el valor de documento constancia.
	 *
	 * @param asc_dsc el nuevo valor de documento constancia
	 */
	public void setDocumentoConstancia(StreamedContent asc_dsc)
	{
		isc_documentoConstancia = asc_dsc;
	}

	/**
	 * Obtiene el valor de ib_solicitudModificacion.
	 *
	 * @return el valor de ib_solicitudModificacion
	 */
	public boolean isSolicitudModificacion()
	{
		return ib_solicitudModificacion;
	}

	/**
	 * Cambia el valor de ib_solicitudModificacion.
	 *
	 * @param ab_b el valor de ib_solicitudModificacion
	 */
	public void setSolicitudModificacion(boolean ab_b)
	{
		ib_solicitudModificacion = ab_b;
	}

	/**
	 * Obtiene el valor de documentos cargados.
	 *
	 * @return el valor de documentos cargados
	 */
	public Collection<StreamedContent> getDocumentosCargados()
	{
		if(icsc_documentosCargados == null)
			icsc_documentosCargados = new LinkedList<StreamedContent>();

		return icsc_documentosCargados;
	}

	/**
	 * Cambia el valor de documentos cargados.
	 *
	 * @param acsc_csc el valor de documentos cargados
	 */
	public void setDocumentosCargados(Collection<StreamedContent> acsc_csc)
	{
		icsc_documentosCargados = acsc_csc;
	}

	/**
	 * Obtiene el valor de datos aprobacion.
	 *
	 * @return el valor de datos aprobacion
	 */
	public AprobacionCuentaCupos getDatosAprobacion()
	{
		return iacc_datosAprobacion;
	}

	/**
	 * Cambia el valor de datos aprobacion.
	 *
	 * @param aacc_acc el valor de datos aprobacion
	 */
	public void setDatosAprobacion(AprobacionCuentaCupos aacc_acc)
	{
		iacc_datosAprobacion = aacc_acc;
	}

	/**
	 * Obtiene el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Cambia el valor de id turno historia.
	 *
	 * @param as_s el valor de id turno historia
	 */
	public void setIdTurnoHistoria(Long as_s)
	{
		il_idTurnoHistoria = as_s;
	}

	/**
	 * Obtiene el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Cambia el valor de id turno.
	 *
	 * @param as_s el valor de id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Obtiene el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Cambia el valor de id solicitud.
	 *
	 * @param as_s el valor de id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Obtiene el valor de nuevo valor minimo.
	 *
	 * @return el valor de nuevo valor minimo
	 */
	public BigDecimal getNuevoValorMinimo()
	{
		return ibd_nuevoValorMinimo;
	}

	/**
	 * Cambia el valor de nuevo valor minimo.
	 *
	 * @param abd_bd el valor de nuevo valor minimo
	 */
	public void setNuevoValorMinimo(BigDecimal abd_bd)
	{
		ibd_nuevoValorMinimo = abd_bd;
	}

	/**
	 * Gets el valor de nuevo valor maximo.
	 *
	 * @return el valor de nuevo valor maximo
	 */
	public BigDecimal getNuevoValorMaximo()
	{
		return ibd_nuevoValorMaximo;
	}

	/**
	 * Sets el valor de nuevo valor maximo.
	 *
	 * @param abd_bd el valor de nuevo valor maximo
	 */
	public void setNuevoValorMaximo(BigDecimal abd_bd)
	{
		ibd_nuevoValorMaximo = abd_bd;
	}

	/**
	 * Verifica si es aprobar solicitud.
	 *
	 * @return true, si es aprobar solicitud
	 */
	public boolean isAprobarSolicitud()
	{
		return ib_aprobarSolicitud;
	}

	/**
	 * Cambia el valor de aprobar solicitud.
	 *
	 * @param ab_b el valor de aprobar solicitud
	 */
	public void setAprobarSolicitud(boolean ab_b)
	{
		ib_aprobarSolicitud = ab_b;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de application
	 */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Obtiene el valor de nir consulta bandeja.
	 *
	 * @return el valor de nir consulta bandeja
	 */
	public String getNirConsultaBandeja()
	{
		return is_nirConsultaBandeja;
	}

	/**
	 * Cambia el valor de nir consulta bandeja.
	 *
	 * @param as_s el nuevo valor de nir consulta bandeja
	 */
	public void setNirConsultaBandeja(String as_s)
	{
		is_nirConsultaBandeja = as_s;
	}

	/**
	 * Obtiene el valor de turno consulta bandeja.
	 *
	 * @return el valor de turno consulta bandeja
	 */
	public String getTurnoConsultaBandeja()
	{
		return is_turnoConsultaBandeja;
	}

	/**
	 * Cambia el valor de turno consulta bandeja.
	 *
	 * @param as_s el nuevo valor de turno consulta bandeja
	 */
	public void setTurnoConsultaBandeja(String as_s)
	{
		is_turnoConsultaBandeja = as_s;
	}

	/**
	 * Obtiene el valor de datos bandeja pricipal.
	 *
	 * @return el valor de datos bandeja pricipal
	 */
	public Collection<TramiteCantidad> getDatosBandejaPricipal()
	{
		return ictc_datosBandejaPricipal;
	}

	/**
	 * Cambia el valor de datos bandeja pricipal.
	 *
	 * @param actc_ctc el nuevo valor de datos bandeja pricipal
	 */
	public void setDatosBandejaPricipal(Collection<TramiteCantidad> actc_ctc)
	{
		ictc_datosBandejaPricipal = actc_ctc;
	}

	/**
	 * Obtiene el valor de datos bandeja detalle.
	 *
	 * @return el valor de datos bandeja detalle
	 */
	public Collection<TramiteCantidad> getDatosBandejaDetalle()
	{
		return ictc_datosBandejaDetalle;
	}

	/**
	 * Cambia el valor de datos bandeja detalle.
	 *
	 * @param actc_ctc el nuevo valor de datos bandeja detalle
	 */
	public void setDatosBandejaDetalle(Collection<TramiteCantidad> actc_ctc)
	{
		ictc_datosBandejaDetalle = actc_ctc;
	}

	/**
	 * Obtiene el valor de archivos cargados.
	 *
	 * @return el valor de archivos cargados
	 */
	public Map<String, byte[]> getArchivosCargados()
	{
		if(imsba_archivosCargados == null)
			imsba_archivosCargados = new LinkedHashMap<String, byte[]>();

		return imsba_archivosCargados;
	}

	/**
	 * Cambia el valor de archivos cargados.
	 *
	 * @param amsba_msba el nuevo valor de amsba msba
	 */
	public void setArchivosCargados(Map<String, byte[]> amsba_msba)
	{
		imsba_archivosCargados = amsba_msba;
	}

	/**
	 * Verifica si es solicitud cancelacion.
	 *
	 * @return true, si es solicitud cancelacion
	 */
	public boolean isSolicitudCancelacion()
	{
		return ib_solicitudCancelacion;
	}

	/**
	 * Cambia el valor de solicitud cancelacion.
	 *
	 * @param ab_b el nuevo valor de solicitud cancelacion
	 */
	public void setSolicitudCancelacion(boolean ab_b)
	{
		ib_solicitudCancelacion = ab_b;
	}

	/**
	 * Obtiene el valor de total bandeja.
	 *
	 * @return el valor de total bandeja
	 */
	public int getTotalBandeja()
	{
		return ii_totalBandeja;
	}

	/**
	 * Cambia el valor de total bandeja.
	 *
	 * @param ai_i el nuevo valor de total bandeja
	 */
	public void setTotalBandeja(int ai_i)
	{
		ii_totalBandeja = ai_i;
	}

	/**
	 * Obtiene el valor de subproceso seleccionado.
	 *
	 * @return el valor de subproceso seleccionado
	 */
	public String getSubprocesoSeleccionado()
	{
		return is_subprocesoSeleccionado;
	}

	/**
	 * Cambia el valor de subproceso seleccionado.
	 *
	 * @param as_s el nuevo valor de subproceso seleccionado
	 */
	public void setSubprocesoSeleccionado(String as_s)
	{
		is_subprocesoSeleccionado = as_s;
	}

	/**
	 * Obtiene el valor de observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Cambia el valor de observaciones.
	 *
	 * @param as_s el nuevo valor de observaciones
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Obtiene el valor de id cuenta cupo.
	 *
	 * @return el valor de id cuenta cupo
	 */
	public String getIdCuentaCupo()
	{
		return is_idCuentaCupo;
	}

	/**
	 * Cambia el valor de id cuenta cupo.
	 *
	 * @param as_s el valor de id cuenta cupo
	 */
	public void setIdCuentaCupo(String as_s)
	{
		is_idCuentaCupo = as_s;
	}

	/**
	 *  Valida la información ingresada por el usuario y finaliza el proceso.
	 *
	 * @return el valor de string
	 */
	public String aprobarSolicitudCuentaCupo()
	{
		String ls_return;

		ls_return = null;

		try
		{
			AprobacionCuentaCupos lacc_dataAprobacion;
			boolean               lb_aprobarSolicitud;
			String                ls_observaciones;
			FacesContext          lfc_faces;

			lacc_dataAprobacion     = new AprobacionCuentaCupos();
			lb_aprobarSolicitud     = isAprobarSolicitud();
			ls_observaciones        = getObservaciones();
			lfc_faces               = FacesContext.getCurrentInstance();

			lacc_dataAprobacion.setIdTurnoHistoria(getIdTurnoHistoria());
			lacc_dataAprobacion.setIdSolicitud(getIdSolicitud());
			lacc_dataAprobacion.setIdCuentaCupo(getIdCuentaCupo());

			if(lb_aprobarSolicitud && !isSolicitudCancelacion())
			{
				BigDecimal lbd_valorMinimo;
				BigDecimal lbd_valorMaximo;

				lbd_valorMinimo     = getNuevoValorMinimo();
				lbd_valorMaximo     = getNuevoValorMaximo();

				validateStyles(is_idForm + ":idINValorMinNuevo", lfc_faces, lbd_valorMinimo, true);

				if(!NumericUtils.isValidBigDecimal(lbd_valorMinimo, BigDecimal.ONE))
					throw new B2BException(ErrorKeys.ERROR_VALOR_MINIMO_NO_VALIDO);

				validateStyles(is_idForm + ":idINValorMaxNuevo", lfc_faces, lbd_valorMaximo, true);

				if(!NumericUtils.isValidBigDecimal(lbd_valorMaximo, BigDecimal.ONE))
					throw new B2BException(ErrorKeys.ERROR_VALOR_MAXIMO_NO_VALIDO);

				{
					int li_comparacionValores;

					li_comparacionValores = lbd_valorMinimo.compareTo(lbd_valorMaximo);

					switch(li_comparacionValores)
					{
						case 0:
							throw new B2BException(ErrorKeys.ERROR_VALOR_MIN_MAX_IGUALES);

						case 1:
							throw new B2BException(ErrorKeys.ERROR_VALOR_MINIMO_MAYOR_QUE_MAXIMO);

						default:
							break;
					}
				}

				lacc_dataAprobacion.setValorMinimo(lbd_valorMinimo);
				lacc_dataAprobacion.setValorMaximo(lbd_valorMaximo);
			}

			if(!lb_aprobarSolicitud)
			{
				validateStyles(is_idObservaciones, lfc_faces, ls_observaciones, true);

				if(!StringUtils.isValidString(ls_observaciones))
					throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);
				else if(ls_observaciones.length() > IdentificadoresCommon.LIMITE_CARACTERES_OBSERVACIONES)
				{
					validateStyles(is_idObservaciones, lfc_faces, IdentificadoresCommon.DATO_INVALIDO, true);

					throw new B2BException(ErrorKeys.ERROR_OBSERVACIONES_TAM_4000);
				}
			}

			lacc_dataAprobacion.setObservaciones(ls_observaciones);
			lacc_dataAprobacion.setAprobar(lb_aprobarSolicitud);

			iccr_cuentaCuposRemote.aprobarSolicitudCuentaCupo(
			    lacc_dataAprobacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			ls_return = NavegacionCommon.CUENTA_CUPOS;

			clean();
			obtenerBandejaEntrada();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("aprobarSolicitudCuentaCupo", lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Genera el documento con la constancia del proceso.
	 */
	public void exportarArchivoConstancia()
	{
		try
		{
			byte[]     lba_documento;
			BigDecimal lbd_valorMinimo;
			BigDecimal lbd_valorMaximo;

			lbd_valorMinimo     = getNuevoValorMinimo();
			lbd_valorMaximo     = getNuevoValorMaximo();

			lba_documento = iccr_cuentaCuposRemote.generarConstanciaDecisionAprobacion(
				    getIdSolicitud(), getIdCuentaCupo(), getObservaciones(), isAprobarSolicitud(),
				    NumericUtils.isValidBigDecimal(lbd_valorMinimo, BigDecimal.ONE) ? lbd_valorMinimo : null,
				    NumericUtils.isValidBigDecimal(lbd_valorMaximo, BigDecimal.ONE) ? lbd_valorMaximo : null,
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			{
				DefaultStreamedContent ldsc_documento;

				ldsc_documento = generarArchivosDescarga(
					    lba_documento, TipoContenidoCommon.PDF,
					    TipoArchivoCommon.REPORTE_MOVIMIENTOS + ExtensionCommon.PDF_MAYUS_PUNTO
					);

				if(ldsc_documento == null)
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);

				setDocumentoConstancia(ldsc_documento);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("exportarArchivoConstancia", lb2be_e);
		}
	}

	/**
	 * Obtener bandeja entrada.
	 *
	 * @throws B2BException el valor de b 2 B exception
	 */
	public void obtenerBandejaEntrada()
	    throws B2BException
	{
		try
		{
			String                      ls_idUsuario;
			TramiteCantidad             ltc_paramtros;
			int                         li_cantidadTramites;
			Collection<TramiteCantidad> lctc_tramites;

			ls_idUsuario            = getUserId();
			ltc_paramtros           = new TramiteCantidad();
			li_cantidadTramites     = 0;

			ltc_paramtros.setUsuario(ls_idUsuario);
			ltc_paramtros.setNir(getNirConsultaBandeja());
			ltc_paramtros.setIdTurno(getTurnoConsultaBandeja());

			lctc_tramites = iccr_cuentaCuposRemote.obtenerBandejaEntrada(
				    ltc_paramtros, ls_idUsuario, getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lctc_tramites))
			{
				for(TramiteCantidad ltc_tramite : lctc_tramites)
				{
					if(ltc_tramite != null)
					{
						Integer li_cantidad;

						li_cantidad = ltc_tramite.getCantidad();

						if(NumericUtils.isValidInteger(li_cantidad))
							li_cantidadTramites += li_cantidad.intValue();
					}
				}
			}

			setDatosBandejaPricipal(lctc_tramites);
			setTotalBandeja(li_cantidadTramites);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("obtenerBandejaEntrada", lb2be_e);
		}
	}

	/**
	 * Return pages.
	 *
	 * @return el valor de string
	 */
	public String returnPages()
	{
		String ls_return;

		ls_return = null;

		try
		{
			String as_idSubproceso;

			as_idSubproceso = FacesUtils.getStringFacesParameter("idSubproceso");

			if(!StringUtils.isValidString(as_idSubproceso))
				throw new B2BException(ErrorKeys.ERROR_SUBPROCESO_NO_VALIDO);

			setSubprocesoSeleccionado(as_idSubproceso);

			String          ls_idUsuario;
			TramiteCantidad ltc_parametros;

			ls_idUsuario       = getUserId();
			ltc_parametros     = new TramiteCantidad();

			ltc_parametros.setUsuario(ls_idUsuario);
			ltc_parametros.setNir(getNirConsultaBandeja());
			ltc_parametros.setIdTurno(getTurnoConsultaBandeja());
			ltc_parametros.setIdSubProceso(as_idSubproceso);

			setSolicitudModificacion(as_idSubproceso.equals(ProcesoCommon.ID_SUBPROCESO_2));
			setSolicitudCancelacion(as_idSubproceso.equals(ProcesoCommon.ID_SUBPROCESO_3));

			setDatosBandejaDetalle(
			    iccr_cuentaCuposRemote.obtenerBandejaDetalle(
			        ltc_parametros, ls_idUsuario, getLocalIpAddress(), getRemoteIpAddress()
			    )
			);

			ls_return = NavegacionCommon.CUENTA_CUPOS_TRAMITES;
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("obtenerBandejaDetalle", lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Mostrar observaciones proceso anterior.
	 *
	 * @param as_observaciones el valor de as observaciones
	 */
	public void mostrarObservacionesProcesoAnterior(String as_observaciones)
	{
		if(StringUtils.isValidString(as_observaciones))
		{
			setObservaciones(as_observaciones);

			PrimeFaces.current().executeScript("PF('obsProcesoAnterior').show();");
			PrimeFaces.current().ajax().update("fDialogo:id_observacionesProcesoAnterior");
		}
		else
			addMessage(MessagesKeys.SIN_OBSERVACIONES_PROCESO_ANTERIOR);
	}

	/**
	 * Abrir detalle turno.
	 *
	 * @param atc_tramite el valor de atc tramite
	 * @return el valor de string
	 */
	public String abrirDetalleTurno(TramiteCantidad atc_tramite)
	{
		String ls_return;

		ls_return = null;

		try
		{
			if(atc_tramite != null)
			{
				String ls_idTurno;

				ls_idTurno = atc_tramite.getIdTurno();

				{
					AprobacionCuentaCupos lacc_datos;

					lacc_datos = iccr_cuentaCuposRemote.obtenerDatosTurno(
						    ls_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lacc_datos == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_INFORMACION_ASOCIADA_AL_TURNO);

					setNuevoValorMinimo(lacc_datos.getValorMinimoModificacion());
					setNuevoValorMaximo(lacc_datos.getValorMaximoModificacion());
					setIdCuentaCupo(lacc_datos.getIdCuentaCupo());
					setIdSolicitud(lacc_datos.getIdSolicitud());
					setDatosAprobacion(lacc_datos);
					setAprobarSolicitud(true);
				}

				setIdTurnoHistoria(NumericUtils.getLongWrapper(atc_tramite.getIdTurnoHistoria()));
				setIdTurno(ls_idTurno);

				ls_return = NavegacionCommon.CUENTA_CUPOS_DETALLE;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("abrirDetalleTurno", lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Cargar documento pdf.
	 *
	 * @param afue_event de afue event
	 */
	public void cargarDocumentoPdf(FileUploadEvent afue_event)
	{
		cargarDocumentoPdf(afue_event, is_messageIdGrowl);
	}

	/**
	 * Procesa el cargue de archivos pdf para el soporte de rechazar una solicitud.
	 *
	 * @param afue_event Objeto contenedor del arreglo de bytes correspondiente al archivo cargado
	 * @param as_messageIdGrowl de as message id growl
	 */
	public void cargarDocumentoPdf(FileUploadEvent afue_event, String as_messageIdGrowl)
	{
		try
		{
			String       ls_mensaje;
			UploadedFile luf_file;

			ls_mensaje     = null;
			luf_file       = afue_event.getFile();

			if((luf_file != null) && StringUtils.isValidString(as_messageIdGrowl))
			{
				if(luf_file.getSize() <= ii_tamanioArchivo5MB)
				{
					byte[] lba_pdfFile;

					lba_pdfFile = luf_file.getContents();

					if(
					    (lba_pdfFile != null) && StringUtils.isValidString(luf_file.getFileName())
						    && luf_file.getFileName().toUpperCase().endsWith(ExtensionCommon.PDF_MAYUS_PUNTO)
					)
					{
						boolean lb_isEncrypted;
						String  ls_nombreArchivo;

						lb_isEncrypted       = false;
						ls_nombreArchivo     = luf_file.getFileName();

						if(lba_pdfFile.length < 1)
							throw new B2BException(ErrorKeys.ARCHIVO_DANADO);

						{
							String ls_pdfContent;
							int    li_lastTrailerIndex;

							ls_pdfContent           = new String(lba_pdfFile);
							li_lastTrailerIndex     = ls_pdfContent.lastIndexOf("trailer");

							if((li_lastTrailerIndex >= 0) && (li_lastTrailerIndex < ls_pdfContent.length()))
							{
								String ls_partEncrypted;
								int    ls_firstEOFIndex;
								String ls_trailer;

								ls_partEncrypted     = ls_pdfContent.substring(
									    li_lastTrailerIndex, ls_pdfContent.length()
									);
								ls_firstEOFIndex     = ls_partEncrypted.indexOf("%%EOF");
								ls_trailer           = ls_partEncrypted.substring(0, ls_firstEOFIndex);

								if(ls_trailer.contains("/Encrypt"))
									lb_isEncrypted = true;
							}
						}

						if(lb_isEncrypted)
							throw new B2BException(ErrorKeys.ARCHIVO_PROTEGIDO);

						{
							Collection<StreamedContent> lcsc_documentosCargados;

							lcsc_documentosCargados = getDocumentosCargados();

							if(lcsc_documentosCargados != null)
							{
								Optional<StreamedContent> losc_documento;

								//FORMATO Comentar lambda antes de formatear
								losc_documento = lcsc_documentosCargados.stream()
										.filter(lsc_documento -> StringUtils.getStringNotNull(lsc_documento.getName()).equals(ls_nombreArchivo))
										.findFirst();
								if(losc_documento.isPresent())
								{
									Object[] loa_args;

									loa_args     = new String[1];

									loa_args[0] = ls_nombreArchivo;

									throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_YA_CARGADO, loa_args);
								}

								{
									StreamedContent lsc_uploadedFile;

									InputStream     lis_stream = new ByteArrayInputStream(lba_pdfFile);

									lsc_uploadedFile = new DefaultStreamedContent(
										    lis_stream, luf_file.getContentType(), ls_nombreArchivo
										);

									lcsc_documentosCargados.add(lsc_uploadedFile);
								}
							}
						}

						{
							Map<String, byte[]> lmsba_archivosCargados;

							lmsba_archivosCargados = getArchivosCargados();

							if(lmsba_archivosCargados != null)
								lmsba_archivosCargados.put(ls_nombreArchivo, lba_pdfFile);
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_TAMANIO_ARCHIVO_PDF);
			}
			else
				ls_mensaje = "No se encontro un Archivo para procesar.";

			if(!StringUtils.isValidString(ls_mensaje))
				ls_mensaje = "Procesamiento de archivo terminado.";

			addMessage("I", ls_mensaje);
			PrimeFaces.current().ajax().update(as_messageIdGrowl);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("cargarDocumentoPdf", lb2be_e);
			PrimeFaces.current().ajax().update(as_messageIdGrowl);
		}
		catch(Exception lb2be_e)
		{
			addMessage("E", lb2be_e.getMessage());
			clh_LOGGER.error("cargarDocumentoPdf", lb2be_e);
			PrimeFaces.current().ajax().update(as_messageIdGrowl);
		}
	}

	/**
	 * Elimina un documento agregado coo soporte.
	 *
	 * @param asc_documento Objeto contenedor del documento a eliminar
	 */
	public void eliminarDocumentoCargado(StreamedContent asc_documento)
	{
		try
		{
			if(asc_documento == null)
				throw new B2BException(ErrorKeys.ERROR_SIN_DOCUMENTO_PARA_ELIMINAR);

			String                      ls_nombre;
			Collection<StreamedContent> lcsc_documentosTmp;

			ls_nombre              = StringUtils.getStringNotNull(asc_documento.getName());
			lcsc_documentosTmp     = new LinkedList<StreamedContent>();

			{
				Collection<StreamedContent> lcsc_documentosCargados;

				lcsc_documentosCargados = getDocumentosCargados();

				//FORMATO Comentar lambda antes de formatear
				lcsc_documentosCargados.stream()
					.filter(lsc_documento -> !StringUtils.getStringNotNull(lsc_documento.getName()).equals(ls_nombre))
					.forEach(lsc_documento -> lcsc_documentosTmp.add(lsc_documento));
			}

			{
				Map<String, byte[]> lmsba_archivosCargados;

				lmsba_archivosCargados = getArchivosCargados();

				if(CollectionUtils.isValidCollection(lmsba_archivosCargados))
					lmsba_archivosCargados.remove(ls_nombre);
			}

			setDocumentosCargados(lcsc_documentosTmp);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("eliminarDocumentoCargado", lb2be_e);
		}
	}

	/**
	 * Consulta SGD.
	 */
	public void consultaSGD()
	{
		try
		{
			AprobacionCuentaCupos lacc_aprobacion;

			lacc_aprobacion = getDatosAprobacion();

			if(lacc_aprobacion != null)
			{
				DocumentoOWCC ldowcc_datosBusqueda;

				ldowcc_datosBusqueda = new DocumentoOWCC(lacc_aprobacion.getNir(), true);

				accionSGD(ldowcc_datosBusqueda, NavegacionCommon.CUENTA_CUPOS_DETALLE);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("consultaSGD", lb2be_e);
		}
		catch(IOException lioe_e)
		{
			addMessage(new B2BException(lioe_e.getLocalizedMessage()));
			clh_LOGGER.error("consultaSGD", lioe_e);
		}
	}
}
