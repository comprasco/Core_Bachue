package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.png.EntidadesAlerta;

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
 * Clase que contiene todos las propiedades y acciones de BeanEntidadesAlerta.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanEntidadesAlerta")
@SessionScoped
public class BeanEntidadesAlerta extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanEntidadesAlerta.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -630105375392077782L;

	/** Propiedad icea datos auditoria. */
	private Collection<EntidadesAlerta> icea_datosAuditoria;

	/** Propiedad iea entidades alerta. */
	private EntidadesAlerta iea_entidadesAlerta;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param acea_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<EntidadesAlerta> acea_datosAuditoria)
	{
		icea_datosAuditoria = acea_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<EntidadesAlerta> getDatosAuditoria()
	{
		return icea_datosAuditoria;
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
	 * @param aae_ae asigna el valor a la propiedad
	 */
	public void setEntidadesAlerta(EntidadesAlerta aea_ea)
	{
		iea_entidadesAlerta = aea_ea;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public EntidadesAlerta getEntidadesAlerta()
	{
		return iea_entidadesAlerta;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar o modificar un registro
	 * de la tabla SDB_PNG_ENTIDADES_ALERTA
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setEntidadesAlerta((new EntidadesAlerta()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarEntidadesAlerta");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_PNG_ENTIDADES_ALERTA por medio de su indicativo
	 *
	 * @param aea_entidadesAlerta indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(EntidadesAlerta aea_entidadesAlerta)
	    throws B2BException
	{
		if(aea_entidadesAlerta != null)
		{
			EntidadesAlerta lea_entidadesAlerta;

			lea_entidadesAlerta = ipr_parameterRemote.findEntidadesAlertaById(
				    aea_entidadesAlerta, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lea_entidadesAlerta != null)
			{
				Collection<EntidadesAlerta> lcea_cea;

				lcea_cea = new ArrayList<EntidadesAlerta>();

				lcea_cea.add(lea_entidadesAlerta);

				setEntidadesAlerta(lea_entidadesAlerta);
				setDatosAuditoria(lcea_cea);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PNG_ENTIDADES_ALERTA
	 *
	 * @return Collection de EntidadesAlerta resultante de la consulta
	 */
	public Collection<EntidadesAlerta> findAllEntidadesAlerta()
	{
		Collection<EntidadesAlerta> lcea_entidadesAlerta;

		lcea_entidadesAlerta = null;

		try
		{
			lcea_entidadesAlerta = ipr_parameterRemote.findAllEntidadesAlerta();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return lcea_entidadesAlerta;
	}

	/**
	 * Método para salvar la inserción o actualización de un registro en la tabla SDB_PNG_ENTIDADES_ALERTA
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			EntidadesAlerta lea_entidadesAlerta;
			boolean         lb_focus;
			FacesContext    lfc_context;

			lb_focus                = true;
			lfc_context             = FacesContext.getCurrentInstance();
			lea_entidadesAlerta     = getEntidadesAlerta();

			if(lea_entidadesAlerta != null)
			{
				String ls_validador;

				{
					ls_validador     = lea_entidadesAlerta.getTipoDocumentoId();

					lb_focus = validateStyles(
						    ":fEntidadesAlertaDetalle:tipoDocumentoId", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.TIPO_DOCUMENTO_INVALIDO);
				}

				{
					ls_validador     = lea_entidadesAlerta.getNumeroDocumentoId();

					lb_focus = validateStyles(
						    ":fEntidadesAlertaDetalle:numeroDocumentoId", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_DE_DOC);
				}

				{
					ls_validador     = lea_entidadesAlerta.getNombre();

					lb_focus = validateStyles(":fEntidadesAlertaDetalle:nombre", lfc_context, ls_validador, lb_focus);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					ls_validador     = lea_entidadesAlerta.getEmail();

					lb_focus = validateStyles(":fEntidadesAlertaDetalle:email", lfc_context, ls_validador, lb_focus);

					if(
					    !StringUtils.isValidString(ls_validador)
						    || !ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_validador)
					)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
				}

				ipr_parameterRemote.salvarEntidadesAlerta(
				    lea_entidadesAlerta, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clear();

				ls_result = NavegacionCommon.ENTIDADES_ALERTA;

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
			PrimeFaces.current().ajax().update("fEntidadesAlertaDetalle:globalMsg");
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

		ls_return = NavegacionCommon.ENTIDADES_ALERTA;

		clear();

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setEntidadesAlerta(null);
		setInsertar(false);
	}
}
