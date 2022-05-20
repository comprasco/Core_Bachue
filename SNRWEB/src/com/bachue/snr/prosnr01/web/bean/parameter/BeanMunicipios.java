package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanMunicipios.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanMunicipios")
@SessionScoped
public class BeanMunicipios extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3527231864457961599L;

	/** Propiedad ic parametros. */
	private Municipio ic_parametros;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

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
	public void setParametros(Municipio ac_c)
	{
		ic_parametros = ac_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public Municipio getParametros()
	{
		if(ic_parametros == null)
			ic_parametros = new Municipio();

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
			Municipio lc_parametros;
			lc_parametros = getParametros();

			if(lc_parametros != null)
			{
				String ls_idPais;
				String ls_idDepartamento;
				String ls_idMunicipio;

				ls_idPais             = FacesUtils.getStringFacesParameter("idPais");
				ls_idDepartamento     = FacesUtils.getStringFacesParameter("idDepartamento");
				ls_idMunicipio        = FacesUtils.getStringFacesParameter("idMunicipio");

				if(StringUtils.isValidString(ls_idPais))
				{
					lc_parametros.setIdPais(ls_idPais);
					lc_parametros.setIdDepartamento(ls_idDepartamento);
					lc_parametros.setIdMunicipio(ls_idMunicipio);
					setParametros(ipr_parameterRemote.findMunicipioById(lc_parametros));
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
	public Collection<Municipio> findAllMunicipios()
	{
		Collection<Municipio> lcc_constantes;
		lcc_constantes = null;

		try
		{
			lcc_constantes = ipr_parameterRemote.findAllMunicipios();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcc_constantes;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentos()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			if(getParametros().getIdPais() != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(getParametros().getIdPais());

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Pais.
	 */
	public void pais()
	{
		getParametros().setIdDepartamento(null);
		findDepartamentos();
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
		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			Municipio lm_parametros;

			lm_parametros = getParametros();

			if(lm_parametros != null)
			{
				{
					String ls_idPais;

					ls_idPais     = lm_parametros.getIdPais();
					lb_focus      = validateStyles(":fMunicipiosDetalle:idMunIdPais", lfc_context, ls_idPais, lb_focus);

					if(!StringUtils.isValidString(ls_idPais))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_PAIS);
				}

				{
					String ls_idDepartamento;

					ls_idDepartamento     = lm_parametros.getIdDepartamento();
					lb_focus              = validateStyles(
						    ":fMunicipiosDetalle:idMunIdDepartamento", lfc_context, ls_idDepartamento, lb_focus
						);

					if(!StringUtils.isValidString(ls_idDepartamento))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_DEPARTAMENTO);
				}

				{
					String ls_idMunicipio;

					ls_idMunicipio     = lm_parametros.getIdMunicipio();
					lb_focus           = validateStyles(
						    ":fMunicipiosDetalle:idMunIdMunicipio", lfc_context, ls_idMunicipio, lb_focus
						);

					if(!StringUtils.isValidString(ls_idMunicipio))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_MUNICIPIO);
				}

				{
					String ls_nombre;

					ls_nombre     = lm_parametros.getNombre();
					lb_focus      = validateStyles(":fMunicipiosDetalle:idMunNombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_implementandoNupre;

					ls_implementandoNupre     = lm_parametros.getImplementadoNupre();
					lb_focus                  = validateStyles(
						    ":fMunicipiosDetalle:idMunImNup", lfc_context, ls_implementandoNupre, lb_focus
						);

					if(!StringUtils.isValidString(ls_implementandoNupre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_IMPLEMENTADO_NUPRE);
				}

				{
					String ls_activo;

					ls_activo     = lm_parametros.getActivo();
					lb_focus      = validateStyles(":fMunicipiosDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				{
					String ls_zonaRiesgo;

					ls_zonaRiesgo     = lm_parametros.getZonaRiesgo();
					lb_focus          = validateStyles(
						    ":fMunicipiosDetalle:idZonaRiesgo", lfc_context, ls_zonaRiesgo, lb_focus
						);

					if(!StringUtils.isValidString(ls_zonaRiesgo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ZONA_RIESGO);
				}

				lm_parametros.setIdUsuarioCreacion(getUserId());
				lm_parametros.setIdUsuarioModificacion(getUserId());

				ipr_parameterRemote.salvarMunicipio(
				    lm_parametros, isInsertar(), getUserId(), getRemoteIpAddress(), getLocalIpAddress()
				);

				{
					BeanReference lbr_beanReference;

					lbr_beanReference = getBeanReference();

					lbr_beanReference.setMunicipiosPorPais(null);
					lbr_beanReference.setMunicipios(null);
				}

				setParametros(null);

				ls_result = NavegacionCommon.MUNICIPIOS;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fMunicipiosDetalle");
		}

		return ls_result;
	}
}
