package semante.pipeline.impl;

import semante.pipeline.Entailment;
import org.junit.Test;
import lombok.val;

public final class TestCaseRTE2test410 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE2test410());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE2test410",createRTE2test410());
		}

		public final Entailment createRTE2test410() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","the");
			val t01_head = word("N_1","head");
			val t02_of = word("P_R","of");
			val t03_the = word("THE","the");
			val t04_italian = word("MOD_R","Italian");
			val t05_opposition = word("N_1","opposition");
			val t06_app = word("WHO_A","APP");
			val t07_romano_prodi = word("NP","Romano Prodi");
			val t08_was = word("IS","was");
			val t09_the = word("THE","the");
			val t10_last = word("MOD_R","last");
			val t11_president = word("N_1","president");
			val t12_of = word("P_R","of");
			val t13_the = word("THE","the");
			val t14_european = word("MOD_R","European");
			val t15_commission = word("N_1","Commission");

			// create the vocabulary for the hypothesis;
			val h00_romano_prodi = word("NP","Romano Prodi");
			val h01_is = word("IS","is");
			val h02_a = word("A","a");
			val h03_former = word("MOD_R","former");
			val h04_president = word("N_1","president");
			val h05_of = word("P_R","of");
			val h06_the = word("THE","the");
			val h07_european = word("MOD_R","European");
			val h08_commission = word("N_1","Commission");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						_(
							t01_head
							,
							_(
								t02_of
								,
								_(
									t03_the
									,
									_(
										t04_italian
										,
										t05_opposition
									)
								)
							)
						)
					)
					,
					_(
						t06_app
						,
						t07_romano_prodi
					)
				)
				,
				_(
					t08_was
					,
					_(
						t09_the
						,
						_(
							t10_last
							,
							_(
								t11_president
								,
								_(
									t12_of
									,
									_(
										t13_the
										,
										_(
											t14_european
											,
											t15_commission
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
				h00_romano_prodi
				,
				_(
					h01_is
					,
					_(
						h02_a
						,
						_(
							h03_former
							,
							_(
								h04_president
								,
								_(
									h05_of
									,
									_(
										h06_the
										,
										_(
											h07_european
											,
											h08_commission
										)
									)
								)
							)
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val ss =
			new String[] {
				"all x (last_president(x) -> former_president(x)).",
				"all x (all y (last_of_president(x,y) -> former_of_president(x,y)))."
			};

			// return the new entailment;
			return new IEntailment(tt, th, ss);
		}

}

