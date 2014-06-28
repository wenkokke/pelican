package semante.pipeline.test.iota;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestCase20 extends AbsPipelineTest {

		@Test
		public final void TestCase20() throws Exception {

			// create the vocabulary for the text;
			val t00_john = word("NP_D","John");
			val t01_kissed = word("V_2","kissed");
			val t02_the = word("THE","the");
			val t03_girl = word("N","girl");
			val t04_who = word("WHO_R","who");
			val t05_loves = word("V_2","loves");
			val t06_the = word("THE","the");
			val t07_boy = word("N","boy");
			val t08_and = word("AND","and");
			val t09_bill = word("NP_D","Bill");
			val t10_is = word("IS","is");
			val t11_a = word("A","a");
			val t12_boy = word("N","boy");
			val t13_and = word("AND","and");
			val t14_mary = word("NP_D","Mary");
			val t15_loves = word("V_2","loves");
			val t16_bill = word("NP_D","Bill");

			// create the vocabulary for the hypothesis;
			val h00_john = word("NP_D","John");
			val h01_kissed = word("V_2","kissed");
			val h02_the = word("THE","the");
			val h03_girl = word("N","girl");
			val h04_who = word("WHO_R","who");
			val h05_loves = word("V_2","loves");
			val h06_bill = word("NP_D","Bill");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_john
						,
						_(
							t01_kissed
							,
							_(
								t02_the
								,
								_(
									t03_girl
									,
									_(
										t04_who
										,
										_(
											t05_loves
											,
											_(
												t06_the
												,
												t07_boy
											)
										)
									)
								)
							)
						)
					)
					,
					_(
						t08_and
						,
						_(
							t09_bill
							,
							_(
								t10_is
								,
								_(
									t11_a
									,
									t12_boy
								)
							)
						)
					)
				)
				,
				_(
					t13_and
					,
					_(
						t14_mary
						,
						_(
							t15_loves
							,
							t16_bill
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_john
				,
				_(
					h01_kissed
					,
					_(
						h02_the
						,
						_(
							h03_girl
							,
							_(
								h04_who
								,
								_(
									h05_loves
									,
									h06_bill
								)
							)
						)
					)
				)
			)
			;

			// create the subsumption relations;
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
