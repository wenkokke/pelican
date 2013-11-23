package semante.flattener.rte;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class RTE1dev1934 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE1dev1934());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE1dev1934",createRTE1dev1934());
		}

		public final Entailment createRTE1dev1934() throws Exception {

			// create the vocabulary for the text;
			val t00_evans = word("NP_D","Evans");
			val t01_app = word("WHO_A","APP");
			val t02_the = word("THE","the");
			val t03_mayor = word("N","mayor");
			val t04_of = word("P_R","of");
			val t05_port_au_prince = word("NP_D","Port_au_Prince");
			val t06_loves = word("V_2","loves");
			val t07_mary = word("NP_D","Mary");

			// create the vocabulary for the hypothesis;
			val h00_evans = word("NP_D","Evans");
			val h01_is = word("IS","is");
			val h02_the = word("THE","the");
			val h03_mayor = word("N","mayor");
			val h04_of = word("P_R","of");
			val h05_port_au_prince = word("NP_D","Port_au_Prince");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_evans
					,
					_(
						t01_app
						,
						_(
							t02_the
							,
							_(
								t03_mayor
								,
								_(
									t04_of
									,
									t05_port_au_prince
								)
							)
						)
					)
				)
				,
				_(
					t06_loves
					,
					t07_mary
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_evans
				,
				_(
					h01_is
					,
					_(
						h02_the
						,
						_(
							h03_mayor
							,
							_(
								h04_of
								,
								h05_port_au_prince
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
