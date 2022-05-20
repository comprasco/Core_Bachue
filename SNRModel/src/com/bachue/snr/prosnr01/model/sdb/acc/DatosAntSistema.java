package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_DATOS_ANT_SISTEMA.
 *
 * @author lchacon
 */
public class DatosAntSistema extends Auditoria implements Serializable
{
	/**
	 * Constante serialVersionUID.
	 */
	private static final long serialVersionUID = 6616207312408381511L;

	/**
	 * Propiedad icdas detalle ant sistema nueva entrada.
	 */
	private Collection<DetalleAntSistema> icdas_detalleAntSistemaNuevaEntrada;

	/**
	 * Propiedad il anio.
	 */
	private Long il_anio;

	/**
	 * Propiedad il cantidad certificados.
	 */
	private Long il_cantidadCertificados;

	/**
	 * Propiedad il folio.
	 */
	private Long il_folio;

	/**
	 * Propiedad il id libro ant sistema.
	 */
	private Long il_idLibroAntSistema;

	/**
	 * Propiedad il id matricula.
	 */
	private Long il_idMatricula;

	/**
	 * Propiedad il id matricula grabacion.
	 */
	private Long il_idMatriculaGrabacion;

	/**
	 * Propiedad il tomo.
	 */
	private Long il_tomo;

	/**
	 * Propiedad imsb actos.
	 */
	private Map<String, Boolean> imsb_actos;

	/**
	 * Propiedad ims matricula segregacion.
	 */
	private MatriculaSegregacion ims_matriculaSegregacion;

	/**
	 * Propiedad is accion.
	 */
	private String is_accion;

	/**
	 * Propiedad is accion descripcion.
	 */
	private String is_accionDescripcion;

	/**
	 * Propiedad is adquisicion predio.
	 */
	private String is_adquisicionPredio;

	/**
	 * Propiedad is comentario.
	 */
	private String is_comentario;

	/**
	 * Propiedad is consecutivo predio ant sistema.
	 */
	private String is_consecutivoPredioAntSistema;

	/**
	 * Propiedad is id acto.
	 */
	private String is_idActo;

	/**
	 * Propiedad is id circulo.
	 */
	private String is_idCirculo;

	/**
	 * Propiedad is id circulo grabacion.
	 */
	private String is_idCirculoGrabacion;

	/**
	 * Propiedad is id datos ant sistema.
	 */
	private String is_idDatosAntSistema;

	/**
	 * Propiedad is id departamento.
	 */
	private String is_idDepartamento;

	/**
	 * Propiedad is id municipio.
	 */
	private String is_idMunicipio;

	/**
	 * Propiedad is id pais.
	 */
	private String is_idPais;

	/**
	 * Propiedad is id solicitud.
	 */
	private String is_idSolicitud;

	/**
	 * Propiedad is id tipo predio.
	 */
	private String is_idTipoPredio;

	/**
	 * Propiedad is id turno.
	 */
	private String is_idTurno;

	/**
	 * Propiedad is id turno certificado.
	 */
	private String is_idTurnoCertificado;

	/**
	 * Propiedad is id usuario.
	 */
	private String is_idUsuario;

	/**
	 * Propiedad is id vereda.
	 */
	private String is_idVereda;

	/**
	 * Propiedad is matriculas asociadas.
	 */
	private String is_matriculasAsociadas;

	/**
	 * Propiedad is nombre libro.
	 */
	private String is_nombreLibro;

	/**
	 * Propiedad is nombre predio.
	 */
	private String is_nombrePredio;

	/**
	 * Propiedad is observaciones firma libro.
	 */
	private String is_observacionesFirmaLibro;

	/**
	 * Propiedad is partida.
	 */
	private String is_partida;

	/**
	 * Propiedad is requiere firma libro.
	 */
	private String is_requiereFirmaLibro;

	/**
	 * Propiedad is revisado ant sistema.
	 */
	private String is_revisadoAntSistema;

	/**
	 * Propiedad is revisado ant str.
	 */
	private String is_revisadoAntStr;

	/**
	 * Propiedad is solicitar complementación.
	 */
	private String is_solicitarComplementacion;

	/**
	 * Propiedad is es predio inconsistente.
	 */
	private boolean ib_esPredioInconsistente;

	/**
	 * Propiedad ib jsutificacion firma.
	 */
	private boolean ib_justificacionFirma;

	/**
	 * Propiedad ib matricula seleccionada.
	 */
	private boolean ib_matriculaSeleccionada;

	/**
	 * Instancia un nuevo objeto datos ant sistema.
	 */
	public DatosAntSistema()
	{
	}

	/**
	 * Instancia un nuevo objeto datos ant sistema.
	 *
	 * @param as_idTipoPredio de as id tipo predio
	 */
	public DatosAntSistema(String as_idTipoPredio)
	{
		is_idTipoPredio = as_idTipoPredio;
	}

