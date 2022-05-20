package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.bng.AlertaTInactivacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las propiedades AlertaTInactivacionDAO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 2/04/2020
 */
public class AlertaTInactivacionDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ATI.*, OO.NOMBRE AS NOM_OFICINA_ORIGEN, TDP.NOMBRE AS NOM_TIPO_DOCUMENTO_PUBLICO"
		+ " FROM SDB_BNG_ALERTA_T_INACTIVACION ATI"
		+ " INNER JOIN SDB_PGN_OFICINA_ORIGEN OO ON ATI.DOCUMENTO_OFICINA_ORIGEN = OO.ID_OFICINA_ORIGEN"
		+ " INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO TDP ON ATI.DOCUMENTO_TIPO = TDP.ID_TIPO_DOCUMENTO"
		+ " WHERE ATI.ID_ALERTA_TIERRAS = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_BNG_ALERTA_T_INACTIVACION (ID_ALERTA_TIERRAS,MOTIVO_INACTIVACION,DOCUMENTO_OFICINA_ORIGEN,DOCUMENTO_TIPO,DOCUMENTO_FECHA,DOCUMENTO_NUMERO,DOCUMENTO_SGD_ID,DOCUMENTO_SGD_DOCNAME,TEXTO_INACTIVACION,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_DELETE_BY_ID_ALERTA_TIERRAS. */
	private static final String cs_DELETE_BY_ID_ALERTA_TIERRAS = "DELETE FROM SDB_BNG_ALERTA_T_INACTIVACION WHERE ID_ALERTA_TIERRAS = ?";

	/**
	 * Find by id.
	 *
	 * @param al_idAlertaTierras de al id alerta tierras
	 * @return el valor de alerta T inactivacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AlertaTInactivacion findById(long al_idAlertaTierras)
	    throws B2BException
	{
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;
		AlertaTInactivacion lati_alertaTInactivacion;

		lps_ps                       = null;
		lrs_rs                       = null;
		lati_alertaTInactivacion     = null;

		try
		{
			int li_contador;

			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setLong(li_contador++, al_idAlertaTierras);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lati_alertaTInactivacion = getObjetFromResultSet(lrs_rs, true);
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

		return lati_alertaTInactivacion;
	}

	/**
	 * Insert.
	 *
	 * @param aati_parametro de aati parametro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(AlertaTInactivacion aati_parametro)
	    throws B2BException
	{
		if(aati_parametro != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setLong(li_column++, aati_parametro.getIdAlertaTierras());
				lps_ps.setString(li_column++, aati_parametro.getMotivoInactivacion());
				lps_ps.setString(li_column++, aati_parametro.getDocumentoOficinaOrigen());
				lps_ps.setString(li_column++, aati_parametro.getDocumentoTipo());
				setDate(lps_ps, aati_parametro.getDocumentoFecha(), li_column++);
				lps_ps.setString(li_column++, aati_parametro.getDocumentoNumero());
				lps_ps.setString(li_column++, aati_parametro.getDocumentoSGDId());
				lps_ps.setString(li_column++, aati_parametro.getDocumentoSGDDocName());
				lps_ps.setString(li_column++, aati_parametro.getTextoInactivacion());
				lps_ps.setString(li_column++, aati_parametro.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aati_parametro.getIpCreacion());

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
	 * @param ab_adicional de ab adicional
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private AlertaTInactivacion getObjetFromResultSet(ResultSet ars_rs, boolean ab_adicional)
	    throws SQLException
	{
		AlertaTInactivacion lat_alertaTInactivacion;

		lat_alertaTInactivacion = new AlertaTInactivacion();

		lat_alertaTInactivacion.setIdAlertaTierras(ars_rs.getLong("ID_ALERTA_TIERRAS"));
		lat_alertaTInactivacion.setMotivoInactivacion(ars_rs.getString("MOTIVO_INACTIVACION"));
		lat_alertaTInactivacion.setDocumentoOficinaOrigen(ars_rs.getString("DOCUMENTO_OFICINA_ORIGEN"));
		lat_alertaTInactivacion.setDocumentoTipo(ars_rs.getString("DOCUMENTO_TIPO"));
		lat_alertaTInactivacion.setDocumentoFecha(ars_rs.getTimestamp("DOCUMENTO_FECHA"));
		lat_alertaTInactivacion.setDocumentoNumero(ars_rs.getString("DOCUMENTO_NUMERO"));
		lat_alertaTInactivacion.setDocumentoSGDId(ars_rs.getString("DOCUMENTO_SGD_ID"));
		lat_alertaTInactivacion.setDocumentoSGDDocName(ars_rs.getString("DOCUMENTO_SGD_DOCNAME"));
		lat_alertaTInactivacion.setTextoInactivacion(ars_rs.getString("TEXTO_INACTIVACION"));

		if(ab_adicional)
		{
			lat_alertaTInactivacion.setNomOficinaOrigen(ars_rs.getString("NOM_OFICINA_ORIGEN"));
			lat_alertaTInactivacion.setNomTipoDocumentoPublico(ars_rs.getString("NOM_TIPO_DOCUMENTO_PUBLICO"));
		}

		fillAuditoria(ars_rs, lat_alertaTInactivacion);

		return lat_alertaTInactivacion;
	}
}
