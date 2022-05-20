package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.registro.Direccion;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;

import java.io.Serializable;


/**
 * Class que contiene todos las propiedades DireccionDelPredio.
 *
 * @author garias
 */
public class DireccionDelPredio extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2229473545843073316L;

	/** Propiedad idas datos antiguo sistema. */
	private DatosAntSistema idas_datosAntiguoSistema;

	/** Propiedad iodp direccion predio bng. */
	private DireccionPredio iodp_direccionPredioBng;

	/** Propiedad idpa direccion predio. */
	private DireccionPredioAcc idpa_direccionPredio;

	/** Propiedad is direccion. */
	private String is_direccion;

	/** Propiedad is id direccion. */
	private String is_idDireccion;

	/** Propiedad ib eliminar. */
	private boolean ib_eliminar;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/**
	 * Instancia un nuevo objeto direccion del predio.
	 */
	public DireccionDelPredio()
	{
		idas_datosAntiguoSistema = new DatosAntSistema();
		idas_datosAntiguoSistema.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
	}

	/**
	 * Instancia un nuevo objeto direccion del predio.
	 *
	 * @param ad_d de ad d
	 */
	public DireccionDelPredio(Direccion ad_d, boolean ab_complemento)
	{
		if(ad_d != null)
		{
			idas_datosAntiguoSistema     = new DatosAntSistema(ad_d.getIdTipoPredio());
			idpa_direccionPredio         = ad_d.getDireccionPredioAcc();
			ib_eliminar                  = ad_d.isEliminar();

			if(ab_complemento)
			{
				idpa_direccionPredio.setComplementoDireccion(ad_d.getComplementoDireccion());
				idpa_direccionPredio.setIdComplementoDireccion(ad_d.getIdComplementoDireccion());
			}

			idas_datosAntiguoSistema.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}
	}

	/**
	 * Instancia un nuevo objeto direccion del predio.
	 *
	 * @param ad_d de ad d
	 */
	public DireccionDelPredio(Direccion ad_d)
	{
		new DireccionDelPredio(ad_d, false);
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
	 * Modifica el valor de direccion.
	 *
	 * @param as_s asigna el valor a la propiedad direccion
	 */
	public void setDireccion(String as_s)
	{
		is_direccion = as_s;
	}

	/**
	 * Retorna el valor de direccion.
	 *
	 * @return el valor de direccion
	 */
	public String getDireccion()
	{
		return is_direccion;
	}

	/**
	 * Modifica el valor de direccion predio.
	 *
	 * @param adp_dp asigna el valor a la propiedad direccion predio
	 */
	public void setDireccionPredio(DireccionPredioAcc adp_dp)
	{
		idpa_direccionPredio = adp_dp;
	}

	/**
	 * Retorna el valor de direccion predio.
	 *
	 * @return el valor de direccion predio
	 */
	public DireccionPredioAcc getDireccionPredio()
	{
		if(idpa_direccionPredio == null)
			idpa_direccionPredio = new DireccionPredioAcc();

		return idpa_direccionPredio;
	}

	/**
	 * Modifica el valor de direccion predio bng.
	 *
	 * @param adp_dp asigna el valor a la propiedad direccion predio bng
	 */
	public void setDireccionPredioBng(DireccionPredio adp_dp)
	{
		iodp_direccionPredioBng = adp_dp;
	}

	/**
	 * Retorna el valor de direccion predio bng.
	 *
	 * @return el valor de direccion predio bng
	 */
	public DireccionPredio getDireccionPredioBng()
	{
		if(iodp_direccionPredioBng == null)
			iodp_direccionPredioBng = new DireccionPredio();

		return iodp_direccionPredioBng;
	}

	/**
	 * Modifica el valor de eliminar.
	 *
	 * @param ab_b asigna el valor a la propiedad eliminar
	 */
	public void setEliminar(boolean ab_b)
	{
		ib_eliminar = ab_b;
	}

	/**
	 * Valida la propiedad eliminar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en eliminar
	 */
	public boolean isEliminar()
	{
		return ib_eliminar;
	}

	/**
	 * Modifica el valor de IdDireccion.
	 *
	 * @param as_s the idDireccion to set
	 */
	public void setIdDireccion(String as_s)
	{
		is_idDireccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id direccion.
	 *
	 * @return the idDireccion
	 */
	public String getIdDireccion()
	{
		return is_idDireccion;
	}

	/**
	 * Modifica el valor de seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad seleccionado
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en seleccionado
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}
}
