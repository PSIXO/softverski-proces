/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Jelena
 */
public class TransferObjekat implements Serializable {
   
    private int operacija;
    private Object parametar;
    private Object rezultat;
    private Object izuzetak;
    private String poruka;

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }

    public Object getRezultat() {
        return rezultat;
    }

    public void setRezultat(Object rezultat) {
        this.rezultat = rezultat;
    }

    public Object getIzuzetak() {
        return izuzetak;
    }

    public void setIzuzetak(Object izuzetak) {
        this.izuzetak = izuzetak;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
}