package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.model.sdb.png.UsuarioEtapa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase que contiene todos las consultas relacionadas a la tabla SDB_PNG_USUARIO_ETAPA de la base de datos.
 *
 * @author jpatino
 */
public class UsuarioEtapaDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_USUARIO,ID_ETAPA,ACUMULADO,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_CREACION,IP_MODIFICACION FROM SDB_PNG_USUARIO_ETAPA WHERE ID_USUARIO=? AND ID_ETAPA=?";

	/** Constante cs_FIND_BY_USUARIO. */
	private static final String cs_FIND_BY_USUARIO = "SELECT ID_USUARIO,ID_ETAPA,ACUMULADO,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_CREACION,IP_MODIFICACION FROM SDB_PNG_USUARIO_ETAPA WHERE ID_USUARIO=?";

	/** Constante cs_FIND_BY_USUARIO_ACTIVE. */
	private static final String cs_FIND_BY_USUARIO_ACTIVE = "SELECT ID_USUARIO,ID_ETAPA,ACUMULADO,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_CREACION,IP_MODIFICACION FROM SDB_PNG_USUARIO_ETAPA WHERE ID_USUARIO=? AND ACTIVO = 'S'";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_USUARIO,ID_ETAPA,ACUMULADO,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_CREACION,IP_MODIFICACION FROM SDB_PNG_USUARIO_ETAPA";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PNG_USUARIO_ETAPA SET ACUMULADO=?,ACTIVO=?,ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=? WHERE ID_USUARIO=? AND ID_ETAPA=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PNG_USUARIO_ETAPA (ID_USUARIO,ID_ETAPA,ACUMULADO,ACTIVO,ID_USUARIO_CREACION,IP_CREACION,FECHA_CREACION) VALUES (?, ?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_UPDATE_STATE_BY_ID_USER. */
	private static final String cs_UPDATE_STATE_BY_ID_USER = "UPDATE SDB_PNG_USUARIO_ETAPA SET ACTIVO=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_USUARIO=? ";

	/** Constante cs_BUSCAR_ETAPAS_AUTORIZADAS_POR_USUARIO. */
	private static final String cs_BUSCAR_ETAPAS_AUTORIZADAS_POR_USUARIO = "SELECT DISTINCT TO_CHAR(E.ID_ETAPA) AS ID_ETAPA FROM SDB_AUT_USUARIO U INNER JOIN SDB_AUT_USUARIO_ROL UR ON (UR.ID_USUARIO = U.ID_USUARIO AND NVL(UR.ACTIVO,'S') = 'S') INNER JOIN SDB_AUT_ROL R ON (R.ID_ROL = UR.ID_ROL AND NVL(R.ACTIVO,'S') = 'S') INNER JOIN SDB_AUT_ROL_OPCION RO ON (RO.ID_ROL = R.ID_ROL AND NVL(RO.ACTIVO,'S') = 'S') INNER JOIN SDB_AUT_OPCION O ON (O.ID_OPCION = RO.ID_OPCION AND NVL(O.ACTIVO,'S') = 'S') INNER JOIN SDB_AUT_OPCION_ETAPA OE ON (OE.ID_OPCION = O.ID_OPCION) INNER JOIN SDB_PGN_ETAPA E ON (E.ID_ETAPA = OE.ID_ETAPA AND NVL(E.ESTADO,'A') = 'A') WHERE U.ID_USUARIO = ? AND NVL(U.ACTIVO,'S') = 'S' ORDER BY 1 ASC";

	/** Constante cs_BUSCAR_ETAPAS_EXISTENTES_POR_USUARIO. */
	private static final String cs_BUSCAR_ETAPAS_EXISTENTES_POR_USUARIO = "SELECT COUNT(0) FROM  SDB_PNG_USUARIO_ETAPA WHERE ID_USUARIO = ? AND ID_ETAPA = ?";

	/**
	 * Retorna las etapas autorizadas para un determinado usuario.
	 *
	 * @param as_idUsuario correspondiente al ID_USUARIO a buscar
	 * @return devuelve el valor del objeto Collection<String>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<String> buscarEtapasAutorizadas(String as_idUsuario)
	    throws B2BException
	{
		Collection<String> lcs_retorno;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lcs_retorno     = new ArrayList<String>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_BUSCAR_ETAPAS_AUTORIZADAS_POR_USUARIO);

			lps_ps.setString(1, as_idUsuario);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcs_retorno.add(lrs_rs.getString("ID_ETAPA"));
		}
		catch(SQLException lse_e)
		{
			logError(this, "buscarEtapasAutorizadas", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcs_retorno;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de tipo UsuarioEtapa.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioEtapa
	 */
	public Collection<UsuarioEtapa> findAll()
	    throws B2BException
	{
		Collection<UsuarioEtapa> ls_object;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		ls_object     = new LinkedList<UsuarioEtapa>();
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
	 * Retorna el valor del objeto de tipo UsuarioEtapa consultado por ID.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto UsuarioEtapa
	 * @return devuelve el valor del objeto usuario etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioEtapa
	 */
	public UsuarioEtapa findById(UsuarioEtapa at_param)
	    throws B2BException
	{
		UsuarioEtapa      ls_object;
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
			lps_ps.setLong(li_column++, at_param.getEtapa().getIdEtapa());

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
	 * Retorna el valor del objeto de tipo Collection que contiene objetos de tipo UsuarioEtapa
	 * consultados por eñ usuario.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto UsuarioEtapa
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioEtapa
	 */
	public Collection<UsuarioEtapa> findByUsuario(UsuarioEtapa at_param)
	    throws B2BException
	{
		Collection<UsuarioEtapa> ls_object;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		ls_object     = new LinkedList<UsuarioEtapa>();
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
	 * Retorna el valor del objeto de tipo Collection que contiene objetos de tipo UsuarioEtapa
	 * consultados si el usuario esta activo.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto UsuarioEtapa
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioEtapa
	 */
	public Collection<UsuarioEtapa> findByUsuarioActive(UsuarioEtapa at_param)
	    throws B2BException
	{
		Collection<UsuarioEtapa> ls_object;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		ls_object     = new LinkedList<UsuarioEtapa>();
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
	 * Inserta o actualiza el usuario de acuerto a los parametros recibidos.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto UsuarioEtapa
	 * @param ab_query correspondiente al valor del tipo de objeto boolean, instar un nuevo registro si el valor es verdadero,
	 * de lo contrario actualiza el registro enviado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see UsuarioEtapa
	 */
	public void insertOrUpdate(UsuarioEtapa at_param, boolean ab_query)
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
					lps_ps.setLong(li_column++, at_param.getEtapa().getIdEtapa());
				}

				lps_ps.setLong(li_column++, at_param.getAcumulado());
				lps_ps.setString(li_column++, at_param.getActivo());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, at_param.getIpModificacion());
					lps_ps.setString(li_column++, at_param.getUsuario().getIdUsuario());
					lps_ps.setLong(li_column++, at_param.getEtapa().getIdEtapa());
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
	 * Actualiza el estado del registro.
	 *
	 * @param aue_param correspondiente al valor del tipo de objeto UsuarioEtapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateStateByIdUser(UsuarioEtapa aue_param)
	    throws B2BException
	{
		updateStateByIdUser(aue_param, false);
	}

	/**
	 * Actualiza el estado del registro.
	 *
	 * @param aue_param correspondiente al valor del tipo de objeto UsuarioEtapa
	 * @param ab_etapa valida si el Query se modifica con ID_ETAPA
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateStateByIdUser(UsuarioEtapa aue_param, boolean ab_etapa)
	    throws B2BException
	{
		if(aue_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			li_cont     = 1;
			lps_ps      = null;
			lrs_rs      = null;

			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder(cs_UPDATE_STATE_BY_ID_USER);

				if(ab_etapa)
					lsb_query.append(" AND ID_ETAPA = ? ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, aue_param.getActivo());
				lps_ps.setString(li_cont++, aue_param.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, aue_param.getIpModificacion());
				lps_ps.setString(li_cont++, aue_param.getIdUsuario());

				if(ab_etapa)
					lps_ps.setLong(li_cont++, NumericUtils.getLong(aue_param.getIdEtapa()));

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
	 * Retorna las etapas autorizadas para un determinado usuario.
	 *
	 * @param as_idUsuario correspondiente al ID_USUARIO a buscar
	 * @param ls_idEtapa correspondiente al ID_ETAPA a buscar
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean verificarExistenciaUsuarioEtapa(String as_idUsuario, String ls_idEtapa)
	    throws B2BException
	{
		boolean           lb_retorno;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lb_retorno     = false;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_BUSCAR_ETAPAS_EXISTENTES_POR_USUARIO);

			lps_ps.setString(1, as_idUsuario);
			lps_ps.setString(2, ls_idEtapa);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lb_retorno = lrs_rs.getBoolean(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "verificarExistenciaUsuarioEtapa", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lb_retorno;
	}

	/**
	 * Retorna el valor de UsuarioEtapa contenido en el ResultSet.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de UsuarioEtapa
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see UsuarioEtapa
	 */
	private UsuarioEtapa getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		UsuarioEtapa lue_UsuarioEtapa;

		lue_UsuarioEtapa = new UsuarioEtapa();

		lue_UsuarioEtapa.getUsuario().setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO"));
		lue_UsuarioEtapa.getEtapa().setIdEtapa(ars_rs.getLong("ID_ETAPA"));
		lue_UsuarioEtapa.setAcumulado(ars_rs.getLong("ACUMULADO"));
		lue_UsuarioEtapa.setActivo(ars_rs.getString("ACTIVO"));
		lue_UsuarioEtapa.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lue_UsuarioEtapa.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lue_UsuarioEtapa.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lue_UsuarioEtapa.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lue_UsuarioEtapa.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lue_UsuarioEtapa.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lue_UsuarioEtapa;
	}
}
