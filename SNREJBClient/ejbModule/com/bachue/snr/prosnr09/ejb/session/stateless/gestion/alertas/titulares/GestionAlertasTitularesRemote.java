package com.bachue.snr.prosnr09.ejb.session.stateless.gestion.alertas.titulares;

import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.actualizarcontactoalerta.v1.TipoEntradaActualizarContactoAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.actualizarcontactoalerta.v1.TipoSalidaActualizarContactoAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoSalidaConsultarAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoEntradaConsultarTarifaAlertasTitulares;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoSalidaConsultarTarifaAlertasTitulares;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoEntradaInactivarAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoSalidaInactivarAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoEntradaValidarSolicitudAlertaIndividual;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoSalidaValidarSolicitudAlertaIndividual;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoEntradaValidarSolicitudAlertaMasiva;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoSalidaValidarSolicitudAlertaMasiva;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades GestionAlertasTitularesRemote.
 *
 * @author  Manuel Blanco
 * Fecha de Creacion: 25/10/2019
 */
@Remote
public interface GestionAlertasTitularesRemote
{
	
	/**
	 * Actualizar contacto alerta.
	 *
	 * @param ateaca_request de TipoEntradaActualizarContactoAlerta
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida actualizar contacto alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaActualizarContactoAlerta actualizarContactoAlerta(
	    TipoEntradaActualizarContactoAlerta ateaca_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar alerta.
	 *
	 * @param atevsai_request de TipoEntradaConsultarAlerta
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarAlerta consultarAlerta(
	    TipoEntradaConsultarAlerta atevsai_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar tarifa alertas titulares.
	 *
	 * @param atectat_request de TipoEntradaConsultarTarifaAlertasTitulares
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar tarifa alertas titulares
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarTarifaAlertasTitulares consultarTarifaAlertasTitulares(
	    TipoEntradaConsultarTarifaAlertasTitulares atectat_request, String as_userId, String as_localIp,
	    String                                     as_remoteIp
	)
	    throws B2BException;

	/**
	 * Inactivar alerta.
	 *
	 * @param ateia_request de TipoEntradaInactivarAlerta
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida inactivar alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaInactivarAlerta inactivarAlerta(
	    TipoEntradaInactivarAlerta ateia_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Validar solicitud alerta individual.
	 *
	 * @param atevsai_request de TipoEntradaValidarSolicitudAlertaIndividual
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida validar solicitud alerta individual
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaValidarSolicitudAlertaIndividual validarSolicitudAlertaIndividual(
	    TipoEntradaValidarSolicitudAlertaIndividual atevsai_request, String as_userId, String as_localIp,
	    String                                      as_remoteIp
	)
	    throws B2BException;

	/**
	 * Validar solicitud alerta masiva.
	 *
	 * @param atevsam_request de TipoEntradaValidarSolicitudAlertaMasiva
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida validar solicitud alerta masiva
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaValidarSolicitudAlertaMasiva validarSolicitudAlertaMasiva(
	    TipoEntradaValidarSolicitudAlertaMasiva atevsam_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
