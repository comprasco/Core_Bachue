package com.bachue.snr.prosnr01.common.utils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;


/**
 * Helper para cargar el bundle del log.
 *
 * @author Julian Vaca
 */
public class LoggingHelper
{
	/** Constante cs_DEFAULT_BUNDLE. */
	private static final String cs_DEFAULT_BUNDLE = "conf/prosnr01.xml";

	/** Constante cs_PROSNR03_BUNDLE. */
	private static final String cs_PROSNR03_BUNDLE = "conf/prosnr03.xml";

	/** Constante cs_PROSNR04_BUNDLE. */
	private static final String cs_PROSNR04_BUNDLE = "conf/prosnr04.xml";

	/** Constante cs_PROSNR07_BUNDLE. */
	private static final String cs_PROSNR07_BUNDLE = "conf/prosnr07.xml";

	/** Constante cs_PROSNR08_BUNDLE. */
	private static final String cs_PROSNR08_BUNDLE = "conf/prosnr08.xml";

	/** Constante cs_PROSNR09_BUNDLE. */
	private static final String cs_PROSNR09_BUNDLE = "conf/prosnr09.xml";

	/** Constante cs_PROSNR10_BUNDLE. */
	private static final String cs_PROSNR10_BUNDLE = "conf/prosnr10.xml";

	/** Constante cs_PROSNR11_BUNDLE. */
	private static final String cs_PROSNR11_BUNDLE = "conf/prosnr11.xml";

	/** Constante cs_PROSNR12_BUNDLE. */
	private static final String cs_PROSNR12_BUNDLE = "conf/prosnr12.xml";

	/** Constante cs_PROSNR13_BUNDLE. */
	private static final String cs_PROSNR13_BUNDLE = "conf/prosnr13.xml";

	/** Constante cs_PROSNR14_BUNDLE. */
	private static final String cs_PROSNR14_BUNDLE = "conf/prosnr14.xml";

	/** Constante cs_PROSNR15_BUNDLE. */
	private static final String cs_PROSNR15_BUNDLE = "conf/prosnr15.xml";

	/** Constante cs_PROSNR16_BUNDLE. */
	private static final String cs_PROSNR16_BUNDLE = "conf/prosnr16.xml";

	/** Constante cs_PROSNR17_BUNDLE. */
	private static final String cs_PROSNR17_BUNDLE = "conf/prosnr17.xml";

	/** Constante cs_PROSNR18_BUNDLE. */
	private static final String cs_PROSNR18_BUNDLE = "conf/prosnr18.xml";

	/** Constante cs_PROSNR19_BUNDLE. */
	private static final String cs_PROSNR19_BUNDLE = "conf/prosnr19.xml";

	/** Constante cs_PROSNR20_BUNDLE. */
	private static final String cs_PROSNR20_BUNDLE = "conf/prosnr20.xml";
	
	/** Constante cs_PROSNR22_BUNDLE. */
	private static final String cs_PROSNR22_BUNDLE = "conf/prosnr22.xml";

	/**
	 * Obtiene el log.
	 *
	 * @param ac_class Clase que se implementa para el log
	 * @return lloger instancia del log
	 * @see org.apache.log4j.Logger
	 */
	public static org.apache.log4j.Logger getLogger(Class<?> ac_class)
	{
		LoggerHandler.setDefaultBundle(cs_DEFAULT_BUNDLE);

		return LoggerHandler.getLogger(ac_class);
	}

	/**
	 * Retorna el valor de logger.
	 *
	 * @param ac_class correspondiente al valor del tipo de objeto Class<?>
	 * @param as_bundleNumber correspondiente al valor del tipo de objeto String
	 * @return el valor de logger
	 */
	public static org.apache.log4j.Logger getLogger(Class<?> ac_class, String as_bundleNumber)
	{
		String ls_bundleRoute;

		ls_bundleRoute = cs_DEFAULT_BUNDLE;

		if(StringUtils.isValidString(as_bundleNumber))
		{
			if(as_bundleNumber.equals(ProyectosCommon.GESTION_DE_USUARIOS_03))
				ls_bundleRoute = cs_PROSNR03_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.NOTIFICACION_PAGOS_04))
				ls_bundleRoute = cs_PROSNR04_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.CONSULTA_HISTORIAL_SOLICITUDES_PAGADASA_07))
				ls_bundleRoute = cs_PROSNR07_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.ENTREGAR_PRODUCTO_08))
				ls_bundleRoute = cs_PROSNR08_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.GESTION_ALERTA_TITULARES_09))
				ls_bundleRoute = cs_PROSNR09_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.CATASTRO_10))
				ls_bundleRoute = cs_PROSNR10_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.CONTROL_DIGITALIZACION_11))
				ls_bundleRoute = cs_PROSNR11_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.GESTION_CUENTA_CUPOS_12))
				ls_bundleRoute = cs_PROSNR12_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.GENERACION_SOLICITUD_13))
				ls_bundleRoute = cs_PROSNR13_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.COEXISTENCIA_14))
				ls_bundleRoute = cs_PROSNR14_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.CONSULTA_DATOS_REGISTRALES_15))
				ls_bundleRoute = cs_PROSNR15_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16))
				ls_bundleRoute = cs_PROSNR16_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.SOLICITUD_DE_CORRECCION_17))
				ls_bundleRoute = cs_PROSNR17_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.SOLICITUD_DE_COPIAS_18))
				ls_bundleRoute = cs_PROSNR18_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.ALERTA_TIERRAS_19))
				ls_bundleRoute = cs_PROSNR19_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.NOTIFICADOR_20))
				ls_bundleRoute = cs_PROSNR20_BUNDLE;
			else if(as_bundleNumber.equals(ProyectosCommon.NOTIFICAR_DIGITALIZACION_CONTENT_22))
				ls_bundleRoute = cs_PROSNR22_BUNDLE;
		}

		LoggerHandler.setDefaultBundle(ls_bundleRoute);

		return LoggerHandler.getLogger(ac_class);
	}
}
