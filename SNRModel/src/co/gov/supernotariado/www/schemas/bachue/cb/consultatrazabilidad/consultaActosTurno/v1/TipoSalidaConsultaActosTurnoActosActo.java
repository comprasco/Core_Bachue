/**
 * TipoSalidaConsultaActosTurnoActosActo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultaActosTurnoActosActo.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/09/2020
 */
public class TipoSalidaConsultaActosTurnoActosActo implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7825175920323208766L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultaActosTurnoActosActo.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
		        ">>tipoSalidaConsultaActosTurno>actos>acto"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoActo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
		        "codigoActo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoTipoActo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
		        "codigoTipoActo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreTipoActo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
		        "nombreTipoActo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tieneCuantia");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
		        "tieneCuantia"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tarifaExenta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
		        "tarifaExenta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorCuantia");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
		        "valorCuantia"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorAvaluo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
		        "valorAvaluo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("cantidadActos");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
		        "cantidadActos"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorDerechos");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
		        "valorDerechos"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorConservacionDocumental");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
		        "valorConservacionDocumental"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorTotal");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
		        "valorTotal"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cantidad actos. */
	private java.lang.String cantidadActos;

	/** Propiedad codigo acto. */
	private java.lang.String codigoActo;

	/** Propiedad codigo tipo acto. */
	private java.lang.String codigoTipoActo;

	/** Propiedad nombre tipo acto. */
	private java.lang.String nombreTipoActo;

	/** Propiedad tarifa exenta. */
	private java.lang.String tarifaExenta;

	/** Propiedad tiene cuantia. */
	private java.lang.String tieneCuantia;

	/** Propiedad valor avaluo. */
	private java.lang.String valorAvaluo;

	/** Propiedad valor conservacion documental. */
	private java.lang.String valorConservacionDocumental;

	/** Propiedad valor cuantia. */
	private java.lang.String valorCuantia;

	/** Propiedad valor derechos. */
	private java.lang.String valorDerechos;

	/** Propiedad valor total. */
	private java.lang.String valorTotal;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consulta actos turno actos acto.
	 */
	public TipoSalidaConsultaActosTurnoActosActo()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consulta actos turno actos acto.
	 *
	 * @param codigoActo correspondiente al valor de codigo acto
	 * @param codigoTipoActo correspondiente al valor de codigo tipo acto
	 * @param nombreTipoActo correspondiente al valor de nombre tipo acto
	 * @param tieneCuantia correspondiente al valor de tiene cuantia
	 * @param tarifaExenta correspondiente al valor de tarifa exenta
	 * @param valorCuantia correspondiente al valor de valor cuantia
	 * @param valorAvaluo correspondiente al valor de valor avaluo
	 * @param cantidadActos correspondiente al valor de cantidad actos
	 * @param valorDerechos correspondiente al valor de valor derechos
	 * @param valorConservacionDocumental correspondiente al valor de valor conservacion documental
	 * @param valorTotal correspondiente al valor de valor total
	 */
	public TipoSalidaConsultaActosTurnoActosActo(
	    java.lang.String codigoActo, java.lang.String codigoTipoActo, java.lang.String nombreTipoActo,
	    java.lang.String tieneCuantia, java.lang.String tarifaExenta, java.lang.String valorCuantia,
	    java.lang.String valorAvaluo, java.lang.String cantidadActos, java.lang.String valorDerechos,
	    java.lang.String valorConservacionDocumental, java.lang.String valorTotal
	)
	{
		this.codigoActo                      = codigoActo;
		this.codigoTipoActo                  = codigoTipoActo;
		this.nombreTipoActo                  = nombreTipoActo;
		this.tieneCuantia                    = tieneCuantia;
		this.tarifaExenta                    = tarifaExenta;
		this.valorCuantia                    = valorCuantia;
		this.valorAvaluo                     = valorAvaluo;
		this.cantidadActos                   = cantidadActos;
		this.valorDerechos                   = valorDerechos;
		this.valorConservacionDocumental     = valorConservacionDocumental;
		this.valorTotal                      = valorTotal;
	}

	/**
	 * Sets the cantidadActos value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @param cantidadActos correspondiente al valor de cantidad actos
	 */
	public void setCantidadActos(java.lang.String cantidadActos)
	{
		this.cantidadActos = cantidadActos;
	}

	/**
	 * Gets the cantidadActos value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @return cantidadActos
	 */
	public java.lang.String getCantidadActos()
	{
		return cantidadActos;
	}

	/**
	 * Sets the codigoActo value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @param codigoActo correspondiente al valor de codigo acto
	 */
	public void setCodigoActo(java.lang.String codigoActo)
	{
		this.codigoActo = codigoActo;
	}

	/**
	 * Gets the codigoActo value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @return codigoActo
	 */
	public java.lang.String getCodigoActo()
	{
		return codigoActo;
	}

	/**
	 * Sets the codigoTipoActo value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @param codigoTipoActo correspondiente al valor de codigo tipo acto
	 */
	public void setCodigoTipoActo(java.lang.String codigoTipoActo)
	{
		this.codigoTipoActo = codigoTipoActo;
	}

	/**
	 * Gets the codigoTipoActo value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @return codigoTipoActo
	 */
	public java.lang.String getCodigoTipoActo()
	{
		return codigoTipoActo;
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
	 * Sets the nombreTipoActo value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @param nombreTipoActo correspondiente al valor de nombre tipo acto
	 */
	public void setNombreTipoActo(java.lang.String nombreTipoActo)
	{
		this.nombreTipoActo = nombreTipoActo;
	}

	/**
	 * Gets the nombreTipoActo value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @return nombreTipoActo
	 */
	public java.lang.String getNombreTipoActo()
	{
		return nombreTipoActo;
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
	 * Sets the tarifaExenta value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @param tarifaExenta correspondiente al valor de tarifa exenta
	 */
	public void setTarifaExenta(java.lang.String tarifaExenta)
	{
		this.tarifaExenta = tarifaExenta;
	}

	/**
	 * Gets the tarifaExenta value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @return tarifaExenta
	 */
	public java.lang.String getTarifaExenta()
	{
		return tarifaExenta;
	}

	/**
	 * Sets the tieneCuantia value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @param tieneCuantia correspondiente al valor de tiene cuantia
	 */
	public void setTieneCuantia(java.lang.String tieneCuantia)
	{
		this.tieneCuantia = tieneCuantia;
	}

	/**
	 * Gets the tieneCuantia value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @return tieneCuantia
	 */
	public java.lang.String getTieneCuantia()
	{
		return tieneCuantia;
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
	 * Sets the valorAvaluo value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @param valorAvaluo correspondiente al valor de valor avaluo
	 */
	public void setValorAvaluo(java.lang.String valorAvaluo)
	{
		this.valorAvaluo = valorAvaluo;
	}

	/**
	 * Gets the valorAvaluo value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @return valorAvaluo
	 */
	public java.lang.String getValorAvaluo()
	{
		return valorAvaluo;
	}

	/**
	 * Sets the valorConservacionDocumental value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @param valorConservacionDocumental correspondiente al valor de valor conservacion documental
	 */
	public void setValorConservacionDocumental(java.lang.String valorConservacionDocumental)
	{
		this.valorConservacionDocumental = valorConservacionDocumental;
	}

	/**
	 * Gets the valorConservacionDocumental value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @return valorConservacionDocumental
	 */
	public java.lang.String getValorConservacionDocumental()
	{
		return valorConservacionDocumental;
	}

	/**
	 * Sets the valorCuantia value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @param valorCuantia correspondiente al valor de valor cuantia
	 */
	public void setValorCuantia(java.lang.String valorCuantia)
	{
		this.valorCuantia = valorCuantia;
	}

	/**
	 * Gets the valorCuantia value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @return valorCuantia
	 */
	public java.lang.String getValorCuantia()
	{
		return valorCuantia;
	}

	/**
	 * Sets the valorDerechos value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @param valorDerechos correspondiente al valor de valor derechos
	 */
	public void setValorDerechos(java.lang.String valorDerechos)
	{
		this.valorDerechos = valorDerechos;
	}

	/**
	 * Gets the valorDerechos value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @return valorDerechos
	 */
	public java.lang.String getValorDerechos()
	{
		return valorDerechos;
	}

	/**
	 * Sets the valorTotal value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @param valorTotal correspondiente al valor de valor total
	 */
	public void setValorTotal(java.lang.String valorTotal)
	{
		this.valorTotal = valorTotal;
	}

	/**
	 * Gets the valorTotal value for this TipoSalidaConsultaActosTurnoActosActo.
	 *
	 * @return valorTotal
	 */
	public java.lang.String getValorTotal()
	{
		return valorTotal;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultaActosTurnoActosActo))
			return false;

		TipoSalidaConsultaActosTurnoActosActo other = (TipoSalidaConsultaActosTurnoActosActo)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigoActo == null) && (other.getCodigoActo() == null))
				|| ((this.codigoActo != null) && this.codigoActo.equals(other.getCodigoActo())))
				&& (((this.codigoTipoActo == null) && (other.getCodigoTipoActo() == null))
				|| ((this.codigoTipoActo != null) && this.codigoTipoActo.equals(other.getCodigoTipoActo())))
				&& (((this.nombreTipoActo == null) && (other.getNombreTipoActo() == null))
				|| ((this.nombreTipoActo != null) && this.nombreTipoActo.equals(other.getNombreTipoActo())))
				&& (((this.tieneCuantia == null) && (other.getTieneCuantia() == null))
				|| ((this.tieneCuantia != null) && this.tieneCuantia.equals(other.getTieneCuantia())))
				&& (((this.tarifaExenta == null) && (other.getTarifaExenta() == null))
				|| ((this.tarifaExenta != null) && this.tarifaExenta.equals(other.getTarifaExenta())))
				&& (((this.valorCuantia == null) && (other.getValorCuantia() == null))
				|| ((this.valorCuantia != null) && this.valorCuantia.equals(other.getValorCuantia())))
				&& (((this.valorAvaluo == null) && (other.getValorAvaluo() == null))
				|| ((this.valorAvaluo != null) && this.valorAvaluo.equals(other.getValorAvaluo())))
				&& (((this.cantidadActos == null) && (other.getCantidadActos() == null))
				|| ((this.cantidadActos != null) && this.cantidadActos.equals(other.getCantidadActos())))
				&& (((this.valorDerechos == null) && (other.getValorDerechos() == null))
				|| ((this.valorDerechos != null) && this.valorDerechos.equals(other.getValorDerechos())))
				&& (((this.valorConservacionDocumental == null) && (other.getValorConservacionDocumental() == null))
				|| ((this.valorConservacionDocumental != null)
				&& this.valorConservacionDocumental.equals(other.getValorConservacionDocumental())))
				&& (((this.valorTotal == null) && (other.getValorTotal() == null))
				|| ((this.valorTotal != null) && this.valorTotal.equals(other.getValorTotal())));
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

		if(getCodigoActo() != null)
			_hashCode += getCodigoActo().hashCode();

		if(getCodigoTipoActo() != null)
			_hashCode += getCodigoTipoActo().hashCode();

		if(getNombreTipoActo() != null)
			_hashCode += getNombreTipoActo().hashCode();

		if(getTieneCuantia() != null)
			_hashCode += getTieneCuantia().hashCode();

		if(getTarifaExenta() != null)
			_hashCode += getTarifaExenta().hashCode();

		if(getValorCuantia() != null)
			_hashCode += getValorCuantia().hashCode();

		if(getValorAvaluo() != null)
			_hashCode += getValorAvaluo().hashCode();

		if(getCantidadActos() != null)
			_hashCode += getCantidadActos().hashCode();

		if(getValorDerechos() != null)
			_hashCode += getValorDerechos().hashCode();

		if(getValorConservacionDocumental() != null)
			_hashCode += getValorConservacionDocumental().hashCode();

		if(getValorTotal() != null)
			_hashCode += getValorTotal().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
