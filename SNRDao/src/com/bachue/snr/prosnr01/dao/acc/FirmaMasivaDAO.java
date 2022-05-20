package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.FirmaMasiva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_FIRMA_MASIVA.
 *
 * @author lchacon
 */
public class FirmaMasivaDAO extends BaseDAO
{
	/** Constante cs_INSERT_SIGN_MASIVE. */
	private static final String cs_INSERT_SIGN_MASIVE = "INSERT INTO SDB_ACC_FIRMA_MASIVA "
		+ " (ID_FIRMA, ID_USUARIO, TIPO_FIRMA, LLAVE1, LLAVE2, LLAVE3, LLAVE4, FECHA_CREACION, ID_USUARIO_CREACION) "
		+ " VALUES (?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_SEARCH_ALL_BY_TIPO_FIRMA. */
	private static final String cs_SEARCH_ALL_BY_TIPO_FIRMA = "SELECT * FROM SDB_ACC_FIRMA_MASIVA SAFM INNER JOIN SDB_ACC_TURNO_HISTORIA SATH ON SATH.ID_TURNO_HISTORIA = SAFM.LLAVE1 WHERE NVL(SATH.INTENTOS_FALLIDOS_EJECUCION_AUTOMATICA, 0) < ? ";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_FIRMA_MASIVA_ID_FIRMA.NEXTVAL FROM DUAL";

	/** Constante cs_DELETE_FINISHED. */
	private static final String cs_DELETE_FINISHED = "DELETE FROM SDB_ACC_FIRMA_MASIVA WHERE ID_FIRMA = ?";

	/**
	 * Delete firma masiva.
	 *
	 * @param afm_parametros correspondiente al valor del tipo de objeto FirmaMasiva
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteFirmaMasiva(FirmaMasiva afm_parametros)
	    throws B2BException
	{
		if(afm_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;
				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_DELETE_FINISHED);

				lps_ps.setInt(li_column++, afm_parametros.getIdFirma());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				getLog().error("deleteFirmaMasiva", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Insert firma masiva.
	 *
	 * @param afm_parametros correspondiente al valor del tipo de objeto FirmaMasiva
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertFirmaMasiva(FirmaMasiva afm_parametros)
	    throws B2BException
	{
		if(afm_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps            = lc_connection.prepareStatement(cs_INSERT_SIGN_MASIVE);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setInt(li_column++, lrs_rs.getInt(1));

				lps_ps.setString(li_column++, afm_parametros.getUsuario());
				lps_ps.setInt(li_column++, afm_parametros.getTipoFirma());
				lps_ps.setString(li_column++, afm_parametros.getLlave1());
				lps_ps.setString(li_column++, afm_parametros.getLlave2());
				lps_ps.setString(li_column++, afm_parametros.getLlave3());
				lps_ps.setString(li_column++, afm_parametros.getLlave4());
				lps_ps.setString(li_column++, afm_parametros.getIdUsuarioCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				getLog().error("insertFirmaMasiva", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Retorna el valor del objeto de Collection de FirmaMasiva.
	 *
	 * @param afm_parametros correspondiente al valor del tipo de objeto FirmaMasiva
	 * @param al_limiteIntentos de al limite intentos
	 * @return devuelve el valor de Collection de FirmaMasiva
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see FirmaMasiva
	 */
	public Collection<FirmaMasiva> searchAllByTipoFirma(FirmaMasiva afm_parametros, long al_limiteIntentos)
	    throws B2BException
	{
		Collection<FirmaMasiva> lcfm_datos;
		lcfm_datos = new ArrayList<FirmaMasiva>();

		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			if(afm_parametros != null)
			{
				try
				{
					int li_contador;
					int li_tipoFirma;

					StringBuilder lsb_sb;

					li_contador      = 1;
					li_tipoFirma     = afm_parametros.getTipoFirma();

					lsb_sb = new StringBuilder(cs_SEARCH_ALL_BY_TIPO_FIRMA);

					if(li_tipoFirma > 0)
						lsb_sb.append(" AND SAFM.TIPO_FIRMA = ? ");

					lps_ps = getConnection().prepareStatement(lsb_sb.toString());

					lps_ps.setLong(li_contador++, al_limiteIntentos);

					if(li_tipoFirma > 0)
						lps_ps.setInt(li_contador++, afm_parametros.getTipoFirma());

					lrs_rs = lps_ps.executeQuery();

					while(lrs_rs.next())
						lcfm_datos.add(getFirmaMasiva(lrs_rs));
				}
				catch(SQLException lse_e)
				{
					getLog().error("searchAllByTipoFirma", lse_e);

					throw new B2BException(SQL_ERROR, lse_e);
				}
				finally
				{
					close(lrs_rs);
					close(lps_ps);
				}
			}

			if(!CollectionUtils.isValidCollection(lcfm_datos))
				lcfm_datos = null;
		}

		return lcfm_datos;
	}

	/**
	 * Retorna el valor de firma masiva.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de firma masiva
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private FirmaMasiva getFirmaMasiva(ResultSet ars_rs)
	    throws SQLException
	{
		FirmaMasiva lfm_masiva;

		lfm_masiva = new FirmaMasiva();

		lfm_masiva.setIdFirma(ars_rs.getInt("ID_FIRMA"));
		lfm_masiva.setTipoFirma(ars_rs.getInt("TIPO_FIRMA"));
		lfm_masiva.setUsuario(ars_rs.getString("ID_USUARIO"));
		lfm_masiva.setLlave1(ars_rs.getString("LLAVE1"));
		lfm_masiva.setLlave2(ars_rs.getString("LLAVE2"));
		lfm_masiva.setLlave3(ars_rs.getString("LLAVE3"));
		lfm_masiva.setLlave4(ars_rs.getString("LLAVE4"));
		lfm_masiva.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lfm_masiva.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lfm_masiva.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lfm_masiva.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));

		return lfm_masiva;
	}
}
