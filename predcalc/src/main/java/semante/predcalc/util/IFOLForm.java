package semante.predcalc.util;

import java.util.List;

import lombok.experimental.Value;
import semante.predcalc.FOLExpr.Formula;
import semante.predcalc.FOLForm;

@Value
public class IFOLForm implements FOLForm {

	Formula semantics;
	List<Formula> pragmatics;

}
