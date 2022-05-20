package com.bachue.snr.prosnr08.ejb.session.stateless.entrega.producto;

import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoEntradaObtenerProducto;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoEntradaObtenerReciboCaja;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoEntradaObtenerTurnosRefPago;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoEntradaRegistrarEntregaProducto;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoEntradaVerificarProducto;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades EntregaProductoRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 10/10/2019
 */
@Remote
public interface EntregaProductoRemote
{
	
	/**
	 * Obtener producto.
	 *
	 * @param ateop_entrada de TipoEntradaObtenerProducto
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de documentos salida
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DocumentosSalida obtenerProducto(
	    TipoEntradaObtenerProducto ateop_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtener recibo caja.
	 *
	 * @param ateop_entrada de TipoEntradaObtenerReciboCaja
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de documentos salida
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DocumentosSalida obtenerReciboCaja(
	    TipoEntradaObtenerReciboCaja ateop_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtener turnos ref pago.
	 *
	 * @param ateotrp_entrada de TipoEntradaObtenerTurnosRefPago
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Turno> obtenerTurnosRefPago(
	    TipoEntradaObtenerTurnosRefPago ateotrp_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Registrar entrega producto.
	 *
	 * @param aterep_entrada de TipoEntradaRegistrarEntregaProducto
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void registrarEntregaProducto(
	    TipoEntradaRegistrarEntregaProducto aterep_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Verificar producto.
	 *
	 * @param atevp_entrada de TipoEntradaVerificarProducto
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void verificarProducto(
	    TipoEntradaVerificarProducto atevp_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
