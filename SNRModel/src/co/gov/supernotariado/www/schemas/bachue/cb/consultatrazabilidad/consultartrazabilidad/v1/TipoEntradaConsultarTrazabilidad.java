/**
 * TipoEntradaConsultarTrazabilidad.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1;


/**
 * Esquema que contiene
 *                         los atributos de entrada de la operación consultar trazabilidad.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/09/2019
 */
public class TipoEntradaConsultarTrazabilidad implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 9200549695739739719L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarTrazabilidad.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tipoEntradaConsultarTrazabilidad"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "modulo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("criterioBusqueda");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "criterioBusqueda"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        ">tipoEntradaConsultarTrazabilidad>criterioBusqueda"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorCriterioBusqueda");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "valorCriterioBusqueda"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad criterio busqueda. */
	/* Campo que identifica los criterios de
	 *                                 búsqueda. Los valores:
	 *                                 -NIR
	 *                                 -TURNO */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidadCriterioBusqueda criterioBusqueda;

	/** Propiedad modulo. */
	/* Campo que identifica la aplicación que realiza
	 *                                 la consulta.
	 *                                 Posibles valores SEDE_ELECTRONICA */
	private java.lang.String modulo;

	/** Propiedad valor criterio busqueda. */
	/* Campo que identifica los valores
	 *                                 correspondientes a los criterios de búsqueda.
	 *                                 Condición especia
	 *                                 NIR: El campo está compuesto por números y letras
	 *                                 snraño00000xxxx */
	private java.lang.String valorCriterioBusqueda;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada consultar trazabilidad.
	 */
	public TipoEntradaConsultarTrazabilidad()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consultar trazabilidad.
	 *
	 * @param modulo correspondiente al valor de modulo
	 * @param criterioBusqueda correspondiente al valor de criterio busqueda
	 * @param valorCriterioBusqueda correspondiente al valor de valor criterio busqueda
	 */
	public TipoEntradaConsultarTrazabilidad(
	    java.lang.String modulo,
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidadCriterioBusqueda criterioBusqueda,
	    java.lang.String valorCriterioBusqueda
	)
	{
		this.modulo                    = modulo;
		this.criterioBusqueda          = criterioBusqueda;
		this.valorCriterioBusqueda     = valorCriterioBusqueda;
	}

	/**
	 * Sets the criterioBusqueda value for this TipoEntradaConsultarTrazabilidad.
	 *
	 * @param criterioBusqueda   * Campo que identifica los criterios de
	     *                                 búsqueda. Los valores:
	     *                                 -NIR
	     *                                 -TURNO
	 */
	public void setCriterioBusqueda(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidadCriterioBusqueda criterioBusqueda
	)
	{
		this.criterioBusqueda = criterioBusqueda;
	}

	/**
	 * Gets the criterioBusqueda value for this TipoEntradaConsultarTrazabilidad.
	 *
	 * @return criterioBusqueda   * Campo que identifica los criterios de
	     *                                 búsqueda. Los valores:
	     *                                 -NIR
	     *                                 -TURNO
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidadCriterioBusqueda getCriterioBusqueda()
	{
		return criterioBusqueda;
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
	 * Sets the modulo value for this TipoEntradaConsultarTrazabilidad.
	 *
	 * @param modulo   * Campo que identifica la aplicación que realiza
	     *                                 la consulta.
	     *                                 Posibles valores SEDE_ELECTRONICA
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the modulo value for this TipoEntradaConsultarTrazabilidad.
	 *
	 * @return modulo   * Campo que identifica la aplicación que realiza
	     *                                 la consulta.
	     *                                 Posibles valores SEDE_ELECTRONICA
	 */
	public java.lang.String getModulo()
	{
		return modulo;
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

	/**
	 * Sets the valorCriterioBusqueda value for this TipoEntradaConsultarTrazabilidad.
	 *
	 * @param valorCriterioBusqueda   * Campo que identifica los valores
	     *                                 correspondientes a los criterios de búsqueda.
	     *                                 Condición especia
	     *                                 NIR: El campo está compuesto por números y letras
	     *                                 snraño00000xxxx
	 */
	public void setValorCriterioBusqueda(java.lang.String valorCriterioBusqueda)
	{
		this.valorCriterioBusqueda = valorCriterioBusqueda;
	}

	/**
	 * Gets the valorCriterioBusqueda value for this TipoEntradaConsultarTrazabilidad.
	 *
	 * @return valorCriterioBusqueda   * Campo que identifica los valores
	     *                                 correspondientes a los criterios de búsqueda.
	     *                                 Condición especia
	     *                                 NIR: El campo está compuesto por números y letras
	     *                                 snraño00000xxxx
	 */
	public java.lang.String getValorCriterioBusqueda()
	{
		return valorCriterioBusqueda;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultarTrazabilidad))
			return false;

		TipoEntradaConsultarTrazabilidad other = (TipoEntradaConsultarTrazabilidad)obj;

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
				&& (((this.criterioBusqueda == null) && (other.getCriterioBusqueda() == null))
				|| ((this.criterioBusqueda != null) && this.criterioBusqueda.equals(other.getCriterioBusqueda())))
				&& (((this.valorCriterioBusqueda == null) && (other.getValorCriterioBusqueda() == null))
				|| ((this.valorCriterioBusqueda != null)
				&& this.valorCriterioBusqueda.equals(other.getValorCriterioBusqueda())));
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

		if(getCriterioBusqueda() != null)
			_hashCode += getCriterioBusqueda().hashCode();

		if(getValorCriterioBusqueda() != null)
			_hashCode += getValorCriterioBusqueda().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
