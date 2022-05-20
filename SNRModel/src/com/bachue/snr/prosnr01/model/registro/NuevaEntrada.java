package com.bachue.snr.prosnr01.model.registro;

import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudDireccionCertificado;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoDerivado;

import java.io.Serializable;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades NuevaEntrada.
 *
 * @author garias
 */
public class NuevaEntrada implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2935020489790678672L;

	/** Propiedad icsm solicitud matricula nueva entrada. */
	private Collection<SolicitudMatricula> icsm_solicitudMatriculaNuevaEntrada;

	/** Propiedad ict turnos recurrencia. */
	private Collection<Turno> ict_turnosRecurrencia;

	/** Propiedad ictd turnos derivados. */
	private Collection<TurnoDerivado> ictd_turnosDerivados;

	/** Propiedad idas datos ant sistema nueva entrada. */
	private DatosAntSistema idas_datosAntSistemaNuevaEntrada;

	/** Propiedad ip persona sistema nueva entrada. */
	private Persona ip_personaNuevaEntrada;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/** Propiedad isa solicitud asociada. */
	private SolicitudAsociada isa_solicitudAsociada;

	/** Propiedad isdc solicitud direccion certificado nueva entrada. */
	private SolicitudDireccionCertificado isdc_solicitudDireccionCertificadoNuevaEntrada;

	/** Propiedad ib no etapa 31. */
	private boolean ib_noEtapa31;

	/** Propiedad ib no etapa 501 o 502. */
	private boolean ib_noEtapa501o502;

	/**
	 * Modifica el valor de no etapa 31.
	 *
	 * @param ab_b asigna el valor a la propiedad no etapa 31
	 */
	public void setNoEtapa31(boolean ab_b)
	{
		ib_noEtapa31 = ab_b;
	}

	/**
	 * Valida la propiedad no etapa 31.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en no etapa 31
	 */
	public boolean isNoEtapa31()
	{
		return ib_noEtapa31;
	}

	/**
	 * Modifica el valor de no etapa 501 o 502.
	 *
	 * @param ab_b asigna el valor a la propiedad no etapa 501 o 502
	 */
	public void setNoEtapa501o502(boolean ab_b)
	{
		ib_noEtapa501o502 = ab_b;
	}

	/**
	 * Valida la propiedad no etapa 501 o 502.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en no etapa 501 o 502
	 */
	public boolean isNoEtapa501o502()
	{
		return ib_noEtapa501o502;
	}

	/**
	 * Modifica el valor de solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad solicitud
	 */
	public void setSolicitud(Solicitud as_s)
	{
		is_solicitud = as_s;
	}

	/**
	 * Retorna el valor de solicitud.
	 *
	 * @return el valor de solicitud
	 */
	public Solicitud getSolicitud()
	{
		return is_solicitud;
	}

	/**
	 * Modifica el valor de solicitud asociada.
	 *
	 * @param asa_sa asigna el valor a la propiedad solicitud asociada
	 */
	public void setSolicitudAsociada(SolicitudAsociada asa_sa)
	{
		isa_solicitudAsociada = asa_sa;
	}

	/**
	 * Retorna el valor de solicitud asociada.
	 *
	 * @return el valor de solicitud asociada
	 */
	public SolicitudAsociada getSolicitudAsociada()
	{
		return isa_solicitudAsociada;
	}

	/**
	 * Modifica el valor de solicitud direccion certificado nueva entrada.
	 *
	 * @param asdc_sdc asigna el valor a la propiedad solicitud direccion certificado nueva entrada
	 */
	public void setSolicitudDireccionCertificadoNuevaEntrada(SolicitudDireccionCertificado asdc_sdc)
	{
		isdc_solicitudDireccionCertificadoNuevaEntrada = asdc_sdc;
	}

	/**
	 * Retorna el valor de solicitud direccion certificado nueva entrada.
	 *
	 * @return el valor de solicitud direccion certificado nueva entrada
	 */
	public SolicitudDireccionCertificado getSolicitudDireccionCertificadoNuevaEntrada()
	{
		return isdc_solicitudDireccionCertificadoNuevaEntrada;
	}

	/**
	 * Modifica el valor de turnos derivados.
	 *
	 * @param actd_td asigna el valor a la propiedad turnos derivados
	 */
	public void setTurnosDerivados(Collection<TurnoDerivado> actd_td)
	{
		ictd_turnosDerivados = actd_td;
	}

	/**
	 * Retorna el valor de turnos derivados.
	 *
	 * @return el valor de turnos derivados
	 */
	public Collection<TurnoDerivado> getTurnosDerivados()
	{
		return ictd_turnosDerivados;
	}

	/**
	 * Modifica el valor de turnos recurrencia.
	 *
	 * @param act_ct asigna el valor a la propiedad turnos recurrencia
	 */
	public void setTurnosRecurrencia(Collection<Turno> act_ct)
	{
		ict_turnosRecurrencia = act_ct;
	}

	/**
	 * Retorna el valor de turnos recurrencia.
	 *
	 * @return el valor de turnos recurrencia
	 */
	public Collection<Turno> getTurnosRecurrencia()
	{
		return ict_turnosRecurrencia;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud matricula nueva entrada.
	 *
	 * @return Retorna el valor de la propiedad solicitudMatriculaNuevaEntrada
	 */
	public Collection<SolicitudMatricula> getSolicitudMatriculaNuevaEntrada()
	{
		return icsm_solicitudMatriculaNuevaEntrada;
	}

	/**
	 * Modifica el valor de Solicitud Matricula Nueva Entrada.
	 *
	 * @param acsm_csm de acsm csm
	 */
	public void setSolicitudMatriculaNuevaEntrada(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_solicitudMatriculaNuevaEntrada = acsm_csm;
	}

	/**
	 * Modifica el valor de DatosAntSistemaNuevaEntrada.
	 *
	 * @param adas_das asigna el valor a la propiedad
	 */
	public void setDatosAntSistemaNuevaEntrada(DatosAntSistema adas_das)
	{
		idas_datosAntSistemaNuevaEntrada = adas_das;
	}

	/**
	 * Retorna Objeto o variable de valor DatosAntSistemaNuevaEntrada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public DatosAntSistema getDatosAntSistemaNuevaEntrada()
	{
		return idas_datosAntSistemaNuevaEntrada;
	}

	/**
	 * Modifica el valor de PersonaNuevaEntrada.
	 *
	 * @param ap_p asigna el valor a la propiedad
	 */
	public void setPersonaNuevaEntrada(Persona ap_p)
	{
		ip_personaNuevaEntrada = ap_p;
	}

	/**
	 * Retorna Objeto o variable de valor PersonaNuevaEntrada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Persona getPersonaNuevaEntrada()
	{
		return ip_personaNuevaEntrada;
	}
}
