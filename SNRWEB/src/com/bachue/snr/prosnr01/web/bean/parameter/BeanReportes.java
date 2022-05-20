package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.Reportes;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanReportes.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanReportes")
@SessionScoped
public class BeanReportes extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3604033385503610334L;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ir parametros. */
	private Reportes ir_parametros;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de insertar.
	 *
	 * @param ab_b asigna el valor a la propiedad insertar
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * Valida la propiedad insertar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en insertar
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param ar_c asigna el valor a la propiedad parametros
	 */
	public void setParametros(Reportes ar_c)
	{
		ir_parametros = ar_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public Reportes getParametros()
	{
		if(ir_parametros == null)
			ir_parametros = new Reportes();

		return ir_parametros;
	}

	/**
	 * Modifica el valor de reportes.
	 *
	 * @param acr_cr asigna el valor a la propiedad reportes
	 */
	public void setReportes(Reportes acr_cr)
	{
		ir_parametros = acr_cr;
	}

	/**
	 * Retorna el valor de reportes.
	 *
	 * @return el valor de reportes
	 */
	public Reportes getReportes()
	{
		return ir_parametros;
	}

	/**
	 * Metodo que indica si se desea insertar un REPORTE.
	 *
	 * @param arc_Reportes seleccionado
	 *            objeto el cual se va a insertar o modificar
	 * @param ab_proceso            indica si se va a insetar o se va a modificar
	 * @return devuelve el valor de String
	 */
	public String botonInsertar(Reportes arc_Reportes, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			arc_Reportes = new Reportes();

			setReportes(arc_Reportes);
			setInsertar(true);
		}
		else
		{
			setReportes(arc_Reportes);
			setInsertar(false);
		}

		ls_return = NavegacionCommon.REPORTES_DETALLE;

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Reportes> cargarReportes()
	{
		Collection<Reportes> lrc_constantes;
		lrc_constantes = null;

		try
		{
			lrc_constantes = ipr_parameterRemote.findAllReportes(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lrc_constantes;
	}

	/**
	 * Metodo que se encarga de reiniciar variables.
	 */
	public void clear()
	{
		setParametros(null);
		setInsertar(false);
	}

	/**
	 * Metodo para el manejo de inserciones o actualizaciones de Reportes.
	 *
	 * @param ab_insertar            indica si se desea insertar o actualizar dependiendo su valor, si
	 *            su valor es true el inserta un nuevo registro, en cambio si su
	 *            valor es false realiza una actualizacion en la base de datos.
	 * @return devuelve el valor de String
	 */
	public String insertUpdateReportes(boolean ab_insertar)
	{
		FacesContext lfc_context;
		boolean      lb_focus;

		lfc_context     = FacesContext.getCurrentInstance();
		lb_focus        = true;

		try
		{
			Reportes lcr_ReportesInsertUpdate;

			lcr_ReportesInsertUpdate = getParametros();

			if(lcr_ReportesInsertUpdate != null)
			{
				{
					String ls_codigo;
					ls_codigo     = lcr_ReportesInsertUpdate.getCodigo();

					lb_focus = validateStyles(":fReportesDetalle:idItCodigo", lfc_context, ls_codigo, lb_focus);

					if(!StringUtils.isValidString(ls_codigo))
						throw new B2BException(ErrorKeys.ERROR_CODIGO);
				}

				{
					String ls_estado;
					ls_estado     = lcr_ReportesInsertUpdate.getEstado();

					lb_focus = validateStyles(":fReportesDetalle:idSOMEstado", lfc_context, ls_estado, lb_focus);

					if(!StringUtils.isValidString(ls_estado))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO);
				}

				ipr_parameterRemote.insertUpdateReportes(
				    lcr_ReportesInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			return null;
		}

		return NavegacionCommon.REPORTES;
	}
}
