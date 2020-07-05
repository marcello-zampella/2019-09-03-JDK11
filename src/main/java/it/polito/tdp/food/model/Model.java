package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	private FoodDao dao;
	private SimpleWeightedGraph<Portion, DefaultWeightedEdge> grafo;
	
	public Model() {
		dao= new FoodDao();
	}

	public ArrayList<Portion> creaGrafo(int calorie) {
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		ArrayList<Portion> porzioni=this.dao.listCondimentCalories(calorie);
		Graphs.addAllVertices(grafo, porzioni);
		ArrayList<Collegamento> coll=dao.getCollegamenti(calorie);
		for(Collegamento c: coll) {
		Graphs.addEdge(grafo, c.getP1(), c.getP2(), c.getPeso());
		}
		return porzioni;
	}

	public Set<DefaultWeightedEdge> cercaVicini(Portion value) {
		return grafo.outgoingEdgesOf(value);
		
	}

	public SimpleWeightedGraph<Portion, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
	
	
	
	
	
}
