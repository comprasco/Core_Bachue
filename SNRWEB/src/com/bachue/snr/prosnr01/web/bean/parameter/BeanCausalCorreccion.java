package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.CausalCorreccion;

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
 * Clase para el manejo de la capa web para Causal Corrección.
 *
 * @author Sebastian Tafur
 */
@ManagedBean(name = "beanCausalCorreccion")
@SessionScoped
public class BeanCausalCorreccion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1823775468937832112L;

	/** Propiedad causal correccion. */
	private CausalCorreccion causalCorreccion;

	/** Propiedad icc parametros. */
	private CausalCorreccion icc_parametros;

	/** Propiedad icma datos auditoria. */
	private Collection<CausalCorreccion> icma_datosAuditoria;

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
	 * Modifica el valor de causal correccion.
	 *
	 * @param acc_cc asigna el valor a la propiedad causal correccion
	 */
	public void setCausalCorreccion(CausalCorreccion acc_cc)
	{
		causalCorreccion = acc_cc;
	}

	/**
	 * Retorna el valor de causal correccion.
	 *
	 * @return el valor de causal correccion
	 */
	public CausalCorreccion getCausalCorreccion()
	{
		return causalCorreccion;
	}

	/**
	 * Modifica el valor de datos auditoria.
	 *
	 * @param acma_cma asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<CausalCorreccion> acma_cma)
	{
		icma_datosAuditoria = acma_cma;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<CausalCorreccion> getDatosAuditoria()
	{
		return icma_datosAuditoria;
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
	 * @param acc_cc asigna el valor a la propiedad parametros
	 */
	public void setParametros(CausalCorreccion acc_cc)
	{
		icc_parametros = acc_cc;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public CausalCorreccion getParametros()
	{
		return icc_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Causal Corrección.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCausalCorreccion(new CausalCorreccion());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCausalCorreccion");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_CAUSAL_CORRECCION.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto CausalCorreccion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(CausalCorreccion acad_cad)
	    throws B2BException
	{
		if(acad_cad != null)
		{
			CausalCorreccion lcad_datos;
			lcad_datos = ipr_parameterRemote.findCausalCorreccionById(acad_cad);

			if(lcad_datos != null)
			{
				Collection<CausalCorreccion> lccad_ccad;
				lccad_ccad = new ArrayList<CausalCorreccion>();

				lccad_ccad.add(lcad_datos);
				setCausalCorreccion(lcad_datos);

				setDatosAuditoria(lccad_ccad);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<CausalCorreccion> findAllCausalCorreccion()
	{
		Collection<CausalCorreccion> lcr_constantes;
		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllCausalCorreccion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_constantes;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION
	 * que coincida con un id específico.
	 */
	public void findCausalCorreccionById()
	{
		try
		{
			CausalCorreccion ld_parametros;
			ld_parametros = getCausalCorreccion();

			if(ld_parametros != null)
			{
				ld_parametros.setIdCausalCorreccion(ld_parametros.getIdCausalCorreccion());
				setParametros(ipr_parameterRemote.findCausalCorreccionById(ld_parametros));
				setInsertar(false);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para salvar la inserción o actualización.
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
			CausalCorreccion lma_parametros;

			lma_parametros = getCausalCorreccion();

			{
				String ls_idConsulta;
				ls_idConsulta     = getCausalCorreccion().getNombre();

				lb_focus = validateStyles(":fCausalCorreccionDetalle:idNombre", lfc_context, ls_idConsulta, lb_focus);

				if(!StringUtils.isValidString(ls_idConsulta))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
			}

			{
				String ls_descripcion;
				ls_descripcion     = lma_parametros.getDescripcion();

				lb_focus = validateStyles(
					    ":fCausalCorreccionDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus
					);

				if(!StringUtils.isValidString(ls_descripcion))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
			}

			{
				String ls_grupoCausal;
				ls_grupoCausal     = lma_parametros.getGrupoCausal();

				lb_focus = validateStyles(
					    ":fCausalCorreccionDetalle:idGrupoCausal", lfc_context, ls_grupoCausal, lb_focus
					);

				if(!StringUtils.isValidString(ls_grupoCausal))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_GRUPO_CAUSAL);
			}

			{
				String ls_activo;
				ls_activo     = lma_parametros.getActivo();

				lb_focus = validateStyles(":fCausalCorreccionDetalle:idActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
			}

			ipr_parameterRemote.salvarCausalCorreccion(
			    lma_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setParametros(null);

			setCausalCorreccion(null);

			ls_result = NavegacionCommon.CAUSAL_CORRECCION;
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
