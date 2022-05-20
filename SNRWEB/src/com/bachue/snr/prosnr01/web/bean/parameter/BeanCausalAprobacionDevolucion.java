package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.CausalAprobacionDevolucion;

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
 * Clase para el manejo de la capa web para Causal Aprobación Devolución.
 *
 * @author Sebastian Tafur
 */
@ManagedBean(name = "beanCausalAprobacionDevolucion")
@SessionScoped
public class BeanCausalAprobacionDevolucion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5486536522831184683L;

	/** Propiedad icad causal aprobacion devolucion. */
	private CausalAprobacionDevolucion icad_causalAprobacionDevolucion;

	/** Propiedad icad parametros. */
	private CausalAprobacionDevolucion icad_parametros;

	/** Propiedad iccad datos auditoria. */
	private Collection<CausalAprobacionDevolucion> iccad_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad id etapa devolucion. */
	private String idEtapaDevolucion;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Retorna el valor de causal aprobacion devolucion.
	 *
	 * @return el valor de causal aprobacion devolucion
	 */
	public CausalAprobacionDevolucion getCausalAprobacionDevolucion()
	{
		return icad_causalAprobacionDevolucion;
	}

	/**
	 * Modifica el valor de datos auditoria.
	 *
	 * @param datosAuditoria asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<CausalAprobacionDevolucion> datosAuditoria)
	{
		iccad_datosAuditoria = datosAuditoria;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<CausalAprobacionDevolucion> getDatosAuditoria()
	{
		return iccad_datosAuditoria;
	}

	/**
	 * Modifica el valor de id etapa devolucion.
	 *
	 * @param as_s asigna el valor a la propiedad id etapa devolucion
	 */
	public void setIdEtapaDevolucion(String as_s)
	{
		idEtapaDevolucion = as_s;
	}

	/**
	 * Retorna el valor de id etapa devolucion.
	 *
	 * @return el valor de id etapa devolucion
	 */
	public String getIdEtapaDevolucion()
	{
		return idEtapaDevolucion;
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
	 * @param ar_c asigna el valor a la propiedad parametros
	 */
	public void setParametros(CausalAprobacionDevolucion ar_c)
	{
		icad_parametros = ar_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public CausalAprobacionDevolucion getParametros()
	{
		if(icad_parametros == null)
			icad_parametros = new CausalAprobacionDevolucion();

		return icad_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Causal Aprobación Devolución.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setcausalAprobacion(new CausalAprobacionDevolucion());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCausalAprobacionDevolucion");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto CausalAprobacionDevolucion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(CausalAprobacionDevolucion acad_cad)
	    throws B2BException
	{
		if(acad_cad != null)
		{
			CausalAprobacionDevolucion lcad_datos;
			lcad_datos = ipr_parameterRemote.findCausalAprobacionDevolucionById(acad_cad);

			if(lcad_datos != null)
			{
				Collection<CausalAprobacionDevolucion> lccad_ccad;
				lccad_ccad = new ArrayList<CausalAprobacionDevolucion>();

				lccad_ccad.add(lcad_datos);
				setcausalAprobacion(lcad_datos);

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
	public Collection<CausalAprobacionDevolucion> findAllCausalAprobacionDevolucion()
	{
		Collection<CausalAprobacionDevolucion> lcr_constantes;
		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllCausalAprobacionDevolucion();
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
	public void findCausalAprobacionDevolucionById()
	{
		try
		{
			CausalAprobacionDevolucion ld_parametros;
			ld_parametros = getCausalAprobacionDevolucion();

			if(ld_parametros != null)
			{
				if(StringUtils.isValidString(ld_parametros.getCodigo()))
				{
					ld_parametros.setCodigo(ld_parametros.getCodigo());
					setParametros(ipr_parameterRemote.findCausalAprobacionDevolucionById(ld_parametros));
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
			CausalAprobacionDevolucion lr_parametros;

			lr_parametros = getCausalAprobacionDevolucion();

			{
				String ls_codigo;
				ls_codigo     = lr_parametros.getCodigo();

				lb_focus = validateStyles(
					    ":fCausalAprobacionDevolucionDetalle:idCodigo", lfc_context, ls_codigo, lb_focus
					);

				if(!StringUtils.isValidString(ls_codigo))
					throw new B2BException(ErrorKeys.ERROR_CODIGO);
			}

			{
				String ls_causalDevolucion;
				ls_causalDevolucion     = lr_parametros.getCausalDevolucion();

				lb_focus = validateStyles(
					    ":fCausalAprobacionDevolucionDetalle:idCausalDevolucion", lfc_context, ls_causalDevolucion,
					    lb_focus
					);

				if(!StringUtils.isValidString(ls_causalDevolucion))
					throw new B2BException(ErrorKeys.ERROR_CAUSAL_DEVOLUCION);
			}

			{
				String ls_accion;
				ls_accion     = lr_parametros.getAccion();

				lb_focus = validateStyles(
					    ":fCausalAprobacionDevolucionDetalle:idAccion", lfc_context, ls_accion, lb_focus
					);

				if(!StringUtils.isValidString(ls_accion))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACCION);
			}

			{
				String ls_estado;
				ls_estado     = lr_parametros.getEstado();

				lb_focus = validateStyles(
					    ":fCausalAprobacionDevolucionDetalle:idEstado", lfc_context, ls_estado, lb_focus
					);

				if(!StringUtils.isValidString(ls_estado))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO);
			}

			{
				String ls_idConsulta;
				ls_idConsulta     = StringUtils.getString(lr_parametros.getIdEtapaDevolucion());

				lb_focus = validateStyles(
					    ":fCausalAprobacionDevolucionDetalle:idEtapaDevolucion", lfc_context, ls_idConsulta, lb_focus
					);

				if(!StringUtils.isValidString(ls_idConsulta))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_ETAPA_DEVOLUCION);

				Long ll_idConsulta;
				ll_idConsulta     = NumericUtils.getLongWrapper(ls_idConsulta, -1);

				lb_focus = validateStyles(
					    ":fCausalAprobacionDevolucionDetalle:idEtapaDevolucion", lfc_context, ll_idConsulta, lb_focus
					);

				if(!NumericUtils.isValidLong(ll_idConsulta))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_ETAPA_DEVOLUCION);

				lr_parametros.setIdEtapaDevolucion(NumericUtils.getBigDecimal(NumericUtils.getLong(ll_idConsulta)));
			}

			ipr_parameterRemote.salvarCausalAprobacionDevolucion(
			    lr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setParametros(null);

			setcausalAprobacion(null);

			ls_result = NavegacionCommon.CAUSAL_APROBACION_DEVOLUCION;
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}

	/**
	 * Modifica el valor de causal aprobacion.
	 *
	 * @param atr_tr asigna el valor a la propiedad causal aprobacion
	 */
	public void setcausalAprobacion(CausalAprobacionDevolucion atr_tr)
	{
		icad_causalAprobacionDevolucion = atr_tr;
	}
}
