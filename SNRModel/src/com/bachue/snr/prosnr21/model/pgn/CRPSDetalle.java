package com.bachue.snr.prosnr21.model.pgn;

import com.b2bsg.common.util.StringUtils;


/**
 * Clase que representa un registro en la vista SDB_VW_CRPS_DET_CONCILIAR del módulo de conciliaciones.
 *
 * @author Edgar Prieto
 */
public class CRPSDetalle extends com.bachue.snr.prosnr01.model.auditoria.Auditoria implements java.io.Serializable
{
	/** Propiedad  serialVersionUID. */
	private static final long serialVersionUID = 6181720514045627273L;

	/**  Valor por conservación documental. */
	private Double id_valorConservacionDocumental;

	/**  Valor por impuestos. */
	private Double id_valorImpuestoLiquidacion;

	/**  Valor del servicio prestado. */
	private Double id_valorLiquidacion;

	/**  Valor total de la liquidación. */
	private Double id_valorTotalLiquidacion;

	/**  Cantidad solicitada en el turno. */
	private Integer ii_cantidad;

	/**  Consecutivo de la liquidación. */
	private Integer ii_consecutivoLiquidacion;

	/**  Identificador del item de detalle. */
	private Integer ii_idItem;

	/**  Version. */
	private Integer ii_version;

	/**  Identificador del acto. */
	private String is_idActo;

	/** The is id archivo. */
	private String is_idArchivo;

	/** The is id archivo cabecera. */
	private String is_idArchivoCabecera;

	/**  Identificador del circulo registral. */
	private String is_idCirculo;

	/**  Identificador del circulo turno. */
	private String is_idCirculoTurno;

	/**  Identificador de la liquidación. */
	private String is_idLiquidacion;

	/**  Identificador de la matricula de certificacion. */
	private String is_idMatriculaCertificado;

	/**  Identificador de la matricula de segregación. */
	private String is_idMatriculaSegregacion;

	/**  Identificador del proceso al que pertenece la solicitud. */
	private String is_idProceso;

	/**  Identificador del registro de pago. */
	private String is_idRegistroPago;

	/** The is id rubro. */
	private String is_idRubro;

	/** The is id rubro conservacion documental. */
	private String is_idRubroConservacionDocumental;

	/**  Identificador del subproceso al que pertenece la solicitud. */
	private String is_idSubproceso;

	/**  Identificador del tipo de acto. */
	private String is_idTipoActo;

	/**  Identificador del turno. */
	private String is_idTurno;

	/**  Nombre del proceso al que pertenece la solicitud. */
	private String is_nombreProceso;

	/**  Nombre del subproceso al que pertenece la solicitud. */
	private String is_nombreSubproceso;

	/** The ii registro. */
	private int ii_registro;

	/** The ii registro cabecera. */
	private int ii_registroCabecera;

