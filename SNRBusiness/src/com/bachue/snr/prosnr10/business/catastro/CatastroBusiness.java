package com.bachue.snr.prosnr10.business.catastro;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.dao.DaoCreator;

import com.bachue.snr.prosnr01.model.predio.Predio;

import com.bachue.snr.prosnr10.common.constants.ErrorKeys;


/**
 * Clase para el manejo de validaciones de negocio comunes en servicios de catastro.
 *
 * @author Manuel Blanco
 */
public class CatastroBusiness extends BaseBusiness
{
	/**
	 * Validar circulo registral.
	 *
	 * @param ac_datos de ac datos
	 * @param adm_manager de adm manager
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected synchronized boolean validarCirculoRegistral(Predio ac_datos, DAOManager adm_manager)
	    throws B2BException
	{
		boolean lpr_return;

		lpr_return = false;

		if(ac_datos != null)
		{
			String ls_idCirculo;

			ls_idCirculo = ac_datos.getIdCirculo();

			if(
			    StringUtils.isValidString(ls_idCirculo)
				    && (DaoCreator.getCirculoRegistralDAO(adm_manager).findById(ls_idCirculo) != null)
			)
				lpr_return = true;
			else
				throw new B2BException(addErrorCTO(ErrorKeys.ERROR_CIRCULO_REGISTRAL_NO_VALIDO_CODIGO));
		}

		return lpr_return;
	}
}
