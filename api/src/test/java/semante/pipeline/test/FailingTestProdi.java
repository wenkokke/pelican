package semante.pipeline.test;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static com.google.common.collect.ImmutableList.of;

public final class FailingTestProdi extends AbsPipelineTest {

		@Test
		public final void testShared112() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_head = word("N","head");
			val t02_of = word("P_R","of");
			val t03_the = word("THE","the");
			val t04_italian = word("MR","Italian");
			val t05_opposition = word("N","opposition");
			val t06_app = word("WHO_A","APP");
			val t07_romano = word("$NPC_1$","Romano");
			val t08_prodi = word("NP_D","Prodi");
			val t09_was = word("IS","was");
			val t10_the = word("THE","the");
			val t11_last = word("MR","last");
			val t12_president = word("N","president");
			val t13_of = word("P_R","of");
			val t14_the = word("THE","the");
			val t15_european = word("MR","European");
			val t16_commission = word("N","Commission");

			// create the vocabulary for the hypothesis;
			val h00_romano = word("$NPC_1$","Romano");
			val h01_prodi = word("NP_D","Prodi");
			val h02_is = word("IS","is");
			val h03_a = word("A","a");
			val h04_former = word("MR","former");
			val h05_president = word("N","president");
			val h06_of = word("P_R","of");
			val h07_the = word("THE","the");
			val h08_european = word("MR","European");
			val h09_commission = word("N","Commission");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						_(
							t01_head
							,
							_(
								t02_of
								,
								_(
									t03_the
									,
									_(
										t04_italian
										,
										t05_opposition
									)
								)
							)
						)
					)
					,
					_(
						t06_app
						,
						_(
							t07_romano
							,
							t08_prodi
						)
					)
				)
				,
				_(
					t09_was
					,
					_(
						t10_the
						,
						_(
							t11_last
							,
							_(
								t12_president
								,
								_(
									t13_of
									,
									_(
										t14_the
										,
										_(
											t15_european
											,
											t16_commission
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
					h00_romano
					,
					h01_prodi
				)
				,
				_(
					h02_is
					,
					_(
						h03_a
						,
						_(
							h04_former
							,
							_(
								h05_president
								,
								_(
									h06_of
									,
									_(
										h07_the
										,
										_(
											h08_european
											,
											h09_commission
										)
									)
								)
							)
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val st000_last = word("MR","last");
			val sh000_last = word("MR","last");
			val st0 = st000_last;
			val sh0 = sh000_last;
			val ss0 = subs(st0, sh0);
			val subs = of(ss0);

			// return the new entailment;
			assertProof(tt, th, subs);
		}

}
