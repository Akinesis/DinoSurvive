package vues;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;
import static org.lwjgl.util.glu.GLU.gluUnProject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

import parametres.Parametres;

public class OpenGL implements Parametres {

	private int haut, larg;
	
	public OpenGL(){
		haut = hauteur;
		larg = largeur;
	}
	
	public void init3D(){
		glViewport(0, 0, larg, haut);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(fov, (float)larg/(float)haut, near, far);
		glMatrixMode(GL_MODELVIEW);	
		enable();
		fogCreate();
		glLoadIdentity();
	}

	public void reset3D(){
		glViewport(0, 0, larg, haut);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(fov, (float)larg/(float)haut, near, far);
		glMatrixMode(GL_MODELVIEW);	
		enable();
	}

	private void enable(){
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public void init2D(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, larg, haut, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glDisable(GL_DEPTH_TEST);
	}

	private void fogCreate(){
		//cr�ation du fog

		//floatBuffer avec la le fog
		FloatBuffer fogColours = BufferUtils.createFloatBuffer(4);
		fogColours.put(new float[]{0.0f, 0.8f, 0.95f, 1f});
		glClearColor(0.0f, 0.8f, 0.95f, 1f);
		fogColours.flip();

		//les option du fog
		glFog(GL_FOG_COLOR, fogColours);
		glFogi(GL_FOG_MODE, GL_LINEAR);
		glHint(GL_FOG_HINT, GL_NICEST);
		glFogf(GL_FOG_START, 5);
		glFogf(GL_FOG_END, 5);
		glFogf(GL_FOG_DENSITY, 0.05f);

	}
	
	public void setSize(int ht, int la){
		haut = ht;
		larg = la;
	}
	
	public Vector3f getPickingRay(float cursorX,float cursorY){    
        IntBuffer viewport = ByteBuffer.allocateDirect((Integer.SIZE/8)*16).order(ByteOrder.nativeOrder()).asIntBuffer();
        FloatBuffer modelview = ByteBuffer.allocateDirect((Float.SIZE/8)*16).order(ByteOrder.nativeOrder()).asFloatBuffer();
        FloatBuffer projection = ByteBuffer.allocateDirect((Float.SIZE/8)*16).order(ByteOrder.nativeOrder()).asFloatBuffer();
        FloatBuffer pickingRayBuffer = ByteBuffer.allocateDirect((Float.SIZE/8)*3).order(ByteOrder.nativeOrder()).asFloatBuffer();
        FloatBuffer zBuffer = ByteBuffer.allocateDirect((Float.SIZE/8)*1).order(ByteOrder.nativeOrder()).asFloatBuffer();
        glGetFloat(GL_MODELVIEW_MATRIX, modelview);
        glGetFloat(GL_PROJECTION_MATRIX, projection);
        glGetInteger(GL_VIEWPORT, viewport);
        float winX = (float) cursorX;
        //float winX = haut/2;
        // convert window coordinates to opengl coordinates (top left to bottom left for (0,0)
        float winY = (float) viewport.get(3) - (float) cursorY;
        //float winY = larg/2;
       
        // now unproject this to get the  vector in to the screen
        // take the frustrm and unproject in to the screen
        // frustrum has a near plane and a far plane
       
        // first the near vector
        gluUnProject(winX, winY,  0, modelview, projection, viewport, pickingRayBuffer);        
        Vector3f nearVector = new Vector3f(pickingRayBuffer.get(0),pickingRayBuffer.get(1),pickingRayBuffer.get(2));
       
        pickingRayBuffer.rewind();
       
        // now the far vector
        gluUnProject(winX, winY,  1, modelview, projection, viewport, pickingRayBuffer);
        Vector3f farVector = new Vector3f(pickingRayBuffer.get(0),pickingRayBuffer.get(1),pickingRayBuffer.get(2));
       
        //save the results in a vector, far-near
        Vector3f temp = new Vector3f();
        Vector3f.sub(farVector, nearVector, temp).normalise();
        return temp;
    }
	
	public Vector3f getPickingRayBis(float cursorX,float cursorY){
		
		float[] matModelView = new float[16], matProjView = new float[16];
        int[] view = new int[16];
        float mouseX = cursorX;
        float mouseY = cursorY;
        Vector3f start = new Vector3f();
        Vector3f end = new Vector3f();

        FloatBuffer modelBuffer = compileBuffer(matModelView);
        FloatBuffer projBuffer = compileBuffer(matProjView);
        FloatBuffer startBuffer = compileBuffer(new float[]{start.x, start.y, start.z, 1});
        FloatBuffer endBuffer = compileBuffer(new float[]{end.x, end.y, end.z, 1});
        IntBuffer viewBuffer = compileBuffer(view);

        glGetFloat(GL_MODELVIEW_MATRIX, modelBuffer);
        glGetFloat(GL_PROJECTION_MATRIX, projBuffer);
        glGetInteger(GL_VIEWPORT, viewBuffer);

        gluUnProject(mouseX, mouseY, 0.0f, modelBuffer, projBuffer, viewBuffer, startBuffer);
        gluUnProject(mouseX, mouseY, 1.0f, modelBuffer, projBuffer, viewBuffer, endBuffer);

        start = new Vector3f(startBuffer.get(0), startBuffer.get(1), startBuffer.get(2));
        end = new Vector3f(endBuffer.get(0), endBuffer.get(1), endBuffer.get(2));
        
        Vector3f ret = new Vector3f();
        ret.x = end.x - start.x;
        ret.y = end.y - start.y;
        ret.z = end.z - start.z;
        
        ret = ret.normalise(ret);
        
        System.out.println("******************************");
        System.out.println(start);
        System.out.println(end);
        System.out.println(ret);
        System.out.println("******************************");
        
        return ret;
	}
	
	private FloatBuffer compileBuffer(float[] floats) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(floats.length);
        buffer.put(floats);
        buffer.flip();
        return buffer;
    }

private IntBuffer compileBuffer(int[] ints) {
        IntBuffer buffer = BufferUtils.createIntBuffer(ints.length);
        buffer.put(ints);
        buffer.flip();
        return buffer;
    }

}
