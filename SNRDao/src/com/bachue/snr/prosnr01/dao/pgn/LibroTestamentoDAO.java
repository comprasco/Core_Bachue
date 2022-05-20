package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.LibroTestamento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_LIBRO_TESTAMENTO
 *
 * @author dbeltran
 */
public class LibroTestamentoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_LIBRO_TESTAMENTO WHERE ID_CIRCULO = ? AND ANIO = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_LIBRO_TESTAMENTO";

	/** Constante cs_UPDATE*/
	private static final String cs_UPDATE = "UPDATE SDB_PGN_LIBRO_TESTAMENTO SET TOMO = ?, LIBRO = ?, FOLIO = ?, ID_USUARIO_MODIFICACION = ? , FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? ";

	/** Constante cs_INSERT */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_LIBRO_TESTAMENTO (ID_LIBRO_ANT_SISTEMA,ID_CIRCULO,TOMO,ANIO,LIBRO,FOLIO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES(?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_LIBRO_TESTAMENTO.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<LibroTestamento> findAll()
	    throws B2BException
	{
		Collection<LibroTestamento> lclt_clt;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lclt_clt     = new ArrayList<LibroTestamento>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lclt_clt.add(getObjetFromResultSet(lrs_rs));
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

		if(lclt_clt.isEmpty())
			lclt_clt = null;

		return lclt_clt;
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_PGN_LIBRO_TESTAMENTO para un Libro Testamento específico.
	 *
	 * @param alt_param Objeto de tipo String que contiene parametros a utilizar como filtro en la busqueda.
	 * @return devuelve el valor del objeto Libro Testamento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see LibroTestamento
	 */
	public LibroTestamento findById(LibroTestamento alt_param)
	    throws B2BException
	{
		LibroTestamento ltd_return;
		ltd_return = null;

		if(alt_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;
				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_column++, alt_param.getIdCirculo());
				lps_ps.setLong(li_column++, NumericUtils.getLong(alt_param.getAno()));

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltd_return = getObjetFromResultSet(lrs_rs);
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

		return ltd_return;
	}

	/**
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_LIBRO_TESTAMENTO
	 *
	 * @param alt_param Objeto de tipo LibroTestamento que contiene parametros a utilizar como filtro en la insercion.
	 */
	public void insert(LibroTestamento alt_param)
	    throws B2BException
	{
		if(alt_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				setLong(lps_ps, alt_param.getLibroAntSistema(), li_column++);
				lps_ps.setString(li_column++, alt_param.getIdCirculo());
				setLong(lps_ps, alt_param.getTomo(), li_column++);
				setLong(lps_ps, alt_param.getAno(), li_column++);
				setLong(lps_ps, alt_param.getLibro(), li_column++);
				setLong(lps_ps, alt_param.getFolio(), li_column++);
				lps_ps.setString(li_column++, alt_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, alt_param.getIpCreacion());

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

	/**
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_LIBRO_TESTAMENTO
	 *
	 * @param alt_param Objeto de tipo LibroTestamento que contiene parametros a utilizar como filtro en la modificacion.
	 * @param ab_testamento Objeto de tipo boolean que contiene parametros a utilizar como filtro en la modificacion.
	 */
	public void update(LibroTestamento alt_param, boolean ab_testamento)
	    throws B2BException
	{
		if(alt_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int           li_column;
				StringBuilder lsb_query;

				li_column     = 1;
				lsb_query     = new StringBuilder();

				lsb_query.append(cs_UPDATE);

				lsb_query.append(
				    ab_testamento ? " WHERE ID_LIBRO_ANT_SISTEMA = ? " : " WHERE ID_LIBRO_ANT_SISTEMA = '12' "
				);
				lsb_query.append(" AND ID_CIRCULO = ? AND ANIO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				setLong(lps_ps, alt_param.getTomo(), li_column++);
				setLong(lps_ps, alt_param.getLibro(), li_column++);
				setLong(lps_ps, alt_param.getFolio(), li_column++);
				lps_ps.setString(li_column++, alt_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, alt_param.getIpModificacion());

				if(ab_testamento)
					setLong(lps_ps, alt_param.getLibroAntSistema(), li_column++);

				lps_ps.setString(li_column++, alt_param.getIdCirculo());
				setLong(lps_ps, alt_param.getAno(), li_column++);

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

	/**
	 * Metodo para la obtencion del objeto Tipo Documental a partir de un resulset.
	 *
	 * @param lrs_rs objeto contenedor de los resultados de la consulta
	 * @return Objeto de tipo LibroTestamento intanciado con la informacion recuperada de base de datos
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private LibroTestamento getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException, B2BException
	{
		LibroTestamento lt_libroTestamento;

		lt_libroTestamento = new LibroTestamento();

		lt_libroTestamento.setLibroAntSistema(getLong(lrs_rs, "ID_LIBRO_ANT_SISTEMA"));
		lt_libroTestamento.setLibro(getLong(lrs_rs, "LIBRO"));
		lt_libroTestamento.setFolio(getLong(lrs_rs, "FOLIO"));
		lt_libroTestamento.setTomo(getLong(lrs_rs, "TOMO"));
		lt_libroTestamento.setAno(getLong(lrs_rs, "ANIO"));
		lt_libroTestamento.setIdCirculo(lrs_rs.getString("ID_CIRCULO"));

		fillAuditoria(lrs_rs, lt_libroTestamento);

		return lt_libroTestamento;
	}
}
