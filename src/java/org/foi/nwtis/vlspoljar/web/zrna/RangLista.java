/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.vlspoljar.web.zrna;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.foi.nwtis.vlspoljar.ejb.eb.Adrese;
import org.foi.nwtis.vlspoljar.ws.klijenti.MeteoWSKlijent;
import org.foi.nwtis.vlspoljar.ws.serveri.Adresa;

/**
 *
 * @author Branko
 */
@ManagedBean
@SessionScoped
public class RangLista {

    public int prvihN = 0;
    public List<Adresa> listaAdresa = new ArrayList<Adresa>();
    
    public RangLista() {
    }

    public int getPrvihN() {
        return prvihN;
    }

    public void setPrvihN(int prvihN) {
        this.prvihN = prvihN;
    }

    public List<Adresa> getListaAdresa() {
        return listaAdresa;
    }

    public void setListaAdresa(List<Adresa> listaAdresa) {
        this.listaAdresa = listaAdresa;
    }
    
    public List<Adrese> prikazi() {
        listaAdresa = null;
        listaAdresa = new ArrayList<>();
        if (prvihN > 0) {
            listaAdresa = MeteoWSKlijent.dajPrvihNAdresa(prvihN);
            prvihN = 0;
        }
        
        return null;
    }
    
}
