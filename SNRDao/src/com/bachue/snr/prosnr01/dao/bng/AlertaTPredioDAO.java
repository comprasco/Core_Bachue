package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.bng.AlertaTPredio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase que contiene todos las propiedades AlertaTPredioDAO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 2/04/2020
 */
public class AlertaTPredioDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SBD_BNG_ALERTA_T_PREDIO WHERE ID_ALERTA_TIERRAS = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_FIND_ALL_BY_ID_ALERTA_TIERRAS. */
	private static final String cs_FIND_ALL_BY_ID_ALERTA_TIERRAS = "SELECT ATP.*, CR.NOMBRE AS NOMBRE_CIRCULO, PR.NUMERO_PREDIAL"
		+ " FROM SBD_BNG_ALERTA_T_PREDIO ATP"
		+ " INNER JOIN SDB_PGN_CIRCULO_REGISTRAL CR ON ATP.ID_CIRCULO = CR.ID_CIRCULO"
		+ " INNER JOIN SDB_BNG_PREDIO_REGISTRO PR ON ATP.ID_CIRCULO = PR.ID_CIRCULO AND ATP.ID_MATRICULA = PR.ID_MATRICULA"
		+ " WHERE ATP.ID_ALERTA_TIERRAS = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SBD_BNG_ALERTA_T_PREDIO (ID_ALERTA_TIERRAS,ID_CIRCULO,ID_MATRICULA,ESTADO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_DELETE. */
	private static final String cs_DELETE = "DELETE FROM SBD_BNG_ALERTA_T_PREDIO WHERE ID_ALERTA_TIERRAS = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_DELETE_BY_ID_ALERTA_TIERRAS_. */
	private static final String cs_DELETE_BY_ID_ALERTA_TIERRAS = "DELETE FROM SBD_BNG_ALERTA_T_PREDIO WHERE ID_ALERTA_TIERRAS = ?";

	/**
	 * Find by id.
	 *
	 * @param aatp_alertaTPredio de aatp alerta T predio
	 * @return el valor de alerta T predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AlertaTPredio findById(AlertaTPredio aatp_alertaTPredio)
	    throws B2BException
	{
		return (aatp_alertaTPredio != null)
		? findById(
		    aatp_alertaTPredio.getIdAlertaTierras(), aatp_alertaTPredio.getIdCirculo(),
		    aatp_alertaTPredio.getIdMatricula()
		) : null;
	}

	/**
	 * Find by id.
	 *
	 * @param al_idAlertaTierras de al id alerta tierras
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @return el valor de alerta T predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AlertaTPredio findById(long al_idAlertaTierras, String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		AlertaTPredio latp_alertaTPredio;

		latp_alertaTPredio = null;

		if(StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setLong(li_contador++, al_idAlertaTierras);
				lps_ps.setString(li_contador++, as_idCirculo);
				lps_ps.setLong(li_contador++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					latp_alertaTPredio = getObjetFromResultSet(lrs_rs);
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

		return latp_alertaTPredio;
	}

	/**
	 * Find all by id alerta tierras.
	 *
	 * @param al_idAlertaTierras de al id alerta tierras
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AlertaTPredio> findAllByIdAlertaTierras(long al_idAlertaTierras)
	    throws B2BException
	{
		return findAllByIdAlertaTierras(al_idAlertaTierras, null);
	}

	/**
	 * Find all by id alerta tierras.
	 *
	 * @param al_idAlertaTierras de al id alerta tierras
	 * @param as_idCirculo de as id circulo
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AlertaTPredio> findAllByIdAlertaTierras(long al_idAlertaTierras, String as_idCirculo)
	    throws B2BException
	{
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;
		Collection<AlertaTPredio> lcatp_alertaTPredio;

		lps_ps                  = null;
		lrs_rs                  = null;
		lcatp_alertaTPredio     = new LinkedList<AlertaTPredio>();

		try
		{
			StringBuilder lsb_sb;
			int           li_contador;

			lsb_sb          = new StringBuilder(cs_FIND_ALL_BY_ID_ALERTA_TIERRAS);
			li_contador     = 1;

			if(StringUtils.isValidString(as_idCirculo))
				lsb_sb.append(" AND ATP.ID_CIRCULO = ?");

			lsb_sb.append(" ORDER BY ATP.ID_MATRICULA ASC");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			lps_ps.setLong(li_contador++, al_idAlertaTierras);

			if(StringUtils.isValidString(as_idCirculo))
				lps_ps.setString(li_contador++, as_idCirculo);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcatp_alertaTPredio.add(getObjectWithCirculoPredial(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllByIdAlertaTierras", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcatp_alertaTPredio.isEmpty())
			lcatp_alertaTPredio = null;

		return lcatp_alertaTPredio;
	}

	/**
	 * Insert.
	 *
	 * @param aatp_parametro de aatp parametro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(AlertaTPredio aatp_parametro)
	    throws B2BException
	{
		if(aatp_parametro != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setLong(li_column++, aatp_parametro.getIdAlertaTierras());
				lps_ps.setString(li_column++, aatp_parametro.getIdCirculo());
				lps_ps.setLong(li_column++, aatp_parametro.getIdMatricula());
				lps_ps.setString(li_column++, aatp_parametro.getEstado());
				lps_ps.setString(li_column++, aatp_parametro.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aatp_parametro.getIpCreacion());

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
	 * Delete.
	 *
	 * @param latp_alertaTPredio de latp alerta T predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void delete(AlertaTPredio latp_alertaTPredio)
	    throws B2BException
	{
		if(latp_alertaTPredio != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setLong(li_column++, latp_alertaTPredio.getIdAlertaTierras());
				lps_ps.setString(li_column++, latp_alertaTPredio.getIdCirculo());
				lps_ps.setLong(li_column++, latp_alertaTPredio.getIdMatricula());

				lps_ps.executeUpdate();
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
	 * Delete by id alertas tierras.
	 *
	 * @param al_idAlertaTierras de al id alerta tierras
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void deleteByIdAlertaTierras(long al_idAlertaTierras)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		try
		{
			int li_column;

			li_column     = 1;
			lps_ps        = getConnection().prepareStatement(cs_DELETE_BY_ID_ALERTA_TIERRAS);

			lps_ps.setLong(li_column++, al_idAlertaTierras);

			lps_ps.executeUpdate();
		}
		catch(SQLException lse_e)
		{
			logError(this, "deleteByIdAlertasTierras", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
		}
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private AlertaTPredio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AlertaTPredio lat_alertaTPredio;

		lat_alertaTPredio = new AlertaTPredio();

		lat_alertaTPredio.setIdAlertaTierras(ars_rs.getLong("ID_ALERTA_TIERRAS"));
		lat_alertaTPredio.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lat_alertaTPredio.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lat_alertaTPredio.setEstado(ars_rs.getString("ESTADO"));

		fillAuditoria(ars_rs, lat_alertaTPredio);

		return lat_alertaTPredio;
	}

	/**
	 * Retorna Objeto o variable de valor object with circulo predial.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de object with circulo predial
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private AlertaTPredio getObjectWithCirculoPredial(ResultSet ars_rs)
	    throws SQLException
	{
		AlertaTPredio lat_alertaTPredio;

		lat_alertaTPredio = getObjetFromResultSet(ars_rs);

		lat_alertaTPredio.setNombreCirculo(ars_rs.getString("NOMBRE_CIRCULO"));
		lat_alertaTPredio.setNumeroPredial(ars_rs.getString("NUMERO_PREDIAL"));

		return lat_alertaTPredio;
	}
}
