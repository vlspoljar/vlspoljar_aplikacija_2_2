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
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.foi.nwtis.vlspoljar.ejb.eb.Korisnici;
import org.foi.nwtis.vlspoljar.ejb.sb.KorisniciFacade;
import org.foi.nwtis.vlspoljar.web.slusaci.SlusacSesije;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Branko
 */
@Path("/korisniciREST")
public class KorisniciRESTResourceContainer {
    KorisniciFacade korisniciFacade = lookupKorisniciFacadeBean();
    

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of KorisniciRESTResourceContainer
     */
    public KorisniciRESTResourceContainer() {
    }

    /**
     * Retrieves representation of an instance of org.foi.nwtis.vlspoljar.rest.serveri.KorisniciRESTResourceContainer
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json;charset=utf-8")
    public String getJson() {
        
        JSONObject rezultat = new JSONObject();
        try {
            JSONArray korisnici = new JSONArray();
            int i = 0;
            for (Korisnici k : SlusacSesije.listaKorisnika) {
                JSONObject jo = new JSONObject();
                jo.put("id", k.getIdkorisnici());
                jo.put("kor_ime", k.getKorIme());
                jo.put("ime", k.getIme());
                jo.put("prezime", k.getPrezime());
                jo.put("lozinka", k.getLozinka());
                jo.put("email", k.getEmailAdresa());
                jo.put("vrsta", k.getVrsta());
                korisnici.put(i, jo);
                i++;
            }
            rezultat.put("korisnici", korisnici);
            return rezultat.toString();
        } catch (JSONException ex) {
            Logger.getLogger(KorisniciRESTResourceContainer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * POST method for creating an instance of KorisniciRESTResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postJson(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{korisnik}")
    public PortfeljRESTResource getPortfeljRESTResource(@PathParam("korisnik") String korisnik) {
        return PortfeljRESTResource.getInstance(korisnik);
    }
    
    @Path("{korisnik}/{portfelj}")
    public AdreseRESTResource getAdreseRESTResource(@PathParam("korisnik") String korisnik, @PathParam("portfelj") String portfelj) {
        return AdreseRESTResource.getInstance(korisnik, portfelj);
    }


    private KorisniciFacade lookupKorisniciFacadeBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (KorisniciFacade) c.lookup("java:global/vlspoljar_aplikacija_2/vlspoljar_aplikacija_2_1/KorisniciFacade!org.foi.nwtis.vlspoljar.ejb.sb.KorisniciFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
