/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.redes2.modelo;

/**
 *
 * @author felipe
 */
public class MensagemChecksum extends Mensagem{

    public MensagemChecksum() {
    }
    
    public MensagemChecksum(String texto) {
        super(texto);
        super.deteccaoErro = this.checkSum(texto);
    }
    
    private String checkSum(String mensagem){ // mensagem de 0´s e 1´s de tamanho multiplo de 8
        
        String result = "00000000";              
        int indice = 0;
        
        while(indice < mensagem.length()){  // enquanto o indice for menor ao tamanho da mensagem
                                            // vou percorrer a mensagem pegando um byte 
            result = addingBytes(result, mensagem.substring(indice, indice + 8 ));
            indice = indice + 8;
        }

        return invertBits(result);
        //System.out.println("checksum = " + result);
    }

     /**
     * 
     * @param a 
     * byte(sequencia de 8 caracteres 0´s ou 1´s)
     * @param b 
     * byte 
     * @return 
     * A soma dos bytes 'a' e 'b'
     */
    private String addingBytes(String a, String b){
            StringBuilder partialResult = new StringBuilder("00000000");
            int position = 7;
            int carry = 0;
            
            while(position >-1){
                switch( carry +     // soma dos bits na mesma posicao em A e B e do carry
                        Character.getNumericValue(a.charAt(position)) + 
                        Character.getNumericValue(b.charAt(position))){
                    //case 0:   se a soma dos 3 dados for 0 nada deve ser feito
                        //      a variavel carry ja vai ter valor 0     
                        //break;
                    case 1:
                        partialResult.setCharAt(position, '1');
                        carry = 0;                        
                        break;
                    case 2:
                        partialResult.setCharAt(position, '0');
                        carry = 1;
                        break;
                    case 3:
                        partialResult.setCharAt(position, '1');
                        carry = 1;
                        break;                        
                }
                position--;
         
            }
            if (carry == 1){        //se no final do soma tem overflow
                position = 7;       //volto pra posicao inicial pra adicionar o valor
                while(carry == 1){  
                    if (position == -1){    
                        position = 7;   
                    }                    
                    if(partialResult.charAt(position) == '1'){
                        partialResult.setCharAt(position, '0');
                        position--;
                    }else{
                        partialResult.setCharAt(position, '1');
                        carry = 0;
                    }                                                                       
                }
            }
            
        return partialResult.toString();
    }  
    
    /**
     * 
     * @param input
     * recebe uma String de 0´s e 1´s
     * @return 
     * retorna uma String de mesmo tamanho que o input tendo como valor o complemento a 1 dos bits no input
     */
    private String invertBits(String input){
        StringBuilder output = new StringBuilder();
        
        for (char c: input.toCharArray()){
            if(c == '1')output.append('0');
            if(c == '0')output.append('1');            
        }              
        
        return output.toString();                    
    }
     
    @Override
    public void inserirErro(long seed, double probabilidade){
        super.inserirErro(seed, probabilidade);
        super.setDeteccaoErro(this.checkSum(texto));        
    }
    
}
