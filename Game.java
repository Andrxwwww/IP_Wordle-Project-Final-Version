package pack;
import java.util.Scanner;

/*Classe para a criação do jogo
Made by Andre Soares */
//funcao teste no final

public class Game {
	
		final int NUM_LETRAS = 26; 	// Numero de letras do teclado
		final String DICIONARIO_DEFAULT = "pt_br.txt"; // só uma constante com o nome do ficheiro do dicionario
		
		//ColorImage bg; // fornece uma CI  
		Dictionary dicionario;// fornece um dicionario
		String PalavraPuzzle;// fornece string puzzle
		
		ColorImage bg;
		public Stats stats = new Stats(5); // cria um objeto da classe Stats 
		final char[][] teclado = Constantes.QWERTY; // teclado
		char[][] tentativas; //  grelha onde vou meter as letras 
		int LinhaAtual = 0; // indice de linhas da array char
		int[] EstadoDasTeclas; // vai ser onde vou guardar os estados , é o suposto int[] v 
		public boolean gameActive = true; // quando gameActive = true , o jogo esta a decorrer
		
		Game(ColorImage bg) // para começar o Game 
		{
			//ColorImage bg = new ColorImage(Constantes.DEFAULT_HEIGHT,Constantes.DEFAULT_WIDTH,Constantes.DEFAULT_BG);
			Dictionary dicionario = new Dictionary(DICIONARIO_DEFAULT);
			String PalavraPuzzle = dicionario.generateSecretWord(Constantes.MAX_CHARS);
			
			InicializaJogo(bg, dicionario, PalavraPuzzle);
		}
//Estado inicial do Game
		void InicializaJogo(ColorImage bg, Dictionary d, String PalavraPuzzle) {
			this.bg = bg;
			this.dicionario = d;
			this.PalavraPuzzle = PalavraPuzzle;
			
// Inicializar a grelha / tentativas
			this.tentativas = new char[Constantes.MAX_LINES][Constantes.MAX_CHARS];
			for(int i = 0; i < Constantes.MAX_LINES; i++) {
				for(int j = 0; j < Constantes.MAX_CHARS; j++) {
					this.tentativas[i][j] = ' ';
				}
			}
			this.LinhaAtual = 0;
			
// Inicializar estado teclas
			this.EstadoDasTeclas = new int[NUM_LETRAS];
			for(int i = 0; i < NUM_LETRAS; i++) {
				this.EstadoDasTeclas[i] = Constantes.UNTESTED;
			}
		}
// procedimento que reinicia o jogo após gastar todas as tentativas / acertar na palavra
		
		void ReiniciaJogo() {
			String PalavraPuzzle = dicionario.generateSecretWord(Constantes.MAX_CHARS);
			this.InicializaJogo(this.bg, this.dicionario, PalavraPuzzle);
		}
		
// quando o utilizador insere a palavra passa pelos ifs(verificações) e insere na matriz de char[][]
		void novaTentativa(String tentativa) {
			
			if(this.gameActive == false){
				return;
			}	

			if(tentativa.length() != Constantes.MAX_CHARS) {
				throw new IllegalArgumentException("A tentativa tem um numero invalido de caracteres");
			}	
			
			tentativa = WordleGUI.convertString(tentativa);
			if(!this.dicionario.exists(tentativa)) {
				System.out.println("a palavra nao existe :(");
				return;
			}
			
			for(int i = 0; i < tentativa.length(); i++){
				this.tentativas[LinhaAtual][i] = tentativa.charAt(i);
			}
			atualizaEstadoDoTeclado(tentativa);
			this.LinhaAtual += 1;
			
			if(tentativa.equals(PalavraPuzzle)) {
				System.out.println("Acertastes mesmo em cheio :)");
					render();
						stats.RegistaVitoria(4);
						stats.visualizarStats();
							ReiniciaJogo();
			}else if(this.LinhaAtual == Constantes.MAX_LINES) {
				System.out.println("Gastastes todas as tentativas :(");
					render();
						stats.RegistaDerrota();
						stats.visualizarStats();
							ReiniciaJogo();
				}
			}
//atualiza o estado de cada tecla associada à string inserida 
		void atualizaEstadoDoTeclado(String tentativa) {
			for(int i = 0; i < tentativa.length(); i++) {
				if(tentativa.charAt(i) == this.PalavraPuzzle.charAt(i)) {
					this.atualizaCorDaLetra(tentativa.charAt(i), Constantes.CORRECT_POSITION);
				} else {
					for(int j = 0; j < this.PalavraPuzzle.length(); j++) {
						if(tentativa.charAt(i) == this.PalavraPuzzle.charAt(j)) {
							this.atualizaCorDaLetra(tentativa.charAt(i), Constantes.EXISTS);
						}else{
							this.atualizaCorDaLetra(tentativa.charAt(i), Constantes.NOT_EXISTS);
							}
						}
					}	
				}
			}
//procedimento para verificar o estado usando um index (associado no CreateKB no WordleGUI)
		void atualizaCorDaLetra(char letra, int estado) {
			int idx = letra - 'A';
			if(this.EstadoDasTeclas[idx] < estado) {
				this.EstadoDasTeclas[idx] = estado;
			}
		}
// função criada para escrever a string na consola
// cheguei a criar desta forma porque dava-me um erro "OUT OF MEMORY" quando fazia o que a função na função para "Start"
		static String mete_letra(){
			Scanner input = new Scanner(System.in);
			System.out.print("Insira a palavra:");
			String palavra = input.nextLine();
			if(palavra.isEmpty()) {
				throw new NullPointerException("A tentativa não tem nada :|");
			}
			return palavra;
		}
		
// criado para dar sempre update no jogo no PandionJ
		void render() {
			WordleGUI.createName(this.bg);
			WordleGUI.createGrid(this.bg, this.PalavraPuzzle, this.tentativas);
			WordleGUI.createKb(this.bg, this.teclado, this.EstadoDasTeclas);
			stats.visualizarStats();
			return;
		}
// Para começar o Jogo 
		public static void Start(){
			Scanner input = new Scanner(System.in);
			ColorImage bg = new ColorImage(Constantes.DEFAULT_HEIGHT,Constantes.DEFAULT_WIDTH,Constantes.DEFAULT_BG);
			Game game = new Game(bg);
			game.render();
			while(game.gameActive == true ) {
				 String tenta = mete_letra();
				 game.novaTentativa(tenta);
				 game.render();
			}
			input.close();
		}
}

/* BUGS
 * - Como introduzir várias vezes uma string (so consegui criar uma array com strings e introduzir 1 a 1 na grelha) { pode ser com input afinal :] }- FIXED !!! 
 * - Como dar reset à grelha quando tentativas == 6 ou quando acerto na palavra [de forma a não acabar o programa] - FIXED
 * - palavra a advinhar "COMER" / palavra introduzida "BEBER" - convem so aparecer o 2ºe a verde nao a amarelo/verde
 * - meto 7 palavras mas a 7 palavra nao vai para a matriz -FIXED
 * - os numeros das estaticas estao por cima de tudo -FIXED 
 */	
