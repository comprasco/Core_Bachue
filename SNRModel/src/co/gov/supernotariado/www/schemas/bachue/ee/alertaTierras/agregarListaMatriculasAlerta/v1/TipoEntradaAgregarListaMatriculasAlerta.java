/**
 * TipoEntradaAgregarListaMatriculasAlerta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaAgregarListaMatriculasAlerta.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoEntradaAgregarListaMatriculasAlerta implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7812572749650750499L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaAgregarListaMatriculasAlerta.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1",
		        "tipoEntradaAgregarListaMatriculasAlerta"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idAlerta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1",
		        "idAlerta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaMatriculas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1",
		        "listaMatriculas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1",
		        ">>tipoEntradaAgregarListaMatriculasAlerta>listaMatriculas>matricula"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/agregarListaMatriculasAlerta/v1",
		        "matricula"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad lista matriculas. */
	private co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoEntradaAgregarListaMatriculasAlertaListaMatriculasMatricula[] listaMatriculas;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/** Propiedad id alerta. */
	private int idAlerta;

	/**
	 * Instancia un nuevo objeto tipo entrada agregar lista matriculas alerta.
	 */
	public TipoEntradaAgregarListaMatriculasAlerta()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada agregar lista matriculas alerta.
	 *
	 * @param idAlerta de id alerta
	 * @param listaMatriculas de lista matriculas
	 */
	public TipoEntradaAgregarListaMatriculasAlerta(
	    int idAlerta,
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoEntradaAgregarListaMatriculasAlertaListaMatriculasMatricula[] listaMatriculas
	)
	{
		this.idAlerta            = idAlerta;
		this.listaMatriculas     = listaMatriculas;
	}

	/**
	 * Gets the idAlerta value for this TipoEntradaAgregarListaMatriculasAlerta.
	 *
	 * @return idAlerta
	 */
	public int getIdAlerta()
	{
		return idAlerta;
	}

	/**
	 * Sets the idAlerta value for this TipoEntradaAgregarListaMatriculasAlerta.
	 *
	 * @param idAlerta de id alerta
	 */
	public void setIdAlerta(int idAlerta)
	{
		this.idAlerta = idAlerta;
	}

	/**
	 * Gets the listaMatriculas value for this TipoEntradaAgregarListaMatriculasAlerta.
	 *
	 * @return listaMatriculas
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoEntradaAgregarListaMatriculasAlertaListaMatriculasMatricula[] getListaMatriculas()
	{
		return listaMatriculas;
	}

	/**
	 * Sets the listaMatriculas value for this TipoEntradaAgregarListaMatriculasAlerta.
	 *
	 * @param listaMatriculas de lista matriculas
	 */
	public void setListaMatriculas(
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoEntradaAgregarListaMatriculasAlertaListaMatriculasMatricula[] listaMatriculas
	)
	{
		this.listaMatriculas = listaMatriculas;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaAgregarListaMatriculasAlerta))
			return false;

		TipoEntradaAgregarListaMatriculasAlerta other = (TipoEntradaAgregarListaMatriculasAlerta)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true && (this.idAlerta == other.getIdAlerta())
				&& (((this.listaMatriculas == null) && (other.getListaMatriculas() == null))
				|| ((this.listaMatriculas != null)
				&& java.util.Arrays.equals(this.listaMatriculas, other.getListaMatriculas())));
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
		_hashCode += getIdAlerta();

		if(getListaMatriculas() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaMatriculas()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaMatriculas(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
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
}
