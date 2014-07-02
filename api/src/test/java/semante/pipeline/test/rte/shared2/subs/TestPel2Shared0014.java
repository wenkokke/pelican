package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0014 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0014() throws Exception {

			// create the vocabulary for the text;
			val t01_mossad = word("NP_D","Mossad",1);
			val t35_app = word("WHO_A","APP",35);
			val t03_the = word("THE","the",3);
			val t04_israeli = word("MI","Israeli",4);
			val t05_intelligence = word("$NC_1$","intelligence",5);
			val t06_agency = word("N","agency",6);
			val t08_is = word("IS","is",8);
			val t09_the = word("THE","the",9);
			val t10_world = word("N","world",10);
			val t11_s = word("GEN","s",11);
			val t12_most = word("MR","most",12);
			val t13_famous = word("MI","famous",13);
			val t14_secret = word("$NC_1$","secret",14);
			val t15_service = word("N","service",15);

			// create the vocabulary for the hypothesis;
			val h01_mossad = word("NP_D","Mossad",1);
			val h02_is = word("IS","is",2);
			val h03_a = word("A","a",3);
			val h04_wellknown = word("MI","wellKnown",4);
			val h05_intelligence = word("$NC_1$","intelligence",5);
			val h06_agency = word("N","agency",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_mossad
					,
					_(
						t35_app
						,
						_(
							t03_the
							,
							_(
								t04_israeli
								,
								_(
									t05_intelligence
									,
									t06_agency
									,
									30
								)
								,
								31
							)
							,
							32
						)
						,
						36
					)
					,
					33
				)
				,
				_(
					t08_is
					,
					_(
						_(
							_(
								t09_the
								,
								t10_world
								,
								27
							)
							,
							t11_s
							,
							28
						)
						,
						_(
							t12_most
							,
							_(
								t13_famous
								,
								_(
									t14_secret
									,
									t15_service
									,
									25
								)
								,
								21
							)
							,
							37
						)
						,
						29
					)
					,
					23
				)
				,
				34
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_mossad
				,
				_(
					h02_is
					,
					_(
						h03_a
						,
						_(
							h04_wellknown
							,
							_(
								h05_intelligence
								,
								h06_agency
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
					10
				)
				,
				15
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(13,4));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

