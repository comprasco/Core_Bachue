package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.ConsultaCriterioAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades CalificacionAntiguoSistema.
 *
 * @author Julian Vaca
 */
public class CalificacionAntiguoSistema extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7227475798939128243L;

	/** Propiedad iccas consulta criterio ant sistema. */
	private ConsultaCriterioAntSistema iccas_consultaCriterioAntSistema;

	/** Propiedad idas datos antiguo sistema. */
	private DatosAntSistema idas_datosAntiguoSistema;

	/** Propiedad id documento. */
	private Documento id_documento;

	/**
	 * Instancia un nuevo objeto calificacion antiguo sistema.
	 */
	public CalificacionAntiguoSistema()
	{
		iccas_consultaCriterioAntSistema     = new ConsultaCriterioAntSistema();
		idas_datosAntiguoSistema             = new DatosAntSistema();
		id_documento                         = new Documento();
	}

	/**
	 * Modifica el valor de consulta criterio ant sistema.
	 *
	 * @param accas_ccas asigna el valor a la propiedad consulta criterio ant sistema
	 */
	public void setConsultaCriterioAntSistema(ConsultaCriterioAntSistema accas_ccas)
	{
		iccas_consultaCriterioAntSistema = accas_ccas;
	}

	/**
	 * Retorna el valor de consulta criterio ant sistema.
	 *
	 * @return el valor de consulta criterio ant sistema
	 */
	public ConsultaCriterioAntSistema getConsultaCriterioAntSistema()
	{
		if(iccas_consultaCriterioAntSistema == null)
			iccas_consultaCriterioAntSistema = new ConsultaCriterioAntSistema();

		return iccas_consultaCriterioAntSistema;
	}

	/**
	 * Modifica el valor de datos antiguo sistema.
	 *
	 * @param adas_das asigna el valor a la propiedad datos antiguo sistema
	 */
	public void setDatosAntiguoSistema(DatosAntSistema adas_das)
	{
		idas_datosAntiguoSistema = adas_das;
	}

	/**
	 * Retorna el valor de datos antiguo sistema.
	 *
	 * @return el valor de datos antiguo sistema
	 */
	public DatosAntSistema getDatosAntiguoSistema()
	{
		if(idas_datosAntiguoSistema == null)
			idas_datosAntiguoSistema = new DatosAntSistema();

		return idas_datosAntiguoSistema;
	}

	/**
	 * Modifica el valor de documento.
	 *
	 * @param ad_d asigna el valor a la propiedad documento
	 */
	public void setDocumento(Documento ad_d)
	{
		id_documento = ad_d;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public Documento getDocumento()
	{
		if(id_documento == null)
			id_documento = new Documento();

		return id_documento;
	}
}
