package semante.lexicon;

import java.util.List;

import lambdacalc.DeBruijn;

public interface Word {

	String getName();

	String getText();

	List<DeBruijn> getDenotations();

	Word addDenotation(DeBruijn expr);

}
