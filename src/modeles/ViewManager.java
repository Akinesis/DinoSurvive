package modeles;

public class ViewManager { 
//	IntBuffer viewport = ByteBuffer.allocateDirect((Integer.SIZE/8)*16).order(ByteOrder.nativeOrder()).asIntBuffer();
//	FloatBuffer modelview = ByteBuffer.allocateDirect((Float.SIZE/8)*16).order(ByteOrder.nativeOrder()).asFloatBuffer();
//	FloatBuffer projection = ByteBuffer.allocateDirect((Float.SIZE/8)*16).order(ByteOrder.nativeOrder()).asFloatBuffer();
//	FloatBuffer pickingRayBuffer = ByteBuffer.allocateDirect((Float.SIZE/8)*3).order(ByteOrder.nativeOrder()).asFloatBuffer();
//	FloatBuffer zBuffer = ByteBuffer.allocateDirect((Float.SIZE/8)*1).order(ByteOrder.nativeOrder()).asFloatBuffer();
//	glGetFloat(GL_MODELVIEW_MATRIX, modelview);
//	glGetFloat(GL_PROJECTION_MATRIX, projection);
//	glGetInteger(GL_VIEWPORT, viewport);
//	float winX = (float) cursorX;
//	// convert window coordinates to opengl coordinates (top left to bottom left for (0,0)
//	float winY = (float) viewport.get(3) - (float) cursorY;
//
//	// now unproject this to get the  vector in to the screen
//	// take the frustrm and unproject in to the screen
//	// frustrum has a near plane and a far plane
//
//	// first the near vector
//	gluUnProject(winX, winY,  0, modelview, projection, viewport, pickingRayBuffer);        
//	Vector3 nearVector = new Vector3(pickingRayBuffer.get(0),pickingRayBuffer.get(1),pickingRayBuffer.get(2));
//
//	pickingRayBuffer.rewind();
//
//	// now the far vector
//	gluUnProject(winX, winY,  1, modelview, projection, viewport, pickingRayBuffer);
//	Vector3 farVector = new Vector3(pickingRayBuffer.get(0),pickingRayBuffer.get(1),pickingRayBuffer.get(2));
//
//	//save the results in a vector, far-near
//	return farVector.subtractVector(nearVector).normalise();
}
