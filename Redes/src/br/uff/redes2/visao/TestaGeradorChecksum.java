/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.redes2.visao;

import br.uff.redes2.controlador.GeradorChecksum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author felipe
 */
public class TestaGeradorChecksum {

    public static void main(String[] args) {
        try {
            long semente = Objects.requireNonNull(Long.valueOf(args[0]));
            double probabilidade = Objects.requireNonNull(Double.valueOf(args[1]));
            int tamanhoPacote = Objects.requireNonNull(Integer.valueOf(args[2]));
            int quantidadeIteracoes = Objects.requireNonNull(Integer.valueOf(args[3]));

            testaChecksum(semente, probabilidade, tamanhoPacote, quantidadeIteracoes);
        } catch (Exception e) {
            System.err.println("Parâmetros faltando.\nPor favor, preencha todos os parâmetros, nesta ordem: Semente, probabilidade de erro, tamanho de pacotes, quantidade de iterações.");
        }
    }


    private static void testaChecksum(long semente, double probabilidade, int tamanhoPacote, int quantidadeIteracoes) {

        GeradorChecksum gerador = new GeradorChecksum(semente, probabilidade, tamanhoPacote, quantidadeIteracoes);

        gerador.executa();
        gerador.relatorio();

    }
    
}
