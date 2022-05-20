package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;

import com.bachue.snr.prosnr21.model.pgn.MulticashCabecera;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 * Clase encargada de las interacciones con base de datos para la tabla SDB_CON_MULTICASH_CABECERA del módulo de conciliaciones
 *
 * @author Edgar Prieto
 */
public class MulticashCabeceraDAO extends com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao
{
	/** Sentencia SQL para la inserción de un registro */
	private static final String cs_INSERTAR = "INSERT INTO SDB_CON_MULTICASH_CABECERA(ID_ARCHIVO,ID_CUENTA,"
		+ "ID_PERIODO_CORTE,CLAVE_MONEDA,SALDO_INICIAL,TOTAL_DEBITOS,TOTAL_CREDITOS,SALDO_FINAL,CANTIDAD_MOVIMIENTO,"
		+ "ESTADO,ID_USUARIO_CREACION,IP_CREACION,FECHA_CREACION)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP)";

	/**Constante cs_ENCONTRAR_POR_ID*/
	private static final String cs_ENCONTRAR_POR_ID = "SELECT * FROM SDB_CON_MULTICASH_CABECERA WHERE ID_ARCHIVO=?";

	/**Constante cs_FIND_BY_CUENTA_PERIODO*/
	private static final String cs_FIND_BY_CUENTA_PERIODO = "SELECT * FROM SDB_CON_MULTICASH_CABECERA WHERE ID_CUENTA=? AND ID_PERIODO_CORTE=?";

	/**Constante cs_FIND_ALL_VISTA*/
	private static final String cs_FIND_VISTA = "SELECT * FROM VW_TMP_MULTICASH_ENCABEZADO WHERE TRUNC(FECHA_ORIGEN_MOVIMIENTO) = TRUNC(TO_DATE(?,'DD/MM/YY')) AND CLAVE_BANCO = ? AND CUENTA_BANCARIA = ?";

	/** Constante cs_PROC_CREA_MULTICASH. */
	private static final String cs_PROC_CREA_MULTICASH = "BEGIN PROC_CREA_MULTICASH(?,?,?,?,?,?,?); END;";

