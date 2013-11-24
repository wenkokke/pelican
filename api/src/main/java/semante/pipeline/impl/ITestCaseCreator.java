package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
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
			final SimpleBinaryTree<Annotation> hypothesis,
			final String subsumptions) {
		
		// create a labeller;
		val lbl = ILabeller.labeller();
		
		// label the pair trees and forward;
		return createTestCase(name, comment,
			lbl.label(text),
			lbl.label(hypothesis),
			subsumptions);
	}

	@Override
	public final <ID> String createTestCase(
			final String name,
			final String comment,
			final BinaryTree<ID, Annotation> text,
			final BinaryTree<ID, Annotation> hypothesis,
			final String subsumptions) {
		
		// create a string buffer for the test;
		test = new StringBuilder();
		
		// create the java source code;
		line("package semante.pipeline.test.rte;");
		line();
		line("import semante.pipeline.test.Entailment;");
		line("import semante.pipeline.test.impl.ATestCase;");
		line("import semante.pipeline.test.impl.IEntailment;");
		line();
		line("import org.junit.Test;");
		line("import lombok.val;");
		line();
		line("public final class TestCase%s extends ATestCase {", name);
		if (! comment.isEmpty()) {
			line();
			line(1,"/*");
			line(2,comment);
			line(1,"*/");
		}
		line();
		line(1,"@Test");
		line(1,"public final void prove() throws Exception {");
		line(2,"proveEntailment(createEntailment());",name);
		line(1,"}");
		line();
		line(1,"@Test");
		line(1,"public final void createTestCase() throws Exception {");
		line(2,"createTestCase(\"%s\",createEntailment());",name,name);
		line(1,"}");
		line();
		line(1,"public final Entailment createEntailment() throws Exception {");
		line();
		line(2,"// create the vocabulary for the text;");
		val vt = vocabulary("t", text);
		line();
		line(2,"// create the vocabulary for the hypothesis;");
		val vh = vocabulary("h", hypothesis);
		line();
		line(2,"// create the tree structure for the text;");
		line(2,"val tt ="); tree(vt);
		line();
		line(2,"// create the tree structure for the hypothesis;");
		line(2,"val th ="); tree(vh);
		line();
		line(2,"// create the subsumption relations;");
		line(2,"val ss ="); subs(subsumptions);
		line();
		line(2,"// return the new entailment;");
		line(2,"return new IEntailment(tt, th, ss);");
		
		line(1,"}");
		line();
		line("}");
		
		return test.toString();
	}
	
	// outputs an array of subsumption relations;
	private final void subs(final String subsumptions) {
		// split into newlines;
		val subs = subsumptions.split("\n");
		// print as a Java array;
		line(2,"new String[] {");
		for (int i=0; i<subs.length; i++) {
			if (i<subs.length-1) {
				line(3,"\"%s\",", subs[i]);
			}
			else {
				line(3,"\"%s\"", subs[i]);
			}
		}
		line(2,"};");
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
