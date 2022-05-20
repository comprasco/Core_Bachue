package com.bachue.snr.prosnr21.business.test;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.crps.MultiCashBusiness;

import java.util.Calendar;
import java.util.Date;


public class GenerarMultiCash
{
	public static void main(String[] args)
	{
		MultiCashBusiness lmcb_business;
		Date              ld_ciclo;
		Date              ld_fin;
		Date              ld_inicio;
		Date              ld_max;
		Date              ld_tmp;

		lmcb_business     = new MultiCashBusiness();
		ld_inicio         = new Date();
		ld_ciclo          = (Date)ld_inicio.clone();
		ld_tmp            = DateUtils.getDate("20190723", "yyyyMMdd");
//		ld_max           = DateUtils.getDate(ld_inicio, Calendar.DATE, 1, false);
		ld_max            = DateUtils.getDate("20190731", "yyyyMMdd");

//		ld_max           = DateUtils.getDate("20190702", "yyyyMMdd");
		while(ld_tmp.before(ld_max))
		{
			try
			{
				lmcb_business.generarMultiCash(ld_tmp, "74", "garias_conciliaciones", "192.168.100.102");
			}
			catch(B2BException lb2b_e)
			{
			}

			ld_fin = new Date();

			System.err.println(
			    StringUtils.getString(ld_tmp, "yyyy-MM-dd") + " :: Inicio -> " + ld_ciclo + " :: Finializo -> "
			    + ld_fin + " :: Duración Ciclo ->" + ((ld_fin.getTime() - ld_ciclo.getTime()) / 1000d)
			    + " :: Duración Total ->" + ((ld_fin.getTime() - ld_inicio.getTime()) / 1000d)
			);

			ld_tmp       = DateUtils.getDate(ld_tmp, Calendar.DATE, 1, false);
			ld_ciclo     = (Date)ld_fin.clone();
		}
	}
}
