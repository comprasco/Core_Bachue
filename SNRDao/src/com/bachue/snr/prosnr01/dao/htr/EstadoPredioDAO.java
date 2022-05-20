package com.bachue.snr.prosnr01.dao.htr;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.htr.EstadoPredio;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas correspondientes a la tabla SDB_HTR_ESTADO_PREDIO
 *
 * @author mblanco
 */
public class EstadoPredioDAO extends BaseDAO
{
	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_HTR_ESTADO_PREDIO_ID_ESTADO_PREDIO.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_ESTADO_PREDIO,ID_CIRCULO,ID_MATRICULA,ID_ESTADO_PREDIO_ANT,FECHA_REGISTRO,"
		+ "JUSTIFICACION_CAMBIO,ID_USUARIO_CREACION,FECHA_CREACION FROM SDB_HTR_ESTADO_PREDIO WHERE ID_ESTADO_PREDIO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_HTR_ESTADO_PREDIO (ID_ESTADO_PREDIO,ID_CIRCULO,ID_MATRICULA,ID_ESTADO_PREDIO_ANT,"
		+ "FECHA_REGISTRO,JUSTIFICACION_CAMBIO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,SYSTIMESTAMP,?,?,SYSTIMESTAMP,?)";

	/**
	 * Retorna el valor de EstadoPredio contenido en el ResultSet
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de EstadoPredio
	 * @see EstadoPredio
	 */
	public EstadoPredio getObjetFromResultSet(ResultSet ars_rs)
	{
		EstadoPredio lep_estadoPredio;

		lep_estadoPredio = new EstadoPredio();

		return lep_estadoPredio;
	}

	/**
	 * Retorna el valor del objeto de tipo  EstadoPredio consultado por ID
	 *
	 * @param at_param correspondiente al valor del tipo de objeto EstadoPredio
	 * @return devuelve el valor del objeto estado predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoPredio
	 */
	public EstadoPredio findById(EstadoPredio at_param)
	    throws B2BException
	{
		EstadoPredio      ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(at_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, at_param.getIdEstadoPredio());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
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

		return ls_object;
	}

	/**
	 * Inserta un nuevo registro en la tabla.
	 *
	 * @param aep_param correspondiente al valor del tipo de objeto EstadoPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(EstadoPredio aep_param)
	    throws B2BException
	{
		if(aep_param != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			li_cont           = 1;
			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				Connection lc_connection;

				lc_connection     = getConnection();

				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					Object lo_o;

					lo_o = lrs_rs.getObject(1);

					if((lo_o != null) && lo_o instanceof BigDecimal)
						aep_param.setIdEstadoPredio(((BigDecimal)lo_o).toString());
				}

				lps_ps.setObject(li_cont++, aep_param.getIdEstadoPredio());
				lps_ps.setString(li_cont++, aep_param.getIdCirculo());
				lps_ps.setObject(li_cont++, aep_param.getIdMatricula());
				lps_ps.setString(li_cont++, aep_param.getIdEstadoPredioAnt());
				lps_ps.setString(li_cont++, aep_param.getJustificacionCambio());
				lps_ps.setString(li_cont++, aep_param.getIdUsuarioCreacion());
				lps_ps.setString(li_cont++, aep_param.getIpCreacion());

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
}
