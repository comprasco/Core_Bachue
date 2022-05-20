package com.bachue.snr.prosnr01.dao.view;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.view.TrazabilidadTurnoSirNotaDevolutiva;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades TrazabilidadTurnoSirNotaDevolutivaDAO.
 *
 * @author  Sebastian Sanchez
 */
public class TrazabilidadTurnoSirNotaDevolutivaDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL_BY_TURNO_ACTUALIZADO. */
	private static final String cs_FIND_ALL_BY_TURNO_ACTUALIZADO = "SELECT * FROM SDB_VW_TRAZABILIDAD_TURNO_SIR_NOTA_DEVOLUTIVA WHERE TURNO_ACTUALIZADO = ?";

	/**
	 * Find all by turno actualizado.
	 *
	 * @param as_turnoActualizado de as turno actualizado
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TrazabilidadTurnoSirNotaDevolutiva> findAllByTurnoActualizado(String as_turnoActualizado)
	    throws B2BException
	{
		Collection<TrazabilidadTurnoSirNotaDevolutiva> lcttsnd_trazabilidadTurno;
		PreparedStatement                              lps_ps;
		ResultSet                                      lrs_rs;

		lcttsnd_trazabilidadTurno     = new ArrayList<TrazabilidadTurnoSirNotaDevolutiva>();
		lps_ps                        = null;
		lrs_rs                        = null;

		if(StringUtils.isValidString(as_turnoActualizado))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_TURNO_ACTUALIZADO);

				lps_ps.setString(1, as_turnoActualizado);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcttsnd_trazabilidadTurno.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByTurnoActualizado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		if(lcttsnd_trazabilidadTurno.isEmpty())
			lcttsnd_trazabilidadTurno = null;

		return lcttsnd_trazabilidadTurno;
	}

	/**
	 * Retorna Objeto o variable de valor object from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de object from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private TrazabilidadTurnoSirNotaDevolutiva getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TrazabilidadTurnoSirNotaDevolutiva lr_datos;

		lr_datos = new TrazabilidadTurnoSirNotaDevolutiva();

		lr_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lr_datos.setTurnoActualizado(ars_rs.getString("TURNO_ACTUALIZADO"));
		lr_datos.setTipo(ars_rs.getString("TIPO"));
		lr_datos.setFecha(ars_rs.getTimestamp("FECHA"));
		lr_datos.setCodigo(ars_rs.getDouble("CODIGO"));
		lr_datos.setCausalDevolucionComentario(ars_rs.getString("CAUSAL_DEVOLUCION_COMENTARIO"));

		return lr_datos;
	}
}
