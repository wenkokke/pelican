package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0087 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0087() throws Exception {

			// create the vocabulary for the text;
			val t40_amar_mani_tripathi = word("NP_D","Amar_Mani_Tripathi",40);
			val t46_app = word("WHO_A","APP",46);
			val t04_ = word("IGNORE","",4);
			val t05_the = word("THE","the",5);
			val t06_former = word("MR","former",6);
			val t07_minister = word("N","minister",7);
			val t08_ = word("IGNORE","",8);
			val t09_is = word("IS","is",9);
			val t10_in = word("P_R","in",10);
			val t52_det = word("EMPTYDET","DET",52);
			val t11_jail = word("N","jail",11);
			val t12_in = word("P_R","in",12);
			val t50_det = word("EMPTYDET","DET",50);
			val t13_connection = word("N","connection",13);
			val t14_with = word("P_R","with",14);
			val t15_the = word("THE","the",15);
			val t16_killing = word("N","killing",16);
			val t17_of = word("P_R","of",17);
			val t18_the = word("THE","the",18);
			val t19_poetess = word("N","poetess",19);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_former = word("MR","former",2);
			val h03_minister = word("N","minister",3);
			val h04_is = word("IS","is",4);
			val h05_in = word("P_R","in",5);
			val h38_det = word("EMPTYDET","DET",38);
			val h06_jail = word("N","jail",6);
			val h07_in = word("P_R","in",7);
			val h36_det = word("EMPTYDET","DET",36);
			val h08_connection = word("N","connection",8);
			val h09_with = word("P_R","with",9);
			val h10_the = word("THE","the",10);
			val h11_killing = word("N","killing",11);
			val h12_of = word("P_R","of",12);
			val h13_a = word("A","a",13);
			val h14_poetess = word("N","poetess",14);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t40_amar_mani_tripathi
					,
					_(
						t46_app
						,
						_(
							_(
								t04_
								,
								_(
									t05_the
									,
									_(
										t06_former
										,
										t07_minister
										,
										41
									)
									,
									42
								)
								,
								43
							)
							,
							t08_
							,
							44
						)
						,
						47
					)
					,
					24
				)
				,
				_(
					_(
						_(
							t09_is
							,
							_(
								t10_in
								,
								_(
									t52_det
									,
									t11_jail
									,
									53
								)
								,
								29
							)
							,
							37
						)
						,
						_(
							t12_in
							,
							_(
								t50_det
								,
								t13_connection
								,
								51
							)
							,
							27
						)
						,
						38
					)
					,
					_(
						t14_with
						,
						_(
							t15_the
							,
							_(
								t16_killing
								,
								_(
									t17_of
									,
									_(
										t18_the
										,
										t19_poetess
										,
										31
									)
									,
									32
								)
								,
								30
							)
							,
							49
						)
						,
						34
					)
					,
					48
				)
				,
				45
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_former
						,
						h03_minister
						,
						31
					)
					,
					32
				)
				,
				_(
					_(
						_(
							h04_is
							,
							_(
								h05_in
								,
								_(
									h38_det
									,
									h06_jail
									,
									39
								)
								,
								21
							)
							,
							29
						)
						,
						_(
							h07_in
							,
							_(
								h36_det
								,
								h08_connection
								,
								37
							)
							,
							19
						)
						,
						30
					)
					,
					_(
						h09_with
						,
						_(
							h10_the
							,
							_(
								h11_killing
								,
								_(
									h12_of
									,
									_(
										h13_a
										,
										h14_poetess
										,
										23
									)
									,
									24
								)
								,
								22
							)
							,
							35
						)
						,
						26
					)
					,
					34
				)
				,
				33
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
