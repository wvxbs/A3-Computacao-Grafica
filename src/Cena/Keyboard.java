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

        if(e.getKeyChar() == '1'){cena.movendo = !cena.movendo;} // alterna a movimentação da bola palos

        if(e.getKeyChar() == 'a'){cena.q1.mover(-cena.q1.getVelx(), 0);} // mexe a barra pra esquerda

        if(e.getKeyChar() == 'd'){cena.q1.mover(cena.q1.getVelx(), 0);} // mexe a barra pra direita


    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
