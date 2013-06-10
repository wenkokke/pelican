package lambdacalc;

import static lombok.AccessLevel.PRIVATE;
import static lambdacalc.IType.*;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Delegate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class STL implements ExprParser, TypePrinter, SymbolPrinter,
		IndexPrinter, ExprPrinter, DeBruijnPrinter, DeBruijnSubstituter,
		Expr2DeBruijn, Expr2FreeNames, DeBruijn2Expr, DeBruijn2FreeNames,
		DeBruijnTypeChecker, DeBruijn2Type {

	// parsing functions
	@Delegate ExprParser			exprParser			= new IExprParser();
	
	// naming conventions
	          Map<Type,String>      namingConventions   = Maps.newHashMap();
	          {
	        	  namingConventions.put(T,"b");
	        	  namingConventions.put(E,"x");
	        	  namingConventions.put(ET,"A");
	        	  namingConventions.put(EET,"A");
	        	  namingConventions.put(EEET,"A");
	        	  namingConventions.put(ET_T,"P");
	        	  namingConventions.put(ET_ET,"M");
	        	  namingConventions.put(ET_ET__ET_ET,"M");
	          }
	
	// printing functions
	@Delegate TypePrinter			typePrinter			= new ITypePrinter(); 
	@Delegate SymbolPrinter			symbolPrinter		= new ISymbolPrinter(typePrinter);
	@Delegate IndexPrinter			indexPrinter		= new IIndexPrinter(typePrinter);
	@Delegate ExprPrinter			exprPrinter			= new IExprPrinter(symbolPrinter);
	@Delegate DeBruijnPrinter   	deBruijnPrinter 	= new IDeBruijnPrinter(typePrinter,indexPrinter,symbolPrinter);
	
	// builder functions
	@Getter   TypeBuilder			typeBuilder			= new ITypeBuilder();
	@Getter   ExprBuilder			exprBuilder			= new IExprBuilder();
	@Getter   DeBruijnBuilder		deBruijnBuilder		= new IDeBruijnBuilder();
	
	// conversion functions
  	@Delegate Expr2FreeNames		expr2FreeNames		= new IExpr2FreeNames();
	@Delegate DeBruijn2FreeNames	deBruijn2FreeNames	= new IDeBruijn2FreeNames();
	@Delegate Expr2DeBruijn			expr2DeBruijn		= new IExpr2DeBruijn(deBruijnBuilder);
	@Delegate DeBruijn2Expr			deBruijn2Expr		= new IDeBruijn2Expr(exprBuilder,namingConventions,deBruijn2FreeNames);
	@Delegate DeBruijn2Type			deBruijn2Type		= new IDeBruijn2Type(typeBuilder);
	@Delegate DeBruijnTypeChecker	deBruijnTypeChecker	= new IDeBruijnTypeChecker(typeBuilder);
	
	// reduction functions
	@Delegate DeBruijnSubstituter 	deBruijnSubstituter	= new IDeBruijnSubstituter(deBruijnBuilder);
	@Delegate DeBruijnBetaReducer	deBruijnBetaReducer = new IDeBruijnBetaReducer(deBruijnSubstituter);
	
}
