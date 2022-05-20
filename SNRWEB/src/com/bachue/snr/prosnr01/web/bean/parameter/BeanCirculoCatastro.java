package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoCatastro;
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
 * Clase que contiene todos las propiedades y acciones de BeanCirculoCatastro.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanCirculoCatastro")
@SessionScoped
public class BeanCirculoCatastro extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanCirculoCatastro.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5225281584961244362L;

	/** Propiedad icc catastro. */
	private CirculoCatastro icc_circuloCatastro;

	/** Propiedad iccc datos auditoria. */
	private Collection<CirculoCatastro> iccc_datosAuditoria;

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
	 * @param accc_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<CirculoCatastro> accc_datosAuditoria)
	{
		iccc_datosAuditoria = accc_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<CirculoCatastro> getDatosAuditoria()
	{
		return iccc_datosAuditoria;
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
	 * @param acc_cc asigna el valor a la propiedad
	 */
	public void setCirculoCatastro(CirculoCatastro acc_cc)
	{
		icc_circuloCatastro = acc_cc;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public CirculoCatastro getCirculoCatastro()
	{
		return icc_circuloCatastro;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar o modificar un registro
	 * de la tabla SDB_PGN_CIRCULO_CATASTRO
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCirculoCatastro((new CirculoCatastro()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCirculoCatastro");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_PGN_CIRCULO_CATASTRO por medio de su indicativo
	 *
	 * @param ac_catastro indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(CirculoCatastro acc_circuloCatastro)
	    throws B2BException
	{
		if(acc_circuloCatastro != null)
		{
			CirculoCatastro lcc_circuloCatastro;

			lcc_circuloCatastro = ipr_parameterRemote.findCirculoCatastroById(
				    acc_circuloCatastro, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lcc_circuloCatastro != null)
			{
				Collection<CirculoCatastro> lccc_ccc;

				lccc_ccc = new ArrayList<CirculoCatastro>();

				lccc_ccc.add(lcc_circuloCatastro);

				setCirculoCatastro(lcc_circuloCatastro);
				setDatosAuditoria(lccc_ccc);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_CIRCULO_CATASTRO
	 *
	 * @return Collection de Catastro resultante de la consulta
	 */
	public Collection<CirculoCatastro> findAllCirculoCatastro()
	{
		Collection<CirculoCatastro> lccc_circuloCatastro;

		lccc_circuloCatastro = null;

		try
		{
			lccc_circuloCatastro = ipr_parameterRemote.findAllCirculoCatastro();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return lccc_circuloCatastro;
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
	 * Método para salvar la inserción o actualización de un registro en la tabla SDB_PGN_CIRCULO_CATASTRO
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			CirculoCatastro lcc_circuloCatastro;
			boolean         lb_focus;
			FacesContext    lfc_context;

			lb_focus                = true;
			lfc_context             = FacesContext.getCurrentInstance();
			lcc_circuloCatastro     = getCirculoCatastro();

			if(lcc_circuloCatastro != null)
			{
				String ls_validador;

				{
					ls_validador     = lcc_circuloCatastro.getIdCirculo();

					lb_focus = validateStyles(
						    ":fCirculoCatastroDetalle:idCirculo", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
				}

				{
					ls_validador     = lcc_circuloCatastro.getIdCatastro();

					lb_focus = validateStyles(
						    ":fCirculoCatastroDetalle:idCatastro", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CATASTRO);
				}

				{
					ls_validador     = lcc_circuloCatastro.getActivo();

					lb_focus = validateStyles(":fCirculoCatastroDetalle:idActivo", lfc_context, ls_validador, lb_focus);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarCirculoCatastro(
				    lcc_circuloCatastro, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clear();

				ls_result = NavegacionCommon.CIRCULO_CATASTRO;

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
			PrimeFaces.current().ajax().update("fCirculoCatastroDetalle:globalMsg");
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

		ls_return = NavegacionCommon.CIRCULO_CATASTRO;

		clear();

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setCirculoCatastro(null);
		setInsertar(false);
	}
}
