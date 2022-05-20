/**
 * TipoEntradaValidarSolicitudAlertaMasiva.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaValidarSolicitudAlertaMasiva.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaValidarSolicitudAlertaMasiva implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1110891026071375818L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaValidarSolicitudAlertaMasiva.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
		        "tipoEntradaValidarSolicitudAlertaMasiva"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
		        "modulo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
		        "tipoDocumento"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
		        ">tipoEntradaValidarSolicitudAlertaMasiva>tipoDocumento"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
		        "numeroDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerNombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
		        "primerNombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoNombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
		        "segundoNombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerApellido");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
		        "primerApellido"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoApellido");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
		        "segundoApellido"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("razonSocial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
		        "razonSocial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("oripSecuencia");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
		        "oripSecuencia"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matriculaInicial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
		        "matriculaInicial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matriculaFinal");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
		        "matriculaFinal"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("archivo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertamasiva/v1",
		        "archivo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                                                         __equalsCalc     =
		null;
	
	/** Propiedad matricula final. */
	private java.lang.String                                                                                                                                         matriculaFinal;
	
	/** Propiedad matricula inicial. */
	private java.lang.String                                                                                                                                         matriculaInicial;
	
	/** Propiedad modulo. */
	private java.lang.String                                                                                                                                         modulo;
	
	/** Propiedad numero documento. */
	private java.lang.String                                                                                                                                         numeroDocumento;
	
	/** Propiedad orip secuencia. */
	private java.lang.String                                                                                                                                         oripSecuencia;
	
	/** Propiedad primer apellido. */
	private java.lang.String                                                                                                                                         primerApellido;
	
	/** Propiedad primer nombre. */
	private java.lang.String                                                                                                                                         primerNombre;
	
	/** Propiedad razon social. */
	private java.lang.String                                                                                                                                         razonSocial;
	
	/** Propiedad segundo apellido. */
	private java.lang.String                                                                                                                                         segundoApellido;
	
	/** Propiedad segundo nombre. */
	private java.lang.String                                                                                                                                         segundoNombre;
	
	/** Propiedad tipo documento. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoEntradaValidarSolicitudAlertaMasivaTipoDocumento tipoDocumento;
	
	/** Propiedad archivo. */
	private byte[]                                                                                                                                                   archivo;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                                                  __hashCodeCalc   =
		false;

	/**
	 * Instancia un nuevo objeto tipo entrada validar solicitud alerta masiva.
	 */
	public TipoEntradaValidarSolicitudAlertaMasiva()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada validar solicitud alerta masiva.
	 *
	 * @param modulo de modulo
	 * @param tipoDocumento de tipo documento
	 * @param numeroDocumento de numero documento
	 * @param primerNombre de primer nombre
	 * @param segundoNombre de segundo nombre
	 * @param primerApellido de primer apellido
	 * @param segundoApellido de segundo apellido
	 * @param razonSocial de razon social
	 * @param oripSecuencia de orip secuencia
	 * @param matriculaInicial de matricula inicial
	 * @param matriculaFinal de matricula final
	 * @param archivo de archivo
	 */
	public TipoEntradaValidarSolicitudAlertaMasiva(
	    java.lang.String modulo,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoEntradaValidarSolicitudAlertaMasivaTipoDocumento tipoDocumento,
	    java.lang.String numeroDocumento, java.lang.String primerNombre, java.lang.String segundoNombre,
	    java.lang.String primerApellido, java.lang.String segundoApellido, java.lang.String razonSocial,
	    java.lang.String oripSecuencia, java.lang.String matriculaInicial, java.lang.String matriculaFinal,
	    byte[] archivo
	)
	{
		this.modulo               = modulo;
		this.tipoDocumento        = tipoDocumento;
		this.numeroDocumento      = numeroDocumento;
		this.primerNombre         = primerNombre;
		this.segundoNombre        = segundoNombre;
		this.primerApellido       = primerApellido;
		this.segundoApellido      = segundoApellido;
		this.razonSocial          = razonSocial;
		this.oripSecuencia        = oripSecuencia;
		this.matriculaInicial     = matriculaInicial;
		this.matriculaFinal       = matriculaFinal;
		this.archivo              = archivo;
	}

	/**
	 * Sets the archivo value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @param archivo de archivo
	 */
	public void setArchivo(byte[] archivo)
	{
		this.archivo = archivo;
	}

	/**
	 * Gets the archivo value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @return archivo
	 */
	public byte[] getArchivo()
	{
		return archivo;
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
	 * Sets the matriculaFinal value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @param matriculaFinal de matricula final
	 */
	public void setMatriculaFinal(java.lang.String matriculaFinal)
	{
		this.matriculaFinal = matriculaFinal;
	}

	/**
	 * Gets the matriculaFinal value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @return matriculaFinal
	 */
	public java.lang.String getMatriculaFinal()
	{
		return matriculaFinal;
	}

	/**
	 * Sets the matriculaInicial value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @param matriculaInicial de matricula inicial
	 */
	public void setMatriculaInicial(java.lang.String matriculaInicial)
	{
		this.matriculaInicial = matriculaInicial;
	}

	/**
	 * Gets the matriculaInicial value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @return matriculaInicial
	 */
	public java.lang.String getMatriculaInicial()
	{
		return matriculaInicial;
	}

	/**
	 * Sets the modulo value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @param modulo de modulo
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the modulo value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @return modulo
	 */
	public java.lang.String getModulo()
	{
		return modulo;
	}

	/**
	 * Sets the numeroDocumento value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @param numeroDocumento de numero documento
	 */
	public void setNumeroDocumento(java.lang.String numeroDocumento)
	{
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Gets the numeroDocumento value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @return numeroDocumento
	 */
	public java.lang.String getNumeroDocumento()
	{
		return numeroDocumento;
	}

	/**
	 * Sets the oripSecuencia value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @param oripSecuencia de orip secuencia
	 */
	public void setOripSecuencia(java.lang.String oripSecuencia)
	{
		this.oripSecuencia = oripSecuencia;
	}

	/**
	 * Gets the oripSecuencia value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @return oripSecuencia
	 */
	public java.lang.String getOripSecuencia()
	{
		return oripSecuencia;
	}

	/**
	 * Sets the primerApellido value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @param primerApellido de primer apellido
	 */
	public void setPrimerApellido(java.lang.String primerApellido)
	{
		this.primerApellido = primerApellido;
	}

	/**
	 * Gets the primerApellido value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @return primerApellido
	 */
	public java.lang.String getPrimerApellido()
	{
		return primerApellido;
	}

	/**
	 * Sets the primerNombre value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @param primerNombre de primer nombre
	 */
	public void setPrimerNombre(java.lang.String primerNombre)
	{
		this.primerNombre = primerNombre;
	}

	/**
	 * Gets the primerNombre value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @return primerNombre
	 */
	public java.lang.String getPrimerNombre()
	{
		return primerNombre;
	}

	/**
	 * Sets the razonSocial value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @param razonSocial de razon social
	 */
	public void setRazonSocial(java.lang.String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/**
	 * Gets the razonSocial value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @return razonSocial
	 */
	public java.lang.String getRazonSocial()
	{
		return razonSocial;
	}

	/**
	 * Sets the segundoApellido value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @param segundoApellido de segundo apellido
	 */
	public void setSegundoApellido(java.lang.String segundoApellido)
	{
		this.segundoApellido = segundoApellido;
	}

	/**
	 * Gets the segundoApellido value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @return segundoApellido
	 */
	public java.lang.String getSegundoApellido()
	{
		return segundoApellido;
	}

	/**
	 * Sets the segundoNombre value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @param segundoNombre de segundo nombre
	 */
	public void setSegundoNombre(java.lang.String segundoNombre)
	{
		this.segundoNombre = segundoNombre;
	}

	/**
	 * Gets the segundoNombre value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @return segundoNombre
	 */
	public java.lang.String getSegundoNombre()
	{
		return segundoNombre;
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
	 * Sets the tipoDocumento value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @param tipoDocumento de tipo documento
	 */
	public void setTipoDocumento(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoEntradaValidarSolicitudAlertaMasivaTipoDocumento tipoDocumento
	)
	{
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the tipoDocumento value for this TipoEntradaValidarSolicitudAlertaMasiva.
	 *
	 * @return tipoDocumento
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoEntradaValidarSolicitudAlertaMasivaTipoDocumento getTipoDocumento()
	{
		return tipoDocumento;
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
		if(!(obj instanceof TipoEntradaValidarSolicitudAlertaMasiva))
			return false;

		TipoEntradaValidarSolicitudAlertaMasiva other = (TipoEntradaValidarSolicitudAlertaMasiva)obj;

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
				&& (((this.tipoDocumento == null) && (other.getTipoDocumento() == null))
				|| ((this.tipoDocumento != null) && this.tipoDocumento.equals(other.getTipoDocumento())))
				&& (((this.numeroDocumento == null) && (other.getNumeroDocumento() == null))
				|| ((this.numeroDocumento != null) && this.numeroDocumento.equals(other.getNumeroDocumento())))
				&& (((this.primerNombre == null) && (other.getPrimerNombre() == null))
				|| ((this.primerNombre != null) && this.primerNombre.equals(other.getPrimerNombre())))
				&& (((this.segundoNombre == null) && (other.getSegundoNombre() == null))
				|| ((this.segundoNombre != null) && this.segundoNombre.equals(other.getSegundoNombre())))
				&& (((this.primerApellido == null) && (other.getPrimerApellido() == null))
				|| ((this.primerApellido != null) && this.primerApellido.equals(other.getPrimerApellido())))
				&& (((this.segundoApellido == null) && (other.getSegundoApellido() == null))
				|| ((this.segundoApellido != null) && this.segundoApellido.equals(other.getSegundoApellido())))
				&& (((this.razonSocial == null) && (other.getRazonSocial() == null))
				|| ((this.razonSocial != null) && this.razonSocial.equals(other.getRazonSocial())))
				&& (((this.oripSecuencia == null) && (other.getOripSecuencia() == null))
				|| ((this.oripSecuencia != null) && this.oripSecuencia.equals(other.getOripSecuencia())))
				&& (((this.matriculaInicial == null) && (other.getMatriculaInicial() == null))
				|| ((this.matriculaInicial != null) && this.matriculaInicial.equals(other.getMatriculaInicial())))
				&& (((this.matriculaFinal == null) && (other.getMatriculaFinal() == null))
				|| ((this.matriculaFinal != null) && this.matriculaFinal.equals(other.getMatriculaFinal())))
				&& (((this.archivo == null) && (other.getArchivo() == null))
				|| ((this.archivo != null) && java.util.Arrays.equals(this.archivo, other.getArchivo())));
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

		if(getTipoDocumento() != null)
			_hashCode += getTipoDocumento().hashCode();

		if(getNumeroDocumento() != null)
			_hashCode += getNumeroDocumento().hashCode();

		if(getPrimerNombre() != null)
			_hashCode += getPrimerNombre().hashCode();

		if(getSegundoNombre() != null)
			_hashCode += getSegundoNombre().hashCode();

		if(getPrimerApellido() != null)
			_hashCode += getPrimerApellido().hashCode();

		if(getSegundoApellido() != null)
			_hashCode += getSegundoApellido().hashCode();

		if(getRazonSocial() != null)
			_hashCode += getRazonSocial().hashCode();

		if(getOripSecuencia() != null)
			_hashCode += getOripSecuencia().hashCode();

		if(getMatriculaInicial() != null)
			_hashCode += getMatriculaInicial().hashCode();

		if(getMatriculaFinal() != null)
			_hashCode += getMatriculaFinal().hashCode();

		if(getArchivo() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getArchivo()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getArchivo(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
