package semante.pipeline.gerunds;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestGerund3 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createEntailment());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("TestCase03",createEntailment());
		}

		public final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_john     = word("NP_D","John");
			val t01_finished = word("V_2","finished");
			val t02_the      = word("THE","the");
			val t03_affair   = word("N","affair");
			val t04_by       = word("P_R","by");
			val t05_writing  = word("GER_2","writing");
			val t06_a        = word("A","a");
			val t07_letter   = word("N","letter");

			// create the vocabulary for the hypothesis;
			val h00_john     = word("NP_D","John");
			val h01_finished = word("V_2","finished");
			val h02_the      = word("THE","the");
			val h03_affair   = word("N","affair");

			// create the tree structure for the text;
			val tt =
			_(
				t00_john
				,
				_(
					_(
						t01_finished
						,
						_(
							t02_the
							,
							t03_affair
						)
					)
					,
					_(
						t04_by
						,
						_(
							t05_writing
							,
							_(
								t06_a
								,
								t07_letter
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
					h01_finished
					,
					_(
						h02_the
						,
						h03_affair
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
