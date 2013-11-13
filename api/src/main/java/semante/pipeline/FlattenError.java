package semante.pipeline;

import static lombok.AccessLevel.PRIVATE;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class FlattenError extends IllegalArgumentException {
	
	Object id;
	
	public FlattenError(Object id, String s) {
		super(s);
		this.id = id;
	}
	
}
