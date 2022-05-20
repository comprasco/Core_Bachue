package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.png.DominioNaturalezaJuridica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase de manejo de datos para la tabla SDB_PNG_DOMINIO_NATURALEZA_JURIDICA.
 *
 * @author Nicolas Guaneme
 */
public class DominioNaturalezaJuridicaDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_DOMINIO_NAT_JUR, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO FROM SDB_PNG_DOMINIO_NATURALEZA_JURIDICA WHERE ID_DOMINIO_NAT_JUR=?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_DOMINIO_NAT_JUR, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO FROM SDB_PNG_DOMINIO_NATURALEZA_JURIDICA";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_DOMINIO_NAT_JUR, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO FROM SDB_PNG_DOMINIO_NATURALEZA_JURIDICA WHERE ACTIVO='S' ORDER BY NOMBRE ASC ";

	/** Constante cs_FIND_ALL_ACTIVO_ID. */
	private static final String cs_FIND_ALL_ACTIVO_ID = "SELECT ID_DOMINIO_NAT_JUR, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO FROM SDB_PNG_DOMINIO_NATURALEZA_JURIDICA WHERE ACTIVO='S' ORDER BY ID_DOMINIO_NAT_JUR ASC ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PNG_DOMINIO_NATURALEZA_JURIDICA SET NOMBRE=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?, ACTIVO=? WHERE ID_DOMINIO_NAT_JUR=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PNG_DOMINIO_NATURALEZA_JURIDICA(ID_DOMINIO_NAT_JUR, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION,ACTIVO)VALUES(?, ?, ?, SYSTIMESTAMP, ?, ?)";

/**
 * Metodo para traer de la base de datos todos los registros de la tabla SDB_PNG_DOMINIO_NATURALEZA_JURIDICA.
 *
 * @return el valor de collection
 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
 */
	public Collection<DominioNaturalezaJuridica> findAll()
	    throws B2BException
	{
		Collection<DominioNaturalezaJuridica> ls_object;
		PreparedStatement                     lps_ps;
		ResultSet                             lrs_rs;

		ls_object     = new LinkedList<DominioNaturalezaJuridica>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_object;
	}

	/**
	 * Find by id.
	 *
	 * @param at_param de at param
	 * @return el valor de dominio naturaleza juridica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DominioNaturalezaJuridica findById(DominioNaturalezaJuridica at_param)
	    throws B2BException
	{
		return (at_param != null) ? findById(at_param.getIdDominioNatJur()) : null;
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PNG_DOMINIO_NATURALEZA_JURIDICA
	 * que coincida con un ID_DOMINIO_NAT_JUR especifico.
	 *
	 * @param as_idDominioNatJur de as id dominio nat jur
	 * @return el valor de dominio naturaleza juridica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @ param at_param representa el objeto del cual se extraera el ID_DOMINIO_NAT_JUR para realizar
	 * la consulta en la base de datos
	 */
	public DominioNaturalezaJuridica findById(String as_idDominioNatJur)
	    throws B2BException
	{
		DominioNaturalezaJuridica ls_object;

		ls_object = null;

		if(StringUtils.isValidString(as_idDominioNatJur))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idDominioNatJur);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_object;
	}

	/**
	 * Metodo para insetar o actualizar un registro en la base de datos.
	 *
	 * @param at_param de at param
	 * @param ab_query de ab query
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @ param at_param representa el objeto el cual se va a insertar o actualizar
	 * @ param ab_query si su valor es true inserta en la base de datos, en camio si su valor es false
	 * se realiza una actualización en la base de datos
	 */
	public void insertOrUpdate(DominioNaturalezaJuridica at_param, boolean ab_query)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
					lps_ps.setString(li_column++, at_param.getIdDominioNatJur());

				lps_ps.setString(li_column++, at_param.getNombre());

				if(ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, at_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, at_param.getIpModificacion());
				}

				lps_ps.setString(li_column++, at_param.getActivo());

				if(!ab_query)
					lps_ps.setString(li_column++, at_param.getIdDominioNatJur());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo para la Busqueda de todos los dominio naturaleza juridica que se encuentren en estado activo.
	 *
	 * @param ab_b de ab b
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DominioNaturalezaJuridica> findAllActivo(boolean ab_b)
	    throws B2BException
	{
		Collection<DominioNaturalezaJuridica> lcdnj_cdnj;
		PreparedStatement                     lps_ps;
		ResultSet                             lrs_rs;

		lcdnj_cdnj     = new ArrayList<DominioNaturalezaJuridica>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(ab_b)
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);
			else
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO_ID);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcdnj_cdnj.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActivo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lcdnj_cdnj))
			lcdnj_cdnj = null;

		return lcdnj_cdnj;
	}

/**
 *  Metodo para obtener objeto de tipo Dominio Naruraleza Juridica.
 *
 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
 * @return el valor de objet from result set
 * @throws SQLException cuando se produce algun error en el proceso
 */
	private DominioNaturalezaJuridica getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		DominioNaturalezaJuridica ldnj_DomNatJur;

		ldnj_DomNatJur = new DominioNaturalezaJuridica();

		ldnj_DomNatJur.setIdDominioNatJur(ars_rs.getString("ID_DOMINIO_NAT_JUR"));
		ldnj_DomNatJur.setNombre(ars_rs.getString("NOMBRE"));
		ldnj_DomNatJur.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, ldnj_DomNatJur);

		return ldnj_DomNatJur;
	}
}
