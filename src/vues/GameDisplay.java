package vues;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import parametres.Parametres;



public class GameDisplay implements Parametres{
	
	public GameDisplay(){
		
	}
	
	public void create(){
		try{
			Display.setResizable(true);
			Display.setDisplayMode(new DisplayMode((int)largeur, (int)hauteur));
			Display.setTitle("Dino Survive");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		
		Mouse.setGrabbed(true);
	}
	
	public boolean isClose(){
		return Display.isCloseRequested();
	}
	
	public void close(){
		Display.destroy();
	}
	
	public void update(){
		Display.update();
		Display.sync(60);
	}
	
	public void changeGrabeMouse(){
		Mouse.setGrabbed(!Mouse.isGrabbed());
	}
	
	public void end(){
		Display.destroy();
	}
	
	public int getHeight(){
		return Display.getHeight();
	}
	
	public int getWidth(){
		return Display.getWidth();
	}
	
	public boolean isResized(){
		return Display.wasResized();
	}

}
