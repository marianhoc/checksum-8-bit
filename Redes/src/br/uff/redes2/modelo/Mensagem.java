/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.redes2.modelo;

import java.util.Objects;
import java.util.Random;

/**
 *
 * @author felipe
 */
public class Mensagem {
    
    protected String texto;
    protected String deteccaoErro;

    public Mensagem() {
    }
    
    public Mensagem(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getDeteccaoErro() {
        return deteccaoErro;
    }

    public void setDeteccaoErro(String deteccaoErro) {
        this.deteccaoErro = deteccaoErro;
    }
    
    public void inserirErro(long seed, double probabilidade){
        StringBuilder output = new StringBuilder(this.texto);
        Boolean changed = false;
        Random random = new Random(seed);
       
        while(!changed){
            for (int i = 0; i < output.length(); i++){
                if (random.nextDouble() <= probabilidade) {
                    changed = true;
                    if (output.charAt(i) == '1'){
                        output.setCharAt(i, '0');
                    }else{
                        output.setCharAt(i, '1');
                    }
                }
            }
        }
        this.texto = output.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.texto);
        hash = 23 * hash + Objects.hashCode(this.deteccaoErro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mensagem other = (Mensagem) obj;
        return Objects.equals(this.deteccaoErro, other.deteccaoErro);
    }    

    @Override
    public String toString() {
        return "Mensagem{" + "texto=" + texto + ", deteccaoErro=" + deteccaoErro + '}';
    } 
    
}
