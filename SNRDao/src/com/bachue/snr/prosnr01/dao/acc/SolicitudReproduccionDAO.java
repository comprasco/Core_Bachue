package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.registro.ConstanciaReproduccion;
import com.bachue.snr.prosnr01.model.registro.SolicitudReproduccion;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_SOLICITUD_REPRODUCCION
 *
 * @author asantos
 */
public class SolicitudReproduccionDAO extends BaseDAO implements IBase<SolicitudReproduccion>
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SOLICITUD_REPRODUCCION (ID_SOLICITUD_REPRODUCCION,ID_SOLICITUD,ID_TURNO_REPRODUCCION,ID_CIRCULO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SOLICITUD_REPRODUCCION SET ID_SOLICITUD_REPRODUCCION=?, ID_SOLICITUD=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_CIRCULO=? AND ID_TURNO_REPRODUCCION=?";

	/** Constante cs_SELECT_VALIDAR. */
	private static final String cs_SELECT_VALIDAR = "SELECT * FROM SDB_ACC_SOLICITUD_REPRODUCCION WHERE ID_CIRCULO=? AND ID_TURNO_REPRODUCCION=? AND ID_SOLICITUD= ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_SOLICITUD_REPRODUCCION_ID_SOLICITUD_REPRODUCCION.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_SOL_REP_TURNO_HIST. */
	private static final String cs_FIND_SOL_REP_TURNO_HIST = "SELECT SR.ID_SOLICITUD_REPRODUCCION SOLICITUD_REPRODUCCION, TH.ID_TURNO_HISTORIA TURNO_HISTORIA FROM SDB_ACC_TURNO_HISTORIA TH "
		+ "LEFT JOIN SDB_ACC_SOLICITUD_REPRODUCCION SR ON (TH.ID_SOLICITUD = SR.ID_SOLICITUD AND TH.ID_CIRCULO_USUARIO = SR.ID_CIRCULO) WHERE TH.ID_ETAPA = ? AND TH.ESTADO_ACTIVIDAD = ? AND NVL(TH.INTENTOS_FALLIDOS_EJECUCION_AUTOMATICA, 0) < ?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_SOLICITUD_REPRODUCCION WHERE ID_SOLICITUD_REPRODUCCION = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD_ID_CIRCULO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_ID_CIRCULO = "SELECT * FROM SDB_ACC_SOLICITUD_REPRODUCCION WHERE ID_SOLICITUD = ? ";

	/** {@inheritdoc} */
	@Override
	public SolicitudReproduccion findById(SolicitudReproduccion at_param)
	    throws B2BException
	{
		SolicitudReproduccion lsr_solicitudReproduccion;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		lsr_solicitudReproduccion     = null;
		lps_ps                        = null;
		lrs_rs                        = null;

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_column++, at_param.getIdSolicitudReproduccion());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lsr_solicitudReproduccion = getSolicitudReproduccionFromResultSet(lrs_rs);
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

		return lsr_solicitudReproduccion;
	}

	/**
	 * Retorna el valor del objeto de SolicitudReproduccion.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto SolicitudReproduccion
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de SolicitudReproduccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudReproduccion
	 */
	public SolicitudReproduccion findByIdSolicitudIdCirculo(SolicitudReproduccion at_param, boolean ab_b)
	    throws B2BException
	{
		SolicitudReproduccion lsr_solicitudReproduccion;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;
		StringBuilder         lsb_sb;

		lsr_solicitudReproduccion     = null;
		lps_ps                        = null;
		lrs_rs                        = null;
		lsb_sb                        = new StringBuilder(cs_FIND_BY_ID_SOLICITUD_ID_CIRCULO);

		try
		{
			int li_column;

			li_column = 1;

			if(ab_b)
				lsb_sb = lsb_sb.append("  AND ID_CIRCULO = ?");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			lps_ps.setString(li_column++, at_param.getIdSolicitud());

			if(ab_b)
				lps_ps.setString(li_column++, at_param.getIdCirculo());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lsr_solicitudReproduccion = getSolicitudReproduccionFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdSolicitudIdCirculo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lsr_solicitudReproduccion;
	}

	/**
	 * Retorna el valor del objeto de String con la secuencia.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findSecuence()
	    throws B2BException
	{
		String            li_secuencia;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_secuencia     = null;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCE);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_secuencia = lrs_rs.getString(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSecuence", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_secuencia;
	}

	/**
	 * Retorna el valor del objeto de Collection de ConstanciaReproduccion.
	 *
	 * @param al_etapa correspondiente al valor del tipo de objeto Long
	 * @param as_estadoActividad correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection de ConstanciaReproduccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ConstanciaReproduccion
	 */
	public Collection<ConstanciaReproduccion> findSolRepByStageState(
	    Long al_etapa, String as_estadoActividad, long al_limiteIntentos
	)
	    throws B2BException
	{
		Collection<ConstanciaReproduccion> lccr_datos;
		PreparedStatement                  lps_ps;
		ResultSet                          lrs_rs;

		lccr_datos     = new ArrayList<ConstanciaReproduccion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_SOL_REP_TURNO_HIST);

			setLong(lps_ps, al_etapa, li_column++);
			lps_ps.setString(li_column++, as_estadoActividad);
			lps_ps.setLong(li_column++, al_limiteIntentos);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
			{
				ConstanciaReproduccion lcr_cr;
				SolicitudReproduccion  lsr_sr;
				TurnoHistoria          lth_th;

				lcr_cr     = new ConstanciaReproduccion();
				lsr_sr     = new SolicitudReproduccion();
				lth_th     = new TurnoHistoria();

				lsr_sr.setIdSolicitudReproduccion(lrs_rs.getString("SOLICITUD_REPRODUCCION"));
				lth_th.setIdTurnoHistoria(getLong(lrs_rs, "TURNO_HISTORIA"));

				lcr_cr.setSolicitudReproduccion(lsr_sr);
				lcr_cr.setTurnoHistoria(lth_th);

				lccr_datos.add(lcr_cr);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSolRepByStageState", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lccr_datos.isEmpty())
			lccr_datos = null;

		return lccr_datos;
	}

	/**
	 * Inserta un nuevo registro en la tabla.
	 *
	 * @param asr_parametro correspondiente al valor del tipo de objeto SolicitudReproduccion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(SolicitudReproduccion asr_parametro)
	    throws B2BException
	{
		if(asr_parametro != null)
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

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));

				lps_ps.setString(li_column++, asr_parametro.getIdSolicitud());
				lps_ps.setString(li_column++, asr_parametro.getIdTurnoReproduccion());
				lps_ps.setString(li_column++, asr_parametro.getIdCirculo());
				lps_ps.setString(li_column++, asr_parametro.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, asr_parametro.getIpCreacion());

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

	/** {@inheritdoc} */
	@Override
	public void insertOrUpdate(SolicitudReproduccion asr_parametro, boolean ab_query)
	    throws B2BException
	{
		if(asr_parametro != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setString(li_column++, asr_parametro.getSecuence());
					lps_ps.setString(li_column++, asr_parametro.getIdSolicitud());
					lps_ps.setString(li_column++, asr_parametro.getIdTurnoReproduccion());
					lps_ps.setString(li_column++, asr_parametro.getIdCirculo());
					lps_ps.setString(li_column++, asr_parametro.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, asr_parametro.getIpCreacion());
				}

				if(!ab_query)
				{
					lps_ps.setString(li_column++, asr_parametro.getIdSolicitudReproduccion());
					lps_ps.setString(li_column++, asr_parametro.getIdSolicitud());
					lps_ps.setString(li_column++, asr_parametro.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, asr_parametro.getIpModificacion());

					lps_ps.setString(li_column++, asr_parametro.getIdCirculo());
					lps_ps.setString(li_column++, asr_parametro.getIdTurnoReproduccion());
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
			}
		}
	}

	/**
	 * Retorna el valor del objeto de SolicitudReproduccion.
	 *
	 * @param asr_parametro correspondiente al valor del tipo de objeto SolicitudReproduccion
	 * @return devuelve el valor de SolicitudReproduccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudReproduccion
	 */
	public SolicitudReproduccion validarReproduccionConstancia(SolicitudReproduccion asr_parametro)
	    throws B2BException
	{
		SolicitudReproduccion ls_solicitudReproduccion;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		ls_solicitudReproduccion     = null;
		lps_ps                       = null;
		lrs_rs                       = null;

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_SELECT_VALIDAR);

			lps_ps.setString(li_column++, asr_parametro.getIdCirculo());
			lps_ps.setString(li_column++, asr_parametro.getIdTurnoReproduccion());
			lps_ps.setString(li_column++, asr_parametro.getIdSolicitud());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_solicitudReproduccion = getSolicitudReproduccionFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "ValidarReproduccionConstancia", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_solicitudReproduccion;
	}

	/**
	 * Retorna el valor de solicitud reproduccion
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de solicitud reproduccion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SolicitudReproduccion getSolicitudReproduccionFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudReproduccion ls_solicitudReproduccion;

		ls_solicitudReproduccion = new SolicitudReproduccion();

		ls_solicitudReproduccion.setIdSolicitudReproduccion(ars_rs.getString("ID_SOLICITUD_REPRODUCCION"));
		ls_solicitudReproduccion.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		ls_solicitudReproduccion.setIdTurnoReproduccion(ars_rs.getString("ID_TURNO_REPRODUCCION"));
		ls_solicitudReproduccion.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		ls_solicitudReproduccion.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitudReproduccion.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitudReproduccion.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ls_solicitudReproduccion.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitudReproduccion.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitudReproduccion.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return ls_solicitudReproduccion;
	}
}
