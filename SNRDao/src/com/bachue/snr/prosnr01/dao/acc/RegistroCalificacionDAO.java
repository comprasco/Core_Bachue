package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.registro.GravamenPendiente;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;


/**
 * Clase que contiene todos las propiedades de RegistroCalificacionDAO.
 *
 * @author garias
 */
public class RegistroCalificacionDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_DATA_PREDIO. */
	private static final String cs_DATA_PREDIO = " SELECT ATA.ID_NATURALEZA_JURIDICA, ANP.VERSION,ANP.ID_SOLICITUD, GNJ.ID_GRUPO_NAT_JURIDICA, ATA.VERSION, ANP.ID_ANOTACION_PREDIO,ANP.ID_ANOTACION,ANP.FECHA_REGISTRO,ANP.FECHA_RADICACION, ANP.ID_TURNO_HISTORIA, ANP.ID_TURNO, ANP.ID_DOCUMENTO,PTD.NOMBRE NOMBRE_DOC ,ATA.NOMBRE NOMBRE_ACTO,ATA.ID_NATURALEZA_JURIDICA COD_ACTO, GNJ.NOMBRE NOMBRE_GRUPO_ACTO, NVL (ANP.REVISION_CALIFICADOR,'N')REVISION_CALIFICADOR,NVL(ANP.VALOR,0)VALOR,NVL(ANP.COMENTARIO,' ')COMENTARIO "
		+ "FROM SDB_ACC_ANOTACION_PREDIO ANP  INNER JOIN SDB_BGN_DOCUMENTO SBD ON SBD.ID_DOCUMENTO = ANP.ID_DOCUMENTO AND SBD.VERSION_DOCUMENTO = ANP.VERSION_DOCUMENTO "
		+ "INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO PTD ON PTD.ID_TIPO_DOCUMENTO = SBD.ID_TIPO_DOCUMENTO "
		+ "INNER JOIN SDB_PNG_NATURALEZA_JURIDICA ATA ON ATA.ID_NATURALEZA_JURIDICA = ANP.ID_NATURALEZA_JURIDICA AND ATA.VERSION = (SELECT MAX(VERSION) "
		+ "FROM SDB_PNG_NATURALEZA_JURIDICA WHERE ID_NATURALEZA_JURIDICA = ANP.ID_NATURALEZA_JURIDICA) "
		+ "INNER JOIN SDB_PNG_GRUPO_NATURALEZA_JURIDICA GNJ ON GNJ.ID_GRUPO_NAT_JURIDICA = ATA.ID_GRUPO_NAT_JURIDICA "
		+ "WHERE ANP.ACTIVO = 'S' AND ID_CIRCULO = ? AND ID_MATRICULA = ? ";

	/** Constante cs_DATA_PREDIO_ANOTACIONES. */
	private static final String cs_DATA_PREDIO_ANOTACIONES = " SELECT AP.PRIMER_NOMBRE || ' ' || NVL(AP.SEGUNDO_NOMBRE,'')  || ' ' || AP.PRIMER_APELLIDO  || ' ' || NVL(AP.SEGUNDO_APELLIDO,'') NOMBRE_PERSONA, AP.RAZON_SOCIAL, ACP.ROL_PERSONA ,AP.ID_DOCUMENTO_TIPO,"
		+ " TO_NUMBER(AP.NUMERO_DOCUMENTO) NUMERO_DOCUMENTO, AP.ID_TIPO_PERSONA, ACP.ID_ANOTACION_PREDIO_CIUDADANO,NVL(ACP.PROPIETARIO,' ')PROPIETARIO,NVL(ACP.PORCENTAJE_PARTICIPACION,' ')PORCENTAJE_PARTICIPACION,NVL(PI.DESCRIPCION,' ') ID_PARTE_INTERESADA"
		+ " FROM SDB_ACC_ANOTACION_PREDIO_CIUDADANO ACP  INNER JOIN SDB_ACC_PERSONA AP ON AP.ID_PERSONA = ACP.ID_PERSONA LEFT JOIN SDB_COL_PARTE_INTERESADA PI ON PI.ID_PARTE_INTERESADA = ACP.ID_PARTE_INTERESADA WHERE ACP.ID_ANOTACION_PREDIO = ? ORDER BY NUMERO_DOCUMENTO ASC, ACP.ROL_PERSONA DESC ";

	/** Constante cs_DATA_PREDIO_ANOTACIONES_BNG. */
	private static final String cs_DATA_PREDIO_ANOTACIONES_BNG = "SELECT  AP.PRIMER_NOMBRE || ' ' ||  NVL(AP.SEGUNDO_NOMBRE,'')  || ' ' ||  AP.PRIMER_APELLIDO  || ' ' ||  NVL(AP.SEGUNDO_APELLIDO,'') || ' ' ||  NVL(AP.RAZON_SOCIAL,'') NOMBRE_PERSONA,  ACP.ROL_PERSONA ,AP.ID_DOCUMENTO_TIPO,  "
		+ "AP.NUMERO_DOCUMENTO,ACP.ID_ANOTACION, NVL(ACP.PROPIETARIO,' ')PROPIETARIO, AP.RAZON_SOCIAL, NVL(ACP.PORCENTAJE_PARTICIPACION,' ')PORCENTAJE_PARTICIPACION, NVL(PI.DESCRIPCION,' ') ID_PARTE_INTERESADA  FROM SDB_BNG_ANOTACION_PREDIO_CIUDADANO ACP  INNER JOIN SDB_ACC_PERSONA AP ON AP.ID_PERSONA = ACP.ID_PERSONA  "
		+ "LEFT JOIN SDB_COL_PARTE_INTERESADA PI ON PI.ID_PARTE_INTERESADA = ACP.ID_PARTE_INTERESADA  WHERE ACP.ID_CIRCULO = ? AND ACP.ID_MATRICULA = ? AND ACP.ID_ANOTACION = ? ORDER BY ACP.ROL_PERSONA DESC";

	/** Constante cs_DATA_MATRICULA. */
	private static final String cs_DATA_MATRICULA = " SELECT PGND.NOMBRE NOMBRE_DEPARTAMENTO, PGNM.NOMBRE NOMBRE_MUNICIPIO,CPT.DESCRIPCION TIPO_PREDIO,PCR.ID_CIRCULO COD_CIRCULO,PCR.NOMBRE NOMBRE_CIRCULO "
		+ " FROM  SDB_BNG_PREDIO_REGISTRO  BPR  LEFT JOIN SDB_COL_PREDIO_TIPO CPT ON CPT.ID_TIPO_PREDIO = BPR.ID_TIPO_PREDIO"
		+ " INNER JOIN SDB_PGN_ZONA_REGISTRAL PZR ON PZR.ID_ZONA_REGISTRAL = BPR.ID_ZONA_REGISTRAL"
		+ " INNER JOIN SDB_PGN_DEPARTAMENTO PGND ON PGND.ID_PAIS = PZR.ID_PAIS    AND  PGND.ID_DEPARTAMENTO  = PZR.ID_DEPARTAMENTO"
		+ " INNER JOIN SDB_PGN_MUNICIPIO PGNM ON PGNM.ID_PAIS  = PZR.ID_PAIS  AND PGNM.ID_MUNICIPIO  = PZR.ID_MUNICIPIO AND  PGNM.ID_DEPARTAMENTO  = PZR.ID_DEPARTAMENTO"
		+ " INNER JOIN SDB_PGN_CIRCULO_REGISTRAL PCR ON PCR.ID_CIRCULO = BPR.ID_CIRCULO WHERE  BPR.ID_CIRCULO = ?  AND BPR.ID_MATRICULA = ? ";

	/** Constante cs_DATA_MATRICULA_ACC. */
	private static final String cs_DATA_MATRICULA_ACC = " SELECT PGND.NOMBRE NOMBRE_DEPARTAMENTO, PGNM.NOMBRE NOMBRE_MUNICIPIO,CPT.DESCRIPCION TIPO_PREDIO,PCR.ID_CIRCULO COD_CIRCULO,PCR.NOMBRE NOMBRE_CIRCULO "
		+ " FROM  SDB_ACC_PREDIO_REGISTRO   BPR  LEFT JOIN SDB_COL_PREDIO_TIPO CPT ON CPT.ID_TIPO_PREDIO = BPR.ID_TIPO_PREDIO"
		+ " INNER JOIN SDB_PGN_ZONA_REGISTRAL PZR ON PZR.ID_ZONA_REGISTRAL = BPR.ID_ZONA_REGISTRAL"
		+ " INNER JOIN SDB_PGN_DEPARTAMENTO PGND ON PGND.ID_PAIS = PZR.ID_PAIS    AND  PGND.ID_DEPARTAMENTO  = PZR.ID_DEPARTAMENTO"
		+ " INNER JOIN SDB_PGN_MUNICIPIO PGNM ON PGNM.ID_PAIS  = PZR.ID_PAIS  AND PGNM.ID_MUNICIPIO  = PZR.ID_MUNICIPIO AND  PGNM.ID_DEPARTAMENTO  = PZR.ID_DEPARTAMENTO"
		+ " INNER JOIN SDB_PGN_CIRCULO_REGISTRAL PCR ON PCR.ID_CIRCULO = BPR.ID_CIRCULO WHERE  BPR.ID_CIRCULO = ?  AND BPR.ID_MATRICULA = ? ";

	/** Constante cs_DIRECCION_MATRICULA. */
	private static final String cs_DIRECCION_MATRICULA = " SELECT BGNDP.DIRECCION_COMPLETA DIRECCION, BPR.ID_CIRCULO, BPR.ID_MATRICULA "
		+ " FROM SDB_ACC_SOLICITUD ACCS "
		+ " LEFT JOIN SDB_ACC_SOLICITUD_MATRICULA ACCSM ON ACCS.ID_SOLICITUD = ACCSM.ID_SOLICITUD "
		+ " LEFT JOIN SDB_BNG_PREDIO_REGISTRO BPR ON ACCSM.ID_CIRCULO = BPR.ID_CIRCULO  AND ACCSM.ID_MATRICULA = BPR.ID_MATRICULA "
		+ " LEFT JOIN SDB_COL_PREDIO_TIPO PT ON BPR.ID_TIPO_PREDIO = PT.ID_TIPO_PREDIO "
		+ " LEFT JOIN SDB_PGN_ZONA_REGISTRAL ZR ON BPR.ID_ZONA_REGISTRAL = ZR.ID_ZONA_REGISTRAL "
		+ " LEFT JOIN SDB_PGN_DEPARTAMENTO PGND ON ZR.ID_PAIS = PGND.ID_PAIS  AND ZR.ID_DEPARTAMENTO =PGND.ID_DEPARTAMENTO "
		+ " LEFT JOIN SDB_PGN_MUNICIPIO PGNM ON ZR.ID_PAIS = PGNM.ID_PAIS  AND ZR.ID_DEPARTAMENTO =PGNM.ID_DEPARTAMENTO  AND ZR.ID_MUNICIPIO = PGNM.ID_MUNICIPIO "
		+ " LEFT JOIN SDB_PGN_VEREDA PGNV ON ZR.ID_PAIS = PGNV.ID_PAIS AND ZR.ID_DEPARTAMENTO = PGNV.ID_DEPARTAMENTO  AND ZR.ID_MUNICIPIO = PGNV.ID_MUNICIPIO AND ZR.ID_VEREDA = PGNV.ID_VEREDA "
		+ " LEFT JOIN SDB_BNG_DIRECCION_PREDIO BGNDP ON BPR.ID_CIRCULO = BGNDP.ID_CIRCULO AND BPR.ID_MATRICULA = BGNDP.ID_MATRICULA "
		+ " LEFT JOIN SDB_PNG_TIPO_EJE PNGTE ON BGNDP.ID_TIPO_EJE_PRINCIPAL = PNGTE.ID_TIPO_EJE "
		+ " LEFT JOIN SDB_PNG_TIPO_EJE PNGTE1 ON BGNDP.ID_TIPO_EJE_SECUNDARIO = PNGTE1.ID_TIPO_EJE "
		+ " WHERE ACCS.ID_SOLICITUD = ?  AND BPR.ID_CIRCULO = ?  AND  BPR.ID_MATRICULA = ?";

	/** Constante cs_DETALLE_DOCUMENTO. */
	private static final String cs_DETALLE_DOCUMENTO = "SELECT   SM.NUMERO,  SM.FECHA_DOCUMENTO,  OO.NOMBRE NOMBRE_OFICINA,  MUN.NOMBRE MUNICIPIO"
		+ " FROM SDB_BGN_DOCUMENTO SM INNER JOIN SDB_ACC_SOLICITUD S ON SM.ID_DOCUMENTO = S.ID_DOCUMENTO"
		+ " RIGHT JOIN SDB_PGN_OFICINA_ORIGEN OO ON SM.ID_OFICINA_ORIGEN = OO.ID_OFICINA_ORIGEN"
		+ " INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO TD ON SM.ID_TIPO_DOCUMENTO = TD.ID_TIPO_DOCUMENTO"
		+ " INNER JOIN SDB_PGN_DEPARTAMENTO DTO ON OO.ID_DEPARTAMENTO = DTO.ID_DEPARTAMENTO"
		+ " INNER JOIN SDB_PGN_PAIS PA ON OO.ID_PAIS = PA.ID_PAIS"
		+ " INNER JOIN SDB_PGN_MUNICIPIO MUN ON OO.ID_MUNICIPIO = MUN.ID_MUNICIPIO  AND OO.ID_PAIS  = MUN.ID_PAIS AND OO.ID_DEPARTAMENTO = MUN.ID_DEPARTAMENTO"
		+ " WHERE S.ID_SOLICITUD = (SELECT ID_SOLICITUD    FROM SDB_ACC_TURNO T   WHERE T.ID_TURNO = (SELECT ID_TURNO FROM SDB_ACC_TURNO_HISTORIA TH WHERE TH.ID_TURNO_HISTORIA = ?)) ORDER BY SM.VERSION_DOCUMENTO DESC";

	/** Constante cs_MODIFICAR_BASICO_ANOTACION. */
	private static final String cs_MODIFICAR_BASICO_ANOTACION = " UPDATE SDB_ACC_ANOTACION_PREDIO_CIUDADANO  "
		+ "SET ROL_PERSONA = ?, ID_PERSONA = ?,  FECHA_MODIFICACION=SYSTIMESTAMP,ID_USUARIO_MODIFICACION = ? ,PROPIETARIO = ? ,PORCENTAJE_PARTICIPACION = ?,ID_PARTE_INTERESADA = ? "
		+ "WHERE ID_ANOTACION_PREDIO_CIUDADANO = ? ";

	/** Constante cs_CONSULTA_ANOTACION. */
	private static final String cs_CONSULTA_ANOTACION = " SELECT  ID_ANOTACION ,ORDEN FROM SDB_ACC_ANOTACION_PREDIO WHERE ID_ANOTACION_PREDIO = ? ";

	/** Constante cs_CONSULTA_ANOTACIONES_PARCIALES. */
	private static final String cs_CONSULTA_ANOTACIONES_PARCIALES = " SELECT AAP.ID_ANOTACION, AAP.ID_ANOTACION_PREDIO ,ASMA.ESTADO FROM SDB_ACC_ANOTACION_PREDIO AAP INNER JOIN SDB_ACC_ACTO AA "
		+ "ON (AA.ID_SOLICITUD = AAP.ID_SOLICITUD AND AA.ID_TIPO_ACTO = AAP.ID_NATURALEZA_JURIDICA) INNER JOIN SDB_ACC_SOLICITUD_MATRICULA_ACTO ASMA "
		+ "ON (ASMA.ID_ACTO = AA.ID_ACTO AND  ASMA.ID_CIRCULO = AAP.ID_CIRCULO AND ASMA.ID_MATRICULA = AAP.ID_MATRICULA) WHERE AAP.ID_CIRCULO = ? AND AAP.ID_MATRICULA = ? AND AAP.ID_TURNO_HISTORIA = ?  ";

	/** Constante cs_MODIFICAR_ANOTACION. */
	private static final String cs_MODIFICAR_ANOTACION = "   UPDATE SDB_ACC_ANOTACION_PREDIO  SET  ID_ANOTACION = ?  , ORDEN = ?, FECHA_MODIFICACION=SYSTIMESTAMP,ID_USUARIO_MODIFICACION = ?  WHERE ID_ANOTACION_PREDIO = ?";

	/** Constante cs_MODIFICAR_REVISION. */
	private static final String cs_MODIFICAR_REVISION = "   UPDATE SDB_ACC_ANOTACION_PREDIO  SET    FECHA_MODIFICACION=SYSTIMESTAMP,ID_USUARIO_MODIFICACION = ?   ";

	/** Constante cs_MODIFICAR_ANOTACION_CIUDADANO. */
	private static final String cs_MODIFICAR_ANOTACION_CIUDADANO = "   UPDATE SDB_ACC_ANOTACION_PREDIO_CIUDADANO  SET  ID_ANOTACION = ?  ,  FECHA_MODIFICACION=SYSTIMESTAMP,ID_USUARIO_MODIFICACION = ?  WHERE ID_ANOTACION_PREDIO = ?";

	/** Constante cs_CONSULTA_REVISION. */
	private static final String cs_CONSULTA_REVISION = "  SELECT NVL(REVISION_CALIFICADOR, 'N')REVISION_CALIFICADOR FROM SDB_ACC_ANOTACION_PREDIO where ID_CIRCULO = ? and ID_MATRICULA = ? and ID_TURNO_HISTORIA = ?  AND NVL(INDICADOR_CREAR_PREDIO_CIUDADANO,'N') = 'S' AND ACTIVO <> 'N'";

	/** Constante cs_CONSULTA_REVISION_DEVOLUCION. */
	private static final String cs_CONSULTA_REVISION_DEVOLUCION = "  SELECT NVL(REVISION_CALIFICADOR, 'N')REVISION_CALIFICADOR FROM SDB_ACC_ANOTACION_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO = ? AND NVL(INDICADOR_CREAR_PREDIO_CIUDADANO,'N') = 'S' AND ACTIVO <> 'N'";

	/** Constante cs_DELETE_PREDIO_CIUDADANO. */
	private static final String cs_DELETE_PREDIO_CIUDADANO = "  DELETE FROM  SDB_ACC_ANOTACION_PREDIO_CIUDADANO WHERE ID_ANOTACION_PREDIO_CIUDADANO = ? ";

	/** Constante cs_DELETE_PREDIO. */
	private static final String cs_DELETE_PREDIO = "  DELETE FROM  SDB_ACC_ANOTACION_PREDIO_CIUDADANO WHERE ID_ANOTACION_PREDIO = ? ";

	/** Constante cs_CONSULTA_DETALLE_INTERVINIENTES. */
	private static final String cs_CONSULTA_DETALLE_INTERVINIENTES = "  SELECT  AP.PRIMER_NOMBRE,  NVL(AP.SEGUNDO_NOMBRE,' ') SEGUNDO_NOMBRE, AP.PRIMER_APELLIDO,NVL(AP.SEGUNDO_APELLIDO,' ') SEGUNDO_APELLIDO, NVL(AP.RAZON_SOCIAL,' ')RAZON_SOCIAL  ,"
		+ " ACP.ROL_PERSONA ,AP.ID_DOCUMENTO_TIPO,AP.NUMERO_DOCUMENTO,ACP.ID_PARTE_INTERESADA,ACP.ID_ANOTACION_PREDIO_CIUDADANO,NVL(ACP.PROPIETARIO,' ')PROPIETARIO,NVL(ACP.PORCENTAJE_PARTICIPACION,' ')PORCENTAJE_PARTICIPACION,ACP.ID_PERSONA "
		+ " FROM SDB_ACC_ANOTACION_PREDIO_CIUDADANO ACP  INNER JOIN SDB_ACC_PERSONA AP ON AP.ID_PERSONA = ACP.ID_PERSONA WHERE ACP.ID_ANOTACION_PREDIO = ? ORDER BY ACP.ROL_PERSONA DESC, AP.NUMERO_DOCUMENTO ASC ";

	/** Constante cs_CONSULTA_ANOTACION_PREDIO. */
	private static final String cs_CONSULTA_ANOTACION_PREDIO = "  SELECT  * FROM SDB_ACC_ANOTACION_PREDIO  WHERE ID_ANOTACION_PREDIO = ? ";

	/** Constante cs_MODIFICAR_PERSONA. */
	private static final String cs_MODIFICAR_PERSONA = "  UPDATE  SDB_ACC_PERSONA SET PRIMER_NOMBRE = ? , SEGUNDO_NOMBRE  = ?, PRIMER_APELLIDO = ? ,SEGUNDO_APELLIDO = ?  ,ID_DOCUMENTO_TIPO  = ? ,"
		+ " NUMERO_DOCUMENTO = ? ,FECHA_MODIFICACION =  SYSTIMESTAMP,ID_USUARIO_MODIFICACION = ? ,RAZON_SOCIAL = ?  WHERE ID_PERSONA = ? ";

	/** Constante cs_DELETE_INTERVINIENTE. */
	private static final String cs_DELETE_INTERVINIENTE = " DELETE FROM  SDB_ACC_SOLICITUD_INTERVINIENTE WHERE ID_PERSONA = ? AND ID_SOLICITUD = ? ";

	/** Constante cs_INSERTAR_INTERVINIENTES. */
	private static final String cs_INSERTAR_INTERVINIENTES = "INSERT INTO SDB_ACC_ANOTACION_PREDIO_CIUDADANO (ID_ANOTACION_PREDIO,ID_CIRCULO,ID_MATRICULA,ID_ANOTACION,ID_PERSONA,ROL_PERSONA,PORCENTAJE_PARTICIPACION,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_TURNO) VALUES (?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante cs_CONSULTA_MATRICULA_A_HEREDAR. */
	private static final String cs_CONSULTA_MATRICULA_A_HEREDAR = "  SELECT BAP.FECHA_RADICACION, BAP.ID_TIPO_ANOTACION,BAP.ID_DOCUMENTO,BAP.ID_CIRCULO ID_CIRCULO_PREDIO ,BAP.ID_MATRICULA ID_MATRICULA_PREDIO,BAP.ID_ESTADO_ANOTACION,  BAP.ID_ANOTACION,BAP.FECHA_REGISTRO ,BAP.RADICACION,PEA.NOMBRE ESTADO_ANOTACION ,TDP.NOMBRE NOMBRE_DOCUMENTO,"
		+ " TO_CHAR(SBD.FECHA_DOCUMENTO, 'DD/MM/YYYY')FECHA_DOCUMENTO,SBD.NUMERO,NVL(PTO.NOMBRE,' ') TIPO_OFICINA ,NVL(POO.NOMBRE,' ') OFICINA_ORIGEN ,NVL(PBJ.ID_NATURALEZA_JURIDICA,' ') CODIGO_ACTO,NVL(PBJ.NOMBRE,' ') DESCRIPCION_ACTO"
		+ "	FROM SDB_BNG_ANOTACION_PREDIO BAP INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PBJ ON PBJ.ID_NATURALEZA_JURIDICA  = BAP.ID_NATURALEZA_JURIDICA "
		+ " AND  NVL(PBJ.HEREDAR_ANOTACION,'N') = 'S' AND PBJ.VERSION  = BAP.VERSION INNER JOIN SDB_BGN_DOCUMENTO SBD ON  SBD.ID_DOCUMENTO = BAP.ID_DOCUMENTO  INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO TDP ON  TDP.ID_TIPO_DOCUMENTO = SBD.ID_TIPO_DOCUMENTO"
		+ "	INNER JOIN SDB_PNG_ESTADO_ANOTACION PEA ON  PEA.ID_ESTADO_ANOTACION = BAP.ID_ESTADO_ANOTACION  LEFT JOIN SDB_PGN_TIPO_OFICINA PTO ON PTO.ID_TIPO_OFICINA = SBD.ID_TIPO_OFICINA LEFT JOIN SDB_PGN_OFICINA_ORIGEN POO ON POO.ID_OFICINA_ORIGEN = SBD.ID_OFICINA_ORIGEN"
		+ "	WHERE  BAP.ID_CIRCULO = ? AND BAP.ID_MATRICULA = ?  AND NVL(BAP.ANOTACION_CANCELADA,'N') = 'N'  ORDER BY BAP.ID_ANOTACION ASC";

	/** Constante cs_CONSULTA_MATRICULA_A_HEREDAR_ACC. */
	private static final String cs_CONSULTA_MATRICULA_A_HEREDAR_ACC = "    SELECT  BAP.ID_ANOTACION_PREDIO, BAP.FECHA_RADICACION, BAP.ID_TIPO_ANOTACION,BAP.ID_DOCUMENTO,BAP.ID_CIRCULO ID_CIRCULO_PREDIO ,BAP.ID_MATRICULA ID_MATRICULA_PREDIO,BAP.ID_ESTADO_ANOTACION,  BAP.ID_ANOTACION,BAP.FECHA_REGISTRO ,BAP.RADICACION,PEA.NOMBRE ESTADO_ANOTACION ,TDP.NOMBRE NOMBRE_DOCUMENTO,"
		+ "	TO_CHAR(SBD.FECHA_DOCUMENTO, 'DD/MM/YYYY')FECHA_DOCUMENTO,SBD.NUMERO,NVL(PTO.NOMBRE,' ') TIPO_OFICINA ,NVL(POO.NOMBRE,' ') OFICINA_ORIGEN ,NVL(PBJ.ID_NATURALEZA_JURIDICA,' ') CODIGO_ACTO,NVL(PBJ.NOMBRE,' ') DESCRIPCION_ACTO "
		+ "	FROM SDB_ACC_ANOTACION_PREDIO BAP   INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PBJ ON PBJ.ID_NATURALEZA_JURIDICA  = BAP.ID_NATURALEZA_JURIDICA 	AND  NVL(PBJ.HEREDAR_ANOTACION,'N') = 'S' AND PBJ.VERSION  = BAP.VERSION "
		+ " INNER JOIN SDB_BGN_DOCUMENTO SBD ON  SBD.ID_DOCUMENTO = BAP.ID_DOCUMENTO  AND SBD.VERSION_DOCUMENTO = BAP.VERSION_DOCUMENTO"
		+ " INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO TDP ON  TDP.ID_TIPO_DOCUMENTO = SBD.ID_TIPO_DOCUMENTO  INNER JOIN SDB_PNG_ESTADO_ANOTACION PEA ON  PEA.ID_ESTADO_ANOTACION = BAP.ID_ESTADO_ANOTACION "
		+ " LEFT JOIN SDB_PGN_TIPO_OFICINA PTO ON PTO.ID_TIPO_OFICINA = SBD.ID_TIPO_OFICINA  LEFT JOIN SDB_PGN_OFICINA_ORIGEN POO ON POO.ID_OFICINA_ORIGEN = SBD.ID_OFICINA_ORIGEN "
		+ "	WHERE  BAP.ID_CIRCULO = ? AND BAP.ID_MATRICULA = ?   AND BAP.ID_TURNO =? AND NVL(BAP.ANOTACION_CANCELADA,'N') = 'N'  ORDER BY BAP.ID_ANOTACION ASC";

	/** Constante cs_CONSULTA_GRAVAMENES_PENDIENTES. */
	private static final String cs_CONSULTA_GRAVAMENES_PENDIENTES = "SELECT BAP.FECHA_RADICACION, BAP.ID_TIPO_ANOTACION,BAP.ID_DOCUMENTO,BAP.ID_CIRCULO ID_CIRCULO_PREDIO ,BAP.ID_MATRICULA ID_MATRICULA_PREDIO,BAP.ID_ESTADO_ANOTACION, BAP.ID_ANOTACION,BAP.FECHA_REGISTRO ,BAP.RADICACION,"
		+ "PEA.NOMBRE ESTADO_ANOTACION ,TDP.NOMBRE NOMBRE_DOCUMENTO, TO_CHAR(SBD.FECHA_DOCUMENTO, 'DD/MM/YYYY')FECHA_DOCUMENTO, SBD.NUMERO,NVL(PTO.NOMBRE,' ') TIPO_OFICINA ,NVL(POO.NOMBRE,' ') OFICINA_ORIGEN ,NVL(PBJ.ID_NATURALEZA_JURIDICA,' ') "
		+ "CODIGO_ACTO,NVL(BAP.ESPECIFICACION,' ') DESCRIPCION_ACTO FROM SDB_BNG_ANOTACION_PREDIO BAP "
		+ "INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PBJ ON PBJ.ID_NATURALEZA_JURIDICA = BAP.ID_NATURALEZA_JURIDICA AND NVL(PBJ.HEREDAR_ANOTACION,'N') = 'S' AND BAP.VERSION = PBJ.VERSION "
		+ "INNER JOIN SDB_BGN_DOCUMENTO SBD ON  SBD.ID_DOCUMENTO = BAP.ID_DOCUMENTO "
		+ "INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO TDP ON TDP.ID_TIPO_DOCUMENTO = SBD.ID_TIPO_DOCUMENTO "
		+ "INNER JOIN SDB_PNG_ESTADO_ANOTACION PEA ON  PEA.ID_ESTADO_ANOTACION = BAP.ID_ESTADO_ANOTACION "
		+ "LEFT JOIN SDB_PGN_TIPO_OFICINA PTO ON PTO.ID_TIPO_OFICINA = SBD.ID_TIPO_OFICINA "
		+ "LEFT JOIN SDB_PGN_OFICINA_ORIGEN POO ON POO.ID_OFICINA_ORIGEN = SBD.ID_OFICINA_ORIGEN WHERE BAP.ID_CIRCULO = ? AND BAP.ID_MATRICULA = ? AND NVL(BAP.ANOTACION_CANCELADA,'N') <> 'S' AND (PBJ.ID_GRUPO_NAT_JURIDICA = '0400') ORDER BY BAP.ID_ANOTACION ASC";

	/** Constante cs_CONSULTA_GRAVAMENES_PENDIENTES_ANOTACION_PROHIBICION. */
	private static final String cs_CONSULTA_GRAVAMENES_PENDIENTES_ANOTACION_PROHIBICION = "SELECT BAP.FECHA_RADICACION,BAP.ID_TIPO_ANOTACION,BAP.ID_DOCUMENTO,BAP.ID_CIRCULO ID_CIRCULO_PREDIO ,BAP.ID_MATRICULA ID_MATRICULA_PREDIO,BAP.ID_ESTADO_ANOTACION,BAP.ID_ANOTACION "
		+ ",BAP.FECHA_REGISTRO ,BAP.RADICACION,BAP.ANOTACION_CANCELADA,BAP.ID_NATURALEZA_JURIDICA,BAP.VERSION,TO_CHAR(NVL(BAP.ESPECIFICACION,' ')) DESCRIPCION_ACTO ,PEA.NOMBRE ESTADO_ANOTACION "
		+ ",TDP.NOMBRE NOMBRE_DOCUMENTO,TO_CHAR(SBD.FECHA_DOCUMENTO,'DD/MM/YYYY')FECHA_DOCUMENTO,SBD.NUMERO,NVL(PTO.NOMBRE,' ') TIPO_OFICINA ,NVL(POO.NOMBRE,' ') OFICINA_ORIGEN ,NVL(PBJ.ID_NATURALEZA_JURIDICA,' ') CODIGO_ACTO "
		+ "FROM SDB_BNG_ANOTACION_PREDIO BAP "
		+ "INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PBJ ON PBJ.ID_NATURALEZA_JURIDICA = BAP.ID_NATURALEZA_JURIDICA AND NVL(PBJ.HEREDAR_ANOTACION,'N') = 'S' AND BAP.VERSION = PBJ.VERSION "
		+ "INNER JOIN SDB_BGN_DOCUMENTO SBD ON  SBD.ID_DOCUMENTO = BAP.ID_DOCUMENTO "
		+ "INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO TDP ON TDP.ID_TIPO_DOCUMENTO = SBD.ID_TIPO_DOCUMENTO "
		+ "INNER JOIN SDB_PNG_ESTADO_ANOTACION PEA ON  PEA.ID_ESTADO_ANOTACION = BAP.ID_ESTADO_ANOTACION "
		+ "LEFT JOIN SDB_PGN_TIPO_OFICINA PTO ON PTO.ID_TIPO_OFICINA = SBD.ID_TIPO_OFICINA "
		+ "LEFT JOIN SDB_PGN_OFICINA_ORIGEN POO ON POO.ID_OFICINA_ORIGEN = SBD.ID_OFICINA_ORIGEN "
		+ "WHERE BAP.ID_CIRCULO = ? " + "AND BAP.ID_MATRICULA = ? " + "AND NVL(BAP.ANOTACION_CANCELADA,'N') <> 'S' "
		+ "AND (PBJ.ID_GRUPO_NAT_JURIDICA = '0400' OR PBJ.ID_GRUPO_NAT_JURIDICA = '0200') " + "UNION "
		+ "SELECT AP.FECHA_RADICACION,AP.ID_TIPO_ANOTACION,AP.ID_DOCUMENTO,AP.ID_CIRCULO ID_CIRCULO_PREDIO ,AP.ID_MATRICULA ID_MATRICULA_PREDIO,AP.ID_ESTADO_ANOTACION,AP.ID_ANOTACION "
		+ ",AP.FECHA_REGISTRO ,AP.RADICACION,AP.ANOTACION_CANCELADA,AP.ID_NATURALEZA_JURIDICA,AP.VERSION ,TO_CHAR(NVL(AP.ESPECIFICACION,' ')) DESCRIPCION_ACTO ,EA.NOMBRE ESTADO_ANOTACION "
		+ ",TDP.NOMBRE NOMBRE_DOCUMENTO,TO_CHAR(D.FECHA_DOCUMENTO,'DD/MM/YYYY')FECHA_DOCUMENTO,D.NUMERO,NVL(TOF.NOMBRE,' ') TIPO_OFICINA ,NVL(OO.NOMBRE,' ') OFICINA_ORIGEN ,NVL(NJ.ID_NATURALEZA_JURIDICA,' ') CODIGO_ACTO "
		+ "FROM SDB_BNG_ANOTACION_PREDIO AP "
		+ "INNER JOIN SDB_BNG_REGISTRO_ANOTACION_PROHIBICION APH  ON AP.ID_CIRCULO=APH.ID_CIRCULO AND AP.ID_MATRICULA=APH.ID_MATRICULA and APH.ID_ANOTACION=AP.ID_ANOTACION "
		+ "INNER JOIN SDB_PNG_NATURALEZA_JURIDICA NJ ON  AP.ID_NATURALEZA_JURIDICA=NJ.ID_NATURALEZA_JURIDICA  AND AP.VERSION = NJ.VERSION "
		+ "INNER JOIN SDB_PNG_ESTADO_ANOTACION EA ON  EA.ID_ESTADO_ANOTACION = AP.ID_ESTADO_ANOTACION "
		+ "INNER JOIN SDB_BGN_DOCUMENTO D ON  D.ID_DOCUMENTO = AP.ID_DOCUMENTO "
		+ "INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO TDP ON TDP.ID_TIPO_DOCUMENTO = D.ID_TIPO_DOCUMENTO "
		+ "INNER JOIN SDB_PGN_TIPO_OFICINA TOF ON TOF.ID_TIPO_OFICINA = D.ID_TIPO_OFICINA "
		+ "LEFT  JOIN SDB_PGN_OFICINA_ORIGEN OO ON OO.ID_OFICINA_ORIGEN = D.ID_OFICINA_ORIGEN  and OO.VERSION=D.VERSION "
		+ "WHERE AP.ID_CIRCULO= ? " + "AND   AP.ID_MATRICULA= ? " + "AND   NVL(AP.ANOTACION_CANCELADA,'N') <> 'S' "
		+ "AND   APH.ACTIVO='S'";

	/**
	 * Retorna el valor del objeto de RegistroCalificacion.
	 *
	 * @param as_idAnotacion correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroCalificacion
	 */
	public RegistroCalificacion consultaAnotacion(String as_idAnotacion)
	    throws B2BException
	{
		RegistroCalificacion lca_data;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;
		int                  li_column;
		li_column     = 1;
		lca_data      = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(StringUtils.isValidString(as_idAnotacion))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_CONSULTA_ANOTACION);

				lps_ps.setString(li_column++, as_idAnotacion);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lca_data = getDetelleAnotacion(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultaAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lca_data;
	}

	/**
	 * Retorna el valor del objeto de RegistroCalificacion.
	 *
	 * @param as_idAnotacion correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroCalificacion
	 */
	public RegistroCalificacion consultaAnotacionPredio(String as_idAnotacion)
	    throws B2BException
	{
		RegistroCalificacion lca_data;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;
		int                  li_column;
		li_column     = 1;
		lca_data      = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(StringUtils.isValidString(as_idAnotacion))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_CONSULTA_ANOTACION_PREDIO);
				lps_ps.setString(li_column++, as_idAnotacion);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lca_data = getDetalleIntervinientes(lrs_rs, false);
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultaAnotacionPredio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lca_data;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto Long
	 * @param al_turnoHist correspondiente al valor del tipo de objeto Long
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @param as_turno correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<String> consultaRevision(
	    String as_idCirculo, Long al_idMatricula, Long al_turnoHist, boolean ab_b, String as_turno
	)
	    throws B2BException
	{
		Collection<String> lcs_cs;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;
		int                li_column;
		String             ls_query;
		li_column     = 1;
		lcs_cs        = new ArrayList<String>();
		lps_ps        = null;
		lrs_rs        = null;
		ls_query      = cs_CONSULTA_REVISION;

		if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula))
		{
			try
			{
				if(ab_b)
					ls_query = cs_CONSULTA_REVISION_DEVOLUCION;

				lps_ps = getConnection().prepareStatement(ls_query);
				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);

				if(ab_b)
					lps_ps.setString(li_column++, as_turno);
				else
					setLong(lps_ps, al_turnoHist, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcs_cs.add(lrs_rs.getString("REVISION_CALIFICADOR"));
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultaRevision", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcs_cs;
	}

	/**
	 * Delete interviniente.
	 *
	 * @param as_idInterviniente correspondiente al valor del tipo de objeto String
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteInterviniente(String as_idInterviniente, boolean ab_b)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		lps_ps = null;

		if(StringUtils.isValidString(as_idInterviniente))
		{
			try
			{
				int li_column;

				li_column = 1;

				if(ab_b)
					lps_ps = getConnection().prepareStatement(cs_DELETE_PREDIO_CIUDADANO);
				else
					lps_ps = getConnection().prepareStatement(cs_DELETE_PREDIO);

				lps_ps.setString(li_column++, as_idInterviniente);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteInterviniente", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Delete interviniente solicitud.
	 *
	 * @param as_idPersona correspondiente al valor del tipo de objeto String
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteIntervinienteSolicitud(String as_idPersona, String as_idSolicitud)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		lps_ps = null;

		if(StringUtils.isValidString(as_idPersona) && StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_DELETE_INTERVINIENTE);

				lps_ps.setString(li_column++, as_idPersona);
				lps_ps.setString(li_column++, as_idSolicitud);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteIntervinienteSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor del objeto de LinkedHashMap.
	 *
	 * @param arc_parametros correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @return devuelve el valor de LinkedHashMap
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public LinkedHashMap<String, LinkedHashMap<Long, String>> findAnotacionesParciales(
	    RegistroCalificacion arc_parametros
	)
	    throws B2BException
	{
		LinkedHashMap<String, LinkedHashMap<Long, String>> lcl_return;

		lcl_return = null;

		if(arc_parametros != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count       = 1;
				lcl_return     = new LinkedHashMap<String, LinkedHashMap<Long, String>>();

				lps_ps = getConnection().prepareStatement(cs_CONSULTA_ANOTACIONES_PARCIALES);

				lps_ps.setString(li_count++, arc_parametros.getIdCirculo());
				setLong(lps_ps, arc_parametros.getIdMatricula(), li_count++);
				lps_ps.setString(li_count++, StringUtils.getString(arc_parametros.getIdTurnoHistoria()));

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					LinkedHashMap<Long, String> llhm_data;
					Long                        ll_idAnotacion;
					String                      ls_estado;
					String                      ls_idAnotacionPredio;

					llhm_data                = new LinkedHashMap<Long, String>();
					ll_idAnotacion           = getLong(lrs_rs, "ID_ANOTACION");
					ls_estado                = lrs_rs.getString("ESTADO");
					ls_idAnotacionPredio     = lrs_rs.getString("ID_ANOTACION_PREDIO");

					llhm_data.put(ll_idAnotacion, ls_estado);
					lcl_return.put(ls_idAnotacionPredio, llhm_data);
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "anotacionesParciales", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}

			if(lcl_return.isEmpty())
				lcl_return = null;
		}

		return lcl_return;
	}

	/**
	 * Retorna el valor del objeto de RegistroCalificacion.
	 *
	 * @param al_idTurnoHistoria correspondiente al valor del tipo de objeto Long
	 * @return devuelve el valor de RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroCalificacion
	 */
	public RegistroCalificacion findBasicosDocumento(Long al_idTurnoHistoria)
	    throws B2BException
	{
		RegistroCalificacion lca_data;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;
		int                  li_column;
		li_column     = 1;
		lca_data      = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(NumericUtils.isValidLong(al_idTurnoHistoria))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DETALLE_DOCUMENTO);

				setLong(lps_ps, al_idTurnoHistoria, li_column++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lca_data = getDetelleDocumento(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findBasicosDocumento", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lca_data;
	}

	/**
	 * Retorna el valor del objeto de RegistroCalificacion.
	 *
	 * @param as_circulo correspondiente al valor del tipo de objeto String
	 * @param al_matricula correspondiente al valor del tipo de objeto Long
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroCalificacion
	 */
	public RegistroCalificacion findBasicosMatricula(String as_circulo, Long al_matricula, boolean ab_b)
	    throws B2BException
	{
		RegistroCalificacion lca_data;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;
		int                  li_column;
		String               ls_query;
		li_column     = 1;
		lca_data      = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(StringUtils.isValidString(as_circulo) && NumericUtils.isValidLong(al_matricula))
		{
			try
			{
				if(ab_b)
					ls_query = cs_DATA_MATRICULA;
				else
					ls_query = cs_DATA_MATRICULA_ACC;

				lps_ps = getConnection().prepareStatement(ls_query);

				lps_ps.setString(li_column++, as_circulo);
				setLong(lps_ps, al_matricula, li_column++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lca_data = getDetelleMatricula(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findBasicosMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lca_data;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param aorc_rc correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<RegistroCalificacion> findDataPredio(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		Collection<RegistroCalificacion> lca_data;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;
		int                              li_column;
		StringBuilder                    lsb_sb;
		li_column     = 1;
		lca_data      = new ArrayList<RegistroCalificacion>();
		lps_ps        = null;
		lrs_rs        = null;
		lsb_sb        = new StringBuilder(cs_DATA_PREDIO);

		if(aorc_rc != null)
		{
			try
			{
				boolean lb_b;

				lb_b = aorc_rc.isIndVinculado();

				if(lb_b)
					lsb_sb.append(" AND ID_TURNO_HISTORIA IN (" + aorc_rc.getCodActo() + ")");
				else
					lsb_sb.append(" AND ID_TURNO_HISTORIA = ?");

				if(aorc_rc.isRevision())
					lsb_sb = lsb_sb.append(" AND NVL (ANP.INDICADOR_CREAR_PREDIO_CIUDADANO,'N') = 'S'");

				lsb_sb.append(" ORDER BY ANP.ID_ANOTACION ASC");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, aorc_rc.getIdCirculo());
				setLong(lps_ps, aorc_rc.getIdMatricula(), li_column++);

				if(!lb_b)
					lps_ps.setString(li_column++, aorc_rc.getIdTurnoHistoria().toString());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_data.add(getDataAnotaciones(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDataPredio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lca_data.isEmpty())
			lca_data = null;

		return lca_data;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param al_idAnotacionPredio correspondiente al valor del tipo de objeto Long
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<RegistroCalificacion> findDataPredioAnotacion(Long al_idAnotacionPredio)
	    throws B2BException
	{
		Collection<RegistroCalificacion> lca_data;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;
		int                              li_column;
		li_column     = 1;
		lca_data      = new ArrayList<RegistroCalificacion>();
		lps_ps        = null;
		lrs_rs        = null;

		if(NumericUtils.isValidLong(al_idAnotacionPredio))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DATA_PREDIO_ANOTACIONES);

				setLong(lps_ps, al_idAnotacionPredio, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_data.add(getDataAnotaciones(lrs_rs, false));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDataPredioAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lca_data.isEmpty())
			lca_data = null;

		return lca_data;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param aap_anotacionPredio correspondiente al valor del tipo de objeto AnotacionPredio
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<RegistroCalificacion> findDataPredioAnotacionBng(AnotacionPredio aap_anotacionPredio)
	    throws B2BException
	{
		Collection<RegistroCalificacion> lca_data;
		int                              li_column;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;

		lca_data      = new ArrayList<RegistroCalificacion>();
		li_column     = 1;
		lps_ps        = null;
		lrs_rs        = null;

		if(aap_anotacionPredio != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DATA_PREDIO_ANOTACIONES_BNG);

				lps_ps.setString(li_column++, aap_anotacionPredio.getIdCirculo());
				setLong(lps_ps, aap_anotacionPredio.getIdMatricula(), li_column++);
				setLong(lps_ps, aap_anotacionPredio.getIdAnotacion(), li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_data.add(getDataAnotacionesBng(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDataPredioAnotacionBng", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lca_data.isEmpty())
			lca_data = null;

		return lca_data;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_idAnotacion correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<RegistroCalificacion> findDetalleIntervinientes(String as_idAnotacion)
	    throws B2BException
	{
		Collection<RegistroCalificacion> lca_data;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;
		int                              li_column;
		li_column     = 1;
		lca_data      = new ArrayList<RegistroCalificacion>();
		lps_ps        = null;
		lrs_rs        = null;

		if(StringUtils.isValidString(as_idAnotacion))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_CONSULTA_DETALLE_INTERVINIENTES);
				lps_ps.setString(li_column++, as_idAnotacion);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_data.add(getDetalleIntervinientes(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDetalleIntervinientes", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lca_data.isEmpty())
			lca_data = null;

		return lca_data;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_circulo correspondiente al valor del tipo de objeto String
	 * @param al_matricula correspondiente al valor del tipo de objeto Long
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findDireccionMatricula(String as_circulo, Long al_matricula, String as_idSolicitud)
	    throws B2BException
	{
		String            ls_direccion;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;
		li_column        = 1;
		ls_direccion     = null;
		lps_ps           = null;
		lrs_rs           = null;

		if(
		    StringUtils.isValidString(as_circulo) && StringUtils.isValidString(as_idSolicitud)
			    && NumericUtils.isValidLong(al_matricula)
		)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DIRECCION_MATRICULA);

				lps_ps.setString(li_column++, as_idSolicitud);
				lps_ps.setString(li_column++, as_circulo);
				setLong(lps_ps, al_matricula, li_column++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_direccion = lrs_rs.getString("DIRECCION");
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDireccionMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_direccion;
	}

	/**
	 * Método sobreescrito para consultar siempre con idGrupoNaturalezaJuridica 0200 y 0400.
	 *
	 * @param as_circulo String que contiene el circulo de busqueda
	 * @param as_matricula String que contiene la matricula de busqueda
	 * @return Collection<GravamenPendiente> Colección de GravamenPendiente
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<GravamenPendiente> findGravamenPendiente(String as_circulo, String as_matricula)
	    throws B2BException
	{
		return findGravamenPendiente(as_circulo, as_matricula, false);
	}

	/**
	 * Método para consultar gravamenes pendientes.
	 *
	 * @param as_circulo String que contiene el circulo de busqueda
	 * @param as_matricula String que contiene la matricula de busqueda
	 * @param ab_just0400Group booleana para saber si consultar Grupo juridico 0400 y 0200 o 0400
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<GravamenPendiente> findGravamenPendiente(
	    String as_circulo, String as_matricula, boolean ab_just0400Group
	)
	    throws B2BException
	{
		Collection<GravamenPendiente> lca_data;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;
		int                           li_column;

		li_column     = 1;
		lca_data      = new ArrayList<GravamenPendiente>();
		lps_ps        = null;
		lrs_rs        = null;

		if(StringUtils.isValidString(as_circulo) && StringUtils.isValidString(as_matricula))
		{
			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder();

				if(ab_just0400Group)
					lsb_query.append(cs_CONSULTA_GRAVAMENES_PENDIENTES);
				else
					lsb_query.append(cs_CONSULTA_GRAVAMENES_PENDIENTES_ANOTACION_PROHIBICION);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, as_circulo);
				lps_ps.setString(li_column++, as_matricula);

				if(!ab_just0400Group)
				{
					lps_ps.setString(li_column++, as_circulo);
					lps_ps.setString(li_column++, as_matricula);
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_data.add(getGravamenPendiente(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findGravamenPendiente", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lca_data.isEmpty())
			lca_data = null;

		return lca_data;
	}

	/**
	 * Insertar intervinientes.
	 *
	 * @param aorc_rc correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertarIntervinientes(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		lps_ps = null;

		if(aorc_rc != null)
		{
			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_INSERTAR_INTERVINIENTES);

				lps_ps.setString(li_column++, aorc_rc.getIdAnotacionPredio());
				lps_ps.setString(li_column++, aorc_rc.getIdCirculo());
				setLong(lps_ps, aorc_rc.getIdMatricula(), li_column++);
				setLong(lps_ps, aorc_rc.getIdAnotacion(), li_column++);
				lps_ps.setString(li_column++, aorc_rc.getIdPersona());
				lps_ps.setString(li_column++, aorc_rc.getRolPersona());
				lps_ps.setString(li_column++, aorc_rc.getPorcentajeStr());

				lps_ps.setString(li_column++, aorc_rc.getUserId());
				lps_ps.setString(li_column++, aorc_rc.getIpCreacion());
				lps_ps.setString(li_column++, aorc_rc.getTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertarIntervinientes", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param aorc_rc correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<RegistroCalificacion> matriculasAHeredar(RegistroCalificacion aorc_rc, boolean ab_b)
	    throws B2BException
	{
		Collection<RegistroCalificacion> lca_data;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;
		int                              li_column;
		String                           ls_query;

		li_column     = 1;
		lca_data      = new ArrayList<RegistroCalificacion>();
		lps_ps        = null;
		lrs_rs        = null;
		ls_query      = cs_CONSULTA_MATRICULA_A_HEREDAR;

		if(aorc_rc != null)
		{
			try
			{
				if(ab_b)
					ls_query = cs_CONSULTA_MATRICULA_A_HEREDAR_ACC;

				lps_ps = getConnection().prepareStatement(ls_query);

				lps_ps.setString(li_column++, aorc_rc.getIdCirculo());
				setLong(lps_ps, aorc_rc.getIdMatricula(), li_column++);

				if(ab_b)
					lps_ps.setString(li_column++, aorc_rc.getTurno());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_data.add(getAnotacionHeredar(lrs_rs, ab_b));
			}
			catch(SQLException lse_e)
			{
				logError(this, "matriculasHeredar", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lca_data.isEmpty())
			lca_data = null;

		return lca_data;
	}

	/**
	 * Modificar anotacion.
	 *
	 * @param ap_parametros correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void modificarAnotacion(RegistroCalificacion ap_parametros, boolean ab_b)
	    throws B2BException
	{
		if(ap_parametros != null)
		{
			PreparedStatement lps_ps;
			String            ls_query;
			lps_ps = null;

			try
			{
				int li_column;

				li_column = 1;

				if(ab_b)
					ls_query = cs_MODIFICAR_ANOTACION;
				else
					ls_query = cs_MODIFICAR_ANOTACION_CIUDADANO;

				lps_ps = getConnection().prepareStatement(ls_query);

				setLong(lps_ps, ap_parametros.getIdAnotacion(), li_column++);

				if(ab_b)
					setLong(lps_ps, ap_parametros.getOrden(), li_column++);

				lps_ps.setString(li_column++, ap_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ap_parametros.getIdAnotacionPredio());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "modificarAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Modificar persona.
	 *
	 * @param ap_parametros correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void modificarPersona(RegistroCalificacion ap_parametros)
	    throws B2BException
	{
		if(ap_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_MODIFICAR_PERSONA);
				lps_ps.setString(li_column++, ap_parametros.getPrimerNombre());
				lps_ps.setString(li_column++, ap_parametros.getSegundoNombre());
				lps_ps.setString(li_column++, ap_parametros.getPrimerApellido());
				lps_ps.setString(li_column++, ap_parametros.getSegundoApellido());
				lps_ps.setString(li_column++, ap_parametros.getCodDocumento());
				lps_ps.setString(li_column++, ap_parametros.getDocumento());
				lps_ps.setString(li_column++, ap_parametros.getUserId());
				lps_ps.setString(li_column++, ap_parametros.getRazonSocial());

				lps_ps.setString(li_column++, ap_parametros.getIdPersona());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "modificarPersona", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Modificar revision anotacion.
	 *
	 * @param ap_parametros correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void modificarRevisionAnotacion(RegistroCalificacion ap_parametros)
	    throws B2BException
	{
		if(ap_parametros != null)
		{
			PreparedStatement lps_ps;
			StringBuilder     lsb_sb;

			lps_ps     = null;
			lsb_sb     = new StringBuilder(cs_MODIFICAR_REVISION);

			try
			{
				int li_column;

				li_column = 1;

				if(ap_parametros.isRevision())
					lsb_sb = lsb_sb.append(",REVISION_CALIFICADOR= ?  ");

				lsb_sb     = lsb_sb.append(" WHERE ID_ANOTACION_PREDIO = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, ap_parametros.getIdUsuario());

				if(ap_parametros.isRevision())
					lps_ps.setString(li_column++, ap_parametros.getRevision());

				lps_ps.setString(li_column++, ap_parametros.getIdAnotacionPredio());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "modificarRevisionAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Save detail anotacion.
	 *
	 * @param ap_parametros correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void saveDetailAnotacion(RegistroCalificacion ap_parametros)
	    throws B2BException
	{
		if(ap_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_MODIFICAR_BASICO_ANOTACION);

				lps_ps.setString(li_column++, ap_parametros.getRolPersona());
				lps_ps.setString(li_column++, ap_parametros.getIdPersona());
				lps_ps.setString(li_column++, ap_parametros.getUserId());
				lps_ps.setString(li_column++, ap_parametros.getValuePropietario());
				lps_ps.setString(li_column++, ap_parametros.getPorcentajeStr());
				lps_ps.setString(li_column++, ap_parametros.getInteresadaRrr());
				lps_ps.setString(li_column++, ap_parametros.getIdAnotacionPredio());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "saveDetailAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor de anotacion heredar.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return el valor de anotacion heredar
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RegistroCalificacion getAnotacionHeredar(ResultSet ars_rs, boolean ab_b)
	    throws SQLException
	{
		RegistroCalificacion lorc_datos;
		lorc_datos = new RegistroCalificacion();

		lorc_datos.setFechaRadicacion(ars_rs.getDate("FECHA_RADICACION"));
		lorc_datos.setIdTipoAnotacion(ars_rs.getString("ID_TIPO_ANOTACION"));
		lorc_datos.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		lorc_datos.setCodEstadoAnotacion(ars_rs.getString("ID_ESTADO_ANOTACION"));
		lorc_datos.setIdMatricula(getLong(ars_rs, "ID_MATRICULA_PREDIO"));
		lorc_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO_PREDIO"));
		lorc_datos.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		lorc_datos.setFechaRegistro(ars_rs.getDate("FECHA_REGISTRO"));
		lorc_datos.setRadicacion(ars_rs.getString("RADICACION"));
		lorc_datos.setIdEstadoAnotacion(ars_rs.getString("ESTADO_ANOTACION"));
		lorc_datos.setDocumento(ars_rs.getString("NOMBRE_DOCUMENTO"));
		lorc_datos.setFechaDocumentoStr(ars_rs.getString("FECHA_DOCUMENTO"));
		lorc_datos.setNumero(ars_rs.getString("NUMERO"));
		lorc_datos.setNombreOficina(ars_rs.getString("TIPO_OFICINA"));
		lorc_datos.setNombreOficinaOrigen(ars_rs.getString("OFICINA_ORIGEN"));
		lorc_datos.setCodActo(ars_rs.getString("CODIGO_ACTO"));
		lorc_datos.setNombreActo(ars_rs.getString("DESCRIPCION_ACTO"));
		lorc_datos.setRevision(false);

		if(ab_b)
		{
			lorc_datos.setIdAnotacionPredio(ars_rs.getString("ID_ANOTACION_PREDIO"));
			lorc_datos.setRevision(true);
		}

		return lorc_datos;
	}

	/**
	 * Retorna el valor de data anotaciones.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return el valor de data anotaciones
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RegistroCalificacion getDataAnotaciones(ResultSet ars_rs, boolean ab_b)
	    throws SQLException
	{
		RegistroCalificacion lorc_datos;
		boolean              lb_b;

		lorc_datos     = new RegistroCalificacion();
		lb_b           = false;

		if(ab_b)
		{
			lorc_datos.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
			lorc_datos.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
			lorc_datos.setVersion(getLong(ars_rs, "VERSION"));
			lorc_datos.setIdAnotacionPredio(ars_rs.getString("ID_ANOTACION_PREDIO"));
			lorc_datos.setFechaRegistro(ars_rs.getDate("FECHA_REGISTRO"));
			lorc_datos.setFechaRadicacion(ars_rs.getDate("FECHA_RADICACION"));
			lorc_datos.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));
			lorc_datos.setTurno(ars_rs.getString("ID_TURNO"));
			lorc_datos.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
			lorc_datos.setNombreDoc(ars_rs.getString("NOMBRE_DOC"));
			lorc_datos.setNombreActo(ars_rs.getString("NOMBRE_ACTO"));
			lorc_datos.setNombreGrupoActo(ars_rs.getString("NOMBRE_GRUPO_ACTO"));
			lorc_datos.setCodActo(ars_rs.getString("COD_ACTO"));
			lorc_datos.setComentario(ars_rs.getString("COMENTARIO"));

			if(ars_rs.getString("REVISION_CALIFICADOR").equalsIgnoreCase(EstadoCommon.S))
				lb_b = true;

			lorc_datos.setRevision(lb_b);

			lorc_datos.setValor(ars_rs.getBigDecimal("VALOR"));
			lorc_datos.setIdNaturalezJuridica(ars_rs.getString("ID_GRUPO_NAT_JURIDICA"));
		}
		else
		{
			lorc_datos.setIdCiudadano(ars_rs.getString("ID_ANOTACION_PREDIO_CIUDADANO"));
			lorc_datos.setNombrePersona(ars_rs.getString("NOMBRE_PERSONA"));
			lorc_datos.setRazonSocial(ars_rs.getString("RAZON_SOCIAL"));
			lorc_datos.setRolPersona(ars_rs.getString("ROL_PERSONA"));
			lorc_datos.setTipoDoc(ars_rs.getString("ID_DOCUMENTO_TIPO"));
			lorc_datos.setDocumento(ars_rs.getString("NUMERO_DOCUMENTO"));
			lorc_datos.setIdTipoPersona(ars_rs.getString("ID_TIPO_PERSONA"));
			lorc_datos.setValuePropietario(ars_rs.getString("PROPIETARIO"));
			lorc_datos.setPorcentajeStr(ars_rs.getString("PORCENTAJE_PARTICIPACION"));
			lorc_datos.setInteresadaRrr(ars_rs.getString("ID_PARTE_INTERESADA"));
		}

		return lorc_datos;
	}

	/**
	 * Retorna el valor de data anotaciones bng.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de data anotaciones bng
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RegistroCalificacion getDataAnotacionesBng(ResultSet ars_rs)
	    throws SQLException
	{
		RegistroCalificacion lorc_datos;

		lorc_datos = new RegistroCalificacion();

		lorc_datos.setNombrePersona(ars_rs.getString("NOMBRE_PERSONA"));
		lorc_datos.setRolPersona(ars_rs.getString("ROL_PERSONA"));
		lorc_datos.setTipoDoc(ars_rs.getString("ID_DOCUMENTO_TIPO"));
		lorc_datos.setDocumento(ars_rs.getString("NUMERO_DOCUMENTO"));
		lorc_datos.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		lorc_datos.setValuePropietario(ars_rs.getString("PROPIETARIO"));
		lorc_datos.setPorcentajeStr(ars_rs.getString("PORCENTAJE_PARTICIPACION"));
		lorc_datos.setInteresadaRrr(ars_rs.getString("ID_PARTE_INTERESADA"));
		lorc_datos.setRazonSocial(ars_rs.getString("RAZON_SOCIAL"));

		return lorc_datos;
	}

	/**
	 * Retorna el valor de detalle intervinientes.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return el valor de detalle intervinientes
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RegistroCalificacion getDetalleIntervinientes(ResultSet ars_rs, boolean ab_b)
	    throws SQLException
	{
		RegistroCalificacion lorc_datos;
		lorc_datos = new RegistroCalificacion();

		if(ab_b)
		{
			lorc_datos.setPrimerNombre(ars_rs.getString("PRIMER_NOMBRE"));
			lorc_datos.setSegundoNombre(ars_rs.getString("SEGUNDO_NOMBRE"));
			lorc_datos.setPrimerApellido(ars_rs.getString("PRIMER_APELLIDO"));
			lorc_datos.setSegundoApellido(ars_rs.getString("SEGUNDO_APELLIDO"));
			lorc_datos.setRazonSocial(ars_rs.getString("RAZON_SOCIAL"));
			lorc_datos.setRolPersona(ars_rs.getString("ROL_PERSONA"));
			lorc_datos.setCodDocumento(ars_rs.getString("ID_DOCUMENTO_TIPO"));
			lorc_datos.setDocumento(ars_rs.getString("NUMERO_DOCUMENTO"));
			lorc_datos.setInteresadaRrr(ars_rs.getString("ID_PARTE_INTERESADA"));
			lorc_datos.setIdAnotacionPredio(ars_rs.getString("ID_ANOTACION_PREDIO_CIUDADANO"));
			lorc_datos.setValuePropietario(ars_rs.getString("PROPIETARIO"));
			lorc_datos.setPorcentajeStr(ars_rs.getString("PORCENTAJE_PARTICIPACION"));
			lorc_datos.setIdPersona(ars_rs.getString("ID_PERSONA"));
		}
		else
		{
			lorc_datos.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
			lorc_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
			lorc_datos.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
			lorc_datos.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
			lorc_datos.setIdNaturalezJuridica(ars_rs.getString("ID_NATURALEZA_JURIDICA"));
			lorc_datos.setIdAnotacionPredio(ars_rs.getString("ID_ANOTACION_PREDIO"));
		}

		return lorc_datos;
	}

	/**
	 * Retorna el valor de detelle anotacion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de detelle anotacion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RegistroCalificacion getDetelleAnotacion(ResultSet ars_rs)
	    throws SQLException
	{
		RegistroCalificacion lorc_rc;
		lorc_rc = new RegistroCalificacion();

		lorc_rc.setOrden(getLong(ars_rs, "ORDEN"));
		lorc_rc.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));

		return lorc_rc;
	}

	/**
	 * Retorna el valor de detelle documento.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de detelle documento
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RegistroCalificacion getDetelleDocumento(ResultSet ars_rs)
	    throws SQLException
	{
		RegistroCalificacion lorc_rc;
		lorc_rc = new RegistroCalificacion();

		lorc_rc.setCodDocumento(ars_rs.getString("NUMERO"));
		lorc_rc.setFechaDocumento(ars_rs.getDate("FECHA_DOCUMENTO"));
		lorc_rc.setNombreOficina(ars_rs.getString("NOMBRE_OFICINA"));
		lorc_rc.setNombreMunicipio(ars_rs.getString("MUNICIPIO"));

		return lorc_rc;
	}

	/**
	 * Retorna el valor de detelle matricula.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de detelle matricula
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RegistroCalificacion getDetelleMatricula(ResultSet ars_rs)
	    throws SQLException
	{
		RegistroCalificacion lorc_rc;
		lorc_rc = new RegistroCalificacion();

		lorc_rc.setNombreDepartamento(ars_rs.getString("NOMBRE_DEPARTAMENTO"));
		lorc_rc.setNombreMunicipio(ars_rs.getString("NOMBRE_MUNICIPIO"));
		lorc_rc.setTipoPredio(ars_rs.getString("TIPO_PREDIO"));
		lorc_rc.setIdCirculo(ars_rs.getString("COD_CIRCULO"));
		lorc_rc.setNombreCirculo(ars_rs.getString("NOMBRE_CIRCULO"));

		return lorc_rc;
	}

	/**
	 * Retorna el valor de gravamen pendiente.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de gravamen pendiente
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private GravamenPendiente getGravamenPendiente(ResultSet ars_rs)
	    throws SQLException
	{
		GravamenPendiente lgp_gravamen;
		lgp_gravamen = new GravamenPendiente();

		lgp_gravamen.setFechaRadicacion(ars_rs.getTimestamp("FECHA_RADICACION"));
		lgp_gravamen.setIdTipoAnotacion(ars_rs.getString("ID_TIPO_ANOTACION"));
		lgp_gravamen.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		lgp_gravamen.setIdCirculoPredio(ars_rs.getString("ID_CIRCULO_PREDIO"));
		lgp_gravamen.setIdMatriculaPredio(ars_rs.getString("ID_MATRICULA_PREDIO"));
		lgp_gravamen.setIdEstadoAnotacion(ars_rs.getString("ID_ESTADO_ANOTACION"));
		lgp_gravamen.setIdAnotacion(ars_rs.getLong("ID_ANOTACION"));
		lgp_gravamen.setFechaRegistro(ars_rs.getTimestamp("FECHA_REGISTRO"));
		lgp_gravamen.setRadicacion(ars_rs.getString("RADICACION"));
		lgp_gravamen.setEstadoAnotacion(ars_rs.getString("ESTADO_ANOTACION"));
		lgp_gravamen.setNombreDocumento(ars_rs.getString("NOMBRE_DOCUMENTO"));
		lgp_gravamen.setFechaDocumento(ars_rs.getString("FECHA_DOCUMENTO"));
		lgp_gravamen.setNumero(ars_rs.getString("NUMERO"));
		lgp_gravamen.setTipoOficina(ars_rs.getString("TIPO_OFICINA"));
		lgp_gravamen.setOficinaOrigen(ars_rs.getString("OFICINA_ORIGEN"));
		lgp_gravamen.setCodigoActo(ars_rs.getString("CODIGO_ACTO"));
		lgp_gravamen.setDescripcionActo(ars_rs.getString("DESCRIPCION_ACTO"));

		return lgp_gravamen;
	}
}
