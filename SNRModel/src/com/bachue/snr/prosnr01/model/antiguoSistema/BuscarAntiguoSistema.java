package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacionDocumento;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistemaUI;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;

import java.io.Serializable;

import java.util.Collection;



/**
 * Clase que contiene las propiedades de buscar antiguo sistema.
 *
 * @author hcastaneda
 *
 */
public class BuscarAntiguoSistema implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long                      serialVersionUID                             = 2469531276193768569L;
	
	/** Propiedad icdas matriculas. */
	private Collection<DatosAntSistema>            icdas_matriculas;
	
	/** Propiedad icdcpd data consulta por documento. */
	private Collection<DataConsultaPorCriterio>    icdcpd_dataConsultaPorDocumento;
	
	/** Propiedad ido documentos OWCC. */
	private Collection<DocumentoOWCC>              ido_documentosOWCC;
	
	/** Propiedad icccd consulta criterios calificacion documento. */
	private ConsultaCriteriosCalificacionDocumento icccd_consultaCriteriosCalificacionDocumento;
	
	/** Propiedad idas detalle ant sistema. */
	private DetalleAntSistemaUI                    idas_detalleAntSistema;
	
	/** Propiedad il id turno historia. */
	private Long                                   il_idTurnoHistoria;
	
	/** Propiedad is solicitud. */
	private Solicitud                              is_solicitud;
	
	/** Propiedad is id tipo criterio busqueda. */
	private String                                 is_idTipoCriterioBusqueda;
	
	/** Propiedad is id turno. */
	private String                                 is_idTurno;
	
	/** Propiedad ib validar numero folios. */
	private boolean                                ib_validarNumeroFolios;
	
	/** Propiedad il id motivo. */
	private long                                   il_idMotivo;

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param acccd_cccd Argumento que modifica el valor de la propiedad.
	 */
	public void setConsultaCriteriosCalificacionDocumento(ConsultaCriteriosCalificacionDocumento acccd_cccd)
	{
		icccd_consultaCriteriosCalificacionDocumento                                            = acccd_cccd;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public ConsultaCriteriosCalificacionDocumento getConsultaCriteriosCalificacionDocumento()
	{
		return icccd_consultaCriteriosCalificacionDocumento;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param acdcpc_dcpc Argumento que modifica el valor de la propiedad.
	 */
	public void setDataConsultaPorDocumento(Collection<DataConsultaPorCriterio> acdcpc_dcpc)
	{
		icdcpd_dataConsultaPorDocumento = acdcpc_dcpc;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<DataConsultaPorCriterio> getDataConsultaPorDocumento()
	{
		return icdcpd_dataConsultaPorDocumento;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param adas_das Argumento que modifica el valor de la propiedad.
	 */
	public void setDetalleAntSistema(DetalleAntSistemaUI adas_das)
	{
		idas_detalleAntSistema = adas_das;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public DetalleAntSistemaUI getDetalleAntSistema()
	{
		return idas_detalleAntSistema;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ado_do Argumento que modifica el valor de la propiedad.
	 */
	public void setDocumentosOWCC(Collection<DocumentoOWCC> ado_do)
	{
		ido_documentosOWCC = ado_do;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<DocumentoOWCC> getDocumentosOWCC()
	{
		return ido_documentosOWCC;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param al_l Argumento que modifica el valor de la propiedad.
	 */
	public void setIdMotivo(long al_l)
	{
		il_idMotivo = al_l;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public long getIdMotivo()
	{
		return il_idMotivo;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setIdTipoCriterioBusqueda(String as_s)
	{
		is_idTipoCriterioBusqueda = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdTipoCriterioBusqueda()
	{
		return is_idTipoCriterioBusqueda;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param al_l Argumento que modifica el valor de la propiedad.
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param acdas_das Argumento que modifica el valor de la propiedad.
	 */
	public void setMatriculas(Collection<DatosAntSistema> acdas_das)
	{
		icdas_matriculas = acdas_das;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<DatosAntSistema> getMatriculas()
	{
		return icdas_matriculas;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setSolicitud(Solicitud as_s)
	{
		is_solicitud = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Solicitud getSolicitud()
	{
		return is_solicitud;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Argumento que modifica el valor de la propiedad.
	 */
	public void setValidarNumeroFolios(boolean ab_b)
	{
		ib_validarNumeroFolios = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isValidarNumeroFolios()
	{
		return ib_validarNumeroFolios;
	}
}
