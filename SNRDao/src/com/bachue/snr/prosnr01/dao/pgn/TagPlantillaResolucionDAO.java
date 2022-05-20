package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.utils.ConversionTextos;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_TAG_PLANTILLA_RESOLUCION
 *
 * @author Gabriel Arias
 *
 */
public class TagPlantillaResolucionDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID_PLANTILLA. */
	private static final String cs_FIND_BY_ID_PLANTILLA = " SELECT * FROM SDB_PGN_TAG_PLANTILLA_RESOLUCION WHERE ID_PLANTILLA = ? AND ACTIVO = 'S'";

	/** Constante cs_FIND_BY_ID_TAG_PLANTILLA_RESOLUCION. */
	private static final String cs_FIND_BY_ID_TAG_PLANTILLA_RESOLUCION = " SELECT * FROM SDB_PGN_TAG_PLANTILLA_RESOLUCION WHERE ID_TAG_PLANTILLA_RESOLUCION = ? ";

	/** Constante cs_FIND_BY_ID_PLANTILLA_TAG. */
	private static final String cs_FIND_BY_ID_PLANTILLA_TAG = " SELECT * FROM SDB_PGN_TAG_PLANTILLA_RESOLUCION WHERE ID_PLANTILLA = ? AND TAG = ? AND ACTIVO = 'S'";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_TAG_PLANTILLA_RESOLUCION_ID_TAG_PLANTILLA_RESOLUCION.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TAG_PLANTILLA_RESOLUCION SET "
		+ " ID_PLANTILLA = ?, TAG = ?, TEXTO = ?, ID_UBICACION_SALVADO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? "
		+ " WHERE ID_TAG_PLANTILLA_RESOLUCION = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TAG_PLANTILLA_RESOLUCION("
		+ "ID_TAG_PLANTILLA_RESOLUCION, ID_PLANTILLA, TAG, TEXTO, ID_UBICACION_SALVADO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_TAG_PLANTILLA_RESOLUCION ";

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_TAG_PLANTILLA_RESOLUCION .
	 *
	 * @return devuelve el valor del objeto collection de TagPlantillaResolucion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TagPlantillaResolucion> findAll()
	    throws B2BException
	{
		Collection<TagPlantillaResolucion> lccad_ccad;
		PreparedStatement                  lps_ps;
		ResultSet                          lrs_rs;

		lccad_ccad     = new ArrayList<TagPlantillaResolucion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccad_ccad.add(getTagPlantillaResolucion(lrs_rs));
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

		if(lccad_ccad.isEmpty())
			lccad_ccad = null;

		return lccad_ccad;
	}

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_TAG_PLANTILLA_RESOLUCION para un ID_PLANTILLA y un ID_tag.
	 * @param Argumento de tipo <code>String</code> que contiene el ID_PLANTILLA a consultar.
	 * @return devuelve el valor del objeto collection de PersonaNotificacion
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public TagPlantillaResolucion findById(String as_idTagPlantillaResolucion)
	    throws B2BException
	{
		TagPlantillaResolucion laa_return;

		laa_return = null;

		if(StringUtils.isValidString(as_idTagPlantillaResolucion))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_TAG_PLANTILLA_RESOLUCION);

				lps_ps.setString(li_column++, as_idTagPlantillaResolucion);
				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					laa_return = getTagPlantillaResolucion(lrs_rs);
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

		return laa_return;
	}

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_TAG_PLANTILLA_RESOLUCION para un ID_PLANTILLA.
	 * @param Argumento de tipo <code>String</code> que contiene el ID_PLANTILLA a consultar.
	 * @return devuelve el valor del objeto collection de PersonaNotificacion
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public Collection<TagPlantillaResolucion> findByIdPlantilla(String as_idPlantilla)
	    throws B2BException
	{
		Collection<TagPlantillaResolucion> lcaa_return;

		lcaa_return = new ArrayList<TagPlantillaResolucion>();

		if(StringUtils.isValidString(as_idPlantilla))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_PLANTILLA);

				lps_ps.setString(li_column++, as_idPlantilla);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcaa_return.add(getTagPlantillaResolucion(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdPlantilla", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcaa_return.isEmpty())
			lcaa_return = null;

		return lcaa_return;
	}

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_TAG_PLANTILLA_RESOLUCION para un ID_PLANTILLA.
	 * @param Argumento de tipo <code>String</code> que contiene el ID_PLANTILLA a consultar.
	 * @return devuelve el valor del objeto mapa de String String
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public Map<String, String> findByIdPlantillaMap(String as_idPlantilla)
	    throws B2BException
	{
		Map<String, String> lmss_return;

		lmss_return = new HashMap<String, String>();

		if(StringUtils.isValidString(as_idPlantilla))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_PLANTILLA);

				lps_ps.setString(li_column++, as_idPlantilla);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					TagPlantillaResolucion laa_data;

					laa_data = getTagPlantillaResolucion(lrs_rs);

					if(laa_data != null)
						lmss_return.put(laa_data.getTag(), laa_data.getTexto());
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdPlantillaMap", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lmss_return.isEmpty())
			lmss_return = null;

		return lmss_return;
	}

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_TAG_PLANTILLA_RESOLUCION para un ID_PLANTILLA y un ID_tag.
	 * @param Argumento de tipo <code>String</code> que contiene el ID_PLANTILLA a consultar.
	 * @return devuelve el valor del objeto collection de PersonaNotificacion
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public TagPlantillaResolucion findByIdPlantillaTag(String as_idPlantilla, String as_tag)
	    throws B2BException
	{
		TagPlantillaResolucion laa_return;

		laa_return = null;

		if(StringUtils.isValidString(as_idPlantilla) && StringUtils.isValidString(as_tag))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_PLANTILLA_TAG);

				lps_ps.setString(li_column++, as_idPlantilla);
				lps_ps.setString(li_column++, as_tag);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					laa_return = getTagPlantillaResolucion(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdPlantillaTag", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return laa_return;
	}

	/**
	 * Inserta o actualiza un registro en la tabla.
	 *
	 * @param aaa_parametros contenedor de la informacion a almacenar
	 * @param ab_query true para insertar, false para actualizar
	 * @return Objeto final insertado o actualizado en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaNotificar
	 */
	public TagPlantillaResolucion insertOrUpdate(TagPlantillaResolucion aaa_parametros, boolean ab_query)
	    throws B2BException
	{
		if(aaa_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lrs_rs            = null;
			lps_secuencia     = null;
			lps_ps            = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;
				lps_ps            = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
						{
							aaa_parametros.setIdTagPlantillaResolucion(((BigDecimal)lo_o).toString());

							lps_ps.setString(li_column++, aaa_parametros.getIdTagPlantillaResolucion());
						}
						else
							throw new B2BException(ErrorKeys.SIN_SECUENCIA_PERSONA);
					}
				}

				lps_ps.setString(li_column++, aaa_parametros.getIdPlantilla());
				lps_ps.setString(li_column++, aaa_parametros.getTag());
				lps_ps.setString(li_column++, aaa_parametros.getTexto());
				lps_ps.setString(li_column++, aaa_parametros.getIdUbicacionSalvado());

				if(ab_query)
				{
					lps_ps.setString(li_column++, aaa_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aaa_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, aaa_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, aaa_parametros.getIpModificacion());
					lps_ps.setString(li_column++, aaa_parametros.getIdTagPlantillaResolucion());
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

		return aaa_parametros;
	}

	/**
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de persona notificacion
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de alerta naturaleza
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException
	 */
	private TagPlantillaResolucion getTagPlantillaResolucion(ResultSet ars_rs)
	    throws SQLException, B2BException
	{
		TagPlantillaResolucion laa_datos;

		laa_datos = new TagPlantillaResolucion();

		laa_datos.setIdTagPlantillaResolucion(ars_rs.getString("ID_TAG_PLANTILLA_RESOLUCION"));
		laa_datos.setIdPlantilla(ars_rs.getString("ID_PLANTILLA"));
		laa_datos.setTag(ars_rs.getString("TAG"));

		{
			String ls_texto;

			ls_texto     = ars_rs.getString("TEXTO");

			ls_texto     = ConversionTextos.convertirTextos(ls_texto, "“", "\"");
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, "”", "\"");
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, "(…)", "(...)");

			laa_datos.setTexto(ls_texto);
		}
		laa_datos.setIdUbicacionSalvado(ars_rs.getString("ID_UBICACION_SALVADO"));

		fillAuditoria(ars_rs, laa_datos);

		return laa_datos;
	}
}
