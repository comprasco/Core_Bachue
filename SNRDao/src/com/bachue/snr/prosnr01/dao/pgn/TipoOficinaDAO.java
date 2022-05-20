package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_TIPO_OFICINA
 *
 * @author garias
 */
public class TipoOficinaDAO extends BaseDAO implements IBase<TipoOficina>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "  SELECT PTO.ID_TIPO_OFICINA,PTO.NOMBRE NOMBRE_OFICINA,PTO.ID_USUARIO_CREACION,PTO.FECHA_CREACION,PTO.ID_TIPO_ENTIDAD,PTE.NOMBRE,PTO.ACTIVA"
		+ " FROM SDB_PGN_TIPO_OFICINA PTO  INNER JOIN SDB_PGN_TIPO_ENTIDAD PTE ON PTE.ID_TIPO_ENTIDAD =  PTO.ID_TIPO_ENTIDAD "
		+ " WHERE PTO.ID_TIPO_OFICINA = ?  ORDER BY PTO.NOMBRE ASC";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = " SELECT PTO.ID_TIPO_OFICINA,PTO.NOMBRE NOMBRE_OFICINA,PTO.ID_USUARIO_CREACION,PTO.FECHA_CREACION,PTO.ID_TIPO_ENTIDAD,PTE.NOMBRE,PTO.ACTIVA"
		+ " FROM SDB_PGN_TIPO_OFICINA PTO INNER JOIN SDB_PGN_TIPO_ENTIDAD PTE ON PTE.ID_TIPO_ENTIDAD =  PTO.ID_TIPO_ENTIDAD";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TIPO_OFICINA SET ID_TIPO_ENTIDAD = ?,  NOMBRE=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP ,IP_MODIFICACION = ?, ACTIVA =?  WHERE ID_TIPO_OFICINA=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TIPO_OFICINA (ID_TIPO_OFICINA ,ID_TIPO_ENTIDAD, NOMBRE, ID_USUARIO_CREACION, IP_CREACION,FECHA_CREACION, ACTIVA) VALUES(? ,?, ?, ?, ?,SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_TIPO_OFICINA_ID_TIPO_OFICINA.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM  SDB_PGN_TIPO_OFICINA WHERE ACTIVA=? ";

	/**
	 * Retorna el valor del objeto de tipo Collection de TipoOficina con los registros
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean para filtrar por activa = S
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoOficina
	 */
	public Collection<TipoOficina> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<TipoOficina> ls_object;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;
		StringBuilder           lsb_sb;

		ls_object     = new ArrayList<TipoOficina>();
		lps_ps        = null;
		lrs_rs        = null;
		lsb_sb        = new StringBuilder(cs_FIND_ALL);

		try
		{
			if(ab_b)
				lsb_sb = lsb_sb.append(" WHERE ACTIVA = 'S' ");

			lsb_sb     = lsb_sb.append(" ORDER BY PTO.NOMBRE ASC");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByAll", lse_e);

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
	 * Metodo para encontrar oficinas origen que se esten activas.
	 *
	 * @param ato_parametros correspondiente al valor del tipo de objeto String
	 * @param ab_orderById correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoOficina
	 */
	public Collection<TipoOficina> findAllActivo(String ato_parametros, boolean ab_orderById)
	    throws B2BException
	{
		Collection<TipoOficina> lc_data;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lc_data     = new ArrayList<TipoOficina>();
		lps_ps      = null;
		lrs_rs      = null;

		if(ato_parametros != null)
		{
			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder(cs_FIND_ALL_ACTIVO);

				lsb_query.append(
				    ab_orderById ? " ORDER BY LENGTH(ID_TIPO_OFICINA),ID_TIPO_OFICINA " : " ORDER BY NOMBRE ASC "
				);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());
				lps_ps.setString(1, ato_parametros);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lc_data.add(getTipoOficinaFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllActivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lc_data.isEmpty())
			lc_data = null;

		return lc_data;
	}

	/** {@inheritdoc} */
	@Override
	public TipoOficina findById(TipoOficina at_param)
	    throws B2BException
	{
		TipoOficina       ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, at_param.getIdTipoOficina());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_object = getObjetFromResultSet(lrs_rs);
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

		return ls_object;
	}

	/** {@inheritdoc} */
	@Override
	public void insertOrUpdate(TipoOficina at_param, boolean ab_query)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			int               li_column;
			ResultSet         lrs_rs;
			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
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

				lps_ps.setString(li_column++, at_param.getIdTipoEntidad());
				lps_ps.setString(li_column++, at_param.getNombre());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, at_param.getIpModificacion());
					lps_ps.setString(li_column++, at_param.getActiva());
					lps_ps.setString(li_column++, at_param.getIdTipoOficina());
				}
				else
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, at_param.getIpCreacion());
					lps_ps.setString(li_column++, at_param.getActiva());
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
	 * Retorna el valor de TipoOficina
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TipoOficina
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TipoOficina
	 */
	private TipoOficina getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoOficina ls_tipoActo;

		ls_tipoActo = new TipoOficina();

		ls_tipoActo.setIdTipoOficina(ars_rs.getString("ID_TIPO_OFICINA"));
		ls_tipoActo.setNombre(ars_rs.getString("NOMBRE_OFICINA"));
		ls_tipoActo.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ls_tipoActo.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ls_tipoActo.setIdTipoEntidad(ars_rs.getString("ID_TIPO_ENTIDAD"));
		ls_tipoActo.setDescripcionTipoEntidad(ars_rs.getString("NOMBRE"));
		ls_tipoActo.setActiva(ars_rs.getString("ACTIVA"));

		return ls_tipoActo;
	}

	/**
	 * Retorna el valor de TipoOficina
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TipoOficina
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TipoOficina
	 */
	private TipoOficina getTipoOficinaFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoOficina ls_tipoActo;

		ls_tipoActo = new TipoOficina();

		ls_tipoActo.setIdTipoOficina(ars_rs.getString("ID_TIPO_OFICINA"));
		ls_tipoActo.setNombre(ars_rs.getString("NOMBRE"));
		ls_tipoActo.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ls_tipoActo.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ls_tipoActo.setIdTipoEntidad(ars_rs.getString("ID_TIPO_ENTIDAD"));
		ls_tipoActo.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ls_tipoActo.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_tipoActo.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_tipoActo.setIpModificacion((ars_rs.getString("IP_CREACION")));
		ls_tipoActo.setActiva(ars_rs.getString("ACTIVA"));

		return ls_tipoActo;
	}
}
