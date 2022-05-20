package com.bachue.snr.prosnr01.business.consulta.reportes;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.reportes.ReportsSheet;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDatoCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.pgn.CamposConsultaDAO;
import com.bachue.snr.prosnr01.dao.pgn.InstanciaConsultaDAO;
import com.bachue.snr.prosnr01.dao.pgn.ResultadoConsultaDAO;

import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Consultas;
import com.bachue.snr.prosnr01.model.sdb.pgn.InstanciaConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.ResultadoConsulta;

import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * Clase que contiene todos las funcionalidades para consultar reportes
 *
 * @author Julian Vaca
 */
public class ConsultaReportesBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaReportesBusiness.class);

	/**
	 * Retorna el valor del objeto de CamposConsulta.
	 *
	 * @param aocc_cc correspondiente al valor del tipo de objeto Consultas
	 * @return devuelve el valor de CamposConsulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CamposConsulta
	 */
	public synchronized CamposConsulta findCamposReportes(Consultas aocc_cc)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		CamposConsultaDAO          ldd_DAO;
		Collection<CamposConsulta> lccc_dataCampos;
		CamposConsulta             locc_dataFinal;

		ldm_manager         = DaoManagerFactory.getDAOManager();
		ldd_DAO             = DaoCreator.getCamposConsultaDAO(ldm_manager);
		lccc_dataCampos     = null;
		locc_dataFinal      = null;

		try
		{
			if(aocc_cc != null)
			{
				long ll_idTipoReporte;
				ll_idTipoReporte = aocc_cc.getIdConsulta();

				if(ll_idTipoReporte > 0)
				{
					lccc_dataCampos = ldd_DAO.findAllActive(aocc_cc);

					if(CollectionUtils.isValidCollection(lccc_dataCampos))
					{
						locc_dataFinal = new CamposConsulta();
						locc_dataFinal.setDataCamposConsulta(lccc_dataCampos);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCamposReportes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return locc_dataFinal;
	}

	/**
	 * Retorna el valor del objeto de ResultadoConsulta.
	 *
	 * @param aocc_cc correspondiente al valor del tipo de objeto CamposConsulta
	 * @return devuelve el valor de ResultadoConsulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ResultadoConsulta
	 */
	public synchronized ResultadoConsulta generacionReporte(CamposConsulta aocc_cc)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		CamposConsultaDAO          ldd_DAO;
		ResultadoConsultaDAO       lorc_DAO;
		InstanciaConsultaDAO       loic_DAO;
		ResultadoConsulta          lorc_dataFinal;
		Collection<CamposConsulta> lccc_infoReporte;
		CamposConsulta             locc_query;
		boolean                    lb_oblitoriedad;
		String                     ls_nombreCampo;
		ResultadoConsulta          lorc_dataConsulta;
		String                     ls_idUser;
		String                     ls_ipAddress;
		long                       ll_secuence;
		String                     ls_valorCampo;
		boolean                    lb_secuenceValid;
		String                     ls_tipoCampo;

		ldm_manager          = DaoManagerFactory.getDAOManager();
		ldd_DAO              = DaoCreator.getCamposConsultaDAO(ldm_manager);
		lorc_DAO             = DaoCreator.getResultadoConsultaDAO(ldm_manager);
		loic_DAO             = DaoCreator.getInstanciaConsultaDAO(ldm_manager);
		lorc_dataFinal       = null;
		ll_secuence          = 0;
		ls_valorCampo        = null;
		lb_secuenceValid     = false;

		try
		{
			if(aocc_cc != null)
			{
				ls_idUser        = aocc_cc.getIdUsuarioCreacion();
				ls_ipAddress     = aocc_cc.getIpCreacion();

				lccc_infoReporte = aocc_cc.getDataCamposConsulta();

				if(CollectionUtils.isValidCollection(lccc_infoReporte))
				{
					lorc_dataConsulta = new ResultadoConsulta();
					lorc_dataConsulta.setIdUsuarioCreacion(ls_idUser);
					lorc_dataConsulta.setIpCreacion(ls_ipAddress);

					//CREAR SECUENCIA E INSERTAR REGISTRO EN LA TABLA SDB_PGN_RESULTADO_CONSULTA CON BLOB Y HASH NULL
					ll_secuence = lorc_DAO.insert(lorc_dataConsulta);

					for(CamposConsulta locc_tmp : lccc_infoReporte)
					{
						if(locc_tmp != null)
						{
							//CONSULTA OBLIGATOREIDAD Y TIPOS DE DATOS PARA RESPECTIVAS VALIDACIONES
							locc_query = ldd_DAO.findById(locc_tmp);

							if(locc_query != null)
							{
								lb_oblitoriedad     = locc_query.isObligatoriedad();
								ls_nombreCampo      = StringUtils.getStringNotNull(locc_query.getNombreCampo())
										                             .toLowerCase();
								ls_valorCampo       = locc_tmp.getValorCampo();
								ls_tipoCampo        = locc_query.getTipoCampo();

								if(
								    ls_tipoCampo.equalsIgnoreCase(TipoDatoCommon.ENTERO)
									    || ls_tipoCampo.equalsIgnoreCase(TipoDatoCommon.TEXTO)
								)
								{
									if(lb_oblitoriedad)
									{
										if(!StringUtils.isValidString(ls_valorCampo))
											throw new B2BException(
											    "El campo " + ls_nombreCampo + " " + "es obligatorio."
											);
									}
								}
								else if(ls_tipoCampo.equalsIgnoreCase(TipoDatoCommon.FECHA))
								{
									if(lb_oblitoriedad)
									{
										if(!StringUtils.isValidString(ls_valorCampo))
											throw new B2BException("El campo " + ls_nombreCampo + " es obligatorio.");
									}

									if(StringUtils.isValidString(ls_valorCampo))
									{
										try
										{
											DateFormat lsf_dateFormat;
											Date       ld_convertCalendar;
											lsf_dateFormat     = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);

											ld_convertCalendar = new SimpleDateFormat(
												    "E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH
												).parse(ls_valorCampo);

											if(ld_convertCalendar.after(new Date()))
												throw new B2BException(
												    "El campo " + ls_nombreCampo
												    + " no puede ser superior a la fecha actual"
												);

											ls_valorCampo = lsf_dateFormat.format(ld_convertCalendar);
										}
										catch(ParseException le_e)
										{
											clh_LOGGER.error("generacionReporte", le_e);
										}
									}
								}

								lb_secuenceValid = ll_secuence > 0;

								//CON LA SENCUECIA GENERADA INSERTAR EN LA TABLA SDB_PGN_INSTANCIA_CONSULTA
								if(lb_secuenceValid)
								{
									InstanciaConsulta loic_ic;
									loic_ic = new InstanciaConsulta();

									loic_ic.setIdConsulta(NumericUtils.getLong(locc_tmp.getIdConsulta()));
									loic_ic.setIdCampo(NumericUtils.getLong(locc_tmp.getIdCampo()));
									loic_ic.setIdInstancia(ll_secuence);
									loic_ic.setValor(ls_valorCampo);
									loic_ic.setIdUsuarioCreacion(ls_idUser);
									loic_ic.setIpCreacion(ls_ipAddress);

									if(StringUtils.isValidString(ls_valorCampo))
										loic_DAO.insertOrUpdate(loic_ic, true);
								}
							}
						}
					}

					//INVOCAR PROCEDIMIENTO ALMACENADO CON LA SECUENCIA CREADA EN LA TABLA SDB_PGN_RESULTADO_CONSULTA
					if(lb_secuenceValid)
					{
						InstanciaConsulta loic_ic;

						loic_ic = new InstanciaConsulta();

						loic_ic.setIdInstancia(ll_secuence);
						loic_ic.setIdUsuarioCreacion(ls_idUser);
						loic_ic.setIpCreacion(ls_ipAddress);

						loic_ic = DaoCreator.getProcedimientosDAO(ldm_manager).reporteDiarioRadicador(loic_ic);

						//CAPTURAR RESULTADO DEL PROCEDIMIENTO GENERAR ARCHIVO EXCEL
						if(loic_ic != null)
						{
							Collection<Map<String, Object>>             lcmso_data;
							Collection<Collection<Map<String, Object>>> lccmso_hashMapCollection;
							byte[]                                      lba_plantilla;
							Map<Integer, String>                        lmis_sheetName;
							Constantes                                  loc_datos;

							loc_datos = new Constantes();

							loc_datos.setIdConstante(ConstanteCommon.PLANTILLA_EXCEL);
							loc_datos = DaoCreator.getConstantesDAO(ldm_manager).findImagen(loc_datos);

							if(loc_datos != null)
							{
								lba_plantilla     = loc_datos.getImagenes();

								lcmso_data                   = loic_ic.getDataColumns();
								lmis_sheetName               = new HashMap<Integer, String>();
								lccmso_hashMapCollection     = new ArrayList<Collection<Map<String, Object>>>();

								lmis_sheetName.put(NumericUtils.getInteger(0), "Primer reporte");

								if(CollectionUtils.isValidCollection(lcmso_data))
								{
									lccmso_hashMapCollection.add(lcmso_data);

									{
										Map<String, String> lmss_tags;

										lmss_tags = new HashMap<String, String>();

										lmss_tags.put("<TAG_CIUDAD_CIRCULO>", "POR DEFINIR");

										{
											Consultas lc_consulta;

											lc_consulta = new Consultas();

											lc_consulta.setIdConsulta(NumericUtils.getLong(aocc_cc.getIdConsulta()));

											lc_consulta = DaoCreator.getConsultasReportesDAO(ldm_manager)
													                    .findById(lc_consulta);

											if(lc_consulta != null)
												lmss_tags.put(
												    "<TAG_CONSULTA_REPORTE>", lc_consulta.getNombreConsulta()
												);
										}

										{
											String ls_fechaImpresion;

											ls_fechaImpresion = StringUtils.getString(
												    new Date(), "'Impreso el' dd 'de' MMMMM 'de' yyyy 'a las' HH:mm:ss ",
												    new Locale("es", "CO")
												);

											lmss_tags.put("<TAG_FECHA_IMPRESION>", ls_fechaImpresion);
										}

										{
											String ls_fechaInstancia;

											ls_fechaInstancia = StringUtils.getString(
												    new Date(), " dd '-' MM '-' yyyy", new Locale("es", "CO")
												);

											lmss_tags.put("<TAG_FECHA_REPORTE>", ls_fechaInstancia);
										}

										{
											String ls_nombreUsuario;

											ls_nombreUsuario = "";

											if(StringUtils.isValidString(ls_idUser))
											{
												Usuario lu_usuario;

												lu_usuario = new Usuario();

												lu_usuario.setIdUsuario(ls_idUser);

												lu_usuario = DaoCreator.getUsuarioDAO(ldm_manager).findById(lu_usuario);

												if(lu_usuario != null)
												{
													String ls_s1;
													String ls_pNombre;
													String ls_sNombre;
													String ls_pApellido;
													String ls_sApellido;

													ls_pNombre       = lu_usuario.getPrimerNombre();
													ls_sNombre       = lu_usuario.getSegundoNombre();
													ls_pApellido     = lu_usuario.getPrimerApellido();
													ls_sApellido     = lu_usuario.getSegundoApellido();

													ls_s1 = StringUtils.isValidString(ls_pNombre) ? ls_pNombre : "";
													ls_s1 += (StringUtils.isValidString(ls_s1) ? " " : "");
													ls_s1 += (StringUtils.isValidString(ls_sNombre) ? ls_sNombre : "");
													ls_s1 += (StringUtils.isValidString(ls_s1) ? " " : "");
													ls_s1 += (StringUtils.isValidString(ls_pApellido) ? ls_pApellido : "");
													ls_s1 += (StringUtils.isValidString(ls_s1) ? " " : "");
													ls_s1 += (StringUtils.isValidString(ls_sApellido) ? ls_sApellido : "");
													ls_s1 += (StringUtils.isValidString(ls_s1) ? " " : "");

													if(StringUtils.isValidString(ls_s1))
														ls_nombreUsuario = ls_s1;
												}
											}

											lmss_tags.put("<TAG_USUARIO>", ls_nombreUsuario);
										}

										lba_plantilla = new ReportsSheet().getEveningReport(
											    lccmso_hashMapCollection, lmis_sheetName, null, null, lba_plantilla,
											    lmss_tags
											);
									}

									//ACTUALIZAR BLOB Y HASH  con el ID INSTANCIA EN LA TABLA  SDB_PGN_RESULTADO_CONSULTA
									if(lba_plantilla != null)
									{
										lorc_dataFinal = new ResultadoConsulta();

										lorc_dataFinal.setIdInstancia(ll_secuence);
										lorc_dataFinal.setResultadoBlob(lba_plantilla);

										lorc_DAO.update(lorc_dataFinal);
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

			clh_LOGGER.error("generacionReporte", lb2be_e);

			throw lb2be_e;
		}
		catch(IOException lioe_ioe)
		{
			clh_LOGGER.error("generacionReporte", lioe_ioe);
			ldm_manager.setRollbackOnly();
			throw new B2BException(lioe_ioe.getMessage());
		}
		finally
		{
			ldm_manager.close();
		}

		return lorc_dataFinal;
	}
}
