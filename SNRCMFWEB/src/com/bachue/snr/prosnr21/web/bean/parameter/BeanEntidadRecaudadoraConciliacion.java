package com.bachue.snr.prosnr21.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.common.constants.MessagesKeys;
import com.bachue.snr.prosnr21.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;

import com.bachue.snr.prosnr21.web.bean.BaseBean;
import com.bachue.snr.prosnr21.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.IOException;
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
 * Clase para el manejo de la capa web para EntidadRecaudadoraConciliacion
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanEntidadRecaudadoraConciliacion")
@SessionScoped
public class BeanEntidadRecaudadoraConciliacion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4417879768713343148L;

	/** Propiedad iccmv datos auditoria. */
	private Collection<EntidadRecaudadoraConciliacion> iccmv_datosAuditoria;

	/** Propiedad iccmv temp. */
	private Collection<EntidadRecaudadoraConciliacion> icerc_coleccionEntidadTemp;

	/** Propiedad ir EntidadRecaudadoraConciliacion. */
	private EntidadRecaudadoraConciliacion ir_entidadRecaudadoraConciliacion;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/**Propiedad codigo Entidad Recaudadora Conciliacion*/
	private String is_codigoEntidadRecaudadoraConciliacion;

	/**Propiedad id Entidad Recaudadora Conciliacion*/
	private String is_idEntidadRecaudadoraConciliacion;

	/**Propiedad nombre Entidad Recaudadora Conciliacion*/
	private String is_nombreEntidadRecaudadoraConciliacion;

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
	 * @param ar_EntidadRecaudadoraConciliacion asigna el valor a la propiedad
	 */
	public void setEntidadRecaudadoraConciliacion(EntidadRecaudadoraConciliacion ar_EntidadRecaudadoraConciliacion)
	{
		ir_entidadRecaudadoraConciliacion = ar_EntidadRecaudadoraConciliacion;
	}

	/**
	 * Método de obtencion del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public EntidadRecaudadoraConciliacion getEntidadRecaudadoraConciliacion()
	{
		return ir_entidadRecaudadoraConciliacion;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param acmv_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<EntidadRecaudadoraConciliacion> acmv_datosAuditoria)
	{
		iccmv_datosAuditoria = acmv_datosAuditoria;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<EntidadRecaudadoraConciliacion> getDatosAuditoria()
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
	 * @return Retorna el valor de la propiedad is_idEntidadRecaudadoraConciliacion
	 */
	public String getIdEntidadRecaudadoraConciliacion()
	{
		return is_idEntidadRecaudadoraConciliacion;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idEntidadRecaudadoraConciliacion por as_idEntidadRecaudadoraConciliacion
	 */
	public void setIdEntidadRecaudadoraConciliacion(String as_idEntidadRecaudadoraConciliacion)
	{
		is_idEntidadRecaudadoraConciliacion = as_idEntidadRecaudadoraConciliacion;
	}

	/**
	 * @return Retorna el valor de la propiedad icerc_coleccionEntidadTemp
	 */
	public Collection<EntidadRecaudadoraConciliacion> getColeccionEntidadTemp()
	{
		return icerc_coleccionEntidadTemp;
	}

	/**
	 * @param Modifica el valor de la propiedad icerc_coleccionEntidadTemp por acerc_coleccionEntidadTemp
	 */
	public void setColeccionEntidadTemp(Collection<EntidadRecaudadoraConciliacion> acerc_coleccionEntidadTemp)
	{
		icerc_coleccionEntidadTemp = acerc_coleccionEntidadTemp;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nombreEntidadRecaudadoraConciliacion
	 */
	public String getNombreEntidadRecaudadoraConciliacion()
	{
		return is_nombreEntidadRecaudadoraConciliacion;
	}

	/**
	 * @param Modifica el valor de la propiedad is_nombreEntidadRecaudadoraConciliacion por as_nombreEntidadRecaudadoraConciliacion
	 */
	public void setNombreEntidadRecaudadoraConciliacion(String as_nombreEntidadRecaudadoraConciliacion)
	{
		is_nombreEntidadRecaudadoraConciliacion = as_nombreEntidadRecaudadoraConciliacion;
	}

	/**
	 * @return Retorna el valor de la propiedad is_codigoEntidadRecaudadoraConciliacion
	 */
	public String getCodigoEntidadRecaudadoraConciliacion()
	{
		return is_codigoEntidadRecaudadoraConciliacion;
	}

	/**
	 * @param Modifica el valor de la propiedad is_codigoEntidadRecaudadoraConciliacion por is_codigoEntidadRecaudadoraConciliacion
	 */
	public void setCodigoEntidadRecaudadoraConciliacion(String as_codigoEntidadRecaudadoraConciliacion)
	{
		is_codigoEntidadRecaudadoraConciliacion = as_codigoEntidadRecaudadoraConciliacion;
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		setCodigoEntidadRecaudadoraConciliacion(null);
		setColeccionEntidadTemp(null);
		setDatosAuditoria(null);
		setEntidadRecaudadoraConciliacion(null);
		setIdEntidadRecaudadoraConciliacion(null);
		setNombreEntidadRecaudadoraConciliacion(null);
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo EntidadRecaudadoraConciliacion
	 */
	public void cambiarEstado()
	{
		limpiar();
		setInsertar(true);
		setEntidadRecaudadoraConciliacion((new EntidadRecaudadoraConciliacion()));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarEntidadRecaudadoraConciliacion");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consulta detalla para un EntidadRecaudadoraConciliacion en especial
	 * @param aerc_erc de tipo EntidadRecaudadoraConciliacion
	 * @throws B2BException
	 */
	public void consultaDetallada(EntidadRecaudadoraConciliacion aerc_erc)
	    throws B2BException
	{
		if(aerc_erc != null)
		{
			String                         ls_idEntidadRecaudadoraConciliacion;
			EntidadRecaudadoraConciliacion lerc_erc;
			lerc_erc                                = new EntidadRecaudadoraConciliacion();
			ls_idEntidadRecaudadoraConciliacion     = aerc_erc.getIdEntidadRecaudadora();
			lerc_erc.setIdEntidadRecaudadora(ls_idEntidadRecaudadoraConciliacion);

			lerc_erc = ipr_parameterRemote.findEntidadRecaudadoraConciliacionById(lerc_erc);

			if(lerc_erc != null)
			{
				Collection<EntidadRecaudadoraConciliacion> lcerc_erc;

				lcerc_erc = new ArrayList<EntidadRecaudadoraConciliacion>();

				lcerc_erc.add(lerc_erc);
				setEntidadRecaudadoraConciliacion(lerc_erc);
				findAllEntidadRecaudadoraConciliacionCatalogo();
				setIdEntidadRecaudadoraConciliacion(lerc_erc.getIdEntidadRecaudadora());
				setNombreEntidadRecaudadoraConciliacion(lerc_erc.getNombreEntidadRecaudadora());
				setCodigoEntidadRecaudadoraConciliacion(lerc_erc.getCodigoEntidadRecaudadora());

				setDatosAuditoria(lcerc_erc);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método de consulta de todos los EntidadRecaudadoraConciliacions
	 * @return una colleccion de tipo EntidadRecaudadoraConciliacion
	 */
	public Collection<EntidadRecaudadoraConciliacion> findAllEntidadRecaudadoraConciliacion()
	{
		Collection<EntidadRecaudadoraConciliacion> lcerc_erc;
		lcerc_erc = null;

		try
		{
			lcerc_erc = ipr_parameterRemote.findAllEntidadRecaudadoraConciliacion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcerc_erc;
	}

	/**
	 *método para encontrar todos los Entidad Recaudadora Conciliacion.
	 * @return Colección con datos solicitados
	 */
	public Collection<EntidadRecaudadoraConciliacion> findAllEntidadRecaudadoraConciliacionCatalogo()
	{
		Collection<EntidadRecaudadoraConciliacion> lcuo_datos;

		try
		{
			lcuo_datos = ipr_parameterRemote.findAllEntidadRecaudadoraConciliacionCatalogo();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcuo_datos = null;
		}

		setColeccionEntidadTemp(lcuo_datos);

		return lcuo_datos;
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 * @return String con la url
	 * @throws IOException
	 */
	public String salvar()
	    throws IOException
	{
		String       ls_result;
		boolean      lb_focus;
		FacesContext lfc_context;

		ls_result       = null;
		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			EntidadRecaudadoraConciliacion lr_EntidadRecaudadoraConciliacion;
			String                         ls_idEntidadRecaudadora;

			lr_EntidadRecaudadoraConciliacion     = getEntidadRecaudadoraConciliacion();
			ls_idEntidadRecaudadora               = getIdEntidadRecaudadoraConciliacion();

			if(lr_EntidadRecaudadoraConciliacion != null)
			{
				{
					String ls_validador;

					ls_validador     = lr_EntidadRecaudadoraConciliacion.getActivo();

					lb_focus     = validateStyles(
						    ":fEntidadRecaudadoraConciliacionDetalle:idActivo", lfc_context, ls_validador, lb_focus
						);

					ls_validador     = getNombreEntidadRecaudadoraConciliacion();

					lb_focus = validateStyles(
						    ":fEntidadRecaudadoraConciliacionDetalle:idNombreEntidad", lfc_context, ls_validador,
						    lb_focus
						);

					lr_EntidadRecaudadoraConciliacion.setNombreEntidadRecaudadora(ls_validador);

					ls_validador     = getCodigoEntidadRecaudadoraConciliacion();

					lb_focus = validateStyles(
						    ":fEntidadRecaudadoraConciliacionDetalle:idCodigoEntidadRecaudadora", lfc_context,
						    ls_validador, lb_focus
						);

					lr_EntidadRecaudadoraConciliacion.setCodigoEntidadRecaudadora(ls_validador);
					lr_EntidadRecaudadoraConciliacion.setIdEntidadRecaudadora(ls_idEntidadRecaudadora);
				}

				ipr_parameterRemote.salvarEntidadRecaudadoraConciliacion(
				    lr_EntidadRecaudadoraConciliacion, isInsertar(), getUserId(), getLocalIpAddress(),
				    getRemoteIpAddress()
				);

				if(isInsertar())
					addMessageInfo("I", MessagesKeys.INCERSION_EXITOSA);
				else
					addMessageInfo("I", MessagesKeys.MODIFICACION_EXITOSA);

				setEntidadRecaudadoraConciliacion(null);
				limpiar();

				ls_result = NavegacionCommon.ENTIDAD_RECAUDADORA_CONCILIACION;

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

					PrimeFaces.current().ajax().update("fentidadRecaudadoraConciliacion:globalMsg");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fEntidadRecaudadoraConciliacionDetalle:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Método de actualización de la pantalla de Entidad Recaudadora Conciliacion
	 */
	public void actualizarEntidadRecaudadora()
	{
		Collection<EntidadRecaudadoraConciliacion> lcerc_collectionEntidad;
		String                                     ls_idEntidadRecaudadora;
		ls_idEntidadRecaudadora     = getIdEntidadRecaudadoraConciliacion();
		lcerc_collectionEntidad     = getColeccionEntidadTemp();

		if(StringUtils.isValidString(ls_idEntidadRecaudadora))
		{
			if(CollectionUtils.isValidCollection(lcerc_collectionEntidad))
			{
				for(EntidadRecaudadoraConciliacion ilerc : lcerc_collectionEntidad)
				{
					if(ilerc != null)
					{
						if(ls_idEntidadRecaudadora.equals(ilerc.getIdEntidadRecaudadora()))
						{
							setNombreEntidadRecaudadoraConciliacion(ilerc.getNombreEntidadRecaudadora());
							setCodigoEntidadRecaudadoraConciliacion(ilerc.getCodigoEntidadRecaudadora());
						}
					}
				}
			}
		}
		else
			setCodigoEntidadRecaudadoraConciliacion(null);
	}
}
