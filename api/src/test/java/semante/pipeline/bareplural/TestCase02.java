package semante.pipeline.bareplural;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCase02 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createJan01());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("Jan01",createJan01());
		}

		public final Entailment createJan01() throws Exception {

			// create the vocabulary for the text;
			val emptydet    = word("EMPTYDET","");
			val t01_john    = word("NP","john");
			val t02_wrote   = word("V_2","wrote");
			val t03_a       = word("A","a");
			val t04_book    = word("N","book");
			val t05_of      = word("P_R","of");
			val t06_short   = word("MOD_R","short");
			val t07_stories = word("N","stories");

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					t02_wrote
					,
					_(
						t03_a
						,
						_(
							t04_book
							,
							_(
								t05_of
								,
								_(
									emptydet
									,
									_(
										t06_short
										,
										t07_stories
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
				t01_john
				,
				_(
					t02_wrote
					,
					_(
						t03_a
						,
						t04_book
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

