/**
 * TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1;

public class TipoSalidaConsultarRRRMatriculasListaDerechosDerecho implements java.io.Serializable
{
	private static final long serialVersionUID = -1214521282230884497L;

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        ">>tipoSalidaConsultarRRRMatriculas>listaDerechos>derecho"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numAnotacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "numAnotacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("comentario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "comentario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaAnotacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "fechaAnotacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codNaturalezaJuridicaFolio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "codNaturalezaJuridicaFolio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomNaturalezaJuridicaFolio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "nomNaturalezaJuridicaFolio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("dominioDRR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "dominioDRR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("intervinientes");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "intervinientes"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        ">>>>tipoSalidaConsultarRRRMatriculas>listaDerechos>derecho>intervinientes>interviniente"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "interviniente"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object                                                                                                                                                      __equalsCalc               =
		null;
	private java.lang.String                                                                                                                                                      codNaturalezaJuridicaFolio;
	private java.lang.String                                                                                                                                                      comentario;
	private java.lang.String                                                                                                                                                      dominioDRR;
	private java.util.Calendar                                                                                                                                                    fechaAnotacion;
	private java.lang.String                                                                                                                                                      nomNaturalezaJuridicaFolio;
	private java.lang.String                                                                                                                                                      numAnotacion;
	private co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaDerechosDerechoIntervinientesInterviniente[] intervinientes;
	private boolean                                                                                                                                                               __hashCodeCalc             =
		false;

	public TipoSalidaConsultarRRRMatriculasListaDerechosDerecho()
	{
	}

	public TipoSalidaConsultarRRRMatriculasListaDerechosDerecho(
	    java.lang.String numAnotacion, java.lang.String comentario, java.util.Calendar fechaAnotacion,
	    java.lang.String codNaturalezaJuridicaFolio, java.lang.String nomNaturalezaJuridicaFolio,
	    java.lang.String dominioDRR,
	    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaDerechosDerechoIntervinientesInterviniente[] intervinientes
	)
	{
		this.numAnotacion                   = numAnotacion;
		this.comentario                     = comentario;
		this.fechaAnotacion                 = fechaAnotacion;
		this.codNaturalezaJuridicaFolio     = codNaturalezaJuridicaFolio;
		this.nomNaturalezaJuridicaFolio     = nomNaturalezaJuridicaFolio;
		this.dominioDRR                     = dominioDRR;
		this.intervinientes                 = intervinientes;
	}

	/**
	 * Sets the codNaturalezaJuridicaFolio value for this TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.
	 *
	 * @param codNaturalezaJuridicaFolio
	 */
	public void setCodNaturalezaJuridicaFolio(java.lang.String codNaturalezaJuridicaFolio)
	{
		this.codNaturalezaJuridicaFolio = codNaturalezaJuridicaFolio;
	}

	/**
	 * Gets the codNaturalezaJuridicaFolio value for this TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.
	 *
	 * @return codNaturalezaJuridicaFolio
	 */
	public java.lang.String getCodNaturalezaJuridicaFolio()
	{
		return codNaturalezaJuridicaFolio;
	}

	/**
	 * Sets the comentario value for this TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.
	 *
	 * @param comentario
	 */
	public void setComentario(java.lang.String comentario)
	{
		this.comentario = comentario;
	}

	/**
	 * Gets the comentario value for this TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.
	 *
	 * @return comentario
	 */
	public java.lang.String getComentario()
	{
		return comentario;
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
	 * Sets the dominioDRR value for this TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.
	 *
	 * @param dominioDRR
	 */
	public void setDominioDRR(java.lang.String dominioDRR)
	{
		this.dominioDRR = dominioDRR;
	}

	/**
	 * Gets the dominioDRR value for this TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.
	 *
	 * @return dominioDRR
	 */
	public java.lang.String getDominioDRR()
	{
		return dominioDRR;
	}

	/**
	 * Sets the fechaAnotacion value for this TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.
	 *
	 * @param fechaAnotacion
	 */
	public void setFechaAnotacion(java.util.Calendar fechaAnotacion)
	{
		this.fechaAnotacion = fechaAnotacion;
	}

	/**
	 * Gets the fechaAnotacion value for this TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.
	 *
	 * @return fechaAnotacion
	 */
	public java.util.Calendar getFechaAnotacion()
	{
		return fechaAnotacion;
	}

	/**
	 * Sets the intervinientes value for this TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.
	 *
	 * @param intervinientes
	 */
	public void setIntervinientes(
	    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaDerechosDerechoIntervinientesInterviniente[] intervinientes
	)
	{
		this.intervinientes = intervinientes;
	}

	/**
	 * Gets the intervinientes value for this TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.
	 *
	 * @return intervinientes
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaDerechosDerechoIntervinientesInterviniente[] getIntervinientes()
	{
		return intervinientes;
	}

	/**
	 * Sets the nomNaturalezaJuridicaFolio value for this TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.
	 *
	 * @param nomNaturalezaJuridicaFolio
	 */
	public void setNomNaturalezaJuridicaFolio(java.lang.String nomNaturalezaJuridicaFolio)
	{
		this.nomNaturalezaJuridicaFolio = nomNaturalezaJuridicaFolio;
	}

	/**
	 * Gets the nomNaturalezaJuridicaFolio value for this TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.
	 *
	 * @return nomNaturalezaJuridicaFolio
	 */
	public java.lang.String getNomNaturalezaJuridicaFolio()
	{
		return nomNaturalezaJuridicaFolio;
	}

	/**
	 * Sets the numAnotacion value for this TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.
	 *
	 * @param numAnotacion
	 */
	public void setNumAnotacion(java.lang.String numAnotacion)
	{
		this.numAnotacion = numAnotacion;
	}

	/**
	 * Gets the numAnotacion value for this TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.
	 *
	 * @return numAnotacion
	 */
	public java.lang.String getNumAnotacion()
	{
		return numAnotacion;
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
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultarRRRMatriculasListaDerechosDerecho))
			return false;

		TipoSalidaConsultarRRRMatriculasListaDerechosDerecho other = (TipoSalidaConsultarRRRMatriculasListaDerechosDerecho)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numAnotacion == null) && (other.getNumAnotacion() == null))
				|| ((this.numAnotacion != null) && this.numAnotacion.equals(other.getNumAnotacion())))
				&& (((this.comentario == null) && (other.getComentario() == null))
				|| ((this.comentario != null) && this.comentario.equals(other.getComentario())))
				&& (((this.fechaAnotacion == null) && (other.getFechaAnotacion() == null))
				|| ((this.fechaAnotacion != null) && this.fechaAnotacion.equals(other.getFechaAnotacion())))
				&& (((this.codNaturalezaJuridicaFolio == null) && (other.getCodNaturalezaJuridicaFolio() == null))
				|| ((this.codNaturalezaJuridicaFolio != null)
				&& this.codNaturalezaJuridicaFolio.equals(other.getCodNaturalezaJuridicaFolio())))
				&& (((this.nomNaturalezaJuridicaFolio == null) && (other.getNomNaturalezaJuridicaFolio() == null))
				|| ((this.nomNaturalezaJuridicaFolio != null)
				&& this.nomNaturalezaJuridicaFolio.equals(other.getNomNaturalezaJuridicaFolio())))
				&& (((this.dominioDRR == null) && (other.getDominioDRR() == null))
				|| ((this.dominioDRR != null) && this.dominioDRR.equals(other.getDominioDRR())))
				&& (((this.intervinientes == null) && (other.getIntervinientes() == null))
				|| ((this.intervinientes != null)
				&& java.util.Arrays.equals(this.intervinientes, other.getIntervinientes())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getNumAnotacion() != null)
			_hashCode += getNumAnotacion().hashCode();

		if(getComentario() != null)
			_hashCode += getComentario().hashCode();

		if(getFechaAnotacion() != null)
			_hashCode += getFechaAnotacion().hashCode();

		if(getCodNaturalezaJuridicaFolio() != null)
			_hashCode += getCodNaturalezaJuridicaFolio().hashCode();

		if(getNomNaturalezaJuridicaFolio() != null)
			_hashCode += getNomNaturalezaJuridicaFolio().hashCode();

		if(getDominioDRR() != null)
			_hashCode += getDominioDRR().hashCode();

		if(getIntervinientes() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getIntervinientes()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getIntervinientes(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
