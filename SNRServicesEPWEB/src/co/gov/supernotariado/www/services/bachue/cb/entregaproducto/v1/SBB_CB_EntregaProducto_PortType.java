/**
 * SBB_CB_EntregaProducto_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.entregaproducto.v1;

public interface SBB_CB_EntregaProducto_PortType extends java.rmi.Remote
{
	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoSalidaObtenerProducto obtenerProducto(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoEntradaObtenerProducto entrada
	)
	    throws java.rmi.RemoteException;

	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoSalidaObtenerReciboCaja obtenerReciboCaja(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoEntradaObtenerReciboCaja entrada
	)
	    throws java.rmi.RemoteException;

	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoSalidaObtenerTurnosRefPago obtenerTurnosRefPago(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoEntradaObtenerTurnosRefPago entrada
	)
	    throws java.rmi.RemoteException;

	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoSalidaRegistrarEntregaProducto registrarEntregaProducto(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoEntradaRegistrarEntregaProducto entrada
	)
	    throws java.rmi.RemoteException;

	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoSalidaVerificarProducto verificarProducto(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoEntradaVerificarProducto entrada
	)
	    throws java.rmi.RemoteException;
}
