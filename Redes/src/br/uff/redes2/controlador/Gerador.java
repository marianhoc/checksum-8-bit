package br.uff.redes2.controlador;

import br.uff.redes2.modelo.Mensagem;

import java.util.Random;

public abstract class Gerador {

    protected Random random;

    protected long sementeNumAleatorio;
    protected double probabilidadeErro;
    protected long tamanhoPacote;
    protected int quantidadeIteracoes;
    protected boolean debug;

    protected long quantidadeColisoes = 0;

    protected long startTime;

    /**
     *
     * @param sementeNumAleatorio Semente(Seed) utilizada para geração de valores pseudo-randômicos
     * @param probabilidadeErro Probabilidade p para inserção de erros nos bits dos pacotes
     * @param tamanhoPacote Tamanho (em bytes) dos pacotes gerados
     * @param quantidadeIteracoes Quantidade de pacotes testados
     */
    public Gerador(long sementeNumAleatorio, double probabilidadeErro, long tamanhoPacote, int quantidadeIteracoes) {
        this.sementeNumAleatorio = sementeNumAleatorio;
        this.probabilidadeErro = probabilidadeErro;
        this.tamanhoPacote = tamanhoPacote;
        this.quantidadeIteracoes = quantidadeIteracoes;
        this.debug = false;
        this.startTime = System.currentTimeMillis();
        random = new Random(sementeNumAleatorio);

    }

     public Gerador(long sementeNumAleatorio, double probabilidadeErro, long tamanhoPacote, int quantidadeIteracoes, boolean debug) {
        this(sementeNumAleatorio, probabilidadeErro, tamanhoPacote, quantidadeIteracoes);
        this.debug = debug;
        
    }

    protected void executa(Mensagem mensagemOriginal, Mensagem mensagemAlterada) {

        mensagemAlterada.inserirErro(this.sementeNumAleatorio, this.probabilidadeErro);

        if (debug) {
            System.out.println("MO: " + mensagemOriginal);
            System.out.println("MA: " + mensagemAlterada);
        }

        if (mensagemOriginal.equals(mensagemAlterada)) {
            this.quantidadeColisoes = this.quantidadeColisoes + 1;
        }

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
    protected String gerarMensagemDeTamanho(long bytes) {
        StringBuilder mensagem = new StringBuilder();
        
        for (long i = 0; i < bytes * 8; i++) {
            if (random.nextDouble() <= 0.5) {
                mensagem.append('0');
            } else {
                mensagem.append('1');
            }

        }
        return mensagem.toString();
    }
}
