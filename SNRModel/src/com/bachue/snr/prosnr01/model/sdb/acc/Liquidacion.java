package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.IntentoEnvioJob;
import com.bachue.snr.prosnr01.model.registro.Acto;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_LIQUIDACION.
 *
 * @author Julian Vaca
 */
public class Liquidacion extends IntentoEnvioJob implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8637643255517096630L;

	/** Propiedad ib simular liquidacion. */
	boolean ib_simularLiquidacion;

	/** Propiedad ia acc acto. */
	private com.bachue.snr.prosnr01.model.sdb.acc.Acto ia_accActo;

	/** Propiedad ia acto. */
	private Acto ia_acto;

	/** Propiedad ibd numero cuantia acto. */
	private BigDecimal ibd_numeroCuantiaActo;

	/** Propiedad ibd total mayor valor. */
	private BigDecimal ibd_totalMayorValor;

	/** Propiedad ibd total saldo favor. */
	private BigDecimal ibd_totalSaldoFavor;

	/** Propiedad ibd valor. */
	private BigDecimal ibd_valor;

	/** Propiedad ibd valor avaluo. */
	private BigDecimal ibd_valorAvaluo;

	/** Propiedad ibd valor documental. */
	private BigDecimal ibd_valorDocumental;

	/** Propiedad ibd valor impuesto final. */
	private BigDecimal ibd_valorImpuestoFinal;

	/** Propiedad ibd valor impuesto mayor valor. */
	private BigDecimal ibd_valorImpuestoMayorValor;

	/** Propiedad ibd valor impuesto saldo favor. */
	private BigDecimal ibd_valorImpuestoSaldoFavor;

	/** Propiedad ibd valor impuestos. */
	private BigDecimal ibd_valorImpuestos;

	/** Propiedad ibd valor mayor valor. */
	private BigDecimal ibd_valorMayorValor;

	/** Propiedad ibd valor saldo favor. */
	private BigDecimal ibd_valorSaldoFavor;

	/** Propiedad ibd valor tarifa. */
	private BigDecimal ibd_valorTarifa;

	/** Propiedad ibd valor total. */
	private BigDecimal ibd_valorTotal;

	/** Propiedad icr circulo registral. */
	private CirculoRegistral icr_circuloRegistral;

	/** Propiedad ica actos registro mayor valor. */
	private Collection<Acto> ica_actosRegistroMayorValor;

	/** Propiedad icl condiciones. */
	private Collection<Liquidacion> icl_condiciones;

	/** Propiedad icl liquidacion item condicion. */
	private Collection<Liquidacion> icl_liquidacionItemCondicion;

	/** Propiedad icsm solicitud matricula. */
	private Collection<SolicitudMatricula> icsm_solicitudMatricula;

	/** Propiedad icsma solicitud matricula acto. */
	private Collection<SolicitudMatriculaActo> icsma_solicitudMatriculaActo;

	/** Propiedad id fecha liquidacion. */
	private Date id_fechaLiquidacion;

	/** Propiedad id fecha pago. */
	private Date id_fechaPago;

	/** Propiedad id fecha vencimiento. */
	private Date id_fechaVencimiento;

	/** Propiedad id valor conserv documental. */
	private Double id_valorConservDocumental;

	/** Propiedad id valor final. */
	private Double id_valorFinal;

	/** Propiedad id valor impuestos final. */
	private Double id_valorImpuestosFinal;

	/** Propiedad ii consecutivo asociada. */
	private Integer ii_consecutivoAsociada;

	/** Propiedad il cantidad. */
	private Long il_cantidad;

	/** Propiedad il cantidad actos. */
	private Long il_cantidadActos;

	/** Propiedad il certificados asociados. */
	private Long il_certificadosAsociados;

	/** Propiedad il version tarifa conserv documental. */
	private Long il_versionTarifaConservDocumental;

	/** Propiedad il version tarifa fija. */
	private Long il_versionTarifaFija;

	/** Propiedad il version tarifa registral. */
	private Long il_versionTarifaRegistral;

	/** Propiedad ip proceso. */
	private Proceso ip_proceso;

	/** Propiedad ir registro. */
	private Registro ir_registro;

	/** Propiedad irmv registro mayor valor. */
	private RegistroMayorValor irmv_registroMayorValor;

	/** Propiedad irpe registro pago exceso. */
	private RegistroPagoExceso irpe_registroPagoExceso;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is automatico. */
	private String is_automatico;

	/** Propiedad is circulos registrales. */
	private String is_circulosRegistrales;

	/** Propiedad is codigo respuesta. */
	private String is_codigoRespuesta;

	/** Propiedad is codigo sql. */
	private String is_codigoSql;

	/** Propiedad is concepto mayor valor. */
	private String is_conceptoMayorValor;

	/** Propiedad is condicion. */
	private String is_condicion;

	/** Propiedad is consecutivo barcode. */
	private String is_consecutivoBarcode;

	/** Propiedad is cuantia acto. */
	private String is_cuantiaActo;

	/** Propiedad is descripcion acto. */
	private String is_descripcionActo;

	/** Propiedad is enviado notificacion pago. */
	private String is_enviadoNotificacionPago;

	/** Propiedad is fecha expedicion documento. */
	private String is_fechaExpedicionDocumento;

	/** Propiedad is id acto. */
	private String is_idActo;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id condicion. */
	private String is_idCondicion;

	/** Propiedad is id condicion tarifa. */
	private String is_idCondicionTarifa;

	/** Propiedad is id item. */
	private String is_idItem;

	/** Propiedad is id liquidacion. */
	private String is_idLiquidacion;

	/** Propiedad is id liquidacion asociada. */
	private String is_idLiquidacionAsociada;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is id rango. */
	private String is_idRango;

	/** Propiedad is id salario. */
	private String is_idSalario;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id subproceso. */
	private String is_idSubproceso;

	/** Propiedad is id tarifa. */
	private String is_idTarifa;

	/** Propiedad is id tarifa conserv documental. */
	private String is_idTarifaConservDocumental;

	/** Propiedad is id tarifa fija. */
	private String is_idTarifaFija;

	/** Propiedad is id tipo acto. */
	private String is_idTipoActo;

	/** Propiedad is id tipo acto condicion. */
	private String is_idTipoActoCondicion;

	/** Propiedad is id tipo canal origen. */
	private String is_idTipoCanalOrigen;

	/** Propiedad is id tipo estado liquidacion. */
	private String is_idTipoEstadoLiquidacion;

	/** Propiedad is id tipo mayor valor. */
	private String is_idTipoMayorValor;

	/** Propiedad is id tipo tarifa registral. */
	private String is_idTipoTarifaRegistral;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno correcciones. */
	private String is_idTurnoCorrecciones;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is ind mayor valor. */
	private String is_indMayorValor;

	/** Propiedad is motivo mayor valor. */
	private String is_motivoMayorValor;

	/** Propiedad is motivo saldo favor. */
	private String is_motivoSaldoFavor;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is nombre circulo. */
	private String is_nombreCirculo;

	/** Propiedad is nombre oficina origen. */
	private String is_nombreOficinaOrigen;

	/** Propiedad is numero documento. */
	private String is_numeroDocumento;

	/** Propiedad is numero matriculas. */
	private String is_numeroMatriculas;

	/** Propiedad is numero recibo liquidacion. */
	private String is_numeroReciboLiquidacion;

	/** Propiedad is numero referencia. */
	private String is_numeroReferencia;

	/** Propiedad is pagada. */
	private String is_pagada;

	/** Propiedad is regla cuantia cantidad. */
	private String is_reglaCuantiaCantidad;

	/**  Propiedad is_requiereCuantia. */
	private String is_requiereCuantia;

	/** Propiedad is respuesta. */
	private String is_respuesta;

	/** Propiedad is respuesta liquidacion. */
	private String is_respuestaLiquidacion;

	/** Propiedad is selected. */
	private String is_selected;

	/** Propiedad is sub proceso. */
	private String is_subProceso;

	/** Propiedad is tarifa. */
	private String is_tarifa;

	/** Propiedad is tipo acto condicion. */
	private String is_tipoActoCondicion;

	/** Propiedad is tipo doc inscribir. */
	private String is_tipoDocInscribir;

	/** Propiedad is tramite. */
	private String is_tramite;

	/** Propiedad is version. */
	private String is_version;

	/** Propiedad ita tipo acto. */
	private TipoActo ita_tipoActo;

	/** Propiedad ib detalle reproduccion constancia. */
	private boolean ib_detalleReproduccionConstancia;

	/** Propiedad ib liquidar. */
	private boolean ib_liquidar;

	/** Propiedad ib mayor valor modificado. */
	private boolean ib_mayorValorModificado;

	/** Propiedad ib proceso certificados. */
	private boolean ib_procesoCertificados;

	/** Propiedad ib respuesta boolean. */
	private boolean ib_respuestaBoolean;

	/** Propiedad ib sin cantidad actos. */
	private boolean ib_sinCantidadActos;

	/** Propiedad id cuantia acto double. */
	private double id_cuantiaActoDouble;

	/** Propiedad ii consecutivo. */
	private int ii_consecutivo;

	/**
	 * Constructor por defecto.
	 */
	public Liquidacion()
	{
	}

	/**
	 * Constructor que recibe como parametro el id solicitud.
	 *
	 * @param as_idSolicitud id solicutd
	 */
	public Liquidacion(String as_idSolicitud)
	{
		setIdSolicitud(as_idSolicitud);
	}

	/**
	 * Instancia un nuevo objeto liquidacion.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_numeroReferencia de as numero referencia
	 */
	public Liquidacion(String as_idSolicitud, String as_numeroReferencia)
	{
		setIdSolicitud(as_idSolicitud);
		setNumeroReferencia(as_numeroReferencia);
	}

	/**
	 * Instancia un nuevo objeto liquidacion.
	 *
	 * @param as_idLiquidacion correspondiente al valor de id liquidacion
	 * @param ai_consecutivo correspondiente al valor de consecutivo liquidacion
	 * @param as_idCirculo correspondiente al valor de id circulo
	 * @param as_idActo correspondiente al valor de id acto
	 */
	public Liquidacion(String as_idLiquidacion, int ai_consecutivo, String as_idCirculo, String as_idActo)
	{
		setIdLiquidacion(as_idLiquidacion);
		setConsecutivo(ai_consecutivo);
		setIdCirculo(as_idCirculo);
		setIdActo(as_idActo);
	}

	/**
	 * Modifica el valor de AccActo.
	 *
	 * @param aa_a asigna el valor a la propiedad
	 */
	public void setAccActo(com.bachue.snr.prosnr01.model.sdb.acc.Acto aa_a)
	{
		ia_accActo = aa_a;
	}

	/**
	 * Retorna Objeto o variable de valor acc acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public com.bachue.snr.prosnr01.model.sdb.acc.Acto getAccActo()
	{
		return ia_accActo;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de Acto.
	 *
	 * @param aa_a de aa a
	 */
	public void setActo(Acto aa_a)
	{
		ia_acto = aa_a;
	}

	/**
	 * Retorna Objeto o variable de valor acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Acto getActo()
	{
		return ia_acto;
	}

	/**
	 * Modifica el valor de ActosRegistroMayorValor.
	 *
	 * @param aca_ca asigna el valor a la propiedad
	 */
	public void setActosRegistroMayorValor(Collection<Acto> aca_ca)
	{
		ica_actosRegistroMayorValor = aca_ca;
	}

	/**
	 * Retorna Objeto o variable de valor actos registro mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Acto> getActosRegistroMayorValor()
	{
		return ica_actosRegistroMayorValor;
	}

	/**
	 * Modifica el valor de Automatico.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAutomatico(String as_s)
	{
		is_automatico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor automatico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAutomatico()
	{
		return is_automatico;
	}

	/**
	 * Modifica el valor de Cantidad.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setCantidad(Long al_l)
	{
		il_cantidad = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getCantidad()
	{
		return il_cantidad;
	}

	/**
	 * Modifica el valor de CantidadActos.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setCantidadActos(Long al_l)
	{
		il_cantidadActos = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad actos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getCantidadActos()
	{
		return il_cantidadActos;
	}

	/**
	 * Modifica el valor de CertificadosAsociados.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setCertificadosAsociados(Long al_l)
	{
		il_certificadosAsociados = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor certificados asociados.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getCertificadosAsociados()
	{
		return il_certificadosAsociados;
	}

	/**
	 * Modifica el valor de CirculoRegistral.
	 *
	 * @param acr_cr asigna el valor a la propiedad
	 */
	public void setCirculoRegistral(CirculoRegistral acr_cr)
	{
		icr_circuloRegistral = acr_cr;
	}

	/**
	 * Retorna Objeto o variable de valor circulo registral.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public CirculoRegistral getCirculoRegistral()
	{
		return icr_circuloRegistral;
	}

	/**
	 * Modifica el valor de CirculosRegistrales.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCirculosRegistrales(String as_s)
	{
		is_circulosRegistrales = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor circulos registrales.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCirculosRegistrales()
	{
		return is_circulosRegistrales;
	}

	/**
	 * Modifica el valor de CodigoRespuesta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigoRespuesta(String as_s)
	{
		is_codigoRespuesta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo respuesta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoRespuesta()
	{
		return is_codigoRespuesta;
	}

	/**
	 * Modifica el valor de CodigoSql.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigoSql(String as_s)
	{
		is_codigoSql = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo sql.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoSql()
	{
		return is_codigoSql;
	}

	/**
	 * Modifica el valor de ConceptoMayorValor.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setConceptoMayorValor(String as_s)
	{
		is_conceptoMayorValor = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor concepto mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getConceptoMayorValor()
	{
		return is_conceptoMayorValor;
	}

	/**
	 * Modifica el valor de Condicion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCondicion(String as_s)
	{
		is_condicion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor condicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCondicion()
	{
		return is_condicion;
	}

	/**
	 * Modifica el valor de Condiciones.
	 *
	 * @param acl_cl asigna el valor a la propiedad
	 */
	public void setCondiciones(Collection<Liquidacion> acl_cl)
	{
		icl_condiciones = acl_cl;
	}

	/**
	 * Retorna Objeto o variable de valor condiciones.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Liquidacion> getCondiciones()
	{
		return icl_condiciones;
	}

	/**
	 * Modifica el valor de Consecutivo.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setConsecutivo(int ai_i)
	{
		ii_consecutivo = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getConsecutivo()
	{
		return ii_consecutivo;
	}

	/**
	 * Modifica el valor de ConsecutivoAsociada.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setConsecutivoAsociada(Integer ai_i)
	{
		ii_consecutivoAsociada = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo asociada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getConsecutivoAsociada()
	{
		return ii_consecutivoAsociada;
	}

	/**
	 * Modifica el valor de ConsecutivoBarcode.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setConsecutivoBarcode(String as_s)
	{
		is_consecutivoBarcode = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo barcode.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getConsecutivoBarcode()
	{
		return is_consecutivoBarcode;
	}

	/**
	 * Modifica el valor de CuantiaActo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCuantiaActo(String as_s)
	{
		is_cuantiaActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cuantia acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCuantiaActo()
	{
		return is_cuantiaActo;
	}

	/**
	 * Modifica el valor de CuantiaActoDouble.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setCuantiaActoDouble(double ad_d)
	{
		id_cuantiaActoDouble = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor cuantia acto double.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public double getCuantiaActoDouble()
	{
		return id_cuantiaActoDouble;
	}

	/**
	 * Modifica el valor de DescripcionActo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcionActo(String as_s)
	{
		is_descripcionActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcionActo()
	{
		return is_descripcionActo;
	}

	/**
	 * Modifica el valor de DetalleReproduccionConstancia.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setDetalleReproduccionConstancia(boolean ab_b)
	{
		ib_detalleReproduccionConstancia = ab_b;
	}

	/**
	 * Valida la propiedad detalle reproduccion constancia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isDetalleReproduccionConstancia()
	{
		return ib_detalleReproduccionConstancia;
	}

	/**
	 * Modifica el valor de EnviadoNotificacionPago.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEnviadoNotificacionPago(String as_s)
	{
		is_enviadoNotificacionPago = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor enviado notificacion pago.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEnviadoNotificacionPago()
	{
		return is_enviadoNotificacionPago;
	}

	/**
	 * Modifica el valor de FechaExpedicionDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setFechaExpedicionDocumento(String as_s)
	{
		is_fechaExpedicionDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor fecha expedicion documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getFechaExpedicionDocumento()
	{
		return is_fechaExpedicionDocumento;
	}

	/**
	 * Modifica el valor de FechaLiquidacion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaLiquidacion(Date ad_d)
	{
		id_fechaLiquidacion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha liquidacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaLiquidacion()
	{
		return id_fechaLiquidacion;
	}

	/**
	 * Modifica el valor de FechaPago.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaPago(Date ad_d)
	{
		id_fechaPago = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha pago.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaPago()
	{
		return id_fechaPago;
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
	 * Modifica el valor de IdActo.
	 *
	 * @param as_s de as s
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
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s de as s
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
	 * Modifica el valor de IdCondicion.
	 *
	 * @param as_s de as s
	 */
	public void setIdCondicion(String as_s)
	{
		is_idCondicion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id condicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCondicion()
	{
		return is_idCondicion;
	}

	/**
	 * Modifica el valor de IdCondicionTarifa.
	 *
	 * @param as_s de as s
	 */
	public void setIdCondicionTarifa(String as_s)
	{
		is_idCondicionTarifa = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id condicion tarifa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCondicionTarifa()
	{
		return is_idCondicionTarifa;
	}

	/**
	 * Modifica el valor de IdItem.
	 *
	 * @param as_s de as s
	 */
	public void setIdItem(String as_s)
	{
		is_idItem = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id item.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdItem()
	{
		return is_idItem;
	}

	/**
	 * Modifica el valor de IdLiquidacion.
	 *
	 * @param as_s de as s
	 */
	public void setIdLiquidacion(String as_s)
	{
		is_idLiquidacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id liquidacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdLiquidacion()
	{
		return is_idLiquidacion;
	}

	/**
	 * Modifica el valor de IdLiquidacionAsociada.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdLiquidacionAsociada(String as_s)
	{
		is_idLiquidacionAsociada = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id liquidacion asociada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdLiquidacionAsociada()
	{
		return is_idLiquidacionAsociada;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s de as s
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
	 * Modifica el valor de IdRango.
	 *
	 * @param as_s de as s
	 */
	public void setIdRango(String as_s)
	{
		is_idRango = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id rango.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdRango()
	{
		return is_idRango;
	}

	/**
	 * Modifica el valor de IdSalario.
	 *
	 * @param as_s de as s
	 */
	public void setIdSalario(String as_s)
	{
		is_idSalario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id salario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSalario()
	{
		return is_idSalario;
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
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param as_s de as s
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
	 * Modifica el valor de IdTarifa.
	 *
	 * @param as_s de as s
	 */
	public void setIdTarifa(String as_s)
	{
		is_idTarifa = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tarifa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTarifa()
	{
		return is_idTarifa;
	}

	/**
	 * Modifica el valor de IdTarifaConservDocumental.
	 *
	 * @param as_s de as s
	 */
	public void setIdTarifaConservDocumental(String as_s)
	{
		is_idTarifaConservDocumental = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tarifa conserv documental.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTarifaConservDocumental()
	{
		return is_idTarifaConservDocumental;
	}

	/**
	 * Modifica el valor de IdTarifaFija.
	 *
	 * @param as_s de as s
	 */
	public void setIdTarifaFija(String as_s)
	{
		is_idTarifaFija = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tarifa fija.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTarifaFija()
	{
		return is_idTarifaFija;
	}

	/**
	 * Modifica el valor de IdTipoActo.
	 *
	 * @param as_s de as s
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
	 * Modifica el valor de IdTipoActoCondicion.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoActoCondicion(String as_s)
	{
		is_idTipoActoCondicion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo acto condicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoActoCondicion()
	{
		return is_idTipoActoCondicion;
	}

	/**
	 * Modifica el valor de IdTipoCanalOrigen.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoCanalOrigen(String as_s)
	{
		is_idTipoCanalOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo canal origen.
	 *
	 * @return Retorna el valor de la propiedad idTipoCanalOrigen
	 */
	public String getIdTipoCanalOrigen()
	{
		return is_idTipoCanalOrigen;
	}

	/**
	 * Modifica el valor de IdTipoEstadoLiquidacion.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoEstadoLiquidacion(String as_s)
	{
		is_idTipoEstadoLiquidacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo estado liquidacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoEstadoLiquidacion()
	{
		return is_idTipoEstadoLiquidacion;
	}

	/**
	 * Modifica el valor de IdTipoMayorValor.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoMayorValor(String as_s)
	{
		is_idTipoMayorValor = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoMayorValor()
	{
		return is_idTipoMayorValor;
	}

	/**
	 * Modifica el valor de IdTipoTarifaRegistral.
	 *
	 * @param as_s de as s
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
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s de as s
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIdTurnoCorrecciones(String as_s)
	{
		is_idTurnoCorrecciones = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdTurnoCorrecciones()
	{
		return is_idTurnoCorrecciones;
	}

	/**
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param as_s de as s
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
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
	 * Modifica el valor de LiquidacionItemCondicion.
	 *
	 * @param acl_cl asigna el valor a la propiedad
	 */
	public void setLiquidacionItemCondicion(Collection<Liquidacion> acl_cl)
	{
		icl_liquidacionItemCondicion = acl_cl;
	}

	/**
	 * Retorna Objeto o variable de valor liquidacion item condicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Liquidacion> getLiquidacionItemCondicion()
	{
		return icl_liquidacionItemCondicion;
	}

	/**
	 * Modifica el valor de Liquidar.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setLiquidar(boolean ab_b)
	{
		ib_liquidar = ab_b;
	}

	/**
	 * Valida la propiedad liquidar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isLiquidar()
	{
		return ib_liquidar;
	}

	/**
	 * Modifica el valor de MayorValorModificado.
	 *
	 * @param mayorValorModificado de mayor valor modificado
	 */
	public void setMayorValorModificado(boolean mayorValorModificado)
	{
		ib_mayorValorModificado = mayorValorModificado;
	}

	/**
	 * Valida la propiedad mayor valor modificado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isMayorValorModificado()
	{
		return ib_mayorValorModificado;
	}

	/**
	 * Modifica el valor de MotivoMayorValor.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setMotivoMayorValor(String as_s)
	{
		is_motivoMayorValor = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor motivo mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMotivoMayorValor()
	{
		return is_motivoMayorValor;
	}

	/**
	 * Modifica el valor de MotivoSaldoFavor.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setMotivoSaldoFavor(String as_s)
	{
		is_motivoSaldoFavor = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor motivo saldo favor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMotivoSaldoFavor()
	{
		return is_motivoSaldoFavor;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		is_nombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de NombreCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreCirculo(String as_s)
	{
		is_nombreCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreCirculo()
	{
		return is_nombreCirculo;
	}

	/**
	 * Modifica el valor de NombreOficinaOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreOficinaOrigen(String as_s)
	{
		is_nombreOficinaOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre oficina origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreOficinaOrigen()
	{
		return is_nombreOficinaOrigen;
	}

	/**
	 * Modifica el valor de NumeroCuantiaActo.
	 *
	 * @param abd_bd de abd bd
	 */
	public void setNumeroCuantiaActo(BigDecimal abd_bd)
	{
		ibd_numeroCuantiaActo = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor numero cuantia acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getNumeroCuantiaActo()
	{
		return ibd_numeroCuantiaActo;
	}

	/**
	 * Modifica el valor de NumeroDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroDocumento(String as_s)
	{
		is_numeroDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroDocumento()
	{
		return is_numeroDocumento;
	}

	/**
	 * Modifica el valor de NumeroMatriculas.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroMatriculas(String as_s)
	{
		is_numeroMatriculas = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero matriculas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroMatriculas()
	{
		return is_numeroMatriculas;
	}

	/**
	 * Modifica el valor de NumeroReciboLiquidacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroReciboLiquidacion(String as_s)
	{
		is_numeroReciboLiquidacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero recibo liquidacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroReciboLiquidacion()
	{
		return is_numeroReciboLiquidacion;
	}

	/**
	 * Modifica el valor de NumeroReferencia.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroReferencia(String as_s)
	{
		is_numeroReferencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero referencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroReferencia()
	{
		return is_numeroReferencia;
	}

	/**
	 * Modifica el valor de Pagada.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPagada(String as_s)
	{
		is_pagada = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor pagada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPagada()
	{
		return is_pagada;
	}

	/**
	 * Modifica el valor de Proceso.
	 *
	 * @param ap_p asigna el valor a la propiedad
	 */
	public void setProceso(Proceso ap_p)
	{
		ip_proceso = ap_p;
	}

	/**
	 * Retorna Objeto o variable de valor proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Proceso getProceso()
	{
		return ip_proceso;
	}

	/**
	 * Modifica el valor de ProcesoCertificados.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setProcesoCertificados(boolean ab_b)
	{
		ib_procesoCertificados = ab_b;
	}

	/**
	 * Valida la propiedad proceso certificados.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isProcesoCertificados()
	{
		return ib_procesoCertificados;
	}

	/**
	 * Modifica el valor de Registro.
	 *
	 * @param ar_r asigna el valor a la propiedad
	 */
	public void setRegistro(Registro ar_r)
	{
		ir_registro = ar_r;
	}

	/**
	 * Retorna Objeto o variable de valor registro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Registro getRegistro()
	{
		return ir_registro;
	}

	/**
	 * Modifica el valor de RegistroMayorValor.
	 *
	 * @param armv_rmv asigna el valor a la propiedad
	 */
	public void setRegistroMayorValor(RegistroMayorValor armv_rmv)
	{
		irmv_registroMayorValor = armv_rmv;
	}

	/**
	 * Retorna Objeto o variable de valor registro mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public RegistroMayorValor getRegistroMayorValor()
	{
		return irmv_registroMayorValor;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param arpe_rpe Valor que modifica la propiedad.
	 */
	public void setRegistroPagoExceso(RegistroPagoExceso arpe_rpe)
	{
		irpe_registroPagoExceso = arpe_rpe;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public RegistroPagoExceso getRegistroPagoExceso()
	{
		return irpe_registroPagoExceso;
	}

	/**
	 * Modifica el valor de ReglaCuantiaCantidad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setReglaCuantiaCantidad(String as_s)
	{
		is_reglaCuantiaCantidad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor regla cuantia cantidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getReglaCuantiaCantidad()
	{
		return is_reglaCuantiaCantidad;
	}

	/**
	 * Modifica el valor de RequiereCuantia.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRequiereCuantia(String as_s)
	{
		is_requiereCuantia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor requiere cuantia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRequiereCuantia()
	{
		return is_requiereCuantia;
	}

	/**
	 * Modifica el valor de Respuesta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRespuesta(String as_s)
	{
		is_respuesta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor respuesta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRespuesta()
	{
		return is_respuesta;
	}

	/**
	 * Modifica el valor de RespuestaBoolean.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setRespuestaBoolean(boolean ab_b)
	{
		ib_respuestaBoolean = ab_b;
	}

	/**
	 * Valida la propiedad respuesta boolean.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isRespuestaBoolean()
	{
		return ib_respuestaBoolean;
	}

	/**
	 * Modifica el valor de RespuestaLiquidacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRespuestaLiquidacion(String as_s)
	{
		is_respuestaLiquidacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor respuesta liquidacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRespuestaLiquidacion()
	{
		return is_respuestaLiquidacion;
	}

	/**
	 * Modifica el valor de Selected.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSelected(String as_s)
	{
		is_selected = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor selected.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSelected()
	{
		return is_selected;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad simularLiquidacion
	 */
	public void setSimularLiquidacion(boolean ab_b)
	{
		ib_simularLiquidacion = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad simularLiquidacion
	 */
	public boolean isSimularLiquidacion()
	{
		return ib_simularLiquidacion;
	}

	/**
	 * Modifica el valor de SinCantidadActos.
	 *
	 * @param ab_b de ab b
	 */
	public void setSinCantidadActos(boolean ab_b)
	{
		ib_sinCantidadActos = ab_b;
	}

	/**
	 * Valida la propiedad sin cantidad actos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSinCantidadActos()
	{
		return ib_sinCantidadActos;
	}

	/**
	 * Modifica el valor de Solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSolicitud(Solicitud as_s)
	{
		is_solicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Solicitud getSolicitud()
	{
		return is_solicitud;
	}

	/**
	 * Modifica el valor de SolicitudMatricula.
	 *
	 * @param acsm_csm asigna el valor a la propiedad
	 */
	public void setSolicitudMatricula(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_solicitudMatricula = acsm_csm;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<SolicitudMatricula> getSolicitudMatricula()
	{
		return icsm_solicitudMatricula;
	}

	/**
	 * Modifica el valor de SolicitudMatriculaActo.
	 *
	 * @param acsma_csma asigna el valor a la propiedad
	 */
	public void setSolicitudMatriculaActo(Collection<SolicitudMatriculaActo> acsma_csma)
	{
		this.icsma_solicitudMatriculaActo = acsma_csma;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud matricula acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<SolicitudMatriculaActo> getSolicitudMatriculaActo()
	{
		return icsma_solicitudMatriculaActo;
	}

	/**
	 * Modifica el valor de SubProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSubProceso(String as_s)
	{
		is_subProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor sub proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSubProceso()
	{
		return is_subProceso;
	}

	/**
	 * Modifica el valor de Tarifa.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTarifa(String as_s)
	{
		is_tarifa = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tarifa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTarifa()
	{
		return is_tarifa;
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
	 * Modifica el valor de TipoActoCondicion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoActoCondicion(String as_s)
	{
		is_tipoActoCondicion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo acto condicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoActoCondicion()
	{
		return is_tipoActoCondicion;
	}

	/**
	 * Modifica el valor de TipoDocInscribir.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoDocInscribir(String as_s)
	{
		is_tipoDocInscribir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo doc inscribir.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoDocInscribir()
	{
		return is_tipoDocInscribir;
	}

	/**
	 * Modifica el valor de TotalMayorValor.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setTotalMayorValor(BigDecimal abd_bd)
	{
		ibd_totalMayorValor = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor total mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getTotalMayorValor()
	{
		return ibd_totalMayorValor;
	}

	/**
	 * Modifica el valor de TotalSaldoFavor.
	 *
	 * @param abs_bd asigna el valor a la propiedad
	 */
	public void setTotalSaldoFavor(BigDecimal abs_bd)
	{
		ibd_totalSaldoFavor = abs_bd;
	}

	/**
	 * Retorna Objeto o variable de valor total saldo favor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getTotalSaldoFavor()
	{
		return ibd_totalSaldoFavor;
	}

	/**
	 * Modifica el valor de Tramite.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTramite(String as_s)
	{
		is_tramite = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tramite.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTramite()
	{
		return is_tramite;
	}

	/**
	 * Modifica el valor de Valor.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setValor(BigDecimal abd_bd)
	{
		ibd_valor = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getValor()
	{
		return ibd_valor;
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
	 * Modifica el valor de ValorConservDocumental.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setValorConservDocumental(Double ad_d)
	{
		id_valorConservDocumental = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor valor conserv documental.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getValorConservDocumental()
	{
		return id_valorConservDocumental;
	}

	/**
	 * Modifica el valor de ValorDocumental.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setValorDocumental(BigDecimal abd_bd)
	{
		ibd_valorDocumental = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor documental.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getValorDocumental()
	{
		return ibd_valorDocumental;
	}

	/**
	 * Modifica el valor de ValorFinal.
	 *
	 * @param ad_d de ad d
	 */
	public void setValorFinal(Double ad_d)
	{
		id_valorFinal = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor valor final.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getValorFinal()
	{
		return id_valorFinal;
	}

	/**
	 * Modifica el valor de ValorImpuestoFinal.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setValorImpuestoFinal(BigDecimal abd_bd)
	{
		ibd_valorImpuestoFinal = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor impuesto final.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getValorImpuestoFinal()
	{
		return ibd_valorImpuestoFinal;
	}

	/**
	 * Modifica el valor de ValorImpuestoMayorValor.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setValorImpuestoMayorValor(BigDecimal abd_bd)
	{
		ibd_valorImpuestoMayorValor = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor impuesto mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getValorImpuestoMayorValor()
	{
		return ibd_valorImpuestoMayorValor;
	}

	/**
	 * Modifica el valor de ValorImpuestoSaldoFavor.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setValorImpuestoSaldoFavor(BigDecimal abd_bd)
	{
		ibd_valorImpuestoSaldoFavor = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor impuesto saldo favor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getValorImpuestoSaldoFavor()
	{
		return ibd_valorImpuestoSaldoFavor;
	}

	/**
	 * Modifica el valor de ValorImpuestos.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setValorImpuestos(BigDecimal abd_bd)
	{
		ibd_valorImpuestos = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor impuestos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getValorImpuestos()
	{
		return ibd_valorImpuestos;
	}

	/**
	 * Modifica el valor de ValorImpuestosFinal.
	 *
	 * @param ad_d de ad d
	 */
	public void setValorImpuestosFinal(Double ad_d)
	{
		id_valorImpuestosFinal = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor valor impuestos final.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getValorImpuestosFinal()
	{
		return id_valorImpuestosFinal;
	}

	/**
	 * Modifica el valor de ValorMayorValor.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setValorMayorValor(BigDecimal abd_bd)
	{
		ibd_valorMayorValor = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getValorMayorValor()
	{
		return ibd_valorMayorValor;
	}

	/**
	 * Modifica el valor de ValorSaldoFavor.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setValorSaldoFavor(BigDecimal abd_bd)
	{
		ibd_valorSaldoFavor = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor saldo favor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getValorSaldoFavor()
	{
		return ibd_valorSaldoFavor;
	}

	/**
	 * Modifica el valor de ValorTarifa.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setValorTarifa(BigDecimal abd_bd)
	{
		ibd_valorTarifa = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor tarifa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getValorTarifa()
	{
		return ibd_valorTarifa;
	}

	/**
	 * Modifica el valor de ValorTotal.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setValorTotal(BigDecimal abd_bd)
	{
		ibd_valorTotal = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor total.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getValorTotal()
	{
		return ibd_valorTotal;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setVersion(String as_s)
	{
		is_version = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getVersion()
	{
		return is_version;
	}

	/**
	 * Modifica el valor de VersionTarifaConservDocumental.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setVersionTarifaConservDocumental(Long al_l)
	{
		il_versionTarifaConservDocumental = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version tarifa conserv documental.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getVersionTarifaConservDocumental()
	{
		return il_versionTarifaConservDocumental;
	}

	/**
	 * Modifica el valor de VersionTarifaFija.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setVersionTarifaFija(Long al_l)
	{
		il_versionTarifaFija = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version tarifa fija.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getVersionTarifaFija()
	{
		return il_versionTarifaFija;
	}

	/**
	 * Modifica el valor de VersionTarifaRegistral.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setVersionTarifaRegistral(Long al_l)
	{
		il_versionTarifaRegistral = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version tarifa registral.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getVersionTarifaRegistral()
	{
		return il_versionTarifaRegistral;
	}
}
