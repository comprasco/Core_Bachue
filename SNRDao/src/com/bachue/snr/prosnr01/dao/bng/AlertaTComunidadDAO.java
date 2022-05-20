package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.bng.AlertaTComunidad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las propiedades AlertaTComunidadDAO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 2/04/2020
 */
public class AlertaTComunidadDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_BNG_ALERTA_T_COMUNIDAD WHERE ID_ALERTA_TIERRA = ? AND ID_COMUNIDAD_ETNICA = ?";

	/** Constante cs_FIND_FIRST_BY_ID_ALERTA_TIERRA. */
	private static final String cs_FIND_FIRST_BY_ID_ALERTA_TIERRA = "SELECT * FROM SDB_BNG_ALERTA_T_COMUNIDAD WHERE ID_ALERTA_TIERRA = ? ORDER BY ID_COMUNIDAD_ETNICA ASC";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_BNG_ALERTA_T_COMUNIDAD (ID_ALERTA_TIERRA,ID_COMUNIDAD_ETNICA,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_DELETE_BY_ID_ALERTA_TIERRAS. */
	private static final String cs_DELETE_BY_ID_ALERTA_TIERRAS = "DELETE FROM SDB_BNG_ALERTA_T_COMUNIDAD WHERE ID_ALERTA_TIERRA = ?";

	/**
	 * Find by id.
	 *
	 * @param al_idAlertaTierra de al id alerta tierra
	 * @param al_idComunidadEtnica de al id comunidad etnica
	 * @return el valor de alerta T comunidad
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AlertaTComunidad findById(long al_idAlertaTierra, long al_idComunidadEtnica)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		AlertaTComunidad  latc_alertaTComunidad;

		lps_ps                    = null;
		lrs_rs                    = null;
		latc_alertaTComunidad     = null;

		try
		{
			int li_contador;

			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setLong(li_contador++, al_idAlertaTierra);
			lps_ps.setLong(li_contador++, al_idComunidadEtnica);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				latc_alertaTComunidad = getObjetFromResultSet(lrs_rs);
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

		return latc_alertaTComunidad;
	}

	/**
	 * Find first by id alerta tierra.
	 *
	 * @param al_idAlertaTierra de al id alerta tierra
	 * @return el valor de alerta T comunidad
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AlertaTComunidad findFirstByIdAlertaTierra(long al_idAlertaTierra)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		AlertaTComunidad  latc_alertaTComunidad;

		lps_ps                    = null;
		lrs_rs                    = null;
		latc_alertaTComunidad     = null;

		try
		{
			int li_contador;

			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_FIRST_BY_ID_ALERTA_TIERRA);

			lps_ps.setLong(li_contador++, al_idAlertaTierra);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				latc_alertaTComunidad = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findFirstByIdAlertaTierra", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return latc_alertaTComunidad;
	}

	/**
	 * Insert.
	 *
	 * @param aatc_parametro de aatc parametro
	 * @return el valor de long
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public long insert(AlertaTComunidad aatc_parametro)
	    throws B2BException
	{
		long ll_idAlertaTierras;

		ll_idAlertaTierras = 0;

		if(aatc_parametro != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setLong(li_column++, aatc_parametro.getIdAlertaTierra());
				lps_ps.setLong(li_column++, aatc_parametro.getIdComunidadEtnica());
				lps_ps.setString(li_column++, aatc_parametro.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aatc_parametro.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}

		return ll_idAlertaTierras;
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
	private AlertaTComunidad getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AlertaTComunidad latc_alertaTComunidad;

		latc_alertaTComunidad = new AlertaTComunidad();

		latc_alertaTComunidad.setIdAlertaTierra(ars_rs.getLong("ID_ALERTA_TIERRA"));
		latc_alertaTComunidad.setIdComunidadEtnica(ars_rs.getLong("ID_COMUNIDAD_ETNICA"));

		fillAuditoria(ars_rs, latc_alertaTComunidad);

		return latc_alertaTComunidad;
	}
}
