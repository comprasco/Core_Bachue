package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.bng.ProcAntSistemaAT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades ProcAntSistemaATDAO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 3/04/2020
 */
public class ProcAntSistemaATDAO extends AuditoriaDao
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_BNG_PROC_ANT_SISTEMA_AT (ID_ALERTA_TIERRA, ID_ANT_SISTEMA, ID_CIRCULO, ID_TURNO, ESTADO_PROCESO, ID_MATRICULA, ID_PAIS, ID_DEPARTAMENTO, ID_MUNICIPIO, TIPO_PREDIO, NUMERO_LIBRO, NUMERO_TOMO, TIPO_PARTIDA, NUMERO_PARTIDA, NUMERO_FOLIO, ANIO, NOMBRE_PREDIO, NUMERO_PREDIO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_DELETE. */
	private static final String cs_DELETE = "DELETE FROM SDB_BNG_PROC_ANT_SISTEMA_AT WHERE ID_ALERTA_TIERRA = ? AND ID_ANT_SISTEMA = ?";

	/** Constante cs_DELETE_BY_ID_ALERTA_TIERRAS. */
	private static final String cs_DELETE_BY_ID_ALERTA_TIERRAS = "DELETE FROM SDB_BNG_PROC_ANT_SISTEMA_AT WHERE ID_ALERTA_TIERRA = ?";

	/** Constante cs_FIND_ALL_BY_ID_ALERTA_TIERRA. */
	private static final String cs_FIND_ALL_BY_ID_ALERTA_TIERRA = "SELECT PROCAT.*, PA.NOMBRE AS NOM_PAIS, MUN.NOMBRE AS NOM_MUNICIPIO, DTO.NOMBRE AS NOM_DEPARTAMENTO, CR.NOMBRE AS NOM_CIRCULO_REGISTRAL FROM SDB_BNG_PROC_ANT_SISTEMA_AT PROCAT INNER JOIN SDB_PGN_DEPARTAMENTO DTO ON PROCAT.ID_DEPARTAMENTO = DTO.ID_DEPARTAMENTO INNER JOIN SDB_PGN_PAIS PA ON PROCAT.ID_PAIS = PA.ID_PAIS INNER JOIN SDB_PGN_MUNICIPIO MUN ON PROCAT.ID_MUNICIPIO = MUN.ID_MUNICIPIO AND PROCAT.ID_PAIS = MUN.ID_PAIS AND PROCAT.ID_DEPARTAMENTO = MUN.ID_DEPARTAMENTO INNER JOIN SDB_PGN_CIRCULO_REGISTRAL CR ON PROCAT.ID_CIRCULO = CR.ID_CIRCULO WHERE PROCAT.ID_ALERTA_TIERRA = ?";

	/** Constante cs_FIND_LAST_BY_ID_ALERTA_TIERRA. */
	private static final String cs_FIND_LAST_BY_ID_ALERTA_TIERRA = "SELECT * FROM SDB_BNG_PROC_ANT_SISTEMA_AT WHERE ID_ALERTA_TIERRA = ? ORDER BY ID_ANT_SISTEMA DESC FETCH FIRST 1 ROW ONLY";

	/**
	 * Delete.
	 *
	 * @param apasat_parametro de apasat parametro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void delete(ProcAntSistemaAT apasat_parametro)
	    throws B2BException
	{
		if(apasat_parametro != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setLong(li_column++, apasat_parametro.getIdAlertaTierra());
				lps_ps.setLong(li_column++, apasat_parametro.getIdAntSistema());

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
	 * Delete by id alerta tierras.
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
	 * Find all by id alerta tierra.
	 *
	 * @param al_idAlertaTierra de al id alerta tierra
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ProcAntSistemaAT> findAllByIdAlertaTierra(long al_idAlertaTierra)
	    throws B2BException
	{
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;
		Collection<ProcAntSistemaAT> lcpat_return;

		lps_ps           = null;
		lrs_rs           = null;
		lcpat_return     = new ArrayList<ProcAntSistemaAT>();

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_ID_ALERTA_TIERRA);

			lps_ps.setLong(li_column++, al_idAlertaTierra);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpat_return.add(getObjectNombres(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllByIdAlertaTierra", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcpat_return.isEmpty())
			lcpat_return = null;

		return lcpat_return;
	}

	/**
	 * Find last by id alerta tierra.
	 *
	 * @param al_idAlertaTierra de id alerta tierra
	 * @return el valor de proc ant sistema AT
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ProcAntSistemaAT findLastByIdAlertaTierra(long al_idAlertaTierra)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		ProcAntSistemaAT  lpasat_return;

		lps_ps            = null;
		lrs_rs            = null;
		lpasat_return     = null;

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_LAST_BY_ID_ALERTA_TIERRA);

			lps_ps.setLong(li_column++, al_idAlertaTierra);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lpasat_return = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllByIdAlertaTierra", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lpasat_return;
	}

	/**
	 * Insert.
	 *
	 * @param apasat_parametro de apasat parametro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(ProcAntSistemaAT apasat_parametro)
	    throws B2BException
	{
		if(apasat_parametro != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setLong(li_column++, apasat_parametro.getIdAlertaTierra());
				lps_ps.setLong(li_column++, apasat_parametro.getIdAntSistema());
				lps_ps.setString(li_column++, apasat_parametro.getIdCirculo());
				lps_ps.setString(li_column++, apasat_parametro.getIdTurno());
				lps_ps.setString(li_column++, apasat_parametro.getEstadoProceso());
				lps_ps.setLong(li_column++, apasat_parametro.getIdMatricula());
				lps_ps.setLong(li_column++, apasat_parametro.getIdPais());
				lps_ps.setString(li_column++, apasat_parametro.getIdDepartamento());
				lps_ps.setString(li_column++, apasat_parametro.getIdMunicipio());
				lps_ps.setString(li_column++, apasat_parametro.getTipoPredio());
				lps_ps.setLong(li_column++, apasat_parametro.getNumeroLibro());
				lps_ps.setLong(li_column++, apasat_parametro.getNumeroTomo());
				lps_ps.setString(li_column++, apasat_parametro.getTipoPartida());
				lps_ps.setLong(li_column++, apasat_parametro.getNumeroPartida());
				lps_ps.setLong(li_column++, apasat_parametro.getNumeroFolio());
				lps_ps.setLong(li_column++, apasat_parametro.getAnio());
				lps_ps.setString(li_column++, apasat_parametro.getNombrePredio());
				lps_ps.setLong(li_column++, apasat_parametro.getNumeroPredio());
				lps_ps.setString(li_column++, apasat_parametro.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, apasat_parametro.getIpCreacion());

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

	private ProcAntSistemaAT getObjectNombres(ResultSet ars_rs)
	    throws SQLException
	{
		ProcAntSistemaAT lpasat_procAntSistemaAT;

		lpasat_procAntSistemaAT = getObjetFromResultSet(ars_rs);

		lpasat_procAntSistemaAT.setNomPais(ars_rs.getString("NOM_PAIS"));
		lpasat_procAntSistemaAT.setNomMunicipio(ars_rs.getString("NOM_MUNICIPIO"));
		lpasat_procAntSistemaAT.setNomDepartamento(ars_rs.getString("NOM_DEPARTAMENTO"));
		lpasat_procAntSistemaAT.setNomCirculoRegistral(ars_rs.getString("NOM_CIRCULO_REGISTRAL"));

		return lpasat_procAntSistemaAT;
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private ProcAntSistemaAT getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ProcAntSistemaAT lpasat_procAntSistemaAT;

		lpasat_procAntSistemaAT = new ProcAntSistemaAT();

		lpasat_procAntSistemaAT.setEstadoProceso(ars_rs.getString("ESTADO_PROCESO"));
		lpasat_procAntSistemaAT.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lpasat_procAntSistemaAT.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		lpasat_procAntSistemaAT.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		lpasat_procAntSistemaAT.setIdTurno(ars_rs.getString("ID_TURNO"));
		lpasat_procAntSistemaAT.setNombrePredio(ars_rs.getString("NOMBRE_PREDIO"));
		lpasat_procAntSistemaAT.setTipoPartida(ars_rs.getString("TIPO_PARTIDA"));
		lpasat_procAntSistemaAT.setTipoPredio(ars_rs.getString("TIPO_PREDIO"));
		lpasat_procAntSistemaAT.setAnio(ars_rs.getLong("ANIO"));
		lpasat_procAntSistemaAT.setIdAlertaTierra(ars_rs.getLong("ID_ALERTA_TIERRA"));
		lpasat_procAntSistemaAT.setIdAntSistema(ars_rs.getLong("ID_ANT_SISTEMA"));
		lpasat_procAntSistemaAT.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lpasat_procAntSistemaAT.setIdPais(ars_rs.getLong("ID_PAIS"));
		lpasat_procAntSistemaAT.setNumeroFolio(ars_rs.getLong("NUMERO_FOLIO"));
		lpasat_procAntSistemaAT.setNumeroLibro(ars_rs.getLong("NUMERO_LIBRO"));
		lpasat_procAntSistemaAT.setNumeroPartida(ars_rs.getLong("NUMERO_PARTIDA"));
		lpasat_procAntSistemaAT.setNumeroPredio(ars_rs.getLong("NUMERO_PREDIO"));
		lpasat_procAntSistemaAT.setNumeroTomo(ars_rs.getLong("NUMERO_TOMO"));

		fillAuditoria(ars_rs, lpasat_procAntSistemaAT);

		return lpasat_procAntSistemaAT;
	}
}
