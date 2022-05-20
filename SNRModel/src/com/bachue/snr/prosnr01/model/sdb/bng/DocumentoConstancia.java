package com.bachue.snr.prosnr01.model.sdb.bng;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de DocumentoConstancia.
 *
 * @author Julian Vaca
 */
public class DocumentoConstancia extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID          = -2982373730532951248L;
	
	/** Propiedad ibd version. */
	private BigDecimal        ibd_version;
	
	/** Propiedad id fecha documento. */
	private Date              id_fechaDocumento;
	
	/** Propiedad id fecha ejecutoria. */
	private Date              id_fechaEjecutoria;
	
	/** Propiedad il numero copias. */
	private Long              il_numeroCopias;
	
	/** Propiedad il version documento. */
	private Long              il_versionDocumento;
	
	/** Propiedad is comentario. */
	private String            is_comentario;
	
	/** Propiedad is documento. */
	private String            is_documento;
	
	/** Propiedad is entidad exenta. */
	private String            is_entidadExenta;
	
	/** Propiedad is escritura. */
	private String            is_escritura;
	
	/** Propiedad is id departamento. */
	private String            is_idDepartamento;
	
	/** Propiedad is id documento. */
	private String            is_idDocumento;
	
	/** Propiedad is id municipio. */
	private String            is_idMunicipio;
	
	/** Propiedad is id oficina origen. */
	private String            is_idOficinaOrigen;
	
	/** Propiedad is id pais. */
	private String            is_idPais;
	
	/** Propiedad is id tipo documento. */
	private String            is_idTipoDocumento;
	
	/** Propiedad is id tipo entidad. */
	private String            is_idTipoEntidad;
	
	/** Propiedad is id tipo oficina. */
	private String            is_idTipoOficina;
	
	/** Propiedad is id usuario. */
	private String            is_idUsuario;
	
	/** Propiedad is id usuario modificacion. */
	private String            is_idUsuarioModificacion;
	
	/** Propiedad is ip creacion. */
	private String            is_ipCreacion;
	
	/** Propiedad is ip modificacion. */
	private String            is_ipModificacion;
	
	/** Propiedad is is nombre tipo documento. */
	private String            is_is_nombreTipoDocumento;
	
	/** Propiedad is nombre oficina. */
	private String            is_nombreOficina;
	
	/** Propiedad is nombre oficina origen. */
	private String            is_nombreOficinaOrigen;
	
	/** Propiedad is numero. */
	private String            is_numero;
	
	/** Propiedad is oficina internacional. */
	private String            is_oficinaInternacional;
	
	/** Propiedad ib seleccione. */
	private boolean           ib_seleccione;

	/**
	 * Constructor por defecto.
	 */
	public DocumentoConstancia()
	{
		super();
	}

	/**
	 * Modifica el valor de Comentario.
	 *
	 * @param comentario asigna el valor a la propiedad
	 */
	public void setComentario(String comentario)
	{
		this.is_comentario                              = comentario;
	}

	/**
	 * Retorna Objeto o variable de valor comentario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getComentario()
	{
		return is_comentario;
	}

	/**
	 * Modifica el valor de Documento.
	 *
	 * @param documento asigna el valor a la propiedad
	 */
	public void setDocumento(String documento)
	{
		this.is_documento = documento;
	}

	/**
	 * Retorna Objeto o variable de valor documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDocumento()
	{
		return is_documento;
	}

	/**
	 * Modifica el valor de EntidadExenta.
	 *
	 * @param entidadExenta asigna el valor a la propiedad
	 */
	public void setEntidadExenta(String entidadExenta)
	{
		this.is_entidadExenta = entidadExenta;
	}

	/**
	 * Retorna Objeto o variable de valor entidad exenta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEntidadExenta()
	{
		return is_entidadExenta;
	}

	/**
	 * Modifica el valor de Escritura.
	 *
	 * @param escritura asigna el valor a la propiedad
	 */
	public void setEscritura(String escritura)
	{
		this.is_escritura = escritura;
	}

	/**
	 * Retorna Objeto o variable de valor escritura.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEscritura()
	{
		return is_escritura;
	}

	/**
	 * Modifica el valor de FechaDocumento.
	 *
	 * @param fechaDocumento asigna el valor a la propiedad
	 */
	public void setFechaDocumento(Date fechaDocumento)
	{
		this.id_fechaDocumento = fechaDocumento;
	}

	/**
	 * Retorna Objeto o variable de valor fecha documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaDocumento()
	{
		return id_fechaDocumento;
	}

	/**
	 * Modifica el valor de FechaEjecutoria.
	 *
	 * @param fechaEjecutoria asigna el valor a la propiedad
	 */
	public void setFechaEjecutoria(Date fechaEjecutoria)
	{
		this.id_fechaEjecutoria = fechaEjecutoria;
	}

	/**
	 * Retorna Objeto o variable de valor fecha ejecutoria.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaEjecutoria()
	{
		return id_fechaEjecutoria;
	}

	/**
	 * Modifica el valor de IdDepartamento.
	 *
	 * @param idDepartamento asigna el valor a la propiedad
	 */
	public void setIdDepartamento(String idDepartamento)
	{
		this.is_idDepartamento = idDepartamento;
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
	 * Modifica el valor de IdDocumento.
	 *
	 * @param idDocumento asigna el valor a la propiedad
	 */
	public void setIdDocumento(String idDocumento)
	{
		this.is_idDocumento = idDocumento;
	}

	/**
	 * Retorna Objeto o variable de valor id documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDocumento()
	{
		return is_idDocumento;
	}

	/**
	 * Modifica el valor de IdMunicipio.
	 *
	 * @param idMunicipio asigna el valor a la propiedad
	 */
	public void setIdMunicipio(String idMunicipio)
	{
		this.is_idMunicipio = idMunicipio;
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
	 * Modifica el valor de IdOficinaOrigen.
	 *
	 * @param idOficinaOrigen asigna el valor a la propiedad
	 */
	public void setIdOficinaOrigen(String idOficinaOrigen)
	{
		this.is_idOficinaOrigen = idOficinaOrigen;
	}

	/**
	 * Retorna Objeto o variable de valor id oficina origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdOficinaOrigen()
	{
		return is_idOficinaOrigen;
	}

	/**
	 * Modifica el valor de IdPais.
	 *
	 * @param idPais asigna el valor a la propiedad
	 */
	public void setIdPais(String idPais)
	{
		this.is_idPais = idPais;
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
	 * Modifica el valor de IdTipoDocumento.
	 *
	 * @param idTipoDocumento asigna el valor a la propiedad
	 */
	public void setIdTipoDocumento(String idTipoDocumento)
	{
		this.is_idTipoDocumento = idTipoDocumento;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoDocumento()
	{
		return is_idTipoDocumento;
	}

	/**
	 * Modifica el valor de IdTipoEntidad.
	 *
	 * @param idTipoEntidad asigna el valor a la propiedad
	 */
	public void setIdTipoEntidad(String idTipoEntidad)
	{
		this.is_idTipoEntidad = idTipoEntidad;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo entidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoEntidad()
	{
		return is_idTipoEntidad;
	}

	/**
	 * Modifica el valor de IdTipoOficina.
	 *
	 * @param idTipoOficina asigna el valor a la propiedad
	 */
	public void setIdTipoOficina(String idTipoOficina)
	{
		this.is_idTipoOficina = idTipoOficina;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo oficina.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoOficina()
	{
		return is_idTipoOficina;
	}

	/**
	 * Modifica el valor de IdUsuario.
	 *
	 * @param idUsuario asigna el valor a la propiedad
	 */
	public void setIdUsuario(String idUsuario)
	{
		this.is_idUsuario = idUsuario;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}

	/**
	 * Modifica el valor de IdUsuarioModificacion.
	 *
	 * @param idUsuarioModificacion asigna el valor a la propiedad
	 */
	public void setIdUsuarioModificacion(String idUsuarioModificacion)
	{
		this.is_idUsuarioModificacion = idUsuarioModificacion;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario modificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUsuarioModificacion()
	{
		return is_idUsuarioModificacion;
	}

	/**
	 * Modifica el valor de IpCreacion.
	 *
	 * @param ipCreacion asigna el valor a la propiedad
	 */
	public void setIpCreacion(String ipCreacion)
	{
		this.is_ipCreacion = ipCreacion;
	}

	/**
	 * Retorna Objeto o variable de valor ip creacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIpCreacion()
	{
		return is_ipCreacion;
	}

	/**
	 * Modifica el valor de IpModificacion.
	 *
	 * @param ipModificacion asigna el valor a la propiedad
	 */
	public void setIpModificacion(String ipModificacion)
	{
		this.is_ipModificacion = ipModificacion;
	}

	/**
	 * Retorna Objeto o variable de valor ip modificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIpModificacion()
	{
		return is_ipModificacion;
	}

	/**
	 * Modifica el valor de NombreOficina.
	 *
	 * @param nombreOficina asigna el valor a la propiedad
	 */
	public void setNombreOficina(String nombreOficina)
	{
		this.is_nombreOficina = nombreOficina;
	}

	/**
	 * Retorna Objeto o variable de valor nombre oficina.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreOficina()
	{
		return is_nombreOficina;
	}

	/**
	 * Modifica el valor de NombreOficinaOrigen.
	 *
	 * @param nombreOficinaOrigen asigna el valor a la propiedad
	 */
	public void setNombreOficinaOrigen(String nombreOficinaOrigen)
	{
		this.is_nombreOficinaOrigen = nombreOficinaOrigen;
	}

	/**
	 * Retorna Objeto o variable de valor nombre oficina origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreOficinaOrigen()
	{
		return is_nombreOficinaOrigen;
	}

	/**
	 * Modifica el valor de NombreTipoDocumento.
	 *
	 * @param nombreTipoDocumento asigna el valor a la propiedad
	 */
	public void setNombreTipoDocumento(String nombreTipoDocumento)
	{
		this.is_is_nombreTipoDocumento = nombreTipoDocumento;
	}

	/**
	 * Retorna Objeto o variable de valor nombre tipo documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreTipoDocumento()
	{
		return is_is_nombreTipoDocumento;
	}

	/**
	 * Modifica el valor de Numero.
	 *
	 * @param numero asigna el valor a la propiedad
	 */
	public void setNumero(String numero)
	{
		this.is_numero = numero;
	}

	/**
	 * Retorna Objeto o variable de valor numero.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumero()
	{
		return is_numero;
	}

	/**
	 * Modifica el valor de NumeroCopias.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setNumeroCopias(Long al_l)
	{
		il_numeroCopias = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor numero copias.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getNumeroCopias()
	{
		return il_numeroCopias;
	}

	/**
	 * Modifica el valor de OficinaInternacional.
	 *
	 * @param oficinaInternacional asigna el valor a la propiedad
	 */
	public void setOficinaInternacional(String oficinaInternacional)
	{
		this.is_oficinaInternacional = oficinaInternacional;
	}

	/**
	 * Retorna Objeto o variable de valor oficina internacional.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getOficinaInternacional()
	{
		return is_oficinaInternacional;
	}

	/**
	 * Modifica el valor de Seleccione.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setSeleccione(boolean ab_b)
	{
		ib_seleccione = ab_b;
	}

	/**
	 * Valida la propiedad seleccione.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSeleccione()
	{
		return ib_seleccione;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param version asigna el valor a la propiedad
	 */
	public void setVersion(BigDecimal version)
	{
		this.ibd_version = version;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getVersion()
	{
		return ibd_version;
	}

	/**
	 * Modifica el valor de VersionDocumento.
	 *
	 * @param versionDocumento asigna el valor a la propiedad
	 */
	public void setVersionDocumento(Long versionDocumento)
	{
		this.il_versionDocumento = versionDocumento;
	}

	/**
	 * Retorna Objeto o variable de valor version documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getVersionDocumento()
	{
		return il_versionDocumento;
	}
}
