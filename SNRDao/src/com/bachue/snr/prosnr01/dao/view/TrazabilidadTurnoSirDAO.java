package com.bachue.snr.prosnr01.dao.view;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.view.TrazabilidadTurnoSir;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades TrazabilidadTurnoSirDAO.
 *
 * @author  Sebastian Sanchez
 */
public class TrazabilidadTurnoSirDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL_BY_TURNO_ACTUALIZADO. */
	private static final String cs_FIND_ALL_BY_TURNO_ACTUALIZADO = "SELECT * FROM SDB_VW_TRAZABILIDAD_TURNO_SIR WHERE ID_TURNO = ?";

	/**
	 * Find all by turno actualizado.
	 *
	 * @param as_turnoActualizado de as turno actualizado
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TrazabilidadTurnoSir> findAllByTurnoActualizado(String as_turnoActualizado)
	    throws B2BException
	{
		Collection<TrazabilidadTurnoSir> lctts_trazabilidadTurno;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;

		lctts_trazabilidadTurno     = new ArrayList<TrazabilidadTurnoSir>();
		lps_ps                      = null;
		lrs_rs                      = null;

		if(StringUtils.isValidString(as_turnoActualizado))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_TURNO_ACTUALIZADO);

				lps_ps.setString(1, as_turnoActualizado);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lctts_trazabilidadTurno.add(getObjectFromResultSet(lrs_rs));
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

		if(lctts_trazabilidadTurno.isEmpty())
			lctts_trazabilidadTurno = null;

		return lctts_trazabilidadTurno;
	}

	/**
	 * Retorna Objeto o variable de valor object from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de object from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private TrazabilidadTurnoSir getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TrazabilidadTurnoSir lr_datos;

		lr_datos = new TrazabilidadTurnoSir();

		lr_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lr_datos.setNombre(ars_rs.getString("NOMBRE"));
		lr_datos.setTurnoActualizado(ars_rs.getString("ID_TURNO"));
		lr_datos.setTurnoActualizadoAsociado(ars_rs.getString("ID_TURNO_ASOCIADO"));
		lr_datos.setNroRadica(ars_rs.getString("NRORADICA"));
		lr_datos.setDescripcionEstado(ars_rs.getString("DESCRIPCION_ESTADO"));
		lr_datos.setUsuario(ars_rs.getString("USUARIO"));
		lr_datos.setEstado(ars_rs.getString("ESTADO"));
		lr_datos.setFecentra(ars_rs.getTimestamp("FECENTRA"));
		lr_datos.setLogCertiCorrec(ars_rs.getString("LOG_CERTI_CORREC"));

		return lr_datos;
	}
}
