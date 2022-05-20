/**
 * SUT_CB_GestionUsuarios_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.v1;


/**
 * Interface que contiene todos las propiedades SUT_CB_GestionUsuarios_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface SUT_CB_GestionUsuarios_PortType extends java.rmi.Remote
{
	/**
	 * Obtener accesos por rol.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v 1 . tipo salida obtener accesos por rol
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v1.TipoSalidaObtenerAccesosPorRol obtenerAccesosPorRol(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v1.TipoEntradaObtenerAccesosPorRol entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Obtener convenios entidad.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerconveniosentidad.v 1 . tipo salida obtener convenios entidad
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerconveniosentidad.v1.TipoSalidaObtenerConveniosEntidad obtenerConveniosEntidad(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerconveniosentidad.v1.TipoEntradaObtenerConveniosEntidad entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Obtener entidades convenio.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerentidadesconvenio.v 1 . tipo salida obtener entidades convenio
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerentidadesconvenio.v1.TipoSalidaObtenerEntidadesConvenio obtenerEntidadesConvenio(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerentidadesconvenio.v1.EntradaObtenerEntidadesConvenio entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Obtener modulos por rol.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v 1 . tipo salida obtener modulos por rol
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v1.TipoSalidaObtenerModulosPorRol obtenerModulosPorRol(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v1.TipoEntradaObtenerModulosPorRol entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Obtener ORI ps.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerorips.v 1 . tipo salida obtener ORI ps
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerorips.v1.TipoSalidaObtenerORIPs obtenerORIPs(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerorips.v1.EntradaObtenerORIPs entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Obtener roles.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerroles.v 1 . tipo salida obtener roles
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerroles.v1.TipoSalidaObtenerRoles obtenerRoles(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerroles.v1.EntradaObtenerRoles entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Obtener roles componente.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolescomponente.v 1 . tipo salida obtener roles componente
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolescomponente.v1.TipoSalidaObtenerRolesComponente obtenerRolesComponente(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolescomponente.v1.TipoEntradaObtenerRolesComponente entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Obtener roles usuario.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolesusuario.v 1 . tipo salida obtener roles usuario
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolesusuario.v1.TipoSalidaObtenerRolesUsuario obtenerRolesUsuario(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolesusuario.v1.TipoEntradaObtenerRolesUsuario entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Obtener usuarios rol orip.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerusuariosrolorip.v 1 . tipo salida obtener usuarios rol orip
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerusuariosrolorip.v1.TipoSalidaObtenerUsuariosRolOrip obtenerUsuariosRolOrip(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerusuariosrolorip.v1.TipoEntradaObtenerUsuariosRolOrip entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Registrar usuario.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v 1 . tipo salida registrar usuario
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoSalidaRegistrarUsuario registrarUsuario(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuario entrada
	)
	    throws java.rmi.RemoteException;
}
