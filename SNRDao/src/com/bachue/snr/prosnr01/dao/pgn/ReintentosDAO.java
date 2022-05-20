package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr16.model.sdb.pgn.Reintentos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_PGN_REINTENTOS
 *
 * @author Sebastian Sanchez
 *
 */
public class ReintentosDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_REINTENTOS ";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = " SELECT * FROM SDB_PGN_REINTENTOS WHERE ID_CANAL = ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_REINTENTOS(ID_CANAL, NUMERO_MAXIMO_INTENTOS, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_REINTENTOS SET NUMERO_MAXIMO_INTENTOS=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_CANAL=?";

	/**
	 * Consulta en base de datos todos los registros que se encuentren
	 * @return Colección de Reintentos resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<Reintentos> findAll()
	    throws B2BException
	{
		Collection<Reintentos> lcr_cr;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lcr_cr     = new ArrayList<Reintentos>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			StringBuilder lsb_consulta;

			lsb_consulta = new StringBuilder();

			lsb_consulta.append(cs_FIND_ALL);

			lps_ps     = getConnection().prepareStatement(lsb_consulta.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcr_cr.add(getObjetFromResultSet(lrs_rs));
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

		if(lcr_cr.isEmpty())
			lcr_cr = null;

		return lcr_cr;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador
	 *
	 * @param ar_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Origen resultante de la consulta
	 * @throws B2BException
	 */
	public Reintentos findById(Reintentos ar_param)
	    throws B2BException
	{
		return (ar_param != null) ? findById(ar_param.getIdCanal()) : null;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador
	 *
	 * @param as_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Origen resultante de la consulta
	 * @throws B2BException
	 */
	public Reintentos findById(String as_param)
	    throws B2BException
	{
		Reintentos lr_reintentos;

		lr_reintentos = null;

		if(as_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lr_reintentos = getObjetFromResultSet(lrs_rs);
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

		return lr_reintentos;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param ar_param objeto contenedor de la información a insertar
	 * @throws B2BException
	 */
	public void insert(Reintentos ar_param)
	    throws B2BException
	{
		if(ar_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int           li_column;
				StringBuilder lsb_query;

				li_column     = 1;
				lsb_query     = new StringBuilder(cs_INSERT);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, ar_param.getIdCanal());
				lps_ps.setString(li_column++, ar_param.getNumeroMaximoIntentos());
				lps_ps.setString(li_column++, ar_param.getActivo());
				lps_ps.setString(li_column++, ar_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, ar_param.getIpCreacion());

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
			}
		}
	}

	/**
	 * Actualiza un registro
	 * con la información suministrada.
	 *
	 * @param ar_param objeto contenedor de la información a insertar
	 * @throws B2BException
	 */
	public void update(Reintentos ar_param)
	    throws B2BException
	{
		if(ar_param != null)
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

				lps_ps.setString(li_column++, ar_param.getNumeroMaximoIntentos());
				lps_ps.setString(li_column++, ar_param.getActivo());
				lps_ps.setString(li_column++, ar_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ar_param.getIpModificacion());
				lps_ps.setString(li_column++, ar_param.getIdCanal());

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
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param lrs_rs contenedor del resultado de a consulta
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws SQLException
	 */
	private Reintentos getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		Reintentos ls_solicitud;

		ls_solicitud = new Reintentos();

		ls_solicitud.setIdCanal(lrs_rs.getString("ID_CANAL"));
		ls_solicitud.setNumeroMaximoIntentos(lrs_rs.getString("NUMERO_MAXIMO_INTENTOS"));
		ls_solicitud.setActivo(lrs_rs.getString("ACTIVO"));
		ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ls_solicitud.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitud.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitud.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return ls_solicitud;
	}
}
