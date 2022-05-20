package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess.oracle.ClobUtils;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.AccComplementacionPredio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase para poder realizar transacciones con la base de datos en la tabla SDB_ACC_COMPLEMENTACION_PREDIO.
 *
 * @author Julian Vaca
 */
public class AccComplementacionPredioDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_COMPLEMENTACION, ID_TURNO_HISTORIA, "
		+ "COMPLEMENTACION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, "
		+ "ID_TURNO, ID_COMPLEMENTACION_ORIGINAL, TIPO_COMPLEMENTACION, ID_DATOS_ANT_SISTEMA FROM SDB_ACC_COMPLEMENTACION_PREDIO WHERE ID_COMPLEMENTACION = ?";

	/** Constante cs_FIND_BY_ID_ORIGINAL. */
	private static final String cs_FIND_BY_ID_ORIGINAL = "SELECT ID_COMPLEMENTACION, ID_TURNO_HISTORIA, "
		+ "COMPLEMENTACION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, "
		+ "ID_TURNO, ID_COMPLEMENTACION_ORIGINAL, TIPO_COMPLEMENTACION, ID_DATOS_ANT_SISTEMA FROM SDB_ACC_COMPLEMENTACION_PREDIO WHERE ID_COMPLEMENTACION_ORIGINAL = ? AND ID_TURNO = ?";

	/** Constante cs_FIND_BY_ID_TURNO. */
	private static final String cs_FIND_BY_ID_TURNO = "SELECT ID_COMPLEMENTACION, ID_TURNO_HISTORIA, "
		+ "COMPLEMENTACION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, "
		+ "ID_TURNO, ID_COMPLEMENTACION_ORIGINAL, TIPO_COMPLEMENTACION, ID_DATOS_ANT_SISTEMA FROM SDB_ACC_COMPLEMENTACION_PREDIO WHERE ID_TURNO = ? ";

	/** Constante cs_FIND_COMPLEM_BY_ID_TURNO. */
	private static final String cs_FIND_COMPLEM_BY_ID_TURNO = "SELECT DISTINCT(ID_COMPLEMENTACION_ORIGINAL) ID_COMPLEMENTACION_ORIGINAL, "
		+ "ID_COMPLEMENTACION, ID_TURNO_HISTORIA, COMPLEMENTACION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, "
		+ "FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO, TIPO_COMPLEMENTACION, ID_DATOS_ANT_SISTEMA FROM SDB_ACC_COMPLEMENTACION_PREDIO "
		+ "WHERE ID_TURNO = ? ";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_COMPLEMENTACION_PREDIO_ID_COMPLEMENTACION.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT_COMPLEMENTACION. */
	private static final String cs_INSERT_COMPLEMENTACION = "INSERT INTO SDB_ACC_COMPLEMENTACION_PREDIO(ID_COMPLEMENTACION, "
		+ "ID_TURNO_HISTORIA, COMPLEMENTACION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_TURNO,ID_COMPLEMENTACION_ORIGINAL,TIPO_COMPLEMENTACION, ID_DATOS_ANT_SISTEMA) "
		+ "VALUES (?,?,?,?,SYSTIMESTAMP,?,?,?,?,?)";

	/** Constante cs_UPDATE_BY_ID. */
	private static final String cs_UPDATE_BY_ID = "UPDATE SDB_ACC_COMPLEMENTACION_PREDIO SET COMPLEMENTACION = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ?, ID_COMPLEMENTACION_ORIGINAL = ?, TIPO_COMPLEMENTACION = ?, ID_DATOS_ANT_SISTEMA = ? WHERE ID_COMPLEMENTACION = ? ";

	/** Constante cs_UPDATE_COMPLEMENTACION. */
	private static final String cs_UPDATE_COMPLEMENTACION = "UPDATE SDB_ACC_COMPLEMENTACION_PREDIO SET COMPLEMENTACION = ?, TIPO_COMPLEMENTACION = ?,"
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_COMPLEMENTACION = ? "
		+ "AND ID_TURNO_HISTORIA = ? AND ID_TURNO = ?";

	/**
	 * Método para poder obtener un registro por el IdComplementacion.
	 *
	 * @param aacp_param correspondiente al valor del tipo de objeto AccComplementacionPredio
	 * @return devuelve el valor de AccComplementacionPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccComplementacionPredio
	 */
	public AccComplementacionPredio findById(AccComplementacionPredio aacp_param)
	    throws B2BException
	{
		AccComplementacionPredio lacp_object;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lacp_object     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(aacp_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				setLong(lps_ps, aacp_param.getIdComplementacion(), 1);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lacp_object = getObjetFromResultSet(lrs_rs);
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

		return lacp_object;
	}

	/**
	 * Método para poder obtener un registro por el IdComplementacionOriginal.
	 *
	 * @param aacp_complemento correspondiente al valor del tipo de objeto AccComplementacionPredio
	 * @return devuelve el valor de AccComplementacionPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccComplementacionPredio
	 */
	public AccComplementacionPredio findByIdOriginal(AccComplementacionPredio aacp_complemento)
	    throws B2BException
	{
		AccComplementacionPredio lacp_object;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;
		int                      li_count;

		lacp_object     = null;
		lps_ps          = null;
		lrs_rs          = null;
		li_count        = 1;

		if(aacp_complemento != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ORIGINAL);

				setLong(lps_ps, aacp_complemento.getIdComplementacionOriginal(), li_count++);
				lps_ps.setString(li_count++, aacp_complemento.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lacp_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdOriginal", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lacp_object;
	}

	/**
	 * Método para poder obtener un registro por el IdTurno.
	 *
	 * @param aacp_param correspondiente al valor del tipo de objeto AccComplementacionPredio
	 * @return devuelve el valor de AccComplementacionPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccComplementacionPredio
	 */
	public AccComplementacionPredio findByIdTurno(AccComplementacionPredio aacp_param)
	    throws B2BException
	{
		AccComplementacionPredio lacp_object;

		lacp_object = null;

		if(aacp_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				int           li_cont;
				String        ls_idDatosAntSistema;
				boolean       lb_idDatosAntSistema;

				lsb_query                = new StringBuilder(cs_FIND_BY_ID_TURNO);
				li_cont                  = 1;
				ls_idDatosAntSistema     = aacp_param.getIdDatosAntSistema();
				lb_idDatosAntSistema     = StringUtils.isValidString(ls_idDatosAntSistema);

				if(lb_idDatosAntSistema)
					lsb_query.append(" AND ID_DATOS_ANT_SISTEMA = ? ");

				lsb_query.append(" ORDER BY FECHA_CREACION DESC ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, aacp_param.getIdTurno());

				if(lb_idDatosAntSistema)
					lps_ps.setString(li_cont++, ls_idDatosAntSistema);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lacp_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lacp_object;
	}

	/**
	 * Método para poder obtener un registro por el IdTurno.
	 *
	 * @param aacp_param correspondiente al valor del tipo de objeto AccComplementacionPredio
	 * @return devuelve el valor de AccComplementacionPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccComplementacionPredio
	 */
	public AccComplementacionPredio findComplemByIdTurno(AccComplementacionPredio aacp_param)
	    throws B2BException
	{
		AccComplementacionPredio lacp_object;

		lacp_object = null;

		if(aacp_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				int           li_cont;
				String        ls_idDatosAntSistema;
				boolean       lb_idDatosAntSistema;

				lsb_query                = new StringBuilder(cs_FIND_COMPLEM_BY_ID_TURNO);
				li_cont                  = 1;
				ls_idDatosAntSistema     = aacp_param.getIdDatosAntSistema();
				lb_idDatosAntSistema     = StringUtils.isValidString(ls_idDatosAntSistema);

				if(lb_idDatosAntSistema)
					lsb_query.append(" AND ID_DATOS_ANT_SISTEMA = ? ");

				lsb_query.append(" ORDER BY FECHA_CREACION DESC ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, aacp_param.getIdTurno());

				if(lb_idDatosAntSistema)
					lps_ps.setString(li_cont++, ls_idDatosAntSistema);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lacp_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lacp_object;
	}

	/**
	 * Método para encontrar la secuencia de la tabla.
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
	 * Método para poder insertar un registro el la tabla.
	 *
	 * @param aacp_dp correspondiente al valor del tipo de objeto AccComplementacionPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(AccComplementacionPredio aacp_dp)
	    throws B2BException
	{
		if(aacp_dp != null)
		{
			AccComplementacionPredio lacp_complementacion;
			int                      li_cont;
			PreparedStatement        lps_ps;
			ResultSet                lrs_rs;

			lacp_complementacion     = aacp_dp;
			li_cont                  = 1;
			lps_ps                   = null;
			lrs_rs                   = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_INSERT_COMPLEMENTACION);

				setLong(lps_ps, lacp_complementacion.getIdComplementacion(), li_cont++);
				lps_ps.setLong(li_cont++, lacp_complementacion.getIdTurnoHistoria());
				lps_ps.setString(li_cont++, lacp_complementacion.getComplementacion());
				lps_ps.setString(li_cont++, lacp_complementacion.getIdUsuarioCreacion());
				lps_ps.setString(li_cont++, lacp_complementacion.getIpCreacion());
				lps_ps.setString(li_cont++, lacp_complementacion.getIdTurno());
				setLong(lps_ps, lacp_complementacion.getIdComplementacionOriginal(), li_cont++);
				lps_ps.setString(li_cont++, lacp_complementacion.getTipoComplementacion());
				lps_ps.setString(li_cont++, lacp_complementacion.getIdDatosAntSistema());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para poder actualizar un registro en la tabla.
	 *
	 * @param aacp_cp objeto AccComplementacionPredio para poder obtener el registro a actualizar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void update(AccComplementacionPredio aacp_cp)
	    throws B2BException
	{
		if(aacp_cp != null)
		{
			AccComplementacionPredio lacp_complemento;
			int                      li_cont;
			PreparedStatement        lps_ps;
			ResultSet                lrs_rs;

			lacp_complemento     = aacp_cp;
			li_cont              = 1;
			lps_ps               = null;
			lrs_rs               = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_UPDATE_COMPLEMENTACION);

				lps_ps.setString(li_cont++, lacp_complemento.getComplementacion());
				lps_ps.setString(li_cont++, lacp_complemento.getTipoComplementacion());
				lps_ps.setString(li_cont++, lacp_complemento.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, lacp_complemento.getIpModificacion());
				setLong(lps_ps, lacp_complemento.getIdComplementacion(), li_cont++);
				lps_ps.setLong(li_cont++, lacp_complemento.getIdTurnoHistoria());
				lps_ps.setString(li_cont++, lacp_complemento.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para poder actualizar un registro en la tabla.
	 *
	 * @param aacp_cp objeto AccComplementacionPredio para poder obtener el registro a actualizar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateById(AccComplementacionPredio aacp_cp)
	    throws B2BException
	{
		if(aacp_cp != null)
		{
			AccComplementacionPredio lacp_complemento;
			int                      li_cont;
			PreparedStatement        lps_ps;
			ResultSet                lrs_rs;

			lacp_complemento     = aacp_cp;
			li_cont              = 1;
			lps_ps               = null;
			lrs_rs               = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_UPDATE_BY_ID);

				lps_ps.setString(li_cont++, lacp_complemento.getComplementacion());
				lps_ps.setString(li_cont++, lacp_complemento.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, lacp_complemento.getIpModificacion());
				setLong(lps_ps, lacp_complemento.getIdComplementacionOriginal(), li_cont++);
				lps_ps.setString(li_cont++, lacp_complemento.getTipoComplementacion());
				lps_ps.setString(li_cont++, lacp_complemento.getIdDatosAntSistema());
				setLong(lps_ps, lacp_complemento.getIdComplementacion(), li_cont++);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para poder llenar un objeto AccComplementacionPredio con un resultSet.
	 *
	 * @param lrs_rs Objeto ResultSet de lo que retorna la consulta a la base de datos
	 * @return el valor de objet from result set
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private AccComplementacionPredio getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException, B2BException
	{
		AccComplementacionPredio lacp_complemento;

		lacp_complemento = new AccComplementacionPredio();

		lacp_complemento.setIdComplementacion(getLong(lrs_rs, "ID_COMPLEMENTACION"));
		lacp_complemento.setIdTurnoHistoria(lrs_rs.getLong("ID_TURNO_HISTORIA"));
		lacp_complemento.setComplementacion(ClobUtils.clobToString(lrs_rs.getClob("COMPLEMENTACION")));
		lacp_complemento.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		lacp_complemento.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		lacp_complemento.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		lacp_complemento.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		lacp_complemento.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		lacp_complemento.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));
		lacp_complemento.setIdTurno(lrs_rs.getString("ID_TURNO"));
		lacp_complemento.setIdComplementacionOriginal(getLong(lrs_rs, "ID_COMPLEMENTACION_ORIGINAL"));
		lacp_complemento.setTipoComplementacion(lrs_rs.getString("TIPO_COMPLEMENTACION"));
		lacp_complemento.setIdDatosAntSistema(lrs_rs.getString("ID_DATOS_ANT_SISTEMA"));

		return lacp_complemento;
	}
}
