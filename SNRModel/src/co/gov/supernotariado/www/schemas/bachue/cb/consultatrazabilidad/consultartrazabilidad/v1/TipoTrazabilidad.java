/**
 * TipoTrazabilidad.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1;


/**
 * Clase que contiene todos las propiedades TipoTrazabilidad.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/09/2019
 */
public class TipoTrazabilidad implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6808987380263329159L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoTrazabilidad.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tipoTrazabilidad"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("etapa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "etapa"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreEtapa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "nombreEtapa"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estadoActividad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "estadoActividad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("usuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "usuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaAsignacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "fechaAsignacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaReparto");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "fechaReparto"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaInicioEtapa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "fechaInicioEtapa"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaFinalEtapa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "fechaFinalEtapa"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad estado actividad. */
	/* Estado en el que se encuentra el trámite */
	private java.lang.String estadoActividad;

	/** Propiedad etapa. */
	/* Consecutivo numérico de la etapa donde se
	 *                                 encuentra el trámite */
	private java.lang.String etapa;

	/** Propiedad fecha asignacion. */
	/* Fecha de asignación del trámite */
	private java.util.Calendar fechaAsignacion;

	/** Propiedad fecha final etapa. */
	/* Fecha en la que finalizó inicio la etapa en la
	 *                                 que se encuentra el trámite */
	private java.util.Calendar fechaFinalEtapa;

	/** Propiedad fecha inicio etapa. */
	/* Fecha en la que inicio la etapa en la que se
	 *                                 encuentra el trámite */
	private java.util.Calendar fechaInicioEtapa;

	/** Propiedad fecha reparto. */
	/* Fecha en la que se realizó el reparto */
	private java.util.Calendar fechaReparto;

	/** Propiedad nombre etapa. */
	/* Nombre de la etapa. Ej: Proceso de Registro
	 *                                 terminado, Certificados */
	private java.lang.String nombreEtapa;

	/** Propiedad usuario. */
	/* Usuario de red del cajero quien generó el
	 *                                 trámite */
	private java.lang.String usuario;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo trazabilidad.
	 */
	public TipoTrazabilidad()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo trazabilidad.
	 *
	 * @param etapa correspondiente al valor de etapa
	 * @param nombreEtapa correspondiente al valor de nombre etapa
	 * @param estadoActividad correspondiente al valor de estado actividad
	 * @param usuario correspondiente al valor de usuario
	 * @param fechaAsignacion correspondiente al valor de fecha asignacion
	 * @param fechaReparto correspondiente al valor de fecha reparto
	 * @param fechaInicioEtapa correspondiente al valor de fecha inicio etapa
	 * @param fechaFinalEtapa correspondiente al valor de fecha final etapa
	 */
	public TipoTrazabilidad(
	    java.lang.String etapa, java.lang.String nombreEtapa, java.lang.String estadoActividad, java.lang.String usuario,
	    java.util.Calendar fechaAsignacion, java.util.Calendar fechaReparto, java.util.Calendar fechaInicioEtapa,
	    java.util.Calendar fechaFinalEtapa
	)
	{
		this.etapa                = etapa;
		this.nombreEtapa          = nombreEtapa;
		this.estadoActividad      = estadoActividad;
		this.usuario              = usuario;
		this.fechaAsignacion      = fechaAsignacion;
		this.fechaReparto         = fechaReparto;
		this.fechaInicioEtapa     = fechaInicioEtapa;
		this.fechaFinalEtapa      = fechaFinalEtapa;
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
	 * Sets the estadoActividad value for this TipoTrazabilidad.
	 *
	 * @param estadoActividad   * Estado en el que se encuentra el trámite
	 */
	public void setEstadoActividad(java.lang.String estadoActividad)
	{
		this.estadoActividad = estadoActividad;
	}

	/**
	 * Gets the estadoActividad value for this TipoTrazabilidad.
	 *
	 * @return estadoActividad   * Estado en el que se encuentra el trámite
	 */
	public java.lang.String getEstadoActividad()
	{
		return estadoActividad;
	}

	/**
	 * Sets the etapa value for this TipoTrazabilidad.
	 *
	 * @param etapa   * Consecutivo numérico de la etapa donde se
	     *                                 encuentra el trámite
	 */
	public void setEtapa(java.lang.String etapa)
	{
		this.etapa = etapa;
	}

	/**
	 * Gets the etapa value for this TipoTrazabilidad.
	 *
	 * @return etapa   * Consecutivo numérico de la etapa donde se
	     *                                 encuentra el trámite
	 */
	public java.lang.String getEtapa()
	{
		return etapa;
	}

	/**
	 * Sets the fechaAsignacion value for this TipoTrazabilidad.
	 *
	 * @param fechaAsignacion   * Fecha de asignación del trámite
	 */
	public void setFechaAsignacion(java.util.Calendar fechaAsignacion)
	{
		this.fechaAsignacion = fechaAsignacion;
	}

	/**
	 * Gets the fechaAsignacion value for this TipoTrazabilidad.
	 *
	 * @return fechaAsignacion   * Fecha de asignación del trámite
	 */
	public java.util.Calendar getFechaAsignacion()
	{
		return fechaAsignacion;
	}

	/**
	 * Sets the fechaFinalEtapa value for this TipoTrazabilidad.
	 *
	 * @param fechaFinalEtapa   * Fecha en la que finalizó inicio la etapa en la
	     *                                 que se encuentra el trámite
	 */
	public void setFechaFinalEtapa(java.util.Calendar fechaFinalEtapa)
	{
		this.fechaFinalEtapa = fechaFinalEtapa;
	}

	/**
	 * Gets the fechaFinalEtapa value for this TipoTrazabilidad.
	 *
	 * @return fechaFinalEtapa   * Fecha en la que finalizó inicio la etapa en la
	     *                                 que se encuentra el trámite
	 */
	public java.util.Calendar getFechaFinalEtapa()
	{
		return fechaFinalEtapa;
	}

	/**
	 * Sets the fechaInicioEtapa value for this TipoTrazabilidad.
	 *
	 * @param fechaInicioEtapa   * Fecha en la que inicio la etapa en la que se
	     *                                 encuentra el trámite
	 */
	public void setFechaInicioEtapa(java.util.Calendar fechaInicioEtapa)
	{
		this.fechaInicioEtapa = fechaInicioEtapa;
	}

	/**
	 * Gets the fechaInicioEtapa value for this TipoTrazabilidad.
	 *
	 * @return fechaInicioEtapa   * Fecha en la que inicio la etapa en la que se
	     *                                 encuentra el trámite
	 */
	public java.util.Calendar getFechaInicioEtapa()
	{
		return fechaInicioEtapa;
	}

	/**
	 * Sets the fechaReparto value for this TipoTrazabilidad.
	 *
	 * @param fechaReparto   * Fecha en la que se realizó el reparto
	 */
	public void setFechaReparto(java.util.Calendar fechaReparto)
	{
		this.fechaReparto = fechaReparto;
	}

	/**
	 * Gets the fechaReparto value for this TipoTrazabilidad.
	 *
	 * @return fechaReparto   * Fecha en la que se realizó el reparto
	 */
	public java.util.Calendar getFechaReparto()
	{
		return fechaReparto;
	}

	/**
	 * Sets the nombreEtapa value for this TipoTrazabilidad.
	 *
	 * @param nombreEtapa   * Nombre de la etapa. Ej: Proceso de Registro
	     *                                 terminado, Certificados
	 */
	public void setNombreEtapa(java.lang.String nombreEtapa)
	{
		this.nombreEtapa = nombreEtapa;
	}

	/**
	 * Gets the nombreEtapa value for this TipoTrazabilidad.
	 *
	 * @return nombreEtapa   * Nombre de la etapa. Ej: Proceso de Registro
	     *                                 terminado, Certificados
	 */
	public java.lang.String getNombreEtapa()
	{
		return nombreEtapa;
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

	/**
	 * Sets the usuario value for this TipoTrazabilidad.
	 *
	 * @param usuario   * Usuario de red del cajero quien generó el
	     *                                 trámite
	 */
	public void setUsuario(java.lang.String usuario)
	{
		this.usuario = usuario;
	}

	/**
	 * Gets the usuario value for this TipoTrazabilidad.
	 *
	 * @return usuario   * Usuario de red del cajero quien generó el
	     *                                 trámite
	 */
	public java.lang.String getUsuario()
	{
		return usuario;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoTrazabilidad))
			return false;

		TipoTrazabilidad other = (TipoTrazabilidad)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.etapa == null) && (other.getEtapa() == null))
				|| ((this.etapa != null) && this.etapa.equals(other.getEtapa())))
				&& (((this.nombreEtapa == null) && (other.getNombreEtapa() == null))
				|| ((this.nombreEtapa != null) && this.nombreEtapa.equals(other.getNombreEtapa())))
				&& (((this.estadoActividad == null) && (other.getEstadoActividad() == null))
				|| ((this.estadoActividad != null) && this.estadoActividad.equals(other.getEstadoActividad())))
				&& (((this.usuario == null) && (other.getUsuario() == null))
				|| ((this.usuario != null) && this.usuario.equals(other.getUsuario())))
				&& (((this.fechaAsignacion == null) && (other.getFechaAsignacion() == null))
				|| ((this.fechaAsignacion != null) && this.fechaAsignacion.equals(other.getFechaAsignacion())))
				&& (((this.fechaReparto == null) && (other.getFechaReparto() == null))
				|| ((this.fechaReparto != null) && this.fechaReparto.equals(other.getFechaReparto())))
				&& (((this.fechaInicioEtapa == null) && (other.getFechaInicioEtapa() == null))
				|| ((this.fechaInicioEtapa != null) && this.fechaInicioEtapa.equals(other.getFechaInicioEtapa())))
				&& (((this.fechaFinalEtapa == null) && (other.getFechaFinalEtapa() == null))
				|| ((this.fechaFinalEtapa != null) && this.fechaFinalEtapa.equals(other.getFechaFinalEtapa())));
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

		if(getEtapa() != null)
			_hashCode += getEtapa().hashCode();

		if(getNombreEtapa() != null)
			_hashCode += getNombreEtapa().hashCode();

		if(getEstadoActividad() != null)
			_hashCode += getEstadoActividad().hashCode();

		if(getUsuario() != null)
			_hashCode += getUsuario().hashCode();

		if(getFechaAsignacion() != null)
			_hashCode += getFechaAsignacion().hashCode();

		if(getFechaReparto() != null)
			_hashCode += getFechaReparto().hashCode();

		if(getFechaInicioEtapa() != null)
			_hashCode += getFechaInicioEtapa().hashCode();

		if(getFechaFinalEtapa() != null)
			_hashCode += getFechaFinalEtapa().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
