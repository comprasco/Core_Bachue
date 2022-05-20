package com.bachue.snr.prosnr01.web.util;

import com.bachue.snr.prosnr01.model.entrega.Entrega;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import org.primefaces.model.StreamedContent;

import java.io.Serializable;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades para manejar EntregaUI.
 *
 * @author Julian Vaca
 */
public class EntregaUI extends Entrega implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 249143346553659084L;

	/** Propiedad ica data zip. */
	private Collection<EntregaUI> ica_dataZip;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad icsi dato inter select. */
	private SolicitudInterviniente icsi_datoInterSelect;

	/** Propiedad isc imagen. */
	private StreamedContent isc_imagen;

	/** Propiedad is motivo. */
	private String is_motivo;

	/** Propiedad is motivo no tramite. */
	private String is_motivoNoTramite;

	/** Propiedad is usuario etapa. */
	private String is_usuarioEtapa;

	/** Propiedad ib image valida. */
	private boolean ib_imageValida;

	/** Propiedad proceso vinculado 47 o 48. */
	private boolean ib_procesoVinculado47o48;

	/**
	 * Instancia un nuevo objeto entrega UI.
	 */
	public EntregaUI()
	{
	}

	/**
	 * Instancia un nuevo objeto entrega UI.
	 *
	 * @param ae_entrega correspondiente a una <code>Entrega</code>.
	 */
	public EntregaUI(Entrega ae_entrega)
	{
		if(ae_entrega != null)
		{
			setCartaAutorizacion(ae_entrega.getCartaAutorizacion());
			setCedulaCiudadania(ae_entrega.getCedulaCiudadania());
			setCorreoElectronico(ae_entrega.getCorreoElectronico());
			setDatosIntervinientes(ae_entrega.getDatosIntervinientes());
			setDatosMatriculas(ae_entrega.getDatosMatriculas());
			setDatosPredioEntrega(ae_entrega.getDatosPredioEntrega());
			setDocumentoRecurso(ae_entrega.getDocumentoRecurso());
			setDocumentosAdicionales(ae_entrega.getDocumentosAdicionales());
			setIdEtapa(ae_entrega.getIdEtapa());
			setIdTurno(ae_entrega.getIdTurno());
			setIntervinienteEntrega(ae_entrega.getIntervinienteEntrega());
			setIntervinientesApoderado(ae_entrega.getIntervinientesApoderado());
			setNir(ae_entrega.getNir());
			setObservacionesDocumentos(ae_entrega.getObservacionesDocumentos());
			setRenunciaTerminos(ae_entrega.getRenunciaTerminos());
			setSolicitud(ae_entrega.getSolicitud());
			setTercero(ae_entrega.getTercero());
			setTurno(ae_entrega.getTurno());
			setTurnoHistoria(ae_entrega.getTurnoHistoria());
			setTiposDocumentales(ae_entrega.getTiposDocumentales());
			setProcesoVinculado47o48(ae_entrega.isProcesoVinculado47o48());
		}
	}

	/**
	 * Instancia un nuevo objeto entrega UI.
	 *
	 * @param al_nir correspondiente al valor del tipo de objeto String
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 */
	public EntregaUI(String al_nir, String as_idTurno)
	{
		setNir(al_nir);
		setIdTurno(as_idTurno);
	}

	/**
	 * Modifica el valor de data zip.
	 *
	 * @param aca_ca asigna el valor a la propiedad data zip
	 */
	public void setDataZip(Collection<EntregaUI> aca_ca)
	{
		ica_dataZip = aca_ca;
	}

	/**
	 * Retorna el valor de data zip.
	 *
	 * @return el valor de data zip
	 */
	public Collection<EntregaUI> getDataZip()
	{
		return ica_dataZip;
	}

	/**
	 * Modifica el valor de datos inter select.
	 *
	 * @param acsi_csi asigna el valor a la propiedad datos inter select
	 */
	public void setDatosInterSelect(SolicitudInterviniente acsi_csi)
	{
		icsi_datoInterSelect = acsi_csi;
	}

	/**
	 * Retorna el valor de datos inter select.
	 *
	 * @return el valor de datos inter select
	 */
	public SolicitudInterviniente getDatosInterSelect()
	{
		return icsi_datoInterSelect;
	}

	/**
	 * Modifica el valor de documento.
	 *
	 * @param ad_d asigna el valor a la propiedad documento
	 */
	public void setDocumento(Documento ad_d)
	{
		id_documento = ad_d;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public Documento getDocumento()
	{
		if(id_documento == null)
			id_documento = new Documento();

		return id_documento;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param al_l asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de image valida.
	 *
	 * @param ab_b asigna el valor a la propiedad image valida
	 */
	public void setImageValida(boolean ab_b)
	{
		ib_imageValida = ab_b;
	}

	/**
	 * Valida la propiedad image valida.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en image valida
	 */
	public boolean isImageValida()
	{
		return ib_imageValida;
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
	 * Modifica el valor de motivo.
	 *
	 * @param as_s asigna el valor a la propiedad motivo
	 */
	public void setMotivo(String as_s)
	{
		is_motivo = as_s;
	}

	/**
	 * Retorna el valor de motivo.
	 *
	 * @return el valor de motivo
	 */
	public String getMotivo()
	{
		return is_motivo;
	}

	/**
	 * Modifica el valor de motivo no tramite.
	 *
	 * @param as_s asigna el valor a la propiedad motivo no tramite
	 */
	public void setMotivoNoTramite(String as_s)
	{
		is_motivoNoTramite = as_s;
	}

	/**
	 * Retorna el valor de motivo no tramite.
	 *
	 * @return el valor de motivo no tramite
	 */
	public String getMotivoNoTramite()
	{
		return is_motivoNoTramite;
	}

	/**
	 * @param Modifica el valor de la propiedad procesoVinculado47o48 por procesoVinculado47o48
	 */
	public void setProcesoVinculado47o48(boolean as_procesoVinculado47o48)
	{
		ib_procesoVinculado47o48 = as_procesoVinculado47o48;
	}

	/**
	 * @return Retorna el valor de la propiedad procesoVinculado47o48
	 */
	public boolean isProcesoVinculado47o48()
	{
		return ib_procesoVinculado47o48;
	}

	/**
	 * Modifica el valor de usuario etapa.
	 *
	 * @param as_s asigna el valor a la propiedad usuario etapa
	 */
	public void setUsuarioEtapa(String as_s)
	{
		is_usuarioEtapa = as_s;
	}

	/**
	 * Retorna el valor de usuario etapa.
	 *
	 * @return el valor de usuario etapa
	 */
	public String getUsuarioEtapa()
	{
		return is_usuarioEtapa;
	}
}
