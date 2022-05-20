package com.bachue.snr.prosnr01.business.consulta.solicitud;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.bgn.DocumentoDAO;
import com.bachue.snr.prosnr01.dao.consulta.solicitud.ConsultaSolicitudDAO;

import com.bachue.snr.prosnr01.model.consulta.solicitud.Persona;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Predio;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Segregacion;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Solicitud;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Tramite;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaSolicitud;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * Clase paa el manejo del negocio de consulta de la solicitud.
 *
 * @author Sebastian Castaño
 */
public class ConsultaSolicitudBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaSolicitudBusiness.class);

	/**
	 * Método para encontrar de la tabla SDB_ACC_ACTO todos los registros que coincidadn con un ID_SOLICITUD específico.
	 *
	 * @param as_idSolicitud es el id_solicitud para realizar la consulta en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<com.bachue.snr.prosnr01.model.registro.Acto> findByIdSolicitud(
	    String as_idSolicitud
	)
	    throws B2BException
	{
		DAOManager                                              ldm_manager;
		ConsultaSolicitudDAO                                    lcd_DAO;
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lca_datos       = null;
		lcd_DAO         = DaoCreator.getConsultaSolicitudDAO(ldm_manager);

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
				lca_datos = lcd_DAO.findByIdSolicitud(as_idSolicitud);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByIdSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lca_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar los datos del tramite en la tabla SDB_ACC_SOLICITUD a partir de un id_solicitud
	 * específico.
	 *
	 * @param as_parametros id_solicitud para realizar la consulta en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Tramite> findDatosDelTramiteByidSolicitud(String as_parametros)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<Tramite> lct_tramites;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lct_tramites     = null;

		try
		{
			if(as_parametros != null)
			{
				lct_tramites = new ArrayList<Tramite>();

				ConsultaSolicitudDAO lcd_DAO;
				lcd_DAO          = DaoCreator.getConsultaSolicitudDAO(ldm_manager);
				lct_tramites     = lcd_DAO.findDatosDelTramiteByidSolicitud(as_parametros);

				if(lct_tramites == null)
					lct_tramites = lcd_DAO.findDatosDelTramiteByidSolicitudSinProcedencia(as_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDatosDelTramiteByidSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lct_tramites;
	}

	/**
	 * Método de transacciones con la base de datos con el fin de encontrar el documento a partir de un id solicitud específico.
	 *
	 * @param as_idSolicitud id solicitud para la consulta en diferentes tablas para traer el documento
	 * @return devuelve el valor de Documento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Documento
	 */
	public synchronized Documento findDocumentoByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		DocumentoDAO ldd_DAO;
		Documento    lca_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lca_datos       = null;
		ldd_DAO         = DaoCreator.getDocumentoDAO(ldm_manager);

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				lca_datos = ldd_DAO.findDocumentoByIdSolicitud(as_idSolicitud);

				if(lca_datos != null)
				{
					TipoDocumentoPublico ltdp_tmp;
					ltdp_tmp = new TipoDocumentoPublico();

					ltdp_tmp.setIdTipoDocumento(lca_datos.getIdTipoDocumento());
					ltdp_tmp = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager).findById(ltdp_tmp);

					if(ltdp_tmp != null)
						lca_datos.setNombreTipoDocumento(ltdp_tmp.getNombre());

					OficinaOrigen loo_tmp;
					loo_tmp = new OficinaOrigen();
					loo_tmp.setIdOficinaOrigen(lca_datos.getIdOficinaOrigen());
					loo_tmp.setVersion(lca_datos.getVersion());

					loo_tmp = DaoCreator.getOficinaOrigenDAO(ldm_manager).findById(loo_tmp);

					if(loo_tmp != null)
					{
						lca_datos.setNombreOficinaOrigen(loo_tmp.getNombre());

						Departamento ld_tmp;
						ld_tmp = new Departamento();
						ld_tmp.setIdPais(loo_tmp.getIdPais());
						ld_tmp.setIdDepartamento(loo_tmp.getIdDepartamento());
						ld_tmp = DaoCreator.getDepartamentoDAO(ldm_manager).findById(ld_tmp);

						if(ld_tmp != null)
							lca_datos.setNombreDepartamento(ld_tmp.getNombre());

						Municipio lm_tmp;
						lm_tmp = new Municipio();
						lm_tmp.setIdPais(loo_tmp.getIdPais());
						lm_tmp.setIdDepartamento(loo_tmp.getIdDepartamento());
						lm_tmp.setIdMunicipio(loo_tmp.getIdMunicipio());
						lm_tmp = DaoCreator.getMunicipioDAO(ldm_manager).findById(lm_tmp);

						if(lm_tmp != null)
							lca_datos.setNombreMunicipio(lm_tmp.getNombre());
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDocumentoByIdSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lca_datos;
	}

	/**
	 * Método para el manejo de transacciones con la base de datos para encontrar al interesado de la solicitud.
	 *
	 * @param as_parametros objeto de tipo solicitud para encontrar la persona interesada en la solicitud
	 * @param as_b indica si se consulta en la tabla SDB_ACC_SOLICITUD_INTERVINIENTE ó SDB_ACC_SOLICITUD
	 * @return devuelve el valor de Solicitud
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Solicitud
	 */
	public synchronized Solicitud findInteresados(String as_parametros, boolean as_b)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Solicitud  ls_dato;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_dato         = null;

		try
		{
			if(as_parametros != null)
			{
				Collection<Persona>  lcp_personas;
				ConsultaSolicitudDAO lcd_DAO;

				lcd_DAO          = DaoCreator.getConsultaSolicitudDAO(ldm_manager);
				lcp_personas     = lcd_DAO.findPersonaBySolicitud(as_parametros, as_b);

				if(CollectionUtils.isValidCollection(lcp_personas))
				{
					ls_dato = new Solicitud();

					for(Persona lp_iterador : lcp_personas)
					{
						String ls_idPersona;
						String ls_idDireccion;
						String ls_idCorreo;
						String ls_idDireccionComunicacion;
						String ls_idCorreoComuniciacion;
						String ls_medioNotificar;
						String ls_medioComunicar;

						ls_idPersona                   = lp_iterador.getIdPersona();
						ls_idDireccion                 = lp_iterador.getIdDireccion();
						ls_idCorreo                    = lp_iterador.getIdCorreo();
						ls_idDireccionComunicacion     = lp_iterador.getIdDireccionComunicacion();
						ls_idCorreoComuniciacion       = lp_iterador.getIdCorreoComunicacion();
						ls_medioNotificar              = lp_iterador.getTipoMedioNotificacion();
						ls_medioComunicar              = lp_iterador.getTipoMedioComunicacion();

						if(StringUtils.isValidString(ls_idPersona))
						{
							if(StringUtils.isValidString(ls_medioNotificar))
							{
								if(
								    ls_medioNotificar.equalsIgnoreCase(MedioNotificarCommon.DIRECCION_RESIDENCIA)
									    || ls_medioNotificar.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									    )
								)
								{
									String ls_direcion;
									ls_direcion = lcd_DAO.findDireccionByPersonaAndIdDireccion(
										    ls_idPersona, ls_idDireccion
										);
									lp_iterador.setMedioNotificacion(ls_direcion);
								}
								else if(ls_medioNotificar.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
								{
									String ls_correo;
									ls_correo = lcd_DAO.findCorroByPersonaAndIdCorreo(ls_idPersona, ls_idCorreo);

									if(StringUtils.isValidString(ls_correo))
										lp_iterador.setMedioNotificacion(ls_correo);
								}
								else
								{
									String ls_nombre;
									ls_nombre = lcd_DAO.findByTipo(ls_medioNotificar);
									lp_iterador.setMedioNotificacion(ls_nombre);
								}
							}

							if(StringUtils.isValidString(ls_medioComunicar))
							{
								if(
								    ls_medioComunicar.equalsIgnoreCase(MedioNotificarCommon.DIRECCION_RESIDENCIA)
									    || ls_medioComunicar.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									    )
								)
								{
									String ls_direcion;
									ls_direcion = lcd_DAO.findDireccionByPersonaAndIdDireccion(
										    ls_idPersona, ls_idDireccionComunicacion
										);
									lp_iterador.setMedioComunicacion(ls_direcion);
								}
								else if(ls_medioComunicar.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
								{
									String ls_correo;
									ls_correo = lcd_DAO.findCorroByPersonaAndIdCorreo(
										    ls_idPersona, ls_idCorreoComuniciacion
										);

									if(StringUtils.isValidString(ls_correo))
										lp_iterador.setMedioComunicacion(ls_correo);
								}
								else if(ls_medioComunicar.equalsIgnoreCase(MedioNotificarCommon.SMS))
								{
									String ls_telefono;
									ls_telefono = lcd_DAO.findTelefonoByidPersona(ls_idPersona);
									lp_iterador.setMedioComunicacion(ls_telefono);
								}

								else
								{
									String ls_nombre;
									ls_nombre = lcd_DAO.findByTipo(ls_medioComunicar);
									lp_iterador.setMedioComunicacion(ls_nombre);
								}
							}
						}
					}

					ls_dato.setListadoPersona(lcp_personas);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findInteresados", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_dato;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_ACC_PERSONA que coincida con un
	 * ID_SOLICITUD específico.
	 *
	 * @param as_parametros objeto de donde se extrae el id para realizar la consulta
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Predio> findMatriculaBySolicitud(String as_parametros)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		Collection<Predio> lcp_predios;

		lcp_predios     = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(as_parametros != null)
			{
				lcp_predios = new ArrayList<Predio>();

				ConsultaSolicitudDAO lcd_DAO;
				lcd_DAO         = DaoCreator.getConsultaSolicitudDAO(ldm_manager);
				lcp_predios     = lcd_DAO.findMatriculaBySolicitud(as_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMatriculaBySolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcp_predios;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros que coincidan con un ID_SOLICITUD especifico
	 * de la tabla SDB_ACC_PERSONA.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<PersonaSolicitud> findPersonasByIdSolicitud(String as_s)
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<PersonaSolicitud> lcps_final;
		Collection<PersonaSolicitud> lcps_datos;
		Collection<PersonaSolicitud> lcps_datosPersonasAll;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lcps_final                = null;
		lcps_datos                = null;
		lcps_datosPersonasAll     = null;

		if(StringUtils.isValidString(as_s))
		{
			try
			{
				PersonaDAO lp_DAO;

				lp_DAO         = DaoCreator.getPersonaDAO(ldm_manager);
				lcps_datos     = lp_DAO.findPersonasByIdSolicitud(as_s);

				if(CollectionUtils.isValidCollection(lcps_datos))
				{
					Iterator<PersonaSolicitud> lips_iterator;

					lips_iterator = lcps_datos.iterator();

					while(lips_iterator.hasNext())
					{
						PersonaSolicitud lps_actual;

						lps_actual = lips_iterator.next();

						if(lps_actual != null)
						{
							String ls_nombreCalidad;

							ls_nombreCalidad = lps_actual.getNombreCalidad();

							if(
							    StringUtils.isValidString(ls_nombreCalidad)
								    && ls_nombreCalidad.equalsIgnoreCase(IdentificadoresCommon.APODERADO)
							)
							{
								String        ls_idPersonaEntrega;
								StringBuilder lsb_nombreCompleto;

								ls_idPersonaEntrega     = lps_actual.getIdPersonaEntrega();
								lsb_nombreCompleto      = new StringBuilder();

								if(StringUtils.isValidString(ls_idPersonaEntrega))
								{
									Collection<String> lcs_nombres;

									lcs_nombres = lp_DAO.findNombrePersonasTerceroByIdPersona(lps_actual);

									if(CollectionUtils.isValidCollection(lcs_nombres))
									{
										Iterator<String> lis_iterator;
										int              li_count;
										int              li_tam;

										lis_iterator     = lcs_nombres.iterator();
										li_count         = 1;
										li_tam           = lcs_nombres.size();

										while(lis_iterator.hasNext())
										{
											String ls_nombreActual;
											String ls_separador;

											ls_separador        = ((li_tam == 1) || (li_count == li_tam)) ? ""
												                                                          : IdentificadoresCommon.SIMBOLO_COMA;
											ls_nombreActual     = lis_iterator.next();

											if(StringUtils.isValidString(ls_nombreActual))
												lsb_nombreCompleto.append(ls_nombreActual + ls_separador);

											li_count++;
										}
									}
								}

								if(StringUtils.isValidString(lsb_nombreCompleto.toString()))
									lps_actual.setIdPersonaTercero(lsb_nombreCompleto.toString());
							}
						}
					}

					lcps_final = lcps_datos;
				}
				else
				{
					lcps_datosPersonasAll = lp_DAO.findPersonasAllByIdSolicitud(as_s);

					if(CollectionUtils.isValidCollection(lcps_datosPersonasAll))
					{
						for(PersonaSolicitud lips_actuali : lcps_datosPersonasAll)
						{
							if(lips_actuali != null)
							{
								String ls_nombreCalidadi;

								ls_nombreCalidadi = lips_actuali.getNombreCalidad();

								if(
								    (StringUtils.isValidString(ls_nombreCalidadi)
									    && ls_nombreCalidadi.equalsIgnoreCase(IdentificadoresCommon.INTERVINIENTE))
									    || (StringUtils.isValidString(ls_nombreCalidadi)
									    && ls_nombreCalidadi.equalsIgnoreCase(IdentificadoresCommon.TERCERO))
								)
									lips_actuali.setIdPersonaTercero(null);
							}
						}

						lcps_final = lcps_datosPersonasAll;
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findPersonaByIdSolicitud", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcps_final;
	}

	/**
	 * Método para encontrar la segregacion en la tabla SDB_ACC_MATRICULA_SEGREGACION que coindida con un ID_SOLICITUD específico.
	 *
	 * @param as_parametros es el id solicitud para realizar la busqueda
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Segregacion> findSegregacion(String as_parametros)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<Segregacion> lcs_segregacion;

		ldm_manager         = DaoManagerFactory.getDAOManager();
		lcs_segregacion     = null;

		try
		{
			if(as_parametros != null)
			{
				lcs_segregacion = new ArrayList<Segregacion>();

				ConsultaSolicitudDAO lcd_DAO;
				lcd_DAO             = DaoCreator.getConsultaSolicitudDAO(ldm_manager);
				lcs_segregacion     = lcd_DAO.findSegregacion(as_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSegregacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcs_segregacion;
	}
}
