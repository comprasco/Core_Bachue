package com.bachue.snr.prosnr03.business.gestionUsuarios;

import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v1.TipoAcceso;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v1.TipoEntradaObtenerAccesosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v1.TipoSalidaObtenerAccesosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerconveniosentidad.v1.TipoConvenio;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerconveniosentidad.v1.TipoEntradaObtenerConveniosEntidad;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerconveniosentidad.v1.TipoSalidaObtenerConveniosEntidad;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerentidadesconvenio.v1.TipoEntidad;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerentidadesconvenio.v1.TipoSalidaObtenerEntidadesConvenio;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v1.TipoEntradaObtenerModulosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v1.TipoModulo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v1.TipoSalidaObtenerModulosPorRol;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerorips.v1.TipoOrip;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerorips.v1.TipoSalidaObtenerORIPs;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerroles.v1.RolTypeOR;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerroles.v1.TipoSalidaObtenerRoles;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolescomponente.v1.RolTypeORC;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolescomponente.v1.TipoEntradaObtenerRolesComponente;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolescomponente.v1.TipoSalidaObtenerRolesComponente;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolesusuario.v1.RolTypeORU;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolesusuario.v1.TipoEntradaObtenerRolesUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolesusuario.v1.TipoSalidaObtenerRolesUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerusuariosrolorip.v1.TipoEntradaObtenerUsuariosRolOrip;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerusuariosrolorip.v1.TipoSalidaObtenerUsuariosRolOrip;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerusuariosrolorip.v1.TipoUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuarioEstadoUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuarioSegundoFactor;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoSalidaRegistrarUsuario;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.DescripcionMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.constants.TipoCambioCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.aut.RolDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioCirculoDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioRolDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.png.UsuarioEtapaDAO;

import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExternaConvenio;
import com.bachue.snr.prosnr01.model.sdb.aut.Componente;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.Rol;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioCirculo;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioRol;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.png.UsuarioEtapa;

import com.bachue.snr.prosnr03.common.constants.ErrorKeys;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades GestionUsuariosBusiness.
 *
 * Contiene los métodos de negocio relacionados a los servicios de CA de la SNR.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 28/07/2019
 */
