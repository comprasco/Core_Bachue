package com.bachue.snr.prosnr02.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr02.model.acc.SubProcesoTrabajo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Controla las acciones CRUD sobre la tabla SDB_ACC_SUBPROCESO_TRABAJO.
 *
 * @author jpatino
 */
public class SubProcesoTrabajoDAO extends AuditoriaDao
{
	/** Constante cs_EXISTE. */
	private static final String cs_EXISTE = "SELECT 1 FROM SDB_ACC_SUBPROCESO_TRABAJO WHERE ID_PROCESO=? AND ID_SUBPROCESO=?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_SUBPROCESO_TRABAJO WHERE ID_PROCESO=? AND ID_SUBPROCESO=?";

	/** Constante cs_FIND_BY_PROCESO. */
	private static final String cs_FIND_BY_PROCESO = "SELECT * FROM SDB_ACC_SUBPROCESO_TRABAJO WHERE ID_PROCESO=? ORDER BY ID_SUBPROCESO ASC";

	/** Constante cs_FIND_ALL_APROBACION. */
	private static final String cs_FIND_ALL_APROBACION = "SELECT DISTINCT SPT.* FROM SDB_ACC_SUBPROCESO_TRABAJO SPT, SDB_ACC_SUBPROCESO_VERSION_TRABAJO SPVT WHERE SPVT.ESTADO_FLUJO = 'APROBADOR' AND SPVT.ID_PROCESO = ?  AND SPT.ID_PROCESO = SPVT.ID_PROCESO AND SPVT.ID_SUBPROCESO = SPT.ID_SUBPROCESO ORDER BY SPT.ID_SUBPROCESO ASC";

	/**
	 * Indica si existe un registro de proceso.
	 *
	 * @param as_idProceso Id del proceso a validar
	 * @param as_idSubProceso Id del subproceso a validar
	 * @return true si el subproceso existe
	 * @throws B2BException de b 2 B exception
	 */
	public boolean existe(String as_idProceso, String as_idSubProceso)
	    throws B2BException
	{
		boolean lb_existe;

		lb_existe = false;

		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubProceso))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_EXISTE);

				lps_ps.setString(1, as_idProceso);
				lps_ps.setString(2, as_idSubProceso);

				lrs_rs        = lps_ps.executeQuery();
				lb_existe     = lrs_rs.next();
			}
			catch(SQLException lse_e)
			{
				logError(this, "existe", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lb_existe;
	}

	/**
	 * Consulta todos los subprocesos y los filtra por un id proceso previamente definido
	 *     cuyas versiones esten en estado APROBADOR.
	 *
	 * @param as_idProceso String del id_proceso para aplicar al filtro
	 * @return Colección de Subprocesos resultante de la consulta
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<SubProcesoTrabajo> findAllAprobacion(String as_idProceso)
	    throws B2BException
	{
		Collection<SubProcesoTrabajo> lcspt_subProcesos;

		lcspt_subProcesos = null;

		if(StringUtils.isValidString(as_idProceso))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_APROBACION);

				lps_ps.setString(1, as_idProceso);

				lrs_rs                = lps_ps.executeQuery();
				lcspt_subProcesos     = new ArrayList<SubProcesoTrabajo>();

				while(lrs_rs.next())
					lcspt_subProcesos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllAprobacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcspt_subProcesos;
	}

	/**
	 * Consulta en la base de datos un subproceso, filtrando por un id de
	 * subproceso definido con anterioridad.
	 *
	 * @param as_idProceso de as id proceso
	 * @param as_idSubProceso de as id sub proceso
	 * @return Subproceso resultante de la ejecución de la consulta
	 * @throws B2BException de b 2 B exception
	 */
	public SubProcesoTrabajo findById(String as_idProceso, String as_idSubProceso)
	    throws B2BException
	{
		SubProcesoTrabajo lspt_subProceso;

		lspt_subProceso = null;

		if(StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubProceso))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idProceso);
				lps_ps.setString(2, as_idSubProceso);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lspt_subProceso = getObjetFromResultSet(lrs_rs);
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

		return lspt_subProceso;
	}

	/**
	 * Consulta todos los subprocesos y los filtra por un id proceso previamente definido.
	 *
	 * @param as_idProceso String del id_proceso para aplicar al filtro
	 * @return Colección de Subprocesos resultante de la consulta
	 * @throws B2BException de b 2 B exception
	 */
	public Collection<SubProcesoTrabajo> findByProceso(String as_idProceso)
	    throws B2BException
	{
		Collection<SubProcesoTrabajo> lcspt_subProcesos;

		lcspt_subProcesos = new ArrayList<SubProcesoTrabajo>();

		if(StringUtils.isValidString(as_idProceso))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_PROCESO);

				lps_ps.setString(1, as_idProceso);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcspt_subProcesos.add(getObjetFromResultSet(lrs_rs));
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

		if(lcspt_subProcesos.isEmpty())
			lcspt_subProcesos = null;

		return lcspt_subProcesos;
	}

	/**
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param ars_rs contenedor del resultado de a consulta
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws SQLException de SQL exception
	 */
	private SubProcesoTrabajo getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		SubProcesoTrabajo lspt_subProceso;

		lspt_subProceso = new SubProcesoTrabajo();

		lspt_subProceso.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lspt_subProceso.setIdSubproceso(ars_rs.getString("ID_SUBPROCESO"));
		lspt_subProceso.setNombre(ars_rs.getString("NOMBRE"));

		fillAuditoria(ars_rs, lspt_subProceso);

		return lspt_subProceso;
	}
}
