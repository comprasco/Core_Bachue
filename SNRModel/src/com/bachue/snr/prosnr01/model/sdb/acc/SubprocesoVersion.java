package com.bachue.snr.prosnr01.model.sdb.acc;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla de Subproceso SDB_ACC_SUBPROCESO_VERSION.
 *
 * @author Gabriel Arias
 */
public class SubprocesoVersion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID          = -9123826464308889343L;
	
	/** Propiedad il id etapa inicial. */
	private Long              il_idEtapaInicial;
	
	/** Propiedad il tiempo vencimiento. */
	private Long              il_tiempoVencimiento;
	
	/** Propiedad il version subproceso. */
	private Long              il_versionSubproceso;
	
	/** Propiedad is conservacion documental. */
	private String            is_conservacionDocumental;
	
	/** Propiedad is estado actividad. */
	private String            is_estadoActividad;
	
	/** Propiedad is estado flujo. */
	private String            is_estadoFlujo;
	
	/** Propiedad is id proceso. */
	private String            is_idProceso;
	
	/** Propiedad is id subproceso. */
	private String            is_idSubproceso;
	
	/** Propiedad is id unidad tiempo. */
	private String            is_idUnidadTiempo;
	
	/** Propiedad is obtener recibo caja. */
	private String            is_obtenerReciboCaja;
	
	/** Propiedad is plantilla. */
	private String            is_plantilla;
	
	/** Propiedad is solicitud certificado. */
	private String            is_solicitudCertificado;
	
	/** Propiedad is xml. */
	private String            is_xml;

	/**
	 * Modifica el valor de ConservacionDocumental.
	 *
	 * @param as_s de as s
	 */
	public void setConservacionDocumental(String as_s)
	{
		is_conservacionDocumental                       = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor conservacion documental.
	 *
	 * @return Retorna el valor de la propiedad conservacionDocumental
	 */
	public String getConservacionDocumental()
	{
		return is_conservacionDocumental;
	}

	/**
	 * Modifica el valor de EstadoActividad.
	 *
	 * @param as_s de as s
	 */
	public void setEstadoActividad(String as_s)
	{
		is_estadoActividad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado actividad.
	 *
	 * @return Retorna el valor de la propiedad estadoActividad
	 */
	public String getEstadoActividad()
	{
		return is_estadoActividad;
	}

	/**
	 * Modifica el valor de EstadoFlujo.
	 *
	 * @param as_s de as s
	 */
	public void setEstadoFlujo(String as_s)
	{
		is_estadoFlujo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado flujo.
	 *
	 * @return Retorna el valor de la propiedad estadoFlujo
	 */
	public String getEstadoFlujo()
	{
		return is_estadoFlujo;
	}

	/**
	 * Modifica el valor de IdEtapaInicial.
	 *
	 * @param al_l de al l
	 */
	public void setIdEtapaInicial(Long al_l)
	{
		il_idEtapaInicial = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa inicial.
	 *
	 * @return Retorna el valor de la propiedad idEtapaInicial
	 */
	public Long getIdEtapaInicial()
	{
		return il_idEtapaInicial;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s de as s
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
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
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param as_s de as s
	 */
	public void setIdSubproceso(String as_s)
	{
		is_idSubproceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso.
	 *
	 * @return Retorna el valor de la propiedad idSubproceso
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * Modifica el valor de IdUnidadTiempo.
	 *
	 * @param as_s de as s
	 */
	public void setIdUnidadTiempo(String as_s)
	{
		is_idUnidadTiempo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id unidad tiempo.
	 *
	 * @return Retorna el valor de la propiedad idUnidadTiempo
	 */
	public String getIdUnidadTiempo()
	{
		return is_idUnidadTiempo;
	}

	/**
	 * Modifica el valor de ObtenerReciboCaja.
	 *
	 * @param as_s de as s
	 */
	public void setObtenerReciboCaja(String as_s)
	{
		is_obtenerReciboCaja = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor obtener recibo caja.
	 *
	 * @return Retorna el valor de la propiedad obtenerReciboCaja
	 */
	public String getObtenerReciboCaja()
	{
		return is_obtenerReciboCaja;
	}

	/**
	 * Modifica el valor de Plantilla.
	 *
	 * @param as_s de as s
	 */
	public void setPlantilla(String as_s)
	{
		is_plantilla = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor plantilla.
	 *
	 * @return Retorna el valor de la propiedad plantilla
	 */
	public String getPlantilla()
	{
		return is_plantilla;
	}

	/**
	 * Modifica el valor de SolicitudCertificado.
	 *
	 * @param as_s de as s
	 */
	public void setSolicitudCertificado(String as_s)
	{
		is_solicitudCertificado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud certificado.
	 *
	 * @return Retorna el valor de la propiedad solicitudCertificado
	 */
	public String getSolicitudCertificado()
	{
		return is_solicitudCertificado;
	}

	/**
	 * Modifica el valor de TiempoVencimiento.
	 *
	 * @param al_l de al l
	 */
	public void setTiempoVencimiento(Long al_l)
	{
		il_tiempoVencimiento = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor tiempo vencimiento.
	 *
	 * @return Retorna el valor de la propiedad tiempoVencimiento
	 */
	public Long getTiempoVencimiento()
	{
		return il_tiempoVencimiento;
	}

	/**
	 * Modifica el valor de VersionSubproceso.
	 *
	 * @param al_l de al l
	 */
	public void setVersionSubproceso(Long al_l)
	{
		il_versionSubproceso = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version subproceso.
	 *
	 * @return Retorna el valor de la propiedad versionSubproceso
	 */
	public Long getVersionSubproceso()
	{
		return il_versionSubproceso;
	}

	/**
	 * Modifica el valor de Xml.
	 *
	 * @param as_s de as s
	 */
	public void setXml(String as_s)
	{
		is_xml = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor xml.
	 *
	 * @return el valor de xml
	 */
	public String getXml()
	{
		return is_xml;
	}
}
