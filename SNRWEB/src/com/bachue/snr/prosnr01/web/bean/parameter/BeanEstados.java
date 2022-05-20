package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import com.bachue.snr.prosnr16.model.sdb.pgn.Estados;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de
 * BeanEstados.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanEstados")
@SessionScoped
public class BeanEstados extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4822670099408497933L;

	/** Propiedad ice datos auditoria. */
	private Collection<Estados> ice_datosAuditoria;

	/** Propiedad ie estados. */
	private Estados ie_estados;

	/** Propiedad ie parametros. */
	private Estados ie_parametros;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Estados> getDatosAuditoria()
	{
		return ice_datosAuditoria;
	}

	/**
	 * @param ace_ce asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<Estados> ace_ce)
	{
		ice_datosAuditoria = ace_ce;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Estados getParametros()
	{
		if(ie_parametros == null)
			ie_parametros = new Estados();

		return ie_parametros;
	}

	/**
	 * @param ae_e asigna el valor a la propiedad
	 */
	public void setParametros(Estados ae_e)
	{
		ie_parametros = ae_e;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Estados getEstados()
	{
		return ie_estados;
	}

	/**
	 * @param ae_e asigna el valor a la propiedad
	 */
	public void setEstados(Estados ae_e)
	{
		ie_estados = ae_e;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Estados
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setEstados(new Estados());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarEstados");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_ESTADOS
	 * @return Collection Estados
	 */
	public Collection<Estados> findEstados()
	{
		Collection<Estados> lce_estados;

		lce_estados = null;

		try
		{
			lce_estados = ipr_parameterRemote.findAllEstados();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lce_estados;
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_ESTADOS
	 * @param ae_e
	 * @throws B2BException
	 */
	public void consultaDetallada(Estados ae_e)
	    throws B2BException
	{
		if(ae_e != null)
		{
			Estados le_estados;
			le_estados = ipr_parameterRemote.findEstadosById(ae_e);

			if(le_estados != null)
			{
				Collection<Estados> lce_ce;

				lce_ce = new ArrayList<Estados>();

				lce_ce.add(le_estados);
				setEstados(le_estados);

				setDatosAuditoria(lce_ce);
			}

			setInsertar(false);
		}
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
			Estados le_parametros;

			le_parametros = getEstados();

			if(le_parametros != null)
			{
				{
					String ls_nombre;
					ls_nombre     = le_parametros.getNombre();

					lb_focus = validateStyles(":fEstadosDetalle:nombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;
					ls_activo     = le_parametros.getActivo();

					lb_focus = validateStyles(":fEstadosDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarEstados(
				    le_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);

				addMessage(MessagesKeys.PROCESO_COMPLETADO);
				PrimeFaces.current().ajax().update("fEstadosDetalle:globalMsg");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fEstadosDetalle:globalMsg");
		}
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
	}

	@Override
	public String getApplication()
	{
		return null;
	}
}
