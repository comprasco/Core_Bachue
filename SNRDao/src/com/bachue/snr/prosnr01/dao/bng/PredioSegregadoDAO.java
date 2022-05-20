package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_BNG_PREDIO_SEGREGADO.
 *
 * @author Jorge Patino
 * Fecha de Creacion: 20/02/2019
 */
public class PredioSegregadoDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_ANOTACION. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_ANOTACION = "SELECT * FROM SDB_BNG_PREDIO_SEGREGADO WHERE ID_CIRCULO1 = ? AND "
		+ " ID_MATRICULA1 = ? AND ID_ANOTACION1 = ?";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT * FROM SDB_BNG_PREDIO_SEGREGADO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_FIND_BY_CIRCULO1_MATRICULA1. */
	private static final String cs_FIND_BY_CIRCULO1_MATRICULA1 = "SELECT * FROM SDB_BNG_PREDIO_SEGREGADO WHERE ID_CIRCULO1 = ? AND ID_MATRICULA1 = ?";

	/** Constante cs_FIND_NUM_PREDIAL_BY_PADRE. */
	private static final String cs_FIND_NUM_PREDIAL_BY_PADRE = "SELECT DISTINCT PS.ID_CIRCULO, PS.ID_MATRICULA, PR.NUMERO_PREDIAL"
			+ " FROM SDB_BNG_PREDIO_SEGREGADO PS"
			+ " INNER JOIN SDB_BNG_PREDIO_REGISTRO PR ON PS.ID_CIRCULO1 = PR.ID_CIRCULO AND PS.ID_MATRICULA1 = PR.ID_MATRICULA"
			+ " WHERE PS.ID_CIRCULO = ? AND PS.ID_MATRICULA = ? AND PR.NUMERO_PREDIAL IS NOT NULL";

	/**
	 * Retorna el valor del objeto de tipo Collection de PredioSegregado filtrado por id circulo y id matricula.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto PredioSegregado
	 * @return devuelve el valor del objeto collection de PredioSegregado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioSegregado
	 */
	public Collection<PredioSegregado> findAllByCirculo1Matricula1(PredioSegregado at_param)
	    throws B2BException
	{
		return findAllByCirculo1Matricula1(at_param.getIdCirculo1(), at_param.getIdMatricula1());
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de PredioSegregado filtrado por id circulo y id matricula.
	 *
	 * @param as_idCirculo1 de as id circulo 1
	 * @param al_idMatricula1 de al id matricula 1
	 * @return devuelve el valor del objeto collection de PredioSegregado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioSegregado
	 */
	public Collection<PredioSegregado> findAllByCirculo1Matricula1(String as_idCirculo1, Long al_idMatricula1)
	    throws B2BException
	{
		Collection<PredioSegregado> lcpr_predioSegregado;

		lcpr_predioSegregado = new ArrayList<PredioSegregado>();

		if(StringUtils.isValidString(as_idCirculo1) && NumericUtils.isValidLong(al_idMatricula1))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;

			li_column     = 1;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO1_MATRICULA1);

				lps_ps.setString(li_column++, as_idCirculo1);
				setLong(lps_ps, al_idMatricula1, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcpr_predioSegregado.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByCirculo1Matricula1", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcpr_predioSegregado.isEmpty())
			lcpr_predioSegregado = null;

		return lcpr_predioSegregado;
	}

	/**
	 * Find all by circulo matricula.
	 *
	 * @param at_param de at param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PredioSegregado> findAllByCirculoMatricula(PredioSegregado at_param)
	    throws B2BException
	{
		if(at_param != null)
			return findAllByCirculoMatricula(at_param.getIdCirculo(), NumericUtils.getLong(at_param.getIdMatricula()));
		else

			return null;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de PredioSegregado filtrado por el id del circulo y id de la matricula.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @return devuelve el valor del objeto collection de PredioSegregado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioSegregado
	 */
	public Collection<PredioSegregado> findAllByCirculoMatricula(String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		Collection<PredioSegregado> lcpr_predioSegregado;

		lcpr_predioSegregado = new ArrayList<PredioSegregado>();

		if(StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);

				lps_ps.setString(li_column++, as_idCirculo);
				lps_ps.setLong(li_column++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcpr_predioSegregado.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcpr_predioSegregado.isEmpty())
			lcpr_predioSegregado = null;

		return lcpr_predioSegregado;
	}

	/**
	 * Find num predial by padre.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PredioSegregado> findNumPredialByPadre(String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		Collection<PredioSegregado> lcpr_predioSegregado;

		lcpr_predioSegregado = new ArrayList<PredioSegregado>();

		if(StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_NUM_PREDIAL_BY_PADRE);

				lps_ps.setString(li_column++, as_idCirculo);
				lps_ps.setLong(li_column++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					PredioSegregado lps_predioSegregado;

					lps_predioSegregado = new PredioSegregado();

					lps_predioSegregado.setIdCirculo1(lrs_rs.getString("ID_CIRCULO"));
					lps_predioSegregado.setIdMatricula1(getLong(lrs_rs, "ID_MATRICULA"));
					lps_predioSegregado.setNumeroPredial(lrs_rs.getString("NUMERO_PREDIAL"));

					lcpr_predioSegregado.add(lps_predioSegregado);
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findNumPredialByPadre", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcpr_predioSegregado.isEmpty())
			lcpr_predioSegregado = null;

		return lcpr_predioSegregado;
	}

	/**
	 * Find by circulo 1 matricula 1.
	 *
	 * @param as_idCirculo1 de as id circulo 1
	 * @param al_idMatricula1 de al id matricula 1
	 * @return el valor de predio segregado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PredioSegregado findByCirculo1Matricula1(String as_idCirculo1, Long al_idMatricula1)
	    throws B2BException
	{
		PredioSegregado lpr_predioSegregado;

		lpr_predioSegregado = null;

		if(StringUtils.isValidString(as_idCirculo1) && NumericUtils.isValidLong(al_idMatricula1))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;

			li_column     = 1;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO1_MATRICULA1);

				lps_ps.setString(li_column++, as_idCirculo1);
				setLong(lps_ps, al_idMatricula1, li_column++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpr_predioSegregado = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculo1Matricula1", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpr_predioSegregado;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de PredioSegregado filtrado por el id del circulo y id de la matricula
	 * y ID anotacion.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto PredioSegregado
	 * @return devuelve el valor del objeto collection de PredioSegregado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioSegregado
	 */
	public Collection<PredioSegregado> findByCirculoMatriculaAnotacion(PredioSegregado at_param)
	    throws B2BException
	{
		return (at_param != null)
		? findByCirculoMatriculaAnotacion(
		    at_param.getIdCirculo(), NumericUtils.getLong(at_param.getIdMatricula()),
		    NumericUtils.getLong(at_param.getIdAnotacion()), true
		) : null;
	}

	/**
	 * Find by circulo matricula anotacion.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @param al_idAnotacion de al id anotacion
	 * @param ab_matriz de ab matriz
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PredioSegregado> findByCirculoMatriculaAnotacion(
	    String as_idCirculo, long al_idMatricula, long al_idAnotacion, boolean ab_matriz
	)
	    throws B2BException
	{
		Collection<PredioSegregado> lcps_prediosSegregados;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcps_prediosSegregados     = new ArrayList<PredioSegregado>();
		lps_ps                     = null;
		lrs_rs                     = null;

		if(StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0L) && (al_idAnotacion > 0L))
		{
			try
			{
				int    li_column;
				String ls_query;

				ls_query      = cs_FIND_BY_CIRCULO_MATRICULA_ANOTACION;
				li_column     = 1;

				if(ab_matriz)
					ls_query = ls_query.replace("1", "");

				lps_ps = getConnection().prepareStatement(ls_query);

				lps_ps.setString(li_column++, as_idCirculo);
				lps_ps.setLong(li_column++, al_idMatricula);
				lps_ps.setLong(li_column++, al_idAnotacion);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcps_prediosSegregados.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatriculaAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcps_prediosSegregados.isEmpty())
			lcps_prediosSegregados = null;

		return lcps_prediosSegregados;
	}

	/**
	 * Retorna el valor de PredioSegregado.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de PredioSegregado
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private PredioSegregado getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		PredioSegregado lps_predioSegregado;

		lps_predioSegregado = new PredioSegregado();

		lps_predioSegregado.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lps_predioSegregado.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		lps_predioSegregado.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		lps_predioSegregado.setIdCirculo1(ars_rs.getString("ID_CIRCULO1"));
		lps_predioSegregado.setIdMatricula1(getLong(ars_rs, "ID_MATRICULA1"));
		lps_predioSegregado.setIdAnotacion1(getLong(ars_rs, "ID_ANOTACION1"));
		lps_predioSegregado.setLote(ars_rs.getString("LOTE"));
		lps_predioSegregado.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lps_predioSegregado.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lps_predioSegregado.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));

		return lps_predioSegregado;
	}
}
