package com.bachue.snr.prosnr01.business.cuentaCupos;

import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoMovimientoCMO;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoNotaCreditoCMO;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoReciboCajaCMO;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.acc.DetalleMovimientoCuentaCupoDAO;
import com.bachue.snr.prosnr01.dao.acc.LiquidacionDAO;
import com.bachue.snr.prosnr01.dao.acc.UsuarioCuentaCupoDAO;

import com.bachue.snr.prosnr01.model.CodigoError;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleMovimientoCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.MaestroMovimientoCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.NotaCreditoCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.UsuarioCuentaCupo;

import com.bachue.snr.prosnr12.common.constants.ErrorKeys;
import com.bachue.snr.prosnr12.common.constants.EstadoTipoMovimientoCuentaCupoCommon;
import com.bachue.snr.prosnr12.common.constants.TipoMovimientoCuentaCupoCommon;
import com.bachue.snr.prosnr12.common.constants.TipoUsuarioCuentaCupoCommon;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.time.LocalDateTime;

import java.util.Collection;
import java.util.Date;


/**
 * Clase para el manejo de logica de negocio común en los procesos de cuenta cupo
 *
 * @author Manuel Blanco
 *
 */
public class BaseCuentaCupo extends BaseBusiness
{
	/**
	 * Consulta los movimientos de una cuenta cupo en un rango de tiempo determinado
	 *
	 * @param as_idCuentaCupo id de la cuenta cupo a consultar
	 * @param aldt_fechaInicial fecha inicial del rango a consultar
	 * @param aldt_fechaFinal fecha final del rango a consultar
	 * @param adm_manager Conexión a la base de datos
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	protected Collection<MaestroMovimientoCuentaCupo> consultarMovimientosEnRangoFechas(
	    String as_idCuentaCupo, LocalDateTime aldt_fechaInicial, LocalDateTime aldt_fechaFinal, DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<MaestroMovimientoCuentaCupo> lcmmcc_movimientos;

		validarFechasConsultaCuentaCupo(as_idCuentaCupo, aldt_fechaInicial, aldt_fechaFinal, adm_manager);

		lcmmcc_movimientos = DaoCreator.getMaestroMovimientoCuentaCupoDAO(adm_manager)
				                           .findByIdCuentaCupoFecha(
				    as_idCuentaCupo, aldt_fechaInicial, aldt_fechaFinal
				);

		return lcmmcc_movimientos;
	}

	/**
	 * Obtiene los detalles de cada movimiento registrado para una cuenta cupo
	 *
	 * @param acmmcc_movimientos Objeto contendor de los movimientos a verificar
	 * @param adm_manager Conexión a la base de datos
	 * @return Arreglo de movimientos resultante de la consulta
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	protected TipoMovimientoCMO[] obtenerDetalleMovimientosCuentaCupo(
	    Collection<MaestroMovimientoCuentaCupo> acmmcc_movimientos, DAOManager adm_manager
	)
	    throws B2BException
	{
		TipoMovimientoCMO[] ltmcmoa_movimientos;

		ltmcmoa_movimientos = null;

		if(CollectionUtils.isValidCollection(acmmcc_movimientos))
		{
			int                            li_cont;
			DetalleMovimientoCuentaCupoDAO ldmccd_detalleMovimientoDAO;
			LiquidacionDAO                 lld_liquidacionDAO;

			li_cont                         = 0;
			ldmccd_detalleMovimientoDAO     = DaoCreator.getDetalleMovimientoCuentaCupoDAO(adm_manager);
			lld_liquidacionDAO              = DaoCreator.getAccLiquidacionDAO(adm_manager);
			ltmcmoa_movimientos             = new TipoMovimientoCMO[acmmcc_movimientos.size()];

			for(MaestroMovimientoCuentaCupo lmmcc_movimiento : acmmcc_movimientos)
			{
				if(lmmcc_movimiento != null)
				{
					String            ls_tipoMovimiento;
					String            ls_idMovimiento;
					TipoMovimientoCMO ltmcmo_registro;

					ls_tipoMovimiento     = StringUtils.getStringNotNull(lmmcc_movimiento.getTipoMovimiento());
					ls_idMovimiento       = lmmcc_movimiento.getIdMovimiento();
					ltmcmo_registro       = new TipoMovimientoCMO(
						    ls_idMovimiento, obtenerCalendarDesdeDate(lmmcc_movimiento.getFecha()), ls_tipoMovimiento,
						    null, null
						);

					{
						TipoNotaCreditoCMO                      ltnccmo_detalleCredito;
						Collection<DetalleMovimientoCuentaCupo> lcdmcc_detallesMovimiento;

						ltnccmo_detalleCredito        = new TipoNotaCreditoCMO();
						lcdmcc_detallesMovimiento     = ldmccd_detalleMovimientoDAO.findByIdMaestroMovimiento(
							    ls_idMovimiento
							);

						if(CollectionUtils.isValidCollection(lcdmcc_detallesMovimiento))
						{
							String     ls_estadoMovimiento;
							String     ls_idNotaCredito;
							BigDecimal lbd_total;

							ls_idNotaCredito        = null;
							lbd_total               = BigDecimal.ZERO;
							ls_estadoMovimiento     = StringUtils.getStringNotNull(lmmcc_movimiento.getEstado());

							for(DetalleMovimientoCuentaCupo ldmcc_detalle : lcdmcc_detallesMovimiento)
							{
								if(ldmcc_detalle != null)
								{
									if(!StringUtils.isValidString(ls_idNotaCredito))
										ls_idNotaCredito = ldmcc_detalle.getIdNotaCredito();

									BigDecimal lbd_valor;

									lbd_valor = ldmcc_detalle.getValor();

									if(NumericUtils.isValidBigDecimal(lbd_valor))
										lbd_total = lbd_total.add(lbd_valor);
								}
							}

							if(
							    NumericUtils.isValidBigDecimal(lbd_total)
								    && ls_tipoMovimiento.equals(TipoMovimientoCuentaCupoCommon.CONSUMO)
								    && ls_estadoMovimiento.equals(EstadoTipoMovimientoCuentaCupoCommon.APROBADO)
							)
								lbd_total = lbd_total.negate();

							ltnccmo_detalleCredito.setNumero(ls_idNotaCredito);
							ltnccmo_detalleCredito.setValor(lbd_total);

							ltmcmo_registro.setNotaCredito(ltnccmo_detalleCredito);
						}
						else
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_DETALLE_MOVIMIENTOS));
					}

					{
						TipoReciboCajaCMO ltrccmo_infoRecibo;
						String            ls_numeroRecibo;

						ltrccmo_infoRecibo     = new TipoReciboCajaCMO();
						ls_numeroRecibo        = StringUtils.getStringNotNull(lmmcc_movimiento.getReferenciaPago());

						ltrccmo_infoRecibo.setNumero(ls_numeroRecibo);

						{
							Liquidacion ll_liquidacion;

							ll_liquidacion = lld_liquidacionDAO.findByNumeroReferencia(ls_numeroRecibo);

							if(ll_liquidacion != null)
								ltrccmo_infoRecibo.setValorConsumo(ll_liquidacion.getValorTotal());
						}

						ltmcmo_registro.setReciboCaja(ltrccmo_infoRecibo);
					}

					ltmcmoa_movimientos[li_cont++] = ltmcmo_registro;
				}
			}
		}

		return ltmcmoa_movimientos;
	}

	/**
	 * Verifica que las fechas sean validas para una consulta y verifica que existan movimientos en la cuenta cupo
	 *
	 * @param as_idCuentaCupo id de la cuenta cupo a consultar
	 * @param aldt_fechaInicial fecha inicial del rango a consultar
	 * @param aldt_fechaFinal fecha final del rango a consultar
	 * @param adm_manager Conexión a la base de datos
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	protected void validarFechasConsultaCuentaCupo(
	    String as_idCuentaCupo, LocalDateTime aldt_fechaInicial, LocalDateTime aldt_fechaFinal, DAOManager adm_manager
	)
	    throws B2BException
	{
		LocalDateTime lldt_fechaActual;

		lldt_fechaActual = LocalDateTime.now();

		if(aldt_fechaInicial != null)
		{
			NotaCreditoCuentaCupo lnccc_notaCredito;

			lnccc_notaCredito = DaoCreator.getNotaCreditoCuentaCupoDAO(adm_manager)
					                          .findLatestOrOldestByIdCuentaCupo(as_idCuentaCupo, false);

			if(lnccc_notaCredito != null)
			{
				Date ld_fechaNotaCredito;

				ld_fechaNotaCredito = lnccc_notaCredito.getFecha();

				if(ld_fechaNotaCredito == null)
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_FECHA_INICIAL_NO_VALIDA));

				if(aldt_fechaInicial.isBefore(obtenerLocalDateTime(ld_fechaNotaCredito)))
				{
					Object[] loa_args;

					loa_args     = new String[1];

					loa_args[0] = StringUtils.getString(ld_fechaNotaCredito);

					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_FECHA_INICIAL_NO_VALIDA, loa_args));
				}
				else if(aldt_fechaInicial.isAfter(lldt_fechaActual))
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_FECHA_INICIAL_SUPERIOR_ACTUAL));
			}
			else
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_MOVIMIENTOS_RECARGA));
		}
		else
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_FECHA_INICIAL));

		if(aldt_fechaFinal != null)
		{
			if(aldt_fechaFinal.isAfter(lldt_fechaActual))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_FECHA_FINAL_NO_VALIDA));
			else if(aldt_fechaFinal.isBefore(aldt_fechaInicial))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_FECHA_FINAL_ANTERIOR_A_INICIAL));
		}
		else
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_FECHA_FINAL));
	}

	/**
	 * Verifica que el número de documento ingresado sea válido
	 *
	 * @param as_numeroDocumento número de documento a validar
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	protected void validarNumeroDocumento(String as_numeroDocumento)
	    throws B2BException
	{
		validarNumeroDocumento(as_numeroDocumento, null);
	}

	/**
	 * Verifica que el número de documento ingresado sea válido
	 *
	 * @param as_numeroDocumento número de documento a validar
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	protected void validarNumeroDocumento(String as_numeroDocumento, String as_tipoError)
	    throws B2BException
	{
		if(!StringUtils.isValidString(as_numeroDocumento))
		{
			String ls_error;

			ls_error = com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_NUMERO_DOCUMENTO;

			if(StringUtils.isValidString(as_tipoError))
			{
				switch(as_tipoError)
				{
					case EstadoCommon.P:
						ls_error = com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_NUMERO_DOCUMENTO_PODERDANTE;

						break;

					case EstadoCommon.I:
						ls_error = com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_NUMERO_DOCUMENTO_INTERVINIENTE;

						break;

					case EstadoCommon.T:
						ls_error = com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_NUMERO_DOCUMENTO_TITULAR_CUENTA;

						break;

					default:
						ls_error = com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_SIN_NUMERO_DOCUMENTO;

						break;
				}
			}

			throw new B2BException(addMessageGCC(ls_error));
		}

		if(
		    !ExpresionRegular.getExpresionRegular().esDocumentoIdentidad(as_numeroDocumento)
			    || (as_numeroDocumento.length() > 100)
		)
		{
			Object[] loa_args;
			String   ls_error;

			loa_args     = new String[1];
			ls_error     = com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_NUMERO_DOCUMENTO_NO_VALIDO;

			loa_args[0] = as_numeroDocumento;

			if(StringUtils.isValidString(as_tipoError))
			{
				switch(as_tipoError)
				{
					case EstadoCommon.P:
						ls_error = com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_NUMERO_DOCUMENTO_NO_VALIDO_PODERDANTE;

						break;

					case EstadoCommon.I:
						ls_error = com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_NUMERO_DOCUMENTO_NO_VALIDO_INTERVINIENTE;

						break;

					case EstadoCommon.T:
						ls_error = com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_NUMERO_DOCUMENTO_NO_VALIDO_TITULAR_CUENTA;

						break;

					default:
						ls_error = com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_NUMERO_DOCUMENTO_NO_VALIDO;

						break;
				}
			}

			throw new B2BException(addMessageGCC(ls_error, loa_args));
		}
	}

	/**
	 * Verifica la información ingresada para el administrador de una cuenta cupo.
	 *
	 * @param as_tipoDocumento to´p documento del usuario a verificar
	 * @param as_numeroDocumento número documento del usuario a verificar
	 * @param as_idCuentaCupo id de la cuenta cupo a la cual pertenece el usuario
	 * @param ace_codigoError Objeto contenedor del código de respuesta de la operación
	 * @param adm_manager Conexión a la base de datos
	 * @param ab_admin true para indicar si el usuario es un administrador, false para usuario normal.
	 * @return Correo electrónico del administrador
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	protected UsuarioCuentaCupo validarUsuarioCuentaCupo(
	    String as_tipoDocumento, String as_numeroDocumento, String as_idCuentaCupo, CodigoError ace_codigoError,
	    DAOManager adm_manager, boolean ab_admin
	)
	    throws B2BException
	{
		return validarUsuarioCuentaCupo(
		    as_tipoDocumento, as_numeroDocumento, as_idCuentaCupo, ace_codigoError, adm_manager, ab_admin, true
		);
	}

	/**
	 * Verifica la información ingresada para el administrador de una cuenta cupo.
	 *
	 * @param as_tipoDocumento to´p documento del usuario a verificar
	 * @param as_numeroDocumento número documento del usuario a verificar
	 * @param as_idCuentaCupo id de la cuenta cupo a la cual pertenece el usuario
	 * @param ace_codigoError Objeto contenedor del código de respuesta de la operación
	 * @param adm_manager Conexión a la base de datos
	 * @param ab_admin true para indicar si el usuario es un administrador, false para usuario normal.
	 * @param ab_validarInactivo true para indicar si se debe validar si el usuario está inactivo.
	 * @return Correo electrónico del administrador
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	protected UsuarioCuentaCupo validarUsuarioCuentaCupo(
	    String as_tipoDocumento, String as_numeroDocumento, String as_idCuentaCupo, CodigoError ace_codigoError,
	    DAOManager adm_manager, boolean ab_admin, boolean ab_validarInactivo
	)
	    throws B2BException
	{
		if(
		    !StringUtils.isValidString(as_idCuentaCupo) || !StringUtils.isValidString(as_tipoDocumento)
			    || !StringUtils.isValidString(as_numeroDocumento)
		)
			throw new B2BException(
			    addMessageGCC(com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS)
			);

		UsuarioCuentaCupoDAO luccd_usuarioCuentaCupoDAO;
		UsuarioCuentaCupo    lucc_usuario;

		luccd_usuarioCuentaCupoDAO     = DaoCreator.getUsuarioCuentaCupoDAO(adm_manager);
		lucc_usuario                   = luccd_usuarioCuentaCupoDAO.findByIdCuentaAndDoc(
			    as_idCuentaCupo, as_tipoDocumento, as_numeroDocumento
			);

		if(lucc_usuario == null)
		{
			UsuarioCuentaCupo lucc_usuarioVerificacion;

			lucc_usuarioVerificacion = luccd_usuarioCuentaCupoDAO.findByTipoDocNumDoc(
				    as_tipoDocumento, as_numeroDocumento
				);

			if(lucc_usuarioVerificacion != null)
			{
				Object[] loa_args;

				loa_args     = new String[3];

				loa_args[0]     = as_tipoDocumento;
				loa_args[1]     = as_numeroDocumento;
				loa_args[2]     = as_idCuentaCupo;

				ace_codigoError.setCodigoError(BigInteger.valueOf(420L));

				throw new B2BException(
				    addMessageGCC(
				        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_USUARIO_NO_VINCULADO_A_CUENTA_CUPO,
				        loa_args
				    )
				);
			}
			else
				throw new B2BException(
				    addMessageGCC(
				        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_USUARIO_CUENTA_CUPO_NO_EXISTE
				    )
				);
		}

		if(ab_validarInactivo)
		{
			String ls_estado;

			ls_estado = StringUtils.getStringNotNull(lucc_usuario.getEstado());

			if(ls_estado.equals(EstadoCommon.I))
			{
				Object[] loa_args;

				loa_args     = new String[2];

				loa_args[0]     = lucc_usuario.getTipoDocumento();
				loa_args[1]     = lucc_usuario.getNumeroDocumento();

				throw new B2BException(
				    addMessageGCC(com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_USUARIO_INACTIVO, loa_args)
				);
			}
		}

		if(ab_admin)
		{
			String ls_tipoUsuario;

			ls_tipoUsuario = StringUtils.getStringNotNull(lucc_usuario.getTipoUsuario());

			if(!ls_tipoUsuario.equals(TipoUsuarioCuentaCupoCommon.ADMINISTRADOR))
			{
				Object[] loa_args;

				loa_args     = new String[3];

				loa_args[0]     = lucc_usuario.getTipoDocumento();
				loa_args[1]     = lucc_usuario.getNumeroDocumento();
				loa_args[2]     = as_idCuentaCupo;

				throw new B2BException(
				    addMessageGCC(
				        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_USUARIO_NO_ES_ADMINISTRADOR, loa_args
				    )
				);
			}

			if(!validarCorreoElectronico(lucc_usuario.getCorreoElectronico()))
				throw new B2BException(
				    addMessageGCC(
				        com.bachue.snr.prosnr12.common.constants.ErrorKeys.ERROR_CORREO_ADMINISTRADOR_NO_VALIDO
				    )
				);
		}

		return lucc_usuario;
	}
}
