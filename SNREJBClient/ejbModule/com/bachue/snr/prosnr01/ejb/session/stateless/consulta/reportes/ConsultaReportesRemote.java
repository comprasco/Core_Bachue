package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.reportes;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.Consultas;
import com.bachue.snr.prosnr01.model.sdb.pgn.ResultadoConsulta;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ConsultaReportesRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface ConsultaReportesRemote
{
	
	/**
	 * buscar campos reportes.
	 *
	 * @param aoc_c de Consultas
	 * @return el valor de campos consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CamposConsulta findCamposReportes(Consultas aoc_c)
	    throws B2BException;

	/**
	 * Generar reporte.
	 *
	 * @param aocc_cc de CamposConsulta
	 * @return el valor de resultado consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ResultadoConsulta generacionReporte(CamposConsulta aocc_cc)
	    throws B2BException;
}
