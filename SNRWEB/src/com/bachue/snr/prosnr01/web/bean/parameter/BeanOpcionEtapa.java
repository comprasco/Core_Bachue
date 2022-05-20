package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.OpcionEtapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;

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
 * Clase que contiene todos las propiedades y acciones de BeanOpcionEtapa.
 *
 * @author ssanchez
 */
@ManagedBean(name = "beanOpcionEtapa")
@SessionScoped
public class BeanOpcionEtapa extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3750574563241576506L;

	/** Propiedad icoe datos auditoria. */
	private Collection<OpcionEtapa> icoe_datosAuditoria;

	/** Propiedad icoe opcion etapa. */
	private Collection<OpcionEtapa> icoe_opcionEtapa;

	/** Propiedad ioe opcion etapa. */
	private OpcionEtapa ioe_opcionEtapa;

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
	 * @param acoe_coe asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<OpcionEtapa> acoe_coe)
	{
		icoe_datosAuditoria = acoe_coe;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<OpcionEtapa> getDatosAuditoria()
	{
		return icoe_datosAuditoria;
	}

	/**
	 * @param acoe_coe asigna el valor a la propiedad
	 */
	public void setOpcionEtapaPantalla(Collection<OpcionEtapa> acoe_coe)
	{
		icoe_opcionEtapa = acoe_coe;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<OpcionEtapa> getOpcionEtapaPantalla()
	{
		return icoe_opcionEtapa;
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
	 * @param aoe_oe asigna el valor a la propiedad
	 */
	public void setOpcionEtapa(OpcionEtapa aoe_oe)
	{
		ioe_opcionEtapa = aoe_oe;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public OpcionEtapa getOpcionEtapa()
	{
		return ioe_opcionEtapa;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo Campo
	 * Criterio Busqueda
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setOpcionEtapa((new OpcionEtapa()));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarOpcionEtapa");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 *    Método de consulta para encontrar todos los registros de la tabla SDB_AUT_OPCION_ETAPA
	 * @return Collection de OpcionEtapa
	 */
	public Collection<OpcionEtapa> findAllOpcionEtapa()
	{
		Collection<OpcionEtapa> lcoe_oe;
		lcoe_oe = null;

		try
		{
			lcoe_oe = ipr_parameterRemote.findAllOpcionEtapa();

			if(CollectionUtils.isValidCollection(lcoe_oe))
				setOpcionEtapaPantalla(lcoe_oe);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcoe_oe;
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_AUT_OPCION
	 * @return
	 */
	public Collection<Opcion> findAllOpcion()
	{
		Collection<Opcion> lco_o;
		lco_o = null;

		try
		{
			lco_o = ipr_parameterRemote.findAllOpcion(false);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lco_o;
	}

	/**
	 * Metodo para traer de la base de datos todos los registros existentes de la tabla SDB_PGN_ETAPA
	 * para pintarlos en la capa de presentacion.
	 *
	 * @return Colección de etapas resultante de la consulta
	 */
	public Collection<Etapa> findAllEtapas()
	{
		Collection<Etapa> lccr_Etapas;
		lccr_Etapas = new ArrayList<Etapa>();

		try
		{
			lccr_Etapas = ipr_parameterRemote.findAllEtapas(getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccr_Etapas;
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 */
	public void salvar()
	{
		boolean      lb_focus;
		FacesContext lfc_context;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			OpcionEtapa loe_opcionEtapa;

			loe_opcionEtapa = getOpcionEtapa();

			if(loe_opcionEtapa != null)
			{
				{
					long ll_idEtapa;

					ll_idEtapa     = loe_opcionEtapa.getIdEtapa();

					lb_focus = validateStyles(":fOpcionEtapaDetalle:idEtapa", lfc_context, NumericUtils.getLongWrapper(ll_idEtapa), lb_focus);

					if(ll_idEtapa <=0)
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ETAPA);
				}

				{
					String ls_idOpcion;

					ls_idOpcion     = loe_opcionEtapa.getIdOpcion();

					lb_focus = validateStyles(":fOpcionEtapaDetalle:idOpcion", lfc_context, ls_idOpcion, lb_focus);

					if(!StringUtils.isValidString(ls_idOpcion))
						throw new B2BException(ErrorKeys.ERROR_SELECCIONE_OPCION);
				}

				ipr_parameterRemote.salvarOpcionEtapa(
				    loe_opcionEtapa, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				addMessage(MessagesKeys.PROCESO_COMPLETADO);
				PrimeFaces.current().ajax().update("fOpcionEtapaDetalle:globalMsg");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fOpcionEtapaDetalle:globalMsg");
		}
	}
}
