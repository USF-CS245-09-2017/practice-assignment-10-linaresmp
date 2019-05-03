import java.util.*;

public class GraphImplementation implements Graph{

	// constructor
 

	private final int vertices;
	private int[][] adjmatrix;

	public GraphImplementation( int v){
		vertices = v;
		adjmatrix = new int[vertices ][vertices ]; 

	}

	// add edges. 
	// v1 is the source, v2 is the target
	public void addEdge(int v1, int v2){
		try{
			adjmatrix[v1][v2] = 1; // 1 means its an edge in the matrix

		} catch(ArrayIndexOutOfBoundsException index){ // catch the exception if the given vertices dont exist 
			System.out.println("Vertices dont exist");
		}
	}

	//returns an array of vertex IDs such that each ID represents a vertex which is the 
	//destination of the edge origination from the argument
	// when there's a 1 inside the adj matrix add column value to new arrayList
	public int[] neighbors(int vertex){
		// make arrayList to hold the neighbor values
		// build an ArrayList that can hold the values of the edges from given vertez
		ArrayList<Integer> col = new ArrayList<Integer>();

		// use co
		int count = 0;
		//adds value of edges by checking if value in adjacency matrix is a 1
		for(int column = 0; column < adjmatrix.length; column++){
			if(adjmatrix[vertex][column] == 1){
				col.add(Integer.valueOf(column));
				count ++;
			}
			
		}

		// changes arrayList that holds IDs to an array
		int[] col2 = new int[col.size()];
		for(int a = 0; a < col.size(); a++){
			col2[a] = col.get(a);

		}
	

			return col2; // return the array
		}


	//sorts the adj matrix
	//returns the nodes in topological order
	public List<Integer> topologicalSort(){
		// set values we will use throughout the sort
		int length = adjmatrix.length;
		boolean[] visited = new boolean[length];
		int[] order = new int[length];
		int index = length - 1; 

		//visit each node
		for(int u = 0; u < length; u++){
			if(!visited[u]){
				//calls visit method 
				index = visit(adjmatrix, visited, order, index, u);
			}
		}


		// make this array into arrayList
		ArrayList<Integer> list = new ArrayList<Integer>();

		// add everything from the array into the arraylist we need to return
		for(int g = 0; g < order.length; g++){
			list.add(order[g]);
		
		}

		// returns an arrayList 
		return list; 

	}
	
	//returns the index of the node that is next in the order
	private static int visit(int[][] adjmatrix, boolean[] visited, int[] order, int index, int u){
		//if it has already been visited return
		if(visited[u]){
			return index;
		}
		//visited becomes true to show that this index has been visited
		visited[u] = true;

		// visit all the neighbors
		for(int v = 0; v < adjmatrix.length; v++){
			if(adjmatrix[u][v] != 0 && !visited[v]){
				index = visit(adjmatrix, visited, order, index, v);
			}
		}
		//places node at head of list to follow in topological order
		order[index--] = u;

		return index; // returns the index of the node 
	}



	
}