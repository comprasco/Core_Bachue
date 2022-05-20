package com.bachue.snr.prosnr01.model.sdb.acc;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_SOLICITUD_MATRICULA.
 *
 * @author garias
 */
public class SolicitudMatricula extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7257660609022593640L;

	/** Propiedad ibd cantidad certificados. */
	private BigDecimal ibd_cantidadCertificados;

	/** Propiedad il cantidad aperturar. */
	private Long il_cantidadAperturar;

	/** Propiedad isma solicitud matricula acto. */
	private SolicitudMatriculaActo isma_solicitudMatriculaActo;

	/** Propiedad is indicador englobe correcciones. */
	private String ib_indicadorEnglobeCorrecciones;

	/** Propiedad is cedula catastral. */
	private String is_cedulaCatastral;

	/** Propiedad is cementerio. */
	private String is_cementerio;

	/** Propiedad is direccion completa. */
	private String is_direccionCompleta;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is expedir certificado. */
	private String is_expedirCertificado;

	/** Propiedad is id acto seleccionado. */
	private String is_idActoSeleccionado;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id datos ant sistema. */
	private String is_idDatosAntSistema;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id turno certificado. */
	private String is_idTurnoCertificado;

	/** Propiedad is ind mayor valor. */
	private String is_indMayorValor;

	/** Propiedad is matricula concatenada. */
	private String is_matriculaConcatenada;

	/** Propiedad is nir asociado. */
	private String is_nirAsociado;

	/** Propiedad is nombre circulo. */
	private String is_nombreCirculo;

	/** Propiedad is numero predial. */
	private String is_numeroPredial;

	/** Propiedad ith turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/** Propiedad ib alerta. */
	private boolean ib_alerta;

	/** Propiedad ib bloquear. */
	private boolean ib_bloquear;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/** Propiedad il id matricula. */
	private long il_idMatricula;

	/**
	 * Constructor por defecto.
	 */
	public SolicitudMatricula()
	{
	}

	/**
	 * Constructor que recibe el id de la solicitud, el id del circulo y el id de la matricula.
	 *
	 * @param as_idSolicitud id solicitud
	 * @param as_idcirculo id circulo
	 * @param al_idMatricula id matricula
	 */
	public SolicitudMatricula(String as_idSolicitud, String as_idcirculo, long al_idMatricula)
	{
		setIdSolicitud(as_idSolicitud);
		setIdCirculo(as_idcirculo);
		setIdMatricula(al_idMatricula);
	}

	/**
	 * Modifica el valor de Alerta.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setAlerta(boolean ab_b)
	{
		ib_alerta = ab_b;
	}

	/**
	 * Valida la propiedad alerta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isAlerta()
	{
		return ib_alerta;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad bloquear
	 */
	public void setBloquear(boolean ab_b)
	{
		ib_bloquear = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad bloquear
	 */
	public boolean isBloquear()
	{
		return ib_bloquear;
	}

	/**
	 * Modifica el valor de CantidadAperturar.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setCantidadAperturar(Long al_l)
	{
		il_cantidadAperturar = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad aperturar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getCantidadAperturar()
	{
		return il_cantidadAperturar;
	}

	/**
	 * Modifica el valor de CantidadCertificados.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setCantidadCertificados(BigDecimal abd_bd)
	{
		ibd_cantidadCertificados = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad certificados.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getCantidadCertificados()
	{
		return ibd_cantidadCertificados;
	}

	/**
	 * Modifica el valor de CedulaCatastral.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCedulaCatastral(String as_s)
	{
		is_cedulaCatastral = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cedula catastral.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCedulaCatastral()
	{
		return is_cedulaCatastral;
	}

	/**
	 * Modifica el valor de Cementerio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCementerio(String as_s)
	{
		is_cementerio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cementerio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCementerio()
	{
		return is_cementerio;
	}

	/**
	 * Modifica el valor de DireccionCompleta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDireccionCompleta(String as_s)
	{
		is_direccionCompleta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor direccion completa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDireccionCompleta()
	{
		return is_direccionCompleta;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de ExpedirCertificado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setExpedirCertificado(String as_s)
	{
		is_expedirCertificado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor expedir certificado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getExpedirCertificado()
	{
		return is_expedirCertificado;
	}

	/**
	 * Modifica el valor de IdActoSeleccionado.
	 *
	 * @param as_s de as s
	 */
	public void setIdActoSeleccionado(String as_s)
	{
		is_idActoSeleccionado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id acto seleccionado.
	 *
	 * @return Retorna el valor de la propiedad idActoSeleccionado
	 */
	public String getIdActoSeleccionado()
	{
		return is_idActoSeleccionado;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdDatosAntSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDatosAntSistema(String as_s)
	{
		is_idDatosAntSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id datos ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDatosAntSistema()
	{
		return is_idDatosAntSistema;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula(long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdTurnoCertificado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoCertificado(String as_s)
	{
		is_idTurnoCertificado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno certificado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoCertificado()
	{
		return is_idTurnoCertificado;
	}

	/**
	 * Modifica el valor de IndMayorValor.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIndMayorValor(String as_s)
	{
		is_indMayorValor = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor ind mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIndMayorValor()
	{
		return is_indMayorValor;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad indicadorEnglobeCorrecciones
	 */
	public void setIndicadorEnglobeCorrecciones(String as_s)
	{
		ib_indicadorEnglobeCorrecciones = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad indicadorEnglobeCorrecciones
	 */
	public String isIndicadorEnglobeCorrecciones()
	{
		return ib_indicadorEnglobeCorrecciones;
	}

	/**
	 * Modifica el valor de MatriculaConcatenada.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setMatriculaConcatenada(String as_s)
	{
		is_matriculaConcatenada = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor matricula concatenada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMatriculaConcatenada()
	{
		return is_matriculaConcatenada;
	}

	/**
	 * Modifica el valor de NirAsociado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNirAsociado(String as_s)
	{
		is_nirAsociado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir asociado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNirAsociado()
	{
		return is_nirAsociado;
	}

	/**
	 * Modifica el valor de NombreCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setNombreCirculo(String as_s)
	{
		is_nombreCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre circulo.
	 *
	 * @return Retorna el valor de la propiedad nombreCirculo
	 */
	public String getNombreCirculo()
	{
		return is_nombreCirculo;
	}

	/**
	 * Modifica el valor de NumeroPredial.
	 *
	 * @param as_s de as s
	 */
	public void setNumeroPredial(String as_s)
	{
		is_numeroPredial = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero predial.
	 *
	 * @return Retorna el valor de la propiedad numeroPredial
	 */
	public String getNumeroPredial()
	{
		return is_numeroPredial;
	}

	/**
	 * Modifica el valor de Seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Modifica el valor de SolicitudMatriculaActo.
	 *
	 * @param asma_sma asigna el valor a la propiedad
	 */
	public void setSolicitudMatriculaActo(SolicitudMatriculaActo asma_sma)
	{
		isma_solicitudMatriculaActo = asma_sma;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud matricula acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public SolicitudMatriculaActo getSolicitudMatriculaActo()
	{
		return isma_solicitudMatriculaActo;
	}

	/**
	 * Modifica el valor de TurnoHistoria.
	 *
	 * @param ath_th asigna el valor a la propiedad
	 */
	public void setTurnoHistoria(TurnoHistoria ath_th)
	{
		ith_turnoHistoria = ath_th;
	}

	/**
	 * Retorna Objeto o variable de valor turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public TurnoHistoria getTurnoHistoria()
	{
		return ith_turnoHistoria;
	}

	/** {@inheritdoc} */
	@Override
	public boolean equals(Object asm_matricula)
	{
		boolean lb_return;

		lb_return = false;

		if(asm_matricula != null)
		{
			String ls_idCirculo;
			String ls_idMatricula;

			ls_idCirculo       = this.is_idCirculo;
			ls_idMatricula     = StringUtils.getString(il_idMatricula);

			if(asm_matricula instanceof SolicitudMatricula)
			{
				SolicitudMatricula lsm_matricula;

				lsm_matricula = (SolicitudMatricula)asm_matricula;

				if(lsm_matricula != null)
				{
					String ls_idCirculoComparar;
					String ls_idMatriculaComparar;

					ls_idCirculoComparar       = lsm_matricula.getIdCirculo();
					ls_idMatriculaComparar     = StringUtils.getString(lsm_matricula.getIdMatricula());

					lb_return = ls_idCirculo.equalsIgnoreCase(ls_idCirculoComparar)
							&& ls_idMatricula.equalsIgnoreCase(ls_idMatriculaComparar);
				}
			}
			else if(asm_matricula instanceof AlertaTitular)
			{
				AlertaTitular lsm_matricula;

				lsm_matricula = (AlertaTitular)asm_matricula;

				if(lsm_matricula != null)
				{
					String ls_idCirculoComparar;
					String ls_idMatriculaComparar;

					ls_idCirculoComparar       = lsm_matricula.getIdCirculo();
					ls_idMatriculaComparar     = StringUtils.getString(lsm_matricula.getIdMatricula());

					lb_return = ls_idCirculo.equalsIgnoreCase(ls_idCirculoComparar)
							&& ls_idMatricula.equalsIgnoreCase(ls_idMatriculaComparar);
				}
			}
		}

		return lb_return;
	}

	public int hashCode()
	{
		int li_hashCode;

		li_hashCode = 1;

		{
			String ls_s;

			ls_s = getIdCirculo();

			if(ls_s != null)
				li_hashCode += ls_s.hashCode();
		}

		li_hashCode += NumericUtils.getInt(getIdMatricula());

		return li_hashCode;
	}
}
