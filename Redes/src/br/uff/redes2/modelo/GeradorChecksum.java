/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.redes2.modelo;

import br.uff.redes2.modelo.MensagemChecksum;
import br.uff.redes2.modelo.MensagemChecksum;
import java.util.Random;

/**
 *
 * @author felipe
 */
public class GeradorChecksum {

    private long sementeNumAleatorio;
    private double probabilidadeErro;
    private long tamanhoPacote;
    private int quantidadeIteracoes;
    private boolean debug;

    private long quantidadeColisoes;
    private double percentualColisoes;
    private String pacote;
    private MensagemChecksum mensagemChecksumOriginal;
    private MensagemChecksum mensagemChecksumAlterada;

    /**
     *
     * @param sementeNumAleatorio
     * @param probabilidadeErro
     * @param tamanhoPacote
     * @param quantidadeIteracoes
     */
    public GeradorChecksum(long sementeNumAleatorio, double probabilidadeErro, long tamanhoPacote, int quantidadeIteracoes) {
        this.sementeNumAleatorio = sementeNumAleatorio;
        this.probabilidadeErro = probabilidadeErro;
        this.tamanhoPacote = tamanhoPacote;
        this.quantidadeIteracoes = quantidadeIteracoes;
        this.debug = false;
        
    }
    
     public GeradorChecksum(long sementeNumAleatorio, double probabilidadeErro, long tamanhoPacote, int quantidadeIteracoes, boolean debug) {
        this.sementeNumAleatorio = sementeNumAleatorio;
        this.probabilidadeErro = probabilidadeErro;
        this.tamanhoPacote = tamanhoPacote;
        this.quantidadeIteracoes = quantidadeIteracoes;
        this.debug = debug;
        
    }
    
    public void executa(){
        String pacoteLocal;
        this.quantidadeColisoes = 0;
        
        for(long i = 0; i < quantidadeIteracoes; i++){
            pacoteLocal = this.gerarMensagemDeTamanho(tamanhoPacote, i+this.sementeNumAleatorio, probabilidadeErro);
            
            if(debug)System.out.println("PacoteLocal: " + pacoteLocal);
            
            mensagemChecksumOriginal = new MensagemChecksum(pacoteLocal);
            mensagemChecksumAlterada = new MensagemChecksum(pacoteLocal);
            
            mensagemChecksumAlterada.inserirErro(this.sementeNumAleatorio, this.probabilidadeErro);
            
            if(debug){
                System.out.println("MO: " + mensagemChecksumOriginal);
                System.out.println("MA: " + mensagemChecksumAlterada);
            }
            
            
            if(mensagemChecksumOriginal.equals(mensagemChecksumAlterada)){
                this.quantidadeColisoes = this.quantidadeColisoes + 1;
            }
        }        
    }
    
    public void relatorio(){
        
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
