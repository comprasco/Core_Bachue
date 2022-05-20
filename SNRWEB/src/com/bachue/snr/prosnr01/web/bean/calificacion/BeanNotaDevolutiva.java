package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroParcialCalificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;

import com.bachue.snr.prosnr01.web.bean.dispositivos.BeanDispositivos;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.component.inputtext.InputText;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanNotaDevolutiva.
 *
 * @author
 */
@SessionScoped
@ManagedBean(name = "beanNotaDevolutiva")
public class BeanNotaDevolutiva extends BeanDispositivos implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2096507104369954298L;

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fNotaDevolutiva:globalMsg";

	/** Constante is_messageId. */
	private static final String is_messageId = "fresumenNotasDevolutivas:globalMsg";

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad icrc info turnos. */
	private Collection<RegistroCalificacion> icrc_infoTurnos;

	/** Propiedad ictc detalle tipo causal. */
	private Collection<TipoCausal> ictc_detalleTipoCausal;

	/** Propiedad iltc tipos causales filtro. */
	private Collection<TipoCausal> iltc_tiposCausalesFiltro;

	/** Propiedad iltc filtro tipos causales. */
	private List<TipoCausal> iltc_filtroTiposCausales;

	/** Propiedad il id motivo. */
	private Long il_idMotivo;

	/** Propiedad ioo oficina medida cautelar. */
	private OficinaOrigen ioo_oficinaMedidaCautelar;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irpc registro parcial calificacion. */
	private RegistroParcialCalificacion irpc_registroParcialCalificacion;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isc image cancelacion. */
	private StreamedContent isc_imageCancelacion;

	/** Propiedad isc imagen. */
	private StreamedContent isc_imagen;

	/** Propiedad is complementacion causales. */
	private String is_complementacionCausales;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is numero proceso. */
	private String is_numeroProceso;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is referencia. */
	private String is_referencia;

	/** Propiedad is turno. */
	private String is_turno;

	/** Propiedad ib aprobacion vinculados. */
	private boolean ib_aprobacionVinculados;

	/** Propiedad ib causales vinculacion. */
	private boolean ib_causalesVinculacion;

	/** Propiedad ib habilitar campos medida cautelar. */
	private boolean ib_habilitarCamposMedidaCautelar;

	/** Propiedad ib habilitar medida cautelar. */
	private boolean ib_habilitarMedidaCautelar;

	/** Propiedad ib ind vinculado. */
	private boolean ib_indVinculado;

	/** Propiedad ib pdf cancelacion. */
	private boolean ib_pdfCancelacion;

	/** Propiedad ib pdf generado. */
	private boolean ib_pdfGenerado;

	/** Propiedad ib pdf vinculacion. */
	private boolean ib_pdfVinculacion;

	/** Propiedad ib registro parcial. */
	private boolean ib_registroParcial;

	/** Propiedad ib reproduccion constancia. */
	private boolean ib_reproduccionConstancia;
	private boolean ib_testamentos;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de aprobacion vinculados.
	 *
	 * @param ab_b asigna el valor a la propiedad aprobacion vinculados
	 */
	public void setAprobacionVinculados(boolean ab_b)
	{
		ib_aprobacionVinculados = ab_b;
	}

	/**
	 * Valida la propiedad aprobacion vinculados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en aprobacion vinculados
	 */
	public boolean isAprobacionVinculados()
	{
		return ib_aprobacionVinculados;
	}

	/**
	 * Modifica el valor de causales vinculacion.
	 *
	 * @param ab_b asigna el valor a la propiedad causales vinculacion
	 */
	public void setCausalesVinculacion(boolean ab_b)
	{
		ib_causalesVinculacion = ab_b;
	}

	/**
	 * Valida la propiedad causales vinculacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en causales vinculacion
	 */
	public boolean isCausalesVinculacion()
	{
		return ib_causalesVinculacion;
	}

	/**
	 * Modifica el valor de complementacion causales.
	 *
	 * @param as_s asigna el valor a la propiedad complementacion causales
	 */
	public void setComplementacionCausales(String as_s)
	{
		is_complementacionCausales = as_s;
	}

	/**
	 * Retorna el valor de complementacion causales.
	 *
	 * @return el valor de complementacion causales
	 */
	public String getComplementacionCausales()
	{
		return is_complementacionCausales;
	}

	/**
	 * Modifica el valor de detalle tipo causal.
	 *
	 * @param actc_ctc asigna el valor a la propiedad detalle tipo causal
	 */
	public void setDetalleTipoCausal(Collection<TipoCausal> actc_ctc)
	{
		ictc_detalleTipoCausal = actc_ctc;
	}

	/**
	 * Retorna el valor de detalle tipo causal.
	 *
	 * @return el valor de detalle tipo causal
	 */
	public Collection<TipoCausal> getDetalleTipoCausal()
	{
		return ictc_detalleTipoCausal;
	}

	/**
	 * Modifica el valor de filtro tipos causales.
	 *
	 * @param altc_ltc asigna el valor a la propiedad filtro tipos causales
	 */
	public void setFiltroTiposCausales(List<TipoCausal> altc_ltc)
	{
		iltc_filtroTiposCausales = altc_ltc;
	}

	/**
	 * Retorna el valor de filtro tipos causales.
	 *
	 * @return el valor de filtro tipos causales
	 */
	public List<TipoCausal> getFiltroTiposCausales()
	{
		return iltc_filtroTiposCausales;
	}

	/**
	 * Modifica el valor de habilitar campos medida cautelar.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar campos medida cautelar
	 */
	public void setHabilitarCamposMedidaCautelar(boolean ab_b)
	{
		ib_habilitarCamposMedidaCautelar = ab_b;
	}

	/**
	 * Valida la propiedad habilitar campos medida cautelar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar campos medida cautelar
	 */
	public boolean isHabilitarCamposMedidaCautelar()
	{
		return ib_habilitarCamposMedidaCautelar;
	}

	/**
	 * Modifica el valor de habilitar medida cautelar.
	 *
	 * @param as_s asigna el valor a la propiedad habilitar medida cautelar
	 */
	public void setHabilitarMedidaCautelar(boolean as_s)
	{
		ib_habilitarMedidaCautelar = as_s;
	}

	/**
	 * Valida la propiedad habilitar medida cautelar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar medida cautelar
	 */
	public boolean isHabilitarMedidaCautelar()
	{
		return ib_habilitarMedidaCautelar;
	}

	/**
	 * Modifica el valor de id motivo.
	 *
	 * @param al_l asigna el valor a la propiedad id motivo
	 */
	public void setIdMotivo(Long al_l)
	{
		il_idMotivo = al_l;
	}

	/**
	 * Retorna el valor de id motivo.
	 *
	 * @return el valor de id motivo
	 */
	public Long getIdMotivo()
	{
		return il_idMotivo;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param as_s asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de image cancelacion.
	 *
	 * @param asc_sc asigna el valor a la propiedad image cancelacion
	 */
	public void setImageCancelacion(StreamedContent asc_sc)
	{
		isc_imageCancelacion = asc_sc;
	}

	/**
	 * Retorna el valor de image cancelacion.
	 *
	 * @return el valor de image cancelacion
	 */
	public StreamedContent getImageCancelacion()
	{
		return isc_imageCancelacion;
	}

	/**
	 * Modifica el valor de imagen.
	 *
	 * @param asc_sc asigna el valor a la propiedad imagen
	 */
	public void setImagen(StreamedContent asc_sc)
	{
		isc_imagen = asc_sc;
	}

	/**
	 * Retorna el valor de imagen.
	 *
	 * @return el valor de imagen
	 */
	public StreamedContent getImagen()
	{
		return isc_imagen;
	}

	/**
	 * Modifica el valor de ind vinculado.
	 *
	 * @param ab_b asigna el valor a la propiedad ind vinculado
	 */
	public void setIndVinculado(boolean ab_b)
	{
		ib_indVinculado = ab_b;
	}

	/**
	 * Valida la propiedad ind vinculado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ind vinculado
	 */
	public boolean isIndVinculado()
	{
		return ib_indVinculado;
	}

	/**
	 * Modifica el valor de info turnos.
	 *
	 * @param acrc_crc asigna el valor a la propiedad info turnos
	 */
	public void setInfoTurnos(Collection<RegistroCalificacion> acrc_crc)
	{
		icrc_infoTurnos = acrc_crc;
	}

	/**
	 * Retorna el valor de info turnos.
	 *
	 * @return el valor de info turnos
	 */
	public Collection<RegistroCalificacion> getInfoTurnos()
	{
		return icrc_infoTurnos;
	}

	/**
	 * Modifica el valor de nir.
	 *
	 * @param as_s asigna el valor a la propiedad nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna el valor de nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de numero proceso.
	 *
	 * @param as_s asigna el valor a la propiedad numero proceso
	 */
	public void setNumeroProceso(String as_s)
	{
		is_numeroProceso = as_s;
	}

	/**
	 * Retorna el valor de numero proceso.
	 *
	 * @return el valor de numero proceso
	 */
	public String getNumeroProceso()
	{
		return is_numeroProceso;
	}

	/**
	 * Modifica el valor de observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna el valor de observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de oficina medida cautelar.
	 *
	 * @param aoo_oo asigna el valor a la propiedad oficina medida cautelar
	 */
	public void setOficinaMedidaCautelar(OficinaOrigen aoo_oo)
	{
		ioo_oficinaMedidaCautelar = aoo_oo;
	}

	/**
	 * Retorna el valor de oficina medida cautelar.
	 *
	 * @return el valor de oficina medida cautelar
	 */
	public OficinaOrigen getOficinaMedidaCautelar()
	{
		if(ioo_oficinaMedidaCautelar == null)
			ioo_oficinaMedidaCautelar = new OficinaOrigen();

		return ioo_oficinaMedidaCautelar;
	}

	/**
	 * Modifica el valor de pdf cancelacion.
	 *
	 * @param ab_b asigna el valor a la propiedad pdf cancelacion
	 */
	public void setPdfCancelacion(boolean ab_b)
	{
		ib_pdfCancelacion = ab_b;
	}

	/**
	 * Valida la propiedad pdf cancelacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pdf cancelacion
	 */
	public boolean isPdfCancelacion()
	{
		return ib_pdfCancelacion;
	}

	/**
	 * Modifica el valor de pdf generado.
	 *
	 * @param pdfGenerado asigna el valor a la propiedad pdf generado
	 */
	public void setPdfGenerado(boolean pdfGenerado)
	{
		this.ib_pdfGenerado = pdfGenerado;
	}

	/**
	 * Valida la propiedad pdf generado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pdf generado
	 */
	public boolean isPdfGenerado()
	{
		return ib_pdfGenerado;
	}

	/**
	 * Modifica el valor de pdf vinculacion.
	 *
	 * @param ab_b asigna el valor a la propiedad pdf vinculacion
	 */
	public void setPdfVinculacion(boolean ab_b)
	{
		ib_pdfVinculacion = ab_b;
	}

	/**
	 * Valida la propiedad pdf vinculacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pdf vinculacion
	 */
	public boolean isPdfVinculacion()
	{
		return ib_pdfVinculacion;
	}

	/**
	 * Modifica el valor de referencia.
	 *
	 * @param as_s asigna el valor a la propiedad referencia
	 */
	public void setReferencia(String as_s)
	{
		is_referencia = as_s;
	}

	/**
	 * Retorna el valor de referencia.
	 *
	 * @return el valor de referencia
	 */
	public String getReferencia()
	{
		return is_referencia;
	}

	/**
	 * Modifica el valor de registro parcial.
	 *
	 * @param ab_b asigna el valor a la propiedad registro parcial
	 */
	public void setRegistroParcial(boolean ab_b)
	{
		ib_registroParcial = ab_b;
	}

	/**
	 * Valida la propiedad registro parcial.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en registro parcial
	 */
	public boolean isRegistroParcial()
	{
		return ib_registroParcial;
	}

	/**
	 * Modifica el valor de registro parcial calificacion.
	 *
	 * @param arpc_rpc asigna el valor a la propiedad registro parcial calificacion
	 */
	public void setRegistroParcialCalificacion(RegistroParcialCalificacion arpc_rpc)
	{
		irpc_registroParcialCalificacion = arpc_rpc;
	}

	/**
	 * Retorna el valor de registro parcial calificacion.
	 *
	 * @return el valor de registro parcial calificacion
	 */
	public RegistroParcialCalificacion getRegistroParcialCalificacion()
	{
		return irpc_registroParcialCalificacion;
	}

	/**
	 * Modifica el valor de reproduccion constancia.
	 *
	 * @param ab_b asigna el valor a la propiedad reproduccion constancia
	 */
	public void setReproduccionConstancia(boolean ab_b)
	{
		ib_reproduccionConstancia = ab_b;
	}

	/**
	 * Valida la propiedad reproduccion constancia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en reproduccion constancia
	 */
	public boolean isReproduccionConstancia()
	{
		return ib_reproduccionConstancia;
	}

	public void setTestamentos(boolean ab_b)
	{
		ib_testamentos = ab_b;
	}

	public boolean isTestamentos()
	{
		return ib_testamentos;
	}

	/**
	 * Modifica el valor de tipos causales filtro.
	 *
	 * @param ams_ams asigna el valor a la propiedad tipos causales filtro
	 */
	public void setTiposCausalesFiltro(Collection<TipoCausal> ams_ams)
	{
		iltc_tiposCausalesFiltro = ams_ams;
	}

	/**
	 * Retorna el valor de tipos causales filtro.
	 *
	 * @return el valor de tipos causales filtro
	 */
	public Collection<TipoCausal> getTiposCausalesFiltro()
	{
		return iltc_tiposCausalesFiltro;
	}

	/**
	 * Modifica el valor de turno.
	 *
	 * @param as_s asigna el valor a la propiedad turno
	 */
	public void setTurno(String as_s)
	{
		is_turno = as_s;
	}

	/**
	 * Retorna el valor de turno.
	 *
	 * @return el valor de turno
	 */
	public String getTurno()
	{
		return is_turno;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String accionRegresarNotaDevolutiva()
	    throws B2BException
	{
		FacesContext lfc_context;
		String       ls_return;

		lfc_context     = FacesUtils.getFacesContext();
		ls_return       = null;

		if(isIndVinculado() && isCausalesVinculacion())
		{
			setPdfVinculacion(false);
			setCausalesVinculacion(false);

			ls_return = NavegacionCommon.CARGAR_NOTAS_DEVOLUTIVAS;
		}
		else
		{
			ls_return = NavegacionCommon.DETALLE_ACTO;

			setCausalesVinculacion(false);

			if(isReproduccionConstancia())
			{
				BeanPredioDocumentoActo lbpd_pda;

				lbpd_pda = (BeanPredioDocumentoActo)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
						);

				if(lbpd_pda != null)
				{
					lbpd_pda.setOcultarPaneles(false);
					lbpd_pda.setIdTurnoHistoria(getIdTurnoHistoria());
					lbpd_pda.setDocumentos(null);
					lbpd_pda.generarDatosDocumento();

					limpiar();

					ls_return = NavegacionCommon.DETALLE_REPRODUCCION_CONSTANCIA;
				}
			}

			if(isRegistroParcial())
			{
				BeanRegistroCalificacion lbrc_bean;

				lbrc_bean = (BeanRegistroCalificacion)lfc_context.getApplication()
						                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_REGISTRO_CALIFICACION, BeanRegistroCalificacion.class
						);

				lbrc_bean.findMatriculas(false, true);

				ls_return = NavegacionCommon.DETALLE_REGISTRO;
			}
			else if(isTestamentos())
				ls_return = NavegacionCommon.TESTAMENTOS_EJECUTOR;
			else
			{
				BeanPredioDocumentoActo lbpdab_bean;

				lbpdab_bean = (BeanPredioDocumentoActo)lfc_context.getApplication()
						                                              .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
						);

				if(lbpdab_bean != null)
				{
					lbpdab_bean.generarDatosAntSistema();
					lbpdab_bean.generarDatosDocumento();
					lbpdab_bean.obtenerInformacionASEtapa101();
					lbpdab_bean.validarFechaVencimientoActo();
					lbpdab_bean.setMotivoTramite(null);
					lbpdab_bean.getMatriculasRangos();
					lbpdab_bean.getMatriculasIndividuales();
					lbpdab_bean.getMatriculasTmpRangos();
					lbpdab_bean.getMatriculasTmpIndividuales();
				}
			}
		}

		return ls_return;
	}

	/**
	 * Cambiar departamento residencia.
	 */
	public void cambiarDepartamentoResidencia()
	{
		findMunicipioResidencia();
	}

	/**
	 * Cambiar pais residencia.
	 */
	public void cambiarPaisResidencia()
	{
		OficinaOrigen loo_oficina;
		loo_oficina = getOficinaMedidaCautelar();

		if(loo_oficina != null)
		{
			String ls_pais;
			ls_pais = loo_oficina.getIdPais();

			if(
			    !StringUtils.isValidString(ls_pais)
				    || !ls_pais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
			)
				loo_oficina.setIdDepartamento(null);
		}

		findDepartamentosResidencia();
		findMunicipioResidencia();
	}

	/**
	 * Cargar info medidas cautelares.
	 */
	public void cargarInfoMedidasCautelares()
	{
		try
		{
			Collection<TipoActo> lcta_actos;
			lcta_actos = irr_calificacionRemote.findTipoActos(getTurno());

			String ls_tipoActo;

			if(CollectionUtils.isValidCollection(lcta_actos))
			{
				for(TipoActo la_acto : lcta_actos)
				{
					if(la_acto != null)
					{
						ls_tipoActo = la_acto.getIdTipoActo();

						if(!isHabilitarMedidaCautelar())
						{
							setHabilitarMedidaCautelar(
							    irr_registroRemote.perteneceActoMedidaCautelar(
							        ls_tipoActo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							    )
							);

							if(isHabilitarMedidaCautelar())
							{
								setReferencia(la_acto.getReferencia());
								setNumeroProceso(la_acto.getNumeroProceso());

								cargarOficinaOrigenMedidaCautelar();

							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar oficina origen medida cautelar.
	 */
	public void cargarOficinaOrigenMedidaCautelar()
	{
		try
		{
			setOficinaMedidaCautelar(
			    irr_calificacionRemote.cargarOficinaOrigenMedidaCautelar(
			        getTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
		iltc_tiposCausalesFiltro = null;
		setObservaciones(null);
		setComplementacionCausales(null);
		findTiposCausales();
		getIdTurnoHistoria();
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @param as_variable correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 */
	public String contar(String as_variable)
	{
		String ls_complementacion;
		String ls_observaciones;
		char[] lca_arrayChar;
		int    li_contador;
		String ls_result;

		ls_complementacion     = getComplementacionCausales();
		ls_observaciones       = getObservaciones();
		li_contador            = 0;
		ls_result              = "";

		if(StringUtils.isValidString(as_variable))
		{
			if(as_variable.equalsIgnoreCase(EstadoCommon.C))
			{
				if(ls_complementacion != null)
				{
					lca_arrayChar     = ls_complementacion.toCharArray();
					li_contador       = lca_arrayChar.length;
					ls_result         = Integer.toString(li_contador) + "/400";
				}
			}
			else
			{
				if(ls_observaciones != null)
				{
					lca_arrayChar     = ls_observaciones.toCharArray();
					li_contador       = lca_arrayChar.length;
					ls_result         = Integer.toString(li_contador) + "/4000";
				}
			}
		}

		return ls_result;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String detalleNotaDevolutiva()
	{
		String ls_return;
		String ls_idTurno;

		ls_return      = NavegacionCommon.NOTA_DEVOLUTIVA;
		ls_idTurno     = FacesUtils.getStringFacesParameter("idTurno");
		setTurno(ls_idTurno);
		setObservaciones(null);
		setRegistroParcial(false);
		setComplementacionCausales(null);
		setCausalesVinculacion(true);
		findTiposCausales();

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentosResidencia()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			OficinaOrigen loo_oficina;
			loo_oficina = getOficinaMedidaCautelar();

			if(loo_oficina != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(loo_oficina.getIdPais());

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipioResidencia()
	{
		Collection<Municipio> lcm_municipios;
		lcm_municipios = null;

		try
		{
			OficinaOrigen loo_oficina;
			loo_oficina = getOficinaMedidaCautelar();

			if(loo_oficina != null)
			{
				Municipio lm_parametros;
				lm_parametros = new Municipio();

				lm_parametros.setIdPais(loo_oficina.getIdPais());
				lm_parametros.setIdDepartamento(loo_oficina.getIdDepartamento());

				lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoCausal> findTiposCausales()
	{
		Collection<TipoCausal> lcta_cta;
		lcta_cta = new ArrayList<TipoCausal>();

		try
		{
			lcta_cta = irr_referenceRemote.findAllTiposCausalesActivos(
				    getIdTurnoHistoria(), getUserId(), getLocalIpAddress(), getRemoteIpAddress(), true, true
				);

			if(CollectionUtils.isValidCollection(lcta_cta))
			{
				iltc_tiposCausalesFiltro = new ArrayList<TipoCausal>();
				setDetalleTipoCausal(lcta_cta);

				for(TipoCausal lo_tc : lcta_cta)
					iltc_tiposCausalesFiltro.add(lo_tc);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcta_cta;
	}

	/**
	 * Generate file.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generateFile()
	    throws B2BException
	{
		TipoCausal ltc_notaDevolutiva;
		byte[]     lab_file;

		ltc_notaDevolutiva = new TipoCausal();

		try
		{
			if(isIndVinculado())
			{
				if(!StringUtils.isValidString(getTurno()))
					throw new B2BException(ErrorKeys.TURNO_INVALIDO);
			}
			else
			{
				if(StringUtils.isValidString(getIdTurnoHistoria()))
					ltc_notaDevolutiva.setIdTurnoHistoria(getIdTurnoHistoria());
				else
					throw new B2BException(ErrorKeys.TURNO_INVALIDO);
			}

			if(NumericUtils.isValidLong(getIdMotivo()))
				ltc_notaDevolutiva.setIdMotivo(getIdMotivo());
			else
				throw new B2BException("Sin Id Motivo.");

			Collection<TipoCausal> lltc_tipoCausal;

			lltc_tipoCausal = getTiposCausalesFiltro();

			if(CollectionUtils.isValidCollection(lltc_tipoCausal))
			{
				boolean lb_selecionado;
				boolean lb_avanzar;

				lb_selecionado     = false;
				lb_avanzar         = false;

				for(TipoCausal ltc_temp : lltc_tipoCausal)
				{
					if(ltc_temp.isSeleccionado())
					{
						lb_selecionado = true;

						break;
					}
				}

				if(lb_selecionado)
					lb_avanzar = true;
				else
				{
					String ls_complementacionCausales;

					ls_complementacionCausales = getComplementacionCausales();

					if(StringUtils.isValidString(ls_complementacionCausales))
						lb_avanzar = true;
					else
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_CAUSAL);
				}

				if(lb_avanzar)
				{
					ltc_notaDevolutiva.setListTiposCausales(lltc_tipoCausal);
					ltc_notaDevolutiva.setObservaciones(getComplementacionCausales());
					ltc_notaDevolutiva.setIndVinculacion(isIndVinculado());
					ltc_notaDevolutiva.setIdTurno(getTurno());

					RegistroParcialCalificacion lrpc_rpc;

					lrpc_rpc = getRegistroParcialCalificacion();

					if(lrpc_rpc == null)
						lrpc_rpc = new RegistroParcialCalificacion();

					lrpc_rpc.setRegistroParcial(isRegistroParcial());

					lab_file = irr_calificacionRemote.genereteFile(
						    ltc_notaDevolutiva, lrpc_rpc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lab_file != null)
					{
						InputStream lis_stream;
						lis_stream = new ByteArrayInputStream(lab_file);

						String ls_nombrePdf;

						ls_nombrePdf = (isRegistroParcial() ? ConstanteCommon.NOMBRE_ARCHIVO_NOTA_DEVOLUTIVA_PARCIAL
							                                : ConstanteCommon.NOMBRE_ARCHIVO_NOTA_DEVOLUTIVA)
							+ ExtensionCommon.PDF_PUNTO;

						setImagen(new DefaultStreamedContent(lis_stream, TipoContenidoCommon.PDF, ls_nombrePdf));
					}
					else
						throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
				}
			}

			ib_pdfGenerado = true;
			setPdfVinculacion(true);
			PrimeFaces.current().ajax().update("fNotaDevolutiva:opBtnGenerateFile");
		}
		catch(B2BException lb2be_e)
		{
			setImagen(null);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Generate file cancelacion.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generateFileCancelacion(boolean ab_NotaDev)
	    throws B2BException
	{
		byte[] lab_file;

		try
		{
			RegistroCalificacion lorc_rc;

			lorc_rc = new RegistroCalificacion();

			lorc_rc.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			lorc_rc.setUserId(getUserId());
			lorc_rc.setTurno(getTurno());

			if(isHabilitarMedidaCautelar())
			{
				if(isHabilitarCamposMedidaCautelar())
					throw new B2BException(ErrorKeys.ERROR_SIN_GUARDAR_PROCESO_MEDIDA_CAUTELAR);

				guardarCambiosOficinaOrigen();

				lorc_rc.setOficinaOrigenMedidaCautelar(getOficinaMedidaCautelar());
				lorc_rc.setNotaDevolutivaMedidaCautelar(true);
				lorc_rc.setReferencia(getReferencia());
				lorc_rc.setNumeroProceso(getNumeroProceso());
			}
			else
				lorc_rc.setNotaDevolutiva(true);

			if(ab_NotaDev)
			{
				lorc_rc.setNotaDevolutivaMedidaCautelar(false);
				lorc_rc.setNotaDevolutiva(true);
			}

			lab_file = irr_calificacionRemote.generateFileCancelacion(
				    lorc_rc, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lab_file != null)
			{
				InputStream lis_stream;
				lis_stream = new ByteArrayInputStream(lab_file);

				String ls_nombre;
				ls_nombre = ((!ab_NotaDev) ? ConstanteCommon.NOMBRE_ARCHIVO_COM_MEDIDA_CAUTELAR_NOTADEV
					                       : ConstanteCommon.NOMBRE_ARCHIVO_COM_CANCELACIONES_NOTADEV)
					+ ExtensionCommon.PDF_PUNTO;

				setImageCancelacion(new DefaultStreamedContent(lis_stream, TipoContenidoCommon.PDF, ls_nombre));
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		PrimeFaces.current().ajax().update(is_messageIdGrowl);
	}

	/**
	 * Guardar cambios para pdf medida cautelar.
	 */
	public void guardarCambiosParaPdfMedidaCautelar()
	{
		try
		{
			guardarCambiosOficinaOrigen();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		PrimeFaces.current().ajax().update(is_messageIdGrowl);
	}

	/**
	 * Guardar tipo numero proceso.
	 */
	public void guardarTipoNumeroProceso()
	{
		try
		{
			String       ls_referencia;
			String       ls_numeroProceso;
			boolean      lb_focus;
			FacesContext lfc_context;

			lb_focus        = true;
			lfc_context     = FacesContext.getCurrentInstance();

			ls_referencia        = getReferencia();
			ls_numeroProceso     = getNumeroProceso();

			lb_focus = validateStyles("fNotaDevolutiva:idItNumeroProceso", lfc_context, ls_numeroProceso, lb_focus);

			if(!StringUtils.isValidString(ls_numeroProceso))
				throw new B2BException(ErrorKeys.ERROR_SIN_NUMERO_PROCESO);

			irr_calificacionRemote.guardarTipoNumeroProceso(
			    getTurno(), ls_referencia, ls_numeroProceso, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
			setHabilitarCamposMedidaCautelar(false);

			addMessage(MessagesKeys.MODIFICACION_EXITOSA);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Limpiar.
	 * @throws B2BException
	 */
	public void limpiar()
	    throws B2BException
	{
		iltc_tiposCausalesFiltro = null;
		setComplementacionCausales(null);
		setObservaciones(null);
		setIdTurnoHistoria(null);
		setIdMotivo(null);
		setHabilitarMedidaCautelar(false);
		setHabilitarCamposMedidaCautelar(false);
		setReferencia(null);
		setNumeroProceso(null);
		setOficinaMedidaCautelar(null);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String revisionNotaDevolutiva()
	{
		String ls_return;
		ls_return = NavegacionCommon.CARGAR_NOTAS_DEVOLUTIVAS;

		try
		{
			String                           ls_idTurno;
			Collection<RegistroCalificacion> lcrc_crc;
			int                              li_revision;

			lcrc_crc        = getInfoTurnos();
			ls_idTurno      = getTurno();
			li_revision     = 0;

			if(!isPdfVinculacion())
				throw new B2BException(ErrorKeys.ERROR_GENERAR_ARCHIVO_NOTA_DEVOLUTIVA);

			if(StringUtils.isValidString(ls_idTurno))
			{
				setTurno(ls_idTurno);

				if(CollectionUtils.isValidCollection(lcrc_crc))
				{
					for(RegistroCalificacion lrc_tmp : lcrc_crc)
					{
						if(lrc_tmp != null)
						{
							if(StringUtils.getStringNotNull(lrc_tmp.getTurno()).equalsIgnoreCase(ls_idTurno))
								lrc_tmp.setTramite(EstadoCommon.ESTADO_ANOTACION_COMPLETO);

							if(
							    StringUtils.getStringNotNull(lrc_tmp.getTramite())
								               .equalsIgnoreCase(EstadoCommon.ESTADO_ANOTACION_INCOMPLETO)
							)
								li_revision++;
						}
					}

					if(li_revision == 0)
						setAprobacionVinculados(true);

					setPdfVinculacion(false);

					{
						TipoCausal ltc_tipoCausal;

						ltc_tipoCausal = new TipoCausal();
						ltc_tipoCausal.setIdTurno(ls_idTurno);
						ltc_tipoCausal.setCausalesVinculacion(true);
						ltc_tipoCausal.setListTiposCausales(getTiposCausalesFiltro());

						irr_calificacionRemote.notaDevolutiva(
						    ltc_tipoCausal, true, getObservaciones(), getUserId(), getLocalIpAddress(),
						    getRemoteIpAddress()
						);
					}

					setCausalesVinculacion(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ls_return = null;
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String saveCausales()
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		try
		{
			TipoCausal ltc_tipoCausal;
			boolean    lb_parcial;
			boolean    lb_causalesVinculacion;

			ltc_tipoCausal             = new TipoCausal();
			lb_parcial                 = isRegistroParcial();
			lb_causalesVinculacion     = isCausalesVinculacion();

			{
				boolean                lb_focus;
				FacesContext           lfc_context;
				Collection<TipoCausal> lltc_tipoCausal;
				String                 ls_form;

				lb_focus            = true;
				lfc_context         = FacesContext.getCurrentInstance();
				lltc_tipoCausal     = getTiposCausalesFiltro();
				ls_form             = ":fNotaDevolutiva";

				if(isIndVinculado())
					ls_form = ":fresumenNotasDevolutivas";

				if(CollectionUtils.isValidCollection(lltc_tipoCausal))
				{
					boolean              lb_selecionado;
					Iterator<TipoCausal> litc_iterator;

					lb_selecionado     = false;
					litc_iterator      = lltc_tipoCausal.iterator();

					while(litc_iterator.hasNext() && !lb_selecionado)
					{
						TipoCausal ltc_temp;

						ltc_temp = litc_iterator.next();

						if((ltc_temp != null) && ltc_temp.isSeleccionado())
							lb_selecionado = true;
					}

					if(!lb_selecionado)
					{
						String ls_complementacionCausales;

						ls_complementacionCausales = getComplementacionCausales();

						if(!StringUtils.isValidString(ls_complementacionCausales))
						{
							validateStyles(
							    ls_form + ":idITAComplementacion", lfc_context, ls_complementacionCausales, lb_focus
							);
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_CAUSAL);
						}
					}
				}
			}

			if(
			    !lb_causalesVinculacion && !isIndVinculado()
				    && !irr_calificacionRemote.archivoGenerado(getIdTurnoHistoria(), lb_parcial)
			)
			{
				B2BException lb2b = lb_parcial
					? new B2BException(ErrorKeys.ERROR_GENERAR_ARCHIVO_NOTA_DEVOLUTIVA_PARCIAL)
					: new B2BException(ErrorKeys.ERROR_GENERAR_ARCHIVO_NOTA_DEVOLUTIVA);

				throw lb2b;
			}

			if(!lb_causalesVinculacion)
			{
				if(isIndVinculado())
				{
					Collection<RegistroCalificacion> lcrc_crc;
					lcrc_crc = getInfoTurnos();

					if(!CollectionUtils.isValidCollection(lcrc_crc))
						throw new B2BException(ErrorKeys.ERROR_TURNOS_INVALIDOS);
					else
					{
						ltc_tipoCausal.setInfoTurnos(lcrc_crc);
						ltc_tipoCausal.setIndVinculacion(isIndVinculado());
					}
				}
				else
				{
					if(StringUtils.isValidString(getIdTurnoHistoria()))
						ltc_tipoCausal.setIdTurno(getIdTurnoHistoria());
					else
						throw new B2BException(ErrorKeys.TURNO_INVALIDO);
				}

				if(NumericUtils.isValidLong(getIdMotivo()))
					ltc_tipoCausal.setIdMotivo(getIdMotivo());
				else
					throw new B2BException("Sin Id Motivo.");
			}

			if(CollectionUtils.isValidCollection(getTiposCausalesFiltro()))
			{
				ltc_tipoCausal.setListTiposCausales(getTiposCausalesFiltro());
				ltc_tipoCausal.setObservaciones(getComplementacionCausales());
				ltc_tipoCausal.setUserId(getUserId());
				ltc_tipoCausal.setCancelacion(isPdfCancelacion());
				ltc_tipoCausal.setMedidaCautelar(isHabilitarMedidaCautelar());
				ltc_tipoCausal.setUserId(getUserId());
				ltc_tipoCausal.setCausalesVinculacion(isCausalesVinculacion());

				if(isHabilitarMedidaCautelar())
				{
					if(isHabilitarCamposMedidaCautelar())
						throw new B2BException(ErrorKeys.ERROR_SIN_GUARDAR_PROCESO_MEDIDA_CAUTELAR);

					guardarCambiosOficinaOrigen();

					ltc_tipoCausal.setOficinaOrigenMedidaCautelar(getOficinaMedidaCautelar());
					ltc_tipoCausal.setReferencia(getReferencia());
					ltc_tipoCausal.setNumeroProceso(getNumeroProceso());
				}

				irr_calificacionRemote.notaDevolutiva(
				    ltc_tipoCausal, isRegistroParcial(), getObservaciones(), getUserId(), getLocalIpAddress(),
				    getRemoteIpAddress()
				);

				if(!lb_causalesVinculacion)
				{
					BigDecimal lbd_etapaAnterior;
					lbd_etapaAnterior = irr_calificacionRemote.obtenerEtapaAnterior(
						    getIdTurnoHistoria(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(NumericUtils.isValidBigDecimal(lbd_etapaAnterior))
					{
						if(NumericUtils.getLong(lbd_etapaAnterior) == EtapaCommon.ID_ETAPA_REANOTACION)
						{
							TurnoHistoria lth_turnoHistoria;

							lth_turnoHistoria = new TurnoHistoria();

							lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
							irr_calificacionRemote.updateNotaDevolutiva(
							    lth_turnoHistoria, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);
						}
					}
				}
			}

			if(!lb_causalesVinculacion)
			{
				ls_return = NavegacionCommon.CALIFICACION;

				if(isRegistroParcial())
				{
					RegistroCalificacion        lorc_tmp;
					RegistroParcialCalificacion lrpc_rpc;

					lorc_tmp     = new RegistroCalificacion();
					lrpc_rpc     = getRegistroParcialCalificacion();

					if(lrpc_rpc != null)
					{
						FacesContext lfc_context;

						lorc_tmp.setRegistroParcialCalificacion(lrpc_rpc);
						lorc_tmp.setIdUsuarioModificacion(getUserId());
						lorc_tmp.setIpModificacion(getRemoteIpAddress());

						irr_calificacionRemote.eliminarAnotacion(lorc_tmp);

						lfc_context     = FacesUtils.getFacesContext();
						ls_return       = NavegacionCommon.REGISTRO_CALIFICACION;

						BeanRegistroCalificacion lbrc_bean;
						lbrc_bean       = (BeanRegistroCalificacion)lfc_context.getApplication()
								                                                   .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_REGISTRO_CALIFICACION,
								    BeanRegistroCalificacion.class
								);

						lbrc_bean.findMatriculas(false, true);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_NO_SE_PUDO_REALIZAR_PROCESO);
				}

				FacesContext     lfc_context;
				BeanCalificacion lbde_bean;
				lfc_context     = FacesUtils.getFacesContext();
				lbde_bean       = (BeanCalificacion)lfc_context.getApplication()
						                                           .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
						);

				if(lbde_bean != null)
				{
					lbde_bean.clear();
					lbde_bean.generarDatosTramiteCantidad();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			String ls_form;

			ls_return     = null;
			ls_form       = is_messageIdGrowl;

			addMessage(lb2be_e);

			if(isIndVinculado())
				ls_form = is_messageId;

			PrimeFaces.current().ajax().update(ls_form);
		}

		is_complementacionCausales = null;

		if(CollectionUtils.isValidCollection(iltc_tiposCausalesFiltro))
		{
			for(TipoCausal ltc_actual : iltc_tiposCausalesFiltro)
				ltc_actual.setSeleccionado(false);
		}

		PrimeFaces.current().ajax().update(is_messageIdGrowl);

		return ls_return;
	}

	/**
	 * Guardar cambios oficina origen.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void guardarCambiosOficinaOrigen()
	    throws B2BException
	{
		boolean          lb_focus;
		ExpresionRegular ler_validador;
		FacesContext     lfc_context;
		OficinaOrigen    loo_oficina;
		String           ls_correo;
		String           ls_direccion;
		String           ls_referencia;
		String           ls_numeroProceso;
		String           ls_telefono;
		String           ls_idCampoTelefono;
		String           ls_idCampoEmail;
		String           ls_idDireccion;

		loo_oficina = getOficinaMedidaCautelar();

		if(loo_oficina == null)
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

		lb_focus               = true;
		ler_validador          = ExpresionRegular.getExpresionRegular();
		lfc_context            = FacesContext.getCurrentInstance();
		ls_direccion           = loo_oficina.getDireccion();
		ls_referencia          = getReferencia();
		ls_numeroProceso       = getNumeroProceso();
		ls_correo              = loo_oficina.getCorreoElectronico();
		ls_telefono            = loo_oficina.getTelefono();
		ls_idCampoTelefono     = "fNofaDevolitiva:idITTelefonoOficina";
		ls_idCampoEmail        = "fNofaDevolitiva:idITEmailOficina";
		ls_idDireccion         = "fNofaDevolitiva:idITDireccionOficina";

		lb_focus = validateStyles("fNofaDevolitiva:idItNumeroProceso", lfc_context, ls_numeroProceso, lb_focus);

		if(!StringUtils.isValidString(ls_numeroProceso))
			throw new B2BException(ErrorKeys.ERROR_SIN_NUMERO_PROCESO);

		irr_calificacionRemote.guardarTipoNumeroProceso(
		    getTurno(), ls_referencia, ls_numeroProceso, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
		);

		lb_focus = validateStyles(ls_idDireccion, lfc_context, ls_direccion, lb_focus);

		if(!StringUtils.isValidString(ls_direccion))
			throw new B2BException(ErrorKeys.ERROR_SIN_DIRECCION_OFICINA_ORIGEN);

		if(!ler_validador.esDireccion(ls_direccion))
		{
			lb_focus = validateStyles(ls_idDireccion, lfc_context, "", lb_focus);

			throw new B2BException(ErrorKeys.ERROR_CARACTERES_ESPECIALES_MEDIDA_CAUTELAR);
		}
		else
			cleanInputText(ls_idDireccion, lfc_context);

		if(StringUtils.isValidString(ls_telefono) && !ler_validador.esTelefono(ls_telefono))
		{
			lb_focus = validateStyles(ls_idCampoTelefono, lfc_context, "", lb_focus);

			throw new B2BException(ErrorKeys.TELEFONO_FIJO_INVALIDO);
		}
		else
			cleanInputText(ls_idCampoTelefono, lfc_context);

		if(
		    StringUtils.isValidString(ls_correo)
			    && !ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_correo)
		)
		{
			lb_focus = validateStyles(ls_idCampoEmail, lfc_context, IdentificadoresCommon.DATO_INVALIDO, lb_focus);

			InputText lit_campoCorreo;

			lit_campoCorreo = (InputText)FacesContext.getCurrentInstance().getViewRoot().findComponent(ls_idCampoEmail);

			if(lit_campoCorreo != null)
				lit_campoCorreo.setStyle("border-color: red;");

			PrimeFaces.current().ajax().update(ls_idCampoEmail);

			throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
		}

		else
			cleanInputText(ls_idCampoEmail, lfc_context);

		setHabilitarCamposMedidaCautelar(false);

		addMessage(MessagesKeys.MODIFICACION_EXITOSA);
	}
}
