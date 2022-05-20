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

import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.PlantillaComunicacion;

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
 * Clase que contiene todos las propiedades y funcionalidad de
 * BeanPlantilla.
 *
 * @author Juan Sebastian Gómez
 */
@ManagedBean(name = "beanPlantillaComunicacion")
@SessionScoped
public class BeanPlantillaComunicacion extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4844196696996748420L;
	/** Propiedad lcp_plantillaComunicacion */
	Collection<PlantillaComunicacion> icpc_plantillasComunicacion;

	/** Propiedad icp datos auditoria. */
	private Collection<PlantillaComunicacion> icpn_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ip parametros. */
	private PlantillaComunicacion ipn_parametros;

	/** Propiedad ip plantilla. */
	private PlantillaComunicacion ipn_plantillaComunicacion;

	/** Propiedad ipr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/**
	 * Metodo encargado de devolver las plantillas comunicación
	 *
	 * @return devuelve el valor de las planitllas comunicación
	 */
	public Collection<PlantillaComunicacion> getPlantillasComunicacion()
	{
		if(icpc_plantillasComunicacion == null)
			findPlantillaComunicacion();

		return icpc_plantillasComunicacion;
	}

	/**
	 * Metodo encargado de asignar el valor a la propiedad plantillas comunicación
	 *
	 * @param icp_cpc asigna el valor a la propiedad plantillas comunicación
	 */
	public void setPlantillasComunicacion(Collection<PlantillaComunicacion> icp_cpc)
	{
		icpc_plantillasComunicacion = icp_cpc;
	}

	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param acpn_datosAuditoria Dato de tipo <code>Collection<PlantillaComunicacion</code> que se asigna a los datos auditoria
	 */
	public void setDatosAuditoria(Collection<PlantillaComunicacion> acpn_datosAuditoria)
	{
		icpn_datosAuditoria = acpn_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de los datos de auditoria
	 */
	public Collection<PlantillaComunicacion> getDatosAuditoria()
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
	public void setParametros(PlantillaComunicacion ap_p)
	{
		ipn_parametros = ap_p;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public PlantillaComunicacion getParametros()
	{
		if(ipn_parametros == null)
			ipn_parametros = new PlantillaComunicacion();

		return ipn_parametros;
	}

	/**
	 * @param ap_p asigna el valor a la plantilla calificación
	 */
	public void setPlantillaComunicacion(PlantillaComunicacion ap_p)
	{
		ipn_plantillaComunicacion = ap_p;
	}

	/**
	 * @return devuelve el valor de la plantilla calificación
	 */
	public PlantillaComunicacion getPlantillaComunicacion()
	{
		return ipn_plantillaComunicacion;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar una nueva
	 * PlantillaComunicacion
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setPlantillaComunicacion(new PlantillaComunicacion());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarPlantillaComunicacion");

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
	 * Método para consultar una plantilla comunicación específica.
	 *
	 * @param apn_plantilla correspondiente al valor del tipo de objeto <code>PlantillaNotificacion</code>
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String consultaDetallada(PlantillaComunicacion apn_plantilla)
	    throws B2BException
	{
		String ls_return;
		ls_return = NavegacionCommon.PLANTILLA_COMUNICACION_DETALLE;

		try
		{
			if(apn_plantilla != null)
			{
				PlantillaComunicacion lpn_datos;
				lpn_datos = ipr_parameterRemote.findPlantillaComunicacionById(apn_plantilla);

				if(lpn_datos != null)
				{
					Collection<PlantillaComunicacion> lcpn_cpn;
					lcpn_cpn = new ArrayList<PlantillaComunicacion>();

					lcpn_cpn.add(lpn_datos);
					setPlantillaComunicacion(lpn_datos);
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
	 * Retorna el valor del objeto de Collection del resultado de la consulta de los motivos por id proceso, id subproceso y id etapa
	 *
	 * @return Colección de tipo <code>MotivoTramite</code> que contiene los resultados de la busqueda.
	 *
	 */
	public Collection<MotivoTramite> findMotivoByIdProcesoIdSubProcesoAndIdEtapa()
	{
		Collection<MotivoTramite> lcidt_datos;
		lcidt_datos = null;

		try
		{
			PlantillaComunicacion lpn_plantillaComunicacion;
			lpn_plantillaComunicacion = getPlantillaComunicacion();

			if(lpn_plantillaComunicacion != null)
			{
				String ls_idProceso;
				String ls_idSubProceso;
				long   ll_idEtapa;

				ls_idProceso        = lpn_plantillaComunicacion.getIdProceso();
				ls_idSubProceso     = lpn_plantillaComunicacion.getIdSubProceso();
				ll_idEtapa          = lpn_plantillaComunicacion.getIdEtapaAnterior();

				if(
				    StringUtils.isValidString(ls_idProceso) && StringUtils.isValidString(ls_idSubProceso)
					    && (ll_idEtapa > 0)
				)
					lcidt_datos = irr_referenceRemote.findMotivoByIdProcesoIdSubProcesoAndIdEtapa(
						    ls_idProceso, ls_idSubProceso, ll_idEtapa, getUserId(), getLocalIpAddress(),
						    getRemoteIpAddress()
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
	 *  Método para encontrar todos los registros de plantilla comunicación
	 * @return Collection de tipo <code>PlantillaComunicacion</code>
	 */
	public void findPlantillaComunicacion()
	{
		try
		{
			icpc_plantillasComunicacion = ipr_parameterRemote.findAllPlantillaComunicacion(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor del objeto de Collection del resultado de la consulta de los subprocesos por id proceso
	 *
	 * @return Colección de tipo <code>SubProceso</code> que contiene los resultados de la busqueeda.
	 *
	 */
	public Collection<Subproceso> findSubProcesoByIdProceso()
	{
		Collection<Subproceso> lcidt_datos;
		lcidt_datos = null;

		try
		{
			PlantillaComunicacion lpn_plantillaComunicacion;
			lpn_plantillaComunicacion = getPlantillaComunicacion();

			if(lpn_plantillaComunicacion != null)
			{
				String     ls_idProceso;
				Subproceso ls_subproceso;

				ls_idProceso      = lpn_plantillaComunicacion.getIdProceso();
				ls_subproceso     = new Subproceso();
				ls_subproceso.setIdProceso(ls_idProceso);

				if(StringUtils.isValidString(ls_idProceso))
					lcidt_datos = irr_referenceRemote.findSubprocesosActivosByProceso(
						    ls_subproceso, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
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
	 * Método para salvar la inserción o actualización
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_componente;
		String ls_result;

		ls_result         = null;
		ls_componente     = ":fPlantillaComunicacionDetalle:";

		try
		{
			PlantillaComunicacion lpc_parametros;

			lpc_parametros = getPlantillaComunicacion();

			if(lpc_parametros != null)
			{
				FacesContext lfc_context;

				lfc_context = FacesContext.getCurrentInstance();

				{
					String ls_idProceso;

					ls_idProceso = lpc_parametros.getIdProceso();

					validateStyles(ls_componente + "idProceso", lfc_context, ls_idProceso, true);

					if(!StringUtils.isValidString(ls_idProceso))
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_PROCESO);

					actualizarComponente(ls_componente + "idProceso");
				}

				{
					long ll_idEtapaActual;

					ll_idEtapaActual = lpc_parametros.getIdEtapaActual();

					validateStyles(
					    ls_componente + "idEtapaActual", lfc_context,
					    (ll_idEtapaActual > 0) ? NumericUtils.getLongWrapper(ll_idEtapaActual) : null, true
					);

					if(ll_idEtapaActual <= 0)
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_ETAPA_ACTUAL);

					actualizarComponente(ls_componente + "idEtapaActual");
				}

				{
					String ls_idPlantilla;

					ls_idPlantilla = lpc_parametros.getIdPlantilla();

					validateStyles(ls_componente + "idPlantilla", lfc_context, ls_idPlantilla, true);

					if(!StringUtils.isValidString(ls_idPlantilla))
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_PLANTILLA);

					actualizarComponente(ls_componente + "idPlantilla");
				}

				{
					String ls_idAutorizacion;

					ls_idAutorizacion = lpc_parametros.getIdAutorizacionComunicacion();

					validateStyles(ls_componente + "idAutorizacionComunicacion", lfc_context, ls_idAutorizacion, true);

					if(!StringUtils.isValidString(ls_idAutorizacion))
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_ID_AUTORIZACION_COMUNICACION);

					actualizarComponente(ls_componente + "idAutorizacionComunicacion");
				}

				{
					String ls_activo;

					ls_activo = lpc_parametros.getActivo();

					validateStyles(ls_componente + "idActivo", lfc_context, ls_activo, true);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarPlantillaComunicacion(
				    lpc_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setPlantillasComunicacion(null);

				addMessage(MessagesKeys.PROCESO_COMPLETADO);

				ls_result = NavegacionCommon.PLANTILLA_COMUNICACION;

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
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(ls_componente + "globalMsg");
		}

		return ls_result;
	}
}
