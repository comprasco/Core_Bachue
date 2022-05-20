/**
 * TipoEntradaActualizarEntidad.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1;



/**
 * The Class TipoEntradaActualizarEntidad.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaActualizarEntidad implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9055965088278509698L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaActualizarEntidad.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "tipoEntradaActualizarEntidad"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1", "modulo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("empresa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1", "empresa"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "tipoEmpresaAEI"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("representanteLegal");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "representanteLegal"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/actualizarentidad/v1",
		        "tipoRepresentanteLegalAEI"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The empresa. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoEmpresaAEI empresa;

	/** The modulo. */
	private java.lang.String modulo;

	/** The representante legal. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoRepresentanteLegalAEI representanteLegal;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo entrada actualizar entidad.
	 */
	public TipoEntradaActualizarEntidad()
	{
	}

	/**
	 * Instantiates a new tipo entrada actualizar entidad.
	 *
	 * @param modulo the modulo
	 * @param empresa the empresa
	 * @param representanteLegal the representante legal
	 */
	public TipoEntradaActualizarEntidad(
	    java.lang.String modulo,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoEmpresaAEI empresa,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoRepresentanteLegalAEI representanteLegal
	)
	{
		this.modulo                 = modulo;
		this.empresa                = empresa;
		this.representanteLegal     = representanteLegal;
	}

	/**
	 * Gets the modulo value for this TipoEntradaActualizarEntidad.
	 *
	 * @return modulo
	 */
	public java.lang.String getModulo()
	{
		return modulo;
	}

	/**
	 * Sets the modulo value for this TipoEntradaActualizarEntidad.
	 *
	 * @param modulo the new modulo
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the empresa value for this TipoEntradaActualizarEntidad.
	 *
	 * @return empresa
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoEmpresaAEI getEmpresa()
	{
		return empresa;
	}

	/**
	 * Sets the empresa value for this TipoEntradaActualizarEntidad.
	 *
	 * @param empresa the new empresa
	 */
	public void setEmpresa(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoEmpresaAEI empresa
	)
	{
		this.empresa = empresa;
	}

	/**
	 * Gets the representanteLegal value for this TipoEntradaActualizarEntidad.
	 *
	 * @return representanteLegal
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoRepresentanteLegalAEI getRepresentanteLegal()
	{
		return representanteLegal;
	}

	/**
	 * Sets the representanteLegal value for this TipoEntradaActualizarEntidad.
	 *
	 * @param representanteLegal the new representante legal
	 */
	public void setRepresentanteLegal(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoRepresentanteLegalAEI representanteLegal
	)
	{
		this.representanteLegal = representanteLegal;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaActualizarEntidad))
			return false;

		TipoEntradaActualizarEntidad other = (TipoEntradaActualizarEntidad)obj;

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
				&& (((this.empresa == null) && (other.getEmpresa() == null))
				|| ((this.empresa != null) && this.empresa.equals(other.getEmpresa())))
				&& (((this.representanteLegal == null) && (other.getRepresentanteLegal() == null))
				|| ((this.representanteLegal != null) && this.representanteLegal.equals(other.getRepresentanteLegal())));
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

		if(getEmpresa() != null)
			_hashCode += getEmpresa().hashCode();

		if(getRepresentanteLegal() != null)
			_hashCode += getRepresentanteLegal().hashCode();

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
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
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
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}
}
