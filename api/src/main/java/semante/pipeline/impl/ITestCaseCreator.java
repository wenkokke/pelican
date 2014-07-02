package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Pair;
import semante.pipeline.ResultType;
import semante.pipeline.TestCaseCreator;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class ITestCaseCreator implements TestCaseCreator {
	
	@NonFinal StringBuilder test;
	
	/*
	@Override
	public final String createTestCase(
			final String packageName,
			final String className,
			final String comment,
			final SimpleBinaryTree<Annotation<Integer>> text,
			final SimpleBinaryTree<Annotation<Integer>> hypo,
			final List<Pair<Integer,Integer>> subs,
			final ResultType resultType) {
		
		// create a labeller;
		val lbl = ILabeller.labeller();
		
		val builder = ImmutableList.<Pair<BinaryTree<Integer,Annotation<Integer>>,BinaryTree<Integer,Annotation<Integer>>>> builder();
		for (val sub: subs) {
			builder.add(pair(lbl.label(sub.getFirst()),lbl.label(sub.getSecond())));
		}
		
		// label the pair trees and forward;
		return createTestCase(
			packageName,
			className,
			comment,
			lbl.label(text),
			lbl.label(hypo),
			builder.build(),
			resultType);
	}*/

	@Override
	public final <ID> String createTestCase(
			final String packageName,
			final String className,
			final String comment,
			final BinaryTree<ID, Annotation<ID>> text,
			final BinaryTree<ID, Annotation<ID>> hypo,
			final List<Pair<ID,ID>> subs,
			final ResultType resultType) {
		
		// create a string buffer for the test;
		test = new StringBuilder();
		
		boolean incSubs = subs!=null && subs.size()>0;
		
		List<String> subsImport;
		String subsReference;
		
		if (incSubs) {
			subsImport = Arrays.asList(
					"import java.util.List;",
					"import com.google.common.collect.Lists;",
					"import semante.pipeline.Pair;",
					"import semante.pipeline.impl.IPair;");
			subsReference = ", subs";
		} else {
			subsImport = Arrays.asList();
			subsReference = "";
		}
		
		
		// create the java source code;
		if (packageName != null) {
			line("package %s;", packageName);
			line();
		}
		line("import lombok.val;");
		line("import org.junit.Test;");
		line("import semante.pipeline.AbsPipelineTest;");
		line("import static semante.pipeline.ResultType.*;");
		if (incSubs) {
			for (String imp : subsImport) { 
				line(imp);
			}
		}
		line();
		line("public final class Test%s extends AbsPipelineTest {", className);
		if (! comment.isEmpty()) {
			line();
			line(1,"/*");
			line(2,comment);
			line(1,"*/");
		}
		line();
		line(1,"@Test");
		line(1,"public final void test%s() throws Exception {", className);
		line();
		line(2,"// create the vocabulary for the text;");
		val vt = vocabulary("t", text);
		line();
		line(2,"// create the vocabulary for the hypothesis;");
		val vh = vocabulary("h", hypo);
		line();
		line(2,"// create the tree structure for the text;");
		line(2,"val tt ="); tree(vt);
		line();
		line(2,"// create the tree structure for the hypothesis;");
		line(2,"val th ="); tree(vh);
		line();
		line(2,"// create the subsumption relations;");
		if (incSubs) {
			subs(subs);
		}
		line();
		line(2,"// test for a proof;");
		switch (resultType) {
		case Proof:
			line(2,"assertProof(tt, th" + subsReference + ");");
			break;
		case NoProof:
			line(2,"assertNoProof(tt, th" + subsReference + ");");
			break;
		case Exception:
			line(2,"assertException(tt, th" + subsReference + ");");
			break;
		}
		line(2,"// test the testcasecreator;");
		line(2,"testTestCaseCreator(tt, th, %s" + subsReference + ");", resultType.toString());
		
		line(1,"}");
		line();
		line("}");
		
		return test.toString();
	}
	
	// outputs an array of subsumption relations;
	private final <ID> void subs(
		final List<Pair<ID,ID>> subs) {

		ID id = subs.get(0).getFirst();
		String idName = id.getClass().getSimpleName();
		line(1, "List<Pair<"+idName+","+idName+">> subs = Lists.newArrayList();");
		for (Pair<ID,ID> sub : subs) {
			line(1, "subs.add(new IPair<"+idName+","+idName+">(" + sub.getFirst().toString() + "," + sub.getSecond().toString() + "));");
		}
	}
	
	// outputs a tree of variable names;
	private final <ID> void tree(
			final BinaryTree<ID, String> tree) {
		tree.accept(new BinaryTree.Visitor<ID, String, Void>() {

			@NonFinal Integer depth = 2;
			
			@Override
			public final Void leaf(String name) {
				line(depth, name);
				return null;
			}

			@Override
			public final Void node(ID _, BinaryTree<ID, String> l, BinaryTree<ID, String> r) {
				line(depth,"_("); 	// _(
				depth++;			// 
				l.accept(this);				//	left-subtree
				line(depth,",");			// 	,
				r.accept(this);				//	right-subtree
				line(depth,",");			//	,
				line(depth,_.toString());	//  id
				depth--;			// 
				line(depth,")");	// );
				return null;
			}
		});
		line(2,";");
	}
	
	// outputs the vocabulary for a given annotation tree and returns a tree of variable names;
	private final <ID> BinaryTree<ID, String> vocabulary(
			final String prefix,
			final BinaryTree<ID, Annotation<ID>> tree) {
		return tree.accept(new BinaryTree.Visitor<ID, Annotation<ID>, BinaryTree<ID, String>>() {
			
			@NonFinal Integer id = 0;

			@Override
			public final BinaryTree<ID, String> leaf(Annotation<ID> ann) {
				return IBinaryTree.leaf(
					ann.accept(new Annotation.Visitor<String,ID>() {
						@Override
						public final String annotation(ID id, String text, String category) {
							val safe = text.replaceAll(" ","_").toLowerCase();
							val name = String.format("%s%02d_%s", prefix, id, safe);
							line(2,"val %s = word(\"%s\",\"%s\",%s);", name, category, text, id.toString());
							return name;
							
						}
					}));
			}

			@Override
			public final BinaryTree<ID, String> node(ID _, BinaryTree<ID, Annotation<ID>> l, BinaryTree<ID, Annotation<ID>> r) {
				return IBinaryTree.node(_, l.accept(this), r.accept(this));
			}

		});
	}
	
	// outputs a blank line;
	private final void line() {
		line("");
	}
	
	// outputs the given line after applying String#format;
	private final void line(String line, Object... args) {
		test.append(String.format(line, args)).append('\n');
	}
	
	// outputs the given line after applying String#format and indenting to the given depth;
	private final void line(Integer depth, String line, Object... args) {
		line(indent(depth,line),args);
	}
	
	// indents a string to the given depth;
	private final String indent(Integer depth, String str) {
		return repeat("\t", depth) + str;
	}
	
	// repeats a string a given number of times;
	private final String repeat(String str, Integer times) {
		if (times > 0) {
			return str + repeat(str, times - 1);
		}
		else {
			return str;
		}
	}
	
}
