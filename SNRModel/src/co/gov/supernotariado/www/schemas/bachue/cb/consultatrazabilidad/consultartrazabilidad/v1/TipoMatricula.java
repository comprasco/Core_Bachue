/**
 * TipoMatricula.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1;


/**
 * Clase que contiene todos las propiedades TipoMatricula.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/09/2019
 */
public class TipoMatricula implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3765539321258720714L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoMatricula.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tipoMatricula"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("orip");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "orip"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroMatricula");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "numeroMatricula"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "estado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("alertaVigente");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "alertaVigente"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad alerta vigente. */
	/* Si tiene alertas vigentes, posibles valores:
	 *                                 -
	 *                                 SI
	 *                                 - NO */
	private java.lang.String alertaVigente;

	/** Propiedad estado. */
	/* Estado de la matricula */
	private java.lang.String estado;

	/** Propiedad numero matricula. */
	/* Número de la matricula inmobiliaria */
	private java.lang.String numeroMatricula;

	/** Propiedad orip. */
	/* Nombre de la ORIP asignada al turno */
	private java.lang.String orip;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo matricula.
	 */
	public TipoMatricula()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo matricula.
	 *
	 * @param orip correspondiente al valor de orip
	 * @param numeroMatricula correspondiente al valor de numero matricula
	 * @param estado correspondiente al valor de estado
	 * @param alertaVigente correspondiente al valor de alerta vigente
	 */
	public TipoMatricula(
	    java.lang.String orip, java.lang.String numeroMatricula, java.lang.String estado, java.lang.String alertaVigente
	)
	{
		this.orip                = orip;
		this.numeroMatricula     = numeroMatricula;
		this.estado              = estado;
		this.alertaVigente       = alertaVigente;
	}

	/**
	 * Sets the alertaVigente value for this TipoMatricula.
	 *
	 * @param alertaVigente   * Si tiene alertas vigentes, posibles valores:
	     *                                 -
	     *                                 SI
	     *                                 - NO
	 */
	public void setAlertaVigente(java.lang.String alertaVigente)
	{
		this.alertaVigente = alertaVigente;
	}

	/**
	 * Gets the alertaVigente value for this TipoMatricula.
	 *
	 * @return alertaVigente   * Si tiene alertas vigentes, posibles valores:
	     *                                 -
	     *                                 SI
	     *                                 - NO
	 */
	public java.lang.String getAlertaVigente()
	{
		return alertaVigente;
	}

	/**
	 * Get Custom Deserializer.
	 *
	 * @param mechType correspondiente al valor de mech type
	 * @param _javaType correspondiente al valor de java type
	 * @param _xmlType correspondiente al valor de xml type
	 * @return el valor de deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Sets the estado value for this TipoMatricula.
	 *
	 * @param estado   * Estado de la matricula
	 */
	public void setEstado(java.lang.String estado)
	{
		this.estado = estado;
	}

	/**
	 * Gets the estado value for this TipoMatricula.
	 *
	 * @return estado   * Estado de la matricula
	 */
	public java.lang.String getEstado()
	{
		return estado;
	}

	/**
	 * Sets the numeroMatricula value for this TipoMatricula.
	 *
	 * @param numeroMatricula   * Número de la matricula inmobiliaria
	 */
	public void setNumeroMatricula(java.lang.String numeroMatricula)
	{
		this.numeroMatricula = numeroMatricula;
	}

	/**
	 * Gets the numeroMatricula value for this TipoMatricula.
	 *
	 * @return numeroMatricula   * Número de la matricula inmobiliaria
	 */
	public java.lang.String getNumeroMatricula()
	{
		return numeroMatricula;
	}

	/**
	 * Sets the orip value for this TipoMatricula.
	 *
	 * @param orip   * Nombre de la ORIP asignada al turno
	 */
	public void setOrip(java.lang.String orip)
	{
		this.orip = orip;
	}

	/**
	 * Gets the orip value for this TipoMatricula.
	 *
	 * @return orip   * Nombre de la ORIP asignada al turno
	 */
	public java.lang.String getOrip()
	{
		return orip;
	}

	/**
	 * Get Custom Serializer.
	 *
	 * @param mechType correspondiente al valor de mech type
	 * @param _javaType correspondiente al valor de java type
	 * @param _xmlType correspondiente al valor de xml type
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
		if(!(obj instanceof TipoMatricula))
			return false;

		TipoMatricula other = (TipoMatricula)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.orip == null) && (other.getOrip() == null))
				|| ((this.orip != null) && this.orip.equals(other.getOrip())))
				&& (((this.numeroMatricula == null) && (other.getNumeroMatricula() == null))
				|| ((this.numeroMatricula != null) && this.numeroMatricula.equals(other.getNumeroMatricula())))
				&& (((this.estado == null) && (other.getEstado() == null))
				|| ((this.estado != null) && this.estado.equals(other.getEstado())))
				&& (((this.alertaVigente == null) && (other.getAlertaVigente() == null))
				|| ((this.alertaVigente != null) && this.alertaVigente.equals(other.getAlertaVigente())));
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

		if(getOrip() != null)
			_hashCode += getOrip().hashCode();

		if(getNumeroMatricula() != null)
			_hashCode += getNumeroMatricula().hashCode();

		if(getEstado() != null)
			_hashCode += getEstado().hashCode();

		if(getAlertaVigente() != null)
			_hashCode += getAlertaVigente().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
