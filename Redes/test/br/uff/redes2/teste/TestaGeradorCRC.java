/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.redes2.teste;

import br.uff.redes2.modelo.GeradorCRC;

/**
 *
 * @author felipe
 */
public class TestaGeradorCRC {
    public static void main(String[] args) {
        GeradorCRC gerador = new GeradorCRC(1, 0.5, 5, 383, 1000000);
        
        gerador.executa();
        gerador.relatorio();
    }
    
}
