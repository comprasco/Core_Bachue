package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.pgn.Consultas;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_CONSULTAS
 *
 * @author dbeltran
 */
public class ConsultasDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_CONSULTA, NOMBRE_CONSULTA, NOMBRE_PROCEDIMIENTO, DESCRIPCION_CONSULTA, ESTADO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PGN_CONSULTAS WHERE ID_CONSULTA=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CONSULTAS SET NOMBRE_CONSULTA=?, NOMBRE_PROCEDIMIENTO=?, DESCRIPCION_CONSULTA=?, ESTADO=?, FECHA_MODIFICACION=SYSTIMESTAMP, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=? WHERE ID_CONSULTA=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CONSULTAS(ID_CONSULTA, NOMBRE_CONSULTA, NOMBRE_PROCEDIMIENTO, DESCRIPCION_CONSULTA, ESTADO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_CONSULTA, NOMBRE_CONSULTA, NOMBRE_PROCEDIMIENTO, DESCRIPCION_CONSULTA, ESTADO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PGN_CONSULTAS ORDER BY NOMBRE_CONSULTA ASC";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_CONSULTA, NOMBRE_CONSULTA, NOMBRE_PROCEDIMIENTO, DESCRIPCION_CONSULTA, ESTADO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_PGN_CONSULTAS WHERE ESTADO = 'S' ";

	/** Constante cs_FIND_SECUENCE_CONSULTAS. */
	private static final String cs_FIND_SECUENCE_CONSULTAS = "SELECT SEC_PGN_CONSULTAS_ID_CONSULTA.NEXTVAL FROM DUAL";

	/** Constante cs_DATOS_ANT_SISTEMA_REGISTRO. */
	private static final String cs_DATOS_ANT_SISTEMA_REGISTRO = "select ci.ID_CIRCULO||' - '||ci.NOMBRE as CIRCULO,pr.DESCRIPCION as TIPO_DE_PREDIO, an.NOMBRE_PREDIO, co.NOMBRE as PAIS, de.NOMBRE as DEPARTAMENTO, mu.NOMBRE as MUNICIPIO from SDB_ACC_DATOS_ANT_SISTEMA an inner join SDB_PGN_CIRCULO_REGISTRAL ci on an.ID_CIRCULO = ci.ID_CIRCULO inner join SDB_PGN_PAIS co on an.ID_PAIS = co.ID_PAIS inner join SDB_PGN_DEPARTAMENTO de on an.ID_PAIS = de.ID_PAIS and an.ID_DEPARTAMENTO = de.ID_DEPARTAMENTO inner join SDB_PGN_MUNICIPIO mu on an.ID_PAIS = mu.ID_PAIS and an.ID_DEPARTAMENTO = mu.ID_DEPARTAMENTO and an.ID_MUNICIPIO = mu.ID_MUNICIPIO inner join SDB_COL_PREDIO_TIPO pr on an.ID_TIPO_PREDIO = pr.ID_TIPO_PREDIO where an.ID_SOLICITUD = ? AND an.ID_CIRCULO = ? ";

	/**
	 * Retorna el valor del objeto de tipo Collection de Consultas con todos los registros
	 *
	 * @return devuelve el valor del objeto collection de Consultas
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Consultas> findAll()
	    throws B2BException
	{
		Collection<Consultas> lc_data;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		lc_data     = new ArrayList<Consultas>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lc_data.add(getObjetFromResultSet(lrs_rs));
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

		if(lc_data.isEmpty())
			lc_data = null;

		return lc_data;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Consultas con todos los registros activos
	 *
	 * @param ab_OrderById correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection de Consultas
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Consultas> findAllActive(boolean ab_OrderById)
	    throws B2BException
	{
		Collection<Consultas> lc_data;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		lc_data     = new ArrayList<Consultas>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			StringBuilder lsb_query;

			lsb_query = new StringBuilder(cs_FIND_ALL_ACTIVO);

			lsb_query.append(
			    ab_OrderById ? " ORDER BY LENGTH(ID_CONSULTA),ID_CONSULTA " : " ORDER BY NOMBRE_CONSULTA ASC "
			);
			lps_ps     = getConnection().prepareStatement(lsb_query.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lc_data.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActive", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lc_data.isEmpty())
			lc_data = null;

		return lc_data;
	}

	/**
	 * Retorna el valor del objeto de tipo Consultas filtado por ID
	 *
	 * @param at_param correspondiente al valor del tipo de objeto Consultas
	 * @return devuelve el valor del objeto consultas
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Consultas findById(Consultas at_param)
	    throws B2BException
	{
		Consultas         le_e;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		le_e       = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setLong(1, at_param.getIdConsulta());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				le_e = getObjetFromResultSet(lrs_rs);
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

		return le_e;
	}

	/**
	 * Consulta los datos de antiguo sistema asociados a una solicitud específica.
	 *
	 * @param as_idSolicitud Numero de solicitud para utilizar como filtro en la consulta
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @return Objeto contenedor con los resultados de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<DatosAntSistema> findDatosAntSistemaRegistro(String as_idSolicitud, String as_idCirculo)
	    throws B2BException
	{
		Collection<DatosAntSistema> lcdas_datos;
		lcdas_datos = new LinkedList<DatosAntSistema>();

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_DATOS_ANT_SISTEMA_REGISTRO);

				lps_ps.setString(li_cont++, as_idSolicitud);
				lps_ps.setString(li_cont++, as_idCirculo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdas_datos.add(getDatosAntSistemaRegistro(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDatosAntSistemaRegistro", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcdas_datos.isEmpty())
			lcdas_datos = null;

		return lcdas_datos;
	}

	/**
	 * Método para insertar o actualizar registros en la base de datos.
	 *
	 * @param apt_parametros correspondiente al valor del tipo de objeto Consultas
	 * @param ab_query indica si se desea insertar o actualizar(true inserta, false modifica)
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(Consultas apt_parametros, boolean ab_query)
	    throws B2BException
	{
		if(apt_parametros != null)
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

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE_CONSULTAS);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							apt_parametros.setIdConsulta(NumericUtils.getLong(lo_o.toString()));
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}

					lps_ps.setLong(li_column++, apt_parametros.getIdConsulta());
				}

				lps_ps.setString(li_column++, apt_parametros.getNombreConsulta());
				lps_ps.setString(li_column++, apt_parametros.getNombreProcedimiento());
				lps_ps.setString(li_column++, apt_parametros.getDescripcionConsulta());
				lps_ps.setString(li_column++, apt_parametros.getEstado());

				if(ab_query)
				{
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, apt_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, apt_parametros.getIpModificacion());
					lps_ps.setLong(li_column++, apt_parametros.getIdConsulta());
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

				if(ab_query)
				{
					close(lps_secuencia);
					close(lrs_rs);
				}
			}
		}
	}

	/**
	 * Retorna el valor de datos ant sistema registro.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de datos ant sistema registro
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private DatosAntSistema getDatosAntSistemaRegistro(ResultSet ars_rs)
	    throws SQLException
	{
		DatosAntSistema ldas_datos;

		ldas_datos = new DatosAntSistema();

		ldas_datos.setIdCirculo(ars_rs.getString("CIRCULO"));
		ldas_datos.setIdTipoPredio(ars_rs.getString("TIPO_DE_PREDIO"));
		ldas_datos.setNombrePredio(ars_rs.getString("NOMBRE_PREDIO"));
		ldas_datos.setIdPais(ars_rs.getString("PAIS"));
		ldas_datos.setIdDepartamento(ars_rs.getString("DEPARTAMENTO"));
		ldas_datos.setIdMunicipio(ars_rs.getString("MUNICIPIO"));

		return ldas_datos;
	}

	/**
	 * Retorna el valor de Consultas
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de Consultas
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Consultas getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Consultas lc_datos;

		lc_datos = new Consultas();

		lc_datos.setIdConsulta(ars_rs.getLong("ID_CONSULTA"));
		lc_datos.setNombreConsulta(ars_rs.getString("NOMBRE_CONSULTA"));
		lc_datos.setNombreProcedimiento(ars_rs.getString("NOMBRE_PROCEDIMIENTO"));
		lc_datos.setDescripcionConsulta(ars_rs.getString("DESCRIPCION_CONSULTA"));
		lc_datos.setEstado(ars_rs.getString("ESTADO"));

		lc_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lc_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lc_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lc_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lc_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lc_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lc_datos;
	}
}
