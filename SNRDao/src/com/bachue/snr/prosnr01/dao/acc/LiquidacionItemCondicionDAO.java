package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.LiquidacionItemCondicion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_LIQUIDACION_ITEM_CONDICION.
 *
 * @author garias
 */
public class LiquidacionItemCondicionDAO extends BaseDAO
{
	/** Constante cs_DELETE. */
	private static final String cs_DELETE = " DELETE FROM SDB_ACC_LIQUIDACION_ITEM_CONDICION WHERE ID_LIQUIDACION = ? AND CONSECUTIVO = ? AND ID_ITEM = ? ";

	/** Constante cs_BUSCAR_POR_ID_LIQUIDACION. */
	private static final String cs_BUSCAR_POR_ID_LIQUIDACION = "SELECT * FROM SDB_ACC_LIQUIDACION_ITEM_CONDICION WHERE ID_LIQUIDACION = ? AND CONSECUTIVO = ? AND ID_ITEM = ?";

	/**
	 * Metodo encargado de consultar todos los registros de la tabla SDB_ACC_LIQUIDACION_ITEM_CONDICION para un ID_LIQUIDACION específico.
	 *
	 * @param alic_parametros Argumento de tipo <code>LiquidacionItemCondicion</code> que contiene los criterios necesarios para realizar la consulta.
	 * @return Liquidacion que contiene los resultados que coinciden con los criterios de consulta.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Liquidacion
	 */
	public LiquidacionItemCondicion buscarLiquidacionItemTipoActo(LiquidacionItemCondicion alic_parametros)
	    throws B2BException
	{
		LiquidacionItemCondicion llic_datos;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		llic_datos     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(alic_parametros != null)
		{
			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_BUSCAR_POR_ID_LIQUIDACION);

				lps_ps.setString(li_column++, alic_parametros.getIdLiquidacion());
				lps_ps.setInt(li_column++, alic_parametros.getConsecutivo());
				lps_ps.setLong(li_column++, alic_parametros.getIdItem());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					llic_datos = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarLiquidacionItemTipoActo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return llic_datos;
	}

	/**
	 * Elimina el registro en la tabla.
	 *
	 * @param alic_param correspondiente al valor del tipo de objeto Liquidacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteLiquidacionItem(LiquidacionItemCondicion alic_param)
	    throws B2BException
	{
		if(alic_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setString(li_column++, alic_param.getIdLiquidacion());
				lps_ps.setInt(li_column++, alic_param.getConsecutivo());
				lps_ps.setLong(li_column++, alic_param.getIdItem());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteLiquidacionItem", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de cargar los datos consultados y almacenados en el objeto de tipo <code>ResultSet</code>,
	 * en un objeto de tipo <code>LiquidacionItemCondicion</code>.
	 *
	 * @param lrs_rs Argumento de tipo <code>ResultSet</code> que contiene los datos consultados.
	 * @return Objeto de tipo <code>LiquidacionItemCondicion</code> que retornará los datos consultados.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private LiquidacionItemCondicion getObjectFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		LiquidacionItemCondicion llic_liquidacionItemCondicion;

		llic_liquidacionItemCondicion = new LiquidacionItemCondicion();

		llic_liquidacionItemCondicion.setIdLiquidacion(lrs_rs.getString("ID_LIQUIDACION"));
		llic_liquidacionItemCondicion.setConsecutivo(lrs_rs.getInt("CONSECUTIVO"));
		llic_liquidacionItemCondicion.setIdItem(lrs_rs.getLong("ID_ITEM"));
		llic_liquidacionItemCondicion.setIdTipoActoCondicion(lrs_rs.getString("ID_TIPO_ACTO_CONDICION"));
		llic_liquidacionItemCondicion.setActivo(lrs_rs.getString("ACTIVO"));
		llic_liquidacionItemCondicion.setAutomatico(lrs_rs.getString("AUTOMATICO"));
		llic_liquidacionItemCondicion.setRespuesta(lrs_rs.getString("RESPUESTA"));
		llic_liquidacionItemCondicion.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		llic_liquidacionItemCondicion.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		llic_liquidacionItemCondicion.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		llic_liquidacionItemCondicion.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		llic_liquidacionItemCondicion.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		llic_liquidacionItemCondicion.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return llic_liquidacionItemCondicion;
	}
}
