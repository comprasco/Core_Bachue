package com.bachue.snr.prosnr01.ejb.session.stateless.reasignar;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.reasignar.ReasignarTurnos;
import com.bachue.snr.prosnr01.model.sdb.aut.Rol;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ReasignarTurnosRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface ReasignarTurnosRemote
{
	/**
	 * Método encargado de actualizar el campo REASIGNADO_ESPECIAL de la tabla SDB_ACC_TURNO.
	 *
	 * @param acrt_data Colección Tipo ReasignarTurnos con todos los turnos de la colección
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarReasignadoEspecial(
	    Collection<ReasignarTurnos> acrt_data, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para consultar etapas.
	 *
	 * @param as_idRol String idRol con el rol a consultar
	 * @return Colección de Etapas consultadas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Etapa> consultarEtapas(String as_idRol)
	    throws B2BException;

	/**
	 * Método encargado de enviar casos a etapa sustanciador de actuaciones administrativas.
	 *
	 * @param ar_reasignarTurnos Argumento de tipo <code>ReasignarTurnos</code> que contiene los valores necesarios para asignar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarAbogadoSustanciador(
	    ReasignarTurnos ar_reasignarTurnos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de extraer individualmente el registro seleccionado.
	 *
	 * @param acrt_data de Collection<ReasignarTurnos>
	 * @return lrt_return Objeto ResignarTurnos con el turno extraído
	 */
	public ReasignarTurnos extraerSeleccionado(Collection<ReasignarTurnos> acrt_data);

	/**
	 * Buscar todo por etapa usuario.
	 *
	 * @param al_etapa de id etapa
	 * @param as_idCirculo de id circulo
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param ab_porCantidad de busqueda por cantidad
	 * @param ai_cantidad de cantidad
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ReasignarTurnos> findAllByEtapaUsuario(
	    long al_etapa, String as_idCirculo, String as_idUsuario, boolean ab_porCantidad, Integer ai_cantidad
	)
	    throws B2BException;

	/**
	 * Buscar todo por etapa usuario.
	 *
	 * @param al_etapa de id etapa
	 * @param as_idCirculo de id circulo
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param ab_porCantidad de busqueda por cantidad
	 * @param ai_cantidad de cantidad
	 * @param ab_opcionSelected de busqueda por opcion seleccionada
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ReasignarTurnos> findAllByEtapaUsuario(
	    long al_etapa, String as_idCirculo, String as_idUsuario, boolean ab_porCantidad, Integer ai_cantidad,
	    boolean ab_opcionSelected
	)
	    throws B2BException;

	/**
	 * Buscar todos los roles.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Rol> findAllRoles()
	    throws B2BException;

	/**
	 * Método para consultar roles de recursos
	 * @return collección con los roles
	 * @throws B2BException
	 */
	public Collection<Rol> findAllRolesRecursos()
	    throws B2BException;

	/**
	 * Método para consultar roles de segunda instancia
	 * @return collección con los roles
	 * @throws B2BException
	 */
	public Collection<Rol> findAllRolesSegundaInstancia()
	    throws B2BException;

	/**
	 * buscar circulos desborde.
	 *
	 * @param as_idCirculoSelected de id circulo selected
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CirculoRegistral> findCirculosDesborde(String as_idCirculoSelected)
	    throws B2BException;

	/**
	 * buscar nombre etapa.
	 *
	 * @param as_idEtapa de id etapa
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String findNombreEtapa(String as_idEtapa, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Buscar todos los usuarios por  etapa filtrado por estado usuario de AUT USUARIO ROL.
	 *
	 * @param as_idEtapa de id etapa
	 * @param as_idRol de id rol
	 * @param as_idCirculo de id circulo
	 * @param ab_rolCirculoActivo de busqueda rol circulo activo
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Usuario> findUsuariosByEtapaRolCirculo(
	    long as_idEtapa, String as_idRol, String as_idCirculo, boolean ab_rolCirculoActivo
	)
	    throws B2BException;

	/**
	 * Reasignar turnos.
	 *
	 * @param ic_data de Collection<ReasignarTurnos>
	 * @param as_idCirculo de id circulo
	 * @param as_idRol de id rol
	 * @param as_idEtapa de id etapa
	 * @param as_idUsuarioSelected de id usuario selected
	 * @param as_idUsuarioAsignar de id usuario asignar
	 * @param as_observaciones de observaciones
	 * @param as_justificacionReasignadoEspecial de justificacion reasignado especial
	 * @param ab_paramAutomatico de parametro automatico
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ReasignarTurnos> reasignarTurnos(
	    Collection<ReasignarTurnos> ic_data, String as_idCirculo, String as_idRol, String as_idEtapa,
	    String as_idUsuarioSelected, String as_idUsuarioAsignar, String as_observaciones,
	    String as_justificacionReasignadoEspecial, boolean ab_paramAutomatico, String as_idUsuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método que valida si los turnos vinculados del turno seleccionado están bloqueados.
	 *
	 * @param acrt_data Colección Tipo ReasignarTurnos con todos los turnos de la colección
	 * @return boolean con confirmación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarTurnosVinculadosBloqueados(Collection<ReasignarTurnos> acrt_data)
	    throws B2BException;
}
