package com.bachue.snr.prosnr01.ejb.session.stateless.crps;

@javax.ejb.Remote
public interface CRPSRemote
{
	/**
	 * Generar el archivo CRPS (cabecera y detalle) para una fecha específica, dejando el resultado en el FTP cuyos
	 * datos estan parametrizados en el módulo de conciliaciones
	 *
	 * @param ad_fecha Fecha de transacción bancaria sobre la cual se extraeran los registros en el archivo CRPS
	 * @param as_remoteIp de as remote ip
	 * @param as_userId usuario que realiza el proceso de cargue de archivos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void generarCRPS(java.util.Date ad_fecha, String as_userId, String as_localIp, String as_remoteIp)
	    throws com.b2bsg.common.exception.B2BException;
}
