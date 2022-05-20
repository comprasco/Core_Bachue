package com.bachue.snr.prosnr01.model.sdb.bng;

import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoEntradaAgregarListaMatriculasAlertaListaMatriculasMatricula;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoEntradaAgregarMatriculaAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoEntradaConsultarMatriculaAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoEntradaRemoverMatriculaAlerta;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades AlertaTPredio.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 2/04/2020
 */
public class AlertaTPredio extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3184913497193747095L;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is nombre circulo. */
	private String is_nombreCirculo;

	/** Propiedad is numero predial. */
	private String is_numeroPredial;

	/** Propiedad il id alerta tierras. */
	private long il_idAlertaTierras;

	/** Propiedad il id matricula. */
	private long il_idMatricula;

	/**
	 * Instancia un nuevo objeto alerta T predio.
	 */
	public AlertaTPredio()
	{
	}

	/**
	 * Instancia un nuevo objeto alerta T predio.
	 *
	 * @param ateama_entrada de ateama entrada
	 */
	public AlertaTPredio(TipoEntradaAgregarMatriculaAlerta ateama_entrada)
	{
		if(ateama_entrada != null)
		{
			is_idCirculo           = ateama_entrada.getCodCirculoRegistral();
			il_idAlertaTierras     = ateama_entrada.getIdAlerta();
			il_idMatricula         = ateama_entrada.getNumMatriculaInmobiliaria();
		}
	}

	/**
	 * Instancia un nuevo objeto alerta T predio.
	 *
	 * @param aterma_entrada de aterma entrada
	 */
	public AlertaTPredio(TipoEntradaRemoverMatriculaAlerta aterma_entrada)
	{
		if(aterma_entrada != null)
		{
			is_idCirculo           = aterma_entrada.getCodCirculoRegistral();
			il_idAlertaTierras     = aterma_entrada.getIdAlerta();
			il_idMatricula         = aterma_entrada.getNumMatriculaInmobiliaria();
		}
	}

	/**
	 * Instancia un nuevo objeto alerta T predio.
	 *
	 * @param atealmalmm_entrada de atealmalmm entrada
	 */
	public AlertaTPredio(TipoEntradaAgregarListaMatriculasAlertaListaMatriculasMatricula atealmalmm_entrada)
	{
		if(atealmalmm_entrada != null)
		{
			is_idCirculo       = atealmalmm_entrada.getCodCirculoRegistral();
			il_idMatricula     = atealmalmm_entrada.getNumMatriculaInmobiliaria();
		}
	}

	/**
	 * Instancia un nuevo objeto alerta T predio.
	 *
	 * @param atecma_entrada de atecma entrada
	 */
	public AlertaTPredio(TipoEntradaConsultarMatriculaAlerta atecma_entrada)
	{
		if(atecma_entrada != null)
		{
			il_idAlertaTierras     = atecma_entrada.getIdAlerta();
			is_idCirculo           = atecma_entrada.getCodCirculoRegistral();
			il_idMatricula         = atecma_entrada.getNumMatriculaInmobiliaria();
		}
	}

	/**
	 * Retorna Objeto o variable de valor id alerta tierras.
	 *
	 * @return Retorna el valor de la propiedad idAlertaTierras
	 */
	public long getIdAlertaTierras()
	{
		return il_idAlertaTierras;
	}

	/**
	 * Modifica el valor de IdAlertaTierras.
	 *
	 * @param al_l de al l
	 */
	public void setIdAlertaTierras(long al_l)
	{
		il_idAlertaTierras = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return Retorna el valor de la propiedad idMatricula
	 */
	public long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l de al l
	 */
	public void setIdMatricula(long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return Retorna el valor de la propiedad estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s de as s
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre circulo.
	 *
	 * @return Retorna el valor de la propiedad nombreCirculo
	 */
	public String getNombreCirculo()
	{
		return is_nombreCirculo;
	}

	/**
	 * Modifica el valor de NombreCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setNombreCirculo(String as_s)
	{
		is_nombreCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero predial.
	 *
	 * @return Retorna el valor de la propiedad numeroPredial
	 */
	public String getNumeroPredial()
	{
		return is_numeroPredial;
	}

	/**
	 * Modifica el valor de NumeroPredial.
	 *
	 * @param as_s de as s
	 */
	public void setNumeroPredial(String as_s)
	{
		is_numeroPredial = as_s;
	}
}
