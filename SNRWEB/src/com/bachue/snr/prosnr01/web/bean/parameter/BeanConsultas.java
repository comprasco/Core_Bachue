package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.Consultas;

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
 * Clase para el manejo de la capa web para Consultas.
 *
 * @author Sebastian Tafur
 */
@ManagedBean(name = "beanConsultas")
@SessionScoped
public class BeanConsultas extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6573575237467799977L;

	/** Propiedad icma datos auditoria. */
	private Collection<Consultas> icma_datosAuditoria;

	/** Propiedad ima consulta. */
	private Consultas ima_consulta;

	/** Propiedad ima parametros. */
	private Consultas ima_parametros;

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
	 * Modifica el valor de datos auditoria.
	 *
	 * @param acma_cma asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<Consultas> acma_cma)
	{
		icma_datosAuditoria = acma_cma;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<Consultas> getDatosAuditoria()
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
	 * @param parametros asigna el valor a la propiedad parametros
	 */
	public void setParametros(Consultas parametros)
	{
		ima_parametros = parametros;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public Consultas getParametros()
	{
		if(ima_parametros == null)
			ima_parametros = new Consultas();

		return ima_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Causal Aprobación Devolución.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setconsulta(new Consultas());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarConsultas");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_CAUSAL_APROBACION_DEVOLUCION.
	 *
	 * @param acad_cad correspondiente al valor del tipo de objeto Consultas
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(Consultas acad_cad)
	    throws B2BException
	{
		if(acad_cad != null)
		{
			Consultas lcad_datos;
			Long      ll_s;

			ll_s           = NumericUtils.getLongWrapper(acad_cad.getIdConsulta());
			lcad_datos     = null;

			if(NumericUtils.isValidLong(ll_s))
				lcad_datos = ipr_parameterRemote.findConsultasById(acad_cad);

			if(lcad_datos != null)
			{
				Collection<Consultas> lccad_ccad;
				lccad_ccad = new ArrayList<Consultas>();

				lccad_ccad.add(lcad_datos);
				setconsulta(lcad_datos);

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
	public Collection<Consultas> findAllConsultas()
	{
		Collection<Consultas> lcr_constantes;
		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllConsultas();
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
	public void findConsultasById()
	{
		try
		{
			Consultas ld_parametros;

			ld_parametros = getconsulta();

			if(ld_parametros != null)
			{
				Long ll_dato;

				ll_dato = NumericUtils.getLongWrapper(ld_parametros.getIdConsulta());

				if(NumericUtils.isValidLong((ll_dato)))
				{
					ld_parametros.setIdConsulta(ld_parametros.getIdConsulta());
					setParametros(ipr_parameterRemote.findConsultasById(ld_parametros));
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
	 * Retorna el valor de consulta.
	 *
	 * @return el valor de consulta
	 */
	public Consultas getconsulta()
	{
		return ima_consulta;
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
			Consultas lma_parametros;

			lma_parametros = getconsulta();

			if(lma_parametros != null)
			{
				{
					String ls_nombreConsulta;
					ls_nombreConsulta     = lma_parametros.getNombreConsulta();

					lb_focus = validateStyles(
						    ":fConsultasDetalle:idNombreConsulta", lfc_context, ls_nombreConsulta, lb_focus
						);

					if(!StringUtils.isValidString(ls_nombreConsulta))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE_CONSULTA);
				}

				{
					String ls_nombreProcedimiento;
					ls_nombreProcedimiento     = lma_parametros.getNombreProcedimiento();

					lb_focus = validateStyles(
						    ":fConsultasDetalle:idNombreProcedimiento", lfc_context, ls_nombreProcedimiento, lb_focus
						);

					if(!StringUtils.isValidString(ls_nombreProcedimiento))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE_PROCEDIMIENTO);
				}

				{
					String ls_estado;
					ls_estado     = lma_parametros.getEstado();

					lb_focus = validateStyles(":fConsultasDetalle:idEstado", lfc_context, ls_estado, lb_focus);

					if(!StringUtils.isValidString(ls_estado))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO);
				}

				{
					String ls_nombreProcedimiento;
					ls_nombreProcedimiento     = lma_parametros.getDescripcionConsulta();

					lb_focus = validateStyles(
						    ":fConsultasDetalle:idDescripcionConsulta", lfc_context, ls_nombreProcedimiento, lb_focus
						);

					if(!StringUtils.isValidString(ls_nombreProcedimiento))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION_CONSULTA);
				}

				ipr_parameterRemote.salvarConsultas(
				    lma_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				
				getBeanReference().setCargarTipoConsulta(null);

				setParametros(null);
				setconsulta(null);

				ls_result = NavegacionCommon.CONSULTAS;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}

	/**
	 * Modifica el valor de consulta.
	 *
	 * @param ama_ma asigna el valor a la propiedad consulta
	 */
	public void setconsulta(Consultas ama_ma)
	{
		ima_consulta = ama_ma;
	}
}
