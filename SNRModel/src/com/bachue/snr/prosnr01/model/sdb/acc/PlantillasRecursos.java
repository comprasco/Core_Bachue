package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.notificaciones.PersonaNotificacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalRechazoRecurso;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import java.io.Serializable;

import java.util.Collection;


/**
 * Clase de manejo de plantillas recursos.
 *
 * @author hcastaneda
 *
 */
public class PlantillasRecursos extends Auditoria implements Serializable
{
	private static final long serialVersionUID = -8242972648361949252L;

	/**  Atributo iccrr_causalesRechazaRecurso. */
	private Collection<CausalRechazoRecurso> iccrr_causalesRechazaRecurso;

	/**  Atributo icl_listadoDias. */
	private Collection<Long> icl_listadoDias;

	/**  Atributo icpn_personaNotificacion. */
	private Collection<PersonaNotificacion> icpn_personaNotificacion;

	/**  Atributo icpn_resultadosNotificacion. */
	private Collection<PersonaNotificacion> icpn_resultadosNotificacion;

	/** Atributo itd_tiposDocumentales */
	private Collection<TipoDocumental> itd_tiposDocumentales;

	/**  Atributo iot_oficiosTexto. */
	private OficiosTexto iot_oficiosTexto;

	/**  Atributo is_solicitud. */
	private Persona is_persona;

	/**  Atributo is_solicitud. */
	private Solicitud is_solicitud;

	/**  Atributo is_idPlantilla. */
	private String is_idPlantilla;

	/**  Atributo is_idPlantillasRecursos. */
	private String is_idPlantillasRecursos;

	/**  Atributo is_idTurno. */
	private String is_idTurno;

	/**  Atributo is_idTurnoHistoria. */
	private String is_idTurnoHistoria;

	/**  Atributo is_observaciones. */
	private String is_observaciones;

	/**  Atributo is_tag. */
	private String is_tag;

	/**  Atributo is_texto. */
	private String is_texto;

	/**  Atributo is_tipoArchivo. */
	private String is_tipoArchivo;

	/**  Atributo is_tipoDocumental. */
	private String is_tipoDocumental;

	/**  Atributo iba_archivo. */
	private byte[] iba_archivo;

	/**  Atributo ib recurso. */
	private boolean ib_recurso;

	/**  Atributo il_idMotivo. */
	private long il_idMotivo;

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param aba_ba Argumento encargado de modificar la propiedad.
	 */
	public void setArchivo(byte[] aba_ba)
	{
		iba_archivo                            = aba_ba;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public byte[] getArchivo()
	{
		return iba_archivo;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param accrr_ccrr Argumento encargado de modificar la propiedad.
	 */
	public void setCausalesRechazaRecurso(Collection<CausalRechazoRecurso> accrr_ccrr)
	{
		iccrr_causalesRechazaRecurso = accrr_ccrr;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<CausalRechazoRecurso> getCausalesRechazaRecurso()
	{
		return iccrr_causalesRechazaRecurso;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param al_l Argumento encargado de modificar la propiedad.
	 */
	public void setIdMotivo(long al_l)
	{
		il_idMotivo = al_l;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public long getIdMotivo()
	{
		return il_idMotivo;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdPlantilla(String as_s)
	{
		is_idPlantilla = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdPlantilla()
	{
		return is_idPlantilla;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setIdPlantillasRecurso(String as_s)
	{
		is_idPlantillasRecursos = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdPlantillasRecursos()
	{
		return is_idPlantillasRecursos;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param acl_cl Argumento encargado de modificar la propiedad.
	 */
	public void setListadoDias(Collection<Long> acl_cl)
	{
		icl_listadoDias = acl_cl;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<Long> getListadoDias()
	{
		return icl_listadoDias;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param aot_ot Argumento encargado de modificar la propiedad.
	 */
	public void setOficiosTexto(OficiosTexto aot_ot)
	{
		iot_oficiosTexto = aot_ot;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public OficiosTexto getOficiosTexto()
	{
		return iot_oficiosTexto;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param ap_p Argumento encargado de modificar la propiedad.
	 */
	public void setPersona(Persona ap_p)
	{
		is_persona = ap_p;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Persona getPersona()
	{
		return is_persona;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param acpn_pn de acpn pn
	 */
	public void setPersonaNotificacion(Collection<PersonaNotificacion> acpn_pn)
	{
		icpn_personaNotificacion = acpn_pn;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<PersonaNotificacion> getPersonaNotificacion()
	{
		return icpn_personaNotificacion;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param ab_b Argumento encargado de modificar la propiedad.
	 */
	public void setRecurso(boolean ab_b)
	{
		ib_recurso = ab_b;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isRecurso()
	{
		return ib_recurso;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param acpn_cpn Argumento encargado de modificar la propiedad.
	 */
	public void setResultadosNotificacion(Collection<PersonaNotificacion> acpn_cpn)
	{
		icpn_resultadosNotificacion = acpn_cpn;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<PersonaNotificacion> getResultadosNotificacion()
	{
		return icpn_resultadosNotificacion;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setSolicitud(Solicitud as_s)
	{
		is_solicitud = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Solicitud getSolicitud()
	{
		return is_solicitud;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setTag(String as_s)
	{
		is_tag = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getTag()
	{
		return is_tag;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo.
	 *
	 * @param as_s modifica el valor del atributo.
	 */
	public void setTexto(String as_s)
	{
		is_texto = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getTexto()
	{
		return is_texto;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setTipoArchivo(String as_s)
	{
		is_tipoArchivo = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getTipoArchivo()
	{
		return is_tipoArchivo;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setTipoDocumental(String as_s)
	{
		is_tipoDocumental = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getTipoDocumental()
	{
		return is_tipoDocumental;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param aba_ba Argumento encargado de modificar la propiedad.
	 */
	public void setTiposDocumentales(Collection<TipoDocumental> atd_td)
	{
		itd_tiposDocumentales = atd_td;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<TipoDocumental> getTiposDocumentales()
	{
		return itd_tiposDocumentales;
	}
}
