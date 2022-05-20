/**
 * TipoSalidaConsultaMigracionMatriculas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1;



/**
 * El esquema define los
 * 						datos de salida para saber con cada una de las matrículas
 * 						recibidas,
 * 						si se busca en Nodo Central o en los
 * 						Servicios de Bachué.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaConsultaMigracionMatriculas  implements java.io.Serializable {
    
    /** Propiedad datos matriculas migracion. */
    private co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoDatosMatriculasMigracion[] datosMatriculasMigracion;

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
     * Instancia un nuevo objeto tipo salida consulta migracion matriculas.
     */
    public TipoSalidaConsultaMigracionMatriculas() {
    }

    /**
     * Instancia un nuevo objeto tipo salida consulta migracion matriculas.
     *
     * @param datosMatriculasMigracion de datos matriculas migracion
     * @param codMensaje de cod mensaje
     * @param descripcionMensaje de descripcion mensaje
     */
    public TipoSalidaConsultaMigracionMatriculas(
           co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoDatosMatriculasMigracion[] datosMatriculasMigracion,
           java.math.BigInteger codMensaje,
           java.lang.String descripcionMensaje) {
           this.datosMatriculasMigracion = datosMatriculasMigracion;
           this.codMensaje = codMensaje;
           this.descripcionMensaje = descripcionMensaje;
    }


    /**
     * Gets the datosMatriculasMigracion value for this TipoSalidaConsultaMigracionMatriculas.
     * 
     * @return datosMatriculasMigracion
     */
    public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoDatosMatriculasMigracion[] getDatosMatriculasMigracion() {
        return datosMatriculasMigracion;
    }


    /**
     * Sets the datosMatriculasMigracion value for this TipoSalidaConsultaMigracionMatriculas.
     *
     * @param datosMatriculasMigracion de datos matriculas migracion
     */
    public void setDatosMatriculasMigracion(co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoDatosMatriculasMigracion[] datosMatriculasMigracion) {
        this.datosMatriculasMigracion = datosMatriculasMigracion;
    }

    /**
     * Retorna Objeto o variable de valor datos matriculas migracion.
     *
     * @param i de i
     * @return el valor de datos matriculas migracion
     */
    public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoDatosMatriculasMigracion getDatosMatriculasMigracion(int i) {
        return this.datosMatriculasMigracion[i];
    }

    /**
     * Cambia el valor de datos matriculas migracion.
     *
     * @param i de i
     * @param _value de value
     */
    public void setDatosMatriculasMigracion(int i, co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoDatosMatriculasMigracion _value) {
        this.datosMatriculasMigracion[i] = _value;
    }


    /**
     * Gets the codMensaje value for this TipoSalidaConsultaMigracionMatriculas.
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
     * Sets the codMensaje value for this TipoSalidaConsultaMigracionMatriculas.
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
     * Gets the descripcionMensaje value for this TipoSalidaConsultaMigracionMatriculas.
     * 
     * @return descripcionMensaje   * En caso de que se presente un error en la
     * 								ejecución del servicio se envía un texto explicativo
     */
    public java.lang.String getDescripcionMensaje() {
        return descripcionMensaje;
    }


    /**
     * Sets the descripcionMensaje value for this TipoSalidaConsultaMigracionMatriculas.
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
        if (!(obj instanceof TipoSalidaConsultaMigracionMatriculas)) return false;
        TipoSalidaConsultaMigracionMatriculas other = (TipoSalidaConsultaMigracionMatriculas) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datosMatriculasMigracion==null && other.getDatosMatriculasMigracion()==null) || 
             (this.datosMatriculasMigracion!=null &&
              java.util.Arrays.equals(this.datosMatriculasMigracion, other.getDatosMatriculasMigracion()))) &&
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
        if (getDatosMatriculasMigracion() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDatosMatriculasMigracion());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDatosMatriculasMigracion(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        new org.apache.axis.description.TypeDesc(TipoSalidaConsultaMigracionMatriculas.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1", "tipoSalidaConsultaMigracionMatriculas"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosMatriculasMigracion");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1", "datosMatriculasMigracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1", "tipoDatosMatriculasMigracion"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codMensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1", "codMensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionMensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1", "descripcionMensaje"));
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
