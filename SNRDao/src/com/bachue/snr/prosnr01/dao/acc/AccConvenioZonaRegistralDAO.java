package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.AccConvenioZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExternaConvenio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_CONVENIO_ZONA_REGISTRAL.
 *
 * @author Sebastian Sanchez
 */
public class AccConvenioZonaRegistralDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_BY_ID_ACTIVO. */
	private static final String cs_FIND_BY_ID_ACTIVO = " SELECT * FROM SDB_ACC_CONVENIO_ZONA_REGISTRAL WHERE NUMERO_CONVENIO= ? AND ACTIVO = 'S' ";

	/** Constante  cs_FIND_BY_ID_ENTIDAD_NUMERO. */
	private static final String cs_FIND_BY_ID_ENTIDAD_NUMERO = " SELECT * FROM SDB_ACC_CONVENIO_ZONA_REGISTRAL WHERE ID_ENTIDAD_EXTERNA = ? AND NUMERO_CONVENIO= ?";

	/** Constante  cs_FIND_BY_ID_ZONA_NUMERO. */
	private static final String cs_FIND_BY_ID_ZONA_NUMERO = " SELECT * FROM SDB_ACC_CONVENIO_ZONA_REGISTRAL WHERE ID_ZONA_REGISTRAL = ? AND NUMERO_CONVENIO= ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_CONVENIO_ZONA_REGISTRAL(ID_CONVENIO_ZONA_REGISTRAL, NUMERO_CONVENIO, ID_ENTIDAD_EXTERNA, "
		+ "ID_ZONA_REGISTRAL, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE_ACTIVO. */
	private static final String cs_UPDATE_ACTIVO = "UPDATE SDB_ACC_CONVENIO_ZONA_REGISTRAL SET "
		+ " ACTIVO='S', ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, "
		+ "FECHA_MODIFICACION = SYSTIMESTAMP WHERE NUMERO_CONVENIO=? AND ID_ENTIDAD_EXTERNA=? AND ID_ZONA_REGISTRAL=?";

	/** Constante  cs_UPDATE_INACTIVO. */
	private static final String cs_UPDATE_INACTIVO = "UPDATE SDB_ACC_CONVENIO_ZONA_REGISTRAL SET "
		+ " ACTIVO='N',ID_ENTIDAD_EXTERNA=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, "
		+ "FECHA_MODIFICACION = SYSTIMESTAMP WHERE NUMERO_CONVENIO=?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_CONVENIO_ZONA_REGISTRAL_ID_CONVENIO_ZONA_REGISTRAL.NEXTVAL FROM DUAL";

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param aaczr_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Collection de AccConvenioZonaRegistral resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccConvenioZonaRegistral> findByIdActivo(AccConvenioZonaRegistral aaczr_param)
	    throws B2BException
	{
		Collection<AccConvenioZonaRegistral> lcaeec_convenioZonaRegistral;

		lcaeec_convenioZonaRegistral = new ArrayList<AccConvenioZonaRegistral>();

		if(aaczr_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;

				lsb_sb     = new StringBuilder(cs_FIND_BY_ID_ACTIVO);

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(1, aaczr_param.getNumeroConvenio());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcaeec_convenioZonaRegistral.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdActivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcaeec_convenioZonaRegistral;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param aaczr_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Collection de AccConvenioZonaRegistral resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccConvenioZonaRegistral> findByIdEntidadNumero(AccConvenioZonaRegistral aaczr_param)
	    throws B2BException
	{
		Collection<AccConvenioZonaRegistral> lcaeec_convenioZonaRegistral;

		lcaeec_convenioZonaRegistral = new ArrayList<AccConvenioZonaRegistral>();

		if(aaczr_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;
				int           li_column;

				lsb_sb        = new StringBuilder(cs_FIND_BY_ID_ENTIDAD_NUMERO);
				li_column     = 1;

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, aaczr_param.getIdEntidadExterna());
				lps_ps.setString(li_column++, aaczr_param.getNumeroConvenio());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcaeec_convenioZonaRegistral.add(getObjetFromResultSet(lrs_rs));
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

		return lcaeec_convenioZonaRegistral;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param aaczr_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Collection de AccConvenioZonaRegistral resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccConvenioZonaRegistral> findByIdZonaNumero(AccConvenioZonaRegistral aaczr_param)
	    throws B2BException
	{
		Collection<AccConvenioZonaRegistral> lcaeec_convenioZonaRegistral;

		lcaeec_convenioZonaRegistral = new ArrayList<AccConvenioZonaRegistral>();

		if(aaczr_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;
				int           li_column;

				lsb_sb        = new StringBuilder(cs_FIND_BY_ID_ZONA_NUMERO);
				li_column     = 1;

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, aaczr_param.getIdZonaRegistral());
				lps_ps.setString(li_column++, aaczr_param.getNumeroConvenio());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcaeec_convenioZonaRegistral.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdZonaNumero", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcaeec_convenioZonaRegistral;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param ac_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(AccEntidadExternaConvenio ac_param)
	    throws B2BException
	{
		if(ac_param != null)
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
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				{
					lps_ps.setString(li_column++, ac_param.getNumeroConvenio());
					lps_ps.setString(li_column++, ac_param.getIdEntidadExterna());
					lps_ps.setString(li_column++, ac_param.getIdZonaRegistral());
					lps_ps.setString(li_column++, ac_param.getActivo());
					lps_ps.setString(li_column++, ac_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ac_param.getIpCreacion());
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
	 * Método para modificar el campo ACTIVO de la tabla.
	 *
	 * @param aaeec_param correspondiente al valor de aaeec param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateActivo(AccEntidadExternaConvenio aaeec_param)
	    throws B2BException
	{
		if(aaeec_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_ACTIVO);

				lps_ps.setString(li_column++, aaeec_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aaeec_param.getIpModificacion());

				lps_ps.setString(li_column++, aaeec_param.getNumeroConvenio());
				lps_ps.setString(li_column++, aaeec_param.getIdEntidadExterna());
				lps_ps.setString(li_column++, aaeec_param.getIdZonaRegistral());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "updateActivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para modificar el campo ACTIVO de la tabla.
	 *
	 * @param aaeec_param correspondiente al valor de aaeec param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateInactivo(AccEntidadExternaConvenio aaeec_param)
	    throws B2BException
	{
		if(aaeec_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_INACTIVO);

				lps_ps.setString(li_column++, aaeec_param.getIdEntidadExterna());
				lps_ps.setString(li_column++, aaeec_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aaeec_param.getIpModificacion());

				lps_ps.setString(li_column++, aaeec_param.getNumeroConvenio());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "updateActivo", lse_e);

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
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private AccConvenioZonaRegistral getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		AccConvenioZonaRegistral ls_solicitud;

		ls_solicitud = new AccConvenioZonaRegistral();

		ls_solicitud.setIdEntidadExterna(lrs_rs.getString("ID_ENTIDAD_EXTERNA"));
		ls_solicitud.setNumeroConvenio(lrs_rs.getString("NUMERO_CONVENIO"));
		ls_solicitud.setIdConvenioZonaRegistral(lrs_rs.getString("ID_CONVENIO_ZONA_REGISTRAL"));
		ls_solicitud.setIdZonaRegistral(lrs_rs.getString("ID_ZONA_REGISTRAL"));
		ls_solicitud.setActivo(lrs_rs.getString("ACTIVO"));
		ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ls_solicitud.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitud.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitud.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return ls_solicitud;
	}
}
