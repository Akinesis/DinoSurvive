package texGen;

public class Convertion {



	/**

	 * @param args

	 */

	public static void main(String[] args) {

		float init[] = new float[]{

				0.03125f, 0.03125f,
				0.03125f, 0,
				0, 0.03125f,
				0, 0.03125f,
				0.03125f, 0,
				0, 0,

				0.03125f, 0,
				0, 0,
				0, 0.03125f,
				0.03125f, 0,
				0, 0.03125f,
				0.03125f, 0.03125f,

				//top
				0.03125f, 0.03125f,
				0.03125f, 0,
				0, 0.03125f,
				0, 0.03125f,
				0.03125f, 0,
				0, 0,

				//bottom
				0, 0,
				0, 0.03125f,
				0.03125f, 0,
				0.03125f, 0,
				0, 0.03125f,
				0.03125f, 0.03125f,

				0.03125f, 0.03125f,
				0.03125f, 0,
				0, 0.03125f,
				0, 0.03125f,
				0.03125f, 0,
				0, 0,

				0, 0.03125f,
				0.03125f, 0,
				0, 0,
				0.03125f,0.03125f,
				0.03125f, 0,
				0, 0.03125f


		};


		int x,y;

		float taille = 0.03125f;
		boolean indice = true;


		x=6;

		y=4;

		System.out.println("//north");
		for(int i = 0; i<init.length; i+=2){
			if(indice){
				System.out.println((init[i]+(taille*x))+"f+indiceTextX, "+(init[i+1]+(taille*y))+"f+indiceTextY,");
			}else{
				System.out.println((init[i]+(taille*x))+"f, "+(init[i+1]+(taille*y))+"f,");
			}
			if((i+2)%12==0){System.out.println("");}
			if((i+2)==24){System.out.println("//top");}
			if((i+2)==36){System.out.println("//bottom");}
		}





	}



}
