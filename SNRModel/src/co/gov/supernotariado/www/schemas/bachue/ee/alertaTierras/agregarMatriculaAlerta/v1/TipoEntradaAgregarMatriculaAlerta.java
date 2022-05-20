/**
 * TipoEntradaAgregarMatriculaAlerta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaAgregarMatriculaAlerta.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoEntradaAgregarMatriculaAlerta implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6430094934451151008L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaAgregarMatriculaAlerta.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarMatriculaAlerta/v1",
		        "tipoEntradaAgregarMatriculaAlerta"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idAlerta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarMatriculaAlerta/v1",
		        "idAlerta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarMatriculaAlerta/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarMatriculaAlerta/v1",
		        "numMatriculaInmobiliaria"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod circulo registral. */
	private java.lang.String codCirculoRegistral;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/** Propiedad id alerta. */
	private int idAlerta;

	/** Propiedad num matricula inmobiliaria. */
	private int numMatriculaInmobiliaria;

	/**
	 * Instancia un nuevo objeto tipo entrada agregar matricula alerta.
	 */
	public TipoEntradaAgregarMatriculaAlerta()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada agregar matricula alerta.
	 *
	 * @param idAlerta de id alerta
	 * @param codCirculoRegistral de cod circulo registral
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 */
	public TipoEntradaAgregarMatriculaAlerta(
	    int idAlerta, java.lang.String codCirculoRegistral, int numMatriculaInmobiliaria
	)
	{
		this.idAlerta                     = idAlerta;
		this.codCirculoRegistral          = codCirculoRegistral;
		this.numMatriculaInmobiliaria     = numMatriculaInmobiliaria;
	}

	/**
	 * Gets the idAlerta value for this TipoEntradaAgregarMatriculaAlerta.
	 *
	 * @return idAlerta
	 */
	public int getIdAlerta()
	{
		return idAlerta;
	}

	/**
	 * Sets the idAlerta value for this TipoEntradaAgregarMatriculaAlerta.
	 *
	 * @param idAlerta de id alerta
	 */
	public void setIdAlerta(int idAlerta)
	{
		this.idAlerta = idAlerta;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoEntradaAgregarMatriculaAlerta.
	 *
	 * @return codCirculoRegistral
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoEntradaAgregarMatriculaAlerta.
	 *
	 * @param codCirculoRegistral de cod circulo registral
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the numMatriculaInmobiliaria value for this TipoEntradaAgregarMatriculaAlerta.
	 *
	 * @return numMatriculaInmobiliaria
	 */
	public int getNumMatriculaInmobiliaria()
	{
		return numMatriculaInmobiliaria;
	}

	/**
	 * Sets the numMatriculaInmobiliaria value for this TipoEntradaAgregarMatriculaAlerta.
	 *
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 */
	public void setNumMatriculaInmobiliaria(int numMatriculaInmobiliaria)
	{
		this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaAgregarMatriculaAlerta))
			return false;

		TipoEntradaAgregarMatriculaAlerta other = (TipoEntradaAgregarMatriculaAlerta)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true && (this.idAlerta == other.getIdAlerta())
				&& (((this.codCirculoRegistral == null) && (other.getCodCirculoRegistral() == null))
				|| ((this.codCirculoRegistral != null)
				&& this.codCirculoRegistral.equals(other.getCodCirculoRegistral())))
				&& (this.numMatriculaInmobiliaria == other.getNumMatriculaInmobiliaria());
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
		_hashCode += getIdAlerta();

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

		_hashCode += getNumMatriculaInmobiliaria();
		__hashCodeCalc = false;

		return _hashCode;
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
}
