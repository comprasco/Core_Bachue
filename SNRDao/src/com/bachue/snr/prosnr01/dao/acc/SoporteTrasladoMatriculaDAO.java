package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.SoporteTrasladoMatricula;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para el manejo de datos para la tabla SDB_ACC_SOPORTE_TRASLADO_MATRICULA.
 *
 * @author Jorge Patiño
 */
public class SoporteTrasladoMatriculaDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID_SOPORTE_TRASLADO. */
	private static final String cs_FIND_BY_ID_SOPORTE_TRASLADO = "SELECT * FROM SDB_ACC_SOPORTE_TRASLADO_MATRICULA WHERE ID_SOPORTE_TRASLADO = ?";

	/** Constante cs_FIND_BY_ID_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_ID_CIRCULO_MATRICULA = "SELECT * FROM SDB_ACC_SOPORTE_TRASLADO_MATRICULA WHERE ID_CIRCULO_DESTINO = ? AND ID_MATRICULA_DESTINO = ? ";

	/**
	 * Busca registros de la tabla por medio de su id circulo y id matricula.
	 *
	 * @param as_idCirculo Objeto contenedor de los filtros a usar en la consulta
	 * @param al_idMatricula Objeto contenedor de los filtros a usar en la consulta
	 * @return SoporteTrasladoMatricula resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SoporteTrasladoMatricula findByIdCirculoMatricula(String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		SoporteTrasladoMatricula lcsp_return;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcsp_return     = null;
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idCirculo)
				    && NumericUtils.isValidLong(NumericUtils.getLongWrapper(al_idMatricula))
			)
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_CIRCULO_MATRICULA);

				lps_ps.setString(li_column++, as_idCirculo);
				lps_ps.setLong(li_column++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcsp_return = getObjectFromResultSet(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdCirculoMatricula", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcsp_return;
	}

	/**
	 * Busca registros de la tabla por medio de su id_identificador.
	 *
	 * @param as_idSoporte Objeto contenedor de los filtros a usar en la consulta
	 * @return Collection de SoporteTrasladoMatricula resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SoporteTrasladoMatricula> findByIdSoporteTraslado(String as_idSoporte)
	    throws B2BException
	{
		Collection<SoporteTrasladoMatricula> lcsp_return;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lcsp_return     = new ArrayList<SoporteTrasladoMatricula>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(StringUtils.isValidString(as_idSoporte))
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_SOPORTE_TRASLADO);

				lps_ps.setString(li_column++, as_idSoporte);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsp_return.add(getObjectFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdSoporteTraslado", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcsp_return.isEmpty())
			lcsp_return = null;

		return lcsp_return;
	}

	/**
	 * Método que construye el modelo de la tabla consultada.
	 *
	 * @param ars_rs ResultSet que contiene el resultado de la búsqueda.
	 * @return Objeto que contiene la información consultada.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SoporteTrasladoMatricula getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		SoporteTrasladoMatricula lstm_return;

		lstm_return = new SoporteTrasladoMatricula();

		lstm_return.setIdSoporteTrasladoMatricula(ars_rs.getString("ID_SOPORTE_TRASLADO_MATRICULA"));
		lstm_return.setIdSoporteTraslado(ars_rs.getString("ID_SOPORTE_TRASLADO"));
		lstm_return.setIdCirculoOrigen(ars_rs.getString("ID_CIRCULO_ORIGEN"));
		lstm_return.setMatriculaOrigen(getLong(ars_rs, "ID_MATRICULA_ORIGEN"));
		lstm_return.setIdCirculoDestino(ars_rs.getString("ID_CIRCULO_DESTINO"));
		lstm_return.setMatriculaDestino(getLong(ars_rs, "ID_MATRICULA_DESTINO"));

		fillAuditoria(ars_rs, lstm_return);

		return lstm_return;
	}
}
