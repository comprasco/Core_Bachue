package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_MUNICIPIO
 *
 * @author garias
 */
public class MunicipioDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_MUNICIPIO WHERE ID_PAIS = ? AND "
		+ " ID_DEPARTAMENTO = ? AND ID_MUNICIPIO = ?";

	/** Constante cs_FIND_BY_ID_CIRCULO. */
	private static final String cs_FIND_BY_ID_CIRCULO = "SELECT CR.ID_CIRCULO,CR.ID_OFICINA_ORIGEN, OO.NOMBRE, OO.ID_DEPARTAMENTO, OO.ID_MUNICIPIO, MP.NOMBRE AS NOMBRE_MUNICIPIO, MP.ID_PAIS FROM SDB_PGN_CIRCULO_REGISTRAL CR, SDB_PGN_OFICINA_ORIGEN OO, SDB_PGN_MUNICIPIO MP WHERE OO.ID_OFICINA_ORIGEN (+) = CR.ID_OFICINA_ORIGEN AND OO.VERSION (+) = CR.VERSION AND OO.ID_PAIS (+) = MP.ID_PAIS AND OO.ID_DEPARTAMENTO (+) = MP.ID_DEPARTAMENTO AND OO.ID_MUNICIPIO (+) = MP.ID_MUNICIPIO AND OO.ID_VEREDA = '000' AND CR.ID_CIRCULO = ? ";

	/** Constante cs_FIND_ALL_BY_DEPARTAMENT. */
	private static final String cs_FIND_ALL_BY_DEPARTAMENT = "SELECT * FROM SDB_PGN_MUNICIPIO "
		+ " WHERE ID_DEPARTAMENTO = ?  AND ID_PAIS = ? ";

	/** Constante cs_FIND_ALL_BY_COUNTRY. */
	private static final String cs_FIND_ALL_BY_COUNTRY = "SELECT * FROM SDB_PGN_MUNICIPIO "
		+ " WHERE ID_PAIS = ? AND ACTIVO = ? ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_MUNICIPIO " + " ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT DEPARTAMENTO_SEC.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_MUNICIPIO SET NOMBRE = ?, FECHA_MODIFICACION = SYSTIMESTAMP,"
		+ "  ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, IMPLEMENTADO_NUPRE = ?,ACTIVO = ?, ZONA_RIESGO = ? WHERE ID_PAIS = ? AND ID_DEPARTAMENTO = ? AND ID_MUNICIPIO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_MUNICIPIO (ID_PAIS,ID_DEPARTAMENTO,"
		+ " ID_MUNICIPIO,NOMBRE,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,IMPLEMENTADO_NUPRE, ACTIVO,ZONA_RIESGO) VALUES (?,?,?,?,?,SYSTIMESTAMP,?,?,?,?)";

	/** Constante cs_FIND_BY_CIRCULO*/
	private static final String cs_FIND_BY_CIRCULO = "SELECT DISTINCT SPM.ID_PAIS, SPM.ID_DEPARTAMENTO, SPM.ID_MUNICIPIO, SPM.NOMBRE, SPM.ID_USUARIO_CREACION, SPM.FECHA_CREACION, SPM.ID_USUARIO_MODIFICACION, SPM.IP_MODIFICACION,SPM.FECHA_MODIFICACION, SPM.IMPLEMENTADO_NUPRE, SPM.ACTIVO, SPM.ZONA_RIESGO "
		+ "FROM SDB_PGN_ZONA_REGISTRAL SPZN "
		+ "INNER JOIN SDB_PGN_MUNICIPIO SPM ON SPM.ID_PAIS = SPZN.ID_PAIS AND SPM.ID_DEPARTAMENTO = SPZN.ID_DEPARTAMENTO "
		+ "WHERE SPZN.ID_CIRCULO = ? AND SPZN.ID_VEREDA = '000' AND SPZN.ACTIVO = 'S' ORDER BY SPM.NOMBRE";

	/**
	 * Instancia un nuevo objeto municipio DAO.
	 */
	public MunicipioDAO()
	{
	}

	/**
	 * Llena un obteto de tipo Municipio
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param am_municipio correspondiente al valor del tipo de objeto Municipio
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Municipio
	 */
	public void fillMunicipio(java.sql.ResultSet ars_rs, Municipio am_municipio)
	    throws java.sql.SQLException
	{
		if(am_municipio != null)
		{
			am_municipio.setIdMunicipio(ars_rs.getString("CODIGO_MUNICIPIO"));
			am_municipio.setNombreMunicipio(ars_rs.getString("NOMBRE_MUNICIPIO"));
		}
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Municipio con todos los registros de la tabla
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Municipio
	 */
	public Collection<Municipio> findAll()
	    throws B2BException
	{
		Collection<Municipio> lcm_cm;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		lcm_cm     = new ArrayList<Municipio>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcm_cm.add(getMunicipio(lrs_rs));
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

		if(lcm_cm.isEmpty())
			lcm_cm = null;

		return lcm_cm;
	}

	/**
	 * Metodo encargado de consultar todos los municipios de un pais.
	 *
	 * @param am_parametros Objeto que contiene el pais y el estado activo que se desea consultar.
	 * @return Colección de Municipios que cumplen con los criterios de busqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Municipio> findAllByCountry(Municipio am_parametros)
	    throws B2BException
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = new ArrayList<Municipio>();

		if(am_parametros != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_COUNTRY);

				lps_ps.setString(li_contador++, am_parametros.getIdPais());
				lps_ps.setString(li_contador++, am_parametros.getActivo());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcm_municipios.add(getMunicipio(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByCountry", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcm_municipios.isEmpty())
			lcm_municipios = null;

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Municipio filtrado por id del departamento
	 *
	 * @param am_parametros correspondiente al valor del tipo de objeto Municipio
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Municipio> findAllByDepartament(Municipio am_parametros)
	    throws B2BException
	{
		Collection<Municipio> lcm_cm;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		lcm_cm     = new ArrayList<Municipio>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			int li_contador;
			li_contador = 1;

			String ls_idDepartamento;

			StringBuilder lsb_query;
			ls_idDepartamento     = am_parametros.getIdDepartamento();

			lsb_query = new StringBuilder();

			lsb_query.append(cs_FIND_ALL_BY_DEPARTAMENT);

			if(StringUtils.isValidString(ls_idDepartamento))
				if(ls_idDepartamento.equalsIgnoreCase("11"))
					lsb_query.append(" AND ID_MUNICIPIO = '001'");

			lsb_query.append(" AND ACTIVO = 'S' ORDER BY NOMBRE ASC");

			lps_ps = getConnection().prepareStatement(lsb_query.toString());

			lps_ps.setString(li_contador++, am_parametros.getIdDepartamento());
			lps_ps.setString(li_contador++, am_parametros.getIdPais());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcm_cm.add(getMunicipio(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllByDepartament", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcm_cm.isEmpty())
			lcm_cm = null;

		return lcm_cm;
	}

	/**
	 * Obtiene un municipio asociado a un id específico.
	 *
	 * @param am_parametros id del municipio a utilizar como filtro en la busqueda
	 * @return Municipio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Municipio findById(Municipio am_parametros)
	    throws B2BException
	{
		return (am_parametros != null)
		? findById(am_parametros.getIdPais(), am_parametros.getIdDepartamento(), am_parametros.getIdMunicipio()) : null;
	}

	/**
	 * Obtiene un municipio asociado a un id específico.
	 *
	 * @param as_idPais id del país al cual pertenece el municipio
	 * @param as_idDepartamento id del departamento al cual pertenece el municipio
	 * @param as_idMunicipio id del municipio a utilizar como filtro en la busqueda
	 * @return Municipio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Municipio findById(String as_idPais, String as_idDepartamento, String as_idMunicipio)
	    throws B2BException
	{
		Municipio lm_municipio;

		lm_municipio = null;

		if(
		    StringUtils.isValidString(as_idPais) && StringUtils.isValidString(as_idDepartamento)
			    && StringUtils.isValidString(as_idMunicipio)
		)
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
				lps_ps.setString(li_contador++, as_idDepartamento);
				lps_ps.setString(li_contador++, as_idMunicipio);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lm_municipio = getMunicipio(lrs_rs);
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

		return lm_municipio;
	}

	/**
	 * Obtiene un municipio asociado a un id circulo específico.
	 *
	 * @param as_idCirculo id del circulo al cual pertenece la oficina origen
	 * @return Municipio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Municipio findByIdCirculo(String as_idCirculo)
	    throws B2BException
	{
		Municipio lm_municipio;

		lm_municipio = null;

		if(StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_CIRCULO);

				lps_ps.setString(li_contador++, as_idCirculo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lm_municipio = getMunicipioCirculo(lrs_rs);
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
		}

		return lm_municipio;
	}

	/**
	 * Metodo encargado de consultar todos los municipios de un círculo.
	 *
	 * @param as_parametros Objeto que contiene el círculo.
	 * @return Colección de Municipios que cumplen con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Municipio> findMunicipiosByCirculo(String as_parametros)
	    throws B2BException
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = new ArrayList<Municipio>();

		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lps_ps     = null;
		lrs_rs     = null;

		if(StringUtils.isValidString(as_parametros))
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO);

				lps_ps.setString(li_contador++, as_parametros);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcm_municipios.add(getMunicipio(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMunicipiosByCirculo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcm_municipios.isEmpty())
			lcm_municipios = null;

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de tipo Find secuence.
	 *
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int findSecuence()
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
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCE);

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
	 * Inserta o actualiza un nuevo registro en la tabla
	 *
	 * @param am_parametros correspondiente al valor del tipo de objeto Municipio
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(Municipio am_parametros, boolean ab_query)
	    throws B2BException
	{
		if(am_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setString(li_column++, am_parametros.getIdPais());
					lps_ps.setString(li_column++, am_parametros.getIdDepartamento());
					lps_ps.setString(li_column++, am_parametros.getIdMunicipio());
					lps_ps.setString(li_column++, am_parametros.getNombre());
					lps_ps.setString(li_column++, am_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, am_parametros.getIpCreacion());
					lps_ps.setString(li_column++, am_parametros.getImplementadoNupre());
					lps_ps.setString(li_column++, am_parametros.getActivo());
					lps_ps.setString(li_column++, am_parametros.getZonaRiesgo());
				}

				if(!ab_query)
				{
					lps_ps.setString(li_column++, am_parametros.getNombre());
					lps_ps.setString(li_column++, am_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, am_parametros.getIpModificacion());
					lps_ps.setString(li_column++, am_parametros.getImplementadoNupre());
					lps_ps.setString(li_column++, am_parametros.getActivo());
					lps_ps.setString(li_column++, am_parametros.getZonaRiesgo());
					lps_ps.setString(li_column++, am_parametros.getIdPais());
					lps_ps.setString(li_column++, am_parametros.getIdDepartamento());
					lps_ps.setString(li_column++, am_parametros.getIdMunicipio());
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
	 * Retorna el valor de municipio.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de municipio
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Municipio
	 */
	private Municipio getMunicipio(ResultSet ars_rs)
	    throws SQLException
	{
		Municipio lm_municipio;
		lm_municipio = new Municipio();

		lm_municipio.setIdPais(ars_rs.getString("ID_PAIS"));
		lm_municipio.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		lm_municipio.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		lm_municipio.setNombre(ars_rs.getString("NOMBRE"));
		lm_municipio.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lm_municipio.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lm_municipio.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lm_municipio.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lm_municipio.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lm_municipio.setImplementadoNupre(ars_rs.getString("IMPLEMENTADO_NUPRE"));
		lm_municipio.setActivo(ars_rs.getString("ACTIVO"));
		lm_municipio.setZonaRiesgo(ars_rs.getString("ZONA_RIESGO"));

		return lm_municipio;
	}

	/**
	 * Retorna el valor de municipio.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de municipio
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Municipio
	 */
	private Municipio getMunicipioCirculo(ResultSet ars_rs)
	    throws SQLException
	{
		Municipio lm_municipio;
		lm_municipio = new Municipio();

		lm_municipio.setIdPais(ars_rs.getString("ID_PAIS"));
		lm_municipio.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		lm_municipio.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		lm_municipio.setNombre(ars_rs.getString("NOMBRE_MUNICIPIO"));

		return lm_municipio;
	}
}
