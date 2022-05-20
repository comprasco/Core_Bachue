/**
 * SBB_CB_HistoricoPropietarios_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.v1;

/**
 * Interface que contiene todos las propiedades SBB_CB_HistoricoPropietarios_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface SBB_CB_HistoricoPropietarios_PortType extends java.rmi.Remote {
    
    /**
     * Consultar historico propiedades.
     *
     * @param entrada de entrada
     * @return el valor de co.gov.supernotariado.www.services.bachue.cb. SB B C B historico propietarios.consultar historico propiedades.v 1 . tipo salida consultar historico propiedades
     * @throws RemoteException cuando se produce algun error en el proceso
     */
    public co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoSalidaConsultarHistoricoPropiedades consultarHistoricoPropiedades(co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoEntradaConsultarHistoricoPropiedades entrada) throws java.rmi.RemoteException;
    
    /**
     * Consultar historico propietarios.
     *
     * @param entrada de entrada
     * @return el valor de co.gov.supernotariado.www.services.bachue.cb. SB B C B historico propietarios.consultar historico propietarios.v 1 . tipo salida consultar historico propietarios
     * @throws RemoteException cuando se produce algun error en el proceso
     */
    public co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.TipoSalidaConsultarHistoricoPropietarios consultarHistoricoPropietarios(co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.TipoEntradaConsultarHistoricoPropietarios entrada) throws java.rmi.RemoteException;
}
