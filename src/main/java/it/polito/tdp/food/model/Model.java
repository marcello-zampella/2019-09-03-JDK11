package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	private FoodDao dao;
	private SimpleWeightedGraph<String, DefaultWeightedEdge> grafo;
	
	public Model() {
		dao= new FoodDao();
	}

	public ArrayList<String> creaGrafo(int calorie) {
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		ArrayList<String> porzioni=this.dao.listCondimentCalories(calorie);
		System.out.println(porzioni.contains("teaspoon"));
		Graphs.addAllVertices(grafo, porzioni);
		ArrayList<Collegamento> coll=dao.getCollegamenti(calorie);
		for(Collegamento c: coll) {
		Graphs.addEdge(grafo, c.getP1(), c.getP2(), c.getPeso());
		}
		return porzioni;
	}

	public ArrayList<Vicino> cercaVicini(String porzione) {
		ArrayList<Vicino> vicini=new ArrayList<Vicino>();
		String porz=null;
		for (DefaultWeightedEdge arco : grafo.outgoingEdgesOf(porzione)) {
    		if(!grafo.getEdgeSource(arco).equals(porzione)) {
    			porz=grafo.getEdgeSource(arco);
    		}
    		else
    			porz=grafo.getEdgeTarget(arco);
    		vicini.add(new Vicino(porz,grafo.getEdgeWeight(arco)));
	}
		return vicini;
	}

	public SimpleWeightedGraph<String, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
	

	public LinkedList<Vicino> cercaCammino(String porzione, int passi) {
		LinkedList<Vicino> percorso=new LinkedList<Vicino>();
		totale=0;
		int livello=0;
		HashMap<String,Double> inseriti=new HashMap<String,Double>();
		massimo=null;
		this.passi=passi;
		this.espandi(porzione, percorso, livello);
		System.out.println(massimo);
		return massimo;
		
		
	}
	
	private double totale;
	private LinkedList<Vicino> massimo;	
	private int passi;
	
	

	public double getTotale() {
		return totale;
	}

	private void espandi(String porzione, LinkedList<Vicino> percorso, int livello) {
		if(livello==passi) {
			double temp=0;
			for(Vicino x: percorso) {
				temp+=x.getPeso();
			}
			if(temp>totale) {
				massimo=new LinkedList<Vicino>(percorso);
				totale=temp;
			}
			return;
		}
		
		for( Vicino v : this.cercaVicini(porzione)) {
			if(!percorso.contains(v)) {
			percorso.addLast(v);
			espandi(v.vicino, percorso,livello+1 );
			percorso.removeLast();
			}
		}
		
		
	}
	
	
	
	
	
	
}
