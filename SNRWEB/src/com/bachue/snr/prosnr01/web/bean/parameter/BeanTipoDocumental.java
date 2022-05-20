package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanTipoDocumental.
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanTipoDocumental")
@SessionScoped
public class BeanTipoDocumental extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7006465044408426404L;

	/** Propiedad icp datos auditoria. */
	private Collection<TipoDocumental> ictd_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad itd tipo documental. */
	private TipoDocumental itd_tipoDocumental;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<TipoDocumental> actd_datosAuditoria)
	{
		ictd_datosAuditoria = actd_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoDocumental> getDatosAuditoria()
	{
		return ictd_datosAuditoria;
	}

	/**
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_a)
	{
		ib_insertar = ab_a;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean getInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoDocumental(TipoDocumental atd_td)
	{
		itd_tipoDocumental = atd_td;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public TipoDocumental getTipoDocumental()
	{
		return itd_tipoDocumental;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo Campo
	 * Criterio Busqueda
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setTipoDocumental((new TipoDocumental()));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoDocumental");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 *método de consulta detalada de Tipo Documental por medio de su indicativo
	 * @param atd_td indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(TipoDocumental atd_td)
	    throws B2BException
	{
		if(atd_td != null)
		{
			String ls_idTipoDocumental;

			ls_idTipoDocumental = atd_td.getIdTipoDocumental();

			if(StringUtils.isValidString(ls_idTipoDocumental))
			{
				atd_td = ipr_parameterRemote.findAllTipoDocumentalById(ls_idTipoDocumental);

				if(atd_td != null)
				{
					Collection<TipoDocumental> lctd_td;

					lctd_td = new ArrayList<TipoDocumental>();

					lctd_td.add(atd_td);
					setTipoDocumental(atd_td);

					setDatosAuditoria(lctd_td);
				}

				setInsertar(false);
			}
		}
	}

/**
 *Método de consulta para consultar todos los tipos documental
 * @return una colección con los datos solicitados
 */
	public Collection<TipoDocumental> findAllTipoDocumental()
	{
		Collection<TipoDocumental> lccc_cc;
		lccc_cc = null;

		try
		{
			lccc_cc = ipr_parameterRemote.findAllTipoDocumental();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccc_cc;
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 * @return
	 */
	public String salvar()
	{
		String       ls_result;
		boolean      lb_focus;
		FacesContext lfc_context;

		ls_result       = null;
		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			TipoDocumental ltd_TipoDocumental;

			ltd_TipoDocumental = getTipoDocumental();

			if(ltd_TipoDocumental != null)
			{
				{
					String ls_nombre;

					ls_nombre     = ltd_TipoDocumental.getNombre();

					lb_focus = validateStyles(":fTipoDocumentalDetalle:idNombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;

					ls_activo     = ltd_TipoDocumental.getActivo();

					lb_focus = validateStyles(":fTipoDocumentalDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ipr_parameterRemote.salvarTipoDocumental(
				    ltd_TipoDocumental, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				setTipoDocumental(null);

				{
					BeanReference lbr_beanReference;

					lbr_beanReference = getBeanReference();

					lbr_beanReference.setTipoDocumental(null);
					lbr_beanReference.setTiposDocumentales(null);
				}

				ls_result = NavegacionCommon.TIPO_DOCUMENTAL;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fTipoDocumentalDetalle:globalMsg");
		}

		return ls_result;
	}
}
