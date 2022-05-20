/**
 * TipoSalidaConsultaSegregacionSinCambioPropietarioSegregacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1;

public class TipoSalidaSinCambioSegregacion implements java.io.Serializable
{
	private static final long serialVersionUID = -5366372908057077617L;

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaSinCambioSegregacion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionSinCambioPropietario/v1",
		        ">tipoSalidaConsultaSegregacionSinCambioPropietario>segregacion"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoActo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionSinCambioPropietario/v1",
		        "codigoActo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionActo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionSinCambioPropietario/v1",
		        "descripcionActo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matriculaMatriz");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionSinCambioPropietario/v1",
		        "matriculaMatriz"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionSinCambioPropietario/v1",
		        ">>tipoSalidaConsultaSegregacionSinCambioPropietario>segregacion>matriculaMatriz"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matriculasSegregadas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionSinCambioPropietario/v1",
		        "matriculasSegregadas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionSinCambioPropietario/v1",
		        ">>>tipoSalidaConsultaSegregacionSinCambioPropietario>segregacion>matriculasSegregadas>matriculaSegregada"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionSinCambioPropietario/v1",
		        "matriculaSegregada"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object                                                                                                                                                __equalsCalc         =
		null;
	private java.lang.String                                                                                                                                                codigoActo;
	private java.lang.String                                                                                                                                                descripcionActo;
	private co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioSegregacionMatriculaMatriz      matriculaMatriz;
	private co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioSegregacionMatriculaSegregada[] matriculasSegregadas;
	private boolean                                                                                                                                                         __hashCodeCalc       =
		false;

	public TipoSalidaSinCambioSegregacion()
	{
	}

	public TipoSalidaSinCambioSegregacion(
	    java.lang.String codigoActo, java.lang.String descripcionActo,
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioSegregacionMatriculaMatriz matriculaMatriz,
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioSegregacionMatriculaSegregada[] matriculasSegregadas
	)
	{
		this.codigoActo               = codigoActo;
		this.descripcionActo          = descripcionActo;
		this.matriculaMatriz          = matriculaMatriz;
		this.matriculasSegregadas     = matriculasSegregadas;
	}

	/**
	 * Sets the codigoActo value for this TipoSalidaConsultaSegregacionSinCambioPropietarioSegregacion.
	 *
	 * @param codigoActo
	 */
	public void setCodigoActo(java.lang.String codigoActo)
	{
		this.codigoActo = codigoActo;
	}

	/**
	 * Gets the codigoActo value for this TipoSalidaConsultaSegregacionSinCambioPropietarioSegregacion.
	 *
	 * @return codigoActo
	 */
	public java.lang.String getCodigoActo()
	{
		return codigoActo;
	}

	/**
	 * Sets the descripcionActo value for this TipoSalidaConsultaSegregacionSinCambioPropietarioSegregacion.
	 *
	 * @param descripcionActo
	 */
	public void setDescripcionActo(java.lang.String descripcionActo)
	{
		this.descripcionActo = descripcionActo;
	}

	/**
	 * Gets the descripcionActo value for this TipoSalidaConsultaSegregacionSinCambioPropietarioSegregacion.
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
	 * Sets the matriculaMatriz value for this TipoSalidaConsultaSegregacionSinCambioPropietarioSegregacion.
	 *
	 * @param matriculaMatriz
	 */
	public void setMatriculaMatriz(
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioSegregacionMatriculaMatriz matriculaMatriz
	)
	{
		this.matriculaMatriz = matriculaMatriz;
	}

	/**
	 * Gets the matriculaMatriz value for this TipoSalidaConsultaSegregacionSinCambioPropietarioSegregacion.
	 *
	 * @return matriculaMatriz
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioSegregacionMatriculaMatriz getMatriculaMatriz()
	{
		return matriculaMatriz;
	}

	/**
	 * Sets the matriculasSegregadas value for this TipoSalidaConsultaSegregacionSinCambioPropietarioSegregacion.
	 *
	 * @param matriculasSegregadas
	 */
	public void setMatriculasSegregadas(
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioSegregacionMatriculaSegregada[] matriculasSegregadas
	)
	{
		this.matriculasSegregadas = matriculasSegregadas;
	}

	/**
	 * Gets the matriculasSegregadas value for this TipoSalidaConsultaSegregacionSinCambioPropietarioSegregacion.
	 *
	 * @return matriculasSegregadas
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioSegregacionMatriculaSegregada[] getMatriculasSegregadas()
	{
		return matriculasSegregadas;
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
		if(!(obj instanceof TipoSalidaSinCambioSegregacion))
			return false;

		TipoSalidaSinCambioSegregacion other = (TipoSalidaSinCambioSegregacion)obj;

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
				&& (((this.matriculaMatriz == null) && (other.getMatriculaMatriz() == null))
				|| ((this.matriculaMatriz != null) && this.matriculaMatriz.equals(other.getMatriculaMatriz())))
				&& (((this.matriculasSegregadas == null) && (other.getMatriculasSegregadas() == null))
				|| ((this.matriculasSegregadas != null)
				&& java.util.Arrays.equals(this.matriculasSegregadas, other.getMatriculasSegregadas())));
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

		if(getMatriculaMatriz() != null)
			_hashCode += getMatriculaMatriz().hashCode();

		if(getMatriculasSegregadas() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getMatriculasSegregadas()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getMatriculasSegregadas(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
