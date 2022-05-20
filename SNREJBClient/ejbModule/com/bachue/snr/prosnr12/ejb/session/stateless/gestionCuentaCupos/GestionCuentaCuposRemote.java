package com.bachue.snr.prosnr12.ejb.session.stateless.gestionCuentaCupos;

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

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades GestionCuentaCuposRemote.
 *
 * @author  Manuel Blanco
 * Fecha de Creacion: 11/02/2020
 */
@Remote
public interface GestionCuentaCuposRemote
{
	/**
	 * Asocia un usuario a una cuenta cupo para que pueda administrarla.
	 *
	 * @param ateiu_request de ateiu request
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Objeto contenedor del resultado de la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaInscribirUsuario inscribirUsuario(
	    TipoEntradaInscribirUsuario ateiu_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Inactiva un usuario de una cuenta cupo.
	 *
	 * @param atecu_request Objeto contenedor de la información de la cuenta cupo y los usuarios
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Objeto contenedor del resultado de la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaCancelarUsuario cancelarUsuario(
	    TipoEntradaCancelarUsuario atecu_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consulta un usuario de una cuenta cupo.
	 *
	 * @param atecu_request Objeto contenedor de la información de la cuenta cupo y los usuarios
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Objeto contenedor del resultado de la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarUsuario consultarUsuario(
	    TipoEntradaConsultarUsuario atecu_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consulta los usuarios de una cuenta cupo.
	 *
	 * @param atecu_request Objeto contenedor de la información de la cuenta cupo y los usuarios
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Objeto contenedor del resultado de la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarUsuarios consultarUsuarios(
	    TipoEntradaConsultarUsuarios atecu_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consulta el saldo de una cuenta cupo.
	 *
	 * @param atecs_request de atecs request
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Objeto contenedor del resultado de la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarSaldo consultarSaldo(
	    TipoEntradaConsultarSaldo atecs_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consulta los movimientos de una cuenta cupo.
	 *
	 * @param atecm_request de atecm request
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Objeto contenedor del resultado de la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarMovimientos consultarMovimientos(
	    TipoEntradaConsultarMovimientos atecm_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Realiza un pago por medio de una cuenta cupo.
	 *
	 * @param atecm_request de atecm request
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Objeto contenedor del resultado de la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaPagarCuentaCupo pagarCuentaCupo(
	    TipoEntradaPagarCuentaCupo atecm_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consulta la información de una cuenta cupo.
	 *
	 * @param atecicc_request de atecicc request
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Objeto contenedor del resultado de la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarIDCuentaCupo consultarIDCuentaCupo(
	    TipoEntradaConsultarIDCuentaCupo atecicc_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Actualiza la información de una entidad externa.
	 *
	 * @param ateae_request de ateae request
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return Objeto contenedor del resultado de la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaActualizarEntidad actualizarEntidad(
	    TipoEntradaActualizarEntidad ateae_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar saldos nota credito.
	 *
	 * @param atecsnc_entrada de atecsnc entrada
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar saldos nota credito
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarSaldosNotaCredito consultarSaldosNotaCredito(
	    TipoEntradaConsultarSaldosNotaCredito atecsnc_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
