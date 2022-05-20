package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.NotaDevolutiva;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_NOTA_DEVOLUTIVA
 *
 * @author Julian Vaca
 */
public class NotaDevolutivaDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_SAVE_TIPOS_CAUSALES. */
	private static final String cs_SAVE_TIPOS_CAUSALES = " INSERT INTO SDB_ACC_NOTA_DEVOLUTIVA (ID_NOTA_DEVOLUTIVA,ID_TURNO,ID_TURNO_HISTORIA,ID_CAUSAL,ID_USUARIO_CREACION ,FECHA_CREACION ) "
		+ "		VALUES (?,?,?,?,?,SYSTIMESTAMP)";

	/** Constante cs_FIND_SECUENCE_NOTA_DEVOLUTIVA. */
	private static final String cs_FIND_SECUENCE_NOTA_DEVOLUTIVA = "  SELECT SEC_ACC_NOTA_DEVOLUTIVA_ID_NOTA_DEVOLUTIVA.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE_NOTA_DEVOLUTIVA. */
	private static final String cs_UPDATE_NOTA_DEVOLUTIVA = "  UPDATE SDB_ACC_NOTA_DEVOLUTIVA SET ID_TURNO_HISTORIA = ?, ID_USUARIO_MODIFICACION = ?"
		+ ", FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ?, ACTIVO = ?, ID_TURNO = ? WHERE ID_NOTA_DEVOLUTIVA = ? ";

	/** Constante cs_FIND_TAGS_NOTA_DEVOLUTIVA. */
	private static final String cs_FIND_TAGS_NOTA_DEVOLUTIVA = "  SELECT CR.NOMBRE CIRCULO ,SBG.FECHA_DOCUMENTO ,SBG.NUMERO DOC_INT ,STD.NOMBRE TIPO_DOC,POO.NOMBRE OFICINA_ORIGEN, SAS.NIR "
		+ " FROM SDB_ACC_TURNO ACT INNER JOIN SDB_PGN_CIRCULO_REGISTRAL CR ON ACT.ID_CIRCULO = CR.ID_CIRCULO "
		+ " INNER JOIN SDB_ACC_SOLICITUD SAS ON ACT.ID_SOLICITUD = SAS.ID_SOLICITUD "
		+ " LEFT JOIN SDB_BGN_DOCUMENTO SBG ON SAS.ID_DOCUMENTO = SBG.ID_DOCUMENTO "
		+ " LEFT JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO STD ON STD.ID_TIPO_DOCUMENTO = SBG.ID_TIPO_DOCUMENTO "
		+ " LEFT JOIN SDB_PGN_OFICINA_ORIGEN POO ON POO.ID_OFICINA_ORIGEN = SBG.ID_OFICINA_ORIGEN"
		+ " WHERE ACT.ID_TURNO = ? ";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_NOTA_DEVOLUTIVA "
		+ " WHERE ID_NOTA_DEVOLUTIVA = ?";

	/** Constante cs_FIND_BY_TURNO. */
	private static final String cs_FIND_BY_TURNO = "SELECT * FROM SDB_ACC_NOTA_DEVOLUTIVA " + " WHERE ID_TURNO = ?";

	/** Constante cs_FIND_BY_TURNO_HISTORIA. */
	private static final String cs_FIND_BY_TURNO_HISTORIA = "SELECT * FROM SDB_ACC_NOTA_DEVOLUTIVA WHERE ID_TURNO_HISTORIA = ?";

	/** Constante cs_FIND_CAUSALES_BY_TURNO. */
	private static final String cs_FIND_CAUSALES_BY_TURNO = " SELECT ANT.ID_CAUSAL, CASE WHEN ANT.ID_CAUSAL IS NOT NULL THEN 'S' ELSE 'N' END AS SELECCIONADO, TC.DESCRIPCION "
		+ " FROM SDB_ACC_NOTA_DEVOLUTIVA  ANT INNER  JOIN SDB_ACC_TIPO_CAUSAL TC ON TC.ID_CAUSAL = ANT.ID_CAUSAL   WHERE ID_TURNO = ?";

	/**
	 * Instancia un nuevo objeto nota devolutiva DAO.
	 */
	public NotaDevolutivaDAO()
	{
	}

	/**
	 * Retorna el valor del objeto de TurnoHistoria.
	 *
	 * @param as_Idturno correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de TurnoHistoria
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TurnoHistoria
	 */
	public TurnoHistoria dataNotaDevolutiva(String as_Idturno)
	    throws B2BException
	{
		TurnoHistoria     lth_datos;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lth_datos     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_TAGS_NOTA_DEVOLUTIVA);

			lps_ps.setString(li_contador++, as_Idturno);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lth_datos = dataNotaDevolutiva(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "dataNotaDevolutiva", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lth_datos;
	}

	/**
	 * Retorna el valor del objeto de NotaDevolutiva.
	 *
	 * @param ath_parametros correspondiente al valor del tipo de objeto NotaDevolutiva
	 * @return devuelve el valor de NotaDevolutiva
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see NotaDevolutiva
	 */
	public NotaDevolutiva findById(NotaDevolutiva ath_parametros)
	    throws B2BException
	{
		NotaDevolutiva    lca_data;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		lca_data     = new NotaDevolutiva();

		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_contador++, ath_parametros.getIdNotaDevolutiva());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lca_data = getNotaDevolutiva(lrs_rs);
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

		return lca_data;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param and_parametros correspondiente al valor del tipo de objeto NotaDevolutiva
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<NotaDevolutiva> findByTurno(NotaDevolutiva and_parametros)
	    throws B2BException
	{
		return (and_parametros != null) ? findByTurno(and_parametros.getIdTurno()) : null;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_parametros correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<NotaDevolutiva> findByTurno(String as_parametros)
	    throws B2BException
	{
		Collection<NotaDevolutiva> lcnd_data;

		lcnd_data = new ArrayList<NotaDevolutiva>();

		if(StringUtils.isValidString(as_parametros))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO);

				lps_ps.setString(li_contador++, as_parametros);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcnd_data.add(getNotaDevolutiva(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcnd_data))
			lcnd_data = null;

		return lcnd_data;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_parametros correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<NotaDevolutiva> findByTurnoHistoria(String as_parametros)
	    throws B2BException
	{
		Collection<NotaDevolutiva> lcnd_data;

		lcnd_data = new ArrayList<NotaDevolutiva>();

		if(StringUtils.isValidString(as_parametros))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO_HISTORIA);

				lps_ps.setString(li_contador++, as_parametros);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcnd_data.add(getNotaDevolutiva(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoHistoria", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcnd_data))
			lcnd_data = null;

		return lcnd_data;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_idTurno correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoCausal
	 */
	public Collection<TipoCausal> findCausalesByTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<TipoCausal> lctc_data;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;
		lctc_data     = new ArrayList<TipoCausal>();

		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_CAUSALES_BY_TURNO);

				lps_ps.setString(li_contador++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lctc_data.add(getCausales(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findCausalesByTurno", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lctc_data))
			lctc_data = null;

		return lctc_data;
	}

	/**
	 * Retorna el valor del objeto de int.
	 *
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int findSecuenceNotaDevolutiva()
	    throws B2BException
	{
		int               li_secuencia;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_secuencia     = 0;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCE_NOTA_DEVOLUTIVA);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_secuencia = lrs_rs.getInt(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSecuence", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_secuencia;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param aotc_otc correspondiente al valor del tipo de objeto TipoCausal
	 * @param ath_turnoHistoria correspondiente al valor del tipo de objeto TurnoHistoria
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String saveTipoCausal(TipoCausal aotc_otc, TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		String            lp_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_column;
		li_column     = 1;

		lp_return     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_SAVE_TIPOS_CAUSALES);

			lps_ps.setString(li_column++, aotc_otc.getSecuence());
			lps_ps.setString(li_column++, ath_turnoHistoria.getIdTurno());
			lps_ps.setLong(li_column++, NumericUtils.getLong(ath_turnoHistoria.getIdTurnoHistoria()));
			lps_ps.setString(li_column++, aotc_otc.getIdTipoCausal());
			lps_ps.setString(li_column++, aotc_otc.getUserId());

			lps_ps.executeUpdate();
		}
		catch(SQLException lse_e)
		{
			logError(this, "saveTipoCausal", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lp_return;
	}

	/**
	 * Actualiza nota devolutiva.
	 *
	 * @param and_parametros correspondiente al valor del tipo de objeto NotaDevolutiva
	 * @param ab_aprobador correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateNotaDevolutiva(NotaDevolutiva and_parametros, boolean ab_aprobador)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		lps_ps = null;

		int li_column;

		if(and_parametros != null)
		{
			try
			{
				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_NOTA_DEVOLUTIVA);

				lps_ps.setLong(li_column++, NumericUtils.getLong(and_parametros.getIdTurnoHistoria()));
				lps_ps.setString(li_column++, and_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, and_parametros.getIpModificacion());

				if(ab_aprobador)
					lps_ps.setString(li_column++, EstadoCommon.N);
				else
					lps_ps.setString(li_column++, EstadoCommon.S);

				lps_ps.setString(li_column++, and_parametros.getIdTurno());
				lps_ps.setString(li_column++, and_parametros.getIdNotaDevolutiva());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "updateEstadoNotaDevolutiva", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor de causales.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de causales
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoCausal getCausales(ResultSet ars_rs)
	    throws SQLException
	{
		TipoCausal ltc_datos;

		ltc_datos = new TipoCausal();

		ltc_datos.setIdTipoCausal(ars_rs.getString("ID_CAUSAL"));
		ltc_datos.setSeleccionado(BooleanUtils.getBooleanValue(ars_rs.getString("SELECCIONADO")));
		ltc_datos.setNombre(ars_rs.getString("DESCRIPCION"));

		return ltc_datos;
	}

	/**
	 * Retorna el valor de nota devolutiva.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de nota devolutiva
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private NotaDevolutiva getNotaDevolutiva(ResultSet ars_rs)
	    throws SQLException
	{
		NotaDevolutiva lth_datos;

		lth_datos = new NotaDevolutiva();

		lth_datos.setIdNotaDevolutiva(ars_rs.getString("ID_NOTA_DEVOLUTIVA"));
		lth_datos.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));
		lth_datos.setIdTurno(ars_rs.getString("ID_TURNO"));
		lth_datos.setIdCausal(ars_rs.getString("ID_CAUSAL"));
		lth_datos.setFechaRegistro(ars_rs.getDate("FECHA_REGISTRO"));
		lth_datos.setVersion(getLong(ars_rs, "VERSION"));
		lth_datos.setActivo(ars_rs.getString("ACTIVO"));
		lth_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lth_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lth_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lth_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lth_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lth_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lth_datos;
	}

	/**
	 * Retorna el valor del objeto de TurnoHistoria.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return devuelve el valor de TurnoHistoria
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TurnoHistoria
	 */
	private TurnoHistoria dataNotaDevolutiva(ResultSet ars_rs)
	    throws SQLException
	{
		TurnoHistoria lth_datos;

		lth_datos = new TurnoHistoria();

		lth_datos.setNombreCirculo(ars_rs.getString("CIRCULO"));
		lth_datos.setFechaDocumento(ars_rs.getDate("FECHA_DOCUMENTO"));
		lth_datos.setNumeroDoc(NumericUtils.getLongWrapper(ars_rs.getString("DOC_INT")));
		lth_datos.setNombreTipoDoc(ars_rs.getString("TIPO_DOC"));
		lth_datos.setOficinaOrigen(ars_rs.getString("OFICINA_ORIGEN"));
		lth_datos.setNir(ars_rs.getString("NIR"));

		return lth_datos;
	}
}
