/**
 * TipoEntradaObtenerEECorrespondencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1;

public class TipoEntradaObtenerEECorrespondencia implements java.io.Serializable
{
	private static final long serialVersionUID = -6470865327186300220L;

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaObtenerEECorrespondencia.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "tipoEntradaObtenerEECorrespondencia"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("clasificacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "clasificacion"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        ">tipoEntradaObtenerEECorrespondencia>clasificacion"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("canal");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "canal"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        ">tipoEntradaObtenerEECorrespondencia>canal"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("orip");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "orip"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nir");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "nir"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("turno");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "turno"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroFolios");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "numeroFolios"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("documentos");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "documentos"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "nombreDocumento"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("destinatarios");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "destinatarios"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "tipoDestinatario"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "destinatario"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object __equalsCalc = null;
	private co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondenciaCanal canal;
	private co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondenciaClasificacion clasificacion;
	private java.lang.String nir;
	private java.lang.String numeroFolios;
	private java.lang.String orip;
	private java.lang.String turno;
	private co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoDestinatario[] destinatarios;
	private java.lang.String[] documentos;
	private boolean          __hashCodeCalc = false;

	public TipoEntradaObtenerEECorrespondencia()
	{
	}

	public TipoEntradaObtenerEECorrespondencia(
	    co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondenciaClasificacion clasificacion,
	    co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondenciaCanal         canal,
	    java.lang.String                                                                                                                                      orip,
	    java.lang.String                                                                                                                                      nir,
	    java.lang.String                                                                                                                                      turno,
	    java.lang.String                                                                                                                                      numeroFolios,
	    java.lang.String[]                                                                                                                                    documentos,
	    co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoDestinatario[]                               destinatarios
	)
	{
		this.clasificacion     = clasificacion;
		this.canal             = canal;
		this.orip              = orip;
		this.nir               = nir;
		this.turno             = turno;
		this.numeroFolios      = numeroFolios;
		this.documentos        = documentos;
		this.destinatarios     = destinatarios;
	}

	/**
	 * Sets the canal value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @param canal
	 */
	public void setCanal(
	    co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondenciaCanal canal
	)
	{
		this.canal = canal;
	}

	/**
	 * Gets the canal value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @return canal
	 */
	public co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondenciaCanal getCanal()
	{
		return canal;
	}

	/**
	 * Sets the clasificacion value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @param clasificacion
	 */
	public void setClasificacion(
	    co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondenciaClasificacion clasificacion
	)
	{
		this.clasificacion = clasificacion;
	}

	/**
	 * Gets the clasificacion value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @return clasificacion
	 */
	public co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondenciaClasificacion getClasificacion()
	{
		return clasificacion;
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Sets the destinatarios value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @param destinatarios
	 */
	public void setDestinatarios(
	    co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoDestinatario[] destinatarios
	)
	{
		this.destinatarios = destinatarios;
	}

	/**
	 * Gets the destinatarios value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @return destinatarios
	 */
	public co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoDestinatario[] getDestinatarios()
	{
		return destinatarios;
	}

	/**
	 * Sets the documentos value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @param documentos
	 */
	public void setDocumentos(java.lang.String[] documentos)
	{
		this.documentos = documentos;
	}

	/**
	 * Gets the documentos value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @return documentos
	 */
	public java.lang.String[] getDocumentos()
	{
		return documentos;
	}

	/**
	 * Sets the nir value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @param nir
	 */
	public void setNir(java.lang.String nir)
	{
		this.nir = nir;
	}

	/**
	 * Gets the nir value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @return nir
	 */
	public java.lang.String getNir()
	{
		return nir;
	}

	/**
	 * Sets the numeroFolios value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @param numeroFolios
	 */
	public void setNumeroFolios(java.lang.String numeroFolios)
	{
		this.numeroFolios = numeroFolios;
	}

	/**
	 * Gets the numeroFolios value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @return numeroFolios
	 */
	public java.lang.String getNumeroFolios()
	{
		return numeroFolios;
	}

	/**
	 * Sets the orip value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @param orip
	 */
	public void setOrip(java.lang.String orip)
	{
		this.orip = orip;
	}

	/**
	 * Gets the orip value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @return orip
	 */
	public java.lang.String getOrip()
	{
		return orip;
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Sets the turno value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @param turno
	 */
	public void setTurno(java.lang.String turno)
	{
		this.turno = turno;
	}

	/**
	 * Gets the turno value for this TipoEntradaObtenerEECorrespondencia.
	 *
	 * @return turno
	 */
	public java.lang.String getTurno()
	{
		return turno;
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaObtenerEECorrespondencia))
			return false;

		TipoEntradaObtenerEECorrespondencia other = (TipoEntradaObtenerEECorrespondencia)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.clasificacion == null) && (other.getClasificacion() == null))
				|| ((this.clasificacion != null) && this.clasificacion.equals(other.getClasificacion())))
				&& (((this.canal == null) && (other.getCanal() == null))
				|| ((this.canal != null) && this.canal.equals(other.getCanal())))
				&& (((this.orip == null) && (other.getOrip() == null))
				|| ((this.orip != null) && this.orip.equals(other.getOrip())))
				&& (((this.nir == null) && (other.getNir() == null))
				|| ((this.nir != null) && this.nir.equals(other.getNir())))
				&& (((this.turno == null) && (other.getTurno() == null))
				|| ((this.turno != null) && this.turno.equals(other.getTurno())))
				&& (((this.numeroFolios == null) && (other.getNumeroFolios() == null))
				|| ((this.numeroFolios != null) && this.numeroFolios.equals(other.getNumeroFolios())))
				&& (((this.documentos == null) && (other.getDocumentos() == null))
				|| ((this.documentos != null) && java.util.Arrays.equals(this.documentos, other.getDocumentos())))
				&& (((this.destinatarios == null) && (other.getDestinatarios() == null))
				|| ((this.destinatarios != null)
				&& java.util.Arrays.equals(this.destinatarios, other.getDestinatarios())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getClasificacion() != null)
			_hashCode += getClasificacion().hashCode();

		if(getCanal() != null)
			_hashCode += getCanal().hashCode();

		if(getOrip() != null)
			_hashCode += getOrip().hashCode();

		if(getNir() != null)
			_hashCode += getNir().hashCode();

		if(getTurno() != null)
			_hashCode += getTurno().hashCode();

		if(getNumeroFolios() != null)
			_hashCode += getNumeroFolios().hashCode();

		if(getDocumentos() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getDocumentos()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getDocumentos(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getDestinatarios() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getDestinatarios()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getDestinatarios(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
