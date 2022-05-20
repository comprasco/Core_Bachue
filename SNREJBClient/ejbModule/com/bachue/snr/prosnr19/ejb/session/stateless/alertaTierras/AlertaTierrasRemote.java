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

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades AlertaTierrasRemote.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 05/04/2020
 */
@Remote
public interface AlertaTierrasRemote
{
	/**
	 * Activar alerta tierras.
	 *
	 * @param ateaat_entrada de TipoEntradaActivarAlertaTierras
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida activar alerta tierras
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaActivarAlertaTierras activarAlertaTierras(
	    TipoEntradaActivarAlertaTierras ateaat_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Agregar matricula alerta.
	 *
	 * @param ateama_entrada de TipoEntradaAgregarMatriculaAlerta
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida agregar matricula alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaAgregarMatriculaAlerta agregarMatriculaAlerta(
	    TipoEntradaAgregarMatriculaAlerta ateama_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cancelar ingreso alerta tierras.
	 *
	 * @param ateciat_entrada de TipoEntradaCancelarIngresoAlertaTierras
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida cancelar ingreso alerta tierras
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaCancelarIngresoAlertaTierras cancelarIngresoAlertaTierras(
	    TipoEntradaCancelarIngresoAlertaTierras ateciat_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar alertas.
	 *
	 * @param ateca_entrada de TipoEntradaConsultarAlertas
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar alertas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarAlertas consultarAlertas(
	    TipoEntradaConsultarAlertas ateca_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar detalle alerta.
	 *
	 * @param atecda_entrada de TipoEntradaConsultarDetalleAlerta
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar detalle alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarDetalleAlerta consultarDetalleAlerta(
	    TipoEntradaConsultarDetalleAlerta atecda_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar documento alerta.
	 *
	 * @param atecda_entrada de TipoEntradaConsultarDocumentoAlerta
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar documento alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarDocumentoAlerta consultarDocumentoAlerta(
	    TipoEntradaConsultarDocumentoAlerta atecda_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar entidades J A.
	 *
	 * @param aece_entrada de EntradaConsultarEntidadesJ_A
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar entidades J A
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarEntidadesJ_A consultarEntidadesJ_A(
	    EntradaConsultarEntidadesJ_A aece_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar lista matriculas.
	 *
	 * @param ateclm_entrada de TipoEntradaConsultarListaMatriculas
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar lista matriculas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarListaMatriculas consultarListaMatriculas(
	    TipoEntradaConsultarListaMatriculas ateclm_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar matricula.
	 *
	 * @param atecm_entrada de TipoEntradaConsultarMatricula
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar matricula
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarMatricula consultarMatricula(
	    TipoEntradaConsultarMatricula atecm_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar matricula alerta.
	 *
	 * @param atecma_entrada de TipoEntradaConsultarMatriculaAlerta
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar matricula alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarMatriculaAlerta consultarMatriculaAlerta(
	    TipoEntradaConsultarMatriculaAlerta atecma_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar matricula filtros tierras.
	 *
	 * @param atecmft_entrada de TipoEntradaConsultarMatriculaFiltrosTierras
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar matricula filtros tierras
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarMatriculaFiltrosTierras consultarMatriculaFiltrosTierras(
	    TipoEntradaConsultarMatriculaFiltrosTierras atecmft_entrada, String as_userId, String as_localIp,
	    String                                      as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar matricula info catastral.
	 *
	 * @param atecmic_entrada de TipoEntradaConsultarMatriculaInfoCatastral
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar matricula I catastral
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarMatriculaICatastral consultarMatriculaInfoCatastral(
	    TipoEntradaConsultarMatriculaInfoCatastral atecmic_entrada, String as_userId, String as_localIp,
	    String                                     as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar oficinas origen tipo.
	 *
	 * @param atecoot_entrada de TipoEntradaConsultarOficinasOrigenTipo
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar oficinas origen tipo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarOficinasOrigenTipo consultarOficinasOrigenTipo(
	    TipoEntradaConsultarOficinasOrigenTipo atecoot_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Inactivar alerta.
	 *
	 * @param ateia_entrada de TipoEntradaInactivarAlerta
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida inactivar alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaInactivarAlerta inactivarAlerta(
	    TipoEntradaInactivarAlerta ateia_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Inscribir alerta cabecera.
	 *
	 * @param ateiac_entrada de TipoEntradaInscribirAlertaCabecera
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida inscribir alerta cabecera
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaInscribirAlertaCabecera inscribirAlertaCabecera(
	    TipoEntradaInscribirAlertaCabecera ateiac_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Rechazar correccion alerta.
	 *
	 * @param aterca_entrada de TipoEntradaRechazarCorreccionAlerta
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida rechazar correccion alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaRechazarCorreccionAlerta rechazarCorreccionAlerta(
	    TipoEntradaRechazarCorreccionAlerta aterca_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Remover matricula alerta.
	 *
	 * @param aterma_entrada de TipoEntradaRemoverMatriculaAlerta
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida remover matricula alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaRemoverMatriculaAlerta removerMatriculaAlerta(
	    TipoEntradaRemoverMatriculaAlerta aterma_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Crear proc antiguo sistema tierras.
	 *
	 * @param atecpast_entrada de TipoEntradaCrearProcAntiguoSistemaTierras
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida crear proc antiguo sistema tierras
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaCrearProcAntiguoSistemaTierras crearProcAntiguoSistemaTierras(
	    TipoEntradaCrearProcAntiguoSistemaTierras atecpast_entrada, String as_userId, String as_localIp,
	    String                                    as_remoteIp
	)
	    throws B2BException;

	/**
	 * Agregar lista matriculas alerta.
	 *
	 * @param atealma_entrada de TipoEntradaAgregarListaMatriculasAlerta
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida agregar lista matriculas alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaAgregarListaMatriculasAlerta agregarListaMatriculasAlerta(
	    TipoEntradaAgregarListaMatriculasAlerta atealma_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Listar proc antiguo sistema.
	 *
	 * @param atelpas_entrada de TipoEntradaListarProcAntiguoSistema
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida listar proc antiguo sistema
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaListarProcAntiguoSistema listarProcAntiguoSistema(
	    TipoEntradaListarProcAntiguoSistema atelpas_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Eliminar proc antiguo sistema.
	 *
	 * @param ateepas_entrada de TipoEntradaEliminarProcAntiguoSistema
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida eliminar proc antiguo sistema
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaEliminarProcAntiguoSistema eliminarProcAntiguoSistema(
	    TipoEntradaEliminarProcAntiguoSistema ateepas_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
