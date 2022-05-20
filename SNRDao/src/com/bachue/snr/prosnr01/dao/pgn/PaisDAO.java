package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_PAIS
 *
 * @author itrujillo
 */
public class PaisDAO extends DepartamentoDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_PAIS WHERE ID_PAIS = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_PAIS ";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_PAIS_ID_PAIS.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_PAIS SET NOMBRE = ?, "
		+ "NACIONALIDAD = ?, ACTIVO = ?,ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, CODIGO_PAIS=?, INDICATIVO_TELEFONICO_PAIS=?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_PAIS = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_PAIS (ID_PAIS,"
		+ " NOMBRE,NACIONALIDAD,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,CODIGO_PAIS,INDICATIVO_TELEFONICO_PAIS) VALUES (?,?,?,?,?,SYSTIMESTAMP,?,?,?)";

	/**
	 * Instancia un nuevo objeto pais DAO.
	 */
	public PaisDAO()
	{
	}

	/**
	 * Llena una nueva instancia del objeto Pais a partir del resultset
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ap_pais correspondiente al valor del tipo de objeto Pais
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Pais
	 */
	public void fillPais(java.sql.ResultSet ars_rs, Pais ap_pais)
	    throws java.sql.SQLException
	{
		if(ap_pais != null)
		{
			ap_pais.setIdPais(ars_rs.getString("CODIGO_PAIS"));
			ap_pais.setNombrePais(ars_rs.getString("NOMBRE_PAIS"));

			fillDepartamento(ars_rs, ap_pais);
		}
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Pais con el listado de los mismos
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean, verdadero para filtrar los paises activos
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Pais> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<Pais>  lp_pais;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		StringBuilder     lsb_sb;

		lsb_sb      = new StringBuilder(cs_FIND_ALL);
		lp_pais     = new ArrayList<Pais>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			if(ab_b)
				lsb_sb = lsb_sb.append(" WHERE ACTIVO = 'S' ");

			lsb_sb     = lsb_sb.append(" ORDER BY NOMBRE ASC");
			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_pais.add(getPais(lrs_rs, true));
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

		if(lp_pais.isEmpty())
			lp_pais = null;

		return lp_pais;
	}

	/**
	 * Busca un país asociado a un id específico.
	 *
	 * @param ap_parametros Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return País resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Pais findById(Pais ap_parametros)
	    throws B2BException
	{
		return (ap_parametros != null) ? findById(ap_parametros.getIdPais()) : null;
	}

	/**
	 * Busca un país asociado a un id específico.
	 *
	 * @param as_idPais Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return País resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Pais findById(String as_idPais)
	    throws B2BException
	{
		Pais lp_pais;

		lp_pais = null;

		if(StringUtils.isValidString(as_idPais))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, as_idPais);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lp_pais = getPais(lrs_rs, true);
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

		return lp_pais;
	}

	/**
	 * Inserta o actualiza el registro en la base de datos
	 *
	 * @param ap_parametros correspondiente al valor del tipo de objeto Pais
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(Pais ap_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ap_parametros != null)
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

				lc_c          = getConnection();
				li_column     = 1;
				lps_ps        = lc_c.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_c.prepareStatement(cs_FIND_SECUENCE);
					lrs_rs            = lps_secuencia.executeQuery();

					if(lrs_rs.next())
						lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
					else
						throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);
				}

				if(ab_query)
				{
					lps_ps.setString(li_column++, ap_parametros.getNombre());
					lps_ps.setString(li_column++, ap_parametros.getNacionalidad());
					lps_ps.setString(li_column++, ap_parametros.getActivo());
					lps_ps.setString(li_column++, ap_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ap_parametros.getIpCreacion());
					lps_ps.setString(li_column++, ap_parametros.getCodigoPais());
					lps_ps.setString(li_column++, ap_parametros.getIndicativoTelefonicoPais());
				}

				if(!ab_query)
				{
					lps_ps.setString(li_column++, ap_parametros.getNombre());
					lps_ps.setString(li_column++, ap_parametros.getNacionalidad());
					lps_ps.setString(li_column++, ap_parametros.getActivo());
					lps_ps.setString(li_column++, ap_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ap_parametros.getIpModificacion());
					lps_ps.setString(li_column++, ap_parametros.getCodigoPais());
					lps_ps.setString(li_column++, ap_parametros.getIndicativoTelefonicoPais());
					lps_ps.setString(li_column++, ap_parametros.getIdPais());
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
	}

	/**
	 * Retorna el valor de pais.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de pais
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Pais
	 */
	private Pais getPais(ResultSet ars_rs, boolean ab_variables)
	    throws SQLException
	{
		Pais lp_pais;
		lp_pais = new Pais();

		lp_pais.setIdPais(ars_rs.getString("ID_PAIS"));
		lp_pais.setNombre(ars_rs.getString("NOMBRE"));
		lp_pais.setNacionalidad(ars_rs.getString("NACIONALIDAD"));
		lp_pais.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lp_pais.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lp_pais.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lp_pais.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lp_pais.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lp_pais.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lp_pais.setActivo(ars_rs.getString("ACTIVO"));

		if(ab_variables)
		{
			lp_pais.setCodigoPais(ars_rs.getString("CODIGO_PAIS"));
			lp_pais.setIndicativoTelefonicoPais(ars_rs.getString("INDICATIVO_TELEFONICO_PAIS"));
		}

		return lp_pais;
	}
}
