package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0008 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0008() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_international = word("MI","international");
			val t02_humanitarian = word("MI","humanitarian");
			val t03_aid = word("$NC_1$","aid");
			val t04_organization = word("N","organization");
			val t05_app = word("WHO_A","APP");
			val t06_doctors = word("$NPC_1$","Doctors");
			val t07_without = word("$NPC_1$","Without");
			val t08_borders = word("NP_D","Borders");
			val t09_app = word("WHO_A","APP");
			val t10_medecins = word("$NPC_1$","Medecins");
			val t11_sans = word("$NPC_1$","Sans");
			val t12_frontieres = word("NP_D","Frontieres");
			val t13_app = word("WHO_A","APP");
			val t14_ = word("IGNORE","");
			val t15_msf = word("NP_D","MSF");
			val t16_ = word("IGNORE","");
			val t17_treats = word("V_2","treats");
			val t18_det = word("A","DET");
			val t19_victims = word("N","victims");
			val t20_of = word("P_R","of");
			val t21_det = word("A","DET");
			val t22_violence = word("N","violence");
			val t23_in = word("P_R","in");
			val t24_all = word("EVERY","all");
			val t25_locations = word("N","locations");
			val t26_that = word("WHO_R","that");
			val t27_exhibit = word("V_2","exhibit");
			val t28_det = word("A","DET");
			val t29_violence = word("N","violence");
			val t30_in = word("P_R","in");
			val t31_darfur = word("NP_D","Darfur");

			// create the vocabulary for the hypothesis;
			val h00_doctors = word("$NPC_1$","Doctors");
			val h01_without = word("$NPC_1$","Without");
			val h02_borders = word("NP_D","Borders");
			val h03_is = word("IS","is");
			val h04_an = word("A","an");
			val h05_international = word("MI","international");
			val h06_aid = word("$NC_1$","aid");
			val h07_organization = word("N","organization");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						_(
							t01_international
							,
							_(
								t02_humanitarian
								,
								_(
									t03_aid
									,
									t04_organization
								)
							)
						)
					)
					,
					_(
						t05_app
						,
						_(
							_(
								t06_doctors
								,
								_(
									t07_without
									,
									t08_borders
								)
							)
							,
							_(
								t09_app
								,
								_(
									_(
										t10_medecins
										,
										_(
											t11_sans
											,
											t12_frontieres
										)
									)
									,
									_(
										t13_app
										,
										_(
											_(
												t14_
												,
												t15_msf
											)
											,
											t16_
										)
									)
								)
							)
						)
					)
				)
				,
				_(
					_(
						t17_treats
						,
						_(
							t18_det
							,
							_(
								t19_victims
								,
								_(
									t20_of
									,
									_(
										t21_det
										,
										t22_violence
									)
								)
							)
						)
					)
					,
					_(
						t23_in
						,
						_(
							t24_all
							,
							_(
								t25_locations
								,
								_(
									t26_that
									,
									_(
										t27_exhibit
										,
										_(
											t28_det
											,
											_(
												t29_violence
												,
												_(
													t30_in
													,
													t31_darfur
												)
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
					h00_doctors
					,
					_(
						h01_without
						,
						h02_borders
					)
				)
				,
				_(
					h03_is
					,
					_(
						h04_an
						,
						_(
							h05_international
							,
							_(
								h06_aid
								,
								h07_organization
							)
						)
					)
				)
			)
			;

			// create the subsumption relations;
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

