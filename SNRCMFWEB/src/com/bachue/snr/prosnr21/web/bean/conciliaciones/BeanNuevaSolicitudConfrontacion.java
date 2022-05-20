package com.bachue.snr.prosnr21.web.bean.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.MessagesKeys;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote;
import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;

import com.bachue.snr.prosnr21.web.bean.BaseBean;

import java.io.Serializable;

import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import javax.mail.MessagingException;

import javax.mail.internet.AddressException;


/**
 * Clase que contiene todos las propiedades BeanConfrontacionManual.
 *
 * @author  Duvan Beltrán
 */
@ManagedBean(name = "beanNuevaSolicitudConfrontacion")
@SessionScoped
public class BeanNuevaSolicitudConfrontacion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID*/
	private static final long serialVersionUID = 6236281145239220204L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanNuevaSolicitudConfrontacion.class);

	/**Propiedad Entidad recaaudadoraCuenta*/
	private Collection<EntidadRecaudadoraCuenta> ic_entidadRecaudadoraCuentas;

	/** Propiedad ipr conciliaciones remote. */
	@EJB
	private ConciliacionesRemote ipr_conciliacionesRemote;

	/**Propiedad medio a comunicar*/
	private Date is_fechaConfrontar;

	/**Propiedad Entidad Recaudadora cuenta*/
	private EntidadRecaudadoraCuenta ir_entidadRecaudadoraCuenta;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/**Propiedad correo electronico*/
	private String is_correoElectronico;

	/**Propiedad id banco*/
	private String is_idBanco;

	/** Propiedad id cuenta*/
	private String is_idCuenta;

	/** Propiedad id entidad recaudadora*/
	private String is_idEntidadRecaudadora;

	/**Propiedad id tipo Archivo */
	private String is_idTipoArchivo;

	/**Propiedad medio a comunicar*/
	private String is_medioComunicar;

	/** The nombre banco. */
	private String is_nombreBanco;

	/** The numero cuenta. */
	private String is_numeroCuenta;
	private String mensajeConfirmacion;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param Modifica el valor de la propiedad is_correoElectronico por as_correoElectronico
	 */
	public void setCorreoElectronico(String as_correoElectronico)
	{
		is_correoElectronico = as_correoElectronico;
	}

	/**
	 * @return Retorna el valor de la propiedad is_correoElectronico
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Método de asignación del valor de la propiedad
	 * @param ar_entidadRecaudadoraCuenta asigna el valor a la propiedad
	 */
	public void setEntidadRecaudadoraCuenta(EntidadRecaudadoraCuenta ar_entidadRecaudadoraCuenta)
	{
		ir_entidadRecaudadoraCuenta = ar_entidadRecaudadoraCuenta;
	}

	/**
	 * Método de obtencion del valor de la propiedad
	 * @return devuelve el valor de la propiedad
	 */
	public EntidadRecaudadoraCuenta getEntidadRecaudadoraCuenta()
	{
		return (ir_entidadRecaudadoraCuenta != null) ? ir_entidadRecaudadoraCuenta : new EntidadRecaudadoraCuenta();
	}

	/**
	 * @param Modifica el valor de la propiedad ic_entidadRecaudadoraCuentas por ac_entidadRecaudadoraCuentas
	 */
	public void setEntidadRecaudadoraCuentas(Collection<EntidadRecaudadoraCuenta> ac_entidadRecaudadoraCuentas)
	{
		ic_entidadRecaudadoraCuentas = ac_entidadRecaudadoraCuentas;
	}

	/**
	 * @return Retorna el valor de la propiedad ic_entidadRecaudadoraCuentas
	 */
	public Collection<EntidadRecaudadoraCuenta> getEntidadRecaudadoraCuentas()
	{
		return ic_entidadRecaudadoraCuentas;
	}

	/**
	 * @param Modifica el valor de la propiedad is_fechaConfrontar por is_fechaConfrontar
	 */
	public void setFechaConfrontar(Date as_fechaConfrontar)
	{
		is_fechaConfrontar = as_fechaConfrontar;
	}

	/**
	 * @return Retorna el valor de la propiedad is_fechaConfrontar
	 */
	public Date getFechaConfrontar()
	{
		return is_fechaConfrontar;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idBanco por as_idBanco
	 */
	public void setIdBanco(String as_idBanco)
	{
		is_idBanco = as_idBanco;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idBanco
	 */
	public String getIdBanco()
	{
		return is_idBanco;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idCuenta por as_idCuenta
	 */
	public void setIdCuenta(String as_idCuenta)
	{
		is_idCuenta = as_idCuenta;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idCuenta
	 */
	public String getIdCuenta()
	{
		return is_idCuenta;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idEntidadRecaudadora por as_idEntidadRecaudadora
	 */
	public void setIdEntidadRecaudadora(String as_idEntidadRecaudadora)
	{
		is_idEntidadRecaudadora = as_idEntidadRecaudadora;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idEntidadRecaudadora
	 */
	public String getIdEntidadRecaudadora()
	{
		return is_idEntidadRecaudadora;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idTipoArchivo por as_idTipoArchivo
	 */
	public void setIdTipoArchivo(String as_idTipoArchivo)
	{
		is_idTipoArchivo = as_idTipoArchivo;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idTipoArchivo
	 */
	public String getIdTipoArchivo()
	{
		return is_idTipoArchivo;
	}

	/**
	 * @param Modifica el valor de la propiedad is_medioComunicar por as_medioComunicar
	 */
	public void setMedioComunicar(String as_medioComunicar)
	{
		is_medioComunicar = as_medioComunicar;
	}

	/**
	 * @return Retorna el valor de la propiedad is_medioComunicar
	 */
	public String getMedioComunicar()
	{
		return is_medioComunicar;
	}

	/**
	 * @param Modifica el valor de la propiedad mensajeConfirmacion por mensajeConfirmacion
	 */
	public void setMensajeConfirmacion(String mensajeConfirmacion)
	{
		this.mensajeConfirmacion = mensajeConfirmacion;
	}

	/**
	 * @return Retorna el valor de la propiedad mensajeConfirmacion
	 */
	public String getMensajeConfirmacion()
	{
		return mensajeConfirmacion;
	}

	/**
	 * @param Modifica el valor de la propiedad nombreBanco por nombreBanco
	 */
	public void setNombreBanco(String as_nombreBanco)
	{
		is_nombreBanco = as_nombreBanco;
	}

	/**
	 * @return Retorna el valor de la propiedad nombreBanco
	 */
	public String getNombreBanco()
	{
		return is_nombreBanco;
	}

	/**
	 * @param Modifica el valor de la propiedad numeroCuenta por numeroCuenta
	 */
	public void setNumeroCuenta(String as_numeroCuenta)
	{
		is_numeroCuenta = as_numeroCuenta;
	}

	/**
	 * @return Retorna el valor de la propiedad numeroCuenta
	 */
	public String getNumeroCuenta()
	{
		return is_numeroCuenta;
	}

	/**
	 *  Método que retorna todas las entidades recaudadoras de conciliación asignadas a un usuario.
	 *
	 * Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 *
	 * @return el valor de collection
	 */
	public Collection<EntidadRecaudadoraConciliacion> buscarEntidadRecaudadoraConciliacion()
	{
		Collection<EntidadRecaudadoraConciliacion> lcpc_procesoConciliacion;
		lcpc_procesoConciliacion = null;

		try
		{
			lcpc_procesoConciliacion = ipr_conciliacionesRemote.findEntidadRecaudadoraConciliacionByUser(getUserId());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcpc_procesoConciliacion;
	}

	/**
	 *  Método que retorna todas las entidades recaudadoras de conciliación asiganadas a un usuario y una entidad.
	 *
	 * Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 *
	 * @return el valor de collection
	 */
	public Collection<EntidadRecaudadoraCuenta> buscarEntidadRecaudadoraCuentaDeUsuario()
	{
		Collection<EntidadRecaudadoraCuenta> lcpc_procesoConciliacion;

		lcpc_procesoConciliacion = null;

		try
		{
			Map<String, Object> lmso_data;

			lmso_data = ipr_conciliacionesRemote.findEntidadRecaudadoraCuentaData(
				    getIdEntidadRecaudadora(), getUserId()
				);

			if(CollectionUtils.isValidCollection(lmso_data))
			{
				EntidadRecaudadoraConciliacion lerc_banco;

				lerc_banco                   = (EntidadRecaudadoraConciliacion)lmso_data.get("ENTIDAD");
				lcpc_procesoConciliacion     = (Collection<EntidadRecaudadoraCuenta>)lmso_data.get("CUENTAS");

				if(lerc_banco != null)
					setNombreBanco(lerc_banco.getNombreEntidadRecaudadora());
			}

			setEntidadRecaudadoraCuentas(lcpc_procesoConciliacion);

			cambiarTipoArchivo();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcpc_procesoConciliacion;
	}

	/**
	 * Cambiar tipo archivo.
	 *
	 * @throws B2BException the b 2 B exception
	 */
	public void cambiarTipoArchivo()
	    throws B2BException
	{
		EntidadRecaudadoraCuenta lerc_data;
		String                   ls_tipoArchivo;
		String                   ls_numeroCuenta;
		String                   ls_correoCuenta;

		lerc_data           = new EntidadRecaudadoraCuenta();
		ls_tipoArchivo      = null;
		ls_numeroCuenta     = null;
		ls_correoCuenta     = null;

		lerc_data.setIdCuenta(getIdCuenta());

		lerc_data = ipr_parameterRemote.findEntidadRecaudadoraCuentaById(lerc_data);

		if(lerc_data != null)
		{
			ls_tipoArchivo      = lerc_data.getTipoArchivo();
			ls_numeroCuenta     = lerc_data.getNumeroCuenta();
			ls_correoCuenta     = lerc_data.getCorreoElectronicoContacto();
		}

		setNumeroCuenta(ls_numeroCuenta);
		setIdTipoArchivo(ls_tipoArchivo);
		setCorreoElectronico(ls_correoCuenta);
	}

	/**
	 * Método de consulta de todos los EntidadRecaudadoraConciliacions
	 * @return una coleccion de tipo EntidadRecaudadoraConciliacion
	 */
	public Collection<EntidadRecaudadoraConciliacion> findAllEntidadRecaudadoraConciliacion()
	{
		Collection<EntidadRecaudadoraConciliacion> lcerc_erc;
		lcerc_erc = null;

		try
		{
			lcerc_erc = ipr_parameterRemote.findAllEntidadRecaudadoraConciliacion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcerc_erc;
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		setIdEntidadRecaudadora(null);
		setIdCuenta(null);
		setIdTipoArchivo(null);
		setFechaConfrontar(null);
		setNombreBanco(null);
		setCorreoElectronico(null);
	}

	/**
	 * Método consultar Confrontacion el cual resulta en la confrontación manual
	 * @throws MessagingException
	 * @throws AddressException
	 */
	public void realizarNuevaSolicitud()
	    throws AddressException, MessagingException
	{
		try
		{
			boolean      lb_focus;
			FacesContext lfc_context;
			Date         ld_fechaConciliacion;
			String       ls_entidadRecaudadora;
			String       ls_correoElectronico;
			String       ls_idCuenta;

			lb_focus                  = true;
			lfc_context               = FacesContext.getCurrentInstance();
			ls_entidadRecaudadora     = getIdEntidadRecaudadora();
			lb_focus                  = validateStyles(
				    ":fNuevaSolicitud:idEntidadRecaudadoraCuenta", lfc_context, ls_entidadRecaudadora, lb_focus
				);

			ls_idCuenta     = getIdCuenta();
			lb_focus        = validateStyles(":fNuevaSolicitud:idNumeroCuenta", lfc_context, ls_idCuenta, lb_focus);

			ld_fechaConciliacion     = getFechaConfrontar();
			lb_focus                 = validateStyles(
				    ":fNuevaSolicitud:idFechaAConciliar", lfc_context, ld_fechaConciliacion, lb_focus
				);

			ls_correoElectronico     = getCorreoElectronico();
			lb_focus                 = validateStyles(
				    ":fNuevaSolicitud:idCorreoElectronico", lfc_context, ls_correoElectronico, lb_focus
				);

			if(!lb_focus)
				throw new B2BException(ErrorKeys.ERROR_SELECCION_INFORMACION_CONSULTA);

			if(ld_fechaConciliacion == null)
				throw new B2BException(ErrorKeys.ERROR_FECHA_MAYOR_A_ACTUAL);

			ipr_conciliacionesRemote.realizarNuevaSolicitud(
			    ld_fechaConciliacion, ls_idCuenta, ls_correoElectronico, getIdTipoArchivo(), getUserId(),
			    getLocalIpAddress(), getRemoteIpAddress()
			);

			addMessage(MessagesKeys.NUEVA_SOLICITUD_REALIZADA);
			limpiar();
			actualizarComponente(":fNuevaSolicitud");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("realizarNuevaSolicitud", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Validar confrontacion.
	 */
	public void validarNuevaSolicitud()
	{
		try
		{
			Date   ld_fechaConciliacion;
			String ls_idCuenta;

			ld_fechaConciliacion     = getFechaConfrontar();
			ls_idCuenta              = getIdCuenta();

			if(!StringUtils.isValidString(ls_idCuenta) || (ld_fechaConciliacion == null))
				throw new B2BException(ErrorKeys.ERROR_SELECCION_INFORMACION_CONSULTA);
			else
			{
				EntidadRecaudadoraCuenta lerc_cuenta;

				lerc_cuenta = ipr_parameterRemote.findEntidadRecaudadoraCuentaById(
					    ls_idCuenta, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lerc_cuenta != null)
				{
					Object[] loa_arg;

					loa_arg        = new String[2];
					loa_arg[0]     = lerc_cuenta.getNumeroCuenta();
					loa_arg[1]     = new SimpleDateFormat("dd/MM/yyyy").format(ld_fechaConciliacion);

					setMensajeConfirmacion(getMessages().getString(MessagesKeys.CONFIRMACION_NUEVA_SOLICITUD, loa_arg));

					abrirDialogo("dlgConfirmar", "dlgConfirmar");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarConfrontacion", lb2be_e);
			addMessage(lb2be_e);
		}
	}
}
