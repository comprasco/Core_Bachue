package com.bachue.snr.prosnr08.ejb.session.stateless.entrega.producto;

import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoEntradaObtenerProducto;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoEntradaObtenerReciboCaja;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoEntradaObtenerTurnosRefPago;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoEntradaRegistrarEntregaProducto;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoEntradaVerificarProducto;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;

import com.bachue.snr.prosnr08.business.entrega.producto.EntregaProductoBusiness;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades EntregaProductoBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "EntregaProducto", mappedName = "entregaProductoMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class EntregaProductoBean implements EntregaProductoRemote
{
	/** Propiedad lepb business. */
	private EntregaProductoBusiness lepb_business;

	/** {@inheritdoc} */
	public DocumentosSalida obtenerProducto(
	    TipoEntradaObtenerProducto ateop_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		DocumentosSalida lds_ds;

		lsw_watch     = Logger.getNewStopWatch();

		lds_ds = getBusiness().obtenerProducto(ateop_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "EntregaProductoBean", "obtenerProducto", as_userId, as_localIp, as_remoteIp, null);

		return lds_ds;
	}

	/** {@inheritdoc} */
	public DocumentosSalida obtenerReciboCaja(
	    TipoEntradaObtenerReciboCaja ateop_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		DocumentosSalida lds_ds;

		lsw_watch     = Logger.getNewStopWatch();

		lds_ds = getBusiness().obtenerReciboCaja(ateop_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "EntregaProductoBean", "obtenerReciboCaja", as_userId, as_localIp, as_remoteIp, null);

		return lds_ds;
	}

	/** {@inheritdoc} */
	public Collection<Turno> obtenerTurnosRefPago(
	    TipoEntradaObtenerTurnosRefPago ateotrp_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		Collection<Turno> lct_turnos;

		lsw_watch     = Logger.getNewStopWatch();

		lct_turnos = getBusiness().obtenerTurnosRefPago(ateotrp_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "EntregaProductoBean", "obtenerTurnosRefPago", as_userId, as_localIp, as_remoteIp, lct_turnos
		);

		return lct_turnos;
	}

	/** {@inheritdoc} */
	public void registrarEntregaProducto(
	    TipoEntradaRegistrarEntregaProducto aterep_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().registrarEntregaProducto(aterep_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "EntregaProductoBean", "registrarEntregaProducto", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void verificarProducto(
	    TipoEntradaVerificarProducto atevp_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().verificarProducto(atevp_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "EntregaProductoBean", "verificarProducto", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private EntregaProductoBusiness getBusiness()
	{
		if(lepb_business == null)
			lepb_business = new EntregaProductoBusiness();

		return lepb_business;
	}
}
