package com.bachue.snr.prosnr01.business.cuentaCupos;

import co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.notificarpago.v2.TipoEntradaNotificarPago;
import co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.notificarpago.v2.TipoSalidaNotificarPago;

import co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2.SBB_EF_OperacionesFinancierasProxy;
import co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2.SBB_EF_OperacionesFinancieras_PortType;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.CuentaCupoDAO;
import com.bachue.snr.prosnr01.dao.acc.DetalleMovimientoCuentaCupoDAO;
import com.bachue.snr.prosnr01.dao.acc.MaestroMovimientoCuentaCupoDAO;
import com.bachue.snr.prosnr01.dao.acc.NotaCreditoCuentaCupoDAO;
import com.bachue.snr.prosnr01.dao.acc.PagoCuentaCupoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaCorreoElectronicoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaTelefonoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.UsuarioCuentaCupoDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.CodigoError;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.cuentaCupos.AprobacionCuentaCupos;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.CuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleMovimientoCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.MaestroMovimientoCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.ModCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.NotaCreditoCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.PagoCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.acc.UsuarioCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;

import com.bachue.snr.prosnr12.common.constants.EstadoTipoMovimientoCuentaCupoCommon;
import com.bachue.snr.prosnr12.common.constants.TipoMovimientoCuentaCupoCommon;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.time.LocalDateTime;

import java.util.Collection;
import java.util.Date;
import java.util.Map;


/**
 * Clase para el manejo de la logica de negocio de cuenta cupos.
 *
 * @author Manuel Blanco
 */
