package semante.predcalc.util;

import java.util.List;

import lombok.experimental.Value;
import semante.predcalc.ExprForm;

@Value
public class IExprForm<Kind> implements ExprForm<Kind> {

	Kind semantics;
	List<Kind> pragmatics;

}
