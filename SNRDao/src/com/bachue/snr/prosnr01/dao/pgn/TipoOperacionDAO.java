package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOperacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_TIPO_OPERACION.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 14/05/2020
 */
public class TipoOperacionDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_TIPO_OPERACION ";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TIPO_OPERACION WHERE ID_TIPO_OPERACION = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TIPO_OPERACION SET NOMBRE=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_TIPO_OPERACION=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TIPO_OPERACION(ID_TIPO_OPERACION, NOMBRE, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_TIPO_OPERACION_ID_TIPO_OPERACION.NEXTVAL FROM DUAL";

	/**
	 * Find all.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoOperacion> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<TipoOperacion> ls_object;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;
		StringBuilder             lsb_sb;

		ls_object     = new ArrayList<TipoOperacion>();
		lps_ps        = null;
		lrs_rs        = null;
		lsb_sb        = new StringBuilder(cs_FIND_ALL);

		try
		{
			if(ab_b)
				lsb_sb = lsb_sb.append("WHERE ACTIVA = 'S' ");

			lsb_sb     = lsb_sb.append("ORDER BY NOMBRE ASC");

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
	 * Find by id.
	 *
	 * @param as_s de as s
	 * @return el valor de tipo operacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoOperacion findById(String as_s)
	    throws B2BException
	{
		TipoOperacion     ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_s);

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
	 * Insert.
	 *
	 * @param ato_param de ato param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(TipoOperacion ato_param)
	    throws B2BException
	{
		if(ato_param != null)
		{
			int               li_column;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			li_column         = 1;

			try
			{
				Connection lc_C;

				lc_C       = getConnection();
				lps_ps     = lc_C.prepareStatement(cs_INSERT);

				lps_secuencia     = lc_C.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				{
					lps_ps.setString(li_column++, ato_param.getNombre());
					lps_ps.setString(li_column++, ato_param.getActivo());
					lps_ps.setString(li_column++, ato_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ato_param.getIpCreacion());
				}

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
				close(lrs_rs);
				close(lps_secuencia);
			}
		}
	}

	/**
	 * Update.
	 *
	 * @param ato_param de ato param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(TipoOperacion ato_param)
	    throws B2BException
	{
		if(ato_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int           li_column;
				StringBuilder lsb_query;

				li_column     = 1;
				lsb_query     = new StringBuilder(cs_UPDATE);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, ato_param.getNombre());
				lps_ps.setString(li_column++, ato_param.getActivo());
				lps_ps.setString(li_column++, ato_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ato_param.getIpModificacion());
				lps_ps.setString(li_column++, ato_param.getIdTipoOperacion());

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
	 * Retorna el valor de TipoOperacion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TipoOperacion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TipoOperacion
	 */
	private TipoOperacion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoOperacion ls_tipoOperacion;

		ls_tipoOperacion = new TipoOperacion();

		ls_tipoOperacion.setIdTipoOperacion(ars_rs.getString("ID_TIPO_OPERACION"));
		ls_tipoOperacion.setNombre(ars_rs.getString("NOMBRE"));
		ls_tipoOperacion.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, ls_tipoOperacion);

		return ls_tipoOperacion;
	}
}
