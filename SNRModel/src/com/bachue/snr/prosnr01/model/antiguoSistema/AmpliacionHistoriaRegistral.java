package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;

import java.io.Serializable;

import java.util.List;
import java.util.Map;



/**
 * Class que contiene todos las propiedades AmpliacionHistoriaRegistral.
 *
 * @author Julian Vaca
 */
public class AmpliacionHistoriaRegistral implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4386860057291251228L;

	/** Propiedad iapr acc predio registro. */
	private AccPredioRegistro iapr_accPredioRegistro;

	/** Propiedad ill documentos. */
	private List<Map<String, Object>> ill_documentos;

	/** Propiedad ill info antiguo sistema. */
	private List<Map<String, Object>> ill_infoAntiguoSistema;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad is data matricula creada grabada. */
	private String is_dataMatriculaCreadaGrabada;

	/** Propiedad is id circulo destino. */
	private String is_idCirculoDestino;

	/** Propiedad is id circulo origen. */
	private String is_idCirculoOrigen;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad ls id usurio. */
	private String ls_idUsurio;

	/** Propiedad ib salvar ampliacion. */
	private boolean ib_salvarAmpliacion;

	/** Propiedad ib ver pdf. */
	private boolean ib_verPdf;

	/**
	 * Modifica el valor de acc predio registro.
	 *
	 * @param aapr_apr asigna el valor a la propiedad acc predio registro
	 */
	public void setAccPredioRegistro(AccPredioRegistro aapr_apr)
	{
		iapr_accPredioRegistro = aapr_apr;
	}

	/**
	 * Retorna el valor de acc predio registro.
	 *
	 * @return el valor de acc predio registro
	 */
	public AccPredioRegistro getAccPredioRegistro()
	{
		if(iapr_accPredioRegistro == null)
			iapr_accPredioRegistro = new AccPredioRegistro();

		return iapr_accPredioRegistro;
	}

	/**
	 * Modifica el valor de data matricula creada grabada.
	 *
	 * @param as_s asigna el valor a la propiedad data matricula creada grabada
	 */
	public void setDataMatriculaCreadaGrabada(String as_s)
	{
		is_dataMatriculaCreadaGrabada = as_s;
	}

	/**
	 * Retorna el valor de data matricula creada grabada.
	 *
	 * @return el valor de data matricula creada grabada
	 */
	public String getDataMatriculaCreadaGrabada()
	{
		return is_dataMatriculaCreadaGrabada;
	}

	/**
	 * Sets the documentos.
	 *
	 * @param all_lhm de all lhm
	 */
	public void setDocumentos(List<Map<String, Object>> all_lhm)
	{
		ill_documentos = all_lhm;
	}

	/**
	 * Retorna el valor de documentos.
	 *
	 * @return el valor de documentos
	 */
	public List<Map<String, Object>> getDocumentos()
	{
		return ill_documentos;
	}

	/**
	 * Modifica el valor de id circulo destino.
	 *
	 * @param idCirculoDestino asigna el valor a la propiedad id circulo destino
	 */
	public void setIdCirculoDestino(String idCirculoDestino)
	{
		is_idCirculoDestino = idCirculoDestino;
	}

	/**
	 * Retorna el valor de id circulo destino.
	 *
	 * @return el valor de id circulo destino
	 */
	public String getIdCirculoDestino()
	{
		return is_idCirculoDestino;
	}

	/**
	 * Modifica el valor de id circulo origen.
	 *
	 * @param idCirculoOrigen asigna el valor a la propiedad id circulo origen
	 */
	public void setIdCirculoOrigen(String idCirculoOrigen)
	{
		is_idCirculoOrigen = idCirculoOrigen;
	}

	/**
	 * Retorna el valor de id circulo origen.
	 *
	 * @return el valor de id circulo origen
	 */
	public String getIdCirculoOrigen()
	{
		return is_idCirculoOrigen;
	}

	/**
	 * Modifica el valor de id solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param al_l asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de id usurio.
	 *
	 * @param as_S asigna el valor a la propiedad id usurio
	 */
	public void setIdUsurio(String as_S)
	{
		ls_idUsurio = as_S;
	}

	/**
	 * Retorna el valor de id usurio.
	 *
	 * @return el valor de id usurio
	 */
	public String getIdUsurio()
	{
		return ls_idUsurio;
	}

	/**
	 * Sets the info antiguo sistema.
	 *
	 * @param all_lhm de all lhm
	 */
	public void setInfoAntiguoSistema(List<Map<String, Object>> all_lhm)
	{
		ill_infoAntiguoSistema = all_lhm;
	}

	/**
	 * Retorna el valor de info antiguo sistema.
	 *
	 * @return el valor de info antiguo sistema
	 */
	public List<Map<String, Object>> getInfoAntiguoSistema()
	{
		return ill_infoAntiguoSistema;
	}

	/**
	 * Modifica el valor de observaciones.
	 *
	 * @param observaciones asigna el valor a la propiedad observaciones
	 */
	public void setObservaciones(String observaciones)
	{
		is_observaciones = observaciones;
	}

	/**
	 * Retorna el valor de observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de salvar ampliacion.
	 *
	 * @param salvarAmpliacion asigna el valor a la propiedad salvar ampliacion
	 */
	public void setSalvarAmpliacion(boolean salvarAmpliacion)
	{
		ib_salvarAmpliacion = salvarAmpliacion;
	}

	/**
	 * Valida la propiedad salvar ampliacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en salvar ampliacion
	 */
	public boolean isSalvarAmpliacion()
	{
		return ib_salvarAmpliacion;
	}

	/**
	 * Modifica el valor de ver pdf.
	 *
	 * @param verPdf asigna el valor a la propiedad ver pdf
	 */
	public void setVerPdf(boolean verPdf)
	{
		ib_verPdf = verPdf;
	}

	/**
	 * Valida la propiedad ver pdf.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ver pdf
	 */
	public boolean isVerPdf()
	{
		return ib_verPdf;
	}
}
