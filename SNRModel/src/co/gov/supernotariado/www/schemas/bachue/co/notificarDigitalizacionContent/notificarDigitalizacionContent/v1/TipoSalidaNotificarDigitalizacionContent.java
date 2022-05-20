/**
 * TipoSalidaNotificarDigitalizacionContent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.schemas.bachue.co.notificarDigitalizacionContent.notificarDigitalizacionContent.v1;

public class TipoSalidaNotificarDigitalizacionContent  implements java.io.Serializable {
    private java.math.BigInteger codigoMensaje;

    private java.lang.String descripcionMensaje;

    public TipoSalidaNotificarDigitalizacionContent() {
    }

    public TipoSalidaNotificarDigitalizacionContent(
           java.math.BigInteger codigoMensaje,
           java.lang.String descripcionMensaje) {
           this.codigoMensaje = codigoMensaje;
           this.descripcionMensaje = descripcionMensaje;
    }


    /**
     * Gets the codigoMensaje value for this TipoSalidaNotificarDigitalizacionContent.
     * 
     * @return codigoMensaje
     */
    public java.math.BigInteger getCodigoMensaje() {
        return codigoMensaje;
    }


    /**
     * Sets the codigoMensaje value for this TipoSalidaNotificarDigitalizacionContent.
     * 
     * @param codigoMensaje
     */
    public void setCodigoMensaje(java.math.BigInteger codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }


    /**
     * Gets the descripcionMensaje value for this TipoSalidaNotificarDigitalizacionContent.
     * 
     * @return descripcionMensaje
     */
    public java.lang.String getDescripcionMensaje() {
        return descripcionMensaje;
    }


    /**
     * Sets the descripcionMensaje value for this TipoSalidaNotificarDigitalizacionContent.
     * 
     * @param descripcionMensaje
     */
    public void setDescripcionMensaje(java.lang.String descripcionMensaje) {
        this.descripcionMensaje = descripcionMensaje;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoSalidaNotificarDigitalizacionContent)) return false;
        TipoSalidaNotificarDigitalizacionContent other = (TipoSalidaNotificarDigitalizacionContent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoMensaje==null && other.getCodigoMensaje()==null) || 
             (this.codigoMensaje!=null &&
              this.codigoMensaje.equals(other.getCodigoMensaje()))) &&
            ((this.descripcionMensaje==null && other.getDescripcionMensaje()==null) || 
             (this.descripcionMensaje!=null &&
              this.descripcionMensaje.equals(other.getDescripcionMensaje())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCodigoMensaje() != null) {
            _hashCode += getCodigoMensaje().hashCode();
        }
        if (getDescripcionMensaje() != null) {
            _hashCode += getDescripcionMensaje().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoSalidaNotificarDigitalizacionContent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/co/notificarDigitalizacionContent/notificarDigitalizacionContent/v1", "tipoSalidaNotificarDigitalizacionContent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoMensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/co/notificarDigitalizacionContent/notificarDigitalizacionContent/v1", "codigoMensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionMensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/co/notificarDigitalizacionContent/notificarDigitalizacionContent/v1", "descripcionMensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
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
     * Get Custom Deserializer
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
