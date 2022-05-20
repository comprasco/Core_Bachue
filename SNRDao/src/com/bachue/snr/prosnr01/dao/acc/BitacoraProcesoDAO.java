package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.BitacoraProceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 * Clase de manejo de datos para la tabla SDB_ACC_BITACORA_PROCESO.
 *
 * @author Heiner Castañeda
 */
public class BitacoraProcesoDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	
	/** Constante cs_FILTRO_BY_FECHA. */
	private static final String cs_FILTRO_BY_FECHA = "SELECT DISTINCT (SABP.DESCRIPCION), SABP.ID_TURNO, SABP.FECHA_CREACION,"
		+ " SAC.NIR AS NIR_SOLICITUD, SATH.ID_ETAPA AS ETAPA FROM SDB_ACC_BITACORA_PROCESO SABP "
		+ "INNER JOIN SDB_ACC_SOLICITUD SAC ON SABP.ID_SOLICITUD = SAC.ID_SOLICITUD "
		+ "INNER JOIN SDB_ACC_TURNO_HISTORIA SATH ON SATH.ID_TURNO_HISTORIA = SABP.ID_TURNO_HISTORIA "
		+ "WHERE SABP.PROCESO = ? AND SABP.FECHA_CREACION between to_date(?, 'YYYY-MM-DD')  "
		+ "AND to_date(?, 'YYYY-MM-DD:HH24:MI:SS')";
	
	/** Constante cs_INSERT. */
	private static final String cs_INSERT          = "INSERT INTO SDB_ACC_BITACORA_PROCESO (PROCESO,"
		+ "DESCRIPCION,ID_USUARIO_CREACION,FECHA_CREACION,LLAVE1,LLAVE2,LLAVE3,LLAVE4,LLAVE5,LLAVE6,IP_CREACION) "
		+ "VALUES (?,?,?,SYSTIMESTAMP,?,?,?,?,?,?,?)";

	/**
	 * Instancia un nuevo objeto bitacora proceso DAO.
	 */
	public BitacoraProcesoDAO()
	{
	}

	/**
	 * Metodo para traer de la base de datos todos los registros de acuerdo al filtro de la tabla SDB_ACC_BITACORA_PROCESO.
	 *
	 * @param as_descripcion correspondiente al valor del tipo de objeto String
	 * @param ad_fechaDesde correspondiente al valor del tipo de objeto Date
	 * @param ad_fechaHasta correspondiente al valor del tipo de objeto Date
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * 
	 */
	public Collection<BitacoraProceso> filtroFecha(
	    String as_descripcion, Date ad_fechaDesde, Date ad_fechaHasta, boolean ab_b
	)
	    throws B2BException
	{
		Collection<BitacoraProceso> lcbp_bitacoraProc;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;
		StringBuilder                lsb_sbf;

		lcbp_bitacoraProc     = new ArrayList<BitacoraProceso>();
		lps_ps              = null;
		lrs_rs              = null;
		lsb_sbf             = new StringBuilder(cs_FILTRO_BY_FECHA);

		try
		{
			if(ab_b)
				lsb_sbf = lsb_sbf.append(" ORDER BY SABP.FECHA_CREACION ASC");
			else
				lsb_sbf = lsb_sbf.append(" ORDER BY SABP.FECHA_CREACION ASC ");

			ad_fechaDesde = DateUtils.getCleanSQLDate(ad_fechaDesde);

			String ls_fechaHastaTMP;
			ls_fechaHastaTMP = StringUtils.getString(DateUtils.getCleanSQLDate(ad_fechaHasta)) + ":23:59:59";

			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(lsb_sbf.toString());
			lps_ps.setString(li_contador++, as_descripcion);
			lps_ps.setString(li_contador++, StringUtils.getString(DateUtils.getCleanSQLDate(ad_fechaDesde)));
			lps_ps.setString(li_contador++, ls_fechaHastaTMP);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcbp_bitacoraProc.add(getBitacoraProceso(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "filtroFecha", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcbp_bitacoraProc.isEmpty())
			lcbp_bitacoraProc = null;

		return lcbp_bitacoraProc;
	}

	/**
	 * Método para insertar un registro en la tabla SDB_ACC_BITACORA_PROCESO.
	 *
	 * @param abp_parametros Objeto bitacora proceso a insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(BitacoraProceso abp_parametros)
	    throws B2BException
	{
		if(abp_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, abp_parametros.getProceso());
				lps_ps.setString(li_column++, abp_parametros.getDescripcion());
				lps_ps.setString(li_column++, abp_parametros.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, abp_parametros.getLlave1());
				lps_ps.setString(li_column++, abp_parametros.getLlave2());
				lps_ps.setString(li_column++, abp_parametros.getLlave3());
				lps_ps.setString(li_column++, abp_parametros.getLlave4());
				lps_ps.setString(li_column++, abp_parametros.getLlave5());
				lps_ps.setString(li_column++, abp_parametros.getLlave6());
				lps_ps.setString(li_column++, abp_parametros.getIpCreacion());

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
	 * Método para obtener objeto de tipo bitacora proceso.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de bitacora proceso
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private BitacoraProceso getBitacoraProceso(ResultSet ars_rs)
	    throws SQLException
	{
		BitacoraProceso lbp_datos;
		lbp_datos = new BitacoraProceso();

		lbp_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));

		lbp_datos.setIdEtapa(ars_rs.getString("ETAPA"));
		lbp_datos.setNir(ars_rs.getString("NIR_SOLICITUD"));
		lbp_datos.setIdTurno(ars_rs.getString("ID_TURNO"));
		lbp_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));

		return lbp_datos;
	}
}
