package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.reimpresion.Reimpresion;
import com.bachue.snr.prosnr01.model.reimpresion.ReimpresionRecibos;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de SDB_ACC_DOCUMENTOS_SALIDA.
 *
 * @author garias
 */
public class DocumentosSalidaDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_FIND_BY_ID_TURNO_HISTORIA_TIPO_DOCUMENTAL_ESTADO. */
	private static final String cs_FIND_BY_ID_TURNO_HISTORIA_TIPO_DOCUMENTAL_ESTADO = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA WHERE ID_TURNO_HISTORIA = ? AND ID_TIPO_DOCUMENTAL = ? AND ESTADO = ?";

	/** Constante cs_FIND_BY_D_ID_DOC_NAME_TIPO_DOCUMENTAL. */
	private static final String cs_FIND_BY_D_ID_DOC_NAME_TIPO_DOCUMENTAL = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_ECM = ? AND ID_NOMBRE_DOCUMENTO = ? AND ID_TIPO_DOCUMENTAL = ?";

	/** Constante cs_FIND_BY_ID_MENSAJE. */
	private static final String cs_FIND_BY_ID_MENSAJE = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_MENSAJE = ?";

	/** Constante cs_FIND_MAX_TURNO_HISTORIA_BY_TURNO. */
	private static final String cs_FIND_MAX_TURNO_HISTORIA_BY_TURNO = "SELECT MAX(ID_TURNO_HISTORIA) FROM "
		+ "SDB_ACC_DOCUMENTOS_SALIDA WHERE ID_TURNO = ? ";

	/** Constante cs_FIND_BY_ID_IMAGEN. */
	private static final String cs_FIND_BY_ID_IMAGEN = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_IMAGEN = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD_ESTADO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_ESTADO = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_SOLICITUD = ? AND ESTADO = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD_TIPO_DOC_Y_ESTADO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_TIPO_DOC_Y_ESTADO = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_SOLICITUD = ? AND ESTADO = ? ";

	/** Constante cs_FIND_BY_ID_TURNO_ESTADO_A. */
	private static final String cs_FIND_BY_ID_TURNO_ESTADO_A = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_TURNO = ? AND NVL(ESTADO,'A')";

	/** Constante cs_FIND_BY_ID_TURNO_ESTADO_IMPRESION. */
	private static final String cs_FIND_BY_ID_TURNO_ESTADO_IMPRESION = "SELECT NVL(ESTADO_IMPRESION,'DISPONIBLE') AS ESTADO_IMPRESION_FILTRADO"
		+ ",NVL(NUMERO_COPIAS,1) AS NUMERO_COPIAS_FILTRADO ,DS.* FROM SDB_ACC_DOCUMENTOS_SALIDA DS WHERE ID_TURNO = ? AND NVL(ESTADO_IMPRESION,'DISPONIBLE')"
		+ " IN ('DISPONIBLE','IMPRIMIENDO','ERROR') AND NVL(DS.ESTADO,'A') = 'A' AND REPOSITORIO = 'FINAL' ";

	/** Constante cs_FIND_BY_ID_TURNO_ESTADO_IMPRESION_SIN_REPO. */
	private static final String cs_FIND_BY_ID_TURNO_ESTADO_IMPRESION_SIN_REPO = "SELECT NVL(ESTADO_IMPRESION,'DISPONIBLE') AS ESTADO_IMPRESION_FILTRADO"
		+ ",NVL(NUMERO_COPIAS,1) AS NUMERO_COPIAS_FILTRADO ,DS.* FROM SDB_ACC_DOCUMENTOS_SALIDA DS WHERE ID_TURNO = ? AND NVL(ESTADO_IMPRESION,'DISPONIBLE')"
		+ " IN ('DISPONIBLE','IMPRIMIENDO','ERROR') AND NVL(DS.ESTADO,'A') = 'A'";

	/** Constante cs_FIND_BY_ID_TURNO_IMPRESO. */
	private static final String cs_FIND_BY_ID_TURNO_IMPRESO = "SELECT NVL(ESTADO_IMPRESION,'DISPONIBLE') AS ESTADO_IMPRESION_FILTRADO"
		+ ",NVL(NUMERO_COPIAS,1) AS NUMERO_COPIAS_FILTRADO ,DS.* FROM SDB_ACC_DOCUMENTOS_SALIDA DS WHERE ID_TURNO = ? AND "
		+ "ESTADO_IMPRESION = 'IMPRESO'";

	/** Constante cs_FIND_BY_ID_TURNO_ENTREGA. */
	private static final String cs_FIND_BY_ID_SOLICITUD_ESTADO_IMPRESION = "SELECT NVL(ESTADO_IMPRESION,'DISPONIBLE') AS ESTADO_IMPRESION_FILTRADO"
		+ ",NVL(NUMERO_COPIAS,1) AS NUMERO_COPIAS_FILTRADO ,DS.* FROM SDB_ACC_DOCUMENTOS_SALIDA DS WHERE ID_SOLICITUD = ? AND NVL(ESTADO_IMPRESION,'DISPONIBLE')"
		+ " IN ('DISPONIBLE','IMPRESO','IMPRIMIENDO','ERROR')";

	/** Constante cs_FIND_BY_ID_TURNO_ENTREGA. */
	private static final String cs_FIND_BY_ID_SOLICITUD_ESTADO_IMPRESION_TIPO = "SELECT NVL(ESTADO_IMPRESION,'DISPONIBLE') AS ESTADO_IMPRESION_FILTRADO"
		+ ",NVL(NUMERO_COPIAS,1) AS NUMERO_COPIAS_FILTRADO ,DS.* FROM SDB_ACC_DOCUMENTOS_SALIDA DS WHERE ID_SOLICITUD = ? AND TIPO = ? AND NVL(ESTADO_IMPRESION,'DISPONIBLE')"
		+ " IN ('DISPONIBLE','IMPRESO','IMPRIMIENDO','ERROR')";

	/** Constante cs_FIND_BY_ID_TURNO_ANT_SISTEMA. */
	private static final String cs_FIND_BY_ID_TURNO_ANT_SISTEMA = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_TURNO = ? AND ID_DATOS_ANT_SISTEMA = ? AND ESTADO <> 'I'";

	/** Constante cs_FIND_BY_ID_TURNO_HISTORIA_ANT_SISTEMA. */
	private static final String cs_FIND_BY_ID_TURNO_HISTORIA_ANT_SISTEMA = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_TURNO_HISTORIA = ? AND ID_DATOS_ANT_SISTEMA = ? AND ESTADO <> 'I'";

	/** Constante cs_FIND_BY_ID_TURNO_TIPO. */
	private static final String cs_FIND_BY_ID_TURNO_TIPO = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_TURNO = ? AND TIPO = ?";

	/** Constante cs_FIND_BY_ID_TURNO. */
	private static final String cs_FIND_BY_ID_TURNO = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_TURNO = ?";

	/** Constante cs_FIND_BY_ID_TURNO_TIPO_ACTIVO. */
	private static final String cs_FIND_BY_ID_TURNO_TIPO_ACTIVO = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_TURNO = ? AND TIPO = ? AND ESTADO = 'A'";

	/** Constante cs_FIND_BY_ID_TURNO_HISTORIA. */
	private static final String cs_FIND_BY_ID_TURNO_HISTORIA = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_TURNO_HISTORIA = ?";

	/** Constante cs_FIND_BY_ID_TURNO_HISTORIA_TIPO. */
	private static final String cs_FIND_BY_ID_TURNO_HISTORIA_TIPO = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_TURNO_HISTORIA = ? AND TIPO = ?";

	/** Constante cs_FIND_BY_ID_TURNO_HISTORIA_TIPO. */
	private static final String cs_FIND_BY_ID_TURNO_HISTORIA_NO_TIPO = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_TURNO_HISTORIA = ? AND TIPO NOT IN('COMUNICADO','COMUNICADO_INDETERMINADO') AND ESTADO = 'A'";

	/** Constante cs_FIND_BY_ID_TURNO_HISTORIA_TIPO. */
	private static final String cs_FIND_BY_ID_TURNO_HISTORIA_TIPO_DOC = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_TURNO_HISTORIA = ? AND ID_TIPO_DOCUMENTAL = ?";

	/** Constante cs_FIND_BY_ID_TURNO_TIPO_DOC. */
	private static final String cs_FIND_BY_ID_TURNO_TIPO_DOC = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_TURNO = ? AND ID_TIPO_DOCUMENTAL = ?";

	/** Constante cs_FIND_RECIBO_CAJA_LIQUIDACION. */
	private static final String cs_FIND_RECIBO_CAJA_LIQUIDACION = " SELECT SADC.ID_DOCUMENTO_SALIDA, SADC.ID_ECM, SADC.ID_NOMBRE_DOCUMENTO, SADC.FECHA_ENVIO, SADC.FECHA_RADICACION_ECM, NVL(SADC.ESTADO_IMPRESION,'DISPONIBLE') AS ESTADO_IMPRESION_FILTRADO, NVL(SADC.NUMERO_COPIAS,1) AS NUMERO_COPIAS_FILTRADO, SAC.NIR, SADC.ID_TURNO, LIQ.NUMERO_REFERENCIA, SADC.ID_TIPO_DOCUMENTAL, SAC.ID_PROCESO, LIQ.FECHA_LIQUIDACION "
		+ " FROM SDB_ACC_DOCUMENTOS_SALIDA SADC "
		+ " INNER JOIN SDB_ACC_SOLICITUD SAC ON SAC.ID_SOLICITUD = SADC.ID_SOLICITUD  "
		+ " INNER JOIN SDB_ACC_LIQUIDACION LIQ ON LIQ.ID_SOLICITUD = SADC.ID_SOLICITUD AND LIQ.NUMERO_REFERENCIA IS NOT NULL WHERE ";

	/** Constante cs_FIND_REIMPRESION_TURNOS. */
	private static final String cs_FIND_REIMPRESION_TURNOS = " SELECT NVL(SADC.ESTADO_IMPRESION,'DISPONIBLE') AS ESTADO_IMPRESION_FILTRADO, NVL(SADC.NUMERO_COPIAS,1) AS NUMERO_COPIAS_FILTRADO, SAS.NIR, SADC.ID_TURNO, SATH.MOTIVO_NO_TRAMITE, SAT.ID_ETAPA_ACTUAL,SADC.ID_TIPO_DOCUMENTAL,SADC.FECHA_CREACION,SADC.* "
		+ " FROM SDB_ACC_DOCUMENTOS_SALIDA SADC " + " INNER JOIN SDB_ACC_TURNO SAT ON SAT.ID_TURNO = SADC.ID_TURNO  "
		+ " INNER JOIN SDB_ACC_TURNO_HISTORIA SATH ON SATH.ID_TURNO_HISTORIA = SADC.ID_TURNO_HISTORIA "
		+ " INNER JOIN SDB_ACC_SOLICITUD SAS ON SAS.ID_SOLICITUD = SADC.ID_SOLICITUD WHERE SADC.ID_TURNO = ? "
		+ " AND (SAT.ID_ETAPA_ACTUAL >= ? AND SAT.ID_ETAPA_ACTUAL < ?) AND SATH.ESTADO_ACTIVIDAD = 'TER' "
		+ " AND SADC.ESTADO_IMPRESION='IMPRESO' " + " AND SADC.ID_TIPO_DOCUMENTAL NOT IN ('38','39') ";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_DOCUMENTOS_SALIDA_ID_DOCUMENTO_SALIDA.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET "
		+ " ID_TURNO_HISTORIA = ?,ID_SOLICITUD = ?,ID_TURNO = ?,"
		+ " ID_IMAGEN = ?,DESTINATARIO = ?,DIRECCION = ?,ID_PAIS = ?,ID_DEPARTAMENTO = ?,"
		+ " ID_MUNICIPIO = ?,OBSERVACIONES = ?,REFERENCIA_SALIDA = ?,TIPO_ARCHIVO = ?,ID_TIPO_DOCUMENTAL = ?,"
		+ " TIPO = ?,NUMERO_RESOL_ASOCIADA = ?,FECHA_RESOL_ASOCIADA = ?,ESTADO = ?, REPOSITORIO = ?,ID_ECM = ?,FECHA_ENVIO = ?,FECHA_RADICACION_ECM = ?,"
		+ " FECHA_MODIFICACION = SYSTIMESTAMP,ID_USUARIO_MODIFICACION = ?,IP_MODIFICACION = ?, CORREO_ELECTRONICO=?, TELEFONO=?, ID_DATOS_ANT_SISTEMA = ?, DOCUMENTO_FINAL = ?, CONSECUTIVO_RESOLUCION = ?, FECHA_RESOLUCION = ?, CONSECUTIVO_OFICIO = ?, FECHA_CONSECUTIVO_OFICIO = ?, ID_NOMBRE_DOCUMENTO = ?, ID_PERSONA_NOTIFICACION = ?, FECHA_PUBLICACION_AVISO_WEB = ?, FECHA_DESFIJACION_AVISO_WEB = ? WHERE ID_DOCUMENTO_SALIDA = ?";

	/**  Constante cs_UPDATE_ESTADO. */
	private static final String cs_UPDATE_ESTADO = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET ESTADO = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE ID_SOLICITUD = ? AND ID_TURNO = ? AND TIPO ='CREACION_MATRICULA' AND ESTADO = 'E'";

	/**  Constante CS_UPDATE_REIMPRESION. */
	private static final String CS_UPDATE_REIMPRESION = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET REIMPRESION=?, ESTADO_IMPRESION='IMPRESO', FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante CS_UPDATE_ACUSE_RECIBIDO. */
	private static final String CS_UPDATE_ACUSE_RECIBIDO = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET "
		+ "FECHA_GUIA=?, NUMERO_GUIA = ?, FECHA_ENVIO_COMPONENTE = ?, FECHA_ACUSE_RECIBO_COMPONENTE = ?, "
		+ "FECHA_NOTIFICACION = ?, TIPO_NOTIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, "
		+ "ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_UPDATE_OFICINA_ORIGEN_BY_TURNO. */
	private static final String cs_UPDATE_OFICINA_ORIGEN_BY_TURNO = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET DESTINATARIO = ?, DIRECCION = ?, ID_DEPARTAMENTO = ?, ID_MUNICIPIO = ?, CORREO_ELECTRONICO = ?, TELEFONO = ?, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_TURNO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_DOCUMENTOS_SALIDA (ID_DOCUMENTO_SALIDA,"
		+ "ID_TURNO_HISTORIA,ID_SOLICITUD,ID_TURNO,ID_IMAGEN,DESTINATARIO,DIRECCION,ID_PAIS,ID_DEPARTAMENTO,"
		+ "ID_MUNICIPIO,OBSERVACIONES,REFERENCIA_SALIDA,TIPO_ARCHIVO,ID_TIPO_DOCUMENTAL,TIPO,"
		+ "NUMERO_RESOL_ASOCIADA,FECHA_RESOL_ASOCIADA,ESTADO,REPOSITORIO,ID_ECM,FECHA_ENVIO,FECHA_RADICACION_ECM,FECHA_CREACION,"
		+ "ID_USUARIO_CREACION,IP_CREACION, DOCUMENTO_AUTOMATICO, CORREO_ELECTRONICO, TELEFONO, ID_DATOS_ANT_SISTEMA,DOCUMENTO_FINAL,CONSECUTIVO_RESOLUCION,FECHA_RESOLUCION,CONSECUTIVO_OFICIO,FECHA_CONSECUTIVO_OFICIO, ID_NOMBRE_DOCUMENTO, ID_PERSONA_NOTIFICACION,FECHA_PUBLICACION_AVISO_WEB,FECHA_DESFIJACION_AVISO_WEB,MEDIO_PUBLICACION,RESPONSABLE_PUBLICACION_WEB,FECHA_RECIBO_AVISO_WEB) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	/** Constante cs_FIND_DOCUMENT. */
	private static final String cs_FIND_DOCUMENT = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_SOLICITUD = ? AND TIPO = ? AND ESTADO = ?";

	/** Constante cs_FIND_DOCUMENT_BY_TURNO_TIPO_ESTADO. */
	private static final String cs_FIND_DOCUMENT_BY_TURNO_TIPO_ESTADO = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_SOLICITUD = ? AND ID_TURNO = ? AND TIPO = ? AND ESTADO = ?";

	/** Constante cs_FIND_BY_TURNO_HISTORIA_TIPO_DOCUMENTAL. */
	private static final String cs_FIND_BY_TURNO_HISTORIA_TIPO_DOCUMENTAL = "SELECT NVL(ESTADO_IMPRESION,'DISPONIBLE') AS ESTADO_IMPRESION_FILTRADO, NVL(NUMERO_COPIAS,1) AS NUMERO_COPIAS_FILTRADO ,DS.* "
		+ "FROM SDB_ACC_DOCUMENTOS_SALIDA DS "
		+ "WHERE ID_TURNO_HISTORIA = ? AND ID_TIPO_DOCUMENTAL = ? ORDER BY FECHA_CREACION DESC";

	/** Constante cs_FIND_DOCUMENT_BY_TURNO_TIPO_DOCUMENTAL_ESTADO. */
	private static final String cs_FIND_DOCUMENT_BY_TURNO_TIPO_DOCUMENTAL_ESTADO = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ";

	/** Constante cs_FIND_DOCUMENT_BY_OUTPUT_REFERENCE. */
	private static final String cs_FIND_DOCUMENT_BY_OUTPUT_REFERENCE = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_SOLICITUD = ? AND ID_TURNO = ? AND TIPO = ? AND ESTADO = ? ";

	/** Constante cs_FIND_BY_TURNO_TIPO_ESTADO_DESTINATARIO. */
	private static final String cs_FIND_BY_TURNO_TIPO_ESTADO_DESTINATARIO = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_TURNO = ? AND TIPO = ? AND ESTADO = ? AND DESTINATARIO = ?";

	/** Constante cs_FIND_DOCUMENT_BY_TURNO_HISTORIA_TIPO_ESTADO. */
	private static final String cs_FIND_DOCUMENT_BY_TURNO_HISTORIA_TIPO_ESTADO = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_SOLICITUD = ? AND ID_TURNO_HISTORIA = ? AND TIPO = ? AND ESTADO = ?";

	/** Constante cs_FIND_DOCUMENT_BY_TURNO_HISTORIA_TIPO_DOC_ESTADO. */
	private static final String cs_FIND_DOCUMENT_BY_TURNO_HISTORIA_TIPO_DOC_ESTADO = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_SOLICITUD = ? AND ID_TURNO_HISTORIA = ? AND ID_TIPO_DOCUMENTAL = ? AND ESTADO = ?";

	/** Constante cs_FIND_DOCUMENTS_ECM. */
	private static final String cs_FIND_DOCUMENTS_ECM = "SELECT DS.* FROM SDB_ACC_DOCUMENTOS_SALIDA DS "
		+ " INNER JOIN SDB_ACC_SOLICITUD SAS ON SAS.ID_SOLICITUD = DS.ID_SOLICITUD "
		+ " INNER JOIN SDB_BGN_IMAGENES I ON (I.ID_IMAGEN = DS.ID_IMAGEN) AND dbms_lob.getlength(I.IMAGEN_BLOB) > 0 "
		+ " WHERE DS.ID_ECM IS NULL AND DS.ID_NOMBRE_DOCUMENTO IS NULL AND DS.FECHA_ENVIO IS NULL "
		+ " AND NVL(DS.INTENTO_ENVIO,0) < (SELECT ENTERO FROM SDB_PGN_CONSTANTES WHERE ID_CONSTANTE = '"
		+ ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_REINTENTOS + "')"
		+ " AND SAS.NIR IS NOT NULL ORDER BY DS.FECHA_CREACION ASC ";

	/** Constante cs_DELETE_DOCUMENTO_BY_ID_IMAGEN. */
	private static final String cs_DELETE_DOCUMENTO_BY_ID_IMAGEN = "DELETE FROM SDB_ACC_DOCUMENTOS_SALIDA WHERE ID_IMAGEN = ?";

	/** Constante cs_DELETE_DOCUMENTO_BY_ID_IMAGEN. */
	private static final String cs_DELETE_DOCUMENTO_BY_ID_TURNO_HISTORIA = "DELETE FROM SDB_ACC_DOCUMENTOS_SALIDA WHERE ID_TURNO_HISTORIA = ?";

	/** Constante cs_DELETE_DOCUMENTO_BY_ID_TURNO_HISTORIA_TIPO. */
	private static final String cs_DELETE_DOCUMENTO_BY_ID_TURNO_HISTORIA_TIPO = "DELETE FROM SDB_ACC_DOCUMENTOS_SALIDA WHERE ID_TURNO_HISTORIA = ? AND TIPO = ?";

	/** Constante cs_DELETE_DOCUMENTO_BY_ID_TURNO_Y_PERSONA_NOTIFICAR. */
	private static final String cs_DELETE_DOCUMENTO_BY_ID_TURNO_Y_PERSONA_NOTIFICAR = "DELETE FROM SDB_ACC_DOCUMENTOS_SALIDA WHERE ID_TURNO = ? "
		+ "AND TIPO = 'COMUNICADO' AND ID_TIPO_DOCUMENTAL = '81' AND ID_PERSONA_NOTIFICACION = ? ";

	/** Constante cs_UPDATE_IMAGEN. */
	private static final String cs_UPDATE_IMAGEN = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET ID_IMAGEN = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_UPDATE_ID_MENSAJE. */
	private static final String cs_UPDATE_ID_MENSAJE = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET ID_MENSAJE = ?, FECHA_RESPUESTA_MENSAJE = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_UPDATE_REPOSITORIO. */
	private static final String cs_UPDATE_REPOSITORIO = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET REPOSITORIO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_UPDATE_ESTADO_ANY. */
	private static final String cs_UPDATE_ESTADO_ANY = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET ESTADO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_UPDATE_FECHA_PUBLICACION_AVISO_WEB. */
	private static final String cs_UPDATE_FECHA_PUBLICACION_AVISO_WEB = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET FECHA_PUBLICACION_AVISO_WEB = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_UPDATE_FECHA_DESFIJACION_AVISO_WEB. */
	private static final String cs_UPDATE_FECHA_DESFIJACION_AVISO_WEB = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET FECHA_DESFIJACION_AVISO_WEB = ?, "
		+ "TIPO_NOTIFICACION = ?, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_UPDATE_IMAGEN. */
	private static final String cs_UPDATE_ESTADO_IMPRESION = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET ESTADO_IMPRESION = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_UPDATE_ID_TURNO_HISTORIA. */
	private static final String cs_UPDATE_ID_TURNO_HISTORIA = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET ID_TURNO_HISTORIA = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_UPDATE_ID_ECM_AND_DOCNAME. */
	private static final String cs_UPDATE_ID_ECM_AND_DOCNAME = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET ID_ECM = ?, ID_NOMBRE_DOCUMENTO = ?,FECHA_ENVIO = ? ,"
		+ "ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_UPDATE_MEDIO_PUBLICACION. */
	private static final String cs_UPDATE_MEDIO_PUBLICACION = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET FECHA_RECIBO_AVISO_WEB = ?, FECHA_PUBLICACION_AVISO_WEB= ?,FECHA_DESFIJACION_AVISO_WEB = ?, MEDIO_PUBLICACION = ?, ID_USUARIO_MODIFICACION = ?,FECHA_MODIFICACION = SYSTIMESTAMP,"
		+ "IP_MODIFICACION = ? WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_UPDATE_MEDIO_PUBLICACION. */
	private static final String cs_UPDATE_RESPONSABLE_PUBLICACION_WEB = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET RESPONSABLE_PUBLICACION_WEB = ?, ID_USUARIO_MODIFICACION = ?,FECHA_MODIFICACION = SYSTIMESTAMP,"
		+ "IP_MODIFICACION = ? WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_ACTUALIZAR_ENVIO_EXITOSO. */
	private static final String cs_ACTUALIZAR_ENVIO_EXITOSO = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET ID_ECM = ?, "
		+ "ID_NOMBRE_DOCUMENTO = ?, FECHA_ENVIO = SYSTIMESTAMP, FECHA_RADICACION_ECM = SYSTIMESTAMP, ESTADO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_ACTUALIZAR_ENVIO_FALLIDO. */
	private static final String cs_ACTUALIZAR_ENVIO_FALLIDO = "UPDATE SDB_ACC_DOCUMENTOS_SALIDA SET INTENTO_ENVIO = ?, "
		+ "FECHA_INTENTO_ENVIO = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_DOCUMENTO_SALIDA = ?";

	/** Constante cs_BUSCAR_POR_ID_TURNO_HISTORIA_TIPO_AUTO_RESOLUCION. */
	private static final String cs_BUSCAR_POR_ID_TURNO_HISTORIA_TIPO_AUTO_RESOLUCION = "SELECT * FROM SDB_ACC_DOCUMENTOS_SALIDA "
		+ " WHERE ID_TURNO_HISTORIA = ? AND (TIPO LIKE '%AUTO%' OR TIPO LIKE '%RESOLUCION%') ";

	/**
	 * Instancia un nuevo objeto documentos salida DAO.
	 */
	public DocumentosSalidaDAO()
	{
	}

	/**
	 * Metodo encargado de realizar la actualizacion de un registro en la tabla SDB_ACC_DOCUMENTOS_SALIDA cuando se realiza un envio exitoso al OWCC.
	 *
	 * @param apd_parametros Documento contenedor de datos para utilizar en la actualizacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarEnvioExitoso(DocumentosSalida apd_parametros)
	    throws B2BException
	{
		if(apd_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_ACTUALIZAR_ENVIO_EXITOSO);

				lps_ps.setString(li_column++, apd_parametros.getIdEcm());
				lps_ps.setString(li_column++, apd_parametros.getIdNombreDocumento());
				lps_ps.setString(li_column++, apd_parametros.getEstado());
				lps_ps.setString(li_column++, apd_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, apd_parametros.getIpModificacion());

				lps_ps.setLong(li_column++, apd_parametros.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de realizar la actualizacion de un registro en la tabla SDB_ACC_DOCUMENTOS_SALIDA cuando se realiza un envio fallido al OWCC.
	 *
	 * @param apd_parametros Documento contenedor de datos para utilizar en la actualizacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarEnvioFallido(DocumentosSalida apd_parametros)
	    throws B2BException
	{
		if(apd_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_ACTUALIZAR_ENVIO_FALLIDO);

				lps_ps.setInt(li_column++, apd_parametros.getIntentoEnvio());
				lps_ps.setString(li_column++, apd_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, apd_parametros.getIpModificacion());

				lps_ps.setLong(li_column++, apd_parametros.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Buscarpor id turno historia tipo auto resolucion.
	 *
	 * @param al_idTurnoHistoria de id turno historia
	 * @return el valor de documentos salida
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DocumentosSalida buscarporIdTurnoHistoriaTipoAutoResolucion(Long al_idTurnoHistoria)
	    throws B2BException
	{
		DocumentosSalida  lds_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lds_return     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(NumericUtils.isValidLong(al_idTurnoHistoria))
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(
					    cs_BUSCAR_POR_ID_TURNO_HISTORIA_TIPO_AUTO_RESOLUCION
					);

				setLong(lps_ps, al_idTurnoHistoria, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_return = getDocumentosSalida(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarporIdTurnoHistoriaTipoAutoResolucion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_return;
	}

	/**
	 * Metodo encargado de eliminar de la tabla SDB_ACC_DOCUMENTOS_SALIDA por el ID_IMAGEN.
	 *
	 * @param ads_parametros correspondiente al valor del tipo de objeto DocumentosSalida
	 * @return DocumentosSalida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public DocumentosSalida deleteByIdImagen(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		DocumentosSalida  lpce_correo;
		PreparedStatement lps_ps;

		lpce_correo     = ads_parametros;
		lps_ps          = null;

		if(ads_parametros != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_DELETE_DOCUMENTO_BY_ID_IMAGEN);

				lps_ps.setLong(li_contador++, NumericUtils.getLong(ads_parametros.getIdImagen()));

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByIdImagen", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}

		return lpce_correo;
	}

	/**
	 * Metodo encargado de eliminar de la tabla SDB_ACC_DOCUMENTOS_SALIDA por el ID_TURNO_HISTORIA.
	 *
	 * @param al_idTurnoHistoria de id turno historia
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public void deleteByIdTurnoHistoria(long al_idTurnoHistoria)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		if(al_idTurnoHistoria > 0)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE_DOCUMENTO_BY_ID_TURNO_HISTORIA);

				setLong(lps_ps, NumericUtils.getLongWrapper(al_idTurnoHistoria), 1);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByIdTurnoHistoria", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de eliminar de la tabla SDB_ACC_DOCUMENTOS_SALIDA por el ID_TURNO_HISTORIA Y TIPO.
	 *
	 * @param al_idTurnoHistoria de id turno historia
	 * @param as_tipo de tipo documento.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public void deleteByIdTurnoHistoriaTipo(long al_idTurnoHistoria, String as_tipo)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		if(al_idTurnoHistoria > 0)
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE_DOCUMENTO_BY_ID_TURNO_HISTORIA_TIPO);

				setLong(lps_ps, NumericUtils.getLongWrapper(al_idTurnoHistoria), li_column++);
				lps_ps.setString(li_column++, as_tipo);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByIdTurnoHistoriaTipo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de eliminar de la tabla SDB_ACC_DOCUMENTOS_SALIDA por el ID_TURNO y PERSONA_NOTIFICACION.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_idPersonaNotificacion de as id persona notificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public void deleteByIdTurnoYPersonaNotificacion(String as_idTurno, String as_idPersonaNotificacion)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		if(StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_idPersonaNotificacion))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE_DOCUMENTO_BY_ID_TURNO_Y_PERSONA_NOTIFICAR);

				lps_ps.setString(1, as_idTurno);
				lps_ps.setString(2, as_idPersonaNotificacion);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByIdTurnoYPersonaNotificacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_SOLICITUD, un TIPO y un ESTADO.
	 *
	 * @param ads_parametros correspondiente al valor del tipo de objeto DocumentosSalida
	 * @return devuelve el valor de Collection de DocumentosSalida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findAllDocumentBySolicitudTipoEstado(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		return (ads_parametros != null)
		? findAllDocumentBySolicitudTipoEstado(
		    ads_parametros.getIdSolicitud(), ads_parametros.getTipo(), ads_parametros.getEstado()
		) : null;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_SOLICITUD, un TIPO y un ESTADO.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_tipo de as tipo
	 * @param as_estado de as estado
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DocumentosSalida> findAllDocumentBySolicitudTipoEstado(
	    String as_idSolicitud, String as_tipo, String as_estado
	)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;

		lcds_documentoSalida = new ArrayList<DocumentosSalida>();

		if(
		    StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_tipo)
			    && StringUtils.isValidString(as_estado)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_DOCUMENT);

				lps_ps.setString(li_contador++, as_idSolicitud);
				lps_ps.setString(li_contador++, as_tipo);
				lps_ps.setString(li_contador++, as_estado);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllDocumentBySollicitudTipoEstado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcds_documentoSalida.isEmpty())
			lcds_documentoSalida = null;

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_SOLICITUD, un ID_TURNO_HISTORIA, un TIPO_DOC y un ESTADO.
	 *
	 * @param ads_parametros correspondiente al valor del tipo de objeto DocumentosSalida
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findAllDocumentByTurnoHistoriaTipoDocEstado(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lcds_documentoSalida     = null;
		lps_ps                   = null;
		lrs_rs                   = null;

		if(ads_parametros != null)
		{
			try
			{
				int li_contador;

				li_contador              = 1;
				lcds_documentoSalida     = new ArrayList<DocumentosSalida>();

				lps_ps = getConnection().prepareStatement(cs_FIND_DOCUMENT_BY_TURNO_HISTORIA_TIPO_DOC_ESTADO);

				lps_ps.setString(li_contador++, ads_parametros.getIdSolicitud());
				setInteger(lps_ps, ads_parametros.getIdTurnoHistoria(), li_contador++);
				lps_ps.setString(li_contador++, ads_parametros.getIdTipoDocumental());
				lps_ps.setString(li_contador++, ads_parametros.getEstado());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllDocumentByTurnoHistoriaTipoEstado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_SOLICITUD, un ID_TURNO_HISTORIA, un TIPO y un ESTADO.
	 *
	 * @param ads_parametros correspondiente al valor del tipo de objeto DocumentosSalida
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findAllDocumentByTurnoHistoriaTipoEstado(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lcds_documentoSalida     = null;
		lps_ps                   = null;
		lrs_rs                   = null;

		if(ads_parametros != null)
		{
			try
			{
				int li_contador;

				li_contador              = 1;
				lcds_documentoSalida     = new ArrayList<DocumentosSalida>();

				lps_ps = getConnection().prepareStatement(cs_FIND_DOCUMENT_BY_TURNO_HISTORIA_TIPO_ESTADO);

				lps_ps.setString(li_contador++, ads_parametros.getIdSolicitud());
				setInteger(lps_ps, ads_parametros.getIdTurnoHistoria(), li_contador++);
				lps_ps.setString(li_contador++, ads_parametros.getTipo());
				lps_ps.setString(li_contador++, ads_parametros.getEstado());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllDocumentByTurnoHistoriaTipoEstado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcds_documentoSalida;
	}

	/**
	 * Método encargado de consultar con base al dId, DocName e idTipoDocumental.
	 *
	 * @param as_dId Varieble que contiene el dato de dId.
	 * @param as_docName Varible que contiene el docName.
	 * @param as_idTipoDocumental Variable que contiene el idTipoDocumental.
	 * @return Objeto que contiene el documento consultado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DocumentosSalida findByDIdDocNameTipoDocumental(
	    String as_dId, String as_docName, String as_idTipoDocumental
	)
	    throws B2BException
	{
		DocumentosSalida lds_return;

		lds_return = null;

		if(
		    StringUtils.isValidString(as_dId) && StringUtils.isValidString(as_docName)
			    && StringUtils.isValidString(as_idTipoDocumental)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_D_ID_DOC_NAME_TIPO_DOCUMENTAL);

				lps_ps.setString(li_contador++, as_dId);
				lps_ps.setString(li_contador++, as_docName);
				lps_ps.setString(li_contador++, as_idTipoDocumental);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_return = getDocumentosSalida(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByDIdDocNameTipoDocumental", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_return;
	}

	/**
	 *  Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA un registro por un ID_DOCUMENTO_SALIDA.
	 *
	 * @param ads_parametros correspondiente al valor del tipo de objeto DocumentosSalida
	 * @return devuelve el valor de DocumentosSalida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public DocumentosSalida findById(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		return (ads_parametros != null) ? findById(ads_parametros.getIdDocumentoSalida()) : null;
	}

	/**
	 *  Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA un registro por un ID_DOCUMENTO_SALIDA.
	 *
	 * @param al_idDocumentoSalida de al id documento salida
	 * @return devuelve el valor de DocumentosSalida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public DocumentosSalida findById(long al_idDocumentoSalida)
	    throws B2BException
	{
		DocumentosSalida lds_return;

		lds_return = null;

		if(al_idDocumentoSalida > 0L)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setLong(li_contador++, al_idDocumentoSalida);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_return = getObjectResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_return;
	}

	/**
	 * Consulta en la base de datos un registro que coincida con un id imagen
	 * enviado en el filtro.
	 *
	 * @param ads_parametros Objeto contenedor del filtro para ser utilizado en
	 * la consulta
	 * @return Documento salida resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public DocumentosSalida findByIdImagen(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		return ((ads_parametros != null) ? findByIdImagen(ads_parametros.getIdImagen()) : null);
	}

	/**
	 * Consulta en la base de datos un registro que coincida con un id imagen
	 * enviado en el filtro.
	 *
	 * @param al_idImagen Objeto contenedor del filtro para ser utilizado en
	 * la consulta
	 * @return Documento salida resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public DocumentosSalida findByIdImagen(Long al_idImagen)
	    throws B2BException
	{
		DocumentosSalida lpce_correo;
		lpce_correo = null;

		if(NumericUtils.isValidLong(al_idImagen, 1L))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_IMAGEN);

				setLong(lps_ps, al_idImagen, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpce_correo = getDocumentosSalida(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdImagen", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpce_correo;
	}

	/**
	 *  Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA un registro por un ID_DOCUMENTO_SALIDA.
	 *
	 * @param al_idDocumentoSalida de al id documento salida
	 * @return devuelve el valor de DocumentosSalida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public DocumentosSalida findByIdImpresion(long al_idDocumentoSalida)
	    throws B2BException
	{
		DocumentosSalida lds_return;

		lds_return = null;

		if(al_idDocumentoSalida > 0)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection()
						         .prepareStatement(
						    "SELECT NVL(ESTADO_IMPRESION,'DISPONIBLE') AS ESTADO_IMPRESION_FILTRADO,NVL(NUMERO_COPIAS,1) AS NUMERO_COPIAS_FILTRADO ,DS.* FROM SDB_ACC_DOCUMENTOS_SALIDA DS WHERE DS.ID_DOCUMENTO_SALIDA = ?"
						);

				lps_ps.setLong(li_contador++, al_idDocumentoSalida);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_return = getDocumentosSalida(lrs_rs, true);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdImpresion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_return;
	}

	/**
	 * Find by id mensaje.
	 *
	 * @param as_idMensaje de as id mensaje
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DocumentosSalida> findByIdMensaje(String as_idMensaje)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_response;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lcds_response     = new ArrayList<DocumentosSalida>();
		lps_ps            = null;
		lrs_rs            = null;

		if(as_idMensaje != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_MENSAJE);

				lps_ps.setString(li_contador++, as_idMensaje);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_response.add(getObjectResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdMensaje", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcds_response;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA un registro por un ID_SOLICITUD.
	 *
	 * @param ads_parametros correspondiente al valor del tipo de objeto DocumentosSalida
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdSolicitud(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lcds_documentoSalida     = new ArrayList<DocumentosSalida>();
		lps_ps                   = null;
		lrs_rs                   = null;

		if(ads_parametros != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(li_contador++, ads_parametros.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA un registro por un ID_SOLICITUD.
	 *
	 * @param ads_parametros correspondiente al valor del tipo de objeto DocumentosSalida
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdSolicitudEstado(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lcds_documentoSalida     = new ArrayList<DocumentosSalida>();
		lps_ps                   = null;
		lrs_rs                   = null;

		if(ads_parametros != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_ESTADO);

				lps_ps.setString(li_contador++, ads_parametros.getIdSolicitud());
				lps_ps.setString(li_contador++, ads_parametros.getEstado());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudEstado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_SOLICITUD y ESTADO activo (A).
	 *
	 * @param as_idSolicitud Corresponde al id de la solicitud a consultar
	 * @return devuelve la colección de documentos.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdSolicitudEstadoImpresion(String as_idSolicitud)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;

		lcds_documentoSalida = new ArrayList<DocumentosSalida>();

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_ESTADO_IMPRESION);

				lps_ps.setString(li_contador++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudEstadoImpresion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcds_documentoSalida))
			lcds_documentoSalida = null;

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_SOLICITUD y ESTADO activo (A).
	 *
	 * @param as_idSolicitud Corresponde al id de la solicitud a consultar
	 * @param as_tipo de as tipo
	 * @return devuelve la colección de documentos.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdSolicitudEstadoImpresionTipo(String as_idSolicitud, String as_tipo)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;

		lcds_documentoSalida = new ArrayList<DocumentosSalida>();

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_tipo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_ESTADO_IMPRESION_TIPO);

				lps_ps.setString(li_contador++, as_idSolicitud);
				lps_ps.setString(li_contador++, as_tipo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudEstadoImpresion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcds_documentoSalida))
			lcds_documentoSalida = null;

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA un registro por un ID_SOLICITUD.
	 *
	 * @param ads_parametros correspondiente al valor del tipo de objeto DocumentosSalida
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public DocumentosSalida findByIdSolicitudTipoDocYEstado(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		DocumentosSalida  lds_documentoSalida;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lds_documentoSalida     = null;
		lps_ps                  = null;
		lrs_rs                  = null;

		if(ads_parametros != null)
		{
			try
			{
				int           li_contador;
				String        ls_idTipoDoc;
				StringBuilder lsb_query;

				li_contador      = 1;
				lsb_query        = new StringBuilder();
				ls_idTipoDoc     = ads_parametros.getIdTipoDocumental();

				lsb_query.append(cs_FIND_BY_ID_SOLICITUD_TIPO_DOC_Y_ESTADO);

				if(
				    StringUtils.isValidString(ls_idTipoDoc)
					    && !ls_idTipoDoc.equalsIgnoreCase(TipoDocumentalCommon.RECIBO_LIQUIDACION)
				)
					lsb_query.append("AND ID_TIPO_DOCUMENTAL = ?");
				else
					lsb_query.append("AND ID_TIPO_DOCUMENTAL IS NULL");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_contador++, ads_parametros.getIdSolicitud());

				lps_ps.setString(li_contador++, ads_parametros.getEstado());

				if(
				    StringUtils.isValidString(ls_idTipoDoc)
					    && !ls_idTipoDoc.equalsIgnoreCase(TipoDocumentalCommon.RECIBO_LIQUIDACION)
				)
					lps_ps.setString(li_contador++, ls_idTipoDoc);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_documentoSalida = getDocumentosSalida(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudTipoDocYEstado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_documentoSalida;
	}

	/**
	 * Método encargado de consultar los documentos salida con base a un id turno y id datos ant sistema.
	 *
	 * @param ads_parametros Objeto que contiene los datos para realizar la consulta.
	 * @return Colección que contiene los documentos consultados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoAntSistema(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;

		lcds_documentoSalida = new ArrayList<DocumentosSalida>();

		if(ads_parametros != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_contador;
				StringBuilder lsb_query;

				li_contador     = 1;
				lsb_query       = new StringBuilder();

				lsb_query.append(cs_FIND_BY_ID_TURNO_ANT_SISTEMA);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_contador++, ads_parametros.getIdTurno());
				lps_ps.setString(li_contador++, ads_parametros.getIdDatosAntSistema());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoAntSistema", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcds_documentoSalida))
			lcds_documentoSalida = null;

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO y ESTADO activo (A).
	 *
	 * @param ads_parametros correspondiente al valor del tipo de objeto DocumentosSalida
	 * @param ab_activos correspondiente al valor del tipo de objeto boolean
	 * @param ab_repositorio de ab repositorio
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoEstadoA(
	    DocumentosSalida ads_parametros, boolean ab_activos, boolean ab_repositorio
	)
	    throws B2BException
	{
		return (ads_parametros != null) ? findByIdTurnoEstadoA(ads_parametros.getIdTurno(), ab_activos, ab_repositorio)
		                                : null;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO y ESTADO activo (A).
	 *
	 * @param ads_parametros correspondiente al valor del tipo de objeto DocumentosSalida
	 * @param ab_activos correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoEstadoA(DocumentosSalida ads_parametros, boolean ab_activos)
	    throws B2BException
	{
		return (ads_parametros != null) ? findByIdTurnoEstadoA(ads_parametros.getIdTurno(), ab_activos, false) : null;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO y ESTADO activo (A).
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto id turno
	 * @param ab_activos correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoEstadoA(String as_idTurno, boolean ab_activos)
	    throws B2BException
	{
		return findByIdTurnoEstadoA(as_idTurno, ab_activos, false);
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO y ESTADO activo (A).
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto id turno
	 * @param ab_activos correspondiente al valor del tipo de objeto boolean
	 * @param ab_repositorio de ab repositorio
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoEstadoA(
	    String as_idTurno, boolean ab_activos, boolean ab_repositorio
	)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;

		lcds_documentoSalida = new ArrayList<DocumentosSalida>();

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_contador;
				StringBuilder lsb_query;

				li_contador     = 1;
				lsb_query       = new StringBuilder();

				lsb_query.append(cs_FIND_BY_ID_TURNO_ESTADO_A);

				if(ab_activos)
					lsb_query.append(" = 'A' ");
				else
					lsb_query.append(" <> 'I' ");

				if(ab_repositorio)
					lsb_query.append(" AND REPOSITORIO ='FINAL' ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_contador++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoEstadoA", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcds_documentoSalida))
			lcds_documentoSalida = null;

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO y ESTADO activo (A).
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto DocumentosSalida
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoEstadoImpresion(String as_idTurno, boolean ab_cancelacion)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;

		lcds_documentoSalida = new ArrayList<DocumentosSalida>();

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador = 1;

				StringBuilder lsb_query;

				li_contador     = 1;
				lsb_query       = new StringBuilder(cs_FIND_BY_ID_TURNO_ESTADO_IMPRESION);

				if(ab_cancelacion)
					lsb_query.append(" AND ID_USUARIO_CREACION = 'CORE_BACHUE'");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_contador++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoEstadoImpresion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcds_documentoSalida))
			lcds_documentoSalida = null;

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO y ESTADO activo (A).
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto DocumentosSalida
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoEstadoImpresionSinRepo(String as_idTurno)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;

		lcds_documentoSalida = new ArrayList<DocumentosSalida>();

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_ESTADO_IMPRESION_SIN_REPO);

				lps_ps.setString(li_contador++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoEstadoImpresion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcds_documentoSalida))
			lcds_documentoSalida = null;

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO y ESTADO activo (A).
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto DocumentosSalida
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoEstadoImpreso(String as_idTurno)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;

		lcds_documentoSalida = new ArrayList<DocumentosSalida>();

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_IMPRESO);

				lps_ps.setString(li_contador++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoEstadoImpreso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcds_documentoSalida))
			lcds_documentoSalida = null;

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO y ESTADO activo (A).
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto id turno
	 * @param ab_activos correspondiente al valor del tipo de objeto boolean
	 * @param ab_repositorio de ab repositorio
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoExcluyente(String as_idTurno, boolean ab_repositorio)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;

		lcds_documentoSalida = new ArrayList<DocumentosSalida>();

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_contador;
				StringBuilder lsb_query;

				li_contador     = 1;
				lsb_query       = new StringBuilder();

				lsb_query.append(cs_FIND_BY_ID_TURNO);

				lsb_query.append(
				    " AND TIPO NOT IN ('INDICE_ELECTRONICO'," + "'CREACION_MATRICULA',"
				    + "'SOLICITUD_DOCUMENTO_CORRECCIONES'," + "'RELACION_APROBACION'," + "'SOLICITUD',"
				    + "'SOLICITUD_CORRECCION'," + "'RECIBO_LIQUIDACION')"
				);

				if(ab_repositorio)
					lsb_query.append(" AND REPOSITORIO ='FINAL' ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_contador++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoEstadoA", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcds_documentoSalida))
			lcds_documentoSalida = null;

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO_HISTORIA.
	 *
	 * @param ads_parametros correspondiente al valor del tipo de objeto DocumentosSalida
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoHistoria(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		return ((ads_parametros != null) ? findByIdTurnoHistoria(ads_parametros.getIdTurnoHistoria()) : null);
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO_HISTORIA.
	 *
	 * @param ai_parametros correspondiente al valor del tipo de objeto Integer
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoHistoria(Integer ai_parametros)
	    throws B2BException
	{
		return findByIdTurnoHistoria(ai_parametros, false);
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO_HISTORIA.
	 *
	 * @param ai_parametros correspondiente al valor del tipo de objeto Integer
	 * @param ab_estado de ab estado
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoHistoria(Integer ai_parametros, boolean ab_estado)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lcds_documentoSalida     = new ArrayList<DocumentosSalida>();
		lps_ps                   = null;
		lrs_rs                   = null;

		if(ai_parametros != null)
		{
			try
			{
				int           li_contador;
				StringBuilder lsb_query;

				li_contador     = 1;
				lsb_query       = new StringBuilder(cs_FIND_BY_ID_TURNO_HISTORIA);

				if(ab_estado)
					lsb_query.append(" AND NVL(ESTADO,'A') = 'A'");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				setInteger(lps_ps, ai_parametros, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoHistoria", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcds_documentoSalida))
			lcds_documentoSalida = null;

		return lcds_documentoSalida;
	}

	/**
	 * Método encargado de consultar los documentos salida con base a un id turno historia y id datos ant sistema.
	 *
	 * @param ads_parametros Objeto que contiene los datos para realizar la consulta.
	 * @return Colección que contiene los documentos consultados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoHistoriaAntSistema(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;

		lcds_documentoSalida = new ArrayList<DocumentosSalida>();

		if(ads_parametros != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_contador;
				StringBuilder lsb_query;

				li_contador     = 1;
				lsb_query       = new StringBuilder();

				lsb_query.append(cs_FIND_BY_ID_TURNO_HISTORIA_ANT_SISTEMA);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				setLong(lps_ps, NumericUtils.getLongWrapper(ads_parametros.getIdTurnoHistoria()), li_contador++);
				lps_ps.setString(li_contador++, ads_parametros.getIdDatosAntSistema());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoHistoriaAntSistema", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcds_documentoSalida))
			lcds_documentoSalida = null;

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO.
	 *
	 * @param al_idTurnoHistoria de al id turno historia
	 * @return devuelve el valor de DocumentosSalida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public DocumentosSalida findByIdTurnoHistoriaNoComunicado(Long al_idTurnoHistoria)
	    throws B2BException
	{
		DocumentosSalida  lds_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lds_return     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(NumericUtils.isValidLong(al_idTurnoHistoria))
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_HISTORIA_NO_TIPO);

				setLong(lps_ps, al_idTurnoHistoria, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_return = getDocumentosSalida(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoHistoriaNoComunicado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_return;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO_HISTORIA y UN TIPO.
	 *
	 * @param ads_parametros correspondiente al valor del tipo de objeto DocumentosSalida
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoHistoriaTipo(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		return (ads_parametros != null)
		? findByIdTurnoHistoriaTipo(ads_parametros.getIdTurnoHistoria(), ads_parametros.getTipo(), false) : null;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO_HISTORIA y UN TIPO.
	 *
	 * @param ai_idTurnoHistoria correspondiente al valor del tipo de objeto Integer
	 * @param as_tipo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoHistoriaTipo(Integer ai_idTurnoHistoria, String as_tipo)
	    throws B2BException
	{
		return findByIdTurnoHistoriaTipo(ai_idTurnoHistoria, as_tipo, false);
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO_HISTORIA y UN TIPO.
	 *
	 * @param ai_idTurnoHistoria correspondiente al valor del tipo de objeto Integer
	 * @param as_tipo correspondiente al valor del tipo de objeto String
	 * @param ab_activo de ab activo
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoHistoriaTipo(
	    Integer ai_idTurnoHistoria, String as_tipo, boolean ab_activo
	)
	    throws B2BException
	{
		return findByIdTurnoHistoriaTipo(ai_idTurnoHistoria, as_tipo, ab_activo, false);
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO_HISTORIA y UN TIPO.
	 *
	 * @param ai_idTurnoHistoria correspondiente al valor del tipo de objeto Integer
	 * @param as_tipo correspondiente al valor del tipo de objeto String
	 * @param ab_activo de ab activo
	 * @param ab_allFields de ab all fields
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoHistoriaTipo(
	    Integer ai_idTurnoHistoria, String as_tipo, boolean ab_activo, boolean ab_allFields
	)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;

		lcds_documentoSalida = new ArrayList<DocumentosSalida>();

		if(NumericUtils.isValidInteger(ai_idTurnoHistoria, 1) && StringUtils.isValidString(as_tipo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_contador;
				StringBuilder lsb_sb;

				li_contador     = 1;
				lsb_sb          = new StringBuilder(cs_FIND_BY_ID_TURNO_HISTORIA_TIPO);

				if(ab_activo)
					lsb_sb.append(" AND ESTADO = 'A' ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				setInteger(lps_ps, ai_idTurnoHistoria, li_contador++);
				lps_ps.setString(li_contador++, as_tipo);

				lrs_rs = lps_ps.executeQuery();

				if(ab_allFields)
					while(lrs_rs.next())
						lcds_documentoSalida.add(getObjectResultSet(lrs_rs));
				else

					while(lrs_rs.next())
						lcds_documentoSalida.add(getDocumentosSalida(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoHistoriaTipo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcds_documentoSalida))
			lcds_documentoSalida = null;

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO_HISTORIA y UN TIPO DOC.
	 *
	 * @param ads_parametros correspondiente al valor del tipo de objeto DocumentosSalida
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoHistoriaTipoDoc(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lcds_documentoSalida     = new ArrayList<DocumentosSalida>();
		lps_ps                   = null;
		lrs_rs                   = null;

		if(ads_parametros != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_HISTORIA_TIPO_DOC);

				setInteger(lps_ps, ads_parametros.getIdTurnoHistoria(), li_contador++);
				lps_ps.setString(li_contador++, ads_parametros.getIdTipoDocumental());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoHistoriaTipoDoc", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcds_documentoSalida))
			lcds_documentoSalida = null;

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO_HISTORIA y ID_TIPO_DOCUMENTAL.
	 *
	 * @param ads_parametros <code>DocumentosSalida</code> que contiene los parametros de búsqueda
	 * @return <code>Collection</code> llena con la información de la BD.
	 * @throws B2BException Señala que se ha producido un error.
	 * @see <code>DocumentosSalida</code>
	 */
	public DocumentosSalida findByIdTurnoHistoriaTipoDocumental(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		return (ads_parametros != null)
		? findByIdTurnoHistoriaTipoDocumental(
		    ads_parametros.getIdTurnoHistoria(), ads_parametros.getIdTipoDocumental()
		) : null;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO_HISTORIA y ID_TIPO_DOCUMENTAL.
	 *
	 * @param ai_idTurnoHistoria de ai id turno historia
	 * @param as_idTipoDocumental de as id tipo documental
	 * @return <code>Collection</code> llena con la información de la BD.
	 * @throws B2BException Señala que se ha producido un error.
	 * @see <code>DocumentosSalida</code>
	 */
	public DocumentosSalida findByIdTurnoHistoriaTipoDocumental(Integer ai_idTurnoHistoria, String as_idTipoDocumental)
	    throws B2BException
	{
		DocumentosSalida lcds_documentoSalida;

		lcds_documentoSalida = null;

		if(NumericUtils.isValidInteger(ai_idTurnoHistoria) && StringUtils.isValidString(as_idTipoDocumental))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_TURNO_HISTORIA_TIPO_DOCUMENTAL);

				setInteger(lps_ps, ai_idTurnoHistoria, li_contador++);
				lps_ps.setString(li_contador++, as_idTipoDocumental);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcds_documentoSalida = getDocumentosSalida(lrs_rs, true);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoHistoriaTipoDocumental", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcds_documentoSalida;
	}

	/**
	 * Find by id tipo documental estado.
	 *
	 * @param lds_param de lds param
	 * @return el valor de documentos salida
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DocumentosSalida findByIdTurnoHistoriaTipoDocumentalEstado(DocumentosSalida lds_param)
	    throws B2BException
	{
		DocumentosSalida lds_return;

		lds_return = null;

		if(lds_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_HISTORIA_TIPO_DOCUMENTAL_ESTADO);

				lps_ps.setLong(li_contador++, NumericUtils.getLong(lds_param.getIdTurnoHistoria()));
				lps_ps.setString(li_contador++, lds_param.getIdTipoDocumental());
				lps_ps.setString(li_contador++, lds_param.getEstado());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_return = getObjectResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTipoDocumentalEstado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_return;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO y TIPO.
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @param as_idTipo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de DocumentosSalida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public DocumentosSalida findByIdTurnoTipo(String as_idTurno, String as_idTipo)
	    throws B2BException
	{
		return findByIdTurnoTipo(as_idTurno, as_idTipo, false, null);
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO y TIPO.
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @param as_idTipo correspondiente al valor del tipo de objeto String
	 * @param ab_repositorio de ab repositorio
	 * @param al_turnoHistoria de al turno historia
	 * @return devuelve el valor de DocumentosSalida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public DocumentosSalida findByIdTurnoTipo(
	    String as_idTurno, String as_idTipo, boolean ab_repositorio, Long al_turnoHistoria
	)
	    throws B2BException
	{
		DocumentosSalida  lds_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		StringBuilder     lsb_sb;

		lds_return     = null;
		lps_ps         = null;
		lrs_rs         = null;
		lsb_sb         = new StringBuilder(cs_FIND_BY_ID_TURNO_TIPO);

		if(StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_idTipo))
		{
			try
			{
				int li_contador;

				li_contador = 1;

				if(ab_repositorio)
					lsb_sb.append(" AND REPOSITORIO = 'FINAL'");

				if(al_turnoHistoria != null)
					lsb_sb.append(" AND ID_TURNO_HISTORIA = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_contador++, as_idTurno);
				lps_ps.setString(li_contador++, as_idTipo);

				if(al_turnoHistoria != null)
					setLong(lps_ps, al_turnoHistoria, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_return = getObjectResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoTipo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_return;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO y TIPO.
	 *
	 * @param ads_parametros Documento contenedor de datos para utilizar en filtro de consulta
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoTipo(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		return (ads_parametros != null)
		? findByIdTurnoTipoActivo(ads_parametros.getIdTurno(), ads_parametros.getTipo()) : null;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO y TIPO.
	 *
	 * @param as_idTurno Id del turno a utilizar como filtro en la busqueda
	 * @param as_tipo Tipo del documento a utilizar como filtro en la busqueda
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findByIdTurnoTipoActivo(String as_idTurno, String as_tipo)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;

		lcds_documentoSalida = new ArrayList<DocumentosSalida>();

		if(StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_tipo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_TIPO_ACTIVO);

				lps_ps.setString(li_contador++, as_idTurno);
				lps_ps.setString(li_contador++, as_tipo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentoSalida.add(getDocumentosSalida(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoTipoActivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcds_documentoSalida.isEmpty())
			lcds_documentoSalida = null;

		return lcds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_TURNO_ y ID_TIPO_DOCUMENTAL.
	 *
	 * @param ai_idTurno de ai id turno
	 * @param as_idTipoDocumental de as id tipo documental
	 * @return <code>Collection</code> llena con la información de la BD.
	 * @throws B2BException Señala que se ha producido un error.
	 * @see <code>DocumentosSalida</code>
	 */
	public DocumentosSalida findByIdTurnoTipoDocumental(String ai_idTurno, String as_idTipoDocumental)
	    throws B2BException
	{
		DocumentosSalida lcds_documentoSalida;

		lcds_documentoSalida = null;

		if(StringUtils.isValidString(ai_idTurno) && StringUtils.isValidString(as_idTipoDocumental))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_TIPO_DOC);

				lps_ps.setString(li_contador++, ai_idTurno);
				lps_ps.setString(li_contador++, as_idTipoDocumental);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcds_documentoSalida = getDocumentosSalida(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoTipoDocumental", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcds_documentoSalida;
	}

	/**
	 * Método encargado de consultar documento salida.
	 *
	 * @param ads_parametros Objeto que contiene la información para realizar la consulta.
	 * @return Objeto que contiene el documento salida consultado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DocumentosSalida findByTurnoTipoEstadoDestinatario(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		DocumentosSalida  lds_documentoSalida;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lds_documentoSalida     = null;
		lps_ps                  = null;
		lrs_rs                  = null;

		if(ads_parametros != null)
		{
			try
			{
				int           li_contador;
				String        ls_repositorio;
				StringBuilder lsb_sb;

				li_contador        = 1;
				ls_repositorio     = ads_parametros.getRepositorio();
				lsb_sb             = new StringBuilder(cs_FIND_BY_TURNO_TIPO_ESTADO_DESTINATARIO);

				if(StringUtils.isValidString(ls_repositorio))
					lsb_sb.append(" AND REPOSITORIO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_contador++, ads_parametros.getIdTurno());
				lps_ps.setString(li_contador++, ads_parametros.getTipo());
				lps_ps.setString(li_contador++, ads_parametros.getEstado());
				lps_ps.setString(li_contador++, ads_parametros.getDestinatario());

				if(StringUtils.isValidString(ls_repositorio))
					lps_ps.setString(li_contador++, ls_repositorio);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_documentoSalida = getDocumentosSalida(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoTipoEstadoDestinatario", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_SOLICITUD, un TIPO y un ESTADO.
	 *
	 * @param ads_parametros Documento contenedor de datos para utilizar en filtro de consulta
	 * @return devuelve el valor de DocumentosSalida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public DocumentosSalida findDocument(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		DocumentosSalida  lds_documentoSalida;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lds_documentoSalida     = null;
		lps_ps                  = null;
		lrs_rs                  = null;

		if(ads_parametros != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_DOCUMENT);

				lps_ps.setString(li_contador++, ads_parametros.getIdSolicitud());
				lps_ps.setString(li_contador++, ads_parametros.getTipo());
				lps_ps.setString(li_contador++, ads_parametros.getEstado());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_documentoSalida = getDocumentosSalida(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDocument", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_SOLICITUD, un ID_TURNO_HISTORIA, un TIPO y un ESTADO.
	 *
	 * @param ads_parametros Documento contenedor de datos para utilizar en filtro de consulta
	 * @return devuelve el valor de DocumentosSalida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public DocumentosSalida findDocumentByTurnoHistoriaTipoEstado(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		DocumentosSalida  lds_documentoSalida;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lds_documentoSalida     = null;
		lps_ps                  = null;
		lrs_rs                  = null;

		if(ads_parametros != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_DOCUMENT_BY_TURNO_HISTORIA_TIPO_ESTADO);

				lps_ps.setString(li_contador++, ads_parametros.getIdSolicitud());
				setInteger(lps_ps, ads_parametros.getIdTurnoHistoria(), li_contador++);
				lps_ps.setString(li_contador++, ads_parametros.getTipo());
				lps_ps.setString(li_contador++, ads_parametros.getEstado());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_documentoSalida = getObjectResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDocumentByTurnoHistoriaTipoEstado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_SOLICITUD, un ID_TURNO, un ID_TIPO_DOCUMENTAL y un ESTADO.
	 *
	 * @param ads_parametros Documento contenedor de datos para utilizar en filtro de consulta
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findDocumentByTurnoTipoDocumentalEstado(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		return findDocumentByTurnoTipoDocumentalEstado(ads_parametros, true);
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_SOLICITUD, un ID_TURNO, un ID_TIPO_DOCUMENTAL y un ESTADO.
	 *
	 * @param ads_parametros Documento contenedor de datos para utilizar en filtro de consulta
	 * @param ab_documentoFinal true si desea filtrar el documento final, false de lo contrario.
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findDocumentByTurnoTipoDocumentalEstado(
	    DocumentosSalida ads_parametros, boolean ab_documentoFinal
	)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_return;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lcds_return     = new ArrayList<DocumentosSalida>();
		lps_ps          = null;
		lrs_rs          = null;

		if(ads_parametros != null)
		{
			try
			{
				int           li_contador;
				String        ls_idSolicitud;
				String        ls_idTurno;
				String        ls_idTipoDocumental;
				String        ls_estado;
				StringBuilder lsb_sb;

				lsb_sb          = new StringBuilder(cs_FIND_DOCUMENT_BY_TURNO_TIPO_DOCUMENTAL_ESTADO);
				li_contador     = 1;

				ls_idSolicitud          = ads_parametros.getIdSolicitud();
				ls_idTurno              = ads_parametros.getIdTurno();
				ls_idTipoDocumental     = ads_parametros.getIdTipoDocumental();
				ls_estado               = ads_parametros.getEstado();

				if(ab_documentoFinal)
					lsb_sb.append(" DOCUMENTO_FINAL = 'S' AND");

				if(StringUtils.isValidString(ls_idSolicitud))
					lsb_sb.append(" ID_SOLICITUD = ? AND");

				if(StringUtils.isValidString(ls_idTurno))
					lsb_sb.append(" ID_TURNO = ? AND");

				if(StringUtils.isValidString(ls_idTipoDocumental))
					lsb_sb.append(" ID_TIPO_DOCUMENTAL = ? AND");

				if(StringUtils.isValidString(ls_estado))
					lsb_sb.append(" ESTADO = ? ");

				{
					String ls_query;

					ls_query = lsb_sb.toString();

					if((ls_query != null) && ls_query.endsWith("AND"))
						ls_query = ls_query.substring(0, ls_query.length() - 3);

					lsb_sb = new StringBuilder(ls_query + " ORDER BY FECHA_CREACION DESC ");
				}

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				if(StringUtils.isValidString(ls_idSolicitud))
					lps_ps.setString(li_contador++, ls_idSolicitud);

				if(StringUtils.isValidString(ls_idTurno))
					lps_ps.setString(li_contador++, ls_idTurno);

				if(StringUtils.isValidString(ls_idTipoDocumental))
					lps_ps.setString(li_contador++, ls_idTipoDocumental);

				if(StringUtils.isValidString(ls_estado))
					lps_ps.setString(li_contador++, ls_estado);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_return.add(getDocumentosSalida(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDocumentByTurnoTipoDocumentalEstado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcds_return.isEmpty())
			lcds_return = null;

		return lcds_return;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_SOLICITUD, un ID_TURNO, un TIPO y un ESTADO.
	 *
	 * @param ads_parametros Documento contenedor de datos para utilizar en filtro de consulta
	 * @return devuelve el valor de DocumentosSalida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public DocumentosSalida findDocumentByTurnoTipoEstado(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		DocumentosSalida  lds_documentoSalida;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lds_documentoSalida     = null;
		lps_ps                  = null;
		lrs_rs                  = null;

		if(ads_parametros != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_DOCUMENT_BY_TURNO_TIPO_ESTADO);

				lps_ps.setString(li_contador++, ads_parametros.getIdSolicitud());
				lps_ps.setString(li_contador++, ads_parametros.getIdTurno());
				lps_ps.setString(li_contador++, ads_parametros.getTipo());
				lps_ps.setString(li_contador++, ads_parametros.getEstado());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_documentoSalida = getDocumentosSalida(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDocumentByTurnoTipoEstado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_SOLICITUD, un ID_TURNO, un TIPO, un ESTADO y una REFERENCIA_SALIDA.
	 *
	 * @param ads_parametros Documento contenedor de datos para utilizar en filtro de consulta
	 * @return devuelve el valor de DocumentosSalida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public DocumentosSalida findDocumentByTurnoTipoEstadoReferenciaSalida(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		return findDocumentByTurnoTipoEstadoReferenciaSalida(ads_parametros, true);
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros por un ID_SOLICITUD, un ID_TURNO, un TIPO, un ESTADO y una REFERENCIA_SALIDA.
	 *
	 * @param ads_parametros Documento contenedor de datos para utilizar en filtro de consulta
	 * @param ab_referenciaSalida de ab referencia salida
	 * @return devuelve el valor de DocumentosSalida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public DocumentosSalida findDocumentByTurnoTipoEstadoReferenciaSalida(
	    DocumentosSalida ads_parametros, boolean ab_referenciaSalida
	)
	    throws B2BException
	{
		DocumentosSalida  lds_documentoSalida;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lds_documentoSalida     = null;
		lps_ps                  = null;
		lrs_rs                  = null;

		if(ads_parametros != null)
		{
			try
			{
				int           li_contador;
				StringBuilder lsb_sb;

				li_contador     = 1;
				lsb_sb          = new StringBuilder(cs_FIND_DOCUMENT_BY_OUTPUT_REFERENCE);

				if(ab_referenciaSalida)
					lsb_sb.append("AND REFERENCIA_SALIDA = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_contador++, ads_parametros.getIdSolicitud());
				lps_ps.setString(li_contador++, ads_parametros.getIdTurno());
				lps_ps.setString(li_contador++, ads_parametros.getTipo());
				lps_ps.setString(li_contador++, ads_parametros.getEstado());

				if(ab_referenciaSalida)
					lps_ps.setString(li_contador++, ads_parametros.getReferenciaSalida());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_documentoSalida = getDocumentosSalida(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDocumentByTurnoTipoEstadoReferenciaSalida", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_documentoSalida;
	}

	/**
	 * Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA registros que tengan una imagen relacionada en la tabla SDB_BGN_IMAGENES y el ID_ECM este nulo.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentosSalida
	 */
	public Collection<DocumentosSalida> findDocumentsEcm()
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentoSalida;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lcds_documentoSalida     = new ArrayList<DocumentosSalida>();
		lps_ps                   = null;
		lrs_rs                   = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_DOCUMENTS_ECM);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcds_documentoSalida.add(getDocumentosSalida(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDocumentsEcm", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcds_documentoSalida.isEmpty())
			lcds_documentoSalida = null;

		return lcds_documentoSalida;
	}

	/**
	 * Find last auto resolucion.
	 *
	 * @param al_idTurnoHistoria de al id turno historia
	 * @return el valor de documentos salida
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DocumentosSalida findLastAutoResolucion(Long al_idTurnoHistoria)
	    throws B2BException
	{
		return findLastAutoResolucion(al_idTurnoHistoria, false);
	}

	/**
	 * Find last auto resolucion.
	 *
	 * @param al_idTurnoHistoria de al id turno historia
	 * @param ab_recursos correspondiente al valor de recursos
	 * @return el valor de documentos salida
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DocumentosSalida findLastAutoResolucion(Long al_idTurnoHistoria, boolean ab_recursos)
	    throws B2BException
	{
		DocumentosSalida lds_documentoSalida;

		lds_documentoSalida = null;

		if(NumericUtils.isValidLong(al_idTurnoHistoria))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_contador;
				StringBuilder lsb_query;

				li_contador     = 1;
				lsb_query       = new StringBuilder(cs_FIND_BY_ID_TURNO_HISTORIA);

				lsb_query.append(" AND (TIPO LIKE '%AUTO%' OR TIPO LIKE '%RESOLUCION%' ");

				if(ab_recursos)
					lsb_query.append(" OR TIPO LIKE '%RECURSO%' ");

				lsb_query.append(" ) ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				setLong(lps_ps, al_idTurnoHistoria, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_documentoSalida = getDocumentosSalida(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findLastAutoResolucion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_documentoSalida;
	}

	/**
	 * Find last by id solicitud tipo.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_tipo de as tipo
	 * @return el valor de documentos salida
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DocumentosSalida findLastByIdSolicitudTipo(String as_idSolicitud, String as_tipo)
	    throws B2BException
	{
		return findLastByIdSolicitudTipo(as_idSolicitud, as_tipo, false);
	}

	/**
	 * Find last by id solicitud tipo.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_tipo de as tipo
	 * @param ab_tipoDocumental de ab tipo documental
	 * @return el valor de documentos salida
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DocumentosSalida findLastByIdSolicitudTipo(String as_idSolicitud, String as_tipo, boolean ab_tipoDocumental)
	    throws B2BException
	{
		DocumentosSalida lds_documentoSalida;

		lds_documentoSalida = null;

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_tipo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_contador;
				StringBuilder lsb_query;

				li_contador     = 1;
				lsb_query       = new StringBuilder(cs_FIND_BY_ID_SOLICITUD);

				lsb_query.append(ab_tipoDocumental ? " AND ID_TIPO_DOCUMENTAL = ? " : " AND TIPO = ? ");
				lsb_query.append(" ORDER BY FECHA_CREACION DESC ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_contador++, as_idSolicitud);
				lps_ps.setString(li_contador++, as_tipo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_documentoSalida = getObjectResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findLastByIdSolicitudTipo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_documentoSalida;
	}

	/**
	 * Retorna el valor del objeto de Collection de Reimpresion.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection Reimpresion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ReimpresionRecibos
	 */
	public Collection<ReimpresionRecibos> findReciboCajaLiquidacion(String as_param, boolean ab_b)
	    throws B2BException
	{
		Collection<ReimpresionRecibos> lcl_liquidacion;

		lcl_liquidacion = new ArrayList<ReimpresionRecibos>();

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;
			StringBuilder     lsb_sb;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;
			lsb_sb        = new StringBuilder(cs_FIND_RECIBO_CAJA_LIQUIDACION);

			if(ab_b)
				lsb_sb.append(" SAC.NIR = ? ");
			else
				lsb_sb.append(" SADC.ID_TURNO =? ");

			lsb_sb.append("AND SADC.ID_TIPO_DOCUMENTAL IN ('38','39') ");

			try
			{
				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_param);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcl_liquidacion.add(getDatosReimpresion(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findReciboCajaLiquidacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcl_liquidacion;
	}

	/**
	 * Retorna el valor del objeto de Collection de DocumentosSalida.
	 *
	 * @param ar_reimpresion correspondiente al valor del tipo de objeto Reimpresion
	 * @return devuelve el valor de Collection DocumentosSalida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Reimpresion
	 */
	public Collection<DocumentosSalida> findReimpresionDocumentosTurnos(Reimpresion ar_reimpresion)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_reimpresion;

		lcds_reimpresion = new ArrayList<DocumentosSalida>();

		if(ar_reimpresion != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_REIMPRESION_TURNOS);

				lps_ps.setString(li_column++, ar_reimpresion.getIdTurno());

				{
					long ll_idEtapaActualInicio;
					long ll_idEtapaActualFinal;

					ll_idEtapaActualInicio     = NumericUtils.getLong(ar_reimpresion.getRangoInicial());
					ll_idEtapaActualFinal      = NumericUtils.getLong(ar_reimpresion.getRangoFinal());

					lps_ps.setLong(li_column++, ll_idEtapaActualInicio);
					lps_ps.setLong(li_column++, ll_idEtapaActualFinal);
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_reimpresion.add(getDocumentosSalida(lrs_rs, false, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findReimpresionDocumentosTurnos", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcds_reimpresion;
	}

	/**
	 * Retorna el valor del objeto de Collection de DocumentosSalida.
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @param al_idEtapaActualInicio correspondiente al valor del tipo de objeto long
	 * @param al_idEtapaActualFinal correspondiente al valor del tipo de objeto long
	 * @return devuelve el valor de Collection DocumentosSalida
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Reimpresion
	 */
	public Collection<DocumentosSalida> findReimpresionDocumentosTurnosImpresos(
	    String as_idTurno, long al_idEtapaActualInicio, long al_idEtapaActualFinal
	)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentosSalida;

		lcds_documentosSalida = new ArrayList<DocumentosSalida>();

		if(StringUtils.isValidString(as_idTurno) && (al_idEtapaActualInicio > 0) && (al_idEtapaActualFinal > 0))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;
			StringBuilder     lsb_sb;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;
			lsb_sb        = new StringBuilder(cs_FIND_REIMPRESION_TURNOS);

			lsb_sb.append(" AND SADC.REIMPRESION='0' ");

			try
			{
				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_idTurno);
				lps_ps.setLong(li_column++, al_idEtapaActualInicio);
				lps_ps.setLong(li_column++, al_idEtapaActualFinal);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcds_documentosSalida.add(getDocumentosSalida(lrs_rs, false, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findReimpresionDocumentosTurnosImpresos", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcds_documentosSalida.isEmpty())
			lcds_documentosSalida = null;

		return lcds_documentosSalida;
	}

	/**
	 * Metodo encargado de calcular la secuencia para la tabla SDB_ACC_DOCUMENTOS_SALIDA.
	 *
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int findSecuence()
	    throws B2BException
	{
		int               li_secuencia;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_secuencia     = 0;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCE);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_secuencia = lrs_rs.getInt(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSecuence", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_secuencia;
	}

	/**
	 *  Metodo encargado de buscar de la tabla SDB_ACC_DOCUMENTOS_SALIDA un registro por un TURNO.
	 *
	 * @param as_parametro correspondiente al valor del tipo de objeto String
	 * @return String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public String findTurnoHistoriaByIdTurno(String as_parametro)
	    throws B2BException
	{
		String            ls_idTurnoHistoria;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_idTurnoHistoria     = null;
		lps_ps                 = null;
		lrs_rs                 = null;

		if(StringUtils.isValidString(as_parametro))
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_MAX_TURNO_HISTORIA_BY_TURNO);

				lps_ps.setString(li_contador++, as_parametro);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_idTurnoHistoria = lrs_rs.getString("MAX(ID_TURNO_HISTORIA)");
			}
			catch(SQLException lse_e)
			{
				logError(this, "findTurnoHistoriaByIdTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_idTurnoHistoria;
	}

	/**
	 * Metodo encargado de realizar la insercion o actualizacion de un registro en la tabla SDB_ACC_DOCUMENTOS_SALIDA.
	 *
	 * @param ads_parametros Documento contenedor de datos para utilizar en insetcion o actualizacion
	 * @param ab_query flag que indica si se actualiza o se inserta
	 * @return devuelve el valor de long
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see long
	 */
	public long insertOrUpdate(DocumentosSalida ads_parametros, boolean ab_query)
	    throws B2BException
	{
		long ll_secuencia;
		ll_secuencia = 0;

		if(ads_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;
			Connection        lc_connection;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			lc_connection     = getConnection();

			try
			{
				int li_column;

				li_column         = 1;
				lps_ps            = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				if(ab_query)
				{
					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						ll_secuencia = lrs_rs.getLong(1);

						lps_ps.setLong(li_column++, ll_secuencia);
					}
				}

				setInteger(lps_ps, ads_parametros.getIdTurnoHistoria(), li_column++);
				lps_ps.setString(li_column++, ads_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, ads_parametros.getIdTurno());
				setLong(lps_ps, ads_parametros.getIdImagen(), li_column++);
				lps_ps.setString(li_column++, ads_parametros.getDestinatario());
				lps_ps.setString(li_column++, ads_parametros.getDireccion());
				lps_ps.setString(li_column++, ads_parametros.getIdPais());
				lps_ps.setString(li_column++, ads_parametros.getIdDepartamento());
				lps_ps.setString(li_column++, ads_parametros.getIdMunicipio());
				lps_ps.setString(li_column++, ads_parametros.getObservaciones());
				lps_ps.setString(li_column++, ads_parametros.getReferenciaSalida());
				lps_ps.setString(li_column++, ads_parametros.getTipoArchivo());
				lps_ps.setString(li_column++, ads_parametros.getIdTipoDocumental());
				lps_ps.setString(li_column++, ads_parametros.getTipo());
				setLong(lps_ps, ads_parametros.getNumeroResolAsociada(), li_column++);
				setDate(lps_ps, ads_parametros.getFechaResolAsociada(), li_column++);
				lps_ps.setString(li_column++, ads_parametros.getEstado());

				String ls_repositorio;
				ls_repositorio = ads_parametros.getRepositorio();

				if(StringUtils.isValidString(ls_repositorio))
					lps_ps.setString(li_column++, ls_repositorio);
				else
					lps_ps.setString(li_column++, RepositorioCommon.TEMPORAL);

				lps_ps.setString(li_column++, ads_parametros.getIdEcm());
				setDate(lps_ps, ads_parametros.getFechaEnvio(), li_column++);
				setDate(lps_ps, ads_parametros.getFechaRadicacionEcm(), li_column++);

				if(ab_query)
				{
					lps_ps.setString(li_column++, ads_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ads_parametros.getIpCreacion());
					lps_ps.setString(li_column++, ads_parametros.getDocumentoAutomatico());
				}
				else
				{
					lps_ps.setString(li_column++, ads_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ads_parametros.getIpModificacion());
				}

				lps_ps.setString(li_column++, ads_parametros.getCorreoElectronico());
				lps_ps.setString(li_column++, ads_parametros.getTelefono());
				lps_ps.setString(li_column++, ads_parametros.getIdDatosAntSistema());
				lps_ps.setString(li_column++, ads_parametros.getDocumentoFinal());
				setLong(lps_ps, ads_parametros.getConsecutivoResolucion(), li_column++);
				setDate(lps_ps, ads_parametros.getFechaResolucion(), li_column++);
				lps_ps.setString(li_column++, ads_parametros.getConsecutivoOficio());
				setDate(lps_ps, ads_parametros.getFechaOficio(), li_column++);
				lps_ps.setString(li_column++, ads_parametros.getIdNombreDocumento());
				lps_ps.setString(li_column++, ads_parametros.getIdPersonaNotificacion());
				setDate(lps_ps, ads_parametros.getFechaPublicacionAvisoWeb(), li_column++);
				setDate(lps_ps, ads_parametros.getFechaDesfijacionAviso(), li_column++);

				if(ab_query)
				{
					lps_ps.setString(li_column++, ads_parametros.getMedioPublicacion());
					lps_ps.setString(li_column++, ads_parametros.getResponsablePublicacion());
					setDate(lps_ps, ads_parametros.getFechaReciboAvisoWeb(), li_column++);
				}

				if(!ab_query)
					lps_ps.setLong(li_column++, ads_parametros.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lps_secuencia);
				close(lrs_rs);
			}
		}

		return ll_secuencia;
	}

	/**
	 * Update acuse recibido.
	 *
	 * @param ads_param de ads param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateAcuseRecibido(DocumentosSalida ads_param)
	    throws B2BException
	{
		if(ads_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(CS_UPDATE_ACUSE_RECIBIDO);

				setDate(lps_ps, ads_param.getFechaGuia(), li_column++);
				lps_ps.setString(li_column++, ads_param.getNumeroGuia());
				setDate(lps_ps, ads_param.getFechaEnvioComponente(), li_column++);
				setDate(lps_ps, ads_param.getFechaAcuseReciboComponente(), li_column++);
				setDate(lps_ps, ads_param.getFechaNotificacion(), li_column++);
				lps_ps.setString(li_column++, ads_param.getIdTipoNotificacion());
				lps_ps.setString(li_column++, ads_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ads_param.getIpModificacion());

				lps_ps.setLong(li_column++, ads_param.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "updateAcuseRecibido", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param ads_param de ads param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateEstado(DocumentosSalida ads_param)
	    throws B2BException
	{
		if(ads_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_ESTADO);

				lps_ps.setString(li_column++, ads_param.getEstado());
				lps_ps.setString(li_column++, ads_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ads_param.getIpModificacion());

				lps_ps.setString(li_column++, ads_param.getIdSolicitud());
				lps_ps.setString(li_column++, ads_param.getIdTurno());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "updateEstado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Update estado any.
	 *
	 * @param apd_parametros de apd parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateEstadoAny(DocumentosSalida apd_parametros)
	    throws B2BException
	{
		if(apd_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_ESTADO_ANY);

				lps_ps.setString(li_column++, apd_parametros.getEstado());
				lps_ps.setString(li_column++, apd_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, apd_parametros.getIpModificacion());
				lps_ps.setLong(li_column++, apd_parametros.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateEstadoAny", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar el estado impresion del documento salida.
	 *
	 * @param apd_parametros <code>DocumentosSalida</code> contenedor de datos para utilizar en la actualizacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateEstadoImpresion(DocumentosSalida apd_parametros)
	    throws B2BException
	{
		if(apd_parametros != null)
		{
			Connection        lc_connection;
			PreparedStatement lps_ps;

			lc_connection     = getConnection();
			lps_ps            = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = lc_connection.prepareStatement(cs_UPDATE_ESTADO_IMPRESION);

				lps_ps.setString(li_column++, apd_parametros.getEstadoImpresion());
				lps_ps.setString(li_column++, apd_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, apd_parametros.getIpModificacion());
				lps_ps.setLong(li_column++, apd_parametros.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateEstadoImpresion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Update fecha desfijacion aviso web.
	 *
	 * @param apd_parametros de apd parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateFechaDesfijacionAvisoWeb(DocumentosSalida apd_parametros)
	    throws B2BException
	{
		if(apd_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_FECHA_DESFIJACION_AVISO_WEB);

				setDate(lps_ps, apd_parametros.getFechaDesfijacionAviso(), li_column++);
				lps_ps.setString(li_column++, apd_parametros.getIdTipoNotificacion());
				lps_ps.setString(li_column++, apd_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, apd_parametros.getIpModificacion());
				lps_ps.setLong(li_column++, apd_parametros.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateFechaDesfijacionAvisoWeb", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Update fecha publicacion aviso web.
	 *
	 * @param apd_parametros de apd parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateFechaPublicacionAvisoWeb(DocumentosSalida apd_parametros)
	    throws B2BException
	{
		if(apd_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_FECHA_PUBLICACION_AVISO_WEB);

				setDate(lps_ps, apd_parametros.getFechaPublicacionAvisoWeb(), li_column++);
				lps_ps.setString(li_column++, apd_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, apd_parametros.getIpModificacion());
				lps_ps.setLong(li_column++, apd_parametros.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateFechaPublicacionAvisoWeb", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Update id ecm y docname.
	 *
	 * @param ads_parametros de apd parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateIdEcmAndDocName(DocumentosSalida ads_parametros)
	    throws B2BException
	{
		if(ads_parametros != null)
		{
			Connection        lc_connection;
			PreparedStatement lps_ps;

			lc_connection     = getConnection();
			lps_ps            = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = lc_connection.prepareStatement(cs_UPDATE_ID_ECM_AND_DOCNAME);

				lps_ps.setString(li_column++, ads_parametros.getIdEcm());
				lps_ps.setString(li_column++, ads_parametros.getIdNombreDocumento());
				setDate(lps_ps, ads_parametros.getFechaEnvio(), li_column++);
				lps_ps.setString(li_column++, ads_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ads_parametros.getIpModificacion());
				lps_ps.setLong(li_column++, ads_parametros.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateIdEcmAndDocName", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Update id mensaje.
	 *
	 * @param apd_parametros de apd parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateIdMensaje(DocumentosSalida apd_parametros)
	    throws B2BException
	{
		if(apd_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_ID_MENSAJE);

				lps_ps.setString(li_column++, apd_parametros.getIdMensaje());
				setDate(lps_ps, apd_parametros.getFechaRespuestaMensaje(), li_column++);
				lps_ps.setString(li_column++, apd_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, apd_parametros.getIpModificacion());
				lps_ps.setLong(li_column++, apd_parametros.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateIdMensaje", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Update id turno historia.
	 *
	 * @param apd_parametros de apd parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateIdTurnoHistoria(DocumentosSalida apd_parametros)
	    throws B2BException
	{
		if(apd_parametros != null)
		{
			Connection        lc_connection;
			PreparedStatement lps_ps;

			lc_connection     = getConnection();
			lps_ps            = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = lc_connection.prepareStatement(cs_UPDATE_ID_TURNO_HISTORIA);

				setInteger(lps_ps, apd_parametros.getIdTurnoHistoria(), li_column++);
				lps_ps.setString(li_column++, apd_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, apd_parametros.getIpModificacion());
				lps_ps.setLong(li_column++, apd_parametros.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateIdTurnoHistoria", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar la imagen del documento salida.
	 *
	 * @param apd_parametros Documento contenedor de datos para utilizar en la actualizacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateImagen(DocumentosSalida apd_parametros)
	    throws B2BException
	{
		if(apd_parametros != null)
		{
			Connection        lc_connection;
			PreparedStatement lps_ps;

			lc_connection     = getConnection();
			lps_ps            = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = lc_connection.prepareStatement(cs_UPDATE_IMAGEN);

				setLong(lps_ps, apd_parametros.getIdImagen(), li_column++);
				lps_ps.setString(li_column++, apd_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, apd_parametros.getIpModificacion());
				lps_ps.setLong(li_column++, apd_parametros.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateImagen", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Update medio publicacion.
	 *
	 * @param ath_turnoHistoria Argumento de tipo <code>TurnoHistoria</code> que contiene el turno con la información modificar.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateMedioPublicacion(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		if(ath_turnoHistoria != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_MEDIO_PUBLICACION);

				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(ath_turnoHistoria.getFechaInicial()));
				lps_ps.setTimestamp(
				    li_column++, DateUtils.getTimestamp(ath_turnoHistoria.getFechaPublicacionAvisoWeb())
				);
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(ath_turnoHistoria.getFechaDesfijacionAviso()));
				lps_ps.setString(li_column++, ath_turnoHistoria.getMedioPublicacion());
				lps_ps.setString(li_column++, ath_turnoHistoria.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ath_turnoHistoria.getIpModificacion());
				lps_ps.setString(li_column++, ath_turnoHistoria.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateMedioPublicacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Update oficina origen by turno.
	 *
	 * @param aoo_parametros de aoo parametros
	 * @param as_idTurno de as id turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateOficinaOrigenByTurno(OficinaOrigen aoo_parametros, String as_idTurno)
	    throws B2BException
	{
		updateOficinaOrigenByTurno(aoo_parametros, as_idTurno, false);
	}

	/**
	 * Método encargado de guardar la información de medidas cautelares.
	 *
	 * @param aoo_parametros OficinaOrigen Objeto coontenedor de la información a actualizar
	 * @param as_idTurno String identificador de turno
	 * @param lb_filtroTipo de lb filtro tipo
	 * @throws B2BException de b 2 B exception
	 */
	public void updateOficinaOrigenByTurno(OficinaOrigen aoo_parametros, String as_idTurno, boolean lb_filtroTipo)
	    throws B2BException
	{
		if(aoo_parametros != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			Connection        lc_connection;

			lps_ps            = null;
			lrs_rs            = null;
			lc_connection     = getConnection();

			if((aoo_parametros != null) && StringUtils.isValidString(as_idTurno))
			{
				try
				{
					StringBuilder lsdb_sb;
					int           li_column;

					li_column     = 1;
					lsdb_sb       = new StringBuilder();

					lsdb_sb.append(cs_UPDATE_OFICINA_ORIGEN_BY_TURNO);

					if(lb_filtroTipo)
						lsdb_sb.append(" AND TIPO LIKE '%CONSTANCIA%'");

					lps_ps = lc_connection.prepareStatement(lsdb_sb.toString());

					lps_ps.setString(li_column++, aoo_parametros.getIdOficinaOrigen());
					lps_ps.setString(li_column++, aoo_parametros.getDireccion());
					lps_ps.setString(li_column++, aoo_parametros.getIdDepartamento());
					lps_ps.setString(li_column++, aoo_parametros.getIdMunicipio());
					lps_ps.setString(li_column++, aoo_parametros.getCorreoElectronico());
					lps_ps.setString(li_column++, aoo_parametros.getTelefono());
					lps_ps.setString(li_column++, aoo_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, aoo_parametros.getIpModificacion());
					lps_ps.setString(li_column++, as_idTurno);

					lps_ps.executeUpdate();
				}
				catch(SQLException lse_e)
				{
					logError(this, "updateOficinaOrigenByTurno", lse_e);

					throw new B2BException(SQL_ERROR, lse_e);
				}
				finally
				{
					close(lps_ps);
					close(lrs_rs);
				}
			}
		}
	}

	/**
	 * Metodo para actualizar el campo REIMPRESION en la base de datos de la tabla SDB_ACC_DOCUMENTOS_SALIDA.
	 *
	 * @param ads_param de ads param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateReimpresionDocumentos(DocumentosSalida ads_param)
	    throws B2BException
	{
		if(ads_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(CS_UPDATE_REIMPRESION);

				lps_ps.setInt(li_column++, ads_param.getReimpresionDocumento());
				lps_ps.setString(li_column++, ads_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ads_param.getIpModificacion());

				lps_ps.setLong(li_column++, ads_param.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "updateReimpresionDocumentos", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo para actualizar el campo REIMPRESION en la base de datos de la tabla SDB_ACC_DOCUMENTOS_SALIDA.
	 *
	 * @param ads_param de ads param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateReimpresionRecibos(ReimpresionRecibos ads_param)
	    throws B2BException
	{
		if(ads_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(CS_UPDATE_REIMPRESION);

				lps_ps.setInt(li_column++, ads_param.getReimpresionDocumento());
				lps_ps.setString(li_column++, ads_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ads_param.getIpModificacion());

				lps_ps.setLong(li_column++, ads_param.getIdDocumentoSalidaReimpresion());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "updateReimpresionRecibos", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Update repositorio.
	 *
	 * @param apd_parametros de apd parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateRepositorio(DocumentosSalida apd_parametros)
	    throws B2BException
	{
		if(apd_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_REPOSITORIO);

				lps_ps.setString(li_column++, apd_parametros.getRepositorio());
				lps_ps.setString(li_column++, apd_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, apd_parametros.getIpModificacion());
				lps_ps.setLong(li_column++, apd_parametros.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateRepositorio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Update responsable publicacion.
	 *
	 * @param ath_turnoHistoria Argumento de tipo <code>TurnoHistoria</code> que contiene el turno con la información modificar.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateResponsablePublicacion(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		if(ath_turnoHistoria != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_RESPONSABLE_PUBLICACION_WEB);

				lps_ps.setString(li_column++, ath_turnoHistoria.getResponsablePublicacion());
				lps_ps.setString(li_column++, ath_turnoHistoria.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ath_turnoHistoria.getIpModificacion());
				lps_ps.setString(li_column++, ath_turnoHistoria.getIdDocumentoSalida());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateMedioPublicacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor de datos reimpresion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de datos reimpresion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see ReimpresionRecibos
	 */
	private ReimpresionRecibos getDatosReimpresion(ResultSet ars_rs)
	    throws SQLException
	{
		ReimpresionRecibos lr_reimpresionTemp;

		lr_reimpresionTemp = new ReimpresionRecibos();

		lr_reimpresionTemp.setIdDocumentoSalidaReimpresion(ars_rs.getLong("ID_DOCUMENTO_SALIDA"));
		lr_reimpresionTemp.setIdDocumentoSalida(ars_rs.getLong("ID_DOCUMENTO_SALIDA"));
		lr_reimpresionTemp.setNir(ars_rs.getString("NIR"));
		lr_reimpresionTemp.setIdTurno(ars_rs.getString("ID_TURNO"));
		lr_reimpresionTemp.setNumeroReferencia(ars_rs.getString("NUMERO_REFERENCIA"));
		lr_reimpresionTemp.setTipo(ars_rs.getString("ID_TIPO_DOCUMENTAL"));
		lr_reimpresionTemp.setProceso(ars_rs.getString("ID_PROCESO"));
		lr_reimpresionTemp.setFechaLiquidacion(ars_rs.getDate("FECHA_LIQUIDACION"));
		lr_reimpresionTemp.setIdEcm(ars_rs.getString("ID_ECM"));
		lr_reimpresionTemp.setIdNombreDocumento(ars_rs.getString("ID_NOMBRE_DOCUMENTO"));
		lr_reimpresionTemp.setFechaEnvio(ars_rs.getDate("FECHA_ENVIO"));
		lr_reimpresionTemp.setFechaRadicacionEcm(ars_rs.getDate("FECHA_RADICACION_ECM"));
		lr_reimpresionTemp.setNumeroCopias(ars_rs.getInt("NUMERO_COPIAS_FILTRADO"));
		lr_reimpresionTemp.setEstadoImpresion(ars_rs.getString("ESTADO_IMPRESION_FILTRADO"));

		return lr_reimpresionTemp;
	}

	/**
	 * Metodo para la obtencion del objeto Documentos Salida a partir de un resulset.
	 *
	 * @param ars_rs objeto contenedor de los resultados de la consulta
	 * @return DocumentosSalida
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private DocumentosSalida getDocumentosSalida(ResultSet ars_rs)
	    throws SQLException
	{
		return getDocumentosSalida(ars_rs, false);
	}

	/**
	 * Metodo para la obtencion del objeto Documentos Salida a partir de un resulset.
	 *
	 * @param ars_rs objeto contenedor de los resultados de la consulta
	 * @param ab_impresion de ab impresion
	 * @return DocumentosSalida
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private DocumentosSalida getDocumentosSalida(ResultSet ars_rs, boolean ab_impresion)
	    throws SQLException
	{
		return getDocumentosSalida(ars_rs, ab_impresion, false);
	}

	/**
	 * Metodo para la obtencion del objeto Documentos Salida a partir de un resulset.
	 *
	 * @param ars_rs objeto contenedor de los resultados de la consulta
	 * @param ab_impresion de ab impresion
	 * @param ab_reimpresion de ab reimpresion
	 * @return DocumentosSalida
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private DocumentosSalida getDocumentosSalida(ResultSet ars_rs, boolean ab_impresion, boolean ab_reimpresion)
	    throws SQLException
	{
		DocumentosSalida lds_documentoSalida;
		Reimpresion      lr_reimpresion;

		lds_documentoSalida     = new DocumentosSalida();
		lr_reimpresion          = new Reimpresion();

		lds_documentoSalida.setIdDocumentoSalida(ars_rs.getLong("ID_DOCUMENTO_SALIDA"));
		lds_documentoSalida.setIdTurnoHistoria(getInteger(ars_rs, "ID_TURNO_HISTORIA"));
		lds_documentoSalida.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lds_documentoSalida.setIdTurno(ars_rs.getString("ID_TURNO"));
		lds_documentoSalida.setIdImagen(getLong(ars_rs, "ID_IMAGEN"));
		lds_documentoSalida.setDestinatario(ars_rs.getString("DESTINATARIO"));
		lds_documentoSalida.setDireccion(ars_rs.getString("DIRECCION"));
		lds_documentoSalida.setIdPais(ars_rs.getString("ID_PAIS"));
		lds_documentoSalida.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		lds_documentoSalida.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		lds_documentoSalida.setObservaciones(ars_rs.getString("OBSERVACIONES"));
		lds_documentoSalida.setReferenciaSalida(ars_rs.getString("REFERENCIA_SALIDA"));
		lds_documentoSalida.setTipoArchivo(ars_rs.getString("TIPO_ARCHIVO"));
		lds_documentoSalida.setIdTipoDocumental(ars_rs.getString("ID_TIPO_DOCUMENTAL"));
		lds_documentoSalida.setTipo(ars_rs.getString("TIPO"));
		lds_documentoSalida.setNumeroResolAsociada(getLong(ars_rs, "NUMERO_RESOL_ASOCIADA"));
		lds_documentoSalida.setFechaResolAsociada(ars_rs.getTimestamp("FECHA_RESOL_ASOCIADA"));
		lds_documentoSalida.setEstado(ars_rs.getString("ESTADO"));
		lds_documentoSalida.setIdEcm(ars_rs.getString("ID_ECM"));
		lds_documentoSalida.setFechaRadicacionEcm(ars_rs.getTimestamp("FECHA_RADICACION_ECM"));
//		lds_documentoSalida.setDocumentoAutomatico(ars_rs.getString("DOCUMENTO_AUTOMATICO"));
//		lds_documentoSalida.setCorreoElectronico(ars_rs.getString("CORREO_ELECTRONICO"));
//		lds_documentoSalida.setTelefono(ars_rs.getString("TELEFONO"));
		lds_documentoSalida.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		lds_documentoSalida.setIdNombreDocumento(ars_rs.getString("ID_NOMBRE_DOCUMENTO"));
		lds_documentoSalida.setRepositorio(ars_rs.getString("REPOSITORIO"));
		lds_documentoSalida.setDocumentoSalidaAsociada(getLong(ars_rs, "SALIDA_ASOCIADA"));
		lds_documentoSalida.setEliminarEnvio(ars_rs.getString("ELIMINAR_ENVIO_OWCC"));
		lds_documentoSalida.setFechaEnvio(ars_rs.getTimestamp("FECHA_ENVIO"));
		lds_documentoSalida.setIntentoEnvio(NumericUtils.getInt(getInteger(ars_rs, "INTENTO_ENVIO")));
		lds_documentoSalida.setFechaIntentoEnvio(ars_rs.getTimestamp("FECHA_INTENTO_ENVIO"));
		lds_documentoSalida.setDocumentoFinal(ars_rs.getString("DOCUMENTO_FINAL"));
		lds_documentoSalida.setReimpresionDocumento(NumericUtils.getInt(getInteger(ars_rs, "REIMPRESION")));
		lds_documentoSalida.setConsecutivoResolucion(getLong(ars_rs, "CONSECUTIVO_RESOLUCION"));
		lds_documentoSalida.setFechaResolucion(ars_rs.getTimestamp("FECHA_RESOLUCION"));
		lds_documentoSalida.setConsecutivoOficio(ars_rs.getString("CONSECUTIVO_OFICIO"));
		lds_documentoSalida.setFechaOficio(ars_rs.getTimestamp("FECHA_CONSECUTIVO_OFICIO"));
		lds_documentoSalida.setFechaAcuseReciboComponente(ars_rs.getTimestamp("FECHA_ACUSE_RECIBO_COMPONENTE"));
		lds_documentoSalida.setFechaEnvioComponente(ars_rs.getTimestamp("FECHA_ENVIO_COMPONENTE"));
		lds_documentoSalida.setFechaPublicacionAvisoWeb(ars_rs.getTimestamp("FECHA_PUBLICACION_AVISO_WEB"));
		lds_documentoSalida.setFechaDesfijacionAviso(ars_rs.getTimestamp("FECHA_DESFIJACION_AVISO_WEB"));

		if(ab_impresion || ab_reimpresion)
		{
			lr_reimpresion.setFechaAsignacion(ars_rs.getDate("FECHA_CREACION"));
			lr_reimpresion.setTipo(ars_rs.getString("ID_TIPO_DOCUMENTAL"));
			lr_reimpresion.setIdTurno(ars_rs.getString("ID_TURNO"));
			lr_reimpresion.setIdDocumentoSalidaReimpresion(ars_rs.getLong("ID_DOCUMENTO_SALIDA"));
			lds_documentoSalida.setReimpresion(lr_reimpresion);
			lds_documentoSalida.setEstadoImpresion(ars_rs.getString("ESTADO_IMPRESION_FILTRADO"));
			lds_documentoSalida.setNumeroCopias(ars_rs.getInt("NUMERO_COPIAS_FILTRADO"));
		}

		if(ab_reimpresion)
		{
			lr_reimpresion.setMotivoNoTramite(ars_rs.getString("MOTIVO_NO_TRAMITE"));
			lr_reimpresion.setIdEtapaActual(getLong(ars_rs, "ID_ETAPA_ACTUAL"));
			lr_reimpresion.setNir(ars_rs.getString("NIR"));
		}

		fillAuditoria(ars_rs, lds_documentoSalida);

		return lds_documentoSalida;
	}

	/**
	 * Retorna Objeto o variable de valor object result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de object result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private DocumentosSalida getObjectResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		DocumentosSalida lds_documentoSalida;

		lds_documentoSalida = new DocumentosSalida();

		lds_documentoSalida.setIdDocumentoSalida(ars_rs.getLong("ID_DOCUMENTO_SALIDA"));
		lds_documentoSalida.setIdTurnoHistoria(getInteger(ars_rs, "ID_TURNO_HISTORIA"));
		lds_documentoSalida.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lds_documentoSalida.setIdTurno(ars_rs.getString("ID_TURNO"));
		lds_documentoSalida.setIdImagen(getLong(ars_rs, "ID_IMAGEN"));
		lds_documentoSalida.setDestinatario(ars_rs.getString("DESTINATARIO"));
		lds_documentoSalida.setDireccion(ars_rs.getString("DIRECCION"));
		lds_documentoSalida.setIdPais(ars_rs.getString("ID_PAIS"));
		lds_documentoSalida.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		lds_documentoSalida.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		lds_documentoSalida.setObservaciones(ars_rs.getString("OBSERVACIONES"));
		lds_documentoSalida.setReferenciaSalida(ars_rs.getString("REFERENCIA_SALIDA"));
		lds_documentoSalida.setTipoArchivo(ars_rs.getString("TIPO_ARCHIVO"));
		lds_documentoSalida.setIdTipoDocumental(ars_rs.getString("ID_TIPO_DOCUMENTAL"));
		lds_documentoSalida.setTipo(ars_rs.getString("TIPO"));
		lds_documentoSalida.setNumeroResolAsociada(getLong(ars_rs, "NUMERO_RESOL_ASOCIADA"));
		lds_documentoSalida.setFechaResolAsociada(ars_rs.getTimestamp("FECHA_RESOL_ASOCIADA"));
		lds_documentoSalida.setEstado(ars_rs.getString("ESTADO"));
		lds_documentoSalida.setIdEcm(ars_rs.getString("ID_ECM"));
		lds_documentoSalida.setFechaRadicacionEcm(ars_rs.getTimestamp("FECHA_RADICACION_ECM"));
		lds_documentoSalida.setDocumentoAutomatico(ars_rs.getString("DOCUMENTO_AUTOMATICO"));
		lds_documentoSalida.setCorreoElectronico(ars_rs.getString("CORREO_ELECTRONICO"));
		lds_documentoSalida.setTelefono(ars_rs.getString("TELEFONO"));
		lds_documentoSalida.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		lds_documentoSalida.setIdNombreDocumento(ars_rs.getString("ID_NOMBRE_DOCUMENTO"));
		lds_documentoSalida.setRepositorio(ars_rs.getString("REPOSITORIO"));
		lds_documentoSalida.setDocumentoSalidaAsociada(getLong(ars_rs, "SALIDA_ASOCIADA"));
		lds_documentoSalida.setEliminarEnvio(ars_rs.getString("ELIMINAR_ENVIO_OWCC"));
		lds_documentoSalida.setFechaEnvio(ars_rs.getTimestamp("FECHA_ENVIO"));
		lds_documentoSalida.setIntentoEnvio(NumericUtils.getInt(getInteger(ars_rs, "INTENTO_ENVIO")));
		lds_documentoSalida.setFechaIntentoEnvio(ars_rs.getTimestamp("FECHA_INTENTO_ENVIO"));
		lds_documentoSalida.setDocumentoFinal(ars_rs.getString("DOCUMENTO_FINAL"));
		lds_documentoSalida.setEstadoImpresion(ars_rs.getString("ESTADO_IMPRESION"));
		lds_documentoSalida.setNumeroCopias(ars_rs.getInt("NUMERO_COPIAS"));
		lds_documentoSalida.setReimpresionDocumento(NumericUtils.getInt(getInteger(ars_rs, "REIMPRESION")));
		lds_documentoSalida.setConsecutivoResolucion(
		    NumericUtils.getLongWrapper(ars_rs.getLong("CONSECUTIVO_RESOLUCION"))
		);
		lds_documentoSalida.setConsecutivoOficio(ars_rs.getString("CONSECUTIVO_OFICIO"));
		lds_documentoSalida.setFechaResolucion(ars_rs.getDate("FECHA_RESOLUCION"));
//		lds_documentoSalida.setClasificacion(ars_rs.getString("CLASIFICACION"));
		lds_documentoSalida.setIdMensaje(ars_rs.getString("ID_MENSAJE"));
		lds_documentoSalida.setFechaGuia(ars_rs.getTimestamp("FECHA_GUIA"));
		lds_documentoSalida.setNumeroGuia(ars_rs.getString("NUMERO_GUIA"));
		lds_documentoSalida.setFechaEnvioComponente(ars_rs.getTimestamp("FECHA_ENVIO_COMPONENTE"));
		lds_documentoSalida.setFechaAcuseReciboComponente(ars_rs.getTimestamp("FECHA_ACUSE_RECIBO_COMPONENTE"));
		lds_documentoSalida.setFechaPublicacionAvisoWeb(ars_rs.getTimestamp("FECHA_PUBLICACION_AVISO_WEB"));
		lds_documentoSalida.setFechaNotificacion(ars_rs.getTimestamp("FECHA_NOTIFICACION"));
		lds_documentoSalida.setIdTipoNotificacion(ars_rs.getString("TIPO_NOTIFICACION"));
		lds_documentoSalida.setIdPersonaNotificacion(ars_rs.getString("ID_PERSONA_NOTIFICACION"));

		fillAuditoria(ars_rs, lds_documentoSalida);

		return lds_documentoSalida;
	}
}
