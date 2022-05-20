package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.BitacoraBloqueo;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_BITACORA_BLOQUEO.
 *
 * @author Gabriel Arias
 */
public class BitacoraBloqueoDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT * FROM SDB_ACC_BITACORA_BLOQUEO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_FIND_TURNOS_BLOQUEO_ACTIVO_DIF_DESBLOQUEO. */
	private static final String cs_FIND_TURNOS_BLOQUEO_ACTIVO_DIF_DESBLOQUEO = "SELECT * FROM SDB_ACC_BITACORA_BLOQUEO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ACTIVO = 'S' AND ESTADO <> 'DESBLOQUEO'";

	/** Constante cs_FIND_TURNO_BLOQUEO_REGISTRO_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_TURNO_BLOQUEO_REGISTRO_BY_CIRCULO_MATRICULA = "SELECT SUBSTR(BQ.ID_TURNO_BLOQUEO,1,4) PRIMERA_PARTE,SUBSTR(BQ.ID_TURNO_BLOQUEO,6,3) SEGUNDA_PARTE,SUBSTR(BQ.ID_TURNO_BLOQUEO,10,1) TERCERA_PARTE, CAST (SUBSTR(BQ.ID_TURNO_BLOQUEO,12) AS INTEGER) CUARTA_PARTE, BQ.ID_TURNO_BLOQUEO, BQ.ID_CIRCULO, BQ.ID_MATRICULA, BQ.ID_BITACORA_BLOQUEO, BQ.ESTADO FROM SDB_ACC_BITACORA_BLOQUEO BQ INNER JOIN ( SELECT SDB_ACC_BITACORA_BLOQUEO.ID_TURNO_BLOQUEO, SDB_ACC_BITACORA_BLOQUEO.ID_CIRCULO, SDB_ACC_BITACORA_BLOQUEO.ID_MATRICULA, MAX(SDB_ACC_BITACORA_BLOQUEO.ID_BITACORA_BLOQUEO) ID_BITACORA FROM SDB_ACC_BITACORA_BLOQUEO GROUP BY SDB_ACC_BITACORA_BLOQUEO.ID_TURNO_BLOQUEO, SDB_ACC_BITACORA_BLOQUEO.ID_CIRCULO, SDB_ACC_BITACORA_BLOQUEO.ID_MATRICULA ) VQ ON VQ.ID_BITACORA = BQ.ID_BITACORA_BLOQUEO WHERE  BQ.ID_CIRCULO = ? AND BQ.ID_MATRICULA = ? AND BQ.ESTADO = ? ORDER BY PRIMERA_PARTE, SEGUNDA_PARTE, TERCERA_PARTE, CUARTA_PARTE ";

	/** Constante cs_FIND_TURNO_BLOQUEO_BY_CIRCULO_MATRICULA_BLQ_ACT. */
	private static final String cs_FIND_TURNO_BLOQUEO_BY_CIRCULO_MATRICULA_BLQ_ACT = "SELECT SUBSTR(BQ.ID_TURNO_BLOQUEO,1,4) PRIMERA_PARTE,SUBSTR(BQ.ID_TURNO_BLOQUEO,6,3) SEGUNDA_PARTE,SUBSTR(BQ.ID_TURNO_BLOQUEO,10,1)  TERCERA_PARTE, CAST (SUBSTR(BQ.ID_TURNO_BLOQUEO,12) AS INTEGER) CUARTA_PARTE, BQ.ID_TURNO_BLOQUEO, BQ.ID_CIRCULO, BQ.ID_MATRICULA,  BQ.ID_BITACORA_BLOQUEO, BQ.ESTADO  FROM SDB_ACC_BITACORA_BLOQUEO BQ  INNER JOIN ( SELECT SDB_ACC_BITACORA_BLOQUEO.ID_TURNO_BLOQUEO,  SDB_ACC_BITACORA_BLOQUEO.ID_CIRCULO, SDB_ACC_BITACORA_BLOQUEO.ID_MATRICULA, MAX(SDB_ACC_BITACORA_BLOQUEO.ID_BITACORA_BLOQUEO)  ID_BITACORA FROM SDB_ACC_BITACORA_BLOQUEO GROUP BY SDB_ACC_BITACORA_BLOQUEO.ID_TURNO_BLOQUEO, SDB_ACC_BITACORA_BLOQUEO.ID_CIRCULO,  SDB_ACC_BITACORA_BLOQUEO.ID_MATRICULA ) VQ ON VQ.ID_BITACORA = BQ.ID_BITACORA_BLOQUEO  WHERE  BQ.ID_CIRCULO = ? AND BQ.ID_MATRICULA = ? AND BQ.ESTADO = ? UNION ALL SELECT SUBSTR(BPR.TURNO_BLOQUEO,1,4) PRIMERA_PARTE,SUBSTR(BPR.TURNO_BLOQUEO,6,3) SEGUNDA_PARTE,SUBSTR(BPR.TURNO_BLOQUEO,10,1)  TERCERA_PARTE, CAST (SUBSTR(BPR.TURNO_BLOQUEO,12) AS INTEGER) CUARTA_PARTE, BPR.TURNO_BLOQUEO ID_TURNO_BLOQUEO, BPR.ID_CIRCULO, BPR.ID_MATRICULA,  TO_NUMBER('0') ID_BITACORA_BLOQUEO, 'ACTIVO' ESTADO FROM SDB_BNG_PREDIO_REGISTRO BPR  WHERE  BPR.ID_CIRCULO = ? AND BPR.ID_MATRICULA = ? AND BPR.TURNO_BLOQUEO IS NOT NULL ORDER BY PRIMERA_PARTE, SEGUNDA_PARTE, TERCERA_PARTE, CUARTA_PARTE";

	/** Constante cs_FIND_TURNO_BLOQUEO_BY_CIRCULO_MATRICULA_BLQ_ACT_DIF_TURNO. */
	private static final String cs_FIND_TURNO_BLOQUEO_BY_CIRCULO_MATRICULA_BLQ_ACT_DIF_TURNO = "SELECT SUBSTR(BQ.ID_TURNO_BLOQUEO,1,4) PRIMERA_PARTE,SUBSTR(BQ.ID_TURNO_BLOQUEO,6,3) SEGUNDA_PARTE,SUBSTR(BQ.ID_TURNO_BLOQUEO,10,1)  TERCERA_PARTE, CAST (SUBSTR(BQ.ID_TURNO_BLOQUEO,12) AS INTEGER) CUARTA_PARTE, BQ.ID_TURNO_BLOQUEO, BQ.ID_CIRCULO, BQ.ID_MATRICULA,  BQ.ID_BITACORA_BLOQUEO, BQ.ESTADO  FROM SDB_ACC_BITACORA_BLOQUEO BQ  INNER JOIN ( SELECT SDB_ACC_BITACORA_BLOQUEO.ID_TURNO_BLOQUEO,  SDB_ACC_BITACORA_BLOQUEO.ID_CIRCULO, SDB_ACC_BITACORA_BLOQUEO.ID_MATRICULA, MAX(SDB_ACC_BITACORA_BLOQUEO.ID_BITACORA_BLOQUEO)  ID_BITACORA FROM SDB_ACC_BITACORA_BLOQUEO GROUP BY SDB_ACC_BITACORA_BLOQUEO.ID_TURNO_BLOQUEO, SDB_ACC_BITACORA_BLOQUEO.ID_CIRCULO,  SDB_ACC_BITACORA_BLOQUEO.ID_MATRICULA ) VQ ON VQ.ID_BITACORA = BQ.ID_BITACORA_BLOQUEO  WHERE  BQ.ID_CIRCULO = ? AND BQ.ID_MATRICULA = ? AND BQ.ESTADO = ? UNION ALL SELECT SUBSTR(BPR.TURNO_BLOQUEO,1,4) PRIMERA_PARTE,SUBSTR(BPR.TURNO_BLOQUEO,6,3) SEGUNDA_PARTE,SUBSTR(BPR.TURNO_BLOQUEO,10,1)  TERCERA_PARTE, CAST (SUBSTR(BPR.TURNO_BLOQUEO,12) AS INTEGER) CUARTA_PARTE, BPR.TURNO_BLOQUEO ID_TURNO_BLOQUEO, BPR.ID_CIRCULO, BPR.ID_MATRICULA,  TO_NUMBER('0') ID_BITACORA_BLOQUEO, 'ACTIVO' ESTADO FROM SDB_BNG_PREDIO_REGISTRO BPR  WHERE  BPR.ID_CIRCULO = ? AND BPR.ID_MATRICULA = ? AND BPR.TURNO_BLOQUEO IS NOT NULL AND BPR.TURNO_BLOQUEO != ? ORDER BY PRIMERA_PARTE, SEGUNDA_PARTE, TERCERA_PARTE, CUARTA_PARTE";

	/**
	 * Consulta en la base de datos todos los registros que estén asociados a un id círculo e id matrícula.
	 *
	 * @param as_circulo correspondiente al valor del tipo de objeto String
	 * @param as_matricula correspondiente al valor del tipo de objeto String
	 * @return Colección de BitacoraBloqueo con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<BitacoraBloqueo> findAllTurnosBloqueoActivoDifDesbloqueo(String as_circulo, String as_matricula)
	    throws B2BException
	{
		Collection<BitacoraBloqueo> lcbb_turnosBloqueo;

		lcbb_turnosBloqueo = new ArrayList<BitacoraBloqueo>();

		if(StringUtils.isValidString(as_circulo) && StringUtils.isValidString(as_matricula))
		{
			int               li_count;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			li_count     = 1;
			lps_ps       = null;
			lrs_rs       = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_TURNOS_BLOQUEO_ACTIVO_DIF_DESBLOQUEO);

				lps_ps.setString(li_count++, as_circulo);
				setLong(lps_ps, NumericUtils.getLongWrapper(as_matricula), li_count++);
				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcbb_turnosBloqueo.add(getBitacoraBloqueo(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllTurnosBloqueoActivoDifDesbloqueo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcbb_turnosBloqueo.isEmpty())
			lcbb_turnosBloqueo = null;

		return lcbb_turnosBloqueo;
	}

	/**
	 * Consulta en la base de datos todos los registros que estén asociados a un id círculo e id matrícula.
	 *
	 * @param abb_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return Colección de BitacoraBloqueo con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<BitacoraBloqueo> findByCirculoMatricula(BitacoraBloqueo abb_param)
	    throws B2BException
	{
		return findByCirculoMatricula(abb_param, false);
	}

	/**
	 * Consulta en la base de datos todos los registros que estén asociados a un id círculo e id matrícula.
	 *
	 * @param abb_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @param ab_onlyBloqueantes Objeto que indica si se consultará solo turnos bloqueantes
	 * @return Colección de BitacoraBloqueo con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<BitacoraBloqueo> findByCirculoMatricula(BitacoraBloqueo abb_param, boolean ab_onlyBloqueantes)
	    throws B2BException
	{
		Collection<BitacoraBloqueo> lcbb_turnosBloqueo;

		lcbb_turnosBloqueo = new ArrayList<BitacoraBloqueo>();

		if(abb_param != null)
		{
			int               li_count;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			li_count     = 1;
			lps_ps       = null;
			lrs_rs       = null;

			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder(cs_FIND_BY_CIRCULO_MATRICULA);

				if(ab_onlyBloqueantes)
					lsb_query.append(" AND ESTADO = 'BLOQUEADO'");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_count++, abb_param.getIdCirculo());
				setLong(lps_ps, NumericUtils.getLongWrapper(abb_param.getIdMatricula()), li_count++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcbb_turnosBloqueo.add(getBitacoraBloqueo(lrs_rs));
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

		if(lcbb_turnosBloqueo.isEmpty())
			lcbb_turnosBloqueo = null;

		return lcbb_turnosBloqueo;
	}

	/**
	 * Consulta en la base de datos todos los registros que estén asociados a un id círculo e id matrícula.
	 *
	 * @param apr_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return Colección de String con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<String> findTurnoBloqueoRegistroByCirculoMatricula(PredioRegistro apr_param)
	    throws B2BException
	{
		return findTurnoBloqueoRegistroByCirculoMatricula(apr_param, false);
	}

	/**
	 * Consulta en la base de datos todos los registros que estén asociados a un id círculo e id matrícula.
	 *
	 * @param apr_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @param ab_onlyBloqueados correspondiente al valor del tipo de objeto boolean
	 * @return Colección de String con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<String> findTurnoBloqueoRegistroByCirculoMatricula(
	    PredioRegistro apr_param, boolean ab_onlyBloqueados
	)
	    throws B2BException
	{
		Collection<String> lcs_turnosBloqueo;
		int                li_count;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lcs_turnosBloqueo     = new ArrayList<String>();
		li_count              = 1;
		lps_ps                = null;
		lrs_rs                = null;

		if(apr_param != null)
		{
			try
			{
				String ls_query;

				ls_query     = (ab_onlyBloqueados ? cs_FIND_TURNO_BLOQUEO_BY_CIRCULO_MATRICULA_BLQ_ACT
					                              : cs_FIND_TURNO_BLOQUEO_REGISTRO_BY_CIRCULO_MATRICULA);

				lps_ps = getConnection().prepareStatement(ls_query);

				lps_ps.setString(li_count++, apr_param.getIdCirculo());
				lps_ps.setLong(li_count++, apr_param.getIdMatricula());
				lps_ps.setString(li_count++, ab_onlyBloqueados ? EstadoCommon.BLOQUEADO_TXT : EstadoCommon.ACTIVO_TXT);

				if(ab_onlyBloqueados)
				{
					lps_ps.setString(li_count++, apr_param.getIdCirculo());
					lps_ps.setLong(li_count++, apr_param.getIdMatricula());
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcs_turnosBloqueo.add(lrs_rs.getString("ID_TURNO_BLOQUEO"));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findTurnoBloqueoRegistroByCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcs_turnosBloqueo.isEmpty())
			lcs_turnosBloqueo = null;

		return lcs_turnosBloqueo;
	}

	/**
	 * Consulta en la base de datos todos los registros que estén asociados a un id círculo e id matrícula.
	 *
	 * @param apr_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @param ab_onlyBloqueados correspondiente al valor del tipo de objeto boolean
	 * @return Colección de String con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<String> findTurnoBloqueoRegistroByCirculoMatricula(
	    DatosAntSistema apr_param, boolean ab_onlyBloqueados
	)
	    throws B2BException
	{
		Collection<String> lcs_turnosBloqueo;
		int                li_count;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lcs_turnosBloqueo     = new ArrayList<String>();
		li_count              = 1;
		lps_ps                = null;
		lrs_rs                = null;

		if(apr_param != null)
		{
			try
			{
				String ls_query;

				ls_query     = (ab_onlyBloqueados ? cs_FIND_TURNO_BLOQUEO_BY_CIRCULO_MATRICULA_BLQ_ACT
					                              : cs_FIND_TURNO_BLOQUEO_REGISTRO_BY_CIRCULO_MATRICULA);

				lps_ps = getConnection().prepareStatement(ls_query);

				lps_ps.setString(li_count++, apr_param.getIdCirculo());
				lps_ps.setString(li_count++, StringUtils.getString(apr_param.getIdMatricula()));
				lps_ps.setString(li_count++, ab_onlyBloqueados ? EstadoCommon.BLOQUEADO_TXT : EstadoCommon.ACTIVO_TXT);

				if(ab_onlyBloqueados)
				{
					lps_ps.setString(li_count++, apr_param.getIdCirculo());
					lps_ps.setString(li_count++, StringUtils.getString(apr_param.getIdMatricula()));
					;
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcs_turnosBloqueo.add(lrs_rs.getString("ID_TURNO_BLOQUEO"));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findTurnoBloqueoRegistroByCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcs_turnosBloqueo.isEmpty())
			lcs_turnosBloqueo = null;

		return lcs_turnosBloqueo;
	}

	/**
	 * Busca en la tabla los registros asociados a una matrícula que no estén asociados a un turno específico
	 *
	 * @param as_idCirculo Id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula Id de la matrícula a utilizar como filtro en la consulta
	 * @param as_idTurno Id del turno a verificar que no estén asociadas las matrículas
	 * @return Colección de turnos bloqueando las matrículas
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public Collection<String> findTurnoBloqueoByCirculoMatriculaDifTurno(
	    String as_idCirculo, long al_idMatricula, String as_idTurno
	)
	    throws B2BException
	{
		Collection<String> lcs_turnosBloqueo;

		lcs_turnosBloqueo = new ArrayList<String>();

		if(StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0) && StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_TURNO_BLOQUEO_BY_CIRCULO_MATRICULA_BLQ_ACT_DIF_TURNO);

				lps_ps.setString(li_count++, as_idCirculo);
				lps_ps.setLong(li_count++, al_idMatricula);
				lps_ps.setString(li_count++, EstadoCommon.BLOQUEADO_TXT);
				lps_ps.setString(li_count++, as_idCirculo);
				lps_ps.setLong(li_count++, al_idMatricula);
				lps_ps.setString(li_count++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcs_turnosBloqueo.add(lrs_rs.getString("ID_TURNO_BLOQUEO"));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findTurnoBloqueoByCirculoMatriculaDifTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcs_turnosBloqueo.isEmpty())
			lcs_turnosBloqueo = null;

		return lcs_turnosBloqueo;
	}

	/**
	 * Extrae la información recuperada de la base de datos y la asigna a un objeto BitacoraBloqueo.
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return BitacoraBloqueo con la información extraida de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private BitacoraBloqueo getBitacoraBloqueo(ResultSet ars_rs)
	    throws SQLException
	{
		BitacoraBloqueo lbb_datos;
		lbb_datos = new BitacoraBloqueo();

		lbb_datos.setIdBitacoraBloqueo(NumericUtils.getBigInteger(ars_rs.getLong("ID_BITACORA_BLOQUEO")));
		lbb_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lbb_datos.setIdMatricula(NumericUtils.getBigInteger(ars_rs.getLong("ID_MATRICULA")));
		lbb_datos.setEstado(ars_rs.getString("ESTADO"));
		lbb_datos.setIdTurnoBloqueo(ars_rs.getString("ID_TURNO_BLOQUEO"));
		lbb_datos.setFechaInicial(ars_rs.getTimestamp("FECHA_INICIAL"));
		lbb_datos.setFechaFinal(ars_rs.getTimestamp("FECHA_FINAL"));
		lbb_datos.setUsuarioInsercion(ars_rs.getString("ID_USUARIO_CREACION"));
		lbb_datos.setFechaInsercion(ars_rs.getTimestamp("FECHA_CREACION"));
		lbb_datos.setIpInsercion(ars_rs.getString("IP_CREACION"));
		lbb_datos.setUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lbb_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lbb_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lbb_datos;
	}
}
