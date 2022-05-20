package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoActAdmin;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanCirculoActAdmin.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanCirculoActAdmin")
@SessionScoped
public class BeanCirculoActAdmin extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanCirculoActAdmin.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6668462137132466581L;

	/** Propiedad icaa circulo act admin. */
	private CirculoActAdmin icaa_circuloActAdmin;

	/** Propiedad iccaa datos auditoria. */
	private Collection<CirculoActAdmin> iccaa_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param acaa_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<CirculoActAdmin> acaa_datosAuditoria)
	{
		iccaa_datosAuditoria = acaa_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<CirculoActAdmin> getDatosAuditoria()
	{
		return iccaa_datosAuditoria;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param acaa_caa asigna el valor a la propiedad
	 */
	public void setCirculoActAdmin(CirculoActAdmin acaa_caa)
	{
		icaa_circuloActAdmin = acaa_caa;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public CirculoActAdmin getCirculoActAdmin()
	{
		return icaa_circuloActAdmin;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar o modificar un registro
	 * de la tabla SDB_PGN_CIRCULO_ACT_ADMIN
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCirculoActAdmin((new CirculoActAdmin()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCirculoActAdmin");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_PGN_CIRCULO_ACT_ADMIN por medio de su indicativo
	 *
	 * @param acaa_circuloActAdmin indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(CirculoActAdmin acaa_circuloActAdmin)
	    throws B2BException
	{
		if(acaa_circuloActAdmin != null)
		{
			CirculoActAdmin lcaa_circuloActAdmin;

			lcaa_circuloActAdmin = ipr_parameterRemote.findCirculoActAdminById(
				    acaa_circuloActAdmin, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lcaa_circuloActAdmin != null)
			{
				Collection<CirculoActAdmin> lcaa_caa;

				lcaa_caa = new ArrayList<CirculoActAdmin>();

				lcaa_caa.add(lcaa_circuloActAdmin);

				setCirculoActAdmin(lcaa_circuloActAdmin);
				setDatosAuditoria(lcaa_caa);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_CIRCULO_ACT_ADMIN
	 *
	 * @return Collection de CirculoActAdmin resultante de la consulta
	 */
	public Collection<CirculoActAdmin> findAllCirculoActAdmin()
	{
		Collection<CirculoActAdmin> lcaa_circuloActAdmin;

		lcaa_circuloActAdmin = null;

		try
		{
			lcaa_circuloActAdmin = ipr_parameterRemote.findAllCirculoActAdmin();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return lcaa_circuloActAdmin;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<CirculoRegistral> findCirculoRegistral()
	{
		Collection<CirculoRegistral> lcidt_datos;

		lcidt_datos = new ArrayList<CirculoRegistral>();

		try
		{
			lcidt_datos = irr_referenceRemote.findAllCirculosRegistralesActivos(
				    false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Método para salvar la inserción o actualización de un registro en la tabla SDB_PGN_CIRCULO_ACT_ADMIN
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			CirculoActAdmin lcaa_circuloActAdmin;
			boolean         lb_focus;
			FacesContext    lfc_context;

			lb_focus                 = true;
			lfc_context              = FacesContext.getCurrentInstance();
			lcaa_circuloActAdmin     = getCirculoActAdmin();

			if(lcaa_circuloActAdmin != null)
			{
				String ls_validador;

				{
					ls_validador     = lcaa_circuloActAdmin.getIdCirculo();

					lb_focus = validateStyles(
						    ":fCirculoActAdminDetalle:idCirculo", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				{
					ls_validador     = lcaa_circuloActAdmin.getTipoExpediente();

					lb_focus = validateStyles(
						    ":fCirculoActAdminDetalle:tipoExpediente", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				{
					ls_validador     = lcaa_circuloActAdmin.getVigencia();

					lb_focus = validateStyles(":fCirculoActAdminDetalle:vigencia", lfc_context, ls_validador, lb_focus);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				{
					Long ll_validador;

					ll_validador     = lcaa_circuloActAdmin.getConsecutivo();

					lb_focus = validateStyles(
						    ":fCirculoActAdminDetalle:consecutivo", lfc_context, ll_validador, lb_focus
						);

					if(!NumericUtils.isValidLong(ll_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				{
					ls_validador     = lcaa_circuloActAdmin.getActivoString();

					lb_focus = validateStyles(":fCirculoActAdminDetalle:idActivo", lfc_context, "", lb_focus);

					if(StringUtils.isValidString(ls_validador))
						lcaa_circuloActAdmin.setActivo(BooleanUtils.getBooleanValue(ls_validador));
					else
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ipr_parameterRemote.salvarCirculoActAdmin(
				    lcaa_circuloActAdmin, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clear();

				ls_result = NavegacionCommon.CIRCULOS_ACT_ADMIN;

				addMessage(MessagesKeys.PROCESO_COMPLETADO);

				{
					ExternalContext lec_externalContext;

					lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

					if(lec_externalContext != null)
					{
						Flash lf_flash;

						lf_flash = lec_externalContext.getFlash();

						if(lf_flash != null)
							lf_flash.setKeepMessages(true);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fCirculoActAdminDetalle:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String returnPages()
	{
		String ls_return;

		ls_return = NavegacionCommon.CIRCULOS_ACT_ADMIN;

		clear();

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setCirculoActAdmin(null);
		setInsertar(false);
	}
}
