package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.FestivoNacional;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanFestivoNacional.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanFestivoNacional")
@SessionScoped
public class BeanFestivoNacional extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4260770067423826462L;

	/** Propiedad ifn parametros. */
	private FestivoNacional ifn_parametros;

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
	public void setParametros(FestivoNacional ac_c)
	{
		ifn_parametros = ac_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public FestivoNacional getParametros()
	{
		if(ifn_parametros == null)
			ifn_parametros = new FestivoNacional();

		return ifn_parametros;
	}

	/**
	 * Metodo para  cambiar estado con el fin de saber si se desea insertar un festivo nacional.
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
	 * Metodo para traer los registros del festivo nacional que coincida con un id_procesoautomaticos
	 * especifico.
	 */
	public void consultaDetallada()
	{
		try
		{
			FestivoNacional ld_parametros;
			ld_parametros = getParametros();

			if(ld_parametros != null)
			{
				String ls_idProcesoAutomatico;

				ls_idProcesoAutomatico = FacesUtils.getStringFacesParameter("idFestivoNacional");

				if(StringUtils.isValidString(ls_idProcesoAutomatico))
				{
					ld_parametros.setIdFestivoNacional(ls_idProcesoAutomatico);
					setParametros(ipr_parameterRemote.findFestivoNacionalById(ld_parametros));
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
	 * Metodo para traer todos los registros de la tabla SDB_PGN_FESTIVO_NACIONAL.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<FestivoNacional> findAllFestivoNacional()
	{
		Collection<FestivoNacional> lcfn_festivos;
		lcfn_festivos = null;

		try
		{
			lcfn_festivos = ipr_parameterRemote.findAllFestivoNacional(false);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcfn_festivos;
	}

	/**
	 * Metodo para salvar un festivo nacional ya sea para modificar o insertar registros.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvar()
	{
		String          ls_result;
		FestivoNacional ld_parametros;
		FacesContext    lfc_context;
		boolean         lb_focus;

		ls_result         = null;
		ld_parametros     = getParametros();
		lfc_context       = FacesContext.getCurrentInstance();
		lb_focus          = true;

		{
			Date ld_datoValidar;
			ld_datoValidar         = ld_parametros.getFecha();
			lb_focus               = validateStyles(
				    ":FFestivoNacionalDetalle:idFecha", lfc_context, ld_datoValidar, lb_focus
				);

			PrimeFaces.current().ajax().update("FFestivoNacionalDetalle");
		}

		try
		{
			ld_parametros.setIdUsuarioCreacion(getUserId());
			ld_parametros.setIdUsuarioModificacion(getUserId());

			ipr_parameterRemote.salvarFestivoNacional(
			    ld_parametros, isInsertar(), getUserId(), getRemoteIpAddress(), getLocalIpAddress()
			);

			setParametros(null);

			ls_result = NavegacionCommon.FESTIVO_NACIONAL;

			if(isInsertar())
			{
				addMessage(MessagesKeys.INSERCION_EXITOSA);
				PrimeFaces.current().ajax().update("FFestivoNacional");
				PrimeFaces.current().ajax().update("FFestivoNacionalDetalle");
			}
			else
			{
				addMessage(MessagesKeys.MODIFICACION_EXITOSA);
				PrimeFaces.current().ajax().update("FFestivoNacional");
				PrimeFaces.current().ajax().update("FFestivoNacionalDetalle");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
