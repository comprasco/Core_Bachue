package com.bachue.snr.prosnr14.business.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoDatosMatriculasMigracion;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoEntradaConsultaMigracionMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoMatriculaConsultada;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoSalidaConsultaMigracionMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoEntradaConsultaMigracionORIP;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoEntradaConsultaMigracionPredio;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoSalidaConsultaMigracionPredio;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.Matricula;
import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;

import com.bachue.snr.prosnr14.common.constants.ErrorKeys;

import java.math.BigInteger;

import java.util.Date;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades ConsultaMigracionBusiness.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 11/03/2020
 */
public class ConsultaMigracionBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaMigracionBusiness.class, ProyectosCommon.COEXISTENCIA_14);

	/**
	 * Consultar migracion predio.
	 *
	 * @param atecmp_entrada de atecmp entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consulta migracion predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultaMigracionPredio consultarMigracionPredio(
	    TipoEntradaConsultaMigracionPredio atecmp_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaConsultaMigracionPredio ltscmp_return;
		DAOManager                        ldm_manager;
		BigInteger                        lbi_codigoMensaje;
		String                            ls_descripcionMensaje;

		ltscmp_return             = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			if(atecmp_entrada == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			Predio lp_datos;

			lp_datos = new Predio(atecmp_entrada);

			if(
			    validarTipoIdentificacionPredio(lp_datos, ProyectosCommon.COEXISTENCIA_14)
				    && validarNumeroIdentificacionPredio(lp_datos, ProyectosCommon.COEXISTENCIA_14)
			)
			{
				if(lp_datos.isFolioMatricula())
					validarMatriculaCoexistenciaNumerica(lp_datos.getNumeroIdentificacionPredio());

				PredioRegistro lpr_predioRegistro;

				lpr_predioRegistro = consultarInformacionMatricula(
					    lp_datos, ProyectosCommon.COEXISTENCIA_14, ldm_manager
					);

				if(lpr_predioRegistro != null)
				{
					TipoSalidaConsultaMigracionORIP ltscmp_output;

					ltscmp_output     = consultarMigracionORIP(
						    new TipoEntradaConsultaMigracionORIP(lpr_predioRegistro.getIdCirculo()), as_userId,
						    as_remoteIp
						);

					ltscmp_return = new TipoSalidaConsultaMigracionPredio(
						    ltscmp_output.getInformacionMigrada(), lbi_codigoMensaje, "OK"
						);
				}
				else
					throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMigracionPredio", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMigracionPredio", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltscmp_return == null)
			ltscmp_return = new TipoSalidaConsultaMigracionPredio(
				    BooleanUtils.getBoolean("false"), lbi_codigoMensaje, ls_descripcionMensaje
				);

		return ltscmp_return;
	}

	/**
	 * Consultar migracion ORIP.
	 *
	 * @param atecmo_entrada de atecmo entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultar migracion ORI p.v 1 . tipo salida consulta migracion predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP consultarMigracionORIP(
	    TipoEntradaConsultaMigracionORIP atecmo_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP ltscmp_return;
		DAOManager                                                                                                               ldm_manager;
		BigInteger                                                                                                               lbi_codigoMensaje;
		String                                                                                                                   ls_descripcionMensaje;

		ltscmp_return             = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			if(atecmo_entrada == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			String ls_idCirculo;

			ls_idCirculo = atecmo_entrada.getCodCirculoRegistral();

			if(StringUtils.isValidString(ls_idCirculo))
				ltscmp_return = new co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP(
					    BooleanUtils.getBoolean(
					        StringUtils.getString(validarCirculoMigrado(ls_idCirculo, true, 0, ldm_manager))
					    ), lbi_codigoMensaje, ls_descripcionMensaje
					);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMigracionPredio", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMigracionPredio", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltscmp_return == null)
			ltscmp_return = new co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP(
				    BooleanUtils.getBoolean("false"), lbi_codigoMensaje, ls_descripcionMensaje
				);

		return ltscmp_return;
	}

	/**
	 * Validar circulo migrado.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param ab_migracionOripTMigracionMatriculasF de ab migracion orip T migracion matriculas F
	 * @param adm_manager de adm manager
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 */
	private synchronized boolean validarCirculoMigrado(
	    String as_idCirculo, boolean ab_migracionOripTMigracionMatriculasF, int ai_countMatricula,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		if(StringUtils.isValidString(as_idCirculo))
		{
			try
			{
				CirculoRegistral lcr_circuloRegistral;

				lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(as_idCirculo);

				if(lcr_circuloRegistral != null)
				{
					Date ld_fechaMigracion;

					ld_fechaMigracion = lcr_circuloRegistral.getFechaProduccionBachue();

					if(ld_fechaMigracion != null)
					{
						Date ld_actualDate;

						ld_actualDate     = new Date();

						lb_return = ld_actualDate.after(ld_fechaMigracion) || ld_actualDate.equals(ld_fechaMigracion);
					}
				}
				else if(ab_migracionOripTMigracionMatriculasF)
					throw new B2BException(addErrorCX(ErrorKeys.ERROR_ORIP_INVALIDA));
				else
				{
					Object[] loa_arg;

					loa_arg        = new String[1];
					loa_arg[0]     = String.valueOf(ai_countMatricula);

					throw new B2BException(addErrorCX(ErrorKeys.ERROR_MATRICULA_POSICION_FORMATO, loa_arg));
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("validarCirculoMigrado", lb2be_e);
				throw lb2be_e;
			}
		}

		return lb_return;
	}

	/**
	 * Consultar migracion matriculas.
	 *
	 * @param atecmm_entrada de atecmm entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consulta migracion matriculas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultaMigracionMatriculas consultarMigracionMatriculas(
	    TipoEntradaConsultaMigracionMatriculas atecmm_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaConsultaMigracionMatriculas ltcmm_return;
		DAOManager                            ldm_manager;
		BigInteger                            lbi_codigoMensaje;
		String                                ls_descripcionMensaje;

		ltcmm_return              = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			if(atecmm_entrada == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			TipoMatriculaConsultada[] ltmc_tipoMatriculaConsultada;

			ltmc_tipoMatriculaConsultada = atecmm_entrada.getMatriculasConsultadas();

			if(CollectionUtils.isValidCollection(ltmc_tipoMatriculaConsultada))
			{
				TipoDatosMatriculasMigracion[] ltdmp_matriculaMigracion;
				int                            li_count;

				ltdmp_matriculaMigracion     = new TipoDatosMatriculasMigracion[ltmc_tipoMatriculaConsultada.length];
				li_count                     = 0;

				for(TipoMatriculaConsultada ltmc_tmp : ltmc_tipoMatriculaConsultada)
				{
					if(ltmc_tmp != null)
					{
						Matricula lm_matricula;
						boolean   lb_cumple;

						lm_matricula     = Matricula.getMatriculaCTandCX(ltmc_tmp.getMatricula());
						lb_cumple        = false;

						if(lm_matricula != null)
						{
							String ls_idCirculo;

							ls_idCirculo = lm_matricula.getCirculo();

							if(StringUtils.isValidString(ls_idCirculo))
							{
								if(validarCirculoMigrado(ls_idCirculo, false, li_count, ldm_manager))
								{
									PredioRegistro lpr_predioRegistro;
									Long           ll_idMatricula;

									ll_idMatricula = lm_matricula.getMatricula();

									if(NumericUtils.isValidLong(ll_idMatricula))
									{
										lpr_predioRegistro     = DaoCreator.getPredioRegistroDAO(ldm_manager)
												                               .findByCirculoMatricula(
												    ls_idCirculo, NumericUtils.getLong(ll_idMatricula)
												);
										lb_cumple              = lpr_predioRegistro != null;
									}
								}
							}
							else
								throw new B2BException(addErrorCX(ErrorKeys.ERROR_ORIP_INVALIDA));

							ltdmp_matriculaMigracion[li_count] = new TipoDatosMatriculasMigracion(
								    ltmc_tmp.getMatricula(), BooleanUtils.getBoolean(StringUtils.getString(lb_cumple))
								);

							li_count++;
						}
					}
				}

				ltcmm_return = new TipoSalidaConsultaMigracionMatriculas(
					    ltdmp_matriculaMigracion, lbi_codigoMensaje, "OK"
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMigracionPredio", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltcmm_return              = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMigracionPredio", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltcmm_return              = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltcmm_return == null)
		{
			TipoDatosMatriculasMigracion[] ltdmp_matriculaMigracion;

			ltdmp_matriculaMigracion     = new TipoDatosMatriculasMigracion[1];

			ltdmp_matriculaMigracion[0]     = new TipoDatosMatriculasMigracion("", BooleanUtils.getBoolean("false"));

			ltcmm_return = new TipoSalidaConsultaMigracionMatriculas(
				    ltdmp_matriculaMigracion, lbi_codigoMensaje, ls_descripcionMensaje
				);
		}

		return ltcmm_return;
	}
}
