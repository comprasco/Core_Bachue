package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.model.pgn.CuentaAnalista;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_CUENTA_ANALISTA
 * @author dbeltran
 *
 */
public class CuentaAnalistaDAO extends AuditoriaDao
{
	/**Propiedad cs_FIND_SECUENCE*/
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_CUENTA_ANALISTA_ID_CUENTA_ANALISTA.NEXTVAL FROM DUAL";

	/**Propiedad cs_FIND_ALL*/
	private static final String cs_FIND_ALL = "SELECT ERCC.NOMBRE_ENTIDAD_RECAUDADORA,ERCC.ID_ENTIDAD_RECAUDADORA,CA.*,EC.NUMERO_CUENTA FROM SDB_PGN_CUENTA_ANALISTA CA "
		+ "INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA EC ON EC.ID_CUENTA = CA.ID_CUENTA "
		+ "INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION ERCC ON EC.ID_ENTIDAD_RECAUDADORA = ERCC.ID_ENTIDAD_RECAUDADORA";

	/**Propiedad cs_FIND_BY_ID*/
	private static final String cs_FIND_BY_ID = "SELECT ERCC.NOMBRE_ENTIDAD_RECAUDADORA,ERCC.ID_ENTIDAD_RECAUDADORA,CA.*,EC.NUMERO_CUENTA FROM SDB_PGN_CUENTA_ANALISTA CA "
		+ "INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA EC ON EC.ID_CUENTA = CA.ID_CUENTA "
		+ "INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION ERCC ON EC.ID_ENTIDAD_RECAUDADORA = ERCC.ID_ENTIDAD_RECAUDADORA "
		+ "WHERE CA.ID_CUENTA_ANALISTA = ?";

	/**Propiedad cs_FIND_BY_ID_CUENTA*/
	private static final String cs_FIND_BY_ID_CUENTA = "SELECT ERCC.NOMBRE_ENTIDAD_RECAUDADORA,ERCC.ID_ENTIDAD_RECAUDADORA,CA.*,EC.NUMERO_CUENTA FROM SDB_PGN_CUENTA_ANALISTA CA "
		+ "INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA EC ON EC.ID_CUENTA = CA.ID_CUENTA "
		+ "INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION ERCC ON EC.ID_ENTIDAD_RECAUDADORA = ERCC.ID_ENTIDAD_RECAUDADORA "
		+ "WHERE CA.ID_CUENTA = ?";

	/**Propiedad cs_FIND_CORREOS_BY_CUENTA*/
	private static final String cs_FIND_CORREOS_BY_CUENTA = "SELECT CORREO_ELECTRONICO_ANALISTA FROM SDB_PGN_CUENTA_ANALISTA WHERE ID_CUENTA = ?";

	/**Propiedad cs_INSERT*/
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CUENTA_ANALISTA (ID_CUENTA_ANALISTA, ID_CUENTA, ID_USUARIO, SALDO_INICIAL, FECHA_SALDO_INICIAL, ACTIVO,CORREO_ELECTRONICO_ANALISTA, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?, ?, ?, ?, ?, ?, ?,?, SYSTIMESTAMP, ?)";

