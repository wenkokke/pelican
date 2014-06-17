package semante.predcalc.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import semante.predcalc.ExprForm;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level = PRIVATE)
public class IExprForm<Kind> implements ExprForm<Kind> {

	Kind		semantics;
	List<Kind>	pragmatics;

}
