package semante.pipeline.impl;

import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.impl.IBinaryTree;


public class ITreeTools {
	
	public static <ID, A extends Annotation<ID>> ID getId(BinaryTree<ID, A> tree) {
		return tree.accept(new BinaryTree.Visitor<ID,A,ID>() {

			@Override
			public ID node(ID value, BinaryTree<ID, A> l,
					BinaryTree<ID, A> r) {
				return value;
			}

			@Override
			public ID leaf(A value) {
				return value.getId();
			}
			
		});
	}

	
	static public <ID, A extends Annotation<ID>> BinaryTree<ID, A> extractElement(BinaryTree<ID, A> tree, final ID elementId) {

		return tree.accept(new BinaryTree.Visitor<ID,A,BinaryTree<ID, A>>() {

			@Override
			public BinaryTree<ID, A> leaf(A arg0) {
				return arg0.getId().equals(elementId) ? IBinaryTree.<ID,A>leaf(arg0) : null;
			}

			@Override
			public BinaryTree<ID, A> node(ID arg0,
					BinaryTree<ID, A> l,
					BinaryTree<ID, A> r) {
				BinaryTree<ID, A> ret = null;
				if (arg0.equals(elementId)) {
					ret = IBinaryTree.node(arg0,l,r);
				} 
				if (ret==null) {
					ret = l.accept(this);
				}
				if (ret==null) {
					ret = r.accept(this);
				}
				return ret;
			}
			
		});	
	}
	
	static public <ID,A extends Annotation<ID>> String printReadable(final BinaryTree<ID, A> tree, final boolean incText) {
		final StringBuilder bracketStructure = new StringBuilder();
		final StringBuilder treeAnnotation = new StringBuilder();

		final IndentLeveler levelr = new IndentLeveler();
		
		tree.accept(new BinaryTree.Visitor<ID, A,Void>() {

			@Override
			public Void node(ID arg0,
					BinaryTree<ID, A> l,
					BinaryTree<ID, A> r) {

				if (incText) {
					append(bracketStructure,"[");
				}

				if (treeAnnotation.length()>0) {
					append(treeAnnotation,"\n");
				}
				appendIndent(treeAnnotation,"(XP" + ":" + arg0);
				
				levelr.push();
				if (l!=null) {
					l.accept(this);
				}
				if (r!=null) {
					r.accept(this);
				}
				levelr.pop();
				
				treeAnnotation.append(")");
				
				if (incText) {
					bracketStructure.append("]");
				}
				return null;
			}

			@Override
			public Void leaf(A value) {
				if (incText) {
					append(bracketStructure,value.getText());
				}
				if (treeAnnotation.length()>0) {
					append(treeAnnotation,"\n");
				}
				appendIndent(treeAnnotation,"(" + value.getCategory() + ":" + value.getId() + " ["+value.getText()+"])");				
				return null;
			}

			void append(StringBuilder buff, String str) {
				if (buff.length()>0 &&
					buff.charAt(buff.length()-1)!=' ' &&
					buff.charAt(buff.length()-1)!='(' &&
					buff.charAt(buff.length()-1)!='[') {
					buff.append(" ");
				}
				buff.append(str);
			}
			
			void appendIndent(StringBuilder buff, String str) {
				levelr.indent(treeAnnotation);
				append(buff,str);
			}

			
		});
		return (incText ? bracketStructure.toString() + "\n"  : "") + treeAnnotation.toString(); 
	}
	
	static private class IndentLeveler {
		private int level = 0;
		
		void push() {
			level = level + 1;
		}
		
		void pop() {
			level = level - 1;
		}
		
		void indent(StringBuilder buff) {
			for (int i=0 ; i<level ; i++) {
				buff.append("  ");
			}
		}
		
	}

}
