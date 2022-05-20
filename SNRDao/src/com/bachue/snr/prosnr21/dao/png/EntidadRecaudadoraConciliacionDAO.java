package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION.
 *
 * @author dbeltran
 */
public class EntidadRecaudadoraConciliacionDAO extends AuditoriaDao
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION (ID_ENTIDAD_RECAUDADORA, NOMBRE_ENTIDAD_RECAUDADORA, CODIGO_ENTIDAD_RECAUDADORA, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION SET NOMBRE_ENTIDAD_RECAUDADORA = ?, CODIGO_ENTIDAD_RECAUDADORA = ?, ACTIVO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_ENTIDAD_RECAUDADORA = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION WHERE ACTIVO='S'";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION WHERE ID_ENTIDAD_RECAUDADORA = ?";

	/** Constante cs_FIND_BY_CODIGO. */
	private static final String cs_FIND_BY_CODIGO = "SELECT * FROM SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION WHERE CODIGO_ENTIDAD_RECAUDADORA = ?";

	/** Constante cs_FIND_BY_ID_ANALISTA. */
	private static final String cs_FIND_BY_ID_ANALISTA = "SELECT DISTINCT ER.* " + "FROM SDB_PGN_CUENTA_ANALISTA CA "
		+ "INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA EC " + "ON EC.ID_CUENTA = CA.ID_CUENTA "
		+ "INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION ER "
		+ "ON EC.ID_ENTIDAD_RECAUDADORA = ER.ID_ENTIDAD_RECAUDADORA " + "WHERE CA.ID_USUARIO = ?";

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION.
	 *
	 * @param ab_activo the ab activo
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<EntidadRecaudadoraConciliacion> findAll(boolean ab_activo)
	    throws B2BException
	{
		Collection<EntidadRecaudadoraConciliacion> lcpr_cpr;
		PreparedStatement                          lps_ps;
		ResultSet                                  lrs_rs;

		lcpr_cpr     = new ArrayList<EntidadRecaudadoraConciliacion>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			StringBuilder lsb_query;

			lsb_query = new StringBuilder();

			lsb_query.append(cs_FIND_ALL);

			if(ab_activo)
				lsb_query.append(" WHERE ACTIVO = 'S'");

			lps_ps     = getConnection().prepareStatement(lsb_query.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpr_cpr.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		if(!CollectionUtils.isValidCollection(lcpr_cpr))
			lcpr_cpr = null;

		return lcpr_cpr;
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION.
	 *
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<EntidadRecaudadoraConciliacion> findAllActivo()
	    throws B2BException
	{
		Collection<EntidadRecaudadoraConciliacion> lcpr_cpr;
		PreparedStatement                          lps_ps;
		ResultSet                                  lrs_rs;

		lcpr_cpr     = new ArrayList<EntidadRecaudadoraConciliacion>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpr_cpr.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		if(!CollectionUtils.isValidCollection(lcpr_cpr))
			lcpr_cpr = null;

		return lcpr_cpr;
	}

	/**
	 * Find by codigo.
	 *
	 * @param as_param the as param
	 * @return the entidad recaudadora conciliacion
	 * @throws B2BException the b 2 B exception
	 */
	public EntidadRecaudadoraConciliacion findByCodigo(String as_param)
	    throws B2BException
	{
		EntidadRecaudadoraConciliacion ltd_return;
		ltd_return = null;

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CODIGO);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltd_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCodigo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ltd_return;
	}

	/**
	 * Metodo para encontrar  los registros existentes en la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION buscados por ID_ENTIDAD_RECAUDADORA_CONCILIACION.
	 *
	 * @param atd_param the atd param
	 * @return the entidad recaudadora conciliacion
	 * @throws B2BException the b 2 B exception
	 */
	public EntidadRecaudadoraConciliacion findById(EntidadRecaudadoraConciliacion atd_param)
	    throws B2BException
	{
		return ((atd_param != null) ? findById(atd_param.getIdEntidadRecaudadora()) : null);
	}

	/**
	 * Metodo para encontrar  los registros existentes en la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION buscados por ID_ENTIDAD_RECAUDADORA_CONCILIACION.
	 *
	 * @param as_param String con el valor del ID del registro a buscar
	 * @return de tipo PeriodoCorte con el valor del registro requerido
	 * @throws B2BException the b 2 B exception
	 */
	public EntidadRecaudadoraConciliacion findById(String as_param)
	    throws B2BException
	{
		EntidadRecaudadoraConciliacion ltd_return;
		ltd_return = null;

		if(StringUtils.isValidString(as_param))
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
					ltd_return = getObjetFromResultSet(lrs_rs);
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

		return ltd_return;
	}

	/**
	 * Metodo para encontrar  los registros existentes en la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION buscados por ID DEL ANALISTA de la consulta.
	 *
	 * @param as_param String con el valor del id del analista del registro a buscar
	 * @return de tipo Entidad Recaudadora Conciliacion con el valor del registro requerido
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<EntidadRecaudadoraConciliacion> findByIdAnalista(String as_param)
	    throws B2BException
	{
		Collection<EntidadRecaudadoraConciliacion> lcpr_cpr;

		lcpr_cpr = new ArrayList<EntidadRecaudadoraConciliacion>();

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ANALISTA);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcpr_cpr.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdAnalista", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcpr_cpr;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION.
	 *
	 * @param atd_param the atd param
	 * @throws B2BException the b 2 B exception
	 */
	public void insert(EntidadRecaudadoraConciliacion atd_param)
	    throws B2BException
	{
		if(atd_param != null)
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

				lps_ps = lc_connection.prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, atd_param.getIdEntidadRecaudadora());
				lps_ps.setString(li_column++, atd_param.getNombreEntidadRecaudadora());
				lps_ps.setString(li_column++, atd_param.getCodigoEntidadRecaudadora());
				lps_ps.setString(li_column++, atd_param.getActivo());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, atd_param.getIpCreacion());

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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION.
	 *
	 * @param atd_param the atd param
	 * @throws B2BException the b 2 B exception
	 */
	public void update(EntidadRecaudadoraConciliacion atd_param)
	    throws B2BException
	{
		if(atd_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, atd_param.getNombreEntidadRecaudadora());
				lps_ps.setString(li_column++, atd_param.getCodigoEntidadRecaudadora());
				lps_ps.setString(li_column++, atd_param.getActivo());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atd_param.getIpModificacion());
				lps_ps.setString(li_column++, atd_param.getIdEntidadRecaudadora());

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
	 * Metodo que se encarga de llenar un objeto de tipo PeriodoCorte con lo consultado y almacenado en un objeto ResultSet.
	 *
	 * @param ars_rs Argumento de tipo ResultSet que contiene los datos que serán almacenados en el objeto PeriodoCorte
	 * @return Objeto de tipo PeriodoCorte con lo consultado y almacenado en un objeto ResultSet.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private EntidadRecaudadoraConciliacion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		EntidadRecaudadoraConciliacion lc_datos;

		lc_datos = new EntidadRecaudadoraConciliacion();

		lc_datos.setIdEntidadRecaudadora(ars_rs.getString("ID_ENTIDAD_RECAUDADORA"));
		lc_datos.setNombreEntidadRecaudadora(ars_rs.getString("NOMBRE_ENTIDAD_RECAUDADORA"));
		lc_datos.setCodigoEntidadRecaudadora(ars_rs.getString("CODIGO_ENTIDAD_RECAUDADORA"));
		lc_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lc_datos);

		return lc_datos;
	}
}
