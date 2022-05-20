/**
 * PropietarioDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1;



/**
 * El esquema define los
 *                         datos de salida para la operacion Consulta ínidce de propietarios
 *                         datos de propietario.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
public class PropietarioDTO implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8042189658276025545L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    PropietarioDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1", "propietarioDTO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numNIT");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1", "numNIT"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numDocumentoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
		        "numDocumentoPersona"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomTipoDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
		        "nomTipoDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomPropietario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1", "nomPropietario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad nom propietario. */
	/* Corresponde a los nombres y apellidos del
	 *                                 propietario */
	private java.lang.String nomPropietario;

	/** Propiedad nom tipo documento. */
	/* Corresponde al nonmbre del tipo de documento
	 *                                 del
	 *                                 propietario */
	private java.lang.String nomTipoDocumento;

	/** Propiedad num documento persona. */
	/* Corresponde al número de identificación del
	 *                                 propietario persona natural */
	private java.lang.String numDocumentoPersona;

	/** Propiedad num NIT. */
	/* Corresponde al número de identificación del
	 *                                 propietario persona jurídica */
	private java.lang.String numNIT;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto propietario DTO.
	 */
	public PropietarioDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto propietario DTO.
	 *
	 * @param numNIT de num NIT
	 * @param numDocumentoPersona de num documento persona
	 * @param nomTipoDocumento de nom tipo documento
	 * @param nomPropietario de nom propietario
	 */
	public PropietarioDTO(
	    java.lang.String numNIT, java.lang.String numDocumentoPersona, java.lang.String nomTipoDocumento,
	    java.lang.String nomPropietario
	)
	{
		this.numNIT                  = numNIT;
		this.numDocumentoPersona     = numDocumentoPersona;
		this.nomTipoDocumento        = nomTipoDocumento;
		this.nomPropietario          = nomPropietario;
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
	 * Sets the nomPropietario value for this PropietarioDTO.
	 *
	 * @param nomPropietario   * Corresponde a los nombres y apellidos del
	 *                                 propietario
	 */
	public void setNomPropietario(java.lang.String nomPropietario)
	{
		this.nomPropietario = nomPropietario;
	}

	/**
	 * Gets the nomPropietario value for this PropietarioDTO.
	 *
	 * @return nomPropietario   * Corresponde a los nombres y apellidos del
	 *                                 propietario
	 */
	public java.lang.String getNomPropietario()
	{
		return nomPropietario;
	}

	/**
	 * Sets the nomTipoDocumento value for this PropietarioDTO.
	 *
	 * @param nomTipoDocumento   * Corresponde al nonmbre del tipo de documento
	 *                                 del
	 *                                 propietario
	 */
	public void setNomTipoDocumento(java.lang.String nomTipoDocumento)
	{
		this.nomTipoDocumento = nomTipoDocumento;
	}

	/**
	 * Gets the nomTipoDocumento value for this PropietarioDTO.
	 *
	 * @return nomTipoDocumento   * Corresponde al nonmbre del tipo de documento
	 *                                 del
	 *                                 propietario
	 */
	public java.lang.String getNomTipoDocumento()
	{
		return nomTipoDocumento;
	}

	/**
	 * Sets the numDocumentoPersona value for this PropietarioDTO.
	 *
	 * @param numDocumentoPersona   * Corresponde al número de identificación del
	 *                                 propietario persona natural
	 */
	public void setNumDocumentoPersona(java.lang.String numDocumentoPersona)
	{
		this.numDocumentoPersona = numDocumentoPersona;
	}

	/**
	 * Gets the numDocumentoPersona value for this PropietarioDTO.
	 *
	 * @return numDocumentoPersona   * Corresponde al número de identificación del
	 *                                 propietario persona natural
	 */
	public java.lang.String getNumDocumentoPersona()
	{
		return numDocumentoPersona;
	}

	/**
	 * Sets the numNIT value for this PropietarioDTO.
	 *
	 * @param numNIT   * Corresponde al número de identificación del
	 *                                 propietario persona jurídica
	 */
	public void setNumNIT(java.lang.String numNIT)
	{
		this.numNIT = numNIT;
	}

	/**
	 * Gets the numNIT value for this PropietarioDTO.
	 *
	 * @return numNIT   * Corresponde al número de identificación del
	 *                                 propietario persona jurídica
	 */
	public java.lang.String getNumNIT()
	{
		return numNIT;
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

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof PropietarioDTO))
			return false;

		PropietarioDTO other = (PropietarioDTO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numNIT == null) && (other.getNumNIT() == null))
				|| ((this.numNIT != null) && this.numNIT.equals(other.getNumNIT())))
				&& (((this.numDocumentoPersona == null) && (other.getNumDocumentoPersona() == null))
				|| ((this.numDocumentoPersona != null)
				&& this.numDocumentoPersona.equals(other.getNumDocumentoPersona())))
				&& (((this.nomTipoDocumento == null) && (other.getNomTipoDocumento() == null))
				|| ((this.nomTipoDocumento != null) && this.nomTipoDocumento.equals(other.getNomTipoDocumento())))
				&& (((this.nomPropietario == null) && (other.getNomPropietario() == null))
				|| ((this.nomPropietario != null) && this.nomPropietario.equals(other.getNomPropietario())));
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

		if(getNumNIT() != null)
			_hashCode += getNumNIT().hashCode();

		if(getNumDocumentoPersona() != null)
			_hashCode += getNumDocumentoPersona().hashCode();

		if(getNomTipoDocumento() != null)
			_hashCode += getNomTipoDocumento().hashCode();

		if(getNomPropietario() != null)
			_hashCode += getNomPropietario().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
