/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes2;

/**
 *
 * @author copes
 */
public class Redes2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         System.out.println(             
                            Mensagem.checkSum( 
                                          "11111110"
                                        + "11101010"
                                //------------------
                                //parcial+ 11101001
                                        + "10111010"
                                //--------------------- 
                                //parcial+ 10100100
                                        + "11111111")
                                //----------------------
                                // final   10100100
                            ); 
    }
    
}
