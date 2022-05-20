package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.CriteriosDeBusqueda;
import com.bachue.snr.prosnr01.model.sdb.pgn.DetalleProcesoConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.ProcesoConsulta;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

/**
 * Clase que contiene todos las propiedades y funcionalidad de
 * BeanDetalleProcesoConsulta.
 *
 */
@ManagedBean(name = "beanDetalleProcesoConsulta")
@SessionScoped
public class BeanDetalleProcesoConsulta extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long                  serialVersionUID            = -3976448919842779191L;
	
	/** Propiedad icpc datos auditoria. */
	private Collection<DetalleProcesoConsulta> icpc_datosAuditoria;
	
	/** Propiedad icdpc parámetros*/
	private DetalleProcesoConsulta             icdpc_parametros;
	
	/** Propiedad idpc detalle proceso consulta*/
	private DetalleProcesoConsulta             idpc_detalleProcesoConsulta;
	
	/** Propiedad ipr parameter remote*/
	@EJB
	private ParameterRemote                    ipr_parameterRemote;
	
	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote                    irr_referenceRemote;
	
	/** Propiedad ib insertar*/
	private boolean                            ib_insertar;

	/**
	 * Retorna el valor de datos auditoria.
	 * 
	 * @return el valor de datos auditoria.
	 */
	public Collection<DetalleProcesoConsulta> getDatosAuditoria()
	{
		return icpc_datosAuditoria;
	}

	/**
	 * Modifica el valor de datos auditoria.
	 *  
	 * @param datosAuditoria el valor a la propiedad datos auditoria.
	 */
	public void setDatosAuditoria(Collection<DetalleProcesoConsulta> datosAuditoria)
	{
		icpc_datosAuditoria                                                = datosAuditoria;
	}

	/**
	 * Retorna el valor de parámetros.
	 * 
	 * @return el valor de parámetros.
	 */
	public DetalleProcesoConsulta getParametros()
	{
		if(icdpc_parametros == null)
			icdpc_parametros = new DetalleProcesoConsulta();

		return icdpc_parametros;
	}

	/**
	 * Modifica el valor de parámetros.
	 *  
	 * @param parametros el valor a la propiedad parámetros.
	 */
	public void setParametros(DetalleProcesoConsulta parametros)
	{
		icdpc_parametros = parametros;
	}

	/**
	 * Retorna el valor de detalle proceso consulta.
	 * 
	 * @return el valor de detalle proceso consulta.
	 */
	public DetalleProcesoConsulta getDetalleProcesoConsulta()
	{
		return idpc_detalleProcesoConsulta;
	}

	/**
	 * Modifica el valor de detalle proceso consulta.
	 * 
	 * @param adpc_dpc el valor a la propiedad detalle proceso consulta.
	 */
	public void setDetalleProcesoConsulta(DetalleProcesoConsulta adpc_dpc)
	{
		idpc_detalleProcesoConsulta = adpc_dpc;
	}

	/**
	 * Retorna el valor de insertar.
	 * 
	 * @return el valor de insertar.
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * Modifica el valor de insertar.
	 * 
	 * @param ab_b el valor a la propiedad insertar.
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar una nueva
	 * Proceso Consulta
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setDetalleProcesoConsulta(new DetalleProcesoConsulta());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarDetalleProcesoConsulta");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_DETALLE_PROCESO_CONSULTA.
	 *
	 * @return el valor de collection
	 */
	public Collection<DetalleProcesoConsulta> findDetalleProcesoConsulta()
	{
		Collection<DetalleProcesoConsulta> lcdpc_constantes;
		lcdpc_constantes = null;

		try
		{
			lcdpc_constantes = ipr_parameterRemote.findAllDetalleProcesoConsulta();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcdpc_constantes;
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_DETALLE_PROCESO_CONSULTA.
	 *
	 * @param adpc_dpc correspondiente al valor de adpc dpc
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultaDetallada(DetalleProcesoConsulta adpc_dpc)
	    throws B2BException
	{
		if(adpc_dpc != null)
		{
			DetalleProcesoConsulta lcpc_datos;
			lcpc_datos = ipr_parameterRemote.findDetalleProcesoConsultaById(adpc_dpc);

			if(lcpc_datos != null)
			{
				Collection<DetalleProcesoConsulta> lccdpc_ccdpc;
				lccdpc_ccdpc = new ArrayList<DetalleProcesoConsulta>();

				lccdpc_ccdpc.add(lcpc_datos);
				setDetalleProcesoConsulta(lcpc_datos);

				setDatosAuditoria(lccdpc_ccdpc);
			}

			setInsertar(false);
		}
	}

	/**
	 * Metodo para traer los procesos de la base de datos
	 *
	 * @return Colección de proceso consulta resultante de la consulta
	 */
	public Collection<ProcesoConsulta> cargarProcesoConsulta()
	{
		Collection<ProcesoConsulta> lp_procesoConsulta;
		lp_procesoConsulta = new LinkedList<ProcesoConsulta>();

		try
		{
			lp_procesoConsulta = irr_referenceRemote.findAllProcesoConsultaActivos(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lp_procesoConsulta;
	}

	/**
	 * Metodo para traer los procesos de la base de datos
	 *
	 * @return Colección detipo criterio busqueda resultante de la consulta
	 */
	public Collection<CriteriosDeBusqueda> cargarTipoCriterioBusqueda()
	{
		Collection<CriteriosDeBusqueda> lp_tipoCriterioBusqueda;
		lp_tipoCriterioBusqueda = new LinkedList<CriteriosDeBusqueda>();

		try
		{
			lp_tipoCriterioBusqueda = irr_referenceRemote.findAllTipoCriterioBusquedaActivos(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lp_tipoCriterioBusqueda;
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
			DetalleProcesoConsulta lpc_parametros;

			lpc_parametros = getDetalleProcesoConsulta();

			{
				String ls_idProcesoConsulta;
				ls_idProcesoConsulta     = lpc_parametros.getIdProcesoConsulta();

				lb_focus = validateStyles(
					    ":fDetalleProcesoConsultaDetalle:idProcesoConsulta", lfc_context, ls_idProcesoConsulta, lb_focus
					);

				if(!StringUtils.isValidString(ls_idProcesoConsulta))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PROCESO_CONSULTA);
			}

			{
				String ls_idTipoCriterioBusqueda;
				ls_idTipoCriterioBusqueda     = lpc_parametros.getIdTipoCriterioBusqueda();

				lb_focus = validateStyles(
					    ":fDetalleProcesoConsultaDetalle:idTipoCriterioBusqueda", lfc_context, ls_idTipoCriterioBusqueda,
					    lb_focus
					);

				if(!StringUtils.isValidString(ls_idTipoCriterioBusqueda))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_CRITERIO_BUSQUEDA);
			}

			{
				String ls_activo;
				ls_activo     = lpc_parametros.getActivo();

				lb_focus = validateStyles(":fDetalleProcesoConsultaDetalle:idActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
			}

			ipr_parameterRemote.salvarDetalleProcesoConsulta(
			    lpc_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setParametros(null);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fDetalleProcesoConsultaDetalle:globalMsg");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fDetalleProcesoConsultaDetalle:globalMsg");
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
