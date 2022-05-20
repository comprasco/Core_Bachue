package com.bachue.snr.prosnr01.web.bean.consulta.documento;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.documento.ConsultaDocumentoRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.trazabilidad.ConsultaTrazabilidadRemote;

import com.bachue.snr.prosnr01.model.consulta.documento.ConsultaDocumento;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.ConsultaTrazabilidad;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.InformacionDocumento;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanConsultaDocumento.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanConsultaDocumento")
@SessionScoped
public class BeanConsultaDocumento extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5518845231670473556L;

	/** Propiedad iccd consulta documento. */
	private Collection<ConsultaDocumento> iccd_consultaDocumento;

	/** Propiedad icct consulta trazabilidad. */
	private Collection<ConsultaTrazabilidad> icct_consultaTrazabilidad;

	/** Propiedad ict trazabilidad. */
	private Collection<Trazabilidad> ict_trazabilidad;

	/** Propiedad icdr consulta documento remote. */
	@EJB
	private ConsultaDocumentoRemote icdr_consultaDocumentoRemote;

	/** Propiedad ictr consulta trazabilidad remote. */
	@EJB
	private ConsultaTrazabilidadRemote ictr_consultaTrazabilidadRemote;

	/** Propiedad iid informacion documento. */
	private InformacionDocumento iid_informacionDocumento;

	/** Propiedad is solicitud actual. */
	private Solicitud is_solicitudActual;

	/** Propiedad is id documento. */
	private String is_idDocumento;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is tipo documento. */
	private String is_tipoDocumento;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de consulta documento.
	 *
	 * @param accd_ccd asigna el valor a la propiedad consulta documento
	 */
	public void setConsultaDocumento(Collection<ConsultaDocumento> accd_ccd)
	{
		iccd_consultaDocumento = accd_ccd;
	}

	/**
	 * Retorna el valor de consulta documento.
	 *
	 * @return el valor de consulta documento
	 */
	public Collection<ConsultaDocumento> getConsultaDocumento()
	{
		return iccd_consultaDocumento;
	}

	/**
	 * Modifica el valor de consulta trazabilidad.
	 *
	 * @param acct_cct asigna el valor a la propiedad consulta trazabilidad
	 */
	public void setConsultaTrazabilidad(Collection<ConsultaTrazabilidad> acct_cct)
	{
		icct_consultaTrazabilidad = acct_cct;
	}

	/**
	 * Retorna el valor de consulta trazabilidad.
	 *
	 * @return el valor de consulta trazabilidad
	 */
	public Collection<ConsultaTrazabilidad> getConsultaTrazabilidad()
	{
		return icct_consultaTrazabilidad;
	}

	/**
	 * Modifica el valor de id documento.
	 *
	 * @param as_s asigna el valor a la propiedad id documento
	 */
	public void setIdDocumento(String as_s)
	{
		is_idDocumento = as_s;
	}

	/**
	 * Retorna el valor de id documento.
	 *
	 * @return el valor de id documento
	 */
	public String getIdDocumento()
	{
		return is_idDocumento;
	}

	/**
	 * Modifica el valor de informacion documento.
	 *
	 * @param aid_id asigna el valor a la propiedad informacion documento
	 */
	public void setInformacionDocumento(InformacionDocumento aid_id)
	{
		iid_informacionDocumento = aid_id;
	}

	/**
	 * Retorna el valor de informacion documento.
	 *
	 * @return el valor de informacion documento
	 */
	public InformacionDocumento getInformacionDocumento()
	{
		return iid_informacionDocumento;
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
	 * Modifica el valor de solicitud actual.
	 *
	 * @param as_s asigna el valor a la propiedad solicitud actual
	 */
	public void setSolicitudActual(Solicitud as_s)
	{
		is_solicitudActual = as_s;
	}

	/**
	 * Retorna el valor de solicitud actual.
	 *
	 * @return el valor de solicitud actual
	 */
	public Solicitud getSolicitudActual()
	{
		return is_solicitudActual;
	}

	/**
	 * Modifica el valor de tipo documento.
	 *
	 * @param as_s asigna el valor a la propiedad tipo documento
	 */
	public void setTipoDocumento(String as_s)
	{
		is_tipoDocumento = as_s;
	}

	/**
	 * Retorna el valor de tipo documento.
	 *
	 * @return el valor de tipo documento
	 */
	public String getTipoDocumento()
	{
		return is_tipoDocumento;
	}

	/**
	 * Modifica el valor de trazabilidad.
	 *
	 * @param act_ct asigna el valor a la propiedad trazabilidad
	 */
	public void setTrazabilidad(Collection<Trazabilidad> act_ct)
	{
		ict_trazabilidad = act_ct;
	}

	/**
	 * Retorna el valor de trazabilidad.
	 *
	 * @return el valor de trazabilidad
	 */
	public Collection<Trazabilidad> getTrazabilidad()
	{
		return ict_trazabilidad;
	}

	/**
	 * Consulta detallada.
	 */
	public void consultaDetallada()
	{
		findInfoDoc();
		findConsultaTraza();
	}

	/**
	 * Find all.
	 */
	public void findAll()
	{
		try
		{
			setConsultaDocumento(null);

			String ls_idDocumento;
			String ls_tipoDocumento;

			Documento ld_documento;
			Persona   lp_persona;

			ConsultaDocumento lcd_consultaDocumento;

			ls_idDocumento       = getIdDocumento();
			ls_tipoDocumento     = getTipoDocumento();

			ld_documento              = new Documento();
			lp_persona                = new Persona();
			lcd_consultaDocumento     = new ConsultaDocumento();

			ld_documento.setIdDocumento(ls_idDocumento);
			lp_persona.setTipoDocIdentidad(ls_tipoDocumento);

			lcd_consultaDocumento.setDocumento(ld_documento);
			lcd_consultaDocumento.setPersona(lp_persona);

			setConsultaDocumento(icdr_consultaDocumentoRemote.findConsultaDocumento(lcd_consultaDocumento));
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Find consulta traza.
	 */
	public void findConsultaTraza()
	{
		try
		{
			Turno lt_t;
			lt_t = new Turno();

			String ls_idTurno;
			ls_idTurno = FacesUtils.getStringFacesParameter("idTurno");

			if(StringUtils.isValidString(ls_idTurno))
			{
				lt_t.setIdTurno(ls_idTurno);
				setConsultaTrazabilidad(ictr_consultaTrazabilidadRemote.findConsultaTraza(lt_t));
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Find info doc.
	 */
	public void findInfoDoc()
	{
		try
		{
			Turno lt_t;
			lt_t = new Turno();

			String ls_idTurno;
			ls_idTurno = FacesUtils.getStringFacesParameter("idTurno");

			if(StringUtils.isValidString(ls_idTurno))
			{
				lt_t.setIdTurno(ls_idTurno);
				setInformacionDocumento(ictr_consultaTrazabilidadRemote.findInfoDoc(lt_t));
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Find traza.
	 */
	public void findTraza()
	{
		try
		{
			setTrazabilidad(null);

			Solicitud    ls_solicitud;
			Turno        lt_turno;
			Fases        lf_fases;
			Trazabilidad lt_trazabilidad;

			lf_fases            = new Fases();
			ls_solicitud        = new Solicitud();
			lt_turno            = new Turno();
			lt_trazabilidad     = new Trazabilidad();

			setNir(FacesUtils.getStringFacesParameter("varNIR"));

			ls_solicitud.setNir(getNir());

			lt_trazabilidad.setSolicitud(ls_solicitud);
			lt_trazabilidad.setTurno(lt_turno);
			lt_trazabilidad.setFases(lf_fases);

			setTrazabilidad(icdr_consultaDocumentoRemote.findTrazabilidad(lt_trazabilidad));
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Limpiar pantalla.
	 */
	public void limpiarPantalla()
	{
		setConsultaDocumento(null);
		setConsultaTrazabilidad(null);
		setIdDocumento(null);
		setInformacionDocumento(null);
		setNir(null);
		setTipoDocumento(null);
		setTrazabilidad(null);
	}
}
