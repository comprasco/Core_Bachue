package com.bachue.snr.prosnr01.web.bean.consulta.documento.publico;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.SGD.ConsultaSGDRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.documento.publico.ConsultaDocumentoPublicoRemote;

import com.bachue.snr.prosnr01.model.Matricula;
import com.bachue.snr.prosnr01.model.antiguoSistema.ConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;

import com.bachue.snr.prosnr01.web.bean.antiguo.sistema.BeanAntSistema;

import java.io.IOException;
import java.io.Serializable;

import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Permite controlar las acciones en pantalla de la consulta por documento público.
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanConsultaDocumentoPublico")
@SessionScoped
public class BeanConsultaDocumentoPublico extends BeanAntSistema implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3675012338640851350L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanConsultaDocumentoPublico.class);

	/** Constante is_idForm. */
	private static final String is_idForm = "fConsultaDocumentoPublico:";

	/** Constante is_idTipoDoc. */
	private static final String is_idTipoDoc = "idSOMEscrituraDocumento";

	/** Constante is_idNumDoc. */
	private static final String is_idNumDoc = "idItDocuActoDocumento:";

	/** Constante is_fechaDoc. */
	private static final String is_fechaDoc = "idCalFechaDocDocumento:";

	/** Propiedad icd documentos consultados. */
	private Collection<Documento> icd_documentosConsultados;

	/** Propiedad icd documentos consultados 2. */
	private Collection<Documento> icd_documentosConsultados2;

	/** Propiedad icdpr consulta documento publico remote. */
	@EJB
	private ConsultaDocumentoPublicoRemote icdpr_consultaDocumentoPublicoRemote;

	/** Propiedad irr consulta sgd remote. */
	@EJB
	private ConsultaSGDRemote irr_consultaSGDRemote;

	/**
	 * Propiedad ab render asociados.
	 */
	private boolean ab_renderAsociados;

	/**
	 * Propiedad ab render registrados.
	 */
	private boolean ab_renderRegistrados;

	/**
	 * Modifica el valor de documentos consultados.
	 *
	 * @param acd_cd asigna el valor a la propiedad documentos consultados
	 */
	public void setDocumentosConsultados(Collection<Documento> acd_cd)
	{
		icd_documentosConsultados = acd_cd;
	}

	/**
	 * Retorna el valor de documentos consultados.
	 *
	 * @return el valor de documentos consultados
	 */
	public Collection<Documento> getDocumentosConsultados()
	{
		return icd_documentosConsultados;
	}

	/**
	 * Modifica el valor de documentos consultados 2.
	 *
	 * @param acd_cd asigna el valor a la propiedad documentos consultados 2
	 */
	public void setDocumentosConsultados2(Collection<Documento> acd_cd)
	{
		icd_documentosConsultados2 = acd_cd;
	}

	/**
	 * Retorna el valor de documentos consultados 2.
	 *
	 * @return el valor de documentos consultados 2
	 */
	public Collection<Documento> getDocumentosConsultados2()
	{
		return icd_documentosConsultados2;
	}

	/**
	 * Modifica el valor de Render asociados.
	 *
	 * @param ab_b Objeto o Variable de tipo boolean que asigna un valor a la propiedad RenderAsociados
	 */
	public void setRenderAsociados(boolean ab_b)
	{
		ab_renderAsociados = ab_b;
	}

	/**
	 * Valida la propiedad render asociados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en render asociados
	 */
	public boolean isRenderAsociados()
	{
		return ab_renderAsociados;
	}

	/**
	 * Modifica el valor de Render registrados.
	 *
	 * @param ab_b Objeto o Variable de tipo boolean que asigna un valor a la propiedad RenderRegistrados
	 */
	public void setRenderRegistrados(boolean ab_b)
	{
		ab_renderRegistrados = ab_b;
	}

	/**
	 * Valida la propiedad render registrados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en render registrados
	 */
	public boolean isRenderRegistrados()
	{
		return ab_renderRegistrados;
	}

	/**
	 * Limpia las variables de instancia de la clase.
	 */
	public void clean()
	{
		super.clean();
		setConsultaDatosDocumento(null);
		setDocumentosConsultados(null);
		setRenderAsociados(false);
		setRenderRegistrados(false);

		{
			ConsultaDatosDocumento lcdd_datosConsulta;

			lcdd_datosConsulta = getConsultaDatosDocumento();

			if(lcdd_datosConsulta != null)
			{
				OficinaOrigen loo_oficinaOrigen;

				loo_oficinaOrigen = lcdd_datosConsulta.getOficinaOrigen();

				if(loo_oficinaOrigen != null)
				{
					String ls_idPais;

					ls_idPais = loo_oficinaOrigen.getIdPais();

					if(!StringUtils.isValidString(ls_idPais))
						loo_oficinaOrigen.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
				}
			}
		}
	}

	/**
	 * Método encargado de consultar la información del documento.
	 *
	 * @param ab_asociados Argumento de tipo <code>boolean</code> que indica si hay documentos asociados.
	 * @param ad_row Argumento de tipo <code>Documento</code> que contiene el documento que se va a consultar.
	 */
	public void consultaSGD(boolean ab_asociados, Documento ad_row)
	{
		try
		{
			if(ad_row != null)
			{
				DocumentoOWCC ldo_documentoConsulta;

				ldo_documentoConsulta = new DocumentoOWCC();

				ldo_documentoConsulta.setNir(ad_row.getNir());
				ldo_documentoConsulta.setTurno(ad_row.getIdTurno());

				if(ab_asociados)
				{
					Matricula lm_matricula;

					lm_matricula = Matricula.getMatricula(ad_row.getNirAsociados());

					if(lm_matricula != null)
					{
						ldo_documentoConsulta.setIdOrip(lm_matricula.getCirculo());
						ldo_documentoConsulta.setMatriculas(StringUtils.getString(lm_matricula.getMatricula()));
					}
				}
				else
				{
					ldo_documentoConsulta.setIdOrip(ad_row.getIdCirculo());
					ldo_documentoConsulta.setMatriculas(StringUtils.getString(ad_row.getIdMatricula()));
				}

				{
					ConsultaDatosDocumento lcdd_infoPantalla;

					lcdd_infoPantalla = getConsultaDatosDocumento();

					if(lcdd_infoPantalla != null)
					{
						{
							Documento ld_documento;

							ld_documento = lcdd_infoPantalla.getDocumento();

							if(ld_documento != null)
							{
								ldo_documentoConsulta.setDocType(ld_documento.getIdTipoDocumento());
								ldo_documentoConsulta.setNumeroDoc(ld_documento.getNumero());
								ldo_documentoConsulta.setFechaDocumento(ld_documento.getFechaDocumento());
							}
						}

						{
							OficinaOrigen loo_oficina;

							loo_oficina = lcdd_infoPantalla.getOficinaOrigen();

							if(loo_oficina != null)
							{
								DocumentoOWCC ldo_resultadoConsulta;

								ldo_resultadoConsulta = irr_consultaSGDRemote.generarDataPais(
									    loo_oficina, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
									);

								if(ldo_resultadoConsulta != null)
								{
									ldo_documentoConsulta.setNombrePais(ldo_resultadoConsulta.getNombrePais());
									ldo_documentoConsulta.setNombreDepartamento(
									    ldo_resultadoConsulta.getNombreDepartamento()
									);
									ldo_documentoConsulta.setNombreMunicipio(
									    ldo_resultadoConsulta.getNombreMunicipio()
									);

									if(ab_asociados)
										ldo_documentoConsulta.setEntidadOrigen(ad_row.getIdOficinaOrigen());
									else
										ldo_documentoConsulta.setEntidadOrigen(
										    ldo_resultadoConsulta.getEntidadOrigen()
										);
								}
							}
						}
					}
				}

				accionSGD(ldo_documentoConsulta, NavegacionCommon.CONSULTA_DOCUMENTO_PUBLICO);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultaSGD", lb2be_e);

			addMessage(new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS));
		}
		catch(IOException lio_exception)
		{
			clh_LOGGER.error("consultaSGD", lio_exception);

			addMessage(new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS));
		}
	}

	/**
	 * Busca en la base de datos los documentos quye coincidan con la información ingresada en los filtros de
	 * la consulta.
	 */
	public void consultarDocumento()
	{
		try
		{
			ConsultaDatosDocumento lcdd_datosConsulta;

			lcdd_datosConsulta = getConsultaDatosDocumento();

			if(lcdd_datosConsulta != null)
			{
				Documento ld_documento;

				ld_documento = lcdd_datosConsulta.getDocumento();

				if(ld_documento != null)
				{
					FacesContext lfc_context;
					boolean      lb_focus;

					lb_focus        = true;
					lfc_context     = FacesContext.getCurrentInstance();

					{
						String ls_documento;

						ls_documento = ld_documento.getIdTipoDocumento();

						validateStyles(is_idForm + is_idTipoDoc, lfc_context, ls_documento, lb_focus);

						if(!StringUtils.isValidString(ls_documento))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);
					}

					{
						String ls_numero;

						ls_numero = ld_documento.getNumero();

						validateStyles(is_idForm + is_idNumDoc, lfc_context, ls_numero, lb_focus);

						if(!StringUtils.isValidString(ls_numero))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_DE_DOC);
					}

					{
						Date ld_fecha;

						ld_fecha = ld_documento.getFechaDocumento();

						validateStyles(is_idForm + is_fechaDoc, lfc_context, ld_fecha, lb_focus);

						if(ld_fecha == null)
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);
					}
				}
			}

			Documento ld_documentos;

			ld_documentos = icdpr_consultaDocumentoPublicoRemote.findByIdSolicitud(
				    lcdd_datosConsulta, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ld_documentos != null)
			{
				boolean lb_asociados;
				boolean lb_registrados;

				lb_asociados       = CollectionUtils.isValidCollection(ld_documentos.getDocumentosAsociados());
				lb_registrados     = CollectionUtils.isValidCollection(ld_documentos.getDocumentosRegistrados());

				setRenderAsociados(lb_asociados);
				setRenderRegistrados(lb_registrados);

				if(!lb_asociados && !lb_registrados)
				{
					setDocumentosConsultados(null);
					setDocumentosConsultados2(null);

					throw new B2BException(ErrorKeys.ERROR_SIN_REGISTROS_CONSULTA);
				}
				else
				{
					setDocumentosConsultados(ld_documentos.getDocumentosAsociados());
					setDocumentosConsultados2(ld_documentos.getDocumentosRegistrados());
					addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarDocumento", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Limpia la información ingresada en los campos del documento.
	 */
	public void limparDatos()
	{
		FacesContext lfc_context;

		lfc_context = FacesContext.getCurrentInstance();

		setConsultaDatosDocumento(null);
		setDocumentosConsultados(null);
		setDocumentosConsultados2(null);
		setRenderAsociados(false);
		setRenderRegistrados(false);

		validateStyles(is_idForm + is_idTipoDoc, lfc_context, IdentificadoresCommon.DATO_VALIDO, true);
		validateStyles(is_idForm + is_idNumDoc, lfc_context, IdentificadoresCommon.DATO_VALIDO, true);
		validateStyles(is_idForm + is_fechaDoc, lfc_context, IdentificadoresCommon.DATO_VALIDO, true);
	}
}
