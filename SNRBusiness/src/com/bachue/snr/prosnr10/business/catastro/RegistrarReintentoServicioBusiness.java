package com.bachue.snr.prosnr10.business.catastro;

import co.gov.supernotariado.www.schemas.bachue.ci.recepcionNotificacionBachue.registrarReintentoServicio.v1.TipoEntradaRegistrarReintentoServicio;
import co.gov.supernotariado.www.schemas.bachue.ci.recepcionNotificacionBachue.registrarReintentoServicio.v1.TipoSalidaRegistrarReintentoServicio;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.DescripcionMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr10.common.constants.ErrorKeys;

import com.bachue.snr.prosnr10.dao.acc.ServicioActualizacionHaciaCatastrosDAO;

import com.bachue.snr.prosnr10.model.acc.ServicioActualizacionHaciaCatastros;

import java.math.BigInteger;

import java.util.Date;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades RecepcionNotificacionBachueBusiness.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 12/05/2020
 */
public class RegistrarReintentoServicioBusiness extends CatastroBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(RegistrarReintentoServicioBusiness.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Registrar reintento servicio.
	 *
	 * @param aterrs_entrada de aterrs entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida registrar reintento servicio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaRegistrarReintentoServicio registrarReintentoServicio(
	    TipoEntradaRegistrarReintentoServicio aterrs_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		TipoSalidaRegistrarReintentoServicio ltsrrs_respuesta;
		BigInteger                           lbi_codigoMensaje;
		String                               ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		ltsrrs_respuesta          = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;

		try
		{
			if(aterrs_entrada == null)
				throw new B2BException(addErrorCTO(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS_CODIGO));

			ServicioActualizacionHaciaCatastros    lsahc_catastro;
			ServicioActualizacionHaciaCatastrosDAO lsahcd_DAO;
			String                                 ls_idtransaccion;
			String                                 ls_tipoOperacion;
			String                                 ls_estadoRegistro;
			String                                 ls_fechaRegistro;
			String                                 ls_numeroIntentos;
			Date                                   ld_fechaRegistro;
			long                                   ll_numeroIntentos;

			lsahcd_DAO            = DaoCreator.getServicioActualizacionHaciaCatastrosDAO(ldm_manager);
			ls_idtransaccion      = aterrs_entrada.getCodTransaccion();
			ls_tipoOperacion      = aterrs_entrada.getOperacionRegistro();
			ls_estadoRegistro     = aterrs_entrada.getEstadoRegistro();
			ls_fechaRegistro      = aterrs_entrada.getFechaRegistro();
			ls_numeroIntentos     = aterrs_entrada.getNumeroReintento();
			ld_fechaRegistro      = null;
			ll_numeroIntentos     = 0L;

			if(!StringUtils.isValidString(ls_idtransaccion))
				throw new B2BException(addErrorCTO("El codigo transacción es obligatorio"));

			if(!StringUtils.isValidString(ls_tipoOperacion))
				throw new B2BException(addErrorCTO("La operacion registro es obligatoria"));

			if(!StringUtils.isValidString(ls_estadoRegistro))
				throw new B2BException(addErrorCTO("El estado registro es obligatorio"));
			else
			{
				if(!(ls_estadoRegistro.contains(EstadoCommon.SI) || ls_estadoRegistro.contains(EstadoCommon.NO)))
					throw new B2BException(addErrorCTO("El estado registro debe ser 'S' ó 'N' "));
			}

			if(StringUtils.isValidString(ls_fechaRegistro))
			{
				ld_fechaRegistro = DateUtils.getDate(
					    ls_fechaRegistro, FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO_SEGUNDO
					);

				if(ld_fechaRegistro == null)
					throw new B2BException(
					    addErrorCTO("La fecha registro no cumple con el formato (dd/MM/yyyy HH:mm:ss)")
					);
			}
			else
				throw new B2BException(addErrorCTO("La fecha registro es obligatorio"));

			if(StringUtils.isValidString(ls_numeroIntentos) && StringUtils.isNumeric(ls_numeroIntentos))
				ll_numeroIntentos = NumericUtils.getLong(ls_numeroIntentos);
			else
				throw new B2BException(addErrorCTO("El número reintento es obligatorio y tiene que ser númerico"));

			lsahc_catastro = lsahcd_DAO.buscarPorId(ls_idtransaccion);

			if(lsahc_catastro == null)
				throw new B2BException(addErrorCTO("El codigo transacción no existe"));

			lsahc_catastro.setEstadoRegistro(ls_estadoRegistro);
			lsahc_catastro.setNumeroReintentos(ll_numeroIntentos);
			lsahc_catastro.setFechaNotificacion(ld_fechaRegistro);
			lsahc_catastro.setIdUsuarioModificacion(as_userId);
			lsahc_catastro.setIpModificacion(as_remoteIp);

			lsahcd_DAO.actualizarEstadoTransaccion(lsahc_catastro);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("registrarReintentoServicio", lb2be_e);
			ltsrrs_respuesta = null;

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
			clh_LOGGER.error("registrarReintentoServicio", le_e);
			ltsrrs_respuesta     = null;

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsrrs_respuesta == null)
			ltsrrs_respuesta = new TipoSalidaRegistrarReintentoServicio(lbi_codigoMensaje, ls_descripcionMensaje);

		return ltsrrs_respuesta;
	}
}
