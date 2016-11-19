/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.redes2.teste;

import br.uff.redes2.CRC;

/**
 *
 * @author felipe
 */
public class TestaCRC {
    public static void main(String[] args) {
        //CRC m = new CRC("100111110101010011011010", "110101101");
        //CRC m = new CRC("11010011101100", "1011");
        CRC m = new CRC("101110", "1001");
        
        System.out.println("Mensagem: " + m.getMensagem());
        System.out.println("Polinomio: " + m.getPolinomio());
        System.out.println("CRC: " + m.getCrcString());
        System.out.println("Mensagem empacotada: " + m.getMensagemEmpacotada());
    }
    
}
