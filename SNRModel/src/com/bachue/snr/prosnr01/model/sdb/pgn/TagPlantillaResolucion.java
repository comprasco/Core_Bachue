package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.notificaciones.PersonaNotificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;

import java.io.Serializable;

import java.util.Collection;


/**
 * Clase de manejo de tag plantilla resolucion.
 *
 * @author broa
 *
 */
public class TagPlantillaResolucion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1357722642118888637L;

	/**  Atributo iccrr_causalesRechazaRecurso. */
	private Collection<CausalRechazoRecurso> iccrr_causalesRechazaRecurso;

	/**  Atributo icl_listadoDias. */
	private Collection<Long> icl_listadoDias;

	/**  Atributo icpn_personaNotificacion. */
	private Collection<PersonaNotificacion> icpn_personaNotificacion;

	/**  Atributo icpn_resultadosNotificacion. */
	private Collection<PersonaNotificacion> icpn_resultadosNotificacion;

	/**  Atributo itd_tiposDocumentales. */
	private Collection<TipoDocumental> itd_tiposDocumentales;

	/**  Atributo iot_oficiosTexto. */
	private OficiosTexto iot_oficiosTexto;

	/**  Atributo iot_oficiosTexto 2. */
	private OficiosTexto iot_oficiosTexto2;

	/**  Atributo is_solicitud. */
	private Persona is_persona;

	/**  Atributo is_solicitud. */
	private Solicitud is_solicitud;

	/**  Atributo is_idPlantilla. */
	private String is_idPlantilla;

	/**  Atributo is_idTagPlantillaResolucion. */
	private String is_idTagPlantillaResolucion;

	/**  Atributo is_idTurno. */
	private String is_idTurno;

	/**  Atributo is_idTurnoHistoria. */
	private String is_idTurnoHistoria;

	/** Propiedad is id ubicacion salvado. */
	private String is_idUbicacionSalvado;

	/**  Atributo is_observaciones. */
	private String is_observaciones;

	/**  Atributo is_tag. */
	private String is_tag;

	/**  Atributo is_texto. */
	private String is_texto;

	/**  Atributo is_tipoArchivo. */
	private String is_tipoArchivo;

	/**  Atributo is_tipoArchivo2. */
	private String is_tipoArchivo2;

	/**  Atributo is_tipoDocumental. */
	private String is_tipoDocumental;

	/**  Atributo ls suspension. */
	private Suspension is_suspension;

	/**  Atributo iba_archivo. */
	private byte[] iba_archivo;

	/**  Atributo iba_archivo2. */
	private byte[] iba_archivo2;

	/**  Atributo ib masivo. */
	private boolean ib_masivo;

	/**  Atributo ib no salvar completitud documental. */
	private boolean ib_noSalvarCompletitudDocumental;

	/**  Atributo ib planeacion. */
	private boolean ib_planeacion;

	/**  Atributo ib ib_proyectar. */
	private boolean ib_proyectar;

	/**  Atributo ib recurso. */
	private boolean ib_recurso;

	/**  Atributo ib segunda instancia. */
	private boolean ib_segundaInstancia;

	/**  Atributo ib recurso. */
	private boolean ib_traslado;

	/**  Atributo il_idMotivo. */
	private long il_idEtapa;

	/**  Atributo il_idMotivo. */
	private long il_idMotivo;

	/**
	 * Instancia un nuevo objeto tag plantilla resolucion.
	 */
	public TagPlantillaResolucion()
	{
	}

	/**
	 * Instancia un nuevo objeto tag plantilla resolucion.
	 *
	 * @param as_idTurnoHistoria correspondiente al valor de id turno historia
	 */
	public TagPlantillaResolucion(String as_idTurnoHistoria)
	{
		setIdTurnoHistoria(as_idTurnoHistoria);
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param aba_ba Argumento encargado de modificar la propiedad.
	 */
	public void setArchivo(byte[] aba_ba)
	{
		iba_archivo = aba_ba;
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
	 * @param aba_ba Argumento encargado de modificar la propiedad.
	 */
	public void setArchivo2(byte[] aba_ba)
	{
		iba_archivo2 = aba_ba;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public byte[] getArchivo2()
	{
		return iba_archivo2;
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
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public long getIdEtapa()
	{
		return il_idEtapa;
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
	public void setIdTagPlantillaResolucion(String as_s)
	{
		is_idTagPlantillaResolucion = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdTagPlantillaResolucion()
	{
		return is_idTagPlantillaResolucion;
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
	 * @param is_idUbicacionSalvado Modifica el valor de la propiedad is_idUbicacionSalvado
	 */
	public void setIdUbicacionSalvado(String as_ius)
	{
		is_idUbicacionSalvado = as_ius;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idUbicacionSalvado
	 */
	public String getIdUbicacionSalvado()
	{
		return is_idUbicacionSalvado;
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
	 * @param Modifica el valor de la propiedad masivo por masivo
	 */
	public void setMasivo(boolean ab_b)
	{
		ib_masivo = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad masivo
	 */
	public boolean isMasivo()
	{
		return ib_masivo;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad noSalvarCompletitudDocumental
	 */
	public void setNoSalvarCompletitudDocumental(boolean ab_b)
	{
		ib_noSalvarCompletitudDocumental = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad noSalvarCompletitudDocumental
	 */
	public boolean isNoSalvarCompletitudDocumental()
	{
		return ib_noSalvarCompletitudDocumental;
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
	 * @param aot_ot Argumento encargado de modificar la propiedad.
	 */
	public void setOficiosTexto2(OficiosTexto aot_ot)
	{
		iot_oficiosTexto2 = aot_ot;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public OficiosTexto getOficiosTexto2()
	{
		return iot_oficiosTexto2;
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
	 * @param Modifica el valor de la propiedad planeacion por planeacion
	 */
	public void setPlaneacion(boolean ab_b)
	{
		ib_planeacion = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad planeacion
	 */
	public boolean isPlaneacion()
	{
		return ib_planeacion;
	}

	/**
	 * @param Modifica el valor de la propiedad proyectar
	 */
	public void setProyectar(boolean ab_b)
	{
		ib_proyectar = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad proyectar
	 */
	public boolean isProyectar()
	{
		return ib_proyectar;
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
	 * @param Modifica el valor de la propiedad segundaInstancia por segundaInstancia
	 */
	public void setSegundaInstancia(boolean ab_b)
	{
		ib_segundaInstancia = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad segundaInstancia
	 */
	public boolean isSegundaInstancia()
	{
		return ib_segundaInstancia;
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
	 * @param Modifica el valor de la propiedad suspension por suspension
	 */
	public void setSuspension(Suspension as_s)
	{
		is_suspension = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad suspension
	 */
	public Suspension getSuspension()
	{
		return is_suspension;
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
	public void setTipoArchivo2(String as_s)
	{
		is_tipoArchivo2 = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getTipoArchivo2()
	{
		return is_tipoArchivo2;
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
	 * @param atd_td de atd td
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

	/**
	 * @param Modifica el valor de la propiedad traslado por traslado
	 */
	public void setTraslado(boolean ab_b)
	{
		ib_traslado = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad traslado
	 */
	public boolean isTraslado()
	{
		return ib_traslado;
	}
}
