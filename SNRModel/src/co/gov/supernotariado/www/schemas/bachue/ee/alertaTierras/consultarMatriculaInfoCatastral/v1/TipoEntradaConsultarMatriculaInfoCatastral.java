/**
 * TipoEntradaConsultarMatriculaInfoCatastral.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarMatriculaInfoCatastral.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoEntradaConsultarMatriculaInfoCatastral implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8936594672921911410L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarMatriculaInfoCatastral.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1",
		        "tipoEntradaConsultarMatriculaInfoCatastral"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("NUPRE");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1",
		        "NUPRE"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numPredial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1",
		        "numPredial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("CHIP");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaInfoCatastral/v1",
		        "CHIP"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad chip. */
	private java.lang.String CHIP;

	/** Propiedad nupre. */
	private java.lang.String NUPRE;

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad num predial. */
	private java.lang.String numPredial;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada consultar matricula info catastral.
	 */
	public TipoEntradaConsultarMatriculaInfoCatastral()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consultar matricula info catastral.
	 *
	 * @param NUPRE de nupre
	 * @param numPredial de num predial
	 * @param CHIP de chip
	 */
	public TipoEntradaConsultarMatriculaInfoCatastral(
	    java.lang.String NUPRE, java.lang.String numPredial, java.lang.String CHIP
	)
	{
		this.NUPRE          = NUPRE;
		this.numPredial     = numPredial;
		this.CHIP           = CHIP;
	}

	/**
	 * Gets the NUPRE value for this TipoEntradaConsultarMatriculaInfoCatastral.
	 *
	 * @return NUPRE
	 */
	public java.lang.String getNUPRE()
	{
		return NUPRE;
	}

	/**
	 * Sets the NUPRE value for this TipoEntradaConsultarMatriculaInfoCatastral.
	 *
	 * @param NUPRE de nupre
	 */
	public void setNUPRE(java.lang.String NUPRE)
	{
		this.NUPRE = NUPRE;
	}

	/**
	 * Gets the numPredial value for this TipoEntradaConsultarMatriculaInfoCatastral.
	 *
	 * @return numPredial
	 */
	public java.lang.String getNumPredial()
	{
		return numPredial;
	}

	/**
	 * Sets the numPredial value for this TipoEntradaConsultarMatriculaInfoCatastral.
	 *
	 * @param numPredial de num predial
	 */
	public void setNumPredial(java.lang.String numPredial)
	{
		this.numPredial = numPredial;
	}

	/**
	 * Gets the CHIP value for this TipoEntradaConsultarMatriculaInfoCatastral.
	 *
	 * @return CHIP
	 */
	public java.lang.String getCHIP()
	{
		return CHIP;
	}

	/**
	 * Sets the CHIP value for this TipoEntradaConsultarMatriculaInfoCatastral.
	 *
	 * @param CHIP de chip
	 */
	public void setCHIP(java.lang.String CHIP)
	{
		this.CHIP = CHIP;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultarMatriculaInfoCatastral))
			return false;

		TipoEntradaConsultarMatriculaInfoCatastral other = (TipoEntradaConsultarMatriculaInfoCatastral)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.NUPRE == null) && (other.getNUPRE() == null))
				|| ((this.NUPRE != null) && this.NUPRE.equals(other.getNUPRE())))
				&& (((this.numPredial == null) && (other.getNumPredial() == null))
				|| ((this.numPredial != null) && this.numPredial.equals(other.getNumPredial())))
				&& (((this.CHIP == null) && (other.getCHIP() == null))
				|| ((this.CHIP != null) && this.CHIP.equals(other.getCHIP())));
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

		if(getNUPRE() != null)
			_hashCode += getNUPRE().hashCode();

		if(getNumPredial() != null)
			_hashCode += getNumPredial().hashCode();

		if(getCHIP() != null)
			_hashCode += getCHIP().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
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
}
