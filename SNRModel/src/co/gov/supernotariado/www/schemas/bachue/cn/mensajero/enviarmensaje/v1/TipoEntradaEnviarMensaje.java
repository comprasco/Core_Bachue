/**
 * TipoEntradaEnviarMensaje.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaEnviarMensaje.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 25/03/2020
 */
public class TipoEntradaEnviarMensaje implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6936027153266374764L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaEnviarMensaje.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
		        "tipoEntradaEnviarMensaje"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "modulo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("identificadorEE");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "identificadorEE"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("clasificacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "clasificacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("canal");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "canal"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("orip");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "orip"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nir");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "nir"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("turno");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "turno"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("destinatario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "destinatario"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "tipoDestinatarioEMI"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("documentos");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "documentos"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "tipoDocumento"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "documento"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("plantilla");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "plantilla"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("variables");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "variables"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "tipoVariableEMI"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "variable"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad canal. */
	private java.lang.String canal;

	/** Propiedad clasificacion. */
	private java.lang.String clasificacion;

	/** Propiedad destinatario. */
	private co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDestinatarioEMI destinatario;

	/** Propiedad identificador EE. */
	private java.lang.String identificadorEE;

	/** Propiedad modulo. */
	private java.lang.String modulo;

	/** Propiedad nir. */
	private java.lang.String nir;

	/** Propiedad orip. */
	private java.lang.String orip;

	/** Propiedad plantilla. */
	private java.lang.String plantilla;

	/** Propiedad turno. */
	private java.lang.String turno;

	/** Propiedad documentos. */
	private co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDocumento[] documentos;

	/** Propiedad variables. */
	private co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoVariableEMI[] variables;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada enviar mensaje.
	 */
	public TipoEntradaEnviarMensaje()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada enviar mensaje.
	 *
	 * @param modulo de modulo
	 * @param identificadorEE de identificador EE
	 * @param clasificacion de clasificacion
	 * @param canal de canal
	 * @param orip de orip
	 * @param nir de nir
	 * @param turno de turno
	 * @param destinatario de destinatario
	 * @param documentos de documentos
	 * @param plantilla de plantilla
	 * @param variables de variables
	 */
	public TipoEntradaEnviarMensaje(
	    java.lang.String modulo, java.lang.String identificadorEE, java.lang.String clasificacion,
	    java.lang.String canal, java.lang.String orip, java.lang.String nir, java.lang.String turno,
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDestinatarioEMI destinatario,
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDocumento[] documentos,
	    java.lang.String plantilla,
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoVariableEMI[] variables
	)
	{
		this.modulo              = modulo;
		this.identificadorEE     = identificadorEE;
		this.clasificacion       = clasificacion;
		this.canal               = canal;
		this.orip                = orip;
		this.nir                 = nir;
		this.turno               = turno;
		this.destinatario        = destinatario;
		this.documentos          = documentos;
		this.plantilla           = plantilla;
		this.variables           = variables;
	}

	/**
	 * Sets the canal value for this TipoEntradaEnviarMensaje.
	 *
	 * @param canal de canal
	 */
	public void setCanal(java.lang.String canal)
	{
		this.canal = canal;
	}

	/**
	 * Gets the canal value for this TipoEntradaEnviarMensaje.
	 *
	 * @return canal
	 */
	public java.lang.String getCanal()
	{
		return canal;
	}

	/**
	 * Sets the clasificacion value for this TipoEntradaEnviarMensaje.
	 *
	 * @param clasificacion de clasificacion
	 */
	public void setClasificacion(java.lang.String clasificacion)
	{
		this.clasificacion = clasificacion;
	}

	/**
	 * Gets the clasificacion value for this TipoEntradaEnviarMensaje.
	 *
	 * @return clasificacion
	 */
	public java.lang.String getClasificacion()
	{
		return clasificacion;
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
	 * Sets the destinatario value for this TipoEntradaEnviarMensaje.
	 *
	 * @param destinatario de destinatario
	 */
	public void setDestinatario(
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDestinatarioEMI destinatario
	)
	{
		this.destinatario = destinatario;
	}

	/**
	 * Gets the destinatario value for this TipoEntradaEnviarMensaje.
	 *
	 * @return destinatario
	 */
	public co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDestinatarioEMI getDestinatario()
	{
		return destinatario;
	}

	/**
	 * Sets the documentos value for this TipoEntradaEnviarMensaje.
	 *
	 * @param documentos de documentos
	 */
	public void setDocumentos(
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDocumento[] documentos
	)
	{
		this.documentos = documentos;
	}

	/**
	 * Gets the documentos value for this TipoEntradaEnviarMensaje.
	 *
	 * @return documentos
	 */
	public co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDocumento[] getDocumentos()
	{
		return documentos;
	}

	/**
	 * Sets the identificadorEE value for this TipoEntradaEnviarMensaje.
	 *
	 * @param identificadorEE de identificador EE
	 */
	public void setIdentificadorEE(java.lang.String identificadorEE)
	{
		this.identificadorEE = identificadorEE;
	}

	/**
	 * Gets the identificadorEE value for this TipoEntradaEnviarMensaje.
	 *
	 * @return identificadorEE
	 */
	public java.lang.String getIdentificadorEE()
	{
		return identificadorEE;
	}

	/**
	 * Sets the modulo value for this TipoEntradaEnviarMensaje.
	 *
	 * @param modulo de modulo
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the modulo value for this TipoEntradaEnviarMensaje.
	 *
	 * @return modulo
	 */
	public java.lang.String getModulo()
	{
		return modulo;
	}

	/**
	 * Sets the nir value for this TipoEntradaEnviarMensaje.
	 *
	 * @param nir de nir
	 */
	public void setNir(java.lang.String nir)
	{
		this.nir = nir;
	}

	/**
	 * Gets the nir value for this TipoEntradaEnviarMensaje.
	 *
	 * @return nir
	 */
	public java.lang.String getNir()
	{
		return nir;
	}

	/**
	 * Sets the orip value for this TipoEntradaEnviarMensaje.
	 *
	 * @param orip de orip
	 */
	public void setOrip(java.lang.String orip)
	{
		this.orip = orip;
	}

	/**
	 * Gets the orip value for this TipoEntradaEnviarMensaje.
	 *
	 * @return orip
	 */
	public java.lang.String getOrip()
	{
		return orip;
	}

	/**
	 * Sets the plantilla value for this TipoEntradaEnviarMensaje.
	 *
	 * @param plantilla de plantilla
	 */
	public void setPlantilla(java.lang.String plantilla)
	{
		this.plantilla = plantilla;
	}

	/**
	 * Gets the plantilla value for this TipoEntradaEnviarMensaje.
	 *
	 * @return plantilla
	 */
	public java.lang.String getPlantilla()
	{
		return plantilla;
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
	 * Sets the turno value for this TipoEntradaEnviarMensaje.
	 *
	 * @param turno de turno
	 */
	public void setTurno(java.lang.String turno)
	{
		this.turno = turno;
	}

	/**
	 * Gets the turno value for this TipoEntradaEnviarMensaje.
	 *
	 * @return turno
	 */
	public java.lang.String getTurno()
	{
		return turno;
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
	 * Sets the variables value for this TipoEntradaEnviarMensaje.
	 *
	 * @param variables de variables
	 */
	public void setVariables(
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoVariableEMI[] variables
	)
	{
		this.variables = variables;
	}

	/**
	 * Gets the variables value for this TipoEntradaEnviarMensaje.
	 *
	 * @return variables
	 */
	public co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoVariableEMI[] getVariables()
	{
		return variables;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaEnviarMensaje))
			return false;

		TipoEntradaEnviarMensaje other = (TipoEntradaEnviarMensaje)obj;

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
				&& (((this.identificadorEE == null) && (other.getIdentificadorEE() == null))
				|| ((this.identificadorEE != null) && this.identificadorEE.equals(other.getIdentificadorEE())))
				&& (((this.clasificacion == null) && (other.getClasificacion() == null))
				|| ((this.clasificacion != null) && this.clasificacion.equals(other.getClasificacion())))
				&& (((this.canal == null) && (other.getCanal() == null))
				|| ((this.canal != null) && this.canal.equals(other.getCanal())))
				&& (((this.orip == null) && (other.getOrip() == null))
				|| ((this.orip != null) && this.orip.equals(other.getOrip())))
				&& (((this.nir == null) && (other.getNir() == null))
				|| ((this.nir != null) && this.nir.equals(other.getNir())))
				&& (((this.turno == null) && (other.getTurno() == null))
				|| ((this.turno != null) && this.turno.equals(other.getTurno())))
				&& (((this.destinatario == null) && (other.getDestinatario() == null))
				|| ((this.destinatario != null) && this.destinatario.equals(other.getDestinatario())))
				&& (((this.documentos == null) && (other.getDocumentos() == null))
				|| ((this.documentos != null) && java.util.Arrays.equals(this.documentos, other.getDocumentos())))
				&& (((this.plantilla == null) && (other.getPlantilla() == null))
				|| ((this.plantilla != null) && this.plantilla.equals(other.getPlantilla())))
				&& (((this.variables == null) && (other.getVariables() == null))
				|| ((this.variables != null) && java.util.Arrays.equals(this.variables, other.getVariables())));
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

		if(getIdentificadorEE() != null)
			_hashCode += getIdentificadorEE().hashCode();

		if(getClasificacion() != null)
			_hashCode += getClasificacion().hashCode();

		if(getCanal() != null)
			_hashCode += getCanal().hashCode();

		if(getOrip() != null)
			_hashCode += getOrip().hashCode();

		if(getNir() != null)
			_hashCode += getNir().hashCode();

		if(getTurno() != null)
			_hashCode += getTurno().hashCode();

		if(getDestinatario() != null)
			_hashCode += getDestinatario().hashCode();

		if(getDocumentos() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getDocumentos()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getDocumentos(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getPlantilla() != null)
			_hashCode += getPlantilla().hashCode();

		if(getVariables() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getVariables()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getVariables(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
