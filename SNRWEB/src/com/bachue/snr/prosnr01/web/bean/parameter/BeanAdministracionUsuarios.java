package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.messages.Messages;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioCirculo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.UsuarioLinea;
import com.bachue.snr.prosnr01.model.sdb.png.UsuarioEtapa;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

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

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para la administracion de usuarios.
 *
 * @author Jorge Patiño
 */
@ManagedBean(name = "beanAdministracionUsuarios")
@SessionScoped
public class BeanAdministracionUsuarios extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6121089090820073798L;

	/** Propiedad icu usuarios bandeja. */
	private Collection<Usuario> icu_usuariosBandeja;

	/** Propiedad icu usuarios coleccion. */
	private Collection<Usuario> icu_usuariosColeccion;

	/** Propiedad icuc circulos activos por usuario. */
	private Collection<UsuarioCirculo> icuc_circulosActivosPorUsuario;

	/** Propiedad icuc circulos por usuario. */
	private Collection<UsuarioCirculo> icuc_circulosPorUsuario;

	/** Propiedad icue etapas activas por usuario. */
	private Collection<UsuarioEtapa> icue_etapasActivasPorUsuario;

	/** Propiedad icue etapas por usuario. */
	private Collection<UsuarioEtapa> icue_etapasPorUsuario;

	/** Propiedad icul all lineas por usuario. */
	private Collection<UsuarioLinea> icul_allLineasPorUsuario;

	/** Propiedad icul lineas activas por usuario. */
	private Collection<UsuarioLinea> icul_lineasActivasPorUsuario;

	/** Propiedad icul lineas por usuario. */
	private Collection<UsuarioLinea> icul_lineasPorUsuario;

	/** Propiedad im messages. */
	private Messages im_messages;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is id usuario actualizar. */
	private String is_idUsuarioActualizar;

	/** Propiedad iu usuario. */
	private Usuario iu_usuario;

	/** Propiedad iuc usuario circulo. */
	private UsuarioCirculo iuc_usuarioCirculo;

	/** Propiedad iue usuario etapa. */
	private UsuarioEtapa iue_usuarioEtapa;

	/** Propiedad iul usuario linea. */
	private UsuarioLinea iul_usuarioLinea;

	/** Propiedad ib boton regresar. */
	private boolean ib_botonRegresar;

	/** Propiedad ib circulo seleccionado. */
	private boolean ib_circuloSeleccionado;

	/** Propiedad ib doc nit. */
	private boolean ib_docNit;

	/** Propiedad ib insert. */
	private boolean ib_insert;

	/** Propiedad ib insertar actualizar. */
	private boolean ib_insertarActualizar;

	/** Propiedad ib linea seleccionado. */
	private boolean ib_lineaSeleccionado;

	/** Propiedad ib renderizar boton regresar. */
	private boolean ib_renderizarBotonRegresar;

	/** Propiedad ib validacion documento. */
	private boolean ib_validacionDocumento;

	/** Propiedad lb etapa seleccionado. */
	private boolean lb_etapaSeleccionado;

	/** Propiedad lb modificar. */
	private boolean lb_modificar;

	/** Propiedad ii seleccionado. */
	private int ii_seleccionado;

	/**
	 * Modifica el valor de all lineas por usuario.
	 *
	 * @param ac_a asigna el valor a la propiedad all lineas por usuario
	 */
	public void setAllLineasPorUsuario(Collection<UsuarioLinea> ac_a)
	{
		icul_allLineasPorUsuario = ac_a;
	}

	/**
	 * Retorna el valor de all lineas por usuario.
	 *
	 * @return el valor de all lineas por usuario
	 */
	public Collection<UsuarioLinea> getAllLineasPorUsuario()
	{
		return icul_allLineasPorUsuario;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de circulo seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad circulo seleccionado
	 */
	public void setCirculoSeleccionado(boolean ab_b)
	{
		if(ab_b)
		{
			setCirculosPorUsuario(findCirculosPorUsuario());
			setModificar(false);
		}

		ib_circuloSeleccionado = ab_b;
	}

	/**
	 * Valida la propiedad circulo seleccionado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en circulo seleccionado
	 */
	public boolean isCirculoSeleccionado()
	{
		return ib_circuloSeleccionado;
	}

	/**
	 * Modifica el valor de circulos activos por usuario.
	 *
	 * @param acuc_cuc asigna el valor a la propiedad circulos activos por usuario
	 */
	public void setCirculosActivosPorUsuario(Collection<UsuarioCirculo> acuc_cuc)
	{
		icuc_circulosActivosPorUsuario = acuc_cuc;
	}

	/**
	 * Retorna el valor de circulos activos por usuario.
	 *
	 * @return el valor de circulos activos por usuario
	 */
	public Collection<UsuarioCirculo> getCirculosActivosPorUsuario()
	{
		if(!CollectionUtils.isValidCollection(icuc_circulosActivosPorUsuario))
			icuc_circulosActivosPorUsuario = findCirculosPorUsuarioActivo();

		return icuc_circulosActivosPorUsuario;
	}

	/**
	 * Modifica el valor de circulos por usuario.
	 *
	 * @param acul_cul asigna el valor a la propiedad circulos por usuario
	 */
	public void setCirculosPorUsuario(Collection<UsuarioCirculo> acul_cul)
	{
		icuc_circulosPorUsuario = acul_cul;
	}

	/**
	 * Retorna el valor de circulos por usuario.
	 *
	 * @return el valor de circulos por usuario
	 */
	public Collection<UsuarioCirculo> getCirculosPorUsuario()
	{
		return icuc_circulosPorUsuario;
	}

	/**
	 * Modifica el valor de doc nit.
	 *
	 * @param ab_b asigna el valor a la propiedad doc nit
	 */
	public void setDocNit(boolean ab_b)
	{
		ib_docNit = ab_b;
	}

	/**
	 * Valida la propiedad doc nit.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en doc nit
	 */
	public boolean isDocNit()
	{
		return ib_docNit;
	}

	/**
	 * Modifica el valor de etapa seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad etapa seleccionado
	 */
	public void setEtapaSeleccionado(boolean ab_b)
	{
		if(ab_b)
		{
			setEtapasPorUsuario(findEtapasPorUsuario());
			setModificar(false);
		}

		lb_etapaSeleccionado = ab_b;
	}

	/**
	 * Valida la propiedad etapa seleccionado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en etapa seleccionado
	 */
	public boolean isEtapaSeleccionado()
	{
		return lb_etapaSeleccionado;
	}

	/**
	 * Modifica el valor de etapas activas por usuario.
	 *
	 * @param acue_cue asigna el valor a la propiedad etapas activas por usuario
	 */
	public void setEtapasActivasPorUsuario(Collection<UsuarioEtapa> acue_cue)
	{
		icue_etapasActivasPorUsuario = acue_cue;
	}

	/**
	 * Retorna el valor de etapas activas por usuario.
	 *
	 * @return el valor de etapas activas por usuario
	 */
	public Collection<UsuarioEtapa> getEtapasActivasPorUsuario()
	{
		setEtapasActivasPorUsuario(findEtapasPorUsuarioActivo());

		return icue_etapasActivasPorUsuario;
	}

	/**
	 * Modifica el valor de etapas por usuario.
	 *
	 * @param acul_cul asigna el valor a la propiedad etapas por usuario
	 */
	public void setEtapasPorUsuario(Collection<UsuarioEtapa> acul_cul)
	{
		icue_etapasPorUsuario = acul_cul;
	}

	/**
	 * Retorna el valor de etapas por usuario.
	 *
	 * @return el valor de etapas por usuario
	 */
	public Collection<UsuarioEtapa> getEtapasPorUsuario()
	{
		return icue_etapasPorUsuario;
	}

	/**
	 * Modifica el valor de id usuario actualizar.
	 *
	 * @param as_idUsuarioActualizar asigna el valor a la propiedad id usuario actualizar
	 */
	public void setIdUsuarioActualizar(String as_idUsuarioActualizar)
	{
		is_idUsuarioActualizar = as_idUsuarioActualizar;
	}

	/**
	 * Retorna el valor de id usuario actualizar.
	 *
	 * @return el valor de id usuario actualizar
	 */
	public String getIdUsuarioActualizar()
	{
		return is_idUsuarioActualizar;
	}

	/**
	 * Modifica el valor de insert.
	 *
	 * @param insert asigna el valor a la propiedad insert
	 */
	public void setInsert(boolean insert)
	{
		this.ib_insert = insert;
	}

	/**
	 * Valida la propiedad insert.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en insert
	 */
	public boolean isInsert()
	{
		return ib_insert;
	}

	/**
	 * Modifica el valor de insert update.
	 *
	 * @param insertUpdate asigna el valor a la propiedad insert update
	 */
	public void setInsertUpdate(boolean insertUpdate)
	{
		this.ib_insertarActualizar = insertUpdate;
	}

	/**
	 * Valida la propiedad insert update.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en insert update
	 */
	public boolean isInsertUpdate()
	{
		return ib_insertarActualizar;
	}

	/**
	 * Modifica el valor de linea seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad linea seleccionado
	 */
	public void setLineaSeleccionado(boolean ab_b)
	{
		if(ab_b)
		{
			setAllLineasPorUsuario(findLineasPorUsuario());
			setModificar(false);
		}

		ib_lineaSeleccionado = ab_b;
	}

	/**
	 * Valida la propiedad linea seleccionado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en linea seleccionado
	 */
	public boolean isLineaSeleccionado()
	{
		return ib_lineaSeleccionado;
	}

	/**
	 * Modifica el valor de lineas activas por usuario.
	 *
	 * @param acul_cul asigna el valor a la propiedad lineas activas por usuario
	 */
	public void setLineasActivasPorUsuario(Collection<UsuarioLinea> acul_cul)
	{
		icul_lineasActivasPorUsuario = acul_cul;
	}

	/**
	 * Retorna el valor de lineas activas por usuario.
	 *
	 * @return el valor de lineas activas por usuario
	 */
	public Collection<UsuarioLinea> getLineasActivasPorUsuario()
	{
		setLineasActivasPorUsuario(findLineasPorUsuarioActivo());

		return icul_lineasActivasPorUsuario;
	}

	/**
	 * Modifica el valor de lineas por usuario.
	 *
	 * @param acul_cul asigna el valor a la propiedad lineas por usuario
	 */
	public void setLineasPorUsuario(Collection<UsuarioLinea> acul_cul)
	{
		icul_lineasPorUsuario = acul_cul;
	}

	/**
	 * Retorna el valor de lineas por usuario.
	 *
	 * @return el valor de lineas por usuario
	 */
	public Collection<UsuarioLinea> getLineasPorUsuario()
	{
		return icul_lineasPorUsuario;
	}

	/**
	 * Modifica el valor de modificar.
	 *
	 * @param ab_b asigna el valor a la propiedad modificar
	 */
	public void setModificar(boolean ab_b)
	{
		lb_modificar = ab_b;
	}

	/**
	 * Valida la propiedad modificar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en modificar
	 */
	public boolean isModificar()
	{
		return lb_modificar;
	}

	/**
	 * Modifica el valor de rendered.
	 *
	 * @param ab_b asigna el valor a la propiedad rendered
	 */
	public void setRendered(boolean ab_b)
	{
		ib_botonRegresar = ab_b;
	}

	/**
	 * Valida la propiedad rendered.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered
	 */
	public boolean isRendered()
	{
		return ib_botonRegresar;
	}

	/**
	 * Sets the renderizar boton regresar.
	 */
	public void setRenderizarBotonRegresar()
	{
		int li_seleccion;
		li_seleccion     = getSeleccionado();

		ib_renderizarBotonRegresar = true;

		if(li_seleccion != 0)
			ib_renderizarBotonRegresar = false;

		setModificar(false);
		PrimeFaces.current().ajax().update("fAdministracionUsuariosDetalle");
	}

	/**
	 * Valida la propiedad renderizar boton regresar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en renderizar boton regresar
	 */
	public boolean isRenderizarBotonRegresar()
	{
		return ib_renderizarBotonRegresar;
	}

	/**
	 * Modifica el valor de seleccionado.
	 *
	 * @param ai_i asigna el valor a la propiedad seleccionado
	 */
	public void setSeleccionado(int ai_i)
	{
		ii_seleccionado = ai_i;
	}

	/**
	 * Retorna el valor de seleccionado.
	 *
	 * @return el valor de seleccionado
	 */
	public int getSeleccionado()
	{
		return ii_seleccionado;
	}

	/**
	 * Modifica el valor de usuario.
	 *
	 * @param au_usuario asigna el valor a la propiedad usuario
	 */
	public void setUsuario(Usuario au_usuario)
	{
		if(au_usuario != null)
			iu_usuario = au_usuario;
		else
			iu_usuario = new Usuario();
	}

	/**
	 * Retorna el valor de usuario.
	 *
	 * @return el valor de usuario
	 */
	public Usuario getUsuario()
	{
		return iu_usuario;
	}

	/**
	 * Modifica el valor de usuario circulo.
	 *
	 * @param acr_cr asigna el valor a la propiedad usuario circulo
	 */
	public void setUsuarioCirculo(UsuarioCirculo acr_cr)
	{
		iuc_usuarioCirculo = acr_cr;
	}

	/**
	 * Retorna el valor de usuario circulo.
	 *
	 * @return el valor de usuario circulo
	 */
	public UsuarioCirculo getUsuarioCirculo()
	{
		if(iuc_usuarioCirculo == null)
		{
			UsuarioCirculo lul_temp;
			lul_temp = new UsuarioCirculo();
			lul_temp.setUsuario(getUsuario());
			setUsuarioCirculo(lul_temp);
		}

		return iuc_usuarioCirculo;
	}

	/**
	 * Modifica el valor de usuario etapa.
	 *
	 * @param acr_cr asigna el valor a la propiedad usuario etapa
	 */
	public void setUsuarioEtapa(UsuarioEtapa acr_cr)
	{
		iue_usuarioEtapa = acr_cr;
	}

	/**
	 * Retorna el valor de usuario etapa.
	 *
	 * @return el valor de usuario etapa
	 */
	public UsuarioEtapa getUsuarioEtapa()
	{
		return iue_usuarioEtapa;
	}

	/**
	 * Modifica el valor de usuario linea.
	 *
	 * @param acr_cr asigna el valor a la propiedad usuario linea
	 */
	public void setUsuarioLinea(UsuarioLinea acr_cr)
	{
		iul_usuarioLinea = acr_cr;
	}

	/**
	 * Retorna el valor de usuario linea.
	 *
	 * @return el valor de usuario linea
	 */
	public UsuarioLinea getUsuarioLinea()
	{
		return iul_usuarioLinea;
	}

	/**
	 * Modifica el valor de usuarios bandeja.
	 *
	 * @param acu_cu asigna el valor a la propiedad usuarios bandeja
	 */
	public void setUsuariosBandeja(Collection<Usuario> acu_cu)
	{
		icu_usuariosBandeja = acu_cu;
	}

	/**
	 * Retorna el valor de usuarios bandeja.
	 *
	 * @return el valor de usuarios bandeja
	 */
	public Collection<Usuario> getUsuariosBandeja()
	{
		return icu_usuariosBandeja;
	}

	/**
	 * Modifica el valor de usuarios coleccion.
	 *
	 * @param acu_cu asigna el valor a la propiedad usuarios coleccion
	 */
	public void setUsuariosColeccion(Collection<Usuario> acu_cu)
	{
		if(CollectionUtils.isValidCollection(acu_cu))
			icu_usuariosColeccion = acu_cu;
		else
			icu_usuariosColeccion = new ArrayList<Usuario>();
	}

	/**
	 * Retorna el valor de usuarios coleccion.
	 *
	 * @return el valor de usuarios coleccion
	 */
	public Collection<Usuario> getUsuariosColeccion()
	{
		return icu_usuariosColeccion;
	}

	/**
	 * Modifica el valor de validacion documento.
	 *
	 * @param ab_b asigna el valor a la propiedad validacion documento
	 */
	public void setValidacionDocumento(boolean ab_b)
	{
		ib_validacionDocumento = ab_b;
	}

	/**
	 * Valida la propiedad validacion documento.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en validacion documento
	 */
	public boolean isValidacionDocumento()
	{
		return ib_validacionDocumento;
	}

	/**
	 * Controla la accion del boton actualizar, cargando en pantalla los datos del
	 * usuario seleccionado.
	 *
	 * @param as_userId            es el id del usuario seleccionado en pantalla
	 * @return String que contiene la direccion de la pagina de actualizar usuarios
	 */
	public String botonActualizar(String as_userId)
	{
		Usuario lcu_user;

		lcu_user = new Usuario();

		lcu_user.setIdUsuario(as_userId);

		setUsuario(lcu_user);

		consultarUsuarios();

		setAllLineasPorUsuario(findLineasPorUsuario());

		findAllLineasProduccion();

		setUsuario(getUsuariosColeccion().iterator().next());

		setIdUsuarioActualizar(getUsuario().getIdUsuario());

		addMessage(MessagesKeys.EDITAR_CAMPOS);
		PrimeFaces.current().ajax().update("fAdministracionUsuariosDetalle:gUserInsertUpdate");

		setIdUsuarioActualizar(null);

		return NavegacionCommon.ADMINISTRACION_USUARIOS_DETALLE;
	}

	/**
	 * Limpia todas las variables de instancia que puedan contener informacion de
	 * algun proceso realizado.
	 *
	 * @return String que contiene la direccion de la pagina principal de la
	 *         pantalla de usuarios
	 */
	public String clean()
	{
		setUsuario(null);
		setUsuarioCirculo(null);
		setUsuarioEtapa(null);
		setUsuarioLinea(null);
		setUsuariosColeccion(null);
		setIdUsuarioActualizar(null);
		setRendered(false);
		setInsertUpdate(false);
		setCirculoSeleccionado(false);
		setEtapaSeleccionado(false);
		setLineaSeleccionado(false);
		setSeleccionado(0);
		setRenderizarBotonRegresar();
		setAllLineasPorUsuario(null);
		setCirculosActivosPorUsuario(null);
		setModificar(false);

		return NavegacionCommon.ADMINISTRACION_USUARIOS;
	}

	/**
	 * Consulta la informacion completa del usuario que se seleccionó en pantalla y
	 * la almacena en la coleccion icu_usuariosColeccion.
	 */
	public void consultarUsuarios()
	{
		boolean             lb_focus;
		Collection<Usuario> lcu_usuario;
		FacesContext        lfc_context;

		lcu_usuario     = new ArrayList<Usuario>();
		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		setUsuariosColeccion(null);

		try
		{
			Usuario lu_usuario;

			lu_usuario = getUsuario();

			if(lu_usuario != null)
			{
				String ls_userId;
				String ls_id;

				ls_userId     = lu_usuario.getIdUsuario();

				ls_id = "bean_Administracion_usuarios:pNombre";

				if(!StringUtils.isValidString(ls_userId))
				{
					lcu_usuario = irr_referenceRemote.findAllUsersActivos(
						    ls_userId, ls_userId, getLocalIpAddress(), getRemoteIpAddress()
						);
					setRendered(true);
					validateStyles(ls_id, lfc_context, "valido", lb_focus);
				}
				else
				{
					lcu_usuario.add(irr_referenceRemote.findUserActiveById(lu_usuario));
					setRendered(true);
					validateStyles(ls_id, lfc_context, ls_userId, lb_focus);
				}

				setUsuariosColeccion(lcu_usuario);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			setRendered(false);
			PrimeFaces.current().ajax().update("bean_Administracion_usuarios:globalMsg");
		}

		PrimeFaces.current().ajax().update("bean_Administracion_usuarios");
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<CirculoRegistral> findAllCirculosActivos()
	{
		Collection<CirculoRegistral> lccr_datos;
		lccr_datos = null;

		try
		{
			lccr_datos = irr_referenceRemote.findAllCirculosRegistralesActivos(
				    true, getUsuario().getIdUsuario(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccr_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Etapa> findAllEtapasActivos()
	{
		Collection<Etapa> lccr_datos;
		lccr_datos = null;

		try
		{
			lccr_datos = irr_referenceRemote.findAllEtapasActivo(
				    getUsuario().getIdUsuario(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccr_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Fases> findAllFases()
	{
		Collection<Fases> lcf_datos;
		lcf_datos = null;

		try
		{
			lcf_datos = irr_referenceRemote.findAllFasesActivas(
				    getUsuario().getIdUsuario(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcf_datos;
	}

	/**
	 * Find all lineas produccion.
	 */
	public void findAllLineasProduccion()
	{
		Collection<LineaProduccion> lclp_datos;
		lclp_datos = null;

		Collection<UsuarioLinea> lcul_datos;
		lcul_datos = new ArrayList<UsuarioLinea>();

		try
		{
			lclp_datos = ipr_parameterRemote.findAllLineasProduccion();

			if(CollectionUtils.isValidCollection(lclp_datos))
			{
				for(LineaProduccion llp_temp : lclp_datos)
				{
					UsuarioLinea lul_tmp;
					lul_tmp = new UsuarioLinea();

					lul_tmp.setLineaProduccion(llp_temp);
					lcul_datos.add(lul_tmp);
				}
			}

			setLineasPorUsuario(lcul_datos);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<UsuarioCirculo> findCirculosPorUsuario()
	{
		Collection<UsuarioCirculo> lcul_datos;
		lcul_datos = null;

		try
		{
			UsuarioCirculo lul_temp;
			lul_temp = new UsuarioCirculo();

			lul_temp.setUsuario(getUsuario());

			lcul_datos = ipr_parameterRemote.findCirculosPorUsuario(
				    lul_temp, getUsuario().getIdUsuario(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcul_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<UsuarioCirculo> findCirculosPorUsuarioActivo()
	{
		Collection<UsuarioCirculo> lcul_datos;
		lcul_datos = null;

		try
		{
			UsuarioCirculo lul_temp;
			lul_temp = new UsuarioCirculo();

			lul_temp.setUsuario(getUsuario());

			lcul_datos = ipr_parameterRemote.findCirculosPorUsuarioActivo(
				    lul_temp, getUsuario().getIdUsuario(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcul_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<UsuarioEtapa> findEtapasPorUsuario()
	{
		Collection<UsuarioEtapa> lcul_datos;
		lcul_datos = null;

		UsuarioEtapa lul_temp;
		lul_temp = new UsuarioEtapa();

		lul_temp.setUsuario(getUsuario());

		setUsuarioEtapa(lul_temp);

		try
		{
			lcul_datos = ipr_parameterRemote.findEtapasPorUsuario(
				    lul_temp, getUsuario().getIdUsuario(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcul_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<UsuarioEtapa> findEtapasPorUsuarioActivo()
	{
		Collection<UsuarioEtapa> lcul_datos;
		lcul_datos = null;

		UsuarioEtapa lul_temp;
		lul_temp = new UsuarioEtapa();

		lul_temp.setUsuario(getUsuario());

		setUsuarioEtapa(lul_temp);

		try
		{
			lcul_datos = ipr_parameterRemote.findEtapasPorUsuarioActivo(
				    lul_temp, getUsuario().getIdUsuario(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcul_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<UsuarioLinea> findLineasPorUsuario()
	{
		Collection<UsuarioLinea> lcul_datos;
		lcul_datos = null;

		UsuarioLinea lul_temp;
		Usuario      lu_usuario;

		lu_usuario     = getUsuario();
		lul_temp       = new UsuarioLinea();

		if(lu_usuario == null)
			lu_usuario = new Usuario();

		lul_temp.setUsuario(lu_usuario);

		try
		{
			lcul_datos = ipr_parameterRemote.findLineasPorUsuario(
				    lul_temp, getUsuario().getIdUsuario(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcul_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<UsuarioLinea> findLineasPorUsuarioActivo()
	{
		Collection<UsuarioLinea> lcul_datos;
		lcul_datos = null;

		UsuarioLinea lul_temp;
		Usuario      lu_usuario;

		lu_usuario     = getUsuario();
		lul_temp       = new UsuarioLinea();

		if(lu_usuario == null)
			lu_usuario = new Usuario();

		lul_temp.setUsuario(lu_usuario);

		try
		{
			lcul_datos = ipr_parameterRemote.findLineasPorUsuarioActivo(
				    lul_temp, getUsuario().getIdUsuario(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcul_datos;
	}

	/**
	 * Método que instancia distintos atributos de la clase.
	 */
	public void iniciar()
	{
		iu_usuario                 = new Usuario();
		icu_usuariosColeccion      = new ArrayList<Usuario>();
		is_idUsuarioActualizar     = "";
		ib_botonRegresar           = false;
		im_messages                = new Messages(CONFIG_MESSAGES);
		ib_insertarActualizar      = false;
		ib_validacionDocumento     = true;
	}

	/**
	 * Insert update usuario circulo.
	 *
	 * @param aul_param correspondiente al valor del tipo de objeto UsuarioCirculo
	 * @param ab_insertar correspondiente al valor del tipo de objeto boolean
	 */
	public void insertUpdateUsuarioCirculo(UsuarioCirculo aul_param, boolean ab_insertar)
	{
		try
		{
			boolean      lb_focus;
			FacesContext lfc_context;

			lb_focus        = true;
			lfc_context     = FacesContext.getCurrentInstance();

			if(aul_param == null)
				aul_param = getUsuarioCirculo();

			setCirculosPorUsuario(findCirculosPorUsuario());

			if(ab_insertar)
			{
				{
					String ls_idCirculo;

					ls_idCirculo     = aul_param.getCirculo().getIdCirculo();

					lb_focus = validateStyles(
						    "fAdministracionUsuariosDetalle:TvdetalleUsuario:idSOMCirculoInsert", lfc_context,
						    ls_idCirculo, lb_focus
						);

					if(!StringUtils.isValidString(ls_idCirculo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
				}

				{
					Date ld_fechaDesde;
					ld_fechaDesde     = aul_param.getFechaDesde();

					lb_focus = validateStyles(
						    ":fAdministracionUsuariosDetalle:TvdetalleUsuario:idCalFechaDesde", lfc_context,
						    ld_fechaDesde, lb_focus
						);

					if(ld_fechaDesde == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DESDE);
					else if(
					    ld_fechaDesde.compareTo(
						        DateUtils.clearDateFrom(getUsuario().getFechaCreacion(), Calendar.HOUR)
						    ) == -1
					)
						throw new B2BException(ErrorKeys.ERROR_FECHA_DESDE_INFERIOR);
				}
			}
			else
			{
				Date ld_fechaDesde;
				ld_fechaDesde     = aul_param.getFechaDesde();

				lb_focus = validateStyles(
					    ":fAdministracionUsuariosDetalle:TvdetalleUsuario:idDtListadoCirulo:idCalFechaDesde",
					    lfc_context, ld_fechaDesde, lb_focus
					);

				if(ld_fechaDesde == null)
					throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DESDE);
				else if(
				    ld_fechaDesde.compareTo(DateUtils.clearDateFrom(getUsuario().getFechaCreacion(), Calendar.HOUR)) == -1
				)
					throw new B2BException(ErrorKeys.ERROR_FECHA_DESDE_INFERIOR);
			}

			{
				String ls_activo;
				ls_activo     = aul_param.getActivo();

				lb_focus = validateStyles(
					    ":fAdministracionUsuariosDetalle:TvdetalleUsuario:idDtListadoCirulo:idSOMActivo2", lfc_context,
					    ls_activo, lb_focus
					);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
			}

			aul_param.setUsuario(getUsuarioCirculo().getUsuario());
			ipr_parameterRemote.insertUpdateUsuarioCirculo(
			    aul_param, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);

			setUsuarioCirculo(null);
			setCirculosPorUsuario(findCirculosPorUsuario());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Insert update usuario etapa.
	 *
	 * @param aul_param correspondiente al valor del tipo de objeto UsuarioEtapa
	 * @param ab_insertar correspondiente al valor del tipo de objeto boolean
	 */
	public void insertUpdateUsuarioEtapa(UsuarioEtapa aul_param, boolean ab_insertar)
	{
		try
		{
			FacesContext lfc_context;
			boolean      lb_focus;

			lfc_context     = FacesContext.getCurrentInstance();
			lb_focus        = true;

			if(aul_param != null)
				aul_param.setUsuario(getUsuarioEtapa().getUsuario());
			else
				aul_param = getUsuarioEtapa();

			if(ab_insertar)
			{
				{
					long ll_idEtapa;
					ll_idEtapa     = aul_param.getEtapa().getIdEtapa();

					lb_focus = validateStyles(
						    ":fAdministracionUsuariosDetalle:TvdetalleUsuario:idSOMEtapaInsert", lfc_context,
						    StringUtils.getString(ll_idEtapa), lb_focus
						);

					if(ll_idEtapa <= 0)
						throw new B2BException(ErrorKeys.ERROR_SIN_ETAPA);
				}

				{
					String ls_habilitado;
					ls_habilitado = aul_param.getActivo();

					if(
					    !validateStyles(
						        ":fAdministracionUsuariosDetalle:TvdetalleUsuario:idSOMHabilitado", lfc_context,
						        ls_habilitado, lb_focus
						    )
					)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}
			}
			else
			{
				{
					String ls_habilitado;
					ls_habilitado = aul_param.getActivo();

					if(
					    !validateStyles(
						        ":fAdministracionUsuariosDetalle:TvdetalleUsuario:idDtListadoEtapas:idSOMHabilitado",
						        lfc_context, ls_habilitado, lb_focus
						    )
					)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_HABILITADO);
				}

				aul_param.setIdUsuarioModificacion(getUserId());
				aul_param.setIpModificacion(getRemoteIpAddress());
			}

			ipr_parameterRemote.insertUpdateUsuarioEtapa(
			    aul_param, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setEtapasPorUsuario(findEtapasPorUsuario());
			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fAdministracionUsuariosDetalle:gAdministracionUsuarioDetMsg");
			PrimeFaces.current().ajax().update("fAdministracionUsuariosDetalle:TvdetalleUsuario:idDtListadoEtapas");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param aul_param correspondiente al valor del tipo de objeto UsuarioLinea
	 * @param ab_insertar correspondiente al valor del tipo de objeto boolean
	 * @param ab_mensaje correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 */
	public String insertUpdateUsuarioLinea(UsuarioLinea aul_param, boolean ab_insertar, boolean ab_mensaje)
	{
		boolean      lb_focus;
		FacesContext lfc_context;
		UsuarioLinea lcr_UsuarioLineaInsertUpdate;
		String       ls_return;

		lb_focus      = true;
		ls_return     = null;

		if(aul_param == null)
			lcr_UsuarioLineaInsertUpdate = getUsuarioLinea();
		else
		{
			lcr_UsuarioLineaInsertUpdate = aul_param;
			lcr_UsuarioLineaInsertUpdate.setUsuario(getUsuario());
		}

		lfc_context = FacesContext.getCurrentInstance();

		try
		{
			if(!ab_insertar)
			{
				{
					String ls_activo;
					ls_activo = lcr_UsuarioLineaInsertUpdate.getActivo();

					validateStyles(
					    ":fAdministracionUsuariosDetalle:TvdetalleUsuario:idDtListadoLineas:idSOMActivo4", lfc_context,
					    ls_activo, lb_focus
					);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}
			}

			ls_return = ipr_parameterRemote.insertUpdateUsuarioLinea(
				    lcr_UsuarioLineaInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ab_mensaje)
			{
				String ls_mensaje;
				ls_mensaje = im_messages.getString("commonMessageProcesoCompletado");
				setAllLineasPorUsuario(findLineasPorUsuario());
				addMessage("I", ls_mensaje);
				PrimeFaces.current().ajax().update("fAdministracionUsuariosDetalle:gAdministracionUsuarioDetMsg");
				PrimeFaces.current().ajax().update("fAdministracionUsuariosDetalle:TvdetalleUsuario:idDtListadoLineas");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Insert usuarios.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertUsuarios()
	    throws B2BException
	{
		Collection<UsuarioLinea> lcul_datos;
		Collection<UsuarioLinea> lcul_usuariosLineas;
		Usuario                  lu_usuario;

		lcul_datos              = getLineasPorUsuario();
		lcul_usuariosLineas     = new ArrayList<UsuarioLinea>();
		lu_usuario              = getUsuario();

		if(lu_usuario != null)
		{
			try
			{
				if(CollectionUtils.isValidCollection(lcul_datos))
				{
					for(UsuarioLinea lul_tmp : lcul_datos)
					{
						if(lul_tmp.isSeleccionado())
						{
							if(lul_tmp.getActivo() != null)
								lcul_usuariosLineas.add(lul_tmp);
							else
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
						}
					}
				}

				if(CollectionUtils.isValidCollection(lcul_usuariosLineas))
				{
					Collection<String> lcs_errores;

					lcs_errores = new ArrayList<String>();

					for(UsuarioLinea lul_tmpUsuario : lcul_usuariosLineas)
					{
						String ls_error;

						ls_error = insertUpdateUsuarioLinea(lul_tmpUsuario, true, false);

						if(StringUtils.isValidString(ls_error))
							lcs_errores.add(ls_error);
					}

					if(CollectionUtils.isValidCollection(lcs_errores))
					{
						int              li_tam;
						int              li_pos;
						Iterator<String> lis_iterator;
						String           ls_errorFinal;
						StringBuilder    lsb_sb;

						lis_iterator     = lcs_errores.iterator();
						li_tam           = lcs_errores.size();
						li_pos           = 1;
						lsb_sb           = new StringBuilder();

						while(lis_iterator.hasNext())
						{
							String ls_dato;
							String ls_separador;

							ls_dato          = lis_iterator.next();
							ls_separador     = (li_pos++ != li_tam) ? ", " : "";

							if(StringUtils.isValidString(ls_dato))
							{
								lsb_sb.append(ls_dato);
								lsb_sb.append(ls_separador);
							}
						}

						ls_errorFinal = lsb_sb.toString();

						if(StringUtils.isValidString(ls_errorFinal))
						{
							Object[] loa_messageArgs = new String[2];
							loa_messageArgs[0]     = ls_errorFinal;
							loa_messageArgs[1]     = lu_usuario.getIdUsuario();

							throw new B2BException(ErrorKeys.ERROR_ID_USUARIO_LINEAS_REPETIDAS, loa_messageArgs);
						}
					}

					String ls_mensaje;
					ls_mensaje = im_messages.getString("commonMessageProcesoCompletado");
					setAllLineasPorUsuario(findLineasPorUsuario());
					addMessage("I", ls_mensaje);
					PrimeFaces.current().ajax().update("fAdministracionUsuariosDetalle:gAdministracionUsuarioDetMsg");
					PrimeFaces.current().ajax()
						          .update("fAdministracionUsuariosDetalle:TvdetalleUsuario:idDtListadoLineas");
				}
				else
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_LINEA_PRODUCCION);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		setLineasPorUsuario(findLineasPorUsuario());
		findAllLineasProduccion();

		PrimeFaces.current().ajax().update("fAdministracionUsuariosDetalle:gAdministracionUsuarioDetMsg");
		PrimeFaces.current().ajax().update("fAdministracionUsuariosDetalle");
	}
}
