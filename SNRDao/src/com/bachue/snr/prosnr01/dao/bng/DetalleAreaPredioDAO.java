package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio;

import java.math.BigInteger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


/**
 * Clase que contiene todos las propiedades de DetalleAreaPredioDAO.
 *
 * @author mblanco
 */
public class DetalleAreaPredioDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_DETALLE_AREA_PREDIO,ID_AREA_PREDIO,ID_TIPO_AREA,ID_UNIDAD_MEDIDA,AREA,ID_CIRCULO,ID_MATRICULA,"
		+ "ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_BNG_DETALLE_AREA_PREDIO"
		+ " WHERE ID_DETALLE_AREA_PREDIO = ? AND ID_AREA_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? ";

	/** Constante cs_FIND_BY_ID_AREA_PREDIO. */
	private static final String cs_FIND_BY_ID_AREA_PREDIO = "SELECT ID_DETALLE_AREA_PREDIO,ID_AREA_PREDIO,ID_TIPO_AREA,ID_UNIDAD_MEDIDA,AREA,ID_CIRCULO,ID_MATRICULA,"
		+ "ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_BNG_DETALLE_AREA_PREDIO"
		+ " WHERE ID_AREA_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? ";

	/** Constante cs_FIND_BY_ID_AREA_PREDIO_TIPO. */
	private static final String cs_FIND_BY_ID_AREA_PREDIO_TIPO = "SELECT ID_DETALLE_AREA_PREDIO,ID_AREA_PREDIO,ID_TIPO_AREA,ID_UNIDAD_MEDIDA,AREA,ID_CIRCULO,ID_MATRICULA,"
		+ "ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_BNG_DETALLE_AREA_PREDIO"
		+ " WHERE ID_AREA_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TIPO_AREA = ?";

	/** Constante cs_FIND_MAX_ID_DETALLE_AREA_PREDIO. */
	private static final String cs_FIND_MAX_ID_DETALLE_AREA_PREDIO = "SELECT MAX(ID_DETALLE_AREA_PREDIO) ID_DETALLE_AREA_PREDIO FROM SDB_BNG_DETALLE_AREA_PREDIO WHERE ID_AREA_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_FIND_BY_ID_AREA_PREDIO_TIPO_UNIDAD. */
	private static final String cs_FIND_BY_ID_AREA_PREDIO_TIPO_UNIDAD = "SELECT ID_DETALLE_AREA_PREDIO,ID_AREA_PREDIO,ID_TIPO_AREA,ID_UNIDAD_MEDIDA,AREA,ID_CIRCULO,ID_MATRICULA,"
		+ "ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_BNG_DETALLE_AREA_PREDIO"
		+ " WHERE ID_AREA_PREDIO = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TIPO_AREA = ? AND ID_UNIDAD_MEDIDA = ?";

	/**
	 * Retorna el valor del objeto de tipo Collection de DetalleAreaPredio filtrado por el id area predio, id circulo, id matricula
	 * y id tipo de area.
	 *
	 * @param adap_param correspondiente al valor del tipo de objeto DetalleAreaPredio
	 * @return devuelve el valor del objeto collection de DetalleAreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAreaPredio
	 */
	public Collection<DetalleAreaPredio> findAllByIdAreaPredioTipo(DetalleAreaPredio adap_param)
	    throws B2BException
	{
		Collection<DetalleAreaPredio> lcadap_areas;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcadap_areas     = new ArrayList<DetalleAreaPredio>();
		lps_ps           = null;
		lrs_rs           = null;

		if(adap_param != null)
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID_AREA_PREDIO_TIPO);

				lps_ps.setString(li_count++, adap_param.getIdAreaPredio());
				lps_ps.setString(li_count++, adap_param.getIdCirculo());
				setLong(lps_ps, adap_param.getIdMatricula(), li_count++);
				lps_ps.setString(li_count++, adap_param.getIdTipoArea());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcadap_areas.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdAreaPredioTipo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcadap_areas.isEmpty())
			lcadap_areas = null;

		return lcadap_areas;
	}

	/**
	 * Retorna el valor del objeto de tipo HashMap de String, DetalleAreaPredio filtrado por el id area predio.
	 *
	 * @param adap_param correspondiente al valor del tipo de objeto DetalleAreaPredio
	 * @return devuelve el valor del objeto hash map
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public HashMap<String, DetalleAreaPredio> findAllByIdAreaPredioTipoDetalle(DetalleAreaPredio adap_param)
	    throws B2BException
	{
		HashMap<String, DetalleAreaPredio> lhmsadap_areas;
		PreparedStatement                  lps_ps;
		ResultSet                          lrs_rs;

		lhmsadap_areas     = new HashMap<String, DetalleAreaPredio>();
		lps_ps             = null;
		lrs_rs             = null;

		if(adap_param != null)
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID_AREA_PREDIO_TIPO);

				lps_ps.setString(li_count++, adap_param.getIdAreaPredio());
				lps_ps.setString(li_count++, adap_param.getIdCirculo());
				setLong(lps_ps, adap_param.getIdMatricula(), li_count++);
				lps_ps.setString(li_count++, adap_param.getIdTipoArea());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
				{
					DetalleAreaPredio ladap_area;

					ladap_area = getObjetFromResultSet(lrs_rs);

					if(ladap_area != null)
					{
						String ls_idDetalleAreaPredio;

						ls_idDetalleAreaPredio = ladap_area.getIdDetalleAreaPredio();

						if(StringUtils.isValidString(ls_idDetalleAreaPredio))
							lhmsadap_areas.put(ls_idDetalleAreaPredio, ladap_area);
					}
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdAreaPredioTipoDetalle", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lhmsadap_areas.isEmpty())
			lhmsadap_areas = null;

		return lhmsadap_areas;
	}

	/**
	 * Retorna el valor del objeto de tipo DetalleAreaPredio filtrado por el id circulo y id unidad de medida.
	 *
	 * @param as_idAreaPredio correspondiente al valor del tipo de objeto String
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto Long
	 * @param as_idTipoArea correspondiente al valor del tipo de objeto String
	 * @param as_idUnidadMedida correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto detalle area predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAreaPredio
	 */
	public DetalleAreaPredio findAllByIdAreaPredioTipoUnidad(
	    String as_idAreaPredio, String as_idCirculo, Long al_idMatricula, String as_idTipoArea, String as_idUnidadMedida
	)
	    throws B2BException
	{
		DetalleAreaPredio ldap_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ldap_return     = null;
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idAreaPredio) && StringUtils.isValidString(as_idCirculo)
				    && StringUtils.isValidString(as_idTipoArea) && StringUtils.isValidString(as_idUnidadMedida)
				    && NumericUtils.isValidLong(al_idMatricula)
			)
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID_AREA_PREDIO_TIPO_UNIDAD);

				lps_ps.setString(li_count++, as_idAreaPredio);
				lps_ps.setString(li_count++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_count++);
				lps_ps.setString(li_count++, as_idTipoArea);
				lps_ps.setString(li_count++, as_idUnidadMedida);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ldap_return = getObjetFromResultSet(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllByIdAreaPredioTipoUnidad", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ldap_return;
	}

	/**
	 * Retorna el valor del objeto de tipo DetalleAreaPredio filtrado por el id detalle area predio, id area predio, id circulo,
	 * id matricula.
	 *
	 * @param adap_param correspondiente al valor del tipo de objeto DetalleAreaPredio
	 * @return devuelve el valor del objeto detalle area predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAreaPredio
	 */
	public DetalleAreaPredio findById(DetalleAreaPredio adap_param)
	    throws B2BException
	{
		DetalleAreaPredio ladap_detalleAreaPredio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ladap_detalleAreaPredio     = null;
		lps_ps                      = null;
		lrs_rs                      = null;

		if(adap_param != null)
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_count++, adap_param.getIdDetalleAreaPredio());
				lps_ps.setString(li_count++, adap_param.getIdAreaPredio());
				lps_ps.setString(li_count++, adap_param.getIdCirculo());
				setLong(lps_ps, adap_param.getIdMatricula(), li_count++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ladap_detalleAreaPredio = getObjetFromResultSet(lrs_rs);
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

		return ladap_detalleAreaPredio;
	}

	/**
	 * Find by id area predio.
	 *
	 * @param adap_param de adap param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DetalleAreaPredio> findByIdAreaPredio(DetalleAreaPredio adap_param)
		    throws B2BException
		{
		return adap_param != null ? findByIdAreaPredio(adap_param.getIdAreaPredio(), adap_param.getIdCirculo(), NumericUtils.getLong(adap_param.getIdMatricula())) : null;
		}
	
	/**
	 * Retorna el valor del objeto de tipo Collection de DetalleAreaPredio filtrado por id area predio, id circulo, id matricula.
	 *
	 * @param as_idAreaPredio de as id area predio
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @return devuelve el valor del objeto collection de DetalleAreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAreaPredio
	 */
	public Collection<DetalleAreaPredio> findByIdAreaPredio(String as_idAreaPredio, String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		Collection<DetalleAreaPredio> lcadap_Predio;

		lcadap_Predio     = new ArrayList<DetalleAreaPredio>();

		if(StringUtils.isValidString(as_idAreaPredio) && StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement             lps_ps;
			ResultSet                     lrs_rs;
			
			lps_ps            = null;
			lrs_rs            = null;
			
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID_AREA_PREDIO);

				lps_ps.setString(li_count++, as_idAreaPredio);
				lps_ps.setString(li_count++, as_idCirculo);
				lps_ps.setLong(li_count++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcadap_Predio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdAreaPredio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcadap_Predio.isEmpty())
			lcadap_Predio = null;

		return lcadap_Predio;
	}

	/**
	 * Retorna el valor del objeto de tipo DetalleAreaPredio.
	 *
	 * @param adap_param correspondiente al valor del tipo de objeto DetalleAreaPredio
	 * @return devuelve el valor del objeto detalle area predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DetalleAreaPredio
	 */
	public DetalleAreaPredio findByIdAreaPredioTipo(DetalleAreaPredio adap_param)
	    throws B2BException
	{
		DetalleAreaPredio ladap_area;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ladap_area     = null;
		lps_ps         = null;
		lrs_rs         = null;

		if(adap_param != null)
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID_AREA_PREDIO_TIPO);

				lps_ps.setString(li_count++, adap_param.getIdAreaPredio());
				lps_ps.setString(li_count++, adap_param.getIdCirculo());
				setLong(lps_ps, adap_param.getIdMatricula(), li_count++);
				lps_ps.setString(li_count++, adap_param.getIdTipoArea());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ladap_area = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdAreaPredioTipo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ladap_area;
	}

	/**
	 * Retorna el valor del objeto de tipo BigInteger correspondiente al maximo id de area predio.
	 *
	 * @param adap_param correspondiente al valor del tipo de objeto DetalleAreaPredio
	 * @return devuelve el valor del objeto big integer
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public BigInteger findByMaxIdDetalleAreaPredio(DetalleAreaPredio adap_param)
	    throws B2BException
	{
		BigInteger        lbi_idDetalleAreaPredio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lbi_idDetalleAreaPredio     = null;
		lps_ps                      = null;
		lrs_rs                      = null;

		if(adap_param != null)
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_MAX_ID_DETALLE_AREA_PREDIO);

				lps_ps.setString(li_count++, adap_param.getIdAreaPredio());
				lps_ps.setString(li_count++, adap_param.getIdCirculo());
				setLong(lps_ps, adap_param.getIdMatricula(), li_count++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
				{
					String ls_idDetalleAreaPredio;

					ls_idDetalleAreaPredio      = lrs_rs.getString("ID_DETALLE_AREA_PREDIO");
					lbi_idDetalleAreaPredio     = StringUtils.isValidString(ls_idDetalleAreaPredio)
						? NumericUtils.getBigInteger(ls_idDetalleAreaPredio) : BigInteger.ZERO;
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByMaxIdDetalleAreaPredio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lbi_idDetalleAreaPredio;
	}

	/**
	 * Retorna el valor de DetalleAreaPredio.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de DetalleAreaPredio
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see DetalleAreaPredio
	 */
	private DetalleAreaPredio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		DetalleAreaPredio ladap_areaPredio;

		ladap_areaPredio = new DetalleAreaPredio();

		ladap_areaPredio.setIdDetalleAreaPredio(ars_rs.getString("ID_DETALLE_AREA_PREDIO"));
		ladap_areaPredio.setIdAreaPredio(ars_rs.getString("ID_AREA_PREDIO"));
		ladap_areaPredio.setIdTipoArea(ars_rs.getString("ID_TIPO_AREA"));
		ladap_areaPredio.setIdUnidadMedida(ars_rs.getString("ID_UNIDAD_MEDIDA"));
		ladap_areaPredio.setArea(getDouble(ars_rs, "AREA"));
		ladap_areaPredio.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		ladap_areaPredio.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		ladap_areaPredio.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ladap_areaPredio.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ladap_areaPredio.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ladap_areaPredio.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ladap_areaPredio.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ladap_areaPredio.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return ladap_areaPredio;
	}
}
