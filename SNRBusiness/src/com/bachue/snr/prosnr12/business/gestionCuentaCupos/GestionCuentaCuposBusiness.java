package com.bachue.snr.prosnr12.business.gestionCuentaCupos;

import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoEmpresaAEI;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoEntradaActualizarEntidad;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoRepresentanteLegalAEI;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoSalidaActualizarEntidad;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.cancelarusuario.v1.TipoAdminCNUI;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.cancelarusuario.v1.TipoEntradaCancelarUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.cancelarusuario.v1.TipoSalidaCancelarUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.cancelarusuario.v1.TipoUsuarioCNUI;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoEntradaConsultarSaldosNotaCredito;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCredito;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCreditoNotasCreditos;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCreditoNotasCreditosNotaCredito;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1.TipoAdminCICCI;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1.TipoEntradaConsultarIDCuentaCupo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1.TipoSalidaConsultarIDCuentaCupo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoAdminCMI;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoEntradaConsultarMovimientos;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoMovimientoCMO;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoNotaCreditoCMO;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoReciboCajaCMO;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoSalidaConsultarMovimientos;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoAdminCSI;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoEntradaConsultarSaldo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoSalidaConsultarSaldo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoUsuarioCSI;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoAdminCUI;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoEntradaConsultarUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoSalidaConsultarUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoUsuarioCUI;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoUsuarioCUO;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoAdminCUSI;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoEntradaConsultarUsuarios;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoSalidaConsultarUsuarios;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoUsuarioCUSO;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoAdminIUI;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoEntradaInscribirUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoSalidaInscribirUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoUsuarioIUI;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.pagarcuentacupo.v1.TipoEntradaPagarCuentaCupo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.pagarcuentacupo.v1.TipoSalidaPagarCuentaCupo;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.cuentaCupos.BaseCuentaCupo;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.constants.TipoOficinaCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccEntidadExternaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaCorreoElectronicoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaTelefonoDAO;
import com.bachue.snr.prosnr01.dao.acc.UsuarioCuentaCupoDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.CodigoError;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.CuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.MaestroMovimientoCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.NotaCreditoCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.PagoCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.UsuarioCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;

import com.bachue.snr.prosnr12.common.constants.ErrorKeys;
import com.bachue.snr.prosnr12.common.constants.TipoUsuarioCuentaCupoCommon;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.time.LocalDateTime;

import java.util.Calendar;
import java.util.Collection;


/**
 * Clase encargada de la logica de negocio del servicio web Gestion Cuenta Cupos
 *
 * @author Manuel Blanco
 *
 */
