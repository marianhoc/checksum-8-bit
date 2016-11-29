/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.redes2.visao;

import br.uff.redes2.controlador.GeradorChecksum;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipe
 */
public class TestaGeradorChecksum {

    public static void main(String[] args) {

        int quantidadeIteracoes = 10000;

        List<Long> sementes = new ArrayList<Long>() {{
            add(123456L);
            add(1L);
            add(519281291L);
        }};

        List<Double> probabilidades = new ArrayList<Double>() {{
            add(0.1);
            add(0.5);
            add(0.9);
        }};

        List<Long> tamanhoPacotes = new ArrayList<Long>() {{
            add(150L);
            add(700L);
            add(1450L);
        }};

        for (Long semente : sementes) {
            for (Double probabilidade : probabilidades) {
                for(Long tamanhoPacote : tamanhoPacotes) {

                    GeradorChecksum gerador = new GeradorChecksum(semente, probabilidade, tamanhoPacote, quantidadeIteracoes);

                    gerador.executa();
                    gerador.relatorio();

                }
            }
        }

    }
    
}
