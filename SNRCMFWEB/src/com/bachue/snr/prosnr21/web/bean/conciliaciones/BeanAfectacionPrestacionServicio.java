package com.bachue.snr.prosnr21.web.bean.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr21.common.constants.MessagesKeys;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.AfectacionPrestacionServicio;

import com.bachue.snr.prosnr21.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import org.primefaces.model.DefaultStreamedContent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades BeanAfectacionPrestacionServicio.
 *
 * @author  Duvan Beltrán
 */
@ManagedBean(name = "beanAfectacionPrestacionServicio")
@SessionScoped
public class BeanAfectacionPrestacionServicio extends BaseBean implements Serializable
{
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 7612578504362015460L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanAfectacionPrestacionServicio.class);

	/** Propiedad mostrar Panel. */
	boolean ib_mostrarPanel;

	/** Propiedad afectacion Prestacion Servicio. */
	private Collection<AfectacionPrestacionServicio> icaps_afectacionPrestacionServicio;

	/** Propiedad afectacion listado Ley. */
	private Collection<AfectacionPrestacionServicio> icaps_listadoLey;

	/** Propiedad fecha concfrontacion. */
	private Date id_fechaConsulta;

	/** Propiedad isc file. */
	private DefaultStreamedContent isc_reportes;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/**
	 * Modifica el valor de AfectacionPrestacionServicio.
	 *
	 * @param lcaps_afectacionPrestacionServicio de lcaps afectacion prestacion servicio
	 */
	public void setAfectacionPrestacionServicio(
	    Collection<AfectacionPrestacionServicio> lcaps_afectacionPrestacionServicio
	)
	{
		this.icaps_afectacionPrestacionServicio = lcaps_afectacionPrestacionServicio;
	}

	/**
	 * Retorna Objeto o variable de valor afectacion prestacion servicio.
	 *
	 * @return el valor de afectacion prestacion servicio
	 */
	public Collection<AfectacionPrestacionServicio> getAfectacionPrestacionServicio()
	{
		return icaps_afectacionPrestacionServicio;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de application
	 */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de FechaConsulta.
	 *
	 * @param ad_fechaConsulta de ad fecha consulta
	 */
	public void setFechaConsulta(Date ad_fechaConsulta)
	{
		id_fechaConsulta = ad_fechaConsulta;
	}

	/**
	 * Retorna Objeto o variable de valor fecha consulta.
	 *
	 * @return Retorna el valor de la propiedad id_fechaMovimiento
	 */
	public Date getFechaConsulta()
	{
		return id_fechaConsulta;
	}

	/**
	 * Modifica el valor de ListadoLey.
	 *
	 * @param acaps_listadoLey de listado ley
	 */
	public void setListadoLey(Collection<AfectacionPrestacionServicio> acaps_listadoLey)
	{
		this.icaps_listadoLey = acaps_listadoLey;
	}

	/**
	 * Retorna Objeto o variable de valor listado ley.
	 *
	 * @return el valor de listado ley
	 */
	public Collection<AfectacionPrestacionServicio> getListadoLey()
	{
		return icaps_listadoLey;
	}

	/**
	 * Modifica el valor de MostrarPanel.
	 *
	 * @param ab_mostrarPanel de ab mostrar panel
	 */
	public void setMostrarPanel(boolean ab_mostrarPanel)
	{
		ib_mostrarPanel = ab_mostrarPanel;
	}

	/**
	 * Retorna Objeto o variable de valor mostrar panel.
	 *
	 * @return Retorna el valor de la propiedad ib_mostrarPanel
	 */
	public boolean getMostrarPanel()
	{
		return ib_mostrarPanel;
	}

	/**
	 * Sets the reportes.
	 *
	 * @param asc_reportes the new reportes
	 */
	public void setReportes(DefaultStreamedContent asc_reportes)
	{
		isc_reportes = asc_reportes;
	}

	/**
	 * Gets the reportes.
	 *
	 * @return Retorna el valor de la propiedad reporteExcel
	 */
	public DefaultStreamedContent getReportes()
	{
		return isc_reportes;
	}

	/**
	 * Consultar alertas.
	 */
	public void consultar()
	{
		try
		{
			boolean      lb_focus;
			Date         ld_fechaConsulta;
			FacesContext lfc_context;

			lb_focus             = true;
			ld_fechaConsulta     = getFechaConsulta();
			lfc_context          = FacesContext.getCurrentInstance();

			if(ld_fechaConsulta == null)
				lb_focus = validateStyles(
					    ":fAfectacionPrestacionServicio:idFechaConsulta", lfc_context, ld_fechaConsulta, lb_focus
					);
			else if(ld_fechaConsulta.after(new Date()))
			{
				lb_focus = validateStyles(
					    ":fAfectacionPrestacionServicio:idFechaConsulta", lfc_context, ld_fechaConsulta, lb_focus
					);
				addMessageInfo("W", MessagesKeys.ERROR_FECHA_MAYOR_A_ACTUAL);
				lb_focus = false;
			}
			else
				lb_focus = validateStyles(
					    ":fAfectacionPrestacionServicio:idFechaConsulta", lfc_context, ld_fechaConsulta, lb_focus
					);

			PrimeFaces.current().ajax().update("fAfectacionPrestacionServicio");

			if(!lb_focus)
				throw new B2BException(ErrorKeys.ERROR_SELECCION_INFORMACION_CONSULTA);

			{
				Map<String, Collection<AfectacionPrestacionServicio>> lmscaps_data;

				lmscaps_data = ipr_parameterRemote.consultarAfectacionPrestacionServicio(
					    ld_fechaConsulta, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(lmscaps_data))
				{
					setAfectacionPrestacionServicio(
					    lmscaps_data.get(IdentificadoresCommon.AFECTACION_PRESTACION_SERVICIO)
					);
					setListadoLey(lmscaps_data.get(IdentificadoresCommon.LISTA_LEY));
				}
			}

			setMostrarPanel(true);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultar", lb2be_e);
			addMessage(lb2be_e);
			actualizarComponente(":fAfectacionPrestacionServicio:idGrowl");
		}
	}

	/**
	 * Generar reportes.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void generarReportes()
	    throws IOException
	{
		try
		{
			byte[] lba_archivo;

			lba_archivo = ipr_parameterRemote.generarReportesAfectacionPrestacionServicio(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress(), getListadoLey()
				);

			if(lba_archivo != null)
			{
				LocalDateTime     lldt_currentdate;
				DateTimeFormatter ldtf_formatter;

				lldt_currentdate     = LocalDateTime.now();
				ldtf_formatter       = DateTimeFormatter.ofPattern("YYYYMMdd_HHmm");

				setReportes(
				    new DefaultStreamedContent(
				        new ByteArrayInputStream(lba_archivo), TipoContenidoCommon.ZIP,
				        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO_ZIP + IdentificadoresCommon.SIMBOLO_GUION_BAJO
				        + lldt_currentdate.format(ldtf_formatter) + ExtensionCommon.ZIP_PUNTO
				    )
				);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarReportes", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(":fAfectacionPrestacionServicio:idGrowl");
		}
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		setAfectacionPrestacionServicio(null);
		setListadoLey(null);
		setFechaConsulta(null);
		setMostrarPanel(false);
	}
}
