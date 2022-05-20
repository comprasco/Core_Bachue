package com.bachue.snr.prosnr03.ejb.session.stateless.gestionUsuarios;

import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v1.TipoEntradaObtenerAccesosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v1.TipoSalidaObtenerAccesosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerconveniosentidad.v1.TipoEntradaObtenerConveniosEntidad;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerconveniosentidad.v1.TipoSalidaObtenerConveniosEntidad;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerentidadesconvenio.v1.TipoSalidaObtenerEntidadesConvenio;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v1.TipoEntradaObtenerModulosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v1.TipoSalidaObtenerModulosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerorips.v1.TipoSalidaObtenerORIPs;
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

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr03.business.gestionUsuarios.GestionUsuariosBusiness;

import com.bachue.snr.prosnr03.ejb.session.stateless.gestionUsuarios.GestionUsuariosRemote;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades GestionUsuariosBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 28/07/2019
 */
@javax.ejb.Stateless(name = "GestionUsuarios", mappedName = "gestionUsuariosMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class GestionUsuariosBean implements GestionUsuariosRemote
{
	/** Propiedad irb business. */
	private GestionUsuariosBusiness irb_business;

	/**
	 * Retorna el valor de gestion usuarios business.
	 *
	 * @return el valor de gestion usuarios business
	 */
	public GestionUsuariosBusiness getGestionUsuariosBusiness()
	{
		if(irb_business == null)
			irb_business = new GestionUsuariosBusiness();

		return irb_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaObtenerAccesosPorRol obtenerAccesosPorRol(
	    TipoEntradaObtenerAccesosPorRol ateoapr_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		TipoSalidaObtenerAccesosPorRol lceec_return;

		lsw_watch        = Logger.getNewStopWatch();
		lceec_return     = getGestionUsuariosBusiness()
				                   .obtenerAccesosPorRol(ateoapr_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "GestionUsuariosBean", "obtenerAccesosPorRol", as_userId, as_localIp, as_remoteIp, null);

		return lceec_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaObtenerConveniosEntidad obtenerConveniosEntidad(
	    TipoEntradaObtenerConveniosEntidad ateoce_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                         lsw_watch;
		TipoSalidaObtenerConveniosEntidad ltsoce_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsoce_return     = getGestionUsuariosBusiness()
				                    .obtenerConveniosEntidad(ateoce_request, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "GestionUsuariosBean", "obtenerConveniosEntidad", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsoce_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaObtenerEntidadesConvenio obtenerEntidadesConvenio(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		TipoSalidaObtenerEntidadesConvenio ltsoec_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsoec_return     = getGestionUsuariosBusiness().obtenerEntidadesConvenio(as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "GestionUsuariosBean", "obtenerEntidadesConvenio", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsoec_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaObtenerModulosPorRol obtenerModulosPorRol(
	    TipoEntradaObtenerModulosPorRol ateompr_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		TipoSalidaObtenerModulosPorRol lceec_return;

		lsw_watch        = Logger.getNewStopWatch();
		lceec_return     = getGestionUsuariosBusiness()
				                   .obtenerModulosPorRol(ateompr_request, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "GestionUsuariosBean", "obtenerModulosPorRol", as_userId, as_localIp, as_remoteIp, null);

		return lceec_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaObtenerORIPs obtenerORIPs(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TipoSalidaObtenerORIPs ltsoo_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltsoo_return     = getGestionUsuariosBusiness().obtenerORIPs(as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "GestionUsuariosBean", "obtenerORIPs", as_userId, as_localIp, as_remoteIp, null);

		return ltsoo_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaObtenerRoles obtenerRoles(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		TipoSalidaObtenerRoles ltsor_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltsor_return     = getGestionUsuariosBusiness().obtenerRoles(as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "GestionUsuariosBean", "obtenerRoles", as_userId, as_localIp, as_remoteIp, null);

		return ltsor_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaObtenerRolesComponente obtenerRolesComponente(
	    TipoEntradaObtenerRolesComponente ateorc_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		TipoSalidaObtenerRolesComponente ltsorc_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsorc_return     = getGestionUsuariosBusiness()
				                    .obtenerRolesComponente(ateorc_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "GestionUsuariosBean", "obtenerRolesComponente", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsorc_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaObtenerRolesUsuario obtenerRolesUsuario(
	    TipoEntradaObtenerRolesUsuario ateoru_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		TipoSalidaObtenerRolesUsuario ltsoru_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsoru_return     = getGestionUsuariosBusiness()
				                    .obtenerRolesUsuario(ateoru_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "GestionUsuariosBean", "obtenerRolesUsuario", as_userId, as_localIp, as_remoteIp, null);

		return ltsoru_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaObtenerUsuariosRolOrip obtenerUsuariosRolOrip(
	    TipoEntradaObtenerUsuariosRolOrip ateouro_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		TipoSalidaObtenerUsuariosRolOrip lceec_return;

		lsw_watch        = Logger.getNewStopWatch();
		lceec_return     = getGestionUsuariosBusiness()
				                   .obtenerUsuariosRolOrip(ateouro_request, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "GestionUsuariosBean", "obtenerUsuariosRolOrip", as_userId, as_localIp, as_remoteIp, null
		);

		return lceec_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaRegistrarUsuario registrarUsuario(
	    TipoEntradaRegistrarUsuario ateru_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaRegistrarUsuario ltsru_return;
		StopWatch                  lsw_watch;

		lsw_watch        = Logger.getNewStopWatch();
		ltsru_return     = getGestionUsuariosBusiness()
				                   .registrarUsuario(ateru_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "GestionUsuariosBean", "registrarUsuario", as_userId, as_localIp, as_remoteIp, null);

		return ltsru_return;
	}
}
