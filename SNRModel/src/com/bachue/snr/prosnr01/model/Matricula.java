package com.bachue.snr.prosnr01.model;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;



/**
 * Class que contiene todos las propiedades Matricula.
 *
 * @author hcastaneda
 */
public class Matricula
{
	/** Propiedad il matricula. */
	private Long il_matricula;

	/** Propiedad is circulo. */
	private String is_circulo;

	/**
	 * Retorna el valor de circulo.
	 *
	 * @return el valor de circulo
	 */
	public String getCirculo()
	{
		return is_circulo;
	}

	/**
	 * Modifica el valor de matricula.
	 *
	 * @param al_l asigna el valor a la propiedad matricula
	 */
	public void setMatricula(Long al_l)
	{
		il_matricula = al_l;
	}

	/**
	 * Retorna el valor de matricula.
	 *
	 * @return el valor de matricula
	 */
	public Long getMatricula()
	{
		return il_matricula;
	}

	/**
	 * Retorna el valor de matricula.
	 *
	 * @param as_circulo de as circulo
	 * @return el valor de matricula
	 * @throws B2BException the b 2 B exception
	 */
	public static Matricula getMatricula(String as_circulo)
	    throws B2BException
	{
		return getMatricula(as_circulo, IdentificadoresCommon.SIMBOLO_GUION, 0, 1);
	}

	/**
	 * Retorna Objeto o variable de valor matricula CT(Catastros) y CX(Coexistencias).
	 *
	 * @param as_circulo de as circulo
	 * @return el valor de matricula CT and CX
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static Matricula getMatriculaCTandCX(String as_circulo)
	    throws B2BException
	{
		Matricula lm_matricula;

		lm_matricula = null;

		if(StringUtils.isValidString(as_circulo))
		{
			String ls_idCirculo;
			String ls_idMatricula;
			Long   ll_matricula;

			ls_idCirculo       = as_circulo.substring(0, 3);
			ls_idMatricula     = as_circulo.substring(3);
			ll_matricula       = NumericUtils.getLongWrapper(ls_idMatricula);

			if(!StringUtils.isValidString(ls_idCirculo))
				throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_INVALIDO);

			if(!NumericUtils.isValidLong(ll_matricula))
				throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);

			lm_matricula = new Matricula();

			lm_matricula.setCirculo(ls_idCirculo);
			lm_matricula.setMatricula(ll_matricula);
		}
		else
			throw new B2BException(ErrorKeys.ERROR_PARAMETRO_NO_VALIDO);

		return lm_matricula;
	}

	/**
	 * Retorna el valor de matricula.
	 *
	 * @param as_circulo de as circulo
	 * @param as_separador de as separador
	 * @param ai_posicionCirculo de ai posicion circulo
	 * @param ai_posicionMatricula de ai posicion matricula
	 * @return el valor de matricula
	 * @throws B2BException the b 2 B exception
	 */
	public static Matricula getMatricula(
	    String as_circulo, String as_separador, int ai_posicionCirculo, int ai_posicionMatricula
	)
	    throws B2BException
	{
		Matricula lm_matricula;

		lm_matricula = null;

		if(StringUtils.isValidString(as_circulo))
		{
			Long     ll_matricula;
			String[] lsa_partes;

			lsa_partes = as_circulo.split(as_separador);

			if((lsa_partes != null) && (lsa_partes.length == 2))
			{
				lm_matricula     = new Matricula();
				ll_matricula     = NumericUtils.getLongWrapper(lsa_partes[ai_posicionMatricula]);

				lm_matricula.setCirculo(lsa_partes[ai_posicionCirculo]);
				lm_matricula.setMatricula(ll_matricula);

				if(!NumericUtils.isValidLong(ll_matricula))
					throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);
			}
		}
		else
			throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_INVALIDO);

		return lm_matricula;
	}

	/**
	 * Modifica el valor de circulo.
	 *
	 * @param as_s asigna el valor a la propiedad circulo
	 */
	public void setCirculo(String as_s)
	{
		is_circulo = as_s;
	}
}
