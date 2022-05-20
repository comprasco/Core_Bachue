/**
 * TipoEntradaConsultarDocumentoAlerta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarDocumentoAlerta.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaConsultarDocumentoAlerta  implements java.io.Serializable {
    
    /** Propiedad oficina origen. */
    private java.lang.String oficinaOrigen;

    /** Propiedad cod tipo documento publico. */
    private java.lang.String codTipoDocumentoPublico;

    /** Propiedad fecha documento. */
    private java.util.Calendar fechaDocumento;

    /** Propiedad doc numero. */
    private java.lang.String docNumero;

    /**
     * Instancia un nuevo objeto tipo entrada consultar documento alerta.
     */
    public TipoEntradaConsultarDocumentoAlerta() {
    }

    /**
     * Instancia un nuevo objeto tipo entrada consultar documento alerta.
     *
     * @param oficinaOrigen de oficina origen
     * @param codTipoDocumentoPublico de cod tipo documento publico
     * @param fechaDocumento de fecha documento
     * @param docNumero de doc numero
     */
    public TipoEntradaConsultarDocumentoAlerta(
           java.lang.String oficinaOrigen,
           java.lang.String codTipoDocumentoPublico,
           java.util.Calendar fechaDocumento,
           java.lang.String docNumero) {
           this.oficinaOrigen = oficinaOrigen;
           this.codTipoDocumentoPublico = codTipoDocumentoPublico;
           this.fechaDocumento = fechaDocumento;
           this.docNumero = docNumero;
    }


    /**
     * Gets the oficinaOrigen value for this TipoEntradaConsultarDocumentoAlerta.
     * 
     * @return oficinaOrigen
     */
    public java.lang.String getOficinaOrigen() {
        return oficinaOrigen;
    }


    /**
     * Sets the oficinaOrigen value for this TipoEntradaConsultarDocumentoAlerta.
     *
     * @param oficinaOrigen de oficina origen
     */
    public void setOficinaOrigen(java.lang.String oficinaOrigen) {
        this.oficinaOrigen = oficinaOrigen;
    }


    /**
     * Gets the codTipoDocumentoPublico value for this TipoEntradaConsultarDocumentoAlerta.
     * 
     * @return codTipoDocumentoPublico
     */
    public java.lang.String getCodTipoDocumentoPublico() {
        return codTipoDocumentoPublico;
    }


    /**
     * Sets the codTipoDocumentoPublico value for this TipoEntradaConsultarDocumentoAlerta.
     *
     * @param codTipoDocumentoPublico de cod tipo documento publico
     */
    public void setCodTipoDocumentoPublico(java.lang.String codTipoDocumentoPublico) {
        this.codTipoDocumentoPublico = codTipoDocumentoPublico;
    }


    /**
     * Gets the fechaDocumento value for this TipoEntradaConsultarDocumentoAlerta.
     * 
     * @return fechaDocumento
     */
    public java.util.Calendar getFechaDocumento() {
        return fechaDocumento;
    }


    /**
     * Sets the fechaDocumento value for this TipoEntradaConsultarDocumentoAlerta.
     *
     * @param fechaDocumento de fecha documento
     */
    public void setFechaDocumento(java.util.Calendar fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }


    /**
     * Gets the docNumero value for this TipoEntradaConsultarDocumentoAlerta.
     * 
     * @return docNumero
     */
    public java.lang.String getDocNumero() {
        return docNumero;
    }


    /**
     * Sets the docNumero value for this TipoEntradaConsultarDocumentoAlerta.
     *
     * @param docNumero de doc numero
     */
    public void setDocNumero(java.lang.String docNumero) {
        this.docNumero = docNumero;
    }

    /** Propiedad equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /** {@inheritdoc} */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoEntradaConsultarDocumentoAlerta)) return false;
        TipoEntradaConsultarDocumentoAlerta other = (TipoEntradaConsultarDocumentoAlerta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.oficinaOrigen==null && other.getOficinaOrigen()==null) || 
             (this.oficinaOrigen!=null &&
              this.oficinaOrigen.equals(other.getOficinaOrigen()))) &&
            ((this.codTipoDocumentoPublico==null && other.getCodTipoDocumentoPublico()==null) || 
             (this.codTipoDocumentoPublico!=null &&
              this.codTipoDocumentoPublico.equals(other.getCodTipoDocumentoPublico()))) &&
            ((this.fechaDocumento==null && other.getFechaDocumento()==null) || 
             (this.fechaDocumento!=null &&
              this.fechaDocumento.equals(other.getFechaDocumento()))) &&
            ((this.docNumero==null && other.getDocNumero()==null) || 
             (this.docNumero!=null &&
              this.docNumero.equals(other.getDocNumero())));
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
        if (getOficinaOrigen() != null) {
            _hashCode += getOficinaOrigen().hashCode();
        }
        if (getCodTipoDocumentoPublico() != null) {
            _hashCode += getCodTipoDocumentoPublico().hashCode();
        }
        if (getFechaDocumento() != null) {
            _hashCode += getFechaDocumento().hashCode();
        }
        if (getDocNumero() != null) {
            _hashCode += getDocNumero().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** Propiedad type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoEntradaConsultarDocumentoAlerta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDocumentoAlerta/v1", "tipoEntradaConsultarDocumentoAlerta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oficinaOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDocumentoAlerta/v1", "oficinaOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codTipoDocumentoPublico");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDocumentoAlerta/v1", "codTipoDocumentoPublico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDocumentoAlerta/v1", "fechaDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docNumero");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDocumentoAlerta/v1", "docNumero"));
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
