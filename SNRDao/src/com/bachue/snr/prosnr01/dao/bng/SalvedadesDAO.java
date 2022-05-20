package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.bng.ConsultaSalvedad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_BNG_SALVEDAD_ANOTACION
 *
 * @author Julian Vaca
 */
public class SalvedadesDAO extends BaseDAO
{
	/** Constante cs_FIND_COMPUESTO. */
	private static final String cs_FIND_COMPUESTO = "SELECT ID_SALVEDAD_PREDIO ID_SALVEDAD, FECHA_REGISTRO, ID_CIRCULO, "
		+ "ID_MATRICULA, 0 ID_ANOTACION, DESCRIPCION, RADICACION FROM SDB_BNG_SALVEDAD_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? "
		+ "UNION SELECT ID_SALVEDAD_ANOTACION ID_SALVEDAD, FECHA_REGISTRO,ID_CIRCULO, ID_MATRICULA, ID_ANOTACION, DESCRIPCION, RADICACION "
		+ "FROM SDB_BNG_SALVEDAD_ANOTACION WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? ORDER BY FECHA_REGISTRO";

	/**
	 * Retorna el valor del objeto de tipo Collection de ConsultaSalvedad filtrado por el id del circulo,
	 * id matricula y ordenado por fecha registro
	 *
	 * @param acs_param correspondiente al valor del tipo de objeto ConsultaSalvedad
	 * @return devuelve el valor del objeto collection de ConsultaSalvedad
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ConsultaSalvedad
	 */
	public Collection<ConsultaSalvedad> findCompuesto(ConsultaSalvedad acs_param)
	    throws B2BException
	{
		Collection<ConsultaSalvedad> lcs_consultaSalvedad;
		int                          li_column;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lcs_consultaSalvedad     = new ArrayList<ConsultaSalvedad>();
		li_column                = 1;
		lps_ps                   = null;
		lrs_rs                   = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_COMPUESTO);

			lps_ps.setString(li_column++, acs_param.getIdCirculo());
			setLong(lps_ps, acs_param.getIdMatricula(), li_column++);
			lps_ps.setString(li_column++, acs_param.getIdCirculo());
			setLong(lps_ps, acs_param.getIdMatricula(), li_column++);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcs_consultaSalvedad.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findCompuesto", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcs_consultaSalvedad;
	}

	/**
	 * Retorna el valor de ConsultaSalvedad
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de ConsultaSalvedad
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see ConsultaSalvedad
	 */
	private ConsultaSalvedad getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ConsultaSalvedad lcs_consultaSalvedad;

		lcs_consultaSalvedad = new ConsultaSalvedad();

		lcs_consultaSalvedad.setIdSalvedad(getLong(ars_rs, "ID_SALVEDAD"));
		lcs_consultaSalvedad.setFechaRegistro(ars_rs.getTimestamp("FECHA_REGISTRO"));
		lcs_consultaSalvedad.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lcs_consultaSalvedad.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		lcs_consultaSalvedad.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		lcs_consultaSalvedad.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lcs_consultaSalvedad.setRadicacion(ars_rs.getString("RADICACION"));

		return lcs_consultaSalvedad;
	}
}
