/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.vlspoljar.web.zrna;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import org.foi.nwtis.vlspoljar.konfiguracije.Konfiguracija;
import org.foi.nwtis.vlspoljar.web.kontrole.Poruka;
import org.foi.nwtis.vlspoljar.web.slusaci.SlusacAplikacije;

/**
 *
 * @author Branko
 */
@ManagedBean
@SessionScoped
public class PregledAdminDio {

    private String email_posluzitelj = "";
    private String korisnicko_ime = "";
    private String lozinka = "";

    private List<Poruka> poruke;
    private Map<String, String> mape;
    public static String odabranaMapa = "INBOX";

    Store store;
    Session session;
    Folder folder;

    public PregledAdminDio() {
        
        email_posluzitelj = "127.0.0.1";
        korisnicko_ime = "admin@nwtis.nastava.foi.hr";
        lozinka = "123456";
        try {
            poveziNaServer();
        } catch (MessagingException ex) {
            Logger.getLogger(PregledAdminDio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Poruka> getPoruke() {
        try {
            preuzmiPoruke();
        } catch (MessagingException ex) {
            Logger.getLogger(PregledAdminDio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PregledAdminDio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return poruke;
    }

    public void setPoruke(List<Poruka> poruke) {
        this.poruke = poruke;
    }

    public Map<String, String> getMape() {
        return mape;
    }

    public void setMape(Map<String, String> mape) {
        this.mape = mape;
    }

    public String getOdabranaMapa() {
        return odabranaMapa;
    }

    public void setOdabranaMapa(String odabranaMapa) {
        PregledAdminDio.odabranaMapa = odabranaMapa;
    }
    
    public String odaberiMapu() {
        return "promjenaMape";
    }

    private void preuzmiPoruke() throws MessagingException, IOException {
        Message[] messages;
        Multipart sadrzaj;
        Part dio;

        // Open the INBOX folder
        folder = store.getFolder(this.odabranaMapa);
        folder.open(Folder.READ_ONLY);
        messages = folder.getMessages();

        this.poruke = new ArrayList<Poruka>();

        for (int i = 0; i < messages.length; ++i) {
            Message m = messages[i];
            String sadrzajPoruke = "";
            Object objekt = m.getContent();
            if (objekt instanceof Multipart) {
                sadrzaj = (Multipart) m.getContent();
                for (int j = 0; j < sadrzaj.getCount(); j++) {
                    dio = sadrzaj.getBodyPart(j);
                        sadrzajPoruke = (String) dio.getContent();
                }
                Poruka p = new Poruka(m.getHeader("Message-ID")[0],
                        m.getSentDate(), m.getFrom()[0].toString(), m.getSubject(),
                        m.getContentType(), sadrzajPoruke, m.getSize(), m.getFlags(), true, true);
                this.poruke.add(p);
            } else {
                Poruka p = new Poruka(m.getHeader("Message-ID")[0],
                        m.getSentDate(), m.getFrom()[0].toString(), m.getSubject(),
                        m.getContentType(), (String) objekt, m.getSize(), m.getFlags(), true, true);
                this.poruke.add(p);
            }
        }

    }

    private void poveziNaServer() throws NoSuchProviderException, MessagingException {
        // Start the session
        java.util.Properties properties = System.getProperties();
        session = Session.getInstance(properties, null);

        // Connect to the store
        store = session.getStore("imap");
        store.connect(this.email_posluzitelj, this.korisnicko_ime, this.lozinka);

        folder = store.getDefaultFolder();
        Folder[] folderi = folder.list();

        this.mape = new HashMap<String, String>();
        for (Folder f : folderi) {
            this.mape.put(f.getName(), f.getName());
        }
    }
    
    public void odjava() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("korisnik");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("prijava.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(PregledAdminDio.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
