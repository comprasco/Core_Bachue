package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.AlertaTurnoTramite;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para poder realizar transacciones con la base de datos en la tabla SDB_ACC_ALERTA_TURNO_TRAMITE.
 *
 * @author Alejandro Santos
 */
public class AlertaTurnoTramiteDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = " SELECT ATT.ACTIVO,PAT.DESCRIPCION NOMBRE_ALERTA,ATT.ID_ALERTA_TRAMITE,ATT.ID_ALERTA_TURNO,ATT.ID_TURNO_AFECTADO,ATT.ID_TURNO_ASOCIADO,ATT.ID_SOLICITUD,ATT.ID_SOLICITUD_VINCULADA,ATT.FECHA_INACTIVACION,ATT.FECHA_CREACION "
		+ " FROM SDB_ACC_ALERTA_TURNO_TRAMITE ATT  INNER JOIN SDB_PGN_ALERTA_TRAMITE PAT ON PAT.ID_ALERTA_TRAMITE = ATT.ID_ALERTA_TRAMITE WHERE ID_TURNO_AFECTADO=? ";

	/** Constante cs_FIND_BY_ID_TURNO_ASOCIADO. */
	private static final String cs_FIND_BY_ID_TURNO_ASOCIADO = " SELECT ATT.ACTIVO,PAT.DESCRIPCION NOMBRE_ALERTA,ATT.ID_ALERTA_TRAMITE,ATT.ID_ALERTA_TURNO,ATT.ID_TURNO_AFECTADO,ATT.ID_TURNO_ASOCIADO,ATT.ID_SOLICITUD,ATT.ID_SOLICITUD_VINCULADA,ATT.FECHA_INACTIVACION,ATT.FECHA_CREACION "
		+ " FROM SDB_ACC_ALERTA_TURNO_TRAMITE ATT  INNER JOIN SDB_PGN_ALERTA_TRAMITE PAT ON PAT.ID_ALERTA_TRAMITE = ATT.ID_ALERTA_TRAMITE WHERE ID_TURNO_ASOCIADO=? ";

	/** Constante cs_FIND_BY_ID_TURNO_CIR_ACTIVO. */
	private static final String cs_FIND_BY_ID_TURNO_CIR_ACTIVO = "SELECT ATT.ACTIVO, PAT.DESCRIPCION NOMBRE_ALERTA, ATT.ID_ALERTA_TRAMITE, ATT.ID_ALERTA_TURNO, ATT.ID_TURNO_AFECTADO, ATT.ID_TURNO_ASOCIADO, ATT.ID_SOLICITUD, ATT.ID_SOLICITUD_VINCULADA, ATT.FECHA_INACTIVACION, ATT.FECHA_CREACION FROM SDB_ACC_ALERTA_TURNO_TRAMITE ATT INNER JOIN SDB_PGN_ALERTA_TRAMITE PAT ON PAT.ID_ALERTA_TRAMITE = ATT.ID_ALERTA_TRAMITE LEFT JOIN SDB_ACC_TURNO SAT ON ATT.ID_TURNO_ASOCIADO = SAT.ID_TURNO LEFT JOIN SDB_ACC_TURNO SAT2 ON ATT.ID_TURNO_AFECTADO = SAT2.ID_TURNO WHERE ID_TURNO_AFECTADO = ? AND ATT.ACTIVO = 'S'";

	/** Constante cs_FIND_BY_ID_SOLICITUD_VINCULADA. */
	private static final String cs_FIND_BY_ID_SOLICITUD_VINCULADA = "SELECT * FROM SDB_ACC_ALERTA_TURNO_TRAMITE WHERE ID_SOLICITUD_VINCULADA = ? AND ACTIVO = 'S' ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_ALERTA_TURNO_TRAMITE(ID_ALERTA_TURNO ,ID_ALERTA_TRAMITE,ID_TURNO_ASOCIADO,ACTIVO,ID_TURNO_AFECTADO,ID_SOLICITUD,ID_SOLICITUD_VINCULADA,ID_USUARIO_CREACION,IP_CREACION,FECHA_CREACION ) VALUES(?,?,?,?,?,?,?,?,?,SYSTIMESTAMP)";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_ALERTA_TURNO_TRAMITE_ID_ALERTA_TURNO.NEXTVAL FROM DUAL";

	/**
	 * Método para encontrar todos los registros AlertaTurnoTramite por el idTurno y el circulo del turno.
	 *
	 * @param as_idTurno String para filtrar en la BD
	 * @param as_matriculasRelacionadas String para agregarla a cada registro de la consulta de BD
	 * @return Colleccion de AlertaTurnoTramite
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AlertaTurnoTramite> findByIdTurnoCirculoActivo(
	    String as_idTurno, String as_matriculasRelacionadas
	)
	    throws B2BException
	{
		Collection<AlertaTurnoTramite> lcatt_object;

		lcatt_object = new ArrayList<AlertaTurnoTramite>();

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_CIR_ACTIVO);

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcatt_object.add(getObjectFromResultSet(lrs_rs, as_matriculasRelacionadas));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoCirculoActivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcatt_object.isEmpty())
			lcatt_object = null;

		return lcatt_object;
	}

	/**
	 * Método para encontrar todos los registros AlertaTurnoTramite por el idTurno.
	 *
	 * @param as_idTurno String para filtrar en la BD
	 * @param as_matriculasRelacionadas String para agregarla a cada registro de la consulta de BD
	 * @return Colleccion de AlertaTurnoTramite
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AlertaTurnoTramite> findByIdTurno(String as_idTurno, String as_matriculasRelacionadas)
	    throws B2BException
	{
		Collection<AlertaTurnoTramite> lcatt_object;

		lcatt_object = new ArrayList<AlertaTurnoTramite>();

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcatt_object.add(getObjectFromResultSet(lrs_rs, as_matriculasRelacionadas));
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
		}

		if(lcatt_object.isEmpty())
			lcatt_object = null;

		return lcatt_object;
	}

	/**
	 * Busca el primer registro que coincida para una solicitud vinculada y que se encuentre activo
	 *
	 * @param as_idSolicitudVinculada id de la solicitud a utilizar como filtro en la busqueda
	 * @return AlertaTurnoTramite resultante de la busqueda
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public AlertaTurnoTramite findFirstByIdSolicitudVinculada(String as_idSolicitudVinculada)
	    throws B2BException
	{
		AlertaTurnoTramite latt_return;

		latt_return = null;

		if(StringUtils.isValidString(as_idSolicitudVinculada))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_VINCULADA);

				lps_ps.setString(1, as_idSolicitudVinculada);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					latt_return = getAlertaTurnoTramite(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findFirstByIdSolicitudVinculada", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return latt_return;
	}

	/**
	 * Método para encontrar todos los registros AlertaTurnoTramite por el idTurno y estado activo.
	 *
	 * @param as_idTurno String para filtrar en la BD
	 * @return AlertaTurnoTramite Resutante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public AlertaTurnoTramite findByIdTurnoAsociadoActivo(String as_idTurno)
	    throws B2BException
	{
		AlertaTurnoTramite lcatt_return;

		lcatt_return = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder(cs_FIND_BY_ID_TURNO_ASOCIADO);

				lsb_query.append(" AND ATT.ACTIVO = 'S' ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcatt_return = getObjectFromResultSet(lrs_rs, null);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoEstadoActivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcatt_return;
	}

	/**
	 * Método para encontrar todos los registros AlertaTurnoTramite por el idTurno y estado activo.
	 *
	 * @param as_idTurno String para filtrar en la BD
	 * @return Colleccion de AlertaTurnoTramite
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<AlertaTurnoTramite> findByIdTurnoEstadoActivo(String as_idTurno)
	    throws B2BException
	{
		Collection<AlertaTurnoTramite> lcatt_return;

		lcatt_return = new ArrayList<AlertaTurnoTramite>();

		if(StringUtils.isValidString(as_idTurno))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder(cs_FIND_BY_ID);

				lsb_query.append(" AND ATT.ACTIVO = 'S' ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcatt_return.add(getObjectFromResultSet(lrs_rs, null));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoEstadoActivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcatt_return.isEmpty())
			lcatt_return = null;

		return lcatt_return;
	}

	/**
	 * Método para insertar una alertaTurnoTramite en la BD.
	 *
	 * @param aatt_param objeto AlertaTurnoTramite con información a insertar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertarAlerta(AlertaTurnoTramite aatt_param)
	    throws B2BException
	{
		if(aatt_param != null)
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

				lc_connection     = getConnection();

				li_column         = 1;
				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					Object lo_o;

					lo_o = lrs_rs.getObject(1);

					if((lo_o != null) && lo_o instanceof BigDecimal)
						lps_ps.setString(li_column++, ((BigDecimal)lo_o).toString());
					else
						throw new B2BException(ErrorKeys.SIN_SECUENCIA_ALERTAS);
				}

				lps_ps.setString(li_column++, aatt_param.getIdAlertaTramite());
				lps_ps.setString(li_column++, aatt_param.getIdTurnoAsociado());
				lps_ps.setString(li_column++, aatt_param.getActivo());
				lps_ps.setString(li_column++, aatt_param.getIdTurnoAfectado());
				lps_ps.setString(li_column++, aatt_param.getIdSolicitud());
				lps_ps.setString(li_column++, aatt_param.getIdSolicitudVinculada());
				lps_ps.setString(li_column++, aatt_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aatt_param.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertarAlerta", lse_e);

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
	 * Método para extraer la información de la consulta a la BD.
	 *
	 * @param ars_rs objeto con informacion de la consulta a la BD
	 * @param as_matriculasRelacionadas String para agregar al objeto AlertaTurnoTramite
	 * @return objeto AlertaTurnoTramite
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AlertaTurnoTramite getObjectFromResultSet(ResultSet ars_rs, String as_matriculasRelacionadas)
	    throws SQLException
	{
		AlertaTurnoTramite latt_tmp;

		latt_tmp = new AlertaTurnoTramite();

		latt_tmp.setActivo(ars_rs.getString("ACTIVO"));
		latt_tmp.setIdAlertaTramite(ars_rs.getString("ID_ALERTA_TRAMITE"));
		latt_tmp.setIdAlertaTurno(ars_rs.getString("ID_ALERTA_TURNO"));
		latt_tmp.setIdTurnoAfectado(ars_rs.getString("ID_TURNO_AFECTADO"));
		latt_tmp.setIdTurnoAsociado(ars_rs.getString("ID_TURNO_ASOCIADO"));
		latt_tmp.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		latt_tmp.setIdSolicitudVinculada(ars_rs.getString("ID_SOLICITUD_VINCULADA"));
		latt_tmp.setFechaInactivacion(ars_rs.getTimestamp("FECHA_INACTIVACION"));
		latt_tmp.setDescripcionAlerta(ars_rs.getString("NOMBRE_ALERTA"));
		latt_tmp.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		latt_tmp.setMatriculasRelacionadas(StringUtils.getStringNotNull(as_matriculasRelacionadas));

		return latt_tmp;
	}

	/**
	 * Extrae los datos consultados y los almacena en un objeto AlertaTurnoTramite
	 *
	 * @param ars_rs Objeto contenedor de los datos consultados
	 * @return AlertaTurnoTramite resultante de la consulta
	 * @throws SQLException Si ocurre un error en base de datos
	 */
	private AlertaTurnoTramite getAlertaTurnoTramite(ResultSet ars_rs)
	    throws SQLException
	{
		AlertaTurnoTramite latt_tmp;

		latt_tmp = new AlertaTurnoTramite();

		latt_tmp.setIdAlertaTurno(ars_rs.getString("ID_ALERTA_TURNO"));
		latt_tmp.setIdAlertaTramite(ars_rs.getString("ID_ALERTA_TRAMITE"));
		latt_tmp.setActivo(ars_rs.getString("ACTIVO"));
		latt_tmp.setIdTurnoAfectado(ars_rs.getString("ID_TURNO_AFECTADO"));
		latt_tmp.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		latt_tmp.setIdSolicitudVinculada(ars_rs.getString("ID_SOLICITUD_VINCULADA"));
		latt_tmp.setIdTurnoAsociado(ars_rs.getString("ID_TURNO_ASOCIADO"));
		latt_tmp.setFechaInactivacion(ars_rs.getTimestamp("FECHA_INACTIVACION"));

		fillAuditoria(ars_rs, latt_tmp);

		return latt_tmp;
	}
}
