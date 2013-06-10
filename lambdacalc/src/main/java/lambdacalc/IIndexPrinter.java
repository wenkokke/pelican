package lambdacalc;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IIndexPrinter implements IndexPrinter {

	TypePrinter typePrinter;
	
	@Override
	public final String format(Index index) {
		return index.getIndex() + ":" + typePrinter.format(index.getType());
	}

}
