package com.bachue.snr.prosnr01.web.bean.consulta.reparto.calificacion;

import com.bachue.snr.prosnr01.model.consulta.reparto.calificacion.UsuarioConsulta;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;

import org.primefaces.component.picklist.PickList;

import org.primefaces.model.DualListModel;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;

import javax.faces.context.FacesContext;

import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


/**
 * Clase que contiene todos las propiedades y acciones de UsuariosConsultaConverter.
 *
 * @author Julian Vaca
 */
@FacesConverter("usuariosConsultaConverter")
public class UsuariosConsultaConverter implements Converter
{
/** {@inheritdoc} */

//	private static final Logger LOG = LoggerFactory.getLogger(UsuariosConsultaConverter.class);
	public Object getAsObject(FacesContext afc_context, UIComponent auic_component, String as_value)
	{
		DualListModel<UsuarioConsulta> ldlmuc_lista;
		UsuarioConsulta                luc_usuario;

		luc_usuario      = null;
		ldlmuc_lista     = null;

		if((auic_component != null) && auic_component instanceof PickList)
			ldlmuc_lista = (DualListModel<UsuarioConsulta>)((PickList)auic_component).getValue();

		if(ldlmuc_lista != null)
		{
			Iterator<UsuarioConsulta> liuc_fuente;

			liuc_fuente = ldlmuc_lista.getSource().iterator();

			while(liuc_fuente.hasNext() && (luc_usuario == null))
			{
				UsuarioConsulta luc_fuente;

				luc_fuente = liuc_fuente.next();

				if(luc_fuente != null)
				{
					Usuario lu_fuente;

					lu_fuente = luc_fuente.getUsuario();

					if((lu_fuente != null) && lu_fuente.getIdUsuario().equals(as_value))
						luc_usuario = luc_fuente;
				}
			}

			if(luc_usuario == null)
			{
				Iterator<UsuarioConsulta> liuc_destino;

				liuc_destino = ldlmuc_lista.getTarget().iterator();

				while(liuc_destino.hasNext() && (luc_usuario == null))
				{
					UsuarioConsulta luc_destino;

					luc_destino = liuc_destino.next();

					if(luc_destino != null)
					{
						Usuario lu_destino;

						lu_destino = luc_destino.getUsuario();

						if((lu_destino != null) && lu_destino.getIdUsuario().equals(as_value))
							luc_usuario = luc_destino;
					}
				}
			}
		}

		return luc_usuario;
	}

	/** {@inheritdoc} */
	@Override
	public String getAsString(FacesContext afc_context, UIComponent auic_component, Object ao_object)
	{
		String ls_value;

		ls_value = null;

		if((ao_object != null) && ao_object instanceof UsuarioConsulta)
		{
			Usuario lu_u;

			lu_u = ((UsuarioConsulta)ao_object).getUsuario();

			if(lu_u != null)
				ls_value = lu_u.getIdUsuario();
		}

		return ls_value;
	}

	/**
	 * Retorna el valor de object from list.
	 *
	 * @param list correspondiente al valor del tipo de objeto List<?>
	 * @param identifier correspondiente al valor del tipo de objeto Integer
	 * @return el valor de object from list
	 */
	private UsuarioConsulta getObjectFromList(final List<?> list, final Integer identifier)
	{
		for(final Object object : list)
		{
			final UsuarioConsulta usuarioConsulta = (UsuarioConsulta)object;

			if(usuarioConsulta.getIdentidicador().equals(identifier))
				return usuarioConsulta;
		}

		return null;
	}

	/**
	 * Retorna el valor de object from UI pick list component.
	 *
	 * @param component correspondiente al valor del tipo de objeto UIComponent
	 * @param value correspondiente al valor del tipo de objeto String
	 * @return el valor de object from UI pick list component
	 */
	@SuppressWarnings("unused")
	private UsuarioConsulta getObjectFromUIPickListComponent(UIComponent component, String value)
	{
		final DualListModel<UsuarioConsulta> dualList;

		try
		{
			dualList = (DualListModel<UsuarioConsulta>)((PickList)component).getValue();

			UsuarioConsulta usuarioConsulta = getObjectFromList(dualList.getSource(), Integer.valueOf(value));

			if(usuarioConsulta == null)
				usuarioConsulta = getObjectFromList(dualList.getTarget(), Integer.valueOf(value));

			return usuarioConsulta;
		}
		catch(ClassCastException | NumberFormatException nfe)
		{
			throw new ConverterException();
		}
	}
}
