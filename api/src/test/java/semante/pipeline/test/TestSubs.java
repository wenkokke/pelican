package semante.pipeline.test;

import static semante.pipeline.impl.IPair.pair;
import lombok.val;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import semante.pipeline.AbsPipelineTest;
import semante.pipeline.ResultType;

public class TestSubs extends AbsPipelineTest {
	
	@Test
	public final void example1() throws Exception {
		val every  = word("EVERY","every");
		val animal = word("N","animal");
		val dog    = word("N","dog");
		val ran    = word("V_1","ran");
		
		val text   = _(_(every,animal),ran);
		val hypo   = _(_(every,dog),ran);
		val subs   = ImmutableList.of(pair(dog, animal));
		
		assertProof(text, hypo, subs);
		testTestCaseCreator(text, hypo, ResultType.Proof, subs);
	}

}
