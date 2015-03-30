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
import org.foi.nwtis.vlspoljar.ejb.eb.Portfelj;
import org.foi.nwtis.vlspoljar.ejb.sb.AdreseFacade;
import org.foi.nwtis.vlspoljar.ejb.sb.PortfeljFacade;
import org.foi.nwtis.vlspoljar.ws.klijenti.MeteoWSKlijent;
import org.foi.nwtis.vlspoljar.ws.serveri.Adresa;

/**
 *
 * @author Branko
 */
@ManagedBean
@SessionScoped
public class NoviPortfelj {
    @EJB
    private AdreseFacade adreseFacade;
    @EJB
    private PortfeljFacade portfeljFacade;
    
    
    public String naziv;
    public List<Adresa> listaAdresa;
    public String odabranaAdresa;
    public String[] odabraneAdrese;
    public List<String> listaOdabranihAdresa;
    
    public NoviPortfelj() {
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Adresa> getListaAdresa() {
        return MeteoWSKlijent.dajSveAdrese();
    }

    public void setListaAdresa(List<Adresa> listaAdresa) {
        this.listaAdresa = listaAdresa;
    }
    
    public String[] getOdabraneAdrese() {
        return odabraneAdrese;
    }

    public void setOdabraneAdrese(String[] odabraneAdrese) {
        this.odabraneAdrese = odabraneAdrese;
    }

    public List<String> getListaOdabranihAdresa() {
        List<String> l = new ArrayList<>();
        for (String a : getOdabraneAdrese()) {
            l.add(a);
        }
        return l;
    }

    public void setListaOdabranihAdresa(List<String> listaOdabranihAdresa) {
        this.listaOdabranihAdresa = listaOdabranihAdresa;
    }

    public String getOdabranaAdresa() {
        for (String adresa : getOdabraneAdrese()) {
            return adresa;
        }
        return null;
    }

    public void setOdabranaAdresa(String odabranaAdresa) {
        this.odabranaAdresa = odabranaAdresa;
    }
    
    public void kreiraj() {
        Portfelj p = new Portfelj(Integer.SIZE, naziv, FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("korisnik").toString());
        portfeljFacade.kreirajPortfelj(p);
        for (String adr : getOdabraneAdrese()) {
            System.out.println(adr);
            Adrese a = new Adrese(Integer.SIZE, adr, naziv);
            adreseFacade.kreirajAdresu(a);
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("odabirPortfelja.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Registracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void odjava() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("korisnik");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("prijava.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(OdabirPortfelja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
