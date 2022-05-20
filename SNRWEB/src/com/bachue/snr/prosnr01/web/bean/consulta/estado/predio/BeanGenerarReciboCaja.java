package com.bachue.snr.prosnr01.web.bean.consulta.estado.predio;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.registro.Registro;

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
 * Clase que contiene todos las propiedades y acciones de BeanGenerarReciboCaja.
 *
 * @author
 */
@ManagedBean(name = "beanGenerarReciboCaja")
@ViewScoped
public class BeanGenerarReciboCaja extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -418947579265215818L;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isc imagen. */
	private StreamedContent isc_imagen;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad iba documento. */
	private byte[] iba_documento;

	/** Propiedad ib estado. */
	private boolean ib_estado;

	/**
	 * Instancia un nuevo objeto bean generar recibo caja.
	 */
	public BeanGenerarReciboCaja()
	{
		setEstado(false);
	}

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
	 * Clean.
	 */
	public void clean()
	{
		setNir(null);
	}

	/**
	 * Generar documento.
	 */
	public void generarDocumento()
	{
		try
		{
			byte[] lba_img;
			String ls_s;

			lba_img     = null;
			ls_s        = getNir();

			if(StringUtils.isValidString(ls_s))
			{
				Registro lr_r;
				lr_r = new Registro();

				lr_r.setNirProceso(ls_s);
				lr_r.setIdUsuarioCreacion(getUserId());
				lr_r.setIpCreacion(getLocalIpAddress());

				lr_r = irr_registroRemote.generarReciboCaja(
					    lr_r, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lr_r != null)
				{
					lba_img = lr_r.getPdf();

					if(lba_img != null)
					{
						InputStream lis_stream;

						lis_stream = new ByteArrayInputStream(lba_img);

						setImagen(
						    new DefaultStreamedContent(
						        lis_stream, TipoContenidoCommon.ZIP, ConstanteCommon.NOMBRE_ZIP_GENERADO
						    )
						);

						String ls_mensaje = "Consulta exitosa, verifique los datos.";
						addMessage("I", ls_mensaje);
						PrimeFaces.current().ajax().update("fReciboCaja:globalMsg");
					}
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
