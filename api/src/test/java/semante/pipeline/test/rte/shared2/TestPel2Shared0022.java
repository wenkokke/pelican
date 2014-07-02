package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0022 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0022() throws Exception {

			// create the vocabulary for the text;
			val t01_wal = word("$NPC_1$","Wal",1);
			val t02_mart = word("NP_D","Mart",2);
			val t42_app = word("WHO_A","APP",42);
			val t04_the = word("THE","the",4);
			val t05_american = word("MI","American",5);
			val t06_store = word("N","store",6);
			val t08_faces = word("V_2","faces",8);
			val t09_a = word("A","a",9);
			val t10_huge = word("MR","huge",10);
			val t11_sex = word("$NC_1$","sex",11);
			val t12_discrimination = word("$NC_1$","discrimination",12);
			val t13_suit = word("N","suit",13);
			val t15_following = word("GER_2","following",15);
			val t16_another = word("A","another",16);
			val t17_great = word("MR","great",17);
			val t18_supermarket = word("$NC_1$","supermarket",18);
			val t19_chain = word("N","chain",19);

			// create the vocabulary for the hypothesis;
			val h01_wal = word("$NPC_1$","Wal",1);
			val h02_mart = word("NP_D","Mart",2);
			val h03_is = word("IS","is",3);
			val h04_american = word("MI","American",4);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_wal
						,
						t02_mart
						,
						21
					)
					,
					_(
						t42_app
						,
						_(
							t04_the
							,
							_(
								t05_american
								,
								t06_store
								,
								38
							)
							,
							39
						)
						,
						43
					)
					,
					40
				)
				,
				_(
					_(
						t08_faces
						,
						_(
							t09_a
							,
							_(
								t10_huge
								,
								_(
									t11_sex
									,
									_(
										t12_discrimination
										,
										t13_suit
										,
										30
									)
									,
									31
								)
								,
								32
							)
							,
							33
						)
						,
						28
					)
					,
					_(
						t15_following
						,
						_(
							t16_another
							,
							_(
								t17_great
								,
								_(
									t18_supermarket
									,
									t19_chain
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
						26
					)
					,
					41
				)
				,
				44
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_wal
					,
					h02_mart
					,
					5
				)
				,
				_(
					h03_is
					,
					h04_american
					,
					7
				)
				,
				8
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

