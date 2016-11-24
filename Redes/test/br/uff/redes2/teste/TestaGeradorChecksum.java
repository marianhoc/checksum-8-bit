/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.redes2.teste;

import br.uff.redes2.modelo.GeradorChecksum;

/**
 *
 * @author felipe
 */
public class TestaGeradorChecksum {
    public static void main(String[] args) {
        GeradorChecksum gerador = new GeradorChecksum(2, 0.5, 1, 100, true);
        
        gerador.executa();
        gerador.relatorio();
    }
    
}
