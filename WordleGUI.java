package pack;
/*Classe para a manipulação de cores/imagem 
 Made by André Soares */
public class WordleGUI {

// Cria o nome do Jogo no bg (extra)
	 
	 static void createName(ColorImage bg){
		 bg.drawCenteredText(bg.getWidth()/2, 40 ,"Wordle",40, Color.WHITE);
	 }
	 
// Cria o icon com a letra lá dentro 

	static void createIcon(ColorImage bg, int w, int h ,Color color,char c){ // bg - background
		// Color color = Constantes.EMPTY_BG;
		int side = Constantes.ICON_SIZE ; // side  = 40 
		String letter = String.valueOf(c); // mete o  char a string
		for (int x = w; x < w + side; x++){
			for (int y = h; y < h + side; y++){
					bg.setColor(x,y,color);
			}
		}
		bg.drawCenteredText(w+side/2,h+side/2,letter,30,Color.WHITE);
	}
	
// Criar a grelha do jogo a partir da matriz e da palavra a advinhar "string"
	
	public static void createGrid(ColorImage bg, String puzzle  , char[][] grid ){ 
		 char[] word = convertString(puzzle).toCharArray();
		 int x= bg.getWidth()/2 - ( (Constantes.MAX_CHARS) * Constantes.ICON_SIZE + (Constantes.MAX_CHARS-1) * Constantes.ICON_SPACING)/2; 
		 int y = 100; 
		 for (int i = 0; i < Constantes.MAX_LINES ;i++){
			for(int j = 0 ; j < Constantes.MAX_CHARS ; j++){
				char letra = grid[i][j];
				Color background = Constantes.EMPTY_BG;
				if(letra == ' ') {
					background = Constantes.EMPTY_BG;
				} else {
					if(letra == word[j]) {
						background = Constantes.CORRECT_BG;
					} else {
						// verificar se a letra existe na palavra_tentativa
						boolean encontrou = false;
						for(int k = 0; k < word.length; k++) {
							if(letra == word[k]) {
								background = Constantes.EXISTS_BG;
								encontrou = true;
								break;
							}
							if(!encontrou) {
								background = Constantes.NOT_IN_WORD_BG;
							}
						}
					}
				}
				
				createIcon(bg,x+44*j,y+44*i,background, letra);
			}
		}
	}	
	
// Criar o teclado do jogo 
	 static void createKb(ColorImage bg, char[][] matrix ,  int[] v)
	 	{
		 char[][] kb = Constantes.QWERTY;
		 int y = 450;
		 for (int i = 0; i < kb.length ;i++){
				for(int j= 0; j < kb[i].length;j++){
					char letra = kb[i][j];
					
					int idx = letra - 'A';
					Color background = Constantes.EMPTY_BG;
					if(v[idx] == Constantes.NOT_EXISTS) {
						background = Constantes.NOT_IN_WORD_BG;
					} else if (v[idx] == Constantes.EXISTS) {
						background = Constantes.EXISTS_BG;
					} else if (v[idx] == Constantes.CORRECT_POSITION) {
						background = Constantes.CORRECT_BG;
					} else {
						background = Constantes.EMPTY_BG;
					}
					
					int x = bg.getWidth()/2 - kb[i].length/2*44;
					if(i >= 1){
						createIcon(bg,x+44*j-20,y+44*i,background,letra);
					}else{
						createIcon(bg,x+44*j,y+44*i,background, letra);
				}
			}
		 }
	 }
	 
//converte uma string com acentos para uma string sem*/

	static String convertString( String word ){
		char [] w = word.toLowerCase().toCharArray();
		for(int i = 0; i < w.length; i++){
			if( w[i] == 'à' || w[i] == 'á'|| w[i] =='â'|| w[i] =='ã')
				w[i] = 'a';
			if ( w[i] == 'é' || w[i] =='è' || w[i] =='ê')
				w[i] = 'e';
			if(w[i] == 'û' || w[i] == 'ù' || w[i] == 'ú')
				w[i] = 'u';
			if(w[i] == 'ó'||w[i] == 'ò'||w[i] == 'õ'||w[i] == 'ô')
				w[i] = 'o';
			if ( w[i] == 'í'|| w[i] =='ì'|| w[i] =='î')
				w[i] ='i';
			if(w[i] == 'ç')
				w[i] = 'c';
		}
		return new String(w).toUpperCase();
	}
}