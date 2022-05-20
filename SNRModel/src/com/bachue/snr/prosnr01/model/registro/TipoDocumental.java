package com.bachue.snr.prosnr01.model.registro;

import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase que contiene todos las propiedades SDB_PGN_TIPO_DOCUMENTAL_ACTO.
 *
 * @author Julian Vaca
 */
public class TipoDocumental extends AccCompletitudDocumental implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6678999585103554356L;

	/** Propiedad id fecha pago impuesto. */
	private Date id_fechaPagoImpuesto;

	/** Propiedad id fecha pago impuesto ext. */
	private Date id_fechaPagoImpuestoExtemporaneo;

	/** Propiedad il version. */
	private Long il_version;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is boleta fiscal. */
	private String is_boletaFiscal;

	/** Propiedad is numero boleta fiscal. */
	private String is_numeroBoletaFiscal;

	/** Propiedad is numero boleta fiscal ext. */
	private String is_numeroBoletaFiscalExt;

	/** Propiedad is secuence. */
	private String is_secuence;

	/** Propiedad is tipo documental. */
	private String is_tipoDocumental;

	/** Propiedad is tipologías Bachue. */
	private String is_tipologiasBachue;

	/** Propiedad is url capture. */
	private String is_urlCapture;

	/** Propiedad ls obligatorio C. */
	private String ls_obligatorioC;

	/** Propiedad ib active tipos doc. */
	private boolean ib_activeTiposDoc;

	/** Propiedad ib agregado pantalla. */
	private boolean ib_agregadoPantalla;

	/** Propiedad ib campo rojo. */
	private boolean ib_campoRojo;

	/** Propiedad ib editar. */
	private boolean ib_editar;

	/** Propiedad ib boleta fiscal. */
	private boolean ib_esBoletaFiscal;

	/** Propiedad ib mostrar boleta fiscal. */
	private boolean ib_mostrarBoletaFiscal;

	/** Propiedad ib recepcion valida. */
	private boolean ib_recepcionValida;

	/** Propiedad is active obv. */
	private boolean is_activeObv;

	/** Propiedad is no es Boleta Fiscal. */
	private boolean is_noEsBoletaFiscal;

	/**
	 * Instancia un nuevo objeto tipo documental.
	 */
	public TipoDocumental()
	{
		setRecepcionValida(true);
	}

	/**
	 * Modifica el valor de active obs.
	 *
	 * @param ab_b asigna el valor a la propiedad active obs
	 */
	public void setActiveObs(boolean ab_b)
	{
		is_activeObv = ab_b;
	}

	/**
	 * Checks if is active obs.
	 *
	 * @return true, if is active obs
	 */
	public boolean isActiveObs()
	{
		return is_activeObv;
	}

	/**
	 * Modifica el valor de active tipos doc.
	 *
	 * @param ab_b asigna el valor a la propiedad active tipos doc
	 */
	public void setActiveTiposDoc(boolean ab_b)
	{
		ib_activeTiposDoc = ab_b;
	}

	/**
	 * Valida la propiedad active tipos doc.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en active tipos doc
	 */
	public boolean isActiveTiposDoc()
	{
		return ib_activeTiposDoc;
	}

	/**
	 * Modifica el valor de activo.
	 *
	 * @param as_activo asigna el valor a la propiedad activo
	 */
	public void setActivo(String as_activo)
	{
		is_activo = as_activo;
	}

	/**
	 * Retorna el valor activo.
	 *
	 * @return el valor de activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de agregado pantalla.
	 *
	 * @param ab_b asigna el valor a la propiedad boleta fiscal
	 */
	public void setAgregadoPantalla(boolean ab_b)
	{
		ib_agregadoPantalla = ab_b;
	}

	/**
	 * Retorna el valor de agregado pantalla.
	 *
	 * @return el valor de agregado pantalla
	 */
	public boolean isAgregadoPantalla()
	{
		return ib_agregadoPantalla;
	}

	/**
	 * Modifica el valor de boleta fiscal.
	 *
	 * @param as_s asigna el valor a la propiedad boleta fiscal
	 */
	public void setBoletaFiscal(String as_s)
	{
		is_boletaFiscal = as_s;
	}

	/**
	 * Retorna el valor de boleta fiscal.
	 *
	 * @return el valor de boleta fiscal
	 */
	public String getBoletaFiscal()
	{
		return is_boletaFiscal;
	}

	/**
	 * Modifica el valor de campo rojo.
	 *
	 * @param ab_b asigna el valor a la propiedad campo rojo
	 */
	public void setCampoRojo(boolean ab_b)
	{
		ib_campoRojo = ab_b;
	}

	/**
	 * Valida la propiedad campo rojo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en campo rojo
	 */
	public boolean isCampoRojo()
	{
		return ib_campoRojo;
	}

	/**
	 * Modifica el valor de editar.
	 *
	 * @param ab_b asigna el valor a la propiedad editar
	 */
	public void setEditar(boolean ab_b)
	{
		ib_editar = ab_b;
	}

	/**
	 * Valida la propiedad editar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en editar
	 */
	public boolean isEditar()
	{
		return ib_editar;
	}

	/**
	 * Modifica el valor de EsBoletaFiscal.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsBoletaFiscal(boolean ab_b)
	{
		ib_esBoletaFiscal = ab_b;
	}

	/**
	 * Valida la propiedad es boleta fiscal.
	 *
	 * @return Retorna el valor de la propiedad esBoletaFiscal
	 */
	public boolean isEsBoletaFiscal()
	{
		return ib_esBoletaFiscal;
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
	 * Modifica el valor de mostrar boleta fiscal.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar boleta fiscal
	 */
	public void setMostrarBoletaFiscal(boolean ab_b)
	{
		ib_mostrarBoletaFiscal = ab_b;
	}

	/**
	 * Checks if is mostrar boleta fiscal.
	 *
	 * @return true, if is mostrar boleta fiscal
	 */
	public boolean isMostrarBoletaFiscal()
	{
		return ib_mostrarBoletaFiscal;
	}

	/**
	 * Modifica el valor de NoEsBoletaFiscal.
	 *
	 * @param ab_b de ab b
	 */
	public void setNoEsBoletaFiscal(boolean ab_b)
	{
		this.is_noEsBoletaFiscal = ab_b;
	}

	/**
	 * Valida la propiedad no es boleta fiscal.
	 *
	 * @return Retorna el valor de la propiedad noEsBoletaFiscal
	 */
	public boolean isNoEsBoletaFiscal()
	{
		return is_noEsBoletaFiscal;
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
	 * Modifica el valor de obligatorio C.
	 *
	 * @param as_s asigna el valor a la propiedad obligatorio C
	 */
	public void setObligatorioC(String as_s)
	{
		ls_obligatorioC = as_s;
	}

	/**
	 * Retorna el valor de obligatorio C.
	 *
	 * @return el valor de obligatorio C
	 */
	public String getObligatorioC()
	{
		return ls_obligatorioC;
	}

	/**
	 * Modifica el valor de recepcion valida.
	 *
	 * @param ab_b asigna el valor a la propiedad recepcion valida
	 */
	public void setRecepcionValida(boolean ab_b)
	{
		ib_recepcionValida = ab_b;
	}

	/**
	 * Retorna el valor de recepcion valida.
	 *
	 * @return el valor de recepcion valida
	 */
	public boolean isRecepcionValida()
	{
		return ib_recepcionValida;
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
	 * Modifica el valor de tipo documental.
	 *
	 * @param as_s asigna el valor a la propiedad tipo documental
	 */
	public void setTipoDocumental(String as_s)
	{
		is_tipoDocumental = as_s;
	}

	/**
	 * Retorna el valor de tipo documental.
	 *
	 * @return el valor de tipo documental
	 */
	public String getTipoDocumental()
	{
		return is_tipoDocumental;
	}

	/**
	 * Modifica el valor de tipologías Bachue.
	 *
	 * @param as_tipologiasBachue de as tipologias bachue
	 */
	public void setTipologiasBachue(String as_tipologiasBachue)
	{
		is_tipologiasBachue = as_tipologiasBachue;
	}

	/**
	 * Retorna el valor de tipologías Bachue.
	 *
	 * @return el valor de tipologías Bachue.
	 */
	public String getTipologiasBachue()
	{
		return is_tipologiasBachue;
	}

	/**
	 * Modifica el valor de tipos doc.
	 *
	 * @param as_s asigna el valor a la propiedad tipos doc
	 */
	public void setUrlCapture(String as_s)
	{
		is_urlCapture = as_s;
	}

	/**
	 * Retorna el valor de url capture.
	 *
	 * @return el valor de url capture
	 */
	public String getUrlCapture()
	{
		return is_urlCapture;
	}

	/**
	 * Modifica el valor de version.
	 *
	 * @param al_l asigna el valor a la propiedad version
	 */
	public void setVersion(Long al_l)
	{
		il_version = al_l;
	}

	/**
	 * Retorna el valor de version.
	 *
	 * @return el valor de version
	 */
	public Long getVersion()
	{
		return il_version;
	}
}
