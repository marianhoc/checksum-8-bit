/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.redes2.teste;

import br.uff.redes2.modelo.Mensagem;
import br.uff.redes2.modelo.MensagemCRC;

/**
 *
 * @author felipe
 */
public class TestaMensagemCRC {
    public static void main(String[] args) {
        String texto = "10110110";
        int polinomio = 127;
        
        MensagemCRC m1 = new MensagemCRC(texto, polinomio);
        MensagemCRC m2 = new MensagemCRC(texto, polinomio);
               
        m2.inserirErro(1, .5);
        
        System.out.println(m1.equals(m2));
        
        System.out.println(m1);
        System.out.println(m2);
        
    }
    
}
