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

import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.pgn.UnidadTiempoVencimiento;

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
 * paramétrica de etapas.
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanEtapas")
@SessionScoped
public class BeanEtapas extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2140349806277142853L;

	/** Propiedad ice data etapas. */
	private Collection<Etapa> ice_dataEtapas;

	/** Propiedad ie etapa. */
	private Etapa ie_etapa;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ib insert. */
	private boolean ib_insert;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de data etapas.
	 *
	 * @param accr_ccr asigna el valor a la propiedad data etapas
	 */
	public void setDataEtapas(Collection<Etapa> accr_ccr)
	{
		ice_dataEtapas = accr_ccr;
	}

	/**
	 * Retorna el valor de data etapas.
	 *
	 * @return el valor de data etapas
	 */
	public Collection<Etapa> getDataEtapas()
	{
		if(ice_dataEtapas == null)
			ice_dataEtapas = new LinkedList<Etapa>();

		return ice_dataEtapas;
	}

	/**
	 * Modifica el valor de etapa.
	 *
	 * @param acr_cr asigna el valor a la propiedad etapa
	 */
	public void setEtapa(Etapa acr_cr)
	{
		ie_etapa = acr_cr;
	}

	/**
	 * Retorna el valor de etapa.
	 *
	 * @return el valor de etapa
	 */
	public Etapa getEtapa()
	{
		return ie_etapa;
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
	 * Metodo que indica si se desea insertar una etapa.
	 *
	 * @param acr_Etapaseleccionado objeto el cual se va a insertar o modificar
	 * @param ab_proceso indica si se va a insetar o se va a modificar
	 * @return Cadena de caracteres con el enlace a la página de detalle de etapas
	 */
	public String botonInsertar(Etapa acr_Etapaseleccionado, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			acr_Etapaseleccionado = new Etapa();

			setEtapa(acr_Etapaseleccionado);
			setInsert(true);
		}
		else
		{
			setEtapa(acr_Etapaseleccionado);
			setInsert(false);
		}

		ls_return = NavegacionCommon.ETAPAS_DETALLE;

		return ls_return;
	}

	/**
	 * Metodo para traer de la base de datos todos los registros existentes de la tabla SDB_PGN_ETAPA
	 * para pintarlos en la capa de presentacion.
	 *
	 * @return Colección de etapas resultante de la consulta
	 */
	public Collection<Etapa> cargarEtapas()
	{
		Collection<Etapa> lccr_Etapas;
		lccr_Etapas = new LinkedList<Etapa>();

		try
		{
			lccr_Etapas = ipr_parameterRemote.findAllEtapas(getUserId(), getLocalIpAddress(), getRemoteIpAddress());

			setDataEtapas(lccr_Etapas);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccr_Etapas;
	}

	/**
	 * Metodo para traer de la base de datos todos los registros de la tabla SDB_PGN_FASES que se encuentren activas.
	 *
	 * @return Colección de fases resultante de la consulta a la base de datos
	 */
	public Collection<Fases> findAllFases()
	{
		Collection<Fases> lcf_datos;
		lcf_datos = null;

		try
		{
			lcf_datos = irr_referenceRemote.findAllFasesActivas(getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcf_datos;
	}

	/**
	 * Find all unidades de tiempo.
	 *
	 * @return el valor de collection
	 */
	public Collection<UnidadTiempoVencimiento> findAllUnidadesDeTiempo()
	{
		Collection<UnidadTiempoVencimiento> lcutv_datos;
		lcutv_datos = null;

		try
		{
			lcutv_datos = irr_referenceRemote.findAllUnidadesDeTiempo(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcutv_datos;
	}

	/**
	 * Método que instancia algunos atributos de la clase.
	 */
	public void iniciar()
	{
		ie_etapa = new Etapa();
		setInsert(false);
	}

	/**
	 * Metodo para insertar o modificar registros en la tabla SDB_PGN_ETAPA.
	 *
	 * @param ab_insertar indica si se desea insertar o actualizar dependiendo su valor, si su valor es true el inserta un nuevo registro,
	 * en cambio si su valor es false realiza una actualizacion en la base de datos.
	 * @return Cadena de caracteres con el enlace a la página inicial de etapas.
	 */
	public String insertUpdateEtapa(boolean ab_insertar)
	{
		FacesContext lfc_context;
		boolean      lb_focus;
		Etapa        le_etapa;
		String       ls_resultado;

		le_etapa         = getEtapa();
		lfc_context      = FacesContext.getCurrentInstance();
		lb_focus         = true;
		ls_resultado     = null;

		if(le_etapa != null)
		{
			try
			{
				{
					long ls_idEtapa;

					ls_idEtapa = le_etapa.getIdEtapa();

					if(ls_idEtapa <= 0L)
					{
						validateStyles(":fEtapasDetalle:idItIdEtapa", lfc_context, "", lb_focus);
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_ETAPA);
					}
					else
						lb_focus = validateStyles(
							    ":fEtapasDetalle:idItIdEtapa", lfc_context, NumericUtils.getLongWrapper(ls_idEtapa),
							    lb_focus
							);
				}

				{
					String ls_nombreEtapa;

					ls_nombreEtapa     = le_etapa.getNombre();

					lb_focus = validateStyles(":fEtapasDetalle:idItNombreEtapa", lfc_context, ls_nombreEtapa, lb_focus);

					if(!StringUtils.isValidString(ls_nombreEtapa))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;

					ls_activo     = le_etapa.getEstado();

					lb_focus = validateStyles(":fEtapasDetalle:idSOMActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				{
					String ls_indicadorPeso;

					ls_indicadorPeso     = le_etapa.getIndicadorPeso();

					lb_focus = validateStyles(
						    ":fEtapasDetalle:idSOMIndicadorPeso", lfc_context, ls_indicadorPeso, lb_focus
						);

					if(!StringUtils.isValidString(ls_indicadorPeso))
						throw new B2BException(ErrorKeys.ERROR_SIN_INDICADOR_PESO);
				}

				{
					String ls_indicadorBloqueo;

					ls_indicadorBloqueo     = le_etapa.getIndicadorBloqueo();

					lb_focus = validateStyles(
						    ":fEtapasDetalle:idSOMIndicadorBloqueo", lfc_context, ls_indicadorBloqueo, lb_focus
						);

					if(!StringUtils.isValidString(ls_indicadorBloqueo))
						throw new B2BException(ErrorKeys.ERROR_SIN_INDICADOR_BLOQUEO);
				}

				{
					String ls_indicadorDesborde;

					ls_indicadorDesborde     = le_etapa.getIndicadorDesborde();

					lb_focus = validateStyles(
						    ":fEtapasDetalle:idSOMIndicadorDesborde", lfc_context, ls_indicadorDesborde, lb_focus
						);

					if(!StringUtils.isValidString(ls_indicadorDesborde))
						throw new B2BException(ErrorKeys.ERROR_SIN_INDICADOR_DESBORDE);
				}

				{
					long ls_idFase;

					ls_idFase = le_etapa.getIdFase();

					if(ls_idFase <= 0L)
					{
						validateStyles(":fEtapasDetalle:idSOMIdFase", lfc_context, "", lb_focus);
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_FASE);
					}
					else
						lb_focus = validateStyles(
							    ":fEtapasDetalle:idSOMIdFase", lfc_context, NumericUtils.getLongWrapper(ls_idFase),
							    lb_focus
							);
				}

				{
					String ls_tipoReparto;

					ls_tipoReparto     = le_etapa.getTipoReparto();

					lb_focus = validateStyles(
						    ":fEtapasDetalle:idSOMTipoReparto", lfc_context, ls_tipoReparto, lb_focus
						);

					if(!StringUtils.isValidString(ls_tipoReparto))
						throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_REPARTO);
				}

				{
					Boolean lb_generarQR;

					lb_generarQR = BooleanUtils.getBoolean(le_etapa.getGenerarQRString());

					le_etapa.setGeneraQR(BooleanUtils.getBooleanValue(lb_generarQR));
				}

				ipr_parameterRemote.insertUpdateEtapa(
				    le_etapa, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				{
					BeanReference lbr_beanReference;

					lbr_beanReference = getBeanReference();

					lbr_beanReference.setEtapaActivo(null);
					lbr_beanReference.setEtapaActivoById(null);
				}

				addMessage(MessagesKeys.PROCESO_COMPLETADO);

				ls_resultado = NavegacionCommon.ETAPAS;

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
				PrimeFaces.current().ajax().update("fEtapas:gEtapasMsg");
			}
		}

		return ls_resultado;
	}
}
