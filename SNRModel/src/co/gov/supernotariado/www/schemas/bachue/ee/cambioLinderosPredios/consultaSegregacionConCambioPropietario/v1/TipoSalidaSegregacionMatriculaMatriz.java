/**
 * TipoSalidaConsultaSegregacionConCambioPropietarioSegregacionMatriculaMatriz.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1;

public class TipoSalidaSegregacionMatriculaMatriz implements java.io.Serializable
{
	private static final long serialVersionUID = -4266800377070552761L;

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaSegregacionMatriculaMatriz.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        ">>tipoSalidaConsultaSegregacionConCambioPropietario>segregacion>matriculaMatriz"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numMatriculaInmobiliaria");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        "numMatriculaInmobiliaria"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("direccion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        "direccion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        "estado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("anotacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        "anotacionPredio"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        ">>>tipoSalidaConsultaSegregacionConCambioPropietario>segregacion>matriculaMatriz>anotacionPredio"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object __equalsCalc = null;
	private co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaMatrizAnotacionPredio anotacionPredio;
	private java.lang.String direccion;
	private java.lang.String estado;
	private java.lang.String numMatriculaInmobiliaria;
	private boolean          __hashCodeCalc = false;

	public TipoSalidaSegregacionMatriculaMatriz()
	{
	}

	public TipoSalidaSegregacionMatriculaMatriz(
	    java.lang.String                                                                                                                                                                                        numMatriculaInmobiliaria,
	    java.lang.String                                                                                                                                                                                        direccion,
	    java.lang.String                                                                                                                                                                                        estado,
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaMatrizAnotacionPredio anotacionPredio
	)
	{
		this.numMatriculaInmobiliaria     = numMatriculaInmobiliaria;
		this.direccion                    = direccion;
		this.estado                       = estado;
		this.anotacionPredio              = anotacionPredio;
	}

	/**
	 * Sets the anotacionPredio value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacionMatriculaMatriz.
	 *
	 * @param anotacionPredio
	 */
	public void setAnotacionPredio(
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaMatrizAnotacionPredio anotacionPredio
	)
	{
		this.anotacionPredio = anotacionPredio;
	}

	/**
	 * Gets the anotacionPredio value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacionMatriculaMatriz.
	 *
	 * @return anotacionPredio
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaMatrizAnotacionPredio getAnotacionPredio()
	{
		return anotacionPredio;
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Sets the direccion value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacionMatriculaMatriz.
	 *
	 * @param direccion
	 */
	public void setDireccion(java.lang.String direccion)
	{
		this.direccion = direccion;
	}

	/**
	 * Gets the direccion value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacionMatriculaMatriz.
	 *
	 * @return direccion
	 */
	public java.lang.String getDireccion()
	{
		return direccion;
	}

	/**
	 * Sets the estado value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacionMatriculaMatriz.
	 *
	 * @param estado
	 */
	public void setEstado(java.lang.String estado)
	{
		this.estado = estado;
	}

	/**
	 * Gets the estado value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacionMatriculaMatriz.
	 *
	 * @return estado
	 */
	public java.lang.String getEstado()
	{
		return estado;
	}

	/**
	 * Sets the numMatriculaInmobiliaria value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacionMatriculaMatriz.
	 *
	 * @param numMatriculaInmobiliaria
	 */
	public void setNumMatriculaInmobiliaria(java.lang.String numMatriculaInmobiliaria)
	{
		this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
	}

	/**
	 * Gets the numMatriculaInmobiliaria value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacionMatriculaMatriz.
	 *
	 * @return numMatriculaInmobiliaria
	 */
	public java.lang.String getNumMatriculaInmobiliaria()
	{
		return numMatriculaInmobiliaria;
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaSegregacionMatriculaMatriz))
			return false;

		TipoSalidaSegregacionMatriculaMatriz other = (TipoSalidaSegregacionMatriculaMatriz)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numMatriculaInmobiliaria == null) && (other.getNumMatriculaInmobiliaria() == null))
				|| ((this.numMatriculaInmobiliaria != null)
				&& this.numMatriculaInmobiliaria.equals(other.getNumMatriculaInmobiliaria())))
				&& (((this.direccion == null) && (other.getDireccion() == null))
				|| ((this.direccion != null) && this.direccion.equals(other.getDireccion())))
				&& (((this.estado == null) && (other.getEstado() == null))
				|| ((this.estado != null) && this.estado.equals(other.getEstado())))
				&& (((this.anotacionPredio == null) && (other.getAnotacionPredio() == null))
				|| ((this.anotacionPredio != null) && this.anotacionPredio.equals(other.getAnotacionPredio())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getNumMatriculaInmobiliaria() != null)
			_hashCode += getNumMatriculaInmobiliaria().hashCode();

		if(getDireccion() != null)
			_hashCode += getDireccion().hashCode();

		if(getEstado() != null)
			_hashCode += getEstado().hashCode();

		if(getAnotacionPredio() != null)
			_hashCode += getAnotacionPredio().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
