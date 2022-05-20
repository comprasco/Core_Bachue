package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
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
 * Clase para el manejo de Tipo Documento Público.
 *
 * @author Sebastian Tafur
 */
@ManagedBean(name = "beanTipoDocumentoPublico")
@SessionScoped
public class BeanTipoDocumentoPublico extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -579114283674019362L;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ir parametros. */
	private TipoDocumentoPublico ir_parametros;

	/** Propiedad itdp tipo documento publico. */
	private TipoDocumentoPublico itdp_tipoDocumentoPublico;

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
	public void setParametros(TipoDocumentoPublico ar_c)
	{
		ir_parametros = ar_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoDocumentoPublico getParametros()
	{
		if(ir_parametros == null)
			ir_parametros = new TipoDocumentoPublico();

		return ir_parametros;
	}

	/**
	 * Modifica el valor de tipo documento publico.
	 *
	 * @param atr_tr asigna el valor a la propiedad tipo documento publico
	 */
	public void setTipoDocumentoPublico(TipoDocumentoPublico atr_tr)
	{
		itdp_tipoDocumentoPublico = atr_tr;
	}

	/**
	 * Retorna el valor de tipo documento publico.
	 *
	 * @return el valor de tipo documento publico
	 */
	public TipoDocumentoPublico getTipoDocumentoPublico()
	{
		return itdp_tipoDocumentoPublico;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @param atdp_docTipoDocumentoPublico correspondiente al valor del tipo de objeto TipoDocumentoPublico
	 * @param ab_proceso correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 */
	public String botonInsertar(TipoDocumentoPublico atdp_docTipoDocumentoPublico, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			atdp_docTipoDocumentoPublico = new TipoDocumentoPublico();

			setInsertar(true);
		}
		else
			setInsertar(false);

		setTipoDocumentoPublico(atdp_docTipoDocumentoPublico);

		ls_return = NavegacionCommon.TIPO_DOCUMENTO_PUBLICO_DETALLE;

		return ls_return;
	}

	/**
	 * Método para  cambiar estado para saber si se desea insertar un nuevo Tipo Documento Público.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setTipoDocumentoPublico(new TipoDocumentoPublico());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoDocumentoPublico");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_TIPO_DOCUMENTO_PUBLICO.
	 *
	 * @param atdp_tipodocumento objeto del cual se edxtrae el id para la consulta en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(TipoDocumentoPublico atdp_tipodocumento)
	    throws B2BException
	{
		setTipoDocumentoPublico(atdp_tipodocumento);
		setInsertar(false);
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_TIPO_DOCUMENTO_PUBLICO.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoDocumentoPublico> findAllTipoDocumentoPublico()
	{
		Collection<TipoDocumentoPublico> lcr_constantes;
		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllTipoDocumentoPublico();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_constantes;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_TIPO_DOCUMENTO_PUBLICO
	 * con un activo específico.
	 *
	 * @param as_s Indica que tipo activo traer de la tabla
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoDocumentoPublico> findAllTipoDocumentoPublico(String as_s)
	{
		Collection<TipoDocumentoPublico> lcr_constantes;
		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllTipoDocumentoPublico(as_s);
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
			TipoDocumentoPublico lr_parametros;

			lr_parametros = getTipoDocumentoPublico();

			{
				String ls_nombre;
				ls_nombre     = lr_parametros.getNombre();

				lb_focus = validateStyles(
					    ":fTipoDocumentoPublicoDetalle:idNombreTipoDocumentoPublico", lfc_context, ls_nombre, lb_focus
					);

				if(!StringUtils.isValidString(ls_nombre))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
			}

			{
				String ls_activo;
				ls_activo     = lr_parametros.getActivo();

				lb_focus = validateStyles(":fTipoDocumentoPublicoDetalle:idActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
			}

			ipr_parameterRemote.salvarTipoDocumentoPublico(
			    lr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			{
				BeanReference lbr_beanReference;

				lbr_beanReference = getBeanReference();

				lbr_beanReference.setDocumentoPublicoActivo(null);
				lbr_beanReference.setDocumentoPublico(null);
				lbr_beanReference.setIdTipoOficina(null);
			}

			setParametros(null);

			setTipoDocumentoPublico(null);

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
