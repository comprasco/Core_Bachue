package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoRecurso;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanTipoRecurso.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanTipoRecurso")
@SessionScoped
public class BeanTipoRecurso extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7468054618495143235L;

	/** Propiedad ictr datos auditoria. */
	private Collection<TipoRecurso> ictr_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ir parametros. */
	private TipoRecurso ir_parametros;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de datos auditoria.
	 *
	 * @param actr_ctr asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<TipoRecurso> actr_ctr)
	{
		ictr_datosAuditoria = actr_ctr;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<TipoRecurso> getDatosAuditoria()
	{
		return ictr_datosAuditoria;
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
	public void setParametros(TipoRecurso ar_c)
	{
		ir_parametros = ar_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoRecurso getParametros()
	{
		if(ir_parametros == null)
			ir_parametros = new TipoRecurso();

		return ir_parametros;
	}

	/**
	 * Modifica el valor de tipo recurso.
	 *
	 * @param acr_cr asigna el valor a la propiedad tipo recurso
	 */
	public void setTipoRecurso(TipoRecurso acr_cr)
	{
		ir_parametros = acr_cr;
	}

	/**
	 * Retorna el valor de tipo recurso.
	 *
	 * @return el valor de tipo recurso
	 */
	public TipoRecurso getTipoRecurso()
	{
		return ir_parametros;
	}

	/**
	 * Metodo que indica si se desea insertar un TipoRecurso.
	 *
	 * @param arc_TipoRecurso seleccionado
	 *            objeto el cual se va a insertar o modificar
	 * @param ab_proceso            indica si se va a insetar o se va a modificar
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String botonInsertar(TipoRecurso arc_TipoRecurso, boolean ab_proceso)
	    throws B2BException
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			arc_TipoRecurso = new TipoRecurso();

			setTipoRecurso(arc_TipoRecurso);

			setInsertar(true);
		}
		else
		{
			TipoRecurso ltr_dato;

			ltr_dato     = null;

			ltr_dato = ipr_parameterRemote.findTipoRecursoById(arc_TipoRecurso);

			if(ltr_dato != null)
			{
				Collection<TipoRecurso> lctr_tr;
				lctr_tr = new ArrayList<TipoRecurso>();

				lctr_tr.add(ltr_dato);
				setTipoRecurso(ltr_dato);

				setDatosAuditoria(lctr_tr);
			}

			setInsertar(false);
		}

		ls_return = NavegacionCommon.TIPO_RECURSO_DETALLE;

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoRecurso> cargarTipoRecurso()
	{
		Collection<TipoRecurso> lrc_constantes;
		lrc_constantes = null;

		try
		{
			lrc_constantes = ipr_parameterRemote.findAllTipoRecurso(
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
		setDatosAuditoria(null);
		setInsertar(false);
	}

	/**
	 * Metodo para el manejo de inserciones o actualizaciones de TipoRecurso.
	 *
	 * @param ab_insertar            indica si se desea insertar o actualizar dependiendo su valor, si
	 *            su valor es true el inserta un nuevo registro, en cambio si su
	 *            valor es false realiza una actualizacion en la base de datos.
	 * @return devuelve el valor de String
	 */
	public String insertUpdateTipoRecurso(boolean ab_insertar)
	{
		FacesContext lfc_context;
		TipoRecurso  lcr_TipoRecursoInsertUpdate;
		boolean      lb_focus;
		String       ls_result;

		ls_result                       = null;
		lcr_TipoRecursoInsertUpdate     = getParametros();
		lfc_context                     = FacesContext.getCurrentInstance();
		lb_focus                        = true;

		try
		{
			{
				String ls_descripcion;
				ls_descripcion     = lcr_TipoRecursoInsertUpdate.getDescripcion();

				lb_focus = validateStyles(
					    ":fTipoRecursoDetalle:idItDescripcion", lfc_context, ls_descripcion, lb_focus
					);

				if(!StringUtils.isValidString(ls_descripcion))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
			}

			{
				String ls_activo;
				ls_activo     = lcr_TipoRecursoInsertUpdate.getActivo();

				lb_focus = validateStyles(":fTipoRecursoDetalle:idSOMActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
			}

			ipr_parameterRemote.insertUpdateTipoRecurso(
			    lcr_TipoRecursoInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
			
			getBeanReference().setTipoRecursoByActivo(null);

			ls_result = NavegacionCommon.TIPO_RECURSO;

			addMessage(MessagesKeys.PROCESO_COMPLETADO);

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
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fTipoRecurso:gTipoRecursoMsg");

			return null;
		}

		return ls_result;
	}
}
