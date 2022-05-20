package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.AlertaNaturalezaJuridica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_ALERTA_NATURALEZA_JURIDICA
 *
 * @author hcastaneda
 */
public class AlertaNaturalezaJuridicaDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = " SELECT  * FROM SDB_PGN_ALERTA_NATURALEZA_JURIDICA PANJ INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PNJ  "
		+ " ON PNJ.ID_NATURALEZA_JURIDICA = PANJ.ID_NATURALEZA_JURIDICA AND  PNJ.VERSION = PANJ.VERSION WHERE PANJ.ID_NATURALEZA_JURIDICA = ? AND PANJ.VERSION = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_ALERTA_NATURALEZA_JURIDICA SET VERSION = ?, "
		+ " NOMBRE_ALERTA = ?, ACTIVO = ?, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP"
		+ " WHERE ID_NATURALEZA_JURIDICA = ? AND VERSION = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_ALERTA_NATURALEZA_JURIDICA("
		+ "ID_NATURALEZA_JURIDICA, VERSION, NOMBRE_ALERTA, ACTIVO, ID_USUARIO_CREACION, IP_CREACION, FECHA_CREACION) "
		+ "VALUES(?, ?, ?, ?, ?, ?,SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_ALERTA_NATURALEZA_JURIDICA ";

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_MEDIDA_AREA.
	 *
	 * @return devuelve el valor del objeto collection de AlertaNaturalezaJuridica
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<AlertaNaturalezaJuridica> findAll()
	    throws B2BException
	{
		Collection<AlertaNaturalezaJuridica> lccad_ccad;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lccad_ccad     = new ArrayList<AlertaNaturalezaJuridica>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccad_ccad.add(getAlertaNaturaleza(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lccad_ccad.isEmpty())
			lccad_ccad = null;

		return lccad_ccad;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_ALERTA_NATURALEZA_JURIDICA
	 * que coíncida con un ID_NATURALEZA_JURIDICA específico.
	 *
	 * @param as_id objeto del cual se extrae el id para realizar la consulta en la base de datos
	 * @param al_version correspondiente al valor del tipo de objeto long
	 * @return devuelve el valor del objeto alerta naturaleza juridica
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public AlertaNaturalezaJuridica findById(String as_id, long al_version)
	    throws B2BException
	{
		AlertaNaturalezaJuridica lc_c;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		int li_column;
		li_column     = 1;

		lc_c       = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_column++, as_id);
			lps_ps.setLong(li_column++, al_version);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lc_c = getAlertaNaturaleza(lrs_rs);
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

		return lc_c;
	}

	/**
	 * Método para insertar o actualizar registros en la base de datos.
	 *
	 * @param aanj_parametros objeto a insertar
	 * @param ab_query indica si se va a insertar o modificar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(AlertaNaturalezaJuridica aanj_parametros, boolean ab_query)
	    throws B2BException
	{
		if(aanj_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
					lps_ps.setString(li_column++, aanj_parametros.getIdNaturalezaJuridica());

				lps_ps.setLong(li_column++, NumericUtils.getLong(aanj_parametros.getVersion()));
				lps_ps.setString(li_column++, aanj_parametros.getNombreAlerta());
				lps_ps.setString(li_column++, aanj_parametros.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, aanj_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aanj_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, aanj_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, aanj_parametros.getIpModificacion());
					lps_ps.setString(li_column++, aanj_parametros.getIdNaturalezaJuridica());
					lps_ps.setLong(li_column++, NumericUtils.getLong(aanj_parametros.getVersion()));
				}

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de alerta naturaleza juridica.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de alerta naturaleza
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AlertaNaturalezaJuridica getAlertaNaturaleza(ResultSet ars_rs)
	    throws SQLException
	{
		AlertaNaturalezaJuridica lc_datos;

		lc_datos = new AlertaNaturalezaJuridica();

		lc_datos.setIdNaturalezaJuridica(ars_rs.getString("ID_NATURALEZA_JURIDICA"));
		lc_datos.setNombreAlerta(ars_rs.getString("NOMBRE_ALERTA"));
		lc_datos.setActivo(ars_rs.getString("ACTIVO"));
		lc_datos.setVersion(NumericUtils.getLongWrapper(ars_rs.getLong("VERSION")));

		fillAuditoria(ars_rs, lc_datos);

		return lc_datos;
	}
}
