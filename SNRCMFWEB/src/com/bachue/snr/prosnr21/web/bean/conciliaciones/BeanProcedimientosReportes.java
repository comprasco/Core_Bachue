package com.bachue.snr.prosnr21.web.bean.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.common.constants.MessagesKeys;
import com.bachue.snr.prosnr21.common.constants.PeriodicidadCommon;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote;
import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.web.bean.BaseBean;

import java.io.IOException;
import java.io.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades BeanConfrontacionManual.
 *
 * @author Duvan Beltrán
 */
@ManagedBean(name = "beanProcedimientosReportes")
@SessionScoped
public class BeanProcedimientosReportes extends BaseBean implements Serializable
{
	/** Constante serialVersionUID */
	private static final long serialVersionUID = -3799077522696164360L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanProcedimientosReportes.class);
	private Collection<String> ics_procedimientos;

	/** Propiedad icr conciliaciones remote. */
	@EJB
	private ConciliacionesRemote icr_conciliacionesRemote;

	/** Propiedad fecha. */
	private Date id_fecha;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad is periodicidad. */
	private String is_periodicidad;
	private String is_procedimiento;

	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Checks if is reporte diario.
	 *
	 * @return true, if is reporte diario
	 */
	public boolean isDiario()
	{
		String ls_periodicidad;

		ls_periodicidad = getPeriodicidad();

		return StringUtils.isValidString(ls_periodicidad)
			&& ls_periodicidad.equalsIgnoreCase(PeriodicidadCommon.DIARIO);
	}

	/**
	 * @param Modifica
	 *            el valor de la propiedad fecha por fecha
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
	 * Checks if is reporte mensual.
	 *
	 * @return true, if is reporte mensual
	 */
	public boolean isMensual()
	{
		String ls_periodicidad;

		ls_periodicidad = getPeriodicidad();

		return StringUtils.isValidString(ls_periodicidad)
			&& ls_periodicidad.equalsIgnoreCase(PeriodicidadCommon.MENSUAL);
	}

	/**
	 * @param Modifica el valor de la propiedad periodicidad por periodicidad
	 */
	public void setPeriodicidad(String as_s)
	{
		is_periodicidad = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad periodicidad
	 */
	public String getPeriodicidad()
	{
		return is_periodicidad;
	}

	/**
	 * @param Modifica el valor de la propiedad procedimiento por procedimiento
	 */
	public void setProcedimiento(String as_s)
	{
		is_procedimiento = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad procedimiento
	 */
	public String getProcedimiento()
	{
		return is_procedimiento;
	}

	/**
	 * @param Modifica el valor de la propiedad procedimientos por procedimientos
	 */
	public void setProcedimientos(Collection<String> acs_cs)
	{
		ics_procedimientos = acs_cs;
	}

	/**
	 * @return Retorna el valor de la propiedad procedimientos
	 */
	public Collection<String> getProcedimientos()
	{
		return ics_procedimientos;
	}

	/**
	 * Cambiar reporte.
	 *
	 * @throws B2BException
	 *             the b 2 B exception
	 */
	public void cambiarPeriodo()
	    throws B2BException
	{
		Collection<String> lcs_return;

		lcs_return = null;

		try
		{
			String ls_periodicidad;

			ls_periodicidad = getPeriodicidad();

			if(StringUtils.isValidString(ls_periodicidad))
				lcs_return = ipr_parameterRemote.findProcedimientosReportesByPeriodicidad(
					    ls_periodicidad, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cambiarReporte", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			setProcedimientos(lcs_return);
		}
	}

	/**
	 * Procesar.
	 *
	 * @throws IOException
	 * @throws ParseException
	 */
	public void ejecutar()
	    throws ParseException, IOException
	{
		try
		{
			String ls_periodicidad;

			ls_periodicidad = getPeriodicidad();

			if(StringUtils.isValidString(ls_periodicidad))
			{
				String ls_procedimiento;

				ls_procedimiento = getProcedimiento();

				if(StringUtils.isValidString(ls_procedimiento))
				{
					Date   ld_fecha;
					String ls_fecha;

					ld_fecha     = getFecha();
					ls_fecha     = null;

					if(ld_fecha == null)
						throw new B2BException("Debe seleccionar fecha.");

					{
						Calendar lc_date;

						lc_date = Calendar.getInstance();

						lc_date.setTime(ld_fecha);

						if(isDiario())
						{
							SimpleDateFormat lsdf_formato;

							lsdf_formato     = new SimpleDateFormat("yyyy-MM-dd");

							ls_fecha = lsdf_formato.format(lc_date.getTime());
						}
						else if(isMensual())
							ls_fecha = lc_date.get(Calendar.YEAR)
								+ DateUtils.getMesNumero((lc_date.get(Calendar.MONTH) + 1));
					}

					if(!StringUtils.isValidString(ls_fecha))
						throw new B2BException("Debe seleccionar fecha.");

					icr_conciliacionesRemote.ejecutarProcedimientoReportes(
					    ls_fecha, ls_procedimiento, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

					addMessage(MessagesKeys.REPORTE_GENERADO);

					limpiar();
				}
				else
					throw new B2BException("Debe seleccionar un procedimiento.");
			}
			else
				throw new B2BException("Debe elegir una periodicidad.");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("procesar", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		setFecha(null);
		setPeriodicidad(null);
		setProcedimiento(null);
		setProcedimientos(null);
	}
}
