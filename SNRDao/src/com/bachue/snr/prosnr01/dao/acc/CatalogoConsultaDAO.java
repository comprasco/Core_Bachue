package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.CatalogoConsulta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase encargada del manejo de transacciones relacionadas a la tabla SDB_ACC_CATALOGO_CONSULTA.
 *
 * @author Manuel Blanco
 */
public class CatalogoConsultaDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_CATALOGO_CONSULTA WHERE CATALOGO = ?";

	/**
	 * Busca en la base de datos un registro asociado a un id específico.
	 *
	 * @param as_catalogo Objeto contenedor del id a utilizar como filtro en la consulta
	 * @return CatalogoConsulta resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CatalogoConsulta
	 */
	public CatalogoConsulta findById(String as_catalogo)
	    throws B2BException
	{
		CatalogoConsulta lcc_object;

		lcc_object = null;

		if(StringUtils.isValidString(as_catalogo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_catalogo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcc_object = getObjectFromResultSet(lrs_rs);
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

		return lcc_object;
	}

	/**
	 * Obtiene los datos resultantes de una consulta.
	 *
	 * @param ars_rs Objeto contenedor de los resultados de una consulta
	 * @return CatalogoConsulta resultante de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CatalogoConsulta getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CatalogoConsulta lcc_catalogoConsulta;

		lcc_catalogoConsulta = new CatalogoConsulta();

		lcc_catalogoConsulta.setCatalogo(ars_rs.getString("CATALOGO"));
		lcc_catalogoConsulta.setComponente(ars_rs.getString("NOMBRE_COMPONENTE"));
		lcc_catalogoConsulta.setProcedimiento(ars_rs.getString("PROCEDIMIENTO"));

		fillAuditoria(ars_rs, lcc_catalogoConsulta);

		return lcc_catalogoConsulta;
	}
}
