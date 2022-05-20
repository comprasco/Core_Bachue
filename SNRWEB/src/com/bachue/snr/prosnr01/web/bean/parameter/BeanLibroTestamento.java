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

import com.bachue.snr.prosnr01.model.sdb.pgn.LibroTestamento;

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
 * Clase que contiene todos las propiedades y acciones de BeanLibroTestamento.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanLibroTestamento")
@SessionScoped
public class BeanLibroTestamento extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanLibroTestamento.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1761288721082167810L;

	/** Propiedad iclt datos auditoria. */
	private Collection<LibroTestamento> iclt_datosAuditoria;

	/** Propiedad ilt libro testamento. */
	private LibroTestamento ilt_libroTestamento;

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
	 * @param aclt_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<LibroTestamento> aclt_datosAuditoria)
	{
		iclt_datosAuditoria = aclt_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<LibroTestamento> getDatosAuditoria()
	{
		return iclt_datosAuditoria;
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
	 * @param alt_lt asigna el valor a la propiedad
	 */
	public void setLibroTestamento(LibroTestamento alt_lt)
	{
		ilt_libroTestamento = alt_lt;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public LibroTestamento getLibroTestamento()
	{
		return ilt_libroTestamento;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar o modificar un registro
	 * de la tabla SDB_PGN_LIBRO_TESTAMENTO
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setLibroTestamento((new LibroTestamento()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarLibroTestamento");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_PGN_LIBRO_TESTAMENTO por medio de su indicativo
	 *
	 * @param alt_libroTestamento indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(LibroTestamento alt_libroTestamento)
	    throws B2BException
	{
		if(alt_libroTestamento != null)
		{
			LibroTestamento llt_libroTestamento;

			llt_libroTestamento = ipr_parameterRemote.findLibroTestamentoById(
				    alt_libroTestamento, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(llt_libroTestamento != null)
			{
				Collection<LibroTestamento> lclt_clt;

				lclt_clt = new ArrayList<LibroTestamento>();

				lclt_clt.add(llt_libroTestamento);

				setLibroTestamento(llt_libroTestamento);
				setDatosAuditoria(lclt_clt);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_LIBRO_TESTAMENTO
	 *
	 * @return Collection de LibroTestamento resultante de la consulta
	 */
	public Collection<LibroTestamento> findAllLibroTestamento()
	{
		Collection<LibroTestamento> llt_libroTestamento;

		llt_libroTestamento = null;

		try
		{
			llt_libroTestamento = ipr_parameterRemote.findAllLibroTestamento();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return llt_libroTestamento;
	}

	/**
	 * Método para salvar la inserción o actualización de un registro en la tabla SDB_PGN_LIBRO_TESTAMENTO
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			LibroTestamento llt_libroTestamento;
			boolean         lb_focus;
			FacesContext    lfc_context;

			lb_focus                = true;
			lfc_context             = FacesContext.getCurrentInstance();
			llt_libroTestamento     = getLibroTestamento();

			if(llt_libroTestamento != null)
			{
				Long   ll_validador;
				String ls_validador;

				{
					ll_validador     = llt_libroTestamento.getLibroAntSistema();

					lb_focus = validateStyles(
						    ":fLibroTestamentoDetalle:idLibroAntSistema", lfc_context, ll_validador, lb_focus
						);

					if(!NumericUtils.isValidLong(ll_validador))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ID_LIBRO_ANTIGUO_SISTEMA);
				}

				{
					ls_validador     = llt_libroTestamento.getIdCirculo();

					lb_focus = validateStyles(
						    ":fLibroTestamentoDetalle:idCirculo", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
				}

				{
					ll_validador     = llt_libroTestamento.getTomo();

					lb_focus = validateStyles(":fLibroTestamentoDetalle:tomo", lfc_context, ll_validador, lb_focus);

					if(!NumericUtils.isValidLong(ll_validador))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TOMO);
				}

				{
					ll_validador     = llt_libroTestamento.getAno();

					lb_focus = validateStyles(":fLibroTestamentoDetalle:ano", lfc_context, ll_validador, lb_focus);

					if(!NumericUtils.isValidLong(ll_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ANIO);
				}

				{
					ll_validador     = llt_libroTestamento.getLibro();

					lb_focus = validateStyles(":fLibroTestamentoDetalle:libro", lfc_context, ll_validador, lb_focus);

					if(!NumericUtils.isValidLong(ll_validador))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_LIBRO);
				}

				{
					ll_validador     = llt_libroTestamento.getFolio();

					lb_focus = validateStyles(":fLibroTestamentoDetalle:folio", lfc_context, ll_validador, lb_focus);

					if(!NumericUtils.isValidLong(ll_validador))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_FOLIO);
				}

				ipr_parameterRemote.salvarLibroTestamento(
				    llt_libroTestamento, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clear();

				ls_result = NavegacionCommon.LIBRO_TESTAMENTO;

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
			PrimeFaces.current().ajax().update("fLibroTestamentoDetalle:globalMsg");
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

		ls_return = NavegacionCommon.LIBRO_TESTAMENTO;

		clear();

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setLibroTestamento(null);
		setInsertar(false);
	}
}
