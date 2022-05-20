package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_LIQUIDACION_ITEM_CERTIFICADO.
 *
 * @author ccalderon
 */
public class LiquidacionItemCertificado extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5755078826236684332L;

	/** Propiedad is certificado obligatorio. */
	private String is_certificadoObligatorio;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad id condicion tarifa. */
	private String is_idCondicionTarifa;

	/** Propiedad is id datos ant sistema. */
	private String is_idDatosAntSistema;

	/** Propiedad is id liquidacion. */
	private String is_idLiquidacion;

	/** Propiedad is id matricula segregacion. */
	private String is_idMatriculaSegregacion;

	/** Propiedad id tarifa fija. */
	private String is_idTarifaFija;

	/** Propiedad id tipo tarifa registral. */
	private String is_idTipoTarifaRegistral;

	/** Propiedad is id turno certificado. */
	private String is_idTurnoCertificado;

	/** Propiedad il cantidad. */
	private long il_cantidad;

	/** Propiedad il consecutivo. */
	private long il_consecutivo;

	/** Propiedad il id certificado. */
	private long il_idCertificado;

	/** Propiedad il id item. */
	private long il_idItem;

	/** Propiedad il id matricula. */
	private long il_idMatricula;

	/** Propiedad il valor. */
	private long il_valor;

	/** Propiedad valor final. */
	private long il_valorFinal;

	/** Propiedad version tarifa fija. */
	private long il_versionTarifaFija;

	/** Propiedad version tarifa registral. */
	private long il_versionTarifaRegistral;

	/**
	 * Instancia un nuevo objeto liquidacion item certificado.
	 *
	 * @param al_liquidacion de al liquidacion
	 */
	public LiquidacionItemCertificado(Liquidacion al_liquidacion)
	{
		if(al_liquidacion != null)
		{
			is_idLiquidacion          = al_liquidacion.getIdLiquidacion();
			il_consecutivo            = al_liquidacion.getConsecutivo();
			is_idCirculo              = al_liquidacion.getIdCirculo();
			is_idTurnoCertificado     = al_liquidacion.getIdTurno();
		}
	}

	/**
	 * Instancia un nuevo objeto liquidacion item certificado.
	 */
	public LiquidacionItemCertificado()
	{
	}

	/**
	 * Modifica el valor de Cantidad.
	 *
	 * @param al_l de al l
	 */
	public void setCantidad(long al_l)
	{
		il_cantidad = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad.
	 *
	 * @return Retorna el valor de la propiedad cantidad
	 */
	public long getCantidad()
	{
		return il_cantidad;
	}

	/**
	 * Modifica el valor de CertificadoObligatorio.
	 *
	 * @param as_s de as s
	 */
	public void setCertificadoObligatorio(String as_s)
	{
		is_certificadoObligatorio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor certificado obligatorio.
	 *
	 * @return Retorna el valor de la propiedad certificadoObligatorio
	 */
	public String getCertificadoObligatorio()
	{
		return is_certificadoObligatorio;
	}

	/**
	 * Modifica el valor de Consecutivo.
	 *
	 * @param al_l de al l
	 */
	public void setConsecutivo(long al_l)
	{
		il_consecutivo = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo.
	 *
	 * @return Retorna el valor de la propiedad consecutivo
	 */
	public long getConsecutivo()
	{
		return il_consecutivo;
	}

	/**
	 * Modifica el valor de IdCertificado.
	 *
	 * @param al_l de al l
	 */
	public void setIdCertificado(long al_l)
	{
		il_idCertificado = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id certificado.
	 *
	 * @return Retorna el valor de la propiedad idCertificado
	 */
	public long getIdCertificado()
	{
		return il_idCertificado;
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
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdCondicionTarifa.
	 *
	 * @param as_s de id condicion tarifa
	 */
	public void setIdCondicionTarifa(String as_s)
	{
		is_idCondicionTarifa = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id condicion tarifa.
	 *
	 * @return Retorna el valor de la propiedad idCondicionTarifa
	 */
	public String getIdCondicionTarifa()
	{
		return is_idCondicionTarifa;
	}

	/**
	 * Modifica el valor de IdDatosAntSistema.
	 *
	 * @param as_s de as s
	 */
	public void setIdDatosAntSistema(String as_s)
	{
		is_idDatosAntSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id datos ant sistema.
	 *
	 * @return Retorna el valor de la propiedad idDatosAntSistema
	 */
	public String getIdDatosAntSistema()
	{
		return is_idDatosAntSistema;
	}

	/**
	 * Modifica el valor de IdItem.
	 *
	 * @param al_l de al l
	 */
	public void setIdItem(long al_l)
	{
		il_idItem = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id item.
	 *
	 * @return Retorna el valor de la propiedad idItem
	 */
	public long getIdItem()
	{
		return il_idItem;
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
	 * @return Retorna el valor de la propiedad idLiquidacion
	 */
	public String getIdLiquidacion()
	{
		return is_idLiquidacion;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l de al l
	 */
	public void setIdMatricula(long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return Retorna el valor de la propiedad idMatricula
	 */
	public long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdMatriculaSegregacion.
	 *
	 * @param as_s de as s
	 */
	public void setIdMatriculaSegregacion(String as_s)
	{
		is_idMatriculaSegregacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula segregacion.
	 *
	 * @return Retorna el valor de la propiedad idMatriculaSegrefacion
	 */
	public String getIdMatriculaSegregacion()
	{
		return is_idMatriculaSegregacion;
	}

	/**
	 * Modifica el valor de IdTarifaFija.
	 *
	 * @param as_s de id tarifa fija
	 */
	public void setIdTarifaFija(String as_s)
	{
		is_idTarifaFija = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tarifa fija.
	 *
	 * @return Retorna el valor de la propiedad idTarifaFija
	 */
	public String getIdTarifaFija()
	{
		return is_idTarifaFija;
	}

	/**
	 * Modifica el valor de IdTipoTarifaRegistral.
	 *
	 * @param as_s de id tipo tarifa registral
	 */
	public void setIdTipoTarifaRegistral(String as_s)
	{
		is_idTipoTarifaRegistral = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo tarifa registral.
	 *
	 * @return Retorna el valor de la propiedad idTipoTarifaRegistral
	 */
	public String getIdTipoTarifaRegistral()
	{
		return is_idTipoTarifaRegistral;
	}

	/**
	 * Modifica el valor de IdTurnoCertificado.
	 *
	 * @param as_s de as s
	 */
	public void setIdTurnoCertificado(String as_s)
	{
		is_idTurnoCertificado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno certificado.
	 *
	 * @return Retorna el valor de la propiedad idTurnoCertificado
	 */
	public String getIdTurnoCertificado()
	{
		return is_idTurnoCertificado;
	}

	/**
	 * Modifica el valor de Valor.
	 *
	 * @param al_l de al l
	 */
	public void setValor(long al_l)
	{
		il_valor = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor valor.
	 *
	 * @return Retorna el valor de la propiedad valor
	 */
	public long getValor()
	{
		return il_valor;
	}

	/**
	 * Modifica el valor de ValorFinal.
	 *
	 * @param al_l de valor final
	 */
	public void setValorFinal(long al_l)
	{
		il_valorFinal = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor valor final.
	 *
	 * @return Retorna el valor de la propiedad valorFinal
	 */
	public long getValorFinal()
	{
		return il_valorFinal;
	}

	/**
	 * Modifica el valor de VersionTarifaFija.
	 *
	 * @param al_l de version tarifa fija
	 */
	public void setVersionTarifaFija(long al_l)
	{
		il_versionTarifaFija = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version tarifa fija.
	 *
	 * @return Retorna el valor de la propiedad versionTarifaFija
	 */
	public long getVersionTarifaFija()
	{
		return il_versionTarifaFija;
	}

	/**
	 * Modifica el valor de VersionTarifaRegistral.
	 *
	 * @param al_l de version tarifa registral
	 */
	public void setVersionTarifaRegistral(long al_l)
	{
		il_versionTarifaRegistral = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version tarifa registral.
	 *
	 * @return Retorna el valor de la propiedad versionTarifaRegistral
	 */
	public long getVersionTarifaRegistral()
	{
		return il_versionTarifaRegistral;
	}
}