	/**
	 * Asigna la cantidad solicitada en el turno.
	 *
	 * @param ai_i Valor a asignar
	 */
	public void setCantidad(Integer ai_i)
	{
		ii_cantidad = ai_i;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return Obtiene la cantidad solicitada en el turno
	 */
	public Integer getCantidad()
	{
		return ii_cantidad;
	}

	/**
	 * Asigna el consecutivo de la liquidación.
	 *
	 * @param ai_i Valor a asignar
	 */
	public void setConsecutivoLiquidacion(Integer ai_i)
	{
		ii_consecutivoLiquidacion = ai_i;
	}

	/**
	 * Gets the consecutivo liquidacion.
	 *
	 * @return Obtiene el consecutivo de la liquidación
	 */
	public Integer getConsecutivoLiquidacion()
	{
		return ii_consecutivoLiquidacion;
	}

	/**
	 * Asigna el identificador del acto.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdActo(String as_s)
	{
		is_idActo = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id acto.
	 *
	 * @return Obtiene el identificador del acto
	 */
	public String getIdActo()
	{
		return is_idActo;
	}

	/**
	 * Sets the id archivo.
	 *
	 * @param as_idArchivo Modifica el valor de la propiedad idArchivo
	 */
	public void setIdArchivo(String as_idArchivo)
	{
		is_idArchivo = as_idArchivo;
	}

	/**
	 * Gets the id archivo.
	 *
	 * @return Retorna el valor de la propiedad idArchivo
	 */
	public String getIdArchivo()
	{
		return is_idArchivo;
	}

	/**
	 * Sets the id archivo cabecera.
	 *
	 * @param as_idArchivoCabecera Modifica el valor de la propiedad idArchivoCabecera
	 */
	public void setIdArchivoCabecera(String as_idArchivoCabecera)
	{
		is_idArchivoCabecera = as_idArchivoCabecera;
	}

	/**
	 * Gets the id archivo cabecera.
	 *
	 * @return Retorna el valor de la propiedad idArchivoCabecera
	 */
	public String getIdArchivoCabecera()
	{
		return is_idArchivoCabecera;
	}

	/**
	 * Asigna el identificador del circulo registral.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id circulo.
	 *
	 * @return Obtiene el identificador del circulo registral
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Asigna el identificador del circulo turno.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdCirculoTurno(String as_s)
	{
		is_idCirculoTurno = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id circulo turno.
	 *
	 * @return Obtiene el identificador del circulo turno
	 */
	public String getIdCirculoTurno()
	{
		return is_idCirculoTurno;
	}

	/**
	 * Asigna el identificador del item de detalle.
	 *
	 * @param ai_i Valor a asignar
	 */
	public void setIdItem(Integer ai_i)
	{
		ii_idItem = ai_i;
	}

	/**
	 * Gets the id item.
	 *
	 * @return Obtiene el identificador del item de detalle
	 */
	public Integer getIdItem()
	{
		return ii_idItem;
	}

	/**
	 * Asigna el identificador de la liquidación.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdLiquidacion(String as_s)
	{
		is_idLiquidacion = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id liquidacion.
	 *
	 * @return Obtiene el identificador de la liquidación
	 */
	public String getIdLiquidacion()
	{
		return is_idLiquidacion;
	}

	/**
	 * Asigna el identificador de la matricula de certificacion.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdMatriculaCertificado(String as_s)
	{
		is_idMatriculaCertificado = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id matricula certificado.
	 *
	 * @return Obtiene el identificador de la matricula de certificacion
	 */
	public String getIdMatriculaCertificado()
	{
		return is_idMatriculaCertificado;
	}

	/**
	 * Asigna el identificador de la matricula de segregación.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdMatriculaSegregacion(String as_s)
	{
		is_idMatriculaSegregacion = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id matricula segregacion.
	 *
	 * @return Obtiene el identificador de la matricula de segregación
	 */
	public String getIdMatriculaSegregacion()
	{
		return is_idMatriculaSegregacion;
	}

	/**
	 * Asigna el identificador del proceso al que pertenece la solicitud.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id proceso.
	 *
	 * @return Obtiene el identificador del proceso al que pertenece la solicitud
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Asigna el identificador del registro de pago.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdRegistroPago(String as_s)
	{
		is_idRegistroPago = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id registro pago.
	 *
	 * @return Obtiene el identificador del registro de pago
	 */
	public String getIdRegistroPago()
	{
		return is_idRegistroPago;
	}

	/**
	 * Sets the id rubro.
	 *
	 * @param as_idRubro Modifica el valor de la propiedad idRubro
	 */
	public void setIdRubro(String as_idRubro)
	{
		is_idRubro = as_idRubro;
	}

	/**
	 * Gets the id rubro.
	 *
	 * @return Retorna el valor de la propiedad idRubro
	 */
	public String getIdRubro()
	{
		return is_idRubro;
	}

	/**
	 * Sets the id rubro conservacion documental.
	 *
	 * @param as_idRubroConservacionDocumental Modifica el valor de la propiedad idRubroConservacionDocumental
	 */
	public void setIdRubroConservacionDocumental(String as_idRubroConservacionDocumental)
	{
		is_idRubroConservacionDocumental = as_idRubroConservacionDocumental;
	}

	/**
	 * Gets the id rubro conservacion documental.
	 *
	 * @return Retorna el valor de la propiedad idRubroConservacionDocumental
	 */
	public String getIdRubroConservacionDocumental()
	{
		return is_idRubroConservacionDocumental;
	}

	/**
	 * Asigna el identificador del subproceso al que pertenece la solicitud.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdSubproceso(String as_s)
	{
		is_idSubproceso = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id subproceso.
	 *
	 * @return Obtiene el identificador del subproceso al que pertenece la solicitud
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * Asigna el identificador del tipo de acto.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdTipoActo(String as_s)
	{
		is_idTipoActo = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id tipo acto.
	 *
	 * @return Obtiene el identificador del tipo de acto
	 */
	public String getIdTipoActo()
	{
		return is_idTipoActo;
	}

	/**
	 * Asigna el identificador del turno.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id turno.
	 *
	 * @return Obtiene el identificador del turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Asigna el nombre del proceso al que pertenece la solicitud.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNombreProceso(String as_s)
	{
		is_nombreProceso = StringUtils.getString(as_s);
	}

	/**
	 * Gets the nombre proceso.
	 *
	 * @return Obtiene el nombre del proceso al que pertenece la solicitud
	 */
	public String getNombreProceso()
	{
		return is_nombreProceso;
	}

	/**
	 * Asigna el nombre del subproceso al que pertenece la solicitud.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNombreSubproceso(String as_s)
	{
		is_nombreSubproceso = StringUtils.getString(as_s);
	}

	/**
	 * Gets the nombre subproceso.
	 *
	 * @return Obtiene el nombre del subproceso al que pertenece la solicitud
	 */
	public String getNombreSubproceso()
	{
		return is_nombreSubproceso;
	}

	/**
	 * Sets the registro.
	 *
	 * @param ai_registro Modifica el valor de la propiedad Registro
	 */
	public void setRegistro(int ai_registro)
	{
		ii_registro = ai_registro;
	}

	/**
	 * Gets the registro.
	 *
	 * @return Retorna el valor de la propiedad Registro
	 */
	public int getRegistro()
	{
		return ii_registro;
	}

	/**
	 * @param ai_registroCabecera Modifica el valor de la propiedad registroCabecera
	 */
	public void setRegistroCabecera(int ai_registroCabecera)
	{
		ii_registroCabecera = ai_registroCabecera;
	}

	/**
	 * @return Retorna el valor de la propiedad registroCabecera
	 */
	public int getRegistroCabecera()
	{
		return ii_registroCabecera;
	}

	/**
	 * Asigna el valor por conservación documental.
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setValorConservacionDocumental(Double ad_d)
	{
		id_valorConservacionDocumental = ad_d;
	}

	/**
	 * Gets the valor conservacion documental.
	 *
	 * @return Obtiene el valor por conservación documental
	 */
	public Double getValorConservacionDocumental()
	{
		return id_valorConservacionDocumental;
	}

	/**
	 * Asigna el valor por impuestos.
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setValorImpuestoLiquidacion(Double ad_d)
	{
		id_valorImpuestoLiquidacion = ad_d;
	}

	/**
	 * Gets the valor impuesto liquidacion.
	 *
	 * @return Obtiene el valor por impuestos
	 */
	public Double getValorImpuestoLiquidacion()
	{
		return id_valorImpuestoLiquidacion;
	}

	/**
	 * Asigna el valor del servicio prestado.
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setValorLiquidacion(Double ad_d)
	{
		id_valorLiquidacion = ad_d;
	}

	/**
	 * Gets the valor liquidacion.
	 *
	 * @return Obtiene el valor del servicio prestado
	 */
	public Double getValorLiquidacion()
	{
		return id_valorLiquidacion;
	}

	/**
	 * Asigna el valor total de la liquidación.
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setValorTotalLiquidacion(Double ad_d)
	{
		id_valorTotalLiquidacion = ad_d;
	}

	/**
	 * Gets the valor total liquidacion.
	 *
	 * @return Obtiene el valor total de la liquidación
	 */
	public Double getValorTotalLiquidacion()
	{
		return id_valorTotalLiquidacion;
	}

	/**
	 * Asigna la version.
	 *
	 * @param ai_i Valor a asignar
	 */
	public void setVersion(Integer ai_i)
	{
		ii_version = ai_i;
	}

	/**
	 * Gets the version.
	 *
	 * @return Obtiene la version
	 */
	public Integer getVersion()
	{
		return ii_version;
	}
}
