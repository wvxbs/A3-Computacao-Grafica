package Cena;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class Keyboard implements KeyListener {
    private Cena cena;

    public Keyboard(Cena cena){
        this.cena = cena;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyCode());
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);

        if(e.getKeyChar() == '1'){ // alterna a movimentação da bola palos
            cena.q2.setMovendo(!cena.q2.isMovendo());
        }

        // TODO MELHORAR MOVIMENTAÇÃO DA BARRA (TA TRAVANDO DPOIS DE ANDAR UM POUCO)
        if(e.getKeyChar() == 'a'){ // mexe a barra pra esquerda
            cena.q1.setMovendo(true);
            cena.q1.setDirecao(Direcao.ESQUERDA);

        } else if(e.getKeyChar() == 'd'){ // mexe a barra pra direita
            cena.q1.setMovendo(true);
            cena.q1.setDirecao(Direcao.DIREITA);
        } //else {cena.q1.setVelx(0);}

        if(e.getKeyChar() == 'z'){
            cena.q2.setAlfa(cena.q2.getAlfa()-1);
        } // mexe a barra pra direita

        if(e.getKeyChar() == 'x'){
            cena.q2.setAlfa(cena.q2.getAlfa()+1);
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyChar() == 'a' || e.getKeyChar() == 'd') cena.q1.setMovendo(false);

        //if(e.getKeyChar() == 'd') cena.q1.setMovendo(false);
    }
}
