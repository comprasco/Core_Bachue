package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.pgn.UsuarioLinea;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_USUARIO_LINEA
 *
 * @author jpatino
 */
public class UsuarioLineaDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_USUARIO,ID_LINEA_PRODUCCION,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_PGN_USUARIO_LINEA WHERE ID_USUARIO=? AND ID_LINEA_PRODUCCION=?";

	/** Constante cs_FIND_BY_USUARIO. */
	private static final String cs_FIND_BY_USUARIO = "SELECT ID_USUARIO,ID_LINEA_PRODUCCION,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_PGN_USUARIO_LINEA WHERE ID_USUARIO=?";

	/** Constante cs_FIND_BY_USUARIO_ACTIVE. */
	private static final String cs_FIND_BY_USUARIO_ACTIVE = "SELECT ID_USUARIO,ID_LINEA_PRODUCCION,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_PGN_USUARIO_LINEA WHERE ID_USUARIO=? AND ACTIVO = 'S'";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_USUARIO,ID_LINEA_PRODUCCION,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_PGN_USUARIO_LINEA";

	/** Constante cs_FIND_BY_ROL_LINEA_PROD_AND_DATE. */
	private static final String cs_FIND_BY_ROL_LINEA_PROD_AND_DATE = "SELECT PUL.* FROM SDB_AUT_USUARIO AU INNER JOIN SDB_AUT_USUARIO_ROL AUR ON AUR.ID_USUARIO = AU.ID_USUARIO INNER JOIN SDB_PGN_USUARIO_LINEA PUL ON PUL.ID_USUARIO = AU.ID_USUARIO WHERE AUR.ID_ROL = ? AND PUL.ID_LINEA_PRODUCCION = ? AND AU.ACTIVO = 'S' AND AUR.ACTIVO = 'S' AND PUL.ACTIVO = 'S' AND PUL.FECHA_CREACION > TO_DATE(?, 'DD/MM/YYYY')";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_USUARIO_LINEA SET ACTIVO=?,ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=? WHERE ID_USUARIO=? AND ID_LINEA_PRODUCCION=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_USUARIO_LINEA(ID_USUARIO,ID_LINEA_PRODUCCION,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION)VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**
	 * Retorna el valor del objeto de tipo Collection de UsuarioLinea sin filtros para obtener todos los registros
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioLinea
	 */
	public Collection<UsuarioLinea> findAll()
	    throws B2BException
	{
		Collection<UsuarioLinea> ls_object;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		ls_object     = new LinkedList<UsuarioLinea>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
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

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de tipo UsuarioLinea filtrado por ID
	 *
	 * @param at_param correspondiente al valor del tipo de objeto UsuarioLinea
	 * @return devuelve el valor del objeto usuario linea
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioLinea
	 */
	public UsuarioLinea findById(UsuarioLinea at_param)
	    throws B2BException
	{
		UsuarioLinea      ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_column++, at_param.getUsuario().getIdUsuario());
			lps_ps.setString(li_column++, at_param.getLineaProduccion().getIdLineaProduccion());

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

	/**
	 * Find by rol linea produccion y por la fecha.
	 *
	 * @param as_idRol de as id rol
	 * @param as_idLineaProduccion de as id linea produccion
	 * @param ad_fecha de ad fecha
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<UsuarioLinea> findByRolLineaProdAndFecha(
	    String as_idRol, String as_idLineaProduccion, Date ad_fecha
	)
	    throws B2BException
	{
		Collection<UsuarioLinea> ls_object;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		ls_object     = new LinkedList<UsuarioLinea>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ROL_LINEA_PROD_AND_DATE);

			lps_ps.setString(li_column++, as_idRol);
			lps_ps.setString(li_column++, as_idLineaProduccion);
			setDate(lps_ps, ad_fecha, li_column++);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByRolLineaProdAndFecha", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de UsuarioLinea filtrado por usuario
	 *
	 * @param at_param correspondiente al valor del tipo de objeto UsuarioLinea
	 * @return devuelve el valor del objeto collection de UsuarioLinea
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioLinea
	 */
	public Collection<UsuarioLinea> findByUsuario(UsuarioLinea at_param)
	    throws B2BException
	{
		Collection<UsuarioLinea> ls_object;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		ls_object     = new LinkedList<UsuarioLinea>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_USUARIO);

			lps_ps.setString(1, at_param.getUsuario().getIdUsuario());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByUsuario", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection UsuarioLinea filtrado por usuario activo
	 *
	 * @param at_param correspondiente al valor del tipo de objeto UsuarioLinea
	 * @return devuelve el valor del objeto collection de UsuarioLinea
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioLinea
	 */
	public Collection<UsuarioLinea> findByUsuarioActive(UsuarioLinea at_param)
	    throws B2BException
	{
		Collection<UsuarioLinea> ls_object;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		ls_object     = new LinkedList<UsuarioLinea>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_USUARIO_ACTIVE);

			lps_ps.setString(1, at_param.getUsuario().getIdUsuario());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByUsuarioActive", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_object;
	}

	/**
	 * Inserta o Actualiza en la base de datos el tipo que se recibe por parametro
	 *
	 * @param at_param correspondiente al valor del tipo de objeto UsuarioLinea
	 * @param ab_query <b>verdadero</b> para insertar un nuevo registro en la base de datos, <b>falso</b> para actualizar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(UsuarioLinea at_param, boolean ab_query)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setString(li_column++, at_param.getUsuario().getIdUsuario());
					lps_ps.setString(li_column++, at_param.getLineaProduccion().getIdLineaProduccion());
				}

				lps_ps.setString(li_column++, at_param.getActivo());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, at_param.getIpModificacion());
					lps_ps.setString(li_column++, at_param.getUsuario().getIdUsuario());
					lps_ps.setString(li_column++, at_param.getLineaProduccion().getIdLineaProduccion());
				}
				else
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, at_param.getIpCreacion());
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
			}
		}
	}

	/**
	 * Retorna el valor de UsuarioLinea
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de UsuarioLinea
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see UsuarioLinea
	 */
	private UsuarioLinea getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		UsuarioLinea lul_usuarioLinea;

		lul_usuarioLinea = new UsuarioLinea();

		lul_usuarioLinea.getUsuario().setIdUsuario(ars_rs.getString("ID_USUARIO"));
		lul_usuarioLinea.getLineaProduccion().setIdLineaProduccion(ars_rs.getString("ID_LINEA_PRODUCCION"));
		lul_usuarioLinea.setActivo(ars_rs.getString("ACTIVO"));
		lul_usuarioLinea.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lul_usuarioLinea.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lul_usuarioLinea.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lul_usuarioLinea.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lul_usuarioLinea.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lul_usuarioLinea.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lul_usuarioLinea;
	}
}
