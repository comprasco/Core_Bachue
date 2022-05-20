package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;

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
 * Clase que contiene todos las funcionalidades del bean de Zona Registral
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanZonaRegistral")
@SessionScoped
public class BeanZonaRegistral extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -10996797782787935L;
	/** Propiedad iczr zonas registrales. */
	Collection<ZonaRegistral> iczr_zonasRegistrales;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ic parametros. */
	private ZonaRegistral ic_parametros;

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
	public void setParametros(ZonaRegistral ac_c)
	{
		ic_parametros = ac_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public ZonaRegistral getParametros()
	{
		if(ic_parametros == null)
			ic_parametros = new ZonaRegistral();

		return ic_parametros;
	}

	/**
	 * Modifica el valor de zonas registrales.
	 *
	 * @param aczr_czr asigna el valor a la propiedad zonas registrales
	 */
	public void setZonasRegistrales(Collection<ZonaRegistral> aczr_czr)
	{
		iczr_zonasRegistrales = aczr_czr;
	}

	/**
	 * Retorna el valor de zonas registrales.
	 *
	 * @return el valor de zonas registrales
	 */
	public Collection<ZonaRegistral> getZonasRegistrales()
	{
		if(!CollectionUtils.isValidCollection(iczr_zonasRegistrales))
			findAllZonaRegistrales();

		return iczr_zonasRegistrales;
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

		if(isInsertar())
		{
			ZonaRegistral lzr_zonaRegistral;

			lzr_zonaRegistral = getParametros();

			if(lzr_zonaRegistral == null)
				lzr_zonaRegistral = new ZonaRegistral();

			lzr_zonaRegistral.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
		setZonasRegistrales(null);
		setParametros(null);
	}

	/**
	 * Consulta detallada.
	 *
	 * @param azr_zonaRegistral correspondiente al valor del tipo de objeto ZonaRegistral
	 */
	public void consultaDetallada(ZonaRegistral azr_zonaRegistral)
	{
		try
		{
			ZonaRegistral lc_parametros;
			lc_parametros = getParametros();

			if(lc_parametros != null)
			{
				String ls_idZonaRegistral;

				ls_idZonaRegistral = azr_zonaRegistral.getIdZonaRegistral();

				if(StringUtils.isValidString(ls_idZonaRegistral))
				{
					lc_parametros.setIdZonaRegistral(ls_idZonaRegistral);
					setParametros(ipr_parameterRemote.findZonaRegistralById(lc_parametros));
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
	 * Find all zona registrales.
	 */
	public void findAllZonaRegistrales()
	{
		try
		{
			setZonasRegistrales(ipr_parameterRemote.findAllZonaRegistrales());
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
	 *
	 */
	public Collection<CirculoRegistral> findCirculoRegistral()
	{
		Collection<CirculoRegistral> lcidt_datos;

		try
		{
			lcidt_datos = irr_referenceRemote.findAllCirculosRegistralesActivos(
				    true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
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
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Vereda> findVeredas()
	{
		Collection<Vereda> lcv_veredas;
		lcv_veredas = null;

		String ls_idPais;
		String ls_idDepartamento;
		String ls_idMunicipio;

		ls_idPais             = getParametros().getIdPais();
		ls_idDepartamento     = getParametros().getIdDepartamento();
		ls_idMunicipio        = getParametros().getIdMunicipio();

		if((ls_idPais != null) && (ls_idDepartamento != null) && (ls_idMunicipio != null))
		{
			try
			{
				Vereda lm_parametros;
				lm_parametros = new Vereda();

				lm_parametros.setIdPais(ls_idPais);
				lm_parametros.setIdDepartamento(ls_idDepartamento);
				lm_parametros.setIdMunicipio(ls_idMunicipio);

				lcv_veredas = irr_referenceRemote.findVeredas(lm_parametros,false);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				lcv_veredas = null;
			}
		}

		return lcv_veredas;
	}

	/**
	 * Municipio.
	 */
	public void municipio()
	{
		getParametros().setIdVereda(null);
		findVeredas();
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
	 * Retorna el valor del objeto de String.
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
			ZonaRegistral lzr_parametros;

			lzr_parametros = getParametros();

			if(lzr_parametros != null)
			{
				{
					String ls_idCirculo;

					ls_idCirculo     = lzr_parametros.getIdCirculo();
					lb_focus         = validateStyles(
						    ":fZonaRegistralDetalle:idCirculo", lfc_context, ls_idCirculo, lb_focus
						);

					if(!StringUtils.isValidString(ls_idCirculo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
				}

				{
					String ls_idPais;

					ls_idPais     = lzr_parametros.getIdPais();
					lb_focus      = validateStyles(":fZonaRegistralDetalle:idPais", lfc_context, ls_idPais, lb_focus);

					if(!StringUtils.isValidString(ls_idPais))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
				}

				{
					String ls_idDepartamento;

					ls_idDepartamento     = lzr_parametros.getIdDepartamento();
					lb_focus              = validateStyles(
						    ":fZonaRegistralDetalle:idDepartamento", lfc_context, ls_idDepartamento, lb_focus
						);

					if(!StringUtils.isValidString(ls_idDepartamento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
				}

				{
					String ls_idMunicipio;

					ls_idMunicipio     = lzr_parametros.getIdMunicipio();
					lb_focus           = validateStyles(
						    ":fZonaRegistralDetalle:idMunicipio", lfc_context, ls_idMunicipio, lb_focus
						);

					if(!StringUtils.isValidString(ls_idMunicipio))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
				}

				{
					String ls_idVereda;

					ls_idVereda     = lzr_parametros.getIdVereda();
					lb_focus        = validateStyles(
						    ":fZonaRegistralDetalle:idVereda", lfc_context, ls_idVereda, lb_focus
						);

					if(!StringUtils.isValidString(ls_idVereda))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_VEREDA);
				}

				{
					String ls_activo;

					ls_activo     = lzr_parametros.getActivo();
					lb_focus      = validateStyles(":fZonaRegistralDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				lzr_parametros.setIdUsuarioCreacion(getUserId());
				lzr_parametros.setIdUsuarioModificacion(getUserId());

				ipr_parameterRemote.salvarZonaRegistral(
				    lzr_parametros, isInsertar(), getUserId(), getRemoteIpAddress(), getLocalIpAddress()
				);

				clear();

				ls_result = NavegacionCommon.ZONA_REGISTRAL;

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
			PrimeFaces.current().ajax().update("fZonaRegistralDetalle");
		}

		return ls_result;
	}
}
