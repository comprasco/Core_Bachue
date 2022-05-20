package co.gov.supernotariado.www.services.bachue.cn.mensajero.v1;

import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;

import com.b2bsg.common.logging.LoggerHandler;

/**
 * Clase que contiene todos las propiedades AxisLogHandler.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 6/08/2020
 */
public class AxisLogHandler extends BasicHandler {


	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -9149600791417943352L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AxisLogHandler.class);
	
	/** {@inheritdoc} */
	@Override
	public void invoke(MessageContext msgContext) throws AxisFault {
		try {
			logMessage(msgContext);
		} catch (Exception e) {
			clh_LOGGER.error("Error on logging messages ",e);
		}
	}

	/**
	 * Log message.
	 *
	 * @param amc_msgContext correspondiente al valor de contexto del mensaje
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	private void logMessage(MessageContext amc_msgContext) throws Exception {
		
		Message lm_inMsg = amc_msgContext.getRequestMessage();
		Message lm_outMsg = amc_msgContext.getResponseMessage();
		
		if (lm_inMsg != null && lm_outMsg == null) 
			clh_LOGGER.info("{} Request ={}",lm_inMsg.getSOAPPartAsString());
		
		if (lm_outMsg != null) 
			clh_LOGGER.info("{} Response ={}",lm_outMsg.getSOAPPartAsString());
	}

	/** {@inheritdoc} */
	@Override
	public void onFault(MessageContext msgContext) {
		super.onFault(msgContext);
		try {
			logMessage(msgContext);
		} catch (Exception e) {
			clh_LOGGER.error("Error on logging messages ",e);
		}
	}
}
