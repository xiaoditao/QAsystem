package hw1;

import java.util.ArrayList;
import java.util.List;


public class AnnotatedString {
	// Set AnnotatedString class
	String text;
	AnnotationIndex index;
	public AnnotatedString (String text) {
		this.text=text;
		this.index=new ListAnnotationIndex();
	}
	public static void main(String[] args) {
		// Process the AnnotatedString to get the sentence, token
		AnnotatedString s = new AnnotatedString("Alan W Black is a Scottish computer scientist, known for his research on speech synthesis. ");
		Annotator a = new RegexAnnotator("edu.cmu.lti.deiis.Sentence" , "([^\\.]+\\.)\\s+" );
		a.process(s);
		Annotator a2 = new RegexAnnotator("edu.cmu.lti.deiis.Token" , "([A-Za-z]+)" );
		Annotator a3 = new NGramAnnotator(2, "edu.cmu.lti.deiis.Token" , "edu.cmu.lti.deiis.TokenBigram" );
		for ( Annotation sentence : s.index.getAnnotations("edu.cmu.lti.deiis.Sentence" )) {
			// Print sentences, tokens
			System.out.println( sentence + " "+s.text.substring(sentence.begin,sentence.end));
			a2.process(s,sentence.begin,sentence.end);
			a3.process(s,sentence.begin,sentence.end);
			for (Annotation token : s.index.getAnnotations("edu.cmu.lti.deiis.Token",sentence.begin,sentence.end)) {
				System.out.println(token+" "+ s.text.substring(token.begin,token.end));
			}
			// Print bigrams
			for(Annotation ngram: s.index.getAnnotations("edu.cmu.lti.deiis.TokenBigram",sentence.begin,sentence.end))
				System.out.println(ngram+" "+s.text.substring(ngram.begin,ngram.end));
			
		}
	}

	public boolean  exactMatch(AnnotatedString target, AnnotatedString test, String type) {
		// Get two lists, judge if the annotations in test list are in the target list
		List<Annotation> targetList = new ArrayList<>(target.index.getAnnotations(type));
		List<Annotation> testList = new ArrayList<>(test.index.getAnnotations(type));
		int count = 0;
		for (Annotation annotation : testList) {
			if(targetList.contains(annotation) && target.text.substring(annotation.begin,annotation.end).equals(test.text.substring(annotation.begin,annotation.end))) {
				count++;
			}
		}
		if (count==testList.size()) {
			return true;
		}
		return false;
	}
	public double softMatch(AnnotatedString target, AnnotatedString test, String type) {
		// Get two lists, find the number of the annotations in test list that vare in the target list
		List<Annotation> targetList = new ArrayList<>(target.index.getAnnotations(type));
		List<Annotation> testList = new ArrayList<>(test.index.getAnnotations(type));
		int count = 0;
		for (Annotation annotation : testList) {
			if(targetList.contains(annotation) && target.text.substring(annotation.begin,annotation.end).equals(test.text.substring(annotation.begin,annotation.end))) {
				count++;
			}
		}
		double res = (double)count/test.index.getAnnotations(type).size();
		return res;
	}
}


