/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.vlspoljar.web.slusaci;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.foi.nwtis.vlspoljar.konfiguracije.Konfiguracija;
import org.foi.nwtis.vlspoljar.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.vlspoljar.konfiguracije.NemaKonfiguracije;
import org.foi.nwtis.vlspoljar.konfiguracije.bp.BP_Konfiguracija;

/**
 * Slušač konteksta kojim se jednostavno pristupa podacima iz konteksta
 * 
 * @author Vlatko Špoljarić
 */
public class SlusacAplikacije implements ServletContextListener{
    public static javax.servlet.ServletContext context;
    //DretvaZaMeteopodatke dzm;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            context = sce.getServletContext();
            String path = context.getRealPath("WEB-INF") + java.io.File.separator;
            String datoteka = context.getInitParameter("konfiguracija");
            
            BP_Konfiguracija bp_konfig = new BP_Konfiguracija(path + datoteka);
            Konfiguracija konfig = KonfiguracijaApstraktna.preuzmiKonfiguraciju(path + datoteka);
            if (konfig == null) {
                System.out.println("Pogreška u konfiguraciji!");
            }
            
            if (bp_konfig == null) {
                System.out.println("Pogreška u konfiguraciji!");
                return;
            }
            context.setAttribute("BP_Konfig", bp_konfig);
            context.setAttribute("Konfig", konfig);
            
        } catch (NemaKonfiguracije ex) {
            Logger.getLogger(SlusacAplikacije.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
