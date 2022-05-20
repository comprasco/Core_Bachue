/**
 * SBB_CB_EntregaProductoSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.entregaproducto.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoEntradaObtenerProducto;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoSalidaObtenerProducto;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoEntradaObtenerReciboCaja;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoSalidaObtenerReciboCaja;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoEntradaObtenerTurnosRefPago;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoSalidaObtenerTurnosRefPago;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoTurno;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoEntradaRegistrarEntregaProducto;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoSalidaRegistrarEntregaProducto;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoEntradaVerificarProducto;
import co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoSalidaVerificarProducto;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import com.bachue.snr.prosnr08.common.constants.ErrorKeys;

import java.math.BigInteger;

import java.rmi.RemoteException;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades SBB_CB_EntregaProductoSOAP12BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/09/2019
 */
public class SBB_CB_EntregaProductoSOAP12BindingImpl extends BaseServices implements SBB_CB_EntregaProducto_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2503927695306800031L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SBB_CB_EntregaProductoSOAP12BindingImpl.class, ProyectosCommon.ENTREGAR_PRODUCTO_08);

	/**
	 * la operación “ObtenerProducto” los canales pueden presentar el producto al usuario final,
	 * los canales envían la petición a través del BUS de servicios a Bachue en intervalos de 5 segundos esperando respuesta.
	 *
	 * @param ateop_entrada de ateop entrada
	 * @return el valor de tipo salida obtener producto
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaObtenerProducto obtenerProducto(TipoEntradaObtenerProducto ateop_entrada)
	    throws RemoteException
	{
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;
		String     ls_did;
		String     ls_docName;

		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = "";
		ls_did                    = null;
		ls_docName                = null;

		try
		{
			DocumentosSalida lds_return;

			lds_return = getEntregaProductoRemote()
					             .obtenerProducto(
					    ateop_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

			if(lds_return != null)
			{
				ls_did         = lds_return.getIdEcm();
				ls_docName     = lds_return.getIdNombreDocumento();
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerProducto", lb2be_e);

			long   ll_key;
			String ls_key;
			String ls_subkey;
			String ls_msg;

			{
				ls_msg        = lb2be_e.getMessageKey();
				ls_subkey     = ls_msg.substring(0, 3);
				ls_key        = ls_msg.substring(5);
				ll_key        = NumericUtils.getLong(ls_subkey, ConstanteCommon.DEFAULT_ID);
			}

			lbi_codigoMensaje         = BigInteger.valueOf(
				    (ll_key == ConstanteCommon.DEFAULT_ID) ? CodigoMensajeCommon.CODIGO_409 : ll_key
				);
			ls_descripcionMensaje     = ((ll_key == ConstanteCommon.DEFAULT_ID) ? ls_msg : ls_key.trim());
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerProducto", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}

		{
			TipoSalidaObtenerProducto ltsop_salida;

			ltsop_salida = new TipoSalidaObtenerProducto(
				    StringUtils.getStringNotEmpty(ls_docName), StringUtils.getStringNotEmpty(ls_did), lbi_codigoMensaje,
				    ls_descripcionMensaje
				);

			return ltsop_salida;
		}
	}

	/**
	 * Operación que permite obtener el recibo de caja y ser presentado al usuario final.
	 *
	 * @param ateorc_entrada de ateorc entrada
	 * @return el valor de tipo salida obtener recibo caja
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaObtenerReciboCaja obtenerReciboCaja(TipoEntradaObtenerReciboCaja ateorc_entrada)
	    throws RemoteException
	{
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;
		String     ls_did;
		String     ls_docName;

		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = "";
		ls_did                    = null;
		ls_docName                = null;

		try
		{
			DocumentosSalida lds_return;

			lds_return = getEntregaProductoRemote()
					             .obtenerReciboCaja(
					    ateorc_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

			if(lds_return != null)
			{
				ls_did         = lds_return.getIdEcm();
				ls_docName     = lds_return.getIdNombreDocumento();
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerReciboCaja", lb2be_e);

			long   ll_key;
			String ls_key;
			String ls_subkey;
			String ls_msg;

			{
				ls_msg        = lb2be_e.getMessageKey();
				ls_subkey     = ls_msg.substring(0, 3);
				ls_key        = ls_msg.substring(5);
				ll_key        = NumericUtils.getLong(ls_subkey, ConstanteCommon.DEFAULT_ID);
			}

			lbi_codigoMensaje         = BigInteger.valueOf(
				    (ll_key == ConstanteCommon.DEFAULT_ID) ? CodigoMensajeCommon.CODIGO_409 : ll_key
				);
			ls_descripcionMensaje     = ((ll_key == ConstanteCommon.DEFAULT_ID) ? ls_msg : ls_key.trim());
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerReciboCaja", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}

		{
			TipoSalidaObtenerReciboCaja ltsorc_salida;

			ltsorc_salida = new TipoSalidaObtenerReciboCaja(
				    StringUtils.getStringNotNull(ls_docName), StringUtils.getStringNotNull(ls_did), lbi_codigoMensaje,
				    ls_descripcionMensaje
				);

			return ltsorc_salida;
		}
	}

	/**
	 * La operación “ObtenerTurnoRefPago” se consume solo para las peticiones realizadas por referencia de pago y,
	 * cuando estas tengan más de un turno asociado.
	 *
	 * @param ateotrp_entrada de ateotrp entrada
	 * @return el valor de tipo salida obtener turnos ref pago
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaObtenerTurnosRefPago obtenerTurnosRefPago(TipoEntradaObtenerTurnosRefPago ateotrp_entrada)
	    throws RemoteException
	{
		BigInteger  lbi_codigoMensaje;
		String      ls_descripcionMensaje;
		TipoTurno[] ltta_response;

		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;
		ltta_response             = null;

		try
		{
			Collection<Turno> lct_turnos;

			lct_turnos = getEntregaProductoRemote()
					             .obtenerTurnosRefPago(
					    ateotrp_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

			if((lct_turnos != null) && (lct_turnos.size() > NumericUtils.DEFAULT_INT_VALUE))
			{
				int li_contador;

				ltta_response     = new TipoTurno[lct_turnos.size()];
				li_contador       = 0;

				for(Turno lt_item : lct_turnos)
				{
					if(lt_item != null)
						ltta_response[li_contador++] = new TipoTurno(lt_item.getIdTurno());
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRARON_TURNOS_CON_DOCUMENTOS_ACTIVOS);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerTurnosRefPago", lb2be_e);

			long   ll_key;
			String ls_key;
			String ls_subkey;
			String ls_msg;

			{
				ls_msg        = getErrorKeyEP(lb2be_e);
				ls_subkey     = ls_msg.substring(0, 3);
				ls_key        = ls_msg.substring(5);
				ll_key        = NumericUtils.getLong(ls_subkey, ConstanteCommon.DEFAULT_ID);
			}

			lbi_codigoMensaje         = BigInteger.valueOf(
				    (ll_key == ConstanteCommon.DEFAULT_ID) ? CodigoMensajeCommon.CODIGO_409 : ll_key
				);
			ls_descripcionMensaje     = ((ll_key == ConstanteCommon.DEFAULT_ID) ? ls_msg : ls_key.trim());
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerTurnosRefPago", le_e);

			lbi_codigoMensaje     = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);

			ls_descripcionMensaje = le_e.getMessage();
		}

		if(!CollectionUtils.isValidCollection(ltta_response))
		{
			ltta_response        = new TipoTurno[1];
			ltta_response[0]     = new TipoTurno("");
		}

		return new TipoSalidaObtenerTurnosRefPago(ltta_response, lbi_codigoMensaje, ls_descripcionMensaje);
	}

	/**
	 * La operación “registrarEntregaProducto” informa a Bachue sobre la entrega al usuario del producto correspondiente,
	 * esta operación se expone desde Sede Electrónica y Kioskos  hacia Bachue para poder cerrar el NIR.
	 *
	 * @param aterep_entrada de aterep entrada
	 * @return el valor de tipo salida registrar entrega producto
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaRegistrarEntregaProducto registrarEntregaProducto(
	    TipoEntradaRegistrarEntregaProducto aterep_entrada
	)
	    throws RemoteException
	{
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;

		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			getEntregaProductoRemote()
				    .registrarEntregaProducto(aterep_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("registrarEntregaProducto", lb2be_e);

			long   ll_key;
			String ls_key;
			String ls_subkey;
			String ls_msg;

			{
				ls_msg        = lb2be_e.getMessageKey();
				ls_subkey     = ls_msg.substring(0, 3);
				ls_key        = ls_msg.substring(5);
				ll_key        = NumericUtils.getLong(ls_subkey, ConstanteCommon.DEFAULT_ID);
			}

			lbi_codigoMensaje         = BigInteger.valueOf(
				    (ll_key == ConstanteCommon.DEFAULT_ID) ? CodigoMensajeCommon.CODIGO_409 : ll_key
				);
			ls_descripcionMensaje     = ((ll_key == ConstanteCommon.DEFAULT_ID) ? ls_msg : ls_key.trim());
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("registrarEntregaProducto", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}

		return new TipoSalidaRegistrarEntregaProducto(lbi_codigoMensaje, ls_descripcionMensaje);
	}

	/**
	 * A través de la operación “VerificarProducto” los interesados pueden hacer consultas a través del Bus de servicios hacia Bachue
	 * y conocer si existe un documento asociado a su consulta.
	 *
	 * Si el documento existe y está asociado al criterio de entrada, la respuesta se mostrara como satisfactoria.
	 * Si para la entrada en Bachue no se encuentra información o no existen documentos asociados la respuesta seria fallida.
	 *
	 * @param atevp_entrada de atevp entrada
	 * @return el valor de tipo salida verificar producto
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaVerificarProducto verificarProducto(TipoEntradaVerificarProducto atevp_entrada)
	    throws RemoteException
	{
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;

		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			getEntregaProductoRemote()
				    .verificarProducto(atevp_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("verificarProducto", lb2be_e);

			long   ll_key;
			String ls_key;
			String ls_subkey;
			String ls_msg;

			{
				ls_msg        = lb2be_e.getMessageKey();
				ls_subkey     = ls_msg.substring(0, 3);
				ls_key        = ls_msg.substring(5);
				ll_key        = NumericUtils.getLong(ls_subkey, ConstanteCommon.DEFAULT_ID);
			}

			lbi_codigoMensaje         = BigInteger.valueOf(
				    (ll_key == ConstanteCommon.DEFAULT_ID) ? CodigoMensajeCommon.CODIGO_409 : ll_key
				);
			ls_descripcionMensaje     = ((ll_key == ConstanteCommon.DEFAULT_ID) ? ls_msg : ls_key.trim());
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("verificarProducto", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}

		return new TipoSalidaVerificarProducto(lbi_codigoMensaje, ls_descripcionMensaje);
	}
}
