package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr04.model.pgn.CanalOrigenServicio;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO.
 *
 * @author Carlos Calderón
 */
public class CanalOrigenServicioDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_CANAL_ORIGEN_SERVICIO";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_CANAL_ORIGEN_SERVICIO WHERE ID_CANAL_ORIGEN_SERVICIO = ?";

	/** Constante cs_FIND_BY_CODIGO. */
	private static final String cs_FIND_BY_CODIGO = "SELECT * FROM SDB_PGN_CANAL_ORIGEN_SERVICIO WHERE CODIGO_CANAL_ORIGEN_SERVICIO = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CANAL_ORIGEN_SERVICIO SET CODIGO_CANAL_ORIGEN_SERVICIO = ?, NOMBRE_CANAL_ORIGEN_SERVICIO = ?, ACTIVO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_CANAL_ORIGEN_SERVICIO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CANAL_ORIGEN_SERVICIO (ID_CANAL_ORIGEN_SERVICIO, CODIGO_CANAL_ORIGEN_SERVICIO, NOMBRE_CANAL_ORIGEN_SERVICIO, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_CANAL_ORIGEN_SERVICIO_ID_CANAL_ORIGEN_SERVICIO.NEXTVAL FROM DUAL";

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO.
	 *
	 * @return devuelve el valor del objeto collection de CanalOrigenServicio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CanalOrigenServicio> findAll()
	    throws B2BException
	{
		Collection<CanalOrigenServicio> lccos_cllCanalOrigenServicio;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lccos_cllCanalOrigenServicio     = new ArrayList<CanalOrigenServicio>();
		lps_ps                           = null;
		lrs_rs                           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccos_cllCanalOrigenServicio.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lccos_cllCanalOrigenServicio))
			lccos_cllCanalOrigenServicio = null;

		return lccos_cllCanalOrigenServicio;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un CODIGO_CANAL_ORIGEN_SERVICIO específico de la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto canal origen servicio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CanalOrigenServicio findByCodigo(String as_s)
	    throws B2BException
	{
		CanalOrigenServicio lcos_canalOrigenServicio;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcos_canalOrigenServicio     = null;
		lps_ps                       = null;
		lrs_rs                       = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_CODIGO);

			lps_ps.setString(1, as_s);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lcos_canalOrigenServicio = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByCodigo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcos_canalOrigenServicio;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un codigoMensaje específico de la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto canal origen servicio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CanalOrigenServicio findById(String as_s)
	    throws B2BException
	{
		CanalOrigenServicio lcos_canalOrigenServicio;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcos_canalOrigenServicio     = null;
		lps_ps                       = null;
		lrs_rs                       = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_s);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lcos_canalOrigenServicio = getObjetFromResultSet(lrs_rs);
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

		return lcos_canalOrigenServicio;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un codigoMensaje específico de la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param acos_param correspondiente al valor del tipo de objeto CanalOrigenServicio
	 * @return devuelve el valor del objeto canal origen servicio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CanalOrigenServicio findById(CanalOrigenServicio acos_param)
	    throws B2BException
	{
		CanalOrigenServicio lcos_canalOrigenServicio;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcos_canalOrigenServicio     = null;
		lps_ps                       = null;
		lrs_rs                       = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, acos_param.getIdCanalOrigenServicio());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lcos_canalOrigenServicio = getObjetFromResultSet(lrs_rs);
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

		return lcos_canalOrigenServicio;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param acos_param correspondiente al valor del tipo de objeto CanalOrigenServicio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(CanalOrigenServicio acos_param)
	    throws B2BException
	{
		if(acos_param != null)
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

				lps_ps     = lc_connection.prepareStatement(cs_INSERT);

				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					Object lo_o;

					lo_o = lrs_rs.getObject(1);

					if((lo_o != null) && lo_o instanceof BigDecimal)
					{
						acos_param.setIdCanalOrigenServicio(((BigDecimal)lo_o).toString());

						lps_ps.setString(li_column++, acos_param.getIdCanalOrigenServicio());
					}
					else
						throw new B2BException(ErrorKeys.PGN_CANAL_ORIGEN_SERVICIO_SEQUENCE);
				}

				lps_ps.setString(li_column++, acos_param.getCodigoCanalOrigenServicio());
				lps_ps.setString(li_column++, acos_param.getNombreCanalOrigenServicio());
				lps_ps.setString(li_column++, acos_param.getActivo());
				lps_ps.setString(li_column++, acos_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, acos_param.getIpCreacion());

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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param acos_param correspondiente al valor del tipo de objeto CanalOrigenServicio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void update(CanalOrigenServicio acos_param)
	    throws B2BException
	{
		if(acos_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps = lc_connection.prepareStatement(cs_UPDATE);
				lps_ps.setString(li_column++, acos_param.getCodigoCanalOrigenServicio());
				lps_ps.setString(li_column++, acos_param.getNombreCanalOrigenServicio());
				lps_ps.setString(li_column++, acos_param.getActivo());
				lps_ps.setString(li_column++, acos_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, acos_param.getIpModificacion());
				lps_ps.setString(li_column++, acos_param.getIdCanalOrigenServicio());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para la obtencion del objeto CanalOrigenServicio.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de CanalOrigenServicio
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see CanalOrigenServicio
	 */
	private CanalOrigenServicio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CanalOrigenServicio lcos_cos;

		lcos_cos = new CanalOrigenServicio();

		lcos_cos.setIdCanalOrigenServicio(ars_rs.getString("ID_CANAL_ORIGEN_SERVICIO"));
		lcos_cos.setCodigoCanalOrigenServicio(ars_rs.getString("CODIGO_CANAL_ORIGEN_SERVICIO"));
		lcos_cos.setNombreCanalOrigenServicio(ars_rs.getString("NOMBRE_CANAL_ORIGEN_SERVICIO"));
		lcos_cos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lcos_cos);

		return lcos_cos;
	}
}
