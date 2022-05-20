/**
 * TipoEntradaCrearProcAntiguoSistemaTierras.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaCrearProcAntiguoSistemaTierras.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoEntradaCrearProcAntiguoSistemaTierras implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7218720081867876618L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaCrearProcAntiguoSistemaTierras.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "tipoEntradaCrearProcAntiguoSistemaTierras"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idAlerta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "idAlerta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "codCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codPais");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "codPais"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "codDepartamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMunicipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "codMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "tipoPredio"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        ">tipoEntradaCrearProcAntiguoSistemaTierras>tipoPredio"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numLibro");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "numLibro"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numTomo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "numTomo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idTipoPartida");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "idTipoPartida"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        ">tipoEntradaCrearProcAntiguoSistemaTierras>idTipoPartida"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numPartida");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "numPartida"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numFolio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "numFolio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("anio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "anio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "nomPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "numPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad anio. */
	private java.math.BigInteger anio;

	/** Propiedad cod circulo registral. */
	private java.lang.String codCirculoRegistral;

	/** Propiedad id tipo partida. */
	private co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierrasIdTipoPartida idTipoPartida;

	/** Propiedad nom predio. */
	private java.lang.String nomPredio;

	/** Propiedad num predio. */
	private java.lang.Integer numPredio;

	/** Propiedad tipo predio. */
	private co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierrasTipoPredio tipoPredio;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/** Propiedad cod departamento. */
	private int codDepartamento;

	/** Propiedad cod municipio. */
	private int codMunicipio;

	/** Propiedad cod pais. */
	private int codPais;

	/** Propiedad id alerta. */
	private int idAlerta;

	/** Propiedad num folio. */
	private int numFolio;

	/** Propiedad num libro. */
	private int numLibro;

	/** Propiedad num partida. */
	private int numPartida;

	/** Propiedad num tomo. */
	private int numTomo;

	/**
	 * Instancia un nuevo objeto tipo entrada crear proc antiguo sistema tierras.
	 */
	public TipoEntradaCrearProcAntiguoSistemaTierras()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada crear proc antiguo sistema tierras.
	 *
	 * @param idAlerta de id alerta
	 * @param codCirculoRegistral de cod circulo registral
	 * @param codPais de cod pais
	 * @param codDepartamento de cod departamento
	 * @param codMunicipio de cod municipio
	 * @param tipoPredio de tipo predio
	 * @param numLibro de num libro
	 * @param numTomo de num tomo
	 * @param idTipoPartida de id tipo partida
	 * @param numPartida de num partida
	 * @param numFolio de num folio
	 * @param anio de anio
	 * @param nomPredio de nom predio
	 * @param numPredio de num predio
	 */
	public TipoEntradaCrearProcAntiguoSistemaTierras(
	    int idAlerta, java.lang.String codCirculoRegistral, int codPais, int codDepartamento, int codMunicipio,
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierrasTipoPredio tipoPredio,
	    int numLibro, int numTomo,
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierrasIdTipoPartida idTipoPartida,
	    int numPartida, int numFolio, java.math.BigInteger anio, java.lang.String nomPredio, java.lang.Integer numPredio
	)
	{
		this.idAlerta                = idAlerta;
		this.codCirculoRegistral     = codCirculoRegistral;
		this.codPais                 = codPais;
		this.codDepartamento         = codDepartamento;
		this.codMunicipio            = codMunicipio;
		this.tipoPredio              = tipoPredio;
		this.numLibro                = numLibro;
		this.numTomo                 = numTomo;
		this.idTipoPartida           = idTipoPartida;
		this.numPartida              = numPartida;
		this.numFolio                = numFolio;
		this.anio                    = anio;
		this.nomPredio               = nomPredio;
		this.numPredio               = numPredio;
	}

	/**
	 * Gets the idAlerta value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @return idAlerta
	 */
	public int getIdAlerta()
	{
		return idAlerta;
	}

	/**
	 * Sets the idAlerta value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @param idAlerta de id alerta
	 */
	public void setIdAlerta(int idAlerta)
	{
		this.idAlerta = idAlerta;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @return codCirculoRegistral
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @param codCirculoRegistral de cod circulo registral
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the codPais value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @return codPais
	 */
	public int getCodPais()
	{
		return codPais;
	}

	/**
	 * Sets the codPais value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @param codPais de cod pais
	 */
	public void setCodPais(int codPais)
	{
		this.codPais = codPais;
	}

	/**
	 * Gets the codDepartamento value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @return codDepartamento
	 */
	public int getCodDepartamento()
	{
		return codDepartamento;
	}

	/**
	 * Sets the codDepartamento value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @param codDepartamento de cod departamento
	 */
	public void setCodDepartamento(int codDepartamento)
	{
		this.codDepartamento = codDepartamento;
	}

	/**
	 * Gets the codMunicipio value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @return codMunicipio
	 */
	public int getCodMunicipio()
	{
		return codMunicipio;
	}

	/**
	 * Sets the codMunicipio value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @param codMunicipio de cod municipio
	 */
	public void setCodMunicipio(int codMunicipio)
	{
		this.codMunicipio = codMunicipio;
	}

	/**
	 * Gets the tipoPredio value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @return tipoPredio
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierrasTipoPredio getTipoPredio()
	{
		return tipoPredio;
	}

	/**
	 * Sets the tipoPredio value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @param tipoPredio de tipo predio
	 */
	public void setTipoPredio(
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierrasTipoPredio tipoPredio
	)
	{
		this.tipoPredio = tipoPredio;
	}

	/**
	 * Gets the numLibro value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @return numLibro
	 */
	public int getNumLibro()
	{
		return numLibro;
	}

	/**
	 * Sets the numLibro value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @param numLibro de num libro
	 */
	public void setNumLibro(int numLibro)
	{
		this.numLibro = numLibro;
	}

	/**
	 * Gets the numTomo value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @return numTomo
	 */
	public int getNumTomo()
	{
		return numTomo;
	}

	/**
	 * Sets the numTomo value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @param numTomo de num tomo
	 */
	public void setNumTomo(int numTomo)
	{
		this.numTomo = numTomo;
	}

	/**
	 * Gets the idTipoPartida value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @return idTipoPartida
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierrasIdTipoPartida getIdTipoPartida()
	{
		return idTipoPartida;
	}

	/**
	 * Sets the idTipoPartida value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @param idTipoPartida de id tipo partida
	 */
	public void setIdTipoPartida(
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierrasIdTipoPartida idTipoPartida
	)
	{
		this.idTipoPartida = idTipoPartida;
	}

	/**
	 * Gets the numPartida value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @return numPartida
	 */
	public int getNumPartida()
	{
		return numPartida;
	}

	/**
	 * Sets the numPartida value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @param numPartida de num partida
	 */
	public void setNumPartida(int numPartida)
	{
		this.numPartida = numPartida;
	}

	/**
	 * Gets the numFolio value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @return numFolio
	 */
	public int getNumFolio()
	{
		return numFolio;
	}

	/**
	 * Sets the numFolio value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @param numFolio de num folio
	 */
	public void setNumFolio(int numFolio)
	{
		this.numFolio = numFolio;
	}

	/**
	 * Gets the anio value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @return anio
	 */
	public java.math.BigInteger getAnio()
	{
		return anio;
	}

	/**
	 * Sets the anio value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @param anio de anio
	 */
	public void setAnio(java.math.BigInteger anio)
	{
		this.anio = anio;
	}

	/**
	 * Gets the nomPredio value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @return nomPredio
	 */
	public java.lang.String getNomPredio()
	{
		return nomPredio;
	}

	/**
	 * Sets the nomPredio value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @param nomPredio de nom predio
	 */
	public void setNomPredio(java.lang.String nomPredio)
	{
		this.nomPredio = nomPredio;
	}

	/**
	 * Gets the numPredio value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @return numPredio
	 */
	public java.lang.Integer getNumPredio()
	{
		return numPredio;
	}

	/**
	 * Sets the numPredio value for this TipoEntradaCrearProcAntiguoSistemaTierras.
	 *
	 * @param numPredio de num predio
	 */
	public void setNumPredio(java.lang.Integer numPredio)
	{
		this.numPredio = numPredio;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaCrearProcAntiguoSistemaTierras))
			return false;

		TipoEntradaCrearProcAntiguoSistemaTierras other = (TipoEntradaCrearProcAntiguoSistemaTierras)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true && (this.idAlerta == other.getIdAlerta())
				&& (((this.codCirculoRegistral == null) && (other.getCodCirculoRegistral() == null))
				|| ((this.codCirculoRegistral != null)
				&& this.codCirculoRegistral.equals(other.getCodCirculoRegistral())))
				&& (this.codPais == other.getCodPais()) && (this.codDepartamento == other.getCodDepartamento())
				&& (this.codMunicipio == other.getCodMunicipio())
				&& (((this.tipoPredio == null) && (other.getTipoPredio() == null))
				|| ((this.tipoPredio != null) && this.tipoPredio.equals(other.getTipoPredio())))
				&& (this.numLibro == other.getNumLibro()) && (this.numTomo == other.getNumTomo())
				&& (((this.idTipoPartida == null) && (other.getIdTipoPartida() == null))
				|| ((this.idTipoPartida != null) && this.idTipoPartida.equals(other.getIdTipoPartida())))
				&& (this.numPartida == other.getNumPartida()) && (this.numFolio == other.getNumFolio())
				&& (((this.anio == null) && (other.getAnio() == null))
				|| ((this.anio != null) && this.anio.equals(other.getAnio())))
				&& (((this.nomPredio == null) && (other.getNomPredio() == null))
				|| ((this.nomPredio != null) && this.nomPredio.equals(other.getNomPredio())))
				&& (((this.numPredio == null) && (other.getNumPredio() == null))
				|| ((this.numPredio != null) && this.numPredio.equals(other.getNumPredio())));
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
		_hashCode += getIdAlerta();

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

		_hashCode += getCodPais();
		_hashCode += getCodDepartamento();
		_hashCode += getCodMunicipio();

		if(getTipoPredio() != null)
			_hashCode += getTipoPredio().hashCode();

		_hashCode += getNumLibro();
		_hashCode += getNumTomo();

		if(getIdTipoPartida() != null)
			_hashCode += getIdTipoPartida().hashCode();

		_hashCode += getNumPartida();
		_hashCode += getNumFolio();

		if(getAnio() != null)
			_hashCode += getAnio().hashCode();

		if(getNomPredio() != null)
			_hashCode += getNomPredio().hashCode();

		if(getNumPredio() != null)
			_hashCode += getNumPredio().hashCode();

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
