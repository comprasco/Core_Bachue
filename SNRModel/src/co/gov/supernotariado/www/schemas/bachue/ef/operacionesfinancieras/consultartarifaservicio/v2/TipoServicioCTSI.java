/**
 * TipoServicioCTSI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultartarifaservicio.v2;

public class TipoServicioCTSI  implements java.io.Serializable {
    private java.lang.String tipoServicio;

    private java.lang.String subtipoServicio;

    private co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultartarifaservicio.v2.TipoCriterioCTSI[] criterios;

    public TipoServicioCTSI() {
    }

    public TipoServicioCTSI(
           java.lang.String tipoServicio,
           java.lang.String subtipoServicio,
           co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultartarifaservicio.v2.TipoCriterioCTSI[] criterios) {
           this.tipoServicio = tipoServicio;
           this.subtipoServicio = subtipoServicio;
           this.criterios = criterios;
    }


    /**
     * Gets the tipoServicio value for this TipoServicioCTSI.
     * 
     * @return tipoServicio
     */
    public java.lang.String getTipoServicio() {
        return tipoServicio;
    }


    /**
     * Sets the tipoServicio value for this TipoServicioCTSI.
     * 
     * @param tipoServicio
     */
    public void setTipoServicio(java.lang.String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }


    /**
     * Gets the subtipoServicio value for this TipoServicioCTSI.
     * 
     * @return subtipoServicio
     */
    public java.lang.String getSubtipoServicio() {
        return subtipoServicio;
    }


    /**
     * Sets the subtipoServicio value for this TipoServicioCTSI.
     * 
     * @param subtipoServicio
     */
    public void setSubtipoServicio(java.lang.String subtipoServicio) {
        this.subtipoServicio = subtipoServicio;
    }


    /**
     * Gets the criterios value for this TipoServicioCTSI.
     * 
     * @return criterios
     */
    public co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultartarifaservicio.v2.TipoCriterioCTSI[] getCriterios() {
        return criterios;
    }


    /**
     * Sets the criterios value for this TipoServicioCTSI.
     * 
     * @param criterios
     */
    public void setCriterios(co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultartarifaservicio.v2.TipoCriterioCTSI[] criterios) {
        this.criterios = criterios;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoServicioCTSI)) return false;
        TipoServicioCTSI other = (TipoServicioCTSI) obj;
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
            ((this.criterios==null && other.getCriterios()==null) || 
             (this.criterios!=null &&
              java.util.Arrays.equals(this.criterios, other.getCriterios())));
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
        if (getCriterios() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCriterios());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCriterios(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoServicioCTSI.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/consultartarifaservicio/v2", "tipoServicioCTSI"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoServicio");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/consultartarifaservicio/v2", "tipoServicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subtipoServicio");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/consultartarifaservicio/v2", "subtipoServicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("criterios");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/consultartarifaservicio/v2", "criterios"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/consultartarifaservicio/v2", "tipoCriterioCTSI"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/consultartarifaservicio/v2", "criterio"));
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
