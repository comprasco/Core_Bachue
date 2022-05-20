/**
 * TipoElementoNir.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1;


/**
 * Clase que contiene todos las propiedades TipoElementoNir.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/09/2019
 */
public class TipoElementoNir implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1668518684085559472L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoElementoNir.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tipoElementoNir"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("turno");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "turno"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("orip");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "orip"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("decisionCalificacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "decisionCalificacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("faseActualTurno");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "faseActualTurno"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("procesoTurno");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "procesoTurno"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("documentos");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "documentos"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tipoDocumento"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "documento"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matriculas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "matriculas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tipoMatricula"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "matricula"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("trazabilidades");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "trazabilidades"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tipoTrazabilidad"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "trazabilidad"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad decision calificacion. */
	/* Decisión del calificador sobre el tramite */
	private java.lang.String decisionCalificacion;

	/** Propiedad fase actual turno. */
	/* Fase actual en la que se encuentra el tramite */
	private java.lang.String faseActualTurno;

	/** Propiedad orip. */
	/* Nombre de la ORIP asignada al turno */
	private java.lang.String orip;

	/** Propiedad proceso turno. */
	/* Proceso del turno */
	private java.lang.String procesoTurno;

	/** Propiedad turno. */
	/* Turno del tramite */
	private java.lang.String turno;

	/** Propiedad documentos. */
	/* Listado de los criterios para el servicio a
	 *                                 liquidar */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoDocumento[] documentos;

	/** Propiedad matriculas. */
	/* matriculas relacionadas */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoMatricula[] matriculas;

	/** Propiedad trazabilidades. */
	/* Lista de cada item de trazabilidad */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoTrazabilidad[] trazabilidades;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo elemento nir.
	 */
	public TipoElementoNir()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo elemento nir.
	 *
	 * @param turno correspondiente al valor de turno
	 * @param orip correspondiente al valor de orip
	 * @param decisionCalificacion correspondiente al valor de decision calificacion
	 * @param faseActualTurno correspondiente al valor de fase actual turno
	 * @param procesoTurno correspondiente al valor de proceso turno
	 * @param documentos correspondiente al valor de documentos
	 * @param matriculas correspondiente al valor de matriculas
	 * @param trazabilidades correspondiente al valor de trazabilidades
	 */
	public TipoElementoNir(
	    java.lang.String turno, java.lang.String orip, java.lang.String decisionCalificacion,
	    java.lang.String faseActualTurno, java.lang.String procesoTurno,
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoDocumento[] documentos,
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoMatricula[] matriculas,
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoTrazabilidad[] trazabilidades
	)
	{
		this.turno                    = turno;
		this.orip                     = orip;
		this.decisionCalificacion     = decisionCalificacion;
		this.faseActualTurno          = faseActualTurno;
		this.procesoTurno             = procesoTurno;
		this.documentos               = documentos;
		this.matriculas               = matriculas;
		this.trazabilidades           = trazabilidades;
	}

	/**
	 * Sets the decisionCalificacion value for this TipoElementoNir.
	 *
	 * @param decisionCalificacion   * Decisión del calificador sobre el tramite
	 */
	public void setDecisionCalificacion(java.lang.String decisionCalificacion)
	{
		this.decisionCalificacion = decisionCalificacion;
	}

	/**
	 * Gets the decisionCalificacion value for this TipoElementoNir.
	 *
	 * @return decisionCalificacion   * Decisión del calificador sobre el tramite
	 */
	public java.lang.String getDecisionCalificacion()
	{
		return decisionCalificacion;
	}

	/**
	 * Get Custom Deserializer.
	 *
	 * @param mechType correspondiente al valor de mech type
	 * @param _javaType correspondiente al valor de java type
	 * @param _xmlType correspondiente al valor de xml type
	 * @return el valor de deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Sets the documentos value for this TipoElementoNir.
	 *
	 * @param documentos   * Listado de los criterios para el servicio a
	     *                                 liquidar
	 */
	public void setDocumentos(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoDocumento[] documentos
	)
	{
		this.documentos = documentos;
	}

	/**
	 * Gets the documentos value for this TipoElementoNir.
	 *
	 * @return documentos   * Listado de los criterios para el servicio a
	     *                                 liquidar
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoDocumento[] getDocumentos()
	{
		return documentos;
	}

	/**
	 * Sets the faseActualTurno value for this TipoElementoNir.
	 *
	 * @param faseActualTurno   * Fase actual en la que se encuentra el tramite
	 */
	public void setFaseActualTurno(java.lang.String faseActualTurno)
	{
		this.faseActualTurno = faseActualTurno;
	}

	/**
	 * Gets the faseActualTurno value for this TipoElementoNir.
	 *
	 * @return faseActualTurno   * Fase actual en la que se encuentra el tramite
	 */
	public java.lang.String getFaseActualTurno()
	{
		return faseActualTurno;
	}

	/**
	 * Sets the matriculas value for this TipoElementoNir.
	 *
	 * @param matriculas   * matriculas relacionadas
	 */
	public void setMatriculas(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoMatricula[] matriculas
	)
	{
		this.matriculas = matriculas;
	}

	/**
	 * Gets the matriculas value for this TipoElementoNir.
	 *
	 * @return matriculas   * matriculas relacionadas
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoMatricula[] getMatriculas()
	{
		return matriculas;
	}

	/**
	 * Sets the orip value for this TipoElementoNir.
	 *
	 * @param orip   * Nombre de la ORIP asignada al turno
	 */
	public void setOrip(java.lang.String orip)
	{
		this.orip = orip;
	}

	/**
	 * Gets the orip value for this TipoElementoNir.
	 *
	 * @return orip   * Nombre de la ORIP asignada al turno
	 */
	public java.lang.String getOrip()
	{
		return orip;
	}

	/**
	 * Sets the procesoTurno value for this TipoElementoNir.
	 *
	 * @param procesoTurno   * Proceso del turno
	 */
	public void setProcesoTurno(java.lang.String procesoTurno)
	{
		this.procesoTurno = procesoTurno;
	}

	/**
	 * Gets the procesoTurno value for this TipoElementoNir.
	 *
	 * @return procesoTurno   * Proceso del turno
	 */
	public java.lang.String getProcesoTurno()
	{
		return procesoTurno;
	}

	/**
	 * Get Custom Serializer.
	 *
	 * @param mechType correspondiente al valor de mech type
	 * @param _javaType correspondiente al valor de java type
	 * @param _xmlType correspondiente al valor de xml type
	 * @return el valor de serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Sets the trazabilidades value for this TipoElementoNir.
	 *
	 * @param trazabilidades   * Lista de cada item de trazabilidad
	 */
	public void setTrazabilidades(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoTrazabilidad[] trazabilidades
	)
	{
		this.trazabilidades = trazabilidades;
	}

	/**
	 * Gets the trazabilidades value for this TipoElementoNir.
	 *
	 * @return trazabilidades   * Lista de cada item de trazabilidad
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoTrazabilidad[] getTrazabilidades()
	{
		return trazabilidades;
	}

	/**
	 * Sets the turno value for this TipoElementoNir.
	 *
	 * @param turno   * Turno del tramite
	 */
	public void setTurno(java.lang.String turno)
	{
		this.turno = turno;
	}

	/**
	 * Gets the turno value for this TipoElementoNir.
	 *
	 * @return turno   * Turno del tramite
	 */
	public java.lang.String getTurno()
	{
		return turno;
	}

	/**
	 * Return type metadata object.
	 *
	 * @return el valor de type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoElementoNir))
			return false;

		TipoElementoNir other = (TipoElementoNir)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.turno == null) && (other.getTurno() == null))
				|| ((this.turno != null) && this.turno.equals(other.getTurno())))
				&& (((this.orip == null) && (other.getOrip() == null))
				|| ((this.orip != null) && this.orip.equals(other.getOrip())))
				&& (((this.decisionCalificacion == null) && (other.getDecisionCalificacion() == null))
				|| ((this.decisionCalificacion != null)
				&& this.decisionCalificacion.equals(other.getDecisionCalificacion())))
				&& (((this.faseActualTurno == null) && (other.getFaseActualTurno() == null))
				|| ((this.faseActualTurno != null) && this.faseActualTurno.equals(other.getFaseActualTurno())))
				&& (((this.procesoTurno == null) && (other.getProcesoTurno() == null))
				|| ((this.procesoTurno != null) && this.procesoTurno.equals(other.getProcesoTurno())))
				&& (((this.documentos == null) && (other.getDocumentos() == null))
				|| ((this.documentos != null) && java.util.Arrays.equals(this.documentos, other.getDocumentos())))
				&& (((this.matriculas == null) && (other.getMatriculas() == null))
				|| ((this.matriculas != null) && java.util.Arrays.equals(this.matriculas, other.getMatriculas())))
				&& (((this.trazabilidades == null) && (other.getTrazabilidades() == null))
				|| ((this.trazabilidades != null)
				&& java.util.Arrays.equals(this.trazabilidades, other.getTrazabilidades())));
		__equalsCalc     = null;

		return _equals;
	}

	/** {@inheritdoc} */
	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getTurno() != null)
			_hashCode += getTurno().hashCode();

		if(getOrip() != null)
			_hashCode += getOrip().hashCode();

		if(getDecisionCalificacion() != null)
			_hashCode += getDecisionCalificacion().hashCode();

		if(getFaseActualTurno() != null)
			_hashCode += getFaseActualTurno().hashCode();

		if(getProcesoTurno() != null)
			_hashCode += getProcesoTurno().hashCode();

		if(getDocumentos() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getDocumentos()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getDocumentos(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getMatriculas() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getMatriculas()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getMatriculas(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getTrazabilidades() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getTrazabilidades()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getTrazabilidades(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
