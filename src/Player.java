import java.awt.*;
import java.awt.event.KeyEvent;

/**
	Esta classe representa os jogadores (players) do jogo. A classe princial do jogo (Pong)
	instancia dois objetos deste tipo quando a execução é iniciada.
*/

public class Player {
	double cy;
	double height;
	double cx;
	double width;
	double speed;
	double[] v_limit;
	String id;
	Color color;
	double meia_altura = this.height/2;
	double meia_largura = this.width/2;

	/**
		Construtor da classe Player.

		@param cx coordenada x da posição inicial do player (centro do retangulo que o representa).
		@param cy coordenada y da posição inicial do player (centro do retangulo que o representa).
		@param width largura do retangulo que representa o player.
		@param height altura do retangulo que representa o player.
		@param color cor do player.
		@param id uma string que identifica o player
		@param v_limit um array de double contendo dois valores (em pixels) que determinam os limites verticais da área útil da quadra.   
		@param speed velocidade do movimento vertical do player (em pixels por millisegundo).
	*/

	public Player(double cx, double cy, double width, double height, Color color, String id, double [] v_limit, double speed){
		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.id = id;
		this.v_limit = v_limit;
		this.speed = speed;
	}

	/**
		Método chamado sempre que o player precisa ser (re)desenhado.
	*/

	public void draw(){

		GameLib.setColor(getColor());
		GameLib.fillRect(getCx(), getCy(), getWidth(), getHeight());
	}

	/**
		Método chamado quando se deseja mover o player para cima. 
		Este método é chamado sempre que a tecla associada à ação 
		de mover o player para cima estiver pressionada.

		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void moveUp(long delta){
		double aux = delta *getSpeed();
		if(getCy() - getHeight()/2 - aux < getV_limit(0)) aux = (getCy() - getHeight()/2) - getV_limit(0);
		this.cy -= aux;
	}

	/**
		Método chamado quando se deseja mover o player para baixo. 
		Este método é chamado sempre que a tecla associada à ação 
		de mover o player para baixo estiver pressionada.

		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void moveDown(long delta){

		double aux = delta *getSpeed();
		if(getCy() + getHeight()/2 + aux > getV_limit(1)) aux = getV_limit(1) - (getCy() + getHeight()/2);
		this.cy += aux;
	}

	/**
		Método que devolve a string de identificação do player.
		@return a String de indentificação.
	*/

	public String getId() {
		return this.id;
	}

	/**
		Método que devolve a largura do retangulo que representa o player.
		@return um double com o valor da largura.
	*/

	public double getWidth() {
		return this.width;
	}

	/**
		Método que devolve a algura do retangulo que representa o player.
		@return um double com o valor da altura.
	*/

	public double getHeight() {

		return this.height;
	}

	/**
		Método que devolve a coordenada x do centro do retangulo que representa o player.
		@return o valor double da coordenada x.
	*/

	public double getCx() {
		return this.cx;
	}

	/**
		Método que devolve a coordenada y do centro do retangulo que representa o player.
		@return o valor double da coordenada y.
	*/

	public double getCy() {

		return this.cy;
	}

	public Color getColor() {
		return this.color;
	}

	public double getSpeed() {
		return this.speed;
	}

	public double getV_limit(int index) {
		if(index == 0) return v_limit[0];
		else if(index == 1) return v_limit[1];

		return 0;
	}
}

