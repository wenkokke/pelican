package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0018 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0018() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_book = word("N","book",2);
			val t03_contains = word("V_2","contains",3);
			val t33_det = word("EMPTYDET","DET",33);
			val t04_short = word("MR","short",4);
			val t05_stories = word("N","stories",5);
			val t06_by = word("P_R","by",6);
			val t07_the = word("THE","the",7);
			val t08_famous = word("MR","famous",8);
			val t09_bulgarian = word("MI","Bulgarian",9);
			val t10_writer = word("N","writer",10);
			val t30_app = word("WHO_A","APP",30);
			val t12_nikolai = word("$NPC_1$","Nikolai",12);
			val t13_haitov = word("NP_D","Haitov",13);

			// create the vocabulary for the hypothesis;
			val h01_nikolai = word("$NPC_1$","Nikolai",1);
			val h02_haitov = word("NP_D","Haitov",2);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_writer = word("N","writer",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					t02_book
					,
					15
				)
				,
				_(
					t03_contains
					,
					_(
						t33_det
						,
						_(
							_(
								t04_short
								,
								t05_stories
								,
								16
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
												,
												24
											)
											,
											25
										)
										,
										26
									)
									,
									_(
										t30_app
										,
										_(
											t12_nikolai
											,
											t13_haitov
											,
											18
										)
										,
										31
									)
									,
									27
								)
								,
								20
							)
							,
							23
						)
						,
						34
					)
					,
					32
				)
				,
				29
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_nikolai
					,
					h02_haitov
					,
					7
				)
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						h05_writer
						,
						8
					)
					,
					9
				)
				,
				11
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

