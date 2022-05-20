package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_REGISTRO_MAYOR_VALOR.
 *
 * @author hcastaneda
 */
public class RegistroMayorValor extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4623395718973886345L;

	/** Propiedad ibd cuantia. */
	private BigDecimal ibd_cuantia;

	/** Propiedad iibd valor avaluo. */
	private BigDecimal iibd_valorAvaluo;

	/** Propiedad id fecha cambio estado. */
	private Date id_fechaCambioEstado;

	/** Propiedad id fecha registro. */
	private Date id_fechaRegistro;

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

	/** Propiedad id valor liquidado. */
	private Double id_valorLiquidado;

	/** Propiedad il cantidad actos. */
	private Long il_cantidadActos;

	/** Propiedad il cantidad certif ant sistema. */
	private Long il_cantidadCertifAntSistema;

	/** Propiedad il consecutivo. */
	private Long il_consecutivo;

	/** Propiedad il respuesta ley 1943. */
	private String is_respuestaLey1943;

	/** Propiedad il tiempo canon. */
	private Long il_tiempoCanon;

	/** Propiedad is concepto cobro mayor valor. */
	private String is_conceptoCobroMayorValor;

	/** Propiedad is estado mayor valor. */
	private String is_estadoMayorValor;

	/** Propiedad is garantia hipotecaria. */
	private String is_garantiaHipotecaria;

	/** Propiedad is hijuela pasivo. */
	private String is_hijuelaPasivo;

	/** Propiedad is id acto. */
	private String is_idActo;

	/** Propiedad is id liquidacion. */
	private String is_idLiquidacion;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is id registro mayor valor. */
	private String is_idRegistroMayorValor;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id sub proceso. */
	private String is_idSubProceso;

	/** Propiedad is id tipo acto. */
	private String is_idTipoActo;

	/** Propiedad is id tipo adquisicion. */
	private String is_idTipoAdquisicion;

	/** Propiedad is id tipo mayor valor. */
	private String is_idTipoMayorValor;

	/** Propiedad is id tipo tarifa registral. */
	private String is_idTipoTarifaRegistral;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is madre cabeza. */
	private String is_madreCabeza;

	/** Propiedad is organismo internacional. */
	private String is_organismoInternacional;

	/** Propiedad is periodicidad cuantia. */
	private String is_periodicidadCuantia;

	/** Propiedad il version. */
	private long il_version;

	/** Propiedad il version tarifa registral. */
	private long il_versionTarifaRegistral;

	/**
	 * Constructor por defecto.
	 */
	public RegistroMayorValor()
	{
	}

	/**
	 * Constructor que recibe como parametros el id turno, estado mayor valor, usuario modificacion, ip modificacion.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_estadoMayorValor de as estado mayor valor
	 * @param as_usuarioModificacion de as usuario modificacion
	 * @param as_ipModificacion de as ip modificacion
	 */
	public RegistroMayorValor(
	    String as_idTurno, String as_estadoMayorValor, String as_usuarioModificacion, String as_ipModificacion
	)
	{
		setIdTurno(as_idTurno);
		setEstadoMayorValor(as_estadoMayorValor);
		setIdUsuarioModificacion(as_usuarioModificacion);
		setIpModificacion(as_ipModificacion);
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
	 * @return valor de la propiedad.
	 */
	public Double getAreaActo()
	{
		return id_areaActo;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ad_d Modifica el valor de la propiedad.
	 */
	public void setAreaTotal(Double ad_d)
	{
		id_areaTotal = ad_d;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return valor de la propiedad.
	 */
	public Double getAreaTotal()
	{
		return id_areaTotal;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ad_d Modifica el valor de la propiedad.
	 */
	public void setAreaTotalInmueble(Double ad_d)
	{
		id_areaTotalInmueble = ad_d;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return valor de la propiedad.
	 */
	public Double getAreaTotalInmueble()
	{
		return id_areaTotalInmueble;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ad_d Modifica el valor de la propiedad.
	 */
	public void setAreaTransferir(Double ad_d)
	{
		id_areaTransferir = ad_d;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return valor de la propiedad.
	 */
	public Double getAreaTransferir()
	{
		return id_areaTransferir;
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
	 * @return valor de la propiedad.
	 */
	public Long getCantidadCertifAntSistema()
	{
		return il_cantidadCertifAntSistema;
	}

	/**
	 * Modifica el valor de ConceptoCobroMayorValor.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setConceptoCobroMayorValor(String as_s)
	{
		is_conceptoCobroMayorValor = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor concepto cobro mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getConceptoCobroMayorValor()
	{
		return is_conceptoCobroMayorValor;
	}

	/**
	 * Modifica el valor de Consecutivo.
	 *
	 * @param al_l de al l
	 */
	public void setConsecutivo(Long al_l)
	{
		il_consecutivo = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getConsecutivo()
	{
		return il_consecutivo;
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
	 * Modifica el valor de EstadoMayorValor.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstadoMayorValor(String as_s)
	{
		is_estadoMayorValor = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoMayorValor()
	{
		return is_estadoMayorValor;
	}

	/**
	 * Modifica el valor de FechaCambioEstado.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaCambioEstado(Date ad_d)
	{
		id_fechaCambioEstado = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha cambio estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaCambioEstado()
	{
		return id_fechaCambioEstado;
	}

	/**
	 * Modifica el valor de FechaRegistro.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaRegistro(Date ad_d)
	{
		id_fechaRegistro = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha registro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaRegistro()
	{
		return id_fechaRegistro;
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
	 * @return valor de la propiedad.
	 */
	public String getGarantiaHipotecaria()
	{
		return is_garantiaHipotecaria;
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
	 * @return valor de la propiedad.
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
	 * Modifica el valor de IdLiquidacion.
	 *
	 * @param as_s asigna el valor a la propiedad
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
	 * Modifica el valor de IdRegistroMayorValor.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdRegistroMayorValor(String as_s)
	{
		is_idRegistroMayorValor = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id registro mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdRegistroMayorValor()
	{
		return is_idRegistroMayorValor;
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
	 * Modifica el valor de IdSubProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSubProceso(String as_s)
	{
		is_idSubProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sub proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubProceso()
	{
		return is_idSubProceso;
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
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Modifica el valor de la propiedad.
	 */
	public void setIdTipoAdquisicion(String as_s)
	{
		is_idTipoAdquisicion = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return valor de la propiedad.
	 */
	public String getIdTipoAdquisicion()
	{
		return is_idTipoAdquisicion;
	}

	/**
	 * Modifica el valor de IdTipoMayorValor.
	 *
	 * @param as_s asigna el valor a la propiedad
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
	 * @return valor de la propiedad.
	 */
	public String getIdTipoTarifaRegistral()
	{
		return is_idTipoTarifaRegistral;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
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
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Modifica el valor de la propiedad.
	 */
	public void setMadreCabeza(String as_s)
	{
		is_madreCabeza = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return valor de la propiedad.
	 */
	public String getMadreCabeza()
	{
		return is_madreCabeza;
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
	 * @return valor de la propiedad.
	 */
	public String getOrganismoInternacional()
	{
		return is_organismoInternacional;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Modifica el valor de la propiedad.
	 */
	public void setPeriodicidadCuantia(String as_s)
	{
		is_periodicidadCuantia = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return valor de la propiedad.
	 */
	public String getPeriodicidadCuantia()
	{
		return is_periodicidadCuantia;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ad_d Modifica el valor de la propiedad.
	 */
	public void setPorcentajeTransferencia(Double ad_d)
	{
		id_porcentajeTransferencia = ad_d;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return valor de la propiedad.
	 */
	public Double getPorcentajeTransferencia()
	{
		return id_porcentajeTransferencia;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Modifica el valor de la propiedad.
	 */
	public void setRespuestaLey1943(String as_s)
	{
		is_respuestaLey1943 = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return valor de la propiedad.
	 */
	public String getRespuestaLey1943()
	{
		return is_respuestaLey1943;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param al_l Modifica el valor de la propiedad.
	 */
	public void setTiempoCanon(Long al_l)
	{
		il_tiempoCanon = al_l;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return valor de la propiedad.
	 */
	public Long getTiempoCanon()
	{
		return il_tiempoCanon;
	}

	/**
	 * Modifica el valor de ValorAvaluo.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setValorAvaluo(BigDecimal abd_bd)
	{
		iibd_valorAvaluo = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor avaluo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getValorAvaluo()
	{
		return iibd_valorAvaluo;
	}

	/**
	 * Modifica el valor de ValorLiquidado.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setValorLiquidado(Double ad_d)
	{
		id_valorLiquidado = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor valor liquidado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getValorLiquidado()
	{
		return id_valorLiquidado;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setVersion(long al_l)
	{
		il_version = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getVersion()
	{
		return il_version;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param al_l Modifica el valor de la propiedad.
	 */
	public void setVersionTarifaRegistral(long al_l)
	{
		il_versionTarifaRegistral = al_l;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return valor de la propiedad.
	 */
	public long getVersionTarifaRegistral()
	{
		return il_versionTarifaRegistral;
	}
}
