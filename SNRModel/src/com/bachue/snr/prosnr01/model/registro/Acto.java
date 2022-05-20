package com.bachue.snr.prosnr01.model.registro;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Date;


/**
 * Clase que contiene todos las propiedades Acto.
 *
 * @author hcastaneda
 */
public class Acto extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4918368075222303718L;

	/** Propiedad id cuantia acto. */
	private BigDecimal id_cuantiaActo;

	/** Propiedad id valor avaluo. */
	private BigDecimal id_valorAvaluo;

	/**  Propiedad ics datos periodicidad cuantia. */
	private Collection<String> ics_datosPeriodicidadCuantia;

	/** Propiedad ictd tipo documental. */
	private Collection<TipoDocumental> ictd_tipoDocumental;

	/**  Propiedad id fecha ejecutoria. */
	private Date id_fechaEjecutoria;

	/**  Propiedad id fecha pago impuesto extemporaneo. */
	private Date id_fechaPagoImpuesto;

	/**  Propiedad id fecha pago impuesto extemporaneo. */
	private Date id_fechaPagoImpuestoExtemporaneo;

	/** Propiedad id fecha vencimiento. */
	private Date id_fechaVencimiento;

	/** Propiedad id area acto. */
	private Double id_areaActo;

	/** Propiedad id area total inmueble. */
	private Double id_areaTotalInmueble;

	/** Propiedad id area transferir. */
	private Double id_areaTransferir;

	/** Propiedad id porcentaje transferencia. */
	private Double id_porcentajeTransferencia;

	/** Propiedad is area total. */
	private Double is_areaTotal;

	/** Propiedad ii cantidad actos. */
	private Integer ii_cantidadActos;

	/** Propiedad ii cantidad matriculas. */
	private Integer ii_cantidadMatriculas;

	/** Propiedad ii cantidad matriculas inclr. */
	private Integer ii_cantidadMatriculasInclr;

	/** Propiedad il cantidad certif ant sistema. */
	private Long il_cantidadCertifAntSistema;

	/** Propiedad il cuantia. */
	private Long il_cuantia;

	/** Propiedad il departamento. */
	private Long il_departamento;

	/** Propiedad il municipio. */
	private Long il_municipio;

	/** Propiedad il tiempo. */
	private Long il_tiempo;

	/**  Propiedad il tiempo canon. */
	private Long il_tiempoCanon;

	/** Propiedad is activo precalificacion. */
	private String is_activoPrecalificacion;

	/** Propiedad is acto con cuantia. */
	private String is_actoConCuantia;

	/** Propiedad is acto sin cuantia. */
	private String is_actoSinCuantia;

	/** Propiedad is apertura matricula. */
	private String is_aperturaMatricula;

	/** Propiedad is avaluo. */
	private String is_avaluo;

	/** Propiedad is circulo registral. */
	private String is_circuloRegistral;

	/** Propiedad is codigo. */
	private String is_codigo;

	/** Propiedad is ejecutora. */
	private String is_ejecutora;

	/** Propiedad is especificacion. */
	private String is_especificacion;

	/** Propiedad is garantia hipotecaria. */
	private String is_garantiaHipotecaria;

	/** Propiedad is genera segregacion. */
	private String is_generaSegregacion;

	/** Propiedad is hijuela pasivo. */
	private String is_hijuelaPasivo;

	/** Propiedad is id acto db. */
	private String is_idActoDb;

	/** Propiedad is id acto derivado. */
	private String is_idActoDerivado;

	/** Propiedad is id acto principal. */
	private String is_idActoPrincipal;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id solicitud asociada. */
	private String is_idSolicitudAsociada;

	/** Propiedad is id sub proceso. */
	private String is_idSubProceso;

	/** Propiedad is id tipoTarifa registral. */
	private String is_idTipoTarifaRegistral;

	/** Propiedad is impuesto registro. */
	private String is_impuestoRegistro;

	/** Propiedad is impuesto registro extemporaneo. */
	private String is_impuestoRegistroExtemporaneo;

	/** Propiedad is ind mayor valor. */
	private String is_indMayorValor;

	/** Propiedad is inscripcion adicional. */
	private String is_inscripcionAdicional;

	/** Propiedad is madre cabeza. */
	private String is_madreCabeza;

	/** Propiedad is nombre TipoAdquisicion. */
	private String is_nombreTipoAdquisicion;

	/** Propiedad is numero proceso. */
	private String is_numeroProceso;

	/** Propiedad is organismo internacional. */
	private String is_organismoInternacional;

	/**  Propiedad is periodicidad cuantia. */
	private String is_periodicidadCuantia;

	/** Propiedad is pregunta1943. */
	private String is_pregunta1943;

	/** Propiedad is referencia. */
	private String is_referencia;

	/** Propiedad is requiere tipo adquisicion. */
	private String is_requiereTipoAdquisicion;

	/** Propiedad id cuantia acto. */
	private String is_respuestaLey1943;

	/** Propiedad is secuence. */
	private String is_secuence;

	/** Propiedad is sumatoria area. */
	private String is_sumatoriaArea;

	/** Propiedad is tipo adquisicion. */
	private String is_tipoAdquisicion;

	/** Propiedad is tipo tarifa. */
	private String is_tipoTarifa;

	/** Propiedad is user id. */
	private String is_userId;

	/** Propiedad ib activa fecha ejecutoria. */
	private boolean ib_activaFechaEjecutoria;

	/**  Propiedad ib_areaActoModificada. */
	private boolean ib_areaActoModificada;

	/**  Propiedad ib_areaTotalInmuebleModificada. */
	private boolean ib_areaTotalInmuebleModificada;

	/**  Propiedad ib_areaTotalModificada. */
	private boolean ib_areaTotalModificada;

	/**  Propiedad ib_areaTransferirModificada. */
	private boolean ib_areaTransferirModificada;

	/**  Propiedad ib_cantidadCertAntSistemaModificada. */
	private boolean ib_cantidadCertAntSistemaModificada;

	/** Propiedad ib cantidad modificada. */
	private boolean ib_cantidadModificada;

	/**  Propiedad ib cuantia modificada. */
	private boolean ib_cuantiaModificada;

	/** Propiedad ib es cambio tipo cuantia. */
	private boolean ib_esCambioTipoCuantia;

	/**  Propiedad ib_garantiaHipotecariaModificada. */
	private boolean ib_garantiaHipotecariaModificada;

	/**  Propiedad ib_hijuelaPasivoModificada. */
	private boolean ib_hijuelaPasivoModificada;

	/**  Propiedad ib_idTipoAdquisicionModificada. */
	private boolean ib_idTipoAdquisicionModificada;

	/**  Propiedad ib_idTipoTarifaRegistralModificada. */
	private boolean ib_idTipoTarifaRegistralModificada;

	/** Propiedad ib impuesto registro ext show. */
	private boolean ib_impuestoRegistroExtShow;

	/** Propiedad ib impuesto registro show. */
	private boolean ib_impuestoRegistroShow;

	/**  Propiedad ib_madreCabezaModificada. */
	private boolean ib_madreCabezaModificada;

	/** Propiedad ib matricula liquidacion. */
	private boolean ib_matriculaLiquidacion;

	/** Propiedad ib no requiere cuantia acto. */
	private boolean ib_noRequiereCuantiaActo;

	/** Propiedad ib nueva entrada. */
	private boolean ib_nuevaEntrada;

	/**  Propiedad ib_organismoInternacionalModificada. */
	private boolean ib_organismoInternacionalModificada;

	/**  Propiedad ib_periodicidadCuantiaModificada. */
	private boolean ib_periodicidadCuantiaModificada;

	/**  Propiedad ib_porcentajeTransferenciaModificada. */
	private boolean ib_porcentajeTransferenciaModificada;

	/** Propiedad ib requiere cuantia acto. */
	private boolean ib_requiereCuantiaActo;

	/**  Propiedad ib_respuestaLey1943Modificada. */
	private boolean ib_respuestaLey1943Modificada;

	/**  Propiedad ib_tiempoCanonModificada. */
	private boolean ib_tiempoCanonModificada;

	/** Propiedad ib valid cantidad matriculas. */
	private boolean ib_validCantidadMatriculas;

	/** Propiedad ib valid matriculas incrl. */
	private boolean ib_validMatriculasIncrl;

	/**  Propiedad ib valor avaluo modificado. */
	private boolean ib_valorAvaluoModificado;

	/**  Propiedad ib_versionModificada. */
	private boolean ib_versionModificada;

	/** Propiedad ib vis. */
	private boolean ib_vis;

	/** Propiedad il version. */
	private long il_version;

	/**
	 * Modifica el valor de activa fecha ejecutoria.
	 *
	 * @param ab_b asigna el valor a la propiedad activa fecha ejecutoria
	 */
	public void setActivaFechaEjecutoria(boolean ab_b)
	{
		ib_activaFechaEjecutoria = ab_b;
	}

	/**
	 * Valida la propiedad activa fecha ejecutoria.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en activa fecha ejecutoria
	 */
	public boolean isActivaFechaEjecutoria()
	{
		return ib_activaFechaEjecutoria;
	}

	/**
	 * Modifica el valor de activo precalificacion.
	 *
	 * @param as_s asigna el valor a la propiedad activo precalificacion
	 */
	public void setActivoPrecalificacion(String as_s)
	{
		is_activoPrecalificacion = as_s;
	}

	/**
	 * Retorna el valor de activo precalificacion.
	 *
	 * @return el valor de activo precalificacion
	 */
	public String getActivoPrecalificacion()
	{
		return is_activoPrecalificacion;
	}

	/**
	 * Modifica el valor de acto con cuantia.
	 *
	 * @param as_s asigna el valor a la propiedad acto con cuantia
	 */
	public void setActoConCuantia(String as_s)
	{
		is_actoConCuantia = as_s;
	}

	/**
	 * Retorna el valor de acto con cuantia.
	 *
	 * @return el valor de acto con cuantia
	 */
	public String getActoConCuantia()
	{
		return is_actoConCuantia;
	}

	/**
	 * Modifica el valor de acto sin cuantia.
	 *
	 * @param as_s asigna el valor a la propiedad acto sin cuantia
	 */
	public void setActoSinCuantia(String as_s)
	{
		is_actoSinCuantia = as_s;
	}

	/**
	 * Retorna el valor de acto sin cuantia.
	 *
	 * @return el valor de acto sin cuantia
	 */
	public String getActoSinCuantia()
	{
		return is_actoSinCuantia;
	}

	/**
	 * Modifica el valor de actosui.
	 *
	 * @param atd_tipoDocumental asigna el valor a la propiedad actosui
	 */
	public void setActosui(Collection<TipoDocumental> atd_tipoDocumental)
	{
		ictd_tipoDocumental = atd_tipoDocumental;
	}

	/**
	 * Retorna el valor de actosui.
	 *
	 * @return el valor de actosui
	 */
	public Collection<TipoDocumental> getActosui()
	{
		return ictd_tipoDocumental;
	}

	/**
	 * Modifica el valor de apertura matricula.
	 *
	 * @param as_s asigna el valor a la propiedad apertura matricula
	 */
	public void setAperturaMatricula(String as_s)
	{
		is_aperturaMatricula = as_s;
	}

	/**
	 * Retorna el valor de apertura matricula.
	 *
	 * @return el valor de apertura matricula
	 */
	public String getAperturaMatricula()
	{
		return is_aperturaMatricula;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ad_d Modifica el valor de la propiedad.
	 */
	public void setAreaActo(Double ad_d)
	{
		id_areaActo = ad_d;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public Double getAreaActo()
	{
		return id_areaActo;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setAreaActoModificada(boolean ab_b)
	{
		ib_areaActoModificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isAreaActoModificada()
	{
		return ib_areaActoModificada;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ad_d Modifica el valor de la propiedad.
	 */
	public void setAreaTotal(Double ad_d)
	{
		is_areaTotal = ad_d;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public Double getAreaTotal()
	{
		return is_areaTotal;
	}

	/**
	 * Modifica el valor de AreaTotalInmueble.
	 *
	 * @param ad_d de ad d
	 */
	public void setAreaTotalInmueble(Double ad_d)
	{
		id_areaTotalInmueble = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor area total inmueble.
	 *
	 * @return Retorna el valor de la propiedad areaTotalInmueble
	 */
	public Double getAreaTotalInmueble()
	{
		return id_areaTotalInmueble;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setAreaTotalInmuebleModificada(boolean ab_b)
	{
		ib_areaTotalInmuebleModificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isAreaTotalInmuebleModificada()
	{
		return ib_areaTotalInmuebleModificada;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setAreaTotalModificada(boolean ab_b)
	{
		ib_areaTotalModificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isAreaTotalModificada()
	{
		return ib_areaTotalModificada;
	}

	/**
	 * Modifica el valor de AreaTransferir.
	 *
	 * @param ad_d de ad d
	 */
	public void setAreaTransferir(Double ad_d)
	{
		id_areaTransferir = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor area transferir.
	 *
	 * @return Retorna el valor de la propiedad areaTransferir
	 */
	public Double getAreaTransferir()
	{
		return id_areaTransferir;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setAreaTransferirModificada(boolean ab_b)
	{
		ib_areaTransferirModificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isAreaTransferirModificada()
	{
		return ib_areaTransferirModificada;
	}

	/**
	 * Modifica el valor de avaluo.
	 *
	 * @param as_s asigna el valor a la propiedad avaluo
	 */
	public void setAvaluo(String as_s)
	{
		is_avaluo = as_s;
	}

	/**
	 * Retorna el valor de avaluo.
	 *
	 * @return el valor de avaluo
	 */
	public String getAvaluo()
	{
		return is_avaluo;
	}

	/**
	 * Modifica el valor de cantidad actos.
	 *
	 * @param ai_i asigna el valor a la propiedad cantidad actos
	 */
	public void setCantidadActos(Integer ai_i)
	{
		ii_cantidadActos = ai_i;
	}

	/**
	 * Retorna el valor de cantidad actos.
	 *
	 * @return el valor de cantidad actos
	 */
	public Integer getCantidadActos()
	{
		return ii_cantidadActos;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setCantidadCertAntSistemaModificada(boolean ab_b)
	{
		ib_cantidadCertAntSistemaModificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isCantidadCertAntSistemaModificada()
	{
		return ib_cantidadCertAntSistemaModificada;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param al_l Modifica el valor de la propiedad.
	 */
	public void setCantidadCertifAntSistema(Long al_l)
	{
		il_cantidadCertifAntSistema = al_l;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public Long getCantidadCertifAntSistema()
	{
		return il_cantidadCertifAntSistema;
	}

	/**
	 * Modifica el valor de cantidad matriculas.
	 *
	 * @param ai_i asigna el valor a la propiedad cantidad matriculas
	 */
	public void setCantidadMatriculas(Integer ai_i)
	{
		ii_cantidadMatriculas = ai_i;
	}

	/**
	 * Retorna el valor de cantidad matriculas.
	 *
	 * @return el valor de cantidad matriculas
	 */
	public Integer getCantidadMatriculas()
	{
		return ii_cantidadMatriculas;
	}

	/**
	 * Modifica el valor de cantidad matriculas inclr.
	 *
	 * @param ai_i asigna el valor a la propiedad cantidad matriculas inclr
	 */
	public void setCantidadMatriculasInclr(Integer ai_i)
	{
		ii_cantidadMatriculasInclr = ai_i;
	}

	/**
	 * Retorna el valor de cantidad matriculas inclr.
	 *
	 * @return el valor de cantidad matriculas inclr
	 */
	public Integer getCantidadMatriculasInclr()
	{
		return ii_cantidadMatriculasInclr;
	}

	/**
	 * Modifica el valor de circulo registral.
	 *
	 * @param ab_b asigna el valor a la propiedad cantidad modificada
	 */
	public void setCantidadModificada(boolean ab_b)
	{
		ib_cantidadModificada = ab_b;
	}

	/**
	 * Retorna el valor de cantidad modificada.
	 *
	 * @return el valor de cantidad modificada
	 */
	public boolean isCantidadModificada()
	{
		return ib_cantidadModificada;
	}

	/**
	 * Modifica el valor de circulo registral.
	 *
	 * @param as_s asigna el valor a la propiedad circulo registral
	 */
	public void setCirculoRegistral(String as_s)
	{
		is_circuloRegistral = as_s;
	}

	/**
	 * Retorna el valor de circulo registral.
	 *
	 * @return el valor de circulo registral
	 */
	public String getCirculoRegistral()
	{
		return is_circuloRegistral;
	}

	/**
	 * Modifica el valor de codigo.
	 *
	 * @param as_s asigna el valor a la propiedad codigo
	 */
	public void setCodigo(String as_s)
	{
		is_codigo = as_s;
	}

	/**
	 * Retorna el valor de codigo.
	 *
	 * @return el valor de codigo
	 */
	public String getCodigo()
	{
		return is_codigo;
	}

	/**
	 * Modifica el valor de cuantia.
	 *
	 * @param al_al asigna el valor a la propiedad cuantia
	 */
	public void setCuantia(Long al_al)
	{
		il_cuantia = al_al;
	}

	/**
	 * Retorna el valor de cuantia.
	 *
	 * @return el valor de cuantia
	 */
	public Long getCuantia()
	{
		return il_cuantia;
	}

	/**
	 * Modifica el valor de cuantia acto.
	 *
	 * @param ad_d asigna el valor a la propiedad cuantia acto
	 */
	public void setCuantiaActo(BigDecimal ad_d)
	{
		id_cuantiaActo = ad_d;
	}

	/**
	 * Retorna el valor de cuantia acto.
	 *
	 * @return el valor de cuantia acto
	 */
	public BigDecimal getCuantiaActo()
	{
		return id_cuantiaActo;
	}

	/**
	 * Modifica el valor de cuantia modificada.
	 *
	 * @param ab_b asigna el valor a la propiedad cuantia modificada
	 */
	public void setCuantiaModificada(boolean ab_b)
	{
		ib_cuantiaModificada = ab_b;
	}

	/**
	 * Retorna el valor de cuantia modificada.
	 *
	 * @return el valor de cuantia modificada
	 */
	public boolean isCuantiaModificada()
	{
		return ib_cuantiaModificada;
	}

	/**
	 * Modifica el valor de DatosPeriodicidadCuantia.
	 *
	 * @param acs_cs de acs cs
	 */
	public void setDatosPeriodicidadCuantia(Collection<String> acs_cs)
	{
		ics_datosPeriodicidadCuantia = acs_cs;
	}

	/**
	 * Retorna Objeto o variable de valor datos periodicidad cuantia.
	 *
	 * @return Retorna el valor de la propiedad datosPeriodicidadCuantia
	 */
	public Collection<String> getDatosPeriodicidadCuantia()
	{
		return ics_datosPeriodicidadCuantia;
	}

	/**
	 * Modifica el valor de departamento.
	 *
	 * @param al_l asigna el valor a la propiedad departamento
	 */
	public void setDepartamento(Long al_l)
	{
		il_departamento = al_l;
	}

	/**
	 * Retorna el valor de departamento.
	 *
	 * @return el valor de departamento
	 */
	public Long getDepartamento()
	{
		return il_departamento;
	}

	/**
	 * Modifica el valor de ejecutora.
	 *
	 * @param as_s asigna el valor a la propiedad ejecutora
	 */
	public void setEjecutora(String as_s)
	{
		is_ejecutora = as_s;
	}

	/**
	 * Retorna el valor de ejecutora.
	 *
	 * @return el valor de ejecutora
	 */
	public String getEjecutora()
	{
		return is_ejecutora;
	}

	/**
	 * Modifica el valor de es cambio tipo cuantia.
	 *
	 * @param ab_b asigna el valor a la propiedad es cambio tipo cuantia
	 */
	public void setEsCambioTipoCuantia(boolean ab_b)
	{
		ib_esCambioTipoCuantia = ab_b;
	}

	/**
	 * Valida la propiedad es cambio tipo cuantia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es cambio tipo cuantia
	 */
	public boolean isEsCambioTipoCuantia()
	{
		return ib_esCambioTipoCuantia;
	}

	/**
	 * Modifica el valor de especificacion.
	 *
	 * @param as_s asigna el valor a la propiedad especificacion
	 */
	public void setEspecificacion(String as_s)
	{
		is_especificacion = as_s;
	}

	/**
	 * Retorna el valor de especificacion.
	 *
	 * @return el valor de especificacion
	 */
	public String getEspecificacion()
	{
		return is_especificacion;
	}

	/**
	 * * Modifica el valor de fecha ejecutoria.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha ejecutoria
	 */
	public void setFechaEjecutoria(Date ad_d)
	{
		id_fechaEjecutoria = ad_d;
	}

	/**
	 *  Retorna el valor de fecha ejecutoria.
	 *
	 * @return el valor de fecha ejecutoria
	 */
	public Date getFechaEjecutoria()
	{
		return id_fechaEjecutoria;
	}

	/**
	 * Modifica el valor de FechaPagoImpuesto.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaPagoImpuesto(Date ad_d)
	{
		id_fechaPagoImpuesto = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha pago impuesto.
	 *
	 * @return Retorna el valor de la propiedad fechaPagoImpuesto
	 */
	public Date getFechaPagoImpuesto()
	{
		return id_fechaPagoImpuesto;
	}

/**
 * Modifica el valor de FechaPagoImpuestoExtemporaneo.
 *
 * @param ad_d de ad d
 */
	public void setFechaPagoImpuestoExtemporaneo(Date ad_d)
	{
		id_fechaPagoImpuestoExtemporaneo = ad_d;
	}

/**
 * Retorna Objeto o variable de valor fecha pago impuesto extemporaneo.
 *
 * @return Retorna el valor de la propiedad fechaPagoImpuestoExtemporaneo
 */
	public Date getFechaPagoImpuestoExtemporaneo()
	{
		return id_fechaPagoImpuestoExtemporaneo;
	}

	/**
	 * Modifica el valor de fecha vencimiento.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha vencimiento
	 */
	public void setFechaVencimiento(Date ad_d)
	{
		id_fechaVencimiento = ad_d;
	}

	/**
	 * Retorna el valor de fecha vencimiento.
	 *
	 * @return el valor de fecha vencimiento
	 */
	public Date getFechaVencimiento()
	{
		return id_fechaVencimiento;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Modifica el valor de la propiedad.
	 */
	public void setGarantiaHipotecaria(String as_s)
	{
		is_garantiaHipotecaria = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public String getGarantiaHipotecaria()
	{
		return is_garantiaHipotecaria;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setGarantiaHipotecariaModificada(boolean ab_b)
	{
		ib_garantiaHipotecariaModificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isGarantiaHipotecariaModificada()
	{
		return ib_garantiaHipotecariaModificada;
	}

	/**
	 * Modifica el valor de genera segregacion.
	 *
	 * @param as_s asigna el valor a la propiedad genera segregacion
	 */
	public void setGeneraSegregacion(String as_s)
	{
		is_generaSegregacion = as_s;
	}

	/**
	 * Retorna el valor de genera segregacion.
	 *
	 * @return el valor de genera segregacion
	 */
	public String getGeneraSegregacion()
	{
		return is_generaSegregacion;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Modifica el valor de la propiedad.
	 */
	public void setHijuelaPasivo(String as_s)
	{
		is_hijuelaPasivo = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public String getHijuelaPasivo()
	{
		return is_hijuelaPasivo;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setHijuelaPasivoModificada(boolean ab_b)
	{
		ib_hijuelaPasivoModificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isHijuelaPasivoModificada()
	{
		return ib_hijuelaPasivoModificada;
	}

	/**
	 * Modifica el valor de id acto db.
	 *
	 * @param as_s asigna el valor a la propiedad id acto db
	 */
	public void setIdActoDb(String as_s)
	{
		is_idActoDb = as_s;
	}

	/**
	 * Retorna el valor de id acto db.
	 *
	 * @return el valor de id acto db
	 */
	public String getIdActoDb()
	{
		return is_idActoDb;
	}

	/**
	 * Modifica el valor de id acto derivado.
	 *
	 * @param as_s asigna el valor a la propiedad id acto derivado
	 */
	public void setIdActoDerivado(String as_s)
	{
		is_idActoDerivado = as_s;
	}

	/**
	 * Retorna el valor de id acto derivado.
	 *
	 * @return el valor de id acto derivado
	 */
	public String getIdActoDerivado()
	{
		return is_idActoDerivado;
	}

	/**
	 * Modifica el valor de id acto principal.
	 *
	 * @param as_s asigna el valor a la propiedad id acto principal
	 */
	public void setIdActoPrincipal(String as_s)
	{
		is_idActoPrincipal = as_s;
	}

	/**
	 * Retorna el valor de id acto principal.
	 *
	 * @return el valor de id acto principal
	 */
	public String getIdActoPrincipal()
	{
		return is_idActoPrincipal;
	}

	/**
	 * Modifica el valor de id circulo.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna el valor de id circulo.
	 *
	 * @return el valor de id circulo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de id proceso.
	 *
	 * @param as_s asigna el valor a la propiedad id proceso
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Retorna el valor de id proceso.
	 *
	 * @return el valor de id proceso
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de id solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de id solicitud asociada.
	 *
	 * @param as_s asigna el valor a la propiedad id solicitud asociada
	 */
	public void setIdSolicitudAsociada(String as_s)
	{
		is_idSolicitudAsociada = as_s;
	}

	/**
	 * Retorna el valor de id solicitud asociada.
	 *
	 * @return el valor de id solicitud asociada
	 */
	public String getIdSolicitudAsociada()
	{
		return is_idSolicitudAsociada;
	}

	/**
	 * Modifica el valor de id sub proceso.
	 *
	 * @param as_s asigna el valor a la propiedad id sub proceso
	 */
	public void setIdSubProceso(String as_s)
	{
		is_idSubProceso = as_s;
	}

	/**
	 * Retorna el valor de id sub proceso.
	 *
	 * @return el valor de id sub proceso
	 */
	public String getIdSubProceso()
	{
		return is_idSubProceso;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setIdTipoAdquisicionModificada(boolean ab_b)
	{
		ib_idTipoAdquisicionModificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isIdTipoAdquisicionModificada()
	{
		return ib_idTipoAdquisicionModificada;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Modifica el valor de la propiedad.
	 */
	public void setIdTipoTarifaRegistral(String as_s)
	{
		is_idTipoTarifaRegistral = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public String getIdTipoTarifaRegistral()
	{
		return is_idTipoTarifaRegistral;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setIdTipoTarifaRegistralModificada(boolean ab_b)
	{
		ib_idTipoTarifaRegistralModificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isIdTipoTarifaRegistralModificada()
	{
		return ib_idTipoTarifaRegistralModificada;
	}

	/**
	 * Modifica el valor de impuesto registro.
	 *
	 * @param as_s asigna el valor a la propiedad impuesto registro
	 */
	public void setImpuestoRegistro(String as_s)
	{
		is_impuestoRegistro = as_s;
	}

	/**
	 * Retorna el valor de impuesto registro.
	 *
	 * @return el valor de impuesto registro
	 */
	public String getImpuestoRegistro()
	{
		return is_impuestoRegistro;
	}

	/**
	 * Modifica el valor de impuesto registro ext show.
	 *
	 * @param ab_b asigna el valor a la propiedad impuesto registro ext show
	 */
	public void setImpuestoRegistroExtShow(boolean ab_b)
	{
		ib_impuestoRegistroExtShow = ab_b;
	}

	/**
	 * Valida la propiedad impuesto registro ext show.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en impuesto registro ext show
	 */
	public boolean isImpuestoRegistroExtShow()
	{
		return ib_impuestoRegistroExtShow;
	}

	/**
	 * Modifica el valor de impuesto registro extemporaneo.
	 *
	 * @param as_s asigna el valor a la propiedad impuesto registro extemporaneo
	 */
	public void setImpuestoRegistroExtemporaneo(String as_s)
	{
		is_impuestoRegistroExtemporaneo = as_s;
	}

	/**
	 * Retorna el valor de impuesto registro extemporaneo.
	 *
	 * @return el valor de impuesto registro extemporaneo
	 */
	public String getImpuestoRegistroExtemporaneo()
	{
		return is_impuestoRegistroExtemporaneo;
	}

	/**
	 * Modifica el valor de impuesto registro show.
	 *
	 * @param ab_b asigna el valor a la propiedad impuesto registro show
	 */
	public void setImpuestoRegistroShow(boolean ab_b)
	{
		ib_impuestoRegistroShow = ab_b;
	}

	/**
	 * Valida la propiedad impuesto registro show.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en impuesto registro show
	 */
	public boolean isImpuestoRegistroShow()
	{
		return ib_impuestoRegistroShow;
	}

	/**
	 * Modifica el valor de ind mayor valor.
	 *
	 * @param as_s asigna el valor a la propiedad ind mayor valor
	 */
	public void setIndMayorValor(String as_s)
	{
		is_indMayorValor = as_s;
	}

	/**
	 * Retorna el valor de ind mayor valor.
	 *
	 * @return el valor de ind mayor valor
	 */
	public String getIndMayorValor()
	{
		return is_indMayorValor;
	}

	/**
	 * Modifica el valor de inscripcion adicional.
	 *
	 * @param as_s asigna el valor a la propiedad inscripcion adicional
	 */
	public void setInscripcionAdicional(String as_s)
	{
		is_inscripcionAdicional = as_s;
	}

	/**
	 * Retorna el valor de inscripcion adicional.
	 *
	 * @return el valor de inscripcion adicional
	 */
	public String getInscripcionAdicional()
	{
		return is_inscripcionAdicional;
	}

	/**
	 * Modifica el valor de madre cabeza.
	 *
	 * @param as_s asigna el valor a la propiedad madre cabeza
	 */
	public void setMadreCabeza(String as_s)
	{
		is_madreCabeza = as_s;
	}

	/**
	 * Retorna el valor de madre cabeza.
	 *
	 * @return el valor de madre cabeza
	 */
	public String getMadreCabeza()
	{
		return is_madreCabeza;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setMadreCabezaModificada(boolean ab_b)
	{
		ib_madreCabezaModificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isMadreCabezaModificada()
	{
		return ib_madreCabezaModificada;
	}

	/**
	 * Modifica el valor de matricula liquidacion.
	 *
	 * @param ab_b asigna el valor a la propiedad matricula liquidacion
	 */
	public void setMatriculaLiquidacion(boolean ab_b)
	{
		ib_matriculaLiquidacion = ab_b;
	}

	/**
	 * Valida la propiedad matricula liquidacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en matricula liquidacion
	 */
	public boolean isMatriculaLiquidacion()
	{
		return ib_matriculaLiquidacion;
	}

	/**
	 * Modifica el valor de municipio.
	 *
	 * @param al_l asigna el valor a la propiedad municipio
	 */
	public void setMunicipio(Long al_l)
	{
		il_municipio = al_l;
	}

	/**
	 * Retorna el valor de municipio.
	 *
	 * @return el valor de municipio
	 */
	public Long getMunicipio()
	{
		return il_municipio;
	}

	/**
	 * Modifica el valor de no requiere cuantia acto.
	 *
	 * @param ab_b asigna el valor a la propiedad no requiere cuantia acto
	 */
	public void setNoRequiereCuantiaActo(boolean ab_b)
	{
		ib_noRequiereCuantiaActo = ab_b;
	}

	/**
	 * Valida la propiedad no requiere cuantia acto.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en no requiere cuantia acto
	 */
	public boolean isNoRequiereCuantiaActo()
	{
		return ib_noRequiereCuantiaActo;
	}

	/**
	 * Modifica el valor de nombre tipo adquisicion.
	 *
	 * @param as_a el valor a asignar
	 */
	public void setNombreTipoAdquisicion(String as_a)
	{
		is_nombreTipoAdquisicion = as_a;
	}

	/**
	 * Retorna el nombre del tipo Adquisicion.
	 *
	 * @return el valor de tipo Adquisicion
	 */
	public String getNombreTipoAdquisicion()
	{
		return is_nombreTipoAdquisicion;
	}

	/**
	 * Modifica el valor de nueva entrada.
	 *
	 * @param ab_b asigna el valor a la propiedad nueva entrada
	 */
	public void setNuevaEntrada(boolean ab_b)
	{
		ib_nuevaEntrada = ab_b;
	}

	/**
	 * Valida la propiedad nueva entrada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en nueva entrada
	 */
	public boolean isNuevaEntrada()
	{
		return ib_nuevaEntrada;
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
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Modifica el valor de la propiedad.
	 */
	public void setOrganismoInternacional(String as_s)
	{
		is_organismoInternacional = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public String getOrganismoInternacional()
	{
		return is_organismoInternacional;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setOrganismoInternacionalModificada(boolean ab_b)
	{
		ib_organismoInternacionalModificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isOrganismoInternacionalModificada()
	{
		return ib_organismoInternacionalModificada;
	}

	/**
	 * Modifica el valor de PeriodicidadCuantia.
	 *
	 * @param as_s de as s
	 */
	public void setPeriodicidadCuantia(String as_s)
	{
		is_periodicidadCuantia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor periodicidad cuantia.
	 *
	 * @return Retorna el valor de la propiedad periodicidadCuantia
	 */
	public String getPeriodicidadCuantia()
	{
		return is_periodicidadCuantia;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setPeriodicidadCuantiaModificada(boolean ab_b)
	{
		ib_periodicidadCuantiaModificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isPeriodicidadCuantiaModificada()
	{
		return ib_periodicidadCuantiaModificada;
	}

	/**
	 * Modifica el valor de PorcentajeTransferencia.
	 *
	 * @param ad_d de ad d
	 */
	public void setPorcentajeTransferencia(Double ad_d)
	{
		id_porcentajeTransferencia = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor porcentaje transferencia.
	 *
	 * @return Retorna el valor de la propiedad porcentajeTransferencia
	 */
	public Double getPorcentajeTransferencia()
	{
		return id_porcentajeTransferencia;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setPorcentajeTransferenciaModificada(boolean ab_b)
	{
		ib_porcentajeTransferenciaModificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isPorcentajeTransferenciaModificada()
	{
		return ib_porcentajeTransferenciaModificada;
	}

	/**
	 * Modifica el valor de Pregunta1943.
	 *
	 * @param as_s de as s
	 */
	public void setPregunta1943(String as_s)
	{
		is_pregunta1943 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor pregunta 1943.
	 *
	 * @return Retorna el valor de la propiedad pregunta1943
	 */
	public String getPregunta1943()
	{
		return is_pregunta1943;
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
	 * Modifica el valor de requiere cuantia acto.
	 *
	 * @param ab_b asigna el valor a la propiedad requiere cuantia acto
	 */
	public void setRequiereCuantiaActo(boolean ab_b)
	{
		ib_requiereCuantiaActo = ab_b;
	}

	/**
	 * Valida la propiedad requiere cuantia acto.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en requiere cuantia acto
	 */
	public boolean isRequiereCuantiaActo()
	{
		return ib_requiereCuantiaActo;
	}

	/**
	 * Modifica el valor de requiere tipo adquisicion.
	 *
	 * @param as_s asigna el valor a la propiedad requiere tipo adquisicion
	 */
	public void setRequiereTipoAdquisicion(String as_s)
	{
		is_requiereTipoAdquisicion = as_s;
	}

	/**
	 * Retorna el valor de requiere tipo adquisicion.
	 *
	 * @return el valor de requiere tipo adquisicion
	 */
	public String getRequiereTipoAdquisicion()
	{
		if(!StringUtils.isValidString(is_requiereTipoAdquisicion))
			is_requiereTipoAdquisicion = new String();

		return is_requiereTipoAdquisicion;
	}

	/**
	 * Modifica el valor de RespuestaLey1943.
	 *
	 * @param as_s de as s
	 */
	public void setRespuestaLey1943(String as_s)
	{
		is_respuestaLey1943 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor respuesta ley 1943.
	 *
	 * @return Retorna el valor de la propiedad respuestaLey1943
	 */
	public String getRespuestaLey1943()
	{
		if(!StringUtils.isValidString(is_respuestaLey1943))
			is_respuestaLey1943 = EstadoCommon.S;

		return is_respuestaLey1943;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setRespuestaLey1943Modificada(boolean ab_b)
	{
		ib_respuestaLey1943Modificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isRespuestaLey1943Modificada()
	{
		return ib_respuestaLey1943Modificada;
	}

	/**
	 * Modifica el valor de secuence.
	 *
	 * @param as_s asigna el valor a la propiedad secuence
	 */
	public void setSecuence(String as_s)
	{
		is_secuence = as_s;
	}

	/**
	 * Retorna el valor de secuence.
	 *
	 * @return el valor de secuence
	 */
	public String getSecuence()
	{
		return is_secuence;
	}

	/**
	 * Modifica el valor de sumatoria area.
	 *
	 * @param as_s asigna el valor a la propiedad sumatoria area
	 */
	public void setSumatoriaArea(String as_s)
	{
		is_sumatoriaArea = as_s;
	}

	/**
	 * Retorna el valor de sumatoria area.
	 *
	 * @return el valor de sumatoria area
	 */
	public String getSumatoriaArea()
	{
		return is_sumatoriaArea;
	}

	/**
	 * Modifica el valor de tiempo.
	 *
	 * @param al_l asigna el valor a la propiedad tiempo
	 */
	public void setTiempo(Long al_l)
	{
		il_tiempo = al_l;
	}

	/**
	 * Retorna el valor de tiempo.
	 *
	 * @return el valor de tiempo
	 */
	public Long getTiempo()
	{
		return il_tiempo;
	}

	/**
	 * Modifica el valor de TiempoCanon.
	 *
	 * @param al_l de al l
	 */
	public void setTiempoCanon(Long al_l)
	{
		il_tiempoCanon = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor tiempo canon.
	 *
	 * @return Retorna el valor de la propiedad tiempoCanon
	 */
	public Long getTiempoCanon()
	{
		return il_tiempoCanon;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setTiempoCanonModificada(boolean ab_b)
	{
		ib_tiempoCanonModificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isTiempoCanonModificada()
	{
		return ib_tiempoCanonModificada;
	}

	/**
	 * Modifica el valor de tipo adquisicion.
	 *
	 * @param as_s asigna el valor a la propiedad tipo adquisicion
	 */
	public void setTipoAdquisicion(String as_s)
	{
		is_tipoAdquisicion = as_s;
	}

	/**
	 * Retorna el valor de tipo adquisicion.
	 *
	 * @return el valor de tipo adquisicion
	 */
	public String getTipoAdquisicion()
	{
		return is_tipoAdquisicion;
	}

	/**
	 * Modifica el valor de tipo tarifa.
	 *
	 * @param as_s asigna el valor a la propiedad tipo tarifa
	 */
	public void setTipoTarifa(String as_s)
	{
		is_tipoTarifa = as_s;
	}

	/**
	 * Retorna el valor de tipo tarifa.
	 *
	 * @return el valor de tipo tarifa
	 */
	public String getTipoTarifa()
	{
		return is_tipoTarifa;
	}

	/**
	 * Modifica el valor de user id.
	 *
	 * @param as_s asigna el valor a la propiedad user id
	 */
	public void setUserId(String as_s)
	{
		is_userId = as_s;
	}

	/**
	 * Retorna el valor de user id.
	 *
	 * @return el valor de user id
	 */
	public String getUserId()
	{
		return is_userId;
	}

	/**
	 * Modifica el valor de valid cantidad matriculas.
	 *
	 * @param ab_b asigna el valor a la propiedad valid cantidad matriculas
	 */
	public void setValidCantidadMatriculas(boolean ab_b)
	{
		ib_validCantidadMatriculas = ab_b;
	}

	/**
	 * Valida la propiedad valid cantidad matriculas.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en valid cantidad matriculas
	 */
	public boolean isValidCantidadMatriculas()
	{
		return ib_validCantidadMatriculas;
	}

	/**
	 * Modifica el valor de valid matriculas incrl.
	 *
	 * @param ab_b asigna el valor a la propiedad valid matriculas incrl
	 */
	public void setValidMatriculasIncrl(boolean ab_b)
	{
		ib_validMatriculasIncrl = ab_b;
	}

	/**
	 * Valida la propiedad valid matriculas incrl.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en valid matriculas incrl
	 */
	public boolean isValidMatriculasIncrl()
	{
		return ib_validMatriculasIncrl;
	}

	/**
	 * Modifica el valor de valor avaluo.
	 *
	 * @param ad_d asigna el valor a la propiedad valor avaluo
	 */
	public void setValorAvaluo(BigDecimal ad_d)
	{
		id_valorAvaluo = ad_d;
	}

	/**
	 * Retorna el valor de valor avaluo.
	 *
	 * @return el valor de valor avaluo
	 */
	public BigDecimal getValorAvaluo()
	{
		return id_valorAvaluo;
	}

	/**
	 * Modifica el valor de valor avaluo modificado.
	 *
	 * @param ab_b asigna el valor a la propiedad version
	 */
	public void setValorAvaluoModificado(boolean ab_b)
	{
		ib_valorAvaluoModificado = ab_b;
	}

	/**
	 * Retorna el valor de valor avaluo modificado.
	 *
	 * @return el valor de valor avaluo modificado
	 */
	public boolean isValorAvaluoModificado()
	{
		return ib_valorAvaluoModificado;
	}

	/**
	 * Modifica el valor de version.
	 *
	 * @param al_l asigna el valor a la propiedad version
	 */
	public void setVersion(long al_l)
	{
		il_version = al_l;
	}

	/**
	 * Retorna el valor de version.
	 *
	 * @return el valor de version
	 */
	public long getVersion()
	{
		return il_version;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Modifica el valor de la propiedad.
	 */
	public void setVersionModificada(boolean ab_b)
	{
		ib_versionModificada = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad.
	 */
	public boolean isVersionModificada()
	{
		return ib_versionModificada;
	}

	/**
	 * Modifica el valor de Vis.
	 *
	 * @param ab_b de ab b
	 */
	public void setVis(boolean ab_b)
	{
		ib_vis = ab_b;
	}

	/**
	 * Valida la propiedad vis.
	 *
	 * @return Retorna el valor de la propiedad vis
	 */
	public boolean isVis()
	{
		return ib_vis;
	}
}
