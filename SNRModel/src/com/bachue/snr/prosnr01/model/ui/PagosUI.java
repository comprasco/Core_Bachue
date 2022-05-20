package com.bachue.snr.prosnr01.model.ui;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.admHistoricosMisional.PagosFolio;
import com.bachue.snr.prosnr01.model.admHistoricosMisional.PagosSir;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase que contiene todos las propiedades PagosUI.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 19/06/2020
 */
public class PagosUI implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7298049153705314959L;

	/** Propiedad id fecha. */
	private Date id_fecha;

	/** Propiedad is aprobacion. */
	private String is_aprobacion;

	/** Propiedad is banco. */
	private String is_banco;

	/** Propiedad is canal. */
	private String is_canal;

	/** Propiedad is cheque. */
	private String is_cheque;

	/** Propiedad is concepto. */
	private String is_concepto;

	/** Propiedad is fechaString. */
	private String is_fechaString;

	/** Propiedad is forma pago. */
	private String is_formaPago;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is recibo pago. */
	private String is_reciboPago;

	/** Propiedad is valor. */
	private String is_valor;

	/**
	 * Instancia un nuevo objeto pagos UI.
	 *
	 * @param lps_ps de lps ps
	 */
	public PagosUI(PagosSir lps_ps)
	{
		is_idTurno        = lps_ps.getTurnoActualizado();
		id_fecha          = lps_ps.getFecha();
		is_reciboPago     = lps_ps.getRecibo();
		is_cheque         = lps_ps.getCheque();
		is_aprobacion     = lps_ps.getAprobacion();
		is_valor          = StringUtils.getString(lps_ps.getValor());

		{
			if(id_fecha != null)
				is_fechaString = StringUtils.getString(id_fecha, FormatoFechaCommon.DIA_MES_ANIO);
		}

		{
			StringBuilder lsb_sb;
			String        ls_idCirculo;
			String        ls_nombreCirculo;

			lsb_sb               = new StringBuilder();
			ls_idCirculo         = lps_ps.getIdCirculo();
			ls_nombreCirculo     = lps_ps.getNombreCirculo();

			if(StringUtils.isValidString(ls_nombreCirculo))
				lsb_sb.append(ls_idCirculo);

			if(StringUtils.isValidString(ls_nombreCirculo))
			{
				if(StringUtils.isValidString(ls_idCirculo))
					lsb_sb.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS + ls_nombreCirculo);
				else
					lsb_sb.append(ls_nombreCirculo);
			}

			is_idCirculo = lsb_sb.toString();
		}

		{
			StringBuilder lsb_sb;
			String        ls_concepto;
			String        ls_descripcionConcepto;

			lsb_sb                     = new StringBuilder();
			ls_concepto                = lps_ps.getConcepto();
			ls_descripcionConcepto     = lps_ps.getDescripcionConcepto();

			if(StringUtils.isValidString(ls_concepto))
				lsb_sb.append(ls_concepto);

			if(StringUtils.isValidString(ls_descripcionConcepto))
			{
				if(StringUtils.isValidString(ls_concepto))
					lsb_sb.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS + ls_descripcionConcepto);
				else
					lsb_sb.append(ls_descripcionConcepto);
			}

			is_concepto = lsb_sb.toString();
		}

		{
			StringBuilder lsb_sb;
			String        ls_forma;
			String        ls_descripcionFormaPago;

			lsb_sb                      = new StringBuilder();
			ls_forma                    = lps_ps.getForma();
			ls_descripcionFormaPago     = lps_ps.getDescripcionFormaPago();

			if(StringUtils.isValidString(ls_forma))
				lsb_sb.append(ls_forma);

			if(StringUtils.isValidString(ls_descripcionFormaPago))
			{
				if(StringUtils.isValidString(ls_forma))
					lsb_sb.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS + ls_descripcionFormaPago);
				else
					lsb_sb.append(ls_descripcionFormaPago);
			}

			is_formaPago = lsb_sb.toString();
		}

		{
			StringBuilder lsb_sb;
			String        ls_banco;
			String        ls_nombreBanco;

			lsb_sb             = new StringBuilder();
			ls_banco           = lps_ps.getBanco();
			ls_nombreBanco     = lps_ps.getNombreBanco();

			if(StringUtils.isValidString(ls_banco))
				lsb_sb.append(ls_banco);

			if(StringUtils.isValidString(ls_nombreBanco))
			{
				if(StringUtils.isValidString(ls_banco))
					lsb_sb.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS + ls_nombreBanco);
				else
					lsb_sb.append(ls_nombreBanco);
			}

			is_banco = lsb_sb.toString();
		}

		{
			StringBuilder lsb_sb;
			String        ls_canal;
			String        ls_descripcionCanal;

			lsb_sb                  = new StringBuilder();
			ls_canal                = StringUtils.getString(lps_ps.getCanal());
			ls_descripcionCanal     = lps_ps.getDescripcionCanal();

			if(StringUtils.isValidString(ls_canal))
				lsb_sb.append(ls_canal);

			if(StringUtils.isValidString(ls_descripcionCanal))
			{
				if(StringUtils.isValidString(ls_canal))
					lsb_sb.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS + ls_descripcionCanal);
				else
					lsb_sb.append(ls_descripcionCanal);
			}

			is_canal = lsb_sb.toString();
		}
	}

	/**
	 * Instancia un nuevo objeto pagos UI.
	 *
	 * @param lpf_pf de lpf pf
	 */
	public PagosUI(PagosFolio lpf_pf)
	{
		is_idTurno        = lpf_pf.getTurnoActualizado();
		id_fecha          = lpf_pf.getFecha();
		is_reciboPago     = StringUtils.getString(lpf_pf.getRecibo());
		is_cheque         = lpf_pf.getCheque();
		is_aprobacion     = StringUtils.getString(lpf_pf.getAprobacion());
		is_valor          = StringUtils.getString(lpf_pf.getValor());

		{
			if(id_fecha != null)
				is_fechaString = StringUtils.getString(id_fecha, FormatoFechaCommon.DIA_MES_ANIO);
		}

		{
			StringBuilder lsb_sb;
			String        ls_idCirculo;
			String        ls_nombreCirculo;

			lsb_sb               = new StringBuilder();
			ls_idCirculo         = lpf_pf.getIdCirculo();
			ls_nombreCirculo     = lpf_pf.getNombreCirculo();

			if(StringUtils.isValidString(ls_nombreCirculo))
				lsb_sb.append(ls_idCirculo);

			if(StringUtils.isValidString(ls_nombreCirculo))
			{
				if(StringUtils.isValidString(ls_idCirculo))
					lsb_sb.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS + ls_nombreCirculo);
				else
					lsb_sb.append(ls_nombreCirculo);
			}

			is_idCirculo = lsb_sb.toString();
		}

		{
			StringBuilder lsb_sb;
			String        ls_concepto;
			String        ls_descripcionConcepto;

			lsb_sb                     = new StringBuilder();
			ls_concepto                = lpf_pf.getConcepto();
			ls_descripcionConcepto     = lpf_pf.getDescripcionConcepto();

			if(StringUtils.isValidString(ls_concepto))
				lsb_sb.append(ls_concepto);

			if(StringUtils.isValidString(ls_descripcionConcepto))
			{
				if(StringUtils.isValidString(ls_concepto))
					lsb_sb.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS + ls_descripcionConcepto);
				else
					lsb_sb.append(ls_descripcionConcepto);
			}

			is_concepto = lsb_sb.toString();
		}

		{
			StringBuilder lsb_sb;
			String        ls_forma;
			String        ls_descripcionFormaPago;

			lsb_sb                      = new StringBuilder();
			ls_forma                    = lpf_pf.getForma();
			ls_descripcionFormaPago     = lpf_pf.getDescripcionFormaPago();

			if(StringUtils.isValidString(ls_forma))
				lsb_sb.append(ls_forma);

			if(StringUtils.isValidString(ls_descripcionFormaPago))
			{
				if(StringUtils.isValidString(ls_forma))
					lsb_sb.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS + ls_descripcionFormaPago);
				else
					lsb_sb.append(ls_descripcionFormaPago);
			}

			is_formaPago = lsb_sb.toString();
		}

		{
			StringBuilder lsb_sb;
			String        ls_banco;
			String        ls_nombreBanco;

			lsb_sb             = new StringBuilder();
			ls_banco           = lpf_pf.getBanco();
			ls_nombreBanco     = lpf_pf.getNombreBanco();

			if(StringUtils.isValidString(ls_banco))
				lsb_sb.append(ls_banco);

			if(StringUtils.isValidString(ls_nombreBanco))
			{
				if(StringUtils.isValidString(ls_banco))
					lsb_sb.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS + ls_nombreBanco);
				else
					lsb_sb.append(ls_nombreBanco);
			}

			is_banco = lsb_sb.toString();
		}

		{
			StringBuilder lsb_sb;
			String        ls_canal;
			String        ls_descripcionCanal;

			lsb_sb                  = new StringBuilder();
			ls_canal                = StringUtils.getString(lpf_pf.getCanal());
			ls_descripcionCanal     = lpf_pf.getDescripcionCanal();

			if(StringUtils.isValidString(ls_canal))
				lsb_sb.append(ls_canal);

			if(StringUtils.isValidString(ls_descripcionCanal))
			{
				if(StringUtils.isValidString(ls_canal))
					lsb_sb.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS + ls_descripcionCanal);
				else
					lsb_sb.append(ls_descripcionCanal);
			}

			is_canal = lsb_sb.toString();
		}
	}

	/**
	 * Retorna Objeto o variable de valor fecha.
	 *
	 * @return Retorna el valor de la propiedad fecha
	 */
	public Date getFecha()
	{
		return id_fecha;
	}

	/**
	 * Modifica el valor de Fecha.
	 *
	 * @param ad_d de ad d
	 */
	public void setFecha(Date ad_d)
	{
		id_fecha = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha string.
	 *
	 * @return Retorna el valor de la propiedad fechaString
	 */
	public String getFechaString()
	{
		return is_fechaString;
	}

	/**
	 * Modifica el valor de Fecha String.
	 *
	 * @param as_s de as s
	 */
	public void setFechaString(String as_s)
	{
		is_fechaString = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor aprobacion.
	 *
	 * @return Retorna el valor de la propiedad aprobacion
	 */
	public String getAprobacion()
	{
		return is_aprobacion;
	}

	/**
	 * Modifica el valor de Aprobacion.
	 *
	 * @param as_s de as s
	 */
	public void setAprobacion(String as_s)
	{
		is_aprobacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor banco.
	 *
	 * @return Retorna el valor de la propiedad banco
	 */
	public String getBanco()
	{
		return is_banco;
	}

	/**
	 * Modifica el valor de Banco.
	 *
	 * @param as_s de as s
	 */
	public void setBanco(String as_s)
	{
		is_banco = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor canal.
	 *
	 * @return Retorna el valor de la propiedad canal
	 */
	public String getCanal()
	{
		return is_canal;
	}

	/**
	 * Modifica el valor de Canal.
	 *
	 * @param as_s de as s
	 */
	public void setCanal(String as_s)
	{
		is_canal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cheque.
	 *
	 * @return Retorna el valor de la propiedad cheque
	 */
	public String getCheque()
	{
		return is_cheque;
	}

	/**
	 * Modifica el valor de Cheque.
	 *
	 * @param as_s de as s
	 */
	public void setCheque(String as_s)
	{
		is_cheque = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor concepto.
	 *
	 * @return Retorna el valor de la propiedad concepto
	 */
	public String getConcepto()
	{
		return is_concepto;
	}

	/**
	 * Modifica el valor de Concepto.
	 *
	 * @param as_s de as s
	 */
	public void setConcepto(String as_s)
	{
		is_concepto = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor forma pago.
	 *
	 * @return Retorna el valor de la propiedad formaPago
	 */
	public String getFormaPago()
	{
		return is_formaPago;
	}

	/**
	 * Modifica el valor de FormaPago.
	 *
	 * @param as_s de as s
	 */
	public void setFormaPago(String as_s)
	{
		is_formaPago = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return Retorna el valor de la propiedad idTurno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s de as s
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor recibo pago.
	 *
	 * @return Retorna el valor de la propiedad reciboPago
	 */
	public String getReciboPago()
	{
		return is_reciboPago;
	}

	/**
	 * Modifica el valor de ReciboPago.
	 *
	 * @param as_s de as s
	 */
	public void setReciboPago(String as_s)
	{
		is_reciboPago = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor valor.
	 *
	 * @return Retorna el valor de la propiedad valor
	 */
	public String getValor()
	{
		return is_valor;
	}

	/**
	 * Modifica el valor de Valor.
	 *
	 * @param as_s de as s
	 */
	public void setValor(String as_s)
	{
		is_valor = as_s;
	}
}
