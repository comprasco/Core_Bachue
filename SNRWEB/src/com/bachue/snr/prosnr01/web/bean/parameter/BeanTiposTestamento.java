package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoTestamento;

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
 * paramétrica de tipos testamento.
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanTiposTestamento")
@SessionScoped
public class BeanTiposTestamento extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7180095174739621738L;

	/** Propiedad iccr data tipos testamento. */
	private Collection<TipoTestamento> iccr_dataTiposTestamento;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ita tipo testamento. */
	private TipoTestamento ita_tipoTestamento;

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
	 * Modifica el valor de data tipos testamento.
	 *
	 * @param acta_cta asigna el valor a la propiedad data tipos testamento
	 */
	public void setDataTiposTestamento(Collection<TipoTestamento> acta_cta)
	{
		iccr_dataTiposTestamento = acta_cta;
	}

	/**
	 * Retorna el valor de data tipos testamento.
	 *
	 * @return el valor de data tipos testamento
	 */
	public Collection<TipoTestamento> getDataTiposTestamento()
	{
		if(iccr_dataTiposTestamento == null)
			iccr_dataTiposTestamento = new LinkedList<TipoTestamento>();

		return iccr_dataTiposTestamento;
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
	 * Modifica el valor de tipo testamento.
	 *
	 * @param ata_ta asigna el valor a la propiedad tipo testamento
	 */
	public void setTipoTestamento(TipoTestamento ata_ta)
	{
		ita_tipoTestamento = ata_ta;
	}

	/**
	 * Retorna el valor de tipo testamento.
	 *
	 * @return el valor de tipo testamento
	 */
	public TipoTestamento getTipoTestamento()
	{
		return ita_tipoTestamento;
	}

	/**
	 * Determina dependiendo de un flag booleano si se va a insertar un registro, o a modificar
	 * uno ya existente en la tabla SDB_PGN_TIPO_TESTAMENTO.
	 *
	 * @param acr_tipoTestamentoSeleccionado correspondiente al valor del tipo de objeto TipoTestamento
	 * @param ab_proceso indica si la presentación de la página debe ser de inserción o actualización
	 * @return Cadena de caracteres con el enlace a la página de detalle de tipos testamento
	 */
	public String botonInsertar(TipoTestamento acr_tipoTestamentoSeleccionado, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			acr_tipoTestamentoSeleccionado = new TipoTestamento();

			setTipoTestamento(acr_tipoTestamentoSeleccionado);
			setInsert(true);
		}
		else
		{
			setTipoTestamento(acr_tipoTestamentoSeleccionado);
			setInsert(false);
		}

		setRenderInsertUpdate(true);

		ls_return = NavegacionCommon.TIPO_TESTAMENTO_DETALLE;

		return ls_return;
	}

	/**
	 * Consulta en la base de datos todos los registros existentes de tipo testamento.
	 *
	 * @return Colección de tipo testamento resultante de la consulta
	 */
	public Collection<TipoTestamento> cargarTiposTestamento()
	{
		Collection<TipoTestamento> lcta_tiposTestamento;
		lcta_tiposTestamento = new LinkedList<TipoTestamento>();

		try
		{
			lcta_tiposTestamento = ipr_parameterRemote.findAllTiposTestamento(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			setDataTiposTestamento(lcta_tiposTestamento);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcta_tiposTestamento;
	}

	/**
	 * Método que instancia algunos objetos de la clase.
	 */
	public void iniciar()
	{
		ita_tipoTestamento = new TipoTestamento();
		setInsert(false);
		setRenderInsertUpdate(false);
	}

	/**
	 * Metodo para el manejo de inserciones o actualizaciones de tipo testamento.
	 *
	 * @param ab_insertar indica si se desea insertar o actualizar dependiendo su valor, si su valor es true el inserta un nuevo registro,
	 * en cambio si su valor es false realiza una actualizacion en la base de datos.
	 * @return Cadena de caracteres con el enlace a la página inicial de tipos testamento.
	 */
	public String insertUpdateTipoTestamento(boolean ab_insertar)
	{
		FacesContext lfc_context;
		String       ls_result;

		ls_result       = null;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			TipoTestamento ltt_tipoTestamentoInsertUpdate;

			ltt_tipoTestamentoInsertUpdate = getTipoTestamento();

			if(ltt_tipoTestamentoInsertUpdate != null)
			{
				{
					String ls_idTipoTestamento;
					ls_idTipoTestamento = ltt_tipoTestamentoInsertUpdate.getNombre();

					validateStyles(":fTiposTestamentoDetalle:idItNombre", lfc_context, ls_idTipoTestamento, true);

					if(!StringUtils.isValidString(ls_idTipoTestamento))
						throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_TIPO_TESTAMENTO);
				}

				{
					boolean lb_focus;
					lb_focus = true;

					String ls_activo;
					ls_activo     = ltt_tipoTestamentoInsertUpdate.getActivo();

					lb_focus = validateStyles(":fTiposTestamentoDetalle:idItActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ipr_parameterRemote.insertUpdateTiposTestamento(
				    ltt_tipoTestamentoInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				ls_result = NavegacionCommon.TIPOS_TESTAMENTO;

				addMessage(MessagesKeys.PROCESO_COMPLETADO);

				{
					ExternalContext lec_externalContext;

					lec_externalContext = lfc_context.getExternalContext();

					if(lec_externalContext != null)
					{
						Flash lf_flash;

						lf_flash = lec_externalContext.getFlash();

						if(lf_flash != null)
							lf_flash.setKeepMessages(true);
					}
				}

				setRenderInsertUpdate(false);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			PrimeFaces.current().ajax().update("fTiposTestamentoDetalle");

			return null;
		}

		return ls_result;
	}
}
