package com.bachue.snr.prosnr01.business.digitalizacion;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.reasignar.ReasignarTurnosBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioCirculoDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioCirculo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase para el manejo del negocio de digitalizacion.
 *
 * @author Carlos Calderon
 */
public class DigitalizacionBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ReasignarTurnosBusiness.class);

	/** Constante cs_IDENTIFICADOR_NIR. */
	private final String cs_IDENTIFICADOR_NIR = "&nir=";

	/** Constante cs_IDENTIFICADOR_TURNO. */
	private final String cs_IDENTIFICADOR_TURNO = "&turno=";

	/**
	 * Método encargado de actualizar el estado de digitalización.
	 * @param ath_parametros Argumento de tipo <code>TurnoHistoria</code> que contiene
	 *     los criterios necesarios para realizar la actualización.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene
	 *     el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene
	 *     la ip remota desde donde se realiza la operación.
	 * @throws B2BException
	 */
	public void actualizarEstadoDigitalizacion(TurnoHistoria ath_parametros, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_parametros != null)
			{
				TurnoHistoriaDAO lthd_DAO;
				Long             ll_idTurnoHistoria;
				String           ls_idTurno;

				lthd_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				ll_idTurnoHistoria     = ath_parametros.getIdTurnoHistoria();
				ls_idTurno             = ath_parametros.getIdTurno();

				if(!NumericUtils.isValidLong(ll_idTurnoHistoria))
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);

				if(StringUtils.isValidString(ls_idTurno))
				{
					{
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = lthd_DAO.findById(ll_idTurnoHistoria);

						if(lth_turnoHistoria != null)
						{
							Solicitud ls_solicitud;

							ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager)
									                     .findById(lth_turnoHistoria.getIdSolicitud());

							if(ls_solicitud != null)
								DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);
						}
					}

					{
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = new TurnoHistoria();

						lth_turnoHistoria.setIdTurno(ls_idTurno);
						lth_turnoHistoria.setIdEtapa(
						    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_DIGITALIZACION_CORE_BACHUE)
						);
						lth_turnoHistoria.setEstadoActividad(EstadoCommon.ASIGNADA);

						lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
								                          .findByIdTurnoEtapa(lth_turnoHistoria);

						if(lth_turnoHistoria != null)
							ll_idTurnoHistoria = lth_turnoHistoria.getIdTurnoHistoria();
					}
				}

				{
					String ls_calificacion;

					ls_calificacion = ath_parametros.getCalificacion();

					if(!StringUtils.isValidString(ls_calificacion))
						throw new B2BException(ErrorKeys.ERROR_SIN_CALIFICACION);
				}

				ath_parametros.setIdTurnoHistoria(ll_idTurnoHistoria);
				ath_parametros.setIdUsuarioModificacion(as_userId);
				ath_parametros.setIpModificacion(as_remoteIp);

				DaoCreator.getTurnoHistoriaDAO(ldm_manager).updateCalificacion(ath_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("actualizarEstadoDigitalizacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de consultar los turnos para un nir y estado actividad de etapas menores a las ingresadas.
	 *
	 * @param at_parametros Argumento de tipo <code>Turno</code> que contiene el nir para realizar la consulta.
	 * @return Collection<Turno> Colección de datos de tipo turnos con todos los registros encontrados.
	 * @throws B2BException
	 */
	public Collection<Turno> consultarTurnosNirEtapaEstado(Turno at_parametros)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Turno> lct_turnos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lct_turnos      = null;

		try
		{
			if(at_parametros != null)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = at_parametros.getTurnoHistoria();

				if(lth_turnoHistoria != null)
					lct_turnos = DaoCreator.getTurnoDAO(ldm_manager)
							                   .consultarTurnosNirEtapaEstado(
							    at_parametros.getNir(), NumericUtils.getInt(lth_turnoHistoria.getIdEtapa()),
							    lth_turnoHistoria.getEstadoActividad()
							);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarTurnosNirEtapaEstado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lct_turnos;
	}

	/**
	 * Método para consutruir URL de capture con DAOManager
	 *
	 * @param as_nir <code>String</code> que contiene el nir con el cual se realizará la búsqueda
	 * @param as_idTurno <code>String</code> que contiene el turno con el cual se realizará la búsqueda
	 * @param adm_manager Conexión a base de datos
	 * @return <code>String</code> con la url construída
	 * @throws B2BException
	 */
	public String construirUrlCapture(String as_nir, String as_idTurno, DAOManager adm_manager)
	    throws B2BException
	{
		SolicitudDAO        lsd_DAO;
		UsuarioCirculoDAO   lucd_DAO;
		CirculoRegistralDao lcrd_DAO;
		ConstantesDAO       lcd_DAO;
		String              ls_url;

		lsd_DAO      = DaoCreator.getSolicitudDAO(adm_manager);
		lucd_DAO     = DaoCreator.getUsuarioCirculoDAO(adm_manager);
		lcrd_DAO     = DaoCreator.getCirculoRegistralDAO(adm_manager);
		lcd_DAO      = DaoCreator.getConstantesDAO(adm_manager);
		ls_url       = obtenerUrlCapture(lcd_DAO);

		return construirUrlCapture(as_nir, as_idTurno, ls_url, lsd_DAO, lucd_DAO, lcrd_DAO);
	}

	/**
	 * Retorna el valor del objeto de TramiteCantidad buscando los turnos
	 *
	 * @param at_t correspondiente al valor del tipo de objeto Turno
	 * @return devuelve el valor de TramiteCantidad
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TramiteCantidad
	 */
	public synchronized TramiteCantidad findTurnos(Turno at_t)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		TramiteCantidad ltc_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ltc_return      = null;

		try
		{
			if(at_t != null)
			{
				TurnoHistoriaDAO            lthd_DAO;
				Collection<TramiteCantidad> lctc_ctc;
				long                        ll_idEtapa;
				String                      ls_turno;

				lthd_DAO       = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lctc_ctc       = new ArrayList<TramiteCantidad>();
				ll_idEtapa     = NumericUtils.getLong(at_t.getIdEtapaActual());
				ls_turno       = at_t.getIdTurno();

				if(at_t.isValidarTurno())
				{
					if(!StringUtils.isValidString(ls_turno))
						throw new B2BException(ErrorKeys.TURNO_INVALIDO);

					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = lthd_DAO.buscarPorIdTurnoEstadoActividad(ls_turno, EstadoCommon.ASIGNADA);

					if(lth_turnoHistoria == null)
						throw new B2BException(ErrorKeys.ERROR_TURNO_DIGITADO_NO_TIENE_ETAPA_ASG);

					{
						BigDecimal lbd_etapa;

						lbd_etapa = lth_turnoHistoria.getIdEtapa();

						if(lbd_etapa == null)
							throw new B2BException(ErrorKeys.ERROR_TURNO_DIGITADO_NO_TIENE_ETAPA_ASG);

						int li_etapa;

						li_etapa = NumericUtils.getInt(lbd_etapa);

						if(li_etapa >= 500)
							throw new B2BException(ErrorKeys.ERROR_TURNO_DIGITADO_NO_TIENE_ETAPA_ASG_500);
					}
				}

				lctc_ctc = lthd_DAO.bandejaDigitalizacion(ll_idEtapa, ls_turno, at_t.getNir());

				if(CollectionUtils.isValidCollection(lctc_ctc))
				{
					ltc_return = new TramiteCantidad();

					Collection<TramiteCantidad> lc_dataFinal;
					TurnoDAO                    ltd_DAO;
					SolicitudDAO                lsd_DAO;
					UsuarioCirculoDAO           lucd_DAO;
					CirculoRegistralDao         lcrd_DAO;
					ConstantesDAO               lcd_DAO;
					String                      ls_url;

					lc_dataFinal     = new ArrayList<TramiteCantidad>();
					ltd_DAO          = DaoCreator.getTurnoDAO(ldm_manager);
					lsd_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);
					lucd_DAO         = DaoCreator.getUsuarioCirculoDAO(ldm_manager);
					lcrd_DAO         = DaoCreator.getCirculoRegistralDAO(ldm_manager);
					lcd_DAO          = DaoCreator.getConstantesDAO(ldm_manager);
					ls_url           = obtenerUrlCapture(lcd_DAO);

					for(TramiteCantidad ltc_tmp : lctc_ctc)
					{
						if(ltc_tmp != null)
						{
							String ls_idTurnoHistoria;
							String ls_nir;
							String ls_idTurno;

							ls_idTurnoHistoria     = ltc_tmp.getIdTurnoHistoria();
							ls_nir                 = ltc_tmp.getNir();
							ls_idTurno             = ltc_tmp.getIdTurno();

							if(StringUtils.isValidString(ls_idTurnoHistoria))
							{
								TurnoHistoria lth_turnoHistoria;

								lth_turnoHistoria = new TurnoHistoria();

								lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

								lth_turnoHistoria = lthd_DAO.findById(lth_turnoHistoria);

								if(lth_turnoHistoria != null)
								{
									ltc_tmp.setFechaReparto(lth_turnoHistoria.getFechaInicial());
									ltc_tmp.setObservaciones(lth_turnoHistoria.getObservaciones());
									ltc_tmp.setCalificacion(lth_turnoHistoria.getCalificacion());
								}
							}

							if(StringUtils.isValidString(ls_idTurno))
							{
								Turno lt_turno;
								lt_turno = new Turno();

								lt_turno.setIdTurno(ls_idTurno);

								lt_turno = ltd_DAO.findById(lt_turno);

								if(lt_turno != null)
									ltc_tmp.setFechaCreacion(lt_turno.getFechaCreacion());
							}

							ltc_tmp.setUrlDigitalizacion(
							    construirUrlCapture(ls_nir, ls_idTurno, ls_url, lsd_DAO, lucd_DAO, lcrd_DAO)
							);

							lc_dataFinal.add(ltc_tmp);
						}
					}

					ltc_return.setTurnos(lc_dataFinal);
				}

				if(!CollectionUtils.isValidCollection(lctc_ctc))
					ltc_return = new TramiteCantidad();
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ltc_return;
	}

	/**
	 * Método encargado de encontrar el último circulo del usuario
	 * @param as_nir <code>String</code> que contiene el nir con el cual se realizará la búsqueda
	 * @param asd_DAO <code>SolicitudDAO</code> el cual interactua con la base de datos
	 * @param aucd_DAO <code>UsuarioCirculoDAO</code> el cual interactua con la base de datos
	 * @param acrd_DAO <code>CirculoRegistralDao</code> el cual interactua con la base de datos
	 * @return <code>String</code> con el nombre del circulo del usuario
	 * @throws B2BException
	 */
	private String obtenerCirculo(
	    String as_nir, SolicitudDAO asd_DAO, UsuarioCirculoDAO aucd_DAO, CirculoRegistralDao acrd_DAO
	)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(StringUtils.isValidString(as_nir) && (asd_DAO != null) && (aucd_DAO != null) && (acrd_DAO != null))
		{
			String ls_usuario;

			ls_usuario = null;

			{
				Solicitud ls_solicitud;

				ls_solicitud = new Solicitud();

				ls_solicitud.setNir(as_nir);

				ls_solicitud = asd_DAO.findByNir(ls_solicitud);

				if(ls_solicitud != null)
					ls_usuario = ls_solicitud.getIdUsuarioCreacion();
			}

			{
				UsuarioCirculo luc_usuarioCirculo;

				luc_usuarioCirculo = new UsuarioCirculo();

				luc_usuarioCirculo.setIdUsuario(ls_usuario);
				luc_usuarioCirculo.setActivo(EstadoCommon.S);

				luc_usuarioCirculo = aucd_DAO.buscarUsuarioActivoMasReciente(luc_usuarioCirculo);

				if(luc_usuarioCirculo != null)
				{
					CirculoRegistral lcr_circuloRegistral;

					lcr_circuloRegistral = acrd_DAO.findById(luc_usuarioCirculo.getIdCirculo());

					if(lcr_circuloRegistral != null)
						ls_return = lcr_circuloRegistral.getNombre();
				}
			}
		}

		return ls_return;
	}

	/**
	 * Método con el cual se obtiene una url de capture especifica para cada tipo documental
	 * @param as_nir <code>String</code> que contiene el nir con el cual se realizará la búsqueda
	 * @param as_idTurno <code>String</code> que contiene el turno con el cual se realizará la búsqueda
	 * @return <code>String</code> con la url construída
	 * @throws B2BException
	 */
	public synchronized String construirUrlCapture(String as_nir, String as_idTurno)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(StringUtils.isValidString(as_nir))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				SolicitudDAO        lsd_DAO;
				UsuarioCirculoDAO   lucd_DAO;
				CirculoRegistralDao lcrd_DAO;
				ConstantesDAO       lcd_DAO;
				String              ls_url;

				lsd_DAO       = DaoCreator.getSolicitudDAO(ldm_manager);
				lucd_DAO      = DaoCreator.getUsuarioCirculoDAO(ldm_manager);
				lcrd_DAO      = DaoCreator.getCirculoRegistralDAO(ldm_manager);
				lcd_DAO       = DaoCreator.getConstantesDAO(ldm_manager);
				ls_url        = obtenerUrlCapture(lcd_DAO);
				ls_return     = construirUrlCapture(as_nir, as_idTurno, ls_url, lsd_DAO, lucd_DAO, lcrd_DAO);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("construirUrlCapture", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ls_return;
	}

	/**
	 * Método con el cual se obtiene una url de capture especifica para cada tipo documental
	 * @param as_nir <code>String</code> que contiene el nir con el cual se realizará la búsqueda
	 * @param as_idTurno <code>String</code> que contiene el turno con el cual se realizará la búsqueda
	 * @param as_url <code>String</code> que contiene la url base con la cual se construirá la url
	 * @param asd_DAO <code>SolicitudDAO</code> que controla la comunicación con la base de datos
	 * @param aucd_DAO <code>UsuarioCirculoDAO</code> que controla la comunicación con la base de datos
	 * @param acrd_DAO <code>CirculoRegistralDao</code> que controla la comunicación con la base de datos
	 * @return <code>String</code> con la url construída
	 * @throws B2BException
	 */
	private synchronized String construirUrlCapture(
	    String as_nir, String as_idTurno, String as_url, SolicitudDAO asd_DAO, UsuarioCirculoDAO aucd_DAO,
	    CirculoRegistralDao acrd_DAO
	)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(
		    StringUtils.isValidString(as_nir) && StringUtils.isValidString(as_url) && (asd_DAO != null)
			    && (aucd_DAO != null) && (acrd_DAO != null)
		)
		{
			try
			{
				String ls_circulo;

				ls_circulo = obtenerCirculo(as_nir, asd_DAO, aucd_DAO, acrd_DAO);

				if(StringUtils.isValidString(ls_circulo))
				{
					String        ls_url;
					StringBuilder lsb_sb;

					ls_url     = as_url.replace(ConstanteCommon.CAPTURE_WORKSPACE_DIGITALIZACION, ls_circulo);
					lsb_sb     = new StringBuilder(ls_url);

					lsb_sb.append(cs_IDENTIFICADOR_NIR);
					lsb_sb.append(as_nir);

					if(StringUtils.isValidString(as_idTurno))
					{
						lsb_sb.append(cs_IDENTIFICADOR_TURNO);
						lsb_sb.append(as_idTurno);
					}

					ls_return = lsb_sb.toString();
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("construirUrlCapture", lb2be_e);

				throw lb2be_e;
			}
		}

		return ls_return;
	}

	/**
	 * Metodo encargado de generar la url de capture para digitalización de documentos.
	 * @param acd_DAO Argumento de tipo <code>ConstantesDAO</code> que permite la conexión a la base de datos.
	 * @return variable de tipo <code>String</code> que contiene la url de capture.
	 * @throws B2BException
	 */
	private synchronized String obtenerUrlCapture(ConstantesDAO acd_DAO)
	    throws B2BException
	{
		String ls_url;

		ls_url = null;

		try
		{
			Collection<String> lcs_constantes;

			lcs_constantes     = new LinkedList<String>();

			ls_url = acd_DAO.findString(ConstanteCommon.URL_DIGITALIZACION_CAPTURE);

			lcs_constantes.add(ConstanteCommon.CLIENT_PROFILE_DIGITALIZACION);
			lcs_constantes.add(ConstanteCommon.CAPTURE_DRIVER_DIGITALIZACION);
			lcs_constantes.add(ConstanteCommon.CAPTURE_SOURCE_DIGITALIZACION);
			lcs_constantes.add(ConstanteCommon.SIGN_OUT_ON_RELEASE_DIGITALIZACION);

			for(String ls_iterador : lcs_constantes)
			{
				String ls_constante;

				ls_constante = acd_DAO.findString(ls_iterador);

				if(StringUtils.isValidString(ls_constante))
					ls_url = ls_url.replace(ls_iterador, ls_constante);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerUrlCapture", lb2be_e);

			throw lb2be_e;
		}

		return ls_url;
	}
}
