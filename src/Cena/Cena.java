package Cena;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class Cena implements GLEventListener {
    private float xMin, xMax, yMin, yMax, zMin, zMax;
    GLU glu;

    // TODO: Insert here values to the figure
    //
    //


    // TODO: Get here the objects to the scene
    //
    //

    @Override
    public void init(GLAutoDrawable drawable) {
        glu = new GLU();
        xMin = yMin = zMin = -1;
        xMax = yMax = zMax = 1;
    }



    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 1); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT); // Cleans the window with the color especified
        gl.glLoadIdentity(); // Reads the matrix identity


        // SCENE
        //
        //

        // Example Scene
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-.4f, -.5f);
        gl.glVertex2f(-.4f, .2f);
        gl.glVertex2f(-.1f, .2f);
        gl.glVertex2f(-.1f, -.5f);
        gl.glEnd();



        gl.glFlush();
    }



    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //obtem o contexto grafico Opengl
        GL2 gl = drawable.getGL().getGL2();

        //evita a divisão por zero
        if(height == 0) height = 1;
        //calcula a proporção da janela (aspect ratio) da nova janela
        float aspect = (float) width / height;

        //seta o viewport para abranger a janela inteira
        gl.glViewport(0, 0, width, height);

        //ativa a matriz de projeção
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity(); //lê a matriz identidade

        //Projeção ortogonal
        //true:   aspect >= 1 configura a altura de -1 para 1 : com largura maior
        //false:  aspect < 1 configura a largura de -1 para 1 : com altura maior
        if(width >= height)
            gl.glOrtho(xMin * aspect, xMax * aspect, yMin, yMax, zMin, zMax);
        else
            gl.glOrtho(xMin, xMax, yMin / aspect, yMax / aspect, zMin, zMax);

        //ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); //lê a matriz identidade
        System.out.println("Reshape: " + width + ", " + height);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {}
}
