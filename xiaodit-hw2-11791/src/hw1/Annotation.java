package hw1;

public class Annotation {

	String type;
	int begin;
	int end;
	public Annotation(String type, int begin, int end) {
		this.type=type;
		this.begin=begin;
		this.end=end;
	}
	// Modify toString to make the format of print
	public String toString(){
		return "["+Integer.toString(begin)+","+Integer.toString(end)+" "+type+"]";
	}
	// Modify equals to define the equals condition
	@Override
	public boolean equals(Object obj) {
		if (this.type==((Annotation)obj).type && ((Annotation)obj).begin==this.begin&&((Annotation)obj).end==this.end) {
			return true;
		}
		return false;
	}
}
