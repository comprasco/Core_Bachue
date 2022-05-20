package com.bachue.snr.prosnr14.business.coexistencia;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.firma.FirmaMasivaBusiness;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.dao.DaoCreator;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExternaConvenio;
import com.bachue.snr.prosnr01.model.sdb.acc.ConvenioCirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.bng.Propietario;

import com.bachue.snr.prosnr14.common.constants.ErrorKeys;

import com.bachue.snr.prosnr14.model.coexistencia.CoexistenciaResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * Clase que contiene todos las propiedades CoexistenciaBusiness.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 28/05/2020
 */
public class CoexistenciaBusiness extends FirmaMasivaBusiness
{
	/**
	 * Obtener circulos convenio.
	 *
	 * @param as_numeroConvenio de as numero convenio
	 * @param adm_manager de adm manager
	 * @return el valor de sets the
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized Set<String> obtenerCirculosConvenio(String as_numeroConvenio, DAOManager adm_manager)
	    throws B2BException
	{
		Set<String> lcs_circulosConvenio;

		lcs_circulosConvenio = new HashSet<String>();

		if(StringUtils.isValidString(as_numeroConvenio))
		{
			AccEntidadExternaConvenio lexc_entidadExternaConvenio;

			lexc_entidadExternaConvenio = DaoCreator.getAccEntidadExternaConvenioDAO(adm_manager)
					                                    .findByConvenioNumero(as_numeroConvenio);

			if(lexc_entidadExternaConvenio != null)
			{
				Collection<ConvenioCirculoRegistral> lcccr_convenioCirculoRegistral;

				lcccr_convenioCirculoRegistral = DaoCreator.getConvenioCirculoRegistralDAO(adm_manager)
						                                       .findAllByIdEntidadExternaNumeroConvenio(
						    lexc_entidadExternaConvenio.getIdEntidadExterna(),
						    lexc_entidadExternaConvenio.getNumeroConvenio()
						);

				if(CollectionUtils.isValidCollection(lcccr_convenioCirculoRegistral))
				{
					for(ConvenioCirculoRegistral lccr_tmp : lcccr_convenioCirculoRegistral)
					{
						if(lccr_tmp != null)
						{
							String ls_idCirculo;

							ls_idCirculo = lccr_tmp.getIdCirculo();

							if(StringUtils.isValidString(ls_idCirculo))
								lcs_circulosConvenio.add(ls_idCirculo);
						}
					}
				}
			}
			else
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONVENIO_OBLIGATORIO_VALIDO));
		}
		else
			throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONVENIO_OBLIGATORIO_VALIDO));

		if(lcs_circulosConvenio.isEmpty())
			lcs_circulosConvenio = null;

		return lcs_circulosConvenio;
	}

	/**
	 * Filtrar consulta.
	 *
	 * @param lcr_request de lcr request
	 * @param as_servicio de as servicio
	 * @param adm_manager de adm manager
	 * @return el valor de coexistencia response
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized CoexistenciaResponse filtrarConsulta(
	    CoexistenciaResponse lcr_request, String as_servicio, DAOManager adm_manager
	)
	    throws B2BException
	{
		CoexistenciaResponse lcps_response;

		lcps_response = new CoexistenciaResponse();

		if((lcr_request != null) & StringUtils.isValidString(as_servicio))
		{
			Predio lp_predio;

			lp_predio = lcr_request.getPredio();

			if(lp_predio != null)
			{
				Set<String> lss_circulosConvenio;

				lss_circulosConvenio = obtenerCirculosConvenio(lp_predio.getConvenio(), adm_manager);

				switch(as_servicio)
				{
					case com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.VALIDACION_UNICA:

						if(CollectionUtils.isValidCollection(lss_circulosConvenio))
						{
							String ls_idCirculo;

							ls_idCirculo = lp_predio.getIdCirculo();

							if(StringUtils.isValidString(ls_idCirculo))
							{
								if(!lss_circulosConvenio.contains(ls_idCirculo))
								{
									Object[] loa_arg;

									loa_arg        = new String[2];
									loa_arg[0]     = lp_predio.getConvenio();
									loa_arg[1]     = ls_idCirculo;

									throw new B2BException(
									    addErrorCX(ErrorKeys.ERROR_PREDIO_NO_DISPONIBLE_CONVENIO, loa_arg)
									);
								}
							}
						}

					case com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.PREDIO_SEGREGADO:

						Collection<PredioSegregado> lcps_cllPredioSegregado;
						lcps_cllPredioSegregado = lcr_request.getCllPredioSegregado();

						if(CollectionUtils.isValidCollection(lcps_cllPredioSegregado))
						{
							if(CollectionUtils.isValidCollection(lss_circulosConvenio))
							{
								Collection<PredioSegregado> lcps_cllFiltrada;
								Set<String>                 lss_convenioCirculos;

								lcps_cllFiltrada         = new ArrayList<PredioSegregado>();
								lss_convenioCirculos     = new HashSet<String>();

								for(PredioSegregado lps_tmp : lcps_cllPredioSegregado)
								{
									if(lps_tmp != null)
									{
										String ls_idCirculo;

										ls_idCirculo = lps_tmp.getIdCirculo();

										if(StringUtils.isValidString(ls_idCirculo))
										{
											if(
											    lss_circulosConvenio.contains(ls_idCirculo)
												    ? lcps_cllFiltrada.add(lps_tmp)
												        : lss_convenioCirculos.add(ls_idCirculo)
											)
												;
										}
									}
								}

								{
									StringBuilder lsb_circulosConcatenados;
									String        ls_circulos;
									int           li_count;

									lsb_circulosConcatenados     = new StringBuilder();
									li_count                     = 0;

									if(CollectionUtils.isValidCollection(lss_convenioCirculos))
									{
										for(String ls_tmp : lss_convenioCirculos)
										{
											if(StringUtils.isValidString(ls_tmp))
											{
												if(li_count == 0)
												{
													lsb_circulosConcatenados.append(ls_tmp);

													li_count++;
												}
												else
													lsb_circulosConcatenados.append(
													    IdentificadoresCommon.SIMBOLO_COMA + ls_tmp
													);
											}
										}
									}

									if(!lcps_cllFiltrada.isEmpty())
										lcps_response.setCllPredioSegregado(lcps_cllFiltrada);

									ls_circulos = lsb_circulosConcatenados.toString();

									if(StringUtils.isValidString(ls_circulos))
									{
										Object[] loa_arg;

										loa_arg        = new String[1];
										loa_arg[0]     = ls_circulos;

										lcps_response.setMensajeConvenioCirculos(
										    addMessage(MessagesKeys.ORIPS_CONVENIO, loa_arg)
										);
									}
								}
							}
							else
								lcps_response.setCllPredioSegregado(lcps_cllPredioSegregado);
						}

					case com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.PREDIO_REGISTRO:

						Collection<PredioRegistro> lcpr_cllPredioRegistro;
						lcpr_cllPredioRegistro = lcr_request.getCllPredioRegistro();

						if(CollectionUtils.isValidCollection(lcpr_cllPredioRegistro))
						{
							if(CollectionUtils.isValidCollection(lss_circulosConvenio))
							{
								Collection<PredioRegistro> lcpr_cllFiltrada;
								Set<String>                lss_convenioCirculos;

								lcpr_cllFiltrada         = new ArrayList<PredioRegistro>();
								lss_convenioCirculos     = new HashSet<String>();

								for(PredioRegistro lpr_tmp : lcpr_cllPredioRegistro)
								{
									if(lpr_tmp != null)
									{
										String ls_idCirculo;

										ls_idCirculo = lpr_tmp.getIdCirculo();

										if(StringUtils.isValidString(ls_idCirculo))
										{
											if(
											    lss_circulosConvenio.contains(ls_idCirculo)
												    ? lcpr_cllFiltrada.add(lpr_tmp)
												        : lss_convenioCirculos.add(ls_idCirculo)
											)
												;
										}
									}
								}

								{
									StringBuilder lsb_circulosConcatenados;
									String        ls_circulos;
									int           li_count;

									lsb_circulosConcatenados     = new StringBuilder();
									li_count                     = 0;

									if(CollectionUtils.isValidCollection(lss_convenioCirculos))
									{
										for(String ls_tmp : lss_convenioCirculos)
										{
											if(StringUtils.isValidString(ls_tmp))
											{
												if(li_count == 0)
												{
													lsb_circulosConcatenados.append(ls_tmp);

													li_count++;
												}
												else
													lsb_circulosConcatenados.append(
													    IdentificadoresCommon.SIMBOLO_COMA + ls_tmp
													);
											}
										}
									}

									if(!lcpr_cllFiltrada.isEmpty())
										lcps_response.setCllPredioRegistro(lcpr_cllFiltrada);

									ls_circulos = lsb_circulosConcatenados.toString();

									if(StringUtils.isValidString(ls_circulos))
									{
										Object[] loa_arg;

										loa_arg        = new String[1];
										loa_arg[0]     = ls_circulos;

										lcps_response.setMensajeConvenioCirculos(
										    addMessage(MessagesKeys.ORIPS_CONVENIO, loa_arg)
										);
									}
								}
							}
							else
								lcps_response.setCllPredioRegistro(lcpr_cllPredioRegistro);
						}

					case com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.PROPIETARIO:

						Collection<Propietario> lcp_cllPropietario;
						lcp_cllPropietario = lcr_request.getCllPropietario();

						if(CollectionUtils.isValidCollection(lcp_cllPropietario))
						{
							if(CollectionUtils.isValidCollection(lss_circulosConvenio))
							{
								Collection<Propietario> lcp_cllFiltrada;
								Set<String>             lss_convenioCirculos;

								lcp_cllFiltrada          = new ArrayList<Propietario>();
								lss_convenioCirculos     = new HashSet<String>();

								for(Propietario lp_tmp : lcp_cllPropietario)
								{
									if(lp_tmp != null)
									{
										String ls_idCirculo;

										ls_idCirculo = lp_tmp.getIdCirculo();

										if(StringUtils.isValidString(ls_idCirculo))
										{
											if(
											    lss_circulosConvenio.contains(ls_idCirculo)
												    ? lcp_cllFiltrada.add(lp_tmp) : lss_convenioCirculos.add(
												        ls_idCirculo
												    )
											)
												;
										}
									}
								}

								{
									StringBuilder lsb_circulosConcatenados;
									String        ls_circulos;
									int           li_count;

									lsb_circulosConcatenados     = new StringBuilder();
									li_count                     = 0;

									if(CollectionUtils.isValidCollection(lss_convenioCirculos))
									{
										for(String ls_tmp : lss_convenioCirculos)
										{
											if(StringUtils.isValidString(ls_tmp))
											{
												if(li_count == 0)
												{
													lsb_circulosConcatenados.append(ls_tmp);

													li_count++;
												}
												else
													lsb_circulosConcatenados.append(
													    IdentificadoresCommon.SIMBOLO_COMA + ls_tmp
													);
											}
										}
									}

									if(!lcp_cllFiltrada.isEmpty())
										lcps_response.setCllPropietario(lcp_cllFiltrada);

									ls_circulos = lsb_circulosConcatenados.toString();

									if(StringUtils.isValidString(ls_circulos))
									{
										Object[] loa_arg;

										loa_arg        = new String[1];
										loa_arg[0]     = ls_circulos;

										lcps_response.setMensajeConvenioCirculos(
										    addMessage(MessagesKeys.ORIPS_CONVENIO, loa_arg)
										);
									}
								}
							}
							else
								lcps_response.setCllPropietario(lcp_cllPropietario);
						}

					default:
						break;
				}
			}
		}

		return lcps_response;
	}
}
