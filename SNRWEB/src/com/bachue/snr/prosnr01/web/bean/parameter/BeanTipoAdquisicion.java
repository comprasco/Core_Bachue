package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoAdquisicion;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase para el manejo de Tipo Adquisicion.
 *
 * @author Sebastian Tafur
 */
@ManagedBean(name = "beanTipoAdquisicion")
@SessionScoped
public class BeanTipoAdquisicion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6265140467138125826L;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ir parametros. */
	private TipoAdquisicion ir_parametros;

	/** Propiedad ita tipo adquisicion. */
	private TipoAdquisicion ita_tipoAdquisicion;

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
	public void setParametros(TipoAdquisicion ar_c)
	{
		ir_parametros = ar_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoAdquisicion getParametros()
	{
		if(ir_parametros == null)
			ir_parametros = new TipoAdquisicion();

		return ir_parametros;
	}

	/**
	 * Modifica el valor de tipo adquisicion.
	 *
	 * @param atr_tr asigna el valor a la propiedad tipo adquisicion
	 */
	public void setTipoAdquisicion(TipoAdquisicion atr_tr)
	{
		ita_tipoAdquisicion = atr_tr;
	}

	/**
	 * Retorna el valor de tipo adquisicion.
	 *
	 * @return el valor de tipo adquisicion
	 */
	public TipoAdquisicion getTipoAdquisicion()
	{
		return ita_tipoAdquisicion;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo Tipo
	 * Adquisición.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setTipoAdquisicion(new TipoAdquisicion());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoAdquisicion");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para consultar en la base de datos la tabla
	 * SDB_ACC_TIPO_ADQUISICION.
	 *
	 * @param ata_tipoadquisicion            objeto del cual se edxtrae el id para la consulta en la base de
	 *            datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(TipoAdquisicion ata_tipoadquisicion)
	    throws B2BException
	{
		setTipoAdquisicion(ata_tipoadquisicion);
		setInsertar(false);
	}

	/**
	 * Método para encontrar todos los registros de la tabla
	 * SDB_ACC_TIPO_ADQUISICION.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoAdquisicion> findAllTipoAdquisicion()
	{
		Collection<TipoAdquisicion> lcr_constantes;
		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllTipoAdquisicion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_constantes;
	}

	/**
	 * Método para encontrar todos los registros de la tabla
	 * SDB_ACC_TIPO_ADQUISICION con un activo específico.
	 *
	 * @param as_s            Indica que tipo activo traer de la tabla
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoAdquisicion> findAllTipoAdquisicion(String as_s)
	{
		Collection<TipoAdquisicion> lcr_constantes;
		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllTipoAdquisicion(as_s);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_constantes;
	}

	/**
	 * Método para insertar o actualizar en la base de datos.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvar()
	{
		String       ls_result;
		boolean      lb_focus;
		FacesContext lfc_context;

		ls_result       = null;
		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			TipoAdquisicion lr_parametros;

			lr_parametros = getTipoAdquisicion();

			{
				String ls_nombre;
				ls_nombre     = lr_parametros.getNombre();

				lb_focus = validateStyles(
					    ":fTipoAdquisicionDetalle:idNombreTipoAdquisicion", lfc_context, ls_nombre, lb_focus
					);

				if(!StringUtils.isValidString(ls_nombre))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
			}

			{
				String ls_activo;
				ls_activo     = lr_parametros.getActivo();

				lb_focus = validateStyles(
					    "fTipoAdquisicionDetalle:idActivoTipoAdquisiscion", lfc_context, ls_activo, lb_focus
					);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
			}

			ipr_parameterRemote.salvarTipoAdquisicion(
			    lr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			getBeanReference().setAdquisicionActivo(null);

			setParametros(null);

			setTipoAdquisicion(null);

			ls_result = NavegacionCommon.TIPO_DOCUMENTO_PUBLICO;

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
			PrimeFaces.current().ajax().update("fTipoRecepcion:globalMsg");
		}

		return ls_result;
	}
}
