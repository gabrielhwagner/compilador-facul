
public abstract class Parser {

	TabelaSimbolos ts;
	LexicoDois lexico;
	Token token;

	public Parser() {
		ts = new TabelaSimbolos();
		lexico = new LexicoDois();
	}
	
	public abstract void parse();
	
	public Token buscaToken() {
		token = lexico.buscaToken();
		return token;
	}
	
	public boolean erro(String palavra) {
		System.out.println(palavra + ": linha: " + token.linha + ", coluna :" + token.coluna);
		return false;
	}
	
	
	//Analise Semântica
	public Token buscaDuplicidade() { //vai receber o token, mas como comparar
		token = lexico.buscaToken();
		return token;
	}
}
