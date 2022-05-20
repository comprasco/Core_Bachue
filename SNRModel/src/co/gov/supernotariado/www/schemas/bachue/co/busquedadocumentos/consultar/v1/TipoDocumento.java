/**
 * TipoDocumento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1;


/**
 * Clase que contiene todos las propiedades TipoDocumento.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoDocumento implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3489177185493407328L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoDocumento.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "tipoDocumento"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("DID");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "dID"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("docName");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "docName"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumental");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "tipoDocumental"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("orip");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "orip"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nir");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "nir"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codOrip");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "codOrip"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("turno");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "turno"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("documentoRegistrado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1",
		        "documentoRegistrado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "numeroDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "fechaDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("entidadOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "entidadOrigen"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("pais");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "pais"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("departamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "departamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("municipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "municipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matricula");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "matricula"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("anotacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "anotacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroPagina");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "numeroPagina"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroFolios");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "numeroFolios"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreInterviniente");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1",
		        "nombreInterviniente"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("identificacionInterviniente");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1",
		        "identificacionInterviniente"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaPublicacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1",
		        "fechaPublicacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaVigencia");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "fechaVigencia"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoOficina");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "tipoOficina"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("actoNaturalezaJuridica");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1",
		        "actoNaturalezaJuridica"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("proceso");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "proceso"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nirVinculado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "nirVinculado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("turnoVinculado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "turnoVinculado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("urlVisor");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "urlVisor"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc                = null;
	
	/** Propiedad acto naturaleza juridica. */
	private java.lang.String actoNaturalezaJuridica;
	
	/** Propiedad anotacion. */
	private java.lang.String anotacion;
	
	/** Propiedad cod orip. */
	private java.lang.String codOrip;
	
	/** Propiedad d ID. */
	private java.lang.String dID;
	
	/** Propiedad departamento. */
	private java.lang.String departamento;
	
	/** Propiedad doc name. */
	private java.lang.String docName;
	
	/** Propiedad documento registrado. */
	private java.lang.String documentoRegistrado;
	
	/** Propiedad entidad origen. */
	private java.lang.String entidadOrigen;
	
	/** Propiedad fecha documento. */
	private java.lang.String fechaDocumento;
	
	/** Propiedad fecha publicacion. */
	private java.lang.String fechaPublicacion;
	
	/** Propiedad fecha vigencia. */
	private java.lang.String fechaVigencia;
	
	/** Propiedad identificacion interviniente. */
	private java.lang.String identificacionInterviniente;
	
	/** Propiedad matricula. */
	private java.lang.String matricula;
	
	/** Propiedad municipio. */
	private java.lang.String municipio;
	
	/** Propiedad nir. */
	private java.lang.String nir;
	
	/** Propiedad nir vinculado. */
	private java.lang.String nirVinculado;
	
	/** Propiedad nombre interviniente. */
	private java.lang.String nombreInterviniente;
	
	/** Propiedad numero documento. */
	private java.lang.String numeroDocumento;
	
	/** Propiedad numero folios. */
	private java.lang.String numeroFolios;
	
	/** Propiedad numero pagina. */
	private java.lang.String numeroPagina;
	
	/** Propiedad orip. */
	private java.lang.String orip;
	
	/** Propiedad pais. */
	private java.lang.String pais;
	
	/** Propiedad proceso. */
	private java.lang.String proceso;
	
	/** Propiedad tipo documental. */
	private java.lang.String tipoDocumental;
	
	/** Propiedad tipo oficina. */
	private java.lang.String tipoOficina;
	
	/** Propiedad turno. */
	private java.lang.String turno;
	
	/** Propiedad turno vinculado. */
	private java.lang.String turnoVinculado;
	
	/** Propiedad url visor. */
	private java.lang.String urlVisor;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc              = false;

	/**
	 * Instancia un nuevo objeto tipo documento.
	 */
	public TipoDocumento()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo documento.
	 *
	 * @param dID de d ID
	 * @param docName de doc name
	 * @param tipoDocumental de tipo documental
	 * @param orip de orip
	 * @param nir de nir
	 * @param codOrip de cod orip
	 * @param turno de turno
	 * @param documentoRegistrado de documento registrado
	 * @param numeroDocumento de numero documento
	 * @param fechaDocumento de fecha documento
	 * @param entidadOrigen de entidad origen
	 * @param pais de pais
	 * @param departamento de departamento
	 * @param municipio de municipio
	 * @param matricula de matricula
	 * @param anotacion de anotacion
	 * @param numeroPagina de numero pagina
	 * @param numeroFolios de numero folios
	 * @param nombreInterviniente de nombre interviniente
	 * @param identificacionInterviniente de identificacion interviniente
	 * @param fechaPublicacion de fecha publicacion
	 * @param fechaVigencia de fecha vigencia
	 * @param tipoOficina de tipo oficina
	 * @param actoNaturalezaJuridica de acto naturaleza juridica
	 * @param proceso de proceso
	 * @param nirVinculado de nir vinculado
	 * @param turnoVinculado de turno vinculado
	 * @param urlVisor de url visor
	 */
	public TipoDocumento(
	    java.lang.String dID, java.lang.String docName, java.lang.String tipoDocumental, java.lang.String orip,
	    java.lang.String nir, java.lang.String codOrip, java.lang.String turno, java.lang.String documentoRegistrado,
	    java.lang.String numeroDocumento, java.lang.String fechaDocumento, java.lang.String entidadOrigen,
	    java.lang.String pais, java.lang.String departamento, java.lang.String municipio, java.lang.String matricula,
	    java.lang.String anotacion, java.lang.String numeroPagina, java.lang.String numeroFolios,
	    java.lang.String nombreInterviniente, java.lang.String identificacionInterviniente,
	    java.lang.String fechaPublicacion, java.lang.String fechaVigencia, java.lang.String tipoOficina,
	    java.lang.String actoNaturalezaJuridica, java.lang.String proceso, java.lang.String nirVinculado,
	    java.lang.String turnoVinculado, java.lang.String urlVisor
	)
	{
		this.dID                             = dID;
		this.docName                         = docName;
		this.tipoDocumental                  = tipoDocumental;
		this.orip                            = orip;
		this.nir                             = nir;
		this.codOrip                         = codOrip;
		this.turno                           = turno;
		this.documentoRegistrado             = documentoRegistrado;
		this.numeroDocumento                 = numeroDocumento;
		this.fechaDocumento                  = fechaDocumento;
		this.entidadOrigen                   = entidadOrigen;
		this.pais                            = pais;
		this.departamento                    = departamento;
		this.municipio                       = municipio;
		this.matricula                       = matricula;
		this.anotacion                       = anotacion;
		this.numeroPagina                    = numeroPagina;
		this.numeroFolios                    = numeroFolios;
		this.nombreInterviniente             = nombreInterviniente;
		this.identificacionInterviniente     = identificacionInterviniente;
		this.fechaPublicacion                = fechaPublicacion;
		this.fechaVigencia                   = fechaVigencia;
		this.tipoOficina                     = tipoOficina;
		this.actoNaturalezaJuridica          = actoNaturalezaJuridica;
		this.proceso                         = proceso;
		this.nirVinculado                    = nirVinculado;
		this.turnoVinculado                  = turnoVinculado;
		this.urlVisor                        = urlVisor;
	}

	/**
	 * Sets the actoNaturalezaJuridica value for this TipoDocumento.
	 *
	 * @param actoNaturalezaJuridica de acto naturaleza juridica
	 */
	public void setActoNaturalezaJuridica(java.lang.String actoNaturalezaJuridica)
	{
		this.actoNaturalezaJuridica = actoNaturalezaJuridica;
	}

	/**
	 * Gets the actoNaturalezaJuridica value for this TipoDocumento.
	 *
	 * @return actoNaturalezaJuridica
	 */
	public java.lang.String getActoNaturalezaJuridica()
	{
		return actoNaturalezaJuridica;
	}

	/**
	 * Sets the anotacion value for this TipoDocumento.
	 *
	 * @param anotacion de anotacion
	 */
	public void setAnotacion(java.lang.String anotacion)
	{
		this.anotacion = anotacion;
	}

	/**
	 * Gets the anotacion value for this TipoDocumento.
	 *
	 * @return anotacion
	 */
	public java.lang.String getAnotacion()
	{
		return anotacion;
	}

	/**
	 * Sets the codOrip value for this TipoDocumento.
	 *
	 * @param codOrip de cod orip
	 */
	public void setCodOrip(java.lang.String codOrip)
	{
		this.codOrip = codOrip;
	}

	/**
	 * Gets the codOrip value for this TipoDocumento.
	 *
	 * @return codOrip
	 */
	public java.lang.String getCodOrip()
	{
		return codOrip;
	}

	/**
	 * Sets the dID value for this TipoDocumento.
	 *
	 * @param dID de d ID
	 */
	public void setDID(java.lang.String dID)
	{
		this.dID = dID;
	}

	/**
	 * Gets the dID value for this TipoDocumento.
	 *
	 * @return dID
	 */
	public java.lang.String getDID()
	{
		return dID;
	}

	/**
	 * Sets the departamento value for this TipoDocumento.
	 *
	 * @param departamento de departamento
	 */
	public void setDepartamento(java.lang.String departamento)
	{
		this.departamento = departamento;
	}

	/**
	 * Gets the departamento value for this TipoDocumento.
	 *
	 * @return departamento
	 */
	public java.lang.String getDepartamento()
	{
		return departamento;
	}

	/**
	 * Get Custom Deserializer.
	 *
	 * @param mechType de mech type
	 * @param _javaType de java type
	 * @param _xmlType de xml type
	 * @return el valor de deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Sets the docName value for this TipoDocumento.
	 *
	 * @param docName de doc name
	 */
	public void setDocName(java.lang.String docName)
	{
		this.docName = docName;
	}

	/**
	 * Gets the docName value for this TipoDocumento.
	 *
	 * @return docName
	 */
	public java.lang.String getDocName()
	{
		return docName;
	}

	/**
	 * Sets the documentoRegistrado value for this TipoDocumento.
	 *
	 * @param documentoRegistrado de documento registrado
	 */
	public void setDocumentoRegistrado(java.lang.String documentoRegistrado)
	{
		this.documentoRegistrado = documentoRegistrado;
	}

	/**
	 * Gets the documentoRegistrado value for this TipoDocumento.
	 *
	 * @return documentoRegistrado
	 */
	public java.lang.String getDocumentoRegistrado()
	{
		return documentoRegistrado;
	}

	/**
	 * Sets the entidadOrigen value for this TipoDocumento.
	 *
	 * @param entidadOrigen de entidad origen
	 */
	public void setEntidadOrigen(java.lang.String entidadOrigen)
	{
		this.entidadOrigen = entidadOrigen;
	}

	/**
	 * Gets the entidadOrigen value for this TipoDocumento.
	 *
	 * @return entidadOrigen
	 */
	public java.lang.String getEntidadOrigen()
	{
		return entidadOrigen;
	}

	/**
	 * Sets the fechaDocumento value for this TipoDocumento.
	 *
	 * @param fechaDocumento de fecha documento
	 */
	public void setFechaDocumento(java.lang.String fechaDocumento)
	{
		this.fechaDocumento = fechaDocumento;
	}

	/**
	 * Gets the fechaDocumento value for this TipoDocumento.
	 *
	 * @return fechaDocumento
	 */
	public java.lang.String getFechaDocumento()
	{
		return fechaDocumento;
	}

	/**
	 * Sets the fechaPublicacion value for this TipoDocumento.
	 *
	 * @param fechaPublicacion de fecha publicacion
	 */
	public void setFechaPublicacion(java.lang.String fechaPublicacion)
	{
		this.fechaPublicacion = fechaPublicacion;
	}

	/**
	 * Gets the fechaPublicacion value for this TipoDocumento.
	 *
	 * @return fechaPublicacion
	 */
	public java.lang.String getFechaPublicacion()
	{
		return fechaPublicacion;
	}

	/**
	 * Sets the fechaVigencia value for this TipoDocumento.
	 *
	 * @param fechaVigencia de fecha vigencia
	 */
	public void setFechaVigencia(java.lang.String fechaVigencia)
	{
		this.fechaVigencia = fechaVigencia;
	}

	/**
	 * Gets the fechaVigencia value for this TipoDocumento.
	 *
	 * @return fechaVigencia
	 */
	public java.lang.String getFechaVigencia()
	{
		return fechaVigencia;
	}

	/**
	 * Sets the identificacionInterviniente value for this TipoDocumento.
	 *
	 * @param identificacionInterviniente de identificacion interviniente
	 */
	public void setIdentificacionInterviniente(java.lang.String identificacionInterviniente)
	{
		this.identificacionInterviniente = identificacionInterviniente;
	}

	/**
	 * Gets the identificacionInterviniente value for this TipoDocumento.
	 *
	 * @return identificacionInterviniente
	 */
	public java.lang.String getIdentificacionInterviniente()
	{
		return identificacionInterviniente;
	}

	/**
	 * Sets the matricula value for this TipoDocumento.
	 *
	 * @param matricula de matricula
	 */
	public void setMatricula(java.lang.String matricula)
	{
		this.matricula = matricula;
	}

	/**
	 * Gets the matricula value for this TipoDocumento.
	 *
	 * @return matricula
	 */
	public java.lang.String getMatricula()
	{
		return matricula;
	}

	/**
	 * Sets the municipio value for this TipoDocumento.
	 *
	 * @param municipio de municipio
	 */
	public void setMunicipio(java.lang.String municipio)
	{
		this.municipio = municipio;
	}

	/**
	 * Gets the municipio value for this TipoDocumento.
	 *
	 * @return municipio
	 */
	public java.lang.String getMunicipio()
	{
		return municipio;
	}

	/**
	 * Get Custom Serializer.
	 *
	 * @param mechType de mech type
	 * @param _javaType de java type
	 * @param _xmlType de xml type
	 * @return el valor de serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Return type metadata object.
	 *
	 * @return el valor de type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Sets the nir value for this TipoDocumento.
	 *
	 * @param nir de nir
	 */
	public void setNir(java.lang.String nir)
	{
		this.nir = nir;
	}

	/**
	 * Gets the nir value for this TipoDocumento.
	 *
	 * @return nir
	 */
	public java.lang.String getNir()
	{
		return nir;
	}

	/**
	 * Sets the nirVinculado value for this TipoDocumento.
	 *
	 * @param nirVinculado de nir vinculado
	 */
	public void setNirVinculado(java.lang.String nirVinculado)
	{
		this.nirVinculado = nirVinculado;
	}

	/**
	 * Gets the nirVinculado value for this TipoDocumento.
	 *
	 * @return nirVinculado
	 */
	public java.lang.String getNirVinculado()
	{
		return nirVinculado;
	}

	/**
	 * Sets the nombreInterviniente value for this TipoDocumento.
	 *
	 * @param nombreInterviniente de nombre interviniente
	 */
	public void setNombreInterviniente(java.lang.String nombreInterviniente)
	{
		this.nombreInterviniente = nombreInterviniente;
	}

	/**
	 * Gets the nombreInterviniente value for this TipoDocumento.
	 *
	 * @return nombreInterviniente
	 */
	public java.lang.String getNombreInterviniente()
	{
		return nombreInterviniente;
	}

	/**
	 * Sets the numeroDocumento value for this TipoDocumento.
	 *
	 * @param numeroDocumento de numero documento
	 */
	public void setNumeroDocumento(java.lang.String numeroDocumento)
	{
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Gets the numeroDocumento value for this TipoDocumento.
	 *
	 * @return numeroDocumento
	 */
	public java.lang.String getNumeroDocumento()
	{
		return numeroDocumento;
	}

	/**
	 * Sets the numeroFolios value for this TipoDocumento.
	 *
	 * @param numeroFolios de numero folios
	 */
	public void setNumeroFolios(java.lang.String numeroFolios)
	{
		this.numeroFolios = numeroFolios;
	}

	/**
	 * Gets the numeroFolios value for this TipoDocumento.
	 *
	 * @return numeroFolios
	 */
	public java.lang.String getNumeroFolios()
	{
		return numeroFolios;
	}

	/**
	 * Sets the numeroPagina value for this TipoDocumento.
	 *
	 * @param numeroPagina de numero pagina
	 */
	public void setNumeroPagina(java.lang.String numeroPagina)
	{
		this.numeroPagina = numeroPagina;
	}

	/**
	 * Gets the numeroPagina value for this TipoDocumento.
	 *
	 * @return numeroPagina
	 */
	public java.lang.String getNumeroPagina()
	{
		return numeroPagina;
	}

	/**
	 * Sets the orip value for this TipoDocumento.
	 *
	 * @param orip de orip
	 */
	public void setOrip(java.lang.String orip)
	{
		this.orip = orip;
	}

	/**
	 * Gets the orip value for this TipoDocumento.
	 *
	 * @return orip
	 */
	public java.lang.String getOrip()
	{
		return orip;
	}

	/**
	 * Sets the pais value for this TipoDocumento.
	 *
	 * @param pais de pais
	 */
	public void setPais(java.lang.String pais)
	{
		this.pais = pais;
	}

	/**
	 * Gets the pais value for this TipoDocumento.
	 *
	 * @return pais
	 */
	public java.lang.String getPais()
	{
		return pais;
	}

	/**
	 * Sets the proceso value for this TipoDocumento.
	 *
	 * @param proceso de proceso
	 */
	public void setProceso(java.lang.String proceso)
	{
		this.proceso = proceso;
	}

	/**
	 * Gets the proceso value for this TipoDocumento.
	 *
	 * @return proceso
	 */
	public java.lang.String getProceso()
	{
		return proceso;
	}

	/**
	 * Sets the tipoDocumental value for this TipoDocumento.
	 *
	 * @param tipoDocumental de tipo documental
	 */
	public void setTipoDocumental(java.lang.String tipoDocumental)
	{
		this.tipoDocumental = tipoDocumental;
	}

	/**
	 * Gets the tipoDocumental value for this TipoDocumento.
	 *
	 * @return tipoDocumental
	 */
	public java.lang.String getTipoDocumental()
	{
		return tipoDocumental;
	}

	/**
	 * Sets the tipoOficina value for this TipoDocumento.
	 *
	 * @param tipoOficina de tipo oficina
	 */
	public void setTipoOficina(java.lang.String tipoOficina)
	{
		this.tipoOficina = tipoOficina;
	}

	/**
	 * Gets the tipoOficina value for this TipoDocumento.
	 *
	 * @return tipoOficina
	 */
	public java.lang.String getTipoOficina()
	{
		return tipoOficina;
	}

	/**
	 * Sets the turno value for this TipoDocumento.
	 *
	 * @param turno de turno
	 */
	public void setTurno(java.lang.String turno)
	{
		this.turno = turno;
	}

	/**
	 * Gets the turno value for this TipoDocumento.
	 *
	 * @return turno
	 */
	public java.lang.String getTurno()
	{
		return turno;
	}

	/**
	 * Sets the turnoVinculado value for this TipoDocumento.
	 *
	 * @param turnoVinculado de turno vinculado
	 */
	public void setTurnoVinculado(java.lang.String turnoVinculado)
	{
		this.turnoVinculado = turnoVinculado;
	}

	/**
	 * Gets the turnoVinculado value for this TipoDocumento.
	 *
	 * @return turnoVinculado
	 */
	public java.lang.String getTurnoVinculado()
	{
		return turnoVinculado;
	}

	/**
	 * Sets the urlVisor value for this TipoDocumento.
	 *
	 * @param urlVisor de url visor
	 */
	public void setUrlVisor(java.lang.String urlVisor)
	{
		this.urlVisor = urlVisor;
	}

	/**
	 * Gets the urlVisor value for this TipoDocumento.
	 *
	 * @return urlVisor
	 */
	public java.lang.String getUrlVisor()
	{
		return urlVisor;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoDocumento))
			return false;

		TipoDocumento other = (TipoDocumento)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.dID == null) && (other.getDID() == null))
				|| ((this.dID != null) && this.dID.equals(other.getDID())))
				&& (((this.docName == null) && (other.getDocName() == null))
				|| ((this.docName != null) && this.docName.equals(other.getDocName())))
				&& (((this.tipoDocumental == null) && (other.getTipoDocumental() == null))
				|| ((this.tipoDocumental != null) && this.tipoDocumental.equals(other.getTipoDocumental())))
				&& (((this.orip == null) && (other.getOrip() == null))
				|| ((this.orip != null) && this.orip.equals(other.getOrip())))
				&& (((this.nir == null) && (other.getNir() == null))
				|| ((this.nir != null) && this.nir.equals(other.getNir())))
				&& (((this.codOrip == null) && (other.getCodOrip() == null))
				|| ((this.codOrip != null) && this.codOrip.equals(other.getCodOrip())))
				&& (((this.turno == null) && (other.getTurno() == null))
				|| ((this.turno != null) && this.turno.equals(other.getTurno())))
				&& (((this.documentoRegistrado == null) && (other.getDocumentoRegistrado() == null))
				|| ((this.documentoRegistrado != null)
				&& this.documentoRegistrado.equals(other.getDocumentoRegistrado())))
				&& (((this.numeroDocumento == null) && (other.getNumeroDocumento() == null))
				|| ((this.numeroDocumento != null) && this.numeroDocumento.equals(other.getNumeroDocumento())))
				&& (((this.fechaDocumento == null) && (other.getFechaDocumento() == null))
				|| ((this.fechaDocumento != null) && this.fechaDocumento.equals(other.getFechaDocumento())))
				&& (((this.entidadOrigen == null) && (other.getEntidadOrigen() == null))
				|| ((this.entidadOrigen != null) && this.entidadOrigen.equals(other.getEntidadOrigen())))
				&& (((this.pais == null) && (other.getPais() == null))
				|| ((this.pais != null) && this.pais.equals(other.getPais())))
				&& (((this.departamento == null) && (other.getDepartamento() == null))
				|| ((this.departamento != null) && this.departamento.equals(other.getDepartamento())))
				&& (((this.municipio == null) && (other.getMunicipio() == null))
				|| ((this.municipio != null) && this.municipio.equals(other.getMunicipio())))
				&& (((this.matricula == null) && (other.getMatricula() == null))
				|| ((this.matricula != null) && this.matricula.equals(other.getMatricula())))
				&& (((this.anotacion == null) && (other.getAnotacion() == null))
				|| ((this.anotacion != null) && this.anotacion.equals(other.getAnotacion())))
				&& (((this.numeroPagina == null) && (other.getNumeroPagina() == null))
				|| ((this.numeroPagina != null) && this.numeroPagina.equals(other.getNumeroPagina())))
				&& (((this.numeroFolios == null) && (other.getNumeroFolios() == null))
				|| ((this.numeroFolios != null) && this.numeroFolios.equals(other.getNumeroFolios())))
				&& (((this.nombreInterviniente == null) && (other.getNombreInterviniente() == null))
				|| ((this.nombreInterviniente != null)
				&& this.nombreInterviniente.equals(other.getNombreInterviniente())))
				&& (((this.identificacionInterviniente == null) && (other.getIdentificacionInterviniente() == null))
				|| ((this.identificacionInterviniente != null)
				&& this.identificacionInterviniente.equals(other.getIdentificacionInterviniente())))
				&& (((this.fechaPublicacion == null) && (other.getFechaPublicacion() == null))
				|| ((this.fechaPublicacion != null) && this.fechaPublicacion.equals(other.getFechaPublicacion())))
				&& (((this.fechaVigencia == null) && (other.getFechaVigencia() == null))
				|| ((this.fechaVigencia != null) && this.fechaVigencia.equals(other.getFechaVigencia())))
				&& (((this.tipoOficina == null) && (other.getTipoOficina() == null))
				|| ((this.tipoOficina != null) && this.tipoOficina.equals(other.getTipoOficina())))
				&& (((this.actoNaturalezaJuridica == null) && (other.getActoNaturalezaJuridica() == null))
				|| ((this.actoNaturalezaJuridica != null)
				&& this.actoNaturalezaJuridica.equals(other.getActoNaturalezaJuridica())))
				&& (((this.proceso == null) && (other.getProceso() == null))
				|| ((this.proceso != null) && this.proceso.equals(other.getProceso())))
				&& (((this.nirVinculado == null) && (other.getNirVinculado() == null))
				|| ((this.nirVinculado != null) && this.nirVinculado.equals(other.getNirVinculado())))
				&& (((this.turnoVinculado == null) && (other.getTurnoVinculado() == null))
				|| ((this.turnoVinculado != null) && this.turnoVinculado.equals(other.getTurnoVinculado())))
				&& (((this.urlVisor == null) && (other.getUrlVisor() == null))
				|| ((this.urlVisor != null) && this.urlVisor.equals(other.getUrlVisor())));
		__equalsCalc     = null;

		return _equals;
	}

	/** {@inheritdoc} */
	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getDID() != null)
			_hashCode += getDID().hashCode();

		if(getDocName() != null)
			_hashCode += getDocName().hashCode();

		if(getTipoDocumental() != null)
			_hashCode += getTipoDocumental().hashCode();

		if(getOrip() != null)
			_hashCode += getOrip().hashCode();

		if(getNir() != null)
			_hashCode += getNir().hashCode();

		if(getCodOrip() != null)
			_hashCode += getCodOrip().hashCode();

		if(getTurno() != null)
			_hashCode += getTurno().hashCode();

		if(getDocumentoRegistrado() != null)
			_hashCode += getDocumentoRegistrado().hashCode();

		if(getNumeroDocumento() != null)
			_hashCode += getNumeroDocumento().hashCode();

		if(getFechaDocumento() != null)
			_hashCode += getFechaDocumento().hashCode();

		if(getEntidadOrigen() != null)
			_hashCode += getEntidadOrigen().hashCode();

		if(getPais() != null)
			_hashCode += getPais().hashCode();

		if(getDepartamento() != null)
			_hashCode += getDepartamento().hashCode();

		if(getMunicipio() != null)
			_hashCode += getMunicipio().hashCode();

		if(getMatricula() != null)
			_hashCode += getMatricula().hashCode();

		if(getAnotacion() != null)
			_hashCode += getAnotacion().hashCode();

		if(getNumeroPagina() != null)
			_hashCode += getNumeroPagina().hashCode();

		if(getNumeroFolios() != null)
			_hashCode += getNumeroFolios().hashCode();

		if(getNombreInterviniente() != null)
			_hashCode += getNombreInterviniente().hashCode();

		if(getIdentificacionInterviniente() != null)
			_hashCode += getIdentificacionInterviniente().hashCode();

		if(getFechaPublicacion() != null)
			_hashCode += getFechaPublicacion().hashCode();

		if(getFechaVigencia() != null)
			_hashCode += getFechaVigencia().hashCode();

		if(getTipoOficina() != null)
			_hashCode += getTipoOficina().hashCode();

		if(getActoNaturalezaJuridica() != null)
			_hashCode += getActoNaturalezaJuridica().hashCode();

		if(getProceso() != null)
			_hashCode += getProceso().hashCode();

		if(getNirVinculado() != null)
			_hashCode += getNirVinculado().hashCode();

		if(getTurnoVinculado() != null)
			_hashCode += getTurnoVinculado().hashCode();

		if(getUrlVisor() != null)
			_hashCode += getUrlVisor().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
