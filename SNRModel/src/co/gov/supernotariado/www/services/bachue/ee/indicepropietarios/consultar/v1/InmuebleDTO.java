/**
 * InmuebleDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1;


/**
 * El esquema define los
 *                         datos de salida para la operacion Consulta ínidce de propietarios
 *                         Datos del inmueble.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/07/2020
 */
public class InmuebleDTO implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3112806782448953609L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    InmuebleDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "inmuebleDTO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "codCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "codDepartamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMunicipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "codMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("direccionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "direccionPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numMatriculaInmobiliaria");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "numMatriculaInmobiliaria"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numPredial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "numPredial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("NUPRE");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "NUPRE"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "nomCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "nomDepartamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomMunicipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "nomMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("propietarios");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "propietarios"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "propietarioDTO"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad nupre. */
	/* Número Único Predial */
	private java.lang.String NUPRE;

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod circulo registral. */
	/* Código del círculo o código de ORIP al que
	 *                                 corresponde el predio. */
	private java.lang.String codCirculoRegistral;

	/** Propiedad cod departamento. */
	/* Código DANE, string de longitud 2 */
	private java.lang.String codDepartamento;

	/** Propiedad cod municipio. */
	/* código DANE, longitud 5, dpto + municipio */
	private java.lang.String codMunicipio;

	/** Propiedad direccion predio. */
	/* Corresponde a la dirección del predio */
	private java.lang.String direccionPredio;

	/** Propiedad nom circulo registral. */
	/* Corresponde al nombre del círculo registral al
	 *                                 que pertenece el predio */
	private java.lang.String nomCirculoRegistral;

	/** Propiedad nom departamento. */
	/* Corresponde al nombre del departamento en
	 *                                 dondese ubica el predio */
	private java.lang.String nomDepartamento;

	/** Propiedad nom municipio. */
	/* Nombre del municipio según DANE donde está
	 *                                 ubicado el predio. */
	private java.lang.String nomMunicipio;

	/** Propiedad num matricula inmobiliaria. */
	/* Número de matrícula inmobiliaria o número de
	 *                                 folio con el que se identifica el predio. */
	private java.lang.String numMatriculaInmobiliaria;

	/** Propiedad num predial. */
	/* Corresponde al CHIP, si es Bogotá o a la
	 *                                 referencia o cédula catastral del predio. */
	private java.lang.String numPredial;

	/** Propiedad propietarios. */
	/* Corresponde al número de propietarios del
	 *                                 predio */
	private co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.PropietarioDTO[] propietarios;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto inmueble DTO.
	 */
	public InmuebleDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto inmueble DTO.
	 *
	 * @param codCirculoRegistral correspondiente al valor de cod circulo registral
	 * @param codDepartamento correspondiente al valor de cod departamento
	 * @param codMunicipio correspondiente al valor de cod municipio
	 * @param direccionPredio correspondiente al valor de direccion predio
	 * @param numMatriculaInmobiliaria correspondiente al valor de num matricula inmobiliaria
	 * @param numPredial correspondiente al valor de num predial
	 * @param NUPRE correspondiente al valor de nupre
	 * @param nomCirculoRegistral correspondiente al valor de nom circulo registral
	 * @param nomDepartamento correspondiente al valor de nom departamento
	 * @param nomMunicipio correspondiente al valor de nom municipio
	 * @param propietarios correspondiente al valor de propietarios
	 */
	public InmuebleDTO(
	    java.lang.String codCirculoRegistral, java.lang.String codDepartamento, java.lang.String codMunicipio,
	    java.lang.String direccionPredio, java.lang.String numMatriculaInmobiliaria, java.lang.String numPredial,
	    java.lang.String NUPRE, java.lang.String nomCirculoRegistral, java.lang.String nomDepartamento,
	    java.lang.String nomMunicipio,
	    co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.PropietarioDTO[] propietarios
	)
	{
		this.codCirculoRegistral          = codCirculoRegistral;
		this.codDepartamento              = codDepartamento;
		this.codMunicipio                 = codMunicipio;
		this.direccionPredio              = direccionPredio;
		this.numMatriculaInmobiliaria     = numMatriculaInmobiliaria;
		this.numPredial                   = numPredial;
		this.NUPRE                        = NUPRE;
		this.nomCirculoRegistral          = nomCirculoRegistral;
		this.nomDepartamento              = nomDepartamento;
		this.nomMunicipio                 = nomMunicipio;
		this.propietarios                 = propietarios;
	}

	/**
	 * Sets the codCirculoRegistral value for this InmuebleDTO.
	 *
	 * @param codCirculoRegistral   * Código del círculo o código de ORIP al que
	     *                                 corresponde el predio.
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the codCirculoRegistral value for this InmuebleDTO.
	 *
	 * @return codCirculoRegistral   * Código del círculo o código de ORIP al que
	     *                                 corresponde el predio.
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the codDepartamento value for this InmuebleDTO.
	 *
	 * @param codDepartamento   * Código DANE, string de longitud 2
	 */
	public void setCodDepartamento(java.lang.String codDepartamento)
	{
		this.codDepartamento = codDepartamento;
	}

	/**
	 * Gets the codDepartamento value for this InmuebleDTO.
	 *
	 * @return codDepartamento   * Código DANE, string de longitud 2
	 */
	public java.lang.String getCodDepartamento()
	{
		return codDepartamento;
	}

	/**
	 * Sets the codMunicipio value for this InmuebleDTO.
	 *
	 * @param codMunicipio   * código DANE, longitud 5, dpto + municipio
	 */
	public void setCodMunicipio(java.lang.String codMunicipio)
	{
		this.codMunicipio = codMunicipio;
	}

	/**
	 * Gets the codMunicipio value for this InmuebleDTO.
	 *
	 * @return codMunicipio   * código DANE, longitud 5, dpto + municipio
	 */
	public java.lang.String getCodMunicipio()
	{
		return codMunicipio;
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
	 * Sets the direccionPredio value for this InmuebleDTO.
	 *
	 * @param direccionPredio   * Corresponde a la dirección del predio
	 */
	public void setDireccionPredio(java.lang.String direccionPredio)
	{
		this.direccionPredio = direccionPredio;
	}

	/**
	 * Gets the direccionPredio value for this InmuebleDTO.
	 *
	 * @return direccionPredio   * Corresponde a la dirección del predio
	 */
	public java.lang.String getDireccionPredio()
	{
		return direccionPredio;
	}

	/**
	 * Sets the NUPRE value for this InmuebleDTO.
	 *
	 * @param NUPRE   * Número Único Predial
	 */
	public void setNUPRE(java.lang.String NUPRE)
	{
		this.NUPRE = NUPRE;
	}

	/**
	 * Gets the NUPRE value for this InmuebleDTO.
	 *
	 * @return NUPRE   * Número Único Predial
	 */
	public java.lang.String getNUPRE()
	{
		return NUPRE;
	}

	/**
	 * Sets the nomCirculoRegistral value for this InmuebleDTO.
	 *
	 * @param nomCirculoRegistral   * Corresponde al nombre del círculo registral al
	     *                                 que pertenece el predio
	 */
	public void setNomCirculoRegistral(java.lang.String nomCirculoRegistral)
	{
		this.nomCirculoRegistral = nomCirculoRegistral;
	}

	/**
	 * Gets the nomCirculoRegistral value for this InmuebleDTO.
	 *
	 * @return nomCirculoRegistral   * Corresponde al nombre del círculo registral al
	     *                                 que pertenece el predio
	 */
	public java.lang.String getNomCirculoRegistral()
	{
		return nomCirculoRegistral;
	}

	/**
	 * Sets the nomDepartamento value for this InmuebleDTO.
	 *
	 * @param nomDepartamento   * Corresponde al nombre del departamento en
	     *                                 dondese ubica el predio
	 */
	public void setNomDepartamento(java.lang.String nomDepartamento)
	{
		this.nomDepartamento = nomDepartamento;
	}

	/**
	 * Gets the nomDepartamento value for this InmuebleDTO.
	 *
	 * @return nomDepartamento   * Corresponde al nombre del departamento en
	     *                                 dondese ubica el predio
	 */
	public java.lang.String getNomDepartamento()
	{
		return nomDepartamento;
	}

	/**
	 * Sets the nomMunicipio value for this InmuebleDTO.
	 *
	 * @param nomMunicipio   * Nombre del municipio según DANE donde está
	     *                                 ubicado el predio.
	 */
	public void setNomMunicipio(java.lang.String nomMunicipio)
	{
		this.nomMunicipio = nomMunicipio;
	}

	/**
	 * Gets the nomMunicipio value for this InmuebleDTO.
	 *
	 * @return nomMunicipio   * Nombre del municipio según DANE donde está
	     *                                 ubicado el predio.
	 */
	public java.lang.String getNomMunicipio()
	{
		return nomMunicipio;
	}

	/**
	 * Sets the numMatriculaInmobiliaria value for this InmuebleDTO.
	 *
	 * @param numMatriculaInmobiliaria   * Número de matrícula inmobiliaria o número de
	     *                                 folio con el que se identifica el predio.
	 */
	public void setNumMatriculaInmobiliaria(java.lang.String numMatriculaInmobiliaria)
	{
		this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
	}

	/**
	 * Gets the numMatriculaInmobiliaria value for this InmuebleDTO.
	 *
	 * @return numMatriculaInmobiliaria   * Número de matrícula inmobiliaria o número de
	     *                                 folio con el que se identifica el predio.
	 */
	public java.lang.String getNumMatriculaInmobiliaria()
	{
		return numMatriculaInmobiliaria;
	}

	/**
	 * Sets the numPredial value for this InmuebleDTO.
	 *
	 * @param numPredial   * Corresponde al CHIP, si es Bogotá o a la
	     *                                 referencia o cédula catastral del predio.
	 */
	public void setNumPredial(java.lang.String numPredial)
	{
		this.numPredial = numPredial;
	}

	/**
	 * Gets the numPredial value for this InmuebleDTO.
	 *
	 * @return numPredial   * Corresponde al CHIP, si es Bogotá o a la
	     *                                 referencia o cédula catastral del predio.
	 */
	public java.lang.String getNumPredial()
	{
		return numPredial;
	}

	/**
	 * Sets the propietarios value for this InmuebleDTO.
	 *
	 * @param propietarios   * Corresponde al número de propietarios del
	     *                                 predio
	 */
	public void setPropietarios(
	    co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.PropietarioDTO[] propietarios
	)
	{
		this.propietarios = propietarios;
	}

	/**
	 * Sets the propietarios.
	 *
	 * @param i correspondiente al valor de i
	 * @param _value correspondiente al valor de value
	 */
	public void setPropietarios(
	    int i, co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.PropietarioDTO _value
	)
	{
		this.propietarios[i] = _value;
	}

	/**
	 * Gets the propietarios value for this InmuebleDTO.
	 *
	 * @return propietarios   * Corresponde al número de propietarios del
	     *                                 predio
	 */
	public co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.PropietarioDTO[] getPropietarios()
	{
		return propietarios;
	}

	/**
	 * Retorna Objeto o variable de valor propietarios.
	 *
	 * @param i correspondiente al valor de i
	 * @return el valor de propietarios
	 */
	public co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.PropietarioDTO getPropietarios(
	    int i
	)
	{
		return this.propietarios[i];
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

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof InmuebleDTO))
			return false;

		InmuebleDTO other = (InmuebleDTO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codCirculoRegistral == null) && (other.getCodCirculoRegistral() == null))
				|| ((this.codCirculoRegistral != null)
				&& this.codCirculoRegistral.equals(other.getCodCirculoRegistral())))
				&& (((this.codDepartamento == null) && (other.getCodDepartamento() == null))
				|| ((this.codDepartamento != null) && this.codDepartamento.equals(other.getCodDepartamento())))
				&& (((this.codMunicipio == null) && (other.getCodMunicipio() == null))
				|| ((this.codMunicipio != null) && this.codMunicipio.equals(other.getCodMunicipio())))
				&& (((this.direccionPredio == null) && (other.getDireccionPredio() == null))
				|| ((this.direccionPredio != null) && this.direccionPredio.equals(other.getDireccionPredio())))
				&& (((this.numMatriculaInmobiliaria == null) && (other.getNumMatriculaInmobiliaria() == null))
				|| ((this.numMatriculaInmobiliaria != null)
				&& this.numMatriculaInmobiliaria.equals(other.getNumMatriculaInmobiliaria())))
				&& (((this.numPredial == null) && (other.getNumPredial() == null))
				|| ((this.numPredial != null) && this.numPredial.equals(other.getNumPredial())))
				&& (((this.NUPRE == null) && (other.getNUPRE() == null))
				|| ((this.NUPRE != null) && this.NUPRE.equals(other.getNUPRE())))
				&& (((this.nomCirculoRegistral == null) && (other.getNomCirculoRegistral() == null))
				|| ((this.nomCirculoRegistral != null)
				&& this.nomCirculoRegistral.equals(other.getNomCirculoRegistral())))
				&& (((this.nomDepartamento == null) && (other.getNomDepartamento() == null))
				|| ((this.nomDepartamento != null) && this.nomDepartamento.equals(other.getNomDepartamento())))
				&& (((this.nomMunicipio == null) && (other.getNomMunicipio() == null))
				|| ((this.nomMunicipio != null) && this.nomMunicipio.equals(other.getNomMunicipio())))
				&& (((this.propietarios == null) && (other.getPropietarios() == null))
				|| ((this.propietarios != null) && java.util.Arrays.equals(this.propietarios, other.getPropietarios())));
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

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

		if(getCodDepartamento() != null)
			_hashCode += getCodDepartamento().hashCode();

		if(getCodMunicipio() != null)
			_hashCode += getCodMunicipio().hashCode();

		if(getDireccionPredio() != null)
			_hashCode += getDireccionPredio().hashCode();

		if(getNumMatriculaInmobiliaria() != null)
			_hashCode += getNumMatriculaInmobiliaria().hashCode();

		if(getNumPredial() != null)
			_hashCode += getNumPredial().hashCode();

		if(getNUPRE() != null)
			_hashCode += getNUPRE().hashCode();

		if(getNomCirculoRegistral() != null)
			_hashCode += getNomCirculoRegistral().hashCode();

		if(getNomDepartamento() != null)
			_hashCode += getNomDepartamento().hashCode();

		if(getNomMunicipio() != null)
			_hashCode += getNomMunicipio().hashCode();

		if(getPropietarios() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getPropietarios()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getPropietarios(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
