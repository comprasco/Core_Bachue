/**
 * TipoEntradaConsultarAlerta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarAlerta.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaConsultarAlerta implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1631910757017672329L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarAlerta.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "tipoEntradaConsultarAlerta"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "modulo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("departamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "departamento"
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "municipio"
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1", "orip"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "estado"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        ">tipoEntradaConsultarAlerta>estado"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroMatricula");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "numeroMatricula"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "tipoDocumento"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        ">tipoEntradaConsultarAlerta>tipoDocumento"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "numeroDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerNombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "primerNombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoNombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "segundoNombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerApellido");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "primerApellido"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoApellido");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "segundoApellido"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("razonSocial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "razonSocial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("cantidadRegistrosPagina");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "cantidadRegistrosPagina"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("ultimoIdAlerta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "ultimoIdAlerta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                               __equalsCalc            =
		null;
	
	/** Propiedad cantidad registros pagina. */
	private java.lang.String                                                                                                               cantidadRegistrosPagina;
	
	/** Propiedad departamento. */
	private java.lang.String                                                                                                               departamento;
	
	/** Propiedad estado. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlertaEstado        estado;
	
	/** Propiedad modulo. */
	private java.lang.String                                                                                                               modulo;
	
	/** Propiedad municipio. */
	private java.lang.String                                                                                                               municipio;
	
	/** Propiedad numero documento. */
	private java.lang.String                                                                                                               numeroDocumento;
	
	/** Propiedad numero matricula. */
	private java.lang.String                                                                                                               numeroMatricula;
	
	/** Propiedad orip. */
	private java.lang.String                                                                                                               orip;
	
	/** Propiedad primer apellido. */
	private java.lang.String                                                                                                               primerApellido;
	
	/** Propiedad primer nombre. */
	private java.lang.String                                                                                                               primerNombre;
	
	/** Propiedad razon social. */
	private java.lang.String                                                                                                               razonSocial;
	
	/** Propiedad segundo apellido. */
	private java.lang.String                                                                                                               segundoApellido;
	
	/** Propiedad segundo nombre. */
	private java.lang.String                                                                                                               segundoNombre;
	
	/** Propiedad tipo documento. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlertaTipoDocumento tipoDocumento;
	
	/** Propiedad ultimo id alerta. */
	private java.lang.String                                                                                                               ultimoIdAlerta;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                        __hashCodeCalc          =
		false;

	/**
	 * Instancia un nuevo objeto tipo entrada consultar alerta.
	 */
	public TipoEntradaConsultarAlerta()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consultar alerta.
	 *
	 * @param modulo de modulo
	 * @param departamento de departamento
	 * @param municipio de municipio
	 * @param orip de orip
	 * @param estado de estado
	 * @param numeroMatricula de numero matricula
	 * @param tipoDocumento de tipo documento
	 * @param numeroDocumento de numero documento
	 * @param primerNombre de primer nombre
	 * @param segundoNombre de segundo nombre
	 * @param primerApellido de primer apellido
	 * @param segundoApellido de segundo apellido
	 * @param razonSocial de razon social
	 * @param cantidadRegistrosPagina de cantidad registros pagina
	 * @param ultimoIdAlerta de ultimo id alerta
	 */
	public TipoEntradaConsultarAlerta(
	    java.lang.String modulo, java.lang.String departamento, java.lang.String municipio, java.lang.String orip,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlertaEstado estado,
	    java.lang.String numeroMatricula,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlertaTipoDocumento tipoDocumento,
	    java.lang.String numeroDocumento, java.lang.String primerNombre, java.lang.String segundoNombre,
	    java.lang.String primerApellido, java.lang.String segundoApellido, java.lang.String razonSocial,
	    java.lang.String cantidadRegistrosPagina, java.lang.String ultimoIdAlerta
	)
	{
		this.modulo                      = modulo;
		this.departamento                = departamento;
		this.municipio                   = municipio;
		this.orip                        = orip;
		this.estado                      = estado;
		this.numeroMatricula             = numeroMatricula;
		this.tipoDocumento               = tipoDocumento;
		this.numeroDocumento             = numeroDocumento;
		this.primerNombre                = primerNombre;
		this.segundoNombre               = segundoNombre;
		this.primerApellido              = primerApellido;
		this.segundoApellido             = segundoApellido;
		this.razonSocial                 = razonSocial;
		this.cantidadRegistrosPagina     = cantidadRegistrosPagina;
		this.ultimoIdAlerta              = ultimoIdAlerta;
	}

	/**
	 * Sets the cantidadRegistrosPagina value for this TipoEntradaConsultarAlerta.
	 *
	 * @param cantidadRegistrosPagina de cantidad registros pagina
	 */
	public void setCantidadRegistrosPagina(java.lang.String cantidadRegistrosPagina)
	{
		this.cantidadRegistrosPagina = cantidadRegistrosPagina;
	}

	/**
	 * Gets the cantidadRegistrosPagina value for this TipoEntradaConsultarAlerta.
	 *
	 * @return cantidadRegistrosPagina
	 */
	public java.lang.String getCantidadRegistrosPagina()
	{
		return cantidadRegistrosPagina;
	}

	/**
	 * Sets the departamento value for this TipoEntradaConsultarAlerta.
	 *
	 * @param departamento de departamento
	 */
	public void setDepartamento(java.lang.String departamento)
	{
		this.departamento = departamento;
	}

	/**
	 * Gets the departamento value for this TipoEntradaConsultarAlerta.
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
	 * Sets the estado value for this TipoEntradaConsultarAlerta.
	 *
	 * @param estado de estado
	 */
	public void setEstado(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlertaEstado estado
	)
	{
		this.estado = estado;
	}

	/**
	 * Gets the estado value for this TipoEntradaConsultarAlerta.
	 *
	 * @return estado
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlertaEstado getEstado()
	{
		return estado;
	}

	/**
	 * Sets the modulo value for this TipoEntradaConsultarAlerta.
	 *
	 * @param modulo de modulo
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the modulo value for this TipoEntradaConsultarAlerta.
	 *
	 * @return modulo
	 */
	public java.lang.String getModulo()
	{
		return modulo;
	}

	/**
	 * Sets the municipio value for this TipoEntradaConsultarAlerta.
	 *
	 * @param municipio de municipio
	 */
	public void setMunicipio(java.lang.String municipio)
	{
		this.municipio = municipio;
	}

	/**
	 * Gets the municipio value for this TipoEntradaConsultarAlerta.
	 *
	 * @return municipio
	 */
	public java.lang.String getMunicipio()
	{
		return municipio;
	}

	/**
	 * Sets the numeroDocumento value for this TipoEntradaConsultarAlerta.
	 *
	 * @param numeroDocumento de numero documento
	 */
	public void setNumeroDocumento(java.lang.String numeroDocumento)
	{
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Gets the numeroDocumento value for this TipoEntradaConsultarAlerta.
	 *
	 * @return numeroDocumento
	 */
	public java.lang.String getNumeroDocumento()
	{
		return numeroDocumento;
	}

	/**
	 * Sets the numeroMatricula value for this TipoEntradaConsultarAlerta.
	 *
	 * @param numeroMatricula de numero matricula
	 */
	public void setNumeroMatricula(java.lang.String numeroMatricula)
	{
		this.numeroMatricula = numeroMatricula;
	}

	/**
	 * Gets the numeroMatricula value for this TipoEntradaConsultarAlerta.
	 *
	 * @return numeroMatricula
	 */
	public java.lang.String getNumeroMatricula()
	{
		return numeroMatricula;
	}

	/**
	 * Sets the orip value for this TipoEntradaConsultarAlerta.
	 *
	 * @param orip de orip
	 */
	public void setOrip(java.lang.String orip)
	{
		this.orip = orip;
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
	 * Gets the orip value for this TipoEntradaConsultarAlerta.
	 *
	 * @return orip
	 */
	public java.lang.String getOrip()
	{
		return orip;
	}

	/**
	 * Sets the primerApellido value for this TipoEntradaConsultarAlerta.
	 *
	 * @param primerApellido de primer apellido
	 */
	public void setPrimerApellido(java.lang.String primerApellido)
	{
		this.primerApellido = primerApellido;
	}

	/**
	 * Gets the primerApellido value for this TipoEntradaConsultarAlerta.
	 *
	 * @return primerApellido
	 */
	public java.lang.String getPrimerApellido()
	{
		return primerApellido;
	}

	/**
	 * Sets the primerNombre value for this TipoEntradaConsultarAlerta.
	 *
	 * @param primerNombre de primer nombre
	 */
	public void setPrimerNombre(java.lang.String primerNombre)
	{
		this.primerNombre = primerNombre;
	}

	/**
	 * Gets the primerNombre value for this TipoEntradaConsultarAlerta.
	 *
	 * @return primerNombre
	 */
	public java.lang.String getPrimerNombre()
	{
		return primerNombre;
	}

	/**
	 * Sets the razonSocial value for this TipoEntradaConsultarAlerta.
	 *
	 * @param razonSocial de razon social
	 */
	public void setRazonSocial(java.lang.String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/**
	 * Gets the razonSocial value for this TipoEntradaConsultarAlerta.
	 *
	 * @return razonSocial
	 */
	public java.lang.String getRazonSocial()
	{
		return razonSocial;
	}

	/**
	 * Sets the segundoApellido value for this TipoEntradaConsultarAlerta.
	 *
	 * @param segundoApellido de segundo apellido
	 */
	public void setSegundoApellido(java.lang.String segundoApellido)
	{
		this.segundoApellido = segundoApellido;
	}

	/**
	 * Gets the segundoApellido value for this TipoEntradaConsultarAlerta.
	 *
	 * @return segundoApellido
	 */
	public java.lang.String getSegundoApellido()
	{
		return segundoApellido;
	}

	/**
	 * Sets the segundoNombre value for this TipoEntradaConsultarAlerta.
	 *
	 * @param segundoNombre de segundo nombre
	 */
	public void setSegundoNombre(java.lang.String segundoNombre)
	{
		this.segundoNombre = segundoNombre;
	}

	/**
	 * Gets the segundoNombre value for this TipoEntradaConsultarAlerta.
	 *
	 * @return segundoNombre
	 */
	public java.lang.String getSegundoNombre()
	{
		return segundoNombre;
	}

	/**
	 * Sets the tipoDocumento value for this TipoEntradaConsultarAlerta.
	 *
	 * @param tipoDocumento de tipo documento
	 */
	public void setTipoDocumento(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlertaTipoDocumento tipoDocumento
	)
	{
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the tipoDocumento value for this TipoEntradaConsultarAlerta.
	 *
	 * @return tipoDocumento
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlertaTipoDocumento getTipoDocumento()
	{
		return tipoDocumento;
	}

	/**
	 * Sets the ultimoIdAlerta value for this TipoEntradaConsultarAlerta.
	 *
	 * @param ultimoIdAlerta de ultimo id alerta
	 */
	public void setUltimoIdAlerta(java.lang.String ultimoIdAlerta)
	{
		this.ultimoIdAlerta = ultimoIdAlerta;
	}

	/**
	 * Gets the ultimoIdAlerta value for this TipoEntradaConsultarAlerta.
	 *
	 * @return ultimoIdAlerta
	 */
	public java.lang.String getUltimoIdAlerta()
	{
		return ultimoIdAlerta;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultarAlerta))
			return false;

		TipoEntradaConsultarAlerta other = (TipoEntradaConsultarAlerta)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.modulo == null) && (other.getModulo() == null))
				|| ((this.modulo != null) && this.modulo.equals(other.getModulo())))
				&& (((this.departamento == null) && (other.getDepartamento() == null))
				|| ((this.departamento != null) && this.departamento.equals(other.getDepartamento())))
				&& (((this.municipio == null) && (other.getMunicipio() == null))
				|| ((this.municipio != null) && this.municipio.equals(other.getMunicipio())))
				&& (((this.orip == null) && (other.getOrip() == null))
				|| ((this.orip != null) && this.orip.equals(other.getOrip())))
				&& (((this.estado == null) && (other.getEstado() == null))
				|| ((this.estado != null) && this.estado.equals(other.getEstado())))
				&& (((this.numeroMatricula == null) && (other.getNumeroMatricula() == null))
				|| ((this.numeroMatricula != null) && this.numeroMatricula.equals(other.getNumeroMatricula())))
				&& (((this.tipoDocumento == null) && (other.getTipoDocumento() == null))
				|| ((this.tipoDocumento != null) && this.tipoDocumento.equals(other.getTipoDocumento())))
				&& (((this.numeroDocumento == null) && (other.getNumeroDocumento() == null))
				|| ((this.numeroDocumento != null) && this.numeroDocumento.equals(other.getNumeroDocumento())))
				&& (((this.primerNombre == null) && (other.getPrimerNombre() == null))
				|| ((this.primerNombre != null) && this.primerNombre.equals(other.getPrimerNombre())))
				&& (((this.segundoNombre == null) && (other.getSegundoNombre() == null))
				|| ((this.segundoNombre != null) && this.segundoNombre.equals(other.getSegundoNombre())))
				&& (((this.primerApellido == null) && (other.getPrimerApellido() == null))
				|| ((this.primerApellido != null) && this.primerApellido.equals(other.getPrimerApellido())))
				&& (((this.segundoApellido == null) && (other.getSegundoApellido() == null))
				|| ((this.segundoApellido != null) && this.segundoApellido.equals(other.getSegundoApellido())))
				&& (((this.razonSocial == null) && (other.getRazonSocial() == null))
				|| ((this.razonSocial != null) && this.razonSocial.equals(other.getRazonSocial())))
				&& (((this.cantidadRegistrosPagina == null) && (other.getCantidadRegistrosPagina() == null))
				|| ((this.cantidadRegistrosPagina != null)
				&& this.cantidadRegistrosPagina.equals(other.getCantidadRegistrosPagina())))
				&& (((this.ultimoIdAlerta == null) && (other.getUltimoIdAlerta() == null))
				|| ((this.ultimoIdAlerta != null) && this.ultimoIdAlerta.equals(other.getUltimoIdAlerta())));
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

		if(getModulo() != null)
			_hashCode += getModulo().hashCode();

		if(getDepartamento() != null)
			_hashCode += getDepartamento().hashCode();

		if(getMunicipio() != null)
			_hashCode += getMunicipio().hashCode();

		if(getOrip() != null)
			_hashCode += getOrip().hashCode();

		if(getEstado() != null)
			_hashCode += getEstado().hashCode();

		if(getNumeroMatricula() != null)
			_hashCode += getNumeroMatricula().hashCode();

		if(getTipoDocumento() != null)
			_hashCode += getTipoDocumento().hashCode();

		if(getNumeroDocumento() != null)
			_hashCode += getNumeroDocumento().hashCode();

		if(getPrimerNombre() != null)
			_hashCode += getPrimerNombre().hashCode();

		if(getSegundoNombre() != null)
			_hashCode += getSegundoNombre().hashCode();

		if(getPrimerApellido() != null)
			_hashCode += getPrimerApellido().hashCode();

		if(getSegundoApellido() != null)
			_hashCode += getSegundoApellido().hashCode();

		if(getRazonSocial() != null)
			_hashCode += getRazonSocial().hashCode();

		if(getCantidadRegistrosPagina() != null)
			_hashCode += getCantidadRegistrosPagina().hashCode();

		if(getUltimoIdAlerta() != null)
			_hashCode += getUltimoIdAlerta().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
