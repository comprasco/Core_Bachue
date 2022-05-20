package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion;
import com.bachue.snr.prosnr01.model.sdb.png.TipoAnotacion;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

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
 * paramétrica de tipos anotación.
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanTiposAnotacion")
@SessionScoped
public class BeanTiposAnotacion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1164103610068707749L;

	/** Propiedad iccr data tipos anotacion. */
	private Collection<TipoAnotacion> iccr_dataTiposAnotacion;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ita tipo anotacion. */
	private TipoAnotacion ita_tipoAnotacion;

	/** Propiedad ib insert. */
	private boolean ib_insert;

	/** Propiedad ib render insert update. */
	private boolean ib_renderInsertUpdate;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de data tipos anotacion.
	 *
	 * @param acta_cta asigna el valor a la propiedad data tipos anotacion
	 */
	public void setDataTiposAnotacion(Collection<TipoAnotacion> acta_cta)
	{
		iccr_dataTiposAnotacion = acta_cta;
	}

	/**
	 * Retorna el valor de data tipos anotacion.
	 *
	 * @return el valor de data tipos anotacion
	 */
	public Collection<TipoAnotacion> getDataTiposAnotacion()
	{
		if(iccr_dataTiposAnotacion == null)
			iccr_dataTiposAnotacion = new LinkedList<TipoAnotacion>();

		return iccr_dataTiposAnotacion;
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
	 * Modifica el valor de render insert update.
	 *
	 * @param ab_b asigna el valor a la propiedad render insert update
	 */
	public void setRenderInsertUpdate(boolean ab_b)
	{
		ib_renderInsertUpdate = ab_b;
	}

	/**
	 * Valida la propiedad render insert update.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en render insert update
	 */
	public boolean isRenderInsertUpdate()
	{
		return ib_renderInsertUpdate;
	}

	/**
	 * Modifica el valor de tipo anotacion.
	 *
	 * @param ata_ta asigna el valor a la propiedad tipo anotacion
	 */
	public void setTipoAnotacion(TipoAnotacion ata_ta)
	{
		ita_tipoAnotacion = ata_ta;
	}

	/**
	 * Retorna el valor de tipo anotacion.
	 *
	 * @return el valor de tipo anotacion
	 */
	public TipoAnotacion getTipoAnotacion()
	{
		return ita_tipoAnotacion;
	}

	/**
	 * Determina dependiendo de un flag booleano si se va a insertar un registro, o a modificar
	 * uno ya existente en la tabla SDB_PNG_TIPO_ANOTACION.
	 *
	 * @param acr_tipoAnotacionSeleccionado correspondiente al valor del tipo de objeto TipoAnotacion
	 * @param ab_proceso indica si la presentación de la página debe ser de inserción o actualización
	 * @return Cadena de caracteres con el enlace a la página de detalle de tipos anotación
	 */
	public String botonInsertar(TipoAnotacion acr_tipoAnotacionSeleccionado, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			acr_tipoAnotacionSeleccionado = new TipoAnotacion();

			setTipoAnotacion(acr_tipoAnotacionSeleccionado);
			setInsert(true);
		}
		else
		{
			setTipoAnotacion(acr_tipoAnotacionSeleccionado);
			setInsert(false);
		}

		setRenderInsertUpdate(true);

		return ls_return;
	}

	/**
	 * Consulta en la base de datos todos los registros existentes de tipo anotación.
	 *
	 * @return Colección de tipo anotación resultante de la consulta
	 */
	public Collection<TipoAnotacion> cargarTiposAnotacion()
	{
		Collection<TipoAnotacion> lcta_tiposAnotacion;
		lcta_tiposAnotacion = new LinkedList<TipoAnotacion>();

		try
		{
			lcta_tiposAnotacion = ipr_parameterRemote.findAllTiposAnotacion(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			setDataTiposAnotacion(lcta_tiposAnotacion);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcta_tiposAnotacion;
	}

	/**
	 * Consulta en la base de datos todos los registros existentes de líneas de producción.
	 *
	 * @return Colección de linea producción resultante de la consulta
	 */
	public Collection<LineaProduccion> findAllLineaProduccion()
	{
		Collection<LineaProduccion> llp_datos;
		llp_datos = null;

		try
		{
			llp_datos = ipr_parameterRemote.findAllLineasProduccion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return llp_datos;
	}

	/**
	 * Método que instancia algunos atributos de la clase.
	 */
	public void iniciar()
	{
		ita_tipoAnotacion = new TipoAnotacion();
		setInsert(false);
		setRenderInsertUpdate(false);
	}

	/**
	 * Metodo para el manejo de inserciones o actualizaciones de tipo anotación.
	 *
	 * @param ab_insertar indica si se desea insertar o actualizar dependiendo su valor, si su valor es true el inserta un nuevo registro,
	 * en cambio si su valor es false realiza una actualizacion en la base de datos.
	 * @return Cadena de caracteres con el enlace a la página inicial de tipos anotación.
	 */
	public String insertUpdateTipoAnotacion(boolean ab_insertar)
	{
		FacesContext  lfc_context;
		TipoAnotacion lta_tipoAnotacionInsertUpdate;
		String        ls_result;

		lta_tipoAnotacionInsertUpdate     = getTipoAnotacion();
		lfc_context                       = FacesContext.getCurrentInstance();
		ls_result                         = null;

		try
		{
			{
				String ls_idTipoAnotacion;
				ls_idTipoAnotacion = lta_tipoAnotacionInsertUpdate.getNombre();

				validateStyles(":fTiposAnotacion:idItNombre", lfc_context, ls_idTipoAnotacion, true);

				if(!StringUtils.isValidString(ls_idTipoAnotacion))
					throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_TIPO_ANOTACION);
			}

			{
				boolean lb_focus;
				lb_focus = true;

				String ls_activo;
				ls_activo     = lta_tipoAnotacionInsertUpdate.getActivo();

				lb_focus = validateStyles(":fTiposAnotacion:idItActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
			}

			ipr_parameterRemote.insertUpdateTiposAnotacion(
			    lta_tipoAnotacionInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			ls_result = NavegacionCommon.TIPOS_ANOTACION;

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fTiposAnotacion:gTiposAnotacion");

			setRenderInsertUpdate(false);

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

			return null;
		}

		return ls_result;
	}
}
