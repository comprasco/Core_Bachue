package com.bachue.snr.prosnr01.dao.auditoria;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.registro.Direccion;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase DAO que extrae todos los atributos usados en la auditoria apartir de un resulset enviado
 *
 * @author Julian Vaca
 *
 */
public class AuditoriaDao extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/**
	 * Constructor por defecto de clase
	 */
	public AuditoriaDao()
	{
	}

	/**
	 * Método encargado de extraer todos los atributos usados en la auditoria apartir de un resulset enviado
	 *
	 * @param ars_rs Objeto de tipo ResultSet que contiene atributos de auditoria a extraer
	 * @param aa_auditoria Objeto de tipo Auditoria al cual se le asignan atributos de auditoria extraidos
	 * @throws java.sql.SQLException
	 */
	public void fillAuditoria(java.sql.ResultSet ars_rs, Auditoria aa_auditoria)
	    throws java.sql.SQLException
	{
		if(aa_auditoria != null)
		{
			aa_auditoria.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
			aa_auditoria.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
			aa_auditoria.setIpCreacion(ars_rs.getString("IP_CREACION"));
			aa_auditoria.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
			aa_auditoria.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
			aa_auditoria.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		}
	}

	/**
	 * Método encargado de extraer todos los atributos usados en la auditoria de creacion apartir de un resulset enviado
	 *
	 * @param ars_rs Objeto de tipo ResultSet que contiene atributos de auditoria de creacion a extraer
	 * @param aa_auditoria Objeto de tipo Auditoria al cual se le asignan atributos de auditoria de creacion extraidos
	 * @throws java.sql.SQLException
	 */
	public void fillAuditoriaCreacion(java.sql.ResultSet ars_rs, Auditoria aa_auditoria)
	    throws java.sql.SQLException
	{
		if(aa_auditoria != null)
		{
			aa_auditoria.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
			aa_auditoria.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
			aa_auditoria.setIpCreacion(ars_rs.getString("IP_CREACION"));
		}
	}

	/**
	 * Método encargado de generar la direccion completa dados los atributos apartir de un resultset y direccion enviados
	 *
	 * @param ars_rs Objeto de tipo ResultSet que contiene atributos para la creacion de la direccion
	 * @param ad_direccion Direccion Objeto de tipo Direccion que contiene atributos para la creacion de la direccion
	 * @return String resultante con la direccion completa del registro
	 * @throws java.sql.SQLException
	 */
	public String generarDireccionCompleta(ResultSet ars_rs, Direccion ad_direccion)
	    throws SQLException
	{
		StringBuilder lsb_direccionCompleta;
		String        ls_espacio;

		lsb_direccionCompleta     = new StringBuilder();
		ls_espacio                = IdentificadoresCommon.ESPACIO_VACIO;

		if(ad_direccion != null)
		{
			{
				String ls_dato;

				ls_dato = ars_rs.getString("EJE_PRINCIPAL");

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}

			{
				String ls_dato;

				ls_dato = ad_direccion.getDatoEjePrincipal();

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}

			{
				String ls_dato;

				ls_dato = ad_direccion.getIdLetraEjePrincipal();

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}

			{
				String ls_dato;

				ls_dato = ars_rs.getString("COMPLEMENTO");

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}

			{
				String ls_dato;

				ls_dato = ars_rs.getString("COORDENADA");

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}

			{
				String ls_dato;

				ls_dato = ars_rs.getString("EJE_SECUNDARIO");

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}

			{
				String ls_dato;

				ls_dato = ad_direccion.getDatoEjeSecundario();

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}

			{
				String ls_dato;

				ls_dato = ad_direccion.getIdLetra1EjeSecundario();

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}

			{
				String ls_dato;

				ls_dato = ars_rs.getString("COMPLEMENTO1");

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}

			{
				String ls_dato;

				ls_dato = ars_rs.getString("COORDENADA1");

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}

			{
				String ls_dato;

				ls_dato = ad_direccion.getDato2EjeSecundario();

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}

			{
				String ls_dato;

				ls_dato = ad_direccion.getIdLetra2EjeSecundario();

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}

			{
				String ls_dato;

				ls_dato = ars_rs.getString("COMPLEMENTO2");

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}

			{
				String ls_dato;

				ls_dato = ars_rs.getString("COORDENADA2");

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}

			{
				String ls_dato;

				ls_dato = ars_rs.getString("COMPLEMENTO_DIRECCION");

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}

			{
				String ls_dato;

				ls_dato = ad_direccion.getComplementoDireccion();

				if(StringUtils.isValidString(ls_dato))
					lsb_direccionCompleta.append(ls_dato + ls_espacio);
			}
		}

		return lsb_direccionCompleta.toString();
	}
}
