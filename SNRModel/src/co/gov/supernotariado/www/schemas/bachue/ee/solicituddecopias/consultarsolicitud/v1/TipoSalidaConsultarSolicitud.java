/**
 * TipoSalidaConsultarSolicitud.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarSolicitud.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaConsultarSolicitud  implements java.io.Serializable {
    
    /** Propiedad valor pago. */
    private java.lang.String valorPago;

    /** Propiedad referencia pago. */
    private java.lang.String referenciaPago;

    /** Propiedad estado. */
    private java.lang.String estado;

    /** Propiedad fecha liquidacion. */
    private java.util.Date fechaLiquidacion;

    /** Propiedad fecha vencimiento pago. */
    private java.util.Date fechaVencimientoPago;

    /** Propiedad codigo. */
    private co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoSalidaConsultarSolicitudCodigo codigo;

    /** Propiedad mensaje. */
    private java.lang.String mensaje;

    /**
     * Instancia un nuevo objeto tipo salida consultar solicitud.
     */
    public TipoSalidaConsultarSolicitud() {
    }

    /**
     * Instancia un nuevo objeto tipo salida consultar solicitud.
     *
     * @param valorPago de valor pago
     * @param referenciaPago de referencia pago
     * @param estado de estado
     * @param fechaLiquidacion de fecha liquidacion
     * @param fechaVencimientoPago de fecha vencimiento pago
     * @param codigo de codigo
     * @param mensaje de mensaje
     */
    public TipoSalidaConsultarSolicitud(
           java.lang.String valorPago,
           java.lang.String referenciaPago,
           java.lang.String estado,
           java.util.Date fechaLiquidacion,
           java.util.Date fechaVencimientoPago,
           co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoSalidaConsultarSolicitudCodigo codigo,
           java.lang.String mensaje) {
           this.valorPago = valorPago;
           this.referenciaPago = referenciaPago;
           this.estado = estado;
           this.fechaLiquidacion = fechaLiquidacion;
           this.fechaVencimientoPago = fechaVencimientoPago;
           this.codigo = codigo;
           this.mensaje = mensaje;
    }


    /**
     * Gets the valorPago value for this TipoSalidaConsultarSolicitud.
     * 
     * @return valorPago
     */
    public java.lang.String getValorPago() {
        return valorPago;
    }


    /**
     * Sets the valorPago value for this TipoSalidaConsultarSolicitud.
     *
     * @param valorPago de valor pago
     */
    public void setValorPago(java.lang.String valorPago) {
        this.valorPago = valorPago;
    }


    /**
     * Gets the referenciaPago value for this TipoSalidaConsultarSolicitud.
     * 
     * @return referenciaPago
     */
    public java.lang.String getReferenciaPago() {
        return referenciaPago;
    }


    /**
     * Sets the referenciaPago value for this TipoSalidaConsultarSolicitud.
     *
     * @param referenciaPago de referencia pago
     */
    public void setReferenciaPago(java.lang.String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }


    /**
     * Gets the estado value for this TipoSalidaConsultarSolicitud.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this TipoSalidaConsultarSolicitud.
     *
     * @param estado de estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the fechaLiquidacion value for this TipoSalidaConsultarSolicitud.
     * 
     * @return fechaLiquidacion
     */
    public java.util.Date getFechaLiquidacion() {
        return fechaLiquidacion;
    }


    /**
     * Sets the fechaLiquidacion value for this TipoSalidaConsultarSolicitud.
     *
     * @param fechaLiquidacion de fecha liquidacion
     */
    public void setFechaLiquidacion(java.util.Date fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }


    /**
     * Gets the fechaVencimientoPago value for this TipoSalidaConsultarSolicitud.
     * 
     * @return fechaVencimientoPago
     */
    public java.util.Date getFechaVencimientoPago() {
        return fechaVencimientoPago;
    }


    /**
     * Sets the fechaVencimientoPago value for this TipoSalidaConsultarSolicitud.
     *
     * @param fechaVencimientoPago de fecha vencimiento pago
     */
    public void setFechaVencimientoPago(java.util.Date fechaVencimientoPago) {
        this.fechaVencimientoPago = fechaVencimientoPago;
    }


    /**
     * Gets the codigo value for this TipoSalidaConsultarSolicitud.
     * 
     * @return codigo
     */
    public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoSalidaConsultarSolicitudCodigo getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this TipoSalidaConsultarSolicitud.
     *
     * @param codigo de codigo
     */
    public void setCodigo(co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoSalidaConsultarSolicitudCodigo codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the mensaje value for this TipoSalidaConsultarSolicitud.
     * 
     * @return mensaje
     */
    public java.lang.String getMensaje() {
        return mensaje;
    }


    /**
     * Sets the mensaje value for this TipoSalidaConsultarSolicitud.
     *
     * @param mensaje de mensaje
     */
    public void setMensaje(java.lang.String mensaje) {
        this.mensaje = mensaje;
    }

    /** Propiedad equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /** {@inheritdoc} */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoSalidaConsultarSolicitud)) return false;
        TipoSalidaConsultarSolicitud other = (TipoSalidaConsultarSolicitud) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.valorPago==null && other.getValorPago()==null) || 
             (this.valorPago!=null &&
              this.valorPago.equals(other.getValorPago()))) &&
            ((this.referenciaPago==null && other.getReferenciaPago()==null) || 
             (this.referenciaPago!=null &&
              this.referenciaPago.equals(other.getReferenciaPago()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.fechaLiquidacion==null && other.getFechaLiquidacion()==null) || 
             (this.fechaLiquidacion!=null &&
              this.fechaLiquidacion.equals(other.getFechaLiquidacion()))) &&
            ((this.fechaVencimientoPago==null && other.getFechaVencimientoPago()==null) || 
             (this.fechaVencimientoPago!=null &&
              this.fechaVencimientoPago.equals(other.getFechaVencimientoPago()))) &&
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.mensaje==null && other.getMensaje()==null) || 
             (this.mensaje!=null &&
              this.mensaje.equals(other.getMensaje())));
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
        if (getValorPago() != null) {
            _hashCode += getValorPago().hashCode();
        }
        if (getReferenciaPago() != null) {
            _hashCode += getReferenciaPago().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getFechaLiquidacion() != null) {
            _hashCode += getFechaLiquidacion().hashCode();
        }
        if (getFechaVencimientoPago() != null) {
            _hashCode += getFechaVencimientoPago().hashCode();
        }
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        if (getMensaje() != null) {
            _hashCode += getMensaje().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** Propiedad type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoSalidaConsultarSolicitud.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", "tipoSalidaConsultarSolicitud"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPago");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", "valorPago"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenciaPago");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", "referenciaPago"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaLiquidacion");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", "fechaLiquidacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaVencimientoPago");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", "fechaVencimientoPago"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", ">tipoSalidaConsultarSolicitud>codigo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", "mensaje"));
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
