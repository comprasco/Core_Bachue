/**
 * SUT_CB_GestionUsuarios.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.cliente.v1;

public interface SUT_CB_GestionUsuarios extends java.rmi.Remote
{
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obteneraccesosporrol.v1.TipoSalidaObtenerAccesosPorRol obtenerAccesosPorRol(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obteneraccesosporrol.v1.TipoEntradaObtenerAccesosPorRol entrada
	)
	    throws java.rmi.RemoteException;

	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerconveniosentidad.v1.TipoSalidaObtenerConveniosEntidad obtenerConveniosEntidad(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerconveniosentidad.v1.TipoEntradaObtenerConveniosEntidad entrada
	)
	    throws java.rmi.RemoteException;

	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerentidadesconvenio.v1.TipoSalidaObtenerEntidadesConvenio obtenerEntidadesConvenio(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerentidadesconvenio.v1.EntradaObtenerEntidadesConvenio entrada
	)
	    throws java.rmi.RemoteException;

	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenermodulosporrol.v1.TipoSalidaObtenerModulosPorRol obtenerModulosPorRol(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenermodulosporrol.v1.TipoEntradaObtenerModulosPorRol entrada
	)
	    throws java.rmi.RemoteException;

	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerorips.v1.TipoSalidaObtenerORIPs obtenerORIPs(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerorips.v1.EntradaObtenerORIPs entrada
	)
	    throws java.rmi.RemoteException;

	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerroles.v1.TipoSalidaObtenerRoles obtenerRoles(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerroles.v1.EntradaObtenerRoles entrada
	)
	    throws java.rmi.RemoteException;

	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerrolescomponente.v1.TipoSalidaObtenerRolesComponente obtenerRolesComponente(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerrolescomponente.v1.TipoEntradaObtenerRolesComponente entrada
	)
	    throws java.rmi.RemoteException;

	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerrolesusuario.v1.TipoSalidaObtenerRolesUsuario obtenerRolesUsuario(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerrolesusuario.v1.TipoEntradaObtenerRolesUsuario entrada
	)
	    throws java.rmi.RemoteException;

	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerusuariosrolorip.v1.TipoSalidaObtenerUsuariosRolOrip obtenerUsuariosRolOrip(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerusuariosrolorip.v1.TipoEntradaObtenerUsuariosRolOrip entrada
	)
	    throws java.rmi.RemoteException;

	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.registrarusuario.v1.TipoSalidaRegistrarUsuario registrarUsuario(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.registrarusuario.v1.TipoEntradaRegistrarUsuario entrada
	)
	    throws java.rmi.RemoteException;
}
