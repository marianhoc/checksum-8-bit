package br.uff.redes2.visao;

import br.uff.redes2.controlador.GeradorCRC;

import java.util.Objects;

/**
 *
 * @author felipe
 */
public class TestaGeradorCRC {

    public static final int BASE_16 = 16;

    public static void main(String[] args) {

        try {
            long semente = Objects.requireNonNull(Long.valueOf(args[0]));
            double probabilidade = Objects.requireNonNull(Double.valueOf(args[1]));
            int tamanhoPacote = Objects.requireNonNull(Integer.valueOf(args[2]));
            int quantidadeIteracoes = Objects.requireNonNull(Integer.valueOf(args[3]));
            String polinomio = Objects.requireNonNull(args[4]);

            testaCRC(semente, probabilidade, tamanhoPacote, quantidadeIteracoes, polinomio);
        } catch (Exception e) {
            System.err.println("Parâmetros faltando.\nPor favor, preencha todos os parâmetros, nesta ordem: Semente, probabilidade de erro, tamanho de pacotes, quantidade de iterações, polinomio.");
        }
    }

    private static void testaCRC(long semente, double probabilidade, int tamanhoPacote, int quantidadeIteracoes, String polinomio) {

        GeradorCRC gerador = new GeradorCRC(semente, probabilidade, tamanhoPacote, quantidadeIteracoes, Integer.parseInt(polinomio, BASE_16), false);

        gerador.executa();
        gerador.relatorio();

    }

}
