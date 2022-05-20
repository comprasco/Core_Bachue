package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para poder realizar transacciones con la base de datos en la tabla SDB_ACC_ANOTACION_PREDIO_CIUDADANO.
 *
 * @author Julian Vaca
 */
public class AnotacionPredioCiudadanoDAO extends BaseDAO
{
	/** Constante cs_DELETE_BY_ID. */
	private static final String cs_DELETE_BY_ID = " DELETE FROM SDB_ACC_ANOTACION_PREDIO_CIUDADANO  WHERE ID_ANOTACION_PREDIO = ? ";

	/** Constante cs_DELETE_BY_ID_CIUDADANO. */
	private static final String cs_DELETE_BY_ID_CIUDADANO = " DELETE FROM SDB_ACC_ANOTACION_PREDIO_CIUDADANO  WHERE ID_ANOTACION_PREDIO_CIUDADANO = ? ";

	/** Constante cs_FIND_BY_ID_ANOTACION. */
	private static final String cs_FIND_BY_ID_ANOTACION = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO_CIUDADANO WHERE ID_ANOTACION_PREDIO = ?";

	/** Constante cs_FIND_PERSONA_BY_ID_TURNO. */
	private static final String cs_FIND_PERSONA_BY_ID_TURNO = "SELECT DISTINCT(ID_PERSONA) FROM SDB_ACC_ANOTACION_PREDIO_CIUDADANO WHERE ID_TURNO = ?";

	/** Constante cs_FIND_BY_ID_ANOTACION_PERSONA. */
	private static final String cs_FIND_BY_ID_ANOTACION_PERSONA = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO_CIUDADANO WHERE ID_ANOTACION_PREDIO = ? AND ID_PERSONA = ?";

	/** Constante cs_FIND_BY_ID_ANOTACION_PERSONA_ROL. */
	private static final String cs_FIND_BY_ID_ANOTACION_PERSONA_ROL = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO_CIUDADANO WHERE ID_ANOTACION_PREDIO = ? AND ID_ANOTACION = ? AND ID_PERSONA = ? AND ROL_PERSONA = ?";

	/** Constante cs_FIND_BY_ID_ANOTACION_PREDIO_CIUDADANO. */
	private static final String cs_FIND_BY_ID_ANOTACION_PREDIO_CIUDADANO = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO_CIUDADANO WHERE ID_ANOTACION_PREDIO_CIUDADANO = ?";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO_CIUDADANO "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ? ORDER BY ID_ANOTACION ASC, ROL_PERSONA DESC";

	/** Constante cs_FIND_BY_ANOT_PREDIO_CIR_MAT_ANOT_PER. */
	private static final String cs_FIND_BY_ANOT_PREDIO_CIR_MAT_ANOT_PER = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO_CIUDADANO "
		+ "WHERE ID_ANOTACION_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ? AND ID_PERSONA = ? ORDER BY ID_ANOTACION ASC, ROL_PERSONA DESC";

	/** Constante cs_FIND_BY_ANOT_PREDIO_CIR_MAT_ANOT_PER_ROL. */
	private static final String cs_FIND_BY_ANOT_PREDIO_CIR_MAT_ANOT_PER_ROL = "SELECT * FROM SDB_ACC_ANOTACION_PREDIO_CIUDADANO "
		+ "WHERE ID_ANOTACION_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ? AND ID_PERSONA = ? AND ROL_PERSONA = ? ORDER BY ID_ANOTACION ASC, ROL_PERSONA DESC";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_ANOTACION_PREDIO_CIUDADANO_ID_ANOTACION_PREDIO_CIUDADANO.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_ANOTACION_PREDIO_CIUDADANO (ID_ANOTACION_PREDIO_CIUDADANO,"
		+ "ID_ANOTACION_PREDIO,ID_CIRCULO,ID_MATRICULA,ID_ANOTACION,ID_PERSONA,ROL_PERSONA,PROPIETARIO,PORCENTAJE_PARTICIPACION,"
		+ "ID_PARTE_INTERESADA,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ESTADO,ID_TURNO) "
		+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?)";

