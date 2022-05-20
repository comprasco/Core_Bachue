package com.bachue.snr.prosnr01.web.bean.consulta.estado.predio;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.estado.predio.ConsultaEstadoPredioRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanConsultaEstadoPredio.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanConsultaEstadoPredio")
@ViewScoped
public class BeanConsultaEstadoPredio extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -418947579265215818L;

	/** Propiedad icepe consulta estado remote. */
	@EJB
	private ConsultaEstadoPredioRemote icepe_consultaEstadoRemote;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad isc imagen. */
	private StreamedContent isc_imagen;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad iba documento. */
	private byte[] iba_documento;

	/** Propiedad ib estado. */
	private boolean ib_estado;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de documento.
	 *
	 * @param aba_ba asigna el valor a la propiedad documento
	 */
	public void setDocumento(byte[] aba_ba)
	{
		iba_documento = aba_ba;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public byte[] getDocumento()
	{
		return iba_documento;
	}

	/**
	 * Modifica el valor de estado.
	 *
	 * @param ab_b asigna el valor a la propiedad estado
	 */
	public void setEstado(boolean ab_b)
	{
		ib_estado = ab_b;
	}

	/**
	 * Retorna el valor de estado.
	 *
	 * @return el valor de estado
	 */
	public boolean getEstado()
	{
		return ib_estado;
	}

	/**
	 * Modifica el valor de id circulo.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
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
	 * Modifica el valor de id matricula.
	 *
	 * @param al_l asigna el valor a la propiedad id matricula
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna el valor de id matricula.
	 *
	 * @return el valor de id matricula
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
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
	 * Generar documento.
	 */
	public void generarDocumento()
	{
		try
		{
			SolicitudMatricula lsm_solicitudMatricula;
			lsm_solicitudMatricula = new SolicitudMatricula();

			long   ls_im;
			byte[] lba_img;
			String ls_ic;

			lba_img     = null;
			ls_im       = NumericUtils.getLong(getIdMatricula());
			ls_ic       = getIdCirculo();

			lsm_solicitudMatricula.setIdCirculo(ls_ic);
			lsm_solicitudMatricula.setIdMatricula(ls_im);

			String ls_user;
			ls_user = getUserId();

			if(StringUtils.isValidString(ls_user))
			{
				lba_img = icepe_consultaEstadoRemote.findEstado(
					    lsm_solicitudMatricula, ls_user, getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lba_img != null)
				{
					InputStream lis_stream;
					lis_stream = new ByteArrayInputStream(lba_img);

					setImagen(
					    new DefaultStreamedContent(
					        lis_stream, TipoContenidoCommon.PDF,
					        ConstanteCommon.NOMBRE_ARCHIVO_CONSULTA_ESTADO_DEL_PREDIO + ExtensionCommon.PDF_PUNTO
					    )
					);

					String ls_mensaje = "Consulta exitosa, verifique los datos.";
					addMessage("I", ls_mensaje);
					PrimeFaces.current().ajax().update("fConsultaEstadoPredio:globalMsg");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			setEstado(false);
		}
	}
}
