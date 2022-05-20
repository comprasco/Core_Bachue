package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.AccConsultaCriterioAntiguoSistema;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase para poder realizar transacciones con la base de datos en la tabla SDB_ACC_CONSULTA_CRITERIO_ANT_SISTEMA.
 *
 * @author Julian Vaca
 */
public class AccConsultaCriteriosAntSistemaDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_CRITERIO_ANT_SISTEMA,ID_TURNO_HISTORIA,ID_DOCUMENTO,ID_DATOS_ANT_SISTEMA,"
		+ "ID_ANOTACION,ID_CIRCULO,ID_MATRICULA,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,"
		+ "IP_MODIFICACION FROM SDB_ACC_CONSULTA_CRITERIO_ANT_SISTEMA WHERE ID_CRITERIO_ANT_SISTEMA = ?";

	/** Constante cs_FIND_BY_ID_TURNO_HISTORIA. */
	private static final String cs_FIND_BY_ID_TURNO_HISTORIA = "SELECT ID_CRITERIO_ANT_SISTEMA,ID_TURNO_HISTORIA,ID_DOCUMENTO,ID_DATOS_ANT_SISTEMA,"
		+ "ID_ANOTACION,ID_CIRCULO,ID_MATRICULA,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,"
		+ "IP_MODIFICACION FROM SDB_ACC_CONSULTA_CRITERIO_ANT_SISTEMA WHERE ID_TURNO_HISTORIA = ?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_CONSULTA_CRITERIO_ANT_SISTEMA_ID_CRITERIO_ANT_SISTEMA.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_CONSULTA_CRITERIO_ANT_SISTEMA (ID_CRITERIO_ANT_SISTEMA,ID_TURNO_HISTORIA,"
		+ "ID_DOCUMENTO,ID_DATOS_ANT_SISTEMA,ID_ANOTACION,ID_CIRCULO,ID_MATRICULA,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES(?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_CONSULTA_CRITERIO_ANT_SISTEMA SET ID_DATOS_ANT_SISTEMA = ?, ID_USUARIO_MODIFICACION = ?, "
		+ "FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_CRITERIO_ANT_SISTEMA = ? AND ID_TURNO_HISTORIA = ?";

	/**
	 * Método para filtrar un registro en la BD por el id.
	 *
	 * @param accas_param AccConsultaCriterioAntiguoSistema
	 * @return objeto AccConsultaCriterioAntiguoSistema
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccConsultaCriterioAntiguoSistema
	 */
	public AccConsultaCriterioAntiguoSistema findById(AccConsultaCriterioAntiguoSistema accas_param)
	    throws B2BException
	{
		AccConsultaCriterioAntiguoSistema laccas_consultaCriterioAntSistema;
		PreparedStatement                 lps_ps;
		ResultSet                         lrs_rs;

		laccas_consultaCriterioAntSistema     = null;
		lps_ps                                = null;
		lrs_rs                                = null;

		if(accas_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, accas_param.getIdCriterioAntSistema());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					laccas_consultaCriterioAntSistema = getObjetFromResultSet(lrs_rs);
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

		return laccas_consultaCriterioAntSistema;
	}

	/**
	 * Método para filtrar en la BD por el IdTurnoHistoria.
	 *
	 * @param accas_param objeto AccConsultaCriterioAntiguoSistema para obtener los datos para filtrar en la BD
	 * @return objeto AccConsultaCriterioAntiguoSistema
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccConsultaCriterioAntiguoSistema
	 */
	public AccConsultaCriterioAntiguoSistema findByIdTurnoHistoria(AccConsultaCriterioAntiguoSistema accas_param)
	    throws B2BException
	{
		AccConsultaCriterioAntiguoSistema laccas_consultaCriterioAntSistema;
		laccas_consultaCriterioAntSistema = null;

		if(accas_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_HISTORIA);

				setLong(lps_ps, accas_param.getIdTurnoHistoria(), 1);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					laccas_consultaCriterioAntSistema = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoHistoria", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return laccas_consultaCriterioAntSistema;
	}

	/**
	 * Método para encontrar la siguiente secuencia de la BD.
	 *
	 * @return int
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
	 * Método para insertar un registro en la BD.
	 *
	 * @param aaccas_param objeto AccConsultaCriterioAntiguoSistema  para obtener los datos a insertar en el registro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(AccConsultaCriterioAntiguoSistema aaccas_param)
	    throws B2BException
	{
		if(aaccas_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;
				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, aaccas_param.getIdCriterioAntSistema());
				setLong(lps_ps, aaccas_param.getIdTurnoHistoria(), li_column++);
				lps_ps.setString(li_column++, aaccas_param.getIdDocumento());
				lps_ps.setString(li_column++, aaccas_param.getIdDatosAntSistema());
				setLong(lps_ps, aaccas_param.getIdAnotacion(), li_column++);
				lps_ps.setString(li_column++, aaccas_param.getIdCirculo());
				setLong(lps_ps, aaccas_param.getIdMatricula(), li_column++);
				lps_ps.setString(li_column++, aaccas_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aaccas_param.getIpCreacion());

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
	 * Método para actualizar un registro en la BD.
	 *
	 * @param aaccas_param objeto AccConsultaCriterioAntiguoSistema para obtener los datos para actualizar el registro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void update(AccConsultaCriterioAntiguoSistema aaccas_param)
	    throws B2BException
	{
		if(aaccas_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;
				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, aaccas_param.getIdDatosAntSistema());
				lps_ps.setString(li_column++, aaccas_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aaccas_param.getIpModificacion());

				lps_ps.setString(li_column++, aaccas_param.getIdCriterioAntSistema());
				setLong(lps_ps, aaccas_param.getIdTurnoHistoria(), li_column++);

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
	 * Método para extraer los datos de la consulta de la BD, ponerlos en un objeto AccConsultaCriterioAntiguoSistema y retornarlo.
	 *
	 * @param ars_rs objeto ResultSet con lo que retorna la consulta a la BD
	 * @return objeto AccConsultaCriterioAntiguoSistema
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AccConsultaCriterioAntiguoSistema getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AccConsultaCriterioAntiguoSistema laccas_consultaCriteriosAntSistema;

		laccas_consultaCriteriosAntSistema = new AccConsultaCriterioAntiguoSistema();

		laccas_consultaCriteriosAntSistema.setIdCriterioAntSistema(ars_rs.getString("ID_CRITERIO_ANT_SISTEMA"));
		laccas_consultaCriteriosAntSistema.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));
		laccas_consultaCriteriosAntSistema.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		laccas_consultaCriteriosAntSistema.setIdDatosAntSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		laccas_consultaCriteriosAntSistema.setIdAnotacion(getLong(ars_rs, "ID_ANOTACION"));
		laccas_consultaCriteriosAntSistema.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		laccas_consultaCriteriosAntSistema.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		laccas_consultaCriteriosAntSistema.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		laccas_consultaCriteriosAntSistema.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		laccas_consultaCriteriosAntSistema.setIpCreacion(ars_rs.getString("IP_CREACION"));
		laccas_consultaCriteriosAntSistema.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		laccas_consultaCriteriosAntSistema.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		laccas_consultaCriteriosAntSistema.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return laccas_consultaCriteriosAntSistema;
	}
}
