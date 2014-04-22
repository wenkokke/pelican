package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;
import static semante.pipeline.impl.IPair.pair;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.TestCaseCreator;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class ITestCaseCreator implements TestCaseCreator {
	
	@NonFinal StringBuilder test;
	
	@Override
	public final String createTestCase(
			final String name,
			final String comment,
			final SimpleBinaryTree<Annotation> text,
			final SimpleBinaryTree<Annotation> hypo,
			final Iterable<Pair<SimpleBinaryTree<Annotation>,SimpleBinaryTree<Annotation>>> subs) {
		
		// create a labeller;
		val lbl = ILabeller.labeller();
		
		val builder = ImmutableList.<Pair<BinaryTree<Integer,Annotation>,BinaryTree<Integer,Annotation>>> builder();
		for (val sub: subs) {
			builder.add(pair(lbl.label(sub.getFirst()),lbl.label(sub.getSecond())));
		}
		
		// label the pair trees and forward;
		return createTestCase(name, comment,
			lbl.label(text),
			lbl.label(hypo),
			builder.build());
	}

	@Override
	public final <ID> String createTestCase(
			final String name,
			final String comment,
			final BinaryTree<ID, Annotation> text,
			final BinaryTree<ID, Annotation> hypo,
			final Iterable<Pair<BinaryTree<ID, Annotation>,BinaryTree<ID, Annotation>>> subs) {
		
		// create a string buffer for the test;
		test = new StringBuilder();
		
		// create the java source code;
		line("package semante.pipeline.test;");
		line();
		line("import lombok.val;");
		line("import org.junit.Test;");
		line("import semante.pipeline.AbsPipelineTest;");
		line("import static com.google.common.collect.ImmutableList.of;");
		line();
		line("public final class Test%s extends AbsPipelineTest {", name);
		if (! comment.isEmpty()) {
			line();
			line(1,"/*");
			line(2,comment);
			line(1,"*/");
		}
		line();
		line(1,"@Test");
		line(1,"public final void test%s() throws Exception {");
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
		subs(subs);
		line();
		line(2,"// return the new entailment;");
		line(2,"return new IEntailment(tt, th, ss);");
		
		line(1,"}");
		line();
		line("}");
		
		return test.toString();
	}
	
	// outputs an array of subsumption relations;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private final <ID> void subs(
		final Iterable<Pair<BinaryTree<ID,Annotation>,BinaryTree<ID,Annotation>>> subs) {
		val arr = Iterables.toArray(subs, Pair.class);
		for (int i = 0; i < arr.length; i++) {
			val pt = String.format("st%d", i);
			val ph = String.format("sh%d", i);
			val vt = vocabulary(pt, (BinaryTree<ID, Annotation>) arr[i].getFirst());
			val vh = vocabulary(ph, (BinaryTree<ID, Annotation>) arr[i].getFirst());
			 
			line("val st%d = ", i); tree(vt);
			line("val sh%d = ", i); tree(vh);
			line("val ss%d = subs(st%d, sh%d);", i, i, i);
		}
		line("val subs = of(");
		for (int i = 0; i < arr.length; i++) {
			line(2, "ss%d" + ((i != arr.length - 1 ? "," : "")), i);
		}
		line(");");
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
				l.accept(this);		//     left-subtree
				line(depth,",");	//     ,
				r.accept(this);		//     right-subtree
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
			final BinaryTree<ID, Annotation> tree) {
		return tree.accept(new BinaryTree.Visitor<ID, Annotation, BinaryTree<ID, String>>() {
			
			@NonFinal Integer id = 0;

			@Override
			public final BinaryTree<ID, String> leaf(Annotation ann) {
				return IBinaryTree.leaf(
					ann.accept(new Annotation.Visitor<String>() {
						@Override
						public final String annotation(String text, String category) {
							
							val safe = text.replaceAll(" ","_").toLowerCase();
							val name = String.format("%s%02d_%s", prefix, id, safe);
							line(2,"val %s = word(\"%s\",\"%s\");", name, category, text);
							id++;
							return name;
							
						}
					}));
			}

			@Override
			public final BinaryTree<ID, String> node(ID _, BinaryTree<ID, Annotation> l, BinaryTree<ID, Annotation> r) {
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
