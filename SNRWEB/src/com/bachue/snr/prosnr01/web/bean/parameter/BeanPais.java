package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanPais.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanPais")
@SessionScoped
public class BeanPais extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6853084254943684295L;

	/** Propiedad ic parametros. */
	private Pais ic_parametros;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

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
	public void setParametros(Pais ac_c)
	{
		ic_parametros = ac_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public Pais getParametros()
	{
		if(ic_parametros == null)
			ic_parametros = new Pais();

		return ic_parametros;
	}

	/**
	 * Cambiar estado.
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
	 * Consulta detallada.
	 */
	public void consultaDetallada()
	{
		try
		{
			Pais lc_parametros;
			lc_parametros = getParametros();

			if(lc_parametros != null)
			{
				String ls_idPais;

				ls_idPais = FacesUtils.getStringFacesParameter("idPais");

				if(StringUtils.isValidString(ls_idPais))
				{
					lc_parametros.setIdPais(ls_idPais);
					setParametros(ipr_parameterRemote.findPaisById(lc_parametros));
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
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Pais> findAllPaises()
	{
		Collection<Pais> lcc_constantes;
		lcc_constantes = null;

		try
		{
			lcc_constantes = ipr_parameterRemote.findAllPaises();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcc_constantes;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvar()
	{
		String       ls_result;
		FacesContext lfc_context;
		boolean      lb_focus;

		ls_result       = null;
		lfc_context     = FacesContext.getCurrentInstance();
		lb_focus        = true;

		try
		{
			Pais lc_parametros;

			lc_parametros = getParametros();

			if(lc_parametros != null)
			{
				{
					String ls_nombre;

					ls_nombre     = lc_parametros.getNombre();
					lb_focus      = validateStyles(":fPaisDetalle:idNombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;

					ls_activo     = lc_parametros.getActivo();
					lb_focus      = validateStyles(":fPaisDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				{
					String ls_nacionalidad;

					ls_nacionalidad     = lc_parametros.getNacionalidad();
					lb_focus            = validateStyles(
						    ":fPaisDetalle:idNacionalidad", lfc_context, ls_nacionalidad, lb_focus
						);

					if(!StringUtils.isValidString(ls_nacionalidad))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NACIONALIDAD);
				}

				{
					String ls_codigoPais;

					ls_codigoPais     = lc_parametros.getCodigoPais();
					lb_focus          = validateStyles(
						    ":fPaisDetalle:idCodigoPais", lfc_context, ls_codigoPais, lb_focus
						);

					if(!StringUtils.isValidString(ls_codigoPais))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_CODIGO_PAIS);
				}

				{
					String ls_indicativoTelefonicoPais;

					ls_indicativoTelefonicoPais     = lc_parametros.getIndicativoTelefonicoPais();
					lb_focus                        = validateStyles(
						    ":fPaisDetalle:idIndicativoTelefonicoPais", lfc_context, ls_indicativoTelefonicoPais,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_indicativoTelefonicoPais))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_INDICATIVO_TELEFONICO);
				}

				ipr_parameterRemote.salvarPais(
				    lc_parametros, isInsertar(), getUserId(), getRemoteIpAddress(), getLocalIpAddress()
				);

				getBeanReference().setPaises(null);

				setParametros(null);

				ls_result = NavegacionCommon.PAISES;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fPaisDetalle");
		}

		return ls_result;
	}
}
