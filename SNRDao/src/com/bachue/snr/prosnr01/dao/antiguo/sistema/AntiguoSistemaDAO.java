package com.bachue.snr.prosnr01.dao.antiguo.sistema;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.antiguoSistema.Complementacion;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Clase que contiene todos las consultas de SDB_ACC_SOLICITUD
 *
 * @author garias
 */
public class AntiguoSistemaDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante CS_CONSULTA_PREDIO_DOCUMENTO. */
	private static final String CS_CONSULTA_PREDIO_DOCUMENTO = "SELECT S.ID_SOLICITUD, S.NIR, T.ID_TURNO FROM SDB_ACC_SOLICITUD S RIGHT JOIN SDB_ACC_TURNO T ON S.ID_SOLICITUD = T.ID_SOLICITUD WHERE T.ID_TURNO = (SELECT ID_TURNO	FROM SDB_ACC_TURNO_HISTORIA TH WHERE TH.ID_TURNO_HISTORIA = ?)";

	/** Constante CS_DATOS_DOCUMENTO_PREDIO. */
	private static final String CS_DATOS_DOCUMENTO_PREDIO = "SELECT S.ID_SOLICITUD AS ID_SOLICITUD_DOC, SM.ID_DOCUMENTO, TD.NOMBRE TIPO_DOCUMENTO, SM.NUMERO, TO_DATE(TO_CHAR(S.FECHA_CREACION, 'YYYY/MM/DD HH:MI:SS'), 'YYYY/MM/DD HH:MI:SS') AS FECHA_SOLICITUD, SM.FECHA_DOCUMENTO, SM.FECHA_EJECUTORIA, OO.NOMBRE NOMBRE_OFICINA, TOF.NOMBRE TIPO_OFICINA, PA.NOMBRE PAIS, DTO.NOMBRE DEPARTAMENTO, MUN.NOMBRE MUNICIPIO, S.ENTIDAD_EXENTA ,TE.NOMBRE TIPO_ENTIDAD,S.ID_TURNO_REPRODUCCION FROM SDB_BGN_DOCUMENTO SM RIGHT JOIN SDB_ACC_SOLICITUD S ON SM.ID_DOCUMENTO = S.ID_DOCUMENTO AND SM.VERSION_DOCUMENTO = S.VERSION_DOCUMENTO LEFT JOIN SDB_PGN_OFICINA_ORIGEN OO ON SM.ID_OFICINA_ORIGEN = OO.ID_OFICINA_ORIGEN LEFT JOIN SDB_PGN_TIPO_OFICINA TOF ON OO.ID_TIPO_OFICINA = TOF.ID_TIPO_OFICINA LEFT JOIN SDB_PGN_TIPO_ENTIDAD TE ON TE.ID_TIPO_ENTIDAD = TOF.ID_TIPO_ENTIDAD LEFT JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO TD ON SM.ID_TIPO_DOCUMENTO = TD.ID_TIPO_DOCUMENTO LEFT JOIN SDB_PGN_DEPARTAMENTO DTO ON OO.ID_DEPARTAMENTO = DTO.ID_DEPARTAMENTO LEFT JOIN SDB_PGN_PAIS PA ON OO.ID_PAIS = PA.ID_PAIS LEFT JOIN SDB_PGN_MUNICIPIO MUN ON OO.ID_MUNICIPIO = MUN.ID_MUNICIPIO AND OO.ID_PAIS = MUN.ID_PAIS AND OO.ID_DEPARTAMENTO = MUN.ID_DEPARTAMENTO WHERE S.ID_SOLICITUD = ( SELECT ID_SOLICITUD FROM SDB_ACC_TURNO T WHERE T.ID_TURNO = ";

	/** Constante CS_ACTOS_PREDIO. */
	public static final String CS_ACTOS_PREDIO = " SELECT  SM.ID_TIPO_ACTO, UPPER(TA.NOMBRE) ESPECIFICACION,"
		+ " TA.ACTO_SIN_CUANTIA,  SM.CUANTIA, SM.VALOR_AVALUO, SM.CANTIDAD_ACTOS, SM.FECHA_VENCIMIENTO, NVL(TAD.NOMBRE,' ') TIPO_ADQUISICION "
		+ " FROM SDB_ACC_TURNO_HISTORIA TH  INNER JOIN SDB_ACC_TURNO SAT ON SAT.ID_SOLICITUD = TH.ID_SOLICITUD AND SAT.ID_TURNO = TH.ID_TURNO"
		+ " INNER JOIN SDB_ACC_ACTO SM  ON SM.ID_SOLICITUD = TH.ID_SOLICITUD AND SM.ID_CIRCULO = SAT.ID_CIRCULO"
		+ " INNER JOIN SDB_PGN_TIPO_ACTO TA ON TA.ID_TIPO_ACTO  = SM.ID_TIPO_ACTO  AND TA.VERSION = SM.VERSION"
		+ " LEFT JOIN SDB_ACC_TIPO_ADQUISICION TAD ON TAD.ID_TIPO_ADQUISICION  = SM.ID_TIPO_ADQUISICION WHERE  TH.ID_TURNO_HISTORIA = ?";

	/** Constante CS_DATOS_ANT_SISTEMA. */
	private static final String CS_DATOS_ANT_SISTEMA = "SELECT DAS.ADQUISICION_PREDIO, CR.NOMBRE CIRCULO, PT.ILICODE, DAS.NOMBRE_PREDIO, DAS.ID_LIBRO_ANT_SISTEMA, DAS.TOMO,  DAS.FOLIO, DAS.PARTIDA, DAS.ANIO, P.NOMBRE PAIS, D.NOMBRE DEPARTAMENTO, M.NOMBRE MUNICIPIO FROM SDB_ACC_DATOS_ANT_SISTEMA DAS INNER JOIN SDB_ACC_SOLICITUD S ON (DAS.ID_DATOS_ANT_SISTEMA = S.ID_DATOS_ANT_SISTEMA) INNER JOIN SDB_ACC_TURNO T ON (S.ID_SOLICITUD = T.ID_SOLICITUD) INNER JOIN SDB_ACC_TURNO_HISTORIA TH ON (T.ID_TURNO = TH.ID_TURNO) INNER JOIN SDB_PGN_CIRCULO_REGISTRAL CR ON (CR.ID_CIRCULO = DAS.ID_CIRCULO) INNER JOIN SDB_COL_PREDIO_TIPO PT ON (PT.ID_TIPO_PREDIO = DAS.ID_TIPO_PREDIO) INNER JOIN SDB_PGN_PAIS P ON (P.ID_PAIS = DAS.ID_PAIS) INNER JOIN SDB_PGN_DEPARTAMENTO D ON (D.ID_DEPARTAMENTO = DAS.ID_DEPARTAMENTO) INNER JOIN SDB_PGN_MUNICIPIO M ON (M.ID_MUNICIPIO = DAS.ID_MUNICIPIO AND M.ID_DEPARTAMENTO = DAS.ID_DEPARTAMENTO) WHERE TH.ID_TURNO_HISTORIA = ? ";

	/** Constante CS_CANTIDAD_POR_ANTIGUO_SISTEMA. */
	private static final String CS_CANTIDAD_POR_ANTIGUO_SISTEMA = "SELECT TH.ID_ETAPA, COUNT(TH.ID_ETAPA) CANTIDAD FROM SDB_ACC_TURNO_HISTORIA TH WHERE TH.ESTADO_ACTIVIDAD IN ('ASG') AND TH.ID_ETAPA = ? AND TH.ID_USUARIO = ? ";

	/** Constante CS_CONSULTA_BANDEJA_TURNOS_ANTIGUO_SISTEMA. */
	private static final String CS_CONSULTA_BANDEJA_TURNOS_ANTIGUO_SISTEMA = "SELECT P.NOMBRE PROCESO, TH.ID_TURNO, TH.FECHA_REPARTO FECHA_ASIGNACION, S.NIR, TH.MOTIVO_NO_TRAMITE, THANT.OBSERVACIONES OBSERVACIONES, TH.ID_TURNO_HISTORIA, TH.ESTADO_ACTIVIDAD FROM SDB_ACC_TURNO_HISTORIA TH INNER JOIN SDB_ACC_TURNO T ON TH.ID_TURNO = T.ID_TURNO INNER JOIN SDB_ACC_PROCESO P ON TH.ID_PROCESO = P.ID_PROCESO LEFT JOIN SDB_ACC_SOLICITUD S ON TH.ID_PROCESO = S.ID_PROCESO AND TH.ID_SOLICITUD = S.ID_SOLICITUD LEFT JOIN SDB_ACC_TURNO_HISTORIA THANT ON TH.ID_ANTERIOR = THANT.ID_TURNO_HISTORIA WHERE TH.ID_ETAPA = ? AND TH.ID_USUARIO = ? AND TH.ESTADO_ACTIVIDAD IN ('ASG') ";

	/** Constante CS_DATOS_ANT_SISTEMA_SOLICITUD. */
	private static final String CS_DATOS_ANT_SISTEMA_SOLICITUD = "SELECT * FROM SDB_ACC_DATOS_ANT_SISTEMA WHERE ID_DATOS_ANT_SISTEMA = (select ID_DATOS_ANT_SISTEMA from SDB_ACC_SOLICITUD where ID_SOLICITUD = ? )";

	/** Constante CS_FIND_COMPLEMENTACION. */
	private static final String CS_FIND_COMPLEMENTACION = "SELECT COMPLEMENTACION FROM SDB_BNG_COMPLEMENTACION_PREDIO WHERE ID_COMPLEMENTACION = (SELECT ID_COMPLEMENTACION FROM SDB_BNG_PREDIO_REGISTRO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?)";

	/**
	 * Retorna el valor de custon query.
	 *
	 * @param as_consulta correspondiente al valor del tipo de objeto String
	 * @param lhmpCriterios correspondiente al valor del tipo de objeto Map<Integer,Object>
	 * @return el valor de custon query
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getCustonQuery(String as_consulta, Map<Integer, Object> lhmpCriterios)
	    throws B2BException
	{
		List<Map<String, Object>> lllhmpRet = new LinkedList<Map<String, Object>>();
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lps_ps                              = null;
		lrs_rs                              = null;

		try
		{
			StringBuilder as_query = null;

			if(as_consulta.equals(IdentificadoresCommon.PREDIO))
				as_query = new StringBuilder(CS_CONSULTA_PREDIO_DOCUMENTO);
			else if(as_consulta.equals(IdentificadoresCommon.DOCUMENTO))
			{
				as_query = new StringBuilder(CS_DATOS_DOCUMENTO_PREDIO);
				as_query.append(" (SELECT ID_TURNO	FROM SDB_ACC_TURNO_HISTORIA TH WHERE TH.ID_TURNO_HISTORIA = ?))");
			}
			else if(as_consulta.equals(IdentificadoresCommon.DETALLE_DOCUMENTO))
			{
				as_query = new StringBuilder(CS_DATOS_DOCUMENTO_PREDIO);
				as_query.append(" ? )");
			}
			else if(as_consulta.equals(IdentificadoresCommon.ACTOS))
				as_query = new StringBuilder(CS_ACTOS_PREDIO);
			else if(as_consulta.equals(IdentificadoresCommon.ANT_SISTEMA))
				as_query = new StringBuilder(CS_DATOS_ANT_SISTEMA);

			lps_ps = getConnection().prepareStatement(as_query.toString());

			for(Map.Entry<Integer, Object> criterios : lhmpCriterios.entrySet())
				lps_ps.setObject(NumericUtils.getInt(criterios.getKey()), criterios.getValue());

			lrs_rs = lps_ps.executeQuery();

			ResultSetMetaData md      = lrs_rs.getMetaData();
			int               columns = md.getColumnCount();

			while(lrs_rs.next())
			{
				LinkedHashMap<String, Object> row = new LinkedHashMap<String, Object>(columns);

				for(int i = 1; i <= columns; ++i)
					row.put(md.getColumnLabel(i), lrs_rs.getObject(i));

				lllhmpRet.add(row);
			}

			if(lllhmpRet.isEmpty())
				lllhmpRet = null;
		}
		catch(SQLException lse_e)
		{
			logError(this, "getCustonQuery", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lllhmpRet;
	}

	/**
	 * Retorna el valor de custon query detail.
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param lhmpCriterios correspondiente al valor del tipo de objeto Map<Integer,Object>
	 * @return el valor de custon query detail
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getCustonQueryDetail(
	    String as_idTurno, String as_idSolicitud, Map<Integer, Object> lhmpCriterios
	)
	    throws B2BException
	{
		List<Map<String, Object>> lllhmpRet = new LinkedList<Map<String, Object>>();
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;
		StringBuilder             lsb_sb;

		lps_ps                              = null;
		lrs_rs                              = null;
		lsb_sb                              = new StringBuilder();

		try
		{
			lsb_sb = lsb_sb.append(CS_CONSULTA_BANDEJA_TURNOS_ANTIGUO_SISTEMA);

			if(StringUtils.isValidString(as_idTurno))
			{
				lsb_sb = lsb_sb.append("AND TH.ID_TURNO = ? ");
				lhmpCriterios.put(NumericUtils.getInteger(3), as_idTurno);

				if(StringUtils.isValidString(as_idSolicitud))
				{
					lsb_sb = lsb_sb.append("AND TH.ID_SOLICITUD = ? ");
					lhmpCriterios.put(NumericUtils.getInteger(4), as_idSolicitud);
				}
			}
			else
			{
				if(StringUtils.isValidString(as_idSolicitud))
				{
					lsb_sb = lsb_sb.append("AND TH.ID_SOLICITUD = ? ");
					lhmpCriterios.put(NumericUtils.getInteger(3), as_idSolicitud);
				}
			}

			lsb_sb     = lsb_sb.append("ORDER BY TH.ID_TURNO ASC, TH.FECHA_INICIAL,TH.FECHA_CREACION DESC ");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			for(Map.Entry<Integer, Object> criterios : lhmpCriterios.entrySet())
				lps_ps.setObject(NumericUtils.getInt(criterios.getKey()), criterios.getValue());

			lrs_rs = lps_ps.executeQuery();

			ResultSetMetaData md      = lrs_rs.getMetaData();
			int               columns = md.getColumnCount();

			while(lrs_rs.next())
			{
				LinkedHashMap<String, Object> row = new LinkedHashMap<String, Object>(columns);

				for(int i = 1; i <= columns; ++i)
					row.put(md.getColumnLabel(i), lrs_rs.getObject(i));

				lllhmpRet.add(row);
			}

			if(lllhmpRet.isEmpty())
				lllhmpRet = null;
		}
		catch(SQLException lse_e)
		{
			logError(this, "getCustonQuery", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lllhmpRet;
	}

	/**
	 * Retorna el valor de custon query ibox.
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param lhmpCriterios correspondiente al valor del tipo de objeto LinkedHashMap<Integer,Object>
	 * @return el valor de custon query ibox
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public LinkedList<LinkedHashMap<String, Object>> getCustonQueryIbox(
	    String as_idTurno, String as_idSolicitud, LinkedHashMap<Integer, Object> lhmpCriterios
	)
	    throws B2BException
	{
		LinkedList<LinkedHashMap<String, Object>> lllhmpRet = new LinkedList<LinkedHashMap<String, Object>>();
		PreparedStatement                         lps_ps;
		ResultSet                                 lrs_rs;
		StringBuilder                             lsb_sb;

		lps_ps                                              = null;
		lrs_rs                                              = null;
		lsb_sb                                              = new StringBuilder();

		try
		{
			lsb_sb = lsb_sb.append(CS_CANTIDAD_POR_ANTIGUO_SISTEMA);

			if(StringUtils.isValidString(as_idTurno))
			{
				lsb_sb = lsb_sb.append("AND TH.ID_TURNO = ? ");
				lhmpCriterios.put(NumericUtils.getInteger(3), as_idTurno);

				if(StringUtils.isValidString(as_idSolicitud))
				{
					lsb_sb = lsb_sb.append("AND TH.ID_SOLICITUD = ? GROUP BY TH.ID_ETAPA ");
					lhmpCriterios.put(NumericUtils.getInteger(4), as_idSolicitud);
				}
				else
					lsb_sb = lsb_sb.append("GROUP BY TH.ID_ETAPA ");
			}
			else
			{
				if(StringUtils.isValidString(as_idSolicitud))
				{
					lsb_sb = lsb_sb.append("AND TH.ID_SOLICITUD = ? GROUP BY TH.ID_ETAPA ");
					lhmpCriterios.put(NumericUtils.getInteger(3), as_idSolicitud);
				}
				else
					lsb_sb = lsb_sb.append("GROUP BY TH.ID_ETAPA ");
			}

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			for(Map.Entry<Integer, Object> criterios : lhmpCriterios.entrySet())
				lps_ps.setObject(NumericUtils.getInt(criterios.getKey()), criterios.getValue());

			lrs_rs = lps_ps.executeQuery();

			ResultSetMetaData md      = lrs_rs.getMetaData();
			int               columns = md.getColumnCount();

			while(lrs_rs.next())
			{
				LinkedHashMap<String, Object> row = new LinkedHashMap<String, Object>(columns);

				for(int i = 1; i <= columns; ++i)
					row.put(md.getColumnLabel(i), lrs_rs.getObject(i));

				lllhmpRet.add(row);
			}

			if(lllhmpRet.isEmpty())
				lllhmpRet = null;
		}
		catch(SQLException lse_e)
		{
			logError(this, "getCustonQuery", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lllhmpRet;
	}

	/**
	 * Ejecuta la consulta
	 *
	 * @param as_consulta correspondiente al valor del tipo de objeto String
	 * @param lhmpCriterios correspondiente al valor del tipo de objeto LinkedHashMap<Integer,Object>
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void executeQuery(String as_consulta, LinkedHashMap<Integer, Object> lhmpCriterios)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(as_consulta);

			for(Map.Entry<Integer, Object> criterios : lhmpCriterios.entrySet())
				lps_ps.setObject(NumericUtils.getInt(criterios.getKey()), criterios.getValue());

			lps_ps.executeUpdate();
		}
		catch(SQLException lse_e)
		{
			logError(this, "executeQuery", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}
	}

	/**
	 * Retorna el valor del objeto de tipo Find ant sistema.
	 *
	 * @param isSolicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto datos ant sistema
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public DatosAntSistema findAntSistema(String isSolicitud)
	    throws B2BException
	{
		DatosAntSistema   ldas_datosAntSistema;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ldas_datosAntSistema     = null;
		lps_ps                   = null;
		lrs_rs                   = null;

		try
		{
			int contador;
			contador     = 1;

			lps_ps = getConnection().prepareStatement(CS_DATOS_ANT_SISTEMA_SOLICITUD);

			lps_ps.setString(contador++, isSolicitud);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ldas_datosAntSistema = getAntSistema(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAntSistema", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ldas_datosAntSistema;
	}

	/**
	 * Retorna el valor del objeto de complementacion por id circulo y id matricula
	 *
	 * @param lc_complementacion correspondiente al valor del tipo de objeto Complementacion
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findComplementacion(Complementacion lc_complementacion)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		String            ls_return;

		lps_ps        = null;
		lrs_rs        = null;
		ls_return     = null;

		try
		{
			int contador;
			contador     = 1;

			lps_ps = getConnection().prepareStatement(CS_FIND_COMPLEMENTACION);

			String ls_idCirculo;
			Long   ll_idMatricula;

			ls_idCirculo       = lc_complementacion.getIdCirculo();
			ll_idMatricula     = lc_complementacion.getIdMatricula();

			lps_ps.setString(contador++, ls_idCirculo);
			setLong(lps_ps, ll_idMatricula, contador++);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_return = getComplementacion(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findComplementacion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor de la secuencia
	 *
	 * @param as_consulta correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int findSecuence(String as_consulta)
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
			lps_ps     = getConnection().prepareStatement(as_consulta);

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
	 * Retorna el valor de DatosAntSistema
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de DatosAntSistema
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see DatosAntSistema
	 */
	private DatosAntSistema getAntSistema(ResultSet lrs_rs)
	    throws SQLException
	{
		DatosAntSistema ldas_return;
		ldas_return = new DatosAntSistema();

		ldas_return.setIdDatosAntSistema(lrs_rs.getString("ID_DATOS_ANT_SISTEMA"));
		ldas_return.setAdquisicionPredio(lrs_rs.getString("ADQUISICION_PREDIO"));
		ldas_return.setIdTipoPredio(lrs_rs.getString("ID_TIPO_PREDIO"));
		ldas_return.setIdCirculo(lrs_rs.getString("ID_CIRCULO"));
		ldas_return.setIdPais(lrs_rs.getString("ID_PAIS"));
		ldas_return.setIdDepartamento(lrs_rs.getString("ID_DEPARTAMENTO"));
		ldas_return.setIdMunicipio(lrs_rs.getString("ID_MUNICIPIO"));
		ldas_return.setIdLibroAntSistema(getLong(lrs_rs, "ID_LIBRO_ANT_SISTEMA"));
		ldas_return.setTomo(getLong(lrs_rs, "TOMO"));
		ldas_return.setFolio(getLong(lrs_rs, "FOLIO"));
		ldas_return.setPartida(lrs_rs.getString("PARTIDA"));
		ldas_return.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ldas_return.setNombrePredio(lrs_rs.getString("NOMBRE_PREDIO"));
		ldas_return.setAnio(getLong(lrs_rs, "ANIO"));

		return ldas_return;
	}

	/**
	 * Retorna el valor de complementacion.
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de complementacion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private String getComplementacion(ResultSet lrs_rs)
	    throws SQLException
	{
		String ls_return;
		ls_return = lrs_rs.getString("COMPLEMENTACION");

		return ls_return;
	}
}
