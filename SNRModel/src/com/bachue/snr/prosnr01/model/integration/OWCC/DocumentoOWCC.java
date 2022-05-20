package com.bachue.snr.prosnr01.model.integration.OWCC;

import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoDocumento;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import java.io.Serializable;

import java.util.Date;


/**
 * Class que contiene todos las propiedades DocumentoOWCC.
 *
 * @author jpatino
 */
public class DocumentoOWCC implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6602973901735086705L;

	/** Propiedad id fecha documento. */
	private Date id_fechaDocumento;

	/** Propiedad id fecha publicacion. */
	private Date id_fechaPublicacion;

	/** Propiedad id fecha vigencia. */
	private Date id_fechaVigencia;

	/** Propiedad il numero copias. */
	private Long il_numeroCopias;

	/** Propiedad is URL. */
	private String is_URL;

	/** Propiedad is anotacion. */
	private String is_anotacion;

	/** Propiedad is codigo etapa. */
	private String is_codigoEtapa;

	/** Propiedad is doc name. */
	private String is_docName;

	/** Propiedad is doc registrado. */
	private String is_docRegistrado;

	/** Propiedad is doc type. */
	private String is_docType;

	/** Propiedad is documentos intervinientes. */
	private String is_documentosIntervinientes;

	/** Propiedad is entidad origen. */
	private String is_entidadOrigen;

	/** Propiedad is i D. */
	private String is_iD;

	/** Propiedad is id orip. */
	private String is_idOrip;

	/** Propiedad is matriculas. */
	private String is_matriculas;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nir vinculado. */
	private String is_nirVinculado;

	/** Propiedad is nombre departamento. */
	private String is_nombreDepartamento;

	/** Propiedad is nombre municipio. */
	private String is_nombreMunicipio;

	/** Propiedad is nombre orip. */
	private String is_nombreOrip;

	/** Propiedad is nombre pais. */
	private String is_nombrePais;

	/** Propiedad is nombres intervinientes. */
	private String is_nombresIntervinientes;

	/** Propiedad is numero doc. */
	private String is_numeroDoc;

	/** Propiedad is numeroFolios. */
	private String is_numeroFolios;

	/** Propiedad is numero paginas. */
	private String is_numeroPaginas;

	/** Propiedad is operador. */
	private String is_operador;

	/** Propiedad is proceso. */
	private String is_proceso;

	/** Propiedad is tipo acto. */
	private String is_tipoActo;

	/** Propiedad is tipo oficina. */
	private String is_tipoOficina;

	/** Propiedad is turno. */
	private String is_turno;

	/** Propiedad is turno vinculado. */
	private String is_turnoVinculado;

	/** Propiedad ib tipo documental. */
	private TipoDocumental itd_tipoDocumental;

	/** Propiedad ib seleccione. */
	private boolean ib_seleccione;
	
	/** Propiedad is ruta servidor FTP. */
	private String is_rutaServidorFTP;
	
	/** Propiedad is comprobante. */
	private String is_comprobante;
	
	/** Propiedad is numeroSIIF. */
	private String is_numeroSIIF;
	
	/** Propiedad is version . */
	private String is_version;
	
	/** Propiedad is numero Cuenta. */
	private String is_numeroCuenta;
	
	/** Propiedad is codigo Banco. */
	private String is_codigoBanco;
	
	/** Propiedad is nombreBanco. */
	private String is_nombreBanco;
	
	/** Propiedad il periodo corte. */
	private Long il_periodoCorte;
	
	/**
	 * Instancia un nuevo objeto documento OWCC.
	 */
	public DocumentoOWCC()
	{
	}

	/**
	 * Instancia un nuevo objeto documento OWCC.
	 *
	 * @param as_ID de as ID
	 * @param as_docName de as doc name
	 */
	public DocumentoOWCC(String as_ID, String as_docName)
	{
		is_iD          = as_ID;
		is_docName     = as_docName;
	}

	/**
	 * Instancia un nuevo objeto documento OWCC.
	 *
	 * @param as_parametro de as parametro
	 * @param ab_nir de ab nir
	 */
	public DocumentoOWCC(String as_parametro, boolean ab_nir)
	{
		if(StringUtils.isValidString(as_parametro))
		{
			if(ab_nir)
				setNir(as_parametro);
			else
				setTurno(as_parametro);
		}
	}

	/**
	 * Instancia un nuevo objeto documento OWCC.
	 *
	 * @param atd_documentoOWCC de atd documento OWCC
	 */
	public DocumentoOWCC(TipoDocumento atd_documentoOWCC)
	{
		if(atd_documentoOWCC != null)
		{
			setiD(atd_documentoOWCC.getDID());
			setDocName(atd_documentoOWCC.getDocName());
			setDocType(atd_documentoOWCC.getTipoDocumental());
			setNombreOrip(atd_documentoOWCC.getOrip());
			setNir(atd_documentoOWCC.getNir());
			setIdOrip(atd_documentoOWCC.getCodOrip());
			setTurno(atd_documentoOWCC.getTurno());
			setDocRegistrado(atd_documentoOWCC.getDocumentoRegistrado());
			setNumeroDoc(atd_documentoOWCC.getNumeroDocumento());
			setFechaDocumento(
			    DateUtils.getDate(atd_documentoOWCC.getFechaDocumento(), FormatoFechaCommon.DIA_MES_ANIO)
			);
			setEntidadOrigen(atd_documentoOWCC.getEntidadOrigen());
			setNombrePais(atd_documentoOWCC.getPais());
			setNombreDepartamento(atd_documentoOWCC.getDepartamento());
			setNombreMunicipio(atd_documentoOWCC.getMunicipio());
			setMatriculas(atd_documentoOWCC.getMatricula());
			setAnotacion(atd_documentoOWCC.getAnotacion());
			setNumeroPaginas(atd_documentoOWCC.getNumeroPagina());
			setnumeroFolios(atd_documentoOWCC.getNumeroFolios());
			setNombresIntervinientes(atd_documentoOWCC.getNombreInterviniente());
			setDocumentosIntervinientes(atd_documentoOWCC.getIdentificacionInterviniente());
			setFechaPublicacion(
			    DateUtils.getDate(atd_documentoOWCC.getFechaPublicacion(), FormatoFechaCommon.DIA_MES_ANIO)
			);
			setFechaVigencia(DateUtils.getDate(atd_documentoOWCC.getFechaVigencia(), FormatoFechaCommon.DIA_MES_ANIO));
			setTipoOficina(atd_documentoOWCC.getTipoOficina());
			setTipoActo(atd_documentoOWCC.getActoNaturalezaJuridica());
			setProceso(atd_documentoOWCC.getProceso());
			setNirVinculado(atd_documentoOWCC.getNirVinculado());
			setTurnoVinculado(atd_documentoOWCC.getTurnoVinculado());
			setURL(atd_documentoOWCC.getUrlVisor());
		}
	}

	/**
	 * Número de anotación asociada a la matrícula inmobiliaria.
	 * ej: 31233
	 *
	 * @param as_s String Número de anotación asociada a la matrícula inmobiliaria.
	 */
	public void setAnotacion(String as_s)
	{
		is_anotacion = as_s;
	}

	/**
	 * Número de anotación asociada a la matrícula inmobiliaria.
	 * ej: 31233
	 *
	 * @return String Número de anotación asociada a la matrícula inmobiliaria.
	 */
	public String getAnotacion()
	{
		return is_anotacion;
	}

	/**
	 * <h2>CORRESPONDE A UN PARAMETRO DE CIERRE Y REAPERTURA DE EXPEDIENTES.</h2>
	 *
	 * Número de identificación de la etapa asociada al expediente.
	 * ej: 200
	 *
	 * @param as_s String Número de identificación de la etapa asociada al expediente.
	 */
	public void setCodigoEtapa(String as_s)
	{
		is_codigoEtapa = as_s;
	}

	/**
	 * <h2>CORRESPONDE A UN PARAMETRO DE CIERRE Y REAPERTURA DE EXPEDIENTES.</h2>
	 *
	 * Número de identificación de la etapa asociada al expediente.
	 * ej: 200
	 *
	 * @return String Número de identificación de la etapa asociada al expediente.
	 */
	public String getCodigoEtapa()
	{
		return is_codigoEtapa;
	}

	/**
	 * Identificador del documento en Owcc
	 * Si es temporal retorna el prefijo: TMP
	 * Si es el final retorna el prefijo: SNR
	 *
	 * ej:SNR039264.
	 *
	 * @param as_s String Identificador del documento en Owcc
	 */
	public void setDocName(String as_s)
	{
		is_docName = as_s;
	}

	/**
	 * Identificador del documento en Owcc
	 * Si es temporal retorna el prefijo: TMP
	 * Si es el final retorna el prefijo: SNR
	 *
	 * ej:SNR039264.
	 *
	 * @return String Identificador del documento en Owcc
	 */
	public String getDocName()
	{
		return is_docName;
	}

	/**
	 * Nombre del tipo del documento público objeto de registro.
	 * ej: Oficio, sentencia, escritura.
	 *
	 * @param as_s String Nombre del tipo del documento público objeto de registro.
	 */
	public void setDocRegistrado(String as_s)
	{
		is_docRegistrado = as_s;
	}

	/**
	 * Nombre del tipo del documento público objeto de registro.
	 * ej: Oficio, sentencia, escritura.
	 *
	 * @return String Nombre del tipo del documento público objeto de registro.
	 */
	public String getDocRegistrado()
	{
		return is_docRegistrado;
	}

	/**
	 * Nombre del tipo documental al que pertenece el documento.
	 * ej: Escritura,Certificado
	 *
	 * @param as_s String Nombre del tipo documental al que pertenece el documento.
	 */
	public void setDocType(String as_s)
	{
		is_docType = as_s;
	}

	/**
	 * Nombre del tipo documental al que pertenece el documento.
	 * ej: Escritura,Certificado
	 *
	 * @return String Nombre del tipo documental al que pertenece el documento.
	 */
	public String getDocType()
	{
		return is_docType;
	}

	/**
	 * ID del Tipo de documento de identificación y Número Identificación del ciudadano, empresa, persona que realiza el trámite separados por coma (,).
	 * ej: CC1234532,NIT44800000-9
	 *
	 * @param as_s String ID del Tipo de documento de identificación y Número Identificación del ciudadano, empresa, persona que realiza el trámite separados por coma (,).
	 */
	public void setDocumentosIntervinientes(String as_s)
	{
		is_documentosIntervinientes = as_s;
	}

	/**
	 * ID del Tipo de documento de identificación y Número Identificación del ciudadano, empresa, persona que realiza el trámite separados por coma (,).
	 * ej: CC1234532,NIT44800000-9
	 *
	 * @return String ID del Tipo de documento de identificación y Número Identificación del ciudadano, empresa, persona que realiza el trámite separados por coma (,).
	 */
	public String getDocumentosIntervinientes()
	{
		return is_documentosIntervinientes;
	}

	/**
	 * Nombre de la entidad que crea el documento.
	 * ej: Juzgado, Notaria, Banco.
	 *
	 * @param as_s String Nombre de la entidad que crea el documento.
	 */
	public void setEntidadOrigen(String as_s)
	{
		is_entidadOrigen = as_s;
	}

	/**
	 * Nombre de la entidad que crea el documento.
	 * ej: Juzgado, Notaria, Banco.
	 *
	 * @return String Nombre de la entidad que crea el documento.
	 */
	public String getEntidadOrigen()
	{
		return is_entidadOrigen;
	}

	/**
	 * Fecha del documento público objeto del registro.
	 * formato (DD/MM/YYYY).
	 *
	 * @param ad_d Date Fecha del documento público objeto del registro.
	 */
	public void setFechaDocumento(Date ad_d)
	{
		id_fechaDocumento = ad_d;
	}

	/**
	 * Fecha del documento público objeto del registro.
	 * formato (DD/MM/YYYY).
	 *
	 * @return Date Fecha del documento público objeto del registro.
	 */
	public Date getFechaDocumento()
	{
		return id_fechaDocumento;
	}

	/**
	 * Fecha en que se envía el documento al OWCC (fecha_envio)
	 * formato (DD/MM/YYYY).
	 *
	 * @param ad_d Date Fecha en que se envía el documento al OWCC (fecha_envio)
	 */
	public void setFechaPublicacion(Date ad_d)
	{
		id_fechaPublicacion = ad_d;
	}

	/**
	 * Fecha en que se envía el documento al OWCC (fecha_envio)
	 * formato (DD/MM/YYYY).
	 *
	 * @return Date Fecha en que se envía el documento al OWCC (fecha_envio)
	 */
	public Date getFechaPublicacion()
	{
		return id_fechaPublicacion;
	}

	/**
	 * Fecha de vigencia del recibo de liquidación.
	 * formato (DD/MM/YYYY).
	 *
	 * @param ad_d Date Fecha de vigencia del recibo de liquidación.
	 */
	public void setFechaVigencia(Date ad_d)
	{
		id_fechaVigencia = ad_d;
	}

	/**
	 * Fecha de vigencia del recibo de liquidación.
	 * formato (DD/MM/YYYY).
	 *
	 * @return Date Fecha de vigencia del recibo de liquidación.
	 */
	public Date getFechaVigencia()
	{
		return id_fechaVigencia;
	}

	/**
	 * Código ID del circulo registral de la oficina de registro de instrumentos públicos
	 * ej: 425,50C.
	 *
	 * @param as_s String Código ID del circulo registral de la oficina de registro de instrumentos públicos
	 */
	public void setIdOrip(String as_s)
	{
		is_idOrip = as_s;
	}

	/**
	 * Código ID del circulo registral de la oficina de registro de instrumentos públicos
	 * ej: 425,50C.
	 *
	 * @return String Código ID del circulo registral de la oficina de registro de instrumentos públicos
	 */
	public String getIdOrip()
	{
		return is_idOrip;
	}

	/**
	 * Nombre de la(s) matrícula(s) al que está asociado el documento separados por punto y coma (;).
	 * ej: FAE-547;RAM-123;ESR-432
	 *
	 * @param as_s String matrícula(s) (id_cisculo-id_matricula) al que está asociado el documento.
	 */
	public void setMatriculas(String as_s)
	{
		is_matriculas = as_s;
	}

	/**
	 * Nombre de la(s) matrícula(s) al que está asociado el documento separados por punto y coma (;).
	 * ej: FAE-547;RAM-123;ESR-432
	 *
	 * @return String matrícula(s) (id_cisculo-id_matricula) al que está asociado el documento.
	 */
	public String getMatriculas()
	{
		return is_matriculas;
	}

	/**
	 * Número de identificación registral al que está asociado el documento.
	 * ej: SNR2019000004357
	 *
	 * @param as_s String Número de identificación registral al que está asociado el documento.
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Número de identificación registral al que está asociado el documento.
	 * ej: SNR201900000XXXX
	 *
	 * @return String Número de identificación registral al que está asociado el documento.
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Identificador del NIR principal al que está asociado el NIR actual.
	 * ej: SNR201900000XXXX
	 *
	 * @param as_s String Identificador del NIR principal al que está asociado el NIR actual.
	 */
	public void setNirVinculado(String as_s)
	{
		is_nirVinculado = as_s;
	}

	/**
	 * Identificador del NIR principal al que está asociado el NIR actual.
	 * ej: SNR201900000XXXX
	 *
	 * @return String Identificador del NIR principal al que está asociado el NIR actual.
	 */
	public String getNirVinculado()
	{
		return is_nirVinculado;
	}

	/**
	 * Nombre del departamento origen del documento público a registrar.
	 * ej: Cundinamarca, Sucre
	 *
	 * @param as_s String Nombre del departamento origen del documento público a registrar.
	 */
	public void setNombreDepartamento(String as_s)
	{
		is_nombreDepartamento = as_s;
	}

	/**
	 * Nombre del departamento origen del documento público a registrar.
	 * ej: Cundinamarca, Sucre
	 *
	 * @return String Nombre del departamento origen del documento público a registrar.
	 */
	public String getNombreDepartamento()
	{
		return is_nombreDepartamento;
	}

	/**
	 * Nombre del municipio origen del documento público a registrar.
	 * ej: Alban, Mosquera, Bogotá
	 *
	 * @param as_s String Nombre del municipio origen del documento público a registrar.
	 */
	public void setNombreMunicipio(String as_s)
	{
		is_nombreMunicipio = as_s;
	}

	/**
	 * Nombre del municipio origen del documento público a registrar.
	 * ej: Alban, Mosquera, Bogotá
	 *
	 * @return String Nombre del municipio origen del documento público a registrar.
	 */
	public String getNombreMunicipio()
	{
		return is_nombreMunicipio;
	}

	/**
	 * Nombre de la oficina de registro de instrumentos públicos.
	 * ej: Fusagasugá,Bogota
	 *
	 * @param as_s String Nombre de la oficina de registro de instrumentos públicos.
	 */
	public void setNombreOrip(String as_s)
	{
		is_nombreOrip = as_s;
	}

	/**
	 * Nombre de la oficina de registro de instrumentos públicos.
	 * ej: Fusagasugá,Bogota
	 *
	 * @return String Nombre de la oficina de registro de instrumentos públicos.
	 */
	public String getNombreOrip()
	{
		return is_nombreOrip;
	}

	/**
	 * Nombre del País de origen del documento público a registrar.
	 * ej: COLOMBIA, PERÚ.
	 *
	 * @param as_s String Nombre del País de origen del documento público a registrar.
	 */
	public void setNombrePais(String as_s)
	{
		is_nombrePais = as_s;
	}

	/**
	 * Nombre del País de origen del documento público a registrar.
	 * ej: COLOMBIA, PERÚ.
	 *
	 * @return String Nombre del País de origen del documento público a registrar.
	 */
	public String getNombrePais()
	{
		return is_nombrePais;
	}

	/**
	 * Nombres del ciudadano, empresa, persona que realiza el trámite separados por coma (,).
	 * ej: Andrés Camargo,Fabián Gómez
	 *
	 * @param as_s String Nombres del ciudadano, empresa, persona que realiza el trámite separados por coma (,).
	 */
	public void setNombresIntervinientes(String as_s)
	{
		is_nombresIntervinientes = as_s;
	}

	/**
	 * Nombres del ciudadano, empresa, persona que realiza el trámite separados por coma (,).
	 * ej: Andrés Camargo,Fabián Gómez
	 *
	 * @return String Nombres del ciudadano, empresa, persona que realiza el trámite separados por coma (,).
	 */
	public String getNombresIntervinientes()
	{
		return is_nombresIntervinientes;
	}

	/**
	 * Número de copias a gesionar.
	 *
	 * @param al_l Argumento de tipo Long que modifica el valor del atributo.
	 */
	public void setNumeroCopias(Long al_l)
	{
		il_numeroCopias = al_l;
	}

	/**
	 * Metodo que retorna el valor del atributo.
	 * @return Retorna el valor del atributo.
	 */
	public Long getNumeroCopias()
	{
		return il_numeroCopias;
	}

	/**
	 * Número del documento público objeto del registro.
	 * ej: A1232
	 *
	 * @param as_s String Número del documento público objeto del registro.
	 */
	public void setNumeroDoc(String as_s)
	{
		is_numeroDoc = as_s;
	}

	/**
	 * Número del documento público objeto del registro.
	 * ej: A1232
	 *
	 * @return String Número del documento público objeto del registro.
	 */
	public String getNumeroDoc()
	{
		return is_numeroDoc;
	}

	/**
	 * Si el documento a enviar(checkin) especifica una página en especial
	 * ej: 12.
	 *
	 * @param as_s String numero de pagina especifica del documento
	 */
	public void setNumeroPaginas(String as_s)
	{
		is_numeroPaginas = as_s;
	}

	/**
	 * Si el documento a enviar(checkin) especifica una página en especial
	 * ej: 12.
	 *
	 * @return String numero de pagina especifica del documento
	 */
	public String getNumeroPaginas()
	{
		return is_numeroPaginas;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setOperador(String as_s)
	{
		is_operador = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getOperador()
	{
		return is_operador;
	}

	/**
	 * Corresponde al nombre del proceso del Core Bachué que está enviando los documentos generados.
	 * ej: Registro, correcciones, certificados, etc.
	 *
	 * @param as_s String Corresponde al nombre del proceso del Core Bachué que está enviando los documentos generados.
	 */
	public void setProceso(String as_s)
	{
		is_proceso = as_s;
	}

	/**
	 * Corresponde al nombre del proceso del Core Bachué que está enviando los documentos generados.
	 * ej: Registro, correcciones, certificados, etc.
	 *
	 * @return String Corresponde al nombre del proceso del Core Bachué que está enviando los documentos generados.
	 */
	public String getProceso()
	{
		return is_proceso;
	}

	/**
	 * Atributo que indica si se debe seleccionar el documento.
	 *
	 * @param ab_b Argumento de tipo boolean que modifica el valor del atributo.
	 */
	public void setSeleccione(boolean ab_b)
	{
		ib_seleccione = ab_b;
	}

	/**
	 * Metodo que retorna el valor del atributo.
	 * @return Retorna el valor del atributo.
	 */
	public boolean isSeleccione()
	{
		return ib_seleccione;
	}

	/**
	 * Código del acto de naturaleza jurídica a registrar.
	 * ej: 0125
	 *
	 * @param as_s String Código del acto de naturaleza jurídica a registrar.
	 */
	public void setTipoActo(String as_s)
	{
		is_tipoActo = as_s;
	}

	/**
	 * Código del acto de naturaleza jurídica a registrar.
	 * ej: 0125
	 *
	 * @return String Código del acto de naturaleza jurídica a registrar.
	 */
	public String getTipoActo()
	{
		return is_tipoActo;
	}

	/**
	 * Metodo encargado de asignar un tipo documental al atributo.
	 * @param atd_td Argumento de tipo <code>TipoDocumental</code> que contiene el tipo documental a asignar.
	 */
	public void setTipoDocumental(TipoDocumental atd_td)
	{
		itd_tipoDocumental = atd_td;
	}

	/**
	 * Metodo encargado de retornar un tipo documental.
	 * @return Tipo documental a retornar.
	 */
	public TipoDocumental getTipoDocumental()
	{
		return itd_tipoDocumental;
	}

	/**
	 * Indica nombre de la entidad generadora del documento público a registrar.
	 * ej: Juzgado, Alcaldía, Defensoría
	 *
	 * @param as_s String Indica nombre de la entidad generadora del documento público a registrar.
	 */
	public void setTipoOficina(String as_s)
	{
		is_tipoOficina = as_s;
	}

	/**
	 * Indica nombre de la entidad generadora del documento público a registrar.
	 * ej: Juzgado, Alcaldía, Defensoría
	 *
	 * @return String Indica nombre de la entidad generadora del documento público a registrar.
	 */
	public String getTipoOficina()
	{
		return is_tipoOficina;
	}

	/**
	 * Número de identificación del turno al que está asociado el documento.
	 * ej: 2019-425-6-XXXX
	 *
	 * @param as_s String Número de identificación del turno al que está asociado el documento.
	 */
	public void setTurno(String as_s)
	{
		is_turno = as_s;
	}

	/**
	 * Número de identificación del turno al que está asociado el documento.
	 * ej: 2019-425-6-XXXX
	 *
	 * @return String Número de identificación del turno al que está asociado el documento.
	 */
	public String getTurno()
	{
		return is_turno;
	}

	/**
	 * Identificador del TURNO asociado al NIR actual.
	 * ej: 2019-425-1-XXXX
	 *
	 * @param as_s String Identificador del TURNO asociado al NIR actual.
	 */
	public void setTurnoVinculado(String as_s)
	{
		is_turnoVinculado = as_s;
	}

	/**
	 * Identificador del TURNO asociado al NIR actual.
	 * ej: 2019-425-1-XXXX
	 *
	 * @return String Identificador del TURNO asociado al NIR actual.
	 */
	public String getTurnoVinculado()
	{
		return is_turnoVinculado;
	}

	/**
	 * URL del Visor retornada por cada documento.
	 * ej:http://168.62.168.96:16225/wcc/faces/wccdoc?dID=38412
	 *
	 * @param as_s String URL del Visor retornada por cada documento.
	 */
	public void setURL(String as_s)
	{
		is_URL = as_s;
	}

	/**
	 * URL del Visor retornada por cada documento.
	 * ej:http://168.62.168.96:16225/wcc/faces/wccdoc?dID=38412
	 *
	 * @return String URL del Visor retornada por cada documento.
	 */
	public String getURL()
	{
		return is_URL;
	}

	/**
	 * Es el identificador del archivo en Owcc.
	 * ej: CA1234
	 *
	 * @return String Es el identificador del archivo en Owcc.
	 */
	public String getiD()
	{
		return is_iD;
	}

	/**
	 * Número de folios o páginas de un documento.
	 * ej. 34
	 *
	 * @return el valor de string Número de folios o páginas de un documento.
	 */
	public String getnumeroFolios()
	{
		return is_numeroFolios;
	}

	/**
	 * Es el identificador del archivo en Owcc.
	 * ej: CA1234
	 *
	 * @param as_s String Es el identificador del archivo en Owcc.
	 */
	public void setiD(String as_s)
	{
		is_iD = as_s;
	}

	/**
	 * Modifica el valor de Número de folios o páginas de un documento.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad Folio
	 */
	public void setnumeroFolios(String as_s)
	{
		is_numeroFolios = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_rutaServidorFTP
	 */
	public String getRutaServidorFTP() {
		return is_rutaServidorFTP;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_rutaServidorFTP
	 */
	public void setRutaServidorFTP(String as_s) {
		is_rutaServidorFTP = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_comprobante
	 */
	public String getComprobante() {
		return is_comprobante;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_comprobante
	 */
	public void setComprobante(String as_s) {
		is_comprobante = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_numeroSIIF
	 */
	public String getNumeroSIIF() {
		return is_numeroSIIF;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_numeroSIIF
	 */
	public void setNumeroSIIF(String as_s) {
		is_numeroSIIF = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_version
	 */
	public String getVersion() {
		return is_version;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_version
	 */
	public void setVersion(String as_s) {
		is_version = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_numeroCuenta
	 */
	public String getNumeroCuenta() {
		return is_numeroCuenta;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_numeroCuenta
	 */
	public void setNumeroCuenta(String as_s) {
		is_numeroCuenta = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_codigoBanco
	 */
	public String getCodigoBanco() {
		return is_codigoBanco;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_codigoBanco
	 */
	public void setCodigoBanco(String as_s) {
		is_codigoBanco = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nombreBanco
	 */
	public String getNombreBanco() {
		return is_nombreBanco;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_nombreBanco
	 */
	public void setNombreBanco(String as_s) {
		is_nombreBanco = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad il_periodoCorte
	 */
	public Long getPeriodoCorte() {
		return il_periodoCorte;
	}

	/**
	 * @param al_l Modifica el valor de la propiedad il_periodoCorte
	 */
	public void setPeriodoCorte(Long al_l) {
		il_periodoCorte = al_l;
	}
}
