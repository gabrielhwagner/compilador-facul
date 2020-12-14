import java.util.HashMap;

public class TabelaSimbolos {

	HashMap<Chave, Token> lt = new HashMap<>();
	
	public void addToken(Token t) {
		Chave chave = new Chave(t.escopo, t.tipo, t.lexema);
		lt.put(chave, t);
	}
	
	public Token getToken(Chave ch) {
		return lt.get(ch);
	}
}
