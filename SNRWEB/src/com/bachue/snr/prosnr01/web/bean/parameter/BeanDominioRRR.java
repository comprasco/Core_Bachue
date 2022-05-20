package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.col.DominioRrr;
import com.bachue.snr.prosnr01.model.sdb.col.TipoRrr;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanDominioRRR.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanDominioRRR")
@SessionScoped
public class BeanDominioRRR extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6824492946617569901L;

	/** Propiedad icdr datos auditoria. */
	private Collection<DominioRrr> icdr_datosAuditoria;

	/** Propiedad idr parametros. */
	private DominioRrr idr_parametros;

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
	 * @param datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<DominioRrr> datosAuditoria)
	{
		icdr_datosAuditoria = datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<DominioRrr> getDatosAuditoria()
	{
		return icdr_datosAuditoria;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param adr_dr asigna el valor a la propiedad parametros
	 */
	public void setParametros(DominioRrr adr_dr)
	{
		idr_parametros = adr_dr;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public DominioRrr getParametros()
	{
		if(idr_parametros == null)
			idr_parametros = new DominioRrr();

		return idr_parametros;
	}

	/**
	 * Cambiar estado.
	 */
	public void cambiarEstado()
	{
		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertar");

		setParametros(new DominioRrr());

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 *  Metodo para traer los registros de los DominiosRRR que coincidan con un id_Dominiorrr
	 * especifico.
	 */
	public void consultaDetallada()
	{
		try
		{
			DominioRrr ldr_parametros;
			ldr_parametros = getParametros();

			if(ldr_parametros != null)
			{
				String ls_idDominioRRR;

				ls_idDominioRRR = FacesUtils.getStringFacesParameter("idDominioRRR");

				if(StringUtils.isValidString(ls_idDominioRRR))
				{
					Collection<DominioRrr> lcdr_cdr;

					lcdr_cdr = new ArrayList<DominioRrr>();

					ldr_parametros.setIdDominioRrr(ls_idDominioRRR);

					setParametros(ipr_parameterRemote.findDominioRRRById(ldr_parametros));
					lcdr_cdr.add(getParametros());

					setDatosAuditoria(lcdr_cdr);
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
	 *  Metodo para traer todos los registros de la tabla SDB_COL_DOMINIO_RRR.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<DominioRrr> findAllDominioRRR()
	{
		Collection<DominioRrr> lcdr_data;
		lcdr_data = null;

		try
		{
			lcdr_data = ipr_parameterRemote.findAllDominioRRR();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcdr_data;
	}

	/**
	 * Metodo para traer todos los registros de la tabla SDB_COL_DOMINIO_RRR que coincidan con un ID_DOMINIORRR
	 * determinado.
	 *
	 * @param adr_dr es el objeto del cual se extrae el id para realizar la consulta en la base de datos
	 * @return devuelve el valor de TipoRrr
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TipoRrr findTipoRRRById(DominioRrr adr_dr)
	    throws B2BException
	{
		TipoRrr ltr_return;

		ltr_return = null;

		if(adr_dr != null)
		{
			try
			{
				String ls_idTipoRRR;

				ls_idTipoRRR = adr_dr.getIdTipoRrr();

				if(StringUtils.isValidString(ls_idTipoRRR))
				{
					ltr_return = new TipoRrr();

					ltr_return.setIdTipoRrr(ls_idTipoRRR);

					ltr_return = ipr_parameterRemote.findTipoRRRById(ltr_return);
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return ltr_return;
	}

	/**
	 * Metodo para salvar un DominioRRR ya sea una iserción o una modificación.
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
			DominioRrr ldr_parametros;

			ldr_parametros = getParametros();

			{
				String ls_idTipoRRR;
				ls_idTipoRRR     = ldr_parametros.getIdTipoRrr();

				lb_focus = validateStyles(
					    ":fDominoRRRDetalle:idSOMtipoRRRIdentidad", lfc_context, ls_idTipoRRR, lb_focus
					);

				if(!StringUtils.isValidString(ls_idTipoRRR))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_TIPO_RRR);
			}

			{
				String ls_descripcion;
				ls_descripcion     = ldr_parametros.getDescripcion();

				lb_focus = validateStyles(
					    ":fDominoRRRDetalle:idDescripcionTRRR", lfc_context, ls_descripcion, lb_focus
					);

				if(!StringUtils.isValidString(ls_descripcion))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
			}

			{
				String ls_activo;
				ls_activo     = ldr_parametros.getActivo();

				lb_focus = validateStyles(":fDominoRRRDetalle:idSOMActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
			}

			ipr_parameterRemote.salvarDominioRRR(
			    ldr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setParametros(null);

			ls_result = NavegacionCommon.DOMINIO_RRR;
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
