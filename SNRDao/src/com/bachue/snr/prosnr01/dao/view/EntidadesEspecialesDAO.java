package com.bachue.snr.prosnr01.dao.view;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.view.EntidadesEspeciales;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades EntidadesEspecialesDAO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/07/2020
 */
public class EntidadesEspecialesDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_VW_ENTIDADES_ESPECIALES";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_VW_ENTIDADES_ESPECIALES WHERE ID_ENTIDAD_EXTERNA = ?";

	/**
	 * Find all.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<EntidadesEspeciales> findAll()
	    throws B2BException
	{
		Collection<EntidadesEspeciales> lcee_cee;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lcee_cee     = new ArrayList<EntidadesEspeciales>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcee_cee.add(getObjetFromResultSet(lrs_rs));
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

		if(lcee_cee.isEmpty())
			lcee_cee = null;

		return lcee_cee;
	}

	/**
	 * Find by id.
	 *
	 * @param ll_idEntidad de ll id entidad
	 * @return el valor de entidades especiales
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EntidadesEspeciales findById(long ll_idEntidad)
	    throws B2BException
	{
		EntidadesEspeciales lca_at;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lca_at     = null;
		lps_ps     = null;
		lrs_rs     = null;

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

		return lca_at;
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private EntidadesEspeciales getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		EntidadesEspeciales lea_EntidadesEspeciales;

		lea_EntidadesEspeciales = new EntidadesEspeciales();

		lea_EntidadesEspeciales.setIdEntidad(ars_rs.getLong("ID_ENTIDAD_EXTERNA"));
		lea_EntidadesEspeciales.setTipoDocumentoId(ars_rs.getString("ID_DOCUMENTO_TIPO"));
		lea_EntidadesEspeciales.setNumeroDocumentoId(ars_rs.getString("NUMERO_DOCUMENTO"));
		lea_EntidadesEspeciales.setNombre(ars_rs.getString("NOMBRE"));
		lea_EntidadesEspeciales.setEmail(ars_rs.getString("CORREO_ELECTRONICO"));
		lea_EntidadesEspeciales.setActivo(ars_rs.getString("ACTIVO"));
		lea_EntidadesEspeciales.setDireccion(ars_rs.getString("DIRECCION"));
		lea_EntidadesEspeciales.setEntidadExenta(ars_rs.getString("ENTIDAD_EXENTA"));
		lea_EntidadesEspeciales.setIdActividadEconomica(ars_rs.getString("ID_ACTIVIDAD_ECONOMICA"));
		lea_EntidadesEspeciales.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		lea_EntidadesEspeciales.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		lea_EntidadesEspeciales.setIdPais(ars_rs.getString("ID_PAIS"));
		lea_EntidadesEspeciales.setIdRepresentanteLegal(ars_rs.getString("ID_REPRESENTANTE_LEGAL"));
		lea_EntidadesEspeciales.setIdTipoEntidad(ars_rs.getString("ID_TIPO_ENTIDAD"));
		lea_EntidadesEspeciales.setIdTipoOficina(ars_rs.getString("ID_TIPO_OFICINA"));
		lea_EntidadesEspeciales.setTelefono(ars_rs.getString("TELEFONO"));

		return lea_EntidadesEspeciales;
	}
}
