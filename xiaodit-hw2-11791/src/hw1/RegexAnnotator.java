package hw1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
// Constructor 
public class RegexAnnotator extends Annotator{
	public RegexAnnotator(String type,String regrex) {
		super(type, regrex);
	}

	@Override
	public void process(AnnotatedString s) {
		// If the type is sentence, use the regrex to add annotation instance to the list
		if (this.type=="edu.cmu.lti.deiis.Sentence" ) {
			String replace=s.text;
			Pattern p = Pattern.compile(this.regrex);
			int b=0;
			// if it does not match, move on, else, add to list
			for(int e=1;e<replace.length()+1;e++) {
				String tem = replace.substring(b,e);
				Matcher matcher = p.matcher(tem);
				if (!matcher.matches()) {
					continue;
				}
				if(matcher.matches() || e==replace.length()+1){	
					s.index.addAnnotation(this.type, b, e);
					b=e;
					
				}
			}
			
		}
		
	}



	@Override
	public void process(AnnotatedString s, int begin, int end) {
		// If the type is token, use the regrex to add annotation instance to the list
		String replace=s.text.substring(begin,end);
		if (this.type=="edu.cmu.lti.deiis.Token") {
			Pattern p = Pattern.compile(this.regrex);
			int b=0;	
			int e=1;
			// if it matches, move on, else, add to list
			// test if the following is a token, if not, move on
			while(e<replace.length()) {	
				String tem = replace.substring(b,e);
				Matcher matcher = p.matcher(tem);
				if(matcher.matches()) {
					e++;
					continue;
				}
				else{	
					for(int i=e-1;i>=1;i--) {
						String test=replace.substring(i-1,i);
						Matcher matcher2=p.matcher(test);
						if(matcher2.matches()) {
							s.index.addAnnotation(this.type, b, i);
							b=e;
							e++;
							break;
						}
					}
					
					for(int i =b;i<replace.length();i++) {
						String tx=replace.substring(i,i+1);
						Matcher matcher3=p.matcher(tx);
						if (!matcher3.matches()) {
							b++;
						}else {
							e=b+1;
							break;
							
						}
					}							
				}
			}
		}
	}
}
