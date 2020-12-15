
public class PPR extends Parser{

	public PPR(String arquivo) {
		super();
		lexico.analisa(arquivo);
	}
	
	@Override
	public void parse() {
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
						return erro("Bloco principal nao encontrado");
					}
				} else {
					return erro("Ponto e virgula esperado");
				}
			} else {
				return erro("Identificador esperado");
			}
		}
		return erro("Programa não declarado");
	}

	private boolean analisaBloco() {
		buscaToken();
		analisaEtVariaveis();
		analisaComandos();
		return true;
	}
	
	private boolean analisaComandos() {
		if(token.tipo == TipoToken.SINICIO) {
			buscaToken();
			analisaComandoSimples();
			while(token.tipo != TipoToken.SFIM) {
				if(token.tipo == TipoToken.SPONTO_E_VIRGULA) {
					buscaToken();
					if(token.tipo != TipoToken.SFIM) {
						analisaComandoSimples();
					} else {						
						return true;
					}
				} else {
					return erro("Ponto e virgula esperado");
				}
			}
			buscaToken();
			return true;
		} else {
			return erro("Programa não iniciado");
		}
	}
	
	private boolean analisaComandoSimples() {
		if(token.tipo == TipoToken.SIDENTIFICADOR) {
			return analisaAtribuicao();
		} else if(token.tipo == TipoToken.SESCREVA) {
			return analisaEscreva();
		} else {
			return analisaComandos();
		}
	}
	
	private boolean analisaAtribuicao() {
		buscaToken();
		if(token.tipo == TipoToken.SATRIBUICAO) {
			// ANALISAR ATRIBUIÇÃO ATÉ O FIM
			while(token.tipo != TipoToken.SPONTO_E_VIRGULA) {
				buscaToken();
			}
			return true;
			//return analisaAtribuicao();
		} else {
			return erro("Atribuição esperada");
		}
	}
	
	private boolean analisaEscreva() {
		buscaToken();
		if(token.tipo == TipoToken.SABRE_PARENTESIS) {
			buscaToken();
			if(token.tipo == TipoToken.SIDENTIFICADOR) {
				Chave chave = new Chave(token.escopo, token.tipo, token.lexema);
				// desfazer deppois
				// ts.getToken(chave) != null
				if(true) {
					buscaToken();
					if(token.tipo == TipoToken.SFECHA_PARENTESIS) {
						buscaToken();
						return true;
					} else {
						return erro("Fecha parentesis esperado");
					}
				} else {
					return erro("Identificador não encontrado");
				}
			} else {
				return erro("Identificador esperado");
			}
		} else {
			return erro("Abre parentesis esperado");
		}
		//analisaComandos();
		return false;
	}

	private void analisaSubRotinas() {
		// TODO Auto-generated method stub
	}


	// ANALISAR
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
        while(token.tipo == TipoToken.SDOISPONTOS) {
            if(token.tipo == TipoToken.SIDENTIFICADOR) {
                boolean duplicado = !buscaDuplicidade(token.lexema); //Parte Semântica - todd
                if(!duplicado) { 
                    ts.addToken(token);
                    buscaToken();
                    if((token.tipo == TipoToken.SVIRGULA) || (token.tipo == TipoToken.SDOISPONTOS)){
                        if(token.tipo == TipoToken.SVIRGULA) {
                            buscaToken();
                            if(token.tipo == TipoToken.SDOISPONTOS) {
                                erro("Não se usa dois pontos depois de uma vírgula.");
                            }
                        } else {
                            erro("O símbolo não é uma vírgula.");
                        }
                    }
                } else {
                    erro("Existe duplicidade de símbolos.");
                }
            } else {
                erro("Não é um identificador.");
            }
            buscaToken();
            analisaTipo();
        }
    }

	
	private void analisaTipo() {
		
	}
	
	

}
