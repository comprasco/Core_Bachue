package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_OFICIOS_TEXTO.
 *
 * @author ccalderon
 */
public class OficiosTexto extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6065580266584886545L;

	/** Propiedad id fecha fin traslado. */
	private Date id_fechaFinTraslado;

	/** Propiedad id fecha final pantalla. */
	private Date id_fechaFinalPantalla;

	/** Propiedad id fecha inicial pantalla. */
	private Date id_fechaInicialPantalla;

	/** Propiedad id fecha inicio traslado. */
	private Date id_fechaInicioTraslado;

	/** Propiedad il dias suspension. */
	private Long il_diasSuspension;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad il numero anotaciones. */
	private Long il_numeroAnotaciones;

	/** Propiedad as dias letras. */
	private String as_diasLetras;

	/** Propiedad il id persona notificar. */
	private String il_idPersonaNotificar;

	/** Propiedad is analisis probatorio. */
	private String is_analisisProbatorio;

	/** Propiedad is antecedentes. */
	private String is_antecedentes;

	/** Propiedad is argumentos recurrente. */
	private String is_argumentosRecurrente;

	/** Propiedad is articulo. */
	private String is_articulo;

	/** Propiedad is consideracion. */
	private String is_consideracion;

	/** Propiedad is_consideracionSajr. */
	private String is_consideracionSajr;

	/** Propiedad is decisionTramiteRegistral. */
	private String is_decisionTramiteRegistral;

	/** Propiedad is encabezado. */
	private String is_encabezado;

	/** Propiedad is expediente. */
	private String is_expediente;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is intervencion interesados. */
	private String is_intervencionInteresados;

	/** Propiedad is pertinencia. */
	private String is_pertinencia;

	/** Propiedad is plantilla. */
	private String is_plantilla;

	/** Propiedad is pruebas recaudadas. */
	private String is_pruebasRecaudadas;

	/** Propiedad is razon 1. */
	private String is_razon1;

	/** Propiedad is razon 2. */
	private String is_razon2;

	/** Propiedad is referencia. */
	private String is_referencia;

	/** Propiedad is resuelve. */
	private String is_resuelve;

	/** Propiedad is tipo. */
	private String is_tipo;

	/** Propiedad ib_planeacion. */
	private boolean ib_planeacion;

	/** Propiedad ib_proyectar. */
	private boolean ib_proyectar;

	/** Propiedad ib segunda instancia. */
	private boolean ib_segundaInstancia;

	/** Propiedad il id oficio texto. */
	private long il_idOficioTexto;

	/**
	 * Modifica el valor de AnalisisProbatorio.
	 *
	 * @param as_s de as s
	 */
	public void setAnalisisProbatorio(String as_s)
	{
		is_analisisProbatorio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor analisis probatorio.
	 *
	 * @return Retorna el valor de la propiedad analisisProbatorio
	 */
	public String getAnalisisProbatorio()
	{
		return is_analisisProbatorio;
	}

	/**
	 * Modifica el valor de Antecedentes.
	 *
	 * @param as_s de as s
	 */
	public void setAntecedentes(String as_s)
	{
		is_antecedentes = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor antecedentes.
	 *
	 * @return Retorna el valor de la propiedad antecedentes
	 */
	public String getAntecedentes()
	{
		return is_antecedentes;
	}

	/**
	 * Modifica el valor de ArgumentosRecurrente.
	 *
	 * @param as_s de as s
	 */
	public void setArgumentosRecurrente(String as_s)
	{
		is_argumentosRecurrente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor argumentos recurrente.
	 *
	 * @return Retorna el valor de la propiedad
	 */
	public String getArgumentosRecurrente()
	{
		return is_argumentosRecurrente;
	}

	/**
	 * Modifica el valor de Articulo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setArticulo(String as_s)
	{
		is_articulo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor articulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getArticulo()
	{
		return is_articulo;
	}

	/**
	 * Modifica el valor de Consideracion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setConsideracion(String as_s)
	{
		is_consideracion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor consideracion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getConsideracion()
	{
		return is_consideracion;
	}

	/**
	 * Modifica el valor de ConsideracionSajr.
	 *
	 * @param as_s de as s
	 */
	public void setConsideracionSajr(String as_s)
	{
		is_consideracionSajr = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor consideracion sajr.
	 *
	 * @return Retorna el valor de la propiedad consideracionSajr
	 */
	public String getConsideracionSajr()
	{
		return is_consideracionSajr;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_decisionTramiteRegistral
	 */
	public void setDecisionTramiteRegistral(String as_s)
	{
		is_decisionTramiteRegistral = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_decisionTramiteRegistral
	 */
	public String getDecisionTramiteRegistral()
	{
		return is_decisionTramiteRegistral;
	}

	/**
	 * Modifica el valor de DiasLetras.
	 *
	 * @param as_s de as s
	 */
	public void setDiasLetras(String as_s)
	{
		as_diasLetras = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor dias letras.
	 *
	 * @return Retorna el valor de la propiedad
	 */
	public String getDiasLetras()
	{
		return as_diasLetras;
	}

	/**
	 * Modifica el valor de DiasSuspension.
	 *
	 * @param al_l de al l
	 */
	public void setDiasSuspension(Long al_l)
	{
		il_diasSuspension = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor dias suspension.
	 *
	 * @return Retorna el valor de la propiedad analisisProbatorio
	 */
	public Long getDiasSuspension()
	{
		return il_diasSuspension;
	}

	/**
	 * Modifica el valor de Encabezado.
	 *
	 * @param as_s de as s
	 */
	public void setEncabezado(String as_s)
	{
		is_encabezado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor encabezado.
	 *
	 * @return Retorna el valor de la propiedad encabezado
	 */
	public String getEncabezado()
	{
		return is_encabezado;
	}

	/**
	 * Modifica el valor de Expediente.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setExpediente(String as_s)
	{
		is_expediente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor expediente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getExpediente()
	{
		return is_expediente;
	}

	/**
	 * Modifica el valor de FechaFinTraslado.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaFinTraslado(Date ad_d)
	{
		id_fechaFinTraslado = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha fin traslado.
	 *
	 * @return Retorna el valor de la propiedad fechaFinTraslado
	 */
	public Date getFechaFinTraslado()
	{
		return id_fechaFinTraslado;
	}

	/**
	 * @param id_fechaFinalPantalla Modifica el valor de la propiedad id_fechaFinalPantalla
	 */
	public void setFechaFinalPantalla(Date ad_ffp)
	{
		id_fechaFinalPantalla = ad_ffp;
	}

	/**
	 * @return Retorna el valor de la propiedad id_fechaFinalPantalla
	 */
	public Date getFechaFinalPantalla()
	{
		return id_fechaFinalPantalla;
	}

	/**
	 * @param id_fechaInicialPantalla Modifica el valor de la propiedad id_fechaInicialPantalla
	 */
	public void setFechaInicialPantalla(Date ad_fip)
	{
		id_fechaInicialPantalla = ad_fip;
	}

	/**
	 * @return Retorna el valor de la propiedad id_fechaInicialPantalla
	 */
	public Date getFechaInicialPantalla()
	{
		return id_fechaInicialPantalla;
	}

	/**
	 * Modifica el valor de FechaInicioTraslado.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaInicioTraslado(Date ad_d)
	{
		id_fechaInicioTraslado = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha inicio traslado.
	 *
	 * @return Retorna el valor de la propiedad fechaInicioTraslado
	 */
	public Date getFechaInicioTraslado()
	{
		return id_fechaInicioTraslado;
	}

	/**
	 * Modifica el valor de IdOficioTexto.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdOficioTexto(long al_l)
	{
		il_idOficioTexto = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id oficio texto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdOficioTexto()
	{
		return il_idOficioTexto;
	}

	/**
	 * @param il_idPersonaNotificar Modifica el valor de la propiedad il_idPersonaNotificar
	 */
	public void setIdPersonaNotificar(String as_ipn)
	{
		il_idPersonaNotificar = as_ipn;
	}

	/**
	 * @return Retorna el valor de la propiedad il_idPersonaNotificar
	 */
	public String getIdPersonaNotificar()
	{
		return il_idPersonaNotificar;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s de as s
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de IntervencionInteresados.
	 *
	 * @param as_s de as s
	 */
	public void setIntervencionInteresados(String as_s)
	{
		is_intervencionInteresados = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor intervencion interesados.
	 *
	 * @return Retorna el valor de la propiedad intervencionInteresados
	 */
	public String getIntervencionInteresados()
	{
		return is_intervencionInteresados;
	}

	/**
	 * Modifica el valor de NumeroAnotaciones.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setNumeroAnotaciones(Long al_l)
	{
		il_numeroAnotaciones = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor numero anotaciones.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getNumeroAnotaciones()
	{
		return il_numeroAnotaciones;
	}

	/**
	 * Modifica el valor de Pertinencia.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPertinencia(String as_s)
	{
		is_pertinencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor pertinencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPertinencia()
	{
		return is_pertinencia;
	}

	/**
	 * Modifica el valor de Planeacion.
	 *
	 * @param ab_b de ab b
	 */
	public void setPlaneacion(boolean ab_b)
	{
		ib_planeacion = ab_b;
	}

	/**
	 * Valida la propiedad planeacion.
	 *
	 * @return Retorna el valor de la propiedad planeacion
	 */
	public boolean isPlaneacion()
	{
		return ib_planeacion;
	}

	/**
	 * Modifica el valor de Plantilla.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPlantilla(String as_s)
	{
		is_plantilla = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor plantilla.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPlantilla()
	{
		return is_plantilla;
	}

	/**
	 * Modifica el valor de Proyectar.
	 *
	 * @param ab_b de ab b
	 */
	public void setProyectar(boolean ab_b)
	{
		ib_proyectar = ab_b;
	}

	/**
	 * Valida la propiedad proyectar.
	 *
	 * @return Retorna el valor de la propiedad proyectar
	 */
	public boolean isProyectar()
	{
		return ib_proyectar;
	}

	/**
	 * Modifica el valor de PruebasRecaudadas.
	 *
	 * @param as_s de as s
	 */
	public void setPruebasRecaudadas(String as_s)
	{
		is_pruebasRecaudadas = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor pruebas recaudadas.
	 *
	 * @return Retorna el valor de la propiedad pruebasRecaudadas
	 */
	public String getPruebasRecaudadas()
	{
		return is_pruebasRecaudadas;
	}

	/**
	 * Modifica el valor de Razon1.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRazon1(String as_s)
	{
		is_razon1 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor razon 1.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRazon1()
	{
		return is_razon1;
	}

	/**
	 * Modifica el valor de Razon2.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRazon2(String as_s)
	{
		is_razon2 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor razon 2.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRazon2()
	{
		return is_razon2;
	}

	/**
	 * Modifica el valor de Referencia.
	 *
	 * @param as_s de as s
	 */
	public void setReferencia(String as_s)
	{
		is_referencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor referencia.
	 *
	 * @return Retorna el valor de la propiedad referencia
	 */
	public String getReferencia()
	{
		return is_referencia;
	}

	/**
	 * Modifica el valor de Resuelve.
	 *
	 * @param as_s de as s
	 */
	public void setResuelve(String as_s)
	{
		is_resuelve = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor resuelve.
	 *
	 * @return Retorna el valor de la propiedad
	 */
	public String getResuelve()
	{
		return is_resuelve;
	}

	/**
	 * Modifica el valor de SegundaInstancia.
	 *
	 * @param ab_b de ab b
	 */
	public void setSegundaInstancia(boolean ab_b)
	{
		ib_segundaInstancia = ab_b;
	}

	/**
	 * Valida la propiedad segunda instancia.
	 *
	 * @return Retorna el valor de la propiedad segundaInstancia
	 */
	public boolean isSegundaInstancia()
	{
		return ib_segundaInstancia;
	}

	/**
	 * Modifica el valor de Tipo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipo(String as_s)
	{
		is_tipo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipo()
	{
		return is_tipo;
	}
}
