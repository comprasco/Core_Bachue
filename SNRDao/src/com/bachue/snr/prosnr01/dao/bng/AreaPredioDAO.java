package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas de SDB_BNG_AREA_PREDIO
 *
 * @author mblanco
 */
public class AreaPredioDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_BNG_AREA_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_BNG_AREA_PREDIO SET ID_ANOTACION = ?, COEFICIENTE = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, "
		+ "IP_MODIFICACION = ? WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_AREA = ? ";

	/** Constante cs_AREA_TOTAL_TERRENO. */
	private static final String cs_AREA_TOTAL_TERRENO = " SELECT DAP.AREA FROM SDB_BNG_AREA_PREDIO AP INNER JOIN SDB_BNG_DETALLE_AREA_PREDIO DAP ON DAP.ID_AREA_PREDIO =  ID_AREA AND ID_TIPO_AREA = 1"
		+ "  WHERE AP.ID_CIRCULO = ? AND  AP.ID_MATRICULA = ? ";

	/**
	 * Retorna el valor del area del terreno filtrado por el id circulo, id matricula
	 *
	 * @param ap_param correspondiente al valor del tipo de objeto AreaPredio
	 * @return devuelve el valor del objeto double
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public double areaTotalTerreno(AreaPredio ap_param)
	    throws B2BException
	{
		double            ld_d;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_count;

		ld_d         = 0;
		lps_ps       = null;
		lrs_rs       = null;
		li_count     = 1;

		if(ap_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_AREA_TOTAL_TERRENO);

				lps_ps.setString(li_count++, ap_param.getIdCirculo());
				lps_ps.setLong(li_count++, ap_param.getIdMatricula());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ld_d = lrs_rs.getDouble("AREA");
			}
			catch(SQLException lse_e)
			{
				logError(this, "areaTotalTerreno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ld_d;
	}

	/**
	 * Retorna el valor del objeto de tipo AreaPredio filtrado por id circulo, id matricula
	 *
	 * @param alp_param correspondiente al valor del tipo de objeto AreaPredio
	 * @return devuelve el valor del objeto area predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AreaPredio
	 */
	public AreaPredio findById(AreaPredio alp_param)
	    throws B2BException
	{
		return (alp_param != null) ? findById(alp_param.getIdCirculo(), alp_param.getIdMatricula()) : null;
	}

	/**
	 * Retorna el valor del objeto de tipo AreaPredio filtrado por ID
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto long
	 * @return devuelve el valor del objeto area predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AreaPredio
	 */
	public AreaPredio findById(String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		AreaPredio        lpr_Predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lpr_Predio     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0))
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, as_idCirculo);
				lps_ps.setLong(li_contador++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpr_Predio = getObjetFromResultSet(lrs_rs);
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

		return lpr_Predio;
	}

	/**
	 * Actualiza el registro de area predio.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto AreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateAreaPredio(AreaPredio aap_param)
	    throws B2BException
	{
		if(aap_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setBigDecimal(li_column++, aap_param.getIdAnotacion());
				lps_ps.setBigDecimal(li_column++, aap_param.getCoeficiente());
				lps_ps.setString(li_column++, aap_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aap_param.getIpModificacion());
				lps_ps.setString(li_column++, aap_param.getIdCirculo());
				lps_ps.setLong(li_column++, aap_param.getIdMatricula());
				lps_ps.setLong(li_column++, aap_param.getIdArea());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor de AreaPredio
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de AreaPredio
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see AreaPredio
	 */
	private AreaPredio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AreaPredio laap_areaPredio;

		laap_areaPredio = new AreaPredio();

		laap_areaPredio.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		laap_areaPredio.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		laap_areaPredio.setIdArea(ars_rs.getLong("ID_AREA"));
		laap_areaPredio.setIdAnotacion(getBigDecimal(ars_rs, "ID_ANOTACION"));
		laap_areaPredio.setCoeficiente(getBigDecimal(ars_rs, "COEFICIENTE"));
		laap_areaPredio.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		laap_areaPredio.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));

		return laap_areaPredio;
	}
}
