/**
 * TipoSalidaConsultaSegregacionConCambioPropietarioSegregacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1;

public class TipoSalidaSegregacion implements java.io.Serializable
{
	private static final long serialVersionUID = 7850175212648787575L;

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaSegregacion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        ">tipoSalidaConsultaSegregacionConCambioPropietario>segregacion"
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
		elemField.setNillable(true);
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
		elemField.setFieldName("matriculaMatriz");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        "matriculaMatriz"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        ">>tipoSalidaConsultaSegregacionConCambioPropietario>segregacion>matriculaMatriz"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matriculasSegregadas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        "matriculasSegregadas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        ">>>tipoSalidaConsultaSegregacionConCambioPropietario>segregacion>matriculasSegregadas>matriculaSegregada"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioLinderosPredios/consultaSegregacionConCambioPropietario/v1",
		        "matriculaSegregada"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object                                                                                                                                                                                                  __equalsCalc         =
		null;
	private java.lang.String                                                                                                                                                                                                  codigoActo;
	private java.lang.String                                                                                                                                                                                                  descripcionActo;
	private co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaMatriz                          matriculaMatriz;
	private co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaSegregada[] matriculasSegregadas;
	private boolean                                                                                                                                                                                                           __hashCodeCalc       =
		false;

	public TipoSalidaSegregacion()
	{
	}

	public TipoSalidaSegregacion(
	    java.lang.String codigoActo, java.lang.String descripcionActo,
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaMatriz matriculaMatriz,
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaSegregada[] matriculasSegregadas
	)
	{
		this.codigoActo               = codigoActo;
		this.descripcionActo          = descripcionActo;
		this.matriculaMatriz          = matriculaMatriz;
		this.matriculasSegregadas     = matriculasSegregadas;
	}

	/**
	 * Sets the codigoActo value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacion.
	 *
	 * @param codigoActo
	 */
	public void setCodigoActo(java.lang.String codigoActo)
	{
		this.codigoActo = codigoActo;
	}

	/**
	 * Gets the codigoActo value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacion.
	 *
	 * @return codigoActo
	 */
	public java.lang.String getCodigoActo()
	{
		return codigoActo;
	}

	/**
	 * Sets the descripcionActo value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacion.
	 *
	 * @param descripcionActo
	 */
	public void setDescripcionActo(java.lang.String descripcionActo)
	{
		this.descripcionActo = descripcionActo;
	}

	/**
	 * Gets the descripcionActo value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacion.
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
	 * Sets the matriculaMatriz value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacion.
	 *
	 * @param matriculaMatriz
	 */
	public void setMatriculaMatriz(
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaMatriz matriculaMatriz
	)
	{
		this.matriculaMatriz = matriculaMatriz;
	}

	/**
	 * Gets the matriculaMatriz value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacion.
	 *
	 * @return matriculaMatriz
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaMatriz getMatriculaMatriz()
	{
		return matriculaMatriz;
	}

	/**
	 * Sets the matriculasSegregadas value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacion.
	 *
	 * @param matriculasSegregadas
	 */
	public void setMatriculasSegregadas(
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaSegregada[] matriculasSegregadas
	)
	{
		this.matriculasSegregadas = matriculasSegregadas;
	}

	/**
	 * Gets the matriculasSegregadas value for this TipoSalidaConsultaSegregacionConCambioPropietarioSegregacion.
	 *
	 * @return matriculasSegregadas
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaSegregada[] getMatriculasSegregadas()
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
		if(!(obj instanceof TipoSalidaSegregacion))
			return false;

		TipoSalidaSegregacion other = (TipoSalidaSegregacion)obj;

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
