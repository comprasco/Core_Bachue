/**
 * TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredioPropietariosPropietario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredioPropietariosPropietario.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/04/2020
 */
public class TipoPropietario implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6040128352732493184L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoPropietario.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
		        ">>>>tipoSalidaConsultarCambioPropietario>anotacionesPredio>anotacionPredio>propietarios>propietario"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
		        "tipoPersona"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumentoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
		        "tipoDocumentoPersona"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numDocumentoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
		        "numDocumentoPersona"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("DRR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
		        "DRR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("porcentajeParticipacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
		        "porcentajeParticipacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad drr. */
	private java.lang.String DRR;

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad num documento persona. */
	private java.lang.String numDocumentoPersona;

	/** Propiedad porcentaje participacion. */
	private java.lang.String porcentajeParticipacion;

	/** Propiedad tipo documento persona. */
	private java.lang.String tipoDocumentoPersona;

	/** Propiedad tipo persona. */
	private java.lang.String tipoPersona;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar cambio propietario anotaciones predio anotacion predio propietarios propietario.
	 */
	public TipoPropietario()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar cambio propietario anotaciones predio anotacion predio propietarios propietario.
	 *
	 * @param tipoPersona de tipo persona
	 * @param tipoDocumentoPersona de tipo documento persona
	 * @param numDocumentoPersona de num documento persona
	 * @param DRR de drr
	 * @param porcentajeParticipacion de porcentaje participacion
	 */
	public TipoPropietario(
	    java.lang.String tipoPersona, java.lang.String tipoDocumentoPersona, java.lang.String numDocumentoPersona,
	    java.lang.String DRR, java.lang.String porcentajeParticipacion
	)
	{
		this.tipoPersona                 = tipoPersona;
		this.tipoDocumentoPersona        = tipoDocumentoPersona;
		this.numDocumentoPersona         = numDocumentoPersona;
		this.DRR                         = DRR;
		this.porcentajeParticipacion     = porcentajeParticipacion;
	}

	/**
	 * Sets the DRR value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredioPropietariosPropietario.
	 *
	 * @param DRR de drr
	 */
	public void setDRR(java.lang.String DRR)
	{
		this.DRR = DRR;
	}

	/**
	 * Gets the DRR value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredioPropietariosPropietario.
	 *
	 * @return DRR
	 */
	public java.lang.String getDRR()
	{
		return DRR;
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
	 * Sets the numDocumentoPersona value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredioPropietariosPropietario.
	 *
	 * @param numDocumentoPersona de num documento persona
	 */
	public void setNumDocumentoPersona(java.lang.String numDocumentoPersona)
	{
		this.numDocumentoPersona = numDocumentoPersona;
	}

	/**
	 * Gets the numDocumentoPersona value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredioPropietariosPropietario.
	 *
	 * @return numDocumentoPersona
	 */
	public java.lang.String getNumDocumentoPersona()
	{
		return numDocumentoPersona;
	}

	/**
	 * Sets the porcentajeParticipacion value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredioPropietariosPropietario.
	 *
	 * @param porcentajeParticipacion de porcentaje participacion
	 */
	public void setPorcentajeParticipacion(java.lang.String porcentajeParticipacion)
	{
		this.porcentajeParticipacion = porcentajeParticipacion;
	}

	/**
	 * Gets the porcentajeParticipacion value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredioPropietariosPropietario.
	 *
	 * @return porcentajeParticipacion
	 */
	public java.lang.String getPorcentajeParticipacion()
	{
		return porcentajeParticipacion;
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
	 * Sets the tipoDocumentoPersona value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredioPropietariosPropietario.
	 *
	 * @param tipoDocumentoPersona de tipo documento persona
	 */
	public void setTipoDocumentoPersona(java.lang.String tipoDocumentoPersona)
	{
		this.tipoDocumentoPersona = tipoDocumentoPersona;
	}

	/**
	 * Gets the tipoDocumentoPersona value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredioPropietariosPropietario.
	 *
	 * @return tipoDocumentoPersona
	 */
	public java.lang.String getTipoDocumentoPersona()
	{
		return tipoDocumentoPersona;
	}

	/**
	 * Sets the tipoPersona value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredioPropietariosPropietario.
	 *
	 * @param tipoPersona de tipo persona
	 */
	public void setTipoPersona(java.lang.String tipoPersona)
	{
		this.tipoPersona = tipoPersona;
	}

	/**
	 * Gets the tipoPersona value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredioPropietariosPropietario.
	 *
	 * @return tipoPersona
	 */
	public java.lang.String getTipoPersona()
	{
		return tipoPersona;
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
		if(
		    !(obj instanceof TipoPropietario)
		)
			return false;

		TipoPropietario other = (TipoPropietario)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoPersona == null) && (other.getTipoPersona() == null))
				|| ((this.tipoPersona != null) && this.tipoPersona.equals(other.getTipoPersona())))
				&& (((this.tipoDocumentoPersona == null) && (other.getTipoDocumentoPersona() == null))
				|| ((this.tipoDocumentoPersona != null)
				&& this.tipoDocumentoPersona.equals(other.getTipoDocumentoPersona())))
				&& (((this.numDocumentoPersona == null) && (other.getNumDocumentoPersona() == null))
				|| ((this.numDocumentoPersona != null)
				&& this.numDocumentoPersona.equals(other.getNumDocumentoPersona())))
				&& (((this.DRR == null) && (other.getDRR() == null))
				|| ((this.DRR != null) && this.DRR.equals(other.getDRR())))
				&& (((this.porcentajeParticipacion == null) && (other.getPorcentajeParticipacion() == null))
				|| ((this.porcentajeParticipacion != null)
				&& this.porcentajeParticipacion.equals(other.getPorcentajeParticipacion())));
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

		if(getTipoPersona() != null)
			_hashCode += getTipoPersona().hashCode();

		if(getTipoDocumentoPersona() != null)
			_hashCode += getTipoDocumentoPersona().hashCode();

		if(getNumDocumentoPersona() != null)
			_hashCode += getNumDocumentoPersona().hashCode();

		if(getDRR() != null)
			_hashCode += getDRR().hashCode();

		if(getPorcentajeParticipacion() != null)
			_hashCode += getPorcentajeParticipacion().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