public class GestionCuentaCuposBusiness extends BaseCuentaCupo
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GestionCuentaCuposBusiness.class, ProyectosCommon.GESTION_CUENTA_CUPOS_12);

	/**
	 * Inactiva un usuario de una cuenta cupo
	 *
	 * @param ateae_entrada Objeto contenedor de la información del usuario a inactivar
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Direcciín IP del cliente que ejecuta la acción
	 * @return TipoSalidaCancelarUsuario con la respuesta de la operación
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public synchronized TipoSalidaActualizarEntidad actualizarEntidad(
	    TipoEntradaActualizarEntidad ateae_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaActualizarEntidad ltsae_return;
		DAOManager                  ldm_manager;
		CodigoError                 lce_codigoSalida;
		String                      ls_mensajeSalida;

		ltsae_return         = new TipoSalidaActualizarEntidad();
		ldm_manager          = DaoManagerFactory.getDAOManager();
		lce_codigoSalida     = new CodigoError();
		ls_mensajeSalida     = "";

		try
		{
			if(ateae_entrada == null)
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

			if(!validarModuloSE(ateae_entrada.getModulo()))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MODULO_NO_VALIDO));

			String         ls_tipoDocumentoEmpresa;
			String         ls_numeroDocumentoEmpresa;
			String         ls_idPersona;
			TipoEmpresaAEI lteaei_empresa;

			ls_idPersona       = null;
			lteaei_empresa     = ateae_entrada.getEmpresa();

			if(lteaei_empresa != null)
			{
				ls_tipoDocumentoEmpresa       = lteaei_empresa.getTipoDocumentoEmpresa();
				ls_numeroDocumentoEmpresa     = lteaei_empresa.getNumeroDocumentoEmpresa();

				validarTipoDocumento(ls_tipoDocumentoEmpresa, lce_codigoSalida, ldm_manager);

				validarNumeroDocumento(ls_numeroDocumentoEmpresa);

				if(!StringUtils.isValidString(lteaei_empresa.getRazonSocialEmpresa()))
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_RAZON_SOCIAL));

				validarPaisDepMun(
				    lteaei_empresa.getPaisEmpresa(), lteaei_empresa.getDepartamentoEmpresa(),
				    lteaei_empresa.getMunicipioEmpresa(), ProyectosCommon.GESTION_CUENTA_CUPOS_12, ldm_manager
				);

				{
					String ls_idTipoEntidad;

					ls_idTipoEntidad = lteaei_empresa.getTipoEntidadEmpresa();

					if(StringUtils.isValidString(ls_idTipoEntidad))
					{
						TipoEntidad lte_tipoEntidad;

						lte_tipoEntidad = DaoCreator.getTipoEntidadDAO(ldm_manager)
								                        .findById(lteaei_empresa.getTipoEntidadEmpresa());

						if(lte_tipoEntidad == null)
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TIPO_ENTIDAD_NO_VALIDO));
					}
					else
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_TIPO_ENTIDAD));
				}

				if(!StringUtils.isValidString(lteaei_empresa.getActividadEconomica()))
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_ACTIVIDAD_ECONOMICA));

				validarTelefono(
				    lteaei_empresa.getTelefonoEmpresa(), ls_tipoDocumentoEmpresa, ls_numeroDocumentoEmpresa, ldm_manager
				);
			}
			else
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

			{
				TipoRepresentanteLegalAEI ltrlaei_representante;

				ltrlaei_representante = ateae_entrada.getRepresentanteLegal();

				if(ltrlaei_representante != null)
				{
					String ls_tipoDocumentoRepresentante;
					String ls_numeroDocumentoRepresentante;
					String ls_telefonoRepresentante;
					String ls_correoRepresentante;
					String ls_departamentoRepresentante;

					ls_tipoDocumentoRepresentante       = ltrlaei_representante.getTipoDocumentoRepresentanteLegal();
					ls_numeroDocumentoRepresentante     = ltrlaei_representante.getNumeroDocumentoRepresentanteLegal();
					ls_telefonoRepresentante            = ltrlaei_representante.getTelefono();
					ls_correoRepresentante              = ltrlaei_representante.getCorreoElectronicoCorporativoUsuario();
					ls_departamentoRepresentante        = ltrlaei_representante.getDepartamentoEmpresa();

					validarTipoDocumento(ls_tipoDocumentoRepresentante, lce_codigoSalida, ldm_manager);

					validarNumeroDocumento(ls_numeroDocumentoRepresentante);

					validarPrimerNombreApellido(ltrlaei_representante.getPrimerNombre(), true);

					validarPaisDepMun(
					    IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT, ls_departamentoRepresentante, null, false, true,
					    false, ProyectosCommon.GESTION_CUENTA_CUPOS_12, ldm_manager
					);

					validarPrimerNombreApellido(ltrlaei_representante.getPrimerApellido(), false);

					validarTelefono(
					    ls_telefonoRepresentante, ls_tipoDocumentoRepresentante, ls_numeroDocumentoRepresentante,
					    ldm_manager
					);

					if(!validarCorreoElectronico(ls_correoRepresentante))
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CORREO_FORMATO_NO_VALIDO));

					{
						PersonaDAO                  lpd_personaDAO;
						PersonaTelefonoDAO          lptd_personaTelefonoDAO;
						PersonaCorreoElectronicoDAO lpced_personaCorreoDAO;
						Persona                     lp_personaInsUpd;
						Persona                     lp_persona;

						lpd_personaDAO              = DaoCreator.getPersonaDAO(ldm_manager);
						lptd_personaTelefonoDAO     = DaoCreator.getPersonaTelefonoDAO(ldm_manager);
						lpced_personaCorreoDAO      = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager);
						lp_personaInsUpd            = new Persona(ltrlaei_representante);
						lp_persona                  = lpd_personaDAO.findByDocNameMaxId(lp_personaInsUpd);

						if(lp_persona != null)
						{
							ls_idPersona = lp_persona.getIdPersona();

							lp_personaInsUpd.setIdPersona(ls_idPersona);
							lp_personaInsUpd.setRazonSocial(lp_persona.getRazonSocial());
							lp_personaInsUpd.setIdPais(lp_persona.getIdPais());
							lp_personaInsUpd.setIdNaturalGenero(lp_persona.getIdNaturalGenero());
							lp_personaInsUpd.setRazonSocial(lp_persona.getRazonSocial());
							lp_personaInsUpd.setIdUsuarioModificacion(as_userId);
							lp_personaInsUpd.setIpModificacion(as_remoteIp);
							lp_personaInsUpd.setOrigenBachue(lp_persona.getOrigenBachue());

							lpd_personaDAO.insertOrUpdate(lp_personaInsUpd, false);

							{
								PersonaTelefono lpt_personaTelefono;

								lpt_personaTelefono = lptd_personaTelefonoDAO.findByIdPersonaTelefonoMax(
									    ls_idPersona, ls_telefonoRepresentante
									);

								if(lpt_personaTelefono == null)
									insertarTelefonoPersonaRepresentanteEntidad(
									    ls_idPersona, ls_telefonoRepresentante, ls_departamentoRepresentante, as_userId,
									    as_remoteIp, lptd_personaTelefonoDAO
									);
							}

							{
								PersonaCorreoElectronico lpce_personaCorreo;

								lpce_personaCorreo = lpced_personaCorreoDAO.findByIdPersonaCorreoMax(
									    ls_idPersona, ls_correoRepresentante
									);

								if(lpce_personaCorreo == null)
									insertarCorreoPersonaRepresentanteEntidad(
									    ls_idPersona, ls_correoRepresentante, as_userId, as_remoteIp,
									    lpced_personaCorreoDAO
									);
							}
						}
						else
						{
							lp_personaInsUpd.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
							lp_personaInsUpd.setOrigenBachue(EstadoCommon.S);
							lp_personaInsUpd.setIdUsuarioCreacion(as_userId);
							lp_personaInsUpd.setIpCreacion(as_remoteIp);

							lp_personaInsUpd = lpd_personaDAO.insertOrUpdate(lp_personaInsUpd, true);

							if(lp_personaInsUpd == null)
							{
								lce_codigoSalida.setCodigoError(BigInteger.valueOf(482L));

								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_INSERTANDO_REPRESENTANTE));
							}

							ls_idPersona = lp_personaInsUpd.getIdPersona();

							insertarTelefonoPersonaRepresentanteEntidad(
							    ls_idPersona, ls_telefonoRepresentante, ls_departamentoRepresentante, as_userId,
							    as_remoteIp, lptd_personaTelefonoDAO
							);

							insertarCorreoPersonaRepresentanteEntidad(
							    ls_idPersona, ls_correoRepresentante, as_userId, as_remoteIp, lpced_personaCorreoDAO
							);
						}
					}
				}
				else
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));
			}

			{
				AccEntidadExternaDAO laeed_entidadExternaDAO;
				AccEntidadExterna    laee_entidad;
				AccEntidadExterna    laee_entidadInsUpd;

				laeed_entidadExternaDAO     = DaoCreator.getAccEntidadExternaDAO(ldm_manager);
				laee_entidad                = laeed_entidadExternaDAO.findByTipoDocNumDoc(
					    ls_tipoDocumentoEmpresa, ls_numeroDocumentoEmpresa
					);
				laee_entidadInsUpd          = new AccEntidadExterna(lteaei_empresa);

				if(laee_entidad != null)
				{
					laee_entidadInsUpd.setIdTipoOficina(laee_entidad.getIdTipoOficina());
					laee_entidadInsUpd.setIdEntidadExterna(laee_entidad.getIdEntidadExterna());
					laee_entidadInsUpd.setIdRepresentanteLegal(ls_idPersona);
					laee_entidadInsUpd.setEntidadExenta(laee_entidad.getEntidadExenta());
					laee_entidadInsUpd.setIdUsuarioModificacion(as_userId);
					laee_entidadInsUpd.setIpModificacion(as_remoteIp);

					laeed_entidadExternaDAO.update(laee_entidadInsUpd);
				}
				else
				{
					laee_entidadInsUpd.setIdRepresentanteLegal(ls_idPersona);
					laee_entidadInsUpd.setIdTipoOficina(TipoOficinaCommon.PARTICULAR);
					laee_entidadInsUpd.setEntidadExenta(EstadoCommon.N);
					laee_entidadInsUpd.setActivo(EstadoCommon.S);
					laee_entidadInsUpd.setIdUsuarioCreacion(as_userId);
					laee_entidadInsUpd.setIpCreacion(as_remoteIp);

					laeed_entidadExternaDAO.insert(laee_entidadInsUpd);
				}
			}

			lce_codigoSalida.setCodigoError(BigInteger.valueOf(200L));

			ls_mensajeSalida = addMessage(MessagesKeys.OK);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("actualizarEntidad", lb2be_e);

			ls_mensajeSalida = lb2be_e.getMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(400L));
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("actualizarEntidad", le_e);

			ls_mensajeSalida = le_e.getLocalizedMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(500L));
		}
		finally
		{
			ldm_manager.close();
		}

		ltsae_return.setCodigoMensaje(lce_codigoSalida.getCodigoError());
		ltsae_return.setDescripcionMensaje(ls_mensajeSalida);

		return ltsae_return;
	}

	/**
	 * Inactiva un usuario de una cuenta cupo
	 *
	 * @param atecu_entrada Objeto contenedor de la información del usuario a inactivar
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Direcciín IP del cliente que ejecuta la acción
	 * @return TipoSalidaCancelarUsuario con la respuesta de la operación
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public synchronized TipoSalidaCancelarUsuario cancelarUsuario(
	    TipoEntradaCancelarUsuario atecu_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaCancelarUsuario ltscu_return;
		DAOManager                ldm_manager;
		CodigoError               lce_codigoSalida;
		String                    ls_mensajeSalida;

		ltscu_return         = new TipoSalidaCancelarUsuario();
		ldm_manager          = DaoManagerFactory.getDAOManager();
		lce_codigoSalida     = new CodigoError();
		ls_mensajeSalida     = "";

		try
		{
			if(atecu_entrada == null)
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

			if(!validarModuloSE(atecu_entrada.getModulo()))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MODULO_NO_VALIDO));

			String ls_idCuentaCupo;

			ls_idCuentaCupo = atecu_entrada.getIDCuentaCupo();

			validarCuentaCupo(ls_idCuentaCupo, lce_codigoSalida, ldm_manager);

			{
				TipoAdminCNUI ltacnui_admin;

				ltacnui_admin = atecu_entrada.getAdmin();

				if(ltacnui_admin != null)
				{
					String ls_tipoDocumento;
					String ls_numeroDocumento;

					ls_tipoDocumento       = ltacnui_admin.getTipoDocAdmin();
					ls_numeroDocumento     = ltacnui_admin.getNumDocAdmin();

					validarTipoDocumento(ls_tipoDocumento, lce_codigoSalida, ldm_manager);

					validarNumeroDocumento(ls_numeroDocumento);

					validarUsuarioCuentaCupo(
					    ls_tipoDocumento, ls_numeroDocumento, ls_idCuentaCupo, lce_codigoSalida, ldm_manager, true
					);
				}
				else
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));
			}

			{
				TipoUsuarioCNUI ltucnui_usuario;

				ltucnui_usuario = atecu_entrada.getUsuario();

				if(ltucnui_usuario != null)
				{
					UsuarioCuentaCupo lucc_usuario;
					String            ls_tipoDocumento;
					String            ls_numeroDocumento;

					ls_tipoDocumento       = ltucnui_usuario.getTipoDocUsuario();
					ls_numeroDocumento     = ltucnui_usuario.getNumDocUsuario();

					validarTipoDocumento(ls_tipoDocumento, lce_codigoSalida, ldm_manager);

					validarNumeroDocumento(ls_numeroDocumento);

					validarPrimerNombreApellido(ltucnui_usuario.getPrimerNombreUsuario(), true);

					validarPrimerNombreApellido(ltucnui_usuario.getPrimerApellidoUsuario(), false);

					if(!validarCorreoElectronico(ltucnui_usuario.getCorreoElectronicoCorporativoUsuario()))
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CORREO_FORMATO_NO_VALIDO));

					lucc_usuario = validarUsuarioCuentaCupo(
						    ls_tipoDocumento, ls_numeroDocumento, ls_idCuentaCupo, lce_codigoSalida, ldm_manager, false
						);

					lucc_usuario.setEstado(EstadoCommon.I);
					lucc_usuario.setIdUsuarioModificacion(as_userId);
					lucc_usuario.setIpModificacion(as_remoteIp);

					DaoCreator.getUsuarioCuentaCupoDAO(ldm_manager).update(lucc_usuario);
				}
				else
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));
			}

			lce_codigoSalida.setCodigoError(BigInteger.valueOf(200L));

			ls_mensajeSalida = addMessage(MessagesKeys.OK);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cancelarUsuario", lb2be_e);

			ls_mensajeSalida = lb2be_e.getMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(400L));
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cancelarUsuario", le_e);

			ls_mensajeSalida = le_e.getLocalizedMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(500L));
		}
		finally
		{
			ldm_manager.close();
		}

		ltscu_return.setCodigoMensaje(lce_codigoSalida.getCodigoError());
		ltscu_return.setDescripcionMensaje(ls_mensajeSalida);

		return ltscu_return;
	}

	/**
	 * Consulta el saldo de una cuenta cupo
	 *
	 * @param atecicc_entrada Objeto contenedor de la información de la cuenta cupo a consultar
	 * @return TipoSalidaConsultarSaldo con la respuesta de la operación
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public synchronized TipoSalidaConsultarIDCuentaCupo consultarIDCuentaCupo(
	    TipoEntradaConsultarIDCuentaCupo atecicc_entrada
	)
	    throws B2BException
	{
		TipoSalidaConsultarIDCuentaCupo ltscicc_return;
		DAOManager                      ldm_manager;
		CodigoError                     lce_codigoSalida;
		String                          ls_mensajeSalida;

		ltscicc_return       = new TipoSalidaConsultarIDCuentaCupo();
		ldm_manager          = DaoManagerFactory.getDAOManager();
		lce_codigoSalida     = new CodigoError();
		ls_mensajeSalida     = "";

		try
		{
			if(atecicc_entrada == null)
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

			if(!validarModuloSE(atecicc_entrada.getModulo()))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MODULO_NO_VALIDO));

			String ls_idCuentaCupo;

			ls_idCuentaCupo = null;

			{
				TipoAdminCICCI ltacicci_admin;

				ltacicci_admin = atecicc_entrada.getAdmin();

				if(ltacicci_admin != null)
				{
					UsuarioCuentaCupo lucc_admin;
					String            ls_tipoDocumento;
					String            ls_numeroDocumento;
					String            ls_correoElectronico;

					ls_tipoDocumento         = ltacicci_admin.getTipoDocAdmin();
					ls_numeroDocumento       = ltacicci_admin.getNumDocAdmin();
					ls_correoElectronico     = ltacicci_admin.getCorreoElectronicoCorporativoUsuario();

					validarTipoDocumento(ls_tipoDocumento, lce_codigoSalida, ldm_manager);

					validarNumeroDocumento(ls_numeroDocumento);

					if(!validarCorreoElectronico(ls_correoElectronico))
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CORREO_FORMATO_NO_VALIDO));

					lucc_admin = DaoCreator.getUsuarioCuentaCupoDAO(ldm_manager)
							                   .findAdminByTipoDocNumDocCorreo(
							    ls_tipoDocumento, ls_numeroDocumento, ls_correoElectronico
							);

					if(lucc_admin == null)
					{
						Object[] loa_args;

						loa_args     = new String[2];

						loa_args[0]     = ls_tipoDocumento;
						loa_args[1]     = ls_numeroDocumento;

						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_USUARIO_NO_ADMIN_O_NO_EXISTE, loa_args));
					}

					ls_idCuentaCupo = lucc_admin.getIdCuentaCupo();
				}
			}

			{
				CuentaCupo lcc_cuentaCupo;

				lcc_cuentaCupo = validarCuentaCupo(ls_idCuentaCupo, lce_codigoSalida, ldm_manager);

				if(lcc_cuentaCupo != null)
				{
					String ls_estado;

					ls_estado = StringUtils.getStringNotNull(lcc_cuentaCupo.getEstado());

					ltscicc_return.setEstado(ls_estado);
					ltscicc_return.setID(lcc_cuentaCupo.getIdCuentaCupo() + ":" + lcc_cuentaCupo.getCodigo());
					ltscicc_return.setFechaCreacion(obtenerCalendarDesdeDate(lcc_cuentaCupo.getFechaCreacion()));
					ltscicc_return.setValorMinimo(lcc_cuentaCupo.getValorMinimo());
					ltscicc_return.setValorMaximo(lcc_cuentaCupo.getValorMaximo());
					ltscicc_return.setSaldo(lcc_cuentaCupo.getSaldo());
				}
			}

			lce_codigoSalida.setCodigoError(BigInteger.valueOf(200L));

			ls_mensajeSalida = addMessage(MessagesKeys.OK);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarIDCuentaCupo", lb2be_e);

			ls_mensajeSalida = lb2be_e.getMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(400L));

			ltscicc_return = new TipoSalidaConsultarIDCuentaCupo(
				    "", "", obtenerCalendarDesdeLocalDateTime(LocalDateTime.now()), BigDecimal.ZERO, BigDecimal.ZERO,
				    BigDecimal.ZERO, null, null
				);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarIDCuentaCupo", le_e);

			ls_mensajeSalida = le_e.getLocalizedMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(500L));

			ltscicc_return = new TipoSalidaConsultarIDCuentaCupo(
				    "", "", obtenerCalendarDesdeLocalDateTime(LocalDateTime.now()), BigDecimal.ZERO, BigDecimal.ZERO,
				    BigDecimal.ZERO, null, null
				);
		}
		finally
		{
			ldm_manager.close();
		}

		ltscicc_return.setCodigoMensaje(lce_codigoSalida.getCodigoError());
		ltscicc_return.setDescripcionMensaje(ls_mensajeSalida);

		return ltscicc_return;
	}

	/**
	 * Consulta los movimientos de una cuenta cupo
	 *
	 * @param atecm_entrada Objeto contenedor de la información de la cuenta cupo a consultar
	 * @return TipoSalidaConsultarSaldo con la respuesta de la operación
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public synchronized TipoSalidaConsultarMovimientos consultarMovimientos(
	    TipoEntradaConsultarMovimientos atecm_entrada
	)
	    throws B2BException
	{
		TipoSalidaConsultarMovimientos ltscm_return;
		DAOManager                     ldm_manager;
		CodigoError                    lce_codigoSalida;
		String                         ls_mensajeSalida;
		TipoMovimientoCMO[]            ltmcmoa_movimientos;

		ltscm_return            = new TipoSalidaConsultarMovimientos();
		ldm_manager             = DaoManagerFactory.getDAOManager();
		lce_codigoSalida        = new CodigoError();
		ls_mensajeSalida        = "";
		ltmcmoa_movimientos     = null;

		try
		{
			if(atecm_entrada == null)
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

			if(!validarModuloSE(atecm_entrada.getModulo()))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MODULO_NO_VALIDO));

			String ls_idCuentaCupo;

			ls_idCuentaCupo = atecm_entrada.getIDCuentaCupo();

			validarCuentaCupo(ls_idCuentaCupo, lce_codigoSalida, ldm_manager);

			{
				TipoAdminCMI ltacnui_admin;

				ltacnui_admin = atecm_entrada.getAdmin();

				if(ltacnui_admin != null)
				{
					String ls_tipoDocumento;
					String ls_numeroDocumento;

					ls_tipoDocumento       = ltacnui_admin.getTipoDocAdmin();
					ls_numeroDocumento     = ltacnui_admin.getNumDocAdmin();

					validarTipoDocumento(ls_tipoDocumento, lce_codigoSalida, ldm_manager);

					validarNumeroDocumento(ls_numeroDocumento);

					validarUsuarioCuentaCupo(
					    ls_tipoDocumento, ls_numeroDocumento, ls_idCuentaCupo, lce_codigoSalida, ldm_manager, true
					);
				}
				else
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));
			}

			Collection<MaestroMovimientoCuentaCupo> lcmmcc_movimientos;

			lcmmcc_movimientos = consultarMovimientosEnRangoFechas(
				    ls_idCuentaCupo, obtenerLocalDateTime(atecm_entrada.getFechaInicial()),
				    obtenerLocalDateTime(atecm_entrada.getFechaFinal()), ldm_manager
				);

			if(CollectionUtils.isValidCollection(lcmmcc_movimientos))
			{
				ltmcmoa_movimientos = obtenerDetalleMovimientosCuentaCupo(lcmmcc_movimientos, ldm_manager);

				if(ltmcmoa_movimientos == null)
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_INTERNO_SISTEMA));
			}
			else
			{
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(422L));

				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_MOVIMIENTOS_EN_RANGO_FECHAS));
			}

			lce_codigoSalida.setCodigoError(BigInteger.valueOf(200L));

			ls_mensajeSalida = addMessage(MessagesKeys.OK);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarMovimientos", lb2be_e);

			ls_mensajeSalida = lb2be_e.getMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(400L));

			ltmcmoa_movimientos     = new TipoMovimientoCMO[1];

			ltmcmoa_movimientos[0] = new TipoMovimientoCMO(
				    "", Calendar.getInstance(), "", new TipoNotaCreditoCMO("", null), new TipoReciboCajaCMO("", null)
				);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarMovimientos", le_e);

			ls_mensajeSalida = le_e.getLocalizedMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(500L));

			ltmcmoa_movimientos     = new TipoMovimientoCMO[1];

			ltmcmoa_movimientos[0] = new TipoMovimientoCMO(
				    "", Calendar.getInstance(), "", new TipoNotaCreditoCMO("", null), new TipoReciboCajaCMO("", null)
				);
		}
		finally
		{
			ldm_manager.close();
		}

		ltscm_return.setMovimientos(ltmcmoa_movimientos);
		ltscm_return.setCodigoMensaje(lce_codigoSalida.getCodigoError());
		ltscm_return.setDescripcionMensaje(ls_mensajeSalida);

		return ltscm_return;
	}

	/**
	 * Consulta el saldo de una cuenta cupo
	 *
	 * @param atecs_entrada Objeto contenedor de la información de la cuenta cupo a consultar
	 * @return TipoSalidaConsultarSaldo con la respuesta de la operación
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public synchronized TipoSalidaConsultarSaldo consultarSaldo(TipoEntradaConsultarSaldo atecs_entrada)
	    throws B2BException
	{
		TipoSalidaConsultarSaldo ltscs_return;
		DAOManager               ldm_manager;
		CodigoError              lce_codigoSalida;
		String                   ls_mensajeSalida;

		ltscs_return         = new TipoSalidaConsultarSaldo();
		ldm_manager          = DaoManagerFactory.getDAOManager();
		lce_codigoSalida     = new CodigoError();
		ls_mensajeSalida     = "";

		try
		{
			if(atecs_entrada == null)
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

			if(!validarModuloSE(atecs_entrada.getModulo()))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MODULO_NO_VALIDO));

			String  ls_idCuentaCupo;
			boolean lb_administrador;

			ls_idCuentaCupo      = atecs_entrada.getIDCuentaCupo();
			lb_administrador     = false;

			{
				CuentaCupo lcc_cuentaCupo;

				lcc_cuentaCupo = validarCuentaCupo(ls_idCuentaCupo, lce_codigoSalida, ldm_manager);

				if(lcc_cuentaCupo != null)
				{
					BigDecimal lbd_saldo;

					lbd_saldo = lcc_cuentaCupo.getSaldo();

					ltscs_return.setSaldoActual((lbd_saldo != null) ? lbd_saldo : BigDecimal.valueOf(0L));
				}
			}

			{
				String ls_tipoDocumento;
				String ls_numeroDocumento;

				ls_tipoDocumento       = null;
				ls_numeroDocumento     = null;

				{
					TipoAdminCSI ltacsi_admin;

					ltacsi_admin = atecs_entrada.getAdmin();

					if(ltacsi_admin != null)
					{
						String ls_tipoDocAdmin;
						String ls_numDocAdmin;

						ls_tipoDocAdmin     = ltacsi_admin.getTipoDocAdmin();
						ls_numDocAdmin      = ltacsi_admin.getNumDocAdmin();

						if(StringUtils.isValidString(ls_tipoDocAdmin) || StringUtils.isValidString(ls_numDocAdmin))
						{
							ls_tipoDocumento       = ls_tipoDocAdmin;
							ls_numeroDocumento     = ls_numDocAdmin;

							lb_administrador = true;
						}
					}
				}

				{
					TipoUsuarioCSI ltucsi_usuario;

					ltucsi_usuario = atecs_entrada.getUsuario();

					if(
					    (ltucsi_usuario != null) && !StringUtils.isValidString(ls_tipoDocumento)
						    && !StringUtils.isValidString(ls_numeroDocumento)
					)
					{
						String ls_tipoDocUsuario;
						String ls_numDocUsuario;

						ls_tipoDocUsuario     = ltucsi_usuario.getTipoDocUsuario();
						ls_numDocUsuario      = ltucsi_usuario.getNumDocUsuario();

						if(StringUtils.isValidString(ls_tipoDocUsuario) || StringUtils.isValidString(ls_numDocUsuario))
						{
							ls_tipoDocumento       = ls_tipoDocUsuario;
							ls_numeroDocumento     = ls_numDocUsuario;
						}
					}
				}

				if(StringUtils.isValidString(ls_tipoDocumento))
					validarTipoDocumento(ls_tipoDocumento, lce_codigoSalida, ldm_manager);

				if(StringUtils.isValidString(ls_numeroDocumento))
					validarNumeroDocumento(ls_numeroDocumento);

				if(StringUtils.isValidString(ls_tipoDocumento) && StringUtils.isValidString(ls_numeroDocumento))
					validarUsuarioCuentaCupo(
					    ls_tipoDocumento, ls_numeroDocumento, ls_idCuentaCupo, lce_codigoSalida, ldm_manager,
					    lb_administrador
					);
			}

			{
				NotaCreditoCuentaCupo lnccc_notaCredito;

				lnccc_notaCredito = DaoCreator.getNotaCreditoCuentaCupoDAO(ldm_manager)
						                          .findLatestOrOldestByIdCuentaCupo(ls_idCuentaCupo);

				if(lnccc_notaCredito != null)
				{
					ltscs_return.setUltimaRecargaFecha(obtenerCalendarDesdeDate(lnccc_notaCredito.getFecha()));
					ltscs_return.setUltimaRecargaValor(lnccc_notaCredito.getValorRecarga());
				}
				else
				{
					lce_codigoSalida.setCodigoError(BigInteger.valueOf(421L));

					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SALDO_NO_DISPONIBLE));
				}
			}

			lce_codigoSalida.setCodigoError(BigInteger.valueOf(200L));

			ls_mensajeSalida = addMessage(MessagesKeys.OK);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarSaldo", lb2be_e);

			ls_mensajeSalida = lb2be_e.getMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(400L));

			ltscs_return = new TipoSalidaConsultarSaldo(
				    BigDecimal.ZERO, Calendar.getInstance(), BigDecimal.ZERO, null, null
				);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarSaldo", le_e);

			ls_mensajeSalida = le_e.getLocalizedMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(500L));

			ltscs_return = new TipoSalidaConsultarSaldo(
				    BigDecimal.ZERO, Calendar.getInstance(), BigDecimal.ZERO, null, null
				);
		}
		finally
		{
			ldm_manager.close();
		}

		ltscs_return.setCodigoMensaje(lce_codigoSalida.getCodigoError());
		ltscs_return.setDescripcionMensaje(ls_mensajeSalida);

		return ltscs_return;
	}

	/**
	 * Consulta un usuario de una cuenta cupo
	 *
	 * @param atecu_entrada Objeto contenedor de la información del usuario a consultar
	 * @return TipoSalidaConsultarUsuario con la respuesta de la operación
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public synchronized TipoSalidaConsultarUsuario consultarUsuario(TipoEntradaConsultarUsuario atecu_entrada)
	    throws B2BException
	{
		TipoSalidaConsultarUsuario ltscu_return;
		DAOManager                 ldm_manager;
		CodigoError                lce_codigoSalida;
		String                     ls_mensajeSalida;
		TipoUsuarioCUO             ltucuo_usuarioSalida;

		ltscu_return             = new TipoSalidaConsultarUsuario();
		ldm_manager              = DaoManagerFactory.getDAOManager();
		lce_codigoSalida         = new CodigoError();
		ls_mensajeSalida         = "";
		ltucuo_usuarioSalida     = null;

		try
		{
			if(atecu_entrada == null)
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

			if(!validarModuloSE(atecu_entrada.getModulo()))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MODULO_NO_VALIDO));

			String ls_idCuentaCupo;

			ls_idCuentaCupo = atecu_entrada.getIDCuentaCupo();

			validarCuentaCupo(ls_idCuentaCupo, lce_codigoSalida, ldm_manager);

			{
				TipoAdminCUI ltacui_admin;

				ltacui_admin = atecu_entrada.getAdmin();

				if(ltacui_admin != null)
				{
					String ls_tipoDocumento;
					String ls_numeroDocumento;

					ls_tipoDocumento       = ltacui_admin.getTipoDocAdmin();
					ls_numeroDocumento     = ltacui_admin.getNumDocAdmin();

					validarTipoDocumento(ls_tipoDocumento, lce_codigoSalida, ldm_manager);

					validarNumeroDocumento(ls_numeroDocumento);

					validarUsuarioCuentaCupo(
					    ls_tipoDocumento, ls_numeroDocumento, ls_idCuentaCupo, lce_codigoSalida, ldm_manager, true
					);
				}
				else
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));
			}

			{
				TipoUsuarioCUI    ltucui_usuario;
				UsuarioCuentaCupo lucc_usuario;

				ltucui_usuario     = atecu_entrada.getUsuario();
				lucc_usuario       = null;

				if(ltucui_usuario != null)
				{
					String ls_tipoDocumento;
					String ls_numeroDocumento;

					ls_tipoDocumento       = ltucui_usuario.getTipoDocUsuario();
					ls_numeroDocumento     = ltucui_usuario.getNumDocUsuario();

					validarTipoDocumento(ls_tipoDocumento, lce_codigoSalida, ldm_manager);

					validarNumeroDocumento(ls_numeroDocumento);

					lucc_usuario = validarUsuarioCuentaCupo(
						    ls_tipoDocumento, ls_numeroDocumento, ls_idCuentaCupo, lce_codigoSalida, ldm_manager, false,
						    false
						);
				}

				if(lucc_usuario != null)
				{
					String ls_estado;

					ls_estado     = StringUtils.getStringNotNull(lucc_usuario.getEstado());

					ltucuo_usuarioSalida = new TipoUsuarioCUO(
						    lucc_usuario.getTipoDocumento(), lucc_usuario.getNumeroDocumento(),
						    lucc_usuario.getPrimerNombre(), lucc_usuario.getSegundoNombre(),
						    lucc_usuario.getPrimerApellido(), lucc_usuario.getSegundoApellido(),
						    lucc_usuario.getCorreoElectronico(), ls_estado,
						    obtenerCalendarDesdeDate(lucc_usuario.getFechaCreacion()), null
						);

					if(ls_estado.equals(EstadoCommon.I))
						ltucuo_usuarioSalida.setFechaCancelacion(
						    obtenerCalendarDesdeDate(lucc_usuario.getFechaModificacion())
						);
				}
				else
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_USUARIO_CUENTA_CUPO_NO_EXISTE));
			}

			lce_codigoSalida.setCodigoError(BigInteger.valueOf(200L));

			ls_mensajeSalida = addMessage(MessagesKeys.OK);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarUsuario", lb2be_e);

			ls_mensajeSalida = lb2be_e.getMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(400L));

			ltucuo_usuarioSalida = new TipoUsuarioCUO("", "", "", null, "", null, "", "", null, null);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarUsuario", le_e);

			ls_mensajeSalida = le_e.getLocalizedMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(500L));

			ltucuo_usuarioSalida = new TipoUsuarioCUO("", "", "", null, "", null, "", "", null, null);
		}
		finally
		{
			ldm_manager.close();
		}

		ltscu_return.setUsuario(ltucuo_usuarioSalida);
		ltscu_return.setCodigoMensaje(lce_codigoSalida.getCodigoError());
		ltscu_return.setDescripcionMensaje(ls_mensajeSalida);

		return ltscu_return;
	}

	/**
	 * Consulta los usuarios de una cuenta cupo
	 *
	 * @param atecu_entrada Objeto contenedor de la información del usuario a consultar
	 * @return TipoSalidaConsultarUsuarios con la respuesta de la operación
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public synchronized TipoSalidaConsultarUsuarios consultarUsuarios(TipoEntradaConsultarUsuarios atecu_entrada)
	    throws B2BException
	{
		TipoSalidaConsultarUsuarios ltscu_return;
		DAOManager                  ldm_manager;
		TipoUsuarioCUSO[]           ltucusoa_usuarios;
		CodigoError                 lce_codigoSalida;
		String                      ls_mensajeSalida;

		ltscu_return          = new TipoSalidaConsultarUsuarios();
		ldm_manager           = DaoManagerFactory.getDAOManager();
		ltucusoa_usuarios     = null;
		lce_codigoSalida      = new CodigoError();
		ls_mensajeSalida      = "";

		try
		{
			if(atecu_entrada == null)
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

			if(!validarModuloSE(atecu_entrada.getModulo()))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MODULO_NO_VALIDO));

			String ls_idCuentaCupo;

			ls_idCuentaCupo = atecu_entrada.getIDCuentaCupo();

			validarCuentaCupo(ls_idCuentaCupo, lce_codigoSalida, ldm_manager);

			{
				TipoAdminCUSI ltacui_admin;

				ltacui_admin = atecu_entrada.getAdmin();

				if(ltacui_admin != null)
				{
					String ls_tipoDocumento;
					String ls_numeroDocumento;

					ls_tipoDocumento       = ltacui_admin.getTipoDocAdmin();
					ls_numeroDocumento     = ltacui_admin.getNumDocAdmin();

					validarTipoDocumento(ls_tipoDocumento, lce_codigoSalida, ldm_manager);

					validarNumeroDocumento(ls_numeroDocumento);

					validarUsuarioCuentaCupo(
					    ls_tipoDocumento, ls_numeroDocumento, ls_idCuentaCupo, lce_codigoSalida, ldm_manager, true
					);
				}
				else
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));
			}

			{
				Collection<UsuarioCuentaCupo> lcucc_usuarios;

				lcucc_usuarios = DaoCreator.getUsuarioCuentaCupoDAO(ldm_manager).findByIdCuentaCupo(ls_idCuentaCupo);

				if(CollectionUtils.isValidCollection(lcucc_usuarios))
				{
					int li_cont;

					ltucusoa_usuarios     = new TipoUsuarioCUSO[lcucc_usuarios.size()];
					li_cont               = 0;

					for(UsuarioCuentaCupo lucc_usuario : lcucc_usuarios)
					{
						if(lucc_usuario != null)
						{
							String ls_estado;

							ls_estado     = StringUtils.getStringNotNull(lucc_usuario.getEstado());

							ltucusoa_usuarios[li_cont] = new TipoUsuarioCUSO(
								    lucc_usuario.getTipoDocumento(), lucc_usuario.getNumeroDocumento(),
								    lucc_usuario.getPrimerNombre(), lucc_usuario.getSegundoNombre(),
								    lucc_usuario.getPrimerApellido(), lucc_usuario.getSegundoApellido(),
								    lucc_usuario.getCorreoElectronico(), ls_estado,
								    obtenerCalendarDesdeDate(lucc_usuario.getFechaCreacion()), null,
								    lucc_usuario.getTipoUsuario()
								);

							if(ls_estado.equals(EstadoCommon.I))
								ltucusoa_usuarios[li_cont].setFechaCancelacion(
								    obtenerCalendarDesdeDate(lucc_usuario.getFechaModificacion())
								);

							li_cont++;
						}
					}
				}
				else
				{
					lce_codigoSalida.setCodigoError(BigInteger.valueOf(404L));

					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS));
				}
			}

			if((ltucusoa_usuarios == null) || (ltucusoa_usuarios.length <= 0))
			{
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(404L));

				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS));
			}

			lce_codigoSalida.setCodigoError(BigInteger.valueOf(200L));

			ls_mensajeSalida = addMessage(MessagesKeys.OK);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarUsuarios", lb2be_e);

			ls_mensajeSalida = lb2be_e.getMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(400L));

			ltucusoa_usuarios     = new TipoUsuarioCUSO[1];

			ltucusoa_usuarios[0] = new TipoUsuarioCUSO("", "", "", null, "", null, "", "", null, null, null);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarUsuarios", le_e);

			ls_mensajeSalida = le_e.getLocalizedMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(500L));

			ltucusoa_usuarios     = new TipoUsuarioCUSO[1];

			ltucusoa_usuarios[0] = new TipoUsuarioCUSO("", "", "", null, "", null, "", "", null, null, null);
		}
		finally
		{
			ldm_manager.close();
		}

		ltscu_return.setUsuarios(ltucusoa_usuarios);
		ltscu_return.setCodigoMensaje(lce_codigoSalida.getCodigoError());
		ltscu_return.setDescripcionMensaje(ls_mensajeSalida);

		return ltscu_return;
	}

	/**
	 * Asocia un usuario a una cuenta cupo para que pueda administrarla
	 *
	 * @param ateiu_entrada Objeto contenedor de la información de la cuenta cupo y los usuarios
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return Objeto contenedor con el resultado de la operación
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public synchronized TipoSalidaInscribirUsuario inscribirUsuario(
	    TipoEntradaInscribirUsuario ateiu_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaInscribirUsuario ltsiu_return;
		DAOManager                 ldm_manager;
		CodigoError                lce_codigoSalida;
		String                     ls_mensajeSalida;

		ltsiu_return         = new TipoSalidaInscribirUsuario();
		ldm_manager          = DaoManagerFactory.getDAOManager();
		lce_codigoSalida     = new CodigoError();
		ls_mensajeSalida     = "";

		try
		{
			if(ateiu_entrada == null)
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

			if(!validarModuloSE(ateiu_entrada.getModulo()))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MODULO_NO_VALIDO));

			String ls_idCuentaCupo;
			String ls_correoElectronicoAdmin;

			ls_idCuentaCupo               = ateiu_entrada.getIDCuentaCupo();
			ls_correoElectronicoAdmin     = "";

			validarCuentaCupo(ls_idCuentaCupo, lce_codigoSalida, ldm_manager);

			{
				TipoAdminIUI ltaiui_admin;

				ltaiui_admin = ateiu_entrada.getAdmin();

				if(ltaiui_admin != null)
				{
					String ls_tipoDocumento;
					String ls_numeroDocumento;

					ls_tipoDocumento       = ltaiui_admin.getTipoDocAdmin();
					ls_numeroDocumento     = ltaiui_admin.getNumDocAdmin();

					validarTipoDocumento(ls_tipoDocumento, lce_codigoSalida, ldm_manager);

					validarNumeroDocumento(ls_numeroDocumento);

					{
						UsuarioCuentaCupo lucc_usuario;

						lucc_usuario = validarUsuarioCuentaCupo(
							    ls_tipoDocumento, ls_numeroDocumento, ls_idCuentaCupo, lce_codigoSalida, ldm_manager,
							    true
							);

						if(lucc_usuario != null)
							ls_correoElectronicoAdmin = lucc_usuario.getCorreoElectronico();
					}
				}
				else
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));
			}

			{
				TipoUsuarioIUI ltuiui_usuario;

				ltuiui_usuario = ateiu_entrada.getUsuario();

				if(ltuiui_usuario != null)
				{
					String               ls_tipoDocumento;
					String               ls_numeroDocumento;
					UsuarioCuentaCupoDAO luccd_usuarioCuentaCupoDAO;
					UsuarioCuentaCupo    lucc_usuario;
					boolean              lb_reactivarUsuario;

					ls_tipoDocumento               = ltuiui_usuario.getTipoDocUsuario();
					ls_numeroDocumento             = ltuiui_usuario.getNumDocUsuario();
					luccd_usuarioCuentaCupoDAO     = DaoCreator.getUsuarioCuentaCupoDAO(ldm_manager);

					validarTipoDocumento(ls_tipoDocumento, lce_codigoSalida, ldm_manager);

					validarNumeroDocumento(ls_numeroDocumento);

					lucc_usuario     = luccd_usuarioCuentaCupoDAO.findByIdCuentaAndDoc(
						    ls_idCuentaCupo, ls_tipoDocumento, ls_numeroDocumento
						);

					lb_reactivarUsuario = lucc_usuario != null;

					if(lb_reactivarUsuario)
					{
						String ls_estado;

						ls_estado = StringUtils.getStringNotNull(lucc_usuario.getEstado());

						if(ls_estado.equals(EstadoCommon.A))
						{
							Object[] loa_args;

							loa_args     = new String[3];

							loa_args[0]     = ls_tipoDocumento;
							loa_args[1]     = ls_numeroDocumento;
							loa_args[2]     = ls_idCuentaCupo;

							throw new B2BException(
							    addMessageGCC(ErrorKeys.ERROR_USUARIO_YA_INSCRITO_A_CUENTA_CUPO, loa_args)
							);
						}
					}

					validarPrimerNombreApellido(ltuiui_usuario.getPrimerNombreUsuario(), true);

					validarPrimerNombreApellido(ltuiui_usuario.getPrimerApellidoUsuario(), false);

					{
						String ls_correoUsuario;

						ls_correoUsuario = ltuiui_usuario.getCorreoElectronicoCorporativoUsuario();

						if(validarCorreoElectronico(ls_correoUsuario))
						{
							String[] lsa_partesCorreoUsuario;
							String[] lsa_partesCorreoAdmin;

							lsa_partesCorreoUsuario     = ls_correoUsuario.split(IdentificadoresCommon.ARROBA);
							lsa_partesCorreoAdmin       = ls_correoElectronicoAdmin.split(IdentificadoresCommon.ARROBA);

							if(
							    (lsa_partesCorreoUsuario != null) && (lsa_partesCorreoUsuario.length > 1)
								    && (lsa_partesCorreoAdmin != null) && (lsa_partesCorreoAdmin.length > 1)
							)
							{
								String ls_dominioUsuario;
								String ls_dominioAdmin;

								ls_dominioUsuario     = lsa_partesCorreoUsuario[1];
								ls_dominioAdmin       = lsa_partesCorreoAdmin[1];

								if(
								    !(StringUtils.isValidString(ls_dominioUsuario)
									    && StringUtils.isValidString(ls_dominioAdmin)
									    && ls_dominioUsuario.equalsIgnoreCase(ls_dominioAdmin))
								)
									throw new B2BException(
									    addMessageGCC(ErrorKeys.ERROR_DOMINIO_CORREO_USUARIO_NO_VALIDO)
									);
							}
							else
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_INTERNO_SISTEMA));
						}
						else
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CORREO_USUARIO_NO_VALIDO));

						{
							UsuarioCuentaCupo lucc_usuarioCorreo;

							lucc_usuarioCorreo = luccd_usuarioCuentaCupoDAO.findByCorreoElectronico(
								    StringUtils.getStringUpperCase(ls_correoUsuario)
								);

							if((lucc_usuarioCorreo != null) && !lb_reactivarUsuario)
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CORREO_USUARIO_YA_EXISTENTE));
						}
					}

					if(lb_reactivarUsuario)
					{
						lucc_usuario.setEstado(EstadoCommon.A);
						lucc_usuario.setIdUsuarioModificacion(as_userId);
						lucc_usuario.setIpModificacion(as_remoteIp);

						luccd_usuarioCuentaCupoDAO.update(lucc_usuario);
					}
					else
					{
						UsuarioCuentaCupo lucc_usuarioInsert;

						lucc_usuarioInsert = new UsuarioCuentaCupo(ltuiui_usuario);

						lucc_usuarioInsert.setIdCuentaCupo(ls_idCuentaCupo);
						lucc_usuarioInsert.setEstado(EstadoCommon.A);
						lucc_usuarioInsert.setTipoUsuario(TipoUsuarioCuentaCupoCommon.USUARIO);
						lucc_usuarioInsert.setIdUsuarioCreacion(as_userId);
						lucc_usuarioInsert.setIpCreacion(as_remoteIp);

						luccd_usuarioCuentaCupoDAO.insert(lucc_usuarioInsert);
					}
				}
				else
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));
			}

			lce_codigoSalida.setCodigoError(BigInteger.valueOf(200L));

			ls_mensajeSalida = addMessage(MessagesKeys.OK);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("inscribirUsuario", lb2be_e);

			ls_mensajeSalida = lb2be_e.getMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(400L));
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("inscribirUsuario", le_e);

			ls_mensajeSalida = le_e.getLocalizedMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(500L));
		}
		finally
		{
			ldm_manager.close();
		}

		ltsiu_return.setCodigoMensaje(lce_codigoSalida.getCodigoError());
		ltsiu_return.setDescripcionMensaje(ls_mensajeSalida);

		return ltsiu_return;
	}

	/**
	 * Realiza un pago por medio de una cuenta cupo
	 *
	 * @param atepcc_entrada Objeto contenedor de la información de la cuenta cupo a utilizar como medio de pago
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return TipoSalidaPagarCuentaCupo con la respuesta de la operación
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public synchronized TipoSalidaPagarCuentaCupo pagarCuentaCupo(
	    TipoEntradaPagarCuentaCupo atepcc_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaPagarCuentaCupo ltspcc_return;
		DAOManager                ldm_manager;
		CodigoError               lce_codigoSalida;
		String                    ls_mensajeSalida;

		ltspcc_return        = new TipoSalidaPagarCuentaCupo();
		ldm_manager          = DaoManagerFactory.getDAOManager();
		lce_codigoSalida     = new CodigoError();
		ls_mensajeSalida     = "";

		try
		{
			if(atepcc_entrada == null)
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

			if(!validarModuloSE(atepcc_entrada.getModulo()))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MODULO_NO_VALIDO));

			String         ls_idCuentaCupo;
			CuentaCupo     lcc_cuentaCupo;
			PagoCuentaCupo lpcc_pago;

			ls_idCuentaCupo     = atepcc_entrada.getIDCuentaCupo();
			lcc_cuentaCupo      = validarCuentaCupo(ls_idCuentaCupo, lce_codigoSalida, ldm_manager);
			lpcc_pago           = new PagoCuentaCupo();

			lpcc_pago.setIdCuentaCupo(ls_idCuentaCupo);

			{
				String ls_correoElectronicoUsuario;

				ls_correoElectronicoUsuario = atepcc_entrada.getCorreoElectronicoCorporativoUsuario();

				if(validarCorreoElectronico(ls_correoElectronicoUsuario))
				{
					UsuarioCuentaCupo lucc_usuario;

					lucc_usuario = DaoCreator.getUsuarioCuentaCupoDAO(ldm_manager)
							                     .findByCorreoElectronico(ls_correoElectronicoUsuario);

					if(lucc_usuario == null)
					{
						Object[] loa_args;

						loa_args     = new String[1];

						loa_args[0] = ls_correoElectronicoUsuario;

						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CORREO_USUARIO_NO_EXISTENTE, loa_args));
					}

					validarUsuarioCuentaCupo(
					    lucc_usuario.getTipoDocumento(), lucc_usuario.getNumeroDocumento(), ls_idCuentaCupo,
					    lce_codigoSalida, ldm_manager, false
					);

					lpcc_pago.setIdUsuarioCuentaCupo(lucc_usuario.getIdUsuario());
				}
				else
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CORREO_FORMATO_NO_VALIDO));
			}

			{
				BigDecimal lbd_montoPago;

				lbd_montoPago = atepcc_entrada.getMontoPago();

				if(!NumericUtils.isValidBigDecimal(lbd_montoPago))
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MONTO_PAGO_NO_VALIDO));

				{
					BigDecimal lbd_saldo;

					lbd_saldo = lcc_cuentaCupo.getSaldo();

					if(!(NumericUtils.isValidBigDecimal(lbd_saldo) && (lbd_saldo.compareTo(lbd_montoPago) >= 0)))
					{
						lce_codigoSalida.setCodigoError(BigInteger.valueOf(423L));

						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CUENTA_CUPO_SIN_SALDO));
					}
				}

				lpcc_pago.setValor(lbd_montoPago);
			}

			{
				LocalDateTime lldt_fechaPago;

				lldt_fechaPago = obtenerLocalDateTime(atepcc_entrada.getFechaPago());

				if(lldt_fechaPago != null)
				{
					if(lldt_fechaPago.isAfter(LocalDateTime.now()))
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_FECHA_PAGO_NO_VALIDA));
				}
				else
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_FECHA_PAGO));

				lpcc_pago.setFechaPago(lldt_fechaPago);
			}

			{
				String ls_referenciaPago;

				ls_referenciaPago = atepcc_entrada.getReferenciaPago();

				if(!StringUtils.isValidString(ls_referenciaPago))
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_REFERENCIA_PAGO));

				lpcc_pago.setReferenciaPago(ls_referenciaPago);
			}

			{
				String ls_idMovimientoCreado;

				ls_idMovimientoCreado = DaoCreator.getProcedimientosDAO(ldm_manager)
						                              .procPagarCuentaCupo(lpcc_pago, as_userId, as_remoteIp);

				if(!StringUtils.isValidString(ls_idMovimientoCreado))
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_ID_MOVIMIENTO));

				lpcc_pago.setIdMovimiento(ls_idMovimientoCreado);
			}

			lpcc_pago.setEstado(EstadoCommon.A);
			lpcc_pago.setIdUsuarioCreacion(as_userId);
			lpcc_pago.setIpCreacion(as_remoteIp);

			DaoCreator.getPagoCuentaCupoDAO(ldm_manager).insert(lpcc_pago);

			lce_codigoSalida.setCodigoError(BigInteger.valueOf(200L));

			ls_mensajeSalida = addMessage(MessagesKeys.OK);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("pagarCuentaCupo", lb2be_e);

			ls_mensajeSalida = lb2be_e.getMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(400L));
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("pagarCuentaCupo", le_e);

			ls_mensajeSalida = le_e.getLocalizedMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(500L));
		}
		finally
		{
			ldm_manager.close();
		}

		ltspcc_return.setCodigoMensaje(lce_codigoSalida.getCodigoError());
		ltspcc_return.setDescripcionMensaje(ls_mensajeSalida);

		return ltspcc_return;
	}

	/**
	 * Verifica el primer nombre o primer apellido ingresado.
	 *
	 * @param as_valor primer nombre o apellido a validar
	 * @param ab_nombre true para indicar que se valida el primer nombre, false para el primer apellido
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	private void validarPrimerNombreApellido(String as_valor, boolean ab_nombre)
	    throws B2BException
	{
		if(!StringUtils.isValidString(as_valor))
			throw new B2BException(
			    addMessageGCC(ab_nombre ? ErrorKeys.ERROR_SIN_PRIMER_NOMBRE : ErrorKeys.ERROR_SIN_PRIMER_APELLIDO)
			);

		if(!ExpresionRegular.getExpresionRegular().esSoloLetras(as_valor))
			throw new B2BException(
			    addMessageGCC(
			        ab_nombre ? ErrorKeys.ERROR_PRIMER_NOMBRE_NO_VALIDO : ErrorKeys.ERROR_PRIMER_APELLIDO_NO_VALIDO
			    )
			);
	}

	/**
	 * Verifica que el número de teléfono ingresado sea válido
	 *
	 * @param as_telefono Objeto contenedor del número de teléfono a validar
	 * @param as_tipoDocumento tipo de documento de la persona asociada al teléfono
	 * @param as_numeroDocumento número de documento de la persona asociada al teléfono
	 * @param adm_manager Conexión a la base de datos
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	private void validarTelefono(
	    String as_telefono, String as_tipoDocumento, String as_numeroDocumento, DAOManager adm_manager
	)
	    throws B2BException
	{
		if(!StringUtils.isValidString(as_telefono))
		{
			Object[] loa_args;

			loa_args     = new String[2];

			loa_args[0]     = as_tipoDocumento;
			loa_args[1]     = as_numeroDocumento;

			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_TELEFONO, loa_args));
		}

		ConstantesDAO lcd_constantesDAO;
		Constantes    lc_digitosTelFijo;
		Constantes    lc_digitosTelMovil;

		lcd_constantesDAO      = DaoCreator.getConstantesDAO(adm_manager);
		lc_digitosTelFijo      = lcd_constantesDAO.findById(ConstanteCommon.DIGITOS_TEL_FIJO_COL);
		lc_digitosTelMovil     = lcd_constantesDAO.findById(ConstanteCommon.DIGITOS_TEL_MOVIL_COL);

		if((lc_digitosTelFijo != null) && (lc_digitosTelMovil != null))
		{
			BigInteger lbi_digitosFijo;
			BigInteger lbi_digitosMovil;
			boolean    lb_digitosFijoValido;

			lbi_digitosFijo          = lc_digitosTelFijo.getEntero();
			lbi_digitosMovil         = lc_digitosTelMovil.getEntero();
			lb_digitosFijoValido     = NumericUtils.isValidBigInteger(lbi_digitosFijo);

			if(lb_digitosFijoValido && NumericUtils.isValidBigInteger(lbi_digitosMovil))
			{
				int li_digitosTelefono;
				int li_digitosFijo;
				int li_digitosMovil;

				li_digitosTelefono     = as_telefono.length();
				li_digitosFijo         = lbi_digitosFijo.intValue();
				li_digitosMovil        = lbi_digitosMovil.intValue();

				if(!((li_digitosTelefono == li_digitosFijo) || (li_digitosTelefono == li_digitosMovil)))
				{
					Object[] loa_args;

					loa_args     = new String[4];

					loa_args[0]     = as_tipoDocumento;
					loa_args[1]     = as_numeroDocumento;
					loa_args[2]     = StringUtils.getString(li_digitosFijo);
					loa_args[3]     = StringUtils.getString(li_digitosMovil);

					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TELEFONO_NO_VALIDO, loa_args));
				}
			}
			else
			{
				Object[] loa_args;

				loa_args     = new String[1];

				loa_args[0] = (!lb_digitosFijoValido) ? ConstanteCommon.DIGITOS_TEL_FIJO_COL
					                                  : ConstanteCommon.DIGITOS_TEL_MOVIL_COL;

				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_ENTERO_CONSTANTE_NO_VALIDO, loa_args));
			}
		}
		else
		{
			Object[] loa_args;

			loa_args     = new String[1];

			loa_args[0] = (lc_digitosTelFijo == null) ? ConstanteCommon.DIGITOS_TEL_FIJO_COL
				                                      : ConstanteCommon.DIGITOS_TEL_MOVIL_COL;

			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args));
		}
	}

	/**
	 * Verifica que el tipo documento ingresado sea válido y exista.
	 *
	 * @param as_tipoDocUsuario id del tipo documento a validar
	 * @param adm_manager Conexión a la base de datos
	 * @throws B2BException Si no se cumple una regla de negocio.
	 */
	private void validarTipoDocumento(String as_tipoDocUsuario, CodigoError ace_codigoSalida, DAOManager adm_manager)
	    throws B2BException
	{
		if(!StringUtils.isValidString(as_tipoDocUsuario))
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_TIPO_DOCUMENTO));

		InteresadoDocumentoTipo lidt_tipoDoc;

		lidt_tipoDoc = DaoCreator.getInteresadoDocumentoTipoDAO(adm_manager).findById(as_tipoDocUsuario);

		if(lidt_tipoDoc == null)
		{
			Object[] loa_args;

			loa_args     = new String[1];

			loa_args[0] = as_tipoDocUsuario;

			ace_codigoSalida.setCodigoError(BigInteger.valueOf(455L));

			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TIPO_DOCUMENTO_NO_VALIDO, loa_args));
		}
	}

	/**
	 * Consultar saldos nota credito.
	 *
	 * @param atecsnc_entrada de atecsnc entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar saldos nota credito
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarSaldosNotaCredito consultarSaldosNotaCredito(
	    TipoEntradaConsultarSaldosNotaCredito atecsnc_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaConsultarSaldosNotaCredito ltscsnc_return;
		DAOManager                           ldm_manager;
		CodigoError                          lce_codigoSalida;
		String                               ls_mensajeSalida;

		ltscsnc_return       = new TipoSalidaConsultarSaldosNotaCredito();
		ldm_manager          = DaoManagerFactory.getDAOManager();
		lce_codigoSalida     = new CodigoError();
		ls_mensajeSalida     = "";

		try
		{
			if(atecsnc_entrada == null)
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

			if(!validarModuloSE(atecsnc_entrada.getModulo()))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MODULO_NO_VALIDO));

			String ls_idCuentaCupo;

			ls_idCuentaCupo = atecsnc_entrada.getIDCuentaCupo();

			validarCuentaCupo(ls_idCuentaCupo, lce_codigoSalida, ldm_manager);

			{
				String ls_tipoDocumento;
				String ls_numeroDocumento;

				ls_tipoDocumento       = null;
				ls_numeroDocumento     = null;

				{
					co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoAdminCSI ltacsi_admin;

					ltacsi_admin = atecsnc_entrada.getAdmin();

					if(ltacsi_admin != null)
					{
						ls_tipoDocumento       = ltacsi_admin.getTipoDocAdmin();
						ls_numeroDocumento     = ltacsi_admin.getNumDocAdmin();
					}
				}

				validarTipoDocumento(ls_tipoDocumento, lce_codigoSalida, ldm_manager);

				validarNumeroDocumento(ls_numeroDocumento);

				validarUsuarioCuentaCupo(
				    ls_tipoDocumento, ls_numeroDocumento, ls_idCuentaCupo, lce_codigoSalida, ldm_manager, true
				);
			}

			{
				Collection<NotaCreditoCuentaCupo> lcncc_notaCreditoCuentaCupo;

				lcncc_notaCreditoCuentaCupo = DaoCreator.getNotaCreditoCuentaCupoDAO(ldm_manager)
						                                    .findAllByIdCuentaCupo(ls_idCuentaCupo);

				if(CollectionUtils.isValidCollection(lcncc_notaCreditoCuentaCupo))
				{
					TipoSalidaConsultarSaldosNotaCreditoNotasCreditos[] ltscsncnc_notascreditos;
					int                                                 li_count;

					ltscsncnc_notascreditos     = new TipoSalidaConsultarSaldosNotaCreditoNotasCreditos[lcncc_notaCreditoCuentaCupo
							.size()];
					li_count                    = 0;

					for(NotaCreditoCuentaCupo lnccc_tmp : lcncc_notaCreditoCuentaCupo)
					{
						if(lnccc_tmp != null)
						{
							TipoSalidaConsultarSaldosNotaCreditoNotasCreditosNotaCredito ltscsncncnc_notacredito;

							ltscsncncnc_notacredito     = new TipoSalidaConsultarSaldosNotaCreditoNotasCreditosNotaCredito(
								    lnccc_tmp.getCodigo(), lnccc_tmp.getFecha(),
								    StringUtils.getString(lnccc_tmp.getValorRecarga()),
								    StringUtils.getString(lnccc_tmp.getSaldo())
								);

							ltscsncnc_notascreditos[li_count] = new TipoSalidaConsultarSaldosNotaCreditoNotasCreditos(
								    ltscsncncnc_notacredito
								);

							li_count++;
						}
					}

					ltscsnc_return.setNotasCreditos(ltscsncnc_notascreditos);
				}
			}

			lce_codigoSalida.setCodigoError(BigInteger.valueOf(200L));

			ls_mensajeSalida = addMessage(MessagesKeys.OK);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarSaldosNotaCredito", lb2be_e);

			ls_mensajeSalida = lb2be_e.getMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(400L));
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarSaldosNotaCredito", le_e);

			ls_mensajeSalida = le_e.getLocalizedMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(500L));
		}
		finally
		{
			ldm_manager.close();
		}

		ltscsnc_return.setCodigoMensaje(lce_codigoSalida.getCodigoError());
		ltscsnc_return.setDescripcionMensaje(ls_mensajeSalida);

		return ltscsnc_return;
	}
}
