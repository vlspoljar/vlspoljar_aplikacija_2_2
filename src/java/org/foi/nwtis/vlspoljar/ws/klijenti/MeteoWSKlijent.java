/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.vlspoljar.ws.klijenti;

import org.foi.nwtis.vlspoljar.ws.serveri.WeatherData;

/**
 * Klasa u kojoj su obavljeni pozivi za sve operacije web servisa
 *
 * @author Vlatko Špoljarić 
 */
public class MeteoWSKlijent {

    public static java.util.List<org.foi.nwtis.vlspoljar.ws.serveri.Adresa> dajSveAdrese() {
        org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS_Service service = new org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS_Service();
        org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS port = service.getGeoMeteoWSPort();
        return port.dajSveAdrese();
    }

    public static WeatherData dajVazeceMeteoPodatkeZaAdresu(java.lang.String adresa) {
        org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS_Service service = new org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS_Service();
        org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS port = service.getGeoMeteoWSPort();
        return port.dajVazeceMeteoPodatkeZaAdresu(adresa);
    }

    public static java.util.List<org.foi.nwtis.vlspoljar.ws.serveri.WeatherData> dajSveMeteoPodatkeZaAdresu(java.lang.String adresa) {
        org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS_Service service = new org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS_Service();
        org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS port = service.getGeoMeteoWSPort();
        return port.dajSveMeteoPodatkeZaAdresu(adresa);
    }

    public static java.util.List<org.foi.nwtis.vlspoljar.ws.serveri.WeatherData> dajPosljednjihNZaAdresu(java.lang.String adresa, int posljednjihN) {
        org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS_Service service = new org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS_Service();
        org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS port = service.getGeoMeteoWSPort();
        return port.dajPosljednjihNZaAdresu(adresa, posljednjihN);
    }

    public static java.util.List<org.foi.nwtis.vlspoljar.ws.serveri.WeatherData> dajMeteoPodatkeUIntervalu(java.lang.String adresa, java.lang.String odDate, java.lang.String doDate) {
        org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS_Service service = new org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS_Service();
        org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS port = service.getGeoMeteoWSPort();
        return port.dajMeteoPodatkeUIntervalu(adresa, odDate, doDate);
    }

    public static java.util.List<org.foi.nwtis.vlspoljar.ws.serveri.Adresa> dajPrvihNAdresa(int prvihN) {
        org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS_Service service = new org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS_Service();
        org.foi.nwtis.vlspoljar.ws.serveri.GeoMeteoWS port = service.getGeoMeteoWSPort();
        return port.dajPrvihNAdresa(prvihN);
    }
    
    
    
}
