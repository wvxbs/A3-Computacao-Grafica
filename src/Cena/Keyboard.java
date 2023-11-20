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

        // textura
        if(e.getKeyChar() == '1'){ // alterna a movimentação da bola palos
            cena.q2.setMovendo(!cena.q2.isMovendo());
        }
        if(e.getKeyChar() == '2'){ // alterna a animação da bola palos
            cena.q2.setAnimado(!cena.q2.isAnimado());
        }
        if(e.getKeyChar() == '3'){ // diminui a escala vertical da imagem
            float[] novoTamanho = {
                    (cena.q2.getEscala()[0]),
                    (cena.q2.getEscala()[1]+0.25f)
            };
            cena.q2.setEscala(novoTamanho);
        }
        if(e.getKeyChar() == '4'){ // aumenta a escala vertical da imagem
            float[] novoTamanho = {
                    (cena.q2.getEscala()[0]),
                    (cena.q2.getEscala()[1]-0.25f)
            };
            cena.q2.setEscala(novoTamanho);
        }

        // movimenta a textura
        if(e.getKeyCode() == 149){ // seta esquerda
            float[] novoOfsetTextura = {
                cena.q2.getTexturaOfset()[0]-0.9f,
                cena.q2.getTexturaOfset()[1]
            };
            cena.q2.setTexturaOfset(novoOfsetTextura);
            System.out.println("textura offset: "+cena.q2.getTexturaOfset()[0]);
        }
        if(e.getKeyCode() == 150){ // seta cima
            float[] novoOfsetTextura = {
                cena.q2.getTexturaOfset()[0],
                cena.q2.getTexturaOfset()[1]+0.25f
            };
            cena.q2.setTexturaOfset(novoOfsetTextura);
            System.out.println("textura offset: "+cena.q2.getTexturaOfset()[0]);
        }
        if(e.getKeyCode() == 151){ // seta direita
            float[] novoOfsetTextura = {
                cena.q2.getTexturaOfset()[0]+0.9f,
                cena.q2.getTexturaOfset()[1]
            };
            cena.q2.setTexturaOfset(novoOfsetTextura);
            System.out.println("textura offset: "+cena.q2.getTexturaOfset()[0]);
        }
        if(e.getKeyCode() == 152){ // seta baixo
            float[] novoOfsetTextura = {
                cena.q2.getTexturaOfset()[0],
                cena.q2.getTexturaOfset()[1]-0.25f
            };
            cena.q2.setTexturaOfset(novoOfsetTextura);
            System.out.println("textura offset: "+cena.q2.getTexturaOfset()[0]);
        }

        // TODO |MELHORAR MOVIMENTAÇÃO DA BARRA (TA TRAVANDO DPOIS DE ANDAR UM POUCO)
        // TODO |possível motivo: o código não é chamado todo frame, ele fica esperando um input do jogador

        if(e.getKeyChar() == 'a'){ // mexe a barra pra esquerda
            cena.q1.setMovendo(true);
            cena.q1.setDirecao(Direcao.ESQUERDA);

        } else if(e.getKeyChar() == 'd'){ // mexe a barra pra direita
            cena.q1.setMovendo(true);
            cena.q1.setDirecao(Direcao.DIREITA);
        } //else {cena.q1.setVelx(0);}

        if(e.getKeyChar() == 'c'){ // diminui a transparência
            cena.q2.setAlfa(cena.q2.getAlfa()-1);
        } // mexe a barra pra direita

        if(e.getKeyChar() == 'v'){ // aumenta a transparência
            cena.q2.setAlfa(cena.q2.getAlfa()+1);
        }
        if(e.getKeyChar() == 'x'){ // aumenta a transparência
            cena.pausado = !cena.pausado;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyChar() == 'a' || e.getKeyChar() == 'd') cena.q1.setMovendo(false);

        //if(e.getKeyChar() == 'd') cena.q1.setMovendo(false);
    }
}
