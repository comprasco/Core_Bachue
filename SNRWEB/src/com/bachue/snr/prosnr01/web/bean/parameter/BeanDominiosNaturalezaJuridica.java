package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.png.DominioNaturalezaJuridica;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de eventos e interacciones con la capa de presentación de la pantalla
 * paramétrica de dominios naturaleza jurídica.
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanDominiosNaturalezaJuridica")
@SessionScoped
public class BeanDominiosNaturalezaJuridica extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8550749940738764397L;

	/** Propiedad icdnj data dominios nat jur. */
	private Collection<DominioNaturalezaJuridica> icdnj_dataDominiosNatJur;

	/** Propiedad idnj dominio naturaleza juridica. */
	private DominioNaturalezaJuridica idnj_dominioNaturalezaJuridica;

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
	 * Modifica el valor de data dominios nat jur.
	 *
	 * @param acdnj_cdnj asigna el valor a la propiedad data dominios nat jur
	 */
	public void setDataDominiosNatJur(Collection<DominioNaturalezaJuridica> acdnj_cdnj)
	{
		icdnj_dataDominiosNatJur = acdnj_cdnj;
	}

	/**
	 * Retorna el valor de data dominios nat jur.
	 *
	 * @return el valor de data dominios nat jur
	 */
	public Collection<DominioNaturalezaJuridica> getDataDominiosNatJur()
	{
		return icdnj_dataDominiosNatJur;
	}

	/**
	 * Modifica el valor de dominio naturaleza juridica.
	 *
	 * @param adnj_dnj asigna el valor a la propiedad dominio naturaleza juridica
	 */
	public void setDominioNaturalezaJuridica(DominioNaturalezaJuridica adnj_dnj)
	{
		idnj_dominioNaturalezaJuridica = adnj_dnj;
	}

	/**
	 * Retorna el valor de dominio naturaleza juridica.
	 *
	 * @return el valor de dominio naturaleza juridica
	 */
	public DominioNaturalezaJuridica getDominioNaturalezaJuridica()
	{
		return idnj_dominioNaturalezaJuridica;
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
	 * Metodo que indica si se presiono el boton de insertar Dominio Naturaleza.
	 *
	 * @param adnj_dominioSeleccionado objeto de tipo DominioNaturalezaJuridica
	 * el cual se llevara hacia la base de datos insertando un nuevo registro o
	 * modificando uno ya existente
	 * @param ab_proceso indica si se va a insertar o modificar dependiendo su valor
	 * si es true inserta, pero en cambio si es false su valor modifica en la base de datos
	 * @return Cadena de caracteres con el enlace a la página de detalle de dominio naturaleza jurídica
	 */
	public String botonInsertar(DominioNaturalezaJuridica adnj_dominioSeleccionado, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			adnj_dominioSeleccionado = new DominioNaturalezaJuridica();

			setInsert(true);
		}
		else
			setInsert(false);

		setDominioNaturalezaJuridica(adnj_dominioSeleccionado);

		ls_return = NavegacionCommon.DOMINIOS_NATURALEZA_JURIDICA_DETALLE;

		return ls_return;
	}

	/**
	 * Metodo para traer todos los registros  existentes en la base de datos de la tabla SDB_PNG_DOMINIO_NATURALEZA_JURIDICA.
	 *
	 * @return Colección de dominios naturaleza jurídica resultante de la consulta
	 */
	public Collection<DominioNaturalezaJuridica> cargarDominios()
	{
		Collection<DominioNaturalezaJuridica> lcdnj_dominios;
		lcdnj_dominios = new LinkedList<DominioNaturalezaJuridica>();

		try
		{
			lcdnj_dominios = ipr_parameterRemote.findAllDominiosNaturalezaJuridica(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			setDataDominiosNatJur(lcdnj_dominios);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcdnj_dominios;
	}

	/**
	 * Método que instancia atributos de la clase.
	 */
	public void iniciar()
	{
		idnj_dominioNaturalezaJuridica = new DominioNaturalezaJuridica();
		setInsert(false);
	}

	/**
	 * Metodo para insertar o actualizar en la base de datos para la tabla SDB_PNG_DOMINIO_NATURALEZA_JURIDICA.
	 *
	 * @param ab_insertar indica si se desea insertar o actualizar dependiendo su valor, si su valor es true el inserta un nuevo registro,
	 * en cambio si su valor es false realiza una actualizacion en la base de datos.
	 * @return Cadena de caracteres con el enlace a la página inicial de dominios naturaleza jurídica
	 */
	public String insertUpdateDominio(boolean ab_insertar)
	{
		FacesContext              lfc_context;
		DominioNaturalezaJuridica ldnj_dominioInsertUpdate;

		ldnj_dominioInsertUpdate     = getDominioNaturalezaJuridica();
		lfc_context                  = FacesContext.getCurrentInstance();

		try
		{
			{
				String ls_idDominio;
				ls_idDominio = ldnj_dominioInsertUpdate.getIdDominioNatJur();

				validateStyles(":fDominiosNaturalezaJuridicaDetalle:idItIdDominio", lfc_context, ls_idDominio, true);

				if(!StringUtils.isValidString(ls_idDominio))
					throw new B2BException(ErrorKeys.ERROR_ID_DOMINIO_NATURALEZA_JURIDICA);
			}

			{
				boolean lb_focus;
				lb_focus = true;

				String ls_activo;
				ls_activo     = ldnj_dominioInsertUpdate.getActivo();

				lb_focus = validateStyles(
					    ":fDominiosNaturalezaJuridicaDetalle:idItActivo", lfc_context, ls_activo, lb_focus
					);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
			}

			ipr_parameterRemote.insertUpdateDominioNaturalezaJuridica(
			    ldnj_dominioInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update(":fDominiosNaturalezaJuridicaDetalle:gDominiosDetMsg");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			return null;
		}

		return NavegacionCommon.DOMINIOS_NATURALEZA_JURIDICA;
	}
}
