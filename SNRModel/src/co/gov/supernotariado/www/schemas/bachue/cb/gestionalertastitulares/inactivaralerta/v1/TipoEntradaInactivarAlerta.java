/**
 * TipoEntradaInactivarAlerta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaInactivarAlerta.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaInactivarAlerta implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5640771077778168895L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaInactivarAlerta.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
		        "tipoEntradaInactivarAlerta"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
		        "modulo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("identificadorAlerta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
		        "identificadorAlerta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
		        "tipoDocumento"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
		        ">tipoEntradaInactivarAlerta>tipoDocumento"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
		        "razonSocial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                               __equalsCalc        =
		null;
	
	/** Propiedad identificador alerta. */
	private java.lang.String                                                                                                               identificadorAlerta;
	
	/** Propiedad modulo. */
	private java.lang.String                                                                                                               modulo;
	
	/** Propiedad numero documento. */
	private java.lang.String                                                                                                               numeroDocumento;
	
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
	private co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoEntradaInactivarAlertaTipoDocumento tipoDocumento;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                        __hashCodeCalc      =
		false;

	/**
	 * Instancia un nuevo objeto tipo entrada inactivar alerta.
	 */
	public TipoEntradaInactivarAlerta()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada inactivar alerta.
	 *
	 * @param modulo de modulo
	 * @param identificadorAlerta de identificador alerta
	 * @param tipoDocumento de tipo documento
	 * @param numeroDocumento de numero documento
	 * @param primerNombre de primer nombre
	 * @param segundoNombre de segundo nombre
	 * @param primerApellido de primer apellido
	 * @param segundoApellido de segundo apellido
	 * @param razonSocial de razon social
	 */
	public TipoEntradaInactivarAlerta(
	    java.lang.String modulo, java.lang.String identificadorAlerta,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoEntradaInactivarAlertaTipoDocumento tipoDocumento,
	    java.lang.String numeroDocumento, java.lang.String primerNombre, java.lang.String segundoNombre,
	    java.lang.String primerApellido, java.lang.String segundoApellido, java.lang.String razonSocial
	)
	{
		this.modulo                  = modulo;
		this.identificadorAlerta     = identificadorAlerta;
		this.tipoDocumento           = tipoDocumento;
		this.numeroDocumento         = numeroDocumento;
		this.primerNombre            = primerNombre;
		this.segundoNombre           = segundoNombre;
		this.primerApellido          = primerApellido;
		this.segundoApellido         = segundoApellido;
		this.razonSocial             = razonSocial;
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
	 * Sets the identificadorAlerta value for this TipoEntradaInactivarAlerta.
	 *
	 * @param identificadorAlerta de identificador alerta
	 */
	public void setIdentificadorAlerta(java.lang.String identificadorAlerta)
	{
		this.identificadorAlerta = identificadorAlerta;
	}

	/**
	 * Gets the identificadorAlerta value for this TipoEntradaInactivarAlerta.
	 *
	 * @return identificadorAlerta
	 */
	public java.lang.String getIdentificadorAlerta()
	{
		return identificadorAlerta;
	}

	/**
	 * Sets the modulo value for this TipoEntradaInactivarAlerta.
	 *
	 * @param modulo de modulo
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the modulo value for this TipoEntradaInactivarAlerta.
	 *
	 * @return modulo
	 */
	public java.lang.String getModulo()
	{
		return modulo;
	}

	/**
	 * Sets the numeroDocumento value for this TipoEntradaInactivarAlerta.
	 *
	 * @param numeroDocumento de numero documento
	 */
	public void setNumeroDocumento(java.lang.String numeroDocumento)
	{
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Gets the numeroDocumento value for this TipoEntradaInactivarAlerta.
	 *
	 * @return numeroDocumento
	 */
	public java.lang.String getNumeroDocumento()
	{
		return numeroDocumento;
	}

	/**
	 * Sets the primerApellido value for this TipoEntradaInactivarAlerta.
	 *
	 * @param primerApellido de primer apellido
	 */
	public void setPrimerApellido(java.lang.String primerApellido)
	{
		this.primerApellido = primerApellido;
	}

	/**
	 * Gets the primerApellido value for this TipoEntradaInactivarAlerta.
	 *
	 * @return primerApellido
	 */
	public java.lang.String getPrimerApellido()
	{
		return primerApellido;
	}

	/**
	 * Sets the primerNombre value for this TipoEntradaInactivarAlerta.
	 *
	 * @param primerNombre de primer nombre
	 */
	public void setPrimerNombre(java.lang.String primerNombre)
	{
		this.primerNombre = primerNombre;
	}

	/**
	 * Gets the primerNombre value for this TipoEntradaInactivarAlerta.
	 *
	 * @return primerNombre
	 */
	public java.lang.String getPrimerNombre()
	{
		return primerNombre;
	}

	/**
	 * Sets the razonSocial value for this TipoEntradaInactivarAlerta.
	 *
	 * @param razonSocial de razon social
	 */
	public void setRazonSocial(java.lang.String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/**
	 * Gets the razonSocial value for this TipoEntradaInactivarAlerta.
	 *
	 * @return razonSocial
	 */
	public java.lang.String getRazonSocial()
	{
		return razonSocial;
	}

	/**
	 * Sets the segundoApellido value for this TipoEntradaInactivarAlerta.
	 *
	 * @param segundoApellido de segundo apellido
	 */
	public void setSegundoApellido(java.lang.String segundoApellido)
	{
		this.segundoApellido = segundoApellido;
	}

	/**
	 * Gets the segundoApellido value for this TipoEntradaInactivarAlerta.
	 *
	 * @return segundoApellido
	 */
	public java.lang.String getSegundoApellido()
	{
		return segundoApellido;
	}

	/**
	 * Sets the segundoNombre value for this TipoEntradaInactivarAlerta.
	 *
	 * @param segundoNombre de segundo nombre
	 */
	public void setSegundoNombre(java.lang.String segundoNombre)
	{
		this.segundoNombre = segundoNombre;
	}

	/**
	 * Gets the segundoNombre value for this TipoEntradaInactivarAlerta.
	 *
	 * @return segundoNombre
	 */
	public java.lang.String getSegundoNombre()
	{
		return segundoNombre;
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
	 * Sets the tipoDocumento value for this TipoEntradaInactivarAlerta.
	 *
	 * @param tipoDocumento de tipo documento
	 */
	public void setTipoDocumento(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoEntradaInactivarAlertaTipoDocumento tipoDocumento
	)
	{
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the tipoDocumento value for this TipoEntradaInactivarAlerta.
	 *
	 * @return tipoDocumento
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoEntradaInactivarAlertaTipoDocumento getTipoDocumento()
	{
		return tipoDocumento;
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

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaInactivarAlerta))
			return false;

		TipoEntradaInactivarAlerta other = (TipoEntradaInactivarAlerta)obj;

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
				&& (((this.identificadorAlerta == null) && (other.getIdentificadorAlerta() == null))
				|| ((this.identificadorAlerta != null)
				&& this.identificadorAlerta.equals(other.getIdentificadorAlerta())))
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
				|| ((this.razonSocial != null) && this.razonSocial.equals(other.getRazonSocial())));
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

		if(getIdentificadorAlerta() != null)
			_hashCode += getIdentificadorAlerta().hashCode();

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

		__hashCodeCalc = false;

		return _hashCode;
	}
}
