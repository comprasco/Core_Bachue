package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_ZONA_REGISTRAL
 *
 * @author garias
 */
public class ZonaRegistralDAO extends BaseDAO
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_ZONA_REGISTRAL";

	/** Constante cs_FIND_BY_CIRCULO_ID. */
	private static final String cs_FIND_BY_CIRCULO_ID = "SELECT * FROM SDB_PGN_ZONA_REGISTRAL WHERE ID_CIRCULO=? AND ACTIVO = 'S'";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_ZONA_REGISTRAL WHERE ID_ZONA_REGISTRAL=?";

	/** Constante cs_FIND_BY_PAIS_DEP_MUN_VER. */
	private static final String cs_FIND_BY_PAIS_DEP_MUN_VER = "SELECT * FROM SDB_PGN_ZONA_REGISTRAL WHERE ID_PAIS=? AND ID_DEPARTAMENTO=? AND ID_MUNICIPIO=? AND ID_VEREDA=?";

	/** Constante cs_FIND_BY_KEYS. */
	private static final String cs_FIND_BY_KEYS = "SELECT * FROM SDB_PGN_ZONA_REGISTRAL WHERE ID_CIRCULO = ? AND "
		+ "ID_PAIS = ? AND ID_DEPARTAMENTO = ? AND ID_MUNICIPIO = ? AND ID_VEREDA = ? ";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_PGN_ZONA_REGISTRAL_ID_ZONA_REGISTRAL.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_ZONA_REGISTRAL SET "
		+ " ID_CIRCULO = ?, ID_PAIS = ?, ID_DEPARTAMENTO = ?, ID_MUNICIPIO = ?, ID_VEREDA = ?,ID_USUARIO_MODIFICACION = ?, "
		+ "IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ACTIVO = ? WHERE ID_ZONA_REGISTRAL = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_ZONA_REGISTRAL (ID_ZONA_REGISTRAL,ID_CIRCULO,"
		+ "ID_PAIS,ID_DEPARTAMENTO,ID_MUNICIPIO,ID_VEREDA,FECHA_CREACION,ID_USUARIO_CREACION,IP_CREACION,ACTIVO) VALUES (?,?,?,?,?,?,SYSTIMESTAMP,?,?,?)";

	/**
	 * Retorna el valor del objeto de tipo Collection de ZonaRegistral consultando todos los registros
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ZonaRegistral
	 */
	public Collection<ZonaRegistral> findAll(boolean ab_accion)
	    throws B2BException
	{
		Collection<ZonaRegistral> ls_object;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		ls_object     = new ArrayList<ZonaRegistral>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder(cs_FIND_ALL);

			if(ab_accion)
				lsb_sb.append(" WHERE ACTIVO = 'S' ");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
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

		if(ls_object.isEmpty())
			ls_object = null;

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de tipo ZonaRegistral filtrado por circulo id
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto Collection de ZonaRegistral
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ZonaRegistral
	 */
	public Collection<ZonaRegistral> findByCirculo(String as_idCirculo)
	    throws B2BException
	{
		Collection<ZonaRegistral> lczr_zonaRegistral;

		lczr_zonaRegistral = new ArrayList<ZonaRegistral>();

		if(StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;
			StringBuilder     lsb_sb;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;
			lsb_sb        = new StringBuilder(cs_FIND_BY_CIRCULO_ID);

			try
			{
				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_idCirculo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lczr_zonaRegistral.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lczr_zonaRegistral;
	}

	/**
	 * Obtiene una zona registral asociada a un di específico.
	 *
	 * @param azr_param Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return Zona registral resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public ZonaRegistral findById(ZonaRegistral azr_param)
	    throws B2BException
	{
		return (azr_param != null) ? findById(azr_param.getIdZonaRegistral()) : null;
	}

	/**
	 * Obtiene una zona registral asociada a un di específico.
	 *
	 * @param as_idZonaRegistral correspondiente al valor del tipo de objeto String
	 * @return Zona registral resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public ZonaRegistral findById(String as_idZonaRegistral)
	    throws B2BException
	{
		ZonaRegistral lzr_object;

		lzr_object = null;

		if(StringUtils.isValidString(as_idZonaRegistral))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idZonaRegistral);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lzr_object = getObjetFromResultSet(lrs_rs);
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
		}

		return lzr_object;
	}

	/**
	 * Retorna el valor del objeto de tipo ZonaRegistral filtrado por circulo id
	 *
	 * @param at_param correspondiente al valor del tipo de objeto ZonaRegistral
	 * @return devuelve el valor del objeto zona registral
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ZonaRegistral
	 */
	public ZonaRegistral findByIdCirculo(ZonaRegistral at_param)
	    throws B2BException
	{
		ZonaRegistral     ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_ID);

			lps_ps.setString(1, at_param.getIdCirculo());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_object = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdCirculo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de tipo ZonaRegistral filtrado por id circulo, id pais, id departamento, id municipio, id vereda
	 *
	 * @param at_param correspondiente al valor del tipo de objeto ZonaRegistral
	 * @return devuelve el valor del objeto zona registral
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ZonaRegistral
	 */
	public ZonaRegistral findByKeys(ZonaRegistral at_param)
	    throws B2BException
	{
		ZonaRegistral     ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_count;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;
		li_count      = 1;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_KEYS);

			lps_ps.setString(li_count++, at_param.getIdCirculo());
			lps_ps.setString(li_count++, at_param.getIdPais());
			lps_ps.setString(li_count++, at_param.getIdDepartamento());
			lps_ps.setString(li_count++, at_param.getIdMunicipio());
			lps_ps.setString(li_count++, at_param.getIdVereda());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_object = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByKeys", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de tipo ZonaRegistral filtrado por id pais, id depratamento, id municipio, id vereda
	 *
	 * @param at_param correspondiente al valor del tipo de objeto ZonaRegistral
	 * @return devuelve el valor del objeto zona registral
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ZonaRegistral
	 */
	public ZonaRegistral findByPaisDepMunVer(ZonaRegistral at_param)
	    throws B2BException
	{
		ZonaRegistral     ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			int cont;
			cont     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_PAIS_DEP_MUN_VER);

			lps_ps.setString(cont++, at_param.getIdPais());
			lps_ps.setString(cont++, at_param.getIdDepartamento());
			lps_ps.setString(cont++, at_param.getIdMunicipio());
			lps_ps.setString(cont++, at_param.getIdVereda());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_object = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByPaisDepMunVer", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_object;
	}

	/**
	 * Retorna el valor de la secuencia
	 *
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int findSecuencia()
	    throws B2BException
	{
		int               li_secuencia;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_secuencia     = 0;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCIA);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_secuencia = lrs_rs.getInt(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSecuence", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_secuencia;
	}

	/**
	 * Inserta o Actualiza el registro en la base de datos
	 *
	 * @param apt_parametros correspondiente al valor del tipo de objeto ZonaRegistral
	 * @param ab_query correspondiente al valor del tipo de objeto boolean, verdadero para insertar un nuevo registro, falso para actualizar el registro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public ZonaRegistral insertOrUpdate(ZonaRegistral apt_parametros, boolean ab_query)
	    throws B2BException
	{
		if(apt_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int        li_column;
				Connection lc_c;

				li_column     = 1;
				lc_c          = getConnection();
				lps_ps        = lc_c.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_c.prepareStatement(cs_FIND_SECUENCIA);
					lrs_rs            = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						String ls_secuencia;

						ls_secuencia = StringUtils.getString(lrs_rs.getInt(1));

						lps_ps.setString(li_column++, ls_secuencia);
						apt_parametros.setIdZonaRegistral(ls_secuencia);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

					lps_ps.setString(li_column++, apt_parametros.getIdCirculo());
					lps_ps.setString(li_column++, apt_parametros.getIdPais());
					lps_ps.setString(li_column++, apt_parametros.getIdDepartamento());
					lps_ps.setString(li_column++, apt_parametros.getIdMunicipio());
					lps_ps.setString(li_column++, apt_parametros.getIdVereda());
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, apt_parametros.getIpCreacion());
					lps_ps.setString(li_column++, apt_parametros.getActivo());
				}

				if(!ab_query)
				{
					lps_ps.setString(li_column++, apt_parametros.getIdCirculo());
					lps_ps.setString(li_column++, apt_parametros.getIdPais());
					lps_ps.setString(li_column++, apt_parametros.getIdDepartamento());
					lps_ps.setString(li_column++, apt_parametros.getIdMunicipio());
					lps_ps.setString(li_column++, apt_parametros.getIdVereda());
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, apt_parametros.getIpModificacion());
					lps_ps.setString(li_column++, apt_parametros.getActivo());
					lps_ps.setString(li_column++, apt_parametros.getIdZonaRegistral());
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
				close(lrs_rs);
				close(lps_ps);
				close(lps_secuencia);
			}
		}

		return apt_parametros;
	}

	/**
	 * Retorna el valor de ZonaRegistral
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de ZonaRegistral
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see ZonaRegistral
	 */
	private ZonaRegistral getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ZonaRegistral lzr_zonaRegistral;

		lzr_zonaRegistral = new ZonaRegistral();

		lzr_zonaRegistral.setIdZonaRegistral(ars_rs.getString("ID_ZONA_REGISTRAL"));
		lzr_zonaRegistral.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lzr_zonaRegistral.setIdPais(ars_rs.getString("ID_PAIS"));
		lzr_zonaRegistral.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		lzr_zonaRegistral.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		lzr_zonaRegistral.setIdVereda(ars_rs.getString("ID_VEREDA"));
		lzr_zonaRegistral.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lzr_zonaRegistral.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lzr_zonaRegistral.setActivo(ars_rs.getString("ACTIVO"));

		return lzr_zonaRegistral;
	}
}
