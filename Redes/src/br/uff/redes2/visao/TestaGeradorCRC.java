package br.uff.redes2.visao;

import br.uff.redes2.controlador.GeradorCRC;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipe
 */
public class TestaGeradorCRC {

    public static final int BASE_16 = 16;

    public static void main(String[] args) {

        String crc8 = "D5"; // 0111
        String crc8_AUTOSAR = "2F"; // 0001 0000 0010 0001
        String crc8_WCDMA = "9B"; // 0001 0000 0010 0001
        String crcCustomizado = "196"; // 11001 0110

        int quantidadeIteracoes = 10_000;

        List<Long> sementes = new ArrayList<Long>() {{
            add(123456L);
            add(30L);
            add(959124L);
            add(124523L);
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

        testaCRC(crc8, quantidadeIteracoes, sementes, probabilidades, tamanhoPacotes);
        testaCRC(crc8_AUTOSAR, quantidadeIteracoes, sementes, probabilidades, tamanhoPacotes);
        testaCRC(crc8_WCDMA, quantidadeIteracoes, sementes, probabilidades, tamanhoPacotes);
        testaCRC(crcCustomizado, quantidadeIteracoes, sementes, probabilidades, tamanhoPacotes);
    }

    private static void testaCRC(String polinomio, int quantidadeIteracoes, List<Long> sementes, List<Double> probabilidades, List<Long> tamanhoPacotes) {

        for (Long semente : sementes) {
            for (Double probabilidade : probabilidades) {
                for(Long tamanhoPacote : tamanhoPacotes) {

                    GeradorCRC gerador = new GeradorCRC(semente, probabilidade, tamanhoPacote, quantidadeIteracoes, Integer.parseInt(polinomio, BASE_16), false);

                    gerador.executa();
                    gerador.relatorio();

                }
            }
        }

    }

}
