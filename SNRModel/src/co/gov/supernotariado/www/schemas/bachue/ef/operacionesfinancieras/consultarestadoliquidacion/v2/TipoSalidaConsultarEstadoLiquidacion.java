/**
 * TipoSalidaConsultarEstadoLiquidacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultarestadoliquidacion.v2;

public class TipoSalidaConsultarEstadoLiquidacion  implements java.io.Serializable {
    private java.lang.String numeroReferencia;

    private java.lang.String codigoTransaccionRecaudador;

    private co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultarestadoliquidacion.v2.TipoSalidaConsultarEstadoLiquidacionEstadoTransaccion estadoTransaccion;

    private java.math.BigInteger codigoMensaje;

    private java.lang.String descripcionMensaje;

    public TipoSalidaConsultarEstadoLiquidacion() {
    }

    public TipoSalidaConsultarEstadoLiquidacion(
           java.lang.String numeroReferencia,
           java.lang.String codigoTransaccionRecaudador,
           co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultarestadoliquidacion.v2.TipoSalidaConsultarEstadoLiquidacionEstadoTransaccion estadoTransaccion,
           java.math.BigInteger codigoMensaje,
           java.lang.String descripcionMensaje) {
           this.numeroReferencia = numeroReferencia;
           this.codigoTransaccionRecaudador = codigoTransaccionRecaudador;
           this.estadoTransaccion = estadoTransaccion;
           this.codigoMensaje = codigoMensaje;
           this.descripcionMensaje = descripcionMensaje;
    }


    /**
     * Gets the numeroReferencia value for this TipoSalidaConsultarEstadoLiquidacion.
     * 
     * @return numeroReferencia
     */
    public java.lang.String getNumeroReferencia() {
        return numeroReferencia;
    }


    /**
     * Sets the numeroReferencia value for this TipoSalidaConsultarEstadoLiquidacion.
     * 
     * @param numeroReferencia
     */
    public void setNumeroReferencia(java.lang.String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }


    /**
     * Gets the codigoTransaccionRecaudador value for this TipoSalidaConsultarEstadoLiquidacion.
     * 
     * @return codigoTransaccionRecaudador
     */
    public java.lang.String getCodigoTransaccionRecaudador() {
        return codigoTransaccionRecaudador;
    }


    /**
     * Sets the codigoTransaccionRecaudador value for this TipoSalidaConsultarEstadoLiquidacion.
     * 
     * @param codigoTransaccionRecaudador
     */
    public void setCodigoTransaccionRecaudador(java.lang.String codigoTransaccionRecaudador) {
        this.codigoTransaccionRecaudador = codigoTransaccionRecaudador;
    }


    /**
     * Gets the estadoTransaccion value for this TipoSalidaConsultarEstadoLiquidacion.
     * 
     * @return estadoTransaccion
     */
    public co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultarestadoliquidacion.v2.TipoSalidaConsultarEstadoLiquidacionEstadoTransaccion getEstadoTransaccion() {
        return estadoTransaccion;
    }


    /**
     * Sets the estadoTransaccion value for this TipoSalidaConsultarEstadoLiquidacion.
     * 
     * @param estadoTransaccion
     */
    public void setEstadoTransaccion(co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultarestadoliquidacion.v2.TipoSalidaConsultarEstadoLiquidacionEstadoTransaccion estadoTransaccion) {
        this.estadoTransaccion = estadoTransaccion;
    }


    /**
     * Gets the codigoMensaje value for this TipoSalidaConsultarEstadoLiquidacion.
     * 
     * @return codigoMensaje
     */
    public java.math.BigInteger getCodigoMensaje() {
        return codigoMensaje;
    }


    /**
     * Sets the codigoMensaje value for this TipoSalidaConsultarEstadoLiquidacion.
     * 
     * @param codigoMensaje
     */
    public void setCodigoMensaje(java.math.BigInteger codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }


    /**
     * Gets the descripcionMensaje value for this TipoSalidaConsultarEstadoLiquidacion.
     * 
     * @return descripcionMensaje
     */
    public java.lang.String getDescripcionMensaje() {
        return descripcionMensaje;
    }


    /**
     * Sets the descripcionMensaje value for this TipoSalidaConsultarEstadoLiquidacion.
     * 
     * @param descripcionMensaje
     */
    public void setDescripcionMensaje(java.lang.String descripcionMensaje) {
        this.descripcionMensaje = descripcionMensaje;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoSalidaConsultarEstadoLiquidacion)) return false;
        TipoSalidaConsultarEstadoLiquidacion other = (TipoSalidaConsultarEstadoLiquidacion) obj;
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
              this.numeroReferencia.equals(other.getNumeroReferencia()))) &&
            ((this.codigoTransaccionRecaudador==null && other.getCodigoTransaccionRecaudador()==null) || 
             (this.codigoTransaccionRecaudador!=null &&
              this.codigoTransaccionRecaudador.equals(other.getCodigoTransaccionRecaudador()))) &&
            ((this.estadoTransaccion==null && other.getEstadoTransaccion()==null) || 
             (this.estadoTransaccion!=null &&
              this.estadoTransaccion.equals(other.getEstadoTransaccion()))) &&
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
        if (getNumeroReferencia() != null) {
            _hashCode += getNumeroReferencia().hashCode();
        }
        if (getCodigoTransaccionRecaudador() != null) {
            _hashCode += getCodigoTransaccionRecaudador().hashCode();
        }
        if (getEstadoTransaccion() != null) {
            _hashCode += getEstadoTransaccion().hashCode();
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
        new org.apache.axis.description.TypeDesc(TipoSalidaConsultarEstadoLiquidacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/consultarestadoliquidacion/v2", "tipoSalidaConsultarEstadoLiquidacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroReferencia");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/consultarestadoliquidacion/v2", "numeroReferencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTransaccionRecaudador");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/consultarestadoliquidacion/v2", "codigoTransaccionRecaudador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoTransaccion");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/consultarestadoliquidacion/v2", "estadoTransaccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/consultarestadoliquidacion/v2", ">tipoSalidaConsultarEstadoLiquidacion>estadoTransaccion"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoMensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/consultarestadoliquidacion/v2", "codigoMensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionMensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/consultarestadoliquidacion/v2", "descripcionMensaje"));
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
