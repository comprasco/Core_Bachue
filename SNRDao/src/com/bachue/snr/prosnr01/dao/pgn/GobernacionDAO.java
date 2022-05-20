package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.Gobernacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_GOBERNACION.
 *
 * @author Luis Chacón
 */
public class GobernacionDAO extends com.b2bsg.common.dataAccess2.BaseDAO implements IBase<Gobernacion>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_GOBERNACION WHERE ID_GOBERNACION = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_GOBERNACION ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_GOBERNACION SET ID_PAIS = ?, ID_DEPARTAMENTO = ?,"
		+ " FECHA_MODIFICACION = SYSTIMESTAMP, NOMBRE_GOBERNACION = ?, TIPO_INTEGRACION_GOBERNACION = ?, "
		+ " ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, ACTIVO = ? WHERE ID_GOBERNACION = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_GOBERNACION (ID_GOBERNACION, "
		+ "ID_PAIS, ID_DEPARTAMENTO, NOMBRE_GOBERNACION, TIPO_INTEGRACION_GOBERNACION, "
		+ "ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ACTIVO) " + "VALUES (?,?,?,?,?,?, SYSTIMESTAMP,?,?) ";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_PGN_GOBERNACION_ID_GOBERNACION.NEXTVAL FROM DUAL";

	/**
	 * Metodo para traer de la base de datos todos los registros de la tabla SDB_PGN_GOBERNACION.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection de Gobernacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Gobernacion> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<Gobernacion> lcg_cg;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;
		StringBuilder           lsb_sbf;

		lcg_cg      = new ArrayList<Gobernacion>();
		lps_ps      = null;
		lrs_rs      = null;
		lsb_sbf     = new StringBuilder(cs_FIND_ALL);

		try
		{
			if(ab_b)
				lsb_sbf = lsb_sbf.append(" WHERE ACTIVO = 'S' ");

			lsb_sbf     = lsb_sbf.append(" ORDER BY LENGTH(ID_GOBERNACION), ID_GOBERNACION ");

			lps_ps     = getConnection().prepareStatement(lsb_sbf.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcg_cg.add(getGobernacion(lrs_rs));
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

		if(lcg_cg.isEmpty())
			lcg_cg = null;

		return lcg_cg;
	}

	/**
	 * Método para hallar Gobernación por id_gobernacion.
	 *
	 * @param ap_parametros correspondiente al valor del tipo de objeto Gobernacion
	 * @return devuelve el valor del objeto gobernacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@Override
	public Gobernacion findById(Gobernacion ap_parametros)
	    throws B2BException
	{
		Gobernacion       lg_gob;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lg_gob     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_contador++, ap_parametros.getIdGobernacion());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lg_gob = getGobernacion(lrs_rs);
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

		return lg_gob;
	}

	/**
	 * Metodo para actualizar o insertar registros en la tabla SDB_PGN_GOBERNACION.
	 *
	 * @param apt_parametros correspondiente al valor del tipo de objeto Gobernacion
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 * @ param apt_parametros true para insertar false para actualizar
	 */
	public void insertOrUpdate(Gobernacion apt_parametros, boolean ab_query)
	    throws B2BException
	{
		if(apt_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int        li_column;
				Connection lc_c;

				li_column     = 1;
				lc_c          = getConnection();
				lps_ps        = lc_c.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_c.prepareStatement(cs_FIND_SECUENCIA);
					lrs_rs            = lps_secuencia.executeQuery();

					if(lrs_rs.next())
						lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
					else
						throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);
				}

				if(ab_query)
				{
					lps_ps.setString(li_column++, apt_parametros.getIdPais());
					lps_ps.setString(li_column++, apt_parametros.getIdDepartamento());
					lps_ps.setString(li_column++, apt_parametros.getNombreGobernacion());
					lps_ps.setString(li_column++, apt_parametros.getTipoIntegracionGobernacion());
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, apt_parametros.getIpCreacion());
					lps_ps.setString(li_column++, apt_parametros.getActivo());
				}

				else
				{
					lps_ps.setString(li_column++, apt_parametros.getIdPais());
					lps_ps.setString(li_column++, apt_parametros.getIdDepartamento());
					lps_ps.setString(li_column++, apt_parametros.getNombreGobernacion());
					lps_ps.setString(li_column++, apt_parametros.getTipoIntegracionGobernacion());
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, apt_parametros.getIpModificacion());
					lps_ps.setString(li_column++, apt_parametros.getActivo());
					lps_ps.setString(li_column++, apt_parametros.getIdGobernacion());
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
				close(lrs_rs);
				close(lps_ps);
				close(lps_secuencia);
			}
		}
	}

	/**
	 * Metodo para obtener objeto de tipo Gobernacion.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de Gobernacion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Gobernacion
	 */
	private Gobernacion getGobernacion(ResultSet ars_rs)
	    throws SQLException
	{
		Gobernacion ld_datos;
		ld_datos = new Gobernacion();

		ld_datos.setIdGobernacion(ars_rs.getString("ID_GOBERNACION"));
		ld_datos.setIdPais(ars_rs.getString("ID_PAIS"));
		ld_datos.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		ld_datos.setNombreGobernacion(ars_rs.getString("NOMBRE_GOBERNACION"));
		ld_datos.setTipoIntegracionGobernacion(ars_rs.getString("TIPO_INTEGRACION_GOBERNACION"));
		ld_datos.setActivo(ars_rs.getString("ACTIVO"));
		ld_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ld_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ld_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ld_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ld_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		ld_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));

		return ld_datos;
	}
}
