/**
 * BS_SDI_CB_AlertaTierras_BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.alertaTierras.v1;

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

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades BS_SDI_CB_AlertaTierras_BindingImpl.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class BS_SDI_CB_AlertaTierras_BindingImpl extends BaseServices implements BS_SDI_CB_AlertaTierras_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1236305052587614914L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BS_SDI_CB_AlertaTierras_BindingImpl.class, ProyectosCommon.ALERTA_TIERRAS_19);

	/** {@inheritdoc} */
	public TipoSalidaActivarAlertaTierras activarAlertaTierras(TipoEntradaActivarAlertaTierras ateaat_entrada)
	    throws RemoteException
	{
		TipoSalidaActivarAlertaTierras ltsaat_salida;

		ltsaat_salida = new TipoSalidaActivarAlertaTierras();

		try
		{
			ltsaat_salida = getAlertaTierrasRemote()
					                .activarAlertaTierras(
					    ateaat_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("activarAlertaTierras", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("activarAlertaTierras", le_e);
		}

		return ltsaat_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaAgregarMatriculaAlerta agregarMatriculaAlerta(TipoEntradaAgregarMatriculaAlerta ateama_entrada)
	    throws RemoteException
	{
		TipoSalidaAgregarMatriculaAlerta ltama_salida;

		ltama_salida = new TipoSalidaAgregarMatriculaAlerta();

		try
		{
			ltama_salida = getAlertaTierrasRemote()
					               .agregarMatriculaAlerta(
					    ateama_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarMatriculaAlerta", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("agregarMatriculaAlerta", le_e);
		}

		return ltama_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaCancelarIngresoAlertaTierras cancelarIngresoAlertaTierras(
	    TipoEntradaCancelarIngresoAlertaTierras ateciat_entrada
	)
	    throws RemoteException
	{
		TipoSalidaCancelarIngresoAlertaTierras ltsciat_salida;

		ltsciat_salida = new TipoSalidaCancelarIngresoAlertaTierras();

		try
		{
			ltsciat_salida = getAlertaTierrasRemote()
					                 .cancelarIngresoAlertaTierras(
					    ateciat_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cancelarIngresoAlertaTierras", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("cancelarIngresoAlertaTierras", le_e);
		}

		return ltsciat_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarAlertas consultarAlertas(TipoEntradaConsultarAlertas ateca_entrada)
	    throws RemoteException
	{
		TipoSalidaConsultarAlertas ltsca_salida;

		ltsca_salida = new TipoSalidaConsultarAlertas();

		try
		{
			ltsca_salida = getAlertaTierrasRemote()
					               .consultarAlertas(
					    ateca_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarAlertas", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarAlertas", le_e);
		}

		return ltsca_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarDetalleAlerta consultarDetalleAlerta(TipoEntradaConsultarDetalleAlerta atecda_entrada)
	    throws RemoteException
	{
		TipoSalidaConsultarDetalleAlerta ltscda_salida;

		ltscda_salida = new TipoSalidaConsultarDetalleAlerta();

		try
		{
			ltscda_salida = getAlertaTierrasRemote()
					                .consultarDetalleAlerta(
					    atecda_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarDetalleAlerta", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarDetalleAlerta", le_e);
		}

		return ltscda_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarDocumentoAlerta consultarDocumentoAlerta(
	    TipoEntradaConsultarDocumentoAlerta atecda_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultarDocumentoAlerta ltscda_salida;

		ltscda_salida = new TipoSalidaConsultarDocumentoAlerta();

		try
		{
			ltscda_salida = getAlertaTierrasRemote()
					                .consultarDocumentoAlerta(
					    atecda_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarDocumentoAlerta", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarDocumentoAlerta", le_e);
		}

		return ltscda_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarEntidadesJ_A consultarEntidadesJ_A(EntradaConsultarEntidadesJ_A aece_entrada)
	    throws RemoteException
	{
		TipoSalidaConsultarEntidadesJ_A ltsce_salida;

		ltsce_salida = new TipoSalidaConsultarEntidadesJ_A();

		try
		{
			ltsce_salida = getAlertaTierrasRemote()
					               .consultarEntidadesJ_A(
					    aece_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarEntidadesJ_A", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarEntidadesJ_A", le_e);
		}

		return ltsce_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarListaMatriculas consultarListaMatriculas(
	    TipoEntradaConsultarListaMatriculas ateclm_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultarListaMatriculas ltsclm_salida;

		ltsclm_salida = new TipoSalidaConsultarListaMatriculas();

		try
		{
			ltsclm_salida = getAlertaTierrasRemote()
					                .consultarListaMatriculas(
					    ateclm_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarListaMatriculas", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarListaMatriculas", le_e);
		}

		return ltsclm_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarMatricula consultarMatricula(TipoEntradaConsultarMatricula atecm_entrada)
	    throws RemoteException
	{
		TipoSalidaConsultarMatricula ltscm_salida;

		ltscm_salida = new TipoSalidaConsultarMatricula();

		try
		{
			ltscm_salida = getAlertaTierrasRemote()
					               .consultarMatricula(
					    atecm_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarMatricula", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarMatricula", le_e);
		}

		return ltscm_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarMatriculaAlerta consultarMatriculaAlerta(
	    TipoEntradaConsultarMatriculaAlerta atecma_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultarMatriculaAlerta ltscma_salida;

		ltscma_salida = new TipoSalidaConsultarMatriculaAlerta();

		try
		{
			ltscma_salida = getAlertaTierrasRemote()
					                .consultarMatriculaAlerta(
					    atecma_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarMatriculaAlerta", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarMatriculaAlerta", le_e);
		}

		return ltscma_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarMatriculaFiltrosTierras consultarMatriculaFiltrosTierras(
	    TipoEntradaConsultarMatriculaFiltrosTierras atecmft_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultarMatriculaFiltrosTierras ltscmft_salida;

		ltscmft_salida = new TipoSalidaConsultarMatriculaFiltrosTierras();

		try
		{
			ltscmft_salida = getAlertaTierrasRemote()
					                 .consultarMatriculaFiltrosTierras(
					    atecmft_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarMatriculaFiltrosTierras", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarMatriculaFiltrosTierras", le_e);
		}

		return ltscmft_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarMatriculaICatastral consultarMatriculaInfoCatastral(
	    TipoEntradaConsultarMatriculaInfoCatastral atecmic_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultarMatriculaICatastral ltscmic_salida;

		ltscmic_salida = new TipoSalidaConsultarMatriculaICatastral();

		try
		{
			ltscmic_salida = getAlertaTierrasRemote()
					                 .consultarMatriculaInfoCatastral(
					    atecmic_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarMatriculaInfoCatastral", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarMatriculaInfoCatastral", le_e);
		}

		return ltscmic_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarOficinasOrigenTipo consultarOficinasOrigenTipo(
	    TipoEntradaConsultarOficinasOrigenTipo atecoot_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultarOficinasOrigenTipo ltscoot_salida;

		ltscoot_salida = new TipoSalidaConsultarOficinasOrigenTipo();

		try
		{
			ltscoot_salida = getAlertaTierrasRemote()
					                 .consultarOficinasOrigenTipo(
					    atecoot_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarOficinasOrigenTipo", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarOficinasOrigenTipo", le_e);
		}

		return ltscoot_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaInactivarAlerta inactivarAlerta(TipoEntradaInactivarAlerta ateia_entrada)
	    throws RemoteException
	{
		TipoSalidaInactivarAlerta ltsia_salida;

		ltsia_salida = new TipoSalidaInactivarAlerta();

		try
		{
			ltsia_salida = getAlertaTierrasRemote()
					               .inactivarAlerta(
					    ateia_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("inactivarAlerta", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("inactivarAlerta", le_e);
		}

		return ltsia_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaInscribirAlertaCabecera inscribirAlertaCabecera(TipoEntradaInscribirAlertaCabecera ateiac_entrada)
	    throws RemoteException
	{
		TipoSalidaInscribirAlertaCabecera ltsiac_salida;

		ltsiac_salida = new TipoSalidaInscribirAlertaCabecera();

		try
		{
			ltsiac_salida = getAlertaTierrasRemote()
					                .inscribirAlertaCabecera(
					    ateiac_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("inscribirAlertaCabecera", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("inscribirAlertaCabecera", le_e);
		}

		return ltsiac_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaRechazarCorreccionAlerta rechazarCorreccionAlerta(
	    TipoEntradaRechazarCorreccionAlerta aterca_entrada
	)
	    throws RemoteException
	{
		TipoSalidaRechazarCorreccionAlerta ltsrca_salida;

		ltsrca_salida = new TipoSalidaRechazarCorreccionAlerta();

		try
		{
			ltsrca_salida = getAlertaTierrasRemote()
					                .rechazarCorreccionAlerta(
					    aterca_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("rechazarCorreccionAlerta", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("rechazarCorreccionAlerta", le_e);
		}

		return ltsrca_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaRemoverMatriculaAlerta removerMatriculaAlerta(TipoEntradaRemoverMatriculaAlerta aterma_entrada)
	    throws RemoteException
	{
		TipoSalidaRemoverMatriculaAlerta ltsrca_salida;

		ltsrca_salida = new TipoSalidaRemoverMatriculaAlerta();

		try
		{
			ltsrca_salida = getAlertaTierrasRemote()
					                .removerMatriculaAlerta(
					    aterma_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("removerMatriculaAlerta", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("removerMatriculaAlerta", le_e);
		}

		return ltsrca_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaCrearProcAntiguoSistemaTierras crearProcAntiguoSistemaTierras(
	    TipoEntradaCrearProcAntiguoSistemaTierras atecpast_entrada
	)
	    throws RemoteException
	{
		TipoSalidaCrearProcAntiguoSistemaTierras ltscpast_salida;

		ltscpast_salida = new TipoSalidaCrearProcAntiguoSistemaTierras();

		try
		{
			ltscpast_salida = getAlertaTierrasRemote()
					                  .crearProcAntiguoSistemaTierras(
					    atecpast_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("crearProcAntiguoSistemaTierras", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("crearProcAntiguoSistemaTierras", le_e);
		}

		return ltscpast_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaAgregarListaMatriculasAlerta agregarListaMatriculasAlerta(
	    TipoEntradaAgregarListaMatriculasAlerta atealma_entrada
	)
	    throws RemoteException
	{
		TipoSalidaAgregarListaMatriculasAlerta ltsalma_salida;

		ltsalma_salida = new TipoSalidaAgregarListaMatriculasAlerta();

		try
		{
			ltsalma_salida = getAlertaTierrasRemote()
					                 .agregarListaMatriculasAlerta(
					    atealma_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarListaMatriculasAlerta", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("agregarListaMatriculasAlerta", le_e);
		}

		return ltsalma_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaListarProcAntiguoSistema listarProcAntiguoSistema(
	    TipoEntradaListarProcAntiguoSistema atelpas_entrada
	)
	    throws RemoteException
	{
		TipoSalidaListarProcAntiguoSistema ltslpas_salida;

		ltslpas_salida = new TipoSalidaListarProcAntiguoSistema();

		try
		{
			ltslpas_salida = getAlertaTierrasRemote()
					                 .listarProcAntiguoSistema(
					    atelpas_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("listarProcAntiguoSistema", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("listarProcAntiguoSistema", le_e);
		}

		return ltslpas_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaEliminarProcAntiguoSistema eliminarProcAntiguoSistema(
	    TipoEntradaEliminarProcAntiguoSistema ateepas_entrada
	)
	    throws RemoteException
	{
		TipoSalidaEliminarProcAntiguoSistema ltsepas_salida;

		ltsepas_salida = new TipoSalidaEliminarProcAntiguoSistema();

		try
		{
			ltsepas_salida = getAlertaTierrasRemote()
					                 .eliminarProcAntiguoSistema(
					    ateepas_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("eliminarProcAntiguoSistema", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("eliminarProcAntiguoSistema", le_e);
		}

		return ltsepas_salida;
	}
}
