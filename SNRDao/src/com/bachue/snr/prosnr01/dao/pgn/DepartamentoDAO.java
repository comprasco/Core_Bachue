package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_DEPARTAMENTO.
 *
 * @author Heiner Castañeda
 */
public class DepartamentoDAO extends MunicipioDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_DEPARTAMENTO WHERE ID_PAIS = ? AND "
		+ " ID_DEPARTAMENTO = ?";

	/** Constante cs_FIND_ALL_BY_PAIS. */
	private static final String cs_FIND_ALL_BY_PAIS = "SELECT * FROM SDB_PGN_DEPARTAMENTO "
		+ " WHERE ID_PAIS = ?  AND ACTIVO = 'S' ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_DEPARTAMENTO  ORDER BY LENGTH(ID_PAIS), ID_PAIS, NOMBRE ASC";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT DEPARTAMENTO_SEC.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_DEPARTAMENTO SET NOMBRE = ?, FECHA_MODIFICACION = SYSTIMESTAMP,"
		+ " INDICATIVO_TELEFONICO = ?, ACTIVO = ?, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE ID_PAIS = ? AND ID_DEPARTAMENTO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_DEPARTAMENTO (ID_PAIS,ID_DEPARTAMENTO,"
		+ " NOMBRE,INDICATIVO_TELEFONICO,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/**
	 * Instancia un nuevo objeto departamento DAO.
	 */
	public DepartamentoDAO()
	{
	}

	/**
	 * Fill departamento.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ad_departamento correspondiente al valor del tipo de objeto Departamento
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	public void fillDepartamento(java.sql.ResultSet ars_rs, Departamento ad_departamento)
	    throws java.sql.SQLException
	{
		if(ad_departamento != null)
		{
			ad_departamento.setIdDepartamento(ars_rs.getString("CODIGO_DEPARTAMENTO"));
			ad_departamento.setNombreDepartamento(ars_rs.getString("NOMBRE_DEPARTAMENTO"));

			fillMunicipio(ars_rs, ad_departamento);
		}
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_DEPARTAMENTO
	 * que coincida con un id_pais especifico.
	 *
	 * @param ad_parametros representa el objeto departamento del cual se tomara el id_pais para realizar la consulta
	 * @return devuelve el valor del objeto collection de Departamento
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Departamento> findAllByPais(Departamento ad_parametros)
	    throws B2BException
	{
		Collection<Departamento> lcd_cd;

		lcd_cd = new ArrayList<Departamento>();

		if(ad_parametros != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_PAIS);

				lps_ps.setString(li_contador++, ad_parametros.getIdPais());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcd_cd.add(getDepartamento(lrs_rs));
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

		if(lcd_cd.isEmpty())
			lcd_cd = null;

		return lcd_cd;
	}

	/**
	 * Metodo para traer de la base de datos todos los registros de la tabla SDB_PGN_DEPARTAMENTO.
	 *
	 * @return devuelve el valor del objeto collection de Departamento
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Departamento> findAllD()
	    throws B2BException
	{
		Collection<Departamento> lcd_cd;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcd_cd     = new ArrayList<Departamento>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcd_cd.add(getDepartamento(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllD", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcd_cd.isEmpty())
			lcd_cd = null;

		return lcd_cd;
	}

	/**
	 * Obtiene un departamento asociado a un id específico.
	 *
	 * @param ad_parametros id del departamento a utilizar como filtro en la busqueda
	 * @return Departamento resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Departamento findById(Departamento ad_parametros)
	    throws B2BException
	{
		return (ad_parametros != null) ? findById(ad_parametros.getIdPais(), ad_parametros.getIdDepartamento()) : null;
	}

	/**
	 * Obtiene un departamento asociado a un id específico.
	 *
	 * @param as_idPais id del país al cual pertenece el departamento
	 * @param as_idDepartamento id del departamento a utilizar como filtro en la busqueda
	 * @return Departamento resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Departamento findById(String as_idPais, String as_idDepartamento)
	    throws B2BException
	{
		Departamento ld_dept;

		ld_dept = null;

		if(StringUtils.isValidString(as_idPais) && StringUtils.isValidString(as_idDepartamento))
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

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ld_dept = getDepartamento(lrs_rs);
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

		return ld_dept;
	}

	/**
	 * Metodo para encontrar una nueva secuencia para un nuevo registro.
	 *
	 * @return devuelve el valor de la secuencia
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
	 * Metodo para actualizar o insertar registros en la tabla SDB_PGN_DEPARTAMENTO.
	 *
	 * @param ad_parametros correspondiente al valor del tipo de objeto Departamento
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 * @ param apt_parametros true para insertar false para actualizar
	 */
	public void insertOrUpdate(Departamento ad_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ad_parametros != null)
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
					lps_ps.setString(li_column++, ad_parametros.getIdPais());
					lps_ps.setString(li_column++, ad_parametros.getIdDepartamento());
				}

				lps_ps.setString(li_column++, ad_parametros.getNombre());
				lps_ps.setString(li_column++, ad_parametros.getIndicativoTelefonico());
				lps_ps.setString(li_column++, ad_parametros.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, ad_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ad_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ad_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ad_parametros.getIpModificacion());
					lps_ps.setString(li_column++, ad_parametros.getIdPais());
					lps_ps.setString(li_column++, ad_parametros.getIdDepartamento());
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
	 *   Metodo para obtener objeto de tipo Departamento.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de departamento
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Departamento getDepartamento(ResultSet ars_rs)
	    throws SQLException
	{
		Departamento ld_datos;
		ld_datos = new Departamento();

		ld_datos.setIdPais(ars_rs.getString("ID_PAIS"));
		ld_datos.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		ld_datos.setNombre(ars_rs.getString("NOMBRE"));
		ld_datos.setIndicativoTelefonico(ars_rs.getString("INDICATIVO_TELEFONICO"));
		ld_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ld_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ld_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		ld_datos.setActivo(ars_rs.getString("ACTIVO"));
		ld_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ld_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));

		return ld_datos;
	}
}
