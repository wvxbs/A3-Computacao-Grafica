package Cena;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import objetos.QuadradoSprite;

public class Cena implements GLEventListener {

    // atributos gerais
    boolean pausado = false;
    private float xMin, xMax, yMin, yMax, zMin, zMax;
    private GL2 gl;
    private GLU glu;
    private GLUT glut;

    private boolean passou=false;
    private int pontos=0;
    // atributos dos quads
    public QuadradoSprite q1, q2, q3, q4, q5;

    // atributos textura
    public float limite;
    public int filtro = GL2.GL_LINEAR; ////GL_NEAREST ou GL_LINEAR
    public int wrap = GL2.GL_REPEAT;  //GL.GL_REPEAT ou GL.GL_CLAMP
    public int modo = GL2.GL_DECAL; ////GL.GL_MODULATE ou GL.GL_DECAL ou GL.GL_BLEND
    public static final String FACE2 = "imagens/palosInterrogacao1-1.png";
    public static final String FACE1 = "imagens/brick.jpg";
    public static final String FACE3 = "imagens/background.png";
    public static final String FACE4 = "imagens/ferreira1-1.png";
    public static final String FACE5 = "imagens/ben_andando.png";

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        glu = new GLU();
        //xMin = yMin = zMin = -1;
        //xMax = yMax = zMax = 1;
        limite = 1;

        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_BLEND);

        // criação de objetos que entrarão em cena
        float[] tamq1 = {30,5}; // tamanho do quadrado 1
        float[] tamq2 = {50,50}; // tamanho do quadrado 2
        float[] tamq3 = {200,200}; // tamanho do quadrado 2
        float[] tamq4 = {50,50}; // tamanho do quadrado 2

        float[] corq1 = {0,0,0}; // cor do quadrado 1(tnt faz se tiver textura aplicada(aparentemente faz ss))

        q1 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq1,corq1,FACE1,false);
        q2 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq2,corq1,FACE2,false);
        q3 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq3,corq1,FACE3,false);
        q4 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq3,corq1,FACE4,false);
        //q5 = new QuadradoSprite(4,filtro,wrap,modo,limite,tamq4,corq1,FACE5,true);


        // conigurando q1 (barra)
        q1.setVelx(1.5f); // definindo a velocidade x do quadrado 1
        q1.setPosy(-60);

        // conigurando q2 (bola palos)
        q2.setVelx(1);
        q2.setVely(1);

        // conigurando q3 (background)
        q3.setPosz(-0.1f);

        // configurando q4 ()
        q4.setPosz(-0.1f);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        if (pausado){pause(drawable);}
        else {
            switch (pontos){
                case 0: // fase 1
                    teste(drawable);
                    break;

                case 200: // fase 2
                    teste(drawable);
                    break;

                default:
                    menu(drawable);
            }


        }

    }
    // "telas"

    public void pause(GLAutoDrawable drawable){ // TODO fazer pause bunitin
        gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo
        q4.desenhar(gl);

        // texto do menu
        gl.glColor3f(1f, 1f, 1f);
        desenhaTexto(gl, -5,80, "PAUSADO",24);
        desenhaTexto(gl, -20,60, "pressione X para continuar",18);

        desenhaTexto(gl, -20,-90, "Pressione ESC para sair",18);
        desenhaTexto(gl, -10,40, "Pontos: (TBA)",18);


        gl.glFlush();
    }

    public void menu(GLAutoDrawable drawable){ // TODO fazer pause bunitin
        gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo
        q4.desenhar(gl);

        // texto do menu
        gl.glColor3f(1f, 1f, 1f);
        desenhaTexto(gl, -5,80, "PAUSADO",24);
        desenhaTexto(gl, -20,60, "pressione X para continuar",18);

        desenhaTexto(gl, -20,-90, "Pressione ESC para sair",18);
        desenhaTexto(gl, -10,40, "Pontos: (TBA)",18);


        gl.glFlush();
    }


    public void teste(GLAutoDrawable drawable){
        gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo
        q1.desenhar(gl);
        q2.desenhar(gl);
        q3.desenhar(gl);

        // movimentar tudo q precisa

        if (!pausado){// TODO adicionar uma classe para manipular eventos do jogo (colisão, pontos, etc)

            // movimentação q2
            colisaoQ2bordas(); // detectando colisões
            colisaoQ2barra();
            if (q2.isMovendo()) q2.mover(); // chamando o método de movimento

            gl.glColor4f(1.0f, 1.0f, 1.0f, q2.getAlfa());
            desenhaTexto(gl, (int) (q2.getIntervaloEsquerda()[0][0]),
                    (int) q2.getIntervaloCima()[1][1] + 10,
                    "alfa q2: " + q2.getAlfa());

            // movimentação q1
            if (q1.isMovendo()) {
                switch (q1.getDirecao()) {
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
                colisaoQ1bordas();
                q1.mover();
            }
        }

        gl.glFlush();
    }


    // métodos
    public void desenhaTexto(GL2 gl, int x, int y, String frase) {
        glut = new GLUT(); //objeto da biblioteca glut
        gl.glRasterPos2f(x, y);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, frase);
    }
    public void desenhaTexto(GL2 gl, int x, int y, String frase,int tamFonte) {
        glut = new GLUT(); //objeto da biblioteca glut
        gl.glRasterPos2f(x, y);
        switch (tamFonte){
            case 12:
                glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, frase);
                break;
            case 18:
                glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, frase);
                break;
            case 24:
                glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, frase);
                break;
            default:
                glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, frase);
                break;
        }
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
        colisaoQ1direita = q1.isColiding(bordas[0][0], bordas[0][1]);
        colisaoQ1esquerda = q1.isColiding(bordas[1][0], bordas[1][1]);

        //feedback
        desenhaTexto(gl, (int) q1.getIntervaloBaixo()[0][0],
                (int) (q1.getIntervaloBaixo()[1][0]-5),"q1 colidiu direita: "+colisaoQ1direita,18);

        desenhaTexto(gl, (int) q1.getIntervaloBaixo()[0][0],
                (int) (q1.getIntervaloBaixo()[1][0]-10),"q1 colidiu esquerda: "+colisaoQ1esquerda,18);

        // definindo interação caso às condições sejam atendidas
        if (colisaoQ1direita && q1.getVelx() == 1) q1.setVelx(0);
        if (colisaoQ1esquerda && q1.getVelx() == -1) q1.setVelx(0);
    }

    public void colisaoQ2barra(){

        // obtendo feedback e armazenando
        boolean colisaoY,colisaoX;

        colisaoY = q2.isColiding(q1.getIntervaloCima()[0],q1.getIntervaloCima()[1]) ||
                q2.isColiding(q1.getIntervaloBaixo()[0],q1.getIntervaloBaixo()[1]);

        colisaoX = q2.isColiding(q1.getIntervaloDireita()[0],q1.getIntervaloDireita()[1]) ||
                q2.isColiding(q1.getIntervaloEsquerda()[0],q1.getIntervaloEsquerda()[1]);

        // definindo interações
        if (colisaoY){q2.setVely(q2.getVely()*-1);}
        if (colisaoX){q2.setVelx(q2.getVelx()*-1);}

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
