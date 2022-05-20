package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.acc.AccSalvedadPredio;

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
public class AccSalvedadPredioDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID_CAUSAL. */
	private static final String cs_FIND_BY_ID_CAUSAL = "SELECT * FROM SDB_ACC_SALVEDAD_PREDIO WHERE ID_TURNO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_CAUSAL_CORRECCION = ?";

	/** Constante cs_FIND. */
	private static final String cs_FIND = "SELECT * FROM SDB_ACC_SALVEDAD_PREDIO WHERE ID_TURNO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SALVEDAD_PREDIO SET DESCRIPCION = ?, ID_USUARIO_MODIFICACION = ?,FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TURNO_HISTORIA = ? AND ID_TURNO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_CAUSAL_CORRECCION = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SALVEDAD_PREDIO(ID_SALVEDAD_PREDIO,ID_TURNO_HISTORIA,ID_TURNO,ID_CIRCULO,ID_MATRICULA,ID_CAUSAL_CORRECCION,RADICACION,FECHA_REGISTRO,DESCRIPCION,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION)VALUES(?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_SDB_ACC_SALVEDAD_PREDIO_ID_SALVEDAD_PREDIO.NEXTVAL FROM DUAL";

	/**
	 * Método encargado de consultar con base a los datos del proceso y predio.
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto Long
	 * @return Mapa que contiene la información consultada con el campo id causal como llave.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AccSalvedadPredio> findAll(String as_idTurno, String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		Collection<AccSalvedadPredio> lcsp_return;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcsp_return     = new ArrayList<AccSalvedadPredio>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_idCirculo)
				    && NumericUtils.isValidLong(al_idMatricula)
			)
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND);

				lps_ps.setString(li_column++, as_idTurno);
				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsp_return.add(getObjetFromResultSet(lrs_rs));
			}
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

		if(lcsp_return.isEmpty())
			lcsp_return = null;

		return lcsp_return;
	}

	/**
	 * Busca un registro asociado a un id específico.
	 *
	 * @param aasp_param Objeto contenedor de los parametros a utilizar como filtro en la busqueda
	 * @return Salvedad predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccSalvedadPredio
	 */
	public AccSalvedadPredio findById(AccSalvedadPredio aasp_param)
	    throws B2BException
	{
		return (aasp_param != null)
		? findById(
		    aasp_param.getIdTurno(), aasp_param.getIdCirculo(), aasp_param.getIdMatricula(), aasp_param.getIdCausal()
		) : null;
	}

	/**
	 * Busca un registro asociado a un id específico.
	 *
	 * @param as_idTurno id del turno actual asociado al registro
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id del a matrícula a utilizar como filtro en la busqueda
	 * @param al_idCausal id del causal de la salvedad
	 * @return Salvedad predio resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccSalvedadPredio
	 */
	public AccSalvedadPredio findById(String as_idTurno, String as_idCirculo, Long al_idMatricula, Long al_idCausal)
	    throws B2BException
	{
		AccSalvedadPredio lasp_return;

		lasp_return = null;

		if(
		    StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_idCirculo)
			    && NumericUtils.isValidLong(al_idMatricula) && NumericUtils.isValidLong(al_idCausal)
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
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_CAUSAL);

				lps_ps.setString(li_column++, as_idTurno);
				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);
				setLong(lps_ps, al_idCausal, li_column++);

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
	 * Método encargado de consultar con base a los datos del proceso y predio.
	 *
	 * @param asp_param Objeto que contiene los argumentos para realizar la busqueda.
	 * @return Mapa que contiene la información consultada con el campo id causal como llave.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Map
	 */
	public Map<Long, AccSalvedadPredio> findCausalById(AccSalvedadPredio asp_param)
	    throws B2BException
	{
		Map<Long, AccSalvedadPredio> lmlsp_return;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lmlsp_return     = new HashMap<Long, AccSalvedadPredio>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			if(asp_param != null)
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND);

				lps_ps.setString(li_column++, asp_param.getIdTurno());
				lps_ps.setString(li_column++, asp_param.getIdCirculo());
				setLong(lps_ps, asp_param.getIdMatricula(), li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					AccSalvedadPredio lsp_salvedad;

					lsp_salvedad = getObjetFromResultSet(lrs_rs);

					if(lsp_salvedad != null)
						lmlsp_return.put(lsp_salvedad.getIdCausal(), lsp_salvedad);
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

		if(lmlsp_return.isEmpty())
			lmlsp_return = null;

		return lmlsp_return;
	}

	/**
	 * Método encargado de insertar o actualizar un registro para salvedades.
	 *
	 * @param asp_param Objeto que contiene la información a insertar o actualizar.
	 * @param ab_query Variable de tipo boolean que valida si se debe insertar o actualizar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(AccSalvedadPredio asp_param, boolean ab_query)
	    throws B2BException
	{
		if(asp_param != null)
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
					setLong(lps_ps, asp_param.getIdTurnoHistoria(), li_column++);
					lps_ps.setString(li_column++, asp_param.getIdTurno());
					lps_ps.setString(li_column++, asp_param.getIdCirculo());
					setLong(lps_ps, asp_param.getIdMatricula(), li_column++);
					setLong(lps_ps, asp_param.getIdCausal(), li_column++);
					lps_ps.setString(li_column++, asp_param.getRadicacion());
				}

				lps_ps.setString(li_column++, asp_param.getDescripcion());

				if(ab_query)
				{
					lps_ps.setString(li_column++, asp_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, asp_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, asp_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, asp_param.getIpModificacion());
					setLong(lps_ps, asp_param.getIdTurnoHistoria(), li_column++);
					lps_ps.setString(li_column++, asp_param.getIdTurno());
					lps_ps.setString(li_column++, asp_param.getIdCirculo());
					setLong(lps_ps, asp_param.getIdMatricula(), li_column++);
					setLong(lps_ps, asp_param.getIdCausal(), li_column++);
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
	private AccSalvedadPredio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AccSalvedadPredio lsp_salvedad;

		lsp_salvedad = new AccSalvedadPredio();

		lsp_salvedad.setIdSalvedadPredio(ars_rs.getString("ID_SALVEDAD_PREDIO"));
		lsp_salvedad.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));
		lsp_salvedad.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lsp_salvedad.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		lsp_salvedad.setIdCausal(getLong(ars_rs, "ID_CAUSAL_CORRECCION"));
		lsp_salvedad.setFechaRegistro(ars_rs.getTimestamp("FECHA_REGISTRO"));
		lsp_salvedad.setRadicacion(ars_rs.getString("RADICACION"));
		lsp_salvedad.setIdTurno(ars_rs.getString("ID_TURNO"));
		lsp_salvedad.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lsp_salvedad.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lsp_salvedad.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lsp_salvedad.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lsp_salvedad.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lsp_salvedad.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lsp_salvedad.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lsp_salvedad;
	}
}
