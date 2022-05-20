package com.bachue.snr.prosnr01.model.consulta.trazabilidad;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.consulta.predio.ConsultaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.NotaDevolutiva;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;

import java.io.Serializable;

import java.util.Collection;


/**
 * Class que contiene todos las propiedades Trazabilidad.
 *
 * @author Julian Vaca
 */
public class Trazabilidad extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1539451644650980635L;

	/** Propiedad icr circulo registral. */
	private CirculoRegistral icr_circuloRegistral;

	/** Propiedad icct trazabilidad. */
	private Collection<ConsultaTrazabilidad> icct_trazabilidad;

	/** Propiedad icnd nota devolutiva. */
	private Collection<NotaDevolutiva> icnd_notaDevolutiva;

	/** Propiedad icsm matriculas turno. */
	private Collection<SolicitudMatricula> icsm_matriculasTurno;

	/** Propiedad if fases. */
	private Fases if_fases;

	/** Propiedad ip proceso. */
	private Proceso ip_proceso;

	/** Propiedad irc detalle documento. */
	private RegistroCalificacion irc_detalleDocumento;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/** Propiedad is decision calificacion. */
	private String is_decisionCalificacion;

	/** Propiedad is tipificacion turno. */
	private String is_tipificacionTurno;

	/** Propiedad it turno. */
	private Turno it_turno;

	/** Propiedad ith turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/** Propiedad ib nir etapa 10. */
	private boolean ib_nirEtapa10;

	/**
	 * Instancia un nuevo objeto trazabilidad.
	 */
	public Trazabilidad()
	{
	}

	/**
	 * Constructor que crea una instancia apartir de un <code>ConsultaPredio</code>.
	 *
	 * @param acp_cp <code>ConsultaPredio</code> del cual se extraerán los valores necesarios para crear la nueva instancia
	 */
	public Trazabilidad(ConsultaPredio acp_cp)
	{
		if(acp_cp != null)
		{
			is_solicitud     = new Solicitud(null, acp_cp.getNir());
			it_turno         = new Turno(acp_cp.getIdTurno());
			ip_proceso       = new Proceso(null, acp_cp.getProceso());
			if_fases         = new Fases(null, acp_cp.getEstado());
		}
	}

	/**
	 * Instancia un nuevo objeto trazabilidad.
	 *
	 * @param as_solicitud de as solicitud
	 * @param at_turno de at turno
	 */
	public Trazabilidad(Solicitud as_solicitud, Turno at_turno)
	{
		setSolicitud(as_solicitud);
		setTurno(at_turno);
	}

	/**
	 * Instancia un nuevo objeto trazabilidad.
	 *
	 * @param as_solicitud de as solicitud
	 */
	public Trazabilidad(Solicitud as_solicitud)
	{
		setSolicitud(as_solicitud);
	}

	/**
	 * Instancia un nuevo objeto trazabilidad.
	 *
	 * @param at_turno de at turno
	 */
	public Trazabilidad(Turno at_turno)
	{
		setTurno(at_turno);
	}

	/**
	 * Modifica el valor de circulo registral.
	 *
	 * @param acr_cr asigna el valor a la propiedad circulo registral
	 */
	public void setCirculoRegistral(CirculoRegistral acr_cr)
	{
		icr_circuloRegistral = acr_cr;
	}

	/**
	 * Retorna el valor de circulo registral.
	 *
	 * @return el valor de circulo registral
	 */
	public CirculoRegistral getCirculoRegistral()
	{
		return icr_circuloRegistral;
	}

	/**
	 * Modifica el valor de decision calificacion.
	 *
	 * @param as_s asigna el valor a la propiedad decision calificacion
	 */
	public void setDecisionCalificacion(String as_s)
	{
		is_decisionCalificacion = as_s;
	}

	/**
	 * Retorna el valor de decision calificacion.
	 *
	 * @return el valor de decision calificacion
	 */
	public String getDecisionCalificacion()
	{
		return is_decisionCalificacion;
	}

	/**
	 * Modifica el valor de detalle documento.
	 *
	 * @param arc_rc asigna el valor a la propiedad detalle documento
	 */
	public void setDetalleDocumento(RegistroCalificacion arc_rc)
	{
		irc_detalleDocumento = arc_rc;
	}

	/**
	 * Retorna el valor de detalle documento.
	 *
	 * @return el valor de detalle documento
	 */
	public RegistroCalificacion getDetalleDocumento()
	{
		return irc_detalleDocumento;
	}

	/**
	 * Modifica el valor de fases.
	 *
	 * @param af_f asigna el valor a la propiedad fases
	 */
	public void setFases(Fases af_f)
	{
		if_fases = af_f;
	}

	/**
	 * Retorna el valor de fases.
	 *
	 * @return el valor de fases
	 */
	public Fases getFases()
	{
		return if_fases;
	}

	/**
	 * Modifica el valor de matriculas turno.
	 *
	 * @param acsm_csm asigna el valor a la propiedad matriculas turno
	 */
	public void setMatriculasTurno(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_matriculasTurno = acsm_csm;
	}

	/**
	 * Retorna el valor de matriculas turno.
	 *
	 * @return el valor de matriculas turno
	 */
	public Collection<SolicitudMatricula> getMatriculasTurno()
	{
		return icsm_matriculasTurno;
	}

	/**
	 * Modifica el valor de nir etapa 10.
	 *
	 * @param ab_b asigna el valor a la propiedad nir etapa 10
	 */
	public void setNirEtapa10(boolean ab_b)
	{
		ib_nirEtapa10 = ab_b;
	}

	/**
	 * Valida la propiedad nir etapa 10.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en nir etapa 10
	 */
	public boolean isNirEtapa10()
	{
		return ib_nirEtapa10;
	}

	/**
	 * Modifica el valor de nota devolutiva.
	 *
	 * @param acnd_cnd asigna el valor a la propiedad nota devolutiva
	 */
	public void setNotaDevolutiva(Collection<NotaDevolutiva> acnd_cnd)
	{
		icnd_notaDevolutiva = acnd_cnd;
	}

	/**
	 * Retorna el valor de nota devolutiva.
	 *
	 * @return el valor de nota devolutiva
	 */
	public Collection<NotaDevolutiva> getNotaDevolutiva()
	{
		return icnd_notaDevolutiva;
	}

	/**
	 * Modifica el valor de proceso.
	 *
	 * @param ap_p asigna el valor a la propiedad proceso
	 */
	public void setProceso(Proceso ap_p)
	{
		ip_proceso = ap_p;
	}

	/**
	 * Retorna el valor de proceso.
	 *
	 * @return el valor de proceso
	 */
	public Proceso getProceso()
	{
		return ip_proceso;
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
	 * Modifica el valor de tipificacion turno.
	 *
	 * @param tipificacionTurno asigna el valor a la propiedad tipificacion turno
	 */
	public void setTipificacionTurno(String tipificacionTurno)
	{
		is_tipificacionTurno = tipificacionTurno;
	}

	/**
	 * Retorna el valor de tipificacion turno.
	 *
	 * @return el valor de tipificacion turno
	 */
	public String getTipificacionTurno()
	{
		return is_tipificacionTurno;
	}

	/**
	 * Modifica el valor de trazabilidad.
	 *
	 * @param acct_cct asigna el valor a la propiedad trazabilidad
	 */
	public void setTrazabilidad(Collection<ConsultaTrazabilidad> acct_cct)
	{
		icct_trazabilidad = acct_cct;
	}

	/**
	 * Retorna el valor de trazabilidad.
	 *
	 * @return el valor de trazabilidad
	 */
	public Collection<ConsultaTrazabilidad> getTrazabilidad()
	{
		return icct_trazabilidad;
	}

	/**
	 * Modifica el valor de turno.
	 *
	 * @param at_t asigna el valor a la propiedad turno
	 */
	public void setTurno(Turno at_t)
	{
		it_turno = at_t;
	}

	/**
	 * Retorna el valor de turno.
	 *
	 * @return el valor de turno
	 */
	public Turno getTurno()
	{
		return it_turno;
	}

	/**
	 * Modifica el valor de turno historia.
	 *
	 * @param ath_th asigna el valor a la propiedad turno historia
	 */
	public void setTurnoHistoria(TurnoHistoria ath_th)
	{
		ith_turnoHistoria = ath_th;
	}

	/**
	 * Retorna el valor de turno historia.
	 *
	 * @return el valor de turno historia
	 */
	public TurnoHistoria getTurnoHistoria()
	{
		return ith_turnoHistoria;
	}
}
