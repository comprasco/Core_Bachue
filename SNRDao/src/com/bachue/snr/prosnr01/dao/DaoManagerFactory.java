package com.bachue.snr.prosnr01.dao;

import com.b2bsg.common.dataAccess2.DAOManager;
import com.b2bsg.common.dataAccess2.source.ConnectionSource;
import com.b2bsg.common.dataAccess2.source.JNDISource;


/**
 * Una fábrica para crear objetos DaoManager.
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
public class DaoManagerFactory
{
	/** Constante cs_CONFIG_FILE_SNR. */
	private static final String cs_CONFIG_FILE_SNR = "com.bachue.common.dataAccess2.source.prosnr01";

	/** Constante cs_CONFIG_FILE_NPA. */
	private static final String cs_CONFIG_FILE_NPA = "com.bachue.common.dataAccess2.source.prosnr02";

	/** Constante cs_CONFIG_FILE_CYN. */
	private static final String cs_CONFIG_FILE_CYN = "com.bachue.common.dataAccess2.source.prosnr04";

	/** Constante cs_CONFIG_FILE_WORKFLOW. */
	private static final String cs_CONFIG_FILE_WORKFLOW = "com.bachue.common.dataAccess2.source.prosnr03";

	/** Constante cs_CONFIG_FILE_CONCILIACION. */
	private static final String cs_CONFIG_FILE_CONCILIACION = "com.bachue.common.dataAccess2.source.prosnr05";

	/** Propiedad ccs SNR. */
	private static ConnectionSource ccs_SNR;

	/** Propiedad ccs NPA. */
	private static ConnectionSource ccs_NPA;

	/** Propiedad ccs CYN. */
	private static ConnectionSource ccs_CYN;

	/** Propiedad ccs WORKFLOW. */
	private static ConnectionSource ccs_WORKFLOW;

	/** Propiedad ccs CONCILIACION. */
	private static ConnectionSource ccs_CONCILIACION;

	/**
	 * Retorna el valor de DAO manager.
	 *
	 * @return el valor de DAO manager
	 */
	public static DAOManager getDAOManager()
	{
		return getDAOManager(false);
	}

	/**
	 * Retorna el valor de DAO manager.
	 *
	 * @param ab_readOnly correspondiente al valor del tipo de objeto boolean
	 * @return el valor de DAO manager
	 */
	public static DAOManager getDAOManager(boolean ab_readOnly)
	{
		DAOManager ldm_manager;

		ldm_manager = new DAOManager();

		ldm_manager.setConnectionSource(getSource());

		return ab_readOnly ? setReadOnly(ldm_manager) : ldm_manager;
	}

	/**
	 * Retorna Objeto o variable de valor DAO manager CYN.
	 *
	 * @return el valor de DAO manager CYN
	 */
	public static DAOManager getDAOManagerCYN()
	{
		return getDAOManagerCYN(false);
	}

	/**
	 * Retorna Objeto o variable de valor DAO manager CYN.
	 *
	 * @param ab_readOnly de ab read only
	 * @return el valor de DAO manager CYN
	 */
	public static DAOManager getDAOManagerCYN(boolean ab_readOnly)
	{
		DAOManager ldm_manager;

		ldm_manager = new DAOManager();

		ldm_manager.setConnectionSource(getSourceCYN());

		return ab_readOnly ? setReadOnly(ldm_manager) : ldm_manager;
	}

	/**
	 * Retorna el valor de DAO manager Conciliacion.
	 *
	 * @return el valor de DAO manager Conciliacion
	 */
	public static DAOManager getDAOManagerConciliacion()
	{
		return getDAOManagerConciliacion(false);
	}

	/**
	 * Retorna el valor de DAO manager Conciliacion.
	 *
	 * @param ab_readOnly correspondiente al valor del tipo de objeto boolean
	 * @return el valor de DAO manager Conciliacion
	 */
	public static DAOManager getDAOManagerConciliacion(boolean ab_readOnly)
	{
		DAOManager ldm_manager;

		ldm_manager = new DAOManager();

		ldm_manager.setConnectionSource(getSourceConciliacion());

		return ab_readOnly ? setReadOnly(ldm_manager) : ldm_manager;
	}

	/**
	 * Retorna el valor de DAO manager NPA.
	 *
	 * @return el valor de DAO manager NPA
	 */
	public static DAOManager getDAOManagerNPA()
	{
		return getDAOManagerNPA(false);
	}

	/**
	 * Retorna el valor de DAO manager NPA.
	 *
	 * @param ab_readOnly correspondiente al valor del tipo de objeto boolean
	 * @return el valor de DAO manager NPA
	 */
	public static DAOManager getDAOManagerNPA(boolean ab_readOnly)
	{
		DAOManager ldm_manager;

		ldm_manager = new DAOManager();

		ldm_manager.setConnectionSource(getSourceNPA());

		return ab_readOnly ? setReadOnly(ldm_manager) : ldm_manager;
	}

	/**
	 * Retorna el valor de DAO manager Workflow.
	 *
	 * @return el valor de DAO manager Workflow
	 */
	public static DAOManager getDAOManagerWorkflow()
	{
		return getDAOManagerWorkflow(false);
	}

	/**
	 * Retorna el valor de DAO manager Workflow.
	 *
	 * @param ab_readOnly correspondiente al valor del tipo de objeto boolean
	 * @return el valor de DAO manager Workflow
	 */
	public static DAOManager getDAOManagerWorkflow(boolean ab_readOnly)
	{
		DAOManager ldm_manager;

		ldm_manager = new DAOManager();

		ldm_manager.setConnectionSource(getSourceWorkflow());

		return ab_readOnly ? setReadOnly(ldm_manager) : ldm_manager;
	}

	/**
	 * Retorna el valor del objeto de tipo Sets the read only.
	 *
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return devuelve el valor del objeto DAO manager
	 */
	private static DAOManager setReadOnly(DAOManager adm_manager)
	{
		if(adm_manager != null)
		{
			adm_manager.setReadOnly();
			adm_manager.setTransactionIsolationLevelReadUncommited();
		}

		return adm_manager;
	}

	/**
	 * Retorna el valor de source.
	 *
	 * @return el valor de source
	 */
	private static synchronized ConnectionSource getSource()
	{
		if(ccs_SNR == null)
			ccs_SNR = new JNDISource(cs_CONFIG_FILE_SNR);

		return ccs_SNR;
	}

	/**
	 * Retorna Objeto o variable de valor source CYN.
	 *
	 * @return el valor de source CYN
	 */
	private static synchronized ConnectionSource getSourceCYN()
	{
		if(ccs_CYN == null)
			ccs_CYN = new JNDISource(cs_CONFIG_FILE_CYN);

		return ccs_CYN;
	}

	/**
	 * Retorna Objeto o variable de valor source conciliacion.
	 *
	 * @return el valor de source conciliacion
	 */
	private static synchronized ConnectionSource getSourceConciliacion()
	{
		if(ccs_CONCILIACION == null)
			ccs_CONCILIACION = new JNDISource(cs_CONFIG_FILE_CONCILIACION);

		return ccs_CONCILIACION;
	}

	/**
	 * Retorna el valor de source NPA.
	 *
	 * @return el valor de source NPA
	 */
	private static synchronized ConnectionSource getSourceNPA()
	{
		if(ccs_NPA == null)
			ccs_NPA = new JNDISource(cs_CONFIG_FILE_NPA);

		return ccs_NPA;
	}

	/**
	 * Retorna Objeto o variable de valor source workflow.
	 *
	 * @return el valor de source workflow
	 */
	private static synchronized ConnectionSource getSourceWorkflow()
	{
		if(ccs_WORKFLOW == null)
			ccs_WORKFLOW = new JNDISource(cs_CONFIG_FILE_WORKFLOW);

		return ccs_WORKFLOW;
	}
}
