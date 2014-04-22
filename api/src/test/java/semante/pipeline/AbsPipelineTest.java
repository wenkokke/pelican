package semante.pipeline;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.Assert.fail;
import static semante.pipeline.impl.IPair.pair;
import lambdacalc.STL;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.lexicon.impl.IRichLexicon;
import semante.pipeline.impl.IAnnotation;
import semante.pipeline.impl.IBinaryTree;
import semante.pipeline.impl.ILabeller;
import semante.pipeline.impl.IPair;
import semante.pipeline.impl.IPipeline;
import semante.pipeline.impl.ISimpleBinaryTree;
import semante.settings.impl.ISettings;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;

@FieldDefaults(level=PRIVATE)
public class AbsPipelineTest {

	Pipeline pipeline;
	Labeller labeller;
	BinaryTreeFunctor<Integer, Pair<String, String>, Integer, Annotation> annotator;

	public AbsPipelineTest() {
		try {
            val lambdacalc = new STL();
            val settings   = new ISettings();
            val lexicon    = new IRichLexicon(settings,lambdacalc);
		    pipeline       = new IPipeline(settings,lambdacalc,lexicon);
		    labeller       = ILabeller.labeller();
		    annotator      = IBinaryTree.functor(
				Functions.<Integer> identity(),
					new Function<Pair<String,String>,Annotation>() {
						@Override
						public final Annotation apply(final Pair<String,String> pair) {
							return new IAnnotation(pair.getFirst(),pair.getSecond());
						}
				});
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected final Pair<SimpleBinaryTree<Pair<String, String>>, SimpleBinaryTree<Pair<String, String>>> subs(
			SimpleBinaryTree<Pair<String, String>> fst,
			SimpleBinaryTree<Pair<String, String>> snd) {
		return IPair.pair(fst, snd);
	}
	
	protected final BinaryTree<Integer,Annotation> label(SimpleBinaryTree<Pair<String,String>> input) {
		return labeller.label(input).accept(annotator);
	}
	
	protected final SimpleBinaryTree<Pair<String,String>> _(SimpleBinaryTree<Pair<String,String>> l, SimpleBinaryTree<Pair<String,String>> r) {
		return ISimpleBinaryTree.node(l, r);
	}

	protected final SimpleBinaryTree<Pair<String,String>> word(String category, String text) {
		return ISimpleBinaryTree.leaf(pair(text, category));
	}
	
	protected final Result<Integer> prove
		(SimpleBinaryTree<Pair<String,String>> text
		,SimpleBinaryTree<Pair<String,String>> hypo
		,Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs)
			throws Exception {
		
		val builder = ImmutableList.<Pair<BinaryTree<Integer,Annotation>,BinaryTree<Integer,Annotation>>> builder();
		for (val sub: subs) {
			builder.add(pair(label(sub.getFirst()),label(sub.getSecond())));
		}
		return pipeline.prove(label(text), label(hypo), builder.build());
	}
	
	protected final void assertProof
		(SimpleBinaryTree<Pair<String,String>> text
		,SimpleBinaryTree<Pair<String,String>> hypo
		,Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs)
			throws Exception {
		prove(text,hypo,subs).accept(new AssertProof<Integer>());
	}
	
	protected final void assertProof
		(SimpleBinaryTree<Pair<String,String>> text
		,SimpleBinaryTree<Pair<String,String>> hypo
		,Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>... subs)
				throws Exception {
		assertProof(text,hypo,ImmutableList.copyOf(subs));
	}
	
	protected final void assertNoProof
		(SimpleBinaryTree<Pair<String,String>> text
		,SimpleBinaryTree<Pair<String,String>> hypo
		,Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs)
			throws Exception {
		prove(text,hypo,subs).accept(new AssertNoProof<Integer>());
	}
	
	protected final void assertNoProof
		(SimpleBinaryTree<Pair<String,String>> text
		,SimpleBinaryTree<Pair<String,String>> hypo
		,Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>... subs)
				throws Exception {
		assertNoProof(text,hypo,ImmutableList.copyOf(subs));
	}
	
	private final class AssertProof<ID> implements Result.Visitor<ID, Void> {

		@Override
		public final Void proof() {
			return null;
		}

		@Override
		public final Void unknown() {
			fail("No proof or counterexample found.");
			return null;
		}

		@Override
		public final Void counterExample() {
			fail("Counterexample found.");
			return null;
		}

		@Override
		public final Void exception(ID id) {
			fail("type error at node "+id);
			return null;
		}

		@Override
		public Void exception(ID id, String msg) {
			fail("type error at node "+id+": "+msg);
			return null;
		}
		
	}	

	private final class AssertNoProof<ID> implements Result.Visitor<ID, Void> {

		@Override
		public final Void proof() {
			fail("Proof found.");
			return null;
		}

		@Override
		public final Void unknown() {
			return null;
		}

		@Override
		public final Void counterExample() {
			return null;
		}

		@Override
		public final Void exception(ID id) {
			fail("type error at node "+id);
			return null;
		}

		@Override
		public Void exception(ID id, String msg) {
			fail("type error at node "+id+": "+msg);
			return null;
		}
		
	}
	
}
