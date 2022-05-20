/**
 * TipoEntradaIngresoSolicitud.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaIngresoSolicitud.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaIngresoSolicitud implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 287605266279360824L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaIngresoSolicitud.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1",
		        "tipoEntradaIngresoSolicitud"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaDocumentos");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1",
		        "listaDocumentos"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1",
		        ">>tipoEntradaIngresoSolicitud>listaDocumentos>documentoSGD"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1",
		        "documentoSGD"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                                           __equalsCalc    =
		null;
	
	/** Propiedad lista documentos. */
	private co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD[] listaDocumentos;
	
	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada ingreso solicitud.
	 */
	public TipoEntradaIngresoSolicitud()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada ingreso solicitud.
	 *
	 * @param listaDocumentos de lista documentos
	 */
	public TipoEntradaIngresoSolicitud(
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD[] listaDocumentos
	)
	{
		this.listaDocumentos = listaDocumentos;
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
	 * Sets the listaDocumentos value for this TipoEntradaIngresoSolicitud.
	 *
	 * @param listaDocumentos de lista documentos
	 */
	public void setListaDocumentos(
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD[] listaDocumentos
	)
	{
		this.listaDocumentos = listaDocumentos;
	}

	/**
	 * Gets the listaDocumentos value for this TipoEntradaIngresoSolicitud.
	 *
	 * @return listaDocumentos
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD[] getListaDocumentos()
	{
		return listaDocumentos;
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
				&& (((this.listaDocumentos == null) && (other.getListaDocumentos() == null))
				|| ((this.listaDocumentos != null)
				&& java.util.Arrays.equals(this.listaDocumentos, other.getListaDocumentos())));
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

		if(getListaDocumentos() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaDocumentos()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaDocumentos(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
