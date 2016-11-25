/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.redes2.visao;

import br.uff.redes2.modelo.GeradorCRC;

/**
 *
 * @author felipe
 */
public class TestaGeradorCRC {
    public static void main(String[] args) {
        GeradorCRC gerador = new GeradorCRC(2, 0.4, 5, 38, 100, true);
        
        gerador.executa();
        gerador.relatorio();
    }
    
}
