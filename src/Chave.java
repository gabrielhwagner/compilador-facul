
public class Chave {
	
	private String escopo;
	private TipoToken tipo;
	private String lexema;
	
	
	public Chave(String escopo, TipoToken tipo, String lexema) {
		super();
		this.escopo = escopo;
		this.tipo = tipo;
		this.lexema = lexema;
	}

	public String getEscopo() {
		return escopo;
	}
	public void setEscopo(String escopo) {
		this.escopo = escopo;
	}
	public TipoToken getTipo() {
		return tipo;
	}
	public void setTipo(TipoToken tipo) {
		this.tipo = tipo;
	}
	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	
}
