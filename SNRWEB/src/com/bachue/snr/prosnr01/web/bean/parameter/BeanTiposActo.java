package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de eventos e interacciones con la capa de presentación de la pantalla
 * paramétrica de tipos acto.
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanTiposActo")
@SessionScoped
public class BeanTiposActo extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -708455950581870187L;

	/** Propiedad iccr data tipos acto. */
	private Collection<TipoActo> iccr_dataTiposActo;

	/** Propiedad icma datos auditoria. */
	private Collection<TipoActo> icta_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ita tipo acto. */
	private TipoActo ita_tipoActo;

	/** Propiedad ib insert. */
	private boolean ib_insert;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de data tipos acto.
	 *
	 * @param acta_cta asigna el valor a la propiedad data tipos acto
	 */
	public void setDataTiposActo(Collection<TipoActo> acta_cta)
	{
		iccr_dataTiposActo = acta_cta;
	}

	/**
	 * Retorna el valor de data tipos acto.
	 *
	 * @return el valor de data tipos acto
	 */
	public Collection<TipoActo> getDataTiposActo()
	{
		if(iccr_dataTiposActo == null)
			iccr_dataTiposActo = new LinkedList<TipoActo>();

		return iccr_dataTiposActo;
	}

	/**
	 * Modifica el valor de datos auditoria.
	 *
	 * @param acta_cta asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<TipoActo> acta_cta)
	{
		icta_datosAuditoria = acta_cta;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<TipoActo> getDatosAuditoria()
	{
		return icta_datosAuditoria;
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
	 * Modifica el valor de tipo acto.
	 *
	 * @param ata_ta asigna el valor a la propiedad tipo acto
	 */
	public void setTipoActo(TipoActo ata_ta)
	{
		ita_tipoActo = ata_ta;
	}

	/**
	 * Retorna el valor de tipo acto.
	 *
	 * @return el valor de tipo acto
	 */
	public TipoActo getTipoActo()
	{
		return ita_tipoActo;
	}

	/**
	 * Determina dependiendo de un flag booleano si se va a insertar un registro, o a modificar
	 * uno ya existente en la tabla SDB_PGN_TIPO_ACTO.
	 *
	 * @param acr_tipoActoSeleccionado es el objeto que se desea insertar o actualizar
	 * @param ab_proceso indica si la presentación de la página debe ser de inserción o actualización
	 * @return Cadena de caracteres con el enlace a la página de detalle de tipos acto
	 */
	public String botonInsertar(TipoActo acr_tipoActoSeleccionado, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			acr_tipoActoSeleccionado = new TipoActo();

			setTipoActo(acr_tipoActoSeleccionado);
			setInsert(true);
		}
		else
		{
			setTipoActo(acr_tipoActoSeleccionado);
			setInsert(false);

			Collection<TipoActo> lcta_tipoActo;

			lcta_tipoActo = new ArrayList<TipoActo>();
			lcta_tipoActo.add(acr_tipoActoSeleccionado);

			setDatosAuditoria(lcta_tipoActo);
		}

		ls_return = NavegacionCommon.TIPOS_ACTO_DETALLE;

		return ls_return;
	}

	/**
	 * Consulta en la base de datos todos los registros existentes de tipo acto.
	 *
	 * @return Colección de tipo acto resultante de la consulta
	 */
	public Collection<TipoActo> cargarTiposActo()
	{
		Collection<TipoActo> lcta_tiposActo;
		lcta_tiposActo = new LinkedList<TipoActo>();

		try
		{
			lcta_tiposActo = ipr_parameterRemote.findAllTipoActo(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			setDataTiposActo(lcta_tiposActo);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcta_tiposActo;
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
		ita_tipoActo = new TipoActo();
		setInsert(false);
	}

	/**
	 * Metodo para el manejo de inserciones o actualizaciones de tipo acto.
	 *
	 * @param ab_insertar indica si se desea insertar o actualizar dependiendo su valor, si su valor es true el inserta un nuevo registro,
	 * en cambio si su valor es false realiza una actualizacion en la base de datos.
	 * @return Cadena de caracteres con el enlace a la página inicial de tipos acto.
	 */
	public String insertUpdateTipoActo(boolean ab_insertar)
	{
		FacesContext lfc_context;
		boolean      lb_focus;
		TipoActo     lta_tipoActoInsertUpdate;
		String       ls_final;

		lta_tipoActoInsertUpdate     = getTipoActo();
		lfc_context                  = FacesContext.getCurrentInstance();
		lb_focus                     = true;
		ls_final                     = null;

		try
		{
			{
				String ls_idTipoActo;
				ls_idTipoActo     = lta_tipoActoInsertUpdate.getIdTipoActo();

				lb_focus = validateStyles(":fTiposActoDetalle:idItTipoActo", lfc_context, ls_idTipoActo, lb_focus);

				if(!StringUtils.isValidString(ls_idTipoActo))
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_TIPO_ACTO);
			}

			{
				String ls_nombreTipoActo;
				ls_nombreTipoActo     = lta_tipoActoInsertUpdate.getNombre();

				lb_focus = validateStyles(":fTiposActoDetalle:idItNombre", lfc_context, ls_nombreTipoActo, lb_focus);

				if(!StringUtils.isValidString(ls_nombreTipoActo))
					throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_TIPO_ACTO);
			}

			{
				String ls_aplicaTarifaDoc;
				ls_aplicaTarifaDoc     = lta_tipoActoInsertUpdate.getAplicaTarifaRetencionDocumental();

				lb_focus = validateStyles(
					    ":fTiposActoDetalle:idretencionDocumental", lfc_context, ls_aplicaTarifaDoc, lb_focus
					);

				if(!StringUtils.isValidString(ls_aplicaTarifaDoc))
					throw new B2BException(ErrorKeys.ERROR_SIN_RETENCION_TARIFA_DOCUMENTAL);
			}

			{
				/*String ls_impuestoPorCuantia;
				ls_impuestoPorCuantia     = lta_tipoActoInsertUpdate.getImpuestoPorCuantia();
				lb_focus = validateStyles(
				        ":fTiposActoDetalle:idItImpuestoPorCuantia", lfc_context, ls_impuestoPorCuantia, lb_focus
				    );
				if(!StringUtils.isValidString(ls_impuestoPorCuantia))
				    throw new B2BException(ErrorKeys.ERROR_SIN_IMPUESTO_POR_CUANTIA);*/
			}

			{
				String ls_activo;
				ls_activo     = lta_tipoActoInsertUpdate.getActivo();

				lb_focus = validateStyles(":fTiposActoDetalle:idItActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
			}

			{
				String ls_requiereCuantia;
				ls_requiereCuantia     = lta_tipoActoInsertUpdate.getRequiereCuantia();

				lb_focus = validateStyles(
					    ":fTiposActoDetalle:idItRequiereCuantia", lfc_context, ls_requiereCuantia, lb_focus
					);

				if(!StringUtils.isValidString(ls_requiereCuantia))
					throw new B2BException(ErrorKeys.ERROR_SIN_REQUIERE_CUANTIA);
			}

			{
				String ls_aplicaDesborde;
				ls_aplicaDesborde     = lta_tipoActoInsertUpdate.getAplicaDesborde();

				lb_focus = validateStyles(
					    ":fTiposActoDetalle:idaplicaDesborde", lfc_context, ls_aplicaDesborde, lb_focus
					);

				if(!StringUtils.isValidString(ls_aplicaDesborde))
					throw new B2BException(ErrorKeys.ERROR_SIN_APLICA_DESBORDE);
			}

			{
				String ls_avaluo;
				ls_avaluo     = lta_tipoActoInsertUpdate.getAvaluo();

				lb_focus = validateStyles(":fTiposActoDetalle:idItAvaluo", lfc_context, ls_avaluo, lb_focus);

				if(!StringUtils.isValidString(ls_avaluo))
					throw new B2BException(ErrorKeys.ERROR_VALOR_AVALUO);
			}

			{
				String ls_requiereMatricula;
				ls_requiereMatricula     = lta_tipoActoInsertUpdate.getRequiereMatricula();

				lb_focus = validateStyles(
					    ":fTiposActoDetalle:idItRequiereMatricula", lfc_context, ls_requiereMatricula, lb_focus
					);

				if(!StringUtils.isValidString(ls_requiereMatricula))
					throw new B2BException(ErrorKeys.ERROR_REQUIERE_MATRICULA);
			}

			{
				String ls_tarifaExenta;
				ls_tarifaExenta     = lta_tipoActoInsertUpdate.getTarifaExenta();

				lb_focus = validateStyles(":fTiposActoDetalle:idtarifaexenta", lfc_context, ls_tarifaExenta, lb_focus);

				if(!StringUtils.isValidString(ls_tarifaExenta))
					throw new B2BException(ErrorKeys.ERROR_SIN_TARIFA_EXENTA);
			}

			{
				String ls_actoSinCuantia;
				ls_actoSinCuantia     = lta_tipoActoInsertUpdate.getActoSinCuantia();

				lb_focus = validateStyles(
					    ":fTiposActoDetalle:idItActoSinCuantia", lfc_context, ls_actoSinCuantia, lb_focus
					);

				if(!StringUtils.isValidString(ls_actoSinCuantia))
					throw new B2BException(ErrorKeys.ERROR_ACTO_SIN_CUANTIA);
			}

			{
				String ls_aperturaMatricula;
				ls_aperturaMatricula     = lta_tipoActoInsertUpdate.getAperturaMatricula();

				lb_focus = validateStyles(
					    ":fTiposActoDetalle:idItAperturaMatricula", lfc_context, ls_aperturaMatricula, lb_focus
					);

				if(!StringUtils.isValidString(ls_aperturaMatricula))
					throw new B2BException(ErrorKeys.ERROR_SIN_APERTURA_MATRICULA);
			}

			{
				String ls_certificadoObligatorio;
				ls_certificadoObligatorio     = lta_tipoActoInsertUpdate.getCertificadoObligatorioString();

				lb_focus = validateStyles(
					    ":fTiposActoDetalle:idItCertificadoObligatorio", lfc_context, ls_certificadoObligatorio,
					    lb_focus
					);

				if(!StringUtils.isValidString(ls_certificadoObligatorio))
					throw new B2BException(ErrorKeys.ERROR_SIN_CERTIFICADO_OBLIGATORIO);
			}

			{
				String ls_generaSegregacion;
				ls_generaSegregacion     = lta_tipoActoInsertUpdate.getGeneraSegregacion();

				lb_focus = validateStyles(
					    ":fTiposActoDetalle:idItGeneraSegregacion", lfc_context, ls_generaSegregacion, lb_focus
					);

				if(!StringUtils.isValidString(ls_generaSegregacion))
					throw new B2BException(ErrorKeys.ERROR_SIN_GENERA_SEGREGACION);
			}

			{
				String ls_inscripcionAdicional;
				ls_inscripcionAdicional     = lta_tipoActoInsertUpdate.getInscripcionAdicional();

				lb_focus = validateStyles(
					    ":fTiposActoDetalle:idItInscripcionAdicional", lfc_context, ls_inscripcionAdicional, lb_focus
					);

				if(!StringUtils.isValidString(ls_inscripcionAdicional))
					throw new B2BException(ErrorKeys.ERROR_SIN_INSCRIPCION_ADICIONAL);
			}

			{
				String ls_requiereTipoAdquisicion;
				ls_requiereTipoAdquisicion     = lta_tipoActoInsertUpdate.getRequiereTipoAdquisicion();

				lb_focus = validateStyles(
					    ":fTiposActoDetalle:idItRequiereTipoAdquisicion", lfc_context, ls_requiereTipoAdquisicion,
					    lb_focus
					);

				if(!StringUtils.isValidString(ls_requiereTipoAdquisicion))
					throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_ADQUISICION);
			}

			{
				String ls_validarArea;
				ls_validarArea     = lta_tipoActoInsertUpdate.getValidarAreaString();

				lb_focus = validateStyles(":fTiposActoDetalle:idItValidarArea", lfc_context, ls_validarArea, lb_focus);

				if(!StringUtils.isValidString(ls_validarArea))
					throw new B2BException(ErrorKeys.ERROR_SIN_VALIDAR_AREA);
			}

			{
				String ls_sumatoriaCoeficiente;
				ls_sumatoriaCoeficiente     = lta_tipoActoInsertUpdate.getSumatoriaCoeficiente();

				lb_focus = validateStyles(
					    ":fTiposActoDetalle:idSumatoriaCoeficiente", lfc_context, ls_sumatoriaCoeficiente, lb_focus
					);

				if(!StringUtils.isValidString(ls_sumatoriaCoeficiente))
					throw new B2BException(ErrorKeys.ERROR_SIN_SUMATORIA_COEFICIENTE);
			}

			{
				String ls_sumatoriaArea;
				ls_sumatoriaArea     = lta_tipoActoInsertUpdate.getSumatoriaArea();

				lb_focus = validateStyles(
					    ":fTiposActoDetalle:idSumatoriaArea", lfc_context, ls_sumatoriaArea, lb_focus
					);

				if(!StringUtils.isValidString(ls_sumatoriaArea))
					throw new B2BException(ErrorKeys.ERROR_SIN_SUMATORIA_AREA);
			}

			{
				String ls_requiereTipoAdquisicion;
				ls_requiereTipoAdquisicion     = lta_tipoActoInsertUpdate.getRequiereTipoAdquisicion();

				lb_focus = validateStyles(
					    ":fTiposActoDetalle:idItRequiereTipoAdquisicion", lfc_context, ls_requiereTipoAdquisicion,
					    lb_focus
					);

				if(!StringUtils.isValidString(ls_requiereTipoAdquisicion))
					throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_ADQUISICION);
			}

			ipr_parameterRemote.insertUpdateTipoActo(
			    lta_tipoActoInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
			

			{
				BeanReference lbr_beanReference;
				
				lbr_beanReference = getBeanReference();
				
				lbr_beanReference.setTipoActo(null);
				lbr_beanReference.setRol(null);
				lbr_beanReference.setCodigo4Y5Digitos(null);
			}
			

			ls_final = NavegacionCommon.TIPOS_ACTO;

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fTiposActoDetalle:gTiposADetMsg");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fTiposActo:gTiposA");

			return null;
		}

		return ls_final;
	}
}
