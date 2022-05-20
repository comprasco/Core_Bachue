package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.acc.SoporteTraslado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para el manejo de datos para la tabla SDB_ACC_SOPORTE_TRASLADO.
 *
 * @author Jorge Patiño
 */
public class SoporteTrasladoDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_SOPORTE_TRASLADO WHERE ID_SOPORTE_TRASLADO = ?";

	/** Constante cs_FIND_BY_TURNO. */
	private static final String cs_FIND_BY_TURNO = "SELECT * FROM SDB_ACC_SOPORTE_TRASLADO WHERE ID_TURNO = ?";

	/** Constante cs_FIND_BY_TURNO_HISTORIA. */
	private static final String cs_FIND_BY_TURNO_HISTORIA = "SELECT * FROM SDB_ACC_SOPORTE_TRASLADO WHERE ID_TURNO_HISTORIA = ?";

	/** Constante cs_FIND_BY_SOLICITUD. */
	private static final String cs_FIND_BY_SOLICITUD = "SELECT * FROM SDB_ACC_SOPORTE_TRASLADO WHERE ID_SOLICITUD = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SOPORTE_TRASLADO (ID_SOPORTE_TRASLADO,ID_TURNO,ID_SOLICITUD,ID_TIPO_DOCUMENTO,FECHA_DOCUMENTO,NUMERO,ID_OFICINA_ORIGEN,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_TURNO_HISTORIA,VERSION) VALUES (?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?)";

	/** Constante cs_DELETE_BY_SOLICITUD. */
	private static final String cs_DELETE_BY_SOLICITUD = "DELETE FROM SDB_ACC_SOPORTE_TRASLADO WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_SOPORTE_TRASLADO_ID_SOPORTE_TRASLADO.NEXTVAL FROM DUAL";

	/**
	 * Método encargado de eliminar los registros para la solicitud.
	 *
	 * @param as_idSolicitud Contiene el id de la solicitud.
	 * @throws B2BException Señala que se ha producido una excepción.
	 */
	public void deleteByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE_BY_SOLICITUD);

				lps_ps.setString(li_column++, as_idSolicitud);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método encargado de consultar por id.
	 *
	 * @param as_idSolicitud Contiene la información del id soporte traslado.
	 * @return SoporteTraslado de objeto consultado.
	 * @throws B2BException Señala que se ha producido una excepción.
	 */
	public SoporteTraslado findById(String as_idSoporteTraslado)
	    throws B2BException
	{
		SoporteTraslado   lst_soporteTraslado;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lst_soporteTraslado     = null;
		lps_ps                  = null;
		lrs_rs                  = null;

		try
		{
			if(StringUtils.isValidString(as_idSoporteTraslado))
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_column++, as_idSoporteTraslado);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lst_soporteTraslado = getObjetFromResultSet(lrs_rs);
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

		return lst_soporteTraslado;
	}

	/**
	 * Método encargado de consultar por id solicitud.
	 *
	 * @param as_idSolicitud Contiene la información de la solicitud.
	 * @return Colección de objetos consultados.
	 * @throws B2BException Señala que se ha producido una excepción.
	 */
	public Collection<SoporteTraslado> findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<SoporteTraslado> lcsp_return;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcsp_return     = new ArrayList<SoporteTraslado>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD);

				lps_ps.setString(li_column++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsp_return.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdSolicitud", lse_e);

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
	 * Método encargado de consultar por id turno.
	 *
	 * @param as_idTurno Contiene la información del turno.
	 * @return Colección de objetos consultados.
	 * @throws B2BException Señala que se ha producido una excepción.
	 */
	public Collection<SoporteTraslado> findByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<SoporteTraslado> lcst_return;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcst_return     = new ArrayList<SoporteTraslado>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_TURNO);

				lps_ps.setString(li_column++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcst_return.add(getObjetFromResultSet(lrs_rs));
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

		if(lcst_return.isEmpty())
			lcst_return = null;

		return lcst_return;
	}

	/**
	 * Método encargado de consultar por id turno HISTORIA.
	 *
	 * @param al_idTurnoHistoria de al id turno historia
	 * @return el valor de soporte traslado
	 * @throws B2BException Señala que se ha producido una excepción.
	 */
	public SoporteTraslado findByIdTurnoHistoria(long al_idTurnoHistoria)
	    throws B2BException
	{
		SoporteTraslado   lst_traslado;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lst_traslado     = null;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			if(NumericUtils.isValidLong(NumericUtils.getLongWrapper(al_idTurnoHistoria)))
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_TURNO_HISTORIA);

				lps_ps.setLong(li_column++, al_idTurnoHistoria);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lst_traslado = getObjetFromResultSet(lrs_rs);
			}
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

		return lst_traslado;
	}

	/**
	 * Método encargado de insertar o actualizar un registro para traslado de matriculas.
	 *
	 * @param ast_param Objeto que contiene la información a insertar o actualizar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(SoporteTraslado ast_param)
	    throws B2BException
	{
		if(ast_param != null)
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
				lps_ps            = lc_C.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_C.prepareStatement(cs_FIND_SECUENCIA);

				{
					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
						lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
					else
						throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);
				}

				lps_ps.setString(li_column++, ast_param.getIdTurno());
				lps_ps.setString(li_column++, ast_param.getIdSolicitud());
				lps_ps.setString(li_column++, ast_param.getIdTipoDocumento());
				setDate(lps_ps, ast_param.getFechaDocumento(), li_column++);
				setLong(lps_ps, NumericUtils.getLongWrapper(ast_param.getNumero()), li_column++);
				lps_ps.setString(li_column++, ast_param.getIdOficinaOrigen());
				lps_ps.setString(li_column++, ast_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, ast_param.getIpCreacion());
				setLong(lps_ps, ast_param.getIdTurnoHistoria(), li_column++);
				setLong(lps_ps, ast_param.getIdVersion(), li_column++);

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
	 * Método que construye el modelo de la tabla consultada.
	 *
	 * @param ars_rs ResultSet que contiene el resultado de la búsqueda.
	 * @return Objeto que contiene la información consultada.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SoporteTraslado getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		SoporteTraslado ast_return;

		ast_return = new SoporteTraslado();

		ast_return.setIdSoporteTraslado(ars_rs.getString("ID_SOPORTE_TRASLADO"));
		ast_return.setIdTurno(ars_rs.getString("ID_TURNO"));
		ast_return.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		ast_return.setIdTipoDocumento(ars_rs.getString("ID_TIPO_DOCUMENTO"));
		ast_return.setFechaDocumento(ars_rs.getTimestamp("FECHA_DOCUMENTO"));
		ast_return.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));
		ast_return.setIdVersion(getLong(ars_rs, "VERSION"));

		{
			Long ll_numero;

			ll_numero = getLong(ars_rs, "NUMERO");

			if(NumericUtils.isValidLong(ll_numero))
				ast_return.setNumero(StringUtils.getString(ll_numero));
		}

		ast_return.setIdOficinaOrigen(ars_rs.getString("ID_OFICINA_ORIGEN"));
		ast_return.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ast_return.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ast_return.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ast_return.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ast_return.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ast_return.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return ast_return;
	}
}
