package com.bachue.snr.prosnr21.web.bean.login;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr21.common.constants.ConstanteCommon;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote;
import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import javax.ejb.EJB;

import javax.faces.context.FacesContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Clase que contiene todos los filtros de inicio de sesion
 *
 * @author Julian Vaca
 */
@WebFilter("/pages/*")
public class filtroSesion implements Filter
{
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(filtroSesion.class);

	/** Propiedad filter config. */
	FilterConfig filterConfig;

	/** Propiedad irr conciliaciones remote. */
	@EJB
	private ConciliacionesRemote icr_conciliacionesRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ParameterRemote irr_referenceRemote;

	/** {@inheritdoc} */
	@Override
	public void destroy()
	{
		this.filterConfig                         = null;
	}

	/** {@inheritdoc} */
	@Override
	public void doFilter(ServletRequest asr_request, ServletResponse asr_response, FilterChain afc_chain)
	    throws ServletException, java.io.IOException
	{
		HttpServletRequest  lhttpsr_request  = (HttpServletRequest)asr_request;
		HttpServletResponse lhttpsr_response = (HttpServletResponse)asr_response;
		HttpSession         https_sesion     = lhttpsr_request.getSession(false);

		if(
		    (https_sesion != null)
			    && (StringUtils.isValidString((String)https_sesion.getAttribute(IdentificadoresCommon.SESION_USUARIO))
			    || StringUtils.isValidString(lhttpsr_request.getRemoteUser()))
		)
		{
			boolean lb_superoSegundoFactor;
			boolean lb_expiroLogin;
			String  ls_usuario;
			String  ls_idSesion;
			String  ls_timeout;
			String  ls_entregaRecurso;
			String  ls_urlSegundoFactor;

			ls_usuario                 = (String)https_sesion.getAttribute(IdentificadoresCommon.SESION_USUARIO);
			ls_idSesion                = (String)https_sesion.getAttribute(IdentificadoresCommon.ID_SESION);
			ls_timeout                 = (String)https_sesion.getAttribute(IdentificadoresCommon.SESION_TIME_OUT);
			lb_superoSegundoFactor     = BooleanUtils.getBooleanValue(
				    (String)https_sesion.getAttribute(IdentificadoresCommon.SUPERO_SEGUNDO_FACTOR)
				);
			lb_expiroLogin             = BooleanUtils.getBooleanValue(ls_timeout);
			ls_entregaRecurso          = (String)https_sesion.getAttribute(IdentificadoresCommon.ENTREGAR_RECURSO);
			ls_urlSegundoFactor        = "/pages/segundoFactor.jsf";

			if(!StringUtils.isValidString(ls_usuario))
			{
				ls_usuario = lhttpsr_request.getRemoteUser();
				https_sesion.setAttribute(IdentificadoresCommon.SESION_USUARIO, ls_usuario);
			}

			if(!lb_superoSegundoFactor)
			{
				try
				{
					if(!lhttpsr_request.getRequestURI().endsWith(ls_urlSegundoFactor))
						ls_entregaRecurso = lhttpsr_request.getRequestURI();

					lb_superoSegundoFactor = icr_conciliacionesRemote.verificarAutenticacionSegundoFactor(ls_idSesion);
				}
				catch(B2BException lb2be_e)
				{
					clh_LOGGER.error("doFilter-verificarAutenticacionSegundoFactor", lb2be_e);
				}

				https_sesion.setAttribute(
				    IdentificadoresCommon.SUPERO_SEGUNDO_FACTOR, StringUtils.getString(lb_superoSegundoFactor)
				);
			}

			if(lb_superoSegundoFactor && !lb_expiroLogin)
				afc_chain.doFilter(asr_request, asr_response);
			else if(!lb_superoSegundoFactor && !lb_expiroLogin)
			{
				if(lhttpsr_request.getRequestURI().endsWith(ls_urlSegundoFactor))
					afc_chain.doFilter(asr_request, asr_response);
				else
				{
					https_sesion.setAttribute(IdentificadoresCommon.ENTREGAR_RECURSO, ls_entregaRecurso);
					lhttpsr_response.sendRedirect(lhttpsr_request.getContextPath() + ls_urlSegundoFactor);
				}
			}
			else if(lb_expiroLogin)
			{
				https_sesion.invalidate();
				lhttpsr_request.logout();

				try
				{
					String ls_constante;

					ls_constante = irr_referenceRemote.obtenerCaracterConstante(ConstanteCommon.URL_CERRAR_SESION);

					if(StringUtils.isValidString(ls_constante))
						FacesContext.getCurrentInstance().getExternalContext().redirect(ls_constante);
				}
				catch(B2BException lb2be_e)
				{
					clh_LOGGER.error("doFilter-sesionExpiro", lb2be_e);
				}
			}
		}
		else
		{
			try
			{
				String ls_constante;

				ls_constante = irr_referenceRemote.obtenerCaracterConstante(ConstanteCommon.URL_CERRAR_SESION);

				if(StringUtils.isValidString(ls_constante))
					FacesContext.getCurrentInstance().getExternalContext().redirect(ls_constante);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("doFilter-sesionInvalida", lb2be_e);
			}
		}
	}

	/** {@inheritdoc} */
	@Override
	public void init(FilterConfig filterConfig)
	    throws ServletException
	{
		this.filterConfig = filterConfig;
	}
}
