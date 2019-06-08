
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Grafo {
    public static final boolean ORIENTADO = true;
    private int n; //total de nós
    private boolean ehOrientado;
    private double[][] matriz; //matriz de adjacencia que pode vir a ter pesos
    
    //construtor
    public Grafo(int _n, boolean _ehOrientado) {
        n = _n;
        ehOrientado = _ehOrientado;
    	inicializando();
    }
    
    //construtor (lendo de arquivo)
    public Grafo (String _arquivo) throws FileNotFoundException {
    	Scanner scanner = new Scanner(new FileReader(_arquivo));
    	
		n = scanner.nextInt();
		System.out.println("Tem " + n + " nós");
		
		ehOrientado = scanner.nextBoolean();
		if(ehOrientado)
			System.out.println("orientado");
		else
			System.out.println("não orientado");
		
		boolean temPeso = scanner.nextBoolean();
		if(temPeso)
			System.out.println("com peso");
		else
			System.out.println("sem peso");
		
    	inicializando();
		
		while(scanner.hasNext()) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			if(temPeso)
				inserirAresta(u, v, scanner.nextDouble());
			else
				inserirAresta(u, v, 1);
		}
		
		scanner.close();
    }

    private void inicializando() {
        matriz = new double[n][n];

        //inicializando matriz de adjacencia com zero
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	matriz[i][j] = 0;
            }
        }
    }
    
    //inserindo aresta entre o vertice i e j
    //a matriz começa do zero, porém o usuário começa do nó 1
    public boolean inserirAresta(int _i, int _j, double _peso) {    	
    	if(_i == _j){
        	System.out.println("[inserirAresta] Não pode haver laço.");
    		return false;
    	}
    	
    	if((_i < n) && (_j < n)){
    		matriz[_i][_j] = _peso; 
    		
    		if(!ehOrientado){
    			matriz[_j][_i] = _peso;
    		}
    		return true;
    	}

    	System.out.println("[inserirAresta] Esse vértice não existe! (" + _i + " ou " + _j + ")");
    	return false;
    }
    
    //mostrando a matriz de adjacência
    public String toString() {
        String r = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	r += matriz[i][j] + "\t";
            }
            r += "\n";
        }
        return r;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
    	//criando grafo hardcode
        //Grafo g = new Grafo(5, !ORIENTADO);
        //g.inserirAresta(2, 2, 1);
        //g.inserirAresta(1, 2, 1);
    	
    	//criando a partir de um arquivo  
    	Grafo g = new Grafo("matrizDijkstra.txt");
        System.out.println(g);
    }
    
}
