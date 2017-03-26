import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Vertex implements Comparable{

	public String name;
	public Map<Vertex,Edge>adj;
	//public Map<Vertex,Boolean> edgeDown;
	public boolean down;
	
	public Vertex(String name){
		this.name = name;
		adj = new TreeMap<Vertex,Edge>();
		down = false;
	}
	
	public void addEdge(Vertex v, Float f){
		Edge e = new Edge(v,f);
		adj.put(v,e);
	}
	
	
	/*
	 * Clears all the edges of vertex
	 */
	public void reset(){
		adj.clear();
	}
	
	/*
	 * Prints all the edges and weight in alphabetical order
	 */
	public void printEdges(){
		System.out.println("");
		for(Map.Entry<Vertex,Edge> entry : adj.entrySet()){
			System.out.println("\t"+entry.getKey().name+" "+entry.getValue());
		}	
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Vertex v =(Vertex) arg0;
		return (this.name.compareTo(v.name));
	}

}

class Edge{
	Vertex vertex;
	Float weight;
	boolean down;
	
	public Edge(Vertex v, Float weight){
		this.vertex = v;
		this.weight = weight;
		down = false;
	}
	
	@Override
	public String toString(){
		String str =new Float(weight).toString();
		if(down)
			str+=" DOWN";
	
		return str;
	}
	
}