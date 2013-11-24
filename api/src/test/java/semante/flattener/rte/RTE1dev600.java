package semante.flattener.rte;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.flattener.AFlattenerTest;

public final class RTE1dev600 extends AFlattenerTest {
	
		@Before
		public final void setUp() throws Exception {
			super.setUp(getClass());
			this.entailment = createRTE1dev600();
		}
		
		@Test
		public final void flattenTest() {
			super.flattenTest();
		}

		public final Entailment createRTE1dev600() throws Exception {

			// create the vocabulary for the text;
			val t00_lp = word("NP_D","LP");
			val t01_app = word("WHO_A","APP");
			val t02_the = word("THE","the");
			val t03_chinese = word("MR","Chinese");
			val t04_dog = word("N","dog");
			val t05_app = word("WHO_R","APP");
			val t06_accused = word("V_1","accused");
			val t07_of = word("P_R","of");
			val t08_a = word("A","a");
			val t09_1971 = word("MR","nineteenseventyone");
			val t10_plot = word("N","plot");
			val t11_of = word("P_R","of");
			val t12_an = word("A","an");
			val t13_uprising = word("N","uprising");
			val t14_sneezed = word("V_1","sneezed");

			// create the vocabulary for the hypothesis;
			val h00_lp = word("NP_D","LP");
			val h01_is = word("IS","is");
			val h02_a = word("A","a");
			val h03_chinese = word("MR","Chinese");
			val h04_dog = word("N","dog");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_lp
					,
					_(
						t01_app
						,
						_(
							t02_the
							,
							_(
								_(
									t03_chinese
									,
									t04_dog
								)
								,
								_(
									t05_app
									,
									_(
										t06_accused
										,
										_(
											t07_of
											,
											_(
												t08_a
												,
												_(
													_(
														t09_1971
														,
														t10_plot
													)
													,
													_(
														t11_of
														,
														_(
															t12_an
															,
															t13_uprising
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
				,
				t14_sneezed
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_lp
				,
				_(
					h01_is
					,
					_(
						h02_a
						,
						_(
							h03_chinese
							,
							h04_dog
						)
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
