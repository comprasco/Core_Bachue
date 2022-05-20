/**
 * TipoEntradaIngresoSolicitudMatriculasMatricula.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaIngresoSolicitudMatriculasMatricula.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaIngresoSolicitudMatriculasMatricula implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7804716525902806938L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaIngresoSolicitudMatriculasMatricula.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        ">>tipoEntradaIngresoSolicitud>matriculas>matricula"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        "codCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numMatriculaInmobiliaria");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        "numMatriculaInmobiliaria"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("datosCorregir");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        "datosCorregir"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        ">>>>tipoEntradaIngresoSolicitud>matriculas>matricula>datosCorregir>datoCorregir"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        "datoCorregir"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc        = null;
	
	/** Propiedad cod circulo registral. */
	private java.lang.String codCirculoRegistral;
	
	/** Propiedad num matricula inmobiliaria. */
	private java.lang.String numMatriculaInmobiliaria;
	
	/** Propiedad datos corregir. */
	private co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudMatriculasMatriculaDatosCorregirDatoCorregir[] datosCorregir;
	
	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada ingreso solicitud matriculas matricula.
	 */
	public TipoEntradaIngresoSolicitudMatriculasMatricula()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada ingreso solicitud matriculas matricula.
	 *
	 * @param codCirculoRegistral de cod circulo registral
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 * @param datosCorregir de datos corregir
	 */
	public TipoEntradaIngresoSolicitudMatriculasMatricula(
	    java.lang.String codCirculoRegistral, java.lang.String numMatriculaInmobiliaria,
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudMatriculasMatriculaDatosCorregirDatoCorregir[] datosCorregir
	)
	{
		this.codCirculoRegistral          = codCirculoRegistral;
		this.numMatriculaInmobiliaria     = numMatriculaInmobiliaria;
		this.datosCorregir                = datosCorregir;
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

	/**
	 * Sets the codCirculoRegistral value for this TipoEntradaIngresoSolicitudMatriculasMatricula.
	 *
	 * @param codCirculoRegistral de cod circulo registral
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoEntradaIngresoSolicitudMatriculasMatricula.
	 *
	 * @return codCirculoRegistral
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the datosCorregir value for this TipoEntradaIngresoSolicitudMatriculasMatricula.
	 *
	 * @param datosCorregir de datos corregir
	 */
	public void setDatosCorregir(
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudMatriculasMatriculaDatosCorregirDatoCorregir[] datosCorregir
	)
	{
		this.datosCorregir = datosCorregir;
	}

	/**
	 * Gets the datosCorregir value for this TipoEntradaIngresoSolicitudMatriculasMatricula.
	 *
	 * @return datosCorregir
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudMatriculasMatriculaDatosCorregirDatoCorregir[] getDatosCorregir()
	{
		return datosCorregir;
	}

	/**
	 * Sets the numMatriculaInmobiliaria value for this TipoEntradaIngresoSolicitudMatriculasMatricula.
	 *
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 */
	public void setNumMatriculaInmobiliaria(java.lang.String numMatriculaInmobiliaria)
	{
		this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
	}

	/**
	 * Gets the numMatriculaInmobiliaria value for this TipoEntradaIngresoSolicitudMatriculasMatricula.
	 *
	 * @return numMatriculaInmobiliaria
	 */
	public java.lang.String getNumMatriculaInmobiliaria()
	{
		return numMatriculaInmobiliaria;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaIngresoSolicitudMatriculasMatricula))
			return false;

		TipoEntradaIngresoSolicitudMatriculasMatricula other = (TipoEntradaIngresoSolicitudMatriculasMatricula)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codCirculoRegistral == null) && (other.getCodCirculoRegistral() == null))
				|| ((this.codCirculoRegistral != null)
				&& this.codCirculoRegistral.equals(other.getCodCirculoRegistral())))
				&& (((this.numMatriculaInmobiliaria == null) && (other.getNumMatriculaInmobiliaria() == null))
				|| ((this.numMatriculaInmobiliaria != null)
				&& this.numMatriculaInmobiliaria.equals(other.getNumMatriculaInmobiliaria())))
				&& (((this.datosCorregir == null) && (other.getDatosCorregir() == null))
				|| ((this.datosCorregir != null)
				&& java.util.Arrays.equals(this.datosCorregir, other.getDatosCorregir())));
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

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

		if(getNumMatriculaInmobiliaria() != null)
			_hashCode += getNumMatriculaInmobiliaria().hashCode();

		if(getDatosCorregir() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getDatosCorregir()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getDatosCorregir(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
