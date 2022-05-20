package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.MensajeComunicacion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_MENSAJE_COMUNICACION.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 16/06/2020
 */
public class MensajeComunicacionDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_MENSAJE_COMUNICACION "
		+ " WHERE ID_MENSAJE_COMUNICACIONES = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_MENSAJE_COMUNICACION "
		+ "SET ID_TURNO_HISTORIA = ?, ID_ORIGEN = ?, ID_CANAL = ?, ID_SOLICITUD = ?, ID_PLANTILLA = ?, CORREO_ELECTRONICO = ?, NUMERO_CELULAR = ?, INTENTOS_FALLIDOS_EJECUCION_AUTOMATICA = ?, FECHA_INTENTO_EJECUCION_AUTOMATICA = ?, FECHA_EXITO_EJECUCION_AUTOMATICA = ?, RESPUESTAS_EJECUCION_AUTOMATICA = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? "
		+ "WHERE ID_MENSAJE_COMUNICACIONES = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_MENSAJE_COMUNICACION "
		+ "(ID_MENSAJE_COMUNICACIONES, ID_TURNO_HISTORIA, ID_ORIGEN, ID_CANAL, ID_SOLICITUD, ID_PLANTILLA, CORREO_ELECTRONICO, NUMERO_CELULAR, INTENTOS_FALLIDOS_EJECUCION_AUTOMATICA, FECHA_INTENTO_EJECUCION_AUTOMATICA, FECHA_EXITO_EJECUCION_AUTOMATICA, RESPUESTAS_EJECUCION_AUTOMATICA, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_MENSAJE_COMUNICACION ";

	/** Constante cs_UPDATE_INTENTO_ENVIOS. */
	private static final String cs_UPDATE_INTENTO_ENVIOS = "UPDATE SDB_ACC_MENSAJE_COMUNICACION SET INTENTOS_FALLIDOS_EJECUCION_AUTOMATICA = ?, FECHA_INTENTO_EJECUCION_AUTOMATICA = SYSTIMESTAMP, RESPUESTAS_EJECUCION_AUTOMATICA = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_MENSAJE_COMUNICACIONES = ?";

	/** Constante cs_DELETE_BY_ID. */
	private static final String cs_DELETE_BY_ID = "DELETE FROM SDB_ACC_MENSAJE_COMUNICACION WHERE ID_MENSAJE_COMUNICACIONES = ?";

	/** Constante cs_BUSCAR_COMUNICACIONES_POR_ENVIAR. */
	private static final String cs_BUSCAR_COMUNICACIONES_POR_ENVIAR = "SELECT DISTINCT MC.* FROM SDB_ACC_MENSAJE_COMUNICACION MC "
		+ "INNER JOIN SDB_ACC_DOCUMENTOS_SALIDA DS ON (DS.ID_SOLICITUD = MC.ID_SOLICITUD AND DS.ID_ECM IS NOT NULL AND "
		+ "DS.ID_NOMBRE_DOCUMENTO IS NOT NULL AND DS.FECHA_ENVIO IS NOT NULL) "
		+ "WHERE MC.FECHA_EXITO_EJECUCION_AUTOMATICA IS NULL AND NVL(MC.INTENTOS_FALLIDOS_EJECUCION_AUTOMATICA,0) < "
		+ "(SELECT ENTERO FROM SDB_PGN_CONSTANTES WHERE ID_CONSTANTE = '"
		+ ConstanteCommon.JOB_ENVIO_COMUNICADOS_LIMITE_INTENTOS + "')  ORDER BY MC.FECHA_CREACION ASC";

	/** Constante cs_FIND_SECUENCE_ID_MENSAJE. */
	private static final String cs_FIND_SECUENCE_ID_MENSAJE = "SELECT SEC_ACC_MENSAJE_COMUNICACION_ID_MENSAJE_COMUNICACIONES.NEXTVAL FROM DUAL";

	/**
	 * Buscar todas las comunicaciones.
	 *
	 * @return el valor de Collection<MensajeComunicacion>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<MensajeComunicacion> buscarTodasLasComunicaciones()
	    throws B2BException
	{
		Collection<MensajeComunicacion> lcmc_mensajes;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lcmc_mensajes     = new ArrayList<MensajeComunicacion>();
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_BUSCAR_COMUNICACIONES_POR_ENVIAR);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcmc_mensajes.add(getObjectFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "buscarTodasLasComunicaciones", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcmc_mensajes.isEmpty())
			lcmc_mensajes = null;

		return lcmc_mensajes;
	}

	/**
	 * Metodo encargado de eliminar un registro de la tabla SDB_ACC_MENSAJE_COMUNICACION de la base de datos.
	 *
	 * @param amc_parametros de MensajeComunicacion con la informacion a eliminar.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void deleteById(MensajeComunicacion amc_parametros)
	    throws B2BException
	{
		if(amc_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE_BY_ID);

				lps_ps.setString(li_column++, amc_parametros.getIdMensajeComunicaciones());

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
	}

	/**
	 * Find all.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<MensajeComunicacion> findAll()
	    throws B2BException
	{
		Collection<MensajeComunicacion> lcmc_mensajes;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lcmc_mensajes     = new ArrayList<MensajeComunicacion>();
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcmc_mensajes.add(getObjectFromResultSet(lrs_rs));
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

		if(lcmc_mensajes.isEmpty())
			lcmc_mensajes = null;

		return lcmc_mensajes;
	}

	/**
	 * Buscar por la llave.
	 *
	 * @param amc_param de MensajeComunicacion
	 * @return el valor de mensaje comunicacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MensajeComunicacion findById(MensajeComunicacion amc_param)
	    throws B2BException
	{
		return (amc_param != null) ? findById(amc_param.getIdMensajeComunicaciones()) : null;
	}

	/**
	 * Buscar por la llave.
	 *
	 * @param as_param de idMensajeComunicacion
	 * @return el valor de mensaje comunicacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MensajeComunicacion findById(String as_param)
	    throws B2BException
	{
		MensajeComunicacion lmc_mensaje;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lmc_mensaje     = null;
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_param);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lmc_mensaje = getObjectFromResultSet(lrs_rs);
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

		return lmc_mensaje;
	}

	/**
	 * creacion o modificacion de un registro
	 *
	 * @param amc_parametros de MensajeComunicacion
	 * @param ab_query true si desea crear un registro de lo contrario se modificara un registro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertOrUpdate(MensajeComunicacion amc_parametros, boolean ab_query)
	    throws B2BException
	{
		if(amc_parametros != null)
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

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE_ID_MENSAJE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							amc_parametros.setIdMensajeComunicaciones(StringUtils.getString(lo_o));
						else
							throw new B2BException(ErrorKeys.ACC_MENSAJE_COMUNICACION_SEQUENCE);
					}

					lps_ps.setString(li_column++, amc_parametros.getIdMensajeComunicaciones());
				}

				lps_ps.setString(li_column++, amc_parametros.getIdTurnoHistoria());
				lps_ps.setString(li_column++, amc_parametros.getIdOrigen());
				lps_ps.setString(li_column++, amc_parametros.getIdCanal());
				lps_ps.setString(li_column++, amc_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, amc_parametros.getIdPlantilla());
				lps_ps.setString(li_column++, amc_parametros.getCorreoElectronico());
				lps_ps.setString(li_column++, amc_parametros.getNumeroCelular());
				lps_ps.setInt(li_column++, amc_parametros.getIntentoEnvio());
				setDate(lps_ps, amc_parametros.getFechaIntentoEjecucionAutomatica(), li_column++);
				setDate(lps_ps, amc_parametros.getFechaExitoEjecucionAutomatica(), li_column++);
				lps_ps.setString(li_column++, amc_parametros.getRespuestasEjecucionAutomatica());

				if(ab_query)
				{
					lps_ps.setString(li_column++, amc_parametros.getIpCreacion());
					lps_ps.setString(li_column++, amc_parametros.getIdUsuarioCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, amc_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, amc_parametros.getIpModificacion());
					lps_ps.setString(li_column++, amc_parametros.getIdMensajeComunicaciones());
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

				if(ab_query)
				{
					close(lps_secuencia);
					close(lrs_rs);
				}
			}
		}
	}

	/**
	 * Update intento envios.
	 *
	 * @param amc_parametros de MensajeComunicacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateIntentoEnvios(MensajeComunicacion amc_parametros)
	    throws B2BException
	{
		if(amc_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int    li_column;
				String ls_respuesta;

				li_column        = 1;
				lps_ps           = getConnection().prepareStatement(cs_UPDATE_INTENTO_ENVIOS);
				ls_respuesta     = amc_parametros.getRespuestasEjecucionAutomatica();

				if(StringUtils.isValidString(ls_respuesta))
					ls_respuesta = ls_respuesta.substring(0, 90);

				setInteger(lps_ps, amc_parametros.getIntentosFallidosEjecucionAutomatica(), li_column++);
				lps_ps.setString(li_column++, ls_respuesta);
				lps_ps.setString(li_column++, amc_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, amc_parametros.getIpModificacion());
				lps_ps.setString(li_column++, amc_parametros.getIdMensajeComunicaciones());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateIntentoEnvios", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna Objeto o variable de valor MensajeComunicacion.
	 *
	 * @param ars_rs de ResultSet
	 * @return el valor de MensajeComunicacion
	 * @throws SQLException cuando se produce algún error en el proceso
	 */
	private MensajeComunicacion getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		MensajeComunicacion lmc_mensaje;

		lmc_mensaje = new MensajeComunicacion();

		lmc_mensaje.setIdMensajeComunicaciones(ars_rs.getString("ID_MENSAJE_COMUNICACIONES"));
		lmc_mensaje.setIdTurnoHistoria(ars_rs.getString("ID_TURNO_HISTORIA"));
		lmc_mensaje.setIdOrigen(ars_rs.getString("ID_ORIGEN"));
		lmc_mensaje.setIdCanal(ars_rs.getString("ID_CANAL"));
		lmc_mensaje.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lmc_mensaje.setIdPlantilla(ars_rs.getString("ID_PLANTILLA"));
		lmc_mensaje.setCorreoElectronico(ars_rs.getString("CORREO_ELECTRONICO"));
		lmc_mensaje.setNumeroCelular(ars_rs.getString("NUMERO_CELULAR"));
		lmc_mensaje.setIntentosFallidosEjecucionAutomatica(
		    getInteger(ars_rs, "INTENTOS_FALLIDOS_EJECUCION_AUTOMATICA")
		);
		lmc_mensaje.setFechaIntentoEjecucionAutomatica(ars_rs.getDate("FECHA_INTENTO_EJECUCION_AUTOMATICA"));
		lmc_mensaje.setFechaExitoEjecucionAutomatica(ars_rs.getDate("FECHA_EXITO_EJECUCION_AUTOMATICA"));
		lmc_mensaje.setRespuestasEjecucionAutomatica(ars_rs.getString("RESPUESTAS_EJECUCION_AUTOMATICA"));

		fillAuditoria(ars_rs, lmc_mensaje);

		return lmc_mensaje;
	}
}
