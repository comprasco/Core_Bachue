package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.InstanciaConsulta;

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
 * Clase que contiene todos las propiedades y acciones de BeanInstanciaConsulta.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanInstanciaConsulta")
@SessionScoped
public class BeanInstanciaConsulta extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3729349407647421260L;

	/** Propiedad id parametros. */
	private InstanciaConsulta id_parametros;

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
	public void setParametros(InstanciaConsulta ac_c)
	{
		id_parametros = ac_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public InstanciaConsulta getParametros()
	{
		if(id_parametros == null)
			id_parametros = new InstanciaConsulta();

		return id_parametros;
	}

/**
 * Metodo para  cambiar estado con el fin de saber si se desea insertar un proceso automatico.
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
	 * Metodo para  cambiar el id campo cuando se selecciona un id consulta.
	 */
	public void cambiarIdCampo()
	{
		CamposConsulta lcc_camposConsulta;
		lcc_camposConsulta = new CamposConsulta();

		lcc_camposConsulta.setIdConsulta(NumericUtils.getLongWrapper(getParametros().getIdConsulta()));

		//buscar el campo consulta por id y traer el id campo
	}

/**
 * Metodo para traer los registros de la instancia automática que coincida con un id_instancia
 * especifico.
 */
	public void consultaDetallada()
	{
		try
		{
			InstanciaConsulta ld_parametros;
			ld_parametros = getParametros();

			if(ld_parametros != null)
			{
				long ls_idInstancia;
				long ls_idCampo;

				ls_idInstancia     = FacesUtils.getLongFacesParameter("idInstancia");
				ls_idCampo         = FacesUtils.getLongFacesParameter("idCampo");

				if(
				    NumericUtils.isValidLong(NumericUtils.getLongWrapper(ls_idInstancia))
					    && NumericUtils.isValidLong(NumericUtils.getLongWrapper(ls_idCampo))
				)
				{
					ld_parametros.setIdInstancia(ls_idInstancia);
					ld_parametros.setIdCampo(ls_idCampo);
					setParametros(ipr_parameterRemote.findInstanciaConsultaById(ld_parametros));
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
	 * Metodo para traer todos los registros de la tabla SDB_PGN_INSTANCIA_CONSULTA.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<InstanciaConsulta> findAllInstanciaConsulta()
	{
		Collection<InstanciaConsulta> lcic_instancia;
		lcic_instancia = null;

		try
		{
			lcic_instancia = ipr_parameterRemote.findAllInstanciaConsulta(false);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcic_instancia;
	}

	/**
	 * Metodo para salvar una instancia consulta ya sea para modificar o insertar registros.
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
			InstanciaConsulta ld_parametros;

			ld_parametros = getParametros();

			if(ld_parametros != null)
			{
				{
					long ll_idInstancia;

					ll_idInstancia     = ld_parametros.getIdInstancia();
					lb_focus           = validateStyles(
						    ":fInstanciaConsultaDetalle:idInstancia", lfc_context,
						    NumericUtils.getLongWrapper(ll_idInstancia), lb_focus
						);

					if(!NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_idInstancia)) || (ll_idInstancia == 0))
					{
						lb_focus = validateStyles(":fInstanciaConsultaDetalle:idInstancia", lfc_context, "", lb_focus);
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_INSTANCIA_CONSULTA);
					}
				}

				{
					long ll_idCampo;

					ll_idCampo     = ld_parametros.getIdCampo();
					lb_focus       = validateStyles(
						    ":fInstanciaConsultaDetalle:idCampo", lfc_context, NumericUtils.getLongWrapper(ll_idCampo),
						    lb_focus
						);

					if(!NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_idCampo)) || (ll_idCampo == 0))
					{
						lb_focus = validateStyles(":fInstanciaConsultaDetalle:idCampo", lfc_context, "", lb_focus);
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ID_CAMPO);
					}
				}

				{
					long ll_idConsulta;

					ll_idConsulta     = ld_parametros.getIdConsulta();
					lb_focus          = validateStyles(
						    ":fInstanciaConsultaDetalle:idConsulta", lfc_context,
						    NumericUtils.getLongWrapper(ll_idConsulta), lb_focus
						);

					if(!NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_idConsulta)) || (ll_idConsulta == 0))
					{
						lb_focus = validateStyles(":fInstanciaConsultaDetalle:idConsulta", lfc_context, "", lb_focus);
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ID_CONSULTA);
					}
				}

				{
					String ls_valor;

					ls_valor     = ld_parametros.getValor();
					lb_focus     = validateStyles(
						    ":fInstanciaConsultaDetalle:idValor", lfc_context, ls_valor, lb_focus
						);

					if(!StringUtils.isValidString(ls_valor))
						throw new B2BException(ErrorKeys.ERROR_SIN_VALOR);
				}

				ipr_parameterRemote.salvarInstanciaConsulta(
				    ld_parametros, isInsertar(), getUserId(), getRemoteIpAddress(), getLocalIpAddress()
				);

				setParametros(null);

				ls_result = NavegacionCommon.INSTANCIA_CONSULTA;

				if(isInsertar())
				{
					addMessage(MessagesKeys.INSERCION_EXITOSA);
					PrimeFaces.current().ajax().update("FInstanciaConsulta");
					PrimeFaces.current().ajax().update("fInstanciaConsultaDetalle");
				}
				else
				{
					addMessage(MessagesKeys.MODIFICACION_EXITOSA);
					PrimeFaces.current().ajax().update("FInstanciaConsulta");
					PrimeFaces.current().ajax().update("fInstanciaConsultaDetalle");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fInstanciaConsultaDetalle");
		}

		return ls_result;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<CamposConsulta> findCamposConsulta()
	{
		Collection<CamposConsulta> lccc_resultado;

		try
		{
			lccc_resultado = irr_referenceRemote.findCamposConsulta(
				    true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lccc_resultado = null;
		}

		return lccc_resultado;
	}
}
