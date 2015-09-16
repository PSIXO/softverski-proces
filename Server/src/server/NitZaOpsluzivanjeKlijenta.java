/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domen.Faktura;
import domen.Korisnik;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import domen.PoslovniPartner;
import domen.Proizvod;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Operacije;
import kontroler.Kontroler;
import transfer.TransferObjekat;

/**
 *
 * @author student1
 */
public class NitZaOpsluzivanjeKlijenta extends Thread {
    
    private Socket socket;
    private boolean kraj;
    public static int brojac = 0;

    public NitZaOpsluzivanjeKlijenta(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            izvrsenjeOperacija();
        } catch (IOException ex) {
            Logger.getLogger(NitZaOpsluzivanjeKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NitZaOpsluzivanjeKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void izvrsenjeOperacija() throws IOException, ClassNotFoundException {
        while (!kraj) {
            ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
            TransferObjekat toZahtev = (TransferObjekat) inSocket.readObject();
            TransferObjekat toOdgovor = new TransferObjekat();
            switch (toZahtev.getOperacija()) {
                case Operacije.SACUVAJ_MESTO:
                    try {
                        System.out.println("O: " + Operacije.SACUVAJ_MESTO);
                        Mesto m = (Mesto) toZahtev.getParametar();
                        Kontroler.vratiObjekat().kreirajISacuvajObjekat(m);
                        toOdgovor.setPoruka("Mesto je sacuvano.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setPoruka("Mesto nije sacuvano.");
                        toOdgovor.setIzuzetak(ex);
                    }
                    break;
                case Operacije.VRATI_SVA_MESTA:
                    try {
                        System.out.println("O: " + Operacije.VRATI_SVA_MESTA);
                        Mesto m = (Mesto) toZahtev.getParametar();
                        List<Mesto> lm = Kontroler.vratiObjekat().vratiSveObjekte(m);
                        toOdgovor.setRezultat(lm);
                        toOdgovor.setPoruka("Sistem je vratio listu mesta.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setIzuzetak(ex);
                        toOdgovor.setPoruka("Sistem nije vratio listu mesta.");
                    }
                    break;
                case Operacije.KREIRAJ_POSLOVNOG_PARTNERA:
                    try {
                        System.out.println("O: " + Operacije.KREIRAJ_POSLOVNOG_PARTNERA);
                        PoslovniPartner p = Kontroler.vratiObjekat().kreirajObjekat((PoslovniPartner) toZahtev.getParametar());
                        toOdgovor.setPoruka("Poslovni partner je kreiran.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setPoruka("Poslovni partner nije kreiran.");
                        toOdgovor.setIzuzetak(ex);
                    }
                    break;
                case Operacije.SACUVAJ_POSLOVNOG_PARTNERA:
                    try {
                        System.out.println("O: " + Operacije.SACUVAJ_POSLOVNOG_PARTNERA);
                        PoslovniPartner p = (PoslovniPartner) toZahtev.getParametar();
                        Kontroler.vratiObjekat().sacuvajObjekat(p);
                        toOdgovor.setPoruka("Poslovni partner je sacuvan.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setPoruka("Poslovni partner nije sacuvan.");
                        toOdgovor.setIzuzetak(ex);
                    }
                    break;
                case Operacije.VRATI_SVE_POSLOVNE_PARTNERE:
                    try {
                        System.out.println("O: " + Operacije.VRATI_SVE_POSLOVNE_PARTNERE);
                        PoslovniPartner p = (PoslovniPartner) toZahtev.getParametar();
                        List<PoslovniPartner> lista = Kontroler.vratiObjekat().vratiObjekteSaVezanimObjektom(p);
                        toOdgovor.setRezultat(lista);
                        toOdgovor.setPoruka("Sistem je vratio listu poslovnih partnera.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setIzuzetak(ex);
                        toOdgovor.setPoruka("Sistem nije vratio listu poslovnih partnera.");
                    }
                    break;
                case Operacije.KREIRAJ_PROIZVOD:
                    try {
                        System.out.println("O: " + Operacije.KREIRAJ_PROIZVOD);
                        Proizvod p = Kontroler.vratiObjekat().kreirajObjekat((Proizvod) toZahtev.getParametar());
                        toOdgovor.setPoruka("Proizvod je kreiran.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setPoruka("Proizvod nije kreiran.");
                        toOdgovor.setIzuzetak(ex);
                    }
                    break;
                case Operacije.SACUVAJ_PROIZVOD:
                    try {
                        System.out.println("O: " + Operacije.SACUVAJ_PROIZVOD);
                        Proizvod p = (Proizvod) toZahtev.getParametar();
                        Kontroler.vratiObjekat().sacuvajObjekat(p);
                        toOdgovor.setPoruka("Proizvod je sacuvan.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setPoruka("Proizvod nije sacuvan.");
                        toOdgovor.setIzuzetak(ex);
                    }
                    break;
                case Operacije.VRATI_SVE_PROIZVODE:
                    try {
                        System.out.println("O: " + Operacije.VRATI_SVE_PROIZVODE);
                        Proizvod p = (Proizvod) toZahtev.getParametar();
                        List<Proizvod> lista = Kontroler.vratiObjekat().vratiSveObjekte(p);
                        toOdgovor.setRezultat(lista);
                        toOdgovor.setPoruka("Sistem je vratio listu proizvoda.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setIzuzetak(ex);
                        toOdgovor.setPoruka("Sistem nije vratio listu proizvoda.");
                    }
                    break;
                case Operacije.PRETRAZI_PROIZVODE:
                    try {
                        System.out.println("O: " + Operacije.PRETRAZI_PROIZVODE);
                        Proizvod p = (Proizvod) toZahtev.getParametar();
                        List<OpstiDomenskiObjekat> lista;
                        lista = Kontroler.vratiObjekat().pretraziObjekte(p);
                        toOdgovor.setRezultat(lista);
                        toOdgovor.setPoruka("Sistem je pronasao proizvode.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setIzuzetak(ex);
                        toOdgovor.setPoruka("Sistem nije pronasao proizvode.");
                    }
                    break;
                case Operacije.PRETRAZI_POSLOVNE_PARTNERE:
                    try {
                        System.out.println("O: " + Operacije.PRETRAZI_POSLOVNE_PARTNERE);
                        PoslovniPartner p = (PoslovniPartner) toZahtev.getParametar();
                        List<OpstiDomenskiObjekat> lista;
                        lista = Kontroler.vratiObjekat().pretraziSaVezanimObjektom(p);
                        toOdgovor.setRezultat(lista);
                        toOdgovor.setPoruka("Sistem je pronasao poslovne partnere.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setIzuzetak(ex);
                        toOdgovor.setPoruka("Sistem nije pronasao poslovne partnere.");
                    }
                    break;
                case Operacije.KREIRAJ_FAKTURU:
                    try {
                        System.out.println("O: " + Operacije.KREIRAJ_FAKTURU);
                        Faktura f = Kontroler.vratiObjekat().kreirajObjekat((Faktura) toZahtev.getParametar());
                        toOdgovor.setPoruka("Faktura je kreirana.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setPoruka("Faktura nije kreirana.");
                        toOdgovor.setIzuzetak(ex);
                    }
                    break;
                case Operacije.SACUVAJ_FAKTURU:
                    try {
                        System.out.println("O: " + Operacije.SACUVAJ_FAKTURU);
                        Faktura f = (Faktura) toZahtev.getParametar();
                        Kontroler.vratiObjekat().sacuvajObjekatSaStavkama(f);
                        toOdgovor.setPoruka("Faktura je sacuvana.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setPoruka("Faktura nije sacuvana.");
                        toOdgovor.setIzuzetak(ex);
                    }
                    break;
                case Operacije.VRATI_SVE_FAKTURE:
                    try {
                        System.out.println("O: " + Operacije.VRATI_SVE_FAKTURE);
                        Faktura f = (Faktura) toZahtev.getParametar();
                        List<Faktura> lista = Kontroler.vratiObjekat().vratiObjekteSaVezanimObjektom(f);
                        toOdgovor.setRezultat(lista);
                        toOdgovor.setPoruka("Sistem je vratio listu faktura.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setIzuzetak(ex);
                        toOdgovor.setPoruka("Sistem nije vratio listu faktura.");
                    }
                    break;
                case Operacije.OBRADI_FAKTURU:
                    try {
                        System.out.println("O: " + Operacije.OBRADI_FAKTURU);
                        Faktura f = (Faktura) toZahtev.getParametar();
                        Kontroler.vratiObjekat().obradi(f);
                        toOdgovor.setPoruka("Faktura je obrađena.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setPoruka("Faktura nije obrađena.");
                        toOdgovor.setIzuzetak(ex);
                    }
                    break;
                case Operacije.STORNIRAJ_FAKTURU:
                    try {
                        System.out.println("O: " + Operacije.STORNIRAJ_FAKTURU);
                        Faktura f = (Faktura) toZahtev.getParametar();
                        Kontroler.vratiObjekat().storniraj(f);
                        toOdgovor.setPoruka("Faktura je stornirana.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setPoruka("Faktura nije stornirana.");
                        toOdgovor.setIzuzetak(ex);
                    }
                    break;
                case Operacije.PRETRAZI_FAKTURE:
                    try {
                        System.out.println("O: " + Operacije.PRETRAZI_FAKTURE);
                        Faktura p = (Faktura) toZahtev.getParametar();
                        List<OpstiDomenskiObjekat> lista;
                        lista = Kontroler.vratiObjekat().pretraziSaVezanimObjektom(p);
                        toOdgovor.setRezultat(lista);
                        toOdgovor.setPoruka("Sistem je pronasao fakture.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setIzuzetak(ex);
                        toOdgovor.setPoruka("Sistem nije pronasao fakture.");
                    }
                    break;
                case Operacije.PRIJAVI_KORISNIKA:
                    try {
                        System.out.println("O: " + Operacije.PRIJAVI_KORISNIKA);
                        Korisnik k = (Korisnik) toZahtev.getParametar();
                        k = (Korisnik) Kontroler.vratiObjekat().pretraziObjekte(k).get(0);
                        toOdgovor.setRezultat(k);
                        toOdgovor.setPoruka("Sistem je pronasao korisnika.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setIzuzetak(ex);
                        toOdgovor.setPoruka("Sistem nije pronasao korisnika.");
                    }
                    break;
                case Operacije.GENERISI_ID:
                    try {
                        System.out.println("O: " + Operacije.GENERISI_ID);
                        OpstiDomenskiObjekat odo = (OpstiDomenskiObjekat) toZahtev.getParametar();
                        int id = Kontroler.vratiObjekat().generisiID(odo);
                        toOdgovor.setRezultat(id);
                        toOdgovor.setPoruka("Generisan je ID za objekat tipa " + odo.vratiNazivTabele());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        toOdgovor.setIzuzetak(ex);
                        toOdgovor.setPoruka(ex.getMessage());
                    }
                    break;
                case Operacije.PRIKAZI_FAKTURU:
                    try {
                        System.out.println("O: " + Operacije.PRIKAZI_FAKTURU);
                        Faktura f = (Faktura) toZahtev.getParametar();
                        Faktura nova = (Faktura) Kontroler.vratiObjekat().prikaziPodatkeSaVezanimObjektomIStavkama(f);
                        toOdgovor.setRezultat(nova);
                        toOdgovor.setPoruka("Sistem je pronasao podatke o izabranoj fakturi!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        toOdgovor.setIzuzetak(e);
                        toOdgovor.setPoruka("Sistem nije pronasao podatke o izabranoj fakturi!");
                    }
                    break;
                case Operacije.PRIKAZI_PROIZVOD:
                    try {
                        System.out.println("O: " + Operacije.PRIKAZI_PROIZVOD);
                        Proizvod p = (Proizvod) toZahtev.getParametar();
                        Proizvod novi = (Proizvod) Kontroler.vratiObjekat().prikaziPodatke(p);
                        toOdgovor.setRezultat(novi);
                        toOdgovor.setPoruka("Sistem je pronasao podatke o izabranom proizvodu!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        toOdgovor.setIzuzetak(e);
                        toOdgovor.setPoruka("Sistem nije pronasao podatke o izabranom proizvodu!");
                    }
                    break;
                case Operacije.PRIKAZI_POSLOVNOG_PARTNERA:
                    try {
                        System.out.println("O: " + Operacije.PRIKAZI_POSLOVNOG_PARTNERA);
                        PoslovniPartner p = (PoslovniPartner) toZahtev.getParametar();
                        PoslovniPartner novi = (PoslovniPartner) Kontroler.vratiObjekat().prikaziPodatkeSaVezanimObjektom(p);
                        toOdgovor.setRezultat(novi);
                        toOdgovor.setPoruka("Sistem je pronasao podatke o izabranom poslovnom partneru!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        toOdgovor.setIzuzetak(e);
                        toOdgovor.setPoruka("Sistem nije pronasao podatke o izabranom proposlovnom partneru!");
                    }
                    break;
                case Operacije.PREKINI_KOMUNIKACIJU:
                    try {
                        System.out.println("O: " + Operacije.PREKINI_KOMUNIKACIJU);
                        kraj = true;
                        brojac--;
                        toOdgovor.setPoruka("Komunikacija uspešno prekinuta.");
                    } catch (Exception e) {
                        e.printStackTrace();
                        toOdgovor.setIzuzetak(e);
                        toOdgovor.setPoruka("Greška pri prekidu komunikacije!");
                    }
                    break;
            }

            posaljiOdgovor(toOdgovor);
        }

    }

    public void posaljiOdgovor(TransferObjekat toOdgovor) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(toOdgovor);
    }

}
