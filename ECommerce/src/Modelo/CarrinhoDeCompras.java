package Modelo;

public class CarrinhoDeCompras {

		private int cod;
		private Usuario usuario;
		private Produto produto;
		private int quantidade;
		private double valor;
		public double getValor() {
			return valor;
		}


		public void setValor(double valor) {
			this.valor = valor;
		}


		public int getCod() {
			return cod;
		}
		
		
		public Usuario getUsuario() {
			return usuario;
		}


		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}


		public Produto getProduto() {
			return produto;
		}


		public void setProduto(Produto produto) {
			this.produto = produto;
		}


		public void setCod(int cod) {
			this.cod = cod;
		}


		public int getQuantidade() {
			return quantidade;
		}
		public void setQuantidade(int quantidade) {
			this.quantidade = quantidade;
		}
		
}
