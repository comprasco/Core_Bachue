package com.bachue.snr.prosnr01.web.bean.entrega;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.entrega.EntregaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.notificaciones.NotificacionesRemote;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanEntrega.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanEntrega")
public class BeanEntrega extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5344798427558801509L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanEntrega.class);

	/** Propiedad ier entrega remote. */
	@EJB
	protected EntregaRemote ier_entregaRemote;

	/** Propiedad inr notificaciones remote. */
	@EJB
	protected NotificacionesRemote inr_notificacionesRemote;

	/** Propiedad ictc datos tramite cantidad. */
	private Collection<TramiteCantidad> ictc_datosTramiteCantidad;

	/** Propiedad is id turno consulta. */
	private String is_idTurnoConsulta;

	/** Propiedad is nir consulta. */
	private String is_nirConsulta;

	/** Propiedad ii total bandeja. */
	private int ii_totalBandeja;

	/** Propiedad il id etapa. */
	private long il_idEtapa;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de datos tramite cantidad.
	 *
	 * @param ac_t asigna el valor a la propiedad datos tramite cantidad
	 */
	public void setDatosTramiteCantidad(Collection<TramiteCantidad> ac_t)
	{
		ictc_datosTramiteCantidad = ac_t;
	}

	/**
	 * Retorna el valor de datos tramite cantidad.
	 *
	 * @return el valor de datos tramite cantidad
	 */
	public Collection<TramiteCantidad> getDatosTramiteCantidad()
	{
		return ictc_datosTramiteCantidad;
	}

	/**
	 * Modifica el valor de id etapa.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa
	 */
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna el valor de id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public long getIdEtapa()
	{
		String tmp = FacesUtils.getStringFacesParameter("idEtapa");

		if(StringUtils.isValidString(tmp))
			il_idEtapa = Long.parseLong(tmp);

		return il_idEtapa;
	}

	/**
	 * Modifica el valor de id turno consulta.
	 *
	 * @param as_s asigna el valor a la propiedad id turno consulta
	 */
	public void setIdTurnoConsulta(String as_s)
	{
		is_idTurnoConsulta = as_s;
	}

	/**
	 * Retorna el valor de id turno consulta.
	 *
	 * @return el valor de id turno consulta
	 */
	public String getIdTurnoConsulta()
	{
		return is_idTurnoConsulta;
	}

	/**
	 * Modifica el valor de nir consulta.
	 *
	 * @param as_s asigna el valor a la propiedad nir consulta
	 */
	public void setNirConsulta(String as_s)
	{
		is_nirConsulta = as_s;
	}

	/**
	 * Retorna el valor de nir consulta.
	 *
	 * @return el valor de nir consulta
	 */
	public String getNirConsulta()
	{
		return is_nirConsulta;
	}

	/**
	 * Modifica el valor de total bandeja.
	 *
	 * @param ai_i asigna el valor a la propiedad total bandeja
	 */
	public void setTotalBandeja(int ai_i)
	{
		ii_totalBandeja = ai_i;
	}

	/**
	 * Retorna el valor de total bandeja.
	 *
	 * @return el valor de total bandeja
	 */
	public int getTotalBandeja()
	{
		return ii_totalBandeja;
	}

	/**
	 * Método para limpiar la pantalla.
	 */
	public void clean()
	{
		setNirConsulta(null);
		setIdTurnoConsulta(null);
		setTotalBandeja(0);
		setIdEtapa(0);
		setDatosTramiteCantidad(null);
	}

	/**
	 * Generar datos tramite cantidad.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDatosTramiteCantidad()
	    throws B2BException
	{
		try
		{
			String  ls_nir;
			String  ls_turno;
			boolean lb_validNir;
			boolean lb_validTurno;

			ls_nir            = getNirConsulta();
			ls_turno          = getIdTurnoConsulta();
			lb_validNir       = StringUtils.isValidString(ls_nir);
			lb_validTurno     = StringUtils.isValidString(ls_turno);

			if(!lb_validNir && !lb_validTurno)
			{
				setDatosTramiteCantidad(null);
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_NIR_O_TURNO);
			}

			if(lb_validNir)
				ls_nir = ls_nir.trim().toUpperCase();

			if(lb_validTurno)
				ls_turno = ls_turno.trim();

			{
				Collection<TramiteCantidad> lctc_tramitesCantidad;

				lctc_tramitesCantidad = ier_entregaRemote.findInboxByTurnoNir(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress(), ls_turno, ls_nir
					);

				setDatosTramiteCantidad(lctc_tramitesCantidad);
				setIdTurnoConsulta(ls_turno);
				setNirConsulta(ls_nir);

				if(CollectionUtils.isValidCollection(lctc_tramitesCantidad))
				{
					setTotalBandeja(lctc_tramitesCantidad.size());
					addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Return pages.
	 *
	 * @return el valor de string
	 */
	public String returnPages()
	{
		return returnPages(false);
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @param ab_impresionCorrespondencia de ab impresion correspondencia
	 * @return devuelve el valor de String
	 */
	protected String returnPages(boolean ab_impresionCorrespondencia)
	{
		String ls_return;
		ls_return = null;

		try
		{
			long ll_idEtapa;

			ll_idEtapa = getIdEtapa();

			if(ll_idEtapa > 0)
			{
				String ls_navImpresion;
				ls_navImpresion     = (ll_idEtapa == EtapaCommon.IMPRESION_DOCUMENTOS_CORRESPONDENCIA)
					? NavegacionCommon.DETALLE_IMPRESION_CORRESPONDENCIA
					: NavegacionCommon.DETALLE_IMPRESION_CORRESPONDENCIA_SALVAR;

				ls_return = ab_impresionCorrespondencia ? ls_navImpresion : NavegacionCommon.DETALLE_ENTREGA;

				if(!ab_impresionCorrespondencia)
				{
					BeanDetalleEntrega lbde_bean;

					lbde_bean = (BeanDetalleEntrega)FacesUtils.obtenerInstanciaBean(
						    BeanDetalleEntrega.class, BeanNameCommon.BEAN_DETALLE_ENTREGA
						);

					if(lbde_bean != null)
					{
						lbde_bean.iniciar();
						lbde_bean.setIdEtapa(ll_idEtapa);
						lbde_bean.setBandejaentrada(true);
						lbde_bean.setDatosTramiteTurno(null);
						lbde_bean.setIdTurno(getIdTurnoConsulta());
						lbde_bean.setNir(getNirConsulta());
						lbde_bean.setEntrega(null);
						lbde_bean.obtenerTurnos();
					}
				}
				else
				{
					BeanDetalleImpresionDocumentosCorrespondencia lbde_bean;

					lbde_bean = (BeanDetalleImpresionDocumentosCorrespondencia)FacesUtils.obtenerInstanciaBean(
						    BeanDetalleImpresionDocumentosCorrespondencia.class,
						    BeanNameCommon.BEAN_DETALLE_IMPRESION_DOCUMENTOS_CORRESPONDENCIA
						);

					if(lbde_bean != null)
					{
						lbde_bean.iniciar();
						lbde_bean.setIdEtapa(ll_idEtapa);
						lbde_bean.setBandejaentrada(true);
						lbde_bean.setDatosTramiteTurno(null);
						lbde_bean.setIdTurno(getIdTurnoConsulta());
						lbde_bean.setNir(getNirConsulta());
						lbde_bean.setEntrega(null);
						lbde_bean.obtenerTurnos();
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			ls_return = null;
		}

		return ls_return;
	}
}
