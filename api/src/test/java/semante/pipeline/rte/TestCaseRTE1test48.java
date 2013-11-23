package semante.pipeline.rte;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCaseRTE1test48 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE1test48());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE1test48",createRTE1test48());
		}

		public final Entailment createRTE1test48() throws Exception {

			// create the vocabulary for the text;
			val t00_clinton = word("NP","clinton");
			val t01_is = word("IS","is");
			val t02_a = word("A","a");
			val t03_very = word("MOD_R","very");
			val t04_charismatic = word("MOD_I","charismatic");
			val t05_person = word("N_1","person");

			// create the vocabulary for the hypothesis;
			val h00_clinton = word("NP","clinton");
			val h01_is = word("IS","is");
			val h02_articulate = word("MOD_I","articulate");

			// create the tree structure for the text;
			val tt =
			_(
				t00_clinton
				,
				_(
					t01_is
					,
					_(
						t02_a
						,
						_(
							t03_very
							,
							_(
								t04_charismatic
								,
								t05_person
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_clinton
				,
				_(
					h01_is
					,
					h02_articulate
				)
			)
			;

			// create the subsumption relations;
			val ss =
			new String[] {
				"all x (charismatic(x) -> articulate(x))."
			};

			// return the new entailment;
			return new IEntailment(tt, th, ss);
		}

}

