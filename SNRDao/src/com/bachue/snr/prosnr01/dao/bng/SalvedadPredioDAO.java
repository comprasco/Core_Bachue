package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.bng.SalvedadPredio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_BNG_SALVEDAD_PREDIO
 *
 * @author Manuel Blanco
 *
 */
public class SalvedadPredioDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT * FROM SDB_BNG_SALVEDAD_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/**
	 * Consulta los registros asociados a un id círculo e id matrícula específicos
	 *
	 * @param asp_param Objeto contenedor de los filtros a utilizar en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<SalvedadPredio> findByCirculoMatricula(SalvedadPredio asp_param)
	    throws B2BException
	{
		return (asp_param != null) ? findByCirculoMatricula(asp_param.getIdCirculo(), asp_param.getIdMatricula()) : null;
	}

	/**
	 * Consulta los registros asociados a un id círculo e id matrícula específicos
	 *
	 * @param asp_param Objeto contenedor de los filtros a utilizar en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<SalvedadPredio> findByCirculoMatricula(String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		Collection<SalvedadPredio> lcsp_object;

		lcsp_object = new LinkedList<SalvedadPredio>();

		if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);

				lps_ps.setString(li_cont++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_cont++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsp_object.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcsp_object.isEmpty())
			lcsp_object = null;

		return lcsp_object;
	}

	/**
	 * Extrae la información resultante de la consulta
	 *
	 * @param ars_rs Objeto contenedor del resultado de la consulta
	 * @return Objeto SalvedadPredio resultante de la consulta
	 * @throws SQLException
	 */
	private SalvedadPredio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		SalvedadPredio lsp_datos;

		lsp_datos = new SalvedadPredio();

		lsp_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lsp_datos.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		lsp_datos.setIdSalvedadPredio(getLong(ars_rs, "ID_SALVEDAD_PREDIO"));
		lsp_datos.setFechaRegistro(ars_rs.getTimestamp("FECHA_REGISTRO"));
		lsp_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lsp_datos.setRadicacion(ars_rs.getString("RADICACION"));
		lsp_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lsp_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lsp_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lsp_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lsp_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lsp_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lsp_datos;
	}
}
