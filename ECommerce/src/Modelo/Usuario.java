package Modelo;

public class Usuario {

	private int cod;
	private String nomeUsuario;
	private String loginUsuario;
	private String senhaUsuario;
	private String papelUsuario;
	private boolean logado;
	
	public static String papelAdministrador = "administrador";
	public static String papelCliente = "cliente";
	public boolean isLogado() {
		return logado;
	}
	public void setLogado(boolean logado) {
		this.logado = logado;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getLoginUsuario() {
		return loginUsuario;
	}
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}
	public String getSenhaUsuario() {
		return senhaUsuario;
	}
	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	public String getPapelUsuario() {
		return papelUsuario;
	}
	public void setPapelUsuario(String papelUsuario) {
		this.papelUsuario = papelUsuario;
	}

	
}