	/**
	 * Modifica el valor de Accion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAccion(String as_s)
	{
		is_accion = as_s;
	}

	/**
	 * Get accion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAccion()
	{
		return is_accion;
	}

	/**
	 * Modifica el valor de Accion descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAccionDescripcion(String as_s)
	{
		is_accionDescripcion = as_s;
	}

	/**
	 * Get accion descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAccionDescripcion()
	{
		return is_accionDescripcion;
	}

	/**
	 * Sets the actos.
	 *
	 * @param amsb_actos asigna el valor a la propiedad
	 */
	public void setActos(Map<String, Boolean> amsb_actos)
	{
		imsb_actos = amsb_actos;
	}

	/**
	 * Get actos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Map<String, Boolean> getActos()
	{
		if(imsb_actos == null)
			imsb_actos = new HashMap<String, Boolean>();

		return imsb_actos;
	}

	/**
	 * Modifica el valor de Adquisicion predio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAdquisicionPredio(String as_s)
	{
		is_adquisicionPredio = as_s;
	}

	/**
	 * Get adquisicion predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAdquisicionPredio()
	{
		return is_adquisicionPredio;
	}

	/**
	 * Modifica el valor de Anio.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setAnio(Long al_l)
	{
		il_anio = al_l;
	}

	/**
	 * Get anio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getAnio()
	{
		return il_anio;
	}

	/**
	 * Modifica el valor de Cantidad certificados.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setCantidadCertificados(Long al_l)
	{
		il_cantidadCertificados = al_l;
	}

	/**
	 * Get cantidad certificados.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getCantidadCertificados()
	{
		return il_cantidadCertificados;
	}

	/**
	 * Modifica el valor de Comentario.
	 *
	 * @param as_c asigna el valor a la propiedad
	 */
	public void setComentario(String as_c)
	{
		is_comentario = as_c;
	}

	/**
	 * Get comentario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getComentario()
	{
		return is_comentario;
	}

	/**
	 * Modifica el valor de Consecutivo predio ant sistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setConsecutivoPredioAntSistema(String as_s)
	{
		is_consecutivoPredioAntSistema = as_s;
	}

	/**
	 * Get consecutivo predio ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getConsecutivoPredioAntSistema()
	{
		return is_consecutivoPredioAntSistema;
	}

	/**
	 * Modifica el valor de DetalleAntSistemaNuevaEntrada
	 *
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setDetalleAntSistemaNuevaEntrada(Collection<DetalleAntSistema> ac_c)
	{
		icdas_detalleAntSistemaNuevaEntrada = ac_c;
	}

	/**
	 * Get DetalleAntSistemaNuevaEntrada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<DetalleAntSistema> getDetalleAntSistemaNuevaEntrada()
	{
		return icdas_detalleAntSistemaNuevaEntrada;
	}

	/**
	 * Modifica el valor de EsPredioInconsistente.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsPredioInconsistente(boolean ab_b)
	{
		ib_esPredioInconsistente = ab_b;
	}

	/**
	 * Valida la propiedad es predio inconsistente.
	 *
	 * @return Retorna el valor de la propiedad esPredioInconsistente
	 */
	public boolean isEsPredioInconsistente()
	{
		return ib_esPredioInconsistente;
	}

	/**
	 * Modifica el valor de Folio.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setFolio(Long al_l)
	{
		il_folio = al_l;
	}

	/**
	 * Get folio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getFolio()
	{
		return il_folio;
	}

	/**
	 * Modifica el valor de Id acto.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdActo(String as_s)
	{
		is_idActo = as_s;
	}

	/**
	 * Get id acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdActo()
	{
		return is_idActo;
	}

	/**
	 * Modifica el valor de Id circulo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Get id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de Id circulo grabacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculoGrabacion(String as_s)
	{
		is_idCirculoGrabacion = as_s;
	}

	/**
	 * Get id circulo grabacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculoGrabacion()
	{
		return is_idCirculoGrabacion;
	}

	/**
	 * Modifica el valor de Id datos ant sistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDatosAntSistema(String as_s)
	{
		is_idDatosAntSistema = as_s;
	}

	/**
	 * Get id datos ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDatosAntSistema()
	{
		return is_idDatosAntSistema;
	}

	/**
	 * Modifica el valor de Id departamento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDepartamento(String as_s)
	{
		is_idDepartamento = as_s;
	}

	/**
	 * Get id departamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de Id libro ant sistema.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdLibroAntSistema(Long al_l)
	{
		il_idLibroAntSistema = al_l;
	}

	/**
	 * Get id libro ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdLibroAntSistema()
	{
		return il_idLibroAntSistema;
	}

	/**
	 * Modifica el valor de Id matricula.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Get id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de Id matricula grabacion.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatriculaGrabacion(Long al_l)
	{
		il_idMatriculaGrabacion = al_l;
	}

	/**
	 * Get id matricula grabacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdMatriculaGrabacion()
	{
		return il_idMatriculaGrabacion;
	}

	/**
	 * Modifica el valor de Id municipio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdMunicipio(String as_s)
	{
		is_idMunicipio = as_s;
	}

	/**
	 * Get id municipio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * Modifica el valor de Id pais.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Get id pais.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de Id solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Get id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de Id tipo predio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoPredio(String as_s)
	{
		is_idTipoPredio = as_s;
	}

	/**
	 * Get id tipo predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoPredio()
	{
		return is_idTipoPredio;
	}

	/**
	 * Modifica el valor de Id turno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Get id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de Id turno certificado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoCertificado(String as_s)
	{
		is_idTurnoCertificado = as_s;
	}

	/**
	 * Get id turno certificado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoCertificado()
	{
		return is_idTurnoCertificado;
	}

	/**
	 * Modifica el valor de Id usuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUsuario(String as_s)
	{
		is_idUsuario = as_s;
	}

	/**
	 * Get id usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}

	/**
	 * Modifica el valor de Id vereda.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdVereda(String as_s)
	{
		is_idVereda = as_s;
	}

	/**
	 * Get id vereda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdVereda()
	{
		return is_idVereda;
	}

	/**
	 * Modifica el valor de Justificacion firma.
	 *
	 * @param ab_b Objeto o Variable de tipo boolean que asigna un valor a la propiedad JustificacionFirma
	 */
	public void setJustificacionFirma(boolean ab_b)
	{
		ib_justificacionFirma = ab_b;
	}

