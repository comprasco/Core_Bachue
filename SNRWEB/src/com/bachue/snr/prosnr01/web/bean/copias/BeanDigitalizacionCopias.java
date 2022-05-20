package com.bachue.snr.prosnr01.web.bean.copias;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.copias.DigitalizacionCopias;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;

import com.bachue.snr.prosnr01.web.bean.antiguo.sistema.BeanBuscarAntiguoSistema;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanDigitalizacionCopias.
 *
 * @author hcastaneda
 */
@SessionScoped
@ManagedBean(name = "beanDigitalizacionCopias")
public class BeanDigitalizacionCopias extends BeanBuscarAntiguoSistema implements Serializable
{
	private static final long serialVersionUID = 1782077076563915199L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanDigitalizacionCopias.class);

	/** Constante is_idForm. */
	public static final String cs_ID_FORM = "fDigitalizacionCopias";

	/** Constante is_messageIdGrowl. */
	private static final String cs_ID_GROWL = cs_ID_FORM + ":idGrowl";

	/** Propiedad iccc campos consulta. */
	private Collection<CamposConsulta> iccc_camposConsulta;

	/** Propiedad ir registroRemote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/**
	 * Modifica el valor de campos consulta.
	 *
	 * @param accc_ccc asigna el valor a la propiedad campos consulta
	 */
	public void setCamposConsulta(Collection<CamposConsulta> accc_ccc)
	{
		iccc_camposConsulta                    = accc_ccc;
	}

	/**
	 * Retorna el valor de campos consulta.
	 *
	 * @return el valor de campos consulta
	 */
	public Collection<CamposConsulta> getCamposConsulta()
	{
		return iccc_camposConsulta;
	}

	/**
	 * Metodo encargado de cargar la información capturada en expedición de copias para digitalización de copias.
	 */
	public void cargarInformacionDigitalizacionCopias()
	{
		try
		{
			DigitalizacionCopias ldc_digitalizacionCopias;

			ldc_digitalizacionCopias = new DigitalizacionCopias();

			ldc_digitalizacionCopias.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

			ldc_digitalizacionCopias = irr_registroRemote.cargarInformacionDigitalizacionCopias(
				    ldc_digitalizacionCopias, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ldc_digitalizacionCopias != null)
			{
				Collection<CamposConsulta> lccc_camposConsulta;

				lccc_camposConsulta = ldc_digitalizacionCopias.getCamposConsulta();

				if(CollectionUtils.isValidCollection(lccc_camposConsulta))
				{
					for(CamposConsulta lcc_iterador : lccc_camposConsulta)
					{
						if(lcc_iterador != null)
							cambiarDatosMultivaloresCampoCriterio(lcc_iterador);
					}
				}

				setCamposConsulta(lccc_camposConsulta);
				setSolicitud(ldc_digitalizacionCopias.getSolicitud());
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarInformacionDigitalizacionCopias", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de agregar criterios al detalle de criterios.
	 *
	 * @param ab_guardarCriterios Argumento de tipo boolean que determina si se deben guardar criterios o no.
	 * @throws B2BException Señala que se produjo una excepción
	 */
	public void consultarCriterio(boolean ab_guardarCriterios)
	    throws B2BException
	{
		consultarCriterio(ab_guardarCriterios, false);
	}

	/**
	 * Metodo encargado de agregar criterios al detalle de criterios.
	 *
	 * @param ab_guardarCriterios Argumento de tipo boolean que determina si se deben guardar criterios o no.
	 * @param ab_traerSolicitudCopias Argumento de tipo boolean que determina si se deben traer datos de solicitud copias o no.
	 * @throws B2BException Señala que se produjo una excepción
	 */
	public void consultarCriterio(boolean ab_guardarCriterios, boolean ab_traerSolicitudCopias)
	    throws B2BException
	{
		try
		{
			Collection<CamposConsulta> lccc_camposConsulta;

			lccc_camposConsulta = getCamposConsulta();

			if(CollectionUtils.isValidCollection(lccc_camposConsulta))
			{
				for(CamposConsulta lcc_iterador : lccc_camposConsulta)
				{
					if(lcc_iterador != null)
					{
						Collection<CamposConsulta> lccc_dataCamposConsulta;

						lccc_dataCamposConsulta = lcc_iterador.getDataCamposConsulta();

						if(CollectionUtils.isValidCollection(lccc_dataCamposConsulta))
						{
							for(CamposConsulta lcc_campoPantalla : lccc_dataCamposConsulta)
							{
								if((lcc_campoPantalla != null) && lcc_campoPantalla.isObligatoriedad())
									lcc_campoPantalla.setRojoPantalla(
									    !StringUtils.isValidString(lcc_campoPantalla.getValorCampo())
									);
							}
						}
					}
				}

				{
					CamposConsulta lcc_camposConsulta;

					lcc_camposConsulta = irr_registroRemote.guardarCriteriosYConsultarCopias(
						    lccc_camposConsulta, getSolicitud(), ab_guardarCriterios, ab_traerSolicitudCopias,
						    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lcc_camposConsulta != null)
					{
						Collection<DocumentoOWCC> lcdo_documentosOWCC;

						lcdo_documentosOWCC = lcc_camposConsulta.getDocumentosOWCC();

						setDocumentosOWCC(lcdo_documentosOWCC);

						if(!CollectionUtils.isValidCollection(lcdo_documentosOWCC))
							throw new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS);
					}
					else
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarCriterio", lb2be_e);

			addMessage(lb2be_e);

			setDocumentosOWCC(null);
		}
		finally
		{
			actualizarComponente(cs_ID_FORM);
		}
	}
}
