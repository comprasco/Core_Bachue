package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess.oracle.ClobUtils;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase para poder realizar transacciones con la base de datos en la tabla SDB_ACC_LINDERO_PREDIO.
 *
 * @author Julian Vaca
 */
public class AccLinderoPredioDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_LINDERO_PREDIO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, LINDERO, "
		+ "ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO "
		+ "FROM SDB_ACC_LINDERO_PREDIO WHERE ID_LINDERO_PREDIO = ?";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT ID_LINDERO_PREDIO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, LINDERO, "
		+ "ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO "
		+ "FROM SDB_ACC_LINDERO_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_FIND_MAX_ID_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_MAX_ID_BY_CIRCULO_MATRICULA = "SELECT max(ID_LINDERO_PREDIO) ID_LINDERO_PREDIO "
		+ "FROM SDB_ACC_LINDERO_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_TURNO. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_TURNO = "SELECT ID_LINDERO_PREDIO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, LINDERO, "
		+ "ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO "
		+ "FROM SDB_ACC_LINDERO_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO = ?";

	/** Constante cs_FIND_MAX_ID_BY_CIRCULO_MATRICULA_TURNO. */
	private static final String cs_FIND_MAX_ID_BY_CIRCULO_MATRICULA_TURNO = "SELECT max(ID_LINDERO_PREDIO) ID_LINDERO_PREDIO "
		+ "FROM SDB_ACC_LINDERO_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO = ?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_LINDERO_PREDIO_ID_LINDERO_PREDIO.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_LINDERO_PREDIO (ID_LINDERO_PREDIO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, "
		+ "LINDERO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_TURNO) VALUES(?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?, ?)";

	/** Constante cs_UPDATE_BY_ID. */
	private static final String cs_UPDATE_BY_ID = "UPDATE SDB_ACC_LINDERO_PREDIO SET LINDERO = ?, ID_USUARIO_MODIFICACION = ?, "
		+ "FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? ";

	/** Constante cs_UPDATE_ALL_BY_ID. */
	private static final String cs_UPDATE_ALL_BY_ID = "UPDATE SDB_ACC_LINDERO_PREDIO SET LINDERO = ?, ID_TURNO_HISTORIA = ?, ID_TURNO = ?, ID_USUARIO_MODIFICACION = ?, "
		+ "FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_LINDERO_PREDIO = ?";

	/**
	 * Método para encontrar todos los registros de la BD de un filtro especifico.
	 *
	 * @param aalp_param objeto AccLinderoPredio para obtener los datos para filtrar
	 * @return objeto AccLinderoPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccLinderoPredio
	 */
	public AccLinderoPredio findByCirculoMatricula(AccLinderoPredio aalp_param)
	    throws B2BException
	{
		return (aalp_param != null) ? findByCirculoMatricula(aalp_param.getIdCirculo(), aalp_param.getIdMatricula())
		                            : null;
	}

	/**
	 * Método para encontrar todos los registros de la BD de un filtro especifico.
	 *
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id de la matrícula a utilizar como filtro en la busqueda
	 * @return lindero predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccLinderoPredio
	 */
	public AccLinderoPredio findByCirculoMatricula(String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		AccLinderoPredio lalp_predio;

		lalp_predio = null;

		if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);

				lps_ps.setString(li_count++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_count++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lalp_predio = getObjetFromResultSet(lrs_rs);
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

		return lalp_predio;
	}

	/**
	 * Método para encontrar todos los registros de la BD de un filtro especifico.
	 *
	 * @param aalp_param objeto AccLinderoPredio para obtener los datos para filtrar
	 * @return objeto AccLinderoPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccLinderoPredio
	 */
	public AccLinderoPredio findByCirculoMatriculaTurno(AccLinderoPredio aalp_param)
	    throws B2BException
	{
		AccLinderoPredio  lalp_Predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_count;

		lalp_Predio     = null;
		lps_ps          = null;
		lrs_rs          = null;
		li_count        = 1;

		if(aalp_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_TURNO);

				lps_ps.setString(li_count++, aalp_param.getIdCirculo());
				setLong(lps_ps, aalp_param.getIdMatricula(), li_count++);
				lps_ps.setString(li_count++, aalp_param.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lalp_Predio = getObjetFromResultSet(lrs_rs);
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
		}

		return lalp_Predio;
	}

	/**
	 * Método para encontrar todos los registros de la BD de un filtro especifico.
	 *
	 * @param aalp_param objeto AccLinderoPredio para obtener los datos para filtrar
	 * @return objeto AccLinderoPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccLinderoPredio
	 */
	public AccLinderoPredio findById(AccLinderoPredio aalp_param)
	    throws B2BException
	{
		AccLinderoPredio  lalp_Predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lalp_Predio     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(aalp_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, aalp_param.getIdLinderoPredio());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lalp_Predio = getObjetFromResultSet(lrs_rs);
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

		return lalp_Predio;
	}

	/**
	 * Método para encontrar el máximo idLindero de la BD.
	 *
	 * @param aalp_param correspondiente al valor del tipo de objeto AccLinderoPredio
	 * @param ab_withOutIdTurno Booleana para saber que consulta usar
	 * @return String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public String findMaxIdLindero(AccLinderoPredio aalp_param, boolean ab_withOutIdTurno)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		String            ls_idLindero;

		lps_ps           = null;
		lrs_rs           = null;
		ls_idLindero     = null;

		try
		{
			if(aalp_param != null)
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection()
						         .prepareStatement(
						    ab_withOutIdTurno ? cs_FIND_MAX_ID_BY_CIRCULO_MATRICULA
						                          : cs_FIND_MAX_ID_BY_CIRCULO_MATRICULA_TURNO
						);

				lps_ps.setString(li_column++, aalp_param.getIdCirculo());
				setLong(lps_ps, aalp_param.getIdMatricula(), li_column++);

				if(!ab_withOutIdTurno)
					lps_ps.setString(li_column++, aalp_param.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_idLindero = lrs_rs.getString(1);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findMaxIdLindero", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_idLindero;
	}

	/**
	 * Método para hallar la secuencia siguiente de la BD.
	 *
	 * @return int
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int findSecuencia()
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
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCIA);

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
	 * Método para inserción de un registro en la tabla BD.
	 *
	 * @param aalp_param objeto AccLinderoPredio para obtener los datos a insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(AccLinderoPredio aalp_param)
	    throws B2BException
	{
		if(aalp_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, aalp_param.getIdLinderoPredio());
				setLong(lps_ps, aalp_param.getIdTurnoHistoria(), li_column++);
				lps_ps.setString(li_column++, aalp_param.getIdCirculo());
				setLong(lps_ps, aalp_param.getIdMatricula(), li_column++);
				lps_ps.setString(li_column++, aalp_param.getLindero());
				lps_ps.setString(li_column++, aalp_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aalp_param.getIpCreacion());
				lps_ps.setString(li_column++, aalp_param.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para actualizar todos los linderos filtrados por un id.
	 *
	 * @param aalp_param objeto AccLinderoPredio para obtener los datos para actualizarlos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateAllById(AccLinderoPredio aalp_param)
	    throws B2BException
	{
		if(aalp_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_ALL_BY_ID);

				lps_ps.setString(li_column++, aalp_param.getLindero());
				setLong(lps_ps, aalp_param.getIdTurnoHistoria(), li_column++);
				lps_ps.setString(li_column++, aalp_param.getIdTurno());
				lps_ps.setString(li_column++, aalp_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aalp_param.getIpModificacion());

				lps_ps.setString(li_column++, aalp_param.getIdLinderoPredio());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateAllById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para actualizar un registro por idLindero, o por el idCirculo, idMatricula, y idTurno.
	 *
	 * @param aalp_param objeto AccLinderoPredio para obtener los datos a actualizar
	 * @param ab_b booleana para saber agregarle una cosa o otra a la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateById(AccLinderoPredio aalp_param, boolean ab_b)
	    throws B2BException
	{
		if(aalp_param != null)
		{
			PreparedStatement lps_ps;
			StringBuilder     lsb_lsb;
			lps_ps      = null;
			lsb_lsb     = new StringBuilder(cs_UPDATE_BY_ID);

			try
			{
				int li_column;

				li_column = 1;

				if(ab_b)
					lsb_lsb.append(" WHERE ID_LINDERO_PREDIO = ?");
				else
					lsb_lsb.append(" WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO  = ?");

				lps_ps = getConnection().prepareStatement(lsb_lsb.toString());

				lps_ps.setString(li_column++, aalp_param.getLindero());
				lps_ps.setString(li_column++, aalp_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aalp_param.getIpModificacion());

				if(ab_b)
					lps_ps.setString(li_column++, aalp_param.getIdLinderoPredio());
				else
				{
					lps_ps.setString(li_column++, aalp_param.getIdCirculo());
					setLong(lps_ps, aalp_param.getIdMatricula(), li_column++);
					lps_ps.setString(li_column++, aalp_param.getIdTurno());
				}

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "UpdateById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para extraer los datos de la consulta a la BD, ponerlos en un objeto AccLinderoPredio y retornarlo.
	 *
	 * @param ars_rs objeto ResultSet con el resultado de la consulta de la BD
	 * @return objeto AccLinderoPredio
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AccLinderoPredio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AccLinderoPredio lalp_linderoPredio;

		lalp_linderoPredio = new AccLinderoPredio();

		lalp_linderoPredio.setIdLinderoPredio(ars_rs.getString("ID_LINDERO_PREDIO"));
		lalp_linderoPredio.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));
		lalp_linderoPredio.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lalp_linderoPredio.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		lalp_linderoPredio.setLindero(ClobUtils.clobToString(ars_rs.getClob("LINDERO")));
		lalp_linderoPredio.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lalp_linderoPredio.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lalp_linderoPredio.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lalp_linderoPredio.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lalp_linderoPredio.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lalp_linderoPredio.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lalp_linderoPredio.setIdTurno(ars_rs.getString("ID_TURNO"));

		return lalp_linderoPredio;
	}
}
