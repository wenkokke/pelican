package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.Assert.fail;

import java.io.IOException;

import lombok.val;
import lombok.experimental.FieldDefaults;

import org.junit.Before;

import semante.lambdacalc.impl.IT;
import semante.lexicon.impl.IRichLexicon;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Entailment;
import semante.pipeline.Pipeline;
import semante.pipeline.Result.Visitor;
import semante.settings.SettingsException;
import semante.settings.impl.ISettings;
import semante.util.Pair;
import semante.util.SimpleBinaryTree;
import semante.util.binarytree.impl.ILabeller;
import semante.util.impl.IAnnotation;
import semante.util.impl.IBinaryTree;

import com.google.common.base.Function;
import com.google.common.base.Functions;

@FieldDefaults(level=PRIVATE)
public abstract class ATestCase {
	
	Pipeline pipeline;
	
	@Before
	public final void setUpPipeline() throws SettingsException, IOException {
		val lambdacalc = IT.LambdaCalc();
		val settings   = new ISettings();
		val lexicon    = new IRichLexicon(settings,lambdacalc);
		pipeline = new IPipeline(settings,lambdacalc,lexicon);
	}
	
	protected final void someTest(final Entailment ent) throws Exception {
		someTest(ent.getText(),ent.getHypothesis(),ent.getSubsumptions());
	}
	
	protected final void someTest(
			final SimpleBinaryTree<Pair<String,String>> txt,
			final SimpleBinaryTree<Pair<String,String>> hyp,
			final String subs) throws Exception {
		
		// label the annotation trees.
		val lbl = new ILabeller();
		val lbltxt = lbl.label(txt);
		val lblhyp = lbl.label(hyp);
		
		someTest(lbltxt,lblhyp,subs);
	}
		
	protected final void someTest(
				final BinaryTree<Integer,Pair<String,String>> lbltxt,
				final BinaryTree<Integer,Pair<String,String>> lblhyp,
				final String subs) throws Exception {
		
		// convert pairs to annotations.
		val ann = IBinaryTree.functor(
			Functions.<Integer> identity(),
			new Function<Pair<String,String>,Annotation>() {
				
				@Override
				public final Annotation apply(final Pair<String,String> pair) {
					return new IAnnotation(pair.getFirst(),pair.getSecond());
				}
			});
		val anntxt = lbltxt.accept(ann);
		val annhyp = lblhyp.accept(ann);
		
		// execute the pipeline.
		pipeline.<Integer> prove(anntxt, annhyp, subs).accept(
			new Visitor<Integer,Void>() {

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
				public final Void exception(Integer id) {
					fail("type error at node "+id);
					return null;
				}

				@Override
				public Void exception(Integer id, String msg) {
					fail("type error at node "+id+": "+msg);
					return null;
				}			
			});
		
	}
}
