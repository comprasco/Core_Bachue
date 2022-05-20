/**
 * TipoSalidaConsultaMigracionPredio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1;



/**
 * El esquema define los
 * 						datos de salida para la operacion Consulta Migracion Predio.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaConsultaMigracionPredio  implements java.io.Serializable {
    
    /** Propiedad informacion migrada. */
    /* True cuando la información del Predio ha sido
     * 								migrada,
     * 								false si no está migrada. */
    private java.lang.Boolean informacionMigrada;

    /** Propiedad cod mensaje. */
    /* 200 cuando no hay error, 4* por errores de
     * 								validación de campos de entrada o validación de negocio y
     * 500
     * 								cuando existen errores de sistema */
    private java.math.BigInteger codMensaje;

    /** Propiedad descripcion mensaje. */
    /* En caso de que se presente un error en la
     * 								ejecución del servicio se envía un texto explicativo */
    private java.lang.String descripcionMensaje;

    /**
     * Instancia un nuevo objeto tipo salida consulta migracion predio.
     */
    public TipoSalidaConsultaMigracionPredio() {
    }

    /**
     * Instancia un nuevo objeto tipo salida consulta migracion predio.
     *
     * @param informacionMigrada de informacion migrada
     * @param codMensaje de cod mensaje
     * @param descripcionMensaje de descripcion mensaje
     */
    public TipoSalidaConsultaMigracionPredio(
           java.lang.Boolean informacionMigrada,
           java.math.BigInteger codMensaje,
           java.lang.String descripcionMensaje) {
           this.informacionMigrada = informacionMigrada;
           this.codMensaje = codMensaje;
           this.descripcionMensaje = descripcionMensaje;
    }


    /**
     * Gets the informacionMigrada value for this TipoSalidaConsultaMigracionPredio.
     * 
     * @return informacionMigrada   * True cuando la información del Predio ha sido
     * 								migrada,
     * 								false si no está migrada.
     */
    public java.lang.Boolean getInformacionMigrada() {
        return informacionMigrada;
    }


    /**
     * Sets the informacionMigrada value for this TipoSalidaConsultaMigracionPredio.
     * 
     * @param informacionMigrada   * True cuando la información del Predio ha sido
     * 								migrada,
     * 								false si no está migrada.
     */
    public void setInformacionMigrada(java.lang.Boolean informacionMigrada) {
        this.informacionMigrada = informacionMigrada;
    }


    /**
     * Gets the codMensaje value for this TipoSalidaConsultaMigracionPredio.
     * 
     * @return codMensaje   * 200 cuando no hay error, 4* por errores de
     * 								validación de campos de entrada o validación de negocio y
     * 500
     * 								cuando existen errores de sistema
     */
    public java.math.BigInteger getCodMensaje() {
        return codMensaje;
    }


    /**
     * Sets the codMensaje value for this TipoSalidaConsultaMigracionPredio.
     * 
     * @param codMensaje   * 200 cuando no hay error, 4* por errores de
     * 								validación de campos de entrada o validación de negocio y
     * 500
     * 								cuando existen errores de sistema
     */
    public void setCodMensaje(java.math.BigInteger codMensaje) {
        this.codMensaje = codMensaje;
    }


    /**
     * Gets the descripcionMensaje value for this TipoSalidaConsultaMigracionPredio.
     * 
     * @return descripcionMensaje   * En caso de que se presente un error en la
     * 								ejecución del servicio se envía un texto explicativo
     */
    public java.lang.String getDescripcionMensaje() {
        return descripcionMensaje;
    }


    /**
     * Sets the descripcionMensaje value for this TipoSalidaConsultaMigracionPredio.
     * 
     * @param descripcionMensaje   * En caso de que se presente un error en la
     * 								ejecución del servicio se envía un texto explicativo
     */
    public void setDescripcionMensaje(java.lang.String descripcionMensaje) {
        this.descripcionMensaje = descripcionMensaje;
    }

    /** Propiedad equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /** {@inheritdoc} */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoSalidaConsultaMigracionPredio)) return false;
        TipoSalidaConsultaMigracionPredio other = (TipoSalidaConsultaMigracionPredio) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.informacionMigrada==null && other.getInformacionMigrada()==null) || 
             (this.informacionMigrada!=null &&
              this.informacionMigrada.equals(other.getInformacionMigrada()))) &&
            ((this.codMensaje==null && other.getCodMensaje()==null) || 
             (this.codMensaje!=null &&
              this.codMensaje.equals(other.getCodMensaje()))) &&
            ((this.descripcionMensaje==null && other.getDescripcionMensaje()==null) || 
             (this.descripcionMensaje!=null &&
              this.descripcionMensaje.equals(other.getDescripcionMensaje())));
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
        if (getInformacionMigrada() != null) {
            _hashCode += getInformacionMigrada().hashCode();
        }
        if (getCodMensaje() != null) {
            _hashCode += getCodMensaje().hashCode();
        }
        if (getDescripcionMensaje() != null) {
            _hashCode += getDescripcionMensaje().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** Propiedad type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoSalidaConsultaMigracionPredio.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1", "tipoSalidaConsultaMigracionPredio"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("informacionMigrada");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1", "informacionMigrada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codMensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1", "codMensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionMensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1", "descripcionMensaje"));
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
