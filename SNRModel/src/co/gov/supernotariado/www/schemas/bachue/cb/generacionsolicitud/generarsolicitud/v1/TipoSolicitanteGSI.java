/**
 * TipoSolicitanteGSI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1;



/**
 * The Class TipoSolicitanteGSI.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSolicitanteGSI implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -13205568565595589L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSolicitanteGSI.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "tipoSolicitanteGSI"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "tipoDocumento"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        ">tipoSolicitanteGSI>tipoDocumento"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "numeroDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "tipoPersona"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The numero documento. */
	private java.lang.String numeroDocumento;

	/** The tipo documento. */
	private co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSolicitanteGSITipoDocumento tipoDocumento;

	/** The tipo persona. */
	private java.lang.String tipoPersona;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo solicitante GSI.
	 */
	public TipoSolicitanteGSI()
	{
	}

	/**
	 * Instantiates a new tipo solicitante GSI.
	 *
	 * @param tipoDocumento the tipo documento
	 * @param numeroDocumento the numero documento
	 * @param tipoPersona the tipo persona
	 */
	public TipoSolicitanteGSI(
	    co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSolicitanteGSITipoDocumento tipoDocumento,
	    java.lang.String                                                                                                    numeroDocumento,
	    java.lang.String                                                                                                    tipoPersona
	)
	{
		this.tipoDocumento       = tipoDocumento;
		this.numeroDocumento     = numeroDocumento;
		this.tipoPersona         = tipoPersona;
	}

	/**
	 * Gets the tipoDocumento value for this TipoSolicitanteGSI.
	 *
	 * @return tipoDocumento
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSolicitanteGSITipoDocumento getTipoDocumento()
	{
		return tipoDocumento;
	}

	/**
	 * Sets the tipoDocumento value for this TipoSolicitanteGSI.
	 *
	 * @param tipoDocumento the new tipo documento
	 */
	public void setTipoDocumento(
	    co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSolicitanteGSITipoDocumento tipoDocumento
	)
	{
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the numeroDocumento value for this TipoSolicitanteGSI.
	 *
	 * @return numeroDocumento
	 */
	public java.lang.String getNumeroDocumento()
	{
		return numeroDocumento;
	}

	/**
	 * Sets the numeroDocumento value for this TipoSolicitanteGSI.
	 *
	 * @param numeroDocumento the new numero documento
	 */
	public void setNumeroDocumento(java.lang.String numeroDocumento)
	{
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Gets the tipoPersona value for this TipoSolicitanteGSI.
	 *
	 * @return tipoPersona
	 */
	public java.lang.String getTipoPersona()
	{
		return tipoPersona;
	}

	/**
	 * Sets the tipoPersona value for this TipoSolicitanteGSI.
	 *
	 * @param tipoPersona the new tipo persona
	 */
	public void setTipoPersona(java.lang.String tipoPersona)
	{
		this.tipoPersona = tipoPersona;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSolicitanteGSI))
			return false;

		TipoSolicitanteGSI other = (TipoSolicitanteGSI)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoDocumento == null) && (other.getTipoDocumento() == null))
				|| ((this.tipoDocumento != null) && this.tipoDocumento.equals(other.getTipoDocumento())))
				&& (((this.numeroDocumento == null) && (other.getNumeroDocumento() == null))
				|| ((this.numeroDocumento != null) && this.numeroDocumento.equals(other.getNumeroDocumento())))
				&& (((this.tipoPersona == null) && (other.getTipoPersona() == null))
				|| ((this.tipoPersona != null) && this.tipoPersona.equals(other.getTipoPersona())));
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

		if(getTipoDocumento() != null)
			_hashCode += getTipoDocumento().hashCode();

		if(getNumeroDocumento() != null)
			_hashCode += getNumeroDocumento().hashCode();

		if(getTipoPersona() != null)
			_hashCode += getTipoPersona().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}

	/**
	 * Return type metadata object.
	 *
	 * @return the type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Get Custom Serializer.
	 *
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer.
	 *
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}
}
