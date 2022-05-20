package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase para poder realizar transacciones con la base de datos en la tabla SDB_ACC_AREA_PREDIO.
 *
 * @author Julian Vaca
 */
public class AccAreaPredioDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_AREA, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, ID_ANOTACION, "
		+ "COEFICIENTE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO, USO_PREDIO FROM SDB_ACC_AREA_PREDIO WHERE ID_AREA = ?";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT ID_AREA, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, ID_ANOTACION, COEFICIENTE, ID_USUARIO_CREACION, "
		+ "FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO, USO_PREDIO FROM SDB_ACC_AREA_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? ORDER BY FECHA_CREACION DESC";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_ANOTACION. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_ANOTACION = "SELECT ID_AREA, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, ID_ANOTACION, COEFICIENTE, ID_USUARIO_CREACION, "
		+ "FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO, USO_PREDIO FROM SDB_ACC_AREA_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ? ORDER BY ID_AREA DESC";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_TURNO. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_TURNO = "SELECT ID_AREA, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, ID_ANOTACION, COEFICIENTE, ID_USUARIO_CREACION, FECHA_CREACION, "
		+ "IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO, USO_PREDIO FROM SDB_ACC_AREA_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO = ?";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_TURNO_ANOTACION. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_TURNO_ANOTACION = "SELECT ID_AREA, ID_TURNO_HISTORIA, ID_CIRCULO, ID_MATRICULA, ID_ANOTACION, COEFICIENTE, ID_USUARIO_CREACION, FECHA_CREACION, "
		+ "IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ID_TURNO, USO_PREDIO FROM SDB_ACC_AREA_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO = ? AND ID_ANOTACION = ?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_AREA_PREDIO_ID_AREA.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_AREA_PREDIO (ID_AREA, ID_TURNO_HISTORIA, ID_CIRCULO, "
		+ "ID_MATRICULA, ID_ANOTACION, COEFICIENTE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_TURNO,USO_PREDIO) VALUES(?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?)";

	/** Constante cs_UPDATE_BY_ID. */
	private static final String cs_UPDATE_BY_ID = "UPDATE SDB_ACC_AREA_PREDIO SET ID_TURNO_HISTORIA = ?, ID_TURNO = ?, ID_ANOTACION = ?,  COEFICIENTE = ?,  ID_USUARIO_MODIFICACION = ?,  "
		+ "FECHA_MODIFICACION = SYSTIMESTAMP,  IP_MODIFICACION = ? ,USO_PREDIO = ? WHERE ID_AREA = ?";

	/** Constante cs_AREA_TOTAL_TERRENO. */
	private static final String cs_AREA_TOTAL_TERRENO = " SELECT DAP.AREA  FROM SDB_ACC_AREA_PREDIO AP INNER JOIN SDB_ACC_DETALLE_AREA_PREDIO DAP ON DAP.ID_AREA_PREDIO =  ID_AREA AND ID_TIPO_AREA = 1 WHERE AP.ID_CIRCULO = ? AND  AP.ID_MATRICULA = ? ";

	/**
	 * Método para poder obtener el area del terreno.
	 *
	 * @param aaap_param objeto AccAreaPredio con los datos para filtrar en la base de datos
	 * @return double
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see double
	 */
	public double areaTotalTerreno(AccAreaPredio aaap_param)
	    throws B2BException
	{
		double            ld_predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_count;

		ld_predio     = 0;
		lps_ps        = null;
		lrs_rs        = null;
		li_count      = 1;

		if(aaap_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_AREA_TOTAL_TERRENO);

				lps_ps.setString(li_count++, aaap_param.getIdCirculo());
				setLong(lps_ps, aaap_param.getIdMatricula(), li_count++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ld_predio = lrs_rs.getDouble("AREA");
			}
			catch(SQLException lse_e)
			{
				logError(this, "areaTotalTerreno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ld_predio;
	}

	/**
	 * Método para poder obtener si el area esta validada para calificación.
	 *
	 * @param aaap_param objeto AccAreaPredio con los datos para filtrar en la base de datos
	 * @return boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean areaValidadaCalificacion(AccAreaPredio aaap_param)
	    throws B2BException
	{
		AccAreaPredio     laap_areaPredio;
		boolean           lb_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_count;

		laap_areaPredio     = null;
		lb_return           = false;
		lps_ps              = null;
		lrs_rs              = null;
		li_count            = 1;

		if(aaap_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);

				lps_ps.setString(li_count++, aaap_param.getIdCirculo());
				setLong(lps_ps, aaap_param.getIdMatricula(), li_count++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
				{
					laap_areaPredio = getObjetFromResultSet(lrs_rs);

					if(laap_areaPredio != null)
					{
						String ls_idTurno;

						ls_idTurno = laap_areaPredio.getIdTurno();

						if(
						    StringUtils.isValidString(ls_idTurno)
							    && ls_idTurno.equalsIgnoreCase(aaap_param.getIdTurno())
						)
							lb_return = true;
					}
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "areaValidadaCalificacion", lse_e);

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
	 * Método para poder obtener los registros por el circulo y la matricula.
	 *
	 * @param aaap_param objeto AccAreaPredio con los datos para filtrar en la base de datos
	 * @return devuelve el valor de AccAreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccAreaPredio
	 */
	public AccAreaPredio findByCirculoMatricula(AccAreaPredio aaap_param)
	    throws B2BException
	{
		return (aaap_param != null) ? findByCirculoMatricula(aaap_param.getIdCirculo(), aaap_param.getIdMatricula())
		                            : null;
	}

	/**
	 * Método para poder obtener los registros por el circulo y la matricula.
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param ll_idMatricula correspondiente al valor del tipo de objeto Long
	 * @return devuelve el valor de AccAreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccAreaPredio
	 */
	public AccAreaPredio findByCirculoMatricula(String as_idCirculo, Long ll_idMatricula)
	    throws B2BException
	{
		AccAreaPredio     laap_predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_count;

		laap_predio     = null;
		lps_ps          = null;
		lrs_rs          = null;
		li_count        = 1;

		try
		{
			if(NumericUtils.isValidLong(ll_idMatricula) && StringUtils.isValidString(as_idCirculo))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);

				lps_ps.setString(li_count++, as_idCirculo);
				setLong(lps_ps, ll_idMatricula, li_count++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					laap_predio = getObjetFromResultSet(lrs_rs);
			}
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

		return laap_predio;
	}

	/**
	 * Método para poder obtener los registros por el circulo y la matricula de la anotacion.
	 *
	 * @param aaap_param objeto AccAreaPredio con los datos para filtrar en la base de datos
	 * @return devuelve el valor de AccAreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccAreaPredio
	 */
	public AccAreaPredio findByCirculoMatriculaAnotacion(AccAreaPredio aaap_param)
	    throws B2BException
	{
		AccAreaPredio     laap_predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_count;

		laap_predio     = null;
		lps_ps          = null;
		lrs_rs          = null;
		li_count        = 1;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_ANOTACION);

			lps_ps.setString(li_count++, aaap_param.getIdCirculo());
			setLong(lps_ps, aaap_param.getIdMatricula(), li_count++);
			setLong(lps_ps, aaap_param.getIdAnotacion(), li_count++);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				laap_predio = getObjetFromResultSet(lrs_rs);
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

		return laap_predio;
	}

	/**
	 * Método para poder obtener los registros por el circulo y la matricula del turno.
	 *
	 * @param aaap_param objeto AccAreaPredio con los datos para filtrar en la base de datos
	 * @return Objeto resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccAreaPredio
	 */
	public AccAreaPredio findByCirculoMatriculaTurno(AccAreaPredio aaap_param)
	    throws B2BException
	{
		return (aaap_param != null)
		? findByCirculoMatriculaTurno(aaap_param.getIdCirculo(), aaap_param.getIdMatricula(), aaap_param.getIdTurno())
		: null;
	}

	/**
	 * Método para poder obtener los registros por el circulo y la matricula del turno.
	 *
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id de la matrícula a utilizar como filtro en la busqueda
	 * @param as_idTurno id del turno asociado a la matrícula
	 * @return Objeto resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccAreaPredio
	 */
	public AccAreaPredio findByCirculoMatriculaTurno(String as_idCirculo, Long al_idMatricula, String as_idTurno)
	    throws B2BException
	{
		AccAreaPredio laap_predio;

		laap_predio = null;

		if(
		    StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula)
			    && StringUtils.isValidString(as_idTurno)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_TURNO);

				lps_ps.setString(li_count++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_count++);
				lps_ps.setString(li_count++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					laap_predio = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatriculaTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return laap_predio;
	}

	/**
	 * Método para poder obtener los registros por el circulo y la matricula, turno y la anotación.
	 *
	 * @param aaap_param objeto AccAreaPredio con los datos para filtrar en la base de datos
	 * @return devuelve el valor de AccAreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccAreaPredio
	 */
	public AccAreaPredio findByCirculoMatriculaTurnoAnotacion(AccAreaPredio aaap_param)
	    throws B2BException
	{
		AccAreaPredio     laap_predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_count;

		laap_predio     = null;
		lps_ps          = null;
		lrs_rs          = null;
		li_count        = 1;

		if(aaap_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_TURNO_ANOTACION);

				lps_ps.setString(li_count++, aaap_param.getIdCirculo());
				setLong(lps_ps, aaap_param.getIdMatricula(), li_count++);
				lps_ps.setString(li_count++, aaap_param.getIdTurno());
				setLong(lps_ps, aaap_param.getIdAnotacion(), li_count++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					laap_predio = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatriculaTurnoAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return laap_predio;
	}

	/**
	 * Método para poder obtener los registros por el idArea.
	 *
	 * @param aaap_param objeto AccAreaPredio con los datos para filtrar en la base de datos
	 * @return devuelve el valor de AccAreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccAreaPredio
	 */
	public AccAreaPredio findById(AccAreaPredio aaap_param)
	    throws B2BException
	{
		AccAreaPredio     laap_predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		laap_predio     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(aaap_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, aaap_param.getIdArea());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					laap_predio = getObjetFromResultSet(lrs_rs);
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

		return laap_predio;
	}

	/**
	 * Método para poder encontrar la secuencia.
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
	 * Método para poder insertar un registro en la base de datos.
	 *
	 * @param aaap_param objeto AccAreaPredio con los datos para filtrar en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(AccAreaPredio aaap_param)
	    throws B2BException
	{
		if(aaap_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, aaap_param.getIdArea());
				setLong(lps_ps, aaap_param.getIdTurnoHistoria(), li_column++);
				lps_ps.setString(li_column++, aaap_param.getIdCirculo());
				setLong(lps_ps, aaap_param.getIdMatricula(), li_column++);
				setLong(lps_ps, aaap_param.getIdAnotacion(), li_column++);
				setDouble(lps_ps, aaap_param.getCoeficiente(), li_column++);
				lps_ps.setString(li_column++, aaap_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aaap_param.getIpCreacion());
				lps_ps.setString(li_column++, aaap_param.getIdTurno());
				lps_ps.setString(li_column++, aaap_param.getTipoSuelo());

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
			}
		}
	}

	/**
	 * Método para actualizar un registro en la base de datos.
	 *
	 * @param aaap_param objeto para poder obtener los datos para actualizar el campo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateById(AccAreaPredio aaap_param)
	    throws B2BException
	{
		if(aaap_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_BY_ID);

				setLong(lps_ps, aaap_param.getIdTurnoHistoria(), li_column++);
				lps_ps.setString(li_column++, aaap_param.getIdTurno());
				setLong(lps_ps, aaap_param.getIdAnotacion(), li_column++);
				setDouble(lps_ps, aaap_param.getCoeficiente(), li_column++);
				lps_ps.setString(li_column++, aaap_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aaap_param.getIpModificacion());
				lps_ps.setString(li_column++, aaap_param.getTipoSuelo());
				lps_ps.setString(li_column++, aaap_param.getIdArea());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "UpdateById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para poder llenar el objeto AccAreaPredio con el resultado de la base de datos.
	 *
	 * @param ars_rs objeto con el resultado de la base de datos
	 * @return el valor de objet from result set
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AccAreaPredio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AccAreaPredio laap_areaPredio;

		laap_areaPredio = new AccAreaPredio();

		laap_areaPredio.setIdArea(ars_rs.getString("ID_AREA"));
		laap_areaPredio.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));
		laap_areaPredio.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		laap_areaPredio.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		laap_areaPredio.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));

		{
			Double ld_coeficiente;

			ld_coeficiente = getDouble(ars_rs, "COEFICIENTE");

			laap_areaPredio.setCoeficiente(ld_coeficiente);

			if(NumericUtils.isValidDouble(ld_coeficiente, 0, false))
				laap_areaPredio.setCoeficienteLectura(StringUtils.getString(ld_coeficiente));
		}

		laap_areaPredio.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		laap_areaPredio.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		laap_areaPredio.setIpCreacion(ars_rs.getString("IP_CREACION"));
		laap_areaPredio.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		laap_areaPredio.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		laap_areaPredio.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		laap_areaPredio.setIdTurno(ars_rs.getString("ID_TURNO"));
		laap_areaPredio.setTipoSuelo(ars_rs.getString("USO_PREDIO"));

		return laap_areaPredio;
	}
}
