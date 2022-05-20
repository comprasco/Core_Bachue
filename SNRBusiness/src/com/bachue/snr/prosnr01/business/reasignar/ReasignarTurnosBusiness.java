package com.bachue.snr.prosnr01.business.reasignar;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;

import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.reasignar.ReasignarTurnos;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Rol;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para el manejo del negocio de reasignar turnos para realizar consultas o el proceso de reasignación.
 *
 * @author Carlos Calderon
 */
public class ReasignarTurnosBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ReasignarTurnosBusiness.class);

	/**
	 * Método encargado de actualizar el campo REASIGNADO_ESPECIAL de la tabla SDB_ACC_TURNO.
	 *
	 * @param acrt_data Colección Tipo ReasignarTurnos con todos los turnos de la colección
	 * @param as_idUsuario de as id usuario
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarReasignadoEspecial(
	    Collection<ReasignarTurnos> acrt_data, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		if(CollectionUtils.isValidCollection(acrt_data))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				ReasignarTurnos lrt_turnoSeleccionado = extraerSeleccionado(acrt_data);

				if(lrt_turnoSeleccionado != null)
				{
					String ls_idTurno;

					ls_idTurno = lrt_turnoSeleccionado.getIdTurno();

					if(StringUtils.isValidString(ls_idTurno))
					{
						Turno lrt_turno;

						lrt_turno = new Turno();

						lrt_turno.setReasignadoEspecial(EstadoCommon.S);
						lrt_turno.setIdUsuarioModificacion(as_idUsuario);
						lrt_turno.setIpModificacion(as_remoteIp);
						lrt_turno.setIdTurno(ls_idTurno);

						DaoCreator.getTurnoDAO(ldm_manager).actualizarReasignadoEspecial(lrt_turno);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();
				clh_LOGGER.error("actualizarReasignadoEspecial", lb2be_e);
				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Método para consultar etapas con un rol seleccionado.
	 *
	 * @param as_idRol String de rol seleccionado
	 * @return Coleccion de objetos Etapa consultados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Etapa> consultarEtapas(String as_idRol)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Etapa> lce_cllEtapas;

		ldm_manager       = DaoManagerFactory.getDAOManager();
		lce_cllEtapas     = new ArrayList<Etapa>();

		try
		{
			if(StringUtils.isValidString(as_idRol))
				lce_cllEtapas = DaoCreator.getEtapaDAO(ldm_manager).findByRol(as_idRol);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarEtapas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(lce_cllEtapas.isEmpty())
			lce_cllEtapas = null;

		return lce_cllEtapas;
	}

	/**
	 * Método encargado de enviar casos a etapa sustanciador de actuaciones administrativas.
	 *
	 * @param ar_reasignarTurnos Argumento de tipo <code>ReasignarTurnos</code> que contiene los valores necesarios para asignar.
	 * @param as_idUsuario Argumento de tipo <code>ReasignarTurnos</code> que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>ReasignarTurnos</code> que contiene la ip desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarAbogadoSustanciador(
	    ReasignarTurnos ar_reasignarTurnos, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ar_reasignarTurnos != null)
			{
				boolean lb_etapa415;
				boolean lb_etapa460;
				String  ls_idCirculo;
				String  ls_idRol;
				String  ls_tipoExpediente;
				String  ls_idUsuario;
				String  ls_observaciones;
				String  ls_turno;
				long    ll_idEtapa;

				ls_idCirculo          = ar_reasignarTurnos.getIdCirculo();
				ls_idRol              = ar_reasignarTurnos.getIdRol();
				ls_tipoExpediente     = ar_reasignarTurnos.getIdTipoExpediente();
				ls_idUsuario          = ar_reasignarTurnos.getUsuarioReasignacion();
				ls_observaciones      = ar_reasignarTurnos.getObservaciones();
				ls_turno              = ar_reasignarTurnos.getIdTurno();
				ll_idEtapa            = ar_reasignarTurnos.getIdEtapa();
				lb_etapa415           = ll_idEtapa == EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS;
				lb_etapa460           = ll_idEtapa == EtapaCommon.ID_ETAPA_460;

				ll_idEtapa = (lb_etapa415 || lb_etapa460) ? ll_idEtapa
					                                      : EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS;

				if(!StringUtils.isValidString(ls_idCirculo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL_CONSTANCIA);

				if(!StringUtils.isValidString(ls_idRol))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);

				if(!StringUtils.isValidString(ls_tipoExpediente))
					throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_EXPENDIENTE);

				if(!StringUtils.isValidString(ls_idUsuario))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NOMBRE_USUARIO_ASIGNAR);

				if(!StringUtils.isValidString(ls_observaciones))
					throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);

				{
					TurnoDAO ltd_DAO;
					Turno    lt_turno;

					ltd_DAO      = DaoCreator.getTurnoDAO(ldm_manager);
					lt_turno     = ltd_DAO.findById(ls_turno);

					if(lt_turno != null)
					{
						LineaProduccion llp_lineaProduccion;

						llp_lineaProduccion = new LineaProduccion();

						llp_lineaProduccion.setIdLineaProduccion(ls_tipoExpediente);

						llp_lineaProduccion = DaoCreator.getLineaProduccionDAO(ldm_manager).findById(
							    llp_lineaProduccion
							);

						if(llp_lineaProduccion != null)
						{
							String ls_nomenclatura;

							ls_nomenclatura = llp_lineaProduccion.getNomenclatura();

							if(lb_etapa460)
							{
								lt_turno.setTipoExpedienteSI(ls_tipoExpediente);
								lt_turno.setNomemclaturaExpedienteSI(ls_nomenclatura);
							}
							else
							{
								lt_turno.setTipoExpediente(ls_tipoExpediente);
								lt_turno.setNomemclaturaExpedienteAA(ls_nomenclatura);
							}

							ltd_DAO.actualizarTipoExpedienteExp(lt_turno, as_idUsuario);
						}
					}
				}

				{
					long ll_idMotivo;

					ll_idMotivo = calcularMotivoPorLinea(ll_idEtapa, ls_tipoExpediente);

					if(ll_idMotivo > 0)
					{
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
								                          .findById(
								    NumericUtils.getLongWrapper(ar_reasignarTurnos.getIdTurnoHistoria())
								);

						if(lth_turnoHistoria != null)
						{
							lth_turnoHistoria.setObservaciones(ls_observaciones);
							lth_turnoHistoria.setIdTurno(ls_turno);
							lth_turnoHistoria.setIdUsuarioAsignacion(ls_idUsuario);
							lth_turnoHistoria.setIdCirculo(ls_idCirculo);
							lth_turnoHistoria.setIdUsuarioModificacion(as_idUsuario);
							lth_turnoHistoria.setIpModificacion(as_remoteIp);

							terminarTurnoHistoriaYCrearEtapa(
							    lth_turnoHistoria, ldm_manager, new MotivoTramite(ll_idEtapa, ll_idMotivo), as_idUsuario,
							    as_remoteIp, EstadoCommon.TERMINADA, true, false
							);

							if(ar_reasignarTurnos.isEsPredioInconsistente())
								lth_turnoHistoria.setIdUsuarioAsignacion(as_idUsuario);

							DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarAbogadoSustanciador", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de extraer individualmente el registro seleccionado.
	 *
	 * @param lcrt_collection Colección Tipo ReasignarTurnos con todos los turnos de la colección
	 * @return lrt_return Objeto ResignarTurnos con el turno extraído
	 */
	public synchronized ReasignarTurnos extraerSeleccionado(Collection<ReasignarTurnos> lcrt_collection)
	{
		ReasignarTurnos lrt_return;

		lrt_return = null;

		if(CollectionUtils.isValidCollection(lcrt_collection))
		{
			boolean lb_encontrado;

			lb_encontrado = false;

			for(ReasignarTurnos lrt_tmp : lcrt_collection)
			{
				if((lrt_tmp != null) && !lb_encontrado && lrt_tmp.isSeleccionado())
				{
					lrt_return        = lrt_tmp;
					lb_encontrado     = true;
				}
			}
		}

		return lrt_return;
	}

	/**
	 * Método sobrecargado para realizar transaccciones con la base de datos para encontrar los registros de una etapa y usuario especificos.
	 *
	 * @param al_etapa long para hacer la consulta
	 * @param as_idCirculo de as id circulo
	 * @param as_idUsuario de as id usuario
	 * @param ab_porCantidad de ab por cantidad
	 * @param ai_cantidad de ai cantidad
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<ReasignarTurnos> findAllByEtapaUsuario(
	    long al_etapa, String as_idCirculo, String as_idUsuario, boolean ab_porCantidad, Integer ai_cantidad
	)
	    throws B2BException
	{
		return findAllByEtapaUsuario(al_etapa, as_idCirculo, as_idUsuario, ab_porCantidad, ai_cantidad, false);
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar los registros de una etapa y usuario especificos.
	 *
	 * @param al_etapa long para hacer la consulta
	 * @param as_idCirculo de as id circulo
	 * @param as_idUsuario de as id usuario
	 * @param ab_porCantidad de ab por cantidad
	 * @param ai_cantidad de ai cantidad
	 * @param ab_opcionSelected de ab opcion selected
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<ReasignarTurnos> findAllByEtapaUsuario(
	    long al_etapa, String as_idCirculo, String as_idUsuario, boolean ab_porCantidad, Integer ai_cantidad,
	    boolean ab_opcionSelected
	)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<ReasignarTurnos> lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
					                .findAllByEtapaUsuario(
					    al_etapa, as_idCirculo, as_idUsuario, ab_porCantidad, ai_cantidad
					);

			if(CollectionUtils.isValidCollection(lc_data))
			{
				String                 ls_idTurno;
				Collection<Aprobacion> lc_turnosDerivados;
				TurnoHistoriaDAO       lthd_thd;

				ls_idTurno     = null;
				lthd_thd       = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				for(ReasignarTurnos lrt_temp : lc_data)
				{
					ls_idTurno = lrt_temp.getIdTurno();

					if(ls_idTurno != null)
					{
						lc_turnosDerivados = lthd_thd.findTurnosDerivadosConIndicadorVinculado(ls_idTurno);

						if(CollectionUtils.isValidCollection(lc_turnosDerivados))
						{
							StringBuilder lsb_tmp;
							int           li_contador;

							lsb_tmp         = new StringBuilder();
							li_contador     = 0;

							for(Aprobacion loa_tmp : lc_turnosDerivados)
							{
								if(li_contador == (lc_turnosDerivados.size() - 1))
									lsb_tmp = lsb_tmp.append(loa_tmp.getTurnosAsociados());
								else
								{
									lsb_tmp = lsb_tmp.append(loa_tmp.getTurnosAsociados() + ", ");
									li_contador++;
								}
							}

							if(StringUtils.isValidString(lsb_tmp.toString()))
								lrt_temp.setIdTurnoHijo(lsb_tmp.toString());
						}
						else
							lrt_temp.setIdTurnoHijo(IdentificadoresCommon.SIN_TURNOS_VINCULADOS);
					}

					if(ab_opcionSelected && (lrt_temp != null))
						lrt_temp.setSeleccionado(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("findAllByEtapaUsuario", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Find all roles.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Rol> findAllRoles()
	    throws B2BException
	{
		DAOManager      ldm_manager;
		Collection<Rol> lcr_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_return      = new ArrayList<Rol>();

		try
		{
			lcr_return = DaoCreator.getRolDAO(ldm_manager).findAllRolesReasignacion();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("findAllRoles", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(lcr_return.isEmpty())
			lcr_return = null;

		return lcr_return;
	}

	/**
	 * Find all roles Recursos.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Rol> findAllRolesRecursos()
	    throws B2BException
	{
		DAOManager      ldm_manager;
		Collection<Rol> lcr_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_return      = new ArrayList<Rol>();

		try
		{
			lcr_return = DaoCreator.getRolDAO(ldm_manager).findAllRecursos();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("findAllRolesRecursos", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(lcr_return.isEmpty())
			lcr_return = null;

		return lcr_return;
	}

	/**
	 * Find all roles Segunda instancia.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Rol> findAllRolesSegundaInstancia()
	    throws B2BException
	{
		DAOManager      ldm_manager;
		Collection<Rol> lcr_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcr_return      = new ArrayList<Rol>();

		try
		{
			lcr_return = DaoCreator.getRolDAO(ldm_manager).findAllSegundaInstancia();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("findAllRolesSegundaInstancia", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(lcr_return.isEmpty())
			lcr_return = null;

		return lcr_return;
	}

	/**
	 * Find circulos desborde.
	 *
	 * @param as_idCirculoSelected de as id circulo selected
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<CirculoRegistral> findCirculosDesborde(String as_idCirculoSelected)
	    throws B2BException
	{
		Collection<CirculoRegistral> lccr_return;

		lccr_return = new ArrayList<CirculoRegistral>();

		if(StringUtils.isValidString(as_idCirculoSelected))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				CirculoRegistral lcr_circuloRegistral;

				lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager).findById(as_idCirculoSelected);

				if(lcr_circuloRegistral != null)
				{
					lccr_return.add(lcr_circuloRegistral);

					Collection<CirculoRegistral> lccr_circulosDesborde;

					lccr_circulosDesborde = DaoCreator.getCirculoRegistralDAO(ldm_manager)
							                              .findCirculosDesbordeByCirculoOrigen(as_idCirculoSelected);

					if(CollectionUtils.isValidCollection(lccr_circulosDesborde))
					{
						for(CirculoRegistral lcr_tmp : lccr_circulosDesborde)
						{
							if(lcr_tmp != null)
								lccr_return.add(lcr_tmp);
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();
				clh_LOGGER.error("findCirculosDesborde", lb2be_e);
				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		if(lccr_return.isEmpty())
			lccr_return = null;

		return lccr_return;
	}

	/**
	 * Método para consultar el nombre de una Etapa con el id para un INPUTTEXT.
	 *
	 * @param as_idEtapa String para consultar el nombre
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String findNombreEtapa(String as_idEtapa)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_data         = null;

		try
		{
			Etapa lr_r;

			lr_r = new Etapa();

			if(StringUtils.isValidString(as_idEtapa))
			{
				lr_r.setIdEtapa(NumericUtils.getLong(as_idEtapa));

				lr_r = DaoCreator.getEtapaDAO(ldm_manager).findById(lr_r);

				if(lr_r != null)
				{
					String ls_nombreEtapa;

					ls_nombreEtapa = lr_r.getNombre();

					if(StringUtils.isValidString(ls_nombreEtapa))
						ls_data = ls_nombreEtapa;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("findNombreEtapa", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_data;
	}

	/**
	 * Método de transacciones con la base de datos para encontrar todos los registros de la tabla
	 * SDB_PNG_USUARIO_ETAPA que coincidan con un ID_ETAPA específico
	 * y que este diferente a inactivbo en la tabla SDB_AUT_USUARIO_ROL.
	 *
	 * @param as_etapa es el id etapa para consultar en la base de datos
	 * @param as_idRol correspondiente al valor del tipo de objeto String
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param ab_rolCirculoActivo de ab rol circulo activo
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<Usuario> findUsuariosByEtapaRolCirculo(
	    long as_etapa, String as_idRol, String as_idCirculo, boolean ab_rolCirculoActivo
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
					                  .findByEtapaRolCirculo(as_etapa, as_idRol, as_idCirculo, ab_rolCirculoActivo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findUsuariosByEtapaRolCirculo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcu_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datosa para poder reasignar los turnos.
	 *
	 * @param acr_data de acr data
	 * @param as_idCirculo de as id circulo
	 * @param as_idRol de as id rol
	 * @param as_idEtapa de as id etapa
	 * @param as_idUsuarioSelected de as id usuario selected
	 * @param as_idUsuarioAsignar String con el usuario al que se le va a asignar a los turnos
	 * @param as_observaciones String con observaciones
	 * @param as_justificacionReasignadoEspecial  String con justificacion de reasignado especial
	 * @param ab_paramAutomatico de ab param automatico
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<ReasignarTurnos> reasignarTurnos(
	    Collection<ReasignarTurnos> acr_data, String as_idCirculo, String as_idRol, String as_idEtapa,
	    String as_idUsuarioSelected, String as_idUsuarioAsignar, String as_observaciones,
	    String as_justificacionReasignadoEspecial, boolean ab_paramAutomatico, String as_idUsuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<ReasignarTurnos> lcrt_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcrt_return     = null;

		try
		{
			if(CollectionUtils.isValidCollection(acr_data))
			{
				TurnoHistoriaDAO lthd_DAO;
				boolean          lb_justificacionReasignadoEspecial;
				int              li_ordenTurno;

				lthd_DAO                               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lcrt_return                            = new ArrayList<ReasignarTurnos>();
				lb_justificacionReasignadoEspecial     = false;
				li_ordenTurno                          = 1;

				if(StringUtils.isValidString(as_justificacionReasignadoEspecial))
					lb_justificacionReasignadoEspecial = true;

				for(ReasignarTurnos lrt_idTurnoReasignacion : acr_data)
				{
					ReasignarTurnos lrt_reasignar;

					lrt_reasignar = new ReasignarTurnos();

					if((lrt_idTurnoReasignacion != null) && lrt_idTurnoReasignacion.isSeleccionado())
					{
						TurnoHistoria lth_retornoSP;
						long          ll_idTurnoHistoria;
						TurnoHistoria lth_turnoHistory;

						lth_retornoSP          = reasignarTurnosMensajeError(
							    lrt_idTurnoReasignacion, as_idEtapa, as_observaciones, ab_paramAutomatico,
							    lb_justificacionReasignadoEspecial, as_idUsuarioAsignar, as_idCirculo, as_idUsuario,
							    as_remoteIp
							);
						ll_idTurnoHistoria     = NumericUtils.getLong(lrt_idTurnoReasignacion.getIdTurnoHistoria());
						lth_turnoHistory       = new TurnoHistoria();

						if(NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_idTurnoHistoria)))
							lth_turnoHistory.setIdTurnoHistoria(
							    NumericUtils.getLongWrapper(lrt_idTurnoReasignacion.getIdTurnoHistoria())
							);

						lth_turnoHistory = lthd_DAO.findById(lth_turnoHistory);

						if((lth_retornoSP != null) && (lth_turnoHistory != null))
						{
							if(lb_justificacionReasignadoEspecial)
							{
								lth_turnoHistory.setJustificacion(as_justificacionReasignadoEspecial);
								lth_turnoHistory.setIdUsuarioModificacion(as_idUsuario);
								lth_turnoHistory.setIpModificacion(as_remoteIp);

								lthd_DAO.insertOrUpdate(lth_turnoHistory, false);
							}

							lrt_reasignar.setIdOrdenTurno(li_ordenTurno++);
							lrt_reasignar.setIdTurnoHijo(lrt_idTurnoReasignacion.getIdTurnoHijo());
							lrt_reasignar.setNir(lrt_idTurnoReasignacion.getNir());
							lrt_reasignar.setIdTurno(lth_turnoHistory.getIdTurno());
							lrt_reasignar.setIdTurnoHistoria(
							    NumericUtils.getBigDecimal(NumericUtils.getLong(lth_turnoHistory.getIdTurnoHistoria()))
							);
							lrt_reasignar.setDescripcionRespuesta(lth_retornoSP.getCalificacion());
						}

						lcrt_return.add(lrt_reasignar);
					}
				}

				if(lcrt_return.isEmpty())
					throw new B2BException(ErrorKeys.SIN_REASIGNACION);
			}

			return lcrt_return;
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("reasignarTurnos", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de validar si los turnos vinculados de los turnos seleccionados estan bloqueados.
	 *
	 * @param acrt_data Colección Tipo ReasignarTurnos con todos los turnos de la colección
	 * @return booleana con validacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarTurnosVinculadosBloqueados(Collection<ReasignarTurnos> acrt_data)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		if(CollectionUtils.isValidCollection(acrt_data))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				ReasignarTurnos lrt_reasignarTurnos;

				lrt_reasignarTurnos = extraerSeleccionado(acrt_data);

				if(lrt_reasignarTurnos != null)
				{
					String ls_turnosVinculados;

					ls_turnosVinculados = lrt_reasignarTurnos.getIdTurnoHijo();

					if(
					    StringUtils.isValidString(ls_turnosVinculados)
						    && !ls_turnosVinculados.equalsIgnoreCase(IdentificadoresCommon.SIN_TURNOS_VINCULADOS)
					)
					{
						Collection<String> lcs_turnosVinculados;

						lcs_turnosVinculados = separarPorSimboloComa(ls_turnosVinculados);

						if(CollectionUtils.isValidCollection(lcs_turnosVinculados))
						{
							for(String ls_tmp : lcs_turnosVinculados)
							{
								if(!lb_return && StringUtils.isValidString(ls_tmp))
								{
									TurnoHistoria lth_turnoHistoria;

									lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdTurno(
										    ls_tmp
										);

									if(lth_turnoHistoria != null)
									{
										String ls_estadoActividad;

										ls_estadoActividad = lth_turnoHistoria.getEstadoActividad();

										if(
										    StringUtils.isValidString(ls_estadoActividad)
											    && ls_estadoActividad.equalsIgnoreCase(EstadoCommon.BLOQUEADO)
										)
											lb_return = true;
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
				clh_LOGGER.error("validarTurnosVinculadosBloqueados", lb2be_e);
				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lb_return;
	}

	/**
	 * Método para concatenar los errores en las transaccciones con la base de datos para poder reasignar los turnos.
	 *
	 * @param as_turnoHistoria List con distintos id turno
	 * @param as_etapa de as etapa
	 * @param as_observaciones String con observaciones
	 * @param ab_paramAutomatico de ab param automatico
	 * @param ab_justificacionReasignadoEspecial de ab justificacion reasignado especial
	 * @param as_idUsuarioAsignar String con el usuario al que se le va a asignar a los turnos
	 * @param as_circulo String para asignarselos a los turnos
	 * @param as_idUsuario de as id usuario
	 * @param as_remoteIp de as remote ip
	 * @return el valor de turno historia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private TurnoHistoria reasignarTurnosMensajeError(
	    ReasignarTurnos as_turnoHistoria, String as_etapa, String as_observaciones, boolean ab_paramAutomatico,
	    boolean ab_justificacionReasignadoEspecial, String as_idUsuarioAsignar, String as_circulo, String as_idUsuario,
	    String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		TurnoHistoria lth_turnoHistoria;

		ldm_manager           = DaoManagerFactory.getDAOManager();
		lth_turnoHistoria     = null;

		try
		{
			if(as_turnoHistoria != null)
			{
				BigDecimal lbd_idTurnoHistoria;

				lth_turnoHistoria       = new TurnoHistoria();
				lbd_idTurnoHistoria     = as_turnoHistoria.getIdTurnoHistoria();

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(lbd_idTurnoHistoria));

				if(NumericUtils.isValidBigDecimal(NumericUtils.getBigDecimal(NumericUtils.getLong(as_etapa))))
					lth_turnoHistoria.setIdEtapa(NumericUtils.getBigDecimal(NumericUtils.getLong(as_etapa)));

				if(StringUtils.isValidString(as_observaciones))
					lth_turnoHistoria.setObservaciones(as_observaciones);

				//Puede ser nulo, por el parámetro AUTOMÁTICO
				lth_turnoHistoria.setIdUsuarioAsignacion(as_idUsuarioAsignar);

				if(StringUtils.isValidString(as_circulo))
					lth_turnoHistoria.setIdCirculo(as_circulo);

				lth_turnoHistoria.setIdUsuarioModificacion(as_idUsuario);

				lth_turnoHistoria.setIpModificacion(as_remoteIp);

				lth_turnoHistoria = DaoCreator.getProcedimientosDAO(ldm_manager)
						                          .spReasignaTurno(
						    lth_turnoHistoria, ab_paramAutomatico, ab_justificacionReasignadoEspecial
						);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("reasignarTurnosMensajeError", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lth_turnoHistoria;
	}
}
