/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.redes2;

/**
 *
 * @author felipe
 */
public class CRC {
    private String mensagem;
    private String polinomio;
    private int[] crc;

    public CRC(String mensagem, String polinomio) {
        this.mensagem = mensagem;
        this.polinomio = polinomio;
        this.calculaCRC();
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getPolinomio() {
        return polinomio;
    }

    public void setPolinomio(String polinomio) {
        this.polinomio = polinomio;
    }
    
    public int[] getCrc(){
        return this.crc;
    }
    
    public String getCrcString(){
        return this.intArray2String(this.crc);
    }
    
    public String getMensagemEmpacotada(){
        return this.mensagem + this.getCrcString();
    }
    
    private void calculaCRC(){
       
        int lcrc[] = new int[this.polinomio.length() - 1]; //Usado para guardar o valor do crc
        int posicaoBit1 = 0; //Usando para guardar a posição do bit 1 no resultado do calculo
        int conta0 = 0; //Usado para saber se existem apenas zeros no resultado (exclui o crc)
        boolean flag = true; //Controle do loop de calculo
        
        int mensagemIntArray[] = this.strig2IntArray(this.mensagem);
        int polinomioIntArray[] = this.strig2IntArray(this.polinomio);
        
        //Para efetuar os cálculos do CRC é necessário que a mensagem original seja
        //concatenada com a quantidade de bits necessários para o calculo do crc
        //Esse novo array é usado para efetuar o "ou exclusivo" com o polinômio gerador do crc
        //O tamanho do array é a soma da mensagem mais o tamanho do polinomio gerador - 1
        //Como no Java o array é inicializado com 0, basta apenas copiar a carga util da mensagem 
        //original para o resultado. O restante será automaticamente zero.
        int resultadoIntArray[] = new int[mensagemIntArray.length + polinomioIntArray.length-1];
        System.arraycopy(mensagemIntArray, 0, resultadoIntArray, 0, mensagemIntArray.length);
        
        //Itera sobre a mensagem mensagem aumentada (resultado)
        do{
            //XOR sobre os dados da mensagem e o polinomio
            for(int i = 0; i < polinomioIntArray.length; i++){
                resultadoIntArray[posicaoBit1+i] = resultadoIntArray[posicaoBit1 + i] ^ polinomioIntArray[i];
            }
            //Deslocamento para pesquisar em qual posição esta o próximo bit 1 na mensagem
            while((resultadoIntArray[posicaoBit1] == 0) && (posicaoBit1 != resultadoIntArray.length - 1)){
                posicaoBit1++;// quarda a posição do bit 1
                conta0++; // conta a quantidade de 0 existe no resultado. So interessa os valores correspondente
                          // a carga útil, isto é, a quantidade de bits da mensagem
            }
            
            //verifica se todos os bits da carga útil são zero. caso afirmativo pode acabar a iteração
            if(conta0 >= resultadoIntArray.length - (polinomioIntArray.length - 1)){
                flag = false;
            }
            
        }while(flag);        
        
        //copia apenas o resultado do crc
        System.arraycopy(resultadoIntArray, resultadoIntArray.length - (polinomioIntArray.length-1), lcrc, 0, lcrc.length);
        
        this.crc = lcrc;
    }
    
    private int[] strig2IntArray(String str){
        int intArray[] = new int[str.length()];
        
        for(int i = 0; i < intArray.length; i++){
            intArray[i] = Character.getNumericValue(str.charAt(i));
        }
        
        return intArray;
    }
    
    private String intArray2String(int[] intArray){
        StringBuilder sb = new StringBuilder();
        
        for(int i : intArray){
            sb.append(i);
        }
        
        return sb.toString();
    }
    
    private void imprimeIntArray(int[] intArray){
        for(int i : intArray){
            System.out.print(i);
        }
        System.out.println("");
    }
}
