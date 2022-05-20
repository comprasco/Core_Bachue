package com.bachue.snr.prosnr01.ejb.session.stateless.aprobacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.aprobacion.AprobacionBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.aprobacion.AprobacionRemote;

import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.sdb.acc.CambioEstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.TrasladoMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import org.perf4j.StopWatch;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades AprobacionBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "Aprobacion", mappedName = "aprobacionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class AprobacionBean implements AprobacionRemote
{
	/** Propiedad irb business. */
	private AprobacionBusiness irb_business;

	/** {@inheritdoc} */
	public void actualizarEtapaYCrearSiguiente(
	    TurnoHistoria ath_parametros, MotivoTramite amt_motivoTramite, String as_usuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getBusiness().actualizarEtapaYCrearSiguiente(ath_parametros, amt_motivoTramite, as_usuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "AprobacionBean", "actualizarEtapaYCrearSiguiente", as_usuario, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void actualizarTurnoHistoriaRelacion(
	    Aprobacion aca_param, String as_usuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getBusiness().actualizarTurnoHistoriaRelacion(aca_param, as_usuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "AprobacionBean", "actualizarTurnoHistoriaRelacion", as_usuario, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public boolean adjudicacionRemate(
	    Long al_idTurnoHistoria, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException, Exception
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getBusiness().adjudicacionRemate(al_idTurnoHistoria);

		Logger.log(
		    lsw_watch, "AprobacionBean", "adjudicacionRemate", as_userId, as_localIpAddress, as_remoteIpAddress, null
		);

		return lb_return;
	}

	/** {@inheritdoc} */
	public void aprobarTrasladoEtapa660(
	    long al_idTurnoHistoria, String as_ObservacionesTramite, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getBusiness().aprobarTrasladoEtapa660(al_idTurnoHistoria, as_ObservacionesTramite, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "AprobacionBean", "aprobarTrasladoEtapa660", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public CambioEstadoPredio cargarJustificacionCierreFolio(CambioEstadoPredio acep_arg)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		CambioEstadoPredio lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getBusiness().cargarJustificacionCierreFolio(acep_arg);

		Logger.log(lsw_watch, "AprobacionBean", "cargarJustificacionCierreFolio", null, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public Collection<TrasladoMatricula> consultarTrasladosMatriculas(
	    Long al_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<TrasladoMatricula> lctm_return;

		lsw_watch       = Logger.getNewStopWatch();
		lctm_return     = getBusiness().consultarTrasladosMatriculas(al_idTurnoHistoria, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AprobacionBean", "consultarTrasladosMatriculas", as_userId, as_localIp, as_remoteIp, null
		);

		return lctm_return;
	}

	/** {@inheritdoc} */
	public void devolverAprobacion(Aprobacion aa_data, String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().devolverAprobacion(aa_data, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "AprobacionBean", "devolverAprobacion", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void ejecutarTrasladoProcedimiento(
	    Long al_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getBusiness().ejecutarTrasladoProcedimiento(al_idTurnoHistoria, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AprobacionBean", "ejecutarTrasladoProcedimiento", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public boolean esRealizarRegistro(Long al_idTurnoHistoria)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getBusiness().esRealizarRegistro(al_idTurnoHistoria);

		Logger.log(lsw_watch, "AprobacionBean", "esRealizarRegistro", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public Collection<Aprobacion> findAllData(Aprobacion aa_oa, boolean ab_conUsuario)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Aprobacion> lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getBusiness().findAllData(aa_oa, ab_conUsuario);

		Logger.log(lsw_watch, "AprobacionBean", "findAllData", null, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<Aprobacion> findDetalleBandeja(
	    Aprobacion aa_oa, boolean ab_bandejaSubproceso, boolean ab_conUsuario, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Aprobacion> lca_registro;

		lsw_watch        = Logger.getNewStopWatch();
		lca_registro     = getBusiness().findDetalleBandeja(aa_oa, ab_bandejaSubproceso, ab_conUsuario,as_userId);

		Logger.log(lsw_watch, "AprobacionBean", "findDetalleBandeja", as_userId, as_localIp, as_remoteIp, lca_registro);

		return lca_registro;
	}

	/** {@inheritdoc} */
	public Integer findTurnosCantidad(
	    String as_estado, String as_userId, long al_etapa, String as_proceso, boolean ab_bandejaSubprocesos
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Integer   li_return;

		lsw_watch     = Logger.getNewStopWatch();
		li_return     = getBusiness()
				                .findTurnosCantidad(as_estado, as_userId, al_etapa, as_proceso, ab_bandejaSubprocesos);

		Logger.log(lsw_watch, "AprobacionBean", "findTurnosCantidad", as_userId, null, null, null);

		return li_return;
	}

	/** {@inheritdoc} */
	public byte[] generarArchivoZipAprobacionRelacion(Map<String, byte[]> aba_ba)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_zip;

		lsw_watch     = Logger.getNewStopWatch();
		lba_zip       = getBusiness().generarArchivoZipAprobacionRelacion(aba_ba);

		Logger.log(lsw_watch, "AprobacionBean", "generarArchivoZipAprobacionRelacion", null, null, null, null);

		return lba_zip;
	}

	/** {@inheritdoc} */
	public Map<String, byte[]> generarDocumentoRelacionAprobacion(
	    Collection<Aprobacion> aca_aprobaciones, boolean ab_firmar, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException, Exception
	{
		StopWatch           lsw_watch;
		Map<String, byte[]> llhm_documentos;

		lsw_watch           = Logger.getNewStopWatch();
		llhm_documentos     = getBusiness()
				                      .generarDocumentoRelacionAprobacion(
				    aca_aprobaciones, ab_firmar, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "AprobacionBean", "generarDocumentoRelacionAprobacion", as_userId, as_localIp, as_remoteIp, null
		);

		return llhm_documentos;
	}

	/** {@inheritdoc} */
	public Aprobacion generateZip(Aprobacion aoa_oa)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Aprobacion lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getBusiness().generateZip(aoa_oa);

		Logger.log(lsw_watch, "AprobacionBean", "generateZip", null, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public void inactivarMatriculaTrasladoById(
	    TrasladoMatricula atm_matricula, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getBusiness().inactivarMatriculaTrasladoById(atm_matricula, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AprobacionBean", "inactivarMatriculaTrasladoById", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void insertarTipoDecisionRecurso(
	    Collection<Aprobacion> aca_data, String ls_idTipoDecisionRecurso, String as_idUsuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().insertarTipoDecisionRecurso(aca_data, ls_idTipoDecisionRecurso, as_idUsuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "AprobacionBean", "insertarTipoDecisionRecurso", as_idUsuario, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public Object[] mensajeAdjudicacionRemate(
	    Long al_idTurnoHistoria, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException, Exception
	{
		StopWatch lsw_watch;
		Object[]  loa_return;

		lsw_watch      = Logger.getNewStopWatch();
		loa_return     = getBusiness().mensajeAdjudicacionRemate(al_idTurnoHistoria);

		Logger.log(
		    lsw_watch, "AprobacionBean", "mensajeAdjudicacionRemate", as_userId, as_localIpAddress, as_remoteIpAddress,
		    null
		);

		return loa_return;
	}

	/** {@inheritdoc} */
	public Collection<DocumentosSalida> obtenerDocumentosPorTurno(
	    String al_idTurnoHistoria, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<DocumentosSalida> lcds_return;

		lsw_watch       = Logger.getNewStopWatch();
		lcds_return     = getBusiness().obtenerDocumentosPorTurno(al_idTurnoHistoria);

		Logger.log(
		    lsw_watch, "AprobacionBean", "obtenerDocumentosPorTurno", as_userId, as_localIpAddress, as_remoteIpAddress,
		    lcds_return
		);

		return lcds_return;
	}

	/** {@inheritdoc} */
	public Aprobacion salvarAprobacion(
	    Collection<Aprobacion> aca_param, String as_usuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Aprobacion la_registro;

		lsw_watch       = Logger.getNewStopWatch();
		la_registro     = getBusiness().salvarAprobacion(aca_param, as_usuario, as_remoteIp);

		Logger.log(lsw_watch, "AprobacionBean", "salvarAprobacion", as_usuario, as_localIp, as_remoteIp, null);

		return la_registro;
	}

	/** {@inheritdoc} */
	public BigDecimal validacionEtapaAnteriorAprobacionAS(
	    Aprobacion aoa_oa, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		BigDecimal lbd_bd;

		lsw_watch     = Logger.getNewStopWatch();
		lbd_bd        = getBusiness().validacionEtapaAnteriorAprobacionAS(aoa_oa);

		Logger.log(
		    lsw_watch, "AprobacionBean", "validacionEtapaAnteriorAprobacionAS", as_idUsuario, as_localIp, as_remoteIp,
		    null
		);

		return lbd_bd;
	}

	/** {@inheritdoc} */
	public Aprobacion validacionEtapaAnteriorAprobacionRecursos(
	    Collection<Aprobacion> aca_aprobaciones, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Aprobacion la_data;

		lsw_watch     = Logger.getNewStopWatch();
		la_data       = getBusiness().validacionEtapaAnteriorAprobacionRecursos(aca_aprobaciones);

		Logger.log(
		    lsw_watch, "AprobacionBean", "validacionEtapaAnteriorAprobacionRecursos", as_idUsuario, as_localIp,
		    as_remoteIp, null
		);

		return la_data;
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private AprobacionBusiness getBusiness()
	{
		if(irb_business == null)
			irb_business = new AprobacionBusiness();

		return irb_business;
	}
}
