/**
 * Anotacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1;


/**
 * Clase que contiene todos las propiedades Anotacion.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class Anotacion implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1379473132167940538L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    Anotacion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "anotacion"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("consecutivoAnotacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "consecutivoAnotacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumentoAnotacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "tipoDocumentoAnotacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("actoJuridico");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "actoJuridico"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("drr");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "drr"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorActo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "valorActo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaAnotacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "fechaAnotacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("intervinientes");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "intervinientes"
		    )
		);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "interviniente"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                  __equalsCalc           =
		null;
	
	/** Propiedad acto juridico. */
	private java.lang.String                                                                                                  actoJuridico;
	
	/** Propiedad consecutivo anotacion. */
	private java.lang.String                                                                                                  consecutivoAnotacion;
	
	/** Propiedad drr. */
	private java.lang.String                                                                                                  drr;
	
	/** Propiedad fecha anotacion. */
	private java.lang.String                                                                                                  fechaAnotacion;
	
	/** Propiedad tipo documento anotacion. */
	private java.lang.String                                                                                                  tipoDocumentoAnotacion;
	
	/** Propiedad valor acto. */
	private java.lang.String                                                                                                  valorActo;
	
	/** Propiedad intervinientes. */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Interviniente[] intervinientes;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                           __hashCodeCalc         =
		false;

	/**
	 * Instancia un nuevo objeto anotacion.
	 */
	public Anotacion()
	{
	}

	/**
	 * Instancia un nuevo objeto anotacion.
	 *
	 * @param consecutivoAnotacion de consecutivo anotacion
	 * @param tipoDocumentoAnotacion de tipo documento anotacion
	 * @param actoJuridico de acto juridico
	 * @param drr de drr
	 * @param valorActo de valor acto
	 * @param fechaAnotacion de fecha anotacion
	 * @param intervinientes de intervinientes
	 */
	public Anotacion(
	    java.lang.String consecutivoAnotacion, java.lang.String tipoDocumentoAnotacion, java.lang.String actoJuridico,
	    java.lang.String drr, java.lang.String valorActo, java.lang.String fechaAnotacion,
	    co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Interviniente[] intervinientes
	)
	{
		this.consecutivoAnotacion       = consecutivoAnotacion;
		this.tipoDocumentoAnotacion     = tipoDocumentoAnotacion;
		this.actoJuridico               = actoJuridico;
		this.drr                        = drr;
		this.valorActo                  = valorActo;
		this.fechaAnotacion             = fechaAnotacion;
		this.intervinientes             = intervinientes;
	}

	/**
	 * Sets the actoJuridico value for this Anotacion.
	 *
	 * @param actoJuridico de acto juridico
	 */
	public void setActoJuridico(java.lang.String actoJuridico)
	{
		this.actoJuridico = actoJuridico;
	}

	/**
	 * Gets the actoJuridico value for this Anotacion.
	 *
	 * @return actoJuridico
	 */
	public java.lang.String getActoJuridico()
	{
		return actoJuridico;
	}

	/**
	 * Sets the consecutivoAnotacion value for this Anotacion.
	 *
	 * @param consecutivoAnotacion de consecutivo anotacion
	 */
	public void setConsecutivoAnotacion(java.lang.String consecutivoAnotacion)
	{
		this.consecutivoAnotacion = consecutivoAnotacion;
	}

	/**
	 * Gets the consecutivoAnotacion value for this Anotacion.
	 *
	 * @return consecutivoAnotacion
	 */
	public java.lang.String getConsecutivoAnotacion()
	{
		return consecutivoAnotacion;
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
	 * Sets the drr value for this Anotacion.
	 *
	 * @param drr de drr
	 */
	public void setDrr(java.lang.String drr)
	{
		this.drr = drr;
	}

	/**
	 * Gets the drr value for this Anotacion.
	 *
	 * @return drr
	 */
	public java.lang.String getDrr()
	{
		return drr;
	}

	/**
	 * Sets the fechaAnotacion value for this Anotacion.
	 *
	 * @param fechaAnotacion de fecha anotacion
	 */
	public void setFechaAnotacion(java.lang.String fechaAnotacion)
	{
		this.fechaAnotacion = fechaAnotacion;
	}

	/**
	 * Gets the fechaAnotacion value for this Anotacion.
	 *
	 * @return fechaAnotacion
	 */
	public java.lang.String getFechaAnotacion()
	{
		return fechaAnotacion;
	}

	/**
	 * Sets the intervinientes value for this Anotacion.
	 *
	 * @param intervinientes de intervinientes
	 */
	public void setIntervinientes(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Interviniente[] intervinientes
	)
	{
		this.intervinientes = intervinientes;
	}

	/**
	 * Gets the intervinientes value for this Anotacion.
	 *
	 * @return intervinientes
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Interviniente[] getIntervinientes()
	{
		return intervinientes;
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
	 * Sets the tipoDocumentoAnotacion value for this Anotacion.
	 *
	 * @param tipoDocumentoAnotacion de tipo documento anotacion
	 */
	public void setTipoDocumentoAnotacion(java.lang.String tipoDocumentoAnotacion)
	{
		this.tipoDocumentoAnotacion = tipoDocumentoAnotacion;
	}

	/**
	 * Gets the tipoDocumentoAnotacion value for this Anotacion.
	 *
	 * @return tipoDocumentoAnotacion
	 */
	public java.lang.String getTipoDocumentoAnotacion()
	{
		return tipoDocumentoAnotacion;
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
	 * Sets the valorActo value for this Anotacion.
	 *
	 * @param valorActo de valor acto
	 */
	public void setValorActo(java.lang.String valorActo)
	{
		this.valorActo = valorActo;
	}

	/**
	 * Gets the valorActo value for this Anotacion.
	 *
	 * @return valorActo
	 */
	public java.lang.String getValorActo()
	{
		return valorActo;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof Anotacion))
			return false;

		Anotacion other = (Anotacion)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.consecutivoAnotacion == null) && (other.getConsecutivoAnotacion() == null))
				|| ((this.consecutivoAnotacion != null)
				&& this.consecutivoAnotacion.equals(other.getConsecutivoAnotacion())))
				&& (((this.tipoDocumentoAnotacion == null) && (other.getTipoDocumentoAnotacion() == null))
				|| ((this.tipoDocumentoAnotacion != null)
				&& this.tipoDocumentoAnotacion.equals(other.getTipoDocumentoAnotacion())))
				&& (((this.actoJuridico == null) && (other.getActoJuridico() == null))
				|| ((this.actoJuridico != null) && this.actoJuridico.equals(other.getActoJuridico())))
				&& (((this.drr == null) && (other.getDrr() == null))
				|| ((this.drr != null) && this.drr.equals(other.getDrr())))
				&& (((this.valorActo == null) && (other.getValorActo() == null))
				|| ((this.valorActo != null) && this.valorActo.equals(other.getValorActo())))
				&& (((this.fechaAnotacion == null) && (other.getFechaAnotacion() == null))
				|| ((this.fechaAnotacion != null) && this.fechaAnotacion.equals(other.getFechaAnotacion())))
				&& (((this.intervinientes == null) && (other.getIntervinientes() == null))
				|| ((this.intervinientes != null)
				&& java.util.Arrays.equals(this.intervinientes, other.getIntervinientes())));
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

		if(getConsecutivoAnotacion() != null)
			_hashCode += getConsecutivoAnotacion().hashCode();

		if(getTipoDocumentoAnotacion() != null)
			_hashCode += getTipoDocumentoAnotacion().hashCode();

		if(getActoJuridico() != null)
			_hashCode += getActoJuridico().hashCode();

		if(getDrr() != null)
			_hashCode += getDrr().hashCode();

		if(getValorActo() != null)
			_hashCode += getValorActo().hashCode();

		if(getFechaAnotacion() != null)
			_hashCode += getFechaAnotacion().hashCode();

		if(getIntervinientes() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getIntervinientes()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getIntervinientes(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
