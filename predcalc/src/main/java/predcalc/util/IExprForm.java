package predcalc.util;

import java.util.List;

import predcalc.ExprForm;

import lombok.experimental.Value;

@Value
public class IExprForm<Kind> implements ExprForm<Kind> {

	Kind semantics;
	List<Kind> pragmatics;

}
