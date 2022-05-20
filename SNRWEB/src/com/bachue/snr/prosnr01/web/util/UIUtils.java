package com.bachue.snr.prosnr01.web.util;

import com.b2bsg.common.util.StringUtils;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import javax.faces.component.UISelectItems;

import javax.faces.component.html.HtmlSelectOneMenu;

import javax.faces.model.SelectItem;


/**
 * Clase que contiene todos las utilidades para la aplicacion que se usan recurrentemente
 *
 * @author ccalderon
 */
public class UIUtils implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4232852908359802560L;

	/**
	 * Retorna el valor de select items.
	 *
	 * @param amoo_items correspondiente al valor del tipo de objeto Map
	 * @param ab_insertDefault correspondiente al valor del tipo de objeto boolean
	 * @param as_defaultValue correspondiente al valor del tipo de objeto String
	 * @param as_defaultLabel correspondiente al valor del tipo de objeto String
	 * @return el valor de select items
	 */
	public static List<SelectItem> getSelectItems(
	    Map amoo_items, boolean ab_insertDefault, String as_defaultValue, String as_defaultLabel
	)
	{
		List<SelectItem> llsi_items;

		llsi_items = new java.util.ArrayList<SelectItem>();

		if(ab_insertDefault)
		{
			String ls_defaultLabel;
			String ls_defaultValue;

			ls_defaultLabel     = StringUtils.isValidString(as_defaultLabel) ? as_defaultLabel : new String();

			ls_defaultValue = StringUtils.isValidString(as_defaultLabel) ? as_defaultValue : new String();

			llsi_items.add(new SelectItem(ls_defaultLabel, ls_defaultValue));
		}

		if(com.b2bsg.common.util.CollectionUtils.isValidCollection(amoo_items))
		{
			java.util.Iterator li_key;

			li_key = amoo_items.keySet().iterator();

			while(li_key.hasNext())
			{
				Object lo_key;

				lo_key = li_key.next();

				llsi_items.add(new SelectItem(lo_key, amoo_items.get(lo_key).toString()));
			}
		}

		return llsi_items;
	}

	/**
	 * Retorna el valor de years ago.
	 *
	 * @return el valor de years ago
	 */
	public static Map<String, String> getYearsAgo()
	{
		Map<String, String> lmls_mls;
		Map<String, String> result;
		lmls_mls = new HashMap<String, String>();

		for(long ll_yearactual = 1900; ll_yearactual <= 1978; ll_yearactual++)
			lmls_mls.put("" + ll_yearactual, "" + ll_yearactual);

		result = lmls_mls.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));
		return result;
	}

	/**
	 * Fill html select one menu.
	 *
	 * @param ahsom_hsom correspondiente al valor del tipo de objeto HtmlSelectOneMenu
	 * @param amoo_items correspondiente al valor del tipo de objeto Map
	 * @param ab_insertDefault correspondiente al valor del tipo de objeto boolean
	 * @param as_defaultValue correspondiente al valor del tipo de objeto String
	 * @param as_defaultLabel correspondiente al valor del tipo de objeto String
	 */
	public static void fillHtmlSelectOneMenu(
	    HtmlSelectOneMenu ahsom_hsom, Map amoo_items, boolean ab_insertDefault, String as_defaultValue,
	    String as_defaultLabel
	)
	{
		if(ahsom_hsom != null)
		{
			List<SelectItem> llsi_items;

			llsi_items = getSelectItems(amoo_items, ab_insertDefault, as_defaultValue, as_defaultLabel);

			{
				List<javax.faces.component.UIComponent> lluic_children;
				UISelectItems                           luisi_items;

				lluic_children     = ahsom_hsom.getChildren();
				luisi_items        = new UISelectItems();

				luisi_items.getChildren().clear();
				luisi_items.setValue(llsi_items);

				luisi_items.setId(getRandomId());

				lluic_children.clear();
				lluic_children.add(luisi_items);
			}
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @see String
	 */
	public static String replaceNewLine(String as_s)
	{
		String ls_s;

		ls_s = null;

		if(StringUtils.isValidString(as_s))
			ls_s = as_s.replaceAll("\n", "<br/>");

		return ls_s;
	}

	/**
	 * Retorna el valor de random id.
	 *
	 * @return el valor de random id
	 */
	private static String getRandomId()
	{
		return "rid_" +  new java.security.SecureRandom().nextLong();
	}
}
