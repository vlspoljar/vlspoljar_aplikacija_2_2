/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.vlspoljar.web.zrna;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Pozadinsko zrno za odabir jezika.
 *
 * @author Vlatko Špoljarić
 */
@ManagedBean
@SessionScoped
public class Lokalizacija {

    private static Map<String, Object> podrzaniJezici;
    private String odabraniJezik;
    private Locale vazecaLokalizacija;
    
    static {
        podrzaniJezici = new LinkedHashMap<String, Object>();
        podrzaniJezici.put("Hrvatski", new Locale("hr"));
        podrzaniJezici.put("English", Locale.ENGLISH);
    }

     /**
     * Creates a new instance of Lokalizacija
     */
    public Lokalizacija() {
        vazecaLokalizacija = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        odabraniJezik = vazecaLokalizacija.getLanguage();
    }
    
    public Map<String, Object> getPodrzaniJezici() {
        return podrzaniJezici;
    }

    public void setPodrzaniJezici(Map<String, Object> podrzaniJezici) {
        Lokalizacija.podrzaniJezici = podrzaniJezici;
    }

    public String getOdabraniJezik() {
        return odabraniJezik;
    }

    public void setOdabraniJezik(String odabraniJezik) {
        this.odabraniJezik = odabraniJezik;
    }

    public Locale getVazecaLokalizacija() {
        return vazecaLokalizacija;
    }

    public void setVazecaLokalizacija(Locale vazecaLokalizacija) {
        this.vazecaLokalizacija = vazecaLokalizacija;
    }
    
    /**
     * Metoda za odabir jezika.
     * 
     * @return "OK"
     */
    public Object odaberiJezik() {
        Iterator i = podrzaniJezici.keySet().iterator();
        while(i.hasNext()) {
            String kljuc = (String) i.next();
            Locale l = (Locale) podrzaniJezici.get(kljuc);
            if(odabraniJezik.compareTo(l.getLanguage()) == 0) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale(l);
                vazecaLokalizacija = l;
                break;
            }
        }
        return "OK";
    }
    
}
