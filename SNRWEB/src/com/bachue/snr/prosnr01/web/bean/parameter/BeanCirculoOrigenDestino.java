package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.png.CirculoOrigenDestino;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades BeanCirculoOrigenDestino.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 12/08/2020
 */
@ManagedBean(name = "beanCirculoOrigenDestino")
@SessionScoped
public class BeanCirculoOrigenDestino extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3755446134953591389L;

	/** Propiedad icod circulo origen destino. */
	private CirculoOrigenDestino icod_circuloOrigenDestino;

	/** Propiedad icod parametros. */
	private CirculoOrigenDestino icod_parametros;

	/** Propiedad icod datos auditoria. */
	private Collection<CirculoOrigenDestino> icod_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de application
	 */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de DatosAuditoria.
	 *
	 * @param datosAuditoria de datos auditoria
	 */
	public void setDatosAuditoria(Collection<CirculoOrigenDestino> datosAuditoria)
	{
		icod_datosAuditoria = datosAuditoria;
	}

	/**
	 * Retorna Objeto o variable de valor datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<CirculoOrigenDestino> getDatosAuditoria()
	{
		return icod_datosAuditoria;
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
	 * @param acod_cod de parametros
	 */
	public void setParametros(CirculoOrigenDestino acod_cod)
	{
		icod_parametros = acod_cod;
	}

	/**
	 * Retorna Objeto o variable de valor parametros.
	 *
	 * @return el valor de parametros
	 */
	public CirculoOrigenDestino getParametros()
	{
		if(icod_parametros == null)
			icod_parametros = new CirculoOrigenDestino();

		return icod_parametros;
	}

	/**
	 * Modifica el valor de CirculoOrigenDestino.
	 *
	 * @param apc_pc de apc pc
	 */
	public void setCirculoOrigenDestino(CirculoOrigenDestino apc_pc)
	{
		icod_circuloOrigenDestino = apc_pc;
	}

	/**
	 * Retorna Objeto o variable de valor proceso consulta.
	 *
	 * @return el valor de proceso consulta
	 */
	public CirculoOrigenDestino getCirculoOrigenDestino()
	{
		return icod_circuloOrigenDestino;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar una nueva
	 * Proceso Consulta.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCirculoOrigenDestino(new CirculoOrigenDestino());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCirculoOrigenDestino");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para limpiar la pantalla.
	 */
	public void clear()
	{
		setParametros(null);
		setCirculoOrigenDestino(null);
		setDatosAuditoria(null);
	}

	/**
	 * Find all.
	 *
	 * @return el valor de collection
	 */
	public Collection<CirculoOrigenDestino> findAll()
	{
		Collection<CirculoOrigenDestino> lccod_circuloOrigenDestino;

		lccod_circuloOrigenDestino = null;

		try
		{
			lccod_circuloOrigenDestino = ipr_parameterRemote.findAllCirculoOrigenDestino();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccod_circuloOrigenDestino;
	}

	/**
	 * Consulta detallada.
	 *
	 * @param acod_cod de acod cod
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultaDetallada(CirculoOrigenDestino acod_cod)
	    throws B2BException
	{
		if(acod_cod != null)
		{
			CirculoOrigenDestino lcpc_datos;
			lcpc_datos = ipr_parameterRemote.findCirculoOrigenDestinoById(acod_cod);

			if(lcpc_datos != null)
			{
				Collection<CirculoOrigenDestino> lcccr_cccr;

				lcccr_cccr = new ArrayList<CirculoOrigenDestino>();

				lcccr_cccr.add(lcpc_datos);

				setCirculoOrigenDestino(lcpc_datos);
				setDatosAuditoria(lcccr_cccr);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método para salvar la inserción o actualización.
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String       ls_result;
		boolean      lb_focus;
		FacesContext lfc_context;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();
		ls_result       = null;

		try
		{
			CirculoOrigenDestino lpc_parametros;

			lpc_parametros = getCirculoOrigenDestino();

			{
				String ls_idCirculoOrigen;
				ls_idCirculoOrigen     = lpc_parametros.getIdCirculoOrigen();

				lb_focus = validateStyles(
					    ":fCirculoOrigenDestinoDetalle:idCirculoOrigen", lfc_context, ls_idCirculoOrigen, lb_focus
					);

				if(!StringUtils.isValidString(ls_idCirculoOrigen))
					throw new B2BException(ErrorKeys.ERROR_SELECCIONE_CIRCULO_ORIGEN);
			}

			{
				String ls_idCirculoDestino;
				ls_idCirculoDestino     = lpc_parametros.getIdCirculoDestino();

				lb_focus = validateStyles(
					    ":fCirculoOrigenDestinoDetalle:idCirculoDestino", lfc_context, ls_idCirculoDestino, lb_focus
					);

				if(!StringUtils.isValidString(ls_idCirculoDestino))
					throw new B2BException(ErrorKeys.ERROR_SELECCIONE_CIRCULO_DESTINO);
			}

			{
				String ls_activo;
				ls_activo     = lpc_parametros.getActivo();

				lb_focus = validateStyles(":fCirculoOrigenDestinoDetalle:idActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
			}

			ipr_parameterRemote.salvarCirculoOrigenDestino(
			    lpc_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setParametros(null);

			clear();

			ls_result = NavegacionCommon.CIRCULO_ORIGEN_DESTINO;

			addMessage(MessagesKeys.PROCESO_COMPLETADO);

			{
				ExternalContext lec_externalContext;

				lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

				if(lec_externalContext != null)
				{
					Flash lf_flash;

					lf_flash = lec_externalContext.getFlash();

					if(lf_flash != null)
						lf_flash.setKeepMessages(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fCirculoOrigenDestinoDetalle:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String returnPages()
	{
		String ls_return;

		ls_return = NavegacionCommon.CIRCULO_ORIGEN_DESTINO;

		clear();

		return ls_return;
	}
}
