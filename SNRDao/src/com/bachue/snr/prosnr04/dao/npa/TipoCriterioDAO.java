package com.bachue.snr.prosnr04.dao.npa;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr04.model.npa.TipoCriterio;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Clase DAO que extrae todos los atributos usados en la tabla SDB_ACC_TIPO_CRITERIO_NOTIFICACION_PAGO
 *
 * @author Julian Vaca
 *
 */
public class TipoCriterioDAO extends AuditoriaDao
{
	
	/**
	 * Constante cs_INSERTAR_TIPO_CRITERIO.
	 */
	private static final String cs_INSERTAR_TIPO_CRITERIO = "INSERT INTO SDB_ACC_TIPO_CRITERIO_NOTIFICACION_PAGO (ID_TIPO_SERVICIO_NOTIFICACION_PAGO,ID_NUMERO_REFERENCIA, ID_PROCESO, ID_SUBPROCESO, CODIGO, VALOR, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?,?,?,?,?,?,?,SYSTIMESTAMP,?) ";

	/**
	 * Método que inserta registro en la tabla SDB_ACC_TIPO_CRITERIO_NOTIFICACION_PAGO
	 *
	 * @param atc_parametros Objeto de tipo TipoCriterio que contiene los parametros para realizar la insercion
	 * @throws B2BException
	 */
	public void insertarTipoCriterio(TipoCriterio atc_parametros)
	    throws B2BException
	{
		if(atc_parametros != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;

			li_cont     = 1;
			lps_ps      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_INSERTAR_TIPO_CRITERIO);

				lps_ps.setString(li_cont++, atc_parametros.getIdTipoServicioNotificacionPago());
				lps_ps.setString(li_cont++, atc_parametros.getIdNumeroReferencia());
				lps_ps.setString(li_cont++, atc_parametros.getIdProceso());
				lps_ps.setString(li_cont++, atc_parametros.getIdSubProceso());
				lps_ps.setString(li_cont++, atc_parametros.getCodigo());
				lps_ps.setString(li_cont++, atc_parametros.getValor());
				lps_ps.setString(li_cont++, atc_parametros.getIdUsuarioCreacion());
				lps_ps.setString(li_cont++, atc_parametros.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertarTipoCriterio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}
}
