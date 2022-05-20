/**
 * TipoEntradaConsultarCatalogos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.schemas.bachue.ci.consultacatalogos.consultarcatalogos.v1;

public class TipoEntradaConsultarCatalogos  implements java.io.Serializable {
    private java.lang.String modulo;

    private java.lang.String catalogo;

    private java.lang.String parametro;

    public TipoEntradaConsultarCatalogos() {
    }

    public TipoEntradaConsultarCatalogos(
           java.lang.String modulo,
           java.lang.String catalogo,
           java.lang.String parametro) {
           this.modulo = modulo;
           this.catalogo = catalogo;
           this.parametro = parametro;
    }


    /**
     * Gets the modulo value for this TipoEntradaConsultarCatalogos.
     * 
     * @return modulo
     */
    public java.lang.String getModulo() {
        return modulo;
    }


    /**
     * Sets the modulo value for this TipoEntradaConsultarCatalogos.
     * 
     * @param modulo
     */
    public void setModulo(java.lang.String modulo) {
        this.modulo = modulo;
    }


    /**
     * Gets the catalogo value for this TipoEntradaConsultarCatalogos.
     * 
     * @return catalogo
     */
    public java.lang.String getCatalogo() {
        return catalogo;
    }


    /**
     * Sets the catalogo value for this TipoEntradaConsultarCatalogos.
     * 
     * @param catalogo
     */
    public void setCatalogo(java.lang.String catalogo) {
        this.catalogo = catalogo;
    }


    /**
     * Gets the parametro value for this TipoEntradaConsultarCatalogos.
     * 
     * @return parametro
     */
    public java.lang.String getParametro() {
        return parametro;
    }


    /**
     * Sets the parametro value for this TipoEntradaConsultarCatalogos.
     * 
     * @param parametro
     */
    public void setParametro(java.lang.String parametro) {
        this.parametro = parametro;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoEntradaConsultarCatalogos)) return false;
        TipoEntradaConsultarCatalogos other = (TipoEntradaConsultarCatalogos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.modulo==null && other.getModulo()==null) || 
             (this.modulo!=null &&
              this.modulo.equals(other.getModulo()))) &&
            ((this.catalogo==null && other.getCatalogo()==null) || 
             (this.catalogo!=null &&
              this.catalogo.equals(other.getCatalogo()))) &&
            ((this.parametro==null && other.getParametro()==null) || 
             (this.parametro!=null &&
              this.parametro.equals(other.getParametro())));
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
        if (getModulo() != null) {
            _hashCode += getModulo().hashCode();
        }
        if (getCatalogo() != null) {
            _hashCode += getCatalogo().hashCode();
        }
        if (getParametro() != null) {
            _hashCode += getParametro().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoEntradaConsultarCatalogos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ci/consultacatalogos/consultarcatalogos/v1", "tipoEntradaConsultarCatalogos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modulo");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ci/consultacatalogos/consultarcatalogos/v1", "modulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("catalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ci/consultacatalogos/consultarcatalogos/v1", "catalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametro");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ci/consultacatalogos/consultarcatalogos/v1", "parametro"));
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
