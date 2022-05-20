package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene metodos de acceso a datos para la tabla SDB_ACC_PREDIO_SEGREGADO.
 *
 * @author Julian Vaca
 *
 */
public class AccPredioSegregadoDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_PREDIO_SEGREGADO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, "
		+ "ID_ANOTACION, ID_CIRCULO1, ID_MATRICULA1, ID_ANOTACION1, LOTE, DESCRIPCION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, "
		+ "ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO FROM SDB_ACC_PREDIO_SEGREGADO WHERE ID_PREDIO_SEGREGADO = ? ";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ALL_ID = "SELECT ID_PREDIO_SEGREGADO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, "
		+ "ID_ANOTACION, ID_CIRCULO1, ID_MATRICULA1, ID_ANOTACION1, LOTE, DESCRIPCION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, "
		+ "ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO FROM SDB_ACC_PREDIO_SEGREGADO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_CIRCULO1 = ? AND ID_MATRICULA1 = ? AND ID_TURNO = ? ";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT ID_PREDIO_SEGREGADO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, "
		+ "ID_ANOTACION, ID_CIRCULO1, ID_MATRICULA1, ID_ANOTACION1, LOTE, DESCRIPCION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, "
		+ "ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO FROM SDB_ACC_PREDIO_SEGREGADO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_FIND_BY_CIRCULO1_MATRICULA1. */
	private static final String cs_FIND_BY_CIRCULO1_MATRICULA1 = "SELECT ID_PREDIO_SEGREGADO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, "
		+ "ID_ANOTACION, ID_CIRCULO1, ID_MATRICULA1, ID_ANOTACION1, LOTE, DESCRIPCION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, "
		+ "ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO FROM SDB_ACC_PREDIO_SEGREGADO WHERE ID_CIRCULO1 = ? AND ID_MATRICULA1 = ?";

	/** Constante cs_FIND_BY_CIRCULO1_MATRICULA1. */
	private static final String cs_FIND_BY_CIRCULO1_MATRICULA1_TURNO = "SELECT ID_PREDIO_SEGREGADO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, "
		+ "ID_ANOTACION, ID_CIRCULO1, ID_MATRICULA1, ID_ANOTACION1, LOTE, DESCRIPCION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, "
		+ "ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO FROM SDB_ACC_PREDIO_SEGREGADO WHERE ID_CIRCULO1 = ? AND ID_MATRICULA1 = ? AND ID_TURNO = ? ";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_TURNO. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_TURNO = "SELECT ID_PREDIO_SEGREGADO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, "
		+ "ID_ANOTACION, ID_CIRCULO1, ID_MATRICULA1, ID_ANOTACION1, LOTE, DESCRIPCION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, "
		+ "ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO FROM SDB_ACC_PREDIO_SEGREGADO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO = ? ";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_ANOTACION_TURNO. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_ANOTACION_TURNO = "SELECT ID_PREDIO_SEGREGADO, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, "
		+ "ID_ANOTACION, ID_CIRCULO1, ID_MATRICULA1, ID_ANOTACION1, LOTE, DESCRIPCION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, "
		+ "ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO FROM SDB_ACC_PREDIO_SEGREGADO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?  AND ID_ANOTACION = ? AND ID_TURNO = ? ";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_PREDIO_SEGREGADO_ID_PREDIO_SEGREGADO.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_PREDIO_SEGREGADO (ID_PREDIO_SEGREGADO, ID_TURNO_HISTORIA, "
		+ "ID_CIRCULO, ID_MATRICULA, ID_ANOTACION, ID_CIRCULO1, ID_MATRICULA1, ID_ANOTACION1, LOTE, DESCRIPCION, ID_USUARIO_CREACION, "
		+ "FECHA_CREACION, IP_CREACION, ID_TURNO) VALUES (?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante CS_UPDATE. */
	private static final String CS_UPDATE = "UPDATE SDB_ACC_PREDIO_SEGREGADO SET ID_ANOTACION = ?, ID_ANOTACION1 = ?, LOTE = ?, DESCRIPCION = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_CIRCULO1 = ? AND ID_MATRICULA1 = ? AND ID_TURNO = ? ";

	/** Constante FIND_PREDIO_ACC. */
	private static final String FIND_PREDIO_ACC = "SELECT * FROM SDB_ACC_PREDIO_SEGREGADO APS INNER JOIN SDB_ACC_MATRICULA_SEGREGACION AMS ON AMS.ID_CIRCULO_TEMPORAL = APS.ID_CIRCULO1 AND AMS.ID_MATRICULA_TEMPORAL = APS.ID_MATRICULA1 INNER JOIN SDB_ACC_TURNO SAT ON SAT.ID_SOLICITUD = AMS.ID_SOLICITUD WHERE SAT.ID_TURNO = ? AND APS.ID_CIRCULO1 = ? AND APS.ID_MATRICULA1 = ?";

	/** Constante cs_DELETE_BY_CIRCULO_MATRICULA_TURNO. */
	private static final String cs_DELETE_BY_CIRCULO_MATRICULA_TURNO = "DELETE FROM SDB_ACC_PREDIO_SEGREGADO WHERE ID_CIRCULO = ? "
		+ "AND ID_MATRICULA = ? AND ID_TURNO = ? ";

	/** Constante cs_DELETE_BY_ID. */
	private static final String cs_DELETE_BY_ID = "DELETE FROM SDB_ACC_PREDIO_SEGREGADO WHERE ID_CIRCULO = ? "
		+ "AND ID_MATRICULA = ? AND AND ID_CIRCULO1 = ? AND ID_MATRICULA1 = ? ID_TURNO = ? ";

	/** Constante cs_DELETE_BY_CIRCULO_MATRICULA_ANOTACION_TURNO. */
	private static final String cs_DELETE_BY_CIRCULO_MATRICULA_ANOTACION_TURNO = "DELETE FROM SDB_ACC_PREDIO_SEGREGADO WHERE ID_CIRCULO = ? "
		+ "AND ID_MATRICULA = ? AND ID_ANOTACION = ? AND ID_TURNO = ? ";

	/**
	 * Metodo encargado de eliminar registros de la tabla SDB_ACC_PREDIO_SEGREGADO  para un ID_CIRCULO, ID_MATRICULA, ID_ANOTACION, ID_TURNO.
	 *
	 * @param aps_param correspondiente al valor del tipo de objeto PredioSegregado
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteByCirculoMatriculaAnotacionTurno(PredioSegregado aps_param)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		int               li_column;

		li_column     = 1;

		lps_ps = null;

		if(aps_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE_BY_CIRCULO_MATRICULA_ANOTACION_TURNO);

				lps_ps.setString(li_column++, aps_param.getIdCirculo());
				lps_ps.setLong(li_column++, aps_param.getIdMatricula());
				setLong(lps_ps, aps_param.getIdAnotacion(), li_column++);
				lps_ps.setString(li_column++, aps_param.getIdTurno());

				lps_ps.executeQuery();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByCirculoMatriculaAnotacionTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de eliminar registros de la tabla SDB_ACC_PREDIO_SEGREGADO  para un ID_CIRCULO, ID_MATRICULA, ID_TURNO.
	 *
	 * @param aps_param correspondiente al valor del tipo de objeto PredioSegregado
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteByCirculoMatriculaTurno(PredioSegregado aps_param)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		int               li_column;

		li_column     = 1;

		lps_ps = null;

		if(aps_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE_BY_CIRCULO_MATRICULA_TURNO);

				lps_ps.setString(li_column++, aps_param.getIdCirculo());
				lps_ps.setLong(li_column++, aps_param.getIdMatricula());
				lps_ps.setString(li_column++, aps_param.getIdTurno());

				lps_ps.executeQuery();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByCirculoMatriculaTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de eliminar registros de la tabla SDB_ACC_PREDIO_SEGREGADO para un ID_CIRCULO, ID_MATRICULA, ID_TURNO, ID_CIRCULO1, ID_MATRICULA1.
	 *
	 * @param aps_param correspondiente al valor del tipo de objeto PredioSegregado
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteById(PredioSegregado aps_param)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		if(aps_param != null)
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE_BY_ID);

				lps_ps.setString(li_column++, aps_param.getIdCirculo());
				lps_ps.setLong(li_column++, aps_param.getIdMatricula());
				lps_ps.setString(li_column++, aps_param.getIdCirculo1());
				lps_ps.setLong(li_column++, aps_param.getIdMatricula1());
				lps_ps.setString(li_column++, aps_param.getIdTurno());

				lps_ps.executeQuery();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de buscar todos los registros de la tabla SDB_ACC_PREDIO_SEGREGADO  para un ID_CIRCULO1, ID_MATRICULA1.
	 *
	 * @param aps_param id de la matrícula a consultar
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<PredioSegregado> findAllByCirculo1Matricula1(PredioSegregado aps_param)
	    throws B2BException
	{
		return (aps_param != null)
		? findAllByCirculo1Matricula1(aps_param.getIdCirculo1(), aps_param.getIdMatricula1()) : null;
	}

	/**
	 * Metodo encargado de buscar todos los registros de la tabla SDB_ACC_PREDIO_SEGREGADO  para un ID_CIRCULO1, ID_MATRICULA1.
	 *
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id de la matrícula a consultar
	 * @return Collection<PredioSegregado>
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<PredioSegregado> findAllByCirculo1Matricula1(String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		Collection<PredioSegregado> lcps_predioSegregado;

		lcps_predioSegregado = new ArrayList<PredioSegregado>();

		if(StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0L))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO1_MATRICULA1);

				lps_ps.setString(li_column++, as_idCirculo);
				lps_ps.setLong(li_column++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcps_predioSegregado.add(getObjetFromResultSet(lrs_rs));
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

		if(lcps_predioSegregado.isEmpty())
			lcps_predioSegregado = null;

		return lcps_predioSegregado;
	}

	/**
	 * Metodo encargado de buscar todos los registros de la tabla SDB_ACC_PREDIO_SEGREGADO  para un ID_CIRCULO1, ID_MATRICULA1, ID_TURNO.
	 *
	 * @param aps_param id de la matrícula a consultar
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<PredioSegregado> findAllByCirculo1Matricula1Turno(PredioSegregado aps_param)
	    throws B2BException
	{
		return (aps_param != null)
		? findAllByCirculo1Matricula1Turno(
		    aps_param.getIdCirculo1(), aps_param.getIdMatricula1(), aps_param.getIdTurno()
		) : null;
	}

	/**
	 * Metodo encargado de buscar todos los registros de la tabla SDB_ACC_PREDIO_SEGREGADO  para un ID_CIRCULO1, ID_MATRICULA1, ID_TURNO.
	 *
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id de la matrícula a consultar
	 * @param as_idTurno id del turno del proceso
	 * @return Collection<PredioSegregado>
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<PredioSegregado> findAllByCirculo1Matricula1Turno(
	    String as_idCirculo, long al_idMatricula, String as_idTurno
	)
	    throws B2BException
	{
		Collection<PredioSegregado> lcps_predioSegregado;

		lcps_predioSegregado = new ArrayList<PredioSegregado>();

		if(StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0L))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_CIRCULO1_MATRICULA1_TURNO);

				lps_ps.setString(li_column++, as_idCirculo);
				lps_ps.setLong(li_column++, al_idMatricula);
				lps_ps.setString(li_column++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcps_predioSegregado.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByCirculo1Matricula1Turno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcps_predioSegregado.isEmpty())
			lcps_predioSegregado = null;

		return lcps_predioSegregado;
	}

	/**
	 * Metodo encargado de buscar todos los registros de la tabla SDB_ACC_PREDIO_SEGREGADO  para un ID_CIRCULO, ID_MATRICULA.
	 *
	 * @param aps_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<PredioSegregado> findAllByCirculoMatricula(PredioSegregado aps_param)
	    throws B2BException
	{
		return (aps_param != null) ? findAllByCirculoMatricula(aps_param.getIdCirculo(), aps_param.getIdMatricula())
		                           : null;
	}

	/**
	 * Metodo encargado de buscar todos los registros de la tabla SDB_ACC_PREDIO_SEGREGADO  para un ID_CIRCULO, ID_MATRICULA.
	 *
	 * @param as_idCirculo param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @param al_idMatricula param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<PredioSegregado> findAllByCirculoMatricula(String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		Collection<PredioSegregado> lcps_predioSegregado;

		lcps_predioSegregado = new ArrayList<PredioSegregado>();

		if(StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0))
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
					lcps_predioSegregado.add(getObjetFromResultSet(lrs_rs));
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

		return lcps_predioSegregado;
	}

	/**
	 * Metodo encargado de buscar un registro en la tabla SDB_ACC_PREDIO_SEGREGADO  para un ID_CIRCULO, ID_MATRICULA y ID_TURNO.
	 *
	 * @param aps_param correspondiente al valor del tipo de objeto PredioSegregado
	 * @return PredioSegregado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioSegregado
	 */
	public Collection<PredioSegregado> findAllByCirculoMatriculaTurno(PredioSegregado aps_param)
	    throws B2BException
	{
		Collection<PredioSegregado> lcps_return;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcps_return     = new ArrayList<PredioSegregado>();
		lps_ps          = null;
		lrs_rs          = null;

		if(aps_param != null)
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_TURNO);

				lps_ps.setString(li_column++, aps_param.getIdCirculo());
				lps_ps.setLong(li_column++, aps_param.getIdMatricula());
				lps_ps.setString(li_column++, aps_param.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcps_return.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByCirculoMatriculaTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcps_return.isEmpty())
			lcps_return = null;

		return lcps_return;
	}

	/**
	 * Metodo encargado de buscar todos los registros de la tabla SDB_ACC_PREDIO_SEGREGADO  para un ID_CIRCULO, ID_MATRICULA, ID_CIRCULO1, ID_MATRICULA1, ID_TURNO.
	 *
	 * @param aps_param Objeto que contiene los datos para realizar la búsqueda.
	 * @return Objeto que contiene la información consultada.
	 * @throws B2BException
	 */
	public PredioSegregado findByAllId(PredioSegregado aps_param)
	    throws B2BException
	{
		return (aps_param != null)
		? findByAllId(
		    aps_param.getIdCirculo(), NumericUtils.getLongWrapper(aps_param.getIdMatricula()), aps_param.getIdCirculo1(),
		    NumericUtils.getLongWrapper(aps_param.getIdMatricula1()), aps_param.getIdTurno()
		) : null;
	}

	/**
	 * Metodo encargado de buscar todos los registros de la tabla SDB_ACC_PREDIO_SEGREGADO  para un ID_CIRCULO, ID_MATRICULA, ID_CIRCULO1, ID_MATRICULA1, ID_TURNO.
	 *
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula.
	 * @param al_idMatricula id de la matrícula a consultar.
	 * @param as_idCirculo1 id del círculo 1 al cual pertenece la matrícula.
	 * @param al_idMatricula1 id de la matrícula 1 a consultar.
	 * @param as_idTurno id del turno del proceso.
	 * @return Objeto que contiene la información consultada.
	 * @throws B2BException
	 */
	public PredioSegregado findByAllId(
	    String as_idCirculo, Long al_idMatricula, String as_idCirculo1, Long al_idMatricula1, String as_idTurno
	)
	    throws B2BException
	{
		PredioSegregado lcps_predioSegregado;

		lcps_predioSegregado = null;

		if(
		    StringUtils.isValidString(as_idCirculo) && (NumericUtils.isValidLong(al_idMatricula))
			    && StringUtils.isValidString(as_idCirculo1) && (NumericUtils.isValidLong(al_idMatricula1))
			    && StringUtils.isValidString(as_idTurno)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ALL_ID);

				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);
				lps_ps.setString(li_column++, as_idCirculo1);
				setLong(lps_ps, al_idMatricula1, li_column++);
				lps_ps.setString(li_column++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcps_predioSegregado = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByAllId", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcps_predioSegregado;
	}

	/**
	 * Metodo encargado de buscar un registro en la tabla SDB_ACC_PREDIO_SEGREGADO  para un ID_CIRCULO, ID_MATRICULA.
	 *
	 * @param aps_param correspondiente al valor del tipo de objeto PredioSegregado
	 * @return PredioSegregado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioSegregado
	 */
	public PredioSegregado findByCirculoMatricula(PredioSegregado aps_param)
	    throws B2BException
	{
		PredioSegregado   lps_predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;

		li_column     = 1;

		lps_predio     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(aps_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);

				lps_ps.setString(li_column++, aps_param.getIdCirculo());
				lps_ps.setLong(li_column++, aps_param.getIdMatricula());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lps_predio = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lps_predio;
	}

	/**
	 * Metodo encargado de buscar registros en la tabla SDB_ACC_PREDIO_SEGREGADO  para un ID_CIRCULO, ID_MATRICULA, ID_ANOTACION y ID_TURNO.
	 *
	 * @param aps_param correspondiente al valor del tipo de objeto PredioSegregado
	 * @return Collection<PredioSegregado>
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<PredioSegregado> findByCirculoMatriculaAnotacionTurno(PredioSegregado aps_param)
	    throws B2BException
	{
		Collection<PredioSegregado> lps_predio;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;
		int                         li_column;

		li_column     = 1;

		lps_predio     = new ArrayList<PredioSegregado>();
		lps_ps         = null;
		lrs_rs         = null;

		if(aps_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_ANOTACION_TURNO);

				lps_ps.setString(li_column++, aps_param.getIdCirculo());
				lps_ps.setLong(li_column++, aps_param.getIdMatricula());
				setLong(lps_ps, aps_param.getIdAnotacion(), li_column++);
				lps_ps.setString(li_column++, aps_param.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lps_predio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatriculaAnotacionTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}

			if(!CollectionUtils.isValidCollection(lps_predio))
				lps_predio = null;
		}

		return lps_predio;
	}

	/**
	 * Metodo encargado de buscar un registro en la tabla SDB_ACC_PREDIO_SEGREGADO  para un ID_CIRCULO, ID_MATRICULA y ID_TURNO.
	 *
	 * @param aps_param correspondiente al valor del tipo de objeto PredioSegregado
	 * @return PredioSegregado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioSegregado
	 */
	public PredioSegregado findByCirculoMatriculaTurno(PredioSegregado aps_param)
	    throws B2BException
	{
		PredioSegregado   lps_predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;

		li_column     = 1;

		lps_predio     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(aps_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_TURNO);

				lps_ps.setString(li_column++, aps_param.getIdCirculo());
				lps_ps.setLong(li_column++, aps_param.getIdMatricula());
				lps_ps.setString(li_column++, aps_param.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lps_predio = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lps_predio;
	}

	/**
	 * Metodo encargado de buscar un registro en la tabla SDB_ACC_PREDIO_SEGREGADO  para un ID_CIRCULO, ID_MATRICULA y ID_TURNO.
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto Long
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @return Collection<PredioSegregado>
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<PredioSegregado> findByCirculoMatriculaTurno(
	    String as_idCirculo, Long al_idMatricula, String as_idTurno
	)
	    throws B2BException
	{
		Collection<PredioSegregado> lps_predio;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;
		int                         li_column;

		li_column     = 1;

		lps_predio     = new ArrayList<PredioSegregado>();
		lps_ps         = null;
		lrs_rs         = null;

		if(
		    StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula)
			    && StringUtils.isValidString(as_idTurno)
		)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_TURNO);

				lps_ps.setString(li_column++, as_idCirculo);
				lps_ps.setLong(li_column++, NumericUtils.getLong(al_idMatricula));
				lps_ps.setString(li_column++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lps_predio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lps_predio;
	}

	/**
	 * Metodo encargado de buscar un registro en la tabla SDB_ACC_PREDIO_SEGREGADO para el ID_PREDIO_SEGREGADO.
	 *
	 * @param aps_param correspondiente al valor del tipo de objeto PredioSegregado
	 * @return PredioSegregado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioSegregado
	 */
	public PredioSegregado findById(PredioSegregado aps_param)
	    throws B2BException
	{
		PredioSegregado   lps_predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;

		li_column     = 1;

		lps_predio     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(aps_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_column++, aps_param.getIdPredioSegregado());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lps_predio = getObjetFromResultSet(lrs_rs);
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

		return lps_predio;
	}

	/**
	 * Retorna el valor del objeto de PredioSegregado.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto PredioSegregado
	 * @return devuelve el valor de PredioSegregado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioSegregado
	 */
	public PredioSegregado findMatriculaDefinitiva(PredioSegregado as_s)
	    throws B2BException
	{
		PredioSegregado   lcpr_predioSegregado;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;

		li_column     = 1;

		lcpr_predioSegregado     = null;
		lps_ps                   = null;
		lrs_rs                   = null;

		try
		{
			lcpr_predioSegregado     = new PredioSegregado();

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO1_MATRICULA1);

			lps_ps.setString(li_column++, as_s.getIdCirculo1());
			lps_ps.setLong(li_column++, as_s.getIdMatricula1());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lcpr_predioSegregado = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findMatriculaDefinitiva", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcpr_predioSegregado;
	}

	/**
	 * Método encargado de validar si el predio es acc.
	 *
	 * @param as_idCirculo Variable que contiene el id del circulo.
	 * @param al_idMatricula Variable que contiene el id de la matrícula.
	 * @param as_idTurno Variable que contiene el id del turno.
	 * @return boolean que válida si es un predio acc.
	 * @throws B2BException
	 */
	public boolean findPredioAcc(String as_idCirculo, Long al_idMatricula, String as_idTurno)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		if(
		    StringUtils.isValidString(as_idCirculo) && StringUtils.isValidString(as_idTurno)
			    && NumericUtils.isValidLong(al_idMatricula)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(FIND_PREDIO_ACC);

				lps_ps.setString(li_column++, as_idTurno);
				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);

				lrs_rs        = lps_ps.executeQuery();
				lb_return     = lrs_rs.next();
			}
			catch(SQLException lse_e)
			{
				logError(this, "findPredioAcc", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lb_return;
	}

	/**
	 * Metodo encargado de calcular la secuencia de la tabla SDB_ACC_PREDIO_SEGREGADO.
	 *
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
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
	 * Metodo encargado de insertar un registro en la tabla SDB_ACC_PREDIO_SEGREGADO.
	 *
	 * @param aps_param correspondiente al valor del tipo de objeto PredioSegregado
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(PredioSegregado aps_param)
	    throws B2BException
	{
		if(aps_param != null)
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

				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));

				lps_ps.setLong(li_column++, aps_param.getIdTurnoHistoria());
				lps_ps.setString(li_column++, aps_param.getIdCirculo());
				lps_ps.setLong(li_column++, aps_param.getIdMatricula());
				setLong(lps_ps, aps_param.getIdAnotacion(), li_column++);
				lps_ps.setString(li_column++, aps_param.getIdCirculo1());
				lps_ps.setLong(li_column++, aps_param.getIdMatricula1());
				setLong(lps_ps, aps_param.getIdAnotacion1(), li_column++);
				lps_ps.setString(li_column++, aps_param.getLote());
				lps_ps.setString(li_column++, aps_param.getDescripcion());
				lps_ps.setString(li_column++, aps_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aps_param.getIpCreacion());
				lps_ps.setString(li_column++, aps_param.getIdTurno());

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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Actualiza el registro en la base de datos
	 *
	 * @param aps_param correspondiente al valor del tipo de objeto PredioSegregado
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateById(PredioSegregado aps_param)
	    throws B2BException
	{
		if(aps_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;
				lps_ps      = getConnection().prepareStatement(CS_UPDATE);

				setLong(lps_ps, aps_param.getIdAnotacion(), li_cont++);
				setLong(lps_ps, aps_param.getIdAnotacion1(), li_cont++);
				lps_ps.setString(li_cont++, aps_param.getLote());
				lps_ps.setString(li_cont++, aps_param.getDescripcion());
				lps_ps.setString(li_cont++, aps_param.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, aps_param.getIpModificacion());
				lps_ps.setString(li_cont++, aps_param.getIdCirculo());
				setLong(lps_ps, NumericUtils.getLongWrapper(aps_param.getIdMatricula()), li_cont++);
				lps_ps.setString(li_cont++, aps_param.getIdCirculo1());
				setLong(lps_ps, NumericUtils.getLongWrapper(aps_param.getIdMatricula1()), li_cont++);
				lps_ps.setString(li_cont++, aps_param.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de extraer los datos de un resulset y asignarlos en el objeto PredioSegregado.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return PredioSegregado
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private PredioSegregado getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		PredioSegregado lps_predioSegregado;

		lps_predioSegregado = new PredioSegregado();

		lps_predioSegregado.setIdPredioSegregado(ars_rs.getString("ID_PREDIO_SEGREGADO"));
		lps_predioSegregado.setIdTurnoHistoria(ars_rs.getLong("ID_TURNO_HISTORIA"));
		lps_predioSegregado.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lps_predioSegregado.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lps_predioSegregado.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		lps_predioSegregado.setIdCirculo1(ars_rs.getString("ID_CIRCULO1"));
		lps_predioSegregado.setIdMatricula1(ars_rs.getLong("ID_MATRICULA1"));
		lps_predioSegregado.setIdAnotacion1(getLong(ars_rs, "ID_ANOTACION1"));
		lps_predioSegregado.setLote(ars_rs.getString("LOTE"));
		lps_predioSegregado.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lps_predioSegregado.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lps_predioSegregado.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lps_predioSegregado.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lps_predioSegregado.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lps_predioSegregado.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lps_predioSegregado.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lps_predioSegregado.setIdTurno(ars_rs.getString("ID_TURNO"));

		return lps_predioSegregado;
	}
}
