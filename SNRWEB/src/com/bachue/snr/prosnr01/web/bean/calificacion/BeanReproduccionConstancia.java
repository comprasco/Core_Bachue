package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.ConstanciaReproduccionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.calificacion.Calificacion;
import com.bachue.snr.prosnr01.model.registro.ConstanciaReproduccion;
import com.bachue.snr.prosnr01.model.registro.DataReproduccionConstancia;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioDireccion;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.DocumentoConstancia;
import com.bachue.snr.prosnr01.model.sdb.bng.ValidacionDocumento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.PrimeFaces.Ajax;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanReproduccionConstancia.
 *
 * @author jpatino
 */
@SessionScoped
@ManagedBean(name = "beanReproduccionConstancia")
public class BeanReproduccionConstancia extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2096507104369954298L;

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fRepConstancia:globalMsg";

	/** Constante is_idForm. */
	private static final String is_idForm = "fRepConstancia";

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad icdrc data reproduccion constancia. */
	private Collection<DataReproduccionConstancia> icdrc_dataReproduccionConstancia;

	/** Propiedad icto tipo oficina documento. */
	private Collection<TipoOficina> icto_tipoOficinaDocumento;

	/** Propiedad irr constancia reproduccion remote. */
	@EJB
	private ConstanciaReproduccionRemote irr_constanciaReproduccionRemote;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad il id motivo. */
	private Long il_idMotivo;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isc imagen. */
	private StreamedContent isc_imagen;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id departamento. */
	private String is_idDepartamento;

	/** Propiedad is id municipio. */
	private String is_idMunicipio;

	/** Propiedad is id oficina origen. */
	private String is_idOficinaOrigen;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is id tipo entidad. */
	private String is_idTipoEntidad;

	/** Propiedad is id tipo oficina. */
	private String is_idTipoOficina;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is turno. */
	private String is_turno;

	/** Propiedad ib envio aprobador. */
	private boolean ib_envioAprobador;

	/** Propiedad ib generar archivo. */
	private boolean ib_generarArchivo;

	/** Propiedad ib modificar valores. */
	private boolean ib_modificarValores;

	/** Propiedad ib pdf generado. */
	private boolean ib_pdfGenerado;

	/** Propiedad ib rendered guardar info constancia. */
	private boolean ib_renderedGuardarInfoConstancia;

	/** Propiedad ib sin constancia reproduccion. */
	private boolean ib_sinConstanciaReproduccion;

	/** Propiedad ib sin constancia reproduccion turno. */
	private boolean ib_sinConstanciaReproduccionTurno;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de bgn documento.
	 *
	 * @param ad_d asigna el valor a la propiedad bgn documento
	 */
	public void setBgnDocumento(Documento ad_d)
	{
		id_documento = ad_d;
	}

	/**
	 * Retorna el valor de bgn documento.
	 *
	 * @return el valor de bgn documento
	 */
	public Documento getBgnDocumento()
	{
		if(id_documento == null)
		{
			String ls_idPais;
			ls_idPais     = getIdPais();

			id_documento = new Documento();

			if(!StringUtils.isValidString(ls_idPais))
				setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return id_documento;
	}

	/**
	 * Modifica el valor de data reproduccion constancia.
	 *
	 * @param as_s asigna el valor a la propiedad data reproduccion constancia
	 */
	public void setDataReproduccionConstancia(Collection<DataReproduccionConstancia> as_s)
	{
		icdrc_dataReproduccionConstancia = as_s;
	}

	/**
	 * Retorna el valor de data reproduccion constancia.
	 *
	 * @return el valor de data reproduccion constancia
	 */
	public Collection<DataReproduccionConstancia> getDataReproduccionConstancia()
	{
		return icdrc_dataReproduccionConstancia;
	}

	/**
	 * Modifica el valor de envio aprobador.
	 *
	 * @param ab_b asigna el valor a la propiedad envio aprobador
	 */
	public void setEnvioAprobador(boolean ab_b)
	{
		ib_envioAprobador = ab_b;
	}

	/**
	 * Valida la propiedad envio aprobador.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en envio aprobador
	 */
	public boolean isEnvioAprobador()
	{
		return ib_envioAprobador;
	}

	/**
	 * Modifica el valor de generar archivo.
	 *
	 * @param ab_b asigna el valor a la propiedad generar archivo
	 */
	public void setGenerarArchivo(boolean ab_b)
	{
		ib_generarArchivo = ab_b;
	}

	/**
	 * Valida la propiedad generar archivo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en generar archivo
	 */
	public boolean isGenerarArchivo()
	{
		return ib_generarArchivo;
	}

	/**
	 * Modifica el valor de id circulo.
	 *
	 * @param ac_c asigna el valor a la propiedad id circulo
	 */
	public void setIdCirculo(String ac_c)
	{
		is_idCirculo = ac_c;
	}

	/**
	 * Retorna el valor de id circulo.
	 *
	 * @return el valor de id circulo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de id departamento.
	 *
	 * @param as_s asigna el valor a la propiedad id departamento
	 */
	public void setIdDepartamento(String as_s)
	{
		is_idDepartamento = as_s;
	}

	/**
	 * Retorna el valor de id departamento.
	 *
	 * @return el valor de id departamento
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de id motivo.
	 *
	 * @param al_l asigna el valor a la propiedad id motivo
	 */
	public void setIdMotivo(Long al_l)
	{
		il_idMotivo = al_l;
	}

	/**
	 * Retorna el valor de id motivo.
	 *
	 * @return el valor de id motivo
	 */
	public Long getIdMotivo()
	{
		return il_idMotivo;
	}

	/**
	 * Modifica el valor de id municipio.
	 *
	 * @param as_s asigna el valor a la propiedad id municipio
	 */
	public void setIdMunicipio(String as_s)
	{
		is_idMunicipio = as_s;
	}

	/**
	 * Retorna el valor de id municipio.
	 *
	 * @return el valor de id municipio
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * Modifica el valor de id oficina origen.
	 *
	 * @param as_s asigna el valor a la propiedad id oficina origen
	 */
	public void setIdOficinaOrigen(String as_s)
	{
		is_idOficinaOrigen = as_s;
	}

	/**
	 * Retorna el valor de id oficina origen.
	 *
	 * @return el valor de id oficina origen
	 */
	public String getIdOficinaOrigen()
	{
		return is_idOficinaOrigen;
	}

	/**
	 * Modifica el valor de id pais.
	 *
	 * @param as_s asigna el valor a la propiedad id pais
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Retorna el valor de id pais.
	 *
	 * @return el valor de id pais
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de id tipo entidad.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo entidad
	 */
	public void setIdTipoEntidad(String as_s)
	{
		is_idTipoEntidad = as_s;
	}

	/**
	 * Retorna el valor de id tipo entidad.
	 *
	 * @return el valor de id tipo entidad
	 */
	public String getIdTipoEntidad()
	{
		return is_idTipoEntidad;
	}

	/**
	 * Modifica el valor de id tipo oficina.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo oficina
	 */
	public void setIdTipoOficina(String as_s)
	{
		is_idTipoOficina = as_s;
	}

	/**
	 * Retorna el valor de id tipo oficina.
	 *
	 * @return el valor de id tipo oficina
	 */
	public String getIdTipoOficina()
	{
		return is_idTipoOficina;
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param ai_i asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String ai_i)
	{
		is_idTurno = ai_i;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param as_s asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de imagen.
	 *
	 * @param asc_sc asigna el valor a la propiedad imagen
	 */
	public void setImagen(StreamedContent asc_sc)
	{
		isc_imagen = asc_sc;
	}

	/**
	 * Retorna el valor de imagen.
	 *
	 * @return el valor de imagen
	 */
	public StreamedContent getImagen()
	{
		return isc_imagen;
	}

	/**
	 * Modifica el valor de modificar valores.
	 *
	 * @param ab_b asigna el valor a la propiedad modificar valores
	 */
	public void setModificarValores(boolean ab_b)
	{
		ib_modificarValores = ab_b;
	}

	/**
	 * Valida la propiedad modificar valores.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en modificar valores
	 */
	public boolean isModificarValores()
	{
		return ib_modificarValores;
	}

	/**
	 * Modifica el valor de nir.
	 *
	 * @param as_s asigna el valor a la propiedad nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna el valor de nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Sets the oficina origen.
	 */
	public void setOficinaOrigen()
	{
	}

	/**
	 * Retorna el valor de oficina origen.
	 *
	 * @return el valor de oficina origen
	 */
	public Collection<OficinaOrigen> getOficinaOrigen()
	{
		Collection<OficinaOrigen> lcidt_datos;

		lcidt_datos = null;

		try
		{
			if(is_idPais != null)
			{
				OficinaOrigen oficina = new OficinaOrigen();
				oficina.setIdTipoOficina(getIdTipoOficina());
				oficina.setIdPais(is_idPais);
				oficina.setIdDepartamento(is_idDepartamento);
				oficina.setIdMunicipio(is_idMunicipio);
				lcidt_datos = irr_referenceRemote.findOficinaOrigen(oficina);
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
	 * Modifica el valor de pdf generado.
	 *
	 * @param pdfGenerado asigna el valor a la propiedad pdf generado
	 */
	public void setPdfGenerado(boolean pdfGenerado)
	{
		this.ib_pdfGenerado = pdfGenerado;
	}

	/**
	 * Valida la propiedad pdf generado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pdf generado
	 */
	public boolean isPdfGenerado()
	{
		return ib_pdfGenerado;
	}

	/**
	 * Modifica el valor de rendered guardar info constancia.
	 *
	 * @param ab_r asigna el valor a la propiedad rendered guardar info constancia
	 */
	public void setRenderedGuardarInfoConstancia(boolean ab_r)
	{
		ib_renderedGuardarInfoConstancia = ab_r;
	}

	/**
	 * Valida la propiedad rendered guardar info constancia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered guardar info constancia
	 */
	public boolean isRenderedGuardarInfoConstancia()
	{
		return ib_renderedGuardarInfoConstancia;
	}

	/**
	 * Modifica el valor de sin constancia reproduccion.
	 *
	 * @param ab_b asigna el valor a la propiedad sin constancia reproduccion
	 */
	public void setSinConstanciaReproduccion(boolean ab_b)
	{
		ib_sinConstanciaReproduccion = ab_b;
	}

	/**
	 * Valida la propiedad sin constancia reproduccion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en sin constancia reproduccion
	 */
	public boolean isSinConstanciaReproduccion()
	{
		return ib_sinConstanciaReproduccion;
	}

	/**
	 * Modifica el valor de sin constancia reproduccion turno.
	 *
	 * @param ab_b asigna el valor a la propiedad sin constancia reproduccion turno
	 */
	public void setSinConstanciaReproduccionTurno(boolean ab_b)
	{
		ib_sinConstanciaReproduccionTurno = ab_b;
	}

	/**
	 * Valida la propiedad sin constancia reproduccion turno.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en sin constancia reproduccion turno
	 */
	public boolean isSinConstanciaReproduccionTurno()
	{
		return ib_sinConstanciaReproduccionTurno;
	}

	/**
	 * Modifica el valor de tipo oficina documento.
	 *
	 * @param acto_to asigna el valor a la propiedad tipo oficina documento
	 */
	public void setTipoOficinaDocumento(Collection<TipoOficina> acto_to)
	{
		icto_tipoOficinaDocumento = acto_to;
	}

	/**
	 * Retorna el valor de tipo oficina documento.
	 *
	 * @return el valor de tipo oficina documento
	 */
	public Collection<TipoOficina> getTipoOficinaDocumento()
	{
		return icto_tipoOficinaDocumento;
	}

	/**
	 * Modifica el valor de turno.
	 *
	 * @param as_s asigna el valor a la propiedad turno
	 */
	public void setTurno(String as_s)
	{
		is_turno = as_s;
	}

	/**
	 * Retorna el valor de turno.
	 *
	 * @return el valor de turno
	 */
	public String getTurno()
	{
		return is_turno;
	}

	/**
	 * Buscar rep constancia documento.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void buscarRepConstanciaDocumento()
	    throws B2BException
	{
		Documento ld_docTemp;
		boolean   lb_focus;
		boolean   lb_indMessage;
		String    ls_message;

		ld_docTemp        = getBgnDocumento();
		lb_focus          = true;
		lb_indMessage     = false;
		ls_message        = MessagesKeys.CONSULTA_EXITOSA;

		if(ld_docTemp != null)
		{
			FacesContext lfc_context;
			lfc_context = FacesContext.getCurrentInstance();

			try
			{
				{
					String ls_idTipoDocumento;
					ls_idTipoDocumento     = ld_docTemp.getIdTipoDocumento();

					lb_focus = validateStyles(
						    ":fRepConstancia:idSOMEscritura", lfc_context, ls_idTipoDocumento, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoDocumento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO);
				}

				{
					String ls_numero;
					ls_numero     = ld_docTemp.getNumero();

					lb_focus = validateStyles(":fRepConstancia:idItDocuActo", lfc_context, ls_numero, lb_focus);

					if(!StringUtils.isValidString(ls_numero))
						throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
				}

				{
					Date ld_fecha;
					ld_fecha     = ld_docTemp.getFechaDocumento();

					lb_focus = validateStyles(":fRepConstancia:idCalFechaDoc", lfc_context, ld_fecha, lb_focus);

					if(ld_fecha == null)
					{
						Object[] aoa_messageArgs = new String[1];
						aoa_messageArgs[0] = " Documento ";
						throw new B2BException(ErrorKeys.ERROR_CAMPO_FECHA_OBLIGATORIO, aoa_messageArgs);
					}
				}

				Collection<DataReproduccionConstancia> lcdrc_tmp;
				Collection<AnotacionPredioDireccion>   lcap_colAnotPred;
				Collection<AnotacionPredioDireccion>   lcap_colFinal;
				DocumentoConstancia                    ld_documento;
				AnotacionPredioDireccion               lap_anotacionPredio;

				ld_documento            = new DocumentoConstancia();
				lap_anotacionPredio     = new AnotacionPredioDireccion();

				lap_anotacionPredio.setIdCirculo(getIdCirculo());

				lcdrc_tmp         = new ArrayList<DataReproduccionConstancia>();
				lcap_colFinal     = new ArrayList<AnotacionPredioDireccion>();

				ld_documento = new DocumentoConstancia();
				ld_documento.setIdTipoOficina(getIdTipoOficina());
				ld_documento.setIdOficinaOrigen(getIdOficinaOrigen());
				ld_documento.setNumero(ld_docTemp.getNumero());
				ld_documento.setIdTipoDocumento(ld_docTemp.getIdTipoDocumento());
				ld_documento.setFechaDocumento(ld_docTemp.getFechaDocumento());

				{
					DataReproduccionConstancia      lsrc_datarepCons;
					Collection<DocumentoConstancia> lcd_documentos;

					lsrc_datarepCons     = new DataReproduccionConstancia();

					lcd_documentos = irr_registroRemote.findDocumentosConstancia(ld_documento, getUserId());

					if(CollectionUtils.isValidCollection(lcd_documentos))
					{
						setSinConstanciaReproduccion(false);

						for(DocumentoConstancia ldc_iterador : lcd_documentos)
						{
							if(ldc_iterador != null)
							{
								lcap_colAnotPred = irr_registroRemote.findAnotacionPredioByRadicacion(
									    lap_anotacionPredio, getUserId(), ldc_iterador
									);

								AnotacionPredioDireccion lapd_temp;
								String                   ls_anotaciones;

								lapd_temp          = null;
								ls_anotaciones     = null;

								if(lcap_colAnotPred != null)
								{
									for(AnotacionPredioDireccion lap_iterador : lcap_colAnotPred)
									{
										if(lap_iterador != null)
										{
											DireccionPredio ldp_direccionPredio;

											ldp_direccionPredio = new DireccionPredio();

											ldp_direccionPredio.setIdCirculo(lap_iterador.getIdCirculo());
											ldp_direccionPredio.setIdMatricula(
											    NumericUtils.getLongWrapper(lap_iterador.getIdMatricula())
											);

											ldp_direccionPredio = irr_registroRemote.findDireccionPredioById(
												    ldp_direccionPredio, getUserId()
												);

											if(ldp_direccionPredio != null)
												lap_iterador.setDireccionPredio(ldp_direccionPredio.getDireccion());

											if(lapd_temp == null)
											{
												lapd_temp          = lap_iterador;
												ls_anotaciones     = String.valueOf(lapd_temp.getIdAnotacion());
											}
											else if(
											    lapd_temp.getIdCirculo().equalsIgnoreCase(lap_iterador.getIdCirculo())
											)
											{
												if(lapd_temp.getIdMatricula() == lap_iterador.getIdMatricula())
													ls_anotaciones = ls_anotaciones + "-"
														+ String.valueOf(lap_iterador.getIdAnotacion());
												else if(!(lapd_temp.getIdMatricula() == lap_iterador.getIdMatricula()))
												{
													lapd_temp.setAnotaciones(ls_anotaciones);
													lcap_colFinal.add(lapd_temp);
													ls_anotaciones     = String.valueOf(lap_iterador.getIdAnotacion());
													lapd_temp          = lap_iterador;
												}
											}
											else if(
											    !lapd_temp.getIdCirculo().equalsIgnoreCase(lap_iterador.getIdCirculo())
											)
											{
												lapd_temp.setAnotaciones(ls_anotaciones);
												lcap_colFinal.add(lapd_temp);
												ls_anotaciones     = String.valueOf(lap_iterador.getIdAnotacion());
												lapd_temp          = lap_iterador;
											}
											else if(lapd_temp.getIdDocumento().equals(lap_iterador.getIdDocumento()))
												ls_anotaciones = ls_anotaciones + "-"
													+ String.valueOf(lap_iterador.getIdAnotacion());
											else if(!lapd_temp.getIdDocumento().equals(lap_iterador.getIdDocumento()))
											{
												lapd_temp.setAnotaciones(ls_anotaciones);
												lcap_colFinal.add(lapd_temp);
												ls_anotaciones     = String.valueOf(lap_iterador.getIdAnotacion());
												lapd_temp          = lap_iterador;
											}
										}
									}
								}

								if(lapd_temp != null)
								{
									lapd_temp.setAnotaciones(ls_anotaciones);
									lapd_temp.setSeleccionado(true);
									lcap_colFinal.add(lapd_temp);
									ls_anotaciones     = null;
									lapd_temp          = null;
								}

								lsrc_datarepCons.setDocumento(ldc_iterador);
								lsrc_datarepCons.setAnotacionPredioDireccion(lcap_colFinal);
							}
						}
					}
					else
					{
						setDataReproduccionConstancia(null);
						setSinConstanciaReproduccion(true);

						throw new B2BException(ErrorKeys.DEBE_REVISAR_DATOS_DOCUMENTO);
					}

					if(!CollectionUtils.isValidCollection(lcap_colFinal))
						lb_indMessage = true;

					lcdrc_tmp.add(lsrc_datarepCons);
					setDataReproduccionConstancia(lcdrc_tmp);
					setRenderedGuardarInfoConstancia(true);
					setIdTurno(null);

					if(lb_indMessage)
						ls_message = MessagesKeys.CONSULTA_EXITOSA_SIN_MATRICULAS;

					addMessage(ls_message);
					PrimeFaces.current().ajax().update(is_messageIdGrowl);
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
				setIdTurno(null);
			}

			{
				Ajax la_ajax;

				la_ajax = PrimeFaces.current().ajax();

				la_ajax.update("fRepConstancia:idItTurnRadicacion");
				la_ajax.update("fRepConstancia:idSOMCirculoRegistralAntSistemaConstancia");
				la_ajax.update("fRepConstancia:idSOMEscritura");
				la_ajax.update("fRepConstancia:idItDocuActo");
				la_ajax.update("fRepConstancia:idCalFechaDoc");
				la_ajax.update("fRepConstancia:idSOMTipoOficina");
				la_ajax.update("fRepConstancia:idSOMTipoEntidad");
				la_ajax.update("fRepConstancia:idEntidadExenta");
				la_ajax.update("fRepConstancia:idPaisDocumento");
				la_ajax.update("fRepConstancia:idSOMDepartamento");
				la_ajax.update("fRepConstancia:idSOMMunicipio");
				la_ajax.update("fRepConstancia:idSOMOficinaOrigen");
				la_ajax.update("fRepConstancia:idDtListadoDocumentos");
				la_ajax.update("fRepConstancia:cbTerminarConstanciaRep");
				la_ajax.update("fRepConstancia:cbGuardarConstanciaRep");
				la_ajax.update("fRepConstancia:opPanelTerminarProcesoConstanciaRep");
			}
		}
	}

	/**
	 * Buscar rep constancia turno.
	 */
	public void buscarRepConstanciaTurno()
	{
		try
		{
			boolean lb_focus;
			String  ls_idTurnoRepConstancia;

			lb_focus                    = true;
			ls_idTurnoRepConstancia     = getIdTurno();

			FacesContext lfc_context;
			lfc_context                 = FacesContext.getCurrentInstance();

			{
				lb_focus = validateStyles(
					    ":fRepConstancia:idItTurnRadicacion", lfc_context, ls_idTurnoRepConstancia, lb_focus
					);
			}

			if(StringUtils.isValidString(ls_idTurnoRepConstancia))
			{
				Collection<DataReproduccionConstancia> lcdrc_tmp;
				AnotacionPredioDireccion               lap_anotacionPredio;

				lap_anotacionPredio = new AnotacionPredioDireccion();

				lap_anotacionPredio.setRadicacion(ls_idTurnoRepConstancia);

				lcdrc_tmp = irr_registroRemote.findAnotacionPredioByRadicacion(lap_anotacionPredio, getUserId());

				if(CollectionUtils.isValidCollection(lcdrc_tmp))
				{
					setDataReproduccionConstancia(lcdrc_tmp);
					setRenderedGuardarInfoConstancia(true);
				}
				else
				{
					setDataReproduccionConstancia(null);
					setSinConstanciaReproduccionTurno(true);
					throw new B2BException(ErrorKeys.DEBE_REVISAR_DATOS_TURNO);
				}

				addMessage(MessagesKeys.CONSULTA_EXITOSA);
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
				setBgnDocumento(null);
			}
			else
			{
				setDataReproduccionConstancia(null);
				throw new B2BException(ErrorKeys.DEBE_REVISAR_DATOS_TURNO);
			}

			{
				Ajax la_ajax;

				la_ajax = PrimeFaces.current().ajax();

				la_ajax.update("fRepConstancia:idItTurnRadicacion");
				la_ajax.update("fRepConstancia:idSOMCirculoRegistralAntSistemaConstancia");
				la_ajax.update("fRepConstancia:idSOMEscritura");
				la_ajax.update("fRepConstancia:idItDocuActo");
				la_ajax.update("fRepConstancia:idCalFechaDoc");
				la_ajax.update("fRepConstancia:idSOMTipoOficina");
				la_ajax.update("fRepConstancia:idSOMTipoEntidad");
				la_ajax.update("fRepConstancia:idEntidadExenta");
				la_ajax.update("fRepConstancia:idPaisDocumento");
				la_ajax.update("fRepConstancia:idSOMDepartamento");
				la_ajax.update("fRepConstancia:idSOMMunicipio");
				la_ajax.update("fRepConstancia:idSOMOficinaOrigen");
				la_ajax.update("fRepConstancia:idDtListadoDocumentos");
				la_ajax.update("fRepConstancia:cbTerminarConstanciaRep");
				la_ajax.update("fRepConstancia:cbGuardarConstanciaRep");
				la_ajax.update("fRepConstancia:opPanelTerminarProcesoConstanciaRep");
			}
		}
		catch(B2BException lb2be_e)
		{
			if(
			    StringUtils.getStringNotNull(lb2be_e.getMessageKey())
				               .equalsIgnoreCase("errorSinReproduccionConstanciaTurno")
			)
				setSinConstanciaReproduccionTurno(true);

			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String enviarAprobador()
	    throws B2BException
	{
		Calificacion lc_c;
		String       ls_return;
		lc_c          = new Calificacion();
		ls_return     = NavegacionCommon.CALIFICACION;

		try
		{
			lc_c.setIdMotivo(getIdMotivo());
			lc_c.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			lc_c.setIdUsuarioModificacion(getUserId());
			lc_c.setIpModificacion(getRemoteIpAddress());

			irr_calificacionRemote.enviarAprobadorRepConstancia(
			    lc_c, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentos()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			if(this.is_idPais != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(this.is_idPais);

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Prepara la pantalla para cargar los municipios según el departamento seleccionado
	 */
	public void departamento()
	{
		setIdMunicipio(null);
		findMunicipios();
	}

	/**
	 * Prepara la pantalla para cargar las oficinas origen según el municipio seleccionado
	 */
	public void oficinaOrigen()
	{
		setIdOficinaOrigen(null);
		findOficinasOrigen();
	}

	/**
	 * Busca las oficinas origen disponibles dependiendo del departamento y municipio seleccionado
	 *
	 * @return Colección resultante de la consulta
	 */
	public Collection<OficinaOrigen> findOficinasOrigen()
	{
		Collection<OficinaOrigen> lcoo_return;

		lcoo_return = null;

		if((is_idPais != null) && (is_idDepartamento != null) && (is_idMunicipio != null) && (is_idTipoOficina != null))
		{
			try
			{
				OficinaOrigen loo_oficinaOrigen;

				loo_oficinaOrigen = new OficinaOrigen();

				loo_oficinaOrigen.setIdTipoOficina(is_idTipoOficina);
				loo_oficinaOrigen.setIdPais(is_idPais);
				loo_oficinaOrigen.setIdDepartamento(is_idDepartamento);
				loo_oficinaOrigen.setIdMunicipio(is_idMunicipio);

				lcoo_return = irr_referenceRemote.findOficinaOrigen(loo_oficinaOrigen);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return lcoo_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipios()
	{
		Collection<Municipio> lcm_municipios;
		lcm_municipios = null;

		if((this.is_idPais != null) && (this.is_idDepartamento != null))
		{
			try
			{
				Municipio lm_parametros;
				lm_parametros = new Municipio();

				lm_parametros.setIdPais(this.is_idPais);
				lm_parametros.setIdDepartamento(this.is_idDepartamento);

				lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				lcm_municipios = null;
			}
		}

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoOficina> findTipoOficina()
	{
		Collection<TipoOficina> lcidt_datos;

		lcidt_datos = new ArrayList<TipoOficina>();

		try
		{
			Documento lod_d;

			lod_d     = getBgnDocumento();

			lcidt_datos = irr_referenceRemote.findTipoOficina(lod_d);

			if(CollectionUtils.isValidCollection(lcidt_datos))
			{
				if(lcidt_datos.size() == 1)
				{
					for(TipoOficina loto_tmp : lcidt_datos)
					{
						if(loto_tmp != null)
							setIdTipoOficina(loto_tmp.getIdTipoOficina());
					}
				}
				else
					setIdTipoOficina(null);

				setTipoOficinaDocumento(lcidt_datos);
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
	 * Generate file.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generateFile()
	    throws B2BException
	{
		try
		{
			byte[]                 lb_archivo;
			ConstanciaReproduccion lcr_tmp;
			TurnoHistoria          lth_th;
			lcr_tmp     = new ConstanciaReproduccion();
			lth_th      = new TurnoHistoria();

			lth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

			lcr_tmp.setIdCirculo(getIdCirculo());
			lcr_tmp.setTurnoHistoria(lth_th);

			lb_archivo = irr_constanciaReproduccionRemote.generarConstanciaInscripcionRepConstancia(
				    lcr_tmp, false, getUserId(), getRemoteIpAddress()
				);

			if(lb_archivo != null)
			{
				InputStream lis_stream;
				lis_stream = new ByteArrayInputStream(lb_archivo);

				setImagen(
				    new DefaultStreamedContent(
				        lis_stream, TipoContenidoCommon.PDF,
				        ConstanteCommon.NOMBRE_ARCHIVO_REPRODUCCION_CONSTANCIA + ExtensionCommon.PDF_PUNTO
				    )
				);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);

			setEnvioAprobador(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Guardar info reproduccion.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarInfoReproduccion()
	    throws B2BException
	{
		try
		{
			Collection<DataReproduccionConstancia> lcdrc_dataRepCons;
			DataReproduccionConstancia             ldrc_parametros;

			lcdrc_dataRepCons     = getDataReproduccionConstancia();
			ldrc_parametros       = new DataReproduccionConstancia();

			ldrc_parametros.setDataReproduccionConstancia(lcdrc_dataRepCons);
			ldrc_parametros.setIdTurnoHistoria(getIdTurnoHistoria());

			ldrc_parametros = irr_calificacionRemote.salvarReproduccionConstanciaCalificador(
				    ldrc_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lcdrc_dataRepCons))
			{
				for(DataReproduccionConstancia ldrc_iterador : lcdrc_dataRepCons)
				{
					if(ldrc_iterador != null)
					{
						Collection<AnotacionPredioDireccion> lcapd_anotacionPredioDireccion;
						lcapd_anotacionPredioDireccion = ldrc_iterador.getAnotacionPredioDireccion();

						if(CollectionUtils.isValidCollection(lcapd_anotacionPredioDireccion))
						{
							for(AnotacionPredioDireccion lapd_iterador : lcapd_anotacionPredioDireccion)
							{
								if(lapd_iterador != null)
									lapd_iterador.setBloquearSeleccionado(true);
							}
						}
					}
				}
			}

			setRenderedGuardarInfoConstancia(false);
			setGenerarArchivo(true);

			addMessage(MessagesKeys.MODIFICACION_EXITOSA);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		limpiarRepConstanciaDocumento();
		limpiarRepConstanciaTurno();
		setIdTurnoHistoria(null);
		setIdCirculo(null);
		setIdMotivo(null);
		setIdTurno(null);
		setNir(null);
		setDataReproduccionConstancia(null);
		setTipoOficinaDocumento(null);
		setIdDepartamento(null);
		setIdMunicipio(null);
		setIdPais(null);
		setEnvioAprobador(false);
		setGenerarArchivo(false);
	}

	/**
	 * Limpiar rep constancia documento.
	 */
	public void limpiarRepConstanciaDocumento()
	{
		Ajax la_ajax;

		setBgnDocumento(null);
		setIdTipoOficina(null);
		setIdDepartamento(null);
		setIdMunicipio(null);
		setDataReproduccionConstancia(null);
		la_ajax = PrimeFaces.current().ajax();

		la_ajax.update("fRepConstancia:idItTurnRadicacion");
		la_ajax.update("fRepConstancia:idSOMCirculoRegistralAntSistemaConstancia");
		la_ajax.update("fRepConstancia:idSOMEscritura");
		la_ajax.update("fRepConstancia:idItDocuActo");
		la_ajax.update("fRepConstancia:idCalFechaDoc");
		la_ajax.update("fRepConstancia:idSOMTipoOficina");
		la_ajax.update("fRepConstancia:idSOMTipoEntidad");
		la_ajax.update("fRepConstancia:idEntidadExenta");
		la_ajax.update("fRepConstancia:idPaisDocumento");
		la_ajax.update("fRepConstancia:idSOMDepartamento");
		la_ajax.update("fRepConstancia:idSOMMunicipio");
		la_ajax.update("fRepConstancia:idSOMOficinaOrigen");
		la_ajax.update("fRepConstancia:idDtListadoDocumentos");
		la_ajax.update(is_messageIdGrowl);
		la_ajax.update(is_idForm);
	}

	/**
	 * Limpiar rep constancia turno.
	 */
	public void limpiarRepConstanciaTurno()
	{
		Ajax la_ajax;

		setDataReproduccionConstancia(null);
		setIdTurno(null);
		la_ajax = PrimeFaces.current().ajax();

		la_ajax.update("fRepConstancia:idItTurnRadicacion");
		la_ajax.update("fRepConstancia:idSOMCirculoRegistralAntSistemaConstancia");
		la_ajax.update("fRepConstancia:idSOMEscritura");
		la_ajax.update("fRepConstancia:idItDocuActo");
		la_ajax.update("fRepConstancia:idCalFechaDoc");
		la_ajax.update("fRepConstancia:idSOMTipoOficina");
		la_ajax.update("fRepConstancia:idSOMTipoEntidad");
		la_ajax.update("fRepConstancia:idEntidadExenta");
		la_ajax.update("fRepConstancia:idPaisDocumento");
		la_ajax.update("Registro:idSOMDepartamento");
		la_ajax.update("fRepConstancia:idSOMMunicipio");
		la_ajax.update("fRepConstancia:idSOMOficinaOrigen");
		la_ajax.update("fRepConstancia:idDtListadoDocumentos");
		la_ajax.update(is_messageIdGrowl);
	}

	/**
	 * Modificar valores.
	 */
	public void modificarValores()
	{
		try
		{
			if(!CollectionUtils.isValidCollection(getDataReproduccionConstancia()))
				throw new B2BException(ErrorKeys.ERROR_NO_AGREGO_REGISTROS);

			setModificarValores(true);
			PrimeFaces.current().executeScript("PF('dlgRepConst').show();");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Pais.
	 */
	public void pais()
	{
		setIdDepartamento(null);
		setIdMunicipio(null);
		findDepartamentos();
		findMunicipios();
		getOficinaOrigen();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String regresar()
	    throws B2BException
	{
		BeanPredioDocumentoActo lbpd_pda;
		FacesContext            lfc_context;

		lfc_context     = FacesUtils.getFacesContext();
		lbpd_pda        = (BeanPredioDocumentoActo)lfc_context.getApplication()
				                                                  .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
				);

		if(lbpd_pda != null)
		{
			lbpd_pda.setOcultarPaneles(false);
			lbpd_pda.setIdTurnoHistoria(getIdTurnoHistoria());
			lbpd_pda.setDocumentos(null);
			lbpd_pda.generarDatosDocumento();

			limpiar();
		}

		return NavegacionCommon.DETALLE_REPRODUCCION_CONSTANCIA;
	}

	/**
	 * Retorna el valor del objeto de ValidacionDocumento.
	 *
	 * @param ab_mensaje correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de ValidacionDocumento
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public ValidacionDocumento validarExistenciaDocumento(boolean ab_mensaje)
	    throws B2BException
	{
		ValidacionDocumento lb_retorno;

		lb_retorno = null;

		try
		{
			Documento ld_documento;
			ld_documento = getBgnDocumento();

			if(ld_documento != null)
			{
				ld_documento.setIdTipoOficina(getIdTipoOficina());
				ld_documento.setIdOficinaOrigen(getIdOficinaOrigen());

				lb_retorno = irr_registroRemote.validarExistenciaDocumento(ld_documento);

				if(lb_retorno != null)
				{
					if(lb_retorno.isValidacion() && ab_mensaje)
					{
						Collection<String> lcs_nirs;

						lcs_nirs = lb_retorno.getNirs();

						if(CollectionUtils.isValidCollection(lcs_nirs))
						{
							int           li_contador;
							int           li_tam;
							StringBuilder lsb_mensaje;
							String        ls_comaPunto;

							li_contador     = 1;
							li_tam          = lcs_nirs.size();
							lsb_mensaje     = new StringBuilder();

							for(String ls_iterador : lcs_nirs)
							{
								if(StringUtils.isValidString(ls_iterador))
								{
									ls_comaPunto = (li_contador == li_tam) ? IdentificadoresCommon.PUNTO : ",\n";
									lsb_mensaje.append(ls_iterador + ls_comaPunto);

									li_contador++;
								}
							}

							throw new B2BException(
							    "Los datos ingresados del documento, ya existen en la solicitud con NIR: "
							    + lsb_mensaje.toString()
							);
						}
					}
				}
			}
		}
		catch(B2BException lb2b_e)
		{
			addMessage(lb2b_e);
		}

		return lb_retorno;
	}

	/**
	 * Validar fecha anterior.
	 */
	public void validarFechaAnterior()
	{
		Documento ld_documento;
		Calendar  lc_calendar;
		Date      ld_d;
		int       li_meses;
		String    ls_idPais;

		ld_documento     = getBgnDocumento();
		lc_calendar      = Calendar.getInstance();
		ld_d             = new Date();
		li_meses         = -2;
		ls_idPais        = getIdPais();

		try
		{
			if(ld_documento != null)
			{
				Date ld_fechaDoc;
				ld_fechaDoc = ld_documento.getFechaDocumento();

				if(ld_fechaDoc == null)
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);

				if(ld_fechaDoc.after(new Date()))
				{
					Object[] aoa_messageArgs = new String[1];
					aoa_messageArgs[0] = " del documento ";
					throw new B2BException(ErrorKeys.FECHA_SUPERIOR_ACTUAL, aoa_messageArgs);
				}

				if(
				    StringUtils.isValidString(ls_idPais)
					    && !ls_idPais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
				)
					li_meses = -3;

				lc_calendar.setTime(ld_d);
				lc_calendar.add(Calendar.MONTH, li_meses);
				ld_d = lc_calendar.getTime();

				String ls_mensaje;
				ls_mensaje = null;

				if(ld_fechaDoc.before(ld_d))
				{
					ls_mensaje = "El documento que se pretende registrar  ha superado  el  termino de dos  meses para su  radicación y/o calificación.";
					addMessage("I", ls_mensaje);
					PrimeFaces.current().ajax().update(is_messageIdGrowl);
				}
			}
		}
		catch(B2BException lb2b_e)
		{
			addMessage(lb2b_e);
		}
	}
}
