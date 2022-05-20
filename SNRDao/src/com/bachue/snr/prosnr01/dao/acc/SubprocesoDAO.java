package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Controla las acciones CRUD sobre la tabla SDB_ACC_SUBPROCESO
 *
 * @author Nicolas Guaneme
 *
 */
public class SubprocesoDAO extends BaseDAO implements IBase<Subproceso>
{
	/** Constante cs_FIND_BY_ID . */
	private static final String cs_FIND_BY_ID = "SELECT ID_PROCESO,ID_SUBPROCESO,NOMBRE,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION,ACTIVO FROM SDB_ACC_SUBPROCESO WHERE ID_PROCESO=? AND ID_SUBPROCESO = ?";

	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_PROCESO, ID_SUBPROCESO, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO FROM SDB_ACC_SUBPROCESO ";

	/** Constante cs_FIND_BY_PROCESO . */
	private static final String cs_FIND_BY_PROCESO = "SELECT ID_PROCESO, ID_SUBPROCESO, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ACTIVO FROM SDB_ACC_SUBPROCESO WHERE ID_PROCESO=? AND ACTIVO = 'S' ";

	/** Constante cs_FIND_BY_PROCESO_SOLICITUD_CERT. */
	private static final String cs_FIND_BY_PROCESO_SOLICITUD_CERT = "SELECT SAS.ID_PROCESO, SAS.ID_SUBPROCESO, SAS.NOMBRE, SAS.ID_USUARIO_CREACION, SAS.FECHA_CREACION, SAS.IP_CREACION, SAS.ID_USUARIO_MODIFICACION, SAS.FECHA_MODIFICACION, SAS.IP_MODIFICACION, SAS.ACTIVO FROM SDB_ACC_SUBPROCESO SAS INNER JOIN SDB_ACC_SUBPROCESO_VERSION SASV ON SASV.ID_PROCESO = SAS.ID_PROCESO AND SASV.ID_SUBPROCESO = SAS.ID_SUBPROCESO AND SASV.VERSION_SUBPROCESO = (SELECT MAX(VERSION_SUBPROCESO) FROM SDB_ACC_SUBPROCESO_VERSION SASV1 WHERE SASV1.ID_PROCESO = SASV.ID_PROCESO AND SASV1.ID_SUBPROCESO = SASV.ID_SUBPROCESO) AND SASV.SOLICITUD_CERTIFICADO = ? WHERE SAS.ID_PROCESO = ? ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_ACTIVO_BY_ID_PROCESO. */
	private static final String cs_FIND_ACTIVO_BY_PROCESO = "SELECT ID_PROCESO, ID_SUBPROCESO, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, NVL(ACTIVO,'S')ACTIVO FROM SDB_ACC_SUBPROCESO WHERE ID_PROCESO = ? AND ACTIVO = 'S' ORDER BY NOMBRE ASC";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SUBPROCESO SET NOMBRE=?, ACTIVO=?, ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_PROCESO=? AND ID_SUBPROCESO=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SUBPROCESO(ID_PROCESO, ID_SUBPROCESO, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ACTIVO) VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?, ?)";

	/**
	 * Consulta todos los subprocesos que se encuentren activos y los filtra por
	 * un id proceso previamente definido
	 *
	 * @param as_subproceso objeto que contiene el id_proceso para aplicar al filtro
	 * @return Colección de Subprocesos resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<Subproceso> findActivoByProceso(Subproceso as_subproceso)
	    throws B2BException
	{
		Collection<Subproceso> lp_subproceso;
		lp_subproceso = new ArrayList<Subproceso>();

		if(as_subproceso != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ACTIVO_BY_PROCESO);

				lps_ps.setString(1, as_subproceso.getIdProceso());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lp_subproceso.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findActivoByProceso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lp_subproceso.isEmpty())
			lp_subproceso = null;

		return lp_subproceso;
	}

	/**
	 * Consulta en base de datos todos los registros que se encuentren
	 * activos y los ordena de forma ascendente
	 *
	 * @return Colección de Subprocesos resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<Subproceso> findAll()
	    throws B2BException
	{
		return findAll(true);
	}

	/**
	 * Consulta en base de datos todos los registros que se encuentren
	 * activos y los ordena de forma ascendente
	 * @param  ab_activo Argumento de tipo boolean que indica la busqueda de subprocesos activos.
	 * @return Colección de Subprocesos resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<Subproceso> findAll(boolean ab_activo)
	    throws B2BException
	{
		Collection<Subproceso> lp_subproceso;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lp_subproceso     = new ArrayList<Subproceso>();
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder(cs_FIND_ALL);

			if(ab_activo)
				lsb_sb.append(" WHERE ACTIVO = 'S' ");

			lsb_sb.append(" ORDER BY LENGTH(ID_PROCESO),ID_PROCESO ASC ");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_subproceso.add(getObjetFromResultSet(lrs_rs));
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

		if(lp_subproceso.isEmpty())
			lp_subproceso = null;

		return lp_subproceso;
	}

	/**
	 * Busca en la base de datos un registro asociado a un id proceso e id subproceso específicos
	 *
	 * @param as_idProceso Id del proceso a utilizar como filtro en la busqueda
	 * @param as_idSubproceso Id del subroceso a utilizar como filtro en la busqueda
	 * @return Subproceso resultante de la busqueda
	 * @throws B2BException
	 */
	public Subproceso findById(String as_idProceso, String as_idSubproceso)
	    throws B2BException
	{
		Subproceso ls_return;

		ls_return = null;

		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubproceso))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_column++, as_idProceso);
				lps_ps.setString(li_column++, as_idSubproceso);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_return = getObjetFromResultSet(lrs_rs);
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

		return ls_return;
	}

	/**
	 * Consulta en la base de datos un subproceso, filtrando por un id de
	 * subproceso definido con anterioridad
	 *
	 * @param as_param objeto contenedor del id subproceso que se va a aplicar
	 * en el filtro de la consulta
	 * @return Subproceso resultante de la ejecución de la consulta
	 * @throws B2BException
	 */
	public Subproceso findById(Subproceso as_param)
	    throws B2BException
	{
		return (as_param != null) ? findById(as_param.getIdProceso(), as_param.getIdSubproceso()) : null;
	}

	/**
	 * Metodo sobrecargado para extraer el idProceso y consultar por este
	 * 
	 * @param as_subproceso Subproceso con idProceso contenido
	 * @return coleccion de Subprocesos, resultado de consulta
	 * @throws B2BException
	 */
	public Collection<Subproceso> findByProceso(Subproceso as_subproceso,boolean ab_OrderById)
	    throws B2BException
	{
		Collection<Subproceso> lcs_return;

		lcs_return = null;

		if(as_subproceso != null)
			lcs_return = findByProceso(as_subproceso.getIdProceso(),ab_OrderById);

		return lcs_return;
	}

	/**
	 * Consulta todos los subprocesos y los filtra por un id proceso previamente definido
	 *
	 * @param as_idProceso String del id_proceso para aplicar al filtro
	 * @param ab_OrderById boolean del ab_OrderById para aplicar al filtro
	 * @return Colección de Subprocesos resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<Subproceso> findByProceso(String as_idProceso, boolean ab_OrderById)
	    throws B2BException
	{
		Collection<Subproceso> lp_subproceso;
		
		lp_subproceso = new ArrayList<Subproceso>();

		if(StringUtils.isValidString(as_idProceso))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder(cs_FIND_BY_PROCESO);

				lsb_query.append(ab_OrderById ? " ORDER BY LENGTH(ID_SUBPROCESO),ID_SUBPROCESO " : " ORDER BY NOMBRE ASC ");
				lps_ps     = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(1, as_idProceso);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lp_subproceso.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByProceso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lp_subproceso.isEmpty())
			lp_subproceso = null;

		return lp_subproceso;
	}

	/**
	 * Consulta todos los subprocesos y los filtra por un id proceso y solicitud certificado previamente definido
	 *
	 * @param as_subproceso objeto que contiene el id_proceso y solicitud_certificado para aplicar al filtro
	 * @return Colección de Subprocesos resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<Subproceso> findByProcesoSolicitudCert(Subproceso as_subproceso)
	    throws B2BException
	{
		Collection<Subproceso> lcs_subproceso;
		lcs_subproceso = new ArrayList<Subproceso>();

		if(as_subproceso != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_PROCESO_SOLICITUD_CERT);

				lps_ps.setString(li_cont++, as_subproceso.getSolicitudCertificado());
				lps_ps.setString(li_cont++, as_subproceso.getIdProceso());
				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcs_subproceso.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByProcesoSolicitudCert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcs_subproceso.isEmpty())
			lcs_subproceso = null;

		return lcs_subproceso;
	}

	/**
	 * Dependiendo del procedimiento seleccionado, inserta o actualiza un registro
	 * con la información del subproceso suministrada.
	 *
	 * @param as_param objeto contenedor de la información a actualizar o insertar
	 * @param ab_query define el proceso seleccionado, true para insertar un nuevo
	 * registro, false para actualizar un registro existente.
	 * @throws B2BException
	 */
	public void insertOrUpdate(Subproceso as_param, boolean ab_query)
	    throws B2BException
	{
		if(as_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setString(li_column++, as_param.getIdProceso());
					lps_ps.setString(li_column++, as_param.getIdSubproceso());
					lps_ps.setString(li_column++, as_param.getNombre());
					lps_ps.setString(li_column++, as_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, as_param.getIpCreacion());
					lps_ps.setString(li_column++, as_param.getActivo());
				}
				else
				{
					lps_ps.setString(li_column++, as_param.getNombre());
					lps_ps.setString(li_column++, as_param.getActivo());
					lps_ps.setString(li_column++, as_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, as_param.getIpModificacion());
					lps_ps.setString(li_column++, as_param.getIdProceso());
					lps_ps.setString(li_column++, as_param.getIdSubproceso());
				}

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
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param lrs_rs contenedor del resultado de a consulta
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws SQLException
	 */
	private Subproceso getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		Subproceso ls_solicitud;

		ls_solicitud = new Subproceso();

		ls_solicitud.setIdProceso(lrs_rs.getString("ID_PROCESO"));
		ls_solicitud.setIdSubproceso(lrs_rs.getString("ID_SUBPROCESO"));
		ls_solicitud.setNombre(lrs_rs.getString("NOMBRE"));
		ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ls_solicitud.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitud.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitud.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));
		ls_solicitud.setActivo(lrs_rs.getString("ACTIVO"));

		return ls_solicitud;
	}
}
