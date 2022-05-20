/**
 * SBB_CB_GestionCuentaCupos_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.gov.supernotariado.www.services.bachue.cb.gestioncuentacupos.v1;

public interface SBB_CB_GestionCuentaCupos_PortType extends java.rmi.Remote {
    public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoSalidaInscribirUsuario inscribirUsuario(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoEntradaInscribirUsuario entrada) throws java.rmi.RemoteException;
    public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.cancelarusuario.v1.TipoSalidaCancelarUsuario cancelarUsuario(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.cancelarusuario.v1.TipoEntradaCancelarUsuario entrada) throws java.rmi.RemoteException;
    public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoSalidaConsultarUsuario consultarUsuario(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoEntradaConsultarUsuario entrada) throws java.rmi.RemoteException;
    public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoSalidaConsultarUsuarios consultarUsuarios(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoEntradaConsultarUsuarios entrada) throws java.rmi.RemoteException;
    public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoSalidaConsultarSaldo consultarSaldo(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoEntradaConsultarSaldo entrada) throws java.rmi.RemoteException;
    public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoSalidaConsultarMovimientos consultarMovimientos(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoEntradaConsultarMovimientos entrada) throws java.rmi.RemoteException;
    public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.pagarcuentacupo.v1.TipoSalidaPagarCuentaCupo pagarCuentaCupo(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.pagarcuentacupo.v1.TipoEntradaPagarCuentaCupo entrada) throws java.rmi.RemoteException;
    public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1.TipoSalidaConsultarIDCuentaCupo consultarIDCuentaCupo(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1.TipoEntradaConsultarIDCuentaCupo entrada) throws java.rmi.RemoteException;
    public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoSalidaActualizarEntidad actualizarEntidad(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoEntradaActualizarEntidad entrada) throws java.rmi.RemoteException;
    public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCredito consultarSaldosNotaCredito(co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoEntradaConsultarSaldosNotaCredito entrada) throws java.rmi.RemoteException;
}
