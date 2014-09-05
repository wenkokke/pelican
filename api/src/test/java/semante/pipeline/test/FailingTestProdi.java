package semante.pipeline.test;

import java.util.List;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

import com.google.common.collect.Lists;

public final class FailingTestProdi extends AbsPipelineTest<Integer> {

		@Test
		public final void testShared112() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The",1);
			val t01_head = word("N","head",2);
			val t02_of = word("P_R","of",3);
			val t03_the = word("THE","the",4);
			val t04_italian = word("MR","Italian",5);
			val t05_opposition = word("N","opposition",6);
			val t06_app = word("WHO_A","APP",7);
			val t07_romano = word("$NPC_1$","Romano",8);
			val t08_prodi = word("NP_D","Prodi",9);
			val t09_was = word("IS","was",10);
			val t10_the = word("THE","the",11);
			val t11_last = word("MR","last",12);
			val t12_president = word("N","president",13);
			val t13_of = word("P_R","of",14);
			val t14_the = word("THE","the",15);
			val t15_european = word("MR","European",16);
			val t16_commission = word("N","Commission",17);

			// create the vocabulary for the hypothesis;
			val h00_romano = word("$NPC_1$","Romano",1);
			val h01_prodi = word("NP_D","Prodi",2);
			val h02_is = word("IS","is",3);
			val h03_a = word("A","a",4);
			val h04_former = word("MR","former",5);
			val h05_president = word("N","president",6);
			val h06_of = word("P_R","of",7);
			val h07_the = word("THE","the",8);
			val h08_european = word("MR","European",9);
			val h09_commission = word("N","Commission",10);

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
									,18)
								,19)
							,20)
						,21)
					,22)
					,
					_(
						t06_app
						,
						_(
							t07_romano
							,
							t08_prodi
						,23)
					,24)
				,25)
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
										,26)
									,27)
								,28)
							,29)
						,30)
					,31)
				,32)
			,33)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_romano
					,
					h01_prodi
				,11)
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
										,12)
									,13)
								,14)
							,15)
						,16)
					,17)
				,18)
			,19)
			;

			
			// create the subsumption relations;
			List<Pair<Integer,Integer>> subs = Lists.newArrayList();
			subs.add(new IPair<Integer,Integer>(12,5));

			// test for a proof;
			assertProof(tt, th, subs);
		}
			
}
