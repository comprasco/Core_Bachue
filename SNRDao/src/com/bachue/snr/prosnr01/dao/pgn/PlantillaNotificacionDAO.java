package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.PlantillaNotificacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades PlantillaNotificacionDAO.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 14/04/2020
 */
public class PlantillaNotificacionDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = " SELECT * FROM SDB_PGN_PLANTILLA_NOTIFICACION WHERE ID_PROCESO = ? AND ID_ETAPA_ANTERIOR = ? AND ID_MOTIVO = ?";

	/** Constante  cs_FIND_BY_IDS. */
	private static final String cs_FIND_BY_IDS = " SELECT * FROM SDB_PGN_PLANTILLA_NOTIFICACION WHERE ID_PROCESO = ? AND ID_ETAPA_ANTERIOR = ? AND ID_MOTIVO = ? AND ID_PLANTILLA = ?";

	/** Contante cs_FIND_ALL*/
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_PLANTILLA_NOTIFICACION";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_PLANTILLA_NOTIFICACION(ID_PROCESO, ID_ETAPA_ANTERIOR, ID_MOTIVO, ID_PLANTILLA, TRAMITE, CLASIFICACION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_PLANTILLA_NOTIFICACION SET TRAMITE=?, CLASIFICACION=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_PROCESO=? AND ID_ETAPA_ANTERIOR=? AND ID_MOTIVO=? AND ID_PLANTILLA=?";

	/**
	 * Método encargado de consultar en base de datos todos los registros que se encuentren
	 * @return Colección de tipo <code>Plantilla</code> resultante de la consulta
	 * @throws B2BException  Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PlantillaNotificacion> findAll()
	    throws B2BException
	{
		Collection<PlantillaNotificacion> lc_plantillas;
		PreparedStatement                 lps_statement;
		ResultSet                         lrs_resultado;

		lc_plantillas     = new ArrayList<PlantillaNotificacion>();
		lps_statement     = null;
		lrs_resultado     = null;

		try
		{
			StringBuilder lsb_consulta;

			lsb_consulta = new StringBuilder();

			lsb_consulta.append(cs_FIND_ALL);

			lps_statement     = getConnection().prepareStatement(lsb_consulta.toString());

			lrs_resultado = lps_statement.executeQuery();

			while(lrs_resultado.next())
				lc_plantillas.add(getPlantillaNotificacion(lrs_resultado));
		}
		catch(SQLException lse_excepcion)
		{
			logError(this, "findAll", lse_excepcion);
			throw new B2BException(SQL_ERROR, lse_excepcion);
		}
		finally
		{
			close(lps_statement);
			close(lrs_resultado);
		}

		if(lc_plantillas.isEmpty())
			lc_plantillas = null;

		return lc_plantillas;
	}

	/**
	 * Método encargado de consultar en base de datos una plantilla notificacion por Id
	 *
	 * @param as_proceso de as proceso
	 * @param al_etapaAnterior de al etapa anterior
	 * @param al_motivo de al motivo
	 * @return el valor de plantilla notificacion
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public PlantillaNotificacion findById(String as_proceso, long al_etapaAnterior, long al_motivo)
	    throws B2BException
	{
		PlantillaNotificacion lpn_plantillaNotificacion;

		lpn_plantillaNotificacion = null;

		if(
		    (as_proceso != null) && (al_etapaAnterior > NumericUtils.DEFAULT_LONG_VALUE)
			    && (al_motivo > NumericUtils.DEFAULT_LONG_VALUE)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_proceso);
				lps_ps.setLong(2, al_etapaAnterior);
				lps_ps.setLong(3, al_motivo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpn_plantillaNotificacion = getPlantillaNotificacion(lrs_rs);
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

		return lpn_plantillaNotificacion;
	}

	/**
	 * Método encargado de consultar en base de datos una plantilla notificacion por Id
	 *
	 * @param as_proceso de as proceso
	 * @param al_etapaAnterior de al etapa anterior
	 * @param al_motivo de al motivo
	 * @return el valor de plantilla notificacion
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public PlantillaNotificacion findByIds(
	    String as_proceso, long al_etapaAnterior, long al_motivo, String as_plantilla
	)
	    throws B2BException
	{
		PlantillaNotificacion lpn_plantillaNotificacion;

		lpn_plantillaNotificacion = null;

		if(
		    (as_proceso != null) && (al_etapaAnterior > NumericUtils.DEFAULT_LONG_VALUE)
			    && ((al_motivo > NumericUtils.DEFAULT_LONG_VALUE) && StringUtils.isValidString(as_plantilla))
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_IDS);

				lps_ps.setString(1, as_proceso);
				lps_ps.setLong(2, al_etapaAnterior);
				lps_ps.setLong(3, al_motivo);
				lps_ps.setString(4, as_plantilla);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpn_plantillaNotificacion = getPlantillaNotificacion(lrs_rs);
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

		return lpn_plantillaNotificacion;
	}

	/**
	 * Inserta un registro de plantilla notificacion con la información suministrada.
	 *
	 * @param ap_param objeto de tipo <code>PlantillaNotificacion</code> contenedor de la información a insertar.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(PlantillaNotificacion ap_param)
	    throws B2BException
	{
		if(ap_param != null)
		{
			int               li_column;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, ap_param.getIdProceso());
				lps_ps.setLong(li_column++, ap_param.getIdEtapaAnterior());
				lps_ps.setLong(li_column++, ap_param.getIdMotivo());
				lps_ps.setString(li_column++, ap_param.getIdPlantilla());
				lps_ps.setString(li_column++, ap_param.getTramite());
				lps_ps.setString(li_column++, ap_param.getClasificacion());
				lps_ps.setString(li_column++, ap_param.getActivo());
				lps_ps.setString(li_column++, ap_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, ap_param.getIpCreacion());

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
				close(lrs_rs);
			}
		}
	}

	/**
	 * Actualiza un registro de plantilla notificacion con la información suministrada.
	 *
	 * @param ap_param objeto de tipo <code>PlantillaNotificacion</code> contenedor de la información a insertar.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public void update(PlantillaNotificacion ap_param)
	    throws B2BException
	{
		if(ap_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;
				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, ap_param.getTramite());
				lps_ps.setString(li_column++, ap_param.getClasificacion());
				lps_ps.setString(li_column++, ap_param.getActivo());
				lps_ps.setString(li_column++, ap_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ap_param.getIpModificacion());
				lps_ps.setString(li_column++, ap_param.getIdProceso());
				lps_ps.setLong(li_column++, ap_param.getIdEtapaAnterior());
				lps_ps.setLong(li_column++, ap_param.getIdMotivo());
				lps_ps.setString(li_column++, ap_param.getIdPlantilla());

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
	 * Retorna Objeto o variable de valor plantilla notificacion.
	 *
	 * @param ars_resultSet Objeto de tipo <code>ResultSet</code>
	 * @return Objeto de tipo <code>PlantillaNotificacion</code> con los datos especificados.
	 * @throws SQLException cuando se produce algún error en el proceso
	 */
	private PlantillaNotificacion getPlantillaNotificacion(ResultSet ars_resultSet)
	    throws SQLException
	{
		PlantillaNotificacion lpn_plantillaNotificacion;

		lpn_plantillaNotificacion = new PlantillaNotificacion();

		lpn_plantillaNotificacion.setIdProceso(ars_resultSet.getString("ID_PROCESO"));
		lpn_plantillaNotificacion.setIdEtapaAnterior(ars_resultSet.getLong("ID_ETAPA_ANTERIOR"));
		lpn_plantillaNotificacion.setIdMotivo(ars_resultSet.getLong("ID_MOTIVO"));
		lpn_plantillaNotificacion.setIdPlantilla(ars_resultSet.getString("ID_PLANTILLA"));
		lpn_plantillaNotificacion.setTramite(ars_resultSet.getString("TRAMITE"));
		lpn_plantillaNotificacion.setClasificacion(ars_resultSet.getString("CLASIFICACION"));
		lpn_plantillaNotificacion.setActivo(ars_resultSet.getString("ACTIVO"));

		fillAuditoria(ars_resultSet, lpn_plantillaNotificacion);

		return lpn_plantillaNotificacion;
	}
}
