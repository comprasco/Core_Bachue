package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.registro.Direccion;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_DIRECCION_PREDIO.
 *
 * @author ccalderon
 */
public class DireccionPredioAcc extends Direccion implements Serializable
{
	/**
	 * Constante serialVersionUID.
	 */
	private static final long serialVersionUID = 4985002930550275153L;

	/**
	 * Propiedad il id turno historia.
	 */
	private Long il_idTurnoHistoria;

	/**
	 * Propiedad is id turno.
	 */
	private String is_idTurno;

	/**
	 * Instancia un nuevo objeto direccion predio acc.
	 *
	 * @param ad_d de ad d
	 */
	public DireccionPredioAcc(Direccion ad_d)
	{
		if(ad_d != null)
		{
			setIdCirculo(ad_d.getIdCirculo());
			setIdMatricula(ad_d.getIdMatricula());
			setIdDireccion(ad_d.getIdDireccion());
			setIdDireccionPredio(ad_d.getIdDireccionPredio());
			setIdTipoPredio(ad_d.getIdTipoPredio());
			setIdTipoEjePrincipal(ad_d.getIdTipoEjePrincipal());
			setDatoEjePrincipal(ad_d.getDatoEjePrincipal());
			setIdLetraEjePrincipal(ad_d.getIdLetraEjePrincipal());
			setIdComplementoEjePrincipal(ad_d.getIdComplementoEjePrincipal());
			setIdCoordenadaEjePrincipal(ad_d.getIdCoordenadaEjePrincipal());
			setIdTipoEjeSecundario(ad_d.getIdTipoEjeSecundario());
			setDatoEjeSecundario(ad_d.getDatoEjeSecundario());
			setIdLetra1EjeSecundario(ad_d.getIdLetra1EjeSecundario());
			setIdComplemento1EjeSecundario(ad_d.getIdComplemento1EjeSecundario());
			setIdCoordenada1EjeSecundario(ad_d.getIdCoordenada1EjeSecundario());
			setDato2EjeSecundario(ad_d.getDato2EjeSecundario());
			setIdLetra2EjeSecundario(ad_d.getIdLetra2EjeSecundario());
			setIdComplemento2EjeSecundario(ad_d.getIdComplemento2EjeSecundario());
			setIdCoordenada2EjeSecundario(ad_d.getIdCoordenada2EjeSecundario());
			setIdComplementoDireccion(ad_d.getIdComplementoDireccion());
			setComplementoDireccion(ad_d.getComplementoDireccion());
			setDireccion(ad_d.getDireccion());
			setSeleccionado(ad_d.isSeleccionado());
			setIdDireccion(ad_d.getIdDireccion());
			setIdDireccionPredio(ad_d.getIdDireccionPredio());
		}
	}

	/**
	 * Instancia un nuevo objeto direccion predio acc.
	 */
	public DireccionPredioAcc()
	{
	}

	/**
	 * Modifica el valor de Id turno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Get id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de Id turno historia.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Get id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}
}
