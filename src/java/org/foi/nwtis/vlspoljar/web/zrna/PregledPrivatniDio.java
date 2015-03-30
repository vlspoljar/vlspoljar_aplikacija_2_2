/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.vlspoljar.web.zrna;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.foi.nwtis.vlspoljar.ejb.eb.Adrese;
import org.foi.nwtis.vlspoljar.ejb.sb.AdreseFacade;
import org.foi.nwtis.vlspoljar.ws.klijenti.MeteoWSKlijent;
import org.foi.nwtis.vlspoljar.ws.serveri.WeatherData;

/**
 *
 * @author Branko
 */
@ManagedBean
@SessionScoped
public class PregledPrivatniDio {

    @EJB
    private AdreseFacade adreseFacade;

    public List<Adrese> listaAdresa;
    public int posljednjihN;
    public String Od;
    public String Do;
    public List<WeatherData> listaWD = new ArrayList<WeatherData>();
    public List<WeatherData> posljednji = new ArrayList<WeatherData>();
    public List<WeatherData> interval = new ArrayList<WeatherData>();

    public PregledPrivatniDio() {
    }

    public List<Adrese> getListaAdresa() {
        return adreseFacade.provjeriAdrese(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("portfelj").toString());
    }

    public void setListaAdresa(List<Adrese> listaAdresa) {
        this.listaAdresa = listaAdresa;
    }

    public int getPosljednjihN() {
        return posljednjihN;
    }

    public void setPosljednjihN(int posljednjihN) {
        this.posljednjihN = posljednjihN;
    }

    public String getOd() {
        return Od;
    }

    public void setOd(String Od) {
        this.Od = Od;
    }

    public String getDo() {
        return Do;
    }

    public void setDo(String Do) {
        this.Do = Do;
    }

    public List<WeatherData> getListaWD() {
        return listaWD;
    }

    public void setListaWD(List<WeatherData> listaWD) {
        this.listaWD = listaWD;
    }

    public List<WeatherData> prikazi() {
        listaWD = null;
        listaWD = new ArrayList<>();

        if ((posljednjihN > 0) && (!getListaAdresa().isEmpty())) {
            for (Adrese a : getListaAdresa()) {
                posljednji = MeteoWSKlijent.dajPosljednjihNZaAdresu(a.getAdresa(), posljednjihN);
                if (!posljednji.isEmpty()) {
                    for (int i = 0; i < posljednji.size(); i++) {
                        WeatherData wd = new WeatherData();
                        wd.setAddress(posljednji.get(i).getAddress());
                        wd.setDate(posljednji.get(i).getDate());
                        wd.setTemperature(posljednji.get(i).getTemperature());
                        wd.setPressureSeaLevel(posljednji.get(i).getPressureSeaLevel());
                        wd.setHumidity(posljednji.get(i).getHumidity());
                        wd.setWindSpeed(posljednji.get(i).getWindSpeed());
                        wd.setRainRate(posljednji.get(i).getRainRate());
                        wd.setSnowRate(posljednji.get(i).getSnowRate());
                        listaWD.add(wd);
                    }
                }
            }
            posljednjihN = 0;
            return listaWD;
        }

        if ((Od != null && Od.length() > 0 && Do != null && Do.length() > 0) && (!getListaAdresa().isEmpty())) {
            for (Adrese a : getListaAdresa()) {
                interval = MeteoWSKlijent.dajMeteoPodatkeUIntervalu(a.getAdresa(), Od, Do);
                if (!interval.isEmpty()) {
                    for (int i = 0; i < interval.size(); i++) {
                        WeatherData wd = new WeatherData();
                        wd.setAddress(interval.get(i).getAddress());
                        wd.setDate(interval.get(i).getDate());
                        wd.setTemperature(interval.get(i).getTemperature());
                        wd.setPressureSeaLevel(interval.get(i).getPressureSeaLevel());
                        wd.setHumidity(interval.get(i).getHumidity());
                        wd.setWindSpeed(interval.get(i).getWindSpeed());
                        wd.setRainRate(interval.get(i).getRainRate());
                        wd.setSnowRate(interval.get(i).getSnowRate());
                        listaWD.add(wd);
                    }
                }

            }
            Od = null;
            Do = null;
            return listaWD;
        }

        return null;
    }

    public void odjava() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("korisnik");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("prijava.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(PregledPrivatniDio.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
