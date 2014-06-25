package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0020 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0020() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_zulu = word("NP_D","Zulu");
			val t02_are = word("IS","are");
			val t03_an = word("A","an");
			val t04_african = word("MI","African");
			val t05_ethnic = word("MR","ethnic");
			val t06_group = word("N","group");
			val t07_of = word("P_R","of");
			val t08_about = word("MR","about");
			val t09_num11 = word("NUMBER","num11");
			val t10_million = word("MR","million");
			val t11_people = word("N","people");
			val t12_who = word("WHO_A","who");
			val t13_live = word("V_1","live");
			val t14_in = word("P_R","in");
			val t15_kwazulunatal = word("$NPC_1$","KwaZuluNatal");
			val t16_province = word("NP_D","Province");
			val t17_app = word("WHO_A","APP");
			val t18_south = word("$NPC_1$","South");
			val t19_africa = word("NP_D","Africa");

			// create the vocabulary for the hypothesis;
			val h00_the = word("THE","The");
			val h01_zulus = word("NP_D","Zulus");
			val h02_live = word("V_1","live");
			val h03_in = word("P_R","in");
			val h04_kwazulunatal = word("$NPC_1$","KwazuluNatal");
			val h05_province = word("NP_D","Province");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_the
					,
					t01_zulu
				)
				,
				_(
					t02_are
					,
					_(
						_(
							t03_an
							,
							_(
								t04_african
								,
								_(
									t05_ethnic
									,
									_(
										t06_group
										,
										_(
											t07_of
											,
											_(
												t08_about
												,
												_(
													t09_num11
													,
													_(
														t10_million
														,
														t11_people
													)
												)
											)
										)
									)
								)
							)
						)
						,
						_(
							t12_who
							,
							_(
								t13_live
								,
								_(
									t14_in
									,
									_(
										_(
											t15_kwazulunatal
											,
											t16_province
										)
										,
										_(
											t17_app
											,
											_(
												t18_south
												,
												t19_africa
											)
										)
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
				_(
					h00_the
					,
					h01_zulus
				)
				,
				_(
					h02_live
					,
					_(
						h03_in
						,
						_(
							h04_kwazulunatal
							,
							h05_province
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val st000_zulu = word("NP_D","Zulu");
			val sh000_zulu = word("NP_D","Zulu");
val st0 = 
			st000_zulu
			;
val sh0 = 
			sh000_zulu
			;
val ss0 = subs(st0, sh0);
			val st100_kwazulunatal = word("$NPC_1$","KwaZuluNatal");
			val sh100_kwazulunatal = word("$NPC_1$","KwaZuluNatal");
val st1 = 
			st100_kwazulunatal
			;
val sh1 = 
			sh100_kwazulunatal
			;
val ss1 = subs(st1, sh1);
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
			ss0,
			ss1
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

