import java.util.HashMap;

public class TabelaSimbolos {

	HashMap<Chave, Token> lt;
	
	public TabelaSimbolos() {
		lt = new HashMap<>();
	}
	
	public void addToken(Token t) {
		Chave chave = new Chave(t.escopo, t.tipo, t.lexema);
		lt.put(chave, t);
		System.out.println("Add " + lt.containsKey(chave));
	}
	
	public Token getToken(Token t) {
		Chave chave = new Chave(t.escopo, t.tipo, t.lexema);
		
		System.out.println("Get " + lt.containsKey(chave));
		return lt.get(chave);
	}
}
