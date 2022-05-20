/**
 * TipoEntradaConsultarSolicitud.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarSolicitud.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaConsultarSolicitud  implements java.io.Serializable {
    
    /** Propiedad nir. */
    private java.lang.String NIR;

    /**
     * Instancia un nuevo objeto tipo entrada consultar solicitud.
     */
    public TipoEntradaConsultarSolicitud() {
    }

    /**
     * Instancia un nuevo objeto tipo entrada consultar solicitud.
     *
     * @param NIR de nir
     */
    public TipoEntradaConsultarSolicitud(
           java.lang.String NIR) {
           this.NIR = NIR;
    }


    /**
     * Gets the NIR value for this TipoEntradaConsultarSolicitud.
     * 
     * @return NIR
     */
    public java.lang.String getNIR() {
        return NIR;
    }


    /**
     * Sets the NIR value for this TipoEntradaConsultarSolicitud.
     *
     * @param NIR de nir
     */
    public void setNIR(java.lang.String NIR) {
        this.NIR = NIR;
    }

    /** Propiedad equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /** {@inheritdoc} */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoEntradaConsultarSolicitud)) return false;
        TipoEntradaConsultarSolicitud other = (TipoEntradaConsultarSolicitud) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NIR==null && other.getNIR()==null) || 
             (this.NIR!=null &&
              this.NIR.equals(other.getNIR())));
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
        if (getNIR() != null) {
            _hashCode += getNIR().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** Propiedad type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoEntradaConsultarSolicitud.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", "tipoEntradaConsultarSolicitud"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NIR");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", "NIR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
