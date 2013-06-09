package lambdacalc;

import static lombok.AccessLevel.PRIVATE;
import lombok.Delegate;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class STL implements
	ExprParser,
	TypePrinter, SymbolPrinter, ExprPrinter, DeBruijnPrinter,
	Expr2DeBruijn,
	DeBruijnSubstituter,
	Expr2FreeNames {

	// parsing functions
	@Delegate ExprParser			exprParser			= new IExprParser();
	
	// printing functions
	@Delegate TypePrinter			typePrinter			= new ITypePrinter(); 
	@Delegate SymbolPrinter			symbolPrinter		= new ISymbolPrinter(typePrinter);
	@Delegate ExprPrinter			exprPrinter			= new IExprPrinter(symbolPrinter);
	@Delegate DeBruijnPrinter   	deBruijnPrinter 	= new IDeBruijnPrinter(typePrinter,symbolPrinter);
	
	// builder functions
	          ExprBuilder			exprBuilder			= new IExprBuilder();
	          DeBruijnBuilder		deBruijnBuilder		= new IDeBruijnBuilder();
	
	// conversion functions
	@Delegate Expr2DeBruijn			expr2DeBruijn		= new IExpr2DeBruijn(deBruijnBuilder);
	@Delegate Expr2FreeNames		expr2FreeNames		= new IExpr2FreeNames();
	
	// reduction functions
	@Delegate DeBruijnSubstituter 	deBruijnSubstituter	= new IDeBruijnSubstituter(deBruijnBuilder);
	@Delegate DeBruijnBetaReducer	deBruijnBetaReducer = new IDeBruijnBetaReducer(deBruijnSubstituter);
	
}
