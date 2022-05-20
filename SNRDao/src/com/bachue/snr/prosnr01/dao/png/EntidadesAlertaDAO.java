package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.png.EntidadesAlerta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades EntidadesAlertaDAO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 3/04/2020
 */
public class EntidadesAlertaDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PNG_ENTIDADES_ALERTA";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PNG_ENTIDADES_ALERTA WHERE ID_ENTIDAD = ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PNG_ENTIDADES_ALERTA(ID_ENTIDAD, TIPO_DOCUMENTO_ID, NUMERO_DOCUMENTO_ID, NOMBRE, EMAIL, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ? , ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PNG_ENTIDADES_ALERTA SET TIPO_DOCUMENTO_ID=?, NUMERO_DOCUMENTO_ID=?, NOMBRE=?, EMAIL=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_ENTIDAD=?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_PNG_ENTIDADES_ALERTA_ID_ENTIDAD.NEXTVAL FROM DUAL";

	/**
	 * Find all.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EntidadesAlerta> findAll()
	    throws B2BException
	{
		Collection<EntidadesAlerta> lcet_cat;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcet_cat     = new ArrayList<EntidadesAlerta>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcet_cat.add(getObjetFromResultSet(lrs_rs));
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

		if(lcet_cat.isEmpty())
			lcet_cat = null;

		return lcet_cat;
	}

	/**
	 * Find by id.
	 *
	 * @param ll_idEntidad de ll id entidad
	 * @return el valor de entidades alerta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EntidadesAlerta findById(long ll_idEntidad)
	    throws B2BException
	{
		EntidadesAlerta   lca_at;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lca_at     = null;
		lps_ps     = null;
		lrs_rs     = null;

		if(ll_idEntidad > 0L)
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setLong(li_column++, ll_idEntidad);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lca_at = getObjetFromResultSet(lrs_rs);
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

		return lca_at;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param aea_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(EntidadesAlerta aea_param)
	    throws B2BException
	{
		if(aea_param != null)
		{
			int               li_column;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			li_column         = 1;

			try
			{
				Connection lc_C;

				lc_C       = getConnection();
				lps_ps     = lc_C.prepareStatement(cs_INSERT);

				lps_secuencia     = lc_C.prepareStatement(cs_FIND_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setLong(li_column++, NumericUtils.getLong(lrs_rs.getInt(1)));
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				{
					lps_ps.setString(li_column++, aea_param.getTipoDocumentoId());
					lps_ps.setString(li_column++, aea_param.getNumeroDocumentoId());
					lps_ps.setString(li_column++, aea_param.getNombre());
					lps_ps.setString(li_column++, aea_param.getEmail());
					lps_ps.setString(li_column++, aea_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aea_param.getIpCreacion());
				}

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
				close(lps_secuencia);
			}
		}
	}

	/**
	 * Actualiza un registro
	 * con la información suministrada.
	 *
	 * @param aea_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(EntidadesAlerta aea_param)
	    throws B2BException
	{
		if(aea_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int           li_column;
				StringBuilder lsb_query;

				li_column     = 1;
				lsb_query     = new StringBuilder(cs_UPDATE);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, aea_param.getTipoDocumentoId());
				lps_ps.setString(li_column++, aea_param.getNumeroDocumentoId());
				lps_ps.setString(li_column++, aea_param.getNombre());
				lps_ps.setString(li_column++, aea_param.getEmail());
				lps_ps.setString(li_column++, aea_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aea_param.getIpModificacion());
				lps_ps.setLong(li_column++, aea_param.getIdEntidad());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private EntidadesAlerta getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		EntidadesAlerta lea_entidadesAlerta;

		lea_entidadesAlerta = new EntidadesAlerta();

		lea_entidadesAlerta.setIdEntidad(ars_rs.getLong("ID_ENTIDAD"));
		lea_entidadesAlerta.setTipoDocumentoId(ars_rs.getString("TIPO_DOCUMENTO_ID"));
		lea_entidadesAlerta.setNumeroDocumentoId(ars_rs.getString("NUMERO_DOCUMENTO_ID"));
		lea_entidadesAlerta.setNombre(ars_rs.getString("NOMBRE"));
		lea_entidadesAlerta.setEmail(ars_rs.getString("EMAIL"));

		fillAuditoria(ars_rs, lea_entidadesAlerta);

		return lea_entidadesAlerta;
	}
}
