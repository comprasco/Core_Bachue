package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.RegistroPagoExceso;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades RegistroPagoExcesoDAO.
 *
 * @author  Luis Chacón
 * Fecha de Creacion: 3/08/2020
 */
public class RegistroPagoExcesoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_REGISTRO_PAGO_EXCESO WHERE ID_REGISTRO_PAGO_EXCESO=?";

	/** Constante cs_FIND_BY_ID_TURNO. */
	private static final String cs_FIND_BY_ID_TURNO = "SELECT * FROM SDB_ACC_REGISTRO_PAGO_EXCESO WHERE ID_TURNO=?";

	/** Constante cs_FIND_BY_ID_LIQUIDACION. */
	private static final String cs_FIND_BY_ID_LIQUIDACION = "SELECT * FROM SDB_ACC_REGISTRO_PAGO_EXCESO WHERE ID_LIQUIDACION=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_REGISTRO_PAGO_EXCESO SET ID_LIQUIDACION=?, CONSECUTIVO=?, ID_ITEM=?, VALOR_TOTAL_LIQUIDADO=?, TOTAL_SALDO_FAVOR=?, FECHA_REGISTRO=?, CONCEPTO_PAGO_EXCESO=?, ESTADO_PAGO_EXCESO=?, FECHA_CAMBIO_ESTADO=?, ID_TURNO = ?, ID_SOLICITUD = ?, FECHA_MODIFICACION=SYSTIMESTAMP,ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=? WHERE ID_REGISTRO_PAGO_EXCESO=?";

	/** Constante cs_UPDATE_CONCEPTO. */
	private static final String cs_UPDATE_CONCEPTO = "UPDATE SDB_ACC_REGISTRO_PAGO_EXCESO SET CONCEPTO_PAGO_EXCESO = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE ID_REGISTRO_PAGO_EXCESO = ?";

	/** Constante cs_INSERTAR. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_REGISTRO_PAGO_EXCESO (ID_REGISTRO_PAGO_EXCESO,ID_LIQUIDACION,CONSECUTIVO,ID_ITEM,VALOR_TOTAL_LIQUIDADO,TOTAL_SALDO_FAVOR,FECHA_REGISTRO,CONCEPTO_PAGO_EXCESO,ESTADO_PAGO_EXCESO,FECHA_CAMBIO_ESTADO,ID_TURNO,ID_SOLICITUD,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_ACC_REGISTRO_PAGO_EXCESO que coincidan con un ID_REGISTRO_PAGO_EXCESO especifico.
	 *
	 * @param arpe_rpe correspondiente al valor del tipo de objeto Etapa
	 * @return devuelve el valor del objeto etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public RegistroPagoExceso findById(RegistroPagoExceso arpe_rpe)
	    throws B2BException
	{
		return (arpe_rpe != null) ? findById(arpe_rpe.getIdRegistroPagoExceso()) : null;
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_ACC_REGISTRO_PAGO_EXCESO que coincidan con un ID_REGISTRO_PAGO_EXCESO especifico.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto long
	 * @return devuelve el valor del objeto etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public RegistroPagoExceso findById(String as_s)
	    throws B2BException
	{
		RegistroPagoExceso let_etapa;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		let_etapa     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			if(StringUtils.isValidString(as_s))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_s);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					let_etapa = getObjetFromResultSet(lrs_rs);
			}
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

		return let_etapa;
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_ACC_REGISTRO_PAGO_EXCESO que coincidan con un ID_REGISTRO_PAGO_EXCESO especifico.
	 *
	 * @param as_idLiquidacion Argumento de tipo <code>String</code> que contiene el id liquidación a consultar.
	 * @return Devuelve listado de registro pago en exceso que coincida con los criterios de búsqueda.
	 * @throws B2BException Señala si se ha generado una excepción.
	 */
	public Collection<RegistroPagoExceso> findByIdLiquidacion(String as_idLiquidacion)
	    throws B2BException
	{
		Collection<RegistroPagoExceso> lcrpe_datos;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcrpe_datos     = new ArrayList<RegistroPagoExceso>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(StringUtils.isValidString(as_idLiquidacion))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_LIQUIDACION);

				lps_ps.setString(1, as_idLiquidacion);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcrpe_datos.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdLiquidacion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcrpe_datos;
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_ACC_REGISTRO_PAGO_EXCESO que coincidan con un ID_REGISTRO_PAGO_EXCESO especifico.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public RegistroPagoExceso findByIdTurno(String as_s)
	    throws B2BException
	{
		RegistroPagoExceso let_etapa;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		let_etapa     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			if(StringUtils.isValidString(as_s))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO);

				lps_ps.setString(1, as_s);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					let_etapa = getObjetFromResultSet(lrs_rs);
			}
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

		return let_etapa;
	}

	/**
	 * Inserta un registro en la tabla.
	 *
	 * @param arpe_rpe Objeto contenedor de la información a insertar
	 * @throws B2BException Si ocurre un error en la comunicación con la base de datos
	 */
	public void insert(RegistroPagoExceso arpe_rpe)
	    throws B2BException
	{
		if(arpe_rpe != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, arpe_rpe.getIdRegistroPagoExceso());
				lps_ps.setInt(li_column++, arpe_rpe.getConsecutivo());
				lps_ps.setLong(li_column++, NumericUtils.getLong(arpe_rpe.getIdItem()));
				lps_ps.setBigDecimal(li_column++, arpe_rpe.getValorTotalLiquidado());
				lps_ps.setBigDecimal(li_column++, arpe_rpe.getTotalSaldoFavor());
				setDate(lps_ps, arpe_rpe.getFechaRegistro(), li_column++);
				lps_ps.setString(li_column++, arpe_rpe.getConceptoPagoExceso());
				lps_ps.setString(li_column++, arpe_rpe.getEstadoPagoExceso());
				setDate(lps_ps, arpe_rpe.getFechaCambioEstado(), li_column++);
				lps_ps.setString(li_column++, arpe_rpe.getIdTurno());
				lps_ps.setString(li_column++, arpe_rpe.getIdSolicitud());
				lps_ps.setString(li_column++, arpe_rpe.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, arpe_rpe.getIpCreacion());

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
	 * Actualiza un registro en la tabla a partir del id registro pago exceso.
	 *
	 * @param arpe_rpe Objeto contenedor de la información que se actualizará
	 * @throws B2BException Si ocurre un error en la comunicación con la base de datos
	 */
	public void update(RegistroPagoExceso arpe_rpe)
	    throws B2BException
	{
		if(arpe_rpe != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setInt(li_column++, arpe_rpe.getConsecutivo());
				lps_ps.setLong(li_column++, NumericUtils.getLong(arpe_rpe.getIdItem()));
				lps_ps.setBigDecimal(li_column++, arpe_rpe.getValorTotalLiquidado());
				lps_ps.setBigDecimal(li_column++, arpe_rpe.getTotalSaldoFavor());
				setDate(lps_ps, arpe_rpe.getFechaRegistro(), li_column++);
				lps_ps.setString(li_column++, arpe_rpe.getConceptoPagoExceso());
				lps_ps.setString(li_column++, arpe_rpe.getEstadoPagoExceso());
				setDate(lps_ps, arpe_rpe.getFechaCambioEstado(), li_column++);
				lps_ps.setString(li_column++, arpe_rpe.getIdTurno());
				lps_ps.setString(li_column++, arpe_rpe.getIdSolicitud());
				lps_ps.setString(li_column++, arpe_rpe.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, arpe_rpe.getIpModificacion());
				lps_ps.setString(li_column++, arpe_rpe.getIdRegistroPagoExceso());

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
	 * Actualiza concepto pago exceso en la tabla a partir del id registro pago exceso.
	 *
	 * @param arpe_parametros Argumento de tipo <code>RegistroPagoExceso</code> que contiene los
	 * criterios necesarios para realizar la actualización.
	 * @throws B2BException Si ocurre un error en la comunicación con la base de datos
	 */
	public void updateConcepto(RegistroPagoExceso arpe_parametros)
	    throws B2BException
	{
		if(arpe_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_CONCEPTO);

				lps_ps.setString(li_column++, arpe_parametros.getConceptoPagoExceso());
				lps_ps.setString(li_column++, arpe_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, arpe_parametros.getIpModificacion());
				lps_ps.setString(li_column++, arpe_parametros.getIdRegistroPagoExceso());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateConcepto", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo para obtener el objeto de tipo RegistroPagoExceso.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de Etapa
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RegistroPagoExceso getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		RegistroPagoExceso lrpe_rpe;

		lrpe_rpe = new RegistroPagoExceso();

		lrpe_rpe.setIdRegistroPagoExceso(ars_rs.getString("ID_REGISTRO_PAGO_EXCESO"));
		lrpe_rpe.setIdLiquidacion(ars_rs.getString("ID_LIQUIDACION"));
		lrpe_rpe.setConsecutivo(ars_rs.getInt("CONSECUTIVO"));
		lrpe_rpe.setIdItem(NumericUtils.getLongWrapper(ars_rs.getLong("ID_ITEM")));
		lrpe_rpe.setValorTotalLiquidado(ars_rs.getBigDecimal("VALOR_TOTAL_LIQUIDADO"));
		lrpe_rpe.setTotalSaldoFavor(ars_rs.getBigDecimal("TOTAL_SALDO_FAVOR"));
		lrpe_rpe.setFechaRegistro(ars_rs.getDate("FECHA_REGISTRO"));
		lrpe_rpe.setConceptoPagoExceso(ars_rs.getString("CONCEPTO_PAGO_EXCESO"));
		lrpe_rpe.setEstadoPagoExceso(ars_rs.getString("ESTADO_PAGO_EXCESO"));
		lrpe_rpe.setFechaCambioEstado(ars_rs.getDate("FECHA_CAMBIO_ESTADO"));
		lrpe_rpe.setIdTurno(ars_rs.getString("ID_TURNO"));
		lrpe_rpe.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		fillAuditoria(ars_rs, lrpe_rpe);

		return lrpe_rpe;
	}
}
