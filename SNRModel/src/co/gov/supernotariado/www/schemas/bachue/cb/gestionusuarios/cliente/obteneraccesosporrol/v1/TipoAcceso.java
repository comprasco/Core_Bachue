/**
 * TipoAcceso.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obteneraccesosporrol.v1;

public class TipoAcceso implements java.io.Serializable
{
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoAcceso.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
		        "tipoAcceso"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idOpcion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
		        "idOpcion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("opcion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1", "opcion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
		        "descripcion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("opcionPadre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
		        "opcionPadre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1", "tipo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("url");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1", "url"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaDesde");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
		        "fechaDesde"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaHasta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
		        "fechaHasta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("activo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1", "activo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object __equalsCalc   = null;
	private java.lang.String activo;
	private java.lang.String descripcion;
	private java.lang.String fechaDesde;
	private java.lang.String fechaHasta;
	private java.lang.String idOpcion;
	private java.lang.String opcion;
	private java.lang.String opcionPadre;
	private java.lang.String tipo;
	private java.lang.String url;
	private boolean          __hashCodeCalc = false;

	public TipoAcceso()
	{
	}

	public TipoAcceso(
	    java.lang.String idOpcion, java.lang.String opcion, java.lang.String descripcion, java.lang.String opcionPadre,
	    java.lang.String tipo, java.lang.String url, java.lang.String fechaDesde, java.lang.String fechaHasta,
	    java.lang.String activo
	)
	{
		this.idOpcion        = idOpcion;
		this.opcion          = opcion;
		this.descripcion     = descripcion;
		this.opcionPadre     = opcionPadre;
		this.tipo            = tipo;
		this.url             = url;
		this.fechaDesde      = fechaDesde;
		this.fechaHasta      = fechaHasta;
		this.activo          = activo;
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

	/**
	 * Sets the activo value for this TipoAcceso.
	 *
	 * @param activo
	 */
	public void setActivo(java.lang.String activo)
	{
		this.activo = activo;
	}

	/**
	 * Gets the activo value for this TipoAcceso.
	 *
	 * @return activo
	 */
	public java.lang.String getActivo()
	{
		return activo;
	}

	/**
	 * Sets the descripcion value for this TipoAcceso.
	 *
	 * @param descripcion
	 */
	public void setDescripcion(java.lang.String descripcion)
	{
		this.descripcion = descripcion;
	}

	/**
	 * Gets the descripcion value for this TipoAcceso.
	 *
	 * @return descripcion
	 */
	public java.lang.String getDescripcion()
	{
		return descripcion;
	}

	/**
	 * Sets the fechaDesde value for this TipoAcceso.
	 *
	 * @param fechaDesde
	 */
	public void setFechaDesde(java.lang.String fechaDesde)
	{
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fechaDesde value for this TipoAcceso.
	 *
	 * @return fechaDesde
	 */
	public java.lang.String getFechaDesde()
	{
		return fechaDesde;
	}

	/**
	 * Sets the fechaHasta value for this TipoAcceso.
	 *
	 * @param fechaHasta
	 */
	public void setFechaHasta(java.lang.String fechaHasta)
	{
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Gets the fechaHasta value for this TipoAcceso.
	 *
	 * @return fechaHasta
	 */
	public java.lang.String getFechaHasta()
	{
		return fechaHasta;
	}

	/**
	 * Sets the idOpcion value for this TipoAcceso.
	 *
	 * @param idOpcion
	 */
	public void setIdOpcion(java.lang.String idOpcion)
	{
		this.idOpcion = idOpcion;
	}

	/**
	 * Gets the idOpcion value for this TipoAcceso.
	 *
	 * @return idOpcion
	 */
	public java.lang.String getIdOpcion()
	{
		return idOpcion;
	}

	/**
	 * Sets the opcion value for this TipoAcceso.
	 *
	 * @param opcion
	 */
	public void setOpcion(java.lang.String opcion)
	{
		this.opcion = opcion;
	}

	/**
	 * Gets the opcion value for this TipoAcceso.
	 *
	 * @return opcion
	 */
	public java.lang.String getOpcion()
	{
		return opcion;
	}

	/**
	 * Sets the opcionPadre value for this TipoAcceso.
	 *
	 * @param opcionPadre
	 */
	public void setOpcionPadre(java.lang.String opcionPadre)
	{
		this.opcionPadre = opcionPadre;
	}

	/**
	 * Gets the opcionPadre value for this TipoAcceso.
	 *
	 * @return opcionPadre
	 */
	public java.lang.String getOpcionPadre()
	{
		return opcionPadre;
	}

	/**
	 * Sets the tipo value for this TipoAcceso.
	 *
	 * @param tipo
	 */
	public void setTipo(java.lang.String tipo)
	{
		this.tipo = tipo;
	}

	/**
	 * Gets the tipo value for this TipoAcceso.
	 *
	 * @return tipo
	 */
	public java.lang.String getTipo()
	{
		return tipo;
	}

	/**
	 * Sets the url value for this TipoAcceso.
	 *
	 * @param url
	 */
	public void setUrl(java.lang.String url)
	{
		this.url = url;
	}

	/**
	 * Gets the url value for this TipoAcceso.
	 *
	 * @return url
	 */
	public java.lang.String getUrl()
	{
		return url;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoAcceso))
			return false;

		TipoAcceso other = (TipoAcceso)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.idOpcion == null) && (other.getIdOpcion() == null))
				|| ((this.idOpcion != null) && this.idOpcion.equals(other.getIdOpcion())))
				&& (((this.opcion == null) && (other.getOpcion() == null))
				|| ((this.opcion != null) && this.opcion.equals(other.getOpcion())))
				&& (((this.descripcion == null) && (other.getDescripcion() == null))
				|| ((this.descripcion != null) && this.descripcion.equals(other.getDescripcion())))
				&& (((this.opcionPadre == null) && (other.getOpcionPadre() == null))
				|| ((this.opcionPadre != null) && this.opcionPadre.equals(other.getOpcionPadre())))
				&& (((this.tipo == null) && (other.getTipo() == null))
				|| ((this.tipo != null) && this.tipo.equals(other.getTipo())))
				&& (((this.url == null) && (other.getUrl() == null))
				|| ((this.url != null) && this.url.equals(other.getUrl())))
				&& (((this.fechaDesde == null) && (other.getFechaDesde() == null))
				|| ((this.fechaDesde != null) && this.fechaDesde.equals(other.getFechaDesde())))
				&& (((this.fechaHasta == null) && (other.getFechaHasta() == null))
				|| ((this.fechaHasta != null) && this.fechaHasta.equals(other.getFechaHasta())))
				&& (((this.activo == null) && (other.getActivo() == null))
				|| ((this.activo != null) && this.activo.equals(other.getActivo())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getIdOpcion() != null)
			_hashCode += getIdOpcion().hashCode();

		if(getOpcion() != null)
			_hashCode += getOpcion().hashCode();

		if(getDescripcion() != null)
			_hashCode += getDescripcion().hashCode();

		if(getOpcionPadre() != null)
			_hashCode += getOpcionPadre().hashCode();

		if(getTipo() != null)
			_hashCode += getTipo().hashCode();

		if(getUrl() != null)
			_hashCode += getUrl().hashCode();

		if(getFechaDesde() != null)
			_hashCode += getFechaDesde().hashCode();

		if(getFechaHasta() != null)
			_hashCode += getFechaHasta().hashCode();

		if(getActivo() != null)
			_hashCode += getActivo().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
