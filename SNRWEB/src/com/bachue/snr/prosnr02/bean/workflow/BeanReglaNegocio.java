package com.bachue.snr.prosnr02.bean.workflow;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import com.bachue.snr.prosnr02.model.pgn.ReglaNegocio;
import com.bachue.snr.prosnr02.model.pgn.TopologiaRegla;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de
 * BeanReglaNegocio.
 *
 * @author Juan Sebastian Gómez
 */
@ManagedBean(name = "beanReglaNegocio")
@SessionScoped
public class BeanReglaNegocio extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6309290361728351827L;

	/** Propiedad icp datos auditoria. */
	private Collection<ReglaNegocio> icrn_datosAuditoria;

	/** Propiedad icrn reglasNegocio */
	private Collection<ReglaNegocio> icrn_reglasNegocio;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ip parametros. */
	private ReglaNegocio irn_parametros;

	/** Propiedad irn reglaNegocio. */
	private ReglaNegocio irn_reglaNegocio;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Método que asigna el valor de la propiedad datos de auditoría
	 *
	 * @param acrn_datosAuditoria Dato de tipo <code>Collection<ReglaNegocio</code> que se asigna a los datos auditoria
	 */
	public void setDatosAuditoria(Collection<ReglaNegocio> acrn_datosAuditoria)
	{
		icrn_datosAuditoria = acrn_datosAuditoria;
	}

	/**
	 * Metodo que devuelve el valor de la propiedad datos de auditoría
	 *
	 * @return devuelve el valor de los datos de auditoria
	 */
	public Collection<ReglaNegocio> getDatosAuditoria()
	{
		return icrn_datosAuditoria;
	}

	/**
	 * Asigna el valor de la propiedad insertar
	 *
	 * @param ab_b asigna el valor a insertar
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * Devuelve el valor de la propiedad insertar
	 *
	 * @return devuelve el valor de insertar
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param arn_p asigna el valor a la propiedad
	 */
	public void setParametros(ReglaNegocio arn_p)
	{
		irn_parametros = arn_p;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public ReglaNegocio getParametros()
	{
		if(irn_parametros == null)
			irn_parametros = new ReglaNegocio();

		return irn_parametros;
	}

	/**
	 * @param arn_rn asigna el valor a la regla negocio
	 */
	public void setReglaNegocio(ReglaNegocio arn_rn)
	{
		irn_reglaNegocio = arn_rn;
	}

	/**
	 * @return devuelve el valor de la plantilla notificación
	 */
	public ReglaNegocio getReglaNegocio()
	{
		return irn_reglaNegocio;
	}

	/**
	 * Metodo encargado de asignar el valor a la propiedad reglas negocio
	 *
	 * @param acrn_crn asigna el valor a la propiedad reglas negocio
	 */
	public void setReglasNegocio(Collection<ReglaNegocio> acrn_crn)
	{
		icrn_reglasNegocio = acrn_crn;
	}

	/**
	 * Metodo encargado de devolver las reglas negocio
	 *
	 * @return devuelve el valor de las reglas negocio
	 */
	public Collection<ReglaNegocio> getReglasNegocio()
	{
		if(icrn_reglasNegocio == null)
			findAllReglaNegocion();

		return icrn_reglasNegocio;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar una nueva
	 * regla negocio
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setReglaNegocio(new ReglaNegocio());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarReglaNegocio");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para consultar una regla negocio específica.
	 *
	 * @param arn_regla correspondiente al valor del tipo de objeto <code>ReglaNegocio</code>
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String consultaDetallada(ReglaNegocio arn_regla)
	    throws B2BException
	{
		String ls_return;
		ls_return = NavegacionCommon.REGLA_NEGOCIO_DETALLE;

		try
		{
			if(arn_regla != null)
			{
				ReglaNegocio lpn_datos;
				lpn_datos = ipr_parameterRemote.findReglaNegocioById(arn_regla);

				if(lpn_datos != null)
				{
					Collection<ReglaNegocio> lcpn_cpn;
					lcpn_cpn = new ArrayList<ReglaNegocio>();

					lcpn_cpn.add(lpn_datos);
					setReglaNegocio(lpn_datos);
					setDatosAuditoria(lcpn_cpn);
				}

				setInsertar(false);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			ls_return = null;
		}

		return ls_return;
	}

	/**
	 * Método para encontrar todos los registros de regla negocio
	 *
	 * @return Collection de tipo <code>ReglaNegocio</code>
	 */
	public Collection<ReglaNegocio> findAllReglaNegocion()
	{
		try
		{
			icrn_reglasNegocio = ipr_parameterRemote.findAllReglaNegocio(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return icrn_reglasNegocio;
	}

	/**
	 * Retorna el valor del objeto de Collection del resultado de la consulta de las topologías regla
	 *
	 * @return Colección de tipo <code>TopologiaRegla</code> que contiene los resultados de la busqueda.
	 *
	 */
	public Collection<TopologiaRegla> findTopologiaRegla()
	{
		Collection<TopologiaRegla> lctr_datos;
		lctr_datos = null;

		try
		{
			lctr_datos = ipr_parameterRemote.findAllTopologiaRegla(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lctr_datos = null;
		}

		return lctr_datos;
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			ReglaNegocio lrn_parametros;

			lrn_parametros = getReglaNegocio();

			if(lrn_parametros != null)
			{
				FacesContext lfc_context;

				lfc_context = FacesContext.getCurrentInstance();

				{
					String ls_nombre;

					ls_nombre = lrn_parametros.getNombre();

					validateStyles("fReglaNegocioDetalle:nombre", lfc_context, ls_nombre, true);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);

					actualizarComponente("fReglaNegocioDetalle:nombre");
				}

				{
					String ls_descripcion;

					ls_descripcion = lrn_parametros.getDescripcion();

					validateStyles("fReglaNegocioDetalle:descripcion", lfc_context, ls_descripcion, true);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.ERROR_DESCRIPCION);

					actualizarComponente("fReglaNegocioDetalle:descripcion");
				}

				{
					String ls_nombreProdecmiento;

					ls_nombreProdecmiento = lrn_parametros.getNombreProcedimiento();

					validateStyles(
					    "fReglaNegocioDetalle:nombreProcedimiento", lfc_context, ls_nombreProdecmiento, true
					);

					if(!StringUtils.isValidString(ls_nombreProdecmiento))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE_PROCEDIMIENTO);

					actualizarComponente("fReglaNegocioDetalle:nombreProcedimiento");
				}

				{
					String ls_idTopologia;

					ls_idTopologia = lrn_parametros.getIdTopologiaRegla();

					validateStyles("fReglaNegocioDetalle:idTopologia", lfc_context, ls_idTopologia, true);

					if(!StringUtils.isValidString(ls_idTopologia))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TOPOLOGIA_REGLA);

					actualizarComponente("fReglaNegocioDetalle:idTopologia");
				}

				ipr_parameterRemote.salvarReglaNegocio(
				    lrn_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setReglaNegocio(null);
				setReglasNegocio(null);

				addMessage(MessagesKeys.PROCESO_COMPLETADO);
				PrimeFaces.current().ajax().update("fReglaNegocioDetalle:globalMsg");

				ls_result = NavegacionCommon.REGLA_NEGOCIO;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fReglaNegocioDetalle:globalMsg");
		}

		return ls_result;
	}
}
