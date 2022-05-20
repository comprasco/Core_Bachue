package com.bachue.snr.prosnr01.dao.bng;

import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Anotacion;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.dao.ConstantesDao;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.consulta.predio.ConsultaPredio;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Persona;
import com.bachue.snr.prosnr01.model.registro.ProhibicionVPM;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioDireccion;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.DocumentoConstancia;

import com.bachue.snr.prosnr10.common.constants.TipoOperacionCommon;
import com.bachue.snr.prosnr10.common.constants.TipoRRRCommon;

import com.bachue.snr.prosnr10.model.catastro.AnotacionCatastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Clase que contiene todos las consultas de la tabla SDB_BNG_ANOTACION_PREDIO.
 *
 * @author jpatino
 */
public class AnotacionPredioDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_BNG_ANOTACION_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ?";

	/** Constante cs_FIND_BY_ID_ACC. */
	private static final String cs_FIND_BY_ID_ACC = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ?";

	/** Constante cs_FIND_BY_ID_DOCUMENTO_VERSION. */
	private static final String cs_FIND_BY_ID_DOCUMENTO_VERSION = "SELECT * FROM SDB_BNG_ANOTACION_PREDIO WHERE ID_DOCUMENTO = ? AND VERSION_DOCUMENTO = ? AND ID_ESTADO_ANOTACION = 'V' ORDER BY ID_CIRCULO, ID_MATRICULA";

	/** Constante CS_CONSULTA_DATOS_REGISTRALES. */
	private static final String CS_CONSULTA_DATOS_REGISTRALES = "SELECT SBAP.ID_ANOTACION CONSECUTIVO_ANOTACION, SPTDP.NOMBRE TIPO_DOCUMENTO_ANOTACION, SPNJ.NOMBRE ACTO_JURIDICO, SCTR.DESCRIPCION DRR, TO_CHAR(NVL(SBAP.VALOR,'0'),'999999999999999999.99') VALOR_ACTO, TO_CHAR(SBAP.FECHA_CREACION, 'DD/MM/YYYY') FECHA_ANOTACION FROM SDB_BNG_ANOTACION_PREDIO SBAP INNER JOIN SDB_BGN_DOCUMENTO SBD ON SBD.ID_DOCUMENTO = SBAP.ID_DOCUMENTO INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO SPTDP ON SPTDP.ID_TIPO_DOCUMENTO = SBD.ID_TIPO_DOCUMENTO INNER JOIN SDB_PNG_NATURALEZA_JURIDICA SPNJ ON SPNJ.ID_NATURALEZA_JURIDICA = SBAP.ID_NATURALEZA_JURIDICA AND SPNJ.VERSION = (SELECT MAX(SPNJ2.VERSION) FROM SDB_PNG_NATURALEZA_JURIDICA SPNJ2 WHERE SPNJ2.ID_NATURALEZA_JURIDICA = SPNJ.ID_NATURALEZA_JURIDICA) INNER JOIN SDB_COL_TIPO_RRR SCTR ON SCTR.ID_TIPO_RRR = SPNJ.ID_TIPO_RRR WHERE SBAP.ID_CIRCULO = ? AND SBAP.ID_MATRICULA = ?";

	/** Constante cs_FIND_BY_ID_DOCUMENTO_VERSION_CIRCULO. */
	private static final String cs_FIND_BY_ID_DOCUMENTO_VERSION_CIRCULO = "SELECT * FROM SDB_BNG_ANOTACION_PREDIO WHERE ID_DOCUMENTO = ? AND VERSION_DOCUMENTO = ? AND ID_ESTADO_ANOTACION = 'V' AND ID_CIRCULO = ? ORDER BY ID_CIRCULO, ID_MATRICULA";

	/** Constante cs_FIND_SEGREGACION_AGREGACION_BY_ID_CIRCULO_MATRICULA. */
	private static final String cs_FIND_SEGREGACION_AGREGACION_BY_ID_CIRCULO_MATRICULA = "SELECT C.ID_CIRCULO, C.ID_MATRICULA, MAX(C.ORDEN) ORDEN,C.ID_ANOTACION, C.TIPO_ANOTACION, C.FECHA_CREACION, C.ID_TIPO_ACTO,C.NOMBRE FROM ("
		+ "	SELECT A.ID_CIRCULO,A.ID_MATRICULA,MAX(NVL(A.ORDEN,A.ID_ANOTACION)) ORDEN,A.ID_ANOTACION,'AGREGACION' AS TIPO_ANOTACION, A.FECHA_CREACION, TA.ID_TIPO_ACTO, TA.NOMBRE"
		+ "	FROM  SDB_BNG_ANOTACION_PREDIO A "
		+ "		INNER JOIN SDB_PGN_TIPO_ACTO TA ON (TA.ID_TIPO_ACTO = A.ID_NATURALEZA_JURIDICA AND TA.VERSION = A.VERSION AND TA.ID_TIPO_ACTO = '0919') "
		+ "		INNER JOIN SDB_PNG_NATURALEZA_JURIDICA NJ ON (NJ.ID_NATURALEZA_JURIDICA = A.ID_NATURALEZA_JURIDICA AND NJ.VERSION = A.VERSION AND NJ.ID_GRUPO_NAT_JURIDICA @@_NOMBRE_INCLUSION_@@ (0100,0300))"
		+ "	WHERE EXISTS(SELECT 1 FROM SDB_BNG_PREDIO_SEGREGADO PS WHERE PS.ID_CIRCULO1 = A.ID_CIRCULO AND PS.ID_MATRICULA1 = A.ID_MATRICULA AND PS.ID_ANOTACION1 = A.ID_ANOTACION)"
		+ "	GROUP BY A.ID_CIRCULO, A.ID_MATRICULA,A.ID_ANOTACION, 'AGREGACION', A.FECHA_CREACION, TA.ID_TIPO_ACTO, TA.NOMBRE  UNION ALL"
		+ "	SELECT A.ID_CIRCULO,A.ID_MATRICULA,MAX(NVL(A.ORDEN,A.ID_ANOTACION)) ORDEN,A.ID_ANOTACION,'SEGREGACION' AS TIPO_ANOTACION, A.FECHA_CREACION, TA.ID_TIPO_ACTO, TA.NOMBRE"
		+ "	FROM  SDB_BNG_ANOTACION_PREDIO A "
		+ "		INNER JOIN SDB_PGN_TIPO_ACTO TA ON (TA.ID_TIPO_ACTO = A.ID_NATURALEZA_JURIDICA AND TA.VERSION = A.VERSION AND NVL(TA.GENERA_SEGREGACION,'S') = 'S') "
		+ "		INNER JOIN SDB_PNG_NATURALEZA_JURIDICA NJ ON (NJ.ID_NATURALEZA_JURIDICA = A.ID_NATURALEZA_JURIDICA AND NJ.VERSION = A.VERSION AND NJ.ID_GRUPO_NAT_JURIDICA @@_NOMBRE_INCLUSION_@@ (0100,0300))"