	/**
	 * Crear multicash.
	 *
	 * @param as_idEntidad the as id entidad
	 * @param as_cuenta the as cuenta
	 * @param as_idTipoCuenta the as id tipo cuenta
	 * @param ad_fecha the ad fecha
	 * @param ad_fsaldo the ad saldo
	 * @throws B2BException the b 2 B exception
	 */
	public void crearMulticash(
	    String as_idEntidad, String as_cuenta, String as_idTipoCuenta, Date ad_fecha, Double ad_saldo
	)
	    throws B2BException
	{
		CallableStatement lcs_cs;

		lcs_cs = null;

		try
		{
			int    li_i;
			int    li_return;
			String ls_error;

			li_i          = 1;
			li_return     = 0;
			lcs_cs        = getConnection().prepareCall(cs_PROC_CREA_MULTICASH);

			lcs_cs.setString(li_i++, as_idEntidad);
			lcs_cs.setString(li_i++, as_cuenta);
			lcs_cs.setString(li_i++, as_idTipoCuenta);
			lcs_cs.setObject(li_i++, ad_fecha, OracleTypes.DATE);
			lcs_cs.setObject(li_i++, ad_saldo, OracleTypes.DOUBLE);
			lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
			lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
			lcs_cs.execute();

			li_return     = lcs_cs.getInt(6);
			ls_error      = lcs_cs.getString(7);

			if(li_return < 0)
				throw new B2BException("Error en procedimiento ROC_CREA_MULTICASH :" + li_return + " :: " + ls_error);
		}
		catch(SQLException lse_e)
		{
			logError(this, "crearMulticash", lse_e);
			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lcs_cs);
		}
	}

	/**
	 * Metodo para encontrar un registro de la tabla SDB_CON_MULTICASH_CABECERA por llave primaria
	 * @param as_id Llave primaria del registro a encontrar
	 * @return registro de la tabla SDB_CON_MULTICASH_CABECERA que coincide con la llave primaria
	 * @throws B2BException
	 */
	public MulticashCabecera encontrarPorId(String as_id)
	    throws B2BException
	{
		MulticashCabecera lmc_respuesta;

		lmc_respuesta = null;

		if(com.b2bsg.common.util.StringUtils.isValidString(as_id))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_ENCONTRAR_POR_ID);

				lps_ps.setString(1, as_id);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lmc_respuesta = obtener(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "encontrarPorId", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lmc_respuesta;
	}

	public Collection<MulticashCabecera> findAllVista(Date ad_fecha, String as_claveBanco, String as_cuenta)
	    throws B2BException
	{
		Collection<MulticashCabecera> lcmc_respuesta;

		lcmc_respuesta = new ArrayList<MulticashCabecera>();

		if(ad_fecha != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_VISTA);

				setDate(lps_ps, ad_fecha, li_column++);
				lps_ps.setString(li_column++, as_claveBanco);
				lps_ps.setString(li_column++, as_cuenta);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcmc_respuesta.add(getObjectFromResult(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllVista", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcmc_respuesta.isEmpty())
			lcmc_respuesta = null;

		return lcmc_respuesta;
	}

	/**
	 * Find by cuenta periodo.
	 *
	 * @param as_idCuenta the as id cuenta
	 * @param as_idPeriodoCorte the as id periodo corte
	 * @return the multicash cabecera
	 * @throws B2BException the b 2 B exception
	 */
	public MulticashCabecera findByCuentaPeriodo(String as_idCuenta, String as_idPeriodoCorte)
	    throws B2BException
	{
		MulticashCabecera lmc_respuesta;

		lmc_respuesta = null;

		if(StringUtils.isValidString(as_idCuenta) && StringUtils.isValidString(as_idPeriodoCorte))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_CUENTA_PERIODO);

				lps_ps.setString(li_column++, as_idCuenta);
				lps_ps.setString(li_column++, as_idPeriodoCorte);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lmc_respuesta = obtener(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCuentaPeriodo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lmc_respuesta;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_CON_MULTICASH_CABECERA
	 *  @param amc_archivo Registro a insertar en la base de datos
	 */
	public MulticashCabecera insertar(MulticashCabecera amc_archivo)
	    throws B2BException
	{
		MulticashCabecera lmc_respuesta;

		lmc_respuesta = null;

		if(amc_archivo != null)
		{
			PreparedStatement lps_insercion;

			lps_insercion = null;

			try
			{
				int    li_column;
				String ls_id;

				li_column         = 1;
				lps_insercion     = getConnection().prepareStatement(cs_INSERTAR);
				ls_id             = amc_archivo.getIdArchivo();

				lps_insercion.setString(li_column++, ls_id);
				lps_insercion.setString(li_column++, amc_archivo.getIdCuenta());
				lps_insercion.setString(li_column++, amc_archivo.getIdPeriodoCorte());
				lps_insercion.setString(li_column++, amc_archivo.getClaveMoneda());

				setDouble(lps_insercion, amc_archivo.getSaldoInicial(), li_column++);
				setDouble(lps_insercion, amc_archivo.getTotalDebitos(), li_column++);
				setDouble(lps_insercion, amc_archivo.getTotalCreditos(), li_column++);
				setDouble(lps_insercion, amc_archivo.getSaldoFinal(), li_column++);

				setInteger(lps_insercion, amc_archivo.getCantidadMovimientos(), li_column++);

				lps_insercion.setString(li_column++, amc_archivo.getEstado());
				lps_insercion.setString(li_column++, amc_archivo.getIdUsuarioCreacion());
				lps_insercion.setString(li_column++, amc_archivo.getIpCreacion());

				lps_insercion.executeUpdate();

				lmc_respuesta = encontrarPorId(ls_id);
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertar", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_insercion);
			}
		}

		return lmc_respuesta;
	}

	private MulticashCabecera getObjectFromResult(ResultSet ars_rs)
	    throws SQLException
	{
		MulticashCabecera lmc_return;

		lmc_return = new MulticashCabecera();

		lmc_return.setClaveBanco(ars_rs.getString("CLAVE_BANCO"));
		lmc_return.setCuentaBancaria(ars_rs.getString("CUENTA_BANCARIA"));
		lmc_return.setFechaOrigenMovimiento(ars_rs.getDate("FECHA_ORIGEN_MOVIMIENTO"));
		lmc_return.setFila(ars_rs.getString("FILA"));
		lmc_return.setTipoCuenta(ars_rs.getString("TIPO_CUENTA"));

		return lmc_return;
	}

	/**
	 * Metodo que se encarga de llenar un objeto de tipo MulticashCabecera con lo consultado y almacenado en un objeto ResultSet.
	 *
	 * @param ars_rs Argumento de tipo ResultSet que contiene los datos que serán almacenados en el objeto MulticashCabecera
	 * @return Objeto de tipo Archivo con lo consultado y almacenado en un objeto ResultSet.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private MulticashCabecera obtener(ResultSet ars_rs)
	    throws SQLException
	{
		MulticashCabecera lmc_archivo;

		lmc_archivo = new MulticashCabecera();

		lmc_archivo.setCantidadMovimientos(getInteger(ars_rs, "CANTIDAD_MOVIMIENTO"));
		lmc_archivo.setClaveMoneda(ars_rs.getString("CLAVE_MONEDA"));
		lmc_archivo.setEstado(ars_rs.getString("ESTADO"));
		lmc_archivo.setIdArchivo(ars_rs.getString("ID_ARCHIVO"));
		lmc_archivo.setIdCuenta(ars_rs.getString("ID_CUENTA"));
		lmc_archivo.setIdPeriodoCorte(ars_rs.getString("ID_PERIODO_CORTE"));
		lmc_archivo.setSaldoFinal(getDouble(ars_rs, "SALDO_FINAL"));
		lmc_archivo.setSaldoInicial(getDouble(ars_rs, "SALDO_INICIAL"));
		lmc_archivo.setTotalCreditos(getDouble(ars_rs, "TOTAL_CREDITOS"));
		lmc_archivo.setTotalDebitos(getDouble(ars_rs, "TOTAL_DEBITOS"));

		fillAuditoria(ars_rs, lmc_archivo);

		return lmc_archivo;
	}
}
