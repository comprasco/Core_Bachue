package com.bachue.snr.prosnr01.business.reference;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.integracion.cliente.bioclient.consultarSesion.ClienteConsultarSesion;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.ComponentesCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.SubProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoActoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoCriterioBusquedaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRecepcionCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccCompletitudDocumentalDAO;
import com.bachue.snr.prosnr01.dao.acc.AccEntidadExternaPersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.DesbordeDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaActoDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoActoDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoDocumentalActoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.aut.ComponentesDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO;
import com.bachue.snr.prosnr01.dao.pgn.DepartamentoDAO;
import com.bachue.snr.prosnr01.dao.pgn.MunicipioDAO;
import com.bachue.snr.prosnr01.dao.pgn.PaisDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentoPublicoDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoEntidadDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoOficinaDAO;
import com.bachue.snr.prosnr01.dao.png.EstadoActividadDAO;
import com.bachue.snr.prosnr01.dao.png.NaturalezaJuridicaDAO;

import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.objectDataBase.UserObjects;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExternaPersona;
import com.bachue.snr.prosnr01.model.sdb.acc.BitacoraBloqueo;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.CausalAprobacionDevolucion;
import com.bachue.snr.prosnr01.model.sdb.acc.Desborde;
import com.bachue.snr.prosnr01.model.sdb.acc.NotaDevolutiva;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.SubprocesoVersion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoAdquisicion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoEstadoSolicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoRecepcion;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoTarifaRegistral;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Componente;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioRol;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoNaturalGenero;
import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;
import com.bachue.snr.prosnr01.model.sdb.col.TipoRrr;
import com.bachue.snr.prosnr01.model.sdb.col.TipoUsoSuelo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalNegacionCopias;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Consultas;
import com.bachue.snr.prosnr01.model.sdb.pgn.CriteriosDeBusqueda;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.DependenciaSNR;
import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoActividad;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.MedidaArea;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.ProcesoAutomatico;
import com.bachue.snr.prosnr01.model.sdb.pgn.ProcesoConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.Regional;
import com.bachue.snr.prosnr01.model.sdb.pgn.ResultadoConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoPersona;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoRequiereMatricula;
import com.bachue.snr.prosnr01.model.sdb.pgn.UnidadTiempoVencimiento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.png.Coordenada;
import com.bachue.snr.prosnr01.model.sdb.png.DominioNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoAnotacion;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.png.GrupoNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.LetraEje;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.TipoAnotacion;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;
import com.bachue.snr.prosnr01.model.ui.FechaVenceTerminosUI;

import com.bachue.snr.prosnr16.model.sdb.pgn.Plantilla;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Clase para el manejo del negocio de las referencias.
 *
 * @author Heiner Castañeda
 */
