package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.util.Date;


/**
 * Clase que representa un registro en la vista SDB_CON_DOCUMENTO del módulo de conciliaciones.
 *
 * @author Gabriel Arias
 */
public class ConDocumentos extends Auditoria implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8405409316358210745L;

	/** Propiedad id fecha acuse recibo componente. */
	private Date id_fechaAcuseReciboComponente;

	/** Propiedad id fecha consecutivo oficio. */
	private Date id_fechaConsecutivoOficio;

	/** Propiedad id fecha desfijacion aviso web. */
	private Date id_fechaDesfijacionAvisoWeb;

	/** Propiedad id fecha envio. */
	private Date id_fechaEnvio;

	/** Propiedad id fecha envio componente. */
	private Date id_fechaEnvioComponente;

	/** Propiedad id fecha guia. */
	private Date id_fechaGuia;

	/** Propiedad id fecha intento envio. */
	private Date id_fechaIntentoEnvio;

	/** Propiedad id fecha notificacion. */
	private Date id_fechaNotificacion;

	/** Propiedad id fecha publicacion aviso web. */
	private Date id_fechaPublicacionAvisoWeb;

	/** Propiedad id fecha radicacion ecm. */
	private Date id_fechaRadicacionEcm;

	/** Propiedad id fecha recibo aviso web. */
	private Date id_fechaReciboAvisoWeb;

	/** Propiedad id fecha resol asociada. */
	private Date id_fechaResolAsociada;

	/** Propiedad id fecha resolucion. */
	private Date id_fechaResolucion;

	/** Propiedad id fecha respuesta mensaje. */
	private Date id_fechaRespuestaMensaje;

	/** Propiedad il consecutivo resolucion. */
	private Long il_consecutivoResolucion;

	/** Propiedad il id imagen. */
	private Long il_idImagen;

	/** Propiedad il numero resol asociada. */
	private Long il_numeroResolAsociada;

	/** Propiedad is clasificacion. */
	private String is_clasificacion;

	/** Propiedad is consecutivo oficio. */
	private String is_consecutivoOficio;

	/** Propiedad is correo electronico. */
	private String is_correoElectronico;

	/** Propiedad is destinatario. */
	private String is_destinatario;

	/** Propiedad is direccion. */
	private String is_direccion;

	/** Propiedad is documento automatico. */
	private String is_documentoAutomatico;

	/** Propiedad is documento final. */
	private String is_documentoFinal;

	/** Propiedad is eliminar envio owcc. */
	private String is_eliminarEnvioOwcc;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is estado impresion. */
	private String is_estadoImpresion;

	/** Propiedad is generado. */
	private String is_generado;

	/** Propiedad is id archivo. */
	private String is_idArchivo;

	/** Propiedad . */
	private String is_idCuenta;

	/** Propiedad is id departamento. */
	private String is_idDepartamento;

	/** Propiedad is id ecm. */
	private String is_idEcm;

	/** Propiedad is id mensaje. */
	private String is_idMensaje;

	/** Propiedad is id municipio. */
	private String is_idMunicipio;

	/** Propiedad is id nombre documento. */
	private String is_idNombreDocumento;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is id partida A. */
	private String is_idPartidaA;

	/** Propiedad . */
	private String is_idPeriodoCorte;

	/** Propiedad is id persona notificacion. */
	private String is_idPersonaNotificacion;

	/** Propiedad is id tipo documental. */
	private String is_idTipoDocumental;

	/** Propiedad is medio publicacion. */
	private String is_medioPublicacion;

	/** Propiedad . */
	private String is_numeroComprobanteSIIF;

	/** Propiedad . */
	private String is_numeroSIIF;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is referencia salida. */
	private String is_referenciaSalida;

	/** Propiedad is repositorio. */
	private String is_repositorio;

	/** Propiedad is responsable publicacion web. */
	private String is_responsablePublicacionWeb;

	/** Propiedad is salida asociada. */
	private String is_salidaAsociada;

	/** Propiedad is telefono. */
	private String is_telefono;

	/** Propiedad is tipo. */
	private String is_tipo;

	/** Propiedad is tipo archivo. */
	private String is_tipoArchivo;

	/** Propiedad is tipo notificacion. */
	private String is_tipoNotificacion;

	/** Propiedad il id documento salida. */
	private long il_idDocumentoSalida;

	/** Propiedad il intento envio. */
	private long il_intentoEnvio;

	/** Propiedad il numero copias. */
	private long il_numeroCopias;

	/** Propiedad il numero guia. */
	private long il_numeroGuia;

	/** Propiedad il reimpresion. */
	private long il_reimpresion;

	/**
	 * Modifica el valor de Clasificacion.
	 *
	 * @param as_clasificacion de as clasificacion
	 */
	public void setClasificacion(String as_clasificacion)
	{
		this.is_clasificacion = as_clasificacion;
	}

	/**
	 * Retorna Objeto o variable de valor clasificacion.
	 *
	 * @return el valor de clasificacion
	 */
	public String getClasificacion()
	{
		return is_clasificacion;
	}

	/**
	 * Modifica el valor de ConsecutivoOficio.
	 *
	 * @param as_consecutivoOficio de as consecutivo oficio
	 */
	public void setConsecutivoOficio(String as_consecutivoOficio)
	{
		this.is_consecutivoOficio = as_consecutivoOficio;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo oficio.
	 *
	 * @return el valor de consecutivo oficio
	 */
	public String getConsecutivoOficio()
	{
		return is_consecutivoOficio;
	}

	/**
	 * Modifica el valor de ConsecutivoResolucion.
	 *
	 * @param al_consecutivoResolucion de al consecutivo resolucion
	 */
	public void setConsecutivoResolucion(Long al_consecutivoResolucion)
	{
		this.il_consecutivoResolucion = al_consecutivoResolucion;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo resolucion.
	 *
	 * @return el valor de consecutivo resolucion
	 */
	public Long getConsecutivoResolucion()
	{
		return il_consecutivoResolucion;
	}

	/**
	 * Modifica el valor de CorreoElectronico.
	 *
	 * @param as_correoElectronico de as correo electronico
	 */
	public void setCorreoElectronico(String as_correoElectronico)
	{
		this.is_correoElectronico = as_correoElectronico;
	}

	/**
	 * Retorna Objeto o variable de valor correo electronico.
	 *
	 * @return el valor de correo electronico
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Modifica el valor de Destinatario.
	 *
	 * @param as_destinatario de as destinatario
	 */
	public void setDestinatario(String as_destinatario)
	{
		this.is_destinatario = as_destinatario;
	}

	/**
	 * Retorna Objeto o variable de valor destinatario.
	 *
	 * @return el valor de destinatario
	 */
	public String getDestinatario()
	{
		return is_destinatario;
	}

	/**
	 * Modifica el valor de Direccion.
	 *
	 * @param as_direccion de as direccion
	 */
	public void setDireccion(String as_direccion)
	{
		this.is_direccion = as_direccion;
	}

	/**
	 * Retorna Objeto o variable de valor direccion.
	 *
	 * @return el valor de direccion
	 */
	public String getDireccion()
	{
		return is_direccion;
	}

	/**
	 * Modifica el valor de DocumentoAutomatico.
	 *
	 * @param as_documentoAutomatico de as documento automatico
	 */
	public void setDocumentoAutomatico(String as_documentoAutomatico)
	{
		this.is_documentoAutomatico = as_documentoAutomatico;
	}

	/**
	 * Retorna Objeto o variable de valor documento automatico.
	 *
	 * @return el valor de documento automatico
	 */
	public String getDocumentoAutomatico()
	{
		return is_documentoAutomatico;
	}

	/**
	 * Modifica el valor de DocumentoFinal.
	 *
	 * @param as_documentoFinal de as documento final
	 */
	public void setDocumentoFinal(String as_documentoFinal)
	{
		this.is_documentoFinal = as_documentoFinal;
	}

	/**
	 * Retorna Objeto o variable de valor documento final.
	 *
	 * @return el valor de documento final
	 */
	public String getDocumentoFinal()
	{
		return is_documentoFinal;
	}

	/**
	 * Modifica el valor de EliminarEnvioOwcc.
	 *
	 * @param as_eliminarEnvioOwcc de as eliminar envio owcc
	 */
	public void setEliminarEnvioOwcc(String as_eliminarEnvioOwcc)
	{
		this.is_eliminarEnvioOwcc = as_eliminarEnvioOwcc;
	}

	/**
	 * Retorna Objeto o variable de valor eliminar envio owcc.
	 *
	 * @return el valor de eliminar envio owcc
	 */
	public String getEliminarEnvioOwcc()
	{
		return is_eliminarEnvioOwcc;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_estado de as estado
	 */
	public void setEstado(String as_estado)
	{
		this.is_estado = as_estado;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return el valor de estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de EstadoImpresion.
	 *
	 * @param as_estadoImpresion de as estado impresion
	 */
	public void setEstadoImpresion(String as_estadoImpresion)
	{
		this.is_estadoImpresion = as_estadoImpresion;
	}

	/**
	 * Retorna Objeto o variable de valor estado impresion.
	 *
	 * @return el valor de estado impresion
	 */
	public String getEstadoImpresion()
	{
		return is_estadoImpresion;
	}

	/**
	 * Modifica el valor de FechaAcuseReciboComponente.
	 *
	 * @param ad_fechaAcuseReciboComponente de ad fecha acuse recibo componente
	 */
	public void setFechaAcuseReciboComponente(Date ad_fechaAcuseReciboComponente)
	{
		this.id_fechaAcuseReciboComponente = ad_fechaAcuseReciboComponente;
	}

	/**
	 * Retorna Objeto o variable de valor fecha acuse recibo componente.
	 *
	 * @return el valor de fecha acuse recibo componente
	 */
	public Date getFechaAcuseReciboComponente()
	{
		return id_fechaAcuseReciboComponente;
	}

	/**
	 * Modifica el valor de FechaConsecutivoOficio.
	 *
	 * @param ad_fechaConsecutivoOficio de ad fecha consecutivo oficio
	 */
	public void setFechaConsecutivoOficio(Date ad_fechaConsecutivoOficio)
	{
		this.id_fechaConsecutivoOficio = ad_fechaConsecutivoOficio;
	}

	/**
	 * Retorna Objeto o variable de valor fecha consecutivo oficio.
	 *
	 * @return el valor de fecha consecutivo oficio
	 */
	public Date getFechaConsecutivoOficio()
	{
		return id_fechaConsecutivoOficio;
	}

	/**
	 * Modifica el valor de FechaDesfijacionAvisoWeb.
	 *
	 * @param ad_fechaDesfijacionAvisoWeb de ad fecha desfijacion aviso web
	 */
	public void setFechaDesfijacionAvisoWeb(Date ad_fechaDesfijacionAvisoWeb)
	{
		this.id_fechaDesfijacionAvisoWeb = ad_fechaDesfijacionAvisoWeb;
	}

	/**
	 * Retorna Objeto o variable de valor fecha desfijacion aviso web.
	 *
	 * @return el valor de fecha desfijacion aviso web
	 */
	public Date getFechaDesfijacionAvisoWeb()
	{
		return id_fechaDesfijacionAvisoWeb;
	}

	/**
	 * Modifica el valor de FechaEnvio.
	 *
	 * @param ad_fechaEnvio de ad fecha envio
	 */
	public void setFechaEnvio(Date ad_fechaEnvio)
	{
		this.id_fechaEnvio = ad_fechaEnvio;
	}

	/**
	 * Retorna Objeto o variable de valor fecha envio.
	 *
	 * @return el valor de fecha envio
	 */
	public Date getFechaEnvio()
	{
		return id_fechaEnvio;
	}

	/**
	 * Modifica el valor de FechaEnvioComponente.
	 *
	 * @param ad_fechaEnvioComponente de ad fecha envio componente
	 */
	public void setFechaEnvioComponente(Date ad_fechaEnvioComponente)
	{
		this.id_fechaEnvioComponente = ad_fechaEnvioComponente;
	}

	/**
	 * Retorna Objeto o variable de valor fecha envio componente.
	 *
	 * @return el valor de fecha envio componente
	 */
	public Date getFechaEnvioComponente()
	{
		return id_fechaEnvioComponente;
	}

	/**
	 * Modifica el valor de FechaGuia.
	 *
	 * @param ad_fechaGuia de ad fecha guia
	 */
	public void setFechaGuia(Date ad_fechaGuia)
	{
		this.id_fechaGuia = ad_fechaGuia;
	}

	/**
	 * Retorna Objeto o variable de valor fecha guia.
	 *
	 * @return el valor de fecha guia
	 */
	public Date getFechaGuia()
	{
		return id_fechaGuia;
	}

	/**
	 * Modifica el valor de FechaIntentoEnvio.
	 *
	 * @param ad_fechaIntentoEnvio de ad fecha intento envio
	 */
	public void setFechaIntentoEnvio(Date ad_fechaIntentoEnvio)
	{
		this.id_fechaIntentoEnvio = ad_fechaIntentoEnvio;
	}

	/**
	 * Retorna Objeto o variable de valor fecha intento envio.
	 *
	 * @return el valor de fecha intento envio
	 */
	public Date getFechaIntentoEnvio()
	{
		return id_fechaIntentoEnvio;
	}

	/**
	 * Modifica el valor de FechaNotificacion.
	 *
	 * @param ad_fechaNotificacion de ad fecha notificacion
	 */
	public void setFechaNotificacion(Date ad_fechaNotificacion)
	{
		this.id_fechaNotificacion = ad_fechaNotificacion;
	}

	/**
	 * Retorna Objeto o variable de valor fecha notificacion.
	 *
	 * @return el valor de fecha notificacion
	 */
	public Date getFechaNotificacion()
	{
		return id_fechaNotificacion;
	}

	/**
	 * Modifica el valor de FechaPublicacionAvisoWeb.
	 *
	 * @param ad_fechaPublicacionAvisoWeb de ad fecha publicacion aviso web
	 */
	public void setFechaPublicacionAvisoWeb(Date ad_fechaPublicacionAvisoWeb)
	{
		this.id_fechaPublicacionAvisoWeb = ad_fechaPublicacionAvisoWeb;
	}

	/**
	 * Retorna Objeto o variable de valor fecha publicacion aviso web.
	 *
	 * @return el valor de fecha publicacion aviso web
	 */
	public Date getFechaPublicacionAvisoWeb()
	{
		return id_fechaPublicacionAvisoWeb;
	}

	/**
	 * Modifica el valor de FechaRadicacionEcm.
	 *
	 * @param ad_fechaRadicacionEcm de ad fecha radicacion ecm
	 */
	public void setFechaRadicacionEcm(Date ad_fechaRadicacionEcm)
	{
		this.id_fechaRadicacionEcm = ad_fechaRadicacionEcm;
	}

	/**
	 * Retorna Objeto o variable de valor fecha radicacion ecm.
	 *
	 * @return el valor de fecha radicacion ecm
	 */
	public Date getFechaRadicacionEcm()
	{
		return id_fechaRadicacionEcm;
	}

	/**
	 * Modifica el valor de FechaReciboAvisoWeb.
	 *
	 * @param ad_fechaReciboAvisoWeb de ad fecha recibo aviso web
	 */
	public void setFechaReciboAvisoWeb(Date ad_fechaReciboAvisoWeb)
	{
		this.id_fechaReciboAvisoWeb = ad_fechaReciboAvisoWeb;
	}

	/**
	 * Retorna Objeto o variable de valor fecha recibo aviso web.
	 *
	 * @return el valor de fecha recibo aviso web
	 */
	public Date getFechaReciboAvisoWeb()
	{
		return id_fechaReciboAvisoWeb;
	}

	/**
	 * Modifica el valor de FechaResolAsociada.
	 *
	 * @param ad_fechaResolAsociada de ad fecha resol asociada
	 */
	public void setFechaResolAsociada(Date ad_fechaResolAsociada)
	{
		this.id_fechaResolAsociada = ad_fechaResolAsociada;
	}

	/**
	 * Retorna Objeto o variable de valor fecha resol asociada.
	 *
	 * @return el valor de fecha resol asociada
	 */
	public Date getFechaResolAsociada()
	{
		return id_fechaResolAsociada;
	}

	/**
	 * Modifica el valor de FechaResolucion.
	 *
	 * @param ad_fechaResolucion de ad fecha resolucion
	 */
	public void setFechaResolucion(Date ad_fechaResolucion)
	{
		this.id_fechaResolucion = ad_fechaResolucion;
	}

	/**
	 * Retorna Objeto o variable de valor fecha resolucion.
	 *
	 * @return el valor de fecha resolucion
	 */
	public Date getFechaResolucion()
	{
		return id_fechaResolucion;
	}

	/**
	 * Modifica el valor de FechaRespuestaMensaje.
	 *
	 * @param ad_fechaRespuestaMensaje de ad fecha respuesta mensaje
	 */
	public void setFechaRespuestaMensaje(Date ad_fechaRespuestaMensaje)
	{
		this.id_fechaRespuestaMensaje = ad_fechaRespuestaMensaje;
	}

	/**
	 * Retorna Objeto o variable de valor fecha respuesta mensaje.
	 *
	 * @return el valor de fecha respuesta mensaje
	 */
	public Date getFechaRespuestaMensaje()
	{
		return id_fechaRespuestaMensaje;
	}

	/**
	 * Modifica el valor de Generado.
	 *
	 * @param as_generado de as generado
	 */
	public void setGenerado(String as_generado)
	{
		this.is_generado = as_generado;
	}

	/**
	 * Retorna Objeto o variable de valor generado.
	 *
	 * @return el valor de generado
	 */
	public String getGenerado()
	{
		return is_generado;
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
	 * @param as_s Modifica el valor de la propiedad is_idCuenta
	 */
	public void setIdCuenta(String as_s)
	{
		is_idCuenta = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idCuenta
	 */
	public String getIdCuenta()
	{
		return is_idCuenta;
	}

	/**
	 * Modifica el valor de IdDepartamento.
	 *
	 * @param as_idDepartamento de as id departamento
	 */
	public void setIdDepartamento(String as_idDepartamento)
	{
		this.is_idDepartamento = as_idDepartamento;
	}

	/**
	 * Retorna Objeto o variable de valor id departamento.
	 *
	 * @return el valor de id departamento
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de IdDocumentoSalida.
	 *
	 * @param al_idDocumentoSalida de al id documento salida
	 */
	public void setIdDocumentoSalida(long al_idDocumentoSalida)
	{
		this.il_idDocumentoSalida = al_idDocumentoSalida;
	}

	/**
	 * Retorna Objeto o variable de valor id documento salida.
	 *
	 * @return el valor de id documento salida
	 */
	public long getIdDocumentoSalida()
	{
		return il_idDocumentoSalida;
	}

	/**
	 * Modifica el valor de IdEcm.
	 *
	 * @param as_idEcm de as id ecm
	 */
	public void setIdEcm(String as_idEcm)
	{
		this.is_idEcm = as_idEcm;
	}

	/**
	 * Retorna Objeto o variable de valor id ecm.
	 *
	 * @return el valor de id ecm
	 */
	public String getIdEcm()
	{
		return is_idEcm;
	}

	/**
	 * Modifica el valor de IdImagen.
	 *
	 * @param al_idImagen de al id imagen
	 */
	public void setIdImagen(Long al_idImagen)
	{
		this.il_idImagen = al_idImagen;
	}

	/**
	 * Retorna Objeto o variable de valor id imagen.
	 *
	 * @return el valor de id imagen
	 */
	public Long getIdImagen()
	{
		return il_idImagen;
	}

	/**
	 * Modifica el valor de IdMensaje.
	 *
	 * @param as_idMensaje de as id mensaje
	 */
	public void setIdMensaje(String as_idMensaje)
	{
		this.is_idMensaje = as_idMensaje;
	}

	/**
	 * Retorna Objeto o variable de valor id mensaje.
	 *
	 * @return el valor de id mensaje
	 */
	public String getIdMensaje()
	{
		return is_idMensaje;
	}

	/**
	 * Modifica el valor de IdMunicipio.
	 *
	 * @param as_idMunicipio de as id municipio
	 */
	public void setIdMunicipio(String as_idMunicipio)
	{
		this.is_idMunicipio = as_idMunicipio;
	}

	/**
	 * Retorna Objeto o variable de valor id municipio.
	 *
	 * @return el valor de id municipio
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * Modifica el valor de IdNombreDocumento.
	 *
	 * @param as_idNombreDocumento de as id nombre documento
	 */
	public void setIdNombreDocumento(String as_idNombreDocumento)
	{
		this.is_idNombreDocumento = as_idNombreDocumento;
	}

	/**
	 * Retorna Objeto o variable de valor id nombre documento.
	 *
	 * @return el valor de id nombre documento
	 */
	public String getIdNombreDocumento()
	{
		return is_idNombreDocumento;
	}

	/**
	 * Modifica el valor de IdPais.
	 *
	 * @param as_idPais de as id pais
	 */
	public void setIdPais(String as_idPais)
	{
		this.is_idPais = as_idPais;
	}

	/**
	 * Retorna Objeto o variable de valor id pais.
	 *
	 * @return el valor de id pais
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de IdPartidaA.
	 *
	 * @param as_idPartidaA de as id partida A
	 */
	public void setIdPartidaA(String as_idPartidaA)
	{
		this.is_idPartidaA = as_idPartidaA;
	}

	/**
	 * Retorna Objeto o variable de valor id partida A.
	 *
	 * @return el valor de id partida A
	 */
	public String getIdPartidaA()
	{
		return is_idPartidaA;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_idPeriodoCorte
	 */
	public void setIdPeriodoCorte(String as_s)
	{
		is_idPeriodoCorte = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idPeriodoCorte
	 */
	public String getIdPeriodoCorte()
	{
		return is_idPeriodoCorte;
	}

	/**
	 * Modifica el valor de IdPersonaNotificacion.
	 *
	 * @param as_idPersonaNotificacion de as id persona notificacion
	 */
	public void setIdPersonaNotificacion(String as_idPersonaNotificacion)
	{
		this.is_idPersonaNotificacion = as_idPersonaNotificacion;
	}

	/**
	 * Retorna Objeto o variable de valor id persona notificacion.
	 *
	 * @return el valor de id persona notificacion
	 */
	public String getIdPersonaNotificacion()
	{
		return is_idPersonaNotificacion;
	}

	/**
	 * Modifica el valor de IdTipoDocumental.
	 *
	 * @param as_idTipoDocumental de as id tipo documental
	 */
	public void setIdTipoDocumental(String as_idTipoDocumental)
	{
		this.is_idTipoDocumental = as_idTipoDocumental;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo documental.
	 *
	 * @return el valor de id tipo documental
	 */
	public String getIdTipoDocumental()
	{
		return is_idTipoDocumental;
	}

	/**
	 * Modifica el valor de IntentoEnvio.
	 *
	 * @param al_intentoEnvio de al intento envio
	 */
	public void setIntentoEnvio(long al_intentoEnvio)
	{
		this.il_intentoEnvio = al_intentoEnvio;
	}

	/**
	 * Retorna Objeto o variable de valor intento envio.
	 *
	 * @return el valor de intento envio
	 */
	public long getIntentoEnvio()
	{
		return il_intentoEnvio;
	}

	/**
	 * Modifica el valor de MedioPublicacion.
	 *
	 * @param as_medioPublicacion de as medio publicacion
	 */
	public void setMedioPublicacion(String as_medioPublicacion)
	{
		this.is_medioPublicacion = as_medioPublicacion;
	}

	/**
	 * Retorna Objeto o variable de valor medio publicacion.
	 *
	 * @return el valor de medio publicacion
	 */
	public String getMedioPublicacion()
	{
		return is_medioPublicacion;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_numeroComprobanteSIIF
	 */
	public void setNumeroComprobanteSIIF(String as_s)
	{
		is_numeroComprobanteSIIF = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_numeroComprobanteSIIF
	 */
	public String getNumeroComprobanteSIIF()
	{
		return is_numeroComprobanteSIIF;
	}

	/**
	 * Modifica el valor de NumeroCopias.
	 *
	 * @param al_numeroCopias de al numero copias
	 */
	public void setNumeroCopias(long al_numeroCopias)
	{
		this.il_numeroCopias = al_numeroCopias;
	}

	/**
	 * Retorna Objeto o variable de valor numero copias.
	 *
	 * @return el valor de numero copias
	 */
	public long getNumeroCopias()
	{
		return il_numeroCopias;
	}

	/**
	 * Modifica el valor de NumeroGuia.
	 *
	 * @param al_numeroGuia de al numero guia
	 */
	public void setNumeroGuia(long al_numeroGuia)
	{
		this.il_numeroGuia = al_numeroGuia;
	}

	/**
	 * Retorna Objeto o variable de valor numero guia.
	 *
	 * @return el valor de numero guia
	 */
	public long getNumeroGuia()
	{
		return il_numeroGuia;
	}

	/**
	 * Modifica el valor de NumeroResolAsociada.
	 *
	 * @param al_numeroResolAsociada de al numero resol asociada
	 */
	public void setNumeroResolAsociada(Long al_numeroResolAsociada)
	{
		this.il_numeroResolAsociada = al_numeroResolAsociada;
	}

	/**
	 * Retorna Objeto o variable de valor numero resol asociada.
	 *
	 * @return el valor de numero resol asociada
	 */
	public Long getNumeroResolAsociada()
	{
		return il_numeroResolAsociada;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_numeroSIIF
	 */
	public void setNumeroSIIF(String as_s)
	{
		is_numeroSIIF = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_numeroSIIF
	 */
	public String getNumeroSIIF()
	{
		return is_numeroSIIF;
	}

	/**
	 * Modifica el valor de Observaciones.
	 *
	 * @param as_observaciones de as observaciones
	 */
	public void setObservaciones(String as_observaciones)
	{
		this.is_observaciones = as_observaciones;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de ReferenciaSalida.
	 *
	 * @param as_referenciaSalida de as referencia salida
	 */
	public void setReferenciaSalida(String as_referenciaSalida)
	{
		this.is_referenciaSalida = as_referenciaSalida;
	}

	/**
	 * Retorna Objeto o variable de valor referencia salida.
	 *
	 * @return el valor de referencia salida
	 */
	public String getReferenciaSalida()
	{
		return is_referenciaSalida;
	}

	/**
	 * Modifica el valor de Reimpresion.
	 *
	 * @param al_reimpresion de al reimpresion
	 */
	public void setReimpresion(long al_reimpresion)
	{
		this.il_reimpresion = al_reimpresion;
	}

	/**
	 * Retorna Objeto o variable de valor reimpresion.
	 *
	 * @return el valor de reimpresion
	 */
	public long getReimpresion()
	{
		return il_reimpresion;
	}

	/**
	 * Modifica el valor de Repositorio.
	 *
	 * @param as_repositorio de as repositorio
	 */
	public void setRepositorio(String as_repositorio)
	{
		this.is_repositorio = as_repositorio;
	}

	/**
	 * Retorna Objeto o variable de valor repositorio.
	 *
	 * @return el valor de repositorio
	 */
	public String getRepositorio()
	{
		return is_repositorio;
	}

	/**
	 * Modifica el valor de ResponsablePublicacionWeb.
	 *
	 * @param as_responsablePublicacionWeb de as responsable publicacion web
	 */
	public void setResponsablePublicacionWeb(String as_responsablePublicacionWeb)
	{
		this.is_responsablePublicacionWeb = as_responsablePublicacionWeb;
	}

	/**
	 * Retorna Objeto o variable de valor responsable publicacion web.
	 *
	 * @return el valor de responsable publicacion web
	 */
	public String getResponsablePublicacionWeb()
	{
		return is_responsablePublicacionWeb;
	}

	/**
	 * Modifica el valor de SalidaAsociada.
	 *
	 * @param as_salidaAsociada de as salida asociada
	 */
	public void setSalidaAsociada(String as_salidaAsociada)
	{
		this.is_salidaAsociada = as_salidaAsociada;
	}

	/**
	 * Retorna Objeto o variable de valor salida asociada.
	 *
	 * @return el valor de salida asociada
	 */
	public String getSalidaAsociada()
	{
		return is_salidaAsociada;
	}

	/**
	 * Modifica el valor de Telefono.
	 *
	 * @param as_telefono de as telefono
	 */
	public void setTelefono(String as_telefono)
	{
		this.is_telefono = as_telefono;
	}

	/**
	 * Retorna Objeto o variable de valor telefono.
	 *
	 * @return el valor de telefono
	 */
	public String getTelefono()
	{
		return is_telefono;
	}

	/**
	 * Modifica el valor de Tipo.
	 *
	 * @param tipo de tipo
	 */
	public void setTipo(String tipo)
	{
		this.is_tipo = tipo;
	}

	/**
	 * Retorna Objeto o variable de valor tipo.
	 *
	 * @return el valor de tipo
	 */
	public String getTipo()
	{
		return is_tipo;
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

	/**
	 * Modifica el valor de TipoNotificacion.
	 *
	 * @param as_tipoNotificacion de as tipo notificacion
	 */
	public void setTipoNotificacion(String as_tipoNotificacion)
	{
		this.is_tipoNotificacion = as_tipoNotificacion;
	}

	/**
	 * Retorna Objeto o variable de valor tipo notificacion.
	 *
	 * @return el valor de tipo notificacion
	 */
	public String getTipoNotificacion()
	{
		return is_tipoNotificacion;
	}
}
