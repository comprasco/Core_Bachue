package co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v1.TipoEntradaObtenerAccesosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v1.TipoSalidaObtenerAccesosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerconveniosentidad.v1.TipoEntradaObtenerConveniosEntidad;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerconveniosentidad.v1.TipoSalidaObtenerConveniosEntidad;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerentidadesconvenio.v1.EntradaObtenerEntidadesConvenio;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerentidadesconvenio.v1.TipoSalidaObtenerEntidadesConvenio;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v1.TipoEntradaObtenerModulosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v1.TipoSalidaObtenerModulosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerorips.v1.EntradaObtenerORIPs;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerorips.v1.TipoSalidaObtenerORIPs;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerroles.v1.EntradaObtenerRoles;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerroles.v1.TipoSalidaObtenerRoles;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolescomponente.v1.TipoEntradaObtenerRolesComponente;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolescomponente.v1.TipoSalidaObtenerRolesComponente;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolesusuario.v1.TipoEntradaObtenerRolesUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolesusuario.v1.TipoSalidaObtenerRolesUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerusuariosrolorip.v1.TipoEntradaObtenerUsuariosRolOrip;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerusuariosrolorip.v1.TipoSalidaObtenerUsuariosRolOrip;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoSalidaRegistrarUsuario;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades SUT_CB_GestionUsuariosSOAP12BindingImpl.
 *
 * Servicio que permite intercambiar información dentro del proceso de creación y/o modificación de los usuarios en CA
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 28/07/2019
 */
public class SUT_CB_GestionUsuariosSOAP12BindingImpl extends BaseServices implements SUT_CB_GestionUsuarios_PortType
{
	/**
	 * Constante serialVersionUID.
	 */
	private static final long serialVersionUID = 5992653919188265914L;

