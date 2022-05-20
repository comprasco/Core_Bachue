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
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import com.bachue.snr.prosnr04.model.pgn.MedioRecaudo;
import com.bachue.snr.prosnr04.model.pgn.PuntoRecaudo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Punto Recaudo.
 *
 * @author Carlos Calderón
 */
@ManagedBean(name = "beanPuntoRecaudo")
@SessionScoped
public class BeanPuntoRecaudo extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7600541462629954324L;

	/** Propiedad icpr datos auditoria. */
	private Collection<PuntoRecaudo> icpr_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ipr parametros. */
	private PuntoRecaudo ipr_parametros;

	/** Propiedad ipr punto recaudo. */
	private PuntoRecaudo ipr_puntoRecaudo;

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
	 * @param acpr_cpr asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<PuntoRecaudo> acpr_cpr)
	{
		icpr_datosAuditoria = acpr_cpr;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<PuntoRecaudo> getDatosAuditoria()
	{
		return icpr_datosAuditoria;
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
	 * @param apr_param asigna el valor a la propiedad parametros
	 */
	public void setParametros(PuntoRecaudo apr_param)
	{
		ipr_parametros = apr_param;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public PuntoRecaudo getParametros()
	{
		if(ipr_parametros == null)
			ipr_parametros = new PuntoRecaudo();

		return ipr_parametros;
	}

	/**
	 * Modifica el valor de punto recaudo.
	 *
	 * @param apr_pr asigna el valor a la propiedad punto recaudo
	 */
	public void setPuntoRecaudo(PuntoRecaudo apr_pr)
	{
		ipr_puntoRecaudo = apr_pr;
	}

	/**
	 * Retorna el valor de punto recaudo.
	 *
	 * @return el valor de punto recaudo
	 */
	public PuntoRecaudo getPuntoRecaudo()
	{
		return ipr_puntoRecaudo;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Canal Origen Servicio.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setPuntoRecaudo(new PuntoRecaudo());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarPuntoRecaudo");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Metodo que se encarga de reiniciar variables.
	 */
	public void clear()
	{
		setParametros(null);
		setInsertar(false);
		setPuntoRecaudo(null);
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_PUNTO_RECAUDO.
	 *
	 * @param apr_pr correspondiente al valor del tipo de objeto PuntoRecaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(PuntoRecaudo apr_pr)
	    throws B2BException
	{
		if(apr_pr != null)
		{
			apr_pr = ipr_parameterRemote.findPuntoRecaudoById(apr_pr.getIdPuntoRecaudo());

			if(apr_pr != null)
			{
				Collection<PuntoRecaudo> lcpr_pr;
				lcpr_pr = new ArrayList<PuntoRecaudo>();

				lcpr_pr.add(apr_pr);
				setPuntoRecaudo(apr_pr);

				setDatosAuditoria(lcpr_pr);
			}

			setInsertar(false);
		}
	}

	/**
	 * Departamento.
	 */
	public void departamento()
	{
		PuntoRecaudo lpr_puntoRecaudo;

		lpr_puntoRecaudo = getPuntoRecaudo();

		if(lpr_puntoRecaudo != null)
		{
			lpr_puntoRecaudo.setIdMunicipio(null);
			findMunicipios();
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_PUNTO_RECAUDO.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<PuntoRecaudo> findAllPuntoRecaudo()
	{
		Collection<PuntoRecaudo> lcpr_pr;

		lcpr_pr = null;

		try
		{
			lcpr_pr = ipr_parameterRemote.findAllPuntoRecaudo();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcpr_pr;
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
			PuntoRecaudo lpr_puntoRecaudo;

			lpr_puntoRecaudo = getPuntoRecaudo();

			if(lpr_puntoRecaudo != null)
			{
				String ls_idPais;

				ls_idPais = lpr_puntoRecaudo.getIdPais();

				if(StringUtils.isValidString(ls_idPais))
				{
					Departamento ld_parametros;

					ld_parametros = new Departamento();

					ld_parametros.setIdPais(ls_idPais);

					lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
				}
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
	public Collection<MedioRecaudo> findMediosRecaudo()
	{
		Collection<MedioRecaudo> lcmr_mediosRecaudo;

		lcmr_mediosRecaudo = new LinkedList<MedioRecaudo>();

		try
		{
			lcmr_mediosRecaudo = ipr_parameterRemote.findAllMedioRecaudo();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcmr_mediosRecaudo;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipios()
	{
		Collection<Municipio> lcm_municipios;
		PuntoRecaudo          lpr_puntoRecaudo;

		lcm_municipios       = null;
		lpr_puntoRecaudo     = getPuntoRecaudo();

		if(lpr_puntoRecaudo != null)
		{
			String ls_idPais;
			String ls_idDepartamento;

			ls_idPais             = lpr_puntoRecaudo.getIdPais();
			ls_idDepartamento     = lpr_puntoRecaudo.getIdDepartamento();

			if((StringUtils.isValidString(ls_idPais)) && (StringUtils.isValidString(ls_idDepartamento)))
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
		}

		return lcm_municipios;
	}

	/**
	 * Pais.
	 */
	public void pais()
	{
		PuntoRecaudo lpr_puntoRecaudo;

		lpr_puntoRecaudo = getPuntoRecaudo();

		if(lpr_puntoRecaudo != null)
		{
			lpr_puntoRecaudo.setIdDepartamento(null);
			findDepartamentos();
			findMunicipios();
		}
	}

	/**
	 * Método para salvar la inserción o actualización.
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
			PuntoRecaudo lpr_parametros;

			lpr_parametros = getPuntoRecaudo();

			if(lpr_parametros != null)
			{
				{
					String ls_nombrePuntoRecaudo;
					ls_nombrePuntoRecaudo     = lpr_parametros.getNombrePuntoRecaudo();

					lb_focus = validateStyles(
						    ":fPuntoRecaudoDetalle:nombrePuntoRecaudo", lfc_context, ls_nombrePuntoRecaudo, lb_focus
						);

					if(!StringUtils.isValidString(ls_nombrePuntoRecaudo))
						throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_PUNTO_RECAUDO);
				}

				{
					String ls_codigoPuntoRecaudo;
					ls_codigoPuntoRecaudo     = lpr_parametros.getCodigoPuntoRecaudo();

					lb_focus = validateStyles(
						    ":fPuntoRecaudoDetalle:codigoPuntoRecaudo", lfc_context, ls_codigoPuntoRecaudo, lb_focus
						);

					if(!StringUtils.isValidString(ls_codigoPuntoRecaudo))
						throw new B2BException(ErrorKeys.ERROR_SIN_CODIGO_PUNTO_RECAUDO);
				}

				{
					String ls_entidadRecaudadora;
					ls_entidadRecaudadora     = lpr_parametros.getIdEntidadRecaudadora();

					lb_focus = validateStyles(
						    ":fPuntoRecaudoDetalle:idEntidadRecaudadora", lfc_context, ls_entidadRecaudadora, lb_focus
						);

					if(!StringUtils.isValidString(ls_entidadRecaudadora))
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ENTIDAD_RECAUDADORA_VALIDA);
				}

				{
					String ls_medioRecaudadora;
					ls_medioRecaudadora     = lpr_parametros.getIdMedioRecaudo();

					lb_focus = validateStyles(
						    ":fPuntoRecaudoDetalle:idMedioRecaudo", lfc_context, ls_medioRecaudadora, lb_focus
						);

					if(!StringUtils.isValidString(ls_medioRecaudadora))
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_MEDIO_RECAUDO_VALIDO);
				}

				{
					String ls_pais;
					ls_pais     = lpr_parametros.getIdPais();

					lb_focus = validateStyles(":fPuntoRecaudoDetalle:idPais", lfc_context, ls_pais, lb_focus);

					if(!StringUtils.isValidString(ls_pais))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
				}

				{
					String ls_departamento;
					ls_departamento     = lpr_parametros.getIdDepartamento();

					lb_focus = validateStyles(
						    ":fPuntoRecaudoDetalle:idDepartamento", lfc_context, ls_departamento, lb_focus
						);

					if(!StringUtils.isValidString(ls_departamento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
				}

				{
					String ls_municipio;
					ls_municipio     = lpr_parametros.getIdMunicipio();

					lb_focus = validateStyles(":fPuntoRecaudoDetalle:idMunicipio", lfc_context, ls_municipio, lb_focus);

					if(!StringUtils.isValidString(ls_municipio))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
				}

				{
					String ls_activo;
					ls_activo     = lpr_parametros.getActivo();

					lb_focus = validateStyles(":fPuntoRecaudoDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarPuntoRecaudo(
				    lpr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setPuntoRecaudo(null);

				ls_result = NavegacionCommon.PUNTO_RECAUDO;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
