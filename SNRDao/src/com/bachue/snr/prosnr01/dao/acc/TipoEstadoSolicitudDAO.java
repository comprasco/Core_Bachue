package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoEstadoSolicitud;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de SDB_ACC_TIPO_ESTADO_SOLICITUD
 *
 * @author Julian Vaca
 */
public class TipoEstadoSolicitudDAO extends BaseDAO
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_ESTADO_SOLICITUD, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO FROM SDB_ACC_TIPO_ESTADO_SOLICITUD";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_ESTADO_SOLICITUD, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO FROM SDB_ACC_TIPO_ESTADO_SOLICITUD WHERE ACTIVO = 'S'";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_ESTADO_SOLICITUD, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO FROM SDB_ACC_TIPO_ESTADO_SOLICITUD WHERE ID_ESTADO_SOLICITUD=?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_TIPO_ESTADO_SOLICITUD_ID_ESTADO_SOLICITUD.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_TIPO_ESTADO_SOLICITUD (ID_ESTADO_SOLICITUD, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ACTIVO) VALUES (?,?,?,SYSTIMESTAMP,?,?) ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_TIPO_ESTADO_SOLICITUD SET NOMBRE=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?, ACTIVO=? WHERE ID_ESTADO_SOLICITUD=?";

	/**
	 * Retorna el valor del objeto de Collection de TipoEstadoSolicitud con todos los registros.
	 *
	 * @return devuelve el valor de Collection de TipoEstadoSolicitud
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<TipoEstadoSolicitud> findAll()
	    throws B2BException
	{
		Collection<TipoEstadoSolicitud> lctc_tiposCausales;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lctc_tiposCausales     = new ArrayList<TipoEstadoSolicitud>();
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctc_tiposCausales.add(getObjectFromResultSet(lrs_rs));
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

		if(lctc_tiposCausales.isEmpty())
			lctc_tiposCausales = null;

		return lctc_tiposCausales;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoEstadoSolicitud filtrado por estado solicitud activo.
	 *
	 * @return devuelve el valor de Collection de TipoEstadoSolicitud
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<TipoEstadoSolicitud> findAllActivo()
	    throws B2BException
	{
		Collection<TipoEstadoSolicitud> lctes_tiposEstSol;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lctes_tiposEstSol     = new ArrayList<TipoEstadoSolicitud>();
		lps_ps                = null;
		lrs_rs                = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctes_tiposEstSol.add(getObjectFromResultSet(lrs_rs));
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

		if(lctes_tiposEstSol.isEmpty())
			lctes_tiposEstSol = null;

		return lctes_tiposEstSol;
	}

	/**
	 * Retorna el valor del objeto de TipoEstadoSolicitud.
	 *
	 * @param atc_parametros correspondiente al valor del tipo de objeto TipoEstadoSolicitud
	 * @return devuelve el valor de TipoEstadoSolicitud
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoEstadoSolicitud
	 */
	public TipoEstadoSolicitud findById(TipoEstadoSolicitud atc_parametros)
	    throws B2BException
	{
		TipoEstadoSolicitud lta_tipoEstadoSolicitud;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lta_tipoEstadoSolicitud     = null;
		lps_ps                      = null;
		lrs_rs                      = null;

		if(atc_parametros != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, atc_parametros.getIdTipoEstadoSolicitud());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lta_tipoEstadoSolicitud = getObjectFromResultSet(lrs_rs);
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

		return lta_tipoEstadoSolicitud;
	}

	/**
	 * Insert or update.
	 *
	 * @param ata_parametros correspondiente al valor del tipo de objeto TipoEstadoSolicitud
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(TipoEstadoSolicitud ata_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ata_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					Connection lc_connection;

					lc_connection     = getConnection();

					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
						{
							ata_parametros.setIdTipoEstadoSolicitud(((BigDecimal)lo_o).toString());

							lps_ps.setString(li_column++, ata_parametros.getIdTipoEstadoSolicitud());
						}
						else
							throw new B2BException(ErrorKeys.ACC_TIPO_ESTADO_SOLICITUD_SECUENCE);
					}
				}

				lps_ps.setString(li_column++, ata_parametros.getNombre());

				if(ab_query)
				{
					lps_ps.setString(li_column++, ata_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ata_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ata_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ata_parametros.getIpModificacion());
				}

				lps_ps.setString(li_column++, ata_parametros.getActivo());

				if(!ab_query)
					lps_ps.setString(li_column++, ata_parametros.getIdTipoEstadoSolicitud());

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
	 * Retorna el valor de TipoEstadoSolicitud
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TipoEstadoSolicitud
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoEstadoSolicitud getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoEstadoSolicitud lta_tipoEstadoSolicitud;

		lta_tipoEstadoSolicitud = new TipoEstadoSolicitud();

		lta_tipoEstadoSolicitud.setIdTipoEstadoSolicitud(ars_rs.getString("ID_ESTADO_SOLICITUD"));
		lta_tipoEstadoSolicitud.setNombre(ars_rs.getString("NOMBRE"));
		lta_tipoEstadoSolicitud.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lta_tipoEstadoSolicitud.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lta_tipoEstadoSolicitud.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lta_tipoEstadoSolicitud.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lta_tipoEstadoSolicitud.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lta_tipoEstadoSolicitud.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lta_tipoEstadoSolicitud.setActivo(ars_rs.getString("ACTIVO"));

		return lta_tipoEstadoSolicitud;
	}
}
