package MyOwnVector;

import java.lang.reflect.Array;

public class Lista<T> {

	// cria o array que servirá de base para a classe
	private T[] elementos; 
	private int tamanho;

	public Lista(int capacidade){
		this.elementos = (T[]) new Object[capacidade]; //solução do livro effective Java
		this.tamanho = 0;
	}
	
	public Lista(){
		this(10);
	}
	
	public Lista(int capacidade, Class<T> tipoClasse){
		this.elementos = (T[]) Array.newInstance(tipoClasse, capacidade);
		this.tamanho = 0;
	}

	//.add (adiciona no fim da lista)
	public boolean adiciona(T elemento) {
		this.aumentaCapacidade();
		if (this.tamanho < this.elementos.length){
			this.elementos[this.tamanho] = elemento;
			this.tamanho++;
			return true;
		} 
		return false;
	}
	
	// 0 1 2 3 4 5 6 = tamanho é 5
	// B C E F G + +
	//.add (adiciona na posição que você quiser (posicao, elemento))
	public boolean adiciona(int posicao, T elemento){
		
		if (!(posicao >= 0 && posicao < tamanho)){
			throw new IllegalArgumentException("Posição inválida");
		}
		
		this.aumentaCapacidade();
		
		//mover todos os elementos
		for (int i=this.tamanho-1; i>=posicao; i--){
			this.elementos[i+1] = this.elementos[i];
		}
		this.elementos[posicao] = elemento;
		this.tamanho++;
		
		return true;
	}
	
	private void aumentaCapacidade(){
		if (this.tamanho == this.elementos.length){
			T[] elementosNovos = (T[]) new Object[this.elementos.length * 2];
			for (int i=0; i<this.elementos.length; i++){
				elementosNovos[i] = this.elementos[i];
			}
			this.elementos = elementosNovos;
		}
	}
	
	// .get
	public T obtem(int posicao){
		return this.busca(posicao);
	}
	
	// .get(passa a posicao e retorna o elemento)
	public T busca(int posicao){
		if (!(posicao >= 0 && posicao < tamanho)){
			throw new IllegalArgumentException("Posição inválida");
		} 
		return this.elementos[posicao];
	}
	
	// .indexOff (passa o elemento e retorna o índice)
	public int busca(T elemento){
		for (int i=0; i<this.tamanho; i++){
			if (this.elementos[i].equals(elemento)){
				return i;
			}
		}
		return -1;
	}
	
	// lastIndexOff (passa o elemento e retorna o último índice que contém esse elemento)
	public int ultimoIndice(T elemento){
		
		for (int i=this.tamanho-1; i>=0; i--){
			if (this.elementos[i].equals(elemento)){
				return i;
			}
		}
		return -1;
	}
	
	// .contains (passa o elemento e retorna um boleano)
	public boolean contem(T elemento){
		
		/*int pos = busca(elemento);
		if (pos > -1){
			return true;
		}
		
		return false;*/
		
		return busca(elemento) > -1; //>=0
	}
	
	// B D E F F -> posição a ser removida é 1 (G)
	// 0 1 2 3 4 -> tamanho é 5
	// vetor[1] = vetor[2]
	// vetor[2] = vetor[3]
	// vetor[3] = vetor[4]
	// .remove (removendo pelo índice)
	public void remove(int posicao){
		if (!(posicao >= 0 && posicao < tamanho)){
			throw new IllegalArgumentException("Posição inválida");
		}
		for (int i=posicao; i<this.tamanho-1; i++){
			this.elementos[i] = this.elementos[i+1];
		}
		this.tamanho--;
	}
	
	// .remove (removendo pela posicao - o primeiro índice encontrado)
	public void remove(T elemento){
		int pos = this.busca(elemento);
		if (pos > -1){
			this.remove(pos);
		}
	}
	
	//.clear (limpa o array)
	public void limpar(){
		//opção 1
		//this.elementos = (T[]) new Object[this.elementos.length];
		
		//opção 2
		//this.tamanho = 0;
		
		//opção 3
		for (int i=0; i<this.tamanho; i++){
			this.elementos[i] = null;
		}
		this.tamanho = 0;
	}
	
	public int tamanho(){
		return this.tamanho;
	}

	@Override
	public String toString() {
		
		StringBuilder s = new StringBuilder();
		s.append("[");
		
		for (int i=0; i<this.tamanho-1; i++){
			s.append(this.elementos[i]);
			s.append(", ");
		}
		
		if (this.tamanho>0){
			s.append(this.elementos[this.tamanho-1]);
		}
		
		s.append("]");
		
		return s.toString();
	}
}