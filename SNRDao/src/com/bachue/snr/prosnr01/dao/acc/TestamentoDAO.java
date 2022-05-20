package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.Testamento;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_TESTAMENTO.
 *
 * @author garias
 */
public class TestamentoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_TESTAMENTO WHERE ID_TESTAMENTO=?";

	/** Constante cs_FIND_BY_ID_PERSONA. */
	private static final String cs_FIND_BY_ID_PERSONA = "SELECT * FROM SDB_ACC_TESTAMENTO WHERE ID_PERSONA=?";

	/** Constante cs_FIND_BY_TURNO. */
	private static final String cs_FIND_BY_TURNO = "SELECT * FROM SDB_ACC_TESTAMENTO WHERE ID_TURNO=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_TESTAMENTO (ID_TESTAMENTO, NUM_RADICACION, OBSERVACION, ID_PERSONA, FECHA_CREACION, TOMO, NUMERO_ANOTACIONES, NUMERO_COPIAS, REVOCA_ESCRITURA, ID_NCIUDADANO, ID_USUARIO_CREACION, ID_USUARIO_TMP, IP_CREACION, LIBRO, FOLIO, CATEGORIA, FECHA_REGISTRO, ID_TIPO_TESTAMENTO, ID_TESTAMENTO_ANTERIOR,ANIO_TESTAMENTO,ID_CIRCULO) VALUES (?, ? , ?, ?, SYSTIMESTAMP, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,SYSTIMESTAMP,?,?,?,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_TESTAMENTO SET NUM_RADICACION = ?, OBSERVACION = ?, ID_PERSONA = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ?, TOMO = ?, NUMERO_ANOTACIONES = ?, NUMERO_COPIAS = ?, REVOCA_ESCRITURA = ?, ID_NCIUDADANO = ?,  ID_USUARIO_TMP = ?,  LIBRO = ?, FOLIO = ?, CATEGORIA = ?,ID_TIPO_TESTAMENTO = ?, ID_TESTAMENTO_ANTERIOR = ?, ANIO_TESTAMENTO = ?, ID_CIRCULO = ? WHERE ID_TESTAMENTO  = ?";

	/** Constante cs_SECUENCE. */
	private static final String cs_SECUENCE = "SELECT SEC_ACC_TESTAMENTO_ID_TESTAMENTO.NEXTVAL FROM DUAL";

	/** Constante cs_BUSQUEDA_DE_TESTAMENTO. */
	private static final String cs_BUSQUEDA_DE_TESTAMENTO = "SELECT  SPTT.NOMBRE, SAP.NUMERO_DOCUMENTO, SAP.PRIMER_NOMBRE,SAP.SEGUNDO_NOMBRE,SAP.PRIMER_APELLIDO,SAP.SEGUNDO_APELLIDO, SAT.*"
		+ " FROM SDB_ACC_TESTAMENTO SAT"
		+ " INNER JOIN SDB_PGN_TIPO_TESTAMENTO SPTT ON SAT.ID_TIPO_TESTAMENTO = SPTT.ID_TIPO_TESTAMENTO"
		+ " INNER JOIN SDB_ACC_SOLICITUD SAS ON SAS.ID_TESTAMENTO = SAT.ID_TESTAMENTO AND SAS.ID_PROCESO = '6' AND SAS.ID_SUBPROCESO = '6'"
		+ " INNER JOIN SDB_ACC_TURNO SATU ON SATU.ID_PROCESO ='6' AND SATU.ID_SUBPROCESO='6' AND SAS.ID_SOLICITUD = SATU.ID_SOLICITUD"
		+ " INNER JOIN SDB_BGN_DOCUMENTO SBD ON SAS.ID_DOCUMENTO = SBD.ID_DOCUMENTO"
		+ " INNER JOIN SDB_ACC_PERSONA SAP ON SAS.ID_PERSONA = SAP.ID_PERSONA ";

	/**
	 * Retorna el valor del objeto de Testamento.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto Testamento
	 * @return devuelve el valor de Testamento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Testamento
	 */
	public Testamento findById(Testamento at_param)
	    throws B2BException
	{
		Testamento        ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(at_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, at_param.getIdTestamento());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
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

		return ls_object;
	}

	/**
	 * Método encargado de buscar el testamento id persona.
	 *
	 * @param as_idPersona Variable que contiene el id de la persona.
	 * @return Colección que contiene los datos consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Testamento> findByIdPersona(String as_idPersona)
	    throws B2BException
	{
		Collection<Testamento> lct_return;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lct_return     = new ArrayList<Testamento>();
		lps_ps         = null;
		lrs_rs         = null;

		if(StringUtils.isValidString(as_idPersona))
		{
			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_PERSONA);

				lps_ps.setString(li_column++, as_idPersona);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lct_return.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdPersona", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lct_return.isEmpty())
			lct_return = null;

		return lct_return;
	}

	/**
	 * Método encargado de buscar el testamento por su turno.
	 *
	 * @param at_param Argumento de tipo <code>Testamento</code> que contiene el testamento de la operación.
	 * @return Variable de tipo <code>Testamento</code> que contiene el valor de testamento.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Testamento findByTurno(Testamento at_param)
	    throws B2BException
	{
		Testamento        ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(at_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO);

				lps_ps.setString(1, at_param.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
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

		return ls_object;
	}

	/**
	 * Método de consulta de testamentos.
	 *
	 * @param at_param con los parametros de la cosnulta
	 * @return de Collection<Testamento> con los resultados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Testamento> findTestamentos(Testamento at_param)
	    throws B2BException
	{
		Collection<Testamento> ls_object;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		ls_object     = new ArrayList<Testamento>();
		lps_ps        = null;
		lrs_rs        = null;

		if(at_param != null)
		{
			try
			{
				StringBuilder lsb_sb;
				int           li_contador;

				lsb_sb          = new StringBuilder(cs_BUSQUEDA_DE_TESTAMENTO);
				li_contador     = 1;

				if(at_param.getTipoBusqueda().equalsIgnoreCase("D"))
					lsb_sb.append("WHERE SBD.NUMERO = ?");
				else
					lsb_sb.append("WHERE SAP.NUMERO_DOCUMENTO = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				if(at_param.getTipoBusqueda().equalsIgnoreCase("D"))
					lps_ps.setString(li_contador++, at_param.getNumeroDocumentoBusqueda());
				else
					lps_ps.setString(li_contador++, at_param.getIdPersonaBusqueda());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ls_object.add(getObjetFromResultSet(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findTestamentos", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_object;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_TIPO_DOCUMENTAL.
	 *
	 * @param atd_param de atd param
	 * @return el valor de long
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public long insert(Testamento atd_param)
	    throws B2BException
	{
		long ll_secuencia;
		ll_secuencia = 0;

		if(atd_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				{
					lps_secuencia     = lc_connection.prepareStatement(cs_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							atd_param.setIdTestamento(StringUtils.getString(lo_o));
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);

						ll_secuencia = lrs_rs.getLong(1);
					}
				}

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setLong(li_column++, ll_secuencia);
				lps_ps.setString(li_column++, atd_param.getNumRadicacion());
				lps_ps.setString(li_column++, atd_param.getObservacion());
				lps_ps.setString(li_column++, atd_param.getIdPersona());
				setLong(lps_ps, atd_param.getTomo(), li_column++);
				lps_ps.setString(li_column++, atd_param.getNumeroAnotaciones());
				lps_ps.setString(li_column++, atd_param.getNumeroCopias());
				lps_ps.setString(li_column++, atd_param.getRevocaEscritura());
				lps_ps.setBigDecimal(li_column++, atd_param.getIdNciudadano());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioCreacion());
				lps_ps.setBigDecimal(li_column++, atd_param.getIdUsuarioTmp());
				lps_ps.setString(li_column++, atd_param.getIpCreacion());
				setLong(lps_ps, atd_param.getLibro(), li_column++);
				setLong(lps_ps, atd_param.getFolio(), li_column++);
				lps_ps.setString(li_column++, atd_param.getCategoria());
				lps_ps.setString(li_column++, atd_param.getIdTipoTestamento());
				lps_ps.setString(li_column++, atd_param.getIdTestamentoAnterior());
				setLong(lps_ps, atd_param.getAnoTestamento(), li_column++);
				lps_ps.setString(li_column++, atd_param.getIdCirculo());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "insertTestamento", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lps_secuencia);
				close(lrs_rs);
			}
		}

		return ll_secuencia;
	}

	/**
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_CAUSAL_MAYOR_VALOR.
	 *
	 * @param atd_param de atd param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(Testamento atd_param)
	    throws B2BException
	{
		if(atd_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, atd_param.getNumRadicacion());
				lps_ps.setString(li_column++, atd_param.getObservacion());
				lps_ps.setString(li_column++, atd_param.getIdPersona());
				lps_ps.setString(li_column++, atd_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atd_param.getIpModificacion());
				setLong(lps_ps, atd_param.getTomo(), li_column++);
				lps_ps.setString(li_column++, atd_param.getNumeroAnotaciones());
				lps_ps.setString(li_column++, atd_param.getNumeroCopias());
				lps_ps.setString(li_column++, atd_param.getRevocaEscritura());
				lps_ps.setBigDecimal(li_column++, atd_param.getIdNciudadano());
				lps_ps.setBigDecimal(li_column++, atd_param.getIdUsuarioTmp());
				setLong(lps_ps, atd_param.getLibro(), li_column++);
				setLong(lps_ps, atd_param.getFolio(), li_column++);
				lps_ps.setString(li_column++, atd_param.getCategoria());
				lps_ps.setString(li_column++, atd_param.getIdTipoTestamento());
				lps_ps.setString(li_column++, atd_param.getIdTestamentoAnterior());
				setLong(lps_ps, atd_param.getAnoTestamento(), li_column++);
				lps_ps.setString(li_column++, atd_param.getIdCirculo());

				lps_ps.setString(li_column++, atd_param.getIdTestamento());

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
	 * @param lrs_rs de lrs rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private Testamento getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		return getObjetFromResultSet(lrs_rs, false);
	}

	/**
	 * Retorna el valor de Testamento.
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_b de ab b
	 * @return el valor de Testamento
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Testamento getObjetFromResultSet(ResultSet lrs_rs, boolean ab_b)
	    throws SQLException
	{
		Testamento lt_testamento;

		lt_testamento = new Testamento();

		lt_testamento.setIdTestamento(lrs_rs.getString("ID_TESTAMENTO"));
		lt_testamento.setNumRadicacion(lrs_rs.getString("NUM_RADICACION"));
		lt_testamento.setObservacion(lrs_rs.getString("OBSERVACION"));
		lt_testamento.setIdPersona(lrs_rs.getString("ID_PERSONA"));
		lt_testamento.setTomo(getLong(lrs_rs, "TOMO"));
		lt_testamento.setNumeroAnotaciones(lrs_rs.getString("NUMERO_ANOTACIONES"));
		lt_testamento.setNumeroCopias(lrs_rs.getString("NUMERO_COPIAS"));
		lt_testamento.setFechaRegistro(lrs_rs.getDate("FECHA_REGISTRO"));
		lt_testamento.setRevocaEscritura(lrs_rs.getString("REVOCA_ESCRITURA"));
		lt_testamento.setIdNciudadano(getBigDecimal(lrs_rs, "ID_NCIUDADANO"));
		lt_testamento.setIdUsuarioTmp(getBigDecimal(lrs_rs, "ID_USUARIO_TMP"));
		lt_testamento.setLibro(getLong(lrs_rs, "LIBRO"));
		lt_testamento.setFolio(getLong(lrs_rs, "FOLIO"));
		lt_testamento.setCategoria(lrs_rs.getString("CATEGORIA"));
		lt_testamento.setIdTipoTestamento(lrs_rs.getString("ID_TIPO_TESTAMENTO"));
		lt_testamento.setIdTestamentoAnterior(lrs_rs.getString("ID_TESTAMENTO_ANTERIOR"));
		lt_testamento.setAnoTestamento(getLong(lrs_rs, "ANIO_TESTAMENTO"));
		lt_testamento.setIIdCirculo(lrs_rs.getString("ID_CIRCULO"));
		lt_testamento.setIdTurno(lrs_rs.getString("ID_TURNO"));

		if(ab_b)
		{
			lt_testamento.setNombreTipoTestamento(lrs_rs.getString("NOMBRE"));
			lt_testamento.setPrimerNombre(lrs_rs.getString("PRIMER_NOMBRE"));
			lt_testamento.setSegundoNombre(lrs_rs.getString("SEGUNDO_NOMBRE"));
			lt_testamento.setPrimerApellido(lrs_rs.getString("PRIMER_APELLIDO"));
			lt_testamento.setSegundoApellido(lrs_rs.getString("SEGUNDO_APELLIDO"));
		}

		fillAuditoria(lrs_rs, lt_testamento);

		return lt_testamento;
	}
}
