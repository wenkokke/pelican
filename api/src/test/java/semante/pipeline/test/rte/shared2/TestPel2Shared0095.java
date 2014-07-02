package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0095 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0095() throws Exception {

			// create the vocabulary for the text;
			val t01_many = word("SOME","Many",1);
			val t02_patients = word("N","patients",2);
			val t03_were = word("V_AUX","were",3);
			val t04_shocked = word("FACT","shocked",4);
			val t05_that = word("IGNORE","that",5);
			val t06_tom = word("$NPC_1$","Tom",6);
			val t07_jennings = word("NP_D","Jennings",7);
			val t34_app = word("WHO_A","APP",34);
			val t09_the = word("THE","the",9);
			val t10_boardcertified = word("MR","boardCertified",10);
			val t11_ophthalmologist = word("N","ophthalmologist",11);
			val t24_died_from = word("V_2","died_from",24);
			val t15_a = word("A","a",15);
			val t16_stroke = word("N","stroke",16);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_boardcertified = word("MR","boardCertified",2);
			val h03_ophthalmologist = word("N","ophthalmologist",3);
			val h12_died_from = word("V_2","died_from",12);
			val h06_a = word("A","a",6);
			val h07_stroke = word("N","stroke",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_many
					,
					t02_patients
					,
					18
				)
				,
				_(
					t03_were
					,
					_(
						t04_shocked
						,
						_(
							t05_that
							,
							_(
								_(
									_(
										t06_tom
										,
										t07_jennings
										,
										19
									)
									,
									_(
										t34_app
										,
										_(
											t09_the
											,
											_(
												t10_boardcertified
												,
												t11_ophthalmologist
												,
												30
											)
											,
											31
										)
										,
										35
									)
									,
									32
								)
								,
								_(
									t24_died_from
									,
									_(
										t15_a
										,
										t16_stroke
										,
										22
									)
									,
									36
								)
								,
								25
							)
							,
							26
						)
						,
						27
					)
					,
					28
				)
				,
				33
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_boardcertified
						,
						h03_ophthalmologist
						,
						14
					)
					,
					15
				)
				,
				_(
					h12_died_from
					,
					_(
						h06_a
						,
						h07_stroke
						,
						10
					)
					,
					17
				)
				,
				16
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

