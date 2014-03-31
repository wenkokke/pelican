package semante.pipeline.factives;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCase04 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createEntailment());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("TestCase04",createEntailment());
		}

		public final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_john = word("NP_D","John");
			val t01_loves = word("V_2","loves");
			val t02_a = word("A","a");
			val t03_senior = word("MR","senior");
			val t04_coalition = word("MR","coalition");
			val t05_official = word("N","official");
			val t06_in = word("P_R","in");
			val t07_iraq = word("NP_D","Iraq");
			val t08_who = word("WHO_R","who");
			val t09_said = word("NOFACT","said");
			val t10_the = word("THE","the");
			val t11_body = word("N","body");
			val t12_was = word("IS","was");
			val t13_found = word("V_1","found");
			val t14_by = word("P_R","by");
			val t15_the = word("THE","the");
			val t16_police = word("N","police");

			// create the vocabulary for the hypothesis;
			val h00_john = word("NP_D","John");
			val h01_loves = word("V_2","loves");
			val h02_an = word("A","an");
			val h03_official = word("N","official");

			// create the tree structure for the text;
			val tt =
			_(
				t00_john
				,
				_(
					t01_loves
					,
					_(
						t02_a
						,
						_(
							_(
								_(
									t03_senior
									,
									_(
										t04_coalition
										,
										t05_official
									)
								)
								,
								_(
									t06_in
									,
									t07_iraq
								)
							)
							,
							_(
								t08_who
								,
								_(
									t09_said
									,
									_(
										_(
											t10_the
											,
											t11_body
										)
										,
										_(
											t12_was
											,
											_(
												t13_found
												,
												_(
													t14_by
													,
													_(
														t15_the
														,
														t16_police
													)
												)
											)
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
				h00_john
				,
				_(
					h01_loves
					,
					_(
						h02_an
						,
						h03_official
					)
				)
			)
			;

			// create the subsumption relations;
			val ss =
			new String[] {
				""
			};

			// return the new entailment;
			return new IEntailment(tt, th, ss);
		}

}
