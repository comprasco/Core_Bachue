package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_SOLICITUD_MATRICULA_ACTO
 *
 * @author mblanco
 */
public class SolicitudMatriculaActoDAO extends BaseDAO implements IBase<SolicitudMatriculaActo>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, ID_ACTO, FECHA,  ID_TURNO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ESTADO, MOTIVO_TRAMITE FROM SDB_ACC_SOLICITUD_MATRICULA_ACTO WHERE ID_SOLICITUD=? AND ID_CIRCULO=? AND ID_MATRICULA=? AND ID_ACTO=?";

	/** Constante cs_FIND_BY_ID_TURNO. */
	private static final String cs_FIND_BY_ID_TURNO = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, ID_ACTO, FECHA,  ID_TURNO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ESTADO, MOTIVO_TRAMITE FROM SDB_ACC_SOLICITUD_MATRICULA_ACTO WHERE ID_TURNO=?";

	/** Constante cs_FIND_BY_ID_ACTO. */
	private static final String cs_FIND_BY_ID_ACTO = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, ID_ACTO, FECHA,  ID_TURNO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ESTADO, MOTIVO_TRAMITE FROM SDB_ACC_SOLICITUD_MATRICULA_ACTO WHERE ID_ACTO=?";

	/** Constante cs_FIND_BY_ID_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_ID_CIRCULO_MATRICULA = "SELECT * FROM SDB_ACC_SOLICITUD_MATRICULA_ACTO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ESTADO = 'A'";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, ID_ACTO, FECHA,  ID_TURNO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ESTADO, MOTIVO_TRAMITE FROM SDB_ACC_SOLICITUD_MATRICULA_ACTO WHERE ID_SOLICITUD=? AND ID_CIRCULO=?";

	/** Constante cs_FIND_BY_ID_SOLICITUD_NO_ESTADO_I. */
	private static final String cs_FIND_BY_ID_SOLICITUD_NO_ESTADO_I = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, ID_ACTO, FECHA,  ID_TURNO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ESTADO, MOTIVO_TRAMITE FROM SDB_ACC_SOLICITUD_MATRICULA_ACTO WHERE ID_SOLICITUD=? AND ID_CIRCULO=? AND ESTADO != 'I'";

	/** Constante cs_FIND_BY_ID_SOLICITUD_CIRCULO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_CIRCULO = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, ID_ACTO, FECHA,  ID_TURNO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION, ESTADO, MOTIVO_TRAMITE FROM SDB_ACC_SOLICITUD_MATRICULA_ACTO WHERE ID_SOLICITUD=? AND ID_CIRCULO=?";

	/** Constante cs_FIND_ALL_ID_TURNO. */
	private static final String cs_FIND_ALL_ID_TURNO = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, ID_ACTO, FECHA,  FROM SDB_ACC_SOLICITUD_MATRICULA_ACTO WHERE ID_TURNO=(SELECT ID_TURNO	FROM SDB_ACC_TURNO_HISTORIA TH WHERE TH.ID_TURNO_HISTORIA = ?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SOLICITUD_MATRICULA_ACTO SET FECHA=SYSTIMESTAMP,  ID_TURNO=?,ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?, ESTADO=?, MOTIVO_TRAMITE=? WHERE ID_SOLICITUD=? AND ID_CIRCULO=? AND ID_MATRICULA=? AND ID_ACTO=? ";

	/**Constante cs_INACTIVAR_ACTOS*/
	private static final String cs_UPDATE_INACTIVAR_ACTOS = "UPDATE SDB_ACC_SOLICITUD_MATRICULA_ACTO SET ID_USUARIO_MODIFICACION = ?,FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ?, ESTADO = 'I' WHERE ID_SOLICITUD=? AND ID_CIRCULO=? AND ID_MATRICULA=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SOLICITUD_MATRICULA_ACTO(ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, ID_ACTO, FECHA, ID_TURNO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ESTADO, MOTIVO_TRAMITE) VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?, ?, SYSTIMESTAMP, ?, ?, ?)";

	/** Constante cs_DELETE. */
	private static final String cs_DELETE = "DELETE FROM SDB_ACC_SOLICITUD_MATRICULA_ACTO WHERE ID_SOLICITUD=? AND ID_CIRCULO=? AND ID_MATRICULA=? AND ID_ACTO=?";

	/** Constante cs_DELETE_BY_ID_ACTO. */
	private static final String cs_DELETE_BY_ID_ACTO = "DELETE FROM SDB_ACC_SOLICITUD_MATRICULA_ACTO WHERE ID_ACTO=?";

	/** Constante cs_DATA_GRUPO_JURIDICA_BY_SOLICITUD. */
	private static final String cs_DATA_GRUPO_JURIDICA_BY_SOLICITUD = "   SELECT  * FROM SDB_ACC_SOLICITUD_MATRICULA_ACTO ASM    INNER JOIN SDB_ACC_ACTO PNJ ON PNJ.ID_ACTO = ASM.ID_ACTO"
		+ "  INNER JOIN SDB_PNG_NATURALEZA_JURIDICA PNJ ON PNJ.ID_NATURALEZA_JURIDICA = PNJ.ID_TIPO_ACTO WHERE ASM.ID_CIRCULO = ?  AND ASM.ID_SOLICITUD= ?";

	/** Constante cs_DELETE_BY_SOLICITUD. */
	private static final String cs_DELETE_BY_SOLICITUD = "DELETE FROM SDB_ACC_SOLICITUD_MATRICULA_ACTO WHERE ID_SOLICITUD = ? ";

	/**
	 * Retorna el valor del objeto de Collection de SolicitudMatriculaActo filtrado por grupo juridica.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto SolicitudMatriculaActo
	 * @return devuelve el valor de Collection de SolicitudMatriculaActo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatriculaActo
	 */
	public Collection<SolicitudMatriculaActo> dataBySolicitud(SolicitudMatriculaActo at_param)
	    throws B2BException
	{
		Collection<SolicitudMatriculaActo> ls_object;
		PreparedStatement                  lps_ps;
		ResultSet                          lrs_rs;

		int li_column;

		li_column     = 1;

		ls_object     = new ArrayList<SolicitudMatriculaActo>();
		lps_ps        = null;
		lrs_rs        = null;

		if(at_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DATA_GRUPO_JURIDICA_BY_SOLICITUD);

				lps_ps.setString(li_column++, at_param.getIdCirculo());
				lps_ps.setString(li_column++, at_param.getIdSolicitud());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ls_object.add(getObjetFromResultSetInner(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "dataBySolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(ls_object))
			ls_object = null;

		return ls_object;
	}

	/**
	 * Elimina el registro en la tabla.
	 *
	 * @param asma_param correspondiente al valor del tipo de objeto SolicitudMatriculaActo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void delete(SolicitudMatriculaActo asma_param)
	    throws B2BException
	{
		if(asma_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setString(li_column++, asma_param.getIdSolicitud());
				lps_ps.setString(li_column++, asma_param.getIdCirculo());
				lps_ps.setLong(li_column++, asma_param.getIdMatricula());
				lps_ps.setString(li_column++, asma_param.getIdActo());

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
	 * Elimina el registro por id acto.
	 *
	 * @param asma_param correspondiente al valor del tipo de objeto SolicitudMatriculaActo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteByIdActo(SolicitudMatriculaActo asma_param)
	    throws B2BException
	{
		if(asma_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE_BY_ID_ACTO);

				lps_ps.setString(li_column++, asma_param.getIdActo());

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
	 * Elimina el registro por solicitud.
	 *
	 * @param asma_solicitudMatriculaActo correspondiente al valor del tipo de objeto SolicitudMatriculaActo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteBySolicitud(SolicitudMatriculaActo asma_solicitudMatriculaActo)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		int               li_column;

		li_column     = 1;

		lps_ps = null;

		if(asma_solicitudMatriculaActo != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE_BY_SOLICITUD);

				lps_ps.setString(li_column++, asma_solicitudMatriculaActo.getIdSolicitud());

				lps_ps.executeQuery();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteBySolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Elimina el registro por solicitud circulo matricula.
	 *
	 * @param asma_solicitudMatriculaActo correspondiente al valor del tipo de objeto SolicitudMatriculaActo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteBySolicitudCirculoMatricula(SolicitudMatriculaActo asma_solicitudMatriculaActo)
	    throws B2BException
	{
		deleteBySolicitudCirculoMatricula(asma_solicitudMatriculaActo, true);
	}

	/**
	 * Elimina el registro por solicitud circulo matricula.
	 *
	 * @param asma_solicitudMatriculaActo correspondiente al valor del tipo de objeto SolicitudMatriculaActo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteBySolicitudCirculoMatricula(
	    SolicitudMatriculaActo asma_solicitudMatriculaActo, boolean ab_conMatricula
	)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		int               li_column;

		li_column     = 1;

		lps_ps = null;

		if(asma_solicitudMatriculaActo != null)
		{
			try
			{
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder(cs_DELETE_BY_SOLICITUD);

				if(!ab_conMatricula)
					lsb_sb.append(" AND ID_CIRCULO = ?");
				else
					lsb_sb.append(" AND ID_CIRCULO = ? AND ID_MATRICULA = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, asma_solicitudMatriculaActo.getIdSolicitud());
				lps_ps.setString(li_column++, asma_solicitudMatriculaActo.getIdCirculo());

				if(ab_conMatricula)
					lps_ps.setLong(li_column++, asma_solicitudMatriculaActo.getIdMatricula());

				lps_ps.executeQuery();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteBySolicitudCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Obtiene los registros que coincidan con el id solicitud, el id círculo y el id matrícula especificados.
	 *
	 * @param asma_param Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @param ab_estado correspondiente al valor del tipo de objeto boolean
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatriculaActo
	 */
	public Collection<SolicitudMatriculaActo> findAllByIdSolicitud(
	    SolicitudMatriculaActo asma_param, boolean ab_estado
	)
	    throws B2BException
	{
		return (asma_param != null)
		? findAllByIdSolicitud(
		    asma_param.getIdSolicitud(), asma_param.getIdCirculo(), asma_param.getIdMatricula(), ab_estado, false
		) : null;
	}

	/**
	 * Método de busqueda de los actos de la matricula por la solicitud
	 * @param as_idSolicitud
	 * @param as_idCirculo
	 * @param al_idMatricula
	 * @param ab_estado
	 * @return
	 * @throws B2BException
	 */
	public Collection<SolicitudMatriculaActo> findAllByIdSolicitud(
	    String as_idSolicitud, String as_idCirculo, long al_idMatricula, boolean ab_estado
	)
	    throws B2BException
	{
		return findAllByIdSolicitud(as_idSolicitud, as_idCirculo, al_idMatricula, ab_estado, false);
	}

	/**
	 * Obtiene los registros que coincidan con el id solicitud, el id círculo y el id matrícula especificados.
	 *
	 * @param as_idSolicitud Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @param as_idCirculo Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @param al_idMatricula Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @param ab_estado correspondiente al valor del tipo de objeto boolean
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatriculaActo
	 */
	public Collection<SolicitudMatriculaActo> findAllByIdSolicitud(
	    String as_idSolicitud, String as_idCirculo, long al_idMatricula, boolean ab_estado, boolean ab_sinMatricula
	)
	    throws B2BException
	{
		Collection<SolicitudMatriculaActo> ls_object;

		ls_object = new ArrayList<SolicitudMatriculaActo>();

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;
				li_count = 1;

				StringBuilder lsb_sb;

				if(ab_estado)
					lsb_sb = new StringBuilder(cs_FIND_BY_ID_SOLICITUD);
				else
					lsb_sb = new StringBuilder(cs_FIND_BY_ID_SOLICITUD_NO_ESTADO_I);

				if(!ab_sinMatricula)
					lsb_sb.append(" AND ID_MATRICULA = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_count++, as_idSolicitud);
				lps_ps.setString(li_count++, as_idCirculo);

				if(!ab_sinMatricula && (al_idMatricula > 0))
					lps_ps.setLong(li_count++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ls_object.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(ls_object))
			ls_object = null;

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de Collection de SolicitudMatriculaActo filtrado por id turno.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto SolicitudMatriculaActo
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatriculaActo
	 */
	public Collection<SolicitudMatriculaActo> findAllByIdTurno(SolicitudMatriculaActo at_param)
	    throws B2BException
	{
		Collection<SolicitudMatriculaActo> ls_object;
		PreparedStatement                  lps_ps;
		ResultSet                          lrs_rs;

		ls_object     = new ArrayList<SolicitudMatriculaActo>();
		lps_ps        = null;
		lrs_rs        = null;

		if(at_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_ID_TURNO);

				lps_ps.setString(1, at_param.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ls_object.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(ls_object))
			ls_object = null;

		return ls_object;
	}

	/** {@inheritdoc} */
	@Override
	public SolicitudMatriculaActo findById(SolicitudMatriculaActo at_param)
	    throws B2BException
	{
		SolicitudMatriculaActo ls_object;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(at_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, at_param.getIdSolicitud());
				lps_ps.setString(2, at_param.getIdCirculo());
				lps_ps.setLong(3, at_param.getIdMatricula());
				lps_ps.setString(4, at_param.getIdActo());

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
	 * Retorna el valor del objeto de SolicitudMatriculaActo.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto SolicitudMatriculaActo
	 * @return devuelve el valor de SolicitudMatriculaActo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatriculaActo
	 */
	public SolicitudMatriculaActo findByIdActo(SolicitudMatriculaActo at_param)
	    throws B2BException
	{
		SolicitudMatriculaActo ls_object;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(at_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ACTO);

				lps_ps.setString(1, at_param.getIdActo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdActo", lse_e);

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
	 * Find by id circulo matricula.
	 *
	 * @param asma_param de asma param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatriculaActo> findByIdCirculoMatricula(String ls_idCirculo, long ll_idMatricula)
	    throws B2BException
	{
		Collection<SolicitudMatriculaActo> lcsma_object;

		lcsma_object = new ArrayList<SolicitudMatriculaActo>();

		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			int           li_contador;
			StringBuilder lsb_query;

			li_contador     = 1;
			lsb_query       = new StringBuilder();

			lsb_query.append(cs_FIND_BY_ID_CIRCULO_MATRICULA);

			lps_ps = getConnection().prepareStatement(lsb_query.toString());

			lps_ps.setString(li_contador++, ls_idCirculo);
			lps_ps.setLong(li_contador++, ll_idMatricula);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcsma_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdCirculoMatricula", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcsma_object.isEmpty())
			lcsma_object = null;

		return lcsma_object;
	}

	public Collection<SolicitudMatriculaActo> findByIdSolicitudCirculo(
	    SolicitudMatriculaActo asma_param, boolean ab_estadoActivo
	)
	    throws B2BException
	{
		return findByIdSolicitudCirculo(asma_param, ab_estadoActivo, false);
	}

	/**
	 * Consulta en la tabla todos los registros para un circulo y solicitud específicos.
	 *
	 * @param asma_param Objeto contenedor de los datos a utilizar como filtro en la consulta
	 * @param ab_estadoActivo true si se van a buscar solo registros que tenga estado activo
	 * @param ab_matricula true si se van a buscar los registros de una matrícula especifica
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatriculaActo
	 */
	public Collection<SolicitudMatriculaActo> findByIdSolicitudCirculo(
	    SolicitudMatriculaActo asma_param, boolean ab_estadoActivo, boolean ab_matricula
	)
	    throws B2BException
	{
		Collection<SolicitudMatriculaActo> lcsma_object;

		lcsma_object = new ArrayList<SolicitudMatriculaActo>();

		if(asma_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_contador;
				StringBuilder lsb_query;

				li_contador     = 1;
				lsb_query       = new StringBuilder();

				lsb_query.append(cs_FIND_BY_ID_SOLICITUD_CIRCULO);

				if(ab_estadoActivo)
					lsb_query.append(" AND ESTADO = 'A' ");

				if(ab_matricula)
					lsb_query.append(" AND ID_MATRICULA = ? ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_contador++, asma_param.getIdSolicitud());
				lps_ps.setString(li_contador++, asma_param.getIdCirculo());

				if(ab_matricula)
					lps_ps.setLong(li_contador++, asma_param.getIdMatricula());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsma_object.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudCirculo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcsma_object.isEmpty())
			lcsma_object = null;

		return lcsma_object;
	}

	/**
	 * Retorna el valor del objeto de SolicitudMatriculaActo.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto SolicitudMatriculaActo
	 * @return devuelve el valor de SolicitudMatriculaActo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudMatriculaActo
	 */
	public SolicitudMatriculaActo findByIdTurno(SolicitudMatriculaActo at_param)
	    throws B2BException
	{
		SolicitudMatriculaActo ls_object;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(at_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO);

				lps_ps.setString(1, at_param.getIdTurno());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurno", lse_e);

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
	 * Método de transacción con la base de datos para la inactivacion de actos asociados a una solicitud y matricula
	 * @param at_param con los parametros de cambio
	 * @throws B2BException
	 */
	public void inactivarActos(SolicitudMatriculaActo at_param)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_INACTIVAR_ACTOS);

				lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, at_param.getIpModificacion());
				lps_ps.setString(li_column++, at_param.getIdSolicitud());
				lps_ps.setString(li_column++, at_param.getIdCirculo());
				lps_ps.setLong(li_column++, at_param.getIdMatricula());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "inactivarActos", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/** {@inheritdoc} */
	@Override
	public void insertOrUpdate(SolicitudMatriculaActo at_param, boolean ab_query)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdSolicitud());
					lps_ps.setString(li_column++, at_param.getIdCirculo());
					lps_ps.setLong(li_column++, at_param.getIdMatricula());
					lps_ps.setString(li_column++, at_param.getIdActo());
					lps_ps.setString(li_column++, at_param.getIdTurno());
					lps_ps.setString(li_column++, at_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, at_param.getIpCreacion());
					lps_ps.setString(li_column++, at_param.getEstado());
					lps_ps.setString(li_column++, at_param.getMotivoTramite());
				}

				if(!ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdTurno());
					lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, at_param.getIpModificacion());
					lps_ps.setString(li_column++, at_param.getEstado());
					lps_ps.setString(li_column++, at_param.getMotivoTramite());
					lps_ps.setString(li_column++, at_param.getIdSolicitud());
					lps_ps.setString(li_column++, at_param.getIdCirculo());
					lps_ps.setLong(li_column++, at_param.getIdMatricula());
					lps_ps.setString(li_column++, at_param.getIdActo());
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
			}
		}
	}

	/**
	 * Retorna el valor de SolicitudMatriculaActo
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de SolicitudMatriculaActo
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SolicitudMatriculaActo getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		SolicitudMatriculaActo lsma_sma;

		lsma_sma = new SolicitudMatriculaActo();

		lsma_sma.setIdSolicitud(lrs_rs.getString("ID_SOLICITUD"));
		lsma_sma.setIdCirculo(lrs_rs.getString("ID_CIRCULO"));
		lsma_sma.setIdMatricula(lrs_rs.getLong("ID_MATRICULA"));
		lsma_sma.setIdActo(lrs_rs.getString("ID_ACTO"));
		lsma_sma.setFecha(lrs_rs.getTimestamp("FECHA"));
		lsma_sma.setIdTurno(lrs_rs.getString("ID_TURNO"));
		lsma_sma.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		lsma_sma.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		lsma_sma.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		lsma_sma.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		lsma_sma.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		lsma_sma.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));
		lsma_sma.setEstado(lrs_rs.getString("ESTADO"));
		lsma_sma.setMotivoTramite(lrs_rs.getString("MOTIVO_TRAMITE"));

		return lsma_sma;
	}

	/**
	 * Retorna el valor de SolicitudMatriculaActo
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de SolicitudMatriculaActo
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SolicitudMatriculaActo getObjetFromResultSetInner(ResultSet lrs_rs)
	    throws SQLException
	{
		SolicitudMatriculaActo lsma_sma;

		lsma_sma = new SolicitudMatriculaActo();

		lsma_sma.setIdGrupoJuridica(lrs_rs.getString("ID_GRUPO_NAT_JURIDICA"));

		return lsma_sma;
	}
}
