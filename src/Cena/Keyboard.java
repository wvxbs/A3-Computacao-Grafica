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
            //cena.ReproduzirEfeitoSonoro("coin");
            cena.bolinha.getObjSprite().setMovendo(!cena.bolinha.getObjSprite().isMovendo());
            cena.jogador.setJogando(true);
        }
        if(e.getKeyChar() == '5'){ // alterna a utilização do mouse
            //cena.ReproduzirEfeitoSonoro("coin");
            cena.mouseHabilitado = !cena.mouseHabilitado;
        }
        if(e.getKeyChar() == 'm'){
            cena.AlternarEfeitoSonoroEmLoop();
        }

        switch (cena.iter_telas){
            case 0:
                if(e.getKeyChar() == 'p'){ // inicia o jogo
                    cena.jogador.setFase(1);
                    cena.iter_telas = cena.jogador.getFase();
                }
                if(e.getKeyChar() == '3'){ // inicia o jogo
                    cena.jogador.setFase(3);
                    cena.iter_telas = cena.jogador.getFase();
                }
                break;

            case 1:
            case 2:
            case 3:
                if(e.getKeyChar() == 'x'){ // pause
                cena.jogador.setPausado(true);
            }
                break;

            case 4:
                if(e.getKeyChar() == 'x'){ cena.jogador.setPausado(false);}

                break;

            case 5:
                break;

            case 6:
                break;

            case 7:
                if(e.getKeyChar() == 's'){ // próxima fase
                    cena.jogador.setFase(cena.jogador.getFase()+1);
                    cena.iter_telas = cena.jogador.getFase();
                    cena.jogador.setJogando(true);
                }
                break;

            default:
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!cena.mouseHabilitado) {
            if (e.getKeyChar() == 'a' || e.getKeyChar() == 'd') cena.jogador.getObjSprite().setMovendo(false);
        }
    }
}
