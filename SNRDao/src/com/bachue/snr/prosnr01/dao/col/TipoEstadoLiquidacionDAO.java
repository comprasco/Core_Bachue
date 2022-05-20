package com.bachue.snr.prosnr01.dao.col;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEstadoLiquidacion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_TIPO_AREA.
 *
 * @author Carlos Calderón
 */
public class TipoEstadoLiquidacionDAO extends BaseDAO implements IBase<TipoEstadoLiquidacion>
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_TIPO_ESTADO_LIQUIDACION";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TIPO_ESTADO_LIQUIDACION WHERE ID_TIPO_ESTADO_LIQUIDACION = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TIPO_ESTADO_LIQUIDACION SET DESCRIPCION_ESTADO_LIQUIDACION = ?, ACTIVO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TIPO_ESTADO_LIQUIDACION = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TIPO_ESTADO_LIQUIDACION (ID_TIPO_ESTADO_LIQUIDACION, DESCRIPCION_ESTADO_LIQUIDACION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_TIPO_ESTADO_LIQUIDACION_ID_TIPO_ESTADO_LIQUIDACION.NEXTVAL FROM DUAL";

	/**
	 * Retorna el valor del objeto Collection de TipoEstadoLiquidacion consultando todo en la tabla
	 *
	 * @return devuelve el valor del objeto collection de TipoEstadoLiquidacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoEstadoLiquidacion
	 */
	public Collection<TipoEstadoLiquidacion> findAll()
	    throws B2BException
	{
		Collection<TipoEstadoLiquidacion> lctel_tel;
		PreparedStatement                 lps_ps;
		ResultSet                         lrs_rs;

		lctel_tel     = new ArrayList<TipoEstadoLiquidacion>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctel_tel.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lctel_tel))
			lctel_tel = null;

		return lctel_tel;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un idTipoEstadoLiquidacion específico.
	 *
	 * @param atel_param correspondiente al valor del tipo de objeto TipoEstadoLiquidacion
	 * @return devuelve el valor del objeto tipo estado liquidacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@Override
	public TipoEstadoLiquidacion findById(TipoEstadoLiquidacion atel_param)
	    throws B2BException
	{
		TipoEstadoLiquidacion ltel_tel;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		ltel_tel     = null;
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, atel_param.getIdTipoEstadoLiquidacion());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ltel_tel = getObjetFromResultSet(lrs_rs);
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

		return ltel_tel;
	}

	/**
	 * Metodo para insertar o actualizar un registro en la base de datos de la tabla SDB_PGN_TIPO_ESTADO_LIQUIDACION.
	 *
	 * @param atel_param correspondiente al valor del tipo de objeto TipoEstadoLiquidacion
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@Override
	public void insertOrUpdate(TipoEstadoLiquidacion atel_param, boolean ab_query)
	    throws B2BException
	{
		if(atel_param != null)
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

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
						{
							atel_param.setIdTipoEstadoLiquidacion(((BigDecimal)lo_o).toString());

							lps_ps.setString(li_column++, atel_param.getIdTipoEstadoLiquidacion());
						}
						else
							throw new B2BException(ErrorKeys.PGN_TIPO_ESTADO_LIQUIDACION_SEQUENCE);
					}
				}

				lps_ps.setString(li_column++, atel_param.getDescripcionEstadoLiquidacion());
				lps_ps.setString(li_column++, atel_param.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, atel_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, atel_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, atel_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, atel_param.getIpModificacion());
					lps_ps.setString(li_column++, atel_param.getIdTipoEstadoLiquidacion());
				}

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
	}

	/**
	 * Método para la obtencion del objeto TipoEstadoLiquidacion.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de objet from result set
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TipoEstadoLiquidacion
	 */
	private TipoEstadoLiquidacion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoEstadoLiquidacion ltel_tel;

		ltel_tel = new TipoEstadoLiquidacion();

		ltel_tel.setIdTipoEstadoLiquidacion(ars_rs.getString("ID_TIPO_ESTADO_LIQUIDACION"));
		ltel_tel.setDescripcionEstadoLiquidacion(ars_rs.getString("DESCRIPCION_ESTADO_LIQUIDACION"));
		ltel_tel.setActivo(ars_rs.getString("ACTIVO"));
		ltel_tel.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ltel_tel.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ltel_tel.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ltel_tel.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ltel_tel.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ltel_tel.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return ltel_tel;
	}
}
