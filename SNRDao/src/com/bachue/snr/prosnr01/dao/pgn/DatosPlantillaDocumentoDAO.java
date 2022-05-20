package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.DatosPlantillaDocumento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_TRAMITE_VISITA
 *
 * @author Bryan Márquez
 */
public class DatosPlantillaDocumentoDAO extends AuditoriaDao
{
	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_DATOS_PLANTILLA_DOCUMENTO SET ID_TRAMITE = ?, "
		+ "ID__TIPO_TRAMITE = ?,ID_CONSTANTE = ?,ACTIVO = ?,ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_TRAMITE_VISITA = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_DATOS_PLANTILLA_DOCUMENTO (ID_DATOS_PLANTILLA_DOCUMENTO,"
		+ " ID_TRAMITE,ID__TIPO_TRAMITE,ID_CONSTANTE,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION) VALUES (?,?,?,?,?,?,SYSTIMESTAMP)";

	/** Constante cs_FIND_BY_TRAMITE_TIPO_TRAMITE_ACTIVO. */
	private static final String cs_FIND_BY_TRAMITE_TIPO_TRAMITE_ACTIVO = "SELECT * FROM SDB_PGN_DATOS_PLANTILLA_DOCUMENTO WHERE ACTIVO = 'S' AND ID_TRAMITE = ? AND ID_TIPO_TRAMITE = ?";

	/** Constante cs_FIND_BY_ID_ACTIVO. */
	private static final String cs_FIND_BY_ID_ACTIVO = "SELECT * FROM SDB_PGN_DATOS_PLANTILLA_DOCUMENTO WHERE ACTIVO = 'S' AND ID ID_DATOS_PLANTILLA_DOCUMENTO = ? ";

	/**
	 * Find by id tramite id tipo tramite activo.
	 *
	 * @param as_idTramite de as id tramite
	 * @param as_idTramiteTipo de as id tramite tipo
	 * @return el valor de datos plantilla documento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosPlantillaDocumento findByIdTramiteIdTipoTramiteActivo(String as_idTramite, String as_idTramiteTipo)
	    throws B2BException
	{
		DatosPlantillaDocumento ldpd_dpd;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		ldpd_dpd     = null;
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_TRAMITE_TIPO_TRAMITE_ACTIVO);

			lps_ps.setString(1, as_idTramite);
			lps_ps.setString(2, as_idTramiteTipo);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ldpd_dpd = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdTramiteIdTipoTramiteActivo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ldpd_dpd;
	}

	/**
	 * Find by id activo.
	 *
	 * @param as_idDatosPlantillaDocumento correspondiente al valor de as id datos plantilla documento
	 * @return el valor de datos plantilla documento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosPlantillaDocumento findByIdActivo(String as_idDatosPlantillaDocumento)
	    throws B2BException
	{
		DatosPlantillaDocumento ldpd_datosPlantillaDocumento;

		ldpd_datosPlantillaDocumento = null;

		if(StringUtils.isValidString(as_idDatosPlantillaDocumento))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ACTIVO);
				lps_ps.setString(1, as_idDatosPlantillaDocumento);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ldpd_datosPlantillaDocumento = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdActivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ldpd_datosPlantillaDocumento;
	}

	/**
	 * Insert.
	 *
	 * @param adpd_dpd correspondiente al valor de adpd dpd
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(DatosPlantillaDocumento adpd_dpd)
	    throws B2BException
	{
		if(adpd_dpd != null)
		{
			int               li_column;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_INSERT);

				{
					lps_ps.setString(li_column++, adpd_dpd.getIdDatosPlantillaDocumento());
					lps_ps.setString(li_column++, adpd_dpd.getIdTramite());
					lps_ps.setString(li_column++, adpd_dpd.getIdTipoTramite());
					lps_ps.setString(li_column++, adpd_dpd.getIdConstante());
					lps_ps.setString(li_column++, adpd_dpd.getActivo());
					lps_ps.setString(li_column++, adpd_dpd.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, adpd_dpd.getIpCreacion());
				}

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
				close(lrs_rs);
			}
		}
	}

	public void update(DatosPlantillaDocumento adpd_dpd)
	    throws B2BException
	{
		if(adpd_dpd != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int           li_column;
				StringBuilder lsb_query;

				li_column     = 1;
				lsb_query     = new StringBuilder(cs_UPDATE);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, adpd_dpd.getIdDatosPlantillaDocumento());
				lps_ps.setString(li_column++, adpd_dpd.getIdTramite());
				lps_ps.setString(li_column++, adpd_dpd.getIdTipoTramite());
				lps_ps.setString(li_column++, adpd_dpd.getIdConstante());
				lps_ps.setString(li_column++, adpd_dpd.getActivo());
				lps_ps.setString(li_column++, adpd_dpd.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, adpd_dpd.getIpModificacion());

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

	private DatosPlantillaDocumento getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		DatosPlantillaDocumento ldpd_datosPlantillaDocumento;

		ldpd_datosPlantillaDocumento = new DatosPlantillaDocumento();

		ldpd_datosPlantillaDocumento.setIdDatosPlantillaDocumento(lrs_rs.getString("ID_DATOS_PLANTILLA_DOCUMENTO"));
		ldpd_datosPlantillaDocumento.setIdTramite(lrs_rs.getString("ID_TRAMITE"));
		ldpd_datosPlantillaDocumento.setIdTipoTramite(lrs_rs.getString("ID_TIPO_TRAMITE"));
		ldpd_datosPlantillaDocumento.setIdConstante(lrs_rs.getString("ID_CONSTANTE"));
		ldpd_datosPlantillaDocumento.setActivo(lrs_rs.getString("ACTIVO"));

		fillAuditoria(lrs_rs, ldpd_datosPlantillaDocumento);

		return ldpd_datosPlantillaDocumento;
	}
}
