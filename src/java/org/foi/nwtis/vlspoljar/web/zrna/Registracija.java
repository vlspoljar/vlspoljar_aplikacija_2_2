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
import org.foi.nwtis.vlspoljar.ejb.eb.Korisnici;
import org.foi.nwtis.vlspoljar.ejb.sb.KorisniciFacade;

/**
 *
 * @author Branko
 */
@ManagedBean
@SessionScoped
public class Registracija {
    @EJB
    private KorisniciFacade korisniciFacade;
    

    public String korisnickoIme;
    public String ime;
    public String prezime;
    public String lozinka;
    public String email;
    
    public Registracija() {
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void registriraj() {
        Korisnici k = new Korisnici(Integer.SIZE, korisnickoIme, ime, prezime, lozinka, email, 1);
        korisniciFacade.registrirajKorisnika(k);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("prijava.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Registracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
