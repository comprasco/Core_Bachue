package com.bachue.snr.prosnr01.common.utils;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;


/**
 * Clase para el interacción con servidores FTP bajo el protocolo SFTP
 *
 * @author Edgar Prieto
 */
public class FTPUtils
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(FTPUtils.class);

	/**
	 * Obtener un objeto de conexión a un servidor SFTP
	 *
	 * @param as_servidor IP o nombre del servidor
	 * @param as_usuario Usuario de autenticación
	 * @param as_clave Clave del usuario
	 * @param as_puerto Puerto del servidor
	 * @return objeto de conexión al FTP
	 * @throws B2BException
	 */
	public static ChannelSftp abrirConexionFTP(
	    String as_servidor, String as_usuario, String as_clave, String as_puerto
	)
	    throws B2BException
	{
		ChannelSftp lcs_canal;

		lcs_canal = null;

		try
		{
			if(
			    StringUtils.isValidString(as_servidor) && StringUtils.isValidString(as_usuario)
				    && StringUtils.isValidString(as_clave)
			)
			{
				Session ls_sesion;

				JSch.setConfig("StrictHostKeyChecking", "no");

				if(StringUtils.isValidString(as_puerto))
					ls_sesion = new JSch().getSession(as_usuario, as_servidor, NumericUtils.getInt(as_puerto));
				else
					ls_sesion = new JSch().getSession(as_usuario, as_servidor);

				ls_sesion.setPassword(as_clave);
				ls_sesion.connect();

				lcs_canal = (ChannelSftp)ls_sesion.openChannel("sftp");

				lcs_canal.connect();
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SFTP);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("abrirConexionFTP", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		return lcs_canal;
	}

	/**
	 * Obtener un objeto de conexión a un servidor SFTP
	 *
	 * @param as_servidor IP o nombre del servidor
	 * @param as_usuario Usuario de autenticación
	 * @param as_clave Clave del usuario
	 * @return objeto de conexión al FTP
	 * @throws B2BException
	 */
	public static ChannelSftp abrirConexionFTP(String as_servidor, String as_usuario, String as_clave)
	    throws B2BException
	{
		ChannelSftp lcs_canal;

		lcs_canal = null;

		try
		{
			lcs_canal = abrirConexionFTP(as_servidor, as_usuario, as_clave, null);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("abrirConexionFTP", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		return lcs_canal;
	}

	/**
	 * Subir el contenido de una archivo de texto al servidor FTP
	 *
	 * @param acs_canal Objeto de conexión al FTP
	 * @param as_contenidoArchivo Contenido a subir al FTP
	 * @param as_rutaArchivo Ruta del archivo destino
	 * @throws B2BException
	 */
	public static void cargarArchivoFTP(ChannelSftp acs_canal, String as_contenidoArchivo, String as_rutaArchivo)
	    throws B2BException
	{
		if(as_contenidoArchivo != null)
			cargarArchivoFTP(acs_canal, new ByteArrayInputStream(as_contenidoArchivo.getBytes()), as_rutaArchivo);
	}

	/**
	 * Subir el contenido de una archivo de texto al servidor FTP
	 *
	 * @param acs_canal Objeto de conexión al FTP
	 * @param aba_contenidoArchivo Contenido a subir al FTP
	 * @param as_rutaArchivo Ruta del archivo destino
	 * @throws B2BException
	 */
	public static void cargarArchivoFTP(ChannelSftp acs_canal, byte[] aba_contenidoArchivo, String as_rutaArchivo)
	    throws B2BException
	{
		if(aba_contenidoArchivo != null)
			cargarArchivoFTP(acs_canal, new ByteArrayInputStream(aba_contenidoArchivo), as_rutaArchivo);
	}

	/**
	 * Cerrar una conexión abierta
	 * @param acs_canal Objeto de conexión al FTP
	 * @throws B2BException
	 */
	public static void cerrarConexionFTP(ChannelSftp acs_canal)
	    throws B2BException
	{
		try
		{
			if(acs_canal != null)
			{
				Session ls_sesion;

				ls_sesion = acs_canal.getSession();

				acs_canal.exit();
				acs_canal.disconnect();

				acs_canal = null;

				if(ls_sesion != null)
				{
					ls_sesion.disconnect();

					ls_sesion = null;
				}
			}
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("cerrarConexionFTP", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}
	}

	/**
	 * Crear una carpeta en el servidor FTP
	 * @param acs_canal Objeto de conexión al FTP
	 * @param as_nuevoDirectorio Ruta del directorio a crear
	 * @param as_separadorDirectorio Separador de directorios del sistema operativo del servidor
	 * @throws B2BException
	 */
	public static void crearDirectorioFTP(
	    ChannelSftp acs_canal, String as_nuevoDirectorio, String as_separadorDirectorio
	)
	    throws B2BException
	{
		try
		{
			if(
			    (acs_canal != null) && StringUtils.isValidString(as_nuevoDirectorio)
				    && StringUtils.isValidString(as_separadorDirectorio)
			)
			{
				String[] lsa_nuevoDirectorio;

				lsa_nuevoDirectorio = as_nuevoDirectorio.split(as_separadorDirectorio);

				if((lsa_nuevoDirectorio != null) && (lsa_nuevoDirectorio.length > 0))
				{
					StringBuilder lsb_nuevoDirectorio;

					lsb_nuevoDirectorio = new StringBuilder(as_separadorDirectorio);

					for(String ls_parte : lsa_nuevoDirectorio)
					{
						if(ls_parte != null)
						{
							ls_parte = StringUtils.getString(ls_parte);

							if(StringUtils.isValidString(ls_parte))
							{
								boolean lb_existe;
								String  ls_subdirectorio;

								lsb_nuevoDirectorio.append(ls_parte);

								ls_subdirectorio = lsb_nuevoDirectorio.toString();

								try
								{
									acs_canal.cd(ls_subdirectorio);
									lb_existe = true;
								}
								catch(SftpException e)
								{
									lb_existe = false;
								}

								if(!lb_existe)
									acs_canal.mkdir(ls_subdirectorio);

								lsb_nuevoDirectorio.append(as_separadorDirectorio);
							}
						}
					}
				}
			}
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("crearDirectorioFTP", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}
	}

	/**
	 * Descargar un archvo desde el FTP
	 *
	 * @param acs_canal Objeto de conexión al FTP
	 * @param as_rutaArchivo Ruta del archivo a descargar
	 * @return Arreglo de bytes que representa el contenido del archivo a descargar
	 *
	 * @throws B2BException
	 */
	public static byte[] descargarArchivoFTP(ChannelSftp acs_canal, String as_rutaArchivo)
	    throws B2BException
	{
		byte[] lba_archivo;

		lba_archivo = null;

		try
		{
			if((acs_canal != null) && StringUtils.isValidString(as_rutaArchivo))
			{
				InputStream lis_archivo;

				lis_archivo = acs_canal.get(as_rutaArchivo);

				if(lis_archivo != null)
				{
					byte[]                lba_buffer;
					ByteArrayOutputStream lbyos_archivo;
					int                   li_longitudBuffer;

					lbyos_archivo     = new ByteArrayOutputStream();
					lba_buffer        = new byte[1024];

					while((li_longitudBuffer = lis_archivo.read(lba_buffer)) != -1)
						lbyos_archivo.write(lba_buffer, 0, li_longitudBuffer);

					lba_archivo = lbyos_archivo.toByteArray();

					lbyos_archivo.flush();
					lbyos_archivo.close();
					lis_archivo.close();
				}
			}
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("descargarArchivoFTP", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		return lba_archivo;
	}

	/**
	 * Subir un flujo al servidor FTP
	 *
	 * @param acs_canal
	 * @param ais_archivo Flujo a subir
	 * @param as_rutaArchivo Ruta del archivo destino
	 * @throws B2BException
	 */
	private static void cargarArchivoFTP(ChannelSftp acs_canal, InputStream ais_archivo, String as_rutaArchivo)
	    throws B2BException
	{
		try
		{
			if((acs_canal != null) && (ais_archivo != null) && StringUtils.isValidString(as_rutaArchivo))
				acs_canal.put(ais_archivo, as_rutaArchivo);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("cargarArchivoFTP", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}
	}
}
