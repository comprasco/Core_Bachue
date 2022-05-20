package com.bachue.snr.prosnr01.dao.col;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.col.TipoUsoSuelo;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas para la tabla SDB_COL_TIPO_USO_SUELO de la base de datos
 *
 * @author Julian Vaca
 */
public class TipoUsoSueloDAO extends BaseDAO implements IBase<TipoUsoSuelo>
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_COL_TIPO_USO_SUELO ";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_COL_TIPO_USO_SUELO WHERE ID_TIPO_USO_SUELO = ? ";

	/** Constante CS_FIND_BY_DESCRIPTION. */
	private static final String CS_FIND_BY_DESCRIPTION = "SELECT ID_TIPO_USO_SUELO, ILICODE, ACTIVO, DESCRIPTION, ID_USUARIO_CREACION, FECHA_CREACION "
		+ "FROM SDB_COL_TIPO_USO_SUELO WHERE DESCRIPTION = ? ORDER BY DESCRIPTION ASC";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_COL_TIPO_USO_SUELO SET ILICODE=?, ACTIVO=?, "
		+ "DESCRIPTION=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TIPO_USO_SUELO=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_COL_TIPO_USO_SUELO (ID_TIPO_USO_SUELO, ILICODE, "
		+ "ACTIVO, DESCRIPTION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_COL_TIPO_USO_SUELO_ID_TIPO_USO_SUELO .NEXTVAL FROM DUAL";

	/**
	 * Retorna el valor del objeto de tipo Collection de TipoUsoSuelo cuando Activo = S
	 * ordenado por descripcion
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection de TipoUsoSuelo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoUsoSuelo
	 */
	public Collection<TipoUsoSuelo> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<TipoUsoSuelo> lctus_return;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;
		StringBuilder            lsb_sb;

		lctus_return     = new ArrayList<TipoUsoSuelo>();
		lps_ps           = null;
		lrs_rs           = null;
		lsb_sb           = new StringBuilder(cs_FIND_ALL);

		try
		{
			if(ab_b)
				lsb_sb.append("  WHERE ACTIVO = 'S' ");

			lsb_sb.append("  ORDER BY DESCRIPTION ASC");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctus_return.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lctus_return))
			lctus_return = null;

		return lctus_return;
	}

	/**
	 * Retorna el valor del objeto de tipo TipoUsoSuelo consultado por descripcion
	 *
	 * @param atus_param correspondiente al valor del tipo de objeto TipoUsoSuelo
	 * @return devuelve el valor del objeto tipo uso suelo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoUsoSuelo
	 */
	public TipoUsoSuelo findByDescription(TipoUsoSuelo atus_param)
	    throws B2BException
	{
		TipoUsoSuelo      ltus_tus;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ltus_tus     = null;
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps = getConnection().prepareStatement(CS_FIND_BY_DESCRIPTION);

			lps_ps.setString(1, atus_param.getDescription());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ltus_tus = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByDescription", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ltus_tus;
	}

	/** {@inheritdoc} */
	@Override
	public TipoUsoSuelo findById(TipoUsoSuelo atus_param)
	    throws B2BException
	{
		return (atus_param != null) ? findById(atus_param.getIdTipoUsoSuelo()) : null;
	}

	/**
	 * Consulta un registro asociado a un id específico
	 *
	 * @param as_idTipoUsoSuelo id del tipo uso suelo a utilizar como filtro en la busqueda
	 * @return TipoUsoSuelo resultante de la busqueda
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public TipoUsoSuelo findById(String as_idTipoUsoSuelo)
	    throws B2BException
	{
		TipoUsoSuelo ltus_tus;

		ltus_tus = null;

		if(StringUtils.isValidString(as_idTipoUsoSuelo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idTipoUsoSuelo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltus_tus = getObjetFromResultSet(lrs_rs);
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

		return ltus_tus;
	}

	/** {@inheritdoc} */
	@Override
	public void insertOrUpdate(TipoUsoSuelo atus_param, boolean ab_query)
	    throws B2BException
	{
		if(atus_param != null)
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
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCIA);
					lrs_rs            = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							atus_param.setIdTipoUsoSuelo(((BigDecimal)lo_o).toString());

						lps_ps.setString(li_column++, atus_param.getIdTipoUsoSuelo());
					}
				}

				lps_ps.setString(li_column++, atus_param.getIlicode());
				lps_ps.setString(li_column++, atus_param.getActivo());
				lps_ps.setString(li_column++, atus_param.getDescription());

				if(ab_query)
				{
					lps_ps.setString(li_column++, atus_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, atus_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, atus_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, atus_param.getIpModificacion());
					lps_ps.setString(li_column++, atus_param.getIdTipoUsoSuelo());
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
	 * Retorna el valor de TipoUsoSuelo
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de objet TipoUsoSuelo
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TipoUsoSuelo
	 */
	private TipoUsoSuelo getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		TipoUsoSuelo ltus_return;

		ltus_return = new TipoUsoSuelo();

		ltus_return.setIdTipoUsoSuelo(lrs_rs.getString("ID_TIPO_USO_SUELO"));
		ltus_return.setIlicode(lrs_rs.getString("ILICODE"));
		ltus_return.setActivo(lrs_rs.getString("ACTIVO"));
		ltus_return.setDescription(lrs_rs.getString("DESCRIPTION"));
		ltus_return.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ltus_return.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ltus_return.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ltus_return.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ltus_return.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ltus_return.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return ltus_return;
	}
}
