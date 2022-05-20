package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.LibroAntiguoSistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_LIBRO_ANT_SISTEMA
 *
 * @author itrujillo
 */
public class LibroAntiguoSistemaDao extends com.b2bsg.common.dataAccess2.BaseDAO implements IBase<LibroAntiguoSistema>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_LIBRO_ANT_SISTEMA WHERE ID_LIBRO_ANT_SISTEMA = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_LIBRO_ANT_SISTEMA ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_LIBRO_ANT_SISTEMA SET NOMBRE = ?, "
		+ " FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?,"
		+ " IP_MODIFICACION = ?, ACTIVO=? WHERE ID_LIBRO_ANT_SISTEMA = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_LIBRO_ANT_SISTEMA (ID_LIBRO_ANT_SISTEMA, "
		+ "NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ACTIVO) " + "VALUES (?,?,?,SYSTIMESTAMP,?,?) ";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_PGN_LIBRO_ANT_SISTEMA_ID_LIBRO_ANT_SISTEMA.NEXTVAL FROM DUAL";

	/**
	 * Metodo para traer de la base de datos todos los registros de la tabla SDB_PGN_LIBRO_ANT_SISTEMA.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<LibroAntiguoSistema> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<LibroAntiguoSistema> lclas_libro;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;
		StringBuilder                   lsb_sbf;

		lclas_libro     = new ArrayList<LibroAntiguoSistema>();
		lps_ps          = null;
		lrs_rs          = null;
		lsb_sbf         = new StringBuilder(cs_FIND_ALL);

		try
		{
			if(ab_b)
				lsb_sbf = lsb_sbf.append(" ORDER BY ID_LIBRO_ANT_SISTEMA ASC");
			else
				lsb_sbf = lsb_sbf.append(" ORDER BY ID_LIBRO_ANT_SISTEMA ASC ");

			lps_ps     = getConnection().prepareStatement(lsb_sbf.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lclas_libro.add(getLibroAntiguoSistema(lrs_rs));
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

		if(lclas_libro.isEmpty())
			lclas_libro = null;

		return lclas_libro;
	}

	/**
	 * Busca un libro de antiguo sistema asociado a un id específico.
	 *
	 * @param alas_parametros id del libro ant sistema a utilizar como filtro en la busqueda
	 * @return libro resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public LibroAntiguoSistema findById(LibroAntiguoSistema alas_parametros)
	    throws B2BException
	{
		return (alas_parametros != null) ? findById(alas_parametros.getIdLibroAntiguoSistema()) : null;
	}

	/**
	 * Busca un libro de antiguo sistema asociado a un id específico.
	 *
	 * @param al_idLibroAntiguoSistema id del libro ant sistema a utilizar como filtro en la busqueda
	 * @return libro resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public LibroAntiguoSistema findById(long al_idLibroAntiguoSistema)
	    throws B2BException
	{
		LibroAntiguoSistema llbas_libros;

		llbas_libros = null;

		if(al_idLibroAntiguoSistema > 0)
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

				lps_ps.setLong(li_contador++, al_idLibroAntiguoSistema);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					llbas_libros = getLibroAntiguoSistema(lrs_rs);
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

		return llbas_libros;
	}

	/**
	 * Metodo para actualizar o insertar registros en la tabla SDB_PGN_LIBRO_ANT_SISTEMA.
	 *
	 * @param ac_parametros correspondiente al valor del tipo de objeto LibroAntiguoSistema
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@Override
	public void insertOrUpdate(LibroAntiguoSistema alas_parametros, boolean ab_query)
	    throws B2BException
	{
		if(alas_parametros != null)
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
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCIA);
					lrs_rs            = lps_secuencia.executeQuery();

					if(lrs_rs.next())
						lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
					else
						throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);
				}

				if(ab_query)
				{
					lps_ps.setString(li_column++, alas_parametros.getNombre());
					lps_ps.setString(li_column++, alas_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, alas_parametros.getIpCreacion());
					lps_ps.setString(li_column++, alas_parametros.getActivo());
				}
				else
				{
					lps_ps.setString(li_column++, alas_parametros.getNombre());
					lps_ps.setString(li_column++, alas_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, alas_parametros.getIpModificacion());
					lps_ps.setString(li_column++, alas_parametros.getActivo());
					lps_ps.setLong(li_column++, alas_parametros.getIdLibroAntiguoSistema());
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

				if(ab_query)
				{
					close(lps_secuencia);
					close(lrs_rs);
				}
			}
		}
	}

	/**
	 *   Metodo para obtener objeto de tipo libro antiguo sistema.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de libro antiguo sistema
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private LibroAntiguoSistema getLibroAntiguoSistema(ResultSet ars_rs)
	    throws SQLException
	{
		LibroAntiguoSistema ld_datos;

		ld_datos = new LibroAntiguoSistema();

		ld_datos.setIdLibroAntiguoSistema(ars_rs.getLong("ID_LIBRO_ANT_SISTEMA"));
		ld_datos.setNombre(ars_rs.getString("NOMBRE"));
		ld_datos.setActivo(ars_rs.getString("ACTIVO"));
		ld_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ld_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ld_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ld_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ld_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ld_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return ld_datos;
	}
}
