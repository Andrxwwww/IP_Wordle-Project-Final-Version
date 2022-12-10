package pack;
/**
 * Represents RGB colors.
 * RGB values are stored in a 3-position array, with values in the interval [0, 255].
 * rgb[0] - Red
 * rgb[1] - Green
 * rgb[2] - Blue
 */
class Color {

	private final int[] rgb; // @color
	
	//primary colors as constants
	
	static final Color RED = new Color(255, 0, 0);
	static final Color GREEN = new Color(0, 255, 0);
	static final Color BLUE = new Color(0, 0, 255);
	static final Color BLACK = new Color(0, 0, 0);
	static final Color GREY = new Color(10, 10, 10);
	static final Color GREYB = new Color(80,80,80);
	static final Color WHITE = new Color(255, 255, 255);
	
	
	
	/**
	 * Creates an RGB color. Provided values have to 
	 * be in the interval [0, 255]
	 */
	Color(int r, int g, int b) {
		if(!valid(r) || !valid(g) || !valid(b))
			throw new IllegalArgumentException("invalid RGB values: " + r + ", " + g + ", " + b);
		
		this.rgb = new int[] {r, g, b};
	
	}

	/**
	 * Red value [0, 255]
	 */
	int getR() {
		return this.rgb[0];
	}

	/**
	 * Green value [0, 255]
	 */
	int getG() {
		return this.rgb[1];
	}

	/**
	 * Blue value [0, 255]
	 */
	int getB() {
		return this.rgb[2];
	}

	/**
	 * Obtains the luminance in the interval [0, 255].
	 */
	int getLuminance() {
		return (int) Math.round(rgb[0]*.21 + rgb[1]*.71 + rgb[2]*.08);
	}

	boolean valid(int value) {
		return value >= 0 && value <= 255;
	}
	
	// verifica o valor de uma cor para nao dar erro 
	
	static int limit(int x) {
		return Math.min(255, Math.max(0, x));
	}
	
	// inverted color
	
	Color doInverse() {
		//if (!valid(255 - this.getR()) || !valid(255 - this.getG()) || !valid(255 - this.getB()))
		//	throw new IllegalArgumentException("invalid RGB values");
		return new Color(255 - this.getR(), 255 - this.getG(), 255 - this.getB());
	}
	// brighter or darker color
	
	Color adjustBrighter(int value) {
		return new Color( limit(getR() + value), limit(getG() + value), limit(getB() + value) );
	}	
	// conversao para uma cor em tom cinzento , a partir de outra cor
	
	Color getGrayTone(){
		return new Color(getLuminance(),getLuminance(),getLuminance());
	}
	// Verificar se 2 cores são iguais (c == rgb)

	boolean isEqualTo(Color c){
		return rgb[0] == c.getR() && rgb[1] == c.getG() && rgb[2] == c.getB();
	}
	// verificar se uma cor está num vetor de cores

	boolean isOnthevec( Color[] v ){
		for(int i = 0; i<v.length ; i++){
			if ( rgb[0] == v[i].getR() && rgb[1] == v[i].getG() && rgb[2] == v[i].getB() ){
				return true;
			}
		}
		return false;
	}

static void teste(){
	Color bacano = new Color(10,67,10);
	bacano.getR();
	bacano.getG();
	bacano.getB();
	bacano.adjustBrighter(7);
	}
}





