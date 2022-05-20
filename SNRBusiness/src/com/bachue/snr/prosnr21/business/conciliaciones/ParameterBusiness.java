package com.bachue.snr.prosnr21.business.conciliaciones;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import com.aspose.words.CellMerge;
import com.aspose.words.CellVerticalAlignment;
import com.aspose.words.ControlChar;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.PreferredWidth;
import com.aspose.words.SaveFormat;
import com.aspose.words.Table;
import com.aspose.words.TableAlignment;
import com.b2bsg.common.dataAccess2.DAOManager;
import com.b2bsg.common.exception.B2BException;
import com.b2bsg.common.file.ZipEntryUtil;
import com.b2bsg.common.file.ZipUtils;
import com.b2bsg.common.logging.LoggerHandler;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;
import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;
import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr21.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr21.common.constants.PeriodicidadCommon;
import com.bachue.snr.prosnr21.dao.png.AfectacionPrestacionServicioDAO;
import com.bachue.snr.prosnr21.dao.png.EntidadRecaudadoraCuentaDAO;
import com.bachue.snr.prosnr21.dao.png.ExtractoDiarioDAO;
import com.bachue.snr.prosnr21.dao.png.ExtractoMensualDAO;
import com.bachue.snr.prosnr21.dao.png.PeriodoCorteDAO;
import com.bachue.snr.prosnr21.dao.png.SIIFCatalogoDAO;
import com.bachue.snr.prosnr21.model.pgn.AfectacionPrestacionServicio;
import com.bachue.snr.prosnr21.model.pgn.ConArchivo;
import com.bachue.snr.prosnr21.model.pgn.ConInconsistencia;
import com.bachue.snr.prosnr21.model.pgn.ConSiifDetalle;
import com.bachue.snr.prosnr21.model.pgn.ConSiifMaestro;
import com.bachue.snr.prosnr21.model.pgn.CuentaAnalista;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;
import com.bachue.snr.prosnr21.model.pgn.Expediente;
import com.bachue.snr.prosnr21.model.pgn.ExtractoDiario;
import com.bachue.snr.prosnr21.model.pgn.ExtractoMensual;
import com.bachue.snr.prosnr21.model.pgn.HoraEjecucionProceso;
import com.bachue.snr.prosnr21.model.pgn.Periodicidad;
import com.bachue.snr.prosnr21.model.pgn.PeriodoCorte;
import com.bachue.snr.prosnr21.model.pgn.ProcesoConciliacion;
import com.bachue.snr.prosnr21.model.pgn.RPTPrograma;
import com.bachue.snr.prosnr21.model.pgn.Rubro;
import com.bachue.snr.prosnr21.model.pgn.RubroHomologacion;
import com.bachue.snr.prosnr21.model.pgn.SIIFCatalogo;
import com.bachue.snr.prosnr21.model.pgn.TipoDocumental;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import co.gov.supernotariado.www.schemas.bachue.ci.consultacatalogos.consultarcatalogos.v1.CatalogoType;
import co.gov.supernotariado.www.schemas.bachue.ci.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogos;


/**
 * Clase para el manejo del negocio de las tablas parametricas en la base de datos.
 *
 * @author Heiner Castañeda
 */
