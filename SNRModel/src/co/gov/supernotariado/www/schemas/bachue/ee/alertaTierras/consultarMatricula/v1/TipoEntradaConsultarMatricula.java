/**
 * TipoEntradaConsultarMatricula.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarMatricula.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaConsultarMatricula  implements java.io.Serializable {
    
    /** Propiedad cod circulo registral. */
    private java.lang.String codCirculoRegistral;

    /** Propiedad num matricula inmobiliaria. */
    private int numMatriculaInmobiliaria;

    /**
     * Instancia un nuevo objeto tipo entrada consultar matricula.
     */
    public TipoEntradaConsultarMatricula() {
    }

    /**
     * Instancia un nuevo objeto tipo entrada consultar matricula.
     *
     * @param codCirculoRegistral de cod circulo registral
     * @param numMatriculaInmobiliaria de num matricula inmobiliaria
     */
    public TipoEntradaConsultarMatricula(
           java.lang.String codCirculoRegistral,
           int numMatriculaInmobiliaria) {
           this.codCirculoRegistral = codCirculoRegistral;
           this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
    }


    /**
     * Gets the codCirculoRegistral value for this TipoEntradaConsultarMatricula.
     * 
     * @return codCirculoRegistral
     */
    public java.lang.String getCodCirculoRegistral() {
        return codCirculoRegistral;
    }


    /**
     * Sets the codCirculoRegistral value for this TipoEntradaConsultarMatricula.
     *
     * @param codCirculoRegistral de cod circulo registral
     */
    public void setCodCirculoRegistral(java.lang.String codCirculoRegistral) {
        this.codCirculoRegistral = codCirculoRegistral;
    }


    /**
     * Gets the numMatriculaInmobiliaria value for this TipoEntradaConsultarMatricula.
     * 
     * @return numMatriculaInmobiliaria
     */
    public int getNumMatriculaInmobiliaria() {
        return numMatriculaInmobiliaria;
    }


    /**
     * Sets the numMatriculaInmobiliaria value for this TipoEntradaConsultarMatricula.
     *
     * @param numMatriculaInmobiliaria de num matricula inmobiliaria
     */
    public void setNumMatriculaInmobiliaria(int numMatriculaInmobiliaria) {
        this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
    }

    /** Propiedad equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /** {@inheritdoc} */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoEntradaConsultarMatricula)) return false;
        TipoEntradaConsultarMatricula other = (TipoEntradaConsultarMatricula) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codCirculoRegistral==null && other.getCodCirculoRegistral()==null) || 
             (this.codCirculoRegistral!=null &&
              this.codCirculoRegistral.equals(other.getCodCirculoRegistral()))) &&
            this.numMatriculaInmobiliaria == other.getNumMatriculaInmobiliaria();
        __equalsCalc = null;
        return _equals;
    }

    /** Propiedad hash code calc. */
    private boolean __hashCodeCalc = false;
    
    /** {@inheritdoc} */
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCodCirculoRegistral() != null) {
            _hashCode += getCodCirculoRegistral().hashCode();
        }
        _hashCode += getNumMatriculaInmobiliaria();
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** Propiedad type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoEntradaConsultarMatricula.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", "tipoEntradaConsultarMatricula"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codCirculoRegistral");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", "codCirculoRegistral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numMatriculaInmobiliaria");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatricula/v1", "numMatriculaInmobiliaria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object.
     *
     * @return el valor de type desc
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
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
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
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
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
