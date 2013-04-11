package semante.pipeline;

public interface Result<ID> {
	
	public interface Visitor<ID,X> {
		X proof();
		X unknown();
		X counterExample();
		X exception(ID id);
		X exception(ID id, String msg);
	}
	
	<X> X accept(Visitor<ID,X> v);
	
}
