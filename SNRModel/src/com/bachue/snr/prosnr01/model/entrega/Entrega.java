package com.bachue.snr.prosnr01.model.entrega;

import com.bachue.snr.prosnr01.model.sdb.acc.PersonaEntrega;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import java.io.Serializable;

import java.util.Collection;
import java.util.Map;


/**
 * Objeto utilizado para enviar a la capa de negocio la información necesaria para finalizar
 * un tramite de entrega.
 *
 * @author Manuel Blanco
 */
public class Entrega implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 249143346553659084L;

	/** Propiedad icacd tipos Documentales. */
	private Collection<TipoDocumental> icacd_tiposDocumentales;

	/** Propiedad icsi datos intervinientes. */
	private Collection<SolicitudInterviniente> icsi_datosIntervinientes;

	/** Propiedad icsi intervinientes apoderado. */
	private Collection<SolicitudInterviniente> icsi_intervinientesApoderado;

	/** Propiedad icsm datos matriculas. */
	private Collection<SolicitudMatricula> icsm_datosMatriculas;

	/** Propiedad idpte datos predio entrega. */
	private DatosPredioTurnoEntrega idpte_datosPredioEntrega;

	/** Propiedad il turno historia. */
	private Long il_turnoHistoria;

	/** Propiedad imsba documentos adicionales. */
	private Map<String, DocumentoAdicional> imsba_documentosAdicionales;

	/** Propiedad ipe tercero. */
	private PersonaEntrega ipe_tercero;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/** Propiedad isi interviniente entrega. */
	private SolicitudInterviniente isi_intervinienteEntrega;

	/** Propiedad is correo electronico. */
	private String is_correoElectronico;

	/** Propiedad is turno. */
	private String is_idTurno;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is observaciones documentos. */
	private String is_observacionesDocumentos;

	/** Propiedad is renuncia terminos. */
	private String is_renunciaTerminos;

	/** Propiedad it turno. */
	private Turno it_turno;

	/** Propiedad iba carta autorizacion. */
	private byte[] iba_cartaAutorizacion;

	/** Propiedad iba cedula ciudadania. */
	private byte[] iba_cedulaCiudadania;

	/** Propiedad iba documento recurso. */
	private byte[] iba_documentoRecurso;

	/** Propiedad proceso vinculado 47 o 48. */
	private boolean procesoVinculado47o48;

	/** Propiedad il id etapa. */
	private long il_idEtapa;

	/**
	 * Modifica el valor de carta autorizacion.
	 *
	 * @param aba_ba asigna el valor a la propiedad carta autorizacion
	 */
	public void setCartaAutorizacion(byte[] aba_ba)
	{
		iba_cartaAutorizacion = aba_ba;
	}

	/**
	 * Retorna el valor de carta autorizacion.
	 *
	 * @return el valor de carta autorizacion
	 */
	public byte[] getCartaAutorizacion()
	{
		return iba_cartaAutorizacion;
	}

	/**
	 * Modifica el valor de cedula ciudadania.
	 *
	 * @param aba_ba asigna el valor a la propiedad cedula ciudadania
	 */
	public void setCedulaCiudadania(byte[] aba_ba)
	{
		iba_cedulaCiudadania = aba_ba;
	}

	/**
	 * Retorna el valor de cedula ciudadania.
	 *
	 * @return el valor de cedula ciudadania
	 */
	public byte[] getCedulaCiudadania()
	{
		return iba_cedulaCiudadania;
	}

	/**
	 * Modifica el valor de correo electronico.
	 *
	 * @param as_s asigna el valor a la propiedad correo electronico
	 */
	public void setCorreoElectronico(String as_s)
	{
		is_correoElectronico = as_s;
	}

	/**
	 * Retorna el valor de correo electronico.
	 *
	 * @return el valor de correo electronico
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Modifica el valor de datos intervinientes.
	 *
	 * @param acsi_csi asigna el valor a la propiedad datos intervinientes
	 */
	public void setDatosIntervinientes(Collection<SolicitudInterviniente> acsi_csi)
	{
		icsi_datosIntervinientes = acsi_csi;
	}

	/**
	 * Retorna el valor de datos intervinientes.
	 *
	 * @return el valor de datos intervinientes
	 */
	public Collection<SolicitudInterviniente> getDatosIntervinientes()
	{
		return icsi_datosIntervinientes;
	}

	/**
	 * Modifica el valor de datos matriculas.
	 *
	 * @param acsm_csm asigna el valor a la propiedad datos matriculas
	 */
	public void setDatosMatriculas(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_datosMatriculas = acsm_csm;
	}

	/**
	 * Retorna el valor de datos matriculas.
	 *
	 * @return el valor de datos matriculas
	 */
	public Collection<SolicitudMatricula> getDatosMatriculas()
	{
		return icsm_datosMatriculas;
	}

	/**
	 * Modifica el valor de datos predio entrega.
	 *
	 * @param adpte_dpte asigna el valor a la propiedad datos predio entrega
	 */
	public void setDatosPredioEntrega(DatosPredioTurnoEntrega adpte_dpte)
	{
		idpte_datosPredioEntrega = adpte_dpte;
	}

	/**
	 * Retorna el valor de datos predio entrega.
	 *
	 * @return el valor de datos predio entrega
	 */
	public DatosPredioTurnoEntrega getDatosPredioEntrega()
	{
		if(idpte_datosPredioEntrega == null)
			idpte_datosPredioEntrega = new DatosPredioTurnoEntrega();

		return idpte_datosPredioEntrega;
	}

	/**
	 * Modifica el valor de documento recurso.
	 *
	 * @param aba_ba asigna el valor a la propiedad documento recurso
	 */
	public void setDocumentoRecurso(byte[] aba_ba)
	{
		iba_documentoRecurso = aba_ba;
	}

	/**
	 * Retorna el valor de documento recurso.
	 *
	 * @return el valor de documento recurso
	 */
	public byte[] getDocumentoRecurso()
	{
		return iba_documentoRecurso;
	}

	/**
	 * Sets the documentos adicionales.
	 *
	 * @param amsba_msba de amsba msba
	 */
	public void setDocumentosAdicionales(Map<String, DocumentoAdicional> amsba_msba)
	{
		imsba_documentosAdicionales = amsba_msba;
	}

	/**
	 * Retorna el valor de documentos adicionales.
	 *
	 * @return el valor de documentos adicionales
	 */
	public Map<String, DocumentoAdicional> getDocumentosAdicionales()
	{
		return imsba_documentosAdicionales;
	}

	/**
	 * Modifica el valor de id etapa.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa
	 */
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna el valor de id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public long getIdEtapa()
	{
		return il_idEtapa;
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
	 * Modifica el valor de interviniente entrega.
	 *
	 * @param asi_si asigna el valor a la propiedad interviniente entrega
	 */
	public void setIntervinienteEntrega(SolicitudInterviniente asi_si)
	{
		isi_intervinienteEntrega = asi_si;
	}

	/**
	 * Retorna el valor de interviniente entrega.
	 *
	 * @return el valor de interviniente entrega
	 */
	public SolicitudInterviniente getIntervinienteEntrega()
	{
		return isi_intervinienteEntrega;
	}

	/**
	 * Modifica el valor de intervinientes apoderado.
	 *
	 * @param acsi_csi asigna el valor a la propiedad intervinientes apoderado
	 */
	public void setIntervinientesApoderado(Collection<SolicitudInterviniente> acsi_csi)
	{
		icsi_intervinientesApoderado = acsi_csi;
	}

	/**
	 * Retorna el valor de intervinientes apoderado.
	 *
	 * @return el valor de intervinientes apoderado
	 */
	public Collection<SolicitudInterviniente> getIntervinientesApoderado()
	{
		return icsi_intervinientesApoderado;
	}

	/**
	 * Modifica el valor de nir.
	 *
	 * @param as_s asigna el valor a la propiedad nir.
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna el valor de nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de observaciones documentos.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones documentos
	 */
	public void setObservacionesDocumentos(String as_s)
	{
		is_observacionesDocumentos = as_s;
	}

	/**
	 * Retorna el valor de observaciones documentos.
	 *
	 * @return el valor de observaciones documentos
	 */
	public String getObservacionesDocumentos()
	{
		return is_observacionesDocumentos;
	}

	/**
	 * @param Modifica el valor de la propiedad procesoVinculado47o48 por procesoVinculado47o48
	 */
	public void setProcesoVinculado47o48(boolean as_procesoVinculado47o48)
	{
		procesoVinculado47o48 = as_procesoVinculado47o48;
	}

	/**
	 * @return Retorna el valor de la propiedad procesoVinculado47o48
	 */
	public boolean isProcesoVinculado47o48()
	{
		return procesoVinculado47o48;
	}

	/**
	 * Modifica el valor de renuncia terminos.
	 *
	 * @param as_s asigna el valor a la propiedad renuncia terminos
	 */
	public void setRenunciaTerminos(String as_s)
	{
		is_renunciaTerminos = as_s;
	}

	/**
	 * Retorna el valor de renuncia terminos.
	 *
	 * @return el valor de renuncia terminos
	 */
	public String getRenunciaTerminos()
	{
		return is_renunciaTerminos;
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
		if(is_solicitud == null)
			is_solicitud = new Solicitud();

		return is_solicitud;
	}

	/**
	 * Modifica el valor de tercero.
	 *
	 * @param ape_pe asigna el valor a la propiedad tercero
	 */
	public void setTercero(PersonaEntrega ape_pe)
	{
		ipe_tercero = ape_pe;
	}

	/**
	 * Retorna el valor de tercero.
	 *
	 * @return el valor de tercero
	 */
	public PersonaEntrega getTercero()
	{
		return ipe_tercero;
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param acacd_cacd asigna el valor a la propiedad tipos documentales
	 */
	public void setTiposDocumentales(Collection<TipoDocumental> acacd_cacd)
	{
		icacd_tiposDocumentales = acacd_cacd;
	}

	/**
	 * Retorna el valor de tipos documentales.
	 *
	 * @return el valor de tipos documentales
	 */
	public Collection<TipoDocumental> getTiposDocumentales()
	{
		return icacd_tiposDocumentales;
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
	 * @param al_l asigna el valor a la propiedad turno historia
	 */
	public void setTurnoHistoria(Long al_l)
	{
		il_turnoHistoria = al_l;
	}

	/**
	 * Retorna el valor de turno historia.
	 *
	 * @return el valor de turno historia
	 */
	public Long getTurnoHistoria()
	{
		return il_turnoHistoria;
	}
}