	/**
	 * Valida la propiedad justificacion firma.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isJustificacionFirma()
	{
		return ib_justificacionFirma;
	}

	/**
	 * Modifica el valor de Matricula segregacion.
	 *
	 * @param am_m asigna el valor a la propiedad
	 */
	public void setMatriculaSegregacion(MatriculaSegregacion am_m)
	{
		ims_matriculaSegregacion = am_m;
	}

	/**
	 * Get matricula segregacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public MatriculaSegregacion getMatriculaSegregacion()
	{
		if(ims_matriculaSegregacion == null)
			ims_matriculaSegregacion = new MatriculaSegregacion();

		return ims_matriculaSegregacion;
	}

	/**
	 * Modifica el valor de Matricula seleccionada.
	 *
	 * @param ab_b Objeto o Variable de tipo boolean que asigna un valor a la propiedad MatriculaSeleccionada
	 */
	public void setMatriculaSeleccionada(boolean ab_b)
	{
		ib_matriculaSeleccionada = ab_b;
	}

	/**
	 * Valida la propiedad matricula seleccionada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isMatriculaSeleccionada()
	{
		return ib_matriculaSeleccionada;
	}

	/**
	 * Modifica el valor de Matriculas asociadas.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setMatriculasAsociadas(String as_s)
	{
		is_matriculasAsociadas = as_s;
	}

	/**
	 * Get matriculas asociadas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMatriculasAsociadas()
	{
		return is_matriculasAsociadas;
	}

	/**
	 * Modifica el valor de Nombre libro.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreLibro(String as_s)
	{
		is_nombreLibro = as_s;
	}

	/**
	 * Get nombre libro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreLibro()
	{
		return is_nombreLibro;
	}

	/**
	 * Modifica el valor de Nombre predio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombrePredio(String as_s)
	{
		is_nombrePredio = as_s;
	}

	/**
	 * Get nombre predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombrePredio()
	{
		return is_nombrePredio;
	}

	/**
	 * Modifica el valor de Observaciones firma libro.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservacionesFirmaLibro(String as_s)
	{
		is_observacionesFirmaLibro = as_s;
	}

	/**
	 * Get observaciones firma libro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservacionesFirmaLibro()
	{
		return is_observacionesFirmaLibro;
	}

	/**
	 * Modifica el valor de Partida.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPartida(String as_s)
	{
		is_partida = as_s;
	}

	/**
	 * Get partida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPartida()
	{
		return is_partida;
	}

	/**
	 * Modifica el valor de Requiere firma libro.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRequiereFirmaLibro(String as_s)
	{
		is_requiereFirmaLibro = as_s;
	}

	/**
	 * Get requiere firma libro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRequiereFirmaLibro()
	{
		return is_requiereFirmaLibro;
	}

	/**
	 * Modifica el valor de Revisado ant sistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRevisadoAntSistema(String as_s)
	{
		is_revisadoAntSistema = as_s;
	}

	/**
	 * Get revisado ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRevisadoAntSistema()
	{
		return is_revisadoAntSistema;
	}

	/**
	 * Modifica el valor de Revisado ant str.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRevisadoAntStr(String as_s)
	{
		is_revisadoAntStr = as_s;
	}

	/**
	 * Get revisado ant str.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRevisadoAntStr()
	{
		return is_revisadoAntStr;
	}

	/**
	 * @param solicitarComplementacion Modifica el valor de la propiedad solicitarComplementacion
	 */
	public void setSolicitarComplementacion(String solicitarComplementacion)
	{
		this.is_solicitarComplementacion = solicitarComplementacion;
	}

	/**
	 * @return Retorna el valor de la propiedad solicitarComplementacion
	 */
	public String getSolicitarComplementacion()
	{
		return is_solicitarComplementacion;
	}

	/**
	 * Modifica el valor de Tomo.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setTomo(Long al_l)
	{
		il_tomo = al_l;
	}

	/**
	 * Get tomo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getTomo()
	{
		return il_tomo;
	}
}
