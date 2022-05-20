package com.bachue.snr.prosnr02.business.workflow;

import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obteneraccesosporrol.v1.TipoAcceso;

import co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.cliente.v1.AccesosPorUsuario;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.b2bsg.common.xml.DomHelper;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.EtapaDAO;
import com.bachue.snr.prosnr01.dao.pgn.FestivoNacionalDAO;

import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SubprocesoVersion;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.pgn.UnidadTiempoVencimiento;

import com.bachue.snr.prosnr02.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr02.common.constants.ErrorKeys;
import com.bachue.snr.prosnr02.common.constants.EstadoFlujoCommon;

import com.bachue.snr.prosnr02.dao.acc.ProcesoTrabajoDAO;
import com.bachue.snr.prosnr02.dao.acc.SubProcesoTrabajoDAO;
import com.bachue.snr.prosnr02.dao.acc.SubProcesoVersionTrabajoDAO;
import com.bachue.snr.prosnr02.dao.pgn.EtapaTrabajoDAO;
import com.bachue.snr.prosnr02.dao.pgn.MotivoTramiteTrabajoDAO;

import com.bachue.snr.prosnr02.model.acc.EtapaTrabajo;
import com.bachue.snr.prosnr02.model.acc.ProcesoTrabajo;
import com.bachue.snr.prosnr02.model.acc.SubProcesoTrabajo;
import com.bachue.snr.prosnr02.model.acc.SubProcesoVersionTrabajo;
import com.bachue.snr.prosnr02.model.pgn.MotivoTramiteTrabajo;
import com.bachue.snr.prosnr02.model.pgn.ReglaNegocio;
import com.bachue.snr.prosnr02.model.workflow.Componente;
import com.bachue.snr.prosnr02.model.workflow.Compuerta;
import com.bachue.snr.prosnr02.model.workflow.CompuertaExclusiva;
import com.bachue.snr.prosnr02.model.workflow.CompuertaParalela;
import com.bachue.snr.prosnr02.model.workflow.DescriptorFin;
import com.bachue.snr.prosnr02.model.workflow.Evento;
import com.bachue.snr.prosnr02.model.workflow.EventoFinEtapa;
import com.bachue.snr.prosnr02.model.workflow.EventoFinProceso;
import com.bachue.snr.prosnr02.model.workflow.EventoInicioProceso;
import com.bachue.snr.prosnr02.model.workflow.TipoCompuertaLlegada;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.StringWriter;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.xml.XMLConstants;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import javax.xml.transform.dom.DOMSource;

import javax.xml.transform.stream.StreamResult;


/**
 * Clase para el manejo del negocio de workflow.
 *
 * @author Manuel Blanco
 */
