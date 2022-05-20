package com.bachue.snr.prosnr01.model.calificacion;

import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import java.io.Serializable;

import java.util.Collection;


/**
 * Class que contiene todos las propiedades Suspension.
 *
 * @author jpatino
 */
public class Suspension extends Calificacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4646686184330514789L;

	/** Constante ART_18. */
	public static final String ART_18 = "ART_18";

	/** Constante ART_19. */
	public static final String ART_19 = "ART_19";

	/** Propiedad actd tipos documentales. */
	private Collection<TipoDocumental> actd_tiposDocumentales;

	/** Propiedad personas. */
	private Collection<Persona> personas;

	/** Propiedad iddi datos del interesado. */
	private DatosDelInteresado iddi_datosDelInteresado;

	/** Propiedad il anotaciones. */
	private Long il_anotaciones;

	/** Propiedad is_oficiosTexto */
	private OficiosTexto is_oficiosTexto;

	/** Propiedad is_oficiosTexto2 */
	private OficiosTexto is_oficiosTexto2;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad is solicitud 1. */
	private Solicitud is_solicitud1;

	/** Propiedad is solicitud 2. */
	private Solicitud is_solicitud2;

	/** Propiedad is antecedentes. */
	private String is_antecedentes;

	/** Propiedad is articulo. */
	private String is_articulo;

	/** Propiedad is consideracion. */
	private String is_consideracion;

	/** Propiedad is id causal negacion copias. */
	private String is_idCausalNegacionCopias;

	/** Propiedad is observaciones proceso anterior. */
	private String is_observacionesProcesoAnterior;

	/** Propiedad is pertinencia. */
	private String is_pertinencia;

	/** Propiedad is proceso. */
	private String is_proceso;

	/** Propiedad is razon 1. */
	private String is_razon1;

	/** Propiedad is razon 2. */
	private String is_razon2;

	/** Propiedad is razon A negar. */
	private String is_razonANegar;

	/** Propiedad is respuesta desistimiento. */
	private String is_respuestaDesistimiento;

	/** Propiedad is resuelve. */
	private String is_resuelve;

	/** Propiedad is tipo. */
	private String is_tipo;

	/** Propiedad is usuario. */
	private String is_usuario;

	/** Propiedad it turno. */
	private Turno it_turno;

	/** Propiedad ith turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/** Propiedad iba comunicado PDF. */
	private byte[] iba_comunicadoPDF;

	/** Propiedad iba resolucion. */
	private byte[] iba_resolucionPDF;

	/** Propiedad iba zip pdf. */
	private byte[] iba_zipPdf;

	/** Propiedad ib es analista copias. */
	private boolean ib_esAnalistaCopias;

	/** Propiedad ib es negar solicitud de certificados. */
	private boolean ib_esNegarSolicitudCertificados;

	/** Propiedad ib es persona entidad juridica. */
	private boolean ib_esPersonaEntidadJuridica;

	/** Propiedad ib es procede no correccion. */
	private boolean ib_esProcedeNoCorreccion;

	/** Propiedad ib es proceso 45. */
	private boolean ib_esProceso45;

	/** Propiedad ib es solicitud documentos. */
	private boolean ib_esSolicitudDocumentos;

	/** Propiedad ib es suspension solicitud documentos. */
	private boolean ib_esSuspensionSolicitudDocumentos;

	/** Propiedad ib negacion traslados. */
	private boolean ib_negacionTraslados;

	/** Propiedad ib proyectar resolucion aceptacion. */
	private boolean ib_proyectarResolucionAceptacion;

	/** Propiedad il etapa. */
	private long il_etapa;

	/**  Propiedad il id comunicado. */
	private long il_idComunicado;

	/** Propiedad il id resolucion. */
	private long il_idResolucion;

	/** Propiedad il motivo tramite. */
	private long il_motivoTramite;

	/**
	 * Modifica el valor de anotaciones.
	 *
	 * @param al_l asigna el valor a la propiedad anotaciones
	 */
	public void setAnotaciones(Long al_l)
	{
		il_anotaciones = al_l;
	}

	/**
	 * Retorna el valor de anotaciones.
	 *
	 * @return el valor de anotaciones
	 */
	public Long getAnotaciones()
	{
		return il_anotaciones;
	}

	/**
	 * @param Modifica el valor de la propiedad antecedentes por antecedentes
	 */
	public void setAntecedentes(String as_s)
	{
		is_antecedentes = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad antecedentes
	 */
	public String getAntecedentes()
	{
		return is_antecedentes;
	}

	/**
	 * Modifica el valor de articulo.
	 *
	 * @param as_s asigna el valor a la propiedad articulo
	 */
	public void setArticulo(String as_s)
	{
		is_articulo = as_s;
	}

	/**
	 * Retorna el valor de articulo.
	 *
	 * @return el valor de articulo
	 */
	public String getArticulo()
	{
		return is_articulo;
	}

	/**
	 * Modifica el valor de comunicado PDF.
	 *
	 * @param ab_b asigna el valor a la propiedad comunicado PDF.
	 */
	public void setComunicadoPDF(byte[] ab_b)
	{
		iba_comunicadoPDF = ab_b;
	}

	/**
	 * Retorna el valor de comunicado PDF.
	 *
	 * @return el valor de comunicado PDF.
	 */
	public byte[] getComunicadoPDF()
	{
		return iba_comunicadoPDF;
	}

	/**
	 * Modifica el valor de consideracion.
	 *
	 * @param as_s asigna el valor a la propiedad consideracion
	 */
	public void setConsideracion(String as_s)
	{
		is_consideracion = as_s;
	}

	/**
	 * Retorna el valor de consideracion.
	 *
	 * @return el valor de consideracion
	 */
	public String getConsideracion()
	{
		return is_consideracion;
	}

	/**
	 * Modifica el valor de datos del interesado.
	 *
	 * @param addi_ddi asigna el valor a la propiedad datos del interesado
	 */
	public void setDatosDelInteresado(DatosDelInteresado addi_ddi)
	{
		iddi_datosDelInteresado = addi_ddi;
	}

	/**
	 * Retorna el valor de datos del interesado.
	 *
	 * @return el valor de datos del interesado
	 */
	public DatosDelInteresado getDatosDelInteresado()
	{
		if(iddi_datosDelInteresado == null)
			iddi_datosDelInteresado = new DatosDelInteresado();

		return iddi_datosDelInteresado;
	}

	/**
	 * Modifica el valor de es analista copias.
	 *
	 * @param ab_b asigna el valor a la propiedad es analista copias.
	 */
	public void setEsAnalistaCopias(boolean ab_b)
	{
		ib_esAnalistaCopias = ab_b;
	}

	/**
	 * Valida la propiedad es analista copias.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es analista copias.
	 */
	public boolean isEsAnalistaCopias()
	{
		return ib_esAnalistaCopias;
	}

	/**
	 * Modifica el valor de EsNegarSolicitudCertificados.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsNegarSolicitudCertificados(boolean ab_b)
	{
		ib_esNegarSolicitudCertificados = ab_b;
	}

	/**
	 * Valida la propiedad es negar solicitud certificados.
	 *
	 * @return Retorna el valor de la propiedad esNegarSolicitudCertificados
	 */
	public boolean isEsNegarSolicitudCertificados()
	{
		return ib_esNegarSolicitudCertificados;
	}

	/**
	 * Modifica el valor de es persona entidad juridica.
	 *
	 * @param ab_b asigna el valor a la propiedad es persona entidad juridica
	 */
	public void setEsPersonaEntidadJuridica(boolean ab_b)
	{
		ib_esPersonaEntidadJuridica = ab_b;
	}

	/**
	 * Valida la propiedad es persona entidad juridica.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es persona entidad juridica
	 */
	public boolean isEsPersonaEntidadJuridica()
	{
		return ib_esPersonaEntidadJuridica;
	}

	/**
	 * Modifica el valor de es procede no correccion.
	 *
	 * @param ab_b asigna el valor a la propiedad es procede no correccion
	 */
	public void setEsProcedeNoCorreccion(boolean ab_b)
	{
		ib_esProcedeNoCorreccion = ab_b;
	}

	/**
	 * Valida la propiedad es procede no correccion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es procede no correccion
	 */
	public boolean isEsProcedeNoCorreccion()
	{
		return ib_esProcedeNoCorreccion;
	}

	/**
	 * Modifica el valor de es proceso 45.
	 *
	 * @param ab_b asigna el valor a la propiedad es proceso 45
	 */
	public void setEsProceso45(boolean ab_b)
	{
		ib_esProceso45 = ab_b;
	}

	/**
	 * Valida la propiedad es proceso 45.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es proceso 45.
	 */
	public boolean isEsProceso45()
	{
		return ib_esProceso45;
	}

	/**
	 * Modifica el valor de es solicitud documentos.
	 *
	 * @param ab_b asigna el valor a la propiedad es solicitud documentos
	 */
	public void setEsSolicitudDocumentos(boolean ab_b)
	{
		ib_esSolicitudDocumentos = ab_b;
	}

	/**
	 * Valida la propiedad es solicitud documentos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es solicitud documentos
	 */
	public boolean isEsSolicitudDocumentos()
	{
		return ib_esSolicitudDocumentos;
	}

	/**
	 * Modifica el valor de es suspension solicitud documentos.
	 *
	 * @param ab_b asigna el valor a la propiedad es suspension solicitud documentos
	 */
	public void setEsSuspensionSolicitudDocumentos(boolean ab_b)
	{
		ib_esSuspensionSolicitudDocumentos = ab_b;
	}

	/**
	 * Valida la propiedad es suspension solicitud documentos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es suspension solicitud documentos
	 */
	public boolean isEsSuspensionSolicitudDocumentos()
	{
		return ib_esSuspensionSolicitudDocumentos;
	}

	/**
	 * Modifica el valor de etapa.
	 *
	 * @param al_l asigna el valor a la propiedad etapa
	 */
	public void setEtapa(long al_l)
	{
		il_etapa = al_l;
	}

	/**
	 * Retorna el valor de etapa.
	 *
	 * @return el valor de etapa
	 */
	public long getEtapa()
	{
		return il_etapa;
	}

	/**
	 * Modifica el valor de IdCausalNegacionCopias.
	 *
	 * @param as_s de as s
	 */
	public void setIdCausalNegacionCopias(String as_s)
	{
		is_idCausalNegacionCopias = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id causal negacion copias.
	 *
	 * @return Retorna el valor de la propiedad idCausalNegacionCopias
	 */
	public String getIdCausalNegacionCopias()
	{
		return is_idCausalNegacionCopias;
	}

	/**
	 * Modifica el valor de id comunicado.
	 *
	 * @param al_l asigna el valor a la propiedad id comunicado.
	 */
	public void setIdComunicado(long al_l)
	{
		il_idComunicado = al_l;
	}

	/**
	 * Retorna el valor de id comunicado.
	 *
	 * @return el valor de id comunicado.
	 */
	public long getIdComunicado()
	{
		return il_idComunicado;
	}

	/**
	 * Modifica el valor de IdResolucion.
	 *
	 * @param al_l de al l
	 */
	public void setIdResolucion(long al_l)
	{
		il_idResolucion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id resolucion.
	 *
	 * @return el valor de id resolucion
	 */
	public long getIdResolucion()
	{
		return il_idResolucion;
	}

	/**
	 * Modifica el valor de motivo tramite.
	 *
	 * @param al_l asigna el valor a la propiedad motivo tramite
	 */
	public void setMotivoTramite(long al_l)
	{
		il_motivoTramite = al_l;
	}

	/**
	 * Retorna el valor de motivo tramite.
	 *
	 * @return el valor de motivo tramite
	 */
	public long getMotivoTramite()
	{
		return il_motivoTramite;
	}

	/**
	 * @param Modifica el valor de la propiedad negacionTraslados por negacionTraslados
	 */
	public void setNegacionTraslados(boolean ab_b)
	{
		ib_negacionTraslados = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad negacionTraslados
	 */
	public boolean isNegacionTraslados()
	{
		return ib_negacionTraslados;
	}

	/**
	 * Modifica el valor de observaciones proceso anterior.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones proceso anterior
	 */
	public void setObservacionesProcesoAnterior(String as_s)
	{
		is_observacionesProcesoAnterior = as_s;
	}

	/**
	 * Retorna el valor de observaciones proceso anterior.
	 *
	 * @return el valor de observaciones proceso anterior
	 */
	public String getObservacionesProcesoAnterior()
	{
		return is_observacionesProcesoAnterior;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setOficiosTexto(OficiosTexto as_s)
	{
		is_oficiosTexto = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public OficiosTexto getOficiosTexto()
	{
		if(is_oficiosTexto == null)
			is_oficiosTexto = new OficiosTexto();

		return is_oficiosTexto;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setOficiosTexto2(OficiosTexto as_s)
	{
		is_oficiosTexto2 = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public OficiosTexto getOficiosTexto2()
	{
		if(is_oficiosTexto2 == null)
			is_oficiosTexto2 = new OficiosTexto();

		return is_oficiosTexto2;
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
	 * Modifica el valor de personas.
	 *
	 * @param acp_cp asigna el valor a la propiedad personas
	 */
	public void setPersonas(Collection<Persona> acp_cp)
	{
		personas = acp_cp;
	}

	/**
	 * Retorna el valor de personas.
	 *
	 * @return el valor de personas
	 */
	public Collection<Persona> getPersonas()
	{
		return personas;
	}

	/**
	 * Modifica el valor de pertinencia.
	 *
	 * @param as_s asigna el valor a la propiedad pertinencia
	 */
	public void setPertinencia(String as_s)
	{
		is_pertinencia = as_s;
	}

	/**
	 * Retorna el valor de pertinencia.
	 *
	 * @return el valor de pertinencia
	 */
	public String getPertinencia()
	{
		return is_pertinencia;
	}

	/**
	 * Modifica el valor de proceso.
	 *
	 * @param as_s asigna el valor a la propiedad proceso
	 */
	public void setProceso(String as_s)
	{
		is_proceso = as_s;
	}

	/**
	 * Retorna el valor de proceso.
	 *
	 * @return el valor de proceso
	 */
	public String getProceso()
	{
		return is_proceso;
	}

	/**
	 * @param Modifica el valor de la propiedad proyectarResolucionAceptacion por proyectarResolucionAceptacion
	 */
	public void setProyectarResolucionAceptacion(boolean ab_b)
	{
		ib_proyectarResolucionAceptacion = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad proyectarResolucionAceptacion
	 */
	public boolean isProyectarResolucionAceptacion()
	{
		return ib_proyectarResolucionAceptacion;
	}

	/**
	 * Modifica el valor de razon 1.
	 *
	 * @param as_s asigna el valor a la propiedad razon 1
	 */
	public void setRazon1(String as_s)
	{
		is_razon1 = as_s;
	}

	/**
	 * Retorna el valor de razon 1.
	 *
	 * @return el valor de razon 1
	 */
	public String getRazon1()
	{
		return is_razon1;
	}

	/**
	 * Modifica el valor de razon 2.
	 *
	 * @param as_s asigna el valor a la propiedad razon 2
	 */
	public void setRazon2(String as_s)
	{
		is_razon2 = as_s;
	}

	/**
	 * Retorna el valor de razon 2.
	 *
	 * @return el valor de razon 2
	 */
	public String getRazon2()
	{
		return is_razon2;
	}

	/**
	 * Modifica el valor de razon A negar.
	 *
	 * @param as_s asigna el valor a la propiedad razon A negar
	 */
	public void setRazonANegar(String as_s)
	{
		is_razonANegar = as_s;
	}

	/**
	 * Retorna el valor de razon A negar.
	 *
	 * @return el valor de razon A negar
	 */
	public String getRazonANegar()
	{
		return is_razonANegar;
	}

	/**
	 * Modifica el valor de resolucion PDF.
	 *
	 * @param ab_b de ab b
	 */
	public void setResolucionPDF(byte[] ab_b)
	{
		iba_resolucionPDF = ab_b;
	}

	/**
	 * Retorna el valor de resolucion PDF.
	 *
	 * @return el valor de resolucion PDF.
	 */
	public byte[] getResolucionPDF()
	{
		return iba_resolucionPDF;
	}

	/**
	 * Modifica el valor de respuesta desistimiento.
	 *
	 * @param as_s asigna el valor a la propiedad respuesta desistimiento
	 */
	public void setRespuestaDesistimiento(String as_s)
	{
		is_respuestaDesistimiento = as_s;
	}

	/**
	 * Retorna el valor de respuesta desistimiento.
	 *
	 * @return el valor de respuesta desistimiento
	 */
	public String getRespuestaDesistimiento()
	{
		return is_respuestaDesistimiento;
	}

	/**
	 * @param Modifica el valor de la propiedad resuelve por resuelve
	 */
	public void setResuelve(String as_s)
	{
		is_resuelve = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad resuelve
	 */
	public String getResuelve()
	{
		return is_resuelve;
	}

	/**
	 * Modifica el valor de solicitud 1.
	 *
	 * @param as_s asigna el valor a la propiedad solicitud 1
	 */
	public void setSolicitud1(Solicitud as_s)
	{
		is_solicitud1 = as_s;
	}

	/**
	 * Retorna el valor de solicitud 1.
	 *
	 * @return el valor de solicitud 1
	 */
	public Solicitud getSolicitud1()
	{
		return is_solicitud1;
	}

	/**
	 * Modifica el valor de solicitud 2.
	 *
	 * @param as_s asigna el valor a la propiedad solicitud 2
	 */
	public void setSolicitud2(Solicitud as_s)
	{
		is_solicitud2 = as_s;
	}

	/**
	 * Retorna el valor de solicitud 2.
	 *
	 * @return el valor de solicitud 2
	 */
	public Solicitud getSolicitud2()
	{
		return is_solicitud2;
	}

	/**
	 * Modifica el valor de tipo.
	 *
	 * @param as_s asigna el valor a la propiedad tipo
	 */
	public void setTipo(String as_s)
	{
		is_tipo = as_s;
	}

	/**
	 * Retorna el valor de tipo.
	 *
	 * @return el valor de tipo
	 */
	public String getTipo()
	{
		return is_tipo;
	}

	/**
	 * Modifica el valor de tipos documentales.
	 *
	 * @param ac_c asigna el valor a la propiedad tipos documentales
	 */
	public void setTiposDocumentales(Collection<TipoDocumental> ac_c)
	{
		actd_tiposDocumentales = ac_c;
	}

	/**
	 * Retorna el valor de tipos documentales.
	 *
	 * @return el valor de tipos documentales
	 */
	public Collection<TipoDocumental> getTiposDocumentales()
	{
		return actd_tiposDocumentales;
	}

	/**
	 * Modifica el valor de turno.
	 *
	 * @param at_t asigna el valor a la propiedad turno
	 */
	public void setTurno(Turno at_t)
	{
		it_turno = at_t;
	}

	/**
	 * Retorna el valor de turno.
	 *
	 * @return el valor de turno
	 */
	public Turno getTurno()
	{
		return it_turno;
	}

	/**
	 * Modifica el valor de turno historia.
	 *
	 * @param ath_th asigna el valor a la propiedad turno historia
	 */
	public void setTurnoHistoria(TurnoHistoria ath_th)
	{
		ith_turnoHistoria = ath_th;
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

	/**
	 * Modifica el valor de usuario.
	 *
	 * @param as_s asigna el valor a la propiedad usuario
	 */
	public void setUsuario(String as_s)
	{
		is_usuario = as_s;
	}

	/**
	 * Retorna el valor de usuario.
	 *
	 * @return el valor de usuario
	 */
	public String getUsuario()
	{
		return is_usuario;
	}

	/**
	 * Modifica el valor de zip pdf.
	 *
	 * @param aba_ba asigna el valor a la propiedad zip pdf
	 */
	public void setZipPdf(byte[] aba_ba)
	{
		iba_zipPdf = aba_ba;
	}

	/**
	 * Retorna el valor de zip pdf.
	 *
	 * @return el valor de zip pdf
	 */
	public byte[] getZipPdf()
	{
		return iba_zipPdf;
	}
}
