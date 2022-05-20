package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.acc.AccSalvedadAnotacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase para el manejo de datos para la tabla SDB_PGN_ALERTA_TRAMITE.
 *
 * @author Jorge Patiño
 */
public class AccSalvedadAnotacionDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID_ANOTACION. */
	private static final String cs_FIND_BY_ID_ANOTACION = "SELECT * FROM SDB_ACC_SALVEDAD_ANOTACION WHERE ID_TURNO_HISTORIA = ? AND ID_TURNO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ?";

	/** Constante cs_FIND. */
	private static final String cs_FIND = "SELECT * FROM SDB_ACC_SALVEDAD_ANOTACION WHERE ID_TURNO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_SALVEDAD_ANOTACION WHERE ID_TURNO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SALVEDAD_ANOTACION SET DESCRIPCION = ?, ID_USUARIO_MODIFICACION = ?,FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TURNO_HISTORIA = ? AND ID_TURNO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SALVEDAD_ANOTACION(ID_SALVEDAD_ANOTACION,ID_TURNO_HISTORIA,ID_TURNO,ID_CIRCULO,ID_MATRICULA,ID_ANOTACION,RADICACION,FECHA_REGISTRO,DESCRIPCION,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION)VALUES(?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_SALVEDAD_ANOTACION_ID_SALVEDAD_ANOTACION.NEXTVAL FROM DUAL";

	/**
	 * Método encargado de consultar con base a los datos del proceso y predio.
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto Long
	 * @return Colección que contiene la información consultada con el campo id causal como llave.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Map
	 */
	public Collection<AccSalvedadAnotacion> findAllAnotacioneslById(
	    String as_idTurno, String as_idCirculo, Long al_idMatricula
	)
	    throws B2BException
	{
		Collection<AccSalvedadAnotacion> lcasa_return;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;

		lcasa_return     = new ArrayList<AccSalvedadAnotacion>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_idCirculo)
				    && NumericUtils.isValidLong(al_idMatricula)
			)
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_column++, as_idTurno);
				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcasa_return.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllAnotacioneslById", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcasa_return.isEmpty())
			lcasa_return = null;

		return lcasa_return;
	}

	/**
	 * Método encargado de consultar con base a los datos del proceso y predio.
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto Long
	 * @return Mapa que contiene la información consultada con el campo id causal como llave.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Map
	 */
	public Map<Long, AccSalvedadAnotacion> findAnotacionlById(
	    String as_idTurno, String as_idCirculo, Long al_idMatricula
	)
	    throws B2BException
	{
		Map<Long, AccSalvedadAnotacion> lmlsa_return;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lmlsa_return     = new HashMap<Long, AccSalvedadAnotacion>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_idCirculo)
				    && NumericUtils.isValidLong(al_idMatricula)
			)
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_column++, as_idTurno);
				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					AccSalvedadAnotacion lsa_salvedad;

					lsa_salvedad = getObjetFromResultSet(lrs_rs);

					if(lsa_salvedad != null)
					{
						Long ll_idAnotacion;

						ll_idAnotacion = lsa_salvedad.getIdAnotacion();

						if(NumericUtils.isValidLong(ll_idAnotacion))
							lmlsa_return.put(ll_idAnotacion, lsa_salvedad);
					}
				}
			}
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

		if(lmlsa_return.isEmpty())
			lmlsa_return = null;

		return lmlsa_return;
	}

	/**
	 * Busca un registro asociado a un id específico.
	 *
	 * @param aasp_param Objeto contenedor de los parametros a utilizar como filtro en la busqueda
	 * @return Salvedad predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccSalvedadAnotacion
	 */
	public AccSalvedadAnotacion findById(AccSalvedadAnotacion aasp_param)
	    throws B2BException
	{
		return (aasp_param != null)
		? findById(
		    aasp_param.getIdTurnoHistoria(), aasp_param.getIdTurno(), aasp_param.getIdCirculo(),
		    aasp_param.getIdMatricula(), aasp_param.getIdAnotacion()
		) : null;
	}

	/**
	 * Busca un registro asociado a un id específico.
	 *
	 * @param al_idTurnoHistoria id del turno historia asociado al registro
	 * @param as_idTurno id del turno actual asociado al registro
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id del a matrícula a utilizar como filtro en la busqueda
	 * @param al_idAnotacion id del causal de la salvedad
	 * @return Salvedad predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccSalvedadAnotacion
	 */
	public AccSalvedadAnotacion findById(
	    Long al_idTurnoHistoria, String as_idTurno, String as_idCirculo, Long al_idMatricula, Long al_idAnotacion
	)
	    throws B2BException
	{
		AccSalvedadAnotacion lasp_return;

		lasp_return = null;

		if(
		    NumericUtils.isValidLong(al_idTurnoHistoria) && StringUtils.isValidString(as_idTurno)
			    && StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula)
			    && NumericUtils.isValidLong(al_idAnotacion)
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
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_ANOTACION);

				setLong(lps_ps, al_idTurnoHistoria, li_column++);
				lps_ps.setString(li_column++, as_idTurno);
				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);
				setLong(lps_ps, al_idAnotacion, li_column++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lasp_return = getObjetFromResultSet(lrs_rs);
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

		return lasp_return;
	}

	/**
	 * Busca un registro asociado a un id específico.
	 *
	 * @param as_idTurno id del turno actual asociado al registro
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id del a matrícula a utilizar como filtro en la busqueda
	 * @param al_idAnotacion id del causal de la salvedad
	 * @return Salvedad predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccSalvedadAnotacion
	 */
	public AccSalvedadAnotacion findById(
	    String as_idTurno, String as_idCirculo, Long al_idMatricula, Long al_idAnotacion
	)
	    throws B2BException
	{
		AccSalvedadAnotacion lasp_return;

		lasp_return = null;

		if(
		    StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_idCirculo)
			    && NumericUtils.isValidLong(al_idMatricula) && NumericUtils.isValidLong(al_idAnotacion)
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
				lps_ps        = getConnection().prepareStatement(cs_FIND);

				lps_ps.setString(li_column++, as_idTurno);
				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);
				setLong(lps_ps, al_idAnotacion, li_column++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lasp_return = getObjetFromResultSet(lrs_rs);
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

		return lasp_return;
	}

	/**
	 * Método encargado de insertar o actualizar un registro para salvedades.
	 *
	 * @param asa_param Objeto que contiene la información a insertar o actualizar.
	 * @param ab_query Variable de tipo boolean que valida si se debe insertar o actualizar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(AccSalvedadAnotacion asa_param, boolean ab_query)
	    throws B2BException
	{
		if(asa_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				Connection lc_C;
				int        li_column;

				li_column         = 1;
				lc_C              = getConnection();
				lps_ps            = lc_C.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);
				lps_secuencia     = lc_C.prepareStatement(cs_FIND_SECUENCIA);

				if(ab_query)
				{
					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
						lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
					else
						throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);
				}

				if(ab_query)
				{
					setLong(lps_ps, asa_param.getIdTurnoHistoria(), li_column++);
					lps_ps.setString(li_column++, asa_param.getIdTurno());
					lps_ps.setString(li_column++, asa_param.getIdCirculo());
					setLong(lps_ps, asa_param.getIdMatricula(), li_column++);
					setLong(lps_ps, asa_param.getIdAnotacion(), li_column++);
					lps_ps.setString(li_column++, asa_param.getRadicacion());
				}

				lps_ps.setString(li_column++, asa_param.getDescripcion());

				if(ab_query)
				{
					lps_ps.setString(li_column++, asa_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, asa_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, asa_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, asa_param.getIpModificacion());
					setLong(lps_ps, asa_param.getIdTurnoHistoria(), li_column++);
					lps_ps.setString(li_column++, asa_param.getIdTurno());
					lps_ps.setString(li_column++, asa_param.getIdCirculo());
					setLong(lps_ps, asa_param.getIdMatricula(), li_column++);
					setLong(lps_ps, asa_param.getIdAnotacion(), li_column++);
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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Método que construye el modelo de la tabla consultada.
	 *
	 * @param ars_rs ResultSet que contiene el resultado de la búsqueda.
	 * @return Objeto que contiene la información consultada.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AccSalvedadAnotacion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AccSalvedadAnotacion lsa_salvedad;

		lsa_salvedad = new AccSalvedadAnotacion();

		lsa_salvedad.setIdSalvedadAnotacion(ars_rs.getString("ID_SALVEDAD_ANOTACION"));
		lsa_salvedad.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));
		lsa_salvedad.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lsa_salvedad.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		lsa_salvedad.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		lsa_salvedad.setFechaRegistro(ars_rs.getTimestamp("FECHA_REGISTRO"));
		lsa_salvedad.setRadicacion(ars_rs.getString("RADICACION"));
		lsa_salvedad.setIdTurno(ars_rs.getString("ID_TURNO"));
		lsa_salvedad.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lsa_salvedad.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lsa_salvedad.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lsa_salvedad.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lsa_salvedad.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lsa_salvedad.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lsa_salvedad.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lsa_salvedad;
	}
}
