package com.bachue.snr.prosnr01.web.bean.reusableScreens;

import java.util.Map;
import com.b2bsg.common.exception.B2BException;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;


/**
 * Interface que contiene todos las propiedades HeaderDatosDelTurnoInterface.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 23/10/2021
 */
public interface HeaderDatosDelTurnoInterface
{
	/**
	 * Modifica el valor de IdTurnoConsulta.
	 *
	 * @param as_s de as s
	 */
	public void setIdTurnoConsulta(String as_s);

	/**
	 * Retorna Objeto o variable de valor id turno consulta.
	 *
	 * @return el valor de id turno consulta
	 */
	public String getIdTurnoHistoria();

	/**
	 * Modifica el valor de TurnoHistoria.
	 *
	 * @param ath_th de ath th
	 */
	public void setIdTurnoHistoria(String as_s);

	/**
	 * Retorna Objeto o variable de valor turno historia.
	 *
	 * @return el valor de turno historia
	 */

	/**
	    * Modifica el valor de DecisionCalificacion.
	    *
	    * @param as_s de as s
	    */
	public void setDecisionCalificacion(String as_s);

	/**
	 * Retorna Objeto o variable de valor decision calificacion.
	 *
	 * @return el valor de decision calificacion
	 */
	public String getDecisionCalificacion();

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return el valor de id turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String getIdTurno()
	    throws B2BException;

	/**
	    * Modifica el valor de IdTurno.
	    *
	    * @param as_s de as s
	    */
	public void setIdTurno(String as_s);

	/**
	 * Retorna Objeto o variable de valor predio.
	 *
	 * @return el valor de predio
	 */
	public Map<String, Object> getPredio();

	/**
	 * Sets the predio.
	 *
	 * @param ahmso_hmso de ahmso hmso
	 */
	public void setPredio(Map<String, Object> ahmso_hmso);

	/**
	 * Retorna Objeto o variable de valor turno.
	 *
	 * @return el valor de turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Turno getTurno()
	    throws B2BException;

	/**
	 * Modifica el valor de Turno.
	 *
	 * @param at_t de at t
	 */
	public void setTurno(Turno at_t);

	/**
	 * Consulta SGD.
	 *
	 * @return el valor de string
	 */
	public String consultaSGD();

	/**
	 * Dirigir trazabilidad.
	 *
	 * @return el valor de string
	 */
	public String dirigirTrazabilidad();
}
