import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Graph graph = new Graph();
		graph = createGraph(args[0]);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			try {
				String query = br.readLine();
				String query_arr[] = query.split(" ");
				switch (query_arr[0]){
				case "print":
					graph.printGraph();
					break;
				case "path":
					graph.path(query_arr[1],query_arr[2]);
					break;
				case "vertexdown":
					graph.vertexDown(query_arr[1]);
					break;
				case "vertexup":
					graph.vertexUp(query_arr[1]);
					break;
				case "edgeup":
					graph.edgeUp(query_arr[1], query_arr[2]);
					break;
				case "edgedown":
					graph.edgeDown(query_arr[1], query_arr[2]);
					break;
				case "addedge":
					Float weight = Float.parseFloat(query_arr[3]);
					graph.addEdge(query_arr[1], query_arr[2], weight);
					break;
				case "deleteedge":
					graph.deleteEdge(query_arr[1], query_arr[2]);
					break;
				case "reachable":
					
					break;
				default:
					System.out.println("Incorrect Command");
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Graph createGraph(String fileName){
		Graph g = new Graph();
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line;
			while((line = br.readLine())!= null){
				String network[] = line.split(" ");
				Float f = new Float(Float.parseFloat(network[2]));
				
				g.addBiEdge(network[0], network[1], f);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return g;
	}

}
