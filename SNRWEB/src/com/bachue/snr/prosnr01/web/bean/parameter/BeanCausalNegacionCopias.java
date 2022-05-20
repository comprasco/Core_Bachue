package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalNegacionCopias;
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
 * Clase que contiene todos las propiedades y acciones de BeanCausalNegacionCopias.
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanCausalNegacionCopias")
@SessionScoped
public class BeanCausalNegacionCopias extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7006465044408426404L;

	/** Propiedad icnc causal neg copias. */
	private Collection<CausalNegacionCopias> icnc_causalNegCopias;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad itd tipo documental. */
	private CausalNegacionCopias icnc_causalNegacionCopias;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param actd_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<CausalNegacionCopias> actd_datosAuditoria)
	{
		icnc_causalNegCopias = actd_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<CausalNegacionCopias> getDatosAuditoria()
	{
		return icnc_causalNegCopias;
	}

	/**
	 * @param ab_a asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_a)
	{
		ib_insertar = ab_a;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean getInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setCausalNegacionCopias(CausalNegacionCopias ac_c)
	{
		icnc_causalNegacionCopias = ac_c;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public CausalNegacionCopias getCausalNegacionCopias()
	{
		return icnc_causalNegacionCopias;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo Campo
	 * Criterio Busqueda
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCausalNegacionCopias((new CausalNegacionCopias()));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCausalNegacionCopias");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 *método de consulta detalada de Tipo Documental por medio de su indicativo
	 * @param atd_td indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(CausalNegacionCopias atd_td)
	    throws B2BException
	{
		if(atd_td != null)
		{
			String ls_idCausalNegacionCopias;

			ls_idCausalNegacionCopias = atd_td.getIdCausalNegacionCopias();

			if(StringUtils.isValidString(ls_idCausalNegacionCopias))
			{
				atd_td = ipr_parameterRemote.findAllCausalNegacionCopiasById(ls_idCausalNegacionCopias);

				if(atd_td != null)
				{
					Collection<CausalNegacionCopias> lctd_td;

					lctd_td = new ArrayList<CausalNegacionCopias>();

					lctd_td.add(atd_td);
					setCausalNegacionCopias(atd_td);

					setDatosAuditoria(lctd_td);
				}

				setInsertar(false);
			}
		}
	}

/**
 *Método de consulta para consultar todos los causal negacion copias
 * @return una colección con los datos solicitados
 */
	public Collection<CausalNegacionCopias> findAllCausalNegacionCopias()
	{
		Collection<CausalNegacionCopias> lccc_cc;
		lccc_cc = null;

		try
		{
			lccc_cc = ipr_parameterRemote.findAllCausalNegacionCopias();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccc_cc;
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
			CausalNegacionCopias ltd_CausalNegacionCopias;

			ltd_CausalNegacionCopias = getCausalNegacionCopias();

			if(ltd_CausalNegacionCopias != null)
			{
				{
					String ls_nombre;

					ls_nombre     = ltd_CausalNegacionCopias.getDescripcionCausalNegacionCopias();

					lb_focus = validateStyles(":fCausalNegacionCopiasDetalle:descripcion", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_activo;

					ls_activo     = ltd_CausalNegacionCopias.getActivo();

					lb_focus = validateStyles(":fCausalNegacionCopiasDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ipr_parameterRemote.salvarCausalNegacionCopias(
				    ltd_CausalNegacionCopias, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				
				getBeanReference().setCausalNegacionCopias(null);
				setCausalNegacionCopias(null);

				ls_result = NavegacionCommon.CAUSAL_NEGACION_COPIAS;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fCausalNegacionCopiasDetalle:globalMsg");
		}

		return ls_result;
	}
}
