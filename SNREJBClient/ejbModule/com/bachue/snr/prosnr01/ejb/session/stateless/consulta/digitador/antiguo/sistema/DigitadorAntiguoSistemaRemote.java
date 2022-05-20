package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.digitador.antiguo.sistema;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.consulta.digitador.antiguo.sistema.DigitadorAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades DigitadorAntiguoSistemaRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface DigitadorAntiguoSistemaRemote
{
	/**
	 * Find acc matriculas by turno ant sistema.
	 *
	 * @param AccPredioRegistro de AccPredioRegistro
	 * * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_userId de as user id
	 * @return el valor de collection AccPredioRegistro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccPredioRegistro> findAccMatriculasByTurnoAntSistema(
	    AccPredioRegistro AccPredioRegistro, String as_userId
	)
	    throws B2BException;

	/**
	 * Find digitador antiguo sistema.
	 *
	 * @param AccPredioRegistro de predio registro
	 * @param al_etapa de etapa
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @return el valor de digitador antiguo sistema
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DigitadorAntiguoSistema findDigitadorAntiguoSistema(
	    AccPredioRegistro AccPredioRegistro, long al_etapa, String as_userId
	)
	    throws B2BException;

	/**
	 * Método encargado de permitir modificar el ordenamiento de las anotaciones por medio de la fecha de la última anotación.
	 *
	 * @param aca_anotacionesAgregadas Argumento de tipo <code>Collection</code> que contiene las anotaciones a calcular.
	 * @param aap_anotacionPredio Argumento de tipo <code>Anotacion</code> que contiene los criterios necesarios para realizar el cálculo.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota del usuario que realiza la operación.
	 * @return Colección de anotaciones que contiene las anotaciones modificadas.
	 * @throws B2BException Se genera cuando se produce una excepción.
	 */
	public Collection<Anotacion> validarReordenamientoPorFechaAnotacion(
	    Collection<Anotacion> aca_anotacionesAgregadas, AnotacionPredio aap_anotacionPredio, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
