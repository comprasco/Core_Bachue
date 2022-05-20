package com.bachue.snr.prosnr21.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.Archivo;
import com.bachue.snr.prosnr21.model.pgn.ConArchivo;

import com.bachue.snr.prosnr21.web.bean.BaseBean;
import com.bachue.snr.prosnr21.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase para el manejo de la capa web para Rubro
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanArchivo")
@SessionScoped
public class BeanArchivo extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4867857576700130451L;

	/** Propiedad iccmv datos auditoria. */
	private Collection<ConArchivo> iccmv_datosAuditoria;

	/** Propiedad ir rubro. */
	private ConArchivo ia_conArchivo;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad isc imagen. */
	private StreamedContent isc_imagen;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param aa_archivo asigna el valor a la propiedad
	 */
	public void setArchivo(ConArchivo aa_archivo)
	{
		ia_conArchivo = aa_archivo;
	}

	/**
	 * Método de obtencion del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public ConArchivo getArchivo()
	{
		return ia_conArchivo;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param acmv_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<ConArchivo> acmv_datosAuditoria)
	{
		iccmv_datosAuditoria = acmv_datosAuditoria;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<ConArchivo> getDatosAuditoria()
	{
		return iccmv_datosAuditoria;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ab_insertar asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_insertar)
	{
		ib_insertar = ab_insertar;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @return Retorna el valor de la propiedad isc_imagen
	 */
	public StreamedContent getImagen()
	{
		return isc_imagen;
	}

	/**
	 * @param Modifica el valor de la propiedad isc_imagen por isc_imagen
	 */
	public void setImagen(StreamedContent isc_imagen)
	{
		this.isc_imagen = isc_imagen;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo Rubro
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setArchivo((new ConArchivo()));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarArchivo");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consulta detalla para un Rubro en especial
	 * @param ar_r de tipo rubro
	 * @throws B2BException
	 */
	public void consultaDetallada(ConArchivo ar_r)
	    throws B2BException
	{
		if(ar_r != null)
		{
			String     ll_idCausalMayorValor;
			ConArchivo latd_td;
			latd_td                   = new ConArchivo();
			ll_idCausalMayorValor     = ar_r.getIdArchivo();
			latd_td.setIdArchivo(ll_idCausalMayorValor);

			latd_td = ipr_parameterRemote.findArchivoById(latd_td);

			if(latd_td != null)
			{
				Collection<ConArchivo> lctd_td;

				lctd_td = new ArrayList<ConArchivo>();

				lctd_td.add(latd_td);
				setArchivo(latd_td);

				setDatosAuditoria(lctd_td);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método de consulta de todos los rubros
	 * @return una colleccion de tipo rubro
	 */
	public Collection<ConArchivo> findAllArchivo()
	{
		Collection<ConArchivo> lca_ca;
		lca_ca = null;

		try
		{
			lca_ca = ipr_parameterRemote.findAllArchivo();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lca_ca;
	}

	/**
	 * Método de limpiar
	 */
	public void limpiar()
	{
		setArchivo(null);
		setInsertar(false);
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 * @return String con la url
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
			ConArchivo la_archivo;

			la_archivo = getArchivo();

			if(la_archivo != null)
			{
				String ls_nombre;

				{
					ls_nombre     = null;
//					ls_nombre     = la_archivo.getNombre();
					lb_focus      = validateStyles(":fArchivoDetalle:idNombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
//					ls_nombre     = la_archivo.getActivo();
					lb_focus = validateStyles(":fArchivoDetalle:idActivo", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ipr_parameterRemote.salvarArchivo(
				    la_archivo, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				setArchivo(null);

				ls_result = NavegacionCommon.ARCHIVO;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fArchivoDetalle:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Método para descargar el archivo en formato rtf de la constante.
	 */
	public void descargarConstante()
	{
		try
		{
			String ls_idConstante;
			ls_idConstante = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("idArchivo");

			setImagen(null);

			if(StringUtils.isValidString(ls_idConstante))
			{
				ConArchivo lc_constante;
				lc_constante = new ConArchivo();

				lc_constante.setIdArchivo(ls_idConstante);

				lc_constante = ipr_parameterRemote.findArchivoById(lc_constante);

				if(lc_constante != null)
				{
					InputStream lis_stream;
					lis_stream = new ByteArrayInputStream(lc_constante.getArchivo());

					setImagen(
					    new DefaultStreamedContent(
					        lis_stream, TipoContenidoCommon.TXT, "ArchivoTXTGenerado_" + ls_idConstante
					        + ".txt"
					    )
					);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}
}
