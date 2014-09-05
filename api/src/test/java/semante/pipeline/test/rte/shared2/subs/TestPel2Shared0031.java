package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0031 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0031() throws Exception {

			// create the vocabulary for the text;
			val t01_napster = word("NP_D","Napster",1);
			val t58_app = word("WHO_A","APP",58);
			val t03_once = word("MR","once",3);
			val t04_the = word("THE","the",4);
			val t05_biggest = word("MR","biggest",5);
			val t06_unauthorized = word("MI","unauthorized",6);
			val t51_sharing_site = word("N","sharing_site",51);
			val t10_has = word("V_AUX","has",10);
			val t11_now = word("MR","now",11);
			val t12_become = word("V_2","become",12);
			val t13_a = word("A","a",13);
			val t14_legal = word("MR","legal",14);
			val t15_service = word("N","service",15);
			val t17_offering = word("GER_2","offering",17);
			val t66_det = word("EMPTYDET","DET",66);
			val t35_music_downloads = word("N","music_downloads",35);
			val t20_for = word("P_R","for",20);
			val t21_a = word("A","a",21);
			val t22_monthly = word("MR","monthly",22);
			val t23_fee = word("N","fee",23);

			// create the vocabulary for the hypothesis;
			val h01_napster = word("NP_D","Napster",1);
			val h02_was = word("IS","was",2);
			val h03_illegal = word("MI","illegal",3);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_napster
					,
					_(
						t58_app
						,
						_(
							t03_once
							,
							_(
								t04_the
								,
								_(
									t05_biggest
									,
									_(
										t06_unauthorized
										,
										t51_sharing_site
										,
										61
									)
									,
									60
								)
								,
								54
							)
							,
							55
						)
						,
						59
					)
					,
					56
				)
				,
				_(
					_(
						t10_has
						,
						_(
							t11_now
							,
							_(
								t12_become
								,
								_(
									t13_a
									,
									_(
										t14_legal
										,
										t15_service
										,
										43
									)
									,
									44
								)
								,
								33
							)
							,
							31
						)
						,
						42
					)
					,
					_(
						t17_offering
						,
						_(
							t66_det
							,
							_(
								t35_music_downloads
								,
								_(
									t20_for
									,
									_(
										t21_a
										,
										_(
											t22_monthly
											,
											t23_fee
											,
											47
										)
										,
										48
									)
									,
									37
								)
								,
								68
							)
							,
							67
						)
						,
						46
					)
					,
					64
				)
				,
				57
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_napster
				,
				_(
					h02_was
					,
					h03_illegal
					,
					7
				)
				,
				9
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(6,3));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
