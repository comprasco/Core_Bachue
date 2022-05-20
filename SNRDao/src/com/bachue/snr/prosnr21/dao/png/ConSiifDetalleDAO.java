package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.model.pgn.ConSiifDetalle;
import com.bachue.snr.prosnr21.model.pgn.ConSiifMaestro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_CON_SIIF_DETALLE.
 *
 * @author Kevin Pulido G
 */
public class ConSiifDetalleDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_CONSECUTIVO_MAESTRO = "SELECT * FROM SDB_CON_SIIF_DETALLE WHERE CONSECUTIVO_MAESTRO = ?";

	/**
	 * Find all.
	 *
	 * @param acpa_param de acpa param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ConSiifDetalle> findByConsecutivoMaestro(ConSiifMaestro acpa_param)
	    throws B2BException
	{
		Collection<ConSiifDetalle> lccpa_return;

		lccpa_return = new ArrayList<ConSiifDetalle>();

		if(acpa_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_CONSECUTIVO_MAESTRO);

				lps_ps.setLong(li_column++, acpa_param.getConsecutivoMaestro().longValue());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccpa_return.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByConsecutivoMaestro", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lccpa_return.isEmpty())
			lccpa_return = null;

		return lccpa_return;
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private ConSiifDetalle getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ConSiifDetalle lcpa_datos;

		lcpa_datos = new ConSiifDetalle();

		lcpa_datos.setIdSiifDetalle(ars_rs.getString("ID_SIIF_DETALLE"));
		lcpa_datos.setConsecutivoMaestro(getLong(ars_rs, "CONSECUTIVO_MAESTRO"));
		lcpa_datos.setConsecutivoDetalle(getLong(ars_rs,"CONSECUTIVO_DETALLE"));
		lcpa_datos.setDependenciaAfectacionIng(ars_rs.getString("DEPENDENCIA_AFECTACION_ING"));
		lcpa_datos.setPosicionCatalogoIng(ars_rs.getString("POSICION_CATALOGO_ING"));
		lcpa_datos.setValorMonedaExtranjera(ars_rs.getDouble("VALOR_MONEDA_EXTRANJERA"));
		lcpa_datos.setValorPesos(ars_rs.getDouble("VALOR_PESOS"));

		fillAuditoria(ars_rs, lcpa_datos);

		return lcpa_datos;
	}
}
