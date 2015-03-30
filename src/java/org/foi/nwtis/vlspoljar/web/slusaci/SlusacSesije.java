/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.vlspoljar.web.slusaci;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import org.foi.nwtis.vlspoljar.ejb.eb.Korisnici;
import org.foi.nwtis.vlspoljar.ejb.sb.KorisniciFacade;

/**
 *
 * @author Branko
 */
public class SlusacSesije implements HttpSessionAttributeListener {

    KorisniciFacade korisniciFacade = lookupKorisniciFacadeBean();

    public static List<Korisnici> listaKorisnika = new ArrayList<Korisnici>();

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (event.getName().equals("korisnik")) {
            listaKorisnika.add(korisniciFacade.dajKorisnika(event.getValue().toString()));
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if (event.getName().equals("korisnik")) {
            listaKorisnika.remove(korisniciFacade.dajKorisnika(event.getValue().toString()));
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private KorisniciFacade lookupKorisniciFacadeBean() {
        try {
            Context c = new InitialContext();
            return (KorisniciFacade) c.lookup("java:global/vlspoljar_aplikacija_2/vlspoljar_aplikacija_2_1/KorisniciFacade!org.foi.nwtis.vlspoljar.ejb.sb.KorisniciFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
