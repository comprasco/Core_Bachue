package com.bachue.snr.prosnr04.model.npa;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos usados en los datos de liquidacion.
 * @author jvaca
 *
 */
public class DatosLiquidacion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID       = 2521835219874860156L;
	
	/** Propiedad id fecha anulacion. */
	private Date              id_fechaAnulacion;
	
	/** Propiedad id fecha liquidacion. */
	private Date              id_fechaLiquidacion;
	
	/** Propiedad id fecha recibo caja. */
	private Date              id_fechaReciboCaja;
	
	/** Propiedad id fecha vencimiento. */
	private Date              id_fechaVencimiento;
	
	/** Propiedad il codigo sucursal canal. */
	private Long              il_codigoSucursalCanal;
	
	/** Propiedad is codigo convenio. */
	private String            is_codigoConvenio;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id documento tipo. */
	private String            is_idDocumentoTipo;
	
	/** Propiedad is id numero referencia. */
	private String            is_idNumeroReferencia;
	
	/** Propiedad is id solicitud. */
	private String            is_idSolicitud;
	
	/** Propiedad is id tipo canal origen. */
	private String            is_idTipoCanalOrigen;
	
	/** Propiedad is id tipo persona. */
	private String            is_idTipoPersona;
	
	/** Propiedad is numero documento. */
	private String            is_numeroDocumento;
	
	/** Propiedad is primer apellido. */
	private String            is_primerApellido;
	
	/** Propiedad is primer nombre. */
	private String            is_primerNombre;
	
	/** Propiedad is razon social. */
	private String            is_razonSocial;
	
	/** Propiedad is segundo apellido. */
	private String            is_segundoApellido;
	
	/** Propiedad is segundo nombre. */
	private String            is_segundoNombre;
	
	/** Propiedad id valor total servicio. */
	private double            id_valorTotalServicio;
	
	/** Propiedad il id documento salida. */
	private long              il_idDocumentoSalida;

	/**
	 * Instancia un nuevo objeto datos liquidacion.
	 */
	public DatosLiquidacion()
	{
	}

	/**
	 * Instancia un nuevo objeto datos liquidacion.
	 *
	 * @param as_numeroReferencia de as numero referencia
	 */
	public DatosLiquidacion(String as_numeroReferencia)
	{
		setIdNumeroReferencia(as_numeroReferencia);
	}

	/**
	 * Instancia un nuevo objeto datos liquidacion.
	 *
	 * @param as_numeroReferencia de as numero referencia
	 * @param al_idDocumentoSalida de al id documento salida
	 */
	public DatosLiquidacion(String as_numeroReferencia, long al_idDocumentoSalida)
	{
		setIdNumeroReferencia(as_numeroReferencia);
		setIdDocumentoSalida(al_idDocumentoSalida);
	}

	/**
	 * Modifica el valor de CodigoConvenio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigoConvenio(String as_s)
	{
		this.is_codigoConvenio                       = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo convenio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoConvenio()
	{
		return is_codigoConvenio;
	}

	/**
	 * Modifica el valor de CodigoSucursalCanal.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setCodigoSucursalCanal(Long al_l)
	{
		this.il_codigoSucursalCanal = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor codigo sucursal canal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getCodigoSucursalCanal()
	{
		return il_codigoSucursalCanal;
	}

	/**
	 * Modifica el valor de FechaAnulacion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaAnulacion(Date ad_d)
	{
		this.id_fechaAnulacion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha anulacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaAnulacion()
	{
		return id_fechaAnulacion;
	}

	/**
	 * Modifica el valor de FechaLiquidacion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaLiquidacion(Date ad_d)
	{
		this.id_fechaLiquidacion = ad_d;
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
	 * Modifica el valor de FechaReciboCaja.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaReciboCaja(Date ad_d)
	{
		this.id_fechaReciboCaja = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha recibo caja.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaReciboCaja()
	{
		return id_fechaReciboCaja;
	}

	/**
	 * Modifica el valor de FechaVencimiento.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaVencimiento(Date ad_d)
	{
		this.id_fechaVencimiento = ad_d;
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
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		this.is_idCirculo = as_s;
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
	 * Modifica el valor de IdDocumentoSalida.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdDocumentoSalida(long al_l)
	{
		this.il_idDocumentoSalida = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id documento salida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdDocumentoSalida()
	{
		return il_idDocumentoSalida;
	}

	/**
	 * Modifica el valor de IdDocumentoTipo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDocumentoTipo(String as_s)
	{
		this.is_idDocumentoTipo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id documento tipo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDocumentoTipo()
	{
		return is_idDocumentoTipo;
	}

	/**
	 * Modifica el valor de IdNumeroReferencia.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdNumeroReferencia(String as_s)
	{
		this.is_idNumeroReferencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id numero referencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdNumeroReferencia()
	{
		return is_idNumeroReferencia;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		this.is_idSolicitud = as_s;
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
	 * Modifica el valor de IdTipoCanalOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoCanalOrigen(String as_s)
	{
		this.is_idTipoCanalOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo canal origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoCanalOrigen()
	{
		return is_idTipoCanalOrigen;
	}

	/**
	 * Modifica el valor de IdTipoPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoPersona(String as_s)
	{
		this.is_idTipoPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoPersona()
	{
		return is_idTipoPersona;
	}

	/**
	 * Modifica el valor de NumeroDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroDocumento(String as_s)
	{
		this.is_numeroDocumento = as_s;
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
	 * Modifica el valor de PrimerApellido.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPrimerApellido(String as_s)
	{
		this.is_primerApellido = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor primer apellido.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPrimerApellido()
	{
		return is_primerApellido;
	}

	/**
	 * Modifica el valor de PrimerNombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPrimerNombre(String as_s)
	{
		this.is_primerNombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor primer nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPrimerNombre()
	{
		return is_primerNombre;
	}

	/**
	 * Modifica el valor de RazonSocial.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRazonSocial(String as_s)
	{
		this.is_razonSocial = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor razon social.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRazonSocial()
	{
		return is_razonSocial;
	}

	/**
	 * Modifica el valor de SegundoApellido.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSegundoApellido(String as_s)
	{
		this.is_segundoApellido = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor segundo apellido.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSegundoApellido()
	{
		return is_segundoApellido;
	}

	/**
	 * Modifica el valor de SegundoNombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSegundoNombre(String as_s)
	{
		this.is_segundoNombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor segundo nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSegundoNombre()
	{
		return is_segundoNombre;
	}

	/**
	 * Modifica el valor de ValorTotalServicio.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setValorTotalServicio(double ad_d)
	{
		this.id_valorTotalServicio = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor valor total servicio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public double getValorTotalServicio()
	{
		return id_valorTotalServicio;
	}
}
