/**
 * TipoSalidaConsultarDatosRegistrales.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarDatosRegistrales.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaConsultarDatosRegistrales implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1461950287998321755L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarDatosRegistrales.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "tipoSalidaConsultarDatosRegistrales"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("cantidadRegistros");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "cantidadRegistros"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matriculas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "matriculas"
		    )
		);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "matricula"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "codigoMensaje"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        ">tipoSalidaConsultarDatosRegistrales>codigoMensaje"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                                                   __equalsCalc       =
		null;
	
	/** Propiedad cantidad registros. */
	private java.lang.String                                                                                                                                   cantidadRegistros;
	
	/** Propiedad codigo mensaje. */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoSalidaConsultarDatosRegistralesCodigoMensaje codigoMensaje;
	
	/** Propiedad descripcion mensaje. */
	private java.lang.String                                                                                                                                   descripcionMensaje;
	
	/** Propiedad matriculas. */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Matricula[]                                      matriculas;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                                            __hashCodeCalc     =
		false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar datos registrales.
	 */
	public TipoSalidaConsultarDatosRegistrales()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar datos registrales.
	 *
	 * @param cantidadRegistros de cantidad registros
	 * @param matriculas de matriculas
	 * @param codigoMensaje de codigo mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaConsultarDatosRegistrales(
	    java.lang.String cantidadRegistros,
	    co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Matricula[] matriculas,
	    co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoSalidaConsultarDatosRegistralesCodigoMensaje codigoMensaje,
	    java.lang.String descripcionMensaje
	)
	{
		this.cantidadRegistros      = cantidadRegistros;
		this.matriculas             = matriculas;
		this.codigoMensaje          = codigoMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Sets the cantidadRegistros value for this
	 * TipoSalidaConsultarDatosRegistrales.
	 *
	 * @param cantidadRegistros de cantidad registros
	 */
	public void setCantidadRegistros(java.lang.String cantidadRegistros)
	{
		this.cantidadRegistros = cantidadRegistros;
	}

	/**
	 * Gets the cantidadRegistros value for this
	 * TipoSalidaConsultarDatosRegistrales.
	 *
	 * @return cantidadRegistros
	 */
	public java.lang.String getCantidadRegistros()
	{
		return cantidadRegistros;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaConsultarDatosRegistrales.
	 *
	 * @param codigoMensaje de codigo mensaje
	 */
	public void setCodigoMensaje(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoSalidaConsultarDatosRegistralesCodigoMensaje codigoMensaje
	)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaConsultarDatosRegistrales.
	 *
	 * @return codigoMensaje
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoSalidaConsultarDatosRegistralesCodigoMensaje getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this
	 * TipoSalidaConsultarDatosRegistrales.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this
	 * TipoSalidaConsultarDatosRegistrales.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
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
	 * Sets the matriculas value for this TipoSalidaConsultarDatosRegistrales.
	 *
	 * @param matriculas de matriculas
	 */
	public void setMatriculas(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Matricula[] matriculas
	)
	{
		this.matriculas = matriculas;
	}

	/**
	 * Gets the matriculas value for this TipoSalidaConsultarDatosRegistrales.
	 *
	 * @return matriculas
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Matricula[] getMatriculas()
	{
		return matriculas;
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
		if(!(obj instanceof TipoSalidaConsultarDatosRegistrales))
			return false;

		TipoSalidaConsultarDatosRegistrales other = (TipoSalidaConsultarDatosRegistrales)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.cantidadRegistros == null) && (other.getCantidadRegistros() == null))
				|| ((this.cantidadRegistros != null) && this.cantidadRegistros.equals(other.getCantidadRegistros())))
				&& (((this.matriculas == null) && (other.getMatriculas() == null))
				|| ((this.matriculas != null) && java.util.Arrays.equals(this.matriculas, other.getMatriculas())))
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

		if(getCantidadRegistros() != null)
			_hashCode += getCantidadRegistros().hashCode();

		if(getMatriculas() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getMatriculas()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getMatriculas(), i);

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
}
