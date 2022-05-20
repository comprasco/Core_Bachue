package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoEstadoSolicitud;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase para el manejo de eventos e interacciones con la capa de presentación de la pantalla
 * paramétrica de tipos estado solicitud.
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanTiposEstadoSolicitud")
@SessionScoped
public class BeanTiposEstadoSolicitud extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanTiposEstadoSolicitud.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8456306397510650644L;

	/** Propiedad iccr data tipos estado solicitud. */
	private Collection<TipoEstadoSolicitud> iccr_dataTiposEstadoSolicitud;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ita tipo estado solicitud. */
	private TipoEstadoSolicitud ita_tipoEstadoSolicitud;

	/** Propiedad ib insert. */
	private boolean ib_insert;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de data tipos estado solicitud.
	 *
	 * @param acta_cta asigna el valor a la propiedad data tipos estado solicitud
	 */
	public void setDataTiposEstadoSolicitud(Collection<TipoEstadoSolicitud> acta_cta)
	{
		iccr_dataTiposEstadoSolicitud = acta_cta;
	}

	/**
	 * Retorna el valor de data tipos estado solicitud.
	 *
	 * @return el valor de data tipos estado solicitud
	 */
	public Collection<TipoEstadoSolicitud> getDataTiposEstadoSolicitud()
	{
		if(iccr_dataTiposEstadoSolicitud == null)
			iccr_dataTiposEstadoSolicitud = new LinkedList<TipoEstadoSolicitud>();

		return iccr_dataTiposEstadoSolicitud;
	}

	/**
	 * Modifica el valor de insert.
	 *
	 * @param insert asigna el valor a la propiedad insert
	 */
	public void setInsert(boolean insert)
	{
		this.ib_insert = insert;
	}

	/**
	 * Valida la propiedad insert.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en insert
	 */
	public boolean isInsert()
	{
		return ib_insert;
	}

	/**
	 * Modifica el valor de tipo estado solicitud.
	 *
	 * @param ata_ta asigna el valor a la propiedad tipo estado solicitud
	 */
	public void setTipoEstadoSolicitud(TipoEstadoSolicitud ata_ta)
	{
		ita_tipoEstadoSolicitud = ata_ta;
	}

	/**
	 * Retorna el valor de tipo estado solicitud.
	 *
	 * @return el valor de tipo estado solicitud
	 */
	public TipoEstadoSolicitud getTipoEstadoSolicitud()
	{
		return ita_tipoEstadoSolicitud;
	}

	/**
	 * Determina dependiendo de un flag booleano si se va a insertar un registro, o a modificar
	 * uno ya existente en la tabla SDB_ACC_TIPO_ESTADO_SOLICITUD.
	 *
	 */
	public void cambiarEstado()
	{
		setInsert(true);
		setTipoEstadoSolicitud((new TipoEstadoSolicitud()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoEstadoSolicitud");

		if(lb_parametro != null)
			setInsert(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_ACC_TIPO_ESTADO_SOLICITUD por medio de su indicativo
	 *
	 * @param aae_actividadEconomica indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(TipoEstadoSolicitud ates_tipoEstadoSolicitud)
	    throws B2BException
	{
		if(ates_tipoEstadoSolicitud != null)
		{
			TipoEstadoSolicitud ltes_tipoEstadoSolicitud;

			ltes_tipoEstadoSolicitud = ipr_parameterRemote.findTipoEstadoSolicitudById(
				    ates_tipoEstadoSolicitud, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ltes_tipoEstadoSolicitud != null)
			{
				Collection<TipoEstadoSolicitud> lctes_ctes;

				lctes_ctes = new ArrayList<TipoEstadoSolicitud>();

				lctes_ctes.add(ltes_tipoEstadoSolicitud);

				setTipoEstadoSolicitud(ltes_tipoEstadoSolicitud);
				setDataTiposEstadoSolicitud(lctes_ctes);
			}

			setInsert(false);
		}
	}

	/**
	 * Consulta en la base de datos todos los registros existentes de tipo estado solicitud.
	 *
	 * @return Colección de tipo estado solicitud resultante de la consulta
	 */
	public Collection<TipoEstadoSolicitud> cargarTiposEstadoSolicitud()
	{
		Collection<TipoEstadoSolicitud> lcta_tiposEstadoSolicitud;

		lcta_tiposEstadoSolicitud = new LinkedList<TipoEstadoSolicitud>();

		try
		{
			lcta_tiposEstadoSolicitud = ipr_parameterRemote.findAllTiposEstadoSolicitud(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return lcta_tiposEstadoSolicitud;
	}

	/**
	 * Metodo para el manejo de inserciones o actualizaciones de tipo estado solicitud.
	 *
	 * @param ab_insertar indica si se desea insertar o actualizar dependiendo su valor, si su valor es true el inserta un nuevo registro,
	 * en cambio si su valor es false realiza una actualizacion en la base de datos.
	 * @return Cadena de caracteres con el enlace a la página inicial de tipos estado solicitud.
	 */
	public String insertUpdateTipoEstadoSolicitud(boolean ab_insertar)
	{
		boolean             lb_focus;
		FacesContext        lfc_context;
		TipoEstadoSolicitud lta_tipoEstadoSolicitudInsertUpdate;
		String              ls_result;

		lta_tipoEstadoSolicitudInsertUpdate     = getTipoEstadoSolicitud();
		lfc_context                             = FacesContext.getCurrentInstance();
		ls_result                               = null;
		lb_focus                                = true;

		try
		{
			{
				String ls_idNombre;

				ls_idNombre = lta_tipoEstadoSolicitudInsertUpdate.getNombre();

				validateStyles(":fTiposEstadoSolicitud:idItNombre", lfc_context, ls_idNombre, lb_focus);

				if(!StringUtils.isValidString(ls_idNombre))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
			}

			{
				String ls_activo;

				ls_activo     = lta_tipoEstadoSolicitudInsertUpdate.getActivo();

				lb_focus = validateStyles(":fTiposEstadoSolicitud:idItActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
			}

			ipr_parameterRemote.insertUpdateTiposEstadoSolicitud(
			    lta_tipoEstadoSolicitudInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			ls_result = NavegacionCommon.TIPOS_ESTADO_SOLICITUD;

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fTiposEstadoSolicitud:gTiposEstadoSolicitud");

			setInsert(false);

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
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fTiposEstadoSolicitud:globalMsg");
		}

		return ls_result;
	}
}
