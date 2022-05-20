package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudVisitas;

import com.bachue.snr.prosnr21.model.pgn.ProcesoConciliacion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Clase que contiene todos las sentencias de la tabla
 * SDB_ACC_SOLICITUD_VISITAS.
 *
 * @author arocha
 */
public class SolicitudVisitasDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_SOLICITUD_VISITAS WHERE ID_SOLICITUD_VISITAS = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_SOLICITUD_VISITAS WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD_WITH_PERSONA = "SELECT CR.NOMBRE AS ID_CIRCULO,SP.NOMBRE AS ID_SUBPROCESO,SV.FECHA_DESDE,SV.FECHA_HASTA, P.PRIMER_NOMBRE,P.SEGUNDO_NOMBRE,P.PRIMER_APELLIDO,P.SEGUNDO_APELLIDO,SV.ID_DEPENDENCIA FROM SDB_ACC_SOLICITUD_VISITAS SV INNER JOIN SDB_ACC_SOLICITUD S ON SV.ID_SOLICITUD=S.ID_SOLICITUD INNER JOIN SDB_ACC_SOLICITUD_INTERVINIENTE SI ON S.ID_SOLICITUD=SI.ID_SOLICITUD INNER JOIN SDB_ACC_PERSONA P ON SI.ID_PERSONA=P.ID_PERSONA "
		+ "INNER JOIN SDB_ACC_SUBPROCESO SP ON SP.ID_SUBPROCESO=S.ID_SUBPROCESO AND SP.ID_PROCESO='62' INNER JOIN SDB_PGN_CIRCULO_REGISTRAL CR ON CR.ID_CIRCULO = SV.ID_CIRCULO WHERE SV.ID_SOLICITUD = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_SOLICITUD_VISITAS ORDER BY ID_SOLICITUD_VISITAS ASC";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_SOLICITUD_VISITAS_ID_SOLICITUD_VISITAS.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SOLICITUD_VISITAS (ID_SOLICITUD_VISITAS,"
		+ "ID_SOLICITUD,ID_DEPENDENCIA,FECHA_DESDE,FECHA_HASTA,ID_CIRCULO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES "
		+ " (?,?,?,?,?,?,?,SYSTIMESTAMP,?) ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SOLICITUD_VISITAS SET "
		+ "ID_SOLICITUD = ?,ID_DEPENDENCIA = ?,FECHA_DESDE = ?,FECHA_HASTA = ?,ID_CIRCULO = ?,ID_USUARIO_MODIFICACION = ?,FECHA_MODIFICACION = SYSTIMESTAMP,"
		+ "IP_MODIFICACION = ? WHERE ID_SOLICITUD = ?";

	/** Constante cs_DATOS_PANEL_SOLICITUD_VISITAS. */
	private static final String cs_DATOS_PANEL_SOLICITUD_VISITAS = "SELECT ASV.ID_CIRCULO,SPCR.NOMBRE AS NOMBRE_CIRCULO, SASP.ID_SUBPROCESO, SASP.NOMBRE, ASV.FECHA_DESDE, ASV.FECHA_HASTA, SAPS.PRIMER_NOMBRE, SAPS.SEGUNDO_NOMBRE, "
		+ "SAPS.PRIMER_APELLIDO, SAPS.SEGUNDO_APELLIDO, SPDS.NOMBRE_DEPENDENCIA, SPDS.ID_DEPENDENCIA "
		+ "FROM SDB_ACC_SOLICITUD_VISITAS ASV "
		+ "INNER JOIN SDB_ACC_SOLICITUD SAS ON SAS.ID_SOLICITUD = ASV.ID_SOLICITUD "
		+ "INNER JOIN SDB_ACC_PROCESO SAP ON SAS.ID_PROCESO = SAP.ID_PROCESO "
		+ "INNER JOIN SDB_ACC_SUBPROCESO SASP ON SAP.ID_PROCESO = SASP.ID_PROCESO AND SAS.ID_SUBPROCESO = SASP.ID_SUBPROCESO "
		+ "INNER JOIN SDB_ACC_SOLICITUD_INTERVINIENTE SASI ON SASI.ID_SOLICITUD = SAS.ID_SOLICITUD "
		+ "INNER JOIN SDB_ACC_PERSONA SAPS ON SASI.ID_PERSONA = SAPS.ID_PERSONA "
		+ "LEFT JOIN SDB_PGN_DEPENDENCIA_SNR SPDS ON SASI.ID_DEPENDENCIA = SPDS.ID_DEPENDENCIA "
		+ "INNER JOIN SDB_PGN_CIRCULO_REGISTRAL SPCR ON SPCR.ID_CIRCULO = ASV.ID_CIRCULO "
		+ "WHERE ASV.ID_SOLICITUD = ?";

	/**
	 * Instancia un nuevo objeto solicitud visitas DAO.
	 */
	public SolicitudVisitasDAO()
	{
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de SolicitudVisitas con
	 * todos los registros.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException             Señala que se ha producido una excepción
	 * @see ProcesoConciliacion
	 */
	public Collection<SolicitudVisitas> findAll()
	    throws B2BException
	{
		Collection<SolicitudVisitas> lsv_solicitud;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lsv_solicitud     = new ArrayList<SolicitudVisitas>();
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lsv_solicitud.add(getSolicitudVisitas(lrs_rs));
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

		if(lsv_solicitud.isEmpty())
			lsv_solicitud = null;

		return lsv_solicitud;
	}

	public SolicitudVisitas findById(String as_id)
	    throws B2BException
	{
		SolicitudVisitas  lsv_solicitud;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lsv_solicitud     = null;
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setObject(li_contador++, as_id);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lsv_solicitud = getSolicitudVisitas(lrs_rs);
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

		return lsv_solicitud;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de SolicitudVisitas con
	 * todos los registros asociados a un id solicitud .
	 *
	 * @param as_idSolicitud de Argumento de tipo <code>String</code> que contiene el id de la solicitud.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudVisitas> findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<SolicitudVisitas> lp_proceso;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lp_proceso     = new ArrayList<SolicitudVisitas>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

			lps_ps.setObject(li_contador++, as_idSolicitud);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_proceso.add(getSolicitudVisitas(lrs_rs));
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

		if(lp_proceso.isEmpty())
			lp_proceso = null;

		return lp_proceso;
	}

	/**
	 * Retorna el valor del objeto de tipo List de Map con
	 * todos los registros asociados a un id solicitud y el nombre de la persona que la realiza .
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el id de la solicitud.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public List<Map<String, Object>> findByIdSolicitudWithPersona(String as_idSolicitud)
	    throws B2BException
	{
		List<Map<String, Object>> lcmso_proceso;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lcmso_proceso     = new LinkedList<Map<String, Object>>();
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_WITH_PERSONA);

			lps_ps.setObject(li_contador++, as_idSolicitud);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcmso_proceso.add(getMap(lrs_rs));
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

		if(lcmso_proceso.isEmpty())
			lcmso_proceso = null;

		return lcmso_proceso;
	}

	/**
	 * Find datos panel solicitud visitas.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudVisitas> findDatosPanelSolicitudVisitas(String as_idSolicitud)
	    throws B2BException
	{
		Collection<SolicitudVisitas> lsv_solicitud;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lsv_solicitud     = null;
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			int li_contador;

			li_contador     = 1;
			lps_ps          = getConnection().prepareStatement(cs_DATOS_PANEL_SOLICITUD_VISITAS);

			lps_ps.setObject(li_contador++, as_idSolicitud);

			lrs_rs            = lps_ps.executeQuery();
			lsv_solicitud     = new ArrayList<SolicitudVisitas>(1);

			while(lrs_rs.next())
				lsv_solicitud.add(getDatosPanelSolicitudVisitas(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDatosPanelSolicitudVisitas", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lsv_solicitud;
	}

	/**
	 * Inserta o actualiza el registro en la tabla.
	 *
	 * @param asv_parametros            correspondiente al valor del tipo de objeto SolicitudVisitas
	 * @param ab_insertar            true si el registro se debe insertar. false si el registro se debe actualizar
	 * @return el valor de solicitud visitas
	 * @throws B2BException             Señala que se ha producido una excepción
	 */
	public SolicitudVisitas insertOrUpdate(SolicitudVisitas asv_parametros, boolean ab_insertar)
	    throws B2BException
	{
		if(asv_parametros != null)
		{
			PreparedStatement lps_sentencia;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_secuencia;

			lps_sentencia     = null;
			lps_secuencia     = null;
			lrs_secuencia     = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				lc_connection     = getConnection();
				li_column         = 1;
				lps_sentencia     = lc_connection.prepareStatement(ab_insertar ? cs_INSERT : cs_UPDATE);

				if(ab_insertar)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);
					lrs_secuencia     = lps_secuencia.executeQuery();

					if(lrs_secuencia.next())
					{
						Object lo_o;

						lo_o = lrs_secuencia.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							asv_parametros.setIdSolicitudVisitas(((BigDecimal)lo_o).toString());
						else
							throw new B2BException(ErrorKeys.PGN_PROCESO_CONCILIACION_SEQUENCE);
					}

					lps_sentencia.setString(li_column++, asv_parametros.getIdSolicitudVisitas());
				}

				lps_sentencia.setString(li_column++, asv_parametros.getIdSolicitud());
				lps_sentencia.setString(li_column++, asv_parametros.getIdDependencia());
				lps_sentencia.setTimestamp(li_column++, DateUtils.getTimestamp(asv_parametros.getFechaDesde()));
				lps_sentencia.setTimestamp(li_column++, DateUtils.getTimestamp(asv_parametros.getFechaHasta()));
				lps_sentencia.setString(li_column++, asv_parametros.getIdCirculo());

				if(ab_insertar)
				{
					lps_sentencia.setString(li_column++, asv_parametros.getIdUsuarioCreacion());
					lps_sentencia.setString(li_column++, asv_parametros.getIpCreacion());
				}
				else
				{
					lps_sentencia.setString(li_column++, asv_parametros.getIdUsuarioModificacion());
					lps_sentencia.setString(li_column++, asv_parametros.getIpModificacion());
					lps_sentencia.setString(li_column++, asv_parametros.getIdSolicitud());
				}

				lps_sentencia.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_sentencia);

				if(ab_insertar)
				{
					close(lps_secuencia);
					close(lrs_secuencia);
				}
			}
		}

		return asv_parametros;
	}

	/**
	 * Retorna Objeto o variable de valor datos panel solicitud visitas.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de datos panel solicitud visitas
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private SolicitudVisitas getDatosPanelSolicitudVisitas(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudVisitas lsv_solicitud;

		lsv_solicitud = new SolicitudVisitas();

		lsv_solicitud.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lsv_solicitud.setIdSubProceso(ars_rs.getString("ID_SUBPROCESO"));
		lsv_solicitud.setNombreSubProceso(ars_rs.getString("NOMBRE"));
		lsv_solicitud.setFechaDesde(ars_rs.getTimestamp("FECHA_DESDE"));
		lsv_solicitud.setFechaHasta(ars_rs.getTimestamp("FECHA_HASTA"));
		lsv_solicitud.setNombreUsuario(
		    getNombreCompleto(
		        ars_rs.getString("PRIMER_NOMBRE"), ars_rs.getString("SEGUNDO_NOMBRE"),
		        ars_rs.getString("PRIMER_APELLIDO"), ars_rs.getString("SEGUNDO_APELLIDO")
		    )
		);
		lsv_solicitud.setIdDependencia(ars_rs.getString("ID_DEPENDENCIA"));
		lsv_solicitud.setNombreDependencia(ars_rs.getString("NOMBRE_DEPENDENCIA"));
		lsv_solicitud.setNombreCirculo(ars_rs.getString("NOMBRE_CIRCULO"));

		return lsv_solicitud;
	}

	/**
	 * Retorna Objeto o variable de valor map.
	 *
	 *@param ars_rs
	 *            correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de proceso conciliación.
	 * @throws SQLException
	 *             Señala que se ha producido una excepción
	 */
	private Map<String, Object> getMap(ResultSet ars_rs)
	    throws SQLException
	{
		Map<String, Object> lmsc_datos;
		StringBuilder       ls_nombre;

		lmsc_datos     = new HashMap<String, Object>();
		ls_nombre      = new StringBuilder();

		{
			String[] lls_nombres;

			lls_nombres = new String[]
				{
					ars_rs.getString("PRIMER_NOMBRE"), ars_rs.getString("SEGUNDO_NOMBRE"),
					ars_rs.getString("PRIMER_APELLIDO"), ars_rs.getString("SEGUNDO_APELLIDO")
				};

			for(String ls_iterador : lls_nombres)
			{
				if(ls_iterador != null)
					ls_nombre.append(ls_iterador);

				ls_nombre.append(" ");
			}
		}

		lmsc_datos.put("ORIP", ars_rs.getString("ID_CIRCULO"));
		lmsc_datos.put("TIPO VISITA", ars_rs.getString("ID_SUBPROCESO"));
		lmsc_datos.put("FECHA DESDE", ars_rs.getTimestamp("FECHA_DESDE"));
		lmsc_datos.put("FECHA HASTA", ars_rs.getTimestamp("FECHA_HASTA"));
		lmsc_datos.put("NOMBRE", ls_nombre.toString());
		lmsc_datos.put("DEPENDENCIA", ars_rs.getString("ID_DEPENDENCIA"));

		return lmsc_datos;
	}

	/**
	 * Retorna el valor de la solicitud de visitas.
	 *
	 * @param ars_rs
	 *            correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de proceso conciliación.
	 * @throws SQLException
	 *             Señala que se ha producido una excepción
	 */
	private SolicitudVisitas getSolicitudVisitas(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudVisitas lsv_solicitud;

		lsv_solicitud = new SolicitudVisitas();

		lsv_solicitud.setIdSolicitudVisitas(ars_rs.getString("ID_SOLICITUD_VISITAS"));
		lsv_solicitud.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lsv_solicitud.setIdDependencia(ars_rs.getString("ID_DEPENDENCIA"));
		lsv_solicitud.setFechaDesde(ars_rs.getTimestamp("FECHA_DESDE"));
		lsv_solicitud.setFechaHasta(ars_rs.getTimestamp("FECHA_HASTA"));
		lsv_solicitud.setIdCirculo(ars_rs.getString("ID_CIRCULO"));

		fillAuditoria(ars_rs, lsv_solicitud);

		return lsv_solicitud;
	}
}
