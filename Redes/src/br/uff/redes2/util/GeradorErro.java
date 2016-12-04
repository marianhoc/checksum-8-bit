package br.uff.redes2.util;

import java.util.Random;

public class GeradorErro {

    private Random random;

    public GeradorErro(long seed) {
        this.random = new Random(seed);
    }

    public String inserirErro(String texto, double probabilidade){
        StringBuilder output = new StringBuilder(texto);
        Boolean changed = false;

        while(!changed){
            for (int i = 0; i < output.length(); i++){
                if (random.nextDouble() <= probabilidade) {
                    changed = true;
                    if (output.charAt(i) == '1'){
                        output.setCharAt(i, '0');
                    }else{
                        output.setCharAt(i, '1');
                    }
                }
            }
        }
        return output.toString();
    }
}