public class GestionUsuariosBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GestionUsuariosBusiness.class, ProyectosCommon.GESTION_DE_USUARIOS_03);

	/**
	 * Retorna el valor del objeto de Collection para obtener los roles de usuarios.
	 *
	 * @param ateoapr_entrada correspondiente al valor del tipo de objeto TipoEntradaObtenerRolesUsuario
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_localIp correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection de Rol
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Rol
	 */
	public synchronized TipoSalidaObtenerAccesosPorRol obtenerAccesosPorRol(
	    TipoEntradaObtenerAccesosPorRol ateoapr_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		String       ls_descripcionMensaje;
		DAOManager   ldm_manager;
		TipoAcceso[] ltaa_taa;
		BigInteger   lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		ltaa_taa                  = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);

		try
		{
			if(ateoapr_entrada != null)
			{
				String ls_codigoRol;

				ls_codigoRol = ateoapr_entrada.getCodigoRol();

				if(StringUtils.isValidString(ls_codigoRol))
				{
					Rol lr_rol;

					lr_rol = DaoCreator.getRolDAO(ldm_manager).findById(ls_codigoRol, true);

					if(lr_rol != null)
					{
						Collection<Opcion> lco_opcion;
						int                li_contador;

						lco_opcion = DaoCreator.getRolOpcionDAO(ldm_manager).findOpcionByIdRol(ls_codigoRol);

						if((lco_opcion != null) && (lco_opcion.size() > 0))
						{
							ltaa_taa        = new TipoAcceso[lco_opcion.size()];
							li_contador     = 0;

							for(Opcion lo_item : lco_opcion)
							{
								if(lo_item != null)
									ltaa_taa[li_contador++] = new TipoAcceso(
										    lo_item.getIdOpcion(), lo_item.getOpcion(), lo_item.getDescripcion(),
										    StringUtils.getStringNotEmpty(lo_item.getIdOpcionPadre()), 
										    lo_item.getTipo(), 
										    StringUtils.getStringNotEmpty(lo_item.getUrl()),
										    StringUtils.getStringNotEmpty(
										        StringUtils.getString(
										            lo_item.getFechaDesde(), FormatoFechaCommon.ANIO_MES_DIA
										        )
										    ),
										    StringUtils.getStringNotEmpty(
										        StringUtils.getString(
										            lo_item.getFechaHasta(), FormatoFechaCommon.ANIO_MES_DIA
										        )
										    ), lo_item.getActivo()
										);
							}

							ls_descripcionMensaje = DescripcionMensajeCommon.EXITO;
						}
						else
							throw new B2BException(addErrorCA(ErrorKeys.ERROR_AGRUPACION_NO_DEFINIDA));
					}
					else
						throw new B2BException(addErrorCA(ErrorKeys.ERROR_ROL_INGRESADO_NO_ENCONTRADO));
				}
				else
					throw new B2BException(addErrorCA(ErrorKeys.ERROR_ROL_INGRESADO_OBLIGATORIO));
			}
			else
				throw new B2BException(addErrorCA(ErrorKeys.ERROR_OPERACION_NO_EXITOSA));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("obtenerAccesosPorRol", lb2be_e);

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
			clh_LOGGER.error("obtenerAccesosPorRol", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaObtenerAccesosPorRol ltsoapr_salida;

			if(ltaa_taa == null)
			{
				ltaa_taa        = new TipoAcceso[1];
				ltaa_taa[0]     = new TipoAcceso("", "", "", "", "", "", "", "", "");
			}

			ltsoapr_salida = new TipoSalidaObtenerAccesosPorRol(ltaa_taa, lbi_codigoMensaje, ls_descripcionMensaje);

			return ltsoapr_salida;
		}
	}

	/**
	 * Retorna el valor del objeto de Collection obteniendo los convenios de la entidad.
	 *
	 * @param ateoce_request correspondiente al valor del tipo de objeto TipoEntradaObtenerConveniosEntidad
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_localIp correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized TipoSalidaObtenerConveniosEntidad obtenerConveniosEntidad(
	    TipoEntradaObtenerConveniosEntidad ateoce_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		String         ls_descripcionMensaje;
		TipoConvenio[] ltce_response;
		BigInteger     lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		ltce_response             = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();

		try
		{
			if(ateoce_request != null)
			{
				String ls_codigoEntidad;

				ls_codigoEntidad = ateoce_request.getCodigoEntidad();

				if(StringUtils.isValidString(ls_codigoEntidad))
				{
					AccEntidadExterna lee_entidad;

					lee_entidad = DaoCreator.getAccEntidadExternaDAO(ldm_manager)
							                    .findByIdAccEntidadExterna(ls_codigoEntidad);

					if(lee_entidad != null)
					{
						Collection<AccEntidadExternaConvenio> lceec_return;
						int                                   li_contador;

						lceec_return = DaoCreator.getAccEntidadExternaConvenioDAO(ldm_manager)
								                     .findByIdEntidad(lee_entidad);

						if((lceec_return != null) && (lceec_return.size() > 0))
						{
							ltce_response     = new TipoConvenio[lceec_return.size()];
							li_contador       = 0;

							for(AccEntidadExternaConvenio leec_item : lceec_return)
							{
								if(leec_item != null)
									ltce_response[li_contador++] = new TipoConvenio(
										    leec_item.getNumeroConvenio(), leec_item.getNombreConvenio(),
										    StringUtils.getString(
										        leec_item.getFechaCreacionConvenio(), FormatoFechaCommon.DIA_MES_ANIO
										    ),
										    StringUtils.getString(
										        leec_item.getFechaVencimiento(), FormatoFechaCommon.DIA_MES_ANIO
										    )
										);
							}

							ls_descripcionMensaje = DescripcionMensajeCommon.EXITO;
						}
					}
					else
						throw new B2BException(addErrorCA(ErrorKeys.ERROR_ENTIDAD_INVALIDO_E1));
				}
				else
					throw new B2BException(addErrorCA(ErrorKeys.ERROR_ENTIDAD_INVALIDO_E1));
			}
			else
				throw new B2BException(addErrorCA(ErrorKeys.ERROR_OPERACION_NO_EXITOSA));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("obtenerConveniosEntidad", lb2be_e);

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
			clh_LOGGER.error("obtenerConveniosEntidad", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaObtenerConveniosEntidad ltsoce_return;

			if(ltce_response == null)
			{
				ltce_response        = new TipoConvenio[1];
				ltce_response[0]     = new TipoConvenio("", "", "", "");
			}

			ltsoce_return = new TipoSalidaObtenerConveniosEntidad(
				    ltce_response, lbi_codigoMensaje, ls_descripcionMensaje
				);

			return ltsoce_return;
		}
	}

	/**
	 * Retorna el valor del objeto de Collection obteniendo las entidades con convenio.
	 *
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_localIp correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public synchronized TipoSalidaObtenerEntidadesConvenio obtenerEntidadesConvenio(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		String        ls_descripcionMensaje;
		TipoEntidad[] lte_response;
		BigInteger    lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		lte_response              = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();

		try
		{
			Collection<AccEntidadExterna> lcee_return;
			int                           li_contador;

			lcee_return     = new ArrayList<AccEntidadExterna>();

			lcee_return = DaoCreator.getAccEntidadExternaDAO(ldm_manager).findEntidadesConvenio();

			if((lcee_return != null) && (lcee_return.size() > 0))
			{
				lte_response     = new TipoEntidad[lcee_return.size()];
				li_contador      = 0;

				for(AccEntidadExterna lcr_item : lcee_return)
				{
					if(lcr_item != null)
						lte_response[li_contador++] = new TipoEntidad(
							    lcr_item.getIdEntidadExterna(), lcr_item.getNombre(),
							    NumericUtils.getBigInteger(lcr_item.getIdPais()),
							    NumericUtils.getBigInteger(lcr_item.getIdDepartamento()),
							    NumericUtils.getBigInteger(lcr_item.getIdMunicipio())
							);
				}

				ls_descripcionMensaje = DescripcionMensajeCommon.EXITO;
			}
			else
				ls_descripcionMensaje = DescripcionMensajeCommon.SIN_RESULTADOS;
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("obtenerEntidadesConvenio", lb2be_e);

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
			clh_LOGGER.error("obtenerEntidadesConvenio", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaObtenerEntidadesConvenio ltsoo_return;

			if(lte_response == null)
			{
				lte_response        = new TipoEntidad[1];
				lte_response[0]     = new TipoEntidad(
					    "", "", BigInteger.valueOf(0), BigInteger.valueOf(0), BigInteger.valueOf(0)
					);
			}

			ltsoo_return = new TipoSalidaObtenerEntidadesConvenio(
				    lte_response, lbi_codigoMensaje, ls_descripcionMensaje
				);

			return ltsoo_return;
		}
	}

	/**
	 * Obtener modulos por rol.
	 *
	 * @param ateompr_request de ateompr request
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida obtener modulos por rol
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaObtenerModulosPorRol obtenerModulosPorRol(
	    TipoEntradaObtenerModulosPorRol ateompr_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		String       ls_descripcionMensaje;
		TipoModulo[] ltm_response;
		BigInteger   lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		ltm_response              = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();

		try
		{
			if(ateompr_request != null)
			{
				String ls_codigoRol;

				ls_codigoRol = StringUtils.getStringNotNull(ateompr_request.getCodigoRol());

				if(StringUtils.isValidString(ls_codigoRol))
				{
					{
						Rol lr_rol;

						lr_rol = DaoCreator.getRolDAO(ldm_manager).findById(ls_codigoRol, false);

						if(lr_rol == null)
							throw new B2BException("409 - El rol ingresado no existe");
					}

					{
						Collection<Componente> lcc_return;
						int                    li_contador;

						lcc_return = DaoCreator.getRolDAO(ldm_manager).findComponenteByIdRol(ls_codigoRol);

						if((lcc_return != null) && (lcc_return.size() > 0))
						{
							ltm_response     = new TipoModulo[lcc_return.size()];
							li_contador      = 0;

							for(Componente lcc_item : lcc_return)
							{
								if(lcc_item != null)
									ltm_response[li_contador++] = new TipoModulo(
										    lcc_item.getIdComponente(), lcc_item.getNombre(), lcc_item.getActivo()
										);
							}

							ls_descripcionMensaje = DescripcionMensajeCommon.EXITO;
						}
					}
				}
				else
					throw new B2BException(addErrorCA(ErrorKeys.ERROR_ENTIDAD_INVALIDO_E1));
			}
			else
				throw new B2BException(addErrorCA(ErrorKeys.ERROR_OPERACION_NO_EXITOSA));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("obtenerModulosPorRol", lb2be_e);

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
			clh_LOGGER.error("obtenerModulosPorRol", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaObtenerModulosPorRol ltsompr_return;

			if(ltm_response == null)
			{
				ltm_response        = new TipoModulo[1];
				ltm_response[0]     = new TipoModulo("", "", "");
			}

			ltsompr_return = new TipoSalidaObtenerModulosPorRol(ltm_response, lbi_codigoMensaje, ls_descripcionMensaje);

			return ltsompr_return;
		}
	}

	/**
	 * Retorna el valor del objeto de Collection obteniendo los ORIP.
	 *
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_localIp correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CirculoRegistral
	 */
	public synchronized TipoSalidaObtenerORIPs obtenerORIPs(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_descripcionMensaje;
		TipoOrip[] lto_response;
		BigInteger lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		lto_response              = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();

		try
		{
			Collection<CirculoRegistral> lccr_return;
			int                          li_contador;

			lccr_return = DaoCreator.getCirculoRegistralDAO(ldm_manager).findAllOrips();

			if((lccr_return != null) && (lccr_return.size() > 0))
			{
				lto_response     = new TipoOrip[lccr_return.size()];
				li_contador      = 0;

				for(CirculoRegistral lcr_item : lccr_return)
				{
					if(lcr_item != null)
						lto_response[li_contador++] = new TipoOrip(
							    lcr_item.getIdCirculo(), lcr_item.getNombreCirculoRegistral(),
							    NumericUtils.getBigInteger(lcr_item.getIdDepartamento()),
							    lcr_item.getNombreDepartamento(), NumericUtils.getBigInteger(lcr_item.getIdMunicipio()),
							    lcr_item.getNombreMunicipio()
							);
				}

				ls_descripcionMensaje = DescripcionMensajeCommon.EXITO;
			}
			else
				ls_descripcionMensaje = DescripcionMensajeCommon.SIN_RESULTADOS;
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("obtenerORIPs", lb2be_e);

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
			clh_LOGGER.error("obtenerORIPs", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaObtenerORIPs ltsoo_return;

			if(lto_response == null)
			{
				lto_response        = new TipoOrip[1];
				lto_response[0]     = new TipoOrip("", "", BigInteger.valueOf(0), "", BigInteger.valueOf(0), "");
			}

			ltsoo_return = new TipoSalidaObtenerORIPs(lto_response, lbi_codigoMensaje, ls_descripcionMensaje);

			return ltsoo_return;
		}
	}

	/**
	 * Retorna el valor del objeto de Collection de obtener los roles.
	 *
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_localIp correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection de ROl
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Rol
	 */
	public synchronized TipoSalidaObtenerRoles obtenerRoles(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		DAOManager  ldm_manager;
		String      ls_descripcionMensaje;
		RolTypeOR[] ltr_response;
		BigInteger  lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		ltr_response              = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();

		try
		{
			Collection<Rol> lcr_return;
			int             li_contador;

			lcr_return = DaoCreator.getRolDAO(ldm_manager).findAll(true);

			if((lcr_return != null) && (lcr_return.size() > 0))
			{
				ltr_response     = new RolTypeOR[lcr_return.size()];
				li_contador      = 0;

				for(Rol lr_item : lcr_return)
				{
					if(lr_item != null)
						ltr_response[li_contador++] = new RolTypeOR(
							    lr_item.getIdRol(), lr_item.getNombre(), lr_item.getIdComponente()
							);
				}

				ls_descripcionMensaje = DescripcionMensajeCommon.EXITO;
			}
			else
				ls_descripcionMensaje = DescripcionMensajeCommon.SIN_RESULTADOS;
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("obtenerRoles", lb2be_e);

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
			clh_LOGGER.error("obtenerRoles", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaObtenerRoles ltsoce_return;

			if(ltr_response == null)
			{
				ltr_response        = new RolTypeOR[1];
				ltr_response[0]     = new RolTypeOR("", "", "");
			}

			ltsoce_return = new TipoSalidaObtenerRoles(ltr_response, lbi_codigoMensaje, ls_descripcionMensaje);

			return ltsoce_return;
		}
	}

	/**
	 * Retorna el valor del objeto de Collection para obtener los roles por componentes.
	 *
	 * @param ateorc_entrada correspondiente al valor del tipo de objeto TipoEntradaObtenerRolesComponente
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_localIp correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Rol
	 */
	public synchronized TipoSalidaObtenerRolesComponente obtenerRolesComponente(
	    TipoEntradaObtenerRolesComponente ateorc_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		String       ls_descripcionMensaje;
		RolTypeORC[] ltr_response;
		BigInteger   lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		ltr_response              = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();

		try
		{
			if(ateorc_entrada != null)
			{
				String ls_compomente;

				ls_compomente = ateorc_entrada.getComponente();

				if(StringUtils.isValidString(ls_compomente))
				{
					Componente lc_componente;

					lc_componente = DaoCreator.getAdministracionComponentesDAO(ldm_manager).findById(
						    ls_compomente, true
						);

					if(lc_componente != null)
					{
						Collection<Rol> lcr_return;
						int             li_contador;

						lcr_return = DaoCreator.getRolDAO(ldm_manager)
								                   .findAllByComponente(lc_componente.getIdComponente(), true);

						if((lcr_return != null) && (lcr_return.size() > 0))
						{
							ltr_response     = new RolTypeORC[lcr_return.size()];
							li_contador      = 0;

							for(Rol lr_item : lcr_return)
							{
								if(lr_item != null)
									ltr_response[li_contador++] = new RolTypeORC(
										    lr_item.getIdRol(), lr_item.getNombre(), lr_item.getIdComponente()
										);
							}

							ls_descripcionMensaje = DescripcionMensajeCommon.EXITO;
						}
						else
							ls_descripcionMensaje = DescripcionMensajeCommon.SIN_RESULTADOS;
					}
					else
						throw new B2BException(addErrorCA(ErrorKeys.ERROR_AGRUPACION_NO_DEFINIDA));
				}
				else
					throw new B2BException(addErrorCA(ErrorKeys.ERROR_COMPONENTE_NO_VALIDO));
			}
			else
				throw new B2BException(addErrorCA(ErrorKeys.ERROR_OPERACION_NO_EXITOSA));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("obtenerRolesComponente", lb2be_e);

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
			clh_LOGGER.error("obtenerRolesComponente", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaObtenerRolesComponente ltsoo_return;

			if(ltr_response == null)
			{
				ltr_response        = new RolTypeORC[1];
				ltr_response[0]     = new RolTypeORC("", "", "");
			}

			ltsoo_return = new TipoSalidaObtenerRolesComponente(ltr_response, lbi_codigoMensaje, ls_descripcionMensaje);

			return ltsoo_return;
		}
	}

	/**
	 * Retorna el valor del objeto de Collection para obtener los roles de usuarios.
	 *
	 * @param ateoru_entrada correspondiente al valor del tipo de objeto TipoEntradaObtenerRolesUsuario
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_localIp correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection de Rol
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Rol
	 */
	public synchronized TipoSalidaObtenerRolesUsuario obtenerRolesUsuario(
	    TipoEntradaObtenerRolesUsuario ateoru_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		String       ls_descripcionMensaje;
		RolTypeORU[] ltr_response;
		BigInteger   lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		ltr_response              = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();

		try
		{
			if(ateoru_entrada != null)
			{
				String ls_usuarioLogin;
				String ls_compomente;

				ls_usuarioLogin     = ateoru_entrada.getLoginUsuario();
				ls_compomente       = ateoru_entrada.getComponente();

				if(StringUtils.isValidString(ls_usuarioLogin))
				{
					if(StringUtils.isValidString(ls_compomente))
					{
						Usuario lu_usuario;

						lu_usuario = DaoCreator.getUsuarioDAO(ldm_manager).findById(new Usuario(ls_usuarioLogin));

						if(lu_usuario != null)
						{
							Collection<Rol> lcr_return;
							int             li_contador;

							lcr_return = DaoCreator.getRolDAO(ldm_manager).findUsuarioRol(
								    ls_usuarioLogin, ls_compomente
								);

							if((lcr_return != null) && (lcr_return.size() > 0))
							{
								ltr_response     = new RolTypeORU[lcr_return.size()];
								li_contador      = 0;

								for(Rol lr_item : lcr_return)
								{
									if(lr_item != null)
										ltr_response[li_contador++] = new RolTypeORU(
											    lr_item.getIdRol(), lr_item.getNombre()
											);
								}

								ls_descripcionMensaje = DescripcionMensajeCommon.EXITO;
							}
							else
								ls_descripcionMensaje = DescripcionMensajeCommon.SIN_RESULTADOS;
						}
						else
							throw new B2BException(addErrorCA(ErrorKeys.ERROR_USUARIO_INEXISTENTE_E1));
					}
					else
						throw new B2BException(addErrorCA(ErrorKeys.ERROR_COMPONENTE_NO_VALIDO));
				}
				else
					throw new B2BException(addErrorCA(ErrorKeys.ERROR_USUARIO_NO_VALIDO));
			}
			else
				throw new B2BException(addErrorCA(ErrorKeys.ERROR_OPERACION_NO_EXITOSA));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("obtenerRolesUsuario", lb2be_e);

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
			clh_LOGGER.error("obtenerRolesUsuario", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaObtenerRolesUsuario ltsoo_return;

			if(ltr_response == null)
			{
				ltr_response        = new RolTypeORU[1];
				ltr_response[0]     = new RolTypeORU("", "");
			}

			ltsoo_return = new TipoSalidaObtenerRolesUsuario(ltr_response, lbi_codigoMensaje, ls_descripcionMensaje);

			return ltsoo_return;
		}
	}

	/**
	 * Obtener usuarios rol orip.
	 *
	 * @param ateouro_request de ateouro request
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida obtener usuarios rol orip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaObtenerUsuariosRolOrip obtenerUsuariosRolOrip(
	    TipoEntradaObtenerUsuariosRolOrip ateouro_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		String        ls_descripcionMensaje;
		TipoUsuario[] ltu_response;
		BigInteger    lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		ltu_response              = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();

		try
		{
			if(ateouro_request != null)
			{
				String ls_codigoCirculo;
				String ls_codigoRol;

				ls_codigoCirculo     = ateouro_request.getCodigoCirculo();
				ls_codigoRol         = ateouro_request.getCodigoRol();

				if(StringUtils.isValidString(ls_codigoRol))
				{
					{
						Rol lr_rol;

						lr_rol = DaoCreator.getRolDAO(ldm_manager).findById(ls_codigoRol, true);

						if(lr_rol == null)
							throw new B2BException("409 - El rol ingresado no existe o se encuentra inactivo");
					}

					{
						CirculoRegistral lcr_circulo;
						lcr_circulo = DaoCreator.getCirculoRegistralDAO(ldm_manager).findById(ls_codigoCirculo);

						if(lcr_circulo == null)
							throw new B2BException("409 - El circulo ingresado no existe o se encuentra inactivo");
					}

					{
						Collection<Usuario> lceec_return;
						int                 li_contador;

						lceec_return = DaoCreator.getUsuarioDAO(ldm_manager)
								                     .findAllUsersByCirculoRol(ls_codigoCirculo, ls_codigoRol, false);

						if((lceec_return != null) && (lceec_return.size() > 0))
						{
							ltu_response     = new TipoUsuario[lceec_return.size()];
							li_contador      = 0;

							for(Usuario leec_item : lceec_return)
							{
								if(leec_item != null)
									ltu_response[li_contador++] = new TipoUsuario(
										    StringUtils.getStringNotNull(leec_item.getPrimerNombre()),
										    StringUtils.getStringNotNull(leec_item.getSegundoNombre()),
										    StringUtils.getStringNotNull(leec_item.getPrimerApellido()),
										    StringUtils.getStringNotNull(leec_item.getSegundoApellido()),
										    leec_item.getIdUsuario()
										);
							}

							ls_descripcionMensaje = DescripcionMensajeCommon.EXITO;
						}
					}
				}
				else
					throw new B2BException(addErrorCA(ErrorKeys.ERROR_ENTIDAD_INVALIDO_E1));
			}
			else
				throw new B2BException(addErrorCA(ErrorKeys.ERROR_OPERACION_NO_EXITOSA));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("obtenerUsuariosRolOrip", lb2be_e);

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
			clh_LOGGER.error("obtenerUsuariosRolOrip", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaObtenerUsuariosRolOrip ltsoce_return;

			if(ltu_response == null)
			{
				ltu_response     = new TipoUsuario[1];

				ltu_response[0] = new TipoUsuario("", "", "", "", "");
			}

			ltsoce_return = new TipoSalidaObtenerUsuariosRolOrip(
				    ltu_response, lbi_codigoMensaje, ls_descripcionMensaje
				);

			return ltsoce_return;
		}
	}

	/**
	 * Registrar usuario.
	 *
	 * @param ateru_entrada correspondiente al valor del tipo de objeto TipoEntradaRegistrarUsuario
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_localIp correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return el valor de tipo salida registrar usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized TipoSalidaRegistrarUsuario registrarUsuario(
	    TipoEntradaRegistrarUsuario ateru_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_descripcionMensaje;
		BigInteger lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();

		try
		{
			if(ateru_entrada != null)
			{
				String            ls_usuarioLogin;
				RolDAO            lrd_DAO;
				UsuarioRolDAO     lurd_DAO;
				UsuarioCirculoDAO lucd_DAO;
				UsuarioEtapaDAO   lued_DAO;

				ls_usuarioLogin     = ateru_entrada.getLoginUsuario();
				lrd_DAO             = DaoCreator.getRolDAO(ldm_manager);
				lurd_DAO            = DaoCreator.getUsuarioRolDAO(ldm_manager);
				lucd_DAO            = DaoCreator.getUsuarioCirculoDAO(ldm_manager);
				lued_DAO            = DaoCreator.getUsuarioEtapaDAO(ldm_manager);

				if(StringUtils.isValidString(ls_usuarioLogin))
				{
					lurd_DAO.updateStateByIdUser(
					    new UsuarioRol(ls_usuarioLogin, null, EstadoCommon.NO, as_userId, as_remoteIp)
					);
					lucd_DAO.updateStateByIdUser(
					    new UsuarioCirculo(ls_usuarioLogin, null, EstadoCommon.NO, as_userId, as_remoteIp)
					);
					lued_DAO.updateStateByIdUser(
					    new UsuarioEtapa(ls_usuarioLogin, null, EstadoCommon.NO, as_userId, as_remoteIp)
					);
				}

				{
					CirculoRegistralDao lcrd_DAO;
					Constantes          lc_constantes;
					Map<String, String> lmss_rolesOrip;

					lcrd_DAO           = DaoCreator.getCirculoRegistralDAO(ldm_manager);
					lc_constantes      = DaoCreator.getConstantesDAO(ldm_manager)
							                           .findById(ConstanteCommon.ROLES_UNICOS_POR_ORIP);
					lmss_rolesOrip     = null;

					if(lc_constantes != null)
						lmss_rolesOrip = ListadoCodigosConstantes.generarCodigos(lc_constantes.getCaracter());

					String[] lsa_codigosOrips;
					lsa_codigosOrips = ateru_entrada.getCodigosORIPs();

					if(
					    CollectionUtils.isValidCollection(lsa_codigosOrips)
						    && CollectionUtils.isValidCollection(lmss_rolesOrip)
					)
					{
						for(String ls_OripItem : lsa_codigosOrips)
						{
							if(StringUtils.isValidString(ls_OripItem))
							{
								CirculoRegistral lcr_cr;

								lcr_cr = lcrd_DAO.findById(new CirculoRegistral(ls_OripItem, null));

								if(lcr_cr != null)
								{
									String[] lsa_codigosRoles;

									lsa_codigosRoles = ateru_entrada.getCodigosRoles();

									if(CollectionUtils.isValidCollection(lsa_codigosRoles))
									{
										for(String ls_rolItem : lsa_codigosRoles)
										{
											if(StringUtils.isValidString(ls_rolItem))
											{
												Rol lr_rol;

												lr_rol = lrd_DAO.findById(new Rol(ls_rolItem));

												if(lr_rol != null)
												{
													Collection<Rol> lcr_rolesOrip;

													lcr_rolesOrip = lrd_DAO.findUsuarioRolOrip(ls_OripItem, ls_rolItem);

													if(CollectionUtils.isValidCollection(lcr_rolesOrip))
													{
														for(Rol lr_item : lcr_rolesOrip)
														{
															if(lr_item != null)
															{
																if(lmss_rolesOrip.containsKey(lr_item.getIdRol()))
																{
																	Object[] aoa_messageArgs;

																	aoa_messageArgs     = new String[1];

																	aoa_messageArgs[0] = lr_item.getNombre();

																	throw new B2BException(
																	    addErrorCA(
																	        ErrorKeys.ERROR_NEG_ROL_E1_E2,
																	        aoa_messageArgs
																	    )
																	);
																}
															}
														}
													}
												}
												else
												{
													Object[] aoa_messageArgs;

													aoa_messageArgs     = new String[1];

													aoa_messageArgs[0] = ls_rolItem;

													throw new B2BException(
													    addErrorCA(
													        ErrorKeys.ERROR_CODIGO_ROL_NO_VALIDO, aoa_messageArgs
													    )
													);
												}
											}
											else
											{
												Object[] aoa_messageArgs;

												aoa_messageArgs     = new String[1];

												aoa_messageArgs[0] = ls_rolItem;

												throw new B2BException(
												    addErrorCA(ErrorKeys.ERROR_CODIGO_ROL_NO_VALIDO, aoa_messageArgs)
												);
											}
										}
									}
									else
										throw new B2BException(addErrorCA(ErrorKeys.ERROR_USUARIO_SIN_ORIP_ROL));
								}
								else
								{
									Object[] aoa_messageArgs;

									aoa_messageArgs     = new String[1];

									aoa_messageArgs[0] = ls_OripItem;

									throw new B2BException(
									    addErrorCA(ErrorKeys.ERROR_CODIGO_INVALIDO_E2, aoa_messageArgs)
									);
								}
							}
							else
							{
								Object[] aoa_messageArgs;

								aoa_messageArgs     = new String[1];

								aoa_messageArgs[0] = ls_OripItem;

								throw new B2BException(addErrorCA(ErrorKeys.ERROR_CODIGO_INVALIDO_E2, aoa_messageArgs));
							}
						}
					}
					else
						throw new B2BException(addErrorCA(ErrorKeys.ERROR_USUARIO_SIN_ORIP_ROL));
				}

				{
					UsuarioDAO lud_DAO;
					String     ls_tipoCambio;

					lud_DAO           = DaoCreator.getUsuarioDAO(ldm_manager);
					ls_tipoCambio     = StringUtils.getStringNotNull(ateru_entrada.getTipoCambio().getValue());

					if(StringUtils.isValidString(ls_usuarioLogin))
					{
						Usuario lu_usuario;

						lu_usuario = lud_DAO.findById(new Usuario(ls_usuarioLogin));

						if(lu_usuario == null)
						{
							if(ls_tipoCambio.equalsIgnoreCase(TipoCambioCommon.CREACION))
								lu_usuario = new Usuario(ls_usuarioLogin);
							else
								throw new B2BException(addErrorCA(ErrorKeys.ERROR_NEG_FUNCIONARIO_E2));
						}
						else
						{
							if(ls_tipoCambio.equalsIgnoreCase(TipoCambioCommon.CREACION))
								throw new B2BException(addErrorCA(ErrorKeys.ERROR_NEG_FUNCIONARIO_E1));
						}

						if(lu_usuario != null)
						{
							{
								String ls_idDocumentoTipo;

								ls_idDocumentoTipo = ateru_entrada.getTipoDocumento();

								if(StringUtils.isValidString(ls_idDocumentoTipo))
								{
									InteresadoDocumentoTipo litd_tipoDocumento;

									litd_tipoDocumento = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager)
											                           .findById(ls_idDocumentoTipo);

									if(litd_tipoDocumento != null)
										lu_usuario.setIdDocumentoTipo(ls_idDocumentoTipo);
									else
										throw new B2BException(addErrorCA(ErrorKeys.ERROR_ATTR_USUARIO_E1));
								}
								else
									throw new B2BException(addErrorCA(ErrorKeys.ERROR_ATTR_USUARIO_E1));
							}

							{
								String ls_numeroDocumento;

								ls_numeroDocumento = ateru_entrada.getNumeroDocumento();

								if(StringUtils.isValidString(ls_numeroDocumento))
									lu_usuario.setNumeroDocumento(ls_numeroDocumento);
								else
									throw new B2BException(addErrorCA(ErrorKeys.ERROR_ATTR_USUARIO_E2));
							}

							{
								String ls_primerNombre;
								String ls_primerApellido;

								ls_primerNombre       = ateru_entrada.getPrimerNombreUsuario();
								ls_primerApellido     = ateru_entrada.getPrimerApellidoUsuario();

								if(
								    StringUtils.isValidString(ls_primerNombre)
									    && StringUtils.isValidString(ls_primerApellido)
								)
								{
									String ls_segundoNombre;
									String ls_segundoApellido;

									ls_segundoNombre       = ateru_entrada.getSegundoNombreUsuario();
									ls_segundoApellido     = ateru_entrada.getSegundoApellidoUsuario();

									lu_usuario.setPrimerNombre(ls_primerNombre);
									lu_usuario.setSegundoNombre(
									    StringUtils.isValidString(ls_segundoNombre) ? ls_segundoNombre : null
									);
									lu_usuario.setPrimerApellido(ls_primerApellido);
									lu_usuario.setSegundoApellido(
									    StringUtils.isValidString(ls_segundoApellido) ? ls_segundoApellido : null
									);
								}
								else
									throw new B2BException(addErrorCA(ErrorKeys.ERROR_ATTR_USUARIO_E4));
							}

							{
								String ls_emailPersonal;
								String ls_emailCorporativo;

								ls_emailPersonal        = ateru_entrada.getCorreoElectronicoPersonal();
								ls_emailCorporativo     = ateru_entrada.getCorreoElectronicoCorporativo();

								if(StringUtils.isValidString(ls_emailPersonal))
								{
									if(!ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_emailPersonal))
										throw new B2BException(addErrorCA(ErrorKeys.ERROR_ATTR_USUARIO_E5));

									lu_usuario.setCorreoElectronico(ls_emailPersonal);
								}
								else
									throw new B2BException(addErrorCA(ErrorKeys.ERROR_ATTR_USUARIO_E3));

								if(StringUtils.isValidString(ls_emailCorporativo))
								{
									if(!ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_emailCorporativo))
										throw new B2BException(addErrorCA(ErrorKeys.ERROR_ATTR_USUARIO_E6));

									lu_usuario.setCorreoElectronicoCorporativo(ls_emailCorporativo);
								}

								if(ls_emailPersonal.equalsIgnoreCase(ls_emailCorporativo))
									throw new B2BException(addErrorCA(ErrorKeys.ERROR_ATTR_USUARIO_E7));
							}

							{
								TipoEntradaRegistrarUsuarioSegundoFactor lterusf_segundoFactor;

								lterusf_segundoFactor = ateru_entrada.getSegundoFactor();

								if(lterusf_segundoFactor != null)
								{
									String ls_segundoFactor;

									ls_segundoFactor = lterusf_segundoFactor.getValue();

									if(StringUtils.isValidString(ls_segundoFactor))
									{
										Date ld_fechaVigenciaSegundoClave;

										ld_fechaVigenciaSegundoClave = null;

										{
											Calendar lc_c;

											lc_c = ateru_entrada.getFechaVigenciaSegundaClave();

											if(lc_c != null)
												ld_fechaVigenciaSegundoClave = lc_c.getTime();
											else
												throw new B2BException(addErrorCA(ErrorKeys.ERROR_FECHA_SEGUNDA_CLAVE));
										}

										if((ld_fechaVigenciaSegundoClave != null))
										{
											if(new Date().before(ld_fechaVigenciaSegundoClave))
											{
												lu_usuario.setSegundoFactorAutenticacion(ls_segundoFactor);
												lu_usuario.setFechaVigenciaSegundaClave(ld_fechaVigenciaSegundoClave);
											}
											else
												throw new B2BException(addErrorCA(ErrorKeys.ERROR_FECHA_SEGUNDA_CLAVE));
										}
									}
								}
							}

							{
								String ls_codigoConvenio;
								String ls_codigoEntidad;

								ls_codigoConvenio     = ateru_entrada.getCodigoConvenio();
								ls_codigoEntidad      = ateru_entrada.getIdEntidadExterna();

								if(StringUtils.isValidString(ls_codigoEntidad))
								{
									AccEntidadExterna laee_entidad;

									laee_entidad = DaoCreator.getAccEntidadExternaDAO(ldm_manager)
											                     .findByIdAccEntidadExterna(ls_codigoEntidad);

									if(laee_entidad != null)
									{
										if(StringUtils.isValidString(ls_codigoConvenio))
										{
											AccEntidadExternaConvenio laeec_convenio;

											laeec_convenio = DaoCreator.getAccEntidadExternaConvenioDAO(ldm_manager)
													                       .findById(
													    new AccEntidadExternaConvenio(
													        ls_codigoEntidad, ls_codigoConvenio
													    )
													);

											if(laeec_convenio != null)
											{
												lu_usuario.setIdEntidadExterna(ls_codigoEntidad);
												lu_usuario.setNumeroConvenio(ls_codigoConvenio);
											}
											else
												throw new B2BException(addErrorCA(ErrorKeys.ERROR_CODIGO_INVALIDO_E3));
										}
										else
											throw new B2BException(addErrorCA(ErrorKeys.ERROR_CODIGO_INVALIDO_E3));
									}
									else
										throw new B2BException(addErrorCA(ErrorKeys.ERROR_CODIGO_INVALIDO_E4));
								}
							}

							{
								Date ld_fechaInicio;
								Date ld_fechaFin;

								ld_fechaInicio     = null;
								ld_fechaFin        = null;

								{
									Calendar lc_c;

									lc_c = ateru_entrada.getFechaInicio();

									if(lc_c != null)
										ld_fechaInicio = lc_c.getTime();
									else
										throw new B2BException(addErrorCA(ErrorKeys.ERROR_FECHA_INICIO_VACIA));
								}

								{
									Calendar lc_c;

									lc_c = ateru_entrada.getFechaFin();

									if(lc_c != null)
										ld_fechaFin = lc_c.getTime();
									else
										throw new B2BException(addErrorCA(ErrorKeys.ERROR_FECHA_FIN_VACIA));
								}

								if((ld_fechaInicio != null) && (ld_fechaFin != null))
								{
									if(!ld_fechaFin.before(ld_fechaInicio))
									{
										lu_usuario.setFechaDesde(ld_fechaInicio);
										lu_usuario.setFechaHasta(ld_fechaFin);
									}
									else
										throw new B2BException(addErrorCA(ErrorKeys.ERROR_FECHA_FIN_NO_VALIDA));
								}
							}

							{
								TipoEntradaRegistrarUsuarioEstadoUsuario lterueu_estadoUsuario;

								lterueu_estadoUsuario = ateru_entrada.getEstadoUsuario();

								if(lterueu_estadoUsuario != null)
								{
									String ls_estadoUsuario;

									ls_estadoUsuario = lterueu_estadoUsuario.getValue();

									if(StringUtils.isValidString(ls_estadoUsuario))
									{
										String ls_estado;

										/*
										 * Indicador del estado del usuario, posibles valores:
										 * S=Activo
										 * N=No Activo retiro definitivo
										 * B=Bloqueado temporal horas
										 * I=No activo temporalmente por vacaciones o licencias
										 */
										if(ls_estadoUsuario.equalsIgnoreCase(EstadoCommon.ACTIVO))
											ls_estado = EstadoCommon.SI;
										else if(ls_estadoUsuario.equalsIgnoreCase(EstadoCommon.PERMISO))
											ls_estado = EstadoCommon.INACTIVO;
										else if(ls_estadoUsuario.equalsIgnoreCase(EstadoCommon.LICENCIA))
											ls_estado = EstadoCommon.INACTIVO;
										else if(ls_estadoUsuario.equalsIgnoreCase(EstadoCommon.INACTIVO))
											ls_estado = EstadoCommon.NO;
										else
											ls_estado = EstadoCommon.BLOQUEADO;

										lu_usuario.setActivo(ls_estado);
									}
									else
										throw new B2BException(addErrorCA(ErrorKeys.ERROR_ESTADO_USUARIO_NO_VALIDO));
								}
								else
									throw new B2BException(addErrorCA(ErrorKeys.ERROR_ESTADO_USUARIO_NO_VALIDO));
							}

							{
								String ls_numeroSolitiud;

								ls_numeroSolitiud = ateru_entrada.getNumeroSolicitud();

								if(StringUtils.isValidString(ls_numeroSolitiud))
									lu_usuario.setNumeroRadicadoSolicitud(ls_numeroSolitiud);
								else
									throw new B2BException(addErrorCA(ErrorKeys.ERROR_NUMERO_SOLICITUD_NO_VALIDO));
							}

							{
								String ls_cargo;

								ls_cargo = ateru_entrada.getCargo();

								if(StringUtils.isValidString(ls_cargo))
									lu_usuario.setCargo(ls_cargo);
								else
									throw new B2BException(addErrorCA(ErrorKeys.ERROR_CARGO_NO_VALIDO));
							}

							if(ls_tipoCambio.equalsIgnoreCase(TipoCambioCommon.MODIFICACION))
							{
								String ls_justificacionCambio;

								ls_justificacionCambio = ateru_entrada.getJustificacionCambio();

								if(StringUtils.isValidString(ls_justificacionCambio))
									lu_usuario.setJustificacionCambio(ls_justificacionCambio);
								else
									throw new B2BException(addErrorCA(ErrorKeys.ERROR_JUSTIFICACION_CAMBIO_NO_VALIDO));
							}

							{
								String ls_idPolitica;

								ls_idPolitica = ateru_entrada.getIdPolitica();

								if(StringUtils.isValidString(ls_idPolitica))
								{
									if(ls_idPolitica.length() > IdentificadoresCommon.TAMANIO_COLUMNA_ID)
										throw new B2BException(addErrorCA(ErrorKeys.ERROR_ID_POLITICA_NO_VALIDO));

									lu_usuario.setIdPolitica(ls_idPolitica);
								}
							}

							lu_usuario.setIdUsuarioCreacion(as_userId);
							lu_usuario.setIpCreacion(as_remoteIp);
							lu_usuario.setIdUsuarioModificacion(as_userId);
							lu_usuario.setIpModificacion(as_remoteIp);

							if(ls_tipoCambio.equalsIgnoreCase(TipoCambioCommon.CREACION))
								lud_DAO.insertUser(lu_usuario);
							else
								lud_DAO.updateUser(lu_usuario);

							{
								String[] lsa_codigosOrips;
								lsa_codigosOrips = ateru_entrada.getCodigosORIPs();

								if(CollectionUtils.isValidCollection(lsa_codigosOrips))
								{
									for(String ls_OripItem : lsa_codigosOrips)
									{
										if(StringUtils.isValidString(ls_OripItem))
										{
											boolean        lb_existe;
											UsuarioCirculo luc_usuarioCirculo;

											lb_existe              = true;
											luc_usuarioCirculo     = lucd_DAO.findById(
												    new UsuarioCirculo(ls_usuarioLogin, ls_OripItem)
												);

											if(luc_usuarioCirculo == null)
											{
												luc_usuarioCirculo     = new UsuarioCirculo(
													    ls_usuarioLogin, ls_OripItem
													);
												lb_existe              = false;
											}

											luc_usuarioCirculo.setFechaDesde(lu_usuario.getFechaDesde());
											luc_usuarioCirculo.setFechaHasta(lu_usuario.getFechaHasta());

											{
												String ls_activo;

												ls_activo = lu_usuario.getActivo();

												if(StringUtils.isValidString(ls_activo))
													luc_usuarioCirculo.setActivo(
													    ls_activo.equalsIgnoreCase(EstadoCommon.SI) ? ls_activo
													                                                : EstadoCommon.NO
													);
												else
													luc_usuarioCirculo.setActivo(EstadoCommon.NO);
											}

											luc_usuarioCirculo.setIdUsuarioCreacion(as_userId);
											luc_usuarioCirculo.setIdUsuarioModificacion(as_userId);
											luc_usuarioCirculo.setIpCreacion(as_remoteIp);
											luc_usuarioCirculo.setIpModificacion(as_remoteIp);

											if(lb_existe)
												lucd_DAO.updateUserCircle(luc_usuarioCirculo);
											else
												lucd_DAO.insertUserCircle(luc_usuarioCirculo);
										}
									}
								}
								else
									throw new B2BException(addErrorCA(ErrorKeys.ERROR_USUARIO_SIN_ORIP_ROL));
							}

							String[] lsa_codigosRoles;
							lsa_codigosRoles = ateru_entrada.getCodigosRoles();

							if(CollectionUtils.isValidCollection(lsa_codigosRoles))
							{
								for(String ls_rolItem : lsa_codigosRoles)
								{
									if(StringUtils.isValidString(ls_rolItem))
									{
										boolean    lb_existe;
										UsuarioRol lur_usuarioRol;

										lb_existe          = true;
										lur_usuarioRol     = lurd_DAO.findById(
											    new UsuarioRol(ls_usuarioLogin, ls_rolItem)
											);

										if(lur_usuarioRol == null)
										{
											lur_usuarioRol     = new UsuarioRol(ls_usuarioLogin, ls_rolItem);
											lb_existe          = false;
										}

										lur_usuarioRol.setFechaDesde(lu_usuario.getFechaDesde());
										lur_usuarioRol.setFechaHasta(lu_usuario.getFechaHasta());

										{
											String ls_activo;

											ls_activo = lu_usuario.getActivo();

											if(StringUtils.isValidString(ls_activo))
												lur_usuarioRol.setActivo(
												    ls_activo.equalsIgnoreCase(EstadoCommon.SI) ? ls_activo
												                                                : EstadoCommon.NO
												);
											else
												lur_usuarioRol.setActivo(EstadoCommon.NO);
										}

										lur_usuarioRol.setIdUsuarioCreacion(as_userId);
										lur_usuarioRol.setIdUsuarioModificacion(as_userId);
										lur_usuarioRol.setIpCreacion(as_remoteIp);
										lur_usuarioRol.setIpModificacion(as_remoteIp);

										if(lb_existe)
											lurd_DAO.updateUserRol(lur_usuarioRol);
										else
											lurd_DAO.insertUserRol(lur_usuarioRol);
									}
								}
							}
							else
								throw new B2BException(addErrorCA(ErrorKeys.ERROR_USUARIO_SIN_ORIP_ROL));

							{
								Collection<String> lcs_etapas;

								lcs_etapas = lued_DAO.buscarEtapasAutorizadas(ls_usuarioLogin);

								// FORMATO Comentar lambda antes de formatear
								if (CollectionUtils.isValidCollection(lcs_etapas))
								{
									lcs_etapas.forEach
									(
										ls_etapa -> 
										{
											try
											{
												asociarUsuarioEtapas(new UsuarioEtapa(ls_usuarioLogin, ls_etapa,
														EstadoCommon.SI, as_userId, as_remoteIp), lued_DAO);
											}
											catch (B2BException lb2be_e)
											{
												clh_LOGGER.error("registrarUsuario", lb2be_e);
											}
										}
									);
								}
							}

							ls_descripcionMensaje = DescripcionMensajeCommon.EXITO;
						}
					}
					else
						throw new B2BException(addErrorCA(ErrorKeys.ERROR_USUARIO_NO_VALIDO));
				}
			}
			else
				throw new B2BException(addErrorCA(ErrorKeys.ERROR_OPERACION_NO_EXITOSA));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("registrarUsuario", lb2be_e);

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
			clh_LOGGER.error("registrarUsuario", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaRegistrarUsuario ltsoo_return;

			ltsoo_return = new TipoSalidaRegistrarUsuario(lbi_codigoMensaje, ls_descripcionMensaje);

			return ltsoo_return;
		}
	}

	/**
	 * Asociar usuario etapas.
	 *
	 * @param aue_param Objeto de tipo UsuarioEtapa con parametros a asociar
	 * @param lued_DAO Objeto de tipo UsuarioEtapaDAO con transaccion activa
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void asociarUsuarioEtapas(UsuarioEtapa aue_param, UsuarioEtapaDAO lued_DAO)
	    throws B2BException
	{
		try
		{
			if(aue_param != null)
				lued_DAO.insertOrUpdate(
				    aue_param,
				    !(lued_DAO.verificarExistenciaUsuarioEtapa(aue_param.getIdUsuario(), aue_param.getIdEtapa()))
				);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("asociarUsuarioEtapas", lb2be_e);
			throw lb2be_e;
		}
	}
}
