package lambdacalc;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.IDeBruijn.IAbstraction;
import lambdacalc.IDeBruijn.IApplication;
import lambdacalc.IDeBruijn.IConstant;
import lambdacalc.IDeBruijn.IVariable;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public class IDeBruijnBuilder implements DeBruijnBuilder {
	
	@Override
	public DeBruijn abstraction(Type arg0, DeBruijn arg1) {
		return new IAbstraction(arg0,arg1);
	}

	@Override
	public DeBruijn application(DeBruijn arg0, DeBruijn arg1) {
		return new IApplication(arg0,arg1);
	}

	@Override
	public DeBruijn variable(Integer i) {
		return new IVariable(i);
	}

	@Override
	public DeBruijn constant(Symbol s) {
		return new IConstant(s);
	}

}
