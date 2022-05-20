/**
 * TipoEntradaRelacionarDocumento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaRelacionarDocumento.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaRelacionarDocumento implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1966989606047310852L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaRelacionarDocumento.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1",
		        "tipoEntradaRelacionarDocumento"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("sistemaOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1",
		        "sistemaOrigen"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("parametros");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1",
		        "parametros"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1",
		        "tipoParametroRD"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1",
		        "parametro"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("docName");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1",
		        "docName"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("DID");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1", "dID"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                         __equalsCalc   =
		null;
	
	/** Propiedad d ID. */
	private java.lang.String                                                                                         dID;
	
	/** Propiedad doc name. */
	private java.lang.String                                                                                         docName;
	
	/** Propiedad sistema origen. */
	private java.lang.String                                                                                         sistemaOrigen;
	
	/** Propiedad parametros. */
	private co.gov.supernotariado.www.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoParametroRD[] parametros;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                  __hashCodeCalc =
		false;

	/**
	 * Instancia un nuevo objeto tipo entrada relacionar documento.
	 */
	public TipoEntradaRelacionarDocumento()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada relacionar documento.
	 *
	 * @param sistemaOrigen de sistema origen
	 * @param parametros de parametros
	 * @param docName de doc name
	 * @param dID de d ID
	 */
	public TipoEntradaRelacionarDocumento(
	    java.lang.String sistemaOrigen,
	    co.gov.supernotariado.www.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoParametroRD[] parametros,
	    java.lang.String docName, java.lang.String dID
	)
	{
		this.sistemaOrigen     = sistemaOrigen;
		this.parametros        = parametros;
		this.docName           = docName;
		this.dID               = dID;
	}

	/**
	 * Sets the dID value for this TipoEntradaRelacionarDocumento.
	 *
	 * @param dID de d ID
	 */
	public void setDID(java.lang.String dID)
	{
		this.dID = dID;
	}

	/**
	 * Gets the dID value for this TipoEntradaRelacionarDocumento.
	 *
	 * @return dID
	 */
	public java.lang.String getDID()
	{
		return dID;
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
	 * Sets the docName value for this TipoEntradaRelacionarDocumento.
	 *
	 * @param docName de doc name
	 */
	public void setDocName(java.lang.String docName)
	{
		this.docName = docName;
	}

	/**
	 * Gets the docName value for this TipoEntradaRelacionarDocumento.
	 *
	 * @return docName
	 */
	public java.lang.String getDocName()
	{
		return docName;
	}

	/**
	 * Sets the parametros value for this TipoEntradaRelacionarDocumento.
	 *
	 * @param parametros de parametros
	 */
	public void setParametros(
	    co.gov.supernotariado.www.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoParametroRD[] parametros
	)
	{
		this.parametros = parametros;
	}

	/**
	 * Gets the parametros value for this TipoEntradaRelacionarDocumento.
	 *
	 * @return parametros
	 */
	public co.gov.supernotariado.www.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoParametroRD[] getParametros()
	{
		return parametros;
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
	 * Sets the sistemaOrigen value for this TipoEntradaRelacionarDocumento.
	 *
	 * @param sistemaOrigen de sistema origen
	 */
	public void setSistemaOrigen(java.lang.String sistemaOrigen)
	{
		this.sistemaOrigen = sistemaOrigen;
	}

	/**
	 * Gets the sistemaOrigen value for this TipoEntradaRelacionarDocumento.
	 *
	 * @return sistemaOrigen
	 */
	public java.lang.String getSistemaOrigen()
	{
		return sistemaOrigen;
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
		if(!(obj instanceof TipoEntradaRelacionarDocumento))
			return false;

		TipoEntradaRelacionarDocumento other = (TipoEntradaRelacionarDocumento)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.sistemaOrigen == null) && (other.getSistemaOrigen() == null))
				|| ((this.sistemaOrigen != null) && this.sistemaOrigen.equals(other.getSistemaOrigen())))
				&& (((this.parametros == null) && (other.getParametros() == null))
				|| ((this.parametros != null) && java.util.Arrays.equals(this.parametros, other.getParametros())))
				&& (((this.docName == null) && (other.getDocName() == null))
				|| ((this.docName != null) && this.docName.equals(other.getDocName())))
				&& (((this.dID == null) && (other.getDID() == null))
				|| ((this.dID != null) && this.dID.equals(other.getDID())));
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

		if(getSistemaOrigen() != null)
			_hashCode += getSistemaOrigen().hashCode();

		if(getParametros() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getParametros()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getParametros(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getDocName() != null)
			_hashCode += getDocName().hashCode();

		if(getDID() != null)
			_hashCode += getDID().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
