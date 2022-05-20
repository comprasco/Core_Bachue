package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.DetalleMovimientoCuentaCupo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_DETALLE_MOVIMIENTO_CUENTA_CUPO.
 *
 * @author Manuel Blanco
 *
 */
public class DetalleMovimientoCuentaCupoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID_CUENTA_CUPO. */
	private static final String cs_FIND_BY_ID_MAESTRO_MOVIMIENTO = "SELECT * FROM SDB_ACC_DETALLE_MOVIMIENTO_CUENTA_CUPO WHERE ID_MOVIMIENTO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_DETALLE_MOVIMIENTO_CUENTA_CUPO (ID_DETALLE_MOVIMIENTO, ID_MOVIMIENTO, ID_NOTA_CREDITO, VALOR, FECHA, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?) ";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_DETALLE_MOVIMIENTO_CUENTA_CUPO_ID_DETALLE_MOVIMIENTO.NEXTVAL FROM DUAL";

	/**
	 * Inserta un registro en la tabla
	 *
	 * @param admcc_detalleMovimientoCuentaCupo Objeto contenedor de los datos a insertar en el registro
	 * @return Secuencia generada para el registro
	 * @throws B2BException
	 */
	public long insert(DetalleMovimientoCuentaCupo admcc_detalleMovimientoCuentaCupo)
	    throws B2BException
	{
		long ll_secuencia;

		ll_secuencia = 0;

		if(admcc_detalleMovimientoCuentaCupo != null)
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

				lps_ps.setString(li_column++, admcc_detalleMovimientoCuentaCupo.getIdMovimiento());
				lps_ps.setString(li_column++, admcc_detalleMovimientoCuentaCupo.getIdNotaCredito());
				lps_ps.setBigDecimal(li_column++, admcc_detalleMovimientoCuentaCupo.getValor());
				setDate(lps_ps, admcc_detalleMovimientoCuentaCupo.getFecha(), li_column++);
				lps_ps.setString(li_column++, admcc_detalleMovimientoCuentaCupo.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, admcc_detalleMovimientoCuentaCupo.getIpCreacion());

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
	 * Busca todos los detalle asociados a un movimiento
	 *
	 * @param as_idMovimiento id del movimiento maestro a utilizar como filtro en la busqueda
	 * @return Colección de detalles movimiento resultante de la consulta
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public Collection<DetalleMovimientoCuentaCupo> findByIdMaestroMovimiento(String as_idMovimiento)
	    throws B2BException
	{
		Collection<DetalleMovimientoCuentaCupo> lcdmcc_return;

		lcdmcc_return = new LinkedList<DetalleMovimientoCuentaCupo>();

		if(StringUtils.isValidString(as_idMovimiento))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_MAESTRO_MOVIMIENTO);

				lps_ps.setString(1, as_idMovimiento);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdmcc_return.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdMaestroMovimiento", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcdmcc_return.isEmpty())
			lcdmcc_return = null;

		return lcdmcc_return;
	}

	/**
	 * Extrae los resultados de la consulta y los almacena en un objeto DetalleMovimientoCuentaCupo
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return DetalleMovimientoCuentaCupo resultante de la consulta
	 * @throws SQLException Si ocurre un error en la extracción de la información
	 */
	private DetalleMovimientoCuentaCupo getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		DetalleMovimientoCuentaCupo ldmcc_detalleMovimiento;

		ldmcc_detalleMovimiento = new DetalleMovimientoCuentaCupo();

		ldmcc_detalleMovimiento.setIdDetalleMovimiento(ars_rs.getString("ID_DETALLE_MOVIMIENTO"));
		ldmcc_detalleMovimiento.setIdMovimiento(ars_rs.getString("ID_MOVIMIENTO"));
		ldmcc_detalleMovimiento.setIdNotaCredito(ars_rs.getString("ID_NOTA_CREDITO"));
		ldmcc_detalleMovimiento.setValor(ars_rs.getBigDecimal("VALOR"));
		ldmcc_detalleMovimiento.setFecha(ars_rs.getTimestamp("FECHA"));

		fillAuditoria(ars_rs, ldmcc_detalleMovimiento);

		return ldmcc_detalleMovimiento;
	}
}
