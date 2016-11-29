package br.uff.redes2.controlador;

import br.uff.redes2.modelo.MensagemChecksum;

import java.text.MessageFormat;

/**
 *
 * @author felipe
 */
public class GeradorChecksum extends Gerador {

    /**
     *
     * @param sementeNumAleatorio Semente(Seed) utilizada para geração de valores pseudo-randômicos
     * @param probabilidadeErro Probabilidade p para inserção de erros nos bits dos pacotes
     * @param tamanhoPacote Tamanho (em bytes) dos pacotes gerados
     * @param quantidadeIteracoes Quantidade de pacotes testados
     */
    public GeradorChecksum(long sementeNumAleatorio, double probabilidadeErro, long tamanhoPacote, int quantidadeIteracoes) {
        super(sementeNumAleatorio, probabilidadeErro, tamanhoPacote, quantidadeIteracoes);
    }
    
     public GeradorChecksum(long sementeNumAleatorio, double probabilidadeErro, long tamanhoPacote, int quantidadeIteracoes, boolean debug) {
        this(sementeNumAleatorio, probabilidadeErro, tamanhoPacote, quantidadeIteracoes);
        this.debug = debug;
    }
    
    public void executa() {

        for(long i = 0; i < quantidadeIteracoes; i++){
            String pacoteLocal = this.gerarMensagemDeTamanho(tamanhoPacote);
            
            if(debug)System.out.println("PacoteLocal: " + pacoteLocal);

            MensagemChecksum mensagemChecksumOriginal = new MensagemChecksum(pacoteLocal);
            MensagemChecksum mensagemChecksumAlterada = new MensagemChecksum(pacoteLocal);
            
           executa(mensagemChecksumOriginal, mensagemChecksumAlterada);
        }        
    }
    
    public void relatorio(){

        System.out.println(MessageFormat.format("Parâmetros: [Semente: {0}, p: {1}, Tamanho do Pacote: {2}, Quantidade de pacotes: {3}]",
                this.sementeNumAleatorio,
                this.probabilidadeErro,
                this.tamanhoPacote,
                this.quantidadeIteracoes));
        System.out.println("Quantidade de pacotes gerados: " + this.quantidadeIteracoes);
        System.out.println("Quantidade de colisões: " + this.quantidadeColisoes);
        System.out.println("Percentual de colisões: " + (this.quantidadeColisoes / (double)this.quantidadeIteracoes)*100 + "%");
        System.out.println("Tempo de execução: " + (System.currentTimeMillis() - startTime) + "ms\n");

    }

}
