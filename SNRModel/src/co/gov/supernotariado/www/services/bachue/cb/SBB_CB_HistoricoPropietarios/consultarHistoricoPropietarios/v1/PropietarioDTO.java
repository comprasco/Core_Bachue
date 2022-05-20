/**
 * PropietarioDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1;



/**
 * Clase que contiene todos las propiedades PropietarioDTO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
public class PropietarioDTO implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1833650502673094128L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    PropietarioDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "propietarioDTO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("historicoMatriculas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "historicoMatriculas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "historicoPropietarioDTO"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumentoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "numDocumentoPersona"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numNIT");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "numNIT"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerApellido");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "primerApellido"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerNombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "primerNombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoApellido");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "segundoApellido"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoNombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "segundoNombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("razonSocial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "razonSocial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad num documento persona. */
	/* numero de documento del propietario */
	private java.lang.String numDocumentoPersona;

	/** Propiedad num NIT. */
	/* numero de documento del propietario */
	private java.lang.String numNIT;

	/** Propiedad primer apellido. */
	/* primer apellido del propietario */
	private java.lang.String primerApellido;

	/** Propiedad primer nombre. */
	/* primer nombre del propietario */
	private java.lang.String primerNombre;

	/** Propiedad razon social. */
	/* razon social del propietario */
	private java.lang.String razonSocial;

	/** Propiedad segundo apellido. */
	/* segundo apellido del propietario */
	private java.lang.String segundoApellido;

	/** Propiedad segundo nombre. */
	/* segundo nombre del propietario */
	private java.lang.String segundoNombre;

	/** Propiedad tipo documento persona. */
	/* tipo de documento del propietario */
	private java.lang.String tipoDocumentoPersona;

	/** Propiedad historico matriculas. */
	private co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.HistoricoPropietarioDTO[] historicoMatriculas;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto propietario DTO.
	 */
	public PropietarioDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto propietario DTO.
	 *
	 * @param historicoMatriculas de historico matriculas
	 * @param tipoDocumentoPersona de tipo documento persona
	 * @param numDocumentoPersona de num documento persona
	 * @param numNIT de num NIT
	 * @param primerApellido de primer apellido
	 * @param primerNombre de primer nombre
	 * @param segundoApellido de segundo apellido
	 * @param segundoNombre de segundo nombre
	 * @param razonSocial de razon social
	 */
	public PropietarioDTO(
	    co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.HistoricoPropietarioDTO[] historicoMatriculas,
	    java.lang.String                                                                                                                      tipoDocumentoPersona,
	    java.lang.String                                                                                                                      numDocumentoPersona,
	    java.lang.String                                                                                                                      numNIT,
	    java.lang.String                                                                                                                      primerApellido,
	    java.lang.String                                                                                                                      primerNombre,
	    java.lang.String                                                                                                                      segundoApellido,
	    java.lang.String                                                                                                                      segundoNombre,
	    java.lang.String                                                                                                                      razonSocial
	)
	{
		this.historicoMatriculas      = historicoMatriculas;
		this.tipoDocumentoPersona     = tipoDocumentoPersona;
		this.numDocumentoPersona      = numDocumentoPersona;
		this.numNIT                   = numNIT;
		this.primerApellido           = primerApellido;
		this.primerNombre             = primerNombre;
		this.segundoApellido          = segundoApellido;
		this.segundoNombre            = segundoNombre;
		this.razonSocial              = razonSocial;
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
	 * Sets the historicoMatriculas value for this PropietarioDTO.
	 *
	 * @param historicoMatriculas de historico matriculas
	 */
	public void setHistoricoMatriculas(
	    co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.HistoricoPropietarioDTO[] historicoMatriculas
	)
	{
		this.historicoMatriculas = historicoMatriculas;
	}

	/**
	 * Cambia el valor de historico matriculas.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setHistoricoMatriculas(
	    int i,
	    co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.HistoricoPropietarioDTO _value
	)
	{
		this.historicoMatriculas[i] = _value;
	}

	/**
	 * Gets the historicoMatriculas value for this PropietarioDTO.
	 *
	 * @return historicoMatriculas
	 */
	public co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.HistoricoPropietarioDTO[] getHistoricoMatriculas()
	{
		return historicoMatriculas;
	}

	/**
	 * Retorna Objeto o variable de valor historico matriculas.
	 *
	 * @param i de i
	 * @return el valor de historico matriculas
	 */
	public co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.HistoricoPropietarioDTO getHistoricoMatriculas(
	    int i
	)
	{
		return this.historicoMatriculas[i];
	}

	/**
	 * Sets the numDocumentoPersona value for this PropietarioDTO.
	 *
	 * @param numDocumentoPersona   * numero de documento del propietario
	 */
	public void setNumDocumentoPersona(java.lang.String numDocumentoPersona)
	{
		this.numDocumentoPersona = numDocumentoPersona;
	}

	/**
	 * Gets the numDocumentoPersona value for this PropietarioDTO.
	 *
	 * @return numDocumentoPersona   * numero de documento del propietario
	 */
	public java.lang.String getNumDocumentoPersona()
	{
		return numDocumentoPersona;
	}

	/**
	 * Sets the numNIT value for this PropietarioDTO.
	 *
	 * @param numNIT   * numero de documento del propietario
	 */
	public void setNumNIT(java.lang.String numNIT)
	{
		this.numNIT = numNIT;
	}

	/**
	 * Gets the numNIT value for this PropietarioDTO.
	 *
	 * @return numNIT   * numero de documento del propietario
	 */
	public java.lang.String getNumNIT()
	{
		return numNIT;
	}

	/**
	 * Sets the primerApellido value for this PropietarioDTO.
	 *
	 * @param primerApellido   * primer apellido del propietario
	 */
	public void setPrimerApellido(java.lang.String primerApellido)
	{
		this.primerApellido = primerApellido;
	}

	/**
	 * Gets the primerApellido value for this PropietarioDTO.
	 *
	 * @return primerApellido   * primer apellido del propietario
	 */
	public java.lang.String getPrimerApellido()
	{
		return primerApellido;
	}

	/**
	 * Sets the primerNombre value for this PropietarioDTO.
	 *
	 * @param primerNombre   * primer nombre del propietario
	 */
	public void setPrimerNombre(java.lang.String primerNombre)
	{
		this.primerNombre = primerNombre;
	}

	/**
	 * Gets the primerNombre value for this PropietarioDTO.
	 *
	 * @return primerNombre   * primer nombre del propietario
	 */
	public java.lang.String getPrimerNombre()
	{
		return primerNombre;
	}

	/**
	 * Sets the razonSocial value for this PropietarioDTO.
	 *
	 * @param razonSocial   * razon social del propietario
	 */
	public void setRazonSocial(java.lang.String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/**
	 * Gets the razonSocial value for this PropietarioDTO.
	 *
	 * @return razonSocial   * razon social del propietario
	 */
	public java.lang.String getRazonSocial()
	{
		return razonSocial;
	}

	/**
	 * Sets the segundoApellido value for this PropietarioDTO.
	 *
	 * @param segundoApellido   * segundo apellido del propietario
	 */
	public void setSegundoApellido(java.lang.String segundoApellido)
	{
		this.segundoApellido = segundoApellido;
	}

	/**
	 * Gets the segundoApellido value for this PropietarioDTO.
	 *
	 * @return segundoApellido   * segundo apellido del propietario
	 */
	public java.lang.String getSegundoApellido()
	{
		return segundoApellido;
	}

	/**
	 * Sets the segundoNombre value for this PropietarioDTO.
	 *
	 * @param segundoNombre   * segundo nombre del propietario
	 */
	public void setSegundoNombre(java.lang.String segundoNombre)
	{
		this.segundoNombre = segundoNombre;
	}

	/**
	 * Gets the segundoNombre value for this PropietarioDTO.
	 *
	 * @return segundoNombre   * segundo nombre del propietario
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
	 * Sets the tipoDocumentoPersona value for this PropietarioDTO.
	 *
	 * @param tipoDocumentoPersona   * tipo de documento del propietario
	 */
	public void setTipoDocumentoPersona(java.lang.String tipoDocumentoPersona)
	{
		this.tipoDocumentoPersona = tipoDocumentoPersona;
	}

	/**
	 * Gets the tipoDocumentoPersona value for this PropietarioDTO.
	 *
	 * @return tipoDocumentoPersona   * tipo de documento del propietario
	 */
	public java.lang.String getTipoDocumentoPersona()
	{
		return tipoDocumentoPersona;
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
		if(!(obj instanceof PropietarioDTO))
			return false;

		PropietarioDTO other = (PropietarioDTO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.historicoMatriculas == null) && (other.getHistoricoMatriculas() == null))
				|| ((this.historicoMatriculas != null)
				&& java.util.Arrays.equals(this.historicoMatriculas, other.getHistoricoMatriculas())))
				&& (((this.tipoDocumentoPersona == null) && (other.getTipoDocumentoPersona() == null))
				|| ((this.tipoDocumentoPersona != null)
				&& this.tipoDocumentoPersona.equals(other.getTipoDocumentoPersona())))
				&& (((this.numDocumentoPersona == null) && (other.getNumDocumentoPersona() == null))
				|| ((this.numDocumentoPersona != null)
				&& this.numDocumentoPersona.equals(other.getNumDocumentoPersona())))
				&& (((this.numNIT == null) && (other.getNumNIT() == null))
				|| ((this.numNIT != null) && this.numNIT.equals(other.getNumNIT())))
				&& (((this.primerApellido == null) && (other.getPrimerApellido() == null))
				|| ((this.primerApellido != null) && this.primerApellido.equals(other.getPrimerApellido())))
				&& (((this.primerNombre == null) && (other.getPrimerNombre() == null))
				|| ((this.primerNombre != null) && this.primerNombre.equals(other.getPrimerNombre())))
				&& (((this.segundoApellido == null) && (other.getSegundoApellido() == null))
				|| ((this.segundoApellido != null) && this.segundoApellido.equals(other.getSegundoApellido())))
				&& (((this.segundoNombre == null) && (other.getSegundoNombre() == null))
				|| ((this.segundoNombre != null) && this.segundoNombre.equals(other.getSegundoNombre())))
				&& (((this.razonSocial == null) && (other.getRazonSocial() == null))
				|| ((this.razonSocial != null) && this.razonSocial.equals(other.getRazonSocial())));
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

		if(getHistoricoMatriculas() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getHistoricoMatriculas()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getHistoricoMatriculas(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getTipoDocumentoPersona() != null)
			_hashCode += getTipoDocumentoPersona().hashCode();

		if(getNumDocumentoPersona() != null)
			_hashCode += getNumDocumentoPersona().hashCode();

		if(getNumNIT() != null)
			_hashCode += getNumNIT().hashCode();

		if(getPrimerApellido() != null)
			_hashCode += getPrimerApellido().hashCode();

		if(getPrimerNombre() != null)
			_hashCode += getPrimerNombre().hashCode();

		if(getSegundoApellido() != null)
			_hashCode += getSegundoApellido().hashCode();

		if(getSegundoNombre() != null)
			_hashCode += getSegundoNombre().hashCode();

		if(getRazonSocial() != null)
			_hashCode += getRazonSocial().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
