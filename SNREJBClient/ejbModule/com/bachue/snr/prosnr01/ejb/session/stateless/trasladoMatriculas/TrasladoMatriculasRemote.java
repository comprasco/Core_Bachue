package com.bachue.snr.prosnr01.ejb.session.stateless.trasladoMatriculas;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades TrasladoMatriculasRemote.
 *
 * @author  Sebastian Sanchez
 * Fecha de Creacion: 24/06/2020
 */
@Remote
public interface TrasladoMatriculasRemote
{
	/**
	 * Termina la etapa actual y llama al proc crear etapa.
	 *
	 * @param ath_parametros de Objeto contenedor de la información de la etapa a terminar
	 * @param amt_motivoTramite Objeto contenedor del motivo a utilizar en la terminación de la etapa actual
	 * @param as_usuario Id del usuario que ejecuta la acción
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarEtapaYCrearSiguiente(
	    TurnoHistoria ath_parametros, MotivoTramite amt_motivoTramite, String as_usuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find detail inbox.
	 *
	 * @param as_userId de as user id
	 * @param al_idEtapa de al id etapa
	 * @param as_idTurno de as id turno
	 * @param as_nir de as nir
	 * @return el valor de list
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public List<Map<String, Object>> findDetailInbox(
	    String as_userId, Long al_idEtapa, String as_idTurno, String as_nir
	)
	    throws B2BException;

	/**
	 * Find inbox by user id.
	 *
	 * @param as_userId de as user id
	 * @param as_idTurno de as id turno
	 * @param as_nir de as nir
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteCantidad> findInboxByUserId(String as_userId, String as_idTurno, String as_nir)
	    throws B2BException;

	/**
	 * Método encargado de salvar los procesos seleccionados.
	 *
	 * @param alm_data de Argumento de tipo <code>List</code> que contiene la información de los procesos.
	 * @param as_usuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param al_idEtapa Argumento de tipo <code>long</code> que contiene el id de la etapa.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene el la ip desde donde se realiza la operación.
	 * @return el valor Objeto de tipo <code>List</code> que contiene la información actualizada de los procesos.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public List<Map<String, Object>> saveDetails(
	    List<Map<String, Object>> alm_data, String as_usuario, String as_localIp, long al_idEtapa, String as_remoteIp
	)
	    throws B2BException;
}
