package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para el manejo de datos para la tabla SDB_PGN_TIPO_DOCUMENTO_PUBLICO.
 *
 * @author Sebastian Tafur
 */
public class TipoDocumentoPublicoDAO extends BaseDAO implements IBase<TipoDocumentoPublico>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_TIPO_DOCUMENTO, NOMBRE, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION,TIPO_OFICINA FROM SDB_PGN_TIPO_DOCUMENTO_PUBLICO WHERE ID_TIPO_DOCUMENTO=?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_TIPO_DOCUMENTO, NOMBRE, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION,TIPO_OFICINA FROM SDB_PGN_TIPO_DOCUMENTO_PUBLICO ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_TIPO_DOCUMENTO, NOMBRE, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION,TIPO_OFICINA FROM SDB_PGN_TIPO_DOCUMENTO_PUBLICO WHERE ACTIVO = ? ORDER BY NOMBRE ASC";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TIPO_DOCUMENTO_PUBLICO SET NOMBRE=?, ACTIVO=?,ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP WHERE ID_TIPO_DOCUMENTO=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TIPO_DOCUMENTO_PUBLICO(ID_TIPO_DOCUMENTO, NOMBRE,ACTIVO,ID_USUARIO_CREACION, IP_CREACION, FECHA_CREACION)VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_TIPO_DOCUMENTO_PUBLICO_ID_TIPO_DOCUMENTO.NEXTVAL FROM DUAL";

	/** Constante cs_VALIDAR_TIPO_DOCUMENTO. */
	private static final String cs_VALIDAR_TIPO_DOCUMENTO = "SELECT count(0) resultado FROM SDB_COL_INTERESADO_DOCUMENTO_TIPO WHERE ID_DOCUMENTO_TIPO = ltrim(rtrim(?)) AND ACTIVO = 'S'";

	/**
	 * Retorna el valor del objeto de tipo Find all.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TipoDocumentoPublico> findAll()
	    throws B2BException
	{
		return findAll(null);
	}

/**
 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_TIPO_DOCUMENTO_PUBLICO
 * que se encuentren activos.
 *
 * @param as_estado correspondiente al valor del tipo de objeto String
 * @return devuelve el valor del objeto collection de TipoDocumentoPublico
 * @throws B2BException Señala que se ha producido una excepción
 */
	public Collection<TipoDocumentoPublico> findAll(String as_estado)
	    throws B2BException
	{
		Collection<TipoDocumentoPublico> ls_object;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;
		boolean                          lb_activo;

		lps_ps        = null;
		lrs_rs        = null;
		ls_object     = new ArrayList<TipoDocumentoPublico>();
		lb_activo     = StringUtils.isValidString(as_estado);

		try
		{
			lps_ps = getConnection().prepareStatement(lb_activo ? cs_FIND_ALL_ACTIVO : cs_FIND_ALL);

			if(lb_activo)
				lps_ps.setString(1, as_estado);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
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

		if(ls_object.isEmpty())
			ls_object = null;

		return ls_object;
	}

	/**
	 * Busca un tipo documento publico por un id específico.
	 *
	 * @param atdp_param correspondiente al valor del tipo de objeto TipoDocumentoPublico
	 * @return Tipo documento publico resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TipoDocumentoPublico findById(TipoDocumentoPublico atdp_param)
	    throws B2BException
	{
		return (atdp_param != null) ? findById(atdp_param.getIdTipoDocumento()) : null;
	}

	/**
	 * Busca un tipo documento publico por un id específico.
	 *
	 * @param as_idTipoDocumento Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return Tipo documento publico resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TipoDocumentoPublico findById(String as_idTipoDocumento)
	    throws B2BException
	{
		TipoDocumentoPublico ltdp_object;

		ltdp_object = null;

		if(StringUtils.isValidString(as_idTipoDocumento))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idTipoDocumento);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltdp_object = getObjetFromResultSet(lrs_rs);
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

		return ltdp_object;
	}

	/**
	 * Método para insertar o actualizar en la base de datos para la tabla
	 * SDB_PGN_TIPO_DOCUMENTO_PUBLICO.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto TipoDocumentoPublico
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@Override
	public void insertOrUpdate(TipoDocumentoPublico at_param, boolean ab_query)
	    throws B2BException
	{
		if(at_param != null)
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
				Connection lc_c;

				li_column     = 1;
				lc_c          = getConnection();
				lps_ps        = lc_c.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_c.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
						{
							at_param.setIdTipoDocumento(((BigDecimal)lo_o).toString());

							lps_ps.setString(li_column++, at_param.getIdTipoDocumento());
						}
						else
							throw new B2BException(ErrorKeys.SIN_SECUENCIA_DOCUMENTO_PUBLICO);
					}
				}

				lps_ps.setString(li_column++, at_param.getNombre());
				lps_ps.setString(li_column++, at_param.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, at_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, at_param.getIpModificacion());
					lps_ps.setString(li_column++, at_param.getIdTipoDocumento());
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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Retorna el valor del objeto de tipo Validar tipo documento.
	 *
	 * @param as_tipoDocumento correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int validarTipoDocumento(String as_tipoDocumento)
	    throws B2BException
	{
		int               li_count;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_count     = -1;
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_VALIDAR_TIPO_DOCUMENTO);

			lps_ps.setString(1, as_tipoDocumento.toUpperCase());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_count = lrs_rs.getInt("resultado");
		}
		catch(SQLException lse_e)
		{
			logError(this, "validarTipoDocumento", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_count;
	}

	/**
	 * Método de obtencion del objeto cirulo registral dependiendo el valor
	 * de la sentencia realizada.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de objet from result set
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoDocumentoPublico getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoDocumentoPublico ls_tipoActo;

		ls_tipoActo = new TipoDocumentoPublico();

		ls_tipoActo.setIdTipoDocumento(ars_rs.getString("ID_TIPO_DOCUMENTO"));
		ls_tipoActo.setNombre(ars_rs.getString("NOMBRE"));
		ls_tipoActo.setActivo(ars_rs.getString("ACTIVO"));
		ls_tipoActo.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ls_tipoActo.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ls_tipoActo.setTipoOficina(ars_rs.getString("TIPO_OFICINA"));

		return ls_tipoActo;
	}
}
