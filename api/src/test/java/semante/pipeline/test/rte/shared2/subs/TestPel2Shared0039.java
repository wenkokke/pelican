package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0039 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0039() throws Exception {

			// create the vocabulary for the text;
			val t01_bbc = word("NP_D","BBC",1);
			val t02_reported = word("FACT","reported",2);
			val t03_that = word("IGNORE","that",3);
			val t04_the = word("THE","the",4);
			val t39_october_earthquake = word("N","October_earthquake",39);
			val t07_occurred = word("V_1","occurred",7);
			val t08_at = word("P_R","at",8);
			val t09_the = word("THE","the",9);
			val t42_pacific_side_boundary = word("N","Pacific_side_boundary",42);
			val t13_of = word("P_R","of",13);
			val t14_the = word("THE","the",14);
			val t44_kuril_arc = word("N","Kuril_arc",44);
			val t55_app = word("WHO_A","APP",55);
			val t17_ = word("IGNORE","",17);
			val t18_the = word("THE","the",18);
			val t47_south_kuril_islands = word("NP_D","South_Kuril_Islands",47);
			val t22_ = word("IGNORE","",22);

			// create the vocabulary for the hypothesis;
			val h01_an = word("A","An",1);
			val h02_earthquake = word("N","earthquake",2);
			val h03_occurred = word("V_1","occurred",3);
			val h04_at = word("P_R","at",4);
			val h05_the = word("THE","the",5);
			val h16_south_kuril_islands = word("NP_D","South_Kuril_Islands",16);

			// create the tree structure for the text;
			val tt =
			_(
				t01_bbc
				,
				_(
					t02_reported
					,
					_(
						t03_that
						,
						_(
							_(
								t04_the
								,
								t39_october_earthquake
								,
								40
							)
							,
							_(
								t07_occurred
								,
								_(
									t08_at
									,
									_(
										_(
											t09_the
											,
											_(
												t42_pacific_side_boundary
												,
												_(
													t13_of
													,
													_(
														t14_the
														,
														t44_kuril_arc
														,
														45
													)
													,
													31
												)
												,
												43
											)
											,
											54
										)
										,
										_(
											t55_app
											,
											_(
												_(
													t17_
													,
													_(
														t18_the
														,
														t47_south_kuril_islands
														,
														48
													)
													,
													49
												)
												,
												t22_
												,
												50
											)
											,
											56
										)
										,
										32
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
					37
				)
				,
				51
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_an
					,
					h02_earthquake
					,
					10
				)
				,
				_(
					h03_occurred
					,
					_(
						h04_at
						,
						_(
							h05_the
							,
							h16_south_kuril_islands
							,
							17
						)
						,
						12
					)
					,
					13
				)
				,
				18
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(39,2));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
