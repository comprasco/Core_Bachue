package com.bachue.snr.prosnr01.web.bean.reusableScreens;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.extensions.model.layout.LayoutOptions;

import org.primefaces.model.DefaultStreamedContent;

import java.io.Serializable;

import java.util.Collection;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades DetalleEjecucionVisitas.
 *
 * @author Jorge Esteban Patiño Fonseca Fecha de Creacion: 23/10/2021
 */
@SessionScoped
@ManagedBean(name = "beanProyeccionDocumento")
public class BeanProyeccionDocumento extends BaseBean implements Serializable, ProyeccionDocumentoInterface
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3166687259774535239L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanProyeccionDocumento.class);

	/** Propiedad ictpr campos pantalla. */
	private Collection<TagPlantillaResolucion> ictpr_camposPantalla;

	/** Propiedad idsc imagen documento. */
	private DefaultStreamedContent idsc_imagenDocumento;

	/** Propiedad llo layout options. */
	private LayoutOptions llo_layoutOptions;

	/** Propiedad iot solucion tags. */
	private OficiosTexto iot_solucionTags;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad iba documento array. */
	private byte[] iba_documentoArray;

	/** Propiedad ib mostrar boton. */
	private boolean ib_mostrarBotonSalvarProyeccionDocumento;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.bachue.snr.prosnr01.web.bean.BaseBean#getApplication()
	 */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de CamposPantalla.
	 *
	 * @param actpr_ctpr de actpr ctpr
	 */
	public void setCamposPantalla(Collection<TagPlantillaResolucion> actpr_ctpr)
	{
		ictpr_camposPantalla = actpr_ctpr;
	}

	/**
	 * Retorna Objeto o variable de valor campos pantalla.
	 *
	 * @return el valor de campos pantalla
	 */
	public Collection<TagPlantillaResolucion> getCamposPantalla()
	{
		return ictpr_camposPantalla;
	}

	/**
	 * Modifica el valor de DocumentoArray.
	 *
	 * @param aba_ba de aba ba
	 */
	public void setDocumentoArray(byte[] aba_ba)
	{
		iba_documentoArray = aba_ba;
	}

	/**
	 * Retorna Objeto o variable de valor documento array.
	 *
	 * @return el valor de documento array
	 */
	public byte[] getDocumentoArray()
	{
		return iba_documentoArray;
	}

	/**
	 * Modifica el valor de imagen documento.
	 *
	 * @param adsc_dsc asigna el valor a la propiedad imagen documento
	 */
	public void setImagenDocumento(DefaultStreamedContent adsc_dsc)
	{
		idsc_imagenDocumento = adsc_dsc;
	}

	/**
	 * Retorna el valor de imagen documento.
	 *
	 * @return el valor de imagen documento
	 */
	public DefaultStreamedContent getImagenDocumento()
	{
		setImagenDocumento(
		    generarArchivosDescarga(
		        getDocumentoArray(), TipoContenidoCommon.PDF,
		        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + ExtensionCommon.PDF_PUNTO
		    )
		);

		return idsc_imagenDocumento;
	}

	/**
	 * Modifica el valor de layout options.
	 *
	 * @param alo_lo asigna el valor a la propiedad layout options
	 */
	public void setLayoutOptions(LayoutOptions alo_lo)
	{
		llo_layoutOptions = alo_lo;
	}

	/**
	 * Retorna el valor de layout options.
	 *
	 * @return el valor de layout options
	 */
	public LayoutOptions getLayoutOptions()
	{
		return llo_layoutOptions;
	}

	/**
	 * Modifica el valor de MostrarBoton.
	 *
	 * @param ab_b de ab b
	 */
	public void setMostrarBotonSalvarProyeccionDocumento(boolean ab_b)
	{
		ib_mostrarBotonSalvarProyeccionDocumento = ab_b;
	}

	/**
	 * Valida la propiedad mostrar boton.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna
	 *         <code>false</code> en mostrar boton
	 */
	public boolean isMostrarBotonSalvarProyeccionDocumento()
	{
		return ib_mostrarBotonSalvarProyeccionDocumento;
	}

	/**
	 * Modifica el valor de SolucionTags.
	 *
	 * @param aot_ot de aot ot
	 */
	public void setSolucionTags(OficiosTexto aot_ot)
	{
		iot_solucionTags = aot_ot;
	}

	/**
	 * Retorna Objeto o variable de valor solucion tags.
	 *
	 * @return el valor de solucion tags
	 */
	public OficiosTexto getSolucionTags()
	{
		return iot_solucionTags;
	}

	/**
	 * Cambiar usuario principal.
	 *
	 * @param au_usuario correspondiente al valor de au usuario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */

	/**
	 * Clean.
	 */
	public void cleanProyeccionDocumento()
	{
		setCamposPantalla(null);
		setImagenDocumento(null);
		setSolucionTags(null);
		setDocumentoArray(null);
		setMostrarBotonSalvarProyeccionDocumento(false);
	}

	/**
	 * Generar documentos.
	 *
	 * @param aba_documento de aba documento
	 * @throws B2BException
	 */
	public void generarDocumentos()
	    throws B2BException
	{
		try
		{
			Collection<TagPlantillaResolucion> lctpr_camposPantalla;

			lctpr_camposPantalla = getCamposPantalla();

			if(CollectionUtils.isValidCollection(lctpr_camposPantalla))
			{
				for(TagPlantillaResolucion ltpr_iterator : lctpr_camposPantalla)
				{
					if(ltpr_iterator != null)
					{
						if(!StringUtils.isValidString(ltpr_iterator.getTexto()))
							throw new B2BException(ErrorKeys.ERROR_CAMPOS);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDocumentos", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Ocultar boton salvar proyeccion documento.
	 */
	public void ocultarBotonSalvarProyeccionDocumento()
	{
		setMostrarBotonSalvarProyeccionDocumento(false);
	}

	/**
	 * Regresar proyeccion documento.
	 *
	 * @return el valor de string
	 */
	public String regresarProyeccionDocumento()
	{
		return null;
	}

	/**
	 * Initialize.
	 */
	@PostConstruct
	protected void initialize()
	{
		LayoutOptions llo_layout;
		LayoutOptions llo_paneles;

		llo_layout      = new LayoutOptions();
		llo_paneles     = new LayoutOptions();

		llo_paneles.addOption("slidable", new Boolean(false));
		llo_paneles.addOption("resizeWhileDragging", new Boolean(true));
		llo_layout.setPanesOptions(llo_paneles);

		LayoutOptions llo_layoutSecundario = new LayoutOptions();
		llo_layoutSecundario.addOption("size", NumericUtils.getInteger(500));
		llo_layoutSecundario.addOption("minSize", NumericUtils.getInteger(300));
		llo_layout.setWestOptions(llo_layoutSecundario);
		setLayoutOptions(llo_layout);
	}
}
