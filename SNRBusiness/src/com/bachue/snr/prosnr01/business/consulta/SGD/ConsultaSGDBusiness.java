package com.bachue.snr.prosnr01.business.consulta.SGD;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.integracion.cliente.owcc.ParametrosDocumento;
import com.bachue.prosnr01.integracion.cliente.owcc.consultarDocumentos.ClienteConsultarDocumentos;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.consulta.estado.predio.ConsultaPredioBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.consulta.predio.ConsultaPredio;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * Permite el manejo de logica de negocio y manejo de transacciones con la base
 * de datos
 *
 * @author Jorge Patiño
 *
 */
public class ConsultaSGDBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaSGDBusiness.class);

	/** Constante Consulta predio business*/
	private ConsultaPredioBusiness icpb_consultaPredioBusiness;

	/**
	 * Método de asignación de  valor de la propiedad
	 * @param icpb_consultaPredioBusiness
	 */
	public void setConsultaPredioBusiness(ConsultaPredioBusiness icpb_consultaPredioBusiness)
	{
		this.icpb_consultaPredioBusiness = icpb_consultaPredioBusiness;
	}

	/**
	 * Método de obtención del valor de la propiedad consulta predio
	 * @return de tipo ConsultaPredioBusiness con el valor de la propiedad
	 */
	public ConsultaPredioBusiness getConsultaPredioBusiness()
	{
		return (icpb_consultaPredioBusiness == null) ? (icpb_consultaPredioBusiness = new ConsultaPredioBusiness())
		                                             : icpb_consultaPredioBusiness;
	}

	/**
	 * Método de consulta con la base de datos para encontrar la trazabilidad de una matricula
	 * @param acp_consultaPredio  con los parametros de la consulta
	 * @return de tipo collection de trazabilidad con el valor de la consulta
	 * @throws B2BException
	 */
	public synchronized Collection<Trazabilidad> consultaPorMatriculaYCirculo(ConsultaPredio acp_consultaPredio)
	    throws B2BException
	{
		Collection<Trazabilidad> lct_return;

		lct_return = null;

		if(acp_consultaPredio != null)
		{
			try
			{
				ConsultaPredio lcp_prediotemp;

				lcp_prediotemp = getConsultaPredioBusiness().consultar(acp_consultaPredio);

				if(lcp_prediotemp != null)
				{
					Collection<ConsultaPredio> lccp_temp;

					lccp_temp = lcp_prediotemp.getDataConsulta();

					if(CollectionUtils.isValidCollection(lccp_temp))
					{
						Iterator<ConsultaPredio> licp_iterator;

						licp_iterator = lccp_temp.iterator();

						if(licp_iterator != null)
						{
							lct_return = new ArrayList<Trazabilidad>();

							while(licp_iterator.hasNext())
							{
								ConsultaPredio lcp_temp;

								lcp_temp = licp_iterator.next();

								if(licp_iterator != null)
									lct_return.add(new Trazabilidad(lcp_temp));
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("consultaPorMatriculaYCirculo", lb2be_e);

				throw lb2be_e;
			}
		}

		return lct_return;
	}

/**
 * Método para realizar una búsqueda en el gestor documental por medio de WebService
 * @param ado_parametrosBusqueda <code>DocumentoOWCC</code> que contiene los parametros para realizar la búsqueda.
 * @return <code>Collection</code> llena de <code>DocumentoOWCC</code> resultantes de la invocación del WebService.
 * @throws B2BException
 */
	public synchronized Collection<DocumentoOWCC> consultaSGD(DocumentoOWCC ado_parametrosBusqueda)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<DocumentoOWCC> lcdo_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcdo_return     = null;

		try
		{
			if(ado_parametrosBusqueda != null)
			{
				{
					String ls_nir;
					String ls_idSolicitud;
					String ls_turno;

					ls_nir             = ado_parametrosBusqueda.getNir();
					ls_turno           = ado_parametrosBusqueda.getTurno();
					ls_idSolicitud     = null;

					if(StringUtils.isValidString(ls_turno))
					{
						Turno lt_turno;

						lt_turno = DaoCreator.getTurnoDAO(ldm_manager).buscarNirPorIdTurno(ls_turno);

						if(lt_turno != null)
						{
							ado_parametrosBusqueda.setNir(lt_turno.getNir());
							ls_idSolicitud = lt_turno.getIdSolicitud();
						}
					}
					else if(StringUtils.isValidString(ls_nir))
					{
						Solicitud ls_solicitud;

						ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findByNir(ls_nir);

						if(ls_solicitud != null)
							ls_idSolicitud = ls_solicitud.getIdSolicitud();
					}

					if(StringUtils.isValidString(ls_turno))
						ado_parametrosBusqueda.setTurnoVinculado(obtenerTurnosVinculados(ls_turno, ldm_manager));

					if(StringUtils.isValidString(ls_idSolicitud))
						ado_parametrosBusqueda.setNirVinculado(obtenerNIRsVinculados(ls_idSolicitud, false, ldm_manager));
				}

				Constantes lc_constante;

				lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
						                     .findById(ConstanteCommon.CONSULTA_DOCUMENTOS_OWCC_ENDPOINT);

				if(lc_constante != null)
				{
					lcdo_return = ClienteConsultarDocumentos.consultarDocumentos(
						    SistemaOrigenCommon.CORE,
						    ParametrosDocumento.generarParametrosBusquedaOWCC(ado_parametrosBusqueda, true),
						    RepositorioCommon.MIXTO, lc_constante.getCaracter()
						);

					if(!CollectionUtils.isValidCollection(lcdo_return))
						throw new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultaSGD", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcdo_return;
	}

	/**
	 * Método de consulta con la base de datos para generar la informacion de un país
	 * @param aoo_oficina con los parametros de consulta del país
	 * @return de tipo DocumentoOWCC con el resultado de la consulta
	 * @throws B2BException
	 */
	public synchronized DocumentoOWCC generarDataPais(OficinaOrigen aoo_oficina)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		DocumentoOWCC ldo_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldo_return      = null;

		try
		{
			if(aoo_oficina != null)
			{
				Pais lp_pais;

				lp_pais        = DaoCreator.getPaisDAO(ldm_manager).findById(aoo_oficina.getIdPais());
				ldo_return     = new DocumentoOWCC();

				if(lp_pais != null)
				{
					Departamento ld_departamento;

					ld_departamento = DaoCreator.getDepartamentoDAO(ldm_manager)
							                        .findById(lp_pais.getIdPais(), aoo_oficina.getIdDepartamento());

					ldo_return.setNombrePais(lp_pais.getNombre());

					if(ld_departamento != null)
					{
						Municipio lm_municipio;

						lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager)
								                     .findById(
								    ld_departamento.getIdPais(), ld_departamento.getIdDepartamento(),
								    aoo_oficina.getIdMunicipio()
								);

						ldo_return.setNombreDepartamento(ld_departamento.getNombre());

						if(lm_municipio != null)
							ldo_return.setNombreMunicipio(lm_municipio.getNombre());
					}
				}

				ldo_return.setEntidadOrigen(aoo_oficina.getIdOficinaOrigen());
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDataPais", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldo_return;
	}

	/**
	 * Método encargado de generar una <code>Collection</code> cargada con la
	 * respuesta del servicio de búsqueda el OWCC.
	 *
	 * @param ado_parametrosBusqueda
	 *            <code>DocumentoOWCC</code> que contiene los parametros que serán
	 *            enviados al servicio.
	 * @return <code>Collection</code> cargada con la respuesta del servicio de
	 *         búsqueda el OWCC
	 * @throws B2BException
	 */
	public synchronized Collection<DocumentoOWCC> generarDocumento(DocumentoOWCC ado_parametrosBusqueda)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<DocumentoOWCC> lcdo_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcdo_return     = null;

		try
		{
			DocumentoOWCC ldo_documento;

			ldo_documento = null;

			if(ado_parametrosBusqueda != null)
			{
				AnotacionPredio lap_anotacionPredio;

				lap_anotacionPredio     = DaoCreator.getAnotacionPredioDAO(ldm_manager)
						                                .findById(
						    new AnotacionPredio(
						        ado_parametrosBusqueda.getIdOrip(),
						        NumericUtils.getLongWrapper(ado_parametrosBusqueda.getMatriculas()),
						        NumericUtils.getLongWrapper(ado_parametrosBusqueda.getAnotacion())
						    )
						);
				ldo_documento           = new DocumentoOWCC();
				ldo_documento.setIdOrip(ado_parametrosBusqueda.getIdOrip());
				ldo_documento.setMatriculas(ado_parametrosBusqueda.getMatriculas());

				if(lap_anotacionPredio != null)
				{
					Documento ld_documento;

					ld_documento = DaoCreator.getDocumentoDAO(ldm_manager)
							                     .findByIdDocumentoVersion(
							    new Documento(
							        lap_anotacionPredio.getIdDocumento(),
							        NumericUtils.getLongWrapper(lap_anotacionPredio.getVersionDocumento())
							    )
							);

					if(ld_documento != null)
					{
						ldo_documento.setDocType(ld_documento.getIdTipoDocumento());

						{
							OficinaOrigen loo_oficina;

							loo_oficina = DaoCreator.getOficinaOrigenDAO(ldm_manager)
									                    .findById(
									    ld_documento.getIdOficinaOrigen(), ld_documento.getVersion()
									);

							if(loo_oficina != null)
							{
								Pais lp_pais;

								lp_pais = DaoCreator.getPaisDAO(ldm_manager).findById(loo_oficina.getIdPais());

								if(lp_pais != null)
								{
									Departamento ld_departamento;

									ld_departamento = DaoCreator.getDepartamentoDAO(ldm_manager)
											                        .findById(
											    lp_pais.getIdPais(), loo_oficina.getIdDepartamento()
											);

									ldo_documento.setNombrePais(lp_pais.getNombre());

									if(ld_departamento != null)
									{
										Municipio lm_municipio;

										lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager)
												                     .findById(
												    ld_departamento.getIdPais(), ld_departamento.getIdDepartamento(),
												    loo_oficina.getIdMunicipio()
												);

										ldo_documento.setNombreDepartamento(ld_departamento.getNombre());

										if(lm_municipio != null)
											ldo_documento.setNombreMunicipio(lm_municipio.getNombre());
									}
								}

								ldo_documento.setEntidadOrigen(loo_oficina.getIdOficinaOrigen());
								ldo_documento.setTipoOficina(loo_oficina.getIdTipoOficina());
							}
						}

						ldo_documento.setNumeroDoc(ld_documento.getNumero());
						ldo_documento.setFechaDocumento(ld_documento.getFechaDocumento());
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(ldo_documento != null)
				lcdo_return = consultaSGD(ldo_documento);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcdo_return;
	}
}
