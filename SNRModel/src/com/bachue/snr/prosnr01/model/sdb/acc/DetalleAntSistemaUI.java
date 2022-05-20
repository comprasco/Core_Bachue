package com.bachue.snr.prosnr01.model.sdb.acc;

import java.io.Serializable;

import java.util.Collection;



/**
 * Modelo UI creado para mostrar detalle de antiguo sistema junto a las matriculas de cada solicitud.
 * @author hcastaneda
 *
 */
public class DetalleAntSistemaUI extends DetalleAntSistema implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long           serialVersionUID                = -1205999360184101742L;
	
	/** Propiedad icdas matriculas. */
	private Collection<DatosAntSistema> icdas_matriculas;
	
	/** Propiedad idas datos ant sistema. */
	private DatosAntSistema             idas_datosAntSistema;
	
	/** Propiedad is id proceso. */
	private String                      is_idProceso;
	
	/** Propiedad ib asociar matricula documento. */
	private boolean                     ib_asociarMatriculaDocumento;
	
	/** Propiedad ib detalle matricula seleccionado. */
	private boolean                     ib_detalleMatriculaSeleccionado;
	
	/** Propiedad ib eliminar. */
	private boolean                     ib_eliminar;
	
	/** Propiedad ib seleccione. */
	private boolean                     ib_seleccione;

	/**
	 * Constructor que recibe por parametro un objeto de Detalle antiguo sistema.
	 *
	 * @param adas_das objeto detalle antiguo sistema
	 * @see com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema
	 */
	public DetalleAntSistemaUI(DetalleAntSistema adas_das)
	{
		if(adas_das != null)
		{
			setIdLibroAntSistema(adas_das.getIdLibroAntSistema());
			setNumeroPartida(adas_das.getNumeroPartida());
			setIdMatricula(adas_das.getIdMatricula());
			setActivo(adas_das.getActivo());
			setAnio(adas_das.getAnio());
			setConsecutivoPredioAntSistema(adas_das.getConsecutivoPredioAntSistema());
			setFolio(adas_das.getFolio());
			setIdDatosAntSistema(adas_das.getIdDatosAntSistema());
			setIdDetalleAntSistema(adas_das.getIdDetalleAntSistema());
			setIdDocumentoTradicion(adas_das.getIdDocumentoTradicion());
			setIdMunicipioTomo(adas_das.getIdMunicipioTomo());
			setNombreLibro(adas_das.getNombreLibro());
			setPartida(adas_das.getPartida());
			setTomo(adas_das.getTomo());
			setVersionDocumentoTradicion(adas_das.getVersionDocumentoTradicion());
			setNombrePredio(adas_das.getNombrePredio());
			setDocumentoConsulta(adas_das.getDocumentoConsulta());
		}
	}

	/**
	 * Constructor por defecto.
	 */
	public DetalleAntSistemaUI()
	{
	}

	/**
	 * Modifica el valor de AsociarMatriculaDocumento.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setAsociarMatriculaDocumento(boolean ab_b)
	{
		ib_asociarMatriculaDocumento                                    = ab_b;
	}

	/**
	 * Valida la propiedad asociar matricula documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isAsociarMatriculaDocumento()
	{
		return ib_asociarMatriculaDocumento;
	}

	/**
	 * Modifica el valor de DatosAntSistema.
	 *
	 * @param adas_das asigna el valor a la propiedad
	 */
	public void setDatosAntSistema(DatosAntSistema adas_das)
	{
		idas_datosAntSistema = adas_das;
	}

	/**
	 * Retorna Objeto o variable de valor datos ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public DatosAntSistema getDatosAntSistema()
	{
		return idas_datosAntSistema;
	}

	/**
	 * Modifica el valor de DetalleMatriculaSeleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setDetalleMatriculaSeleccionado(boolean ab_b)
	{
		ib_detalleMatriculaSeleccionado = ab_b;
	}

	/**
	 * Valida la propiedad detalle matricula seleccionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isDetalleMatriculaSeleccionado()
	{
		return ib_detalleMatriculaSeleccionado;
	}

	/**
	 * Modifica el valor de Eliminar.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setEliminar(boolean ab_b)
	{
		ib_eliminar = ab_b;
	}

	/**
	 * Valida la propiedad eliminar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isEliminar()
	{
		return ib_eliminar;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
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
	 * Modifica el valor de Matriculas.
	 *
	 * @param acdas_das asigna el valor a la propiedad
	 */
	public void setMatriculas(Collection<DatosAntSistema> acdas_das)
	{
		icdas_matriculas = acdas_das;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<DatosAntSistema> getMatriculas()
	{
		return icdas_matriculas;
	}

	/**
	 * Modifica el valor de Seleccione.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setSeleccione(boolean ab_b)
	{
		ib_seleccione = ab_b;
	}

	/**
	 * Valida la propiedad seleccione.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSeleccione()
	{
		return ib_seleccione;
	}
}
