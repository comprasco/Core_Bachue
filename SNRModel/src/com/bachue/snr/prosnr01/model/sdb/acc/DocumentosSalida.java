package com.bachue.snr.prosnr01.model.sdb.acc;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.reimpresion.Reimpresion;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_DOCUMENTOS_SALIDA.
 *
 * @author Julian Vaca
 */
public class DocumentosSalida extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7343521300991639468L;

	/** Propiedad id fecha acuse recibo componente. */
	private Date id_fechaAcuseReciboComponente;

	/** Propiedad id fecha desfijacion aviso. */
	private Date id_fechaDesfijacionAviso;

	/** Propiedad id fecha envio. */
	private Date id_fechaEnvio;

	/** Propiedad id fecha envio componente. */
	private Date id_fechaEnvioComponente;

	/** Propiedad id fecha intento envio. */
	private Date id_fechaIntentoEnvio;

	/** Propiedad id fecha notificacion. */
	private Date id_fechaNotificacion;

	/** Propiedad id fecha oficio. */
	private Date id_fechaOficio;

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

	/** Propiedad ld fecha guia. */
	private Date ld_fechaGuia;

	/** Propiedad ai id turno historia. */
	private Integer ii_idTurnoHistoria;

	/** Propiedad il consecutivo resolucion. */
	private Long il_consecutivoResolucion;

	/** Propiedad il documento salida asociada. */
	private Long il_documentoSalidaAsociada;

	/** Propiedad il id imagen. */
	private Long il_idImagen;

	/** Propiedad il numero resol asociada. */
	private Long il_numeroResolAsociada;

	/** Propiedad ir reimpresion. */
	private Reimpresion ir_reimpresion;

	/** Propiedad is causal reimpresion. */
	private String is_causalReimpresionValor;

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

	/** Propiedad is eliminar envio. */
	private String is_eliminarEnvio;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is estado impresion. */
	private String is_estadoImpresion;

	/** Propiedad is id datos ant sistema. */
	private String is_idDatosAntSistema;

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

	/** Propiedad is_idPersonaNotificacion. */
	private String is_idPersonaNotificacion;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id tipo documental. */
	private String is_idTipoDocumental;

	/** Propiedad is id tipo documental nombre. */
	private String is_idTipoDocumentalNombre;

	/** Propiedad is id tipo notificacion. */
	private String is_idTipoNotificacion;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is medio publicación. */
	private String is_medioPublicacion;

	/** Propiedad il numero guia. */
	private String is_numeroGuia;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is referencia salida. */
	private String is_referenciaSalida;

	/** Propiedad is repositorio. */
	private String is_repositorio;

	/** Propiedad is responsable publicación. */
	private String is_responsablePublicacion;

	/** Propiedad is telefono. */
	private String is_telefono;

	/** Propiedad is tipo. */
	private String is_tipo;

	/** Propiedad is tipo archivo. */
	private String is_tipoArchivo;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/** Propiedad ii intento envio. */
	private int ii_intentoEnvio;

	/** Propiedad ii numero copias. */
	private int ii_numeroCopias;

	/** Propiedad ii reimpresion documento. */
	private int ii_reimpresionDocumento;

	/** Propiedad il id documento salida. */
	private long il_idDocumentoSalida;

	/**
	 * Constructor por defecto.
	 */
	public DocumentosSalida()
	{
	}

	/**
	 * Constructor que recibe por parametro el id del docuemento de salida.
	 *
	 * @param al_idDocumentoSalida de al id documento salida
	 */
	public DocumentosSalida(long al_idDocumentoSalida)
	{
		setIdDocumentoSalida(al_idDocumentoSalida);
	}

	/**
	 * Instancia un nuevo objeto documentos salida.
	 *
	 * @param as_idTurno de as id turno
	 */
	public DocumentosSalida(String as_idTurno)
	{
		setIdTurno(as_idTurno);
	}

	/**
	 * Instancia un nuevo objeto documentos salida.
	 *
	 * @param ai_idTurnoHistoria de ai id turno historia
	 * @param as_idTipoDocumental de as id tipo documental
	 */
	public DocumentosSalida(Integer ai_idTurnoHistoria, String as_idTipoDocumental)
	{
		ii_idTurnoHistoria      = ai_idTurnoHistoria;
		is_idTipoDocumental     = as_idTipoDocumental;
	}

	/**
	 * Constructor que recibe por parametro el id de la solicitud, tipo y estado.
	 *
	 * @param as_idSolicitud id solicitud
	 * @param as_tipo tipo
	 * @param as_estado estado
	 */
	public DocumentosSalida(String as_idSolicitud, String as_tipo, String as_estado)
	{
		setIdSolicitud(as_idSolicitud);
		setTipo(as_tipo);
		setEstado(as_estado);
	}

	/**
	 * Instancia un nuevo objeto documentos salida.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idTurno de as id turno
	 * @param as_tipoDocumental de as tipo documental
	 * @param as_estado de as estado
	 */
	public DocumentosSalida(String as_idSolicitud, String as_idTurno, String as_tipoDocumental, String as_estado)
	{
		setIdSolicitud(as_idSolicitud);
		setIdTurno(as_idTurno);
		setIdTipoDocumental(as_tipoDocumental);
		setEstado(as_estado);
	}

	/**
	 * Instancia un nuevo objeto documentos salida.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idPais de as id pais
	 * @param as_idDepartamento de as id departamento
	 * @param as_idMunicipio de as id municipio
	 * @param as_direccion de as direccion
	 */
	public DocumentosSalida(
	    String as_idSolicitud, String as_idPais, String as_idDepartamento, String as_idMunicipio, String as_direccion
	)
	{
		setIdPais(as_idPais);
		setIdDepartamento(as_idDepartamento);
		setIdMunicipio(as_idMunicipio);
		setDireccion(as_direccion);
	}

	/**
	 * Modifica el valor de causal reimpresion.
	 *
	 * @param as_s
	 *                 asigna el valor a la propiedad causal reimpresion
	 */
	public void setCausalReimpresionValor(String as_s)
	{
		is_causalReimpresionValor = as_s;
	}

	/**
	 * Retorna el valor de causal reimpresion.
	 *
	 * @return el valor de causal reimpresion
	 */
	public String getCausalReimpresionValor()
	{
		return is_causalReimpresionValor;
	}

	/**
	 * Método de asignación del valor de la propiedad consecutivo oficio.
	 *
	 * @param as_s de tipo String con el valor a asignar
	 */
	public void setConsecutivoOficio(String as_s)
	{
		is_consecutivoOficio = as_s;
	}

	/**
	 * Método de obtención del valor de la propiedad Consecutivo Oficio.
	 *
	 * @return el valor de la propiedad consecutivo Oficio de tipo String
	 */
	public String getConsecutivoOficio()
	{
		return is_consecutivoOficio;
	}

	/**
	 * Método de asignación del valor de la propiedad Consecutivo Resolucion.
	 *
	 * @param al_l de tipo Long con el valor  a asignar
	 */
	public void setConsecutivoResolucion(Long al_l)
	{
		il_consecutivoResolucion = al_l;
	}

	/**
	 * Método de obtención del valor de la propiedad Consecutivo Resolución.
	 *
	 * @return de Tipo Long con el valor de la propiedad
	 */
	public Long getConsecutivoResolucion()
	{
		return il_consecutivoResolucion;
	}

	/**
	 * Modifica el valor de CorreoElectronico.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCorreoElectronico(String as_s)
	{
		is_correoElectronico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor correo electronico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Modifica el valor de Destinatario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDestinatario(String as_s)
	{
		is_destinatario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor destinatario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDestinatario()
	{
		return is_destinatario;
	}

	/**
	 * Modifica el valor de Direccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDireccion(String as_s)
	{
		is_direccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor direccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDireccion()
	{
		return is_direccion;
	}

	/**
	 * Modifica el valor de DocumentoAutomatico.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDocumentoAutomatico(String as_s)
	{
		is_documentoAutomatico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor documento automatico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDocumentoAutomatico()
	{
		return is_documentoAutomatico;
	}

	/**
	 * Modifica el valor de DocumentoFinal.
	 *
	 * @param as_s de as s
	 */
	public void setDocumentoFinal(String as_s)
	{
		is_documentoFinal = as_s;
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
	 * Modifica el valor de DocumentoSalidaAsociada.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setDocumentoSalidaAsociada(Long al_l)
	{
		il_documentoSalidaAsociada = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor documento salida asociada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getDocumentoSalidaAsociada()
	{
		return il_documentoSalidaAsociada;
	}

	/**
	 * Modifica el valor de EliminarEnvio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEliminarEnvio(String as_s)
	{
		is_eliminarEnvio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor eliminar envio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEliminarEnvio()
	{
		return is_eliminarEnvio;
	}

	/**
	 * Valida la propiedad enviado owcc.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isEnviadoOwcc()
	{
		boolean lb_enviado;

		lb_enviado = false;

		{
			String ls_idEcm;
			String ls_idNombreDocumento;

			ls_idEcm                 = getIdEcm();
			ls_idNombreDocumento     = getIdNombreDocumento();

			lb_enviado = StringUtils.isValidString(ls_idEcm) && StringUtils.isValidString(ls_idNombreDocumento)
					&& !ls_idEcm.equalsIgnoreCase(IdentificadoresCommon.X)
					&& !ls_idNombreDocumento.equalsIgnoreCase(IdentificadoresCommon.X);
		}

		return lb_enviado;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setEstado(String ad_d)
	{
		is_estado = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de EstadoImpresion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstadoImpresion(String as_s)
	{
		is_estadoImpresion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado impresion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoImpresion()
	{
		return is_estadoImpresion;
	}

	/**
	 * Modifica el valor de FechaAcuseReciboComponente.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaAcuseReciboComponente(Date ad_d)
	{
		id_fechaAcuseReciboComponente = ad_d;
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
	 * Modifica el valor de FechaDesfijacionAviso.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaDesfijacionAviso(Date ad_d)
	{
		id_fechaDesfijacionAviso = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha desfijacion aviso.
	 *
	 * @return Retorna el valor de la propiedad fechaDesfijacionAviso
	 */
	public Date getFechaDesfijacionAviso()
	{
		return id_fechaDesfijacionAviso;
	}

	/**
	 * Modifica el valor de FechaEnvio.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaEnvio(Date ad_d)
	{
		id_fechaEnvio = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha envio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaEnvio()
	{
		return id_fechaEnvio;
	}

	/**
	 * Modifica el valor de FechaEnvioComponente.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaEnvioComponente(Date ad_d)
	{
		id_fechaEnvioComponente = ad_d;
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
	 * @param ad_d de ad d
	 */
	public void setFechaGuia(Date ad_d)
	{
		ld_fechaGuia = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha guia.
	 *
	 * @return el valor de fecha guia
	 */
	public Date getFechaGuia()
	{
		return ld_fechaGuia;
	}

	/**
	 * Modifica el valor de FechaIntentoEnvio.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaIntentoEnvio(Date ad_d)
	{
		id_fechaIntentoEnvio = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha intento envio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaIntentoEnvio()
	{
		return id_fechaIntentoEnvio;
	}

	/**
	 * Modifica el valor de FechaNotificacion.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaNotificacion(Date ad_d)
	{
		id_fechaNotificacion = ad_d;
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
	 * Método de asignación de la propiedad Fecha Oficio.
	 *
	 * @param ad_d con el valor a asignar
	 */
	public void setFechaOficio(Date ad_d)
	{
		id_fechaOficio = ad_d;
	}

	/**
	 * Método de obtención del valor de la propiedad Fecha Oficio.
	 *
	 * @return con el valor de la propiedad de tipo Date
	 */
	public Date getFechaOficio()
	{
		return id_fechaOficio;
	}

	/**
	 * Modifica el valor de FechaPublicacionAvisoWeb.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaPublicacionAvisoWeb(Date ad_d)
	{
		id_fechaPublicacionAvisoWeb = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha publicacion aviso web.
	 *
	 * @return Retorna el valor de la propiedad fechaPublicacionAvisoWeb
	 */
	public Date getFechaPublicacionAvisoWeb()
	{
		return id_fechaPublicacionAvisoWeb;
	}

	/**
	 * Modifica el valor de FechaRadicacionEcm.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaRadicacionEcm(Date ad_d)
	{
		id_fechaRadicacionEcm = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha radicacion ecm.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaRadicacionEcm()
	{
		return id_fechaRadicacionEcm;
	}

	/**
	 * @param ad_d Modifica el valor de la propiedad fechaReciboAvisoWeb
	 */
	public void setFechaReciboAvisoWeb(Date ad_d)
	{
		id_fechaReciboAvisoWeb = ad_d;
	}

	/**
	 * @return Retorna el valor de la propiedad fechaReciboAvisoWeb
	 */
	public Date getFechaReciboAvisoWeb()
	{
		return id_fechaReciboAvisoWeb;
	}

	/**
	 * Modifica el valor de FechaResolAsociada.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaResolAsociada(Date ad_d)
	{
		id_fechaResolAsociada = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha resol asociada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaResolAsociada()
	{
		return id_fechaResolAsociada;
	}

	/**
	 * Método de asignación del valor de la propiedad Fecha Resolución.
	 *
	 * @param ad_d de tipo date con el valor a a asignar
	 */
	public void setFechaResolucion(Date ad_d)
	{
		id_fechaResolucion = ad_d;
	}

	/**
	 * Método de obtención del valor de la propiedad Fecha resolución.
	 *
	 * @return de tipo Date con el valor de la propiedad
	 */
	public Date getFechaResolucion()
	{
		return id_fechaResolucion;
	}

	/**
	 * Modifica el valor de FechaRespuestaMensaje.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaRespuestaMensaje(Date ad_d)
	{
		id_fechaRespuestaMensaje = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha respuesta mensaje.
	 *
	 * @return Retorna el valor de la propiedad fechaRespuestaMensaje
	 */
	public Date getFechaRespuestaMensaje()
	{
		return id_fechaRespuestaMensaje;
	}

	/**
	 * Modifica el valor de IdDatosAntSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDatosAntSistema(String as_s)
	{
		is_idDatosAntSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id datos ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDatosAntSistema()
	{
		return is_idDatosAntSistema;
	}

	/**
	 * Modifica el valor de IdDepartamento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDepartamento(String as_s)
	{
		is_idDepartamento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id departamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de IdDocumentoSalida.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdDocumentoSalida(long al_l)
	{
		il_idDocumentoSalida = al_l;
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
	 * Modifica el valor de IdEcm.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEcm(String as_s)
	{
		is_idEcm = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id ecm.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEcm()
	{
		return is_idEcm;
	}

	/**
	 * Modifica el valor de IdImagen.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdImagen(Long al_l)
	{
		il_idImagen = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id imagen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdImagen()
	{
		return il_idImagen;
	}

	/**
	 * Modifica el valor de IdMensaje.
	 *
	 * @param as_s de as s
	 */
	public void setIdMensaje(String as_s)
	{
		is_idMensaje = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id mensaje.
	 *
	 * @return Retorna el valor de la propiedad idMensaje
	 */
	public String getIdMensaje()
	{
		return is_idMensaje;
	}

	/**
	 * Modifica el valor de IdMunicipio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdMunicipio(String as_s)
	{
		is_idMunicipio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id municipio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * Modifica el valor de IdNombreDocumento.
	 *
	 * @param as_s de as s
	 */
	public void setIdNombreDocumento(String as_s)
	{
		is_idNombreDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id nombre documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdNombreDocumento()
	{
		return is_idNombreDocumento;
	}

	/**
	 * Modifica el valor de IdPais.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id pais.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param as_s de tipo String con el valor a asignar
	 */
	public void setIdPersonaNotificacion(String as_s)
	{
		is_idPersonaNotificacion = as_s;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return el valor de la propiedad de tipo String
	 */
	public String getIdPersonaNotificacion()
	{
		return is_idPersonaNotificacion;
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
	 * Modifica el valor de IdTipoDocumental.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoDocumental(String as_s)
	{
		is_idTipoDocumental = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo documental.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoDocumental()
	{
		return is_idTipoDocumental;
	}

	/**
	 * Modifica el valor de IdTipoDocumentalNombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoDocumentalNombre(String as_s)
	{
		is_idTipoDocumentalNombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo documental nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoDocumentalNombre()
	{
		return is_idTipoDocumentalNombre;
	}

	/**
	 * Modifica el valor de IdTipoNotificacion.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoNotificacion(String as_s)
	{
		is_idTipoNotificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo notificacion.
	 *
	 * @return Retorna el valor de la propiedad idTipoNotificacion
	 */
	public String getIdTipoNotificacion()
	{
		return is_idTipoNotificacion;
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
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setIdTurnoHistoria(Integer ai_i)
	{
		ii_idTurnoHistoria = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getIdTurnoHistoria()
	{
		return ii_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de IntentoEnvio.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setIntentoEnvio(int ai_i)
	{
		ii_intentoEnvio = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor intento envio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getIntentoEnvio()
	{
		return ii_intentoEnvio;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad medioPublicacion
	 */
	public void setMedioPublicacion(String as_s)
	{
		is_medioPublicacion = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad medioPublicacion
	 */
	public String getMedioPublicacion()
	{
		return is_medioPublicacion;
	}

	/**
	 * Modifica el valor de NumeroCopias.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setNumeroCopias(int ai_i)
	{
		ii_numeroCopias = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor numero copias.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getNumeroCopias()
	{
		return ii_numeroCopias;
	}

	/**
	 * Modifica el valor de NumeroGuia.
	 *
	 * @param as_s de as s
	 */
	public void setNumeroGuia(String as_s)
	{
		is_numeroGuia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero guia.
	 *
	 * @return el valor de numero guia
	 */
	public String getNumeroGuia()
	{
		return is_numeroGuia;
	}

	/**
	 * Modifica el valor de NumeroResolAsociada.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setNumeroResolAsociada(Long al_l)
	{
		il_numeroResolAsociada = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor numero resol asociada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getNumeroResolAsociada()
	{
		return il_numeroResolAsociada;
	}

	/**
	 * Modifica el valor de Observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de ReferenciaSalida.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setReferenciaSalida(String as_s)
	{
		is_referenciaSalida = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor referencia salida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getReferenciaSalida()
	{
		return is_referenciaSalida;
	}

	/**
	 * Modifica el valor de Reimpresion.
	 *
	 * @param ar_r asigna el valor a la propiedad
	 */
	public void setReimpresion(Reimpresion ar_r)
	{
		ir_reimpresion = ar_r;
	}

	/**
	 * Retorna Objeto o variable de valor reimpresion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Reimpresion getReimpresion()
	{
		return ir_reimpresion;
	}

	/**
	 * Modifica el valor de ReimpresionDocumento.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setReimpresionDocumento(int ai_i)
	{
		ii_reimpresionDocumento = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor reimpresion documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getReimpresionDocumento()
	{
		return ii_reimpresionDocumento;
	}

	/**
	 * Modifica el valor de Repositorio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRepositorio(String as_s)
	{
		is_repositorio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor repositorio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRepositorio()
	{
		return is_repositorio;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad responsablePublicacion
	 */
	public void setResponsablePublicacion(String as_s)
	{
		is_responsablePublicacion = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad responsablePublicacion
	 */
	public String getResponsablePublicacion()
	{
		return is_responsablePublicacion;
	}

	/**
	 * Modifica el valor de seleccionado.
	 *
	 * @param ab_b
	 *                 asigna el valor a la propiedad seleccionado
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Retorna el valor de seleccionado.
	 *
	 * @return el valor de seleccionado
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Modifica el valor de Telefono.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTelefono(String as_s)
	{
		is_telefono = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor telefono.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTelefono()
	{
		return is_telefono;
	}

	/**
	 * Modifica el valor de Tipo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipo(String as_s)
	{
		is_tipo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipo()
	{
		return is_tipo;
	}

	/**
	 * Modifica el valor de TipoArchivo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoArchivo(String as_s)
	{
		is_tipoArchivo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo archivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoArchivo()
	{
		return is_tipoArchivo;
	}
}
