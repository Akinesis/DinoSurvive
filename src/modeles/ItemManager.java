package modeles;


import java.sql.*;
import java.sql.SQLException;

import modeles.entities.Cube3dVbo;
import modeles.entities.FlatItemVBO;
import game.item.Item;
import controleur.Controleur;

public class ItemManager {
	
	Controleur clone;
	DropManager dropManager;
	private BDDConector database;
	
	public ItemManager(Controleur contr){
		clone = contr;
		dropManager = new DropManager();
		database = new BDDConector();
		
		coonectBDD();
		
		stopBDD();
	}
	
	public void addDropOnFoot(Item item){
		FlatItemVBO temp = new FlatItemVBO(10, 10, 10, item.getIdentifiant()); //donnée de l'item et du joueur.
		dropManager.addDrop(temp);
	}
	
	public void addDropOnCube(Item item, Cube3dVbo cube){
		FlatItemVBO temp = new FlatItemVBO(cube.getX()+0.5f, cube.getY(), cube.getZ()-0.5f, item.getIdentifiant()); //à modifier un peut.
		dropManager.addDrop(temp);
	}
	
	private void coonectBDD(){
		try {
			database.connexionBDD();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void stopBDD(){
		database.arretBDD();
	}
	
	public void createItem(int id){
		PreparedStatement prepStatement;
		try {
			prepStatement = database.getConnetion().prepareStatement("SELECT* FROM DINODATA.items WHERE ID=?");
			prepStatement.setInt(1,id);
			
			ResultSet res = prepStatement.executeQuery();
			
			while(res.next()){
				String nature = res.getString("nature"); //etc...
				/*
				 * TO DO :
				 * écrire la création d'objet içi une fois les obhjets définits.
				 */
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

}
