package br.uff.redes2.controlador;

import br.uff.redes2.modelo.MensagemCRC;

import java.text.MessageFormat;

/**
 *
 * @author felipe
 */
public class GeradorCRC extends Gerador {

    private int polinomio;

    /**
     *
     * @param sementeNumAleatorio Semente(Seed) utilizada para geração de valores pseudo-randômicos
     * @param probabilidadeErro Probabilidade p para inserção de erros nos bits dos pacotes
     * @param tamanhoPacote Tamanho (em bytes) dos pacotes gerados
     * @param quantidadeIteracoes Quantidade de pacotes testados
     * @param polinomio Polinomio utilizado para o calculo do CRC
     */
    public GeradorCRC(long sementeNumAleatorio, double probabilidadeErro, long tamanhoPacote, int quantidadeIteracoes, int polinomio) {
        super(sementeNumAleatorio, probabilidadeErro, tamanhoPacote, quantidadeIteracoes);
        this.polinomio = polinomio;
    }

    public GeradorCRC(long sementeNumAleatorio, double probabilidadeErro, long tamanhoPacote, int quantidadeIteracoes, int polinomio, boolean debug) {
        this(sementeNumAleatorio, probabilidadeErro, tamanhoPacote, quantidadeIteracoes, polinomio);
        this.debug = debug;
    }

    public void executa() {

        for (int i = 0; i < quantidadeIteracoes; i++) {
            String pacoteLocal = gerarMensagemDeTamanho(tamanhoPacote);

            if (debug) {
                System.out.println("PacoteLocal: " + pacoteLocal);
            }

            MensagemCRC mensagemCRCOriginal = new MensagemCRC(pacoteLocal, this.polinomio);
            MensagemCRC mensagemCRCAlterada = new MensagemCRC(geradorErro.inserirErro(pacoteLocal, probabilidadeErro), this.polinomio);

            verificaColisao(mensagemCRCOriginal, mensagemCRCAlterada);
        }

    }

    public void relatorio() {

        System.out.println(MessageFormat.format("Parâmetros: [Semente: {0}, p: {1}, Tamanho do Pacote: {2}, Quantidade de pacotes: {3}, Polinomio: {4}]",
                this.sementeNumAleatorio,
                this.probabilidadeErro,
                this.tamanhoPacote,
                this.quantidadeIteracoes,
                Integer.toHexString(this.polinomio)));
        System.out.println("Quantidade de pacotes gerados: " + this.quantidadeIteracoes);
        System.out.println("Quantidade de colisões: " + this.quantidadeColisoes);
        System.out.println("Percentual de colisões: " + (this.quantidadeColisoes / (double)this.quantidadeIteracoes)*100 + "%");
        System.out.println("Tempo de execução: " + (System.currentTimeMillis() - startTime) + "ms\n");
    }

}
