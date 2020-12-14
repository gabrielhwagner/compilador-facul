public class Token {
		
	TipoToken 	tipo;
	String  	lexema;
	int 	   	linha;
	int 	   	coluna;
	String 	    escopo;

	String		tipoDado;
	String		valor;
	String		endereco;
	String		codigo;
	
	
	public Token(TipoToken tipo, String lexema, int linha, int coluna, String escopo) {
		super();
		this.tipo = tipo;
		this.lexema = lexema;
		this.linha = linha;
		this.coluna = coluna;
		this.escopo = escopo;
		
		//this.tipoDado = tipoDado;
		//this.valor = valor;
		//this.endereco = endereco;
		//this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Token [tipo=" + tipo + ", lexema=" + lexema + ", linha=" + linha + ", coluna=" + coluna + ", escopo="
				+ escopo + ", tipoDado=" + tipoDado + ", valor=" + valor + ", endereco=" + endereco + ", codigo="
				+ codigo + "]";
	}
	
	
}

