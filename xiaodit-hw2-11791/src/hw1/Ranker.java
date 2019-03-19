package hw1;

public class Ranker implements IndependentRanker{
	// Process the AnnotatedString and then use the soft match method
	@Override
	public Double rankAnswer(String question, String answer) {
		AnnotatedString que = new AnnotatedString(question);
		AnnotatedString ans = new AnnotatedString(answer);
		Annotator a = new RegexAnnotator("edu.cmu.lti.deiis.Sentence" , "([^\\.]+\\.)\\s+" );
		a.process(que);
		a.process(ans);
		Annotator a2 = new RegexAnnotator("edu.cmu.lti.deiis.Token" , "([A-Za-z]+)" );
		Annotator a3 = new NGramAnnotator(2, "edu.cmu.lti.deiis.Token" , "edu.cmu.lti.deiis.TokenBigram" );
		a.process(que);
		a.process(ans);
		for ( Annotation sentence : que.index.getAnnotations("edu.cmu.lti.deiis.Sentence" )) {
			// Print sentences, tokens
			a2.process(que,sentence.begin,sentence.end);
			a3.process(que,sentence.begin,sentence.end);
		}
		for ( Annotation sentence : ans.index.getAnnotations("edu.cmu.lti.deiis.Sentence" )) {
			// Print sentences, tokens
			a2.process(ans,sentence.begin,sentence.end);
			a3.process(ans,sentence.begin,sentence.end);
		}
		double r = ans.softMatch(que, ans, "edu.cmu.lti.deiis.TokenBigram");
		Double res = Double.valueOf(r);	
		return res;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
