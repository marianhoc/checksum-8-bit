/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.redes2.visao;

import br.uff.redes2.modelo.GeradorChecksum;

/**
 *
 * @author felipe
 */
public class TestaGeradorChecksum {
    public static void main(String[] args) {
        GeradorChecksum gerador = new GeradorChecksum(1, 0.9, 6, 10000);
        
        gerador.executa();
        gerador.relatorio();
    }
    
}
