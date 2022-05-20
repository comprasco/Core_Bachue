/**
 * TipoEntradaBuscarSolicitudes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaBuscarSolicitudes.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaBuscarSolicitudes implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3347878644805048643L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaBuscarSolicitudes.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        "tipoEntradaBuscarSolicitudes"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        "modulo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("solicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        "solicitante"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        ">tipoEntradaBuscarSolicitudes>solicitante"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("servicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        "servicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nir");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        "nir"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaDesdeBusqueda");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        "fechaDesdeBusqueda"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaHastaBusqueda");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        "fechaHastaBusqueda"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                                             __equalsCalc       =
		null;
	
	/** Propiedad fecha desde busqueda. */
	private java.lang.String                                                                                                                             fechaDesdeBusqueda;
	
	/** Propiedad fecha hasta busqueda. */
	private java.lang.String                                                                                                                             fechaHastaBusqueda;
	
	/** Propiedad modulo. */
	private java.lang.String                                                                                                                             modulo;
	
	/** Propiedad nir. */
	private java.lang.String                                                                                                                             nir;
	
	/** Propiedad servicio. */
	private java.lang.String                                                                                                                             servicio;
	
	/** Propiedad solicitante. */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1.TipoEntradaBuscarSolicitudesSolicitante solicitante;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                                      __hashCodeCalc     =
		false;

	/**
	 * Instancia un nuevo objeto tipo entrada buscar solicitudes.
	 */
	public TipoEntradaBuscarSolicitudes()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada buscar solicitudes.
	 *
	 * @param modulo de modulo
	 * @param solicitante de solicitante
	 * @param servicio de servicio
	 * @param nir de nir
	 * @param fechaDesdeBusqueda de fecha desde busqueda
	 * @param fechaHastaBusqueda de fecha hasta busqueda
	 */
	public TipoEntradaBuscarSolicitudes(
	    java.lang.String modulo,
	    co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1.TipoEntradaBuscarSolicitudesSolicitante solicitante,
	    java.lang.String servicio, java.lang.String nir, java.lang.String fechaDesdeBusqueda,
	    java.lang.String fechaHastaBusqueda
	)
	{
		this.modulo                 = modulo;
		this.solicitante            = solicitante;
		this.servicio               = servicio;
		this.nir                    = nir;
		this.fechaDesdeBusqueda     = fechaDesdeBusqueda;
		this.fechaHastaBusqueda     = fechaHastaBusqueda;
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
	 * Sets the fechaDesdeBusqueda value for this TipoEntradaBuscarSolicitudes.
	 *
	 * @param fechaDesdeBusqueda de fecha desde busqueda
	 */
	public void setFechaDesdeBusqueda(java.lang.String fechaDesdeBusqueda)
	{
		this.fechaDesdeBusqueda = fechaDesdeBusqueda;
	}

	/**
	 * Gets the fechaDesdeBusqueda value for this TipoEntradaBuscarSolicitudes.
	 *
	 * @return fechaDesdeBusqueda
	 */
	public java.lang.String getFechaDesdeBusqueda()
	{
		return fechaDesdeBusqueda;
	}

	/**
	 * Sets the fechaHastaBusqueda value for this TipoEntradaBuscarSolicitudes.
	 *
	 * @param fechaHastaBusqueda de fecha hasta busqueda
	 */
	public void setFechaHastaBusqueda(java.lang.String fechaHastaBusqueda)
	{
		this.fechaHastaBusqueda = fechaHastaBusqueda;
	}

	/**
	 * Gets the fechaHastaBusqueda value for this TipoEntradaBuscarSolicitudes.
	 *
	 * @return fechaHastaBusqueda
	 */
	public java.lang.String getFechaHastaBusqueda()
	{
		return fechaHastaBusqueda;
	}

	/**
	 * Sets the modulo value for this TipoEntradaBuscarSolicitudes.
	 *
	 * @param modulo de modulo
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the modulo value for this TipoEntradaBuscarSolicitudes.
	 *
	 * @return modulo
	 */
	public java.lang.String getModulo()
	{
		return modulo;
	}

	/**
	 * Sets the nir value for this TipoEntradaBuscarSolicitudes.
	 *
	 * @param nir de nir
	 */
	public void setNir(java.lang.String nir)
	{
		this.nir = nir;
	}

	/**
	 * Gets the nir value for this TipoEntradaBuscarSolicitudes.
	 *
	 * @return nir
	 */
	public java.lang.String getNir()
	{
		return nir;
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
	 * Sets the servicio value for this TipoEntradaBuscarSolicitudes.
	 *
	 * @param servicio de servicio
	 */
	public void setServicio(java.lang.String servicio)
	{
		this.servicio = servicio;
	}

	/**
	 * Gets the servicio value for this TipoEntradaBuscarSolicitudes.
	 *
	 * @return servicio
	 */
	public java.lang.String getServicio()
	{
		return servicio;
	}

	/**
	 * Sets the solicitante value for this TipoEntradaBuscarSolicitudes.
	 *
	 * @param solicitante de solicitante
	 */
	public void setSolicitante(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1.TipoEntradaBuscarSolicitudesSolicitante solicitante
	)
	{
		this.solicitante = solicitante;
	}

	/**
	 * Gets the solicitante value for this TipoEntradaBuscarSolicitudes.
	 *
	 * @return solicitante
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1.TipoEntradaBuscarSolicitudesSolicitante getSolicitante()
	{
		return solicitante;
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
		if(!(obj instanceof TipoEntradaBuscarSolicitudes))
			return false;

		TipoEntradaBuscarSolicitudes other = (TipoEntradaBuscarSolicitudes)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.modulo == null) && (other.getModulo() == null))
				|| ((this.modulo != null) && this.modulo.equals(other.getModulo())))
				&& (((this.solicitante == null) && (other.getSolicitante() == null))
				|| ((this.solicitante != null) && this.solicitante.equals(other.getSolicitante())))
				&& (((this.servicio == null) && (other.getServicio() == null))
				|| ((this.servicio != null) && this.servicio.equals(other.getServicio())))
				&& (((this.nir == null) && (other.getNir() == null))
				|| ((this.nir != null) && this.nir.equals(other.getNir())))
				&& (((this.fechaDesdeBusqueda == null) && (other.getFechaDesdeBusqueda() == null))
				|| ((this.fechaDesdeBusqueda != null) && this.fechaDesdeBusqueda.equals(other.getFechaDesdeBusqueda())))
				&& (((this.fechaHastaBusqueda == null) && (other.getFechaHastaBusqueda() == null))
				|| ((this.fechaHastaBusqueda != null) && this.fechaHastaBusqueda.equals(other.getFechaHastaBusqueda())));
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

		if(getModulo() != null)
			_hashCode += getModulo().hashCode();

		if(getSolicitante() != null)
			_hashCode += getSolicitante().hashCode();

		if(getServicio() != null)
			_hashCode += getServicio().hashCode();

		if(getNir() != null)
			_hashCode += getNir().hashCode();

		if(getFechaDesdeBusqueda() != null)
			_hashCode += getFechaDesdeBusqueda().hashCode();

		if(getFechaHastaBusqueda() != null)
			_hashCode += getFechaHastaBusqueda().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
