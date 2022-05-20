package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.CausalReimpresion;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades BeanCausalReimpresion.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 4/08/2020
 */
@ManagedBean(name = "beanCausalReimpresion")
@SessionScoped
public class BeanCausalReimpresion extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6544313546981458452L;

	/** Propiedad iccr parametros. */
	private CausalReimpresion iccr_parametros;

	/** Propiedad icr causal reimpresion. */
	private CausalReimpresion icr_causalReimpresion;

	/** Propiedad iccr datos auditoria. */
	private Collection<CausalReimpresion> iccr_datosAuditoria;

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
	 * Modifica el valor de CausalReimpresion.
	 *
	 * @param acr_cr de acr cr
	 */
	public void setCausalReimpresion(CausalReimpresion acr_cr)
	{
		icr_causalReimpresion = acr_cr;
	}

	/**
	 * Retorna Objeto o variable de valor causal reimpresion.
	 *
	 * @return el valor de causal reimpresion
	 */
	public CausalReimpresion getCausalReimpresion()
	{
		return icr_causalReimpresion;
	}

	/**
	 * Modifica el valor de DatosAuditoria.
	 *
	 * @param datosAuditoria de datos auditoria
	 */
	public void setDatosAuditoria(Collection<CausalReimpresion> datosAuditoria)
	{
		iccr_datosAuditoria = datosAuditoria;
	}

	/**
	 * Retorna Objeto o variable de valor datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<CausalReimpresion> getDatosAuditoria()
	{
		return iccr_datosAuditoria;
	}

	/**
	 * Modifica el valor de Insertar.
	 *
	 * @param ab_b de ab b
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * Valida la propiedad insertar.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en insertar
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * Modifica el valor de Parametros.
	 *
	 * @param parametros de parametros
	 */
	public void setParametros(CausalReimpresion parametros)
	{
		iccr_parametros = parametros;
	}

	/**
	 * Retorna Objeto o variable de valor parametros.
	 *
	 * @return el valor de parametros
	 */
	public CausalReimpresion getParametros()
	{
		if(iccr_parametros == null)
			iccr_parametros = new CausalReimpresion();

		return iccr_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar una nueva
	 * Causal Reimpresion.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCausalReimpresion(new CausalReimpresion());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCausalReimpresion");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para limpiar la pantalla.
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setCausalReimpresion(null);
		setInsertar(false);
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_CAUSAL_REIMPRESION.
	 *
	 * @param acr_cr de acr cr
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultaDetallada(CausalReimpresion acr_cr)
	    throws B2BException
	{
		if(acr_cr != null)
		{
			CausalReimpresion lccr_datos;
			lccr_datos = ipr_parameterRemote.findCausalReimpresionById(acr_cr);

			if(lccr_datos != null)
			{
				Collection<CausalReimpresion> lcccr_cccr;
				lcccr_cccr = new ArrayList<CausalReimpresion>();

				lcccr_cccr.add(lccr_datos);
				setCausalReimpresion(lccr_datos);

				setDatosAuditoria(lcccr_cccr);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_CAUSAL_REIMPRESION.
	 *
	 * @return el valor de collection
	 */
	public Collection<CausalReimpresion> findCausalReimpresion()
	{
		Collection<CausalReimpresion> lccr_constantes;
		lccr_constantes = null;

		try
		{
			lccr_constantes = ipr_parameterRemote.findAllCausalReimpresion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccr_constantes;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String returnPages()
	{
		String ls_return;

		ls_return = NavegacionCommon.CAUSAL_REIMPRESION;

		clear();

		return ls_return;
	}

	/**
	 * Método para salvar la inserción o actualización.
	 */
	public void salvar()
	{
		boolean      lb_focus;
		FacesContext lfc_context;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			CausalReimpresion lcr_parametros;

			lcr_parametros = getCausalReimpresion();

			{
				String ls_descripcion;
				ls_descripcion     = lcr_parametros.getDescripcion();

				lb_focus = validateStyles(
					    ":fCausalReimpresionDetalle:descripcion", lfc_context, ls_descripcion, lb_focus
					);

				if(!StringUtils.isValidString(ls_descripcion))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
			}

			{
				String ls_activo;
				ls_activo     = lcr_parametros.getActivo();

				lb_focus = validateStyles(":fCausalReimpresionDetalle:idActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
			}

			ipr_parameterRemote.salvarCausalReimpresion(
			    lcr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setParametros(null);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fCausalReimpresionDetalle:globalMsg");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fCausalReimpresionDetalle:globalMsg");
		}
	}
}