public class WorkflowBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(WorkflowBusiness.class, ProyectosCommon.WORKFLOW_02);

	/** Constante cs_ATRIBUTO_COMPUERTA_FLUJO_DEFECTO. */
	private static final String cs_ATRIBUTO_COMPUERTA_FLUJO_DEFECTO = "default";

	/** Constante cs_ATRIBUTO_ELEMENTO_FRONTERA_ASOCIADO_A. */
	private static final String cs_ATRIBUTO_ELEMENTO_FRONTERA_ASOCIADO_A = "attachedToRef";

	/** Constante cs_ATRIBUTO_ELEMENTO_FRONTERA_NOMBRE. */
	private static final String cs_ATRIBUTO_ELEMENTO_FRONTERA_NOMBRE = "name";

	/** Constante cs_ATRIBUTO_FLUJO_ID_DESTINO. */
	private static final String cs_ATRIBUTO_FLUJO_ID_DESTINO = "targetRef";

	/** Constante cs_ATRIBUTO_FLUJO_ID_ORIGEN. */
	private static final String cs_ATRIBUTO_FLUJO_ID_ORIGEN = "sourceRef";

	/** Constante cs_ATRIBUTO_GENERAL_ID. */
	private static final String cs_ATRIBUTO_GENERAL_ID = "id";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_FLUJO_DECISION_CALIFICACION. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_FLUJO_DECISION_CALIFICACION = "decisionCalificacion";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_FLUJO_ESTADO. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_FLUJO_ESTADO = "estado";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_FLUJO_ID_MOTIVO. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_FLUJO_ID_MOTIVO = "motivo";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_FLUJO_INDICADOR_DEVOLUCION. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_FLUJO_INDICADOR_DEVOLUCION = "indDevolucion";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_GENEAL_DESCRIPCION. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_GENEAL_DESCRIPCION = "descripcion";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_PROCESO_ID. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_PROCESO_ID = "idProceso";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_PROCESO_NOMBRE. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_PROCESO_NOMBRE = "nombreProceso";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_PROCESO_RECEPCION_DOCUMENTOS. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_PROCESO_RECEPCION_DOCUMENTOS = "recepcionDocumentosProceso";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_SUBPROCESO_ID. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_SUBPROCESO_ID = "idSubproceso";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_SUBPROCESO_NOMBRE. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_SUBPROCESO_NOMBRE = "nombreSubproceso";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_TAREA_CANTIDAD_TIEMPO_ESPERA. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_TAREA_CANTIDAD_TIEMPO_ESPERA = "cantidadTiempoEspera";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_TAREA_DIAS_HABILES_NORMAL. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_TAREA_DIAS_HABILES_NORMAL = "diasHabilesNormal";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_TAREA_ESTADO_ACTIVIDAD. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_TAREA_ESTADO_ACTIVIDAD = "estadoActividad";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_TAREA_ESTADO_ETAPA. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_TAREA_ESTADO_ETAPA = "estadoEtapa";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_TAREA_ETAPA. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_TAREA_ETAPA = "etapa";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_TAREA_FASE. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_TAREA_FASE = "fase";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_TAREA_GENERAR_QR. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_TAREA_GENERAR_QR = "indGenerarQR";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_TAREA_INDICADOR_BLOQUEO. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_TAREA_INDICADOR_BLOQUEO = "indBloqueo";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_TAREA_INDICADOR_DESBORDE. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_TAREA_INDICADOR_DESBORDE = "indDesborde";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_TAREA_INDICADOR_PESO. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_TAREA_INDICADOR_PESO = "indPeso";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_TAREA_INDICADOR_TOPE. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_TAREA_INDICADOR_TOPE = "indTope";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_TAREA_NOMBRE. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_TAREA_NOMBRE = "nombre";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_TAREA_TIPO_REPARTO. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_TAREA_TIPO_REPARTO = "tipoReparto";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_TAREA_UNIDAD_TIEMPO_ESPERA. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_TAREA_UNIDAD_TIEMPO_ESPERA = "unidadTiempoEspera";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_VERSION_CONSERVACION_DOCUMENTAL. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_VERSION_CONSERVACION_DOCUMENTAL = "conservacionDocumentalSubproceso";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_VERSION_PLANTILLA. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_VERSION_PLANTILLA = "plantillaSubproceso";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_VERSION_RECIBO_CAJA. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_VERSION_RECIBO_CAJA = "reciboCajaSubproceso";

	/** Constante cs_ATRIBUTO_PERSONALIZADO_VERSION_SOLICITUD_CERTIFICADO. */
	private static final String cs_ATRIBUTO_PERSONALIZADO_VERSION_SOLICITUD_CERTIFICADO = "solicitudCertificadoSubproceso";

	/** Constante cs_ELEMENTO_COMPUERTA_EXCLUSIVA. */
	private static final String cs_ELEMENTO_COMPUERTA_EXCLUSIVA = ":exclusiveGateway";

	/** Constante cs_ELEMENTO_COMPUERTA_PARALELA. */
	private static final String cs_ELEMENTO_COMPUERTA_PARALELA = ":parallelGateway";

	/** Constante cs_ELEMENTO_ENTRADA. */
	private static final String cs_ELEMENTO_ENTRADA = ":incoming";

	/** Constante cs_ELEMENTO_EVENTO_ETAPA_FIN. */
	private static final String cs_ELEMENTO_EVENTO_ETAPA_FIN = ":intermediateThrowEvent";

	/** Constante cs_ELEMENTO_EVENTO_PROCESO_FIN. */
	private static final String cs_ELEMENTO_EVENTO_PROCESO_FIN = ":endEvent";

	/** Constante cs_ELEMENTO_EVENTO_PROCESO_FRONTERA. */
	private static final String cs_ELEMENTO_EVENTO_PROCESO_FRONTERA = ":boundaryEvent";

	/** Constante cs_ELEMENTO_EVENTO_PROCESO_INICIO. */
	private static final String cs_ELEMENTO_EVENTO_PROCESO_INICIO = ":startEvent";

	/** Constante cs_ELEMENTO_FLUJO. */
	private static final String cs_ELEMENTO_FLUJO = ":sequenceFlow";

	/** Constante cs_ELEMENTO_PROCESO. */
	private static final String cs_ELEMENTO_PROCESO = ":process";

	/** Constante cs_ELEMENTO_SALIDA. */
	private static final String cs_ELEMENTO_SALIDA = ":outgoing";

	/** Constante cs_ELEMENTO_TAREA. */
	private static final String cs_ELEMENTO_TAREA = ":task";

	/** Constante cs_ELEMENTO_TAREA_RECEPCION. */
	private static final String cs_ELEMENTO_TAREA_RECEPCION = ":receiveTask";

	/** Constante cs_ELEMENTO_TAREA_SERVICIO. */
	private static final String cs_ELEMENTO_TAREA_SERVICIO = ":serviceTask";

	/** Constante cs_ELEMENTO_TAREA_USUARIO. */
	private static final String cs_ELEMENTO_TAREA_USUARIO = ":userTask";

	/** Constante ci_ATRIBUTOS_SECUENCIA_FLUJO. */
	private static final int ci_ATRIBUTOS_SECUENCIA_FLUJO = 7;

	/** Constante ci_ATRIBUTOS_TAREA. */
	private static final int ci_ATRIBUTOS_TAREA = 14;

	/** Constante ci_MINIMA_CANTIDAD_ETAPAS. */
	private static final int ci_MINIMA_CANTIDAD_ETAPAS = 2;

	/** Constante MODO_GRABACION_DATOS_BASICOS. */
	public static final int MODO_GRABACION_DATOS_BASICOS = 1;

	/** Constante MODO_GRABACION_ENVIO_APROBADOR. */
	public static final int MODO_GRABACION_ENVIO_APROBADOR = 3;

	/** Constante MODO_GRABACION_FLUJOS. */
	public static final int MODO_GRABACION_FLUJOS = 2;

	/** Constante MODO_GRABACION_VALIDACION. */
	public static final int MODO_GRABACION_VALIDACION = 0;

	/** Constante Atributo sobre el monitoreo del proceso para indicar etapas en estado por repartir */
	private static final String cs_ATRIBUTO_PERSONALIZADO_MONITOREO_PROCESO_AMARILLO = "amarillo";

	/** Constante Atributo sobre el monitoreo del proceso para indicar etapas en estado asignado */
	private static final String cs_ATRIBUTO_PERSONALIZADO_MONITOREO_PROCESO_AZUL = "azul";

	/** Constante Atributo sobre el monitoreo del proceso para indicar etapas terminadas fuera de tiempo */
	private static final String cs_ATRIBUTO_PERSONALIZADO_MONITOREO_PROCESO_ROJO = "rojo";

	/** Constante Atributo sobre el monitoreo del proceso para indicar etapas terminadas en tiempo */
	private static final String cs_ATRIBUTO_PERSONALIZADO_MONITOREO_PROCESO_VERDE = "verde";

	/** Constante Atributo sobre el monitoreo de la etapa para indicar el estado */
	private static final String cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_ESTADO_ACTIVIDAD = "estadoActividadEtapa";

	/** Constante Atributo sobre el monitoreo de la etapa para indicar la fecha de creacion */
	private static final String cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_FECHA_CREACION = "fechaCreacion";

	/** Constante Atributo sobre el monitoreo de la etapa para indicar la fecha final */
	private static final String cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_FECHA_FINAL = "fechaFinal";

	/** Constante Atributo sobre el monitoreo de la etapa para indicar la fecha inicial */
	private static final String cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_FECHA_INICIAL = "fechaInicial";

	/** Constante Atributo sobre el monitoreo de la etapa para indicar la fecha de reparto */
	private static final String cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_FECHA_REPARTO = "fechaReparto";

	/** Constante Atributo sobre el monitoreo de la etapa para indicar el id de usuario asigando a la etapa */
	private static final String cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_ID_USUARIO = "idUsuario";

	/** Constante Atributo sobre el monitoreo de la etapa para indicar el indicador de devolucion */
	private static final String cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_INDICADOR_DEVOLUCION = "indicadorDevolucion";

	/** Constante Atributo sobre el monitoreo de la etapa para indicar el motivo tramite */
	private static final String cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_MOTIVO_TRAMITE = "motivoTramite";

	/** Constante Atributo sobre el monitoreo de la etapa para indicar las observaciones */
	private static final String cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_OBSERVACIONES = "observaciones";

	/**
	 * Analizar contenido definiciones.
	 *
	 * @param as_definicion
	 *                                 de as definicion
	 * @param ai_version
	 *                                 de ai version
	 * @param ai_modo
	 *                                 de ai modo
	 * @param ab_nuevaVersion
	 *                                 de ab nueva version
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de collection
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	public synchronized Collection<ProcesoTrabajo> analizarContenidoDefiniciones(
	    String as_definicion, int ai_version, int ai_modo, boolean ab_nuevaVersion, String as_idUsuarioCreacion,
	    String as_ipCreacion
	)
	    throws B2BException
	{
		Collection<ProcesoTrabajo> lcpt_procesos;

		if(!StringUtils.isValidString(as_idUsuarioCreacion))
			throw new B2BException("Usuario inválido");
		else if(!StringUtils.isValidString(as_ipCreacion))
			throw new B2BException("IP inválido");
		else if((ai_modo < MODO_GRABACION_VALIDACION) || (ai_modo > MODO_GRABACION_ENVIO_APROBADOR))
			throw new B2BException("El modo de grabación es inválido");

		lcpt_procesos = null;

		if(StringUtils.isValidString(as_definicion))
		{
			Document ld_bpmn;

			ld_bpmn = DomHelper.getDocument(as_definicion);

			if(ld_bpmn != null)
			{
				Node ln_definiciones;

				ln_definiciones = ld_bpmn.getFirstChild();

				if(ln_definiciones != null)
				{
					{
						boolean  lb_procesos;
						NodeList lnl_listaNodos;

						lb_procesos        = false;
						lnl_listaNodos     = ln_definiciones.getChildNodes();

						if(lnl_listaNodos != null)
						{
							lcpt_procesos = new ArrayList<ProcesoTrabajo>();

							for(int li_i = 0, li_longitud = lnl_listaNodos.getLength(); li_i < li_longitud; li_i++)
							{
								Node ln_nodo;

								ln_nodo = lnl_listaNodos.item(li_i);

								if(ln_nodo != null)
								{
									short ls_tipoNodo;

									ls_tipoNodo = ln_nodo.getNodeType();

									if(ls_tipoNodo == Node.ELEMENT_NODE)
									{
										String ls_nombreNodo;

										ls_nombreNodo = ln_nodo.getNodeName();

										if(
										    StringUtils.isValidString(ls_nombreNodo)
											    && ls_nombreNodo.endsWith(cs_ELEMENTO_PROCESO)
										)
										{
											ProcesoTrabajo lpt_proceso;

											lpt_proceso = analizarElementoProceso(
												    ln_nodo, as_definicion, as_idUsuarioCreacion, as_ipCreacion
												);

											if(lpt_proceso != null)
											{
												lcpt_procesos.add(lpt_proceso);

												lb_procesos = true;
											}
										}
									}
								}
							}
						}

						if(!lb_procesos)
							throw new B2BException("No hay definicion de procesos");
					}

					if(ai_modo >= MODO_GRABACION_DATOS_BASICOS)
					{
						DAOManager ldm_transaccion;

						ldm_transaccion = DaoManagerFactory.getDAOManagerWorkflow();

						ldm_transaccion.setAutoCommit(false);

						try
						{
							lcpt_procesos = CollectionUtils.sort(lcpt_procesos);

							if(CollectionUtils.isValidCollection(lcpt_procesos))
							{
								EtapaTrabajoDAO             letd_etapa;
								MotivoTramiteTrabajoDAO     lmttd_motivoTramite;
								ProcesoTrabajoDAO           lptd_proceso;
								SubProcesoTrabajoDAO        lsptd_subProceso;
								SubProcesoVersionTrabajoDAO lsptd_version;

								letd_etapa              = DaoCreator.getEtapaTrabajoDAO(ldm_transaccion);
								lmttd_motivoTramite     = DaoCreator.getMotivoTramiteTrabajoDAO(ldm_transaccion);
								lptd_proceso            = DaoCreator.getProcesoTrabajoDAO(ldm_transaccion);
								lsptd_subProceso        = DaoCreator.getSubprocesoTrabajoDAO(ldm_transaccion);
								lsptd_version           = DaoCreator.getSubprocesoVersionTrabajoDAO(ldm_transaccion);

								for(ProcesoTrabajo lpt_proceso : lcpt_procesos)
								{
									if(lpt_proceso != null)
									{
										SubProcesoTrabajo lspt_subProceso;

										lspt_subProceso = lpt_proceso.getSubProceso();

										if(lspt_subProceso != null)
										{
											SubProcesoVersionTrabajo lspvt_version;

											lspvt_version = lspt_subProceso.getVersion();

											if(lspvt_version != null)
											{
												boolean                  lb_actualizarProceso;
												boolean                  lb_actualizarSubProceso;
												boolean                  lb_actualizarVersion;
												int                      li_version;
												String                   ls_idProceso;
												String                   ls_idSubProceso;
												Collection<EtapaTrabajo> lcet_etapas;

												ls_idProceso                = lpt_proceso.getId();
												ls_idSubProceso             = lspt_subProceso.getIdSubproceso();
												lcet_etapas                 = lpt_proceso.getColeccionEtapas();
												lb_actualizarProceso        = lptd_proceso.existe(ls_idProceso);
												lb_actualizarVersion        = false;
												lb_actualizarSubProceso     = lsptd_subProceso.existe(
													    ls_idProceso, ls_idSubProceso
													);

												lspvt_version.setEstadoFlujo(EstadoFlujoCommon.DISENIO);

												if(ai_version > 0)
												{
													SubProcesoVersionTrabajo lspvt_versionActual;

													lspvt_versionActual = lsptd_version.findById(
														    ls_idProceso, ls_idSubProceso, ai_version
														);

													if(lspvt_versionActual != null)
													{
														String ls_estadoFlujo;

														ls_estadoFlujo           = lspvt_versionActual.getEstadoFlujo();
														lb_actualizarVersion     = (StringUtils.isValidString(
															    ls_estadoFlujo
															)
																&& (ls_estadoFlujo.equalsIgnoreCase(
																    EstadoFlujoCommon.DISENIO
																)
																|| ls_estadoFlujo.equalsIgnoreCase(
																    EstadoFlujoCommon.DEVUELTO
																)));
														lspvt_version.setVersion(lspvt_versionActual.getVersion());
													}

													li_version = (lb_actualizarVersion && !ab_nuevaVersion)
														? ai_version
														: ((NumericUtils.getInt(
														    lptd_proceso.findMaxVersion(ls_idProceso, ls_idSubProceso)
														)) + 1);
												}
												else
													li_version = (lb_actualizarVersion && !ab_nuevaVersion)
														? ai_version
														: ((NumericUtils.getInt(
														    lptd_proceso.findMaxVersion(ls_idProceso, ls_idSubProceso)
														)) + 1);

												lspvt_version.setVersion(li_version);
												lspvt_version.setVersionBase(ai_version);
												lpt_proceso.setSubProcesoVersion(li_version);

												if(lb_actualizarProceso)
												{
													lpt_proceso.setIdUsuarioModificacion(as_idUsuarioCreacion);
													lpt_proceso.setIpModificacion(as_ipCreacion);
												}
												else
												{
													lpt_proceso.setIdUsuarioCreacion(as_idUsuarioCreacion);
													lpt_proceso.setIpCreacion(as_ipCreacion);
												}

												lptd_proceso.insert(
												    lpt_proceso, lb_actualizarProceso, lb_actualizarSubProceso,
												    ab_nuevaVersion ? false : lb_actualizarVersion
												);

												if(CollectionUtils.isValidCollection(lcet_etapas))
												{
													for(EtapaTrabajo let_etapa : lcet_etapas)
													{
														if(let_etapa != null)
														{
															long ll_idEtapa;

															ll_idEtapa = let_etapa.getIdEtapa();

															if(letd_etapa.existe(ll_idEtapa))
																letd_etapa.update(let_etapa);
															else
																letd_etapa.insert(let_etapa);
														}
													}

													if(ai_modo >= MODO_GRABACION_FLUJOS)
													{
														Collection<MotivoTramiteTrabajo> lcmtt_flujos;

														lcmtt_flujos = lpt_proceso.getColeccionFlujos();

														if(CollectionUtils.isValidCollection(lcmtt_flujos))
														{
															for(MotivoTramiteTrabajo lmtt_flujo : lcmtt_flujos)
															{
																if(lmtt_flujo != null)
																{
																	Integer li_idEtapaOrigen;
																	Integer li_idMotivo;

																	li_idEtapaOrigen     = lmtt_flujo.getIdEtapaOrigen();
																	li_idMotivo          = lmtt_flujo.getMotivo();

																	if(
																	    (NumericUtils.isValidInteger(li_idEtapaOrigen))
																		    && NumericUtils.isValidInteger(li_idMotivo)
																	)
																	{
																		lmtt_flujo.setIdProceso(ls_idProceso);
																		lmtt_flujo.setIdSubProceso(ls_idSubProceso);
																		lmtt_flujo.setVersion(li_version);
																		lmtt_flujo.setIdUsuarioCreacion(
																		    as_idUsuarioCreacion
																		);
																		lmtt_flujo.setIpCreacion(as_ipCreacion);

																		{
																			EtapaTrabajo let_destino;

																			let_destino = lmtt_flujo.getDestino();

																			if(let_destino != null)
																			{
																				if(let_destino.isFinProceso())
																					lmtt_flujo.setDescriptorFin(
																					    DescriptorFin.FIN_PROCESO
																					);
																				else if(let_destino.isFinEtapa())
																					lmtt_flujo.setDescriptorFin(
																					    DescriptorFin.FIN_ETAPA
																					);
																			}
																		}

																		lmttd_motivoTramite.insert(lmtt_flujo);
																	}
																}
															}

															{
																int li_idEtapaInicial;

																li_idEtapaInicial = lmttd_motivoTramite
																		.findIdEtapaOrigen(
																		    li_version, ls_idProceso, ls_idSubProceso
																		);

																if(li_idEtapaInicial >= 0)
																{
																	Optional<EtapaTrabajo> loet_etapa;

																	//FORMATO
																	loet_etapa = lcet_etapas.stream()
																			.filter(le_temp -> le_temp != null
																					&& NumericUtils.getInt(le_temp
																							.getIdEtapa()) == li_idEtapaInicial)
																			.findFirst();
																	
																	if(loet_etapa.isPresent())
																	{
																		EtapaTrabajo let_etapa;

																		let_etapa = loet_etapa.get();

																		if(let_etapa != null)
																			lsptd_version.updateIdEtapaInicial(
																			    let_etapa, li_version, ls_idProceso,
																			    ls_idSubProceso, as_idUsuarioCreacion,
																			    as_ipCreacion
																			);
																	}
																}
															}
														}
													}

													if(ai_modo >= MODO_GRABACION_ENVIO_APROBADOR)
														enviarAlAprobador(
														    ldm_transaccion, ls_idProceso, ls_idSubProceso, li_version,
														    as_idUsuarioCreacion, as_ipCreacion
														);
												}
											}
										}
									}
								}
							}
						}
						catch(B2BException lb2be_e)
						{
							ldm_transaccion.setRollbackOnly();

							throw lb2be_e;
						}
						catch(IOException lioe_ioe)
						{
							ldm_transaccion.setRollbackOnly();

							throw new B2BException(cs_IOEXCEPTION, lioe_ioe);
						}
						finally
						{
							ldm_transaccion.close();
						}
					}
				}
			}
		}

		return lcpt_procesos;
	}

	/**
	 * Aprobar.
	 *
	 * @param as_idProceso
	 *                            de as id proceso
	 * @param as_idSubProceso
	 *                            de as id sub proceso
	 * @param ai_version
	 *                            de ai version
	 * @param as_userId
	 *                            de as user id
	 * @param as_remoteIp
	 *                            de as remote ip
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	public synchronized void aprobar(
	    String as_idProceso, String as_idSubProceso, int ai_version, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubProceso) && (ai_version > 0))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManagerWorkflow();

			try
			{
				aprobar(ldm_manager, as_idProceso, as_idSubProceso, ai_version, as_userId, as_remoteIp);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("aprobar", lb2be_e);

				throw lb2be_e;
			}
			catch(IOException lioe_ioe)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("aprobar", lioe_ioe);

				throw new B2BException(cs_IOEXCEPTION, lioe_ioe);
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Méttodo de consulta de la bandeja de consulta
	 * @param at_parametros con el parametro a buscar
	 * @param ab_onlyTurno con el turno de búsqueda
	 * @return una collection con los turnos asignados
	 * @throws B2BException
	 */
	public synchronized Collection<Trazabilidad> cargarInfoBandejaConsulta(
	    Trazabilidad at_parametros, boolean ab_onlyTurno
	)
	    throws B2BException
	{
		Collection<Trazabilidad> lct_datos;

		lct_datos = new ArrayList<Trazabilidad>();

		if(at_parametros != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				SolicitudDAO     lsd_DAO;
				TurnoDAO         ltd_DAO;
				TurnoHistoriaDAO lthd_DAO;
				Solicitud        ls_solicitud;
				Turno            lt_turno;

				lsd_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);
				ltd_DAO          = DaoCreator.getTurnoDAO(ldm_manager);
				lthd_DAO         = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				ls_solicitud     = lsd_DAO.findByNir(at_parametros.getSolicitud());
				lt_turno         = ltd_DAO.findById(at_parametros.getTurno());

				if((lt_turno == null) && (ls_solicitud == null))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NIR_O_TURNO);
				else if(ls_solicitud == null)
					ls_solicitud = lsd_DAO.findById(new Solicitud(lt_turno.getIdSolicitud()));
				else if(lt_turno == null)
					lt_turno = new Turno();

				at_parametros.setTurno(lt_turno);

				{
					String ls_sn;
					String ls_it;

					ls_it     = lt_turno.getIdTurno();
					ls_sn     = ls_solicitud.getNir();

					if(!ab_onlyTurno)
					{
						if(!StringUtils.isValidString(ls_sn) && !StringUtils.isValidString(ls_it))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_NIR_O_TURNO);
					}
					else if(!StringUtils.isValidString(ls_it))
						throw new B2BException(ErrorKeys.ERROR_TURNO_INVALIDO);

					if(StringUtils.isValidString(ls_sn))
						at_parametros.setSolicitud(
						    DaoCreator.getConsultaTrazabilidadDAO(ldm_manager).findByNIR(ls_solicitud)
						);

					if(at_parametros.getSolicitud() == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NIR_O_TURNO);

					lct_datos = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager).findAllMonitoreo(at_parametros);

					if(!CollectionUtils.isValidCollection(lct_datos))
					{
						ls_solicitud = lsd_DAO.findById(at_parametros.getSolicitud());

						if(ls_solicitud != null)
						{
							Trazabilidad lt_trazabilidad;

							lt_trazabilidad     = new Trazabilidad();
							lct_datos           = new ArrayList<Trazabilidad>();

							{
								TurnoHistoria lth_turnoHistoria;

								lth_turnoHistoria     = new TurnoHistoria();
								lthd_DAO              = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

								lth_turnoHistoria.setIdSolicitud(ls_solicitud.getIdSolicitud());

								lth_turnoHistoria = lthd_DAO.findBySolicitud(lth_turnoHistoria);

								if(lth_turnoHistoria != null)
								{
									BigDecimal lbd_etapa;

									lbd_etapa = lth_turnoHistoria.getIdEtapa();
									lt_trazabilidad.setTurnoHistoria(lth_turnoHistoria);

									if(NumericUtils.isValidBigDecimal(lbd_etapa))
									{
										String ls_turno;

										ls_turno = lth_turnoHistoria.getIdTurno();

										if(StringUtils.isValidString(ls_turno))
										{
											lt_turno.setIdTurno(ls_turno);
											lt_turno = ltd_DAO.findById(lt_turno);

											if(lt_turno != null)
											{
												{
													Long ll_etapaTurno;

													ll_etapaTurno = lt_turno.getIdEtapaActual();

													if(NumericUtils.isValidLong(ll_etapaTurno))
													{
														Etapa le_etapa;

														le_etapa = new Etapa();

														le_etapa.setIdEtapa(NumericUtils.getLong(ll_etapaTurno));
														le_etapa = DaoCreator.getEtapaDAO(ldm_manager).findById(
															    le_etapa
															);

														if(le_etapa != null)
														{
															Fases lf_fase;

															lf_fase = new Fases();

															lf_fase.setIdFase(le_etapa.getIdFase());
															lf_fase = DaoCreator.getFasesDAO(ldm_manager)
																	                .findById(lf_fase);

															if(lf_fase != null)
																lt_trazabilidad.setFases(lf_fase);
														}
													}
												}

												{
													String ls_procesoTurno;

													ls_procesoTurno = lt_turno.getIdProceso();

													if(StringUtils.isValidString(ls_procesoTurno))
													{
														Proceso lp_proceso;

														lp_proceso = new Proceso();

														lp_proceso.setIdProceso(ls_procesoTurno);

														lp_proceso = DaoCreator.getProcesoDAO(ldm_manager)
																                   .findById(lp_proceso);

														if(lp_proceso != null)
															lt_trazabilidad.setProceso(lp_proceso);
													}
												}
											}

											lt_trazabilidad.setTurno(lt_turno);
										}
									}
								}

								lt_trazabilidad.setSolicitud(ls_solicitud);

								lt_trazabilidad.setNirEtapa10(true);

								lct_datos.add(lt_trazabilidad);
							}
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarInfoBandejaConsultaTrazabilidad", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lct_datos;
	}

	/**
	 * Busca las opciones disponibles del menú para el usuario que inicia sesión.
	 *
	 * @param as_userId Id del usuario a utilizar como filtro en la busqueda
	 * @param as_idComponente Id del componente a utilizar como filtro en la busqueda
	 * @return Colección de opciones resultante de la busqueda
	 * @throws B2BException Si ocurre un error en base de datos o no se encuentran opciones para el usuario
	 */
	public synchronized Collection<Opcion> cargarOpcionesMenu(String as_userId, String as_idComponente)
	    throws B2BException
	{
		Collection<Opcion> lco_opciones;
		DAOManager         ldm_manager;

		lco_opciones     = null;
		ldm_manager      = DaoManagerFactory.getDAOManager();

		try
		{
			if(!StringUtils.isValidString(as_userId))
				throw new B2BException(com.bachue.snr.prosnr01.common.constants.ErrorKeys.USUARIO_INVALIDO);

			ConstantesDAO lcd_dao;
			lcd_dao = DaoCreator.getConstantesDAO(ldm_manager);

			Constantes lc_constante;
			lc_constante = lcd_dao.findById(ConstanteCommon.OPCIONES_POR_USUARIO_ENDPOINT);

			if(lc_constante != null)
			{
				Collection<TipoAcceso> lcta_tipoAcceso;
				lcta_tipoAcceso = AccesosPorUsuario.obtenerAccesosPorUsuario(
					    as_userId, as_idComponente, lc_constante.getCaracter()
					);

				if(CollectionUtils.isValidCollection(lcta_tipoAcceso))
				{
					lco_opciones = armarOpcionesComponente(lcta_tipoAcceso);

					if(!CollectionUtils.isValidCollection(lco_opciones))
						throw new B2BException(
						    com.bachue.snr.prosnr01.common.constants.ErrorKeys.ERROR_USUARIO_SIN_OPCIONES
						);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarOpcionesMenu", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lco_opciones;
	}

	/**
	 * Enviar al aprobador.
	 *
	 * @param as_idProceso
	 *                            de as id proceso
	 * @param as_idSubProceso
	 *                            de as id sub proceso
	 * @param ai_version
	 *                            de ai version
	 * @param as_userId
	 *                            de as user id
	 * @param as_remoteIp
	 *                            de as remote ip
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	public synchronized void enviarAlAprobador(
	    String as_idProceso, String as_idSubProceso, int ai_version, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubProceso) && (ai_version > 0))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManagerWorkflow();

			try
			{
				enviarAlAprobador(ldm_manager, as_idProceso, as_idSubProceso, ai_version, as_userId, as_remoteIp);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("enviarAlAprobador", lb2be_e);

				throw lb2be_e;
			}
			catch(IOException lioe_ioe)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("enviarAlAprobador", lioe_ioe);

				throw new B2BException(cs_IOEXCEPTION, lioe_ioe);
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los
	 * registros de la base de datos correspondiente a la tabla SDB_PGN_ETAPA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException
	 *                          Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<EtapaTrabajo> findAllEtapas()
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<EtapaTrabajo> lcet_etapas;

		ldm_manager     = DaoManagerFactory.getDAOManagerWorkflow();
		lcet_etapas     = new LinkedList<EtapaTrabajo>();

		try
		{
			lcet_etapas = DaoCreator.getEtapaTrabajoDAO(ldm_manager).findAllEtapas();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllEtapas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcet_etapas;
	}

	/**
	 * Método de transacciones con la base de datos para traer todos los registros
	 * de la tabla SDB_PGN_FASES.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException
	 *                          Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Fases> findAllFases()
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Fases> lcf_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_return      = null;

		try
		{
			lcf_return = DaoCreator.getFasesDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllFases", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_return;
	}

	/**
	 * Método de transacciones con la base de datos para traer todos los registros
	 * de la regla de negocio.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException
	 *                          Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<ReglaNegocio> findAllReglas()
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<ReglaNegocio> lcrn_reglas;

		ldm_manager     = DaoManagerFactory.getDAOManagerWorkflow();
		lcrn_reglas     = new LinkedList<ReglaNegocio>();

		try
		{
			lcrn_reglas = DaoCreator.getReglaNegocioDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllReglas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcrn_reglas;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los
	 * registros de la tabla SDB_ACC_SUBPROCESO.
	 *
	 * @return devuelve el <code>Collection</code> llena con los valores resultantes
	 *         de la consulta
	 * @throws B2BException
	 *                          Señala que se ha producido una excepción
	 */
	public synchronized Collection<SubProcesoVersionTrabajo> findAllSubProcesos()
	    throws B2BException
	{
		Collection<SubProcesoVersionTrabajo> lcspt_subProcesos;

		lcspt_subProcesos = null;

		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManagerWorkflow();

		try
		{
			lcspt_subProcesos = DaoCreator.getSubprocesoVersionTrabajoDAO(ldm_manager).findAllSubProcesos();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllSubProcesos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcspt_subProcesos;
	}

	/**
	 * Método para encontrar todos los registros de la base de datos para la tabla
	 * SDB_PGN_UNIDAD_TIEMPO_VENCIMIENTO.
	 *
	 * @return de collection
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	public synchronized Collection<UnidadTiempoVencimiento> findAllUnidadTiempoVencimiento()
	    throws B2BException
	{
		DAOManager                          ldm_manager;
		Collection<UnidadTiempoVencimiento> lcutv_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcutv_datos     = null;

		try
		{
			lcutv_datos = DaoCreator.getUnidadTiempoVencimientoDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllUnidadTiempoVencimiento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcutv_datos;
	}

	/**
	 * Método de transacciones por la base de datos para encontrar todos los
	 * registros de la tabla SDB_PGN_CONSTANTES que coincida con un ID_CONSTANTE
	 * específico.
	 *
	 * @param as_parametro
	 *                         id constante para consultar en la base de datos
	 * @return devuelve el valor Constantes
	 * @throws B2BException
	 *                          Señala que se ha producido una excepción
	 * @see Constantes
	 */
	public synchronized Constantes findConstantesById(String as_parametro)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Constantes lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerWorkflow();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getConstantesDAO(ldm_manager).findById(as_parametro);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findConstantesById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los
	 * registros de la tabla SDB_ACC_SUBPROCESO que su campo DEFINICION sea
	 * diferente de NULL.
	 *
	 * @return devuelve el <code>Collection</code> llena con los valores resultantes
	 *         de la consulta
	 * @throws B2BException
	 *                          Señala que se ha producido una excepción
	 * @see Usuario
	 */
	public synchronized String findNewDiagram()
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerWorkflow();
		ls_datos        = null;

		try
		{
			ls_datos = DaoCreator.getConstantesDAO(ldm_manager)
					                 .findXMLWorkflowById(ConstanteCommon.PLANTILLA_DIAGRAMA_BPMN);

			if(!StringUtils.isValidString(ls_datos))
			{
				Object[] loa_messageArgs = new String[1];
				loa_messageArgs[0] = StringUtils.getString(ConstanteCommon.PLANTILLA_DIAGRAMA_BPMN);

				throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
			}
		}
		catch(IOException le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findNewDiagram", le_e);

			throw new B2BException(cs_IOEXCEPTION, le_e);
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los
	 * registros de la tabla SDB_ACC_SUBPROCESO que su campo DEFINICION sea
	 * diferente de NULL.
	 *
	 * @return devuelve el <code>Collection</code> llena con los valores resultantes
	 *         de la consulta
	 * @throws B2BException
	 *                          Señala que se ha producido una excepción
	 * @see Usuario
	 */
	public synchronized Collection<ProcesoTrabajo> findProcesos()
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<ProcesoTrabajo> lcpt_procesos;

		ldm_manager       = DaoManagerFactory.getDAOManagerWorkflow();
		lcpt_procesos     = null;

		try
		{
			lcpt_procesos = DaoCreator.getProcesoTrabajoDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findProcesos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcpt_procesos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los
	 * registros de la tabla SDB_ACC_SUBPROCESO que su campo DEFINICION sea
	 * diferente de NULL.
	 *
	 * @return devuelve el <code>Collection</code> llena con los valores resultantes
	 *         de la consulta
	 * @throws B2BException
	 *                          Señala que se ha producido una excepción
	 * @see Usuario
	 */
	public synchronized Collection<ProcesoTrabajo> findProcesosAprobacion()
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<ProcesoTrabajo> lcpt_procesos;

		ldm_manager       = DaoManagerFactory.getDAOManagerWorkflow();
		lcpt_procesos     = null;

		try
		{
			lcpt_procesos = DaoCreator.getProcesoTrabajoDAO(ldm_manager).findAllAprobacion();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findProcesosAprobacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcpt_procesos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los
	 * registros de la tabla SDB_ACC_SUBPROCESO que coincidan con el idProceso y
	 * cuyo estado_flujo sea 'APROBADOR'.
	 *
	 * @param as_idProceso
	 *                         de as id proceso
	 * @return devuelve el <code>Collection</code> llena con los valores resultantes
	 *         de la consulta
	 * @throws B2BException
	 *                          Señala que se ha producido una excepción
	 */
	public synchronized Collection<SubProcesoTrabajo> findSubProcesosAprobacion(String as_idProceso)
	    throws B2BException
	{
		Collection<SubProcesoTrabajo> lcspt_subProcesos;

		lcspt_subProcesos = null;

		if(StringUtils.isValidString(as_idProceso))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManagerWorkflow();

			try
			{
				lcspt_subProcesos = DaoCreator.getSubprocesoTrabajoDAO(ldm_manager).findAllAprobacion(as_idProceso);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findSubProcesosAprobacion", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcspt_subProcesos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los
	 * registros de la tabla SDB_ACC_SUBPROCESO.
	 *
	 * @param as_idProceso
	 *                         de as id proceso
	 * @return devuelve el <code>Collection</code> llena con los valores resultantes
	 *         de la consulta
	 * @throws B2BException
	 *                          Señala que se ha producido una excepción
	 */
	public synchronized Collection<SubProcesoTrabajo> findSubprocesos(String as_idProceso)
	    throws B2BException
	{
		Collection<SubProcesoTrabajo> lcspt_subProcesos;

		lcspt_subProcesos = null;

		if(StringUtils.isValidString(as_idProceso))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManagerWorkflow();

			try
			{
				lcspt_subProcesos = DaoCreator.getSubprocesoTrabajoDAO(ldm_manager).findByProceso(as_idProceso);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findSubprocesos", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcspt_subProcesos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los
	 * registros de la tabla SDB_ACC_SUBPROCESO_VERSION_TRABAJO.
	 *
	 * @param as_idProceso
	 *                            de as id proceso
	 * @param as_idSubproceso
	 *                            de as id subproceso
	 * @return devuelve el <code>Collection</code> llena con los valores resultantes
	 *         de la consulta
	 * @throws B2BException
	 *                          Señala que se ha producido una excepción
	 */
	public synchronized Collection<SubProcesoVersionTrabajo> findVersiones(String as_idProceso, String as_idSubproceso)
	    throws B2BException
	{
		Collection<SubProcesoVersionTrabajo> lcspvt_versiones;

		lcspvt_versiones = null;

		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubproceso))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManagerWorkflow();

			try
			{
				lcspvt_versiones = DaoCreator.getSubprocesoVersionTrabajoDAO(ldm_manager)
						                         .findByProcesoSubproceso(as_idProceso, as_idSubproceso);
			}
			catch(IOException lioe_ioe)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findVersiones", lioe_ioe);

				throw new B2BException(cs_IOEXCEPTION, lioe_ioe);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findVersiones", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcspvt_versiones;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los
	 * registros de la tabla SDB_ACC_SUBPROCESO_VERSION_TRABAJO.
	 *
	 * @param as_idProceso
	 *                            de as id proceso
	 * @param as_idSubproceso
	 *                            de as id subproceso
	 * @return devuelve el <code>Collection</code> llena con los valores resultantes
	 *         de la consulta
	 * @throws B2BException
	 *                          Señala que se ha producido una excepción
	 */
	public synchronized Collection<SubProcesoVersionTrabajo> findVersionesAprobacion(
	    String as_idProceso, String as_idSubproceso
	)
	    throws B2BException
	{
		Collection<SubProcesoVersionTrabajo> lcspvt_versiones;

		lcspvt_versiones = null;

		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubproceso))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManagerWorkflow();

			try
			{
				lcspvt_versiones = DaoCreator.getSubprocesoVersionTrabajoDAO(ldm_manager)
						                         .findByAllAprobacion(as_idProceso, as_idSubproceso);
			}
			catch(IOException lioe_ioe)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findVersionesAprobacion", lioe_ioe);

				throw new B2BException(cs_IOEXCEPTION, lioe_ioe);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findVersionesAprobacion", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcspvt_versiones;
	}

	/**
	 * Imprimir.
	 *
	 * @param acpt_procesos
	 *                          de acpt procesos
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	public synchronized void imprimir(Collection<ProcesoTrabajo> acpt_procesos)
	    throws B2BException
	{
		if(CollectionUtils.isValidCollection(acpt_procesos))
		{
			for(ProcesoTrabajo lpt_proceso : acpt_procesos)
				imprimir(lpt_proceso);
		}
	}

	/**
	 * Obtener la propiedad caracter de una constante.
	 *
	 * @param as_idConstante Codigo de la constante a buscar
	 * @return Propiedad caracter de la constante identificada con as_idConstante
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public synchronized String obtenerCaracterConstante(String as_idConstante)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_constante;

		ldm_manager      = DaoManagerFactory.getDAOManagerWorkflow();
		ls_constante     = null;

		try
		{
			ls_constante = DaoCreator.getConstantesDAO(ldm_manager).findString(as_idConstante);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerCaracterConstante", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_constante;
	}

	/**
	 * Obtener el modelo del proceso para una solicitud y/o turno indicando, con
	 * colores, las etapas por las cuales ya ha pasado
	 *
	 * @param as_idSolicitud
	 *                           Código interno de la solicitud a obtener
	 *                           información
	 * @param as_idTurno
	 *                           Código interno del turno a obtener información
	 * @return XML con la descripcipon del modelo de proceso indicando, con colores,
	 *         las etapas por las cuales ya ha pasado
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	public synchronized String obtenerMonitoreo(String as_idSolicitud, String as_idTurno)
	    throws B2BException
	{
		String     ls_xmlModificado;
		String     ls_xmlOriginal;
		DAOManager ldm_manager;

		ldm_manager          = DaoManagerFactory.getDAOManagerWorkflow();
		ls_xmlModificado     = null;
		ls_xmlOriginal       = null;

		if(!StringUtils.isValidString(as_idSolicitud))
			throw new B2BException("No hay definicion de solicitud");

		try
		{
			boolean lb_turno;
			Integer li_versionSolicitud;
			String  ls_idProcesoSolicitud;
			String  ls_idSubprocesoSolicitud;

			lb_turno                     = false;
			li_versionSolicitud          = null;
			ls_idProcesoSolicitud        = null;
			ls_idSubprocesoSolicitud     = null;

			{
				Solicitud ls_solicitud;

				ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(as_idSolicitud);

				if(ls_solicitud != null)
				{
					li_versionSolicitud          = ls_solicitud.getVersionSubProceso();
					ls_idProcesoSolicitud        = ls_solicitud.getIdProceso();
					ls_idSubprocesoSolicitud     = ls_solicitud.getIdSubproceso();
				}
				else
					throw new B2BException("La solicitud no existe");
			}

			if(
			    (li_versionSolicitud != null) && StringUtils.isValidString(ls_idProcesoSolicitud)
				    && StringUtils.isValidString(ls_idSubprocesoSolicitud)
			)
			{
				if(StringUtils.isValidString(as_idTurno))
				{
					Turno lt_turno;

					lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(as_idTurno);

					if(lt_turno != null)
					{
						{
							String ls_idSolicitudTurno;

							ls_idSolicitudTurno = lt_turno.getIdSolicitud();

							if(!as_idSolicitud.equals(ls_idSolicitudTurno))
								throw new B2BException("El turno no pernenece a la solicitud");
						}

						{
							String ls_idProcesoTurno;
							String ls_idSubprocesoTurno;

							ls_idProcesoTurno        = lt_turno.getIdProceso();
							ls_idSubprocesoTurno     = lt_turno.getIdSubProceso();

							if(
							    !(ls_idProcesoSolicitud.equals(ls_idProcesoTurno)
								    && ls_idSubprocesoSolicitud.equals(ls_idSubprocesoTurno))
							)
								throw new B2BException(
								    "La identificación del modelo de procesos de la solicitud no "
								    + "coindice con la identificación del modelo de procesos del turno"
								);
						}

						lb_turno = true;
					}
					else
						throw new B2BException("El turno no existe");
				}

				{
					SubprocesoVersion lsv_version;

					lsv_version = DaoCreator.getSubprocesoVersionDAO(ldm_manager)
							                    .findById(
							    ls_idProcesoSolicitud, ls_idSubprocesoSolicitud,
							    NumericUtils.getLongWrapper(li_versionSolicitud), true
							);

					if(lsv_version != null)
						ls_xmlOriginal = lsv_version.getXml();
					else
						throw new B2BException("No existe registro de modelo de proceso");
				}

				/*
				 * TODO esta sección debe eliminarse cuando en las tablas reales esté cargado el
				 * modelo de proceso
				 */
				if(!StringUtils.isValidString(ls_xmlOriginal))
				{
					SubProcesoVersionTrabajo lsvt_version;

					lsvt_version = DaoCreator.getSubprocesoVersionTrabajoDAO(ldm_manager)
							                     .findById(
							    ls_idProcesoSolicitud, ls_idSubprocesoSolicitud,
							    NumericUtils.getInt(li_versionSolicitud)
							);

					if(lsvt_version != null)
						ls_xmlOriginal = lsvt_version.getXml();
					else
						throw new B2BException("No existe registro de modelo de proceso");
				}

				if(!StringUtils.isValidString(ls_xmlOriginal))
					throw new B2BException("No hay definición de modelo de proceso");
			}
			else
				throw new B2BException("No hay información sufiente para obtener el modelo de proceso");

			{
				Collection<TurnoHistoria>      lcth_trazabilidad;
				Map<BigDecimal, TurnoHistoria> lmbdth_etapa;
				Set<BigDecimal>                lsbd_asignado;
				Set<BigDecimal>                lsbd_porRepartir;
				Set<BigDecimal>                lsbd_terminadoVencido;
				Set<BigDecimal>                lsbd_terminadoEnTiempo;

				lcth_trazabilidad = lb_turno
					? DaoCreator.getTurnoHistoriaDAO(ldm_manager).findAllByIdSolicitudIdTurno(
					    as_idSolicitud, as_idTurno
					) : DaoCreator.getTurnoHistoriaDAO(ldm_manager).findAllByIdSolicitud(as_idSolicitud);

				if(CollectionUtils.isValidCollection(lcth_trazabilidad))
				{
					EtapaDAO           led_etapa;
					FestivoNacionalDAO lfnd_festivo;

					led_etapa                  = DaoCreator.getEtapaDAO(ldm_manager);
					lfnd_festivo               = DaoCreator.getFestivoNacionalDAO(ldm_manager);
					lmbdth_etapa               = new HashMap<BigDecimal, TurnoHistoria>();
					lsbd_asignado              = new HashSet<BigDecimal>();
					lsbd_porRepartir           = new HashSet<BigDecimal>();
					lsbd_terminadoEnTiempo     = new HashSet<BigDecimal>();
					lsbd_terminadoVencido      = new HashSet<BigDecimal>();

					for(TurnoHistoria lth_etapa : lcth_trazabilidad)
					{
						if(lth_etapa != null)
						{
							{
								Long   ll_versionTurnoHistoria;
								String ls_idProcesoTurnoHistoria;
								String ls_idSubprocesoTurnoHistoria;

								ll_versionTurnoHistoria          = lth_etapa.getVersionSubproceso();
								ls_idProcesoTurnoHistoria        = lth_etapa.getIdProceso();
								ls_idSubprocesoTurnoHistoria     = lth_etapa.getIdSubproceso();

								if(
								    !(ls_idProcesoSolicitud.equals(ls_idProcesoTurnoHistoria)
									    && ls_idSubprocesoSolicitud.equals(ls_idSubprocesoTurnoHistoria)
									    && li_versionSolicitud.equals(NumericUtils.getInteger(ll_versionTurnoHistoria)))
								)
									throw new B2BException(
									    "La identificación del modelo de procesos de la solicitud no "
									    + "coindice con la identificación del modelo de procesos de la trazabilidad"
									);
							}

							{
								BigDecimal lbd_idEtapa;
								String     ls_estadoActividad;

								lbd_idEtapa            = lth_etapa.getIdEtapa();
								ls_estadoActividad     = lth_etapa.getEstadoActividad();

								if(lbd_idEtapa != null)
								{
									lmbdth_etapa.put(lbd_idEtapa, lth_etapa);

									if(StringUtils.isValidString(ls_estadoActividad))
									{
										if(ls_estadoActividad.equals(EstadoCommon.POR_REPARTIR))
										{
											lsbd_porRepartir.add(lbd_idEtapa);

											lsbd_asignado.remove(lbd_idEtapa);
											lsbd_terminadoVencido.remove(lbd_idEtapa);
											lsbd_terminadoEnTiempo.remove(lbd_idEtapa);
										}
										else if(
										    ls_estadoActividad.equals(EstadoCommon.ASIGNADA)
											    || ls_estadoActividad.equals(EstadoCommon.AUTOMATICA)
											    || ls_estadoActividad.equals(EstadoCommon.BLOQUEADO)
											    || ls_estadoActividad.equals(EstadoCommon.ESPERA)
										)
										{
											if(!lsbd_porRepartir.contains(lbd_idEtapa))
												lsbd_asignado.add(lbd_idEtapa);

											lsbd_terminadoVencido.remove(lbd_idEtapa);
											lsbd_terminadoEnTiempo.remove(lbd_idEtapa);
										}
										else if(ls_estadoActividad.equals(EstadoCommon.TERMINADA))
										{
											if(
											    !lsbd_porRepartir.contains(lbd_idEtapa)
												    && !lsbd_asignado.contains(lbd_idEtapa)
											)
											{
												boolean lb_terminadoEnTiempo;
												Etapa   le_etapa;

												lb_terminadoEnTiempo     = true;
												le_etapa                 = led_etapa.findById(
													    NumericUtils.getLong(lbd_idEtapa)
													);

												if(le_etapa != null)
												{
													BigDecimal lbd_diasHabiles;

													lbd_diasHabiles = le_etapa.getDiasHabilesNormal();

													if(lbd_diasHabiles != null)
													{
														Date ld_fechaFinal;
														Date ld_fechaInicial;

														ld_fechaFinal       = lth_etapa.getFechaFinal();
														ld_fechaInicial     = lth_etapa.getFechaInicial();

														if(ld_fechaFinal == null)
															ld_fechaFinal = lth_etapa.getFechaModificacion();

														if(ld_fechaInicial == null)
															ld_fechaInicial = lth_etapa.getFechaReparto();

														if(ld_fechaInicial == null)
															ld_fechaInicial = lth_etapa.getFechaCreacion();

														if((ld_fechaInicial != null) && (ld_fechaFinal != null))
														{
															long             ll_dias;
															Collection<Date> lcd_festivos;

															ld_fechaFinal       = DateUtils.getCleanDate(ld_fechaFinal);
															ld_fechaInicial     = DateUtils.getCleanDate(
																    ld_fechaInicial
																);
															lcd_festivos        = lfnd_festivo.findAllEnRango(
																    ld_fechaInicial, ld_fechaFinal
																);

															ll_dias = DateUtils.obtenerDiferenciaEnDias(
																    ld_fechaInicial, ld_fechaFinal
																);

															if((ll_dias > 0L) && (lcd_festivos != null))
																ll_dias = ll_dias
																	- NumericUtils.getLong(lcd_festivos.size());

															lb_terminadoEnTiempo = ll_dias <= NumericUtils.getLong(
																	    lbd_diasHabiles
																	);
														}
													}
												}

												if(lb_terminadoEnTiempo)
													lsbd_terminadoEnTiempo.add(lbd_idEtapa);
												else
													lsbd_terminadoVencido.add(lbd_idEtapa);
											}
										}
									}
								}
							}
						}
					}

					{
						Document ld_bpmn;

						ld_bpmn = DomHelper.getDocument(ls_xmlOriginal);

						if(ld_bpmn != null)
						{
							Collection<Node> lcn_etapas;
							Element          le_proceso;
							Node             ln_definiciones;
							StringBuilder    lsb_asignado;
							StringBuilder    lsb_porRepartir;
							StringBuilder    lsb_terminadoVencido;
							StringBuilder    lsb_terminadoEnTiempo;

							lcn_etapas                = new ArrayList<Node>();
							le_proceso                = null;
							ln_definiciones           = ld_bpmn.getFirstChild();
							lsb_asignado              = new StringBuilder();
							lsb_porRepartir           = new StringBuilder();
							lsb_terminadoVencido      = new StringBuilder();
							lsb_terminadoEnTiempo     = new StringBuilder();

							if(ln_definiciones != null)
							{
								NodeList lnl_listaNodos;

								lnl_listaNodos = ln_definiciones.getChildNodes();

								if(lnl_listaNodos != null)
								{
									for(
									    int li_d = 0, li_longitudDefiniciones = lnl_listaNodos.getLength();
										    li_d < li_longitudDefiniciones; li_d++
									)
									{
										Node ln_hijoDefiniciones;

										ln_hijoDefiniciones = lnl_listaNodos.item(li_d);

										if(ln_hijoDefiniciones != null)
										{
											short ls_tipoHijoDefiniciones;

											ls_tipoHijoDefiniciones = ln_hijoDefiniciones.getNodeType();

											if(ls_tipoHijoDefiniciones == Node.ELEMENT_NODE)
											{
												String ls_nombreHijoDefiniciones;

												ls_nombreHijoDefiniciones = ln_hijoDefiniciones.getNodeName();

												if(
												    StringUtils.isValidString(ls_nombreHijoDefiniciones)
													    && ls_nombreHijoDefiniciones.endsWith(cs_ELEMENTO_PROCESO)
												)
												{
													NodeList lnl_contenidoProceso;

													lnl_contenidoProceso     = ln_hijoDefiniciones.getChildNodes();
													le_proceso               = (Element)ln_hijoDefiniciones;

													for(
													    int li_p = 0, li_longitudProceso = lnl_contenidoProceso
															    .getLength(); li_p < li_longitudProceso; li_p++
													)
													{
														Node ln_hijoProceso;

														ln_hijoProceso = lnl_contenidoProceso.item(li_p);

														if(ln_hijoProceso != null)
														{
															short ls_tipoHijoProceso;

															ls_tipoHijoProceso = ln_hijoProceso.getNodeType();

															if(ls_tipoHijoProceso == Node.ELEMENT_NODE)
															{
																String ls_nombreHijoProceso;

																ls_nombreHijoProceso = ln_hijoProceso.getNodeName();

																if(StringUtils.isValidString(ls_nombreHijoProceso))
																{
																	boolean lb_tarea;
																	boolean lb_tareaRecepcion;
																	boolean lb_tareaServicio;
																	boolean lb_tareaUsuario;

																	lb_tarea              = ls_nombreHijoProceso
																			.endsWith(cs_ELEMENTO_TAREA);
																	lb_tareaRecepcion     = ls_nombreHijoProceso
																			.endsWith(cs_ELEMENTO_TAREA_RECEPCION);
																	lb_tareaServicio      = ls_nombreHijoProceso
																			.endsWith(cs_ELEMENTO_TAREA_SERVICIO);
																	lb_tareaUsuario       = ls_nombreHijoProceso
																			.endsWith(cs_ELEMENTO_TAREA_USUARIO);

																	if(
																	    lb_tarea || lb_tareaRecepcion
																		    || lb_tareaServicio || lb_tareaUsuario
																	)
																		lcn_etapas.add(ln_hijoProceso);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}

							if(CollectionUtils.isValidCollection(lcn_etapas))
							{
								Iterator<Node> lin_etapas;

								lin_etapas = lcn_etapas.iterator();

								while(lin_etapas.hasNext())
								{
									Node ln_etapa;

									ln_etapa = lin_etapas.next();

									if(ln_etapa != null)
									{
										BigDecimal   lbd_idEtapa;
										String       ls_id;
										NamedNodeMap lnnm_atributos;

										lbd_idEtapa        = null;
										ls_id              = null;
										lnnm_atributos     = ln_etapa.getAttributes();

										if(lnnm_atributos != null)
										{
											for(
											    int li_e = 0, li_cantidadAjustados = 0, li_longitud = lnnm_atributos
													    .getLength(); li_e < li_longitud; li_e++
											)
											{
												Node ln_atributo;

												ln_atributo = lnnm_atributos.item(li_e);

												if(ln_atributo != null)
												{
													String ls_nombreAtributo;

													ls_nombreAtributo = ln_atributo.getNodeName();

													if(StringUtils.isValidString(ls_nombreAtributo))
													{
														switch(ls_nombreAtributo)
														{
															case cs_ATRIBUTO_PERSONALIZADO_TAREA_ETAPA:
																lbd_idEtapa = NumericUtils.getBigDecimal(
																	    ln_atributo.getNodeValue()
																	);
																li_cantidadAjustados++;

																break;

															case cs_ATRIBUTO_GENERAL_ID:
																ls_id = ln_atributo.getNodeValue();
																li_cantidadAjustados++;

																break;

															default:
																break;
														}

														if(li_cantidadAjustados >= 2)
															li_e = li_longitud + 1;
													}
												}
											}
										}

										if((lbd_idEtapa != null) && StringUtils.isValidString(ls_id))
										{
											{
												TurnoHistoria lth_etapa;

												lth_etapa = lmbdth_etapa.get(lbd_idEtapa);

												if(lth_etapa != null)
												{
													Element le_etapa;

													le_etapa = (Element)ln_etapa;

													le_etapa.setAttribute(
													    cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_ESTADO_ACTIVIDAD,
													    lth_etapa.getEstadoActividad()
													);

													le_etapa.setAttribute(
													    cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_FECHA_CREACION,
													    StringUtils.getString(
													        lth_etapa.getFechaCreacion(),
													        FormatoFechaCommon.DIA_MES_ANIO
													    )
													);

													le_etapa.setAttribute(
													    cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_FECHA_FINAL,
													    StringUtils.getString(
													        lth_etapa.getFechaFinal(), FormatoFechaCommon.DIA_MES_ANIO
													    )
													);

													le_etapa.setAttribute(
													    cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_FECHA_INICIAL,
													    StringUtils.getString(
													        lth_etapa.getFechaInicial(), FormatoFechaCommon.DIA_MES_ANIO
													    )
													);

													le_etapa.setAttribute(
													    cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_FECHA_REPARTO,
													    StringUtils.getString(
													        lth_etapa.getFechaReparto(), FormatoFechaCommon.DIA_MES_ANIO
													    )
													);

													le_etapa.setAttribute(
													    cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_ID_USUARIO,
													    lth_etapa.getIdUsuario()
													);

													le_etapa.setAttribute(
													    cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_INDICADOR_DEVOLUCION,
													    lth_etapa.getIndicadorDevolucion()
													);

													le_etapa.setAttribute(
													    cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_OBSERVACIONES,
													    lth_etapa.getObservaciones()
													);

													{
														Long ll_tmp;

														ll_tmp = lth_etapa.getIdMotivo();

														if(ll_tmp != null)
															le_etapa.setAttribute(
															    cs_ATRIBUTO_PERSONALIZADO_MONITOREO_ETAPA_MOTIVO_TRAMITE,
															    ll_tmp.toString()
															);
													}
												}
											}

											if(lsbd_porRepartir.contains(lbd_idEtapa))
											{
												if(lsb_porRepartir.length() > 0)
													lsb_porRepartir.append(",");

												lsb_porRepartir.append(ls_id);
											}
											else if(lsbd_asignado.contains(lbd_idEtapa))
											{
												if(lsb_asignado.length() > 0)
													lsb_asignado.append(",");

												lsb_asignado.append(ls_id);
											}
											else if(lsbd_terminadoEnTiempo.contains(lbd_idEtapa))
											{
												if(lsb_terminadoEnTiempo.length() > 0)
													lsb_terminadoEnTiempo.append(",");

												lsb_terminadoEnTiempo.append(ls_id);
											}
											else if(lsbd_terminadoVencido.contains(lbd_idEtapa))
											{
												if(lsb_terminadoVencido.length() > 0)
													lsb_terminadoVencido.append(",");

												lsb_terminadoVencido.append(ls_id);
											}
										}
									}
								}
							}

							if(le_proceso != null)
							{
								if(lsb_asignado.length() > 0)
									le_proceso.setAttribute(
									    cs_ATRIBUTO_PERSONALIZADO_MONITOREO_PROCESO_AZUL, lsb_asignado.toString()
									);

								if(lsb_porRepartir.length() > 0)
									le_proceso.setAttribute(
									    cs_ATRIBUTO_PERSONALIZADO_MONITOREO_PROCESO_AMARILLO, lsb_porRepartir.toString()
									);

								if(lsb_terminadoEnTiempo.length() > 0)
									le_proceso.setAttribute(
									    cs_ATRIBUTO_PERSONALIZADO_MONITOREO_PROCESO_VERDE,
									    lsb_terminadoEnTiempo.toString()
									);

								if(lsb_terminadoVencido.length() > 0)
									le_proceso.setAttribute(
									    cs_ATRIBUTO_PERSONALIZADO_MONITOREO_PROCESO_ROJO,
									    lsb_terminadoVencido.toString()
									);
							}

							{
								StringWriter       lsw_bpmn;
								Transformer        lt_bpmn;
								TransformerFactory ltf_bpmn;

								lsw_bpmn     = new StringWriter();
								ltf_bpmn     = TransformerFactory.newInstance();
								
								ltf_bpmn.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
								ltf_bpmn.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
								ltf_bpmn.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

								lt_bpmn = ltf_bpmn.newTransformer();

								lt_bpmn.setOutputProperty(OutputKeys.INDENT, "yes");

								lt_bpmn.transform(new DOMSource(ld_bpmn), new StreamResult(lsw_bpmn));

								ls_xmlModificado = lsw_bpmn.toString();
							}
						}
					}
				}
				else
					throw new B2BException("No hay registros de trazabilidad");
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerMonitoreo", lb2be_e);

			throw lb2be_e;
		}
		catch(TransformerException lte_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerMonitoreo", lte_e);

			throw new B2BException(cs_IOEXCEPTION, lte_e);
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_xmlModificado;
	}

	/**
	 * Método de transacciones con la base de datos para cambiar el campo
	 * ESTADO_FLUJO de todos los registros de la tabla
	 * SDB_ACC_SUBPROCESO_VERSION_TRABAJO asociados a un idProceso, idSubproceso y
	 * version.
	 *
	 * @param as_idProceso
	 *                            de as id proceso
	 * @param as_idSubProceso
	 *                            de as id sub proceso
	 * @param ai_version
	 *                            de ai version
	 * @param as_userId
	 *                            de as user id
	 * @param as_remoteIp
	 *                            de as remote ip
	 * @throws B2BException
	 *                          Señala que se ha producido una excepción
	 */
	public synchronized void retornarAEstadoDevuelto(
	    String as_idProceso, String as_idSubProceso, int ai_version, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubProceso) && (ai_version > 0))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManagerWorkflow();

			try
			{
				SubProcesoVersionTrabajoDAO lspvtd_DAO;
				SubProcesoVersionTrabajo    lspvt_subProcesoVersion;

				lspvtd_DAO                  = DaoCreator.getSubprocesoVersionTrabajoDAO(ldm_manager);
				lspvt_subProcesoVersion     = lspvtd_DAO.findById(as_idProceso, as_idSubProceso, ai_version);

				if(lspvt_subProcesoVersion != null)
				{
					lspvt_subProcesoVersion.setEstadoFlujo(EstadoFlujoCommon.DEVUELTO);
					lspvt_subProcesoVersion.setIdUsuarioModificacion(as_userId);
					lspvt_subProcesoVersion.setIdUsuarioModificacion(as_remoteIp);
					lspvtd_DAO.update(lspvt_subProcesoVersion);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("retornarAEstadoDevuelto", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Analizar contenido compuerta.
	 *
	 * @param apt_proceso
	 *                                   de apt proceso
	 * @param ac_compuerta
	 *                                   de ac compuerta
	 * @param ab_exclusiva
	 *                                   de ab exclusiva
	 * @param anl_contenidoCompuerta
	 *                                   de anl contenido compuerta
	 * @param as_idUsuarioCreacion
	 *                                   de as id usuario creacion
	 * @param as_ipCreacion
	 *                                   de as ip creacion
	 * @return de compuerta
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private Compuerta analizarContenidoCompuerta(
	    ProcesoTrabajo apt_proceso, Compuerta ac_compuerta, boolean ab_exclusiva, NodeList anl_contenidoCompuerta,
	    String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		if(ac_compuerta == null)
			throw new B2BException("No hay definicion de compuerta");

		if(anl_contenidoCompuerta != null)
		{
			boolean lb_flujoDefecto;
			boolean lb_flujoDefectoAjustado;
			String  ls_flujoDefecto;

			lb_flujoDefectoAjustado     = false;
			ls_flujoDefecto             = (ab_exclusiva && ac_compuerta instanceof CompuertaExclusiva)
				? ((CompuertaExclusiva)ac_compuerta).getFlujoDefecto() : null;
			lb_flujoDefecto             = StringUtils.isValidString(ls_flujoDefecto);

			for(int li_i = 0, li_longitud = anl_contenidoCompuerta.getLength(); li_i < li_longitud; li_i++)
			{
				Node ln_nodo;

				ln_nodo = anl_contenidoCompuerta.item(li_i);

				if(ln_nodo != null)
				{
					short ls_tipoNodo;

					ls_tipoNodo = ln_nodo.getNodeType();

					if(ls_tipoNodo == Node.ELEMENT_NODE)
					{
						String ls_nombreNodo;

						ls_nombreNodo = ln_nodo.getNodeName();

						if(StringUtils.isValidString(ls_nombreNodo))
						{
							boolean              lb_entrada;
							boolean              lb_salida;
							MotivoTramiteTrabajo lmtt_flujo;

							lb_entrada     = false;
							lb_salida      = false;
							lmtt_flujo     = null;

							if(ls_nombreNodo.endsWith(cs_ELEMENTO_ENTRADA))
							{
								lb_entrada     = true;
								lmtt_flujo     = analizarElementoFlujoEntrada(
									    apt_proceso, ln_nodo, as_idUsuarioCreacion, as_ipCreacion
									);
							}
							else if(ls_nombreNodo.endsWith(cs_ELEMENTO_SALIDA))
							{
								lb_salida      = true;
								lmtt_flujo     = analizarElementoFlujoSalida(
									    apt_proceso, ln_nodo, as_idUsuarioCreacion, as_ipCreacion
									);
							}

							if(lmtt_flujo != null)
							{
								if(lb_entrada)
									ac_compuerta.adicionarFlujoEntrada(lmtt_flujo);

								if(lb_salida)
								{
									if(lb_flujoDefecto && lmtt_flujo.getId().equals(ls_flujoDefecto))
									{
										lmtt_flujo.setFlujoDefecto(true);
										lb_flujoDefectoAjustado = true;
									}

									ac_compuerta.adicionarFlujoSalida(lmtt_flujo);
								}
							}
						}
					}
				}
			}

			if(lb_flujoDefecto && !lb_flujoDefectoAjustado)
				throw new B2BException("Flujo por defecto no existe");
		}

		return ac_compuerta;
	}

	/**
	 * Analizar contenido evento.
	 *
	 * @param apt_proceso
	 *                                 de apt proceso
	 * @param ae_evento
	 *                                 de ae evento
	 * @param anl_contenidoEvento
	 *                                 de anl contenido evento
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de evento
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private Evento analizarContenidoEvento(
	    ProcesoTrabajo apt_proceso, Evento ae_evento, NodeList anl_contenidoEvento, String as_idUsuarioCreacion,
	    String as_ipCreacion
	)
	    throws B2BException
	{
		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		if(ae_evento == null)
			throw new B2BException("No hay definicion de evento");

		if(anl_contenidoEvento != null)
		{
			for(int li_i = 0, li_longitud = anl_contenidoEvento.getLength(); li_i < li_longitud; li_i++)
			{
				Node ln_nodo;

				ln_nodo = anl_contenidoEvento.item(li_i);

				if(ln_nodo != null)
				{
					short ls_tipoNodo;

					ls_tipoNodo = ln_nodo.getNodeType();

					if(ls_tipoNodo == Node.ELEMENT_NODE)
					{
						String ls_nombreNodo;

						ls_nombreNodo = ln_nodo.getNodeName();

						if(StringUtils.isValidString(ls_nombreNodo))
						{
							boolean              lb_entrada;
							boolean              lb_salida;
							MotivoTramiteTrabajo lmtt_flujo;

							lb_entrada     = false;
							lb_salida      = false;
							lmtt_flujo     = null;

							if(ls_nombreNodo.endsWith(cs_ELEMENTO_ENTRADA))
							{
								lb_entrada     = true;
								lmtt_flujo     = analizarElementoFlujoEntrada(
									    apt_proceso, ln_nodo, as_idUsuarioCreacion, as_ipCreacion
									);
							}
							else if(ls_nombreNodo.endsWith(cs_ELEMENTO_SALIDA))
							{
								lb_salida      = true;
								lmtt_flujo     = analizarElementoFlujoSalida(
									    apt_proceso, ln_nodo, as_idUsuarioCreacion, as_ipCreacion
									);
							}

							if(lmtt_flujo != null)
							{
								if(lb_entrada)
									ae_evento.adicionarFlujoEntrada(lmtt_flujo);

								if(lb_salida)
									ae_evento.adicionarFlujoSalida(lmtt_flujo);
							}
						}
					}
				}
			}
		}

		return ae_evento;
	}

	/**
	 * Analizar contenido proceso.
	 *
	 * @param apt_proceso
	 *                                 de apt proceso
	 * @param anl_contenidoProceso
	 *                                 de anl contenido proceso
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de proceso trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private ProcesoTrabajo analizarContenidoProceso(
	    ProcesoTrabajo apt_proceso, NodeList anl_contenidoProceso, String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		if(anl_contenidoProceso != null)
		{
			Collection<Node> lcn_compuertas;
			Collection<Node> lcn_etapas;
			Collection<Node> lcn_eventos;
			Collection<Node> lcn_eventosFrontera;
			Collection<Node> lcn_flujos;

			lcn_compuertas          = new ArrayList<Node>();
			lcn_etapas              = new ArrayList<Node>();
			lcn_eventos             = new ArrayList<Node>();
			lcn_eventosFrontera     = new ArrayList<Node>();
			lcn_flujos              = new ArrayList<Node>();

			for(int li_i = 0, li_longitud = anl_contenidoProceso.getLength(); li_i < li_longitud; li_i++)
			{
				Node ln_nodo;

				ln_nodo = anl_contenidoProceso.item(li_i);

				if(ln_nodo != null)
				{
					short ls_tipoNodo;

					ls_tipoNodo = ln_nodo.getNodeType();

					if(ls_tipoNodo == Node.ELEMENT_NODE)
					{
						String ls_nombreNodo;

						ls_nombreNodo = ln_nodo.getNodeName();

						if(StringUtils.isValidString(ls_nombreNodo))
						{
							boolean lb_compuertaExlusiva;
							boolean lb_compuertaParalela;
							boolean lb_eventoFinEtapa;
							boolean lb_eventoFinProceso;
							boolean lb_eventoInicioProceso;
							boolean lb_tarea;
							boolean lb_tareaRecepcion;
							boolean lb_tareaServicio;
							boolean lb_tareaUsuario;

							lb_compuertaExlusiva       = ls_nombreNodo.endsWith(cs_ELEMENTO_COMPUERTA_EXCLUSIVA);
							lb_compuertaParalela       = ls_nombreNodo.endsWith(cs_ELEMENTO_COMPUERTA_PARALELA);
							lb_eventoFinEtapa          = ls_nombreNodo.endsWith(cs_ELEMENTO_EVENTO_ETAPA_FIN);
							lb_eventoFinProceso        = ls_nombreNodo.endsWith(cs_ELEMENTO_EVENTO_PROCESO_FIN);
							lb_eventoInicioProceso     = ls_nombreNodo.endsWith(cs_ELEMENTO_EVENTO_PROCESO_INICIO);
							lb_tarea                   = ls_nombreNodo.endsWith(cs_ELEMENTO_TAREA);
							lb_tareaRecepcion          = ls_nombreNodo.endsWith(cs_ELEMENTO_TAREA_RECEPCION);
							lb_tareaServicio           = ls_nombreNodo.endsWith(cs_ELEMENTO_TAREA_SERVICIO);
							lb_tareaUsuario            = ls_nombreNodo.endsWith(cs_ELEMENTO_TAREA_USUARIO);

							if(ls_nombreNodo.endsWith(cs_ELEMENTO_EVENTO_PROCESO_FRONTERA))
								lcn_eventosFrontera.add(ln_nodo);
							else if(ls_nombreNodo.endsWith(cs_ELEMENTO_FLUJO))
								lcn_flujos.add(ln_nodo);
							else if(lb_compuertaExlusiva || lb_compuertaParalela)
								lcn_compuertas.add(ln_nodo);
							else if(lb_eventoFinEtapa || lb_eventoFinProceso || lb_eventoInicioProceso)
								lcn_eventos.add(ln_nodo);
							else if(lb_tarea || lb_tareaRecepcion || lb_tareaServicio || lb_tareaUsuario)
								lcn_etapas.add(ln_nodo);
						}
					}
				}
			}

			if(CollectionUtils.isValidCollection(lcn_etapas))
			{
				for(Node ln_nodo : lcn_etapas)
				{
					if(ln_nodo != null)
					{
						boolean      lb_tareaRecepcion;
						boolean      lb_tareaServicio;
						boolean      lb_tareaUsuario;
						EtapaTrabajo let_etapa;
						String       ls_nombreNodo;

						ls_nombreNodo         = ln_nodo.getNodeName();
						lb_tareaRecepcion     = ls_nombreNodo.endsWith(cs_ELEMENTO_TAREA_RECEPCION);
						lb_tareaServicio      = ls_nombreNodo.endsWith(cs_ELEMENTO_TAREA_SERVICIO);
						lb_tareaUsuario       = ls_nombreNodo.endsWith(cs_ELEMENTO_TAREA_USUARIO);

						let_etapa = analizarElementoTarea(
							    apt_proceso, ln_nodo, lb_tareaUsuario, lb_tareaRecepcion, lb_tareaServicio,
							    as_idUsuarioCreacion, as_ipCreacion
							);

						if(let_etapa != null)
						{
							apt_proceso.adicionarEtapa(let_etapa);

							apt_proceso.adicionarFlujos(let_etapa.getFlujosEntrada());
							apt_proceso.adicionarFlujos(let_etapa.getFlujosSalida());
						}
					}
				}
			}

			if(CollectionUtils.isValidCollection(lcn_eventos))
			{
				for(Node ln_nodo : lcn_eventos)
				{
					if(ln_nodo != null)
					{
						boolean lb_eventoFinEtapa;
						boolean lb_eventoFinProceso;
						boolean lb_eventoInicioProceo;
						Evento  le_evento;
						String  ls_nombreNodo;

						ls_nombreNodo             = ln_nodo.getNodeName();
						lb_eventoFinEtapa         = ls_nombreNodo.endsWith(cs_ELEMENTO_EVENTO_ETAPA_FIN);
						lb_eventoFinProceso       = ls_nombreNodo.endsWith(cs_ELEMENTO_EVENTO_PROCESO_FIN);
						lb_eventoInicioProceo     = ls_nombreNodo.endsWith(cs_ELEMENTO_EVENTO_PROCESO_INICIO);
						le_evento                 = lb_eventoFinProceso
							? analizarElementoEventoFinProceso(
							    apt_proceso, ln_nodo, as_idUsuarioCreacion, as_ipCreacion
							)
							: (lb_eventoInicioProceo
							? analizarElementoEventoInicioProceso(
							    apt_proceso, ln_nodo, as_idUsuarioCreacion, as_ipCreacion
							)
							: (lb_eventoFinEtapa
							? analizarElementoEventoFinEtapa(apt_proceso, ln_nodo, as_idUsuarioCreacion, as_ipCreacion)
							: null));

						if(le_evento != null)
							apt_proceso.adicionarEvento(le_evento);
					}
				}
			}

			if(CollectionUtils.isValidCollection(lcn_compuertas))
			{
				for(Node ln_nodo : lcn_compuertas)
				{
					if(ln_nodo != null)
					{
						boolean   lb_compuertaExlusiva;
						boolean   lb_compuertaParalela;
						Compuerta lc_compuerta;
						String    ls_nombreNodo;

						ls_nombreNodo            = ln_nodo.getNodeName();
						lb_compuertaExlusiva     = ls_nombreNodo.endsWith(cs_ELEMENTO_COMPUERTA_EXCLUSIVA);
						lb_compuertaParalela     = ls_nombreNodo.endsWith(cs_ELEMENTO_COMPUERTA_PARALELA);
						lc_compuerta             = lb_compuertaExlusiva
							? analizarElementoCompuertaExclusiva(
							    apt_proceso, ln_nodo, as_idUsuarioCreacion, as_ipCreacion
							)
							: (lb_compuertaParalela
							? analizarElementoCompuertaParalela(
							    apt_proceso, ln_nodo, as_idUsuarioCreacion, as_ipCreacion
							) : null);

						if(lc_compuerta != null)
						{
							apt_proceso.adicionarCompuerta(lc_compuerta);

							apt_proceso.adicionarFlujos(lc_compuerta.getFlujosEntrada());
							apt_proceso.adicionarFlujos(lc_compuerta.getFlujosSalida());
						}
					}
				}
			}

			if(CollectionUtils.isValidCollection(lcn_eventosFrontera))
			{
				for(Node ln_nodo : lcn_eventosFrontera)
				{
					if(ln_nodo != null)
						analizarElementoEventoFrontera(apt_proceso, ln_nodo, as_idUsuarioCreacion, as_ipCreacion);
				}
			}

			if(CollectionUtils.isValidCollection(lcn_flujos))
			{
				for(Node ln_nodo : lcn_flujos)
				{
					if(ln_nodo != null)
						apt_proceso.adicionarFlujo(
						    analizarElementoSecuenciaFlujo(apt_proceso, ln_nodo, as_idUsuarioCreacion, as_ipCreacion)
						);
				}
			}
		}

		return apt_proceso;
	}

	/**
	 * Analizar contenido tarea.
	 *
	 * @param apt_proceso
	 *                                 de apt proceso
	 * @param aet_etapa
	 *                                 de aet etapa
	 * @param anl_contenidoEtapa
	 *                                 de anl contenido etapa
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de etapa trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private EtapaTrabajo analizarContenidoTarea(
	    ProcesoTrabajo apt_proceso, EtapaTrabajo aet_etapa, NodeList anl_contenidoEtapa, String as_idUsuarioCreacion,
	    String as_ipCreacion
	)
	    throws B2BException
	{
		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		if(aet_etapa == null)
			throw new B2BException("No hay definicion de etapa");

		if(anl_contenidoEtapa != null)
		{
			for(int li_i = 0, li_longitud = anl_contenidoEtapa.getLength(); li_i < li_longitud; li_i++)
			{
				Node ln_nodo;

				ln_nodo = anl_contenidoEtapa.item(li_i);

				if(ln_nodo != null)
				{
					short ls_tipoNodo;

					ls_tipoNodo = ln_nodo.getNodeType();

					if(ls_tipoNodo == Node.ELEMENT_NODE)
					{
						String ls_nombreNodo;

						ls_nombreNodo = ln_nodo.getNodeName();

						if(StringUtils.isValidString(ls_nombreNodo))
						{
							boolean              lb_entrada;
							boolean              lb_salida;
							MotivoTramiteTrabajo lmtt_flujo;

							lb_entrada     = false;
							lb_salida      = false;
							lmtt_flujo     = null;

							if(ls_nombreNodo.endsWith(cs_ELEMENTO_ENTRADA))
							{
								lb_entrada     = true;
								lmtt_flujo     = analizarElementoFlujoEntrada(
									    apt_proceso, ln_nodo, as_idUsuarioCreacion, as_ipCreacion
									);
							}
							else if(ls_nombreNodo.endsWith(cs_ELEMENTO_SALIDA))
							{
								lb_salida      = true;
								lmtt_flujo     = analizarElementoFlujoSalida(
									    apt_proceso, ln_nodo, as_idUsuarioCreacion, as_ipCreacion
									);
							}

							if(lmtt_flujo != null)
							{
								if(lb_entrada)
								{
									lmtt_flujo.setDestino(aet_etapa);

									aet_etapa.adicionarFlujoEntrada(lmtt_flujo);
								}

								if(lb_salida)
								{
									lmtt_flujo.setOrigen(aet_etapa);

									aet_etapa.adicionarFlujoSalida(lmtt_flujo);
								}
							}
						}
					}
				}
			}
		}

		return aet_etapa;
	}

	/**
	 * Analizar elemento compuerta.
	 *
	 * @param apt_proceso
	 *                                 de apt proceso
	 * @param an_compuerta
	 *                                 de an compuerta
	 * @param ab_exclusiva
	 *                                 de ab exclusiva
	 * @param ab_paralela
	 *                                 de ab paralela
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de compuerta
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private Compuerta analizarElementoCompuerta(
	    ProcesoTrabajo apt_proceso, Node an_compuerta, boolean ab_exclusiva, boolean ab_paralela,
	    String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		boolean   lb_compuerta;
		Compuerta lc_compuerta;

		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		if(!(ab_exclusiva ^ ab_paralela))
			throw new B2BException("Tipo inválido de Compuerta");

		lb_compuerta     = an_compuerta != null;
		lc_compuerta     = lb_compuerta
			? (ab_exclusiva ? new CompuertaExclusiva() : (ab_paralela ? new CompuertaParalela() : null)) : null;

		if(lb_compuerta)
		{
			NamedNodeMap lnnm_atributos;

			lnnm_atributos = an_compuerta.getAttributes();

			if(lnnm_atributos != null)
			{
				int li_flujosDefecto;

				li_flujosDefecto = 0;

				for(
				    int li_i = 0, li_cantidadAjustados = 0, li_longitud = lnnm_atributos.getLength();
					    li_i < li_longitud; li_i++
				)
				{
					Node ln_atributo;

					ln_atributo = lnnm_atributos.item(li_i);

					if(ln_atributo != null)
					{
						String ls_nombreAtributo;

						ls_nombreAtributo = ln_atributo.getNodeName();

						if(StringUtils.isValidString(ls_nombreAtributo))
						{
							if(ls_nombreAtributo.equals(cs_ATRIBUTO_GENERAL_ID))
							{
								lc_compuerta.setId(ln_atributo.getNodeValue());

								li_cantidadAjustados++;
							}
							else if(ls_nombreAtributo.equals(cs_ATRIBUTO_COMPUERTA_FLUJO_DEFECTO))
							{
								li_flujosDefecto++;

								if(ab_exclusiva && lc_compuerta instanceof CompuertaExclusiva)
									((CompuertaExclusiva)lc_compuerta).setFlujoDefecto(ln_atributo.getNodeValue());
							}

							if(li_cantidadAjustados >= 1)
								li_i = li_longitud + 1;
						}
					}
				}

				if(li_flujosDefecto > 1)
					throw new B2BException("Mas de un flujo de salida");
			}

			if(!StringUtils.isValidString(lc_compuerta.getId()))
				lc_compuerta = null;
			else
				analizarContenidoCompuerta(
				    apt_proceso, lc_compuerta, ab_exclusiva, an_compuerta.getChildNodes(), as_idUsuarioCreacion,
				    as_ipCreacion
				);
		}

		return lc_compuerta;
	}

	/**
	 * Analizar elemento compuerta exclusiva.
	 *
	 * @param apt_proceso
	 *                                  de apt proceso
	 * @param an_compuertaExclusiva
	 *                                  de an compuerta exclusiva
	 * @param as_idUsuarioCreacion
	 *                                  de as id usuario creacion
	 * @param as_ipCreacion
	 *                                  de as ip creacion
	 * @return de compuerta
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private Compuerta analizarElementoCompuertaExclusiva(
	    ProcesoTrabajo apt_proceso, Node an_compuertaExclusiva, String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		return analizarElementoCompuerta(
		    apt_proceso, an_compuertaExclusiva, true, false, as_idUsuarioCreacion, as_ipCreacion
		);
	}

	/**
	 * Analizar elemento compuerta paralela.
	 *
	 * @param apt_proceso
	 *                                 de apt proceso
	 * @param an_compuertaParalela
	 *                                 de an compuerta paralela
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de compuerta
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private Compuerta analizarElementoCompuertaParalela(
	    ProcesoTrabajo apt_proceso, Node an_compuertaParalela, String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		return analizarElementoCompuerta(
		    apt_proceso, an_compuertaParalela, false, true, as_idUsuarioCreacion, as_ipCreacion
		);
	}

	/**
	 * Analizar elemento evento.
	 *
	 * @param apt_proceso
	 *                                 de apt proceso
	 * @param an_evento
	 *                                 de an evento
	 * @param ab_inicioProceso
	 *                                 de ab inicio proceso
	 * @param ab_finProceso
	 *                                 de ab fin proceso
	 * @param ab_finEtapa
	 *                                 de ab fin etapa
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de evento
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private Evento analizarElementoEvento(
	    ProcesoTrabajo apt_proceso, Node an_evento, boolean ab_inicioProceso, boolean ab_finProceso, boolean ab_finEtapa,
	    String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		boolean lb_evento;
		Evento  le_evento;

		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		if(!(ab_inicioProceso ^ ab_finProceso ^ ab_finEtapa))
			throw new B2BException("Tipo inválido de Evento");

		lb_evento     = an_evento != null;
		le_evento     = lb_evento
			? (ab_inicioProceso ? new EventoInicioProceso()
			                    : (ab_finProceso ? new EventoFinProceso() : (ab_finEtapa ? new EventoFinEtapa() : null)))
			: null;

		if(lb_evento)
		{
			NamedNodeMap lnnm_atributos;

			lnnm_atributos = an_evento.getAttributes();

			if(lnnm_atributos != null)
			{
				for(
				    int li_i = 0, li_cantidadAjustados = 0, li_longitud = lnnm_atributos.getLength();
					    li_i < li_longitud; li_i++
				)
				{
					Node ln_atributo;

					ln_atributo = lnnm_atributos.item(li_i);

					if(ln_atributo != null)
					{
						String ls_nombreAtributo;

						ls_nombreAtributo = ln_atributo.getNodeName();

						if(StringUtils.isValidString(ls_nombreAtributo))
						{
							if(ls_nombreAtributo.equals(cs_ATRIBUTO_GENERAL_ID))
							{
								le_evento.setId(ln_atributo.getNodeValue());

								li_cantidadAjustados++;
							}

							if(li_cantidadAjustados >= 1)
								li_i = li_longitud + 1;
						}
					}
				}
			}

			le_evento = StringUtils.isValidString(le_evento.getId())
				? analizarContenidoEvento(
				    apt_proceso, le_evento, an_evento.getChildNodes(), as_idUsuarioCreacion, as_ipCreacion
				) : null;
		}

		return le_evento;
	}

	/**
	 * Analizar elemento evento fin etapa.
	 *
	 * @param apt_proceso
	 *                                 de apt proceso
	 * @param an_eventoFin
	 *                                 de an evento fin
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de evento
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private Evento analizarElementoEventoFinEtapa(
	    ProcesoTrabajo apt_proceso, Node an_eventoFin, String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		return analizarElementoEvento(
		    apt_proceso, an_eventoFin, false, false, true, as_idUsuarioCreacion, as_ipCreacion
		);
	}

	/**
	 * Analizar elemento evento fin proceso.
	 *
	 * @param apt_proceso
	 *                                 de apt proceso
	 * @param an_eventoFin
	 *                                 de an evento fin
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de evento
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private Evento analizarElementoEventoFinProceso(
	    ProcesoTrabajo apt_proceso, Node an_eventoFin, String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		return analizarElementoEvento(
		    apt_proceso, an_eventoFin, false, true, false, as_idUsuarioCreacion, as_ipCreacion
		);
	}

	/**
	 * Analizar elemento evento frontera.
	 *
	 * @param apt_proceso
	 *                                 de apt proceso
	 * @param an_eventoFrontera
	 *                                 de an evento frontera
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private void analizarElementoEventoFrontera(
	    ProcesoTrabajo apt_proceso, Node an_eventoFrontera, String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		if(an_eventoFrontera != null)
		{
			NamedNodeMap lnnm_atributos;

			lnnm_atributos = an_eventoFrontera.getAttributes();

			if(lnnm_atributos != null)
			{
				String ls_tiempoEspera;
				String ls_idEtapa;

				ls_idEtapa          = null;
				ls_tiempoEspera     = null;

				for(
				    int li_i = 0, li_cantidadAjustados = 0, li_longitud = lnnm_atributos.getLength();
					    li_i < li_longitud; li_i++
				)
				{
					Node ln_attribute;

					ln_attribute = lnnm_atributos.item(li_i);

					if(ln_attribute != null)
					{
						String ls_nombreAtributo;

						ls_nombreAtributo = ln_attribute.getNodeName();

						if(StringUtils.isValidString(ls_nombreAtributo))
						{
							if(ls_nombreAtributo.equals(cs_ATRIBUTO_ELEMENTO_FRONTERA_NOMBRE))
							{
								ls_tiempoEspera = ln_attribute.getNodeValue();

								li_cantidadAjustados++;
							}
							else if(ls_nombreAtributo.equals(cs_ATRIBUTO_ELEMENTO_FRONTERA_ASOCIADO_A))
							{
								ls_idEtapa = ln_attribute.getNodeValue();

								li_cantidadAjustados++;
							}

							if(li_cantidadAjustados >= 2)
								li_i = li_longitud + 1;
						}
					}
				}

				if(StringUtils.isValidString(ls_idEtapa) && StringUtils.isValidString(ls_tiempoEspera))
				{
					EtapaTrabajo let_etapa;

					let_etapa = apt_proceso.getEtapa(ls_idEtapa);

					if(let_etapa == null)
					{
						let_etapa = new EtapaTrabajo(ls_idEtapa);

						let_etapa.setIdUsuarioCreacion(as_idUsuarioCreacion);
						let_etapa.setIpCreacion(as_ipCreacion);
					}

					let_etapa.setTiempoEspera(ls_tiempoEspera);

					apt_proceso.adicionarEtapa(let_etapa);
				}
			}
		}
	}

	/**
	 * Analizar elemento evento inicio proceso.
	 *
	 * @param apt_proceso
	 *                                 de apt proceso
	 * @param an_eventoInicio
	 *                                 de an evento inicio
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de evento
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private Evento analizarElementoEventoInicioProceso(
	    ProcesoTrabajo apt_proceso, Node an_eventoInicio, String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		return analizarElementoEvento(
		    apt_proceso, an_eventoInicio, true, false, false, as_idUsuarioCreacion, as_ipCreacion
		);
	}

	/**
	 * Analizar elemento flujo entrada.
	 *
	 * @param apt_proceso
	 *                                 de apt proceso
	 * @param an_entrada
	 *                                 de an entrada
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de motivo tramite trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private MotivoTramiteTrabajo analizarElementoFlujoEntrada(
	    ProcesoTrabajo apt_proceso, Node an_entrada, String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		return crearFlujo(apt_proceso, an_entrada, as_idUsuarioCreacion, as_ipCreacion);
	}

	/**
	 * Analizar elemento flujo salida.
	 *
	 * @param apt_proceso
	 *                                 de apt proceso
	 * @param an_salida
	 *                                 de an salida
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de motivo tramite trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private MotivoTramiteTrabajo analizarElementoFlujoSalida(
	    ProcesoTrabajo apt_proceso, Node an_salida, String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		return crearFlujo(apt_proceso, an_salida, as_idUsuarioCreacion, as_ipCreacion);
	}

	/**
	 * Analizar elemento proceso.
	 *
	 * @param an_proceso
	 *                                 de an proceso
	 * @param asa_definicion
	 *                                 de asa definicion
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de proceso trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private ProcesoTrabajo analizarElementoProceso(
	    Node an_proceso, String asa_definicion, String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		ProcesoTrabajo lpt_proceso;

		lpt_proceso = null;

		if(an_proceso != null)
		{
			NamedNodeMap             lnnm_atributos;
			SubProcesoTrabajo        lspt_subProceso;
			SubProcesoVersionTrabajo lspvt_version;

			lnnm_atributos      = an_proceso.getAttributes();
			lpt_proceso         = new ProcesoTrabajo();
			lspt_subProceso     = new SubProcesoTrabajo();
			lspvt_version       = new SubProcesoVersionTrabajo();

			lspvt_version.setXml(asa_definicion);

			lpt_proceso.setSubProceso(lspt_subProceso);

			lspt_subProceso.setVersion(lspvt_version);

			if(lnnm_atributos != null)
			{
				for(int li_i = 0, li_longitud = lnnm_atributos.getLength(); li_i < li_longitud; li_i++)
				{
					Node ln_attribute;

					ln_attribute = lnnm_atributos.item(li_i);

					if(ln_attribute != null)
					{
						String ls_nombreAtributo;

						ls_nombreAtributo = ln_attribute.getNodeName();

						if(StringUtils.isValidString(ls_nombreAtributo))
						{
							switch(ls_nombreAtributo)
							{
								case cs_ATRIBUTO_PERSONALIZADO_PROCESO_ID:
									lpt_proceso.setId(ln_attribute.getNodeValue());

									break;

								case cs_ATRIBUTO_PERSONALIZADO_PROCESO_NOMBRE:
									lpt_proceso.setNombre(ln_attribute.getNodeValue());

									break;

								case cs_ATRIBUTO_PERSONALIZADO_PROCESO_RECEPCION_DOCUMENTOS:
									lpt_proceso.setRecepcionDocumentos(ln_attribute.getNodeValue());

									break;

								case cs_ATRIBUTO_PERSONALIZADO_SUBPROCESO_ID:
									lspt_subProceso.setIdSubproceso(ln_attribute.getNodeValue());

									break;

								case cs_ATRIBUTO_PERSONALIZADO_SUBPROCESO_NOMBRE:
									lspt_subProceso.setNombre(ln_attribute.getNodeValue());

									break;

								case cs_ATRIBUTO_PERSONALIZADO_VERSION_CONSERVACION_DOCUMENTAL:
									lspvt_version.setConservacionDocumental(ln_attribute.getNodeValue());

									break;

								case cs_ATRIBUTO_PERSONALIZADO_VERSION_PLANTILLA:
									lspvt_version.setPlantilla(ln_attribute.getNodeValue());

									break;

								case cs_ATRIBUTO_PERSONALIZADO_VERSION_RECIBO_CAJA:
									lspvt_version.setObtenerReciboCaja(ln_attribute.getNodeValue());

									break;

								case cs_ATRIBUTO_PERSONALIZADO_VERSION_SOLICITUD_CERTIFICADO:
									lspvt_version.setSolicitudCertificado(ln_attribute.getNodeValue());

									break;

								default:
									break;
							}
						}
					}
				}
			}

			if(!StringUtils.isValidString(lpt_proceso.getId()))
				lpt_proceso = null;
			else
			{
				lpt_proceso = normalizarFlujos(
					    multiplexarCompuertas(
					        resolverIdsFlujos(
					            validarProceso(
					                validarCompuertas(
					                    validarEventos(
					                        validarEtapas(
					                            analizarContenidoProceso(
					                                lpt_proceso, an_proceso.getChildNodes(), as_idUsuarioCreacion,
					                                as_ipCreacion
					                            )
					                        )
					                    )
					                )
					            )
					        )
					    )
					);

				imprimirFlujos(lpt_proceso.getColeccionFlujos());
			}
		}

		return validarFlujos(lpt_proceso);
	}

	/**
	 * Analizar elemento secuencia flujo.
	 *
	 * @param apt_proceso
	 *                                 de apt proceso
	 * @param an_secuenciaFlujo
	 *                                 de an secuencia flujo
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de motivo tramite trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private MotivoTramiteTrabajo analizarElementoSecuenciaFlujo(
	    ProcesoTrabajo apt_proceso, Node an_secuenciaFlujo, String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		MotivoTramiteTrabajo lmtt_flujo;

		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		lmtt_flujo = null;

		if(an_secuenciaFlujo != null)
		{
			NamedNodeMap lnnm_atributos;

			lmtt_flujo         = new MotivoTramiteTrabajo();
			lnnm_atributos     = an_secuenciaFlujo.getAttributes();

			lmtt_flujo.setIdUsuarioCreacion(as_idUsuarioCreacion);
			lmtt_flujo.setIpCreacion(as_ipCreacion);

			if(lnnm_atributos != null)
			{
				for(
				    int li_i = 0, li_cantidadAjustados = 0, li_longitud = lnnm_atributos.getLength();
					    li_i < li_longitud; li_i++
				)
				{
					Node ln_atributo;

					ln_atributo = lnnm_atributos.item(li_i);

					if(ln_atributo != null)
					{
						String ls_nombreAtributo;

						ls_nombreAtributo = ln_atributo.getNodeName();

						if(StringUtils.isValidString(ls_nombreAtributo))
						{
							switch(ls_nombreAtributo)
							{
								case cs_ATRIBUTO_PERSONALIZADO_FLUJO_DECISION_CALIFICACION:
									lmtt_flujo.setDecisionCalificacion(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_FLUJO_ESTADO:
									lmtt_flujo.setEstado(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_FLUJO_ID_DESTINO:
									lmtt_flujo.setIdDestino(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_FLUJO_ID_MOTIVO:
									lmtt_flujo.setMotivo(NumericUtils.getInteger(ln_atributo.getNodeValue()));
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_FLUJO_ID_ORIGEN:
									lmtt_flujo.setIdOrigen(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_FLUJO_INDICADOR_DEVOLUCION:
									lmtt_flujo.setIndicadorDevolucion(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_GENEAL_DESCRIPCION:
									lmtt_flujo.setDescripcion(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_GENERAL_ID:
									lmtt_flujo.setId(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								default:
									break;
							}

							if(li_cantidadAjustados >= ci_ATRIBUTOS_SECUENCIA_FLUJO)
								li_i = li_longitud + 1;
						}
					}
				}
			}

			{
				String ls_id;

				ls_id = lmtt_flujo.getId();

				if(StringUtils.isValidString(ls_id))
				{
					MotivoTramiteTrabajo lmtt_tmp;

					lmtt_tmp = apt_proceso.getFlujo(ls_id);

					if(lmtt_tmp != null)
					{
						if(lmtt_tmp.getDecisionCalificacion() == null)
							lmtt_tmp.setDecisionCalificacion(lmtt_flujo.getDecisionCalificacion());

						if(lmtt_tmp.getDescripcion() == null)
							lmtt_tmp.setDescripcion(lmtt_flujo.getDescripcion());

						if(lmtt_tmp.getEstado() == null)
							lmtt_tmp.setEstado(lmtt_flujo.getEstado());

						if(lmtt_tmp.getIndicadorDevolucion() == null)
							lmtt_tmp.setIndicadorDevolucion(lmtt_flujo.getIndicadorDevolucion());

						if(lmtt_tmp.getMotivo() == null)
							lmtt_tmp.setMotivo(lmtt_flujo.getMotivo());

						{
							EtapaTrabajo let_destino;
							boolean      lb_destinoValido;

							let_destino          = lmtt_tmp.getDestino();
							lb_destinoValido     = let_destino != null;

							if(!StringUtils.isValidString(lmtt_tmp.getIdDestino()) && (!lb_destinoValido))
								lmtt_tmp.setIdDestino(lmtt_flujo.getIdDestino());

							if(!StringUtils.isValidString(lmtt_tmp.getEstadoActividad()) && lb_destinoValido)
								lmtt_tmp.setEstadoActividad(let_destino.getEstadoActividad());
						}

						if(!StringUtils.isValidString(lmtt_tmp.getIdOrigen()) && (lmtt_tmp.getOrigen() == null))
							lmtt_tmp.setIdOrigen(lmtt_flujo.getIdOrigen());

						lmtt_flujo = lmtt_tmp;
					}
					else
						lmtt_flujo.setProceso(apt_proceso);
				}
				else
					lmtt_flujo = null;
			}
		}

		return lmtt_flujo;
	}

	/**
	 * Analizar elemento tarea.
	 *
	 * @param apt_proceso
	 *                                 de apt proceso
	 * @param an_tareaUsuario
	 *                                 de an tarea usuario
	 * @param ab_usuario
	 *                                 de ab usuario
	 * @param ab_recepcion
	 *                                 de ab recepcion
	 * @param ab_servicio
	 *                                 de ab servicio
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de etapa trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private EtapaTrabajo analizarElementoTarea(
	    ProcesoTrabajo apt_proceso, Node an_tareaUsuario, boolean ab_usuario, boolean ab_recepcion, boolean ab_servicio,
	    String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		EtapaTrabajo let_etapa;

		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		{
			int li_control;

			li_control = 0;

			if(ab_usuario)
				li_control++;

			if(ab_recepcion)
				li_control++;

			if(ab_servicio)
				li_control++;

			if(li_control > 1)
				throw new B2BException("Tipo inválido de Tarea");
		}

		let_etapa = null;

		if(an_tareaUsuario != null)
		{
			NamedNodeMap lnnm_atributos;

			let_etapa          = new EtapaTrabajo();
			lnnm_atributos     = an_tareaUsuario.getAttributes();

			if(lnnm_atributos != null)
			{
				for(
				    int li_i = 0, li_cantidadAjustados = 0, li_longitud = lnnm_atributos.getLength();
					    li_i < li_longitud; li_i++
				)
				{
					Node ln_atributo;

					ln_atributo = lnnm_atributos.item(li_i);

					if(ln_atributo != null)
					{
						String ls_nombreAtributo;

						ls_nombreAtributo = ln_atributo.getNodeName();

						if(StringUtils.isValidString(ls_nombreAtributo))
						{
							switch(ls_nombreAtributo)
							{
								case cs_ATRIBUTO_PERSONALIZADO_GENEAL_DESCRIPCION:
									let_etapa.setDescripcion(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_TAREA_CANTIDAD_TIEMPO_ESPERA:
									let_etapa.setCantidadTiempoEspera(
									    NumericUtils.getBigDecimal(ln_atributo.getNodeValue())
									);
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_TAREA_DIAS_HABILES_NORMAL:
									let_etapa.setDiasHabilesNormal(
									    NumericUtils.getBigDecimal(ln_atributo.getNodeValue())
									);
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_TAREA_ESTADO_ACTIVIDAD:
									let_etapa.setEstadoActividad(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_TAREA_ESTADO_ETAPA:
									let_etapa.setEstado(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_TAREA_ETAPA:
									let_etapa.setIdEtapa(NumericUtils.getLong(ln_atributo.getNodeValue()));

									break;

								case cs_ATRIBUTO_PERSONALIZADO_TAREA_FASE:
									let_etapa.setIdFase(NumericUtils.getLong(ln_atributo.getNodeValue()));
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_TAREA_GENERAR_QR:
									let_etapa.setGeneraQR(BooleanUtils.getBooleanValue(ln_atributo.getNodeValue()));
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_TAREA_INDICADOR_BLOQUEO:
									let_etapa.setIndicadorBloqueo(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_TAREA_INDICADOR_DESBORDE:
									let_etapa.setIndicadorDesborde(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_TAREA_INDICADOR_PESO:
									let_etapa.setIndicadorPeso(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_TAREA_INDICADOR_TOPE:
									let_etapa.setIndicadorTope(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_TAREA_NOMBRE:
									let_etapa.setNombre(ln_atributo.getNodeValue());

									break;

								case cs_ATRIBUTO_PERSONALIZADO_TAREA_TIPO_REPARTO:
									let_etapa.setTipoReparto(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								case cs_ATRIBUTO_PERSONALIZADO_TAREA_UNIDAD_TIEMPO_ESPERA:
									let_etapa.setIdUnidadTiempoEspera(ln_atributo.getNodeValue());
									li_cantidadAjustados++;

									break;

								default:
									break;
							}

							if(li_cantidadAjustados >= ci_ATRIBUTOS_TAREA)
								li_i = li_longitud + 1;
						}
					}
				}
			}

			{
				String ls_id;

				ls_id = StringUtils.getString(let_etapa.getIdEtapa());

				if(StringUtils.isValidString(ls_id))
				{
					let_etapa.setProceso(apt_proceso);
					let_etapa.setRecepcion(ab_recepcion);
					let_etapa.setServicio(ab_servicio);
					let_etapa.setUsuario(ab_usuario);
					let_etapa.setIdUsuarioCreacion(as_idUsuarioCreacion);
					let_etapa.setIpCreacion(as_ipCreacion);

					analizarContenidoTarea(
					    apt_proceso, let_etapa, an_tareaUsuario.getChildNodes(), as_idUsuarioCreacion, as_ipCreacion
					);
				}
				else
					let_etapa = null;
			}
		}

		return let_etapa;
	}

	/**
	 * Aprobar.
	 *
	 * @param adm_manager
	 *                            de adm manager
	 * @param as_idProceso
	 *                            de as id proceso
	 * @param as_idSubProceso
	 *                            de as id sub proceso
	 * @param ai_version
	 *                            de ai version
	 * @param as_userId
	 *                            de as user id
	 * @param as_remoteIp
	 *                            de as remote ip
	 * @throws B2BException
	 *                          de b 2 B exception
	 * @throws IOException
	 *                          Signals that an I/O exception has occurred.
	 */
	private void aprobar(
	    DAOManager adm_manager, String as_idProceso, String as_idSubProceso, int ai_version, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException, IOException
	{
		try
		{
			if(
			    StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubProceso)
				    && (ai_version > 0)
			)
			{
				SubProcesoVersionTrabajoDAO lspvtd_DAO;
				SubProcesoVersionTrabajo    lspvt_subProcesoVersion;

				lspvtd_DAO                  = DaoCreator.getSubprocesoVersionTrabajoDAO(adm_manager);
				lspvt_subProcesoVersion     = lspvtd_DAO.findById(as_idProceso, as_idSubProceso, ai_version);

				if(lspvt_subProcesoVersion != null)
				{
					lspvt_subProcesoVersion.setEstadoFlujo(EstadoFlujoCommon.DEFINITIVO);
					lspvt_subProcesoVersion.setIdUsuarioModificacion(as_userId);
					lspvt_subProcesoVersion.setIdUsuarioModificacion(as_remoteIp);
					lspvtd_DAO.update(lspvt_subProcesoVersion);

					{
						DaoCreator.getProcedimientosDAO(adm_manager)
							          .procApruebaFlujo(
							    as_idProceso, as_idSubProceso, ai_version, as_userId, as_remoteIp
							);
						DaoCreator.getSubprocesoVersionDAO(adm_manager)
							          .updateDefinicion(
							    lspvt_subProcesoVersion.getXml(), as_idProceso, as_idSubProceso, ai_version, as_userId,
							    as_remoteIp
							);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("aprobar", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Método para armar el menú de conciliaciones y Workflow
	 * @param ac_tipoAcceso
	 * @return
	 */
	private Collection<Opcion> armarOpcionesComponente(Collection<TipoAcceso> ac_tipoAcceso)
	{
		Collection<Opcion> lco_coReturn;
		lco_coReturn = new LinkedList<Opcion>();

		if(CollectionUtils.isValidCollection(ac_tipoAcceso))
		{
			for(TipoAcceso ita_tipoAcceso : ac_tipoAcceso)
			{
				if(ita_tipoAcceso != null)
				{
					Opcion lo_opcion;
					lo_opcion = new Opcion();

					String ls_iterador;
					ls_iterador = null;

					{
						ls_iterador = ita_tipoAcceso.getIdOpcion();

						if(StringUtils.isValidString(ls_iterador))
							lo_opcion.setIdOpcion(ls_iterador);
					}

					{
						ls_iterador = ita_tipoAcceso.getOpcion();

						if(StringUtils.isValidString(ls_iterador))
							lo_opcion.setOpcion(ls_iterador);
					}

					{
						ls_iterador = ita_tipoAcceso.getDescripcion();

						if(StringUtils.isValidString(ls_iterador))
							lo_opcion.setDescripcion(ls_iterador);
					}

					{
						ls_iterador = ita_tipoAcceso.getOpcionPadre();

						if(StringUtils.isValidString(ls_iterador))
							lo_opcion.setIdOpcionPadre(ls_iterador);
					}

					{
						ls_iterador = ita_tipoAcceso.getTipo();

						if(StringUtils.isValidString(ls_iterador))
							lo_opcion.setTipo(ls_iterador);
					}

					{
						ls_iterador = ita_tipoAcceso.getUrl();

						if(StringUtils.isValidString(ls_iterador))
							lo_opcion.setUrl(ls_iterador);
					}

					{
						ls_iterador = ita_tipoAcceso.getActivo();

						if(StringUtils.isValidString(ls_iterador))
							lo_opcion.setActivo(ls_iterador);

						if(ls_iterador.equals("S"))
							lco_coReturn.add(lo_opcion);
					}
				}
			}
		}

		return lco_coReturn;
	}

	/**
	 * Crear flujo.
	 *
	 * @param apt_proceso
	 *                                 de apt proceso
	 * @param an_nodo
	 *                                 de an nodo
	 * @param as_idUsuarioCreacion
	 *                                 de as id usuario creacion
	 * @param as_ipCreacion
	 *                                 de as ip creacion
	 * @return de motivo tramite trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private MotivoTramiteTrabajo crearFlujo(
	    ProcesoTrabajo apt_proceso, Node an_nodo, String as_idUsuarioCreacion, String as_ipCreacion
	)
	    throws B2BException
	{
		MotivoTramiteTrabajo llmtt_flujo;

		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		llmtt_flujo = null;

		if(an_nodo != null)
		{
			String ls_id;
			Node   ln_valor;

			ln_valor     = an_nodo.getFirstChild();
			ls_id        = (ln_valor != null) ? ln_valor.getNodeValue() : null;

			if(StringUtils.isValidString(ls_id))
			{
				MotivoTramiteTrabajo lmtt_tmp;

				lmtt_tmp = apt_proceso.getFlujo(ls_id);

				if(lmtt_tmp != null)
					llmtt_flujo = lmtt_tmp;
				else
				{
					llmtt_flujo = new MotivoTramiteTrabajo(ls_id);

					llmtt_flujo.setProceso(apt_proceso);
					llmtt_flujo.setIdUsuarioCreacion(as_idUsuarioCreacion);
					llmtt_flujo.setIpCreacion(as_ipCreacion);
				}
			}
		}

		return llmtt_flujo;
	}

	/**
	 * Enviar al aprobador.
	 *
	 * @param adm_manager
	 *                            de adm manager
	 * @param as_idProceso
	 *                            de as id proceso
	 * @param as_idSubProceso
	 *                            de as id sub proceso
	 * @param ai_version
	 *                            de ai version
	 * @param as_userId
	 *                            de as user id
	 * @param as_remoteIp
	 *                            de as remote ip
	 * @throws B2BException
	 *                          de b 2 B exception
	 * @throws IOException
	 *                          Signals that an I/O exception has occurred.
	 */
	private synchronized void enviarAlAprobador(
	    DAOManager adm_manager, String as_idProceso, String as_idSubProceso, int ai_version, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException, IOException
	{
		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubProceso) && (ai_version > 0))
		{
			SubProcesoVersionTrabajoDAO lspvtd_DAO;
			SubProcesoVersionTrabajo    lspvt_subProcesoVersion;

			lspvtd_DAO                  = DaoCreator.getSubprocesoVersionTrabajoDAO(adm_manager);
			lspvt_subProcesoVersion     = lspvtd_DAO.findById(as_idProceso, as_idSubProceso, ai_version);

			if(lspvt_subProcesoVersion != null)
			{
				lspvt_subProcesoVersion.setEstadoFlujo(EstadoFlujoCommon.APROBADOR);
				lspvt_subProcesoVersion.setIdUsuarioModificacion(as_userId);
				lspvt_subProcesoVersion.setIdUsuarioModificacion(as_remoteIp);
				lspvtd_DAO.update(lspvt_subProcesoVersion);
			}
		}
	}

	/**
	 * Imprimir.
	 *
	 * @param amtt_flujo
	 *                       de amtt flujo
	 */
	private static void imprimir(MotivoTramiteTrabajo amtt_flujo)
	{
		if(amtt_flujo != null)
			System.err.println("\t\t" + amtt_flujo);
	}

	/**
	 * Imprimir.
	 *
	 * @param aet_etapa
	 *                      de aet etapa
	 */
	private static void imprimir(EtapaTrabajo aet_etapa)
	{
		if(aet_etapa != null)
			System.err.println("\t\t" + aet_etapa);
	}

	/**
	 * Imprimir.
	 *
	 * @param apt_proceso
	 *                        de apt proceso
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private static void imprimir(ProcesoTrabajo apt_proceso)
	    throws B2BException
	{
		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		System.err.println(apt_proceso);

		imprimirEtapas(apt_proceso.getColeccionEtapas());
		imprimirFlujos(apt_proceso.getColeccionFlujos());
	}

	/**
	 * Imprimir etapas.
	 *
	 * @param acet_etapas
	 *                        de acet etapas
	 */
	private static void imprimirEtapas(Collection<EtapaTrabajo> acet_etapas)
	{
		if(CollectionUtils.isValidCollection(acet_etapas))
		{
			Collection<EtapaTrabajo> lcet_etapas;
			Iterator<EtapaTrabajo>   liet_etapas;

			System.err.println("\tEtapas");

			lcet_etapas     = CollectionUtils.sort(acet_etapas);
			liet_etapas     = lcet_etapas.iterator();

			while(liet_etapas.hasNext())
				imprimir(liet_etapas.next());
		}
	}

	/**
	 * Imprimir flujos.
	 *
	 * @param acmtt_flujos
	 *                         de acmtt flujos
	 */
	private static void imprimirFlujos(Collection<MotivoTramiteTrabajo> acmtt_flujos)
	{
		if(CollectionUtils.isValidCollection(acmtt_flujos))
		{
			Collection<MotivoTramiteTrabajo> lcmtt_flujos;
			Iterator<MotivoTramiteTrabajo>   limtt_flujos;

			System.err.println("\tFlujos");

			lcmtt_flujos     = CollectionUtils.sort(acmtt_flujos);
			limtt_flujos     = lcmtt_flujos.iterator();

			while(limtt_flujos.hasNext())
				imprimir(limtt_flujos.next());
		}
	}

	private boolean multiplexarCompuerta(ProcesoTrabajo apt_proceso, Compuerta ac_compuerta, boolean ab_entrada)
	    throws B2BException
	{
		boolean                          lb_reanalizar;
		Collection<MotivoTramiteTrabajo> lcmtt_flujos;

		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		if(ac_compuerta == null)
			throw new B2BException("No hay definicion de compuerta");

		lb_reanalizar     = false;
		lcmtt_flujos      = ab_entrada ? ac_compuerta.getColeccionFlujosEntrada()
			                           : ac_compuerta.getColeccionFlujosSalida();

		if(CollectionUtils.isValidCollection(lcmtt_flujos))
		{
			for(MotivoTramiteTrabajo lmtt_flujo : lcmtt_flujos)
			{
				if(lmtt_flujo != null)
				{
					EtapaTrabajo let_etapa;

					let_etapa = ab_entrada ? lmtt_flujo.getOrigen() : lmtt_flujo.getDestino();

					if(let_etapa == null)
					{
						String ls_id;

						ls_id = ab_entrada ? lmtt_flujo.getIdOrigen() : lmtt_flujo.getIdDestino();

						if(StringUtils.isValidString(ls_id))
						{
							Compuerta lc_tmp;

							lc_tmp = apt_proceso.getCompuerta(ls_id);

							if(lc_tmp != null)
							{
								Collection<MotivoTramiteTrabajo> lcmtt_tmp;

								lb_reanalizar     = true;
								lcmtt_tmp         = ab_entrada ? lc_tmp.getColeccionFlujosEntrada()
									                           : lc_tmp.getColeccionFlujosSalida();

								if(CollectionUtils.isValidCollection(lcmtt_tmp))
								{
									boolean lb_eliminar;

									lb_eliminar = false;

									for(MotivoTramiteTrabajo lmtt_tmp : lcmtt_tmp)
									{
										if(lmtt_tmp != null)
										{
											MotivoTramiteTrabajo lmtt_final;

											{
												StringBuilder lsb_id;

												lsb_id = new StringBuilder();

												if(ab_entrada)
												{
													lsb_id.append(lmtt_tmp.getId());
													lsb_id.append(":");
												}

												lsb_id.append(lmtt_flujo.getId());

												if(!ab_entrada)
												{
													lsb_id.append(":");
													lsb_id.append(lmtt_tmp.getId());
												}

												lmtt_final = new MotivoTramiteTrabajo(lsb_id.toString());
											}

											lmtt_final.setFlujoDefecto(lmtt_tmp.isFlujoDefecto());
											lmtt_final.setProceso(lmtt_flujo.getProceso());

											if(ab_entrada)
											{
												lmtt_final.setDescripcion(lmtt_flujo.getDescripcion());
												lmtt_final.setDestino(lmtt_flujo.getDestino());
												lmtt_final.setEstado(lmtt_flujo.getEstado());
												lmtt_final.setIdDestino(lmtt_flujo.getIdDestino());
												lmtt_final.setIndicadorDevolucion(lmtt_flujo.getIndicadorDevolucion());
												lmtt_final.setMotivo(lmtt_flujo.getMotivo());
												lmtt_final.setProceso(lmtt_flujo.getProceso());

												lmtt_final.setIdOrigen(lmtt_tmp.getIdOrigen());
												lmtt_final.setOrigen(lmtt_tmp.getOrigen());

												ac_compuerta.adicionarFlujoEntrada(lmtt_final);
											}
											else
											{
												lmtt_final.setDescripcion(lmtt_tmp.getDescripcion());
												lmtt_final.setDestino(lmtt_tmp.getDestino());
												lmtt_final.setEstado(lmtt_tmp.getEstado());
												lmtt_final.setIdDestino(lmtt_tmp.getIdDestino());
												lmtt_final.setIndicadorDevolucion(lmtt_tmp.getIndicadorDevolucion());
												lmtt_final.setMotivo(lmtt_tmp.getMotivo());
												lmtt_final.setProceso(lmtt_tmp.getProceso());

												lmtt_final.setIdOrigen(lmtt_flujo.getIdOrigen());
												lmtt_final.setOrigen(lmtt_flujo.getOrigen());

												ac_compuerta.adicionarFlujoSalida(lmtt_final);
											}

											apt_proceso.adicionarFlujo(lmtt_final);

											lb_eliminar = true;
										}
									}

									if(lb_eliminar)
									{
										if(ab_entrada)
										{
											ac_compuerta.eliminarFlujoEntrada(lmtt_flujo);
											lc_tmp.eliminarFlujoSalida(lmtt_flujo);
										}
										else
										{
											ac_compuerta.eliminarFlujoSalida(lmtt_flujo);
											lc_tmp.eliminarFlujoEntrada(lmtt_flujo);
										}

										apt_proceso.eliminarFlujo(lmtt_flujo);
									}
								}
							}
						}
					}
				}
			}
		}

		return lb_reanalizar;
	}

	/**
	 * Multiplexar compuertas.
	 *
	 * @param apt_proceso
	 *                        de apt proceso
	 * @return de proceso trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private ProcesoTrabajo multiplexarCompuertas(ProcesoTrabajo apt_proceso)
	    throws B2BException
	{
		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		{
			Collection<Compuerta> lcc_compuertas;

			lcc_compuertas = apt_proceso.getColeccionCompuertas();

			while(CollectionUtils.isValidCollection(lcc_compuertas))
			{
				Collection<Compuerta> lcc_reanalizar;

				lcc_reanalizar = new ArrayList<Compuerta>();

				for(Compuerta lc_compuerta : lcc_compuertas)
				{
					if(lc_compuerta != null)
					{
						if(multiplexarCompuerta(apt_proceso, lc_compuerta, true))
							lcc_reanalizar.add(lc_compuerta);

						if(multiplexarCompuerta(apt_proceso, lc_compuerta, false))
							lcc_reanalizar.add(lc_compuerta);
					}
				}

				lcc_compuertas = lcc_reanalizar;
			}
		}

		{
			Collection<Compuerta>            lcc_compuertas;
			Collection<MotivoTramiteTrabajo> lcmtt_eliminar;

			lcc_compuertas     = apt_proceso.getColeccionCompuertas();
			lcmtt_eliminar     = new ArrayList<MotivoTramiteTrabajo>();

			if(CollectionUtils.isValidCollection(lcc_compuertas))
			{
				Map<String, MotivoTramiteTrabajo> lmsmtt_adicionar;

				lmsmtt_adicionar = new HashMap<String, MotivoTramiteTrabajo>();

				for(Compuerta lc_compuerta : lcc_compuertas)
				{
					if(lc_compuerta != null)
					{
						Collection<MotivoTramiteTrabajo> lcmtt_entrada;
						Collection<MotivoTramiteTrabajo> lcmtt_salida;

						lcmtt_entrada     = lc_compuerta.getColeccionFlujosEntrada();
						lcmtt_salida      = lc_compuerta.getColeccionFlujosSalida();

						if(
						    CollectionUtils.isValidCollection(lcmtt_entrada)
							    && CollectionUtils.isValidCollection(lcmtt_salida)
						)
						{
							boolean lb_unicaSalida;

							lb_unicaSalida = lc_compuerta.isUnicaSalida();

							for(MotivoTramiteTrabajo lmtt_entrada : lcmtt_entrada)
							{
								if(lmtt_entrada != null)
								{
									for(MotivoTramiteTrabajo lmtt_salida : lcmtt_salida)
									{
										if(lmtt_salida != null)
										{
											boolean              lb_descripcionEntrada;
											boolean              lb_motivoEntrada;
											boolean              lb_motivoSalida;
											MotivoTramiteTrabajo lmtt_definitivo;
											String               ls_idEntrada;
											String               ls_idSalida;
											String               ls_descripcion;
											String               ls_descripcionEntrada;
											String               ls_descripcionSalida;
											Integer              li_motivo;
											Integer              li_motivoEntrada;
											Integer              li_motivoSalida;

											ls_idEntrada              = lmtt_entrada.getId();
											ls_idSalida               = lmtt_salida.getId();
											li_motivoEntrada          = lmtt_entrada.getMotivo();
											li_motivoSalida           = lmtt_salida.getMotivo();
											ls_descripcionEntrada     = lmtt_entrada.getDescripcion();
											ls_descripcionSalida      = lmtt_salida.getDescripcion();
											lb_motivoEntrada          = li_motivoEntrada != null;
											lb_motivoSalida           = li_motivoSalida != null;
											lb_descripcionEntrada     = StringUtils.isValidString(
												    ls_descripcionEntrada
												);

											{
												String        ls_id;
												StringBuilder lsb_id;

												lsb_id = new StringBuilder();

												lsb_id.append(ls_idEntrada);
												lsb_id.append(":");
												lsb_id.append(ls_idSalida);

												ls_id               = lsb_id.toString();
												lmtt_definitivo     = new MotivoTramiteTrabajo(ls_id);

												lmsmtt_adicionar.put(ls_id, lmtt_definitivo);
											}

											{
												EtapaTrabajo let_origen;

												let_origen = lmtt_entrada.getOrigen();

												if(let_origen != null)
													lmtt_definitivo.setOrigen(let_origen);
												else
													lmtt_definitivo.setIdOrigen(lmtt_entrada.getIdOrigen());
											}

											{
												EtapaTrabajo let_destino;

												let_destino = lmtt_salida.getDestino();

												if(let_destino != null)
													lmtt_definitivo.setDestino(let_destino);
												else
													lmtt_definitivo.setIdDestino(lmtt_salida.getIdDestino());
											}

											li_motivo     = (lb_unicaSalida && lb_motivoEntrada) ? li_motivoEntrada
												                                                 : li_motivoSalida;

											ls_descripcion = (lb_unicaSalida && lb_descripcionEntrada)
												? ls_descripcionEntrada : ls_descripcionSalida;

											lmtt_definitivo.setDescripcion(ls_descripcion);
											lmtt_definitivo.setMotivo(li_motivo);
											lmtt_definitivo.setProceso(lmtt_entrada.getProceso());
											lmtt_definitivo.setEstadoActividad(lmtt_salida.getEstadoActividad());

											lmtt_definitivo.setDecisionCalificacion(
											    lb_motivoSalida ? lmtt_salida.getDecisionCalificacion()
											                    : lmtt_entrada.getDecisionCalificacion()
											);

											lmtt_definitivo.setDescriptorFin(
											    lb_motivoSalida ? lmtt_salida.getDescriptorFin()
											                    : lmtt_entrada.getDescriptorFin()
											);

											{
												boolean lb_salida;
												String  ls_entrada;
												String  ls_salida;

												ls_entrada     = lmtt_entrada.getEstado();
												ls_salida      = lmtt_salida.getEstado();
												lb_salida      = lb_motivoSalida
														&& StringUtils.isValidString(ls_salida);

												lmtt_definitivo.setEstado(lb_salida ? ls_salida : ls_entrada);
											}

											{
												boolean lb_salida;
												String  ls_entrada;
												String  ls_salida;

												ls_entrada     = lmtt_entrada.getIndicadorDevolucion();
												ls_salida      = lmtt_salida.getIndicadorDevolucion();
												lb_salida      = lb_motivoSalida
														&& StringUtils.isValidString(ls_salida);

												lmtt_definitivo.setIndicadorDevolucion(
												    lb_salida ? ls_salida : ls_entrada
												);
											}

											lmtt_definitivo.setFlujoDefecto(
											    lb_motivoSalida ? lmtt_salida.isFlujoDefecto()
											                    : lmtt_entrada.isFlujoDefecto()
											);

											if(lb_motivoEntrada)
											{
												int    li_tipoCompuertaLlegada;
												String ls_tipoCompuertaLlegada;

												li_tipoCompuertaLlegada = lc_compuerta.getTipo();

												switch(li_tipoCompuertaLlegada)
												{
													case Componente.TIPO_COMPUERTA_EXCLUSIVA:
														ls_tipoCompuertaLlegada = TipoCompuertaLlegada.EXCLUSIVA;

														break;

													case Componente.TIPO_COMPUERTA_EXCLUSIVA_TERMINACION_AUTOMATICA:
														ls_tipoCompuertaLlegada = TipoCompuertaLlegada.EXCLUSIVA_TERMINACION_AUTOMATICA;

														break;

													case Componente.TIPO_COMPUERTA_PARALELA:
														ls_tipoCompuertaLlegada = TipoCompuertaLlegada.PARALELA;

														break;

													default:
														ls_tipoCompuertaLlegada = null;

														break;
												}

												lmtt_definitivo.setTipoCompuertaLLegada(ls_tipoCompuertaLlegada);
											}

											lcmtt_eliminar.add(lmtt_salida);
										}
									}
								}

								lcmtt_eliminar.add(lmtt_entrada);
							}
						}
					}
				}

				if(CollectionUtils.isValidCollection(lcmtt_eliminar))
				{
					for(MotivoTramiteTrabajo lmtt_eliminar : lcmtt_eliminar)
					{
						if(lmtt_eliminar != null)
							apt_proceso.eliminarFlujo(lmtt_eliminar);
					}
				}

				apt_proceso.adicionarFlujos(lmsmtt_adicionar);
			}
		}

		return apt_proceso;
	}

	/**
	 * Normalizar flujos.
	 *
	 * @param apt_proceso
	 *                        de apt proceso
	 * @return de proceso trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private ProcesoTrabajo normalizarFlujos(ProcesoTrabajo apt_proceso)
	    throws B2BException
	{
		Collection<MotivoTramiteTrabajo> lcmtt_flujos;

		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		lcmtt_flujos = apt_proceso.getColeccionFlujos();

		if(CollectionUtils.isValidCollection(lcmtt_flujos))
		{
			Collection<String> lcs_idsEliminar;

			{
				Map<String, MotivoTramiteTrabajo> lmsmtt_flujos;

				lmsmtt_flujos       = new HashMap<String, MotivoTramiteTrabajo>();
				lcs_idsEliminar     = new ArrayList<String>();

				for(MotivoTramiteTrabajo llmtt_flujo : lcmtt_flujos)
				{
					if(llmtt_flujo != null)
					{
						boolean      lb_destino;
						boolean      lb_origen;
						EtapaTrabajo let_destino;
						EtapaTrabajo let_origen;

						let_destino     = llmtt_flujo.getDestino();
						let_origen      = llmtt_flujo.getOrigen();
						lb_destino      = let_destino != null;
						lb_origen       = let_origen != null;

						if(lb_origen)
						{
							String ls_idEtapaOrigen;

							ls_idEtapaOrigen = let_origen.getId();

							if(StringUtils.isValidString(ls_idEtapaOrigen))
							{
								if(lb_destino)
								{
									String ls_idEtapaDestino;

									ls_idEtapaDestino = let_destino.getId();

									if(StringUtils.isValidString(ls_idEtapaDestino))
									{
										String ls_key;

										{
											StringBuilder lsb_key;

											lsb_key = new StringBuilder();

											lsb_key.append(ls_idEtapaOrigen);
											lsb_key.append("<->");
											lsb_key.append(ls_idEtapaDestino);

											ls_key = lsb_key.toString();
										}

										{
											MotivoTramiteTrabajo lmtt_tmp;

											lmtt_tmp = lmsmtt_flujos.get(ls_key);

											if(lmtt_tmp == null)
												lmsmtt_flujos.put(ls_key, llmtt_flujo);
											else
											{
												Integer li_motivoFlujo;
												Integer li_motivoTmp;

												li_motivoFlujo     = llmtt_flujo.getMotivo();
												li_motivoTmp       = lmtt_tmp.getMotivo();

												if((li_motivoTmp == null) && (li_motivoFlujo != null))
												{
													lmsmtt_flujos.put(ls_key, llmtt_flujo);
													lcs_idsEliminar.add(lmtt_tmp.getId());
												}
												else
													lcs_idsEliminar.add(llmtt_flujo.getId());
											}
										}
									}
								}
							}
						}
					}
				}
			}

			if(CollectionUtils.isValidCollection(lcs_idsEliminar))
			{
				for(String ls_id : lcs_idsEliminar)
				{
					if(StringUtils.isValidString(ls_id))
						apt_proceso.eliminarFlujo(ls_id);
				}
			}
		}

		return apt_proceso;
	}

	/**
	 * Resolver ids flujos.
	 *
	 * @param apt_proceso
	 *                        de apt proceso
	 * @return de proceso trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private ProcesoTrabajo resolverIdsFlujos(ProcesoTrabajo apt_proceso)
	    throws B2BException
	{
		Collection<MotivoTramiteTrabajo> lcmtt_flujos;

		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		lcmtt_flujos = apt_proceso.getColeccionFlujos();

		if(CollectionUtils.isValidCollection(lcmtt_flujos))
		{
			for(MotivoTramiteTrabajo lmtt_flujo : lcmtt_flujos)
			{
				if(lmtt_flujo != null)
				{
					boolean lb_compuertaDestino;
					boolean lb_compuertaOrigen;

					{
						boolean      lb_etapaDestino;
						boolean      lb_etapaOrigen;
						Compuerta    lc_compuertaDestino;
						Compuerta    lc_compuertaOrigen;
						EtapaTrabajo let_destino;
						EtapaTrabajo let_origen;
						Evento       le_eventoFinEtapa;
						Evento       le_eventoFinProceso;
						Evento       le_eventoInicioProceso;
						String       ls_idDestino;
						String       ls_idOrigen;

						lb_compuertaDestino        = false;
						lb_compuertaOrigen         = false;
						lb_etapaDestino            = false;
						lb_etapaOrigen             = false;
						lc_compuertaDestino        = null;
						lc_compuertaOrigen         = null;
						let_destino                = null;
						let_origen                 = null;
						le_eventoFinEtapa          = null;
						le_eventoFinProceso        = null;
						le_eventoInicioProceso     = null;
						ls_idDestino               = lmtt_flujo.getIdDestino();
						ls_idOrigen                = lmtt_flujo.getIdOrigen();

						if(StringUtils.isValidString(ls_idOrigen))
						{
							boolean lb_pendiente;

							lb_pendiente = true;

							if(lb_pendiente)
							{
								Compuerta lc_compuerta;

								lc_compuerta = apt_proceso.getCompuerta(ls_idOrigen);

								if(lc_compuerta != null)
								{
									lb_pendiente           = false;
									lc_compuertaOrigen     = lc_compuerta;
								}
							}

							if(lb_pendiente)
							{
								EtapaTrabajo let_etapa;

								let_etapa = apt_proceso.getEtapa(ls_idOrigen);

								if(let_etapa != null)
								{
									lb_pendiente     = false;
									let_origen       = let_etapa;
								}
							}

							if(lb_pendiente)
							{
								Evento le_evento;

								le_evento = apt_proceso.getEvento(ls_idOrigen);

								if(le_evento != null)
								{
									lmtt_flujo.setOrigen(null);

									lb_pendiente = false;

									if(le_evento.isInicioProceso())
										le_eventoInicioProceso = le_evento;
								}
							}

							lb_compuertaOrigen = lc_compuertaOrigen != null;

							if(!lb_pendiente && !lb_compuertaOrigen)
								lmtt_flujo.setIdOrigen(null);
						}

						if(StringUtils.isValidString(ls_idDestino))
						{
							boolean lb_pendiente;

							lb_pendiente = true;

							if(lb_pendiente)
							{
								Compuerta lc_compuerta;

								lc_compuerta = apt_proceso.getCompuerta(ls_idDestino);

								if(lc_compuerta != null)
								{
									lb_pendiente            = false;
									lc_compuertaDestino     = lc_compuerta;
								}
							}

							if(lb_pendiente)
							{
								EtapaTrabajo let_etapa;

								let_etapa = apt_proceso.getEtapa(ls_idDestino);

								if(let_etapa != null)
								{
									lb_pendiente     = false;
									let_destino      = let_etapa;
								}
							}

							if(lb_pendiente)
							{
								Evento le_evento;

								le_evento = apt_proceso.getEvento(ls_idDestino);

								if(le_evento != null)
								{
									lmtt_flujo.setDestino(null);

									lb_pendiente = false;

									if(le_evento.isFinProceso())
										le_eventoFinProceso = le_evento;
									else if(le_evento.isFinEtapa())
										le_eventoFinEtapa = le_evento;
								}
							}

							lb_compuertaDestino = lc_compuertaDestino != null;

							if(!lb_pendiente && !lb_compuertaDestino)
								lmtt_flujo.setIdDestino(null);
						}

						if(let_destino == null)
							let_destino = lmtt_flujo.getDestino();

						if(let_origen == null)
							let_origen = lmtt_flujo.getOrigen();

						lb_etapaDestino     = let_destino != null;
						lb_etapaOrigen      = let_origen != null;

						if(lb_etapaOrigen)
						{
							if(lmtt_flujo.getOrigen() == null)
								lmtt_flujo.setOrigen(let_origen);

							if(le_eventoFinProceso != null)
							{
								let_origen.setFinProceso(true);
								le_eventoFinProceso.setEtapa(let_origen);
							}
							else if(le_eventoFinEtapa != null)
							{
								let_origen.setFinEtapa(true);
								le_eventoFinEtapa.setEtapa(let_origen);
							}
						}

						if(lb_etapaDestino)
						{
							if(lmtt_flujo.getDestino() == null)
								lmtt_flujo.setDestino(let_destino);

							if(le_eventoInicioProceso != null)
							{
								let_destino.setInicioProceso(true);
								le_eventoInicioProceso.setEtapa(let_destino);
							}
						}

						if(lb_compuertaOrigen && lb_compuertaDestino)
						{
							lmtt_flujo.setIdOrigen(ls_idOrigen);
							lmtt_flujo.setIdDestino(ls_idDestino);
						}
					}

					{
						String ls_idOrigen;

						ls_idOrigen = lmtt_flujo.getIdOrigen();

						if(StringUtils.isValidString(ls_idOrigen) && !lb_compuertaOrigen)
						{
							StringBuilder lsb_error;

							lsb_error = new StringBuilder();

							lsb_error.append("El flujo ");
							lsb_error.append(lmtt_flujo.getId());
							lsb_error.append(" hace referencia a origen inexistente ");
							lsb_error.append(ls_idOrigen);

							throw new B2BException(lsb_error.toString());
						}
					}

					{
						String ls_idDestino;

						ls_idDestino = lmtt_flujo.getIdDestino();

						if(StringUtils.isValidString(ls_idDestino) && !lb_compuertaDestino)
						{
							StringBuilder lsb_error;

							lsb_error = new StringBuilder();

							lsb_error.append("El flujo ");
							lsb_error.append(lmtt_flujo.getId());
							lsb_error.append(" hace referencia a destino inexistente ");
							lsb_error.append(ls_idDestino);

							throw new B2BException(lsb_error.toString());
						}
					}
				}
			}
		}

		return apt_proceso;
	}

	/**
	 * Validar compuerta.
	 *
	 * @param ac_compuerta
	 *                         de ac compuerta
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private void validarCompuerta(Compuerta ac_compuerta)
	    throws B2BException
	{
		int li_tipo;
		int li_flujosEntrada;
		int li_flujosSalida;

		String ls_id;

		if(ac_compuerta == null)
			throw new B2BException("No hay definicion de compuerta");

		ls_id = ac_compuerta.getId();

		if(!StringUtils.isValidString(ls_id))
			throw new B2BException("El id de la etapa no puede ser vacio");

		li_flujosEntrada     = ac_compuerta.getCantidadFlujosEntrada();
		li_flujosSalida      = ac_compuerta.getCantidadFlujosSalida();

		if(li_flujosEntrada < 1)
		{
			StringBuilder lsb_error;

			lsb_error = new StringBuilder();

			lsb_error.append("La compuerta ");
			lsb_error.append(ls_id);
			lsb_error.append(" no tiene flujos de entrada");

			throw new B2BException(lsb_error.toString());
		}

		if(li_flujosSalida < 1)
		{
			StringBuilder lsb_error;

			lsb_error = new StringBuilder();

			lsb_error.append("La compuerta ");
			lsb_error.append(ls_id);
			lsb_error.append(" no tiene flujos de salida");

			throw new B2BException(lsb_error.toString());
		}

		li_tipo = ac_compuerta.getTipo();

		if(li_tipo == Componente.TIPO_COMPUERTA_EXCLUSIVA)
		{
			int li_error;

			li_error = 0;

			if((li_flujosSalida == 1) && (li_flujosSalida <= li_flujosEntrada))
			{
				Collection<MotivoTramiteTrabajo> lcmtt_entrada;
				Iterator<MotivoTramiteTrabajo>   limtt_entrada;

				lcmtt_entrada     = ac_compuerta.getColeccionFlujosEntrada();
				limtt_entrada     = lcmtt_entrada.iterator();

				while((li_error == 0) && limtt_entrada.hasNext())
				{
					MotivoTramiteTrabajo lmtt_entrada;

					lmtt_entrada = limtt_entrada.next();

					if(lmtt_entrada != null)
					{
						if(lmtt_entrada.getMotivo() == null)
							li_error = 1;
						else if(!StringUtils.isValidString(lmtt_entrada.getDescripcion()))
							li_error = 4;
					}
				}
			}
			else if(li_flujosEntrada <= li_flujosSalida)
			{
				Collection<MotivoTramiteTrabajo> lcmtt_salida;
				Iterator<MotivoTramiteTrabajo>   limtt_salida;

				lcmtt_salida     = ac_compuerta.getColeccionFlujosSalida();
				limtt_salida     = lcmtt_salida.iterator();

				while((li_error == 0) && limtt_salida.hasNext())
				{
					MotivoTramiteTrabajo lmtt_salida;

					lmtt_salida = limtt_salida.next();

					if(lmtt_salida != null)
					{
						if(lmtt_salida.getMotivo() == null)
							li_error = 2;
						else if(!StringUtils.isValidString(lmtt_salida.getDescripcion()))
							li_error = 8;
					}
				}
			}

			if(li_error > 0)
			{
				StringBuilder lsb_error;

				lsb_error = new StringBuilder();

				lsb_error.append("La compuerta exclusiva ");
				lsb_error.append(ls_id);

				if(li_error == 1)
					lsb_error.append(" tiene flujos de entrada sin definición de motivo tramite");
				else if(li_error == 2)
					lsb_error.append(" tiene flujos de salida sin definición de motivo tramite");
				else if(li_error == 4)
					lsb_error.append(" tiene flujos de entrada sin descripción");
				else if(li_error == 8)
					lsb_error.append(" tiene flujos de salida sin descripción");
				else
					lsb_error.append(" tiene error indeterminado");

				throw new B2BException(lsb_error.toString());
			}
		}
		else if(li_tipo == Componente.TIPO_COMPUERTA_PARALELA)
		{
			boolean lb_compuertaValida;
			boolean lb_motivoValido;
			boolean lb_multipleSalidas;
			Integer li_motivoCompuerta;

			{
				Collection<MotivoTramiteTrabajo> lcmtt_salida;
				Iterator<MotivoTramiteTrabajo>   licmtt_salida;

				lb_compuertaValida     = true;
				lb_motivoValido        = true;
				lb_multipleSalidas     = ac_compuerta.isMultiplesSalidas();
				lcmtt_salida           = ac_compuerta.getColeccionFlujosSalida();

				li_motivoCompuerta     = null;
				licmtt_salida          = lcmtt_salida.iterator();

				while(lb_compuertaValida && lb_motivoValido && licmtt_salida.hasNext())
				{
					MotivoTramiteTrabajo lmtt_salida;

					lmtt_salida = licmtt_salida.next();

					if(lmtt_salida != null)
					{
						Integer li_motivoFlujo;

						li_motivoFlujo = lmtt_salida.getMotivo();

						if(li_motivoFlujo != null)
						{
							if(li_motivoCompuerta == null)
								li_motivoCompuerta = li_motivoFlujo;
							else
								lb_motivoValido = li_motivoCompuerta.equals(li_motivoFlujo);
						}
						else
							lb_compuertaValida = false;
					}
				}
			}

			if(!lb_compuertaValida)
			{
				StringBuilder lsb_error;

				lsb_error = new StringBuilder();

				lsb_error.append("La compuerta paralela ");
				lsb_error.append(ls_id);
				lsb_error.append(" tiene flujos de salida sin definición de motivo tramite");

				throw new B2BException(lsb_error.toString());
			}
			else if(!lb_motivoValido && lb_multipleSalidas)
			{
				StringBuilder lsb_error;

				lsb_error = new StringBuilder();

				lsb_error.append("La compuerta paralela ");
				lsb_error.append(ls_id);
				lsb_error.append(" tiene flujos de salida con distinto motivo");

				throw new B2BException(lsb_error.toString());
			}
			else if(lb_multipleSalidas)
			{
				Collection<MotivoTramiteTrabajo> lcmtt_entrada;

				lcmtt_entrada = ac_compuerta.getColeccionFlujosEntrada();

				for(MotivoTramiteTrabajo lmtt_entrada : lcmtt_entrada)
				{
					if(lmtt_entrada != null)
					{
						Integer li_motivoFlujo;

						li_motivoFlujo = lmtt_entrada.getMotivo();

						if(li_motivoFlujo != null)
						{
							if(li_motivoCompuerta == null)
								li_motivoCompuerta = li_motivoFlujo;
							else
								lb_motivoValido = li_motivoCompuerta.equals(li_motivoFlujo);
						}
						else
							lb_compuertaValida = false;
					}
				}

				if(!lb_compuertaValida)
				{
					StringBuilder lsb_error;

					lsb_error = new StringBuilder();

					lsb_error.append("La compuerta paralela ");
					lsb_error.append(ls_id);
					lsb_error.append(" tiene flujos de entrada sin definición de motivo tramite");

					throw new B2BException(lsb_error.toString());
				}
				else if(!lb_motivoValido)
				{
					StringBuilder lsb_error;

					lsb_error = new StringBuilder();

					lsb_error.append("La compuerta paralela ");
					lsb_error.append(ls_id);
					lsb_error.append(" tiene flujos de entrada con distinto motivo al de salida");

					throw new B2BException(lsb_error.toString());
				}
			}
		}
	}

	/**
	 * Validar compuertas.
	 *
	 * @param apt_proceso
	 *                        de apt proceso
	 * @return de proceso trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private ProcesoTrabajo validarCompuertas(ProcesoTrabajo apt_proceso)
	    throws B2BException
	{
		Collection<Compuerta> lcc_compuertas;

		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		lcc_compuertas = apt_proceso.getColeccionCompuertas();

		if(CollectionUtils.isValidCollection(lcc_compuertas))
		{
			for(Compuerta lc_compuerta : lcc_compuertas)
			{
				if(lc_compuerta != null)
					validarCompuerta(lc_compuerta);
			}
		}

		return apt_proceso;
	}

	/**
	 * Validar etapa.
	 *
	 * @param aet_etapa
	 *                      de aet etapa
	 * @return de etapa trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private EtapaTrabajo validarEtapa(EtapaTrabajo aet_etapa)
	    throws B2BException
	{
		String ls_nombreEtapa;

		if(aet_etapa == null)
			throw new B2BException("No hay definicion de etapa");

		ls_nombreEtapa = aet_etapa.getNombre();

		if(!StringUtils.isValidString(ls_nombreEtapa))
		{
			StringBuilder lsb_error;

			lsb_error = new StringBuilder();

			lsb_error.append("El nombre de la etapa ");
			lsb_error.append(aet_etapa.getIdEtapa());
			lsb_error.append(" no puede ser vacio");

			throw new B2BException(lsb_error.toString());
		}
		else if(!StringUtils.isValidString(aet_etapa.getEstado()))
		{
			StringBuilder lsb_error;

			lsb_error = new StringBuilder();

			lsb_error.append("El estado de la etapa ");
			lsb_error.append(ls_nombreEtapa);
			lsb_error.append(" no puede ser vacio");

			throw new B2BException(lsb_error.toString());
		}
		else if(!StringUtils.isValidString(aet_etapa.getIndicadorBloqueo()))
		{
			StringBuilder lsb_error;

			lsb_error = new StringBuilder();

			lsb_error.append("El indicador de bloqueo de la etapa ");
			lsb_error.append(ls_nombreEtapa);
			lsb_error.append(" no puede ser vacio");

			throw new B2BException(lsb_error.toString());
		}
		else if(!StringUtils.isValidString(aet_etapa.getIndicadorDesborde()))
		{
			StringBuilder lsb_error;

			lsb_error = new StringBuilder();

			lsb_error.append("El indicador de desborde de la etapa ");
			lsb_error.append(ls_nombreEtapa);
			lsb_error.append(" no puede ser vacio");

			throw new B2BException(lsb_error.toString());
		}
		else if(!StringUtils.isValidString(aet_etapa.getIndicadorPeso()))
		{
			StringBuilder lsb_error;

			lsb_error = new StringBuilder();

			lsb_error.append("El indicador de peso de la etapa ");
			lsb_error.append(ls_nombreEtapa);
			lsb_error.append(" no puede ser vacio");

			throw new B2BException(lsb_error.toString());
		}
		else if(!StringUtils.isValidString(aet_etapa.getTipoReparto()))
		{
			StringBuilder lsb_error;

			lsb_error = new StringBuilder();

			lsb_error.append("El tipo reparto de la etapa ");
			lsb_error.append(ls_nombreEtapa);
			lsb_error.append(" no puede ser vacio");

			throw new B2BException(lsb_error.toString());
		}

		{
			Collection<MotivoTramiteTrabajo> lcmtt_salida;
			int                              li_flujosSalida;

			lcmtt_salida        = aet_etapa.getColeccionFlujosSalida();
			li_flujosSalida     = 0;

			if(CollectionUtils.isValidCollection(lcmtt_salida))
			{
				for(MotivoTramiteTrabajo lmtt_salida : lcmtt_salida)
				{
					if(lmtt_salida != null)
						li_flujosSalida++;
				}
			}

			if(li_flujosSalida > 1)
			{
				StringBuilder lsb_error;

				lsb_error = new StringBuilder();

				lsb_error.append("La etapa ");
				lsb_error.append(ls_nombreEtapa);
				lsb_error.append(" tiene mas de un flujo de salida");

				throw new B2BException(lsb_error.toString());
			}
		}

		return aet_etapa;
	}

	/**
	 * Validar etapas.
	 *
	 * @param apt_proceso
	 *                        de apt proceso
	 * @return de proceso trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private ProcesoTrabajo validarEtapas(ProcesoTrabajo apt_proceso)
	    throws B2BException
	{
		Collection<EtapaTrabajo> lcet_etapas;

		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		lcet_etapas = apt_proceso.getColeccionEtapas();

		if(CollectionUtils.isValidCollection(lcet_etapas))
		{
			for(EtapaTrabajo let_etapa : lcet_etapas)
			{
				if(let_etapa != null)
					validarEtapa(let_etapa);
			}
		}

		return apt_proceso;
	}

	/**
	 * Validar evento.
	 *
	 * @param ae_evento
	 *                      de ae evento
	 * @return de evento
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private Evento validarEvento(Evento ae_evento)
	    throws B2BException
	{
		if(ae_evento == null)
			throw new B2BException("No hay definicion de evento");

		{
			{
				Collection<MotivoTramiteTrabajo> lcmtt_entrada;
				int                              li_flujosEntrada;

				lcmtt_entrada        = ae_evento.getColeccionFlujosEntrada();
				li_flujosEntrada     = 0;

				if(CollectionUtils.isValidCollection(lcmtt_entrada))
				{
					for(MotivoTramiteTrabajo lmtt_entrada : lcmtt_entrada)
					{
						if(lmtt_entrada != null)
							li_flujosEntrada++;
					}
				}

				if(ae_evento.isInicioProceso() && (li_flujosEntrada > 0))
				{
					StringBuilder lsb_error;

					lsb_error = new StringBuilder();

					lsb_error.append("El evento de inicio ");
					lsb_error.append(ae_evento.getId());
					lsb_error.append(" no puede tener flujos de entrada");

					throw new B2BException(lsb_error.toString());
				}
			}

			{
				Collection<MotivoTramiteTrabajo> lcmtt_salida;
				int                              li_flujosSalida;

				lcmtt_salida        = ae_evento.getColeccionFlujosSalida();
				li_flujosSalida     = 0;

				if(CollectionUtils.isValidCollection(lcmtt_salida))
				{
					for(MotivoTramiteTrabajo lmtt_salida : lcmtt_salida)
					{
						if(lmtt_salida != null)
							li_flujosSalida++;
					}
				}

				if(ae_evento.isFinProceso() && (li_flujosSalida > 0))
				{
					StringBuilder lsb_error;

					lsb_error = new StringBuilder();

					lsb_error.append("El evento de terminacion ");
					lsb_error.append(ae_evento.getId());
					lsb_error.append(" no puede tener flujos de salida");

					throw new B2BException(lsb_error.toString());
				}

				if(ae_evento.isInicioProceso() && (li_flujosSalida > 1))
				{
					StringBuilder lsb_error;

					lsb_error = new StringBuilder();

					lsb_error.append("El evento de inicio ");
					lsb_error.append(ae_evento.getId());
					lsb_error.append(" tiene mas de un flujo de entrada");

					throw new B2BException(lsb_error.toString());
				}
			}
		}

		return ae_evento;
	}

	/**
	 * Validar eventos.
	 *
	 * @param apt_proceso
	 *                        de apt proceso
	 * @return de proceso trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private ProcesoTrabajo validarEventos(ProcesoTrabajo apt_proceso)
	    throws B2BException
	{
		Collection<Evento> lce_eventos;

		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		lce_eventos = apt_proceso.getColeccionEventos();

		if(CollectionUtils.isValidCollection(lce_eventos))
		{
			for(Evento le_evento : lce_eventos)
			{
				if(le_evento != null)
					validarEvento(le_evento);
			}
		}

		return apt_proceso;
	}

	/**
	 * Validar flujo.
	 *
	 * @param amtt_flujo
	 *                       de amtt flujo
	 * @return de motivo tramite trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private MotivoTramiteTrabajo validarFlujo(MotivoTramiteTrabajo amtt_flujo)
	    throws B2BException
	{
		boolean lb_destino;
		boolean lb_origen;
		boolean lb_validar;
		String  ls_descripcionFlujo;

		if(amtt_flujo == null)
			throw new B2BException("No hay definicion de flujo");

		lb_destino              = amtt_flujo.getDestino() != null;
		lb_origen               = amtt_flujo.getOrigen() != null;
		lb_validar              = lb_destino && lb_origen;
		ls_descripcionFlujo     = amtt_flujo.getDescripcion();

		if(!lb_destino && !lb_origen)
		{
			StringBuilder lsb_error;

			lsb_error = new StringBuilder();

			lsb_error.append("El flujo ");
			lsb_error.append(amtt_flujo.getId());
			lsb_error.append(" no puede no tiene etapa origen ni etapa destino");

			throw new B2BException(lsb_error.toString());
		}
		else if(lb_validar)
		{
			if(!StringUtils.isValidString(ls_descripcionFlujo))
			{
				StringBuilder lsb_error;

				lsb_error = new StringBuilder();

				lsb_error.append("La descripción del flujo ");
				lsb_error.append(amtt_flujo.getId());
				lsb_error.append(" no puede ser vacio");

				throw new B2BException(lsb_error.toString());
			}
			else if(!StringUtils.isValidString(amtt_flujo.getEstado()))
			{
				StringBuilder lsb_error;

				lsb_error = new StringBuilder();

				lsb_error.append("El estado del flujo ");
				lsb_error.append(ls_descripcionFlujo);
				lsb_error.append(" no puede ser vacio");

				throw new B2BException(lsb_error.toString());
			}
			else if(!StringUtils.isValidString(amtt_flujo.getIndicadorDevolucion()))
			{
				StringBuilder lsb_error;

				lsb_error = new StringBuilder();

				lsb_error.append("El indicador de devolución del flujo ");
				lsb_error.append(ls_descripcionFlujo);
				lsb_error.append(" no puede ser vacio");

				throw new B2BException(lsb_error.toString());
			}
		}
		else if(lb_destino && !StringUtils.isValidString(amtt_flujo.getEstadoActividad()))
		{
			StringBuilder lsb_error;

			lsb_error = new StringBuilder();

			lsb_error.append("El estado actividad de la etapa destino del flujo ");
			lsb_error.append(ls_descripcionFlujo);
			lsb_error.append(" no puede ser vacio");

			throw new B2BException(lsb_error.toString());
		}

		return amtt_flujo;
	}

	/**
	 * Validar flujos.
	 *
	 * @param apt_proceso
	 *                        de apt proceso
	 * @return de proceso trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private ProcesoTrabajo validarFlujos(ProcesoTrabajo apt_proceso)
	    throws B2BException
	{
		Collection<MotivoTramiteTrabajo> lcmtt_flujos;

		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		lcmtt_flujos = apt_proceso.getColeccionFlujos();

		if(CollectionUtils.isValidCollection(lcmtt_flujos))
		{
			for(MotivoTramiteTrabajo lmtt_flujo : lcmtt_flujos)
			{
				if(lmtt_flujo != null)
					validarFlujo(lmtt_flujo);
			}
		}

		return apt_proceso;
	}

	/**
	 * Validar proceso.
	 *
	 * @param apt_proceso
	 *                        de apt proceso
	 * @return de proceso trabajo
	 * @throws B2BException
	 *                          de b 2 B exception
	 */
	private ProcesoTrabajo validarProceso(ProcesoTrabajo apt_proceso)
	    throws B2BException
	{
		String ls_nombreProceso;

		if(apt_proceso == null)
			throw new B2BException("No hay definicion de proceso");

		{
			String ls_idProceso;

			ls_idProceso         = apt_proceso.getId();
			ls_nombreProceso     = apt_proceso.getNombre();

			if(!StringUtils.isValidString(ls_idProceso))
				throw new B2BException("El id proceso no puede ser vacío");
			else if(!StringUtils.isValidString(ls_nombreProceso))
			{
				StringBuilder lsb_error;

				lsb_error = new StringBuilder();

				lsb_error.append("El nombre del proceso ");
				lsb_error.append(ls_idProceso);
				lsb_error.append(" no puede ser vacio");

				throw new B2BException(lsb_error.toString());
			}
		}

		{
			SubProcesoTrabajo lspt_subProceso;

			lspt_subProceso = apt_proceso.getSubProceso();

			if(lspt_subProceso != null)
			{
				{
					String ls_idSubProceso;

					ls_idSubProceso = lspt_subProceso.getIdSubproceso();

					if(!StringUtils.isValidString(ls_idSubProceso))
						throw new B2BException("El id subproceso no puede ser vacío");
					else if(!StringUtils.isValidString(lspt_subProceso.getNombre()))
					{
						StringBuilder lsb_error;

						lsb_error = new StringBuilder();

						lsb_error.append("El nombre del subproceso ");
						lsb_error.append(ls_idSubProceso);
						lsb_error.append(" no puede ser vacio");

						throw new B2BException(lsb_error.toString());
					}
				}

				{
					SubProcesoVersionTrabajo lspvt_version;

					lspvt_version = lspt_subProceso.getVersion();

					if(lspvt_version != null)
					{
						if(!StringUtils.isValidString(lspvt_version.getConservacionDocumental()))
							throw new B2BException(
							    "Se debe definir el indicador de conservación documental de la versión"
							);
					}
					else
						throw new B2BException("No hay definicion de version");
				}
			}
			else
				throw new B2BException("No hay definicion de subproceso");
		}

		{
			Collection<EtapaTrabajo> lcet_etapas;
			int                      li_etapas;

			lcet_etapas     = apt_proceso.getColeccionEtapas();
			li_etapas       = 0;

			if(CollectionUtils.isValidCollection(lcet_etapas))
			{
				Iterator<EtapaTrabajo> liet_etapas;

				liet_etapas = lcet_etapas.iterator();

				while(liet_etapas.hasNext() && (li_etapas < ci_MINIMA_CANTIDAD_ETAPAS))
				{
					EtapaTrabajo let_etapa;

					let_etapa = liet_etapas.next();

					if(let_etapa != null)
						li_etapas++;
				}
			}

			if(li_etapas < ci_MINIMA_CANTIDAD_ETAPAS)
			{
				StringBuilder lsb_error;

				lsb_error = new StringBuilder();

				lsb_error.append("El proceso ");
				lsb_error.append(ls_nombreProceso);
				lsb_error.append(" debe tener al menos ");
				lsb_error.append(ci_MINIMA_CANTIDAD_ETAPAS);
				lsb_error.append(" etapas");

				throw new B2BException(lsb_error.toString());
			}
		}

		{
			Collection<MotivoTramiteTrabajo> lcmtt_flujos;
			int                              li_flujos;
			int                              li_minimaCantidadFlujos;

			lcmtt_flujos                = apt_proceso.getColeccionFlujos();
			li_flujos                   = 0;
			li_minimaCantidadFlujos     = ci_MINIMA_CANTIDAD_ETAPAS + 1;

			if(CollectionUtils.isValidCollection(lcmtt_flujos))
			{
				Iterator<MotivoTramiteTrabajo> limtt_flujos;

				limtt_flujos = lcmtt_flujos.iterator();

				while(limtt_flujos.hasNext() && (li_flujos < li_minimaCantidadFlujos))
				{
					MotivoTramiteTrabajo lmtt_trabajo;

					lmtt_trabajo = limtt_flujos.next();

					if(lmtt_trabajo != null)
						li_flujos++;
				}
			}

			if(li_flujos < li_minimaCantidadFlujos)
			{
				StringBuilder lsb_error;

				lsb_error = new StringBuilder();

				lsb_error.append("El proceso ");
				lsb_error.append(ls_nombreProceso);
				lsb_error.append(" debe tener al menos ");
				lsb_error.append(li_minimaCantidadFlujos);
				lsb_error.append(" flujos");

				throw new B2BException(lsb_error.toString());
			}
		}

		return apt_proceso;
	}
}