public class ReferenceBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ReferenceBusiness.class);

	/**
	 * Retorna el valor del objeto de Collection de los campos de la consulta.
	 *
	 * @param acc_cc correspondiente al valor del tipo de objeto CamposConsulta
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<CamposConsulta> buscarCamposPorCriterio(CamposConsulta acc_cc)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<CamposConsulta> lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getCampoCriterioBusquedaDAO(ldm_manager).buscarCamposPorCriterio(acc_cc);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarCamposPorCriterio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<Constantes> buscarMotivosConsulta()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Constantes> lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getConstantesDAO(ldm_manager).buscarMotivosConsulta();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarMotivosConsulta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Metodo de transacciones con la base de datos para encontrar todos los circulos registrales origen destino que se encuentren activos.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @param as_idCirculoOrigen correspondiente al valor de as id circulo origen
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<CirculoRegistral> buscarTodosCirculosRegistralesOrigenDestinoActivos(
	    boolean ab_b, String as_idCirculoOrigen
	)
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<CirculoRegistral> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getCirculoRegistralDAO(ldm_manager)
					                  .buscarTodosCirculosRegistralesOrigenDestinoActivos(ab_b, as_idCirculoOrigen);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarTodosCirculosRegistralesOrigenDestinoActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Metodo encargado de consultar todos los municipios de un pais.
	 *
	 * @param am_parametros Objeto que contiene el pais y el estado activo que se desea consultar.
	 * @return Colección de Municipios que cumplen con los criterios de busqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Municipio> buscarTodosMunicipiosPorPais(Municipio am_parametros)
	    throws B2BException
	{
		Collection<Municipio> lcm_datos;

		lcm_datos = null;

		if(am_parametros != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				{
					String ls_idPais;

					ls_idPais = am_parametros.getIdPais();

					if(!StringUtils.isValidString(ls_idPais))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
				}

				{
					String ls_activo;

					ls_activo = am_parametros.getActivo();

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				lcm_datos = DaoCreator.getMunicipioDAO(ldm_manager).findAllByCountry(am_parametros);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("buscarTodosMunicipiosPorPais", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcm_datos;
	}

	/**
	 * Método encargado de cargar las anotaciones para la matricula a agregar.
	 *
	 * @param aps_predioAgregar Objeto que contiene la información del predio.
	 * @param ab_conBaseEn de ab con base en
	 * @return Objeto que contiene la información del predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado cargarAnotacionesMatriculaAgregar(
	    com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado aps_predioAgregar, boolean ab_conBaseEn
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aps_predioAgregar != null)
			{
				long           ll_idMatricula;
				PredioRegistro lpr_predio;
				String         ls_idCirculo;

				ll_idMatricula     = ab_conBaseEn ? NumericUtils.getLong(aps_predioAgregar.getIdMatricula())
					                              : NumericUtils.getLong(aps_predioAgregar.getIdMatricula1());
				ls_idCirculo       = ab_conBaseEn ? aps_predioAgregar.getIdCirculo() : aps_predioAgregar.getIdCirculo1();
				lpr_predio         = DaoCreator.getPredioRegistroDAO(ldm_manager)
						                           .findByCirculoMatricula(ls_idCirculo, ll_idMatricula);

				if(lpr_predio != null)
				{
					AnotacionPredioDAO lap_DAO;

					lap_DAO = DaoCreator.getAnotacionPredioDAO(ldm_manager);

					aps_predioAgregar.setAnotaciones(
					    lap_DAO.findAnotacionesPredio(
					        aps_predioAgregar.getIdCirculo(), aps_predioAgregar.getIdMatricula()
					    )
					);

					aps_predioAgregar.setAnotacionesSegregadas(
					    lap_DAO.findAnotacionesPredio(
					        aps_predioAgregar.getIdCirculo1(), aps_predioAgregar.getIdMatricula1()
					    )
					);
				}
				else
				{
					Object[] loa_arg;

					loa_arg        = new String[1];
					loa_arg[0]     = ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION + ll_idMatricula;

					throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_NO_ENCONTRADA, loa_arg);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarAnotacionesMatriculaAgregar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return aps_predioAgregar;
	}

	/**
	 * Busca las opciones disponibles del menú para el usuario que inicia sesión.
	 *
	 * @param as_userId Id del usuario a utilizar como filtro en la busqueda
	 * @return Colección de opciones resultante de la busqueda
	 * @throws B2BException Si ocurre un error en base de datos o no se encuentran opciones para el usuario
	 */
	public synchronized List<Componente> cargarComponentesMenu(String as_userId)
	    throws B2BException
	{
		Collection<Componente> lcc_componente;
		DAOManager             ldm_manager;

		ldm_manager        = DaoManagerFactory.getDAOManager();
		lcc_componente     = null;

		try
		{
			ComponentesDAO lc_dao;

			lc_dao = DaoCreator.getComponentesDAO(ldm_manager);

			if(!StringUtils.isValidString(as_userId))
				throw new B2BException(ErrorKeys.USUARIO_INVALIDO);

			{
				Collection<UsuarioRol> lcur_rolesUsuario;

				lcur_rolesUsuario = DaoCreator.getUsuarioRolDAO(ldm_manager).findByIdUsuario(as_userId);

				if(!CollectionUtils.isValidCollection(lcur_rolesUsuario))
					throw new B2BException(ErrorKeys.ERROR_USUARIO_SIN_ROLES);
			}

			lcc_componente = lc_dao.findAllActivo();

			if(CollectionUtils.isValidCollection(lcc_componente))
			{
				Map<String, Componente> lcc_componenteUsuario;

				lcc_componenteUsuario = lc_dao.findAllComponenteUsuario(as_userId);

				if(!CollectionUtils.isValidCollection(lcc_componenteUsuario))
					throw new B2BException(ErrorKeys.ERROR_USUARIO_SIN_OPCIONES);

				for(Componente lco_opcionesC : lcc_componente)
				{
					if(lco_opcionesC != null)
					{
						String ls_idComponente;
						ls_idComponente = lco_opcionesC.getIdComponente();

						if(lco_opcionesC.getImagen() == null)
						{
							Componente lci_imagenCoreBachue;
							lci_imagenCoreBachue = lc_dao.findByIdWithImage(
								    ComponentesCommon.CORE_BACHUE_COMPONENTE, true
								);

							if(lci_imagenCoreBachue != null)
								lco_opcionesC.setImagen(lci_imagenCoreBachue.getImagen());
						}

						if(lcc_componenteUsuario.containsKey(ls_idComponente) && !lco_opcionesC.isEsParte())
							lco_opcionesC.setEsParte(true);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarOpcionesMenu", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return new ArrayList<Componente>(lcc_componente);
	}

	/**
	 * Retorna el valor del objeto de Collection de los campos de la consulta.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Map<String, Map<String, String>> cargarDataDireccion()
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		Map<String, Map<String, String>> lmsmss_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lmsmss_data     = new HashMap<String, Map<String, String>>();

		try
		{
			{
				Map<String, String> lmss_data;

				lmss_data = DaoCreator.getTipoEjeDAO(ldm_manager).findAllActivoMap();

				if(CollectionUtils.isValidCollection(lmss_data))
					lmsmss_data.put(IdentificadoresCommon.EJE, lmss_data);
			}

			{
				Map<String, String> lmss_data;

				lmss_data = DaoCreator.getLetraEjeDAO(ldm_manager).findAllActivoMap();

				if(CollectionUtils.isValidCollection(lmss_data))
					lmsmss_data.put(IdentificadoresCommon.LETRA, lmss_data);
			}

			{
				Map<String, String> lmss_data;

				lmss_data = DaoCreator.getCoordenadaDAO(ldm_manager).findAllActivoMap();

				if(CollectionUtils.isValidCollection(lmss_data))
					lmsmss_data.put(IdentificadoresCommon.COORDENADA, lmss_data);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarCamposPorCriterio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lmsmss_data;
	}

	/**
	 * Busca las opciones disponibles del menú para el usuario que inicia sesión.
	 *
	 * @param as_userId Id del usuario a utilizar como filtro en la busqueda
	 * @param as_idComponente Id del componente a utilizar como filtro en la busqueda
	 * @return Colección de opciones resultante de la busqueda
	 * @throws B2BException Si ocurre un error en base de datos o no se encuentran opciones para el usuario
	 */
	public synchronized Collection<Opcion> cargarOpcionesMenu(String as_userId, String as_idComponente)
	    throws B2BException
	{
		Collection<Opcion> lco_opciones;
		DAOManager         ldm_manager;

		lco_opciones     = null;
		ldm_manager      = DaoManagerFactory.getDAOManager();

		try
		{
			if(!StringUtils.isValidString(as_userId))
				throw new B2BException(ErrorKeys.USUARIO_INVALIDO);

			{
				Collection<UsuarioRol> lcur_rolesUsuario;

				lcur_rolesUsuario = DaoCreator.getUsuarioRolDAO(ldm_manager).findByIdUsuario(as_userId);

				if(!CollectionUtils.isValidCollection(lcur_rolesUsuario))
					throw new B2BException(ErrorKeys.ERROR_USUARIO_SIN_ROLES);
			}

			lco_opciones = DaoCreator.getOpcionDAO(ldm_manager).findAllByUsuario(as_userId, as_idComponente);

			if(!CollectionUtils.isValidCollection(lco_opciones))
				throw new B2BException(ErrorKeys.ERROR_USUARIO_SIN_OPCIONES);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarOpcionesMenu", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lco_opciones;
	}

	/**
	 * Método de transacciones con la base de datos para traer todos los registros de la tabla SDB_PGN_REPORTES
	 * donde su estado se encuentre en 'S'.
	 *
	 * @param ab_orderById Objeto de tipo boolean con el cúal se realizará la consulta
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Consultas> cargarTipoConsulta(boolean ab_orderById)
	    throws B2BException
	{
		DAOManager            ldm_manager;
		Collection<Consultas> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getConsultasReportesDAO(ldm_manager).findAllActive(ab_orderById);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarTipoConsulta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Método para consultar SDB_ACC_BITACORA_BLOQUEO por círculo y matrícula.
	 *
	 * @param apr_pr Objeto de tipo PredioRegistro con el cúal se realizará la consulta
	 * @return Collection<String> llena con los datos de la BD
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<String> findActivoByCirculoMatricula(PredioRegistro apr_pr)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		Collection<String> lcs_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcs_datos       = null;

		try
		{
			if(apr_pr != null)
				lcs_datos = DaoCreator.getBitacoraBloqueoDAO(ldm_manager)
						                  .findTurnoBloqueoRegistroByCirculoMatricula(apr_pr);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findActivoByCirculoMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcs_datos;
	}

	/**
	 * Find all activo by usuario.
	 *
	 * @param as_userId de as user id
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<CirculoRegistral> findAllActivoByUsuario(String as_userId)
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<CirculoRegistral> lccr_cllCirculoRegistral;

		ldm_manager                  = DaoManagerFactory.getDAOManager();
		lccr_cllCirculoRegistral     = null;

		try
		{
			if(StringUtils.isValidString(as_userId))
				lccr_cllCirculoRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager)
						                                 .findAllActivoByUsuario(as_userId);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllActivoByUsuario", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccr_cllCirculoRegistral;
	}

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de la tabla SDB_PGN_CAUSAL_NEGACION_COPIAS.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CausalNegacionCopias> findAllCausalNegacionCopias()
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		Collection<CausalNegacionCopias> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getCausalNegacionCopiasDAO(ldm_manager).findAll();
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCausalNegacionCopias", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Metodo de transacciones con la base de datos para encontrar todos los circulos registrales que se encuentren activos.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CirculoRegistral> findAllCirculosRegistralesActivos(boolean ab_b)
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<CirculoRegistral> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getCirculoRegistralDAO(ldm_manager).findAllActivo(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCirculosRegistralesActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Consulta todos los círculos registrales que estén activos en la base de datos, filtrando
	 * por un id solicitud especificado con anterioridad.
	 *
	 * @param as_idSolicitud utilizado para filtrar la búsqueda
	 * @return Colección de círculos registrales resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CirculoRegistral> findAllCirculosRegistralesActivosByIdSolicitud(
	    String as_idSolicitud
	)
	    throws B2BException
	{
		Collection<CirculoRegistral> lccr_return;
		DAOManager                   ldm_manager;

		lccr_return     = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
				lccr_return = DaoCreator.getCirculoRegistralDAO(ldm_manager).findByIdSolicitudActo(as_idSolicitud);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCirculosRegistralesActivosByIdSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccr_return;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros SDB_PGN_CIRCULO_REGISTRAL
	 * que coincidan con un ID_REGIONAL específico.
	 *
	 * @param acr_param objeto del cual se extrae el ID_REGIONAL para realizar la consulta en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CirculoRegistral> findAllCirculosRegistralesByRegional(CirculoRegistral acr_param)
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<CirculoRegistral> lcr_return;

		lcr_return      = new LinkedList<CirculoRegistral>();
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(acr_param != null)
			{
				Collection<CirculoRegistral> lcr_datos;

				lcr_datos     = null;

				lcr_datos = DaoCreator.getCirculoRegistralDAO(ldm_manager).findByIdRegional(acr_param);

				if(lcr_datos != null)
				{
					DesbordeDAO ld_DAO;
					Desborde    ld_desborde;

					ld_DAO = DaoCreator.getDesbordeDAO(ldm_manager);

					for(CirculoRegistral lcr_circulo : lcr_datos)
					{
						ld_desborde = new Desborde();

						ld_desborde.setIdCirculoOrigen(acr_param.getIdCirculo());
						ld_desborde.setIdCirculoDesborde(lcr_circulo.getIdCirculo());

						ld_desborde = ld_DAO.findById(ld_desborde);

						if(ld_desborde != null)
						{
							lcr_circulo.setHabilitado(ld_desborde.getHabilitada());
							lcr_circulo.setObservaciones(ld_desborde.getObservaciones());
						}

						lcr_return.add(lcr_circulo);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCirculosRegistralesByRegional", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_return;
	}

	/**
	 * Consulta todos los tipos eje que tengan un estado activo, tengan complemento en S, sean de tipo predio Mixto y tipo predio definido por el usuario.
	 *
	 * @param as_idTipoPredio Variable de tipo String que contiene el id tipo predio.
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TipoEje> findAllComplementoDireccionByTipoPredio(String as_idTipoPredio)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<TipoEje> lcte_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcte_datos      = null;

		try
		{
			if(!StringUtils.isValidString(as_idTipoPredio))
				lcte_datos = DaoCreator.getTipoEjeDAO(ldm_manager).findAllActivoMixto(true);
			else
				lcte_datos = DaoCreator.getTipoEjeDAO(ldm_manager).findAllByTipoPredio(as_idTipoPredio, true);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllComplementoDireccionByTipoPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcte_datos;
	}

	/**
	 * Metodo de transacciones con la base de datos para encontrar todos las constantes que se encuentren activos.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Constantes> findAllConstantesActivos(boolean ab_b)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Constantes> lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getConstantesDAO(ldm_manager).findAllActivo(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllConstantesActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Find all dependencia by ind visitas.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<DependenciaSNR> findAllDependenciaByIndVisitas()
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<DependenciaSNR> lcd_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcd_datos       = null;

		try
		{
			lcd_datos = DaoCreator.getDependenciaSNRDAO(ldm_manager).findAllByIndVisitas();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllDependenciaByIndVisitas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcd_datos;
	}

	/**
	 * Metodo de transacciones con la base de datos para encontrar todos los dominios naturaleza juridica que se encuentren activos.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<DominioNaturalezaJuridica> findAllDominioNaturalezaJuridicaActivos(boolean ab_b)
	    throws B2BException
	{
		DAOManager                            ldm_manager;
		Collection<DominioNaturalezaJuridica> ldn_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldn_datos       = null;

		try
		{
			ldn_datos = DaoCreator.getDominioNaturalezaJuridicaDAO(ldm_manager).findAllActivo(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllDominioNaturalezaJuridicaActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldn_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla
	 * SDB_ACC_ENTIDAD_EXTERNA cuando la columna ACTIVO se encuentre en 'S'.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<AccEntidadExterna> findAllEntidadesExternasActivos()
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<AccEntidadExterna> lee_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lee_datos       = null;

		try
		{
			lee_datos = DaoCreator.getAccEntidadExternaDAO(ldm_manager).findAll(true, false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllEntidadesExternasActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lee_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param ab_OrderById si se ordena por id
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Etapa> findAllEtapasActivo(boolean ab_OrderById)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Etapa> lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getEtapaDAO(ldm_manager).findAllActivo(ab_OrderById);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAll", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Método de transaccciones con la base de datos para encontrar todos los registros de la tabla SDB_PGN_FASES
	 * que se encuentren en estado activos.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Fases> findAllFasesActivas()
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Fases> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getFasesDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllFasesActivas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Metodo de transacciones con la base de datos para encontrar todos los grupos naturaleza juridica que se encuentren activos.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<GrupoNaturalezaJuridica> findAllGrupoNaturalezaJuridicaActivos(boolean ab_b)
	    throws B2BException
	{
		DAOManager                          ldm_manager;
		Collection<GrupoNaturalezaJuridica> lgn_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lgn_datos       = null;

		try
		{
			lgn_datos = DaoCreator.getGrupoNaturalezaJuridicaDAO(ldm_manager).findAllActivo(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllGrupoNaturalezaJuridicaActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lgn_datos;
	}

	/**
	 * Consulta en base de datos todos los registros de la tabla que contengan los valores enviados como argumento.
	 *
	 * @param acs_parametros Argumento de tipo <code>CalidadSolicitante</code> que contiene los criterios necesarios para realizar la consulta.
	 * @return Colección de tipo <code>CalidadSolicitante</code> con los resultados de la consulta.
	 * @throws B2BException Señala que se prodújo una excepción.
	 *
	 */
	public synchronized Collection<CalidadSolicitante> findAllInCalidadSolicitante(CalidadSolicitante acs_parametros)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<CalidadSolicitante> lccs_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccs_datos      = null;

		try
		{
			if(acs_parametros != null)
				lccs_datos = DaoCreator.getCalidadSolicitanteDAO(ldm_manager).findAllInCalidadSolicitante(
					    acs_parametros
					);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllInCalidadSolicitante", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccs_datos;
	}

	/**
	 * Método de transacciones con la base de datos con el fin de encontrar la maxima version de la tabla
	 * SDB_PNG_NATURALEZA_JURIDICA.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<NaturalezaJuridica> findAllMaxVersionNaturalezaJuridica(boolean ab_b)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<NaturalezaJuridica> lcnj_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcnj_datos      = null;

		try
		{
			lcnj_datos = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager).findAllMaxVersion(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllMaxVersionNaturalezaJuridica", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcnj_datos;
	}

	/**
	 * Método de transacciones con la base de datos con el fin de encontrar la maxima version de la tabla
	 * SDB_PNG_TIPO_ACTO_EJECUTORIA.
	 *
	 * @param as_tipoActo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized int findAllMaxVersionTipoActoEjecutoria(String as_tipoActo)
	    throws B2BException
	{
		DAOManager ldm_manager;
		int        li_version;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		li_version      = 0;

		try
		{
			li_version = DaoCreator.getTipoActoDAO(ldm_manager).findMaxVersion(as_tipoActo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllMaxVersionTipoActoEjecutoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return li_version;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<MedidaArea> findAllMedidaAreaActivo()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<MedidaArea> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getMedidaAreaDAO(ldm_manager).findAll(true);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllMedidaAreaActivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Retorna el valor del objeto de Map.
	 *
	 * @return devuelve el valor de Map
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Map<String, String> findAllMedidaAreaNombres()
	    throws B2BException
	{
		DAOManager              ldm_manager;
		HashMap<String, String> lhmss_return;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lhmss_return     = new HashMap<String, String>();

		try
		{
			Collection<MedidaArea> lcma_medidas;

			lcma_medidas = DaoCreator.getMedidaAreaDAO(ldm_manager).findAll(true);

			if(CollectionUtils.isValidCollection(lcma_medidas))
			{
				Iterator<MedidaArea> lima_iterator;

				lima_iterator = lcma_medidas.iterator();

				while(lima_iterator.hasNext())
				{
					MedidaArea lma_medidaArea;

					lma_medidaArea = lima_iterator.next();

					if(lma_medidaArea != null)
					{
						String ls_idMedidaArea;
						String ls_codigo;

						ls_idMedidaArea     = lma_medidaArea.getIdMedidaArea();
						ls_codigo           = lma_medidaArea.getCodigo();

						if(StringUtils.isValidString(ls_idMedidaArea) && StringUtils.isValidString(ls_codigo))
							lhmss_return.put(ls_idMedidaArea, ls_codigo);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllMedidaAreaNombres", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lhmss_return;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar la maxíma version
	 * de la tabla SDB_PNG_NATURALEZA_JURIDICA.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<NaturalezaJuridica> findAllNaturalezaJuridica(boolean ab_b)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<NaturalezaJuridica> lcnj_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcnj_datos      = null;

		try
		{
			lcnj_datos = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager).findAllVersion(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllVersion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcnj_datos;
	}

	/**
	 * Método para consultar todos los nombres de las columnas de una tabla.
	 *
	 * @param as_nombreTabla de as nombre tabla
	 * @return Una colección de UserObjects con la consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<UserObjects> findAllNombresColumnas(String as_nombreTabla)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<UserObjects> lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getUserObjectsDAO(ldm_manager).findAllNombresColumnas(as_nombreTabla);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllNombresColumnas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

/**
 * Find all nombres tablas.
 *
 * @return el valor de collection
 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
 */
	public synchronized Collection<UserObjects> findAllNombresTablas()
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<UserObjects> lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getUserObjectsDAO(ldm_manager).findAllNombresTablas();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllNombresTablas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla
	 * SDB_PGN_OFICINA_ORIGEN.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<OficinaOrigen> findAllOficinasOrigen()
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<OficinaOrigen> lcoo_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcoo_datos      = null;

		try
		{
			lcoo_datos = DaoCreator.getOficinaOrigenDAO(ldm_manager).findAllOO();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllOficinasOrigen", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcoo_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection del resultado de la consulta de las plantillas activas.
	 *
	 * @return Colección de tipo <code>Plantilla</code> que contiene los resultados de la búsqueda.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Plantilla> findAllPlantillasActivo()
	    throws B2BException
	{
		DAOManager            ldm_manager;
		Collection<Plantilla> lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManagerCYN();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getPlantillaDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllPlantillasActivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla
	 * SDB_PGN_PROCESO_CONSULTA cuando la columna ACTIVO se encuentre en 'S'.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<ProcesoConsulta> findAllProcesoConsultaActivos()
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<ProcesoConsulta> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getProcesoConsultaDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllProcesoConsultaActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla
	 * SDB_ACC_PROCESO cuando la columna ACTIVO se encuentre en 'S'.
	 *
	 * @param ab_OrderById si se ordena por id
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Proceso> findAllProcesosActivos(boolean ab_OrderById)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<Proceso> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getProcesoDAO(ldm_manager).findAllActivo(ab_OrderById);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllProcesosActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Método de transacciones con la base de datos con el fin de encontrar todos los registros
	 * de la tabla SDB_PGN_REGIONAL donde la columna ACTIVO='S'.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Regional> findAllRegionalesActivos()
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<Regional> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getRegionalDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllRegionalesActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método de transacciones con la base de datos con el fin de encontrar todos los registros
	 * de la tabla SDB_PGN_REGIONAL donde la columna ACTIVO='S'.
	 * @param as_idCirculo con el id del circulo marcado
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Regional> findAllRegionalesActivosConCirculo(String as_idCirculo)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<Regional> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = null;

		try
		{
			CirculoRegistral lcr_circulo;
			lcr_circulo = DaoCreator.getCirculoRegistralDAO(ldm_manager).findById(as_idCirculo);

			if(lcr_circulo != null)
			{
				lcr_datos = DaoCreator.getRegionalDAO(ldm_manager).findAllActivo();

				if(CollectionUtils.isValidCollection(lcr_datos))
				{
					for(Regional li_iterador : lcr_datos)
					{
						if(li_iterador != null)
						{
							String ls_idRegional;
							ls_idRegional = li_iterador.getIdRegional();

							if(
							    StringUtils.isValidString(ls_idRegional)
								    && ls_idRegional.equals(lcr_circulo.getIdRegional())
							)
								li_iterador.setSeleccionado(true);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllRegionalesActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla
	 * SDB_ACC_SUBPROCESO cuando la columna ACTIVO se encuentre en 'S' organizados de forma ascendiente.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Subproceso> findAllSubProcesosActivos()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Subproceso> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getSubprocesoDAO(ldm_manager).findAll(true);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllSubProcesosActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla
	 * SDB_ACC_SUBPROCESO_VERSION.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<SubprocesoVersion> findAllSubprocesosVersion()
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<SubprocesoVersion> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerWorkflow();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getSubprocesoVersionDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllSubprocesosVersion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar los registros
	 * de la tabla SDB_ACC_COMPLETITUD_DOCUMENTAL.
	 *
	 * @param as_idSolicitud Objeto de tipo String con el cúal se realizará la consulta
	 * @param as_idProceso Objeto de tipo String con el cúal se realizará la consulta
	 * @return Collection de AccCompletitudDocumental
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<AccCompletitudDocumental> findAllTipoCompletitudProceso(
	    String as_idSolicitud, String as_idProceso
	)
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		Collection<AccCompletitudDocumental> lcacd_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcacd_datos     = new ArrayList<AccCompletitudDocumental>();

		try
		{
			Collection<AccCompletitudDocumental> lcacd_parametros;

			lcacd_parametros = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager)
					                         .findAllByProcesoSolicitud(as_idSolicitud, as_idProceso);

			if(CollectionUtils.isValidCollection(lcacd_parametros))
				lcacd_datos = lcacd_parametros;
			else
			{
				Collection<com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental> lctd_documentoTipos;

				lctd_documentoTipos = findAllTiposDocumentales(
					    ConstanteCommon.TIPO_DOCUMENTAL_CORRECCIONES, ldm_manager
					);

				if(CollectionUtils.isValidCollection(lctd_documentoTipos))
				{
					for(com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental lc_temp : lctd_documentoTipos)
					{
						if(lc_temp != null)
						{
							String ls_idTipoDocumental;

							ls_idTipoDocumental = lc_temp.getIdTipoDocumental();

							if(StringUtils.isValidString(ls_idTipoDocumental))
							{
								AccCompletitudDocumental lacd_completitudDocumental;

								lacd_completitudDocumental = new AccCompletitudDocumental();
								lacd_completitudDocumental.setIdTipoDocumental(ls_idTipoDocumental);

								if(lacd_completitudDocumental != null)
									lcacd_datos.add(lacd_completitudDocumental);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoCompletitudProceso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcacd_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar la maxíma version
	 * de la tabla SDB_PNG_NATURALEZA_JURIDICA.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<CriteriosDeBusqueda> findAllTipoCriterioBusqueda()
	    throws B2BException
	{
		DAOManager                      ldm_manager;
		Collection<CriteriosDeBusqueda> lccb_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccb_datos      = null;

		try
		{
			lccb_datos = DaoCreator.getTipoCriterioBusquedaDAO(ldm_manager).findAllTipoCriterioBusqueda();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoCriteriosDeBusqueda", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccb_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla
	 * SDB_PGN_TIPO_CRITERIO_BUSQUEDA cuando la columna ACTIVO se encuentre en 'S'.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<CriteriosDeBusqueda> findAllTipoCriterioBusquedaActivos()
	    throws B2BException
	{
		DAOManager                      ldm_manager;
		Collection<CriteriosDeBusqueda> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getTipoCriterioBusquedaDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoCriterioBusquedaActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @param ab_orderById correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoOficina> findAllTipoOficinaActivo(String as_s, boolean ab_orderById)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<TipoOficina> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getTipoOficinaDAO(ldm_manager).findAllActivo(as_s, ab_orderById);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoOficinaActivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Metodo de transacciones con la base de datos para encontrar todos los tipo rrr que se encuentren activos.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TipoRrr> findAllTipoRrrActivos(boolean ab_b)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<TipoRrr> ltr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ltr_datos       = null;

		try
		{
			ltr_datos = DaoCreator.getTipoRrrDAO(ldm_manager).findAll(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoRrrActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltr_datos;
	}

	/**
	 * Metodo de transacciones con la base de datos para encontrar todos los tipos tarifa registral que se encuentren activos.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoTarifaRegistral> findAllTipoTarifaRegistralActivos()
	    throws B2BException
	{
		DAOManager                      ldm_manager;
		Collection<TipoTarifaRegistral> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getTipoTarifaRegistralDao(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTipoTarifaRegistralActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Método para consultar todos los tipos acto.
	 *
	 * @return una colección de tipos acto con la consulta realizada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TipoActo> findAllTiposActo()
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<TipoActo> lctes_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctes_datos     = null;

		try
		{
			lctes_datos = DaoCreator.getTipoActoDAO(ldm_manager).findAllTiposActo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposActo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctes_datos;
	}

	/**
	 * Método encargado de consultar mapa que contiene todos los tipo acto activos.
	 *
	 * @return Mapa que contiene todos los tipo acto activos.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Map<String, TipoActo> findAllTiposActoActivoData()
	    throws B2BException
	{
		DAOManager            ldm_manager;
		Map<String, TipoActo> lmsta_return;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lmsta_return     = null;

		try
		{
			lmsta_return = DaoCreator.getTipoActoDAO(ldm_manager).findAllActosActivosData();

			if(CollectionUtils.isValidCollection(lmsta_return))
			{
				TipoActo lta_tmp;

				lta_tmp = lmsta_return.get(TipoActoCommon.DONACION);

				if(lta_tmp != null)
				{
					String ls_cuotaParteDonacionString;

					ls_cuotaParteDonacionString = lta_tmp.getCuotaparteDonacionString();
					lta_tmp.setCuotaparteDonacion(
					    StringUtils.isValidString(ls_cuotaParteDonacionString)
						    && ls_cuotaParteDonacionString.equalsIgnoreCase(EstadoCommon.S)
					);
					lmsta_return.put(lta_tmp.getIdTipoActo(), lta_tmp);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposActoActivoData", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lmsta_return;
	}

	/**
	 * Método de transacciones con la base de datos con el fin de encontrar todos los registros
	 * de la tabla SDB_PGN_TIPO_ACTO donde la columna ACTIVO='S'.
	 *
	 * @param ab_orderBy correspondiente al valor del tipo de objeto boolean
	 * @param ab_nuevaSolicitud correspondiente al valor del tipo de objeto boolean
	 * @param ab_registro correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoActo> findAllTiposActoActivos(
	    boolean ab_orderBy, boolean ab_nuevaSolicitud, boolean ab_registro
	)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<TipoActo> lcta_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcta_datos      = null;

		try
		{
			if(ab_nuevaSolicitud)
			{
				Constantes lc_c;
				lc_c = new Constantes();
				lc_c.setIdConstante(ConstanteCommon.ACTOS_ADJUDICACION_REMATE);

				lc_c = DaoCreator.getConstantesDAO(ldm_manager).findById(lc_c);

				if(lc_c != null)
				{
					String[]      las_as;
					StringBuilder lsb_sb;
					String        ls_codigos;

					lsb_sb     = new StringBuilder();
					las_as     = lc_c.getCaracter().split(",");

					for(String ls_s : las_as)
						lsb_sb.append("'" + ls_s + "',");

					ls_codigos = lsb_sb.toString();

					if(StringUtils.isValidString(ls_codigos))
					{
						lsb_sb         = new StringBuilder(lsb_sb.substring(0, lsb_sb.length() - 1));
						ls_codigos     = lsb_sb.toString();

						lcta_datos = DaoCreator.getTipoActoDAO(ldm_manager)
								                   .findAllActosActivos(ab_orderBy, ls_codigos, ab_registro);
					}
				}
			}
			else
				lcta_datos = DaoCreator.getTipoActoDAO(ldm_manager).findAllActosActivos(ab_orderBy, null, ab_registro);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposActoActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcta_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoActo> findAllTiposActoRemanenteActivos()
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<TipoActo> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = new ArrayList<TipoActo>();

		try
		{
			Constantes lc_constante;

			lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(ConstanteCommon.ACTOS_REMATE);

			if(lc_constante != null)
			{
				String ls_caracter;

				ls_caracter = lc_constante.getCaracter();

				if(StringUtils.isValidString(ls_caracter))
				{
					TipoActoDAO           ltid_DAO;
					TipoDocumentalActoDAO ltdad_DAO;
					String[]              lsa_codigos;
					int                   li_tam;

					ltid_DAO        = DaoCreator.getTipoActoDAO(ldm_manager);
					ltdad_DAO       = DaoCreator.getTipoDocumentalActoDAO(ldm_manager);
					lsa_codigos     = ls_caracter.split(",");
					li_tam          = lsa_codigos.length;

					if(li_tam > 0)
					{
						for(int li_count = 0; li_count < li_tam; li_count++)
						{
							TipoActo lta_ta;

							lta_ta = ltid_DAO.findById(lsa_codigos[li_count]);

							if(lta_ta != null)
							{
								lta_ta.setRequiereDocumentos(
								    CollectionUtils.isValidCollection(ltdad_DAO.findByActoVersion(lta_ta))
								);

								lcf_datos.add(lta_ta);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposActoRemanenteActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(!CollectionUtils.isValidCollection(lcf_datos))
			lcf_datos = null;

		return lcf_datos;
	}

	/**
	 * Retorna el valor del objeto de Map.
	 *
	 * @return devuelve el valor de Map
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Map<String, TipoActo> findAllTiposActoRemanenteActivosMap()
	    throws B2BException
	{
		DAOManager            ldm_manager;
		Map<String, TipoActo> lmst_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lmst_return     = new HashMap<String, TipoActo>();

		try
		{
			Constantes lc_constante;

			lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
					                     .findById(ConstanteCommon.CODIGOS_ACTOS_EMBARGO_REMANENTES);

			if(lc_constante != null)
			{
				String ls_caracter;

				ls_caracter = lc_constante.getCaracter();

				if(StringUtils.isValidString(ls_caracter))
				{
					TipoActoDAO           ltid_DAO;
					TipoDocumentalActoDAO ltdad_DAO;
					String[]              lsa_codigos;
					int                   li_tam;

					ltid_DAO        = DaoCreator.getTipoActoDAO(ldm_manager);
					ltdad_DAO       = DaoCreator.getTipoDocumentalActoDAO(ldm_manager);
					lsa_codigos     = ls_caracter.split(",");
					li_tam          = lsa_codigos.length;

					if(li_tam > 0)
					{
						for(int li_count = 0; li_count < li_tam; li_count++)
						{
							TipoActo lta_ta;

							lta_ta = ltid_DAO.findById(lsa_codigos[li_count]);

							if(lta_ta != null)
							{
								String ls_tipoActo;

								ls_tipoActo = lta_ta.getIdTipoActo();

								if(StringUtils.isValidString(ls_tipoActo))
								{
									lta_ta.setRequiereDocumentos(
									    CollectionUtils.isValidCollection(ltdad_DAO.findByActoVersion(lta_ta))
									);

									lmst_return.put(ls_tipoActo, lta_ta);
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposActoRemanenteActivosMap", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(!CollectionUtils.isValidCollection(lmst_return))
			lmst_return = null;

		return lmst_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoActo> findAllTiposActoVisActivos()
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<TipoActo> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = new ArrayList<TipoActo>();

		try
		{
			Constantes lc_constante;

			lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
					                     .findById(ConstanteCommon.ACTOS_TIPO_ADQUISICION_ESPECIAL);

			if(lc_constante != null)
			{
				String ls_caracter;

				ls_caracter = lc_constante.getCaracter();

				if(StringUtils.isValidString(ls_caracter))
				{
					TipoActoDAO           ltid_DAO;
					TipoDocumentalActoDAO ltdad_DAO;
					String[]              lsa_codigos;
					int                   li_tam;

					ltid_DAO        = DaoCreator.getTipoActoDAO(ldm_manager);
					ltdad_DAO       = DaoCreator.getTipoDocumentalActoDAO(ldm_manager);
					lsa_codigos     = ls_caracter.split(",");
					li_tam          = lsa_codigos.length;

					if(li_tam > 0)
					{
						for(int li_count = 0; li_count < li_tam; li_count++)
						{
							TipoActo lta_ta;

							lta_ta = ltid_DAO.findById(lsa_codigos[li_count]);

							if(lta_ta != null)
							{
								lta_ta.setRequiereDocumentos(
								    CollectionUtils.isValidCollection(ltdad_DAO.findByActoVersion(lta_ta))
								);

								String ls_requiereCuantia;

								ls_requiereCuantia = StringUtils.getStringNotNull(lta_ta.getRequiereCuantia());

								if(ls_requiereCuantia.equalsIgnoreCase(EstadoCommon.N))
									lta_ta.setCantidadActos(Integer.valueOf(1));

								lcf_datos.add(lta_ta);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposActoVisActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(!CollectionUtils.isValidCollection(lcf_datos))
			lcf_datos = null;

		return lcf_datos;
	}

	/**
	 * Método de transaccciones con la base de datos para encontrar todos los registros
	 * de la tabla SDB_PNG_TIPO_ANOTACION cuando la columna ACTIVO='S'.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoAnotacion> findAllTiposAnotacionActivos()
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<TipoAnotacion> lcta_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcta_datos      = null;

		try
		{
			lcta_datos = DaoCreator.getTipoAnotacionDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposAnotacionActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcta_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontar todos los registros de la tabla
	 * SDB_ACC_TIPO_CAUSAL donde el ESTADO_CAUSAL = 'A'.
	 *
	 * @param ls_idTurnoHistoria correspondiente al valor del tipo de objeto String
	 * @param ab_bandera indica si se debe ejecuttar una sentencia sql diferente para traer los datos donde el ID_SUBPROCESO='2'
	 * @param ab_bandera indica si se viene de Nota Devolutiva
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoCausal> findAllTiposCausalesActivos(
	    String ls_idTurnoHistoria, boolean ab_bandera, boolean ab_notaDevolutiva
	)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<TipoCausal> lctc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctc_datos      = null;

		try
		{
			lctc_datos = DaoCreator.getTipoCausalDAO(ldm_manager).findAllTiposCausalesActivos(ab_bandera);

			if(StringUtils.isValidString(ls_idTurnoHistoria) && CollectionUtils.isValidCollection(lctc_datos))
			{
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lthd_dao;
				lth_turnoHistoria     = new TurnoHistoria();
				lthd_dao              = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

				lth_turnoHistoria = lthd_dao.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					TurnoHistoria lth_turnoHistoriaAnterior;

					lth_turnoHistoriaAnterior = new TurnoHistoria();

					lth_turnoHistoriaAnterior.setIdAnterior(lth_turnoHistoria.getIdAnterior());

					if(lth_turnoHistoriaAnterior != null)
					{
						BigDecimal lbd_idEtapa;

						lbd_idEtapa = lth_turnoHistoriaAnterior.getIdEtapa();

						if(NumericUtils.isValidBigDecimal(lbd_idEtapa))
						{
							if(
							    lth_turnoHistoriaAnterior.getIdEtapa()
								                             .equals(
								        NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_MAYOR_VALOR_VENCIDO)
								    )
								    && lth_turnoHistoriaAnterior.getIdMotivo()
								                                    .equals(
								        NumericUtils.getLongWrapper(MotivoTramiteCommon.CONFRONTACION_CORRECTIVA)
								    )
							)
							{
								Iterator<TipoCausal> lirc_iterator;
								boolean              lb_causal_119;

								lirc_iterator     = lctc_datos.iterator();
								lb_causal_119     = false;

								while(lirc_iterator.hasNext() || lb_causal_119)
								{
									TipoCausal ltc_tipoCausal;
									ltc_tipoCausal = lirc_iterator.next();

									if(ltc_tipoCausal != null)
									{
										if(ltc_tipoCausal.getIdTipoCausal().equalsIgnoreCase("119"))
										{
											ltc_tipoCausal.setSeleccionado(true);
											ltc_tipoCausal.setBloqueado(true);
											lb_causal_119 = true;
										}
									}
								}
							}
						}
					}

					if(ab_notaDevolutiva)
					{
						String ls_idTurno;

						ls_idTurno = lth_turnoHistoria.getIdTurno();

						if(StringUtils.isValidString(ls_idTurno))
						{
							Collection<NotaDevolutiva> lcnd_notaDevolutiva;

							lcnd_notaDevolutiva = DaoCreator.getNotaDevolutivaDAO(ldm_manager).findByTurno(ls_idTurno);

							if(CollectionUtils.isValidCollection(lcnd_notaDevolutiva))
							{
								for(TipoCausal ltc_tmp : lctc_datos)
								{
									if(ltc_tmp != null)
									{
										String ls_idTipoCausal;

										ls_idTipoCausal = ltc_tmp.getIdTipoCausal();

										if(StringUtils.isValidString(ls_idTipoCausal))
										{
											for(NotaDevolutiva lnd_tmp : lcnd_notaDevolutiva)
											{
												if(
												    (lnd_tmp != null)
													    && ls_idTipoCausal.equalsIgnoreCase(lnd_tmp.getIdCausal())
													    && lnd_tmp.getActivo().equalsIgnoreCase(
													        IdentificadoresCommon.S
													    )
												)
													ltc_tmp.setSeleccionado(true);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposCausalesActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctc_datos;
	}

	/**
	 * Consulta todos los tipos causales asociados a un id proceso y subproceso específicos.
	 *
	 * @param as_idProceso id del proceso asociado al causal
	 * @param as_idSubproceso id del subproceso asociado al causal
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoCausal> findAllTiposCausalesActivosPorProcSubproc(
	    String as_idProceso, String as_idSubproceso
	)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<TipoCausal> lctc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctc_datos      = null;

		try
		{
			lctc_datos = DaoCreator.getTipoCausalDAO(ldm_manager)
					                   .findAllTiposCausalesActivosByProcSubproc(as_idProceso, as_idSubproceso);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposCausalesActivosPorProcSubproc", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctc_datos;
	}

	/**
	 * Busca todos los tipos documentales que se encuentren activos.
	 *
	 * @param ls_constante correspondiente al valor del tipo de objeto String
	 * @return Colección de TipoDocumental con el resultado de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoDocumental> findAllTiposDocumentales(String ls_constante)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<TipoDocumental> lctd_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctd_datos      = null;

		try
		{
			lctd_datos = findAllTiposDocumentales(ls_constante, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findAllTiposDocumentales", lb2be_e);
			ldm_manager.setRollbackOnly();

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctd_datos;
	}

	/**
	 * Busca todos los tipos documentales que se encuentren activos.
	 *
	 * @param ls_constante correspondiente al valor del tipo de objeto String
	 * @param ldm_manager de ldm manager
	 * @return Colección de TipoDocumental con el resultado de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<TipoDocumental> findAllTiposDocumentales(
	    String ls_constante, DAOManager ldm_manager
	)
	    throws B2BException
	{
		Collection<TipoDocumental> lctd_datos;

		lctd_datos = new ArrayList<TipoDocumental>();

		try
		{
			if(StringUtils.isValidString(ls_constante))
			{
				Constantes lc_constante;

				lc_constante = new Constantes();

				lc_constante.setIdConstante(ls_constante);

				lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(lc_constante);

				if(lc_constante != null)
				{
					String ls_caracter;

					ls_caracter = lc_constante.getCaracter();

					if(StringUtils.isValidString(ls_caracter))
					{
						String[] lsa_codigos;

						lsa_codigos = ls_caracter.split(",");

						if(CollectionUtils.isValidCollection(lsa_codigos))
						{
							for(String ls_tmp : lsa_codigos)
							{
								if(StringUtils.isValidString(ls_tmp))
								{
									TipoDocumental ltd_tipoDoc;
									ltd_tipoDoc = new TipoDocumental();

									ltd_tipoDoc.setIdTipoDocumental(ls_tmp);
									ltd_tipoDoc = DaoCreator.getTipoDocumentalDAO(ldm_manager).findById(ltd_tipoDoc);

									if(ltd_tipoDoc != null)
										lctd_datos.add(ltd_tipoDoc);
								}
							}
						}
					}
				}
			}
			else
				lctd_datos = DaoCreator.getTipoDocumentalDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findAllTiposDocumentales", lb2be_e);

			throw lb2be_e;
		}

		return lctd_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar SDB_ACC_TIPO_ESTADO_SOLICITUD
	 * donde la columna ACTIVO = 'S'.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoEstadoSolicitud> findAllTiposEstadoSolicitudActivos()
	    throws B2BException
	{
		DAOManager                      ldm_manager;
		Collection<TipoEstadoSolicitud> lctes_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctes_datos     = null;

		try
		{
			lctes_datos = DaoCreator.getTipoEstadoSolicitudDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposEstadoSolicitudActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctes_datos;
	}

	/**
	 * Find all unidades de tiempo.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<UnidadTiempoVencimiento> findAllUnidadesDeTiempo()
	    throws B2BException
	{
		DAOManager                          ldm_manager;
		Collection<UnidadTiempoVencimiento> lcutv_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcutv_datos     = null;

		try
		{
			lcutv_datos = DaoCreator.getUnidadTiempoVencimientoDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("UnidadTiempoVencimiento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcutv_datos;
	}

	/**
	 * Método de transacciones de base de datos para encontrar todos los registros de la tabla
	 * SDB_AUT_USUARIO.
	 *
	 * @param as_idUsuario id usuario para la consualta en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Usuario> findAllUsers(String as_idUsuario)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<Usuario> lcu_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcu_datos       = null;

		try
		{
			lcu_datos = DaoCreator.getUsuarioDAO(ldm_manager).findAllUsers(as_idUsuario);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllUsers", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcu_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los usuarios activos de la tabla
	 * SDB_AUT_USUARIO.
	 *
	 * @param as_idUsuario id para buscar en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Usuario> findAllUsersActivos(String as_idUsuario)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<Usuario> lcu_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcu_datos       = null;

		try
		{
			lcu_datos = DaoCreator.getUsuarioDAO(ldm_manager).findAllUsersActivos(as_idUsuario);

			if(!CollectionUtils.isValidCollection(lcu_datos))
				throw new B2BException(ErrorKeys.USUARIO_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllUsersActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcu_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla
	 * SDB_PNG_USUARIO_ETAPA que coincidan con un ID_ETAPA específico.
	 *
	 * @param as_etapa es el id etapa para consultar en la base de datos
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Usuario> findAllUsersByEtapa(long as_etapa, boolean ab_b)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<Usuario> lcu_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcu_datos       = null;

		try
		{
			lcu_datos = DaoCreator.getUsuarioDAO(ldm_manager).findAllUsersByEtapa(as_etapa, ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllUsersByEtapa", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcu_datos;
	}

	/**
	 * Método de transacciones con la base de datos con el fin de encontrar las versiones para cada naturaleza
	 * SDB_PNG_NATURALEZA_JURIDICA.
	 *
	 * @param as_idAlertaN correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<NaturalezaJuridica> findAllVersionById(String as_idAlertaN)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<NaturalezaJuridica> lcnj_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcnj_datos      = null;

		try
		{
			lcnj_datos = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager).findAllVersionById(as_idAlertaN);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllVersionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcnj_datos;
	}

	/**
	 * Método de transaccciones con la base de datos para encontrar todos los registros de la tabla
	 * SDB_ACC_TURNO_HISTORIA que coincidan con un ID_TURNO, ID_ETAPA específicos.
	 *
	 * @param al_idEtapa id etapa para consultar en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<InteresadoDocumentoTipo> findAprobaciones(Long al_idEtapa)
	    throws B2BException
	{
		DAOManager                          ldm_manager;
		Collection<InteresadoDocumentoTipo> lcidt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcidt_datos     = null;

		try
		{
			lcidt_datos = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findAprobaciones(al_idEtapa);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAprobaciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Método para consultar SDB_ACC_BITACORA_BLOQUEO por círculo y matrícula.
	 *
	 * @param abb_bb Objeto de tipo BitacoraBloqueo con el cúal se realizará la consulta
	 * @param ab_onlyBloqueantes correspondiente al valor del tipo de objeto boolean
	 * @return Collection<BitacoraBloqueo> llena con los datos de la BD
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<BitacoraBloqueo> findBitacoraBloqueoByCirculoMatricula(
	    BitacoraBloqueo abb_bb, boolean ab_onlyBloqueantes
	)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<BitacoraBloqueo> lcbb_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcbb_datos      = null;

		try
		{
			if(abb_bb != null)
				lcbb_datos = DaoCreator.getBitacoraBloqueoDAO(ldm_manager)
						                   .findByCirculoMatricula(abb_bb, ab_onlyBloqueantes);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findBitacoraBloqueoByCirculoMatricula", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcbb_datos;
	}

	/**
	 * Find by etapa devolucion 111.
	 *
	 * @param acad_param correspondiente al valor de acad param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<CausalAprobacionDevolucion> findByEtapaDevolucion111(
	    CausalAprobacionDevolucion acad_param
	)
	    throws B2BException
	{
		DAOManager                             ldm_manager;
		Collection<CausalAprobacionDevolucion> lcda_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcda_datos      = null;

		try
		{
			lcda_datos = DaoCreator.getCausalAprobacionDevolucionDAO(ldm_manager).findByEtapaDevolucion111(acad_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByEtapaDevolucion111", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcda_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la
	 * tabla SDB_ACC_TIPO_RECEPCION que esten habilitados para comunicación ó habilitados para notificaón.
	 *
	 * @param atr_parametros  objeto delc ual se desea consultar si esta habilitado comunicación o notificación
	 * @param ab_not indica si trae los registros que esten habilitados para comunicación ó habilitados para notificaón  dependiendo su valor
	 * @param ab_personaJuridica correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoRecepcion> findByHabilitado(
	    TipoRecepcion atr_parametros, boolean ab_not, boolean ab_personaJuridica
	)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<TipoRecepcion> lctr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctr_datos      = null;

		try
		{
			{
				String ls_soloIncluir;

				ls_soloIncluir = atr_parametros.getSoloIncluir();

				if(StringUtils.isValidString(ls_soloIncluir))
				{
					if(
					    ls_soloIncluir.equalsIgnoreCase(TipoRecepcionCommon.COPIAS)
						    || ls_soloIncluir.equalsIgnoreCase(TipoRecepcionCommon.CERTIFICADOS)
					)
					{
						StringBuilder lsb_sb;

						lsb_sb = new StringBuilder();
						lsb_sb.append("'");
						lsb_sb.append(TipoRecepcionCommon.CORREO_ELECTRONICO);
						lsb_sb.append("','");
						lsb_sb.append(TipoRecepcionCommon.ORIP_DE_ORIGEN);
						lsb_sb.append("'");
						atr_parametros.setSoloIncluir(lsb_sb.toString());
					}
					else if(ls_soloIncluir.equalsIgnoreCase(TipoRecepcionCommon.ACTUACIONES_ADMINISTRATIVAS) && ab_not)
					{
						StringBuilder lsb_sb;

						lsb_sb = new StringBuilder();
						lsb_sb.append("'");
						lsb_sb.append(TipoRecepcionCommon.DIRECCION_CORRESPONDENCIA);
						lsb_sb.append("','");
						lsb_sb.append(TipoRecepcionCommon.DIRECCION_RESIDENCIA);
						lsb_sb.append("','");
						lsb_sb.append(TipoRecepcionCommon.CORREO_ELECTRONICO);
						lsb_sb.append("'");
						atr_parametros.setSoloIncluir(lsb_sb.toString());
					}
					else
						atr_parametros.setSoloIncluir(null);
				}
			}

			lctr_datos = DaoCreator.getTipoRecepcionDAO(ldm_manager)
					                   .findByHabilitado(atr_parametros, ab_not, ab_personaJuridica);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByHabilitado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctr_datos;
	}

	/**
	 * Metodo encargado de consultar todos los circulos con un ID REGIONAL enviado como criterio de consulta.
	 *
	 * @param acr_param Argumento de tipo CirculoRegistral que contiene los criterios necesarios para realizar la consulta.
	 * @return Colección de CirculoRegistral que contiene los resultados que cumplieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CirculoRegistral> findByIdRegionalOnly(CirculoRegistral acr_param)
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<CirculoRegistral> lcr_return;

		lcr_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(acr_param != null)
				lcr_return = DaoCreator.getCirculoRegistralDAO(ldm_manager).findByIdRegionalOnly(acr_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByIdRegionalOnly", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_return;
	}

	/**
	 * Metodo encargado de consultar un tipo documental en especifico.
	 *
	 * @param atd_param Argumento de tipo TipoDocumental que contiene los criterios necesarios para realizar la consulta.
	 * @return TipoDocumental que contiene los resultados que cumplieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoDocumental
	 */
	public synchronized TipoDocumental findByIdTipoDocumental(TipoDocumental atd_param)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		TipoDocumental ltd_return;

		ltd_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(atd_param != null)
				ltd_return = DaoCreator.getTipoDocumentalDAO(ldm_manager).findById(atd_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByIdTipoDocumental", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltd_return;
	}

	/**
	 * Find by id tipo oficina activo entidad exenta.
	 *
	 * @param as_idTipoOficina de as id tipo oficina
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<AccEntidadExterna> findByIdTipoOficinaActivoEntidadExenta(String as_idTipoOficina)
	    throws B2BException
	{
		Collection<AccEntidadExterna> lcto_datos;

		lcto_datos = null;

		if(StringUtils.isValidString(as_idTipoOficina))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lcto_datos = DaoCreator.getAccEntidadExternaDAO(ldm_manager)
						                   .findByIdTipoOficinaActivoEntidadExenta(as_idTipoOficina);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findByIdTipoOficinaActivoEntidadExenta", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcto_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_TIPO_RECEPCION
	 * que coincidan con una PROCEDENCIA específico.
	 *
	 * @param atr_parametros tipo recepcion a consultar
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoRecepcion> findByProcedencia(TipoRecepcion atr_parametros)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<TipoRecepcion> lctr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctr_datos      = null;

		try
		{
			lctr_datos = DaoCreator.getTipoRecepcionDAO(ldm_manager).findByProcedencia(atr_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByProcedencia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctr_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_CALIDAD_SOLICITANTE.
	 *
	 * @param ab_bool correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CalidadSolicitante> findCalidadSolicitante(boolean ab_bool)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<CalidadSolicitante> lccs_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccs_datos      = null;

		try
		{
			lccs_datos = DaoCreator.getCalidadSolicitanteDAO(ldm_manager).findAll(ab_bool);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCalidadSolicitante", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccs_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_CALIDAD_SOLICITANTE
	 * para las etapas de entrega.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CalidadSolicitante> findCalidadSolicitanteEntrega()
	    throws B2BException
	{
		return findCalidadSolicitanteEntrega(false);
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_CALIDAD_SOLICITANTE
	 * para las etapas de entrega.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CalidadSolicitante> findCalidadSolicitanteEntrega(boolean ab_b)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<CalidadSolicitante> lccs_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccs_datos      = null;

		try
		{
			String     ls_idConstante;
			Constantes lc_constante;
			String     ls_caracter;
			String[]   lsa_filtros;

			if(ab_b)
			{
				ls_idConstante     = ConstanteCommon.CALIDAD_APODERADO_TERCERO_PERSONA_ENTREGA;

				lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(ls_idConstante);
			}
			else
			{
				ls_idConstante     = ConstanteCommon.CALIDAD_TERCERO_PERSONA_ENTREGA;

				lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(ls_idConstante);
			}

			if(lc_constante == null)
			{
				Object[] loa_messageArgs;

				loa_messageArgs        = new String[1];
				loa_messageArgs[0]     = ls_idConstante;

				throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
			}

			ls_caracter     = StringUtils.getStringNotNull(lc_constante.getCaracter());

			lsa_filtros     = ls_caracter.split(",");

			lccs_datos = DaoCreator.getCalidadSolicitanteDAO(ldm_manager).findAllEntrega(lsa_filtros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCalidadSolicitanteEntrega", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccs_datos;
	}

	/**
	 * Método encargado de consultar las calidades del solicitante para el proceso de traslado.
	 *
	 * @return Colección de calidad solicitante con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<CalidadSolicitante> findCalidadSolicitanteTraslado()
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<CalidadSolicitante> lccs_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccs_datos      = null;

		try
		{
			lccs_datos = DaoCreator.getCalidadSolicitanteDAO(ldm_manager).findTraslado();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCalidadSolicitanteTraslado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccs_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la
	 * tabla SDB_PGN_CAMPOS_CONSULTA.
	 *
	 * @param ab_activo constante para consultar en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CamposConsulta> findCamposConsulta(boolean ab_activo)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<CamposConsulta> lccc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccc_datos      = null;

		try
		{
			lccc_datos = DaoCreator.getCamposConsultaDAO(ldm_manager).findAll(ab_activo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findResultadoConsulta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccc_datos;
	}

	public synchronized Collection<CirculoRegistral> findCirculoRegistralByTurnoEtapa(
	    String as_idTurno, String as_idEtapa
	)
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		Collection<CirculoRegistral> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getCirculoRegistralDAO(ldm_manager).findByTurnoEtapa(as_idTurno, as_idEtapa);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCirculoRegistralByTurnoEtapa", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Método de transacciones por la base de datos para encontrar todos los registros de la tabla SDB_PGN_CONSTANTES
	 * que coincida con un ID_CONSTANTE específico.
	 *
	 * @param as_parametro id constante para consultar en la base de datos
	 * @return devuelve el valor de Constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Constantes
	 */
	public synchronized Constantes findConstantesById(String as_parametro)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Constantes lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getConstantesDAO(ldm_manager).findById(as_parametro);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findConstantesById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PNG_COORDENADA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Coordenada> findCoordenada()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Coordenada> lcc_datos;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			lcc_datos = DaoCreator.getCoordenadaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCoordenada", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para obtener decisión calificación.
	 *
	 * @param at_t correspondiente al valor del tipo de objeto Trazabilidad
	 * @return String de decisión calificación
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public synchronized String findDecisionCalificacion(Trazabilidad at_t)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_decision;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_decision     = null;

		try
		{
			ls_decision = findDecisionCalificacion(at_t, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDecisionCalificacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_decision;
	}

	/**
	 * Método para obtener decisión calificación.
	 *
	 * @param at_t correspondiente al valor del tipo de objeto Trazabilidad
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return String de decisión calificación
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public synchronized String findDecisionCalificacion(Trazabilidad at_t, DAOManager adm_manager)
	    throws B2BException
	{
		String lcda_datos;

		lcda_datos = null;

		if(at_t != null)
		{
			MotivoTramite lmt_motivoTramite;
			TurnoHistoria lth_turnoHistTemp;
			Turno         lt_turno;
			TurnoHistoria lth_turnoHistoria;

			lmt_motivoTramite     = new MotivoTramite();
			lth_turnoHistTemp     = new TurnoHistoria();
			lt_turno              = at_t.getTurno();
			lth_turnoHistoria     = at_t.getTurnoHistoria();

			lth_turnoHistTemp.setIdTurno(
			    (lt_turno != null) ? lt_turno.getIdTurno()
			                       : ((lth_turnoHistoria != null) ? lth_turnoHistoria.getIdTurno() : null)
			);

			lth_turnoHistTemp = DaoCreator.getTurnoHistoriaDAO(adm_manager).findByIdTurnoEtapaOrdered(
				    lth_turnoHistTemp
				);

			if(lth_turnoHistTemp != null)
			{
				lmt_motivoTramite.setIdEtapaOrigen(NumericUtils.getLong(lth_turnoHistTemp.getIdEtapa()));
				lmt_motivoTramite.setIdMotivo(NumericUtils.getLong(lth_turnoHistTemp.getIdMotivo()));
			}

			lmt_motivoTramite = DaoCreator.getMotivoTramiteDAO(adm_manager).findDecisionCalificacion(lmt_motivoTramite);

			if(lmt_motivoTramite != null)
				lcda_datos = lmt_motivoTramite.getDecisionCalificacion();
		}

		return lcda_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PGN_DEPARTAMENTO
	 * con un ID_PAIS y un ID_DEPARTAMENTO específico.
	 *
	 * @param ad_parametros objeto de donde se extra el ID_PAIS y el ID_DEPARTAMENTO
	 * @return devuelve el valor de Departamento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Departamento
	 */
	public synchronized Departamento findDepartamentById(Departamento ad_parametros)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		Departamento ld_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ld_datos        = null;

		try
		{
			ld_datos = DaoCreator.getDepartamentoDAO(ldm_manager).findById(ad_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDepartamentById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ld_datos;
	}

	/**
	 * Método de transaccciones con la base de datos para encontrar toidos los registros de la
	 * tabla SDB_PGN_DEPARTAMENTO que coincida con un ID_PAIS específico, además que se encuentre en estado activo.
	 *
	 * @param ad_parametros departamento a consultar donde se extrae el ID_PAIS para la consulta en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Departamento> findDepartaments(Departamento ad_parametros)
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<Departamento> lcd_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcd_datos       = null;

		try
		{
			lcd_datos = DaoCreator.getDepartamentoDAO(ldm_manager).findAllByPais(ad_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDepartaments", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcd_datos;
	}

	/**
	 * Método encargado de consultar la información del acto.
	 *
	 * @param as_idActo de as id acto
	 * @return Mapa que contiene la información del acto consultado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Map<String, Object> findDetalleActo(String as_idActo)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Map<String, Object> lmso_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lmso_return     = null;

		try
		{
			if(StringUtils.isValidString(as_idActo))
				lmso_return = DaoCreator.getActoDAO(ldm_manager).findDetalleActo(as_idActo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDetalleActo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lmso_return;
	}

	/**
	 * Retorna el valor del objeto de Collection del resultado de la consulta de los motivos diferentes por id proceso y id etapa.
	 *
	 * @param as_idProceso Objeto de tipo <code>String</code> que indica el id del proceso.
	 * @param al_idEtapa Parametro de tipo <code>long</code> que indica el id de la etapa.
	 * @return Colección de tipo <code>Plantilla</code> que contiene los resultados de la búsqueda.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<MotivoTramite> findDistinctByIdProcesoAndIdEtapa(
	    String as_idProceso, long al_idEtapa
	)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<MotivoTramite> lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getMotivoTramiteDAO(ldm_manager)
					                .findDistinctByIdProcesoAndIdEtapa(as_idProceso, al_idEtapa);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDistinctByIdProcesoAndIdEtapa", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Retorna el valor del objeto de Collection de AccEntidadExterna con base en el nombre entidad.
	 *
	 * @param as_nombreEntidad Variable que contiene el nombre de la entidad.
	 * @return Colección resultante de la consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<AccEntidadExterna> findEntidadExternaByNombreLike(String as_nombreEntidad)
	    throws B2BException
	{
		Collection<AccEntidadExterna> lcaee_return;
		DAOManager                    ldm_manager;
		TipoEntidadDAO                lted_tipoEntidadDAO;
		DepartamentoDAO               ldd_departamentoDAO;
		MunicipioDAO                  lmd_municipioDAO;
		PaisDAO                       lpd_paisDAO;
		PersonaDAO                    lpd_personaDAO;
		AccEntidadExternaPersonaDAO   laeepd_entidadExternaPersonaDAO;

		lcaee_return                        = null;
		ldm_manager                         = DaoManagerFactory.getDAOManager();
		lted_tipoEntidadDAO                 = DaoCreator.getTipoEntidadDAO(ldm_manager);
		ldd_departamentoDAO                 = DaoCreator.getDepartamentoDAO(ldm_manager);
		lmd_municipioDAO                    = DaoCreator.getMunicipioDAO(ldm_manager);
		lpd_paisDAO                         = DaoCreator.getPaisDAO(ldm_manager);
		lpd_personaDAO                      = DaoCreator.getPersonaDAO(ldm_manager);
		laeepd_entidadExternaPersonaDAO     = DaoCreator.getAccEntidadExternaPersonaDAO(ldm_manager);

		try
		{
			if(StringUtils.isValidString(as_nombreEntidad))
			{
				String ls_like;

				ls_like = as_nombreEntidad + "%";

				if(StringUtils.isValidString(ls_like))
					lcaee_return = DaoCreator.getAccEntidadExternaDAO(ldm_manager).findByNombreEntidadLike(ls_like);

				if(CollectionUtils.isValidCollection(lcaee_return))
				{
					for(AccEntidadExterna lc_temp : lcaee_return)
					{
						if(lc_temp != null)
						{
							TipoEntidad lte_tipoEntidad;

							lte_tipoEntidad = lted_tipoEntidadDAO.findById(lc_temp.getIdTipoEntidad());

							if(lte_tipoEntidad != null)
								lc_temp.setNombreTipoEntidad(lte_tipoEntidad.getNombre());

							String ls_idDepartamento;
							String ls_idMunicipio;
							String ls_idPais;

							ls_idDepartamento     = lc_temp.getIdDepartamento();
							ls_idMunicipio        = lc_temp.getIdMunicipio();
							ls_idPais             = lc_temp.getIdPais();

							if(StringUtils.isValidString(ls_idPais))
							{
								Pais lp_pais;

								lp_pais = lpd_paisDAO.findById(ls_idPais);

								if(lp_pais != null)
									lc_temp.setNombrePais(lp_pais.getNombre());

								if(StringUtils.isValidString(ls_idDepartamento))
								{
									Departamento ld_deparmento;

									ld_deparmento = new Departamento();

									ld_deparmento.setIdDepartamento(ls_idDepartamento);
									ld_deparmento.setIdPais(ls_idPais);

									ld_deparmento = ldd_departamentoDAO.findById(ld_deparmento);

									if(ld_deparmento != null)
										lc_temp.setNombreDepartamento(ld_deparmento.getNombre());

									if(StringUtils.isValidString(ls_idMunicipio))
									{
										Municipio lm_municipio;

										lm_municipio = new Municipio();

										lm_municipio.setIdDepartamento(ls_idDepartamento);
										lm_municipio.setIdPais(ls_idPais);
										lm_municipio.setIdMunicipio(ls_idMunicipio);

										lm_municipio = lmd_municipioDAO.findById(lm_municipio);

										if(lm_municipio != null)
											lc_temp.setNombreMunicipio(lm_municipio.getNombre());
									}
								}
							}

							{
								String ls_idRepresentanteLegal;

								ls_idRepresentanteLegal = lc_temp.getIdRepresentanteLegal();

								if(StringUtils.isValidString(ls_idRepresentanteLegal))
								{
									AccEntidadExternaPersona laeep_entidadExternaPersona;

									laeep_entidadExternaPersona = laeepd_entidadExternaPersonaDAO
											.findByIdEntidadExternaPersona(ls_idRepresentanteLegal);

									if(laeep_entidadExternaPersona != null)
									{
										Persona lp_persona;

										lp_persona = lpd_personaDAO.findById(
											    laeep_entidadExternaPersona.getIdPersona()
											);

										if(lp_persona != null)
											lc_temp.setNombreRepresentanteLegal(
											    lp_persona.getPrimerNombre() + " " + lp_persona.getPrimerApellido()
											);
									}
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEntidadExternaByNombreLike", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcaee_return;
	}

	/**
	 * Método encargado de buscar en la tabla la información del Estado Actividad que se desea consultar.
	 *
	 * @param as_estado Variable de tipo String que contiene el id estado actividad que se desea consultar.
	 * @return Objeto EstadoActividad resultante de la consulta.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoActividad
	 */
	public synchronized EstadoActividad findEstadoActividad(String as_estado)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		EstadoActividad lea_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lea_return      = null;

		try
		{
			if(StringUtils.isValidString(as_estado))
			{
				EstadoActividadDAO lea_DAO;

				lea_DAO        = DaoCreator.getEstadoActividadDAO(ldm_manager);
				lea_return     = new EstadoActividad();

				lea_return.setIdEstadoActividad(as_estado);

				lea_return = lea_DAO.findById(lea_return);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEstadoActividad", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lea_return;
	}

	/**
	 * Metodo de transacciones con la base de datos para encontrar todos los estado actividad que se encuentren activos.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<EstadoActividad> findEstadoActividadActivo()
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<EstadoActividad> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getEstadoActividadDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEstadoActividadActivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla
	 * SDB_PNG_ESTADO_ANOTACION.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<EstadoAnotacion> findEstadoAnotacion()
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<EstadoAnotacion> lcidt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcidt_datos     = null;

		try
		{
			lcidt_datos = DaoCreator.getEstadoAnotacionDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEstadoAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros
	 * de la tabla SDB_PNG_ESTADO_PREDIO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<EstadoPredio> findEstadoPredios()
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<EstadoPredio> lcep_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcep_datos      = null;

		try
		{
			lcep_datos = DaoCreator.getEstadoPredioDao(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEstadoPredios", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcep_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla
	 * SDB_COL_INTERESADO_NATURAL_GENERO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<InteresadoNaturalGenero> findGeneros()
	    throws B2BException
	{
		DAOManager                          ldm_manager;
		Collection<InteresadoNaturalGenero> lcidt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcidt_datos     = null;

		try
		{
			lcidt_datos = DaoCreator.getInteresadoNaturalGeneroDAO(ldm_manager).findAll(true);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findGeneros", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Método para saber si el motivo de un turno en la tabla SDB_ACC_TURNO_HISTORIA es reasignación.
	 *
	 * @param at_t objeto de clase <code>Trazabilidad</code> que contiene los parametros con los cuales se realizarán las transacciones a la BD.
	 * @return <code>String</code> lleno con el motivo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public synchronized String findIfMotivoIsReasignacion(Trazabilidad at_t)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_motivo;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_motivo       = null;

		try
		{
			ls_motivo = findIfMotivoIsReasignacion(at_t, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findIfMotivoIsReasignacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_motivo;
	}

	/**
	 * Método para saber si el motivo de un turno en la tabla SDB_ACC_TURNO_HISTORIA es reasignación.
	 *
	 * @param at_trazabilidad objeto de clase <code>Trazabilidad</code> que contiene los parametros con los cuales se realizarán las transacciones a la BD.
	 * @param adm_manager objeto de clase <code>DAOManager</code> el cual controla las transacciones con la BD.
	 * @return <code>String</code> lleno con el motivo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public synchronized String findIfMotivoIsReasignacion(Trazabilidad at_trazabilidad, DAOManager adm_manager)
	    throws B2BException
	{
		String ls_returnTipificacion;

		ls_returnTipificacion = IdentificadoresCommon.NORMAL;

		if(at_trazabilidad != null)
		{
			TurnoHistoria lt_temp;

			lt_temp = DaoCreator.getTurnoHistoriaDAO(adm_manager).findUltimoRegistroTerminadoByTurno(at_trazabilidad);

			if(lt_temp != null)
			{
				Long ls_motivo;

				ls_motivo = lt_temp.getIdMotivo();

				if(NumericUtils.isValidLong(ls_motivo))
				{
					if(NumericUtils.getLong(ls_motivo) == MotivoTramiteCommon.REASIGNACION)
						ls_returnTipificacion = IdentificadoresCommon.REASIGNADO;
					else if(NumericUtils.getLong(ls_motivo) == MotivoTramiteCommon.REASIGNACION_ESPECIAL)
					{
						String ls_idTurno;

						ls_idTurno = lt_temp.getIdTurno();

						if(StringUtils.isValidString(ls_idTurno))
						{
							Turno lt_turno;

							lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);

							if(lt_turno != null)
							{
								String ls_reasignadoEspecial;

								ls_reasignadoEspecial = lt_turno.getReasignadoEspecial();

								if(
								    StringUtils.isValidString(ls_reasignadoEspecial)
									    && ls_reasignadoEspecial.equalsIgnoreCase(EstadoCommon.S)
								)
									ls_returnTipificacion = IdentificadoresCommon.REASIGNADO_ESPECIAL;
							}
						}
					}
				}
			}
		}

		return ls_returnTipificacion;
	}

	/**
	 * Método encargado de consultar la información de las anotaciones temporales.
	 *
	 * @param lap_anotacion Objeto que contiene la información de la anotación a consultar.
	 * @return Mapa que contiene la información de la anotación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Map<String, Object> findInfoAnotacionAcc(AnotacionPredio lap_anotacion)
	    throws B2BException
	{
		Map<String, Object> lmso_return;

		lmso_return = new HashMap<String, Object>();

		if(lap_anotacion != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lmso_return = DaoCreator.getAccAnotacionPredioDAO(ldm_manager)
						                    .findByDataAnotacion(
						    lap_anotacion.getIdCirculo(), lap_anotacion.getIdMatricula(), lap_anotacion.getIdAnotacion()
						);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findInfoAnotacionAcc", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lmso_return;
	}

	/**
	 * Método encargado de consultar la información de las anotaciones definitivas.
	 *
	 * @param lap_anotacion Objeto que contiene la información de la anotación a consultar.
	 * @return Mapa que contiene la información de la anotación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Map<String, Object> findInfoAnotacionBng(AnotacionPredio lap_anotacion)
	    throws B2BException
	{
		Map<String, Object> lmso_return;

		lmso_return = new HashMap<String, Object>();

		if(lap_anotacion != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lmso_return = DaoCreator.getAnotacionPredioDAO(ldm_manager).findByDataAnotacion(lap_anotacion);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findInfoAnotacionBng", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lmso_return;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla}
	 * SDB_COL_INTERESADO_DOCUMENTO_TIPO que coincidan con un ID_DOCUMENTO_TIPO específico.
	 *
	 * @param ate_parametros tipo documento interesado a consultar
	 * @return devuelve el valor de InteresadoDocumentoTipo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see InteresadoDocumentoTipo
	 */
	public synchronized InteresadoDocumentoTipo findInteresadoDocumentoTipoById(InteresadoDocumentoTipo ate_parametros)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		InteresadoDocumentoTipo lte_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lte_datos       = null;

		try
		{
			lte_datos = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager).findById(ate_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findInteresadoDocumentoTipoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lte_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PNG_LETRA_EJE.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<LetraEje> findLetraEje()
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<LetraEje> lcle_datos;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			lcle_datos = DaoCreator.getLetraEjeDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findLetraEje", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcle_datos;
	}

	/**
	 * Método de transaccciones con la base de datos para encontar todos los registros
	 * de la tabla SDB_PGN_LIBRO_ANT_SISTEMA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<LibroAntiguoSistema> findLibroAntiguoSistema()
	    throws B2BException
	{
		DAOManager                      ldm_manager;
		Collection<LibroAntiguoSistema> lctp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctp_datos      = null;

		try
		{
			lctp_datos = DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager).findAll(true);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findLibroAntiguoSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctp_datos;
	}

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de la tabla SDB_PGN_LINEA_PRODUCCION para una etapa.
	 * @param al_etapa Argumento de tipo <code>long</code> que contiene la etapa a consultar.
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<LineaProduccion> findLineasProduccionByEtapa(long al_etapa)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<LineaProduccion> lclp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lclp_datos      = null;

		try
		{
			lclp_datos = DaoCreator.getLineaProduccionDAO(ldm_manager).findByIdEtapa(al_etapa);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findLineasProduccionByEtapa", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lclp_datos;
	}

	/**
	 * Método de transaciones con la base de datos con el fin de encontar todos los registros
	 * de la tabla SDB_PGN_LINEA_PRODUCCION para una nomenclatura.
	 * @param as_nomenclatura Argumento de tipo <code>String</code> que contiene la nomenclatura a consultar.
	 * @return devuelve el valor de la linea de producción
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized LineaProduccion findLineasProduccionByNomenclatura(String as_nomenclatura)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		LineaProduccion llp_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		llp_return      = null;

		try
		{
			llp_return = DaoCreator.getLineaProduccionDAO(ldm_manager).findByNomenclatura(as_nomenclatura);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findLineasProduccionByNomenclatura", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return llp_return;
	}

	/**
	 * Método para encontrar matricula DEFINITIVA de una temporal.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto PredioSegregado
	 * @return devuelve el valor de PredioSegregado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioSegregado
	 */
	public synchronized PredioSegregado findMatriculaDefinitiva(PredioSegregado as_s)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		PredioSegregado lps_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lps_datos       = null;

		try
		{
			lps_datos = DaoCreator.getAccPredioSegregadoDAO(ldm_manager).findMatriculaDefinitiva(as_s);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMatriculaDefinitiva", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lps_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_arg correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<MedidaArea> findMedidaAreaById(String as_arg)
	    throws B2BException
	{
		Collection<MedidaArea> lcma_return;

		lcma_return = null;

		if(StringUtils.isValidString(as_arg))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				MedidaArea lma_medidaArea;

				lma_medidaArea = DaoCreator.getMedidaAreaDAO(ldm_manager).findById(as_arg);

				if(lma_medidaArea != null)
				{
					lcma_return = new ArrayList<MedidaArea>();

					lcma_return.add(lma_medidaArea);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findMedidaAreaById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcma_return;
	}

	/**
	 * Retorna el valor del objeto de Collection de motivos tramite por su id proceso, id subproceso y id etapa.
	 *
	 * @param as_idProceso Objeto de tipo <code>String</code> que indica el id del proceso.
	 * @param as_idSubProceso Objeto de tipo <code>String</code> que indica el id del subproceso.
	 * @param al_idEtapa Parametro de tipo <code>long</code> que indica el id de la etapa.
	 * @return Colección de tipo <code>Plantilla</code> que contiene los resultados de la búsqueda.
	 * @throws B2BException Objeto de tipo <code>B2BException</code>, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<MotivoTramite> findMotivoByIdProcesoIdSubProcesoAndIdEtapa(
	    String as_idProceso, String as_idSubProceso, long al_idEtapa
	)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<MotivoTramite> lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getMotivoTramiteDAO(ldm_manager)
					                .findByIdProcesoIdSubProcesoAndIdEtapa(as_idProceso, as_idSubProceso, al_idEtapa);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMotivoByIdProcesoIdSubProcesoAndIdEtapa", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PGN_MOTIVO_TRAMITE
	 * que coincidan con un ID_ETAPA específico.
	 *
	 * @param as_etapa es el id_etapa para realizar la consulata en la base de datos
	 * @param as_idTurnoHistoria es el id_turno historia para realizar la consulta en la base de datos
	 * @param ab_isRepConstancia correspondiente al valor del tipo de objeto boolean
	 * @param ab_isCalificacion correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<MotivoTramite> findMotivosByEtapa(
	    String as_etapa, String as_idTurnoHistoria, boolean ab_isRepConstancia, boolean ab_isCalificacion
	)
	    throws B2BException
	{
		return findMotivosByEtapa(as_etapa, as_idTurnoHistoria, ab_isRepConstancia, ab_isCalificacion, null);
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PGN_MOTIVO_TRAMITE
	 * que coincidan con un ID_ETAPA específico.
	 *
	 * @param as_etapa es el id_etapa para realizar la consulata en la base de datos
	 * @param as_idTurnoHistoria es el id_turno historia para realizar la consulta en la base de datos
	 * @param ab_isRepConstancia correspondiente al valor del tipo de objeto boolean
	 * @param ab_isCalificacion correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<MotivoTramite> findMotivosByEtapa(
	    String as_etapa, String as_idTurnoHistoria, boolean ab_isRepConstancia, boolean ab_isCalificacion,
	    List<Map<String, Object>> lmso_actos
	)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<MotivoTramite> lcr_datos;
		String                    ls_accion;
		boolean                   lb_acto0463;
		boolean                   lb_cancelacion;
		ls_accion     = null;

		ldm_manager     = DaoManagerFactory.getDAOManager();

		lb_acto0463        = false;
		lb_cancelacion     = false;

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoria loth_th;

				loth_th = new TurnoHistoria();

				loth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				loth_th = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(loth_th);

				if(loth_th != null)
				{
					String  ls_idProceso;
					String  ls_idSubProceso;
					boolean lb_idProceso;
					boolean lb_idSubProceso;

					{
						Solicitud los_tmp;

						los_tmp = new Solicitud();

						los_tmp.setIdSolicitud(loth_th.getIdSolicitud());

						los_tmp     = DaoCreator.getSolicitudDAO(ldm_manager).findById(los_tmp);

						ls_idProceso        = (los_tmp != null) ? los_tmp.getIdProceso() : null;
						ls_idSubProceso     = (los_tmp != null) ? los_tmp.getIdSubproceso() : null;
					}

					lb_idProceso        = StringUtils.isValidString(ls_idProceso);
					lb_idSubProceso     = StringUtils.isValidString(ls_idSubProceso);

					if(
					    lb_idProceso && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6) && lb_idSubProceso
						    && ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.REPRODUCCION_CONSTANCIA)
					)
						ls_accion = IdentificadoresCommon.REPRODUCCION_CONSTANCIA;
					else if(
					    lb_idProceso && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
						    && ((lb_idSubProceso
						    && ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_CARENCIA_REGISTRAL))
						    || (lb_idSubProceso
						    && ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_ANTIGUO_SISTEMA)))
					)
					{
						TurnoHistoria lth_turnoHistoriaAnterior;

						lth_turnoHistoriaAnterior = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdAnterior(
							    loth_th
							);

						if(lth_turnoHistoriaAnterior != null)
						{
							if(
							    !(lth_turnoHistoriaAnterior.getIdEtapa()
								                               .equals(
								        NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_APROBACION_ANTIGUO_SISTEMA)
								    ))
							)
								ls_accion = IdentificadoresCommon.CERTIFICADO_ANTIGUO_SISTEMA;
						}
					}
					else if(
					    lb_idProceso
						    && (ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3)
						    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4))
					)
					{
						long ll_idEtapa;

						ll_idEtapa = NumericUtils.getLong(as_etapa);

						if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS)
							ls_accion = IdentificadoresCommon.ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS;
					}
					else
					{
						TurnoHistoria lth_turnoHistoriaAnterior;
						lth_turnoHistoriaAnterior = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdAnterior(
							    loth_th
							);

						if(lth_turnoHistoriaAnterior != null)
						{
							if(
							    lth_turnoHistoriaAnterior.getIdEtapa()
								                             .equals(
								        NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_MAYOR_VALOR_VENCIDO)
								    )
								    && lth_turnoHistoriaAnterior.getIdMotivo()
								                                    .equals(
								        NumericUtils.getLongWrapper(MotivoTramiteCommon.CONFRONTACION_CORRECTIVA)
								    )
							)
								ls_accion = IdentificadoresCommon.MAYOR_VALOR_VENCIDO;
						}
					}
				}

				if(as_etapa.equals("80") && ab_isCalificacion)
				{
					SolicitudMatriculaActo    lsm_solicitudMatricula;
					SolicitudMatriculaActoDAO ls_dao;
					ls_dao                     = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);
					lsm_solicitudMatricula     = new SolicitudMatriculaActo();
					lsm_solicitudMatricula.setIdTurno(loth_th.getIdTurno());
					lsm_solicitudMatricula = ls_dao.findByIdTurno(lsm_solicitudMatricula);

					if(lsm_solicitudMatricula != null)
					{
						AnotacionPredioDAO    lap_dao = DaoCreator.getAnotacionPredioDAO(ldm_manager);
						NaturalezaJuridicaDAO lnj_dao = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);

						Collection<AnotacionPredio> lap_lap;
						lap_lap = lap_dao.findByCirculoMatricula(
							    lsm_solicitudMatricula.getIdCirculo(),
							    NumericUtils.getLongWrapper(lsm_solicitudMatricula.getIdMatricula()), true
							);

						if(CollectionUtils.isValidCollection(lap_lap))
						{
							for(AnotacionPredio lap_iterador : lap_lap)
							{
								if(lap_iterador != null)
								{
									String ls_idNaturaleza;
									ls_idNaturaleza = lap_iterador.getIdNaturalezaJuridica();

									String ls_anotacionCancelada;
									ls_anotacionCancelada = lap_iterador.getAnotacionCancelada();

									if(
									    StringUtils.isValidString(ls_idNaturaleza)
										    && StringUtils.isValidString(ls_anotacionCancelada)
										    && ls_idNaturaleza.equalsIgnoreCase("0463")
										    && ls_anotacionCancelada.equals("N")
									)
										lb_acto0463 = true;
								}
							}

							if(CollectionUtils.isValidCollection(lmso_actos))
							{
								for(Map<String, Object> lm_tmp : lmso_actos)
								{
									if(lm_tmp != null)
									{
										String ls_idTipoActo;
										ls_idTipoActo = StringUtils.getString(lm_tmp.get("ID_TIPO_ACTO"));

										if(StringUtils.isValidString(ls_idTipoActo))
										{
											NaturalezaJuridica lnj_naturalezaJuridica;
											lnj_naturalezaJuridica = lnj_dao.findByIdMaxVersion(ls_idTipoActo);

											if(lnj_naturalezaJuridica != null)
											{
												String ls_grupoNat;
												ls_grupoNat = lnj_naturalezaJuridica.getIdGrupoNatJur();

												if(StringUtils.isValidString(ls_grupoNat) && ls_grupoNat.equals("0700"))
													lb_cancelacion = true;
											}
										}
									}
								}
							}
						}
					}
				}
			}

			lcr_datos = DaoCreator.getMotivoTramiteDAO(ldm_manager)
					                  .findMotivosByEtapa(as_etapa, ls_accion, ab_isRepConstancia, ab_isCalificacion);

			Collection<MotivoTramite> lcr_datosCancelacion;
			lcr_datosCancelacion = new LinkedList<MotivoTramite>();

			if(lb_acto0463 && !lb_cancelacion)
			{
				if(CollectionUtils.isValidCollection(lcr_datos))
				{
					for(MotivoTramite imt_iterador : lcr_datos)
					{
						if(imt_iterador != null)
						{
							String ls_idMotivo = StringUtils.getString(imt_iterador.getIdMotivo());

							if(StringUtils.isValidString(ls_idMotivo) && ls_idMotivo.equals("60"))
								lcr_datosCancelacion.add(imt_iterador);
						}
					}

					lcr_datos = lcr_datosCancelacion;
				}
			}
			else if(lb_acto0463 && lb_cancelacion)
			{
				if(CollectionUtils.isValidCollection(lcr_datos))
				{
					for(MotivoTramite imt_iterador : lcr_datos)
					{
						if(imt_iterador != null)
						{
							String ls_idMotivo = StringUtils.getString(imt_iterador.getIdMotivo());

							if(StringUtils.isValidString(ls_idMotivo) && ls_idMotivo.equals("70"))
								lcr_datosCancelacion.add(imt_iterador);
						}
					}

					lcr_datos = lcr_datosCancelacion;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMotivosByEtapa", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PGN_MOTIVO_TRAMITE
	 * que coincidan con un ID_ETAPA específico.
	 *
	 * @param as_etapa es el id_etapa para realizar la consulata en la base de datos
	 * @param as_idTurnoHistoria es el id_turno historia para realizar la consulta en la base de datos
	 * @param ab_isRepConstancia correspondiente al valor del tipo de objeto boolean
	 * @param ab_isCalificacion correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<MotivoTramite> findMotivosByEtapa105(
	    String as_etapa, String as_idTurnoHistoria, boolean ab_isRepConstancia, boolean ab_isCalificacion
	)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<MotivoTramite> lcr_datos;
		String                    ls_accion;
		ls_accion     = null;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_datos       = new LinkedList<MotivoTramite>();

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoria loth_th;

				loth_th = new TurnoHistoria();

				loth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				loth_th = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(loth_th);

				if(loth_th != null)
				{
					Collection<MotivoTramite> lcc_datos;
					lcc_datos = DaoCreator.getMotivoTramiteDAO(ldm_manager)
							                  .findMotivosByEtapa(as_etapa, ls_accion, false, true);

					TurnoHistoria lth_turnoHistoriaAnterior;
					lth_turnoHistoriaAnterior = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdAnterior(loth_th);

					if(CollectionUtils.isValidCollection(lcc_datos))
					{
						for(MotivoTramite imt_motivoTramite : lcc_datos)
						{
							if(imt_motivoTramite != null)
							{
								long ls_idMotivo;
								ls_idMotivo = imt_motivoTramite.getIdMotivo();

								if(
								    (lth_turnoHistoriaAnterior != null)
									    && (ls_idMotivo == MotivoTramiteCommon.NEGAR_SOLICITUD_CREACION_MATRICULA_OFICIO)
								)
								{
									if(
									    lth_turnoHistoriaAnterior.getIdEtapa()
										                             .equals(
										        NumericUtils.getBigDecimal(
										            EtapaCommon.ID_ETAPA_APROBACION_ANTIGUO_SISTEMA
										        )
										    )
										    && lth_turnoHistoriaAnterior.getIdMotivo()
										                                    .equals(
										        NumericUtils.getLongWrapper(MotivoTramiteCommon.DEVOLVER_A_ETAPA_105)
										    )
									)
										lcr_datos.add(imt_motivoTramite);
								}
								else
									lcr_datos.add(imt_motivoTramite);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMotivosByEtapa105", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_MUNICIPIO
	 * que coincidadn con un ID_DEPARTAMENTO específico.
	 *
	 * @param am_parametros objeto del cual se extrae el id para realizar la consulta en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Municipio> findMunicipios(Municipio am_parametros)
	    throws B2BException
	{
		DAOManager            ldm_manager;
		Collection<Municipio> lcm_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcm_datos       = null;

		try
		{
			lcm_datos = DaoCreator.getMunicipioDAO(ldm_manager).findAllByDepartament(am_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMunicipios", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcm_datos;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_MUNICIPIO
	 * que pertenezcan a un circulo registral.
	 *
	 * @param as_parametros con el id del circulo para realizar la consulta en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Municipio> findMunicipiosByCirculo(String as_parametros)
	    throws B2BException
	{
		DAOManager            ldm_manager;
		Collection<Municipio> lcm_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcm_datos       = null;

		try
		{
			lcm_datos = DaoCreator.getMunicipioDAO(ldm_manager).findMunicipiosByCirculo(as_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMunicipiosByCirculo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcm_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar la maxíma version
	 * de la tabla SDB_PNG_NATURALEZA_JURIDICA unicamente coleccion de actos 0841 y 0842.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<NaturalezaJuridica> findNaturalezaJuridica0841and0842()
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<NaturalezaJuridica> lcnj_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcnj_datos      = null;

		try
		{
			lcnj_datos = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager).findAllVersion(false);

			if(CollectionUtils.isValidCollection(lcnj_datos))
			{
				String li_idNaturalezaJuridica;

				Collection<NaturalezaJuridica> lcnj_datosFiltrados;
				lcnj_datosFiltrados = new ArrayList<NaturalezaJuridica>();

				for(NaturalezaJuridica lnj_tmp : lcnj_datos)
				{
					if(lnj_tmp != null)
					{
						li_idNaturalezaJuridica = lnj_tmp.getIdNaturalezaJuridica();

						if(StringUtils.isValidString(li_idNaturalezaJuridica))
						{
							if(
							    li_idNaturalezaJuridica.equalsIgnoreCase("0841")
								    || li_idNaturalezaJuridica.equalsIgnoreCase("0842")
							)
								lcnj_datosFiltrados.add(lnj_tmp);
						}
					}
				}

				if(CollectionUtils.isValidCollection(lcnj_datosFiltrados))
					lcnj_datos = lcnj_datosFiltrados;
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllVersion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcnj_datos;
	}

	/**
	 * Retorna el valor del objeto de NaturalezaJuridica.
	 *
	 * @param as_idNaturalezJuridica correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de NaturalezaJuridica
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see NaturalezaJuridica
	 */
	public synchronized NaturalezaJuridica findNaturalezaJuridicaById(String as_idNaturalezJuridica)
	    throws B2BException
	{
		NaturalezaJuridica lcm_datos;

		lcm_datos = null;

		if(StringUtils.isValidString(as_idNaturalezJuridica))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				NaturalezaJuridica lnj_arg;

				lnj_arg = new NaturalezaJuridica();

				lnj_arg.setIdNaturalezaJuridica(as_idNaturalezJuridica);

				lcm_datos = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager).findByIdMaxVersion(lnj_arg);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("NaturalezaJuridica", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcm_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar SDB_PGN_OFICINA_ORIGEN
	 * que coincidan con ID_TIPO_OFICINA, ID_PAIS,ID_DEPARTAMENTO y ID_MUNICIPIO específicos.
	 *
	 * @param aoo_oficina objeto del cual se extrae los datos para realizar la consulta en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<OficinaOrigen> findOficinaOrigen(OficinaOrigen aoo_oficina)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<OficinaOrigen> lcidt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcidt_datos     = null;

		try
		{
			lcidt_datos = DaoCreator.getOficinaOrigenDAO(ldm_manager).findByFilters(aoo_oficina);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findOficinaOrigen", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Retorna el valor del objeto de OficinaOrigen.
	 *
	 * @param as_idOficinaOrigen correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de OficinaOrigen
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see OficinaOrigen
	 */
	public synchronized OficinaOrigen findOficinaOrigenById(String as_idOficinaOrigen)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		OficinaOrigen loo_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		loo_return      = null;

		try
		{
			if(StringUtils.isValidString(as_idOficinaOrigen))
			{
				loo_return = new OficinaOrigen();

				loo_return.setIdOficinaOrigen(as_idOficinaOrigen);
				loo_return.setVersion(obtenerVersionMaximaOficinaOrigen(as_idOficinaOrigen, ldm_manager));

				loo_return = DaoCreator.getOficinaOrigenDAO(ldm_manager).findById(loo_return);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findOficinaOrigenById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return loo_return;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la
	 * tabla SDB_PGN_PAIS.
	 *
	 * @param ab_activo indica si se desean traer los registros donde el ACTIVO='S'
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Pais> findPaises(boolean ab_activo)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		Collection<Pais> lcp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcp_datos       = null;

		try
		{
			lcp_datos = DaoCreator.getPaisDAO(ldm_manager).findAll(ab_activo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPaises", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcp_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros SDB_BNG_PREDIO_REGISTRO
	 * que coincidan con un ID_CIRCULO y un ID_MATRICULA específico.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<PredioRegistro> findPredioRegistroAll()
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<PredioRegistro> lcr_datos;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			lcr_datos = DaoCreator.getPredioRegistroDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPredioRegistroAll", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla
	 * SDB_BNG_PREDIO_REGISTRO cuando se tiene un id circulo y id matricula
	 *
	 * @param as_idCirculo Id del círculo a utilizar como filtro en la consulta
	 * @param al_idMatricula Id de la matrícula a utilizar como filtro en la consulta
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized PredioRegistro findPredioRegistroByMatriculaCirculo(String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		PredioRegistro lpr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lpr_datos       = null;

		try
		{
			if(NumericUtils.isValidLong(al_idMatricula) && StringUtils.isValidString(as_idCirculo))
				lpr_datos = DaoCreator.getPredioRegistroDAO(ldm_manager)
						                  .findByFolioMatricula(as_idCirculo, NumericUtils.getLong(al_idMatricula));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPredioRegistroByMatriculaCirculo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lpr_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_PROCESO.
	 *
	 * @param ab_b variable de tipo boolean para indicar la sentencia SQL a utilizar.
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Proceso> findProcesos(boolean ab_b)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<Proceso> lcp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcp_datos       = null;

		try
		{
			lcp_datos = DaoCreator.getProcesoDAO(ldm_manager).findAll(ab_b);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findProcesos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcp_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_BITACORA_PROCESO.
	 *
	 * @param ab_activo indica si se deben traer los registros con el campo ACTIVO = 'S'
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<ProcesoAutomatico> findProcesosAutomaticos(boolean ab_activo)
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<ProcesoAutomatico> lcpa_datos;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			lcpa_datos = DaoCreator.getProcesoAutomaticoDAO(ldm_manager).findAll(ab_activo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findProcesosAutomaticos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcpa_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_PGN_REGIONAL
	 * que coincida con un ID_REGIONAL específico.
	 *
	 * @param ar_parametro es el objeto del cual se extrae  el id para realizar la consulta en la base de datos
	 * @return devuelve el valor de Regional
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Regional
	 */
	public synchronized Regional findRegionalById(Regional ar_parametro)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Regional   lr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lr_datos        = null;

		try
		{
			lr_datos = DaoCreator.getRegionalDAO(ldm_manager).findById(ar_parametro);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findRegionalById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la
	 * tabla SDB_PGN_CAMPOS_CONSULTA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<ResultadoConsulta> findResultadoConsulta()
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<ResultadoConsulta> lcrc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcrc_datos      = null;

		try
		{
			lcrc_datos = DaoCreator.getResultadoConsultaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findResultadoConsulta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcrc_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar los derechos de responsabilidades y restricciones.
	 *
	 * @param as_idActo es el id acto para realizar la consulta en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<InteresadoDocumentoTipo> findRrr(String as_idActo)
	    throws B2BException
	{
		DAOManager                          ldm_manager;
		Collection<InteresadoDocumentoTipo> lcidt_datos;
		NaturalezaJuridica                  lnj_naturalezaJuridica;
		long                                ll_maxVersion;

		ldm_manager                = DaoManagerFactory.getDAOManager();
		lcidt_datos                = null;
		lnj_naturalezaJuridica     = new NaturalezaJuridica();
		lnj_naturalezaJuridica.setIdNaturalezaJuridica(as_idActo);
		ll_maxVersion = 1;

		try
		{
			lnj_naturalezaJuridica = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager)
					                               .findByIdMaxVersion(lnj_naturalezaJuridica);

			if(lnj_naturalezaJuridica != null)
			{
				ll_maxVersion     = lnj_naturalezaJuridica.getVersion();
				lcidt_datos       = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager)
						                          .findRrr(as_idActo, ll_maxVersion);
			}
			else
				lcidt_datos = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findRrr", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Método de transacciones con la base de datos  para encontrar los derechos de responsabilidades y restricciones
	 *  a partir de un ID_DOCUMENTO
	 * específico.
	 *
	 * @param as_idDoc correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de InteresadoDocumentoTipo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see InteresadoDocumentoTipo
	 */
	public synchronized InteresadoDocumentoTipo findRrrById(String as_idDoc)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		InteresadoDocumentoTipo lcidt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcidt_datos     = null;

		try
		{
			lcidt_datos = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager).findByIdInteresadoRRR(as_idDoc);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findRrrById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Método encargado de consultar la solicitud con base a su id.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @return Objeto que contiene la información del turno historia.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TurnoHistoria
	 */
	public synchronized Solicitud findSolicitudById(String as_idSolicitud)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Solicitud  ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = null;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
				ls_return = DaoCreator.getSolicitudDAO(ldm_manager).findById(as_idSolicitud);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSolicitudById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_SUBPROCESO
	 * cuando el campo  ACTIVO = 'S'.
	 *
	 * @param ab_activo de ab activo
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<Subproceso> findSubprocesos(boolean ab_activo)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Subproceso> lcp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcp_datos       = null;

		try
		{
			lcp_datos = DaoCreator.getSubprocesoDAO(ldm_manager).findAll(ab_activo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSubprocesos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcp_datos;
	}

	/**
	 * Consulta todos los subprocesos que se encuentren activos y los filtra por
	 * un id proceso previamente definido.
	 *
	 * @param as_subproceso objeto que contiene el id_proceso para aplicar al filtro
	 * @return Colección de Subprocesos resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Subproceso> findSubprocesosActivosByProceso(Subproceso as_subproceso)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Subproceso> lcp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcp_datos       = null;

		try
		{
			lcp_datos = DaoCreator.getSubprocesoDAO(ldm_manager).findActivoByProceso(as_subproceso);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSubprocesosActivosByProceso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcp_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_ACC_SUBPROCESO
	 * que coincida con un ID_PROCESO específico.
	 *
	 * @param as_subproceso objeto de donde se extrae el ID_PROCESO para realizar la consulta en la base de datos
	 * @param ab_orden objeto de donde se extrae el condicional para realizar la consulta en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Subproceso> findSubprocesosByProceso(Subproceso as_subproceso, boolean ab_orden)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Subproceso> lcp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcp_datos       = null;

		if(as_subproceso != null)
		{
			try
			{
				lcp_datos = DaoCreator.getSubprocesoDAO(ldm_manager).findByProceso(as_subproceso, ab_orden);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findSubprocesosByProceso", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcp_datos;
	}

	/**
	 * Consulta todos los registro en la tabla relacionados a un proceso determinado y que se encuentren con un estado
	 * de solicitud certificado específico.
	 *
	 * @param as_subproceso Objeto contenedor de los filtros a utilizar en la consulta consulta
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Subproceso> findSubprocesosByProcesoSolicitudCert(Subproceso as_subproceso)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Subproceso> lcp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcp_datos       = null;

		try
		{
			lcp_datos = DaoCreator.getSubprocesoDAO(ldm_manager).findByProcesoSolicitudCert(as_subproceso);

			if(CollectionUtils.isValidCollection(lcp_datos))
			{
				Collection<Subproceso> lcp_temp;

				lcp_temp = new ArrayList<Subproceso>();

				lcp_temp.addAll(lcp_datos);

				for(Subproceso ls_iterador : lcp_temp)
				{
					if(ls_iterador != null)
					{
						String ls_idSubproceso;

						ls_idSubproceso = ls_iterador.getIdSubproceso();

						if(
						    StringUtils.isValidString(ls_idSubproceso)
							    && (ls_idSubproceso.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_MEDIOS_ELECTRONICOS)
							    || ls_idSubproceso.equalsIgnoreCase(
							        SubProcesoCommon.CERTIFICADO_PREDIAL_REGISTRA_SEDE_ELECTRONICA
							    ))
						)
							lcp_datos.remove(ls_iterador);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSubprocesosByProcesoSolicitudCert", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcp_datos;
	}

	/**
	 * Find tipificacion turno.
	 *
	 * @param as_idTurno de as id turno
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String findTipificacionTurno(String as_idTurno)
	    throws B2BException
	{
		String ls_returnTipificacion;

		ls_returnTipificacion = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				ls_returnTipificacion = findTipificacionTurno(as_idTurno, ldm_manager);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findTipificacionTurno", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ls_returnTipificacion;
	}

	/**
	 * Find tipificacion turno.
	 *
	 * @param as_idTurno de as id turno
	 * @param adm_manager de adm manager
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String findTipificacionTurno(String as_idTurno, DAOManager adm_manager)
	    throws B2BException
	{
		String ls_returnTipificacion;

		ls_returnTipificacion = IdentificadoresCommon.NORMAL;

		if(StringUtils.isValidString(as_idTurno))
		{
			TurnoHistoriaDAO lth_DAO;

			lth_DAO = DaoCreator.getTurnoHistoriaDAO(adm_manager);

			{
				TurnoHistoria lt_temp;

				lt_temp = lth_DAO.findUltimoRegistroTerminadoByTurno(as_idTurno);

				if(lt_temp != null)
				{
					Long ls_motivo;

					ls_motivo = lt_temp.getIdMotivo();

					if(
					    NumericUtils.isValidLong(ls_motivo)
						    && (NumericUtils.getLong(ls_motivo) == MotivoTramiteCommon.REASIGNACION)
					)
						ls_returnTipificacion = IdentificadoresCommon.REASIGNADO;
				}
			}

			{
				Collection<TurnoHistoria> lcth_cllturnoHistoria;

				lcth_cllturnoHistoria = lth_DAO.findAllByIdTurno(as_idTurno);

				if(CollectionUtils.isValidCollection(lcth_cllturnoHistoria))
				{
					TurnoDAO lt_DAO;
					boolean  lb_reasignadoEspecialEncontrado;

					lt_DAO                              = DaoCreator.getTurnoDAO(adm_manager);
					lb_reasignadoEspecialEncontrado     = false;

					for(TurnoHistoria lth_tmp : lcth_cllturnoHistoria)
					{
						if((lth_tmp != null) && !lb_reasignadoEspecialEncontrado)
						{
							Long ls_motivo;

							ls_motivo = lth_tmp.getIdMotivo();

							if(
							    NumericUtils.isValidLong(ls_motivo)
								    && (NumericUtils.getLong(ls_motivo) == MotivoTramiteCommon.REASIGNACION_ESPECIAL)
							)
							{
								String ls_idTurno;

								ls_idTurno = lth_tmp.getIdTurno();

								if(StringUtils.isValidString(ls_idTurno))
								{
									Turno lt_turno;

									lt_turno = lt_DAO.findById(ls_idTurno);

									if(lt_turno != null)
									{
										String ls_reasignadoEspecial;

										ls_reasignadoEspecial = lt_turno.getReasignadoEspecial();

										if(
										    StringUtils.isValidString(ls_reasignadoEspecial)
											    && ls_reasignadoEspecial.equalsIgnoreCase(EstadoCommon.S)
										)
										{
											ls_returnTipificacion               = IdentificadoresCommon.REASIGNADO_ESPECIAL;
											lb_reasignadoEspecialEncontrado     = true;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return ls_returnTipificacion;
	}

	/**
	 * Método para buscar todos los tipos actos basaddos en su ID.
	 *
	 * @param as_arg de as arg
	 * @return una coleccion de tipo acto con la consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TipoActo> findTipoActoById(String as_arg)
	    throws B2BException
	{
		Collection<TipoActo> lcta_return;

		lcta_return = null;

		if(StringUtils.isValidString(as_arg))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				TipoActo lta_tipoActo;

				lta_tipoActo = DaoCreator.getTipoActoDAO(ldm_manager).findTipoActoById(as_arg);

				if(lta_tipoActo != null)
				{
					lcta_return = new ArrayList<TipoActo>();

					lcta_return.add(lta_tipoActo);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findTipoActoById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcta_return;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros
	 * que correspondan a un valor del campo ACTIVO específico.
	 *
	 * @param as_activo Determina el valor a encontrar del campo ACTIVO
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoAdquisicion> findTipoAdquisicion(String as_activo)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<TipoAdquisicion> lcidt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcidt_datos     = null;

		try
		{
			lcidt_datos = DaoCreator.getTipoAdquisicionDAO(ldm_manager).findAll(as_activo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoAdquisicion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Método para buscar todos los tipos Adquisicion basaddos en su ID.
	 *
	 * @param as_arg de as arg
	 * @return una coleccion de tipo acto con la consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoAdquisicion findTipoAdquisicionById(String as_arg)
	    throws B2BException
	{
		TipoAdquisicion lcta_return;

		lcta_return = null;

		if(StringUtils.isValidString(as_arg))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lcta_return = DaoCreator.getTipoAdquisicionDAO(ldm_manager).findById(as_arg);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findTipoAdquisicionById", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcta_return;
	}

	/**
	 * Método de transaccciones con la base de datos para encontrar todos los registros de
	 * la tabla SDB_COL_INTERESADO_DOCUMENTO_TIPO donde el campo ACTIVO = 'S'.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<InteresadoDocumentoTipo> findTipoDocumento()
	    throws B2BException
	{
		DAOManager                          ldm_manager;
		Collection<InteresadoDocumentoTipo> lcidt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcidt_datos     = null;

		try
		{
			lcidt_datos = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoDocumento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Consulta en la base de datos todos los registros de tipos de documento
	 * que se encuentren activos.
	 *
	 * @return Colección de InteresadoDocumentoTipo con todos los registros
	 * que retorno la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<InteresadoDocumentoTipo> findTipoDocumentoActivo()
	    throws B2BException
	{
		DAOManager                          ldm_manager;
		Collection<InteresadoDocumentoTipo> lcidt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcidt_datos     = null;

		try
		{
			lcidt_datos = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoDocumentoActivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontar todos los registros
	 * de la tabla SDB_PGN_TIPO_DOCUMENTO_PUBLICO donde el campo ACTIVO = 'S'.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoDocumentoPublico> findTipoDocumentoPublico()
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		Collection<TipoDocumentoPublico> lcidt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcidt_datos     = null;

		try
		{
			lcidt_datos = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoDocumentoPublico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_activo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoDocumentoPublico> findTipoDocumentoPublico(String as_activo)
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		Collection<TipoDocumentoPublico> lcidt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcidt_datos     = null;

		try
		{
			lcidt_datos = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager).findAll(as_activo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoDocumentoPublico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla
	 * SDB_PNG_TIPO_EJE donde el campo ACTIVO = 'S'.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoEje> findTipoEje()
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<TipoEje> lcte_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcte_datos      = null;

		try
		{
			lcte_datos = DaoCreator.getTipoEjeDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoEje", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcte_datos;
	}

	/**
	 * Método de transacciones con la base de datos para buscar todos los registros de la tabla SDB_PNG_TIPO_EJE
	 * que coincida con un ID_TIPO_EJE específico.
	 *
	 * @param ate_parametros objeto de donde se extrae el ID_TIPO_EJE para realizar la consulta
	 * @return devuelve el valor de TipoEje
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoEje
	 */
	public synchronized TipoEje findTipoEjeById(TipoEje ate_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		TipoEje    lte_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lte_datos       = null;

		try
		{
			lte_datos = DaoCreator.getTipoEjeDAO(ldm_manager).findById(ate_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoEjeById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lte_datos;
	}

	/**
	 * Consulta todos los registros de la tabla que tengan un estado activo, tengan complemento en S, sean de tipo predio Mixto y tipo predio definido por el usuario.
	 *
	 * @param as_tipoPredio Variable de tipo String que contiene el id tipo predio.
	 * @return Colección con todos los registros resultantes de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TipoEje> findTipoEjeByTipoPredio(String as_tipoPredio)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<TipoEje> lcte_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcte_datos      = null;

		try
		{
			if(!StringUtils.isValidString(as_tipoPredio))
				lcte_datos = DaoCreator.getTipoEjeDAO(ldm_manager).findAllActivoMixto();
			else
				lcte_datos = DaoCreator.getTipoEjeDAO(ldm_manager).findAllByTipoPredio(as_tipoPredio);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoEjeByTipoPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcte_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los tipo oficina existentes.
	 *
	 * @param ad_od correspondiente al valor del tipo de objeto Documento
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoEntidad> findTipoEntidad(Apertura ad_od)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<TipoEntidad> lcto_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcto_datos      = new ArrayList<TipoEntidad>();

		try
		{
			if(ad_od != null)
			{
				TipoEntidadDAO lted_DAO;
				String         ls_oficinaOrigen;

				lted_DAO             = DaoCreator.getTipoEntidadDAO(ldm_manager);
				ls_oficinaOrigen     = ad_od.getIdTipoEntidad();

				if(StringUtils.isValidString(ls_oficinaOrigen))
				{
					String[] las_tipoEntidad;

					las_tipoEntidad = ls_oficinaOrigen.split(",");

					if(CollectionUtils.isValidCollection(las_tipoEntidad))
					{
						for(String ls_tmp : las_tipoEntidad)
						{
							if(StringUtils.isValidString(ls_tmp))
							{
								TipoEntidad lto_tmp;

								lto_tmp = new TipoEntidad();

								lto_tmp.setIdTipoEntidad(ls_tmp);

								lto_tmp = lted_DAO.findById(lto_tmp);

								if(lto_tmp != null)
									lcto_datos.add(lto_tmp);
							}
						}
					}
				}
				else if(StringUtils.isValidString(ad_od.getIdTipoOficina()))
				{
					TipoOficinaDAO ltod_DAO;
					String         ls_idTipoOficina;

					ltod_DAO             = DaoCreator.getTipoOficinaDAO(ldm_manager);
					ls_idTipoOficina     = ad_od.getIdTipoOficina();

					if(StringUtils.isValidString(ls_idTipoOficina))
					{
						String[] las_tiposOficina;

						las_tiposOficina = ls_idTipoOficina.split(",");

						if(CollectionUtils.isValidCollection(las_tiposOficina))
						{
							for(String ls_tmp : las_tiposOficina)
							{
								if(StringUtils.isValidString(ls_tmp))
								{
									TipoOficina lto_tmp;

									lto_tmp = new TipoOficina();

									lto_tmp.setIdTipoOficina(ls_tmp);

									lto_tmp = ltod_DAO.findById(lto_tmp);

									if(lto_tmp != null)
									{
										TipoEntidad lte_tmp;

										lte_tmp = new TipoEntidad();

										lte_tmp.setIdTipoEntidad(lto_tmp.getIdTipoEntidad());

										lte_tmp = lted_DAO.findById(lte_tmp);

										lcto_datos.add(lte_tmp);
									}
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoEntidad", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcto_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla
	 * SDB_PGN_TIPO_ENTIDAD.
	 *
	 * @param ab_activo boolean que indica si debe traer únicamente los activos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<TipoEntidad> findTipoEntidad(boolean ab_activo)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<TipoEntidad> lcidt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcidt_datos     = null;

		try
		{
			lcidt_datos = DaoCreator.getTipoEntidadDAO(ldm_manager).findAll(ab_activo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoEntidad", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Metodo encargado de consultar el tipo entidad por el id de la tabla SDB_PGN_TIPO_ENTIDAD.
	 *
	 * @param ate_te correspondiente al valor del tipo de objeto TipoEntidad
	 * @return TipoEntidad
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoEntidad
	 */
	public synchronized TipoEntidad findTipoEntidadById(TipoEntidad ate_te)
	    throws B2BException
	{
		DAOManager  ldm_manager;
		TipoEntidad lte_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lte_datos       = null;

		try
		{
			lte_datos = DaoCreator.getTipoEntidadDAO(ldm_manager).findById(ate_te);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoEntidadById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lte_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection de AccEntidadExterna con base en el id tipo entidad.
	 *
	 * @param as_idTipoEntidad Variable que contiene el id tipo entidad.
	 * @param ab_orden Variable que el orden de la collection.
	 * @param ab_activo Variable que el activo de la collection.
	 * @return Colección resultante de la consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<AccEntidadExterna> findTipoEntidadExterna(
	    String as_idTipoEntidad, boolean ab_orden, boolean ab_activo
	)
	    throws B2BException
	{
		Collection<AccEntidadExterna> lcaee_return;
		DAOManager                    ldm_manager;
		TipoEntidadDAO                lted_tipoEntidadDAO;
		DepartamentoDAO               ldd_departamentoDAO;
		MunicipioDAO                  lmd_municipioDAO;
		PaisDAO                       lpd_paisDAO;
		PersonaDAO                    lpd_personaDAO;
		AccEntidadExternaPersonaDAO   laeepd_entidadExternaPersonaDAO;

		lcaee_return                        = null;
		ldm_manager                         = DaoManagerFactory.getDAOManager();
		lted_tipoEntidadDAO                 = DaoCreator.getTipoEntidadDAO(ldm_manager);
		ldd_departamentoDAO                 = DaoCreator.getDepartamentoDAO(ldm_manager);
		lmd_municipioDAO                    = DaoCreator.getMunicipioDAO(ldm_manager);
		lpd_paisDAO                         = DaoCreator.getPaisDAO(ldm_manager);
		lpd_personaDAO                      = DaoCreator.getPersonaDAO(ldm_manager);
		laeepd_entidadExternaPersonaDAO     = DaoCreator.getAccEntidadExternaPersonaDAO(ldm_manager);

		try
		{
			if(StringUtils.isValidString(as_idTipoEntidad))
				lcaee_return = DaoCreator.getAccEntidadExternaDAO(ldm_manager)
						                     .findByIdTipoEntidad(as_idTipoEntidad, ab_orden, ab_activo);

			if(CollectionUtils.isValidCollection(lcaee_return))
			{
				for(AccEntidadExterna lc_temp : lcaee_return)
				{
					if(lc_temp != null)
					{
						TipoEntidad lte_tipoEntidad;

						lte_tipoEntidad = lted_tipoEntidadDAO.findById(lc_temp.getIdTipoEntidad());

						if(lte_tipoEntidad != null)
							lc_temp.setNombreTipoEntidad(lte_tipoEntidad.getNombre());

						String ls_idDepartamento;
						String ls_idMunicipio;
						String ls_idPais;

						ls_idDepartamento     = lc_temp.getIdDepartamento();
						ls_idMunicipio        = lc_temp.getIdMunicipio();
						ls_idPais             = lc_temp.getIdPais();

						if(StringUtils.isValidString(ls_idPais))
						{
							Pais lp_pais;

							lp_pais = lpd_paisDAO.findById(ls_idPais);

							if(lp_pais != null)
								lc_temp.setNombrePais(lp_pais.getNombre());

							if(StringUtils.isValidString(ls_idDepartamento))
							{
								Departamento ld_deparmento;

								ld_deparmento = new Departamento();

								ld_deparmento.setIdDepartamento(ls_idDepartamento);
								ld_deparmento.setIdPais(ls_idPais);

								ld_deparmento = ldd_departamentoDAO.findById(ld_deparmento);

								if(ld_deparmento != null)
									lc_temp.setNombreDepartamento(ld_deparmento.getNombre());

								if(StringUtils.isValidString(ls_idMunicipio))
								{
									Municipio lm_municipio;

									lm_municipio = new Municipio();

									lm_municipio.setIdDepartamento(ls_idDepartamento);
									lm_municipio.setIdPais(ls_idPais);
									lm_municipio.setIdMunicipio(ls_idMunicipio);

									lm_municipio = lmd_municipioDAO.findById(lm_municipio);

									if(lm_municipio != null)
										lc_temp.setNombreMunicipio(lm_municipio.getNombre());
								}
							}
						}

						{
							String ls_idRepresentanteLegal;

							ls_idRepresentanteLegal = lc_temp.getIdRepresentanteLegal();

							if(StringUtils.isValidString(ls_idRepresentanteLegal))
							{
								AccEntidadExternaPersona laeep_entidadExternaPersona;

								laeep_entidadExternaPersona = laeepd_entidadExternaPersonaDAO
										.findByIdEntidadExternaPersona(ls_idRepresentanteLegal);

								if(laeep_entidadExternaPersona != null)
								{
									Persona lp_persona;

									lp_persona = lpd_personaDAO.findById(laeep_entidadExternaPersona.getIdPersona());

									if(lp_persona != null)
										lc_temp.setNombreRepresentanteLegal(
										    lp_persona.getPrimerNombre() + " " + lp_persona.getPrimerApellido()
										);
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoEntidadExterna", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcaee_return;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los tipo oficina existentes.
	 *
	 * @param ad_od correspondiente al valor del tipo de objeto Documento
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoOficina> findTipoOficina(Documento ad_od)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<TipoOficina> lcto_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcto_datos      = new ArrayList<TipoOficina>();

		try
		{
			if(ad_od != null)
			{
				TipoDocumentoPublico    ltdp_datos;
				TipoDocumentoPublicoDAO ltdp_DAO;
				String                  ls_idTipoDocumento;

				ltdp_datos     = new TipoDocumentoPublico();
				ltdp_DAO       = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager);

				ls_idTipoDocumento = ad_od.getIdTipoDocumento();

				ltdp_datos.setIdTipoDocumento(ls_idTipoDocumento);

				ltdp_datos = ltdp_DAO.findById(ltdp_datos);

				if(ltdp_datos != null)
				{
					TipoOficinaDAO ltod_DAO;
					String         ls_oficinaOrigen;

					ltod_DAO             = DaoCreator.getTipoOficinaDAO(ldm_manager);
					ls_oficinaOrigen     = ltdp_datos.getTipoOficina();

					if(StringUtils.isValidString(ls_oficinaOrigen))
					{
						String[] las_tiposOficina;

						las_tiposOficina = ls_oficinaOrigen.split(",");

						if(CollectionUtils.isValidCollection(las_tiposOficina))
						{
							for(String ls_tmp : las_tiposOficina)
							{
								if(StringUtils.isValidString(ls_tmp))
								{
									TipoOficina lto_tmp;

									lto_tmp = new TipoOficina();

									lto_tmp.setIdTipoOficina(ls_tmp);

									lto_tmp = ltod_DAO.findById(lto_tmp);

									if(lto_tmp != null)
										lcto_datos.add(lto_tmp);
								}
							}
						}
					}
					else
						lcto_datos.addAll(ltod_DAO.findAll(true));
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoOficina", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcto_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los tipo oficina existentes.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoOficina> findTipoOficina()
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<TipoOficina> lcidt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcidt_datos     = null;

		try
		{
			lcidt_datos = DaoCreator.getTipoOficinaDAO(ldm_manager).findAll(true);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoOficina", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcidt_datos;
	}

	/**
	 * Metodo encargado de consultar el tipo Oficina por el id de la tabla SDB_PGN_TIPO_OFICINA.
	 *
	 * @param ato_to correspondiente al valor del tipo de objeto TipoOficina
	 * @return TipoOficina
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoOficina
	 */
	public synchronized TipoOficina findTipoOficinaById(TipoOficina ato_to)
	    throws B2BException
	{
		DAOManager  ldm_manager;
		TipoOficina lto_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lto_datos       = null;

		try
		{
			lto_datos = DaoCreator.getTipoOficinaDAO(ldm_manager).findById(ato_to);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoOficinaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lto_datos;
	}

	/**
	 * Método de transacciones de la base de datos para encontrar todos los registros de la tabla SDB_PGN_TIPO_PERSONA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoPersona> findTipoPersona()
	    throws B2BException
	{
		DAOManager              ldm_manager;
		Collection<TipoPersona> lctp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctp_datos      = null;

		try
		{
			lctp_datos = DaoCreator.getTipoPersonaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoPersona", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctp_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_COL_PREDIO_TIPO.
	 *
	 * @param ab_activo indica si se deben traer los registros con el campo ACTIVO = 'S'
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<PredioTipo> findTipoPredio(boolean ab_activo)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<PredioTipo> lcr_datos;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			lcr_datos = DaoCreator.getPredioTipoDao(ldm_manager).findAll(ab_activo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoPredio", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_COL_TIPO_RRR.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoRrr> findTipoRRR()
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<TipoRrr> lctr_datos;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			lctr_datos = DaoCreator.getTipoRrrDAO(ldm_manager).findAll(true);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoRRR", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctr_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar tipo de reparto de una etapa.
	 *
	 * @param as_etapa correspondiente al valor del tipo de objeto long
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String
	 */
	public synchronized String findTipoRepartoByEtapa(long as_etapa)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_dato;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_dato         = null;

		try
		{
			ls_dato = DaoCreator.getEtapaDAO(ldm_manager).findTipoRepartoByEtapa(as_etapa);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoRepartoByEtapa", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_dato;
	}

	/**
	 * Consulta los registros de TipoRequiereMatricula que se encuentren activos en la base de datos.
	 *
	 * @return Colección con el resultado de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoRequiereMatricula> findTipoRequiereMatriculaActivos()
	    throws B2BException
	{
		DAOManager                        ldm_manager;
		Collection<TipoRequiereMatricula> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getTipoRequiereMatriculaDAO(ldm_manager).findAll(true);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoRequiereMatriculaActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Consulta los registros de TipoRequiereMatricula que se encuentren activos en la base de datos.
	 *
	 * @param ab_requiereMatricula correspondiente al valor del tipo de objeto boolean
	 * @return Colección con el resultado de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoRequiereMatricula> findTipoRequiereMatriculaRegistro(
	    boolean ab_requiereMatricula
	)
	    throws B2BException
	{
		DAOManager                        ldm_manager;
		Collection<TipoRequiereMatricula> lcf_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcf_datos       = null;

		try
		{
			lcf_datos = DaoCreator.getTipoRequiereMatriculaDAO(ldm_manager)
					                  .findByTipoRequiereMatricula(ab_requiereMatricula);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoRequiereMatriculaRegistro", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcf_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_COL_TIPO_USO_SUELO.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoUsoSuelo> findTipoUsoSuelo()
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<TipoUsoSuelo> lcr_datos;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			lcr_datos = DaoCreator.getTipoUsoSueloDAO(ldm_manager).findAll(true);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoUsoSuelo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método de transacciones con la base de datos con el fin de encontrar todos los registros
	 * de la tabla SDB_PGN_TIPO_ACTO donde la columna ACTIVO='S' y tenga el id entre 4 y 5 digitos.
	 *
	 * @param ab_orderBy correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TipoActo> findTiposActosCodigo4Y5Digitos(boolean ab_orderBy)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		Collection<TipoActo> lcta_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcta_datos      = null;

		try
		{
			lcta_datos = DaoCreator.getTipoActoDAO(ldm_manager).findAllActosActivos4Y5Digitos(ab_orderBy);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllTiposActoActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcta_datos;
	}

	/**
	 * Método encargado de consultar turno historia con base a su id.
	 *
	 * @param ath_turnoHistoria Objeto que contiene el id del turno historia a consultar.
	 * @return Objeto que contiene la información del turno historia.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TurnoHistoria
	 */
	public synchronized TurnoHistoria findTurnoHistoriaByIdTurno(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		TurnoHistoria lth_turnoHistoria;

		ldm_manager           = DaoManagerFactory.getDAOManager();
		lth_turnoHistoria     = null;

		try
		{
			Collection<TurnoHistoria> lcth_datos;

			lcth_datos = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdTurnoFechaCreacion(ath_turnoHistoria);

			if(CollectionUtils.isValidCollection(lcth_datos))
			{
				Iterator<TurnoHistoria> lith_iterator;
				boolean                 lb_turnoEncontrado;

				lith_iterator          = lcth_datos.iterator();
				lb_turnoEncontrado     = false;

				while(lith_iterator.hasNext() && !lb_turnoEncontrado)
				{
					TurnoHistoria lth_temp;

					lth_temp = lith_iterator.next();

					if(lth_temp != null)
					{
						long   ll_etapa;
						String ls_estadoActividad;

						ls_estadoActividad     = lth_temp.getEstadoActividad();
						ll_etapa               = NumericUtils.getLong(lth_temp.getIdEtapa());

						if(
						    StringUtils.isValidString(ls_estadoActividad)
							    && ls_estadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA)
							    && (ll_etapa == EtapaCommon.ID_ETAPA_CALIFICACION)
						)
						{
							lth_turnoHistoria      = lth_temp;
							lb_turnoEncontrado     = true;
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnoHistoriaByIdTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lth_turnoHistoria;
	}

	/**
	 * Método encargado de consultar turno historia con base a su id.
	 *
	 * @param al_idTurnoHistoria Variable de tipo Long que contiene el id del turno historia a consultar.
	 * @return Objeto que contiene la información del turno historia.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TurnoHistoria
	 */
	public synchronized TurnoHistoria findTurnoHistoriaByid(Long al_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		TurnoHistoria lth_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lth_return      = null;

		try
		{
			if(NumericUtils.isValidLong(al_idTurnoHistoria))
				lth_return = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(al_idTurnoHistoria);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnoHistoriaByid", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lth_return;
	}

	/**
	 * Método encargado de consultar turno historia con base a su id.
	 *
	 * @param ath_turnoHistoria Objeto que contiene el id del turno historia a consultar.
	 * @return Objeto que contiene la información del turno historia.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TurnoHistoria
	 */
	public synchronized TurnoHistoria findTurnoHistoriaByid(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		TurnoHistoria lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			if(ath_turnoHistoria != null)
			{
				lc_data = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ath_turnoHistoria);

				if(lc_data != null)
				{
					boolean lb_validarFechaEtapa;

					lb_validarFechaEtapa = ath_turnoHistoria.isFechaEtapaValida();

					if(lb_validarFechaEtapa)
					{
						Date ld_fechaInicial;

						ld_fechaInicial = lc_data.getFechaInicial();

						if(ld_fechaInicial == null)
							DaoCreator.getTurnoHistoriaDAO(ldm_manager).updateFechaInicial(lc_data);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnoHistoriaByid", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar el Turno por medio de su Id.
	 *
	 * @param as_nir de as nir
	 * @param as_idProceso de as id proceso
	 * @return devuelve el valor de Turno
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Turno
	 */
	public synchronized String findTurnoNirCorrecciones(String as_nir, String as_idProceso)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_datos        = null;

		try
		{
			if(StringUtils.isValidString(as_nir))
			{
				Solicitud ls_solicitud;

				ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findByNir(new Solicitud(null, as_nir));

				if(ls_solicitud != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = ls_solicitud.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						Turno lt_turno;

						lt_turno = DaoCreator.getTurnoDAO(ldm_manager)
								                 .findByIdSolicitudProceso(new Turno(ls_idSolicitud, as_idProceso));

						if(lt_turno != null)
						{
							String ls_turno;

							ls_turno = lt_turno.getIdTurno();

							if(StringUtils.isValidString(ls_turno))
								ls_datos = ls_turno;
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnoNirCorrecciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_datos;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar el Turno por medio de su Id.
	 *
	 * @param at_turno Objeto Turno para extraer el Id turno
	 * @return devuelve el valor de Turno
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Turno
	 */
	public synchronized Turno findTurnoNombreEtapaById(Turno at_turno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Turno      ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			ldp_datos = DaoCreator.getTurnoDAO(ldm_manager).findNombreEtapaById(at_turno);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnoNombreEtapaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método de consulta de la url de catedra consultado por la url de bachue
	 * @param as_urlBachue con el url de parametro de búsqueda
	 * @return de tipo String con la url de catedra
	 * @throws B2BException en caso de error
	 */
	public synchronized String findUrlCatedra(String as_urlBachue)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_urlBaCatedra;

		ldm_manager         = DaoManagerFactory.getDAOManager();
		ls_urlBaCatedra     = null;

		try
		{
			ls_urlBaCatedra = DaoCreator.getOpcionDAO(ldm_manager).findUrlCatedra(as_urlBachue);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findUrlCatedra", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_urlBaCatedra;
	}

	/**
	 * Retorna el valor del objeto de Usuario.
	 *
	 * @param ate_parametros correspondiente al valor del tipo de objeto Usuario
	 * @return devuelve el valor de Usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Usuario
	 */
	public synchronized Usuario findUserActiveById(Usuario ate_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Usuario    lte_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lte_datos       = null;

		try
		{
			lte_datos = DaoCreator.getUsuarioDAO(ldm_manager).findUserActiveById(ate_parametros);

			if(lte_datos == null)
				throw new B2BException(ErrorKeys.USUARIO_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findUserActiveById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lte_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla SDB_AUT_USUARIO
	 * que coincidan con un ID_USUARIO específico.
	 *
	 * @param ate_parametros objeto del cual se extrae el ID_USUARIO para la consulta en la base de datos
	 * @return devuelve el valor de Usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Usuario
	 */
	public synchronized Usuario findUserById(Usuario ate_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Usuario    lte_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lte_datos       = null;

		try
		{
			lte_datos = DaoCreator.getUsuarioDAO(ldm_manager).findById(ate_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findUserById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lte_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar usuarios activos por un filtro de la tabla
	 * SDB_AUT_USUARIO.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param as_idRol de as id rol
	 * @param ab_apoyoNacional de ab apoyo nacional
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<Usuario> findUsersByRolOripActivos(
	    String as_idCirculo, String as_idRol, boolean ab_apoyoNacional
	)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<Usuario> lcu_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcu_datos       = null;

		try
		{
			lcu_datos = DaoCreator.getUsuarioDAO(ldm_manager)
					                  .findAllUsersByCirculoRol(as_idCirculo, as_idRol, ab_apoyoNacional);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findUsersByRolOripActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcu_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar usuarios activos por un filtro de la tabla
	 * SDB_AUT_USUARIO.
	 *
	 * @param as_idUsuario id para buscar en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Usuario> findUsersLineaProduccionByRolOripActivos(
	    String as_idCirculo, String as_idRol, String ls_idLineaProduccion
	)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<Usuario> lcu_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcu_datos       = null;

		try
		{
			lcu_datos = DaoCreator.getUsuarioDAO(ldm_manager)
					                  .findUsersLineaProduccionByRolOripActivos(
					    as_idCirculo, as_idRol, ls_idLineaProduccion
					);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findUsersByRolOripActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcu_datos;
	}

	/**
	 * Consulta todas las veredas que se encuentren activas y que pertenezcan a
	 * un determinado país, departamento y municipio.
	 *
	 * @param av_parametros objeto que contiene el id país, el id departamento y el id municipio para usar como filtro en la consulta
	 * @param ab_param objeto de tipo boolean
	 * @return Colección de veredas resultante de la consulta.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Vereda> findVeredas(Vereda av_parametros, boolean ab_param)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		Collection<Vereda> lcm_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcm_datos       = null;

		try
		{
			lcm_datos = DaoCreator.getVeredaDAO(ldm_manager).findByIds(av_parametros);

			if(CollectionUtils.isValidCollection(lcm_datos) && ab_param)
			{
				PaisDAO         lpd_paisDAO;
				DepartamentoDAO ldd_departamentoDAO;
				MunicipioDAO    lmd_municipioDAO;

				ldd_departamentoDAO     = DaoCreator.getDepartamentoDAO(ldm_manager);
				lmd_municipioDAO        = DaoCreator.getMunicipioDAO(ldm_manager);
				lpd_paisDAO             = DaoCreator.getPaisDAO(ldm_manager);

				for(Vereda lc_temp : lcm_datos)
				{
					if(lc_temp != null)
					{
						String ls_idDepartamento;
						String ls_idMunicipio;
						String ls_idPais;

						ls_idDepartamento     = lc_temp.getIdDepartamento();
						ls_idMunicipio        = lc_temp.getIdMunicipio();
						ls_idPais             = lc_temp.getIdPais();

						if(StringUtils.isValidString(ls_idPais))
						{
							Pais lp_pais;

							lp_pais = lpd_paisDAO.findById(ls_idPais);

							if(lp_pais != null)
								lc_temp.setNombrePais(lp_pais.getNombre());

							if(StringUtils.isValidString(ls_idDepartamento))
							{
								Departamento ld_deparmento;

								ld_deparmento = new Departamento();

								ld_deparmento.setIdDepartamento(ls_idDepartamento);
								ld_deparmento.setIdPais(ls_idPais);

								ld_deparmento = ldd_departamentoDAO.findById(ld_deparmento);

								if(ld_deparmento != null)
									lc_temp.setNombreDepartamento(ld_deparmento.getNombre());

								if(StringUtils.isValidString(ls_idMunicipio))
								{
									Municipio lm_municipio;

									lm_municipio = new Municipio();

									lm_municipio.setIdDepartamento(ls_idDepartamento);
									lm_municipio.setIdPais(ls_idPais);
									lm_municipio.setIdMunicipio(ls_idMunicipio);

									lm_municipio = lmd_municipioDAO.findById(lm_municipio);

									if(lm_municipio != null)
										lc_temp.setNombreMunicipio(lm_municipio.getNombre());
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findVeredas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcm_datos;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar dias restantes de vencimiento.
	 *
	 * @param afvtui_parametros tipo de fecha para consultar en una funcion
	 * @return devuelve el valor de Date
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized long funcionCalcularDiasFechas(FechaVenceTerminosUI afvtui_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		long       ll_dato;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ll_dato         = 0;

		try
		{
			ll_dato = DaoCreator.getUtilDAO(ldm_manager).funcionCalcularDiasFechas(afvtui_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("funcionCalcularDiasFechas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ll_dato;
	}

	/**
	 * Método encargado de generar el código de expediente.
	 *
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @param at_turno Objeto que contiene los datos del turno.
	 * @return String que contiene el código de expediente generado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String generarExpediente(String as_userId, String as_remoteIp, Turno at_turno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_expedienteSI;

		ldm_manager         = DaoManagerFactory.getDAOManager();
		ls_expedienteSI     = null;

		try
		{
			ls_expedienteSI = generarExpediente(as_userId, as_remoteIp, at_turno, ldm_manager);

			if(StringUtils.isValidString(ls_expedienteSI))
			{
				TurnoDAO lt_DAO;

				String   ls_nomenclaturaExpedienteSI;
				lt_DAO     = DaoCreator.getTurnoDAO(ldm_manager);

				ls_nomenclaturaExpedienteSI     = at_turno.getNomemclaturaExpedienteAA();
				at_turno                        = lt_DAO.findById(at_turno);

				if((at_turno != null) && StringUtils.isValidString(ls_nomenclaturaExpedienteSI))
				{
					LineaProduccion llp_data;

					llp_data = DaoCreator.getLineaProduccionDAO(ldm_manager)
							                 .findByNomenclatura(ls_nomenclaturaExpedienteSI);

					if(llp_data != null)
					{
						at_turno.setTipoExpedienteSI(llp_data.getIdLineaProduccion());
						at_turno.setNomemclaturaExpedienteSI(ls_nomenclaturaExpedienteSI);
						at_turno.setExpedienteSI(ls_expedienteSI);

						lt_DAO.actualizarTipoExpedienteExp(at_turno, as_userId);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_GENERAR_EXPEDIENTE);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarExpediente", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_expedienteSI;
	}

	/**
	 * Inserta los datos de un nuevo usuario en la base de datos.
	 *
	 * @param au_usuario objeto con los datos del usuario que se va a insertar
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp dirección ip del cliente donde se está ejecutando la
	 * actualización, para almacenarlo en los campos de auditoria
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertUser(Usuario au_usuario, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(au_usuario != null)
			{
				UsuarioDAO lu_DAO;
				lu_DAO = DaoCreator.getUsuarioDAO(ldm_manager);

				Usuario lu_temp;
				lu_temp = lu_DAO.findById(au_usuario);

				if(lu_temp != null)
				{
					Object[] loa_messageArgs = new String[1];
					loa_messageArgs[0] = lu_temp.getIdUsuario();

					throw new B2BException(ErrorKeys.ERROR_ID_USUARIO, loa_messageArgs);
				}

				au_usuario.setIdUsuarioCreacion(as_userId);
				au_usuario.setIpCreacion(as_remoteIp);

				lu_DAO.insertUser(au_usuario);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertUser", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_tipoCriterioBusqueda Argumento de tipo <code>String</code> que contiene el id_proceso a consultar
	 * @param as_idProceso Argumento de tipo <code>String</code> que contiene el id_proceso a consultar
	 * @param as_procesoBusqueda correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CriteriosDeBusqueda> listarTiposCriteriosBusqueda(
	    String as_tipoCriterioBusqueda, String as_idProceso, String as_procesoBusqueda
	)
	    throws B2BException
	{
		DAOManager                      ldm_manager;
		Collection<CriteriosDeBusqueda> lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getTipoCriterioBusquedaDAO(ldm_manager)
					                .findAll(
					    as_idProceso, as_procesoBusqueda,
					    (StringUtils.isValidString(as_tipoCriterioBusqueda)
					    && as_tipoCriterioBusqueda.equalsIgnoreCase(EstadoCommon.MASIVO))
					    ? TipoCriterioBusquedaCommon.DOCUMENTO_COPIAS : null
					);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("listarTiposCriteriosBusqueda", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Método encargado de consultar turno historia con base a su id.
	 *
	 * @param ath_turnoHistoria Objeto que contiene el id del turno  a consultar.
	 * @return Objeto que contiene la información del turno historia.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TurnoHistoria
	 */
	public synchronized TurnoHistoria obtenerAnteriorTurnoHistoria(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		TurnoHistoria lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			if(ath_turnoHistoria != null)
			{
				TurnoHistoria lth_temp;
				lth_temp     = null;
				lth_temp     = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdTurnoUltimo(ath_turnoHistoria);

				if(NumericUtils.isValidBigDecimal(lth_temp.getIdAnterior()))
					lth_temp.setIdTurnoHistoria(NumericUtils.getLongWrapper(lth_temp.getIdAnterior()));

				lc_data = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_temp);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerAnteriorTurnoHistoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Obtener la propiedad caracter de una constante.
	 *
	 * @param as_idConstante Codigo de la constante a buscar
	 * @return Propiedad caracter de la constante identificada con as_idConstante
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public synchronized String obtenerCaracterConstante(String as_idConstante)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_constante;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		ls_constante     = null;

		try
		{
			ls_constante = DaoCreator.getConstantesDAO(ldm_manager).findString(as_idConstante);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerCaracterConstante", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_constante;
	}

	/**
	 * Método para obtener todos los registros activos de la tabla de causales aprobación devolución.
	 *
	 * @param acad_param correspondiente al valor del tipo de objeto CausalAprobacionDevolucion
	 * @return Colección de causales aprobación devolución resultantes de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<CausalAprobacionDevolucion> obtenerCausalesAprobacionDevolucionActivos(
	    CausalAprobacionDevolucion acad_param
	)
	    throws B2BException
	{
		DAOManager                             ldm_manager;
		Collection<CausalAprobacionDevolucion> lcda_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcda_datos      = null;

		try
		{
			lcda_datos = DaoCreator.getCausalAprobacionDevolucionDAO(ldm_manager).findByIdEtapaAnterior(acad_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerCausalesAprobacionDevolucionActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcda_datos;
	}

	/**
	 * Obtener todos los ID_CONSTANTE que coincidan con un patrón y su el valor de la columna CARACTER concida sea
	 * igual a un valor.
	 *
	 * @param as_IdLike Patrón sobre el cual se realizará búsqueda tipo like en en campo ID_CONSTANTE
	 * @param as_caracter Valor de coincidencia del campo CARACTER
	 * @return Listado de ID_CONSTANTE
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<String> obtenerIdConstanesPorCaracterIdLikeCaracter(
	    String as_IdLike, String as_caracter
	)
	    throws B2BException
	{
		Collection<String> lcs_idConstantes;
		DAOManager         ldm_manager;

		ldm_manager          = DaoManagerFactory.getDAOManager();
		lcs_idConstantes     = null;

		try
		{
			lcs_idConstantes = DaoCreator.getConstantesDAO(ldm_manager)
					                         .findAllIdsByIdLikeCaracter(as_IdLike, as_caracter);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerIdConstanesPorCaracterIdLikeCaracter", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcs_idConstantes;
	}

	/**
	 * Actualiza el campo carácter del registro de una Constante.
	 *
	 * @param as_id identificador de la constante que se va a modificar
	 * @param as_caracter es el nuevo valor que se asignara al campo carácter de la
	 * constante
	 * @param as_userId id del usuario que ejecuta el proceso, para almacenarlo
	 * en los campos de auditoria
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void updateCaracter(String as_id, String as_caracter, String as_userId)
	    throws B2BException
	{
		DAOManager ldm_dm;

		ldm_dm = DaoManagerFactory.getDAOManager();

		try
		{
			DaoCreator.getConstantesDAO(ldm_dm).updateString(as_id, as_caracter, as_userId);
		}
		catch(B2BException lb2be_e)
		{
			ldm_dm.setRollbackOnly();

			clh_LOGGER.error("updateCaracter", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_dm.close();
		}
	}

	/**
	 * Actualiza los datos de un usuario en la base de datos.
	 *
	 * @param au_usuario objeto con los datos del usuario que se va a actualizar
	 * @param as_idUser id del usuario que ejecuta el proceso, para almacenarlo
	 * en los campos de auditoria
	 * @param as_remoteIp dirección ip del cliente donde se esta ejecutando la
	 * actualización, para almacenarlo en los campos de auditoria
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void updateUser(Usuario au_usuario, String as_idUser, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(au_usuario != null)
			{
				au_usuario.setIdUsuarioModificacion(as_idUser);
				au_usuario.setIpModificacion(as_remoteIp);

				DaoCreator.getUsuarioDAO(ldm_manager).updateUser(au_usuario);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("updateUser", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de Rol Opción.
	 *
	 * @param as_idProceso de as id proceso
	 * @param acacd_salvar de acacd salvar
	 * @param as_usuario de as usuario
	 * @param as_ip de as ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void validarTipoDocumental(
	    String as_idProceso, Collection<AccCompletitudDocumental> acacd_salvar, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idProceso))
			{
				AccCompletitudDocumentalDAO lacdd_completitudDocumentalDAO;

				lacdd_completitudDocumentalDAO = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager);

				if(CollectionUtils.isValidCollection(acacd_salvar))
				{
					for(AccCompletitudDocumental lc_temp : acacd_salvar)
					{
						if(
						    (lc_temp != null) && StringUtils.isValidString(as_ip)
							    && StringUtils.isValidString(as_usuario)
						)
						{
							lc_temp.setIdProceso(as_idProceso);
							lc_temp.setIdUsuarioCreacion(as_usuario);
							lc_temp.setIpCreacion(as_ip);
							lc_temp.setDigitalizado(EstadoCommon.N);

							lacdd_completitudDocumentalDAO.insert(lc_temp);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarTipoDocumental", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Verificar autenticacion segundo factor.
	 *
	 * @param as_idSession de as id session
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized boolean verificarAutenticacionSegundoFactor(String as_idSession)
	    throws B2BException
	{
		return verificarAutenticacionSegundoFactor(as_idSession, false);
	}

	/**
	 * Verificar autenticacion segundo factor.
	 *
	 * @param as_idSession de as id session
	 * @param ab_conciliaciones correspondiente al valor de conciliaciones
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized boolean verificarAutenticacionSegundoFactor(String as_idSession, boolean ab_conciliaciones)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_return;

		ldm_manager     = ab_conciliaciones ? DaoManagerFactory.getDAOManagerConciliacion()
			                                : DaoManagerFactory.getDAOManager();
		lb_return       = false;

		try
		{
			if(StringUtils.isValidString(as_idSession))
			{
				Constantes lc_datos;

				lc_datos = DaoCreator.getConstantesDAO(ldm_manager).findById(ConstanteCommon.CONSULTAR_SESION_ENDPOINT);

				if(lc_datos != null)
					lb_return = ClienteConsultarSesion.consultarSesion(as_idSession, lc_datos.getCaracter());
			}
			else
				throw new B2BException(ErrorKeys.ERROR_ID_SESION_INVALIDA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("verificarAutenticacionSegundoFactor", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Verificar proceso traslado por id turno.
	 *
	 * @param as_idTurno correspondiente al valor de id turno
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized boolean verificarProcesoTrasladoPorIdTurno(String as_idTurno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lb_return       = false;

		try
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				Solicitud ls_solicitud;

				ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findSolicitudByIdTurno(as_idTurno);

				if(ls_solicitud != null)
				{
					String ls_idProceso;

					ls_idProceso     = ls_solicitud.getIdProceso();

					lb_return = (StringUtils.isValidString(ls_idProceso)
							&& ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38));
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("verificarProcesoTrasladoPorIdTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}
}
