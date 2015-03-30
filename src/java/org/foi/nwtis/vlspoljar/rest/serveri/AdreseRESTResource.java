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
import org.foi.nwtis.vlspoljar.ejb.eb.Adrese;
import org.foi.nwtis.vlspoljar.ejb.sb.AdreseFacade;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Branko
 */
public class AdreseRESTResource {
    AdreseFacade adreseFacade = lookupAdreseFacadeBean();
    
    private String korisnik;
    private String portfelj;

    /**
     * Creates a new instance of AdreseRESTResource
     */
    private AdreseRESTResource(String korisnik, String portfelj) {
        this.korisnik = korisnik;
        this.portfelj = portfelj;
    }

    /**
     * Get instance of the AdreseRESTResource
     */
    public static AdreseRESTResource getInstance(String korisnik, String portfelj) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of AdreseRESTResource class.
        return new AdreseRESTResource(korisnik, portfelj);
    }

    /**
     * Retrieves representation of an instance of org.foi.nwtis.vlspoljar.rest.serveri.AdreseRESTResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        List<Adrese> listaAdresa = new ArrayList<>();
        listaAdresa = adreseFacade.provjeriAdrese(portfelj);

        JSONObject rezultat = new JSONObject();
        try {
            JSONArray adrese = new JSONArray();
            int i = 0;
            for (Adrese a : listaAdresa) {
                JSONObject jo = new JSONObject();
                jo.put("id", a.getIdadrese());
                jo.put("adresa", a.getAdresa());
                adrese.put(i, jo);
                i++;
            }
            rezultat.put(portfelj, adrese);
            return rezultat.toString();
        } catch (JSONException ex) {
            Logger.getLogger(KorisniciRESTResourceContainer.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return "null";
    }

    /**
     * PUT method for updating or creating an instance of AdreseRESTResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource AdreseRESTResource
     */
    @DELETE
    public void delete() {
    }

    private AdreseFacade lookupAdreseFacadeBean() {
        try {
            Context c = new InitialContext();
            return (AdreseFacade) c.lookup("java:global/vlspoljar_aplikacija_2/vlspoljar_aplikacija_2_1/AdreseFacade!org.foi.nwtis.vlspoljar.ejb.sb.AdreseFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
