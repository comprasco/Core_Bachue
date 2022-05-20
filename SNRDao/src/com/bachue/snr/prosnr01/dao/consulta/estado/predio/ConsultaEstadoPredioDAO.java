package com.bachue.snr.prosnr01.dao.consulta.estado.predio;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas para circulo matricula
 *
 * @author Julian Vaca
 */
public class ConsultaEstadoPredioDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT sbpr.ID_MATRICULA, spcr.ID_CIRCULO FROM SDB_BNG_PREDIO_REGISTRO sbpr "
		+ "INNER JOIN SDB_PGN_CIRCULO_REGISTRAL spcr ON (spcr.ID_CIRCULO=sbpr.ID_CIRCULO) WHERE sbpr.ID_MATRICULA = ? AND sbpr.ID_CIRCULO = ?";

	/**
	 * Retorna el valor del objeto de tipo SolicitudMatricula consultado por el id matricula y
	 * el id circulo
	 *
	 * @param asm_solicitudMatricula correspondiente al valor del tipo de objeto SolicitudMatricula
	 * @return devuelve el valor del objeto solicitud matricula
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	public SolicitudMatricula findEstado(SolicitudMatricula asm_solicitudMatricula)
	    throws B2BException
	{
		SolicitudMatricula lsm_solicitudMatricula;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lsm_solicitudMatricula     = null;
		lps_ps                     = null;
		lrs_rs                     = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);
			lps_ps.setLong(li_contador++, asm_solicitudMatricula.getIdMatricula());
			lps_ps.setString(li_contador++, asm_solicitudMatricula.getIdCirculo());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lsm_solicitudMatricula = getSolicitudMatricula(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findEstado", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		return lsm_solicitudMatricula;
	}

	/**
	 * Retorna el valor de solicitud matricula.
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de solicitud matricula
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see SolicitudMatricula
	 */
	private SolicitudMatricula getSolicitudMatricula(ResultSet lrs_rs)
	    throws SQLException
	{
		SolicitudMatricula lsm_sm;

		lsm_sm = new SolicitudMatricula();

		lsm_sm.setIdCirculo(lrs_rs.getString("ID_CIRCULO"));
		lsm_sm.setIdMatricula(lrs_rs.getLong("ID_MATRICULA"));

		return lsm_sm;
	}
}
