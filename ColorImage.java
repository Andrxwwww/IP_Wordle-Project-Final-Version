package pack;

/**
 * Represents color images.
 * Image data is represented as a matrix:
 * - the number of lines corresponds to the image height (data.length)
 * - the length of the lines corresponds to the image width (data[*].length)
 * - pixel color is encoded as integers (ARGB)
 */

class ColorImage {

	private int[][] data; // @colorimage


	// Construtors

	ColorImage(int width, int height) {
		data = new int[height][width];
	}

	ColorImage(String file) {
		this.data = ImageUtil.readColorImage(file);
	}

	ColorImage(int[][] data) {
		this.data = data;
	}

	ColorImage(int width, int height, Color color) {
		data = new int[height][width];

		for (int x = 0; x < this.getWidth(); x++) {
			for (int y = 0; y < this.getHeight(); y++) {
				this.setColor(x, y, color);
			}
		}
	}

	// Metods

	int getWidth() {
		return data[0].length;
	}

	int getHeight() {
		return data.length;
	}

	void setColor(int x, int y, Color c) {
		data[y][x] = ImageUtil.encodeRgb(c.getR(), c.getG(), c.getB());
	}

	Color getColor(int x, int y) {
		int[] rgb = ImageUtil.decodeRgb(data[y][x]);
		return new Color(rgb[0], rgb[1], rgb[2]);
	}

	// Text functions

	void drawText(int textX, int textY, String text, int textSize, Color textColor) {
		drawText(textX, textY, text, textSize, textColor, false);
	}


	void drawCenteredText(int textX, int textY, String text, int textSize, Color textColor) {
		drawText(textX, textY, text, textSize, textColor, true);
	}
	
	
	
	

	private void drawText(int textX, int textY, String text, int textSize, Color textColor, boolean isCentered) {
		int r = 255 - textColor.getR();
		int g = 255 - textColor.getG();
		int b = 255 - textColor.getB();

		Color maskColor = new Color(r, g, b);

		int encodedMaskRGB = ImageUtil.encodeRgb(r, g, b);

		int[][] aux = ImageUtil.createColorImageWithText(getWidth(), getHeight(), maskColor, textX, textY, text, textSize, textColor, isCentered);

		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux[i].length; j++) {
				int value = aux[i][j];
				if(value != encodedMaskRGB) {
					data[i][j] = aux[i][j];
				}
			}
		}
	}
// copy a image 
	
	ColorImage copy(){
		ColorImage copy = new ColorImage(getWidth(),getHeight());
		for(int y = 0; y < getHeight(); y++){
			for(int x = 0; x < getWidth(); x++){
				copy.setColor(x,y,getColor(x,y)); 
			}
		
		}
		return copy;
	}

//obtem a imagem com cores inversas

	 void getImageColorsInverted(){
		 for(int y = 0; y < getHeight(); y++){
				for(int x = 0; x < getWidth(); x++){
					Color c = getColor(x,y);
					setColor(x,y,c.doInverse());
			}
		 }
	 }

//obtem a imagem a preto e branco

	 ColorImage getBWimage(){
		 ColorImage BW = new ColorImage(getWidth(),getHeight());
		 for(int y = 0; y < getHeight(); y++){
				for(int x = 0; x < getWidth(); x++){
					Color c = getColor(x,y);
					setColor(x,y,c.getGrayTone());
				}
		 }
		 return BW;
	 }
// Torna uma imagem mais escura ou clara dependendo do valor dado(pos ou neg)
	 void getBrighterOrDarkerImage(int value){
		 for(int y = 0; y < getHeight(); y++){
			for(int x = 0; x < getWidth(); x++){
				Color c = getColor(x,y);
				setColor(x,y,c.adjustBrighter(value));
			}
		 }
	 }
	 
// Transforma uma imagem  
	 void getInvertedImage(){
		 for(int y = 0; y < getHeight(); y++){
				for(int x = 0; x < getWidth()/2; x++){
				Color c = getColor(x,y);
				setColor(x,y,getColor(getWidth()-1-x,y));
				setColor(getWidth()-1-x,y, c);
				}
		 }
	 }

/**
* Is (x, y) a valid pixel position in this image?
 */
	 boolean validPosition(int x, int y) {
		 return 
			x >= 0 && x < getWidth() &&
			y >= 0 && y < getHeight();
		}

/**		 
 * * Validates if the given (x, y) is a valid pixel position,
 * throwing an exception in case if it is not.
*/
	 void validatePosition(int x, int y) {
		if(!validPosition(x, y))
			throw new IllegalArgumentException(
					"invalid point " + x + ", " + y + 
					": matrix dimension is " + getWidth() + " x " + getHeight());
		}

/*Faz um contorno de uma cor à volta de uma imagem */


public void drawOutline (int w, int h, Color cor) {
	int side = Constantes.ICON_SIZE;
	for (int x = w; x < w + side; x++){
		for (int y = h; y < h + side; y++){
			if (x == 0 || x == getWidth()-1 || y == 0 || y == getHeight()-1
			|| x == 1 || x == getWidth()-2 || y == 1 || y == getHeight()-2){
				setColor(x, y, cor);
			}
		}
	}
 }
}














	 
	 