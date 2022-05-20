package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_ACTO.
 *
 * @author hcastaneda
 */
public class Acto extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3266878237004061562L;

	/** Propiedad ibd cantidad actos. */
	private BigDecimal ibd_cantidadActos;

	/** Propiedad ibd cuantia. */
	private BigDecimal ibd_cuantia;

	/** Propiedad ibd valor avaluo. */
	private BigDecimal ibd_valorAvaluo;

	/** Propiedad id fecha ejecutoria. */
	private Date id_fechaEjecutoria;

	/** Propiedad id fecha pago impuesto. */
	private Date id_fechaPagoImpuesto;

	/** Propiedad id fecha pago impuesto extemporaneo. */
	private Date id_fechaPagoImpuestoExtemporaneo;

	/** Propiedad id fecha vencimiento. */
	private Date id_fechaVencimiento;

	/** Propiedad id fecha vencimiento impuesto. */
	private Date id_fechaVencimientoImpuesto;

	/** Propiedad id area acto. */
	private Double id_areaActo;

	/** Propiedad id area total. */
	private Double id_areaTotal;

	/** Propiedad id area total inmueble. */
	private Double id_areaTotalInmueble;

	/** Propiedad id area transferir. */
	private Double id_areaTransferir;

	/** Propiedad id porcentaje transferencia. */
	private Double id_porcentajeTransferencia;

	/** Propiedad il tiempo canon. */
	private Long il_tiempoCanon;

	/** Propiedad is activo precalificacion. */
	private String is_activoPrecalificacion;

	/** Propiedad is estado liquidacion impuesto. */
	private String is_estadoLiquidacionImpuesto;

	/** Propiedad is garantia hipoteca. */
	private String is_garantiaHipoteca;

	/** Propiedad is hijuela pasivo. */
	private String is_hijuelaPasivo;

	/** Propiedad is id acto. */
	private String is_idActo;

	/** Propiedad is id acto principal. */
	private String is_idActoPrincipal;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id subproceso. */
	private String is_idSubproceso;

	/** Propiedad is id ticket. */
	private String is_idTicket;

	/** Propiedad is id tipo acto. */
	private String is_idTipoActo;

	/** Propiedad is id tipo adquisicion. */
	private String is_idTipoAdquisicion;

	/** Propiedad is id tipo derecho reg. */
	private String is_idTipoDerechoReg;

	/** Propiedad is id tipo requiere matricula. */
	private String is_idTipoRequiereMatricula;

	/** Propiedad is id tipo tarifa registral. */
	private String is_idTipoTarifaRegistral;

	/** Propiedad is id usuario. */
	private String is_idUsuario;

	/** Propiedad is impuesto registro extemporaneo. */
	private String is_impuestoRegistroExtemporaneo;

	/** Propiedad is ind mayor valor. */
	private String is_indMayorValor;

	/** Propiedad is madre cabeza. */
	private String is_madreCabeza;

	/** Propiedad is numero boleta fiscal. */
	private String is_numeroBoletaFiscal;

	/** Propiedad is numero boleta fiscal ext. */
	private String is_numeroBoletaFiscalExt;

	/** Propiedad is numero proceso. */
	private String is_numeroProceso;

	/** Propiedad is organismo internacional. */
	private String is_organismoInternacional;

	/** Propiedad is periodicidad cuantia. */
	private String is_periodicidadCuantia;

	/** Propiedad is referencia. */
	private String is_referencia;

	/** Propiedad is respuesta ley 1943. */
	private String is_respuestaLey1943;

	/** Propiedad ita tipo acto. */
	private TipoActo ita_tipoActo;

	/** Propiedad ib viene de antiguo sistema. */
	private boolean ib_vieneDeAntiguoSistema;

	/** Propiedad ii cantidad certificados ant sistema. */
	private int ii_cantidadCertificadosAntSistema;

	/** Propiedad ii version. */
	private int ii_version;

	/**
	 * Constructor por defecto.
	 */
	public Acto()
	{
	}

	/**
	 * Constructor recibe como parametro id solicitud.
	 *
	 * @param as_idSolicitud id solicitud
	 */
	public Acto(String as_idSolicitud)
	{
		is_idSolicitud = as_idSolicitud;
	}

	/**
	 * Instancia un nuevo objeto acto.
	 *
	 * @param as_idSolicitud correspondiente al valor de id solicitud
	 * @param as_idCirculo correspondiente al valor de id circulo
	 * @param as_idTipoActo correspondiente al valor de id tipo acto
	 */
	public Acto(String as_idSolicitud, String as_idCirculo, String as_idTipoActo)
	{
		is_idSolicitud     = as_idSolicitud;
		is_idCirculo       = as_idCirculo;
		is_idTipoActo      = as_idTipoActo;
	}

	/**
	 * Modifica el valor de ActivoPrecalificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivoPrecalificacion(String as_s)
	{
		is_activoPrecalificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo precalificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivoPrecalificacion()
	{
		return is_activoPrecalificacion;
	}

	/**
	 * Modifica el valor de AreaActo.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setAreaActo(Double ad_d)
	{
		id_areaActo = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor area acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getAreaActo()
	{
		return id_areaActo;
	}

	/**
	 * Modifica el valor de AreaTotal.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setAreaTotal(Double ad_d)
	{
		id_areaTotal = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor area total.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getAreaTotal()
	{
		return id_areaTotal;
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
	 * Modifica el valor de CantidadActos.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setCantidadActos(BigDecimal abd_bd)
	{
		ibd_cantidadActos = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad actos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getCantidadActos()
	{
		return ibd_cantidadActos;
	}

	/**
	 * Modifica el valor de CantidadCertificadosAntSistema.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setCantidadCertificadosAntSistema(int ai_i)
	{
		ii_cantidadCertificadosAntSistema = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad certificados ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getCantidadCertificadosAntSistema()
	{
		return ii_cantidadCertificadosAntSistema;
	}

	/**
	 * Modifica el valor de Cuantia.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setCuantia(BigDecimal abd_bd)
	{
		ibd_cuantia = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor cuantia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getCuantia()
	{
		return ibd_cuantia;
	}

	/**
	 * Modifica el valor de EstadoLiquidacionImpuesto.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstadoLiquidacionImpuesto(String as_s)
	{
		is_estadoLiquidacionImpuesto = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado liquidacion impuesto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoLiquidacionImpuesto()
	{
		return is_estadoLiquidacionImpuesto;
	}

	/**
	 * Modifica el valor de FechaEjecutoria.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaEjecutoria(Date ad_d)
	{
		id_fechaEjecutoria = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha ejecutoria.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaEjecutoria()
	{
		return id_fechaEjecutoria;
	}

	/**
	 * Modifica el valor de FechaPagoImpuesto.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaPagoImpuesto(Date ad_d)
	{
		id_fechaPagoImpuesto = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha pago impuesto.
	 *
	 * @return devuelve el valor de la propiedad
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
	 * Modifica el valor de FechaVencimiento.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaVencimiento(Date ad_d)
	{
		id_fechaVencimiento = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha vencimiento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaVencimiento()
	{
		return id_fechaVencimiento;
	}

	/**
	 * Modifica el valor de FechaVencimientoImpuesto.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaVencimientoImpuesto(Date ad_d)
	{
		id_fechaVencimientoImpuesto = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha vencimiento impuesto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaVencimientoImpuesto()
	{
		return id_fechaVencimientoImpuesto;
	}

	/**
	 * Modifica el valor de GarantiaHipoteca.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setGarantiaHipoteca(String as_s)
	{
		is_garantiaHipoteca = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor garantia hipoteca.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getGarantiaHipoteca()
	{
		return is_garantiaHipoteca;
	}

	/**
	 * Modifica el valor de HijuelaPasivo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setHijuelaPasivo(String as_s)
	{
		is_hijuelaPasivo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor hijuela pasivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getHijuelaPasivo()
	{
		return is_hijuelaPasivo;
	}

	/**
	 * Modifica el valor de IdActo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdActo(String as_s)
	{
		is_idActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdActo()
	{
		return is_idActo;
	}

	/**
	 * Modifica el valor de IdActoPrincipal.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdActoPrincipal(String as_s)
	{
		is_idActoPrincipal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id acto principal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdActoPrincipal()
	{
		return is_idActoPrincipal;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSubproceso(String as_s)
	{
		is_idSubproceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * Modifica el valor de IdTicket.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTicket(String as_s)
	{
		is_idTicket = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id ticket.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTicket()
	{
		return is_idTicket;
	}

	/**
	 * Modifica el valor de IdTipoActo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoActo(String as_s)
	{
		is_idTipoActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoActo()
	{
		return is_idTipoActo;
	}

	/**
	 * Modifica el valor de IdTipoAdquisicion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoAdquisicion(String as_s)
	{
		is_idTipoAdquisicion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo adquisicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoAdquisicion()
	{
		return is_idTipoAdquisicion;
	}

	/**
	 * Modifica el valor de IdTipoDerechoReg.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoDerechoReg(String as_s)
	{
		is_idTipoDerechoReg = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo derecho reg.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoDerechoReg()
	{
		return is_idTipoDerechoReg;
	}

	/**
	 * Modifica el valor de IdTipoRequiereMatricula.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoRequiereMatricula(String as_s)
	{
		is_idTipoRequiereMatricula = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo requiere matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoRequiereMatricula()
	{
		return is_idTipoRequiereMatricula;
	}

	/**
	 * Modifica el valor de IdTipoTarifaRegistral.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoTarifaRegistral(String as_s)
	{
		is_idTipoTarifaRegistral = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo tarifa registral.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoTarifaRegistral()
	{
		return is_idTipoTarifaRegistral;
	}

	/**
	 * Modifica el valor de IdUsuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUsuario(String as_s)
	{
		is_idUsuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
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
	 * Modifica el valor de IndMayorValor.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIndMayorValor(String as_s)
	{
		is_indMayorValor = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor ind mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIndMayorValor()
	{
		return is_indMayorValor;
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
	 * Modifica el valor de NumeroBoletaFiscal.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroBoletaFiscal(String as_s)
	{
		is_numeroBoletaFiscal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero boleta fiscal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroBoletaFiscal()
	{
		return is_numeroBoletaFiscal;
	}

	/**
	 * Modifica el valor de NumeroBoletaFiscalExt.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroBoletaFiscalExt(String as_s)
	{
		is_numeroBoletaFiscalExt = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero boleta fiscal ext.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroBoletaFiscalExt()
	{
		return is_numeroBoletaFiscalExt;
	}

	/**
	 * Modifica el valor de NumeroProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroProceso(String as_s)
	{
		is_numeroProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroProceso()
	{
		return is_numeroProceso;
	}

	/**
	 * Modifica el valor de OrganismoInternacional.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setOrganismoInternacional(String as_s)
	{
		is_organismoInternacional = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor organismo internacional.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getOrganismoInternacional()
	{
		return is_organismoInternacional;
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
	 * Modifica el valor de Referencia.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setReferencia(String as_s)
	{
		is_referencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor referencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getReferencia()
	{
		return is_referencia;
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
		return is_respuestaLey1943;
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
	 * Modifica el valor de TipoActo.
	 *
	 * @param ata_ta asigna el valor a la propiedad
	 */
	public void setTipoActo(TipoActo ata_ta)
	{
		ita_tipoActo = ata_ta;
	}

	/**
	 * Retorna Objeto o variable de valor tipo acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public TipoActo getTipoActo()
	{
		return ita_tipoActo;
	}

	/**
	 * Modifica el valor de ValorAvaluo.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setValorAvaluo(BigDecimal abd_bd)
	{
		ibd_valorAvaluo = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor avaluo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getValorAvaluo()
	{
		return ibd_valorAvaluo;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param version asigna el valor a la propiedad
	 */
	public void setVersion(int version)
	{
		ii_version = version;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getVersion()
	{
		return ii_version;
	}

	/**
	 * Modifica el valor de VieneDeAntiguoSistema.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setVieneDeAntiguoSistema(boolean ab_b)
	{
		ib_vieneDeAntiguoSistema = ab_b;
	}

	/**
	 * Valida la propiedad viene de antiguo sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isVieneDeAntiguoSistema()
	{
		return ib_vieneDeAntiguoSistema;
	}
}
