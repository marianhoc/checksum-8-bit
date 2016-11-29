package br.uff.redes2.visao;

import br.uff.redes2.controlador.GeradorCRC;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipe
 */
public class TestaGeradorCRC {

    public static void main(String[] args) {

        String crc8 = "07"; // 0111
        String crc16 = "1021"; // 0001 0000 0010 0001
        String crc32 = "04C11DB7"; // 0000 0100 1100 0001 0001 1101 1011 0111
        String crcCustomizado = "196"; // 11001 0110

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

        testaCRC(crc8, quantidadeIteracoes, sementes, probabilidades, tamanhoPacotes);
        testaCRC(crc16, quantidadeIteracoes, sementes, probabilidades, tamanhoPacotes);
        testaCRC(crc32, quantidadeIteracoes, sementes, probabilidades, tamanhoPacotes);
        testaCRC(crcCustomizado, quantidadeIteracoes, sementes, probabilidades, tamanhoPacotes);
    }

    private static void testaCRC(String polinomio, int quantidadeIteracoes, List<Long> sementes, List<Double> probabilidades, List<Long> tamanhoPacotes) {

        for (Long semente : sementes) {
            for (Double probabilidade : probabilidades) {
                for(Long tamanhoPacote : tamanhoPacotes) {

                    GeradorCRC gerador = new GeradorCRC(semente, probabilidade, tamanhoPacote, quantidadeIteracoes, Integer.parseInt(polinomio, 16));

                    gerador.executa();
                    gerador.relatorio();

                }
            }
        }

    }

}
