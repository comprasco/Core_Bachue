package com.bachue.snr.prosnr21.model.pgn;

import java.util.Date;


/**
 * Clase que representa un registro en la tabla SDB_CON_INGRESO_AFECTACION del módulo de conciliaciones.
 *
 * @author Kevin Pulido
 */
public class AfectacionPrestacionServicio extends com.bachue.snr.prosnr01.model.auditoria.Auditoria implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4194116331782919325L;

	/** Propiedad fecha liquidacion. */
	private Date id_fechaLiquidacion;

	/** Propiedad id item. */
	private Double id_idItem;

	/** Propiedad id registro. */
	private Double id_idRegistro;

	/** Propiedad valor conserv documental. */
	private Double id_valorConservDocumental;

	/** Propiedad valor ingresos snr. */
	private Double id_valorIngresosSnr;

	/** Propiedad valor otras entidades. */
	private Double id_valorOtrasEntidades;

	/** Propiedad valor por rubro. */
	private Double id_valorPorRubro;

	/** Propiedad valor total. */
	private Double id_valorTotal;

	/** Propiedad activo. */
	private String is_activo;

	/** Propiedad id archivo. */
	private String is_idArchivo;

	/** Propiedad id circulo matricula. */
	private String is_idCirculoMatricula;

	/** Propiedad id ingreso afectacion. */
	private String is_idIngresoAfectacion;

	/** Propiedad id matricula. */
	private String is_idMatricula;

	/** Propiedad id periodo corte rpt. */
	private String is_idPeriodoCorteRpt;

	/** Propiedad id proceso. */
	private String is_idProceso;

	/** Propiedad id rubro. */
	private String is_idRubro;

	/** Propiedad id rubro conservacion documental. */
	private String is_idRubroConservacionDocumental;

	/** Propiedad id subproceso. */
	private String is_idSubproceso;

	/** Propiedad id turno. */
	private String is_idTurno;

	/** Propiedad nir. */
	private String is_nir;

	/** Propiedad nombre proceso. */
	private String is_nombreProceso;

	/**  Propiedad nombre rubro. */
	private String is_nombreRubro;

	/** Propiedad nombre subproceso. */
	private String is_nombreSubproceso;

	/** Propiedad numero referencia. */
	private String is_numeroReferencia;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_activo de as activo
	 */
	public void setActivo(String as_activo)
	{
		this.is_activo = as_activo;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return el valor de activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de FechaLiquidacion.
	 *
	 * @param ad_fechaLiquidacion de ad fecha liquidacion
	 */
	public void setFechaLiquidacion(Date ad_fechaLiquidacion)
	{
		this.id_fechaLiquidacion = ad_fechaLiquidacion;
	}

	/**
	 * Retorna Objeto o variable de valor fecha liquidacion.
	 *
	 * @return el valor de fecha liquidacion
	 */
	public Date getFechaLiquidacion()
	{
		return id_fechaLiquidacion;
	}

	/**
	 * Modifica el valor de IdArchivo.
	 *
	 * @param as_idArchivo de as id archivo
	 */
	public void setIdArchivo(String as_idArchivo)
	{
		this.is_idArchivo = as_idArchivo;
	}

	/**
	 * Retorna Objeto o variable de valor id archivo.
	 *
	 * @return el valor de id archivo
	 */
	public String getIdArchivo()
	{
		return is_idArchivo;
	}

	/**
	 * Modifica el valor de IdCirculoMatricula.
	 *
	 * @param as_idCirculoMatricula de as id circulo matricula
	 */
	public void setIdCirculoMatricula(String as_idCirculoMatricula)
	{
		this.is_idCirculoMatricula = as_idCirculoMatricula;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo matricula.
	 *
	 * @return el valor de id circulo matricula
	 */
	public String getIdCirculoMatricula()
	{
		return is_idCirculoMatricula;
	}

	/**
	 * Modifica el valor de IdIngresoAfectacion.
	 *
	 * @param as_idIngresoAfectacion de as id ingreso afectacion
	 */
	public void setIdIngresoAfectacion(String as_idIngresoAfectacion)
	{
		this.is_idIngresoAfectacion = as_idIngresoAfectacion;
	}

	/**
	 * Retorna Objeto o variable de valor id ingreso afectacion.
	 *
	 * @return el valor de id ingreso afectacion
	 */
	public String getIdIngresoAfectacion()
	{
		return is_idIngresoAfectacion;
	}

	/**
	 * Modifica el valor de IdItem.
	 *
	 * @param ad_idItem de ad id item
	 */
	public void setIdItem(Double ad_idItem)
	{
		this.id_idItem = ad_idItem;
	}

	/**
	 * Retorna Objeto o variable de valor id item.
	 *
	 * @return el valor de id item
	 */
	public Double getIdItem()
	{
		return id_idItem;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param as_idMatricula de as id matricula
	 */
	public void setIdMatricula(String as_idMatricula)
	{
		this.is_idMatricula = as_idMatricula;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return el valor de id matricula
	 */
	public String getIdMatricula()
	{
		return is_idMatricula;
	}

	/**
	 * Modifica el valor de IdPeriodoCorteRpt.
	 *
	 * @param as_idPeriodoCorteRpt de as id periodo corte rpt
	 */
	public void setIdPeriodoCorteRpt(String as_idPeriodoCorteRpt)
	{
		this.is_idPeriodoCorteRpt = as_idPeriodoCorteRpt;
	}

	/**
	 * Retorna Objeto o variable de valor id periodo corte rpt.
	 *
	 * @return el valor de id periodo corte rpt
	 */
	public String getIdPeriodoCorteRpt()
	{
		return is_idPeriodoCorteRpt;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_idProceso de as id proceso
	 */
	public void setIdProceso(String as_idProceso)
	{
		this.is_idProceso = as_idProceso;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return el valor de id proceso
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de IdRegistro.
	 *
	 * @param ad_idRegistro de ad id registro
	 */
	public void setIdRegistro(Double ad_idRegistro)
	{
		this.id_idRegistro = ad_idRegistro;
	}

	/**
	 * Retorna Objeto o variable de valor id registro.
	 *
	 * @return el valor de id registro
	 */
	public Double getIdRegistro()
	{
		return id_idRegistro;
	}

	/**
	 * Modifica el valor de IdRubro.
	 *
	 * @param as_idRubro de as id rubro
	 */
	public void setIdRubro(String as_idRubro)
	{
		this.is_idRubro = as_idRubro;
	}

	/**
	 * Retorna Objeto o variable de valor id rubro.
	 *
	 * @return el valor de id rubro
	 */
	public String getIdRubro()
	{
		return is_idRubro;
	}

	/**
	 * Modifica el valor de IdRubroConservacionDocumental.
	 *
	 * @param as_idRubroConservacionDocumental de as id rubro conservacion documental
	 */
	public void setIdRubroConservacionDocumental(String as_idRubroConservacionDocumental)
	{
		this.is_idRubroConservacionDocumental = as_idRubroConservacionDocumental;
	}

	/**
	 * Retorna Objeto o variable de valor id rubro conservacion documental.
	 *
	 * @return el valor de id rubro conservacion documental
	 */
	public String getIdRubroConservacionDocumental()
	{
		return is_idRubroConservacionDocumental;
	}

	/**
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param as_idSubproceso de as id subproceso
	 */
	public void setIdSubproceso(String as_idSubproceso)
	{
		this.is_idSubproceso = as_idSubproceso;
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso.
	 *
	 * @return el valor de id subproceso
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_idTurno de as id turno
	 */
	public void setIdTurno(String as_idTurno)
	{
		this.is_idTurno = as_idTurno;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_nir de as nir
	 */
	public void setNir(String as_nir)
	{
		this.is_nir = as_nir;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de NombreProceso.
	 *
	 * @param as_nombreProceso de as nombre proceso
	 */
	public void setNombreProceso(String as_nombreProceso)
	{
		this.is_nombreProceso = as_nombreProceso;
	}

	/**
	 * Retorna Objeto o variable de valor nombre proceso.
	 *
	 * @return el valor de nombre proceso
	 */
	public String getNombreProceso()
	{
		return is_nombreProceso;
	}

	/**
	 * Modifica el valor de NombreRubro.
	 *
	 * @param as_nombreRubro de as nombre rubro
	 */
	public void setNombreRubro(String as_nombreRubro)
	{
		this.is_nombreRubro = as_nombreRubro;
	}

	/**
	 * Retorna Objeto o variable de valor nombre rubro.
	 *
	 * @return el valor de nombre rubro
	 */
	public String getNombreRubro()
	{
		return is_nombreRubro;
	}

	/**
	 * Modifica el valor de NombreSubproceso.
	 *
	 * @param as_nombreSubproceso de as nombre subproceso
	 */
	public void setNombreSubproceso(String as_nombreSubproceso)
	{
		this.is_nombreSubproceso = as_nombreSubproceso;
	}

	/**
	 * Retorna Objeto o variable de valor nombre subproceso.
	 *
	 * @return el valor de nombre subproceso
	 */
	public String getNombreSubproceso()
	{
		return is_nombreSubproceso;
	}

	/**
	 * Modifica el valor de NumeroReferencia.
	 *
	 * @param as_numeroReferencia de as numero referencia
	 */
	public void setNumeroReferencia(String as_numeroReferencia)
	{
		this.is_numeroReferencia = as_numeroReferencia;
	}

	/**
	 * Retorna Objeto o variable de valor numero referencia.
	 *
	 * @return el valor de numero referencia
	 */
	public String getNumeroReferencia()
	{
		return is_numeroReferencia;
	}

	/**
	 * Modifica el valor de ValorConservDocumental.
	 *
	 * @param ad_valorConservDocumental de ad valor conserv documental
	 */
	public void setValorConservDocumental(Double ad_valorConservDocumental)
	{
		this.id_valorConservDocumental = ad_valorConservDocumental;
	}

	/**
	 * Retorna Objeto o variable de valor valor conserv documental.
	 *
	 * @return el valor de valor conserv documental
	 */
	public Double getValorConservDocumental()
	{
		return id_valorConservDocumental;
	}

	/**
	 * Modifica el valor de ValorIngresosSnr.
	 *
	 * @param ad_valorIngresosSnr de ad valor ingresos snr
	 */
	public void setValorIngresosSnr(Double ad_valorIngresosSnr)
	{
		this.id_valorIngresosSnr = ad_valorIngresosSnr;
	}

	/**
	 * Retorna Objeto o variable de valor valor ingresos snr.
	 *
	 * @return el valor de valor ingresos snr
	 */
	public Double getValorIngresosSnr()
	{
		return id_valorIngresosSnr;
	}

	/**
	 * Modifica el valor de ValorOtrasEntidades.
	 *
	 * @param ad_valorOtrasEntidades de ad valor otras entidades
	 */
	public void setValorOtrasEntidades(Double ad_valorOtrasEntidades)
	{
		this.id_valorOtrasEntidades = ad_valorOtrasEntidades;
	}

	/**
	 * Retorna Objeto o variable de valor valor otras entidades.
	 *
	 * @return el valor de valor otras entidades
	 */
	public Double getValorOtrasEntidades()
	{
		return id_valorOtrasEntidades;
	}

	/**
	 * Modifica el valor de ValorPorRubro.
	 *
	 * @param ad_valorPorRubro de ad valor por rubro
	 */
	public void setValorPorRubro(Double ad_valorPorRubro)
	{
		this.id_valorPorRubro = ad_valorPorRubro;
	}

	/**
	 * Retorna Objeto o variable de valor valor por rubro.
	 *
	 * @return el valor de valor por rubro
	 */
	public Double getValorPorRubro()
	{
		return id_valorPorRubro;
	}

	/**
	 * Modifica el valor de ValorTotal.
	 *
	 * @param ad_valorTotal de ad valor total
	 */
	public void setValorTotal(Double ad_valorTotal)
	{
		this.id_valorTotal = ad_valorTotal;
	}

	/**
	 * Retorna Objeto o variable de valor valor total.
	 *
	 * @return el valor de valor total
	 */
	public Double getValorTotal()
	{
		return id_valorTotal;
	}
}
