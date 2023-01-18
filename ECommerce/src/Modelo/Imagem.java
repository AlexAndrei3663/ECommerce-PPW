package Modelo;

import java.io.InputStream;

public class Imagem {
	private int tamanhoImagem;
	public int getTamanhoImagem() {
		return tamanhoImagem;
	}
	public void setTamanhoImagem(int tamanhoImagem) {
		this.tamanhoImagem = tamanhoImagem;
	}
	public InputStream getImagem() {
		return imagem;
	}
	public void setImagem(InputStream imagem) {
		this.imagem = imagem;
	}
	private InputStream imagem;
}
