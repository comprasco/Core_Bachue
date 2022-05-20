/**
 * TipoSalidaConsultarSaldosNotaCreditoNotasCreditos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarSaldosNotaCreditoNotasCreditos.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 10/09/2020
 */
public class TipoSalidaConsultarSaldosNotaCreditoNotasCreditos implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1204098498760498458L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarSaldosNotaCreditoNotasCreditos.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
		        ">tipoSalidaConsultarSaldosNotaCredito>notasCreditos"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("notaCredito");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
		        "notaCredito"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
		        ">>tipoSalidaConsultarSaldosNotaCredito>notasCreditos>notaCredito"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad nota credito. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCreditoNotasCreditosNotaCredito notaCredito;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar saldos nota credito notas creditos.
	 */
	public TipoSalidaConsultarSaldosNotaCreditoNotasCreditos()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar saldos nota credito notas creditos.
	 *
	 * @param notaCredito de nota credito
	 */
	public TipoSalidaConsultarSaldosNotaCreditoNotasCreditos(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCreditoNotasCreditosNotaCredito notaCredito
	)
	{
		this.notaCredito = notaCredito;
	}

	/**
	 * Gets the notaCredito value for this TipoSalidaConsultarSaldosNotaCreditoNotasCreditos.
	 *
	 * @return notaCredito
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCreditoNotasCreditosNotaCredito getNotaCredito()
	{
		return notaCredito;
	}

	/**
	 * Sets the notaCredito value for this TipoSalidaConsultarSaldosNotaCreditoNotasCreditos.
	 *
	 * @param notaCredito de nota credito
	 */
	public void setNotaCredito(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCreditoNotasCreditosNotaCredito notaCredito
	)
	{
		this.notaCredito = notaCredito;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultarSaldosNotaCreditoNotasCreditos))
			return false;

		TipoSalidaConsultarSaldosNotaCreditoNotasCreditos other = (TipoSalidaConsultarSaldosNotaCreditoNotasCreditos)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.notaCredito == null) && (other.getNotaCredito() == null))
				|| ((this.notaCredito != null) && this.notaCredito.equals(other.getNotaCredito())));
		__equalsCalc     = null;

		return _equals;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getNotaCredito() != null)
			_hashCode += getNotaCredito().hashCode();

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
