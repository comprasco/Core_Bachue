/**
 * TipoSalidaObtenerPDFLiquidacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.obtenerpdfliquidacion.v2;

public class TipoSalidaObtenerPDFLiquidacion  implements java.io.Serializable {
    private byte[] documentoSerializado;

    private java.math.BigInteger codigoMensaje;

    private java.lang.String descripcionMensaje;

    public TipoSalidaObtenerPDFLiquidacion() {
    }

    public TipoSalidaObtenerPDFLiquidacion(
           byte[] documentoSerializado,
           java.math.BigInteger codigoMensaje,
           java.lang.String descripcionMensaje) {
           this.documentoSerializado = documentoSerializado;
           this.codigoMensaje = codigoMensaje;
           this.descripcionMensaje = descripcionMensaje;
    }


    /**
     * Gets the documentoSerializado value for this TipoSalidaObtenerPDFLiquidacion.
     * 
     * @return documentoSerializado
     */
    public byte[] getDocumentoSerializado() {
        return documentoSerializado;
    }


    /**
     * Sets the documentoSerializado value for this TipoSalidaObtenerPDFLiquidacion.
     * 
     * @param documentoSerializado
     */
    public void setDocumentoSerializado(byte[] documentoSerializado) {
        this.documentoSerializado = documentoSerializado;
    }


    /**
     * Gets the codigoMensaje value for this TipoSalidaObtenerPDFLiquidacion.
     * 
     * @return codigoMensaje
     */
    public java.math.BigInteger getCodigoMensaje() {
        return codigoMensaje;
    }


    /**
     * Sets the codigoMensaje value for this TipoSalidaObtenerPDFLiquidacion.
     * 
     * @param codigoMensaje
     */
    public void setCodigoMensaje(java.math.BigInteger codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }


    /**
     * Gets the descripcionMensaje value for this TipoSalidaObtenerPDFLiquidacion.
     * 
     * @return descripcionMensaje
     */
    public java.lang.String getDescripcionMensaje() {
        return descripcionMensaje;
    }


    /**
     * Sets the descripcionMensaje value for this TipoSalidaObtenerPDFLiquidacion.
     * 
     * @param descripcionMensaje
     */
    public void setDescripcionMensaje(java.lang.String descripcionMensaje) {
        this.descripcionMensaje = descripcionMensaje;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoSalidaObtenerPDFLiquidacion)) return false;
        TipoSalidaObtenerPDFLiquidacion other = (TipoSalidaObtenerPDFLiquidacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documentoSerializado==null && other.getDocumentoSerializado()==null) || 
             (this.documentoSerializado!=null &&
              java.util.Arrays.equals(this.documentoSerializado, other.getDocumentoSerializado()))) &&
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
        if (getDocumentoSerializado() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocumentoSerializado());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocumentoSerializado(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
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
        new org.apache.axis.description.TypeDesc(TipoSalidaObtenerPDFLiquidacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/obtenerpdfliquidacion/v2", "tipoSalidaObtenerPDFLiquidacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoSerializado");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/obtenerpdfliquidacion/v2", "documentoSerializado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoMensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/obtenerpdfliquidacion/v2", "codigoMensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionMensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/obtenerpdfliquidacion/v2", "descripcionMensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
