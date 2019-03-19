package hw1;

import java.util.LinkedList;
import java.util.List;

public class ListAnnotationIndex implements AnnotationIndex{
	List<Annotation> list=new LinkedList<>();
	// Create instances and put them into list
	@Override
	public void addAnnotation(String type, int begin, int end) {
		Annotation annotation = new Annotation(type, begin, end);
		list.add(annotation);	
	}
	// Get instances which match the type
	@Override
	public List<Annotation> getAnnotations(String type) {
		List<Annotation> result = new LinkedList<>();
		for (Annotation annotation : list) {
			if (type.equals(annotation.type)) {
				result.add(annotation);
			}
		}
		return result;
	}
	// Get instances which match the begin and end
	@Override
	public List<Annotation> getAnnotations(int begin, int end) {
		List<Annotation> result = new LinkedList<>();
		for (Annotation annotation : list) {
			if (annotation.begin==begin && annotation.end==end) {
				result.add(annotation);
			}
		}
		return result;
	}
	// Get all the instances in the list
	@Override
	public List<Annotation> getAnnotations() {
		List<Annotation> result = new LinkedList<>();
		for (Annotation annotation : list) {
				result.add(annotation);
		}
		return result;
	}
	// Get instances which match the type, begin and end
	@Override
	public List<Annotation> getAnnotations(String type, int begin, int end) {
		List<Annotation> result = new LinkedList<>();
		for (Annotation annotation : list) {
			if (annotation.begin>=begin && annotation.end<=end && annotation.type == type) {
				result.add(annotation);
			}
		}
		return result;
	}

}
