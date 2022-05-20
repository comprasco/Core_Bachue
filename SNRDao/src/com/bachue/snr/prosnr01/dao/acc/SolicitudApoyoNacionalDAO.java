package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudApoyoNacional;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades SolicitudApoyoNacionalDAO.
 *
 * @author  Luis Chacón
 * Fecha de Creacion: 15/09/2020
 */
public class SolicitudApoyoNacionalDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_SOLICITUD_APOYO_NACIONAL  WHERE ID_SOLICITUD_APOYO_NACIONAL = ?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_SOLICITUD_APOYO_NACIONAL WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_BY_NIR. */
	private static final String cs_FIND_BY_NIR = "SELECT SA.ID_SOLICITUD_APOYO_NACIONAL, SA.ID_SOLICITUD, SA.ID_CIRCULO_SOLICITANTE, SA.ID_TURNO, SA.ID_SOLICITUD_REGISTRO, SA.ID_TURNO_REGISTRO, SA.ID_TURNO_HISTORIA_REGISTRO, SA.ID_CIRCULO_ASIGNADO, SA.ID_USUARIO_ASIGNADO, SA.FECHA_ASIGNACION, SA.REASIGNACION_EXITOSA, SA.OBSERVACION, "
		+ "SA.ID_USUARIO_CREACION, SA.FECHA_CREACION, SA.IP_CREACION, SA.ID_USUARIO_MODIFICACION, SA.FECHA_MODIFICACION, SA.IP_MODIFICACION, "
		+ "S.NIR,T.ID_ETAPA_ACTUAL, O.NOMBRE AS NOMBRE_CIRCULO, P.NOMBRE AS NOMBRE_PROCESO, "
		+ "S2.NIR AS NIR_REGISTRO " + "FROM SDB_ACC_SOLICITUD_APOYO_NACIONAL SA "
		+ "INNER JOIN SDB_ACC_SOLICITUD S ON SA.ID_SOLICITUD = S.ID_SOLICITUD "
		+ "INNER JOIN SDB_ACC_SOLICITUD S2 ON SA.ID_SOLICITUD_REGISTRO = S2.ID_SOLICITUD "
		+ "INNER JOIN SDB_ACC_TURNO T ON SA.ID_TURNO_REGISTRO = T.ID_TURNO "
		+ "INNER JOIN SDB_PGN_CIRCULO_REGISTRAL O ON SA.ID_CIRCULO_SOLICITANTE = O.ID_CIRCULO "
		+ "INNER JOIN SDB_ACC_PROCESO P ON P.ID_PROCESO = 49 " + "WHERE S.NIR = ? ";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID_TURNO_REGISTRO = "SELECT * FROM SDB_ACC_SOLICITUD_APOYO_NACIONAL  WHERE ID_TURNO_REGISTRO = ?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_SOLICITUD_APOYO_NACIONAL_ID_SOLICITUD_APOYO_NACIONAL.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SOLICITUD_APOYO_NACIONAL(ID_SOLICITUD_APOYO_NACIONAL, ID_SOLICITUD, ID_CIRCULO_SOLICITANTE, ID_TURNO, "
		+ " ID_SOLICITUD_REGISTRO, ID_TURNO_REGISTRO, ID_TURNO_HISTORIA_REGISTRO, ID_CIRCULO_ASIGNADO, ID_USUARIO_ASIGNADO, FECHA_ASIGNACION, REASIGNACION_EXITOSA, OBSERVACION, "
		+ " ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE_BY_ID. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SOLICITUD_APOYO_NACIONAL SET ID_SOLICITUD = ?, ID_CIRCULO_SOLICITANTE = ?, ID_TURNO = ?, "
		+ "ID_SOLICITUD_REGISTRO = ?, ID_TURNO_REGISTRO = ?, ID_TURNO_HISTORIA_REGISTRO = ?, ID_CIRCULO_ASIGNADO = ?, ID_USUARIO_ASIGNADO = ?, FECHA_ASIGNACION = ?, REASIGNACION_EXITOSA = ?, OBSERVACION = ?"
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP,  IP_MODIFICACION = ? WHERE ID_SOLICITUD_APOYO_NACIONAL = ?";

	/**
	 * Método que retorna un SolicitudApoyoNacional específico según el id.
	 *
	 * @param ar_parametros Argumento de tipo <code>SolicitudApoyoNacional</code> que contiene el SolicitudApoyoNacional.
	 * @return devuelve el valor del objeto SolicitudApoyoNacional.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public SolicitudApoyoNacional findById(SolicitudApoyoNacional ar_parametros)
	    throws B2BException
	{
		SolicitudApoyoNacional lsan_SolicitudApoyoNacional;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lsan_SolicitudApoyoNacional     = null;
		lps_ps                          = null;
		lrs_rs                          = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setObject(li_contador++, ar_parametros.getIdSolicitudApoyoNacional());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lsan_SolicitudApoyoNacional = getSolicitudApoyoNacional(lrs_rs);
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

		return lsan_SolicitudApoyoNacional;
	}

	/**
	      * Consulta todas las SolicitudApoyoNacional y los filtra por un id turno registro previamente definido
	 *
	 * @param as_idTurnoRegistro String del as_idTurnoRegistro para aplicar al filtro
	 * @return Colección de SolicitudApoyoNacional resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<SolicitudApoyoNacional> findByIdTurnoRegistro(String as_idTurnoRegistro)
	    throws B2BException
	{
		Collection<SolicitudApoyoNacional> lcsan_san;

		lcsan_san = new ArrayList<SolicitudApoyoNacional>();

		if(StringUtils.isValidString(as_idTurnoRegistro))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO_REGISTRO);

				lps_ps.setString(1, as_idTurnoRegistro);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsan_san.add(getSolicitudApoyoNacional(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoRegistro", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcsan_san.isEmpty())
			lcsan_san = null;

		return lcsan_san;
	}

	/**
	 * Método que retorna un SolicitudApoyoNacional específico según el id Solicitud.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @return devuelve el valor del objeto SolicitudApoyoNacional.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<SolicitudApoyoNacional> findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<SolicitudApoyoNacional> lcsan_SolicitudApoyoNacional;
		PreparedStatement                  lps_ps;
		ResultSet                          lrs_rs;

		lcsan_SolicitudApoyoNacional     = new ArrayList<SolicitudApoyoNacional>();
		lps_ps                           = null;
		lrs_rs                           = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

			lps_ps.setObject(li_contador++, as_idSolicitud);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcsan_SolicitudApoyoNacional.add(getSolicitudApoyoNacional(lrs_rs));
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

		if(lcsan_SolicitudApoyoNacional.isEmpty())
			lcsan_SolicitudApoyoNacional = null;

		return lcsan_SolicitudApoyoNacional;
	}

	/**
	 * Método que retorna un SolicitudApoyoNacional específico según el nir de la solicitud.
	 *
	 * @param as_nir de as nir
	 * @return devuelve el valor del objeto SolicitudApoyoNacional.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<SolicitudApoyoNacional> findByNIR(String as_nir)
	    throws B2BException
	{
		Collection<SolicitudApoyoNacional> lcsan_SolicitudApoyoNacional;
		PreparedStatement                  lps_ps;
		ResultSet                          lrs_rs;

		lcsan_SolicitudApoyoNacional     = new ArrayList<SolicitudApoyoNacional>();
		lps_ps                           = null;
		lrs_rs                           = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_NIR);

			lps_ps.setObject(li_contador++, as_nir);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcsan_SolicitudApoyoNacional.add(getSolicitudApoyoNacionalNir(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByNIR", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcsan_SolicitudApoyoNacional.isEmpty())
			lcsan_SolicitudApoyoNacional = null;

		return lcsan_SolicitudApoyoNacional;
	}

	/**
	 * Inserta o actualiza el registro en la tabla.
	 *
	 * @param ar_parametros Argumento de tipo <code>SolicitudApoyoNacional</code> que contiene el valor del SolicitudApoyoNacional.
	 * @param ab_query Argumento de tipo <code>Boolean</code> que indica la sentencia a ejecutar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(SolicitudApoyoNacional ar_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ar_parametros != null)
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
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							ar_parametros.setIdSolicitudApoyoNacional(((BigDecimal)lo_o).toString());
						else
							throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
					}

					lps_ps.setString(li_column++, ar_parametros.getIdSolicitudApoyoNacional());
				}

				lps_ps.setString(li_column++, ar_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, ar_parametros.getIdCirculoSolicitante());
				lps_ps.setString(li_column++, ar_parametros.getIdTurno());
				lps_ps.setString(li_column++, ar_parametros.getIdSolicitudRegistro());
				lps_ps.setString(li_column++, ar_parametros.getIdTurnoRegistro());
				lps_ps.setLong(li_column++, ar_parametros.getIdTurnoHistoriaRegistro());
				lps_ps.setString(li_column++, ar_parametros.getIdCirculoAsignado());
				lps_ps.setString(li_column++, ar_parametros.getIdUsuarioAsignado());
				setDate(lps_ps, ar_parametros.getFechaAsignacion(), li_column++);
				lps_ps.setString(li_column++, ar_parametros.getReasignacionExitosa());
				lps_ps.setString(li_column++, ar_parametros.getObservacion());

				if(ab_query)
				{
					lps_ps.setString(li_column++, ar_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ar_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ar_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ar_parametros.getIpModificacion());
				}

				if(!ab_query)
					lps_ps.setString(li_column++, ar_parametros.getIdSolicitudApoyoNacional());

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
	 * Método que retorna el valor del SolicitudApoyoNacional.
	 *
	 * @param ars_rs Argumento de tipo <code>ResultSet</code> correspondiente al valor del tipo de objeto ResultSet.
	 * @return el valor de SolicitudApoyoNacional.
	 * @throws SQLException Señala que se ha producido una excepción.
	 */
	private SolicitudApoyoNacional getSolicitudApoyoNacional(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudApoyoNacional lr_datos;

		lr_datos = new SolicitudApoyoNacional();

		lr_datos.setIdSolicitudApoyoNacional(ars_rs.getString("ID_SOLICITUD_APOYO_NACIONAL"));
		lr_datos.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lr_datos.setIdCirculoSolicitante(ars_rs.getString("ID_CIRCULO_SOLICITANTE"));
		lr_datos.setIdTurno(ars_rs.getString("ID_TURNO"));
		lr_datos.setIdSolicitudRegistro(ars_rs.getString("ID_SOLICITUD_REGISTRO"));
		lr_datos.setIdTurnoRegistro(ars_rs.getString("ID_TURNO_REGISTRO"));
		lr_datos.setIdTurnoHistoriaRegistro(ars_rs.getLong("ID_TURNO_HISTORIA_REGISTRO"));
		lr_datos.setIdCirculoAsignado(ars_rs.getString("ID_CIRCULO_ASIGNADO"));
		lr_datos.setIdUsuarioAsignado(ars_rs.getString("ID_USUARIO_ASIGNADO"));
		lr_datos.setFechaAsignacion(ars_rs.getTimestamp("FECHA_ASIGNACION"));
		lr_datos.setReasignacionExitosa(ars_rs.getString("REASIGNACION_EXITOSA"));
		lr_datos.setObservacion(ars_rs.getString("OBSERVACION"));

		fillAuditoria(ars_rs, lr_datos);

		return lr_datos;
	}

	/**
	 * Método que retorna el valor del SolicitudApoyoNacional.
	 *
	 * @param ars_rs Argumento de tipo <code>ResultSet</code> correspondiente al valor del tipo de objeto ResultSet.
	 * @return el valor de SolicitudApoyoNacional.
	 * @throws SQLException Señala que se ha producido una excepción.
	 */
	private SolicitudApoyoNacional getSolicitudApoyoNacionalNir(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudApoyoNacional lr_datos;

		lr_datos = getSolicitudApoyoNacional(ars_rs);

		lr_datos.setEtapaActual(ars_rs.getLong("ID_ETAPA_ACTUAL"));
		lr_datos.setNir(ars_rs.getString("NIR"));
		lr_datos.setNirRegistro(ars_rs.getString("NIR_REGISTRO"));
		lr_datos.setNombreCirculo(ars_rs.getString("NOMBRE_CIRCULO"));
		lr_datos.setNombreProceso(ars_rs.getString("NOMBRE_PROCESO"));

		return lr_datos;
	}
}
