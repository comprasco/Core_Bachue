package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.model.pgn.ConArchivo;

import org.apache.commons.io.IOUtils;

import java.math.BigDecimal;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_CON_ARCHIVO
 * @author dbeltran
 *
 */
public class ConArchivoDAO extends AuditoriaDao
{
	/**Constante cs_FIND_ALL*/
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_CON_ARCHIVO";

	/**Constante cs_FIND_ALL*/
	private static final String cs_FIND_ALL_PRESENTACION = "SELECT ERC.NUMERO_CUENTA, SPC.DIA_CORTE,SCA.* FROM SDB_CON_ARCHIVO SCA "
		+ "INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA ERC ON SCA.ID_CUENTA = ERC.ID_CUENTA "
		+ "INNER JOIN SDB_PGN_PERIODO_CORTE SPC ON SCA.ID_PERIODO_CORTE = SPC.ID_PERIODO_CORTE";

	/**Constante cs_FIND_BY_ID*/
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_CON_ARCHIVO WHERE ID_ARCHIVO = ?";

	/**Constante cs_FIND_BY_ID*/
	private static final String cs_FIND_BY_ID_PRESENTACION = "SELECT ERC.NUMERO_CUENTA, SPC.DIA_CORTE,SCA.* FROM SDB_CON_ARCHIVO SCA "
		+ "INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CUENTA ERC ON SCA.ID_CUENTA = ERC.ID_CUENTA "
		+ "INNER JOIN SDB_PGN_PERIODO_CORTE SPC ON SCA.ID_PERIODO_CORTE = SPC.ID_PERIODO_CORTE WHERE SCA.ID_ARCHIVO = ?";

	/**Constante cs_FIND_BY_PARAMS*/
	private static final String cs_FIND_BY_PARAMS = "SELECT * FROM SDB_CON_ARCHIVO,"
		+ "(SELECT MAX(VERSION_ARCHIVO) AS MVERSION FROM SDB_CON_ARCHIVO  WHERE ID_CUENTA = ? AND ID_PERIODO_CORTE = ? AND TIPO_ARCHIVO = ?) P "
		+ "WHERE ID_CUENTA = ? AND ID_PERIODO_CORTE = ? AND TIPO_ARCHIVO = ? AND VERSION_ARCHIVO = P.MVERSION";

	/**Constante cs_INSERT*/
	private static final String cs_INSERT = "INSERT INTO SDB_CON_ARCHIVO (ID_ARCHIVO, ID_CUENTA, ID_PERIODO_CORTE, VERSION_ARCHIVO, TIPO_ARCHIVO, NOMBRE_ARCHIVO, ARCHIVO, ESTADO_ARCHIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES (?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/**Constante cs_UPDATE*/
	private static final String cs_UPDATE = "UPDATE SDB_CON_ARCHIVO SET ID_CUENTA = ?, ID_PERIODO_CORTE = ?, VERSION_ARCHIVO = ?, TIPO_ARCHIVO = ?, NOMBRE_ARCHIVO = ?, ARCHIVO = ?, ESTADO_ARCHIVO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_CREACION  = ? WHERE ID_ARCHIVO = ?";

	/**Constante cs_FIND_SECUENCE*/
	private static final String cs_FIND_SECUENCE = "SELECT SEC_CON_ARCHIVO_ID_ARCHIVO.NEXTVAL FROM DUAL";

	/**
	 *Metodo para encontrar  los registros existentes en la tabla SDB_PGN_ARCHIVO buscados por ID_CODIGO
	 * @param atd_param
	 * @return
	 * @throws B2BException
	 */
	public ConArchivo findById(ConArchivo atd_param, boolean ab_presentacion)
	    throws B2BException
	{
		return ((atd_param != null) ? findById(atd_param.getIdArchivo(), ab_presentacion) : null);
	}

	/**
	 *Metodo para encontrar  los registros existentes en la tabla SDB_PGN_ARCHIVO buscados por ID_CODIGO
	 * @param atd_param
	 * @return
	 * @throws B2BException
	 */
	public ConArchivo findById(ConArchivo atd_param)
	    throws B2BException
	{
		return ((atd_param != null) ? findById(atd_param.getIdArchivo(), false) : null);
	}

