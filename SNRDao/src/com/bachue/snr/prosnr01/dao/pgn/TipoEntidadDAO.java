package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_TIPO_ENTIDAD
 *
 * @author garias
 */
public class TipoEntidadDAO extends BaseDAO implements IBase<TipoEntidad>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TIPO_ENTIDAD WHERE ID_TIPO_ENTIDAD=?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_TIPO_ENTIDAD";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TIPO_ENTIDAD SET NOMBRE=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP ,IP_MODIFICACION = ?  WHERE ID_TIPO_ENTIDAD=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TIPO_ENTIDAD(ID_TIPO_ENTIDAD, NOMBRE, ACTIVO, ID_USUARIO_CREACION,IP_CREACION, FECHA_CREACION)VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_TIPO_ENTIDAD_ID_TIPO_ENTIDAD.NEXTVAL FROM DUAL";

	/**
	 * Retorna el valor del objeto de tipo Collection de TipoEntidad con todos los registros
	 *
	 * @param ab_validarActivo Variable boolean que valida si se deben traer unicamente los registros con estado Activo
	 * @return devuelve el valor del objeto collection de TipoEntidad
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoEntidad
	 */
	public Collection<TipoEntidad> findAll(boolean ab_validarActivo)
	    throws B2BException
	{
		Collection<TipoEntidad> ls_object;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		ls_object     = new ArrayList<TipoEntidad>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder(cs_FIND_ALL);

			if(ab_validarActivo)
				lsb_sb.append(" WHERE ACTIVO = 'S' ");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
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

		if(ls_object.isEmpty())
			ls_object = null;

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de TipoEntidad con todos los registros
	 *
	 * @return devuelve el valor del objeto collection de TipoEntidad
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoEntidad
	 */
	public Collection<TipoEntidad> findAll()
	    throws B2BException
	{
		return findAll(false);
	}

	/** {@inheritdoc} */
	@Override
	public TipoEntidad findById(TipoEntidad ate_param)
	    throws B2BException
	{
		return (ate_param != null) ? findById(ate_param.getIdTipoEntidad()) : null;
	}

	/**
	 * Busca un registro asociado a un id específico
	 *
	 * @param as_idTipoEntidad Objeto contenedor del id a utilziar como filtro en la busqueda
	 * @return Tipo entidad resultante de la busqueda
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public TipoEntidad findById(String as_idTipoEntidad)
	    throws B2BException
	{
		TipoEntidad lte_return;

		lte_return = null;

		if(StringUtils.isValidString(as_idTipoEntidad))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idTipoEntidad);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lte_return = getObjetFromResultSet(lrs_rs);
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

		return lte_return;
	}

	/** {@inheritdoc} */
	@Override
	public void insertOrUpdate(TipoEntidad at_param, boolean ab_query)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			int li_column;
			lps_ps            = null;
			lrs_rs            = null;
			lps_secuencia     = null;
			li_column         = 1;

			try
			{
				lps_ps = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = getConnection().prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
						lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
				}

				lps_ps.setString(li_column++, at_param.getNombre());
				lps_ps.setString(li_column++, at_param.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, at_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, at_param.getIpModificacion());
					lps_ps.setString(li_column++, at_param.getIdTipoEntidad());
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
				if(lps_secuencia != null)
					close(lps_secuencia);

				close(lps_ps);
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Retorna el valor de TipoEntidad
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TipoEntidad
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TipoEntidad
	 */
	private TipoEntidad getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoEntidad ls_tipoActo;

		ls_tipoActo = new TipoEntidad();

		ls_tipoActo.setIdTipoEntidad(ars_rs.getString("ID_TIPO_ENTIDAD"));
		ls_tipoActo.setNombre(ars_rs.getString("NOMBRE"));
		ls_tipoActo.setActivo(ars_rs.getString("ACTIVO"));
		ls_tipoActo.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ls_tipoActo.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));

		return ls_tipoActo;
	}
}
