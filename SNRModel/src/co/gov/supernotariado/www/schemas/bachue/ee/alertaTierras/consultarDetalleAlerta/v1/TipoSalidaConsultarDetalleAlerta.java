/**
 * TipoSalidaConsultarDetalleAlerta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1;



/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarDetalleAlerta.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoSalidaConsultarDetalleAlerta implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 940638140491779332L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarDetalleAlerta.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "tipoSalidaConsultarDetalleAlerta"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "codigo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("mensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "mensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoAlerta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "tipoAlerta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreEntidad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "nombreEntidad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("procesoNroRadicado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "procesoNroRadicado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("procesoFechaRadicado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "procesoFechaRadicado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomOficinaOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "nomOficinaOrigen"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomTipoDocumentoPublico");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "nomTipoDocumentoPublico"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "fechaDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("docNumero");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "docNumero"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("docIdSGD");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "docIdSGD"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("docNameSGD");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "docNameSGD"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nitComunidadEtnica");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "nitComunidadEtnica"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1", "estado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaInactivacionAlerta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "fechaInactivacionAlerta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("creadoSNR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "creadoSNR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaMatriculas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "listaMatriculas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        ">>tipoSalidaConsultarDetalleAlerta>listaMatriculas>matricula"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "matricula"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("inactivacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "inactivacion"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        ">tipoSalidaConsultarDetalleAlerta>inactivacion"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaAlertasGeneradas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "listaAlertasGeneradas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        ">>tipoSalidaConsultarDetalleAlerta>listaAlertasGeneradas>alertaGenerada"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "alertaGenerada"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaProcesosAS");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "listaProcesosAS"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        ">>tipoSalidaConsultarDetalleAlerta>listaProcesosAS>procesoAS"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "procesoAS"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad codigo. */
	private java.lang.String codigo;

	/** Propiedad creado SNR. */
	private java.lang.String creadoSNR;

	/** Propiedad doc id SGD. */
	private java.lang.String docIdSGD;

	/** Propiedad doc name SGD. */
	private java.lang.String docNameSGD;

	/** Propiedad doc numero. */
	private java.lang.String docNumero;

	/** Propiedad estado. */
	private java.lang.String estado;

	/** Propiedad fecha documento. */
	private java.util.Calendar fechaDocumento;

	/** Propiedad fecha inactivacion alerta. */
	private java.util.Calendar fechaInactivacionAlerta;

	/** Propiedad inactivacion. */
	private co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaInactivacion inactivacion;

	/** Propiedad mensaje. */
	private java.lang.String mensaje;

	/** Propiedad nit comunidad etnica. */
	private java.lang.String nitComunidadEtnica;

	/** Propiedad nom oficina origen. */
	private java.lang.String nomOficinaOrigen;

	/** Propiedad nom tipo documento publico. */
	private java.lang.String nomTipoDocumentoPublico;

	/** Propiedad nombre entidad. */
	private java.lang.String nombreEntidad;

	/** Propiedad proceso fecha radicado. */
	private java.util.Calendar procesoFechaRadicado;

	/** Propiedad proceso nro radicado. */
	private java.lang.String procesoNroRadicado;

	/** Propiedad tipo alerta. */
	private java.lang.String tipoAlerta;

	/** Propiedad lista alertas generadas. */
	private co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaAlertasGeneradasAlertaGenerada[] listaAlertasGeneradas;

	/** Propiedad lista matriculas. */
	private co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula[] listaMatriculas;

	/** Propiedad lista procesos AS. */
	private co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaProcesosASProcesoAS[] listaProcesosAS;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar detalle alerta.
	 */
	public TipoSalidaConsultarDetalleAlerta()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar detalle alerta.
	 *
	 * @param codigo de codigo
	 * @param mensaje de mensaje
	 * @param tipoAlerta de tipo alerta
	 * @param nombreEntidad de nombre entidad
	 * @param procesoNroRadicado de proceso nro radicado
	 * @param procesoFechaRadicado de proceso fecha radicado
	 * @param nomOficinaOrigen de nom oficina origen
	 * @param nomTipoDocumentoPublico de nom tipo documento publico
	 * @param fechaDocumento de fecha documento
	 * @param docNumero de doc numero
	 * @param docIdSGD de doc id SGD
	 * @param docNameSGD de doc name SGD
	 * @param nitComunidadEtnica de nit comunidad etnica
	 * @param estado de estado
	 * @param fechaInactivacionAlerta de fecha inactivacion alerta
	 * @param creadoSNR de creado SNR
	 * @param listaMatriculas de lista matriculas
	 * @param inactivacion de inactivacion
	 * @param listaAlertasGeneradas de lista alertas generadas
	 * @param listaProcesosAS de lista procesos AS
	 */
	public TipoSalidaConsultarDetalleAlerta(
	    java.lang.String codigo, java.lang.String mensaje, java.lang.String tipoAlerta, java.lang.String nombreEntidad,
	    java.lang.String procesoNroRadicado, java.util.Calendar procesoFechaRadicado, java.lang.String nomOficinaOrigen,
	    java.lang.String nomTipoDocumentoPublico, java.util.Calendar fechaDocumento, java.lang.String docNumero,
	    java.lang.String docIdSGD, java.lang.String docNameSGD, java.lang.String nitComunidadEtnica,
	    java.lang.String estado, java.util.Calendar fechaInactivacionAlerta, java.lang.String creadoSNR,
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula[] listaMatriculas,
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaInactivacion inactivacion,
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaAlertasGeneradasAlertaGenerada[] listaAlertasGeneradas,
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaProcesosASProcesoAS[] listaProcesosAS
	)
	{
		this.codigo                      = codigo;
		this.mensaje                     = mensaje;
		this.tipoAlerta                  = tipoAlerta;
		this.nombreEntidad               = nombreEntidad;
		this.procesoNroRadicado          = procesoNroRadicado;
		this.procesoFechaRadicado        = procesoFechaRadicado;
		this.nomOficinaOrigen            = nomOficinaOrigen;
		this.nomTipoDocumentoPublico     = nomTipoDocumentoPublico;
		this.fechaDocumento              = fechaDocumento;
		this.docNumero                   = docNumero;
		this.docIdSGD                    = docIdSGD;
		this.docNameSGD                  = docNameSGD;
		this.nitComunidadEtnica          = nitComunidadEtnica;
		this.estado                      = estado;
		this.fechaInactivacionAlerta     = fechaInactivacionAlerta;
		this.creadoSNR                   = creadoSNR;
		this.listaMatriculas             = listaMatriculas;
		this.inactivacion                = inactivacion;
		this.listaAlertasGeneradas       = listaAlertasGeneradas;
		this.listaProcesosAS             = listaProcesosAS;
	}

	/**
	 * Gets the codigo value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return codigo
	 */
	public java.lang.String getCodigo()
	{
		return codigo;
	}

	/**
	 * Sets the codigo value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param codigo de codigo
	 */
	public void setCodigo(java.lang.String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Gets the mensaje value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return mensaje
	 */
	public java.lang.String getMensaje()
	{
		return mensaje;
	}

	/**
	 * Sets the mensaje value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param mensaje de mensaje
	 */
	public void setMensaje(java.lang.String mensaje)
	{
		this.mensaje = mensaje;
	}

	/**
	 * Gets the tipoAlerta value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return tipoAlerta
	 */
	public java.lang.String getTipoAlerta()
	{
		return tipoAlerta;
	}

	/**
	 * Sets the tipoAlerta value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param tipoAlerta de tipo alerta
	 */
	public void setTipoAlerta(java.lang.String tipoAlerta)
	{
		this.tipoAlerta = tipoAlerta;
	}

	/**
	 * Gets the nombreEntidad value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return nombreEntidad
	 */
	public java.lang.String getNombreEntidad()
	{
		return nombreEntidad;
	}

	/**
	 * Sets the nombreEntidad value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param nombreEntidad de nombre entidad
	 */
	public void setNombreEntidad(java.lang.String nombreEntidad)
	{
		this.nombreEntidad = nombreEntidad;
	}

	/**
	 * Gets the procesoNroRadicado value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return procesoNroRadicado
	 */
	public java.lang.String getProcesoNroRadicado()
	{
		return procesoNroRadicado;
	}

	/**
	 * Sets the procesoNroRadicado value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param procesoNroRadicado de proceso nro radicado
	 */
	public void setProcesoNroRadicado(java.lang.String procesoNroRadicado)
	{
		this.procesoNroRadicado = procesoNroRadicado;
	}

	/**
	 * Gets the procesoFechaRadicado value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return procesoFechaRadicado
	 */
	public java.util.Calendar getProcesoFechaRadicado()
	{
		return procesoFechaRadicado;
	}

	/**
	 * Sets the procesoFechaRadicado value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param procesoFechaRadicado de proceso fecha radicado
	 */
	public void setProcesoFechaRadicado(java.util.Calendar procesoFechaRadicado)
	{
		this.procesoFechaRadicado = procesoFechaRadicado;
	}

	/**
	 * Gets the nomOficinaOrigen value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return nomOficinaOrigen
	 */
	public java.lang.String getNomOficinaOrigen()
	{
		return nomOficinaOrigen;
	}

	/**
	 * Sets the nomOficinaOrigen value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param nomOficinaOrigen de nom oficina origen
	 */
	public void setNomOficinaOrigen(java.lang.String nomOficinaOrigen)
	{
		this.nomOficinaOrigen = nomOficinaOrigen;
	}

	/**
	 * Gets the nomTipoDocumentoPublico value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return nomTipoDocumentoPublico
	 */
	public java.lang.String getNomTipoDocumentoPublico()
	{
		return nomTipoDocumentoPublico;
	}

	/**
	 * Sets the nomTipoDocumentoPublico value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param nomTipoDocumentoPublico de nom tipo documento publico
	 */
	public void setNomTipoDocumentoPublico(java.lang.String nomTipoDocumentoPublico)
	{
		this.nomTipoDocumentoPublico = nomTipoDocumentoPublico;
	}

	/**
	 * Gets the fechaDocumento value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return fechaDocumento
	 */
	public java.util.Calendar getFechaDocumento()
	{
		return fechaDocumento;
	}

	/**
	 * Sets the fechaDocumento value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param fechaDocumento de fecha documento
	 */
	public void setFechaDocumento(java.util.Calendar fechaDocumento)
	{
		this.fechaDocumento = fechaDocumento;
	}

	/**
	 * Gets the docNumero value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return docNumero
	 */
	public java.lang.String getDocNumero()
	{
		return docNumero;
	}

	/**
	 * Sets the docNumero value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param docNumero de doc numero
	 */
	public void setDocNumero(java.lang.String docNumero)
	{
		this.docNumero = docNumero;
	}

	/**
	 * Gets the docIdSGD value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return docIdSGD
	 */
	public java.lang.String getDocIdSGD()
	{
		return docIdSGD;
	}

	/**
	 * Sets the docIdSGD value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param docIdSGD de doc id SGD
	 */
	public void setDocIdSGD(java.lang.String docIdSGD)
	{
		this.docIdSGD = docIdSGD;
	}

	/**
	 * Gets the docNameSGD value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return docNameSGD
	 */
	public java.lang.String getDocNameSGD()
	{
		return docNameSGD;
	}

	/**
	 * Sets the docNameSGD value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param docNameSGD de doc name SGD
	 */
	public void setDocNameSGD(java.lang.String docNameSGD)
	{
		this.docNameSGD = docNameSGD;
	}

	/**
	 * Gets the nitComunidadEtnica value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return nitComunidadEtnica
	 */
	public java.lang.String getNitComunidadEtnica()
	{
		return nitComunidadEtnica;
	}

	/**
	 * Sets the nitComunidadEtnica value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param nitComunidadEtnica de nit comunidad etnica
	 */
	public void setNitComunidadEtnica(java.lang.String nitComunidadEtnica)
	{
		this.nitComunidadEtnica = nitComunidadEtnica;
	}

	/**
	 * Gets the estado value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return estado
	 */
	public java.lang.String getEstado()
	{
		return estado;
	}

	/**
	 * Sets the estado value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param estado de estado
	 */
	public void setEstado(java.lang.String estado)
	{
		this.estado = estado;
	}

	/**
	 * Gets the fechaInactivacionAlerta value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return fechaInactivacionAlerta
	 */
	public java.util.Calendar getFechaInactivacionAlerta()
	{
		return fechaInactivacionAlerta;
	}

	/**
	 * Sets the fechaInactivacionAlerta value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param fechaInactivacionAlerta de fecha inactivacion alerta
	 */
	public void setFechaInactivacionAlerta(java.util.Calendar fechaInactivacionAlerta)
	{
		this.fechaInactivacionAlerta = fechaInactivacionAlerta;
	}

	/**
	 * Gets the creadoSNR value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return creadoSNR
	 */
	public java.lang.String getCreadoSNR()
	{
		return creadoSNR;
	}

	/**
	 * Sets the creadoSNR value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param creadoSNR de creado SNR
	 */
	public void setCreadoSNR(java.lang.String creadoSNR)
	{
		this.creadoSNR = creadoSNR;
	}

	/**
	 * Gets the listaMatriculas value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return listaMatriculas
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula[] getListaMatriculas()
	{
		return listaMatriculas;
	}

	/**
	 * Sets the listaMatriculas value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param listaMatriculas de lista matriculas
	 */
	public void setListaMatriculas(
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula[] listaMatriculas
	)
	{
		this.listaMatriculas = listaMatriculas;
	}

	/**
	 * Gets the inactivacion value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return inactivacion
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaInactivacion getInactivacion()
	{
		return inactivacion;
	}

	/**
	 * Sets the inactivacion value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param inactivacion de inactivacion
	 */
	public void setInactivacion(
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaInactivacion inactivacion
	)
	{
		this.inactivacion = inactivacion;
	}

	/**
	 * Gets the listaAlertasGeneradas value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return listaAlertasGeneradas
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaAlertasGeneradasAlertaGenerada[] getListaAlertasGeneradas()
	{
		return listaAlertasGeneradas;
	}

	/**
	 * Sets the listaAlertasGeneradas value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param listaAlertasGeneradas de lista alertas generadas
	 */
	public void setListaAlertasGeneradas(
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaAlertasGeneradasAlertaGenerada[] listaAlertasGeneradas
	)
	{
		this.listaAlertasGeneradas = listaAlertasGeneradas;
	}

	/**
	 * Gets the listaProcesosAS value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @return listaProcesosAS
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaProcesosASProcesoAS[] getListaProcesosAS()
	{
		return listaProcesosAS;
	}

	/**
	 * Sets the listaProcesosAS value for this TipoSalidaConsultarDetalleAlerta.
	 *
	 * @param listaProcesosAS de lista procesos AS
	 */
	public void setListaProcesosAS(
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlertaListaProcesosASProcesoAS[] listaProcesosAS
	)
	{
		this.listaProcesosAS = listaProcesosAS;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultarDetalleAlerta))
			return false;

		TipoSalidaConsultarDetalleAlerta other = (TipoSalidaConsultarDetalleAlerta)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigo == null) && (other.getCodigo() == null))
				|| ((this.codigo != null) && this.codigo.equals(other.getCodigo())))
				&& (((this.mensaje == null) && (other.getMensaje() == null))
				|| ((this.mensaje != null) && this.mensaje.equals(other.getMensaje())))
				&& (((this.tipoAlerta == null) && (other.getTipoAlerta() == null))
				|| ((this.tipoAlerta != null) && this.tipoAlerta.equals(other.getTipoAlerta())))
				&& (((this.nombreEntidad == null) && (other.getNombreEntidad() == null))
				|| ((this.nombreEntidad != null) && this.nombreEntidad.equals(other.getNombreEntidad())))
				&& (((this.procesoNroRadicado == null) && (other.getProcesoNroRadicado() == null))
				|| ((this.procesoNroRadicado != null) && this.procesoNroRadicado.equals(other.getProcesoNroRadicado())))
				&& (((this.procesoFechaRadicado == null) && (other.getProcesoFechaRadicado() == null))
				|| ((this.procesoFechaRadicado != null)
				&& this.procesoFechaRadicado.equals(other.getProcesoFechaRadicado())))
				&& (((this.nomOficinaOrigen == null) && (other.getNomOficinaOrigen() == null))
				|| ((this.nomOficinaOrigen != null) && this.nomOficinaOrigen.equals(other.getNomOficinaOrigen())))
				&& (((this.nomTipoDocumentoPublico == null) && (other.getNomTipoDocumentoPublico() == null))
				|| ((this.nomTipoDocumentoPublico != null)
				&& this.nomTipoDocumentoPublico.equals(other.getNomTipoDocumentoPublico())))
				&& (((this.fechaDocumento == null) && (other.getFechaDocumento() == null))
				|| ((this.fechaDocumento != null) && this.fechaDocumento.equals(other.getFechaDocumento())))
				&& (((this.docNumero == null) && (other.getDocNumero() == null))
				|| ((this.docNumero != null) && this.docNumero.equals(other.getDocNumero())))
				&& (((this.docIdSGD == null) && (other.getDocIdSGD() == null))
				|| ((this.docIdSGD != null) && this.docIdSGD.equals(other.getDocIdSGD())))
				&& (((this.docNameSGD == null) && (other.getDocNameSGD() == null))
				|| ((this.docNameSGD != null) && this.docNameSGD.equals(other.getDocNameSGD())))
				&& (((this.nitComunidadEtnica == null) && (other.getNitComunidadEtnica() == null))
				|| ((this.nitComunidadEtnica != null) && this.nitComunidadEtnica.equals(other.getNitComunidadEtnica())))
				&& (((this.estado == null) && (other.getEstado() == null))
				|| ((this.estado != null) && this.estado.equals(other.getEstado())))
				&& (((this.fechaInactivacionAlerta == null) && (other.getFechaInactivacionAlerta() == null))
				|| ((this.fechaInactivacionAlerta != null)
				&& this.fechaInactivacionAlerta.equals(other.getFechaInactivacionAlerta())))
				&& (((this.creadoSNR == null) && (other.getCreadoSNR() == null))
				|| ((this.creadoSNR != null) && this.creadoSNR.equals(other.getCreadoSNR())))
				&& (((this.listaMatriculas == null) && (other.getListaMatriculas() == null))
				|| ((this.listaMatriculas != null)
				&& java.util.Arrays.equals(this.listaMatriculas, other.getListaMatriculas())))
				&& (((this.inactivacion == null) && (other.getInactivacion() == null))
				|| ((this.inactivacion != null) && this.inactivacion.equals(other.getInactivacion())))
				&& (((this.listaAlertasGeneradas == null) && (other.getListaAlertasGeneradas() == null))
				|| ((this.listaAlertasGeneradas != null)
				&& java.util.Arrays.equals(this.listaAlertasGeneradas, other.getListaAlertasGeneradas())))
				&& (((this.listaProcesosAS == null) && (other.getListaProcesosAS() == null))
				|| ((this.listaProcesosAS != null)
				&& java.util.Arrays.equals(this.listaProcesosAS, other.getListaProcesosAS())));
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

		if(getCodigo() != null)
			_hashCode += getCodigo().hashCode();

		if(getMensaje() != null)
			_hashCode += getMensaje().hashCode();

		if(getTipoAlerta() != null)
			_hashCode += getTipoAlerta().hashCode();

		if(getNombreEntidad() != null)
			_hashCode += getNombreEntidad().hashCode();

		if(getProcesoNroRadicado() != null)
			_hashCode += getProcesoNroRadicado().hashCode();

		if(getProcesoFechaRadicado() != null)
			_hashCode += getProcesoFechaRadicado().hashCode();

		if(getNomOficinaOrigen() != null)
			_hashCode += getNomOficinaOrigen().hashCode();

		if(getNomTipoDocumentoPublico() != null)
			_hashCode += getNomTipoDocumentoPublico().hashCode();

		if(getFechaDocumento() != null)
			_hashCode += getFechaDocumento().hashCode();

		if(getDocNumero() != null)
			_hashCode += getDocNumero().hashCode();

		if(getDocIdSGD() != null)
			_hashCode += getDocIdSGD().hashCode();

		if(getDocNameSGD() != null)
			_hashCode += getDocNameSGD().hashCode();

		if(getNitComunidadEtnica() != null)
			_hashCode += getNitComunidadEtnica().hashCode();

		if(getEstado() != null)
			_hashCode += getEstado().hashCode();

		if(getFechaInactivacionAlerta() != null)
			_hashCode += getFechaInactivacionAlerta().hashCode();

		if(getCreadoSNR() != null)
			_hashCode += getCreadoSNR().hashCode();

		if(getListaMatriculas() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaMatriculas()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaMatriculas(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getInactivacion() != null)
			_hashCode += getInactivacion().hashCode();

		if(getListaAlertasGeneradas() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaAlertasGeneradas()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaAlertasGeneradas(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getListaProcesosAS() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaProcesosAS()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaProcesosAS(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

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
