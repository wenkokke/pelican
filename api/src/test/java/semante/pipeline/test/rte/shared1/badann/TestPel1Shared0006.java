package semante.pipeline.test.rte.shared1.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel1Shared0006 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0006() throws Exception {

			// create the vocabulary for the text;
			val t01_siemens = word("NP_D","Siemens",1);
			val t52_app = word("WHO_A","APP",52);
			val t03_a = word("A","a",3);
			val t04_german = word("MI","German",4);
			val t05_company = word("N","company",5);
			val t07_may = word("V_AUX","may",7);
			val t08_sell = word("V_2","sell",8);
			val t54_det = word("EMPTYDET","DET",54);
			val t09_relevant = word("MR","relevant",9);
			val t39_core_technology = word("N","core_technology",39);
			val t12_to = word("P_R","to",12);
			val t13_china = word("NP_D","China",13);
			val t15_which = word("WHO_A","which",15);
			val t16_owns = word("V_2","owns",16);
			val t17_the = word("THE","the",17);
			val t18_first = word("MR","first",18);
			val t19_international = word("MI","international",19);
			val t42_maglev_line = word("N","Maglev_line",42);

			// create the vocabulary for the hypothesis;
			val h01_german = word("MI","German",1);
			val h02_siemens = word("NP_D","Siemens",2);
			val h03_may = word("V_AUX","may",3);
			val h04_sell = word("V_2","sell",4);
			val h35_det = word("EMPTYDET","DET",35);
			val h05_relevant = word("MR","relevant",5);
			val h06_technology = word("N","technology",6);
			val h07_to = word("P_R","to",7);
			val h08_china = word("NP_D","China",8);
			val h10_which = word("WHO_A","which",10);
			val h11_owns = word("V_2","owns",11);
			val h12_a = word("A","a",12);
			val h30_maglev_line = word("N","Maglev_line",30);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_siemens
					,
					_(
						t52_app
						,
						_(
							t03_a
							,
							_(
								t04_german
								,
								t05_company
								,
								47
							)
							,
							48
						)
						,
						53
					)
					,
					49
				)
				,
				_(
					t07_may
					,
					_(
						_(
							t08_sell
							,
							_(
								t54_det
								,
								_(
									t09_relevant
									,
									t39_core_technology
									,
									40
								)
								,
								55
							)
							,
							41
						)
						,
						_(
							t12_to
							,
							_(
								t13_china
								,
								_(
									t15_which
									,
									_(
										t16_owns
										,
										_(
											t17_the
											,
											_(
												t18_first
												,
												_(
													t19_international
													,
													t42_maglev_line
													,
													43
												)
												,
												51
											)
											,
											44
										)
										,
										31
									)
									,
									33
								)
								,
								45
							)
							,
							35
						)
						,
						46
					)
					,
					37
				)
				,
				50
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_german
					,
					h02_siemens
					,
					16
				)
				,
				_(
					h03_may
					,
					_(
						_(
							h04_sell
							,
							_(
								h35_det
								,
								_(
									h05_relevant
									,
									h06_technology
									,
									17
								)
								,
								36
							)
							,
							29
						)
						,
						_(
							h07_to
							,
							_(
								h08_china
								,
								_(
									h10_which
									,
									_(
										h11_owns
										,
										_(
											h12_a
											,
											h30_maglev_line
											,
											31
										)
										,
										21
									)
									,
									23
								)
								,
								32
							)
							,
							25
						)
						,
						33
					)
					,
					27
				)
				,
				34
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(39,6));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
