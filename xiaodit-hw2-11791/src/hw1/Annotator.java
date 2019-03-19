package hw1;

public abstract class Annotator {
	String type;
	String regrex;
	public Annotator() {
	}
	
	public Annotator(String type,String regrex) {
		this.type=type;
		this.regrex=regrex;
	}
	// Set methods fot the class
	public abstract void process(AnnotatedString s);
	public abstract void process(AnnotatedString s,int begin,int end);
}
