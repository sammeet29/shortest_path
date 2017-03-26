import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

public class Graph {
	public static final int INFINITY = Integer.MAX_VALUE;
	private Map<String, Vertex> vertexMap = new TreeMap<String, Vertex>();

	/**
	 * Add bi-directed edge to the graph.
	 */
	public void addBiEdge(String sourceName, String destName, Float weight) {
		Vertex v = getVertex(sourceName);
		Vertex w = getVertex(destName);
		v.addEdge(w, weight);
		w.addEdge(v, weight);
	}

	/*
	 * Add an edge in graph from source to destination
	 */
	public void addEdge(String sourceName, String destName, Float weight) {
		Vertex v = getVertex(sourceName);
		Vertex w = getVertex(destName);
		v.addEdge(w, weight);
	}

	/*
	 * Delete a edge from graph
	 */
	public void deleteEdge(String sourceName, String destName) {
		Vertex s = vertexMap.get(sourceName);
		if (s == null)
			throw new NoSuchElementException("Source node not found");
		Vertex d = vertexMap.get(destName);
		if (d == null)
			throw new NoSuchElementException("Destination node not found");
		s.adj.remove(d);
	}

	/*
     * 
     */
	public void edgeDown(String sourceName, String destName) {
		Vertex s = vertexMap.get(sourceName);
		if (s == null)
			throw new NoSuchElementException("Source node not found");
		Vertex d = vertexMap.get(destName);
		if (d == null)
			throw new NoSuchElementException("Destination node not found");
		Edge e = s.adj.get(d);
		if (e == null)
			throw new NoSuchElementException("Edge does not exist");

		e.down = true;
	}

	/*
     * 
     */
	public void edgeUp(String sourceName, String destName) {
		Vertex s = vertexMap.get(sourceName);
		if (s == null)
			throw new NoSuchElementException("Source node not found");
		Vertex d = vertexMap.get(destName);
		if (d == null)
			throw new NoSuchElementException("Destination node not found");
		Edge e = s.adj.get(d);
		if (s.adj.get(d) == null)
			throw new NoSuchElementException("Edge does not exist");
		e.down = false;
	}

	/*
     * 
     */
	public void vertexDown(String name) {
		Vertex v = getVertex(name);
		v.down = true;
	}

	/*
     * 
     */
	public void vertexUp(String Name) {
		Vertex v = getVertex(Name);
		v.down = false;
	}

	/**
	 * If vertexName is not present, add it to vertexMap. In either case, return
	 * the Vertex.
	 */
	private Vertex getVertex(String vertexName) {
		Vertex v = vertexMap.get(vertexName);
		if (v == null) {
			v = new Vertex(vertexName);
			vertexMap.put(vertexName, v);
		}
		return v;
	}

	public void deleteGraph() {
		vertexMap.clear();
	}

	/*
	 * Print all nodes and their edges
	 */
	public void printGraph() {
		for (Map.Entry<String, Vertex> entry : vertexMap.entrySet()) {
			System.out.print(entry.getKey());
			Vertex v = entry.getValue();
			if (v.down)
				System.out.print(" " + "DOWN");
			v.printEdges();
		}
	}

	/*
	 * Print path from source to destination
	 */
	public void path(String sourceName, String destName) {
		Vertex s = vertexMap.get(sourceName);
		if (s == null)
			throw new NoSuchElementException("Source node not found");
		Vertex d = vertexMap.get(destName);
		if (d == null)
			throw new NoSuchElementException("Destination node not found");
		Map<Vertex, Vertex> prev = dijkstra(s);
		// calculate the distance and print the path
		float distance = 0;
		Vertex curr = d;
		String path = "";
		do {
			// System.out.print(curr.name+" ");
			path = curr.name + " " + path;
			Vertex prevVertex = prev.get(curr);
			distance = distance + prevVertex.adj.get(curr).weight;
			curr = prev.get(curr);
		} while (curr != s);
		path = s.name + " " + path + distance;
		System.out.println(path);
	}

	/*
	 * Finds shortest path from vertex s to all the nodes
	 */
	public Map<Vertex, Vertex> dijkstra(Vertex s) {
		Map<Vertex, Float> dist = new HashMap<Vertex, Float>();
		Map<Vertex, Vertex> prev = new HashMap<Vertex, Vertex>();
		ArrayList<Vertex> q = new ArrayList<Vertex>();
		for (Map.Entry<String, Vertex> entry : vertexMap.entrySet()) {
			Vertex v = entry.getValue();
			if (!v.down) { // check if vertex is down
				dist.put(v, Float.MAX_VALUE);
				prev.put(v, null);
				q.add(v);
			}

		}
		dist.put(s, (float) 0.0);
		prev.put(s, s);
		Vertex u = s;
		while (!q.isEmpty()) {
			q.remove(u);
			for (Map.Entry<Vertex, Edge> entry : u.adj.entrySet()) {
				Vertex v = entry.getKey();
				Edge e = entry.getValue();
				if (v.down || e.down)  //if edge or adjoint vertex is down move to next entry
					continue;
				Float alt = dist.get(u) + e.weight;
				if (alt < dist.get(v)) {
					dist.put(v, alt);
					prev.put(v, u);
				}
			}
			// update u with vertex in q with min distance
			float min = Float.MAX_VALUE;
			for (Vertex ver : q) {
				float val = dist.get(ver);
				if (min > val) {
					min = val;
					u = ver;
				}
			}

		}
		return prev;

	}

	/*
	 * Print all reachable vertices
	 */
	public void reachable() {

	}
}
