package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestNumbers<ID> extends AbsPipelineTest<Integer> {
	
	@Test
	public final void number1() throws Exception {

		// create the vocabulary for the text;
		val john    = word("NP", "John",1);
		val loves  = word("V_2", "loves",2);
		val two    = word("NUMBER", "two",3);
		val some    = word("SOME", "some",4);
		val girls    = word("N", "girls",5);
		
		val tt = _(john,_(loves,_(two,girls,6),7),8);
		val th = _(john,_(loves,_(some,girls,6),7),8);

		assertProof(tt, th);
	}

	@Test
	public final void number2() throws Exception {

		// create the vocabulary for the text;
		val john    = word("NP", "John",1);
		val loves  = word("V_2", "loves",2);
		val two    = word("NUMBER", "two",3);
		val some    = word("SOME", "some",4);
		val girls    = word("N", "girls",5);
		
		val tt = _(_(two,girls,6),_(loves,john,7),8);
		val th = _(_(some,girls,6),_(loves,john,7),8);

		assertProof(tt, th);
	}
	
	@Test
	public final void number3() throws Exception {

		// create the vocabulary for the text;
		val john    = word("NP", "John",1);
		val loves  = word("V_2", "loves",2);
		val two    = word("NUMBER", "two",3);
		val some    = word("SOME", "some",4);
		val girls    = word("N", "girls",5);
		
		val tt = _(john,_(loves,_(some,girls,6),7),8);
		val th = _(john,_(loves,_(two,girls,6),7),8);

		assertNoProof(tt, th);
	}
	
	@Test
	public final void number4() throws Exception {

		// create the vocabulary for the text;
		val john    = word("NP", "John",1);
		val loves  = word("V_2", "loves",2);
		val two    = word("NUMBER", "two",3);
		val some    = word("SOME", "some",4);
		val girls    = word("N", "girls",5);
		
		val tt = _(_(some,girls,6),_(loves,john,7),8);
		val th = _(_(two,girls,6),_(loves,john,7),8);

		assertNoProof(tt, th);
	}
	
	@Test
	public final void number5() throws Exception {

		// create the vocabulary for the text;
		val loves  = word("V_2", "loves",1);
		val two1    = word("NUMBER", "two",2);
		val two2    = word("NUMBER", "two",3);
		val some1    = word("SOME", "some",4);
		val some2    = word("SOME", "some",5);
		val girls1    = word("N", "girls",6);
		val girls2    = word("N", "girls",7);
		
		val tt = _(_(two1,girls1,8),_(loves,_(two2,girls2,9),10),11);
		val th = _(_(some1,girls1,8),_(loves,_(some2,girls2,9),10),11);

		assertProof(tt, th);
	}
	
}
