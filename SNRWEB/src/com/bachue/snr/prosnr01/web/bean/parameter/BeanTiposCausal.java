package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

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
 * paramétrica de tipos causal.
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanTiposCausal")
@SessionScoped
public class BeanTiposCausal extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4134893217650801986L;

	/** Propiedad ictc datos auditoria. */
	private Collection<TipoCausal> ictc_datosAuditoria;

	/** Propiedad icte data tipos causal. */
	private Collection<TipoCausal> icte_dataTiposCausal;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is proceso actual. */
	private String is_procesoActual;

	/** Propiedad ite tipo causal. */
	private TipoCausal ite_tipoCausal;

	/** Propiedad ib disable subproceso. */
	private boolean ib_disableSubproceso;

	/** Propiedad ib insert. */
	private boolean ib_insert;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de datos auditoria.
	 *
	 * @param actc_ctc asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<TipoCausal> actc_ctc)
	{
		ictc_datosAuditoria = actc_ctc;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<TipoCausal> getDatosAuditoria()
	{
		return ictc_datosAuditoria;
	}

	/**
	 * Modifica el valor de data tipos causal.
	 *
	 * @param acta_cta asigna el valor a la propiedad data tipos causal
	 */
	public void setDataTiposCausal(Collection<TipoCausal> acta_cta)
	{
		icte_dataTiposCausal = acta_cta;
	}

	/**
	 * Retorna el valor de data tipos causal.
	 *
	 * @return el valor de data tipos causal
	 */
	public Collection<TipoCausal> getDataTiposCausal()
	{
		if(icte_dataTiposCausal == null)
			icte_dataTiposCausal = new LinkedList<TipoCausal>();

		return icte_dataTiposCausal;
	}

	/**
	 * Modifica el valor de disable subproceso.
	 *
	 * @param ab_b asigna el valor a la propiedad disable subproceso
	 */
	public void setDisableSubproceso(boolean ab_b)
	{
		ib_disableSubproceso = ab_b;
	}

	/**
	 * Valida la propiedad disable subproceso.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en disable subproceso
	 */
	public boolean isDisableSubproceso()
	{
		return ib_disableSubproceso;
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
	 * Modifica el valor de proceso actual.
	 *
	 * @param as_s asigna el valor a la propiedad proceso actual
	 */
	public void setProcesoActual(String as_s)
	{
		is_procesoActual = as_s;
	}

	/**
	 * Retorna el valor de proceso actual.
	 *
	 * @return el valor de proceso actual
	 */
	public String getProcesoActual()
	{
		return is_procesoActual;
	}

	/**
	 * Modifica el valor de tipo causal.
	 *
	 * @param ata_ta asigna el valor a la propiedad tipo causal
	 */
	public void setTipoCausal(TipoCausal ata_ta)
	{
		ite_tipoCausal = ata_ta;

		if(ite_tipoCausal != null)
			setProcesoActual(ite_tipoCausal.getIdProceso());
	}

	/**
	 * Retorna el valor de tipo causal.
	 *
	 * @return el valor de tipo causal
	 */
	public TipoCausal getTipoCausal()
	{
		if(ite_tipoCausal == null)
			ite_tipoCausal = new TipoCausal();

		return ite_tipoCausal;
	}

	/**
	 * Determina dependiendo de un flag booleano si se va a insertar un registro, o a modificar
	 * uno ya existente en la tabla SDB_ACC_TIPO_CAUSAL.
	 *
	 * @param acr_tipoCausalSeleccionado correspondiente al valor del tipo de objeto TipoCausal
	 * @param ab_proceso indica si la presentación de la página debe ser de inserción o actualización
	 * @return Cadena de caracteres con el enlace a la página de detalle de tipos causal
	 */
	public String botonInsertar(TipoCausal acr_tipoCausalSeleccionado, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			acr_tipoCausalSeleccionado = new TipoCausal();

			setTipoCausal(acr_tipoCausalSeleccionado);
			setInsert(true);
		}
		else
		{
			setTipoCausal(acr_tipoCausalSeleccionado);
			setInsert(false);

			Collection<TipoCausal> lctc_tipoCausal;

			lctc_tipoCausal = new ArrayList<TipoCausal>();
			lctc_tipoCausal.add(acr_tipoCausalSeleccionado);

			setDatosAuditoria(lctc_tipoCausal);
		}

		ls_return = NavegacionCommon.TIPOS_CAUSAL_DETALLE;

		return ls_return;
	}

	/**
	 * Consulta en la base de datos todos los registros existentes de tipo causal.
	 *
	 * @return Colección de tipo causal resultante de la consulta
	 */
	public Collection<TipoCausal> cargarTiposCausal()
	{
		Collection<TipoCausal> lcta_tiposCausal;
		lcta_tiposCausal = new LinkedList<TipoCausal>();

		try
		{
			lcta_tiposCausal = ipr_parameterRemote.findAllTiposCausal(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			setDataTiposCausal(lcta_tiposCausal);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcta_tiposCausal;
	}

	/**
	 * Consulta en la base de datos todos los registros existentes de procesos.
	 *
	 * @return Colección de proceso resultante de la consulta
	 */
	public Collection<Proceso> findAllProcesos()
	{
		Collection<Proceso> lcp_datos;
		lcp_datos = null;

		try
		{
			lcp_datos = irr_referenceRemote.findAllProcesosActivos(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcp_datos;
	}

	/**
	 * Consulta en la base de datos todos los registros existentes de subprocesos.
	 *
	 * @return Colección de subproceso resultante de la consulta
	 */
	public Collection<Subproceso> findAllSubprocesos()
	{
		Collection<Subproceso> lcs_datos;
		lcs_datos = null;

		try
		{
			lcs_datos = irr_referenceRemote.findSubprocesos(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcs_datos;
	}

	/**
	 * Método que instancia algunos objetos de la clase.
	 */
	public void iniciar()
	{
		ite_tipoCausal = new TipoCausal();
		setInsert(false);
		setDisableSubproceso(false);
	}

	/**
	 * Metodo para el manejo de inserciones o actualizaciones de tipo causal.
	 *
	 * @param ab_insertar indica si se desea insertar o actualizar dependiendo su valor, si su valor es true el inserta un nuevo registro,
	 * en cambio si su valor es false realiza una actualizacion en la base de datos.
	 * @return Cadena de caracteres con el enlace a la página inicial de tipos causal.
	 */
	public String insertUpdateTipoCausal(boolean ab_insertar)
	{
		FacesContext lfc_context;
		TipoCausal   lta_tipoCausalInsertUpdate;
		String       ls_result;

		lta_tipoCausalInsertUpdate     = getTipoCausal();
		lfc_context                    = FacesContext.getCurrentInstance();
		ls_result                      = null;

		try
		{
			{
				String ls_idTipoCausal;
				ls_idTipoCausal = lta_tipoCausalInsertUpdate.getIdTipoCausal();

				if(!validateStyles(":fTiposCausalDetalle:idItIdTipoCausal", lfc_context, ls_idTipoCausal, true))
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_TIPO_CAUSAL);
			}

			{
				String ls_estadoCausal;
				ls_estadoCausal = lta_tipoCausalInsertUpdate.getEstadoCausal();

				if(!validateStyles(":fTiposCausalDetalle:idSOMEstadoCausal", lfc_context, ls_estadoCausal, true))
					throw new B2BException(ErrorKeys.ERROR_SIN_ESTADO_CAUSAL);
			}

			{
				long ll_version;
				ll_version = lta_tipoCausalInsertUpdate.getVersion();

				String ls_validator;

				if(ll_version == 0)
					ls_validator = "";
				else
					ls_validator = StringUtils.getString(ll_version);

				if(!validateStyles(":fTiposCausalDetalle:idItVersion", lfc_context, ls_validator, true))
					throw new B2BException(ErrorKeys.ERROR_SIN_VERSION);
			}

			{
				String ls_descripcion;

				ls_descripcion = lta_tipoCausalInsertUpdate.getNombre();

				if(!validateStyles(":fTiposCausalDetalle:idITADescripcion", lfc_context, ls_descripcion, true))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
			}

			ipr_parameterRemote.insertUpdateTiposCausal(
			    lta_tipoCausalInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			ls_result = NavegacionCommon.TIPOS_CAUSAL;

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fTiposCausalDetalle:gTiposADetMsg");

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
			PrimeFaces.current().ajax().update("fTiposCausal:gTiposC");

			return null;
		}

		return ls_result;
	}
}
