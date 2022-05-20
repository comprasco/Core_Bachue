package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.sql.Date;


/**
 * Clase que representa un registro en la vista SDB_CON_SIIF_MAESTRO del módulo de conciliaciones.
 *
 * @author Kevin Pulido
 */
public class ConSiifMaestro extends Auditoria implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5263006833140606562L;

	/** Propiedad id fecha registro. */
	private Date id_fechaRegistro;

	/** Propiedad id fecha solicitud. */
	private Date id_fechaSolicitud;

	/** Propiedad il anio obligacion. */
	private Long il_anioObligacion;

	/** Propiedad il codigo tipo doc tercero. */
	private Long il_codigoTipoDocTercero;

	/** Propiedad il consecutivo maestro. */
	private Long il_consecutivoMaestro;

	/** Propiedad il documento recaudo. */
	private Long il_documentoRecaudo;

	/** Propiedad il expedidor. */
	private Long il_expedidor;

	/** Propiedad il numero documento soporte. */
	private Long il_numeroDocumentoSoporte;

	/** Propiedad is cargo funcionario. */
	private String is_cargoFuncionario;

	/** Propiedad is fuente financiacion. */
	private String is_fuenteFinanciacion;

	/** Propiedad is id periodo corte. */
	private String is_idPeriodoCorte;

	/** Propiedad is id siif maestro. */
	private String is_idSiifMaestro;

	/** Propiedad is nombre funcionario. */
	private String is_nombreFuncionario;

	/** Propiedad is numero doc tercero. */
	private String is_numeroDocTercero;

	/** Propiedad is observacion. */
	private String is_observacion;

	/** Propiedad is pci conexion. */
	private String is_pciConexion;

	/** Propiedad is pci origen documento. */
	private String is_pciOrigenDocumento;

	/** Propiedad is relacion moneda. */
	private String is_relacionMoneda;

	/** Propiedad is situacion fondo. */
	private String is_situacionFondo;

	/** Propiedad is tipo documento. */
	private String is_tipoDocumento;

	/** Propiedad is tipo documento soporte. */
	private String is_tipoDocumentoSoporte;

	/** Propiedad is url descripcion documento tercero. */
	private String is_urlDescripcionDocumentoTercero;

	/** Propiedad is url documento soporte. */
	private String is_urlDocumentoSoporte;

	/** Propiedad id valor total moneda extranjera. */
	private double id_valorTotalMonedaExtranjera;

	/** Propiedad id valor total pesos. */
	private double id_valorTotalPesos;

	/**
	 * Modifica el valor de AnioObligacion.
	 *
	 * @param al_anioObligacion de al anio obligacion
	 */
	public void setAnioObligacion(Long al_anioObligacion)
	{
		this.il_anioObligacion = al_anioObligacion;
	}

	/**
	 * Retorna Objeto o variable de valor anio obligacion.
	 *
	 * @return el valor de anio obligacion
	 */
	public Long getAnioObligacion()
	{
		return il_anioObligacion;
	}

	/**
	 * Modifica el valor de CargoFuncionario.
	 *
	 * @param as_cargoFuncionario de as cargo funcionario
	 */
	public void setCargoFuncionario(String as_cargoFuncionario)
	{
		this.is_cargoFuncionario = as_cargoFuncionario;
	}

	/**
	 * Retorna Objeto o variable de valor cargo funcionario.
	 *
	 * @return el valor de cargo funcionario
	 */
	public String getCargoFuncionario()
	{
		return is_cargoFuncionario;
	}

	/**
	 * Modifica el valor de CodigoTipoDocTercero.
	 *
	 * @param al_codigoTipoDocTercero de al codigo tipo doc tercero
	 */
	public void setCodigoTipoDocTercero(Long al_codigoTipoDocTercero)
	{
		this.il_codigoTipoDocTercero = al_codigoTipoDocTercero;
	}

	/**
	 * Retorna Objeto o variable de valor codigo tipo doc tercero.
	 *
	 * @return el valor de codigo tipo doc tercero
	 */
	public Long getCodigoTipoDocTercero()
	{
		return il_codigoTipoDocTercero;
	}

	/**
	 * Modifica el valor de ConsecutivoMaestro.
	 *
	 * @param al_consecutivoMaestro de al consecutivo maestro
	 */
	public void setConsecutivoMaestro(Long al_consecutivoMaestro)
	{
		this.il_consecutivoMaestro = al_consecutivoMaestro;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo maestro.
	 *
	 * @return el valor de consecutivo maestro
	 */
	public Long getConsecutivoMaestro()
	{
		return il_consecutivoMaestro;
	}

	/**
	 * Modifica el valor de DocumentoRecaudo.
	 *
	 * @param al_documentoRecaudo de al documento recaudo
	 */
	public void setDocumentoRecaudo(Long al_documentoRecaudo)
	{
		this.il_documentoRecaudo = al_documentoRecaudo;
	}

	/**
	 * Retorna Objeto o variable de valor documento recaudo.
	 *
	 * @return el valor de documento recaudo
	 */
	public Long getDocumentoRecaudo()
	{
		return il_documentoRecaudo;
	}

	/**
	 * Modifica el valor de Expedidor.
	 *
	 * @param al_expedidor de al expedidor
	 */
	public void setExpedidor(Long al_expedidor)
	{
		this.il_expedidor = al_expedidor;
	}

	/**
	 * Retorna Objeto o variable de valor expedidor.
	 *
	 * @return el valor de expedidor
	 */
	public Long getExpedidor()
	{
		return il_expedidor;
	}

	/**
	 * Modifica el valor de FechaRegistro.
	 *
	 * @param ad_fechaRegistro de ad fecha registro
	 */
	public void setFechaRegistro(Date ad_fechaRegistro)
	{
		this.id_fechaRegistro = ad_fechaRegistro;
	}

	/**
	 * Retorna Objeto o variable de valor fecha registro.
	 *
	 * @return el valor de fecha registro
	 */
	public Date getFechaRegistro()
	{
		return id_fechaRegistro;
	}

	/**
	 * Modifica el valor de FechaSolicitud.
	 *
	 * @param ad_fechaSolicitud de ad fecha solicitud
	 */
	public void setFechaSolicitud(Date ad_fechaSolicitud)
	{
		this.id_fechaSolicitud = ad_fechaSolicitud;
	}

	/**
	 * Retorna Objeto o variable de valor fecha solicitud.
	 *
	 * @return el valor de fecha solicitud
	 */
	public Date getFechaSolicitud()
	{
		return id_fechaSolicitud;
	}

	/**
	 * Modifica el valor de FuenteFinanciacion.
	 *
	 * @param as_fuenteFinanciacion de as fuente financiacion
	 */
	public void setFuenteFinanciacion(String as_fuenteFinanciacion)
	{
		this.is_fuenteFinanciacion = as_fuenteFinanciacion;
	}

	/**
	 * Retorna Objeto o variable de valor fuente financiacion.
	 *
	 * @return el valor de fuente financiacion
	 */
	public String getFuenteFinanciacion()
	{
		return is_fuenteFinanciacion;
	}

	/**
	 * Modifica el valor de IdPeriodoCorte.
	 *
	 * @param as_idPeriodoCorte de as id periodo corte
	 */
	public void setIdPeriodoCorte(String as_idPeriodoCorte)
	{
		this.is_idPeriodoCorte = as_idPeriodoCorte;
	}

	/**
	 * Retorna Objeto o variable de valor id periodo corte.
	 *
	 * @return el valor de id periodo corte
	 */
	public String getIdPeriodoCorte()
	{
		return is_idPeriodoCorte;
	}

	/**
	 * Modifica el valor de IdSiifMaestro.
	 *
	 * @param as_idSiifMaestro de as id siif maestro
	 */
	public void setIdSiifMaestro(String as_idSiifMaestro)
	{
		this.is_idSiifMaestro = as_idSiifMaestro;
	}

	/**
	 * Retorna Objeto o variable de valor id siif maestro.
	 *
	 * @return el valor de id siif maestro
	 */
	public String getIdSiifMaestro()
	{
		return is_idSiifMaestro;
	}

	/**
	 * Modifica el valor de NombreFuncionario.
	 *
	 * @param as_nombreFuncionario de as nombre funcionario
	 */
	public void setNombreFuncionario(String as_nombreFuncionario)
	{
		this.is_nombreFuncionario = as_nombreFuncionario;
	}

	/**
	 * Retorna Objeto o variable de valor nombre funcionario.
	 *
	 * @return el valor de nombre funcionario
	 */
	public String getNombreFuncionario()
	{
		return is_nombreFuncionario;
	}

	/**
	 * Modifica el valor de NumeroDocTercero.
	 *
	 * @param as_numeroDocTercero de as numero doc tercero
	 */
	public void setNumeroDocTercero(String as_numeroDocTercero)
	{
		this.is_numeroDocTercero = as_numeroDocTercero;
	}

	/**
	 * Retorna Objeto o variable de valor numero doc tercero.
	 *
	 * @return el valor de numero doc tercero
	 */
	public String getNumeroDocTercero()
	{
		return is_numeroDocTercero;
	}

	/**
	 * Modifica el valor de NumeroDocumentoSoporte.
	 *
	 * @param al_numeroDocumentoSoporte de al numero documento soporte
	 */
	public void setNumeroDocumentoSoporte(Long al_numeroDocumentoSoporte)
	{
		this.il_numeroDocumentoSoporte = al_numeroDocumentoSoporte;
	}

	/**
	 * Retorna Objeto o variable de valor numero documento soporte.
	 *
	 * @return el valor de numero documento soporte
	 */
	public Long getNumeroDocumentoSoporte()
	{
		return il_numeroDocumentoSoporte;
	}

	/**
	 * Modifica el valor de Observacion.
	 *
	 * @param as_observacion de as observacion
	 */
	public void setObservacion(String as_observacion)
	{
		this.is_observacion = as_observacion;
	}

	/**
	 * Retorna Objeto o variable de valor observacion.
	 *
	 * @return el valor de observacion
	 */
	public String getObservacion()
	{
		return is_observacion;
	}

	/**
	 * Modifica el valor de PciConexion.
	 *
	 * @param as_pciConexion de as pci conexion
	 */
	public void setPciConexion(String as_pciConexion)
	{
		this.is_pciConexion = as_pciConexion;
	}

	/**
	 * Retorna Objeto o variable de valor pci conexion.
	 *
	 * @return el valor de pci conexion
	 */
	public String getPciConexion()
	{
		return is_pciConexion;
	}

	/**
	 * Modifica el valor de PciOrigenDocumento.
	 *
	 * @param as_pciOrigenDocumento de as pci origen documento
	 */
	public void setPciOrigenDocumento(String as_pciOrigenDocumento)
	{
		this.is_pciOrigenDocumento = as_pciOrigenDocumento;
	}

	/**
	 * Retorna Objeto o variable de valor pci origen documento.
	 *
	 * @return el valor de pci origen documento
	 */
	public String getPciOrigenDocumento()
	{
		return is_pciOrigenDocumento;
	}

	/**
	 * Modifica el valor de RelacionMoneda.
	 *
	 * @param as_relacionMoneda de as relacion moneda
	 */
	public void setRelacionMoneda(String as_relacionMoneda)
	{
		this.is_relacionMoneda = as_relacionMoneda;
	}

	/**
	 * Retorna Objeto o variable de valor relacion moneda.
	 *
	 * @return el valor de relacion moneda
	 */
	public String getRelacionMoneda()
	{
		return is_relacionMoneda;
	}

	/**
	 * Modifica el valor de SituacionFondo.
	 *
	 * @param as_situacionFondo de as situacion fondo
	 */
	public void setSituacionFondo(String as_situacionFondo)
	{
		this.is_situacionFondo = as_situacionFondo;
	}

	/**
	 * Retorna Objeto o variable de valor situacion fondo.
	 *
	 * @return el valor de situacion fondo
	 */
	public String getSituacionFondo()
	{
		return is_situacionFondo;
	}

	/**
	 * Modifica el valor de TipoDocumento.
	 *
	 * @param as_tipoDocumento de as tipo documento
	 */
	public void setTipoDocumento(String as_tipoDocumento)
	{
		this.is_tipoDocumento = as_tipoDocumento;
	}

	/**
	 * Retorna Objeto o variable de valor tipo documento.
	 *
	 * @return el valor de tipo documento
	 */
	public String getTipoDocumento()
	{
		return is_tipoDocumento;
	}

	/**
	 * Modifica el valor de TipoDocumentoSoporte.
	 *
	 * @param as_tipoDocumentoSoporte de as tipo documento soporte
	 */
	public void setTipoDocumentoSoporte(String as_tipoDocumentoSoporte)
	{
		this.is_tipoDocumentoSoporte = as_tipoDocumentoSoporte;
	}

	/**
	 * Retorna Objeto o variable de valor tipo documento soporte.
	 *
	 * @return el valor de tipo documento soporte
	 */
	public String getTipoDocumentoSoporte()
	{
		return is_tipoDocumentoSoporte;
	}

	/**
	 * Modifica el valor de UrlDescripcionDocumentoTercero.
	 *
	 * @param as_urlDescripcionDocumentoTercero de as url descripcion documento tercero
	 */
	public void setUrlDescripcionDocumentoTercero(String as_urlDescripcionDocumentoTercero)
	{
		this.is_urlDescripcionDocumentoTercero = as_urlDescripcionDocumentoTercero;
	}

	/**
	 * Retorna Objeto o variable de valor url descripcion documento tercero.
	 *
	 * @return el valor de url descripcion documento tercero
	 */
	public String getUrlDescripcionDocumentoTercero()
	{
		return is_urlDescripcionDocumentoTercero;
	}

	/**
	 * Modifica el valor de UrlDocumentoSoporte.
	 *
	 * @param as_urlDocumentoSoporte de as url documento soporte
	 */
	public void setUrlDocumentoSoporte(String as_urlDocumentoSoporte)
	{
		this.is_urlDocumentoSoporte = as_urlDocumentoSoporte;
	}

	/**
	 * Retorna Objeto o variable de valor url documento soporte.
	 *
	 * @return el valor de url documento soporte
	 */
	public String getUrlDocumentoSoporte()
	{
		return is_urlDocumentoSoporte;
	}

	/**
	 * Modifica el valor de ValorTotalMonedaExtranjera.
	 *
	 * @param ad_valorTotalMonedaExtranjera de ad valor total moneda extranjera
	 */
	public void setValorTotalMonedaExtranjera(double ad_valorTotalMonedaExtranjera)
	{
		this.id_valorTotalMonedaExtranjera = ad_valorTotalMonedaExtranjera;
	}

	/**
	 * Retorna Objeto o variable de valor valor total moneda extranjera.
	 *
	 * @return el valor de valor total moneda extranjera
	 */
	public double getValorTotalMonedaExtranjera()
	{
		return id_valorTotalMonedaExtranjera;
	}

	/**
	 * Modifica el valor de ValorTotalPesos.
	 *
	 * @param ad_valorTotalPesos de ad valor total pesos
	 */
	public void setValorTotalPesos(double ad_valorTotalPesos)
	{
		this.id_valorTotalPesos = ad_valorTotalPesos;
	}

	/**
	 * Retorna Objeto o variable de valor valor total pesos.
	 *
	 * @return el valor de valor total pesos
	 */
	public double getValorTotalPesos()
	{
		return id_valorTotalPesos;
	}
}
