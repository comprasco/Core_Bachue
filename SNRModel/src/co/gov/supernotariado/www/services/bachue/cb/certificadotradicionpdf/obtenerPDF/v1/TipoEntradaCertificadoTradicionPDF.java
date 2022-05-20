/**
 * TipoEntradaCertificadoTradicionPDF.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1;



/**
 * El esquema define los
 * 						datos de Entrada para la operación Obtener PDF en Base64 de
 * 						Certificado de
 * 						Tradición y Libertad.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaCertificadoTradicionPDF  implements java.io.Serializable {
    
    /** Propiedad tipo PDF. */
    /* Corresponde al tipo de PDF que se va a obtener */
    private co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDFTipoPDF tipoPDF;

    /** Propiedad tipo identificacion predio. */
    /* Corresponde al tipo de identificacion por el
     * 								cual podemos buscar un predio. */
    private co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio tipoIdentificacionPredio;

    /** Propiedad num identificacion predio. */
    /* Al seleccionar un tipo de Identificación
     * 								predio,
     * 								se espera en este campo el número correspondiente; para
     * 								el caso
     * 								de
     * 								Matricula, el sistema toma los tres primeros caracteres
     * 								como
     * 								Código Circulo Registral y los siguientes como el Número de
     * 								Matrícula Inmobiliaria. */
    private java.lang.String numIdentificacionPredio;

    /** Propiedad convenio. */
    /* Nombre del convenio que tiene la SNR con
     * 								Entidades Externas que necesiten este servicio. */
    private java.lang.String convenio;

    /**
     * Instancia un nuevo objeto tipo entrada certificado tradicion PDF.
     */
    public TipoEntradaCertificadoTradicionPDF() {
    }

    /**
     * Instancia un nuevo objeto tipo entrada certificado tradicion PDF.
     *
     * @param tipoPDF de tipo PDF
     * @param tipoIdentificacionPredio de tipo identificacion predio
     * @param numIdentificacionPredio de num identificacion predio
     * @param convenio de convenio
     */
    public TipoEntradaCertificadoTradicionPDF(
           co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDFTipoPDF tipoPDF,
           co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio tipoIdentificacionPredio,
           java.lang.String numIdentificacionPredio,
           java.lang.String convenio) {
           this.tipoPDF = tipoPDF;
           this.tipoIdentificacionPredio = tipoIdentificacionPredio;
           this.numIdentificacionPredio = numIdentificacionPredio;
           this.convenio = convenio;
    }


    /**
     * Gets the tipoPDF value for this TipoEntradaCertificadoTradicionPDF.
     * 
     * @return tipoPDF   * Corresponde al tipo de PDF que se va a obtener
     */
    public co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDFTipoPDF getTipoPDF() {
        return tipoPDF;
    }


    /**
     * Sets the tipoPDF value for this TipoEntradaCertificadoTradicionPDF.
     * 
     * @param tipoPDF   * Corresponde al tipo de PDF que se va a obtener
     */
    public void setTipoPDF(co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDFTipoPDF tipoPDF) {
        this.tipoPDF = tipoPDF;
    }


    /**
     * Gets the tipoIdentificacionPredio value for this TipoEntradaCertificadoTradicionPDF.
     * 
     * @return tipoIdentificacionPredio   * Corresponde al tipo de identificacion por el
     * 								cual podemos buscar un predio.
     */
    public co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio getTipoIdentificacionPredio() {
        return tipoIdentificacionPredio;
    }


    /**
     * Sets the tipoIdentificacionPredio value for this TipoEntradaCertificadoTradicionPDF.
     * 
     * @param tipoIdentificacionPredio   * Corresponde al tipo de identificacion por el
     * 								cual podemos buscar un predio.
     */
    public void setTipoIdentificacionPredio(co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDFTipoIdentificacionPredio tipoIdentificacionPredio) {
        this.tipoIdentificacionPredio = tipoIdentificacionPredio;
    }


    /**
     * Gets the numIdentificacionPredio value for this TipoEntradaCertificadoTradicionPDF.
     * 
     * @return numIdentificacionPredio   * Al seleccionar un tipo de Identificación
     * 								predio,
     * 								se espera en este campo el número correspondiente; para
     * 								el caso
     * 								de
     * 								Matricula, el sistema toma los tres primeros caracteres
     * 								como
     * 								Código Circulo Registral y los siguientes como el Número de
     * 								Matrícula Inmobiliaria.
     */
    public java.lang.String getNumIdentificacionPredio() {
        return numIdentificacionPredio;
    }


    /**
     * Sets the numIdentificacionPredio value for this TipoEntradaCertificadoTradicionPDF.
     * 
     * @param numIdentificacionPredio   * Al seleccionar un tipo de Identificación
     * 								predio,
     * 								se espera en este campo el número correspondiente; para
     * 								el caso
     * 								de
     * 								Matricula, el sistema toma los tres primeros caracteres
     * 								como
     * 								Código Circulo Registral y los siguientes como el Número de
     * 								Matrícula Inmobiliaria.
     */
    public void setNumIdentificacionPredio(java.lang.String numIdentificacionPredio) {
        this.numIdentificacionPredio = numIdentificacionPredio;
    }


    /**
     * Gets the convenio value for this TipoEntradaCertificadoTradicionPDF.
     * 
     * @return convenio   * Nombre del convenio que tiene la SNR con
     * 								Entidades Externas que necesiten este servicio.
     */
    public java.lang.String getConvenio() {
        return convenio;
    }


    /**
     * Sets the convenio value for this TipoEntradaCertificadoTradicionPDF.
     * 
     * @param convenio   * Nombre del convenio que tiene la SNR con
     * 								Entidades Externas que necesiten este servicio.
     */
    public void setConvenio(java.lang.String convenio) {
        this.convenio = convenio;
    }

    /** Propiedad equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /** {@inheritdoc} */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoEntradaCertificadoTradicionPDF)) return false;
        TipoEntradaCertificadoTradicionPDF other = (TipoEntradaCertificadoTradicionPDF) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoPDF==null && other.getTipoPDF()==null) || 
             (this.tipoPDF!=null &&
              this.tipoPDF.equals(other.getTipoPDF()))) &&
            ((this.tipoIdentificacionPredio==null && other.getTipoIdentificacionPredio()==null) || 
             (this.tipoIdentificacionPredio!=null &&
              this.tipoIdentificacionPredio.equals(other.getTipoIdentificacionPredio()))) &&
            ((this.numIdentificacionPredio==null && other.getNumIdentificacionPredio()==null) || 
             (this.numIdentificacionPredio!=null &&
              this.numIdentificacionPredio.equals(other.getNumIdentificacionPredio()))) &&
            ((this.convenio==null && other.getConvenio()==null) || 
             (this.convenio!=null &&
              this.convenio.equals(other.getConvenio())));
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
        if (getTipoPDF() != null) {
            _hashCode += getTipoPDF().hashCode();
        }
        if (getTipoIdentificacionPredio() != null) {
            _hashCode += getTipoIdentificacionPredio().hashCode();
        }
        if (getNumIdentificacionPredio() != null) {
            _hashCode += getNumIdentificacionPredio().hashCode();
        }
        if (getConvenio() != null) {
            _hashCode += getConvenio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** Propiedad type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoEntradaCertificadoTradicionPDF.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/certificadotradicionpdf/obtenerPDF/v1", "tipoEntradaCertificadoTradicionPDF"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPDF");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/certificadotradicionpdf/obtenerPDF/v1", "tipoPDF"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/certificadotradicionpdf/obtenerPDF/v1", ">tipoEntradaCertificadoTradicionPDF>tipoPDF"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoIdentificacionPredio");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/certificadotradicionpdf/obtenerPDF/v1", "tipoIdentificacionPredio"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/certificadotradicionpdf/obtenerPDF/v1", ">tipoEntradaCertificadoTradicionPDF>tipoIdentificacionPredio"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numIdentificacionPredio");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/certificadotradicionpdf/obtenerPDF/v1", "numIdentificacionPredio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("convenio");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/certificadotradicionpdf/obtenerPDF/v1", "convenio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
