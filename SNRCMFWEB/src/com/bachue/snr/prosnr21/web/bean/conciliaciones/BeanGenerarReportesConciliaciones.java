package com.bachue.snr.prosnr21.web.bean.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.common.constants.MessagesKeys;
import com.bachue.snr.prosnr21.common.constants.PeriodicidadCommon;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote;
import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.RPTPrograma;

import com.bachue.snr.prosnr21.web.bean.BaseBean;

import java.io.IOException;
import java.io.Serializable;

import java.text.ParseException;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades BeanConfrontacionManual.
 *
 * @author  Duvan Beltrán
 */
@ManagedBean(name = "beanGenerarReportesConciliaciones")
@SessionScoped
public class BeanGenerarReportesConciliaciones extends BaseBean implements Serializable
{
	/** Constante serialVersionUID*/
	private static final long serialVersionUID = -3799077522696164360L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanGenerarReportesConciliaciones.class);

	/** Propiedad reportes. */
	private Collection<RPTPrograma> icrptp_reportes;

	/** Propiedad icr conciliaciones remote. */
	@EJB
	private ConciliacionesRemote icr_conciliacionesRemote;

	/** Propiedad fecha. */
	private Date id_fecha;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad reporte. */
	private RPTPrograma irptp_reporte;

	/** Propiedad idReporte. */
	private String is_idReporte;

	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param Modifica el valor de la propiedad fecha por fecha
	 */
	public void setFecha(Date fecha)
	{
		id_fecha = fecha;
	}

	/**
	 * @return Retorna el valor de la propiedad fecha
	 */
	public Date getFecha()
	{
		return id_fecha;
	}

	/**
	 * @param Modifica el valor de la propiedad idReporte por idReporte
	 */
	public void setIdReporte(String idReporte)
	{
		is_idReporte = idReporte;
	}

	/**
	 * @return Retorna el valor de la propiedad idReporte
	 */
	public String getIdReporte()
	{
		return is_idReporte;
	}

	/**
	 * @param Modifica el valor de la propiedad reporte por reporte
	 */
	public void setReporte(RPTPrograma reporte)
	{
		irptp_reporte = reporte;
	}

	/**
	 * @return Retorna el valor de la propiedad reporte
	 */
	public RPTPrograma getReporte()
	{
		return irptp_reporte;
	}

	/**
	 * Checks if is reporte diario.
	 *
	 * @return true, if is reporte diario
	 */
	public boolean isReporteDiario()
	{
		String ls_periodicidad;

		ls_periodicidad = getPeriodicidad();

		return StringUtils.isValidString(ls_periodicidad)
			&& ls_periodicidad.equalsIgnoreCase(PeriodicidadCommon.DIARIO);
	}

	/**
	 * Checks if is reporte mensual.
	 *
	 * @return true, if is reporte mensual
	 */
	public boolean isReporteMensual()
	{
		String ls_periodicidad;

		ls_periodicidad = getPeriodicidad();

		return StringUtils.isValidString(ls_periodicidad)
			&& ls_periodicidad.equalsIgnoreCase(PeriodicidadCommon.MENSUAL);
	}

	/**
	 * @param Modifica el valor de la propiedad reportes por reportes
	 */
	public void setReportes(Collection<RPTPrograma> reportes)
	{
		icrptp_reportes = reportes;
	}

	/**
	 * @return Retorna el valor de la propiedad reportes
	 * @throws B2BException
	 */
	public Collection<RPTPrograma> getReportes()
	    throws B2BException
	{
		if(icrptp_reportes == null)
			icrptp_reportes = ipr_parameterRemote.buscarProcesosRptProgramasActivos(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

		return icrptp_reportes;
	}

	/**
	 * Cambiar reporte.
	 *
	 * @throws B2BException the b 2 B exception
	 */
	public void cambiarReporte()
	    throws B2BException
	{
		try
		{
			String      ls_idReporte;
			RPTPrograma lrptp_reporte;

			ls_idReporte      = getIdReporte();
			lrptp_reporte     = null;

			if(StringUtils.isValidString(ls_idReporte))
				lrptp_reporte = ipr_parameterRemote.findRptProgramaById(
					    ls_idReporte, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

			setReporte(lrptp_reporte);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cambiarReporte", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		setFecha(null);
		setReporte(null);
		setReportes(null);
		setIdReporte(null);
	}

	/**
	 * Procesar.
	 * @throws IOException
	 * @throws ParseException
	 */
	public void procesar()
	    throws ParseException, IOException
	{
		try
		{
			String ls_idReporte;

			ls_idReporte = getIdReporte();

			if(StringUtils.isValidString(ls_idReporte))
			{
				Date        ld_fecha;
				RPTPrograma lrptp_reporte;

				ld_fecha          = getFecha();
				lrptp_reporte     = getReporte();

				if(ld_fecha == null)
					throw new B2BException("Debe seleccionar fecha.");

				if(lrptp_reporte != null)
				{
					Calendar lc_date;

					lc_date = Calendar.getInstance();

					lc_date.setTime(ld_fecha);

					icr_conciliacionesRemote.generarReportesConciliaciones(
					    lrptp_reporte, lc_date, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

					addMessage(MessagesKeys.REPORTE_GENERADO);

					limpiar();
				}
			}
			else
				throw new B2BException("Debe elegir un reporte.");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("procesar", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Gets the periodicidad.
	 *
	 * @return the periodicidad
	 */
	private String getPeriodicidad()
	{
		String      ls_return;
		RPTPrograma lrptp_reporte;

		ls_return         = null;
		lrptp_reporte     = getReporte();

		if(lrptp_reporte != null)
			ls_return = lrptp_reporte.getIdPeriodicidad();

		return ls_return;
	}
}
