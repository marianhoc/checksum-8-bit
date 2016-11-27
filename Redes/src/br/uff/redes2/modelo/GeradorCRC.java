    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.redes2.modelo;

import br.uff.redes2.modelo.MensagemCRC;
import java.util.Random;

/**
 *
 * @author felipe
 */
public class GeradorCRC {

    private long sementeNumAleatorio;
    private double probabilidadeErro;
    private long tamanhoPacote;
    private int polinomio;
    private int quantidadeIteracoes;
    private boolean debug;

    private long quantidadeColisoes;
    private double percentualColisoes;
    private String pacote;
    private MensagemCRC mensagemCRCOriginal;
    private MensagemCRC mensagemCRCAlterada;

    /**
     *
     * @param sementeNumAleatorio
     * @param probabilidadeErro
     * @param tamanhoPacote
     * @param polinomio
     * @param quantidadeIteracoes
     */
    public GeradorCRC(long sementeNumAleatorio, double probabilidadeErro, long tamanhoPacote, int polinomio, int quantidadeIteracoes) {
        this.sementeNumAleatorio = sementeNumAleatorio;
        this.probabilidadeErro = probabilidadeErro;
        this.tamanhoPacote = tamanhoPacote;
        this.polinomio = polinomio;
        this.quantidadeIteracoes = quantidadeIteracoes;
        this.debug = false;

    }

    public GeradorCRC(long sementeNumAleatorio, double probabilidadeErro, long tamanhoPacote, int polinomio, int quantidadeIteracoes, boolean debug) {
        this.sementeNumAleatorio = sementeNumAleatorio;
        this.probabilidadeErro = probabilidadeErro;
        this.tamanhoPacote = tamanhoPacote;
        this.polinomio = polinomio;
        this.quantidadeIteracoes = quantidadeIteracoes;
        this.debug = debug;

    }

    public void executa() {
        String pacoteLocal;
        this.quantidadeColisoes = 0;

        for (int i = 0; i < quantidadeIteracoes; i++) {
            pacoteLocal = this.gerarMensagemDeTamanho(tamanhoPacote, i+this.sementeNumAleatorio, probabilidadeErro);

            if (debug) {
                System.out.println("PacoteLocal: " + pacoteLocal);
            }

            mensagemCRCOriginal = new MensagemCRC(pacoteLocal, this.polinomio);
            mensagemCRCAlterada = new MensagemCRC(pacoteLocal, this.polinomio);

            mensagemCRCAlterada.inserirErro(this.sementeNumAleatorio, this.probabilidadeErro);

            if (debug) {
                System.out.println("MO: " + mensagemCRCOriginal);
                System.out.println("MA: " + mensagemCRCAlterada);
            }

            if (mensagemCRCOriginal.equals(mensagemCRCAlterada)) {
                this.quantidadeColisoes = this.quantidadeColisoes + 1;
            }
        }
    }

    public void relatorio() {

        System.out.println("Quantidade de pacotes gerados: " + this.quantidadeIteracoes);
        System.out.println("Quantidade de colisões: " + this.quantidadeColisoes);
        System.out.println("Percentual de colisões: " + (this.quantidadeColisoes / (double)this.quantidadeIteracoes)*100 + "%");
    }

    /**
     * A função de geração de mensagens aleatórias recebe como argumento um
     * tamanho, em bytes, da mensagem a ser gerada. A implementação utiliza uma
     * função escolhida para geração de números pseudo-aleatórios para a
     * obtenção dos valores dos bytes da mensagem resultante
     *
     * @param bytes tamanho, em bytes, da mensagem a ser gerada.
     * @return String, sequencia de 0´se 1´s
     *
     */
    private String gerarMensagemDeTamanho(long bytes, long semente, double probabilidade) {
        StringBuilder mensagem = new StringBuilder();
        Random random = new Random(semente);
        
        for (long i = 0; i < bytes * 8; i++) {
            if (random.nextDouble() > probabilidadeErro) {
                mensagem.append('0');
            } else {
                mensagem.append('1');
            }

        }
        return mensagem.toString();
    }

}
