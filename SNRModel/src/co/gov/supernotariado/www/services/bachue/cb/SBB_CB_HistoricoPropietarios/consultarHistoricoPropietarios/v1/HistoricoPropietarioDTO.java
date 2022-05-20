/**
 * HistoricoPropietarioDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1;



/**
 * Clase que contiene todos las propiedades HistoricoPropietarioDTO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 20/03/2020
 */
public class HistoricoPropietarioDTO implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6464154806358405929L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    HistoricoPropietarioDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "historicoPropietarioDTO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numAnotacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "numAnotacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "codCirculoRegistral"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "porcentajeParticipacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaDesde");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "fechaDesde"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaHasta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "fechaHasta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numMatriculaInmobiliaria");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "numMatriculaInmobiliaria"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropietarios/v1",
		        "NUPRE"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad nupre. */
	/* Número Único Predial */
	private java.lang.String NUPRE;

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod circulo registral. */
	/* código de circulo u ORIP */
	private java.lang.String codCirculoRegistral;

	/** Propiedad fecha desde. */
	/* fecha desde la cual aparece como propietario */
	private java.util.Calendar fechaDesde;

	/** Propiedad fecha hasta. */
	/* fecha hasta la cual aparece como propietario */
	private java.util.Calendar fechaHasta;

	/** Propiedad num anotacion. */
	/* numero de anotacion en la que aparece como
	 *                                 propietario */
	private java.lang.Integer numAnotacion;

	/** Propiedad num matricula inmobiliaria. */
	/* número de matrícula del predio donde aparece
	 *                                 como propietario detro de
	 *                                 un periodo dado, salvo que no tenga
	 *                                 valor
	 *                                 en fecha hasta, para
	 *                                 este caso al momento de la consulta
	 *                                 todavía
	 *                                 aparece como
	 *                                 propietario */
	private java.lang.String numMatriculaInmobiliaria;

	/** Propiedad porcentaje participacion. */
	/* porcentaje de participación */
	private java.lang.String porcentajeParticipacion;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto historico propietario DTO.
	 */
	public HistoricoPropietarioDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto historico propietario DTO.
	 *
	 * @param numAnotacion de num anotacion
	 * @param codCirculoRegistral de cod circulo registral
	 * @param porcentajeParticipacion de porcentaje participacion
	 * @param fechaDesde de fecha desde
	 * @param fechaHasta de fecha hasta
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 * @param NUPRE de nupre
	 */
	public HistoricoPropietarioDTO(
	    java.lang.Integer numAnotacion, java.lang.String codCirculoRegistral, java.lang.String porcentajeParticipacion,
	    java.util.Calendar fechaDesde, java.util.Calendar fechaHasta, java.lang.String numMatriculaInmobiliaria,
	    java.lang.String NUPRE
	)
	{
		this.numAnotacion                 = numAnotacion;
		this.codCirculoRegistral          = codCirculoRegistral;
		this.porcentajeParticipacion      = porcentajeParticipacion;
		this.fechaDesde                   = fechaDesde;
		this.fechaHasta                   = fechaHasta;
		this.numMatriculaInmobiliaria     = numMatriculaInmobiliaria;
		this.NUPRE                        = NUPRE;
	}

	/**
	 * Sets the codCirculoRegistral value for this HistoricoPropietarioDTO.
	 *
	 * @param codCirculoRegistral   * código de circulo u ORIP
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the codCirculoRegistral value for this HistoricoPropietarioDTO.
	 *
	 * @return codCirculoRegistral   * código de circulo u ORIP
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
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
	 * Sets the fechaDesde value for this HistoricoPropietarioDTO.
	 *
	 * @param fechaDesde   * fecha desde la cual aparece como propietario
	 */
	public void setFechaDesde(java.util.Calendar fechaDesde)
	{
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fechaDesde value for this HistoricoPropietarioDTO.
	 *
	 * @return fechaDesde   * fecha desde la cual aparece como propietario
	 */
	public java.util.Calendar getFechaDesde()
	{
		return fechaDesde;
	}

	/**
	 * Sets the fechaHasta value for this HistoricoPropietarioDTO.
	 *
	 * @param fechaHasta   * fecha hasta la cual aparece como propietario
	 */
	public void setFechaHasta(java.util.Calendar fechaHasta)
	{
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Gets the fechaHasta value for this HistoricoPropietarioDTO.
	 *
	 * @return fechaHasta   * fecha hasta la cual aparece como propietario
	 */
	public java.util.Calendar getFechaHasta()
	{
		return fechaHasta;
	}

	/**
	 * Sets the NUPRE value for this HistoricoPropietarioDTO.
	 *
	 * @param NUPRE   * Número Único Predial
	 */
	public void setNUPRE(java.lang.String NUPRE)
	{
		this.NUPRE = NUPRE;
	}

	/**
	 * Gets the NUPRE value for this HistoricoPropietarioDTO.
	 *
	 * @return NUPRE   * Número Único Predial
	 */
	public java.lang.String getNUPRE()
	{
		return NUPRE;
	}

	/**
	 * Sets the numAnotacion value for this HistoricoPropietarioDTO.
	 *
	 * @param numAnotacion   * numero de anotacion en la que aparece como
	 *                                 propietario
	 */
	public void setNumAnotacion(java.lang.Integer numAnotacion)
	{
		this.numAnotacion = numAnotacion;
	}

	/**
	 * Gets the numAnotacion value for this HistoricoPropietarioDTO.
	 *
	 * @return numAnotacion   * numero de anotacion en la que aparece como
	 *                                 propietario
	 */
	public java.lang.Integer getNumAnotacion()
	{
		return numAnotacion;
	}

	/**
	 * Sets the numMatriculaInmobiliaria value for this HistoricoPropietarioDTO.
	 *
	 * @param numMatriculaInmobiliaria   * número de matrícula del predio donde aparece
	 *                                 como propietario detro de
	 *                                 un periodo dado, salvo que no tenga
	 *                                 valor
	 *                                 en fecha hasta, para
	 *                                 este caso al momento de la consulta
	 *                                 todavía
	 *                                 aparece como
	 *                                 propietario
	 */
	public void setNumMatriculaInmobiliaria(java.lang.String numMatriculaInmobiliaria)
	{
		this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
	}

	/**
	 * Gets the numMatriculaInmobiliaria value for this HistoricoPropietarioDTO.
	 *
	 * @return numMatriculaInmobiliaria   * número de matrícula del predio donde aparece
	 *                                 como propietario detro de
	 *                                 un periodo dado, salvo que no tenga
	 *                                 valor
	 *                                 en fecha hasta, para
	 *                                 este caso al momento de la consulta
	 *                                 todavía
	 *                                 aparece como
	 *                                 propietario
	 */
	public java.lang.String getNumMatriculaInmobiliaria()
	{
		return numMatriculaInmobiliaria;
	}

	/**
	 * Sets the porcentajeParticipacion value for this HistoricoPropietarioDTO.
	 *
	 * @param porcentajeParticipacion   * porcentaje de participación
	 */
	public void setPorcentajeParticipacion(java.lang.String porcentajeParticipacion)
	{
		this.porcentajeParticipacion = porcentajeParticipacion;
	}

	/**
	 * Gets the porcentajeParticipacion value for this HistoricoPropietarioDTO.
	 *
	 * @return porcentajeParticipacion   * porcentaje de participación
	 */
	public java.lang.String getPorcentajeParticipacion()
	{
		return porcentajeParticipacion;
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
		if(!(obj instanceof HistoricoPropietarioDTO))
			return false;

		HistoricoPropietarioDTO other = (HistoricoPropietarioDTO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numAnotacion == null) && (other.getNumAnotacion() == null))
				|| ((this.numAnotacion != null) && this.numAnotacion.equals(other.getNumAnotacion())))
				&& (((this.codCirculoRegistral == null) && (other.getCodCirculoRegistral() == null))
				|| ((this.codCirculoRegistral != null)
				&& this.codCirculoRegistral.equals(other.getCodCirculoRegistral())))
				&& (((this.porcentajeParticipacion == null) && (other.getPorcentajeParticipacion() == null))
				|| ((this.porcentajeParticipacion != null)
				&& this.porcentajeParticipacion.equals(other.getPorcentajeParticipacion())))
				&& (((this.fechaDesde == null) && (other.getFechaDesde() == null))
				|| ((this.fechaDesde != null) && this.fechaDesde.equals(other.getFechaDesde())))
				&& (((this.fechaHasta == null) && (other.getFechaHasta() == null))
				|| ((this.fechaHasta != null) && this.fechaHasta.equals(other.getFechaHasta())))
				&& (((this.numMatriculaInmobiliaria == null) && (other.getNumMatriculaInmobiliaria() == null))
				|| ((this.numMatriculaInmobiliaria != null)
				&& this.numMatriculaInmobiliaria.equals(other.getNumMatriculaInmobiliaria())))
				&& (((this.NUPRE == null) && (other.getNUPRE() == null))
				|| ((this.NUPRE != null) && this.NUPRE.equals(other.getNUPRE())));
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

		if(getNumAnotacion() != null)
			_hashCode += getNumAnotacion().hashCode();

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

		if(getPorcentajeParticipacion() != null)
			_hashCode += getPorcentajeParticipacion().hashCode();

		if(getFechaDesde() != null)
			_hashCode += getFechaDesde().hashCode();

		if(getFechaHasta() != null)
			_hashCode += getFechaHasta().hashCode();

		if(getNumMatriculaInmobiliaria() != null)
			_hashCode += getNumMatriculaInmobiliaria().hashCode();

		if(getNUPRE() != null)
			_hashCode += getNUPRE().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
