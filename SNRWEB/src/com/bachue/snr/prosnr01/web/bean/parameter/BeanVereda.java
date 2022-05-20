package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;

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
 * Clase que contiene todos las propiedades y funcionalidades del Bean de Vereda
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanVereda")
@SessionScoped
public class BeanVereda extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8892194167864777230L;

	/** Propiedad icv veredas. */
	private Collection<Vereda> icv_veredas;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ic parametros. */
	private Vereda ic_parametros;

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
	public void setParametros(Vereda ac_c)
	{
		ic_parametros = ac_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public Vereda getParametros()
	{
		if(ic_parametros == null)
			ic_parametros = new Vereda();

		return ic_parametros;
	}

	/**
	 * Modifica el valor de veredas.
	 *
	 * @param acv_cv asigna el valor a la propiedad veredas
	 */
	public void setVeredas(Collection<Vereda> acv_cv)
	{
		icv_veredas = acv_cv;
	}

	/**
	 * Retorna el valor de veredas.
	 *
	 * @return el valor de veredas
	 */
	public Collection<Vereda> getVeredas()
	{
		if(!CollectionUtils.isValidCollection(icv_veredas))
			findAllVeredas();

		return icv_veredas;
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
	 * Clear.
	 */
	public void clear()
	{
		setParametros(null);
		setVeredas(null);
	}

	/**
	 * Consulta detallada.
	 */
	public void consultaDetallada()
	{
		try
		{
			Vereda lc_parametros;
			lc_parametros = getParametros();

			if(lc_parametros != null)
			{
				String ls_idPais;
				String ls_idDepartamento;
				String ls_idMunicipio;
				String ls_idVereda;

				ls_idPais             = FacesUtils.getStringFacesParameter("idPais");
				ls_idDepartamento     = FacesUtils.getStringFacesParameter("idDepartamento");
				ls_idMunicipio        = FacesUtils.getStringFacesParameter("idMunicipio");
				ls_idVereda           = FacesUtils.getStringFacesParameter("idVereda");

				if(StringUtils.isValidString(ls_idPais))
				{
					lc_parametros.setIdPais(ls_idPais);
					lc_parametros.setIdDepartamento(ls_idDepartamento);
					lc_parametros.setIdMunicipio(ls_idMunicipio);
					lc_parametros.setIdVereda(ls_idVereda);
					setParametros(ipr_parameterRemote.findVeredaById(lc_parametros));
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
	 * Departamento.
	 */
	public void departamento()
	{
		getParametros().setIdMunicipio(null);
		findMunicipios();
	}

	/**
	 * Find all veredas.
	 */
	public void findAllVeredas()
	{
		Vereda lv_vereda;

		lv_vereda = getParametros();

		if(lv_vereda != null)
		{
			try
			{
				setVeredas(irr_referenceRemote.findVeredas(lv_vereda, true));

				PrimeFaces.current().ajax().update("fVereda:globalMsg");
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
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
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Municipio> findMunicipios()
	{
		Collection<Municipio> lcm_municipios;
		lcm_municipios = null;

		String ls_idPais;
		String ls_idDepartamento;

		ls_idPais             = getParametros().getIdPais();
		ls_idDepartamento     = getParametros().getIdDepartamento();

		if((ls_idPais != null) && (ls_idDepartamento != null))
		{
			try
			{
				Municipio lm_parametros;
				lm_parametros = new Municipio();

				lm_parametros.setIdPais(ls_idPais);
				lm_parametros.setIdDepartamento(ls_idDepartamento);

				lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				lcm_municipios = null;
			}
		}

		return lcm_municipios;
	}

	/**
	 * Pais.
	 */
	public void pais()
	{
		getParametros().setIdDepartamento(null);
		findDepartamentos();
		findMunicipios();
	}

	/**
	 * Retorna el valor del objeto de String a la vista despues de salvar
	 *
	 * @return devuelve el valor de String
	 *
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
			Vereda lv_parametros;

			lv_parametros = getParametros();

			if(lv_parametros != null)
			{
				{
					String ls_idPais;

					ls_idPais     = lv_parametros.getIdPais();
					lb_focus      = validateStyles(":fVeredaDetalle:idPais", lfc_context, ls_idPais, lb_focus);

					if(!StringUtils.isValidString(ls_idPais))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
				}

				{
					String ls_idDepartamento;

					ls_idDepartamento     = lv_parametros.getIdDepartamento();
					lb_focus              = validateStyles(
						    ":fVeredaDetalle:idDepartamento", lfc_context, ls_idDepartamento, lb_focus
						);

					if(!StringUtils.isValidString(ls_idDepartamento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
				}

				{
					String ls_idMunicipio;

					ls_idMunicipio     = lv_parametros.getIdMunicipio();
					lb_focus           = validateStyles(
						    ":fVeredaDetalle:idMunicipio", lfc_context, ls_idMunicipio, lb_focus
						);

					if(!StringUtils.isValidString(ls_idMunicipio))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
				}

				{
					String ls_idVereda;

					ls_idVereda     = lv_parametros.getIdVereda();
					lb_focus        = validateStyles(":fVeredaDetalle:idVereda", lfc_context, ls_idVereda, lb_focus);

					if(!StringUtils.isValidString(ls_idVereda))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_VEREDA);
				}

				{
					String ls_cabeceraMunicipal;

					ls_cabeceraMunicipal     = lv_parametros.getCabeceraMunicipal();
					lb_focus                 = validateStyles(
						    ":fVeredaDetalle:idCabeceraMun", lfc_context, ls_cabeceraMunicipal, lb_focus
						);

					if(!StringUtils.isValidString(ls_cabeceraMunicipal))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_CABECERA_MUNICIPAL);
				}

				{
					String ls_activo;

					ls_activo     = lv_parametros.getActivo();
					lb_focus      = validateStyles(":fVeredaDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				lv_parametros.setIdUsuarioCreacion(getUserId());
				lv_parametros.setIdUsuarioModificacion(getUserId());

				ipr_parameterRemote.salvarVereda(
				    lv_parametros, isInsertar(), getUserId(), getRemoteIpAddress(), getLocalIpAddress()
				);
				
				getBeanReference().setVeredas(null);

				clear();

				findAllVeredas();

				ls_result = NavegacionCommon.VEREDAS;

				addMessage(MessagesKeys.PROCESO_COMPLETADO);

				{
					ExternalContext lec_externalContext;

					lec_externalContext = lfc_context.getExternalContext();

					if(lec_externalContext != null)
					{
						Flash lf_flash;

						lf_flash = lec_externalContext.getFlash();

						if(lf_flash != null)
							lf_flash.setKeepMessages(true);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fVereda:globalMsg");
		}

		return ls_result;
	}
}
