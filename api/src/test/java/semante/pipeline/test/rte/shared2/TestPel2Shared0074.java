package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0074 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0074() throws Exception {

			// create the vocabulary for the text;
			val t15_dr_pridi = word("NP_D","Dr_Pridi",15);
			val t03_was = word("IS","was",3);
			val t04_forced = word("V_1","forced",4);
			val t05_into = word("P_R","into",5);
			val t30_det = word("A","DET",30);
			val t06_exile = word("N","exile",6);
			val t08_and = word("AND","and",8);
			val t33_det = word("THE","DET",33);
			val t27_field_marshal = word("N","Field_Marshal",27);
			val t35_app = word("WHO_A","APP",35);
			val t11_pibul = word("NP_D","Pibul",11);
			val t12_assumed = word("V_2","assumed",12);
			val t37_det = word("EMPTYDET","DET",37);
			val t13_power = word("N","power",13);

			// create the vocabulary for the hypothesis;
			val h01_pibul = word("NP_D","Pibul",1);
			val h02_was = word("IS","was",2);
			val h03_a = word("A","a",3);
			val h11_field_marshal = word("N","field_marshal",11);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t15_dr_pridi
					,
					_(
						t03_was
						,
						_(
							t04_forced
							,
							_(
								t05_into
								,
								_(
									t30_det
									,
									t06_exile
									,
									31
								)
								,
								17
							)
							,
							18
						)
						,
						19
					)
					,
					20
				)
				,
				_(
					t08_and
					,
					_(
						_(
							_(
								t33_det
								,
								t27_field_marshal
								,
								39
							)
							,
							_(
								t35_app
								,
								t11_pibul
								,
								36
							)
							,
							40
						)
						,
						_(
							t12_assumed
							,
							_(
								t37_det
								,
								t13_power
								,
								38
							)
							,
							23
						)
						,
						24
					)
					,
					29
				)
				,
				32
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_pibul
				,
				_(
					h02_was
					,
					_(
						h03_a
						,
						h11_field_marshal
						,
						12
					)
					,
					9
				)
				,
				13
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
