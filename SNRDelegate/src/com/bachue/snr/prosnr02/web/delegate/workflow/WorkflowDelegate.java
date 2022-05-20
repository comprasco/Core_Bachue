package com.bachue.snr.prosnr02.web.delegate.workflow;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr02.ejb.session.stateless.workflow.WorkflowRemote;


/**
 * Clase para el acceso a la interfaz remota de workflow.
 *
 * @author Manuel Blanco
 *
 */
public class WorkflowDelegate implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 810748427685349884L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.workflow";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(WorkflowDelegate.class, ProyectosCommon.WORKFLOW_02);

	/** The ir remote. */
	private WorkflowRemote ir_remote;

	/**
	 * Instancia un nuevo workflow delegado.
	 */
	public WorkflowDelegate()
	{
		ir_remote = null;
	}

	/**
	 * Obtiene el valor de remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException el valor de b 2 B exception
	 */
	public WorkflowRemote getRemote()
	    throws B2BException
	{
		if(ir_remote == null)
		{
			try
			{
				ir_remote = (WorkflowRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                      .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				ir_remote = null;
			}
		}

		return ir_remote;
	}
}
