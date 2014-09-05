package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0007 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0007() throws Exception {

			// create the vocabulary for the text;
			val t51_det = word("THE","DET",51);
			val t01_mayor = word("N","Mayor",1);
			val t53_app = word("WHO_A","APP",53);
			val t44_gavin_newsom = word("NP_D","Gavin_Newsom",44);
			val t55_app = word("WHO_A","APP",55);
			val t05_a = word("A","a",5);
			val t06_charismatic = word("MR","charismatic",6);
			val t07_public = word("MR","public",7);
			val t08_figure = word("N","figure",8);
			val t09_from = word("P_I","from",9);
			val t10_california = word("NP_D","California",10);
			val t12_turned = word("V_2","turned",12);
			val t58_det = word("EMPTYDET","DET",58);
			val t13_selfsufficiency = word("N","selfSufficiency",13);
			val t14_into = word("P_R","into",14);
			val t15_a = word("A","a",15);
			val t16_formal = word("MR","formal",16);
			val t17_public = word("MR","public",17);
			val t18_awareness = word("MR","awareness",18);
			val t19_campaign = word("N","campaign",19);
			val t20_in = word("P_R","in",20);
			val t32_san_francisco = word("NP_D","San_Francisco",32);

			// create the vocabulary for the hypothesis;
			val h09_gavin_newsom = word("NP_D","Gavin_Newsom",9);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_politician = word("N","politician",5);
			val h06_from = word("P_I","from",6);
			val h07_california = word("NP_D","California",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t51_det
							,
							t01_mayor
							,
							52
						)
						,
						_(
							t53_app
							,
							t44_gavin_newsom
							,
							54
						)
						,
						45
					)
					,
					_(
						t55_app
						,
						_(
							t05_a
							,
							_(
								_(
									t06_charismatic
									,
									_(
										t07_public
										,
										t08_figure
										,
										57
									)
									,
									47
								)
								,
								_(
									t09_from
									,
									t10_california
									,
									27
								)
								,
								48
							)
							,
							61
						)
						,
						56
					)
					,
					49
				)
				,
				_(
					_(
						t12_turned
						,
						_(
							t58_det
							,
							t13_selfsufficiency
							,
							59
						)
						,
						38
					)
					,
					_(
						t14_into
						,
						_(
							t15_a
							,
							_(
								t16_formal
								,
								_(
									t17_public
									,
									_(
										t18_awareness
										,
										_(
											t19_campaign
											,
											_(
												t20_in
												,
												t32_san_francisco
												,
												33
											)
											,
											39
										)
										,
										60
									)
									,
									40
								)
								,
								41
							)
							,
							42
						)
						,
						35
					)
					,
					43
				)
				,
				50
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h09_gavin_newsom
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						_(
							h05_politician
							,
							_(
								h06_from
								,
								h07_california
								,
								12
							)
							,
							10
						)
						,
						17
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
		subs.add(new IPair<Integer,Integer>(1,5));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
