/**
 * TipoSalidaConsultarSolicitudCodigo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarSolicitudCodigo.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaConsultarSolicitudCodigo implements java.io.Serializable {
    
    /** Propiedad value. */
    private java.lang.String _value_;
    
    /** Propiedad table. */
    private static java.util.HashMap _table_ = new java.util.HashMap();

    /**
     * Instancia un nuevo objeto tipo salida consultar solicitud codigo.
     *
     * @param value de value
     */
    // Constructor
    protected TipoSalidaConsultarSolicitudCodigo(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    /** Constante _value1. */
    public static final java.lang.String _value1 = "200";
    
    /** Constante _value2. */
    public static final java.lang.String _value2 = "500";
    
    /** Constante value1. */
    public static final TipoSalidaConsultarSolicitudCodigo value1 = new TipoSalidaConsultarSolicitudCodigo(_value1);
    
    /** Constante value2. */
    public static final TipoSalidaConsultarSolicitudCodigo value2 = new TipoSalidaConsultarSolicitudCodigo(_value2);
    
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
     * @return el valor de tipo salida consultar solicitud codigo
     * @throws IllegalArgumentException cuando se produce algun error en el proceso
     */
    public static TipoSalidaConsultarSolicitudCodigo fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        TipoSalidaConsultarSolicitudCodigo enumeration = (TipoSalidaConsultarSolicitudCodigo)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    
    /**
     * From string.
     *
     * @param value de value
     * @return el valor de tipo salida consultar solicitud codigo
     * @throws IllegalArgumentException cuando se produce algun error en el proceso
     */
    public static TipoSalidaConsultarSolicitudCodigo fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(TipoSalidaConsultarSolicitudCodigo.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/consultarsolicitud/v1", ">tipoSalidaConsultarSolicitud>codigo"));
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
