package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;

import java.io.Serializable;

import java.util.Collection;



/**
 * Class que contiene todos las propiedades MatriculaBase.
 *
 * @author Julian Vaca
 */
public class MatriculaBase extends PredioSegregado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3114039719702053816L;

	/** Propiedad icdp direccion predio. */
	private Collection<DireccionPredio> icdp_direccionPredio;

	/**
	 * Método constructor por defecto.
	 */
	public MatriculaBase()
	{
	}

	/**
	 * Método constructor a partir de un predio segregado.
	 *
	 * @param aps_param Objeto que contiene la información
	 */
	public MatriculaBase(PredioSegregado aps_param)
	{
		if(aps_param != null)
		{
			setAgregado(aps_param.isAgregado());
			setAnotaciones(aps_param.getAnotaciones());
			setAnotacionesSegregadas(aps_param.getAnotacionesSegregadas());
			setDescripcion(aps_param.getDescripcion());
			setLote(aps_param.getLote());
			setEditado(aps_param.isEditado());
			setEditar(aps_param.isEditado());
			setEliminar(aps_param.isEliminar());
			setIdAnotacion(aps_param.getIdAnotacion());
			setIdAnotacion1(aps_param.getIdAnotacion1());
			setIdCirculo(aps_param.getIdCirculo());
			setIdCirculo1(aps_param.getIdCirculo1());
			setIdMatricula(aps_param.getIdMatricula());
			setIdMatricula1(aps_param.getIdMatricula1());
			setIdTurnoHistoria(aps_param.getIdTurnoHistoria());
		}
	}

	/**
	 * Modifica el valor de direccion predio.
	 *
	 * @param acdp_cdp asigna el valor a la propiedad direccion predio
	 */
	public void setDireccionPredio(Collection<DireccionPredio> acdp_cdp)
	{
		icdp_direccionPredio = acdp_cdp;
	}

	/**
	 * Retorna el valor de direccion predio.
	 *
	 * @return el valor de direccion predio
	 */
	public Collection<DireccionPredio> getDireccionPredio()
	{
		return icdp_direccionPredio;
	}
}
