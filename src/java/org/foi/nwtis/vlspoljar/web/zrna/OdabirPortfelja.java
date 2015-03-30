/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.vlspoljar.web.zrna;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.foi.nwtis.vlspoljar.ejb.eb.Portfelj;
import org.foi.nwtis.vlspoljar.ejb.sb.PortfeljFacade;

/**
 *
 * @author Branko
 */
@ManagedBean
@SessionScoped
public class OdabirPortfelja {

    @EJB
    private PortfeljFacade portfeljFacade;

    public List<Portfelj> listaPortfelja;
    public String odabraniPortfelj;

    public OdabirPortfelja() {
    }

    public List<Portfelj> getListaPortfelja() {
        return portfeljFacade.provjeraPortfelja(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("korisnik").toString());
    }

    public void setListaPortfelja(List<Portfelj> listaPortfelja) {
        this.listaPortfelja = listaPortfelja;
    }

    public String getOdabraniPortfelj() {
        return odabraniPortfelj;
    }

    public void setOdabraniPortfelj(String odabraniPortfelj) {
        this.odabraniPortfelj = odabraniPortfelj;
    }

    public void odaberiPortfelj() {
        if (odabraniPortfelj != null && odabraniPortfelj.length() > 0) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("portfelj");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("portfelj", odabraniPortfelj);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("privatniDio.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(OdabirPortfelja.class.getName()).log(Level.SEVERE, null, ex);
            }
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
