/**
 * VerificacionDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades VerificacionDTO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class VerificacionDTO  extends com.bachue.snr.biometrico.servicios.ws.BaseDTO  implements java.io.Serializable {
    
    /** Propiedad id usuario. */
    private java.lang.String idUsuario;

    /** Propiedad posicion. */
    private com.bachue.snr.biometrico.servicios.ws.DedosEnum posicion;

    /** Propiedad sesion. */
    private java.lang.String sesion;

    /** Propiedad imagen huella. */
    private java.lang.String imagenHuella;

    /**
     * Instancia un nuevo objeto verificacion DTO.
     */
    public VerificacionDTO() {
    }

    /**
     * Instancia un nuevo objeto verificacion DTO.
     *
     * @param idUsuario de id usuario
     * @param posicion de posicion
     * @param sesion de sesion
     * @param imagenHuella de imagen huella
     */
    public VerificacionDTO(
           java.lang.String idUsuario,
           com.bachue.snr.biometrico.servicios.ws.DedosEnum posicion,
           java.lang.String sesion,
           java.lang.String imagenHuella) {
        this.idUsuario = idUsuario;
        this.posicion = posicion;
        this.sesion = sesion;
        this.imagenHuella = imagenHuella;
    }


    /**
     * Gets the idUsuario value for this VerificacionDTO.
     * 
     * @return idUsuario
     */
    public java.lang.String getIdUsuario() {
        return idUsuario;
    }


    /**
     * Sets the idUsuario value for this VerificacionDTO.
     *
     * @param idUsuario de id usuario
     */
    public void setIdUsuario(java.lang.String idUsuario) {
        this.idUsuario = idUsuario;
    }


    /**
     * Gets the posicion value for this VerificacionDTO.
     * 
     * @return posicion
     */
    public com.bachue.snr.biometrico.servicios.ws.DedosEnum getPosicion() {
        return posicion;
    }


    /**
     * Sets the posicion value for this VerificacionDTO.
     *
     * @param posicion de posicion
     */
    public void setPosicion(com.bachue.snr.biometrico.servicios.ws.DedosEnum posicion) {
        this.posicion = posicion;
    }


    /**
     * Gets the sesion value for this VerificacionDTO.
     * 
     * @return sesion
     */
    public java.lang.String getSesion() {
        return sesion;
    }


    /**
     * Sets the sesion value for this VerificacionDTO.
     *
     * @param sesion de sesion
     */
    public void setSesion(java.lang.String sesion) {
        this.sesion = sesion;
    }


    /**
     * Gets the imagenHuella value for this VerificacionDTO.
     * 
     * @return imagenHuella
     */
    public java.lang.String getImagenHuella() {
        return imagenHuella;
    }


    /**
     * Sets the imagenHuella value for this VerificacionDTO.
     *
     * @param imagenHuella de imagen huella
     */
    public void setImagenHuella(java.lang.String imagenHuella) {
        this.imagenHuella = imagenHuella;
    }

    /** Propiedad equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /** {@inheritdoc} */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VerificacionDTO)) return false;
        VerificacionDTO other = (VerificacionDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.idUsuario==null && other.getIdUsuario()==null) || 
             (this.idUsuario!=null &&
              this.idUsuario.equals(other.getIdUsuario()))) &&
            ((this.posicion==null && other.getPosicion()==null) || 
             (this.posicion!=null &&
              this.posicion.equals(other.getPosicion()))) &&
            ((this.sesion==null && other.getSesion()==null) || 
             (this.sesion!=null &&
              this.sesion.equals(other.getSesion()))) &&
            ((this.imagenHuella==null && other.getImagenHuella()==null) || 
             (this.imagenHuella!=null &&
              this.imagenHuella.equals(other.getImagenHuella())));
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
        if (getIdUsuario() != null) {
            _hashCode += getIdUsuario().hashCode();
        }
        if (getPosicion() != null) {
            _hashCode += getPosicion().hashCode();
        }
        if (getSesion() != null) {
            _hashCode += getSesion().hashCode();
        }
        if (getImagenHuella() != null) {
            _hashCode += getImagenHuella().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** Propiedad type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VerificacionDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "verificacionDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("posicion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "posicion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "dedosEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sesion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sesion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imagenHuella");
        elemField.setXmlName(new javax.xml.namespace.QName("", "imagenHuella"));
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
