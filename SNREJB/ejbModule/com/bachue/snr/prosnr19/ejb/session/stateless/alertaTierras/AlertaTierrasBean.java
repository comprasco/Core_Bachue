package com.bachue.snr.prosnr19.ejb.session.stateless.alertaTierras;

import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoEntradaActivarAlertaTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1.TipoSalidaActivarAlertaTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoEntradaAgregarListaMatriculasAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarListaMatriculasAlerta.v1.TipoSalidaAgregarListaMatriculasAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoEntradaAgregarMatriculaAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.agregarMatriculaAlerta.v1.TipoSalidaAgregarMatriculaAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoEntradaCancelarIngresoAlertaTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.cancelarIngresoAlertaTierras.v1.TipoSalidaCancelarIngresoAlertaTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoEntradaConsultarAlertas;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertas;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoEntradaConsultarDetalleAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1.TipoSalidaConsultarDetalleAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoEntradaConsultarDocumentoAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDocumentoAlerta.v1.TipoSalidaConsultarDocumentoAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.EntradaConsultarEntidadesJ_A;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1.TipoSalidaConsultarEntidadesJ_A;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoEntradaConsultarListaMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoSalidaConsultarListaMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoEntradaConsultarMatricula;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatricula.v1.TipoSalidaConsultarMatricula;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoEntradaConsultarMatriculaAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaAlerta.v1.TipoSalidaConsultarMatriculaAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoEntradaConsultarMatriculaFiltrosTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1.TipoSalidaConsultarMatriculaFiltrosTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoEntradaConsultarMatriculaInfoCatastral;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaInfoCatastral.v1.TipoSalidaConsultarMatriculaICatastral;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoEntradaConsultarOficinasOrigenTipo;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1.TipoSalidaConsultarOficinasOrigenTipo;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoEntradaCrearProcAntiguoSistemaTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1.TipoSalidaCrearProcAntiguoSistemaTierras;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoEntradaEliminarProcAntiguoSistema;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.eliminarProcAntiguoSistema.v1.TipoSalidaEliminarProcAntiguoSistema;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoEntradaInactivarAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoSalidaInactivarAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoEntradaInscribirAlertaCabecera;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1.TipoSalidaInscribirAlertaCabecera;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoEntradaListarProcAntiguoSistema;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistema;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoEntradaRechazarCorreccionAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.rechazarCorreccionAlerta.v1.TipoSalidaRechazarCorreccionAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoEntradaRemoverMatriculaAlerta;
import co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.removerMatriculaAlerta.v1.TipoSalidaRemoverMatriculaAlerta;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr19.business.alertaTierras.AlertaTierrasBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades AlertaTierrasBean.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 7/04/2020
 */
