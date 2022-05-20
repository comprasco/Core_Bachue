package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase para el manejo de eventos e interacciones con la capa de presentación de la pantalla
 * paramétrica de interesado documento tipo.
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanInteresadoDocumentoTipo")
@SessionScoped
public class BeanInteresadoDocumentoTipo extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2551341800807939196L;

	/** Propiedad icidt data interesados documento tipo. */
	private Collection<InteresadoDocumentoTipo> icidt_dataInteresadosDocumentoTipo;

	/** Propiedad icidt datos auditoria. */
	private Collection<InteresadoDocumentoTipo> icidt_datosAuditoria;

	/** Propiedad iidt interesado documento tipo. */
	private InteresadoDocumentoTipo iidt_interesadoDocumentoTipo;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ib insert. */
	private boolean ib_insert;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Retorna el valor de data dominios nat jur.
	 *
	 * @return el valor de data dominios nat jur
	 */
	public Collection<InteresadoDocumentoTipo> getDataDominiosNatJur()
	{
		return icidt_dataInteresadosDocumentoTipo;
	}

	/**
	 * Modifica el valor de data int documento tipos.
	 *
	 * @param acidt_cidt asigna el valor a la propiedad data int documento tipos
	 */
	public void setDataIntDocumentoTipos(Collection<InteresadoDocumentoTipo> acidt_cidt)
	{
		icidt_dataInteresadosDocumentoTipo = acidt_cidt;
	}

	/**
	 * @param datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<InteresadoDocumentoTipo> datosAuditoria)
	{
		icidt_datosAuditoria = datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<InteresadoDocumentoTipo> getDatosAuditoria()
	{
		return icidt_datosAuditoria;
	}

	/**
	 * Modifica el valor de insert.
	 *
	 * @param ab_b asigna el valor a la propiedad insert
	 */
	public void setInsert(boolean ab_b)
	{
		ib_insert = ab_b;
	}

	/**
	 * Valida la propiedad insert.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en insert
	 */
	public boolean isInsert()
	{
		return ib_insert;
	}

	/**
	 * Modifica el valor de interesado documento tipo.
	 *
	 * @param aidt_idt asigna el valor a la propiedad interesado documento tipo
	 */
	public void setInteresadoDocumentoTipo(InteresadoDocumentoTipo aidt_idt)
	{
		iidt_interesadoDocumentoTipo = aidt_idt;
	}

	/**
	 * Retorna el valor de interesado documento tipo.
	 *
	 * @return el valor de interesado documento tipo
	 */
	public InteresadoDocumentoTipo getInteresadoDocumentoTipo()
	{
		return iidt_interesadoDocumentoTipo;
	}

	/**
	 * Determina dependiendo de un flag booleano si se va a insertar un registro, o a modificar
	 * uno ya existente en la tabla SDB_COL_INTERESADO_DOCUMENTO_TIPO.
	 *
	 * @param aidt_docTipoSeleccionado correspondiente al valor del tipo de objeto InteresadoDocumentoTipo
	 * @param ab_proceso indica si la presentación de la página debe ser de inserción o actualización
	 * @return Cadena de caracteres con el enlace a la página de detalle de interesado documento tipo
	 */
	public String botonInsertar(InteresadoDocumentoTipo aidt_docTipoSeleccionado, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		try
		{
			if(ab_proceso)
			{
				aidt_docTipoSeleccionado = new InteresadoDocumentoTipo();

				setInsert(true);
			}
			else
			{
				InteresadoDocumentoTipo lidt_interesadoDocumentoTipo;

				lidt_interesadoDocumentoTipo = ipr_parameterRemote.findInteresadoDocumentoTipoById(
					    aidt_docTipoSeleccionado
					);

				if(lidt_interesadoDocumentoTipo != null)
				{
					Collection<InteresadoDocumentoTipo> lcidt_cidt;

					lcidt_cidt = new ArrayList<InteresadoDocumentoTipo>();
					lcidt_cidt.add(lidt_interesadoDocumentoTipo);

					setDatosAuditoria(lcidt_cidt);
					setInsert(false);
				}
			}

			setInteresadoDocumentoTipo(aidt_docTipoSeleccionado);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		ls_return = NavegacionCommon.INTERESADO_DOCUMENTO_TIPOS_DETALLE;

		return ls_return;
	}

	/**
	 * Consulta en la base de datos todos los registros existentes de interesado documento tipo.
	 *
	 * @return Colección de interesado documento tipo resultante de la consulta
	 */
	public Collection<InteresadoDocumentoTipo> cargarDocumentosTipo()
	{
		Collection<InteresadoDocumentoTipo> lidt_documentoTipos;
		lidt_documentoTipos = new LinkedList<InteresadoDocumentoTipo>();

		try
		{
			lidt_documentoTipos = ipr_parameterRemote.findAllInteresadoDocumentoTipos(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			setDataIntDocumentoTipos(lidt_documentoTipos);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lidt_documentoTipos;
	}

	/**
	 * Método que instancia algunos atributos de la clase.
	 */
	public void iniciar()
	{
		iidt_interesadoDocumentoTipo = new InteresadoDocumentoTipo();
		setInsert(false);
	}

	/**
	 * Metodo para el manejo de inserciones o actualizaciones de interesado documento tipo.
	 *
	 * @param ab_insertar indica si se desea insertar o actualizar dependiendo su valor, si su valor es true el inserta un nuevo registro,
	 * en cambio si su valor es false realiza una actualizacion en la base de datos.
	 * @return Cadena de caracteres con el enlace a la página inicial de interesado documento tipo.
	 */
	public String insertUpdateDocumentoTipos(boolean ab_insertar)
	{
		FacesContext            lfc_context;
		InteresadoDocumentoTipo lidt_documentoTipoInsertUpdate;
		boolean                 lb_focus;
		String                  ls_result;

		lidt_documentoTipoInsertUpdate     = getInteresadoDocumentoTipo();
		lfc_context                        = FacesContext.getCurrentInstance();
		lb_focus                           = true;
		ls_result                          = null;

		try
		{
			{
				String ls_idDocumentoTipo;
				ls_idDocumentoTipo     = lidt_documentoTipoInsertUpdate.getIdDocumentoTipo();

				lb_focus = validateStyles(
					    ":fInteresadoDocumentoTiposDetalle:idItIdDocumentoTipo", lfc_context, ls_idDocumentoTipo,
					    lb_focus
					);

				if(!StringUtils.isValidString(ls_idDocumentoTipo))
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_DOCUMENTO_TIPO);
			}

			{
				String ls_ilicode;
				ls_ilicode     = lidt_documentoTipoInsertUpdate.getIlicode();

				lb_focus = validateStyles(
					    ":fInteresadoDocumentoTiposDetalle:idItIlicode", lfc_context, ls_ilicode, lb_focus
					);

				if(!StringUtils.isValidString(ls_ilicode))
					throw new B2BException(ErrorKeys.ERROR_SIN_ILICODE);
			}

			{
				String ls_descripcion;
				ls_descripcion     = lidt_documentoTipoInsertUpdate.getDescripcion();

				lb_focus = validateStyles(
					    ":fInteresadoDocumentoTiposDetalle:idItDescripcion", lfc_context, ls_descripcion, lb_focus
					);

				if(!StringUtils.isValidString(ls_descripcion))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
			}

			{
				String ls_activo;
				ls_activo     = lidt_documentoTipoInsertUpdate.getActivo();

				lb_focus = validateStyles(
					    ":fInteresadoDocumentoTiposDetalle:idItActivo", lfc_context, ls_activo, lb_focus
					);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
			}

			ipr_parameterRemote.insertUpdateInteresadoDocumentoTipo(
			    lidt_documentoTipoInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			{
				BeanReference lbr_beanReference;
				lbr_beanReference = getBeanReference();

				lbr_beanReference.setFindDocumentoPublico(null);
				lbr_beanReference.setInteresadoDocumentoTipo(null);
				lbr_beanReference.setCargarDocumentosTipo(null);
			}

			ls_result = NavegacionCommon.INTERESADO_DOCUMENTO_TIPOS;

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
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fInteresadoDocumentoTipos:gDocumentosMsg");

			return null;
		}

		return ls_result;
	}
}
