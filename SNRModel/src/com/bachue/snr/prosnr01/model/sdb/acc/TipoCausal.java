package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;

import java.io.Serializable;

import java.util.Collection;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_TIPO_CAUSAL.
 *
 * @author Julian Vaca
 */
public class TipoCausal extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long                serialVersionUID                = 8644186576514459408L;
	
	/** Propiedad icrc info turnos. */
	private Collection<RegistroCalificacion> icrc_infoTurnos;
	
	/** Propiedad ictc list tipos causales. */
	private Collection<TipoCausal>           ictc_listTiposCausales;
	
	/** Propiedad il id etapa. */
	private Long                             il_idEtapa;
	
	/** Propiedad il id motivo. */
	private Long                             il_idMotivo;
	
	/** Propiedad il id solicitud. */
	private Long                             il_idSolicitud;
	
	/** Propiedad ioo oficina origen medida cautelar. */
	private OficinaOrigen                    ioo_oficinaOrigenMedidaCautelar;
	
	/** Propiedad is descripcion motivo. */
	private String                           is_descripcionMotivo;
	
	/** Propiedad is estado causal. */
	private String                           is_estadoCausal;
	
	/** Propiedad is id proceso. */
	private String                           is_idProceso;
	
	/** Propiedad is id sub proceso. */
	private String                           is_idSubProceso;
	
	/** Propiedad is id tipo causal. */
	private String                           is_idTipoCausal;
	
	/** Propiedad is id turno. */
	private String                           is_idTurno;
	
	/** Propiedad is id turno historia. */
	private String                           is_idTurnoHistoria;
	
	/** Propiedad is nombre. */
	private String                           is_nombre;
	
	/** Propiedad is numero proceso. */
	private String                           is_numeroProceso;
	
	/** Propiedad is observaciones. */
	private String                           is_observaciones;
	
	/** Propiedad is plantilla. */
	private String                           is_plantilla;
	
	/** Propiedad is referencia. */
	private String                           is_referencia;
	
	/** Propiedad is secuence. */
	private String                           is_secuence;
	
	/** Propiedad is user id. */
	private String                           is_userId;
	
	/** Propiedad ib file pdf. */
	private byte[]                           ib_filePdf;
	
	/** Propiedad ib bloqueado. */
	private boolean                          ib_bloqueado;
	
	/** Propiedad ib cancelacion. */
	private boolean                          ib_cancelacion;
	
	/** Propiedad ib causales vinculacion. */
	private boolean                          ib_causalesVinculacion;
	
	/** Propiedad ib ind vinculacion. */
	private boolean                          ib_indVinculacion;
	
	/** Propiedad ib medida cautelar. */
	private boolean                          ib_medidaCautelar;
	
	/** Propiedad ib seleccionado. */
	private boolean                          ib_seleccionado;
	
	/** Propiedad il version. */
	private long                             il_version;

	/**
	 * Constructor por defecto.
	 */
	public TipoCausal()
	{
	}

	/**
	 * Modifica el valor de Bloqueado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setBloqueado(boolean ab_b)
	{
		ib_bloqueado                                                         = ab_b;
	}

	/**
	 * Valida la propiedad bloqueado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isBloqueado()
	{
		return ib_bloqueado;
	}

	/**
	 * Modifica el valor de Cancelacion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setCancelacion(boolean ab_b)
	{
		ib_cancelacion = ab_b;
	}

	/**
	 * Valida la propiedad cancelacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isCancelacion()
	{
		return ib_cancelacion;
	}

	/**
	 * Modifica el valor de CausalesVinculacion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setCausalesVinculacion(boolean ab_b)
	{
		ib_causalesVinculacion = ab_b;
	}

	/**
	 * Valida la propiedad causales vinculacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isCausalesVinculacion()
	{
		return ib_causalesVinculacion;
	}

	/**
	 * Modifica el valor de DescripcionMotivo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcionMotivo(String as_s)
	{
		is_descripcionMotivo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion motivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcionMotivo()
	{
		return is_descripcionMotivo;
	}

	/**
	 * Modifica el valor de EstadoCausal.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstadoCausal(String as_s)
	{
		is_estadoCausal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado causal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoCausal()
	{
		return is_estadoCausal;
	}

	/**
	 * Modifica el valor de FilePdf.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setFilePdf(byte[] ab_b)
	{
		ib_filePdf = ab_b;
	}

	/**
	 * Retorna Objeto o variable de valor file pdf.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public byte[] getFilePdf()
	{
		return ib_filePdf;
	}

	/**
	 * Modifica el valor de IdEtapa.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdEtapa(Long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdEtapa()
	{
		return il_idEtapa;
	}

	/**
	 * Modifica el valor de IdMotivo.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMotivo(Long al_l)
	{
		il_idMotivo = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id motivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdMotivo()
	{
		return il_idMotivo;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdSolicitud(Long al_l)
	{
		il_idSolicitud = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdSolicitud()
	{
		return il_idSolicitud;
	}

	/**
	 * Modifica el valor de IdSubProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSubProceso(String as_s)
	{
		is_idSubProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sub proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubProceso()
	{
		return is_idSubProceso;
	}

	/**
	 * Modifica el valor de IdTipoCausal.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoCausal(String as_s)
	{
		is_idTipoCausal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo causal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoCausal()
	{
		return is_idTipoCausal;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de IndVinculacion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setIndVinculacion(boolean ab_b)
	{
		ib_indVinculacion = ab_b;
	}

	/**
	 * Valida la propiedad ind vinculacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isIndVinculacion()
	{
		return ib_indVinculacion;
	}

	/**
	 * Modifica el valor de InfoTurnos.
	 *
	 * @param acrc_rc asigna el valor a la propiedad
	 */
	public void setInfoTurnos(Collection<RegistroCalificacion> acrc_rc)
	{
		icrc_infoTurnos = acrc_rc;
	}

	/**
	 * Retorna Objeto o variable de valor info turnos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<RegistroCalificacion> getInfoTurnos()
	{
		return icrc_infoTurnos;
	}

	/**
	 * Modifica el valor de ListTiposCausales.
	 *
	 * @param actc_tc asigna el valor a la propiedad
	 */
	public void setListTiposCausales(Collection<TipoCausal> actc_tc)
	{
		ictc_listTiposCausales = actc_tc;
	}

	/**
	 * Retorna Objeto o variable de valor list tipos causales.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoCausal> getListTiposCausales()
	{
		return ictc_listTiposCausales;
	}

	/**
	 * Modifica el valor de MedidaCautelar.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setMedidaCautelar(boolean ab_b)
	{
		ib_medidaCautelar = ab_b;
	}

	/**
	 * Valida la propiedad medida cautelar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isMedidaCautelar()
	{
		return ib_medidaCautelar;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		is_nombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de NumeroProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroProceso(String as_s)
	{
		is_numeroProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroProceso()
	{
		return is_numeroProceso;
	}

	/**
	 * Modifica el valor de Observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de OficinaOrigenMedidaCautelar.
	 *
	 * @param aoo_oo asigna el valor a la propiedad
	 */
	public void setOficinaOrigenMedidaCautelar(OficinaOrigen aoo_oo)
	{
		ioo_oficinaOrigenMedidaCautelar = aoo_oo;
	}

	/**
	 * Retorna Objeto o variable de valor oficina origen medida cautelar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public OficinaOrigen getOficinaOrigenMedidaCautelar()
	{
		return ioo_oficinaOrigenMedidaCautelar;
	}

	/**
	 * Modifica el valor de Plantilla.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPlantilla(String as_s)
	{
		is_plantilla = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor plantilla.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPlantilla()
	{
		return is_plantilla;
	}

	/**
	 * Modifica el valor de Referencia.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setReferencia(String as_s)
	{
		is_referencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor referencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getReferencia()
	{
		return is_referencia;
	}

	/**
	 * Modifica el valor de Secuence.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSecuence(String as_s)
	{
		is_secuence = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor secuence.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSecuence()
	{
		return is_secuence;
	}

	/**
	 * Modifica el valor de Seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Modifica el valor de UserId.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUserId(String as_s)
	{
		is_userId = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor user id.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUserId()
	{
		return is_userId;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setVersion(long al_l)
	{
		il_version = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getVersion()
	{
		return il_version;
	}
}
