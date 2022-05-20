package co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2;

public class SBB_EF_OperacionesFinancierasProxy implements co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2.SBB_EF_OperacionesFinancieras_PortType {
  private String _endpoint = null;
  private co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2.SBB_EF_OperacionesFinancieras_PortType sBB_EF_OperacionesFinancieras_PortType = null;
  
  public SBB_EF_OperacionesFinancierasProxy() {
    _initSBB_EF_OperacionesFinancierasProxy();
  }
  
  public SBB_EF_OperacionesFinancierasProxy(String endpoint) {
    _endpoint = endpoint;
    _initSBB_EF_OperacionesFinancierasProxy();
  }
  
  private void _initSBB_EF_OperacionesFinancierasProxy() {
    try {
      sBB_EF_OperacionesFinancieras_PortType = (new co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2.SBB_EF_OperacionesFinancieras_ServiceLocator()).getSBB_EF_OperacionesFinancierasPort();
      if (sBB_EF_OperacionesFinancieras_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sBB_EF_OperacionesFinancieras_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sBB_EF_OperacionesFinancieras_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sBB_EF_OperacionesFinancieras_PortType != null)
      ((javax.xml.rpc.Stub)sBB_EF_OperacionesFinancieras_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public co.gov.supernotariado.www.services.bachue.ef.operacionesfinancieras.v2.SBB_EF_OperacionesFinancieras_PortType getSBB_EF_OperacionesFinancieras_PortType() {
    if (sBB_EF_OperacionesFinancieras_PortType == null)
      _initSBB_EF_OperacionesFinancierasProxy();
    return sBB_EF_OperacionesFinancieras_PortType;
  }
  
  public co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultarestadoliquidacion.v2.TipoSalidaConsultarEstadoLiquidacion consultarEstadoLiquidacion(co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultarestadoliquidacion.v2.TipoEntradaConsultarEstadoLiquidacion entrada) throws java.rmi.RemoteException{
    if (sBB_EF_OperacionesFinancieras_PortType == null)
      _initSBB_EF_OperacionesFinancierasProxy();
    return sBB_EF_OperacionesFinancieras_PortType.consultarEstadoLiquidacion(entrada);
  }
  
  public co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultartarifaservicio.v2.TipoSalidaConsultarTarifaServicio consultarTarifaServicio(co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.consultartarifaservicio.v2.TipoEntradaConsultarTarifaServicio entrada) throws java.rmi.RemoteException{
    if (sBB_EF_OperacionesFinancieras_PortType == null)
      _initSBB_EF_OperacionesFinancierasProxy();
    return sBB_EF_OperacionesFinancieras_PortType.consultarTarifaServicio(entrada);
  }
  
  public co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.generarliquidacion.v2.TipoSalidaGenerarLiquidacion generarLiquidacion(co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.generarliquidacion.v2.TipoEntradaGenerarLiquidacion entrada) throws java.rmi.RemoteException{
    if (sBB_EF_OperacionesFinancieras_PortType == null)
      _initSBB_EF_OperacionesFinancierasProxy();
    return sBB_EF_OperacionesFinancieras_PortType.generarLiquidacion(entrada);
  }
  
  public co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.notificarpago.v2.TipoSalidaNotificarPago notificarPago(co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.notificarpago.v2.TipoEntradaNotificarPago entrada) throws java.rmi.RemoteException{
    if (sBB_EF_OperacionesFinancieras_PortType == null)
      _initSBB_EF_OperacionesFinancierasProxy();
    return sBB_EF_OperacionesFinancieras_PortType.notificarPago(entrada);
  }
  
  public co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.obtenerpdfliquidacion.v2.TipoSalidaObtenerPDFLiquidacion obtenerPDFLiquidacion(co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.obtenerpdfliquidacion.v2.TipoEntradaObtenerPDFLiquidacion entrada) throws java.rmi.RemoteException{
    if (sBB_EF_OperacionesFinancieras_PortType == null)
      _initSBB_EF_OperacionesFinancierasProxy();
    return sBB_EF_OperacionesFinancieras_PortType.obtenerPDFLiquidacion(entrada);
  }
  
  public co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.actualizardatossolicitante.v2.TipoSalidaActualizarDatosSolicitante actualizarDatosSolicitante(co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.actualizardatossolicitante.v2.TipoEntradaActualizarDatosSolicitante entrada) throws java.rmi.RemoteException{
    if (sBB_EF_OperacionesFinancieras_PortType == null)
      _initSBB_EF_OperacionesFinancierasProxy();
    return sBB_EF_OperacionesFinancieras_PortType.actualizarDatosSolicitante(entrada);
  }
  
  
}