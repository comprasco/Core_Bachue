package com.bachue.snr.prosnr01.model.calificacion;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades ConsultaCriteriosCalificacion.
 *
 * @author Julian Vaca
 */
public class ConsultaCriteriosCalificacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8817865283754676486L;

	/** Propiedad iap anotacion predio ant sistema. */
	private AnotacionPredio iap_anotacionPredioAntSistema;

	/** Propiedad iap anotacion predio documento. */
	private AnotacionPredio iap_anotacionPredioDocumento;

	/** Propiedad idas datos ant sistema. */
	private DatosAntSistema idas_datosAntSistema;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad is id usuario. */
	private String is_idUsuario;

	/** Propiedad ith turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/**
	 * Modifica el valor de anotacion predio ant sistema.
	 *
	 * @param anotacionPredioAntSistema asigna el valor a la propiedad anotacion predio ant sistema
	 */
	public void setAnotacionPredioAntSistema(AnotacionPredio anotacionPredioAntSistema)
	{
		iap_anotacionPredioAntSistema = anotacionPredioAntSistema;
	}

	/**
	 * Retorna el valor de anotacion predio ant sistema.
	 *
	 * @return el valor de anotacion predio ant sistema
	 */
	public AnotacionPredio getAnotacionPredioAntSistema()
	{
		return iap_anotacionPredioAntSistema;
	}

	/**
	 * Modifica el valor de anotacion predio documento.
	 *
	 * @param anotacionPredioDocumento asigna el valor a la propiedad anotacion predio documento
	 */
	public void setAnotacionPredioDocumento(AnotacionPredio anotacionPredioDocumento)
	{
		iap_anotacionPredioDocumento = anotacionPredioDocumento;
	}

	/**
	 * Retorna el valor de anotacion predio documento.
	 *
	 * @return el valor de anotacion predio documento
	 */
	public AnotacionPredio getAnotacionPredioDocumento()
	{
		return iap_anotacionPredioDocumento;
	}

	/**
	 * Modifica el valor de datos ant sistema.
	 *
	 * @param datosAntSistema asigna el valor a la propiedad datos ant sistema
	 */
	public void setDatosAntSistema(DatosAntSistema datosAntSistema)
	{
		idas_datosAntSistema = datosAntSistema;
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
	 * Modifica el valor de documento.
	 *
	 * @param documento asigna el valor a la propiedad documento
	 */
	public void setDocumento(Documento documento)
	{
		id_documento = documento;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public Documento getDocumento()
	{
		return id_documento;
	}

	/**
	 * Modifica el valor de id usuario.
	 *
	 * @param idUsuario asigna el valor a la propiedad id usuario
	 */
	public void setIdUsuario(String idUsuario)
	{
		is_idUsuario = idUsuario;
	}

	/**
	 * Retorna el valor de id usuario.
	 *
	 * @return el valor de id usuario
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}

	/**
	 * Modifica el valor de turno historia.
	 *
	 * @param turnoHistoria asigna el valor a la propiedad turno historia
	 */
	public void setTurnoHistoria(TurnoHistoria turnoHistoria)
	{
		ith_turnoHistoria = turnoHistoria;
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
