package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoIntegracionGobernacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_TIPO_INTEGRACION_GOBERNACION.
 *
 * @author Luis Chacón
 */
public class TipoIntegracionGobernacionDAO extends com.b2bsg.common.dataAccess2.BaseDAO implements IBase<TipoIntegracionGobernacion>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TIPO_INTEGRACION_GOBERNACION WHERE ID_TIPO_INTEGRACION_GOBERNACION = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_TIPO_INTEGRACION_GOBERNACION ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TIPO_INTEGRACION_GOBERNACION SET "
		+ " FECHA_MODIFICACION = SYSTIMESTAMP, DESCRIPCION = ?, ID_USUARIO_MODIFICACION = ?,"
		+ " IP_MODIFICACION = ?, ACTIVO = ? WHERE ID_TIPO_INTEGRACION_GOBERNACION = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TIPO_INTEGRACION_GOBERNACION (ID_TIPO_INTEGRACION_GOBERNACION, "
		+ "DESCRIPCION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ACTIVO) "
		+ "VALUES (?,?,?,SYSTIMESTAMP,?,?) ";

	/**
	 * Metodo para traer de la base de datos todos los registros de la tabla SDB_PGN_TIPO_INTEGRACION_GOBERNACION.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TipoIntegracionGobernacion> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<TipoIntegracionGobernacion> lp_procesoAut;
		PreparedStatement                      lps_ps;
		ResultSet                              lrs_rs;
		StringBuilder                           lsb_sbf;

		lp_procesoAut     = new ArrayList<TipoIntegracionGobernacion>();
		lps_ps            = null;
		lrs_rs            = null;
		lsb_sbf           = new StringBuilder(cs_FIND_ALL);

		try
		{
			if(ab_b)
				lsb_sbf = lsb_sbf.append(" WHERE ACTIVO = 'S' ORDER BY ID_TIPO_INTEGRACION_GOBERNACION ASC");
			else
				lsb_sbf = lsb_sbf.append(" ORDER BY ID_TIPO_INTEGRACION_GOBERNACION ASC ");

			lps_ps     = getConnection().prepareStatement(lsb_sbf.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_procesoAut.add(getTipoIntegracionGobernacion(lrs_rs));
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

		if(lp_procesoAut.isEmpty())
			lp_procesoAut = null;

		return lp_procesoAut;
	}

	/** {@inheritdoc} */
	@Override
	public TipoIntegracionGobernacion findById(TipoIntegracionGobernacion ap_parametros)
	    throws B2BException
	{
		TipoIntegracionGobernacion ltig_tig;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		ltig_tig     = null;
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_contador++, ap_parametros.getIdTipoIntegracion());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ltig_tig = getTipoIntegracionGobernacion(lrs_rs);
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

		return ltig_tig;
	}

	/**
	 * Metodo para actualizar o insertar registros en la tabla SDB_PGN_TIPO_INTEGRACION_GOBERNACION.
	 *
	 * @param apt_parametros correspondiente al valor del tipo de objeto TipoIntegracionGobernacion
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 * @ param apt_parametros true para insertar false para actualizar
	 */
	public void insertOrUpdate(TipoIntegracionGobernacion apt_parametros, boolean ab_query)
	    throws B2BException
	{
		if(apt_parametros != null)
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
					lps_ps.setString(li_column++, apt_parametros.getIdTipoIntegracion());
					lps_ps.setString(li_column++, apt_parametros.getDescripcion());
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, apt_parametros.getIpCreacion());
					lps_ps.setString(li_column++, apt_parametros.getActivo());
				}

				if(!ab_query)
				{
					lps_ps.setString(li_column++, apt_parametros.getDescripcion());
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, apt_parametros.getIpModificacion());
					lps_ps.setString(li_column++, apt_parametros.getActivo());
					lps_ps.setString(li_column++, apt_parametros.getIdTipoIntegracion());
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
	 * Metodo para obtener objeto de tipo TipoIntegracionGobernacion.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de tipo integracion gobernacion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoIntegracionGobernacion getTipoIntegracionGobernacion(ResultSet ars_rs)
	    throws SQLException
	{
		TipoIntegracionGobernacion ld_datos;
		ld_datos = new TipoIntegracionGobernacion();

		ld_datos.setIdTipoIntegracion(ars_rs.getString("ID_TIPO_INTEGRACION_GOBERNACION"));
		ld_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
		ld_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ld_datos.setActivo(ars_rs.getString("ACTIVO"));
		ld_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ld_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ld_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ld_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		ld_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));

		return ld_datos;
	}
}
