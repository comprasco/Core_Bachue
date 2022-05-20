package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.bng.SolicitudCorreccion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las propiedades SolicitudCorreccionDAO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 2/04/2020
 */
public class SolicitudCorreccionDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_BNG_SOLICITUD_CORRECCION WHERE ID_ALERTA_TIERRA = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_BNG_SOLICITUD_CORRECCION SET ID_CIRCULO = ?,ID_TURNO = ?,ESTADO = ?,TEXTO = ?,ID_USUARIO_MODIFICACION = ?,FECHA_MODIFICACION = SYSTIMESTAMP,IP_MODIFICACION = ? WHERE ID_ALERTA_TIERRA = ?";

	/** Constante cs_DELETE_BY_ID_ALERTA_TIERRAS. */
	private static final String cs_DELETE_BY_ID_ALERTA_TIERRAS = "DELETE FROM SDB_BNG_SOLICITUD_CORRECCION WHERE ID_ALERTA_TIERRA = ?";

	/**
	 * Find by id.
	 *
	 * @param al_idAlertaTierra de al id alerta tierra
	 * @return el valor de solicitud correccion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SolicitudCorreccion findById(long al_idAlertaTierra)
	    throws B2BException
	{
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;
		SolicitudCorreccion lsc_solicitudCorreccion;

		lps_ps                      = null;
		lrs_rs                      = null;
		lsc_solicitudCorreccion     = null;

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setLong(li_column++, al_idAlertaTierra);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lsc_solicitudCorreccion = getObjetFromResultSet(lrs_rs);
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

		return lsc_solicitudCorreccion;
	}

	/**
	 * Update.
	 *
	 * @param asc_parametro de asc parametro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(SolicitudCorreccion asc_parametro)
	    throws B2BException
	{
		if(asc_parametro != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, asc_parametro.getIdCirculo());
				lps_ps.setString(li_column++, asc_parametro.getIdTurno());
				lps_ps.setString(li_column++, asc_parametro.getEstado());
				lps_ps.setString(li_column++, asc_parametro.getTexto());
				lps_ps.setString(li_column++, asc_parametro.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, asc_parametro.getIpModificacion());
				lps_ps.setLong(li_column++, asc_parametro.getIdAlertaTierra());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Delete by id alerta tierras.
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
	private SolicitudCorreccion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudCorreccion lsc_solicitudCorreccion;

		lsc_solicitudCorreccion = new SolicitudCorreccion();

		lsc_solicitudCorreccion.setIdAlertaTierra(ars_rs.getLong("ID_ALERTA_TIERRA"));
		lsc_solicitudCorreccion.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lsc_solicitudCorreccion.setIdTurno(ars_rs.getString("ID_TURNO"));
		lsc_solicitudCorreccion.setEstado(ars_rs.getString("ESTADO"));
		lsc_solicitudCorreccion.setTexto(ars_rs.getString("TEXTO"));

		fillAuditoria(ars_rs, lsc_solicitudCorreccion);

		return lsc_solicitudCorreccion;
	}
}
