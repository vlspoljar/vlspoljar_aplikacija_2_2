/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.vlspoljar.web.kontrole;

import java.util.Date;
import javax.mail.Flags;

/**
 * Klasa za poruku, preuzeta sa nastave.
 * Dodana varijabla sadrzajPoruke u koju se pohranjuje sam sadržaj poruke.
 * 
 * @author dkermek, Vlatko Špoljarić
 */
public class Poruka {
    private String id;
    private Date vrijeme;
    private String salje;
    private String predmet;
    private String vrsta;
    private String sadrzajPoruke;
    private int velicina;
    private Flags zastavice;
    private boolean brisati;
    private boolean procitano;

    public Poruka(String id, Date poslano, String salje, String predmet, String vrsta, String sadrzajPoruke, int velicina, Flags zastavice, boolean brisati, boolean procitano) {
        this.id = id;
        this.vrijeme = poslano;
        this.salje = salje;
        this.predmet = predmet;
        this.vrsta = vrsta;
        this.sadrzajPoruke = sadrzajPoruke;
        this.velicina = velicina;
        this.zastavice = zastavice;
        this.brisati = brisati;
        this.procitano = procitano;
    }

    public String getId() {
        return id;
    }

    public boolean isBrisati() {
        return brisati;
    }

    public void setBrisati(boolean brisati) {
        this.brisati = brisati;
    }

    public Flags getZastavice() {
        return zastavice;
    }

    public Date getVrijeme() {
        return vrijeme;
    }

    public String getPredmet() {
        return predmet;
    }

    public boolean isProcitano() {
        return procitano;
    }

    public void setProcitano(boolean procitano) {
        this.procitano = procitano;
    }
    
    public String getSalje() {
        return salje;
    }

    public String getVrsta() {
        return vrsta;
    }
    
    public String getSadrzajPoruke() {
        return sadrzajPoruke;
    }

    public int getVelicina() {
        return velicina;
    }

    public void setVrijeme(Date vrijeme) {
        this.vrijeme = vrijeme;
    }

    
}
