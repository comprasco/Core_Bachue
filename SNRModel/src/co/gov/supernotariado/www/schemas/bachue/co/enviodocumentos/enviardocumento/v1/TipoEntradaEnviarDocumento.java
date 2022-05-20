/**
 * TipoEntradaEnviarDocumento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaEnviarDocumento.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaEnviarDocumento implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4854389420557740956L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaEnviarDocumento.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1",
		        "tipoEntradaEnviarDocumento"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("sistemaOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1",
		        "sistemaOrigen"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("repositorio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1", "repositorio"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1",
		        ">tipoEntradaEnviarDocumento>repositorio"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("parametros");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1", "parametros"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1",
		        "tipoParametro"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1", "parametro"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("archivo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1", "archivo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreArchivo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1",
		        "nombreArchivo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                     __equalsCalc   =
		null;
	
	/** Propiedad nombre archivo. */
	private java.lang.String                                                                                                     nombreArchivo;
	
	/** Propiedad repositorio. */
	private co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoEntradaEnviarDocumentoRepositorio repositorio;
	
	/** Propiedad sistema origen. */
	private java.lang.String                                                                                                     sistemaOrigen;
	
	/** Propiedad archivo. */
	private byte[]                                                                                                               archivo;
	
	/** Propiedad parametros. */
	private co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoParametro[]                       parametros;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                              __hashCodeCalc =
		false;

	/**
	 * Instancia un nuevo objeto tipo entrada enviar documento.
	 */
	public TipoEntradaEnviarDocumento()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada enviar documento.
	 *
	 * @param sistemaOrigen de sistema origen
	 * @param repositorio de repositorio
	 * @param parametros de parametros
	 * @param archivo de archivo
	 * @param nombreArchivo de nombre archivo
	 */
	public TipoEntradaEnviarDocumento(
	    java.lang.String sistemaOrigen,
	    co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoEntradaEnviarDocumentoRepositorio repositorio,
	    co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoParametro[] parametros,
	    byte[] archivo, java.lang.String nombreArchivo
	)
	{
		this.sistemaOrigen     = sistemaOrigen;
		this.repositorio       = repositorio;
		this.parametros        = parametros;
		this.archivo           = archivo;
		this.nombreArchivo     = nombreArchivo;
	}

	/**
	 * Sets the archivo value for this TipoEntradaEnviarDocumento.
	 *
	 * @param archivo de archivo
	 */
	public void setArchivo(byte[] archivo)
	{
		this.archivo = archivo;
	}

	/**
	 * Gets the archivo value for this TipoEntradaEnviarDocumento.
	 *
	 * @return archivo
	 */
	public byte[] getArchivo()
	{
		return archivo;
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
	 * Sets the nombreArchivo value for this TipoEntradaEnviarDocumento.
	 *
	 * @param nombreArchivo de nombre archivo
	 */
	public void setNombreArchivo(java.lang.String nombreArchivo)
	{
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * Gets the nombreArchivo value for this TipoEntradaEnviarDocumento.
	 *
	 * @return nombreArchivo
	 */
	public java.lang.String getNombreArchivo()
	{
		return nombreArchivo;
	}

	/**
	 * Sets the parametros value for this TipoEntradaEnviarDocumento.
	 *
	 * @param parametros de parametros
	 */
	public void setParametros(
	    co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoParametro[] parametros
	)
	{
		this.parametros = parametros;
	}

	/**
	 * Gets the parametros value for this TipoEntradaEnviarDocumento.
	 *
	 * @return parametros
	 */
	public co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoParametro[] getParametros()
	{
		return parametros;
	}

	/**
	 * Sets the repositorio value for this TipoEntradaEnviarDocumento.
	 *
	 * @param repositorio de repositorio
	 */
	public void setRepositorio(
	    co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoEntradaEnviarDocumentoRepositorio repositorio
	)
	{
		this.repositorio = repositorio;
	}

	/**
	 * Gets the repositorio value for this TipoEntradaEnviarDocumento.
	 *
	 * @return repositorio
	 */
	public co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoEntradaEnviarDocumentoRepositorio getRepositorio()
	{
		return repositorio;
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
	 * Sets the sistemaOrigen value for this TipoEntradaEnviarDocumento.
	 *
	 * @param sistemaOrigen de sistema origen
	 */
	public void setSistemaOrigen(java.lang.String sistemaOrigen)
	{
		this.sistemaOrigen = sistemaOrigen;
	}

	/**
	 * Gets the sistemaOrigen value for this TipoEntradaEnviarDocumento.
	 *
	 * @return sistemaOrigen
	 */
	public java.lang.String getSistemaOrigen()
	{
		return sistemaOrigen;
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
		if(!(obj instanceof TipoEntradaEnviarDocumento))
			return false;

		TipoEntradaEnviarDocumento other = (TipoEntradaEnviarDocumento)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.sistemaOrigen == null) && (other.getSistemaOrigen() == null))
				|| ((this.sistemaOrigen != null) && this.sistemaOrigen.equals(other.getSistemaOrigen())))
				&& (((this.repositorio == null) && (other.getRepositorio() == null))
				|| ((this.repositorio != null) && this.repositorio.equals(other.getRepositorio())))
				&& (((this.parametros == null) && (other.getParametros() == null))
				|| ((this.parametros != null) && java.util.Arrays.equals(this.parametros, other.getParametros())))
				&& (((this.archivo == null) && (other.getArchivo() == null))
				|| ((this.archivo != null) && java.util.Arrays.equals(this.archivo, other.getArchivo())))
				&& (((this.nombreArchivo == null) && (other.getNombreArchivo() == null))
				|| ((this.nombreArchivo != null) && this.nombreArchivo.equals(other.getNombreArchivo())));
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

		if(getSistemaOrigen() != null)
			_hashCode += getSistemaOrigen().hashCode();

		if(getRepositorio() != null)
			_hashCode += getRepositorio().hashCode();

		if(getParametros() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getParametros()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getParametros(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getArchivo() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getArchivo()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getArchivo(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getNombreArchivo() != null)
			_hashCode += getNombreArchivo().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
