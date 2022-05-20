package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Locale;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Contiene metodos para el control de la pantalla de configuracion del idioma
 * y país de la aplicación.
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanConfiguracionIdiomaPais")
@SessionScoped
public class BeanConfiguracionIdiomaPais extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5888551425927881433L;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is lenguaje. */
	private String is_lenguaje;

	/** Propiedad is pais. */
	private String is_pais;

	/** {@inheritdoc} */
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de lenguaje.
	 *
	 * @param as_s asigna el valor a la propiedad lenguaje
	 */
	public void setLenguaje(String as_s)
	{
		is_lenguaje = as_s;
	}

	/**
	 * Retorna el valor de lenguaje.
	 *
	 * @return el valor de lenguaje
	 */
	public String getLenguaje()
	{
		return is_lenguaje;
	}

	/**
	 * Modifica el valor de pais.
	 *
	 * @param as_s asigna el valor a la propiedad pais
	 */
	public void setPais(String as_s)
	{
		is_pais = as_s;
	}

	/**
	 * Retorna el valor de pais.
	 *
	 * @return el valor de pais
	 */
	public String getPais()
	{
		return is_pais;
	}

	/**
	 * Consulta los datos de la constante de configuración de formato de moneda
	 * y los carga a la instancia de una variable.
	 *
	 * @param ab_activar indica si se deben aplicar al sistema los datos que
	 * retornó la consulta
	 */
	public void cargarDataConstanteMoneda(boolean ab_activar)
	{
		try
		{
			Constantes lc_temp;

			lc_temp = irr_referenceRemote.findConstantesById(ConstanteCommon.CONFIGURACION_IDIOMA_PAIS);

			if(lc_temp != null)
			{
				String ls_caracter;
				ls_caracter = lc_temp.getCaracter();

				if(StringUtils.isValidString(ls_caracter))
				{
					String   ls_idioma;
					String   ls_pais;
					String[] ls_temp;

					ls_temp       = ls_caracter.split("-");
					ls_idioma     = ls_temp[0];
					ls_pais       = ls_temp[1];

					setLenguaje(ls_idioma);
					setPais(ls_pais);

					if(ab_activar)
					{
						Locale.setDefault(new Locale(ls_idioma, ls_pais));

						addMessage(MessagesKeys.CONFIGURACION_ACTUALIZADA);
						PrimeFaces.current().ajax().update("fConfigIdiomaPais:gConfigMsg");
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Importa los valores de lenguaje y país ingresados por pantalla y actualiza
	 * el valor de caracter de la constante de configuración de idioma y país.
	 */
	public void guardarValorConstante()
	{
		boolean      lb_focus;
		FacesContext lfc_context;
		String       ls_idioma;
		String       ls_pais;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();
		ls_idioma       = getLenguaje();
		ls_pais         = getPais();

		try
		{
			lb_focus = validateStyles(":fConfigIdiomaPais:idItIdioma", lfc_context, ls_idioma, lb_focus);

			if(!StringUtils.isValidString(ls_idioma))
				throw new B2BException(ErrorKeys.ERROR_SIN_ABREVIACION_IDIOMA);

			lb_focus = validateStyles(":fConfigIdiomaPais:idItPais", lfc_context, ls_pais, lb_focus);

			if(!StringUtils.isValidString(ls_pais))
				throw new B2BException(ErrorKeys.ERROR_SIN_ABREVIACION_PAIS);

			if((ls_idioma.length() > 2) || (ls_pais.length() > 2))
				throw new B2BException(ErrorKeys.ERROR_NUMERO_CARACTERES_EXCEDIDO_PAIS_IDIOMA);

			{
				ExpresionRegular ler_validador;

				ler_validador = ExpresionRegular.getExpresionRegular();

				if((!ler_validador.esSoloLetras(ls_idioma)) || (!ler_validador.esSoloLetras(ls_pais)))
					throw new B2BException(ErrorKeys.ERROR_CARACTERES_ESPECIALES_GENERAL);
			}

			ls_idioma     = ls_idioma.toLowerCase();
			ls_pais       = ls_pais.toUpperCase();

			{
				String ls_update;

				ls_update = ls_idioma + "-" + ls_pais;

				irr_referenceRemote.updateCaracter(ConstanteCommon.CONFIGURACION_IDIOMA_PAIS, ls_update, getUserId());
			}

			setLenguaje(ls_idioma);
			setPais(ls_pais);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fConfigIdiomaPais:gConfigMsg");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}
}