public class CuentaCuposBusiness extends BaseCuentaCupo
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CuentaCuposBusiness.class);

	/**
	 * Procesa la desición del aprobador de cuenta cupo.
	 *
	 * @param aacc_datosCuenta Objeto contenedor de la información del trámite
	 * @param as_userId Id del usuario qeu ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Si no se cumple una regla de negocio.
	 */
	public synchronized void aprobarSolicitudCuentaCupo(
	    AprobacionCuentaCupos aacc_datosCuenta, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aacc_datosCuenta == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			Solicitud ls_solicitud;
			String    ls_subproceso;
			long      ll_idMotivo;
			boolean   lb_aprobar;

			ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(aacc_datosCuenta.getIdSolicitud());

			if(ls_solicitud == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

			ls_subproceso     = StringUtils.getStringNotNull(ls_solicitud.getIdSubproceso());
			lb_aprobar        = aacc_datosCuenta.isAprobar();

			switch(ls_subproceso)
			{
				case ProcesoCommon.ID_SUBPROCESO_1:
					modificarCuentaCupo(
					    aacc_datosCuenta, as_userId, as_remoteIp,
					    lb_aprobar ? EstadoCommon.ACTIVO_TXT : EstadoCommon.RECHAZADA, ldm_manager
					);

					if(lb_aprobar)
						ll_idMotivo = MotivoTramiteCommon.CREACION_CUENTA_CUPO_APROBADA;
					else
						ll_idMotivo = MotivoTramiteCommon.CREACION_CUENTA_CUPO_RECHAZADA;

					break;

				case ProcesoCommon.ID_SUBPROCESO_2:

					if(lb_aprobar)
					{
						modificarCuentaCupo(
						    aacc_datosCuenta, as_userId, as_remoteIp, EstadoCommon.ACTIVO_TXT, ldm_manager
						);

						ll_idMotivo = MotivoTramiteCommon.MODIFICACION_CUENTA_CUPO_APROBADA;
					}
					else
						ll_idMotivo = MotivoTramiteCommon.MODIFICACION_CUENTA_CUPO_RECHAZADA;

					break;

				case ProcesoCommon.ID_SUBPROCESO_3:

					if(lb_aprobar)
					{
						modificarCuentaCupo(
						    aacc_datosCuenta, as_userId, as_remoteIp, EstadoCommon.CANCELADO_TXT, ldm_manager
						);

						ll_idMotivo = MotivoTramiteCommon.CANCELACION_CUENTA_CUPO_APROBADA;
					}
					else
						ll_idMotivo = MotivoTramiteCommon.CANCELACION_CUENTA_CUPO_RECHAZADA;

					break;

				default:
					throw new B2BException(ErrorKeys.ERROR_SUBPROCESO_NO_VALIDO);
			}

			{
				TurnoHistoria lth_turnoHistoria;
				String        ls_observaciones;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                          .findById(aacc_datosCuenta.getIdTurnoHistoria());

				if(lth_turnoHistoria == null)
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);

				ls_observaciones = StringUtils.getStringNotNull(aacc_datosCuenta.getObservaciones());

				if(!StringUtils.isValidString(ls_observaciones) && !lb_aprobar)
					throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);

				if(ls_observaciones.length() > IdentificadoresCommon.LIMITE_CARACTERES_OBSERVACIONES)
					throw new B2BException(ErrorKeys.ERROR_OBSERVACIONES_TAM_4000);

				lth_turnoHistoria.setObservaciones(ls_observaciones);

				{
					byte[] lba_archivoPdf;

					lba_archivoPdf = generarConstanciaDecisionAprobacion(
						    ls_solicitud.getNir(), aacc_datosCuenta.getIdCuentaCupo(),
						    obtenerNombrePlantillaConstancia(ls_subproceso, lb_aprobar), ls_observaciones, null, null,
						    ldm_manager
						);

					DocumentosSalida lds_documento;

					lds_documento = new DocumentosSalida();

					lds_documento.setIdTurnoHistoria(NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria()));
					lds_documento.setIdSolicitud(ls_solicitud.getIdSolicitud());
					lds_documento.setIdTurno(lth_turnoHistoria.getIdTurno());
					lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
					lds_documento.setTipo(TipoArchivoCommon.CONSTANCIA_APROBACION_SOLICITUD_CUENTA_CUPO);
					lds_documento.setIdTipoDocumental(TipoDocumentalCommon.FORMULARIO_DE_SOLICITUD_CUENTA_CUPO);
					lds_documento.setEstado(EstadoCommon.ACTIVO);

					insertarDocumentoSalida(lba_archivoPdf, lds_documento, as_userId, as_remoteIp, ldm_manager);
				}

				terminarTurnoHistoriaYCrearEtapa(
				    lth_turnoHistoria, ldm_manager,
				    new MotivoTramite(EtapaCommon.ID_APROBACION_SOLICITUD_CUENTA_CUPO, ll_idMotivo), as_userId,
				    as_remoteIp, EstadoCommon.TERMINADA, true
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("aprobarSolicitudCuentaCupo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Inactivar cuenta cupo.
	 *
	 * @param aacc_datosCuenta de aacc datos cuenta
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void inactivarCuentaCupo(
	    AprobacionCuentaCupos aacc_datosCuenta, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aacc_datosCuenta == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			String ls_observaciones;

			ls_observaciones = StringUtils.getStringNotNull(aacc_datosCuenta.getObservaciones());

			{
				if(!StringUtils.isValidString(ls_observaciones))
					throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);

				if(ls_observaciones.length() > IdentificadoresCommon.LIMITE_CARACTERES_OBSERVACIONES)
					throw new B2BException(ErrorKeys.ERROR_OBSERVACIONES_TAM_4000);
			}

			Solicitud ls_solicitud;

			ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(aacc_datosCuenta.getIdSolicitud());

			if(ls_solicitud == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

			{
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder();

				{
					String ls_comentarioAnterior;

					ls_comentarioAnterior = ls_solicitud.getComentario();

					if(StringUtils.isValidString(ls_comentarioAnterior))
						lsb_sb.append(ls_comentarioAnterior + IdentificadoresCommon.SIMBOLO_COMA);

					lsb_sb.append(ls_observaciones);
				}

				ls_solicitud.setComentario(lsb_sb.toString());
				ls_solicitud.setIdUsuarioModificacion(as_userId);
				ls_solicitud.setIpModificacion(as_remoteIp);

				DaoCreator.getSolicitudDAO(ldm_manager).update(ls_solicitud);
			}

			modificarCuentaCupo(aacc_datosCuenta, as_userId, as_remoteIp, EstadoCommon.INACTIVO_TXT, ldm_manager);

			DocumentosSalida lds_documento;

			lds_documento = new DocumentosSalida();

			lds_documento.setIdSolicitud(ls_solicitud.getIdSolicitud());
			lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
			lds_documento.setIdTipoDocumental(TipoDocumentalCommon.FORMULARIO_DE_SOLICITUD_CUENTA_CUPO);
			lds_documento.setEstado(EstadoCommon.ENTREGA);

			{
				Map<String, byte[]> lmsba_archivosCargados;

				lmsba_archivosCargados = aacc_datosCuenta.getArchivosCargados();

				if(CollectionUtils.isValidCollection(lmsba_archivosCargados))
				{
					String     ls_idTipoDocumental;
					Constantes lc_tipoDocumental;

					ls_idTipoDocumental     = null;
					lc_tipoDocumental       = DaoCreator.getConstantesDAO(ldm_manager)
							                                .findById(
							    ConstanteCommon.TIPO_DOCUMENTAL_INACTIVAR_CUENTA_CUPO
							);

					if(lc_tipoDocumental != null)
						ls_idTipoDocumental = lc_tipoDocumental.getCaracter();

					lds_documento.setIdTipoDocumental(ls_idTipoDocumental);

					for(Map.Entry<String, byte[]> lmesba_documento : lmsba_archivosCargados.entrySet())
					{
						if(lmesba_documento != null)
						{
							byte[] lba_archivo;

							lba_archivo = lmesba_documento.getValue();

							if(lba_archivo != null)
								insertarDocumentoSalida(
								    lba_archivo, lds_documento, as_userId, as_remoteIp, ldm_manager
								);
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_DOCUMENTOS_ADJUNTOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("inacivarCuentaCupo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Verifica si existen pagos encolados y comienza a procesarlos.
	 *
	 * @param as_remoteIp Dirección IP del del cliente que ejecuta la acción
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	public synchronized void desencolarPago(String as_remoteIp)
	    throws B2BException
	{
		Collection<PagoCuentaCupo> lcpcc_pagos;
		DAOManager                 ldm_manager;

		lcpcc_pagos     = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			Constantes lc_constant;

			lc_constant = DaoCreator.getConstantesDAO(ldm_manager).findById(ConstanteCommon.JOB_CUENTA_CUPOS_WS_INVOKE);

			if((lc_constant != null) && BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
				lcpcc_pagos = DaoCreator.getPagoCuentaCupoDAO(ldm_manager).findByActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("desencolarPago", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcpcc_pagos))
			desencolarPago(lcpcc_pagos, as_remoteIp);
	}

	/**
	 * Prepara el job para comenzar a desencolar los trámites de pago.
	 *
	 * @param acpcc_pagos Objeto contenedor de los pagos a desencolar
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	public synchronized void desencolarPago(Collection<PagoCuentaCupo> acpcc_pagos, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_CUENTA_CUPOS_BLOQUEO;
		ls_userId       = null;

		{
			DAOManager ldm_usuario;

			ldm_usuario = DaoManagerFactory.getDAOManager();

			try
			{
				ls_userId = getSystemUser(ConstanteCommon.USUARIO_PROCESOS_AUTOMATICOS, ldm_usuario);
			}
			catch(B2BException lb2be_e)
			{
				ldm_usuario.setRollbackOnly();

				clh_LOGGER.error("desencolarPago", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_usuario.close();
			}
		}

		{
			DAOManager ldm_processing;

			ldm_processing = DaoManagerFactory.getDAOManager();

			ldm_processing.setAutoCommit(true);

			try
			{
				ConstantesDAO lcd_constant;
				Constantes    lce_constant;

				lcd_constant     = DaoCreator.getConstantesDAO(ldm_processing);
				lce_constant     = lcd_constant.findById(ls_constant);

				if(lce_constant != null)
				{
					lb_alreadyProcessing = BooleanUtils.getBooleanValue(lce_constant.getCaracter());

					if(!lb_alreadyProcessing)
						lcd_constant.updateString(ls_constant, EstadoCommon.S, ls_userId);
				}
				else
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = ls_constant;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("desencolarPago", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_processing.close();
			}
		}

		try
		{
			if(!lb_alreadyProcessing && StringUtils.isValidString(ls_userId))
			{
				DAOManager ldm_bitacora;
				DAOManager ldm_constantes;

				ldm_bitacora       = DaoManagerFactory.getDAOManager();
				ldm_constantes     = DaoManagerFactory.getDAOManager();

				try
				{
					if(CollectionUtils.isValidCollection(acpcc_pagos))
					{
						BitacoraProcesoDAO lbpd_bitacora;
						String             ls_endpoint;

						lbpd_bitacora     = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);
						ls_endpoint       = DaoCreator.getConstantesDAO(ldm_constantes)
								                          .findString(
								    ConstanteCommon.JOB_CUENTA_CUPOS_NOTIFICACION_PAGO_ENDPOINT
								);

						ldm_bitacora.setAutoCommit(true);

						for(PagoCuentaCupo lpcc_iterador : acpcc_pagos)
						{
							if((lpcc_iterador != null))
							{
								try
								{
									notificarPagoCuentaCupo(
									    lpcc_iterador, lbpd_bitacora, ls_endpoint, as_remoteIp, ls_userId
									);
								}
								catch(Exception le_e)
								{
									clh_LOGGER.error("desencolarPago", le_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("desencolarPago", lb2be_e);
				}
				finally
				{
					ldm_bitacora.close();
					ldm_constantes.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("desencolarPago", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			if(!lb_alreadyProcessing)
			{
				DAOManager ldm_processing;
				ldm_processing = DaoManagerFactory.getDAOManager();

				ldm_processing.setAutoCommit(true);

				try
				{
					DaoCreator.getConstantesDAO(ldm_processing).updateString(ls_constant, EstadoCommon.N, ls_userId);
				}
				catch(B2BException lb2be_e)
				{
					ldm_processing.setRollbackOnly();

					clh_LOGGER.error("desencolarPago", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					ldm_processing.close();
				}
			}
		}
	}

	/**
	 * Generar constancia decision aprobacion.
	 *
	 * @param as_idSolicitud Variable de tipo <code>String</code> que contiene id solicitud
	 * @param as_idCuentaCupo Variable de tipo <code>String</code> que contiene id cuenta cupo
	 * @param as_observaciones Variable de tipo <code>String</code> que contiene observaciones
	 * @param ab_aprobar Variable de tipo <code>boolean</code> que contiene aprobar
	 * @param lbd_valorMinimo Variable de tipo <code>BigDecimal</code> correspondiente al valor de valor minimo cuenta cupo
	 * @param lbd_valorMaximo Variable de tipo <code>BigDecimal</code> correspondiente al valor de valor maximo cuenta cupo
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] generarConstanciaDecisionAprobacion(
	    String as_idSolicitud, String as_idCuentaCupo, String as_observaciones, boolean ab_aprobar,
	    BigDecimal lbd_valorMinimo, BigDecimal lbd_valorMaximo
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		byte[]     lba_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lba_return      = null;

		try
		{
			Solicitud ls_solicitud;
			String    ls_subproceso;

			ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(as_idSolicitud);

			if(ls_solicitud == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

			ls_subproceso     = StringUtils.getStringNotNull(ls_solicitud.getIdSubproceso());

			lba_return = generarConstanciaDecisionAprobacion(
				    ls_solicitud.getNir(), as_idCuentaCupo, obtenerNombrePlantillaConstancia(ls_subproceso, ab_aprobar),
				    as_observaciones, lbd_valorMinimo, lbd_valorMaximo, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarConstanciaDecisionAprobacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Intenta realizar la notificación del pago, de no lograrlo hace la devolución del dinero a la cuenta cupo.
	 *
	 * @param apcc_pago Objeto contenedor de la información del pago
	 * @param abpd_DAO Objeto contenedor de la conexión a la tabla de bitacora para el registro de errores
	 * @param as_endpoint de as endpoint
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @param as_usuarioProceso Id del usuario que ejecuta la acción
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public synchronized void notificarPagoCuentaCupo(
	    PagoCuentaCupo apcc_pago, BitacoraProcesoDAO abpd_DAO, String as_endpoint, String as_remoteIp,
	    String as_usuarioProceso
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(apcc_pago == null)
			{
				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.PAGO_CUENTA_CUPO,
				    addMessage(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS, true), as_usuarioProceso, as_remoteIp, null
				);

				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
			}

			PagoCuentaCupoDAO                      lpccd_pagoCuentaCupoDAO;
			SBB_EF_OperacionesFinancierasProxy     lseofp_proxy;
			SBB_EF_OperacionesFinancieras_PortType lseofpt_interface;
			boolean                                lb_notificacionExitosa;

			lpccd_pagoCuentaCupoDAO     = DaoCreator.getPagoCuentaCupoDAO(ldm_manager);
			lseofp_proxy                = new SBB_EF_OperacionesFinancierasProxy(as_endpoint);
			lseofpt_interface           = lseofp_proxy.getSBB_EF_OperacionesFinancieras_PortType();
			lb_notificacionExitosa      = false;

			if(lseofpt_interface != null)
			{
				TipoSalidaNotificarPago ltsnp_respuesta;

				{
					String ls_referenciaPago;

					ls_referenciaPago     = apcc_pago.getReferenciaPago();

					ltsnp_respuesta = lseofpt_interface.notificarPago(
						    new TipoEntradaNotificarPago(
						        "80", "CUPSE", "6", obtenerCalendarDesdeLocalDateTime(LocalDateTime.now()),
						        obtenerCalendarDesdeDate(apcc_pago.getFechaCreacion()), ls_referenciaPago,
						        apcc_pago.getValor(), ls_referenciaPago
						    )
						);
				}

				if(ltsnp_respuesta != null)
				{
					BigInteger lbi_codigoMensaje;

					lbi_codigoMensaje     = ltsnp_respuesta.getCodigoMensaje();

					lb_notificacionExitosa = NumericUtils.isValidBigInteger(lbi_codigoMensaje)
							&& lbi_codigoMensaje.equals(BigInteger.valueOf(200L));

					if(lb_notificacionExitosa)
					{
						apcc_pago.setEstado(EstadoCommon.E);
						apcc_pago.setIdUsuarioModificacion(as_usuarioProceso);
						apcc_pago.setIpModificacion(as_remoteIp);

						lpccd_pagoCuentaCupoDAO.updateEstado(apcc_pago);
					}
					else
						escribirEnBitacoraProceso(
						    abpd_DAO, IdentificadoresCommon.PAGO_CUENTA_CUPO, ltsnp_respuesta.getDescripcionMensaje(),
						    as_usuarioProceso, as_remoteIp, apcc_pago.getIdPagoCuentaCupo()
						);
				}
				else
				{
					Object[] loa_arg;

					loa_arg     = new String[1];

					loa_arg[0] = addMessage(MessagesKeys.NOTIFICAR_PAGO_CUENTA_CUPO, true);

					escribirEnBitacoraProceso(
					    abpd_DAO, IdentificadoresCommon.PAGO_CUENTA_CUPO,
					    addMessage(ErrorKeys.ERROR_SIN_RESPUESTA_OPERACION, loa_arg, true), as_usuarioProceso,
					    as_remoteIp, apcc_pago.getIdPagoCuentaCupo()
					);
				}
			}
			else
				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.PAGO_CUENTA_CUPO,
				    addMessage(ErrorKeys.ERROR_NO_SE_ENCONTRO_INTERFACE_SERVICIOS_VALIDA, true), as_usuarioProceso,
				    as_remoteIp, apcc_pago.getIdPagoCuentaCupo()
				);

			{
				MaestroMovimientoCuentaCupoDAO lmmccd_maestroMovimientoDAO;
				MaestroMovimientoCuentaCupo    lmmcc_movimiento;

				lmmccd_maestroMovimientoDAO     = DaoCreator.getMaestroMovimientoCuentaCupoDAO(ldm_manager);
				lmmcc_movimiento                = lmmccd_maestroMovimientoDAO.findById(apcc_pago.getIdMovimiento());

				if(lmmcc_movimiento != null)
				{
					Constantes lc_cantidadIntentos;

					lc_cantidadIntentos = DaoCreator.getConstantesDAO(ldm_manager)
							                            .findById(
							    ConstanteCommon.CANTIDAD_INTENTOS_NOTIFICAR_PAGO_CUENTA_CUPO
							);

					if(lc_cantidadIntentos != null)
					{
						BigInteger lbi_intentos;

						lbi_intentos = lc_cantidadIntentos.getEntero();

						if(NumericUtils.isValidBigInteger(lbi_intentos))
						{
							int li_intentoNotificacion;

							{
								Integer li_intentoNotificacionPago;

								li_intentoNotificacionPago = lmmcc_movimiento.getIntentoNotificacionPago();

								if(!NumericUtils.isValidInteger(li_intentoNotificacionPago))
									li_intentoNotificacionPago = Integer.valueOf(0);

								li_intentoNotificacion = li_intentoNotificacionPago.intValue();
							}

							li_intentoNotificacion++;

							if(((li_intentoNotificacion) >= lbi_intentos.intValue()) && !lb_notificacionExitosa)
							{
								reversarPagoCuentaCupo(
								    lmmcc_movimiento, abpd_DAO, as_usuarioProceso, as_remoteIp, ldm_manager
								);

								apcc_pago.setEstado(EstadoCommon.I);
								apcc_pago.setIdUsuarioModificacion(as_usuarioProceso);
								apcc_pago.setIpModificacion(as_remoteIp);

								lpccd_pagoCuentaCupoDAO.updateEstado(apcc_pago);

								lmmcc_movimiento.setEstado(EstadoTipoMovimientoCuentaCupoCommon.REVERSADO);
							}

							lmmcc_movimiento.setIntentoNotificacionPago(Integer.valueOf(li_intentoNotificacion));

							lmmcc_movimiento.setIdUsuarioModificacion(as_usuarioProceso);
							lmmcc_movimiento.setIpModificacion(as_remoteIp);

							lmmccd_maestroMovimientoDAO.updateIntentos(lmmcc_movimiento);
						}
						else
						{
							escribirEnBitacoraProceso(
							    abpd_DAO, IdentificadoresCommon.PAGO_CUENTA_CUPO,
							    addMessage(ErrorKeys.ERROR_CONSTANTE_SIN_VALOR_ENTERO_VALIDO, true), as_usuarioProceso,
							    as_remoteIp, apcc_pago.getIdPagoCuentaCupo()
							);

							throw new B2BException(ErrorKeys.ERROR_CONSTANTE_SIN_VALOR_ENTERO_VALIDO);
						}
					}
					else
					{
						Object[] loa_args;

						loa_args     = new String[1];

						loa_args[0] = ConstanteCommon.CANTIDAD_INTENTOS_NOTIFICAR_PAGO_CUENTA_CUPO;

						escribirEnBitacoraProceso(
						    abpd_DAO, IdentificadoresCommon.PAGO_CUENTA_CUPO,
						    addMessage(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args, true), as_usuarioProceso, as_remoteIp,
						    apcc_pago.getIdPagoCuentaCupo()
						);

						throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
					}
				}
				else
				{
					escribirEnBitacoraProceso(
					    abpd_DAO, IdentificadoresCommon.PAGO_CUENTA_CUPO,
					    addMessage(ErrorKeys.ERROR_SIN_MOVIMIENTO_MAESTRO, true), as_usuarioProceso, as_remoteIp,
					    apcc_pago.getIdPagoCuentaCupo()
					);

					throw new B2BException(ErrorKeys.ERROR_SIN_MOVIMIENTO_MAESTRO);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Obtener bandeja detalle.
	 *
	 * @param atc_parametros la atc parametros
	 * @return la collection
	 * @throws B2BException la b 2 B exception
	 */
	public synchronized Collection<TramiteCantidad> obtenerBandejaDetalle(TramiteCantidad atc_parametros)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<TramiteCantidad> lctc_tramites;

		ldm_manager       = DaoManagerFactory.getDAOManager();
		lctc_tramites     = null;

		try
		{
			lctc_tramites = DaoCreator.getTurnoHistoriaDAO(ldm_manager).obtenerBandejaDetalleCuentaCupo(atc_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerBandejaDetalle", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctc_tramites;
	}

	/**
	 * Obtener bandeja entrada.
	 *
	 * @param atc_parametros la atc parametros
	 * @param ab_procesoCancelacion la ab proceso cancelacion
	 * @return la collection
	 * @throws B2BException la b 2 B exception
	 */
	public synchronized Collection<TramiteCantidad> obtenerBandejaEntrada(TramiteCantidad atc_parametros)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<TramiteCantidad> lctc_tramites;

		ldm_manager       = DaoManagerFactory.getDAOManager();
		lctc_tramites     = null;

		try
		{
			lctc_tramites = DaoCreator.getTurnoHistoriaDAO(ldm_manager).obtenerBandejaCuentaCupo(atc_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerBandejaEntrada", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctc_tramites;
	}

	/**
	 * Busca la información relacionada a una solicitud de cuenta cupo.
	 *
	 * @param as_idTurno identificador del trámite
	 * @return Objeto contenedor con la información de la solicitud
	 * @throws B2BException si no se cumple una regla de negocio
	 */
	public synchronized AprobacionCuentaCupos obtenerDatosTurno(String as_idTurno)
	    throws B2BException
	{
		DAOManager            ldm_manager;
		AprobacionCuentaCupos lacc_datosCuentaCupos;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lacc_datosCuentaCupos     = new AprobacionCuentaCupos();

		try
		{
			if(!StringUtils.isValidString(as_idTurno))
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

			Turno lt_turno;

			lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(as_idTurno);

			if(lt_turno != null)
			{
				SolicitudDAO lsd_solicitudDAO;
				String       ls_idSolicitud;
				String       ls_idTelefono;
				String       ls_idCorreo;

				lsd_solicitudDAO = DaoCreator.getSolicitudDAO(ldm_manager);

				{
					Solicitud ls_solicitudActual;

					ls_solicitudActual = lsd_solicitudDAO.findById(lt_turno.getIdSolicitud());

					if(ls_solicitudActual == null)
						throw new B2BException(ErrorKeys.ERROR_NO_EXISTE_SOLICITUD_ACC);

					lacc_datosCuentaCupos.setIdSolicitud(ls_solicitudActual.getIdSolicitud());
					lacc_datosCuentaCupos.setNir(ls_solicitudActual.getNir());
					lacc_datosCuentaCupos.setFechaSolicitud(ls_solicitudActual.getFechaCreacion());

					ls_idSolicitud     = ls_solicitudActual.getIdSolicitud();
					ls_idTelefono      = ls_solicitudActual.getIdTelefono();
					ls_idCorreo        = ls_solicitudActual.getIdCorreoElectronico();

					{
						Subproceso ls_subproceso;

						ls_subproceso = DaoCreator.getSubprocesoDAO(ldm_manager)
								                      .findById(
								    ls_solicitudActual.getIdProceso(), ls_solicitudActual.getIdSubproceso()
								);

						if(ls_subproceso != null)
						{
							String ls_idSubproceso;

							ls_idSubproceso = StringUtils.getStringNotNull(ls_subproceso.getIdSubproceso());

							if(ls_idSubproceso.equals(ProcesoCommon.ID_SUBPROCESO_2))
							{
								ModCuentaCupo lmcc_datosModificacion;

								lmcc_datosModificacion = DaoCreator.getModCuentaCupoDAO(ldm_manager)
										                               .findByIdSolicitud(ls_idSolicitud);

								if(lmcc_datosModificacion != null)
								{
									lacc_datosCuentaCupos.setValorMaximoModificacion(
									    lmcc_datosModificacion.getValorMaximo()
									);
									lacc_datosCuentaCupos.setValorMinimoModificacion(
									    lmcc_datosModificacion.getValorMinimo()
									);
								}
							}

							lacc_datosCuentaCupos.setTipoSolicitud(ls_subproceso.getNombre());
						}
						else
							throw new B2BException(ErrorKeys.ERROR_SUBPROCESO_NO_VALIDO);
					}
				}

				{
					CuentaCupoDAO lccd_cuentaCupoDAO;
					CuentaCupo    lcc_cuentaCupo;

					lccd_cuentaCupoDAO     = DaoCreator.getCuentaCupoDAO(ldm_manager);
					lcc_cuentaCupo         = lccd_cuentaCupoDAO.findByIdSolicitud(ls_idSolicitud);

					if(lcc_cuentaCupo == null)
					{
						ModCuentaCupo lmcc_modCuentaCupo;

						lmcc_modCuentaCupo = DaoCreator.getModCuentaCupoDAO(ldm_manager)
								                           .findByIdSolicitud(ls_idSolicitud);

						if(lmcc_modCuentaCupo != null)
							lcc_cuentaCupo = lccd_cuentaCupoDAO.findByIdSolicitud(
								    lmcc_modCuentaCupo.getIdSolicitudCreacion()
								);
						else
							throw new B2BException(ErrorKeys.ERROR_SOLICITUD_NO_ASOCIADA_PROCESO_CUENTA_CUPOS);
					}

					if(lcc_cuentaCupo == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_CUENTA_CUPO_ASOCIADA_A_SOLICITUD);

					lacc_datosCuentaCupos.setIdCuentaCupo(lcc_cuentaCupo.getIdCuentaCupo());
					lacc_datosCuentaCupos.setEstado(lcc_cuentaCupo.getEstado());
					lacc_datosCuentaCupos.setValorMinimo(lcc_cuentaCupo.getValorMinimo());
					lacc_datosCuentaCupos.setValorMaximo(lcc_cuentaCupo.getValorMaximo());

					{
						AccEntidadExterna laee_entidadExterna;

						laee_entidadExterna = DaoCreator.getAccEntidadExternaDAO(ldm_manager)
								                            .findByIdAccEntidadExterna(
								    lcc_cuentaCupo.getIdEntidadExterna()
								);

						if(laee_entidadExterna != null)
						{
							lacc_datosCuentaCupos.setRazonSocial(laee_entidadExterna.getNombre());
							lacc_datosCuentaCupos.setNit(laee_entidadExterna.getNumeroDocumento());

							{
								Municipio lm_municipio;

								lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager)
										                     .findById(
										    laee_entidadExterna.getIdPais(), laee_entidadExterna.getIdDepartamento(),
										    laee_entidadExterna.getIdMunicipio()
										);

								lacc_datosCuentaCupos.setCiudad(
								    (lm_municipio != null) ? lm_municipio.getNombre() : ConstanteCommon.SIN_INFORMACION
								);
							}

							{
								Persona lp_persona;

								lp_persona = DaoCreator.getPersonaDAO(ldm_manager)
										                   .findById(laee_entidadExterna.getIdRepresentanteLegal());

								if(lp_persona != null)
								{
									String ls_idPersona;

									ls_idPersona = lp_persona.getIdPersona();

									lacc_datosCuentaCupos.setRepresentanteLegal(lp_persona.getNombreCompleto());

									{
										PersonaTelefono    lpt_personaTelefono;
										PersonaTelefonoDAO lptd_DAO;

										lptd_DAO = DaoCreator.getPersonaTelefonoDAO(ldm_manager);

										if(!StringUtils.isValidString(ls_idTelefono))
											lpt_personaTelefono = lptd_DAO.findMaxByIdPersona(ls_idPersona);
										else
											lpt_personaTelefono = lptd_DAO.findById(ls_idPersona, ls_idTelefono);

										lacc_datosCuentaCupos.setTelefono(
										    (lpt_personaTelefono != null) ? lpt_personaTelefono.getTelefono()
										                                  : ConstanteCommon.SIN_INFORMACION
										);
									}

									{
										PersonaCorreoElectronico    lpce_personaCorreo;
										PersonaCorreoElectronicoDAO lpced_DAO;

										lpced_DAO = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager);

										if(!StringUtils.isValidString(ls_idCorreo))
											lpce_personaCorreo = lpced_DAO.findMaxByIdPersona(ls_idPersona);
										else
											lpce_personaCorreo = lpced_DAO.findById(ls_idPersona, ls_idCorreo);

										lacc_datosCuentaCupos.setCorreoElectronico(
										    (lpce_personaCorreo != null) ? lpce_personaCorreo.getCorreoElectronico()
										                                 : ConstanteCommon.SIN_INFORMACION
										);
									}
								}
								else
									throw new B2BException(ErrorKeys.ERROR_SIN_REPRESENTANTE_LEGAL);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_SIN_ENTIDAD_EXTERNA);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.TURNO_NO_EXISTE);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerDatosTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lacc_datosCuentaCupos;
	}

	/**
	 * Construye el documento pdf de aprobación o rechazo de solicitud de cuenta cupo.
	 *
	 * @param as_nir Variable de tipo <code>String</code> que contiene id de la solicitud asociada al proceso
	 * @param as_idCuentaCupo Variable de tipo <code>String</code> que contiene id de la cuenta cupo asociada al proceso
	 * @param as_nombrePlantilla Variable de tipo <code>String</code> que contiene nombre de la plantilla a generar
	 * @param as_observaciones Variable de tipo <code>String</code> que contiene justificación para el proceso
	 * @param lbd_valorMinimo Variable de tipo <code>BigDecimal</code> correspondiente al valor de lbd valor minimo
	 * @param lbd_valorMaximo Variable de tipo <code>BigDecimal</code>  correspondiente al valor de lbd valor maximo
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que maneja la transaccionalidad con la base de datos.
	 * @return arreglo de bytes representando el documento generado
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	private byte[] generarConstanciaDecisionAprobacion(
	    String as_nir, String as_idCuentaCupo, String as_nombrePlantilla, String as_observaciones,
	    BigDecimal lbd_valorMinimo, BigDecimal lbd_valorMaximo, DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		try
		{
			String ls_plantilla;

			{
				Constantes lc_constante;

				lc_constante = DaoCreator.getConstantesDAO(adm_manager).findByIdWithImage(as_nombrePlantilla);

				if(lc_constante != null)
				{
					byte[] lba_plantilla;

					lba_plantilla = lc_constante.getImagenes();

					if(lba_plantilla == null)
						throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE);

					ls_plantilla = new String(lba_plantilla);
				}
				else
				{
					Object[] loa_args;

					loa_args     = new String[1];

					loa_args[0] = as_nombrePlantilla;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
				}
			}

			if(!StringUtils.isValidString(ls_plantilla))
				throw new B2BException(ErrorKeys.ERROR_PLANTILLA);

			ls_plantilla     = reemplazarTagEnPlantilla(ls_plantilla, "<TAG_FECHA>", new Date());

			ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, "<TAG_NIR>", as_nir);

			{
				CuentaCupo lcc_cuentaCupo;

				lcc_cuentaCupo = DaoCreator.getCuentaCupoDAO(adm_manager).findById(as_idCuentaCupo);

				if(lcc_cuentaCupo == null)
					throw new B2BException(ErrorKeys.ERROR_SIN_CUENTA_CUPO_ASOCIADA_A_SOLICITUD);

				ls_plantilla     = reemplazarTagEnPlantilla(
					    ls_plantilla, "<TAG_MONTO_MINIMO>",
					    (lbd_valorMinimo != null) ? lbd_valorMinimo : lcc_cuentaCupo.getValorMinimo()
					);
				ls_plantilla     = reemplazarTagEnPlantilla(
					    ls_plantilla, "<TAG_MONTO_MAXIMO>",
					    (lbd_valorMaximo != null) ? lbd_valorMaximo : lcc_cuentaCupo.getValorMaximo()
					);
				ls_plantilla     = reemplazarTagEnPlantilla(ls_plantilla, "<TAG_SALDO>", lcc_cuentaCupo.getSaldo());

				{
					AccEntidadExterna laee_entidad;

					laee_entidad = DaoCreator.getAccEntidadExternaDAO(adm_manager)
							                     .findByIdAccEntidadExterna(lcc_cuentaCupo.getIdEntidadExterna());

					if(laee_entidad != null)
						ls_plantilla = reemplazarTagEnPlantilla(
							    ls_plantilla, "<TAG_NOMBRE_EMPRESA>", laee_entidad.getNombre()
							);
				}
			}

			ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, "<TAG_JUSTIFICACION>", as_observaciones);

			{
				Map<String, String> lmss_datos;

				lmss_datos     = finalizarPlantilla(ls_plantilla, null, adm_manager);

				ls_plantilla = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
			}

			lba_return = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), adm_manager);

			if(lba_return == null)
				throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarConstanciaDecisionAprobacion", lb2be_e);

			throw lb2be_e;
		}

		return lba_return;
	}

	/**
	 * Modifica una cuenta cupo ya existente.
	 *
	 * @param aacc_datosCuenta Objeto contenedor de los valores a modificar
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @param as_estado Estado a asignar a la cuenta cupo
	 * @param adm_manager Conexión a la base de datos
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	private void modificarCuentaCupo(
	    AprobacionCuentaCupos aacc_datosCuenta, String as_userId, String as_remoteIp, String as_estado,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		if((aacc_datosCuenta == null) || !StringUtils.isValidString(as_estado))
			throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);

		CuentaCupo    lcc_cuentaCupo;
		CuentaCupoDAO lccd_cuentaCupoDAO;

		lccd_cuentaCupoDAO     = DaoCreator.getCuentaCupoDAO(adm_manager);
		lcc_cuentaCupo         = lccd_cuentaCupoDAO.findById(aacc_datosCuenta.getIdCuentaCupo());

		if(lcc_cuentaCupo == null)
			throw new B2BException(ErrorKeys.ERROR_SIN_CUENTA_CUPO_ASOCIADA_A_SOLICITUD);

		if(as_estado.equals(EstadoCommon.ACTIVO_TXT))
		{
			BigDecimal lbd_valorMinimo;
			BigDecimal lbd_valorMaximo;

			lbd_valorMinimo     = aacc_datosCuenta.getValorMinimo();
			lbd_valorMaximo     = aacc_datosCuenta.getValorMaximo();

			if(!NumericUtils.isValidBigDecimal(lbd_valorMinimo, BigDecimal.ONE))
				throw new B2BException(ErrorKeys.ERROR_VALOR_MINIMO_NO_VALIDO);

			if(!NumericUtils.isValidBigDecimal(lbd_valorMaximo, BigDecimal.ONE))
				throw new B2BException(ErrorKeys.ERROR_VALOR_MAXIMO_NO_VALIDO);

			{
				int li_comparacionValores;

				li_comparacionValores = lbd_valorMinimo.compareTo(lbd_valorMaximo);

				switch(li_comparacionValores)
				{
					case 0:
						throw new B2BException(ErrorKeys.ERROR_VALOR_MIN_MAX_IGUALES);

					case 1:
						throw new B2BException(ErrorKeys.ERROR_VALOR_MINIMO_MAYOR_QUE_MAXIMO);

					default:
						break;
				}
			}

			lcc_cuentaCupo.setValorMinimo(lbd_valorMinimo);
			lcc_cuentaCupo.setValorMaximo(lbd_valorMaximo);
		}
		else if(as_estado.equals(EstadoCommon.INACTIVO_TXT))
		{
			UsuarioCuentaCupoDAO          luccd_usuarioCuentaCupoDAO;
			Collection<UsuarioCuentaCupo> lcucc_usuarios;

			luccd_usuarioCuentaCupoDAO     = DaoCreator.getUsuarioCuentaCupoDAO(adm_manager);
			lcucc_usuarios                 = luccd_usuarioCuentaCupoDAO.findByIdCuentaCupo(
				    aacc_datosCuenta.getIdCuentaCupo()
				);

			if(CollectionUtils.isValidCollection(lcucc_usuarios))
			{
				for(UsuarioCuentaCupo lucc_usuario : lcucc_usuarios)
				{
					if(lucc_usuario != null)
					{
						lucc_usuario.setEstado(EstadoCommon.I);
						lucc_usuario.setIdUsuarioModificacion(as_userId);
						lucc_usuario.setIpModificacion(as_remoteIp);

						luccd_usuarioCuentaCupoDAO.update(lucc_usuario);
					}
				}
			}
		}

		lcc_cuentaCupo.setEstado(as_estado);
		lcc_cuentaCupo.setIdUsuarioModificacion(as_userId);
		lcc_cuentaCupo.setIpModificacion(as_remoteIp);

		lccd_cuentaCupoDAO.update(lcc_cuentaCupo);
	}

	/**
	 * Obtener nombre plantilla constancia.
	 *
	 * @param as_subproceso de as subproceso
	 * @param ab_aprobar de ab aprobar
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String obtenerNombrePlantillaConstancia(String as_subproceso, boolean ab_aprobar)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(StringUtils.isValidString(as_subproceso))
		{
			switch(as_subproceso)
			{
				case ProcesoCommon.ID_SUBPROCESO_1:
					ls_return = ab_aprobar ? ConstanteCommon.PLANTILLA_SOLICITUD_CREACION_CUENTA_CUPO_APROBADO
						                   : ConstanteCommon.PLANTILLA_SOLICITUD_CREACION_CUENTA_CUPO_RECHAZADO;

					break;

				case ProcesoCommon.ID_SUBPROCESO_2:
					ls_return = ab_aprobar ? ConstanteCommon.PLANTILLA_SOLICITUD_MODIFICACION_CUENTA_CUPO_APROBADO
						                   : ConstanteCommon.PLANTILLA_SOLICITUD_MODIFICACION_CUENTA_CUPO_RECHAZADO;

					break;

				case ProcesoCommon.ID_SUBPROCESO_3:
					ls_return = ab_aprobar ? ConstanteCommon.PLANTILLA_SOLICITUD_CANCELACION_CUENTA_CUPO_APROBADO
						                   : ConstanteCommon.PLANTILLA_SOLICITUD_CANCELACION_CUENTA_CUPO_RECHAZADO;

					break;

				default:
					throw new B2BException(ErrorKeys.ERROR_SUBPROCESO_NO_VALIDO);
			}
		}

		return ls_return;
	}

	/**
	 * Genera los movimientos correspondientes a la devolución de dinero de una cuenta cupo.
	 *
	 * @param ammcc_movimientoOriginal Objeto contenedor de los datos del movimiento a devolver
	 * @param abpd_DAO Objeto contenedor de la conexión a la tabla de bitacora para el registro de errores
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @param adm_manager Conexión a la base de datos
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	private synchronized void reversarPagoCuentaCupo(
	    MaestroMovimientoCuentaCupo ammcc_movimientoOriginal, BitacoraProcesoDAO abpd_DAO, String as_userId,
	    String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if((ammcc_movimientoOriginal == null))
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);

			String     ls_idMovimientoOriginal;
			String     ls_idCuentaCupo;
			BigDecimal lbd_valor;
			long       ll_idMovimientoReverso;

			ls_idMovimientoOriginal     = ammcc_movimientoOriginal.getIdMovimiento();
			ls_idCuentaCupo             = ammcc_movimientoOriginal.getIdCuentaCupo();
			lbd_valor                   = ammcc_movimientoOriginal.getValor();
			ll_idMovimientoReverso      = 0L;

			{
				MaestroMovimientoCuentaCupo lmmcc_movimientoReverso;

				lmmcc_movimientoReverso = new MaestroMovimientoCuentaCupo();

				lmmcc_movimientoReverso.setReferenciaPago(ammcc_movimientoOriginal.getReferenciaPago());
				lmmcc_movimientoReverso.setIdCuentaCupo(ls_idCuentaCupo);
				lmmcc_movimientoReverso.setValor(lbd_valor);
				lmmcc_movimientoReverso.setTipoMovimiento(TipoMovimientoCuentaCupoCommon.REVERSO);
				lmmcc_movimientoReverso.setIdUsuario(ammcc_movimientoOriginal.getIdUsuario());
				lmmcc_movimientoReverso.setFecha(new Date());
				lmmcc_movimientoReverso.setEstado(EstadoTipoMovimientoCuentaCupoCommon.APROBADO);
				lmmcc_movimientoReverso.setIdUsuarioCreacion(as_userId);
				lmmcc_movimientoReverso.setIpCreacion(as_remoteIp);

				ll_idMovimientoReverso = DaoCreator.getMaestroMovimientoCuentaCupoDAO(adm_manager)
						                               .insert(lmmcc_movimientoReverso);
			}

			{
				DetalleMovimientoCuentaCupoDAO          ldmccd_detalleMovimientoDAO;
				Collection<DetalleMovimientoCuentaCupo> lcdmcc_detallesMovimiento;

				ldmccd_detalleMovimientoDAO     = DaoCreator.getDetalleMovimientoCuentaCupoDAO(adm_manager);
				lcdmcc_detallesMovimiento       = ldmccd_detalleMovimientoDAO.findByIdMaestroMovimiento(
					    ls_idMovimientoOriginal
					);

				if(CollectionUtils.isValidCollection(lcdmcc_detallesMovimiento))
				{
					NotaCreditoCuentaCupoDAO lncccd_notaCreditoDAO;
					String                   ls_idMovimientoReverso;

					lncccd_notaCreditoDAO      = DaoCreator.getNotaCreditoCuentaCupoDAO(adm_manager);
					ls_idMovimientoReverso     = StringUtils.getString(ll_idMovimientoReverso);

					for(DetalleMovimientoCuentaCupo ldmcc_detalle : lcdmcc_detallesMovimiento)
					{
						if(ldmcc_detalle != null)
						{
							String                ls_idNotaCredito;
							NotaCreditoCuentaCupo lnccc_notaCredito;

							ls_idNotaCredito      = ldmcc_detalle.getIdNotaCredito();
							lnccc_notaCredito     = lncccd_notaCreditoDAO.findById(ls_idNotaCredito);

							if(lnccc_notaCredito != null)
							{
								BigDecimal lbd_valorDetalle;

								lbd_valorDetalle = ldmcc_detalle.getValor();

								if(!NumericUtils.isValidBigDecimal(lbd_valorDetalle))
									lbd_valorDetalle = BigDecimal.ZERO;

								{
									BigDecimal lbd_saldoNotaCredito;

									lbd_saldoNotaCredito = lnccc_notaCredito.getSaldo();

									if(!NumericUtils.isValidBigDecimal(lbd_valorDetalle))
										lbd_saldoNotaCredito = BigDecimal.ZERO;

									lnccc_notaCredito.setSaldo(lbd_saldoNotaCredito.add(lbd_valorDetalle));
									lnccc_notaCredito.setIdUsuarioModificacion(as_userId);
									lnccc_notaCredito.setIpModificacion(as_remoteIp);

									lncccd_notaCreditoDAO.updateSaldo(lnccc_notaCredito);
								}

								{
									DetalleMovimientoCuentaCupo ldmcc_detalleDevolucion;

									ldmcc_detalleDevolucion = new DetalleMovimientoCuentaCupo();

									ldmcc_detalleDevolucion.setIdMovimiento(ls_idMovimientoReverso);
									ldmcc_detalleDevolucion.setIdNotaCredito(ls_idNotaCredito);
									ldmcc_detalleDevolucion.setValor(lbd_valorDetalle);
									ldmcc_detalleDevolucion.setFecha(new Date());
									ldmcc_detalleDevolucion.setIdUsuarioCreacion(as_userId);
									ldmcc_detalleDevolucion.setIpCreacion(as_remoteIp);

									ldmccd_detalleMovimientoDAO.insert(ldmcc_detalleDevolucion);
								}
							}
						}
					}
				}
				else
					throw new B2BException(
					    addMessageGCC(com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_DETALLE_MOVIMIENTOS)
					);
			}

			{
				CuentaCupo lcc_cuentaCupo;

				lcc_cuentaCupo = validarCuentaCupo(ls_idCuentaCupo, new CodigoError(), adm_manager);

				{
					BigDecimal lbd_saldo;

					lbd_saldo = lcc_cuentaCupo.getSaldo();

					if(!NumericUtils.isValidBigDecimal(lbd_saldo))
						lbd_saldo = BigDecimal.ZERO;

					lcc_cuentaCupo.setSaldo(lbd_saldo.add(lbd_valor));

					DaoCreator.getCuentaCupoDAO(adm_manager).update(lcc_cuentaCupo);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.PAGO_CUENTA_CUPO, lb2be_e.getMessage(), as_userId, as_remoteIp, null
			);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			String ls_message;

			ls_message = le_e.getLocalizedMessage();

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.PAGO_CUENTA_CUPO, ls_message, as_userId, as_remoteIp, null
			);

			throw new B2BException(ls_message);
		}
	}

	/**
	 * Buscar por id cuenta cupo.
	 *
	 * @param as_idCuentaCupo de as id cuenta cupo
	 * @return el valor de cuenta cupo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CuentaCupo buscarPorIdCuentaCupo(String as_idCuentaCupo)
	    throws B2BException
	{
		CuentaCupo lcc_cuentaCupo;

		lcc_cuentaCupo = null;

		if(StringUtils.isValidString(as_idCuentaCupo))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lcc_cuentaCupo = DaoCreator.getCuentaCupoDAO(ldm_manager).findMoreDataById(as_idCuentaCupo);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("buscarPorIdCuentaCupo", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcc_cuentaCupo;
	}
}
