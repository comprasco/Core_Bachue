package com.bachue.snr.prosnr01.dao.aut;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.aut.OpcionEtapa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
* Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
* directamente a la tabla SDB_AUT_OPCION_ETAPA
*
* @author ccalderon
*
*/
public class OpcionEtapaDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_AUT_OPCION_ETAPA";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_AUT_OPCION_ETAPA (ID_ETAPA,ID_OPCION,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION)VALUES(?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_AUT_OPCION_ETAPA WHERE ID_ETAPA=? AND ID_OPCION=?";

	/** Constante cs_UPDATE_ACTIVO. */
	private static final String cs_DELETE = "DELETE FROM SDB_AUT_OPCION_ETAPA WHERE ID_OPCION=?";

	/** Constante cs_FIND_BY_ID_OPCION. */
	private static final String cs_FIND_BY_ID_OPCION = "SELECT * FROM SDB_AUT_OPCION_ETAPA WHERE ID_OPCION = ?";

	/**
	* Método para consultar todos los registro de la tabla SDB_AUT_OPCION_ETAPA
	*
	* @return Collection<OpcionEtapa> Colección de OpcionEtapa que contiene los criterios de búsqueda
	* @throws B2BException
	*/
	public Collection<OpcionEtapa> findAll()
	    throws B2BException
	{
		Collection<OpcionEtapa> lcoe_datos;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lcoe_datos     = new ArrayList<OpcionEtapa>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcoe_datos.add(getOpcionEtapa(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcoe_datos))
			lcoe_datos = null;

		return lcoe_datos;
	}

	/**
	* Consulta todos los registros relacionados con un id opción
	*
	* @param as_idOpcion Objeto contenedor del id opción que será utilizado
	* como filtro en la consulta
	* @return Objeto contenedor del resultado de la consulta
	* @throws B2BException
	*/
	public Collection<OpcionEtapa> findByIdOpcion(String as_idOpcion)
	    throws B2BException
	{
		Collection<OpcionEtapa> lcoe_opcionEtapa;

		lcoe_opcionEtapa = new ArrayList<OpcionEtapa>();

		if(StringUtils.isValidString(as_idOpcion))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_OPCION);

				lps_ps.setString(1, as_idOpcion);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcoe_opcionEtapa.add(getOpcionEtapa(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdOpcion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcoe_opcionEtapa;
	}

	/**
	* Método para inserción de datos en la tabla SDB_AUT_OPCION_ETAPA
	* @param aoe_param
	* @throws B2BException
	*/
	public void insert(OpcionEtapa aoe_param)
	    throws B2BException
	{
		if(aoe_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setLong(li_column++, aoe_param.getIdEtapa());
				lps_ps.setString(li_column++, aoe_param.getIdOpcion());

				lps_ps.setString(li_column++, aoe_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aoe_param.getIpCreacion());

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
			}
		}
	}

	/**
	* Método para encontrar los datos de la tabla SDB_AUT_OPCION_ETAPA por medio de su llave primaria
	* @param as_idEtapa
	* @param as_idOpcion
	* @return
	* @throws B2BException
	*/
	public OpcionEtapa findOpcionEtapaById(long as_idEtapa, String as_idOpcion)
	    throws B2BException
	{
		OpcionEtapa       loe_rolOpcion;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		loe_rolOpcion     = null;
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			if((as_idEtapa > 0) && StringUtils.isValidString(as_idOpcion))
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setLong(li_contador++, as_idEtapa);
				lps_ps.setString(li_contador++, as_idOpcion);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					loe_rolOpcion = getOpcionEtapa(lrs_rs);
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

		return loe_rolOpcion;
	}

	/**
	* Método para eliminar un registro en la BD.
	*
	* @param as_idOpcion objeto OpcionEtapa para eliminar el registro
	* @throws B2BException Señala que se ha producido una excepción
	*/
	public void delete(String as_idOpcion)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		int               li_column;

		lps_ps        = null;
		li_column     = 1;

		if(StringUtils.isValidString(as_idOpcion))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setString(li_column++, as_idOpcion);

				lps_ps.executeQuery();
			}
			catch(SQLException lse_e)
			{
				logError(this, "delete", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	* Extrae la información de un registro de base de datos, la asigna a un objeto y la retorna
	*
	* @param ars_rs Objeto contenedor de los resultados de la consulta
	* @return Objeto tipo eje con la información recuperada de la base de datos
	* @throws SQLException
	*/
	private OpcionEtapa getOpcionEtapa(ResultSet ars_rs)
	    throws SQLException
	{
		OpcionEtapa loe_opcionEtapa;

		loe_opcionEtapa = new OpcionEtapa();

		loe_opcionEtapa.setIdEtapa(ars_rs.getLong("ID_ETAPA"));
		loe_opcionEtapa.setIdOpcion(ars_rs.getString("ID_OPCION"));

		fillAuditoria(ars_rs, loe_opcionEtapa);

		return loe_opcionEtapa;
	}
}
