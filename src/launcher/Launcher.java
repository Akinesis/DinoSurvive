package launcher;

import modeles.entities2D.Menu;

import org.lwjgl.input.Keyboard;

import vues.GameDisplay;
import vues.OpenGL;
import controleur.Game;

public class Launcher {

	//juste le main
	public static void main(String[] args) {
		Game game = new Game();
/*
		OpenGL matrices = new OpenGL();
		GameDisplay display = new GameDisplay();
		display.create();
		matrices.init3D();
		
	*/	
		
		
		game.start();
	}

}