	/**Propiedad cs_UPDATE*/
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CUENTA_ANALISTA SET ID_CUENTA = ?, ID_USUARIO = ?, SALDO_INICIAL = ?, FECHA_SALDO_INICIAL = ?, ACTIVO = ?,CORREO_ELECTRONICO_ANALISTA=?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_CUENTA_ANALISTA = ?";

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_CUENTA_ANALISTA
	 * @return
	 * @throws B2BException
	 */
	public Collection<CuentaAnalista> findAll()
	    throws B2BException
	{
		Collection<CuentaAnalista> lcpr_cpr;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcpr_cpr     = new ArrayList<CuentaAnalista>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

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
	 *Metodo para encontrar  los registros existentes en la tabla SDB_PGN_CUENTA_ANALISTA buscados por ID_CUENTA_ANALISTA
	 * @param atd_param
	 * @return
	 * @throws B2BException
	 */
	public CuentaAnalista findById(CuentaAnalista atd_param)
	    throws B2BException
	{
		return ((atd_param != null) ? findById(atd_param.getIdCuentaAnalista()) : null);
	}

	/**
	 * Metodo para encontrar  los registros existentes en la tabla SDB_PGN_CUENTA_ANALISTA buscados por ID_CUENTA_ANALISTA
	 * @param as_param String con el valor del ID del registro a buscar
	 * @return de tipo PeriodoCorte con el valor del registro requerido
	 * @throws B2BException
	 */
	public CuentaAnalista findById(String as_param)
	    throws B2BException
	{
		CuentaAnalista ltd_return;
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
	 * Find by id cuenta.
	 *
	 * @param as_idCuenta the as id cuenta
	 * @return the cuenta analista
	 * @throws B2BException the b 2 B exception
	 */
	public CuentaAnalista findByIdCuenta(String as_idCuenta)
	    throws B2BException
	{
		CuentaAnalista ltd_return;
		ltd_return = null;

		if(StringUtils.isValidString(as_idCuenta))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_CUENTA);

				lps_ps.setString(1, as_idCuenta);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltd_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdCuenta", lse_e);

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

	public Collection<String> findCorreosByCuenta(String as_idCuenta)
	    throws B2BException
	{
		Collection<String> ltd_return;

		ltd_return = new ArrayList<String>(1);

		if(StringUtils.isValidString(as_idCuenta))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_CORREOS_BY_CUENTA);

				lps_ps.setString(1, as_idCuenta);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ltd_return.add(lrs_rs.getString(1));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findCorreosByCuenta", lse_e);

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
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_CUENTA_ANALISTA
	 */
	public void insert(CuentaAnalista atd_param)
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

				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							atd_param.setIdCuentaAnalista(lo_o.toString());
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}
				}

				lps_ps = lc_connection.prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, atd_param.getIdCuentaAnalista());
				lps_ps.setString(li_column++, atd_param.getIdCuenta());
				lps_ps.setString(li_column++, atd_param.getIdUsuario());
				setDouble(lps_ps, atd_param.getSaldoInicial(), li_column++);
				setDate(lps_ps, atd_param.getFechaSaldoInicial(), li_column++);
				lps_ps.setString(li_column++, atd_param.getActivo());
				lps_ps.setString(li_column++, atd_param.getCorreoElectronicoAnalista());
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
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_CUENTA_ANALISTA
	 */
	public void update(CuentaAnalista atd_param)
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

				lps_ps.setString(li_column++, atd_param.getIdCuenta());
				lps_ps.setString(li_column++, atd_param.getIdUsuario());
				setDouble(lps_ps, atd_param.getSaldoInicial(), li_column++);
				setDate(lps_ps, atd_param.getFechaSaldoInicial(), li_column++);
				lps_ps.setString(li_column++, atd_param.getActivo());
				lps_ps.setString(li_column++, atd_param.getCorreoElectronicoAnalista());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atd_param.getIpModificacion());
				lps_ps.setString(li_column++, atd_param.getIdCuentaAnalista());

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
	 * @param ars_rs Argumento de tipo ResultSet que contiene los datos que serán almacenados en el objeto CuentaAnalista
	 * @return Objeto de tipo EntidadRecaudadoraCuenta con lo consultado y almacenado en un objeto ResultSet.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CuentaAnalista getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CuentaAnalista lc_datos;

		lc_datos = new CuentaAnalista();

		lc_datos.setIdEntidadRecaudadora(ars_rs.getString("ID_ENTIDAD_RECAUDADORA"));
		lc_datos.setNombreEntidadRecaudadora(ars_rs.getString("NOMBRE_ENTIDAD_RECAUDADORA"));
		lc_datos.setIdCuentaAnalista(ars_rs.getString("ID_CUENTA_ANALISTA"));
		lc_datos.setIdCuenta(ars_rs.getString("ID_CUENTA"));
		lc_datos.setIdUsuario(ars_rs.getString("ID_USUARIO"));
		lc_datos.setSaldoInicial(getDouble(ars_rs, "SALDO_INICIAL"));
		lc_datos.setFechaSaldoInicial(ars_rs.getDate("FECHA_SALDO_INICIAL"));
		lc_datos.setActivo(ars_rs.getString("ACTIVO"));
		lc_datos.setCorreoElectronicoAnalista(ars_rs.getString("CORREO_ELECTRONICO_ANALISTA"));
		lc_datos.setNumeroCuenta(ars_rs.getString("NUMERO_CUENTA"));

		fillAuditoria(ars_rs, lc_datos);

		return lc_datos;
	}
}
