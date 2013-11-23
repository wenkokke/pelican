package semante.flattener;

import static lombok.AccessLevel.PRIVATE;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class FlattenError extends IllegalArgumentException {
	
	// automatically generated serial UID
	private static final long serialVersionUID = -1617060415020690761L;
	
	Object id;
	
	public FlattenError(Object id, String s) {
		super(s);
		this.id = id;
	}
	
}
