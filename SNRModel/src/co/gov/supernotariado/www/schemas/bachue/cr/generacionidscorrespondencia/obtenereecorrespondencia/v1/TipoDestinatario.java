/**
 * TipoDestinatario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1;

public class TipoDestinatario implements java.io.Serializable
{
	private static final long serialVersionUID = 3132477616523145767L;

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoDestinatario.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "tipoDestinatario"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocDestinatario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "tipoDocDestinatario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroDocDestinatario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "numeroDocDestinatario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerNombreDestinatario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "primerNombreDestinatario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoNombreDestinatario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "segundoNombreDestinatario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerApellidoDestinatario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "primerApellidoDestinatario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoApellidoDestinatario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "segundoApellidoDestinatario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("variables");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "variables"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "tipoVariable"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "variable"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object                                                                                                    __equalsCalc                =
		null;
	private java.lang.String                                                                                                    numeroDocDestinatario;
	private java.lang.String                                                                                                    primerApellidoDestinatario;
	private java.lang.String                                                                                                    primerNombreDestinatario;
	private java.lang.String                                                                                                    segundoApellidoDestinatario;
	private java.lang.String                                                                                                    segundoNombreDestinatario;
	private java.lang.String                                                                                                    tipoDocDestinatario;
	private co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoVariable[] variables;
	private boolean                                                                                                             __hashCodeCalc              =
		false;

	public TipoDestinatario()
	{
	}

	public TipoDestinatario(
	    java.lang.String tipoDocDestinatario, java.lang.String numeroDocDestinatario,
	    java.lang.String primerNombreDestinatario, java.lang.String segundoNombreDestinatario,
	    java.lang.String primerApellidoDestinatario, java.lang.String segundoApellidoDestinatario,
	    co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoVariable[] variables
	)
	{
		this.tipoDocDestinatario             = tipoDocDestinatario;
		this.numeroDocDestinatario           = numeroDocDestinatario;
		this.primerNombreDestinatario        = primerNombreDestinatario;
		this.segundoNombreDestinatario       = segundoNombreDestinatario;
		this.primerApellidoDestinatario      = primerApellidoDestinatario;
		this.segundoApellidoDestinatario     = segundoApellidoDestinatario;
		this.variables                       = variables;
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Sets the numeroDocDestinatario value for this TipoDestinatario.
	 *
	 * @param numeroDocDestinatario
	 */
	public void setNumeroDocDestinatario(java.lang.String numeroDocDestinatario)
	{
		this.numeroDocDestinatario = numeroDocDestinatario;
	}

	/**
	 * Gets the numeroDocDestinatario value for this TipoDestinatario.
	 *
	 * @return numeroDocDestinatario
	 */
	public java.lang.String getNumeroDocDestinatario()
	{
		return numeroDocDestinatario;
	}

	/**
	 * Sets the primerApellidoDestinatario value for this TipoDestinatario.
	 *
	 * @param primerApellidoDestinatario
	 */
	public void setPrimerApellidoDestinatario(java.lang.String primerApellidoDestinatario)
	{
		this.primerApellidoDestinatario = primerApellidoDestinatario;
	}

	/**
	 * Gets the primerApellidoDestinatario value for this TipoDestinatario.
	 *
	 * @return primerApellidoDestinatario
	 */
	public java.lang.String getPrimerApellidoDestinatario()
	{
		return primerApellidoDestinatario;
	}

	/**
	 * Sets the primerNombreDestinatario value for this TipoDestinatario.
	 *
	 * @param primerNombreDestinatario
	 */
	public void setPrimerNombreDestinatario(java.lang.String primerNombreDestinatario)
	{
		this.primerNombreDestinatario = primerNombreDestinatario;
	}

	/**
	 * Gets the primerNombreDestinatario value for this TipoDestinatario.
	 *
	 * @return primerNombreDestinatario
	 */
	public java.lang.String getPrimerNombreDestinatario()
	{
		return primerNombreDestinatario;
	}

	/**
	 * Sets the segundoApellidoDestinatario value for this TipoDestinatario.
	 *
	 * @param segundoApellidoDestinatario
	 */
	public void setSegundoApellidoDestinatario(java.lang.String segundoApellidoDestinatario)
	{
		this.segundoApellidoDestinatario = segundoApellidoDestinatario;
	}

	/**
	 * Gets the segundoApellidoDestinatario value for this TipoDestinatario.
	 *
	 * @return segundoApellidoDestinatario
	 */
	public java.lang.String getSegundoApellidoDestinatario()
	{
		return segundoApellidoDestinatario;
	}

	/**
	 * Sets the segundoNombreDestinatario value for this TipoDestinatario.
	 *
	 * @param segundoNombreDestinatario
	 */
	public void setSegundoNombreDestinatario(java.lang.String segundoNombreDestinatario)
	{
		this.segundoNombreDestinatario = segundoNombreDestinatario;
	}

	/**
	 * Gets the segundoNombreDestinatario value for this TipoDestinatario.
	 *
	 * @return segundoNombreDestinatario
	 */
	public java.lang.String getSegundoNombreDestinatario()
	{
		return segundoNombreDestinatario;
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Sets the tipoDocDestinatario value for this TipoDestinatario.
	 *
	 * @param tipoDocDestinatario
	 */
	public void setTipoDocDestinatario(java.lang.String tipoDocDestinatario)
	{
		this.tipoDocDestinatario = tipoDocDestinatario;
	}

	/**
	 * Gets the tipoDocDestinatario value for this TipoDestinatario.
	 *
	 * @return tipoDocDestinatario
	 */
	public java.lang.String getTipoDocDestinatario()
	{
		return tipoDocDestinatario;
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Sets the variables value for this TipoDestinatario.
	 *
	 * @param variables
	 */
	public void setVariables(
	    co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoVariable[] variables
	)
	{
		this.variables = variables;
	}

	/**
	 * Gets the variables value for this TipoDestinatario.
	 *
	 * @return variables
	 */
	public co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoVariable[] getVariables()
	{
		return variables;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoDestinatario))
			return false;

		TipoDestinatario other = (TipoDestinatario)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoDocDestinatario == null) && (other.getTipoDocDestinatario() == null))
				|| ((this.tipoDocDestinatario != null)
				&& this.tipoDocDestinatario.equals(other.getTipoDocDestinatario())))
				&& (((this.numeroDocDestinatario == null) && (other.getNumeroDocDestinatario() == null))
				|| ((this.numeroDocDestinatario != null)
				&& this.numeroDocDestinatario.equals(other.getNumeroDocDestinatario())))
				&& (((this.primerNombreDestinatario == null) && (other.getPrimerNombreDestinatario() == null))
				|| ((this.primerNombreDestinatario != null)
				&& this.primerNombreDestinatario.equals(other.getPrimerNombreDestinatario())))
				&& (((this.segundoNombreDestinatario == null) && (other.getSegundoNombreDestinatario() == null))
				|| ((this.segundoNombreDestinatario != null)
				&& this.segundoNombreDestinatario.equals(other.getSegundoNombreDestinatario())))
				&& (((this.primerApellidoDestinatario == null) && (other.getPrimerApellidoDestinatario() == null))
				|| ((this.primerApellidoDestinatario != null)
				&& this.primerApellidoDestinatario.equals(other.getPrimerApellidoDestinatario())))
				&& (((this.segundoApellidoDestinatario == null) && (other.getSegundoApellidoDestinatario() == null))
				|| ((this.segundoApellidoDestinatario != null)
				&& this.segundoApellidoDestinatario.equals(other.getSegundoApellidoDestinatario())))
				&& (((this.variables == null) && (other.getVariables() == null))
				|| ((this.variables != null) && java.util.Arrays.equals(this.variables, other.getVariables())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getTipoDocDestinatario() != null)
			_hashCode += getTipoDocDestinatario().hashCode();

		if(getNumeroDocDestinatario() != null)
			_hashCode += getNumeroDocDestinatario().hashCode();

		if(getPrimerNombreDestinatario() != null)
			_hashCode += getPrimerNombreDestinatario().hashCode();

		if(getSegundoNombreDestinatario() != null)
			_hashCode += getSegundoNombreDestinatario().hashCode();

		if(getPrimerApellidoDestinatario() != null)
			_hashCode += getPrimerApellidoDestinatario().hashCode();

		if(getSegundoApellidoDestinatario() != null)
			_hashCode += getSegundoApellidoDestinatario().hashCode();

		if(getVariables() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getVariables()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getVariables(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
