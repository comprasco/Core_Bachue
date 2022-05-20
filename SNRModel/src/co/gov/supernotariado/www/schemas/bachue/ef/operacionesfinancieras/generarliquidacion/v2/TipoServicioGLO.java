/**
 * TipoServicioGLO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.generarliquidacion.v2;

public class TipoServicioGLO  implements java.io.Serializable {
    private java.lang.String tipoServicio;

    private java.lang.String subtipoServicio;

    private java.math.BigInteger cantidadSolicitada;

    private java.math.BigDecimal valorServicio;

    public TipoServicioGLO() {
    }

    public TipoServicioGLO(
           java.lang.String tipoServicio,
           java.lang.String subtipoServicio,
           java.math.BigInteger cantidadSolicitada,
           java.math.BigDecimal valorServicio) {
           this.tipoServicio = tipoServicio;
           this.subtipoServicio = subtipoServicio;
           this.cantidadSolicitada = cantidadSolicitada;
           this.valorServicio = valorServicio;
    }


    /**
     * Gets the tipoServicio value for this TipoServicioGLO.
     * 
     * @return tipoServicio
     */
    public java.lang.String getTipoServicio() {
        return tipoServicio;
    }


    /**
     * Sets the tipoServicio value for this TipoServicioGLO.
     * 
     * @param tipoServicio
     */
    public void setTipoServicio(java.lang.String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }


    /**
     * Gets the subtipoServicio value for this TipoServicioGLO.
     * 
     * @return subtipoServicio
     */
    public java.lang.String getSubtipoServicio() {
        return subtipoServicio;
    }


    /**
     * Sets the subtipoServicio value for this TipoServicioGLO.
     * 
     * @param subtipoServicio
     */
    public void setSubtipoServicio(java.lang.String subtipoServicio) {
        this.subtipoServicio = subtipoServicio;
    }


    /**
     * Gets the cantidadSolicitada value for this TipoServicioGLO.
     * 
     * @return cantidadSolicitada
     */
    public java.math.BigInteger getCantidadSolicitada() {
        return cantidadSolicitada;
    }


    /**
     * Sets the cantidadSolicitada value for this TipoServicioGLO.
     * 
     * @param cantidadSolicitada
     */
    public void setCantidadSolicitada(java.math.BigInteger cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }


    /**
     * Gets the valorServicio value for this TipoServicioGLO.
     * 
     * @return valorServicio
     */
    public java.math.BigDecimal getValorServicio() {
        return valorServicio;
    }


    /**
     * Sets the valorServicio value for this TipoServicioGLO.
     * 
     * @param valorServicio
     */
    public void setValorServicio(java.math.BigDecimal valorServicio) {
        this.valorServicio = valorServicio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoServicioGLO)) return false;
        TipoServicioGLO other = (TipoServicioGLO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoServicio==null && other.getTipoServicio()==null) || 
             (this.tipoServicio!=null &&
              this.tipoServicio.equals(other.getTipoServicio()))) &&
            ((this.subtipoServicio==null && other.getSubtipoServicio()==null) || 
             (this.subtipoServicio!=null &&
              this.subtipoServicio.equals(other.getSubtipoServicio()))) &&
            ((this.cantidadSolicitada==null && other.getCantidadSolicitada()==null) || 
             (this.cantidadSolicitada!=null &&
              this.cantidadSolicitada.equals(other.getCantidadSolicitada()))) &&
            ((this.valorServicio==null && other.getValorServicio()==null) || 
             (this.valorServicio!=null &&
              this.valorServicio.equals(other.getValorServicio())));
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
        if (getTipoServicio() != null) {
            _hashCode += getTipoServicio().hashCode();
        }
        if (getSubtipoServicio() != null) {
            _hashCode += getSubtipoServicio().hashCode();
        }
        if (getCantidadSolicitada() != null) {
            _hashCode += getCantidadSolicitada().hashCode();
        }
        if (getValorServicio() != null) {
            _hashCode += getValorServicio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoServicioGLO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/generarliquidacion/v2", "tipoServicioGLO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoServicio");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/generarliquidacion/v2", "tipoServicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subtipoServicio");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/generarliquidacion/v2", "subtipoServicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadSolicitada");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/generarliquidacion/v2", "cantidadSolicitada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorServicio");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/generarliquidacion/v2", "valorServicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