	/**
	 * Constante clh_LOGGER.
	 */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SUT_CB_GestionUsuariosSOAP12BindingImpl.class);

	/**
	 * Permite obtener los accesos a partir de un rol dado.
	 * -    Para cada rol existirá las opciones del menú que al que éste pueda acceder.
	 *
	 * @param ateoapr_entrada de ateoapr entrada
	 * @return el valor de tipo salida obtener accesos por rol
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaObtenerAccesosPorRol obtenerAccesosPorRol(TipoEntradaObtenerAccesosPorRol ateoapr_entrada)
	    throws RemoteException
	{
		TipoSalidaObtenerAccesosPorRol ltsoapr_salida;

		ltsoapr_salida = new TipoSalidaObtenerAccesosPorRol();

		try
		{
			ltsoapr_salida = getGestionUsuariosRemote()
					                 .obtenerAccesosPorRol(
					    ateoapr_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerAccesosPorRol", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerAccesosPorRol", le_e);
		}

		return ltsoapr_salida;
	}

	/**
	 * Permite obtener los convenios asociados a una entidad.
	 *
	 * @param lteoce_request de lteoce request
	 * @return el valor de tipo salida obtener convenios entidad
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaObtenerConveniosEntidad obtenerConveniosEntidad(TipoEntradaObtenerConveniosEntidad lteoce_request)
	    throws RemoteException
	{
		TipoSalidaObtenerConveniosEntidad ltsoce_return;

		ltsoce_return = null;

		try
		{
			ltsoce_return = getGestionUsuariosRemote()
					                .obtenerConveniosEntidad(
					    lteoce_request, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerConveniosEntidad", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerConveniosEntidad", le_e);
		}

		return ltsoce_return;
	}

	/**
	 * Permite obtener las entidades habilitadas para asociar usuarios al sistema Bachué.
	 *
	 * @param aeoec_entrada de aeoec entrada
	 * @return el valor de tipo salida obtener entidades convenio
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaObtenerEntidadesConvenio obtenerEntidadesConvenio(EntradaObtenerEntidadesConvenio aeoec_entrada)
	    throws RemoteException
	{
		TipoSalidaObtenerEntidadesConvenio ltsoo_return;

		ltsoo_return = null;

		try
		{
			ltsoo_return = getGestionUsuariosRemote()
					               .obtenerEntidadesConvenio(getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerEntidadesConvenio", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerEntidadesConvenio", le_e);
		}

		return ltsoo_return;
	}

	/**
	 * Permite obtener los módulos o componentes a partir de un rol dado.
	 * -    Para cada rol existirá un módulo o componente al que el rol dado pueda ingresar.
	 *
	 * @param ateompr_entrada de ateompr entrada
	 * @return el valor de tipo salida obtener modulos por rol
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaObtenerModulosPorRol obtenerModulosPorRol(TipoEntradaObtenerModulosPorRol ateompr_entrada)
	    throws RemoteException
	{
		TipoSalidaObtenerModulosPorRol ltsoo_return;

		ltsoo_return = null;

		try
		{
			ltsoo_return = getGestionUsuariosRemote()
					               .obtenerModulosPorRol(
					    ateompr_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerModulosPorRol", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerModulosPorRol", le_e);
		}

		return ltsoo_return;
	}

	/**
	 * Obtiene la información de las ORPs activas en Bachué.
	 *
	 * @param aeoo_entrada de aeoo entrada
	 * @return el valor de tipo salida obtener ORI ps
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaObtenerORIPs obtenerORIPs(EntradaObtenerORIPs aeoo_entrada)
	    throws RemoteException
	{
		TipoSalidaObtenerORIPs ltsoo_return;

		ltsoo_return = null;

		try
		{
			ltsoo_return = getGestionUsuariosRemote()
					               .obtenerORIPs(getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerORIPs", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerORIPs", le_e);
		}

		return ltsoo_return;
	}

	/**
	 * Obtiene la información de los roles activos en Bachué.
	 *
	 * @param entrada de entrada
	 * @return el valor de tipo salida obtener roles
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaObtenerRoles obtenerRoles(EntradaObtenerRoles entrada)
	    throws RemoteException
	{
		TipoSalidaObtenerRoles ltsoce_return;

		ltsoce_return = null;

		try
		{
			ltsoce_return = getGestionUsuariosRemote()
					                .obtenerRoles(getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerRoles", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerRoles", le_e);
		}

		return ltsoce_return;
	}

	/**
	 * Permite obtener los roles que perteneces a una misma agrupación funcional:
	 * -    Cada rol debe pertenecer a una agrupación funcional.
	 * -    Las agrupaciones reúnen roles relacionados funcionalmente para facilitar los procesos de asignación de permisos.
	 *
	 * @param entrada de entrada
	 * @return el valor de tipo salida obtener roles componente
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaObtenerRolesComponente obtenerRolesComponente(TipoEntradaObtenerRolesComponente entrada)
	    throws RemoteException
	{
		TipoSalidaObtenerRolesComponente ltsoo_return;

		ltsoo_return = null;

		try
		{
			ltsoo_return = getGestionUsuariosRemote()
					               .obtenerRolesComponente(
					    entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerRolesComponente", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerRolesComponente", le_e);
		}

		return ltsoo_return;
	}

	/**
	 * Permite obtener los roles que tiene asignados un usuario en el core bachue, esto para validar
	 * los permisos que tiene en la aplicación solicitante:
	 * -    El usuario debe tener al menos un Rol.
	 * -    Los roles están reunidos en agrupaciones de acuerdo a su funcionalidad
	 *
	 * @param entrada de entrada
	 * @return el valor de tipo salida obtener roles usuario
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaObtenerRolesUsuario obtenerRolesUsuario(TipoEntradaObtenerRolesUsuario entrada)
	    throws RemoteException
	{
		TipoSalidaObtenerRolesUsuario ltsoo_return;

		ltsoo_return = null;

		try
		{
			ltsoo_return = getGestionUsuariosRemote()
					               .obtenerRolesUsuario(
					    entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerRolesUsuario", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerRolesUsuario", le_e);
		}

		return ltsoo_return;
	}

	/**
	 * Permite obtener los usuarios que tiene asignados un rolo y un circulo en el core bachue.
	 *
	 * @param ateouro_entrada de ateouro entrada
	 * @return el valor de tipo salida obtener usuarios rol orip
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaObtenerUsuariosRolOrip obtenerUsuariosRolOrip(TipoEntradaObtenerUsuariosRolOrip ateouro_entrada)
	    throws RemoteException
	{
		TipoSalidaObtenerUsuariosRolOrip ltsouro_return;

		ltsouro_return = null;

		try
		{
			ltsouro_return = getGestionUsuariosRemote()
					                 .obtenerUsuariosRolOrip(
					    ateouro_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerRolesUsuario", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerRolesUsuario", le_e);
		}

		return ltsouro_return;
	}

	/**
	 * Registra la información almacenada en CA relacionada a la creación y/o modificación de los atributos del usuario.
	 * Previo al registro en Bachué, se realizan las siguientes validaciones de negocio identificadas a la fecha:
	 * -    Una ORIP no puede tener más de un registrador.
	 * -    Una notaría no puede tener más de un notario titular.
	 * -    Una notaría no puede tener más de un notario encargado.
	 * -    No puede existir una solicitud de creación de un usuario (funcionario) asociado actualmente a un rol y a una ORIP en el sistema.
	 *
	 * @param entrada de entrada
	 * @return el valor de tipo salida registrar usuario
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaRegistrarUsuario registrarUsuario(TipoEntradaRegistrarUsuario entrada)
	    throws RemoteException
	{
		TipoSalidaRegistrarUsuario ltsoo_return;

		ltsoo_return = null;

		try
		{
			ltsoo_return = getGestionUsuariosRemote()
					               .registrarUsuario(entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("registrarUsuario", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("registrarUsuario", le_e);
		}

		return ltsoo_return;
	}
}
