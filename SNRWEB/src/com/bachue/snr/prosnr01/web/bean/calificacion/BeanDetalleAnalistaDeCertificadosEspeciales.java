package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.certificados.CertificadosRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.ConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacionDocumento;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.certificados.Certificados;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.extensions.model.layout.LayoutOptions;

import org.primefaces.model.DefaultStreamedContent;

import java.io.Serializable;

import java.util.Collection;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Analista de certificados especiales.
 *
 * @author Jorge Patiño
 */
@SessionScoped
@ManagedBean(name = "beanDetalleAnalistaDeCertificadosEspeciales")
public class BeanDetalleAnalistaDeCertificadosEspeciales extends BeanAntSistemaCalificacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4106034154739782629L;

	/** Constante is_idForm. */
	public static final String is_idForm = "fCertificados";

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad icr certificados remote. */
	@EJB
	private CertificadosRemote icr_certificadosRemote;

	/** Propiedad icmt motivos. */
	private Collection<MotivoTramite> icmt_motivos;

	/** Propiedad idsc imagen documento. */
	private DefaultStreamedContent idsc_imagenDocumento;

	/** Propiedad llo layout options. */
	private LayoutOptions llo_layoutOptions;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/** Propiedad is motivo tramite. */
	private String is_motivoTramite;

	/** Propiedad is observacionesEnvioAntSistema. */
	private String is_observacionesEnvioAntSistema;

	/** Propiedad is texto dialog. */
	private String is_textoDialog;

	/** Propiedad is texto tag pantalla. */
	private String is_textoTagPantalla;

	/** Propiedad ib certificado con tag pantalla. */
	private boolean ib_certificadoConTagPantalla;

	/** Propiedad ib certificado con varias plantillas. */
	private boolean ib_certificadoConVariasPlantillas;

	/** Propiedad ib confrontacion. */
	private boolean ib_confrontacion;

	/** Propiedad ib elimina matricula. */
	private boolean ib_eliminaMatricula;

	/** Propiedad ib inserta matricula. */
	private boolean ib_insertaMatricula;

	/** Propiedad ib mostrar boton. */
	private boolean ib_mostrarBoton;

	/** Propiedad ii menu. */
	private int ii_menu;

	/**
	 * Modifica el valor de certificado con tag pantalla.
	 *
	 * @param ab_b asigna el valor a la propiedad certificado con tag pantalla
	 */
	public void setCertificadoConTagPantalla(boolean ab_b)
	{
		ib_certificadoConTagPantalla = ab_b;
	}

	/**
	 * Valida la propiedad certificado con tag pantalla.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en certificado con tag pantalla
	 */
	public boolean isCertificadoConTagPantalla()
	{
		return ib_certificadoConTagPantalla;
	}

	/**
	 * Modifica el valor de certificado con varias plantillas.
	 *
	 * @param ab_b asigna el valor a la propiedad certificado con varias plantillas
	 */
	public void setCertificadoConVariasPlantillas(boolean ab_b)
	{
		ib_certificadoConVariasPlantillas = ab_b;
	}

	/**
	 * Valida la propiedad certificado con varias plantillas.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en certificado con varias plantillas
	 */
	public boolean isCertificadoConVariasPlantillas()
	{
		return ib_certificadoConVariasPlantillas;
	}

	/**
	 * Modifica el valor de confrontacion.
	 *
	 * @param ab_b asigna el valor a la propiedad confrontacion
	 */
	public void setConfrontacion(boolean ab_b)
	{
		ib_confrontacion = ab_b;
	}

	/**
	 * Valida la propiedad confrontacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en confrontacion
	 */
	public boolean isConfrontacion()
	{
		return ib_confrontacion;
	}

	/**
	 * Modifica el valor de elimina matricula.
	 *
	 * @param ab_b asigna el valor a la propiedad elimina matricula
	 */
	public void setEliminaMatricula(boolean ab_b)
	{
		ib_eliminaMatricula = ab_b;
	}

	/**
	 * Valida la propiedad elimina matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en elimina matricula
	 */
	public boolean isEliminaMatricula()
	{
		return ib_eliminaMatricula;
	}

	/**
	 * Modifica el valor de imagen documento.
	 *
	 * @param adsc_dsc asigna el valor a la propiedad imagen documento
	 */
	public void setImagenDocumento(DefaultStreamedContent adsc_dsc)
	{
		idsc_imagenDocumento = adsc_dsc;
	}

	/**
	 * Retorna el valor de imagen documento.
	 *
	 * @return el valor de imagen documento
	 */
	public DefaultStreamedContent getImagenDocumento()
	{
		return idsc_imagenDocumento;
	}

	/**
	 * Modifica el valor de inserta matricula.
	 *
	 * @param ab_b asigna el valor a la propiedad inserta matricula
	 */
	public void setInsertaMatricula(boolean ab_b)
	{
		ib_insertaMatricula = ab_b;
	}

	/**
	 * Valida la propiedad inserta matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en inserta matricula
	 */
	public boolean isInsertaMatricula()
	{
		return ib_insertaMatricula;
	}

	/**
	 * Modifica el valor de layout options.
	 *
	 * @param alo_lo asigna el valor a la propiedad layout options
	 */
	public void setLayoutOptions(LayoutOptions alo_lo)
	{
		llo_layoutOptions = alo_lo;
	}

	/**
	 * Retorna el valor de layout options.
	 *
	 * @return el valor de layout options
	 */
	public LayoutOptions getLayoutOptions()
	{
		return llo_layoutOptions;
	}

	/**
	 * Modifica el valor de menu.
	 *
	 * @param ai_i asigna el valor a la propiedad menu
	 */
	public void setMenu(int ai_i)
	{
		ii_menu = ai_i;
	}

	/**
	 * Retorna el valor de menu.
	 *
	 * @return el valor de menu
	 */
	public int getMenu()
	{
		return ii_menu;
	}

	/**
	 * Modifica el valor de mostrar boton.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar boton
	 */
	public void setMostrarBoton(boolean ab_b)
	{
		ib_mostrarBoton = ab_b;
	}

	/**
	 * Valida la propiedad mostrar boton.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar boton
	 */
	public boolean isMostrarBoton()
	{
		return ib_mostrarBoton;
	}

	/**
	 * Modifica el valor de motivo tramite.
	 *
	 * @param as_s asigna el valor a la propiedad motivo tramite
	 */
	public void setMotivoTramite(String as_s)
	{
		is_motivoTramite = as_s;
	}

	/**
	 * Retorna el valor de motivo tramite.
	 *
	 * @return el valor de motivo tramite
	 */
	public String getMotivoTramite()
	{
		return is_motivoTramite;
	}

	/**
	 * Modifica el valor de motivos.
	 *
	 * @param acmt_cmt asigna el valor a la propiedad motivos
	 */
	public void setMotivos(Collection<MotivoTramite> acmt_cmt)
	{
		icmt_motivos = acmt_cmt;
	}

	/**
	 * Retorna el valor de motivos.
	 *
	 * @return el valor de motivos
	 */
	public Collection<MotivoTramite> getMotivos()
	{
		return icmt_motivos;
	}

	/**
	 * Retorna el valor de motivos tramite.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void getMotivosTramite()
	    throws B2BException
	{
		setMotivos(
		    irr_referenceRemote.findMotivosByEtapa(
		        StringUtils.getString(getIdEtapa()), getIdTurnoHistoria(), false, false
		    )
		);
	}

	/**
	 * Modifica el valor de observaciones envio ant sistema.
	 *
	 * @param as_s asigna el valor de observaciones envio ant sistema
	 */
	public void setObservacionesEnvioAntSistema(String as_s)
	{
		is_observacionesEnvioAntSistema = as_s;
	}

	/**
	 * Retorna el valor de observaciones envio ant sistema.
	 *
	 * @return el valor de observaciones envio ant sistema
	 */
	public String getObservacionesEnvioAntSistema()
	{
		return is_observacionesEnvioAntSistema;
	}

	/**
	 * Modifica el valor de solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad solicitud
	 */
	public void setSolicitud(Solicitud as_s)
	{
		is_solicitud = as_s;
	}

	/**
	 * Retorna el valor de solicitud.
	 *
	 * @return el valor de solicitud
	 */
	public Solicitud getSolicitud()
	{
		return is_solicitud;
	}

	/**
	 * Modifica el valor de texto dialog.
	 *
	 * @param as_s asigna el valor a la propiedad texto dialog
	 */
	public void setTextoDialog(String as_s)
	{
		is_textoDialog = as_s;
	}

	/**
	 * Retorna el valor de texto dialog.
	 *
	 * @return el valor de texto dialog
	 */
	public String getTextoDialog()
	{
		return is_textoDialog;
	}

	/**
	 * Modifica el valor de texto tag pantalla.
	 *
	 * @param as_s asigna el valor a la propiedad texto tag pantalla
	 */
	public void setTextoTagPantalla(String as_s)
	{
		is_textoTagPantalla = as_s;
	}

	/**
	 * Retorna el valor de texto tag pantalla.
	 *
	 * @return el valor de texto tag pantalla
	 */
	public String getTextoTagPantalla()
	{
		return is_textoTagPantalla;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ab_definitivo correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 */
	public String accionCertificados(boolean ab_definitivo)
	{
		Certificados lc_certificado;
		String       ls_return;

		lc_certificado     = getCertificado();
		ls_return          = null;

		try
		{
			if(lc_certificado != null)
			{
				if(StringUtils.isValidString(lc_certificado.getTagResuelvePantalla()))
				{
					if(!StringUtils.isValidString(lc_certificado.getTextoTag()))
						throw new B2BException(ErrorKeys.ERROR_CAMPOS);

					generarDocumento(ab_definitivo);
					setMostrarBoton(true);

					if(ab_definitivo)
						ls_return = enviarAEtapaPosterior(MotivoTramiteCommon.GENERACION_CERTIFICADO_ESPECIAL);
					else
						ls_return = NavegacionCommon.GENERA_CERTIFICADOS_ESPECIALES;
				}
				else
					ls_return = accionCertificadosSinTag();
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionCertificados()
	{
		return accionCertificados(false);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String accionCertificadosSinTag()
	    throws B2BException
	{
		return enviarAEtapaPosterior(MotivoTramiteCommon.GENERACION_CERTIFICADO_ESPECIAL);
	}

	/** {@inheritdoc} */
	public String accionVolver()
	{
		return accionVolver(false);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ab_generacion correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 */
	public String accionVolver(boolean ab_generacion)
	{
		String ls_return;

		ls_return = NavegacionCommon.DETALLE_TURNO_ANALISTA_CERTIFICADOS;

		try
		{
			if(ab_generacion)
			{
				setImagenDocumento(null);

				ls_return = NavegacionCommon.CERTIFICADOS_ESPECIALES;
			}

			else
			{
				FacesContext                         lfc_context;
				BeanAnalistaDeCertificadosEspeciales lbadce_badce;

				lfc_context      = FacesUtils.getFacesContext();
				lbadce_badce     = (BeanAnalistaDeCertificadosEspeciales)lfc_context.getApplication()
						                                                                .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ANALISTA_DE_CERTIFICADOS_ESPECIALES,
						    BeanAnalistaDeCertificadosEspeciales.class
						);

				if(lbadce_badce != null)
					lbadce_badce.generarDataDetalle();

				clear();
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Método para actualizar el círculo cuando se selecciona tipo de búsqueda antiguo sistema.
	 *
	 * @param as_idTurno Parámetro tipo String con el turno a consultar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarCirculoParaBusqueda(String as_idTurno)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idTurno))
		{
			String ls_tipoConsulta;

			ls_tipoConsulta = getTipoConsulta();

			if(StringUtils.isValidString(ls_tipoConsulta) && ls_tipoConsulta.equalsIgnoreCase(EstadoCommon.D))
			{
				Turno lt_turno;

				lt_turno = icr_certificadosRemote.consultarTurno(
					    as_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lt_turno != null)
				{
					String ls_idCirculoTurno;

					ls_idCirculoTurno = lt_turno.getIdCirculo();

					if(StringUtils.isValidString(ls_idCirculoTurno))
					{
						ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosCalificacionDocumento;
						ConsultaDatosDocumento                 lcdd_consultaDatosDocumento;

						lcccd_consultaCriteriosCalificacionDocumento     = getConsultaCriteriosCalificacionDocumento();
						lcdd_consultaDatosDocumento                      = getConsultaDatosDocumento();

						if(lcccd_consultaCriteriosCalificacionDocumento != null)
						{
							AnotacionPredio lap_anotacionPredio;

							lap_anotacionPredio = lcccd_consultaCriteriosCalificacionDocumento.getAnotacionPredio();

							if(lap_anotacionPredio != null)
								lap_anotacionPredio.setIdCirculo(ls_idCirculoTurno);
						}

						if(lcdd_consultaDatosDocumento != null)
						{
							CirculoRegistral lcr_circuloregistral;

							lcr_circuloregistral = lcdd_consultaDatosDocumento.getCirculoRegistral();

							if(lcr_circuloregistral != null)
								lcr_circuloregistral.setIdCirculo(ls_idCirculoTurno);
						}
					}
				}
			}
		}
	}

	/**
	 * Calcula texto dialog.
	 */
	public void calculaTextoDialog()
	{
		String ls_motivo;

		ls_motivo = getMotivoTramite();

		if(StringUtils.isValidString(ls_motivo))
		{
			String ls_texto;

			ls_texto = ls_motivo.equalsIgnoreCase(
				    StringUtils.getString(MotivoTramiteCommon.GENERACION_CERTIFICADO_ESPECIAL)
				) ? MessagesKeys.CONFIRMACION_GENERAR_DOCUMENTOS
				  : (ls_motivo.equalsIgnoreCase(
				    StringUtils.getString(MotivoTramiteCommon.ANTIGUO_SISTEMA_CERTIFICADOS)
				) ? MessagesKeys.CONFIRMACION_ANTIGUO_SISTEMA
				  : (ls_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.MOD_MATRICULAS))
				? MessagesKeys.CONFIRMACION_MODIFICACION_MATRICULAS
				: (ls_motivo.equalsIgnoreCase(
				    StringUtils.getString(MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CERTIFICADOS)
				) ? MessagesKeys.CONFIRMACION_NEGAR_SOLICITUD_DE_CERTIFICADOS : null)));
			setTextoDialog(getMessages().getString(ls_texto));
		}
	}

	/** {@inheritdoc} */
	public void clear()
	{
		setMotivoTramite(null);
		setImagen(null);
		setTextoDialog(null);
		setCertificadoConVariasPlantillas(false);
		setCertificadoConTagPantalla(false);
		setTextoTagPantalla(null);
		setMostrarBoton(false);
		setObservacionesEnvioAntSistema(null);
		setConsultaRealizada(false);
		super.clear();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param al_motivo correspondiente al valor del tipo de objeto long
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String enviarAEtapaPosterior(long al_motivo)
	    throws B2BException
	{
		TurnoHistoria lth_turnoHistoria;
		String        ls_usuarioModificacion;
		String        ls_ipModificacion;

		lth_turnoHistoria          = new TurnoHistoria();
		ls_usuarioModificacion     = getUserId();
		ls_ipModificacion          = getRemoteIpAddress();

		lth_turnoHistoria.setIdTurno(getIdTurno());
		lth_turnoHistoria.setObservaciones(getObservacionesEnvioAntSistema());
		lth_turnoHistoria.setObservacionesNoTramite(getObservaciones());
		lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(al_motivo));
		lth_turnoHistoria.setIdUsuarioModificacion(ls_usuarioModificacion);
		lth_turnoHistoria.setIpModificacion(ls_ipModificacion);

		icr_certificadosRemote.enviarAEtapaPosterior(
		    lth_turnoHistoria, ls_usuarioModificacion, getLocalIpAddress(), ls_ipModificacion
		);

		{
			FacesContext                         lfc_context;
			BeanAnalistaDeCertificadosEspeciales lbadce_badce;

			lfc_context      = FacesUtils.getFacesContext();
			lbadce_badce     = (BeanAnalistaDeCertificadosEspeciales)lfc_context.getApplication()
					                                                                .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_ANALISTA_DE_CERTIFICADOS_ESPECIALES,
					    BeanAnalistaDeCertificadosEspeciales.class
					);

			if(lbadce_badce != null)
			{
				lbadce_badce.clear();
				lbadce_badce.generarDatosBandeja();
			}

			clear();
		}

		return NavegacionCommon.ANALISTA_DE_CERTIFICADOS_ESPECIALES;
	}

	/**
	 * Generar data.
	 */
	public void generarData(Long al_idTurnoHistoria)
	{
		Solicitud ls_solicitud;

		ls_solicitud = getSolicitud();

		if(ls_solicitud != null)
		{
			try
			{
				Certificados lc_certificado;

				lc_certificado = new Certificados();

				lc_certificado.setSolicitud(ls_solicitud);
				lc_certificado.setIdTurnoHistoria(al_idTurnoHistoria);

				lc_certificado = icr_certificadosRemote.consultarDatosCertificadoEspecial(
					    lc_certificado, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lc_certificado != null)
				{
					BeanDireccion lbd_beanDireccion;

					lbd_beanDireccion = getBeanDireccion();

					lbd_beanDireccion.limpiarBeanDireccion();
					lbd_beanDireccion.setForm(is_idForm);
					lbd_beanDireccion.setDireccionCertificado(lc_certificado.getDireccion());
					lbd_beanDireccion.setBloquearCamposCertificados(true);

					setCertificadoConVariasPlantillas(
					    CollectionUtils.isValidCollection(lc_certificado.getPlantillasPorCertificado())
					);
				}

				setCertificado(lc_certificado);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}
	}

	/**
	 * Generar documento.
	 *
	 * @param ab_definitivo correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDocumento(boolean ab_definitivo)
	    throws B2BException
	{
		Certificados lc_certificado;

		lc_certificado = getCertificado();

		if(lc_certificado != null)
		{
			String ls_texto;

			ls_texto = lc_certificado.getTextoTag();
			lc_certificado.setTextoTag(convertirTextoParaRtf(ls_texto));

			lc_certificado = icr_certificadosRemote.generarCertificadosEspeciales(
				    lc_certificado, true, ab_definitivo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lc_certificado != null)
			{
				setImagenDocumento(
				    generarArchivosDescarga(
				        lc_certificado.getDocumento(), TipoContenidoCommon.PDF,
				        lc_certificado.getNombreDocumento() + ExtensionCommon.PDF_PUNTO
				    )
				);

				{
					Certificados lc_certificadoPantalla;

					lc_certificadoPantalla = getCertificado();

					if(lc_certificadoPantalla != null)
						lc_certificadoPantalla.setTextoTag(lc_certificado.getTextoTag());
				}
			}
		}
	}

	/**
	 * Método de obtención del turnoHistoria anterior
	 * @param as_aTurno con el turno actual de la solicitud
	 * @return Un TurnoHistoria con la solicitud del dato
	 * @throws B2BException
	 */
	public TurnoHistoria obtenerAnteriorTurnoHistoria(String as_aTurno)
	    throws B2BException
	{
		TurnoHistoria lh_turnoHistoria;
		lh_turnoHistoria = new TurnoHistoria();

		try
		{
			lh_turnoHistoria.setIdTurno(as_aTurno);

			lh_turnoHistoria = irr_referenceRemote.obtenerAnteriorTurnoHistoria(lh_turnoHistoria);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lh_turnoHistoria;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String seleccionaFlujoProceso()
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		try
		{
			String       ls_motivo;
			Certificados lc_certificado;

			ls_motivo          = getMotivoTramite();
			lc_certificado     = getCertificado();

			if(lc_certificado != null)
			{
				String ls_turno;
				ls_turno = getIdTurno();

				if(StringUtils.isValidString(ls_turno))
				{
					TurnoHistoria lh_turnoHistoria;

					lh_turnoHistoria     = new TurnoHistoria();

					lh_turnoHistoria = obtenerAnteriorTurnoHistoria(ls_turno);

					long ll_etapaTH;

					ll_etapaTH = NumericUtils.getLong(lh_turnoHistoria.getIdEtapa());

					Subproceso ls_subproceso;
					ls_subproceso = lc_certificado.getSubProceso();

					if(ls_subproceso != null)
					{
						String ls_idSubproceso;
						ls_idSubproceso = ls_subproceso.getIdSubproceso();

						if(
						    (ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_12)
							    || ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_14))
							    && !isConsultaRealizada() && (ll_etapaTH != 110)
						)
							throw new B2BException(ErrorKeys.DEBE_REALIZAR_MINIMO_UNA_BUSQUEDA);
					}
				}
			}

			if(StringUtils.isValidString(ls_motivo))
			{
				if(lc_certificado != null)
				{
					long ll_motivo;

					ll_motivo = NumericUtils.getLong(ls_motivo);

					if(ll_motivo == MotivoTramiteCommon.GENERACION_CERTIFICADO_ESPECIAL)
					{
						if(isCertificadoConVariasPlantillas())
						{
							if(!StringUtils.isValidString(lc_certificado.getPlantillaSeleccionada()))
								throw new B2BException(ErrorKeys.ERROR_PLANTILLA);
						}

						String ls_ip;

						ls_ip = getRemoteIpAddress();

						lc_certificado.setIpModificacion(ls_ip);
						lc_certificado.setIpCreacion(ls_ip);
						lc_certificado.setIdUsuarioCreacion(getUserId());
						lc_certificado.setIdUsuarioModificacion(getUserId());
						lc_certificado.setIdTurno(getIdTurno());
						lc_certificado.setSubProceso(lc_certificado.getSubProceso());
						lc_certificado.setSolicitud(lc_certificado.getSolicitud());
						lc_certificado.setPlantillaSeleccionada(lc_certificado.getPlantillaSeleccionada());

						lc_certificado = icr_certificadosRemote.generarCertificadosEspeciales(
							    lc_certificado, false, false, getUserId(), getLocalIpAddress(), ls_ip
							);

						if(lc_certificado != null)
						{
							String ls_tagPantalla;

							ls_tagPantalla = lc_certificado.getTagResuelvePantalla();

							setCertificadoConTagPantalla(StringUtils.isValidString(ls_tagPantalla));
							setMostrarBoton(!isCertificadoConTagPantalla());
							setTextoTagPantalla(ls_tagPantalla);
							setImagenDocumento(
							    generarArchivosDescarga(
							        lc_certificado.getDocumento(), TipoContenidoCommon.PDF,
							        lc_certificado.getNombreDocumento() + ExtensionCommon.PDF_PUNTO
							    )
							);

							{
								Certificados lc_certificadoPantalla;

								lc_certificadoPantalla = getCertificado();

								if(lc_certificadoPantalla != null)
								{
									lc_certificadoPantalla.setTextoTag(lc_certificado.getTextoTag());
									lc_certificadoPantalla.setTagResuelvePantalla(
									    lc_certificado.getTagResuelvePantalla()
									);
								}
							}
						}

						ls_return = NavegacionCommon.GENERA_CERTIFICADOS_ESPECIALES;
					}
					else if(ll_motivo == MotivoTramiteCommon.ANTIGUO_SISTEMA_CERTIFICADOS)
					{
						String ls_observacionesAntSistema;
						String ls_observaciones;

						ls_observacionesAntSistema     = getObservacionesEnvioAntSistema();
						ls_observaciones               = getObservaciones();

						if(!StringUtils.isValidString(ls_observaciones))
							throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES_ANALISTA_DATOS_AS);

						if(!StringUtils.isValidString(ls_observacionesAntSistema))
							throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);

						ls_return = enviarAEtapaPosterior(ll_motivo);
					}
					else if(ll_motivo == MotivoTramiteCommon.MOD_MATRICULAS)
					{
						if((!isInsertaMatricula()) && (!isEliminaMatricula()))
						{
							setConfrontacion(true);
							ls_return = null;

							setMenu((getMenu() + 1));

							if(getMenu() > 2)
								throw new B2BException(ErrorKeys.ERROR_CONFRONTACION_ACCION);
						}
						else
						{
							FacesContext          lfc_context;
							BeanDetalleCorreccion lbdc_bean;

							lfc_context     = FacesUtils.getFacesContext();
							lbdc_bean       = (BeanDetalleCorreccion)lfc_context.getApplication()
									                                                .evaluateExpressionGet(
									    lfc_context, BeanNameCommon.BEAN_DETALLE_CORRECCION, BeanDetalleCorreccion.class
									);

							if(lbdc_bean != null)
							{
								lbdc_bean.clear();
								lbdc_bean.validarActoEjecutoria();
								lbdc_bean.setIdTurnoHistoria(getIdTurnoHistoria());
								lbdc_bean.setInsertaMatricula(isInsertaMatricula());
								lbdc_bean.setEliminaMatricula(isEliminaMatricula());
								lbdc_bean.setMotivoTramite(getMotivoTramite());
								lbdc_bean.setIdTurnoHistoria(getIdTurnoHistoria());
								lbdc_bean.setMatriculas(
								    irr_calificacionRemote.findPredioDocumentoByTurno(
								        getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.MATRICULAS
								    )
								);

								{
									RegistroCalificacion lrc_rc;
									lrc_rc = new RegistroCalificacion();

									lrc_rc.setTurno(getIdTurno());
									lrc_rc.setIdEtapa(NumericUtils.getLong(getIdEtapa()));

									lrc_rc = irr_calificacionRemote.turnosVinculados(
										    lrc_rc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
										);

									if(lrc_rc != null)
										lbdc_bean.setInfoTurnos(lrc_rc.getAllMatriculas());
								}

								lbdc_bean.findObservacionesByIdTurnoHistoria(getIdTurnoHistoria());
								lbdc_bean.setEsCertificadosEspeciales(true);

								BeanPredioDocumentoActo lbpdab_bean;
								lbpdab_bean = (BeanPredioDocumentoActo)FacesUtils.obtenerInstanciaBean(
									    BeanPredioDocumentoActo.class, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO
									);

								if(lbpdab_bean != null)
								{
									lbpdab_bean.limpiarDatos();
									lbpdab_bean.setIdTurnoHistoria(getIdTurnoHistoria());
									lbpdab_bean.setIdTurno(getIdTurno());
									lbpdab_bean.setConfrontacion(true);
									lbpdab_bean.setIdEtapa(getIdEtapa());
									lbpdab_bean.setMotivoTramite(getMotivoTramite());
								}

								ls_return = NavegacionCommon.DETALLE_CORRECCION;
							}

							setConfrontacion(false);
							setMotivoTramite(null);
						}
					}
					else if(ll_motivo == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CERTIFICADOS)
					{
						TurnoHistoria lth_th;

						lth_th = new TurnoHistoria();

						lth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

						lth_th = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_th);

						if(lth_th != null)
						{
							TurnoHistoria lth_tmpAnt;
							String        ls_proceso;

							ls_proceso     = null;
							lth_tmpAnt     = new TurnoHistoria();

							lth_tmpAnt.setIdTurnoHistoria(NumericUtils.getLongWrapper(lth_th.getIdAnterior()));

							lth_tmpAnt = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_tmpAnt);

							if(lth_tmpAnt != null)
							{
								SolicitudAsociada lsa_solicitudAsociada;
								Solicitud         ls_solicitud;

								ls_solicitud = new Solicitud();

								ls_solicitud.setIdSolicitud(lth_tmpAnt.getIdSolicitud());

								lsa_solicitudAsociada = irr_registroRemote.findSolicitudAsociada(ls_solicitud);

								if(lsa_solicitudAsociada != null)
								{
									Solicitud ls_solicitud2;

									ls_solicitud2 = new Solicitud();

									ls_solicitud2.setIdSolicitud(lsa_solicitudAsociada.getIdSolicitud1());
									ls_solicitud2.setDocumento(false);

									ls_solicitud2 = irr_registroRemote.findSolicitudById(ls_solicitud2, getUserId());

									if(ls_solicitud2 != null)
										ls_proceso = ls_solicitud2.getIdProceso();
								}
								else
								{
									ls_solicitud = irr_registroRemote.findSolicitudById(ls_solicitud, getUserId());

									if(ls_solicitud != null)
										ls_proceso = ls_solicitud.getIdProceso();
								}

								if(StringUtils.isValidString(ls_proceso))
								{
									BeanRecepcionDocumentos lbsdt_beanRecepcionDocumentos;
									FacesContext            lfc_context;

									lfc_context                       = FacesUtils.getFacesContext();
									lbsdt_beanRecepcionDocumentos     = (BeanRecepcionDocumentos)lfc_context.getApplication()
											                                                                    .evaluateExpressionGet(
											    lfc_context, BeanNameCommon.BEAN_RECEPCION_DOCUMENTOS,
											    BeanRecepcionDocumentos.class
											);

									if(lbsdt_beanRecepcionDocumentos != null)
									{
										lbsdt_beanRecepcionDocumentos.clear(true);
										lbsdt_beanRecepcionDocumentos.setTurnoHistoria(lth_th);
										lbsdt_beanRecepcionDocumentos.setParametros(null);
										lbsdt_beanRecepcionDocumentos.setDocumentoGenerado(false);
										lbsdt_beanRecepcionDocumentos.setEtapa(
										    EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES
										);
										lbsdt_beanRecepcionDocumentos.setEsNegarSolicitudCertificados(true);
										lbsdt_beanRecepcionDocumentos.setIdTurno(getIdTurno());
										ls_return = NavegacionCommon.RECEPCION_DOCUMENTOS;
									}

									BeanPredioDocumentoActo lbpdab_bean;
									lbpdab_bean = (BeanPredioDocumentoActo)FacesUtils.obtenerInstanciaBean(
										    BeanPredioDocumentoActo.class, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO
										);

									if(lbpdab_bean != null)
									{
										lbpdab_bean.limpiarDatos();
										lbpdab_bean.setIdTurnoHistoria(getIdTurnoHistoria());
										lbpdab_bean.setIdTurno(getIdTurno());
										lbpdab_bean.setConfrontacion(true);
										lbpdab_bean.setIdEtapa(getIdEtapa());
										lbpdab_bean.setMotivoTramite(getMotivoTramite());
									}
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
					}
					else
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACCION);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACCION);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Método para seleccionar opcion de desasociar matricula
	 */
	public void seleccionoInsertarMatriculaCert()
	{
		if(isInsertaMatricula())
			setEliminaMatricula(true);
	}

	/**
	 * Initialize.
	 */
	@PostConstruct
	protected void initialize()
	{
		LayoutOptions llo_layout;
		LayoutOptions llo_paneles;

		llo_layout      = new LayoutOptions();
		llo_paneles     = new LayoutOptions();

		llo_paneles.addOption("slidable", new Boolean(false));
		llo_paneles.addOption("resizeWhileDragging", new Boolean(true));
		llo_layout.setPanesOptions(llo_paneles);

		LayoutOptions llo_layoutSecundario = new LayoutOptions();
		llo_layoutSecundario.addOption("size", NumericUtils.getInteger(500));
		llo_layoutSecundario.addOption("minSize", NumericUtils.getInteger(300));
		llo_layout.setWestOptions(llo_layoutSecundario);
		setLayoutOptions(llo_layout);
	}
}
