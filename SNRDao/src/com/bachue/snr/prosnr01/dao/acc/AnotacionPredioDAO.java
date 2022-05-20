package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess.oracle.ClobUtils;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioDireccion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase para poder realizar transacciones con la base de datos en la tabla SDB_ACC_ANOTACION_PREDIO.
 *
 * @author Heiner Castañeda
 */
public class AnotacionPredioDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO "
		+ "WHERE ID_ANOTACION_PREDIO = ?";

	/** Constante cs_FIND_ACTO_BY_ANOTACION. */
	private static final String cs_FIND_ACTO_BY_ANOTACION = "SELECT  ANP.ID_ANOTACION_PREDIO,ATA.VERSION,ATA.NOMBRE NOMBRE_ACTO,ATA.ID_NATURALEZA_JURIDICA COD_ACTO "
		+ " FROM SDB_ACC_ANOTACION_PREDIO ANP  INNER JOIN SDB_BGN_DOCUMENTO SBD ON SBD.ID_DOCUMENTO = ANP.ID_DOCUMENTO AND SBD.VERSION_DOCUMENTO = ANP.VERSION_DOCUMENTO "
		+ " INNER JOIN SDB_PNG_NATURALEZA_JURIDICA ATA ON ATA.ID_NATURALEZA_JURIDICA = ANP.ID_NATURALEZA_JURIDICA AND ATA.VERSION = ANP.VERSION  WHERE ANP.ID_ANOTACION_PREDIO = ?";

	/** Constante cs_FIND_BY_TURNO_HISTORIA. */
	private static final String cs_FIND_BY_TURNO_HISTORIA = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO WHERE ID_TURNO_HISTORIA = ?";

	/** Constante cs_FIND_BY_TURNO_HISTORIA_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_TURNO_HISTORIA_CIRCULO_MATRICULA = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO WHERE ID_TURNO_HISTORIA = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_FIND_BY_TURNO. */
	private static final String cs_FIND_BY_TURNO = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO WHERE ID_TURNO = ?";

	/** Constante cs_FIND_BY_TURNO_MATRICULA. */
	private static final String cs_FIND_BY_TURNO_MATRICULA = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO WHERE ID_TURNO = ?  AND ID_CIRCULO = ? AND ID_MATRICULA = ?  AND NVL(INDICADOR_CREAR_PREDIO_CIUDADANO ,'N') <> 'S' ";

	/** Constante cs_DELETE_BY_ID_TURNO_HISTORIA. */
	private static final String cs_DELETE_BY_ID_TURNO_HISTORIA = " DELETE FROM SDB_ACC_ANOTACION_PREDIO_CIUDADANO  WHERE ID_TURNO_HISTORIA = ? ";

	/** Constante cs_DELETE_BY_ID. */
	private static final String cs_DELETE_BY_ID = "DELETE FROM SDB_ACC_ANOTACION_PREDIO "
		+ "WHERE ID_ANOTACION_PREDIO = ?";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_MAX. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_MAX = "SELECT MAX(ID_ANOTACION) FROM SDB_ACC_ANOTACION_PREDIO "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_TURNO_MAX. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_TURNO_MAX = "SELECT MAX(ID_ANOTACION) FROM SDB_ACC_ANOTACION_PREDIO "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO = ?";

	/** Constante cs_FIND_MAX_ORDEN_BY_CIRCULO_MATRICULA_TURNO_MAX. */
	private static final String cs_FIND_MAX_ORDEN_BY_CIRCULO_MATRICULA_TURNO_MAX = "SELECT MAX(ORDEN) FROM SDB_ACC_ANOTACION_PREDIO "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO = ?";

	/** Constante cs_FIND_ANOTACIONES_INACTIVAS_BY_ID_TURNO. */
	private static final String cs_FIND_ANOTACIONES_INACTIVAS_BY_ID_TURNO = "SELECT ANP.* FROM SDB_ACC_ANOTACION_PREDIO ANP "
		+ "INNER JOIN SDB_BGN_DOCUMENTO SBD ON SBD.ID_DOCUMENTO = ANP.ID_DOCUMENTO AND SBD.VERSION_DOCUMENTO = ANP.VERSION_DOCUMENTO "
		+ "INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO PTD ON PTD.ID_TIPO_DOCUMENTO = SBD.ID_TIPO_DOCUMENTO "
		+ "INNER JOIN SDB_PNG_NATURALEZA_JURIDICA ATA ON ATA.ID_NATURALEZA_JURIDICA = ANP.ID_NATURALEZA_JURIDICA AND ATA.VERSION = ANP.VERSION "
		+ "INNER JOIN SDB_PNG_GRUPO_NATURALEZA_JURIDICA GNJ ON GNJ.ID_GRUPO_NAT_JURIDICA = ATA.ID_GRUPO_NAT_JURIDICA "
		+ "INNER JOIN SDB_ACC_TURNO_HISTORIA TH ON ANP.ID_TURNO_HISTORIA = TH.ID_TURNO_HISTORIA "
		+ "WHERE ANP.ACTIVO = 'N' AND TH.ID_TURNO = ?  ORDER BY ANP.ID_ANOTACION ASC";

	/** Constante cs_FIND_ANOTACIONES_INACTIVAS_BY_ID_TURNO. */
	private static final String cs_FIND_ANOTACIONES_INACTIVAS_BY_ID_TURNO_AND_INDICADOR_PREDIO_CIUDADANO_S = "SELECT ANP.* FROM SDB_ACC_ANOTACION_PREDIO ANP "
		+ "INNER JOIN SDB_BGN_DOCUMENTO SBD ON SBD.ID_DOCUMENTO = ANP.ID_DOCUMENTO AND SBD.VERSION_DOCUMENTO = ANP.VERSION_DOCUMENTO "
		+ "INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO PTD ON PTD.ID_TIPO_DOCUMENTO = SBD.ID_TIPO_DOCUMENTO "
		+ "INNER JOIN SDB_PNG_NATURALEZA_JURIDICA ATA ON ATA.ID_NATURALEZA_JURIDICA = ANP.ID_NATURALEZA_JURIDICA AND ATA.VERSION = ANP.VERSION "
		+ "INNER JOIN SDB_PNG_GRUPO_NATURALEZA_JURIDICA GNJ ON GNJ.ID_GRUPO_NAT_JURIDICA = ATA.ID_GRUPO_NAT_JURIDICA "
		+ "INNER JOIN SDB_ACC_TURNO_HISTORIA TH ON ANP.ID_TURNO_HISTORIA = TH.ID_TURNO_HISTORIA "
		+ "WHERE ANP.ACTIVO = 'N' AND ANP.INDICADOR_CREAR_PREDIO_CIUDADANO = 'S' AND TH.ID_TURNO = ?  ORDER BY ANP.ID_ANOTACION ASC";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? ORDER BY ID_ANOTACION ASC";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_TURNO. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_TURNO = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO = ? ORDER BY ID_ANOTACION ASC";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_TURNO_ANOTACION. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_TURNO_ANOTACION = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO = ? AND ID_ANOTACION = ? ORDER BY ID_ANOTACION ASC";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_ACTIVE. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_ACTIVE = "SELECT ANP.* FROM SDB_ACC_ANOTACION_PREDIO ANP "
		+ "INNER JOIN SDB_BGN_DOCUMENTO SBD ON SBD.ID_DOCUMENTO = ANP.ID_DOCUMENTO AND SBD.VERSION_DOCUMENTO = ANP.VERSION_DOCUMENTO "
		+ "INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO PTD ON PTD.ID_TIPO_DOCUMENTO = SBD.ID_TIPO_DOCUMENTO "
		+ "INNER JOIN SDB_PNG_NATURALEZA_JURIDICA ATA ON ATA.ID_NATURALEZA_JURIDICA = ANP.ID_NATURALEZA_JURIDICA AND ATA.VERSION = ANP.VERSION "
		+ "INNER JOIN SDB_PNG_GRUPO_NATURALEZA_JURIDICA GNJ ON GNJ.ID_GRUPO_NAT_JURIDICA = ATA.ID_GRUPO_NAT_JURIDICA "
		+ "WHERE ANP.ACTIVO = 'S' AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO_HISTORIA = ? "
		+ "ORDER BY ANP.ID_ANOTACION ASC";

	/** Constante cs_FIND_ANOTACIONES_INACTIVAS_BY_ID_SOLICITUD. */
	private static final String cs_FIND_ANOTACIONES_INACTIVAS_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO WHERE ACTIVO = 'N' AND NVL(INDICADOR_CREAR_PREDIO_CIUDADANO,'N') = 'S' AND ID_SOLICITUD = ? ORDER BY ID_ANOTACION ASC";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_ANOTACION. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_ANOTACION = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ?";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_ACTO. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_ACTO = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_NATURALEZA_JURIDICA = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_ANOTACION_PREDIO_ID_ANOTACION_PREDIO.CURRVAL FROM DUAL";

	/** Constante cs_FIND_ALL_WITH_NATURALEZA_BY_MATRICULA. */
	private static final String cs_FIND_ALL_WITH_NATURALEZA_BY_MATRICULA = "SELECT  PNJ.ID_GRUPO_NAT_JURIDICA ,PNJ.ID_NATURALEZA_JURIDICA,PNJ.NOMBRE,AAP.ID_ANOTACION,AAP.ID_CIRCULO,AAP.ID_MATRICULA ,NVL (AAP.ANOTACION_CANCELADA,'N') ANOTACION_CANCELADA,PNJ.ID_DOMINIO_NAT_JUR , AAP.VERSION  FROM SDB_ACC_ANOTACION_PREDIO AAP  INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PNJ ON PNJ.ID_NATURALEZA_JURIDICA = AAP.ID_NATURALEZA_JURIDICA AND  PNJ.VERSION = AAP.VERSION WHERE AAP.ID_CIRCULO = ? AND  AAP.ID_MATRICULA  = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_ANOTACION_PREDIO (ID_TURNO_HISTORIA,"
		+ "ID_ESTADO_REGISTRO,ID_CIRCULO,ID_MATRICULA,ID_SOLICITUD,ID_ANOTACION,COMENTARIO,FECHA_REGISTRO,"
		+ "VALOR,ID_DOCUMENTO,ID_NATURALEZA_JURIDICA,VERSION,ID_TIPO_ANOTACION,FECHA_RADICACION,"
		+ "RADICACION,ID_ESTADO_ANOTACION,ORDEN,ESPECIFICACION,ID_DATOS_ANT_SISTEMA,ID_DETALLE_ANT_SISTEMA,"
		+ "VERSION_DOCUMENTO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,REVISION_CALIFICADOR,ID_TURNO,ANOTACION_CANCELADA,ACTIVO) "
		+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?,?,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = " UPDATE SDB_ACC_ANOTACION_PREDIO SET FECHA_RADICACION = ? ,RADICACION = ? ,ID_ESTADO_ANOTACION = ?,"
		+ " ID_NATURALEZA_JURIDICA = ? ,ESPECIFICACION = ? ,VALOR = ? , COMENTARIO = ?,ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=?  WHERE ID_ANOTACION_PREDIO = ?";

	/** Constante cs_UPDATE_ANOTACION. */
	private static final String cs_UPDATE_ANOTACION = " UPDATE SDB_ACC_ANOTACION_PREDIO SET ID_TURNO_HISTORIA=?,ID_ESTADO_REGISTRO=?,"
		+ "COMENTARIO=?,FECHA_REGISTRO=?,VALOR=?,ID_DOCUMENTO=?,ID_NATURALEZA_JURIDICA=?,VERSION=?,ID_TIPO_ANOTACION=?,FECHA_RADICACION=?,"
		+ "RADICACION=?,ID_ESTADO_ANOTACION=?,ORDEN=?,ESPECIFICACION=?,ID_DATOS_ANT_SISTEMA=?,ID_DETALLE_ANT_SISTEMA=?,VERSION_DOCUMENTO=?,"
		+ "ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=?,REVISION_CALIFICADOR=?,ANOTACION_CANCELADA=? "
		+ "WHERE ID_ANOTACION_PREDIO = ?";

	/** Constante cs_UPDATE_ANOTACION_ID. */
	private static final String cs_UPDATE_ANOTACION_ID = "UPDATE SDB_ACC_ANOTACION_PREDIO SET ID_ANOTACION = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? "
		+ "WHERE ID_ANOTACION_PREDIO = ?";

	/** Constante cs_UPDATE_ANOTACION_CORRECCION. */
	private static final String cs_UPDATE_ANOTACION_CORRECCION = "UPDATE SDB_ACC_ANOTACION_PREDIO SET CORRECCION = 'S', "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA  = ? AND ID_ANOTACION = ? AND ID_TURNO = ?";

	/** Constante cs_UPDATE_ANOTACION_ACTIVE. */
	private static final String cs_UPDATE_ANOTACION_ACTIVE = " UPDATE SDB_ACC_ANOTACION_PREDIO SET ID_TURNO_HISTORIA=?,ID_ESTADO_REGISTRO=?,"
		+ "COMENTARIO=?,FECHA_REGISTRO=?,VALOR=?,ID_DOCUMENTO=?,ID_NATURALEZA_JURIDICA=?,VERSION=?,ID_TIPO_ANOTACION=?,FECHA_RADICACION=?,"
		+ "RADICACION=?,ID_ESTADO_ANOTACION=?,ORDEN=?,ESPECIFICACION=?,ID_DATOS_ANT_SISTEMA=?,ID_DETALLE_ANT_SISTEMA=?,VERSION_DOCUMENTO=?,ACTIVO=?,"
		+ "ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=?,REVISION_CALIFICADOR=?,ANOTACION_CANCELADA=? "
		+ "WHERE ID_ANOTACION_PREDIO = ?";

	/** Constante cs_UPDATE_DOCUMENTO. */
	private static final String cs_UPDATE_DOCUMENTO = " UPDATE SDB_ACC_ANOTACION_PREDIO SET ID_DOCUMENTO = ? ,ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=?  WHERE ID_ANOTACION_PREDIO = ?";

	/** Constante cs_UPDATE_ORDEN. */
	private static final String cs_UPDATE_ORDEN = "UPDATE SDB_ACC_ANOTACION_PREDIO SET ID_ANOTACION = ? , ORDEN = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ?  WHERE ID_ANOTACION_PREDIO = ?";

	/** Constante cs_UPDATE_CANTIDAD_APERTURAR. */
	private static final String cs_UPDATE_CANTIDAD_APERTURAR = "UPDATE SDB_ACC_ANOTACION_PREDIO SET ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ?, CANTIDAD_APERTURAR = ? WHERE ID_ANOTACION_PREDIO = ?";

	/** Constante cs_DATA_PREDIO. */
	private static final String cs_DATA_PREDIO = " SELECT ANP.ORDEN, ANP.ID_DATOS_ANT_SISTEMA,ANP.ID_DETALLE_ANT_SISTEMA,GNJ.ID_GRUPO_NAT_JURIDICA,ANP.ID_ANOTACION_PREDIO,ANP.ID_ANOTACION,ANP.FECHA_REGISTRO,ANP.ID_TURNO_HISTORIA,ANP.ID_DOCUMENTO,  ANP.RADICACION,"
		+ " PTD.NOMBRE NOMBRE_DOC ,ATA.NOMBRE NOMBRE_ACTO,ATA.ID_NATURALEZA_JURIDICA COD_ACTO, GNJ.NOMBRE NOMBRE_GRUPO_ACTO, NVL (ANP.REVISION_CALIFICADOR,'N')REVISION_CALIFICADOR,NVL(ANP.VALOR,0)VALOR,NVL(ANP.COMENTARIO,' ')COMENTARIO,ANP.FECHA_RADICACION"
		+ " FROM SDB_ACC_ANOTACION_PREDIO ANP  INNER JOIN SDB_BGN_DOCUMENTO SBD ON SBD.ID_DOCUMENTO = ANP.ID_DOCUMENTO AND SBD.VERSION_DOCUMENTO = ANP.VERSION_DOCUMENTO "
		+ " INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO PTD ON PTD.ID_TIPO_DOCUMENTO = SBD.ID_TIPO_DOCUMENTO"
		+ " INNER JOIN SDB_PNG_NATURALEZA_JURIDICA ATA ON ATA.ID_NATURALEZA_JURIDICA = ANP.ID_NATURALEZA_JURIDICA  AND ATA.VERSION = ANP.VERSION"
		+ " INNER JOIN SDB_PNG_GRUPO_NATURALEZA_JURIDICA GNJ ON GNJ.ID_GRUPO_NAT_JURIDICA = ATA.ID_GRUPO_NAT_JURIDICA"
		+ " WHERE ID_TURNO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ?  ";

	/** Constante cs_UPDATE_INDICADOR_PREDIO. */
	private static final String cs_UPDATE_INDICADOR_PREDIO = " UPDATE SDB_ACC_ANOTACION_PREDIO SET   INDICADOR_CREAR_PREDIO_CIUDADANO = ?, ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=? WHERE ID_ANOTACION_PREDIO = ?";

	/** Constante cs_FIND_ALL_BY_TRANSFERENCIA_DOMINIO. */
	private static final String cs_FIND_ALL_BY_TRANSFERENCIA_DOMINIO = "  SELECT  MAX(BAP.ID_ANOTACION)ID_ANOTACION ,PNJ.ID_NATURALEZA_JURIDICA  FROM SDB_ACC_ANOTACION_PREDIO BAP "
		+ " INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PNJ ON PNJ.ID_NATURALEZA_JURIDICA = BAP.ID_NATURALEZA_JURIDICA AND  PNJ.VERSION = BAP.VERSION"
		+ " WHERE BAP.ID_CIRCULO =  ?  AND  BAP.ID_MATRICULA  = ? AND PNJ.ID_DOMINIO_NAT_JUR = 'X'  GROUP BY PNJ.ID_NATURALEZA_JURIDICA";

	/** Constante cs_FIND_ALL_HABILITA_SECUENCIA_PERSONA. */
	private static final String cs_FIND_ALL_HABILITA_SECUENCIA_PERSONA = "SELECT AP.ID_DOCUMENTO_TIPO FROM  SDB_ACC_TURNO_HISTORIA ATH INNER JOIN SDB_ACC_ANOTACION_PREDIO"
		+ " AAP ON ATH.ID_TURNO = AAP.ID_TURNO INNER JOIN SDB_ACC_ANOTACION_PREDIO_CIUDADANO AAPC ON AAPC.ID_ANOTACION_PREDIO = AAP.ID_ANOTACION_PREDIO "
		+ "INNER JOIN SDB_ACC_PERSONA AP ON AP.ID_PERSONA = AAPC.ID_PERSONA  "
		+ "WHERE AAP.ID_MATRICULA = ? AND ATH.ID_TURNO_HISTORIA = ? AND AAP.ACTIVO <> 'N' ";

	/** Constante cs_FIND_MAX_ORDEN_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_MAX_ORDEN_BY_CIRCULO_MATRICULA = "SELECT MAX(ORDEN) "
		+ "FROM SDB_ACC_ANOTACION_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_UPDATE_DOCUMENTO_COPIA_FOLIO. */
	private static final String cs_UPDATE_DOCUMENTO_COPIA_FOLIO = " UPDATE SDB_ACC_ANOTACION_PREDIO SET ID_DOCUMENTO = ? ,"
		+ "ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=?  WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? "
		+ "AND RADICACION = ? AND ID_NATURALEZA_JURIDICA = ?";

	/** Constante cs_FIND_ALL_BY_TURNO_SOLICITUD_ESTADO_N. */
	private static final String cs_FIND_ALL_BY_TURNO_SOLICITUD_ESTADO_N = "SELECT AP.* FROM SDB_ACC_ANOTACION_PREDIO AP INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PNJ ON PNJ.ID_NATURALEZA_JURIDICA = AP.ID_NATURALEZA_JURIDICA AND PNJ.VERSION = AP.VERSION WHERE PNJ.ID_GRUPO_NAT_JURIDICA = '0400' AND AP.ACTIVO = 'N' AND ID_TURNO = ? AND ID_SOLICITUD = ?";

	/** Constante cs_FIND_INFO_ANOTACION. */
	private static final String cs_FIND_INFO_ANOTACION = "SELECT TDP.NOMBRE TIPO_DOCUMENTO, BD.NUMERO NUMERO_DOCUMENTO, BD.FECHA_DOCUMENTO, POO.NOMBRE OFICINA_ORIGEN, PD.NOMBRE DEPARTAMENTO_OFICINA, PM.NOMBRE MUNICIPIO_OFICINA, PNJ.ID_NATURALEZA_JURIDICA TIPO_ACTO, PNJ.NOMBRE NOMBRE_ACTO FROM SDB_ACC_ANOTACION_PREDIO AAP INNER JOIN SDB_BGN_DOCUMENTO BD ON BD.ID_DOCUMENTO = AAP.ID_DOCUMENTO LEFT JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO TDP ON TDP.ID_TIPO_DOCUMENTO = BD.ID_TIPO_DOCUMENTO LEFT JOIN SDB_PGN_OFICINA_ORIGEN POO ON POO.ID_OFICINA_ORIGEN = BD.ID_OFICINA_ORIGEN LEFT JOIN SDB_PGN_DEPARTAMENTO PD ON PD.ID_PAIS = POO.ID_PAIS AND PD.ID_DEPARTAMENTO = POO.ID_DEPARTAMENTO LEFT JOIN SDB_PGN_MUNICIPIO PM ON PM.ID_MUNICIPIO = POO.ID_MUNICIPIO AND PM.ID_DEPARTAMENTO = POO.ID_DEPARTAMENTO AND PM.ID_PAIS = POO.ID_PAIS LEFT JOIN SDB_PNG_NATURALEZA_JURIDICA PNJ ON PNJ.ID_NATURALEZA_JURIDICA = AAP.ID_NATURALEZA_JURIDICA WHERE AAP.ID_CIRCULO = ? AND AAP.ID_MATRICULA = ? AND AAP.ID_ANOTACION = ?";

	/**
	 * Busca todos los id documento que se encunetren vinculados a una matrícula y turno historia.
	 *
	 * @param al_matricula Número de matrícula para utilizar en el filtro de la busqueda
	 * @param as_idTurnoHistoria id turno historia para utilizar en el filtro de la busqueda
	 * @return Colección de cadenas de caracteres con el resultado de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<String> getTipoDocPersonaByMatricula(Long al_matricula, String as_idTurnoHistoria)
	    throws B2BException
	{
		Collection<String> lcs_tipoDoc;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lcs_tipoDoc     = new ArrayList<String>();
		lps_ps          = null;
		lrs_rs          = null;

		if(NumericUtils.isValidLong(al_matricula) && StringUtils.isValidString(as_idTurnoHistoria))
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_ALL_HABILITA_SECUENCIA_PERSONA);

				setLong(lps_ps, al_matricula, li_column++);
				lps_ps.setString(li_column++, as_idTurnoHistoria);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcs_tipoDoc.add(getTipoDocumento(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "getTipoDocPersonaByMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcs_tipoDoc;
	}

	/**
	 * Busca en la base de datos los detalles de un acto para un id anotación.
	 *
	 * @param as_idAnotacion cadena de caracteres con el id anotación a utilizar en la busqueda
	 * @return Objeto registro calificación contenedor del resultado de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroCalificacion
	 */
	public RegistroCalificacion consultaDetalleActo(String as_idAnotacion)
	    throws B2BException
	{
		RegistroCalificacion lcrc_data;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lcrc_data     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(StringUtils.isValidString(as_idAnotacion))
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_ACTO_BY_ANOTACION);

				lps_ps.setString(li_column++, as_idAnotacion);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcrc_data = getDetelleActo(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultaDetalleActo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcrc_data;
	}

	/**
	 * Consulta en la base de datos el id anotación maxima para los registros relacionados a un
	 * id círculo, id matrícula y turno específicos.
	 *
	 * @param aap_anotacionPredio Argumento de tipo AnotacionPredio que contiene los criterios de búsqueda necesarios.
	 * @return Número con el maximo id anotación
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int consultarMaxIdAnotacion(AnotacionPredio aap_anotacionPredio)
	    throws B2BException
	{
		int               li_max;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_max     = -1;
		lps_ps     = null;
		lrs_rs     = null;

		if(aap_anotacionPredio != null)
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_TURNO_MAX);

				lps_ps.setString(li_contador++, aap_anotacionPredio.getIdCirculo());
				setLong(lps_ps, aap_anotacionPredio.getIdMatricula(), li_contador++);
				lps_ps.setString(li_contador++, aap_anotacionPredio.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_max = lrs_rs.getInt(1);
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultarMaxIdAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return li_max;
	}

/**
 * Consulta en la base de datos el orden maximo para los registros relacionados a un
 * id círculo, id matrícula y turno específicos.
 *
 * @param aap_anotacionPredio Argumento de tipo AnotacionPredio que contiene los criterios de búsqueda necesarios.
 * @return Número con el maximo orden
 * @throws B2BException Señala que se ha producido una excepción
 * @see int
 */
	public int consultarMaxOrden(AnotacionPredio aap_anotacionPredio)
	    throws B2BException
	{
		int               li_max;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_max     = -1;
		lps_ps     = null;
		lrs_rs     = null;

		if(aap_anotacionPredio != null)
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_MAX_ORDEN_BY_CIRCULO_MATRICULA_TURNO_MAX);

				lps_ps.setString(li_contador++, aap_anotacionPredio.getIdCirculo());
				setLong(lps_ps, aap_anotacionPredio.getIdMatricula(), li_contador++);
				lps_ps.setString(li_contador++, aap_anotacionPredio.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_max = lrs_rs.getInt(1);
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultarMaxOrden", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return li_max;
	}

	/**
	 * Elimina las anotaciones de predio de la base de datos relacionadas a un turno historia.
	 *
	 * @param abd_turnoHistoria Objeto contenedor del id turno historia a utilizar como filtro en el borrado
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteAnotacionPredioByTurnoHistoria(BigDecimal abd_turnoHistoria)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		if(abd_turnoHistoria != null)
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_DELETE_BY_ID_TURNO_HISTORIA);

				lps_ps.setBigDecimal(li_contador++, abd_turnoHistoria);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteAnotacionPredioByTurnoHistoria", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Elimina un registro de la base de datos por medio de su id.
	 *
	 * @param aap_param objeto contenedor del id anotación predio a utilizar como filtro
	 * @return Retorna null
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio deleteById(AnotacionPredio aap_param)
	    throws B2BException
	{
		return (aap_param != null) ? deleteById(aap_param.getIdAnotacionPredio()) : null;
	}

	/**
	 * Elimina un registro de la base de datos por medio de su id.
	 *
	 * @param as_param cadena de caracteres con el id anotación predio a utilizar en el filtro
	 * @return Retorna null
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio deleteById(String as_param)
	    throws B2BException
	{
		AnotacionPredio lap_predio;

		lap_predio = null;

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_DELETE_BY_ID);

				lps_ps.setString(li_contador++, as_param);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}

		return lap_predio;
	}

	/**
	 * Consulta los datos de anotación predio con un id círculo y matrícula específicos.
	 *
	 * @param aap_param Objeto contenedor del id círculo y matrícula a utilizar como filtro en la busqueda
	 * @return Objeto AnotacionPredio contenedor del resultado de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio findAllByTransferenciaDominio(AnotacionPredio aap_param)
	    throws B2BException
	{
		AnotacionPredio lap_datos;

		lap_datos = new AnotacionPredio();

		if(aap_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_ALL_BY_TRANSFERENCIA_DOMINIO);

				lps_ps.setString(li_contador++, aap_param.getIdCirculo());
				setLong(lps_ps, aap_param.getIdMatricula(), li_contador++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lap_datos = getObjetFromResultSetAnotaciones(lrs_rs, false);
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
		}

		return lap_datos;
	}

	/**
	 * Método encargado de consultar todos los registros por idTurno y idSolicitud flitrados por estado anotacion en N
	 *
	 * @param as_idTurno String Turno con parámetro de búsqueda
	 * @param ls_idSolicitud String Solicitud con parámetro de búsqueda
	 * @return Colección Anotación predio con objetos consultados
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public Collection<AnotacionPredio> findAllByTurnoSolicitudEstadoN(String as_idTurno, String ls_idSolicitud)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_anotacionPredio;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcap_anotacionPredio     = new ArrayList<AnotacionPredio>();
		lps_ps                   = null;
		lrs_rs                   = null;

		if(StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(ls_idSolicitud))
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_ALL_BY_TURNO_SOLICITUD_ESTADO_N);

				lps_ps.setString(li_contador++, as_idTurno);
				lps_ps.setString(li_contador++, ls_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_anotacionPredio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByTurnoSolicitudEstadoN", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcap_anotacionPredio))
			lcap_anotacionPredio = null;

		return lcap_anotacionPredio;
	}

	/**
	 * Consulta en base de datos todas las anotaciones predio relacionadas a un idSolicitud.
	 *
	 * @param as_idSolicitud Objeto contenedor del idSolicitud a utilizar como filtro
	 * @return Colección con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredio> findAnotacionesInactivasByIdSolicitud(String as_idSolicitud)
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
			int li_contador;

			li_contador     = 1;
			lps_ps          = getConnection().prepareStatement(cs_FIND_ANOTACIONES_INACTIVAS_BY_ID_SOLICITUD);

			lps_ps.setString(li_contador++, as_idSolicitud);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcap_datos.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAnotacionesInactivasByIdSolicitud", lse_e);

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
	 * Consulta en base de datos todas las anotaciones predio relacionadas a un idTurnoHistoria.
	 *
	 * @param as_idTurno Objeto contenedor del idTurno a utilizar como filtro
	 * @return Colección con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredio> findAnotacionesInactivasByIdTurno(String as_idTurno, boolean ab_indPredioCiud)
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
			int li_contador;

			li_contador     = 1;
			lps_ps          = getConnection()
					                  .prepareStatement(
					    ab_indPredioCiud ? cs_FIND_ANOTACIONES_INACTIVAS_BY_ID_TURNO_AND_INDICADOR_PREDIO_CIUDADANO_S
					                         : cs_FIND_ANOTACIONES_INACTIVAS_BY_ID_TURNO
					);

			lps_ps.setString(li_contador++, as_idTurno);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcap_datos.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAnotacionesInactivasByIdTurno", lse_e);

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
	 * Consulta en base de datos todas las anotaciones predio relacionadas a un id círculo y matrícula.
	 *
	 * @param aap_param Objeto contenedor del id círculo y matrícula a utilizar como filtro
	 * @return Colección con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredio> findByCirculoMatricula(AnotacionPredio aap_param)
	    throws B2BException
	{
		return (aap_param != null) ? findByCirculoMatricula(aap_param.getIdCirculo(), aap_param.getIdMatricula()) : null;
	}

	/**
	 * Consulta en base de datos todas las anotaciones predio relacionadas a un id círculo y matrícula.
	 *
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id de la matrícula a utilizar como filtro en la busqueda
	 * @return Colección con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredio> findByCirculoMatricula(String as_idCirculo, Long al_idMatricula)
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
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);

				lps_ps.setString(li_contador++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_contador++);

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
	 * Consulta en base de datos todas las anotaciones predio relacionadas a un id círculo y matrícula activos.
	 *
	 * @param aap_param Objeto contenedor del id círculo y matrícula a utilizar como filtro
	 * @return Colección con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredio> findByCirculoMatriculaActive(AnotacionPredio aap_param)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_datos;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcap_datos     = new ArrayList<AnotacionPredio>();
		lps_ps         = null;
		lrs_rs         = null;

		if(aap_param != null)
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_ACTIVE);

				lps_ps.setString(li_contador++, aap_param.getIdCirculo());
				setLong(lps_ps, aap_param.getIdMatricula(), li_contador++);
				setLong(lps_ps, NumericUtils.getLongWrapper(aap_param.getIdTurnoHistoria()), li_contador++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatriculaActive", lse_e);

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
	 * Consulta en base de datos una anotación predio que esté relacionada a un id círculo, id matrícula
	 * e id acto específicos.
	 *
	 * @param aap_param Objeto contenedor de los datos a ser utilizados en el filtro de la consulta
	 * @return Objeto anotación predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio findByCirculoMatriculaActo(AnotacionPredio aap_param)
	    throws B2BException
	{
		AnotacionPredio   lap_predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lap_predio     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(aap_param != null)
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_ACTO);

				lps_ps.setString(li_contador++, aap_param.getIdCirculo());
				setLong(lps_ps, aap_param.getIdMatricula(), li_contador++);
				lps_ps.setString(li_contador++, aap_param.getIdNaturalezaJuridica());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lap_predio = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatriculaActo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lap_predio;
	}

	/**
	 * Consulta en base de datos una anotación predio que esté relacionada a un id círculo, id matrícula
	 * e id anotación específicos.
	 *
	 * @param aap_param Objeto contenedor de los datos a ser utilizados en el filtro de la consulta
	 * @return Objeto anotación predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio findByCirculoMatriculaAnotacion(AnotacionPredio aap_param)
	    throws B2BException
	{
		AnotacionPredio   lap_predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lap_predio     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(aap_param != null)
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_ANOTACION);

				lps_ps.setString(li_contador++, aap_param.getIdCirculo());
				setLong(lps_ps, aap_param.getIdMatricula(), li_contador++);
				setLong(lps_ps, aap_param.getIdAnotacion(), li_contador++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lap_predio = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatriculaAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lap_predio;
	}

	/**
	 * Consulta en base de datos todas las anotaciones predio relacionadas a un id círculo y matrícula.
	 *
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id de la matrícula a utilizar como filtro en la busqueda
	 * @return Colección con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio> findByCirculoMatriculaBng(
	    String as_idCirculo, Long al_idMatricula
	)
	    throws B2BException
	{
		Collection<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio> lcap_datos;

		lcap_datos = new ArrayList<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio>();

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
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);

				lps_ps.setString(li_contador++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_datos.add(getObjetBngAnotacion(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatriculaBng", lse_e);

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
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param aa_param correspondiente al valor del tipo de objeto AnotacionPredio
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredio> findByCirculoMatriculaTurno(AnotacionPredio aa_param)
	    throws B2BException
	{
		return (aa_param != null)
		? findByCirculoMatriculaTurno(aa_param.getIdCirculo(), aa_param.getIdMatricula(), aa_param.getIdTurno()) : null;
	}

	/**
	 * Metodo encargado de consultar todas las anotaciones de un circulo, matricula y turno.
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto Long
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @return Colección de tipo <code>AnotacionPredio</code> con todos los registros que coinciden con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredio> findByCirculoMatriculaTurno(
	    String as_idCirculo, Long al_idMatricula, String as_idTurno
	)
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
			if(
			    StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula)
				    && StringUtils.isValidString(as_idTurno)
			)
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_TURNO);

				lps_ps.setString(li_contador++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_contador++);
				lps_ps.setString(li_contador++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_datos.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByCirculoMatriculaTurno", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcap_datos.isEmpty())
			lcap_datos = null;

		return lcap_datos;
	}

	/**
	 * Metodo encargado de consultar los registros de anotacion predio que coincidan con los criterios de búsqueda.
	 *
	 * @param aap_param Argumento de tipo <code>AnotacionPredio</code> que contiene id circulo, id matricula, id turno, id anotacion para realizar la consulta.
	 * @return Objeto de tipo <code>AnotacionPredio</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio findByCirculoMatriculaTurnoAnotacion(AnotacionPredio aap_param)
	    throws B2BException
	{
		AnotacionPredio   lap_datos;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lap_datos     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(aap_param != null)
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_TURNO_ANOTACION);

				lps_ps.setString(li_contador++, aap_param.getIdCirculo());
				setLong(lps_ps, aap_param.getIdMatricula(), li_contador++);
				lps_ps.setString(li_contador++, aap_param.getIdTurno());
				setLong(lps_ps, aap_param.getIdAnotacion(), li_contador++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lap_datos = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatriculaTurnoAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lap_datos;
	}

	/**
	 * Método encargado de consultar la información de la anotación con base a sus llaves primarias.
	 *
	 * @param as_idCirculo correspondiente al valor de as id circulo
	 * @param al_idMatricula correspondiente al valor de al id matricula
	 * @param al_idAnotacion correspondiente al valor de al id anotacion
	 * @return Retorna el mapa con la información consultada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, Object> findByDataAnotacion(String as_idCirculo, Long al_idMatricula, Long al_idAnotacion)
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
			if(
			    StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula)
				    && NumericUtils.isValidLong(al_idAnotacion)
			)
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_INFO_ANOTACION);

				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);
				setLong(lps_ps, al_idAnotacion, li_column++);

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
	 * Consulta en base de datos una anotación predio que esté relacionada a un id anotación predio específico.
	 *
	 * @param aap_param Objeto contenedor de los datos a ser utilizados en el filtro de la consulta
	 * @return Objeto anotación predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio findById(AnotacionPredio aap_param)
	    throws B2BException
	{
		return findById((aap_param != null) ? aap_param.getIdAnotacionPredio() : null);
	}

	/**
	 * Consulta en base de datos una anotación predio que esté relacionada a un id anotación predio específico.
	 *
	 * @param as_idAnotacionPredio Argumento de tipo <code>String</code> que contiene el id anotacion predio para realizar la búsqueda.
	 * @return Objeto anotación predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio findById(String as_idAnotacionPredio)
	    throws B2BException
	{
		AnotacionPredio   lap_predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lap_predio     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(StringUtils.isValidString(as_idAnotacionPredio))
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, as_idAnotacionPredio);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lap_predio = getObjetFromResultSet(lrs_rs);
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

		return lap_predio;
	}

	/**
	 * Consulta en la base de datos el id anotación maxima para los registros relacionados a un
	 * id círculo e id matrícula específicos.
	 *
	 * @param as_circulo Id círculo a ser utilizado en la consulta
	 * @param as_matricula Id matrícula a ser utilizada en la consulta
	 * @return Número con el maximo id anotación
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Long
	 */
	public Long findByMaxIdAnotacion(String as_circulo, String as_matricula)
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
			lps_ps          = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_MAX);

			lps_ps.setString(li_contador++, as_circulo);
			lps_ps.setString(li_contador++, as_matricula);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ll_max = getObjetMaxFromResultSet(lrs_rs);
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

		return ll_max;
	}

	/**
	 * Consulta en la base de datos todas las anoytacnes predio relacionadas a un turno específico.
	 *
	 * @param as_idTurno id turno historia a ser utilizado como filtro en la consulta
	 * @return Colección de anotaciones predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredio> findByTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_predio;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcap_predio     = null;
		lps_ps          = null;
		lrs_rs          = null;
		lcap_predio     = new ArrayList<AnotacionPredio>();

		if(StringUtils.isValidString(as_idTurno))
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_TURNO);

				lps_ps.setString(li_contador++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_predio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcap_predio;
	}

	/**
	 * Consulta en la base de datos todas las anoytacnes predio relacionadas a un turno historia específico.
	 *
	 * @param abd_turnoHistoria id turno historia a ser utilizado como filtro en la consulta
	 * @return Colección de anotaciones predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredio> findByTurnoHistoria(BigDecimal abd_turnoHistoria)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_predio;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcap_predio     = null;
		lps_ps          = null;
		lrs_rs          = null;
		lcap_predio     = new ArrayList<AnotacionPredio>();

		if(abd_turnoHistoria != null)
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_TURNO_HISTORIA);

				lps_ps.setBigDecimal(li_contador++, abd_turnoHistoria);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_predio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoHistoria", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcap_predio;
	}

	/**
	 * Consulta en la base de datos todas las anoytacnes predio relacionadas a un turno historia, circulo y matrícula específico.
	 *
	 * @param aap_anotacion Objeto que contiene los datos para realizar la busqueda.
	 * @return Colección de anotaciones predio consultadas.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredio> findByTurnoHistoriaCirculoMatricula(AnotacionPredio aap_anotacion)
	    throws B2BException
	{
		return findByTurnoHistoriaCirculoMatricula(aap_anotacion, false);
	}

	/**
	 * Consulta en la base de datos todas las anoytacnes predio relacionadas a un turno historia, circulo y matrícula específico.
	 *
	 * @param aap_anotacion Objeto que contiene los datos para realizar la busqueda.
	 * @param ab_orderByIdAnotacion correspondiente al valor del tipo de objeto boolean
	 * @return Colección de anotaciones predio consultadas.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredio> findByTurnoHistoriaCirculoMatricula(
	    AnotacionPredio aap_anotacion, boolean ab_orderByIdAnotacion
	)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_predio;

		lcap_predio = new ArrayList<AnotacionPredio>();

		if(aap_anotacion != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_contador;
				StringBuilder lbs_query;

				li_contador     = 1;
				lbs_query       = new StringBuilder();

				lbs_query.append(cs_FIND_BY_TURNO_HISTORIA_CIRCULO_MATRICULA);

				if(ab_orderByIdAnotacion)
					lbs_query.append(" ORDER BY ID_ANOTACION ASC ");

				lps_ps = getConnection().prepareStatement(lbs_query.toString());

				setLong(lps_ps, NumericUtils.getLongWrapper(aap_anotacion.getIdTurnoHistoria()), li_contador++);
				lps_ps.setString(li_contador++, aap_anotacion.getIdCirculo());
				setLong(lps_ps, aap_anotacion.getIdMatricula(), li_contador++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_predio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoHistoriaCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcap_predio.isEmpty())
			lcap_predio = null;

		return lcap_predio;
	}

	/**
	 * Consulta en la base de datos todas las anoytacnes predio relacionadas a un turno específico.
	 *
	 * @param aap_ap id turno historia a ser utilizado como filtro en la consulta
	 * @return Colección de anotaciones predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredio> findByTurnoMatricula(AnotacionPredio aap_ap)
	    throws B2BException
	{
		Collection<AnotacionPredio> lcap_predio;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcap_predio     = null;
		lps_ps          = null;
		lrs_rs          = null;
		lcap_predio     = new ArrayList<AnotacionPredio>();

		if(aap_ap != null)
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_TURNO_MATRICULA);

				lps_ps.setString(li_contador++, aap_ap.getIdTurno());
				lps_ps.setString(li_contador++, aap_ap.getIdCirculo());
				setLong(lps_ps, aap_ap.getIdMatricula(), li_contador++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_predio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcap_predio;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param arc_rc correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<RegistroCalificacion> findDataPredio(RegistroCalificacion arc_rc)
	    throws B2BException
	{
		return findDataPredio(arc_rc, false);
	}

	/**
	 * Consulta los datos de un predio relacionado a un id turno, id círculo e id matrícula.
	 *
	 * @param arc_rc Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @param ab_activos correspondiente al valor del tipo de objeto boolean
	 * @return Colección de registro calificación resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<RegistroCalificacion> findDataPredio(RegistroCalificacion arc_rc, boolean ab_activos)
	    throws B2BException
	{
		Collection<RegistroCalificacion> lcrc_data;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;
		StringBuilder                    lsb_sb;

		lcrc_data     = new ArrayList<RegistroCalificacion>();
		lps_ps        = null;
		lrs_rs        = null;
		lsb_sb        = new StringBuilder(cs_DATA_PREDIO);

		if(arc_rc != null)
		{
			try
			{
				int li_column;

				li_column = 1;

				if(ab_activos)
					lsb_sb.append(" AND ANP.ACTIVO = 'S'");

				lsb_sb.append(" ORDER BY  ANP.ID_ANOTACION ASC");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, arc_rc.getTurno());
				lps_ps.setString(li_column++, arc_rc.getIdCirculo());
				setLong(lps_ps, arc_rc.getIdMatricula(), li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcrc_data.add(getDataAnotaciones(lrs_rs));
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

		if(lcrc_data.isEmpty())
			lcrc_data = null;

		return lcrc_data;
	}

	/**
	 * Metodo encargado de consultar el orden máximo de las anotaciones que coincidan con los criterios de búsqueda.
	 *
	 * @param as_circulo Argumento de tipo <code>String</code> que contiene el circulo para realizar la búsqueda.
	 * @param as_matricula Argumento de tipo <code>String</code> que contiene la matricula para realizar la búsqueda.
	 * @return Objeto de tipo <code>Long</code> que contiene el resultado de la búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Long
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
	 * Consulta la naturaleza jurídica de las anotaciones predio que se encuentren relacionadas por medio de un
	 * id círculo e id matrícula específicos.
	 *
	 * @param aap_param  Objeto contenedor de los datos a utilizar en la consulta
	 * @return Colección de anotaciones predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
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
				lps_ps          = getConnection().prepareStatement(cs_FIND_ALL_WITH_NATURALEZA_BY_MATRICULA);

				lps_ps.setString(li_contador++, aap_param.getIdCirculo());
				setLong(lps_ps, aap_param.getIdMatricula(), li_contador++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcap_datos.add(getObjetFromResultSetAnotaciones(lrs_rs));
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
	 * Inserta un nuevo registro en la tabla.
	 *
	 * @param aap_parametros Objeto contenedor de los datos que harán parte de del nuevo registro
	 * @return Objeto contenedor del id creado a partir de la inserción del nuevo registro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredio
	 */
	public AnotacionPredio insert(AnotacionPredio aap_parametros)
	    throws B2BException
	{
		if(aap_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;
				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lps_ps.setLong(li_column++, aap_parametros.getIdTurnoHistoria());
				lps_ps.setString(li_column++, aap_parametros.getIdEstadoRegistro());
				lps_ps.setString(li_column++, aap_parametros.getIdCirculo());
				setLong(lps_ps, aap_parametros.getIdMatricula(), li_column++);
				lps_ps.setString(li_column++, aap_parametros.getIdSolicitud());
				setLong(lps_ps, aap_parametros.getIdAnotacion(), li_column++);
				lps_ps.setString(li_column++, aap_parametros.getComentario());
				setDate(lps_ps, aap_parametros.getFechaRegistro(), li_column++);
				setDouble(lps_ps, NumericUtils.getDoubleWrapper(aap_parametros.getValor()), li_column++);
				lps_ps.setString(li_column++, aap_parametros.getIdDocumento());
				lps_ps.setString(li_column++, aap_parametros.getIdNaturalezaJuridica());
				lps_ps.setLong(li_column++, aap_parametros.getVersion());
				lps_ps.setString(li_column++, aap_parametros.getIdTipoAnotacion());
				setDate(lps_ps, aap_parametros.getFechaRadicacion(), li_column++);
				lps_ps.setString(li_column++, aap_parametros.getRadicacion());
				lps_ps.setString(li_column++, aap_parametros.getIdEstadoAnotacion());
				setDouble(lps_ps, NumericUtils.getDoubleWrapper(aap_parametros.getOrden()), li_column++);
				lps_ps.setString(li_column++, aap_parametros.getEspecificacion());
				lps_ps.setString(li_column++, aap_parametros.getIdDatosAntSistema());
				lps_ps.setString(li_column++, aap_parametros.getIdDetalleAntSistema());
				lps_ps.setLong(li_column++, aap_parametros.getVersionDocumento());
				lps_ps.setString(li_column++, aap_parametros.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aap_parametros.getIpCreacion());
				lps_ps.setString(li_column++, aap_parametros.getRevisionCalificador());
				lps_ps.setString(li_column++, aap_parametros.getIdTurno());
				lps_ps.setString(li_column++, aap_parametros.getAnotacionCancelada());

				String ls_activo;
				ls_activo = aap_parametros.getActivo();

				if(StringUtils.isValidString(ls_activo))
					lps_ps.setString(li_column++, ls_activo);
				else
					lps_ps.setString(li_column++, EstadoCommon.S);

				lps_ps.executeUpdate();

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					aap_parametros.setIdAnotacionPredio(StringUtils.getString(lrs_rs.getLong(1)));
			}
			catch(SQLException lse_e)
			{
				getLog().error("insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lps_secuencia);
				close(lrs_rs);
			}
		}

		return aap_parametros;
	}

	/**
	 * Actualiza el valor del indicador crear predio ciudadano para un id anotación predio específico.
	 *
	 * @param aap_param Objeto contenedor de la información a utlizar en los filtros de busqueda y actualización
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void modificarIndicador(AnotacionPredio aap_param)
	    throws B2BException
	{
		if(aap_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_INDICADOR_PREDIO);

				lps_ps.setString(li_column++, aap_param.getIndicadorPredioCiudadano());
				lps_ps.setString(li_column++, aap_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aap_param.getIpModificacion());
				lps_ps.setString(li_column++, aap_param.getIdAnotacionPredio());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "modificarIndicador", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualiza la información de una anotación predio por medio del id anotación predio.
	 *
	 * @param aap_param Objeto contenedor de la información a actualizar del registro
	 * @param ab_b si es false, indica que solo va a actualizar el id documento del predio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void modificiarAnotacionesPredio(AnotacionPredio aap_param, boolean ab_b)
	    throws B2BException
	{
		if(aap_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column = 1;

				if(ab_b)
					lps_ps = getConnection().prepareStatement(cs_UPDATE);
				else
					lps_ps = getConnection().prepareStatement(cs_UPDATE_DOCUMENTO);

				if(ab_b)
				{
					setDate(lps_ps, aap_param.getFechaRadicacion(), li_column++);
					lps_ps.setString(li_column++, aap_param.getRadicacion());
					lps_ps.setString(li_column++, aap_param.getIdEstadoAnotacion());
					lps_ps.setString(li_column++, aap_param.getIdNaturalezaJuridica());
					lps_ps.setString(li_column++, aap_param.getEspecificacion());
					lps_ps.setBigDecimal(li_column++, aap_param.getValor());
					lps_ps.setString(
					    li_column++, StringUtils.getStringNotNull(aap_param.getComentario()).toUpperCase()
					);
				}
				else
					lps_ps.setString(li_column++, aap_param.getIdDocumento());

				lps_ps.setString(li_column++, aap_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aap_param.getIpModificacion());
				lps_ps.setString(li_column++, aap_param.getIdAnotacionPredio());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "modificiarAnotacionesPredio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de actualizar el documento para todos los folios que cumplan con los criterios de actualización.
	 *
	 * @param aap_anotacionPredio Objeto contenedor de la información a actualizar del registro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void modificiarDocumentoCopiaFolio(AnotacionPredio aap_anotacionPredio)
	    throws B2BException
	{
		if(aap_anotacionPredio != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_DOCUMENTO_COPIA_FOLIO);

				lps_ps.setString(li_column++, aap_anotacionPredio.getIdDocumento());
				lps_ps.setString(li_column++, aap_anotacionPredio.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aap_anotacionPredio.getIpModificacion());

				lps_ps.setString(li_column++, aap_anotacionPredio.getIdCirculo());
				setLong(lps_ps, aap_anotacionPredio.getIdMatricula(), li_column++);
				lps_ps.setString(li_column++, aap_anotacionPredio.getRadicacion());
				lps_ps.setString(li_column++, aap_anotacionPredio.getIdNaturalezaJuridica());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "modificiarDocumentoCopiaFolio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método sobreescrito para actualizar el campo Activo.
	 *
	 * @param aap_parametros Objeto contenedor de la información a actualizar del registro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateAnotacion(AnotacionPredio aap_parametros)
	    throws B2BException
	{
		updateAnotacion(aap_parametros, false);
	}

	/**
	 * Actualiza todos los valores de un registro de anotación predio.
	 *
	 * @param aap_parametros Objeto contenedor de la información a actualizar del registro
	 * @param ab_active correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateAnotacion(AnotacionPredio aap_parametros, boolean ab_active)
	    throws B2BException
	{
		if(aap_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column = 1;

				if(ab_active)
					lps_ps = getConnection().prepareStatement(cs_UPDATE_ANOTACION_ACTIVE);
				else
					lps_ps = getConnection().prepareStatement(cs_UPDATE_ANOTACION);

				lps_ps.setLong(li_column++, aap_parametros.getIdTurnoHistoria());
				lps_ps.setString(li_column++, aap_parametros.getIdEstadoRegistro());
				lps_ps.setString(li_column++, aap_parametros.getComentario());
				setDate(lps_ps, aap_parametros.getFechaRegistro(), li_column++);
				setDouble(lps_ps, NumericUtils.getDoubleWrapper(aap_parametros.getValor()), li_column++);
				lps_ps.setString(li_column++, aap_parametros.getIdDocumento());
				lps_ps.setString(li_column++, aap_parametros.getIdNaturalezaJuridica());
				lps_ps.setLong(li_column++, aap_parametros.getVersion());
				lps_ps.setString(li_column++, aap_parametros.getIdTipoAnotacion());
				setDate(lps_ps, aap_parametros.getFechaRadicacion(), li_column++);
				lps_ps.setString(li_column++, aap_parametros.getRadicacion());
				lps_ps.setString(li_column++, aap_parametros.getIdEstadoAnotacion());
				setDouble(lps_ps, NumericUtils.getDoubleWrapper(aap_parametros.getOrden()), li_column++);
				lps_ps.setString(li_column++, aap_parametros.getEspecificacion());
				lps_ps.setString(li_column++, aap_parametros.getIdDatosAntSistema());
				lps_ps.setString(li_column++, aap_parametros.getIdDetalleAntSistema());
				lps_ps.setLong(li_column++, aap_parametros.getVersionDocumento());

				if(ab_active)
					lps_ps.setString(li_column++, aap_parametros.getActivo());

				lps_ps.setString(li_column++, aap_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aap_parametros.getIpModificacion());
				lps_ps.setString(li_column++, aap_parametros.getRevisionCalificador());
				lps_ps.setString(li_column++, aap_parametros.getAnotacionCancelada());

				lps_ps.setString(li_column++, aap_parametros.getIdAnotacionPredio());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de actualizar el estado de corrección de la anotación.
	 *
	 * @param aap_parametros Objeto que contiene los datos de la anotación.
	 * @throws B2BException
	 */
	public void updateAnotacionCorreccion(AnotacionPredio aap_parametros)
	    throws B2BException
	{
		if(aap_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_ANOTACION_CORRECCION);

				lps_ps.setString(li_column++, aap_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aap_parametros.getIpModificacion());
				lps_ps.setString(li_column++, aap_parametros.getIdCirculo());
				setLong(lps_ps, aap_parametros.getIdMatricula(), li_column++);
				setLong(lps_ps, aap_parametros.getIdAnotacion(), li_column++);
				lps_ps.setString(li_column++, aap_parametros.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateAnotacionCorreccion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualiza los ids de una anotación luego de ser eliminada la anterior.
	 *
	 * @param aap_parametros Objeto contenedor de la información a actualizar del registro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateAnotacionIds(AnotacionPredio aap_parametros)
	    throws B2BException
	{
		if(aap_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_ANOTACION_ID);

				setDouble(lps_ps, NumericUtils.getDoubleWrapper(aap_parametros.getIdAnotacion()), li_column++);
				lps_ps.setString(li_column++, aap_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aap_parametros.getIpModificacion());

				lps_ps.setString(li_column++, aap_parametros.getIdAnotacionPredio());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateAnotacionIds", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualiza la cantidad a aperturar de la anotación.
	 *
	 * @param aap_param Objeto contenedor de la información del registro a actualizar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateCantidadAperturar(AnotacionPredio aap_param)
	    throws B2BException
	{
		if(aap_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_CANTIDAD_APERTURAR);

				lps_ps.setString(li_column++, aap_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aap_param.getIpModificacion());
				lps_ps.setInt(li_column++, aap_param.getCantidadAperturar());
				lps_ps.setString(li_column++, aap_param.getIdAnotacionPredio());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateCantidadAperturar", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualiza el orden e id anotación de un registro.
	 *
	 * @param aap_param Objeto contenedor de la información del registro a actualizar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateOrdenIdAnotacion(AnotacionPredio aap_param)
	    throws B2BException
	{
		if(aap_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_ORDEN);

				setLong(lps_ps, aap_param.getIdAnotacion(), li_column++);
				setLong(lps_ps, NumericUtils.getLongWrapper(aap_param.getOrden()), li_column++);
				lps_ps.setString(li_column++, aap_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aap_param.getIpModificacion());
				lps_ps.setString(li_column++, aap_param.getIdAnotacionPredio());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateOrdenIdAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Extrae la información recuperada de la base de datos y la asigna a un objeto RegistroCalificacion.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return RegistroCalificacion con la información extraida de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RegistroCalificacion getDataAnotaciones(ResultSet ars_rs)
	    throws SQLException
	{
		RegistroCalificacion lrc_datos;
		boolean              lb_b;

		lrc_datos     = new RegistroCalificacion();
		lb_b          = false;

		lrc_datos.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		lrc_datos.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		lrc_datos.setIdDetalleAntSistema(ars_rs.getString("ID_DETALLE_ANT_SISTEMA"));
		lrc_datos.setIdAnotacionPredio(ars_rs.getString("ID_ANOTACION_PREDIO"));
		lrc_datos.setFechaRegistro(ars_rs.getTimestamp("FECHA_REGISTRO"));
		lrc_datos.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));
		lrc_datos.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		lrc_datos.setRadicacion(ars_rs.getString("RADICACION"));
		lrc_datos.setNombreDoc(ars_rs.getString("NOMBRE_DOC"));
		lrc_datos.setNombreActo(ars_rs.getString("NOMBRE_ACTO"));
		lrc_datos.setNombreGrupoActo(ars_rs.getString("NOMBRE_GRUPO_ACTO"));
		lrc_datos.setCodActo(ars_rs.getString("COD_ACTO"));
		lrc_datos.setComentario(ars_rs.getString("COMENTARIO"));
		lrc_datos.setFechaRadicacion(ars_rs.getTimestamp("FECHA_RADICACION"));
		lrc_datos.setOrden(getLong(ars_rs, "ORDEN"));

		if(ars_rs.getString("REVISION_CALIFICADOR").equalsIgnoreCase(EstadoCommon.S))
			lb_b = true;

		lrc_datos.setRevision(lb_b);

		lrc_datos.setValor(ars_rs.getBigDecimal("VALOR"));
		lrc_datos.setIdNaturalezJuridica(ars_rs.getString("ID_GRUPO_NAT_JURIDICA"));

		return lrc_datos;
	}

	/**
	 * Extrae la información recuperada de la base de datos y la asigna a un objeto RegistroCalificacion.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return RegistroCalificacion con la información extraida de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RegistroCalificacion getDetelleActo(ResultSet ars_rs)
	    throws SQLException
	{
		RegistroCalificacion lrc_rc;

		lrc_rc = new RegistroCalificacion();

		lrc_rc.setCodActo(ars_rs.getString("COD_ACTO"));
		lrc_rc.setIdAnotacionPredio(ars_rs.getString("ID_ANOTACION_PREDIO"));

		return lrc_rc;
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
	private com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio getObjetBngAnotacion(ResultSet ars_rs)
	    throws SQLException
	{
		com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_ap;

		lap_ap = new com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio();

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
	 * Extrae la información recuperada de la base de datos y la asigna a un objeto AnotacionPredio.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return AnotacionPredio con la información extraida de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AnotacionPredio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AnotacionPredio lap_ap;

		lap_ap = new AnotacionPredio();

		lap_ap.setIdAnotacionPredio(ars_rs.getString("ID_ANOTACION_PREDIO"));
		lap_ap.setIdTurnoHistoria(ars_rs.getLong("ID_TURNO_HISTORIA"));
		lap_ap.setIdEstadoRegistro(ars_rs.getString("ID_ESTADO_REGISTRO"));
		lap_ap.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lap_ap.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
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
		lap_ap.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		lap_ap.setVersionDocumento(ars_rs.getLong("VERSION_DOCUMENTO"));
		lap_ap.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lap_ap.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lap_ap.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lap_ap.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lap_ap.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lap_ap.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lap_ap.setRevisionCalificador(ars_rs.getString("REVISION_CALIFICADOR"));
		lap_ap.setIdTurno(ars_rs.getString("ID_TURNO"));
		lap_ap.setAnotacionCancelada(ars_rs.getString("ANOTACION_CANCELADA"));
		lap_ap.setEspecificacion(ClobUtils.clobToString(ars_rs.getClob("ESPECIFICACION")));
		lap_ap.setIdDetalleAntSistema(ars_rs.getString("ID_DETALLE_ANT_SISTEMA"));
		lap_ap.setActivo(ars_rs.getString("ACTIVO"));
		lap_ap.setCantidadAperturar(ars_rs.getInt("CANTIDAD_APERTURAR"));

		{
			String ls_data;

			ls_data = ars_rs.getString("CORRECCION");

			if(StringUtils.isValidString(ls_data))
				lap_ap.setCorreccion(BooleanUtils.getBooleanValue(ls_data));
		}

		return lap_ap;
	}

	/**
	 * Extrae la información recuperada de la base de datos y la asigna a un objeto AnotacionPredio.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @param ab_b Indica si solo se extraera de la consulta el di naturaleza jurídica e id anotación
	 * @return AnotacionPredio con la información extraida de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
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
		}
		else
		{
			lap_ap.setIdNaturalezaJuridica(ars_rs.getString("ID_NATURALEZA_JURIDICA"));
			lap_ap.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		}

		return lap_ap;
	}

	/**
	 * Extrae la información recuperada de la base de datos y la asigna a un objeto AnotacionPredio.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return AnotacionPredio con la información extraida de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AnotacionPredio getObjetFromResultSetAnotaciones(ResultSet ars_rs)
	    throws SQLException
	{
		AnotacionPredio lap_ap;

		lap_ap = new AnotacionPredio();

		lap_ap.setGrupoNaturaleza(ars_rs.getString("ID_GRUPO_NAT_JURIDICA"));
		lap_ap.setIdNaturalezaJuridica(ars_rs.getString("ID_NATURALEZA_JURIDICA"));
		lap_ap.setNombreNaturaleza(ars_rs.getString("NOMBRE"));
		lap_ap.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		lap_ap.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		lap_ap.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lap_ap.setDominioNaturaleza(ars_rs.getString("ID_DOMINIO_NAT_JUR"));
		lap_ap.setAnotacionCancelada(ars_rs.getString("ANOTACION_CANCELADA"));
		lap_ap.setVersion(ars_rs.getLong("VERSION"));

		return lap_ap;
	}

	/**
	 * Extrae la información recuperada de la base de datos y la asigna a un objeto Long.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return Número del maximo id anotación
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Long getObjetMaxFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Long ll_ap;

		ll_ap = getLong(ars_rs, "MAX(ID_ANOTACION)");

		return ll_ap;
	}

	/**
	 * Extrae la información recuperada de la base de datos y la asigna a un objeto String.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return cadena de caracteres con el id documento tipo
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private String getTipoDocumento(ResultSet ars_rs)
	    throws SQLException
	{
		String as_tipoDoc;

		as_tipoDoc = ars_rs.getString("ID_DOCUMENTO_TIPO");

		return as_tipoDoc;
	}
}
