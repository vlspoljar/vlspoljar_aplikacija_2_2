/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.vlspoljar.web.zrna;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.foi.nwtis.vlspoljar.ws.klijenti.MeteoWSKlijent;
import org.foi.nwtis.vlspoljar.ws.serveri.Adresa;
import org.foi.nwtis.vlspoljar.ws.serveri.WeatherData;

/**
 *
 * @author Branko
 */
@ManagedBean
@SessionScoped
public class PregledJavniDio {

    public List<Adresa> listaAdresa;
    public Adresa odabranaAdresa;
    public WeatherData zadnjiWD;
    public WeatherData vazeciWD;

    public PregledJavniDio() {
    }

    public List<Adresa> getListaAdresa() {
        return MeteoWSKlijent.dajSveAdrese();
    }

    public void setListaAdresa(List<Adresa> listaAdresa) {
        this.listaAdresa = listaAdresa;
    }

    public Adresa getOdabranaAdresa() {
        return odabranaAdresa;
    }

    public void setOdabranaAdresa(Adresa odabranaAdresa) {
        this.odabranaAdresa = odabranaAdresa;
    }

    public WeatherData getZadnjiWD() {
        if (odabranaAdresa != null) {
            zadnjiWD = MeteoWSKlijent.dajPosljednjihNZaAdresu(odabranaAdresa.getAdresa(), 1).get(0);
        }
        return zadnjiWD;
    }

    public void setZadnjiWD(WeatherData zadnjiWD) {
        this.zadnjiWD = zadnjiWD;
    }

    public WeatherData getVazeciWD() {
        if (odabranaAdresa != null) {
            vazeciWD = MeteoWSKlijent.dajVazeceMeteoPodatkeZaAdresu(odabranaAdresa.getAdresa());
        }
        return vazeciWD;
    }

    public void setVazeciWD(WeatherData vazeciWD) {
        this.vazeciWD = vazeciWD;
    }

}