@javax.ejb.Stateless(name = "AlertaTierras", mappedName = "alertaTierrasMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class AlertaTierrasBean implements AlertaTierrasRemote
{
	/** Propiedad iatb business. */
	private AlertaTierrasBusiness iatb_business;

	/**
	 * Retorna Objeto o variable de valor alerta tierras business.
	 *
	 * @return el valor de alerta tierras business
	 */
	public AlertaTierrasBusiness getAlertaTierrasBusiness()
	{
		if(iatb_business == null)
			iatb_business = new AlertaTierrasBusiness();

		return iatb_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaActivarAlertaTierras activarAlertaTierras(
	    TipoEntradaActivarAlertaTierras ateaat_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		TipoSalidaActivarAlertaTierras ltsaat_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsaat_return     = getAlertaTierrasBusiness()
				                    .activarAlertaTierras(ateaat_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "AlertaTierrasBean", "activarAlertaTierras", as_userId, as_localIp, as_remoteIp, null);

		return ltsaat_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaAgregarListaMatriculasAlerta agregarListaMatriculasAlerta(
	    TipoEntradaAgregarListaMatriculasAlerta atealma_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                              lsw_watch;
		TipoSalidaAgregarListaMatriculasAlerta ltsalma_return;

		lsw_watch          = Logger.getNewStopWatch();
		ltsalma_return     = getAlertaTierrasBusiness()
				                     .agregarListaMatriculasAlerta(atealma_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "AlertaTierrasBean", "agregarListaMatriculasAlerta", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsalma_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaAgregarMatriculaAlerta agregarMatriculaAlerta(
	    TipoEntradaAgregarMatriculaAlerta ateama_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		TipoSalidaAgregarMatriculaAlerta ltsama_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsama_return     = getAlertaTierrasBusiness()
				                    .agregarMatriculaAlerta(ateama_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "AlertaTierrasBean", "agregarMatriculaAlerta", as_userId, as_localIp, as_remoteIp, null);

		return ltsama_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaCancelarIngresoAlertaTierras cancelarIngresoAlertaTierras(
	    TipoEntradaCancelarIngresoAlertaTierras ateciat_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                              lsw_watch;
		TipoSalidaCancelarIngresoAlertaTierras ltsciat_return;

		lsw_watch          = Logger.getNewStopWatch();
		ltsciat_return     = getAlertaTierrasBusiness()
				                     .cancelarIngresoAlertaTierras(ateciat_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "AlertaTierrasBean", "cancelarIngresoAlertaTierras", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsciat_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarAlertas consultarAlertas(
	    TipoEntradaConsultarAlertas ateca_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		TipoSalidaConsultarAlertas ltsca_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltsca_return     = getAlertaTierrasBusiness().consultarAlertas(
			    ateca_entrada, as_userId, as_localIp, as_remoteIp
			);

		Logger.log(lsw_watch, "AlertaTierrasBean", "consultarAlertas", as_userId, as_localIp, as_remoteIp, null);

		return ltsca_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarDetalleAlerta consultarDetalleAlerta(
	    TipoEntradaConsultarDetalleAlerta atecda_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		TipoSalidaConsultarDetalleAlerta ltscda_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltscda_return     = getAlertaTierrasBusiness()
				                    .consultarDetalleAlerta(atecda_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "AlertaTierrasBean", "consultarDetalleAlerta", as_userId, as_localIp, as_remoteIp, null);

		return ltscda_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarDocumentoAlerta consultarDocumentoAlerta(
	    TipoEntradaConsultarDocumentoAlerta atecda_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		TipoSalidaConsultarDocumentoAlerta ltscda_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltscda_return     = getAlertaTierrasBusiness()
				                    .consultarDocumentoAlerta(atecda_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "AlertaTierrasBean", "consultarDocumentoAlerta", as_userId, as_localIp, as_remoteIp, null
		);

		return ltscda_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarEntidadesJ_A consultarEntidadesJ_A(
	    EntradaConsultarEntidadesJ_A aece_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		TipoSalidaConsultarEntidadesJ_A ltsceja_return;

		lsw_watch          = Logger.getNewStopWatch();
		ltsceja_return     = getAlertaTierrasBusiness()
				                     .consultarEntidadesJ_A(aece_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "AlertaTierrasBean", "consultarEntidadesJ_A", as_userId, as_localIp, as_remoteIp, null);

		return ltsceja_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarListaMatriculas consultarListaMatriculas(
	    TipoEntradaConsultarListaMatriculas ateclm_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		TipoSalidaConsultarListaMatriculas ltsclm_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsclm_return     = getAlertaTierrasBusiness()
				                    .consultarListaMatriculas(ateclm_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "AlertaTierrasBean", "consultarListaMatriculas", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsclm_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarMatricula consultarMatricula(
	    TipoEntradaConsultarMatricula atecm_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		TipoSalidaConsultarMatricula ltscm_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltscm_return     = getAlertaTierrasBusiness()
				                   .consultarMatricula(atecm_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "AlertaTierrasBean", "consultarMatricula", as_userId, as_localIp, as_remoteIp, null);

		return ltscm_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarMatriculaAlerta consultarMatriculaAlerta(
	    TipoEntradaConsultarMatriculaAlerta atecma_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		TipoSalidaConsultarMatriculaAlerta ltscma_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltscma_return     = getAlertaTierrasBusiness()
				                    .consultarMatriculaAlerta(atecma_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "AlertaTierrasBean", "consultarMatriculaAlerta", as_userId, as_localIp, as_remoteIp, null
		);

		return ltscma_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarMatriculaFiltrosTierras consultarMatriculaFiltrosTierras(
	    TipoEntradaConsultarMatriculaFiltrosTierras atecmft_entrada, String as_userId, String as_localIp,
	    String                                      as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                  lsw_watch;
		TipoSalidaConsultarMatriculaFiltrosTierras ltscmft_return;

		lsw_watch          = Logger.getNewStopWatch();
		ltscmft_return     = getAlertaTierrasBusiness()
				                     .consultarMatriculaFiltrosTierras(
				    atecmft_entrada, as_userId, as_localIp, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "AlertaTierrasBean", "consultarMatriculaFiltrosTierras", as_userId, as_localIp, as_remoteIp, null
		);

		return ltscmft_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarMatriculaICatastral consultarMatriculaInfoCatastral(
	    TipoEntradaConsultarMatriculaInfoCatastral atecmic_entrada, String as_userId, String as_localIp,
	    String                                     as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                              lsw_watch;
		TipoSalidaConsultarMatriculaICatastral ltscmic_return;

		lsw_watch          = Logger.getNewStopWatch();
		ltscmic_return     = getAlertaTierrasBusiness()
				                     .consultarMatriculaInfoCatastral(
				    atecmic_entrada, as_userId, as_localIp, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "AlertaTierrasBean", "consultarMatriculaInfoCatastral", as_userId, as_localIp, as_remoteIp, null
		);

		return ltscmic_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarOficinasOrigenTipo consultarOficinasOrigenTipo(
	    TipoEntradaConsultarOficinasOrigenTipo atecoot_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                             lsw_watch;
		TipoSalidaConsultarOficinasOrigenTipo ltscoot_return;

		lsw_watch          = Logger.getNewStopWatch();
		ltscoot_return     = getAlertaTierrasBusiness()
				                     .consultarOficinasOrigenTipo(atecoot_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "AlertaTierrasBean", "consultarOficinasOrigenTipo", as_userId, as_localIp, as_remoteIp, null
		);

		return ltscoot_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaCrearProcAntiguoSistemaTierras crearProcAntiguoSistemaTierras(
	    TipoEntradaCrearProcAntiguoSistemaTierras atecpast_entrada, String as_userId, String as_localIp,
	    String                                    as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                lsw_watch;
		TipoSalidaCrearProcAntiguoSistemaTierras ltspast_return;

		lsw_watch          = Logger.getNewStopWatch();
		ltspast_return     = getAlertaTierrasBusiness()
				                     .crearProcAntiguoSistemaTierras(
				    atecpast_entrada, as_userId, as_localIp, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "AlertaTierrasBean", "crearProcAntiguoSistemaTierras", as_userId, as_localIp, as_remoteIp, null
		);

		return ltspast_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaEliminarProcAntiguoSistema eliminarProcAntiguoSistema(
	    TipoEntradaEliminarProcAntiguoSistema ateepas_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		TipoSalidaEliminarProcAntiguoSistema ltsepas_return;

		lsw_watch          = Logger.getNewStopWatch();
		ltsepas_return     = getAlertaTierrasBusiness()
				                     .eliminarProcAntiguoSistema(ateepas_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "AlertaTierrasBean", "eliminarProcAntiguoSistema", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsepas_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaInactivarAlerta inactivarAlerta(
	    TipoEntradaInactivarAlerta ateia_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		TipoSalidaInactivarAlerta ltsia_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltsia_return     = getAlertaTierrasBusiness().inactivarAlerta(
			    ateia_entrada, as_userId, as_localIp, as_remoteIp
			);

		Logger.log(lsw_watch, "AlertaTierrasBean", "inactivarAlerta", as_userId, as_localIp, as_remoteIp, null);

		return ltsia_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaInscribirAlertaCabecera inscribirAlertaCabecera(
	    TipoEntradaInscribirAlertaCabecera ateiac_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                         lsw_watch;
		TipoSalidaInscribirAlertaCabecera ltsiac_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsiac_return     = getAlertaTierrasBusiness()
				                    .inscribirAlertaCabecera(ateiac_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "AlertaTierrasBean", "inscribirAlertaCabecera", as_userId, as_localIp, as_remoteIp, null);

		return ltsiac_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaListarProcAntiguoSistema listarProcAntiguoSistema(
	    TipoEntradaListarProcAntiguoSistema atelpas_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		TipoSalidaListarProcAntiguoSistema ltslpas_return;

		lsw_watch          = Logger.getNewStopWatch();
		ltslpas_return     = getAlertaTierrasBusiness()
				                     .listarProcAntiguoSistema(atelpas_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "AlertaTierrasBean", "listarProcAntiguoSistema", as_userId, as_localIp, as_remoteIp, null
		);

		return ltslpas_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaRechazarCorreccionAlerta rechazarCorreccionAlerta(
	    TipoEntradaRechazarCorreccionAlerta aterca_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		TipoSalidaRechazarCorreccionAlerta ltsrca_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsrca_return     = getAlertaTierrasBusiness()
				                    .rechazarCorreccionAlerta(aterca_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "AlertaTierrasBean", "rechazarCorreccionAlerta", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsrca_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaRemoverMatriculaAlerta removerMatriculaAlerta(
	    TipoEntradaRemoverMatriculaAlerta aterma_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		TipoSalidaRemoverMatriculaAlerta ltsrma_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsrma_return     = getAlertaTierrasBusiness()
				                    .removerMatriculaAlerta(aterma_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "AlertaTierrasBean", "removerMatriculaAlerta", as_userId, as_localIp, as_remoteIp, null);

		return ltsrma_return;
	}
}
