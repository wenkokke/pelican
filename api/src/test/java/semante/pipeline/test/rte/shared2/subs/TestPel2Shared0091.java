package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0091 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0091() throws Exception {

			// create the vocabulary for the text;
			val t01_clinton = word("NP_D","Clinton",1);
			val t26_app = word("WHO_A","APP",26);
			val t03_the = word("THE","the",3);
			val t04_american = word("MI","American",4);
			val t05_president = word("N","president",5);
			val t07_is = word("IS","is",7);
			val t08_a = word("A","a",8);
			val t09_very = word("MR","very",9);
			val t10_charismatic = word("MI","charismatic",10);
			val t11_person = word("N","person",11);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_president = word("N","president",2);
			val h03_is = word("IS","is",3);
			val h04_an = word("A","an",4);
			val h05_articulate = word("MI","articulate",5);
			val h06_american = word("MI","American",6);
			val h07_person = word("N","person",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_clinton
					,
					_(
						t26_app
						,
						_(
							t03_the
							,
							_(
								t04_american
								,
								t05_president
								,
								22
							)
							,
							23
						)
						,
						27
					)
					,
					24
				)
				,
				_(
					t07_is
					,
					_(
						t08_a
						,
						_(
							t09_very
							,
							_(
								t10_charismatic
								,
								t11_person
								,
								16
							)
							,
							28
						)
						,
						21
					)
					,
					18
				)
				,
				25
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					h02_president
					,
					9
				)
				,
				_(
					h03_is
					,
					_(
						h04_an
						,
						_(
							h05_articulate
							,
							_(
								h06_american
								,
								h07_person
								,
								11
							)
							,
							12
						)
						,
						13
					)
					,
					14
				)
				,
				16
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(10,5));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
