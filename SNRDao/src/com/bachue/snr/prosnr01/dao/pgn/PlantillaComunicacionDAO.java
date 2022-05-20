package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.PlantillaComunicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_PLANTILLA_COMUNICACION.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 16/06/2020
 */
public class PlantillaComunicacionDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_PLANTILLA_COMUNICACION ";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = " SELECT * FROM SDB_PGN_PLANTILLA_COMUNICACION WHERE ID_PLANTILLA = ? AND ID_PROCESO = ? AND ID_ETAPA_ACTUAL = ? ";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID_PLANTILLA_COMUNICACION = " SELECT * FROM SDB_PGN_PLANTILLA_COMUNICACION WHERE ID_PLANTILLA_COMUNICACION = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_PLANTILLA_COMUNICACION (ID_PLANTILLA_COMUNICACION, ID_PLANTILLA, ID_PROCESO, ID_ETAPA_ACTUAL, ID_ETAPA_ANTERIOR, ID_MOTIVO_ANTERIOR, ID_SUBPROCESO, TRAMITE, CLASIFICACION, CANAL, ID_AUTORIZACION_COMUNICACION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_UPDATE_COMPLETE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_PLANTILLA_COMUNICACION SET ID_PLANTILLA = ?, ID_PROCESO = ?, ID_ETAPA_ACTUAL = ?, ID_ETAPA_ANTERIOR = ?, ID_MOTIVO_ANTERIOR = ?, ID_SUBPROCESO = ?, TRAMITE = ?, CLASIFICACION = ?, CANAL = ?, ACTIVO = ?, ID_AUTORIZACION_COMUNICACION = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_PLANTILLA_COMUNICACION = ?";

	/** Constate cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_PGN_PLANTILLA_COMUNICACION_ID_PLANTILLA_COMUNICACION.NEXTVAL FROM DUAL";

	/**
	 * Find all.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PlantillaComunicacion> findAll()
	    throws B2BException
	{
		Collection<PlantillaComunicacion> lcpc_plantillas;
		PreparedStatement                 lps_ps;
		ResultSet                         lrs_rs;

		lcpc_plantillas     = new ArrayList<PlantillaComunicacion>();
		lps_ps              = null;
		lrs_rs              = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpc_plantillas.add(getObjectFromResultSetComplete(lrs_rs));
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

		if(lcpc_plantillas.isEmpty())
			lcpc_plantillas = null;

		return lcpc_plantillas;
	}

	/**
	 * Find by id.
	 *
	 * @param apc_param de as param
	 * @return el valor de plantilla comunicacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PlantillaComunicacion findById(PlantillaComunicacion apc_param)
	    throws B2BException
	{
		PlantillaComunicacion lpc_plantilla;

		lpc_plantilla = null;

		if(apc_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, apc_param.getIdPlantilla());
				lps_ps.setString(li_contador++, apc_param.getIdProceso());
				lps_ps.setLong(li_contador++, apc_param.getIdEtapaActual());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpc_plantilla = getObjectFromResultSet(lrs_rs);
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

		return lpc_plantilla;
	}

	/**
	 * Find by id plantilla comunicacion
	 *
	 * @param apc_param Objeto de tipo <code>PlantillaComunicacion</code> a buscar.
	 * @return Objeto de tipo <code>PlantillaComunicacion</code> con el resultado de la búsqueda.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public PlantillaComunicacion findByIdPlantillaComunicacion(PlantillaComunicacion apc_param)
	    throws B2BException
	{
		PlantillaComunicacion lpc_plantilla;

		lpc_plantilla = null;

		if(apc_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_PLANTILLA_COMUNICACION);

				lps_ps.setString(1, apc_param.getIdPlantillaComunicacion());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpc_plantilla = getObjectFromResultSetComplete(lrs_rs);
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

		return lpc_plantilla;
	}

	/**
	 * Método encargado de realizar el insert a la tabla
	 *
	 * @param apc_param Objeto de tipo <code>PlantillaComunicacion</code> a insertar.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public void insertComplete(PlantillaComunicacion apc_param)
	    throws B2BException
	{
		if(apc_param != null)
		{
			int               li_column;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			li_column         = 1;

			try
			{
				Connection lc_conexion;

				lc_conexion     = getConnection();

				lps_ps            = lc_conexion.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_conexion.prepareStatement(cs_FIND_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				lps_ps.setString(li_column++, apc_param.getIdPlantilla());
				lps_ps.setString(li_column++, apc_param.getIdProceso());
				lps_ps.setLong(li_column++, apc_param.getIdEtapaActual());
				lps_ps.setLong(li_column++, apc_param.getIdEtapaAnterior());
				lps_ps.setLong(li_column++, apc_param.getIdMotivo());
				lps_ps.setString(li_column++, apc_param.getIdSubProceso());
				lps_ps.setString(li_column++, apc_param.getTramite());
				lps_ps.setString(li_column++, apc_param.getClasificacion());
				lps_ps.setString(li_column++, apc_param.getCanal());
				lps_ps.setString(li_column++, apc_param.getIdAutorizacionComunicacion());
				lps_ps.setString(li_column++, apc_param.getActivo());
				lps_ps.setString(li_column++, apc_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, apc_param.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertComplete", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
				close(lps_secuencia);
			}
		}
	}

	/**
	 * Método encargado de realizar un Update en la tabla
	 *
	 * @param apc_param Objeto de tipo <code>PlantillaComunicacion</code> a actualizar.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public void updateComplete(PlantillaComunicacion apc_param)
	    throws B2BException
	{
		if(apc_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				Connection lc_conexion;
				int        li_column;

				lc_conexion     = getConnection();
				li_column       = 1;

				lps_ps = lc_conexion.prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, apc_param.getIdPlantilla());
				lps_ps.setString(li_column++, apc_param.getIdProceso());
				lps_ps.setLong(li_column++, apc_param.getIdEtapaActual());
				lps_ps.setLong(li_column++, apc_param.getIdEtapaAnterior());
				lps_ps.setLong(li_column++, apc_param.getIdMotivo());
				lps_ps.setString(li_column++, apc_param.getIdSubProceso());
				lps_ps.setString(li_column++, apc_param.getTramite());
				lps_ps.setString(li_column++, apc_param.getClasificacion());
				lps_ps.setString(li_column++, apc_param.getCanal());
				lps_ps.setString(li_column++, apc_param.getActivo());
				lps_ps.setString(li_column++, apc_param.getIdAutorizacionComunicacion());
				lps_ps.setString(li_column++, apc_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, apc_param.getIpModificacion());
				lps_ps.setString(li_column++, apc_param.getIdPlantillaComunicacion());

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
	 * Retorna Objeto o variable de valor PlantillaComunicacion.
	 *
	 * @param ars_rs de ResultSet
	 * @return el valor de PlantillaComunicacion
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private PlantillaComunicacion getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		PlantillaComunicacion lpc_plantilla;

		lpc_plantilla = new PlantillaComunicacion();

		lpc_plantilla.setIdPlantilla(ars_rs.getString("ID_PLANTILLA"));
		lpc_plantilla.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lpc_plantilla.setIdEtapaActual(ars_rs.getLong("ID_ETAPA_ACTUAL"));
		lpc_plantilla.setIdSubProceso(ars_rs.getString("ID_SUBPROCESO"));
		lpc_plantilla.setTramite(ars_rs.getString("TRAMITE"));
		lpc_plantilla.setClasificacion(ars_rs.getString("CLASIFICACION"));
		lpc_plantilla.setCanal(ars_rs.getString("CANAL"));
		lpc_plantilla.setIdAutorizacionComunicacion(ars_rs.getString("ID_AUTORIZACION_COMUNICACION"));

		fillAuditoria(ars_rs, lpc_plantilla);

		return lpc_plantilla;
	}

	/**
	 * Retorna Objeto o variable de valor <code>PlantillaComunicacion</code>.
	 *
	 * @param ars_rs  Objeto de tipo <code>ResultSet</code> con la informacion de la plantilla comunicación.
	 * @return Objeto de tipo <code>PlantillaComunicacion</code> con la información establecida.
	 * @throws SQLException cuando se produce algun error en el proceso.
	 */
	private PlantillaComunicacion getObjectFromResultSetComplete(ResultSet ars_rs)
	    throws SQLException
	{
		PlantillaComunicacion lpc_plantilla;

		lpc_plantilla = new PlantillaComunicacion();

		lpc_plantilla.setIdPlantillaComunicacion(ars_rs.getString("ID_PLANTILLA_COMUNICACION"));
		lpc_plantilla.setIdPlantilla(ars_rs.getString("ID_PLANTILLA"));
		lpc_plantilla.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lpc_plantilla.setIdEtapaActual(ars_rs.getLong("ID_ETAPA_ACTUAL"));
		lpc_plantilla.setIdEtapaAnterior(ars_rs.getLong("ID_ETAPA_ANTERIOR"));
		lpc_plantilla.setIdMotivo(ars_rs.getLong("ID_MOTIVO_ANTERIOR"));
		lpc_plantilla.setIdAutorizacionComunicacion(ars_rs.getString("ID_AUTORIZACION_COMUNICACION"));
		lpc_plantilla.setIdSubProceso(ars_rs.getString("ID_SUBPROCESO"));
		lpc_plantilla.setTramite(ars_rs.getString("TRAMITE"));
		lpc_plantilla.setClasificacion(ars_rs.getString("CLASIFICACION"));
		lpc_plantilla.setCanal(ars_rs.getString("CANAL"));
		lpc_plantilla.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lpc_plantilla);

		return lpc_plantilla;
	}
}
