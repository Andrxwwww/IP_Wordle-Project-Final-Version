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
	this.NumJogadas += 1;
    this.NumVitoriasConsecutivas = 0;
	}
    
ColorImage visualizar(){
	ColorImage Window = new ColorImage(400, 300, Color.GREY);
	return Window;
	}

void visualizarStats(){
	vis.drawCenteredText(200, 30, "Estatísticas",30, Color.WHITE);
	
	createIconStat(vis, 30, 60 ,Color.GREYB,Constantes.ICON_SIZE,30,NumJogadas);
	createIconStat(vis, 30, 110 ,Color.GREYB,Constantes.ICON_SIZE,30,NumVitorias);
	createIconStat(vis, 30, 160 ,Color.GREYB,Constantes.ICON_SIZE,30,NumVitoriasConsecutivas);
	createIconStat(vis, 30, 210 ,Color.GREYB,Constantes.ICON_SIZE,30,MaiorVitoriasConsecutivas);
	
	createIconStat(vis, 290 , 90 ,Color.GREYB,20,20,Histograma[0]);
	createIconStat(vis, 290 , 120 ,Color.GREYB,20,20,Histograma[1]);
	createIconStat(vis, 290 , 150 ,Color.GREYB,20,20,Histograma[2]);
	createIconStat(vis, 290 , 180 ,Color.GREYB,20,20,Histograma[3]);
	createIconStat(vis, 290 , 210 ,Color.GREYB,20,20,Histograma[4]);
	createIconStat(vis, 290 , 240 ,Color.GREYB,20,20,Histograma[5]);

	vis.drawText(250 , 65 , "Winnings in line ",15, Color.WHITE);
	vis.drawText(275 , 92 , "1",15, Color.WHITE);
	vis.drawText(275 , 122 , "2",15, Color.WHITE);
	vis.drawText(275 , 152 , "3",15, Color.WHITE);
	vis.drawText(275 , 182 , "4",15, Color.WHITE);
	vis.drawText(275 , 212 , "5",15, Color.WHITE);
	vis.drawText(275 , 242 , "6",15, Color.WHITE);
	
	vis.drawText(80, 70, "Plays",20, Color.WHITE);
	vis.drawText(80, 120, "Wins",20, Color.WHITE);
	vis.drawText(80, 170, "Streak",20, Color.WHITE);
	vis.drawText(80, 220, "Best Streak",20, Color.WHITE);
	}

// criado para criar o icon com o numero lá dentro

static void createIconStat(ColorImage bg, int w, int h ,Color color,int side, int sizeletter,int c){
	String num = String.valueOf(c);				
	for (int x = w; x < w + side; x++){
		for (int y = h; y < h + side; y++){
				bg.setColor(x,y,color);
		}
	}
	bg.drawCenteredText(w+side/2,h+side/2,num,sizeletter,Color.WHITE);
	}
}

/* ou o utilizador ganha ou perde
 * se ganhar - NumJogadas++ , NumVitorias++ , NumVitoriasConsecutivas++ , histograma[tentativas]++	
 * se perder - Numjogadas++ , vitorias consecutivas = 0 , e histograma[0] = 0 . */