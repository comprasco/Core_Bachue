package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;

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
 * paramétrica de tipos eje.
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanTiposEje")
@SessionScoped
public class BeanTiposEje extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -815598454645925898L;

	/** Propiedad icte data tipos eje. */
	private Collection<TipoEje> icte_dataTiposEje;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ite tipo eje. */
	private TipoEje ite_tipoEje;

	/** Propiedad ib insert. */
	private boolean ib_insert;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de data tipos eje.
	 *
	 * @param acta_cta asigna el valor a la propiedad data tipos eje
	 */
	public void setDataTiposEje(Collection<TipoEje> acta_cta)
	{
		icte_dataTiposEje = acta_cta;
	}

	/**
	 * Retorna el valor de data tipos eje.
	 *
	 * @return el valor de data tipos eje
	 */
	public Collection<TipoEje> getDataTiposEje()
	{
		if(icte_dataTiposEje == null)
			icte_dataTiposEje = new LinkedList<TipoEje>();

		return icte_dataTiposEje;
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
	 * Modifica el valor de tipo eje.
	 *
	 * @param ata_ta asigna el valor a la propiedad tipo eje
	 */
	public void setTipoEje(TipoEje ata_ta)
	{
		ite_tipoEje = ata_ta;
	}

	/**
	 * Retorna el valor de tipo eje.
	 *
	 * @return el valor de tipo eje
	 */
	public TipoEje getTipoEje()
	{
		return ite_tipoEje;
	}

	/**
	 * Determina dependiendo de un flag booleano si se va a insertar un registro, o a modificar
	 * uno ya existente en la tabla SDB_PNG_TIPO_EJE.
	 *
	 * @param acr_tipoEjeSeleccionado correspondiente al valor del tipo de objeto TipoEje
	 * @param ab_proceso indica si la presentación de la página debe ser de inserción o actualización
	 * @return Cadena de caracteres con el enlace a la página de detalle de tipos eje
	 */
	public String botonInsertar(TipoEje acr_tipoEjeSeleccionado, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			acr_tipoEjeSeleccionado = new TipoEje();

			setTipoEje(acr_tipoEjeSeleccionado);
			setInsert(true);
		}
		else
		{
			setTipoEje(acr_tipoEjeSeleccionado);
			setInsert(false);
		}

		ls_return = NavegacionCommon.TIPOS_EJE_DETALLE;

		return ls_return;
	}

	/**
	 * Consulta en la base de datos todos los registros existentes de tipo eje.
	 *
	 * @return Colección de tipo eje resultante de la consulta
	 */
	public Collection<TipoEje> cargarTiposEje()
	{
		Collection<TipoEje> lcta_tiposEje;
		lcta_tiposEje = new LinkedList<TipoEje>();

		try
		{
			lcta_tiposEje = ipr_parameterRemote.findAllTiposEje(getUserId(), getLocalIpAddress(), getRemoteIpAddress());

			setDataTiposEje(lcta_tiposEje);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcta_tiposEje;
	}

	/**
	 * Método que instancia algunos objetos de la clase.
	 */
	public void iniciar()
	{
		ite_tipoEje = new TipoEje();
		setInsert(false);
	}

	/**
	 * Metodo para el manejo de inserciones o actualizaciones de tipo eje.
	 *
	 * @param ab_insertar indica si se desea insertar o actualizar dependiendo su valor, si su valor es true el inserta un nuevo registro,
	 * en cambio si su valor es false realiza una actualizacion en la base de datos.
	 * @return Cadena de caracteres con el enlace a la página inicial de tipos eje.
	 */
	public String insertUpdateTipoEje(boolean ab_insertar)
	{
		FacesContext lfc_context;
		TipoEje      lta_tipoEjeInsertUpdate;
		boolean      lb_b;
		String       ls_result;

		lta_tipoEjeInsertUpdate     = getTipoEje();
		lfc_context                 = FacesContext.getCurrentInstance();
		lb_b                        = true;
		ls_result                   = null;

		try
		{
			{
				String ls_idTipoEje;

				ls_idTipoEje     = lta_tipoEjeInsertUpdate.getIdTipoEje();

				lb_b = validateStyles(":fTiposEjeDetalle:idItIdTipoEje", lfc_context, ls_idTipoEje, lb_b);

				if(!StringUtils.isValidString(ls_idTipoEje))
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_TIPO_EJE);
			}

			{
				String ls_nombreTipoEje;
				ls_nombreTipoEje = lta_tipoEjeInsertUpdate.getNombre();

				validateStyles(":fTiposEjeDetalle:idItNombre", lfc_context, ls_nombreTipoEje, lb_b);

				if(!StringUtils.isValidString(ls_nombreTipoEje))
					throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_TIPO_EJE);
			}

			{
				boolean lb_focus;
				lb_focus = true;

				String ls_activo;
				ls_activo     = lta_tipoEjeInsertUpdate.getActivo();

				lb_focus = validateStyles(":fTiposEjeDetalle:idItActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
			}

			{
				String ls_tipoPredio;
				ls_tipoPredio = lta_tipoEjeInsertUpdate.getTipoPredio();

				validateStyles(":fTiposEjeDetalle:tipoPredio", lfc_context, ls_tipoPredio, lb_b);

				if(!StringUtils.isValidString(ls_tipoPredio))
					throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);
			}

			{
				String ls_complemento;
				ls_complemento = lta_tipoEjeInsertUpdate.getComplementoString();

				validateStyles(":fTiposEjeDetalle:complemento", lfc_context, ls_complemento, lb_b);

				if(!StringUtils.isValidString(ls_complemento))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_COMPLEMENTO);
			}

			ipr_parameterRemote.insertUpdateTiposEje(
			    lta_tipoEjeInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			{
				BeanReference lbr_beanReference;

				lbr_beanReference = getBeanReference();

				lbr_beanReference.setTipoEje(null);
				lbr_beanReference.setComplementoDireccionByTipoPredio(null);
				lbr_beanReference.setTipoEjeByTipoPredio(null);
			}

			ls_result = NavegacionCommon.TIPOS_EJE;

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fTiposEjeDetalle:gTiposADetMsg");

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
			PrimeFaces.current().ajax().update("fTiposEje:gTiposE");

			return null;
		}

		return ls_result;
	}
}
