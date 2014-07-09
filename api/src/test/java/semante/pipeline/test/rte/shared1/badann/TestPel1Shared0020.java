package semante.pipeline.test.rte.shared1.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel1Shared0020 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0020() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_zulu = word("NP_D","Zulu",2);
			val t03_are = word("IS","are",3);
			val t04_an = word("A","an",4);
			val t05_african = word("MI","African",5);
			val t06_ethnic = word("MR","ethnic",6);
			val t07_group = word("N","group",7);
			val t08_of = word("P_R","of",8);
			val t09_about = word("MR","about",9);
			val t10_num11 = word("NUMBER","num11",10);
			val t11_million = word("MR","million",11);
			val t12_people = word("N","people",12);
			val t13_who = word("WHO_A","who",13);
			val t14_mainly = word("MR","mainly",14);
			val t15_live = word("V_1","live",15);
			val t16_in = word("P_R","in",16);
			val t30_kwazulunatal_province = word("NP_D","KwaZuluNatal_Province",30);
			val t51_app = word("WHO_A","APP",51);
			val t31_south_africa = word("NP_D","South_Africa",31);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_zulus = word("NP_D","Zulus",2);
			val h03_mainly = word("MR","mainly",3);
			val h04_live = word("V_1","live",4);
			val h05_in = word("P_R","in",5);
			val h11_kwazulunatal_province = word("NP_D","KwazuluNatal_Province",11);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					t02_zulu
					,
					23
				)
				,
				_(
					t03_are
					,
					_(
						_(
							t04_an
							,
							_(
								t05_african
								,
								_(
									t06_ethnic
									,
									_(
										t07_group
										,
										_(
											t08_of
											,
											_(
												t09_about
												,
												_(
													t10_num11
													,
													_(
														t11_million
														,
														t12_people
														,
														43
													)
													,
													50
												)
												,
												44
											)
											,
											27
										)
										,
										40
									)
									,
									49
								)
								,
								41
							)
							,
							42
						)
						,
						_(
							t13_who
							,
							_(
								t14_mainly
								,
								_(
									t15_live
									,
									_(
										t16_in
										,
										_(
											t30_kwazulunatal_province
											,
											_(
												t51_app
												,
												t31_south_africa
												,
												52
											)
											,
											46
										)
										,
										33
									)
									,
									34
								)
								,
								35
							)
							,
							36
						)
						,
						47
					)
					,
					38
				)
				,
				48
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					h02_zulus
					,
					9
				)
				,
				_(
					h03_mainly
					,
					_(
						h04_live
						,
						_(
							h05_in
							,
							h11_kwazulunatal_province
							,
							12
						)
						,
						13
					)
					,
					15
				)
				,
				16
			)
			;

			// create the subsumption relations;
			List<Pair<Integer,Integer>> subs = Lists.newArrayList();
			subs.add(new IPair<Integer,Integer>(30,11));
			subs.add(new IPair<Integer,Integer>(2,2));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
