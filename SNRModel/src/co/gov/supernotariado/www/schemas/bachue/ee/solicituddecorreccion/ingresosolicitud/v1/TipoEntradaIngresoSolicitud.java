/**
 * TipoEntradaIngresoSolicitud.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaIngresoSolicitud.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaIngresoSolicitud implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5814674972462488798L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaIngresoSolicitud.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        "tipoEntradaIngresoSolicitud"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("interesado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        "interesado"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        ">tipoEntradaIngresoSolicitud>interesado"
		    )
		);
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("observacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        "observacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matriculas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        "matriculas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        ">>tipoEntradaIngresoSolicitud>matriculas>matricula"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        "matricula"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                                       __equalsCalc   =
		null;
	
	/** Propiedad observacion. */
	private java.lang.String                                                                                                                       observacion;
	
	/** Propiedad interesado. */
	private co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudInteresado[]          interesado;
	
	/** Propiedad matriculas. */
	private co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudMatriculasMatricula[] matriculas;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                                __hashCodeCalc =
		false;

	/**
	 * Instancia un nuevo objeto tipo entrada ingreso solicitud.
	 */
	public TipoEntradaIngresoSolicitud()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada ingreso solicitud.
	 *
	 * @param interesado de interesado
	 * @param observacion de observacion
	 * @param matriculas de matriculas
	 */
	public TipoEntradaIngresoSolicitud(
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudInteresado[]          interesado,
	    java.lang.String                                                                                                                       observacion,
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudMatriculasMatricula[] matriculas
	)
	{
		this.interesado      = interesado;
		this.observacion     = observacion;
		this.matriculas      = matriculas;
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
	 * Sets the interesado value for this TipoEntradaIngresoSolicitud.
	 *
	 * @param interesado de interesado
	 */
	public void setInteresado(
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudInteresado[] interesado
	)
	{
		this.interesado = interesado;
	}

	/**
	 * Cambia el valor de interesado.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setInteresado(
	    int i,
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudInteresado _value
	)
	{
		this.interesado[i] = _value;
	}

	/**
	 * Gets the interesado value for this TipoEntradaIngresoSolicitud.
	 *
	 * @return interesado
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudInteresado[] getInteresado()
	{
		return interesado;
	}

	/**
	 * Retorna Objeto o variable de valor interesado.
	 *
	 * @param i de i
	 * @return el valor de interesado
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudInteresado getInteresado(
	    int i
	)
	{
		return this.interesado[i];
	}

	/**
	 * Sets the matriculas value for this TipoEntradaIngresoSolicitud.
	 *
	 * @param matriculas de matriculas
	 */
	public void setMatriculas(
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudMatriculasMatricula[] matriculas
	)
	{
		this.matriculas = matriculas;
	}

	/**
	 * Gets the matriculas value for this TipoEntradaIngresoSolicitud.
	 *
	 * @return matriculas
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudMatriculasMatricula[] getMatriculas()
	{
		return matriculas;
	}

	/**
	 * Sets the observacion value for this TipoEntradaIngresoSolicitud.
	 *
	 * @param observacion de observacion
	 */
	public void setObservacion(java.lang.String observacion)
	{
		this.observacion = observacion;
	}

	/**
	 * Gets the observacion value for this TipoEntradaIngresoSolicitud.
	 *
	 * @return observacion
	 */
	public java.lang.String getObservacion()
	{
		return observacion;
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
		if(!(obj instanceof TipoEntradaIngresoSolicitud))
			return false;

		TipoEntradaIngresoSolicitud other = (TipoEntradaIngresoSolicitud)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.interesado == null) && (other.getInteresado() == null))
				|| ((this.interesado != null) && java.util.Arrays.equals(this.interesado, other.getInteresado())))
				&& (((this.observacion == null) && (other.getObservacion() == null))
				|| ((this.observacion != null) && this.observacion.equals(other.getObservacion())))
				&& (((this.matriculas == null) && (other.getMatriculas() == null))
				|| ((this.matriculas != null) && java.util.Arrays.equals(this.matriculas, other.getMatriculas())));
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

		if(getInteresado() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getInteresado()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getInteresado(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getObservacion() != null)
			_hashCode += getObservacion().hashCode();

		if(getMatriculas() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getMatriculas()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getMatriculas(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
