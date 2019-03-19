package hw1;

import java.util.List;

public interface AnnotationIndex {
	// Set methods for the interface
	public void addAnnotation (String type, int begin, int end);
	public List<Annotation> getAnnotations(String type);
	public List<Annotation> getAnnotations(int begin, int end);
	public List<Annotation> getAnnotations();
	public List<Annotation> getAnnotations(String type, int begin, int end);
}
