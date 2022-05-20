package co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.cliente.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obteneraccesosporrol.v1.TipoAcceso;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obteneraccesosporrol.v1.TipoEntradaObtenerAccesosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obteneraccesosporrol.v1.TipoSalidaObtenerAccesosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerrolesusuario.v1.RolTypeORU;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerrolesusuario.v1.TipoEntradaObtenerRolesUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerrolesusuario.v1.TipoSalidaObtenerRolesUsuario;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Obtener opciones de menú dentro de un componente para un usuario
 * @author dbeltran
 *
 */
public class AccesosPorUsuario
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AccesosPorUsuario.class);

	/**
	 * Obtener opciones de menú dentro de un componente para un usuario
	 * @param as_idUsuario Id de usuario sobre el que se hace la consulta
	 * @param as_idComponente Id del componente al que deben pertener los accesos del usuario
	 * @param as_url URL del endpoint donde se debe hacer el consumo
	 *
	 * @return Salida de la operación de consulta de catalogo, incluyendo codigos de error y registros de salida
	 */
	public static synchronized Collection<TipoAcceso> obtenerAccesosPorUsuario(
	    String as_idUsuario, String as_idComponente, String as_url
	)
	    throws B2BException
	{
		Collection<TipoAcceso> lcta_respuesta;

		lcta_respuesta = null;

		try
		{
			if(StringUtils.isValidString(as_url))
			{
				SUT_CB_GestionUsuarios      lsutcbgu_interfaz;
				SUT_CB_GestionUsuariosProxy lsutcbgup_proxy;

				lsutcbgup_proxy       = new SUT_CB_GestionUsuariosProxy(as_url);
				lsutcbgu_interfaz     = lsutcbgup_proxy.getSUT_CB_GestionUsuarios();

				if(lsutcbgu_interfaz != null)
				{
					BigInteger   lbi_respuesta;
					RolTypeORU[] lrtorua_roles;
					String       ls_respuesta;

					lbi_respuesta     = null;
					lrtorua_roles     = null;
					ls_respuesta      = null;

					try
					{
						TipoSalidaObtenerRolesUsuario ltsoru_respuesta;

						ltsoru_respuesta = lsutcbgu_interfaz.obtenerRolesUsuario(
							    new TipoEntradaObtenerRolesUsuario(as_idUsuario, as_idComponente)
							);

						if(ltsoru_respuesta != null)
						{
							lbi_respuesta     = ltsoru_respuesta.getCodigoMensaje();
							ls_respuesta      = ltsoru_respuesta.getDescripcionMensaje();
						}
						else
							lbi_respuesta = null;

						if(lbi_respuesta.longValue() == CodigoMensajeCommon.CODIGO_200)
							lrtorua_roles = ltsoru_respuesta.getRoles();
					}
					catch(Exception le_e)
					{
						ls_respuesta = le_e.getMessage();

						clh_LOGGER.error(le_e);
					}

					if(lrtorua_roles != null)
					{
						lcta_respuesta = new ArrayList<TipoAcceso>();

						for(int li_rol = 0, li_roles = lrtorua_roles.length; li_rol < li_roles; li_rol++)
						{
							RolTypeORU lrtoru_rol;

							lrtoru_rol = lrtorua_roles[li_rol];

							if(lrtoru_rol != null)
							{
								String ls_idRol;

								ls_idRol = lrtoru_rol.getCodigoRol();

								if(StringUtils.isValidString(ls_idRol))
								{
									TipoAcceso[] ltaa_accesos;

									ltaa_accesos = null;

									{
										TipoSalidaObtenerAccesosPorRol ltsoapr_respuesta;

										try
										{
											ltsoapr_respuesta = lsutcbgu_interfaz.obtenerAccesosPorRol(
												    new TipoEntradaObtenerAccesosPorRol(ls_idRol)
												);

											if(ltsoapr_respuesta != null)
												lbi_respuesta = ltsoapr_respuesta.getCodigoMensaje();
											else
												lbi_respuesta = null;

											if(
											    (lbi_respuesta != null)
												    && (lbi_respuesta.longValue() == CodigoMensajeCommon.CODIGO_200)
											)
												ltaa_accesos = ltsoapr_respuesta.getAccesos();
											else
												li_rol = li_roles + 11;
										}
										catch(Exception le_e)
										{
											ls_respuesta = le_e.getMessage();

											clh_LOGGER.error(le_e);
										}
									}

									if(ltaa_accesos != null)
									{
										for(
										    int li_acceso = 0, li_accesos = ltaa_accesos.length;
											    li_acceso < li_accesos; li_acceso++
										)
										{
											TipoAcceso lta_acceso;

											lta_acceso = ltaa_accesos[li_acceso];

											if(lta_acceso != null)
												lcta_respuesta.add(lta_acceso);
										}
									}
								}
							}
						}
					}

					if((lbi_respuesta == null) || (lbi_respuesta.longValue() != CodigoMensajeCommon.CODIGO_200))
					{
						Object[] loa_args = {ls_respuesta};

						throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA, loa_args);
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERFAZ);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerAccesosPorUsuario", lb2be_e);

			throw lb2be_e;
		}

		return lcta_respuesta;
	}
}
