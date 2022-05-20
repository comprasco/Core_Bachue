package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;

import java.io.Serializable;

import java.util.Collection;


/**
 * Clase modelo que contiene todos los atributos de CriteriosDeBusqueda.
 *
 * @author hcastaneda
 */
public class CriteriosDeBusqueda extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -882800787084092822L;

	/** Propiedad iccb criterios seleccionados. */
	private Collection<CriteriosDeBusqueda> iccb_criteriosSeleccionados;

	/** Propiedad il cantidad consultas. */
	private Long il_cantidadConsultas;

	/** Propiedad is datos solicitud. */
	private Solicitud is_datosSolicitud;

	/** Propiedad is consulta nacional. */
	private String is_consultaNacional;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is etiqueta campo. */
	private String is_etiquetaCampo;

	/** Propiedad is id campo criterio busqueda. */
	private String is_idCampoCriterioBusqueda;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id detalle criterio busqueda. */
	private String is_idDetalleCriterioBusqueda;

	/** Propiedad is id proceso consulta. */
	private String is_idProcesoConsulta;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id tipo busqueda. */
	private String is_idTipoBusqueda;

	/** Propiedad is id tipo criterio. */
	private String is_idTipoCriterio;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is regional. */
	private String is_regional;

	/** Propiedad is tipo busqueda. */
	private String is_tipoBusqueda;

	/** Propiedad is tipo consulta. */
	private String is_tipoConsulta;

	/** Propiedad is valor campo. */
	private String is_valorCampo;

	/** Propiedad ib archivo pdf. */
	private byte[] ib_archivoPdf;

	/** Propiedad ib pdf liquidacion. */
	private byte[] ib_pdfLiquidacion;

	/** Propiedad ib con documentos OWCC. */
	private boolean ib_conDocumentosOWCC;

	/** Propiedad ib consulta nacional. */
	private boolean ib_consultaNacional;

	/** Propiedad ib copias. */
	private boolean ib_copias;

	/** Propiedad ib seleccion. */
	private boolean ib_seleccion;

	/** Propiedad ib sin cantidad. */
	private boolean ib_sinCantidad;

	/** Propiedad il consecutivo. */
	private long il_consecutivo;

	/** Propiedad il consecutivo consulta detalle. */
	private long il_consecutivoConsultaDetalle;

	/**
	 * Modifica el valor de ArchivoPdf.
	 *
	 * @param archivoPdf asigna el valor a la propiedad
	 */
	public void setArchivoPdf(byte[] archivoPdf)
	{
		this.ib_archivoPdf = archivoPdf;
	}

	/**
	 * Retorna Objeto o variable de valor archivo pdf.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public byte[] getArchivoPdf()
	{
		return ib_archivoPdf;
	}

	/**
	 * Modifica el valor de CantidadConsultas.
	 *
	 * @param cantidadConsultas asigna el valor a la propiedad
	 */
	public void setCantidadConsultas(Long cantidadConsultas)
	{
		this.il_cantidadConsultas = cantidadConsultas;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad consultas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getCantidadConsultas()
	{
		return il_cantidadConsultas;
	}

	/**
	 * Modifica el valor de ConDocumentosOWCC.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setConDocumentosOWCC(boolean ab_b)
	{
		ib_conDocumentosOWCC = ab_b;
	}

	/**
	 * Valida la propiedad con documentos OWCC.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isConDocumentosOWCC()
	{
		return ib_conDocumentosOWCC;
	}

	/**
	 * Modifica el valor de Consecutivo.
	 *
	 * @param consecutivo asigna el valor a la propiedad
	 */
	public void setConsecutivo(long consecutivo)
	{
		this.il_consecutivo = consecutivo;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getConsecutivo()
	{
		return il_consecutivo;
	}

	/**
	 * Modifica el valor de ConsecutivoConsultaDetalle.
	 *
	 * @param consecutivoConsultaDetalle asigna el valor a la propiedad
	 */
	public void setConsecutivoConsultaDetalle(long consecutivoConsultaDetalle)
	{
		this.il_consecutivoConsultaDetalle = consecutivoConsultaDetalle;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo consulta detalle.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getConsecutivoConsultaDetalle()
	{
		return il_consecutivoConsultaDetalle;
	}

	/**
	 * Modifica el valor de ConsultaNacional.
	 *
	 * @param consultaNacional asigna el valor a la propiedad
	 */
	public void setConsultaNacional(String consultaNacional)
	{
		this.is_consultaNacional = consultaNacional;
	}

	/**
	 * Modifica el valor de ConsultaNacional.
	 *
	 * @param consultaNacional asigna el valor a la propiedad
	 */
	public void setConsultaNacional(boolean consultaNacional)
	{
		this.ib_consultaNacional = consultaNacional;
	}

	/**
	 * Retorna Objeto o variable de valor consulta nacional.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getConsultaNacional()
	{
		return is_consultaNacional;
	}

	/**
	 * Valida la propiedad consulta nacional.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isConsultaNacional()
	{
		return ib_consultaNacional;
	}

	/**
	 * Modifica el valor de Copias.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setCopias(boolean ab_b)
	{
		ib_copias = ab_b;
	}

	/**
	 * Valida la propiedad copias.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isCopias()
	{
		return ib_copias;
	}

	/**
	 * Modifica el valor de CriteriosSeleccionados.
	 *
	 * @param criteriosSeleccionados asigna el valor a la propiedad
	 */
	public void setCriteriosSeleccionados(Collection<CriteriosDeBusqueda> criteriosSeleccionados)
	{
		this.iccb_criteriosSeleccionados = criteriosSeleccionados;
	}

	/**
	 * Retorna Objeto o variable de valor criterios seleccionados.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<CriteriosDeBusqueda> getCriteriosSeleccionados()
	{
		return iccb_criteriosSeleccionados;
	}

	/**
	 * Modifica el valor de DatosSolicitud.
	 *
	 * @param datosSolicitud asigna el valor a la propiedad
	 */
	public void setDatosSolicitud(Solicitud datosSolicitud)
	{
		this.is_datosSolicitud = datosSolicitud;
	}

	/**
	 * Retorna Objeto o variable de valor datos solicitud.
	 *
	 * @return el valor de datos solicitud
	 */
	public Solicitud getDatosSolicitud()
	{
		if(is_datosSolicitud == null)
			is_datosSolicitud = new Solicitud();

		return is_datosSolicitud;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param descripcion asigna el valor a la propiedad
	 */
	public void setDescripcion(String descripcion)
	{
		this.is_descripcion = descripcion;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param estado asigna el valor a la propiedad
	 */
	public void setEstado(String estado)
	{
		this.is_estado = estado;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de EtiquetaCampo.
	 *
	 * @param etiquetaCampo asigna el valor a la propiedad
	 */
	public void setEtiquetaCampo(String etiquetaCampo)
	{
		this.is_etiquetaCampo = etiquetaCampo;
	}

	/**
	 * Retorna Objeto o variable de valor etiqueta campo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEtiquetaCampo()
	{
		return is_etiquetaCampo;
	}

	/**
	 * Modifica el valor de IdCampoCriterioBusqueda.
	 *
	 * @param idCampoCriterioBusqueda asigna el valor a la propiedad
	 */
	public void setIdCampoCriterioBusqueda(String idCampoCriterioBusqueda)
	{
		this.is_idCampoCriterioBusqueda = idCampoCriterioBusqueda;
	}

	/**
	 * Retorna Objeto o variable de valor id campo criterio busqueda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCampoCriterioBusqueda()
	{
		return is_idCampoCriterioBusqueda;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param idCirculo asigna el valor a la propiedad
	 */
	public void setIdCirculo(String idCirculo)
	{
		this.is_idCirculo = idCirculo;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdDetalleCriterioBusqueda.
	 *
	 * @param idDetalleCriterioBusqueda asigna el valor a la propiedad
	 */
	public void setIdDetalleCriterioBusqueda(String idDetalleCriterioBusqueda)
	{
		this.is_idDetalleCriterioBusqueda = idDetalleCriterioBusqueda;
	}

	/**
	 * Retorna Objeto o variable de valor id detalle criterio busqueda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDetalleCriterioBusqueda()
	{
		return is_idDetalleCriterioBusqueda;
	}

	/**
	 * Modifica el valor de IdProcesoConsulta.
	 *
	 * @param idProcesoConsulta asigna el valor a la propiedad
	 */
	public void setIdProcesoConsulta(String idProcesoConsulta)
	{
		this.is_idProcesoConsulta = idProcesoConsulta;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProcesoConsulta()
	{
		return is_idProcesoConsulta;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param idSolicitud asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String idSolicitud)
	{
		this.is_idSolicitud = idSolicitud;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdTipoBusqueda.
	 *
	 * @param idTipoBusqueda asigna el valor a la propiedad
	 */
	public void setIdTipoBusqueda(String idTipoBusqueda)
	{
		this.is_idTipoBusqueda = idTipoBusqueda;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo busqueda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoBusqueda()
	{
		return is_idTipoBusqueda;
	}

	/**
	 * Modifica el valor de IdTipoCriterio.
	 *
	 * @param idTipoCriterio asigna el valor a la propiedad
	 */
	public void setIdTipoCriterio(String idTipoCriterio)
	{
		this.is_idTipoCriterio = idTipoCriterio;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo criterio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoCriterio()
	{
		return is_idTipoCriterio;
	}

	/**
	 * @param is_observaciones Modifica el valor de la propiedad is_observaciones
	 */
	public void setObservaciones(String as_o)
	{
		is_observaciones = as_o;
	}

	/**
	 * @return Retorna el valor de la propiedad is_observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de PdfLiquidacion.
	 *
	 * @param pdfLiquidacion asigna el valor a la propiedad
	 */
	public void setPdfLiquidacion(byte[] pdfLiquidacion)
	{
		this.ib_pdfLiquidacion = pdfLiquidacion;
	}

	/**
	 * Retorna Objeto o variable de valor pdf liquidacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public byte[] getPdfLiquidacion()
	{
		return ib_pdfLiquidacion;
	}

	/**
	 * Modifica el valor de Regional.
	 *
	 * @param regional asigna el valor a la propiedad
	 */
	public void setRegional(String regional)
	{
		this.is_regional = regional;
	}

	/**
	 * Retorna Objeto o variable de valor regional.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRegional()
	{
		return is_regional;
	}

	/**
	 * Modifica el valor de Seleccion.
	 *
	 * @param seleccion asigna el valor a la propiedad
	 */
	public void setSeleccion(boolean seleccion)
	{
		this.ib_seleccion = seleccion;
	}

	/**
	 * Valida la propiedad seleccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSeleccion()
	{
		return ib_seleccion;
	}

	/**
	 * Modifica el valor de SinCantidad.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setSinCantidad(boolean ab_b)
	{
		ib_sinCantidad = ab_b;
	}

	/**
	 * Valida la propiedad sin cantidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSinCantidad()
	{
		return ib_sinCantidad;
	}

	/**
	 * Modifica el valor de TipoBusqueda.
	 *
	 * @param tipoBusqueda asigna el valor a la propiedad
	 */
	public void setTipoBusqueda(String tipoBusqueda)
	{
		this.is_tipoBusqueda = tipoBusqueda;
	}

	/**
	 * Retorna Objeto o variable de valor tipo busqueda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoBusqueda()
	{
		return is_tipoBusqueda;
	}

	/**
	 * Modifica el valor de TipoConsulta.
	 *
	 * @param tipoConsulta asigna el valor a la propiedad
	 */
	public void setTipoConsulta(String tipoConsulta)
	{
		this.is_tipoConsulta = tipoConsulta;
	}

	/**
	 * Retorna Objeto o variable de valor tipo consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoConsulta()
	{
		return is_tipoConsulta;
	}

	/**
	 * Modifica el valor de ValorCampo.
	 *
	 * @param valorCampo asigna el valor a la propiedad
	 */
	public void setValorCampo(String valorCampo)
	{
		this.is_valorCampo = valorCampo;
	}

	/**
	 * Retorna Objeto o variable de valor valor campo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getValorCampo()
	{
		return is_valorCampo;
	}
}
