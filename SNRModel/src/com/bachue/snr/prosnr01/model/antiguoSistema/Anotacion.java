package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.b2bsg.common.util.ComparatorUtils;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDatosAnotacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDatosDocumento;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDetalleAntSistemaAnotacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDetalleIntervinientes;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelEspecificacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelIntervinientes;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.MatriculaSegregacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Collection;


/**
 * Class que contiene todos las propiedades Anotacion.
 *
 * @author garias
 */
public class Anotacion implements Serializable, Comparable<Anotacion>
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5149243046181613616L;

	/** Propiedad iapr acc predio registro. */
	private AccPredioRegistro iapr_accPredioRegistro;

	/** Propiedad iac anotacion cancelacion. */
	private AnotacionCancelacion iac_anotacionCancelacion;

	/** Propiedad iap anotacion predio. */
	private AnotacionPredio iap_anotacionPredio;

	/** Propiedad iapc anotacion predio ciudadano. */
	private AnotacionPredioCiudadano iapc_anotacionPredioCiudadano;

	/** Propiedad ibd_orden. */
	private BigDecimal ibd_orden;

	/** Propiedad aca intervinientes agregados. */
	private Collection<Anotacion> aca_intervinientesAgregados;

	/** Propiedad ica matriculas segregadas. */
	private Collection<Anotacion> ica_matriculasSegregadas;

	/** Propiedad icaa anotaciones agregadas. */
	private Collection<Anotacion> icaa_anotacionesAgregadas;

	/** Propiedad icap matriculas informacion. */
	private Collection<AreaPredio> icap_matriculasInformacion;

	/** Propiedad icdas detalles ant sistema. */
	private Collection<DetalleAntSistema> icdas_detallesAntSistema;

	/** Propiedad icms matriculas segregacion. */
	private Collection<MatriculaSegregacion> icms_matriculasSegregacion;

	/** Propiedad icrc all matriculas. */
	private Collection<RegistroCalificacion> icrc_allMatriculas;

	/** Propiedad icsc matriculas correccion. */
	private Collection<SolicitudMatricula> icsc_matriculasCorreccion;

	/** Propiedad iorc anotacion A cancelar nuevo. */
	private Collection<RegistroCalificacion> iorc_anotacionACancelarNuevo;

	/** Propiedad idas datos antiguo sistema. */
	private DatosAntSistema idas_datosAntiguoSistema;

	/** Propiedad idas detalle ant sistema. */
	private DetalleAntSistema idas_detalleAntSistema;

	/** Propiedad ipr direccion predio. */
	private DireccionPredio ipr_direccionPredio;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad ipda panel datos anotaciones. */
	private PanelDatosAnotacion ipda_panelDatosAnotaciones;

	/** Propiedad ipdd panel datos documento. */
	private PanelDatosDocumento ipdd_panelDatosDocumento;

	/** Propiedad ipdasa panel detalle ant sistema anotacion. */
	private PanelDetalleAntSistemaAnotacion ipdasa_panelDetalleAntSistemaAnotacion;

	/** Propiedad ipdi panel detalle intervinientes. */
	private PanelDetalleIntervinientes ipdi_panelDetalleIntervinientes;

	/** Propiedad ipe panel especificacion. */
	private PanelEspecificacion ipe_panelEspecificacion;

	/** Propiedad ipi panel intervinientes. */
	private PanelIntervinientes ipi_panelIntervinientes;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad ipr predio registro. */
	private PredioRegistro ipr_predioRegistro;

	/** Propiedad iso solicitud. */
	private Solicitud iso_solicitud;

	/** Propiedad is solicitud interviniente. */
	private SolicitudInterviniente is_solicitudInterviniente;

	/** Propiedad is copiar. */
	private String is_copiar;

	/** Propiedad is copiar seleccionadas. */
	private String is_copiarSeleccionadas;

	/** Propiedad is direccion. */
	private String is_direccion;

	/** Propiedad is justificacion. */
	private String is_justificacion;

	/** Propiedad is justificacion cambio. */
	private String is_justificacionCambio;

	/** Propiedad is motivo cambio estado. */
	private String is_motivoCambioEstado;

	/** Propiedad is naturaleza juridica. */
	private String is_naturalezaJuridica;

	/** Propiedad is salvedad. */
	private String is_salvedad;

	/** Propiedad ith turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/** Propiedad iba archivo generado. */
	private byte[] iba_archivoGenerado;

	/** Propiedad ib anotacion individual. */
	private boolean ib_anotacionIndividual;

	/** Propiedad ib bloqueo. */
	private boolean ib_bloqueo;

	/** Propiedad ib borrar. */
	private boolean ib_borrar;

	/** Propiedad ib cierre folio. */
	private boolean ib_cierreFolio;

	/** Propiedad ib correccion. */
	private boolean ib_correccion;

	/** Propiedad ib_deshabilitar. */
	private boolean ib_deshabilitar;

	/** Propiedad ib englobe terminado. */
	private boolean ib_englobeTerminado;

	/** Propiedad ib matricula segregada seleccionada. */
	private boolean ib_matriculaSegregadaSeleccionada;

	/** Propiedad ib matriculas aperturadas. */
	private boolean ib_matriculasAperturadas;

	/** Propiedad ib matriculas englobe seleccionadas. */
	private boolean ib_matriculasEnglobeSeleccionadas;

	/** Propiedad ib segregacion sasiva. */
	private boolean ib_segregacionMasiva;

	/** Propiedad ib temporal. */
	private boolean ib_temporal;

	/** Propiedad ii cantidad aperturar. */
	private int ii_cantidadAperturar;

	/** Propiedad il contador interviniente. */
	private long il_contadorInterviniente;

	/** Propiedad il id anotacion. */
	private long il_idAnotacion;

	/**
	 * Instancia un nuevo objeto anotacion.
	 */
	public Anotacion()
	{
	}

	/**
	 * Instancia un nuevo objeto anotacion.
	 *
	 * @param aa_a de aa a
	 */
	public Anotacion(Anotacion aa_a)
	{
		if(aa_a != null)
		{
			iapr_accPredioRegistro              = aa_a.getAccPredioRegistro();
			iac_anotacionCancelacion            = aa_a.getAnotacionCancelacion();
			iap_anotacionPredio                 = aa_a.getAnotacionPredio();
			iapc_anotacionPredioCiudadano       = new AnotacionPredioCiudadano(aa_a.getAnotacionPredioCiudadano());
			aca_intervinientesAgregados         = aa_a.getIntervinientesAgregados();
			ica_matriculasSegregadas            = aa_a.getMatriculasSegregadas();
			icaa_anotacionesAgregadas           = aa_a.getAnotacionesAgregadas();
			iorc_anotacionACancelarNuevo        = aa_a.getAnotacionACancelarNuevo();
			idas_datosAntiguoSistema            = aa_a.getDatosAntiguoSistema();
			ipr_direccionPredio                 = aa_a.getDireccionPredio();
			id_documento                        = aa_a.getDocumento();
			ip_persona                          = new Persona(aa_a.getPersona());
			ipr_predioRegistro                  = aa_a.getPredioRegistro();
			iso_solicitud                       = aa_a.getSolicitud();
			is_solicitudInterviniente           = new SolicitudInterviniente(aa_a.getSolicitudInterviniente());
			is_direccion                        = aa_a.getDireccion();
			is_naturalezaJuridica               = aa_a.getNaturalezaJuridica();
			ith_turnoHistoria                   = aa_a.getTurnoHistoria();
			iba_archivoGenerado                 = aa_a.getArchivoGenerado();
			il_idAnotacion                      = aa_a.getIdAnotacion();
			il_contadorInterviniente            = aa_a.getContadorInterviniente();
			ipdi_panelDetalleIntervinientes     = aa_a.getPanelDetalleIntervinientes();
		}
	}

	/**
	 * Modifica el valor de acc predio registro.
	 *
	 * @param aapr_apr asigna el valor a la propiedad acc predio registro
	 */
	public void setAccPredioRegistro(AccPredioRegistro aapr_apr)
	{
		iapr_accPredioRegistro = aapr_apr;
	}

	/**
	 * Retorna el valor de acc predio registro.
	 *
	 * @return el valor de acc predio registro
	 */
	public AccPredioRegistro getAccPredioRegistro()
	{
		return iapr_accPredioRegistro;
	}

	/**
	 * @param acrc_crc Modifica el valor de la propiedad allMatriculas
	 */
	public void setAllMatriculas(Collection<RegistroCalificacion> acrc_crc)
	{
		icrc_allMatriculas = acrc_crc;
	}

	/**
	 * @return Retorna el valor de la propiedad allMatriculas
	 */
	public Collection<RegistroCalificacion> getAllMatriculas()
	{
		return icrc_allMatriculas;
	}

	/**
	 * Modifica el valor de anotacion A cancelar nuevo.
	 *
	 * @param acrc_rc asigna el valor a la propiedad anotacion A cancelar nuevo
	 */
	public void setAnotacionACancelarNuevo(Collection<RegistroCalificacion> acrc_rc)
	{
		iorc_anotacionACancelarNuevo = acrc_rc;
	}

	/**
	 * Retorna el valor de anotacion A cancelar nuevo.
	 *
	 * @return el valor de anotacion A cancelar nuevo
	 */
	public Collection<RegistroCalificacion> getAnotacionACancelarNuevo()
	{
		return iorc_anotacionACancelarNuevo;
	}

	/**
	 * Modifica el valor de anotacion cancelacion.
	 *
	 * @param aac_ac asigna el valor a la propiedad anotacion cancelacion
	 */
	public void setAnotacionCancelacion(AnotacionCancelacion aac_ac)
	{
		iac_anotacionCancelacion = aac_ac;
	}

	/**
	 * Retorna el valor de anotacion cancelacion.
	 *
	 * @return el valor de anotacion cancelacion
	 */
	public AnotacionCancelacion getAnotacionCancelacion()
	{
		return iac_anotacionCancelacion;
	}

	/**
	 * Modifica el valor de anotacion individual.
	 *
	 * @param ab_b asigna el valor a la propiedad anotacion individual
	 */
	public void setAnotacionIndividual(boolean ab_b)
	{
		ib_anotacionIndividual = ab_b;
	}

	/**
	 * Valida la propiedad anotacion individual.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en anotacion individual
	 */
	public boolean isAnotacionIndividual()
	{
		return ib_anotacionIndividual;
	}

	/**
	 * Modifica el valor de anotacion predio.
	 *
	 * @param aap_ap asigna el valor a la propiedad anotacion predio
	 */
	public void setAnotacionPredio(AnotacionPredio aap_ap)
	{
		iap_anotacionPredio = aap_ap;
	}

	/**
	 * Retorna el valor de anotacion predio.
	 *
	 * @return el valor de anotacion predio
	 */
	public AnotacionPredio getAnotacionPredio()
	{
		return iap_anotacionPredio;
	}

	/**
	 * Modifica el valor de anotacion predio ciudadano.
	 *
	 * @param aapc_apc asigna el valor a la propiedad anotacion predio ciudadano
	 */
	public void setAnotacionPredioCiudadano(AnotacionPredioCiudadano aapc_apc)
	{
		iapc_anotacionPredioCiudadano = aapc_apc;
	}

	/**
	 * Retorna el valor de anotacion predio ciudadano.
	 *
	 * @return el valor de anotacion predio ciudadano
	 */
	public AnotacionPredioCiudadano getAnotacionPredioCiudadano()
	{
		return iapc_anotacionPredioCiudadano;
	}

	/**
	 * Modifica el valor de anotaciones agregadas.
	 *
	 * @param acaa_caa asigna el valor a la propiedad anotaciones agregadas
	 */
	public void setAnotacionesAgregadas(Collection<Anotacion> acaa_caa)
	{
		icaa_anotacionesAgregadas = acaa_caa;
	}

	/**
	 * Retorna el valor de anotaciones agregadas.
	 *
	 * @return el valor de anotaciones agregadas
	 */
	public Collection<Anotacion> getAnotacionesAgregadas()
	{
		return icaa_anotacionesAgregadas;
	}

	/**
	 * Modifica el valor de archivo generado.
	 *
	 * @param aba_ba asigna el valor a la propiedad archivo generado
	 */
	public void setArchivoGenerado(byte[] aba_ba)
	{
		iba_archivoGenerado = aba_ba;
	}

	/**
	 * Retorna el valor de archivo generado.
	 *
	 * @return el valor de archivo generado
	 */
	public byte[] getArchivoGenerado()
	{
		return iba_archivoGenerado;
	}

	/**
	 * Modifica el valor de bloqueo.
	 *
	 * @param ab_b asigna el valor a la propiedad bloqueo
	 */
	public void setBloqueo(boolean ab_b)
	{
		ib_bloqueo = ab_b;
	}

	/**
	 * Valida la propiedad bloqueo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en bloqueo
	 */
	public boolean isBloqueo()
	{
		return ib_bloqueo;
	}

	/**
	 * Modifica el valor de Borrar.
	 *
	 * @param ab_b de ab b
	 */
	public void setBorrar(boolean ab_b)
	{
		ib_borrar = ab_b;
	}

	/**
	 * Valida la propiedad borrar.
	 *
	 * @return Retorna el valor de la propiedad borrar
	 */
	public boolean isBorrar()
	{
		return ib_borrar;
	}

	/**
	 * @param Modifica el valor de la propiedad cantidadAperturar por cantidadAperturar
	 */
	public void setCantidadAperturar(int ai_i)
	{
		ii_cantidadAperturar = ai_i;
	}

	/**
	 * @return Retorna el valor de la propiedad cantidadAperturar
	 */
	public int getCantidadAperturar()
	{
		return ii_cantidadAperturar;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad cierreFolio
	 */
	public void setCierreFolio(boolean ab_b)
	{
		ib_cierreFolio = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad cierreFolio
	 */
	public boolean isCierreFolio()
	{
		return ib_cierreFolio;
	}

	/**
	 * Modifica el valor de contador interviniente.
	 *
	 * @param al_l asigna el valor a la propiedad contador interviniente
	 */
	public void setContadorInterviniente(long al_l)
	{
		il_contadorInterviniente = al_l;
	}

	/**
	 * Retorna el valor de contador interviniente.
	 *
	 * @return el valor de contador interviniente
	 */
	public long getContadorInterviniente()
	{
		return il_contadorInterviniente;
	}

	/**
	 * Modifica el valor de copiar.
	 *
	 * @param as_s asigna el valor a la propiedad copiar
	 */
	public void setCopiar(String as_s)
	{
		this.is_copiar = as_s;
	}

	/**
	 * Retorna el valor de copiar.
	 *
	 * @return el valor de copiar
	 */
	public String getCopiar()
	{
		return is_copiar;
	}

	/**
	 * Modifica el valor de copiar seleccionadas.
	 *
	 * @param as_s asigna el valor a la propiedad copiar seleccionadas
	 */
	public void setCopiarSeleccionadas(String as_s)
	{
		this.is_copiarSeleccionadas = as_s;
	}

	/**
	 * Retorna el valor de copiar seleccionadas.
	 *
	 * @return el valor de copiar seleccionadas
	 */
	public String getCopiarSeleccionadas()
	{
		return is_copiarSeleccionadas;
	}

	/**
	 * Modifica el valor de Correccion.
	 *
	 * @param ab_b de ab b
	 */
	public void setCorreccion(boolean ab_b)
	{
		ib_correccion = ab_b;
	}

	/**
	 * Valida la propiedad correccion.
	 *
	 * @return Retorna el valor de la propiedad correccion
	 */
	public boolean isCorreccion()
	{
		return ib_correccion;
	}

	/**
	 * Modifica el valor de datos antiguo sistema.
	 *
	 * @param adas_das asigna el valor a la propiedad datos antiguo sistema
	 */
	public void setDatosAntiguoSistema(DatosAntSistema adas_das)
	{
		idas_datosAntiguoSistema = adas_das;
	}

	/**
	 * Retorna el valor de datos antiguo sistema.
	 *
	 * @return el valor de datos antiguo sistema
	 */
	public DatosAntSistema getDatosAntiguoSistema()
	{
		return idas_datosAntiguoSistema;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param ab_b modifica el valor de la propiedad.
	 */
	public void setDeshabilitar(boolean ab_b)
	{
		ib_deshabilitar = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isDeshabilitar()
	{
		return ib_deshabilitar;
	}

	/**
	 * Modifica el valor de detalle ant sistema.
	 *
	 * @param adas_das asigna el valor a la propiedad detalle ant sistema
	 */
	public void setDetalleAntSistema(DetalleAntSistema adas_das)
	{
		idas_detalleAntSistema = adas_das;
	}

	/**
	 * Retorna el valor de detalle ant sistema.
	 *
	 * @return el valor de detalle ant sistema
	 */
	public DetalleAntSistema getDetalleAntSistema()
	{
		return idas_detalleAntSistema;
	}

	/**
	 * Modifica el valor de detalles ant sistema.
	 *
	 * @param acdas_cdas asigna el valor a la propiedad detalles ant sistema
	 */
	public void setDetallesAntSistema(Collection<DetalleAntSistema> acdas_cdas)
	{
		icdas_detallesAntSistema = acdas_cdas;
	}

	/**
	 * Retorna el valor de detalles ant sistema.
	 *
	 * @return el valor de detalles ant sistema
	 */
	public Collection<DetalleAntSistema> getDetallesAntSistema()
	{
		return icdas_detallesAntSistema;
	}

	/**
	 * Modifica el valor de direccion.
	 *
	 * @param as_s asigna el valor a la propiedad direccion
	 */
	public void setDireccion(String as_s)
	{
		is_direccion = as_s;
	}

	/**
	 * Retorna el valor de direccion.
	 *
	 * @return el valor de direccion
	 */
	public String getDireccion()
	{
		return is_direccion;
	}

	/**
	 * Modifica el valor de direccion predio.
	 *
	 * @param adp_dp asigna el valor a la propiedad direccion predio
	 */
	public void setDireccionPredio(DireccionPredio adp_dp)
	{
		ipr_direccionPredio = adp_dp;
	}

	/**
	 * Retorna el valor de direccion predio.
	 *
	 * @return el valor de direccion predio
	 */
	public DireccionPredio getDireccionPredio()
	{
		return ipr_direccionPredio;
	}

	/**
	 * Modifica el valor de documento.
	 *
	 * @param ad_d asigna el valor a la propiedad documento
	 */
	public void setDocumento(Documento ad_d)
	{
		id_documento = ad_d;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public Documento getDocumento()
	{
		return id_documento;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad englobeTerminado
	 */
	public void setEnglobeTerminado(boolean ab_b)
	{
		ib_englobeTerminado = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad englobeTerminado
	 */
	public boolean isEnglobeTerminado()
	{
		return ib_englobeTerminado;
	}

	/**
	 * Modifica el valor de id anotacion.
	 *
	 * @param al_l asigna el valor a la propiedad id anotacion
	 */
	public void setIdAnotacion(long al_l)
	{
		il_idAnotacion = al_l;
	}

	/**
	 * Retorna el valor de id anotacion.
	 *
	 * @return el valor de id anotacion
	 */
	public long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/**
	 * Modifica el valor de intervinientes agregados.
	 *
	 * @param aca_ca asigna el valor a la propiedad intervinientes agregados
	 */
	public void setIntervinientesAgregados(Collection<Anotacion> aca_ca)
	{
		aca_intervinientesAgregados = aca_ca;
	}

	/**
	 * Retorna el valor de intervinientes agregados.
	 *
	 * @return el valor de intervinientes agregados
	 */
	public Collection<Anotacion> getIntervinientesAgregados()
	{
		return aca_intervinientesAgregados;
	}

	/**
	 * Modifica el valor de justificacion.
	 *
	 * @param as_s asigna el valor a la propiedad justificacion
	 */
	public void setJustificacion(String as_s)
	{
		is_justificacion = as_s;
	}

	/**
	 * Retorna el valor de justificacion.
	 *
	 * @return el valor de justificacion
	 */
	public String getJustificacion()
	{
		return is_justificacion;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad justificacionCambio
	 */
	public void setJustificacionCambio(String as_s)
	{
		is_justificacionCambio = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad justificacionCambio
	 */
	public String getJustificacionCambio()
	{
		return is_justificacionCambio;
	}

	/**
	 * Modifica el valor de matricula segregada seleccionada.
	 *
	 * @param ab_b asigna el valor a la propiedad matricula segregada seleccionada
	 */
	public void setMatriculaSegregadaSeleccionada(boolean ab_b)
	{
		ib_matriculaSegregadaSeleccionada = ab_b;
	}

	/**
	 * Valida la propiedad matricula segregada seleccionada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en matricula segregada seleccionada
	 */
	public boolean isMatriculaSegregadaSeleccionada()
	{
		return ib_matriculaSegregadaSeleccionada;
	}

	/**
	 * Modifica el valor de MatriculasAperturadas.
	 *
	 * @param ab_b de ab b
	 */
	public void setMatriculasAperturadas(boolean ab_b)
	{
		ib_matriculasAperturadas = ab_b;
	}

	/**
	 * Valida la propiedad matriculas aperturadas.
	 *
	 * @return Retorna el valor de la propiedad matriculasAperturadas
	 */
	public boolean isMatriculasAperturadas()
	{
		return ib_matriculasAperturadas;
	}

	/**
	 * @param Modifica el valor de la propiedad matriculasCorreccion por matriculasCorreccion
	 */
	public void setMatriculasCorreccion(Collection<SolicitudMatricula> acsc_csc)
	{
		icsc_matriculasCorreccion = acsc_csc;
	}

	/**
	 * @return Retorna el valor de la propiedad matriculasCorreccion
	 */
	public Collection<SolicitudMatricula> getMatriculasCorreccion()
	{
		return icsc_matriculasCorreccion;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad matriculasEnglobeSeleccionadas
	 */
	public void setMatriculasEnglobeSeleccionadas(boolean ab_b)
	{
		ib_matriculasEnglobeSeleccionadas = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad matriculasEnglobeSeleccionadas
	 */
	public boolean isMatriculasEnglobeSeleccionadas()
	{
		return ib_matriculasEnglobeSeleccionadas;
	}

	/**
	 * Modifica el valor de matriculas informacion.
	 *
	 * @param acap_ap asigna el valor a la propiedad matriculas informacion
	 */
	public void setMatriculasInformacion(Collection<AreaPredio> acap_ap)
	{
		icap_matriculasInformacion = acap_ap;
	}

	/**
	 * Retorna el valor de matriculas informacion.
	 *
	 * @return el valor de matriculas informacion
	 */
	public Collection<AreaPredio> getMatriculasInformacion()
	{
		return icap_matriculasInformacion;
	}

	/**
	 * Modifica el valor de MatriculasSegregacion.
	 *
	 * @param acms_cms de acms cms
	 */
	public void setMatriculasSegregacion(Collection<MatriculaSegregacion> acms_cms)
	{
		icms_matriculasSegregacion = acms_cms;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas segregacion.
	 *
	 * @return Retorna el valor de la propiedad matriculasSegregacion
	 */
	public Collection<MatriculaSegregacion> getMatriculasSegregacion()
	{
		return icms_matriculasSegregacion;
	}

	/**
	 * Modifica el valor de matriculas segregadas.
	 *
	 * @param aca_ca asigna el valor a la propiedad matriculas segregadas
	 */
	public void setMatriculasSegregadas(Collection<Anotacion> aca_ca)
	{
		ica_matriculasSegregadas = aca_ca;
	}

	/**
	 * Retorna el valor de matriculas segregadas.
	 *
	 * @return el valor de matriculas segregadas
	 */
	public Collection<Anotacion> getMatriculasSegregadas()
	{
		return ica_matriculasSegregadas;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad motivoCambioEstado
	 */
	public void setMotivoCambioEstado(String as_s)
	{
		is_motivoCambioEstado = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad motivoCambioEstado
	 */
	public String getMotivoCambioEstado()
	{
		return is_motivoCambioEstado;
	}

	/**
	 * Modifica el valor de naturaleza juridica.
	 *
	 * @param as_s asigna el valor a la propiedad naturaleza juridica
	 */
	public void setNaturalezaJuridica(String as_s)
	{
		is_naturalezaJuridica = as_s;
	}

	/**
	 * Retorna el valor de naturaleza juridica.
	 *
	 * @return el valor de naturaleza juridica
	 */
	public String getNaturalezaJuridica()
	{
		return is_naturalezaJuridica;
	}

	/**
	 * Modifica el valor de orden.
	 *
	 * @param abd_bd asigna el valor a la propiedad orden
	 */
	public void setOrden(BigDecimal abd_bd)
	{
		ibd_orden = abd_bd;
	}

	/**
	 * Retorna el valor de orden.
	 *
	 * @return el valor de orden
	 */
	public BigDecimal getOrden()
	{
		return ibd_orden;
	}

	/**
	 * Modifica el valor de panel datos anotaciones.
	 *
	 * @param apda_pda asigna el valor a la propiedad panel datos anotaciones
	 */
	public void setPanelDatosAnotaciones(PanelDatosAnotacion apda_pda)
	{
		ipda_panelDatosAnotaciones = apda_pda;
	}

	/**
	 * Retorna el valor de panel datos anotaciones.
	 *
	 * @return el valor de panel datos anotaciones
	 */
	public PanelDatosAnotacion getPanelDatosAnotaciones()
	{
		if(ipda_panelDatosAnotaciones == null)
			ipda_panelDatosAnotaciones = new PanelDatosAnotacion();

		return ipda_panelDatosAnotaciones;
	}

	/**
	 * Modifica el valor de panel datos documento.
	 *
	 * @param apdd_pdd asigna el valor a la propiedad panel datos documento
	 */
	public void setPanelDatosDocumento(PanelDatosDocumento apdd_pdd)
	{
		ipdd_panelDatosDocumento = apdd_pdd;
	}

	/**
	 * Retorna el valor de panel datos documento.
	 *
	 * @return el valor de panel datos documento
	 */
	public PanelDatosDocumento getPanelDatosDocumento()
	{
		if(ipdd_panelDatosDocumento == null)
			ipdd_panelDatosDocumento = new PanelDatosDocumento();

		return ipdd_panelDatosDocumento;
	}

	/**
	 * Modifica el valor de panel detalle ant sistema anotacion.
	 *
	 * @param apdasa_pdasd asigna el valor a la propiedad panel detalle ant sistema anotacion
	 */
	public void setPanelDetalleAntSistemaAnotacion(PanelDetalleAntSistemaAnotacion apdasa_pdasd)
	{
		ipdasa_panelDetalleAntSistemaAnotacion = apdasa_pdasd;
	}

	/**
	 * Retorna el valor de panel detalle ant sistema anotacion.
	 *
	 * @return el valor de panel detalle ant sistema anotacion
	 */
	public PanelDetalleAntSistemaAnotacion getPanelDetalleAntSistemaAnotacion()
	{
		if(ipdasa_panelDetalleAntSistemaAnotacion == null)
			ipdasa_panelDetalleAntSistemaAnotacion = new PanelDetalleAntSistemaAnotacion();

		return ipdasa_panelDetalleAntSistemaAnotacion;
	}

	/**
	 * Modifica el valor de panel detalle intervinientes.
	 *
	 * @param apdi_pdi asigna el valor a la propiedad panel detalle intervinientes
	 */
	public void setPanelDetalleIntervinientes(PanelDetalleIntervinientes apdi_pdi)
	{
		ipdi_panelDetalleIntervinientes = apdi_pdi;
	}

	/**
	 * Retorna el valor de panel detalle intervinientes.
	 *
	 * @return el valor de panel detalle intervinientes
	 */
	public PanelDetalleIntervinientes getPanelDetalleIntervinientes()
	{
		if(ipdi_panelDetalleIntervinientes == null)
			ipdi_panelDetalleIntervinientes = new PanelDetalleIntervinientes();

		return ipdi_panelDetalleIntervinientes;
	}

	/**
	 * Modifica el valor de panel especificacion.
	 *
	 * @param ape_pe asigna el valor a la propiedad panel especificacion
	 */
	public void setPanelEspecificacion(PanelEspecificacion ape_pe)
	{
		ipe_panelEspecificacion = ape_pe;
	}

	/**
	 * Retorna el valor de panel especificacion.
	 *
	 * @return el valor de panel especificacion
	 */
	public PanelEspecificacion getPanelEspecificacion()
	{
		if(ipe_panelEspecificacion == null)
			ipe_panelEspecificacion = new PanelEspecificacion();

		return ipe_panelEspecificacion;
	}

	/**
	 * Modifica el valor de panel intervinientes.
	 *
	 * @param api_pi asigna el valor a la propiedad panel intervinientes
	 */
	public void setPanelIntervinientes(PanelIntervinientes api_pi)
	{
		ipi_panelIntervinientes = api_pi;
	}

	/**
	 * Retorna el valor de panel intervinientes.
	 *
	 * @return el valor de panel intervinientes
	 */
	public PanelIntervinientes getPanelIntervinientes()
	{
		if(ipi_panelIntervinientes == null)
			ipi_panelIntervinientes = new PanelIntervinientes();

		return ipi_panelIntervinientes;
	}

	/**
	 * Modifica el valor de persona.
	 *
	 * @param ap_p asigna el valor a la propiedad persona
	 */
	public void setPersona(Persona ap_p)
	{
		ip_persona = ap_p;
	}

	/**
	 * Retorna el valor de persona.
	 *
	 * @return el valor de persona
	 */
	public Persona getPersona()
	{
		return ip_persona;
	}

	/**
	 * Modifica el valor de predio registro.
	 *
	 * @param apr_pr asigna el valor a la propiedad predio registro
	 */
	public void setPredioRegistro(PredioRegistro apr_pr)
	{
		ipr_predioRegistro = apr_pr;
	}

	/**
	 * Retorna el valor de predio registro.
	 *
	 * @return el valor de predio registro
	 */
	public PredioRegistro getPredioRegistro()
	{
		return ipr_predioRegistro;
	}

	/**
	 * Modifica el valor de salvedad.
	 *
	 * @param as_s asigna el valor a la propiedad salvedad
	 */
	public void setSalvedad(String as_s)
	{
		is_salvedad = as_s;
	}

	/**
	 * Retorna el valor de salvedad.
	 *
	 * @return el valor de salvedad
	 */
	public String getSalvedad()
	{
		return is_salvedad;
	}

	/**
	 * @param Modifica el valor de la propiedad segregacionMasiva por segregacionMasiva
	 */
	public void setSegregacionMasiva(boolean ab_b)
	{
		ib_segregacionMasiva = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad segregacionMasiva
	 */
	public boolean isSegregacionMasiva()
	{
		return ib_segregacionMasiva;
	}

	/**
	 * Modifica el valor de solicitud.
	 *
	 * @param aso_so asigna el valor a la propiedad solicitud
	 */
	public void setSolicitud(Solicitud aso_so)
	{
		iso_solicitud = aso_so;
	}

	/**
	 * Retorna el valor de solicitud.
	 *
	 * @return el valor de solicitud
	 */
	public Solicitud getSolicitud()
	{
		return iso_solicitud;
	}

	/**
	 * Modifica el valor de solicitud interviniente.
	 *
	 * @param asi_si asigna el valor a la propiedad solicitud interviniente
	 */
	public void setSolicitudInterviniente(SolicitudInterviniente asi_si)
	{
		is_solicitudInterviniente = asi_si;
	}

	/**
	 * Retorna el valor de solicitud interviniente.
	 *
	 * @return el valor de solicitud interviniente
	 */
	public SolicitudInterviniente getSolicitudInterviniente()
	{
		return is_solicitudInterviniente;
	}

	/**
	 * Modifica el valor de Temporal.
	 *
	 * @param ab_b de ab b
	 */
	public void setTemporal(boolean ab_b)
	{
		ib_temporal = ab_b;
	}

	/**
	 * Valida la propiedad temporal.
	 *
	 * @return Retorna el valor de la propiedad temporal
	 */
	public boolean isTemporal()
	{
		return ib_temporal;
	}

	/**
	 * Modifica el valor de turno historia.
	 *
	 * @param ith_th asigna el valor a la propiedad turno historia
	 */
	public void setTurnoHistoria(TurnoHistoria ith_th)
	{
		ith_turnoHistoria = ith_th;
	}

	/**
	 * Retorna el valor de turno historia.
	 *
	 * @return el valor de turno historia
	 */
	public TurnoHistoria getTurnoHistoria()
	{
		return ith_turnoHistoria;
	}

	/** {@inheritdoc} */
	@Override
	public int compareTo(Anotacion aa_a)
	{
		int li_return;

		li_return = 0;

		if(aa_a != null)
			li_return = ComparatorUtils.compare(getIdAnotacion(), aa_a.getIdAnotacion());

		return li_return;
	}
}
