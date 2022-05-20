package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TIPO_ACTO.
 *
 * @author nguaneme
 */
public class TipoActo extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8277648416247850794L;

	/** Propiedad ibd cuantia acto. */
	private BigDecimal ibd_cuantiaActo;

	/** Propiedad ibd valor avaluo. */
	private BigDecimal ibd_valorAvaluo;

	/** Propiedad ii cantidad actos. */
	private Integer ii_cantidadActos;

	/** Propiedad il id etapa inicial. */
	private Long il_idEtapaInicial;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is acto sin cuantia. */
	private String is_actoSinCuantia;

	/** Propiedad is alerta titular. */
	private String is_alertaTitular;

	/** Propiedad is apertura matricula. */
	private String is_aperturaMatricula;

	/** Propiedad is aplica desborde. */
	private String is_aplicaDesborde;

	/** Propiedad is aplica tarifa retencion documental. */
	private String is_aplicaTarifaRetencionDocumental;

	/** Propiedad is avaluo. */
	private String is_avaluo;

	/** Propiedad is cambio tipo cuantia. */
	private String is_cambioTipoCuantia;

	/** Propiedad is certificado obligatorio. */
	private String is_certificadoObligatorio;

	/** Propiedad is cuotaparte donacion. */
	private String is_cuotaparteDonacionString;

	/** Propiedad is genera segregacion. */
	private String is_generaSegregacion;

	/** Propiedad is habilitar secuencia. */
	private String is_habilitarSecuencia;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id tarifa fija. */
	private String is_idTarifaFija;

	/** Propiedad is id tipo acto. */
	private String is_idTipoActo;

	/** Propiedad is id tipo calculo. */
	private String is_idTipoCalculo;

	/** Propiedad is impuesto registro. */
	private String is_impuestoRegistro;

	/** Propiedad is ind mayor valor. */
	private String is_indMayorValor;

	/** Propiedad is inscripcion adicional. */
	private String is_inscripcionAdicional;

	/** Propiedad is linea produccion. */
	private String is_lineaProduccion;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is numero proceso. */
	private String is_numeroProceso;

	/** Propiedad is prestaciones periodicas. */
	private String is_prestacionesPeriodicasString;

	/** Propiedad is referencia. */
	private String is_referencia;

	/** Propiedad is requiere cuantia. */
	private String is_requiereCuantia;

	/** Propiedad is requiere matricula. */
	private String is_requiereMatricula;

	/** Propiedad is requiere tipo adquisicion. */
	private String is_requiereTipoAdquisicion;

	/** Propiedad is sumatoria area. */
	private String is_sumatoriaArea;

	/** Propiedad is sumatoria coeficiente. */
	private String is_sumatoriaCoeficiente;

	/** Propiedad is tarifa exenta. */
	private String is_tarifaExenta;

	/** Propiedad is validar area. */
	private String is_validarArea;

	/** Propiedad ib acto segregacion. */
	private boolean ib_actoSegregacion;

	/** Propiedad ib acto selected. */
	private boolean ib_actoSelected;

	/** Propiedad ib certificado obligatorio. */
	private boolean ib_certificadoObligatorio;

	/** Propiedad ib cuotaparte donacion. */
	private boolean ib_cuotaparteDonacion;

	/** Propiedad ib deshabilitar. */
	private boolean ib_deshabilitar;

	/** Propiedad ib prestaciones periodicas. */
	private boolean ib_prestacionesPeriodicas;

	/** Propiedad ib requiere documentos. */
	private boolean ib_requiereDocumentos;

	/** Propiedad ib validar area. */
	private boolean ib_validarArea;

	/** Propiedad il version. */
	private long il_version;

	/**
	 * Constructor por defecto.
	 */
	public TipoActo()
	{
	}

	/**
	 * Constructor que recive el id de tipo de acto como parametro.
	 *
	 * @param as_idTipoActo de as id tipo acto
	 */
	public TipoActo(String as_idTipoActo)
	{
		is_idTipoActo = as_idTipoActo;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param activo asigna el valor a la propiedad
	 */
	public void setActivo(String activo)
	{
		this.is_activo = activo;
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
	 * Modifica el valor de ActoSegregacion.
	 *
	 * @param actoSegregacion asigna el valor a la propiedad
	 */
	public void setActoSegregacion(boolean actoSegregacion)
	{
		this.ib_actoSegregacion = actoSegregacion;
	}

	/**
	 * Valida la propiedad acto segregacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isActoSegregacion()
	{
		return ib_actoSegregacion;
	}

	/**
	 * Modifica el valor de ActoSelected.
	 *
	 * @param actoSelected asigna el valor a la propiedad
	 */
	public void setActoSelected(boolean actoSelected)
	{
		this.ib_actoSelected = actoSelected;
	}

	/**
	 * Valida la propiedad acto selected.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isActoSelected()
	{
		return ib_actoSelected;
	}

	/**
	 * Modifica el valor de ActoSinCuantia.
	 *
	 * @param actoSinCuantia asigna el valor a la propiedad
	 */
	public void setActoSinCuantia(String actoSinCuantia)
	{
		this.is_actoSinCuantia = actoSinCuantia;
	}

	/**
	 * Retorna Objeto o variable de valor acto sin cuantia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActoSinCuantia()
	{
		return is_actoSinCuantia;
	}

	/**
	 * Modifica el valor de AlertaTitular.
	 *
	 * @param alertaTitular asigna el valor a la propiedad
	 */
	public void setAlertaTitular(String alertaTitular)
	{
		this.is_alertaTitular = alertaTitular;
	}

	/**
	 * Retorna Objeto o variable de valor alerta titular.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAlertaTitular()
	{
		return is_alertaTitular;
	}

	/**
	 * Modifica el valor de AperturaMatricula.
	 *
	 * @param aperturaMatricula asigna el valor a la propiedad
	 */
	public void setAperturaMatricula(String aperturaMatricula)
	{
		this.is_aperturaMatricula = aperturaMatricula;
	}

	/**
	 * Retorna Objeto o variable de valor apertura matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAperturaMatricula()
	{
		return is_aperturaMatricula;
	}

	/**
	 * Modifica el valor de AplicaDesborde.
	 *
	 * @param aplicaDesborde asigna el valor a la propiedad
	 */
	public void setAplicaDesborde(String aplicaDesborde)
	{
		this.is_aplicaDesborde = aplicaDesborde;
	}

	/**
	 * Retorna Objeto o variable de valor aplica desborde.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAplicaDesborde()
	{
		return is_aplicaDesborde;
	}

	/**
	 * Modifica el valor de AplicaTarifaRetencionDocumental.
	 *
	 * @param aplicaTarifaRetencionDocumental asigna el valor a la propiedad
	 */
	public void setAplicaTarifaRetencionDocumental(String aplicaTarifaRetencionDocumental)
	{
		this.is_aplicaTarifaRetencionDocumental = aplicaTarifaRetencionDocumental;
	}

	/**
	 * Retorna Objeto o variable de valor aplica tarifa retencion documental.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAplicaTarifaRetencionDocumental()
	{
		return is_aplicaTarifaRetencionDocumental;
	}

	/**
	 * Modifica el valor de Avaluo.
	 *
	 * @param avaluo asigna el valor a la propiedad
	 */
	public void setAvaluo(String avaluo)
	{
		this.is_avaluo = avaluo;
	}

	/**
	 * Retorna Objeto o variable de valor avaluo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAvaluo()
	{
		return is_avaluo;
	}

	/**
	 * Modifica el valor de CambioTipoCuantia.
	 *
	 * @param cambioTipoCuantia asigna el valor a la propiedad
	 */
	public void setCambioTipoCuantia(String cambioTipoCuantia)
	{
		this.is_cambioTipoCuantia = cambioTipoCuantia;
	}

	/**
	 * Retorna Objeto o variable de valor cambio tipo cuantia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCambioTipoCuantia()
	{
		return is_cambioTipoCuantia;
	}

	/**
	 * Modifica el valor de CantidadActos.
	 *
	 * @param cantidadActos asigna el valor a la propiedad
	 */
	public void setCantidadActos(Integer cantidadActos)
	{
		this.ii_cantidadActos = cantidadActos;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad actos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getCantidadActos()
	{
		return ii_cantidadActos;
	}

	/**
	 * Modifica el valor de CertificadoObligatorio.
	 *
	 * @param certificadoObligatorio asigna el valor a la propiedad
	 */
	public void setCertificadoObligatorio(boolean certificadoObligatorio)
	{
		this.ib_certificadoObligatorio = certificadoObligatorio;
	}

	/**
	 * Valida la propiedad certificado obligatorio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isCertificadoObligatorio()
	{
		return ib_certificadoObligatorio;
	}

	/**
	 * Modifica el valor de CertificadoObligatorioString.
	 *
	 * @param certificadoObligatorio asigna el valor a la propiedad
	 */
	public void setCertificadoObligatorioString(String certificadoObligatorio)
	{
		this.is_certificadoObligatorio = certificadoObligatorio;
	}

	/**
	 * Retorna Objeto o variable de valor certificado obligatorio string.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCertificadoObligatorioString()
	{
		return is_certificadoObligatorio;
	}

	/**
	 * Modifica el valor de CuantiaActo.
	 *
	 * @param cuantiaActo asigna el valor a la propiedad
	 */
	public void setCuantiaActo(BigDecimal cuantiaActo)
	{
		this.ibd_cuantiaActo = cuantiaActo;
	}

	/**
	 * Retorna Objeto o variable de valor cuantia acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getCuantiaActo()
	{
		return ibd_cuantiaActo;
	}

	/**
	 * Modifica el valor de CuotaparteDonacion.
	 *
	 * @param ab_b de ab b
	 */
	public void setCuotaparteDonacion(boolean ab_b)
	{
		ib_cuotaparteDonacion = ab_b;
	}

	/**
	 * Valida la propiedad cuotaparte donacion.
	 *
	 * @return Retorna el valor de la propiedad cuotaparteDonacion
	 */
	public boolean isCuotaparteDonacion()
	{
		return ib_cuotaparteDonacion;
	}

	/**
	 * Modifica el valor de CuotaparteDonacion.
	 *
	 * @param as_s de as s
	 */
	public void setCuotaparteDonacionString(String as_s)
	{
		is_cuotaparteDonacionString = as_s;
	}

	/**
	 * Valida la propiedad cuotaparte donacion.
	 *
	 * @return Retorna el valor de la propiedad cuotaparteDonacion
	 */
	public String getCuotaparteDonacionString()
	{
		return is_cuotaparteDonacionString;
	}

	/**
	 * Modifica el valor de Deshabilitar.
	 *
	 * @param deshabilitar asigna el valor a la propiedad
	 */
	public void setDeshabilitar(boolean deshabilitar)
	{
		this.ib_deshabilitar = deshabilitar;
	}

	/**
	 * Valida la propiedad deshabilitar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isDeshabilitar()
	{
		return ib_deshabilitar;
	}

	/**
	 * Modifica el valor de GeneraSegregacion.
	 *
	 * @param generaSegregacion asigna el valor a la propiedad
	 */
	public void setGeneraSegregacion(String generaSegregacion)
	{
		this.is_generaSegregacion = generaSegregacion;
	}

	/**
	 * Retorna Objeto o variable de valor genera segregacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getGeneraSegregacion()
	{
		return is_generaSegregacion;
	}

	/**
	 * Modifica el valor de HabilitarSecuencia.
	 *
	 * @param habilitarSecuencia asigna el valor a la propiedad
	 */
	public void setHabilitarSecuencia(String habilitarSecuencia)
	{
		this.is_habilitarSecuencia = habilitarSecuencia;
	}

	/**
	 * Retorna Objeto o variable de valor habilitar secuencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getHabilitarSecuencia()
	{
		return is_habilitarSecuencia;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param idCirculo asigna el valor a la propiedad
	 */
	public void setIdCirculo(String idCirculo)
	{
		this.is_idCirculo = idCirculo;
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
	 * Modifica el valor de IdEtapaInicial.
	 *
	 * @param idEtapaInicial asigna el valor a la propiedad
	 */
	public void setIdEtapaInicial(Long idEtapaInicial)
	{
		this.il_idEtapaInicial = idEtapaInicial;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa inicial.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdEtapaInicial()
	{
		return il_idEtapaInicial;
	}

	/**
	 * Modifica el valor de IdTarifaFija.
	 *
	 * @param idTarifaFija asigna el valor a la propiedad
	 */
	public void setIdTarifaFija(String idTarifaFija)
	{
		this.is_idTarifaFija = idTarifaFija;
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
	 * @param idTipoActo asigna el valor a la propiedad
	 */
	public void setIdTipoActo(String idTipoActo)
	{
		this.is_idTipoActo = idTipoActo;
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
	 * Modifica el valor de IdTipoCalculo.
	 *
	 * @param idTipoCalculo asigna el valor a la propiedad
	 */
	public void setIdTipoCalculo(String idTipoCalculo)
	{
		this.is_idTipoCalculo = idTipoCalculo;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo calculo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoCalculo()
	{
		return is_idTipoCalculo;
	}

	/**
	 * Modifica el valor de ImpuestoRegistro.
	 *
	 * @param impuestoRegistro asigna el valor a la propiedad
	 */
	public void setImpuestoRegistro(String impuestoRegistro)
	{
		this.is_impuestoRegistro = impuestoRegistro;
	}

	/**
	 * Retorna Objeto o variable de valor impuesto registro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getImpuestoRegistro()
	{
		return is_impuestoRegistro;
	}

	/**
	 * Modifica el valor de IndMayorValor.
	 *
	 * @param as_s de as s
	 */
	public void setIndMayorValor(String as_s)
	{
		is_indMayorValor = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor ind mayor valor.
	 *
	 * @return el valor de ind mayor valor
	 */
	public String getIndMayorValor()
	{
		return is_indMayorValor;
	}

	/**
	 * Modifica el valor de InscripcionAdicional.
	 *
	 * @param inscripcionAdicional asigna el valor a la propiedad
	 */
	public void setInscripcionAdicional(String inscripcionAdicional)
	{
		this.is_inscripcionAdicional = inscripcionAdicional;
	}

	/**
	 * Retorna Objeto o variable de valor inscripcion adicional.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getInscripcionAdicional()
	{
		return is_inscripcionAdicional;
	}

	/**
	 * Modifica el valor de LineaProduccion.
	 *
	 * @param lineaProduccion asigna el valor a la propiedad
	 */
	public void setLineaProduccion(String lineaProduccion)
	{
		this.is_lineaProduccion = lineaProduccion;
	}

	/**
	 * Retorna Objeto o variable de valor linea produccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLineaProduccion()
	{
		return is_lineaProduccion;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param nombre asigna el valor a la propiedad
	 */
	public void setNombre(String nombre)
	{
		this.is_nombre = nombre;
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
	 * Modifica el valor de NumeroProceso.
	 *
	 * @param numeroProceso asigna el valor a la propiedad
	 */
	public void setNumeroProceso(String numeroProceso)
	{
		this.is_numeroProceso = numeroProceso;
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
	 * Modifica el valor de PrestacionesPeriodicas.
	 *
	 * @param ab_b de ab b
	 */
	public void setPrestacionesPeriodicas(boolean ab_b)
	{
		ib_prestacionesPeriodicas = ab_b;
	}

	/**
	 * Valida la propiedad prestaciones periodicas.
	 *
	 * @return Retorna el valor de la propiedad prestacionesPeriodicas
	 */
	public boolean isPrestacionesPeriodicas()
	{
		return ib_prestacionesPeriodicas;
	}

	/**
	 * Modifica el valor de PrestacionesPeriodicas.
	 *
	 * @param as_s de as s
	 */
	public void setPrestacionesPeriodicasString(String as_s)
	{
		is_prestacionesPeriodicasString = as_s;
	}

	/**
	 * Valida la propiedad prestaciones periodicas.
	 *
	 * @return Retorna el valor de la propiedad prestacionesPeriodicas
	 */
	public String getPrestacionesPeriodicasString()
	{
		return is_prestacionesPeriodicasString;
	}

	/**
	 * Modifica el valor de Referencia.
	 *
	 * @param referencia asigna el valor a la propiedad
	 */
	public void setReferencia(String referencia)
	{
		this.is_referencia = referencia;
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
	 * Modifica el valor de RequiereCuantia.
	 *
	 * @param requiereCuantia asigna el valor a la propiedad
	 */
	public void setRequiereCuantia(String requiereCuantia)
	{
		this.is_requiereCuantia = requiereCuantia;
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
	 * Modifica el valor de RequiereDocumentos.
	 *
	 * @param requiereDocumentos asigna el valor a la propiedad
	 */
	public void setRequiereDocumentos(boolean requiereDocumentos)
	{
		this.ib_requiereDocumentos = requiereDocumentos;
	}

	/**
	 * Valida la propiedad requiere documentos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isRequiereDocumentos()
	{
		return ib_requiereDocumentos;
	}

	/**
	 * Modifica el valor de RequiereMatricula.
	 *
	 * @param requiereMatricula asigna el valor a la propiedad
	 */
	public void setRequiereMatricula(String requiereMatricula)
	{
		this.is_requiereMatricula = requiereMatricula;
	}

	/**
	 * Retorna Objeto o variable de valor requiere matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRequiereMatricula()
	{
		return is_requiereMatricula;
	}

	/**
	 * Modifica el valor de RequiereTipoAdquisicion.
	 *
	 * @param requiereTipoAdquisicion asigna el valor a la propiedad
	 */
	public void setRequiereTipoAdquisicion(String requiereTipoAdquisicion)
	{
		this.is_requiereTipoAdquisicion = requiereTipoAdquisicion;
	}

	/**
	 * Retorna Objeto o variable de valor requiere tipo adquisicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRequiereTipoAdquisicion()
	{
		return is_requiereTipoAdquisicion;
	}

	/**
	 * Modifica el valor de SumatoriaArea.
	 *
	 * @param sumatoriaArea asigna el valor a la propiedad
	 */
	public void setSumatoriaArea(String sumatoriaArea)
	{
		this.is_sumatoriaArea = sumatoriaArea;
	}

	/**
	 * Retorna Objeto o variable de valor sumatoria area.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSumatoriaArea()
	{
		return is_sumatoriaArea;
	}

	/**
	 * Modifica el valor de SumatoriaCoeficiente.
	 *
	 * @param sumatoriaCoeficiente asigna el valor a la propiedad
	 */
	public void setSumatoriaCoeficiente(String sumatoriaCoeficiente)
	{
		this.is_sumatoriaCoeficiente = sumatoriaCoeficiente;
	}

	/**
	 * Retorna Objeto o variable de valor sumatoria coeficiente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSumatoriaCoeficiente()
	{
		return is_sumatoriaCoeficiente;
	}

	/**
	 * Modifica el valor de TarifaExenta.
	 *
	 * @param tarifaExenta asigna el valor a la propiedad
	 */
	public void setTarifaExenta(String tarifaExenta)
	{
		this.is_tarifaExenta = tarifaExenta;
	}

	/**
	 * Retorna Objeto o variable de valor tarifa exenta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTarifaExenta()
	{
		return is_tarifaExenta;
	}

	/**
	 * Modifica el valor de ValidarArea.
	 *
	 * @param validarArea asigna el valor a la propiedad
	 */
	public void setValidarArea(boolean validarArea)
	{
		this.ib_validarArea = validarArea;
	}

	/**
	 * Valida la propiedad validar area.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isValidarArea()
	{
		return ib_validarArea;
	}

	/**
	 * Modifica el valor de ValidarAreaString.
	 *
	 * @param validarArea asigna el valor a la propiedad
	 */
	public void setValidarAreaString(String validarArea)
	{
		this.is_validarArea = validarArea;
	}

	/**
	 * Retorna Objeto o variable de valor validar area string.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getValidarAreaString()
	{
		return is_validarArea;
	}

	/**
	 * Modifica el valor de ValorAvaluo.
	 *
	 * @param valorAvaluo asigna el valor a la propiedad
	 */
	public void setValorAvaluo(BigDecimal valorAvaluo)
	{
		this.ibd_valorAvaluo = valorAvaluo;
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
	public void setVersion(long version)
	{
		this.il_version = version;
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
}
