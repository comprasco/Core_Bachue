package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.util.Collection;
import java.util.Date;


/**
 * Clase que representa un registro en la vista SDB_CON_PARTIDA_A del módulo de conciliaciones.
 *
 * @author Gabriel Arias
 */
public class ConPartidaA extends Auditoria implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4642740929712218290L;

	/** Propiedad ils lista alertas. */
	private Collection<String> ilcs_listaClasificacionPartidas;

	/** The fecha comprobante siif. */
	private Date id_fechaComprobanteSiif;

	/** The fecha conciliacion analista. */
	private Date id_fechaConciliacionAnalista;

	/** The Fecha envio archivo ND. */
	private Date id_fechaEnvioArchivoND;

	/** The monto. */
	private Double id_monto;

	/** The activo. */
	private String is_activo;

	/** The clasificacion partidas A. */
	private String is_clasificacionPartidasA;

	/** The conciliado analista. */
	private String is_conciliadoAnalista;

	/** The documento soporte. */
	private String is_documentoSoporte;

	/** The envio siif. */
	private String is_envioSiif;

	/** The id archivo. */
	private String is_idArchivo;

	/** The id documento soporte. */
	private String is_idDocumentoSoporte;

	/** The id partida A. */
	private String is_idPartidaA;

	/** The id rubro presupuestal. */
	private String is_idRubroPresupuestal;

	/** The id usuario analista concilio. */
	private String is_idUsuarioAnalistaConcilio;

	/** The numero comprobante siif. */
	private String is_numeroComprobanteSiif;

	/** The observaciones. */
	private String is_observaciones;

	/** The partidas. */
	private String is_partidas;

	/** The referencia. */
	private String is_referencia;

	/** Propiedad is tipo archivo. */
	private String is_tipoArchivo;

	/** Propiedad iba bytes archivo. */
	private byte[] iba_bytesArchivo;

	/** The id registro. */
	private int ii_idRegistro;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_activo Modifica el valor de la propiedad activo
	 */
	public void setActivo(String as_activo)
	{
		is_activo = as_activo;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return Retorna el valor de la propiedad activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de BytesArchivo.
	 *
	 * @param aba_bytesArchivo de aba bytes archivo
	 */
	public void setBytesArchivo(byte[] aba_bytesArchivo)
	{
		iba_bytesArchivo = aba_bytesArchivo;
	}

	/**
	 * Retorna Objeto o variable de valor bytes archivo.
	 *
	 * @return el valor de bytes archivo
	 */
	public byte[] getBytesArchivo()
	{
		return iba_bytesArchivo;
	}

	/**
	 * Modifica el valor de ClasificacionPartidasA.
	 *
	 * @param as_clasificacionPartidasA Modifica el valor de la propiedad clasificacionPartidasA
	 */
	public void setClasificacionPartidasA(String as_clasificacionPartidasA)
	{
		is_clasificacionPartidasA = as_clasificacionPartidasA;
	}

	/**
	 * Retorna Objeto o variable de valor clasificacion partidas A.
	 *
	 * @return Retorna el valor de la propiedad clasificacionPartidasA
	 */
	public String getClasificacionPartidasA()
	{
		return is_clasificacionPartidasA;
	}

	/**
	 * Modifica el valor de ConciliadoAnalista.
	 *
	 * @param as_conciliadoAnalista Modifica el valor de la propiedad conciliadoAnalista
	 */
	public void setConciliadoAnalista(String as_conciliadoAnalista)
	{
		is_conciliadoAnalista = as_conciliadoAnalista;
	}

	/**
	 * Retorna Objeto o variable de valor conciliado analista.
	 *
	 * @return Retorna el valor de la propiedad conciliadoAnalista
	 */
	public String getConciliadoAnalista()
	{
		return is_conciliadoAnalista;
	}

	/**
	 * Modifica el valor de DocumentoSoporte.
	 *
	 * @param as_documentoSoporte Modifica el valor de la propiedad documentoSoporte
	 */
	public void setDocumentoSoporte(String as_documentoSoporte)
	{
		is_documentoSoporte = as_documentoSoporte;
	}

	/**
	 * Retorna Objeto o variable de valor documento soporte.
	 *
	 * @return Retorna el valor de la propiedad documentoSoporte
	 */
	public String getDocumentoSoporte()
	{
		return is_documentoSoporte;
	}

	/**
	 * Modifica el valor de EnvioSiif.
	 *
	 * @param as_envioSiif Modifica el valor de la propiedad envioSiif
	 */
	public void setEnvioSiif(String as_envioSiif)
	{
		is_envioSiif = as_envioSiif;
	}

	/**
	 * Retorna Objeto o variable de valor envio siif.
	 *
	 * @return Retorna el valor de la propiedad envioSiif
	 */
	public String getEnvioSiif()
	{
		return is_envioSiif;
	}

	/**
	 * Modifica el valor de FechaComprobanteSiif.
	 *
	 * @param ad_fechaComprobanteSiif Modifica el valor de la propiedad fechaComprobanteSiif
	 */
	public void setFechaComprobanteSiif(Date ad_fechaComprobanteSiif)
	{
		id_fechaComprobanteSiif = ad_fechaComprobanteSiif;
	}

	/**
	 * Retorna Objeto o variable de valor fecha comprobante siif.
	 *
	 * @return Retorna el valor de la propiedad fechaComprobanteSiif
	 */
	public Date getFechaComprobanteSiif()
	{
		return id_fechaComprobanteSiif;
	}

	/**
	 * Modifica el valor de FechaConciliacionAnalista.
	 *
	 * @param ad_fechaConciliacionAnalista Modifica el valor de la propiedad fechaConciliacionAnalista
	 */
	public void setFechaConciliacionAnalista(Date ad_fechaConciliacionAnalista)
	{
		id_fechaConciliacionAnalista = ad_fechaConciliacionAnalista;
	}

	/**
	 * Retorna Objeto o variable de valor fecha conciliacion analista.
	 *
	 * @return Retorna el valor de la propiedad fechaConciliacionAnalista
	 */
	public Date getFechaConciliacionAnalista()
	{
		return id_fechaConciliacionAnalista;
	}

	/**
	 * Modifica el valor de FechaEnvioArchivoND.
	 *
	 * @param ad_fechaEnvioArchivoND Modifica el valor de la propiedad fechaEnvioArchivoND
	 */
	public void setFechaEnvioArchivoND(Date ad_fechaEnvioArchivoND)
	{
		id_fechaEnvioArchivoND = ad_fechaEnvioArchivoND;
	}

	/**
	 * Retorna Objeto o variable de valor fecha envio archivo ND.
	 *
	 * @return Retorna el valor de la propiedad fechaEnvioArchivoND
	 */
	public Date getFechaEnvioArchivoND()
	{
		return id_fechaEnvioArchivoND;
	}

	/**
	 * Modifica el valor de IdArchivo.
	 *
	 * @param as_idArchivo Modifica el valor de la propiedad idArchivo
	 */
	public void setIdArchivo(String as_idArchivo)
	{
		is_idArchivo = as_idArchivo;
	}

	/**
	 * Retorna Objeto o variable de valor id archivo.
	 *
	 * @return Retorna el valor de la propiedad idArchivo
	 */
	public String getIdArchivo()
	{
		return is_idArchivo;
	}

	/**
	 * Modifica el valor de IdDocumentoSoporte.
	 *
	 * @param as_idDocumentoSoporte Modifica el valor de la propiedad idDocumentoSoporte
	 */
	public void setIdDocumentoSoporte(String as_idDocumentoSoporte)
	{
		is_idDocumentoSoporte = as_idDocumentoSoporte;
	}

	/**
	 * Retorna Objeto o variable de valor id documento soporte.
	 *
	 * @return Retorna el valor de la propiedad idDocumentoSoporte
	 */
	public String getIdDocumentoSoporte()
	{
		return is_idDocumentoSoporte;
	}

	/**
	 * Modifica el valor de IdPartidaA.
	 *
	 * @param as_idPartidaA Modifica el valor de la propiedad idPartidaA
	 */
	public void setIdPartidaA(String as_idPartidaA)
	{
		is_idPartidaA = as_idPartidaA;
	}

	/**
	 * Retorna Objeto o variable de valor id partida A.
	 *
	 * @return Retorna el valor de la propiedad idPartidaA
	 */
	public String getIdPartidaA()
	{
		return is_idPartidaA;
	}

	/**
	 * Modifica el valor de IdRegistro.
	 *
	 * @param ai_idRegistro Modifica el valor de la propiedad idRegistro
	 */
	public void setIdRegistro(int ai_idRegistro)
	{
		ii_idRegistro = ai_idRegistro;
	}

	/**
	 * Retorna Objeto o variable de valor id registro.
	 *
	 * @return Retorna el valor de la propiedad idRegistro
	 */
	public int getIdRegistro()
	{
		return ii_idRegistro;
	}

	/**
	 * Modifica el valor de IdRubroPresupuestal.
	 *
	 * @param as_idRubroPresupuestal Modifica el valor de la propiedad idRubroPresupuestal
	 */
	public void setIdRubroPresupuestal(String as_idRubroPresupuestal)
	{
		is_idRubroPresupuestal = as_idRubroPresupuestal;
	}

	/**
	 * Retorna Objeto o variable de valor id rubro presupuestal.
	 *
	 * @return Retorna el valor de la propiedad idRubroPresupuestal
	 */
	public String getIdRubroPresupuestal()
	{
		return is_idRubroPresupuestal;
	}

	/**
	 * Modifica el valor de IdUsuarioAnalistaConcilio.
	 *
	 * @param as_idUsuarioAnalistaConcilio Modifica el valor de la propiedad idUsuarioAnalistaConcilio
	 */
	public void setIdUsuarioAnalistaConcilio(String as_idUsuarioAnalistaConcilio)
	{
		is_idUsuarioAnalistaConcilio = as_idUsuarioAnalistaConcilio;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario analista concilio.
	 *
	 * @return Retorna el valor de la propiedad idUsuarioAnalistaConcilio
	 */
	public String getIdUsuarioAnalistaConcilio()
	{
		return is_idUsuarioAnalistaConcilio;
	}

	/**
	 * Modifica el valor de ListaClasificacionPartidas.
	 *
	 * @param als_listaClasificacionPartidas de als lista clasificacion partidas
	 */
	public void setListaClasificacionPartidas(Collection<String> als_listaClasificacionPartidas)
	{
		this.ilcs_listaClasificacionPartidas = als_listaClasificacionPartidas;
	}

	/**
	 * Retorna Objeto o variable de valor lista clasificacion partidas.
	 *
	 * @return el valor de lista clasificacion partidas
	 */
	public Collection<String> getListaClasificacionPartidas()
	{
		return ilcs_listaClasificacionPartidas;
	}

	/**
	 * Modifica el valor de Monto.
	 *
	 * @param ad_monto Modifica el valor de la propiedad monto
	 */
	public void setMonto(Double ad_monto)
	{
		id_monto = ad_monto;
	}

	/**
	 * Retorna Objeto o variable de valor monto.
	 *
	 * @return Retorna el valor de la propiedad monto
	 */
	public Double getMonto()
	{
		return id_monto;
	}

	/**
	 * Modifica el valor de NumeroComprobanteSiif.
	 *
	 * @param as_numeroComprobanteSiif Modifica el valor de la propiedad numeroComprobanteSiif
	 */
	public void setNumeroComprobanteSiif(String as_numeroComprobanteSiif)
	{
		is_numeroComprobanteSiif = as_numeroComprobanteSiif;
	}

	/**
	 * Retorna Objeto o variable de valor numero comprobante siif.
	 *
	 * @return Retorna el valor de la propiedad numeroComprobanteSiif
	 */
	public String getNumeroComprobanteSiif()
	{
		return is_numeroComprobanteSiif;
	}

	/**
	 * Modifica el valor de Observaciones.
	 *
	 * @param as_observaciones Modifica el valor de la propiedad observaciones
	 */
	public void setObservaciones(String as_observaciones)
	{
		is_observaciones = as_observaciones;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones.
	 *
	 * @return Retorna el valor de la propiedad observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de Partidas.
	 *
	 * @param as_partidas Modifica el valor de la propiedad partidas
	 */
	public void setPartidas(String as_partidas)
	{
		is_partidas = as_partidas;
	}

	/**
	 * Retorna Objeto o variable de valor partidas.
	 *
	 * @return Retorna el valor de la propiedad partidas
	 */
	public String getPartidas()
	{
		return is_partidas;
	}

	/**
	 * Modifica el valor de Referencia.
	 *
	 * @param as_referencia Modifica el valor de la propiedad referencia
	 */
	public void setReferencia(String as_referencia)
	{
		is_referencia = as_referencia;
	}

	/**
	 * Retorna Objeto o variable de valor referencia.
	 *
	 * @return Retorna el valor de la propiedad referencia
	 */
	public String getReferencia()
	{
		return is_referencia;
	}

	/**
	 * Modifica el valor de TipoArchivo.
	 *
	 * @param as_tipoArchivo de as tipo archivo
	 */
	public void setTipoArchivo(String as_tipoArchivo)
	{
		this.is_tipoArchivo = as_tipoArchivo;
	}

	/**
	 * Retorna Objeto o variable de valor tipo archivo.
	 *
	 * @return el valor de tipo archivo
	 */
	public String getTipoArchivo()
	{
		return is_tipoArchivo;
	}
}
