package com.bachue.snr.prosnr01.web.util;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.admHistoricosMisional.AdmHistoricosMisionalRemote;

import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmHomologacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.ui.PagosUI;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades de FuncionalidadesHomologacionActos.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "funcionalidadesHomologacionActos")
public class FuncionalidadesHomologacionActos extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8697382488803592888L;

	/** Propiedad iahmr adm historicos misional remote. */
	@EJB
	protected AdmHistoricosMisionalRemote iahmr_admHistoricosMisionalRemote;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Metodo encargado de agregar el acto seleccionado en pantalla.
	 *
	 * @param aah_admHomologacion Argumento de tipo AdmHomologacion que contiene el registro a agregar.
	 * @param as_idTurno Argumento de tipo String que contiene el id del turno.
	 * @param acah_actosHomologadosYAgregados Argumento de tipo Colección que contiene los registros
	 * agregados a los actos.
	 * @return Collección  de tipo AdmHomologacion que contiene los actos agregados.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AdmHomologacion> agregarActo(
	    AdmHomologacion aah_admHomologacion, String as_idTurno,
	    Collection<AdmHomologacion> acah_actosHomologadosYAgregados
	)
	    throws B2BException
	{
		try
		{
			if(aah_admHomologacion != null)
			{
				aah_admHomologacion.setIdTurno(as_idTurno);

				aah_admHomologacion = iahmr_admHistoricosMisionalRemote.insertarActoHomologado(
					    aah_admHomologacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(aah_admHomologacion != null)
				{
					if(!CollectionUtils.isValidCollection(acah_actosHomologadosYAgregados))
						acah_actosHomologadosYAgregados = new ArrayList<AdmHomologacion>();

					acah_actosHomologadosYAgregados.add(aah_admHomologacion);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}

		return acah_actosHomologadosYAgregados;
	}

	/**
	 * Metodo encargado de consultar todos los actos de una solicitud y circulo.
	 *
	 * @param as_idTurno Argumento de tipo String que contiene el id del turno.
	 * @return Collección  de tipo AdmHomologacion que contiene los actos homologados.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AdmHomologacion> consultarAdmHomologacion(String as_idTurno)
	    throws B2BException
	{
		Collection<AdmHomologacion> lcah_homologacion;

		lcah_homologacion = null;

		try
		{
			lcah_homologacion = iahmr_admHistoricosMisionalRemote.consultarAdmHomologacion(
				    as_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lcah_homologacion))
			{
				int li_contador;

				li_contador = 1;

				for(AdmHomologacion lah_iterador : lcah_homologacion)
				{
					if(lah_iterador != null)
						lah_iterador.setCodigoActoHomologado(li_contador++);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}

		return lcah_homologacion;
	}

	/**
	 * Consultar pagos.
	 *
	 * @param as_idTurno de as id turno
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PagosUI> consultarPagos(String as_idTurno)
	    throws B2BException
	{
		Collection<PagosUI> lcpui_pagos;

		lcpui_pagos = null;

		try
		{
			lcpui_pagos = iahmr_admHistoricosMisionalRemote.consultarPagos(
				    as_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}

		return lcpui_pagos;
	}

	/**
	 * Metodo encargado de eliminar el acto seleccionado.
	 *
	 * @param aa_actoEliminar Argumento de tipo Acto que contiene el acto a eliminar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void eliminarActo(Acto aa_actoEliminar)
	    throws B2BException
	{
		try
		{
			if(aa_actoEliminar != null)
				icr_calificacionRemote.eliminarActo(
				    aa_actoEliminar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}
	}

	/**
	 * Metodo encargado de homologar el acto seleccionado en pantalla.
	 *
	 * @param acah_homologacion Argumento de tipo Collection<AdmHomologacion> que contiene los actos homologados.
	 * @param aah_actoAHomologar Argumento de tipo AdmHomologacion que contiene los actos a homologar.
	 * @param acah_admHomologacion Argumento de tipo Collection<AdmHomologacion> que contiene los actos homologados.
	 * @return devuelve el valor de Collection de AdmHomologacion
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AdmHomologacion> homologarActo(
	    Collection<AdmHomologacion> acah_homologacion, AdmHomologacion aah_actoAHomologar,
	    Collection<AdmHomologacion> acah_admHomologacion
	)
	    throws B2BException
	{
		Collection<AdmHomologacion> lcah_actosHomologados;

		lcah_actosHomologados = null;

		try
		{
			if(CollectionUtils.isValidCollection(acah_homologacion) && (aah_actoAHomologar != null))
			{
				Iterator<AdmHomologacion> liah_iterator;
				int                       li_seleccionado;

				liah_iterator       = acah_homologacion.iterator();
				li_seleccionado     = 0;

				while(liah_iterator.hasNext() && (li_seleccionado <= 1))
				{
					AdmHomologacion lah_iterado;
					boolean         lb_homologado;

					lah_iterado       = liah_iterator.next();
					lb_homologado     = lah_iterado.isHomologado();

					if((lah_iterado != null) && lb_homologado)
					{
						if(li_seleccionado == 0)
						{
							TipoActo lta_seleccionado;

							lta_seleccionado = lah_iterado.getTipoActo();

							if(lta_seleccionado != null)
							{
								aah_actoAHomologar.setTipoActo(lta_seleccionado);
								aah_actoAHomologar.setHomologado(lb_homologado);

								li_seleccionado++;
							}
						}
						else
							throw new B2BException(ErrorKeys.DEBE_HOMOLOGAR_MAXIMO_UN_TIPO_ACTO);
					}
				}

				if(li_seleccionado == 0)
					throw new B2BException(ErrorKeys.DEBE_HOMOLOGAR_AL_MENOS_UN_TIPO_ACTO);

				if(CollectionUtils.isValidCollection(acah_admHomologacion))
				{
					lcah_actosHomologados = new ArrayList<AdmHomologacion>();

					for(AdmHomologacion lah_iterador : acah_admHomologacion)
					{
						if(lah_iterador != null)
						{
							int li_codigo;
							int li_codigoActoHomologado;

							li_codigo                   = lah_iterador.getCodigoActoHomologado();
							li_codigoActoHomologado     = aah_actoAHomologar.getCodigoActoHomologado();

							if(li_codigo == li_codigoActoHomologado)
								lcah_actosHomologados.add(aah_actoAHomologar);
							else
								lcah_actosHomologados.add(lah_iterador);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}

		return lcah_actosHomologados;
	}

	/**
	 * Metodo encargado de terminar el proceso de homologación de actos.
	 *
	 * @param lcah_actosHomologadosYAgregados Argumento de tipo Collection<AdmHomologacion> que contiene
	 * los actos homologados y agregados para terminar el proceso.
	 * @param as_idTurnoHistoria Argumento de tipo String que contiene el id turno historia para
	 * ser terminado.
	 * @param as_observaciones Argumento de tipo String que contiene las observaciones para
	 * ser actualizadas en turno historia.
	 * @param as_idTurno Argumento de tipo String que contiene el id turno para
	 * ser terminado.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void terminarProcesoHomologacion(
	    Collection<AdmHomologacion> lcah_actosHomologadosYAgregados, Collection<AdmHomologacion> acah_actosConsultados,
	    String as_idTurnoHistoria, boolean ab_administracionHomologacionActosLiquidacion, String as_observaciones,
	    String as_idTurno
	)
	    throws B2BException
	{
		terminarProcesoHomologacion(
		    lcah_actosHomologadosYAgregados, acah_actosConsultados, as_idTurnoHistoria,
		    ab_administracionHomologacionActosLiquidacion, as_observaciones, as_idTurno, null
		);
	}

	/**
	 * Metodo encargado de terminar el proceso de homologación de actos.
	 *
	 * @param lcah_actosHomologadosYAgregados Argumento de tipo Collection<AdmHomologacion> que contiene
	 * los actos homologados y agregados para terminar el proceso.
	 * @param acah_actosConsultados de acah actos consultados
	 * @param as_idTurnoHistoria Argumento de tipo String que contiene el id turno historia para
	 * ser terminado.
	 * @param ab_administracionHomologacionActosLiquidacion de ab administracion homologacion actos liquidacion
	 * @param as_observaciones Argumento de tipo String que contiene las observaciones para
	 * ser actualizadas en turno historia.
	 * @param as_idTurno Argumento de tipo String que contiene el id turno para
	 * ser terminado.
	 * @param as_solicitud correspondiente al valor del tipo de objeto Solicitud
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void terminarProcesoHomologacion(
	    Collection<AdmHomologacion> lcah_actosHomologadosYAgregados, Collection<AdmHomologacion> acah_actosConsultados,
	    String as_idTurnoHistoria, boolean ab_administracionHomologacionActosLiquidacion, String as_observaciones,
	    String as_idTurno, Solicitud as_solicitud
	)
	    throws B2BException
	{
		try
		{
			if(CollectionUtils.isValidCollection(lcah_actosHomologadosYAgregados))
			{
				//Sección que integra los certificados a la colección
				if(CollectionUtils.isValidCollection(acah_actosConsultados))
				{
					for(AdmHomologacion lah_tmp : acah_actosConsultados)
					{
						if((lah_tmp != null) && lah_tmp.isCertificado())
							lcah_actosHomologadosYAgregados.add(lah_tmp);
					}
				}

				AdmHomologacion lah_homologacion;

				lah_homologacion = lcah_actosHomologadosYAgregados.iterator().next();

				if(lah_homologacion != null)
				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = new TurnoHistoria();

					lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));
					lth_turnoHistoria.setObservaciones(as_observaciones);

					lah_homologacion.setIdTurno(as_idTurno);
					lah_homologacion.setActosHomologadosYAgregados(lcah_actosHomologadosYAgregados);
					lah_homologacion.setTurnoHistoria(lth_turnoHistoria);
					lah_homologacion.setSolicitud(as_solicitud);

					iahmr_admHistoricosMisionalRemote.terminarProcesoHomologacion(
					    lah_homologacion, ab_administracionHomologacionActosLiquidacion, getUserId(),
					    getLocalIpAddress(), getRemoteIpAddress()
					);
				}
			}
			else
				throw new B2BException(ErrorKeys.DEBE_HOMOLOGAR_TODOS_LOS_ACTOS);
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}
	}

	/**
	 * Guardar matriculas asociadas.
	 *
	 * @param lcsm_solicitudMatricula de lcsm solicitud matricula
	 * @param as_idTurno de as id turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarMatriculasAsociadas(Collection<SolicitudMatricula> lcsm_solicitudMatricula, String as_idTurno)
	    throws B2BException
	{
		if(CollectionUtils.isValidCollection(lcsm_solicitudMatricula) && StringUtils.isValidString(as_idTurno))
			iahmr_admHistoricosMisionalRemote.guardarMatriculasAsociadas(
			    lcsm_solicitudMatricula, as_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
	}
}
