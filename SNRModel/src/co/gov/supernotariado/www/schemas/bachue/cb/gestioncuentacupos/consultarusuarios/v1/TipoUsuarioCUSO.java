/**
 * TipoUsuarioCUSO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1;



/**
 * The Class TipoUsuarioCUSO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoUsuarioCUSO implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7596880025020161855L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoUsuarioCUSO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "tipoUsuarioCUSO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "tipoDocUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numDocUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "numDocUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerNombreUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "primerNombreUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoNombreUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "segundoNombreUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerApellidoUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "primerApellidoUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoApellidoUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "segundoApellidoUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("correoElectronicoCorporativoUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "correoElectronicoCorporativoUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1", "estado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaCreacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "fechaCreacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaCancelacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "fechaCancelacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1", "estado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The correo electronico corporativo usuario. */
	private java.lang.String correoElectronicoCorporativoUsuario;

	/** The estado. */
	private java.lang.String estado;

	/** The estado 2. */
	private java.lang.String estado2;

	/** The fecha cancelacion. */
	private java.util.Calendar fechaCancelacion;

	/** The fecha creacion. */
	private java.util.Calendar fechaCreacion;

	/** The num doc usuario. */
	private java.lang.String numDocUsuario;

	/** The primer apellido usuario. */
	private java.lang.String primerApellidoUsuario;

	/** The primer nombre usuario. */
	private java.lang.String primerNombreUsuario;

	/** The segundo apellido usuario. */
	private java.lang.String segundoApellidoUsuario;

	/** The segundo nombre usuario. */
	private java.lang.String segundoNombreUsuario;

	/** The tipo doc usuario. */
	private java.lang.String tipoDocUsuario;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo usuario CUSO.
	 */
	public TipoUsuarioCUSO()
	{
	}

	/**
	 * Instantiates a new tipo usuario CUSO.
	 *
	 * @param tipoDocUsuario the tipo doc usuario
	 * @param numDocUsuario the num doc usuario
	 * @param primerNombreUsuario the primer nombre usuario
	 * @param segundoNombreUsuario the segundo nombre usuario
	 * @param primerApellidoUsuario the primer apellido usuario
	 * @param segundoApellidoUsuario the segundo apellido usuario
	 * @param correoElectronicoCorporativoUsuario the correo electronico corporativo usuario
	 * @param estado the estado
	 * @param fechaCreacion the fecha creacion
	 * @param fechaCancelacion the fecha cancelacion
	 * @param estado2 the estado 2
	 */
	public TipoUsuarioCUSO(
	    java.lang.String tipoDocUsuario, java.lang.String numDocUsuario, java.lang.String primerNombreUsuario,
	    java.lang.String segundoNombreUsuario, java.lang.String primerApellidoUsuario,
	    java.lang.String segundoApellidoUsuario, java.lang.String correoElectronicoCorporativoUsuario,
	    java.lang.String estado, java.util.Calendar fechaCreacion, java.util.Calendar fechaCancelacion,
	    java.lang.String estado2
	)
	{
		this.tipoDocUsuario                          = tipoDocUsuario;
		this.numDocUsuario                           = numDocUsuario;
		this.primerNombreUsuario                     = primerNombreUsuario;
		this.segundoNombreUsuario                    = segundoNombreUsuario;
		this.primerApellidoUsuario                   = primerApellidoUsuario;
		this.segundoApellidoUsuario                  = segundoApellidoUsuario;
		this.correoElectronicoCorporativoUsuario     = correoElectronicoCorporativoUsuario;
		this.estado                                  = estado;
		this.fechaCreacion                           = fechaCreacion;
		this.fechaCancelacion                        = fechaCancelacion;
		this.estado2                                 = estado2;
	}

	/**
	 * Gets the tipoDocUsuario value for this TipoUsuarioCUSO.
	 *
	 * @return tipoDocUsuario
	 */
	public java.lang.String getTipoDocUsuario()
	{
		return tipoDocUsuario;
	}

	/**
	 * Sets the tipoDocUsuario value for this TipoUsuarioCUSO.
	 *
	 * @param tipoDocUsuario the new tipo doc usuario
	 */
	public void setTipoDocUsuario(java.lang.String tipoDocUsuario)
	{
		this.tipoDocUsuario = tipoDocUsuario;
	}

	/**
	 * Gets the numDocUsuario value for this TipoUsuarioCUSO.
	 *
	 * @return numDocUsuario
	 */
	public java.lang.String getNumDocUsuario()
	{
		return numDocUsuario;
	}

	/**
	 * Sets the numDocUsuario value for this TipoUsuarioCUSO.
	 *
	 * @param numDocUsuario the new num doc usuario
	 */
	public void setNumDocUsuario(java.lang.String numDocUsuario)
	{
		this.numDocUsuario = numDocUsuario;
	}

	/**
	 * Gets the primerNombreUsuario value for this TipoUsuarioCUSO.
	 *
	 * @return primerNombreUsuario
	 */
	public java.lang.String getPrimerNombreUsuario()
	{
		return primerNombreUsuario;
	}

	/**
	 * Sets the primerNombreUsuario value for this TipoUsuarioCUSO.
	 *
	 * @param primerNombreUsuario the new primer nombre usuario
	 */
	public void setPrimerNombreUsuario(java.lang.String primerNombreUsuario)
	{
		this.primerNombreUsuario = primerNombreUsuario;
	}

	/**
	 * Gets the segundoNombreUsuario value for this TipoUsuarioCUSO.
	 *
	 * @return segundoNombreUsuario
	 */
	public java.lang.String getSegundoNombreUsuario()
	{
		return segundoNombreUsuario;
	}

	/**
	 * Sets the segundoNombreUsuario value for this TipoUsuarioCUSO.
	 *
	 * @param segundoNombreUsuario the new segundo nombre usuario
	 */
	public void setSegundoNombreUsuario(java.lang.String segundoNombreUsuario)
	{
		this.segundoNombreUsuario = segundoNombreUsuario;
	}

	/**
	 * Gets the primerApellidoUsuario value for this TipoUsuarioCUSO.
	 *
	 * @return primerApellidoUsuario
	 */
	public java.lang.String getPrimerApellidoUsuario()
	{
		return primerApellidoUsuario;
	}

	/**
	 * Sets the primerApellidoUsuario value for this TipoUsuarioCUSO.
	 *
	 * @param primerApellidoUsuario the new primer apellido usuario
	 */
	public void setPrimerApellidoUsuario(java.lang.String primerApellidoUsuario)
	{
		this.primerApellidoUsuario = primerApellidoUsuario;
	}

	/**
	 * Gets the segundoApellidoUsuario value for this TipoUsuarioCUSO.
	 *
	 * @return segundoApellidoUsuario
	 */
	public java.lang.String getSegundoApellidoUsuario()
	{
		return segundoApellidoUsuario;
	}

	/**
	 * Sets the segundoApellidoUsuario value for this TipoUsuarioCUSO.
	 *
	 * @param segundoApellidoUsuario the new segundo apellido usuario
	 */
	public void setSegundoApellidoUsuario(java.lang.String segundoApellidoUsuario)
	{
		this.segundoApellidoUsuario = segundoApellidoUsuario;
	}

	/**
	 * Gets the correoElectronicoCorporativoUsuario value for this TipoUsuarioCUSO.
	 *
	 * @return correoElectronicoCorporativoUsuario
	 */
	public java.lang.String getCorreoElectronicoCorporativoUsuario()
	{
		return correoElectronicoCorporativoUsuario;
	}

	/**
	 * Sets the correoElectronicoCorporativoUsuario value for this TipoUsuarioCUSO.
	 *
	 * @param correoElectronicoCorporativoUsuario the new correo electronico corporativo usuario
	 */
	public void setCorreoElectronicoCorporativoUsuario(java.lang.String correoElectronicoCorporativoUsuario)
	{
		this.correoElectronicoCorporativoUsuario = correoElectronicoCorporativoUsuario;
	}

	/**
	 * Gets the estado value for this TipoUsuarioCUSO.
	 *
	 * @return estado
	 */
	public java.lang.String getEstado()
	{
		return estado;
	}

	/**
	 * Sets the estado value for this TipoUsuarioCUSO.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(java.lang.String estado)
	{
		this.estado = estado;
	}

	/**
	 * Gets the fechaCreacion value for this TipoUsuarioCUSO.
	 *
	 * @return fechaCreacion
	 */
	public java.util.Calendar getFechaCreacion()
	{
		return fechaCreacion;
	}

	/**
	 * Sets the fechaCreacion value for this TipoUsuarioCUSO.
	 *
	 * @param fechaCreacion the new fecha creacion
	 */
	public void setFechaCreacion(java.util.Calendar fechaCreacion)
	{
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Gets the fechaCancelacion value for this TipoUsuarioCUSO.
	 *
	 * @return fechaCancelacion
	 */
	public java.util.Calendar getFechaCancelacion()
	{
		return fechaCancelacion;
	}

	/**
	 * Sets the fechaCancelacion value for this TipoUsuarioCUSO.
	 *
	 * @param fechaCancelacion the new fecha cancelacion
	 */
	public void setFechaCancelacion(java.util.Calendar fechaCancelacion)
	{
		this.fechaCancelacion = fechaCancelacion;
	}

	/**
	 * Gets the estado2 value for this TipoUsuarioCUSO.
	 *
	 * @return estado2
	 */
	public java.lang.String getEstado2()
	{
		return estado2;
	}

	/**
	 * Sets the estado2 value for this TipoUsuarioCUSO.
	 *
	 * @param estado2 the new estado 2
	 */
	public void setEstado2(java.lang.String estado2)
	{
		this.estado2 = estado2;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoUsuarioCUSO))
			return false;

		TipoUsuarioCUSO other = (TipoUsuarioCUSO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoDocUsuario == null) && (other.getTipoDocUsuario() == null))
				|| ((this.tipoDocUsuario != null) && this.tipoDocUsuario.equals(other.getTipoDocUsuario())))
				&& (((this.numDocUsuario == null) && (other.getNumDocUsuario() == null))
				|| ((this.numDocUsuario != null) && this.numDocUsuario.equals(other.getNumDocUsuario())))
				&& (((this.primerNombreUsuario == null) && (other.getPrimerNombreUsuario() == null))
				|| ((this.primerNombreUsuario != null)
				&& this.primerNombreUsuario.equals(other.getPrimerNombreUsuario())))
				&& (((this.segundoNombreUsuario == null) && (other.getSegundoNombreUsuario() == null))
				|| ((this.segundoNombreUsuario != null)
				&& this.segundoNombreUsuario.equals(other.getSegundoNombreUsuario())))
				&& (((this.primerApellidoUsuario == null) && (other.getPrimerApellidoUsuario() == null))
				|| ((this.primerApellidoUsuario != null)
				&& this.primerApellidoUsuario.equals(other.getPrimerApellidoUsuario())))
				&& (((this.segundoApellidoUsuario == null) && (other.getSegundoApellidoUsuario() == null))
				|| ((this.segundoApellidoUsuario != null)
				&& this.segundoApellidoUsuario.equals(other.getSegundoApellidoUsuario())))
				&& (((this.correoElectronicoCorporativoUsuario == null)
				&& (other.getCorreoElectronicoCorporativoUsuario() == null))
				|| ((this.correoElectronicoCorporativoUsuario != null)
				&& this.correoElectronicoCorporativoUsuario.equals(other.getCorreoElectronicoCorporativoUsuario())))
				&& (((this.estado == null) && (other.getEstado() == null))
				|| ((this.estado != null) && this.estado.equals(other.getEstado())))
				&& (((this.fechaCreacion == null) && (other.getFechaCreacion() == null))
				|| ((this.fechaCreacion != null) && this.fechaCreacion.equals(other.getFechaCreacion())))
				&& (((this.fechaCancelacion == null) && (other.getFechaCancelacion() == null))
				|| ((this.fechaCancelacion != null) && this.fechaCancelacion.equals(other.getFechaCancelacion())))
				&& (((this.estado2 == null) && (other.getEstado2() == null))
				|| ((this.estado2 != null) && this.estado2.equals(other.getEstado2())));
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

		if(getTipoDocUsuario() != null)
			_hashCode += getTipoDocUsuario().hashCode();

		if(getNumDocUsuario() != null)
			_hashCode += getNumDocUsuario().hashCode();

		if(getPrimerNombreUsuario() != null)
			_hashCode += getPrimerNombreUsuario().hashCode();

		if(getSegundoNombreUsuario() != null)
			_hashCode += getSegundoNombreUsuario().hashCode();

		if(getPrimerApellidoUsuario() != null)
			_hashCode += getPrimerApellidoUsuario().hashCode();

		if(getSegundoApellidoUsuario() != null)
			_hashCode += getSegundoApellidoUsuario().hashCode();

		if(getCorreoElectronicoCorporativoUsuario() != null)
			_hashCode += getCorreoElectronicoCorporativoUsuario().hashCode();

		if(getEstado() != null)
			_hashCode += getEstado().hashCode();

		if(getFechaCreacion() != null)
			_hashCode += getFechaCreacion().hashCode();

		if(getFechaCancelacion() != null)
			_hashCode += getFechaCancelacion().hashCode();

		if(getEstado2() != null)
			_hashCode += getEstado2().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}

	/**
	 * Return type metadata object.
	 *
	 * @return the type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Get Custom Serializer.
	 *
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the serializer
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
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}
}
