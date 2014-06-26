package semante.pipeline.test.rte.shared1.pass;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0018 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0018() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_book = word("N","book");
			val t02_contains = word("V_2","contains");
			val t03_det = word("EMPTYDET","DET");
			val t04_short = word("MR","short");
			val t05_stories = word("N","stories");
			val t06_by = word("P_R","by");
			val t07_the = word("THE","the");
			val t08_famous = word("MR","famous");
			val t09_bulgarian = word("MI","Bulgarian");
			val t10_writer = word("N","writer");
			val t11_app = word("WHO_A","APP");
			val t12_nikolai = word("$NPC_1$","Nikolai");
			val t13_haitov = word("NP_D","Haitov");

			// create the vocabulary for the hypothesis;
			val h00_nikolai = word("$NPC_1$","Nikolai");
			val h01_haitov = word("NP_D","Haitov");
			val h02_is = word("IS","is");
			val h03_a = word("A","a");
			val h04_writer = word("N","writer");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_the
					,
					t01_book
				)
				,
				_(
					t02_contains
					,
					_(
						t03_det
						,
						_(
							_(
								t04_short
								,
								t05_stories
							)
							,
							_(
								t06_by
								,
								_(
									_(
										t07_the
										,
										_(
											t08_famous
											,
											_(
												t09_bulgarian
												,
												t10_writer
											)
										)
									)
									,
									_(
										t11_app
										,
										_(
											t12_nikolai
											,
											t13_haitov
										)
									)
								)
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_nikolai
					,
					h01_haitov
				)
				,
				_(
					h02_is
					,
					_(
						h03_a
						,
						h04_writer
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

