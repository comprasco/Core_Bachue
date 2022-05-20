package com.bachue.snr.prosnr01.dao.view;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades de ViewDataReportDAO.
 *
 * @author ccalderon
 */
public class ViewDataReportDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_FIND_BY_VIEW. */
	private static final String cs_FIND_BY_VIEW = "SELECT * FROM ";

	/** Propiedad cs ODE R B Y A P I D ANOTACIO N DESC. */
	public final String cs_ODER_BY_AP_ID_ANOTACION_DESC = " AP_ID_ANOTACION DESC ";

	/** Propiedad cs WHER E BALDIO S I D CIRCUL O MATRICULA. */
	public final String cs_WHERE_BALDIOS_ID_CIRCULO_MATRICULA = " WHERE AP_ID_NATURALEZA_JURIDICA = ? AND AP_ID_CIRCULO = ? AND AP_ID_MATRICULA = ?";

	/** Propiedad cs WHER E BALDIO S I D PERSON A ROL. */
	public final String cs_WHERE_BALDIOS_ID_PERSONA_ROL = " WHERE AP_ID_NATURALEZA_JURIDICA = ? AND P_ID_PERSONA = ? AND APC_ROL_PERSONA = ? ";

	/** Propiedad cs WHER E BALDIO S I D PERSON A RO L PROPIETARIO. */
	public final String cs_WHERE_BALDIOS_ID_PERSONA_ROL_PROPIETARIO = " WHERE AP_ID_NATURALEZA_JURIDICA = ? AND P_ID_PERSONA = ? AND APC_ROL_PERSONA = ? AND APC_PROPIETARIO = ? ";

	/** Propiedad cs WHER E BALDIO S NOMBRE. */
	public final String cs_WHERE_BALDIOS_NOMBRE = " WHERE AP_ID_NATURALEZA_JURIDICA = ? AND APC_ROL_PERSONA = ? AND APC_PROPIETARIO = ? AND P_PRIMER_NOMBRE = ? AND P_PRIMER_APELLIDO = ? ";

	/** Propiedad cs WHER E BALDIO S NOMBR E COMPLET O RO L PROPIETARIO. */
	public final String cs_WHERE_BALDIOS_NOMBRE_COMPLETO_ROL_PROPIETARIO = " WHERE AP_ID_NATURALEZA_JURIDICA = ? AND APC_ROL_PERSONA = ? AND APC_PROPIETARIO = ? AND P_PRIMER_NOMBRE = ? AND P_PRIMER_APELLIDO = ? AND P_SEGUNDO_NOMBRE = ? AND P_SEGUNDO_APELLIDO = ? ";

	/** Propiedad cs WHER E BALDIO S NOMBR E SEGUND O APELLID O RO L PROPIETARIO. */
	public final String cs_WHERE_BALDIOS_NOMBRE_SEGUNDO_APELLIDO_ROL_PROPIETARIO = " WHERE AP_ID_NATURALEZA_JURIDICA = ? AND APC_ROL_PERSONA = ? AND APC_PROPIETARIO = ? AND P_PRIMER_NOMBRE = ? AND P_PRIMER_APELLIDO = ? AND P_SEGUNDO_APELLIDO = ? ";

	/** Propiedad cs WHER E BALDIO S NOMBR E SEGUND O NOMBR E RO L PROPIETARIO. */
	public final String cs_WHERE_BALDIOS_NOMBRE_SEGUNDO_NOMBRE_ROL_PROPIETARIO = " WHERE AP_ID_NATURALEZA_JURIDICA = ? AND APC_ROL_PERSONA = ? AND APC_PROPIETARIO = ? AND P_PRIMER_NOMBRE = ? AND P_PRIMER_APELLIDO = ? AND P_SEGUNDO_NOMBRE = ? ";

	/**
	 * Retorna el valor del objeto Collection buscando por el nombre de la vista
	 *
	 * @param as_view correspondiente al valor del tipo de objeto String
	 * @param as_order correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Map<String, Object>> findByViewName(String as_view, String as_order)
	    throws B2BException
	{
		return findByViewName(as_view, as_order, null, null);
	}

	/**
	 * Retorna el valor del objeto de tipo Collection
	 *
	 * @param as_view correspondiente al valor del tipo de objeto String
	 * @param as_order correspondiente al valor del tipo de objeto String
	 * @param as_where correspondiente al valor del tipo de objeto String
	 * @param amio_criterios correspondiente al valor del tipo de objeto Map<Integer,Object>
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Map<String, Object>> findByViewName(
	    String as_view, String as_order, String as_where, Map<Integer, Object> amio_criterios
	)
	    throws B2BException
	{
		Collection<Map<String, Object>> lcmso_records;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lcmso_records     = new ArrayList<Map<String, Object>>();
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			int               li_columns;
			ResultSetMetaData lrmd_columns;
			StringBuilder     lsb_query;

			li_columns       = 0;
			lrmd_columns     = null;
			lsb_query        = new StringBuilder();

			lsb_query.append(cs_FIND_BY_VIEW + as_view);

			if(StringUtils.isValidString(as_where))
				lsb_query.append(as_where);

			if(StringUtils.isValidString(as_order))
				lsb_query.append(" ORDER BY " + as_order);
			else
				lsb_query.append(" ORDER BY 1,2");

			lps_ps = getConnection().prepareStatement(lsb_query.toString());

			if(CollectionUtils.isValidCollection(amio_criterios))
			{
				for(Map.Entry<Integer, Object> lmeio_criterios : amio_criterios.entrySet())
				{
					if(lmeio_criterios != null)
						lps_ps.setObject(NumericUtils.getInt(lmeio_criterios.getKey()), lmeio_criterios.getValue());
				}
			}

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs != null)
			{
				lrmd_columns     = lrs_rs.getMetaData();
				li_columns       = lrmd_columns.getColumnCount();
			}

			if(lrmd_columns != null)
			{
				while(lrs_rs.next())
				{
					Map<String, Object> lhmso_row;

					lhmso_row = new LinkedHashMap();

					for(int li_i = 1; li_i <= li_columns; li_i++)
						lhmso_row.put(lrmd_columns.getColumnName(li_i), lrs_rs.getObject(li_i));

					lcmso_records.add(lhmso_row);
				}
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByViewName", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcmso_records;
	}
}
