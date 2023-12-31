package Cena;

import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;

public class Renderer {

    private static GLWindow window = null;
    // TODO: Change the window size
    public static int screenWidth = 1280;  //1280
    public static int screenHeight = 720; //960

    //Cria a janela de rendeziração do JOGL
    public static void init(){
        GLProfile.initSingleton();
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);
        window = GLWindow.create(caps);
        window.setSize(screenWidth, screenHeight);
        window.setResizable(false);
        window.setFullscreen(true);

        Cena cena = new Cena();
        Keyboard keyboard = new Keyboard(cena); //<------------------------------
        Mouse mouse = new Mouse(cena);


        window.addGLEventListener(cena); //adiciona a Cena a Janela
        window.addKeyListener(keyboard); //Habilita o teclado : cena <------------------------------
        window.addMouseListener(mouse); // Habilita o mouse B^)

        //window.requestFocus();
        FPSAnimator animator = new FPSAnimator(window, 60);
        animator.start(); //inicia o loop de animação

        //encerrar a aplicacao adequadamente
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDestroyNotify(WindowEvent e) {
                animator.stop();
                System.exit(0);
            }
        });

        //window.setFullscreen(true);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        init();
    }
}