public class ParameterBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ParameterBusiness.class);

	/**
	 * Método que retorna todas las entidades recaudadoras de conciliación.
	 *
	 * @return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<EntidadRecaudadoraConciliacion> buscarEntidadRecaudadoraConciliacion()
	    throws B2BException
	{
		Collection<EntidadRecaudadoraConciliacion> lcpc_datos;
		DAOManager                                 ldm_manager;

		lcpc_datos      = null;
		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			lcpc_datos = DaoCreator.getEntidadRecaudadoraConciliacionDAO(ldm_manager).findAllActivo();

			if(!CollectionUtils.isValidCollection(lcpc_datos))
				throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarEntidadRecaudadoraConciliacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcpc_datos;
	}

	/**
	 * Méotodo que retorna una colección entidad recaudadora  conciliación mediante el servicio de consumo de catalogos.
	 *
	 * @return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public synchronized Collection<EntidadRecaudadoraConciliacion> buscarEntidadRecaudadoraConciliaciones()
	    throws B2BException
	{
		Collection<EntidadRecaudadoraConciliacion> lcerc_datos;

		lcerc_datos = new LinkedList<EntidadRecaudadoraConciliacion>();

		try
		{
			String                       ls_catalogo;
			TipoSalidaConsultarCatalogos ltscc_consultaCatalogos;

			ls_catalogo                 = com.bachue.snr.prosnr21.common.constants.ConstanteCommon.ENTIDAD_RECAUDADORA;
			ltscc_consultaCatalogos     = consultarCatalogos(
				    ls_catalogo, "CONCILIACIONES", null, DaoManagerFactory.getDAOManagerConciliacion()
				);

			if(ltscc_consultaCatalogos != null)
			{
				CatalogoType[] lct_catalogos;

				lct_catalogos = ltscc_consultaCatalogos.getCatalogos();

				if(lct_catalogos != null)
				{
					for(CatalogoType lct_catalogo : lct_catalogos)
					{
						if(lct_catalogo != null)
						{
							String ls_codigo;
							String ls_nombreCatalogo;
							ls_codigo             = lct_catalogo.getCodigo();
							ls_nombreCatalogo     = lct_catalogo.getNombre();

							if(StringUtils.isValidString(ls_codigo) && StringUtils.isValidString(ls_nombreCatalogo))
							{
								EntidadRecaudadoraConciliacion lp_proceso;
								lp_proceso = new EntidadRecaudadoraConciliacion();
								lp_proceso.setCodigoEntidadRecaudadora(ls_codigo);

								Object lo_object;

								lo_object = new Gson().fromJson(String.valueOf(ls_nombreCatalogo), Object.class);

								if((lo_object != null) && (lo_object instanceof Map<?, ?>))
								{
									Map<String, String> lltm_datos;

									lltm_datos = (LinkedTreeMap<String, String>)lo_object;

									if(CollectionUtils.isValidCollection(lltm_datos))
									{
										String ls_idEntidadRecaudadora;
										String ls_nombreEntidad;

										ls_idEntidadRecaudadora     = lltm_datos.containsKey(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.ID_ENTIDAD_RECAUDADORA
											)
											? lltm_datos.get(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.ID_ENTIDAD_RECAUDADORA
											) : null;

										ls_nombreEntidad = lltm_datos.containsKey(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.NOMBRE_ENTIDAD_RECAUDADORA
											)
											? lltm_datos.get(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.NOMBRE_ENTIDAD_RECAUDADORA
											) : null;
										lp_proceso.setIdEntidadRecaudadora(ls_idEntidadRecaudadora);
										lp_proceso.setNombreEntidadRecaudadora(ls_nombreEntidad);
									}
								}

								lcerc_datos.add(lp_proceso);
							}
						}
					}
				}
			}
			else
				throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("buscarEntidadRecaudadoraConciliaciones", lb2be_e);

			throw lb2be_e;
		}

		return lcerc_datos;
	}
	
	/**
	 * Buscar extracto diario by cuenta banco fecha.
	 *
	 * @param aed_parametros de aed parametros
	 * @return el valor de extracto diario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized ExtractoDiario buscarExtractoDiarioByCuentaBancoFecha(ExtractoDiario aed_parametros, 
			String as_idUsuario, String as_remoteIp)
		throws B2BException 
	{
		
		ExtractoDiario 		led_result;
		DAOManager     		ldm_manager;
		
		led_result 			= null;
		ldm_manager     	= DaoManagerFactory.getDAOManagerConciliacion();
		
		try
		{
			if(aed_parametros != null) 
			{
				ExtractoDiarioDAO 	led_dao;
				Date 				ld_fecha;
				
				led_dao 	= DaoCreator.getExtractoDiarioDAO(ldm_manager);
				ld_fecha 	= aed_parametros.getFechaMovimiento();
				
				{
					PeriodoCorte lpc_corte;
					lpc_corte = DaoCreator.getPeriodoCorteDAO(ldm_manager).findByDiaCorte(ld_fecha);
					
					if(lpc_corte != null)
						led_dao.procCreaExtractoDiario(aed_parametros.getIdCuenta(),lpc_corte.getIdPeriodoCorte(),as_idUsuario, as_remoteIp);
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
				
				led_result = led_dao.findByBancoCuentaFecha(aed_parametros);
				
				if(led_result == null) 
				{
					EntidadRecaudadoraCuenta    lerc_objTemp;
					
					lerc_objTemp = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager).findById(aed_parametros.getIdCuenta());
					
					if(lerc_objTemp != null) 
					{
						String     	ls_dateStr;
						Object[] 	loa_messageArgs;
						
						loa_messageArgs 		= new String[3];
						ls_dateStr     			= new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO).format(ld_fecha);
						loa_messageArgs[0]     	= ls_dateStr;
						loa_messageArgs[1]     	= lerc_objTemp.getNombreEntidadRecaudadora();
						loa_messageArgs[2]     	= lerc_objTemp.getNumeroCuenta();
						
						throw new B2BException(addErrorCMF(ErrorKeys.NO_EXISTE_INFORMACION_PARA_ESA_BUSQUEDA,loa_messageArgs));
						
					}
					else 
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarExtractoDiarioByCuentaBancoFecha", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
		
		return led_result;
	}

	/**
	 * Méotodo que retorna una colección con todos los procesos de conciliación.
	 *
	 * @return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public synchronized Collection<ProcesoConciliacion> buscarProcesosConciliacion()
	    throws B2BException
	{
		Collection<ProcesoConciliacion> lcpc_datos;
		DAOManager                      ldm_manager;

		lcpc_datos      = null;
		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			lcpc_datos = buscarProcesosConciliacion(ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarProcesosConciliacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcpc_datos;
	}

	/**
	 * Méotodo que retorna una colección con todos los procesos de conciliación.
	 *
	 * @return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados los procesos de conciliacion
	 * activos
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public synchronized Collection<ProcesoConciliacion> buscarProcesosConciliacionActivos()
	    throws B2BException
	{
		Collection<ProcesoConciliacion> lcpc_datos;
		DAOManager                      ldm_manager;

		lcpc_datos      = null;
		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			lcpc_datos = DaoCreator.getProcesoConciliacionDAO(ldm_manager).findAllActive();

			if(!CollectionUtils.isValidCollection(lcpc_datos))
				throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarProcesosConciliacionActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcpc_datos;
	}

	/**
	 * Méotodo que retorna una colección con todos los procesos de conciliación mediante el servicio de consumo de catalogos.
	 *
	 * @return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public synchronized Collection<Proceso> buscarProcesosConciliaciones()
	    throws B2BException
	{
		Collection<Proceso> lcpc_datos;

		lcpc_datos = new LinkedList<Proceso>();

		try
		{
			String                       ls_catalogo;
			TipoSalidaConsultarCatalogos ltscc_consultaCatalogos;

			ls_catalogo                 = com.bachue.snr.prosnr21.common.constants.ConstanteCommon.PROCESO;
			ltscc_consultaCatalogos     = consultarCatalogos(
				    ls_catalogo, null, null, DaoManagerFactory.getDAOManagerConciliacion()
				);

			if(ltscc_consultaCatalogos != null)
			{
				CatalogoType[] lct_catalogos;

				lct_catalogos = ltscc_consultaCatalogos.getCatalogos();

				if(lct_catalogos != null)
				{
					for(CatalogoType lct_catalogo : lct_catalogos)
					{
						if(lct_catalogo != null)
						{
							String ls_codigo;
							String ls_nombreCatalogo;
							ls_codigo             = lct_catalogo.getCodigo();
							ls_nombreCatalogo     = lct_catalogo.getNombre();

							if(StringUtils.isValidString(ls_codigo) && StringUtils.isValidString(ls_nombreCatalogo))
							{
								Proceso lp_proceso;
								lp_proceso = new Proceso();
								lp_proceso.setIdProceso(ls_codigo);
								lp_proceso.setNombre(ls_nombreCatalogo);

								lcpc_datos.add(lp_proceso);
							}
						}
					}
				}
			}
			else
				throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("buscarProcesosConciliaciones", lb2be_e);

			throw lb2be_e;
		}

		return lcpc_datos;
	}

	/**
	 * Méotodo que retorna una colección con todos los subprocesos de conciliación mediante el servicio de consumo de catalogos.
	 *
	 * @param as_parametro de as parametro
	 * @return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public synchronized Collection<Subproceso> buscarSubprocesosConciliaciones(String as_parametro)
	    throws B2BException
	{
		Collection<Subproceso> lcs_return;

		lcs_return = new LinkedList<Subproceso>();

		try
		{
			String                       ls_catalogo;
			TipoSalidaConsultarCatalogos ltscc_consultaCatalogos;

			ls_catalogo                 = com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SUBPROCESO;
			ltscc_consultaCatalogos     = consultarCatalogos(
				    ls_catalogo, null, as_parametro, DaoManagerFactory.getDAOManagerConciliacion()
				);

			if(ltscc_consultaCatalogos != null)
			{
				CatalogoType[] lct_catalogos;

				lct_catalogos = ltscc_consultaCatalogos.getCatalogos();

				if(lct_catalogos != null)
				{
					for(CatalogoType lct_catalogo : lct_catalogos)
					{
						if(lct_catalogo != null)
						{
							String ls_codigo;
							String ls_nombreCatalogo;

							ls_codigo             = lct_catalogo.getCodigo();
							ls_nombreCatalogo     = lct_catalogo.getNombre();

							if(StringUtils.isValidString(ls_codigo) && StringUtils.isValidString(ls_nombreCatalogo))
							{
								Subproceso lp_proceso;
								lp_proceso = new Subproceso();

								if(StringUtils.isValidString(as_parametro))
									lp_proceso.setIdProceso(as_parametro);

								lp_proceso.setIdSubproceso(ls_codigo);
								lp_proceso.setNombre(ls_nombreCatalogo);

								lcs_return.add(lp_proceso);
							}
						}
					}
				}
			}
			else
				throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("buscarSubprocesosConciliaciones", lb2be_e);

			throw lb2be_e;
		}

		return lcs_return;
	}

	/**
	 * Méotodo que retorna una colección con todos los subprocesos de conciliación mediante el servicio de consumo de catalogos.
	 *
	 * @return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	public synchronized Collection<Usuario> buscarUsuariosRolCatalogo()
	    throws B2BException
	{
		Collection<Usuario> lcs_return;
		DAOManager   ldm_manager;
		
		lcs_return = new LinkedList<Usuario>();
		ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();
		
		try
		{
			
			TipoSalidaConsultarCatalogos 	ltscc_consultaCatalogos;
	
			
			ltscc_consultaCatalogos     = consultarCatalogos(ConstanteCommon.USUARIOS_X_ROL, null, 
					obtenerConstanteCaracter(DaoCreator.getConstantesDAO(ldm_manager), 
							ConstanteCommon.CODIGO_ROL_ANALISTA), DaoManagerFactory.getDAOManagerConciliacion()
				);
	
			if(ltscc_consultaCatalogos != null)
			{
				CatalogoType[] lct_catalogos;
	
				lct_catalogos = ltscc_consultaCatalogos.getCatalogos();
	
				if(lct_catalogos != null)
				{
					for(CatalogoType lct_catalogo : lct_catalogos)
					{
						if(lct_catalogo != null)
						{
							String ls_codigo;
							String ls_nombreCatalogo;
	
							ls_codigo             = lct_catalogo.getCodigo();
							ls_nombreCatalogo     = lct_catalogo.getNombre();
	
							if(StringUtils.isValidString(ls_codigo) && StringUtils.isValidString(ls_nombreCatalogo))
							{
								Object lo_object;
	
								lo_object = new Gson().fromJson(String.valueOf(ls_nombreCatalogo), Object.class);
	
								Usuario lp_proceso;
								lp_proceso = new Usuario();
	
								if((lo_object != null) && (lo_object instanceof Map<?, ?>))
								{
									Map<String, String> lltm_datos;
	
									lltm_datos = (LinkedTreeMap<String, String>)lo_object;
	
									if(CollectionUtils.isValidCollection(lltm_datos))
									{
										String ls_numeroDocumento;
										String ls_primerNombre;
										String ls_segundoNombre;
										String ls_primerApellido;
										String ls_segundoApellido;
										String ls_correoElectronico;
										String ls_correoElectronicoCorporativo;
	
										ls_numeroDocumento     = lltm_datos.containsKey(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.sau_NUMERO_DOCUMENTO
											)
											? lltm_datos.get(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.sau_NUMERO_DOCUMENTO
											) : null;
	
										ls_primerNombre                     = lltm_datos.containsKey(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.sau_PRIMER_NOMBRE
											)
											? lltm_datos.get(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.sau_PRIMER_NOMBRE
											) : null;
										ls_segundoNombre                    = lltm_datos.containsKey(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.sau_SEGUNDO_NOMBRE
											)
											? lltm_datos.get(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.sau_SEGUNDO_NOMBRE
											) : null;
										ls_primerApellido                   = lltm_datos.containsKey(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.sau_PRIMER_APELLIDO
											)
											? lltm_datos.get(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.sau_PRIMER_APELLIDO
											) : null;
										ls_segundoApellido                  = lltm_datos.containsKey(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.sau_SEGUNDO_APELLIDO
											)
											? lltm_datos.get(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.sau_SEGUNDO_APELLIDO
											) : null;
										ls_correoElectronico                = lltm_datos.containsKey(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.sau_CORREO_ELECTRONICO
											)
											? lltm_datos.get(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.sau_CORREO_ELECTRONICO
											) : null;
										ls_correoElectronicoCorporativo     = lltm_datos.containsKey(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.sau_CORREO_ELECTRONICO_CORPORATIVO
											)
											? lltm_datos.get(
											    com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon.sau_CORREO_ELECTRONICO
											) : null;
	
										lp_proceso.setNumeroDocumento(ls_numeroDocumento);
										lp_proceso.setPrimerNombre(ls_primerNombre);
										lp_proceso.setSegundoNombre(ls_segundoNombre);
										lp_proceso.setPrimerApellido(ls_primerApellido);
										lp_proceso.setSegundoApellido(ls_segundoApellido);
										lp_proceso.setCorreoElectronico(ls_correoElectronico);
										lp_proceso.setCorreoElectronicoCorporativo(ls_correoElectronicoCorporativo);
									}
								}
	
								lp_proceso.setIdUsuario(ls_codigo);
	
								lcs_return.add(lp_proceso);
							}
						}
					}
				}
			}
			else
				throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));
		}
		catch (B2BException lb2be_e) 
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("buscarUsuariosRolCatalogo", lb2be_e);
			throw lb2be_e;
		}
		finally 
		{
			ldm_manager.close();
		}
	
		return lcs_return;
	}
	
	/**
	 * Find entidad recaudadora cuenta by id.
	 *
	 * @param as_idCuenta the as id
	 * @return the entidad recaudadora cuenta
	 * @throws B2BException the b 2 B exception
	 */
	public EntidadRecaudadoraCuenta findEntidadRecaudadoraCuentaById(String as_idCuenta) 
		throws B2BException
	{
		DAOManager         			ldm_manager;
		EntidadRecaudadoraCuenta 	lerc_return;
		
		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lerc_return 	= null;
		
		try
		{
			lerc_return = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager).findById(as_idCuenta);
		}
		catch (B2BException lb2be_e) 
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("findEntidadRecaudadoraCuentaById", lb2be_e);
			throw lb2be_e;
		}
		finally 
		{
			ldm_manager.close();
		}
		
		return lerc_return;
	}

	/**
	 * Buscar extracto mensual.
	 *
	 * @param aem_objExtractoMensual de aem obj extracto mensual
	 * @return el valor de extracto mensual
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized ExtractoMensual buscarExtractoMensual(ExtractoMensual aem_objExtractoMensual) 
		throws B2BException 
	{
		
		ExtractoMensual 	lem_result;
		DAOManager     		ldm_manager;
		ExtractoMensualDAO 	lem_dao;
		EntidadRecaudadoraCuenta    lerc_objTemp;
		EntidadRecaudadoraCuentaDAO lerc_dao;
		
		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lem_dao 		= DaoCreator.getExtractoMensualDAO(ldm_manager);
		lerc_dao        = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager);
		lem_result 		= null;
		lerc_objTemp    = null;
		
		
		if(aem_objExtractoMensual != null) {
			
			try
			{
				lem_result = lem_dao.buscarExtractoMensual(aem_objExtractoMensual);
				
				if(lem_result == null) {
					
					lerc_objTemp     = lerc_dao.findById(aem_objExtractoMensual.getIdCuenta());
					
					if(lerc_objTemp != null)
					{
						Object[] loa_messageArgs = new String[2];
						loa_messageArgs[0]     = lerc_objTemp.getNombreEntidadRecaudadora();
						loa_messageArgs[1]     = lerc_objTemp.getNumeroCuenta();
						throw new B2BException(addErrorCMF(ErrorKeys.NO_EXISTE_INFORMACION_PARA_ESA_BUSQUEDA_BANCO_CUENTA,loa_messageArgs));
					}
				}
				else 
				{
					if(!StringUtils.isValidString(lem_result.getNumeroExtractoSiif())) 
						addErrorCMF(ErrorKeys.NO_EXISTE_INFORMACION_PARA_ESA_PERIODO_SIIF);
				}
				
			}
			catch (B2BException lb2be_e) 
			{
				
				ldm_manager.setRollbackOnly();
				clh_LOGGER.error("buscarExtractoMensual", lb2be_e);
				throw lb2be_e;
			}
			finally 
			{
				
				ldm_manager.close();
			}
		}
		
		return lem_result;
	}

	/**
	 * Método que calcula las posibles horas de ejecución de un proceso conciliacion.
	 *
	 * @param al_hora Argumento de tipo <code>Long</code> que contiene la hora inicial del proceso.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<HoraEjecucionProceso> calcularHorasProcesoConciliacion(Long al_hora)
	    throws B2BException
	{
		Collection<HoraEjecucionProceso> lchep_datos;
		DAOManager                       ldm_manager;

		lchep_datos     = new ArrayList<HoraEjecucionProceso>();
		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			if(al_hora != null)
			{
				int li_horaParametro;

				li_horaParametro = NumericUtils.getInt(al_hora);

				if((li_horaParametro >= 0) && (li_horaParametro <= 23))
				{
					for(int li_hora = li_horaParametro; li_hora <= 23; li_hora++)
					{
						for(int li_minutos = 0; li_minutos <= 45; li_minutos += 15)
						{
							HoraEjecucionProceso lhep_horaEjecucion;

							lhep_horaEjecucion = new HoraEjecucionProceso();
							lhep_horaEjecucion.setHoraProgramacion(NumericUtils.getLong(li_hora));
							lhep_horaEjecucion.setMinutoProgramacion(NumericUtils.getLong(li_minutos));

							if(li_minutos == 0)
								lhep_horaEjecucion.setLlave(li_hora + ":00");
							else
								lhep_horaEjecucion.setLlave(li_hora + ":" + li_minutos);

							lchep_datos.add(lhep_horaEjecucion);
						}
					}
				}
				else
					throw new B2BException(addErrorCMF(ErrorKeys.ERROR_HORA_FUERA_DE_RANGO));
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("calcularHorasProcesoConciliacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lchep_datos;
	}

	/**
	 * Método para consultar todos los Archivo.
	 *
	 * @return una coleccion de Rubro con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<ConArchivo> findAllArchivo()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<ConArchivo> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getConArchivoDAO(ldm_manager).findAll(true);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllArchivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para realizar transacciones para encontrar todos los registros de constantes.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Constantes> findAllConstants()
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Constantes> lcc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lcc_datos       = null;

		try
		{
			lcc_datos = DaoCreator.getConstantesDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllConstants", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcc_datos;
	}

	/**
	 * Método para consultar todos los Cuenta Analista.
	 *
	 * @return una coleccion de Cuenta Analista con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<CuentaAnalista> findAllCuentaAnalista()
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<CuentaAnalista> lcca_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lcca_datos      = null;

		try
		{
			lcca_datos = DaoCreator.getCuentaAnalistaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllCuentaAnalista", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcca_datos;
	}

	/**
	 * Método para consultar todos los Entidad Recaudadora Conciliacion.
	 *
	 * @return una coleccion de Entidad Recaudadora Conciliacion con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<EntidadRecaudadoraConciliacion> findAllEntidadRecaudadoraConciliacion()
	    throws B2BException
	{
		return findAllEntidadRecaudadoraConciliacion(false);
	}

	/**
	 * Método para consultar todos los Entidad Recaudadora Conciliacion.
	 *
	 * @param ab_activo de ab activo
	 * @return una coleccion de Entidad Recaudadora Conciliacion con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<EntidadRecaudadoraConciliacion> findAllEntidadRecaudadoraConciliacion(
	    boolean ab_activo
	)
	    throws B2BException
	{
		DAOManager                                 ldm_manager;
		Collection<EntidadRecaudadoraConciliacion> lcerc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lcerc_datos     = null;

		try
		{
			lcerc_datos = DaoCreator.getEntidadRecaudadoraConciliacionDAO(ldm_manager).findAll(ab_activo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllEntidadRecaudadoraConciliacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcerc_datos;
	}

	/**
	 * Método para consultar todos los Entidad Recaudadora Cuenta.
	 *
	 * @return una coleccion de Entidad Recaudadora Cuenta con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<EntidadRecaudadoraCuenta> findAllEntidadRecaudadoraCuenta()
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		Collection<EntidadRecaudadoraCuenta> lcerc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lcerc_datos     = null;

		try
		{
			lcerc_datos = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllEntidadRecaudadoraCuenta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcerc_datos;
	}

	/**
	 * Método para consultar todos los Entidad Recaudadora Cuenta.
	 *
	 * @param as_idEntidad correspondiente al valor de id de la entidad recaudadora
	 * @return una coleccion de Entidad Recaudadora Cuenta con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<EntidadRecaudadoraCuenta> findAllEntidadRecaudadoraCuentaByEntidadRecaudadora(
	    String as_idEntidad
	)
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		Collection<EntidadRecaudadoraCuenta> lcerc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lcerc_datos     = null;

		try
		{
			lcerc_datos = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager)
					                    .findAllPorEntidadRecaudadora(as_idEntidad);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllEntidadRecaudadoraCuentaByEntidadRecaudadora", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcerc_datos;
	}
	
	/**
	 * Método para consultar todos los Periodo Corte.
	 *
	 * @return una coleccion de Periodo Corte con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<PeriodoCorte> findAllPeriodoCorte()
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<PeriodoCorte> lcpc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lcpc_datos      = null;

		try
		{
			lcpc_datos = DaoCreator.getPeriodoCorteDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllPeriodoCorte", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcpc_datos;
	}

	/**
	 * Método para consultar todos los Rubro.
	 *
	 * @return una coleccion de Rubro con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Rubro> findAllRubro()
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Rubro> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getRubroDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllRubro", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para consultar todos los Rubro.
	 *
	 * @return una coleccion de Rubro con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Rubro> findAllRubroActivo()
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Rubro> lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getRubroDAO(ldm_manager).findAllActivo();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllRubroActivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}

	/**
	 * Método para consultar todos los Rubro Homologacion.
	 *
	 * @return una coleccion de RubroHomologacion con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<RubroHomologacion> findAllRubroHomologacion()
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<RubroHomologacion> lcrh_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lcrh_datos      = null;

		try
		{
			lcrh_datos = DaoCreator.getRubroHomologacionDAO(ldm_manager).findAll();
			
				if(CollectionUtils.isValidCollection(lcrh_datos)) {
					
					Collection<Proceso> lcpc_datos;
					lcpc_datos = buscarProcesosConciliaciones();
					
						if(CollectionUtils.isValidCollection(lcpc_datos)) {
							
							for(RubroHomologacion lirh_iterador : lcrh_datos) {
								
								boolean lb_procesoEncontrado;
								boolean lb_subprocesoEncontrado;
								Iterator<Proceso> lpi_iterador;
								Collection<Subproceso> lcspc_datos;
								Iterator<Subproceso> lspi_iterador;
								
								lb_procesoEncontrado = false;
								lb_subprocesoEncontrado = false;
								lpi_iterador = lcpc_datos.iterator();
								lcspc_datos = buscarSubprocesosConciliaciones(lirh_iterador.getIdProceso());
								lspi_iterador = lcspc_datos.iterator();
								
								
									if(lirh_iterador != null) {
										
										while(lpi_iterador.hasNext() && !lb_procesoEncontrado) {
											
											Proceso lp_iterador;
											lp_iterador= lpi_iterador.next();
										
											if(lp_iterador != null) {
												
												String ls_idProceso;
												ls_idProceso = lp_iterador.getIdProceso();
											
												if(StringUtils.isValidString(ls_idProceso) && ls_idProceso.equals(lirh_iterador.getIdProceso())) {
													lirh_iterador.setNombreProceso(lp_iterador.getNombre());
													lb_procesoEncontrado = true;
												}
											}
										}
											
										
										while(lspi_iterador.hasNext() && !lb_subprocesoEncontrado) {
											Subproceso lsp_iterador;
											lsp_iterador= lspi_iterador.next();
										
												if(lspi_iterador != null) {
													String ls_idProceso;
													String ls_idSubproceso;
													ls_idProceso = lsp_iterador.getIdProceso();
													ls_idSubproceso = lsp_iterador.getIdSubproceso();
											
														if(StringUtils.isValidString(ls_idProceso) && StringUtils.isValidString(ls_idSubproceso) && ls_idProceso.equals(lirh_iterador.getIdProceso()) && ls_idSubproceso.equals(lirh_iterador.getIdSubproceso())) {
															lirh_iterador.setNombreSubproceso(lsp_iterador.getNombre());
															lb_subprocesoEncontrado = true;
														}
													}
										}
									}
							}
						}
				}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllRubroHomologacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcrh_datos;
	}
	
	/**
	 * Find all SIIF catalogo.
	 *
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized Collection<SIIFCatalogo> findAllSIIFCatalogo()
	    throws B2BException
	{
		Collection<SIIFCatalogo> lcsc_return;
		DAOManager               ldm_manager;

		lcsc_return     = null;
		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			lcsc_return = DaoCreator.getSIIFCatalogoDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllArchivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcsc_return;
	}

	/**
	 * Retorna el valor del objeto de Archivo.
	 *
	 * @param ar_r correspondiente al valor del tipo de objeto Archivo
	 * @return devuelve el valor de Rubro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ProcesoConciliacion
	 */
	public synchronized ConArchivo findArchivoById(ConArchivo ar_r)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ConArchivo lr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lr_datos        = null;

		try
		{
			lr_datos = DaoCreator.getConArchivoDAO(ldm_manager).findById(ar_r, true);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findArchivoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_datos;
	}
	
	/**
	 * Find archivo ingresos.
	 *
	 * @param ad_paramBusqueda de ad param busqueda
	 * @return el valor de map
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Map<String, Collection<Object>> findArchivoIngresos(Date ad_paramBusqueda) 
			throws B2BException 
	{
		
		DAOManager             ldm_manager;
		Map<String, Collection<Object>> lmap_return;
		Collection<ConSiifMaestro> lcr_datosMaestro;
		Collection<ConSiifDetalle> lcr_datosDetalle;

		ldm_manager     	= DaoManagerFactory.getDAOManagerConciliacion();
		lcr_datosMaestro    = null;
		lcr_datosDetalle    = null;
		lmap_return 		= new HashMap<String, Collection<Object>>();
		
		try 
		{
			if(ad_paramBusqueda != null) 
			{
				
				lcr_datosMaestro = DaoCreator.getConSiifMaestroDAO(ldm_manager).findByDiaCorte(ad_paramBusqueda);
				
				if (!CollectionUtils.isValidCollection(lcr_datosMaestro)) 
					throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));
				
				
				Collection<Object> lcr_datosTemp;
				
				lcr_datosTemp = new ArrayList<Object>();
				
				lcr_datosTemp.addAll(lcr_datosMaestro);
				
				if(!lcr_datosTemp.isEmpty()) 
				{
					lmap_return.put(IdentificadoresCommon.MAESTRO, lcr_datosTemp);
					lcr_datosTemp.removeAll(lcr_datosTemp);
				}
				
				ConSiifMaestro conSiifMaestro = lcr_datosMaestro.iterator().next();
				
				if(NumericUtils.isValidLong(conSiifMaestro.getConsecutivoMaestro())) 
				{
					lcr_datosDetalle = DaoCreator.getConSiifDetalleDAO(ldm_manager).findByConsecutivoMaestro(conSiifMaestro);
				
					if (!CollectionUtils.isValidCollection(lcr_datosDetalle)) 
						throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));

					lcr_datosTemp.addAll(lcr_datosDetalle);
					
					if(!lcr_datosTemp.isEmpty()) 
					{
						lcr_datosTemp.addAll(lcr_datosDetalle);
						lmap_return.put(IdentificadoresCommon.DETALLE, lcr_datosTemp);
					}
				}
				
			}else 
				throw new B2BException(addErrorCMF(ErrorKeys.ERROR_FECHA_INVALIDA));
		
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.rollback();

			clh_LOGGER.error("findArchivoIngresos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
		
		return lmap_return;
	}

	/**
	 * Método para realizar transacciones con la base de datos para encontrar todos los registros
	 * de Constantes que coincidan con un id especifico.
	 *
	 * @param as_id Código de la constante a encontrar correspondiente al valor del tipo de objeto Constantes
	 * @return devuelve el valor de Constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Constantes
	 */
	public synchronized Constantes findConstantById(String as_id)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Constantes lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getConstantesDAO(ldm_manager).findById(as_id);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findConstantById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Retorna el valor del objeto de CuentaAnalista.
	 *
	 * @param aca_ca correspondiente al valor del tipo de objeto CuentaAnalista
	 * @return devuelve el valor de CuentaAnalista
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CuentaAnalista
	 */
	public synchronized CuentaAnalista findCuentaAnalistaById(CuentaAnalista aca_ca)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		CuentaAnalista lca_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lca_datos       = null;

		try
		{
			lca_datos = DaoCreator.getCuentaAnalistaDAO(ldm_manager).findById(aca_ca);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCuentaAnalistaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lca_datos;
	}

	/**
	 * Retorna el valor del objeto de EntidadRecaudadoraConciliacion.
	 *
	 * @param aerc_erc correspondiente al valor del tipo de objeto EntidadRecaudadoraConciliacion
	 * @return devuelve el valor de EntidadRecaudadoraConciliacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EntidadRecaudadoraConciliacion
	 */
	public synchronized EntidadRecaudadoraConciliacion findEntidadRecaudadoraConciliacionById(
	    EntidadRecaudadoraConciliacion aerc_erc
	)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		EntidadRecaudadoraConciliacion lerc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lerc_datos      = null;

		try
		{
			lerc_datos = DaoCreator.getEntidadRecaudadoraConciliacionDAO(ldm_manager).findById(aerc_erc);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEntidadRecaudadoraConciliacionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lerc_datos;
	}

	/**
	 * Retorna el valor del objeto de EntidadRecaudadoraConciliacion.
	 *
	 * @param aerc_erc correspondiente al valor del tipo de objeto EntidadRecaudadoraConciliacion
	 * @param adm_manager con el manager de la transacción
	 * @return devuelve el valor de EntidadRecaudadoraConciliacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EntidadRecaudadoraConciliacion
	 */
	public synchronized EntidadRecaudadoraConciliacion findEntidadRecaudadoraConciliacionById(
	    EntidadRecaudadoraConciliacion aerc_erc, DAOManager adm_manager
	)
	    throws B2BException
	{
		EntidadRecaudadoraConciliacion lerc_datos;

		lerc_datos = null;

		try
		{
			lerc_datos = DaoCreator.getEntidadRecaudadoraConciliacionDAO(adm_manager).findById(aerc_erc);
		}

		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findEntidadRecaudadoraConciliacionById", lb2be_e);

			throw lb2be_e;
		}

		return lerc_datos;
	}

	/**
	 * Retorna el valor del objeto de EntidadRecaudadoraCuenta.
	 *
	 * @param aerc_erc correspondiente al valor del tipo de objeto EntidadRecaudadoraCuenta
	 * @return devuelve el valor de EntidadRecaudadoraCuenta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EntidadRecaudadoraCuenta
	 */
	public synchronized EntidadRecaudadoraCuenta findEntidadRecaudadoraCuentaById(EntidadRecaudadoraCuenta aerc_erc)
	    throws B2BException
	{
		return findEntidadRecaudadoraCuentaById(aerc_erc, false);
	}

	/**
	 * Retorna el valor del objeto de EntidadRecaudadoraCuenta.
	 *
	 * @param aerc_erc correspondiente al valor del tipo de objeto EntidadRecaudadoraCuenta
	 * @param ab_validarCuenta de ab validar cuenta
	 * @return devuelve el valor de EntidadRecaudadoraCuenta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EntidadRecaudadoraCuenta
	 */
	public synchronized EntidadRecaudadoraCuenta findEntidadRecaudadoraCuentaById(
	    EntidadRecaudadoraCuenta aerc_erc, boolean ab_validarCuenta
	)
	    throws B2BException
	{
		DAOManager               ldm_manager;
		EntidadRecaudadoraCuenta lcr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lcr_datos       = null;

		try
		{
			lcr_datos = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager).findById(aerc_erc);

			if((lcr_datos != null) && ab_validarCuenta)
			{
				String ls_estado;
				ls_estado = lcr_datos.getActivo();

				if(StringUtils.isValidString(ls_estado) && ls_estado.equals("N"))
				{
					Object[] loa_args = {lcr_datos.getNumeroCuenta()};

					throw new B2BException(addErrorCMF(ErrorKeys.ERROR_CUENTA_INACTIVA, loa_args));
				}
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEntidadRecaudadoraCuentaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcr_datos;
	}
	
	/**
	 * Find expedientes.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Expediente> findExpedientes() throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Expediente> lce_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lce_datos       = null;

		try
		{
			lce_datos = DaoCreator.getExpedienteDAO(ldm_manager).findAllActive();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findExpedientes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lce_datos;
	}

	/**
	 * Retorna el valor del objeto de PeriodoCorte.
	 *
	 * @param apc_pc correspondiente al valor del tipo de objeto PeriodoCorte
	 * @return devuelve el valor de PeriodoCorte
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PeriodoCorte
	 */
	public synchronized PeriodoCorte findPeriodoCorteById(PeriodoCorte apc_pc)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		PeriodoCorte lpc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lpc_datos       = null;

		try
		{
			lpc_datos = DaoCreator.getPeriodoCorteDAO(ldm_manager).findById(apc_pc);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPeriodoCorteById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lpc_datos;
	}

	/**
	 * Retorna el valor del objeto de Rubro.
	 *
	 * @param ar_r correspondiente al valor del tipo de objeto Rubro
	 * @return devuelve el valor de Rubro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ProcesoConciliacion
	 */
	public synchronized Rubro findRubroById(Rubro ar_r)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Rubro      lr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lr_datos        = null;

		try
		{
			lr_datos = DaoCreator.getRubroDAO(ldm_manager).findById(ar_r);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findRubroById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_datos;
	}

	/**
	 * Retorna el valor del objeto de RubroHomologacion.
	 *
	 * @param arh_rh correspondiente al valor del tipo de objeto RubroHomologacion
	 * @return devuelve el valor de RubroHomologacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ProcesoConciliacion
	 */
	public synchronized RubroHomologacion findRubroHomologacionById(RubroHomologacion arh_rh)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		RubroHomologacion lrh_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lrh_datos       = null;

		try
		{
			lrh_datos = DaoCreator.getRubroHomologacionDAO(ldm_manager).findById(arh_rh);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findRubroHomologacionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lrh_datos;
	}

	/**
	 * Método para generar los datos de Periodo Corte.
	 *
	 * @param ap_periodoCorte de ap periodo corte
	 * @return una coleccion de Periodo Corte con todos los registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws ParseException cuando se produce algun error en el proceso
	 */
	public synchronized PeriodoCorte generarDatosPeriodoCorte(PeriodoCorte ap_periodoCorte)
	    throws B2BException, ParseException
	{
		PeriodoCorte lp_return;

		lp_return = null;

		try
		{
			if(ap_periodoCorte != null)
			{
				long ll_periodo;
				ll_periodo = ap_periodoCorte.getPeriodo();

				String ls_periodo;
				ls_periodo = StringUtils.getString(ll_periodo);

				if(ll_periodo > 0)
				{
					GregorianCalendar lgc_calendar = new GregorianCalendar();

					if(lgc_calendar.isLeapYear(NumericUtils.getInt(ll_periodo)))
						ap_periodoCorte.setCorteHasta(366);
					else
						ap_periodoCorte.setCorteHasta(365);

					SimpleDateFormat lsdf_dateFormat;
					Date             ld_dateDesde;
					Date             ld_dateHasta;
					lsdf_dateFormat     = new SimpleDateFormat("dd-MM-yyyy");
					ld_dateDesde        = lsdf_dateFormat.parse("01-01-" + ls_periodo);
					ld_dateHasta        = lsdf_dateFormat.parse("31-12-" + ls_periodo);

					ap_periodoCorte.setDiaDesde(ld_dateDesde);
					ap_periodoCorte.setDiaHasta(ld_dateHasta);

					ap_periodoCorte.setCorteDesde(1);

					lp_return = ap_periodoCorte;
				}
			}
			else
				throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_ID_CUENTA));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDatosPeriodoCorte", lb2be_e);

			throw lb2be_e;
		}

		return lp_return;
	}

	/**
	 * Obtener la propiedad caracter de una constante.
	 *
	 * @param as_idConstante Codigo de la constante a buscar
	 * @return Propiedad caracter de la constante identificada con as_idConstante
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public synchronized String obtenerCaracterConstante(String as_idConstante)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_constante;

		ldm_manager      = DaoManagerFactory.getDAOManagerConciliacion();
		ls_constante     = null;

		try
		{
			ls_constante = DaoCreator.getConstantesDAO(ldm_manager).findString(as_idConstante);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerCaracterConstante", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_constante;
	}

	/**
	 * Obtener todos los ID_CONSTANTE que coincidan con un patrón y su el valor de la columna CARACTER concida sea
	 * igual a un valor.
	 *
	 * @param as_IdLike Patrón sobre el cual se realizará búsqueda tipo like en en campo ID_CONSTANTE
	 * @param as_caracter Valor de coincidencia del campo CARACTER
	 * @return Listado de ID_CONSTANTE
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<String> obtenerIdConstanesPorCaracterIdLikeCaracter(
	    String as_IdLike, String as_caracter
	)
	    throws B2BException
	{
		Collection<String> lcs_idConstantes;
		DAOManager         ldm_manager;

		ldm_manager          = DaoManagerFactory.getDAOManagerConciliacion();
		lcs_idConstantes     = null;

		try
		{
			lcs_idConstantes = DaoCreator.getConstantesDAO(ldm_manager)
					                         .findAllIdsByIdLikeCaracter(as_IdLike, as_caracter);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerIdConstanesPorCaracterIdLikeCaracter", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcs_idConstantes;
	}

	/**
	 * Método de transacción con la base de datos para salvar en inserció o update de los datos de Archivo.
	 * @param aca_parametros con los parametros a guardar
	 * @param ab_accion con la acción de inserción o actualización
	 * @param as_usuario con el usuario de la transacción
	 * @param as_ip con la ip de la transacción
	 * @throws B2BException en caso de error
	 */
	public synchronized void salvarArchivo(
	    ConArchivo aca_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			if(aca_parametros != null)
			{
				String ls_dato;

				ls_dato = null;

				{
//					ls_dato = aca_parametros.getNombre();
					if(!StringUtils.isValidString(ls_dato))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_NOMBRE));
				}

				{
//					ls_dato = aca_parametros.getActivo();
					if(!StringUtils.isValidString(ls_dato))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_ACTIVO));
				}

				if(ab_accion)
				{
					aca_parametros.setIdUsuarioCreacion(as_usuario);
					aca_parametros.setIpCreacion(as_ip);

					DaoCreator.getConArchivoDAO(ldm_manager).insert(aca_parametros);
				}
				else
				{
					aca_parametros.setIdUsuarioModificacion(as_usuario);
					aca_parametros.setIpModificacion(as_ip);

					DaoCreator.getConArchivoDAO(ldm_manager).update(aca_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarArchivo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}
	
	/**
	 * Find tipo documental by expediente.
	 *
	 * @param as_param de as param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TipoDocumental> findTipoDocumentalByExpediente(String as_param)
		throws B2BException
	{
		DAOManager ldm_manager;
		Collection<TipoDocumental> lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getConTipoDocumentalDAO(ldm_manager).findByExpediente(as_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoDocumentalByExpediente", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos con el fin de encontrar todas las
	 * imagenes con un id_constante especifico.
	 *
	 * @param ac_parametros representa el objeto de tipo constantes para encontrar su imagen
	 * @return devuelve el valor de Constantes
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Constantes
	 */
	public synchronized Constantes findImageById(Constantes ac_parametros)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Constantes lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getConstantesDAO(ldm_manager).findImagen(ac_parametros);

			if(lc_datos == null)
				throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));

			if(lc_datos.getImagenes() == null)
				throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARO_IMAGEN));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findImageById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Método para realizar transacciones con la base de datos para salvar todos los registros
	 * de Constantes ya sean modificaciones o inserciones.
	 *
	 * @param ac_parametros Representa el objeto el cual se va a insertar o modificar
	 * @param ab_accion indica si se va a insertar o modificar en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void salvarConstantes(Constantes ac_parametros, boolean ab_accion)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		ConstantesDAO lcd_constantesDao;
		
		ldm_manager           = DaoManagerFactory.getDAOManagerConciliacion();
		lcd_constantesDao     = DaoCreator.getConstantesDAO(ldm_manager);

		try
		{
			if(ac_parametros != null)
			{
				if(ab_accion)
				{
					Constantes lc_constante_temp;
					lc_constante_temp = lcd_constantesDao.findById(ac_parametros);

					if(lc_constante_temp != null)
					{
						Object[] loa_messageArgs = new String[1];

						loa_messageArgs[0] = lc_constante_temp.getIdConstante();

						throw new B2BException(addErrorCMF(ErrorKeys.CONSTANTE_EXISTENTE, loa_messageArgs));
					}
				}

				{

					String ls_idConstante;
					String ls_transferenciaRecursos;
					String ls_ingresosSnr;
					Constantes ls_comparador;
					
					ls_idConstante = ac_parametros.getIdConstante();
					ls_transferenciaRecursos = ConstanteCommon.LEY_INGRESOS_SNR;
					ls_ingresosSnr = ConstanteCommon.LEY__TRASFERENCIA_DE_RECURSOS;
					ls_comparador = new Constantes();
					
					if(!StringUtils.isValidString(ls_idConstante))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_ID_CONSTANTE));
					
					if(ls_idConstante.equalsIgnoreCase((ls_ingresosSnr))) {
						ls_comparador = lcd_constantesDao.findById(ls_transferenciaRecursos);
						if(ls_comparador != null) {
						if((NumericUtils.getDouble(ac_parametros.getReal()) + 
								(NumericUtils.getDouble(ls_comparador.getReal())) > 1.0)) {
							throw new B2BException(addErrorCMF(ErrorKeys.DEBE_SER_MENOR_QUE_UNO));
							}
						}
					}
					else if(ls_idConstante.equalsIgnoreCase(ls_transferenciaRecursos)) {
						ls_comparador = lcd_constantesDao.findById(ls_ingresosSnr);
						if(ls_comparador != null) {
						if((NumericUtils.getDouble(ac_parametros.getReal()) + 
								(NumericUtils.getDouble(ls_comparador.getReal())) > 1.0)) {
							throw new B2BException(addErrorCMF(ErrorKeys.DEBE_SER_MENOR_QUE_UNO));
							}
						}
					}
				
				}

				lcd_constantesDao.insertOrUpdate(ac_parametros, ab_accion);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarConstantes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacción con la base de datos para salvar en inserció o update de los datos de Cuenta Analista.
	 * @param aca_parametros con los parametros a guardar
	 * @param ab_accion con la acción de inserción o actualización
	 * @param as_usuario con el usuario de la transacción
	 * @param as_ip con la ip de la transacción
	 * @throws B2BException en caso de error
	 */
	public synchronized void salvarCuentaAnalista(
	    CuentaAnalista aca_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			if(aca_parametros != null)
			{
				String        ls_idCuenta;
				String        ls_idUsuario;
				String        ls_activo;
				String 		  ls_correo;
				ConstantesDAO lcd_dao;

				ls_idCuenta      = aca_parametros.getIdCuenta();
				ls_idUsuario     = aca_parametros.getIdUsuario();
				ls_activo        = aca_parametros.getActivo();
				ls_correo 		 = aca_parametros.getCorreoElectronicoAnalista();
				lcd_dao          = DaoCreator.getConstantesDAO(ldm_manager);

				if(
				    !StringUtils.isValidString(ls_idCuenta) && !StringUtils.isValidString(ls_idUsuario)
					    && !StringUtils.isValidString(ls_activo) && !StringUtils.isValidString(ls_correo)
				)
					throw new B2BException(addErrorCMF(ErrorKeys.ERROR_CAMPOS_OBLIGATORIOS));

				if(!StringUtils.isValidString(ls_idCuenta))
					throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_ID_CUENTA));

				if(!StringUtils.isValidString(ls_idUsuario))
					throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_ID_USUARIO));

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_ACTIVO));
				
				if(!StringUtils.isValidString(ls_correo))
					throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO));

				Date ld_fecha;
				ld_fecha = aca_parametros.getFechaSaldoInicial();

				if((ld_fecha != null) && ld_fecha.after(new Date()))
					throw new B2BException(addErrorCMF(ErrorKeys.ERROR_FECHA_MAYOR_A_ACTUAL));

				if(ld_fecha != null)
				{
					Constantes lc_constante;
					lc_constante = lcd_dao.findById(ConstanteCommon.DIAS_SALDO_INICIAL);

					if(lc_constante != null)
					{
						int li_dias;
						li_dias = NumericUtils.getInt(lc_constante.getEntero()) + 1;

						if(li_dias > 0)
						{
							Date ld_comparacion;
							ld_comparacion = sumarDías(new Date(), -li_dias);

							if(ld_comparacion != null)
							{
								if(ld_fecha.before(ld_comparacion))
									throw new B2BException(addErrorCMF(ErrorKeys.ERROR_FECHA_MENOR_A_LA_PERMITIDA));
							}
						}
					}
				}

				if(ab_accion)
				{
					aca_parametros.setIdUsuarioCreacion(as_usuario);
					aca_parametros.setIpCreacion(as_ip);

					DaoCreator.getCuentaAnalistaDAO(ldm_manager).insert(aca_parametros);
				}
				else
				{
					aca_parametros.setIdUsuarioModificacion(as_usuario);
					aca_parametros.setIpModificacion(as_ip);

					DaoCreator.getCuentaAnalistaDAO(ldm_manager).update(aca_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarCuentaAnalista", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}
	
	/**
	 * Salvar SIIF catalogo.
	 *
	 * @param asc_parametros the asc parametros
	 * @param ab_accion the ab accion
	 * @param as_usuario the as usuario
	 * @param as_ip the as ip
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized void salvarSIIFCatalogo(
	    SIIFCatalogo asc_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			if(asc_parametros != null)
			{
				String          ls_nemotecnico;
				String          ls_nombre;
				String          ls_codigo;
				String          ls_descripcion;
				String          ls_archivo;
				String          ls_activo;
				SIIFCatalogoDAO lscd_DAO;

				ls_nemotecnico     = asc_parametros.getNemotecnico();
				ls_nombre          = asc_parametros.getNombre();
				ls_codigo          = asc_parametros.getCodigo();
				ls_descripcion     = asc_parametros.getDescripcion();
				ls_archivo         = asc_parametros.getArchivo();
				ls_activo          = asc_parametros.getActivo();
				lscd_DAO           = DaoCreator.getSIIFCatalogoDAO(ldm_manager);

				if(
				    !StringUtils.isValidString(ls_activo) && !StringUtils.isValidString(ls_nemotecnico)
					    && !StringUtils.isValidString(ls_nombre) && !StringUtils.isValidString(ls_codigo)
					    && !StringUtils.isValidString(ls_descripcion) && !StringUtils.isValidString(ls_archivo)
				)
					throw new B2BException(addErrorCMF(ErrorKeys.ERROR_CAMPOS_OBLIGATORIOS));

				if(!StringUtils.isValidString(ls_nemotecnico))
					throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_NEMOTECNICO));

				if(!StringUtils.isValidString(ls_nombre))
					throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_NOMBRE));

				if(!StringUtils.isValidString(ls_codigo))
					throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_CODIGO));

				if(!StringUtils.isValidString(ls_descripcion))
					throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_DESCRIPCION));

				if(!StringUtils.isValidString(ls_archivo))
					throw new B2BException(addErrorCMF(ErrorKeys.DEBE_ELEGIR_ARCHIVO));

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_ACTIVO));

				if(ab_accion)
				{
					asc_parametros.setIdUsuarioCreacion(as_usuario);
					asc_parametros.setIpCreacion(as_ip);

					lscd_DAO.insert(asc_parametros);
				}
				else
				{
					asc_parametros.setIdUsuarioModificacion(as_usuario);
					asc_parametros.setIpModificacion(as_ip);

					lscd_DAO.update(asc_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarSIIFCatalogo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}
	
	/**
	 * Método de suma de días a una fecha.
	 *
	 * @param ld_fecha con la fecha a la cual se le sumarán días
	 * @param ld_días con la cantidad de días a sumar
	 * @return de tipo date con el valor de la fecha modificada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Date sumarDías(Date ld_fecha, int ld_días) 
	   throws B2BException
	{
		Date ld_return;
		ld_return = null;
		
		try 
		{
			if (ld_fecha != null) {
				Calendar lc_calendar;
				lc_calendar = Calendar.getInstance();
				lc_calendar.setTime(ld_fecha);
				lc_calendar.add(Calendar.DAY_OF_YEAR, ld_días);
				ld_return = lc_calendar.getTime();
			}
			else
				throw new B2BException(addErrorCMF(ErrorKeys.ERROR_FECHA_INVALIDA));
		} 
		catch (B2BException lb2be_e) 
		{
			clh_LOGGER.error("sumarDías", lb2be_e);
			throw lb2be_e;
		}
		return ld_return; 
	} 

	/**
	 * Método de transacción con la base de datos para salvar en inserció o update de los datos de Cuenta Analista.
	 * @param aca_parametros con los parametros a guardar
	 * @param ab_accion con la acción de inserción o actualización
	 * @param as_usuario con el usuario de la transacción
	 * @param as_ip con la ip de la transacción
	 * @throws B2BException en caso de error
	 */
	public synchronized void salvarEntidadRecaudadoraConciliacion(
	    EntidadRecaudadoraConciliacion aca_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			if(aca_parametros != null)
			{
				String ls_idEntidad;
				String ls_nombreEntidad;
				String ls_codigoEntidad;
				String ls_activo;
				ls_idEntidad         = aca_parametros.getIdEntidadRecaudadora();
				ls_nombreEntidad     = aca_parametros.getNombreEntidadRecaudadora();
				ls_codigoEntidad     = aca_parametros.getCodigoEntidadRecaudadora();
				ls_activo            = aca_parametros.getActivo();

				if(
				    !StringUtils.isValidString(ls_idEntidad) && !StringUtils.isValidString(ls_nombreEntidad)
					    && !StringUtils.isValidString(ls_activo) && !StringUtils.isValidString(ls_codigoEntidad)
				)
					throw new B2BException(addErrorCMF(ErrorKeys.ERROR_CAMPOS_OBLIGATORIOS));

				{
					if(!StringUtils.isValidString(ls_idEntidad))
						throw new B2BException(addErrorCMF(ErrorKeys.ERROR_ID_ENTIDAD_RECAUDADORA_INVALIDA));
				}

				{
					if(!StringUtils.isValidString(ls_nombreEntidad))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_INGRESAR_NOMBRE_ENTIDAD_RECAUDADORA));
				}

				{
					if(!StringUtils.isValidString(ls_codigoEntidad))
						throw new B2BException(addErrorCMF(ErrorKeys.ERROR_CODIGO_ENTIDAD_RECAUDADORA_INVALIDA));
				}

				{
					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_ACTIVO));
				}

				if(ab_accion)
				{
					EntidadRecaudadoraConciliacion lerc_entidadDeVerificacion;
					lerc_entidadDeVerificacion = findEntidadRecaudadoraConciliacionById(aca_parametros, ldm_manager);

					if(lerc_entidadDeVerificacion != null)
						throw new B2BException(addErrorCMF(ErrorKeys.ERROR_CODIGO_ENTIDAD_RECAUDADORA_REPETIDO));

					aca_parametros.setIdUsuarioCreacion(as_usuario);

					aca_parametros.setIpCreacion(as_ip);

					DaoCreator.getEntidadRecaudadoraConciliacionDAO(ldm_manager).insert(aca_parametros);
				}
				else
				{
					aca_parametros.setIdUsuarioModificacion(as_usuario);
					aca_parametros.setIpModificacion(as_ip);

					DaoCreator.getEntidadRecaudadoraConciliacionDAO(ldm_manager).update(aca_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarEntidadRecaudadoraConciliacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacción con la base de datos para salvar en inserció o update de los datos de Cuenta Analista.
	 * @param aca_parametros con los parametros a guardar
	 * @param ab_accion con la acción de inserción o actualización
	 * @param as_usuario con el usuario de la transacción
	 * @param as_ip con la ip de la transacción
	 * @throws B2BException en caso de error
	 */
	public synchronized void salvarEntidadRecaudadoraCuenta(
	    EntidadRecaudadoraCuenta aca_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		
		ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			if(aca_parametros != null)
			{
				String ls_idEntidad;
				String ls_numeroCuenta;
				String ls_tipoCuenta;
				String ls_tipoArchivo;
				String ls_nombreContacto;
				String ls_correoElectronico;
				String ls_numeroContacto;
				String ls_activo;

				ls_idEntidad             = aca_parametros.getIdEntidadRecaudadora();
				ls_numeroCuenta          = aca_parametros.getNumeroCuenta();
				ls_tipoCuenta            = aca_parametros.getTipoCuenta();
				ls_tipoArchivo           = aca_parametros.getTipoArchivo();
				ls_nombreContacto        = aca_parametros.getNombreContacto();
				ls_correoElectronico     = aca_parametros.getCorreoElectronicoContacto();
				ls_numeroContacto        = aca_parametros.getNumeroCelContacto();
				ls_activo                = aca_parametros.getActivo();
				
				EntidadRecaudadoraCuentaDAO lerc_DAO;
				lerc_DAO = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager);

				if(
				    !StringUtils.isValidString(ls_idEntidad) && !StringUtils.isValidString(ls_numeroCuenta)
					    && !StringUtils.isValidString(ls_tipoArchivo) && !StringUtils.isValidString(ls_tipoCuenta)
					    && !StringUtils.isValidString(ls_nombreContacto)
					    && !StringUtils.isValidString(ls_correoElectronico)
					    && !StringUtils.isValidString(ls_numeroContacto) && !StringUtils.isValidString(ls_activo)
				)
					throw new B2BException(addErrorCMF(ErrorKeys.ERROR_CAMPOS_OBLIGATORIOS));

				{
					if(!StringUtils.isValidString(ls_idEntidad))
						throw new B2BException(addErrorCMF(ErrorKeys.ERROR_CODIGO_ENTIDAD_RECAUDADORA_INVALIDA));
				}

				{
					if(!StringUtils.isValidString(ls_numeroCuenta))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_INGRESAR_NUMERO_CUENTA));
				}

				{
					if(!StringUtils.isValidString(ls_tipoCuenta))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_INGRESAR_TIPO_CUENTA));
				}

				{
					if(!StringUtils.isValidString(ls_tipoArchivo))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_TIPO_ARCHIVO));
				}

				{
					if(!StringUtils.isValidString(ls_nombreContacto))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_NOMBRE_CONTACTO));
				}

				{
					if(!StringUtils.isValidString(ls_correoElectronico))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO));

					if(!validarCorreoElectronico(ls_correoElectronico))
						throw new B2BException(addErrorCMF(ErrorKeys.ERROR_CORREO_FORMATO_NO_VALIDO));
				}

				{
					if(!StringUtils.isValidString(ls_numeroContacto))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_NUMERO_CEL_VALIDO));
				}

				{
					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_ACTIVO));
				}

				if(ab_accion)
				{
					aca_parametros.setIdUsuarioCreacion(as_usuario);
					aca_parametros.setIpCreacion(as_ip);
					
					EntidadRecaudadoraCuenta lc_entidadRecaudadoraValidacioon;
					
					lc_entidadRecaudadoraValidacioon = lerc_DAO.findByIdEntidadCuentaTipo(ls_idEntidad, ls_numeroCuenta, ls_tipoArchivo);
					
					if(lc_entidadRecaudadoraValidacioon != null) {
						
						Object[] loa_messageArgs = new String[1];

						loa_messageArgs[0] =ls_numeroCuenta;

						throw new B2BException(addErrorCMF(ErrorKeys.ERROR_ENTIDAD_CUENTA_EXISTENTE, loa_messageArgs));
					}
					
					aca_parametros = lerc_DAO.insert(aca_parametros);
				}
				else
				{
					aca_parametros.setIdUsuarioModificacion(as_usuario);
					aca_parametros.setIpModificacion(as_ip);

					lerc_DAO.update(aca_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarEntidadRecaudadoraCuenta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacción con la base de datos para salvar en inserció o update de los datos de PeriodoCorte.
	 *
	 * @param aca_parametros con los parametros a guardar
	 * @param ab_accion con la acción de inserción o actualización
	 * @param as_usuario con el usuario de la transacción
	 * @param as_ip con la ip de la transacción
	 * @throws B2BException en caso de error
	 * @throws ParseException cuando se produce algun error en el proceso
	 */
	public synchronized void salvarPeriodoCorte(
	    PeriodoCorte aca_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException, ParseException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

		PeriodoCorteDAO lpcdao_DAO;
		lpcdao_DAO = DaoCreator.getPeriodoCorteDAO(ldm_manager);

		try
		{
			if(aca_parametros != null)
			{
				String ls_dato;
				long   ll_dato;
				ls_dato     = aca_parametros.getActivo();
				ll_dato     = aca_parametros.getPeriodo();

				if(!StringUtils.isValidString(ls_dato) && (ll_dato <= 0))
					throw new B2BException(addErrorCMF(ErrorKeys.ERROR_CAMPOS_OBLIGATORIOS));

				{
					if(!StringUtils.isValidString(ls_dato))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_ACTIVO));
				}

				{
					if(ll_dato <= 0)
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_PERIODO));
				}

				if(ab_accion)
				{
					int    li_corteHasta;
					long   ll_periodo;
					String ls_periodo;

					Calendar         lc_calendar;
					SimpleDateFormat lsdf_dateFormat;
					Date             ld_dateDesde;

					li_corteHasta     = NumericUtils.getInt(aca_parametros.getCorteHasta());
					ll_periodo        = aca_parametros.getPeriodo();
					ls_periodo        = StringUtils.getString(ll_periodo);

					Collection<PeriodoCorte> lcpc_periodoCorte;
					lcpc_periodoCorte = lpcdao_DAO.findByPeriodo(ls_periodo);

					if(!CollectionUtils.isValidCollection(lcpc_periodoCorte))
					{
						lc_calendar         = Calendar.getInstance();
						lsdf_dateFormat     = new SimpleDateFormat("dd-mm-yyyy");
						ld_dateDesde        = lsdf_dateFormat.parse("01-01-" + ls_periodo);

						aca_parametros.setIdUsuarioCreacion(as_usuario);
						aca_parametros.setIpCreacion(as_ip);

						for(int li_iterador = 1; li_iterador <= li_corteHasta; li_iterador++)
						{
							lc_calendar.setTime(ld_dateDesde);

							if(li_iterador == 1)
								lc_calendar.add(Calendar.DAY_OF_YEAR, 0);
							else
								lc_calendar.add(Calendar.DAY_OF_YEAR, 1);

							ld_dateDesde = lc_calendar.getTime();
							aca_parametros.setDiaCorte(ld_dateDesde);
							aca_parametros.setCorte(NumericUtils.getLong(li_iterador));

							lpcdao_DAO.insert(aca_parametros);
						}
					}
					else
						throw new B2BException(addErrorCMF(ErrorKeys.ERROR_PERIODO_EXISTENTE));
				}
				else
				{
					aca_parametros.setIdUsuarioModificacion(as_usuario);
					aca_parametros.setIpModificacion(as_ip);

					lpcdao_DAO.update(aca_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarPeriodoCorte", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar proceso conciliacion.
	 *
	 * @param apc_parametros Argumento de tipo <code>ProcesoConciliacion</code> que contiene el proceso conciliación.
	 * @param ab_insertar Argumento de tipo <code>boolean</code> que indica la acción a ejecutar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de proceso conciliacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized ProcesoConciliacion salvarProcesoConciliacion(
	    ProcesoConciliacion apc_parametros, boolean ab_insertar, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			if(apc_parametros != null)
			{
				Long   ll_horaProgramacion;
				String ls_entidadRecaudadora;
				Long   ll_minutoProgramacion;
				String ls_activo;

				ll_horaProgramacion       = NumericUtils.getLongWrapper(apc_parametros.getHoraProgramacion());
				ls_entidadRecaudadora     = apc_parametros.getIdEntidadRecaudadora();
				ll_minutoProgramacion     = NumericUtils.getLongWrapper(apc_parametros.getMinutoProgramacion());
				ls_activo                 = apc_parametros.getActivo();

				if(
				    !StringUtils.isValidString(ls_entidadRecaudadora)
					    && (NumericUtils.getLong(ll_horaProgramacion) <= 0)
					    && (NumericUtils.getLong(ll_minutoProgramacion) <= 0) && !StringUtils.isValidString(ls_activo)
				)
					throw new B2BException(addErrorCMF(ErrorKeys.ERROR_CAMPOS_OBLIGATORIOS));

				{
					if(!StringUtils.isValidString(ls_entidadRecaudadora))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_SELECCIONAR_ENTIDAD_RECAUDADORA));
				}

				{
					if(!NumericUtils.isValidLong(ll_horaProgramacion))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_SELECCIONAR_HORA_EJECUCION));
				}

				{
					if(!NumericUtils.isValidLong(ll_minutoProgramacion))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_SELECCIONAR_MINUTO_EJECUCION));
				}

				{
					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_ACTIVO));
				}

				if(ab_insertar)
				{
					Collection<ProcesoConciliacion> lcpc_procesos;
					lcpc_procesos = buscarProcesosConciliacion(ldm_manager);
				
					if (CollectionUtils.isValidCollection(lcpc_procesos)) {
						for(ProcesoConciliacion lpc_proceso : lcpc_procesos)
						{
							if(lpc_proceso.getIdEntidadRecaudadora().equals(ls_entidadRecaudadora))
								throw new B2BException(
										addErrorCMF(ErrorKeys.YA_EXISTE_PROCESO_CONCILIACION_ENTIDAD_RECAUDADORA)
										);
						}
					}
				}

				if(ab_insertar)
				{
					apc_parametros.setIdUsuarioCreacion(as_userId);
					apc_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					apc_parametros.setIdUsuarioModificacion(as_userId);
					apc_parametros.setIpModificacion(as_remoteIp);
				}

				apc_parametros = DaoCreator.getProcesoConciliacionDAO(ldm_manager)
						                       .insertOrUpdate(apc_parametros, ab_insertar);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarProcesoConciliacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return apc_parametros;
	}

	/**
	 * Método de transacción con la base de datos para salvar en inserció o update de los datos de Rubro.
	 * @param aca_parametros con los parametros a guardar
	 * @param ab_accion con la acción de inserción o actualización
	 * @param as_usuario con el usuario de la transacción
	 * @param as_ip con la ip de la transacción
	 * @throws B2BException en caso de error
	 */
	public synchronized void salvarRubro(Rubro aca_parametros, boolean ab_accion, String as_usuario, String as_ip)
	    throws B2BException
	{
		DAOManager ldm_manager;
		
		ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			if(aca_parametros != null)
			{
				String ls_nombre;
				String ls_activo;
				String ls_numeroRubro;

				ls_nombre     = aca_parametros.getNombre();

				ls_activo = aca_parametros.getActivo();
				
				ls_numeroRubro= aca_parametros.getNumeroRubro();

				if(!StringUtils.isValidString(ls_nombre) && !StringUtils.isValidString(ls_activo) && !StringUtils.isValidString(ls_numeroRubro))
					throw new B2BException(addErrorCMF(ErrorKeys.ERROR_CAMPOS_OBLIGATORIOS));

				{
					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_NOMBRE));
				}

				{
					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_ACTIVO));
				}
				{
					if(!StringUtils.isValidString(ls_numeroRubro)) 
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_NUMERO_RUBRO));
				}

				if(ab_accion)
				{
					aca_parametros.setIdUsuarioCreacion(as_usuario);
					aca_parametros.setIpCreacion(as_ip);
					
					Rubro lp_rubro;
					
					lp_rubro = DaoCreator.getRubroDAO(ldm_manager).findByNombre(ls_nombre);
					
					
					if(lp_rubro != null) 
					{
						Object[] loa_messageArgs = new String[1];

						loa_messageArgs[0] =ls_nombre;

						throw new B2BException(addErrorCMF(ErrorKeys.ERROR_RUBRO_EXISTENTE, loa_messageArgs));
					}

					DaoCreator.getRubroDAO(ldm_manager).insert(aca_parametros);
				}
				else
				{
					aca_parametros.setIdUsuarioModificacion(as_usuario);
					aca_parametros.setIpModificacion(as_ip);

					DaoCreator.getRubroDAO(ldm_manager).update(aca_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarRubro", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacción con la base de datos para salvar en inserció o update de los datos de RubroHomologacion.
	 * @param aca_parametros con los parametros a guardar
	 * @param ab_accion con la acción de inserción o actualización
	 * @param as_usuario con el usuario de la transacción
	 * @param as_ip con la ip de la transacción
	 * @throws B2BException en caso de error
	 */
	public synchronized void salvarRubroHomologacion(
	    RubroHomologacion aca_parametros, boolean ab_accion, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		
		ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			if(aca_parametros != null)
			{
				String ls_idRubro;
				String ls_idProceso;
				String ls_idSubproceso;
				String ls_activo;
				String ls_tipoOperacion;
				String ls_otrosConceptos;
				String ls_observaciones;

				ls_idRubro           = aca_parametros.getIdRubro();
				ls_idProceso         = aca_parametros.getIdProceso();
				ls_idSubproceso      = aca_parametros.getIdSubproceso();
				ls_activo            = aca_parametros.getActivo();
				ls_tipoOperacion     = aca_parametros.getTipoOperacion();
				ls_otrosConceptos = aca_parametros.getOtrosConceptos();
				ls_observaciones = aca_parametros.getObservacion();

				if(
				    !StringUtils.isValidString(ls_idRubro) && !StringUtils.isValidString(ls_idProceso)
					    && !StringUtils.isValidString(ls_idSubproceso) && !StringUtils.isValidString(ls_activo)
					    && !StringUtils.isValidString(ls_tipoOperacion)
				)
					throw new B2BException(addErrorCMF(ErrorKeys.ERROR_CAMPOS_OBLIGATORIOS));

					if(!StringUtils.isValidString(ls_idRubro))
						throw new B2BException(addErrorCMF(ErrorKeys.ERROR_DEBE_ELEGIR_RUBRO));
					
					if(!StringUtils.isValidString(ls_otrosConceptos)){
						
					if(!StringUtils.isValidString(ls_idProceso))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_SELECCIONAR_PROCESO));

					if(!StringUtils.isValidString(ls_idSubproceso))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_SELECCIONAR_SUBPROCESO));
					}

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_ACTIVO));
					
					if(!StringUtils.isValidString(ls_tipoOperacion))
						throw new B2BException(addErrorCMF(ErrorKeys.ERROR_DEBE_ELEGIR_SIGNO));
					
					if(StringUtils.isValidString(ls_otrosConceptos) && !StringUtils.isValidString(ls_observaciones))
						throw new B2BException(addErrorCMF(ErrorKeys.ERROR_OBSERVACIONES_CONCEPTOS));

				if(ab_accion)
				{
					aca_parametros.setIdUsuarioCreacion(as_usuario);
					aca_parametros.setIpCreacion(as_ip);

					DaoCreator.getRubroHomologacionDAO(ldm_manager).insert(aca_parametros);
				}
				else
				{
					aca_parametros.setIdUsuarioModificacion(as_usuario);
					aca_parametros.setIpModificacion(as_ip);

					DaoCreator.getRubroHomologacionDAO(ldm_manager).update(aca_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarRubroHomologacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Update caracter.
	 *
	 * @param as_id de as id
	 * @param as_caracter de as caracter
	 * @param as_userId de as user id
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void updateCaracter(String as_id, String as_caracter, String as_userId)
	    throws B2BException
	{
		DAOManager ldm_dm;

		ldm_dm = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			DaoCreator.getConstantesDAO(ldm_dm).updateString(as_id, as_caracter, as_userId);
		}
		catch(B2BException lb2be_e)
		{
			ldm_dm.setRollbackOnly();

			clh_LOGGER.error("updateCaracter", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_dm.close();
		}
	}

	/**
	 * Méotodo que retorna una colección con todos los procesos de conciliación.
	 *
	 * @param adm_manager Contexto transaccional donde se ejecuta el método
	 * @return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción.
	 */
	private synchronized Collection<ProcesoConciliacion> buscarProcesosConciliacion(DAOManager adm_manager)
	    throws B2BException
	{
		Collection<ProcesoConciliacion> lcpc_datos;

		lcpc_datos     = null;

		lcpc_datos = DaoCreator.getProcesoConciliacionDAO(adm_manager).buscarConEntidadRecaudadora();

		return lcpc_datos;
	}

	/**
	 * Find SIIF catalogo by id.
	 *
	 * @param asc_data the asc data
	 * @return the SIIF catalogo
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized SIIFCatalogo findSIIFCatalogoById(SIIFCatalogo asc_data) 
		throws B2BException 
	{
		DAOManager ldm_manager;
		SIIFCatalogo lr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lr_datos        = null;

		try
		{
			if(asc_data != null)
				lr_datos = DaoCreator.getSIIFCatalogoDAO(ldm_manager).findById(asc_data.getIdSiifCatalogo());
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSIIFCatalogoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_datos;
	}

	/**
	 * Find caracteres constante by id.
	 *
	 * @param as_idContante the as id contante
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized Collection<String> findCaracteresConstanteById(String as_idContante)
	    throws B2BException
	{
		Collection<String> lcs_return;
		DAOManager         ldm_manager;

		lcs_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			if(StringUtils.isValidString(as_idContante))
			{
				Constantes lc_data;

				lc_data = DaoCreator.getConstantesDAO(ldm_manager).findById(as_idContante);

				if(lc_data != null)
					lcs_return = new ArrayList<String>(
						    ListadoCodigosConstantes.generarCodigos(lc_data.getCaracter()).values()
						);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCaracteresConstanteById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcs_return;
	}
	
	/**
	 * Find alertas confrontacion ingresos.
	 *
	 * @param as_alerta de as alerta
	 * @param ad_fecha de ad fecha
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<ConInconsistencia> findAlertasConfrontacionIngresos(String as_alerta, Date ad_fecha)
	    throws B2BException
	{
		Collection<ConInconsistencia> 			   lcci_datos;
		DAOManager                                 ldm_manager;

		lcci_datos      = null;
		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			lcci_datos = DaoCreator.getConInconsistenciaDAO(ldm_manager).findByTipo(as_alerta, ad_fecha);

			if(!CollectionUtils.isValidCollection(lcci_datos))
				throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));
			
			lcci_datos = fillTagsObservaciones(lcci_datos);
			
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAlertasConfrontacionIngresos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcci_datos;
	}

	/**
	 * Find avisos conciliacion ingresos.
	 *
	 * @param as_idCuenta de as id cuenta
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<ConInconsistencia> findAvisosConciliacionIngresos(String as_idCuenta)
		throws B2BException
	{
		Collection<ConInconsistencia> 			   lcci_datos;
		DAOManager                                 ldm_manager;

		lcci_datos      = null;
		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			lcci_datos = DaoCreator.getConInconsistenciaDAO(ldm_manager).findByCuenta(as_idCuenta);
			
			if(!CollectionUtils.isValidCollection(lcci_datos))
				throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));
			
			lcci_datos = fillTagsObservaciones(lcci_datos);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAvisosConciliacionIngresos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcci_datos;
	}
	
	/**
	 * Fill tags observaciones.
	 *
	 * @param aci_inconsistencia the aci inconsistencia
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized ConInconsistencia fillTagsObservaciones(ConInconsistencia aci_data, DAOManager adm_manager)
	    throws B2BException
	{
		try
		{
			if(aci_data != null)
			{
				String ls_idInconsistencia;
				ls_idInconsistencia = aci_data.getIdInconsistencia();
				
				if(StringUtils.isValidString(ls_idInconsistencia) && StringUtils.isValidString(aci_data.getIdPeriodoCorte()) 
						&& StringUtils.isValidString(aci_data.getIdCuenta())) 
				{
					String              ls_obsTemp;
					String              ls_mensajeTemp;
					ConInconsistencia 	lci_temp;
					
					lci_temp = DaoCreator.getConInconsistenciaDAO(adm_manager).findBancoCuentaById(ls_idInconsistencia);
					
					if(lci_temp == null)
						throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));
					
					SimpleDateFormat lsdf_formatCorte;
					SimpleDateFormat lsdf_formatFecha;
					Date             ld_date;
					String           ls_dateStr;
					String ls_corte;
					
					lsdf_formatFecha     = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);    //"dd/MM/yyyy"
					lsdf_formatCorte     = new SimpleDateFormat(FormatoFechaCommon.ANIO_MES_DIA_HORA_MINUTO_SEGUNDO_OWCC);    //"yyyy-MM-dd hh:mm:ss"
					ld_date              = null;
					ls_dateStr           = null;
					ls_corte 			 = null;
					
					ls_obsTemp     = aci_data.getObservaciones();
					ls_obsTemp     = ls_obsTemp.replace("<TAG_BANCO>", lci_temp.getBanco());
					ls_obsTemp     = ls_obsTemp.replace("<TAG_CUENTA>", lci_temp.getCuenta());
					ls_obsTemp     = ls_obsTemp.replace("<TAG_PERIODO>", lci_temp.getPeriodo());
					
					try
					{
						ls_corte 	= lci_temp.getCorte();
						ld_date 	= lsdf_formatCorte.parse(ls_corte);
					}
					catch(ParseException e)
					{
						throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));
					}
					
					ls_dateStr     	   = lsdf_formatFecha.format(ld_date);
					ls_obsTemp     	   = ls_obsTemp.replace("<TAG_CORTE>", ls_dateStr);
					
					if(!StringUtils.isValidString(ls_corte))
					{
						ld_date            = lci_temp.getFechaCreacion();
						ls_dateStr         = lsdf_formatFecha.format(ld_date);
					}
					
					ls_obsTemp         = ls_obsTemp.replace("<TAG_FECHA>", ls_dateStr);
					ls_mensajeTemp     = aci_data.getMensaje();
					ls_mensajeTemp     = ls_mensajeTemp.replace("<TAG_BANCO>", lci_temp.getBanco());
					ls_mensajeTemp     = ls_mensajeTemp.replace("<TAG_CUENTA>", lci_temp.getCuenta());
					ls_mensajeTemp     = ls_mensajeTemp.replace("<TAG_PERIODO>", lci_temp.getPeriodo());
					ls_dateStr         = lsdf_formatFecha.format(ld_date);
					ls_mensajeTemp     = ls_mensajeTemp.replace("<TAG_CORTE>", ls_dateStr);
					ls_dateStr         = lsdf_formatFecha.format(ld_date);
					ls_mensajeTemp     = ls_mensajeTemp.replace("<TAG_FECHA>", ls_dateStr);
					
					{
						Long ll_idRegistro;
						String ls_registro;
						
						ll_idRegistro 	= lci_temp.getIdRegistro();
						ls_registro 	= IdentificadoresCommon.ESPACIO_VACIO;
						
						if(NumericUtils.isValidLong(ll_idRegistro))
							ls_registro = StringUtils.getString(ll_idRegistro);
						
						ls_mensajeTemp = ls_mensajeTemp.replace("<TAG_REGISTRO>", ls_registro);
					}
					
					{
						String ls_columna;
						ls_columna = lci_temp.getColumna();
						
						if(!StringUtils.isValidString(ls_columna))
							ls_columna = IdentificadoresCommon.ESPACIO_VACIO; 
						
						ls_mensajeTemp = ls_mensajeTemp.replace("<TAG_COLUMNA>", StringUtils.getString(ls_columna));
					}
					
					aci_data.setObservaciones(ls_obsTemp);
					aci_data.setMensaje(ls_mensajeTemp);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("fillTagsObservaciones", lb2be_e);
			
			throw lb2be_e;
		}
		
		return aci_data;
	}
	
	/**
	 * Fill tags observaciones.
	 *
	 * @param ac_collection de ac collection
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<ConInconsistencia> fillTagsObservaciones(Collection<ConInconsistencia> ac_collection)
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(ac_collection))
			throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));

		Collection<ConInconsistencia> lcci_datos;
		DAOManager                    ldm_manager;
		
		lcci_datos	= new ArrayList<ConInconsistencia>();
		ldm_manager	= DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			for(ConInconsistencia lci_objtemp : ac_collection)
				lcci_datos.add(fillTagsObservaciones(lci_objtemp, ldm_manager));
			
			if(!CollectionUtils.isValidCollection(lcci_datos))
				throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("fillTagsObservaciones", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcci_datos;
	}

	/**
	 * Salvar extracto mensual.
	 *
	 * @param aem_extractoMensual de aem extracto mensual
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarExtractoMensual(ExtractoMensual aem_extractoMensual, String as_userId,String as_remoteIp) 
		throws B2BException 
	{
		DAOManager             	ldm_manager;
		ExtractoMensualDAO 		lem_dao;
		
		ldm_manager             = DaoManagerFactory.getDAOManagerConciliacion();
		lem_dao 				= DaoCreator.getExtractoMensualDAO(ldm_manager);
		
		try 
		{
			lem_dao.update(aem_extractoMensual);
		}
		catch (B2BException lb2be_e) 
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("salvarExtractoMensual", lb2be_e);
			throw lb2be_e;
		}
		finally 
		{
			ldm_manager.close();
		}
	}

	/**
	 * Find correos analistas cuenta.
	 *
	 * @param as_idCuenta de as id cuenta
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<String> findCorreosAnalistasCuenta(String as_idCuenta) 
		throws B2BException 
	{
		DAOManager             	ldm_manager;
		Collection<String> 		lcs_return;
		
		ldm_manager             = DaoManagerFactory.getDAOManagerConciliacion();
		lcs_return 				= null;
		
		try 
		{
			lcs_return = DaoCreator.getCuentaAnalistaDAO(ldm_manager).findCorreosByCuenta(as_idCuenta);
		}
		catch (B2BException lb2be_e) 
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("findCorreosAnalistasCuenta", lb2be_e);
			throw lb2be_e;
		}
		finally 
		{
			ldm_manager.close();
		}
		
		return lcs_return;
	}

	/**
	 * Generar reportes afectacion prestacion servicio.
	 *
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param acaps_consulta de acaps consulta
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] generarReportesAfectacionPrestacionServicio(String as_userId, String as_remoteIp,
		Collection<AfectacionPrestacionServicio> acaps_consulta)
				throws B2BException
	{
		byte[]     lba_return;
		DAOManager ldm_manager;

		lba_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		
		if(CollectionUtils.isValidCollection(acaps_consulta))
		{
			try
			{
				Iterator<AfectacionPrestacionServicio> liaps_iterator;

				liaps_iterator = acaps_consulta.iterator();

				if(liaps_iterator.hasNext())
				{
					AfectacionPrestacionServicio laps_data;

					laps_data = liaps_iterator.next();

					if(laps_data != null)
					{
						String ls_idArchivo;

						ls_idArchivo = laps_data.getIdArchivo();

						if(StringUtils.isValidString(ls_idArchivo))
						{
							Collection<String> lcs_dataReporte;

							lcs_dataReporte = DaoCreator.getAfectacionPrestacionServicioDAO(ldm_manager).findDataById(ls_idArchivo);

							if(CollectionUtils.isValidCollection(lcs_dataReporte))
							{
								Collection<ZipEntryUtil> lczeu_zip;
								byte[]                   lba_excel;
								byte[]                   lba_csv;
								byte[]                   lba_pdf;
								String                   ls_nombreArchivo;

								lczeu_zip            = new ArrayList<ZipEntryUtil>();
								ls_nombreArchivo     = "REPORTE INGRESOS CON AFECTACIÓN CON PRESTACIÓN DE SERVICIO";
								lba_excel            = null;
								lba_pdf              = null;
								lba_csv              = getTXTFromStringCollection(lcs_dataReporte);
								
								try 
								{
									lba_excel = getXSLFromStringCollection(lcs_dataReporte);
								}
								catch (IOException e) {
									clh_LOGGER.error("generarReportes", e);
								}
							
								try
								{
									lba_pdf = generarReporteAfectacionPrestacionServiciosPdf(
										    lcs_dataReporte, ldm_manager, ls_nombreArchivo
										);
								}
								catch(Exception le_e)
								{
									clh_LOGGER.error("generarReportes", le_e);
								}

								if(lba_excel != null)
								{
									ZipEntryUtil lzeu_excel;

									lzeu_excel = new ZipEntryUtil(
										    ls_nombreArchivo + ExtensionCommon.XLSX_PUNTO,
										    new ByteArrayInputStream(lba_excel)
										);

									lczeu_zip.add(lzeu_excel);
								}

								if(lba_csv != null)
								{
									ZipEntryUtil lzeu_csv;

									lzeu_csv = new ZipEntryUtil(
										    ls_nombreArchivo + ExtensionCommon.TXT_PUNTO,
										    new ByteArrayInputStream(lba_csv)
										);

									lczeu_zip.add(lzeu_csv);
								}

								if(lba_pdf != null)
								{
									ZipEntryUtil lzeu_pdf;

									lzeu_pdf = new ZipEntryUtil(
										    ls_nombreArchivo + ExtensionCommon.PDF_PUNTO,
										    new ByteArrayInputStream(lba_pdf)
										);

									lczeu_zip.add(lzeu_pdf);
								}

								if(CollectionUtils.isValidCollection(lczeu_zip))
									lba_return = ZipUtils.generateZip(lczeu_zip);
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("generarReportes", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lba_return;

	}
	
	/**
	 * Generar reporte afectacion prestacion servicios pdf.
	 *
	 * @param acs_data de acs data
	 * @param adm_manager de adm manager
	 * @param as_nombreReporte de as nombre reporte
	 * @return el valor de byte[]
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	private synchronized byte[] generarReporteAfectacionPrestacionServiciosPdf(
	    Collection<String> acs_data,DAOManager adm_manager, String as_nombreReporte
	)
	    throws Exception
	{
		byte[]     lba_return;
		DAOManager ldm_manager;

		lba_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(acs_data))
			{
				Constantes lc_plantilla;

				lc_plantilla = DaoCreator.getConstantesDAO(adm_manager)
						                     .findByIdWithImage(ConstanteCommon.PLANTILLA_REPORTES);

				if(lc_plantilla != null)
				{
					byte[] lba_plantilla;

					lba_plantilla = lc_plantilla.getImagenes();

					if(lba_plantilla != null)
					{
						String ls_plantilla;

						ls_plantilla = new String(lba_plantilla);

						if(StringUtils.isValidString(ls_plantilla))
						{
							String ls_tag;

							ls_tag = "<TAG_TIPO_REPORTE>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, as_nombreReporte);

							{
								ls_tag = "<TAG_TABLA>";

								if(ls_plantilla.contains(ls_tag))
								{
									int li_finalTag;

									li_finalTag = ls_plantilla.lastIndexOf(ls_tag);

									if(li_finalTag > 0)
									{
										String ls_textoMitadSuperior;
										String ls_textoMitadInferior;
										String ls_tagNew;

										ls_textoMitadSuperior     = ls_plantilla.substring(0, li_finalTag);
										ls_textoMitadInferior     = ls_plantilla.substring(
											    li_finalTag + ls_tag.length()
											);

										ls_tagNew     = "{\\*\\bkmkstart TAG_TABLA}{\\*\\bkmkend TAG_TABLA} \\line "
											+ ls_tag;

										ls_plantilla = ls_textoMitadSuperior + IdentificadoresCommon.ESPACIO_VACIO
											+ ls_tagNew + IdentificadoresCommon.ESPACIO_VACIO + ls_textoMitadInferior;
									}
								}

								ls_plantilla     = limpiarTags(ls_plantilla);
								ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

								{
									ByteArrayInputStream      lbais_docInStream;
									ByteArrayOutputStream     lbaos_docOutStream;
									com.aspose.words.Document ld_doc;
									DocumentBuilder           ldb_builder;
									int                       li_tamanoLetra;
									long                      ll_porcentajetablas;

									lbais_docInStream       = new ByteArrayInputStream(ls_plantilla.getBytes());
									lbaos_docOutStream      = new ByteArrayOutputStream();
									ld_doc                  = new com.aspose.words.Document(lbais_docInStream);
									ldb_builder             = new DocumentBuilder(ld_doc);
									ll_porcentajetablas     = (long)10.0;
									li_tamanoLetra          = 10;

									ldb_builder.moveToBookmark("TAG_TABLA");

									Table lt_table;

									ldb_builder.writeln(ControlChar.LINE_BREAK);

									lt_table = ldb_builder.startTable();

									for(String ls_linea : acs_data)
									{
										String[] lsa_contenido;

										lsa_contenido = ls_linea.split(IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);

										if(CollectionUtils.isValidCollection(lsa_contenido))
										{
											for(String ls_iterador : lsa_contenido)
											{
												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(ls_iterador);
											}

											ldb_builder.endRow();
										}
									}

									lt_table.setAlignment(TableAlignment.CENTER);
									ldb_builder.endTable();
									ldb_builder.writeln(ControlChar.LINE_BREAK);
									ld_doc.save(lbaos_docOutStream, SaveFormat.RTF);

									lba_return = new PDFGenerator().convertirRTFToPDF(
										    lbaos_docOutStream.toByteArray(), ldm_manager
										);
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.rollback();

			clh_LOGGER.error("generarReporteAfectacionPrestacionServiciosPdf", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Buscar procesos rpt programas activos.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<RPTPrograma> buscarProcesosRptProgramasActivos() 
		throws B2BException 
	{
		Collection<RPTPrograma> 		lcrptp_datos;
		DAOManager                      ldm_manager;

		lcrptp_datos      	= null;
		ldm_manager     	= DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			lcrptp_datos = DaoCreator.getRPTProgramaDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarProcesosRptProgramasActivos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcrptp_datos;
	}
	
	/**
	 * Buscar procesos rpt programas.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<RPTPrograma> buscarProcesosRptProgramas() throws B2BException 
	{
		Collection<RPTPrograma> 		lcrptp_datos;
		DAOManager                      ldm_manager;

		lcrptp_datos      	= null;
		ldm_manager     	= DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			lcrptp_datos = DaoCreator.getRPTProgramaDAO(ldm_manager).findAllPeriodicidad();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarProcesosRptProgramas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcrptp_datos;
	}

	/**
	 * Buscar periodicidad.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Periodicidad> buscarPeriodicidad() throws B2BException
	{
		Collection<Periodicidad> 		lcrptp_datos;
		DAOManager                      ldm_manager;

		lcrptp_datos      	= null;
		ldm_manager     	= DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			lcrptp_datos = DaoCreator.getPeriodicidadDAO(ldm_manager).findAll();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarPeriodicidad", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcrptp_datos;

	}

	/**
	 * Salvar reportes conciliacion.
	 *
	 * @param arptp_parametros de arptp parametros
	 * @param ab_insertar de ab insertar
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de RPT programa
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized RPTPrograma salvarReportesConciliacion(
		    RPTPrograma arptp_parametros, boolean ab_insertar, String as_userId, String as_remoteIp
		)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			if(arptp_parametros != null)
			{
				Long   ll_horaProgramacion;
				Long   ll_minutoProgramacion;
				String ls_activo;

				ll_horaProgramacion       = NumericUtils.getLongWrapper(arptp_parametros.getHoraProgramada());
				ll_minutoProgramacion     = NumericUtils.getLongWrapper(arptp_parametros.getMinutosProgramada());
				ls_activo                 = arptp_parametros.getActivo();

				if((NumericUtils.getLong(ll_horaProgramacion) <= 0)
					    && (NumericUtils.getLong(ll_minutoProgramacion) <= 0) && !StringUtils.isValidString(ls_activo)
				)
					throw new B2BException(addErrorCMF(ErrorKeys.ERROR_CAMPOS_OBLIGATORIOS));

				{
					if(!NumericUtils.isValidLong(ll_horaProgramacion))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_SELECCIONAR_HORA_EJECUCION));
				}

				{
					if(!NumericUtils.isValidLong(ll_minutoProgramacion))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_SELECCIONAR_MINUTO_EJECUCION));
				}
				
				{
					if(!StringUtils.isValidString(arptp_parametros.getNombre()))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}
				
				{
					if(!StringUtils.isValidString(arptp_parametros.getSqlEjecucion()))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_SQL);
				}
				
				{
					if(!StringUtils.isValidString(arptp_parametros.getIdPeriodicidad()))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PERIODICIDAD);
				}
				
				{
					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(addErrorCMF(ErrorKeys.DEBE_DIGITAR_ACTIVO));
				}
				
				{
					if(!StringUtils.isValidString(arptp_parametros.getSolicitaCTA()))
						throw new B2BException(ErrorKeys.ERROR_DEBE_DILIGENCIAR_TODOS_LOS_CAMPOS);
				}
				
				{
					if(!StringUtils.isValidString(arptp_parametros.getTipoArchivo()))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_TIPO_ARCHIVO);
				}
				
				{
					if(!StringUtils.isValidString(arptp_parametros.getExpedientes()))
						throw new B2BException(ErrorKeys.ERROR_DEBE_DILIGENCIAR_TODOS_LOS_CAMPOS);
				}
				
				{
					if(!StringUtils.isValidString(arptp_parametros.getTiposDocumentales()))
						throw new B2BException(ErrorKeys.ERROR_DEBE_DILIGENCIAR_TODOS_LOS_CAMPOS);
				}

				if(ab_insertar)
				{
					Collection<RPTPrograma> lcrptp_procesos;
					lcrptp_procesos = DaoCreator.getRPTProgramaDAO(ldm_manager).findAllPeriodicidad();

					if(CollectionUtils.isValidCollection(lcrptp_procesos))
					{
						for(RPTPrograma lrptp_proceso : lcrptp_procesos)
						{
							if(lrptp_proceso.getNombre().equalsIgnoreCase(arptp_parametros.getNombre()))
								throw new B2BException(addErrorCMF(ErrorKeys.YA_EXISTE_REPORTE_CONCILIACION_NOMBRE));
						}
					}
				}

				if(ab_insertar)
				{
					arptp_parametros.setIdUsuarioCreacion(as_userId);
					arptp_parametros.setIpCreacion(as_remoteIp);
				}
				else
				{
					arptp_parametros.setIdUsuarioModificacion(as_userId);
					arptp_parametros.setIpModificacion(as_remoteIp);
				}

				arptp_parametros = DaoCreator.getRPTProgramaDAO(ldm_manager)
						                         .insertOrUpdate(arptp_parametros, ab_insertar);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarReportesConciliacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return arptp_parametros;
	}

	/**
	 * Find rpt programa by id.
	 *
	 * @param as_idReporte the as id reporte
	 * @return the RPT programa
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized RPTPrograma findRptProgramaById(String as_idReporte) 
		throws B2BException
	{
		DAOManager   ldm_manager;
		RPTPrograma lrptp_return;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lrptp_return       = null;

		try
		{
			lrptp_return = DaoCreator.getRPTProgramaDAO(ldm_manager).findById(as_idReporte);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPeriodoCorteById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lrptp_return;
	}

	public synchronized Collection<String> findProcedimientosReportesByPeriodicidad(String as_periodicidad, String as_userId, String as_remoteIp) 
		throws B2BException 
	{
		Collection<String> lcs_return;

		lcs_return       = null;
		
		if(StringUtils.isValidString(as_periodicidad))
		{
			DAOManager   ldm_manager;
			
			ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
			
			try
			{
				String ls_constante;
				
				ls_constante = null;
				
				if(as_periodicidad.equalsIgnoreCase(PeriodicidadCommon.DIARIO))
					ls_constante = ConstanteCommon.PROCEDIMIENTOS_DIARIOS;
				else if(as_periodicidad.equalsIgnoreCase(PeriodicidadCommon.MENSUAL))
					ls_constante = ConstanteCommon.PROCEDIMIENTOS_MENSUAL;
				
				
				if(StringUtils.isValidString(ls_constante))
				{
					Constantes lc_constante;
					
					lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(ls_constante);
					
					if(lc_constante != null)
					{
						String ls_data;
						
						ls_data = lc_constante.getCaracter();
						
						if(StringUtils.isValidString(ls_data))
						{
							String[] 	lsa_data;
							
							lsa_data 	= ls_data.split(IdentificadoresCommon.SIMBOLO_COMA_TXT);
							lcs_return 	= Arrays.asList(lsa_data);
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();
				
				clh_LOGGER.error("findProcedimientosReportesByPeriodicidad", lb2be_e);
				
				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcs_return;
	}
	
	/**
	 * Consultar afectacion prestacion servicio.
	 *
	 * @param ad_date the as date
	 * @param as_userId the as user id
	 * @param as_remoteIp the as remote ip
	 * @return the map
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized Map<String, Collection<AfectacionPrestacionServicio>> consultarAfectacionPrestacionServicio(
			Date ad_date, String as_userId, String as_remoteIp) 
		throws B2BException 
	{
		DAOManager             									ldm_manager;
		Map<String, Collection<AfectacionPrestacionServicio>> 	lmscaps_return;
		
		ldm_manager             = DaoManagerFactory.getDAOManagerConciliacion();
		lmscaps_return 			= new HashMap<String, Collection<AfectacionPrestacionServicio>>(0);
		
		try 
		{
			AfectacionPrestacionServicioDAO 			laps_dao;
			Collection<AfectacionPrestacionServicio> 	lcaps_AfectacionPrestacionServicio;
			Collection<AfectacionPrestacionServicio> 	lcaps_ListaLey;
			
			laps_dao 							= DaoCreator.getAfectacionPrestacionServicioDAO(ldm_manager);
			lcaps_AfectacionPrestacionServicio 	= null;
			lcaps_ListaLey 						= new ArrayList<AfectacionPrestacionServicio>();
			
			if(ad_date == null) 
				throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));
			
			{
				Calendar 			lc_date;
				SimpleDateFormat 	lsdf_formato;
				
				lc_date 		= Calendar.getInstance();
				lsdf_formato    = new SimpleDateFormat("yyyy-MM-dd");
				
				lc_date.setTime(ad_date);
				
				laps_dao.procCreaIngresoAfectacion(lsdf_formato.format(lc_date.getTime()), as_userId, as_remoteIp);
			}
			
			{
				lcaps_AfectacionPrestacionServicio 	= laps_dao.findValorToltaByDateAndRubro(ad_date);
				
				if(lcaps_AfectacionPrestacionServicio == null || lcaps_AfectacionPrestacionServicio.isEmpty() )
				{
					SimpleDateFormat 	lsdf_formatFecha;
					String           	ls_dateStr;
					Object[] 			loa_messageArgs;
					
					lsdf_formatFecha    = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);    //"dd/MM/yyyy"
					ls_dateStr          = null;
					ls_dateStr    	 	= lsdf_formatFecha.format(ad_date);
					loa_messageArgs 	= new String[1];
					loa_messageArgs[0]  = ls_dateStr;
					
					throw new B2BException(addErrorCMF(ErrorKeys.NO_EXISTE_INFORMACION_PARA_ESA_FECHA,loa_messageArgs));
				}
			}
			
			lmscaps_return.put(IdentificadoresCommon.AFECTACION_PRESTACION_SERVICIO, lcaps_AfectacionPrestacionServicio);
			
			{
				AfectacionPrestacionServicio laps_objTemp;
				
				laps_objTemp = laps_dao.findValorTotalIngresosSNROtrasEntidades(ad_date);
				
				if(laps_objTemp != null ) 
				{
					AfectacionPrestacionServicio laps_objTemp1;
					AfectacionPrestacionServicio laps_objTemp2;
					
					laps_objTemp1 = new AfectacionPrestacionServicio();
					laps_objTemp2 = new AfectacionPrestacionServicio();
					
					laps_objTemp1.setNombreRubro(IdentificadoresCommon.TRANSFERENCIA_DE_RECURSOS_A_OTRAS_ENTIDADES);
					laps_objTemp1.setValorTotal(laps_objTemp.getValorOtrasEntidades());
					laps_objTemp2.setNombreRubro(IdentificadoresCommon.INGRESOS_DE_LA_SUPERINTENDENCIA_DE_NOTARIADO_Y_REGISTRO);
					laps_objTemp2.setValorTotal(laps_objTemp.getValorIngresosSnr());
					lcaps_ListaLey.add(laps_objTemp1);
					lcaps_ListaLey.add(laps_objTemp2);
				}
			}
			
			lmscaps_return.put(IdentificadoresCommon.LISTA_LEY, lcaps_ListaLey);
		}
		catch (B2BException lb2be_e) 
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarAfectacionPrestacionServicio", lb2be_e);
			throw lb2be_e;
		}
		finally 
		{
			ldm_manager.close();
		}
		
		return lmscaps_return;
	}

	/**
	 * Find periodos fecha.
	 *
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<PeriodoCorte> findPeriodosFecha() 
		throws B2BException 
	{
		DAOManager             		ldm_manager;
		Collection<PeriodoCorte> 	lcpc_return;
		
		ldm_manager             = DaoManagerFactory.getDAOManagerConciliacion();
		lcpc_return 			= null;
		
		try 
		{
			lcpc_return = DaoCreator.getPeriodoCorteDAO(ldm_manager).findPeriodoMes();
		}
		catch (B2BException lb2be_e) 
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("findPeriodosFecha", lb2be_e);
			throw lb2be_e;
		}
		finally 
		{
			ldm_manager.close();
		}
		
		return lcpc_return;
	}
}
