package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr21.model.pgn.ConInconsistencia;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 * Clase que contiene todos las consultas de la tabla SDB_CON_INCONSISTENCIA.
 *
 * @author Gabriel Arias
 */
public class ConInconsistenciaDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_CON_INCONSISTENCIA";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_CON_INCONSISTENCIA WHERE ID_CUENTA=? AND ID_PERIODO_CORTE=?";

	/** Constante cs_FIND_BY_TIPO. */
	private static final String cs_FIND_BY_TIPO = "SELECT * FROM SDB_CON_INCONSISTENCIA I INNER JOIN SDB_PGN_INC_AVI_ALE_MEN_NOT B ON I.CODIGO_INCONSISTENCIA = B.ID_INC WHERE I.ESTADO = 'A'";

	/** Constante cs_FIND_BY_TIPO_AND_DATE. */
	private static final String cs_FIND_BY_TIPO_AND_DATE = "SELECT * FROM SDB_CON_INCONSISTENCIA I INNER JOIN SDB_PGN_INC_AVI_ALE_MEN_NOT B ON I.CODIGO_INCONSISTENCIA = B.ID_INC INNER JOIN SDB_PGN_PERIODO_CORTE PC ON I.ID_PERIODO_CORTE = PC.ID_PERIODO_CORTE WHERE I.ESTADO = 'A'";

	/** Constante cs_FIND_BANCO_AND_CUENTA_BY_ID. */
	private static final String cs_FIND_BANCO_AND_CUENTA_BY_ID = "SELECT ER.ID_CUENTA, ER.NUMERO_CUENTA, ER.ID_ENTIDAD_RECAUDADORA, B.NOMBRE_ENTIDAD_RECAUDADORA, PC.PERIODO, PC.DIA_CORTE, I.FECHA_CREACION, I.ID_REGISTRO, I.COLUMNA FROM SDB_CON_INCONSISTENCIA I INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA ER ON I.ID_CUENTA = ER.ID_CUENTA INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION B ON ER.ID_ENTIDAD_RECAUDADORA = B.ID_ENTIDAD_RECAUDADORA\r\n"
		+ "INNER JOIN SDB_PGN_PERIODO_CORTE PC ON I.ID_PERIODO_CORTE = PC.ID_PERIODO_CORTE WHERE I.ID_INCONSISTENCIA =? AND I.ESTADO = 'A' ";

	/** Constante cs_FIND_BY_CUENTA. */
	private static final String cs_FIND_BY_CUENTA = "SELECT * FROM SDB_CON_INCONSISTENCIA B INNER JOIN SDB_PGN_INC_AVI_ALE_MEN_NOT C ON C.ID_INC = B.CODIGO_INCONSISTENCIA WHERE B.ESTADO = 'A' AND C.TIPO IN ('INFORMATIVO', 'ADVERTENCIA', 'EXITOSO') AND B.ID_CUENTA =? ORDER BY B.FECHA_CREACION ASC";

	/** Constante cs_FIND_BY_ID_INCONSISTENCIA. */
	private static final String cs_FIND_BY_ID_INCONSISTENCIA = "SELECT * FROM SDB_CON_INCONSISTENCIA B INNER JOIN SDB_PGN_INC_AVI_ALE_MEN_NOT C ON C.ID_INC = B.CODIGO_INCONSISTENCIA WHERE B.ESTADO = 'A' AND B.ID_INCONSISTENCIA =? ORDER BY B.FECHA_CREACION ASC";

	/** Constante cs_FIND_DINAMYC. */
	private static final String cs_FIND_DINAMYC = "SELECT * FROM SDB_CON_INCONSISTENCIA WHERE CODIGO_INCONSISTENCIA=? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_CON_INCONSISTENCIA (ID_INCONSISTENCIA,ID_ARCHIVO,ID_REGISTRO,ID_CUENTA,ID_PERIODO_CORTE,CODIGO_INCONSISTENCIA,COLUMNA,ESTADO,FECHA_SOLUCION,VALOR_SOLUCION,USUARIO_SOLUCION,ID_BITACORA,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) "
		+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_CON_INCONSISTENCIA SET ID_ARCHIVO=?,ID_REGISTRO=?,ID_CUENTA=?,ID_PERIODO_CORTE=?,CODIGO_INCONSISTENCIA=?,COLUMNA=?,ESTADO=?,FECHA_SOLUCION=?,VALOR_SOLUCION=?,USUARIO_SOLUCION=?,ID_BITACORA=?,ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=? WHERE ID_INCONSISTENCIA=?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_CON_INCONSISTENCIA_ID_INCONSISTENCIA.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_INFORMACION. */
	private static final String cs_FIND_INFORMACION = "SELECT SCI.ID_INCONSISTENCIA, SCI.ID_CUENTA, SCI.ID_PERIODO_CORTE, SPPC.DIA_CORTE || ';' || SCA.TIPO_ARCHIVO || ';' || SCA.NOMBRE_ARCHIVO || ';' || NVL(SCI.ID_REGISTRO,'') || ';' || NVL(SCI.COLUMNA,'') || ';' || SDATA.TIPO || ';' || '\"' || SDATA.MENSAJE || '\"' AS INFORMACION FROM SDB_CON_INCONSISTENCIA SCI INNER JOIN SDB_PGN_INC_AVI_ALE_MEN_NOT SDATA ON SDATA.ID_INC = SCI.CODIGO_INCONSISTENCIA INNER JOIN SDB_PGN_PERIODO_CORTE SPPC ON SPPC.ID_PERIODO_CORTE = SCI.ID_PERIODO_CORTE LEFT JOIN SDB_CON_ARCHIVO SCA ON SCA.ID_ARCHIVO = SCI.ID_ARCHIVO WHERE ";

	public boolean detalle()
	{
		boolean ab_detalle = false;

		return ab_detalle;
	}

	/**
	 * Find all.
	 *
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<ConInconsistencia> findAll()
	    throws B2BException
	{
		Collection<ConInconsistencia> lcci_return;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcci_return     = new ArrayList<ConInconsistencia>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcci_return.add(getObjetFromResultSet(lrs_rs, false));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		if(!CollectionUtils.isValidCollection(lcci_return))
			lcci_return = null;

		return lcci_return;
	}

	/**
	 * Find banco cuenta by id.
	 *
	 * @param as_idInconsistencia the as id inconsistencia
	 * @return the con inconsistencia
	 * @throws B2BException the b 2 B exception
	 */
	public ConInconsistencia findBancoCuentaById(String as_idInconsistencia)
	    throws B2BException
	{
		ConInconsistencia lcsc_return;

		lcsc_return = new ConInconsistencia();

		if(as_idInconsistencia != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BANCO_AND_CUENTA_BY_ID);

				lps_ps.setString(li_column++, as_idInconsistencia);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcsc_return = getObjetFromResultSetFillTagsObs(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCuenta", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcsc_return;
	}

	/**
	 * Find by cuenta.
	 *
	 * @param aci_param the aci param
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<ConInconsistencia> findByCuenta(String as_idCuenta)
	    throws B2BException
	{
		Collection<ConInconsistencia> lcsc_return;

		lcsc_return = new ArrayList<ConInconsistencia>();

		if(as_idCuenta != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CUENTA);

				lps_ps.setString(1, as_idCuenta);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsc_return.add(getObjetFromResultSet(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCuenta", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcsc_return.isEmpty())
			lcsc_return = null;

		return lcsc_return;
	}

	/**
	 * Find by id.
	 *
	 * @param aci_param the aci param
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<ConInconsistencia> findById(ConInconsistencia aci_param)
	    throws B2BException
	{
		Collection<ConInconsistencia> lcsc_return;

		lcsc_return = new ArrayList<ConInconsistencia>();

		if(aci_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				boolean       lb_archivo;
				int           li_column;
				StringBuilder lsb_query;

				li_column      = 1;
				lsb_query      = new StringBuilder(cs_FIND_BY_ID);
				lb_archivo     = StringUtils.isValidString(aci_param.getIdArchivo());

				if(lb_archivo)
					lsb_query.append(" AND ID_ARCHIVO=?");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, aci_param.getIdCuenta());
				lps_ps.setString(li_column++, aci_param.getIdPeriodoCorte());

				if(lb_archivo)
					lps_ps.setString(li_column++, aci_param.getIdArchivo());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsc_return.add(getObjetFromResultSet(lrs_rs, false));
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

		if(lcsc_return.isEmpty())
			lcsc_return = null;

		return lcsc_return;
	}

	/**
	 * Find by id inconsistencia.
	 *
	 * @param as_idInconsistencia the as id inconsistencia
	 * @return the con inconsistencia
	 * @throws B2BException the b 2 B exception
	 */
	public ConInconsistencia findByIdInconsistencia(String as_idInconsistencia)
	    throws B2BException
	{
		ConInconsistencia lci_return;

		lci_return = null;

		if(as_idInconsistencia != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_INCONSISTENCIA);

				lps_ps.setString(1, as_idInconsistencia);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lci_return = getObjetFromResultSet(lrs_rs, true);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdInconsistencia", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lci_return;
	}

	/**
	 * Find by tipo.
	 *
	 * @param aci_param the aci param
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<ConInconsistencia> findByTipo(String as_alerta, Date ad_fecha)
	    throws B2BException
	{
		Collection<ConInconsistencia> lcsc_return;

		lcsc_return = new ArrayList<ConInconsistencia>();

		if(as_alerta != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				boolean       lb_fecha;
				int           li_column;
				StringBuilder lsb_sb;

				lb_fecha      = ad_fecha != null;
				li_column     = 1;
				lsb_sb        = new StringBuilder();

				if(lb_fecha)
					lsb_sb.append(cs_FIND_BY_TIPO_AND_DATE);
				else
					lsb_sb.append(cs_FIND_BY_TIPO);

				if(as_alerta.equalsIgnoreCase("TODAS"))
				{
					if(lb_fecha)
						lsb_sb.append(" AND TO_DATE(PC.DIA_CORTE, 'DD/MM/YY') = TO_DATE(?, 'DD/MM/YY') ");

					lsb_sb.append(" ORDER BY I.FECHA_CREACION ASC ");

					lps_ps = getConnection().prepareStatement(lsb_sb.toString());

					if(lb_fecha)
						setDate(lps_ps, ad_fecha, li_column++);
				}
				else
				{
					lsb_sb.append(" AND B.TIPO =? ");

					if(lb_fecha)
						lsb_sb.append(" AND TO_DATE(PC.DIA_CORTE, 'DD/MM/YY') = TO_DATE(?, 'DD/MM/YY') ");

					lsb_sb.append(" ORDER BY I.FECHA_CREACION ASC ");

					lps_ps = getConnection().prepareStatement(lsb_sb.toString());

					lps_ps.setString(li_column++, as_alerta);

					if(lb_fecha)
						setDate(lps_ps, ad_fecha, li_column++);
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsc_return.add(getObjetFromResultSet(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTipo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcsc_return.isEmpty())
			lcsc_return = null;

		return lcsc_return;
	}

	/**
	 * Find dinamyc.
	 *
	 * @param aci_param the aci param
	 * @return the con inconsistencia
	 * @throws B2BException the b 2 B exception
	 */
	public ConInconsistencia findDinamyc(ConInconsistencia aci_param)
	    throws B2BException
	{
		ConInconsistencia lsc_return;

		lsc_return = null;

		if(aci_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_column;
				StringBuilder lsb_sb;
				String        ls_idArchivo;
				Long          ll_idRegistro;
				String        ls_idCuenta;
				String        ls_idPeriodoCorte;
				String        ls_columna;

				li_column             = 1;
				lsb_sb                = new StringBuilder(cs_FIND_DINAMYC);
				ls_idArchivo          = aci_param.getIdArchivo();
				ll_idRegistro         = aci_param.getIdRegistro();
				ls_idCuenta           = aci_param.getIdCuenta();
				ls_idPeriodoCorte     = aci_param.getIdPeriodoCorte();
				ls_columna            = aci_param.getColumna();

				if(StringUtils.isValidString(ls_idArchivo))
					lsb_sb.append(" AND ID_ARCHIVO=? ");

				if(NumericUtils.isValidLong(ll_idRegistro))
					lsb_sb.append(" AND ID_REGISTRO=? ");

				if(StringUtils.isValidString(ls_idCuenta))
					lsb_sb.append(" AND ID_CUENTA=? ");

				if(StringUtils.isValidString(ls_idPeriodoCorte))
					lsb_sb.append(" AND ID_PERIODO_CORTE=? ");

				if(StringUtils.isValidString(ls_columna))
					lsb_sb.append(" AND COLUMNA=? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, aci_param.getCodigoInconsistencia());

				if(StringUtils.isValidString(ls_idArchivo))
					lps_ps.setString(li_column++, ls_idArchivo);

				if(NumericUtils.isValidLong(ll_idRegistro))
					setLong(lps_ps, ll_idRegistro, li_column++);

				if(StringUtils.isValidString(ls_idCuenta))
					lps_ps.setString(li_column++, ls_idCuenta);

				if(StringUtils.isValidString(ls_idPeriodoCorte))
					lps_ps.setString(li_column++, ls_idPeriodoCorte);

				if(StringUtils.isValidString(ls_columna))
					lps_ps.setString(li_column++, ls_columna);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lsc_return = getObjetFromResultSet(lrs_rs, false);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDinamyc", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lsc_return;
	}

	/**
	 * Find informacion by archivo.
	 *
	 * @param as_idArchivo the as id archivo
	 * @return the string
	 * @throws B2BException the b 2 B exception
	 */
	public ConInconsistencia findInformacionByArchivo(String as_idArchivo)
	    throws B2BException
	{
		return findInformacionByArchivo(as_idArchivo, null, null);
	}

	/**
	 * Find informacion by archivo.
	 *
	 * @param as_idArchivo the as id archivo
	 * @param as_idCuenta the as id cuenta
	 * @param as_idPeriodoCorte the as id periodo corte
	 * @return the con inconsistencia
	 * @throws B2BException the b 2 B exception
	 */
	public ConInconsistencia findInformacionByArchivo(
	    String as_idArchivo, String as_idCuenta, String as_idPeriodoCorte
	)
	    throws B2BException
	{
		ConInconsistencia lci_return;

		lci_return = new ConInconsistencia();

		if(
		    StringUtils.isValidString(as_idArchivo)
			    || (StringUtils.isValidString(as_idCuenta) && StringUtils.isValidString(as_idPeriodoCorte))
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				boolean       ab_archivo;
				int           li_count;
				int           li_column;
				StringBuilder lsb_return;
				StringBuilder lsb_query;

				ab_archivo     = StringUtils.isValidString(as_idArchivo);
				li_count       = 0;
				li_column      = 1;
				lsb_return     = new StringBuilder();
				lsb_query      = new StringBuilder(cs_FIND_INFORMACION);

				if(ab_archivo)
					lsb_query.append(" SCI.ID_ARCHIVO = ?");
				else
					lsb_query.append(" SCI.ID_CUENTA = ? AND SCI.ID_PERIODO_CORTE = ? AND SCI.ID_ARCHIVO IS NULL");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				if(ab_archivo)
					lps_ps.setString(li_column++, as_idArchivo);
				else
				{
					lps_ps.setString(li_column++, as_idCuenta);
					lps_ps.setString(li_column++, as_idPeriodoCorte);
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					if(li_count == 0)
					{
						lci_return.setIdInconsistencia(lrs_rs.getString("ID_INCONSISTENCIA"));
						lci_return.setIdCuenta(lrs_rs.getString("ID_CUENTA"));
						lci_return.setIdPeriodoCorte(lrs_rs.getString("ID_PERIODO_CORTE"));
					}

					lsb_return.append(lrs_rs.getString("INFORMACION"));
					lsb_return.append(IdentificadoresCommon.SALTO_LINEA);

					li_count++;
				}

				{
					String ls_data;

					ls_data = lsb_return.toString();

					lci_return.setMensaje(ls_data);
					lci_return.setObservaciones(ls_data);
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findInformacionByArchivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lci_return;
	}

	/**
	 * Insert.
	 *
	 * @param aci_param the apc corte
	 * @throws B2BException the b 2 B exception
	 */
	public ConInconsistencia insert(ConInconsistencia aci_param)
	    throws B2BException
	{
		if(aci_param != null)
		{
			PreparedStatement lps_insercion;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_secuencia;

			lps_insercion     = null;
			lps_secuencia     = null;
			lrs_secuencia     = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();
				lps_insercion     = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);
				lrs_secuencia     = lps_secuencia.executeQuery();

				if(lrs_secuencia.next())
				{
					Object lo_o;

					lo_o = lrs_secuencia.getObject(1);

					if((lo_o != null) && lo_o instanceof BigDecimal)
						aci_param.setIdInconsistencia(lo_o.toString());
					else
						throw new B2BException(ErrorKeys.CON_INCONSISTENCIA_SEQUENCE);
				}

				lps_insercion.setString(li_column++, aci_param.getIdInconsistencia());
				lps_insercion.setString(li_column++, aci_param.getIdArchivo());
				setLong(lps_insercion, aci_param.getIdRegistro(), li_column++);
				lps_insercion.setString(li_column++, aci_param.getIdCuenta());
				lps_insercion.setString(li_column++, aci_param.getIdPeriodoCorte());
				lps_insercion.setString(li_column++, aci_param.getCodigoInconsistencia());
				lps_insercion.setString(li_column++, aci_param.getColumna());
				lps_insercion.setString(li_column++, aci_param.getEstado());
				setDate(lps_insercion, aci_param.getFechaSolucion(), li_column++);
				lps_insercion.setString(li_column++, aci_param.getValorSolucion());
				lps_insercion.setString(li_column++, aci_param.getUsuarioSolucion());
				setLong(lps_insercion, aci_param.getIdBitacora(), li_column++);
				lps_insercion.setString(li_column++, aci_param.getIdUsuarioCreacion());
				lps_insercion.setString(li_column++, aci_param.getIpCreacion());

				lps_insercion.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_insercion);
				close(lps_secuencia);
				close(lrs_secuencia);
			}
		}

		return aci_param;
	}

	/**
	 * Update.
	 *
	 * @param aci_param the apc corte
	 * @throws B2BException the b 2 B exception
	 */
	public void update(ConInconsistencia aci_param)
	    throws B2BException
	{
		if(aci_param != null)
		{
			PreparedStatement lps_update;

			lps_update = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();
				lps_update        = lc_connection.prepareStatement(cs_UPDATE);

				lps_update.setString(li_column++, aci_param.getIdArchivo());
				setLong(lps_update, aci_param.getIdRegistro(), li_column++);
				lps_update.setString(li_column++, aci_param.getIdCuenta());
				lps_update.setString(li_column++, aci_param.getIdPeriodoCorte());
				lps_update.setString(li_column++, aci_param.getCodigoInconsistencia());
				lps_update.setString(li_column++, aci_param.getColumna());
				lps_update.setString(li_column++, aci_param.getEstado());
				setDate(lps_update, aci_param.getFechaSolucion(), li_column++);
				lps_update.setString(li_column++, aci_param.getValorSolucion());
				lps_update.setString(li_column++, aci_param.getUsuarioSolucion());
				setLong(lps_update, aci_param.getIdBitacora(), li_column++);
				lps_update.setString(li_column++, aci_param.getIdUsuarioModificacion());
				lps_update.setString(li_column++, aci_param.getIpModificacion());
				lps_update.setString(li_column++, aci_param.getIdInconsistencia());

				lps_update.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_update);
			}
		}
	}

	/**
	 * Gets the objet from result set.
	 *
	 * @param ars_rs the ars rs
	 * @return the objet from result set
	 * @throws SQLException the SQL exception
	 */
	private ConInconsistencia getObjetFromResultSet(ResultSet ars_rs, boolean ab_detalle)
	    throws SQLException
	{
		ConInconsistencia lci_datos;

		lci_datos = new ConInconsistencia();

		lci_datos.setIdInconsistencia(ars_rs.getString("ID_INCONSISTENCIA"));
		lci_datos.setIdArchivo(ars_rs.getString("ID_ARCHIVO"));
		lci_datos.setIdRegistro(getLong(ars_rs, "ID_REGISTRO"));
		lci_datos.setIdCuenta(ars_rs.getString("ID_CUENTA"));
		lci_datos.setIdPeriodoCorte(ars_rs.getString("ID_PERIODO_CORTE"));
		lci_datos.setCodigoInconsistencia(ars_rs.getString("CODIGO_INCONSISTENCIA"));
		lci_datos.setColumna(ars_rs.getString("COLUMNA"));
		lci_datos.setEstado(ars_rs.getString("ESTADO"));
		lci_datos.setFechaSolucion(ars_rs.getDate("FECHA_SOLUCION"));
		lci_datos.setValorSolucion(ars_rs.getString("VALOR_SOLUCION"));
		lci_datos.setUsuarioSolucion(ars_rs.getString("USUARIO_SOLUCION"));
		lci_datos.setIdBitacora(getLong(ars_rs, "ID_BITACORA"));

		fillAuditoria(ars_rs, lci_datos);

		if(ab_detalle)
		{
			lci_datos.setTipo(ars_rs.getString("TIPO"));
			lci_datos.setFechaCreacion(DateUtils.getDate(ars_rs.getTimestamp("FECHA_CREACION")));
			lci_datos.setMensaje(ars_rs.getString("MENSAJE"));
			lci_datos.setObservaciones(ars_rs.getString("OBSERVACION"));
		}

		return lci_datos;
	}

	private ConInconsistencia getObjetFromResultSetFillTagsObs(ResultSet ars_rs)
	    throws SQLException
	{
		ConInconsistencia lci_datos;

		lci_datos = new ConInconsistencia();

		lci_datos.setBanco(ars_rs.getString("NOMBRE_ENTIDAD_RECAUDADORA"));
		lci_datos.setCuenta(ars_rs.getString("NUMERO_CUENTA"));
		lci_datos.setPeriodo(ars_rs.getString("PERIODO"));
		lci_datos.setCorte(ars_rs.getString("DIA_CORTE"));
		lci_datos.setIdRegistro(getLong(ars_rs, "ID_REGISTRO"));
		lci_datos.setColumna(ars_rs.getString("COLUMNA"));
		lci_datos.setFechaCreacion(DateUtils.getDate(ars_rs.getTimestamp("FECHA_CREACION")));

		return lci_datos;
	}
}
