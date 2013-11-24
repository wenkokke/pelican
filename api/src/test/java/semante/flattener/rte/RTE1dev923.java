package semante.flattener.rte;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.flattener.AFlattenerTest;

public final class RTE1dev923 extends AFlattenerTest {
	
		@Before
		public final void setUp() throws Exception {
			super.setUp(getClass());
			this.entailment = createRTE1dev923();
		}
		
		@Test
		public final void flattenTest() {
			super.flattenTest();
		}

		public final Entailment createRTE1dev923() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_arabic = word("MR","Arabic");
			val t02_network = word("N","network");
			val t03_who = word("WHO_R","who");
			val t04_is = word("IS","is");
			val t05_aljazeera = word("NP_D","AlJazeera");
			val t06_broadcasted = word("V_2","broadcasted");
			val t07_a = word("A","a");
			val t08_videoclip = word("N","videoclip");
			val t09_from = word("P_R","from");
			val t10_some = word("SOME","some");
			val t11_militants = word("N","militants");

			// create the vocabulary for the hypothesis;
			val h00_aljazeera = word("NP_D","AlJazeera");
			val h01_is = word("IS","is");
			val h02_an = word("A","an");
			val h03_arabic = word("MR","Arabic");
			val h04_network = word("N","network");
			val h05_which = word("WHO_A","which");
			val h06_broadcasted = word("V_2","broadcasted");
			val h07_a = word("A","a");
			val h08_videoclip = word("N","videoclip");
			val h09_from = word("P_R","from");
			val h10_some = word("SOME","some");
			val h11_militants = word("N","militants");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_the
					,
					_(
						_(
							t01_arabic
							,
							t02_network
						)
						,
						_(
							t03_who
							,
							_(
								t04_is
								,
								t05_aljazeera
							)
						)
					)
				)
				,
				_(
					t06_broadcasted
					,
					_(
						t07_a
						,
						_(
							t08_videoclip
							,
							_(
								t09_from
								,
								_(
									t10_some
									,
									t11_militants
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
				h00_aljazeera
				,
				_(
					h01_is
					,
					_(
						_(
							h02_an
							,
							_(
								h03_arabic
								,
								h04_network
							)
						)
						,
						_(
							h05_which
							,
							_(
								h06_broadcasted
								,
								_(
									h07_a
									,
									_(
										h08_videoclip
										,
										_(
											h09_from
											,
											_(
												h10_some
												,
												h11_militants
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

			// create the subsumption relations;
			val ss =
			new String[] {
				""
			};

			// return the new entailment;
			return new IEntailment(tt, th, ss);
		}

}
