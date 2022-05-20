package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.PersonaPresentada;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_PERSONA_PRESENTADA
 *
 * @author hcastaneda
 */
public class PersonaPresentadaDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_PERSONA_PRESENTADA WHERE ID_PERSONA_REPRESENTADA=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_PERSONA_PRESENTADA(ID_PERSONA_REPRESENTADA,ID_SOLICITUD,ID_TURNO,ID_PERSONA_APODERADO,ID_CALIDAD_SOLICITANTE,"
		+ " ID_PERSONA_REPRESENTADA_APODERADO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_CALIDAD_PERSONA_REPRESENTADA) VALUES (?,?,?,?,?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_PERSONA_PRESENTADA SET ID_SOLICITUD = ?,ID_TURNO = ?,ID_PERSONA_APODERADO = ?,ID_CALIDAD_SOLICITANTE = ?,"
		+ "ID_PERSONA_REPRESENTADA_APODERADO = ?,ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION = ?, ID_CALIDAD_PERSONA_REPRESENTADA = ? WHERE ID_PERSONA_REPRESENTADA = ?";

	/** Constante cs_DELETE_BY_ID_SOLICITUD. */
	private static final String cs_DELETE_BY_ID_SOLICITUD = "DELETE FROM SDB_ACC_PERSONA_PRESENTADA WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_SECUENCE */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_PERSONA_PRESENTADA_ID_PERSONA_REPRESENTADA.NEXTVAL FROM DUAL";

	/**
	 * Elimina los registros que cumplan con los criterios de búsqueda en la tabla SDB_ACC_PERSONA_PRESENTADA
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que identifica los registros a eliminar.
	 * @throws B2BException Se presenta cuando se genera una excepción.
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
				lps_ps = getConnection().prepareStatement(cs_DELETE_BY_ID_SOLICITUD);

				lps_ps.setString(1, as_idSolicitud);

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
	 * Retorna el valor del objeto de PersonaPresentada.
	 *
	 * @param app_param correspondiente al valor del tipo de objeto PersonaPresentada
	 * @return devuelve el valor de PersonaPresentada
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaPresentada
	 */
	public PersonaPresentada findById(PersonaPresentada app_param)
	    throws B2BException
	{
		return (app_param != null) ? findById(app_param.getIdPersonaRepresentada()) : null;
	}

	/**
	 * Busca en la tabla un registro relacionado a un id específico
	 *
	 * @param as_idPersonaPresentada Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return PersonaPresentada resultante de la consulta
	 * @throws B2BException
	 */
	public PersonaPresentada findById(String as_idPersonaPresentada)
	    throws B2BException
	{
		PersonaPresentada lpp_return;

		lpp_return = null;

		if(StringUtils.isValidString(as_idPersonaPresentada))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idPersonaPresentada);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpp_return = getObjetFromResultSet(lrs_rs);
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

		return lpp_return;
	}

	/**
	 * Método para insertar registros en la tabla SDB_ACC_PERSONA_PRESENTADA
	 *
	 * @param app_parametros Objeto a insertar
	 * @return String identificador del objeto insertado
	 * @throws B2BException
	 */
	public String insert(PersonaPresentada app_parametros)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(app_parametros != null)
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
				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);
				lrs_rs            = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					Object lo_o;

					lo_o = lrs_rs.getObject(1);

					if((lo_o != null) && lo_o instanceof BigDecimal)
					{
						app_parametros.setIdPersonaRepresentada(((BigDecimal)lo_o).toString());

						lps_ps.setString(li_column++, app_parametros.getIdPersonaRepresentada());

						ls_return = app_parametros.getIdPersonaRepresentada();
					}
					else
						throw new B2BException(ErrorKeys.ACC_DEVOLUCION_DINERO_SEQUENCE);
				}

				lps_ps.setString(li_column++, app_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, app_parametros.getIdTurno());
				lps_ps.setString(li_column++, app_parametros.getIdPersonaApoderado());
				lps_ps.setString(li_column++, app_parametros.getIdCalidadSolicitante());
				lps_ps.setString(li_column++, app_parametros.getIdPersonaRepresentadaApoderado());
				lps_ps.setString(li_column++, app_parametros.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, app_parametros.getIpCreacion());
				lps_ps.setString(li_column++, app_parametros.getIdCalidadPersonaRepresentada());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);

				close(lps_ps);
				close(lps_secuencia);
			}
		}

		return ls_return;
	}

	/**
	 * Actualiza un registro ya existente en la tabla SDB_ACC_PERSONA_PRESENTADA
	 *
	 * @param app_parametros Objeto contenedor de la información que se pretende actualizar
	 * @throws B2BException
	 */
	public void update(PersonaPresentada app_parametros)
	    throws B2BException
	{
		if(app_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, app_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, app_parametros.getIdTurno());
				lps_ps.setString(li_column++, app_parametros.getIdPersonaApoderado());
				lps_ps.setString(li_column++, app_parametros.getIdCalidadSolicitante());
				lps_ps.setString(li_column++, app_parametros.getIdPersonaRepresentadaApoderado());
				lps_ps.setString(li_column++, app_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, app_parametros.getIpModificacion());
				lps_ps.setString(li_column++, app_parametros.getIdCalidadPersonaRepresentada());
				lps_ps.setString(li_column++, app_parametros.getIdPersonaRepresentada());

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
	 * Retorna el valor de PersonaPresentada
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de PersonaPresentada
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private PersonaPresentada getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		PersonaPresentada lp_personaPresentada;

		lp_personaPresentada = new PersonaPresentada();

		lp_personaPresentada.setIdPersonaRepresentada(lrs_rs.getString("ID_PERSONA_REPRESENTADA"));
		lp_personaPresentada.setIdSolicitud(lrs_rs.getString("ID_SOLICITUD"));
		lp_personaPresentada.setIdTurno(lrs_rs.getString("ID_TURNO"));
		lp_personaPresentada.setIdPersonaApoderado(lrs_rs.getString("ID_PERSONA_APODERADO"));
		lp_personaPresentada.setIdCalidadSolicitante(lrs_rs.getString("ID_CALIDAD_SOLICITANTE"));
		lp_personaPresentada.setIdPersonaRepresentadaApoderado(lrs_rs.getString("ID_PERSONA_REPRESENTADA_APODERADO"));
		lp_personaPresentada.setIdCalidadPersonaRepresentada(lrs_rs.getString("ID_CALIDAD_PERSONA_REPRESENTADA"));

		fillAuditoria(lrs_rs, lp_personaPresentada);

		return lp_personaPresentada;
	}
}
