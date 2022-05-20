package com.bachue.snr.prosnr21.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr21.common.constants.MessagesKeys;
import com.bachue.snr.prosnr21.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.PeriodoCorte;

import com.bachue.snr.prosnr21.web.bean.BaseBean;
import com.bachue.snr.prosnr21.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.event.RowEditEvent;

import java.io.IOException;
import java.io.Serializable;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase para el manejo de la capa web para PeriodoCorte
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanPeriodoCorte")
@SessionScoped
public class BeanPeriodoCorte extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7694363075239358888L;

	/** Propiedad iccmv datos auditoria. */
	private Collection<PeriodoCorte> iccmv_datosAuditoria;

	/**Propiedad periodo cortes*/
	private Collection<PeriodoCorte> icpc_periodoCortes;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ir PeriodoCorte. */
	private PeriodoCorte ir_periodoCorte;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/**Propiedad Periodo*/
	private long il_periodo;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param acmv_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<PeriodoCorte> acmv_datosAuditoria)
	{
		iccmv_datosAuditoria = acmv_datosAuditoria;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<PeriodoCorte> getDatosAuditoria()
	{
		return iccmv_datosAuditoria;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ab_insertar asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_insertar)
	{
		ib_insertar = ab_insertar;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param Modifica el valor de la propiedad il_periodo por il_periodo
	 */
	public void setPeriodo(long al_periodo)
	{
		il_periodo = al_periodo;
	}

	/**
	 * @return Retorna el valor de la propiedad il_periodo
	 */
	public long getPeriodo()
	{
		return il_periodo;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ar_PeriodoCorte asigna el valor a la propiedad
	 */
	public void setPeriodoCorte(PeriodoCorte ar_PeriodoCorte)
	{
		ir_periodoCorte = ar_PeriodoCorte;
	}

	/**
	 * Método de obtencion del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public PeriodoCorte getPeriodoCorte()
	{
		return ir_periodoCorte;
	}

	/**
	 * @param Modifica el valor de la propiedad icpc_periodoCortes por icpc_periodoCortes
	 */
	public void setPeriodoCortes(Collection<PeriodoCorte> icpc_periodoCortes)
	{
		this.icpc_periodoCortes = icpc_periodoCortes;
	}

	/**
	 * @return Retorna el valor de la propiedad icpc_periodoCortes
	 */
	public Collection<PeriodoCorte> getPeriodoCortes()
	{
		return icpc_periodoCortes;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo PeriodoCorte
	 */
	public void cambiarEstado()
	{
		setInsertar(true);

		setPeriodoCorte((new PeriodoCorte()));

		Calendar lc_calendar;
		lc_calendar = Calendar.getInstance();
		lc_calendar.setTime(new Date());
		setPeriodo(NumericUtils.getLong(lc_calendar.get(Calendar.YEAR)));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarPeriodoCorte");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consulta detalla para un PeriodoCorte en especial
	 * @param ar_r de tipo PeriodoCorte
	 * @throws B2BException
	 */
	public void consultaDetallada(PeriodoCorte ar_r)
	    throws B2BException
	{
		if(ar_r != null)
		{
			String       ls_idPeriodoCorte;
			PeriodoCorte latd_td;

			latd_td               = new PeriodoCorte();
			ls_idPeriodoCorte     = ar_r.getIdPeriodoCorte();

			latd_td.setIdPeriodoCorte(ls_idPeriodoCorte);

			latd_td = ipr_parameterRemote.findPeriodoCorteById(latd_td);

			if(latd_td != null)
			{
				Collection<PeriodoCorte> lctd_td;

				lctd_td = new ArrayList<PeriodoCorte>();

				lctd_td.add(latd_td);
				setPeriodoCorte(latd_td);
				setPeriodo(latd_td.getPeriodo());
				setDatosAuditoria(lctd_td);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método de consulta de todos los PeriodoCortes
	 * @return una colleccion de tipo PeriodoCorte
	 */
	public Collection<PeriodoCorte> findAllPeriodoCorte()
	{
		Collection<PeriodoCorte> lccmv_cmv;

		lccmv_cmv = null;

		try
		{
			lccmv_cmv = ipr_parameterRemote.findAllPeriodoCorte();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		setPeriodoCortes(lccmv_cmv);

		return lccmv_cmv;
	}

	/**
	 * Método de generación de datos del periodo corte actual
	 */
	public void generarDatosPeriodoCorte()
	{
		try
		{
			PeriodoCorte lr_PeriodoCorte;

			lr_PeriodoCorte     = getPeriodoCorte();

			lr_PeriodoCorte = ipr_parameterRemote.generarDatosPeriodoCorte(lr_PeriodoCorte);
			setPeriodoCorte(lr_PeriodoCorte);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fPeriodoCorteDetalle:globalMsg");
		}
		catch(ParseException lb2be_e)
		{
			PrimeFaces.current().ajax().update("fPeriodoCorteDetalle:globalMsg");
		}
	}

	/**
	 * On row edit.
	 *
	 * @param event correspondiente al valor del tipo de objeto RowEditEvent
	 * @throws ParseException
	 */
	public void onRowEdit(RowEditEvent event)
	{
		PeriodoCorte lorc_rc;
		lorc_rc = (PeriodoCorte)event.getObject();

		if(lorc_rc != null)
		{
			lorc_rc.setIdUsuarioCreacion(getUserId());

			try
			{
				ipr_parameterRemote.salvarPeriodoCorte(
				    lorc_rc, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				addMessage(MessagesKeys.MODIFICACION_EXITOSA);
				PrimeFaces.current().ajax().update("fPeriodoCorte:globalMsg");
			}
			catch(B2BException lbe_lbe)
			{
				addMessage(lbe_lbe);
				PrimeFaces.current().ajax().update("fPeriodoCorte:globalMsg");
			}
			catch(ParseException lb2be_e)
			{
				PrimeFaces.current().ajax().update("fPeriodoCorte:globalMsg");
			}
		}
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 * @return String con la url
	 * @throws IOException
	 */
	public String salvar()
	    throws IOException
	{
		String       ls_result;
		boolean      lb_focus;
		FacesContext lfc_context;

		ls_result       = null;
		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			PeriodoCorte lr_PeriodoCorte;

			lr_PeriodoCorte = getPeriodoCorte();

			if(lr_PeriodoCorte != null)
			{
				{
					Long ls_nombre;

					ls_nombre     = NumericUtils.getLongWrapper(lr_PeriodoCorte.getPeriodo());

					lb_focus = validateStyles(":fPeriodoCorteDetalle:idPeriodo", lfc_context, ls_nombre, lb_focus);
				}

				{
					String ls_nombre;

					ls_nombre     = lr_PeriodoCorte.getActivo();

					lb_focus = validateStyles(":fPeriodoCorteDetalle:idActivo", lfc_context, ls_nombre, lb_focus);
				}

				ipr_parameterRemote.salvarPeriodoCorte(
				    lr_PeriodoCorte, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				setPeriodoCorte(null);

				addMessageInfo("I", MessagesKeys.INCERSION_EXITOSA);

				ls_result = NavegacionCommon.PERIODO_CORTE;
			}

			{
				ExternalContext lec_externalContext;

				lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

				if(lec_externalContext != null)
				{
					Flash lf_flash;

					lf_flash = lec_externalContext.getFlash();

					if(lf_flash != null)
						lf_flash.setKeepMessages(true);
				}

				PrimeFaces.current().ajax().update("fPeriodooCorte:globalMsg");
			}

			findAllPeriodoCorte();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fPeriodoCorteDetalle:globalMsg");
		}
		catch(ParseException lb2be_e)
		{
			PrimeFaces.current().ajax().update("fPeriodoCorteDetalle:globalMsg");
		}

		return ls_result;
	}
}
