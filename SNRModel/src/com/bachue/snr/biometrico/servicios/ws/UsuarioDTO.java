/**
 * UsuarioDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades UsuarioDTO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class UsuarioDTO  extends com.bachue.snr.biometrico.servicios.ws.BaseDTO  implements java.io.Serializable {
    
    /** Propiedad clave. */
    private java.lang.String clave;

    /** Propiedad clave activa. */
    private java.lang.String claveActiva;

    /** Propiedad fecha vencimiento. */
    private java.util.Calendar fechaVencimiento;

    /** Propiedad id usuario. */
    private java.lang.String idUsuario;

    /** Propiedad id usuario creacion. */
    private java.lang.String idUsuarioCreacion;

    /**
     * Instancia un nuevo objeto usuario DTO.
     */
    public UsuarioDTO() {
    }

    /**
     * Instancia un nuevo objeto usuario DTO.
     *
     * @param clave de clave
     * @param claveActiva de clave activa
     * @param fechaVencimiento de fecha vencimiento
     * @param idUsuario de id usuario
     * @param idUsuarioCreacion de id usuario creacion
     */
    public UsuarioDTO(
           java.lang.String clave,
           java.lang.String claveActiva,
           java.util.Calendar fechaVencimiento,
           java.lang.String idUsuario,
           java.lang.String idUsuarioCreacion) {
        this.clave = clave;
        this.claveActiva = claveActiva;
        this.fechaVencimiento = fechaVencimiento;
        this.idUsuario = idUsuario;
        this.idUsuarioCreacion = idUsuarioCreacion;
    }


    /**
     * Gets the clave value for this UsuarioDTO.
     * 
     * @return clave
     */
    public java.lang.String getClave() {
        return clave;
    }


    /**
     * Sets the clave value for this UsuarioDTO.
     *
     * @param clave de clave
     */
    public void setClave(java.lang.String clave) {
        this.clave = clave;
    }


    /**
     * Gets the claveActiva value for this UsuarioDTO.
     * 
     * @return claveActiva
     */
    public java.lang.String getClaveActiva() {
        return claveActiva;
    }


    /**
     * Sets the claveActiva value for this UsuarioDTO.
     *
     * @param claveActiva de clave activa
     */
    public void setClaveActiva(java.lang.String claveActiva) {
        this.claveActiva = claveActiva;
    }


    /**
     * Gets the fechaVencimiento value for this UsuarioDTO.
     * 
     * @return fechaVencimiento
     */
    public java.util.Calendar getFechaVencimiento() {
        return fechaVencimiento;
    }


    /**
     * Sets the fechaVencimiento value for this UsuarioDTO.
     *
     * @param fechaVencimiento de fecha vencimiento
     */
    public void setFechaVencimiento(java.util.Calendar fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }


    /**
     * Gets the idUsuario value for this UsuarioDTO.
     * 
     * @return idUsuario
     */
    public java.lang.String getIdUsuario() {
        return idUsuario;
    }


    /**
     * Sets the idUsuario value for this UsuarioDTO.
     *
     * @param idUsuario de id usuario
     */
    public void setIdUsuario(java.lang.String idUsuario) {
        this.idUsuario = idUsuario;
    }


    /**
     * Gets the idUsuarioCreacion value for this UsuarioDTO.
     * 
     * @return idUsuarioCreacion
     */
    public java.lang.String getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }


    /**
     * Sets the idUsuarioCreacion value for this UsuarioDTO.
     *
     * @param idUsuarioCreacion de id usuario creacion
     */
    public void setIdUsuarioCreacion(java.lang.String idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    /** Propiedad equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /** {@inheritdoc} */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UsuarioDTO)) return false;
        UsuarioDTO other = (UsuarioDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.clave==null && other.getClave()==null) || 
             (this.clave!=null &&
              this.clave.equals(other.getClave()))) &&
            ((this.claveActiva==null && other.getClaveActiva()==null) || 
             (this.claveActiva!=null &&
              this.claveActiva.equals(other.getClaveActiva()))) &&
            ((this.fechaVencimiento==null && other.getFechaVencimiento()==null) || 
             (this.fechaVencimiento!=null &&
              this.fechaVencimiento.equals(other.getFechaVencimiento()))) &&
            ((this.idUsuario==null && other.getIdUsuario()==null) || 
             (this.idUsuario!=null &&
              this.idUsuario.equals(other.getIdUsuario()))) &&
            ((this.idUsuarioCreacion==null && other.getIdUsuarioCreacion()==null) || 
             (this.idUsuarioCreacion!=null &&
              this.idUsuarioCreacion.equals(other.getIdUsuarioCreacion())));
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
        int _hashCode = super.hashCode();
        if (getClave() != null) {
            _hashCode += getClave().hashCode();
        }
        if (getClaveActiva() != null) {
            _hashCode += getClaveActiva().hashCode();
        }
        if (getFechaVencimiento() != null) {
            _hashCode += getFechaVencimiento().hashCode();
        }
        if (getIdUsuario() != null) {
            _hashCode += getIdUsuario().hashCode();
        }
        if (getIdUsuarioCreacion() != null) {
            _hashCode += getIdUsuarioCreacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** Propiedad type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UsuarioDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "usuarioDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clave");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("claveActiva");
        elemField.setXmlName(new javax.xml.namespace.QName("", "claveActiva"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaVencimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaVencimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idUsuarioCreacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idUsuarioCreacion"));
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
