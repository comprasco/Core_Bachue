package co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.cliente.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obteneraccesosporrol.v1.TipoEntradaObtenerAccesosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obteneraccesosporrol.v1.TipoSalidaObtenerAccesosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerconveniosentidad.v1.TipoEntradaObtenerConveniosEntidad;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerconveniosentidad.v1.TipoSalidaObtenerConveniosEntidad;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerentidadesconvenio.v1.EntradaObtenerEntidadesConvenio;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerentidadesconvenio.v1.TipoSalidaObtenerEntidadesConvenio;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenermodulosporrol.v1.TipoEntradaObtenerModulosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenermodulosporrol.v1.TipoSalidaObtenerModulosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerorips.v1.EntradaObtenerORIPs;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerorips.v1.TipoSalidaObtenerORIPs;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerroles.v1.EntradaObtenerRoles;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerroles.v1.TipoSalidaObtenerRoles;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerrolescomponente.v1.TipoEntradaObtenerRolesComponente;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerrolescomponente.v1.TipoSalidaObtenerRolesComponente;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerrolesusuario.v1.TipoEntradaObtenerRolesUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerrolesusuario.v1.TipoSalidaObtenerRolesUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerusuariosrolorip.v1.TipoEntradaObtenerUsuariosRolOrip;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerusuariosrolorip.v1.TipoSalidaObtenerUsuariosRolOrip;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.registrarusuario.v1.TipoEntradaRegistrarUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.registrarusuario.v1.TipoSalidaRegistrarUsuario;


public class SUT_CB_GestionUsuariosProxy implements SUT_CB_GestionUsuarios
{
	private SUT_CB_GestionUsuarios sUT_CB_GestionUsuarios = null;
	private String                 _endpoint              = null;

	public SUT_CB_GestionUsuariosProxy()
	{
		_initSUT_CB_GestionUsuariosProxy();
	}

	public SUT_CB_GestionUsuariosProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initSUT_CB_GestionUsuariosProxy();
	}

	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;

		if(sUT_CB_GestionUsuarios != null)
			((javax.xml.rpc.Stub)sUT_CB_GestionUsuarios)._setProperty(
			    "javax.xml.rpc.service.endpoint.address", _endpoint
			);
	}

	public String getEndpoint()
	{
		return _endpoint;
	}

	public SUT_CB_GestionUsuarios getSUT_CB_GestionUsuarios()
	{
		if(sUT_CB_GestionUsuarios == null)
			_initSUT_CB_GestionUsuariosProxy();

		return sUT_CB_GestionUsuarios;
	}

	public TipoSalidaObtenerAccesosPorRol obtenerAccesosPorRol(TipoEntradaObtenerAccesosPorRol entrada)
	    throws java.rmi.RemoteException
	{
		if(sUT_CB_GestionUsuarios == null)
			_initSUT_CB_GestionUsuariosProxy();

		return sUT_CB_GestionUsuarios.obtenerAccesosPorRol(entrada);
	}

	public TipoSalidaObtenerConveniosEntidad obtenerConveniosEntidad(TipoEntradaObtenerConveniosEntidad entrada)
	    throws java.rmi.RemoteException
	{
		if(sUT_CB_GestionUsuarios == null)
			_initSUT_CB_GestionUsuariosProxy();

		return sUT_CB_GestionUsuarios.obtenerConveniosEntidad(entrada);
	}

	public TipoSalidaObtenerEntidadesConvenio obtenerEntidadesConvenio(EntradaObtenerEntidadesConvenio entrada)
	    throws java.rmi.RemoteException
	{
		if(sUT_CB_GestionUsuarios == null)
			_initSUT_CB_GestionUsuariosProxy();

		return sUT_CB_GestionUsuarios.obtenerEntidadesConvenio(entrada);
	}

	public TipoSalidaObtenerModulosPorRol obtenerModulosPorRol(TipoEntradaObtenerModulosPorRol entrada)
	    throws java.rmi.RemoteException
	{
		if(sUT_CB_GestionUsuarios == null)
			_initSUT_CB_GestionUsuariosProxy();

		return sUT_CB_GestionUsuarios.obtenerModulosPorRol(entrada);
	}

	public TipoSalidaObtenerORIPs obtenerORIPs(EntradaObtenerORIPs entrada)
	    throws java.rmi.RemoteException
	{
		if(sUT_CB_GestionUsuarios == null)
			_initSUT_CB_GestionUsuariosProxy();

		return sUT_CB_GestionUsuarios.obtenerORIPs(entrada);
	}

	public TipoSalidaObtenerRoles obtenerRoles(EntradaObtenerRoles entrada)
	    throws java.rmi.RemoteException
	{
		if(sUT_CB_GestionUsuarios == null)
			_initSUT_CB_GestionUsuariosProxy();

		return sUT_CB_GestionUsuarios.obtenerRoles(entrada);
	}

	public TipoSalidaObtenerRolesComponente obtenerRolesComponente(TipoEntradaObtenerRolesComponente entrada)
	    throws java.rmi.RemoteException
	{
		if(sUT_CB_GestionUsuarios == null)
			_initSUT_CB_GestionUsuariosProxy();

		return sUT_CB_GestionUsuarios.obtenerRolesComponente(entrada);
	}

	public TipoSalidaObtenerRolesUsuario obtenerRolesUsuario(TipoEntradaObtenerRolesUsuario entrada)
	    throws java.rmi.RemoteException
	{
		if(sUT_CB_GestionUsuarios == null)
			_initSUT_CB_GestionUsuariosProxy();

		return sUT_CB_GestionUsuarios.obtenerRolesUsuario(entrada);
	}

	public TipoSalidaObtenerUsuariosRolOrip obtenerUsuariosRolOrip(TipoEntradaObtenerUsuariosRolOrip entrada)
	    throws java.rmi.RemoteException
	{
		if(sUT_CB_GestionUsuarios == null)
			_initSUT_CB_GestionUsuariosProxy();

		return sUT_CB_GestionUsuarios.obtenerUsuariosRolOrip(entrada);
	}

	public TipoSalidaRegistrarUsuario registrarUsuario(TipoEntradaRegistrarUsuario entrada)
	    throws java.rmi.RemoteException
	{
		if(sUT_CB_GestionUsuarios == null)
			_initSUT_CB_GestionUsuariosProxy();

		return sUT_CB_GestionUsuarios.registrarUsuario(entrada);
	}

	private void _initSUT_CB_GestionUsuariosProxy()
	{
		try
		{
			sUT_CB_GestionUsuarios = (new SUT_CB_GestionUsuariosSOAP12BindingQSServiceLocator())
					.getSUT_CB_GestionUsuariosSOAP12BindingQSPort();

			if(sUT_CB_GestionUsuarios != null)
			{
				if(_endpoint != null)
					((javax.xml.rpc.Stub)sUT_CB_GestionUsuarios)._setProperty(
					    "javax.xml.rpc.service.endpoint.address", _endpoint
					);
				else
					_endpoint = (String)((javax.xml.rpc.Stub)sUT_CB_GestionUsuarios)._getProperty(
						    "javax.xml.rpc.service.endpoint.address"
						);
			}
		}
		catch(javax.xml.rpc.ServiceException serviceException)
		{
		}
	}
}
