package semante.pipeline;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.Assert.fail;
import static semante.pipeline.impl.IPair.pair;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.tools.ToolProvider;

import lambdacalc.STL;
import lombok.Cleanup;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.lexicon.impl.IRichLexicon;
import semante.pipeline.impl.IAnnotation;
import semante.pipeline.impl.IBinaryTree;
import semante.pipeline.impl.ILabeller;
import semante.pipeline.impl.IPair;
import semante.pipeline.impl.IPipeline;
import semante.pipeline.impl.ISimpleBinaryTree;
import semante.pipeline.impl.ITestCaseCreator;
import semante.settings.impl.ISettings;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

@FieldDefaults(level=PRIVATE)
public class AbsPipelineTest {

	protected Pipeline pipeline;
	Labeller labeller;
	TestCaseCreator testCaseCreator;
	//BinaryTreeFunctor<Integer, Pair<String, String>, Integer, Annotation> annotator;

	public AbsPipelineTest() {
		this(ISettings.defaultSettingsFile());
	}
	
	public AbsPipelineTest(File settingsFile) {
		try {
            val lambdacalc  = new STL();
            val settings    = new ISettings(settingsFile);
            val lexicon     = new IRichLexicon(settings,lambdacalc);
		    pipeline        = new IPipeline(settings,lambdacalc,lexicon);
		    labeller        = ILabeller.labeller();
		    testCaseCreator = new ITestCaseCreator();
		    /*
		    annotator       = IBinaryTree.functor(
				Functions.<Integer> identity(),
					new Function<Pair<String,String>,Annotation>() {
						@Override
						public final Annotation apply(final Pair<String,String> pair) {
							return new IAnnotation(pair.getFirst(),pair.getSecond());
						}
				});*/
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
	
	/*
	protected final BinaryTree<ID,Annotation<ID>> label(SimpleBinaryTree<Pair<String,String>> input) {
		return labeller.label(input).accept(annotator);
	}*/
	
	protected final <ID> BinaryTree<ID,Annotation<ID>> _(BinaryTree<ID,Annotation<ID>> l, BinaryTree<ID,Annotation<ID>> r, ID id) {
		return IBinaryTree.node(id, l, r);
	}

	protected final <ID> BinaryTree<ID,Annotation<ID>> word(String category, String text, ID id) {
		return IBinaryTree.leaf((Annotation<ID>)new IAnnotation<ID>(id,text,category));
	}
	
	protected final <ID> Result<ID> prove
		(BinaryTree<ID,Annotation<ID>> text
		,BinaryTree<ID,Annotation<ID>> hypo
		,List<Pair<ID,ID>> subs)
			throws Exception {
		return pipeline.prove(text, hypo, subs);
	}
	
	protected final <ID> void assertProof
		(BinaryTree<ID,Annotation<ID>> text
		,BinaryTree<ID,Annotation<ID>> hypo
		,List<Pair<ID,ID>> subs)
			throws Exception {
		prove(text,hypo,subs).accept(new AssertProof<ID>());
	}

	protected final <ID> void assertProof
	(BinaryTree<ID,Annotation<ID>> text
	,BinaryTree<ID,Annotation<ID>> hypo)
		throws Exception {
	prove(text,hypo,new ArrayList<Pair<ID,ID>>()).accept(new AssertProof<ID>());
	}
	
	protected final <ID> void assertNoProof
		(BinaryTree<ID,Annotation<ID>> text
		,BinaryTree<ID,Annotation<ID>> hypo
		,List<Pair<ID,ID>> subs)
			throws Exception {
		prove(text,hypo,subs).accept(new AssertNoProof<ID>());
	}
	
	protected final <ID> void assertNoProof
		(BinaryTree<ID,Annotation<ID>> text
		,BinaryTree<ID,Annotation<ID>> hypo)
				throws Exception {
		assertNoProof(text,hypo,new ArrayList<Pair<ID,ID>>());
	}
	
	protected final <ID> void assertException
		(BinaryTree<ID,Annotation<ID>> text
		,BinaryTree<ID,Annotation<ID>> hypo
		,List<Pair<ID,ID>> subs)
			throws Exception {
		prove(text,hypo,subs).accept(new AssertException<ID>());
	}
	
	protected final <ID> void assertException
		(BinaryTree<ID,Annotation<ID>> text
		,BinaryTree<ID,Annotation<ID>> hypo)
				throws Exception {
		assertException(text,hypo,new ArrayList<Pair<ID,ID>>());
	}
	
	protected final <ID> void testTestCaseCreator
		(BinaryTree<ID,Annotation<ID>> text
		,BinaryTree<ID,Annotation<ID>> hypo
		,ResultType resultType)
				throws IOException{
		testTestCaseCreator(text,hypo,resultType,new ArrayList<Pair<ID,ID>>());
	}
	
	protected final <ID> void testTestCaseCreator
		(BinaryTree<ID,Annotation<ID>> text
		,BinaryTree<ID,Annotation<ID>> hypo
		,ResultType resultType
		,List<Pair<ID,ID>> subs)		
				throws IOException {
		
		// create a temporary file for the test case.
		val temp = File.createTempFile("TestTest", ".java");
		
		@Cleanup
		val writer = new BufferedWriter(new FileWriter(temp));
		
		// convert the subsumptions to the correct format.
		val builder = ImmutableList.<Pair<BinaryTree<ID,Annotation<ID>>,BinaryTree<ID,Annotation<ID>>>> builder();
		
		// write the test case to the temporary file.
		val testCaseTest = testCaseCreator.createTestCase(
			null, "Test", "", text, hypo, subs, resultType);
		writer.append(testCaseTest);
		
		// attempt to compile the file
		val compiler = ToolProvider.getSystemJavaCompiler();
		val exitCode = compiler.run(null, null, null, temp.getAbsolutePath());
		if(exitCode == 0) {
			System.err.println("Compiled test case.");
		} else {
			fail("Compilation of test case failed.");
		}
		// try to delete the file right away, otherwise delete it on exit.
		if (!temp.delete()) {
			 temp.deleteOnExit();
		}
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
			fail("Type error at node "+id);
			return null;
		}

		@Override
		public Void exception(ID id, String msg) {
			fail("Type error at node "+id+": "+msg);
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
			fail("Type error at node "+id);
			return null;
		}

		@Override
		public Void exception(ID id, String msg) {
			fail("Type error at node "+id+": "+msg);
			return null;
		}
		
	}
	
	private final class AssertException<ID> implements Result.Visitor<ID, Void> {

		@Override
		public Void counterExample() {
			fail("Counter example found.");
			return null;
		}

		@Override
		public Void exception(ID arg0) {
			return null;
		}

		@Override
		public Void exception(ID arg0, String arg1) {
			return null;
		}

		@Override
		public Void proof() {
			fail("Proof found.");
			return null;
		}

		@Override
		public Void unknown() {
			fail("No proof or counterexample found.");
			return null;
		}
		
	}
	
}
