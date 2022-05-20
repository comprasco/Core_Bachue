package com.bachue.snr.prosnr01.dao.col;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_COL_INTERESADO_DOCUMENTO_TIPO
 *
 * @author lchacon
 */
public class InteresadoDocumentoTipoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_DOCUMENTO_TIPO, ILICODE, DESCRIPCION, ACTIVO, "
		+ " ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_COL_INTERESADO_DOCUMENTO_TIPO WHERE ID_DOCUMENTO_TIPO=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_COL_INTERESADO_DOCUMENTO_TIPO SET ILICODE=?, "
		+ " DESCRIPCION=?, ACTIVO=?,  ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_DOCUMENTO_TIPO=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_COL_INTERESADO_DOCUMENTO_TIPO(ID_DOCUMENTO_TIPO, "
		+ " ILICODE, DESCRIPCION, ACTIVO, ID_USUARIO_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, ?)";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_DOCUMENTO_TIPO, ILICODE, DESCRIPCION, ACTIVO, "
		+ " ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_COL_INTERESADO_DOCUMENTO_TIPO WHERE ACTIVO = 'S' ORDER BY DESCRIPCION";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_DOCUMENTO_TIPO, ILICODE, DESCRIPCION, ACTIVO, "
		+ " ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_COL_INTERESADO_DOCUMENTO_TIPO  WHERE ACTIVO = 'S' OR ID_DOCUMENTO_TIPO = 'SE' ORDER BY DESCRIPCION";

	/** Constante cs_FIND_RRR. */
	private static final String cs_FIND_RRR = "SELECT PI.ID_PARTE_INTERESADA,PI.DESCRIPCION FROM SDB_COL_PARTE_INTERESADA PI,SDB_COL_PARTE_INTERESADA_TIPO_ACTO PITA WHERE PI.ID_PARTE_INTERESADA=PITA.ID_PARTE_INTERESADA AND PITA.ID_NATURALEZA_JURIDICA=? AND PITA.VERSION=?";

	/** Constante cs_FIND_RRR_NOMBRE. */
	private static final String cs_FIND_RRR_NOMBRE = "    SELECT DISTINCT a.ID_PARTE_INTERESADA, b.DESCRIPCION FROM  SDB_COL_PARTE_INTERESADA_TIPO_ACTO a, SDB_COL_PARTE_INTERESADA b,SDB_PNG_NATURALEZA_JURIDICA c"
		+ " WHERE a.ID_NATURALEZA_JURIDICA = c.ID_NATURALEZA_JURIDICA AND a.ID_PARTE_INTERESADA = b.ID_PARTE_INTERESADA AND a.ID_PARTE_INTERESADA = ? AND c.VERSION = 1   ";

	/**
	 * Retorna el valor del objeto de tipo Collection de InteresadoDocumentoTipo buscando todo en la tabla
	 *
	 * @return devuelve el valor del objeto collection de InteresadoDocumentoTipo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see InteresadoDocumentoTipo
	 */
	public Collection<InteresadoDocumentoTipo> findAll()
	    throws B2BException
	{
		Collection<InteresadoDocumentoTipo> lcidt_datos;
		PreparedStatement                   lps_ps;
		ResultSet                           lrs_rs;

		lcidt_datos     = new ArrayList<InteresadoDocumentoTipo>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcidt_datos.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcidt_datos))
			lcidt_datos = null;

		return lcidt_datos;
	}

	/**
	 * Retorna el valor del objeto de tipo de Collection de InteresadoDocumentoTipo filtrado por los que se
	 * encuentran activos
	 *
	 * @return devuelve el valor del objeto collection de InteresadoDocumentoTipo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see InteresadoDocumentoTipo
	 */
	public Collection<InteresadoDocumentoTipo> findAllActivo()
	    throws B2BException
	{
		Collection<InteresadoDocumentoTipo> lcidt_datos;
		PreparedStatement                   lps_ps;
		ResultSet                           lrs_rs;

		lcidt_datos     = new ArrayList<InteresadoDocumentoTipo>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcidt_datos.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcidt_datos))
			lcidt_datos = null;

		return lcidt_datos;
	}

	/**
	 * Retorna el valor del objeto de tipo InteresadoDocumentoTipo
	 *
	 * @param at_param correspondiente al valor del tipo de objeto InteresadoDocumentoTipo
	 * @return devuelve el valor del objeto interesado documento tipo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see InteresadoDocumentoTipo
	 */
	public InteresadoDocumentoTipo findById(InteresadoDocumentoTipo at_param)
	    throws B2BException
	{
		return (at_param != null) ? findById(at_param.getIdDocumentoTipo()) : null;
	}

	/**
	 * Retorna el valor del objeto de tipo InteresadoDocumentoTipo filtrado por el ID docuemnto
	 *
	 * @param as_idDocumentoTipo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto interesado documento tipo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see InteresadoDocumentoTipo
	 */
	public InteresadoDocumentoTipo findById(String as_idDocumentoTipo)
	    throws B2BException
	{
		InteresadoDocumentoTipo lidt_idt;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lidt_idt     = null;
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_contador++, as_idDocumentoTipo);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lidt_idt = getObjetFromResultSet(lrs_rs);
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

		return lidt_idt;
	}

	/**
	 * Retorna el valor del objeto de tipo InteresadoDocumentoTipo filtrado por la parte interesada
	 *
	 * @param at_param correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto interesado documento tipo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see InteresadoDocumentoTipo
	 */
	public InteresadoDocumentoTipo findByIdInteresadoRRR(String at_param)
	    throws B2BException
	{
		InteresadoDocumentoTipo lidt_idt;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lidt_idt     = null;
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_RRR_NOMBRE);

			lps_ps.setString(1, at_param);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lidt_idt = getObjetFromResultSetRrrNombre(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdInteresadoRRR", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lidt_idt;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de InteresadoDocumentoTipo filtrado por
	 * el ID de naturaleza juridica y version
	 *
	 * @param as_idActo correspondiente al valor del tipo de objeto String
	 * @param al_maxVersion correspondiente al valor del tipo de objeto long
	 * @return devuelve el valor del objeto collection de InteresadoDocumentoTipo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see InteresadoDocumentoTipo
	 */
	public Collection<InteresadoDocumentoTipo> findRrr(String as_idActo, long al_maxVersion)
	    throws B2BException
	{
		Collection<InteresadoDocumentoTipo> lcidt_datos;
		PreparedStatement                   lps_ps;
		ResultSet                           lrs_rs;
		InteresadoDocumentoTipo             lidt_idt;

		lcidt_datos     = new ArrayList<InteresadoDocumentoTipo>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_RRR);

			lps_ps.setString(1, as_idActo);
			lps_ps.setLong(2, al_maxVersion);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
			{
				lidt_idt = new InteresadoDocumentoTipo();
				lidt_idt.setIdDocumentoTipo(lrs_rs.getString("ID_PARTE_INTERESADA"));
				lidt_idt.setDescripcion(lrs_rs.getString("DESCRIPCION"));
				lcidt_datos.add(lidt_idt);
			}
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

		if(!CollectionUtils.isValidCollection(lcidt_datos))
			lcidt_datos = null;

		return lcidt_datos;
	}

	/**
	 *Inserta o Actualiza en la base de datos el tipo que se recibe por parametro
	 *
	 * @param aidt_param correspondiente al valor del tipo de objeto InteresadoDocumentoTipo
	 * @param ab_query correspondiente al valor del tipo de objeto boolean,  <b>verdadero</b> para insertar un nuevo registro en la base de datos, <b>falso</b> para actualizar
	 * el registro en la base de datos.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(InteresadoDocumentoTipo aidt_param, boolean ab_query)
	    throws B2BException
	{
		if(aidt_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
					lps_ps.setString(li_column++, aidt_param.getIdDocumentoTipo());

				lps_ps.setString(li_column++, aidt_param.getIlicode());
				lps_ps.setString(li_column++, aidt_param.getDescripcion());
				lps_ps.setString(li_column++, aidt_param.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, aidt_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aidt_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, aidt_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, aidt_param.getIpModificacion());
				}

				if(!ab_query)
					lps_ps.setString(li_column++, aidt_param.getIdDocumentoTipo());

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
	 * Retorna el valor de InteresadoDocumentoTipo
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de InteresadoDocumentoTipo
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see InteresadoDocumentoTipo
	 */
	private InteresadoDocumentoTipo getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		InteresadoDocumentoTipo lidt_idt;

		lidt_idt = new InteresadoDocumentoTipo();

		lidt_idt.setIdDocumentoTipo(lrs_rs.getString("ID_DOCUMENTO_TIPO"));
		lidt_idt.setIlicode(lrs_rs.getString("ILICODE"));
		lidt_idt.setDescripcion(lrs_rs.getString("DESCRIPCION"));
		lidt_idt.setActivo(lrs_rs.getString("ACTIVO"));

		fillAuditoria(lrs_rs, lidt_idt);

		return lidt_idt;
	}

	/**
	 * Retorna el valor de InteresadoDocumentoTipo
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de objet from result set rrr nombre
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see InteresadoDocumentoTipo
	 */
	private InteresadoDocumentoTipo getObjetFromResultSetRrrNombre(ResultSet lrs_rs)
	    throws SQLException
	{
		InteresadoDocumentoTipo lidt_idt;

		lidt_idt = new InteresadoDocumentoTipo();

		lidt_idt.setIdDocumentoTipo(lrs_rs.getString("ID_PARTE_INTERESADA"));
		lidt_idt.setDescripcion(lrs_rs.getString("DESCRIPCION"));

		return lidt_idt;
	}
}
