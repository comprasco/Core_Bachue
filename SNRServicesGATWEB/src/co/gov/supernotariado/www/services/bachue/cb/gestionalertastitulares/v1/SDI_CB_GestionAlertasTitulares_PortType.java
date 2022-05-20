/**
 * SDI_CB_GestionAlertasTitulares_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1;

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

import java.rmi.RemoteException;


/**
 * Interface que contiene todos las propiedades SDI_CB_GestionAlertasTitulares_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface SDI_CB_GestionAlertasTitulares_PortType extends java.rmi.Remote
{
	/**
	 * Actualizar contacto alerta.
	 *
	 * @param ateaca_entrada de ateaca entrada
	 * @return el valor de tipo salida actualizar contacto alerta
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaActualizarContactoAlerta actualizarContactoAlerta(
	    TipoEntradaActualizarContactoAlerta ateaca_entrada
	)
	    throws RemoteException;

	/**
	 * Consultar alerta.
	 *
	 * @param ateca_entrada de ateca entrada
	 * @return el valor de tipo salida consultar alerta
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultarAlerta consultarAlerta(TipoEntradaConsultarAlerta ateca_entrada)
	    throws RemoteException;

	/**
	 * Consultar tarifa alertas titulares.
	 *
	 * @param atectat_entrada de atectat entrada
	 * @return el valor de tipo salida consultar tarifa alertas titulares
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultarTarifaAlertasTitulares consultarTarifaAlertasTitulares(
	    TipoEntradaConsultarTarifaAlertasTitulares atectat_entrada
	)
	    throws RemoteException;

	/**
	 * Inactivar alerta.
	 *
	 * @param ateia_entrada de ateia entrada
	 * @return el valor de tipo salida inactivar alerta
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaInactivarAlerta inactivarAlerta(TipoEntradaInactivarAlerta ateia_entrada)
	    throws RemoteException;

	/**
	 * Validar solicitud alerta individual.
	 *
	 * @param atevsai_entrada de atevsai entrada
	 * @return el valor de tipo salida validar solicitud alerta individual
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaValidarSolicitudAlertaIndividual validarSolicitudAlertaIndividual(
	    TipoEntradaValidarSolicitudAlertaIndividual atevsai_entrada
	)
	    throws RemoteException;

	/**
	 * Validar solicitud alerta masiva.
	 *
	 * @param atevsam_entrada de atevsam entrada
	 * @return el valor de tipo salida validar solicitud alerta masiva
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaValidarSolicitudAlertaMasiva validarSolicitudAlertaMasiva(
	    TipoEntradaValidarSolicitudAlertaMasiva atevsam_entrada
	)
	    throws RemoteException;
}
