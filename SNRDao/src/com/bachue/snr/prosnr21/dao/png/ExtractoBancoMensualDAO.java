package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.model.pgn.ExtractoBancoMensual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase encargada de las interacciones con base de datos para la tabla SDB_CON_EXTRACTO_BANCO_MENSUAL del módulo de conciliaciones.
 *
 * @author Kevin Pulido
 */
public class ExtractoBancoMensualDAO extends com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao
{
	/** Constante cs_FIND_PERIODO_BY_BANCO_CUENTA. */
	private static final String cs_FIND_PERIODO_BY_BANCO_CUENTA = "SELECT EM.PERIODO FROM SDB_CON_EXTRACTO_BANCO_MENSUAL EM WHERE EM.ID_ENTIDAD_RECAUDADORA = ? AND EM.ID_CUENTA = ?";

	/** Constante cs_FIND_BY_BANCO_CUENTA. */
	private static final String cs_FIND_BY_BANCO_CUENTA_PERIODO = "SELECT * FROM SDB_CON_EXTRACTO_BANCO_MENSUAL WHERE ID_ENTIDAD_RECAUDADORA = ? AND ID_CUENTA = ? AND PERIODO = ?;";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_CON_EXTRACTO_BANCO_MENSUAL (ID_ARCHIVO,ID_REGISTRO,ID_ENTIDAD_RECAUDADORA,ID_CUENTA,PERIODO,FECHA,CUENTA,CONSECUTIVO,TIPO_CUENTA,OFICINA,CODIGO_TRANSACCION,NOMBRE_CORTO_TRANSACCION,NOMBRE_EXTRACTO_TRANSACCION,DOCUMENTO,SIGNO_VALOR,VALOR,OFICINA_ORIGEN,TIPO_TRANSACCION,SIGNO_SALDO,SALDO_DIA,NOMBRE_ARCHIVO,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/**
	 * Buscar periodo por banco cuenta.
	 *
	 * @param as_idEntidadRecaudadora de as id entidad recaudadora
	 * @param as_idCuenta de as id cuenta
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ExtractoBancoMensual> buscarPeriodoPorBancoCuenta(
	    String as_idEntidadRecaudadora, String as_idCuenta
	)
	    throws B2BException
	{
		Collection<ExtractoBancoMensual> lcebm_return;

		lcebm_return = new ArrayList<ExtractoBancoMensual>();

		if(StringUtils.isValidString(as_idEntidadRecaudadora) && StringUtils.isValidString(as_idCuenta))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_PERIODO_BY_BANCO_CUENTA);
				lps_ps.setString(li_column++, as_idEntidadRecaudadora);
				lps_ps.setString(li_column++, as_idCuenta);
				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcebm_return.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarPeriodoPorBancoCuenta", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}

			if(lcebm_return.isEmpty())
				lcebm_return = null;
		}

		return lcebm_return;
	}

	/**
	 * Buscar por banco cuenta.
	 *
	 * @param as_idEntidadRecaudadora de as id entidad recaudadora
	 * @param as_idCuenta de as id cuenta
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ExtractoBancoMensual buscarPorBancoCuentaPeriodo(
	    String as_idEntidadRecaudadora, String as_idCuenta, String as_periodo
	)
	    throws B2BException
	{
		ExtractoBancoMensual lcebm_return;

		lcebm_return = null;

		if(
		    StringUtils.isValidString(as_idEntidadRecaudadora) && StringUtils.isValidString(as_idCuenta)
			    && StringUtils.isValidString(as_periodo)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_BANCO_CUENTA_PERIODO);
				lps_ps.setString(li_column++, as_idEntidadRecaudadora);
				lps_ps.setString(li_column++, as_idCuenta);
				lps_ps.setString(li_column++, as_periodo);
				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcebm_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarPorBancoCuentaPeriodo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcebm_return;
	}

	/**
	 * Insertar desde extracto banco mensual.
	 *
	 * @param aebm_obj de lebm obj
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(ExtractoBancoMensual aebm_obj)
	    throws B2BException
	{
		if(aebm_obj != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();
				lps_ps            = lc_connection.prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, aebm_obj.getIdArchivo());
				setInteger(lps_ps, aebm_obj.getIdRegistro(), li_column++);
				lps_ps.setString(li_column++, aebm_obj.getIdEntidadRecaudadora());
				lps_ps.setString(li_column++, aebm_obj.getIdCuenta());
				
				lps_ps.setString(li_column++, aebm_obj.getPeriodo());
				lps_ps.setString(li_column++, aebm_obj.getFecha());
				lps_ps.setString(li_column++, aebm_obj.getCuenta());
				setInteger(lps_ps, aebm_obj.getConsecutivo(), li_column++);
				lps_ps.setString(li_column++, aebm_obj.getTipoCuenta());
				lps_ps.setString(li_column++, aebm_obj.getOficina());
				setInteger(lps_ps, aebm_obj.getCodigoTransaccion(), li_column++);
				lps_ps.setString(li_column++, aebm_obj.getNombreCortoTransaccion());
				lps_ps.setString(li_column++, aebm_obj.getNombreExtractoTransaccion());
				setInteger(lps_ps, aebm_obj.getDocumento(), li_column++);
				lps_ps.setString(li_column++, aebm_obj.getSignoValor());
				setDouble(lps_ps, aebm_obj.getValor(), li_column++);
				setInteger(lps_ps, aebm_obj.getOficinaOrigen(), li_column++);
				lps_ps.setString(li_column++, aebm_obj.getTipoTransaccion());
				lps_ps.setString(li_column++, aebm_obj.getSignoSaldo());
				setDouble(lps_ps, aebm_obj.getSaldoDia(), li_column++);
				lps_ps.setString(li_column++, aebm_obj.getNombreArchivo());
				lps_ps.setString(li_column++, aebm_obj.getActivo());
				lps_ps.setString(li_column++, aebm_obj.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aebm_obj.getIpCreacion());

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
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private ExtractoBancoMensual getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ExtractoBancoMensual lebm_datos;
		lebm_datos = new ExtractoBancoMensual();

		lebm_datos.setIdArchivo(ars_rs.getString("ID_ARCHIVO"));
		lebm_datos.setIdRegistro(getInteger(ars_rs, "ID_REGISTRO"));
		lebm_datos.setIdEntidadRecaudadora(ars_rs.getString("ID_ENTIDAD_RECAUDADORA"));
		lebm_datos.setIdCuenta(ars_rs.getString("ID_CUENTA"));
		lebm_datos.setPeriodo(ars_rs.getString("PERIODO"));
		lebm_datos.setFecha(ars_rs.getString("FECHA"));
		lebm_datos.setCuenta(ars_rs.getString("CUENTA"));
		lebm_datos.setConsecutivo(getInteger(ars_rs, "CONSECUTIVO"));
		lebm_datos.setTipoCuenta(ars_rs.getString("TIPO_CUENTA"));
		lebm_datos.setOficina(ars_rs.getString("OFICINA"));
		lebm_datos.setCodigoTransaccion(getInteger(ars_rs, "CODIGO_TRANSACCION"));
		lebm_datos.setNombreCortoTransaccion(ars_rs.getString("NOMBRE_CORTO_TRANSACCION"));
		lebm_datos.setNombreExtractoTransaccion(ars_rs.getString("NOMBRE_EXTRACTO_TRANSACCION"));
		lebm_datos.setDocumento(getInteger(ars_rs, "DOCUMENTO"));
		lebm_datos.setSignoValor(ars_rs.getString("SIGNO_VALOR"));
		lebm_datos.setValor(getDouble(ars_rs, "VALOR"));
		lebm_datos.setOficinaOrigen(getInteger(ars_rs, "OFICINA_ORIGEN"));
		lebm_datos.setTipoTransaccion(ars_rs.getString("TIPO_TRANSACCION"));
		lebm_datos.setSignoSaldo(ars_rs.getString("SIGNO_SALDO"));
		lebm_datos.setSaldoDia(getDouble(ars_rs, "SALDO_DIA"));
		lebm_datos.setNombreArchivo(ars_rs.getString("NOMBRE_ARCHIVO"));
		lebm_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lebm_datos);

		return lebm_datos;
	}
}
