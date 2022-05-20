/**
 * TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1;



/**
 * Clase que contiene todos las propiedades TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1883147251499178236L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
		        ">>tipoSalidaListarProcAntiguoSistema>listaProcesosAS>procesoAS"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idAlerta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
		        "idAlerta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
		        "nomCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomPais");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
		        "nomPais"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
		        "nomDepartamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomMunicipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
		        "nomMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
		        "tipoPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numLibro");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
		        "idTipoPartida"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numPartida");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1", "anio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
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

	/** Propiedad id tipo partida. */
	private java.lang.String idTipoPartida;

	/** Propiedad nom circulo registral. */
	private java.lang.String nomCirculoRegistral;

	/** Propiedad nom departamento. */
	private java.lang.String nomDepartamento;

	/** Propiedad nom municipio. */
	private java.lang.String nomMunicipio;

	/** Propiedad nom pais. */
	private java.lang.String nomPais;

	/** Propiedad nom predio. */
	private java.lang.String nomPredio;

	/** Propiedad num predio. */
	private java.lang.Integer numPredio;

	/** Propiedad tipo predio. */
	private java.lang.String tipoPredio;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/** Propiedad anio. */
	private int anio;

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
	 * Instancia un nuevo objeto tipo salida listar proc antiguo sistema lista procesos AS proceso AS.
	 */
	public TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida listar proc antiguo sistema lista procesos AS proceso AS.
	 *
	 * @param idAlerta de id alerta
	 * @param nomCirculoRegistral de nom circulo registral
	 * @param nomPais de nom pais
	 * @param nomDepartamento de nom departamento
	 * @param nomMunicipio de nom municipio
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
	public TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS(
	    int idAlerta, java.lang.String nomCirculoRegistral, java.lang.String nomPais, java.lang.String nomDepartamento,
	    java.lang.String nomMunicipio, java.lang.String tipoPredio, int numLibro, int numTomo,
	    java.lang.String idTipoPartida, int numPartida, int numFolio, int anio, java.lang.String nomPredio,
	    java.lang.Integer numPredio
	)
	{
		this.idAlerta                = idAlerta;
		this.nomCirculoRegistral     = nomCirculoRegistral;
		this.nomPais                 = nomPais;
		this.nomDepartamento         = nomDepartamento;
		this.nomMunicipio            = nomMunicipio;
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
	 * Gets the idAlerta value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @return idAlerta
	 */
	public int getIdAlerta()
	{
		return idAlerta;
	}

	/**
	 * Sets the idAlerta value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @param idAlerta de id alerta
	 */
	public void setIdAlerta(int idAlerta)
	{
		this.idAlerta = idAlerta;
	}

	/**
	 * Gets the nomCirculoRegistral value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @return nomCirculoRegistral
	 */
	public java.lang.String getNomCirculoRegistral()
	{
		return nomCirculoRegistral;
	}

	/**
	 * Sets the nomCirculoRegistral value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @param nomCirculoRegistral de nom circulo registral
	 */
	public void setNomCirculoRegistral(java.lang.String nomCirculoRegistral)
	{
		this.nomCirculoRegistral = nomCirculoRegistral;
	}

	/**
	 * Gets the nomPais value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @return nomPais
	 */
	public java.lang.String getNomPais()
	{
		return nomPais;
	}

	/**
	 * Sets the nomPais value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @param nomPais de nom pais
	 */
	public void setNomPais(java.lang.String nomPais)
	{
		this.nomPais = nomPais;
	}

	/**
	 * Gets the nomDepartamento value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @return nomDepartamento
	 */
	public java.lang.String getNomDepartamento()
	{
		return nomDepartamento;
	}

	/**
	 * Sets the nomDepartamento value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @param nomDepartamento de nom departamento
	 */
	public void setNomDepartamento(java.lang.String nomDepartamento)
	{
		this.nomDepartamento = nomDepartamento;
	}

	/**
	 * Gets the nomMunicipio value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @return nomMunicipio
	 */
	public java.lang.String getNomMunicipio()
	{
		return nomMunicipio;
	}

	/**
	 * Sets the nomMunicipio value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @param nomMunicipio de nom municipio
	 */
	public void setNomMunicipio(java.lang.String nomMunicipio)
	{
		this.nomMunicipio = nomMunicipio;
	}

	/**
	 * Gets the tipoPredio value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @return tipoPredio
	 */
	public java.lang.String getTipoPredio()
	{
		return tipoPredio;
	}

	/**
	 * Sets the tipoPredio value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @param tipoPredio de tipo predio
	 */
	public void setTipoPredio(java.lang.String tipoPredio)
	{
		this.tipoPredio = tipoPredio;
	}

	/**
	 * Gets the numLibro value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @return numLibro
	 */
	public int getNumLibro()
	{
		return numLibro;
	}

	/**
	 * Sets the numLibro value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @param numLibro de num libro
	 */
	public void setNumLibro(int numLibro)
	{
		this.numLibro = numLibro;
	}

	/**
	 * Gets the numTomo value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @return numTomo
	 */
	public int getNumTomo()
	{
		return numTomo;
	}

	/**
	 * Sets the numTomo value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @param numTomo de num tomo
	 */
	public void setNumTomo(int numTomo)
	{
		this.numTomo = numTomo;
	}

	/**
	 * Gets the idTipoPartida value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @return idTipoPartida
	 */
	public java.lang.String getIdTipoPartida()
	{
		return idTipoPartida;
	}

	/**
	 * Sets the idTipoPartida value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @param idTipoPartida de id tipo partida
	 */
	public void setIdTipoPartida(java.lang.String idTipoPartida)
	{
		this.idTipoPartida = idTipoPartida;
	}

	/**
	 * Gets the numPartida value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @return numPartida
	 */
	public int getNumPartida()
	{
		return numPartida;
	}

	/**
	 * Sets the numPartida value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @param numPartida de num partida
	 */
	public void setNumPartida(int numPartida)
	{
		this.numPartida = numPartida;
	}

	/**
	 * Gets the numFolio value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @return numFolio
	 */
	public int getNumFolio()
	{
		return numFolio;
	}

	/**
	 * Sets the numFolio value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @param numFolio de num folio
	 */
	public void setNumFolio(int numFolio)
	{
		this.numFolio = numFolio;
	}

	/**
	 * Gets the anio value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @return anio
	 */
	public int getAnio()
	{
		return anio;
	}

	/**
	 * Sets the anio value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @param anio de anio
	 */
	public void setAnio(int anio)
	{
		this.anio = anio;
	}

	/**
	 * Gets the nomPredio value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @return nomPredio
	 */
	public java.lang.String getNomPredio()
	{
		return nomPredio;
	}

	/**
	 * Sets the nomPredio value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @param nomPredio de nom predio
	 */
	public void setNomPredio(java.lang.String nomPredio)
	{
		this.nomPredio = nomPredio;
	}

	/**
	 * Gets the numPredio value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
	 *
	 * @return numPredio
	 */
	public java.lang.Integer getNumPredio()
	{
		return numPredio;
	}

	/**
	 * Sets the numPredio value for this TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS.
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
		if(!(obj instanceof TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS))
			return false;

		TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS other = (TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true && (this.idAlerta == other.getIdAlerta())
				&& (((this.nomCirculoRegistral == null) && (other.getNomCirculoRegistral() == null))
				|| ((this.nomCirculoRegistral != null)
				&& this.nomCirculoRegistral.equals(other.getNomCirculoRegistral())))
				&& (((this.nomPais == null) && (other.getNomPais() == null))
				|| ((this.nomPais != null) && this.nomPais.equals(other.getNomPais())))
				&& (((this.nomDepartamento == null) && (other.getNomDepartamento() == null))
				|| ((this.nomDepartamento != null) && this.nomDepartamento.equals(other.getNomDepartamento())))
				&& (((this.nomMunicipio == null) && (other.getNomMunicipio() == null))
				|| ((this.nomMunicipio != null) && this.nomMunicipio.equals(other.getNomMunicipio())))
				&& (((this.tipoPredio == null) && (other.getTipoPredio() == null))
				|| ((this.tipoPredio != null) && this.tipoPredio.equals(other.getTipoPredio())))
				&& (this.numLibro == other.getNumLibro()) && (this.numTomo == other.getNumTomo())
				&& (((this.idTipoPartida == null) && (other.getIdTipoPartida() == null))
				|| ((this.idTipoPartida != null) && this.idTipoPartida.equals(other.getIdTipoPartida())))
				&& (this.numPartida == other.getNumPartida()) && (this.numFolio == other.getNumFolio())
				&& (this.anio == other.getAnio())
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

		if(getNomCirculoRegistral() != null)
			_hashCode += getNomCirculoRegistral().hashCode();

		if(getNomPais() != null)
			_hashCode += getNomPais().hashCode();

		if(getNomDepartamento() != null)
			_hashCode += getNomDepartamento().hashCode();

		if(getNomMunicipio() != null)
			_hashCode += getNomMunicipio().hashCode();

		if(getTipoPredio() != null)
			_hashCode += getTipoPredio().hashCode();

		_hashCode += getNumLibro();
		_hashCode += getNumTomo();

		if(getIdTipoPartida() != null)
			_hashCode += getIdTipoPartida().hashCode();

		_hashCode += getNumPartida();
		_hashCode += getNumFolio();
		_hashCode += getAnio();

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
