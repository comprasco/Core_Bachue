/**
 * TipoSalidaConsultaSegregacionConCambioPropietarioAgregacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1;

public class TipoSalidaAgregacion implements java.io.Serializable
{
	private static final long serialVersionUID = 6589974084537160423L;

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaAgregacion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        ">tipoSalidaConsultaSegregacionConCambioPropietario>agregacion"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoActo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        "codigoActo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionActo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        "descripcionActo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matriculasMatrices");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        "matriculasMatrices"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        ">>>tipoSalidaConsultaSegregacionConCambioPropietario>agregacion>matriculasMatrices>matriculaMatriz"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        "matriculaMatriz"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matriculaAgregada");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        "matriculaAgregada"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        ">>tipoSalidaConsultaSegregacionConCambioPropietario>agregacion>matriculaAgregada"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object                                                                                                                                                                                            __equalsCalc       =
		null;
	private java.lang.String                                                                                                                                                                                            codigoActo;
	private java.lang.String                                                                                                                                                                                            descripcionActo;
	private co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaAgregacionMatriculaAgregada                   matriculaAgregada;
	private co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaAgregacionMatriculaMatriz[] matriculasMatrices;
	private boolean                                                                                                                                                                                                     __hashCodeCalc     =
		false;

	public TipoSalidaAgregacion()
	{
	}

	public TipoSalidaAgregacion(
	    java.lang.String codigoActo, java.lang.String descripcionActo,
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaAgregacionMatriculaMatriz[] matriculasMatrices,
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaAgregacionMatriculaAgregada matriculaAgregada
	)
	{
		this.codigoActo             = codigoActo;
		this.descripcionActo        = descripcionActo;
		this.matriculasMatrices     = matriculasMatrices;
		this.matriculaAgregada      = matriculaAgregada;
	}

	/**
	 * Sets the codigoActo value for this TipoSalidaConsultaSegregacionConCambioPropietarioAgregacion.
	 *
	 * @param codigoActo
	 */
	public void setCodigoActo(java.lang.String codigoActo)
	{
		this.codigoActo = codigoActo;
	}

	/**
	 * Gets the codigoActo value for this TipoSalidaConsultaSegregacionConCambioPropietarioAgregacion.
	 *
	 * @return codigoActo
	 */
	public java.lang.String getCodigoActo()
	{
		return codigoActo;
	}

	/**
	 * Sets the descripcionActo value for this TipoSalidaConsultaSegregacionConCambioPropietarioAgregacion.
	 *
	 * @param descripcionActo
	 */
	public void setDescripcionActo(java.lang.String descripcionActo)
	{
		this.descripcionActo = descripcionActo;
	}

	/**
	 * Gets the descripcionActo value for this TipoSalidaConsultaSegregacionConCambioPropietarioAgregacion.
	 *
	 * @return descripcionActo
	 */
	public java.lang.String getDescripcionActo()
	{
		return descripcionActo;
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
	 * Sets the matriculaAgregada value for this TipoSalidaConsultaSegregacionConCambioPropietarioAgregacion.
	 *
	 * @param matriculaAgregada
	 */
	public void setMatriculaAgregada(
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaAgregacionMatriculaAgregada matriculaAgregada
	)
	{
		this.matriculaAgregada = matriculaAgregada;
	}

	/**
	 * Gets the matriculaAgregada value for this TipoSalidaConsultaSegregacionConCambioPropietarioAgregacion.
	 *
	 * @return matriculaAgregada
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaAgregacionMatriculaAgregada getMatriculaAgregada()
	{
		return matriculaAgregada;
	}

	/**
	 * Sets the matriculasMatrices value for this TipoSalidaConsultaSegregacionConCambioPropietarioAgregacion.
	 *
	 * @param matriculasMatrices
	 */
	public void setMatriculasMatrices(
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaAgregacionMatriculaMatriz[] matriculasMatrices
	)
	{
		this.matriculasMatrices = matriculasMatrices;
	}

	/**
	 * Gets the matriculasMatrices value for this TipoSalidaConsultaSegregacionConCambioPropietarioAgregacion.
	 *
	 * @return matriculasMatrices
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaAgregacionMatriculaMatriz[] getMatriculasMatrices()
	{
		return matriculasMatrices;
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
		if(!(obj instanceof TipoSalidaAgregacion))
			return false;

		TipoSalidaAgregacion other = (TipoSalidaAgregacion)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigoActo == null) && (other.getCodigoActo() == null))
				|| ((this.codigoActo != null) && this.codigoActo.equals(other.getCodigoActo())))
				&& (((this.descripcionActo == null) && (other.getDescripcionActo() == null))
				|| ((this.descripcionActo != null) && this.descripcionActo.equals(other.getDescripcionActo())))
				&& (((this.matriculasMatrices == null) && (other.getMatriculasMatrices() == null))
				|| ((this.matriculasMatrices != null)
				&& java.util.Arrays.equals(this.matriculasMatrices, other.getMatriculasMatrices())))
				&& (((this.matriculaAgregada == null) && (other.getMatriculaAgregada() == null))
				|| ((this.matriculaAgregada != null) && this.matriculaAgregada.equals(other.getMatriculaAgregada())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getCodigoActo() != null)
			_hashCode += getCodigoActo().hashCode();

		if(getDescripcionActo() != null)
			_hashCode += getDescripcionActo().hashCode();

		if(getMatriculasMatrices() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getMatriculasMatrices()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getMatriculasMatrices(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getMatriculaAgregada() != null)
			_hashCode += getMatriculaAgregada().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
