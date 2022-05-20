package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;

import java.io.Serializable;

import java.util.Collection;



/**
 * Class que contiene todos las propiedades DataConsultaAntSistema.
 *
 * @author Julian Vaca
 */
public class DataConsultaAntSistema extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2987209923116190057L;

	/** Propiedad icdcpc data consulta por criterio. */
	private Collection<DataConsultaPorCriterio> icdcpc_dataConsultaPorCriterio;

	/** Propiedad idas datos ant sistema. */
	private DatosAntSistema idas_datosAntSistema;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/**
	 * Modifica el valor de data consulta por criterio.
	 *
	 * @param dataConsultaPorCriterio asigna el valor a la propiedad data consulta por criterio
	 */
	public void setDataConsultaPorCriterio(Collection<DataConsultaPorCriterio> dataConsultaPorCriterio)
	{
		icdcpc_dataConsultaPorCriterio = dataConsultaPorCriterio;
	}

	/**
	 * Retorna el valor de data consulta por criterio.
	 *
	 * @return el valor de data consulta por criterio
	 */
	public Collection<DataConsultaPorCriterio> getDataConsultaPorCriterio()
	{
		return icdcpc_dataConsultaPorCriterio;
	}

	/**
	 * Modifica el valor de datos ant sistema.
	 *
	 * @param datosAntSistema asigna el valor a la propiedad datos ant sistema
	 */
	public void setDatosAntSistema(DatosAntSistema datosAntSistema)
	{
		this.idas_datosAntSistema = datosAntSistema;
	}

	/**
	 * Retorna el valor de datos ant sistema.
	 *
	 * @return el valor de datos ant sistema
	 */
	public DatosAntSistema getDatosAntSistema()
	{
		return idas_datosAntSistema;
	}

	/**
	 * Modifica el valor de solicitud.
	 *
	 * @param solicitud asigna el valor a la propiedad solicitud
	 */
	public void setSolicitud(Solicitud solicitud)
	{
		this.is_solicitud = solicitud;
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
}
