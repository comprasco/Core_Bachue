package com.bachue.snr.prosnr01.model.calificacion;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Map;



/**
 * Class que contiene todos las propiedades Calificacion.
 *
 * @author hcastaneda
 */
public class Calificacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3232000592217194293L;

	/** Propiedad il id motivo. */
	private Long il_idMotivo;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad ihm tabs. */
	private Map<String, Object> ihm_tabs;

	/** Propiedad is campo. */
	private String is_campo;

	/** Propiedad is estado modificacion. */
	private String is_estadoModificacion;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is justificacion. */
	private String is_justificacion;

	/** Propiedad is motivo. */
	private String is_motivo;

	/** Propiedad ib departamento oficina. */
	private boolean ib_departamentoOficina;

	/** Propiedad ib es predio inconsistente. */
	private boolean ib_esPredioInconsistente;

	/** Propiedad ib fecha doc. */
	private boolean ib_fechaDoc;

	/** Propiedad ib fecha ejecutoria. */
	private boolean ib_fechaEjecutoria;

	/** Propiedad ib mod datos documento. */
	private boolean ib_modDatosDocumento;

	/** Propiedad ib municipio oficina. */
	private boolean ib_municipioOficina;

	/** Propiedad ib numero doc. */
	private boolean ib_numeroDoc;

	/** Propiedad ib oficina origen. */
	private boolean ib_oficinaOrigen;

	/** Propiedad ib pais. */
	private boolean ib_pais;

	/** Propiedad ib tipo documento. */
	private boolean ib_tipoDocumento;

	/** Propiedad ib tipo entidad. */
	private boolean ib_tipoEntidad;

	/** Propiedad ib tipo oficina. */
	private boolean ib_tipoOficina;

	/**
	 * Modifica el valor de campo.
	 *
	 * @param as_s asigna el valor a la propiedad campo
	 */
	public void setCampo(String as_s)
	{
		is_campo = as_s;
	}

	/**
	 * Retorna el valor de campo.
	 *
	 * @return el valor de campo
	 */
	public String getCampo()
	{
		return is_campo;
	}

	/**
	 * Modifica el valor de departamento oficina.
	 *
	 * @param ab_b asigna el valor a la propiedad departamento oficina
	 */
	public void setDepartamentoOficina(boolean ab_b)
	{
		ib_departamentoOficina = ab_b;
	}

	/**
	 * Valida la propiedad departamento oficina.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en departamento oficina
	 */
	public boolean isDepartamentoOficina()
	{
		return ib_departamentoOficina;
	}

	/**
	 * Modifica el valor de EsPredioInconsistente.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsPredioInconsistente(boolean ab_b)
	{
		ib_esPredioInconsistente = ab_b;
	}

	/**
	 * Valida la propiedad es predio inconsistente.
	 *
	 * @return Retorna el valor de la propiedad esPredioInconsistente
	 */
	public boolean isEsPredioInconsistente()
	{
		return ib_esPredioInconsistente;
	}

	/**
	 * Modifica el valor de estado modificacion.
	 *
	 * @param as_s asigna el valor a la propiedad estado modificacion
	 */
	public void setEstadoModificacion(String as_s)
	{
		is_estadoModificacion = as_s;
	}

	/**
	 * Retorna el valor de estado modificacion.
	 *
	 * @return el valor de estado modificacion
	 */
	public String getEstadoModificacion()
	{
		return is_estadoModificacion;
	}

	/**
	 * Modifica el valor de fecha doc.
	 *
	 * @param ab_b asigna el valor a la propiedad fecha doc
	 */
	public void setFechaDoc(boolean ab_b)
	{
		ib_fechaDoc = ab_b;
	}

	/**
	 * Valida la propiedad fecha doc.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en fecha doc
	 */
	public boolean isFechaDoc()
	{
		return ib_fechaDoc;
	}

	/**
	 * Modifica el valor de fecha ejecutoria.
	 *
	 * @param ab_b asigna el valor a la propiedad fecha ejecutoria
	 */
	public void setFechaEjecutoria(boolean ab_b)
	{
		ib_fechaEjecutoria = ab_b;
	}

	/**
	 * Valida la propiedad fecha ejecutoria.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en fecha ejecutoria
	 */
	public boolean isFechaEjecutoria()
	{
		return ib_fechaEjecutoria;
	}

	/**
	 * Modifica el valor de id motivo.
	 *
	 * @param al_l asigna el valor a la propiedad id motivo
	 */
	public void setIdMotivo(Long al_l)
	{
		il_idMotivo = al_l;
	}

	/**
	 * Retorna el valor de id motivo.
	 *
	 * @return el valor de id motivo
	 */
	public Long getIdMotivo()
	{
		return il_idMotivo;
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
	 * @param as_s asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(Long as_s)
	{
		il_idTurnoHistoria = as_s;
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
	 * Modifica el valor de justificacion.
	 *
	 * @param as_s asigna el valor a la propiedad justificacion
	 */
	public void setJustificacion(String as_s)
	{
		is_justificacion = as_s;
	}

	/**
	 * Retorna el valor de justificacion.
	 *
	 * @return el valor de justificacion
	 */
	public String getJustificacion()
	{
		return is_justificacion;
	}

	/**
	 * Modifica el valor de mod datos documento.
	 *
	 * @param ab_b asigna el valor a la propiedad mod datos documento
	 */
	public void setModDatosDocumento(boolean ab_b)
	{
		ib_modDatosDocumento = ab_b;
	}

	/**
	 * Valida la propiedad mod datos documento.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mod datos documento
	 */
	public boolean isModDatosDocumento()
	{
		if(
		    isTipoDocumento() || isNumeroDoc() || isFechaDoc() || isTipoOficina() || isPais()
			    || isDepartamentoOficina() || isMunicipioOficina() || isOficinaOrigen() || isFechaEjecutoria()
			    || isTipoEntidad()
		)
			ib_modDatosDocumento = true;

		return ib_modDatosDocumento;
	}

	/**
	 * Modifica el valor de motivo.
	 *
	 * @param motivo asigna el valor a la propiedad motivo
	 */
	public void setMotivo(String motivo)
	{
		this.is_motivo = motivo;
	}

	/**
	 * Retorna el valor de motivo.
	 *
	 * @return el valor de motivo
	 */
	public String getMotivo()
	{
		return is_motivo;
	}

	/**
	 * Modifica el valor de municipio oficina.
	 *
	 * @param ab_b asigna el valor a la propiedad municipio oficina
	 */
	public void setMunicipioOficina(boolean ab_b)
	{
		ib_municipioOficina = ab_b;
	}

	/**
	 * Valida la propiedad municipio oficina.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en municipio oficina
	 */
	public boolean isMunicipioOficina()
	{
		return ib_municipioOficina;
	}

	/**
	 * Modifica el valor de numero doc.
	 *
	 * @param ab_b asigna el valor a la propiedad numero doc
	 */
	public void setNumeroDoc(boolean ab_b)
	{
		ib_numeroDoc = ab_b;
	}

	/**
	 * Valida la propiedad numero doc.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en numero doc
	 */
	public boolean isNumeroDoc()
	{
		return ib_numeroDoc;
	}

	/**
	 * Modifica el valor de oficina origen.
	 *
	 * @param ab_b asigna el valor a la propiedad oficina origen
	 */
	public void setOficinaOrigen(boolean ab_b)
	{
		ib_oficinaOrigen = ab_b;
	}

	/**
	 * Valida la propiedad oficina origen.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en oficina origen
	 */
	public boolean isOficinaOrigen()
	{
		return ib_oficinaOrigen;
	}

	/**
	 * Modifica el valor de pais.
	 *
	 * @param pais asigna el valor a la propiedad pais
	 */
	public void setPais(boolean pais)
	{
		this.ib_pais = pais;
	}

	/**
	 * Valida la propiedad pais.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pais
	 */
	public boolean isPais()
	{
		return ib_pais;
	}

	/**
	 * Sets the tabs.
	 *
	 * @param tabs de tabs
	 */
	public void setTabs(Map<String, Object> tabs)
	{
		this.ihm_tabs = tabs;
	}

	/**
	 * Retorna el valor de tabs.
	 *
	 * @return el valor de tabs
	 */
	public Map<String, Object> getTabs()
	{
		return ihm_tabs;
	}

	/**
	 * Modifica el valor de tipo documento.
	 *
	 * @param ab_b asigna el valor a la propiedad tipo documento
	 */
	public void setTipoDocumento(boolean ab_b)
	{
		ib_tipoDocumento = ab_b;
	}

	/**
	 * Valida la propiedad tipo documento.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en tipo documento
	 */
	public boolean isTipoDocumento()
	{
		return ib_tipoDocumento;
	}

	/**
	 * Modifica el valor de tipo entidad.
	 *
	 * @param ab_b asigna el valor a la propiedad tipo entidad
	 */
	public void setTipoEntidad(boolean ab_b)
	{
		ib_tipoEntidad = ab_b;
	}

	/**
	 * Valida la propiedad tipo entidad.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en tipo entidad
	 */
	public boolean isTipoEntidad()
	{
		return ib_tipoEntidad;
	}

	/**
	 * Modifica el valor de tipo oficina.
	 *
	 * @param ab_b asigna el valor a la propiedad tipo oficina
	 */
	public void setTipoOficina(boolean ab_b)
	{
		ib_tipoOficina = ab_b;
	}

	/**
	 * Valida la propiedad tipo oficina.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en tipo oficina
	 */
	public boolean isTipoOficina()
	{
		return ib_tipoOficina;
	}
}
