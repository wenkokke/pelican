package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0088 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0088() throws Exception {

			// create the vocabulary for the text;
			val t21_de_klerk = word("NP_D","De_Klerk",21);
			val t03_was = word("IS","was",3);
			val t46_deputy_president_of_south_africa = word("NP_D","Deputy_President_of_South_Africa",46);
			val t09_during = word("P_R","during",9);
			val t10_the = word("THE","the",10);
			val t11_term = word("N","term",11);
			val t12_of = word("P_R","of",12);
			val t13_the = word("THE","the",13);
			val t14_former = word("MR","former",14);
			val t15_president = word("N","president",15);
			val t44_app = word("WHO_A","APP",44);
			val t36_nelson_mandela = word("NP_D","Nelson_Mandela",36);
			val t18_until = word("P_R","until",18);
			val t19_num1996 = word("NP_D","num1996",19);

			// create the vocabulary for the hypothesis;
			val h08_nelson_mandela = word("NP_D","Nelson_Mandela",8);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_former = word("MR","former",5);
			val h06_president = word("N","president",6);

			// create the tree structure for the text;
			val tt =
			_(
				t21_de_klerk
				,
				_(
					_(
						_(
							t03_was
							,
							t46_deputy_president_of_south_africa
							,
							35
						)
						,
						_(
							t09_during
							,
							_(
								t10_the
								,
								_(
									t11_term
									,
									_(
										t12_of
										,
										_(
											_(
												t13_the
												,
												_(
													t14_former
													,
													t15_president
													,
													38
												)
												,
												39
											)
											,
											_(
												t44_app
												,
												t36_nelson_mandela
												,
												45
											)
											,
											28
										)
										,
										47
									)
									,
									26
								)
								,
								43
							)
							,
							30
						)
						,
						40
					)
					,
					_(
						t18_until
						,
						t19_num1996
						,
						32
					)
					,
					41
				)
				,
				42
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h08_nelson_mandela
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						_(
							h05_former
							,
							h06_president
							,
							12
						)
						,
						13
					)
					,
					10
				)
				,
				14
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
