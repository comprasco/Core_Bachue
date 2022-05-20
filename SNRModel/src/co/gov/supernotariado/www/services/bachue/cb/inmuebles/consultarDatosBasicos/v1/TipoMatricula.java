/**
 * TipoMatricula.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1;



/**
 * Clase que contiene todos las propiedades TipoMatricula.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 19/03/2020
 */
public class TipoMatricula implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3771970305354730759L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoMatricula.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "tipoMatricula"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estadoMatricula");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "estadoMatricula"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numMatriculaInmobiliaria");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "numMatriculaInmobiliaria"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "codCirculoRegistral"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "nomCirculoRegistral"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1", "numPredial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estadoNUPRE");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1", "estadoNUPRE"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1", "NUPRE"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "direccionPredio"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "codDepartamento"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "nomDepartamento"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "codMunicipio"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "nomMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1", "tipoPredio"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "propietarios"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "tipoPropietario"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad nupre. */
	/* Numero predial único  */
	private java.lang.String NUPRE;

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod circulo registral. */
	/* Corresponde al número identificador de la ORIP */
	private java.lang.String codCirculoRegistral;

	/** Propiedad cod departamento. */
	/* Coresponde al código DANE */
	private java.lang.String codDepartamento;

	/** Propiedad cod municipio. */
	/* Coresponde al código DANE - Alfanumérico 5, 2
	 *                                 del codDepartamento y 3 de municipio */
	private java.lang.String codMunicipio;

	/** Propiedad direccion predio. */
	/* Dirección lugar del predio  */
	private java.lang.String direccionPredio;

	/** Propiedad estado matricula. */
	/* Estado de la matrícula. Solo aplica para ORIPS
	 *                                 no migradas */
	private java.lang.Integer estadoMatricula;

	/** Propiedad estado NUPRE. */
	/* Corresponde a los estados del NUPRE: G
	 *                                 (Gestor,
	 *                                 R(Registro), C (Autoridad catastral), T(Temporal).  */
	private java.lang.String estadoNUPRE;

	/** Propiedad nom circulo registral. */
	/* Corresponde al nombre de la ORIP */
	private java.lang.String nomCirculoRegistral;

	/** Propiedad nom departamento. */
	/* Departamento al que pertenece el predio  */
	private java.lang.String nomDepartamento;

	/** Propiedad nom municipio. */
	/* Municipio al que pertenece el predio  */
	private java.lang.String nomMunicipio;

	/** Propiedad num matricula inmobiliaria. */
	/* Folio de matrícula inmobiliaria del predio.  */
	private java.lang.String numMatriculaInmobiliaria;

	/** Propiedad num predial. */
	/* Corresponde al chip para los predios de
	 *                                 Bogotá,
	 *                                 cédula o referencia catastral. */
	private java.lang.String numPredial;

	/** Propiedad tipo predio. */
	/* Tipo de predio: Rural o Urbano  */
	private java.lang.String tipoPredio;

	/** Propiedad propietarios. */
	private co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoPropietario[] propietarios;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo matricula.
	 */
	public TipoMatricula()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo matricula.
	 *
	 * @param estadoMatricula de estado matricula
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 * @param codCirculoRegistral de cod circulo registral
	 * @param nomCirculoRegistral de nom circulo registral
	 * @param numPredial de num predial
	 * @param estadoNUPRE de estado NUPRE
	 * @param NUPRE de nupre
	 * @param direccionPredio de direccion predio
	 * @param codDepartamento de cod departamento
	 * @param nomDepartamento de nom departamento
	 * @param codMunicipio de cod municipio
	 * @param nomMunicipio de nom municipio
	 * @param tipoPredio de tipo predio
	 * @param propietarios de propietarios
	 */
	public TipoMatricula(
	    java.lang.Integer estadoMatricula, java.lang.String numMatriculaInmobiliaria,
	    java.lang.String codCirculoRegistral, java.lang.String nomCirculoRegistral, java.lang.String numPredial,
	    java.lang.String estadoNUPRE, java.lang.String NUPRE, java.lang.String direccionPredio,
	    java.lang.String codDepartamento, java.lang.String nomDepartamento, java.lang.String codMunicipio,
	    java.lang.String nomMunicipio, java.lang.String tipoPredio,
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoPropietario[] propietarios
	)
	{
		this.estadoMatricula              = estadoMatricula;
		this.numMatriculaInmobiliaria     = numMatriculaInmobiliaria;
		this.codCirculoRegistral          = codCirculoRegistral;
		this.nomCirculoRegistral          = nomCirculoRegistral;
		this.numPredial                   = numPredial;
		this.estadoNUPRE                  = estadoNUPRE;
		this.NUPRE                        = NUPRE;
		this.direccionPredio              = direccionPredio;
		this.codDepartamento              = codDepartamento;
		this.nomDepartamento              = nomDepartamento;
		this.codMunicipio                 = codMunicipio;
		this.nomMunicipio                 = nomMunicipio;
		this.tipoPredio                   = tipoPredio;
		this.propietarios                 = propietarios;
	}

	/**
	 * Gets the estadoMatricula value for this TipoMatricula.
	 *
	 * @return estadoMatricula   * Estado de la matrícula. Solo aplica para ORIPS
	     *                                 no migradas
	 */
	public java.lang.Integer getEstadoMatricula()
	{
		return estadoMatricula;
	}

	/**
	 * Sets the estadoMatricula value for this TipoMatricula.
	 *
	 * @param estadoMatricula   * Estado de la matrícula. Solo aplica para ORIPS
	     *                                 no migradas
	 */
	public void setEstadoMatricula(java.lang.Integer estadoMatricula)
	{
		this.estadoMatricula = estadoMatricula;
	}

	/**
	 * Gets the numMatriculaInmobiliaria value for this TipoMatricula.
	 *
	 * @return numMatriculaInmobiliaria   * Folio de matrícula inmobiliaria del predio. 
	 */
	public java.lang.String getNumMatriculaInmobiliaria()
	{
		return numMatriculaInmobiliaria;
	}

	/**
	 * Sets the numMatriculaInmobiliaria value for this TipoMatricula.
	 *
	 * @param numMatriculaInmobiliaria   * Folio de matrícula inmobiliaria del predio. 
	 */
	public void setNumMatriculaInmobiliaria(java.lang.String numMatriculaInmobiliaria)
	{
		this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoMatricula.
	 *
	 * @return codCirculoRegistral   * Corresponde al número identificador de la ORIP
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoMatricula.
	 *
	 * @param codCirculoRegistral   * Corresponde al número identificador de la ORIP
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the nomCirculoRegistral value for this TipoMatricula.
	 *
	 * @return nomCirculoRegistral   * Corresponde al nombre de la ORIP
	 */
	public java.lang.String getNomCirculoRegistral()
	{
		return nomCirculoRegistral;
	}

	/**
	 * Sets the nomCirculoRegistral value for this TipoMatricula.
	 *
	 * @param nomCirculoRegistral   * Corresponde al nombre de la ORIP
	 */
	public void setNomCirculoRegistral(java.lang.String nomCirculoRegistral)
	{
		this.nomCirculoRegistral = nomCirculoRegistral;
	}

	/**
	 * Gets the numPredial value for this TipoMatricula.
	 *
	 * @return numPredial   * Corresponde al chip para los predios de
	     *                                 Bogotá,
	     *                                 cédula o referencia catastral.
	 */
	public java.lang.String getNumPredial()
	{
		return numPredial;
	}

	/**
	 * Sets the numPredial value for this TipoMatricula.
	 *
	 * @param numPredial   * Corresponde al chip para los predios de
	     *                                 Bogotá,
	     *                                 cédula o referencia catastral.
	 */
	public void setNumPredial(java.lang.String numPredial)
	{
		this.numPredial = numPredial;
	}

	/**
	 * Gets the estadoNUPRE value for this TipoMatricula.
	 *
	 * @return estadoNUPRE   * Corresponde a los estados del NUPRE: G
	     *                                 (Gestor,
	     *                                 R(Registro), C (Autoridad catastral), T(Temporal). 
	 */
	public java.lang.String getEstadoNUPRE()
	{
		return estadoNUPRE;
	}

	/**
	 * Sets the estadoNUPRE value for this TipoMatricula.
	 *
	 * @param estadoNUPRE   * Corresponde a los estados del NUPRE: G
	     *                                 (Gestor,
	     *                                 R(Registro), C (Autoridad catastral), T(Temporal). 
	 */
	public void setEstadoNUPRE(java.lang.String estadoNUPRE)
	{
		this.estadoNUPRE = estadoNUPRE;
	}

	/**
	 * Gets the NUPRE value for this TipoMatricula.
	 *
	 * @return NUPRE   * Numero predial único 
	 */
	public java.lang.String getNUPRE()
	{
		return NUPRE;
	}

	/**
	 * Sets the NUPRE value for this TipoMatricula.
	 *
	 * @param NUPRE   * Numero predial único 
	 */
	public void setNUPRE(java.lang.String NUPRE)
	{
		this.NUPRE = NUPRE;
	}

	/**
	 * Gets the direccionPredio value for this TipoMatricula.
	 *
	 * @return direccionPredio   * Dirección lugar del predio 
	 */
	public java.lang.String getDireccionPredio()
	{
		return direccionPredio;
	}

	/**
	 * Sets the direccionPredio value for this TipoMatricula.
	 *
	 * @param direccionPredio   * Dirección lugar del predio 
	 */
	public void setDireccionPredio(java.lang.String direccionPredio)
	{
		this.direccionPredio = direccionPredio;
	}

	/**
	 * Gets the codDepartamento value for this TipoMatricula.
	 *
	 * @return codDepartamento   * Coresponde al código DANE
	 */
	public java.lang.String getCodDepartamento()
	{
		return codDepartamento;
	}

	/**
	 * Sets the codDepartamento value for this TipoMatricula.
	 *
	 * @param codDepartamento   * Coresponde al código DANE
	 */
	public void setCodDepartamento(java.lang.String codDepartamento)
	{
		this.codDepartamento = codDepartamento;
	}

	/**
	 * Gets the nomDepartamento value for this TipoMatricula.
	 *
	 * @return nomDepartamento   * Departamento al que pertenece el predio 
	 */
	public java.lang.String getNomDepartamento()
	{
		return nomDepartamento;
	}

	/**
	 * Sets the nomDepartamento value for this TipoMatricula.
	 *
	 * @param nomDepartamento   * Departamento al que pertenece el predio 
	 */
	public void setNomDepartamento(java.lang.String nomDepartamento)
	{
		this.nomDepartamento = nomDepartamento;
	}

	/**
	 * Gets the codMunicipio value for this TipoMatricula.
	 *
	 * @return codMunicipio   * Coresponde al código DANE - Alfanumérico 5, 2
	     *                                 del codDepartamento y 3 de municipio
	 */
	public java.lang.String getCodMunicipio()
	{
		return codMunicipio;
	}

	/**
	 * Sets the codMunicipio value for this TipoMatricula.
	 *
	 * @param codMunicipio   * Coresponde al código DANE - Alfanumérico 5, 2
	     *                                 del codDepartamento y 3 de municipio
	 */
	public void setCodMunicipio(java.lang.String codMunicipio)
	{
		this.codMunicipio = codMunicipio;
	}

	/**
	 * Gets the nomMunicipio value for this TipoMatricula.
	 *
	 * @return nomMunicipio   * Municipio al que pertenece el predio 
	 */
	public java.lang.String getNomMunicipio()
	{
		return nomMunicipio;
	}

	/**
	 * Sets the nomMunicipio value for this TipoMatricula.
	 *
	 * @param nomMunicipio   * Municipio al que pertenece el predio 
	 */
	public void setNomMunicipio(java.lang.String nomMunicipio)
	{
		this.nomMunicipio = nomMunicipio;
	}

	/**
	 * Gets the tipoPredio value for this TipoMatricula.
	 *
	 * @return tipoPredio   * Tipo de predio: Rural o Urbano 
	 */
	public java.lang.String getTipoPredio()
	{
		return tipoPredio;
	}

	/**
	 * Sets the tipoPredio value for this TipoMatricula.
	 *
	 * @param tipoPredio   * Tipo de predio: Rural o Urbano 
	 */
	public void setTipoPredio(java.lang.String tipoPredio)
	{
		this.tipoPredio = tipoPredio;
	}

	/**
	 * Gets the propietarios value for this TipoMatricula.
	 *
	 * @return propietarios
	 */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoPropietario[] getPropietarios()
	{
		return propietarios;
	}

	/**
	 * Sets the propietarios value for this TipoMatricula.
	 *
	 * @param propietarios de propietarios
	 */
	public void setPropietarios(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoPropietario[] propietarios
	)
	{
		this.propietarios = propietarios;
	}

	/**
	 * Retorna Objeto o variable de valor propietarios.
	 *
	 * @param i de i
	 * @return el valor de propietarios
	 */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoPropietario getPropietarios(
	    int i
	)
	{
		return this.propietarios[i];
	}

	/**
	 * Cambia el valor de propietarios.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setPropietarios(
	    int i, co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoPropietario _value
	)
	{
		this.propietarios[i] = _value;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoMatricula))
			return false;

		TipoMatricula other = (TipoMatricula)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.estadoMatricula == null) && (other.getEstadoMatricula() == null))
				|| ((this.estadoMatricula != null) && this.estadoMatricula.equals(other.getEstadoMatricula())))
				&& (((this.numMatriculaInmobiliaria == null) && (other.getNumMatriculaInmobiliaria() == null))
				|| ((this.numMatriculaInmobiliaria != null)
				&& this.numMatriculaInmobiliaria.equals(other.getNumMatriculaInmobiliaria())))
				&& (((this.codCirculoRegistral == null) && (other.getCodCirculoRegistral() == null))
				|| ((this.codCirculoRegistral != null)
				&& this.codCirculoRegistral.equals(other.getCodCirculoRegistral())))
				&& (((this.nomCirculoRegistral == null) && (other.getNomCirculoRegistral() == null))
				|| ((this.nomCirculoRegistral != null)
				&& this.nomCirculoRegistral.equals(other.getNomCirculoRegistral())))
				&& (((this.numPredial == null) && (other.getNumPredial() == null))
				|| ((this.numPredial != null) && this.numPredial.equals(other.getNumPredial())))
				&& (((this.estadoNUPRE == null) && (other.getEstadoNUPRE() == null))
				|| ((this.estadoNUPRE != null) && this.estadoNUPRE.equals(other.getEstadoNUPRE())))
				&& (((this.NUPRE == null) && (other.getNUPRE() == null))
				|| ((this.NUPRE != null) && this.NUPRE.equals(other.getNUPRE())))
				&& (((this.direccionPredio == null) && (other.getDireccionPredio() == null))
				|| ((this.direccionPredio != null) && this.direccionPredio.equals(other.getDireccionPredio())))
				&& (((this.codDepartamento == null) && (other.getCodDepartamento() == null))
				|| ((this.codDepartamento != null) && this.codDepartamento.equals(other.getCodDepartamento())))
				&& (((this.nomDepartamento == null) && (other.getNomDepartamento() == null))
				|| ((this.nomDepartamento != null) && this.nomDepartamento.equals(other.getNomDepartamento())))
				&& (((this.codMunicipio == null) && (other.getCodMunicipio() == null))
				|| ((this.codMunicipio != null) && this.codMunicipio.equals(other.getCodMunicipio())))
				&& (((this.nomMunicipio == null) && (other.getNomMunicipio() == null))
				|| ((this.nomMunicipio != null) && this.nomMunicipio.equals(other.getNomMunicipio())))
				&& (((this.tipoPredio == null) && (other.getTipoPredio() == null))
				|| ((this.tipoPredio != null) && this.tipoPredio.equals(other.getTipoPredio())))
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

		if(getEstadoMatricula() != null)
			_hashCode += getEstadoMatricula().hashCode();

		if(getNumMatriculaInmobiliaria() != null)
			_hashCode += getNumMatriculaInmobiliaria().hashCode();

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

		if(getNomCirculoRegistral() != null)
			_hashCode += getNomCirculoRegistral().hashCode();

		if(getNumPredial() != null)
			_hashCode += getNumPredial().hashCode();

		if(getEstadoNUPRE() != null)
			_hashCode += getEstadoNUPRE().hashCode();

		if(getNUPRE() != null)
			_hashCode += getNUPRE().hashCode();

		if(getDireccionPredio() != null)
			_hashCode += getDireccionPredio().hashCode();

		if(getCodDepartamento() != null)
			_hashCode += getCodDepartamento().hashCode();

		if(getNomDepartamento() != null)
			_hashCode += getNomDepartamento().hashCode();

		if(getCodMunicipio() != null)
			_hashCode += getCodMunicipio().hashCode();

		if(getNomMunicipio() != null)
			_hashCode += getNomMunicipio().hashCode();

		if(getTipoPredio() != null)
			_hashCode += getTipoPredio().hashCode();

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
