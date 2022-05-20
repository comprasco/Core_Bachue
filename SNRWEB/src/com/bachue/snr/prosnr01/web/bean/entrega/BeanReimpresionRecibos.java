package com.bachue.snr.prosnr01.web.bean.entrega;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.entrega.EntregaRemote;

import com.bachue.snr.prosnr01.model.reimpresion.ReimpresionRecibos;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
* Clase que contiene todos las propiedades y funcionalidad de
* BeanReimpresionRecibos.
*
* @author Sebastian Sanchez
*/
@SessionScoped
@ManagedBean(name = "beanReimpresionRecibos")
public class BeanReimpresionRecibos extends BeanDetalleEntrega implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5524768194023741191L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanReimpresionRecibos.class);

	/** Propiedad icr recibos reimpresion. */
	private Collection<ReimpresionRecibos> icr_recibosReimpresion;

	/** Propiedad ier entrega remote. */
	@EJB
	private EntregaRemote ier_entregaRemote;

	/** Propiedad ir reimpresion documento recibo. */
	private ReimpresionRecibos ir_reimpresionDocumentoRecibo;

	/** Propiedad is id turno reimpresion. */
	private String is_idTurnoReimpresion;

	/** Propiedad is nir reimpresion. */
	private String is_nirReimpresion;

	/** Propiedad ib mostrar recibos. */
	private boolean ib_mostrarRecibos;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	* Modifica el valor de id turno reimpresion.
	*
	* @param as_s
	*                 asigna el valor a la propiedad id turno reimpresion
	*/
	public void setIdTurnoReimpresion(String as_s)
	{
		is_idTurnoReimpresion = as_s;
	}

	/**
	* Retorna el valor de id turno reimpresion.
	*
	* @return el valor de id turno reimpresion
	*/
	public String getIdTurnoReimpresion()
	{
		return is_idTurnoReimpresion;
	}

	/**
	* Modifica el valor de mostrar recibos.
	*
	* @param ab_b
	*                 asigna el valor a la propiedad mostrar recibos
	*/
	public void setMostrarRecibos(boolean ab_b)
	{
		ib_mostrarRecibos = ab_b;
	}

	/**
	* Retorna el valor de mostrar recibos.
	*
	* @return el valor de mostrar recibos
	*/
	public boolean isMostrarRecibos()
	{
		return ib_mostrarRecibos;
	}

	/**
	* Modifica el valor de nir reimpresion.
	*
	* @param as_s
	*                 asigna el valor a la propiedad nir reimpresion
	*/
	public void setNirReimpresion(String as_s)
	{
		is_nirReimpresion = as_s;
	}

	/**
	* Retorna el valor de nir reimpresion.
	*
	* @return el valor de nir reimpresion
	*/
	public String getNirReimpresion()
	{
		return is_nirReimpresion;
	}

	/**
	* Modifica el valor de recibos reimpresion.
	*
	* @param acr_cr
	*                   asigna el valor a la propiedad recibos reimpresion
	*/
	public void setRecibosReimpresion(Collection<ReimpresionRecibos> acr_cr)
	{
		icr_recibosReimpresion = acr_cr;
	}

	/**
	* Retorna el valor de recibos reimpresion.
	*
	* @return el valor de recibos reimpresion
	*/
	public Collection<ReimpresionRecibos> getRecibosReimpresion()
	{
		return icr_recibosReimpresion;
	}

	/**
	* Modifica el valor de reimpresion documento recibo.
	*
	* @param ar_r
	*                   asigna el valor a la propiedad reimpresion documento recibo
	*/
	public void setReimpresionDocumentoRecibo(ReimpresionRecibos ar_r)
	{
		ir_reimpresionDocumentoRecibo = ar_r;
	}

	/**
	* Retorna el valor de reimpresion documento recibo.
	*
	* @return el valor de reimpresion documento recibo
	*/
	public ReimpresionRecibos getReimpresionDocumentoRecibo()
	{
		return ir_reimpresionDocumentoRecibo;
	}

	/**
	* Metodo para cargar los recibos asociados a un nir o a un idTurno.
	*
	* @return el valor collection de reimpresion
	* @throws B2BException
	*/
	public Collection<ReimpresionRecibos> cargarRecibosCajaLiquidacion()
	{
		Collection<ReimpresionRecibos> lcr_documentosSalida;

		lcr_documentosSalida = null;

		try
		{
			lcr_documentosSalida = ier_entregaRemote.cargarRecibosCajaLiquidacion(
				    getIdTurnoReimpresion(), getNirReimpresion(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lcr_documentosSalida))
			{
				setRecibosReimpresion(lcr_documentosSalida);
				setMostrarRecibos(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_documentosSalida;
	}

	/**
	* Método para limpiar la pantalla.
	*/
	public void clean()
	{
		setNirReimpresion(null);
		setIdTurnoReimpresion(null);
		setRecibosReimpresion(null);
		setMostrarRecibos(false);
		setReimpresionDocumentoRecibo(null);
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String returnPages()
	{
		String ls_return;

		ls_return = NavegacionCommon.REIMPRESION_RECIBOS;

		try
		{
			clear(true);
			clean();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			ls_return = null;
		}

		return ls_return;
	}

	/**
	 * Metodo para imprimir los documentos.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void imprimirDocumento()
	    throws B2BException
	{
		imprimirDocumento(getReimpresionDocumentoRecibo(), true);
	}

	/**
	 * Metodo para imprimir los documentos.
	 *
	 * @param ar_documento  Objeto de tipo Reimpresion
	 * @param ab_insertar correspondiente al valor de ab insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void imprimirDocumento(ReimpresionRecibos ar_documento, boolean ab_insertar)
	    throws B2BException
	{
		PrimeFaces lp_primeFaces;

		lp_primeFaces = PrimeFaces.current();

		try
		{
			if(ar_documento != null)
			{
				if(!ar_documento.isEnviadoOwcc())
				{
					Object[] loa_args;

					loa_args        = new String[1];
					loa_args[0]     = ar_documento.getNumeroReferencia();

					throw new B2BException(ErrorKeys.DOCUMENTO_NO_ENVIADO, loa_args);
				}
				else
				{
					String ls_idTurno;

					ls_idTurno = ar_documento.getIdTurno();

					if(StringUtils.isValidString(ls_idTurno) && (!ab_insertar))
						PrimeFaces.current()
							          .executeScript(
							    "abrirURLBioClientImpresion('" + generarURLImpresion(ar_documento, ls_idTurno) + "')"
							);
					else if(!StringUtils.isValidString(ls_idTurno) && (!ab_insertar))
						PrimeFaces.current()
							          .executeScript(
							    "abrirURLBioClientImpresion('"
							    + generarURLImpresion(ar_documento, ar_documento.getNir()) + "')"
							);

					ar_documento.setEstadoImpresion(EstadoCommon.IMPRIMIENDO);
					ar_documento.setReciboBoton(true);

					if(ab_insertar)
					{
						insertarReimpresion(ar_documento);
						ar_documento.setEstadoImpresion(EstadoCommon.IMPRESO);
						ar_documento.setReciboBoton(false);
					}

					setReimpresionDocumentoRecibo(ar_documento);
					lp_primeFaces.ajax().update("fReimpresionrecibos:consultaRecibosCajaLiquidacion");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			lp_primeFaces.ajax().update("fReimpresionrecibos:globalMsg");
		}
	}

	/**
	 * Metodo para imprimir los documentos.
	 *
	 * @param ar_documento  Objeto de tipo Reimpresion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void insertarReimpresion(ReimpresionRecibos ar_documento)
	    throws B2BException
	{
		try
		{
			ier_entregaRemote.salvarReimpresionRecibos(
			    ar_documento, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("insertarReimpresion", lb2be_e);
			throw lb2be_e;
		}
	}
}
