package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0069 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0069() throws Exception {

			// create the vocabulary for the text;
			val t27_det = word("EMPTYDET","DET",27);
			val t01_gemsbok = word("N","Gemsbok",1);
			val t16_depend_on = word("V_2","depend_on",16);
			val t04_the = word("THE","the",4);
			val t19_ugab_wetlands = word("N","Ugab_wetlands",19);
			val t25_app = word("WHO_A","APP",25);
			val t08_the = word("THE","the",8);
			val t09_namimbian = word("MI","Namimbian",9);
			val t10_river = word("N","River",10);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_ugab = word("N","Ugab",2);
			val h03_is = word("IS","is",3);
			val h04_namimbian = word("MI","Namimbian",4);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t27_det
					,
					t01_gemsbok
					,
					28
				)
				,
				_(
					t16_depend_on
					,
					_(
						_(
							t04_the
							,
							t19_ugab_wetlands
							,
							20
						)
						,
						_(
							t25_app
							,
							_(
								t08_the
								,
								_(
									t09_namimbian
									,
									t10_river
									,
									21
								)
								,
								22
							)
							,
							26
						)
						,
						23
					)
					,
					29
				)
				,
				24
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					h02_ugab
					,
					6
				)
				,
				_(
					h03_is
					,
					h04_namimbian
					,
					8
				)
				,
				10
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(19,2));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
