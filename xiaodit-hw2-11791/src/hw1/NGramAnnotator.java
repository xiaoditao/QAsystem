package hw1;

import java.util.HashMap;
import java.util.Map;


public class NGramAnnotator extends Annotator{
	int num;
	String type2;
	String type;

	public NGramAnnotator(int num, String type2,String type) {
		this.num=num;
		this.type2=type2;
		this.type=type;
	}
	@Override
	public void process(AnnotatedString s) {
	}
	// Get the list of Token, and print them in bigram
	@Override
	public void process(AnnotatedString s, int begin, int end) {
		Map<Integer, Annotation> map = new HashMap<>();
		int startMap=1;	
		if (this.num==2) {
			for(Annotation annotation:((ListAnnotationIndex)s.index).getAnnotations("edu.cmu.lti.deiis.Token")) {
				map.put(startMap,annotation);
				startMap++;
			}
			for(int i=1;i<map.size();i++) {
				s.index.addAnnotation(this.type, map.get(i).begin, map.get(i+1).end);	
			}
		}
	}


}
