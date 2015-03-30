/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.vlspoljar.web.zrna;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.foi.nwtis.vlspoljar.ejb.sb.KorisniciFacade;

/**
 *
 * @author Branko
 */
@ManagedBean
@SessionScoped
public class Prijava {

    @EJB
    private KorisniciFacade korisniciFacade;

    public String korisnickoIme;
    public String lozinka;

    public Prijava() {
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void provjera() {
        if (korisniciFacade.provjeriKorisnika(korisnickoIme, lozinka).isEmpty()) {
            Logger.getLogger(Prijava.class.getName()).log(Level.SEVERE, "False");
        } else {
            if (korisniciFacade.dajKorisnika(korisnickoIme).getVrsta() == 2) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("korisnik", korisnickoIme);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("adminDio.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(Prijava.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("korisnik", korisnickoIme);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("noviPortfelj.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(Prijava.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
