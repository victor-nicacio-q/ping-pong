import java.awt.*;
import java.util.Random;

/**
*	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
*	instancia um objeto deste tipo quando a execução é iniciada.
*/

public class Ball {

	double cx;
	double cy;
	double width;
	double height;
	Color color;
	double speed;
	double xDir, yDir;

	/**
	*	Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
	*	(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
	*	aleatóriamente pelo construtor.
	*
	*	@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
	*	@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
	*	@param width largura do retangulo que representa a bola.
	*	@param height altura do retangulo que representa a bola.
	*	@param color cor da bola.
	*	@param speed velocidade da bola (em pixels por millisegundo).
	*/

	public Ball(double cx, double cy, double width, double height, Color color, double speed){
		Random random = new Random();

		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.speed = speed;

		this.xDir = random.nextInt(2);
		this.yDir = random.nextInt(2);
	}


	/**
	*	Método chamado sempre que a bola precisa ser (re)desenhada.
	*/
	public void draw(){
		GameLib.setColor(getColor());
		GameLib.fillRect(getCx(), getCy(), getWidth(), getHeight());
	}

	/**
	*	Método chamado quando o estado (posição) da bola precisa ser atualizado.
	*	
	*	@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/
	public void update(long delta){
		this.cx += delta * getxDir();
		if(getxDir() == 0) this.xDir = -getSpeed();
		else if(getxDir() == 1) this.xDir = getSpeed();

		this.cy += delta * getyDir();
		if(getyDir() == 0) this.yDir = -getSpeed();
		else if(getyDir() == 1) this.yDir = getSpeed();
	}

	/**
	*	Método chamado quando detecta-se uma colisão da bola com um jogador.
	*
	*	@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/
	public void onPlayerCollision(String playerId){
		if(playerId.equals("Player 1")) this.xDir = getSpeed();
		else this.xDir = -getSpeed();
	}

	/**
	*	Método chamado quando detecta-se uma colisão da bola com uma parede.
	*
	*	@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/
	public void onWallCollision(String wallId){
		if (wallId.equals("Top")) this.yDir = getSpeed();
		else if (wallId.equals("Bottom")) this.yDir = -getSpeed();
		else if (wallId.equals("Left")) this.xDir = getSpeed();
		else if (wallId.equals("Right")) this.xDir = -getSpeed();
	}

	/**
	*	Método que verifica se houve colisão da bola com uma parede.
	*
	*	@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
	*	@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	public boolean checkCollision(Wall wall){
		double meia_altura = getHeight()/2;
		double meia_largura = getWidth()/2;

		if(wall.getId().equals("Top")){
			if(getCy() - meia_altura <= wall.getCy() + (wall.getHeight()/2))
				return true;
		}else if (wall.getId().equals("Bottom")) {
			if(getCy() + meia_altura >= wall.getCy() - (wall.getHeight()/2))
				return true;
		}else if (wall.getId().equals("Left")){
			if(getCx() - meia_largura <= wall.getCx() + (wall.getWidth()/2))
				return true;
		}else if (wall.getId().equals("Right")){
			if(getCx() + meia_largura >= wall.getCx() - (wall.getWidth()/2))
				return true;
		}

		return false;
	}

	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	

	public boolean checkCollision(Player player){
		double meia_altura = getHeight()/2;
		double meia_largura = getWidth()/2;

		if(player.getId().equals("Player 1")){
			if (getCx() - meia_largura <= player.getCx() + player.getWidth()/2 && getCy() + meia_largura >= player.getCy() - player.getHeight()/2 && getCy() - meia_largura <= player.getCy() + player.getHeight()/2
			) return true;
		}else {
			if(getCx() + meia_largura >= player.getCx() - player.getWidth()/2 && getCy() + meia_altura >= player.getCy() - player.getHeight()/2 && getCy() - meia_altura <= player.getCy() + player.getHeight()/2
			) return true;
		}
		return false;
	}

	public double getCx() { return this.cx; }

	public double getCy() { return this.cy; }

	public double getSpeed() { return this.speed; }

	public Color getColor() { return this.color; }

	public double getWidth() { return this.width; }

	public double getHeight() { return this.height; }

	public double getxDir() { return this.xDir; }

	public double getyDir() { return this.yDir; }
}
