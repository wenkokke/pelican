package semante.checker;

import static lombok.AccessLevel.PRIVATE;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.pipeline.Result;
import semante.pipeline.impl.IResult$Error;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IllegalAnnotationException extends IllegalArgumentException {
	
	// automatically generated serial UID
	private static final long serialVersionUID = -1617060415020690761L;
	
	Object id;
	
	public IllegalAnnotationException(Object id, String s) {
		super(s);
		this.id = id;
	}
	
	@SuppressWarnings("unchecked")
	public <ID> Result<ID> toResult() {
		return new IResult$Error<ID>((ID) id, getMessage());
	}
	
}