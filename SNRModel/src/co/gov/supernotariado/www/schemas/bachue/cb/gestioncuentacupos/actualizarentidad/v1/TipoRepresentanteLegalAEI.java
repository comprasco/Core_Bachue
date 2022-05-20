/**
 * TipoRepresentanteLegalAEI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1;



/**
 * The Class TipoRepresentanteLegalAEI.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoRepresentanteLegalAEI implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8090853650770254886L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoRepresentanteLegalAEI.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "tipoRepresentanteLegalAEI"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumentoRepresentanteLegal");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "tipoDocumentoRepresentanteLegal"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroDocumentoRepresentanteLegal");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "numeroDocumentoRepresentanteLegal"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerNombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "primerNombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoNombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "segundoNombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("departamentoEmpresa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "departamentoEmpresa"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerApellido");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "primerApellido"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoApellido");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "segundoApellido"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("telefono");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "telefono"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("correoElectronicoCorporativoUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "correoElectronicoCorporativoUsuario"
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

	/** The departamento empresa. */
	private java.lang.String departamentoEmpresa;

	/** The numero documento representante legal. */
	private java.lang.String numeroDocumentoRepresentanteLegal;

	/** The primer apellido. */
	private java.lang.String primerApellido;

	/** The primer nombre. */
	private java.lang.String primerNombre;

	/** The segundo apellido. */
	private java.lang.String segundoApellido;

	/** The segundo nombre. */
	private java.lang.String segundoNombre;

	/** The telefono. */
	private java.lang.String telefono;

	/** The tipo documento representante legal. */
	private java.lang.String tipoDocumentoRepresentanteLegal;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo representante legal AEI.
	 */
	public TipoRepresentanteLegalAEI()
	{
	}

	/**
	 * Instantiates a new tipo representante legal AEI.
	 *
	 * @param tipoDocumentoRepresentanteLegal the tipo documento representante legal
	 * @param numeroDocumentoRepresentanteLegal the numero documento representante legal
	 * @param primerNombre the primer nombre
	 * @param segundoNombre the segundo nombre
	 * @param departamentoEmpresa the departamento empresa
	 * @param primerApellido the primer apellido
	 * @param segundoApellido the segundo apellido
	 * @param telefono the telefono
	 * @param correoElectronicoCorporativoUsuario the correo electronico corporativo usuario
	 */
	public TipoRepresentanteLegalAEI(
	    java.lang.String tipoDocumentoRepresentanteLegal, java.lang.String numeroDocumentoRepresentanteLegal,
	    java.lang.String primerNombre, java.lang.String segundoNombre, java.lang.String departamentoEmpresa,
	    java.lang.String primerApellido, java.lang.String segundoApellido, java.lang.String telefono,
	    java.lang.String correoElectronicoCorporativoUsuario
	)
	{
		this.tipoDocumentoRepresentanteLegal         = tipoDocumentoRepresentanteLegal;
		this.numeroDocumentoRepresentanteLegal       = numeroDocumentoRepresentanteLegal;
		this.primerNombre                            = primerNombre;
		this.segundoNombre                           = segundoNombre;
		this.departamentoEmpresa                     = departamentoEmpresa;
		this.primerApellido                          = primerApellido;
		this.segundoApellido                         = segundoApellido;
		this.telefono                                = telefono;
		this.correoElectronicoCorporativoUsuario     = correoElectronicoCorporativoUsuario;
	}

	/**
	 * Gets the tipoDocumentoRepresentanteLegal value for this TipoRepresentanteLegalAEI.
	 *
	 * @return tipoDocumentoRepresentanteLegal
	 */
	public java.lang.String getTipoDocumentoRepresentanteLegal()
	{
		return tipoDocumentoRepresentanteLegal;
	}

	/**
	 * Sets the tipoDocumentoRepresentanteLegal value for this TipoRepresentanteLegalAEI.
	 *
	 * @param tipoDocumentoRepresentanteLegal the new tipo documento representante legal
	 */
	public void setTipoDocumentoRepresentanteLegal(java.lang.String tipoDocumentoRepresentanteLegal)
	{
		this.tipoDocumentoRepresentanteLegal = tipoDocumentoRepresentanteLegal;
	}

	/**
	 * Gets the numeroDocumentoRepresentanteLegal value for this TipoRepresentanteLegalAEI.
	 *
	 * @return numeroDocumentoRepresentanteLegal
	 */
	public java.lang.String getNumeroDocumentoRepresentanteLegal()
	{
		return numeroDocumentoRepresentanteLegal;
	}

	/**
	 * Sets the numeroDocumentoRepresentanteLegal value for this TipoRepresentanteLegalAEI.
	 *
	 * @param numeroDocumentoRepresentanteLegal the new numero documento representante legal
	 */
	public void setNumeroDocumentoRepresentanteLegal(java.lang.String numeroDocumentoRepresentanteLegal)
	{
		this.numeroDocumentoRepresentanteLegal = numeroDocumentoRepresentanteLegal;
	}

	/**
	 * Gets the primerNombre value for this TipoRepresentanteLegalAEI.
	 *
	 * @return primerNombre
	 */
	public java.lang.String getPrimerNombre()
	{
		return primerNombre;
	}

	/**
	 * Sets the primerNombre value for this TipoRepresentanteLegalAEI.
	 *
	 * @param primerNombre the new primer nombre
	 */
	public void setPrimerNombre(java.lang.String primerNombre)
	{
		this.primerNombre = primerNombre;
	}

	/**
	 * Gets the segundoNombre value for this TipoRepresentanteLegalAEI.
	 *
	 * @return segundoNombre
	 */
	public java.lang.String getSegundoNombre()
	{
		return segundoNombre;
	}

	/**
	 * Sets the segundoNombre value for this TipoRepresentanteLegalAEI.
	 *
	 * @param segundoNombre the new segundo nombre
	 */
	public void setSegundoNombre(java.lang.String segundoNombre)
	{
		this.segundoNombre = segundoNombre;
	}

	/**
	 * Gets the departamentoEmpresa value for this TipoRepresentanteLegalAEI.
	 *
	 * @return departamentoEmpresa
	 */
	public java.lang.String getDepartamentoEmpresa()
	{
		return departamentoEmpresa;
	}

	/**
	 * Sets the departamentoEmpresa value for this TipoRepresentanteLegalAEI.
	 *
	 * @param departamentoEmpresa the new departamento empresa
	 */
	public void setDepartamentoEmpresa(java.lang.String departamentoEmpresa)
	{
		this.departamentoEmpresa = departamentoEmpresa;
	}

	/**
	 * Gets the primerApellido value for this TipoRepresentanteLegalAEI.
	 *
	 * @return primerApellido
	 */
	public java.lang.String getPrimerApellido()
	{
		return primerApellido;
	}

	/**
	 * Sets the primerApellido value for this TipoRepresentanteLegalAEI.
	 *
	 * @param primerApellido the new primer apellido
	 */
	public void setPrimerApellido(java.lang.String primerApellido)
	{
		this.primerApellido = primerApellido;
	}

	/**
	 * Gets the segundoApellido value for this TipoRepresentanteLegalAEI.
	 *
	 * @return segundoApellido
	 */
	public java.lang.String getSegundoApellido()
	{
		return segundoApellido;
	}

	/**
	 * Sets the segundoApellido value for this TipoRepresentanteLegalAEI.
	 *
	 * @param segundoApellido the new segundo apellido
	 */
	public void setSegundoApellido(java.lang.String segundoApellido)
	{
		this.segundoApellido = segundoApellido;
	}

	/**
	 * Gets the telefono value for this TipoRepresentanteLegalAEI.
	 *
	 * @return telefono
	 */
	public java.lang.String getTelefono()
	{
		return telefono;
	}

	/**
	 * Sets the telefono value for this TipoRepresentanteLegalAEI.
	 *
	 * @param telefono the new telefono
	 */
	public void setTelefono(java.lang.String telefono)
	{
		this.telefono = telefono;
	}

	/**
	 * Gets the correoElectronicoCorporativoUsuario value for this TipoRepresentanteLegalAEI.
	 *
	 * @return correoElectronicoCorporativoUsuario
	 */
	public java.lang.String getCorreoElectronicoCorporativoUsuario()
	{
		return correoElectronicoCorporativoUsuario;
	}

	/**
	 * Sets the correoElectronicoCorporativoUsuario value for this TipoRepresentanteLegalAEI.
	 *
	 * @param correoElectronicoCorporativoUsuario the new correo electronico corporativo usuario
	 */
	public void setCorreoElectronicoCorporativoUsuario(java.lang.String correoElectronicoCorporativoUsuario)
	{
		this.correoElectronicoCorporativoUsuario = correoElectronicoCorporativoUsuario;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoRepresentanteLegalAEI))
			return false;

		TipoRepresentanteLegalAEI other = (TipoRepresentanteLegalAEI)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoDocumentoRepresentanteLegal == null)
				&& (other.getTipoDocumentoRepresentanteLegal() == null))
				|| ((this.tipoDocumentoRepresentanteLegal != null)
				&& this.tipoDocumentoRepresentanteLegal.equals(other.getTipoDocumentoRepresentanteLegal())))
				&& (((this.numeroDocumentoRepresentanteLegal == null)
				&& (other.getNumeroDocumentoRepresentanteLegal() == null))
				|| ((this.numeroDocumentoRepresentanteLegal != null)
				&& this.numeroDocumentoRepresentanteLegal.equals(other.getNumeroDocumentoRepresentanteLegal())))
				&& (((this.primerNombre == null) && (other.getPrimerNombre() == null))
				|| ((this.primerNombre != null) && this.primerNombre.equals(other.getPrimerNombre())))
				&& (((this.segundoNombre == null) && (other.getSegundoNombre() == null))
				|| ((this.segundoNombre != null) && this.segundoNombre.equals(other.getSegundoNombre())))
				&& (((this.departamentoEmpresa == null) && (other.getDepartamentoEmpresa() == null))
				|| ((this.departamentoEmpresa != null)
				&& this.departamentoEmpresa.equals(other.getDepartamentoEmpresa())))
				&& (((this.primerApellido == null) && (other.getPrimerApellido() == null))
				|| ((this.primerApellido != null) && this.primerApellido.equals(other.getPrimerApellido())))
				&& (((this.segundoApellido == null) && (other.getSegundoApellido() == null))
				|| ((this.segundoApellido != null) && this.segundoApellido.equals(other.getSegundoApellido())))
				&& (((this.telefono == null) && (other.getTelefono() == null))
				|| ((this.telefono != null) && this.telefono.equals(other.getTelefono())))
				&& (((this.correoElectronicoCorporativoUsuario == null)
				&& (other.getCorreoElectronicoCorporativoUsuario() == null))
				|| ((this.correoElectronicoCorporativoUsuario != null)
				&& this.correoElectronicoCorporativoUsuario.equals(other.getCorreoElectronicoCorporativoUsuario())));
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

		if(getTipoDocumentoRepresentanteLegal() != null)
			_hashCode += getTipoDocumentoRepresentanteLegal().hashCode();

		if(getNumeroDocumentoRepresentanteLegal() != null)
			_hashCode += getNumeroDocumentoRepresentanteLegal().hashCode();

		if(getPrimerNombre() != null)
			_hashCode += getPrimerNombre().hashCode();

		if(getSegundoNombre() != null)
			_hashCode += getSegundoNombre().hashCode();

		if(getDepartamentoEmpresa() != null)
			_hashCode += getDepartamentoEmpresa().hashCode();

		if(getPrimerApellido() != null)
			_hashCode += getPrimerApellido().hashCode();

		if(getSegundoApellido() != null)
			_hashCode += getSegundoApellido().hashCode();

		if(getTelefono() != null)
			_hashCode += getTelefono().hashCode();

		if(getCorreoElectronicoCorporativoUsuario() != null)
			_hashCode += getCorreoElectronicoCorporativoUsuario().hashCode();

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
