package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0032 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0032() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_long = word("MI","long",2);
			val t03_plank = word("N","plank",3);
			val t04_which = word("WHO_A","which",4);
			val t05_extended = word("V_1","extended",5);
			val t06_from = word("P_R","from",6);
			val t07_the = word("THE","the",7);
			val t08_tracks = word("N","tracks",8);
			val t09_provided = word("V_2","provided",9);
			val t10_a = word("A","a",10);
			val t11_haven = word("N","haven",11);
			val t12_for = word("P_R","for",12);
			val t43_det = word("EMPTYDET","DET",43);
			val t13_people = word("N","people",13);
			val t14_who = word("WHO_A","who",14);
			val t15_crossed = word("V_2","crossed",15);
			val t16_the = word("THE","the",16);
			val t17_bridge = word("N","bridge",17);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_plank = word("N","plank",2);
			val h04_which = word("WHO_R","which",4);
			val h05_was = word("IS","was",5);
			val h06_long = word("MI","long",6);
			val h07_and = word("AND","and",7);
			val h08_provided = word("V_2","provided",8);
			val h40_det = word("A","DET",40);
			val h09_haven = word("N","haven",9);
			val h32_jutted_out = word("V_1","jutted_out",32);
			val h13_from = word("P_R","from",13);
			val h14_the = word("THE","the",14);
			val h15_tracks = word("N","tracks",15);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_long
							,
							t03_plank
							,
							39
						)
						,
						40
					)
					,
					_(
						t04_which
						,
						_(
							t05_extended
							,
							_(
								t06_from
								,
								_(
									t07_the
									,
									t08_tracks
									,
									21
								)
								,
								22
							)
							,
							23
						)
						,
						25
					)
					,
					26
				)
				,
				_(
					_(
						t09_provided
						,
						_(
							t10_a
							,
							t11_haven
							,
							27
						)
						,
						37
					)
					,
					_(
						t12_for
						,
						_(
							_(
								t43_det
								,
								t13_people
								,
								44
							)
							,
							_(
								t14_who
								,
								_(
									t15_crossed
									,
									_(
										t16_the
										,
										t17_bridge
										,
										30
									)
									,
									31
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
					42
				)
				,
				41
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_plank
						,
						_(
							h04_which
							,
							_(
								_(
									h05_was
									,
									h06_long
									,
									23
								)
								,
								_(
									h07_and
									,
									_(
										h08_provided
										,
										_(
											h40_det
											,
											h09_haven
											,
											41
										)
										,
										21
									)
									,
									35
								)
								,
								39
							)
							,
							25
						)
						,
						17
					)
					,
					38
				)
				,
				_(
					h32_jutted_out
					,
					_(
						h13_from
						,
						_(
							h14_the
							,
							h15_tracks
							,
							28
						)
						,
						29
					)
					,
					33
				)
				,
				37
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(5,32));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

