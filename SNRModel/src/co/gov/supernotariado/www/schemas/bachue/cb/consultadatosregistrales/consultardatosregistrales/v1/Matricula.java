/**
 * Matricula.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1;


/**
 * Clase que contiene todos las propiedades Matricula.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class Matricula implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8524628514631486066L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    Matricula.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "matricula"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numero");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "numero"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matricula");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "matricula"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoOrip");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "codigoOrip"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreOrip");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "nombreOrip"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("chip");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "chip"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estadoNupre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "estadoNupre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nupre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "nupre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("cedulaCatastral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "cedulaCatastral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("direccionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "direccionPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("departamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "departamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("municipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "municipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("grupo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "grupo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("titulares");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "titulares"
		    )
		);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "titular"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("anotaciones");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "anotaciones"
		    )
		);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "anotacion"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;
	
	/** Propiedad cedula catastral. */
	private java.lang.String cedulaCatastral;
	
	/** Propiedad chip. */
	private java.lang.String chip;
	
	/** Propiedad codigo orip. */
	private java.lang.String codigoOrip;
	
	/** Propiedad departamento. */
	private java.lang.String departamento;
	
	/** Propiedad direccion predio. */
	private java.lang.String direccionPredio;
	
	/** Propiedad estado nupre. */
	private java.lang.String estadoNupre;
	
	/** Propiedad grupo. */
	private java.lang.String grupo;
	
	/** Propiedad matricula. */
	private java.lang.String matricula;
	
	/** Propiedad municipio. */
	private java.lang.String municipio;
	
	/** Propiedad nombre orip. */
	private java.lang.String nombreOrip;
	
	/** Propiedad numero. */
	private java.lang.String numero;
	
	/** Propiedad nupre. */
	private java.lang.String nupre;
	
	/** Propiedad anotaciones. */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Anotacion[] anotaciones;
	
	/** Propiedad titulares. */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Titular[] titulares;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto matricula.
	 */
	public Matricula()
	{
	}

	/**
	 * Instancia un nuevo objeto matricula.
	 *
	 * @param numero de numero
	 * @param matricula de matricula
	 * @param codigoOrip de codigo orip
	 * @param nombreOrip de nombre orip
	 * @param chip de chip
	 * @param estadoNupre de estado nupre
	 * @param nupre de nupre
	 * @param cedulaCatastral de cedula catastral
	 * @param direccionPredio de direccion predio
	 * @param departamento de departamento
	 * @param municipio de municipio
	 * @param grupo de grupo
	 * @param titulares de titulares
	 * @param anotaciones de anotaciones
	 */
	public Matricula(
	    java.lang.String numero, java.lang.String matricula, java.lang.String codigoOrip, java.lang.String nombreOrip,
	    java.lang.String chip, java.lang.String estadoNupre, java.lang.String nupre, java.lang.String cedulaCatastral,
	    java.lang.String direccionPredio, java.lang.String departamento, java.lang.String municipio,
	    java.lang.String grupo,
	    co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Titular[] titulares,
	    co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Anotacion[] anotaciones
	)
	{
		this.numero              = numero;
		this.matricula           = matricula;
		this.codigoOrip          = codigoOrip;
		this.nombreOrip          = nombreOrip;
		this.chip                = chip;
		this.estadoNupre         = estadoNupre;
		this.nupre               = nupre;
		this.cedulaCatastral     = cedulaCatastral;
		this.direccionPredio     = direccionPredio;
		this.departamento        = departamento;
		this.municipio           = municipio;
		this.grupo               = grupo;
		this.titulares           = titulares;
		this.anotaciones         = anotaciones;
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
	 * Return type metadata object.
	 *
	 * @return el valor de type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Sets the anotaciones value for this Matricula.
	 *
	 * @param anotaciones de anotaciones
	 */
	public void setAnotaciones(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Anotacion[] anotaciones
	)
	{
		this.anotaciones = anotaciones;
	}

	/**
	 * Gets the anotaciones value for this Matricula.
	 *
	 * @return anotaciones
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Anotacion[] getAnotaciones()
	{
		return anotaciones;
	}

	/**
	 * Sets the cedulaCatastral value for this Matricula.
	 *
	 * @param cedulaCatastral de cedula catastral
	 */
	public void setCedulaCatastral(java.lang.String cedulaCatastral)
	{
		this.cedulaCatastral = cedulaCatastral;
	}

	/**
	 * Gets the cedulaCatastral value for this Matricula.
	 *
	 * @return cedulaCatastral
	 */
	public java.lang.String getCedulaCatastral()
	{
		return cedulaCatastral;
	}

	/**
	 * Sets the chip value for this Matricula.
	 *
	 * @param chip de chip
	 */
	public void setChip(java.lang.String chip)
	{
		this.chip = chip;
	}

	/**
	 * Gets the chip value for this Matricula.
	 *
	 * @return chip
	 */
	public java.lang.String getChip()
	{
		return chip;
	}

	/**
	 * Sets the codigoOrip value for this Matricula.
	 *
	 * @param codigoOrip de codigo orip
	 */
	public void setCodigoOrip(java.lang.String codigoOrip)
	{
		this.codigoOrip = codigoOrip;
	}

	/**
	 * Gets the codigoOrip value for this Matricula.
	 *
	 * @return codigoOrip
	 */
	public java.lang.String getCodigoOrip()
	{
		return codigoOrip;
	}

	/**
	 * Sets the departamento value for this Matricula.
	 *
	 * @param departamento de departamento
	 */
	public void setDepartamento(java.lang.String departamento)
	{
		this.departamento = departamento;
	}

	/**
	 * Gets the departamento value for this Matricula.
	 *
	 * @return departamento
	 */
	public java.lang.String getDepartamento()
	{
		return departamento;
	}

	/**
	 * Sets the direccionPredio value for this Matricula.
	 *
	 * @param direccionPredio de direccion predio
	 */
	public void setDireccionPredio(java.lang.String direccionPredio)
	{
		this.direccionPredio = direccionPredio;
	}

	/**
	 * Gets the direccionPredio value for this Matricula.
	 *
	 * @return direccionPredio
	 */
	public java.lang.String getDireccionPredio()
	{
		return direccionPredio;
	}

	/**
	 * Sets the estadoNupre value for this Matricula.
	 *
	 * @param estadoNupre de estado nupre
	 */
	public void setEstadoNupre(java.lang.String estadoNupre)
	{
		this.estadoNupre = estadoNupre;
	}

	/**
	 * Gets the estadoNupre value for this Matricula.
	 *
	 * @return estadoNupre
	 */
	public java.lang.String getEstadoNupre()
	{
		return estadoNupre;
	}

	/**
	 * Sets the grupo value for this Matricula.
	 *
	 * @param grupo de grupo
	 */
	public void setGrupo(java.lang.String grupo)
	{
		this.grupo = grupo;
	}

	/**
	 * Gets the grupo value for this Matricula.
	 *
	 * @return grupo
	 */
	public java.lang.String getGrupo()
	{
		return grupo;
	}

	/**
	 * Sets the matricula value for this Matricula.
	 *
	 * @param matricula de matricula
	 */
	public void setMatricula(java.lang.String matricula)
	{
		this.matricula = matricula;
	}

	/**
	 * Gets the matricula value for this Matricula.
	 *
	 * @return matricula
	 */
	public java.lang.String getMatricula()
	{
		return matricula;
	}

	/**
	 * Sets the municipio value for this Matricula.
	 *
	 * @param municipio de municipio
	 */
	public void setMunicipio(java.lang.String municipio)
	{
		this.municipio = municipio;
	}

	/**
	 * Gets the municipio value for this Matricula.
	 *
	 * @return municipio
	 */
	public java.lang.String getMunicipio()
	{
		return municipio;
	}

	/**
	 * Sets the nombreOrip value for this Matricula.
	 *
	 * @param nombreOrip de nombre orip
	 */
	public void setNombreOrip(java.lang.String nombreOrip)
	{
		this.nombreOrip = nombreOrip;
	}

	/**
	 * Gets the nombreOrip value for this Matricula.
	 *
	 * @return nombreOrip
	 */
	public java.lang.String getNombreOrip()
	{
		return nombreOrip;
	}

	/**
	 * Sets the numero value for this Matricula.
	 *
	 * @param numero de numero
	 */
	public void setNumero(java.lang.String numero)
	{
		this.numero = numero;
	}

	/**
	 * Gets the numero value for this Matricula.
	 *
	 * @return numero
	 */
	public java.lang.String getNumero()
	{
		return numero;
	}

	/**
	 * Sets the nupre value for this Matricula.
	 *
	 * @param nupre de nupre
	 */
	public void setNupre(java.lang.String nupre)
	{
		this.nupre = nupre;
	}

	/**
	 * Gets the nupre value for this Matricula.
	 *
	 * @return nupre
	 */
	public java.lang.String getNupre()
	{
		return nupre;
	}

	/**
	 * Sets the titulares value for this Matricula.
	 *
	 * @param titulares de titulares
	 */
	public void setTitulares(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Titular[] titulares
	)
	{
		this.titulares = titulares;
	}

	/**
	 * Gets the titulares value for this Matricula.
	 *
	 * @return titulares
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Titular[] getTitulares()
	{
		return titulares;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof Matricula))
			return false;

		Matricula other = (Matricula)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numero == null) && (other.getNumero() == null))
				|| ((this.numero != null) && this.numero.equals(other.getNumero())))
				&& (((this.matricula == null) && (other.getMatricula() == null))
				|| ((this.matricula != null) && this.matricula.equals(other.getMatricula())))
				&& (((this.codigoOrip == null) && (other.getCodigoOrip() == null))
				|| ((this.codigoOrip != null) && this.codigoOrip.equals(other.getCodigoOrip())))
				&& (((this.nombreOrip == null) && (other.getNombreOrip() == null))
				|| ((this.nombreOrip != null) && this.nombreOrip.equals(other.getNombreOrip())))
				&& (((this.chip == null) && (other.getChip() == null))
				|| ((this.chip != null) && this.chip.equals(other.getChip())))
				&& (((this.estadoNupre == null) && (other.getEstadoNupre() == null))
				|| ((this.estadoNupre != null) && this.estadoNupre.equals(other.getEstadoNupre())))
				&& (((this.nupre == null) && (other.getNupre() == null))
				|| ((this.nupre != null) && this.nupre.equals(other.getNupre())))
				&& (((this.cedulaCatastral == null) && (other.getCedulaCatastral() == null))
				|| ((this.cedulaCatastral != null) && this.cedulaCatastral.equals(other.getCedulaCatastral())))
				&& (((this.direccionPredio == null) && (other.getDireccionPredio() == null))
				|| ((this.direccionPredio != null) && this.direccionPredio.equals(other.getDireccionPredio())))
				&& (((this.departamento == null) && (other.getDepartamento() == null))
				|| ((this.departamento != null) && this.departamento.equals(other.getDepartamento())))
				&& (((this.municipio == null) && (other.getMunicipio() == null))
				|| ((this.municipio != null) && this.municipio.equals(other.getMunicipio())))
				&& (((this.grupo == null) && (other.getGrupo() == null))
				|| ((this.grupo != null) && this.grupo.equals(other.getGrupo())))
				&& (((this.titulares == null) && (other.getTitulares() == null))
				|| ((this.titulares != null) && java.util.Arrays.equals(this.titulares, other.getTitulares())))
				&& (((this.anotaciones == null) && (other.getAnotaciones() == null))
				|| ((this.anotaciones != null) && java.util.Arrays.equals(this.anotaciones, other.getAnotaciones())));
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

		if(getNumero() != null)
			_hashCode += getNumero().hashCode();

		if(getMatricula() != null)
			_hashCode += getMatricula().hashCode();

		if(getCodigoOrip() != null)
			_hashCode += getCodigoOrip().hashCode();

		if(getNombreOrip() != null)
			_hashCode += getNombreOrip().hashCode();

		if(getChip() != null)
			_hashCode += getChip().hashCode();

		if(getEstadoNupre() != null)
			_hashCode += getEstadoNupre().hashCode();

		if(getNupre() != null)
			_hashCode += getNupre().hashCode();

		if(getCedulaCatastral() != null)
			_hashCode += getCedulaCatastral().hashCode();

		if(getDireccionPredio() != null)
			_hashCode += getDireccionPredio().hashCode();

		if(getDepartamento() != null)
			_hashCode += getDepartamento().hashCode();

		if(getMunicipio() != null)
			_hashCode += getMunicipio().hashCode();

		if(getGrupo() != null)
			_hashCode += getGrupo().hashCode();

		if(getTitulares() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getTitulares()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getTitulares(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getAnotaciones() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getAnotaciones()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getAnotaciones(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
