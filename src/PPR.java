
public class PPR extends Parser{

	public PPR() {
		super();
	}
	
	@Override
	public void parse() {
		// TODO Auto-generated method stub
		analisaPrograma();
		
	}
	
	public boolean analisaPrograma() {
		buscaToken();
		if(token.tipo == TipoToken.SPROGRAMA) {
			System.out.println(token.tipo + " ");
			buscaToken();
			
			if(token.tipo == TipoToken.SIDENTIFICADOR) {
				System.out.println(token.tipo + ": " + token.lexema + " ");
				ts.addToken(token);
				buscaToken();
				
				if(token.tipo == TipoToken.SPONTO_E_VIRGULA) {
					System.out.println(token.tipo + " ");
					if(analisaBloco()) {
						buscaToken();
						if(token.tipo == TipoToken.SPONTO) {
							return true;
						}
					} else {
						erro("Bloco principal nao encontrado");
					}
				} else {
					erro("Ponto e virgula esperado");
				}
			} else {
				erro("Identificador esperado");
			}
		}
		erro("Programa não declarado");
	}

	private boolean analisaBloco() {
		buscaToken();
		//analisaEtVariaveis();
		//analisaSubRotinas();
		analisaComandos();
		return false;
	}
	
	private void analisaComandos() {
		
		
	}

	private void analisaSubRotinas() {
		// TODO Auto-generated method stub
		
	}

	private void analisaEtVariaveis() {
		if(token.tipo == TipoToken.SVAR) {
			buscaToken();
			if(token.tipo == TipoToken.SIDENTIFICADOR) {
				while(token.tipo == TipoToken.SIDENTIFICADOR) {
					analisaVariaveis();
					if(token.tipo == TipoToken.SPONTO_E_VIRGULA) {
						buscaToken();
					} else {
						erro("Ponto e virgula esperado");
					}
				}
			} else {
				erro("Identificador esperado");
			}
			
		}
	}

	private void analisaVariaveis() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
