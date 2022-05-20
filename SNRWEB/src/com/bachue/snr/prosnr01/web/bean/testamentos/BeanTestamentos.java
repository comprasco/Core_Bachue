package com.bachue.snr.prosnr01.web.bean.testamentos;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.trazabilidad.ConsultaTrazabilidadRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.testamentos.TestamentosRemote;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.registro.ReproduccionConstanciaTestamento;
import com.bachue.snr.prosnr01.model.registro.SolicitudTestamento;
import com.bachue.snr.prosnr01.model.sdb.acc.Testamento;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroTestamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanConfrontacion;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanNotaDevolutiva;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanPredioDocumentoActo;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanTurnos;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.PrimeFaces.Ajax;

import org.primefaces.event.ItemSelectEvent;

import org.primefaces.model.DefaultStreamedContent;

import org.primefaces.model.chart.PieChartModel;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todas las propiedades y acciones de BeanTestamentos
 * @author dbeltran
 *
 */
@SessionScoped
@ManagedBean(name = "beanTestamentos")
public class BeanTestamentos extends BeanTurnos implements Serializable
{
	/**
	 * Constante serialVersionUID
	 */
	private static final long serialVersionUID = 562517161589188626L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanTestamentos.class);

	/**Propiedad irr Calificacion Remote*/
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/**Propiedad ic_ tipo oficina documento*/
	private Collection<TipoOficina> ic_tipoOficinaDocumento;

	/**Propiedad icmt motivos*/
	private Collection<MotivoTramite> icmt_motivos;

	/** Propiedad ictc datos tramite cantidad. */
	private Collection<TramiteCantidad> ictc_datosTramiteCantidad;

	/** Propiedad ictr_consulta Trazabilidad Remote*/
	@EJB
	private ConsultaTrazabilidadRemote ictr_consultaTrazabilidadRemote;

	/** Propiedad idsc documento formulario correccion. */
	private DefaultStreamedContent idsc_constanciaReproduccionTestamento;

	/** Propiedad idsc documento formulario correccion. */
	private DefaultStreamedContent idsc_constanciaTestamento;

	/**Propiedad id_ bgn Documento */
	private Documento id_bgnDocumento;

	/**Propiedad ipr parameter remote*/
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad itr testamentos Remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/**Propiedad data reproduccion constancia*/
	private ReproduccionConstanciaTestamento irct_dataReproduccionConstancia;

	/**Propiedad Solicitud Testamento*/
	private SolicitudTestamento ist_solicitudTestamento;

	/**Propiedad Cantidad Copias Reproducir*/
	private String is_cantidadCopiasReproducir;

	/**Propiedad is id Circulo turno*/
	private String is_circuloIdTurno;

	/**Propiedad is id Turno*/
	private String is_idTurno;

	/**Propiedad is id Turno Circulo*/
	private String is_idTurnoCirculo;

	/**Propiedad is turno historia*/
	private String is_idTurnoHistoria;

	/** Propiedad itr testamentos Remote. */
	@EJB
	private TestamentosRemote itr_testamentosRemote;

	/**Propiedad tramite cantidad*/
	private TramiteCantidad itc_tramiteCantidad;

	/**Propiedad lth turno historia*/
	private TurnoHistoria lth_turnoHistoria;

	/** Propiedad testamentos anterior visible*/
	private boolean ib_MostrarPanelDocumento;

	/** Propiedad  ib aprobacion Registro*/
	private boolean ib_aprobacionRegistro;

	/** Propiedadm  datosBasicos*/
	private boolean ib_datosBasicos;

	/**propiedad enviar aprobador*/
	private boolean ib_enviarAprobador;

	/** Propiedad guardar copias*/
	private boolean ib_guardarCopias;

	/**Propiedad ib seleccionado*/
	private boolean ib_habilitarBotonGuardar;

	/** Propiedad habilitar enviar Aprobador*/
	private boolean ib_habilitarEnviarAprobador;

	/** Propiedad habilitar enviar Aprobador*/
	private boolean ib_habilitarGenerarRepConstancia;

	/**Propiedad Mostrar Panel Turno*/
	private boolean ib_mostrarPanelTurno;

	/**Propiedad Mostrar Visualizar*/
	private boolean ib_mostrarVisualizar;

	/** Propiedad ib ocultar Boton Siguiente */
	private boolean ib_ocultarBotonSiguiente;

	/**Propiedad ib rendered rep constancia*/
	private boolean ib_renderedRepConstancia;

	/**Propiedad ib seleccionado*/
	private boolean ib_revisionCompleta;

	/**Propiedad ib seleccionado*/
	private boolean ib_seleccionado;

	/**propiedad enviar aprobador*/
	private boolean ib_terminarProceso;

	/** Propiedad testamentos anterior visible*/
	private boolean ib_testamentoAnteriorVisible;

	/** Propiedad ii total bandeja. */
	private int ii_totalBandeja;

	/**Propiedad imt_motivo tramite*/
	private long il_idMotivoTramite;

	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ab_b con el valor a asignar
	 */
	public void setAprobacionRegistro(boolean ab_b)
	{
		ib_aprobacionRegistro = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return con el valor de la propiedad
	 */
	public boolean isAprobacionRegistro()
	{
		return ib_aprobacionRegistro;
	}

	/***
	 * Método de asignación del valor de la propiedad
	 * @param id_bgnDocumento con el valor a asignar.
	 */
	public void setBgnDocumento(Documento ad_d)
	{
		id_bgnDocumento = ad_d;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return con el valor de la propiedad
	 */
	public Documento getBgnDocumento()
	{
		if(id_bgnDocumento == null)
			id_bgnDocumento = new Documento();

		return id_bgnDocumento;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param as_s con el valor de la propiedad a asignar de tipo String
	 */
	public void setCantidadCopiasReproducir(String as_s)
	{
		is_cantidadCopiasReproducir = as_s;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return is_cantidadCopiasReproducir con el valor de la propiedad
	 */
	public String getCantidadCopiasReproducir()
	{
		return is_cantidadCopiasReproducir;
	}

	public void setCirculoIdTurno(String as_s)
	{
		is_circuloIdTurno = as_s;
	}

	public String getCirculoIdTurno()
	{
		return is_circuloIdTurno;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param adsc_dsc  con el valor a asignar de tipo DefaultStreamedContent
	 */
	public void setConstanciaReproduccionTestamento(DefaultStreamedContent adsc_dsc)
	{
		idsc_constanciaReproduccionTestamento = adsc_dsc;
	}

	/**
	 * Método de Obtención del valor de la propiedad
	 * @return idsc_constanciaReproduccionTestamento con el valor de la propiedad
	 */
	public DefaultStreamedContent getConstanciaReproduccionTestamento()
	{
		return idsc_constanciaReproduccionTestamento;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param adsc_dsc con el valor a asignar
	 */
	public void setConstanciaTestamento(DefaultStreamedContent adsc_dsc)
	{
		idsc_constanciaTestamento = adsc_dsc;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return con el valor de la propiedad
	 */
	public DefaultStreamedContent getConstanciaTestamento()
	{
		return idsc_constanciaTestamento;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param arct_rct del tipo ReproduccionConstanciaTestamento con el valor a asignar
	 */
	public void setDataReproduccionConstancia(ReproduccionConstanciaTestamento arct_rct)
	{
		irct_dataReproduccionConstancia = arct_rct;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return del tipo ReproduccionConstanciaTestamento
	 */
	public ReproduccionConstanciaTestamento getDataReproduccionConstancia()
	{
		return irct_dataReproduccionConstancia;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ab_b con el valor a asignar
	 */
	public void setDatosBasicos(boolean ab_b)
	{
		ib_datosBasicos = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return  ib_datosBasicos con el valor de la propiedad
	 */
	public boolean isDatosBasicos()
	{
		return ib_datosBasicos;
	}

	/**
	 * Modifica el valor de datos tramite cantidad.
	 *
	 * @param actc_ctc asigna el valor a la propiedad datos tramite cantidad
	 */
	public void setDatosTramiteCantidad(Collection<TramiteCantidad> actc_ctc)
	{
		ictc_datosTramiteCantidad = actc_ctc;
	}

	/**
	 * Retorna el valor de datos tramite cantidad.
	 *
	 * @return el valor de datos tramite cantidad
	 */
	public Collection<TramiteCantidad> getDatosTramiteCantidad()
	{
		return ictc_datosTramiteCantidad;
	}

	/**
	 * Método de asignacion del valor de la propiedad
	 * @param ab_b de tipo boolñean con el valor de la propiedad
	 */
	public void setEnviarAprobador(boolean ab_b)
	{
		ib_enviarAprobador = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return con el ib_revisionCompleta
	 */
	public boolean isEnviarAprobador()
	{
		return ib_enviarAprobador;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ab_b de tipo boolean con el valor a asignar
	 */
	public void setGuardarCopias(boolean ab_b)
	{
		ib_guardarCopias = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return el valor de la propiedad de tipo boolean
	 */
	public boolean isGuardarCopias()
	{
		return ib_guardarCopias;
	}

	/**
	 * Método de asignación del valor de la propieda
	 * @param ab_b de tipo boolean con el valor de la propieadad
	 */
	public void setHabilitarBotonGuardar(boolean ab_b)
	{
		ib_habilitarBotonGuardar = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return de tipo boolean con el valor de la propiedad
	 */
	public boolean isHabilitarBotonGuardar()
	{
		return ib_habilitarBotonGuardar;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ab_b de tipo boolean con el valor a asignar
	 */
	public void setHabilitarEnviarAprobador(boolean ab_b)
	{
		ib_habilitarEnviarAprobador = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return de tipo boolean con el valor de la propiedad
	 */
	public boolean isHabilitarEnviarAprobador()
	{
		return ib_habilitarEnviarAprobador;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ab_b de tipo boolean con el valor a asignar
	 */
	public void setHabilitarGenerarRepConstancia(boolean ab_b)
	{
		ib_habilitarGenerarRepConstancia = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return de tipo boolean con el valor de la propiedad
	 */
	public boolean isHabilitarGenerarRepConstancia()
	{
		return ib_habilitarGenerarRepConstancia;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param as_it Con el valor a asignar
	 */
	public void setIdTurno(String as_it)
	{
		is_idTurno = as_it;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return String con el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	public void setIdTurnoCirculo(String as_s)
	{
		is_idTurnoCirculo = as_s;
	}

	public String getIdTurnoCirculo()
	{
		return is_idTurnoCirculo;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param as_s con el valor a asignar
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Método de obteción del valor de la propiedad
	 * @return is_idTurnoHistoria con el valor de la propiedad
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ab_b de tipo boolean con el valor a asignar
	 */
	public void setMostrarPanelDocumento(boolean ab_b)
	{
		ib_MostrarPanelDocumento = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad  ib ocultar Panel Documento
	 * @return de tipo boolean con el valor de la propiedad
	 */
	public boolean isMostrarPanelDocumento()
	{
		return ib_MostrarPanelDocumento;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ab_b de tipo boolean con el valor a asignar
	 */
	public void setMostrarPanelTurno(boolean ab_b)
	{
		ib_mostrarPanelTurno = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return de tipo boolean con el valor de la propiedad
	 */
	public boolean isMostrarPanelTurno()
	{
		return ib_mostrarPanelTurno;
	}

	/**
	 * Sets the mostrar visualizar.
	 *
	 * @param ab_b the new mostrar visualizar
	 */
	public void setMostrarVisualizar(boolean ab_b)
	{
		ib_mostrarVisualizar = ab_b;
	}

	public boolean isMostrarVisualizar()
	{
		return ib_mostrarVisualizar;
	}

	/**
	 * Méetodo de asignación del valor de la propiedad
	 * @param amt_mt con el valor a asignar
	 */
	public void setMotivoTramite(long amt_mt)
	{
		il_idMotivoTramite = amt_mt;
	}

	/**
	 * Método de obtención de la propiedad
	 * @return imt_motivoTramite con el valor de la propiedad
	 */
	public long getMotivoTramite()
	{
		return il_idMotivoTramite;
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
		TurnoHistoria             lt_turnoHistoria;
		Collection<MotivoTramite> lc_motivoTramite;
		Collection<MotivoTramite> lc_motivoTramiteTemp;
		SolicitudTestamento       ls_solicitudTestamento;
		Testamento                lt_testamento;

		lt_turnoHistoria           = getTurnoHistoria();
		lc_motivoTramite           = new LinkedList<MotivoTramite>();
		lc_motivoTramiteTemp       = null;
		ls_solicitudTestamento     = getSolicitudTestamento();
		lt_testamento              = null;

		if(ls_solicitudTestamento != null)
			lt_testamento = ls_solicitudTestamento.getTestamento();

		if(lt_turnoHistoria != null)
		{
			lc_motivoTramiteTemp = irr_referenceRemote.findMotivosByEtapa(
				    StringUtils.getString(EtapaCommon.ID_ETAPA_TESTAMENTOS),
				    StringUtils.getString(lt_turnoHistoria.getIdTurnoHistoria()), false, false
				);

			String ls_idProceso;
			String ls_idSubproceso;

			ls_idProceso        = lt_turnoHistoria.getIdProceso();
			ls_idSubproceso     = lt_turnoHistoria.getIdSubproceso();

			if(StringUtils.isValidString(ls_idProceso) && StringUtils.isValidString(ls_idSubproceso))
			{
				if(
				    ls_idProceso.equals(ProcesoCommon.ID_PROCESO_6)
					    && ls_idSubproceso.equals(ProcesoCommon.ID_SUBPROCESO_6)
				)
				{
					long ls_idMotivo;
					ls_idMotivo = 0L;

					String ls_idTipoTestamento;
					ls_idTipoTestamento = null;

					for(MotivoTramite imt_iterador : lc_motivoTramiteTemp)
					{
						if((imt_iterador != null) && (lt_testamento != null))
						{
							ls_idMotivo             = NumericUtils.getLong(imt_iterador.getIdMotivo());
							ls_idTipoTestamento     = lt_testamento.getIdTipoTestamento();

							if((ls_idMotivo != 0L) && StringUtils.isValidString(ls_idTipoTestamento))
							{
								if(ls_idMotivo == EtapaCommon.ID_ETAPA_10)
									lc_motivoTramite.add(imt_iterador);

								if(
								    (ls_idMotivo == EtapaCommon.ID_ETAPA_20)
									    && ls_idTipoTestamento.equals(IdentificadoresCommon.NUM1)
								)
								{
									lc_motivoTramite.add(imt_iterador);

									break;
								}

								if(
								    (ls_idMotivo == EtapaCommon.ID_ETAPA_30)
									    && ls_idTipoTestamento.equals(IdentificadoresCommon.NUM2)
								)
								{
									lc_motivoTramite.add(imt_iterador);

									break;
								}

								if(
								    (ls_idMotivo == EtapaCommon.ID_ETAPA_40)
									    && ls_idTipoTestamento.equals(IdentificadoresCommon.NUM4)
								)
								{
									lc_motivoTramite.add(imt_iterador);

									break;
								}

								if(
								    (ls_idMotivo == EtapaCommon.ID_ETAPA_50)
									    && ls_idTipoTestamento.equals(IdentificadoresCommon.NUM3)
								)
								{
									lc_motivoTramite.add(imt_iterador);

									break;
								}
							}
						}
					}
				}
				else if(
				    ls_idProceso.equals(ProcesoCommon.ID_PROCESO_6)
					    && ls_idSubproceso.equals(ProcesoCommon.ID_SUBPROCESO_4)
				)
					lc_motivoTramite.addAll(lc_motivoTramiteTemp);
			}
		}

		setMotivos(lc_motivoTramite);
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ab_b con el valor a asignar
	 */
	public void setOcultarBotonSiguiente(boolean ab_b)
	{
		ib_ocultarBotonSiguiente = ab_b;
	}

	/**
	 * Método obtención del valor de la propiedad
	 * @return con el valor de la propiedad
	 */
	public boolean isOcultarBotonSiguiente()
	{
		return ib_ocultarBotonSiguiente;
	}

	/**
	 * Retorna el valor de oficina origen.
	 *
	 * @param addp_datosPredio correspondiente al valor del tipo de objeto DatosDelPredio
	 * @return el valor de oficina origen
	 */
	public Collection<OficinaOrigen> getOficinaOrigen()
	{
		Collection<OficinaOrigen> lcidt_datos;

		lcidt_datos = null;

		try
		{
			Documento ld_documento;
			String    ls_idPais;
			String    ls_idDepartamento;
			String    ls_idMunicipio;
			String    ls_idTipoOficina;

			ls_idPais             = null;
			ls_idDepartamento     = null;
			ls_idMunicipio        = null;
			ls_idTipoOficina      = null;

			ld_documento = getBgnDocumento();

			if(ld_documento != null)
			{
				ls_idTipoOficina     = ld_documento.getIdTipoOficina();

				ls_idPais             = ld_documento.getIdPais();
				ls_idDepartamento     = ld_documento.getIdDepartamento();
				ls_idMunicipio        = ld_documento.getIdMunicipio();
			}

			if(ls_idPais != null)
			{
				OficinaOrigen loo_oficina = new OficinaOrigen();
				loo_oficina.setIdTipoOficina(ls_idTipoOficina);
				loo_oficina.setIdPais(ls_idPais);
				loo_oficina.setIdDepartamento(ls_idDepartamento);
				loo_oficina.setIdMunicipio(ls_idMunicipio);
				lcidt_datos = irr_referenceRemote.findOficinaOrigen(loo_oficina);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Método de asignación del valor de la propiead
	 * @param ab_b con el valor a asignar
	 */
	public void setRenderedRepConstancia(boolean ab_b)
	{
		ib_renderedRepConstancia = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return con el valor de la propiedad
	 */
	public boolean isRenderedRepConstancia()
	{
		return ib_renderedRepConstancia;
	}

	/**
	 * Método de asignacion del valor de la propiedad
	 * @param ab_b de tipo boolean con el valor de la propiedad
	 */
	public void setRevisionCompleta(boolean ab_b)
	{
		ib_revisionCompleta = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return con el ib_revisionCompleta
	 */
	public boolean isRevisionCompleta()
	{
		return ib_revisionCompleta;
	}

	/**
	 * Método de asignacion del valor de la propiedad
	 * @param ab_b de tipo boolñean con el valor de la propiedad
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return con el ib_seleccionado
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ast_st con el valor a asignar
	 */
	public void setSolicitudTestamento(SolicitudTestamento ast_st)
	{
		ist_solicitudTestamento = ast_st;
	}

	/**
	 * Metodo de obtención del valor de la propiedad
	 * @return ist_solicitudTestamento con el valor de la propiedad
	 */
	public SolicitudTestamento getSolicitudTestamento()
	{
		if(ist_solicitudTestamento == null)
			ist_solicitudTestamento = new SolicitudTestamento();

		return ist_solicitudTestamento;
	}

	/**
	 * Método de asignacion del valor de la propiedad
	 * @param ab_b de tipo boolñean con el valor de la propiedad
	 */
	public void setTerminarProceso(boolean ab_b)
	{
		ib_terminarProceso = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return con el ib_seleccionado
	 */
	public boolean isTerminarProceso()
	{
		return ib_terminarProceso;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ab_b con el valor a asignar
	 */
	public void setTestamentoAnteriorVisible(boolean ab_b)
	{
		ib_testamentoAnteriorVisible = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return con el valor de la propiedad
	 */
	public boolean isTestamentoAnteriorVisible()
	{
		return ib_testamentoAnteriorVisible;
	}

	/**
	 * Método de asignacion del valor de la propiedad
	 * @param ac_c de tipo Collection<TipoOficina> con el valor a asignar
	 */
	public void setTipoOficinaDocumento(Collection<TipoOficina> ac_c)
	{
		ic_tipoOficinaDocumento = ac_c;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return de tipo Collection<TipoOficina> con el valor de la propiedad
	 */
	public Collection<TipoOficina> getTipoOficinaDocumento()
	{
		return ic_tipoOficinaDocumento;
	}

	/**
	 * Modifica el valor de total bandeja.
	 *
	 * @param ai_i asigna el valor a la propiedad total bandeja
	 */
	public void setTotalBandeja(int ai_i)
	{
		ii_totalBandeja = ai_i;
	}

	/**
	 * Retorna el valor de total bandeja.
	 *
	 * @return el valor de total bandeja
	 */
	public int getTotalBandeja()
	{
		return ii_totalBandeja;
	}

	/**
	 * Método de asignación del valor dfe la propiedad
	 * @param atc_tc con el valor a asignar
	 */
	public void setTramiteCantidad(TramiteCantidad atc_tc)
	{
		itc_tramiteCantidad = atc_tc;
	}

	/**
	 * Método ded obtención del valor de la propiedad
	 * @return itc_tramiteCantidad con el valor de la propiedad
	 */
	public TramiteCantidad getTramiteCantidad()
	{
		return itc_tramiteCantidad;
	}

	/**
	 * Método de asignacion del valor de la varible
	 * @param ath_th con el valor a asignar
	 */
	public void setTurnoHistoria(TurnoHistoria ath_th)
	{
		lth_turnoHistoria = ath_th;
	}

	/**
	 * Método de obtención del valor de la variable
	 * @return con el valor de la variable
	 */
	public TurnoHistoria getTurnoHistoria()
	{
		return lth_turnoHistoria;
	}

	/**
	 * Método de consulta de reproducción de constancia por Documento.
	 * @throws B2BException
	 */
	public void buscarRepConstanciaDocumento()
	    throws B2BException
	{
		ReproduccionConstanciaTestamento lrt_reproduccionTestamento;
		lrt_reproduccionTestamento = null;

		try
		{
			boolean   lb_focus;
			Documento ld_documento;
			ld_documento = getBgnDocumento();

			String ls_validator;
			ls_validator = null;

			if(ld_documento != null)
			{
				lb_focus = true;

				FacesContext lfc_context;
				lfc_context = FacesContext.getCurrentInstance();

				{
					ls_validator     = ld_documento.getNumero();
					lb_focus         = validateStyles(":fIdTramites:idItDocuActo", lfc_context, ls_validator, lb_focus);
				}

				{
					ls_validator     = ld_documento.getIdTipoDocumento();
					lb_focus         = validateStyles(
						    ":fIdTramites:idSOMEscritura", lfc_context, ls_validator, lb_focus
						);
				}

				{
					Date ld_date;
					ld_date      = ld_documento.getFechaDocumento();
					lb_focus     = validateStyles(":fIdTramites:idCalFechaDoc", lfc_context, ld_date, lb_focus);
				}

				lrt_reproduccionTestamento = itr_testamentosRemote.buscarRepConstanciaDocumento(
					    ld_documento, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lrt_reproduccionTestamento != null)
				{
					setSeleccionado(true);
					setHabilitarBotonGuardar(true);
				}

				PrimeFaces lpf_pf;
				lpf_pf = PrimeFaces.current();

				lpf_pf.ajax().update("fIdTramites");
			}
		}
		catch(B2BException lb2be_e)
		{
			actualizarComponente("fIdTramites:idGrowl");
			clh_LOGGER.error(lb2be_e);
			throw lb2be_e;
		}

		setDataReproduccionConstancia(lrt_reproduccionTestamento);
		setMostrarPanelTurno(false);
	}

	/**
	 * Método de consulta de reproducción de constancia por turno.
	 * @throws B2BException
	 */
	public void buscarRepConstanciaTurnoTest()
	    throws B2BException
	{
		ReproduccionConstanciaTestamento lrt_reproduccionTestamento;
		PrimeFaces                       lpf_pf;

		lrt_reproduccionTestamento     = null;
		lpf_pf                         = PrimeFaces.current();

		try
		{
			FacesContext lfc_context;
			boolean      lb_focus;
			String       ls_turno;
			String       ls_circulo;

			lfc_context     = FacesContext.getCurrentInstance();
			lb_focus        = true;
			ls_turno        = null;
			ls_circulo      = null;

			{
				ls_turno       = getIdTurno();
				lb_focus       = validateStyles(":fIdTramites:idItTurnRadicacion", lfc_context, ls_turno, lb_focus);
				ls_circulo     = getIdTurnoCirculo();
				lb_focus       = validateStyles(":fIdTramites:idSOMIdCirculo", lfc_context, ls_circulo, lb_focus);
			}

			if(StringUtils.isValidString(ls_turno))
			{
				if(StringUtils.isValidString(ls_circulo))
				{
					Turno lt_turno;

					lt_turno = new Turno();

					lt_turno.setIdTurno(ls_turno);
					lt_turno.setIdCirculo(ls_circulo);

					lrt_reproduccionTestamento = itr_testamentosRemote.buscarRepConstanciaTurno(
						    lt_turno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lrt_reproduccionTestamento != null)
					{
						setSeleccionado(true);
						setHabilitarBotonGuardar(true);
					}

					setCirculoIdTurno(ls_circulo);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_INTENTE_NUEVAMENTE);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_INTENTE_NUEVAMENTE);

			lpf_pf.ajax().update("fIdTramites");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente("fIdTramites:idGrowl");
		}

		setDataReproduccionConstancia(lrt_reproduccionTestamento);
		setMostrarPanelDocumento(false);
	}

	/**
	 * Método encargado de limpiar/reiniciar las variables de la clase.
	 */
	public void clear()
	{
		setSolicitudTestamento(null);
		setNirConsulta(null);
		setIdTurnoConsulta(null);
		setIdTurno(null);
		setIdTurnoCirculo(null);
		setCirculoIdTurno(null);
		setMotivoTramite(0);
		setIdTurnoHistoria(null);
		setDatosBasicos(false);
		setAprobacionRegistro(false);
		setOcultarBotonSiguiente(false);
		setEnviarAprobador(false);
		setTerminarProceso(false);
		setRenderedRepConstancia(false);
		setReproduccionConstancia(false);
		setMostrarVisualizar(true);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String detalleTurno(TramiteCantidad atc_tc)
	{
		try
		{
			String ls_idTurno;
			String ls_idTurnoHistoria;
			Long   ll_etapa;
			String ls_decisionCalificacion;

			ls_idTurnoHistoria          = null;
			ll_etapa                    = null;
			ls_decisionCalificacion     = null;

			if(atc_tc != null)
			{
				clear();

				ls_idTurno = atc_tc.getIdTurno();

				{
					TurnoHistoria lth_th;
					Long          ll_idTurnoHistoria;

					lth_th                 = new TurnoHistoria();
					ls_idTurnoHistoria     = atc_tc.getIdTurnoHistoria();

					if(StringUtils.isValidString(ls_idTurnoHistoria))
						setIdTurnoHistoria(ls_idTurnoHistoria);

					ll_etapa               = NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_TESTAMENTOS);
					ll_idTurnoHistoria     = NumericUtils.getLongWrapper(ls_idTurnoHistoria);

					lth_th.setIdTurnoHistoria(ll_idTurnoHistoria);

					validarFechaInicialEtapa(lth_th);

					lth_th = itr_testamentosRemote.findTurnoHistoriaById(lth_th);

					generarData(atc_tc.getNir());
					setTramiteCantidad(atc_tc);
					setTurnoHistoria(lth_th);
				}

				{
					SolicitudTestamento     lst_solicitudTestamento;
					FacesContext            lfc_context;
					BeanPredioDocumentoActo lbpdab_bean;

					lst_solicitudTestamento     = getSolicitudTestamento();
					lfc_context                 = FacesUtils.getFacesContext();

					{
						lbpdab_bean = (BeanPredioDocumentoActo)lfc_context.getApplication()
								                                              .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO,
								    BeanPredioDocumentoActo.class
								);

						if(lbpdab_bean != null)
						{
							lbpdab_bean.limpiarDatos();
							lbpdab_bean.setIdEtapa(ll_etapa);
							lbpdab_bean.setIdTurno(ls_idTurno);
							lbpdab_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
							lbpdab_bean.generarDatosAntSistema();
							lbpdab_bean.generarDatosDocumento();
							lbpdab_bean.validarFechaVencimientoActo();
							lbpdab_bean.setMotivoTramite(null);
							lbpdab_bean.setDecisionCalificacion(ls_decisionCalificacion);
							lbpdab_bean.setOcultarPaneles(true);
							lbpdab_bean.setMostrarActo(true);

							if(lst_solicitudTestamento != null)
							{
								Testamento lt_testamento;

								lt_testamento = lst_solicitudTestamento.getTestamento();

								getMotivosTramite();

								lbpdab_bean.setTestamentos(true);

								if(lt_testamento != null)
								{
									lbpdab_bean.setNombreTipoTestamento(lt_testamento.getNombreTipoTestamento());
									lbpdab_bean.setCategoriaTestamento(lt_testamento.getCategoria());
								}
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

		return NavegacionCommon.TESTAMENTOS_EJECUTOR;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param addp_datosPredio correspondiente al valor del tipo de objeto DatosDelPredio
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Departamento> findDepartamentos()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			Documento ld_documento;
			ld_documento = getBgnDocumento();

			String ls_idPais;
			ls_idPais = null;

			if(ld_documento != null)
				ls_idPais = ld_documento.getIdPais();

			if(ls_idPais != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(ls_idPais);

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param addp_datosPredio correspondiente al valor del tipo de objeto DatosDelPredio
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Municipio> findMunicipios()
	{
		Collection<Municipio> lcm_municipios;
		String                ls_idPais;
		String                ls_idDepartamento;

		lcm_municipios        = new ArrayList<Municipio>();
		ls_idPais             = null;
		ls_idDepartamento     = null;

		Documento ld_documento;
		ld_documento          = getBgnDocumento();

		if(ld_documento != null)
		{
			ls_idPais             = ld_documento.getIdPais();
			ls_idDepartamento     = ld_documento.getIdDepartamento();
		}

		if((ls_idPais != null) && (ls_idDepartamento != null))
		{
			try
			{
				Municipio lm_parametros;
				lm_parametros = new Municipio();

				lm_parametros.setIdPais(ls_idPais);
				lm_parametros.setIdDepartamento(ls_idDepartamento);

				if(ls_idDepartamento.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_BOGOTA_DEPARTAMENTO))
				{
					lm_parametros.setIdMunicipio(IdentificadoresCommon.INDICATIVO_BOGOTA_MUNICIPIO);
					lcm_municipios.add(ipr_parameterRemote.findMunicipioById(lm_parametros));
				}
				else
					lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				addMessage(lb2be_e);

				lcm_municipios = null;
			}
		}

		return lcm_municipios;
	}

	/**
	 * Find tipo entidad apertura.
	 */
	public void findTipoEntidadDocumento()
	{
		try
		{
			Documento ld_documento;
			ld_documento = getBgnDocumento();

			if(ld_documento != null)
			{
				if(StringUtils.isValidString(ld_documento.getIdTipoDocumento()))
				{
					TipoOficina lto_to;

					lto_to = new TipoOficina();

					lto_to.setIdTipoOficina(ld_documento.getIdTipoOficina());

					lto_to = irr_referenceRemote.findTipoOficinaById(lto_to);

					if(lto_to != null)
					{
						TipoEntidad lte_te;

						lte_te = new TipoEntidad();

						lte_te.setIdTipoEntidad(lto_to.getIdTipoEntidad());

						lte_te = irr_referenceRemote.findTipoEntidadById(lte_te);

						ld_documento.setTipoEntidad(lte_te.getIdTipoEntidad());
					}
				}
				else
				{
					ld_documento.setTipoOficina(null);
					ld_documento.setTipoEntidad(null);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findTipoEntidadDocumento", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<TipoOficina> findTipoOficina()
	{
		Collection<TipoOficina> lcidt_datos;

		lcidt_datos = new ArrayList<TipoOficina>();

		try
		{
			Documento lod_d;

			lod_d     = getBgnDocumento();

			lcidt_datos = irr_referenceRemote.findTipoOficina(lod_d);

			if(CollectionUtils.isValidCollection(lcidt_datos))
			{
				if(lcidt_datos.size() == 1)
				{
					for(TipoOficina loto_tmp : lcidt_datos)
					{
						if(loto_tmp != null)
							lod_d.setIdTipoOficina(loto_tmp.getIdTipoOficina());
					}
				}

				setTipoOficinaDocumento(lcidt_datos);
			}

			findTipoEntidadDocumento();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * genera el documento que relaciona los cambios realizados al tramite de
	 * correcciones.
	 */
	public void generarConstanciaReproduccionTestamento()
	{
		// TODO
		try
		{
			ReproduccionConstanciaTestamento lct_constanciaTestamento;
			byte[]                           lba_documento;
			Ajax                             la_ajax;

			setConstanciaTestamento(null);

			la_ajax                      = PrimeFaces.current().ajax();
			lct_constanciaTestamento     = getDataReproduccionConstancia();
			lba_documento                = itr_testamentosRemote.generarReproduccionConstanciaTestamento(
				    lct_constanciaTestamento, getTurnoHistoria().getIdTurno(), getUserId(), getLocalIpAddress(),
				    getRemoteIpAddress()
				);

			if(lba_documento != null)
				setConstanciaReproduccionTestamento(
				    new DefaultStreamedContent(
				        new ByteArrayInputStream(lba_documento), TipoContenidoCommon.PDF,
				        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + ExtensionCommon.PDF_PUNTO
				    )
				);
			else
				throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);

			setHabilitarGenerarRepConstancia(false);
			setHabilitarBotonGuardar(true);
			setGuardarCopias(true);

			addMessage(MessagesKeys.DOCUMENTO_GENERADO_VISUALIZAR);

			la_ajax.update("fIdTramites");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarConstanciaReproduccionTestamento", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * genera el documento que relaciona los cambios realizados al tramite de
	 * correcciones.
	 */
	public void generarConstanciaTestamento()
	{
		try
		{
			byte[]     lba_documento;
			PrimeFaces lpf_pf;
			lpf_pf = PrimeFaces.current();

			setConstanciaTestamento(null);

			SolicitudTestamento lst_solicitudTestamento;
			lst_solicitudTestamento     = getSolicitudTestamento();
			lba_documento               = itr_testamentosRemote.generarConstanciaTestamento(
				    lst_solicitudTestamento, getTramiteCantidad().getNir(), getIdTurnoHistoria(), getUserId(),
				    getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lba_documento != null)
				setConstanciaTestamento(
				    new DefaultStreamedContent(
				        new ByteArrayInputStream(lba_documento), TipoContenidoCommon.PDF,
				        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + ExtensionCommon.PDF_PUNTO
				    )
				);
			else
				throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);

			setEnviarAprobador(true);

			lpf_pf.ajax().update("fIdTramites");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarConstanciaTestamento", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de llenar los datos de presentación de la pantalla ejecutor de testamentos
	 * @param as_turno con el turno asignado a la búsqueda en BD
	 * @throws B2BException
	 */
	public void generarData(String as_turno)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_turno))
		{
			SolicitudTestamento lst_solicitudTestamento;

			lst_solicitudTestamento = itr_testamentosRemote.generarDataTestamentos(
				    as_turno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lst_solicitudTestamento != null)
				setSolicitudTestamento(lst_solicitudTestamento);
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_INTENTE_NUEVAMENTE);
		}
	}

	/**
	 * Generar datos bandeja.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDatosBandeja()
	    throws B2BException
	{
		Collection<TramiteCantidad> lctc_tramitesCantidad;

		lctc_tramitesCantidad = null;

		try
		{
			TramiteCantidad ltc_tc;
			int             li_cantidadPorEtapas;

			ltc_tc                   = new TramiteCantidad();
			li_cantidadPorEtapas     = 0;

			ltc_tc.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_TESTAMENTOS));

			ltc_tc.setUsuario(getUserId());
			ltc_tc.setIdTurno(getIdTurnoConsulta());
			ltc_tc.setNir(getNirConsulta());

			lctc_tramitesCantidad = itr_testamentosRemote.consultaBandeja(
				    ltc_tc, true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lctc_tramitesCantidad))
			{
				for(TramiteCantidad ltc_actual : lctc_tramitesCantidad)
				{
					if(ltc_actual != null)
						li_cantidadPorEtapas = li_cantidadPorEtapas
							+ (NumericUtils.isValidInteger(ltc_actual.getCantidad(), 0)
							? NumericUtils.getInt(ltc_actual.getCantidad()) : 0);
				}
			}

			setTotalBandeja(li_cantidadPorEtapas);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		setDatosTramiteCantidad(lctc_tramitesCantidad);
	}

	/**
	 * Generar torta.
	 *
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public PieChartModel generarTorta()
	    throws B2BException
	{
		return getModeloTorta();
	}

	/**
	 * genera el documento que relaciona los cambios realizados al tramite de
	 * correcciones.
	 */
	public String guardarConstanciaReproduccionTestamento()
	{
		String ls_return;

		ls_return = null;

		try
		{
			MotivoTramite lmt_motivoTramite;

			lmt_motivoTramite = new MotivoTramite();

			setConstanciaTestamento(null);

			lmt_motivoTramite.setIdMotivo(getMotivoTramite());
			lmt_motivoTramite.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_TESTAMENTOS);

			itr_testamentosRemote.terminarTurnoHistoriaYCrearEtapa(
			    getTurnoHistoria(), lmt_motivoTramite, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setHabilitarEnviarAprobador(false);
			setHabilitarBotonGuardar(false);
			setTerminarProceso(true);
			clear();
			retornarBandeja();

			ls_return = NavegacionCommon.TESTAMENTOS;
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarConstanciaReproduccionTestamento", lb2be_e);
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * genera el documento que relaciona los cambios realizados al tramite de
	 * correcciones.
	 * @throws B2BException
	 */
	public String guardarConstanciaTestamento()
	    throws B2BException
	{
		Ajax lpf_pf;

		lpf_pf = PrimeFaces.current().ajax();

		try
		{
			if(isRevisionCompleta() && isEnviarAprobador())
			{
				byte[] lba_documento;
				setConstanciaTestamento(null);

				SolicitudTestamento lst_solicitudTestamento;
				lst_solicitudTestamento     = getSolicitudTestamento();
				lba_documento               = itr_testamentosRemote.guardarConstanciaTestamento(
					    lst_solicitudTestamento, getTramiteCantidad().getNir(), getIdTurnoHistoria(), getUserId(),
					    getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lba_documento != null)
					setConstanciaTestamento(
					    new DefaultStreamedContent(
					        new ByteArrayInputStream(lba_documento), TipoContenidoCommon.PDF,
					        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + ExtensionCommon.PDF_PUNTO
					    )
					);
				else
					throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);

				{
					MotivoTramite lmt_motivoTramite;

					lmt_motivoTramite = new MotivoTramite();
					lmt_motivoTramite.setIdMotivo(getMotivoTramite());
					lmt_motivoTramite.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_TESTAMENTOS);
					itr_testamentosRemote.terminarTurnoHistoriaYCrearEtapa(
					    getTurnoHistoria(), lmt_motivoTramite, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
				}

				setEnviarAprobador(false);
				setTerminarProceso(true);
				generarDatosBandeja();
			}
			else
				throw new B2BException(ErrorKeys.ERROR_DEBE_REALIZAR_REVISION_COMPLETA);
		}
		catch(B2BException lb2be_e)
		{
			lpf_pf.update("fIdTramites:idGrowl");
			clh_LOGGER.error("guardarConstanciaTestamento", lb2be_e);
			addMessage(lb2be_e);
		}

		retornarBandeja();

		return NavegacionCommon.TESTAMENTOS;
	}

	/**
	 *
	 * @throws B2BException
	 */
	public void guardarDatosRepConstancia()
	    throws B2BException
	{
		// TODO
		try
		{
			ReproduccionConstanciaTestamento lrt_reproduccionTestamento;
			PrimeFaces                       lpf_pf;

			lrt_reproduccionTestamento     = getDataReproduccionConstancia();
			lpf_pf                         = PrimeFaces.current();

			if(lrt_reproduccionTestamento != null)
			{
				itr_testamentosRemote.guardarSolicitudReproduccion(
				    lrt_reproduccionTestamento, getCantidadCopiasReproducir(), isGuardarCopias(), getUserId(),
				    getRemoteIpAddress(), getLocalIpAddress()
				);

				if(isGuardarCopias())
				{
					setHabilitarEnviarAprobador(true);
					setHabilitarGenerarRepConstancia(false);
				}
				else
				{
					setHabilitarGenerarRepConstancia(true);
					setHabilitarBotonGuardar(false);
				}
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_INTENTE_NUEVAMENTE);

			lpf_pf.ajax().update("fIdTramites");
		}
		catch(B2BException lb2be_e)
		{
			actualizarComponente("fIdTramites:idGrowl");
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Item seleccionado.
	 *
	 * @param event correspondiente al valor del tipo de objeto ItemSelectEvent
	 */
	public void itemSeleccionado(ItemSelectEvent event)
	{
		itemSelect(event);
	}

	/**
	 * Método de limpieza de las consultas de documento
	 */
	public void limpiarRepConstanciaTurno()
	{
		setIdTurno(null);
		setIdTurnoCirculo(null);
		setCirculoIdTurno(null);
		setBgnDocumento(null);

		PrimeFaces.current().ajax().update("fIdTramites");
	}

	/**
	 * Mostrar torta.
	 *
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean mostrarTorta()
	    throws B2BException
	{
		return isMostrarGrafica();
	}

	/**
	 * Ocultar visualizar.
	 */
	public void ocultarVisualizar()
	{
		PrimeFaces lpf_current;

		lpf_current = PrimeFaces.current();

		setMostrarVisualizar(false);

		lpf_current.executeScript(
		    "document.getElementById('fIdTramites:idCBVisualizarConstancia').style.hide = 'inline'"
		);
		lpf_current.ajax().update("fIdTramites");
	}

	/**
	 * Retornar bandeja.
	 *
	 * @throws B2BException the b 2 B exception
	 */
	public void retornarBandeja()
	    throws B2BException
	{
		clear();
		generarDatosBandeja();
		generarGraficoDeTorta(EtapaCommon.ID_ETAPA_TESTAMENTOS, false);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param atc_tc correspondiente al valor del tipo de objeto TramiteCantidad
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String returnPages(TramiteCantidad atc_tc)
	    throws B2BException
	{
		String ls_return;

		ls_return = NavegacionCommon.TESTAMENTOS;

		try
		{
			if(isMostrarPanelTurno() && isRenderedRepConstancia())
			{
				if(atc_tc != null)
				{
					clear();
					generarData(atc_tc.getNir());
					getMotivosTramite();

					{
						TurnoHistoria lth_th;

						lth_th = new TurnoHistoria();

						Long ll_idTurnoHistoria;

						ll_idTurnoHistoria = NumericUtils.getLongWrapper(atc_tc.getIdTurnoHistoria());

						lth_th.setIdTurnoHistoria(ll_idTurnoHistoria);

						validarFechaInicialEtapa(lth_th);
						setIdTurnoHistoria(StringUtils.getString(ll_idTurnoHistoria));
					}

					ls_return = NavegacionCommon.TESTAMENTOS_EJECUTOR;
					setTramiteCantidad(atc_tc);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_INTENTE_NUEVAMENTE);
			}
			else
				retornarBandeja();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/***
	 * Método de validación del motivo trámite y accionees procedentes
	 * @return con la página siguiente
	 * @throws B2BException en caso de un error
	 */
	public String validarMotivoTramite()
	    throws B2BException
	{
		String       lmt_motivoTramite;
		FacesContext lfc_context;
		String       ls_return;

		lmt_motivoTramite     = StringUtils.getString(getMotivoTramite());
		lfc_context           = FacesUtils.getFacesContext();
		ls_return             = null;

		if(isDatosBasicos())
		{
			BeanConfrontacion lbrc_bean;

			lbrc_bean = (BeanConfrontacion)lfc_context.getApplication()
					                                      .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_CONFRONTACION, BeanConfrontacion.class
					);

			if(lbrc_bean != null)
			{
				lbrc_bean.clear();
				lbrc_bean.validarActoEjecutoria();
				lbrc_bean.setDatosBasicos(ib_datosBasicos);
				lbrc_bean.setMotivoTramite(lmt_motivoTramite);
				lbrc_bean.setIdTurnoHistoria(getIdTurnoHistoria());

				{
					RegistroCalificacion lrc_rc;
					lrc_rc = new RegistroCalificacion();

					lrc_rc.setTurno(getIdTurno());
					lrc_rc.setIdEtapa(EtapaCommon.ID_ETAPA_TESTAMENTOS);

					lrc_rc = irr_calificacionRemote.turnosVinculados(
						    lrc_rc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lrc_rc != null)
					{
						lbrc_bean.setInfoTurnos(lrc_rc.getAllMatriculas());
						lbrc_bean.setIndVinculado(isIndVinculado());
					}
				}

				lbrc_bean.findObservacionesByIdTurnoHistoria(getIdTurnoHistoria());

				ls_return = NavegacionCommon.REVISION_CONFRONTACION;
			}

			setMotivoTramite(0);
		}

		if(
		    lmt_motivoTramite.equalsIgnoreCase(
			        StringUtils.getString(MotivoTramiteCommon.MODIFICACION_DATOS_BASICOS_TESTAMENTOS)
			    )
		)
			setDatosBasicos(true);
		else if(
		    (lmt_motivoTramite.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.APROBAR_TESTAMENTOS))
			    || lmt_motivoTramite.equalsIgnoreCase(
			        StringUtils.getString(MotivoTramiteCommon.APROBACION_REVOCATORIA_TESTAMENTO)
			    )
			    || lmt_motivoTramite.equalsIgnoreCase(
			        StringUtils.getString(MotivoTramiteCommon.APROBACION_ANULACION_TESTAMENTO)
			    )
			    || lmt_motivoTramite.equalsIgnoreCase(
			        StringUtils.getString(MotivoTramiteCommon.APROBACION_MODIFICACION_TESTAMENTO)
			    ))
		)
		{
			setAprobacionRegistro(true);

			SolicitudTestamento lst_solicitudTestamento;
			lst_solicitudTestamento = getSolicitudTestamento();

			if(lst_solicitudTestamento != null)
			{
				Testamento lt_testamento;
				lt_testamento = lst_solicitudTestamento.getTestamento();

				if(lt_testamento != null)
				{
					String lt_tipoTestamento;
					lt_tipoTestamento = lt_testamento.getIdTipoTestamento();

					if((lt_tipoTestamento != null) && !lt_tipoTestamento.equals(IdentificadoresCommon.NUM1))
						setTestamentoAnteriorVisible(true);

					TurnoHistoria lth_turnoHistoria;
					lth_turnoHistoria = getTurnoHistoria();

					if(lth_turnoHistoria != null)
					{
						Date ld_fechaActual;
						ld_fechaActual = new Date();

						LibroTestamento llt_libroTestamento;
						llt_libroTestamento = new LibroTestamento();
						llt_libroTestamento.setIdCirculo(lth_turnoHistoria.getIdCirculoUsuario());
						llt_libroTestamento.setAno(NumericUtils.getLongWrapper(DateUtils.getYear(ld_fechaActual)));

						llt_libroTestamento = itr_testamentosRemote.findLibroTestamento(
							    llt_libroTestamento, getUserId(), getRemoteIpAddress(), getLocalIpAddress()
							);

						if(llt_libroTestamento != null)
							lst_solicitudTestamento.setLibroTestamento(llt_libroTestamento);

						setOcultarBotonSiguiente(true);
					}
				}
			}
		}
		else if(
		    lmt_motivoTramite.equalsIgnoreCase(
			        StringUtils.getString(MotivoTramiteCommon.REPRODUCCION_DE_CONSTANCIA_DE_TESTAMENTOS)
			    )
		)
		{
			setHabilitarGenerarRepConstancia(false);
			setDataReproduccionConstancia(null);
			setHabilitarEnviarAprobador(false);
			setReproduccionConstancia(true);
			setRenderedRepConstancia(true);
			setOcultarBotonSiguiente(true);
			setMostrarPanelDocumento(true);
			setHabilitarBotonGuardar(true);
			setMostrarVisualizar(true);
			setMostrarPanelTurno(true);
			setGuardarCopias(false);
			setIdTurnoCirculo(null);
			setCirculoIdTurno(null);
			setIdTurno(null);
		}
		else if(lmt_motivoTramite.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.NOTA_DEVOLUTIVA)))
		{
			BeanNotaDevolutiva lbnd_bean;

			lbnd_bean = (BeanNotaDevolutiva)lfc_context.getApplication()
					                                       .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_NOTA_DEVOLUTIVA, BeanNotaDevolutiva.class
					);

			if(lbnd_bean != null)
			{
				BeanPredioDocumentoActo lbpda_bean;

				lbpda_bean = (BeanPredioDocumentoActo)lfc_context.getApplication()
						                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
						);

				if(lbpda_bean != null)
				{
					Map<String, Object> lmso_data;
					Object              lo_nir;
					Object              lo_idTurno;

					lmso_data      = lbpda_bean.getPredio();
					lo_nir         = null;
					lo_idTurno     = null;

					if(lmso_data != null)
					{
						lo_nir         = lmso_data.get("NIR");
						lo_idTurno     = lmso_data.get("ID_TURNO");
					}

					if(lo_nir != null)
						lbnd_bean.setNir(lo_nir.toString());

					if(lo_idTurno != null)
						lbnd_bean.setTurno(lo_idTurno.toString());
				}

				lbnd_bean.clear();
				lbnd_bean.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivoTramite));
				lbnd_bean.setIdTurnoHistoria(getIdTurnoHistoria());
				lbnd_bean.setObservaciones(null);
				lbnd_bean.setComplementacionCausales(null);
				lbnd_bean.setRegistroParcial(false);
				lbnd_bean.setTestamentos(true);
				lbnd_bean.setReproduccionConstancia(isReproduccionConstancia());
				lbnd_bean.setIndVinculado(isIndVinculado());
				lbnd_bean.setIdTurnoHistoria(getIdTurnoHistoria());

				ls_return = NavegacionCommon.NOTA_DEVOLUTIVA;
			}
		}

		return ls_return;
	}
}
