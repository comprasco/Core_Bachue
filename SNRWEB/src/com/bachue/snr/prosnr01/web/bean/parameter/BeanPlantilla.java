package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import com.bachue.snr.prosnr16.model.sdb.pgn.Plantilla;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de
 * BeanPlantilla.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanPlantilla")
@SessionScoped
public class BeanPlantilla extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4844196696996748420L;

	/** Propiedad icp datos auditoria. */
	private Collection<Plantilla> icp_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ip parametros. */
	private Plantilla ip_parametros;

	/** Propiedad ip plantilla. */
	private Plantilla ip_plantilla;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param acp_cp asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<Plantilla> acp_cp)
	{
		icp_datosAuditoria = acp_cp;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Plantilla> getDatosAuditoria()
	{
		return icp_datosAuditoria;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param ap_p asigna el valor a la propiedad
	 */
	public void setParametros(Plantilla ap_p)
	{
		ip_parametros = ap_p;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Plantilla getParametros()
	{
		if(ip_parametros == null)
			ip_parametros = new Plantilla();

		return ip_parametros;
	}

	/**
	 * @param ap_p asigna el valor a la propiedad
	 */
	public void setPlantilla(Plantilla ap_p)
	{
		ip_plantilla = ap_p;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Plantilla getPlantilla()
	{
		return ip_plantilla;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Plantilla
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setPlantilla(new Plantilla());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarPlantilla");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_PLANTILLA
	 * @param ap_p
	 * @throws B2BException
	 */
	public void consultaDetallada(Plantilla ap_p)
	    throws B2BException
	{
		if(ap_p != null)
		{
			String ls_idPlantilla;

			ls_idPlantilla = FacesUtils.getStringFacesParameter("idPlantilla");

			if(StringUtils.isValidString(ls_idPlantilla))
			{
				Plantilla lp_plantilla;

				ap_p.setIdPlantilla(ls_idPlantilla);
				lp_plantilla = ipr_parameterRemote.findPlantillaById(ap_p);

				if(lp_plantilla != null)
				{
					Collection<Plantilla> lcp_cp;

					lcp_cp = new ArrayList<Plantilla>();

					lcp_cp.add(lp_plantilla);
					setPlantilla(lp_plantilla);

					setDatosAuditoria(lcp_cp);
				}

				setInsertar(false);
			}
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_PLANTILLA
	 * @return Collection Plantilla
	 */
	public Collection<Plantilla> findPlantilla()
	{
		Collection<Plantilla> lcp_plantilla;

		lcp_plantilla = null;

		try
		{
			lcp_plantilla = ipr_parameterRemote.findAllPlantilla();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcp_plantilla;
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		boolean      lb_focus;
		FacesContext lfc_context;
		String       ls_result;

		ls_result       = null;
		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			Plantilla lp_parametros;

			lp_parametros = getPlantilla();

			if(lp_parametros != null)
			{
				{
					String ls_cuerpo;
					ls_cuerpo     = lp_parametros.getCuerpo();

					lb_focus = validateStyles(":fPlantillaDetalle:cuerpo", lfc_context, ls_cuerpo, lb_focus);

					if(!StringUtils.isValidString(ls_cuerpo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_CUERPO);
				}

				ipr_parameterRemote.salvarPlantilla(
				    lp_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				getBeanReference().setPlantillasActivo(null);

				setParametros(null);

				addMessage(MessagesKeys.PROCESO_COMPLETADO);
				PrimeFaces.current().ajax().update("fPlantillaDetalle:globalMsg");

				ls_result = NavegacionCommon.PLANTILLA;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fPlantillaDetalle:globalMsg");
		}

		return ls_result;
	}
}
