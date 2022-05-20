package com.bachue.prosnr01.web.bean.recepcion.documentos;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanPredioDocumentoActo;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanRecepcionDocumentos;
import com.bachue.snr.prosnr01.web.bean.digitador.masivo.BeanDigitadorMasivo;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.io.Serializable;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanTurnosRecepcion.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanTurnosRecepcion")
public class BeanTurnosRecepcion extends BeanDigitadorMasivo implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2237625330347817596L;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String detalleRestitucionTurnos()
	    throws B2BException
	{
		String       ls_result;
		FacesContext lfc_context;

		ls_result       = null;
		lfc_context     = FacesUtils.getFacesContext();

		String ls_idTurnoHistoria = FacesUtils.getStringFacesParameter("idTurnoHistoria");
		String ls_idTurno = FacesUtils.getStringFacesParameter("idTurno");

		{
			BeanPredioDocumentoActo lbpdab_bean;
			lbpdab_bean = (BeanPredioDocumentoActo)lfc_context.getApplication()
					                                              .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
					);

			if(lbpdab_bean != null)
			{
				lbpdab_bean.limpiarDatos();

				lbpdab_bean.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_RESTITUCION_DE_TURNOS));
				lbpdab_bean.setIdTurno(ls_idTurno);
				lbpdab_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
				lbpdab_bean.generarDatosAntSistema();
				lbpdab_bean.generarDatosDocumento();
				lbpdab_bean.generarDatosTramitesVinculados();
				lbpdab_bean.obtenerInformacionASEtapa101();
				lbpdab_bean.validarFechaVencimientoActo();
				lbpdab_bean.setMotivoTramite(null);

				lbpdab_bean.getMatriculasRangos();
				lbpdab_bean.getMatriculasIndividuales();
				lbpdab_bean.getMatriculasTmpRangos();
				lbpdab_bean.getMatriculasTmpIndividuales();

				{
					BeanRecepcionDocumentos lbsdt_beanRecepcionDocumentos;
					lbsdt_beanRecepcionDocumentos = (BeanRecepcionDocumentos)lfc_context.getApplication()
							                                                                .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_RECEPCION_DOCUMENTOS, BeanRecepcionDocumentos.class
							);

					if(lbsdt_beanRecepcionDocumentos != null)
					{
						lbsdt_beanRecepcionDocumentos.setModificar(false);

						if(StringUtils.isValidString(ls_idTurnoHistoria))
						{
							TurnoHistoria lth_turnoHistoria;

							lth_turnoHistoria = new TurnoHistoria();

							lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

							lth_turnoHistoria.setFechaEtapaValida(true);

							lth_turnoHistoria = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_turnoHistoria);

							if(lth_turnoHistoria != null)
							{
								lbsdt_beanRecepcionDocumentos.clear(true);
								lbsdt_beanRecepcionDocumentos.setTurnoHistoria(lth_turnoHistoria);
								lbsdt_beanRecepcionDocumentos.setParametros(null);
								lbsdt_beanRecepcionDocumentos.setMotivoTramite(
								    "" + MotivoTramiteCommon.RESTITUCION_DE_TURNOS_APROBADO
								);
								lbsdt_beanRecepcionDocumentos.setDocumentoGenerado(false);
								lbsdt_beanRecepcionDocumentos.setProceso(ProcesoCommon.ID_PROCESO_43);
								lbsdt_beanRecepcionDocumentos.setEtapa(EtapaCommon.ID_ETAPA_RESTITUCION_DE_TURNOS);

								ls_result = NavegacionCommon.RECEPCION_DOCUMENTOS;
							}
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
					}
				}
			}
		}

		return ls_result;
	}
}
