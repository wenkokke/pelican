package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0074 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0074() throws Exception {

			// create the vocabulary for the text;
			val t01_dr = word("$NPC_1$","Dr",1);
			val t02_pridi = word("NP_D","Pridi",2);
			val t03_was = word("IS","was",3);
			val t04_forced = word("V_1","forced",4);
			val t05_into = word("P_R","into",5);
			val t30_det = word("A","DET",30);
			val t06_exile = word("N","exile",6);
			val t08_and = word("AND","and",8);
			val t33_det = word("THE","DET",33);
			val t09_field = word("$NC_1$","Field",9);
			val t10_marshal = word("N","Marshal",10);
			val t35_app = word("WHO_A","APP",35);
			val t11_pibul = word("NP_D","Pibul",11);
			val t12_assumed = word("V_2","assumed",12);
			val t37_det = word("EMPTYDET","DET",37);
			val t13_power = word("N","power",13);

			// create the vocabulary for the hypothesis;
			val h01_pibul = word("NP_D","Pibul",1);
			val h02_was = word("IS","was",2);
			val h03_a = word("A","a",3);
			val h04_field = word("$NC_1$","field",4);
			val h05_marshal = word("N","marshal",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_dr
						,
						t02_pridi
						,
						15
					)
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
								_(
									t09_field
									,
									t10_marshal
									,
									27
								)
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
						_(
							h04_field
							,
							h05_marshal
							,
							11
						)
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

