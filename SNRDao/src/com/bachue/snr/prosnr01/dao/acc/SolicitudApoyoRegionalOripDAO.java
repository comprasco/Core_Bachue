package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudApoyoRegionalOrip;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las propiedades SolicitudApoyoRegionalOripDAO.
 *
 * @author  Luis chacón
 * Fecha de Creacion: 15/09/2020
 */
public class SolicitudApoyoRegionalOripDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_SOLICITUD_APOYO_REGIONAL_ORIP  WHERE ID_SOLICITUD_APOYO_REGIONAL_ORIP = ?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_SOLICITUD_APOYO_REGIONAL_ORIP_ID_SOLICITUD_APOYO_REGIONAL_ORIP.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SOLICITUD_APOYO_REGIONAL_ORIP(ID_SOLICITUD_APOYO_REGIONAL_ORIP, ID_SOLICITUD, ID_CIRCULO_REGIONAL, OBSERVACIONES, "
		+ " ORDEN, HABILITAR, PARAMETRIZACION_CALIFICADORES, ID_REGIONAL, CORREO_ELECTRONICO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE_BY_ID. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SOLICITUD_APOYO_REGIONAL_ORIP SET ID_SOLICITUD = ?, ID_CIRCULO_REGIONAL = ?, OBSERVACIONES = ?, "
		+ "ORDEN = ?, HABILITAR = ?, PARAMETRIZACION_CALIFICADORES = ?, ID_REGIONAL = ?, CORREO_ELECTRONICO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP,  IP_MODIFICACION = ? WHERE ID_SOLICITUD_APOYO_REGIONAL_ORIP = ?";

	/**
	 * Método que retorna un SolicitudApoyoRegionalOrip específico según el id.
	 *
	 * @param ar_parametros Argumento de tipo <code>SolicitudApoyoRegionalOrip</code> que contiene el SolicitudApoyoRegionalOrip.
	 * @return devuelve el valor del objeto SolicitudApoyoRegionalOrip.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public SolicitudApoyoRegionalOrip findById(SolicitudApoyoRegionalOrip ar_parametros)
	    throws B2BException
	{
		SolicitudApoyoRegionalOrip lsan_SolicitudApoyoRegionalOrip;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lsan_SolicitudApoyoRegionalOrip     = null;
		lps_ps                              = null;
		lrs_rs                              = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setObject(li_contador++, ar_parametros.getIdSolicitudApoyoRegionalOrip());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lsan_SolicitudApoyoRegionalOrip = getSolicitudApoyoRegionalOrip(lrs_rs);
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

		return lsan_SolicitudApoyoRegionalOrip;
	}

	/**
	 * Inserta o actualiza el registro en la tabla.
	 *
	 * @param ar_parametros Argumento de tipo <code>SolicitudApoyoRegionalOrip</code> que contiene el valor del SolicitudApoyoRegionalOrip.
	 * @param ab_query Argumento de tipo <code>Boolean</code> que indica la sentencia a ejecutar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(SolicitudApoyoRegionalOrip ar_parametros, boolean ab_query)
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
							ar_parametros.setIdSolicitudApoyoRegionalOrip(((BigDecimal)lo_o).toString());
						else
							throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
					}

					lps_ps.setString(li_column++, ar_parametros.getIdSolicitudApoyoRegionalOrip());
				}

				lps_ps.setString(li_column++, ar_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, ar_parametros.getIdCirculoRegional());
				lps_ps.setString(li_column++, ar_parametros.getObservaciones());
				lps_ps.setLong(li_column++, ar_parametros.getOrden());
				lps_ps.setString(li_column++, ar_parametros.getHabilitar());
				lps_ps.setString(li_column++, ar_parametros.getParametrizacionCalificadores());
				lps_ps.setString(li_column++, ar_parametros.getIdRegional());
				lps_ps.setString(li_column++, ar_parametros.getCorreoElectronico());

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
					lps_ps.setString(li_column++, ar_parametros.getIdSolicitudApoyoRegionalOrip());

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
	 * Método que retorna el valor del SolicitudApoyoRegionalOrip.
	 *
	 * @param ars_rs Argumento de tipo <code>ResultSet</code> correspondiente al valor del tipo de objeto ResultSet.
	 * @return el valor de SolicitudApoyoRegionalOrip.
	 * @throws SQLException Señala que se ha producido una excepción.
	 */
	private SolicitudApoyoRegionalOrip getSolicitudApoyoRegionalOrip(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudApoyoRegionalOrip lr_datos;

		lr_datos = new SolicitudApoyoRegionalOrip();

		lr_datos.setIdSolicitudApoyoRegionalOrip(ars_rs.getString("ID_SOLICITUD_APOYO_REGIONAL_ORIP"));
		lr_datos.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lr_datos.setIdCirculoRegional(ars_rs.getString("ID_CIRCULO_REGIONAL"));
		lr_datos.setObservaciones(ars_rs.getString("OBSERVACIONES"));
		lr_datos.setOrden(ars_rs.getLong("ORDEN"));
		lr_datos.setHabilitar(ars_rs.getString("HABILITAR"));
		lr_datos.setObservaciones(ars_rs.getString("OBSERVACIONES"));
		lr_datos.setParametrizacionCalificadores(ars_rs.getString("PARAMETRIZACION_CALIFICADORES"));
		lr_datos.setIdRegional(ars_rs.getString("ID_REGIONAL"));
		lr_datos.setCorreoElectronico(ars_rs.getString("CORREO_ELECTRONICO"));

		fillAuditoria(ars_rs, lr_datos);

		return lr_datos;
	}
}
