/**
 * TipoEntradaEliminarProcAntiguoSistema.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaEliminarProcAntiguoSistema.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaEliminarProcAntiguoSistema  implements java.io.Serializable {
    
    /** Propiedad id alerta. */
    private int idAlerta;

    /** Propiedad id antiguo sistema tierras. */
    private int idAntiguoSistemaTierras;

    /**
     * Instancia un nuevo objeto tipo entrada eliminar proc antiguo sistema.
     */
    public TipoEntradaEliminarProcAntiguoSistema() {
    }

    /**
     * Instancia un nuevo objeto tipo entrada eliminar proc antiguo sistema.
     *
     * @param idAlerta de id alerta
     * @param idAntiguoSistemaTierras de id antiguo sistema tierras
     */
    public TipoEntradaEliminarProcAntiguoSistema(
           int idAlerta,
           int idAntiguoSistemaTierras) {
           this.idAlerta = idAlerta;
           this.idAntiguoSistemaTierras = idAntiguoSistemaTierras;
    }


    /**
     * Gets the idAlerta value for this TipoEntradaEliminarProcAntiguoSistema.
     * 
     * @return idAlerta
     */
    public int getIdAlerta() {
        return idAlerta;
    }


    /**
     * Sets the idAlerta value for this TipoEntradaEliminarProcAntiguoSistema.
     *
     * @param idAlerta de id alerta
     */
    public void setIdAlerta(int idAlerta) {
        this.idAlerta = idAlerta;
    }


    /**
     * Gets the idAntiguoSistemaTierras value for this TipoEntradaEliminarProcAntiguoSistema.
     * 
     * @return idAntiguoSistemaTierras
     */
    public int getIdAntiguoSistemaTierras() {
        return idAntiguoSistemaTierras;
    }


    /**
     * Sets the idAntiguoSistemaTierras value for this TipoEntradaEliminarProcAntiguoSistema.
     *
     * @param idAntiguoSistemaTierras de id antiguo sistema tierras
     */
    public void setIdAntiguoSistemaTierras(int idAntiguoSistemaTierras) {
        this.idAntiguoSistemaTierras = idAntiguoSistemaTierras;
    }

    /** Propiedad equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /** {@inheritdoc} */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoEntradaEliminarProcAntiguoSistema)) return false;
        TipoEntradaEliminarProcAntiguoSistema other = (TipoEntradaEliminarProcAntiguoSistema) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idAlerta == other.getIdAlerta() &&
            this.idAntiguoSistemaTierras == other.getIdAntiguoSistemaTierras();
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
        _hashCode += getIdAlerta();
        _hashCode += getIdAntiguoSistemaTierras();
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** Propiedad type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoEntradaEliminarProcAntiguoSistema.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/eliminarProcAntiguoSistema/v1", "tipoEntradaEliminarProcAntiguoSistema"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAlerta");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/eliminarProcAntiguoSistema/v1", "idAlerta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAntiguoSistemaTierras");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/eliminarProcAntiguoSistema/v1", "idAntiguoSistemaTierras"));
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
