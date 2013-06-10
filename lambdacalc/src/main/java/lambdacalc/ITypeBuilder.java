package lambdacalc;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.IType.IConstant;
import lambdacalc.IType.IFunction;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public class ITypeBuilder implements TypeBuilder {
	
	@Override
	public Type constant(String name) {
		if (name.equals("e")) {
			return IType.E;
		}
		else
		if (name.equals("t")) {
			return IType.T;
		}
		else {
			return new IConstant(name);
		}
	}
	
	@Override
	public Type function(Type arg0, Type arg1) {
		return new IFunction(arg0,arg1);
	}
}
