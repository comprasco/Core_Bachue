package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.CausalMayorValor;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para CausalMayorValor
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanCausalMayorValor")
@SessionScoped
public class BeanCausalMayorValor extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5244028336793827707L;

	/** Propiedad icmv causal mayor valor. */
	private CausalMayorValor icmv_causalMayorValor;

	/** Propiedad iccmv datos auditoria. */
	private Collection<CausalMayorValor> iccmv_datosAuditoria;

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
	 * @param acmv_causalMayorValor asigna el valor a la propiedad
	 */
	public void setCausalMayorValor(CausalMayorValor acmv_causalMayorValor)
	{
		icmv_causalMayorValor = acmv_causalMayorValor;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public CausalMayorValor getCausalMayorValor()
	{
		return icmv_causalMayorValor;
	}

	/**
	 * @param acmv_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<CausalMayorValor> acmv_datosAuditoria)
	{
		iccmv_datosAuditoria = acmv_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<CausalMayorValor> getDatosAuditoria()
	{
		return iccmv_datosAuditoria;
	}

	/**
	 * @param ab_insertar asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_insertar)
	{
		ib_insertar = ab_insertar;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo Campo
	 * Criterio Busqueda
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCausalMayorValor((new CausalMayorValor()));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCausalMayorValor");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 *
	 * @param atd_td
	 * @throws B2BException
	 */
	public void consultaDetallada(CausalMayorValor atd_td)
	    throws B2BException
	{
		if(atd_td != null)
		{
			long             ll_idCausalMayorValor;
			CausalMayorValor latd_td;
			ll_idCausalMayorValor     = atd_td.getIdCausalMayorValor();

			latd_td = ipr_parameterRemote.findAllCausalMayorValorById(ll_idCausalMayorValor);

			if(latd_td != null)
			{
				Collection<CausalMayorValor> lctd_td;

				lctd_td = new ArrayList<CausalMayorValor>();

				lctd_td.add(latd_td);
				setCausalMayorValor(latd_td);

				setDatosAuditoria(lctd_td);
			}

			setInsertar(false);
		}
	}

/**
 *
 * @return
 */
	public Collection<CausalMayorValor> findAllCausalMayorValor()
	{
		Collection<CausalMayorValor> lccmv_cmv;
		lccmv_cmv = null;

		try
		{
			lccmv_cmv = ipr_parameterRemote.findAllCausalMayorValor();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccmv_cmv;
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 * @return
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
			CausalMayorValor lcsmv_cmv;

			lcsmv_cmv = getCausalMayorValor();

			if(lcsmv_cmv != null)
			{
				{
					String ls_nombre;

					ls_nombre     = lcsmv_cmv.getNombre();

					lb_focus = validateStyles(":fCausalMayorValorDetalle:idNombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				ipr_parameterRemote.salvarCausalMayorValor(
				    lcsmv_cmv, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				setCausalMayorValor(null);

				ls_result = NavegacionCommon.CAUSAL_MAYOR_VALOR;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fCausalMayorValorDetalle:globalMsg");
		}

		return ls_result;
	}
}
