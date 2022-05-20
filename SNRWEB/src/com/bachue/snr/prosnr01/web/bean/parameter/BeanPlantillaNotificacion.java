package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.PlantillaNotificacion;

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
 * Clase que contiene todos las propiedades y funcionalidad de
 * BeanPlantilla.
 *
 * @author Juan Sebastian Gómez
 */
@ManagedBean(name = "beanPlantillaNotificacion")
@SessionScoped
public class BeanPlantillaNotificacion extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4844196696996748420L;

	/** Propiedad icp datos auditoria. */
	private Collection<PlantillaNotificacion> icpn_datosAuditoria;

	/** Propiedad lcp_plantillaComunicacion */
	private Collection<PlantillaNotificacion> icpn_plantillasNotificacion;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ip parametros. */
	private PlantillaNotificacion ipn_parametros;

	/** Propiedad ip plantilla. */
	private PlantillaNotificacion ipn_plantillaNotificacion;

	/** Propiedad ipr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param acpn_datosAuditoria Dato de tipo <code>Collection<PlantillaNotificacion</code> que se asigna a los datos auditoria
	 */
	public void setDatosAuditoria(Collection<PlantillaNotificacion> acpn_datosAuditoria)
	{
		icpn_datosAuditoria = acpn_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de los datos de auditoria
	 */
	public Collection<PlantillaNotificacion> getDatosAuditoria()
	{
		return icpn_datosAuditoria;
	}

	/**
	 * @param ab_b asigna el valor a insertar
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * @return devuelve el valor de insertar
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param ap_p asigna el valor a la propiedad
	 */
	public void setParametros(PlantillaNotificacion ap_p)
	{
		ipn_parametros = ap_p;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public PlantillaNotificacion getParametros()
	{
		if(ipn_parametros == null)
			ipn_parametros = new PlantillaNotificacion();

		return ipn_parametros;
	}

	/**
	 * @param ap_p asigna el valor a la plantilla notificación
	 */
	public void setPlantillaNotificacion(PlantillaNotificacion ap_p)
	{
		ipn_plantillaNotificacion = ap_p;
	}

	/**
	 * @return devuelve el valor de la plantilla notificación
	 */
	public PlantillaNotificacion getPlantillaNotificacion()
	{
		return ipn_plantillaNotificacion;
	}

	/**
	 * Metodo encargado de asignar el valor a la propiedad plantillas comunicación
	 *
	 * @param icp_cpc asigna el valor a la propiedad plantillas comunicación
	 */
	public void setPlantillasNotificacion(Collection<PlantillaNotificacion> icn_cpn)
	{
		icpn_plantillasNotificacion = icn_cpn;
	}

	/**
	 * Metodo encargado de devolver las plantillas comunicación
	 *
	 * @return devuelve el valor de las planitllas comunicación
	 */
	public Collection<PlantillaNotificacion> getPlantillasNotificacion()
	{
		if(icpn_plantillasNotificacion == null)
			findPlantillaNotificacion();

		return icpn_plantillasNotificacion;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar una nueva
	 * PlantillaNotificacion
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setPlantillaNotificacion(new PlantillaNotificacion());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarPlantillaNotificacion");

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
	 * Método para consultar una plantilla notificación específica.
	 *
	 * @param apn_plantilla correspondiente al valor del tipo de objeto <code>PlantillaNotificacion</code>
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String consultaDetallada(PlantillaNotificacion apn_plantilla)
	    throws B2BException
	{
		String ls_return;
		ls_return = NavegacionCommon.PLANTILLA_NOTIFICACION_DETALLE;

		try
		{
			if(apn_plantilla != null)
			{
				PlantillaNotificacion lpn_datos;
				lpn_datos = ipr_parameterRemote.findPlantillaNotificacionById(apn_plantilla);

				if(lpn_datos != null)
				{
					Collection<PlantillaNotificacion> lcpn_cpn;
					lcpn_cpn = new ArrayList<PlantillaNotificacion>();

					lcpn_cpn.add(lpn_datos);
					setPlantillaNotificacion(lpn_datos);
					setDatosAuditoria(lcpn_cpn);
				}

				setInsertar(false);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			ls_return = null;
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection del resultado de la consulta de los motivos diferentes por id proceso y id etapa
	 *
	 * @return Colección de tipo <code>MotivoTramite</code> que contiene los resultados de la busqueda.
	 *
	 */
	public Collection<MotivoTramite> findDistinctByIdProcesoAndIdEtapa()
	{
		Collection<MotivoTramite> lcidt_datos;
		lcidt_datos = null;

		try
		{
			PlantillaNotificacion lpn_plantillaNotificacion;
			lpn_plantillaNotificacion = getPlantillaNotificacion();

			if(lpn_plantillaNotificacion != null)
			{
				String ls_idProceso;
				long   ll_idEtapa;

				ls_idProceso     = lpn_plantillaNotificacion.getIdProceso();
				ll_idEtapa       = lpn_plantillaNotificacion.getIdEtapaAnterior();

				if(StringUtils.isValidString(ls_idProceso) && (ll_idEtapa > 0))
					lcidt_datos = irr_referenceRemote.findDistinctByIdProcesoAndIdEtapa(
						    ls_idProceso, ll_idEtapa, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 *  Método para encontrar todos los registros de plantilla notificación
	 * @return Collection de tipo <code>PlantillaNotificacion</code>
	 */
	public Collection<PlantillaNotificacion> findPlantillaNotificacion()
	{
		try
		{
			icpn_plantillasNotificacion = ipr_parameterRemote.findAllPlantillaNotificacion(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return icpn_plantillasNotificacion;
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			PlantillaNotificacion lpn_parametros;

			lpn_parametros = getPlantillaNotificacion();

			if(lpn_parametros != null)
			{
				FacesContext lfc_context;

				lfc_context = FacesContext.getCurrentInstance();

				{
					String ls_idProceso;

					ls_idProceso = lpn_parametros.getIdProceso();

					validateStyles("fPlantillaNotificacionDetalle:idProceso", lfc_context, ls_idProceso, true);

					if(!StringUtils.isValidString(ls_idProceso))
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_PROCESO);

					actualizarComponente("fPlantillaNotificacionDetalle:idProceso");
				}

				{
					long ll_idEtapaAnterior;

					ll_idEtapaAnterior = lpn_parametros.getIdEtapaAnterior();

					validateStyles(
					    "fPlantillaNotificacionDetalle:idEtapaAnterior", lfc_context,
					    (ll_idEtapaAnterior > 0) ? NumericUtils.getLongWrapper(ll_idEtapaAnterior) : null, true
					);

					if(ll_idEtapaAnterior <= 0)
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_ETAPA_ACTUAL);

					actualizarComponente("fPlantillaNotificacionDetalle:idEtapaAnterior");
				}

				{
					long ll_idMotivo;

					ll_idMotivo = lpn_parametros.getIdMotivo();

					validateStyles(
					    "fPlantillaNotificacionDetalle:idMotivo", lfc_context,
					    (ll_idMotivo > 0) ? NumericUtils.getLongWrapper(ll_idMotivo) : null, true
					);

					if(ll_idMotivo <= 0)
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_MOTIVO);

					actualizarComponente("fPlantillaNotificacionDetalle:idMotivo");
				}

				{
					String ls_idPlantilla;

					ls_idPlantilla = lpn_parametros.getIdPlantilla();

					validateStyles("fPlantillaNotificacionDetalle:idPlantilla", lfc_context, ls_idPlantilla, true);

					if(!StringUtils.isValidString(ls_idPlantilla))
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_PLANTILLA);

					actualizarComponente("fPlantillaNotificacionDetalle:idPlantilla");
				}

				{
					String ls_clasificacion;

					ls_clasificacion = lpn_parametros.getClasificacion();

					validateStyles("fPlantillaNotificacionDetalle:clasificacion", lfc_context, ls_clasificacion, true);

					if(!StringUtils.isValidString(ls_clasificacion))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CLASIFICACION);

					actualizarComponente("fPlantillaNotificacionDetalle:clasificacion");
				}

				ipr_parameterRemote.salvarPlantillaNotificacion(
				    lpn_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setPlantillaNotificacion(null);

				addMessage(MessagesKeys.PROCESO_COMPLETADO);
				PrimeFaces.current().ajax().update("fPlantillaNotificacionDetalle:globalMsg");

				ls_result = NavegacionCommon.PLANTILLA_NOTIFICACION;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fPlantillaNotificacionDetalle:globalMsg");
		}

		return ls_result;
	}
}
