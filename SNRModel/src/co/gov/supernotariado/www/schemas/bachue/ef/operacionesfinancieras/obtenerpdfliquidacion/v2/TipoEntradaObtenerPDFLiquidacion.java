/**
 * TipoEntradaObtenerPDFLiquidacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.obtenerpdfliquidacion.v2;

public class TipoEntradaObtenerPDFLiquidacion  implements java.io.Serializable {
    private java.lang.String numeroReferencia;

    public TipoEntradaObtenerPDFLiquidacion() {
    }

    public TipoEntradaObtenerPDFLiquidacion(
           java.lang.String numeroReferencia) {
           this.numeroReferencia = numeroReferencia;
    }


    /**
     * Gets the numeroReferencia value for this TipoEntradaObtenerPDFLiquidacion.
     * 
     * @return numeroReferencia
     */
    public java.lang.String getNumeroReferencia() {
        return numeroReferencia;
    }


    /**
     * Sets the numeroReferencia value for this TipoEntradaObtenerPDFLiquidacion.
     * 
     * @param numeroReferencia
     */
    public void setNumeroReferencia(java.lang.String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoEntradaObtenerPDFLiquidacion)) return false;
        TipoEntradaObtenerPDFLiquidacion other = (TipoEntradaObtenerPDFLiquidacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numeroReferencia==null && other.getNumeroReferencia()==null) || 
             (this.numeroReferencia!=null &&
              this.numeroReferencia.equals(other.getNumeroReferencia())));
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
        if (getNumeroReferencia() != null) {
            _hashCode += getNumeroReferencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoEntradaObtenerPDFLiquidacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/obtenerpdfliquidacion/v2", "tipoEntradaObtenerPDFLiquidacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroReferencia");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/obtenerpdfliquidacion/v2", "numeroReferencia"));
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
