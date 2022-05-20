package co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoEntradaActualizarEntidad;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoSalidaActualizarEntidad;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.cancelarusuario.v1.TipoEntradaCancelarUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.cancelarusuario.v1.TipoSalidaCancelarUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoEntradaConsultarSaldosNotaCredito;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCredito;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1.TipoEntradaConsultarIDCuentaCupo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1.TipoSalidaConsultarIDCuentaCupo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoEntradaConsultarMovimientos;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoSalidaConsultarMovimientos;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoEntradaConsultarSaldo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoSalidaConsultarSaldo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoEntradaConsultarUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoSalidaConsultarUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoEntradaConsultarUsuarios;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoSalidaConsultarUsuarios;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoEntradaInscribirUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoSalidaInscribirUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.pagarcuentacupo.v1.TipoEntradaPagarCuentaCupo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.pagarcuentacupo.v1.TipoSalidaPagarCuentaCupo;

import co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1.SBB_CB_GestionCuentaCupos_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades SBB_CB_GestionCuentaCuposSOAP12BindingImpl.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 10/09/2020
 */
public class SBB_CB_GestionCuentaCuposSOAP12BindingImpl extends BaseServices
    implements SBB_CB_GestionCuentaCupos_PortType
{
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 7436655955420399806L;

	/**  Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SBB_CB_GestionCuentaCuposSOAP12BindingImpl.class, ProyectosCommon.GESTION_CUENTA_CUPOS_12);

	/**
	 * Metodo para inscribir un usuario a una cuenta cupo.
	 *
	 * @param ateiu_entrada Objeto contenedor de los parametros a utilizar en la inscripción del usuario al cuenta cupo
	 * @return Respuesta de la ejecución de la operación
	 * @throws RemoteException Si ocurre un error en la comunicación con el cliente que consume la operación
	 */
	public TipoSalidaInscribirUsuario inscribirUsuario(TipoEntradaInscribirUsuario ateiu_entrada)
	    throws RemoteException
	{
		TipoSalidaInscribirUsuario ltsiu_return;

		ltsiu_return = null;

		try
		{
			ltsiu_return = getGestionCuentaCuposRemote()
					               .inscribirUsuario(
					    ateiu_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("inscribirUsuario", lb2be_e);
		}

		return ltsiu_return;
	}

	/**
	 * Metodo para inactivar un usuario de una cuenta cupo.
	 *
	 * @param atecu_entrada Objeto contenedor de los parametros a utilizar en la inactivación del usuario de la cuenta cupo
	 * @return Respuesta de la ejecución de la operación
	 * @throws RemoteException Si ocurre un error en la comunicación con el cliente que consume la operación
	 */
	public TipoSalidaCancelarUsuario cancelarUsuario(TipoEntradaCancelarUsuario atecu_entrada)
	    throws RemoteException
	{
		TipoSalidaCancelarUsuario ltscu_return;

		ltscu_return = null;

		try
		{
			ltscu_return = getGestionCuentaCuposRemote()
					               .cancelarUsuario(
					    atecu_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cancelarUsuario", lb2be_e);
		}

		return ltscu_return;
	}

	/**
	 * Metodo para consultar un usuario de una cuenta cupo.
	 *
	 * @param atecu_entrada Objeto contenedor de los parametros a utilizar en la consulta del usuario de la cuenta cupo
	 * @return Respuesta de la ejecución de la operación
	 * @throws RemoteException Si ocurre un error en la comunicación con el cliente que consume la operación
	 */
	public TipoSalidaConsultarUsuario consultarUsuario(TipoEntradaConsultarUsuario atecu_entrada)
	    throws RemoteException
	{
		TipoSalidaConsultarUsuario ltscu_return;

		ltscu_return = null;

		try
		{
			ltscu_return = getGestionCuentaCuposRemote()
					               .consultarUsuario(
					    atecu_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarUsuario", lb2be_e);
		}

		return ltscu_return;
	}

	/**
	 * Metodo para consultar los usuarios de una cuenta cupo.
	 *
	 * @param atecu_entrada Objeto contenedor de los parametros a utilizar en la consulta de los usuarios de la cuenta cupo
	 * @return Respuesta de la ejecución de la operación
	 * @throws RemoteException Si ocurre un error en la comunicación con el cliente que consume la operación
	 */
	public TipoSalidaConsultarUsuarios consultarUsuarios(TipoEntradaConsultarUsuarios atecu_entrada)
	    throws RemoteException
	{
		TipoSalidaConsultarUsuarios ltscu_return;

		ltscu_return = null;

		try
		{
			ltscu_return = getGestionCuentaCuposRemote()
					               .consultarUsuarios(
					    atecu_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarUsuarios", lb2be_e);
		}

		return ltscu_return;
	}

	/**
	 * Metodo para consultar los usuarios de una cuenta cupo.
	 *
	 * @param atecs_entrada Objeto contenedor de los parametros a utilizar en la consulta del saldo de la cuenta cupo
	 * @return Respuesta de la ejecución de la operación
	 * @throws RemoteException Si ocurre un error en la comunicación con el cliente que consume la operación
	 */
	public TipoSalidaConsultarSaldo consultarSaldo(TipoEntradaConsultarSaldo atecs_entrada)
	    throws RemoteException
	{
		TipoSalidaConsultarSaldo ltscs_return;

		ltscs_return = null;

		try
		{
			ltscs_return = getGestionCuentaCuposRemote()
					               .consultarSaldo(
					    atecs_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarSaldo", lb2be_e);
		}

		return ltscs_return;
	}

	/**
	 * Metodo para consultar los movimientos de una cuenta cupo.
	 *
	 * @param atecm_entrada Objeto contenedor de los parametros a utilizar en la consulta de los movimientos de la cuenta cupo
	 * @return Respuesta de la ejecución de la operación
	 * @throws RemoteException Si ocurre un error en la comunicación con el cliente que consume la operación
	 */
	public TipoSalidaConsultarMovimientos consultarMovimientos(TipoEntradaConsultarMovimientos atecm_entrada)
	    throws RemoteException
	{
		TipoSalidaConsultarMovimientos ltscm_return;

		ltscm_return = null;

		try
		{
			ltscm_return = getGestionCuentaCuposRemote()
					               .consultarMovimientos(
					    atecm_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarMovimientos", lb2be_e);
		}

		return ltscm_return;
	}

	/**
	 * Metodo para realizar un pago por medio de una cuenta cupo.
	 *
	 * @param atepcc_entrada Objeto contenedor de los parametros a utilizar en el pago por medio de la cuenta cupo
	 * @return Respuesta de la ejecución de la operación
	 * @throws RemoteException Si ocurre un error en la comunicación con el cliente que consume la operación
	 */
	public TipoSalidaPagarCuentaCupo pagarCuentaCupo(TipoEntradaPagarCuentaCupo atepcc_entrada)
	    throws RemoteException
	{
		TipoSalidaPagarCuentaCupo ltspcc_return;

		ltspcc_return = null;

		try
		{
			ltspcc_return = getGestionCuentaCuposRemote()
					                .pagarCuentaCupo(
					    atepcc_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("pagarCuentaCupo", lb2be_e);
		}

		return ltspcc_return;
	}

	/**
	 * Metodo para consultar la información de una cuenta cupo.
	 *
	 * @param atecicc_entrada Objeto contenedor de los parametros a utilizar en la consulta de la cuenta cupo
	 * @return Respuesta de la ejecución de la operación
	 * @throws RemoteException Si ocurre un error en la comunicación con el cliente que consume la operación
	 */
	public TipoSalidaConsultarIDCuentaCupo consultarIDCuentaCupo(TipoEntradaConsultarIDCuentaCupo atecicc_entrada)
	    throws RemoteException
	{
		TipoSalidaConsultarIDCuentaCupo ltscicc_return;

		ltscicc_return = null;

		try
		{
			ltscicc_return = getGestionCuentaCuposRemote()
					                 .consultarIDCuentaCupo(
					    atecicc_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarIDCuentaCupo", lb2be_e);
		}

		return ltscicc_return;
	}

	/**
	 * Metodo para actualizar la información de una entidad externa.
	 *
	 * @param ateae_entrada Objeto contenedor de los parametros a utilizar en la actualización de la entidad externa
	 * @return Respuesta de la ejecución de la operación
	 * @throws RemoteException Si ocurre un error en la comunicación con el cliente que consume la operación
	 */
	public TipoSalidaActualizarEntidad actualizarEntidad(TipoEntradaActualizarEntidad ateae_entrada)
	    throws RemoteException
	{
		TipoSalidaActualizarEntidad ltsae_return;

		ltsae_return = null;

		try
		{
			ltsae_return = getGestionCuentaCuposRemote()
					               .actualizarEntidad(
					    ateae_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("actualizarEntidad", lb2be_e);
		}

		return ltsae_return;
	}

	/**
	 * Metodo para consultar saldos de una nota crédito.
	 *
	 * @param ateae_entrada Objeto contenedor de los parametros a utilizar en la actualización de la entidad externa
	 * @return Respuesta de la ejecución de la operación
	 * @throws RemoteException Si ocurre un error en la comunicación con el cliente que consume la operación
	 */
	public TipoSalidaConsultarSaldosNotaCredito consultarSaldosNotaCredito(
	    TipoEntradaConsultarSaldosNotaCredito atecsnc_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultarSaldosNotaCredito ltscsnc_return;

		ltscsnc_return = null;

		try
		{
			ltscsnc_return = getGestionCuentaCuposRemote()
					                 .consultarSaldosNotaCredito(
					    atecsnc_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarSaldosNotaCredito", lb2be_e);
		}

		return ltscsnc_return;
	}
}
