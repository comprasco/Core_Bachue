package com.bachue.snr.prosnr01.web.bean.entrega;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.model.entrega.TramiteTurno;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.context.PrimeFacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import java.util.Map.Entry;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades de ImpresionDocumentosCorrespondencia.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 29/03/2020
 */
@SessionScoped
@ManagedBean(name = "beanDetalleImpresionDocumentosCorrespondencia")
public class BeanDetalleImpresionDocumentosCorrespondencia extends BeanDetalleEntrega implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 9106135046561971206L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanDetalleImpresionDocumentosCorrespondencia.class);

	/** Constante cs_PREGUNTA_APROBAR_RELACIONES_DIALOG. */
	private static final String cs_PREGUNTA_APROBAR_RELACIONES_DIALOG = "preguntaAprobarRelaciones";

	/** Propiedad ilhm documentos generados. */
	private Map<String, byte[]> ilhm_documentosGenerados;

	/** Propiedad isc zip documentos relacion. */
	private StreamedContent isc_zipDocumentosRelacion;

	/** Propiedad is observaciones temporales. */
	private String is_observacionesTemporales;

	/** Propiedad is relaciones creadas string. */
	private String is_relacionesCreadasString;

	/**
	 * Sets the documentos generados.
	 *
	 * @param alhm_lhm correspondiente al valor del tipo de objeto Map<String,byte[]>
	 */
	public void setDocumentosGenerados(Map<String, byte[]> alhm_lhm)
	{
		ilhm_documentosGenerados = alhm_lhm;
	}

	/**
	 * Retorna el valor de documentos generados.
	 *
	 * @return el valor de documentos generados
	 */
	public Map<String, byte[]> getDocumentosGenerados()
	{
		return ilhm_documentosGenerados;
	}

	/**
	 * Modifica el valor de observaciones temporales.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones temporales
	 */
	public void setObservacionesTemporales(String as_s)
	{
		is_observacionesTemporales = as_s;
	}

	/**
	 * Retorna el valor de observaciones temporales.
	 *
	 * @return el valor de observaciones temporales
	 */
	public String getObservacionesTemporales()
	{
		return is_observacionesTemporales;
	}

	/**
	 * Modifica el valor de relaciones creadas string.
	 *
	 * @param as_s asigna el valor a la propiedad relaciones creadas string
	 */
	public void setRelacionesCreadasString(String as_s)
	{
		is_relacionesCreadasString = as_s;
	}

	/**
	 * Retorna el valor de relaciones creadas string.
	 *
	 * @return el valor de relaciones creadas string
	 */
	public String getRelacionesCreadasString()
	{
		return is_relacionesCreadasString;
	}

	/**
	 * Modifica el valor de zip documentos relacion.
	 *
	 * @param asc_sc asigna el valor a la propiedad zip documentos relacion
	 */
	public void setZipDocumentosRelacion(StreamedContent asc_sc)
	{
		isc_zipDocumentosRelacion = asc_sc;
	}

	/**
	 * Retorna el valor de zip documentos relacion.
	 *
	 * @return el valor de zip documentos relacion
	 */
	public StreamedContent getZipDocumentosRelacion()
	{
		return isc_zipDocumentosRelacion;
	}

	/** {@inheritdoc} */
	@Override
	public String accionSalvar()
	{
		String ls_return;

		ls_return = "";

		try
		{
			if(getIdEtapa() == EtapaCommon.ID_ETAPA_ENTREGA_MEDIDAS_CAUTELARES)
			{
				Collection<TramiteTurno> lctc_tmp;
				lctc_tmp = getDatosTramiteTurno();

				if(CollectionUtils.isValidCollection(lctc_tmp))
				{
					for(TramiteTurno ltt_tramiteTurno : lctc_tmp)
					{
						if(ltt_tramiteTurno != null)
						{
							Long ll_idTurnoHistoria;
							ll_idTurnoHistoria = ltt_tramiteTurno.getIdTurnoHistoria();

							if(
							    NumericUtils.isValidLong(ll_idTurnoHistoria)
								    && (getIdTurnoHistoriaEntrega().equals(ll_idTurnoHistoria))
							)
								ltt_tramiteTurno.setGenerarRelacion(true);
						}
					}
				}

				accionVolverDetalle(true);
			}
			else
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = new TurnoHistoria();

				{
					Solicitud ls_solicitud;

					ls_solicitud = getSolicitud();

					if(ls_solicitud != null)
						lth_turnoHistoria.setIdSolicitud(ls_solicitud.getIdSolicitud());
				}

				lth_turnoHistoria.setIdTurno(getIdTurno());
				lth_turnoHistoria.setIdEtapa(NumericUtils.getBigDecimal(getIdEtapa()));

				ier_entregaRemote.salvarImpresionDocumentosCorrespondencia(
				    lth_turnoHistoria, getRemoteIpAddress(), getLocalIpAddress(), getUserId()
				);

				ls_return = NavegacionCommon.PRINCIPAL;
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("accionSalvar", lb2be_e);
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	@Override
	public String accionVolver()
	    throws B2BException
	{
		String ls_return;
		ls_return = accionVolver(false);

		return ls_return;
	}

	/**
	 * Método encargado de preparar la pantalla para realizar la acción de volver.
	 *
	 * @param ab_bool Variable de tipo <code>String</code> que contiene la regla de navegación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String accionVolver(boolean ab_bool)
	    throws B2BException
	{
		String                                 ls_return;
		BeanImpresionDocumentosCorrespondencia lbidc_bean;

		ls_return      = NavegacionCommon.IMPRESION_DOCUMENTOS_CORRESPONDENCIA;
		lbidc_bean     = (BeanImpresionDocumentosCorrespondencia)FacesUtils.obtenerInstanciaBean(
			    BeanImpresionDocumentosCorrespondencia.class, BeanNameCommon.BEAN_IMPRESION_DOCUMENTOS_CORRESPONDENCIA
			);

		if(lbidc_bean != null)
		{
			try
			{
				lbidc_bean.generarDatosTramiteCantidad();
				clear(false);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("accionVolver", lb2be_e);
				ls_return = null;
				addMessage(lb2be_e);
			}
		}

		if(ab_bool)
		{
			PrimeFaces.current().executeScript("PF('confirmacionRelaciones').hide();");

			try
			{
				PrimeFacesContext.getCurrentInstance().getExternalContext()
					                 .redirect("impresionDocumentosEntregaCorrespondencia.jsf");
			}
			catch(IOException ex)
			{
				Logger.getLogger(BeanDetalleImpresionDocumentosCorrespondencia.class.getName())
					      .log(Level.SEVERE, null, ex);

				ls_return = null;
			}
		}

		return ls_return;
	}

	/**
	 * Hace un reset a todas las variables implicadas en el proceso de entrega.
	 *
	 * @param ab_parcial de ab parcial
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void clear(boolean ab_parcial)
	    throws B2BException
	{
		super.clear(ab_parcial);

		setDocumentosGenerados(null);
		setZipDocumentosRelacion(null);
		setObservacionesTemporales(null);
		setRelacionesCreadasString(null);
	}

	/**
	 * Maneja la descarga del archivo ZIP con los documentos de relacion.
	 */
	public void descargarArchivoZip()
	{
		try
		{
			byte[]      lba_zipFinal;
			InputStream lis_stream;

			lba_zipFinal = ier_entregaRemote.generarArchivoZipEntregaRelacion(getDocumentosGenerados());

			if(lba_zipFinal != null)
			{
				lis_stream = new ByteArrayInputStream(lba_zipFinal);

				setZipDocumentosRelacion(
				    new DefaultStreamedContent(lis_stream, TipoContenidoCommon.ZIP, "APROBACION_RELACION.zip")
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método que genera zip que contiene relaciones en formato pdf.
	 *
	 * @param ab_firmar bandera para indicar si el proceso es de firma
	 * @throws Exception Señala que se ha producido una excepción
	 */
	public void generarDocumentoRelacionAprobacion(boolean ab_firmar)
	    throws Exception
	{
		PrimeFaces lpf_faces;
		lpf_faces = PrimeFaces.current();

		try
		{
			boolean lb_procesoTerminado;
			boolean lb_validar;
			Long    ll_idTurnoHistoria;
			String  ls_observacionesTemp;

			lb_procesoTerminado      = false;
			lb_validar               = false;
			ll_idTurnoHistoria       = null;
			ls_observacionesTemp     = null;

			setObservacionesTemporales(null);

			Collection<TramiteTurno> lctt_tramitesTurno;

			lctt_tramitesTurno = getDatosTramiteTurno();

			if(CollectionUtils.isValidCollection(lctt_tramitesTurno))
			{
				Iterator<TramiteTurno> lia_iterator;

				lia_iterator = lctt_tramitesTurno.iterator();

				while(lia_iterator.hasNext() && !lb_validar)
				{
					TramiteTurno ltt_tramiteTurno;

					ltt_tramiteTurno = lia_iterator.next();

					if(ltt_tramiteTurno != null)
					{
						Long ll_idTurnoHistoriaTemp;

						ll_idTurnoHistoriaTemp = ltt_tramiteTurno.getIdTurnoHistoria();

						if(NumericUtils.isValidLong(ll_idTurnoHistoriaTemp))
						{
							ll_idTurnoHistoria     = ll_idTurnoHistoriaTemp;
							lb_validar             = true;
						}
					}
				}
			}

			if(lb_validar)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = new TurnoHistoria();

				lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

				lth_turnoHistoria = irr_referenceRemote.findTurnoHistoriaByid(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					String ls_etadoActividad;

					ls_etadoActividad = lth_turnoHistoria.getEstadoActividad();

					if(StringUtils.isValidString(ls_etadoActividad))
					{
						lb_procesoTerminado      = ls_etadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA);
						ls_observacionesTemp     = lth_turnoHistoria.getObservaciones();
					}
				}
			}

			if(lb_procesoTerminado)
			{
				setObservacionesTemporales(ls_observacionesTemp);

				lpf_faces.executeScript("PF('dlgSuspension').show();");
				lpf_faces.ajax().update("fDialogSuspension");
			}
			else
			{
				Map<String, byte[]> llhm_docs;

				llhm_docs = ier_entregaRemote.generarDocumentoRelacionEntrega(
					    lctt_tramitesTurno, ab_firmar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(llhm_docs))
				{
					byte[]                          lba_zipFinal;
					InputStream                     lis_stream;
					Iterator<Entry<String, byte[]>> liesb_iterador;
					StringBuilder                   lsb_relacionesString;

					liesb_iterador           = llhm_docs.entrySet().iterator();
					lsb_relacionesString     = new StringBuilder();

					lsb_relacionesString.append(" ");

					while(liesb_iterador.hasNext())
					{
						Entry<String, byte[]> le_objeto;

						le_objeto = liesb_iterador.next();

						if(le_objeto != null)
						{
							String ls_key;

							ls_key = le_objeto.getKey();

							if(StringUtils.isValidString(ls_key))
							{
								lsb_relacionesString.append(ls_key);

								if(liesb_iterador.hasNext())
									lsb_relacionesString.append(", ");
							}
						}
					}

					lba_zipFinal     = ier_entregaRemote.generarArchivoZipEntregaRelacion(llhm_docs);
					lis_stream       = new ByteArrayInputStream(lba_zipFinal);

					setZipDocumentosRelacion(
					    new DefaultStreamedContent(lis_stream, TipoContenidoCommon.ZIP, "APROBACION_RELACION.zip")
					);

					setRelacionesCreadasString(lsb_relacionesString.toString());
				}

				setDocumentosGenerados(llhm_docs);

				if(ab_firmar)
					lpf_faces.executeScript("PF('confirmacionRelaciones').show();");
			}

			if(!ab_firmar)
				lpf_faces.executeScript("PF('" + cs_PREGUNTA_APROBAR_RELACIONES_DIALOG + "').show();");
		}
		catch(B2BException lb2be_e)
		{
			lpf_faces.executeScript("PF('" + cs_PREGUNTA_APROBAR_RELACIONES_DIALOG + "').hide();");
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para poder guardar las observaciones del
	 * proceso anterior.
	 *
	 * @param att_param objeto contenedor de observaciones a validar
	 */
	public void mostrarObservacionesProcesoAnterior(TramiteTurno att_param)
	{
		if(att_param != null)
		{
			String ls_observaciones;

			ls_observaciones     = null;

			ls_observaciones = att_param.getObservaciones();

			if(StringUtils.isValidString(ls_observaciones))
			{
				setObservaciones(ls_observaciones);

				PrimeFaces.current().executeScript("PF('obsProcesoAnterior').show();");
				PrimeFaces.current().ajax().update("fDialogo:id_observacionesProcesoAnterior");
			}
			else
				addMessage(MessagesKeys.SIN_OBSERVACIONES_PROCESO_ANTERIOR);
		}
	}
}
