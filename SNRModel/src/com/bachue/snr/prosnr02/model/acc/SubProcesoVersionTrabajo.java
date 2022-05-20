package com.bachue.snr.prosnr02.model.acc;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla de Subproceso SDB_ACC_SUBPROCESO_VERSION_TRABAJO.
 *
 * @author jpatino
 */
public class SubProcesoVersionTrabajo extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4512921398686777474L;

	/** Propiedad is conservacion documental. */
	private String is_conservacionDocumental;

	/** Propiedad is estado actividad. */
	private String is_estadoActividad;

	/** Propiedad is estado flujo. */
	private String is_estadoFlujo;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is id subproceso. */
	private String is_idSubproceso;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is obtener recibo caja. */
	private String is_obtenerReciboCaja;

	/** Propiedad is plantilla. */
	private String is_plantilla;

	/** Propiedad is solicitud certificado. */
	private String is_solicitudCertificado;

	/** Propiedad is xml. */
	private String is_xml;

	/** Propiedad iba definicion. */
	private byte[] iba_definicion;

	/** Propiedad ii version. */
	private int ii_version;

	/** Propiedad ii version base. */
	private int ii_versionBase;

	/** Propiedad il id etapa inicial. */
	private long il_idEtapaInicial;

	/**
	 * Constructor por defecto.
	 */
	public SubProcesoVersionTrabajo()
	{
	}

	/**
	 * Constructor que recibe como parametro del id del proceso y el id del subproceso.
	 *
	 * @param as_idProceso id del proceso
	 * @param as_idSubproceso id del subproceso
	 */
	public SubProcesoVersionTrabajo(String as_idProceso, String as_idSubproceso)
	{
		setIdProceso(as_idProceso);
		setIdSubproceso(as_idSubproceso);
	}

	/**
	 * Modifica el valor de ConservacionDocumental.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setConservacionDocumental(String as_s)
	{
		is_conservacionDocumental = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor conservacion documental.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getConservacionDocumental()
	{
		return is_conservacionDocumental;
	}

	/**
	 * Modifica el valor de Definicion.
	 *
	 * @param aba_ba asigna el valor a la propiedad
	 */
	public void setDefinicion(byte[] aba_ba)
	{
		iba_definicion = aba_ba;
	}

	/**
	 * Retorna Objeto o variable de valor definicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public byte[] getDefinicion()
	{
		return iba_definicion;
	}

	/**
	 * Modifica el valor de EstadoActividad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstadoActividad(String as_s)
	{
		is_estadoActividad = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor estado actividad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoActividad()
	{
		return is_estadoActividad;
	}

	/**
	 * Modifica el valor de EstadoFlujo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstadoFlujo(String as_s)
	{
		is_estadoFlujo = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor estado flujo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoFlujo()
	{
		return is_estadoFlujo;
	}

	/**
	 * Modifica el valor de IdEtapaInicial.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdEtapaInicial(long al_l)
	{
		il_idEtapaInicial = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa inicial.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdEtapaInicial()
	{
		return il_idEtapaInicial;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = StringUtils.getString(as_s);
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
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSubproceso(String as_s)
	{
		is_idSubproceso = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		is_nombre = StringUtils.getString(as_s);
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
	 * Modifica el valor de ObtenerReciboCaja.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObtenerReciboCaja(String as_s)
	{
		is_obtenerReciboCaja = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor obtener recibo caja.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObtenerReciboCaja()
	{
		return is_obtenerReciboCaja;
	}

	/**
	 * Modifica el valor de Plantilla.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPlantilla(String as_s)
	{
		is_plantilla = StringUtils.getString(as_s);
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
	 * Modifica el valor de SolicitudCertificado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSolicitudCertificado(String as_s)
	{
		is_solicitudCertificado = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor solicitud certificado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSolicitudCertificado()
	{
		return is_solicitudCertificado;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setVersion(int ai_i)
	{
		ii_version = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getVersion()
	{
		return ii_version;
	}

	/**
	 * Modifica el valor de VersionBase.
	 *
	 * @param ai_i de ai i
	 */
	public void setVersionBase(int ai_i)
	{
		ii_versionBase = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor version base.
	 *
	 * @return el valor de version base
	 */
	public int getVersionBase()
	{
		return ii_versionBase;
	}

	/**
	 * Modifica el valor de Xml.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setXml(String as_s)
	{
		is_xml = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor xml.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getXml()
	{
		return is_xml;
	}
}