	/** Constante cs_DATA_INTERVINIENTES. */
	private static final String cs_DATA_INTERVINIENTES = " SELECT ACPC.ROL_PERSONA,SAP.ID_DOCUMENTO_TIPO,SAP.NUMERO_DOCUMENTO ,ACPC.ID_PERSONA ,"
		+ " NVL(SAP.PRIMER_NOMBRE,' ') || ' ' ||  NVL(SAP.SEGUNDO_NOMBRE,' ') || ' ' ||  NVL(SAP.PRIMER_APELLIDO,' ') || ' ' ||  NVL(SAP.SEGUNDO_APELLIDO,' ') || ' ' ||  NVL(SAP.RAZON_SOCIAL,' ') NOMBRE_PERSONA"
		+ " FROM SDB_ACC_ANOTACION_PREDIO_CIUDADANO ACPC INNER JOIN SDB_ACC_PERSONA SAP  ON  SAP.ID_PERSONA = ACPC.ID_PERSONA  WHERE ACPC.ID_ANOTACION_PREDIO = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_ANOTACION_PREDIO_CIUDADANO "
		+ "SET ROL_PERSONA = ?, PROPIETARIO = ?, PORCENTAJE_PARTICIPACION = ?, ID_PARTE_INTERESADA =?, "
		+ "ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP "
		+ "WHERE ID_ANOTACION_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ?  AND ID_PERSONA = ?";

	/** Constante cs_UPDATE_ANOTACION_ID. */
	private static final String cs_UPDATE_ANOTACION_ID = "UPDATE SDB_ACC_ANOTACION_PREDIO_CIUDADANO"
		+ " SET ID_ANOTACION = ?, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP"
		+ " WHERE ID_ANOTACION_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_PERSONA = ?";

	/** Constante cs_UPDATE_INTERESADA_RRR. */
	private static final String cs_UPDATE_INTERESADA_RRR = " UPDATE SDB_ACC_ANOTACION_PREDIO_CIUDADANO SET ID_PARTE_INTERESADA =null ,ID_USUARIO_MODIFICACION = ? ,IP_MODIFICACION = ?  ,FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_ANOTACION_PREDIO = ? ";

	/**
	 * Método para encontrar registros por un filtro en especifico en la BD.
	 *
	 * @param as_idPredioCiudadano String para poder filtrar en la BD
	 * @return Colleccion de AnotacionPredioCiudadano
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredioCiudadano> dataIntervinientes(String as_idPredioCiudadano)
	    throws B2BException
	{
		Collection<AnotacionPredioCiudadano> lcapc_datos;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lcapc_datos     = new ArrayList<AnotacionPredioCiudadano>();
		lps_ps          = null;
		lrs_rs          = null;

		if(StringUtils.isValidString(as_idPredioCiudadano))
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_DATA_INTERVINIENTES);

				lps_ps.setString(li_contador++, as_idPredioCiudadano);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcapc_datos.add(getDataIntervinientes(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "dataIntervinientes", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcapc_datos))
			lcapc_datos = null;

		return lcapc_datos;
	}

	/**
	 * Método para poder eliminar los intervinientes por un filtro especifico en la BD.
	 *
	 * @param as_param String para con el id poder eliminar el registro
	 * @return objeto AnotacionPredioCiudadano
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredioCiudadano
	 */
	public AnotacionPredioCiudadano deleteById(String as_param)
	    throws B2BException
	{
		AnotacionPredioCiudadano lapc_predio;
		PreparedStatement        lps_ps;

		lapc_predio     = null;
		lps_ps          = null;

		if(StringUtils.isValidString(as_param))
		{
			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_DELETE_BY_ID_CIUDADANO);

				lps_ps.setString(li_contador++, as_param);

				lps_ps.executeUpdate();
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

		return lapc_predio;
	}

	/**
	 * Método para poder eliminar los intervinientes por un filtro especifico en la BD.
	 *
	 * @param as_param String para con el id poder eliminar el registro
	 * @return objeto AnotacionPredioCiudadano
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredioCiudadano
	 */
	public AnotacionPredioCiudadano deleteIntervinientes(String as_param)
	    throws B2BException
	{
		AnotacionPredioCiudadano lapc_predio;
		PreparedStatement        lps_ps;

		lapc_predio     = null;
		lps_ps          = null;

		if(StringUtils.isValidString(as_param))
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_DELETE_BY_ID);

				lps_ps.setString(li_contador++, as_param);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteIntervinientes", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}

