/**
 * TipoAnotacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1;



/**
 * El esquema define los
 *                         datos de salida para la operacion consultarAnotaciones.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
public class TipoAnotacion implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4349657397023973347L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoAnotacion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "tipoAnotacion"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numAnotacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "numAnotacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("anotacionesCanceladas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "anotacionesCanceladas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "tipoAnotacionCancelada"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("comentario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1", "comentario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codTipoDocumentoPublico");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "codTipoDocumentoPublico"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "fechaDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomDocumentoPublico");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "nomDocumentoPublico"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codDominioActoJuridico");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "codDominioActoJuridico"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomDominioActoJuridico");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "nomDominioActoJuridico"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("oficinaOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "oficinaOrigen"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("comentarioEspecificoAnotacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "comentarioEspecificoAnotacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("DRR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1", "DRR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codNaturalezaJuridicaFolio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "codNaturalezaJuridicaFolio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaAnotacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "fechaAnotacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("intervinientes");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "intervinientes"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "tipoInterviniente"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("NIR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1", "NIR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("radicacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1", "radicacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estadoAnotacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "estadoAnotacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorActo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1", "valorActo"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1", "codMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad drr. */
	/* corresponde a un derecho, una restricción o
	 *                                 una
	 *                                 responsabilidad. */
	private java.lang.String DRR;

	/** Propiedad nir. */
	/* Número de Identificación Registral de cada
	 *                                 anotación */
	private java.lang.String NIR;

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod dominio acto juridico. */
	/* corresponde al código o letra que identifica
	 *                                 el
	 *                                 dominio de la medida cautelar: X, H, C, A, D, E, O, P y V. */
	private java.lang.String codDominioActoJuridico;

	/** Propiedad cod mensaje. */
	/* 200 cuando no hay error, 4* por errores de
	 *                                 validación de campos de entrada o validación de negocio y
	 * 500
	 *                                 cuando existen errores de sistema */
	private java.math.BigInteger codMensaje;

	/** Propiedad cod naturaleza juridica folio. */
	/* Corresponde a los códigos de los actos
	 *                                 jurídicos */
	private java.lang.String codNaturalezaJuridicaFolio;

	/** Propiedad cod tipo documento publico. */
	/* Código del Nombre del documento */
	private java.lang.String codTipoDocumentoPublico;

	/** Propiedad comentario. */
	/* Comentario que detalla la anotación. */
	private java.lang.String comentario;

	/** Propiedad comentario especifico anotacion. */
	/* Comentario específico de la anotación */
	private java.lang.String comentarioEspecificoAnotacion;

	/** Propiedad descripcion mensaje. */
	/* Corresponde a al mensaje de error para los
	 *                                 casos
	 *                                 en qeue la consulta no fue exitosa, vacío cuando la consulta
	 * es
	 *                                 exitosa */
	private java.lang.String descripcionMensaje;

	/** Propiedad estado anotacion. */
	/* Determina que la anotación existe y tiene
	 *                                 validez, valores perrmitidos; V, I. */
	private java.lang.String estadoAnotacion;

	/** Propiedad fecha anotacion. */
	/* Corresponde a la fecha en que se realizó la
	 *                                 anotación */
	private java.util.Date fechaAnotacion;

	/** Propiedad fecha documento. */
	/* Fecha de Otorgamiento del documento público. */
	private java.util.Date fechaDocumento;

	/** Propiedad nom documento publico. */
	/* Nombre del documento */
	private java.lang.String nomDocumentoPublico;

	/** Propiedad nom dominio acto juridico. */
	/* corresponde al nombre correspondiente con la
	 *                                 letra que identifica el dominio de la medida cautelar:
	 *                                 MUTACIONES,
	 *                                 HIPOTECAS, CANCELACIONES, AFECTACIONES, DEMANDAS, EMBARGOS,
	 *                                 OFERTAS, PATRIMONIOS y VALORIZACIONES */
	private java.lang.String nomDominioActoJuridico;

	/** Propiedad num anotacion. */
	/* numero anotación del predio consultado */
	private java.math.BigInteger numAnotacion;

	/** Propiedad oficina origen. */
	/* corresponde a la oficina de origen */
	private java.lang.String oficinaOrigen;

	/** Propiedad radicacion. */
	/* Corresponde al turno */
	private java.lang.String radicacion;

	/** Propiedad valor acto. */
	/* Corresponde al monto de la transacción del
	 *                                 acto */
	private java.lang.String valorActo;

	/** Propiedad anotaciones canceladas. */
	private co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoAnotacionCancelada[] anotacionesCanceladas;

	/** Propiedad intervinientes. */
	private co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoInterviniente[] intervinientes;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo anotacion.
	 */
	public TipoAnotacion()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo anotacion.
	 *
	 * @param numAnotacion de num anotacion
	 * @param anotacionesCanceladas de anotaciones canceladas
	 * @param comentario de comentario
	 * @param codTipoDocumentoPublico de cod tipo documento publico
	 * @param fechaDocumento de fecha documento
	 * @param nomDocumentoPublico de nom documento publico
	 * @param codDominioActoJuridico de cod dominio acto juridico
	 * @param nomDominioActoJuridico de nom dominio acto juridico
	 * @param oficinaOrigen de oficina origen
	 * @param comentarioEspecificoAnotacion de comentario especifico anotacion
	 * @param DRR de drr
	 * @param codNaturalezaJuridicaFolio de cod naturaleza juridica folio
	 * @param fechaAnotacion de fecha anotacion
	 * @param intervinientes de intervinientes
	 * @param NIR de nir
	 * @param radicacion de radicacion
	 * @param estadoAnotacion de estado anotacion
	 * @param valorActo de valor acto
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoAnotacion(
	    java.math.BigInteger numAnotacion,
	    co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoAnotacionCancelada[] anotacionesCanceladas,
	    java.lang.String comentario, java.lang.String codTipoDocumentoPublico, java.util.Date fechaDocumento,
	    java.lang.String nomDocumentoPublico, java.lang.String codDominioActoJuridico,
	    java.lang.String nomDominioActoJuridico, java.lang.String oficinaOrigen,
	    java.lang.String comentarioEspecificoAnotacion, java.lang.String DRR,
	    java.lang.String codNaturalezaJuridicaFolio, java.util.Date fechaAnotacion,
	    co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoInterviniente[] intervinientes,
	    java.lang.String NIR, java.lang.String radicacion, java.lang.String estadoAnotacion, java.lang.String valorActo,
	    java.math.BigInteger codMensaje, java.lang.String descripcionMensaje
	)
	{
		this.numAnotacion                      = numAnotacion;
		this.anotacionesCanceladas             = anotacionesCanceladas;
		this.comentario                        = comentario;
		this.codTipoDocumentoPublico           = codTipoDocumentoPublico;
		this.fechaDocumento                    = fechaDocumento;
		this.nomDocumentoPublico               = nomDocumentoPublico;
		this.codDominioActoJuridico            = codDominioActoJuridico;
		this.nomDominioActoJuridico            = nomDominioActoJuridico;
		this.oficinaOrigen                     = oficinaOrigen;
		this.comentarioEspecificoAnotacion     = comentarioEspecificoAnotacion;
		this.DRR                               = DRR;
		this.codNaturalezaJuridicaFolio        = codNaturalezaJuridicaFolio;
		this.fechaAnotacion                    = fechaAnotacion;
		this.intervinientes                    = intervinientes;
		this.NIR                               = NIR;
		this.radicacion                        = radicacion;
		this.estadoAnotacion                   = estadoAnotacion;
		this.valorActo                         = valorActo;
		this.codMensaje                        = codMensaje;
		this.descripcionMensaje                = descripcionMensaje;
	}

	/**
	 * Gets the numAnotacion value for this TipoAnotacion.
	 *
	 * @return numAnotacion   * numero anotación del predio consultado
	 */
	public java.math.BigInteger getNumAnotacion()
	{
		return numAnotacion;
	}

	/**
	 * Sets the numAnotacion value for this TipoAnotacion.
	 *
	 * @param numAnotacion   * numero anotación del predio consultado
	 */
	public void setNumAnotacion(java.math.BigInteger numAnotacion)
	{
		this.numAnotacion = numAnotacion;
	}

	/**
	 * Gets the anotacionesCanceladas value for this TipoAnotacion.
	 *
	 * @return anotacionesCanceladas
	 */
	public co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoAnotacionCancelada[] getAnotacionesCanceladas()
	{
		return anotacionesCanceladas;
	}

	/**
	 * Sets the anotacionesCanceladas value for this TipoAnotacion.
	 *
	 * @param anotacionesCanceladas de anotaciones canceladas
	 */
	public void setAnotacionesCanceladas(
	    co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoAnotacionCancelada[] anotacionesCanceladas
	)
	{
		this.anotacionesCanceladas = anotacionesCanceladas;
	}

	/**
	 * Retorna Objeto o variable de valor anotaciones canceladas.
	 *
	 * @param i de i
	 * @return el valor de anotaciones canceladas
	 */
	public co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoAnotacionCancelada getAnotacionesCanceladas(
	    int i
	)
	{
		return this.anotacionesCanceladas[i];
	}

	/**
	 * Cambia el valor de anotaciones canceladas.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setAnotacionesCanceladas(
	    int i,
	    co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoAnotacionCancelada _value
	)
	{
		this.anotacionesCanceladas[i] = _value;
	}

	/**
	 * Gets the comentario value for this TipoAnotacion.
	 *
	 * @return comentario   * Comentario que detalla la anotación.
	 */
	public java.lang.String getComentario()
	{
		return comentario;
	}

	/**
	 * Sets the comentario value for this TipoAnotacion.
	 *
	 * @param comentario   * Comentario que detalla la anotación.
	 */
	public void setComentario(java.lang.String comentario)
	{
		this.comentario = comentario;
	}

	/**
	 * Gets the codTipoDocumentoPublico value for this TipoAnotacion.
	 *
	 * @return codTipoDocumentoPublico   * Código del Nombre del documento
	 */
	public java.lang.String getCodTipoDocumentoPublico()
	{
		return codTipoDocumentoPublico;
	}

	/**
	 * Sets the codTipoDocumentoPublico value for this TipoAnotacion.
	 *
	 * @param codTipoDocumentoPublico   * Código del Nombre del documento
	 */
	public void setCodTipoDocumentoPublico(java.lang.String codTipoDocumentoPublico)
	{
		this.codTipoDocumentoPublico = codTipoDocumentoPublico;
	}

	/**
	 * Gets the fechaDocumento value for this TipoAnotacion.
	 *
	 * @return fechaDocumento   * Fecha de Otorgamiento del documento público.
	 */
	public java.util.Date getFechaDocumento()
	{
		return fechaDocumento;
	}

	/**
	 * Sets the fechaDocumento value for this TipoAnotacion.
	 *
	 * @param fechaDocumento   * Fecha de Otorgamiento del documento público.
	 */
	public void setFechaDocumento(java.util.Date fechaDocumento)
	{
		this.fechaDocumento = fechaDocumento;
	}

	/**
	 * Gets the nomDocumentoPublico value for this TipoAnotacion.
	 *
	 * @return nomDocumentoPublico   * Nombre del documento
	 */
	public java.lang.String getNomDocumentoPublico()
	{
		return nomDocumentoPublico;
	}

	/**
	 * Sets the nomDocumentoPublico value for this TipoAnotacion.
	 *
	 * @param nomDocumentoPublico   * Nombre del documento
	 */
	public void setNomDocumentoPublico(java.lang.String nomDocumentoPublico)
	{
		this.nomDocumentoPublico = nomDocumentoPublico;
	}

	/**
	 * Gets the codDominioActoJuridico value for this TipoAnotacion.
	 *
	 * @return codDominioActoJuridico   * corresponde al código o letra que identifica
	     *                                 el
	     *                                 dominio de la medida cautelar: X, H, C, A, D, E, O, P y V.
	 */
	public java.lang.String getCodDominioActoJuridico()
	{
		return codDominioActoJuridico;
	}

	/**
	 * Sets the codDominioActoJuridico value for this TipoAnotacion.
	 *
	 * @param codDominioActoJuridico   * corresponde al código o letra que identifica
	     *                                 el
	     *                                 dominio de la medida cautelar: X, H, C, A, D, E, O, P y V.
	 */
	public void setCodDominioActoJuridico(java.lang.String codDominioActoJuridico)
	{
		this.codDominioActoJuridico = codDominioActoJuridico;
	}

	/**
	 * Gets the nomDominioActoJuridico value for this TipoAnotacion.
	 *
	 * @return nomDominioActoJuridico   * corresponde al nombre correspondiente con la
	     *                                 letra que identifica el dominio de la medida cautelar:
	     *                                 MUTACIONES,
	     *                                 HIPOTECAS, CANCELACIONES, AFECTACIONES, DEMANDAS, EMBARGOS,
	     *                                 OFERTAS, PATRIMONIOS y VALORIZACIONES
	 */
	public java.lang.String getNomDominioActoJuridico()
	{
		return nomDominioActoJuridico;
	}

	/**
	 * Sets the nomDominioActoJuridico value for this TipoAnotacion.
	 *
	 * @param nomDominioActoJuridico   * corresponde al nombre correspondiente con la
	     *                                 letra que identifica el dominio de la medida cautelar:
	     *                                 MUTACIONES,
	     *                                 HIPOTECAS, CANCELACIONES, AFECTACIONES, DEMANDAS, EMBARGOS,
	     *                                 OFERTAS, PATRIMONIOS y VALORIZACIONES
	 */
	public void setNomDominioActoJuridico(java.lang.String nomDominioActoJuridico)
	{
		this.nomDominioActoJuridico = nomDominioActoJuridico;
	}

	/**
	 * Gets the oficinaOrigen value for this TipoAnotacion.
	 *
	 * @return oficinaOrigen   * corresponde a la oficina de origen
	 */
	public java.lang.String getOficinaOrigen()
	{
		return oficinaOrigen;
	}

	/**
	 * Sets the oficinaOrigen value for this TipoAnotacion.
	 *
	 * @param oficinaOrigen   * corresponde a la oficina de origen
	 */
	public void setOficinaOrigen(java.lang.String oficinaOrigen)
	{
		this.oficinaOrigen = oficinaOrigen;
	}

	/**
	 * Gets the comentarioEspecificoAnotacion value for this TipoAnotacion.
	 *
	 * @return comentarioEspecificoAnotacion   * Comentario específico de la anotación
	 */
	public java.lang.String getComentarioEspecificoAnotacion()
	{
		return comentarioEspecificoAnotacion;
	}

	/**
	 * Sets the comentarioEspecificoAnotacion value for this TipoAnotacion.
	 *
	 * @param comentarioEspecificoAnotacion   * Comentario específico de la anotación
	 */
	public void setComentarioEspecificoAnotacion(java.lang.String comentarioEspecificoAnotacion)
	{
		this.comentarioEspecificoAnotacion = comentarioEspecificoAnotacion;
	}

	/**
	 * Gets the DRR value for this TipoAnotacion.
	 *
	 * @return DRR   * corresponde a un derecho, una restricción o
	     *                                 una
	     *                                 responsabilidad.
	 */
	public java.lang.String getDRR()
	{
		return DRR;
	}

	/**
	 * Sets the DRR value for this TipoAnotacion.
	 *
	 * @param DRR   * corresponde a un derecho, una restricción o
	     *                                 una
	     *                                 responsabilidad.
	 */
	public void setDRR(java.lang.String DRR)
	{
		this.DRR = DRR;
	}

	/**
	 * Gets the codNaturalezaJuridicaFolio value for this TipoAnotacion.
	 *
	 * @return codNaturalezaJuridicaFolio   * Corresponde a los códigos de los actos
	     *                                 jurídicos
	 */
	public java.lang.String getCodNaturalezaJuridicaFolio()
	{
		return codNaturalezaJuridicaFolio;
	}

	/**
	 * Sets the codNaturalezaJuridicaFolio value for this TipoAnotacion.
	 *
	 * @param codNaturalezaJuridicaFolio   * Corresponde a los códigos de los actos
	     *                                 jurídicos
	 */
	public void setCodNaturalezaJuridicaFolio(java.lang.String codNaturalezaJuridicaFolio)
	{
		this.codNaturalezaJuridicaFolio = codNaturalezaJuridicaFolio;
	}

	/**
	 * Gets the fechaAnotacion value for this TipoAnotacion.
	 *
	 * @return fechaAnotacion   * Corresponde a la fecha en que se realizó la
	     *                                 anotación
	 */
	public java.util.Date getFechaAnotacion()
	{
		return fechaAnotacion;
	}

	/**
	 * Sets the fechaAnotacion value for this TipoAnotacion.
	 *
	 * @param fechaAnotacion   * Corresponde a la fecha en que se realizó la
	     *                                 anotación
	 */
	public void setFechaAnotacion(java.util.Date fechaAnotacion)
	{
		this.fechaAnotacion = fechaAnotacion;
	}

	/**
	 * Gets the intervinientes value for this TipoAnotacion.
	 *
	 * @return intervinientes
	 */
	public co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoInterviniente[] getIntervinientes()
	{
		return intervinientes;
	}

	/**
	 * Sets the intervinientes value for this TipoAnotacion.
	 *
	 * @param intervinientes de intervinientes
	 */
	public void setIntervinientes(
	    co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoInterviniente[] intervinientes
	)
	{
		this.intervinientes = intervinientes;
	}

	/**
	 * Retorna Objeto o variable de valor intervinientes.
	 *
	 * @param i de i
	 * @return el valor de intervinientes
	 */
	public co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoInterviniente getIntervinientes(
	    int i
	)
	{
		return this.intervinientes[i];
	}

	/**
	 * Cambia el valor de intervinientes.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setIntervinientes(
	    int i, co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoInterviniente _value
	)
	{
		this.intervinientes[i] = _value;
	}

	/**
	 * Gets the NIR value for this TipoAnotacion.
	 *
	 * @return NIR   * Número de Identificación Registral de cada
	     *                                 anotación
	 */
	public java.lang.String getNIR()
	{
		return NIR;
	}

	/**
	 * Sets the NIR value for this TipoAnotacion.
	 *
	 * @param NIR   * Número de Identificación Registral de cada
	     *                                 anotación
	 */
	public void setNIR(java.lang.String NIR)
	{
		this.NIR = NIR;
	}

	/**
	 * Gets the radicacion value for this TipoAnotacion.
	 *
	 * @return radicacion   * Corresponde al turno
	 */
	public java.lang.String getRadicacion()
	{
		return radicacion;
	}

	/**
	 * Sets the radicacion value for this TipoAnotacion.
	 *
	 * @param radicacion   * Corresponde al turno
	 */
	public void setRadicacion(java.lang.String radicacion)
	{
		this.radicacion = radicacion;
	}

	/**
	 * Gets the estadoAnotacion value for this TipoAnotacion.
	 *
	 * @return estadoAnotacion   * Determina que la anotación existe y tiene
	     *                                 validez, valores perrmitidos; V, I.
	 */
	public java.lang.String getEstadoAnotacion()
	{
		return estadoAnotacion;
	}

	/**
	 * Sets the estadoAnotacion value for this TipoAnotacion.
	 *
	 * @param estadoAnotacion   * Determina que la anotación existe y tiene
	     *                                 validez, valores perrmitidos; V, I.
	 */
	public void setEstadoAnotacion(java.lang.String estadoAnotacion)
	{
		this.estadoAnotacion = estadoAnotacion;
	}

	/**
	 * Gets the valorActo value for this TipoAnotacion.
	 *
	 * @return valorActo   * Corresponde al monto de la transacción del
	     *                                 acto
	 */
	public java.lang.String getValorActo()
	{
		return valorActo;
	}

	/**
	 * Sets the valorActo value for this TipoAnotacion.
	 *
	 * @param valorActo   * Corresponde al monto de la transacción del
	     *                                 acto
	 */
	public void setValorActo(java.lang.String valorActo)
	{
		this.valorActo = valorActo;
	}

	/**
	 * Gets the codMensaje value for this TipoAnotacion.
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
	 * Sets the codMensaje value for this TipoAnotacion.
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
	 * Gets the descripcionMensaje value for this TipoAnotacion.
	 *
	 * @return descripcionMensaje   * Corresponde a al mensaje de error para los
	     *                                 casos
	     *                                 en qeue la consulta no fue exitosa, vacío cuando la consulta
	     * es
	     *                                 exitosa
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoAnotacion.
	 *
	 * @param descripcionMensaje   * Corresponde a al mensaje de error para los
	     *                                 casos
	     *                                 en qeue la consulta no fue exitosa, vacío cuando la consulta
	     * es
	     *                                 exitosa
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoAnotacion))
			return false;

		TipoAnotacion other = (TipoAnotacion)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numAnotacion == null) && (other.getNumAnotacion() == null))
				|| ((this.numAnotacion != null) && this.numAnotacion.equals(other.getNumAnotacion())))
				&& (((this.anotacionesCanceladas == null) && (other.getAnotacionesCanceladas() == null))
				|| ((this.anotacionesCanceladas != null)
				&& java.util.Arrays.equals(this.anotacionesCanceladas, other.getAnotacionesCanceladas())))
				&& (((this.comentario == null) && (other.getComentario() == null))
				|| ((this.comentario != null) && this.comentario.equals(other.getComentario())))
				&& (((this.codTipoDocumentoPublico == null) && (other.getCodTipoDocumentoPublico() == null))
				|| ((this.codTipoDocumentoPublico != null)
				&& this.codTipoDocumentoPublico.equals(other.getCodTipoDocumentoPublico())))
				&& (((this.fechaDocumento == null) && (other.getFechaDocumento() == null))
				|| ((this.fechaDocumento != null) && this.fechaDocumento.equals(other.getFechaDocumento())))
				&& (((this.nomDocumentoPublico == null) && (other.getNomDocumentoPublico() == null))
				|| ((this.nomDocumentoPublico != null)
				&& this.nomDocumentoPublico.equals(other.getNomDocumentoPublico())))
				&& (((this.codDominioActoJuridico == null) && (other.getCodDominioActoJuridico() == null))
				|| ((this.codDominioActoJuridico != null)
				&& this.codDominioActoJuridico.equals(other.getCodDominioActoJuridico())))
				&& (((this.nomDominioActoJuridico == null) && (other.getNomDominioActoJuridico() == null))
				|| ((this.nomDominioActoJuridico != null)
				&& this.nomDominioActoJuridico.equals(other.getNomDominioActoJuridico())))
				&& (((this.oficinaOrigen == null) && (other.getOficinaOrigen() == null))
				|| ((this.oficinaOrigen != null) && this.oficinaOrigen.equals(other.getOficinaOrigen())))
				&& (((this.comentarioEspecificoAnotacion == null) && (other.getComentarioEspecificoAnotacion() == null))
				|| ((this.comentarioEspecificoAnotacion != null)
				&& this.comentarioEspecificoAnotacion.equals(other.getComentarioEspecificoAnotacion())))
				&& (((this.DRR == null) && (other.getDRR() == null))
				|| ((this.DRR != null) && this.DRR.equals(other.getDRR())))
				&& (((this.codNaturalezaJuridicaFolio == null) && (other.getCodNaturalezaJuridicaFolio() == null))
				|| ((this.codNaturalezaJuridicaFolio != null)
				&& this.codNaturalezaJuridicaFolio.equals(other.getCodNaturalezaJuridicaFolio())))
				&& (((this.fechaAnotacion == null) && (other.getFechaAnotacion() == null))
				|| ((this.fechaAnotacion != null) && this.fechaAnotacion.equals(other.getFechaAnotacion())))
				&& (((this.intervinientes == null) && (other.getIntervinientes() == null))
				|| ((this.intervinientes != null)
				&& java.util.Arrays.equals(this.intervinientes, other.getIntervinientes())))
				&& (((this.NIR == null) && (other.getNIR() == null))
				|| ((this.NIR != null) && this.NIR.equals(other.getNIR())))
				&& (((this.radicacion == null) && (other.getRadicacion() == null))
				|| ((this.radicacion != null) && this.radicacion.equals(other.getRadicacion())))
				&& (((this.estadoAnotacion == null) && (other.getEstadoAnotacion() == null))
				|| ((this.estadoAnotacion != null) && this.estadoAnotacion.equals(other.getEstadoAnotacion())))
				&& (((this.valorActo == null) && (other.getValorActo() == null))
				|| ((this.valorActo != null) && this.valorActo.equals(other.getValorActo())))
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

		if(getNumAnotacion() != null)
			_hashCode += getNumAnotacion().hashCode();

		if(getAnotacionesCanceladas() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getAnotacionesCanceladas()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getAnotacionesCanceladas(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getComentario() != null)
			_hashCode += getComentario().hashCode();

		if(getCodTipoDocumentoPublico() != null)
			_hashCode += getCodTipoDocumentoPublico().hashCode();

		if(getFechaDocumento() != null)
			_hashCode += getFechaDocumento().hashCode();

		if(getNomDocumentoPublico() != null)
			_hashCode += getNomDocumentoPublico().hashCode();

		if(getCodDominioActoJuridico() != null)
			_hashCode += getCodDominioActoJuridico().hashCode();

		if(getNomDominioActoJuridico() != null)
			_hashCode += getNomDominioActoJuridico().hashCode();

		if(getOficinaOrigen() != null)
			_hashCode += getOficinaOrigen().hashCode();

		if(getComentarioEspecificoAnotacion() != null)
			_hashCode += getComentarioEspecificoAnotacion().hashCode();

		if(getDRR() != null)
			_hashCode += getDRR().hashCode();

		if(getCodNaturalezaJuridicaFolio() != null)
			_hashCode += getCodNaturalezaJuridicaFolio().hashCode();

		if(getFechaAnotacion() != null)
			_hashCode += getFechaAnotacion().hashCode();

		if(getIntervinientes() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getIntervinientes()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getIntervinientes(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getNIR() != null)
			_hashCode += getNIR().hashCode();

		if(getRadicacion() != null)
			_hashCode += getRadicacion().hashCode();

		if(getEstadoAnotacion() != null)
			_hashCode += getEstadoAnotacion().hashCode();

		if(getValorActo() != null)
			_hashCode += getValorActo().hashCode();

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
