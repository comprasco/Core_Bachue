/**
 * TipoServicioGLI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2;


/**
 * Clase que contiene todos las propiedades TipoServicioGLI.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoServicioGLI implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4732970250502445958L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoServicioGLI.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "tipoServicioGLI"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "tipoServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("subtipoServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "subtipoServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("cantidadSolicitada");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "cantidadSolicitada"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "valorServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("criterios");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "criterios"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "tipoCriterioGLI"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "criterio"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                           __equalsCalc       =
		null;
	
	/** Propiedad cantidad solicitada. */
	private java.math.BigInteger                                                                                       cantidadSolicitada;
	
	/** Propiedad subtipo servicio. */
	private java.lang.String                                                                                           subtipoServicio;
	
	/** Propiedad tipo servicio. */
	private java.lang.String                                                                                           tipoServicio;
	
	/** Propiedad valor servicio. */
	private java.math.BigDecimal                                                                                       valorServicio;
	
	/** Propiedad criterios. */
	private co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoCriterioGLI[] criterios;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                    __hashCodeCalc     =
		false;

	/**
	 * Instancia un nuevo objeto tipo servicio GLI.
	 */
	public TipoServicioGLI()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo servicio GLI.
	 *
	 * @param tipoServicio de tipo servicio
	 * @param subtipoServicio de subtipo servicio
	 * @param cantidadSolicitada de cantidad solicitada
	 * @param valorServicio de valor servicio
	 * @param criterios de criterios
	 */
	public TipoServicioGLI(
	    java.lang.String tipoServicio, java.lang.String subtipoServicio, java.math.BigInteger cantidadSolicitada,
	    java.math.BigDecimal valorServicio,
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoCriterioGLI[] criterios
	)
	{
		this.tipoServicio           = tipoServicio;
		this.subtipoServicio        = subtipoServicio;
		this.cantidadSolicitada     = cantidadSolicitada;
		this.valorServicio          = valorServicio;
		this.criterios              = criterios;
	}

	/**
	 * Sets the cantidadSolicitada value for this TipoServicioGLI.
	 *
	 * @param cantidadSolicitada de cantidad solicitada
	 */
	public void setCantidadSolicitada(java.math.BigInteger cantidadSolicitada)
	{
		this.cantidadSolicitada = cantidadSolicitada;
	}

	/**
	 * Gets the cantidadSolicitada value for this TipoServicioGLI.
	 *
	 * @return cantidadSolicitada
	 */
	public java.math.BigInteger getCantidadSolicitada()
	{
		return cantidadSolicitada;
	}

	/**
	 * Sets the criterios value for this TipoServicioGLI.
	 *
	 * @param criterios de criterios
	 */
	public void setCriterios(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoCriterioGLI[] criterios
	)
	{
		this.criterios = criterios;
	}

	/**
	 * Gets the criterios value for this TipoServicioGLI.
	 *
	 * @return criterios
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoCriterioGLI[] getCriterios()
	{
		return criterios;
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
	 * Sets the subtipoServicio value for this TipoServicioGLI.
	 *
	 * @param subtipoServicio de subtipo servicio
	 */
	public void setSubtipoServicio(java.lang.String subtipoServicio)
	{
		this.subtipoServicio = subtipoServicio;
	}

	/**
	 * Gets the subtipoServicio value for this TipoServicioGLI.
	 *
	 * @return subtipoServicio
	 */
	public java.lang.String getSubtipoServicio()
	{
		return subtipoServicio;
	}

	/**
	 * Sets the tipoServicio value for this TipoServicioGLI.
	 *
	 * @param tipoServicio de tipo servicio
	 */
	public void setTipoServicio(java.lang.String tipoServicio)
	{
		this.tipoServicio = tipoServicio;
	}

	/**
	 * Gets the tipoServicio value for this TipoServicioGLI.
	 *
	 * @return tipoServicio
	 */
	public java.lang.String getTipoServicio()
	{
		return tipoServicio;
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
	 * Sets the valorServicio value for this TipoServicioGLI.
	 *
	 * @param valorServicio de valor servicio
	 */
	public void setValorServicio(java.math.BigDecimal valorServicio)
	{
		this.valorServicio = valorServicio;
	}

	/**
	 * Gets the valorServicio value for this TipoServicioGLI.
	 *
	 * @return valorServicio
	 */
	public java.math.BigDecimal getValorServicio()
	{
		return valorServicio;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoServicioGLI))
			return false;

		TipoServicioGLI other = (TipoServicioGLI)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoServicio == null) && (other.getTipoServicio() == null))
				|| ((this.tipoServicio != null) && this.tipoServicio.equals(other.getTipoServicio())))
				&& (((this.subtipoServicio == null) && (other.getSubtipoServicio() == null))
				|| ((this.subtipoServicio != null) && this.subtipoServicio.equals(other.getSubtipoServicio())))
				&& (((this.cantidadSolicitada == null) && (other.getCantidadSolicitada() == null))
				|| ((this.cantidadSolicitada != null) && this.cantidadSolicitada.equals(other.getCantidadSolicitada())))
				&& (((this.valorServicio == null) && (other.getValorServicio() == null))
				|| ((this.valorServicio != null) && this.valorServicio.equals(other.getValorServicio())))
				&& (((this.criterios == null) && (other.getCriterios() == null))
				|| ((this.criterios != null) && java.util.Arrays.equals(this.criterios, other.getCriterios())));
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

		if(getTipoServicio() != null)
			_hashCode += getTipoServicio().hashCode();

		if(getSubtipoServicio() != null)
			_hashCode += getSubtipoServicio().hashCode();

		if(getCantidadSolicitada() != null)
			_hashCode += getCantidadSolicitada().hashCode();

		if(getValorServicio() != null)
			_hashCode += getValorServicio().hashCode();

		if(getCriterios() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getCriterios()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getCriterios(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
