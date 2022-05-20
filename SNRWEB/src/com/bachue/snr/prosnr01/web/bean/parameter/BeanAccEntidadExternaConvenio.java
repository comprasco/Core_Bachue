package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExternaConvenio;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase para el manejo de la capa web para AccEntidadExternaConvenio
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanAccEntidadExternaConvenio")
@SessionScoped
public class BeanAccEntidadExternaConvenio extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanAccEntidadExternaConvenio.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2165848127591894879L;

	/** Propiedad icaeec consulta. */
	private AccEntidadExternaConvenio iaeec_consulta;

	/** Propiedad iaeec parametros. */
	private AccEntidadExternaConvenio iaeec_parametros;

	/** Propiedad icaeec acc entidad externa. */
	private Collection<AccEntidadExterna> icaeec_accEntidadExterna;

	/** Propiedad icaeec acc entidad externa convenio. */
	private Collection<AccEntidadExternaConvenio> icaeec_accEntidadExternaConvenio;

	/** Propiedad icaeec datos auditoria. */
	private Collection<AccEntidadExternaConvenio> icaeec_datosAuditoria;

	/** Propiedad iczr zona registral asociada. */
	private Collection<ZonaRegistral> iczr_zonaRegistralAsociada;

	/** Propiedad iczr zona registral incial. */
	private Collection<ZonaRegistral> iczr_zonaRegistralInicial;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is nombre entidad. */
	private String is_nombreEntidad;

	/** Propiedad is numero convenio. */
	private String is_numeroConvenio;

	/** Propiedad izr zona registral eliminar. */
	private ZonaRegistral iczr_zonaRegistralEliminar;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** Propiedad ib insertar convenio. */
	private boolean ib_insertarConvenio;

	/** Propiedad ib tabla activa. */
	private boolean ib_tablaActiva;

	/** Propiedad ib tabla zonas. */
	private boolean ib_tablaZonas;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroConvenio(String as_s)
	{
		is_numeroConvenio = as_s;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroConvenio()
	{
		return is_numeroConvenio;
	}

	/**
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreEntidad(String as_s)
	{
		is_nombreEntidad = as_s;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreEntidad()
	{
		return is_nombreEntidad;
	}

	/**
	 * @param acaeec_caeec asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<AccEntidadExternaConvenio> acaeec_caeec)
	{
		icaeec_datosAuditoria = acaeec_caeec;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<AccEntidadExternaConvenio> getDatosAuditoria()
	{
		return icaeec_datosAuditoria;
	}

	/**
	 * @param acaeec_caeec asigna el valor a la propiedad
	 */
	public void setAccEntidadExternaConvenio(Collection<AccEntidadExternaConvenio> acaeec_caeec)
	{
		icaeec_accEntidadExternaConvenio = acaeec_caeec;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<AccEntidadExternaConvenio> getAccEntidadExternaConvenio()
	{
		return icaeec_accEntidadExternaConvenio;
	}

	/**
	 * @param acaee_caee asigna el valor a la propiedad
	 */
	public void setAccEntidadExterna(Collection<AccEntidadExterna> acaee_caee)
	{
		icaeec_accEntidadExterna = acaee_caee;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<AccEntidadExterna> getAccEntidadExterna()
	{
		return icaeec_accEntidadExterna;
	}

	/**
	 * @param aczr_czr asigna el valor a la propiedad
	 */
	public void setZonaRegistralAsociada(Collection<ZonaRegistral> aczr_czr)
	{
		iczr_zonaRegistralAsociada = aczr_czr;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<ZonaRegistral> getZonaRegistralAsociada()
	{
		return iczr_zonaRegistralAsociada;
	}

	/**
	 * @param aczr_czr asigna el valor a la propiedad
	 */
	public void setZonaRegistralInicial(Collection<ZonaRegistral> aczr_czr)
	{
		iczr_zonaRegistralInicial = aczr_czr;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<ZonaRegistral> getZonaRegistralInicial()
	{
		return iczr_zonaRegistralInicial;
	}

	/**
	 * @param azr_zr asigna el valor a la propiedad
	 */
	public void setZonaRegistralEliminar(ZonaRegistral azr_zr)
	{
		iczr_zonaRegistralEliminar = azr_zr;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public ZonaRegistral getZonaRegistralEliminar()
	{
		return iczr_zonaRegistralEliminar;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setInsertarConvenios(boolean ab_b)
	{
		ib_insertarConvenio = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertarConvenios()
	{
		return ib_insertarConvenio;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setTablaActiva(boolean ab_b)
	{
		ib_tablaActiva = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isTablaActiva()
	{
		return ib_tablaActiva;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setTablaZonas(boolean ab_b)
	{
		ib_tablaZonas = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isTablaZonas()
	{
		return ib_tablaZonas;
	}

	/**
	 * @param parametros asigna el valor a la propiedad
	 */
	public void setParametros(AccEntidadExternaConvenio parametros)
	{
		iaeec_parametros = parametros;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public AccEntidadExternaConvenio getParametros()
	{
		if(iaeec_parametros == null)
			iaeec_parametros = new AccEntidadExternaConvenio();

		return iaeec_parametros;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public AccEntidadExternaConvenio getEntidadExternaConvenio()
	{
		return iaeec_consulta;
	}

	/**
	 * @param aaeec_aeec parametros asigna el valor a la propiedad
	 */
	public void setEntidadExternaConvenio(AccEntidadExternaConvenio aaeec_aeec)
	{
		iaeec_consulta = aaeec_aeec;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Entidad Externa Convenio
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setEntidadExternaConvenio(new AccEntidadExternaConvenio());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarAccEntidadExternaConvenio");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_ACC_ENTIDAD_EXTERNA_CONVENIO
	 * @param aaeec_aeec
	 * @throws B2BException
	 */
	public void consultaDetallada(AccEntidadExternaConvenio aaeec_aeec)
	    throws B2BException
	{
		if(aaeec_aeec != null)
		{
			AccEntidadExternaConvenio lcaeec_datos;
			String                    ls_idEntidadExterna;
			String                    ls_numeroConvenio;

			ls_idEntidadExterna     = (aaeec_aeec.getIdEntidadExterna());
			ls_numeroConvenio       = (aaeec_aeec.getNumeroConvenio());
			lcaeec_datos            = null;

			if(StringUtils.isValidString((ls_idEntidadExterna)) && StringUtils.isValidString((ls_numeroConvenio)))
				lcaeec_datos = ipr_parameterRemote.findAccEntidadExternaConvenioById(aaeec_aeec);

			if(lcaeec_datos != null)
			{
				Collection<AccEntidadExternaConvenio> lcaeec_entidadExternaConvenios;

				lcaeec_entidadExternaConvenios = new ArrayList<AccEntidadExternaConvenio>();

				lcaeec_entidadExternaConvenios.add(lcaeec_datos);
				setEntidadExternaConvenio(lcaeec_datos);

				{
					Collection<ZonaRegistral> lczr_zonaRegistral;

					lczr_zonaRegistral = lcaeec_datos.getZonaRegistral();

					if(CollectionUtils.isValidCollection(lczr_zonaRegistral))
					{
						Collection<ZonaRegistral> lczr_zonaRegistralInicial;

						lczr_zonaRegistralInicial = new ArrayList<ZonaRegistral>();
						setZonaRegistralAsociada(lczr_zonaRegistral);

						for(ZonaRegistral lzr_temp : lczr_zonaRegistral)
						{
							if(lzr_temp != null)
								lczr_zonaRegistralInicial.add(lzr_temp);
						}

						setZonaRegistralInicial(lczr_zonaRegistralInicial);
					}
				}

				setDatosAuditoria(lcaeec_entidadExternaConvenios);
				setTablaZonas(true);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método encargado de consultar los registros de la tabla SDB_ACC_ENTIDAD_EXTERNA_CONVENIO.
	 */
	public void consultarConvenios()
	{
		try
		{
			Collection<AccEntidadExternaConvenio> lcaeec_entidadExternaConvenio;

			lcaeec_entidadExternaConvenio = ipr_parameterRemote.findConvenios(getNumeroConvenio(), getNombreEntidad());

			if(!CollectionUtils.isValidCollection(lcaeec_entidadExternaConvenio))
				setInsertarConvenios(true);
			else
				setInsertarConvenios(false);

			setAccEntidadExternaConvenio(lcaeec_entidadExternaConvenio);
			setTablaActiva(true);

			PrimeFaces.current().ajax().update("fAccEntidadExternaConvenio");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fAccEntidadExternaConvenio");
		}
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 * @return
	 */
	public String salvar()
	{
		String       ls_result;
		boolean      lb_focus;
		FacesContext lfc_context;

		ls_result       = null;
		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			AccEntidadExternaConvenio lee_entidadExterna;

			lee_entidadExterna = getEntidadExternaConvenio();

			if(lee_entidadExterna != null)
			{
				{
					String ls_numeroConvenio;

					ls_numeroConvenio     = lee_entidadExterna.getNumeroConvenio();

					lb_focus = validateStyles(
						    ":fAccEntidadExternaConvenioDetalle:idNumeroConvenio", lfc_context, ls_numeroConvenio,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_numeroConvenio))
						throw new B2BException(ErrorKeys.ERROR_SIN_NUMERO_CONVENIO);
				}

				{
					String ls_nombreConvenio;

					ls_nombreConvenio     = lee_entidadExterna.getNombreConvenio();

					lb_focus = validateStyles(
						    ":fAccEntidadExternaConvenioDetalle:idNombreConvenio", lfc_context, ls_nombreConvenio,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_nombreConvenio))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE_CONVENIO);
				}

				{
					String ls_idEntidadExterna;

					ls_idEntidadExterna     = lee_entidadExterna.getIdEntidadExterna();

					lb_focus = validateStyles(
						    ":fAccEntidadExternaConvenioDetalle:idEntidadExterna", lfc_context, ls_idEntidadExterna,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_idEntidadExterna))
						throw new B2BException(ErrorKeys.DEBE_ELEGIR_ENTIDAD_EXTERNA);
				}

				{
					String ls_activo;

					ls_activo     = lee_entidadExterna.getActivo();

					lb_focus = validateStyles(
						    ":fAccEntidadExternaConvenioDetalle:idActivo", lfc_context, ls_activo, lb_focus
						);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				{
					String ls_idTipoEntidad;

					ls_idTipoEntidad     = lee_entidadExterna.getIdTipoEntidad();

					lb_focus = validateStyles(
						    ":fAccEntidadExternaConvenioDetalle:idTipoEntidad", lfc_context, ls_idTipoEntidad, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoEntidad))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_ENTIDAD);
				}

				{
					Date ld_fechaDesde;

					ld_fechaDesde     = lee_entidadExterna.getFechaCreacionConvenio();

					lb_focus = validateStyles(
						    ":fAccEntidadExternaConvenioDetalle:idFechaCreacionConvenio", lfc_context, ld_fechaDesde,
						    lb_focus
						);

					if(ld_fechaDesde == null)
						throw new B2BException(ErrorKeys.ERROR_FECHA_CREACION);
				}

				{
					String ls_idCirculo;

					ls_idCirculo = lee_entidadExterna.getIdCirculo();

					if(StringUtils.isValidString(ls_idCirculo))
					{
						Collection<ZonaRegistral> lczr_zonaRegistral;

						lczr_zonaRegistral     = getZonaRegistralAsociada();

						lb_focus = validateStyles(
							    ":fAccEntidadExternaConvenioDetalle:idZonaRegistral", lfc_context,
							    IdentificadoresCommon.DATO_INVALIDO, lb_focus
							);

						if(!CollectionUtils.isValidCollection(lczr_zonaRegistral))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ZONA_REGISTRAL);
					}
				}

				ipr_parameterRemote.salvarAccEntidadExternaConvenio(
				    lee_entidadExterna, isInsertar(), getZonaRegistralAsociada(), getUserId(), getLocalIpAddress(),
				    getRemoteIpAddress()
				);

				setEntidadExternaConvenio(null);
				setTablaActiva(false);

				ls_result = NavegacionCommon.ACC_ENTIDAD_EXTERNA_CONVENIO;

				addMessage(MessagesKeys.PROCESO_COMPLETADO);

				{
					ExternalContext lec_externalContext;

					lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

					if(lec_externalContext != null)
					{
						Flash lf_flash;

						lf_flash = lec_externalContext.getFlash();

						if(lf_flash != null)
							lf_flash.setKeepMessages(true);
					}
				}

				clear();
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fAccEntidadExternaConvenioDetalle:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Metodo para traer los registros de la base de datos de SDB_ACC_ENTIDAD_EXTERNA
	 *
	 */
	public void findAccEntidadesExternas()
	{
		try
		{
			Collection<AccEntidadExterna> lcaee_entidadExterna;

			lcaee_entidadExterna = ipr_parameterRemote.findAllAccEntidadExternas();

			setAccEntidadExterna(lcaee_entidadExterna);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Metodo para cargar la fecha de vencimiento de un convenio
	 *
	 * @param aaeec_entidadConvenio Objeto de tipo AccEntidadExternaConvenio con el parametro
	 */
	public void findFechaVencimiento(AccEntidadExternaConvenio aaeec_entidadConvenio)
	{
		if(aaeec_entidadConvenio != null)
		{
			Date ld_fechaCreacion;

			ld_fechaCreacion = aaeec_entidadConvenio.getFechaCreacionConvenio();

			if(ld_fechaCreacion != null)
			{
				Calendar lc_calendar;
				Date     ld_fechaVencimiento;

				lc_calendar = Calendar.getInstance();
				lc_calendar.setTime(ld_fechaCreacion);
				lc_calendar.add(Calendar.YEAR, 1);

				ld_fechaVencimiento = lc_calendar.getTime();

				if(ld_fechaVencimiento != null)
					aaeec_entidadConvenio.setFechaVencimiento(ld_fechaVencimiento);
			}
		}
	}

	/**
	 * Método para salvar la zona registral
	 *
	 * @return
	 */
	public String salvarZonaRegistral()
	{
		String       ls_result;
		boolean      lb_focus;
		FacesContext lfc_context;

		ls_result       = null;
		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			AccEntidadExternaConvenio lee_entidadExterna;

			lee_entidadExterna = getEntidadExternaConvenio();

			if(lee_entidadExterna != null)
			{
				{
					String ls_idCirculo;

					ls_idCirculo     = lee_entidadExterna.getIdCirculo();

					lb_focus = validateStyles(
						    ":fAccEntidadExternaConvenioDetalle:idCircRegistral", lfc_context, ls_idCirculo, lb_focus
						);

					if(!StringUtils.isValidString(ls_idCirculo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
				}

				{
					Collection<ZonaRegistral> lczr_zonaRegistral;

					lczr_zonaRegistral     = lee_entidadExterna.getZonaRegistral();

					lb_focus = validateStyles(
						    ":fAccEntidadExternaConvenioDetalle:idZonaRegistral", lfc_context,
						    IdentificadoresCommon.DATO_INVALIDO, lb_focus
						);

					if(!CollectionUtils.isValidCollection(lczr_zonaRegistral))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ZONA_REGISTRAL);

					lczr_zonaRegistral = ipr_parameterRemote.findAllZonaRegistralesAsociadas(lczr_zonaRegistral);

					Collection<ZonaRegistral> lczr_zonaRegistralAsociada;

					lczr_zonaRegistralAsociada = getZonaRegistralAsociada();

					if(
					    CollectionUtils.isValidCollection(lczr_zonaRegistralAsociada)
						    && CollectionUtils.isValidCollection(lczr_zonaRegistral)
					)
					{
						Collection<ZonaRegistral> lczr_zonasAsociar;

						lczr_zonasAsociar = new ArrayList<ZonaRegistral>();

						{
							for(ZonaRegistral lzrg_temp : lczr_zonaRegistral)
							{
								if(lzrg_temp != null)
									lczr_zonasAsociar.add(lzrg_temp);
							}
						}

						for(ZonaRegistral lzr_temp : lczr_zonaRegistralAsociada)
						{
							if(lzr_temp != null)
							{
								String ls_idZonaRegistral;

								ls_idZonaRegistral = lzr_temp.getIdZonaRegistral();

								if(StringUtils.isValidString(ls_idZonaRegistral))
								{
									for(ZonaRegistral lzrg_temp : lczr_zonaRegistral)
									{
										if(lzrg_temp != null)
										{
											String ls_idZonaRegistralAsociar;

											ls_idZonaRegistralAsociar = lzrg_temp.getIdZonaRegistral();

											if(
											    StringUtils.isValidString(ls_idZonaRegistralAsociar)
												    && (ls_idZonaRegistralAsociar.equalsIgnoreCase(ls_idZonaRegistral))
											)
											{
												for(
												    Iterator<ZonaRegistral> iterator = lczr_zonasAsociar.iterator();
													    iterator.hasNext();
												)
												{
													ZonaRegistral lzr_zonaRegistral;

													lzr_zonaRegistral = iterator.next();

													if(
													    (lzr_zonaRegistral != null)
														    && lzr_zonaRegistral.equals(lzrg_temp)
													)
														iterator.remove();
												}
											}
										}
									}
								}
							}
						}

						if(CollectionUtils.isValidCollection(lczr_zonasAsociar))
						{
							for(ZonaRegistral lc_temp : lczr_zonasAsociar)
							{
								if(lc_temp != null)
									lczr_zonaRegistralAsociada.add(lc_temp);
							}

							setZonaRegistralAsociada(lczr_zonaRegistralAsociada);
						}
					}
					else
						setZonaRegistralAsociada(lczr_zonaRegistral);

					setTablaZonas(true);
				}

				lee_entidadExterna.setZonaRegistral(null);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fAccEntidadExternaConvenioDetalle:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Metodo para traer los registros de la base de datos
	 *
	 * @return Colección de TipoEntidad resultante de la consulta
	 */
	public Collection<TipoEntidad> cargarTipoEntidad()
	{
		Collection<TipoEntidad> lcte_tipoEntidad;

		lcte_tipoEntidad = new ArrayList<TipoEntidad>();

		try
		{
			lcte_tipoEntidad = irr_referenceRemote.findTipoEntidad(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcte_tipoEntidad;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<CirculoRegistral> findCirculoRegistral()
	{
		Collection<CirculoRegistral> lcidt_datos;

		lcidt_datos = new ArrayList<CirculoRegistral>();

		try
		{
			lcidt_datos = irr_referenceRemote.findAllCirculosRegistralesActivos(
				    false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lcidt_datos))
			{
				Collection<CirculoRegistral> lccr_circuloRegistral;

				lccr_circuloRegistral = new ArrayList<CirculoRegistral>();

				for(CirculoRegistral lcr_temp : lcidt_datos)
				{
					if(lcr_temp != null)
					{
						String ls_idCirculo;

						ls_idCirculo = lcr_temp.getIdCirculo();

						if(StringUtils.isValidString(ls_idCirculo) && !ls_idCirculo.equalsIgnoreCase(EstadoCommon.SNR))
							lccr_circuloRegistral.add(lcr_temp);
					}
				}

				lcidt_datos = lccr_circuloRegistral;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Metodo para traer los registros de la base de datos
	 *
	 * @return Colección de ZonaRegistral resultante de la consulta
	 */
	public Collection<ZonaRegistral> findZonaRegistral()
	{
		Collection<ZonaRegistral> lczr_zonaRegistral;

		lczr_zonaRegistral = new ArrayList<ZonaRegistral>();

		try
		{
			AccEntidadExternaConvenio laeec_entidadExternaConvenio;

			laeec_entidadExternaConvenio = getEntidadExternaConvenio();

			if(laeec_entidadExternaConvenio != null)
			{
				String ls_idCirculo;

				ls_idCirculo = laeec_entidadExternaConvenio.getIdCirculo();

				if(StringUtils.isValidString(ls_idCirculo))
					lczr_zonaRegistral = ipr_parameterRemote.findAllZonaRegistralesActivos(ls_idCirculo, true);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lczr_zonaRegistral;
	}

	/**
	 * Metodo que se encarga de cargar la zona registral a eliminar
	 *
	 * @param azr_zr Objeto ZonaRegistral a eliminar
	 *
	 */
	public void zonaAEliminar(ZonaRegistral azr_zr)
	{
		setZonaRegistralEliminar(azr_zr);
	}

	/**
	 * Método encargado de agregar un registro y modificar los demás.
	 *
	 *
	 */
	public void zonaEliminada()
	{
		ZonaRegistral lzr_zr;

		lzr_zr = getZonaRegistralEliminar();

		if(lzr_zr != null)
		{
			Collection<ZonaRegistral> lzr_zonaActual;

			lzr_zonaActual = getZonaRegistralAsociada();

			if(CollectionUtils.isValidCollection(lzr_zonaActual))
			{
				Collection<ZonaRegistral> lzr_zonaEliminar;

				lzr_zonaEliminar = new ArrayList<ZonaRegistral>();

				for(ZonaRegistral lcp_temp : lzr_zonaActual)
				{
					if(lcp_temp != null)
					{
						if(lcp_temp != lzr_zr)
							lzr_zonaEliminar.add(lcp_temp);
					}
				}

				AccEntidadExternaConvenio laeec_entidadExternaConvenio;

				laeec_entidadExternaConvenio = getEntidadExternaConvenio();

				if(laeec_entidadExternaConvenio != null)
					laeec_entidadExternaConvenio.setZonaRegistral(lzr_zonaEliminar);

				setZonaRegistralAsociada(lzr_zonaEliminar);
			}
		}

		addMessage(MessagesKeys.REGISTRO_ELIMINADO_CORRECTAMENTE);
		PrimeFaces.current().ajax().update("fAccEntidadExternaConvenioDetalle");
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String returnPages()
	{
		String ls_return;

		ls_return = NavegacionCommon.ACC_ENTIDAD_EXTERNA_CONVENIO;

		clear();

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setParametros(null);
		setAccEntidadExternaConvenio(null);
		setDatosAuditoria(null);
		setEntidadExternaConvenio(null);
		setInsertar(false);
		setNombreEntidad(null);
		setNumeroConvenio(null);
		setTablaActiva(false);
		setTablaZonas(false);
		setZonaRegistralAsociada(null);
		setZonaRegistralInicial(null);
		setZonaRegistralEliminar(null);
		setInsertarConvenios(false);
	}
}
