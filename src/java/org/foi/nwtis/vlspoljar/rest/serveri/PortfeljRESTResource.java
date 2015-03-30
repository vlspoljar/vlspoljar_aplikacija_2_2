/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.vlspoljar.rest.serveri;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import org.foi.nwtis.vlspoljar.ejb.eb.Portfelj;
import org.foi.nwtis.vlspoljar.ejb.sb.PortfeljFacade;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Branko
 */
public class PortfeljRESTResource {
    PortfeljFacade portfeljFacade = lookupPortfeljFacadeBean();
    

    private String korisnik;

    /**
     * Creates a new instance of PortfeljRESTResource
     */
    private PortfeljRESTResource(String korisnik) {
        this.korisnik = korisnik;
    }

    /**
     * Get instance of the PortfeljRESTResource
     */
    public static PortfeljRESTResource getInstance(String korisnik) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of PortfeljRESTResource class.
        return new PortfeljRESTResource(korisnik);
    }

    /**
     * Retrieves representation of an instance of org.foi.nwtis.vlspoljar.PortfeljRESTResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        
        List<Portfelj> listaPortfelja = new ArrayList<>();
        listaPortfelja = portfeljFacade.provjeraPortfelja(korisnik);

        JSONObject rezultat = new JSONObject();
        try {
            JSONArray portfelji = new JSONArray();
            int i = 0;
            for (Portfelj p : listaPortfelja) {
                JSONObject jo = new JSONObject();
                jo.put("id", p.getIdportfelj());
                jo.put("naziv", p.getNaziv());
                portfelji.put(i, jo);
                i++;
            }
            rezultat.put(korisnik, portfelji);
            return rezultat.toString();
        } catch (JSONException ex) {
            Logger.getLogger(KorisniciRESTResourceContainer.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return "null";
        
    }

    /**
     * PUT method for updating or creating an instance of PortfeljRESTResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource PortfeljRESTResource
     */
    @DELETE
    public void delete() {
    }

    private PortfeljFacade lookupPortfeljFacadeBean() {
        try {
            Context c = new InitialContext();
            return (PortfeljFacade) c.lookup("java:global/vlspoljar_aplikacija_2/vlspoljar_aplikacija_2_1/PortfeljFacade!org.foi.nwtis.vlspoljar.ejb.sb.PortfeljFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
