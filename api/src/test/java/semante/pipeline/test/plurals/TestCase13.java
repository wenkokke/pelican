package semante.pipeline.test.plurals;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestCase13 extends AbsPipelineTest {

		@Test
		public final void TestCase13t() throws Exception {

			// create the vocabulary for the text;
			val t00_andy = word("NP_D","Andy");
			val t01_and = word("AND","and");
			val t02_yoni = word("NP_D","Yoni");
			val t03_who = word("WHO_A","who");
			val t04_won = word("V_2","won");
			val t05_the = word("THE","the");
			val t06_australian = word("$NC_1$","Australian");
			val t07_open = word("N","Open");
			val t08_are = word("IS","are");
			val t09_successful = word("MR","successful");
			val t10_tennis = word("$NC_1$","tennis");
			val t11_players = word("N","players");

			// create the vocabulary for the hypothesis;
			val h00_andy = word("NP_D","Andy");
			val h01_and = word("AND","and");
			val h02_yoni = word("NP_D","Yoni");
			val h03_who = word("WHO_A","who");
			val h04_won = word("V_2","won");
			val h05_the = word("THE","the");
			val h06_australian = word("$NC_1$","Australian");
			val h07_open = word("N","Open");
			val h08_are = word("IS","are");
			val h09_successful = word("MR","successful");
			val h10_tennis = word("$NC_1$","tennis");
			val h11_players = word("N","players");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_andy
						,
						_(
							t01_and
							,
							t02_yoni
						)
					)
					,
					_(
						t03_who
						,
						_(
							t04_won
							,
							_(
								t05_the
								,
								_(
									t06_australian
									,
									t07_open
								)
							)
						)
					)
				)
				,
				_(
					t08_are
					,
					_(
						t09_successful
						,
						_(
							t10_tennis
							,
							t11_players
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h00_andy
						,
						_(
							h01_and
							,
							h02_yoni
						)
					)
					,
					_(
						h03_who
						,
						_(
							h04_won
							,
							_(
								h05_the
								,
								_(
									h06_australian
									,
									h07_open
								)
							)
						)
					)
				)
				,
				_(
					h08_are
					,
					_(
						h09_successful
						,
						_(
							h10_tennis
							,
							h11_players
						)
					)
				)
			)
			;

			// create the subsumption relations;
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
);

			// test for a proof;
			assertException(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Exception, subs);
		}

}
