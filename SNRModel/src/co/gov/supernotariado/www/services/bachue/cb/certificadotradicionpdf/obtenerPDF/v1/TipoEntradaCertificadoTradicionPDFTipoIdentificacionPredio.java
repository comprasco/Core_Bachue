/**
 * TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio implements java.io.Serializable {
    
    /** Propiedad value. */
    private java.lang.String _value_;
    
    /** Propiedad table. */
    private static java.util.HashMap _table_ = new java.util.HashMap();

    /**
     * Instancia un nuevo objeto tipo entrada certificado tradicion PDF tipo identificacion predio.
     *
     * @param value de value
     */
    // Constructor
    protected TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    /** Constante _matricula. */
    public static final java.lang.String _matricula = "matricula";
    
    /** Constante _numeroPredial. */
    public static final java.lang.String _numeroPredial = "numeroPredial";
    
    /** Constante _numeroPredialAnterior. */
    public static final java.lang.String _numeroPredialAnterior = "numeroPredialAnterior";
    
    /** Constante _NUPRE. */
    public static final java.lang.String _NUPRE = "NUPRE";
    
    /** Constante matricula. */
    public static final TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio matricula = new TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio(_matricula);
    
    /** Constante numeroPredial. */
    public static final TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio numeroPredial = new TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio(_numeroPredial);
    
    /** Constante numeroPredialAnterior. */
    public static final TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio numeroPredialAnterior = new TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio(_numeroPredialAnterior);
    
    /** Constante NUPRE. */
    public static final TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio NUPRE = new TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio(_NUPRE);
    
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
     * @return el valor de tipo entrada certificado tradicion PDF tipo identificacion predio
     * @throws IllegalArgumentException cuando se produce algun error en el proceso
     */
    public static TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio enumeration = (TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    
    /**
     * From string.
     *
     * @param value de value
     * @return el valor de tipo entrada certificado tradicion PDF tipo identificacion predio
     * @throws IllegalArgumentException cuando se produce algun error en el proceso
     */
    public static TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/certificadotradicionpdf/obtenerPDF/v1", ">tipoEntradaCertificadoTradicionPDF>tipoIdentificacionPredio"));
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
