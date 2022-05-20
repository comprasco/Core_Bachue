/**
 * TipoEmpresaAEI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1;

import java.io.Serializable;

import java.lang.String;



/**
 * The Class TipoEmpresaAEI.
 *
 * @author Manuel Blanco
 */
public class TipoEmpresaAEI implements Serializable
{
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 3981714470257094048L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEmpresaAEI.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "tipoEmpresaAEI"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumentoEmpresa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "tipoDocumentoEmpresa"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroDocumentoEmpresa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "numeroDocumentoEmpresa"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("razonSocialEmpresa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "razonSocialEmpresa"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("paisEmpresa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "paisEmpresa"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("departamentoEmpresa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "departamentoEmpresa"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("municipioEmpresa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "municipioEmpresa"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoEntidadEmpresa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "tipoEntidadEmpresa"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("actividadEconomica");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "actividadEconomica"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("telefonoEmpresa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "telefonoEmpresa"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/**  Propiedad __equalsCalc. */
	private Object __equalsCalc = null;

	/**  Propiedad actividadEconomica. */
	private String is_actividadEconomica;

	/**  Propiedad departamentoEmpresa. */
	private String is_departamentoEmpresa;

	/**  Propiedad municipioEmpresa. */
	private String is_municipioEmpresa;

	/**  Propiedad numeroDocumentoEmpresa. */
	private String is_numeroDocumentoEmpresa;

	/**  Propiedad paisEmpresa. */
	private String is_paisEmpresa;

	/**  Propiedad razonSocialEmpresa. */
	private String is_razonSocialEmpresa;

	/**  Propiedad telefonoEmpresa. */
	private String is_telefonoEmpresa;

	/**  Propiedad tipoDocumentoEmpresa. */
	private String is_tipoDocumentoEmpresa;

	/**  Propiedad tipoEntidadEmpresa. */
	private String is_tipoEntidadEmpresa;

	/**  Propiedad __hashCodeCalc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo empresa AEI.
	 */
	public TipoEmpresaAEI()
	{
	}

	/**
	 * Instantiates a new tipo empresa AEI.
	 *
	 * @param tipoDocumentoEmpresa the tipo documento empresa
	 * @param numeroDocumentoEmpresa the numero documento empresa
	 * @param razonSocialEmpresa the razon social empresa
	 * @param paisEmpresa the pais empresa
	 * @param departamentoEmpresa the departamento empresa
	 * @param municipioEmpresa the municipio empresa
	 * @param tipoEntidadEmpresa the tipo entidad empresa
	 * @param actividadEconomica the actividad economica
	 * @param telefonoEmpresa the telefono empresa
	 */
	public TipoEmpresaAEI(
	    String tipoDocumentoEmpresa, String numeroDocumentoEmpresa, String razonSocialEmpresa, String paisEmpresa,
	    String departamentoEmpresa, String municipioEmpresa, String tipoEntidadEmpresa, String actividadEconomica,
	    String telefonoEmpresa
	)
	{
		this.is_tipoDocumentoEmpresa       = tipoDocumentoEmpresa;
		this.is_numeroDocumentoEmpresa     = numeroDocumentoEmpresa;
		this.is_razonSocialEmpresa         = razonSocialEmpresa;
		this.is_paisEmpresa                = paisEmpresa;
		this.is_departamentoEmpresa        = departamentoEmpresa;
		this.is_municipioEmpresa           = municipioEmpresa;
		this.is_tipoEntidadEmpresa         = tipoEntidadEmpresa;
		this.is_actividadEconomica         = actividadEconomica;
		this.is_telefonoEmpresa            = telefonoEmpresa;
	}

	/**
	 * Gets the tipoDocumentoEmpresa value for this TipoEmpresaAEI.
	 *
	 * @return tipoDocumentoEmpresa
	 */
	public String getTipoDocumentoEmpresa()
	{
		return is_tipoDocumentoEmpresa;
	}

	/**
	 * Sets the tipoDocumentoEmpresa value for this TipoEmpresaAEI.
	 *
	 * @param tipoDocumentoEmpresa the new tipo documento empresa
	 */
	public void setTipoDocumentoEmpresa(String tipoDocumentoEmpresa)
	{
		this.is_tipoDocumentoEmpresa = tipoDocumentoEmpresa;
	}

	/**
	 * Gets the numeroDocumentoEmpresa value for this TipoEmpresaAEI.
	 *
	 * @return numeroDocumentoEmpresa
	 */
	public String getNumeroDocumentoEmpresa()
	{
		return is_numeroDocumentoEmpresa;
	}

	/**
	 * Sets the numeroDocumentoEmpresa value for this TipoEmpresaAEI.
	 *
	 * @param numeroDocumentoEmpresa the new numero documento empresa
	 */
	public void setNumeroDocumentoEmpresa(String numeroDocumentoEmpresa)
	{
		this.is_numeroDocumentoEmpresa = numeroDocumentoEmpresa;
	}

	/**
	 * Gets the razonSocialEmpresa value for this TipoEmpresaAEI.
	 *
	 * @return razonSocialEmpresa
	 */
	public String getRazonSocialEmpresa()
	{
		return is_razonSocialEmpresa;
	}

	/**
	 * Sets the razonSocialEmpresa value for this TipoEmpresaAEI.
	 *
	 * @param razonSocialEmpresa the new razon social empresa
	 */
	public void setRazonSocialEmpresa(String razonSocialEmpresa)
	{
		this.is_razonSocialEmpresa = razonSocialEmpresa;
	}

	/**
	 * Gets the paisEmpresa value for this TipoEmpresaAEI.
	 *
	 * @return paisEmpresa
	 */
	public String getPaisEmpresa()
	{
		return is_paisEmpresa;
	}

	/**
	 * Sets the paisEmpresa value for this TipoEmpresaAEI.
	 *
	 * @param paisEmpresa the new pais empresa
	 */
	public void setPaisEmpresa(String paisEmpresa)
	{
		this.is_paisEmpresa = paisEmpresa;
	}

	/**
	 * Gets the departamentoEmpresa value for this TipoEmpresaAEI.
	 *
	 * @return departamentoEmpresa
	 */
	public String getDepartamentoEmpresa()
	{
		return is_departamentoEmpresa;
	}

	/**
	 * Sets the departamentoEmpresa value for this TipoEmpresaAEI.
	 *
	 * @param departamentoEmpresa the new departamento empresa
	 */
	public void setDepartamentoEmpresa(String departamentoEmpresa)
	{
		this.is_departamentoEmpresa = departamentoEmpresa;
	}

	/**
	 * Gets the municipioEmpresa value for this TipoEmpresaAEI.
	 *
	 * @return municipioEmpresa
	 */
	public String getMunicipioEmpresa()
	{
		return is_municipioEmpresa;
	}

	/**
	 * Sets the municipioEmpresa value for this TipoEmpresaAEI.
	 *
	 * @param municipioEmpresa the new municipio empresa
	 */
	public void setMunicipioEmpresa(String municipioEmpresa)
	{
		this.is_municipioEmpresa = municipioEmpresa;
	}

	/**
	 * Gets the tipoEntidadEmpresa value for this TipoEmpresaAEI.
	 *
	 * @return tipoEntidadEmpresa
	 */
	public String getTipoEntidadEmpresa()
	{
		return is_tipoEntidadEmpresa;
	}

	/**
	 * Sets the tipoEntidadEmpresa value for this TipoEmpresaAEI.
	 *
	 * @param tipoEntidadEmpresa the new tipo entidad empresa
	 */
	public void setTipoEntidadEmpresa(String tipoEntidadEmpresa)
	{
		this.is_tipoEntidadEmpresa = tipoEntidadEmpresa;
	}

	/**
	 * Gets the actividadEconomica value for this TipoEmpresaAEI.
	 *
	 * @return actividadEconomica
	 */
	public String getActividadEconomica()
	{
		return is_actividadEconomica;
	}

	/**
	 * Sets the actividadEconomica value for this TipoEmpresaAEI.
	 *
	 * @param actividadEconomica the new actividad economica
	 */
	public void setActividadEconomica(String actividadEconomica)
	{
		this.is_actividadEconomica = actividadEconomica;
	}

	/**
	 * Gets the telefonoEmpresa value for this TipoEmpresaAEI.
	 *
	 * @return telefonoEmpresa
	 */
	public String getTelefonoEmpresa()
	{
		return is_telefonoEmpresa;
	}

	/**
	 * Sets the telefonoEmpresa value for this TipoEmpresaAEI.
	 *
	 * @param telefonoEmpresa the new telefono empresa
	 */
	public void setTelefonoEmpresa(String telefonoEmpresa)
	{
		this.is_telefonoEmpresa = telefonoEmpresa;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(Object obj)
	{
		if(!(obj instanceof TipoEmpresaAEI))
			return false;

		TipoEmpresaAEI other = (TipoEmpresaAEI)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.is_tipoDocumentoEmpresa == null) && (other.getTipoDocumentoEmpresa() == null))
				|| ((this.is_tipoDocumentoEmpresa != null)
				&& this.is_tipoDocumentoEmpresa.equals(other.getTipoDocumentoEmpresa())))
				&& (((this.is_numeroDocumentoEmpresa == null) && (other.getNumeroDocumentoEmpresa() == null))
				|| ((this.is_numeroDocumentoEmpresa != null)
				&& this.is_numeroDocumentoEmpresa.equals(other.getNumeroDocumentoEmpresa())))
				&& (((this.is_razonSocialEmpresa == null) && (other.getRazonSocialEmpresa() == null))
				|| ((this.is_razonSocialEmpresa != null)
				&& this.is_razonSocialEmpresa.equals(other.getRazonSocialEmpresa())))
				&& (((this.is_paisEmpresa == null) && (other.getPaisEmpresa() == null))
				|| ((this.is_paisEmpresa != null) && this.is_paisEmpresa.equals(other.getPaisEmpresa())))
				&& (((this.is_departamentoEmpresa == null) && (other.getDepartamentoEmpresa() == null))
				|| ((this.is_departamentoEmpresa != null)
				&& this.is_departamentoEmpresa.equals(other.getDepartamentoEmpresa())))
				&& (((this.is_municipioEmpresa == null) && (other.getMunicipioEmpresa() == null))
				|| ((this.is_municipioEmpresa != null) && this.is_municipioEmpresa.equals(other.getMunicipioEmpresa())))
				&& (((this.is_tipoEntidadEmpresa == null) && (other.getTipoEntidadEmpresa() == null))
				|| ((this.is_tipoEntidadEmpresa != null)
				&& this.is_tipoEntidadEmpresa.equals(other.getTipoEntidadEmpresa())))
				&& (((this.is_actividadEconomica == null) && (other.getActividadEconomica() == null))
				|| ((this.is_actividadEconomica != null)
				&& this.is_actividadEconomica.equals(other.getActividadEconomica())))
				&& (((this.is_telefonoEmpresa == null) && (other.getTelefonoEmpresa() == null))
				|| ((this.is_telefonoEmpresa != null) && this.is_telefonoEmpresa.equals(other.getTelefonoEmpresa())));
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

		if(getTipoDocumentoEmpresa() != null)
			_hashCode += getTipoDocumentoEmpresa().hashCode();

		if(getNumeroDocumentoEmpresa() != null)
			_hashCode += getNumeroDocumentoEmpresa().hashCode();

		if(getRazonSocialEmpresa() != null)
			_hashCode += getRazonSocialEmpresa().hashCode();

		if(getPaisEmpresa() != null)
			_hashCode += getPaisEmpresa().hashCode();

		if(getDepartamentoEmpresa() != null)
			_hashCode += getDepartamentoEmpresa().hashCode();

		if(getMunicipioEmpresa() != null)
			_hashCode += getMunicipioEmpresa().hashCode();

		if(getTipoEntidadEmpresa() != null)
			_hashCode += getTipoEntidadEmpresa().hashCode();

		if(getActividadEconomica() != null)
			_hashCode += getActividadEconomica().hashCode();

		if(getTelefonoEmpresa() != null)
			_hashCode += getTelefonoEmpresa().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}

	/**
	 * Return type metadata object.
	 *
	 * @return the type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Get Custom Serializer.
	 *
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    String mechType, Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer.
	 *
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    String mechType, Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}
}
