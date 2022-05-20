package com.bachue.snr.prosnr01.business.predio.documento.acto.antiguo;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.ConsultaCriterioAntSistemaDAO;
import com.bachue.snr.prosnr01.dao.acc.DatosAntSistemaDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.bgn.DocumentoDAO;
import com.bachue.snr.prosnr01.dao.pgn.OficinaOrigenDAO;

import com.bachue.snr.prosnr01.model.antiguoSistema.CalificacionAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.ConsultaCriterioAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;

import java.math.BigDecimal;


/**
 *  Clase que contiene los métodos de consulta  para el proceso de calificación antiguo sistema.
 *
 * @author Manuel Blanco.
 *
 */
public class PredioDocumentoActoAntiguoBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(PredioDocumentoActoAntiguoBusiness.class);

	/**
	 * Método encargado de consultar las observaciones de la trazabilidad anterior para un id turno historia determinado.
	 *
	 * @param aoa_oa Variable de tipo String que contiene un id turno historia determinado.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @return
	 * @throws B2BException
	 */
	public synchronized String getObservacionesCalificacion(String aoa_oa, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		String           lcas_result;
		TurnoHistoria    lth_turnoHistoria;
		TurnoHistoriaDAO lthDAO_turnoHistoriaDAO;
		DAOManager       ldm_manager;

		lcas_result           = null;
		lth_turnoHistoria     = new TurnoHistoria();
		ldm_manager           = DaoManagerFactory.getDAOManager();

		try
		{
			lthDAO_turnoHistoriaDAO = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(aoa_oa));
			lth_turnoHistoria = lthDAO_turnoHistoriaDAO.findById(lth_turnoHistoria);

			if(lth_turnoHistoria == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

			long ll_tHAnterior;
			ll_tHAnterior = NumericUtils.getLong(lth_turnoHistoria.getIdAnterior());

			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ll_tHAnterior));
			lth_turnoHistoria = lthDAO_turnoHistoriaDAO.findById(lth_turnoHistoria);

			if(lth_turnoHistoria == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA_ANTERIOR);

			lcas_result = lth_turnoHistoria.getObservaciones();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("getObservacionesCalificacion", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcas_result;
	}

	/**
	 *
	 * Método encargado de consultar la información para el proceso de calificación antiguo sistema con unos criterios de busqueda determinados.
	 *
	 * @param aoa_oa Objeto de tipo CalificacionAntiguoSistema que contiene los criterios de consulta para el proceso de calificación antiguo sistema.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde seque realiza la acción.
	 * @return Retorna  un objeto de tipo CalificacionAntiguoSistema que coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized CalificacionAntiguoSistema datosEtapa101(
	    CalificacionAntiguoSistema aoa_oa, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		CalificacionAntiguoSistema    lcas_result;
		ConsultaCriterioAntSistema    lccas_ccas;
		ConsultaCriterioAntSistemaDAO lccasDAO_consulta;
		DAOManager                    ldm_manager;

		if(aoa_oa == null)
			throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

		lcas_result     = new CalificacionAntiguoSistema();
		lccas_ccas      = aoa_oa.getConsultaCriterioAntSistema();
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(lccas_ccas == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

			TurnoHistoria lth_turnoAnterior;
			lth_turnoAnterior = new TurnoHistoria();

			lth_turnoAnterior.setIdTurnoHistoria(NumericUtils.getLongWrapper(lccas_ccas.getIdTurnoHistoria()));

			TurnoHistoriaDAO lth_DAO;
			lth_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

			lth_turnoAnterior = lth_DAO.findById(lth_turnoAnterior);

			if(lth_turnoAnterior == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

			lccasDAO_consulta = DaoCreator.getConsultaCriterioAntSistemaDAO(ldm_manager);

			lccas_ccas.setIdTurnoHistoria(NumericUtils.getLong(lth_turnoAnterior.getIdAnterior()));
			lccas_ccas = lccasDAO_consulta.findByTH(lccas_ccas);

			if(lccas_ccas == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

			lccas_ccas.setIdTurno(lth_turnoAnterior.getIdTurno());

			lcas_result.setConsultaCriterioAntSistema(lccas_ccas);

			DatosAntSistemaDAO ldasDAO_antSistema;
			DatosAntSistema    ldas_datosAntSistema;
			DocumentoDAO       lcDAO_documento;
			Documento          ld_documento;

			ldasDAO_antSistema       = DaoCreator.getDatosAntSistemaDAO(ldm_manager);
			lcDAO_documento          = DaoCreator.getDocumentoDAO(ldm_manager);
			ldas_datosAntSistema     = new DatosAntSistema();
			ld_documento             = new Documento();

			ldas_datosAntSistema.setIdDatosAntSistema(lccas_ccas.getIdDatosAntSistema());
			ldas_datosAntSistema = ldasDAO_antSistema.findById(ldas_datosAntSistema);

			ld_documento.setIdDocumento(lccas_ccas.getIdDocumento());
			ld_documento = lcDAO_documento.findById(ld_documento);

			if(ld_documento != null)
			{
				String     ls_idOficinaOrigen;
				BigDecimal abd_version;

				ls_idOficinaOrigen     = ld_documento.getIdOficinaOrigen();
				abd_version            = ld_documento.getVersion();

				if(StringUtils.isValidString(ls_idOficinaOrigen))
				{
					OficinaOrigen    loo_oficinaOrigen;
					OficinaOrigenDAO loo_DAO;

					loo_oficinaOrigen     = new OficinaOrigen();
					loo_DAO               = DaoCreator.getOficinaOrigenDAO(ldm_manager);

					loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
					loo_oficinaOrigen.setVersion(abd_version);

					loo_oficinaOrigen = loo_DAO.findById(loo_oficinaOrigen);

					if(loo_oficinaOrigen != null)
					{
						ld_documento.setIdPais(loo_oficinaOrigen.getIdPais());
						ld_documento.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());
						ld_documento.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
					}
				}
			}

			lcas_result.setDatosAntiguoSistema(ldas_datosAntSistema);
			lcas_result.setDocumento(ld_documento);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("datosEtapa101", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcas_result;
	}
}
