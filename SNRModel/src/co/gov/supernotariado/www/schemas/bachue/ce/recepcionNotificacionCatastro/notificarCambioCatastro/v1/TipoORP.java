/**
 * TipoORP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1;



/**
 * Clase que contiene todos las propiedades TipoORP.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 16/03/2020
 */
public class TipoORP implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1794821122731056937L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoORP.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "tipoORP"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "codCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matriculas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "matriculas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "tipoNumMatriculas"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod circulo registral. */
	/* corresponde a la ORIP donde se ha registrado
	 *                                 el
	 *                                 predio */
	private java.lang.String codCirculoRegistral;

	/** Propiedad matriculas. */
	private co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoNumMatriculas[] matriculas;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo ORP.
	 */
	public TipoORP()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo ORP.
	 *
	 * @param codCirculoRegistral de cod circulo registral
	 * @param matriculas de matriculas
	 */
	public TipoORP(
	    java.lang.String codCirculoRegistral,
	    co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoNumMatriculas[] matriculas
	)
	{
		this.codCirculoRegistral     = codCirculoRegistral;
		this.matriculas              = matriculas;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoORP.
	 *
	 * @param codCirculoRegistral   * corresponde a la ORIP donde se ha registrado
	     *                                 el
	     *                                 predio
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoORP.
	 *
	 * @return codCirculoRegistral   * corresponde a la ORIP donde se ha registrado
	     *                                 el
	     *                                 predio
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
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
	 * Sets the matriculas value for this TipoORP.
	 *
	 * @param matriculas de matriculas
	 */
	public void setMatriculas(
	    co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoNumMatriculas[] matriculas
	)
	{
		this.matriculas = matriculas;
	}

	/**
	 * Cambia el valor de matriculas.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setMatriculas(
	    int i,
	    co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoNumMatriculas _value
	)
	{
		this.matriculas[i] = _value;
	}

	/**
	 * Gets the matriculas value for this TipoORP.
	 *
	 * @return matriculas
	 */
	public co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoNumMatriculas[] getMatriculas()
	{
		return matriculas;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas.
	 *
	 * @param i de i
	 * @return el valor de matriculas
	 */
	public co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoNumMatriculas getMatriculas(
	    int i
	)
	{
		return this.matriculas[i];
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
		if(!(obj instanceof TipoORP))
			return false;

		TipoORP other = (TipoORP)obj;

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

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

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
