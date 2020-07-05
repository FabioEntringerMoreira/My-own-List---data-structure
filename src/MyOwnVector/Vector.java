package MyOwnVector;

import java.util.Arrays;

public class Vector {

	private Object[] elementos;
	int tamanho;

	public Vector(int capacidade) {
		this.setElementos(new Object[capacidade]);
		this.tamanho = 0;
	}

	public void adicionar(Object elemento) throws Exception {
		this.aumentarCapacidade();

		if (this.tamanho < this.getElementos().length) {
			this.getElementos()[this.tamanho] = elemento;
			this.tamanho++;
		} else {
			throw new Exception("Vetor sem espaço");
		}
	}

	public void adicionar(int posicao, Object elemento) {

		this.aumentarCapacidade();
		this.verificarPosicao(posicao);

		for (int i = tamanho - 1; i >= posicao; i--) {
			this.getElementos()[i + 1] = this.getElementos()[i];
		}

		this.getElementos()[posicao] = elemento;
		tamanho++;
	}

	private void verificarPosicao(int posicao) {
		if (!(posicao >= 0 && posicao < tamanho)) {
			throw new IllegalArgumentException("A posição procurada não existe no vetor");
		}
	}

	private void aumentarCapacidade() {
		if (tamanho == getElementos().length) {
			Object[] elementosNovos = new Object[this.getElementos().length * 2];
			for (int i = 0; i < getElementos().length; i++) {
				elementosNovos[i] = getElementos()[i];
			}
			this.setElementos(elementosNovos);
		}
	}

	public Object buscar(int posicao) {
		verificarPosicao(posicao);
		return this.getElementos()[posicao];
	}

	public int buscaComparada(Object elemento) {
		for (int i = 0; i < tamanho; i++) {
			if (getElementos()[i].equals(elemento)) {
				return i;
			}
		}
		return -1;
	}
	
	public void remover(int posicao) {
		this.verificarPosicao(posicao);
		
		for (int i = posicao; i < this.tamanho-1; i++) {
			this.getElementos()[i] = this.getElementos()[i+1];
		}
		this.tamanho--;
	}

	public int getPonteiro() {
		return tamanho;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(getElementos());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (!Arrays.deepEquals(getElementos(), other.getElementos()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  Arrays.toString(getElementos()) + "]";
	}

	public Object[] getElementos() {
		return elementos;
	}

	public void setElementos(Object[] elementos) {
		this.elementos = elementos;
	}


}
