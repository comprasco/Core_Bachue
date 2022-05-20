/**
 * TipoServicioGSI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1;



/**
 * The Class TipoServicioGSI.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoServicioGSI implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5177800147790312401L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoServicioGSI.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "tipoServicioGSI"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "tipoServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("subtipoServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "subtipoServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("criterios");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "criterios"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "tipoCriterioGSI"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "criterio"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The subtipo servicio. */
	private java.lang.String subtipoServicio;

	/** The tipo servicio. */
	private java.lang.String tipoServicio;

	/** The criterios. */
	private co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoCriterioGSI[] criterios;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo servicio GSI.
	 */
	public TipoServicioGSI()
	{
	}

	/**
	 * Instantiates a new tipo servicio GSI.
	 *
	 * @param tipoServicio the tipo servicio
	 * @param subtipoServicio the subtipo servicio
	 * @param criterios the criterios
	 */
	public TipoServicioGSI(
	    java.lang.String tipoServicio, java.lang.String subtipoServicio,
	    co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoCriterioGSI[] criterios
	)
	{
		this.tipoServicio        = tipoServicio;
		this.subtipoServicio     = subtipoServicio;
		this.criterios           = criterios;
	}

	/**
	 * Gets the tipoServicio value for this TipoServicioGSI.
	 *
	 * @return tipoServicio
	 */
	public java.lang.String getTipoServicio()
	{
		return tipoServicio;
	}

	/**
	 * Sets the tipoServicio value for this TipoServicioGSI.
	 *
	 * @param tipoServicio the new tipo servicio
	 */
	public void setTipoServicio(java.lang.String tipoServicio)
	{
		this.tipoServicio = tipoServicio;
	}

	/**
	 * Gets the subtipoServicio value for this TipoServicioGSI.
	 *
	 * @return subtipoServicio
	 */
	public java.lang.String getSubtipoServicio()
	{
		return subtipoServicio;
	}

	/**
	 * Sets the subtipoServicio value for this TipoServicioGSI.
	 *
	 * @param subtipoServicio the new subtipo servicio
	 */
	public void setSubtipoServicio(java.lang.String subtipoServicio)
	{
		this.subtipoServicio = subtipoServicio;
	}

	/**
	 * Gets the criterios value for this TipoServicioGSI.
	 *
	 * @return criterios
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoCriterioGSI[] getCriterios()
	{
		return criterios;
	}

	/**
	 * Sets the criterios value for this TipoServicioGSI.
	 *
	 * @param criterios the new criterios
	 */
	public void setCriterios(
	    co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoCriterioGSI[] criterios
	)
	{
		this.criterios = criterios;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoServicioGSI))
			return false;

		TipoServicioGSI other = (TipoServicioGSI)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoServicio == null) && (other.getTipoServicio() == null))
				|| ((this.tipoServicio != null) && this.tipoServicio.equals(other.getTipoServicio())))
				&& (((this.subtipoServicio == null) && (other.getSubtipoServicio() == null))
				|| ((this.subtipoServicio != null) && this.subtipoServicio.equals(other.getSubtipoServicio())))
				&& (((this.criterios == null) && (other.getCriterios() == null))
				|| ((this.criterios != null) && java.util.Arrays.equals(this.criterios, other.getCriterios())));
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

		if(getTipoServicio() != null)
			_hashCode += getTipoServicio().hashCode();

		if(getSubtipoServicio() != null)
			_hashCode += getSubtipoServicio().hashCode();

		if(getCriterios() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getCriterios()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getCriterios(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

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
