package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.cuentaCupos.DetalleConsultaCuentaCupos;
import com.bachue.snr.prosnr01.model.sdb.acc.MaestroMovimientoCuentaCupo;

import oracle.jdbc.OracleTypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_MAESTRO_MOVIMIENTO_CUENTA_CUPO.
 *
 * @author Manuel Blanco
 *
 */
public class MaestroMovimientoCuentaCupoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID_CUENTA_CUPO. */
	private static final String cs_FIND_BY_ID_CUENTA_CUPO_FECHA = "SELECT * FROM SDB_ACC_MAESTRO_MOVIMIENTO_CUENTA_CUPO WHERE ID_CUENTA_CUPO = ? AND FECHA_CREACION >= ? AND FECHA_CREACION <= ? ORDER BY FECHA_CREACION ASC";

	/** Constante cs_FIND_MOVIMIENTOS_CONSULTA. */
	private static final String cs_FIND_MOVIMIENTOS_CONSULTA = "SELECT SADMCC.ID_NOTA_CREDITO, SADMCC.FECHA_CREACION,(CASE WHEN (SAMMCC.TIPO_MOVIMIENTO = 'RECARGA' OR SAMMCC.TIPO_MOVIMIENTO = 'REVERSO') AND SAMMCC.ESTADO = 'APROBADO' THEN SADMCC.VALOR ELSE NULL END) AS VALOR_RECARGA,(CASE WHEN SAMMCC.TIPO_MOVIMIENTO = 'CONSUMO' AND (SAMMCC.ESTADO = 'APROBADO' OR SAMMCC.ESTADO = 'REVERSADO') THEN SADMCC.VALOR ELSE NULL END) AS VALOR_CONSUMO, SAMMCC.REFERENCIA_PAGO, SAMMCC.ESTADO FROM SDB_ACC_MAESTRO_MOVIMIENTO_CUENTA_CUPO SAMMCC INNER JOIN SDB_ACC_DETALLE_MOVIMIENTO_CUENTA_CUPO SADMCC ON SADMCC.ID_MOVIMIENTO = SAMMCC.ID_MOVIMIENTO WHERE ID_CUENTA_CUPO = ? AND SADMCC.FECHA_CREACION >= ? AND SADMCC.FECHA_CREACION <= ? ORDER BY TO_NUMBER(SADMCC.ID_NOTA_CREDITO), SADMCC.FECHA_CREACION ASC ";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_MAESTRO_MOVIMIENTO_CUENTA_CUPO WHERE ID_MOVIMIENTO = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_MAESTRO_MOVIMIENTO_CUENTA_CUPO (ID_MOVIMIENTO, REFERENCIA_PAGO, ID_CUENTA_CUPO, VALOR, TIPO_MOVIMIENTO, ID_USUARIO, FECHA, ESTADO, INTENTO_NOTIFICACION_PAGO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?) ";

	/** Constante cs_UPDATE_INTENTOS. */
	private static final String cs_UPDATE_INTENTOS = "UPDATE SDB_ACC_MAESTRO_MOVIMIENTO_CUENTA_CUPO SET ESTADO = ?, INTENTO_NOTIFICACION_PAGO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_MOVIMIENTO = ? ";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_MAESTRO_MOVIMIENTO_CUENTA_CUPO_ID_MOVIMIENTO.NEXTVAL FROM DUAL";

	/**
	 * Inserta un registro en la tabla
	 *
	 * @param ammcc_maestroMovimientoCuentaCupo Objeto contenedor de los datos a insertar en el registro
	 * @return Secuencia generada para el registro
	 * @throws B2BException
	 */
	public long insert(MaestroMovimientoCuentaCupo ammcc_maestroMovimientoCuentaCupo)
	    throws B2BException
	{
		long ll_secuencia;

		ll_secuencia = 0;

		if(ammcc_maestroMovimientoCuentaCupo != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;
			Connection        lc_connection;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			lc_connection     = getConnection();

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					ll_secuencia = lrs_rs.getLong(1);

					lps_ps.setString(li_column++, StringUtils.getString(ll_secuencia));
				}

				lps_ps.setString(li_column++, ammcc_maestroMovimientoCuentaCupo.getReferenciaPago());
				lps_ps.setString(li_column++, ammcc_maestroMovimientoCuentaCupo.getIdCuentaCupo());
				lps_ps.setBigDecimal(li_column++, ammcc_maestroMovimientoCuentaCupo.getValor());
				lps_ps.setString(li_column++, ammcc_maestroMovimientoCuentaCupo.getTipoMovimiento());
				lps_ps.setString(li_column++, ammcc_maestroMovimientoCuentaCupo.getIdUsuario());
				setDate(lps_ps, ammcc_maestroMovimientoCuentaCupo.getFecha(), li_column++);
				lps_ps.setString(li_column++, ammcc_maestroMovimientoCuentaCupo.getEstado());
				setInteger(lps_ps, ammcc_maestroMovimientoCuentaCupo.getIntentoNotificacionPago(), li_column++);
				lps_ps.setString(li_column++, ammcc_maestroMovimientoCuentaCupo.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, ammcc_maestroMovimientoCuentaCupo.getIpCreacion());

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

		return ll_secuencia;
	}

	/**
	 * Actualiza un registro de la tabla asociado a un id especifico
	 *
	 * @param ammcc_maestroMovimientoCuentaCupo Objeto contenedor de los datos a actualizar
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public void updateIntentos(MaestroMovimientoCuentaCupo ammcc_maestroMovimientoCuentaCupo)
	    throws B2BException
	{
		if(ammcc_maestroMovimientoCuentaCupo != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_INTENTOS);

				lps_ps.setString(li_column++, ammcc_maestroMovimientoCuentaCupo.getEstado());
				setInteger(lps_ps, ammcc_maestroMovimientoCuentaCupo.getIntentoNotificacionPago(), li_column++);
				lps_ps.setString(li_column++, ammcc_maestroMovimientoCuentaCupo.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ammcc_maestroMovimientoCuentaCupo.getIpModificacion());
				lps_ps.setString(li_column++, ammcc_maestroMovimientoCuentaCupo.getIdMovimiento());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateIntentos", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Busca un registro asociado a un id especifico
	 *
	 * @param as_idMovimiento Id del movimiento a utilziar como filtro en la busqueda
	 * @return MaestroMovimientoCuentaCupo resultante de la consulta
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public MaestroMovimientoCuentaCupo findById(String as_idMovimiento)
	    throws B2BException
	{
		MaestroMovimientoCuentaCupo lcc_return;

		lcc_return = null;

		if(StringUtils.isValidString(as_idMovimiento))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idMovimiento);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcc_return = getObjectFromResultSet(lrs_rs);
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

		return lcc_return;
	}

	/**
	 * Busca los movimientos de una cuenta cupo en un lapso determinado
	 *
	 * @param as_idCuentaCupo id de cuenta cupo a utilizar como filtro en la busqueda
	 * @param aldt_fechaInicio fecha de inicio a utilizar como filtro en la busqueda
	 * @param aldt_fechaFin fecha fin a utilizar como filtro en la busqueda
	 * @return Colección de cuenta cupo resultante de la busqueda
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public Collection<MaestroMovimientoCuentaCupo> findByIdCuentaCupoFecha(
	    String as_idCuentaCupo, LocalDateTime aldt_fechaInicio, LocalDateTime aldt_fechaFin
	)
	    throws B2BException
	{
		Collection<MaestroMovimientoCuentaCupo> lcmmcc_return;

		lcmmcc_return = new LinkedList<MaestroMovimientoCuentaCupo>();

		if(StringUtils.isValidString(as_idCuentaCupo) && (aldt_fechaInicio != null) && (aldt_fechaFin != null))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_CUENTA_CUPO_FECHA);

				lps_ps.setString(li_cont++, as_idCuentaCupo);
				lps_ps.setObject(li_cont++, aldt_fechaInicio, OracleTypes.DATE);
				lps_ps.setObject(li_cont++, aldt_fechaFin, OracleTypes.DATE);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcmmcc_return.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdCuentaCupoFecha", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcmmcc_return.isEmpty())
			lcmmcc_return = null;

		return lcmmcc_return;
	}

	/**
	 * Busca los movimientos en un lapso determinado
	 *
	 * @param as_idCuentaCupo Id de la cuenta cupo a consultar
	 * @param aldt_fechaInicio fecha inicial del lapso
	 * @param aldt_fechaFin fecha final del lapso
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public Collection<DetalleConsultaCuentaCupos> findConsultaMovimientos(
	    String as_idCuentaCupo, LocalDateTime aldt_fechaInicio, LocalDateTime aldt_fechaFin
	)
	    throws B2BException
	{
		Collection<DetalleConsultaCuentaCupos> lcdccc_return;

		lcdccc_return = new LinkedList<DetalleConsultaCuentaCupos>();

		if(StringUtils.isValidString(as_idCuentaCupo) && (aldt_fechaInicio != null) && (aldt_fechaFin != null))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_MOVIMIENTOS_CONSULTA);

				lps_ps.setString(li_cont++, as_idCuentaCupo);
				lps_ps.setObject(li_cont++, aldt_fechaInicio, OracleTypes.DATE);
				lps_ps.setObject(li_cont++, aldt_fechaFin, OracleTypes.DATE);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdccc_return.add(getDatoConsultaFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdCuentaCupoFecha", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcdccc_return.isEmpty())
			lcdccc_return = null;

		return lcdccc_return;
	}

	/**
	 * Extrae los resultados de la consulta y los almacena en un objeto DetalleConsultaCuentaCupos
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return DetalleConsultaCuentaCupos resultante de la consulta
	 * @throws SQLException Si ocurre un error en la extracción de la información
	 */
	private DetalleConsultaCuentaCupos getDatoConsultaFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		DetalleConsultaCuentaCupos lmmcc_movimientoCuenta;

		lmmcc_movimientoCuenta = new DetalleConsultaCuentaCupos();

		lmmcc_movimientoCuenta.setIdNotaCredito(ars_rs.getString("ID_NOTA_CREDITO"));
		lmmcc_movimientoCuenta.setFechaMovimiento(ars_rs.getTimestamp("FECHA_CREACION"));
		lmmcc_movimientoCuenta.setValorRecarga(ars_rs.getBigDecimal("VALOR_RECARGA"));
		lmmcc_movimientoCuenta.setValorConsumo(ars_rs.getBigDecimal("VALOR_CONSUMO"));
		lmmcc_movimientoCuenta.setReciboCaja(ars_rs.getString("REFERENCIA_PAGO"));
		lmmcc_movimientoCuenta.setEstado(ars_rs.getString("ESTADO"));

		return lmmcc_movimientoCuenta;
	}

	/**
	 * Extrae los resultados de la consulta y los almacena en un objeto MaestroMovimientoCuentaCupo
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return MaestroMovimientoCuentaCupo resultante de la consulta
	 * @throws SQLException Si ocurre un error en la extracción de la información
	 */
	private MaestroMovimientoCuentaCupo getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		MaestroMovimientoCuentaCupo lmmcc_movimientoCuenta;

		lmmcc_movimientoCuenta = new MaestroMovimientoCuentaCupo();

		lmmcc_movimientoCuenta.setIdMovimiento(ars_rs.getString("ID_MOVIMIENTO"));
		lmmcc_movimientoCuenta.setReferenciaPago(ars_rs.getString("REFERENCIA_PAGO"));
		lmmcc_movimientoCuenta.setIdCuentaCupo(ars_rs.getString("ID_CUENTA_CUPO"));
		lmmcc_movimientoCuenta.setValor(ars_rs.getBigDecimal("VALOR"));
		lmmcc_movimientoCuenta.setTipoMovimiento(ars_rs.getString("TIPO_MOVIMIENTO"));
		lmmcc_movimientoCuenta.setIdUsuario(ars_rs.getString("ID_USUARIO"));
		lmmcc_movimientoCuenta.setFecha(ars_rs.getTimestamp("FECHA"));
		lmmcc_movimientoCuenta.setEstado(ars_rs.getString("ESTADO"));
		lmmcc_movimientoCuenta.setIntentoNotificacionPago(getInteger(ars_rs, "INTENTO_NOTIFICACION_PAGO"));

		fillAuditoria(ars_rs, lmmcc_movimientoCuenta);

		return lmmcc_movimientoCuenta;
	}
}
