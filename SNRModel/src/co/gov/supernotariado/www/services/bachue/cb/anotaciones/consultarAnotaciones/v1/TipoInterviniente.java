/**
 * TipoInterviniente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1;


/**
 * Clase que contiene todos las propiedades TipoInterviniente.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoInterviniente implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3705035496593256790L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoInterviniente.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "tipoInterviniente"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("rolInterviniente");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "rolInterviniente"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "tipoPersona"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("esPropietario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "esPropietario"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1", "numNIT"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "porcentajeParticipacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerNombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "primerNombre"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "segundoNombre"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "primerApellido"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "segundoApellido"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codGenero");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1", "codGenero"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
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

	/** Propiedad cod genero. */
	/* Código Identidad Genero del interviniente 01
	 *                                 para
	 *                                 Masculino y 02 para Femenino */
	private java.lang.String codGenero;

	/** Propiedad es propietario. */
	/* Viene con "X" o "I", donde X significa que la
	 *                                 persona natural o juridica es titular de derecho real principal
	 *                                 del inmueble e I significa que es titular de derecho en falsa
	 *                                 tradicion del inmueble. */
	private java.lang.String esPropietario;

	/** Propiedad num documento persona. */
	/* Número de documento del interviniente */
	private java.lang.String numDocumentoPersona;

	/** Propiedad num NIT. */
	/* Número de documento del interviniente */
	private java.lang.String numNIT;

	/** Propiedad porcentaje participacion. */
	/* porcentaje de participación */
	private java.lang.String porcentajeParticipacion;

	/** Propiedad primer apellido. */
	/* primer nombre del interviniente */
	private java.lang.String primerApellido;

	/** Propiedad primer nombre. */
	/* primer apellido del interviniente */
	private java.lang.String primerNombre;

	/** Propiedad razon social. */
	/* razon social del interviniente */
	private java.lang.String razonSocial;

	/** Propiedad rol interviniente. */
	/* El rol del Interviniente, valores posibles DE
	 *                                 o
	 *                                 A */
	private java.lang.String rolInterviniente;

	/** Propiedad segundo apellido. */
	/* segundo nombre del interviniente */
	private java.lang.String segundoApellido;

	/** Propiedad segundo nombre. */
	/* segundo apellido del interviniente */
	private java.lang.String segundoNombre;

	/** Propiedad tipo documento persona. */
	/* Tipos de documento posibles de un
	 *                                 interviniente:
	 *                                 CC, CE, PA, TI, NUIP */
	private java.lang.String tipoDocumentoPersona;

	/** Propiedad tipo persona. */
	/* Natural o Jurídica */
	private java.lang.String tipoPersona;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo interviniente.
	 */
	public TipoInterviniente()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo interviniente.
	 *
	 * @param rolInterviniente de rol interviniente
	 * @param tipoPersona de tipo persona
	 * @param esPropietario de es propietario
	 * @param tipoDocumentoPersona de tipo documento persona
	 * @param numDocumentoPersona de num documento persona
	 * @param numNIT de num NIT
	 * @param porcentajeParticipacion de porcentaje participacion
	 * @param primerNombre de primer nombre
	 * @param segundoNombre de segundo nombre
	 * @param primerApellido de primer apellido
	 * @param segundoApellido de segundo apellido
	 * @param codGenero de cod genero
	 * @param razonSocial de razon social
	 */
	public TipoInterviniente(
	    java.lang.String rolInterviniente, java.lang.String tipoPersona, java.lang.String esPropietario,
	    java.lang.String tipoDocumentoPersona, java.lang.String numDocumentoPersona, java.lang.String numNIT,
	    java.lang.String porcentajeParticipacion, java.lang.String primerNombre, java.lang.String segundoNombre,
	    java.lang.String primerApellido, java.lang.String segundoApellido, java.lang.String codGenero,
	    java.lang.String razonSocial
	)
	{
		this.rolInterviniente            = rolInterviniente;
		this.tipoPersona                 = tipoPersona;
		this.esPropietario               = esPropietario;
		this.tipoDocumentoPersona        = tipoDocumentoPersona;
		this.numDocumentoPersona         = numDocumentoPersona;
		this.numNIT                      = numNIT;
		this.porcentajeParticipacion     = porcentajeParticipacion;
		this.primerNombre                = primerNombre;
		this.segundoNombre               = segundoNombre;
		this.primerApellido              = primerApellido;
		this.segundoApellido             = segundoApellido;
		this.codGenero                   = codGenero;
		this.razonSocial                 = razonSocial;
	}

	/**
	 * Gets the rolInterviniente value for this TipoInterviniente.
	 *
	 * @return rolInterviniente   * El rol del Interviniente, valores posibles DE
	     *                                 o
	     *                                 A
	 */
	public java.lang.String getRolInterviniente()
	{
		return rolInterviniente;
	}

	/**
	 * Sets the rolInterviniente value for this TipoInterviniente.
	 *
	 * @param rolInterviniente   * El rol del Interviniente, valores posibles DE
	     *                                 o
	     *                                 A
	 */
	public void setRolInterviniente(java.lang.String rolInterviniente)
	{
		this.rolInterviniente = rolInterviniente;
	}

	/**
	 * Gets the tipoPersona value for this TipoInterviniente.
	 *
	 * @return tipoPersona   * Natural o Jurídica
	 */
	public java.lang.String getTipoPersona()
	{
		return tipoPersona;
	}

	/**
	 * Sets the tipoPersona value for this TipoInterviniente.
	 *
	 * @param tipoPersona   * Natural o Jurídica
	 */
	public void setTipoPersona(java.lang.String tipoPersona)
	{
		this.tipoPersona = tipoPersona;
	}

	/**
	 * Gets the esPropietario value for this TipoInterviniente.
	 *
	 * @return esPropietario   * Viene con "X" o "I", donde X significa que la
	     *                                 persona natural o juridica es titular de derecho real principal
	     *                                 del inmueble e I significa que es titular de derecho en falsa
	     *                                 tradicion del inmueble.
	 */
	public java.lang.String getEsPropietario()
	{
		return esPropietario;
	}

	/**
	 * Sets the esPropietario value for this TipoInterviniente.
	 *
	 * @param esPropietario   * Viene con "X" o "I", donde X significa que la
	     *                                 persona natural o juridica es titular de derecho real principal
	     *                                 del inmueble e I significa que es titular de derecho en falsa
	     *                                 tradicion del inmueble.
	 */
	public void setEsPropietario(java.lang.String esPropietario)
	{
		this.esPropietario = esPropietario;
	}

	/**
	 * Gets the tipoDocumentoPersona value for this TipoInterviniente.
	 *
	 * @return tipoDocumentoPersona   * Tipos de documento posibles de un
	     *                                 interviniente:
	     *                                 CC, CE, PA, TI, NUIP
	 */
	public java.lang.String getTipoDocumentoPersona()
	{
		return tipoDocumentoPersona;
	}

	/**
	 * Sets the tipoDocumentoPersona value for this TipoInterviniente.
	 *
	 * @param tipoDocumentoPersona   * Tipos de documento posibles de un
	     *                                 interviniente:
	     *                                 CC, CE, PA, TI, NUIP
	 */
	public void setTipoDocumentoPersona(java.lang.String tipoDocumentoPersona)
	{
		this.tipoDocumentoPersona = tipoDocumentoPersona;
	}

	/**
	 * Gets the numDocumentoPersona value for this TipoInterviniente.
	 *
	 * @return numDocumentoPersona   * Número de documento del interviniente
	 */
	public java.lang.String getNumDocumentoPersona()
	{
		return numDocumentoPersona;
	}

	/**
	 * Sets the numDocumentoPersona value for this TipoInterviniente.
	 *
	 * @param numDocumentoPersona   * Número de documento del interviniente
	 */
	public void setNumDocumentoPersona(java.lang.String numDocumentoPersona)
	{
		this.numDocumentoPersona = numDocumentoPersona;
	}

	/**
	 * Gets the numNIT value for this TipoInterviniente.
	 *
	 * @return numNIT   * Número de documento del interviniente
	 */
	public java.lang.String getNumNIT()
	{
		return numNIT;
	}

	/**
	 * Sets the numNIT value for this TipoInterviniente.
	 *
	 * @param numNIT   * Número de documento del interviniente
	 */
	public void setNumNIT(java.lang.String numNIT)
	{
		this.numNIT = numNIT;
	}

	/**
	 * Gets the porcentajeParticipacion value for this TipoInterviniente.
	 *
	 * @return porcentajeParticipacion   * porcentaje de participación
	 */
	public java.lang.String getPorcentajeParticipacion()
	{
		return porcentajeParticipacion;
	}

	/**
	 * Sets the porcentajeParticipacion value for this TipoInterviniente.
	 *
	 * @param porcentajeParticipacion   * porcentaje de participación
	 */
	public void setPorcentajeParticipacion(java.lang.String porcentajeParticipacion)
	{
		this.porcentajeParticipacion = porcentajeParticipacion;
	}

	/**
	 * Gets the primerNombre value for this TipoInterviniente.
	 *
	 * @return primerNombre   * primer apellido del interviniente
	 */
	public java.lang.String getPrimerNombre()
	{
		return primerNombre;
	}

	/**
	 * Sets the primerNombre value for this TipoInterviniente.
	 *
	 * @param primerNombre   * primer apellido del interviniente
	 */
	public void setPrimerNombre(java.lang.String primerNombre)
	{
		this.primerNombre = primerNombre;
	}

	/**
	 * Gets the segundoNombre value for this TipoInterviniente.
	 *
	 * @return segundoNombre   * segundo apellido del interviniente
	 */
	public java.lang.String getSegundoNombre()
	{
		return segundoNombre;
	}

	/**
	 * Sets the segundoNombre value for this TipoInterviniente.
	 *
	 * @param segundoNombre   * segundo apellido del interviniente
	 */
	public void setSegundoNombre(java.lang.String segundoNombre)
	{
		this.segundoNombre = segundoNombre;
	}

	/**
	 * Gets the primerApellido value for this TipoInterviniente.
	 *
	 * @return primerApellido   * primer nombre del interviniente
	 */
	public java.lang.String getPrimerApellido()
	{
		return primerApellido;
	}

	/**
	 * Sets the primerApellido value for this TipoInterviniente.
	 *
	 * @param primerApellido   * primer nombre del interviniente
	 */
	public void setPrimerApellido(java.lang.String primerApellido)
	{
		this.primerApellido = primerApellido;
	}

	/**
	 * Gets the segundoApellido value for this TipoInterviniente.
	 *
	 * @return segundoApellido   * segundo nombre del interviniente
	 */
	public java.lang.String getSegundoApellido()
	{
		return segundoApellido;
	}

	/**
	 * Sets the segundoApellido value for this TipoInterviniente.
	 *
	 * @param segundoApellido   * segundo nombre del interviniente
	 */
	public void setSegundoApellido(java.lang.String segundoApellido)
	{
		this.segundoApellido = segundoApellido;
	}

	/**
	 * Gets the codGenero value for this TipoInterviniente.
	 *
	 * @return codGenero   * Código Identidad Genero del interviniente 01
	     *                                 para
	     *                                 Masculino y 02 para Femenino
	 */
	public java.lang.String getCodGenero()
	{
		return codGenero;
	}

	/**
	 * Sets the codGenero value for this TipoInterviniente.
	 *
	 * @param codGenero   * Código Identidad Genero del interviniente 01
	     *                                 para
	     *                                 Masculino y 02 para Femenino
	 */
	public void setCodGenero(java.lang.String codGenero)
	{
		this.codGenero = codGenero;
	}

	/**
	 * Gets the razonSocial value for this TipoInterviniente.
	 *
	 * @return razonSocial   * razon social del interviniente
	 */
	public java.lang.String getRazonSocial()
	{
		return razonSocial;
	}

	/**
	 * Sets the razonSocial value for this TipoInterviniente.
	 *
	 * @param razonSocial   * razon social del interviniente
	 */
	public void setRazonSocial(java.lang.String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoInterviniente))
			return false;

		TipoInterviniente other = (TipoInterviniente)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.rolInterviniente == null) && (other.getRolInterviniente() == null))
				|| ((this.rolInterviniente != null) && this.rolInterviniente.equals(other.getRolInterviniente())))
				&& (((this.tipoPersona == null) && (other.getTipoPersona() == null))
				|| ((this.tipoPersona != null) && this.tipoPersona.equals(other.getTipoPersona())))
				&& (((this.esPropietario == null) && (other.getEsPropietario() == null))
				|| ((this.esPropietario != null) && this.esPropietario.equals(other.getEsPropietario())))
				&& (((this.tipoDocumentoPersona == null) && (other.getTipoDocumentoPersona() == null))
				|| ((this.tipoDocumentoPersona != null)
				&& this.tipoDocumentoPersona.equals(other.getTipoDocumentoPersona())))
				&& (((this.numDocumentoPersona == null) && (other.getNumDocumentoPersona() == null))
				|| ((this.numDocumentoPersona != null)
				&& this.numDocumentoPersona.equals(other.getNumDocumentoPersona())))
				&& (((this.numNIT == null) && (other.getNumNIT() == null))
				|| ((this.numNIT != null) && this.numNIT.equals(other.getNumNIT())))
				&& (((this.porcentajeParticipacion == null) && (other.getPorcentajeParticipacion() == null))
				|| ((this.porcentajeParticipacion != null)
				&& this.porcentajeParticipacion.equals(other.getPorcentajeParticipacion())))
				&& (((this.primerNombre == null) && (other.getPrimerNombre() == null))
				|| ((this.primerNombre != null) && this.primerNombre.equals(other.getPrimerNombre())))
				&& (((this.segundoNombre == null) && (other.getSegundoNombre() == null))
				|| ((this.segundoNombre != null) && this.segundoNombre.equals(other.getSegundoNombre())))
				&& (((this.primerApellido == null) && (other.getPrimerApellido() == null))
				|| ((this.primerApellido != null) && this.primerApellido.equals(other.getPrimerApellido())))
				&& (((this.segundoApellido == null) && (other.getSegundoApellido() == null))
				|| ((this.segundoApellido != null) && this.segundoApellido.equals(other.getSegundoApellido())))
				&& (((this.codGenero == null) && (other.getCodGenero() == null))
				|| ((this.codGenero != null) && this.codGenero.equals(other.getCodGenero())))
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

		if(getRolInterviniente() != null)
			_hashCode += getRolInterviniente().hashCode();

		if(getTipoPersona() != null)
			_hashCode += getTipoPersona().hashCode();

		if(getEsPropietario() != null)
			_hashCode += getEsPropietario().hashCode();

		if(getTipoDocumentoPersona() != null)
			_hashCode += getTipoDocumentoPersona().hashCode();

		if(getNumDocumentoPersona() != null)
			_hashCode += getNumDocumentoPersona().hashCode();

		if(getNumNIT() != null)
			_hashCode += getNumNIT().hashCode();

		if(getPorcentajeParticipacion() != null)
			_hashCode += getPorcentajeParticipacion().hashCode();

		if(getPrimerNombre() != null)
			_hashCode += getPrimerNombre().hashCode();

		if(getSegundoNombre() != null)
			_hashCode += getSegundoNombre().hashCode();

		if(getPrimerApellido() != null)
			_hashCode += getPrimerApellido().hashCode();

		if(getSegundoApellido() != null)
			_hashCode += getSegundoApellido().hashCode();

		if(getCodGenero() != null)
			_hashCode += getCodGenero().hashCode();

		if(getRazonSocial() != null)
			_hashCode += getRazonSocial().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
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
}
