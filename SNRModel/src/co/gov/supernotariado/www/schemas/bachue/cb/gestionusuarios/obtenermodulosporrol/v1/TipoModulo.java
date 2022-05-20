/**
 * TipoModulo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v1;


/**
 * Clase que contiene todos las propiedades TipoModulo.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoModulo implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2684471922919085217L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoModulo.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenermodulosporrol/v1",
		        "tipoModulo"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idComponente");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenermodulosporrol/v1",
		        "idComponente"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenermodulosporrol/v1", "nombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("activo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenermodulosporrol/v1", "activo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad activo. */
	private java.lang.String activo;
	
	/** Propiedad id componente. */
	private java.lang.String idComponente;
	
	/** Propiedad nombre. */
	private java.lang.String nombre;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo modulo.
	 */
	public TipoModulo()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo modulo.
	 *
	 * @param idComponente de id componente
	 * @param nombre de nombre
	 * @param activo de activo
	 */
	public TipoModulo(java.lang.String idComponente, java.lang.String nombre, java.lang.String activo)
	{
		this.idComponente     = idComponente;
		this.nombre           = nombre;
		this.activo           = activo;
	}

	/**
	 * Sets the activo value for this TipoModulo.
	 *
	 * @param activo de activo
	 */
	public void setActivo(java.lang.String activo)
	{
		this.activo = activo;
	}

	/**
	 * Gets the activo value for this TipoModulo.
	 *
	 * @return activo
	 */
	public java.lang.String getActivo()
	{
		return activo;
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
	 * Sets the idComponente value for this TipoModulo.
	 *
	 * @param idComponente de id componente
	 */
	public void setIdComponente(java.lang.String idComponente)
	{
		this.idComponente = idComponente;
	}

	/**
	 * Gets the idComponente value for this TipoModulo.
	 *
	 * @return idComponente
	 */
	public java.lang.String getIdComponente()
	{
		return idComponente;
	}

	/**
	 * Sets the nombre value for this TipoModulo.
	 *
	 * @param nombre de nombre
	 */
	public void setNombre(java.lang.String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * Gets the nombre value for this TipoModulo.
	 *
	 * @return nombre
	 */
	public java.lang.String getNombre()
	{
		return nombre;
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
		if(!(obj instanceof TipoModulo))
			return false;

		TipoModulo other = (TipoModulo)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.idComponente == null) && (other.getIdComponente() == null))
				|| ((this.idComponente != null) && this.idComponente.equals(other.getIdComponente())))
				&& (((this.nombre == null) && (other.getNombre() == null))
				|| ((this.nombre != null) && this.nombre.equals(other.getNombre())))
				&& (((this.activo == null) && (other.getActivo() == null))
				|| ((this.activo != null) && this.activo.equals(other.getActivo())));
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

		if(getIdComponente() != null)
			_hashCode += getIdComponente().hashCode();

		if(getNombre() != null)
			_hashCode += getNombre().hashCode();

		if(getActivo() != null)
			_hashCode += getActivo().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