//		+ "	WHERE EXISTS(SELECT 1 FROM SDB_BNG_PREDIO_SEGREGADO PS WHERE PS.ID_CIRCULO = A.ID_CIRCULO AND PS.ID_MATRICULA = A.ID_MATRICULA AND PS.ID_ANOTACION = A.ID_ANOTACION)"
		+ "	GROUP BY A.ID_CIRCULO, A.ID_MATRICULA,A.ID_ANOTACION, 'SEGREGACION', A.FECHA_CREACION, TA.ID_TIPO_ACTO, TA.NOMBRE) C "
		+ "WHERE C.ID_CIRCULO = ? AND	C.ID_MATRICULA = ?"
		+ "GROUP BY C.ID_CIRCULO,C.ID_MATRICULA,C.ID_ANOTACION,C.TIPO_ANOTACION,C.FECHA_CREACION, C.ID_TIPO_ACTO, C.NOMBRE ORDER BY C.FECHA_CREACION DESC";

	/** Constante cs_FIND_SEGREGACION_AGREGACION_SOCP_A_NOTIFICAR. */
	private static final String cs_FIND_SEGREGACION_AGREGACION_SOCP_A_NOTIFICAR = "SELECT C.ID_CIRCULO, C.ID_MATRICULA, MAX(C.ORDEN) ORDEN,C.ID_ANOTACION, C.TIPO_ANOTACION, C.FECHA_CREACION, C.ID_TIPO_ACTO, C.NOMBRE, C.ID_NATURALEZA_JURIDICA, C.VERSION FROM ("
		+ "	SELECT A.ID_CIRCULO,A.ID_MATRICULA,MAX(NVL(A.ORDEN,A.ID_ANOTACION)) ORDEN,A.ID_ANOTACION,'AGREGACION' AS TIPO_ANOTACION, A.FECHA_CREACION, TA.ID_TIPO_ACTO, TA.NOMBRE,A.ID_NATURALEZA_JURIDICA,A.VERSION"
		+ "	FROM  SDB_BNG_ANOTACION_PREDIO A "
		+ "		INNER JOIN SDB_PGN_TIPO_ACTO TA ON (TA.ID_TIPO_ACTO = A.ID_NATURALEZA_JURIDICA AND TA.VERSION = A.VERSION AND TA.ID_TIPO_ACTO = '0919') "
		+ "		INNER JOIN SDB_PNG_NATURALEZA_JURIDICA NJ ON (NJ.ID_NATURALEZA_JURIDICA = A.ID_NATURALEZA_JURIDICA AND NJ.VERSION = A.VERSION AND NJ.ID_GRUPO_NAT_JURIDICA @@_NOMBRE_INCLUSION_@@ (0100,0300))"
		+ "	WHERE EXISTS(SELECT 1 FROM SDB_BNG_PREDIO_SEGREGADO PS WHERE PS.ID_CIRCULO1 = A.ID_CIRCULO AND PS.ID_MATRICULA1 = A.ID_MATRICULA AND PS.ID_ANOTACION1 = A.ID_ANOTACION)"
		+ "	GROUP BY A.ID_CIRCULO, A.ID_MATRICULA,A.ID_ANOTACION, 'AGREGACION', A.FECHA_CREACION, TA.ID_TIPO_ACTO, TA.NOMBRE, A.ID_NATURALEZA_JURIDICA, A.VERSION  UNION ALL"
		+ "	SELECT A.ID_CIRCULO,A.ID_MATRICULA,MAX(NVL(A.ORDEN,A.ID_ANOTACION)) ORDEN,A.ID_ANOTACION,'SEGREGACION' AS TIPO_ANOTACION, A.FECHA_CREACION, TA.ID_TIPO_ACTO, TA.NOMBRE,A.ID_NATURALEZA_JURIDICA,A.VERSION"
		+ "	FROM  SDB_BNG_ANOTACION_PREDIO A "
		+ "		INNER JOIN SDB_PGN_TIPO_ACTO TA ON (TA.ID_TIPO_ACTO = A.ID_NATURALEZA_JURIDICA AND TA.VERSION = A.VERSION AND NVL(TA.GENERA_SEGREGACION,'S') = 'S') "
		+ "		INNER JOIN SDB_PNG_NATURALEZA_JURIDICA NJ ON (NJ.ID_NATURALEZA_JURIDICA = A.ID_NATURALEZA_JURIDICA AND NJ.VERSION = A.VERSION AND NJ.ID_GRUPO_NAT_JURIDICA @@_NOMBRE_INCLUSION_@@ (0100,0300))"
		+ "	WHERE EXISTS(SELECT 1 FROM SDB_BNG_PREDIO_SEGREGADO PS WHERE PS.ID_CIRCULO = A.ID_CIRCULO AND PS.ID_MATRICULA = A.ID_MATRICULA AND PS.ID_ANOTACION = A.ID_ANOTACION)"
		+ "	GROUP BY A.ID_CIRCULO, A.ID_MATRICULA,A.ID_ANOTACION, 'SEGREGACION', A.FECHA_CREACION, TA.ID_TIPO_ACTO, TA.NOMBRE, A.ID_NATURALEZA_JURIDICA, A.VERSION) C "
		+ " INNER JOIN SDB_PGN_CATASTROS CAS ON (CAS.ID_CATASTRO = ?)"
		+ " INNER JOIN SDB_PGN_CIRCULO_CATASTRO CC ON (CC.ID_CATASTRO = CAS.ID_CATASTRO AND C.ID_CIRCULO = CC.ID_CIRCULO AND NVL(CC.ACTIVO,'N') = 'S')"
		+ "WHERE ROWNUM <= ? AND NOT EXISTS (SELECT CAD.* FROM SDB_ACC_SERVICIO_ACTUALIZACION_HACIA_CATASTROS_DETALLE CAD "
		+ "	INNER JOIN SDB_ACC_SERVICIO_ACTUALIZACION_HACIA_CATASTROS CA ON ( CA.ID_TRANSACCION = CAD.ID_TRANSACCION AND CA.ID_TIPO_OPERACION = ?) "
		+ "	WHERE CAD.ID_CIRCULO = C.ID_CIRCULO AND CAD.ID_MATRICULA = C.ID_MATRICULA) "
		+ "GROUP BY C.ID_CIRCULO,C.ID_MATRICULA,C.ID_ANOTACION,C.TIPO_ANOTACION,C.FECHA_CREACION, C.ID_TIPO_ACTO, C.NOMBRE, C.ID_NATURALEZA_JURIDICA, C.VERSION ORDER BY C.ID_CIRCULO,C.ID_MATRICULA,C.ORDEN ASC";

	/** Constante cs_FIND_MUTACION_PRIMER_ORDEN. */
	private static final String cs_FIND_MUTACION_PRIMER_ORDEN = "SELECT A.* FROM  SDB_BNG_PROPIETARIOS P "
		+ "	INNER JOIN SDB_BNG_ANOTACION_PREDIO A ON (A.ID_CIRCULO = P.ID_CIRCULO AND A.ID_MATRICULA = P.ID_MATRICULA AND A.ID_ANOTACION = P.ID_ANOTACION) "
		+ "	INNER JOIN SDB_PGN_TIPO_ACTO TA ON (TA.ID_TIPO_ACTO = A.ID_NATURALEZA_JURIDICA AND TA.VERSION = A.VERSION) "
		+ "	INNER JOIN SDB_PNG_NATURALEZA_JURIDICA NJ ON (NJ.ID_NATURALEZA_JURIDICA = A.ID_NATURALEZA_JURIDICA AND NJ.VERSION = A.VERSION AND NJ.ID_GRUPO_NAT_JURIDICA IN (0100,0300)) "
		+ "WHERE P.ID_CIRCULO = ? AND P.ID_MATRICULA = ? ORDER BY A.ORDEN DESC";

	/** Constante cs_FIND_MUTACION_PRIMER_ORDEN_A_NOTIFICAR. */
	private static final String cs_FIND_MUTACION_PRIMER_ORDEN_A_NOTIFICAR = "SELECT A.* FROM  SDB_BNG_PROPIETARIOS P "
		+ " INNER JOIN SDB_BNG_ANOTACION_PREDIO A ON (A.ID_CIRCULO = P.ID_CIRCULO AND A.ID_MATRICULA = P.ID_MATRICULA AND A.ID_ANOTACION = P.ID_ANOTACION) "
		+ " INNER JOIN SDB_PGN_TIPO_ACTO TA ON (TA.ID_TIPO_ACTO = A.ID_NATURALEZA_JURIDICA AND TA.VERSION = A.VERSION) "
		+ " INNER JOIN SDB_PNG_NATURALEZA_JURIDICA NJ ON (NJ.ID_NATURALEZA_JURIDICA = A.ID_NATURALEZA_JURIDICA AND NJ.VERSION = A.VERSION AND NJ.ID_GRUPO_NAT_JURIDICA IN (0100,0300)) "
		+ " INNER JOIN SDB_PGN_CATASTROS C ON (C.ID_CATASTRO = ?)"
		+ " INNER JOIN SDB_PGN_CIRCULO_CATASTRO CC ON (CC.ID_CATASTRO = C.ID_CATASTRO AND A.ID_CIRCULO = CC.ID_CIRCULO AND NVL(CC.ACTIVO,'N') = 'S')"
		+ "WHERE ROWNUM <= ? AND NOT EXISTS (SELECT CAD.* FROM SDB_ACC_SERVICIO_ACTUALIZACION_HACIA_CATASTROS_DETALLE CAD "
		+ "	INNER JOIN SDB_ACC_SERVICIO_ACTUALIZACION_HACIA_CATASTROS CA ON ( CA.ID_TRANSACCION = CAD.ID_TRANSACCION AND CA.ID_TIPO_OPERACION = '1') "
		+ "WHERE CAD.ID_CIRCULO = P.ID_CIRCULO AND CAD.ID_MATRICULA = P.ID_MATRICULA) ORDER BY A.ID_CIRCULO,A.ID_MATRICULA,A.ORDEN ASC";

	/** Constante cs_FIND_BY_ID_NATURALEZA_JURIDICA. */
	private static final String cs_FIND_BY_ID_NATURALEZA_JURIDICA = "SELECT * FROM SDB_BNG_ANOTACION_PREDIO "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_NATURALEZA_JURIDICA = ?";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT * FROM SDB_BNG_ANOTACION_PREDIO "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? ";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_TIPO_RRR. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_TIPO_RRR = "SELECT AP.* FROM SDB_BNG_ANOTACION_PREDIO AP INNER JOIN SDB_PNG_NATURALEZA_JURIDICA NJ ON (NJ.ID_NATURALEZA_JURIDICA = AP.ID_NATURALEZA_JURIDICA AND NJ.VERSION = AP.VERSION) INNER JOIN SDB_COL_TIPO_RRR RRR ON (RRR.ID_TIPO_RRR = NJ.ID_TIPO_RRR) "
		+ "WHERE AP.ID_CIRCULO = ? AND AP.ID_MATRICULA = ? AND RRR.ID_TIPO_RRR = ? AND NVL(ID_ESTADO_ANOTACION,'V') = 'V'";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_MAX. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_MAX = "SELECT MAX(ID_ANOTACION) "
		+ "FROM SDB_BNG_ANOTACION_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? ";

	/** Constante cs_FIND_MUTACION_TERCER_ORDEN_A_NOTIFICAR. */
	private static final String cs_FIND_MUTACION_TERCER_ORDEN_A_NOTIFICAR = "SELECT A.* FROM SDB_BNG_ANOTACION_PREDIO A"
		+ " INNER JOIN SDB_PGN_CATASTROS C ON (C.ID_CATASTRO = ?)"
		+ " INNER JOIN SDB_PGN_CIRCULO_CATASTRO CC ON (CC.ID_CATASTRO = C.ID_CATASTRO AND A.ID_CIRCULO = CC.ID_CIRCULO AND NVL(CC.ACTIVO,'N') = 'S')"
		+ "WHERE ROWNUM <= ? AND NOT EXISTS (SELECT CAD.* FROM SDB_ACC_SERVICIO_ACTUALIZACION_HACIA_CATASTROS_DETALLE CAD "
		+ "	INNER JOIN SDB_ACC_SERVICIO_ACTUALIZACION_HACIA_CATASTROS CA ON ( CA.ID_TRANSACCION = CAD.ID_TRANSACCION AND CA.ID_TIPO_OPERACION = '4') "
		+ "WHERE CAD.ID_CIRCULO = P.ID_CIRCULO AND CAD.ID_MATRICULA = P.ID_MATRICULA) ";

	/** Constante cs_FIND_ANOTACION_PREDIO. */
	private static final String cs_FIND_ANOTACION_PREDIO = "SELECT * FROM SDB_BNG_ANOTACION_PREDIO WHERE ID_DOCUMENTO = ? AND ID_CIRCULO = ? ";

	/** Constante cs_FIND_ANOTACION_PREDIO_ANT_SISTEMA. */
	private static final String cs_FIND_ANOTACION_PREDIO_ANT_SISTEMA = "SELECT * FROM SDB_BNG_ANOTACION_PREDIO WHERE ID_DATOS_ANT_SISTEMA = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_FIND_BY_ID_ANOTACION_PREDIO. */
	private static final String cs_FIND_BY_ID_ANOTACION_PREDIO = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO WHERE ID_ANOTACION_PREDIO = ?";

	/** Constante cs_FIND_ANOTACION_PREDIO_ANT_SISTEMA_CALIFICACION. */
	private static final String cs_FIND_ANOTACION_PREDIO_ANT_SISTEMA_CALIFICACION = "SELECT * FROM SDB_BNG_ANOTACION_PREDIO WHERE ID_DATOS_ANT_SISTEMA = ? AND ID_CIRCULO = ?";

	/** Constante cs_FIND_INFO_ANOTACION. */
	private static final String cs_FIND_INFO_ANOTACION = "SELECT TDP.NOMBRE TIPO_DOCUMENTO, BD.NUMERO NUMERO_DOCUMENTO, BD.FECHA_DOCUMENTO, POO.NOMBRE OFICINA_ORIGEN, PD.NOMBRE DEPARTAMENTO_OFICINA, PM.NOMBRE MUNICIPIO_OFICINA, PNJ.ID_NATURALEZA_JURIDICA TIPO_ACTO, PNJ.NOMBRE NOMBRE_ACTO FROM SDB_BNG_ANOTACION_PREDIO BAP INNER JOIN SDB_BGN_DOCUMENTO BD ON BD.ID_DOCUMENTO = BAP.ID_DOCUMENTO LEFT JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO TDP ON TDP.ID_TIPO_DOCUMENTO = BD.ID_TIPO_DOCUMENTO LEFT JOIN SDB_PGN_OFICINA_ORIGEN POO ON POO.ID_OFICINA_ORIGEN = BD.ID_OFICINA_ORIGEN LEFT JOIN SDB_PGN_DEPARTAMENTO PD ON PD.ID_PAIS = POO.ID_PAIS AND PD.ID_DEPARTAMENTO = POO.ID_DEPARTAMENTO LEFT JOIN SDB_PGN_MUNICIPIO PM ON PM.ID_MUNICIPIO = POO.ID_MUNICIPIO AND PM.ID_DEPARTAMENTO = POO.ID_DEPARTAMENTO AND PM.ID_PAIS = POO.ID_PAIS LEFT JOIN SDB_PNG_NATURALEZA_JURIDICA PNJ ON PNJ.ID_NATURALEZA_JURIDICA = BAP.ID_NATURALEZA_JURIDICA WHERE BAP.ID_CIRCULO = ? AND BAP.ID_MATRICULA = ? AND BAP.ID_ANOTACION = ?";

	/** Constante cs_FIND_ALL_BY_RADICACION. */
	private static final String cs_FIND_ALL_BY_RADICACION = "SELECT ID_CIRCULO, ID_MATRICULA, ID_ANOTACION, RADICACION, ID_DOCUMENTO, VERSION_DOCUMENTO FROM SDB_BNG_ANOTACION_PREDIO ";

	/** Constante cs_FIND_ALL_BY_RADICACION_JOB. */
	private static final String cs_FIND_ALL_BY_RADICACION_JOB = "SELECT   DISTINCT ID_MATRICULA, ID_CIRCULO FROM SDB_BNG_ANOTACION_PREDIO  WHERE RADICACION = ? ORDER BY ID_MATRICULA ASC";

	/** Constante cs_FIND_ALL_BY_RADICACION_ALL_DATA. */
	private static final String cs_FIND_ALL_BY_RADICACION_ALL_DATA = "SELECT * FROM SDB_BNG_ANOTACION_PREDIO ";

	/** Constante cs_FIND_ALL_WITH_NATURALEZA_BY_MATRICULA. */
	private static final String cs_FIND_ALL_WITH_NATURALEZA_BY_MATRICULA = " SELECT  PNJ.ID_GRUPO_NAT_JURIDICA ,PNJ.ID_NATURALEZA_JURIDICA,PNJ.NOMBRE,BAP.ID_ANOTACION,BAP.ID_CIRCULO,BAP.ID_MATRICULA"
		+ " ,NVL (BAP.ANOTACION_CANCELADA,'N') ANOTACION_CANCELADA,PNJ.ID_DOMINIO_NAT_JUR , BAP.VERSION, BAP.ID_ESTADO_ANOTACION  FROM SDB_BNG_ANOTACION_PREDIO BAP "
		+ " INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PNJ ON PNJ.ID_NATURALEZA_JURIDICA = BAP.ID_NATURALEZA_JURIDICA AND  PNJ.VERSION = BAP.VERSION WHERE BAP.ID_CIRCULO = ? AND  BAP.ID_MATRICULA  = ?";

	/** Constante cs_FIND_ALL_BY_CIRCULO_MATRICULA_NATURALEZA. */
	private static final String cs_FIND_ALL_BY_CIRCULO_MATRICULA_NATURALEZA = "SELECT BAP.* FROM SDB_BNG_ANOTACION_PREDIO BAP "
		+ " INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PNJ ON PNJ.ID_NATURALEZA_JURIDICA = BAP.ID_NATURALEZA_JURIDICA AND  PNJ.VERSION = BAP.VERSION "
		+ "WHERE BAP.ID_CIRCULO = ? AND BAP.ID_MATRICULA = ? AND PNJ.ID_GRUPO_NAT_JURIDICA = ?";

	/** Constante cs_FIND_ALL_BY_TRANSFERENCIA_DOMINIO. */
	private static final String cs_FIND_ALL_BY_TRANSFERENCIA_DOMINIO = "  SELECT  MAX(BAP.ID_ANOTACION)ID_ANOTACION ,PNJ.ID_NATURALEZA_JURIDICA  FROM SDB_BNG_ANOTACION_PREDIO BAP "
		+ " INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PNJ ON PNJ.ID_NATURALEZA_JURIDICA = BAP.ID_NATURALEZA_JURIDICA AND  PNJ.VERSION = BAP.VERSION"
		+ " WHERE BAP.ID_CIRCULO =  ?  AND  BAP.ID_MATRICULA  = ? AND PNJ.ID_DOMINIO_NAT_JUR = 'X'  GROUP BY PNJ.ID_NATURALEZA_JURIDICA";

	/** Constante cs_FIND_DATA_PREDIO. */
	private static final String cs_FIND_DATA_PREDIO = "SELECT GNJ.ID_GRUPO_NAT_JURIDICA,ANP.ORDEN,ANP.ID_ANOTACION,ANP.FECHA_REGISTRO,ANP.ID_DOCUMENTO, ANP.RADICACION,"
		+ "	PTD.NOMBRE NOMBRE_DOC ,ATA.VERSION,ATA.NOMBRE NOMBRE_ACTO,ATA.ID_NATURALEZA_JURIDICA COD_ACTO, GNJ.NOMBRE NOMBRE_GRUPO_ACTO, NVL(ANP.VALOR,0)VALOR,NVL(ANP.COMENTARIO,' ')COMENTARIO,ANP.ID_DATOS_ANT_SISTEMA,ANP.FECHA_RADICACION"
		+ " FROM SDB_BNG_ANOTACION_PREDIO ANP  INNER JOIN SDB_BGN_DOCUMENTO SBD ON SBD.ID_DOCUMENTO = ANP.ID_DOCUMENTO AND SBD.VERSION_DOCUMENTO = ANP.VERSION_DOCUMENTO "
		+ " INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO PTD ON PTD.ID_TIPO_DOCUMENTO = SBD.ID_TIPO_DOCUMENTO "
		+ " INNER JOIN SDB_PNG_NATURALEZA_JURIDICA ATA ON ATA.ID_NATURALEZA_JURIDICA = ANP.ID_NATURALEZA_JURIDICA AND ATA.VERSION = ANP.VERSION"
		+ " INNER JOIN SDB_PNG_GRUPO_NATURALEZA_JURIDICA GNJ ON GNJ.ID_GRUPO_NAT_JURIDICA = ATA.ID_GRUPO_NAT_JURIDICA "
		+ " WHERE  ID_CIRCULO = ? AND ID_MATRICULA = ? ";

	/** Constante cs_CONSULTA_CRITERIOS. */
	private static final String cs_CONSULTA_CRITERIOS = " SELECT GNJ.ID_GRUPO_NAT_JURIDICA,ANP.ORDEN,ANP.ID_ANOTACION,ANP.FECHA_REGISTRO,ANP.ID_DOCUMENTO,"
		+ " ANP.RADICACION, PTD.NOMBRE NOMBRE_DOC ,ATA.VERSION,ATA.NOMBRE NOMBRE_ACTO,ATA.ID_NATURALEZA_JURIDICA COD_ACTO, GNJ.NOMBRE NOMBRE_GRUPO_ACTO, "
		+ " NVL(ANP.VALOR,0)VALOR,NVL(ANP.COMENTARIO,' ')COMENTARIO ,ANP.ID_DATOS_ANT_SISTEMA, ANP.FECHA_RADICACION FROM ";

	/** Constante cs_CONSULTA_PROHIBICIONES_VPM. */
	private static final String cs_CONSULTA_PROHIBICIONES_VPM = "SELECT BAP.ID_DOCUMENTO,BAP.ID_CIRCULO ID_CIRCULO_PREDIO,BAP.ID_MATRICULA ID_MATRICULA_PREDIO,BAP.ID_ANOTACION,BAP.FECHA_REGISTRO,BAP.RADICACION,PEA.NOMBRE ESTADO_ANOTACION,TO_CHAR(SBD.FECHA_DOCUMENTO,'DD/MM/YYYY')FECHA_DOCUMENTO,SBD.NUMERO,NVL(POO.NOMBRE,' ') OFICINA_ORIGEN,NVL(PBJ.ID_NATURALEZA_JURIDICA,' ') CODIGO_ACTO,NVL(PBJ.NOMBRE,' ') DESCRIPCION_ACTO " + 
			"FROM SDB_BNG_ANOTACION_PREDIO BAP " + 
			"INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PBJ ON PBJ.ID_NATURALEZA_JURIDICA = BAP.ID_NATURALEZA_JURIDICA " + 
			"INNER JOIN SDB_BGN_DOCUMENTO SBD ON SBD.ID_DOCUMENTO = BAP.ID_DOCUMENTO " + 
			"INNER JOIN SDB_PNG_ESTADO_ANOTACION PEA ON PEA.ID_ESTADO_ANOTACION = BAP.ID_ESTADO_ANOTACION " + 
			"LEFT JOIN SDB_PGN_OFICINA_ORIGEN POO ON POO.ID_OFICINA_ORIGEN = SBD.ID_OFICINA_ORIGEN " + 
			"INNER JOIN(SELECT MAX(POO.VERSION) MAX_VERSION    FROM SDB_BNG_ANOTACION_PREDIO BAP " + 
			"		INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PBJ ON PBJ.ID_NATURALEZA_JURIDICA = BAP.ID_NATURALEZA_JURIDICA " + 
			"		INNER JOIN SDB_BGN_DOCUMENTO SBD ON SBD.ID_DOCUMENTO = BAP.ID_DOCUMENTO " + 
			"		INNER JOIN SDB_PNG_ESTADO_ANOTACION PEA ON PEA.ID_ESTADO_ANOTACION = BAP.ID_ESTADO_ANOTACION " + 
			"		LEFT JOIN SDB_PGN_OFICINA_ORIGEN POO ON POO.ID_OFICINA_ORIGEN = SBD.ID_OFICINA_ORIGEN " + 
			"	WHERE BAP.ID_CIRCULO = ? AND BAP.ID_MATRICULA = ? AND NVL(BAP.ANOTACION_CANCELADA,'N') <> 'S' AND BAP.ID_ANOTACION = ?)FILTRO ON FILTRO.MAX_VERSION = POO.VERSION " + 
			"WHERE BAP.ID_CIRCULO = ? AND BAP.ID_MATRICULA = ? AND NVL(BAP.ANOTACION_CANCELADA,'N') <> 'S'AND BAP.ID_ANOTACION = ? " + 
			"ORDER BY BAP.ID_ANOTACION ASC";

	/** Constante cs_CONSULTA_CANCELACIONES. */
	private static final String cs_CONSULTA_CANCELACIONES = " SELECT COUNT(0)  CANTIDAD FROM SDB_BNG_ANOTACION_PREDIO BAP "
		+ " INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PNJ ON PNJ.ID_GRUPO_NAT_JURIDICA = '0400' AND PNJ.ID_NATURALEZA_JURIDICA = BAP.ID_NATURALEZA_JURIDICA AND PNJ.VERSION = BAP.VERSION"
		+ " WHERE  BAP.ID_CIRCULO = ?  AND BAP.ID_MATRICULA = ? ";

	/** Constante cs_CONSULTA_ADJUDICACION_EMBARGO_VIGENTE. */
	private static final String cs_CONSULTA_ADJUDICACION_EMBARGO_VIGENTE = "SELECT PGNJ.ID_GRUPO_NAT_JURIDICA FROM SDB_ACC_ANOTACION_PREDIO AAP INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PNJ ON AAP.ID_NATURALEZA_JURIDICA = PNJ.ID_NATURALEZA_JURIDICA AND PNJ.VERSION = (SELECT MAX(VERSION) FROM SDB_PNG_NATURALEZA_JURIDICA TEMP WHERE TEMP.ID_NATURALEZA_JURIDICA = AAP.ID_NATURALEZA_JURIDICA) INNER JOIN SDB_PNG_GRUPO_NATURALEZA_JURIDICA PGNJ ON PGNJ.ID_GRUPO_NAT_JURIDICA = PNJ.ID_GRUPO_NAT_JURIDICA WHERE AAP.ID_CIRCULO = ? AND AAP.ID_MATRICULA = ? AND PNJ.ACTIVO = 'S' AND AAP.ANOTACION_CANCELADA = 'S'";

	/** Constante cs_FIND_MAX_ORDEN_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_MAX_ORDEN_BY_CIRCULO_MATRICULA = "SELECT MAX(ORDEN) "
		+ "FROM SDB_BNG_ANOTACION_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/**
	 * Buscar última anotación cambio propietario.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @return el valor de anotacion predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AnotacionPredio buscarUltimaAnotacionCambioPropietario(String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		AnotacionPredio   lpr_Predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lpr_Predio     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_MUTACION_PRIMER_ORDEN);

			lps_ps.setString(li_contador++, as_idCirculo);
			lps_ps.setLong(li_contador++, al_idMatricula);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lpr_Predio = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "buscarUltimaAnotacionCambioPropietario", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lpr_Predio;
	}

	/**
	 * Buscar ultima anotacion cambio propietario A notificar.
	 *
	 * @param al_cantidadRegistros de al cantidad registros
	 * @param as_idCatastro correspondiente al valor de id catastro
	 * @return el valor de anotacion predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AnotacionPredio> buscarUltimaAnotacionCambioPropietarioANotificar(long al_cantidadRegistros, String as_idCatastro)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcpr_predios;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcpr_predios     = new ArrayList<AnotacionPredio>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_MUTACION_PRIMER_ORDEN_A_NOTIFICAR);

			lps_ps.setString(li_contador++, as_idCatastro);
			lps_ps.setLong(li_contador++, al_cantidadRegistros);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lcpr_predios.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "buscarUltimaAnotacionCambioPropietarioANotificar", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcpr_predios.isEmpty())
			lcpr_predios = null;

		return lcpr_predios;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de RegistroCalificacion filtrado por criterios.
	 *
	 * @param aocp_cp correspondiente al valor del tipo de objeto ConsultaPredio
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroCalificacion
	 */
	public Collection<RegistroCalificacion> consultaCriterios(ConsultaPredio aocp_cp)
	    throws B2BException
	{
		Collection<RegistroCalificacion> lca_data;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;
		int                              li_column;
		StringBuilder                    lsbf_sb;
		String                           ls_fromSinPersona;
		boolean                          lb_fromExist;

		li_column             = 1;
		lca_data              = new ArrayList<RegistroCalificacion>();
		lps_ps                = null;
		lrs_rs                = null;
		lsbf_sb               = new StringBuilder(cs_CONSULTA_CRITERIOS);
		ls_fromSinPersona     = "  SDB_BNG_ANOTACION_PREDIO ANP INNER JOIN SDB_BGN_DOCUMENTO SBD ON SBD.ID_DOCUMENTO = ANP.ID_DOCUMENTO AND SBD.VERSION_DOCUMENTO = ANP.VERSION_DOCUMENTO"
			+ " INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO PTD ON PTD.ID_TIPO_DOCUMENTO = SBD.ID_TIPO_DOCUMENTO"
			+ " INNER JOIN SDB_PNG_NATURALEZA_JURIDICA ATA ON ATA.ID_NATURALEZA_JURIDICA = ANP.ID_NATURALEZA_JURIDICA AND ATA.VERSION = ANP.VERSION "
			+ " INNER JOIN SDB_PNG_GRUPO_NATURALEZA_JURIDICA GNJ ON GNJ.ID_GRUPO_NAT_JURIDICA = ATA.ID_GRUPO_NAT_JURIDICA  WHERE  ANP.ID_CIRCULO = ? AND ANP.ID_MATRICULA = ? ";
		lb_fromExist          = false;

		if(aocp_cp != null)
		{
			Persona   lop_persona;
			Documento lod_documento;

			lop_persona       = aocp_cp.getPersonaCriterio();
			lod_documento     = aocp_cp.getDocumentoCriterio();

			try
			{
				if(aocp_cp.isInfoPersonaCriterio())
				{
					if(lop_persona != null)
					{
						lsbf_sb     = lsbf_sb.append(
							    "  SDB_ACC_PERSONA SAP INNER JOIN SDB_BNG_ANOTACION_PREDIO_CIUDADANO APC ON APC.ID_PERSONA = SAP.ID_PERSONA"
							    + " INNER JOIN SDB_BNG_ANOTACION_PREDIO ANP ON ANP.ID_MATRICULA = APC.ID_MATRICULA AND  ANP.ID_CIRCULO = APC.ID_CIRCULO AND ANP.ID_ANOTACION = APC.ID_ANOTACION"
							    + " INNER JOIN SDB_BGN_DOCUMENTO SBD ON SBD.ID_DOCUMENTO = ANP.ID_DOCUMENTO AND SBD.VERSION_DOCUMENTO = ANP.VERSION_DOCUMENTO "
							    + " INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO PTD ON PTD.ID_TIPO_DOCUMENTO = SBD.ID_TIPO_DOCUMENTO "
							    + " INNER JOIN SDB_PNG_NATURALEZA_JURIDICA ATA ON ATA.ID_NATURALEZA_JURIDICA = ANP.ID_NATURALEZA_JURIDICA AND ATA.VERSION = ANP.VERSION"
							    + " INNER JOIN SDB_PNG_GRUPO_NATURALEZA_JURIDICA GNJ ON GNJ.ID_GRUPO_NAT_JURIDICA = ATA.ID_GRUPO_NAT_JURIDICA  WHERE  ANP.ID_CIRCULO = ? AND ANP.ID_MATRICULA = ? "
							);

						lb_fromExist = true;

						if(lop_persona.isDocumentoValid())
							lsbf_sb = lsbf_sb.append(" AND SAP.ID_DOCUMENTO_TIPO = ?  AND SAP.NUMERO_DOCUMENTO = ? ");

						if(
						    lop_persona.isPrimerNombreValid() || lop_persona.isSegundoNombreValid()
							    || lop_persona.isPrimerApellidoValid() || lop_persona.isSegundoApellidoValid()
						)
							lsbf_sb = lsbf_sb.append(
								    " AND (UPPER(NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.SEGUNDO_APELLIDO,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.PRIMER_APELLIDO,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.SEGUNDO_APELLIDO,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.SEGUNDO_NOMBRE,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.SEGUNDO_NOMBRE,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.PRIMER_APELLIDO,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.SEGUNDO_APELLIDO,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.PRIMER_APELLIDO,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.SEGUNDO_APELLIDO,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.PRIMER_NOMBRE,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.PRIMER_NOMBRE,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.PRIMER_APELLIDO,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.SEGUNDO_APELLIDO,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.PRIMER_NOMBRE,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.SEGUNDO_APELLIDO,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.SEGUNDO_NOMBRE,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.SEGUNDO_NOMBRE,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.PRIMER_NOMBRE,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.PRIMER_NOMBRE,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.PRIMER_APELLIDO,''))LIKE UPPER(?) OR "
								    + " UPPER(NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.PRIMER_NOMBRE,''))LIKE UPPER(?) OR "
								    + " UPPER(NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.SEGUNDO_NOMBRE,''))LIKE UPPER(?) OR"
								    + " UPPER(NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.PRIMER_APELLIDO,'')||NVL(SAP.SEGUNDO_NOMBRE,''))LIKE UPPER(?) OR "
								    + " UPPER(NVL(SAP.SEGUNDO_APELLIDO,'')||NVL(SAP.PRIMER_NOMBRE,'')||NVL(SAP.SEGUNDO_NOMBRE,'')||NVL(SAP.PRIMER_APELLIDO,''))LIKE UPPER(?))"
								);

						else if(lop_persona.isRazonSocialValid())
							lsbf_sb = lsbf_sb.append(" AND SAP.RAZON_SOCIAL = ?");
					}
				}

				if(aocp_cp.isInfoDocumentoCriterio())
				{
					if(!lb_fromExist)
					{
						lsbf_sb          = lsbf_sb.append(ls_fromSinPersona);
						lb_fromExist     = true;
					}

					lsbf_sb = lsbf_sb.append(" AND ANP.ID_DOCUMENTO = ? ");
				}

				if(aocp_cp.isNaturalezaCriterio())
				{
					if(!lb_fromExist)
					{
						lb_fromExist     = true;
						lsbf_sb          = lsbf_sb.append(ls_fromSinPersona);
					}

					lsbf_sb = lsbf_sb.append(" AND ANP.ID_NATURALEZA_JURIDICA =  ? ");
				}

				if(aocp_cp.isAnotacioneCriterio())
				{
					if(!lb_fromExist)
					{
						lb_fromExist     = true;
						lsbf_sb          = lsbf_sb.append(ls_fromSinPersona);
					}

					lsbf_sb = lsbf_sb.append(" AND ANP.ID_ANOTACION >= ?   ");
				}

				if(aocp_cp.isConsultaTotal())
					lsbf_sb = lsbf_sb.append(ls_fromSinPersona);

				lsbf_sb     = lsbf_sb.append(" ORDER BY ANP.ORDEN ASC");

				lps_ps = getConnection().prepareStatement(lsbf_sb.toString());

				lps_ps.setString(li_column++, aocp_cp.getIdCirculo());
				setLong(lps_ps, aocp_cp.getIdMatricula(), li_column++);

				if(aocp_cp.isInfoPersonaCriterio())
				{
					if(lop_persona.isDocumentoValid())
					{
						lps_ps.setString(li_column++, lop_persona.getTipoDocIdentidad());
						lps_ps.setString(li_column++, lop_persona.getIdDocumentoTipo());
					}

					if(
					    lop_persona.isPrimerNombreValid() || lop_persona.isSegundoNombreValid()
						    || lop_persona.isPrimerApellidoValid() || lop_persona.isSegundoApellidoValid()
					)
					{
						String ls_nombreCompleto;

						ls_nombreCompleto = "%" + StringUtils.getStringNotNull(lop_persona.getPrimerNombre()) + "%"
							+ "%" + StringUtils.getStringNotNull(lop_persona.getSegundoNombre()) + "%" + "%"
							+ StringUtils.getStringNotNull(lop_persona.getPrimerApellido()) + "%" + "%"
							+ StringUtils.getStringNotNull(lop_persona.getSegundoApellido()) + "%";

						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
						lps_ps.setString(li_column++, ls_nombreCompleto);
					}

					else if(lop_persona.isRazonSocialValid())
						lps_ps.setString(li_column++, lop_persona.getRazonSocial());
				}

				if(aocp_cp.isInfoDocumentoCriterio())
					lps_ps.setString(li_column++, lod_documento.getIdDocumento());

				if(aocp_cp.isNaturalezaCriterio())
				{
					String[] ls_dataNaturaleza;

					if(StringUtils.isValidString(aocp_cp.getIdNaturaleza()))
					{
						ls_dataNaturaleza = aocp_cp.getIdNaturaleza().split("-");

						lps_ps.setString(li_column++, ls_dataNaturaleza[0]);
					}
				}

				if(aocp_cp.isAnotacioneCriterio())
					setLong(lps_ps, aocp_cp.getIdAnotacion(), li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_data.add(getDataAnotaciones(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultaCriterios", lse_e);

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
	 * Consulta datos registrales.
	 *
	 * @param as_idCirculo <code>String</code> de id circulo
	 * @param al_idMatricula <code>Long</code> de id matricula
	 * @return <code>Collection</code> de <code>Anotacion</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Anotacion> consultaDatosRegistrales(String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		Collection<Anotacion> lca_return;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		lca_return     = new ArrayList<Anotacion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula))
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(CS_CONSULTA_DATOS_REGISTRALES);

				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_return.add(getAnotacionDatosRegistrales(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultaDatosRegistrales", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lca_return))
			lca_return = null;

		return lca_return;
	}

	/**
	 * Consultar cancelaciones.
	 *
	 * @param aap_ap correspondiente al valor del tipo de objeto AnotacionPredio
	 * @param ab_anotacionCancelada correspondiente al valor del tipo de objeto boolean
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean consultarCancelaciones(AnotacionPredio aap_ap, boolean ab_anotacionCancelada)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;
		boolean           lb_b;

		li_column     = 1;
		lb_b          = false;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			lsb_sb.append(cs_CONSULTA_CANCELACIONES);

			if(ab_anotacionCancelada)
				lsb_sb.append(" AND BAP.ANOTACION_CANCELADA = 'S' ");
			else
				lsb_sb.append(" AND BAP.ANOTACION_CANCELADA <> 'S' ");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			lps_ps.setString(li_column++, aap_ap.getIdCirculo());
			setLong(lps_ps, aap_ap.getIdMatricula(), li_column++);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
			{
				long ll_count;

				ll_count = lrs_rs.getLong("CANTIDAD");

				if(ll_count > 0)
					lb_b = true;
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultarCancelaciones", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lb_b;
	}

	/**
	 * Método encargado de consultar los código de los actos de las anotaciones de un predio especifico.
	 *
	 * @param as_idCirculo Variable que contiene el id del circulo.
	 * @param al_idMatricula Variable que contiene el id de la matrícula.
	 * @return Mapa que contiene los códigos del acto de las anotaciones.
	 * @throws B2BException
	 */
	public Map<String, String> findActosAnotacion(String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		Map<String, String> lmss_return;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lmss_return     = new HashMap<String, String>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			int li_column;

			li_column     = 1;
			lps_ps        = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);

			lps_ps.setString(li_column++, as_idCirculo);
			setLong(lps_ps, al_idMatricula, li_column++);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lmss_return.put(lrs_rs.getString("ID_ANOTACION"), lrs_rs.getString("ID_NATURALEZA_JURIDICA"));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findActosAnotacion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lmss_return.isEmpty())
			lmss_return = null;

		return lmss_return;
	}

	/**
	 * Find all by circulo matricula naturaleza.
	 *
	 * @param ls_idCirculo de ls id circulo
	 * @param ll_idMatricula de ll id matricula
	 * @param ls_idGrupoNatJuridica de ls id grupo nat juridica
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AnotacionPredio> findAllByCirculoMatriculaNaturaleza(
	    String ls_idCirculo, long ll_idMatricula, String ls_idGrupoNatJuridica
	)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_datos;

		lcap_datos = new ArrayList<AnotacionPredio>();

		if(StringUtils.isValidString(ls_idCirculo) && StringUtils.isValidString(ls_idGrupoNatJuridica))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_CIRCULO_MATRICULA_NATURALEZA);

				lps_ps.setString(li_contador++, ls_idCirculo);
				lps_ps.setLong(li_contador++, ll_idMatricula);
				lps_ps.setString(li_contador++, ls_idGrupoNatJuridica);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByCirculoMatriculaNaturaleza", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcap_datos))
			lcap_datos = null;

		return lcap_datos;
	}

	/**
	 * Retorna el valor del objeto de tipo AnotacionPredio filtrado por el id circulo y id matricula.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto AnotacionPredio
	 * @return devuelve el valor del objeto anotacion predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio findAllByTransferenciaDominio(AnotacionPredio aap_param)
	    throws B2BException
	{
		AnotacionPredio   lcap_datos;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcap_datos     = new AnotacionPredio();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_contador;

			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_TRANSFERENCIA_DOMINIO);

			lps_ps.setString(li_contador++, aap_param.getIdCirculo());
			setLong(lps_ps, aap_param.getIdMatricula(), li_contador++);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lcap_datos = getObjetFromResultSetAnotaciones(lrs_rs, false);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllByTransferenciaDominio", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcap_datos;
	}

	/**
	 * Retorna el valor del objeto de tipo AnotacionPredio filtrado por id matricula y id anotacion.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto AnotacionPredio
	 * @return devuelve el valor del objeto anotacion predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio findAnotacionPredio(AnotacionPredio aap_param)
	    throws B2BException
	{
		AnotacionPredio   lap_anotacionPredio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		StringBuilder     lsb_sb;

		lap_anotacionPredio     = null;
		lps_ps                  = null;
		lrs_rs                  = null;
		lsb_sb                  = new StringBuilder();

		try
		{
			int li_contador;
			li_contador = 1;

			lsb_sb.append(cs_FIND_ANOTACION_PREDIO);

			Long ll_idMatricula;
			Long ll_idAnotacion;

			boolean lb_validoMatricula;
			boolean lb_validoAnotacion;

			ll_idMatricula     = aap_param.getIdMatricula();
			ll_idAnotacion     = aap_param.getIdAnotacion();

			lb_validoMatricula     = NumericUtils.isValidLong(ll_idMatricula);
			lb_validoAnotacion     = NumericUtils.isValidLong(ll_idAnotacion);

			if(lb_validoAnotacion)
				lsb_sb.append("AND ID_ANOTACION = ? ");

			if(lb_validoMatricula)
				lsb_sb.append("AND ID_MATRICULA = ?");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());
			lps_ps.setString(li_contador++, aap_param.getIdDocumento());
			lps_ps.setString(li_contador++, aap_param.getIdCirculo());

			if(lb_validoAnotacion)
				setLong(lps_ps, aap_param.getIdAnotacion(), li_contador++);

			if(lb_validoMatricula)
				setLong(lps_ps, aap_param.getIdMatricula(), li_contador++);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lap_anotacionPredio = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAnotacionPredio", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lap_anotacionPredio;
	}

	/**
	 * Find anotaciones predio.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AnotacionPredio> findAnotacionesPredio(String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcl_return;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcl_return     = new ArrayList<AnotacionPredio>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula))
			{
				int           li_column;
				StringBuilder lsb_sb;

				li_column     = 1;
				lsb_sb        = new StringBuilder(cs_FIND_BY_CIRCULO_MATRICULA);

				lsb_sb.append(" ORDER BY ID_ANOTACION ASC");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcl_return.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAnotacionesPredio", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lcl_return))
			lcl_return = null;

		return lcl_return;
	}

	/**
	 * Retorna el valor del objeto de tipo AnotacionPredio filtrado por id datos antiguo sistema, id circulo, id matricula.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto AnotacionPredio
	 * @return devuelve el valor del objeto anotacion predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio findByAntSistema(AnotacionPredio aap_param)
	    throws B2BException
	{
		AnotacionPredio   lpr_Predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lpr_Predio     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_ANOTACION_PREDIO_ANT_SISTEMA);

			lps_ps.setString(li_contador++, aap_param.getIdDatosAntSistema());
			lps_ps.setString(li_contador++, aap_param.getIdCirculo());
			setLong(lps_ps, aap_param.getIdMatricula(), li_contador++);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lpr_Predio = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByAntSistema", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lpr_Predio;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de AnotacionPredio filtrado por id circulo, id matricula.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto AnotacionPredio
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection de AnotacionPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public Collection<AnotacionPredio> findByCirculoMatricula(AnotacionPredio aap_param, boolean ab_accion)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_datos;

		lcap_datos = new ArrayList<AnotacionPredio>();

		if(aap_param != null)
		{
			String ls_idCirculo;
			Long   ls_idMatricula;

			ls_idCirculo       = aap_param.getIdCirculo();
			ls_idMatricula     = aap_param.getIdMatricula();

			lcap_datos = findByCirculoMatricula(ls_idCirculo, ls_idMatricula, ab_accion);
		}

		return lcap_datos;
	}

	/**
	 * Busca los registros asociados a un id círculo e id matrícula específicos.
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto Long
	 * @param ab_accion indica el ordenamiento del resultado de la consulta, true para ordenar de forma
	 * ascendente por el campo ORDEN, false para ordenar de forma descendente por el campo FECHA_REGISTRO
	 * @return Colección resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AnotacionPredio> findByCirculoMatricula(
	    String as_idCirculo, Long al_idMatricula, boolean ab_accion
	)
	    throws B2BException
	{
		return findByCirculoMatricula(as_idCirculo, al_idMatricula, ab_accion, null);
	}

	/**
	 * Busca los registros asociados a un id círculo e id matrícula específicos.
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto Long
	 * @param ab_accion indica el ordenamiento del resultado de la consulta, true para ordenar de forma
	 *             ascendente por el campo ORDEN, false para ordenar de forma descendente por el campo FECHA_REGISTRO
	 * @param as_tipoRRR de as tipo RRR
	 * @return Colección resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AnotacionPredio> findByCirculoMatricula(
	    String as_idCirculo, Long al_idMatricula, boolean ab_accion, String as_tipoRRR
	)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_datos;

		lcap_datos = new ArrayList<AnotacionPredio>();

		if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula))
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

				if(StringUtils.isValidString(as_tipoRRR))
				{
					lsb_query.append(cs_FIND_BY_CIRCULO_MATRICULA_TIPO_RRR);

					if(!as_tipoRRR.equalsIgnoreCase(TipoRRRCommon.DERECHOS))
						lsb_query.append(" AND NVL(ANOTACION_CANCELADA,'N') = 'N' ");
				}
				else
					lsb_query.append(cs_FIND_BY_CIRCULO_MATRICULA);

				if(ab_accion)
					lsb_query.append(" ORDER BY ORDEN ASC ");
				else
					lsb_query.append(" ORDER BY FECHA_REGISTRO DESC ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_contador++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_contador++);

				if(StringUtils.isValidString(as_tipoRRR))
					lps_ps.setString(li_contador++, as_tipoRRR);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcap_datos))
			lcap_datos = null;

		return lcap_datos;
	}

	/**
	 * Consulta los registros asociados a un id círculo e id matrícula que tengan un estado de anotación específico.
	 *
	 * @param aap_param Objeto contenedor de los filtros a utilizar en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<AnotacionPredio> findByCirculoMatriculaEstadoAnotacion(AnotacionPredio aap_param)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_datos;

		lcap_datos = new ArrayList<AnotacionPredio>();

		if(aap_param != null)
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

				lsb_query.append(cs_FIND_BY_CIRCULO_MATRICULA);

				lsb_query.append(" AND ID_ESTADO_ANOTACION = ? ORDER BY ORDEN ASC ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_contador++, aap_param.getIdCirculo());
				setLong(lps_ps, aap_param.getIdMatricula(), li_contador++);
				lps_ps.setString(li_contador++, aap_param.getIdEstadoAnotacion());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatriculaEstadoAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcap_datos))
			lcap_datos = null;

		return lcap_datos;
	}

	/**
	 * Método encargado de consultar la información de la anotación con base a sus llaves primarias.
	 *
	 * @param aap_anotacion Objeto que contiene la información de la anotación.
	 * @return Retorna el mapa con la información consultada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, Object> findByDataAnotacion(AnotacionPredio aap_anotacion)
	    throws B2BException
	{
		Map<String, Object> lmso_data;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lmso_data     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			if(aap_anotacion != null)
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_INFO_ANOTACION);

				lps_ps.setString(li_column++, aap_anotacion.getIdCirculo());
				setLong(lps_ps, aap_anotacion.getIdMatricula(), li_column++);
				setLong(lps_ps, aap_anotacion.getIdAnotacion(), li_column++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lmso_data = getMapFromResultSet(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByDataAnotacion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lmso_data;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de AnotacionPredio filtrado por id antiguo sistema, id circulo.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto AnotacionPredio
	 * @return devuelve el valor del objeto anotacion predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio findByDatosAntSistemaCalificacion(AnotacionPredio aap_param)
	    throws B2BException
	{
		AnotacionPredio   lpr_Predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lpr_Predio     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_ANOTACION_PREDIO_ANT_SISTEMA_CALIFICACION);

			lps_ps.setString(li_contador++, aap_param.getIdDatosAntSistema());
			lps_ps.setString(li_contador++, aap_param.getIdCirculo());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lpr_Predio = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByDatosAntSistemaCalificacion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lpr_Predio;
	}

	/**
	 * Retorna el valor del objeto de tipo AnotacionPredio filtrado por ID.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto AnotacionPredio
	 * @return devuelve el valor del objeto anotacion predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio findById(AnotacionPredio aap_param)
	    throws B2BException
	{
		return (aap_param != null)
		? findById(aap_param.getIdCirculo(), aap_param.getIdMatricula(), aap_param.getIdAnotacion()) : null;
	}

	/**
	 * Retorna el valor del objeto de tipo AnotacionPredio filtrado por ID.
	 *
	 * @param as_idCirculo Objeto contenedor del id círculo a utilizar como filtro en la consulta
	 * @param al_idMatricula Id de la matrícula asociada al predio
	 * @param al_idAnotacion Id de la anotación a utilizar como filtro en la busqueda
	 * @return AnotacionPredio resultante de la consulta
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public AnotacionPredio findById(String as_idCirculo, Long al_idMatricula, Long al_idAnotacion)
	    throws B2BException
	{
		AnotacionPredio lpr_Predio;

		lpr_Predio = null;

		if(
		    StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula)
			    && NumericUtils.isValidLong(al_idAnotacion)
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

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_contador++);
				setLong(lps_ps, al_idAnotacion, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpr_Predio = getObjetFromResultSet(lrs_rs);
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

		return lpr_Predio;
	}

	/**
	 * Retorna el valor del objeto de tipo AnotacionPredio filtrado por ID.
	 *
	 * @param as_idCirculo Objeto contenedor del id círculo a utilizar como filtro en la consulta
	 * @param al_idMatricula Id de la matrícula asociada al predio
	 * @param al_idAnotacion Id de la anotación a utilizar como filtro en la busqueda
	 * @return AnotacionPredio resultante de la consulta
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public AnotacionPredio findByIdAcc(String as_idCirculo, Long al_idMatricula, Long al_idAnotacion)
	    throws B2BException
	{
		AnotacionPredio lpr_Predio;

		lpr_Predio = null;

		if(
		    StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula)
			    && NumericUtils.isValidLong(al_idAnotacion)
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

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ACC);

				lps_ps.setString(li_contador++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_contador++);
				setLong(lps_ps, al_idAnotacion, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpr_Predio = getObjetFromResultSet(lrs_rs);
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

		return lpr_Predio;
	}

	/**
	 * Obtiene todos los registros que estén asociados a un documento.
	 *
	 * @param as_idDocumento id del documento
	 * @param al_versionDocumento versión del documento
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<AnotacionPredio> findByIdDocumentoVersion(String as_idDocumento, long al_versionDocumento)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_datos;
		lcap_datos = new ArrayList<AnotacionPredio>();

		if(StringUtils.isValidString(as_idDocumento) && (al_versionDocumento >= 0))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_DOCUMENTO_VERSION);

				lps_ps.setString(li_contador++, as_idDocumento);
				lps_ps.setLong(li_contador++, al_versionDocumento);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdDocumentoVersion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcap_datos))
			lcap_datos = null;

		return lcap_datos;
	}

	/**
	 * Obtiene todos los registros que estén asociados a un documento.
	 *
	 * @param as_idDocumento id del documento
	 * @param al_versionDocumento versión del documento
	 * @param as_idCirculo id del circulo registral
	 * @return <Code>Collection</Code> resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<AnotacionPredio> findByIdDocumentoVersionCirculo(
	    String as_idDocumento, long al_versionDocumento, String as_idCirculo
	)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_datos;
		lcap_datos = new ArrayList<AnotacionPredio>();

		if(
		    StringUtils.isValidString(as_idDocumento) && (al_versionDocumento >= 0)
			    && StringUtils.isValidString(as_idCirculo)
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

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_DOCUMENTO_VERSION_CIRCULO);

				lps_ps.setString(li_contador++, as_idDocumento);
				lps_ps.setLong(li_contador++, al_versionDocumento);
				lps_ps.setString(li_contador++, as_idCirculo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdDocumentoVersionCirculo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcap_datos))
			lcap_datos = null;

		return lcap_datos;
	}

	/**
	 * Retorna el valor maximo del ID.
	 *
	 * @param as_circulo correspondiente al valor del tipo de objeto String
	 * @param as_matricula correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Long findByMaxIdAnotacion(String as_circulo, String as_matricula)
	    throws B2BException
	{
		Long              ls_max;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_max     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_MAX);

			lps_ps.setString(li_contador++, as_circulo);
			lps_ps.setString(li_contador++, as_matricula);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_max = getObjetMaxFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByMaxIdAnotacion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_max;
	}

	/**
	 * Busca el máximo id de la anotación de un predio que cumpla con las caracteristicas de mutación de tercer orden.
	 *
	 * @param as_circulo id del círculo a utilizar como filtro en la consulta
	 * @param al_matricula id de la matrícula asociada al predio
	 * @param acs_codigosActos Códigos de los actos que generan cambios en predios por nuevas edificaciones, contrucciones o demoliciones
	 * @return Máximo id de anotacion que cumpla con la mutación de tercer orden
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public Long findByMutacionTercerOrden(String as_circulo, Long al_matricula, Collection<String> acs_codigosActos)
	    throws B2BException
	{
		Long ls_max;

		ls_max = null;

		if(
		    StringUtils.isValidString(as_circulo) && NumericUtils.isValidLong(al_matricula)
			    && CollectionUtils.isValidCollection(acs_codigosActos)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				int           li_contador;

				lsb_query       = new StringBuilder(cs_FIND_BY_CIRCULO_MATRICULA_MAX);
				li_contador     = 1;

				lsb_query.append(" AND ID_NATURALEZA_JURIDICA IN ( ");

				{
					Iterator<String> lis_iterador;

					lis_iterador = acs_codigosActos.iterator();

					while(lis_iterador.hasNext())
					{
						String ls_codigo;

						ls_codigo = lis_iterador.next();

						if(StringUtils.isValidString(ls_codigo))
						{
							lsb_query.append("?");

							if(lis_iterador.hasNext())
								lsb_query.append(",");
						}
					}
				}

				lsb_query.append(")");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_contador++, as_circulo);
				setLong(lps_ps, al_matricula, li_contador++);

				for(String ls_codigo : acs_codigosActos)
				{
					if(StringUtils.isValidString(ls_codigo))
						lps_ps.setString(li_contador++, ls_codigo);
				}

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_max = getObjetMaxFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByMutacionTercerOrden", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_max;
	}

	/**
	 * Encuentra todas las mutacion tercer orden A notificar.
	 *
	 * @param al_cantidadRegistros <code>long</code> de cantidad registros
	 * @param acs_codigosActos <code>Collection</code> de <code>String</code> llena de codigos actos
	 * @param as_idCatastro correspondiente al valor de id catastro
	 * @return <code>Collection</code> de {@link <code>AnotacionPredio</code>}
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AnotacionPredio> findByMutacionTercerOrdenANotificar(
	    long al_cantidadRegistros, Collection<String> acs_codigosActos, String as_idCatastro
	)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_return;

		lcap_return = new ArrayList<AnotacionPredio>();

		if(
		    (al_cantidadRegistros > NumericUtils.DEFAULT_LONG_VALUE)
			    && CollectionUtils.isValidCollection(acs_codigosActos)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				int           li_contador;

				lsb_query       = new StringBuilder(cs_FIND_MUTACION_TERCER_ORDEN_A_NOTIFICAR);
				li_contador     = 1;

				lsb_query.append(" AND A.ID_NATURALEZA_JURIDICA IN ( ");

				{
					Iterator<String> lis_iterador;

					lis_iterador = acs_codigosActos.iterator();

					while(lis_iterador.hasNext())
					{
						String ls_codigo;

						ls_codigo = lis_iterador.next();

						if(StringUtils.isValidString(ls_codigo))
						{
							lsb_query.append("?");

							if(lis_iterador.hasNext())
								lsb_query.append(",");
						}
					}
				}

				lsb_query.append(") ORDER BY A.ID_CIRCULO,A.ID_MATRICULA,A.ORDEN ASC");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_contador++, as_idCatastro);
				lps_ps.setLong(li_contador++, al_cantidadRegistros);

				for(String ls_codigo : acs_codigosActos)
				{
					if(StringUtils.isValidString(ls_codigo))
						lps_ps.setString(li_contador++, ls_codigo);
				}

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcap_return.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByMutacionTercerOrdenANotificar", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcap_return;
	}

	/**
	 * Retorna el valor del objeto de tipo AnotacionPredio filtrado por id circulo, id matricula, id naturaleza juridica.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto AnotacionPredio
	 * @return devuelve el valor del objeto anotacion predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio findByNaturalezaJuridicaPredio(AnotacionPredio aap_param)
	    throws B2BException
	{
		AnotacionPredio   lpr_Predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lpr_Predio     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_NATURALEZA_JURIDICA);

			lps_ps.setString(li_contador++, aap_param.getIdCirculo());
			setLong(lps_ps, aap_param.getIdMatricula(), li_contador++);
			lps_ps.setString(li_contador++, aap_param.getIdNaturalezaJuridica());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lpr_Predio = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByNaturalezaJuridicaPredio", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lpr_Predio;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de AnotacionPredio filtrado por radicacion.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto AnotacionPredio
	 * @return devuelve el valor del objeto collection de AnotacionPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public Collection<AnotacionPredio> findByRadicacion(AnotacionPredio aap_param)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_datos;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcap_datos     = new ArrayList<AnotacionPredio>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int           li_contador;
			StringBuilder lsb_sb;

			li_contador     = 1;
			lsb_sb          = new StringBuilder(cs_FIND_ALL_BY_RADICACION_ALL_DATA);

			lsb_sb.append(" WHERE RADICACION = ? ORDER BY  ID_CIRCULO, ID_MATRICULA, ID_ANOTACION ASC");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			lps_ps.setString(li_contador++, aap_param.getRadicacion());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcap_datos.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByRadicacion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lcap_datos))
			lcap_datos = null;

		return lcap_datos;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de AnotacionPredioDireccion filtrado por radicacion, id circulo.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto AnotacionPredioDireccion
	 * @param d_param correspondiente al valor del tipo de objeto DocumentoConstancia
	 * @return devuelve el valor del objeto collection de AnotacionPredioDireccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredioDireccion
	 */
	public Collection<AnotacionPredioDireccion> findByRadicacion(
	    AnotacionPredioDireccion aap_param, DocumentoConstancia d_param
	)
	    throws B2BException
	{
		Collection<AnotacionPredioDireccion> lcap_datos;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lcap_datos     = new ArrayList<AnotacionPredioDireccion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_contador;
			li_contador = 1;

			String  ls_radicacion;
			String  ls_idCirculo;
			String  ls_consulta;
			String  li_numero;
			boolean lb_rad;
			boolean lb_idCirculo;
			boolean lb_num;

			ls_radicacion     = aap_param.getRadicacion();
			ls_idCirculo      = aap_param.getIdCirculo();
			ls_consulta       = cs_FIND_ALL_BY_RADICACION;
			li_numero         = (d_param != null) ? d_param.getIdDocumento() : null;
			lb_rad            = true;
			lb_idCirculo      = StringUtils.isValidString(ls_idCirculo);
			lb_num            = true;

			if(StringUtils.isValidString(ls_radicacion))
			{
				ls_consulta     = ls_consulta + "WHERE RADICACION = ? ";
				lb_rad          = false;
			}
			else if(StringUtils.isValidString(li_numero))
			{
				ls_consulta     = ls_consulta + "WHERE ID_DOCUMENTO=? ";
				lb_num          = false;
			}

			if(!lb_rad)
			{
				if(StringUtils.isValidString(li_numero))
				{
					ls_consulta     = ls_consulta + "AND ID_DOCUMENTO = ? ";
					lb_num          = false;
				}
			}

			if(lb_idCirculo)
				ls_consulta = ls_consulta + " AND ID_CIRCULO = ? ";

			lps_ps = getConnection().prepareStatement(ls_consulta);

			if(!lb_rad && !lb_num)
			{
				lps_ps.setString(li_contador++, ls_radicacion);
				lps_ps.setString(li_contador++, li_numero);
			}
			else if(!lb_rad)
				lps_ps.setString(li_contador++, ls_radicacion);
			else if(!lb_num)
				lps_ps.setString(li_contador++, li_numero);

			if(lb_idCirculo)
				lps_ps.setString(li_contador++, ls_idCirculo);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcap_datos.add(getObjectFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByRadicacion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lcap_datos))
			lcap_datos = null;

		return lcap_datos;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de AnotacionPredioDireccion filtrado por radicacion.
	 *
	 * @param apd_param correspondiente al valor del tipo de objeto AnotacionPredioDireccion
	 * @return devuelve el valor del objeto collection de AnotacionPredioDireccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredioDireccion
	 */
	public Collection<AnotacionPredioDireccion> findByRadicacionJob(AnotacionPredioDireccion apd_param)
	    throws B2BException
	{
		Collection<AnotacionPredioDireccion> lcapd_datos;
		lcapd_datos = new ArrayList<AnotacionPredioDireccion>();

		if(apd_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_RADICACION_JOB);

				lps_ps.setString(li_contador++, apd_param.getRadicacion());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcapd_datos.add(getfindByRadicacionJob(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByRadicacionJob", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}

			if(!CollectionUtils.isValidCollection(lcapd_datos))
				lcapd_datos = null;
		}

		return lcapd_datos;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de RegistroCalificacion filtrado por id circulo, id matricula.
	 *
	 * @param aorc_rc correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @return devuelve el valor del objeto collection de RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroCalificacion
	 */
	public Collection<RegistroCalificacion> findDataPredio(RegistroCalificacion aorc_rc)
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

		if(aorc_rc != null)
		{
			try
			{
				StringBuilder lsb_sb;
				boolean       lb_job;
				lsb_sb     = new StringBuilder(cs_FIND_DATA_PREDIO);
				lb_job     = aorc_rc.isJob190();

				if(lb_job)
					lsb_sb.append(" AND RADICACION = ? ");

				lsb_sb.append(" ORDER BY ANP.ORDEN ASC");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, aorc_rc.getIdCirculo());
				setLong(lps_ps, aorc_rc.getIdMatricula(), li_column++);

				if(lb_job)
					lps_ps.setString(li_column++, aorc_rc.getTurno());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_data.add(getDataAnotaciones(lrs_rs));
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
	 * Consulta adjudicacion embargo vigente.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<String> consultaAdjudicacionEmbargoVigente(String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		Collection<String> lcap_datos;

		lcap_datos = new ArrayList<String>();

		if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_CONSULTA_ADJUDICACION_EMBARGO_VIGENTE);

				lps_ps.setString(li_contador++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_datos.add(lrs_rs.getString("ID_GRUPO_NAT_JURIDICA"));
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultaAdjudicacionEmbargoVigente", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcap_datos))
			lcap_datos = null;

		return lcap_datos;
	}

	/**
	 * Metodo encargado de consultar el orden máximo de las anotaciones que coincidan con los criterios de búsqueda.
	 *
	 * @param as_circulo Argumento de tipo <code>String</code> que contiene el circulo para realizar la búsqueda.
	 * @param as_matricula Argumento de tipo <code>String</code> que contiene la matricula para realizar la búsqueda.
	 * @return Objeto de tipo <code>Long</code> que contiene el resultado de la búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Long findMaxOrdenByCirculoMatricula(String as_circulo, String as_matricula)
	    throws B2BException
	{
		Long              ll_max;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ll_max     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_MAX_ORDEN_BY_CIRCULO_MATRICULA);

			lps_ps.setString(li_contador++, as_circulo);
			lps_ps.setString(li_contador++, as_matricula);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ll_max = getLong(lrs_rs, 1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findMaxOrdenByCirculoMatricula", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ll_max;
	}

	/**
	 * Retorna el valor del objeto de tipo AnotacionPredio filtrado por id anotacion predio
	 *
	 * @param as_param correspondiente al valor de tipo string del id de anotacion predio
	 * @return devuelve el valor del objeto anotacion predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio findByIdAnotacioPredio(String as_param)
	    throws B2BException
	{
		AnotacionPredio   lpr_Predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lpr_Predio     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(StringUtils.isValidString(as_param))
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ANOTACION_PREDIO);

				lps_ps.setString(li_contador++, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpr_Predio = getObjetFromResultSetAnotacion(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdAnotacioPredio", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lpr_Predio;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de AnotacionPredio filtrado por el id circulo y id naturaleza juridica.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto AnotacionPredio
	 * @return devuelve el valor del objeto collection de AnotacionPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public Collection<AnotacionPredio> findNaturalezaByMatricula(AnotacionPredio aap_param)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_datos;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcap_datos     = new ArrayList<AnotacionPredio>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(aap_param != null)
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_WITH_NATURALEZA_BY_MATRICULA);

				lps_ps.setString(li_contador++, aap_param.getIdCirculo());
				setLong(lps_ps, aap_param.getIdMatricula(), li_contador++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_datos.add(getObjetFromResultSetAnotaciones(lrs_rs, true));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findNaturalezaByMatricula", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lcap_datos))
			lcap_datos = null;

		return lcap_datos;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de ProhibicionVPM.
	 *
	 * @param apv_pv Objeto de tipo ProhibicionVPM del cual se extraerán los datos necesarios para realizar la consulta a la BD.
	 * @return Collection<ProhibicionVPM> llena con los datos que se encuentran en la BD.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ProhibicionVPM
	 */
	public Collection<ProhibicionVPM> findProhibicionesVPM(ProhibicionVPM apv_pv)
	    throws B2BException
	{
		Collection<ProhibicionVPM> lcpv_datos;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcpv_datos     = new ArrayList<ProhibicionVPM>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_CONSULTA_PROHIBICIONES_VPM);

			lps_ps.setString(li_contador++, apv_pv.getIdCirculoPredio());
			lps_ps.setString(li_contador++, apv_pv.getIdMatriculaPredio());
			lps_ps.setLong(li_contador++, apv_pv.getIdAnotacion());
			lps_ps.setString(li_contador++, apv_pv.getIdCirculoPredio());
			lps_ps.setString(li_contador++, apv_pv.getIdMatriculaPredio());
			lps_ps.setLong(li_contador++, apv_pv.getIdAnotacion());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpv_datos.add(getObjetFromResultSetProhibicionVPM(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findProhibicionesVPM", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lcpv_datos))
			lcpv_datos = null;

		return lcpv_datos;
	}

	/**
	 * Find segregacion agregacion predio.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @param ab_actosTransferencia de ab actos transferencia
	 * @return el valor de anotacion catastro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AnotacionCatastro findSegregacionAgregacionPredio(
	    String as_idCirculo, Long al_idMatricula, boolean ab_actosTransferencia
	)
	    throws B2BException
	{
		AnotacionCatastro lcl_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcl_return     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula))
			{
				int    li_column;
				String ls_query;

				li_column     = 1;
				ls_query      = cs_FIND_SEGREGACION_AGREGACION_BY_ID_CIRCULO_MATRICULA;
				ls_query      = (ab_actosTransferencia) ? ls_query.replace(ConstantesDao.NOMBRE_INCLUSION, "IN")
					                                    : ls_query.replace(ConstantesDao.NOMBRE_INCLUSION, "NOT IN");

				lps_ps = getConnection().prepareStatement(ls_query);

				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcl_return = getAnotacionCatastro(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSegregacionAgregacionPredioSinCambioPropietario", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcl_return;
	}

	/**
	 * Find segregacion agregacion predio A notificar.
	 *
	 * @param al_cantidadRegistros de cantidad registros a consultar
	 * @param ab_actosTransferencia de actos transferencia
	 * @param as_idCatastro correspondiente al valor de id catastro
	 * @return el valor de anotacion catastro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AnotacionPredio> findSegregacionAgregacionPredioANotificar(
	    long al_cantidadRegistros, boolean ab_actosTransferencia, String as_idCatastro
	)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_return;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcap_return     = new ArrayList<AnotacionPredio>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(al_cantidadRegistros > NumericUtils.DEFAULT_LONG_VALUE)
			{
				int    li_column;
				String ls_tipoOperacion;
				String ls_query;

				li_column            = 1;
				ls_query             = cs_FIND_SEGREGACION_AGREGACION_SOCP_A_NOTIFICAR;
				ls_query             = (ab_actosTransferencia) ? ls_query.replace(ConstantesDao.NOMBRE_INCLUSION, "IN")
					                                           : ls_query.replace(
					    ConstantesDao.NOMBRE_INCLUSION, "NOT IN"
					);
				ls_tipoOperacion     = (ab_actosTransferencia)
					? TipoOperacionCommon.CONSULTA_SEGREGACION_CON_CAMBIO_PROPIETARIO
					: TipoOperacionCommon.CONSULTA_SEGREGACION_SIN_CAMBIO_PROPIETARIO;
				lps_ps               = getConnection().prepareStatement(ls_query);

				lps_ps.setString(li_column++, as_idCatastro);
				lps_ps.setLong(li_column++, al_cantidadRegistros);
				lps_ps.setString(li_column++, ls_tipoOperacion);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_return.add(new AnotacionPredio(getAnotacionCatastro(lrs_rs, true)));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSegregacionAgregacionPredioANotificar", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcap_return;
	}

	/**
	 * Retorna Objeto o variable de valor anotacion catastro.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de anotacion catastro
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private AnotacionCatastro getAnotacionCatastro(ResultSet ars_rs)
	    throws SQLException
	{
		return getAnotacionCatastro(ars_rs, false);
	}

	/**
	 * Retorna Objeto o variable de valor anotacion catastro.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de anotacion catastro
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private AnotacionCatastro getAnotacionCatastro(ResultSet ars_rs, boolean ab_camposNaturalezaJuridica)
	    throws SQLException
	{
		AnotacionCatastro lac_ac;

		lac_ac = new AnotacionCatastro();

		lac_ac.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lac_ac.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lac_ac.setOrden(ars_rs.getString("ORDEN"));
		lac_ac.setNumAnotacion(ars_rs.getString("ID_ANOTACION"));
		lac_ac.setTipoAnotacion(ars_rs.getString("TIPO_ANOTACION"));
		lac_ac.setCodigoActo(ars_rs.getString("ID_TIPO_ACTO"));
		lac_ac.setNombreActo(ars_rs.getString("NOMBRE"));

		if(ab_camposNaturalezaJuridica)
		{
			lac_ac.setCodNaturalezaJuridicaFolio(ars_rs.getString("ID_NATURALEZA_JURIDICA"));
			lac_ac.setVersionCodNaturalezaJuridicaFolio(ars_rs.getLong("VERSION"));
		}

		return lac_ac;
	}

	private Anotacion getAnotacionDatosRegistrales(ResultSet ars_rs)
	    throws SQLException
	{
		Anotacion la_anotacion;

		la_anotacion = new Anotacion();

		la_anotacion.setConsecutivoAnotacion(ars_rs.getString("CONSECUTIVO_ANOTACION"));
		la_anotacion.setTipoDocumentoAnotacion(ars_rs.getString("TIPO_DOCUMENTO_ANOTACION"));
		la_anotacion.setActoJuridico(ars_rs.getString("ACTO_JURIDICO"));
		la_anotacion.setDrr(ars_rs.getString("DRR"));
		la_anotacion.setValorActo(ars_rs.getString("VALOR_ACTO"));
		la_anotacion.setFechaAnotacion(ars_rs.getString("FECHA_ANOTACION"));

		return la_anotacion;
	}

	/**
	 * Retorna el valor de RegistroCalificacion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de data anotaciones
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see RegistroCalificacion
	 */
	private RegistroCalificacion getDataAnotaciones(ResultSet ars_rs)
	    throws SQLException
	{
		RegistroCalificacion lorc_datos;
		lorc_datos = new RegistroCalificacion();

		lorc_datos.setIdNaturalezJuridica(ars_rs.getString("ID_GRUPO_NAT_JURIDICA"));
		lorc_datos.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		lorc_datos.setOrden(getLong(ars_rs, "ORDEN"));
		lorc_datos.setFechaRegistro(ars_rs.getTimestamp("FECHA_REGISTRO"));
		lorc_datos.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		lorc_datos.setRadicacion(ars_rs.getString("RADICACION"));
		lorc_datos.setNombreDoc(ars_rs.getString("NOMBRE_DOC"));
		lorc_datos.setVersion(getLong(ars_rs, "VERSION"));
		lorc_datos.setNombreActo(ars_rs.getString("NOMBRE_ACTO"));
		lorc_datos.setCodActo(ars_rs.getString("COD_ACTO"));
		lorc_datos.setNombreGrupoActo(ars_rs.getString("NOMBRE_GRUPO_ACTO"));
		lorc_datos.setValor(ars_rs.getBigDecimal("VALOR"));
		lorc_datos.setComentario(ars_rs.getString("COMENTARIO"));
		lorc_datos.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		lorc_datos.setFechaRadicacion(ars_rs.getTimestamp("FECHA_RADICACION"));

		return lorc_datos;
	}

	/**
	 * Retorna Objeto o variable de valor map from result set.
	 *
	 * @param lrs_rs de lrs rs
	 * @return el valor de map from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private Map<String, Object> getMapFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		Map<String, Object> lmso_return;

		lmso_return = new HashMap<String, Object>();

		lmso_return.put(IdentificadoresCommon.TIPO_DOCUMENTO, lrs_rs.getString(IdentificadoresCommon.TIPO_DOCUMENTO));
		lmso_return.put(
		    IdentificadoresCommon.NUMERO_DOCUMENTO, lrs_rs.getString(IdentificadoresCommon.NUMERO_DOCUMENTO)
		);
		lmso_return.put(IdentificadoresCommon.FECHA_DOCUMENTO, lrs_rs.getDate(IdentificadoresCommon.FECHA_DOCUMENTO));
		lmso_return.put(IdentificadoresCommon.OFICINA_ORIGEN, lrs_rs.getString(IdentificadoresCommon.OFICINA_ORIGEN));
		lmso_return.put(
		    IdentificadoresCommon.MUNICIPIO_OFICINA, lrs_rs.getString(IdentificadoresCommon.MUNICIPIO_OFICINA)
		);
		lmso_return.put(
		    IdentificadoresCommon.DEPARTAMENTO_OFICINA, lrs_rs.getString(IdentificadoresCommon.DEPARTAMENTO_OFICINA)
		);
		lmso_return.put(IdentificadoresCommon.TIPO_ACTO, lrs_rs.getString(IdentificadoresCommon.TIPO_ACTO));
		lmso_return.put(IdentificadoresCommon.NOMBRE_ACTO, lrs_rs.getString(IdentificadoresCommon.NOMBRE_ACTO));

		return lmso_return;
	}

	/**
	 * Retorna el valor de AnotacionPredioDireccion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de AnotacionPredioDireccion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see AnotacionPredioDireccion
	 */
	private AnotacionPredioDireccion getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AnotacionPredioDireccion lap_ap;

		lap_ap = new AnotacionPredioDireccion();

		lap_ap.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lap_ap.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lap_ap.setIdAnotacion(ars_rs.getLong("ID_ANOTACION"));
		lap_ap.setRadicacion(ars_rs.getString("RADICACION"));
		lap_ap.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		lap_ap.setVersionDocumento(ars_rs.getLong("VERSION_DOCUMENTO"));

		return lap_ap;
	}

	/**
	 * Retorna el valor de AnotacionPredio.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de AnotacionPredioDireccion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see AnotacionPredioDireccion
	 */
	private AnotacionPredio getObjetFromResultSetAnotacion(ResultSet ars_rs)
	    throws SQLException
	{
		AnotacionPredio lap_ap;

		lap_ap = new AnotacionPredio();

		lap_ap.setIdCirculo(ars_rs.getString("ID_ANOTACION_PREDIO"));
		lap_ap.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lap_ap.setIdTurnoHistoria(ars_rs.getString("ID_TURNO_HISTORIA"));
		lap_ap.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		lap_ap.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		lap_ap.setComentario(ars_rs.getString("COMENTARIO"));
		lap_ap.setFechaRegistro(ars_rs.getTimestamp("FECHA_REGISTRO"));
		lap_ap.setValor(getBigDecimal(ars_rs, "VALOR"));
		lap_ap.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		lap_ap.setIdNaturalezaJuridica(ars_rs.getString("ID_NATURALEZA_JURIDICA"));
		lap_ap.setVersion(ars_rs.getLong("VERSION"));
		lap_ap.setIdTipoAnotacion(ars_rs.getString("ID_TIPO_ANOTACION"));
		lap_ap.setFechaRadicacion(ars_rs.getTimestamp("FECHA_RADICACION"));
		lap_ap.setRadicacion(ars_rs.getString("RADICACION"));
		lap_ap.setIdEstadoAnotacion(ars_rs.getString("ID_ESTADO_ANOTACION"));
		lap_ap.setOrden(getBigDecimal(ars_rs, "ORDEN"));
		lap_ap.setEspecificacion(ars_rs.getString("ESPECIFICACION"));
		lap_ap.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		lap_ap.setIdDetalleAntSistema(ars_rs.getString("ID_DETALLE_ANT_SISTEMA"));
		lap_ap.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lap_ap.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lap_ap.setVersionDocumento(ars_rs.getLong("VERSION_DOCUMENTO"));
		lap_ap.setAnotacionCancelada(ars_rs.getString("ANOTACION_CANCELADA"));

		return lap_ap;
	}

	/**
	 * Retorna el valor de AnotacionPredioDireccion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de AnotacionPredioDireccion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see AnotacionPredioDireccion
	 */
	private AnotacionPredio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AnotacionPredio lap_ap;

		lap_ap = new AnotacionPredio();

		lap_ap.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lap_ap.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		lap_ap.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		lap_ap.setComentario(ars_rs.getString("COMENTARIO"));
		lap_ap.setFechaRegistro(ars_rs.getTimestamp("FECHA_REGISTRO"));
		lap_ap.setValor(getBigDecimal(ars_rs, "VALOR"));
		lap_ap.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		lap_ap.setIdNaturalezaJuridica(ars_rs.getString("ID_NATURALEZA_JURIDICA"));
		lap_ap.setVersion(ars_rs.getLong("VERSION"));
		lap_ap.setIdTipoAnotacion(ars_rs.getString("ID_TIPO_ANOTACION"));
		lap_ap.setFechaRadicacion(ars_rs.getTimestamp("FECHA_RADICACION"));
		lap_ap.setRadicacion(ars_rs.getString("RADICACION"));
		lap_ap.setIdEstadoAnotacion(ars_rs.getString("ID_ESTADO_ANOTACION"));
		lap_ap.setOrden(getBigDecimal(ars_rs, "ORDEN"));
		lap_ap.setEspecificacion(ars_rs.getString("ESPECIFICACION"));
		lap_ap.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		lap_ap.setIdDetalleAntSistema(ars_rs.getString("ID_DETALLE_ANT_SISTEMA"));
		lap_ap.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lap_ap.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lap_ap.setVersionDocumento(ars_rs.getLong("VERSION_DOCUMENTO"));
		lap_ap.setAnotacionCancelada(ars_rs.getString("ANOTACION_CANCELADA"));

		return lap_ap;
	}

	/**
	 * Retorna el valor de AnotacionPredioDireccion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_b correspondiente al valor del tipo de objeto boolean, falso para incluir los campos de
	 * ID_NATURALEZA_JURIDICA y ID_ANOTACION
	 * @return el valor de AnotacionPredioDireccion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see AnotacionPredioDireccion
	 */
	private AnotacionPredio getObjetFromResultSetAnotaciones(ResultSet ars_rs, boolean ab_b)
	    throws SQLException
	{
		AnotacionPredio lap_ap;

		lap_ap = new AnotacionPredio();

		if(ab_b)
		{
			lap_ap.setGrupoNaturaleza(ars_rs.getString("ID_GRUPO_NAT_JURIDICA"));
			lap_ap.setIdNaturalezaJuridica(ars_rs.getString("ID_NATURALEZA_JURIDICA"));
			lap_ap.setNombreNaturaleza(ars_rs.getString("NOMBRE"));
			lap_ap.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
			lap_ap.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
			lap_ap.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
			lap_ap.setDominioNaturaleza(ars_rs.getString("ID_DOMINIO_NAT_JUR"));
			lap_ap.setAnotacionCancelada(ars_rs.getString("ANOTACION_CANCELADA"));
			lap_ap.setVersion(ars_rs.getLong("VERSION"));
			lap_ap.setIdEstadoAnotacion(ars_rs.getString("ID_ESTADO_ANOTACION"));
		}
		else
		{
			lap_ap.setIdNaturalezaJuridica(ars_rs.getString("ID_NATURALEZA_JURIDICA"));
			lap_ap.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		}

		return lap_ap;
	}

	/**
	 * Retorna el valor de ProhibicionVPM.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de ProhibicionVPM
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see ProhibicionVPM
	 */
	private ProhibicionVPM getObjetFromResultSetProhibicionVPM(ResultSet ars_rs)
	    throws SQLException
	{
		ProhibicionVPM lap_ap;

		lap_ap = new ProhibicionVPM();

		lap_ap.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		lap_ap.setIdCirculoPredio(ars_rs.getString("ID_CIRCULO_PREDIO"));
		lap_ap.setIdMatriculaPredio(ars_rs.getString("ID_MATRICULA_PREDIO"));
		lap_ap.setIdAnotacion(ars_rs.getLong("ID_ANOTACION"));
		lap_ap.setFechaRegistro(ars_rs.getTimestamp("FECHA_REGISTRO"));
		lap_ap.setRadicacion(ars_rs.getString("RADICACION"));
		lap_ap.setEstadoAnotacion(ars_rs.getString("ESTADO_ANOTACION"));
		lap_ap.setFechaDocumento(ars_rs.getString("FECHA_DOCUMENTO"));
		lap_ap.setNumero(ars_rs.getString("NUMERO"));
		lap_ap.setOficinaOrigen(ars_rs.getString("OFICINA_ORIGEN"));
		lap_ap.setCodigoActo(ars_rs.getString("CODIGO_ACTO"));
		lap_ap.setDescripcionActo(ars_rs.getString("DESCRIPCION_ACTO"));

		return lap_ap;
	}

	/**
	 * Retorna el valor maximo del id anotacion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor maximo de id anotacion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Long getObjetMaxFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Long lap_ap;

		lap_ap = getLong(ars_rs, "MAX(ID_ANOTACION)");

		return lap_ap;
	}

	/**
	 * Retorna el valor de AnotacionPredioDireccion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de AnotacionPredioDireccion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see AnotacionPredioDireccion
	 */
	private AnotacionPredioDireccion getfindByRadicacionJob(ResultSet ars_rs)
	    throws SQLException
	{
		AnotacionPredioDireccion lapd_apd;

		lapd_apd = new AnotacionPredioDireccion();

		lapd_apd.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lapd_apd.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));

		return lapd_apd;
	}
}
