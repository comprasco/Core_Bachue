package com.bachue.snr.biometrico.servicios.ws;

public class BiometriaWSProxy implements com.bachue.snr.biometrico.servicios.ws.BiometriaWS {
  private String _endpoint = null;
  private com.bachue.snr.biometrico.servicios.ws.BiometriaWS biometriaWS = null;
  
  public BiometriaWSProxy() {
    _initBiometriaWSProxy();
  }
  
  public BiometriaWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initBiometriaWSProxy();
  }
  
  private void _initBiometriaWSProxy() {
    try {
      biometriaWS = (new com.bachue.snr.biometrico.servicios.ws.BiometriaControllerLocator()).getBiometriaWSPort();
      if (biometriaWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)biometriaWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)biometriaWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (biometriaWS != null)
      ((javax.xml.rpc.Stub)biometriaWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.bachue.snr.biometrico.servicios.ws.BiometriaWS getBiometriaWS() {
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS;
  }
  
  public com.bachue.snr.biometrico.servicios.ws.ConstantesSalidaDTO obtenerConstantes() throws java.rmi.RemoteException{
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS.obtenerConstantes();
  }
  
  public com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO borrarHuellas(com.bachue.snr.biometrico.servicios.ws.BorrarHuellasDTO entradaUsuario) throws java.rmi.RemoteException{
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS.borrarHuellas(entradaUsuario);
  }
  
  public com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO enrolarUsuario(com.bachue.snr.biometrico.servicios.ws.HuellaDTO[] entradaHuella) throws java.rmi.RemoteException{
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS.enrolarUsuario(entradaHuella);
  }
  
  public com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO registrarEvento(com.bachue.snr.biometrico.servicios.ws.LogDTO entradaLog) throws java.rmi.RemoteException{
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS.registrarEvento(entradaLog);
  }
  
  public com.bachue.snr.biometrico.servicios.ws.SesionDTO consultarSesion(com.bachue.snr.biometrico.servicios.ws.SesionEntradaDTO entradaSesion) throws java.rmi.RemoteException{
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS.consultarSesion(entradaSesion);
  }
  
  public com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO obtenerTipoSegundoFactor(com.bachue.snr.biometrico.servicios.ws.UsuarioEntradaDTO entradaUsuario) throws java.rmi.RemoteException{
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS.obtenerTipoSegundoFactor(entradaUsuario);
  }
  
  public com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO crearUsuario(com.bachue.snr.biometrico.servicios.ws.UsuarioDTO entradaUsuario) throws java.rmi.RemoteException{
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS.crearUsuario(entradaUsuario);
  }
  
  public com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO actualizarClave(com.bachue.snr.biometrico.servicios.ws.UsuarioDTO entradaClave) throws java.rmi.RemoteException{
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS.actualizarClave(entradaClave);
  }
  
  public com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO obtenerUsuario(com.bachue.snr.biometrico.servicios.ws.UsuarioEntradaDTO entradaUsuario) throws java.rmi.RemoteException{
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS.obtenerUsuario(entradaUsuario);
  }
  
  public com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO verificarUsuario(com.bachue.snr.biometrico.servicios.ws.VerificacionDTO entradaVerificacion) throws java.rmi.RemoteException{
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS.verificarUsuario(entradaVerificacion);
  }
  
  public com.bachue.snr.biometrico.servicios.ws.EstadisticasSalidaDTO consultarEstadisticas(com.bachue.snr.biometrico.servicios.ws.EstadisticasEntradaDTO entradaEstadisticas) throws java.rmi.RemoteException{
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS.consultarEstadisticas(entradaEstadisticas);
  }
  
  public com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO verificarClave(com.bachue.snr.biometrico.servicios.ws.ClaveDTO entradaClave) throws java.rmi.RemoteException{
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS.verificarClave(entradaClave);
  }
  
  public com.bachue.snr.biometrico.servicios.ws.AgregarFirmaSalidaDTO agregarFirma(com.bachue.snr.biometrico.servicios.ws.AgregarFirmaDTO entradaAgregarFirma) throws java.rmi.RemoteException{
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS.agregarFirma(entradaAgregarFirma);
  }
  
  public com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaSalidaDTO obtenerFirma(com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO entradaObtenerFirma) throws java.rmi.RemoteException{
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS.obtenerFirma(entradaObtenerFirma);
  }
  
  public com.bachue.snr.biometrico.servicios.ws.FirmaSalidaDTO eliminarFirma(com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO entradaEliminarFirma) throws java.rmi.RemoteException{
    if (biometriaWS == null)
      _initBiometriaWSProxy();
    return biometriaWS.eliminarFirma(entradaEliminarFirma);
  }
  
  
}