/**
 * TipoSalidaConsultarUsuarios.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1;



/**
 * The Class TipoSalidaConsultarUsuarios.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaConsultarUsuarios implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8388752054038875424L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarUsuarios.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "tipoSalidaConsultarUsuarios"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("usuarios");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "usuarios"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "tipoUsuarioCUSO"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1", "usuario"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "codigoMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The codigo mensaje. */
	private java.math.BigInteger codigoMensaje;

	/** The descripcion mensaje. */
	private java.lang.String descripcionMensaje;

	/** The usuarios. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoUsuarioCUSO[] usuarios;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo salida consultar usuarios.
	 */
	public TipoSalidaConsultarUsuarios()
	{
	}

	/**
	 * Instantiates a new tipo salida consultar usuarios.
	 *
	 * @param usuarios the usuarios
	 * @param codigoMensaje the codigo mensaje
	 * @param descripcionMensaje the descripcion mensaje
	 */
	public TipoSalidaConsultarUsuarios(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoUsuarioCUSO[] usuarios,
	    java.math.BigInteger                                                                                  codigoMensaje,
	    java.lang.String                                                                                      descripcionMensaje
	)
	{
		this.usuarios               = usuarios;
		this.codigoMensaje          = codigoMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Gets the usuarios value for this TipoSalidaConsultarUsuarios.
	 *
	 * @return usuarios
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoUsuarioCUSO[] getUsuarios()
	{
		return usuarios;
	}

	/**
	 * Sets the usuarios value for this TipoSalidaConsultarUsuarios.
	 *
	 * @param usuarios the new usuarios
	 */
	public void setUsuarios(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoUsuarioCUSO[] usuarios
	)
	{
		this.usuarios = usuarios;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaConsultarUsuarios.
	 *
	 * @return codigoMensaje
	 */
	public java.math.BigInteger getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaConsultarUsuarios.
	 *
	 * @param codigoMensaje the new codigo mensaje
	 */
	public void setCodigoMensaje(java.math.BigInteger codigoMensaje)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaConsultarUsuarios.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaConsultarUsuarios.
	 *
	 * @param descripcionMensaje the new descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultarUsuarios))
			return false;

		TipoSalidaConsultarUsuarios other = (TipoSalidaConsultarUsuarios)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.usuarios == null) && (other.getUsuarios() == null))
				|| ((this.usuarios != null) && java.util.Arrays.equals(this.usuarios, other.getUsuarios())))
				&& (((this.codigoMensaje == null) && (other.getCodigoMensaje() == null))
				|| ((this.codigoMensaje != null) && this.codigoMensaje.equals(other.getCodigoMensaje())))
				&& (((this.descripcionMensaje == null) && (other.getDescripcionMensaje() == null))
				|| ((this.descripcionMensaje != null) && this.descripcionMensaje.equals(other.getDescripcionMensaje())));
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

		if(getUsuarios() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getUsuarios()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getUsuarios(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getCodigoMensaje() != null)
			_hashCode += getCodigoMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

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
