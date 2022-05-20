package com.bachue.snr.prosnr01.model.ui;

import com.bachue.snr.prosnr01.model.devolucion.ActoDevolucionDinero;
import com.bachue.snr.prosnr01.model.devolucion.DevolucionDinero;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.LiquidacionItemCertificado;

import java.io.Serializable;

import java.util.Collection;


/**
 * Clase modelo que contiene todos los atributos de DevolucionesDineroUI.
 *
 * @author ccalderon
 */
public class DevolucionDineroUI implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4609545365172177165L;

	/** Propiedad icadd actos devolución dinero. */
	private Collection<ActoDevolucionDinero> icadd_actosDevolucionDinero;

	/** Propiedad icdd_personaRepresentante. */
	private Collection<DevolucionDinero> icdd_personaRepresentante;

	/** Propiedad icdd_personasARepresentar. */
	private Collection<DevolucionDinero> icdd_personasARepresentar;

	/** Propiedad icl_liquidacion. */
	private Collection<Liquidacion> icl_liquidacion;

	/** Propiedad ics mensajes informativos. */
	private Collection<String> ics_mensajesInformativos;

	/** Propiedad ics mensajes pantalla. */
	private Collection<String> ics_mensajesPantalla;

	/** Propiedad idd devolucion dinero. */
	private DevolucionDinero idd_devolucionDinero;

	/** Propiedad ilic liquidacion item certificado. */
	private LiquidacionItemCertificado ilic_liquidacionItemCertificado;

	/** Propiedad is id devolución dinero. */
	private String is_idDevolucionDinero;

	/** Propiedad is idProceso. */
	private String is_idProceso;

	/** Propiedad is idProcesoAnterior. */
	private String is_idProcesoAnterior;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is idSubProceso. */
	private String is_idSubProceso;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is_intervieneTramiteDevDinero. */
	private String is_intervieneTramiteDevDinero;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is_observaciones. */
	private String is_observaciones;

	/** Propiedad is_titularCuentaDevDinero. */
	private String is_titularCuentaDevDinero;

	/**
	 * Modifica el valor de ActosDevolucionDinero.
	 *
	 * @param acadd_add de acadd add
	 */
	public void setActosDevolucionDinero(Collection<ActoDevolucionDinero> acadd_add)
	{
		icadd_actosDevolucionDinero = acadd_add;
	}

	/**
	 * Retorna Objeto o variable de valor actos devolucion dinero.
	 *
	 * @return Retorna el valor de la propiedad actosDevolucionDinero
	 */
	public Collection<ActoDevolucionDinero> getActosDevolucionDinero()
	{
		return icadd_actosDevolucionDinero;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param add_dd modifica el valor de la propiedad.
	 */
	public void setDevolucionDinero(DevolucionDinero add_dd)
	{
		idd_devolucionDinero = add_dd;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public DevolucionDinero getDevolucionDinero()
	{
		return idd_devolucionDinero;
	}

	/**
	 * Modifica el valor de IdDevolucionDinero.
	 *
	 * @param as_s de as s
	 */
	public void setIdDevolucionDinero(String as_s)
	{
		is_idDevolucionDinero = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id devolucion dinero.
	 *
	 * @return Retorna el valor de la propiedad idDevolucionDinero
	 */
	public String getIdDevolucionDinero()
	{
		return is_idDevolucionDinero;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param idProceso de id proceso
	 */
	public void setIdProceso(String idProceso)
	{
		is_idProceso = idProceso;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return Retorna el valor de la propiedad idProceso
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de IdProcesoAnterior.
	 *
	 * @param as_s de as s
	 */
	public void setIdProcesoAnterior(String as_s)
	{
		is_idProcesoAnterior = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso anterior.
	 *
	 * @return Retorna el valor de la propiedad idProcesoAnterior
	 */
	public String getIdProcesoAnterior()
	{
		return is_idProcesoAnterior;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s de as s
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return Retorna el valor de la propiedad idSolicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdSubProceso.
	 *
	 * @param idSubProceso de id sub proceso
	 */
	public void setIdSubProceso(String idSubProceso)
	{
		is_idSubProceso = idSubProceso;
	}

	/**
	 * Retorna Objeto o variable de valor id sub proceso.
	 *
	 * @return Retorna el valor de la propiedad idSubProceso
	 */
	public String getIdSubProceso()
	{
		return is_idSubProceso;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s de as s
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return Retorna el valor de la propiedad idTurno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad idTurnoHistoria
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idTurnoHistoria
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIntervieneTramiteDevDinero(String as_s)
	{
		is_intervieneTramiteDevDinero = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIntervieneTramiteDevDinero()
	{
		return is_intervieneTramiteDevDinero;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @param acl_cl retorna el valor de la propiedad.
	 */
	public void setLiquidacion(Collection<Liquidacion> acl_cl)
	{
		icl_liquidacion = acl_cl;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<Liquidacion> getLiquidacion()
	{
		return icl_liquidacion;
	}

	/**
	 * Modifica el valor de LiquidacionItemCertificado.
	 *
	 * @param alic_lic de alic lic
	 */
	public void setLiquidacionItemCertificado(LiquidacionItemCertificado alic_lic)
	{
		ilic_liquidacionItemCertificado = alic_lic;
	}

	/**
	 * Retorna Objeto o variable de valor liquidacion item certificado.
	 *
	 * @return Retorna el valor de la propiedad liquidacionItemCertificado
	 */
	public LiquidacionItemCertificado getLiquidacionItemCertificado()
	{
		return ilic_liquidacionItemCertificado;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param acs_cs modifica el valor de la propiedad.
	 */
	public void setMensajesInformativos(Collection<String> acs_cs)
	{
		ics_mensajesInformativos = acs_cs;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<String> getMensajesInformativos()
	{
		return ics_mensajesInformativos;
	}

	/**
	 * Modifica el valor de MensajesPantalla.
	 *
	 * @param as_s de as s
	 */
	public void setMensajesPantalla(Collection<String> as_s)
	{
		ics_mensajesPantalla = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor mensajes pantalla.
	 *
	 * @return Retorna el valor de la propiedad mensajesPantalla
	 */
	public Collection<String> getMensajesPantalla()
	{
		return ics_mensajesPantalla;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_s de as s
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return Retorna el valor de la propiedad nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param acdd_cdd modifica el valor de la propiedad.
	 */
	public void setPersonaRepresentante(Collection<DevolucionDinero> acdd_cdd)
	{
		icdd_personaRepresentante = acdd_cdd;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<DevolucionDinero> getPersonaRepresentante()
	{
		return icdd_personaRepresentante;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param acdd_cdd modifica el valor de la propiedad.
	 */
	public void setPersonasARepresentar(Collection<DevolucionDinero> acdd_cdd)
	{
		icdd_personasARepresentar = acdd_cdd;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<DevolucionDinero> getPersonasARepresentar()
	{
		return icdd_personasARepresentar;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setTitularCuentaDevDinero(String as_s)
	{
		is_titularCuentaDevDinero = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getTitularCuentaDevDinero()
	{
		return is_titularCuentaDevDinero;
	}
}
