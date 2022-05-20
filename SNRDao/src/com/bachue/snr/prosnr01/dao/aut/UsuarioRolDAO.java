package com.bachue.snr.prosnr01.dao.aut;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioRol;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase que contiene todos las propiedades de UsuarioRolDAO.
 *
 * @author Julian Vaca
 */
public class UsuarioRolDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM  SDB_AUT_USUARIO_ROL WHERE ID_USUARIO = ? AND ID_ROL = ? ";

	/** Constante cs_FIND_BY_ID_ROL. */
	private static final String cs_FIND_BY_ID_ROL = "SELECT * FROM  SDB_AUT_USUARIO_ROL WHERE ID_ROL = ? ";

	/** Constante cs_FIND_BY_ID_USUARIO. */
	private static final String cs_FIND_BY_ID_USUARIO = "SELECT * FROM  SDB_AUT_USUARIO_ROL WHERE ID_USUARIO = ? ";

	/** Constante cs_INSERT_USER_ROL. */
	private static final String cs_INSERT_USER_ROL = "INSERT INTO SDB_AUT_USUARIO_ROL "
		+ "(ID_USUARIO,ID_ROL,FECHA_DESDE,FECHA_HASTA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ESTADO_USUARIO) "
		+ "VALUES(?,?,?,?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante cs_UPDATE_USER_ROL. */
	private static final String cs_UPDATE_USER_ROL = "UPDATE SDB_AUT_USUARIO_ROL SET FECHA_DESDE=?, FECHA_HASTA=?, ACTIVO=?, "
		+ "ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?, ESTADO_USUARIO=? WHERE ID_USUARIO=? AND ID_ROL=?";

	/** Constante cs_UPDATE_STATE_BY_ID_USER. */
	private static final String cs_UPDATE_STATE_BY_ID_USER = "UPDATE SDB_AUT_USUARIO_ROL SET ACTIVO=?, "
		+ "ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_USUARIO=?";

	/** Constante cs_FIND_BY_ROL_LINEA_PRODUCCION. */
	private static final String cs_FIND_BY_ROL_LINEA_PRODUCCION = "SELECT UR.ID_USUARIO, "
		+ " U.PRIMER_NOMBRE || ' ' || U.SEGUNDO_NOMBRE || ' ' || U.PRIMER_APELLIDO || ' ' || U.SEGUNDO_APELLIDO NOMBRE "
		+ " FROM SDB_AUT_USUARIO_ROL UR, SDB_PGN_USUARIO_LINEA UL, SDB_AUT_USUARIO U, SDB_AUT_USUARIO_CIRCULO UC,  "
		+ " SDB_PNG_USUARIO_ETAPA UE WHERE UR.ID_ROL = ? AND UL.ID_USUARIO = UR.ID_USUARIO "
		+ " AND U.ID_USUARIO = UR.ID_USUARIO AND UC.ID_USUARIO = U.ID_USUARIO AND UE.ID_USUARIO = U.ID_USUARIO "
		+ " AND UE.ID_ETAPA = ? AND UC.ID_CIRCULO = ? AND UL.ID_LINEA_PRODUCCION = ? AND UR.ACTIVO = ? AND UC.ACTIVO = ?";

	/**
	 * Retorna el valor del objeto de tipo UsuarioRol filtrado por id usuario y id rol
	 *
	 * @param aur_param correspondiente al valor del tipo de objeto UsuarioRol
	 * @return devuelve el valor del objeto usuario rol
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioRol
	 */
	public UsuarioRol findById(UsuarioRol aur_param)
	    throws B2BException
	{
		UsuarioRol lur_return;

		lur_return = null;

		if(aur_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_cont;

			lps_ps      = null;
			lrs_rs      = null;
			li_cont     = 1;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_cont++, aur_param.getIdUsuario());
				lps_ps.setString(li_cont++, aur_param.getIdRol());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lur_return = getObjetFromResultSet(lrs_rs);
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

		return lur_return;
	}

	/**
	 * Retorna el valor del objeto de tipo UsuarioRol filtrado por el id rol.
	 *
	 * @param as_idRol de as id rol
	 * @return devuelve el valor del objeto usuario rol
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioRol
	 */
	public UsuarioRol findByIdRol(String as_idRol)
	    throws B2BException
	{
		UsuarioRol lur_return;

		lur_return = null;

		if(StringUtils.isValidString(as_idRol))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_cont;

			lps_ps      = null;
			lrs_rs      = null;
			li_cont     = 1;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ROL);

				lps_ps.setString(li_cont++, as_idRol);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lur_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdRol", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lur_return;
	}

	/**
	 * Consulta todos los registros asociados a un id usuario específico
	 *
	 * @param as_userId id del usuario a utilizar como filtro en la busqueda
	 * @return colección resultante de la busqueda
	 * @throws B2BException si ocurre un error en base de datos
	 */
	public Collection<UsuarioRol> findByIdUsuario(String as_userId)
	    throws B2BException
	{
		Collection<UsuarioRol> lcur_return;

		lcur_return = new LinkedList<UsuarioRol>();

		if(StringUtils.isValidString(as_userId))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder(cs_FIND_BY_ID_USUARIO);

				lsb_query.append(" AND ACTIVO = 'S' ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(1, as_userId);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcur_return.add(getObjetFromResultSet(lrs_rs));
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

		if(lcur_return.isEmpty())
			lcur_return = null;

		return lcur_return;
	}

	/**
	 * Consulta todos los registros asociados a un id rol, id linea produccion y activo especificos.
	 *
	 * @param as_idRol Argumento de tipo <code>String</code> que contiene el id rol.
	 * @param as_idLineaProduccion Argumento de tipo <code>String</code> que contiene el id linea producción.
	 * @param as_activo Argumento de tipo <code>String</code> que contiene el campo activo.
	 * @return colección resultante de la búsqueda
	 * @throws B2BException si ocurre un error en base de datos
	 */
	public Collection<UsuarioRol> findByRolLineaProduccion(
	    String as_idRol, long al_idEtapa, String as_idCirculo, String as_idLineaProduccion, String as_activo
	)
	    throws B2BException
	{
		Collection<UsuarioRol> lcur_return;

		lcur_return = new LinkedList<UsuarioRol>();

		if(
		    StringUtils.isValidString(as_idRol) && StringUtils.isValidString(as_idLineaProduccion)
			    && StringUtils.isValidString(as_activo)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ROL_LINEA_PRODUCCION);

				lps_ps.setString(li_contador++, as_idRol);
				lps_ps.setLong(li_contador++, al_idEtapa);
				lps_ps.setString(li_contador++, as_idCirculo);
				lps_ps.setString(li_contador++, as_idLineaProduccion);
				lps_ps.setString(li_contador++, as_activo);
				lps_ps.setString(li_contador++, as_activo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcur_return.add(getIdUsuarioNombre(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByRolLineaProduccion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcur_return.isEmpty())
			lcur_return = null;

		return lcur_return;
	}

	/**
	 * Inserta un nuevo registro de usuario rol
	 *
	 * @param aur_param correspondiente al valor del tipo de objeto UsuarioRol
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertUserRol(UsuarioRol aur_param)
	    throws B2BException
	{
		if(aur_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			li_cont     = 1;
			lps_ps      = null;
			lrs_rs      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_INSERT_USER_ROL);

				lps_ps.setString(li_cont++, aur_param.getIdUsuario());
				lps_ps.setString(li_cont++, aur_param.getIdRol());
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(aur_param.getFechaDesde()));
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(aur_param.getFechaHasta()));
				lps_ps.setString(li_cont++, aur_param.getActivo());
				lps_ps.setString(li_cont++, aur_param.getIdUsuarioCreacion());
				lps_ps.setString(li_cont++, aur_param.getIpCreacion());
				lps_ps.setString(li_cont++, aur_param.getEstadoUsuario());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertUserRol", lse_e);

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
	 * Actualiza el estado de usuario rol
	 *
	 * @param aur_param correspondiente al valor del tipo de objeto UsuarioRol
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateStateByIdUser(UsuarioRol aur_param)
	    throws B2BException
	{
		if(aur_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			li_cont     = 1;
			lps_ps      = null;
			lrs_rs      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_UPDATE_STATE_BY_ID_USER);

				lps_ps.setString(li_cont++, aur_param.getActivo());
				lps_ps.setString(li_cont++, aur_param.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, aur_param.getIpModificacion());
				lps_ps.setString(li_cont++, aur_param.getIdUsuario());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateStateByIdUser", lse_e);

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
	 * Actualiza el registro de usuario rol
	 *
	 * @param aur_param correspondiente al valor del tipo de objeto UsuarioRol
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateUserRol(UsuarioRol aur_param)
	    throws B2BException
	{
		if(aur_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			li_cont     = 1;
			lps_ps      = null;
			lrs_rs      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_UPDATE_USER_ROL);

				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(aur_param.getFechaDesde()));
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(aur_param.getFechaHasta()));
				lps_ps.setString(li_cont++, aur_param.getActivo());
				lps_ps.setString(li_cont++, aur_param.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, aur_param.getIpModificacion());
				lps_ps.setString(li_cont++, aur_param.getEstadoUsuario());
				lps_ps.setString(li_cont++, aur_param.getIdUsuario());
				lps_ps.setString(li_cont++, aur_param.getIdRol());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateUserRol", lse_e);

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
	 * Retorna el valor de UsuarioRol
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de UsuarioRol
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos u otros errores.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioRol
	 */
	private UsuarioRol getIdUsuarioNombre(ResultSet lrs_rs)
	    throws SQLException, B2BException
	{
		UsuarioRol lur_usuario;

		lur_usuario = new UsuarioRol();

		lur_usuario.setIdUsuario(lrs_rs.getString("ID_USUARIO"));
		lur_usuario.setNombre(lrs_rs.getString("NOMBRE"));

		return lur_usuario;
	}

	/**
	 * Retorna el valor de UsuarioRol
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de UsuarioRol
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos u otros errores.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioRol
	 */
	private UsuarioRol getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException, B2BException
	{
		UsuarioRol lur_usuario;

		lur_usuario = new UsuarioRol();

		lur_usuario.setIdUsuario(lrs_rs.getString("ID_USUARIO"));
		lur_usuario.setIdRol(lrs_rs.getString("ID_ROL"));
		lur_usuario.setFechaDesde(lrs_rs.getTimestamp("FECHA_DESDE"));
		lur_usuario.setFechaHasta(lrs_rs.getTimestamp("FECHA_HASTA"));
		lur_usuario.setActivo(lrs_rs.getString("ACTIVO"));
		lur_usuario.setEstadoUsuario(lrs_rs.getString("ESTADO_USUARIO"));

		fillAuditoria(lrs_rs, lur_usuario);

		return lur_usuario;
	}
}
