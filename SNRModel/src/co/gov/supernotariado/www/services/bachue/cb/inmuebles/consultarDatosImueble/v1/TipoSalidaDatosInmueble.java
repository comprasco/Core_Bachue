/**
 * TipoSalidaDatosInmueble.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1;



/**
 * El esquema define los
 *                         datos de salida para la operacion consultarDatosInmueble.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 13/03/2020
 */
public class TipoSalidaDatosInmueble implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2193295088384187788L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaDatosInmueble.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "tipoSalidaDatosInmueble"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numMatriculaInmobiliaria");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "numMatriculaInmobiliaria"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "codCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "nomCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("NUPRE");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1", "NUPRE"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estadoNUPRE");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1", "estadoNUPRE"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numPredial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1", "numPredial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numPredialAnterior");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "numPredialAnterior"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("direccionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "direccionPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "codDepartamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "nomDepartamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMunicipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "codMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomMunicipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "nomMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomVereda");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1", "nomVereda"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomBarrio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1", "nomBarrio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaAperturaFolio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "fechaAperturaFolio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaInstrumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "fechaInstrumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("radicacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1", "radicacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomTipoDocumentoPublico");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "nomTipoDocumentoPublico"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("NIR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1", "NIR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("areaInmueble");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "areaInmueble"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("areaConstruida");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "areaConstruida"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("areaPrivada");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1", "areaPrivada"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estadoFolio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1", "estadoFolio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1", "tipoPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("linderos");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1", "linderos"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("complementaciones");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "complementaciones"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1", "codMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad nir. */
	/* Número de Identificación Registral de la
	 *                                 apertura */
	private java.lang.String NIR;

	/** Propiedad nupre. */
	/* Número Único Predial */
	private java.lang.String NUPRE;

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad area construida. */
	/* Area es el área construida del inmueble, es la
	 *                                 superficie
	 *                                 total del predio, incluye elementos adicionales como
	 *                                 ductos,
	 *                                 columnas y falseados. Se dará en Metros cuadrados. */
	private java.lang.Long areaConstruida;

	/** Propiedad area inmueble. */
	/* Area del Inmueble es la superficie que
	 *                                 generalmente se ve reflejada en el registro público de la
	 *                                 propiedad. Se dará en Metros cuadrados. */
	private java.lang.Long areaInmueble;

	/** Propiedad area privada. */
	/* Áea privada del inmueble: Se refiere a toda la
	 *                                 superficie que se puede pisar. Incluye todo el espacio interior
	 *                                 del predio. Se dará en Metros Cuadrados. */
	private java.lang.Long areaPrivada;

	/** Propiedad cod circulo registral. */
	/* corresponde a la ORIP donde se ha registrado
	 *                                 el
	 *                                 predio */
	private java.lang.String codCirculoRegistral;

	/** Propiedad cod departamento. */
	/* corresponde al código del departamento según
	 *                                 DANE donde se ha registrado el predio */
	private java.lang.String codDepartamento;

	/** Propiedad cod mensaje. */
	/* 200 cuando no hay error, 4* por errores de
	 *                                 validación de campos de entrada o validación de negocio y
	 * 500
	 *                                 cuando existen errores de sistema */
	private java.math.BigInteger codMensaje;

	/** Propiedad cod municipio. */
	/* corresponde al código del municipio según DANE
	 *                                 donde se ha registrado el predio */
	private java.lang.String codMunicipio;

	/** Propiedad complementaciones. */
	/* Corresponde a las complementaciones del
	 *                                 predio. */
	private java.lang.String complementaciones;

	/** Propiedad descripcion mensaje. */
	/* Corresponde a al mensaje de error para los
	 *                                 casos
	 *                                 en qeue la consulta no fue exitosa, vacío cuando la
	 *                                 consulta es
	 *                                 exitosa */
	private java.lang.String descripcionMensaje;

	/** Propiedad direccion predio. */
	/* Corresponde a la dirección del predio. */
	private java.lang.String direccionPredio;

	/** Propiedad estado folio. */
	/* Estado Temporalidad Folio, los valores que se
	 *                                 esperan son ACTIVO, CERRADO */
	private java.lang.String estadoFolio;

	/** Propiedad estado NUPRE. */
	/* se espera alguno de los siguientes estados:
	 *                                 Gestor,
	 *                                 Registro, Autoridad catastral y Temporal. */
	private java.lang.String estadoNUPRE;

	/** Propiedad fecha apertura folio. */
	/* Fecha de apertura */
	private java.util.Calendar fechaAperturaFolio;

	/** Propiedad fecha instrumento. */
	/* Fecha de apertura */
	private java.util.Calendar fechaInstrumento;

	/** Propiedad linderos. */
	/* Corresponde a los linderos del predio. */
	private java.lang.String linderos;

	/** Propiedad nom barrio. */
	/* corresponde al barrio donde se ubica el
	 *                                 predio. */
	private java.lang.String nomBarrio;

	/** Propiedad nom circulo registral. */
	/* Nombre de la ORIP al que corresponde el
	 *                                 predio. */
	private java.lang.String nomCirculoRegistral;

	/** Propiedad nom departamento. */
	/* corresponde al nombre del departamento donde
	 *                                 se
	 *                                 ubica el predio. */
	private java.lang.String nomDepartamento;

	/** Propiedad nom municipio. */
	/* corresponde al nombre del municipio donde se
	 *                                 ubica el predio */
	private java.lang.String nomMunicipio;

	/** Propiedad nom tipo documento publico. */
	/* Nombre del Tipo de documento público */
	private java.lang.String nomTipoDocumentoPublico;

	/** Propiedad nom vereda. */
	/* corresponde a la vereda donde se ubica el
	 *                                 predio. */
	private java.lang.String nomVereda;

	/** Propiedad num matricula inmobiliaria. */
	/* corresponde al número de matrícula que
	 *                                 identifica al predio */
	private java.lang.String numMatriculaInmobiliaria;

	/** Propiedad num predial. */
	/* Código Catastral Actual */
	private java.lang.String numPredial;

	/** Propiedad num predial anterior. */
	/* Código Catastral Anterior */
	private java.lang.String numPredialAnterior;

	/** Propiedad radicacion. */
	/* Numero de radicación de la apertura */
	private java.lang.String radicacion;

	/** Propiedad tipo predio. */
	/* No obligatorio, de cara al CTL lo dejan "SIN
	 *                                 INFORMACION" cuando no hay dato. Ejemplo: 50C-1248183 */
	private java.lang.String tipoPredio;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida datos inmueble.
	 */
	public TipoSalidaDatosInmueble()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida datos inmueble.
	 *
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 * @param codCirculoRegistral de cod circulo registral
	 * @param nomCirculoRegistral de nom circulo registral
	 * @param NUPRE de nupre
	 * @param estadoNUPRE de estado NUPRE
	 * @param numPredial de num predial
	 * @param numPredialAnterior de num predial anterior
	 * @param direccionPredio de direccion predio
	 * @param codDepartamento de cod departamento
	 * @param nomDepartamento de nom departamento
	 * @param codMunicipio de cod municipio
	 * @param nomMunicipio de nom municipio
	 * @param nomVereda de nom vereda
	 * @param nomBarrio de nom barrio
	 * @param fechaAperturaFolio de fecha apertura folio
	 * @param fechaInstrumento de fecha instrumento
	 * @param radicacion de radicacion
	 * @param nomTipoDocumentoPublico de nom tipo documento publico
	 * @param NIR de nir
	 * @param areaInmueble de area inmueble
	 * @param areaConstruida de area construida
	 * @param areaPrivada de area privada
	 * @param estadoFolio de estado folio
	 * @param tipoPredio de tipo predio
	 * @param linderos de linderos
	 * @param complementaciones de complementaciones
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaDatosInmueble(
	    java.lang.String numMatriculaInmobiliaria, java.lang.String codCirculoRegistral,
	    java.lang.String nomCirculoRegistral, java.lang.String NUPRE, java.lang.String estadoNUPRE,
	    java.lang.String numPredial, java.lang.String numPredialAnterior, java.lang.String direccionPredio,
	    java.lang.String codDepartamento, java.lang.String nomDepartamento, java.lang.String codMunicipio,
	    java.lang.String nomMunicipio, java.lang.String nomVereda, java.lang.String nomBarrio,
	    java.util.Calendar fechaAperturaFolio, java.util.Calendar fechaInstrumento, java.lang.String radicacion,
	    java.lang.String nomTipoDocumentoPublico, java.lang.String NIR, java.lang.Long areaInmueble,
	    java.lang.Long areaConstruida, java.lang.Long areaPrivada, java.lang.String estadoFolio,
	    java.lang.String tipoPredio, java.lang.String linderos, java.lang.String complementaciones,
	    java.math.BigInteger codMensaje, java.lang.String descripcionMensaje
	)
	{
		this.numMatriculaInmobiliaria     = numMatriculaInmobiliaria;
		this.codCirculoRegistral          = codCirculoRegistral;
		this.nomCirculoRegistral          = nomCirculoRegistral;
		this.NUPRE                        = NUPRE;
		this.estadoNUPRE                  = estadoNUPRE;
		this.numPredial                   = numPredial;
		this.numPredialAnterior           = numPredialAnterior;
		this.direccionPredio              = direccionPredio;
		this.codDepartamento              = codDepartamento;
		this.nomDepartamento              = nomDepartamento;
		this.codMunicipio                 = codMunicipio;
		this.nomMunicipio                 = nomMunicipio;
		this.nomVereda                    = nomVereda;
		this.nomBarrio                    = nomBarrio;
		this.fechaAperturaFolio           = fechaAperturaFolio;
		this.fechaInstrumento             = fechaInstrumento;
		this.radicacion                   = radicacion;
		this.nomTipoDocumentoPublico      = nomTipoDocumentoPublico;
		this.NIR                          = NIR;
		this.areaInmueble                 = areaInmueble;
		this.areaConstruida               = areaConstruida;
		this.areaPrivada                  = areaPrivada;
		this.estadoFolio                  = estadoFolio;
		this.tipoPredio                   = tipoPredio;
		this.linderos                     = linderos;
		this.complementaciones            = complementaciones;
		this.codMensaje                   = codMensaje;
		this.descripcionMensaje           = descripcionMensaje;
	}

	/**
	 * Gets the numMatriculaInmobiliaria value for this TipoSalidaDatosInmueble.
	 *
	 * @return numMatriculaInmobiliaria   * corresponde al número de matrícula que
	     *                                 identifica al predio
	 */
	public java.lang.String getNumMatriculaInmobiliaria()
	{
		return numMatriculaInmobiliaria;
	}

	/**
	 * Sets the numMatriculaInmobiliaria value for this TipoSalidaDatosInmueble.
	 *
	 * @param numMatriculaInmobiliaria   * corresponde al número de matrícula que
	     *                                 identifica al predio
	 */
	public void setNumMatriculaInmobiliaria(java.lang.String numMatriculaInmobiliaria)
	{
		this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoSalidaDatosInmueble.
	 *
	 * @return codCirculoRegistral   * corresponde a la ORIP donde se ha registrado
	     *                                 el
	     *                                 predio
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoSalidaDatosInmueble.
	 *
	 * @param codCirculoRegistral   * corresponde a la ORIP donde se ha registrado
	     *                                 el
	     *                                 predio
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the nomCirculoRegistral value for this TipoSalidaDatosInmueble.
	 *
	 * @return nomCirculoRegistral   * Nombre de la ORIP al que corresponde el
	     *                                 predio.
	 */
	public java.lang.String getNomCirculoRegistral()
	{
		return nomCirculoRegistral;
	}

	/**
	 * Sets the nomCirculoRegistral value for this TipoSalidaDatosInmueble.
	 *
	 * @param nomCirculoRegistral   * Nombre de la ORIP al que corresponde el
	     *                                 predio.
	 */
	public void setNomCirculoRegistral(java.lang.String nomCirculoRegistral)
	{
		this.nomCirculoRegistral = nomCirculoRegistral;
	}

	/**
	 * Gets the NUPRE value for this TipoSalidaDatosInmueble.
	 *
	 * @return NUPRE   * Número Único Predial
	 */
	public java.lang.String getNUPRE()
	{
		return NUPRE;
	}

	/**
	 * Sets the NUPRE value for this TipoSalidaDatosInmueble.
	 *
	 * @param NUPRE   * Número Único Predial
	 */
	public void setNUPRE(java.lang.String NUPRE)
	{
		this.NUPRE = NUPRE;
	}

	/**
	 * Gets the estadoNUPRE value for this TipoSalidaDatosInmueble.
	 *
	 * @return estadoNUPRE   * se espera alguno de los siguientes estados:
	     *                                 Gestor,
	     *                                 Registro, Autoridad catastral y Temporal.
	 */
	public java.lang.String getEstadoNUPRE()
	{
		return estadoNUPRE;
	}

	/**
	 * Sets the estadoNUPRE value for this TipoSalidaDatosInmueble.
	 *
	 * @param estadoNUPRE   * se espera alguno de los siguientes estados:
	     *                                 Gestor,
	     *                                 Registro, Autoridad catastral y Temporal.
	 */
	public void setEstadoNUPRE(java.lang.String estadoNUPRE)
	{
		this.estadoNUPRE = estadoNUPRE;
	}

	/**
	 * Gets the numPredial value for this TipoSalidaDatosInmueble.
	 *
	 * @return numPredial   * Código Catastral Actual
	 */
	public java.lang.String getNumPredial()
	{
		return numPredial;
	}

	/**
	 * Sets the numPredial value for this TipoSalidaDatosInmueble.
	 *
	 * @param numPredial   * Código Catastral Actual
	 */
	public void setNumPredial(java.lang.String numPredial)
	{
		this.numPredial = numPredial;
	}

	/**
	 * Gets the numPredialAnterior value for this TipoSalidaDatosInmueble.
	 *
	 * @return numPredialAnterior   * Código Catastral Anterior
	 */
	public java.lang.String getNumPredialAnterior()
	{
		return numPredialAnterior;
	}

	/**
	 * Sets the numPredialAnterior value for this TipoSalidaDatosInmueble.
	 *
	 * @param numPredialAnterior   * Código Catastral Anterior
	 */
	public void setNumPredialAnterior(java.lang.String numPredialAnterior)
	{
		this.numPredialAnterior = numPredialAnterior;
	}

	/**
	 * Gets the direccionPredio value for this TipoSalidaDatosInmueble.
	 *
	 * @return direccionPredio   * Corresponde a la dirección del predio.
	 */
	public java.lang.String getDireccionPredio()
	{
		return direccionPredio;
	}

	/**
	 * Sets the direccionPredio value for this TipoSalidaDatosInmueble.
	 *
	 * @param direccionPredio   * Corresponde a la dirección del predio.
	 */
	public void setDireccionPredio(java.lang.String direccionPredio)
	{
		this.direccionPredio = direccionPredio;
	}

	/**
	 * Gets the codDepartamento value for this TipoSalidaDatosInmueble.
	 *
	 * @return codDepartamento   * corresponde al código del departamento según
	     *                                 DANE donde se ha registrado el predio
	 */
	public java.lang.String getCodDepartamento()
	{
		return codDepartamento;
	}

	/**
	 * Sets the codDepartamento value for this TipoSalidaDatosInmueble.
	 *
	 * @param codDepartamento   * corresponde al código del departamento según
	     *                                 DANE donde se ha registrado el predio
	 */
	public void setCodDepartamento(java.lang.String codDepartamento)
	{
		this.codDepartamento = codDepartamento;
	}

	/**
	 * Gets the nomDepartamento value for this TipoSalidaDatosInmueble.
	 *
	 * @return nomDepartamento   * corresponde al nombre del departamento donde
	     *                                 se
	     *                                 ubica el predio.
	 */
	public java.lang.String getNomDepartamento()
	{
		return nomDepartamento;
	}

	/**
	 * Sets the nomDepartamento value for this TipoSalidaDatosInmueble.
	 *
	 * @param nomDepartamento   * corresponde al nombre del departamento donde
	     *                                 se
	     *                                 ubica el predio.
	 */
	public void setNomDepartamento(java.lang.String nomDepartamento)
	{
		this.nomDepartamento = nomDepartamento;
	}

	/**
	 * Gets the codMunicipio value for this TipoSalidaDatosInmueble.
	 *
	 * @return codMunicipio   * corresponde al código del municipio según DANE
	     *                                 donde se ha registrado el predio
	 */
	public java.lang.String getCodMunicipio()
	{
		return codMunicipio;
	}

	/**
	 * Sets the codMunicipio value for this TipoSalidaDatosInmueble.
	 *
	 * @param codMunicipio   * corresponde al código del municipio según DANE
	     *                                 donde se ha registrado el predio
	 */
	public void setCodMunicipio(java.lang.String codMunicipio)
	{
		this.codMunicipio = codMunicipio;
	}

	/**
	 * Gets the nomMunicipio value for this TipoSalidaDatosInmueble.
	 *
	 * @return nomMunicipio   * corresponde al nombre del municipio donde se
	     *                                 ubica el predio
	 */
	public java.lang.String getNomMunicipio()
	{
		return nomMunicipio;
	}

	/**
	 * Sets the nomMunicipio value for this TipoSalidaDatosInmueble.
	 *
	 * @param nomMunicipio   * corresponde al nombre del municipio donde se
	     *                                 ubica el predio
	 */
	public void setNomMunicipio(java.lang.String nomMunicipio)
	{
		this.nomMunicipio = nomMunicipio;
	}

	/**
	 * Gets the nomVereda value for this TipoSalidaDatosInmueble.
	 *
	 * @return nomVereda   * corresponde a la vereda donde se ubica el
	     *                                 predio.
	 */
	public java.lang.String getNomVereda()
	{
		return nomVereda;
	}

	/**
	 * Sets the nomVereda value for this TipoSalidaDatosInmueble.
	 *
	 * @param nomVereda   * corresponde a la vereda donde se ubica el
	     *                                 predio.
	 */
	public void setNomVereda(java.lang.String nomVereda)
	{
		this.nomVereda = nomVereda;
	}

	/**
	 * Gets the nomBarrio value for this TipoSalidaDatosInmueble.
	 *
	 * @return nomBarrio   * corresponde al barrio donde se ubica el
	     *                                 predio.
	 */
	public java.lang.String getNomBarrio()
	{
		return nomBarrio;
	}

	/**
	 * Sets the nomBarrio value for this TipoSalidaDatosInmueble.
	 *
	 * @param nomBarrio   * corresponde al barrio donde se ubica el
	     *                                 predio.
	 */
	public void setNomBarrio(java.lang.String nomBarrio)
	{
		this.nomBarrio = nomBarrio;
	}

	/**
	 * Gets the fechaAperturaFolio value for this TipoSalidaDatosInmueble.
	 *
	 * @return fechaAperturaFolio   * Fecha de apertura
	 */
	public java.util.Calendar getFechaAperturaFolio()
	{
		return fechaAperturaFolio;
	}

	/**
	 * Sets the fechaAperturaFolio value for this TipoSalidaDatosInmueble.
	 *
	 * @param fechaAperturaFolio   * Fecha de apertura
	 */
	public void setFechaAperturaFolio(java.util.Calendar fechaAperturaFolio)
	{
		this.fechaAperturaFolio = fechaAperturaFolio;
	}

	/**
	 * Gets the fechaInstrumento value for this TipoSalidaDatosInmueble.
	 *
	 * @return fechaInstrumento   * Fecha de apertura
	 */
	public java.util.Calendar getFechaInstrumento()
	{
		return fechaInstrumento;
	}

	/**
	 * Sets the fechaInstrumento value for this TipoSalidaDatosInmueble.
	 *
	 * @param fechaInstrumento   * Fecha de apertura
	 */
	public void setFechaInstrumento(java.util.Calendar fechaInstrumento)
	{
		this.fechaInstrumento = fechaInstrumento;
	}

	/**
	 * Gets the radicacion value for this TipoSalidaDatosInmueble.
	 *
	 * @return radicacion   * Numero de radicación de la apertura
	 */
	public java.lang.String getRadicacion()
	{
		return radicacion;
	}

	/**
	 * Sets the radicacion value for this TipoSalidaDatosInmueble.
	 *
	 * @param radicacion   * Numero de radicación de la apertura
	 */
	public void setRadicacion(java.lang.String radicacion)
	{
		this.radicacion = radicacion;
	}

	/**
	 * Gets the nomTipoDocumentoPublico value for this TipoSalidaDatosInmueble.
	 *
	 * @return nomTipoDocumentoPublico   * Nombre del Tipo de documento público
	 */
	public java.lang.String getNomTipoDocumentoPublico()
	{
		return nomTipoDocumentoPublico;
	}

	/**
	 * Sets the nomTipoDocumentoPublico value for this TipoSalidaDatosInmueble.
	 *
	 * @param nomTipoDocumentoPublico   * Nombre del Tipo de documento público
	 */
	public void setNomTipoDocumentoPublico(java.lang.String nomTipoDocumentoPublico)
	{
		this.nomTipoDocumentoPublico = nomTipoDocumentoPublico;
	}

	/**
	 * Gets the NIR value for this TipoSalidaDatosInmueble.
	 *
	 * @return NIR   * Número de Identificación Registral de la
	     *                                 apertura
	 */
	public java.lang.String getNIR()
	{
		return NIR;
	}

	/**
	 * Sets the NIR value for this TipoSalidaDatosInmueble.
	 *
	 * @param NIR   * Número de Identificación Registral de la
	     *                                 apertura
	 */
	public void setNIR(java.lang.String NIR)
	{
		this.NIR = NIR;
	}

	/**
	 * Gets the areaInmueble value for this TipoSalidaDatosInmueble.
	 *
	 * @return areaInmueble   * Area del Inmueble es la superficie que
	     *                                 generalmente se ve reflejada en el registro público de la
	     *                                 propiedad. Se dará en Metros cuadrados.
	 */
	public java.lang.Long getAreaInmueble()
	{
		return areaInmueble;
	}

	/**
	 * Sets the areaInmueble value for this TipoSalidaDatosInmueble.
	 *
	 * @param areaInmueble   * Area del Inmueble es la superficie que
	     *                                 generalmente se ve reflejada en el registro público de la
	     *                                 propiedad. Se dará en Metros cuadrados.
	 */
	public void setAreaInmueble(java.lang.Long areaInmueble)
	{
		this.areaInmueble = areaInmueble;
	}

	/**
	 * Gets the areaConstruida value for this TipoSalidaDatosInmueble.
	 *
	 * @return areaConstruida   * Area es el área construida del inmueble, es la
	     *                                 superficie
	     *                                 total del predio, incluye elementos adicionales como
	     *                                 ductos,
	     *                                 columnas y falseados. Se dará en Metros cuadrados.
	 */
	public java.lang.Long getAreaConstruida()
	{
		return areaConstruida;
	}

	/**
	 * Sets the areaConstruida value for this TipoSalidaDatosInmueble.
	 *
	 * @param areaConstruida   * Area es el área construida del inmueble, es la
	     *                                 superficie
	     *                                 total del predio, incluye elementos adicionales como
	     *                                 ductos,
	     *                                 columnas y falseados. Se dará en Metros cuadrados.
	 */
	public void setAreaConstruida(java.lang.Long areaConstruida)
	{
		this.areaConstruida = areaConstruida;
	}

	/**
	 * Gets the areaPrivada value for this TipoSalidaDatosInmueble.
	 *
	 * @return areaPrivada   * Áea privada del inmueble: Se refiere a toda la
	     *                                 superficie que se puede pisar. Incluye todo el espacio interior
	     *                                 del predio. Se dará en Metros Cuadrados.
	 */
	public java.lang.Long getAreaPrivada()
	{
		return areaPrivada;
	}

	/**
	 * Sets the areaPrivada value for this TipoSalidaDatosInmueble.
	 *
	 * @param areaPrivada   * Áea privada del inmueble: Se refiere a toda la
	     *                                 superficie que se puede pisar. Incluye todo el espacio interior
	     *                                 del predio. Se dará en Metros Cuadrados.
	 */
	public void setAreaPrivada(java.lang.Long areaPrivada)
	{
		this.areaPrivada = areaPrivada;
	}

	/**
	 * Gets the estadoFolio value for this TipoSalidaDatosInmueble.
	 *
	 * @return estadoFolio   * Estado Temporalidad Folio, los valores que se
	     *                                 esperan son ACTIVO, CERRADO
	 */
	public java.lang.String getEstadoFolio()
	{
		return estadoFolio;
	}

	/**
	 * Sets the estadoFolio value for this TipoSalidaDatosInmueble.
	 *
	 * @param estadoFolio   * Estado Temporalidad Folio, los valores que se
	     *                                 esperan son ACTIVO, CERRADO
	 */
	public void setEstadoFolio(java.lang.String estadoFolio)
	{
		this.estadoFolio = estadoFolio;
	}

	/**
	 * Gets the tipoPredio value for this TipoSalidaDatosInmueble.
	 *
	 * @return tipoPredio   * No obligatorio, de cara al CTL lo dejan "SIN
	     *                                 INFORMACION" cuando no hay dato. Ejemplo: 50C-1248183
	 */
	public java.lang.String getTipoPredio()
	{
		return tipoPredio;
	}

	/**
	 * Sets the tipoPredio value for this TipoSalidaDatosInmueble.
	 *
	 * @param tipoPredio   * No obligatorio, de cara al CTL lo dejan "SIN
	     *                                 INFORMACION" cuando no hay dato. Ejemplo: 50C-1248183
	 */
	public void setTipoPredio(java.lang.String tipoPredio)
	{
		this.tipoPredio = tipoPredio;
	}

	/**
	 * Gets the linderos value for this TipoSalidaDatosInmueble.
	 *
	 * @return linderos   * Corresponde a los linderos del predio.
	 */
	public java.lang.String getLinderos()
	{
		return linderos;
	}

	/**
	 * Sets the linderos value for this TipoSalidaDatosInmueble.
	 *
	 * @param linderos   * Corresponde a los linderos del predio.
	 */
	public void setLinderos(java.lang.String linderos)
	{
		this.linderos = linderos;
	}

	/**
	 * Gets the complementaciones value for this TipoSalidaDatosInmueble.
	 *
	 * @return complementaciones   * Corresponde a las complementaciones del
	     *                                 predio.
	 */
	public java.lang.String getComplementaciones()
	{
		return complementaciones;
	}

	/**
	 * Sets the complementaciones value for this TipoSalidaDatosInmueble.
	 *
	 * @param complementaciones   * Corresponde a las complementaciones del
	     *                                 predio.
	 */
	public void setComplementaciones(java.lang.String complementaciones)
	{
		this.complementaciones = complementaciones;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaDatosInmueble.
	 *
	 * @return codMensaje   * 200 cuando no hay error, 4* por errores de
	     *                                 validación de campos de entrada o validación de negocio y
	     * 500
	     *                                 cuando existen errores de sistema
	 */
	public java.math.BigInteger getCodMensaje()
	{
		return codMensaje;
	}

	/**
	 * Sets the codMensaje value for this TipoSalidaDatosInmueble.
	 *
	 * @param codMensaje   * 200 cuando no hay error, 4* por errores de
	     *                                 validación de campos de entrada o validación de negocio y
	     * 500
	     *                                 cuando existen errores de sistema
	 */
	public void setCodMensaje(java.math.BigInteger codMensaje)
	{
		this.codMensaje = codMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaDatosInmueble.
	 *
	 * @return descripcionMensaje   * Corresponde a al mensaje de error para los
	     *                                 casos
	     *                                 en qeue la consulta no fue exitosa, vacío cuando la
	     *                                 consulta es
	     *                                 exitosa
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaDatosInmueble.
	 *
	 * @param descripcionMensaje   * Corresponde a al mensaje de error para los
	     *                                 casos
	     *                                 en qeue la consulta no fue exitosa, vacío cuando la
	     *                                 consulta es
	     *                                 exitosa
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaDatosInmueble))
			return false;

		TipoSalidaDatosInmueble other = (TipoSalidaDatosInmueble)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numMatriculaInmobiliaria == null) && (other.getNumMatriculaInmobiliaria() == null))
				|| ((this.numMatriculaInmobiliaria != null)
				&& this.numMatriculaInmobiliaria.equals(other.getNumMatriculaInmobiliaria())))
				&& (((this.codCirculoRegistral == null) && (other.getCodCirculoRegistral() == null))
				|| ((this.codCirculoRegistral != null)
				&& this.codCirculoRegistral.equals(other.getCodCirculoRegistral())))
				&& (((this.nomCirculoRegistral == null) && (other.getNomCirculoRegistral() == null))
				|| ((this.nomCirculoRegistral != null)
				&& this.nomCirculoRegistral.equals(other.getNomCirculoRegistral())))
				&& (((this.NUPRE == null) && (other.getNUPRE() == null))
				|| ((this.NUPRE != null) && this.NUPRE.equals(other.getNUPRE())))
				&& (((this.estadoNUPRE == null) && (other.getEstadoNUPRE() == null))
				|| ((this.estadoNUPRE != null) && this.estadoNUPRE.equals(other.getEstadoNUPRE())))
				&& (((this.numPredial == null) && (other.getNumPredial() == null))
				|| ((this.numPredial != null) && this.numPredial.equals(other.getNumPredial())))
				&& (((this.numPredialAnterior == null) && (other.getNumPredialAnterior() == null))
				|| ((this.numPredialAnterior != null) && this.numPredialAnterior.equals(other.getNumPredialAnterior())))
				&& (((this.direccionPredio == null) && (other.getDireccionPredio() == null))
				|| ((this.direccionPredio != null) && this.direccionPredio.equals(other.getDireccionPredio())))
				&& (((this.codDepartamento == null) && (other.getCodDepartamento() == null))
				|| ((this.codDepartamento != null) && this.codDepartamento.equals(other.getCodDepartamento())))
				&& (((this.nomDepartamento == null) && (other.getNomDepartamento() == null))
				|| ((this.nomDepartamento != null) && this.nomDepartamento.equals(other.getNomDepartamento())))
				&& (((this.codMunicipio == null) && (other.getCodMunicipio() == null))
				|| ((this.codMunicipio != null) && this.codMunicipio.equals(other.getCodMunicipio())))
				&& (((this.nomMunicipio == null) && (other.getNomMunicipio() == null))
				|| ((this.nomMunicipio != null) && this.nomMunicipio.equals(other.getNomMunicipio())))
				&& (((this.nomVereda == null) && (other.getNomVereda() == null))
				|| ((this.nomVereda != null) && this.nomVereda.equals(other.getNomVereda())))
				&& (((this.nomBarrio == null) && (other.getNomBarrio() == null))
				|| ((this.nomBarrio != null) && this.nomBarrio.equals(other.getNomBarrio())))
				&& (((this.fechaAperturaFolio == null) && (other.getFechaAperturaFolio() == null))
				|| ((this.fechaAperturaFolio != null) && this.fechaAperturaFolio.equals(other.getFechaAperturaFolio())))
				&& (((this.fechaInstrumento == null) && (other.getFechaInstrumento() == null))
				|| ((this.fechaInstrumento != null) && this.fechaInstrumento.equals(other.getFechaInstrumento())))
				&& (((this.radicacion == null) && (other.getRadicacion() == null))
				|| ((this.radicacion != null) && this.radicacion.equals(other.getRadicacion())))
				&& (((this.nomTipoDocumentoPublico == null) && (other.getNomTipoDocumentoPublico() == null))
				|| ((this.nomTipoDocumentoPublico != null)
				&& this.nomTipoDocumentoPublico.equals(other.getNomTipoDocumentoPublico())))
				&& (((this.NIR == null) && (other.getNIR() == null))
				|| ((this.NIR != null) && this.NIR.equals(other.getNIR())))
				&& (((this.areaInmueble == null) && (other.getAreaInmueble() == null))
				|| ((this.areaInmueble != null) && this.areaInmueble.equals(other.getAreaInmueble())))
				&& (((this.areaConstruida == null) && (other.getAreaConstruida() == null))
				|| ((this.areaConstruida != null) && this.areaConstruida.equals(other.getAreaConstruida())))
				&& (((this.areaPrivada == null) && (other.getAreaPrivada() == null))
				|| ((this.areaPrivada != null) && this.areaPrivada.equals(other.getAreaPrivada())))
				&& (((this.estadoFolio == null) && (other.getEstadoFolio() == null))
				|| ((this.estadoFolio != null) && this.estadoFolio.equals(other.getEstadoFolio())))
				&& (((this.tipoPredio == null) && (other.getTipoPredio() == null))
				|| ((this.tipoPredio != null) && this.tipoPredio.equals(other.getTipoPredio())))
				&& (((this.linderos == null) && (other.getLinderos() == null))
				|| ((this.linderos != null) && this.linderos.equals(other.getLinderos())))
				&& (((this.complementaciones == null) && (other.getComplementaciones() == null))
				|| ((this.complementaciones != null) && this.complementaciones.equals(other.getComplementaciones())))
				&& (((this.codMensaje == null) && (other.getCodMensaje() == null))
				|| ((this.codMensaje != null) && this.codMensaje.equals(other.getCodMensaje())))
				&& (((this.descripcionMensaje == null) && (other.getDescripcionMensaje() == null))
				|| ((this.descripcionMensaje != null) && this.descripcionMensaje.equals(other.getDescripcionMensaje())));
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

		if(getNumMatriculaInmobiliaria() != null)
			_hashCode += getNumMatriculaInmobiliaria().hashCode();

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

		if(getNomCirculoRegistral() != null)
			_hashCode += getNomCirculoRegistral().hashCode();

		if(getNUPRE() != null)
			_hashCode += getNUPRE().hashCode();

		if(getEstadoNUPRE() != null)
			_hashCode += getEstadoNUPRE().hashCode();

		if(getNumPredial() != null)
			_hashCode += getNumPredial().hashCode();

		if(getNumPredialAnterior() != null)
			_hashCode += getNumPredialAnterior().hashCode();

		if(getDireccionPredio() != null)
			_hashCode += getDireccionPredio().hashCode();

		if(getCodDepartamento() != null)
			_hashCode += getCodDepartamento().hashCode();

		if(getNomDepartamento() != null)
			_hashCode += getNomDepartamento().hashCode();

		if(getCodMunicipio() != null)
			_hashCode += getCodMunicipio().hashCode();

		if(getNomMunicipio() != null)
			_hashCode += getNomMunicipio().hashCode();

		if(getNomVereda() != null)
			_hashCode += getNomVereda().hashCode();

		if(getNomBarrio() != null)
			_hashCode += getNomBarrio().hashCode();

		if(getFechaAperturaFolio() != null)
			_hashCode += getFechaAperturaFolio().hashCode();

		if(getFechaInstrumento() != null)
			_hashCode += getFechaInstrumento().hashCode();

		if(getRadicacion() != null)
			_hashCode += getRadicacion().hashCode();

		if(getNomTipoDocumentoPublico() != null)
			_hashCode += getNomTipoDocumentoPublico().hashCode();

		if(getNIR() != null)
			_hashCode += getNIR().hashCode();

		if(getAreaInmueble() != null)
			_hashCode += getAreaInmueble().hashCode();

		if(getAreaConstruida() != null)
			_hashCode += getAreaConstruida().hashCode();

		if(getAreaPrivada() != null)
			_hashCode += getAreaPrivada().hashCode();

		if(getEstadoFolio() != null)
			_hashCode += getEstadoFolio().hashCode();

		if(getTipoPredio() != null)
			_hashCode += getTipoPredio().hashCode();

		if(getLinderos() != null)
			_hashCode += getLinderos().hashCode();

		if(getComplementaciones() != null)
			_hashCode += getComplementaciones().hashCode();

		if(getCodMensaje() != null)
			_hashCode += getCodMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
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

	/**
	 * Get Custom Serializer.
	 *
	 * @param mechType de mech type
	 * @param _javaType de java type
	 * @param _xmlType de xml type
	 * @return el valor de serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
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
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}
}
