package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.AlertaTitular;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;


/**
 * Clase encargada del manejo de transacciones relacionadas a la tabla SDB_ACC_ALERTA_TITULAR.
 *
 * @author Manuel Blanco
 */
public class AlertaTitularDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_ALERTA_TITULAR, ID_SOLICITUD, ID_MATRICULA, ID_CIRCULO, FECHA_INSCRIPCION, FECHA_FIN_ALERTA, ESTADO_ALERTA, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_ACC_ALERTA_TITULAR WHERE ID_ALERTA_TITULAR = ?";

	/** Constante cs_FIND_COMUNICACION. */
	private static final String cs_FIND_COMUNICACION = "SELECT ID_PERSONA_ALERT FROM SDB_ACC_ALERTA_TITULAR_MENSAJE WHERE ID_MENSAJE_COMUNICACIONES = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_ALERTA_TITULAR WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_BY_DATOS_PERSONA. */
	private static final String cs_FIND_BY_DATOS_PERSONA = "SELECT SAAT.* FROM SDB_ACC_ALERTA_TITULAR SAAT INNER JOIN SDB_ACC_SOLICITUD SAS ON SAAT.ID_SOLICITUD = SAS.ID_SOLICITUD INNER JOIN SDB_ACC_PERSONA SAP ON SAP.ID_PERSONA = SAS.ID_PERSONA ";

	/** Constante cs_UPDATE_ESTADO. */
	private static final String cs_UPDATE_ESTADO = "UPDATE SDB_ACC_ALERTA_TITULAR SET ESTADO_ALERTA = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_ALERTA_TITULAR = ? ";

	/** Constante cs_COUNT_MATRICULA_PERSONA_ALERTA. */
	private static final String cs_COUNT_MATRICULA_PERSONA_ALERTA = "SELECT COUNT(0) CONTADOR FROM SDB_ACC_SOLICITUD "
		+ " INNER JOIN SDB_ACC_SOLICITUD_MATRICULA ON SDB_ACC_SOLICITUD.ID_SOLICITUD = SDB_ACC_SOLICITUD_MATRICULA.ID_SOLICITUD "
		+ " WHERE SDB_ACC_SOLICITUD.ID_SOLICITUD = ? AND NVL(SDB_ACC_SOLICITUD_MATRICULA.ESTADO,'N') <> 'I' "
		+ " AND  EXISTS ( SELECT 1 FROM SDB_ACC_ALERTA_TITULAR  "
		+ " INNER JOIN SDB_ACC_SOLICITUD SANT ON SDB_ACC_ALERTA_TITULAR.ID_SOLICITUD = SANT.ID_SOLICITUD "
		+ " WHERE SANT.ID_PERSONA = ? AND NVL(SDB_ACC_ALERTA_TITULAR.ESTADO_ALERTA,'NULL') <> 'VENCIDA'  "
		+ " AND SDB_ACC_SOLICITUD_MATRICULA.ID_CIRCULO = SDB_ACC_ALERTA_TITULAR.ID_CIRCULO  "
		+ " AND SDB_ACC_SOLICITUD_MATRICULA.ID_MATRICULA = SDB_ACC_ALERTA_TITULAR.ID_MATRICULA ) ";

	/** Constante cs_DELETE. */
	private static final String cs_DELETE = "DELETE FROM SDB_ACC_ALERTA_TITULAR_MENSAJE WHERE ID_MENSAJE_COMUNICACIONES = ?";

	/**
	 * Metodo encargado de consultar si existen registros para una matricula y persona en alertas titulos.
	 *
	 * @param as_solicitud Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return Contador de alerta titular resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AlertaTitular
	 */
	public int countAlertaMatriculaPersona(Solicitud as_solicitud)
	    throws B2BException
	{
		int li_count;

		li_count = 0;

		if(as_solicitud != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_COUNT_MATRICULA_PERSONA_ALERTA);

				lps_ps.setString(li_contador++, as_solicitud.getIdSolicitud());
				lps_ps.setString(li_contador++, as_solicitud.getIdPersona());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_count = lrs_rs.getInt("CONTADOR");
			}
			catch(SQLException lse_e)
			{
				logError(this, "countAlertaMatriculaPersona", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return li_count;
	}

	/**
	 * Delete by mensaje comunicacion.
	 *
	 * @param as_idMensajeComunicacion the as id mensaje comunicacion
	 * @throws B2BException the b 2 B exception
	 */
	public void deleteByMensajeComunicacion(String as_idMensajeComunicacion)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idMensajeComunicacion))
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setString(li_column++, as_idMensajeComunicacion);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByMensajeComunicacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Busca un registro de la tabla por medio de ID_MENSAJE_COMUNICACIONES.
	 *
	 * @param as_idMensajeComunicacion correspondiente al valor de id mensaje comunicacion
	 * @return el valor de idPersonaAlert
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String findByComunicacion(String as_idMensajeComunicacion)
	    throws B2BException
	{
		String ls_idPersonaAlert;

		ls_idPersonaAlert = null;

		if(StringUtils.isValidString(as_idMensajeComunicacion))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_COMUNICACION);

				lps_ps.setString(1, as_idMensajeComunicacion);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_idPersonaAlert = lrs_rs.getString("ID_PERSONA_ALERT");
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByComunicacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_idPersonaAlert;
	}

	/**
	 * Consulta todos los registros asociados a un tipo y número de documento.
	 *
	 * @param aat_parametros Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AlertaTitular> findByDatosPersona(AlertaTitular aat_parametros)
	    throws B2BException
	{
		Collection<AlertaTitular> lcat_return;

		lcat_return = new LinkedList<AlertaTitular>();

		if(aat_parametros != null)
		{
			Persona lp_persona;

			lp_persona = aat_parametros.getPersona();

			if(lp_persona != null)
			{
				PreparedStatement lps_ps;
				ResultSet         lrs_rs;

				lps_ps     = null;
				lrs_rs     = null;

				try
				{
					int           li_cont;
					StringBuilder lsb_query;
					String        ls_idDepartamento;
					String        ls_idMunicipio;
					String        ls_idCirculo;
					String        ls_idMatricula;
					String        ls_estadoAlerta;
					String        ls_idAlertaTitular;
					Date          ld_fechaFinAlerta;

					boolean lb_idDepartamento;
					boolean lb_idMunicipio;
					boolean lb_idCirculo;
					boolean lb_idMatricula;
					boolean lb_estadoAlerta;
					boolean lb_idAlertaTitular;
					boolean lb_fechaFinAlerta;

					li_cont                = 1;
					lsb_query              = new StringBuilder(cs_FIND_BY_DATOS_PERSONA);
					ls_idDepartamento      = aat_parametros.getIdDepartamento();
					ls_idMunicipio         = aat_parametros.getIdMunicipio();
					ls_idCirculo           = aat_parametros.getIdCirculo();
					ls_idMatricula         = aat_parametros.getIdMatricula();
					ls_estadoAlerta        = StringUtils.getStringUpperCase(aat_parametros.getEstadoAlerta());
					ls_idAlertaTitular     = aat_parametros.getIdAlertaTitular();
					ld_fechaFinAlerta      = aat_parametros.getFechaFinAlerta();

					lb_idDepartamento      = StringUtils.isValidString(ls_idDepartamento);
					lb_idMunicipio         = StringUtils.isValidString(ls_idMunicipio);
					lb_idCirculo           = StringUtils.isValidString(ls_idCirculo);
					lb_idMatricula         = StringUtils.isValidString(ls_idMatricula);
					lb_estadoAlerta        = StringUtils.isValidString(ls_estadoAlerta);
					lb_idAlertaTitular     = StringUtils.isValidString(ls_idAlertaTitular);
					lb_fechaFinAlerta      = ld_fechaFinAlerta != null;

					if(lb_idDepartamento || lb_idMunicipio)
						lsb_query.append(
						    " INNER JOIN SDB_PGN_CIRCULO_REGISTRAL SPCR ON SPCR.ID_CIRCULO = SAAT.ID_CIRCULO INNER JOIN SDB_PGN_OFICINA_ORIGEN SPOO ON SPOO.ID_OFICINA_ORIGEN = SPCR.ID_OFICINA_ORIGEN "
						);

					lsb_query.append(" WHERE SAP.ID_DOCUMENTO_TIPO = ? AND SAP.NUMERO_DOCUMENTO = ? ");

					if(lb_idDepartamento)
						lsb_query.append(" AND SPOO.ID_DEPARTAMENTO = ? ");

					if(lb_idMunicipio)
						lsb_query.append(" AND SPOO.ID_MUNICIPIO = ? ");

					if(lb_idCirculo)
						lsb_query.append(" AND SAAT.ID_CIRCULO = ? ");

					if(lb_idMatricula)
						lsb_query.append(" AND SAAT.ID_MATRICULA = ? ");

					if(lb_estadoAlerta)
						lsb_query.append(" AND SAAT.ESTADO_ALERTA = ? ");

					if(lb_idAlertaTitular)
						lsb_query.append(" AND SAAT.ID_ALERTA_TITULAR = ? ");

					if(lb_fechaFinAlerta)
						lsb_query.append(" AND SAAT.FECHA_FIN_ALERTA >= ? ");

					lsb_query.append(" ORDER BY SAAT.ID_ALERTA_TITULAR ASC ");

					lps_ps = getConnection().prepareStatement(lsb_query.toString());

					lps_ps.setString(li_cont++, lp_persona.getIdDocumentoTipo());
					lps_ps.setString(li_cont++, lp_persona.getNumeroDocumento());

					if(lb_idDepartamento)
						lps_ps.setString(li_cont++, ls_idDepartamento);

					if(lb_idMunicipio)
						lps_ps.setString(li_cont++, ls_idMunicipio);

					if(lb_idCirculo)
						lps_ps.setString(li_cont++, ls_idCirculo);

					if(lb_idMatricula)
						lps_ps.setString(li_cont++, ls_idMatricula);

					if(lb_estadoAlerta)
						lps_ps.setString(li_cont++, ls_estadoAlerta);

					if(lb_idAlertaTitular)
						lps_ps.setString(li_cont++, ls_idAlertaTitular);

					if(lb_fechaFinAlerta)
						lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(ld_fechaFinAlerta));

					lrs_rs = lps_ps.executeQuery();

					while(lrs_rs.next())
						lcat_return.add(getObjectFromResultSet(lrs_rs));
				}
				catch(SQLException lse_e)
				{
					logError(this, "findByDatosPersona", lse_e);

					throw new B2BException(SQL_ERROR, lse_e);
				}
				finally
				{
					close(lrs_rs);
					close(lps_ps);
				}
			}
		}

		if(lcat_return.isEmpty())
			lcat_return = null;

		return lcat_return;
	}

	/**
	 * Consulta todos los registros asociados a un tipo, número de documento y estado ACTIVA e INACTIVA.
	 *
	 * @param aat_parametros Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AlertaTitular> findByDatosPersonaActivasEInactivas(AlertaTitular aat_parametros)
	    throws B2BException
	{
		Collection<AlertaTitular> lcat_return;

		lcat_return = new LinkedList<AlertaTitular>();

		if(aat_parametros != null)
		{
			Persona lp_persona;

			lp_persona = aat_parametros.getPersona();

			if(lp_persona != null)
			{
				PreparedStatement lps_ps;
				ResultSet         lrs_rs;

				lps_ps     = null;
				lrs_rs     = null;

				try
				{
					int           li_cont;
					StringBuilder lsb_query;

					li_cont       = 1;
					lsb_query     = new StringBuilder(cs_FIND_BY_DATOS_PERSONA);

					lsb_query.append(" WHERE SAP.ID_DOCUMENTO_TIPO = ? AND SAP.NUMERO_DOCUMENTO = ? ");
					lsb_query.append(" AND SAAT.ESTADO_ALERTA IN ('ACTIVA','INACTIVA')");

					lps_ps = getConnection().prepareStatement(lsb_query.toString());

					lps_ps.setString(li_cont++, lp_persona.getIdDocumentoTipo());
					lps_ps.setString(li_cont++, lp_persona.getNumeroDocumento());

					lrs_rs = lps_ps.executeQuery();

					while(lrs_rs.next())
						lcat_return.add(getObjectFromResultSet(lrs_rs));
				}
				catch(SQLException lse_e)
				{
					logError(this, "findByDatosPersonaActivasEInactivas", lse_e);

					throw new B2BException(SQL_ERROR, lse_e);
				}
				finally
				{
					close(lrs_rs);
					close(lps_ps);
				}
			}
		}

		if(lcat_return.isEmpty())
			lcat_return = null;

		return lcat_return;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param as_idAlertaTitular Objeto contenedor de los filtros a usar en la consulta
	 * @return AlertaTitular resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AlertaTitular findById(String as_idAlertaTitular)
	    throws B2BException
	{
		AlertaTitular lat_return;

		lat_return = null;

		if(StringUtils.isValidString(as_idAlertaTitular))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idAlertaTitular);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lat_return = getObjectFromResultSet(lrs_rs);
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

		return lat_return;
	}

	/**
	 * Find by id solicitud.
	 *
	 * @param as_idSolicitud de as id alerta titular
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AlertaTitular> findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<AlertaTitular> lcat_return;

		lcat_return = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(1, as_idSolicitud);

				lrs_rs          = lps_ps.executeQuery();
				lcat_return     = new ArrayList<AlertaTitular>();

				while(lrs_rs.next())
					lcat_return.add(getObjectFromResultSet(lrs_rs));
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
		}

		return lcat_return;
	}

	/**
	 * Actualiza el estado de una alerta.
	 *
	 * @param aat_param Objeto contenedor de los parametros a utilizar en la actualización de registros
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateEstado(AlertaTitular aat_param)
	    throws B2BException
	{
		if(aat_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_ESTADO);

				lps_ps.setString(li_column++, aat_param.getEstadoAlerta());
				lps_ps.setString(li_column++, aat_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aat_param.getIpModificacion());
				lps_ps.setString(li_column++, aat_param.getIdAlertaTitular());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateEstado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Obtiene los datos resultantes de una consulta.
	 *
	 * @param ars_rs Objeto contenedor de los resultados de una consulta
	 * @return Alerta titular resultante de la consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AlertaTitular getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AlertaTitular lat_alertaTitular;

		lat_alertaTitular = new AlertaTitular();

		lat_alertaTitular.setIdAlertaTitular(ars_rs.getString("ID_ALERTA_TITULAR"));
		lat_alertaTitular.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lat_alertaTitular.setIdMatricula(ars_rs.getString("ID_MATRICULA"));
		lat_alertaTitular.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lat_alertaTitular.setFechaInscripcion(ars_rs.getTimestamp("FECHA_INSCRIPCION"));
		lat_alertaTitular.setFechaFinAlerta(ars_rs.getTimestamp("FECHA_FIN_ALERTA"));
		lat_alertaTitular.setEstadoAlerta(ars_rs.getString("ESTADO_ALERTA"));

		fillAuditoria(ars_rs, lat_alertaTitular);

		return lat_alertaTitular;
	}
}
