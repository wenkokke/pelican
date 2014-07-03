package semante.pipeline.test;

import static semante.pipeline.ResultType.Exception;
import static semante.pipeline.ResultType.Proof;
import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestPlurals extends AbsPipelineTest {

	@Test
	public final void TestCase01() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_and = word("AND","and",2);
		val t02_mary = word("NP_D","Mary",3);
		val t03_ate = word("V_2","ate",4);
		val t04_a = word("A","a",5);
		val t05_sandwich = word("N","sandwich",6);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D","John",1);
		val h01_ate = word("V_2","ate",2);
		val h02_a = word("A","a",3);
		val h03_sandwich = word("N","sandwich",4);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				t00_john
				,
				_(
					t01_and
					,
					t02_mary
				,7)
			,8)
			,
			_(
				t03_ate
				,
				_(
					t04_a
					,
					t05_sandwich
				,9)
			,10)
		,11)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_john
			,
			_(
				h01_ate
				,
				_(
					h02_a
					,
					h03_sandwich
				,5)
			,6)
		,7)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void TestCase02() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_and = word("AND","and",2);
		val t02_mary = word("NP_D","Mary",3);
		val t03_ate = word("V_2","ate",4);
		val t04_a = word("A","a",5);
		val t05_sandwich = word("N","sandwich",6);

		// create the vocabulary for the hypothesis;
		val h00_mary = word("NP_D","Mary",1);
		val h01_ate = word("V_2","ate",2);
		val h02_a = word("A","a",3);
		val h03_sandwich = word("N","sandwich",4);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				t00_john
				,
				_(
					t01_and
					,
					t02_mary
				,7)
			,8)
			,
			_(
				t03_ate
				,
				_(
					t04_a
					,
					t05_sandwich
				,9)
			,10)
		,11)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_mary
			,
			_(
				h01_ate
				,
				_(
					h02_a
					,
					h03_sandwich
				,5)
			,6)
		,7)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void TestCase03() throws Exception {

		// create the vocabulary for the text;
		val t00_bill = word("NP_D","Bill",1);
		val t01_kissed = word("V_2","kissed",2);
		val t02_john = word("NP_D","John",3);
		val t03_and = word("AND","and",4);
		val t04_mary = word("NP_D","Mary",5);

		// create the vocabulary for the hypothesis;
		val h00_bill = word("NP_D","Bill",1);
		val h01_kissed = word("V_2","kissed",2);
		val h02_john = word("NP_D","John",3);

		// create the tree structure for the text;
		val tt =
		_(
			t00_bill
			,
			_(
				t01_kissed
				,
				_(
					t02_john
					,
					_(
						t03_and
						,
						t04_mary
					,6)
				,7)
			,8)
		,9)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_bill
			,
			_(
				h01_kissed
				,
				h02_john
			,4)
		,5)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void TestCase04() throws Exception {

		// create the vocabulary for the text;
		val t00_bill = word("NP_D","Bill",1);
		val t01_kissed = word("V_2","kissed",2);
		val t02_john = word("NP_D","John",3);
		val t03_and = word("AND","and",4);
		val t04_mary = word("NP_D","Mary",5);

		// create the vocabulary for the hypothesis;
		val h00_bill = word("NP_D","Bill",1);
		val h01_kissed = word("V_2","kissed",2);
		val h02_mary = word("NP_D","Mary",3);

		// create the tree structure for the text;
		val tt =
		_(
			t00_bill
			,
			_(
				t01_kissed
				,
				_(
					t02_john
					,
					_(
						t03_and
						,
						t04_mary
					,6)
				,7)
			,8)
		,9)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_bill
			,
			_(
				h01_kissed
				,
				h02_mary
			,4)
		,5)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}
	

	@Test
	public final void TestCase05() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_walked = word("V_1","walked",2);
		val t02_in = word("P_R","in",3);
		val t03_the = word("THE","the",4);
		val t04_park = word("N","park",5);
		val t05_and = word("AND","and",6);
		val t06_the = word("THE","the",7);
		val t07_garden = word("N","garden",8);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D","John",1);
		val h01_walked = word("V_1","walked",2);
		val h02_in = word("P_R","in",3);
		val h03_the = word("THE","the",4);
		val h04_park = word("N","park",5);

		// create the tree structure for the text;
		val tt =
		_(
			t00_john
			,
			_(
				t01_walked
				,
				_(
					t02_in
					,
					_(
						_(
							t03_the
							,
							t04_park
						,9)
						,
						_(
							t05_and
							,
							_(
								t06_the
								,
								t07_garden
							,10)
						,11)
					,12)
				,13)
			,14)
		,15)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_john
			,
			_(
				h01_walked
				,
				_(
					h02_in
					,
					_(
						h03_the
						,
						h04_park
					,6)
				,7)
			,8)
		,9)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}
	
	
	@Test
	public final void TestCase06() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_walked = word("V_1","walked",2);
		val t02_in = word("P_R","in",3);
		val t03_the = word("THE","the",4);
		val t04_park = word("N","park",5);
		val t05_and = word("AND","and",6);
		val t06_the = word("THE","the",7);
		val t07_garden = word("N","garden",8);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D","John",1);
		val h01_walked = word("V_1","walked",2);
		val h02_in = word("P_R","in",3);
		val h03_the = word("THE","the",4);
		val h04_garden = word("N","garden",5);

		// create the tree structure for the text;
		val tt =
		_(
			t00_john
			,
			_(
				t01_walked
				,
				_(
					t02_in
					,
					_(
						_(
							t03_the
							,
							t04_park
						,9)
						,
						_(
							t05_and
							,
							_(
								t06_the
								,
								t07_garden
							,10)
						,11)
					,12)
				,13)
			,14)
		,15)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_john
			,
			_(
				h01_walked
				,
				_(
					h02_in
					,
					_(
						h03_the
						,
						h04_garden
					,6)
				,7)
			,8)
		,9)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}	
	
	@Test
	public final void TestCase07() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_has = word("V_AUX","has",2);
		val t02_been = word("IS","been",3);
		val t03_driving = word("V_1","driving",4);
		val t04_through = word("P_R","through",5);
		val t05_germany = word("NP_D","Germany",6);
		val t06_and = word("AND","and",7);
		val t07_france = word("NP_D","France",8);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D","John",1);
		val h01_has = word("V_AUX","has",2);
		val h02_been = word("IS","been",3);
		val h03_driving = word("V_1","driving",4);
		val h04_through = word("P_R","through",5);
		val h05_germany = word("NP_D","Germany",6);

		// create the tree structure for the text;
		val tt =
		_(
			t00_john
			,
			_(
				t01_has
				,
				_(
					t02_been
					,
					_(
						t03_driving
						,
						_(
							t04_through
							,
							_(
								t05_germany
								,
								_(
									t06_and
									,
									t07_france
								,9)
							,10)
						,11)
					,12)
				,13)
			,14)
		,15)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_john
			,
			_(
				h01_has
				,
				_(
					h02_been
					,
					_(
						h03_driving
						,
						_(
							h04_through
							,
							h05_germany
						,7)
					,8)
				,9)
			,10)
		,11)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}
	
	@Test
	public final void TestCase08() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_has = word("V_AUX","has",2);
		val t02_been = word("IS","been",3);
		val t03_driving = word("V_1","driving",4);
		val t04_through = word("P_R","through",5);
		val t05_germany = word("NP_D","Germany",6);
		val t06_and = word("AND","and",7);
		val t07_france = word("NP_D","France",8);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D","John",1);
		val h01_has = word("V_AUX","has",2);
		val h02_been = word("IS","been",3);
		val h03_driving = word("V_1","driving",4);
		val h04_through = word("P_R","through",5);
		val h05_france = word("NP_D","France",6);

		// create the tree structure for the text;
		val tt =
		_(
			t00_john
			,
			_(
				t01_has
				,
				_(
					t02_been
					,
					_(
						t03_driving
						,
						_(
							t04_through
							,
							_(
								t05_germany
								,
								_(
									t06_and
									,
									t07_france
								,9)
							,10)
						,11)
					,12)
				,13)
			,14)
		,15)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_john
			,
			_(
				h01_has
				,
				_(
					h02_been
					,
					_(
						h03_driving
						,
						_(
							h04_through
							,
							h05_france
						,7)
					,8)
				,9)
			,10)
		,11)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}	
	
	@Test
	public final void TestCase09() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_and = word("AND","and",2);
		val t02_mary = word("NP_D","Mary",3);
		val t03_are = word("IS","are",4);
		val t04_the = word("THE","the",5);
		val t05_doctors = word("N","doctors",6);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D","John",1);
		val h01_and = word("AND","and",2);
		val h02_mary = word("NP_D","Mary",3);
		val h03_are = word("IS","are",4);
		val h04_the = word("THE","the",5);
		val h05_doctors = word("N","doctors",6);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				t00_john
				,
				_(
					t01_and
					,
					t02_mary
				,7)
			,8)
			,
			_(
				t03_are
				,
				_(
					t04_the
					,
					t05_doctors
				,9)
			,10)
		,11)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			_(
				h00_john
				,
				_(
					h01_and
					,
					h02_mary
				,7)
			,8)
			,
			_(
				h03_are
				,
				_(
					h04_the
					,
					h05_doctors
				,9)
			,10)
		,11)
		;

		// test for a proof;
		assertException(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Exception);
	}

	@Test
	public final void TestCase10() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_and = word("AND","and",2);
		val t02_mary = word("NP_D","Mary",3);
		val t03_s = word("GEN","s",4);
		val t04_friends = word("N","friends",5);
		val t05_are = word("IS","are",6);
		val t06_bill = word("NP_D","Bill",7);
		val t07_and = word("AND","and",8);
		val t08_sue = word("NP_D","Sue",9);

		// create the vocabulary for the hypothesis;
		val h00_sue = word("NP_D","Sue",1);
		val h01_is = word("IS","is",2);
		val h02_bill = word("NP_D","Bill",3);
		val h03_and = word("AND","and",4);
		val h04_sue = word("NP_D","Sue",5);
		val h05_is = word("IS","is",6);
		val h06_john = word("NP_D","John",7);
		
		// create the tree structure for the text;
		val tt =
		_(
			_(
				t00_john
				,
				_(
					t01_and
					,
					_(
						_(
							t02_mary
							,
							t03_s
						,10)
						,
						t04_friends
					,11)
				,12)
			,13)
			,
			_(
				t05_are
				,
				_(
					t06_bill
					,
					_(
						t07_and
						,
						t08_sue
					,14)
				,15)
			,16)
		,17)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			_(
				h00_sue
				,
				_(
					h01_is
					,
					h02_bill
				,8)
			,9)
			,
			_(
				h03_and
				,
				_(
					h04_sue
					,
					_(
						h05_is
						,
						h06_john
					,10)
				,11)
			,12)
		,13)
		;
		
		// test for a proof;
		assertException(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Exception);
	}	

	@Test
	public final void TestCase11() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_and = word("AND","and",2);
		val t02_mary = word("NP_D","Mary",3);
		val t03_are = word("IS","are",4);
		val t04_the = word("THE","the",5);
		val t05_researchers = word("N","researchers",6);
		val t06_from = word("P_R","from",7);
		val t07_pennsylvania = word("NP_D","Pennsylvania",8);
		val t08_who = word("WHO_A","who",9);
		val t09_received = word("V_2","received",10);
		val t10_the = word("THE","the",11);
		val t11_nobel = word("MR","Nobel",12);
		val t12_prize = word("N","prize",13);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D","John",1);
		val h01_and = word("AND","and",2);
		val h02_mary = word("NP_D","Mary",3);
		val h03_are = word("IS","are",4);
		val h04_the = word("THE","the",5);
		val h05_researchers = word("N","researchers",6);
		val h06_from = word("P_R","from",7);
		val h07_pennsylvania = word("NP_D","Pennsylvania",8);
		val h08_who = word("WHO_A","who",9);
		val h09_received = word("V_2","received",10);
		val h10_the = word("THE","the",11);
		val h11_nobel = word("MR","Nobel",12);
		val h12_prize = word("N","prize",13);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				t00_john
				,
				_(
					t01_and
					,
					t02_mary
				,14)
			,15)
			,
			_(
				t03_are
				,
				_(
					_(
						t04_the
						,
						_(
							t05_researchers
							,
							_(
								t06_from
								,
								t07_pennsylvania
							,16)
						,17)
					,18)
					,
					_(
						t08_who
						,
						_(
							t09_received
							,
							_(
								t10_the
								,
								_(
									t11_nobel
									,
									t12_prize
								,19)
							,20)
						,21)
					,22)
				,23)
			,24)
		,25)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			_(
				h00_john
				,
				_(
					h01_and
					,
					h02_mary
				,14)
			,15)
			,
			_(
				h03_are
				,
				_(
					_(
						h04_the
						,
						_(
							h05_researchers
							,
							_(
								h06_from
								,
								h07_pennsylvania
							,16)
						,17)
					,18)
					,
					_(
						h08_who
						,
						_(
							h09_received
							,
							_(
								h10_the
								,
								_(
									h11_nobel
									,
									h12_prize
								,19)
							,20)
						,21)
					,22)
				,23)
			,24)
		,25)
		;

		// test for a proof;
		assertException(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Exception);
	}

	@Test
	public final void TestCase12() throws Exception {

		// create the vocabulary for the text;
		val t00_the = word("THE","The",1);
		val t01_doctors = word("N","doctors",2);
		val t02_app = word("WHO_A","APP",3);
		val t03_john = word("NP_D","John",4);
		val t04_and = word("AND","and",5);
		val t05_mary = word("NP_D","Mary",6);
		val t06_received = word("V_2","received",7);
		val t07_the = word("THE","the",8);
		val t08_nobel = word("MR","Nobel",9);
		val t09_prize = word("N","prize",10);

		// create the vocabulary for the hypothesis;
		val h00_the = word("THE","The",1);
		val h01_doctors = word("N","doctors",2);
		val h02_app = word("WHO_A","APP",3);
		val h03_john = word("NP_D","John",4);
		val h04_and = word("AND","and",5);
		val h05_mary = word("NP_D","Mary",6);
		val h06_received = word("V_2","received",7);
		val h07_the = word("THE","the",8);
		val h08_nobel = word("MR","Nobel",9);
		val h09_prize = word("N","prize",10);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				_(
					t00_the
					,
					t01_doctors
				,11)
				,
				_(
					t02_app
					,
					_(
						t03_john
						,
						_(
							t04_and
							,
							t05_mary
						,12)
					,13)
				,14)
			,15)
			,
			_(
				t06_received
				,
				_(
					t07_the
					,
					_(
						t08_nobel
						,
						t09_prize
					,16)
				,17)
			,18)
		,19)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			_(
				_(
					h00_the
					,
					h01_doctors
				,11)
				,
				_(
					h02_app
					,
					_(
						h03_john
						,
						_(
							h04_and
							,
							h05_mary
						,12)
					,13)
				,14)
			,15)
			,
			_(
				h06_received
				,
				_(
					h07_the
					,
					_(
						h08_nobel
						,
						h09_prize
					,16)
				,17)
			,18)
		,19)
		;

		// test for a proof;
		assertException(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Exception);
	}
	
	@Test
	public final void TestCase13() throws Exception {

		// create the vocabulary for the text;
		val t00_andy = word("NP_D","Andy",1);
		val t01_and = word("AND","and",2);
		val t02_yoni = word("NP_D","Yoni",3);
		val t03_who = word("WHO_A","who",4);
		val t04_won = word("V_2","won",5);
		val t05_the = word("THE","the",6);
		val t06_australian = word("$NC_1$","Australian",7);
		val t07_open = word("N","Open",8);
		val t08_are = word("IS","are",9);
		val t09_successful = word("MR","successful",10);
		val t10_tennis = word("$NC_1$","tennis",11);
		val t11_players = word("N","players",12);

		// create the vocabulary for the hypothesis;
		val h00_andy = word("NP_D","Andy",1);
		val h01_and = word("AND","and",2);
		val h02_yoni = word("NP_D","Yoni",3);
		val h03_who = word("WHO_A","who",4);
		val h04_won = word("V_2","won",5);
		val h05_the = word("THE","the",6);
		val h06_australian = word("$NC_1$","Australian",7);
		val h07_open = word("N","Open",8);
		val h08_are = word("IS","are",9);
		val h09_successful = word("MR","successful",10);
		val h10_tennis = word("$NC_1$","tennis",11);
		val h11_players = word("N","players",12);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				_(
					t00_andy
					,
					_(
						t01_and
						,
						t02_yoni
					,13)
				,14)
				,
				_(
					t03_who
					,
					_(
						t04_won
						,
						_(
							t05_the
							,
							_(
								t06_australian
								,
								t07_open
							,15)
						,16)
					,17)
				,18)
			,19)
			,
			_(
				t08_are
				,
				_(
					t09_successful
					,
					_(
						t10_tennis
						,
						t11_players
					,20)
				,21)
			,22)
		,23)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			_(
				_(
					h00_andy
					,
					_(
						h01_and
						,
						h02_yoni
					,13)
				,14)
				,
				_(
					h03_who
					,
					_(
						h04_won
						,
						_(
							h05_the
							,
							_(
								h06_australian
								,
								h07_open
							,15)
						,16)
					,17)
				,18)
			,19)
			,
			_(
				h08_are
				,
				_(
					h09_successful
					,
					_(
						h10_tennis
						,
						h11_players
					,20)
				,21)
			,22)
		,23)
		;

		// test for a proof;
		assertException(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Exception);
	}
	
	@Test
	public final void TestCase14() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_and = word("AND","and",2);
		val t02_mary = word("NP_D","Mary",3);
		val t03_s = word("GEN","s",4);
		val t04_friends = word("N","friends",5);
		val t05_are = word("IS","are",6);
		val t06_bill = word("NP_D","Bill",7);
		val t07_and = word("AND","and",8);
		val t08_sue = word("NP_D","Sue",9);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D","John",1);
		val h01_and = word("AND","and",2);
		val h02_mary = word("NP_D","Mary",3);
		val h03_s = word("GEN","s",4);
		val h04_friends = word("N","friends",5);
		val h05_are = word("IS","are",6);
		val h06_bill = word("NP_D","Bill",7);
		val h07_and = word("AND","and",8);
		val h08_sue = word("NP_D","Sue",9);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				_(
					_(
						t00_john
						,
						_(
							t01_and
							,
							t02_mary
						,10)
					,11)
					,
					t03_s
				,12)
				,
				t04_friends
			,13)
			,
			_(
				t05_are
				,
				_(
					t06_bill
					,
					_(
						t07_and
						,
						t08_sue
					,14)
				,15)
			,16)
		,17)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			_(
				_(
					_(
						h00_john
						,
						_(
							h01_and
							,
							h02_mary
						,10)
					,11)
					,
					h03_s
				,12)
				,
				h04_friends
			,13)
			,
			_(
				h05_are
				,
				_(
					h06_bill
					,
					_(
						h07_and
						,
						h08_sue
					,14)
				,15)
			,16)
		,17)
		;

		// test for a proof;
		assertException(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Exception);
	}
	
	@Test
	public final void TestCase15() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_s = word("POSS","s",2);
		val t02_boyfriend = word("N","boyfriend",3);
		val t03_and = word("AND","and",4);
		val t04_mary = word("NP_D","Mary",5);
		val t05_s = word("POSS","s",6);
		val t06_girlfriend = word("N","girlfriend",7);
		val t07_are = word("IS","are",8);
		val t08_bill = word("NP_D","Bill",9);
		val t09_and = word("AND","and",10);
		val t10_sue = word("NP_D","Sue",11);

		// create the vocabulary for the hypothesis;
		val h00_bill = word("NP_D","Bill",1);
		val h01_is = word("IS","is",2);
		val h02_sue = word("NP_D","Sue",3);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				_(
					_(
						t00_john
						,
						t01_s
					,12)
					,
					t02_boyfriend
				,13)
				,
				_(
					t03_and
					,
					_(
						_(
							t04_mary
							,
							t05_s
						,14)
						,
						t06_girlfriend
					,15)
				,16)
			,17)
			,
			_(
				t07_are
				,
				_(
					t08_bill
					,
					_(
						t09_and
						,
						t10_sue
					,18)
				,19)
			,20)
		,21)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_bill
			,
			_(
				h01_is
				,
				h02_sue
			,4)
		,5)
		;

		// test for a proof;
		assertException(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Exception);
	}
	
	@Test
	public final void TestCase16() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_s = word("POSS","s",2);
		val t02_boyfriend = word("N","boyfriend",3);
		val t03_and = word("AND","and",4);
		val t04_mary = word("NP_D","Mary",5);
		val t05_s = word("POSS","s",6);
		val t06_girlfriend = word("N","girlfriend",7);
		val t07_app = word("WHO_A","APP",8);
		val t08_bill = word("NP_D","Bill",9);
		val t09_and = word("AND","and",10);
		val t10_sue = word("NP_D","Sue",11);
		val t11_are = word("IS","are",12);
		val t12_nice = word("MR","nice",13);

		// create the vocabulary for the hypothesis;
		val h00_bill = word("NP_D","Bill",1);
		val h01_is = word("IS","is",2);
		val h02_sue = word("NP_D","Sue",3);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				_(
					_(
						_(
							t00_john
							,
							t01_s
						,14)
						,
						t02_boyfriend
					,15)
					,
					_(
						t03_and
						,
						_(
							_(
								t04_mary
								,
								t05_s
							,16)
							,
							t06_girlfriend
						,17)
					,18)
				,19)
				,
				_(
					t07_app
					,
					_(
						_(
							t08_bill
							,
							t09_and
						,20)
						,
						t10_sue
					,21)
				,22)
			,23)
			,
			_(
				t11_are
				,
				t12_nice
			,24)
		,25)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_bill
			,
			_(
				h01_is
				,
				h02_sue
			,4)
		,5)
		;

		// test for a proof;
		assertException(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Exception);
	}
	
}
