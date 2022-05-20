package com.bachue.snr.prosnr21.business.conciliaciones;

import co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoEntradaEnviarDocumento;
import co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoEntradaEnviarDocumentoRepositorio;
import co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoParametro;
import co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoSalidaEnviarDocumento;

import co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentosProxy;
import co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentos_PortType;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.integracion.cliente.owcc.ParametrosDocumento;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.BitacoraProceso;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr21.model.pgn.ConDocumentos;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import java.util.Map.Entry;


/**
 * Clase que contiene los métodos de negocio para el envió de imágenes al gestor documental.
 *
 * @author Heiner Castañeda.
 *
 */
public class EnvioGestorDocumentalBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioGestorDocumentalBusiness.class);

	/**
	 * Método encargado de consultar las imagenes con contenido valido en la tabla SDB_BGN_IMAGENES.
	 *
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void enviarGestorDocumental(String as_remoteIp)
	    throws B2BException
	{
		Collection<ConDocumentos> accd_conDocumentos;
		DAOManager                   ldm_manager;

		accd_conDocumentos     = null;
		ldm_manager              = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			Constantes lc_constant;

			lc_constant = DaoCreator.getConstantesDAO(ldm_manager)
					                    .findById(ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_WS_INVOKE);

			if(lc_constant != null)
			{
				if(BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
					accd_conDocumentos = DaoCreator.getConDocumentosDAO(ldm_manager).findDocumentsEcm();
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarGestorDocumental", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(accd_conDocumentos))
			enviarGestorDocumental(accd_conDocumentos, as_remoteIp);
	}

	/**
	 *  Método encargado de validar los documentos encontrados para enviarlos al gestor documental.
	 *
	 * @param acds_parametros Colección de datos de tipo DocumentosSalida que contiene los documentos que se enviaran al gestor documental.
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void enviarGestorDocumental(Collection<ConDocumentos> acds_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_BLOQUEO;
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

				clh_LOGGER.error("enviarGestorDocumental", lb2be_e);

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
					loa_messageArgs[0]     = StringUtils.getString(ls_constant);

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("enviarGestorDocumental", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_processing.close();
			}
		}

		try
		{
			if(!lb_alreadyProcessing)
			{
				DAOManager ldm_bitacora;
				DAOManager ldm_constantes;

				ldm_bitacora       = DaoManagerFactory.getDAOManager();
				ldm_constantes     = DaoManagerFactory.getDAOManager();

				try
				{
					ConstantesDAO lcd_constantes;
					String        ls_endpoint;

					lcd_constantes     = DaoCreator.getConstantesDAO(ldm_constantes);

					ls_endpoint = lcd_constantes.findString(ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT);

					if(CollectionUtils.isValidCollection(acds_parametros))
					{
						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

						ldm_bitacora.setAutoCommit(true);

						for(ConDocumentos lds_iterador : acds_parametros)
						{
							if(lds_iterador != null)
							{
								try
								{
									enviarGestorDocumental(
									    lds_iterador, lbpd_bitacora, ls_endpoint, ls_userId, as_remoteIp, ldm_constantes
									);
								}
								catch(Exception le_e)
								{
									clh_LOGGER.error("enviarGestorDocumental", le_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("enviarGestorDocumental", lb2be_e);
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
			clh_LOGGER.error("enviarGestorDocumental", lb2be_e);

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

					clh_LOGGER.error("enviarGestorDocumental", lb2be_e);

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
	 * Método encargado de ejecutar web service para el envio de imagenes al gestor documental.
	 *
	 * @param ads_parametros  Objeto de tipo  DocumentosSalida que contiene datos de un documento determinado.
	 * @param abpd_DAO Objeto de tipo  BitacoraProcesoDAO utilizado para crear instancia de la clase BitacoraProcesoDAO.
	 * @param as_endpoint Variable de tipo String que contiene la dirección de punto final del servicio de destino para el envió  al gestor documental.
	 * @param as_username Variable de tipo String que contiene el usuario determinado para el envió  al gestor documental.
	 * @param as_ipRemota correspondiente al valor del tipo de objeto String
	 * @param adm_manager <code>DAOManager</code> que controla la transaccionalidad con la base de datos.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void enviarGestorDocumental(
		ConDocumentos ads_parametros, BitacoraProcesoDAO abpd_DAO, String as_endpoint, String as_username,
	    String as_ipRemota, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(ads_parametros != null)
			{
				throw new B2BException("COMPLETAR");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("enviarGestorDocumental", lb2be_e);

			throw lb2be_e;
		}
	}
}
