package com.bachue.snr.prosnr01.dao.aut;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioCirculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase que contiene todos las consultas relacionadas con la tabla SDB_AUT_USUARIO_CIRCULO
 *
 * @author Julian Vaca
 */
public class UsuarioCirculoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_USUARIO,ID_CIRCULO,FECHA_DESDE,FECHA_HASTA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_AUT_USUARIO_CIRCULO WHERE ID_USUARIO=? AND ID_CIRCULO=?";

	/** Constante cs_FIND_BY_USUARIO. */
	private static final String cs_FIND_BY_USUARIO = "SELECT ID_USUARIO,ID_CIRCULO,FECHA_DESDE,FECHA_HASTA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_AUT_USUARIO_CIRCULO WHERE ID_USUARIO=?";

	/** Constante cs_FIND_BY_USUARIO_ACTIVE. */
	private static final String cs_FIND_BY_USUARIO_ACTIVE = "SELECT ID_USUARIO,ID_CIRCULO,FECHA_DESDE,FECHA_HASTA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_AUT_USUARIO_CIRCULO WHERE ID_USUARIO=? AND ACTIVO = 'S'";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_USUARIO,ID_CIRCULO,FECHA_DESDE,FECHA_HASTA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_AUT_USUARIO_CIRCULO";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_AUT_USUARIO_CIRCULO SET "
		+ "FECHA_DESDE=?,FECHA_HASTA=?,ACTIVO=?,ID_USUARIO_MODIFICACION=?,"
		+ "FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=? WHERE ID_USUARIO=? " + "AND ID_CIRCULO=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_AUT_USUARIO_CIRCULO (ID_USUARIO,ID_CIRCULO,FECHA_DESDE,FECHA_HASTA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_INSERT_USER_CIRCLE. */
	private static final String cs_INSERT_USER_CIRCLE = "INSERT INTO SDB_AUT_USUARIO_CIRCULO "
		+ "(ID_USUARIO,ID_CIRCULO,FECHA_DESDE,FECHA_HASTA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) "
		+ "VALUES(?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE_USER_CIRCLE. */
	private static final String cs_UPDATE_USER_CIRCLE = "UPDATE SDB_AUT_USUARIO_CIRCULO SET FECHA_DESDE=?,FECHA_HASTA=?,ACTIVO=?,"
		+ "ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=? WHERE ID_USUARIO=? AND ID_CIRCULO=? ";

	/** Constante cs_UPDATE_STATE_BY_ID_USER. */
	private static final String cs_UPDATE_STATE_BY_ID_USER = "UPDATE SDB_AUT_USUARIO_CIRCULO SET ACTIVO=?, "
		+ "ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_USUARIO=?";

	/** Constante cs_CONSULTA_CIRCULO_EXISTENTE. */
	private static final String cs_CONSULTA_CIRCULO_EXISTENTE = "SELECT COUNT(0) CANTIDAD FROM SDB_AUT_USUARIO_CIRCULO WHERE ID_USUARIO=? AND ID_CIRCULO=?";

	/** Constante cs_BUSCAR_USUARIO_ACTIVO_MAS_RECIENTE. */
	private static final String cs_BUSCAR_USUARIO_ACTIVO_MAS_RECIENTE = "SELECT * FROM SDB_AUT_USUARIO_CIRCULO WHERE ID_USUARIO=? AND ACTIVO = ? ORDER BY FECHA_CREACION ";

	/**
	 * Metodo encargado de consultar el usuario mas reciente, para un estado.
	 *
	 * @param auc_param Argumento de tipo <code>UsuarioCirculo</code> que contiene los criterios necesarios para realizar la consulta.
	 * @param ab_orderByDesc Variable que indica si la consulta se realiza descendientemente (true) o ascendientemente (false).
	 * @return Objeto de tipo <code>UsuarioCirculo</code> que contiene el registro que cumple con los criterios de búsqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public UsuarioCirculo buscarUsuarioActivo(UsuarioCirculo auc_param, boolean ab_orderByDesc)
	    throws B2BException
	{
		UsuarioCirculo luc_return;

		luc_return = null;

		if(auc_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_cont;

			lps_ps      = null;
			lrs_rs      = null;
			li_cont     = 1;

			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder(cs_BUSCAR_USUARIO_ACTIVO_MAS_RECIENTE);

				lsb_query.append(ab_orderByDesc ? " DESC" : " ASC");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, auc_param.getIdUsuario());
				lps_ps.setString(li_cont++, auc_param.getActivo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					luc_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarUsuarioActivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return luc_return;
	}

	/**
	 * Metodo encargado de consultar el usuario mas reciente, para un estado.
	 *
	 * @param auc_param Argumento de tipo <code>UsuarioCirculo</code> que contiene los criterios necesarios para realizar la consulta.
	 * @return Objeto de tipo <code>UsuarioCirculo</code> que contiene el registro que cumple con los criterios de búsqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public UsuarioCirculo buscarUsuarioActivoMasReciente(UsuarioCirculo auc_param)
	    throws B2BException
	{
		return buscarUsuarioActivo(auc_param, true);
	}

	/**
	 * Metodo encargado de consultar el usuario mas antiguo, para un estado.
	 *
	 * @param auc_param Argumento de tipo <code>UsuarioCirculo</code> que contiene los criterios necesarios para realizar la consulta.
	 * @return Objeto de tipo <code>UsuarioCirculo</code> que contiene el registro que cumple con los criterios de búsqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public UsuarioCirculo buscarUsuarioActivoMasAntiguo(UsuarioCirculo auc_param)
			throws B2BException
	{
		return buscarUsuarioActivo(auc_param, false);
	}

	/**
	 * Retorna el valor del contador del circulo existente por id usuario y id circulo
	 *
	 * @param at_param correspondiente al valor del tipo de objeto UsuarioCirculo
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int contadorRegistro(UsuarioCirculo at_param)
	    throws B2BException
	{
		int li_cantidad = 0;

		if(at_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_CONSULTA_CIRCULO_EXISTENTE);
				lps_ps.setString(li_column++, at_param.getUsuario().getIdUsuario());
				lps_ps.setString(li_column++, at_param.getCirculo().getIdCirculo());
				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_cantidad = lrs_rs.getInt("CANTIDAD");
			}
			catch(SQLException lse_e)
			{
				logError(this, "contadorRegistro", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return li_cantidad;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de UsuarioCirculo del total de registros de la tabla
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioCirculo
	 */
	public Collection<UsuarioCirculo> findAll()
	    throws B2BException
	{
		Collection<UsuarioCirculo> ls_object;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		ls_object     = new LinkedList<UsuarioCirculo>();
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
	 * Retorna el valor del objeto de tipo UsuarioCirculo filtrado por ID
	 *
	 * @param auc_param correspondiente al valor del tipo de objeto UsuarioCirculo
	 * @return devuelve el valor del objeto usuario circulo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioCirculo
	 */
	public UsuarioCirculo findById(UsuarioCirculo auc_param)
	    throws B2BException
	{
		UsuarioCirculo luc_return;

		luc_return = null;

		if(auc_param != null)
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

				lps_ps.setString(li_cont++, auc_param.getIdUsuario());
				lps_ps.setString(li_cont++, auc_param.getIdCirculo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					luc_return = getObjetFromResultSet(lrs_rs);
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

		return luc_return;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de UsuarioCirculo filtrado por ID usuario
	 *
	 * @param at_param correspondiente al valor del tipo de objeto UsuarioCirculo
	 * @return devuelve el valor del objeto collection de UsuarioCirculo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioCirculo
	 */
	public Collection<UsuarioCirculo> findByUsuario(UsuarioCirculo at_param)
	    throws B2BException
	{
		Collection<UsuarioCirculo> ls_object;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		ls_object     = new LinkedList<UsuarioCirculo>();
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
	 * Retorna el valor del objeto de tipo Collection de UsuarioCirculo filtrado por ID usuario y estado S
	 *
	 * @param at_param correspondiente al valor del tipo de objeto UsuarioCirculo
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioCirculo
	 */
	public Collection<UsuarioCirculo> findByUsuarioActive(UsuarioCirculo at_param)
	    throws B2BException
	{
		Collection<UsuarioCirculo> ls_object;
		ls_object = new LinkedList<UsuarioCirculo>();

		if(at_param != null)
			ls_object = findByUsuarioActive(
				    (at_param.getUsuario() != null) ? at_param.getUsuario().getIdUsuario() : null
				);

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de UsuarioCirculo filtrado por ID usuario y estado S
	 *
	 * @param at_param correspondiente al valor del tipo de objeto UsuarioCirculo
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioCirculo
	 */
	public Collection<UsuarioCirculo> findByUsuarioActive(String ls_param)
	    throws B2BException
	{
		Collection<UsuarioCirculo> ls_object;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		ls_object     = new LinkedList<UsuarioCirculo>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_USUARIO_ACTIVE);

			lps_ps.setString(1, ls_param);

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
	 * @param at_param correspondiente al valor del tipo de objeto UsuarioCirculo
	 * @param ab_query correspondiente al valor del tipo de objeto boolean, <b>verdadero</b> para insertar un nuevo registro en la base de datos, <b>falso</b> para actualizar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(UsuarioCirculo at_param, boolean ab_query)
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
					lps_ps.setString(li_column++, at_param.getCirculo().getIdCirculo());
				}

				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(at_param.getFechaDesde()));
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(at_param.getFechaHasta()));
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
				}

				if(!ab_query)
				{
					lps_ps.setString(li_column++, at_param.getUsuario().getIdUsuario());
					lps_ps.setString(li_column++, at_param.getCirculo().getIdCirculo());
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
	 * Inserta un nuevo registro en la tabla
	 *
	 * @param auc_param correspondiente al valor del tipo de objeto UsuarioCirculo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertUserCircle(UsuarioCirculo auc_param)
	    throws B2BException
	{
		if(auc_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			li_cont     = 1;
			lps_ps      = null;
			lrs_rs      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_INSERT_USER_CIRCLE);

				lps_ps.setString(li_cont++, auc_param.getIdUsuario());
				lps_ps.setString(li_cont++, auc_param.getIdCirculo());
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(auc_param.getFechaDesde()));
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(auc_param.getFechaHasta()));
				lps_ps.setString(li_cont++, auc_param.getActivo());
				lps_ps.setString(li_cont++, auc_param.getIdUsuarioCreacion());
				lps_ps.setString(li_cont++, auc_param.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertUserCircle", lse_e);

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
	 * Actualiza el estado del registro
	 *
	 * @param aurc_param correspondiente al valor del tipo de objeto UsuarioCirculo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateStateByIdUser(UsuarioCirculo aurc_param)
	    throws B2BException
	{
		if(aurc_param != null)
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

				lps_ps.setString(li_cont++, aurc_param.getActivo());
				lps_ps.setString(li_cont++, aurc_param.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, aurc_param.getIpModificacion());
				lps_ps.setString(li_cont++, aurc_param.getIdUsuario());

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
	 * Actualiza el registro en la tabla
	 *
	 * @param auc_param correspondiente al valor del tipo de objeto UsuarioCirculo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateUserCircle(UsuarioCirculo auc_param)
	    throws B2BException
	{
		if(auc_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			li_cont     = 1;
			lps_ps      = null;
			lrs_rs      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_UPDATE_USER_CIRCLE);

				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(auc_param.getFechaDesde()));
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(auc_param.getFechaHasta()));
				lps_ps.setString(li_cont++, auc_param.getActivo());
				lps_ps.setString(li_cont++, auc_param.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, auc_param.getIpModificacion());
				lps_ps.setString(li_cont++, auc_param.getIdUsuario());
				lps_ps.setString(li_cont++, auc_param.getIdCirculo());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateUserCircle", lse_e);

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
	 * Retorna el valor de UsuarioCirculo
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de UsuarioCirculo
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioCirculo
	 */
	private UsuarioCirculo getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException, B2BException
	{
		UsuarioCirculo luc_UsuarioCirculo;

		luc_UsuarioCirculo = new UsuarioCirculo();

		luc_UsuarioCirculo.setIdUsuario(ars_rs.getString("ID_USUARIO"));
		luc_UsuarioCirculo.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		luc_UsuarioCirculo.setFechaDesde(ars_rs.getTimestamp("FECHA_DESDE"));
		luc_UsuarioCirculo.setFechaHasta(ars_rs.getTimestamp("FECHA_HASTA"));
		luc_UsuarioCirculo.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, luc_UsuarioCirculo);

		return luc_UsuarioCirculo;
	}
}
