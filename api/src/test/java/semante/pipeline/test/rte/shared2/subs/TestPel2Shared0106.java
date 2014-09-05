package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0106 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0106() throws Exception {

			// create the vocabulary for the text;
			val t38_det = word("EMPTYDET","DET",38);
			val t01_election = word("MR","Election",1);
			val t02_officials = word("N","officials",2);
			val t03_announced = word("FACT","announced",3);
			val t04_that = word("IGNORE","that",4);
			val t05_the = word("THE","the",5);
			val t06_austrian = word("MI","Austrian",6);
			val t33_movie_actor = word("N","movie_actor",33);
			val t40_app = word("WHO_A","APP",40);
			val t21_arnold_schwarzenegger = word("NP_D","Arnold_Schwarzenegger",21);
			val t13_was = word("IS","was",13);
			val t14_elected = word("V_2","elected",14);
			val t42_det = word("THE","DET",42);
			val t15_governor = word("N","Governor",15);
			val t16_of = word("P_R","of",16);
			val t17_california = word("NP_D","California",17);

			// create the vocabulary for the hypothesis;
			val h07_arnold_schwarzenegger = word("NP_D","Arnold_Schwarzenegger",7);
			val h03_became = word("V_2","became",3);
			val h04_a = word("A","a",4);
			val h05_governor = word("N","governor",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t38_det
					,
					_(
						t01_election
						,
						t02_officials
						,
						19
					)
					,
					39
				)
				,
				_(
					t03_announced
					,
					_(
						t04_that
						,
						_(
							_(
								_(
									t05_the
									,
									_(
										t06_austrian
										,
										t33_movie_actor
										,
										34
									)
									,
									35
								)
								,
								_(
									t40_app
									,
									t21_arnold_schwarzenegger
									,
									41
								)
								,
								36
							)
							,
							_(
								t13_was
								,
								_(
									t14_elected
									,
									_(
										t42_det
										,
										_(
											t15_governor
											,
											_(
												t16_of
												,
												t17_california
												,
												25
											)
											,
											43
										)
										,
										44
									)
									,
									27
								)
								,
								28
							)
							,
							29
						)
						,
						30
					)
					,
					31
				)
				,
				37
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h07_arnold_schwarzenegger
				,
				_(
					h03_became
					,
					_(
						h04_a
						,
						h05_governor
						,
						8
					)
					,
					9
				)
				,
				11
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(14,3));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
