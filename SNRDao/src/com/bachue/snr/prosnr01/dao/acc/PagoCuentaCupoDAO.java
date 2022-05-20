package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.PagoCuentaCupo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_MAESTRO_MOVIMIENTO_CUENTA_CUPO.
 *
 * @author Manuel Blanco
 *
 */
public class PagoCuentaCupoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ACTIVO. */
	private static final String cs_FIND_BY_ACTIVO = "SELECT * FROM SDB_ACC_PAGO_CUENTA_CUPO WHERE NVL(ESTADO, 'A') = 'A' ORDER BY FECHA_CREACION ASC ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_PAGO_CUENTA_CUPO(ID_PAGO_CUENTA_CUPO, ID_CUENTA_CUPO, REFERENCIA_PAGO, VALOR, ID_MOVIMIENTO, ESTADO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?) ";

	/** Constante cs_UPDATE_ESTADO. */
	private static final String cs_UPDATE_ESTADO = "UPDATE SDB_ACC_PAGO_CUENTA_CUPO SET ESTADO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_PAGO_CUENTA_CUPO = ? ";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_PAGO_CUENTA_CUPO_ID_PAGO_CUENTA_CUPO.NEXTVAL FROM DUAL";

	/**
	 * Inserta un registro en la tabla
	 *
	 * @param apcc_pagoCuentaCupo Objeto contenedor de los datos a insertar en el registro
	 * @return Secuencia generada para el registro
	 * @throws B2BException
	 */
	public long insert(PagoCuentaCupo apcc_pagoCuentaCupo)
	    throws B2BException
	{
		long ll_secuencia;

		ll_secuencia = 0;

		if(apcc_pagoCuentaCupo != null)
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

				lps_ps.setString(li_column++, apcc_pagoCuentaCupo.getIdCuentaCupo());
				lps_ps.setString(li_column++, apcc_pagoCuentaCupo.getReferenciaPago());
				lps_ps.setBigDecimal(li_column++, apcc_pagoCuentaCupo.getValor());
				lps_ps.setString(li_column++, apcc_pagoCuentaCupo.getIdMovimiento());
				lps_ps.setString(li_column++, apcc_pagoCuentaCupo.getEstado());
				lps_ps.setString(li_column++, apcc_pagoCuentaCupo.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, apcc_pagoCuentaCupo.getIpCreacion());

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
	 * Actualiza el valor del campo estado de un registro asociado a un id especifico
	 *
	 * @param apcc_pagoCuentaCupo Objeto contenedor de los datos a actualizar
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public void updateEstado(PagoCuentaCupo apcc_pagoCuentaCupo)
	    throws B2BException
	{
		if(apcc_pagoCuentaCupo != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_ESTADO);

				lps_ps.setString(li_column++, apcc_pagoCuentaCupo.getEstado());
				lps_ps.setString(li_column++, apcc_pagoCuentaCupo.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, apcc_pagoCuentaCupo.getIpModificacion());
				lps_ps.setString(li_column++, apcc_pagoCuentaCupo.getIdPagoCuentaCupo());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateEstado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Busca todos los registros que tengan menos de tres intentos de envio a notificación de pagos
	 *
	 * @return Colección resultante de la consulta
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public Collection<PagoCuentaCupo> findByActivo()
	    throws B2BException
	{
		Collection<PagoCuentaCupo> lcpcc_return;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lps_ps           = null;
		lrs_rs           = null;
		lcpcc_return     = new LinkedList<PagoCuentaCupo>();

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_BY_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpcc_return.add(getObjectFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIntentosValidos", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcpcc_return.isEmpty())
			lcpcc_return = null;

		return lcpcc_return;
	}

	/**
	 * Extrae los resultados de la consulta y los almacena en un objeto PagoCuentaCupo
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return Pago cuenta cupo resultante de la consulta
	 * @throws SQLException Si ocurre un error en la extracción de la información
	 */
	private PagoCuentaCupo getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		PagoCuentaCupo lpcc_pagoCuentaCupo;

		lpcc_pagoCuentaCupo = new PagoCuentaCupo();

		lpcc_pagoCuentaCupo.setIdPagoCuentaCupo(ars_rs.getString("ID_PAGO_CUENTA_CUPO"));
		lpcc_pagoCuentaCupo.setIdCuentaCupo(ars_rs.getString("ID_CUENTA_CUPO"));
		lpcc_pagoCuentaCupo.setReferenciaPago(ars_rs.getString("REFERENCIA_PAGO"));
		lpcc_pagoCuentaCupo.setValor(ars_rs.getBigDecimal("VALOR"));
		lpcc_pagoCuentaCupo.setIdMovimiento(ars_rs.getString("ID_MOVIMIENTO"));
		lpcc_pagoCuentaCupo.setEstado(ars_rs.getString("ESTADO"));

		fillAuditoria(ars_rs, lpcc_pagoCuentaCupo);

		return lpcc_pagoCuentaCupo;
	}
}
