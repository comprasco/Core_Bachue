package com.bachue.snr.prosnr01.dao.bgn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.bng.AlertaGenerada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades AlertaGeneradaDAO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 6/04/2020
 */
public class AlertaGeneradaDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID_ALERTA_TIERRAS. */
	private static final String cs_FIND_BY_ID_ALERTA_TIERRAS = "SELECT AG.*, AMSG.MENSAJE, AMSG.USUARIO_ORIGINA, CR.NOMBRE AS NOMBRE_CIRCULO"
		+ " FROM SDB_BNG_ALERTA_GENERADA AG"
		+ " INNER JOIN SBD_BNG_ALERTA_MSG AMSG ON AG.ID_ALERTA_TIERRA = AMSG.ID_ALERTA_TIERRA"
		+ " INNER JOIN SDB_PGN_CIRCULO_REGISTRAL CR ON AG.ID_CIRCULO = CR.ID_CIRCULO"
		+ " WHERE AG.ID_ALERTA_TIERRA = ?";

	/** Constante cs_DELETE_BY_ID_ALERTA_TIERRAS. */
	private static final String cs_DELETE_BY_ID_ALERTA_TIERRAS = "DELETE FROM SDB_BNG_ALERTA_GENERADA WHERE ID_ALERTA_TIERRA = ?";

	/**
	 * Find by id alerta tierras.
	 *
	 * @param al_idAlertaTierras de al id alerta tierras
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AlertaGenerada> findByIdAlertaTierras(long al_idAlertaTierras)
	    throws B2BException
	{
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;
		Collection<AlertaGenerada> lcag_alertaGenerada;

		lps_ps                  = null;
		lrs_rs                  = null;
		lcag_alertaGenerada     = new ArrayList<AlertaGenerada>();

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ALERTA_TIERRAS);

			lps_ps.setLong(li_column++, al_idAlertaTierras);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcag_alertaGenerada.add(getObjetMensaje(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdAlertaTierras", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcag_alertaGenerada;
	}

	/**
	 * Delete by id alertas tierras.
	 *
	 * @param al_idAlertaTierras de al id alerta tierras
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void deleteByIdAlertaTierras(long al_idAlertaTierras)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		try
		{
			int li_column;

			li_column     = 1;
			lps_ps        = getConnection().prepareStatement(cs_DELETE_BY_ID_ALERTA_TIERRAS);

			lps_ps.setLong(li_column++, al_idAlertaTierras);

			lps_ps.executeUpdate();
		}
		catch(SQLException lse_e)
		{
			logError(this, "deleteByIdAlertasTierras", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
		}
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private AlertaGenerada getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AlertaGenerada lag_alertaGenerada;

		lag_alertaGenerada = new AlertaGenerada();

		lag_alertaGenerada.setIdAlertaTierra(ars_rs.getLong("ID_ALERTA_TIERRA"));
		lag_alertaGenerada.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lag_alertaGenerada.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lag_alertaGenerada.setIdTurno(ars_rs.getString("ID_TURNO"));

		fillAuditoria(ars_rs, lag_alertaGenerada);

		return lag_alertaGenerada;
	}

	/**
	 * Retorna Objeto o variable de valor objet mensaje.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet mensaje
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private AlertaGenerada getObjetMensaje(ResultSet ars_rs)
	    throws SQLException
	{
		AlertaGenerada lag_alertaGenerada;

		lag_alertaGenerada = getObjetFromResultSet(ars_rs);

		lag_alertaGenerada.setMensaje(ars_rs.getString("MENSAJE"));
		lag_alertaGenerada.setUsuarioOrigina(ars_rs.getString("USUARIO_ORIGINA"));
		lag_alertaGenerada.setNombreCirculo(ars_rs.getString("NOMBRE_CIRCULO"));

		return lag_alertaGenerada;
	}
}
