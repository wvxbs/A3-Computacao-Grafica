package Cena;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import objetos.QuadradoSprite;

public class Cena implements GLEventListener {

    // atributos gerais
    float xtela = 90, ytela = 90;
    private float xMin, xMax, yMin, yMax, zMin, zMax;
    private GL2 gl;
    private GLU glu;
    private GLUT glut;

    // atributos dos quads
    public QuadradoSprite q1, q2;
    public float velPalosx, velPalosY;

    // atributos textura
    public float limite;
    public int filtro = GL2.GL_LINEAR; ////GL_NEAREST ou GL_LINEAR
    public int wrap = GL2.GL_REPEAT;  //GL.GL_REPEAT ou GL.GL_CLAMP
    public int modo = GL2.GL_DECAL; ////GL.GL_MODULATE ou GL.GL_DECAL ou GL.GL_BLEND
    public static final String FACE1 = "imagens/bolinha.png";
    public static final String FACE2 = "imagens/brick.jpg";


    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        glu = new GLU();
        //xMin = yMin = zMin = -1;
        //xMax = yMax = zMax = 1;
        limite = 1;

        gl.glEnable(GL2.GL_DEPTH_TEST);

        // criação de objetos que entrarão em cena
        float[] tamq1 = {30,5}; // tamanho do quadrado 1
        float[] tamq2 = {10,10}; // tamanho do quadrado 2
        float[] corq1 = {0,0,0}; // cor do quadrado 1(tnt faz se tiver textura aplicada)
        q1 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq1,corq1,FACE2);
        q2 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq2,corq1,FACE1);

        // setando vars q1 (barra)
        q1.setVelx(1.5f); // definindo a velocidade x do quadrado 1
        q1.setPosy(-40);

        // setando vars q2 (bola palos)
        q2.setVelx(1);
        q2.setVely(1);

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        teste(drawable);
    }

    // "telas"
    public void teste(GLAutoDrawable drawable){
        gl = drawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo
        q1.desenhar(gl);
        q2.desenhar(gl);

        // movimentar tudo q precisa
        // movimentação q2
        colisaoQ2bordas(); // detectando colisões
        colisaoQ2barra();
        // TODO adicionar uma classe para manipular eventos do jogo (colisão, pontos, etc)
        if (q2.isMovendo()) q2.mover(); // chamando o método de movimento
        gl.glColor4f(1.0f, 1.0f, 1.0f,q2.getAlfa());
        desenhaTexto(gl, (int) (q2.getIntervaloEsquerda()[0][0]),
                (int) q2.getIntervaloCima()[1][1]+10,
                "alfa q2: "+q2.getAlfa());

        // movimentação q1
        colisaoQ1bordas();
        if (q1.isMovendo()) {
            switch (q1.getDirecao()){
                case DIREITA:
                    q1.setVelx(+1);
                    break;

                case ESQUERDA:
                    q1.setVelx(-1);
                    break;

                case CIMA:
                    q1.setVely(+1);
                    break;

                case BAIXO:
                    q1.setVely(-1);
                    break;

                default:
                    q1.setVelx(0);
                    q1.setVely(0);
                    break;
            }
            q1.mover();
        }

        // texto
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        desenhaTexto(gl, -90,90, "q2 -> posx: "+q2.getPosx()+" posy: "+q2.getPosy());
        desenhaTexto(gl, -90,80, "q2 -> " +
                "intervaloX: "+q2.getIntervaloEsquerda()[0][0]+","+q2.getIntervaloEsquerda()[0][1]+
                " | "+q2.getIntervaloDireita()[1][0]+","+q2.getIntervaloDireita()[1][1]+
                " \nintervaloY: "+(-q2.getTamanho()[1]/2)+q2.getPosy()+" | "+(q2.getTamanho()[1]/2)+q2.getPosy());

        desenhaTexto(gl, -90,70, "q1 -> posx: "+q1.getPosx()+" posy: "+q1.getPosy());
        desenhaTexto(gl, -90,60, "q1 -> " +
                "intervaloX: "+q1.getIntervaloEsquerda()[0][0]+","+q1.getIntervaloEsquerda()[0][1]+
                " | "+q1.getIntervaloDireita()[1][0]+","+q1.getIntervaloDireita()[1][1]+
                " \nintervaloY: "+(-q1.getTamanho()[1]/2)+q1.getPosy()+" | "+(q1.getTamanho()[1]/2)+q1.getPosy());


        gl.glFlush();
    }

    // métodos
    public void desenhaTexto(GL2 gl, int x, int y, String frase) {
        glut = new GLUT(); //objeto da biblioteca glut
        gl.glRasterPos2f(x, y);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, frase);
    }

    public void colisaoQ2bordas(){
        // definindo as bordas que interagirão com o quadrado q2
        float[][][] bordas = {
                {{-100, 100}, {100, 100}}, // bordas cima ((x0-x1), (y0-y1))
                {{-100, 100}, {-100, -100}}, // bordas baixo ((x0-x1), (y0-y1))
                {{100, 100}, {-100, 100}}, // bordas direita ((x0-x1), (y0-y1))
                {{-100, -100}, {-100, 100}} // bordas esquerda ((x0-x1), (y0-y1))

        };

        // criando vars de controle que receberão a resposta do teste de colisão
        boolean colisaoQ2cima, colisaoQ2baixo, colisaoQ2direita, colisaoQ2esquerda;

        // obtendo resposta se houve colisão ou não
        colisaoQ2cima = q2.isColiding(bordas[0][0], bordas[0][1]);
        colisaoQ2baixo = q2.isColiding(bordas[1][0], bordas[1][1]);
        colisaoQ2direita = q2.isColiding(bordas[2][0], bordas[2][1]);;
        colisaoQ2esquerda = q2.isColiding(bordas[3][0], bordas[3][1]);

        // feedback na tela
        desenhaTexto(gl,-90,10,"colidiu Cima: "+colisaoQ2cima);
        desenhaTexto(gl,-90,0,"colidiu baixo: "+colisaoQ2baixo);
        desenhaTexto(gl,-90,-10,"q2 colidiu direita: "+colisaoQ2direita);
        desenhaTexto(gl,-90,-20,"q2 colidiu esquerda: "+colisaoQ2esquerda);

        // definindo interação caso às condições sejam atendidas
        if (colisaoQ2cima || colisaoQ2baixo){q2.setVely(q2.getVely()*-1);}
        if (colisaoQ2direita || colisaoQ2esquerda){q2.setVelx(q2.getVelx()*-1);}
    }

    public void colisaoQ1bordas(){
        // definindo as bordas que interagirão com o quadrado q2
        float[][][] bordas = {
                {{100, 100}, {-100, 100}}, // bordas direita ((x0-x1), (y0-y1))
                {{-100, -100}, {-100, 100}} // bordas esquerda ((x0-x1), (y0-y1))

        };

        // criando vars de controle que receberão a resposta do teste de colisão
        boolean colisaoQ1direita, colisaoQ1esquerda;

        // obtendo resposta se houve colisão ou não
        colisaoQ1direita = q1.isColiding(bordas[0][0], bordas[0][1]);;
        colisaoQ1esquerda = q1.isColiding(bordas[1][0], bordas[1][1]);

        //feedback
        desenhaTexto(gl,-90,-30,"q1 colidiu direita: "+colisaoQ1direita);
        desenhaTexto(gl,-90,-40,"q1 colidiu esquerda: "+colisaoQ1esquerda);

        // definindo interação caso às condições sejam atendidas
        if (colisaoQ1direita && q1.getVelx() == 1) q1.setVelx(0);
        if (colisaoQ1esquerda && q1.getVelx() == -1) q1.setVelx(0);
    }

    public void colisaoQ2barra(){

        // obtendo feedback e armazenando
        boolean colisao = q2.isColiding(q1.getIntervaloCima()[0],q1.getIntervaloDireita()[1]);

        // definindo interações
        if (colisao){q2.setVely(q2.getVely()*-1);}

    }


    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //obtem o contexto grafico Opengl
        gl = drawable.getGL().getGL2();

        //evita a divisão por zero
        //if(height == 0) height = 1;
        //calcula a proporção da janela (aspect ratio) da nova janela
        //float aspect = (float) width / height;

        //seta o viewport para abranger a janela inteira
        //gl.glViewport(0, 0, width, height);

        //ativa a matriz de projeção
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity(); //lê a matriz identidade

        //Projeção ortogonal
        gl.glOrtho(-100, 100, -100, 100, -100, 100);

        //ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        //gl.glLoadIdentity(); //lê a matriz identidade
        System.out.println("Reshape: " + width + ", " + height);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {}

}
