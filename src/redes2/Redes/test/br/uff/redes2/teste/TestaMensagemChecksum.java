/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.redes2.teste;

import br.uff.redes2.Mensagem;
import br.uff.redes2.MensagemChecksum;

/**
 *
 * @author felipe
 */
public class TestaMensagemChecksum {
    public static void main(String[] args) {
        String texto = "10110110";
        MensagemChecksum m1 = new MensagemChecksum(texto);
        MensagemChecksum m2 = new MensagemChecksum(texto);
               
        m2.inserirErro(1, .5);
        
        System.out.println(m1.equals(m2));
        
        System.out.println(m1);
        System.out.println(m2);
        
    }
    
}
