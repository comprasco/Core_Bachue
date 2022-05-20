/**
 * TipoSalidaConsultarRRRMatriculas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1;

public class TipoSalidaConsultarRRRMatriculas implements java.io.Serializable
{
	private static final long serialVersionUID = -5522336934005897167L;

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarRRRMatriculas.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "tipoSalidaConsultarRRRMatriculas"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "codDepartamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMunicipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "codMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "codCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numMatriculaInmobiliaria");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "numMatriculaInmobiliaria"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaDerechos");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "listaDerechos"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        ">>tipoSalidaConsultarRRRMatriculas>listaDerechos>derecho"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1", "derecho"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaRestricciones");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "listaRestricciones"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        ">>tipoSalidaConsultarRRRMatriculas>listaRestricciones>restriccion"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "restriccion"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaResponsabilidades");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "listaResponsabilidades"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        ">>tipoSalidaConsultarRRRMatriculas>listaResponsabilidades>responsabilidad"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "responsabilidad"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaPublicidades");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "listaPublicidades"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        ">>tipoSalidaConsultarRRRMatriculas>listaPublicidades>publicidad"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "publicidad"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaHipotecas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "listaHipotecas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        ">>tipoSalidaConsultarRRRMatriculas>listaHipotecas>hipoteca"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "hipoteca"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "codMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object                                                                                                                                            __equalsCalc             =
		null;
	private java.lang.String                                                                                                                                            codCirculoRegistral;
	private java.lang.String                                                                                                                                            codDepartamento;
	private java.math.BigInteger                                                                                                                                        codMensaje;
	private java.lang.String                                                                                                                                            codMunicipio;
	private java.lang.String                                                                                                                                            descripcionMensaje;
	private java.lang.String                                                                                                                                            numMatriculaInmobiliaria;
	private co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaDerechosDerecho[]                  listaDerechos;
	private co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaHipotecasHipoteca[]                listaHipotecas;
	private co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidad[]           listaPublicidades;
	private co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidad[] listaResponsabilidades;
	private co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccion[]         listaRestricciones;
	private boolean                                                                                                                                                     __hashCodeCalc           =
		false;

	public TipoSalidaConsultarRRRMatriculas()
	{
	}

	public TipoSalidaConsultarRRRMatriculas(
	    java.lang.String codDepartamento, java.lang.String codMunicipio, java.lang.String codCirculoRegistral,
	    java.lang.String numMatriculaInmobiliaria,
	    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaDerechosDerecho[] listaDerechos,
	    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccion[] listaRestricciones,
	    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidad[] listaResponsabilidades,
	    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidad[] listaPublicidades,
	    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaHipotecasHipoteca[] listaHipotecas,
	    java.math.BigInteger codMensaje, java.lang.String descripcionMensaje
	)
	{
		this.codDepartamento              = codDepartamento;
		this.codMunicipio                 = codMunicipio;
		this.codCirculoRegistral          = codCirculoRegistral;
		this.numMatriculaInmobiliaria     = numMatriculaInmobiliaria;
		this.listaDerechos                = listaDerechos;
		this.listaRestricciones           = listaRestricciones;
		this.listaResponsabilidades       = listaResponsabilidades;
		this.listaPublicidades            = listaPublicidades;
		this.listaHipotecas               = listaHipotecas;
		this.codMensaje                   = codMensaje;
		this.descripcionMensaje           = descripcionMensaje;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @param codCirculoRegistral
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @return codCirculoRegistral
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the codDepartamento value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @param codDepartamento
	 */
	public void setCodDepartamento(java.lang.String codDepartamento)
	{
		this.codDepartamento = codDepartamento;
	}

	/**
	 * Gets the codDepartamento value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @return codDepartamento
	 */
	public java.lang.String getCodDepartamento()
	{
		return codDepartamento;
	}

	/**
	 * Sets the codMensaje value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @param codMensaje
	 */
	public void setCodMensaje(java.math.BigInteger codMensaje)
	{
		this.codMensaje = codMensaje;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @return codMensaje
	 */
	public java.math.BigInteger getCodMensaje()
	{
		return codMensaje;
	}

	/**
	 * Sets the codMunicipio value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @param codMunicipio
	 */
	public void setCodMunicipio(java.lang.String codMunicipio)
	{
		this.codMunicipio = codMunicipio;
	}

	/**
	 * Gets the codMunicipio value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @return codMunicipio
	 */
	public java.lang.String getCodMunicipio()
	{
		return codMunicipio;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @param descripcionMensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
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
	 * Sets the listaDerechos value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @param listaDerechos
	 */
	public void setListaDerechos(
	    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaDerechosDerecho[] listaDerechos
	)
	{
		this.listaDerechos = listaDerechos;
	}

	/**
	 * Gets the listaDerechos value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @return listaDerechos
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaDerechosDerecho[] getListaDerechos()
	{
		return listaDerechos;
	}

	/**
	 * Sets the listaHipotecas value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @param listaHipotecas
	 */
	public void setListaHipotecas(
	    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaHipotecasHipoteca[] listaHipotecas
	)
	{
		this.listaHipotecas = listaHipotecas;
	}

	/**
	 * Gets the listaHipotecas value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @return listaHipotecas
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaHipotecasHipoteca[] getListaHipotecas()
	{
		return listaHipotecas;
	}

	/**
	 * Sets the listaPublicidades value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @param listaPublicidades
	 */
	public void setListaPublicidades(
	    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidad[] listaPublicidades
	)
	{
		this.listaPublicidades = listaPublicidades;
	}

	/**
	 * Gets the listaPublicidades value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @return listaPublicidades
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidad[] getListaPublicidades()
	{
		return listaPublicidades;
	}

	/**
	 * Sets the listaResponsabilidades value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @param listaResponsabilidades
	 */
	public void setListaResponsabilidades(
	    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidad[] listaResponsabilidades
	)
	{
		this.listaResponsabilidades = listaResponsabilidades;
	}

	/**
	 * Gets the listaResponsabilidades value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @return listaResponsabilidades
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidad[] getListaResponsabilidades()
	{
		return listaResponsabilidades;
	}

	/**
	 * Sets the listaRestricciones value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @param listaRestricciones
	 */
	public void setListaRestricciones(
	    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccion[] listaRestricciones
	)
	{
		this.listaRestricciones = listaRestricciones;
	}

	/**
	 * Gets the listaRestricciones value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @return listaRestricciones
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccion[] getListaRestricciones()
	{
		return listaRestricciones;
	}

	/**
	 * Sets the numMatriculaInmobiliaria value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @param numMatriculaInmobiliaria
	 */
	public void setNumMatriculaInmobiliaria(java.lang.String numMatriculaInmobiliaria)
	{
		this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
	}

	/**
	 * Gets the numMatriculaInmobiliaria value for this TipoSalidaConsultarRRRMatriculas.
	 *
	 * @return numMatriculaInmobiliaria
	 */
	public java.lang.String getNumMatriculaInmobiliaria()
	{
		return numMatriculaInmobiliaria;
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
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultarRRRMatriculas))
			return false;

		TipoSalidaConsultarRRRMatriculas other = (TipoSalidaConsultarRRRMatriculas)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codDepartamento == null) && (other.getCodDepartamento() == null))
				|| ((this.codDepartamento != null) && this.codDepartamento.equals(other.getCodDepartamento())))
				&& (((this.codMunicipio == null) && (other.getCodMunicipio() == null))
				|| ((this.codMunicipio != null) && this.codMunicipio.equals(other.getCodMunicipio())))
				&& (((this.codCirculoRegistral == null) && (other.getCodCirculoRegistral() == null))
				|| ((this.codCirculoRegistral != null)
				&& this.codCirculoRegistral.equals(other.getCodCirculoRegistral())))
				&& (((this.numMatriculaInmobiliaria == null) && (other.getNumMatriculaInmobiliaria() == null))
				|| ((this.numMatriculaInmobiliaria != null)
				&& this.numMatriculaInmobiliaria.equals(other.getNumMatriculaInmobiliaria())))
				&& (((this.listaDerechos == null) && (other.getListaDerechos() == null))
				|| ((this.listaDerechos != null)
				&& java.util.Arrays.equals(this.listaDerechos, other.getListaDerechos())))
				&& (((this.listaRestricciones == null) && (other.getListaRestricciones() == null))
				|| ((this.listaRestricciones != null)
				&& java.util.Arrays.equals(this.listaRestricciones, other.getListaRestricciones())))
				&& (((this.listaResponsabilidades == null) && (other.getListaResponsabilidades() == null))
				|| ((this.listaResponsabilidades != null)
				&& java.util.Arrays.equals(this.listaResponsabilidades, other.getListaResponsabilidades())))
				&& (((this.listaPublicidades == null) && (other.getListaPublicidades() == null))
				|| ((this.listaPublicidades != null)
				&& java.util.Arrays.equals(this.listaPublicidades, other.getListaPublicidades())))
				&& (((this.listaHipotecas == null) && (other.getListaHipotecas() == null))
				|| ((this.listaHipotecas != null)
				&& java.util.Arrays.equals(this.listaHipotecas, other.getListaHipotecas())))
				&& (((this.codMensaje == null) && (other.getCodMensaje() == null))
				|| ((this.codMensaje != null) && this.codMensaje.equals(other.getCodMensaje())))
				&& (((this.descripcionMensaje == null) && (other.getDescripcionMensaje() == null))
				|| ((this.descripcionMensaje != null) && this.descripcionMensaje.equals(other.getDescripcionMensaje())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getCodDepartamento() != null)
			_hashCode += getCodDepartamento().hashCode();

		if(getCodMunicipio() != null)
			_hashCode += getCodMunicipio().hashCode();

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

		if(getNumMatriculaInmobiliaria() != null)
			_hashCode += getNumMatriculaInmobiliaria().hashCode();

		if(getListaDerechos() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaDerechos()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaDerechos(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getListaRestricciones() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaRestricciones()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaRestricciones(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getListaResponsabilidades() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaResponsabilidades()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaResponsabilidades(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getListaPublicidades() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaPublicidades()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaPublicidades(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getListaHipotecas() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaHipotecas()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaHipotecas(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getCodMensaje() != null)
			_hashCode += getCodMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
