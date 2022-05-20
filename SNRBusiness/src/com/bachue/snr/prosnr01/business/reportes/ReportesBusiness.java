package com.bachue.snr.prosnr01.business.reportes;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.view.ViewDataReportDAO;

import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Reportes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Clase para el manejo del negocio de reportes para poder obtener los reportes.
 *
 * @author Nicolas Guaneme
 */
public class ReportesBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ReportesBusiness.class);

	/**
	 * Método para realizar transaccciones con la base de datos y poder obtener y generar los reportes.
	 *
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return el valor de reports
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void getReports(String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_REPORTES_BLOQUEO;
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

				clh_LOGGER.error("getReports", lb2be_e);

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

				clh_LOGGER.error("getReports", lb2be_e);

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
				DAOManager ldm_manager;

				ldm_manager = DaoManagerFactory.getDAOManager();

				try
				{
					Collection<Collection<Map<String, Object>>> lccmso_hashMapCollection;
					Collection<Reportes>                        lcr_reportes;
					String                                      ls_outputPath;

					lccmso_hashMapCollection = new ArrayList<Collection<Map<String, Object>>>();

					{
						String     ls_idConstante;
						Constantes lce_outputPath;

						ls_idConstante     = ConstanteCommon.JOB_REPORTES_PATH;
						lce_outputPath     = DaoCreator.getConstantesDAO(ldm_manager).findById(ls_idConstante);
						ls_outputPath      = (lce_outputPath != null) ? lce_outputPath.getCaracter() : null;

						if(!StringUtils.isValidString(ls_outputPath))
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ls_constant;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}
					}

					lcr_reportes = DaoCreator.getReportesDAO(ldm_manager).findAllByActiveState();

					if(CollectionUtils.isValidCollection(lcr_reportes))
					{
						Map<Integer, String> lmis_sheetName;
						Iterator<Reportes>   lir_reports;
						int                  li_sheetNumber;
						String               ls_date;
						String               ls_fileName;
						String               ls_currentPrefix;
						String               ls_prefixView;
						String               ls_type;
						ViewDataReportDAO    lvdrd_viewDataReport;

						lmis_sheetName           = new HashMap<Integer, String>();
						li_sheetNumber           = 0;
						lir_reports              = lcr_reportes.iterator();
						ls_currentPrefix         = new String();
						ls_date                  = StringUtils.getString(DateUtils.getTimestamp(), "yyyy-MM-dd_HHmmss");
						ls_fileName              = new String();
						ls_prefixView            = new String();
						ls_type                  = ".xlsx";
						lvdrd_viewDataReport     = DaoCreator.getViewDataReportDAO(ldm_manager);

						while(lir_reports.hasNext())
						{
							Reportes lr_report;

							lr_report = lir_reports.next();

							if(lr_report != null)
							{
								boolean                         lb_view;
								Collection<Map<String, Object>> lcmso_viewData;
								String                          ls_order;
								String                          ls_view;

								{
									String ls_viewId;

									ls_view       = StringUtils.getString(lr_report.getDescripcion());
									lb_view       = StringUtils.isValidString(ls_view);
									ls_viewId     = lb_view ? StringUtils.getString(lr_report.getCodigo()) : null;
									ls_order      = StringUtils.getStringLowerCase(lr_report.getOrdenConsulta());

									ls_currentPrefix = ((ls_viewId != null) && (ls_viewId.trim().length() > 2))
										? ls_viewId.substring(0, 3) : new String();
								}

								if(
								    CollectionUtils.isValidCollection(lccmso_hashMapCollection)
									    && !ls_currentPrefix.equalsIgnoreCase(ls_prefixView)
								)
								{
									try
									{
										new ReportsSheet().getEveningReport(
										    lccmso_hashMapCollection, lmis_sheetName, ls_outputPath, ls_fileName, null,
										    null
										);
									}
									catch(Exception le_e)
									{
										clh_LOGGER.error("getReports", le_e);
									}

									lcmso_viewData               = new ArrayList<Map<String, Object>>();
									lccmso_hashMapCollection     = new ArrayList<Collection<Map<String, Object>>>();
									lmis_sheetName               = new HashMap<Integer, String>();
									li_sheetNumber               = 0;
									ls_fileName                  = new String();
								}

								if(lb_view)
								{
									try
									{
										lcmso_viewData     = lvdrd_viewDataReport.findByViewName(ls_view, ls_order);
										ls_prefixView      = ls_currentPrefix;
										ls_fileName        = ls_view.substring(3) + '-' + ls_date + ls_type;
									}
									catch(B2BException lb2be_e)
									{
										clh_LOGGER.error("getReports", lb2be_e);

										lcmso_viewData = null;
									}

									if(CollectionUtils.isValidCollection(lcmso_viewData))
									{
										lccmso_hashMapCollection.add(lcmso_viewData);

										lmis_sheetName.put(
										    new Integer(li_sheetNumber++),
										    (ls_view.trim().length() > 3) ? ls_view.substring(3).replace('_', ' ')
										                                  : "default"
										);
									}
								}
							}
						}

						new ReportsSheet().getEveningReport(
						    lccmso_hashMapCollection, lmis_sheetName, ls_outputPath, ls_fileName, null, null
						);
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("getReports", lb2be_e);

					throw lb2be_e;
				}
				catch(Exception le_e)
				{
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("getReports", le_e);

					throw new B2BException(ErrorKeys.ERROR_REPORTES, le_e);
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("getReports", lb2be_e);

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

					clh_LOGGER.error("getReports", lb2be_e);

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
	 * Se encarga de generar el reporte a consumir
	 *
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void reportes(String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			Constantes lc_constant;

			lc_constant = DaoCreator.getConstantesDAO(ldm_manager).findById(ConstanteCommon.JOB_REPORTES_WS_INVOKE);

			if(lc_constant != null)
			{
				if(BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
					getReports(as_remoteIp);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("reportes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}
}
