/**
 * TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredio.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/04/2020
 */
public class TipoAnotacionPredio implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5314234918392820189L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoAnotacionPredio.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
		        ">>tipoSalidaConsultarCambioPropietario>anotacionesPredio>anotacionPredio"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("comentario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
		        "comentario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaAnotacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
		        "fechaAnotacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorActo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
		        "valorActo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codNaturalezaJuridicaFolio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
		        "codNaturalezaJuridicaFolio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("propietarios");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
		        "propietarios"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
		        ">>>>tipoSalidaConsultarCambioPropietario>anotacionesPredio>anotacionPredio>propietarios>propietario"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/cambioPropietario/consultarCambioPropietario/v1",
		        "propietario"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod naturaleza juridica folio. */
	private java.lang.String codNaturalezaJuridicaFolio;

	/** Propiedad comentario. */
	private java.lang.String comentario;

	/** Propiedad fecha anotacion. */
	private java.util.Calendar fechaAnotacion;

	/** Propiedad valor acto. */
	private java.lang.Double valorActo;

	/** Propiedad propietarios. */
	private co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoPropietario[] propietarios;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar cambio propietario anotaciones predio anotacion predio.
	 */
	public TipoAnotacionPredio()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar cambio propietario anotaciones predio anotacion predio.
	 *
	 * @param comentario de comentario
	 * @param fechaAnotacion de fecha anotacion
	 * @param valorActo de valor acto
	 * @param codNaturalezaJuridicaFolio de cod naturaleza juridica folio
	 * @param propietarios de propietarios
	 */
	public TipoAnotacionPredio(
	    java.lang.String comentario, java.util.Calendar fechaAnotacion, java.lang.Double valorActo,
	    java.lang.String codNaturalezaJuridicaFolio,
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoPropietario[] propietarios
	)
	{
		this.comentario                     = comentario;
		this.fechaAnotacion                 = fechaAnotacion;
		this.valorActo                      = valorActo;
		this.codNaturalezaJuridicaFolio     = codNaturalezaJuridicaFolio;
		this.propietarios                   = propietarios;
	}

	/**
	 * Sets the codNaturalezaJuridicaFolio value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredio.
	 *
	 * @param codNaturalezaJuridicaFolio de cod naturaleza juridica folio
	 */
	public void setCodNaturalezaJuridicaFolio(java.lang.String codNaturalezaJuridicaFolio)
	{
		this.codNaturalezaJuridicaFolio = codNaturalezaJuridicaFolio;
	}

	/**
	 * Gets the codNaturalezaJuridicaFolio value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredio.
	 *
	 * @return codNaturalezaJuridicaFolio
	 */
	public java.lang.String getCodNaturalezaJuridicaFolio()
	{
		return codNaturalezaJuridicaFolio;
	}

	/**
	 * Sets the comentario value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredio.
	 *
	 * @param comentario de comentario
	 */
	public void setComentario(java.lang.String comentario)
	{
		this.comentario = comentario;
	}

	/**
	 * Gets the comentario value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredio.
	 *
	 * @return comentario
	 */
	public java.lang.String getComentario()
	{
		return comentario;
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
	 * Sets the fechaAnotacion value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredio.
	 *
	 * @param fechaAnotacion de fecha anotacion
	 */
	public void setFechaAnotacion(java.util.Calendar fechaAnotacion)
	{
		this.fechaAnotacion = fechaAnotacion;
	}

	/**
	 * Gets the fechaAnotacion value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredio.
	 *
	 * @return fechaAnotacion
	 */
	public java.util.Calendar getFechaAnotacion()
	{
		return fechaAnotacion;
	}

	/**
	 * Sets the propietarios value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredio.
	 *
	 * @param propietarios de propietarios
	 */
	public void setPropietarios(
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoPropietario[] propietarios
	)
	{
		this.propietarios = propietarios;
	}

	/**
	 * Gets the propietarios value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredio.
	 *
	 * @return propietarios
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoPropietario[] getPropietarios()
	{
		return propietarios;
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

	/**
	 * Sets the valorActo value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredio.
	 *
	 * @param valorActo de valor acto
	 */
	public void setValorActo(java.lang.Double valorActo)
	{
		this.valorActo = valorActo;
	}

	/**
	 * Gets the valorActo value for this TipoSalidaConsultarCambioPropietarioAnotacionesPredioAnotacionPredio.
	 *
	 * @return valorActo
	 */
	public java.lang.Double getValorActo()
	{
		return valorActo;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoAnotacionPredio))
			return false;

		TipoAnotacionPredio other = (TipoAnotacionPredio)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.comentario == null) && (other.getComentario() == null))
				|| ((this.comentario != null) && this.comentario.equals(other.getComentario())))
				&& (((this.fechaAnotacion == null) && (other.getFechaAnotacion() == null))
				|| ((this.fechaAnotacion != null) && this.fechaAnotacion.equals(other.getFechaAnotacion())))
				&& (((this.valorActo == null) && (other.getValorActo() == null))
				|| ((this.valorActo != null) && this.valorActo.equals(other.getValorActo())))
				&& (((this.codNaturalezaJuridicaFolio == null) && (other.getCodNaturalezaJuridicaFolio() == null))
				|| ((this.codNaturalezaJuridicaFolio != null)
				&& this.codNaturalezaJuridicaFolio.equals(other.getCodNaturalezaJuridicaFolio())))
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

		if(getComentario() != null)
			_hashCode += getComentario().hashCode();

		if(getFechaAnotacion() != null)
			_hashCode += getFechaAnotacion().hashCode();

		if(getValorActo() != null)
			_hashCode += getValorActo().hashCode();

		if(getCodNaturalezaJuridicaFolio() != null)
			_hashCode += getCodNaturalezaJuridicaFolio().hashCode();

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
