package com.bachue.snr.prosnr01.dao.notificaciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.notificaciones.PersonaNotificacion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_PERSONA_NOTIFICACION
 *
 * @author broa
 *
 */
public class PersonaNotificacionDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID_TURNO. */
	private static final String cs_FIND_BY_ID_TURNO = " SELECT  * FROM SDB_ACC_PERSONA_NOTIFICACION WHERE ID_TURNO = ? ";
	private static final String cs_FIND_BY_ID = " SELECT  * FROM SDB_ACC_PERSONA_NOTIFICACION WHERE ID_PERSONA_NOTIFICACION = ? ";

	/** Constante cs_FIND_BY_ID_TURNO. */
	private static final String cs_FIND_ALL_BY_MAX_DECISION_PLANTILLA = " SELECT * FROM SDB_ACC_PERSONA_NOTIFICACION WHERE ID_TURNO = ? "
		+ " AND DECISION_PLANTILLA = (SELECT MAX(DECISION_PLANTILLA) FROM SDB_ACC_PERSONA_NOTIFICACION WHERE ID_TURNO = ? AND ID_ETAPA = ?)";

	/** Constante cs_FIND_BY_ID_TURNO_NOTIFICAR. */
	private static final String cs_FIND_BY_ID_DECISION = " SELECT  * FROM SDB_ACC_PERSONA_NOTIFICACION WHERE ID_TURNO = ? AND DECISION_PLANTILLA = ? AND ID_ETAPA = ?";

	/** Constante cs_DELETE_BY_ID_DECISION. */
	private static final String cs_DELETE_BY_ID_DECISION = " DELETE FROM SDB_ACC_PERSONA_NOTIFICACION WHERE ID_TURNO = ? AND DECISION_PLANTILLA = ? AND ID_ETAPA = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_PERSONA_NOTIFICACION_ID_PERSONA_NOTIFICACION.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_PERSONA_NOTIFICACION SET "
		+ " ID_PERSONA_NOTIFICACION = ?, ID_PERSONA = ?, ID_TURNO = ?, ID_SOLICITUD = ?, DESTINATARIO = ?, DIRECCION = ?, ID_DEPARTAMENTO = ?, ID_MUNICIPIO = ?, ID_PAIS = ?, "
		+ " TERCERO_INDETERMINADO = ?, ID_AUTORIZACION_COMUNICACION = ?, ID_AUTORIZACION_NOTIFICACION = ?, ID_DIRECCION_COMUNICACION = ?, ID_CORREO_ELECTRONICO_COMUNICACION = ?, "
		+ " ID_CORREO_ELECTRONICO = ?, ID_DIRECCION = ?, ID_TELEFONO_COMUNICACION = ?, DECISION_PLANTILLA = ?, ID_ETAPA = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? "
		+ " WHERE ID_PERSONA_NOTIFICACION = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_PERSONA_NOTIFICACION("
		+ " ID_PERSONA_NOTIFICACION, ID_PERSONA, ID_TURNO, ID_SOLICITUD, DESTINATARIO, DIRECCION, ID_DEPARTAMENTO, ID_MUNICIPIO, ID_PAIS, TERCERO_INDETERMINADO, ID_AUTORIZACION_COMUNICACION, "
		+ " ID_AUTORIZACION_NOTIFICACION, ID_DIRECCION_COMUNICACION, ID_CORREO_ELECTRONICO_COMUNICACION, ID_CORREO_ELECTRONICO, ID_DIRECCION, ID_TELEFONO_COMUNICACION, DECISION_PLANTILLA, ID_ETAPA,"
		+ " ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_PERSONA_NOTIFICACION ";

	/**
	 * Método para eliminar de la base de datos todos lo registros de la tabla SDB_ACC_PERSONA_NOTIFICACION para un ID_TURNO y DECISION.
	 * @param as_idTurno Argumento de tipo <code>String</code> que contiene el ID_TURNO a eliminar.
	 * @param al_idMotivo Argumento de tipo <code>Long</code> que contiene la DECISION a eliminar.
	 * @param al_idEtapa Argumento de tipo <code>Long</code> que contiene la etapa a eliminar.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public void deleteByIdTurnoDecision(String as_idTurno, Long al_idMotivo, Long al_idEtapa)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps                                = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idTurno) && NumericUtils.isValidLong(al_idMotivo)
				    && NumericUtils.isValidLong(al_idEtapa)
			)
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE_BY_ID_DECISION);

				lps_ps.setString(li_column++, as_idTurno);
				setLong(lps_ps, al_idMotivo, li_column++);
				setLong(lps_ps, al_idEtapa, li_column++);

				lps_ps.executeUpdate();
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "deleteByIdTurnoDecision", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
		}
	}

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_MEDIDA_AREA.
	 *
	 * @return devuelve el valor del objeto collection de PersonaNotificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<PersonaNotificacion> findAll()
	    throws B2BException
	{
		Collection<PersonaNotificacion> lccad_ccad;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lccad_ccad     = new ArrayList<PersonaNotificacion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccad_ccad.add(getPersonaNotificacion(lrs_rs));
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

		if(lccad_ccad.isEmpty())
			lccad_ccad = null;

		return lccad_ccad;
	}

	/**
	 * Find by id.
	 *
	 * @param as_idPersonaNotificacion de as id persona notificacion
	 * @return el valor de persona notificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PersonaNotificacion findById(String as_idPersonaNotificacion)
	    throws B2BException
	{
		PersonaNotificacion lccad_ccad;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lccad_ccad     = new PersonaNotificacion();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_idPersonaNotificacion);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lccad_ccad = getPersonaNotificacion(lrs_rs);
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

		return lccad_ccad;
	}

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_ACC_PERSONA_NOTIFICACION para un ID_TURNO.
	 * @param Argumento de tipo <code>String</code> que contiene el ID_TURNO a consultar.
	 * @return devuelve el valor del objeto collection de PersonaNotificacion
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public Collection<PersonaNotificacion> findByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<PersonaNotificacion> lccad_ccad;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lccad_ccad     = new ArrayList<PersonaNotificacion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO);

			lps_ps.setString(1, as_idTurno);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccad_ccad.add(getPersonaNotificacion(lrs_rs));
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

		if(lccad_ccad.isEmpty())
			lccad_ccad = null;

		return lccad_ccad;
	}

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_ACC_PERSONA_NOTIFICACION para un ID_TURNO y DECISION.
	 * @param as_idTurno Argumento de tipo <code>String</code> que contiene el ID_TURNO a consultar.
	 * @param al_idMotivo Argumento de tipo <code>long</code> que contiene la DECISION a consultar.
	 * @param al_idEtapa Argumento de tipo <code>Long</code> que contiene la etapa a consultar.
	 * @return devuelve el valor del objeto collection de PersonaNotificacion
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public Collection<PersonaNotificacion> findByIdTurnoDecision(String as_idTurno, long al_idMotivo, Long al_idEtapa)
	    throws B2BException
	{
		Collection<PersonaNotificacion> lccad_ccad;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lccad_ccad     = new ArrayList<PersonaNotificacion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_column;

			li_column     = 1;
			lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_DECISION);

			lps_ps.setString(li_column++, as_idTurno);
			lps_ps.setLong(li_column++, al_idMotivo);
			setLong(lps_ps, al_idEtapa, li_column++);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccad_ccad.add(getPersonaNotificacion(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdTurnoDecision", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lccad_ccad.isEmpty())
			lccad_ccad = null;

		return lccad_ccad;
	}

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_ACC_PERSONA_NOTIFICACION para un ID_TURNO.
	 * @param Argumento de tipo <code>String</code> que contiene el ID_TURNO a consultar.
	 * @return devuelve el valor del objeto collection de PersonaNotificacion
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public Collection<PersonaNotificacion> findByMaxDecisionPlantilla(String as_idTurno, Long al_idEtapa)
	    throws B2BException
	{
		Collection<PersonaNotificacion> lccad_ccad;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lccad_ccad     = new ArrayList<PersonaNotificacion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(StringUtils.isValidString(as_idTurno) && NumericUtils.isValidLong(al_idEtapa))
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_MAX_DECISION_PLANTILLA);

				lps_ps.setString(li_contador++, as_idTurno);
				lps_ps.setString(li_contador++, as_idTurno);
				setLong(lps_ps, al_idEtapa, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccad_ccad.add(getPersonaNotificacion(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByMaxDecisionPlantilla", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lccad_ccad.isEmpty())
			lccad_ccad = null;

		return lccad_ccad;
	}

	/**
	 * Inserta o actualiza un registro en la tabla.
	 *
	 * @param apn_parametros contenedor de la informacion a almacenar
	 * @param ab_query true para insertar, false para actualizar
	 * @return Objeto final insertado o actualizado en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaNotificar
	 */
	public PersonaNotificacion insertOrUpdate(PersonaNotificacion apn_parametros, boolean ab_query)
	    throws B2BException
	{
		PersonaNotificacion lp_return;

		lp_return = null;

		if(apn_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lrs_rs            = null;
			lps_secuencia     = null;
			lps_ps            = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					Connection lc_connection;

					lc_connection     = getConnection();

					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
						{
							apn_parametros.setIdPersonaNotificacion(((BigDecimal)lo_o).toString());

							lps_ps.setString(li_column++, apn_parametros.getIdPersonaNotificacion());
						}
						else
							throw new B2BException(ErrorKeys.SIN_SECUENCIA_PERSONA);
					}
				}

				lps_ps.setString(li_column++, apn_parametros.getIdPersona());
				lps_ps.setString(li_column++, apn_parametros.getIdTurno());
				lps_ps.setString(li_column++, apn_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, apn_parametros.getDestinatario());
				lps_ps.setString(li_column++, apn_parametros.getDireccion());
				lps_ps.setString(li_column++, apn_parametros.getIdDepartamento());
				lps_ps.setString(li_column++, apn_parametros.getIdMunicipio());
				lps_ps.setString(li_column++, apn_parametros.getIdPais());
				lps_ps.setString(li_column++, apn_parametros.getTerceroIndeterminado());
				lps_ps.setString(li_column++, apn_parametros.getIdAutorizacionComunicacion());
				lps_ps.setString(li_column++, apn_parametros.getIdAutorizacionNotificacion());
				lps_ps.setString(li_column++, apn_parametros.getIdDireccionComunicacion());
				lps_ps.setString(li_column++, apn_parametros.getIdCorreoElectronicoComunicacion());
				lps_ps.setString(li_column++, apn_parametros.getIdCorreoElectronico());
				lps_ps.setString(li_column++, apn_parametros.getIdDireccion());
				lps_ps.setString(li_column++, apn_parametros.getIdTelefonoComunicacion());
				lps_ps.setString(li_column++, apn_parametros.getIdDecisionPlantilla());
				setLong(lps_ps, apn_parametros.getIdEtapa(), li_column++);

				if(ab_query)
				{
					lps_ps.setString(li_column++, apn_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, apn_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, apn_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, apn_parametros.getIpModificacion());
				}

				if(!ab_query)
					lps_ps.setString(li_column++, apn_parametros.getIdPersonaNotificacion());

				lps_ps.executeUpdate();

				lp_return = apn_parametros;
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

		return lp_return;
	}

	/**
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de persona notificacion
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de alerta naturaleza
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private PersonaNotificacion getPersonaNotificacion(ResultSet ars_rs)
	    throws SQLException
	{
		PersonaNotificacion lc_datos;

		lc_datos = new PersonaNotificacion();

		lc_datos.setIdPersonaNotificacion(ars_rs.getString("ID_PERSONA_NOTIFICACION"));
		lc_datos.setIdPersona(ars_rs.getString("ID_PERSONA"));
		lc_datos.setIdTurno(ars_rs.getString("ID_TURNO"));
		lc_datos.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lc_datos.setDireccion(ars_rs.getString("DIRECCION"));
		lc_datos.setDestinatario(ars_rs.getString("DESTINATARIO"));
		lc_datos.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		lc_datos.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		lc_datos.setIdPais(ars_rs.getString("ID_PAIS"));
		lc_datos.setTerceroIndeterminado(ars_rs.getString("TERCERO_INDETERMINADO"));
		lc_datos.setIdAutorizacionComunicacion(ars_rs.getString("ID_AUTORIZACION_COMUNICACION"));
		lc_datos.setIdAutorizacionNotificacion(ars_rs.getString("ID_AUTORIZACION_NOTIFICACION"));
		lc_datos.setIdDireccionComunicacion(ars_rs.getString("ID_DIRECCION_COMUNICACION"));
		lc_datos.setIdCorreoElectronicoComunicacion(ars_rs.getString("ID_CORREO_ELECTRONICO_COMUNICACION"));
		lc_datos.setIdCorreoElectronico(ars_rs.getString("ID_CORREO_ELECTRONICO"));
		lc_datos.setIdDireccion(ars_rs.getString("ID_DIRECCION"));
		lc_datos.setIdTelefonoComunicacion(ars_rs.getString("ID_TELEFONO_COMUNICACION"));
		lc_datos.setIdDecisionPlantilla(ars_rs.getString("DECISION_PLANTILLA"));
		lc_datos.setIdEtapa(getLong(ars_rs, "ID_ETAPA"));

		fillAuditoria(ars_rs, lc_datos);

		return lc_datos;
	}
}
