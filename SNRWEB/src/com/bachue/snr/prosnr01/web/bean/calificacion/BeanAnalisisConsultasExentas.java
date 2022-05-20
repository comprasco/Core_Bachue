package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.generarDocumentos.GenerarDocumentosRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import com.bachue.snr.prosnr01.web.bean.consulta.reportes.BeanConsultaIndices;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.StreamedContent;

import java.io.Serializable;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase encargada de tratar los datos de sesión de análisis consultas exentas.
 *
 * @author hcastaneda
 *
 */
@SessionScoped
@ManagedBean(name = "beanAnalisisConsultasExentas")
public class BeanAnalisisConsultasExentas extends BeanConsultaIndices implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -674874857983216066L;

	/** Constante cs_ID_FORM. */
	private static final String cs_ID_FORM = "fAnalisisConsultasExentasId";

	/** Constante cs_ID_GROWL. */
	private static final String cs_ID_GROWL = cs_ID_FORM + ":globalMsg";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanConsultaIndices.class);

	/** Propiedad igdr generar documentos remote. */
	@EJB
	private GenerarDocumentosRemote igdr_generarDocumentosRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_RegistroRemote;

	/** Propiedad isc pdf consulta descarga. */
	private StreamedContent isc_pdfConsultaDescarga;

	/** Propiedad ith turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/** Propiedad iba pdf consulta. */
	private byte[] iba_pdfConsulta;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de pdf consulta.
	 *
	 * @param aba_ba asigna el valor a la propiedad pdf consulta
	 */
	public void setPdfConsulta(byte[] aba_ba)
	{
		iba_pdfConsulta = aba_ba;
	}

	/**
	 * Retorna el valor de pdf consulta.
	 *
	 * @return el valor de pdf consulta
	 */
	public byte[] getPdfConsulta()
	{
		return iba_pdfConsulta;
	}

	/**
	 * Modifica el valor de pdf consulta descarga.
	 *
	 * @param asc_sc asigna el valor a la propiedad pdf consulta descarga
	 */
	public void setPdfConsultaDescarga(StreamedContent asc_sc)
	{
		isc_pdfConsultaDescarga = asc_sc;
	}

	/**
	 * Retorna el valor de pdf consulta descarga.
	 *
	 * @return el valor de pdf consulta descarga
	 */
	public StreamedContent getPdfConsultaDescarga()
	{
		return isc_pdfConsultaDescarga;
	}

	/**
	 * Modifica el valor de turno historia.
	 *
	 * @param ath_th asigna el valor a la propiedad turno historia
	 */
	public void setTurnoHistoria(TurnoHistoria ath_th)
	{
		ith_turnoHistoria = ath_th;
	}

	/**
	 * Retorna el valor de turno historia.
	 *
	 * @return el valor de turno historia
	 */
	public TurnoHistoria getTurnoHistoria()
	{
		if(ith_turnoHistoria == null)
			ith_turnoHistoria = new TurnoHistoria();

		return ith_turnoHistoria;
	}

	/**
	 * Accion consulta SGD.
	 */
	public void accionConsultaSGD()
	{
		try
		{
			DocumentoOWCC ldo_documento;

			ldo_documento = new DocumentoOWCC(getIdTurno(), false);

			accionSGD(ldo_documento, NavegacionCommon.ANALISIS_CONSULTAS_EXENTAS, false);
		}
		catch(Exception le_e)
		{
			B2BException lb2be_e;

			lb2be_e = new B2BException("IOException", le_e);

			addMessage(lb2be_e);
			clh_LOGGER.error("accionConsultaSGD", lb2be_e);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			PrimeFaces.current().ajax().update("fAnalisisConsultasExentasId:globalMsg");
		}
	}

	/**
	 * Metodo encargado de agregar criterios al detalle de criterios.
	 *
	 * @param acc_camposConsulta
	 *            Argumento de tipo CamposConsulta que contiene los criterios a
	 *            agregar.
	 */
	public void agregarCriterioAnalisis(CamposConsulta acc_camposConsulta)
	{
		try
		{
			if(acc_camposConsulta != null)
				agregarCriterio(acc_camposConsulta);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarCriterioAnalisis", lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_FORM);
		}
	}

	/**
	 * Metodo encargado de cargar excel para agregar valoreas a detalle criterio
	 * busqueda.
	 *
	 * @param afue_event
	 *            Argumento de tipo FileUploadEvent que contiene el archivo cargado
	 *            y los datos necesarios para realizar la inserción de detalle
	 *            criterio busqueda.
	 * @return Variable de tipo String para volver a la pantalla sin afectar valores
	 *         precargados.
	 */
	public String cargarExcelCamposCriteriosAnalisis(FileUploadEvent afue_event)
	{
		if(afue_event != null)
		{
			try
			{
				cargarExcelCamposCriterios(afue_event);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("cargarExcelCamposCriteriosAnalisis", lb2be_e);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("cargarExcelCamposCriteriosAnalisis", le_e);
			}
			finally
			{
				actualizarComponente(cs_ID_GROWL);
			}
		}

		return null;
	}

	/**
	 * Descargar pdf.
	 */
	public void descargarPdf()
	{
		byte[] lba_pdfConsulta;

		lba_pdfConsulta = getPdfConsulta();

		if(lba_pdfConsulta != null)
			setPdfConsultaDescarga(
			    generarArchivosDescarga(
			        lba_pdfConsulta, TipoContenidoCommon.PDF,
			        ConstanteCommon.NOMBRE_ARCHIVO_PDF_CONSULTA + ExtensionCommon.PDF_PUNTO
			    )
			);
	}

	/**
	 * Método encargado de generar un <code>DefaultStreamedContent</code> con la
	 * imagen resultante de la consulta en la BD.
	 *
	 * @param acc_camposConsulta            <code>CamposConsulta</code> con el cual se realizará la búsqueda
	 *            de la constante.
	 */
	public void descargarPlantillaCargue(CamposConsulta acc_camposConsulta)
	{
		try
		{
			if(acc_camposConsulta != null)
			{
				Constantes lc_constante;

				lc_constante = irr_RegistroRemote.descargarPlantillaCargue(
					    acc_camposConsulta, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lc_constante != null)
					setImagen(
					    generarArchivosDescarga(
					        lc_constante.getImagenes(), TipoContenidoCommon.XLS_XLSX_XXLS,
					        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + IdentificadoresCommon.SIMBOLO_GUION_BAJO
					        + lc_constante.getIdConstante() + lc_constante.getTipoArchivo()
					    )
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("descargarPlantillaCargue", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de terminar el proceso y enviar el caso a aprobación.
	 *
	 * @return devuelve el valor de String
	 */
	public String enviarAEntrega()
	{
		String ls_retorno;

		ls_retorno = null;

		try
		{
			{
				Solicitud ls_solicitud;

				ls_solicitud = getSolicitud();

				if(ls_solicitud != null)
				{
					TurnoHistoria ath_turnoHistoria;
					MotivoTramite lmt_motivoTramite;

					ath_turnoHistoria     = new TurnoHistoria();
					lmt_motivoTramite     = new MotivoTramite();

					ath_turnoHistoria.setIdSolicitud(ls_solicitud.getIdSolicitud());
					ath_turnoHistoria.setIdTurno(getIdTurno());

					lmt_motivoTramite.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_41_ANALISTA_SOLICITUD_CONSULTAS_EXENTAS);

					igdr_generarDocumentosRemote.enviarAEntrega(
					    ath_turnoHistoria, lmt_motivoTramite, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
				}
			}

			limpiarAnalisisConsultasExentas();

			{
				FacesContext          lfc_context;
				BeanConsultasProcesos lbcp_bean;

				lfc_context     = FacesContext.getCurrentInstance();

				lbcp_bean = (BeanConsultasProcesos)lfc_context.getApplication()
						                                          .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONSULTAS_PROCESOS, BeanConsultasProcesos.class
						);

				lbcp_bean.setNirConsulta(null);
				lbcp_bean.setIdTurnoConsulta(null);
				lbcp_bean.accionVolver();
			}

			ls_retorno = NavegacionCommon.CONSULTAS_PROCESOS;
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("enviarAlAprobador", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}

		return ls_retorno;
	}

	/**
	 * Metodo encargado de generar los documentos de consulta.
	 */
	public void generarConsulta()
	{
		try
		{
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria = new TurnoHistoria();

			lth_turnoHistoria.setIdTurno(getIdTurno());

			setPdfConsulta(
			    igdr_generarDocumentosRemote.generarDocumentosRespuesta(
			        lth_turnoHistoria, getSolicitud(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarConsulta", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Limpiar analisis consultas exentas.
	 */
	public void limpiarAnalisisConsultasExentas()
	{
		setSolicitud(null);
		setTurnoHistoria(null);
		setCriteriosDeBusqueda(null);
		setCriteriosDeConsulta(null);
		setCriterios(null);
		setPdfConsulta(null);
	}
}
