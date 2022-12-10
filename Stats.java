package pack;

	public class Stats {
	
    int[] Histograma;
    int NumJogadas;
    int NumVitorias;
    int NumVitoriasConsecutivas;
    int MaiorVitoriasConsecutivas;
    
    ColorImage vis = new ColorImage(400, 300, Color.GREY);
    
Stats(int MaxTentativas){
	Histograma = new int[MaxTentativas+1];
	for(int i = 0 ; i < MaxTentativas+1; i++){
		Histograma[i] = 0;
	}
	 NumJogadas= 0;
     NumVitorias = 0;
     NumVitoriasConsecutivas = 0;
     MaiorVitoriasConsecutivas = 0;
}
    
void RegistaVitoria(int NumTentativas){
	this.Histograma[NumTentativas] += 1;
	this.NumJogadas += 1;
    this.NumVitorias += 1;
    this.NumVitoriasConsecutivas += 1;
    if( this.NumVitoriasConsecutivas>this.MaiorVitoriasConsecutivas){
    	this.MaiorVitoriasConsecutivas = this.NumVitoriasConsecutivas;
    }
}	
    
    
void RegistaDerrota(){
	this.Histograma[0] += 1;
	this.NumJogadas += 1;
    this.NumVitoriasConsecutivas = 0;
	}
    
ColorImage visualizar(){
	ColorImage Window = new ColorImage(400, 300, Color.GREY);
	return Window;
	}

void visualizarStats(){
	vis.drawCenteredText(200, 30, "Estatísticas",30, Color.WHITE);
	createIconStat(vis, 50, 60 ,Color.GREYB,  NumJogadas);
	createIconStat(vis, 50, 110 ,Color.GREYB,  NumVitorias);
	createIconStat(vis, 50, 160 ,Color.GREYB,  NumVitoriasConsecutivas);
	createIconStat(vis, 50, 210 ,Color.GREYB, MaiorVitoriasConsecutivas);
	
	vis.drawText(100, 70, "Plays",20, Color.WHITE);
	vis.drawText(100, 120, "Wins",20, Color.WHITE);
	vis.drawText(100, 170, "Streak",20, Color.WHITE);
	vis.drawText(100, 220, "Best Streak",20, Color.WHITE);
	}

// criado para criar o icon com o numero lá dentro

static void createIconStat(ColorImage bg, int w, int h ,Color color,int c){
	int side = Constantes.ICON_SIZE ;
	String num = String.valueOf(c);				
	for (int x = w; x < w + side; x++){
		for (int y = h; y < h + side; y++){
				bg.setColor(x,y,color);
		}
	}
	bg.drawCenteredText(w+side/2,h+side/2,num,30,Color.WHITE);
	}
}

/* ou o utilizador ganha ou perde
 * se ganhar - NumJogadas++ , NumVitorias++ , NumVitoriasConsecutivas++ , histograma[tentativas]++	
 * se perder - Numjogadas++ , vitorias consecutivas = 0 , e histograma[0] = 0 . */
