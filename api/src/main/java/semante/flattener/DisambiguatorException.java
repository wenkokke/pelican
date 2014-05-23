package semante.flattener;

import static lombok.AccessLevel.PRIVATE;
import semante.pipeline.Result;
import semante.pipeline.impl.IResult$Error;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class DisambiguatorException extends IllegalArgumentException {
	
	// automatically generated serial UID
	private static final long serialVersionUID = -1617060415020690761L;
	
	Object id;
	
	public DisambiguatorException(Object id, String s) {
		super(s);
		this.id = id;
	}
	
	@SuppressWarnings("unchecked")
	public <ID> Result<ID> toResult() {
		return new IResult$Error<ID>((ID) id, getMessage());
	}
	
}
