package com.bachue.snr.prosnr10.business.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoEntradaConsultarRRRMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaDerechosDerecho;
import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaDerechosDerechoIntervinientesInterviniente;
import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaHipotecasHipoteca;
import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaHipotecasHipotecaIntervinientesInterviniente;
import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidad;
import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidadIntervinientesInterviniente;
import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidad;
import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidadIntervinientesInterviniente;
import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccion;
import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccionIntervinientesInterviniente;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.DescripcionMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.col.TipoRrr;

import com.bachue.snr.prosnr10.common.constants.ErrorKeys;
import com.bachue.snr.prosnr10.common.constants.TipoRRRCommon;

import com.bachue.snr.prosnr10.model.catastro.AnotacionCatastro;
import com.bachue.snr.prosnr10.model.catastro.IntervinienteCatastro;
import com.bachue.snr.prosnr10.model.catastro.PropietarioCatastro;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades MatriculasRRRBusiness.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/03/2020
 */
public class MatriculasRRRBusiness extends CatastroBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MatriculasRRRBusiness.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Permite consultar la información asociada a los derechos, restricciones, responsabilidades, hipotecas o publicidades sobre una propiedad inmueble con Folio de Matrícula Inmobiliaria.
	 *
	 * @param atecrrrm_entrada de atecrrrm entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar RRR matriculas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarRRRMatriculas consultaRRRMatriculas(
	    TipoEntradaConsultarRRRMatriculas atecrrrm_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		TipoSalidaConsultarRRRMatriculas ltscrrrm_respuesta;
		BigInteger                       lbi_codigoMensaje;
		String                           ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		ltscrrrm_respuesta        = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;

		try
		{
			if(atecrrrm_entrada == null)
				throw new B2BException(addErrorCTO(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS_CODIGO));

			Predio lp_datos;

			lp_datos = new Predio(atecrrrm_entrada);

			if(validarTipoIdentificacionPredio(lp_datos) && validarNumeroIdentificacionPredio(lp_datos))
			{
				PredioRegistro lpr_predioRegistro;

				lpr_predioRegistro = consultarInformacionMatricula(lp_datos, ldm_manager);

				if(lpr_predioRegistro != null)
				{
					long                ll_idMatricula;
					String              ls_idCirculo;
					Collection<TipoRrr> lctrrr_trrr;

					obtenerInfoZonaRegistralPredio(lp_datos, lpr_predioRegistro.getIdZonaRegistral(), ldm_manager);

					ltscrrrm_respuesta     = new TipoSalidaConsultarRRRMatriculas();
					ls_idCirculo           = lpr_predioRegistro.getIdCirculo();
					ll_idMatricula         = lpr_predioRegistro.getIdMatricula();
					lctrrr_trrr            = DaoCreator.getTipoRrrDAO(ldm_manager).findAll(true);

					ltscrrrm_respuesta.setCodDepartamento(lp_datos.getCodDepartamento());
					ltscrrrm_respuesta.setCodMunicipio(lp_datos.getCodMunicipio());
					ltscrrrm_respuesta.setCodCirculoRegistral(lp_datos.getCodCirculoRegistral());
					ltscrrrm_respuesta.setNumMatriculaInmobiliaria(String.valueOf(lpr_predioRegistro.getIdMatricula()));

					if(CollectionUtils.isValidCollection(lctrrr_trrr))
					{
						for(TipoRrr ltrrr_actual : lctrrr_trrr)
						{
							if(ltrrr_actual != null)
							{
								String ls_idTipoRRR;

								ls_idTipoRRR = ltrrr_actual.getIdTipoRrr();

								consultarListaAnotacionesRRR(
								    ltscrrrm_respuesta,
								    consultarAnotacionesCirculoMatriculaRRR(
								        ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula), ls_idTipoRRR,
								        ldm_manager
								    ), ls_idTipoRRR, ldm_manager
								);
							}
						}
					}

					ltscrrrm_respuesta.setCodMensaje(lbi_codigoMensaje);
					ltscrrrm_respuesta.setDescripcionMensaje(ls_descripcionMensaje);
				}
				else
					throw new B2BException(addErrorCTO(ErrorKeys.ERROR_NUMERO_ID_CATASTRAL_NO_EXISTENTE_CODIGO));
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarRRRMatriculas", lb2be_e);
			ltscrrrm_respuesta = null;

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarRRRMatriculas", le_e);
			ltscrrrm_respuesta     = null;

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltscrrrm_respuesta == null)
			ltscrrrm_respuesta = new TipoSalidaConsultarRRRMatriculas(
				    null, null, null, null, null, null, null, null, null, lbi_codigoMensaje, ls_descripcionMensaje
				);

		return ltscrrrm_respuesta;
	}

	/**
	 * Consultar lista anotaciones RRR.
	 *
	 * @param atscrrrm_respuesta de atscrrrm respuesta
	 * @param lcap_anotacionPredio de lcanp anotacion predio
	 * @param as_tipoRRR de as tipo RRR
	 * @param adm_manager de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void consultarListaAnotacionesRRR(
	    TipoSalidaConsultarRRRMatriculas atscrrrm_respuesta, Collection<AnotacionPredio> lcap_anotacionPredio,
	    String as_tipoRRR, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(
			    (atscrrrm_respuesta != null) && CollectionUtils.isValidCollection(lcap_anotacionPredio)
				    && StringUtils.isValidString(as_tipoRRR)
			)
			{
				TipoSalidaConsultarRRRMatriculasListaDerechosDerecho[]                  listaDerechos;
				TipoSalidaConsultarRRRMatriculasListaHipotecasHipoteca[]                listaHipotecas;
				TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidad[]           listaPublicidades;
				TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidad[] listaResponsabilidades;
				TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccion[]         listaRestricciones;
				int                                                                     li_count;
				int                                                                     li_size;

				li_size                    = lcap_anotacionPredio.size();
				listaDerechos              = new TipoSalidaConsultarRRRMatriculasListaDerechosDerecho[li_size];
				listaHipotecas             = new TipoSalidaConsultarRRRMatriculasListaHipotecasHipoteca[li_size];
				listaPublicidades          = new TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidad[li_size];
				listaResponsabilidades     = new TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidad[li_size];
				listaRestricciones         = new TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccion[li_size];
				li_count                   = 0;

				for(AnotacionPredio lap_anotacionPredio : lcap_anotacionPredio)
				{
					if(lap_anotacionPredio != null)
					{
						AnotacionCatastro lac_ac;

						lac_ac = obtenerDatosAnotacionIntervinientes(lap_anotacionPredio, adm_manager);

						if(lac_ac != null)
						{
							Collection<PropietarioCatastro>                                                                    lcic_cic;
							TipoSalidaConsultarRRRMatriculasListaDerechosDerechoIntervinientesInterviniente[]                  ltscnmsmsmlppii_IntervinientesDerecho;
							TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidadIntervinientesInterviniente[] ltscnmsmsmlppii_IntervinientesResponsabilidades;
							TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccionIntervinientesInterviniente[]         ltscnmsmsmlppii_IntervinientesRestricciones;
							TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidadIntervinientesInterviniente[]           ltscnmsmsmlppii_IntervinientesPublicidad;
							TipoSalidaConsultarRRRMatriculasListaHipotecasHipotecaIntervinientesInterviniente[]                ltscnmsmsmlppii_IntervinientesHipoteca;

							lcic_cic                                            = lac_ac.getIntervinientesCatastro();
							ltscnmsmsmlppii_IntervinientesDerecho               = null;
							ltscnmsmsmlppii_IntervinientesResponsabilidades     = null;
							ltscnmsmsmlppii_IntervinientesRestricciones         = null;
							ltscnmsmsmlppii_IntervinientesPublicidad            = null;
							ltscnmsmsmlppii_IntervinientesHipoteca              = null;

							if(CollectionUtils.isValidCollection(lcic_cic))
							{
								int li_sizeIntervinientes;
								int li_contadorIntervinientes;

								li_sizeIntervinientes                               = lcic_cic.size();
								li_contadorIntervinientes                           = 0;
								ltscnmsmsmlppii_IntervinientesDerecho               = new TipoSalidaConsultarRRRMatriculasListaDerechosDerechoIntervinientesInterviniente[li_sizeIntervinientes];
								ltscnmsmsmlppii_IntervinientesResponsabilidades     = new TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidadIntervinientesInterviniente[li_sizeIntervinientes];
								ltscnmsmsmlppii_IntervinientesRestricciones         = new TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccionIntervinientesInterviniente[li_sizeIntervinientes];
								ltscnmsmsmlppii_IntervinientesPublicidad            = new TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidadIntervinientesInterviniente[li_sizeIntervinientes];
								ltscnmsmsmlppii_IntervinientesHipoteca              = new TipoSalidaConsultarRRRMatriculasListaHipotecasHipotecaIntervinientesInterviniente[li_sizeIntervinientes];

								for(IntervinienteCatastro lic_actual : lcic_cic)
								{
									if(lic_actual != null)
									{
										switch(as_tipoRRR)
										{
											case TipoRRRCommon.DERECHOS:
												ltscnmsmsmlppii_IntervinientesDerecho[li_contadorIntervinientes++] = new TipoSalidaConsultarRRRMatriculasListaDerechosDerechoIntervinientesInterviniente(
													    lic_actual.getTipoDocumentoPersona(),
													    lic_actual.getNumDocumentoPersona(),
													    lic_actual.getPrimerNombre(), lic_actual.getSegundoNombre(),
													    lic_actual.getPrimerApellido(), lic_actual.getSegundoApellido(),
													    lic_actual.getRazonSocial(), lic_actual.getRolInterviniente(),
													    lic_actual.getTipoParteInteresada()
													);

												break;

											case TipoRRRCommon.RESPONSABILIDAD:
												ltscnmsmsmlppii_IntervinientesResponsabilidades[li_contadorIntervinientes++] = new TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidadIntervinientesInterviniente(
													    lic_actual.getTipoDocumentoPersona(),
													    lic_actual.getNumDocumentoPersona(),
													    lic_actual.getPrimerNombre(), lic_actual.getSegundoNombre(),
													    lic_actual.getPrimerApellido(), lic_actual.getSegundoApellido(),
													    lic_actual.getRazonSocial(), lic_actual.getRolInterviniente(),
													    lic_actual.getTipoParteInteresada()
													);

												break;

											case TipoRRRCommon.RESTRICCION:
												ltscnmsmsmlppii_IntervinientesRestricciones[li_contadorIntervinientes++] = new TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccionIntervinientesInterviniente(
													    lic_actual.getTipoDocumentoPersona(),
													    lic_actual.getNumDocumentoPersona(),
													    lic_actual.getPrimerNombre(), lic_actual.getSegundoNombre(),
													    lic_actual.getPrimerApellido(), lic_actual.getSegundoApellido(),
													    lic_actual.getRazonSocial(), lic_actual.getRolInterviniente(),
													    lic_actual.getTipoParteInteresada()
													);

												break;

											case TipoRRRCommon.PUBLICIDAD:
												ltscnmsmsmlppii_IntervinientesPublicidad[li_contadorIntervinientes++] = new TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidadIntervinientesInterviniente(
													    lic_actual.getTipoDocumentoPersona(),
													    lic_actual.getNumDocumentoPersona(),
													    lic_actual.getPrimerNombre(), lic_actual.getSegundoNombre(),
													    lic_actual.getPrimerApellido(), lic_actual.getSegundoApellido(),
													    lic_actual.getRazonSocial(), lic_actual.getRolInterviniente(),
													    lic_actual.getTipoParteInteresada()
													);

												break;

											case TipoRRRCommon.HIPOTECA:
												ltscnmsmsmlppii_IntervinientesHipoteca[li_contadorIntervinientes++] = new TipoSalidaConsultarRRRMatriculasListaHipotecasHipotecaIntervinientesInterviniente(
													    lic_actual.getTipoDocumentoPersona(),
													    lic_actual.getNumDocumentoPersona(),
													    lic_actual.getPrimerNombre(), lic_actual.getSegundoNombre(),
													    lic_actual.getPrimerApellido(), lic_actual.getSegundoApellido(),
													    lic_actual.getRazonSocial(), lic_actual.getRolInterviniente(),
													    lic_actual.getTipoParteInteresada()
													);

												break;

											default:
												break;
										}
									}
								}
							}

							switch(as_tipoRRR)
							{
								case TipoRRRCommon.DERECHOS:
									listaDerechos[li_count++] = new TipoSalidaConsultarRRRMatriculasListaDerechosDerecho(
										    lac_ac.getNumAnotacion(), lac_ac.getComentario(), lac_ac.getFechaAnotacion(),
										    lac_ac.getCodNaturalezaJuridicaFolio(),
										    lac_ac.getNomNaturalezaJuridicaFolio(),
										    lac_ac.getDominioDRR(), ltscnmsmsmlppii_IntervinientesDerecho
										);

									break;

								case TipoRRRCommon.RESPONSABILIDAD:
									listaResponsabilidades[li_count++] = new TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidad(
										    lac_ac.getNumAnotacion(), lac_ac.getComentario(), lac_ac.getFechaAnotacion(),
										    lac_ac.getCodNaturalezaJuridicaFolio(),
										    lac_ac.getNomNaturalezaJuridicaFolio(), lac_ac.getDominioDRR(),
										    ltscnmsmsmlppii_IntervinientesResponsabilidades
										);

									break;

								case TipoRRRCommon.RESTRICCION:
									listaRestricciones[li_count++] = new TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccion(
										    lac_ac.getNumAnotacion(), lac_ac.getComentario(), lac_ac.getFechaAnotacion(),
										    lac_ac.getCodNaturalezaJuridicaFolio(),
										    lac_ac.getNomNaturalezaJuridicaFolio(), lac_ac.getDominioDRR(),
										    ltscnmsmsmlppii_IntervinientesRestricciones
										);

									break;

								case TipoRRRCommon.PUBLICIDAD:
									listaPublicidades[li_count++] = new TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidad(
										    lac_ac.getNumAnotacion(), lac_ac.getComentario(), lac_ac.getFechaAnotacion(),
										    lac_ac.getCodNaturalezaJuridicaFolio(),
										    lac_ac.getNomNaturalezaJuridicaFolio(),
										    lac_ac.getDominioDRR(), ltscnmsmsmlppii_IntervinientesPublicidad
										);

									break;

								case TipoRRRCommon.HIPOTECA:
									listaHipotecas[li_count++] = new TipoSalidaConsultarRRRMatriculasListaHipotecasHipoteca(
										    lac_ac.getNumAnotacion(), lac_ac.getComentario(), lac_ac.getFechaAnotacion(),
										    lac_ac.getCodNaturalezaJuridicaFolio(),
										    lac_ac.getNomNaturalezaJuridicaFolio(),
										    lac_ac.getDominioDRR(), ltscnmsmsmlppii_IntervinientesHipoteca
										);

									break;

								default:
									break;
							}
						}
					}
				}

				switch(as_tipoRRR)
				{
					case TipoRRRCommon.DERECHOS:
						atscrrrm_respuesta.setListaDerechos(listaDerechos);

						break;

					case TipoRRRCommon.RESPONSABILIDAD:
						atscrrrm_respuesta.setListaResponsabilidades(listaResponsabilidades);

						break;

					case TipoRRRCommon.RESTRICCION:
						atscrrrm_respuesta.setListaRestricciones(listaRestricciones);

						break;

					case TipoRRRCommon.PUBLICIDAD:
						atscrrrm_respuesta.setListaPublicidades(listaPublicidades);

						break;

					case TipoRRRCommon.HIPOTECA:
						atscrrrm_respuesta.setListaHipotecas(listaHipotecas);

						break;

					default:
						break;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarListaAnotacionesRRR", lb2be_e);
			throw lb2be_e;
		}
	}
}