		return lapc_predio;
	}

	/**
	 * Método de sobre carga para saber si el objeto AnotacionPredioCiudadano tiene un idAnotacion y llama al metodo de eliminar intervinientes.
	 *
	 * @param aapc_param objeto AnotacionPredioCiudadano para saber si tiene id
	 * @return objeto AnotacionPredioCiudadano
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredioCiudadano
	 */
	public AnotacionPredioCiudadano deleteIntervinientes(AnotacionPredioCiudadano aapc_param)
	    throws B2BException
	{
		return (aapc_param != null) ? deleteIntervinientes(aapc_param.getIdAnotacionPredio()) : null;
	}

	/**
	 * Método para encontrar registros por un filtro en especifico en la BD.
	 *
	 * @param aapc_param String para poder filtrar en la BD
	 * @return Colleccion de AnotacionPredioCiudadano
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredioCiudadano
	 */
	public AnotacionPredioCiudadano findByAnotPredioCirMatAnotPer(AnotacionPredioCiudadano aapc_param)
	    throws B2BException
	{
		AnotacionPredioCiudadano lcapc_datos;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcapc_datos     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(aapc_param != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ANOT_PREDIO_CIR_MAT_ANOT_PER);

				lps_ps.setString(li_contador++, aapc_param.getIdAnotacionPredio());
				lps_ps.setString(li_contador++, aapc_param.getIdCirculo());
				lps_ps.setLong(li_contador++, aapc_param.getIdMatricula());
				lps_ps.setLong(li_contador++, aapc_param.getIdAnotacion());
				lps_ps.setString(li_contador++, aapc_param.getIdPersona());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcapc_datos = getAnotacinoPredioCiudadano(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByAnotPredioCirMatAnotPer", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcapc_datos;
	}

	/**
	 * Método para encontrar registros por un filtro en especifico en la BD.
	 *
	 * @param aapc_param String para poder filtrar en la BD
	 * @return Colección de AnotacionPredioCiudadano
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredioCiudadano
	 */
	public AnotacionPredioCiudadano findByAnotPredioCirMatAnotPerRol(AnotacionPredioCiudadano aapc_param)
	    throws B2BException
	{
		AnotacionPredioCiudadano lcapc_datos;
		lcapc_datos = null;

		if(aapc_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ANOT_PREDIO_CIR_MAT_ANOT_PER_ROL);

				lps_ps.setString(li_contador++, aapc_param.getIdAnotacionPredio());
				lps_ps.setString(li_contador++, aapc_param.getIdCirculo());
				lps_ps.setLong(li_contador++, aapc_param.getIdMatricula());
				lps_ps.setLong(li_contador++, aapc_param.getIdAnotacion());
				lps_ps.setString(li_contador++, aapc_param.getIdPersona());
				lps_ps.setString(li_contador++, aapc_param.getRolPersona());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcapc_datos = getAnotacinoPredioCiudadano(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByAnotPredioCirMatAnotPerRol", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcapc_datos;
	}

	/**
	 * Método para encontrar registros por un filtro en especifico en la BD.
	 *
	 * @param aapc_param String para poder filtrar en la BD
	 * @return Colección de AnotacionPredioCiudadano
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredioCiudadano> findByCirculoMatricula(AnotacionPredioCiudadano aapc_param)
	    throws B2BException
	{
		return (aapc_param != null)
		? findByCirculoMatricula(aapc_param.getIdCirculo(), aapc_param.getIdMatricula(), aapc_param.getIdAnotacion())
		: null;
	}

	/**
	 * Consulta todos lo registros asociados a una matrícula e id anotación.
	 *
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id de la matrícula a utilizar como filtro en la busqueda
	 * @param al_idAnotacion id de la anotación asociada a la matrícula
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredioCiudadano> findByCirculoMatricula(
	    String as_idCirculo, long al_idMatricula, long al_idAnotacion
	)
	    throws B2BException
	{
		Collection<AnotacionPredioCiudadano> lcapc_datos;

		lcapc_datos = new ArrayList<AnotacionPredioCiudadano>();

		if(StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0L) && (al_idAnotacion > 0L))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);

				lps_ps.setString(li_contador++, as_idCirculo);
				lps_ps.setLong(li_contador++, al_idMatricula);
				lps_ps.setLong(li_contador++, al_idAnotacion);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcapc_datos.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcapc_datos))
			lcapc_datos = null;

		return lcapc_datos;
	}

	/**
	 * Método para encontrar un registro por un filtro en especifico en la BD.
	 *
	 * @param aapc_param String para poder filtrar en la BD
	 * @return objeto AnotacionPredioCiudadano
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredioCiudadano
	 */
	public AnotacionPredioCiudadano findById(AnotacionPredioCiudadano aapc_param)
	    throws B2BException
	{
		AnotacionPredioCiudadano lapc_predio;
		lapc_predio = null;

		if(aapc_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID_ANOTACION_PREDIO_CIUDADANO);

				lps_ps.setString(li_contador++, aapc_param.getIdAnotacionPredioCiudadano());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lapc_predio = getAnotacinoPredioCiudadano(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		return lapc_predio;
	}

	/**
	 * Método para encontrar registros por un filtro en especifico en la BD.
	 *
	 * @param aapc_idPredioCiudadano correspondiente al valor del tipo de objeto AnotacionPredioCiudadano
	 * @return Colección de AnotacionPredioCiudadano
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredioCiudadano> findByIdAnotacion(AnotacionPredioCiudadano aapc_idPredioCiudadano)
	    throws B2BException
	{
		Collection<AnotacionPredioCiudadano> lcapc_datos;
		lcapc_datos = new ArrayList<AnotacionPredioCiudadano>();

		if(aapc_idPredioCiudadano != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ANOTACION);

				lps_ps.setString(li_contador++, aapc_idPredioCiudadano.getIdAnotacionPredio());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcapc_datos.add(getAnotacinoPredioCiudadano(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcapc_datos))
			lcapc_datos = null;

		return lcapc_datos;
	}

	/**
	 * Consulta en la base de datos todos los registros filtrando por un id anotación
	 * predio y un id persona definidos previamente.
	 *
	 * @param aapc_idPredioCiudadano Objeto contenedor de los datos que se utilizaran como
	 * filtro en la consulta
	 * @return Colección con los objetos resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AnotacionPredioCiudadano> findByIdAnotacionPersona(
	    AnotacionPredioCiudadano aapc_idPredioCiudadano
	)
	    throws B2BException
	{
		Collection<AnotacionPredioCiudadano> lcapc_datos;
		lcapc_datos = new ArrayList<AnotacionPredioCiudadano>();

		if(aapc_idPredioCiudadano != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ANOTACION_PERSONA);

				lps_ps.setString(li_contador++, aapc_idPredioCiudadano.getIdAnotacionPredio());
				lps_ps.setString(li_contador++, aapc_idPredioCiudadano.getIdPersona());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcapc_datos.add(getAnotacinoPredioCiudadano(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdAnotacionPersona", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcapc_datos))
			lcapc_datos = null;

		return lcapc_datos;
	}

	/**
	 * Método para encontrar un registro por un flirtro especifico en la BD.
	 *
	 * @param aapc_param objeto AnotacionPredioCiudadano para extraer la información para el filtro en la BD
	 * @return objeto AnotacionPredioCiudadano
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredioCiudadano
	 */
	public AnotacionPredioCiudadano findByIdAnotacionPersonaRol(AnotacionPredioCiudadano aapc_param)
	    throws B2BException
	{
		AnotacionPredioCiudadano lapc_predio;

		lapc_predio = null;

		if(aapc_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lrs_rs     = null;
			lps_ps     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ANOTACION_PERSONA_ROL);

				lps_ps.setString(li_contador++, aapc_param.getIdAnotacionPredio());
				lps_ps.setLong(li_contador++, aapc_param.getIdAnotacion());
				lps_ps.setString(li_contador++, aapc_param.getIdPersona());
				lps_ps.setString(li_contador++, aapc_param.getRolPersona());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lapc_predio = getAnotacinoPredioCiudadano(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdAnotacionPersonaRol", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		return lapc_predio;
	}

	/**
	 * Método para encontrar registros por un filtro en especifico en la BD.
	 *
	 * @param aapc_idPredioCiudadano correspondiente al valor del tipo de objeto AnotacionPredioCiudadano
	 * @return Unsolo registro de AnotacionPredioCiudadano
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredioCiudadano
	 */
	public AnotacionPredioCiudadano findOneByIdAnotacion(AnotacionPredioCiudadano aapc_idPredioCiudadano)
	    throws B2BException
	{
		AnotacionPredioCiudadano lcapc_datos;

		lcapc_datos = new AnotacionPredioCiudadano();

		if(aapc_idPredioCiudadano != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ANOTACION);

				lps_ps.setString(li_contador++, aapc_idPredioCiudadano.getIdAnotacionPredio());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcapc_datos = getAnotacinoPredioCiudadano(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findOneByIdAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcapc_datos;
	}

	/**
	 * Método para consultar las personas por idTurno
	 *
	 * @param as_idTurno String contenedor del filtro de búsqueda
	 * @return Colección de AnotacionPredioCiudadano con resultados de la consulta
	 * @throws B2BException
	 */
	public Collection<Persona> findPersonasByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<Persona> lcapc_datos;
		lcapc_datos = new ArrayList<Persona>();

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_PERSONA_BY_ID_TURNO);

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					Persona lp_p;

					lp_p = new Persona();

					lp_p.setIdPersona(lrs_rs.getString("ID_PERSONA"));

					lcapc_datos.add(lp_p);
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcapc_datos))
			lcapc_datos = null;

		return lcapc_datos;
	}

	/**
	 * Método para poder insertar registros en la BD.
	 *
	 * @param aapc_parametros objeto AnotacionPredioCiudadano con información para insertar en la BD
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(AnotacionPredioCiudadano aapc_parametros)
	    throws B2BException
	{
		if(aapc_parametros != null)
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
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					String ls_secuencia;

					ls_secuencia = StringUtils.getString(lrs_rs.getInt(1));

					aapc_parametros.setIdAnotacionPredioCiudadano(ls_secuencia);
					lps_ps.setString(li_column++, ls_secuencia);
				}

				lps_ps.setString(li_column++, aapc_parametros.getIdAnotacionPredio());
				lps_ps.setString(li_column++, aapc_parametros.getIdCirculo());
				lps_ps.setLong(li_column++, aapc_parametros.getIdMatricula());
				lps_ps.setLong(li_column++, aapc_parametros.getIdAnotacion());
				lps_ps.setString(li_column++, aapc_parametros.getIdPersona());
				lps_ps.setString(
				    li_column++,
				    StringUtils.isValidString(aapc_parametros.getRolPersona()) ? aapc_parametros.getRolPersona().trim()
				                                                               : null
				);
				lps_ps.setString(li_column++, aapc_parametros.getPropietario());
				lps_ps.setString(li_column++, aapc_parametros.getPorcentajeParticipacion());
				lps_ps.setString(li_column++, aapc_parametros.getIdInteresadaRrr());
				lps_ps.setString(li_column++, aapc_parametros.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aapc_parametros.getIpCreacion());
				lps_ps.setString(li_column++, aapc_parametros.getEstado());
				lps_ps.setString(li_column++, aapc_parametros.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				getLog().error("insert", lse_e);

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
	 * Método para modificar el id de los registros AnotacionPredioCiudadano al eliminar una anotación.
	 *
	 * @param aapc_parametros objeto AnotacionPredioCiudadano
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void modificarAnotacionCiudadanoIds(AnotacionPredioCiudadano aapc_parametros)
	    throws B2BException
	{
		if(aapc_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;
				lps_ps            = lc_connection.prepareStatement(cs_UPDATE_ANOTACION_ID);

				lps_ps.setLong(li_column++, aapc_parametros.getIdAnotacion());
				lps_ps.setString(li_column++, aapc_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aapc_parametros.getIpModificacion());

				lps_ps.setString(li_column++, aapc_parametros.getIdAnotacionPredio());
				lps_ps.setString(li_column++, aapc_parametros.getIdCirculo());
				lps_ps.setLong(li_column++, aapc_parametros.getIdMatricula());
				lps_ps.setString(li_column++, aapc_parametros.getIdPersona());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				getLog().error("modificarCiudadano", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para poder realizar una actaualización en un registro de la BD.
	 *
	 * @param aapc_parametros objeto AnotacionPredioCiudadano
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void modificarCiudadano(AnotacionPredioCiudadano aapc_parametros)
	    throws B2BException
	{
		if(aapc_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;
				lps_ps            = lc_connection.prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, aapc_parametros.getRolPersona());
				lps_ps.setString(li_column++, aapc_parametros.getPropietario());
				lps_ps.setString(li_column++, aapc_parametros.getPorcentajeParticipacion());
				lps_ps.setString(li_column++, aapc_parametros.getIdInteresadaRrr());
				lps_ps.setString(li_column++, aapc_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aapc_parametros.getIpModificacion());

				lps_ps.setString(li_column++, aapc_parametros.getIdAnotacionPredio());
				lps_ps.setString(li_column++, aapc_parametros.getIdCirculo());
				lps_ps.setLong(li_column++, aapc_parametros.getIdMatricula());
				lps_ps.setLong(li_column++, aapc_parametros.getIdAnotacion());
				lps_ps.setString(li_column++, aapc_parametros.getIdPersona());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				getLog().error("modificarCiudadano", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para poder modificar un registro en la BD.
	 *
	 * @param as_idAnotacion String para poder actualizar en la BD
	 * @param as_idUsuario String para poder actualizar en la BD
	 * @param as_ipRemota String para poder actualizar en la BD
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void modificarInteresadaRR(String as_idAnotacion, String as_idUsuario, String as_ipRemota)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		try
		{
			Connection lc_connection;
			int        li_column;

			lc_connection     = getConnection();
			li_column         = 1;

			lps_ps = lc_connection.prepareStatement(cs_UPDATE_INTERESADA_RRR);

			lps_ps.setString(li_column++, as_idUsuario);
			lps_ps.setString(li_column++, as_ipRemota);
			lps_ps.setString(li_column++, as_idAnotacion);

			lps_ps.executeUpdate();
		}
		catch(SQLException lse_e)
		{
			getLog().error("modificarInteresadaRR", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
		}
	}

	/**
	 * Método para extraer información de la cunsulta a la BD, ponerlos en un objeto y retornarlo.
	 *
	 * @param ars_rs objeto con la consulta de la BD para extraer la información necesaria
	 * @return objeto AnotacionPredioCiudadano
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AnotacionPredioCiudadano getAnotacinoPredioCiudadano(ResultSet ars_rs)
	    throws SQLException
	{
		AnotacionPredioCiudadano lapc_apc;

		lapc_apc = new AnotacionPredioCiudadano();

		lapc_apc.setIdAnotacionPredioCiudadano(ars_rs.getString("ID_ANOTACION_PREDIO_CIUDADANO"));
		lapc_apc.setIdAnotacionPredio(ars_rs.getString("ID_ANOTACION_PREDIO"));
		lapc_apc.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lapc_apc.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lapc_apc.setIdAnotacion(ars_rs.getLong("ID_ANOTACION"));
		lapc_apc.setIdPersona(ars_rs.getString("ID_PERSONA"));
		lapc_apc.setRolPersona(ars_rs.getString("ROL_PERSONA"));
		lapc_apc.setPropietario(ars_rs.getString("PROPIETARIO"));
		lapc_apc.setPorcentajeParticipacion(ars_rs.getString("PORCENTAJE_PARTICIPACION"));
		lapc_apc.setIdInteresadaRrr(ars_rs.getString("ID_PARTE_INTERESADA"));
		lapc_apc.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lapc_apc.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lapc_apc.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lapc_apc.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lapc_apc.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lapc_apc.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lapc_apc.setEstado(ars_rs.getString("ESTADO"));
		lapc_apc.setIdTurno(ars_rs.getString("ID_TURNO"));

		return lapc_apc;
	}

	/**
	 * Método para extraer información de la cunsulta a la BD, ponerlos en un objeto y retornarlo.
	 *
	 * @param ars_rs objeto con la consulta de la BD para extraer la información necesaria
	 * @return objeto AnotacionPredioCiudadano
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AnotacionPredioCiudadano getDataIntervinientes(ResultSet ars_rs)
	    throws SQLException
	{
		AnotacionPredioCiudadano lapc_apc;

		lapc_apc = new AnotacionPredioCiudadano();

		lapc_apc.setRolPersona(ars_rs.getString("ROL_PERSONA"));
		lapc_apc.setTipoDocumento(ars_rs.getString("ID_DOCUMENTO_TIPO"));
		lapc_apc.setDocumento(ars_rs.getString("NUMERO_DOCUMENTO"));
		lapc_apc.setIdPersona(ars_rs.getString("ID_PERSONA"));
		lapc_apc.setNombrePersona(ars_rs.getString("NOMBRE_PERSONA"));

		return lapc_apc;
	}

	/**
	 * Método para extraer información de la cunsulta a la BD, ponerlos en un objeto y retornarlo.
	 *
	 * @param ars_rs objeto con la consulta de la BD para extraer la información necesaria
	 * @return objeto AnotacionPredioCiudadano
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AnotacionPredioCiudadano getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AnotacionPredioCiudadano lapc_apc;

		lapc_apc = new AnotacionPredioCiudadano();

		lapc_apc.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lapc_apc.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lapc_apc.setIdAnotacion(ars_rs.getLong("ID_ANOTACION"));
		lapc_apc.setIdPersona(ars_rs.getString("ID_PERSONA"));
		lapc_apc.setRolPersona(ars_rs.getString("ROL_PERSONA"));
		lapc_apc.setPropietario(ars_rs.getString("PROPIETARIO"));
		lapc_apc.setPorcentajeParticipacion(ars_rs.getString("PORCENTAJE_PARTICIPACION"));
		lapc_apc.setIdInteresadaRrr(ars_rs.getString("ID_PARTE_INTERESADA"));

		return lapc_apc;
	}
}
