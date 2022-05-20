package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanCalidadSolicitante.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanCalidadSolicitante")
@SessionScoped
public class BeanCalidadSolicitante extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7996739055218569694L;

	/** Propiedad iccs parametros. */
	private CalidadSolicitante iccs_parametros;

	/** Propiedad ics calidad solicitante. */
	private CalidadSolicitante ics_calidadSolicitante;

	/** Propiedad iccs datos auditoria. */
	private Collection<CalidadSolicitante> iccs_datosAuditoria;

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
	 * @param acs_cs asigna el valor a la propiedad
	 */
	public void setCalidadSolicitante(CalidadSolicitante acs_cs)
	{
		ics_calidadSolicitante = acs_cs;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public CalidadSolicitante getCalidadSolicitante()
	{
		return ics_calidadSolicitante;
	}

	/**
	 * @param datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<CalidadSolicitante> datosAuditoria)
	{
		iccs_datosAuditoria = datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<CalidadSolicitante> getDatosAuditoria()
	{
		return iccs_datosAuditoria;
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
	 * @param parametros asigna el valor a la propiedad
	 */
	public void setParametros(CalidadSolicitante parametros)
	{
		iccs_parametros = parametros;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public CalidadSolicitante getParametros()
	{
		if(iccs_parametros == null)
			iccs_parametros = new CalidadSolicitante();

		return iccs_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar una nueva
	 * Calidad Solicictante
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCalidadSolicitante(new CalidadSolicitante());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCalidadSolicitante");

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
	 * Método para consultar en la base de datos la tabla SDB_ACC_CALIDAD_SOLICITANTE
	 * @param acs_cs
	 * @throws B2BException
	 */
	public void consultaDetallada(CalidadSolicitante acs_cs)
	    throws B2BException
	{
		if(acs_cs != null)
		{
			CalidadSolicitante lccs_datos;
			lccs_datos = ipr_parameterRemote.findCalidadSolicitanteById(acs_cs);

			if(lccs_datos != null)
			{
				Collection<CalidadSolicitante> lcccs_cccs;
				lcccs_cccs = new ArrayList<CalidadSolicitante>();

				lcccs_cccs.add(lccs_datos);
				setCalidadSolicitante(lccs_datos);

				setDatosAuditoria(lcccs_cccs);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_ACC_CALIDAD_SOLICITANTE
	 * @return
	 */
	public Collection<CalidadSolicitante> findCalidadSolicitante()
	{
		Collection<CalidadSolicitante> lccs_constantes;
		lccs_constantes = null;

		try
		{
			lccs_constantes = ipr_parameterRemote.findCalidadSolicitante();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccs_constantes;
	}

	/**
	 * Método para salvar la inserción o actualización
	 * @return
	 */
	public void salvar()
	{
		boolean      lb_focus;
		FacesContext lfc_context;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			CalidadSolicitante lc_parametros;

			lc_parametros = getCalidadSolicitante();

			{
				String ls_nombre;
				ls_nombre     = lc_parametros.getNombre();

				lb_focus = validateStyles(":fCalidadSolicitanteDetalle:nombre", lfc_context, ls_nombre, lb_focus);

				if(!StringUtils.isValidString(ls_nombre))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
			}

			{
				String ls_activo;
				ls_activo     = lc_parametros.getActivo();

				lb_focus = validateStyles(":fCalidadSolicitanteDetalle:idActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
			}

			ipr_parameterRemote.salvarCalidadSolicitante(
			    lc_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			{
				BeanReference lbr_beanReference;

				lbr_beanReference = getBeanReference();

				lbr_beanReference.setInCalidadSolicitanteIndices(null);
				lbr_beanReference.setCalidadSolicitanteIndices(null);
				lbr_beanReference.setCalidadSolicitante(null);
			}

			setParametros(null);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fCalidadSolicitanteDetalle:globalMsg");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fCalidadSolicitanteDetalle:globalMsg");
		}
	}
}
