package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoIntegracionGobernacion;

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
 * Clase que contiene todos las propiedades y funcionalidad de BeanTipoIntegracionGobernacion.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanTipoIntegracionGobernacion")
@SessionScoped
public class BeanTipoIntegracionGobernacion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 458420083005645226L;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad id parametros. */
	private TipoIntegracionGobernacion id_parametros;

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
	 * @param ac_c asigna el valor a la propiedad parametros
	 */
	public void setParametros(TipoIntegracionGobernacion ac_c)
	{
		id_parametros = ac_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoIntegracionGobernacion getParametros()
	{
		if(id_parametros == null)
			id_parametros = new TipoIntegracionGobernacion();

		return id_parametros;
	}

/**
 * Metodo para  cambiar estado con el fin de saber si se desea insertar un departamento.
 */
	public void cambiarEstado()
	{
		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertar");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));

		setParametros(null);
	}

/**
 * Metodo para traer los registros del proceso automatico que coincida con un id_procesoautomaticos
 * especifico.
 */
	public void consultaDetallada()
	{
		try
		{
			TipoIntegracionGobernacion ld_parametros;
			ld_parametros = getParametros();

			if(ld_parametros != null)
			{
				String ls_idTipoIntegracion;

				ls_idTipoIntegracion = FacesUtils.getStringFacesParameter("idTipoIntegracion");

				if(StringUtils.isValidString(ls_idTipoIntegracion))
				{
					ld_parametros.setIdTipoIntegracion(ls_idTipoIntegracion);
					setParametros(ipr_parameterRemote.findTipoIntegracionGobernacionById(ld_parametros));
					setInsertar(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Metodo para traer todos los registros de la tabla SDB_PGN_TIPO_INTEGRACION_GOBERNACION.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoIntegracionGobernacion> findAllTipoIntegracionGobernacion()
	{
		Collection<TipoIntegracionGobernacion> lcd_constantes;
		lcd_constantes = null;

		try
		{
			lcd_constantes = ipr_parameterRemote.findAllTipoIntegracionGobernacion(false);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcd_constantes;
	}

	/**
	 * Metodo para salvar un Tipo proceso automatico ya sea para modificar o insertar registros.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvar()
	{
		String                     ls_result;
		TipoIntegracionGobernacion ld_parametros;
		FacesContext               lfc_context;
		boolean                    lb_focus;

		ls_result         = null;
		ld_parametros     = getParametros();
		lfc_context       = FacesContext.getCurrentInstance();
		lb_focus          = true;

		{
			String ls_datoParaValidar;
			ls_datoParaValidar     = ld_parametros.getIdTipoIntegracion();
			lb_focus               = validateStyles(
				    ":fTipoIntegracionGobernacionDetalle:idTipoIntegracion", lfc_context, ls_datoParaValidar, lb_focus
				);

			ls_datoParaValidar     = ld_parametros.getDescripcion();
			lb_focus               = validateStyles(
				    ":fTipoIntegracionGobernacionDetalle:idDescripcion", lfc_context, ls_datoParaValidar, lb_focus
				);

			ls_datoParaValidar     = ld_parametros.getActivo();
			lb_focus               = validateStyles(
				    ":fTipoIntegracionGobernacionDetalle:idActivo", lfc_context, ls_datoParaValidar, lb_focus
				);

			PrimeFaces.current().ajax().update("fTipoIntegracionGobernacionDetalle");
		}

		try
		{
			ld_parametros.setIdUsuarioCreacion(getUserId());
			ld_parametros.setIdUsuarioModificacion(getUserId());

			ipr_parameterRemote.salvarTipoIntegracionGobernacion(
			    ld_parametros, isInsertar(), getUserId(), getRemoteIpAddress(), getLocalIpAddress()
			);

			getBeanReference().setTipoIntegracionGobernacion(null);

			setParametros(null);

			ls_result = NavegacionCommon.TIPO_INTEGRACION_GOBERNACION;

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
			PrimeFaces.current().ajax().update("fProcesoAutomatico:globalMsg");
		}

		return ls_result;
	}
}
