package engine.map;

public class Block {
	private int line;
	private int column;
	private String element;	
	// Ajouter un attribut pour marquer les zones verrouillées
	private boolean isLocked;

	public Block(int ligne, int colonne, String element) {
		this.line = ligne;
		this.column = colonne;
		this.element = element;
	}

	public Block(int ligne, int colonne) {
		this.line = ligne;
		this.column = colonne;
		this.element = "herbe"; //element par défault 
	}


	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public String getElement() {
		return element;
	}
	public void setElement (String blockType){
		this.element = blockType;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean locked) {
		isLocked = locked;
	}


}
