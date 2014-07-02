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
			val t05_october = word("$NC_1$","October",5);
			val t06_earthquake = word("N","earthquake",6);
			val t07_occurred = word("V_1","occurred",7);
			val t08_at = word("P_R","at",8);
			val t09_the = word("THE","the",9);
			val t10_pacific = word("$NC_1$","Pacific",10);
			val t11_side = word("$NC_1$","side",11);
			val t12_boundary = word("N","boundary",12);
			val t13_of = word("P_R","of",13);
			val t14_the = word("THE","the",14);
			val t15_kuril = word("$NC_1$","Kuril",15);
			val t16_arc = word("N","arc",16);
			val t55_app = word("WHO_A","APP",55);
			val t17_ = word("IGNORE","",17);
			val t18_the = word("THE","the",18);
			val t19_south = word("$NPC_1$","South",19);
			val t20_kuril = word("$NPC_1$","Kuril",20);
			val t21_islands = word("NP_D","Islands",21);
			val t22_ = word("IGNORE","",22);

			// create the vocabulary for the hypothesis;
			val h01_an = word("A","An",1);
			val h02_earthquake = word("N","earthquake",2);
			val h03_occurred = word("V_1","occurred",3);
			val h04_at = word("P_R","at",4);
			val h05_the = word("THE","the",5);
			val h06_south = word("$NPC_1$","South",6);
			val h07_kuril = word("$NPC_1$","Kuril",7);
			val h08_islands = word("NP_D","Islands",8);

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
								_(
									t05_october
									,
									t06_earthquake
									,
									39
								)
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
												_(
													t10_pacific
													,
													_(
														t11_side
														,
														t12_boundary
														,
														41
													)
													,
													42
												)
												,
												_(
													t13_of
													,
													_(
														t14_the
														,
														_(
															t15_kuril
															,
															t16_arc
															,
															44
														)
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
														_(
															t19_south
															,
															_(
																t20_kuril
																,
																t21_islands
																,
																46
															)
															,
															47
														)
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
							_(
								h06_south
								,
								_(
									h07_kuril
									,
									h08_islands
									,
									15
								)
								,
								16
							)
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

