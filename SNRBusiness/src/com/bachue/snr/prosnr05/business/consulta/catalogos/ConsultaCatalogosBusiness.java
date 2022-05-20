package com.bachue.snr.prosnr05.business.consulta.catalogos;

import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoEntradaConsultarCatalogos;
import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TiposCatalogos;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.sdb.acc.CatalogoConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr05.common.constants.ConstanteCommon;

import java.util.Collection;


/**
 * Clase que contiene todos la funcionalidad de consultar los catalogos
 *
 * @author jpatino
 */
public class ConsultaCatalogosBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaCatalogosBusiness.class);

	/**
	 * Consulta un catalogo específico mediante parametros previamente establecidos.
	 *
	 * @param atecc_request Objeto contenedor de los parametros de busqueda
	 * @return Arreglo de tipos catalogos resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TiposCatalogos
	 */
	public synchronized TiposCatalogos[] consultarCatalogos(TipoEntradaConsultarCatalogos atecc_request)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		DAOManager       ldm_procedimiento;
		TiposCatalogos[] ltca_return;

		ldm_manager           = DaoManagerFactory.getDAOManager();
		ldm_procedimiento     = null;
		ltca_return           = null;

		try
		{
			if(atecc_request != null)
			{
				String ls_modulo;
				String ls_modulosValidos;

				ls_modulo = StringUtils.getStringNotNull(atecc_request.getModulo());

				{
					String     ls_idConstante;
					Constantes lce_modulosValidos;

					ls_idConstante         = ConstanteCommon.MODULOS_VALIDOS;
					lce_modulosValidos     = DaoCreator.getConstantesDAO(ldm_manager).findById(ls_idConstante);
					ls_modulosValidos      = (lce_modulosValidos != null)
						? StringUtils.getStringNotNull(lce_modulosValidos.getCaracter()) : new String();
				}

				{
					boolean  lb_moduloValido;
					String[] lsa_modulosValidos;

					lb_moduloValido        = false;
					lsa_modulosValidos     = ls_modulosValidos.split(",");

					if(lsa_modulosValidos != null)
					{
						for(
						    int li_i = 0, li_tamano = lsa_modulosValidos.length;
							    !lb_moduloValido && (li_i < li_tamano); li_i++
						)
						{
							String ls_tmp;

							ls_tmp              = StringUtils.getStringNotNull(lsa_modulosValidos[li_i]);
							lb_moduloValido     = ls_tmp.equals(ls_modulo);
						}
					}

					if(!lb_moduloValido)
						throw new B2BException(ErrorKeys.ERROR_CANAL_ORIGEN_NO_VALIDO);
				}

				{
					Collection<TiposCatalogos> lctc_tiposCatalogos;
					String                     ls_catalogo;

					lctc_tiposCatalogos     = null;
					ls_catalogo             = atecc_request.getCatalogo();

					if(!StringUtils.isValidString(ls_catalogo) || (ls_catalogo.length() > 50))
						throw new B2BException(ErrorKeys.ERROR_CATALOGO_NO_VALIDO);

					{
						String ls_parametro;

						ls_parametro = StringUtils.getStringNotNull(atecc_request.getParametro());

						if(StringUtils.isValidString(ls_parametro) && (ls_parametro.length() > 50))
							throw new B2BException(ErrorKeys.ERROR_PARAMETRO_NO_VALIDO);
					}

					{
						CatalogoConsulta lcc_catalogoConsulta;
						String           ls_componente;

						lcc_catalogoConsulta = DaoCreator.getCatalogoConsultaDAO(ldm_manager).findById(ls_catalogo);

						if(lcc_catalogoConsulta == null)
							throw new B2BException(ErrorKeys.ERROR_CATALOGO_NO_VALIDO);

						ls_componente = StringUtils.getStringNotNull(lcc_catalogoConsulta.getComponente());

						if(ls_componente.equals("CONCILIACIONES"))
							ldm_procedimiento = DaoManagerFactory.getDAOManagerConciliacion();
						else if(ls_componente.equals("OPERACIONES_FINANCIERAS"))
							ldm_procedimiento = DaoManagerFactory.getDAOManagerNPA();
						else
							ldm_procedimiento = DaoManagerFactory.getDAOManager();

						lctc_tiposCatalogos = DaoCreator.getProcedimientosDAO(ldm_procedimiento)
								                            .procConsultaCatalogos(
								    atecc_request, lcc_catalogoConsulta.getProcedimiento()
								);
					}

					if(CollectionUtils.isValidCollection(lctc_tiposCatalogos))
					{
						ltca_return = new TiposCatalogos[lctc_tiposCatalogos.size()];

						int li_cont;

						li_cont = 0;

						for(TiposCatalogos ltc_data : lctc_tiposCatalogos)
						{
							if(ltc_data != null)
								ltca_return[li_cont++] = ltc_data;
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
		}
		catch(B2BException lb2be_e)
		{
			if(ldm_procedimiento != null)
				ldm_procedimiento.setRollbackOnly();

			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarCatalogos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			if(ldm_procedimiento != null)
				ldm_procedimiento.close();

			ldm_manager.close();
		}

		return ltca_return;
	}
}
