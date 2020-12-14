import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class LexicoDois {
	
	/*
	 * Stream de leitura do arquivo fonte
	 */
	PushbackReader r;
	
	HashMap<String, Token> lt;
	
	/*
	 * Codigo do caracter sendo analisado
	 */
	int intch;
	
	/*
	 * Caracter sendo analisado
	 */
	char ch;
	
	/*
	 * Linha sendo analisada
	 */
	int linha = 1;
	
	/*
	 * Coluna sendo analisada
	 */
	int coluna;
	
	/*
	 * Le um caracter do Stream
	 */
	private char leCh() {
		try {
			intch = r.read();
			
			if(intch != -1) {				
				ch = (char) intch;
			} else {				
				ch = '@';
			}
			
			// Verifica se o caracter é quebra de linha
			if (ch == '\n') {
				linha++;
				coluna = 0;
			} else {
				coluna++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ch;
	}
	
	/*
	 * Devolve o ultimo caracter lido
	 */
	public void devolve() {
		try {
			r.unread(ch);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Verifica se o caracter é quebra de linha
		if(ch == '\n') {
			linha--;
		} else {
			coluna--;
		}	
	}
	
	/*
	 * Identifica o caracter atual e determina seu tipo e informações
	 */
	public Token buscaToken() {
		int estado = 0;
		
		int col = 0;
		
		// Percorre até o caracter ser invalido
		while(ch != '@') {
			switch(estado) {
				// Estado inicial de um novo token 
				case 0:
					// Guarda a coluna inicial do token
					col = coluna;
					if 		(ch == '{') estado = 16;
					else if (ch == ':') estado = 1;
					
					// Operações de calculo
					else if (ch == '+') return new Token(TipoToken.SMAIS, "+", linha, col, "programa");
					else if (ch == '-') return new Token(TipoToken.SMENOS, "-", linha, col, "programa");
					else if (ch == '*') return new Token(TipoToken.SMULTIPLICACAO, "*", linha, col, "programa");
					else if (ch == '/') return new Token(TipoToken.SDIVISAO, "/", linha, col, "programa");

					// Simbolos
					else if (ch == ';') return new Token(TipoToken.SPONTO_E_VIRGULA, ";", linha, col, "programa");
					else if (ch == '.') return new Token(TipoToken.SPONTO, ".", linha, col, "programa");
					else if (ch == '(') return new Token(TipoToken.SABRE_PARENTESIS, "(", linha, col, "programa");
					else if (ch == ')') return new Token(TipoToken.SFECHA_PARENTESIS, ")", linha, col, "programa");
					
					// Verifica se o caracter é um numero
					else if (Character.isDigit(ch)) {
						// Retorna para o caracter anterior
						devolve();
						estado = 12;
					}
					
					// Verifica se o caracter é letra
					else if (Character.isLetter(ch)) {
						// Retorna para o caracter anterior
						devolve();
						estado = 14;
					}
					break;
				/*
				 * Estado de analise para o caracter dois pontos,
				 * determina se é uma operacao de atribuição ou tipo 
				 */
				case 1:
					// Caso o proximo caracter for um igual
					if(ch == '=') {		
						// Volta para o estado inicial
						estado = 0;
						return new Token(TipoToken.SATRIBUICAO, ":=", linha, col, "programa");
					} else {
						// Retorna para o caracter anterior
						devolve();
						return new Token(TipoToken.STIPO, ":", linha, col, "programa");
					}
				/*
				 * Estado de analise para quando o caracter for um numero,
				 * percorre até os caracteres nao serem um numero
				 */
				case 12:
					String numero = "";
					// Percorre os caracteres até que não seja um numero
					while(Character.isDigit(ch)) {
						// Concatena o numero
						numero = numero + ch;
						
						// Le proximo caracter
						leCh();	
					}
					// Volta para o estado inicial
					estado = 0;
					
					// Retorna para o caracter anterior
					devolve();
					return new Token(TipoToken.SNUMERO, numero, linha, col, "programa");
				/*
				 * Estado de analise para quando o caracter for uma letra,
				 * percorre até os caracteres nao serem uma letra, numero ou underline
				 */
				case 14:
					String palavra = "";
					// Percorre os caracteres até nao ser uma letra, numero ou underline
					while(Character.isLetter(ch) || ch == '_' || Character.isDigit(ch)) {
						// Concatena a palavra
						palavra = palavra + ch;
						
						// Le proximo caracter
						leCh();	
					}
					// Verifica se a palavra é reservada ou é um identificador
					Token token = this.identificaPalavra(palavra, col);
					
					// Retorna para o caracter anterior
					devolve();
					
					// Volta para o estado inicial
					estado = 0;
					return token;
				/*
				 * Estado de analise para comentario
				 */
				case 16:
					// Percorre até o comentario ser fechado
					while(ch != '}') leCh();
					
					// Volta para estado inicial
					estado = 0;
					break;
			}
			leCh();
		}
		
		// Retorna quando o token é desconhecido
		return new Token(TipoToken.SERRO, "", linha, col, "programa");
	}
	
	/*
	 * Define se a palavra recebida é revervada ou um identificador
	 */
	private Token identificaPalavra(String palavra, int coluna) {
		switch(palavra) {
			case "programa":
				return new Token(TipoToken.SPROGRAMA, palavra, linha, coluna, "programa");
			case "var":
				return new Token(TipoToken.SVAR, palavra, linha, coluna, "programa");
			case "inteiro":
				return new Token(TipoToken.SINTEIRO, palavra, linha, coluna, "programa");
			case "booleano":
				return new Token(TipoToken.SBOOLEANO, palavra, linha, coluna, "programa");
			case "inicio":
				return new Token(TipoToken.SINICIO, palavra, linha, coluna, "programa");
			case "escreva":
				return new Token(TipoToken.SESCREVA, palavra, linha, coluna, "programa");
			case "fim":
				return new Token(TipoToken.SFIM, palavra, linha, coluna, "programa");
			default:
				return new Token(TipoToken.SIDENTIFICADOR, palavra, linha, coluna, "programa");

		}
	}
	
	/*
	 * Abre o arquivo e percorre cada caracter
	 */
	public TabelaSimbolos analisa (String arquivo) {
		
		// Cria um hashMap para salvar cada token
		TabelaSimbolos lt = new TabelaSimbolos();
		
		try {
			// Abre o arquivo
			r = new PushbackReader (
					new BufferedReader(
						new InputStreamReader(
							new FileInputStream(arquivo), "US-ASCII")));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// ler o stream
		while((ch = leCh()) != '@') {
			// Pula comentarios
			if(ch == '{') {
				while(ch != '}') {
					leCh();
				}
			}
			
			// Pula espaçoes em branco, tabs e novas linhas
			while(ch == ' ' || ch ==  '\n' || ch == '\t') {
				leCh();
			}
			
			// Funcao que analisa cada caracter
			Token t = buscaToken();
			
			// Seta no hashMap o token retornado
			lt.addToken(t);
		}
		
		return lt;
	}
}
