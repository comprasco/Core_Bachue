package com.bachue.snr.prosnr01.web.bean.publicacionAvisosWeb;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.realizarNotificacion.RealizarNotificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.notificaciones.Notificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.ui.FechaVenceTerminosUI;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanAprobacion;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanPredioDocumentoActo;
import com.bachue.snr.prosnr01.web.bean.dispositivos.BeanDispositivos;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.PrimeFaces.Ajax;

import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.StreamedContent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase para el manejo de eventos de la pantalla de fijacion de avisos.
 *
 * @author Manuel Blanco
 */
@SessionScoped
@ManagedBean(name = "beanPublicacionAvisosWeb")
public class BeanPublicacionAvisosWeb extends BeanDispositivos
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1704904936139495042L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanPublicacionAvisosWeb.class);

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "confrontacionForm:idGrowl";

	/** Propiedad irr reference remote. */
	@EJB
	protected ReferenceRemote irr_referenceRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad acsc documentos cargados. */
	private Collection<StreamedContent> icsc_documentosPdfCargados;

	/** Propiedad icth turnos. */
	private Collection<TurnoHistoria> icth_turnos;

	/** Propiedad imsb archivos pdf cargados. */
	private Map<String, byte[]> imsb_archivosPdfCargados;

	/** Propiedad imso documentos imprimir ordenados. */
	private Map<String, Collection<DocumentosSalida>> imso_documentosImprimirOrdenados;

	/** Propiedad in info notificacion. */
	private Notificacion in_infoNotificacion;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irnr realizar notificacion remote. */
	@EJB
	private RealizarNotificacionRemote irnr_realizarNotificacionRemote;

	/** Propiedad is observaciones turno historia. */
	private String is_observacionesTurnoHistoria;

	/** Propiedad turno actual. */
	private TurnoHistoria ith_turnoActual;

	/** Propiedad turno para observaciones. */
	private TurnoHistoria ith_turnoParaObservaciones;

	/** Propiedad documento generado. */
	private boolean ib_documentoGenerado;

	/** Propiedad seleccionar todo. */
	private boolean ib_seleccionarTodo;

	/** Propiedad il id etapa. */
	private long il_idEtapa;

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
	 * Sets the archivos pdf cargados.
	 *
	 * @param amsb_amsb de amsb amsb
	 */
	public void setArchivosPdfCargados(Map<String, byte[]> amsb_amsb)
	{
		imsb_archivosPdfCargados = amsb_amsb;
	}

	/**
	 * Retorna Objeto o variable de valor archivos pdf cargados.
	 *
	 * @return Retorna el valor de la propiedad archivosPdfCargados
	 */
	public Map<String, byte[]> getArchivosPdfCargados()
	{
		if(imsb_archivosPdfCargados == null)
			imsb_archivosPdfCargados = new HashMap<String, byte[]>();

		return imsb_archivosPdfCargados;
	}

	/**
	 * Modifica el valor de DocumentoGenerado.
	 *
	 * @param ab_b de ab b
	 */
	public void setDocumentoGenerado(boolean ab_b)
	{
		ib_documentoGenerado = ab_b;
	}

	/**
	 * Valida la propiedad documento generado.
	 *
	 * @return Retorna el valor de la propiedad documentoGenerado
	 */
	public boolean isDocumentoGenerado()
	{
		return ib_documentoGenerado;
	}

	/**
	 * Sets the documentos imprimir ordenados.
	 *
	 * @param amso_mso de amso mso
	 */
	public void setDocumentosImprimirOrdenados(HashMap<String, Collection<DocumentosSalida>> amso_mso)
	{
		imso_documentosImprimirOrdenados = amso_mso;
	}

	/**
	 * Retorna Objeto o variable de valor documentos imprimir ordenados.
	 *
	 * @return Retorna el valor de la propiedad documentosImprimirOrdenados
	 */
	public Map<String, Collection<DocumentosSalida>> getDocumentosImprimirOrdenados()
	{
		if(imso_documentosImprimirOrdenados == null)
			imso_documentosImprimirOrdenados = new HashMap<String, Collection<DocumentosSalida>>();

		return imso_documentosImprimirOrdenados;
	}

	/**
	 * Modifica el valor de DocumentosPdfCargados.
	 *
	 * @param acsc_csc de acsc csc
	 */
	public void setDocumentosPdfCargados(Collection<StreamedContent> acsc_csc)
	{
		icsc_documentosPdfCargados = acsc_csc;
	}

	/**
	 * Retorna Objeto o variable de valor documentos pdf cargados.
	 *
	 * @return Retorna el valor de la propiedad documentosPdfCargados
	 */
	public Collection<StreamedContent> getDocumentosPdfCargados()
	{
		if(icsc_documentosPdfCargados == null)
			icsc_documentosPdfCargados = new LinkedList<StreamedContent>();

		return icsc_documentosPdfCargados;
	}

	/**
	 * Modifica el valor de IdEtapa.
	 *
	 * @param idEtapa de id etapa
	 */
	public void setIdEtapa(long idEtapa)
	{
		il_idEtapa = idEtapa;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa.
	 *
	 * @return Retorna el valor de la propiedad idEtapa
	 */
	public long getIdEtapa()
	{
		return il_idEtapa;
	}

	/**
	 * Modifica el valor de InfoNotificacion.
	 *
	 * @param an_n de an n
	 */
	public void setInfoNotificacion(Notificacion an_n)
	{
		in_infoNotificacion = an_n;
	}

	/**
	 * Retorna Objeto o variable de valor info notificacion.
	 *
	 * @return el valor de info notificacion
	 */
	public Notificacion getInfoNotificacion()
	{
		return in_infoNotificacion;
	}

	/**
	 * Modifica el valor de ObservacionesTurnoHistoria.
	 *
	 * @param as_s de observaciones turno historia
	 */
	public void setObservacionesTurnoHistoria(String as_s)
	{
		is_observacionesTurnoHistoria = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones turno historia.
	 *
	 * @return Retorna el valor de la propiedad observacionesTurnoHistoria
	 */
	public String getObservacionesTurnoHistoria()
	{
		return is_observacionesTurnoHistoria;
	}

	/**
	 * Modifica el valor de SeleccionarTodo.
	 *
	 * @param ab_b de ab b
	 */
	public void setSeleccionarTodo(boolean ab_b)
	{
		ib_seleccionarTodo = ab_b;
	}

	/**
	 * Valida la propiedad seleccionar todo.
	 *
	 * @return Retorna el valor de la propiedad seleccionarTodo
	 */
	public boolean isSeleccionarTodo()
	{
		return ib_seleccionarTodo;
	}

	/**
	 * Modifica el valor de TurnoActual.
	 *
	 * @param ath_th de ath th
	 */
	public void setTurnoActual(TurnoHistoria ath_th)
	{
		ith_turnoActual = ath_th;
	}

	/**
	 * Retorna Objeto o variable de valor turno actual.
	 *
	 * @return Retorna el valor de la propiedad turnoActual
	 */
	public TurnoHistoria getTurnoActual()
	{
		return ith_turnoActual;
	}

	/**
	 * Modifica el valor de TurnoParaObservaciones.
	 *
	 * @param ath_th de ath th
	 */
	public void setTurnoParaObservaciones(TurnoHistoria ath_th)
	{
		ith_turnoParaObservaciones = ath_th;
	}

	/**
	 * Retorna Objeto o variable de valor turno para observaciones.
	 *
	 * @return Retorna el valor de la propiedad turnoParaObservaciones
	 */
	public TurnoHistoria getTurnoParaObservaciones()
	{
		return ith_turnoParaObservaciones;
	}

	/**
	 * Modifica el valor de Turnos.
	 *
	 * @param acth_cth de acth cth
	 */
	public void setTurnos(Collection<TurnoHistoria> acth_cth)
	{
		icth_turnos = acth_cth;
	}

	/**
	 * Retorna Objeto o variable de valor turnos.
	 *
	 * @return Retorna el valor de la propiedad turnos
	 */
	public Collection<TurnoHistoria> getTurnos()
	{
		if(!CollectionUtils.isValidCollection(icth_turnos))
			icth_turnos = new LinkedList<TurnoHistoria>();

		return icth_turnos;
	}

	/**
	 * Agregar comentario.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void agregarComentario()
	    throws B2BException
	{
		try
		{
			String ls_observaciones;

			ls_observaciones = getObservacionesTurnoHistoria();

			if(StringUtils.isValidString(ls_observaciones))
			{
				if(ls_observaciones.length() > 4000)
					throw new B2BException(ErrorKeys.ERROR_OBSERVACIONES_TAM_4000);

				TurnoHistoria lth_turno;

				lth_turno = getTurnoParaObservaciones();

				if(lth_turno == null)
					throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);

				lth_turno.setObservaciones(ls_observaciones);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("agregarcomentario", lb2be_e);
		}
	}

		
	/**
	 * Avanzar etapa.
	 *
	 * @param ath_turnoHistoria Argumento de tipo <code>TurnoHistoria</code> que contiene el turno historia con la información a guardar.
	 * @return el valor Variable de tipo <code>String</code> con el valor de la regla de navegación.
	 */
	public String avanzarEtapa(TurnoHistoria ath_turnoHistoria)
	{
		String ls_return;

		ls_return = null;

		try
		{
			if(ath_turnoHistoria != null)
			{
//				TODO Arreglar responsable publicación
				irnr_realizarNotificacionRemote.cambiarResponsablePublicacion(ath_turnoHistoria, getUserId(), 
							getLocalIpAddress(), getRemoteIpAddress());
				
				String ls_idTurno;				
				
				ls_idTurno = ath_turnoHistoria.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					Collection<StreamedContent> lcsc_documentosCargados;

					lcsc_documentosCargados = getDocumentosPdfCargados();

					if(CollectionUtils.isValidCollection(lcsc_documentosCargados))
					{
						for(StreamedContent lsc_iterador : lcsc_documentosCargados)
						{
							if(lsc_iterador != null)
							{
								String ls_idNombre;

								ls_idNombre = lsc_iterador.getName();

								if(StringUtils.isValidString(ls_idNombre))
								{
									Map<String, byte[]> lmsb_archivosGenerados;

									lmsb_archivosGenerados = getArchivosPdfCargados();

									if(lmsb_archivosGenerados.containsKey(ls_idNombre))
									{
										Imagenes li_imagen;

										li_imagen = new Imagenes();

										byte[] imagenBlob = lmsb_archivosGenerados.get(ls_idNombre);
										li_imagen.setImagenBlob(imagenBlob);
										li_imagen.setIdTurno(ls_idTurno);
										li_imagen.setTipoArchivo(ExtensionCommon.PDF);
										//Falta tamaño
										irnr_realizarNotificacionRemote.insertarDocumentoSalida(
												ath_turnoHistoria, li_imagen, getUserId(), getLocalIpAddress(),
										    getRemoteIpAddress()
										);
									}
								}
							}
						}
					}
				}

				if(ath_turnoHistoria.isDocumentoGenerado())
				{
					ls_return = NavegacionCommon.BANDEJA_ENTRADA;
					ii_indiceImpresion = 0;
					clean();
				}
				else 
					throw new B2BException(ErrorKeys.CONTAINER_ERROR);
				
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("avanzarEtapa", lb2be_e);
			actualizarComponente("confrontacionForm:idGrowl");
		}

		return ls_return;
	}

	/**
	 * Cambia el estado de todos los casos.
	 */
	public void cambiarEstadoCasos()
	{
		Collection<TurnoHistoria> lcth_turnos;

		lcth_turnos = getTurnos();

		if(CollectionUtils.isValidCollection(lcth_turnos))
		{
			boolean lb_seleccionarTodos;

			lb_seleccionarTodos = isSeleccionarTodo();

			{
				int                     li_posicion;
				Iterator<TurnoHistoria> lith_iterador;
				Ajax                    la_ajax;

				lith_iterador     = lcth_turnos.iterator();
				la_ajax           = PrimeFaces.current().ajax();
				li_posicion       = 0;

				while(lith_iterador.hasNext())
				{
					TurnoHistoria lth_turno;

					lth_turno = lith_iterador.next();

					if(lth_turno != null)
					{
						lth_turno.setSeleccionado(lb_seleccionarTodos);
						la_ajax.update("fFijacionAvisos:idDtTurnos:" + (li_posicion++) + ":idSBCSeleccionado");
					}
				}
			}

			try
			{
				if(lb_seleccionarTodos)
					setDocumentosImprimir(
					    irnr_realizarNotificacionRemote.obtenerDocumentosAviso(
					        lcth_turnos, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					    )
					);
				else
					setDocumentosImprimir(null);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				clh_LOGGER.error("cambiarEstadoCasos", lb2be_e);
			}
		}

		ii_indiceImpresion = 0;
	}

	/**
	 * Cambiar seleccion ind.
	 *
	 * @param ath_turno de ath turno
	 */
	public void cambiarSeleccionInd(TurnoHistoria ath_turno)
	{
		try
		{
			if(ath_turno == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);

			if(ath_turno.isSeleccionado())
				obtenerTurnosSeleccionados();
			else
			{
				Collection<DocumentosSalida> lcds_documentos;

				lcds_documentos = getDocumentosImprimir();

				if(CollectionUtils.isValidCollection(lcds_documentos))
				{
					Collection<DocumentosSalida> lcds_docsFinal;

					lcds_docsFinal = new LinkedList<DocumentosSalida>();

					//FORMATO Comentar lambda antes de formatear
					lcds_documentos.stream()
						.filter(lds_documento -> lds_documento.getIdDocumentoSalida() 
								!= NumericUtils.getLong(ath_turno.getIdDocumentoSalida()))
						.forEach(lds_documento -> lcds_docsFinal.add(lds_documento));
					setDocumentosImprimir(lcds_docsFinal);
				}
			}

			ii_indiceImpresion = 0;
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("cambiarSeleccionInd", lb2be_e);
		}
	}

	/**
	 * Cargar casos.
	 */
	public void cargarCasos()
	{
		try
		{
			setTurnos(
			    irnr_realizarNotificacionRemote.obtenerCasosAvisos(
			        getIdEtapa(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("cargarCasos", lb2be_e);
		}
	}

	/**
	 * Cargar casos fijación avisos.
	 *
	 * @param as_idProceso Argumento de tipo <code>String</code> que contiene el id del proceso.
	 * @param as_subproceso Argumento de tipo <code>String</code> que contiene el subproceso.
	 * @param aa_aprobacion Argumento de tipo <code>Aprobacion</code> que contiene la información de la aprobación.
	 */
	public void cargarCasosFijacionAvisos(String as_idProceso, String as_subproceso, Aprobacion aa_aprobacion)
	{
		try
		{
			setTurnos(
			    irnr_realizarNotificacionRemote.obtenerCasosFijacionAvisos(
			        getIdEtapa(), as_idProceso, as_subproceso,aa_aprobacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("cargarCasosFijacionAvisos", lb2be_e);
		}
	}

	/**
	 * Cargar documento pdf.
	 *
	 * @param afue_event Argumento de tipo <code>afue_event</code> que contiene el documento a cargar.
	 */
	public void cargarDocumentoPdf(FileUploadEvent afue_event)
	{
		
		cargarDocumentoPDF(afue_event, is_messageIdGrowl, getDocumentosPdfCargados(), getArchivosPdfCargados());
	}

	/**
	 * Clean.
	 */
	public void clean()
	{
		setTurnos(null);
		setIdEtapa(0);
		setObservacionesTurnoHistoria(null);
		setTurnoParaObservaciones(null);
		setSeleccionarTodo(false);
		setInfoNotificacion(null);
		setDocumentosImprimir(null);
		setDocumentosImprimirOrdenados(null);
		setTurnoActual(null);
	}

	/**
	 * Consultar informacion notificacion.
	 *
	 * @param as_idTurno de as id turno
	 */
	public void consultarInformacionNotificacion(String as_idTurno)
	{
		try
		{
			Notificacion ln_notificacion;

			ln_notificacion = irnr_realizarNotificacionRemote.consultarInformacionNotificacion(
				    as_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ln_notificacion == null)
				throw new B2BException(ErrorKeys.ERROR_SIN_INFO_NOTIFICACION);

			setInfoNotificacion(ln_notificacion);

			{
				PrimeFaces lpf_faces;

				lpf_faces = PrimeFaces.current();

				lpf_faces.executeScript("PF('infoNot').show()");
				lpf_faces.ajax().update("fInfoNot:OPInformacionNotificacionDetalle");
			}

			addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("consultarInformacionNotificacion", lb2be_e);
		}
	}
	

	/**
	 * Eliminar documento.
	 *
	 * @param asc_documento de asc documento
	 */
	public void eliminarDocumento(StreamedContent asc_documento)
	{
		Collection<StreamedContent> lcsc_documentosGenerados;

		lcsc_documentosGenerados = eliminarDocumentoPDFCargado(
			    asc_documento, getDocumentosPdfCargados(), getArchivosPdfCargados()
			);

		if(lcsc_documentosGenerados != null)
			setDocumentosPdfCargados(lcsc_documentosGenerados);
	}

	/**
	 * Envio visor SGD.
	 *
	 * @param as_idDocumentoSalida de as id documento salida
	 * @return el valor de string
	 */
	public String envioVisorSGD(String as_idDocumentoSalida)
	{
		String ls_url;

		ls_url = null;

		try
		{
			ls_url = irnr_realizarNotificacionRemote.envioVisorSGD(
				    as_idDocumentoSalida, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(!StringUtils.isValidString(ls_url))
				throw new B2BException(ErrorKeys.ERROR_DOCUMENTOS_NO_ENCONTRADOS);

			PrimeFaces.current().executeScript("window.open('" + ls_url + "')");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("envioVisorSGD", lb2be_e);
		}

		return ls_url;
	}

	/**
	 * Imprime los documentos.
	 *
	 * @param as_pantalla Variable que indica desde que pantalla se ejecuta.
	 */
	public void imprimirDocumentos(String as_pantalla)
	{
		try
		{
			invocarImprimirDocumentos(false, as_pantalla);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("imprimirDocumentos", lb2be_e);
		}
	}

	/**
	 * Imprime los documentos.
	 *
	 * @param ab_boton boolean que indica si se ejecutó mediante la pantalla.
	 * @param as_pantalla Variable que indica desde que pantalla se ejecuta.
	 */
	public void imprimirDocumentos(boolean ab_boton, String as_pantalla)
	{
		try
		{
			invocarImprimirDocumentos(ab_boton, as_pantalla);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("imprimirDocumentos", lb2be_e);
		}
	}

	/**
	 * Listo para imprimir.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 */
	public boolean listoParaImprimir()
	{
		boolean       ls_return;
		TurnoHistoria lth_turno;

		ls_return     = false;
		lth_turno     = getTurnoActual();

		if(lth_turno != null)
		{
			String ls_idTurno;
			ls_idTurno = lth_turno.getIdTurno();

			//Nota el atributo DocumentosImprimirOrdenados nunca es null
			if(StringUtils.isValidString(ls_idTurno))
				ls_return = getDocumentosImprimirOrdenados().containsKey(ls_idTurno);
		}

		return ls_return;
	}

	/**
	 * Obtener documento imprimir.
	 *
	 * @return el valor de collection
	 */
	public Collection<DocumentosSalida> obtenerDocumentoImprimir()
	{
		Collection<DocumentosSalida> lcd_return;
		TurnoHistoria                lth_turno;

		lcd_return     = null;
		lth_turno      = getTurnoActual();

		if(lth_turno != null)
		{
			String                                    ls_idTurno;
			Map<String, Collection<DocumentosSalida>> hmsd_documentos;

			hmsd_documentos     = getDocumentosImprimirOrdenados();
			ls_idTurno          = lth_turno.getIdTurno();

			if(StringUtils.isValidString(ls_idTurno))
			{
				if(hmsd_documentos.containsKey(ls_idTurno))
					lcd_return = hmsd_documentos.get(ls_idTurno);
			}
		}

		return lcd_return;
	}

	/**
	 * Retorna a la bandeja de turnos de aprobación.
	 *
	 * @return enlace a la pagina de turnos
	 */
	public String regresar()
	{
		clean();

		return NavegacionCommon.BANDEJA_ENTRADA;
	}

	/**
	 * Terminar proceso.
	 *
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String terminarProceso()
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		try
		{
			boolean                   lb_fijarAviso;
			Collection<TurnoHistoria> lcth_turnosSeleccionados;
			long                      ll_idEtapa;

			lcth_turnosSeleccionados     = obtenerTurnosSeleccionados();
			ll_idEtapa                   = getIdEtapa();

			if(ll_idEtapa <= 0)
				throw new B2BException(ErrorKeys.ERROR_SIN_ETAPA);

			lb_fijarAviso = (ll_idEtapa == EtapaCommon.ID_ETAPA_FIJACION_AVISOS_OFICINA_ORIGEN)
					|| (ll_idEtapa == EtapaCommon.PUBLICACION_ACTOS_ADMINISTIVOS);

			{
				Optional<TurnoHistoria> loth_turnoSinFecha;

				//FORMATO Comentar lambda antes de formatear
				if(lb_fijarAviso)
					loth_turnoSinFecha = lcth_turnosSeleccionados.stream()
						.filter(lth_turno -> lth_turno.getFechaPublicacionAvisoWeb() == null)
						.findFirst();
				else
					loth_turnoSinFecha = lcth_turnosSeleccionados.stream()
						.filter(lth_turno -> lth_turno.getFechaDesfijacionAviso() == null)
						.findFirst();
				if(loth_turnoSinFecha.isPresent())
				{
					TurnoHistoria lth_turnoSinFecha;
					Object[]      loa_args;

					lth_turnoSinFecha     = loth_turnoSinFecha.get();
					loa_args              = new String[1];

					loa_args[0] = lth_turnoSinFecha.getIdTurno();

					lth_turnoSinFecha.setFechaFijacionNoValida(true);

					throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_PARA_TURNO, loa_args);
				}
			}

			{
				Optional<TurnoHistoria> loth_turnoFechaNoValida;

				//FORMATO Comentar lambda antes de formatear
				if(lb_fijarAviso)
					loth_turnoFechaNoValida = lcth_turnosSeleccionados.stream()
						.filter(lth_turno -> lth_turno.getFechaPublicacionAvisoWeb().after(new Date()))
						.findFirst();
				else
					loth_turnoFechaNoValida = lcth_turnosSeleccionados.stream()
						.filter(lth_turno -> lth_turno.getFechaDesfijacionAviso().after(new Date()))
						.findFirst();
				if(loth_turnoFechaNoValida.isPresent())
				{
					TurnoHistoria lth_turnoSinFecha;
					Object[]      loa_args;

					lth_turnoSinFecha     = loth_turnoFechaNoValida.get();
					loa_args              = new String[1];

					loa_args[0] = lth_turnoSinFecha.getIdTurno();

					lth_turnoSinFecha.setFechaFijacionNoValida(true);

					throw new B2BException(ErrorKeys.ERROR_FECHA_NO_VALIDA_PARA_TURNO, loa_args);
				}
			}

			if(lb_fijarAviso)
				irnr_realizarNotificacionRemote.fijarAvisos(
				    lcth_turnosSeleccionados, ll_idEtapa, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			else
				irnr_realizarNotificacionRemote.desfijarAvisos(
				    lcth_turnosSeleccionados, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ll_idEtapa == EtapaCommon.ID_ETAPA_FIJACION_AVISOS_OFICINA_ORIGEN)
			{
				FacesContext lfc_context;

				lfc_context = FacesUtils.getFacesContext();

				{
					BeanAprobacion lb_beanAprobacion;
					lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
							                                           .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
							);

					if(lb_beanAprobacion != null)
					{
						lb_beanAprobacion.clean();
						lb_beanAprobacion.limpiarBanderaProcesos();
						lb_beanAprobacion.setVieneDeBandejaFijacionAvisosOficinaOrigen(true);
						lb_beanAprobacion.findDetalleAprobacion();
						lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
						lb_beanAprobacion.setIdProcesoSeleccionado(null);
						lb_beanAprobacion.generarGraficoDeTorta(ll_idEtapa, false);

						ls_return = NavegacionCommon.BANDEJA_ENTRADA;
					}
				}
			}
			else
				cargarCasos();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("terminarProceso", lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Terminar proceso de traslados.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String terminarProcesoTraslado()
	    throws B2BException
	{
		String ls_return;
		
		ls_return = null;
		
		try
		{
			boolean                   lb_fijarAviso;
			Collection<TurnoHistoria> lcth_turnosSeleccionados;
			long                      ll_idEtapa;

			lcth_turnosSeleccionados     = obtenerTurnoSeleccionado();
			ll_idEtapa                   = getIdEtapa();

			if(ll_idEtapa <= 0)
				throw new B2BException(ErrorKeys.ERROR_SIN_ETAPA);

			lb_fijarAviso = (ll_idEtapa == EtapaCommon.ID_ETAPA_FIJACION_AVISOS_OFICINA_ORIGEN)
					|| (ll_idEtapa == EtapaCommon.PUBLICACION_ACTOS_ADMINISTIVOS);

			{
				Optional<TurnoHistoria> loth_turnoSinFecha;

//				//FORMATO Comentar lambda antes de formatear
				if(lb_fijarAviso)
					loth_turnoSinFecha = lcth_turnosSeleccionados.stream()
					.filter(lth_turno -> lth_turno.getFechaPublicacionAvisoWeb() == null)
					.findFirst();
				else
					loth_turnoSinFecha = lcth_turnosSeleccionados.stream()
					.filter(lth_turno -> lth_turno.getFechaDesfijacionAviso() == null)
					.findFirst();
				if(loth_turnoSinFecha.isPresent())
				{
					TurnoHistoria lth_turnoSinFecha;
					Object[]      loa_args;
					
					lth_turnoSinFecha     = loth_turnoSinFecha.get();
					loa_args              = new String[1];
					
					loa_args[0] = lth_turnoSinFecha.getIdTurno();
					
					lth_turnoSinFecha.setFechaFijacionNoValida(true);
					
					throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_PARA_TURNO, loa_args);
				}
			}

			{
				Optional<TurnoHistoria> loth_turnoFechaNoValida;

//				//FORMATO Comentar lambda antes de formatear
				if(lb_fijarAviso)
					loth_turnoFechaNoValida = lcth_turnosSeleccionados.stream()
					.filter(lth_turno -> lth_turno.getFechaPublicacionAvisoWeb().after(new Date()))
					.findFirst();
				else
					loth_turnoFechaNoValida = lcth_turnosSeleccionados.stream()
					.filter(lth_turno -> lth_turno.getFechaDesfijacionAviso().after(new Date()))
					.findFirst();
				if(loth_turnoFechaNoValida.isPresent())
				{
					TurnoHistoria lth_turnoSinFecha;
					Object[]      loa_args;
					
					lth_turnoSinFecha     = loth_turnoFechaNoValida.get();
					loa_args              = new String[1];
					
					loa_args[0] = lth_turnoSinFecha.getIdTurno();
					
					lth_turnoSinFecha.setFechaFijacionNoValida(true);
					
					throw new B2BException(ErrorKeys.ERROR_FECHA_NO_VALIDA_PARA_TURNO, loa_args);
				}
			}

			if(lb_fijarAviso)
				irnr_realizarNotificacionRemote.fijarAvisos(
				    lcth_turnosSeleccionados, ll_idEtapa, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			else
				irnr_realizarNotificacionRemote.desfijarAvisos(
				    lcth_turnosSeleccionados, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ll_idEtapa == EtapaCommon.ID_ETAPA_FIJACION_AVISOS_OFICINA_ORIGEN ||
					ll_idEtapa == EtapaCommon.PUBLICACION_ACTOS_ADMINISTIVOS)
			{
				if(verificarCreacionDocumento())
				{
					TurnoHistoria lth_turnoActual;

					lth_turnoActual = getTurnoActual();

					if(lth_turnoActual != null)
						lth_turnoActual.setDocumentoGenerado(true);
					
					ls_return = NavegacionCommon.DETALLE_ACTO;
				}

			}
			else
				cargarCasos();

			//Revisar por qué no muestra el error en pantalla
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("terminarProcesoTraslado", lb2be_e);
			actualizarComponente("confrontacionForm:idGrowl");
		}
		
		return ls_return;
	}

	/**
	 * Validar fijacion avisos.
	 *
	 * @param ath_turno de ath turno
	 * @return el valor de string
	 */
	public String validarFijacionAvisos(TurnoHistoria ath_turno)
	{
		String       ls_result;
		boolean      lb_focus;
		FacesContext lfc_context;

		ls_result       = null;
		lb_focus        = true;
		lfc_context     = FacesUtils.getFacesContext();

		try
		{
			int li_column;

			li_column = 0;

			{
				Collection<TurnoHistoria> lcth_historias;

				lcth_historias = getTurnos();

				if(CollectionUtils.isValidCollection(lcth_historias))
				{
					Iterator<TurnoHistoria> lith_iterador;

					lith_iterador = lcth_historias.iterator();

					if(lith_iterador != null)
					{
						boolean lb_encontro;

						lb_encontro = false;

						while(lith_iterador.hasNext() && !lb_encontro)
						{
							TurnoHistoria lth_historia;

							lth_historia = lith_iterador.next();

							if(lth_historia != null)
							{
								lb_encontro = lth_historia.equals(ath_turno);

								if(!lb_encontro)
									li_column++;
							}
						}
					}
				}
			}

			{
				Date ld_fechaRecibido;

				ld_fechaRecibido     = ath_turno.getFechaInicial();

				lb_focus = validateStyles(
					    "fFijacionAvisos:idDtTurnos:" + li_column + ":idCalFeInicial", lfc_context, ld_fechaRecibido,
					    lb_focus
					);

				if(ld_fechaRecibido == null)
					throw new B2BException(ErrorKeys.ERROR_FECHA_RECIBIDO);
			}

			{
				Date ld_fechaPublicacionAvisoWeb;

				ld_fechaPublicacionAvisoWeb     = ath_turno.getFechaPublicacionAvisoWeb();

				lb_focus = validateStyles(
					    "fFijacionAvisos:idDtTurnos:" + li_column + ":idCalFechaPublicacion_input", lfc_context,
					    ld_fechaPublicacionAvisoWeb, lb_focus
					);

				if(ld_fechaPublicacionAvisoWeb == null)
					throw new B2BException(ErrorKeys.ERROR_FECHA_PUBLICACION);
			}

			{
				String ls_medioPublicacion;

				ls_medioPublicacion     = ath_turno.getMedioPublicacion();

				lb_focus = validateStyles(
					    "fFijacionAvisos:idDtTurnos:" + li_column + ":idSoMPublicado", lfc_context, ls_medioPublicacion,
					    lb_focus
					);

				if(!StringUtils.isValidString(ls_medioPublicacion))
					throw new B2BException(ErrorKeys.ERROR_CAMPO_PUBLICADO);
			}

			ls_result = salvar(ath_turno);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("salvar", lb2be_e);
		}

		return ls_result;
	}

	/**
	 * Invocar imprimir documentos.
	 *
	 * @param ab_boton de ab boton
	 * @param as_pantalla de as pantalla
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void invocarImprimirDocumentos(boolean ab_boton, String as_pantalla)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_turnosSeleccionados;

		lcth_turnosSeleccionados = obtenerTurnosSeleccionados();

		for(TurnoHistoria lth_turno : lcth_turnosSeleccionados)
		{
			if(lth_turno != null)
				imprimirDocumentos(ab_boton, as_pantalla, lth_turno.getIdTurno(), null);
		}
	}

	/**
	 * Obtener turno seleccionado.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private Collection<TurnoHistoria> obtenerTurnoSeleccionado()
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_turnosSeleccionados;

		lcth_turnosSeleccionados = new LinkedList<TurnoHistoria>();

		{
			TurnoHistoria lth_turno;

			lth_turno = getTurnoActual();

			if(lth_turno != null)
			{
				if(!lth_turno.isSeleccionado())
					throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_TURNOS);

				else
				{
					lcth_turnosSeleccionados.add(lth_turno);

					String ls_idTurno;

					ls_idTurno = lth_turno.getIdTurno();

					if(StringUtils.isValidString(ls_idTurno))
					{
						Collection<DocumentosSalida> cds_documentosSalida;

						cds_documentosSalida = irnr_realizarNotificacionRemote.obtenerDocumentosAviso(
							    lcth_turnosSeleccionados, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						//Nota el atributo DocumentosImprimirOrdenados nunca es null
						if(CollectionUtils.isValidCollection(cds_documentosSalida))
							getDocumentosImprimirOrdenados().put(ls_idTurno, cds_documentosSalida);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_TURNOS);
		}

		return lcth_turnosSeleccionados;
	}

	/**
	 * Obtener turnos seleccionados.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private Collection<TurnoHistoria> obtenerTurnosSeleccionados()
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_turnosSeleccionados;

		lcth_turnosSeleccionados = new LinkedList<TurnoHistoria>();

		{
			Collection<TurnoHistoria> lcth_turnos;

			lcth_turnos = getTurnos();

			if(CollectionUtils.isValidCollection(lcth_turnos))
			{
				//FORMATO Comentar lambda antes de formatear
				lcth_turnos.stream().filter(lth_turno -> lth_turno.isSeleccionado())
					.forEach(lth_turno -> lcth_turnosSeleccionados.add(lth_turno));
				if(!CollectionUtils.isValidCollection(lcth_turnosSeleccionados))
					throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_TURNOS);

				setDocumentosImprimir(
				    irnr_realizarNotificacionRemote.obtenerDocumentosAviso(
				        lcth_turnosSeleccionados, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				    )
				);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_TURNOS);
		}

		return lcth_turnosSeleccionados;
	}

	/**
	 * Método encargado de guardar fechas de publicación y avanzar al detalle.
	 *
	 * @param ath_turnoHistoria de ath turno
	 * @return el valor de string
	 */
	private String salvar(TurnoHistoria ath_turnoHistoria)
	{
		String ls_return;
		ls_return = null;

		try
		{
			if(ath_turnoHistoria != null)
			{
				//Calcular fecha desfijación
				{
					Date ld_fechaPublicacion;

					ld_fechaPublicacion = ath_turnoHistoria.getFechaPublicacionAvisoWeb();

					if(ld_fechaPublicacion != null)
					{
						Calendar lc_calendar         = Calendar.getInstance();
						Date     ld_fechaDesfijacion = new Date();

						lc_calendar.setTime(ld_fechaPublicacion);
						lc_calendar.add(Calendar.DAY_OF_MONTH, 5);
						ld_fechaDesfijacion = lc_calendar.getTime();

						ath_turnoHistoria.setFechaDesfijacionAviso(ld_fechaDesfijacion);
					}
				}

				ath_turnoHistoria.setIdUsuarioModificacion(getUserId());
				ath_turnoHistoria.setIpModificacion(getRemoteIpAddress());
				ath_turnoHistoria.setSeleccionado(true);
				setTurnoActual(ath_turnoHistoria);

				irnr_realizarNotificacionRemote.cambiarMedioPublicacion(
				    ath_turnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				BeanPredioDocumentoActo lbpdab_bean;
				lbpdab_bean = (BeanPredioDocumentoActo)FacesUtils.obtenerInstanciaBean(
					    BeanPredioDocumentoActo.class, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO
					);

				String ls_idTurno;
				String ls_idProceso;

				ls_idTurno       = ath_turnoHistoria.getIdTurno();
				ls_idProceso     = ath_turnoHistoria.getIdProceso();

				if(lbpdab_bean != null)
					lbpdab_bean.limpiarDatos();
				
				Collection<TurnoHistoria> lcth_turnosHistoria;
				
				lcth_turnosHistoria = new LinkedList<TurnoHistoria>();
				lcth_turnosHistoria.add(ath_turnoHistoria);

				lbpdab_bean.setTurnoHistoria(ath_turnoHistoria);
				lbpdab_bean.setTurnoHistorias(lcth_turnosHistoria);
				lbpdab_bean.setIdEtapa(NumericUtils.getLongWrapper(ath_turnoHistoria.getIdEtapa()));
				lbpdab_bean.setIdProceso(ls_idProceso);
				lbpdab_bean.setIdTurno(ls_idTurno);

				if(StringUtils.isValidString(ls_idProceso))
				{
					if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_38))
					{
						lbpdab_bean.setObservacionesCalificacion(ath_turnoHistoria.getObservaciones());
						lbpdab_bean.setObservacionesGrabacionMatriculas(null);
						lbpdab_bean.setDatosAntSistema(null);
						lbpdab_bean.setVieneDeFijacionAvisos(true);

						BeanAprobacion lb_beanAprobacion;

						lb_beanAprobacion = (BeanAprobacion)obtenerInstanciaBean(
							    BeanAprobacion.class, BeanNameCommon.BEAN_APROBACION
							);

						if(lb_beanAprobacion != null)
							lb_beanAprobacion.setVieneDeCorrecciones(false);

						lbpdab_bean.setProcesoTraslados(true);
						lbpdab_bean.setConfrontacion(false);
					}

					{
						boolean      lb_actoEjecutoriaMayor;
						String       ls_mensaje;
						String       ls_decisionCalificacion;
						String       ls_mensajeError;
						String       ls_mens;
						Trazabilidad lt_trazaTemp;

						lb_actoEjecutoriaMayor      = false;
						ls_mensaje                  = null;
						ls_decisionCalificacion     = null;
						ls_mens                     = null;
						ls_mensajeError             = null;
						lt_trazaTemp                = new Trazabilidad();

						lbpdab_bean.setIdTurnoHistoria(StringUtils.getString(ath_turnoHistoria.getIdTurnoHistoria()));
						ath_turnoHistoria.setFechaEtapaValida(true);

						lbpdab_bean.clear();
						lbpdab_bean.setIndVinculado(false);
						lbpdab_bean.generarDatosAntSistema();
						lbpdab_bean.obtenerInformacionASEtapa101();
						lbpdab_bean.generarDatosDocumento();
						lbpdab_bean.generarDatosTramitesVinculados();
						lbpdab_bean.validarFechaVencimientoActo();
						lbpdab_bean.setMotivoTramite(null);
						lbpdab_bean.setMostrarDialog(false);
						//TODO Revisar si entran procesos 4 y revisar condición de detalleActoEtapa() BeanTurnos
						{
							List<Map<String, Object>> llmso_infoDoc      = lbpdab_bean.getDocumentos();
							Date                      ld_fechaDocumento  = (Date)llmso_infoDoc.get(0)
									                                                              .get(
									    "FECHA_DOCUMENTO"
									);
							Date                      ld_fechaEjecutoria = (Date)llmso_infoDoc.get(0)
									                                                              .get(
									    "FECHA_EJECUTORIA"
									);
							Date                      ld_fechaACalcular  = ((ld_fechaEjecutoria != null)
								? ld_fechaEjecutoria : ld_fechaDocumento);

							if(ld_fechaACalcular != null)
							{
								Date                 ld_fechaConDosMesesAdelante;
								Date                 ld_fechaActual = new Date();
								FechaVenceTerminosUI lfvtui_fecha;
								lfvtui_fecha                        = new FechaVenceTerminosUI();

								lfvtui_fecha.setFechaInicial(ld_fechaACalcular);
								lfvtui_fecha.setTipoCalendario("C");
								lfvtui_fecha.setIdCirculo(null);

								if(ld_fechaEjecutoria != null)
								{
									lfvtui_fecha.setDiasCalcularFecha(NumericUtils.getInteger(90));
									ld_fechaConDosMesesAdelante = irr_referenceRemote.calcularFechaVencimiento(
										    lfvtui_fecha
										);

									if(ld_fechaActual.after(ld_fechaConDosMesesAdelante))
										ls_mensaje = getMessages()
												             .getString(MessagesKeys.DOCUMENTO_HA_SUPERADO_TRES_MESES);

									lb_actoEjecutoriaMayor = true;
								}
								else if(ld_fechaDocumento != null)
								{
									lfvtui_fecha.setDiasCalcularFecha(NumericUtils.getInteger(60));
									ld_fechaConDosMesesAdelante = irr_referenceRemote.calcularFechaVencimiento(
										    lfvtui_fecha
										);

									if(ld_fechaActual.after(ld_fechaConDosMesesAdelante))
										ls_mensaje = getMessages()
												             .getString(MessagesKeys.DOCUMENTO_HA_SUPERADO_DOS_MESES);
								}
							}
						}

						{
							String ls_mError = null;
							ls_mError = irr_calificacionRemote.getMensajeError(
								    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
								);

							if(StringUtils.isValidString(ls_mError))
								ls_mensajeError = ls_mError;
						}

						lbpdab_bean.getCausales();
						lbpdab_bean.getMatriculasRangos();
						lbpdab_bean.getMatriculasIndividuales();
						lbpdab_bean.getMatriculasTmpRangos();
						lbpdab_bean.getMatriculasTmpIndividuales();
						lbpdab_bean.setRecepcionDeDocumentos(false);

						{
							if(StringUtils.isValidString(ls_idTurno))
							{
								Collection<Acto> lca_actos;

								lca_actos = irr_calificacionRemote.findActosTurnoMatriculas(ls_idTurno);

								if(CollectionUtils.isValidCollection(lca_actos))
								{
									Map<String, TipoActo> lmst_actosRemate;
									Map<String, String>   lc_actosVencimiento;

									lc_actosVencimiento     = ipr_parameterRemote.generarCodigos(
										    ConstanteCommon.ACTOS_VENCIMIENTO_DIAS_HABILES
										);

									lmst_actosRemate = irr_referenceRemote.findAllTiposActoRemanenteActivosMap(
										    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
										);

									if(CollectionUtils.isValidCollection(lmst_actosRemate))
									{
										boolean             lb_remate;
										Iterator<Acto>      lia_iterator;
										Map<String, String> lmss_actos;
										boolean             lb_validar;

										lb_remate        = false;
										lia_iterator     = lca_actos.iterator();
										lmss_actos       = new HashMap<String, String>();
										lb_validar       = false;

										while(lia_iterator.hasNext())
										{
											Acto la_acto;

											la_acto = lia_iterator.next();

											if(la_acto != null)
											{
												String ls_idTipoActo;

												ls_idTipoActo = la_acto.getIdTipoActo();

												if(StringUtils.isValidString(ls_idTipoActo))
												{
													if(lc_actosVencimiento != null)
													{
														if(
														    lc_actosVencimiento.containsKey(ls_idTipoActo)
															    && !lb_validar
														)
														{
															{
																Date                 ld_fechaConTresMesesAdelante;
																Date                 ld_fechaActual    = new Date();
																FechaVenceTerminosUI lfvtui_fecha;
																lfvtui_fecha                           = new FechaVenceTerminosUI();

																List<Map<String, Object>> llmso_infoDoc = lbpdab_bean
																		.getDocumentos();
																Date                      ld_fechaDocumento = (Date)llmso_infoDoc.get(
																	    0
																	).get("FECHA_DOCUMENTO");

																lfvtui_fecha.setFechaInicial(ld_fechaDocumento);
																lfvtui_fecha.setTipoCalendario("C");
																lfvtui_fecha.setIdCirculo(null);
																lfvtui_fecha.setDiasCalcularFecha(
																    NumericUtils.getInteger(90)
																);

																ld_fechaConTresMesesAdelante = irr_referenceRemote
																		.calcularFechaVencimiento(lfvtui_fecha);

																if(
																    ld_fechaActual.after(ld_fechaConTresMesesAdelante)
																	    && !lb_actoEjecutoriaMayor
																)
																{
																	lb_validar     = true;
																	ls_mens        = getMessages()
																			                 .getString(
																			    MessagesKeys.DOCUMENTO_VENCIDO_90_DIAS_HABILES
																			);
																}
															}
														}
													}

													if(lmst_actosRemate.containsKey(ls_idTipoActo))
													{
														String ls_matricula;

														ls_matricula = la_acto.getReferencia();

														if(
														    StringUtils.isValidString(ls_matricula)
															    && !lmss_actos.containsKey(ls_idTipoActo)
														)
															lmss_actos.put(ls_matricula, ls_matricula);

														lb_remate = true;
													}
												}
											}
										}

										if(lb_remate)
										{
											B2BException  lb2be_e;
											Object[]      aoa_messageArgs;
											StringBuilder lsb_mensaje;
											long          ll_tamanoActos;
											long          ll_posicion;

											aoa_messageArgs     = new String[1];
											lsb_mensaje         = new StringBuilder();

											if(CollectionUtils.isValidCollection(lmss_actos))
											{
												ll_tamanoActos     = lmss_actos.size();
												ll_posicion        = 1;

												for(Map.Entry<String, String> lm_iterador : lmss_actos.entrySet())
												{
													String ls_separador;
													ls_separador = null;

													if(ll_posicion < ll_tamanoActos)
														ls_separador = ", ";
													else
														ls_separador = "";

													ll_posicion++;

													lsb_mensaje.append(lm_iterador.getValue() + ls_separador);
												}
											}

											aoa_messageArgs[0]     = lsb_mensaje.toString();
											lb2be_e                = new B2BException(
												    ErrorKeys.ERROR_MATRICULAS_EMBARGO_REMANENTE, aoa_messageArgs
												);
											ls_mensaje             = lb2be_e.getMessage();
										}
									}
								}

								{
									Turno lt_turnoTemp;

									lt_turnoTemp = new Turno();

									lt_turnoTemp.setIdTurno(lbpdab_bean.getIdTurno());

									lt_trazaTemp.setTurno(lt_turnoTemp);

									ls_decisionCalificacion = irr_referenceRemote.findDecisionCalificacion(
										    lt_trazaTemp
										);

									lbpdab_bean.setDecisionCalificacion(ls_decisionCalificacion);
								}

								{
									List<Map<String, Object>> lmsb_matriculas;
									Collection<Object>        lco_matriculas;

									lmsb_matriculas     = lbpdab_bean.getMatriculasIndividuales();
									lco_matriculas      = new ArrayList<Object>();

									if(CollectionUtils.isValidCollection(lmsb_matriculas))
									{
										for(Map<String, Object> lm_map : lmsb_matriculas)
										{
											if(lm_map != null)
											{
												for(Map.Entry<String, Object> lmesb_entry : lm_map.entrySet())
												{
													if(lmesb_entry != null)
														lco_matriculas.add(lmesb_entry.getValue());
												}
											}
										}

										if(CollectionUtils.isValidCollection(lco_matriculas))
										{
											Collection<String> lcs_matriculasExtraidas;

											lcs_matriculasExtraidas = new ArrayList<String>();

											for(Object lo_object : lco_matriculas)
											{
												if(lo_object != null)
												{
													String ls_temp;

													ls_temp = lo_object.toString();

													if(StringUtils.isValidString(ls_temp))
													{
														for(int i = 0; i < ls_temp.length(); i++)
														{
															char   lc_caracter;
															String ls_guion;

															lc_caracter     = ls_temp.charAt(i);
															ls_guion        = IdentificadoresCommon.SIMBOLO_GUION;

															if(lc_caracter == ls_guion.charAt(0))
																lcs_matriculasExtraidas.add(ls_temp);
														}
													}
												}
											}

											if(CollectionUtils.isValidCollection(lcs_matriculasExtraidas))
											{
												String ls_matriculaValidadaMedidaCautelar;

												ls_matriculaValidadaMedidaCautelar = irr_calificacionRemote
														.validacionMedidaCautelar(
														    lcs_matriculasExtraidas, ls_idTurno, getUserId(),
														    getLocalIpAddress(), getRemoteIpAddress()
														);

												if(StringUtils.isValidString(ls_matriculaValidadaMedidaCautelar))
												{
													Object[] aoa_messageArgs;

													aoa_messageArgs     = new String[1];

													aoa_messageArgs[0] = ls_matriculaValidadaMedidaCautelar;

													addMessage(
													    MessagesKeys.MATRICULA_MEDIDA_CAUTELAR_VIGENTE, aoa_messageArgs
													);
												}
											}
										}
									}
								}
							}

							{
								boolean lb_mensaje;
								boolean lb_error;
								boolean lb_mens;

								lb_mensaje     = StringUtils.isValidString(ls_mensaje);
								lb_error       = StringUtils.isValidString(ls_mensajeError);
								lb_mens        = StringUtils.isValidString(ls_mens);

								if(lb_mensaje)
									lbpdab_bean.showGrowl(ls_mensaje);

								if(lb_error)
									lbpdab_bean.showGrowl(ls_mensajeError);

								if(lb_mens)
									lbpdab_bean.showGrowl(ls_mens);

								if(lb_mensaje || lb_error || lb_mens)
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
						}
					}

					setDocumentosPdfCargados(null);
					setArchivosPdfCargados(null);
					ls_return = NavegacionCommon.DETALLE_ACTO;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("salvar", lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Verificar creacion documento.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 */
	private boolean verificarCreacionDocumento()
	{
		boolean lb_return;

		lb_return = false;

		try
		{
			TurnoHistoria lth_turnoAcutal;

			lth_turnoAcutal = getTurnoActual();

			if(lth_turnoAcutal != null)
			{
				String ls_idDocumentoSalida;

				ls_idDocumentoSalida = lth_turnoAcutal.getIdDocumentoSalida();

				if(StringUtils.isValidString(ls_idDocumentoSalida))
				{
					DocumentosSalida lds_Documento;

					lds_Documento = irnr_realizarNotificacionRemote.consultarDocuemntoSalida(
						    NumericUtils.getLong(ls_idDocumentoSalida), getUserId(), getLocalIpAddress(),
						    getRemoteIpAddress()
						);

					if(lds_Documento != null)
					{
						String ls_idEcm;
						String ls_idNombreDocumento;

						ls_idEcm                 = lds_Documento.getIdEcm();
						ls_idNombreDocumento     = lds_Documento.getIdNombreDocumento();

						if(StringUtils.isValidString(ls_idEcm) && StringUtils.isValidString(ls_idNombreDocumento))
							lb_return = true;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("terminarProceso", lb2be_e);
		}

		return lb_return;
	}
}
