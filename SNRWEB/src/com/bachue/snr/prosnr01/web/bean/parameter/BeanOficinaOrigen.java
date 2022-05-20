package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Oficina Origen.
 *
 * @author Sebastian Tafur
 */
@ManagedBean(name = "beanOficinaOrigen")
@SessionScoped
public class BeanOficinaOrigen extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5486536522831184683L;

	/** Propiedad icmt datos auditoria. */
	private Collection<OficinaOrigen> icmt_datosAuditoria;

	/** Propiedad imt parametros. */
	private OficinaOrigen imt_parametros;

	/** Propiedad ioo oficina origen. */
	private OficinaOrigen ioo_oficinaOrigen;

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
	 * Modifica el valor de datos auditoria.
	 *
	 * @param datosAuditoria asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<OficinaOrigen> datosAuditoria)
	{
		icmt_datosAuditoria = datosAuditoria;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<OficinaOrigen> getDatosAuditoria()
	{
		return icmt_datosAuditoria;
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
	 * Modifica el valor de oficina origen.
	 *
	 * @param aoo_oo asigna el valor a la propiedad oficina origen
	 */
	public void setOficinaOrigen(OficinaOrigen aoo_oo)
	{
		ioo_oficinaOrigen = aoo_oo;
	}

	/**
	 * Retorna el valor de oficina origen.
	 *
	 * @return el valor de oficina origen
	 */
	public OficinaOrigen getOficinaOrigen()
	{
		return ioo_oficinaOrigen;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param parametros asigna el valor a la propiedad parametros
	 */
	public void setParametros(OficinaOrigen parametros)
	{
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public OficinaOrigen getParametros()
	{
		return imt_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Motivo Trámite.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setOficinaOrigen(new OficinaOrigen());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCausalAprobacionDevolucion");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_MOTIVO_TRAMITE.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto OficinaOrigen
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(OficinaOrigen acad_cad)
	    throws B2BException
	{
		if(acad_cad != null)
		{
			OficinaOrigen lcad_datos;

			lcad_datos = ipr_parameterRemote.findOficinaOrigenById(acad_cad);

			if(lcad_datos != null)
			{
				Collection<OficinaOrigen> lccad_ccad;
				lccad_ccad = new ArrayList<OficinaOrigen>();

				lccad_ccad.add(lcad_datos);
				setOficinaOrigen(lcad_datos);

				setDatosAuditoria(lccad_ccad);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_MOTIVO_TRAMITE.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<OficinaOrigen> findAllOficinaOrigen()
	{
		Collection<OficinaOrigen> lcr_constantes;

		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllOficinaOrigen();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_constantes;
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
			String ls_pais;

			ls_pais = getOficinaOrigen().getIdPais();

			if(StringUtils.isValidString(ls_pais))
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(ls_pais);

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
	 */
	public Collection<Municipio> findMunicipios()
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		try
		{
			Municipio lm_parametros;

			lm_parametros = new Municipio();

			OficinaOrigen loo_oficinaOrigen;

			loo_oficinaOrigen = getOficinaOrigen();

			if(loo_oficinaOrigen != null)
			{
				lm_parametros.setIdPais(loo_oficinaOrigen.getIdPais());
				lm_parametros.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());

				if(StringUtils.isValidString(lm_parametros.getIdDepartamento()))
					lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
				else
					lcm_municipios = null;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_MOTIVO_TRAMITE
	 * que coincida con un id específico.
	 */
	public void findOficinaOrigenById()
	{
		try
		{
			OficinaOrigen ld_parametros;

			ld_parametros = getOficinaOrigen();

			if(ld_parametros != null)
			{
				setParametros(ipr_parameterRemote.findOficinaOrigenById(ld_parametros));
				setInsertar(false);
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
	public Collection<Vereda> findVereda()
	{
		Collection<Vereda> lcv_veredas;

		lcv_veredas = null;

		try
		{
			OficinaOrigen loo_oficinaOrigen;

			loo_oficinaOrigen = getOficinaOrigen();

			if(loo_oficinaOrigen != null)
			{
				Vereda lv_vereda;

				lv_vereda = new Vereda();

				lv_vereda.setIdPais(loo_oficinaOrigen.getIdPais());
				lv_vereda.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());
				lv_vereda.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());

				if(StringUtils.isValidString(lv_vereda.getIdMunicipio()))
					lcv_veredas = irr_referenceRemote.findVeredas(lv_vereda, false);
				else
					lcv_veredas = null;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcv_veredas = null;
		}

		return lcv_veredas;
	}

	/**
	 * Método para salvar la inserción o actualización para la tabla SDB_PGN_MOTIVO_TRAMITE.
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
			OficinaOrigen lr_parametros;

			lr_parametros = getOficinaOrigen();

			if(lr_parametros != null)
			{
				{
					String ls_idTipoOficina;
					ls_idTipoOficina     = lr_parametros.getIdTipoOficina();

					lb_focus = validateStyles(
						    ":fOficinaOrigenDetalle:idTipoOficina", lfc_context, ls_idTipoOficina, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoOficina))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_OFICINA);
				}

				{
					String ls_idPais;
					ls_idPais     = lr_parametros.getIdPais();

					lb_focus = validateStyles(":fOficinaOrigenDetalle:idPais", lfc_context, ls_idPais, lb_focus);

					if(!StringUtils.isValidString(ls_idPais))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
				}

				{
					String ls_idDepartamento;
					ls_idDepartamento     = lr_parametros.getIdDepartamento();

					lb_focus = validateStyles(
						    ":fOficinaOrigenDetalle:idDepartamento", lfc_context, ls_idDepartamento, lb_focus
						);

					if(!StringUtils.isValidString(ls_idDepartamento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
				}

				{
					String ls_idMunicipio;
					ls_idMunicipio     = lr_parametros.getIdMunicipio();

					lb_focus = validateStyles(
						    ":fOficinaOrigenDetalle:idMunicipio", lfc_context, ls_idMunicipio, lb_focus
						);

					if(!StringUtils.isValidString(ls_idMunicipio))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
				}

				{
					String ls_idVereda;
					ls_idVereda     = lr_parametros.getIdVereda();

					lb_focus = validateStyles(":fOficinaOrigenDetalle:idVereda", lfc_context, ls_idVereda, lb_focus);

					if(!StringUtils.isValidString(ls_idVereda))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_VEREDA);
				}

				{
					String ls_Activo;
					ls_Activo     = lr_parametros.getActivo();

					lb_focus = validateStyles(":fOficinaOrigenDetalle:idActivo", lfc_context, ls_Activo, lb_focus);

					if(!StringUtils.isValidString(ls_Activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				{
					String ls_NotificarCorrespondencia;
					ls_NotificarCorrespondencia     = lr_parametros.getNotificarCorrespondencia();

					lb_focus = validateStyles(
						    ":fOficinaOrigenDetalle:idNotificarCorrespondecia", lfc_context, ls_NotificarCorrespondencia,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_NotificarCorrespondencia))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NOTIFICAR_CORRESPONDENCIA);
				}

				{
					BigDecimal lbd_version;
					lbd_version     = lr_parametros.getVersion();

					lb_focus = validateStyles(":fOficinaOrigenDetalle:idVersion", lfc_context, lbd_version, lb_focus);

					if(!NumericUtils.isValidBigDecimal(lbd_version))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_VERSION);
				}

				ipr_parameterRemote.salvarOficinaOrigen(
				    lr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				{
					BeanReference lbr_beanReference;
					lbr_beanReference = getBeanReference();

					lbr_beanReference.setOficinaOrigenById(null);
					lbr_beanReference.setOficinaOrigenByIdCollection(null);
					lbr_beanReference.setOficinaOrigen(null);
				}

				setParametros(null);

				setOficinaOrigen(null);

				ls_result = NavegacionCommon.OFICINA_ORIGEN;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
