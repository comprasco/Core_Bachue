package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.FestivoNacional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_FESTIVO_NACIONAL.
 *
 * @author Luis Chacón
 */
public class FestivoNacionalDAO extends com.b2bsg.common.dataAccess2.BaseDAO implements IBase<FestivoNacional>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_FESTIVO_NACIONAL WHERE ID_FESTIVO_NACIONAL = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_FESTIVO_NACIONAL ";

	/* TODO Quita referencia a esquema de CB */
	/**  Constante cs_FIND_ALL_EN_RANGO. */
	private static final String cs_FIND_ALL_EN_RANGO = "SELECT * FROM C##SDB.SDB_PGN_FESTIVO_NACIONAL WHERE FECHA "
		+ "BETWEEN ? AND ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_FESTIVO_NACIONAL SET  FECHA=?, "
		+ " FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?,"
		+ " IP_MODIFICACION = ? WHERE ID_FESTIVO_NACIONAL = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_FESTIVO_NACIONAL (ID_FESTIVO_NACIONAL, "
		+ "FECHA, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) " + "VALUES (?,?,?,SYSTIMESTAMP,?) ";

	/**
	 * Metodo para traer de la base de datos todos los registros de la tabla SDB_PGN_FESTIVO_NACIONAL.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection de FestivoNacional
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<FestivoNacional> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<FestivoNacional> lp_procesoAut;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;
		StringBuilder               lsb_sbf;

		lp_procesoAut     = new ArrayList<FestivoNacional>();
		lps_ps            = null;
		lrs_rs            = null;
		lsb_sbf           = new StringBuilder(cs_FIND_ALL);

		try
		{
			if(ab_b)
				lsb_sbf = lsb_sbf.append(" ORDER BY ID_FESTIVO_NACIONAL ASC");
			else
				lsb_sbf = lsb_sbf.append(" ORDER BY ID_FESTIVO_NACIONAL ASC ");

			lps_ps     = getConnection().prepareStatement(lsb_sbf.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_procesoAut.add(getFestivoNacional(lrs_rs));
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

	/**
	 * Método encargado de buscar el rango que hay entre dos fechas.
	 *
	 * @param ad_fechaInicial Argumento de tipo <code>Date</code> que contiene la fecha inicial de la operación.
	 * @param ad_fechaFinal Argumento de tipo <code>Date</code> que contiene la fecha final de la operación.
	 * @return el valor Variable de tipo <code>Collection</code> de Date que contiene el rango entre las dos fechas.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<java.util.Date> findAllEnRango(java.util.Date ad_fechaInicial, java.util.Date ad_fechaFinal)
	    throws B2BException
	{
		Collection<java.util.Date> lcd_festivos;

		lcd_festivos = new ArrayList<java.util.Date>();

		if((ad_fechaInicial != null) && (ad_fechaFinal != null))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_EN_RANGO);

				setDate(lps_ps, ad_fechaInicial, li_contador++);
				setDate(lps_ps, ad_fechaFinal, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcd_festivos.add(lrs_rs.getDate("FECHA"));
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

		return lcd_festivos;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ap_parametros de ap parametros
	 * @return el valor de festivo nacional
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public FestivoNacional findById(FestivoNacional ap_parametros)
	    throws B2BException
	{
		FestivoNacional   lfn_festivo;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lfn_festivo     = null;
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_contador++, ap_parametros.getIdFestivoNacional());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lfn_festivo = getFestivoNacional(lrs_rs);
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

		return lfn_festivo;
	}

	/**
	 * Metodo para actualizar o insertar registros en la tabla SDB_PGN_FESTIVO_NACIONAL.
	 *
	 * @param ac_parametros correspondiente al valor del tipo de objeto FestivoNacional
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 * @ param apt_parametros true para insertar false para actualizar
	 */
	@Override
	public void insertOrUpdate(FestivoNacional ac_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ac_parametros != null)
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
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setString(li_column++, ac_parametros.getIdFestivoNacional());
					lps_ps.setDate(li_column++, DateUtils.getCleanSQLDate(ac_parametros.getFecha()));
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ac_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setDate(li_column++, DateUtils.getCleanSQLDate(ac_parametros.getFecha()));
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIpModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIdFestivoNacional());
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

				if(ab_query)
				{
					close(lps_secuencia);
					close(lrs_rs);
				}
			}
		}
	}

	/**
	 * Método para obtener objeto de tipo festivo nacional.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de festivo nacional
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private FestivoNacional getFestivoNacional(ResultSet ars_rs)
	    throws SQLException
	{
		FestivoNacional ld_datos;
		ld_datos = new FestivoNacional();

		ld_datos.setIdFestivoNacional(ars_rs.getString("ID_FESTIVO_NACIONAL"));
		ld_datos.setFecha(ars_rs.getTimestamp("FECHA"));
		ld_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ld_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ld_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ld_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ld_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ld_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return ld_datos;
	}
}