	/**
	 * Metodo para encontrar  los registros existentes en la tabla SDB_PGN_ARCHIVO buscados por ID_CODIGO
	 * @param as_param String con el valor del ID del registro a buscar
	 * @return de tipo Archivo con el valor del registro requerido
	 * @throws B2BException
	 */
	public ConArchivo findById(String as_param)
	    throws B2BException
	{
		return findById(as_param, false);
	}

	/**
	 * Metodo para encontrar  los registros existentes en la tabla SDB_PGN_ARCHIVO buscados por ID_CODIGO
	 * @param as_param String con el valor del ID del registro a buscar
	 * @param ab_presentacion si es para mostrar en pantalla
	 * @return de tipo Archivo con el valor del registro requerido
	 * @throws B2BException
	 */
	public ConArchivo findById(String as_param, boolean ab_presentacion)
	    throws B2BException
	{
		ConArchivo ltd_return;
		ltd_return = null;

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(ab_presentacion ? cs_FIND_BY_ID_PRESENTACION : cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltd_return = getObjetFromResultSet(lrs_rs, ab_presentacion);
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

		return ltd_return;
	}

	/**
	 * Metodo para encontrar  los registros existentes en la tabla SDB_CON_ARCHIVO buscados por los parametros del archivo
	 * @param as_idCuenta String con el valor del ID del registro a buscar
	 * @return de tipo Archivo con el valor del registro requerido
	 * @throws B2BException
	 */
	public ConArchivo findByCuentaPeriodoTipo(String as_idCuenta, String ls_idPeriodo, String is_TipoArchivo)
	    throws B2BException
	{
		ConArchivo ltd_return;
		ltd_return = null;

		if(
		    StringUtils.isValidString(as_idCuenta) && StringUtils.isValidString(ls_idPeriodo)
			    && StringUtils.isValidString(is_TipoArchivo)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_PARAMS);

				lps_ps.setString(li_column++, as_idCuenta);
				lps_ps.setString(li_column++, ls_idPeriodo);
				lps_ps.setString(li_column++, is_TipoArchivo);
				lps_ps.setString(li_column++, as_idCuenta);
				lps_ps.setString(li_column++, ls_idPeriodo);
				lps_ps.setString(li_column++, is_TipoArchivo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltd_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCuentaPeriodoTipo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ltd_return;
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_CON_ARCHIVO
	 * @return
	 * @throws B2BException
	 */
	public Collection<ConArchivo> findAll()
	    throws B2BException
	{
		return findAll(false);
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_CON_ARCHIVO
	 * @param para obtener los datos de otras tablas para mostrar en pantalla
	 * @return
	 * @throws B2BException
	 */
	public Collection<ConArchivo> findAll(boolean ab_presentacion)
	    throws B2BException
	{
		Collection<ConArchivo> lcpr_cpr;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lcpr_cpr     = new ArrayList<ConArchivo>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(ab_presentacion ? cs_FIND_ALL_PRESENTACION : cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpr_cpr.add(getObjetFromResultSet(lrs_rs, ab_presentacion));
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

		if(!CollectionUtils.isValidCollection(lcpr_cpr))
			lcpr_cpr = null;

		return lcpr_cpr;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_CON_ARCHIVO
	 *  @param apc_corte Registro a insertar en la base de datos
	 */
	public void insert(ConArchivo apc_corte)
	    throws B2BException
	{
		if(apc_corte != null)
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
						apc_corte.setIdArchivo(lo_o.toString());
					else
						throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
				}

				lps_insercion.setString(li_column++, apc_corte.getIdArchivo());
				lps_insercion.setString(li_column++, apc_corte.getIdCuenta());
				lps_insercion.setString(li_column++, apc_corte.getIdPeriodoCorte());

				lps_insercion.setBigDecimal(li_column++, apc_corte.getVersion());
				lps_insercion.setString(li_column++, apc_corte.getTipoArchivo());
				lps_insercion.setString(li_column++, apc_corte.getNombreArchivo());
				lps_insercion.setBinaryStream(li_column++, new java.io.ByteArrayInputStream(apc_corte.getArchivo()));

				lps_insercion.setString(li_column++, apc_corte.getEstadoArchivo());
				lps_insercion.setString(li_column++, apc_corte.getIdUsuarioCreacion());
				lps_insercion.setString(li_column++, apc_corte.getIpCreacion());

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
	}

	/**
	 * Metodo para modificar un registro en la base de datos de la tabla SDB_CON_ARCHIVO
	 *  @param apc_corte Registro a modificar en la base de datos
	 */
	public void update(ConArchivo apc_corte)
	    throws B2BException
	{
		if(apc_corte != null)
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

				lps_update.setString(li_column++, apc_corte.getIdCuenta());
				lps_update.setString(li_column++, apc_corte.getIdPeriodoCorte());

				lps_update.setBigDecimal(li_column++, apc_corte.getVersion());
				lps_update.setString(li_column++, apc_corte.getTipoArchivo());
				lps_update.setString(li_column++, apc_corte.getNombreArchivo());
				lps_update.setBinaryStream(li_column++, new java.io.ByteArrayInputStream(apc_corte.getArchivo()));

				lps_update.setString(li_column++, apc_corte.getEstadoArchivo());
				lps_update.setString(li_column++, apc_corte.getIdUsuarioModificacion());
				lps_update.setString(li_column++, apc_corte.getIpModificacion());

				lps_update.setString(li_column++, apc_corte.getIdArchivo());

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
	 * Metodo que se encarga de llenar un objeto de tipo Rubro con lo consultado y almacenado en un objeto ResultSet.
	 *
	 * @param ars_rs Argumento de tipo ResultSet que contiene los datos que serán almacenados en el objeto PeriodoCorte
	 * @return Objeto de tipo Archivo con lo consultado y almacenado en un objeto ResultSet.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private ConArchivo getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		return getObjetFromResultSet(ars_rs, false);
	}

	/**
	 * Metodo que se encarga de llenar un objeto de tipo Rubro con lo consultado y almacenado en un objeto ResultSet.
	 *
	 * @param ars_rs Argumento de tipo ResultSet que contiene los datos que serán almacenados en el objeto PeriodoCorte
	 * @param ab_presentacion para obtener datos que se deben mostrar en pantalla
	 * @return Objeto de tipo Archivo con lo consultado y almacenado en un objeto ResultSet.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private ConArchivo getObjetFromResultSet(ResultSet ars_rs, boolean ab_presentacion)
	    throws SQLException
	{
		ConArchivo lc_datos;
		byte[]     lba_blob;
		Blob       lb_blob;

		lc_datos     = new ConArchivo();

		lb_blob = ars_rs.getBlob("ARCHIVO");

		try
		{
			lba_blob = IOUtils.toByteArray(lb_blob.getBinaryStream());
		}
		catch(Exception e)
		{
			lba_blob = null;
		}

		lc_datos.setArchivo(lba_blob);
		lc_datos.setIdArchivo(ars_rs.getString("ID_ARCHIVO"));
		lc_datos.setIdCuenta(ars_rs.getString("ID_CUENTA"));
		lc_datos.setIdPeriodoCorte(ars_rs.getString("ID_PERIODO_CORTE"));
		lc_datos.setVersion(getBigDecimal(ars_rs, "VERSION_ARCHIVO"));
		lc_datos.setTipoArchivo(ars_rs.getString("TIPO_ARCHIVO"));
		lc_datos.setNombreArchivo(ars_rs.getString("NOMBRE_ARCHIVO"));
		lc_datos.setEstadoArchivo(ars_rs.getString("ESTADO_ARCHIVO"));

		if(ab_presentacion)
		{
			lc_datos.setDiaCorte(ars_rs.getDate("DIA_CORTE"));
			lc_datos.setNumeroCuenta(ars_rs.getString("NUMERO_CUENTA"));
		}

		fillAuditoria(ars_rs, lc_datos);

		return lc_datos;
	}
}
