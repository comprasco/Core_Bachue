/**
 * DedosEnum.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades DedosEnum.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class DedosEnum implements java.io.Serializable {
    
    /** Propiedad value. */
    private java.lang.String _value_;
    
    /** Propiedad table. */
    private static java.util.HashMap _table_ = new java.util.HashMap();

    /**
     * Instancia un nuevo objeto dedos enum.
     *
     * @param value de value
     */
    // Constructor
    protected DedosEnum(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    /** Constante _PULGAR_DERECHO. */
    public static final java.lang.String _PULGAR_DERECHO = "PULGAR_DERECHO";
    
    /** Constante _INDICE_DERECHO. */
    public static final java.lang.String _INDICE_DERECHO = "INDICE_DERECHO";
    
    /** Constante _CORAZON_DERECHO. */
    public static final java.lang.String _CORAZON_DERECHO = "CORAZON_DERECHO";
    
    /** Constante _ANULAR_DERECHO. */
    public static final java.lang.String _ANULAR_DERECHO = "ANULAR_DERECHO";
    
    /** Constante _MENIQUE_DERECHO. */
    public static final java.lang.String _MENIQUE_DERECHO = "MENIQUE_DERECHO";
    
    /** Constante _PULGAR_IZQUIERDO. */
    public static final java.lang.String _PULGAR_IZQUIERDO = "PULGAR_IZQUIERDO";
    
    /** Constante _INDICE_IZQUIERDO. */
    public static final java.lang.String _INDICE_IZQUIERDO = "INDICE_IZQUIERDO";
    
    /** Constante _CORAZON_IZQUIERDO. */
    public static final java.lang.String _CORAZON_IZQUIERDO = "CORAZON_IZQUIERDO";
    
    /** Constante _ANULAR_IZQUIERDO. */
    public static final java.lang.String _ANULAR_IZQUIERDO = "ANULAR_IZQUIERDO";
    
    /** Constante _MENIQUE_IZQUIERDO. */
    public static final java.lang.String _MENIQUE_IZQUIERDO = "MENIQUE_IZQUIERDO";
    
    /** Constante PULGAR_DERECHO. */
    public static final DedosEnum PULGAR_DERECHO = new DedosEnum(_PULGAR_DERECHO);
    
    /** Constante INDICE_DERECHO. */
    public static final DedosEnum INDICE_DERECHO = new DedosEnum(_INDICE_DERECHO);
    
    /** Constante CORAZON_DERECHO. */
    public static final DedosEnum CORAZON_DERECHO = new DedosEnum(_CORAZON_DERECHO);
    
    /** Constante ANULAR_DERECHO. */
    public static final DedosEnum ANULAR_DERECHO = new DedosEnum(_ANULAR_DERECHO);
    
    /** Constante MENIQUE_DERECHO. */
    public static final DedosEnum MENIQUE_DERECHO = new DedosEnum(_MENIQUE_DERECHO);
    
    /** Constante PULGAR_IZQUIERDO. */
    public static final DedosEnum PULGAR_IZQUIERDO = new DedosEnum(_PULGAR_IZQUIERDO);
    
    /** Constante INDICE_IZQUIERDO. */
    public static final DedosEnum INDICE_IZQUIERDO = new DedosEnum(_INDICE_IZQUIERDO);
    
    /** Constante CORAZON_IZQUIERDO. */
    public static final DedosEnum CORAZON_IZQUIERDO = new DedosEnum(_CORAZON_IZQUIERDO);
    
    /** Constante ANULAR_IZQUIERDO. */
    public static final DedosEnum ANULAR_IZQUIERDO = new DedosEnum(_ANULAR_IZQUIERDO);
    
    /** Constante MENIQUE_IZQUIERDO. */
    public static final DedosEnum MENIQUE_IZQUIERDO = new DedosEnum(_MENIQUE_IZQUIERDO);
    
    /**
     * Retorna Objeto o variable de valor value.
     *
     * @return el valor de value
     */
    public java.lang.String getValue() { return _value_;}
    
    /**
     * From value.
     *
     * @param value de value
     * @return el valor de dedos enum
     * @throws IllegalArgumentException cuando se produce algun error en el proceso
     */
    public static DedosEnum fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        DedosEnum enumeration = (DedosEnum)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    
    /**
     * From string.
     *
     * @param value de value
     * @return el valor de dedos enum
     * @throws IllegalArgumentException cuando se produce algun error en el proceso
     */
    public static DedosEnum fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    
    /** {@inheritdoc} */
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    
    /** {@inheritdoc} */
    public int hashCode() { return toString().hashCode();}
    
    /** {@inheritdoc} */
    public java.lang.String toString() { return _value_;}
    
    /**
     * Read resolve.
     *
     * @return el valor de java.lang. object
     * @throws ObjectStreamException cuando se produce algun error en el proceso
     */
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    
    /**
     * Retorna Objeto o variable de valor serializer.
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
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    
    /**
     * Retorna Objeto o variable de valor deserializer.
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
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    
    /** Propiedad type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DedosEnum.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "dedosEnum"));
    }
    
    /**
     * Return type metadata object.
     *
     * @return el valor de type desc
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
