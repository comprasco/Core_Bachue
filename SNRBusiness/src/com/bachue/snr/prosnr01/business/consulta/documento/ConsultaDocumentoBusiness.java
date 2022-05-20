package com.bachue.snr.prosnr01.business.consulta.documento;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.consulta.documento.ConsultaDocumento;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import java.util.Collection;


/**
 * Clase para el manejo del negocio de consulta documento para realizar las consultas de los documentos.
 *
 * @author Gabriel Arias
 */
public class ConsultaDocumentoBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaDocumentoBusiness.class);

	/**
	 * Método para realizar transaccciones con la base de datos para consultar un documento.
	 *
	 * @param acd_parametros Objeto que contiene el ID del documento para la consulta
	 * @return devuelve el valor de Collection de ConsultaDocumento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ConsultaDocumento
	 */
	public synchronized Collection<ConsultaDocumento> findConsultaDocumento(ConsultaDocumento acd_parametros)
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<ConsultaDocumento> lccd_consultaDocumento;

		ldm_manager                = DaoManagerFactory.getDAOManager();
		lccd_consultaDocumento     = null;

		try
		{
			String    ls_d;
			Documento ld_d;

			ld_d     = acd_parametros.getDocumento();
			ls_d     = ld_d.getIdDocumento();

			if(StringUtils.isValidString(ls_d))
				lccd_consultaDocumento = DaoCreator.getConsultaDocumentoDAO(ldm_manager).findAll(acd_parametros);
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findConsultaDocumento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccd_consultaDocumento;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para consultar la trazabilidad.
	 *
	 * @param at_parametros Objeto para extraer turno y solicitud para realizar la consulta
	 * @return devuelve el valor de Collection de Trazabilidad
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Trazabilidad
	 */
	public synchronized Collection<Trazabilidad> findTrazabilidad(Trazabilidad at_parametros)
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<Trazabilidad> lct_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lct_datos       = null;

		try
		{
			Solicitud ls_s;
			Turno     lt_t;
			lt_t     = at_parametros.getTurno();
			ls_s     = at_parametros.getSolicitud();

			String ls_sn;
			String ls_it;
			ls_it    = lt_t.getIdTurno();
			ls_sn    = ls_s.getNir();

			if(!StringUtils.isValidString(ls_sn) && !StringUtils.isValidString(ls_it))
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);

			if(StringUtils.isValidString(ls_sn))
				at_parametros.setSolicitud(DaoCreator.getConsultaDocumentoDAO(ldm_manager).findByNIR(ls_s));

			lct_datos = DaoCreator.getConsultaDocumentoDAO(ldm_manager).findTrazabilidad(at_parametros);

			if(!CollectionUtils.isValidCollection(lct_datos))
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTrazabilidad", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lct_datos;
	}
}
