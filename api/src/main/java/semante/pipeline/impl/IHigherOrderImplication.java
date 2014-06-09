package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.DeBruijn;
import lambdacalc.STL;
import lambdacalc.Type;
import lambdacalc.Types;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.pipeline.HigherOrderImplication;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public class IHigherOrderImplication implements HigherOrderImplication {

	STL stl;
	
	@Override
	public final DeBruijn higherOrderImplication(
		final DeBruijn text, final DeBruijn hypo, final Iterable<DeBruijn> ctxt) throws HOIException {
		
		// obtain pointer to builder.
		val bld = stl.getDeBruijnBuilder();
		
		// obtain types.
		val textType = stl.typeOf(text);
		val hypoType = stl.typeOf(hypo);
		
		// check if types match.
		if (!textType.equals(hypoType))
			throw new HOIException(
					"Cannot construct higher-order implication from types %s and %s: they do not match.",
					stl.format(textType), stl.format(hypoType));
		
		// check if types are boolean.
		if (!textType.accept(new CheckIfBoolean()))
			throw new HOIException(
					"Cannot construct higher-order implication with type %s: it isn't boolean.",
					stl.format(textType));
		
		// compute the argument types.
		val argTypes = textType.accept(new GetArgumentTypes()).build();
		int numQuant = 0;
		val context  = ImmutableList.<DeBruijn> builder().add(text).add(hypo).addAll(ctxt).build();
		
		Iterable<DeBruijn> mutableText = Lists.newArrayList(text);
		Iterable<DeBruijn> mutableHypo = Lists.newArrayList(hypo);
		
		for (val argType: Lists.reverse(argTypes)) {
			
			// special case: generate a first-order quantifier.
			if (argType.equals(Types.E)) {
				val addVar  = new AddArg(bld.variable(numQuant,Types.E));
				mutableText = Iterables.transform(mutableText, addVar);
				mutableHypo = Iterables.transform(mutableHypo, addVar);
				numQuant += 1;
			}
			// default: just iterate over the closed domains.
			else {
				val domain = ImmutableSet.copyOf(stl.domainOf(argType, context));
				val addArg = new Function<DeBruijn,Iterable<DeBruijn>>() {

					@Override
					public final Iterable<DeBruijn> apply(DeBruijn fun) {
						val builder = ImmutableList.<DeBruijn> builder();
						for (val arg: domain) {
							builder.add(bld.application(fun,arg));
						}
						return builder.build();
					}
					
				};
				mutableText = Iterables.concat(Iterables.transform(mutableText,addArg));
				mutableHypo = Iterables.concat(Iterables.transform(mutableHypo,addArg));
			}
		}
		
		// convert to implications.
		val size = Iterables.size(mutableText);
		if (size > 0) {
			
			val mutableImpl = ImmutableList.<DeBruijn> builder();
			val arrayText = Iterables.toArray(mutableText, DeBruijn.class);
			val arrayHypo = Iterables.toArray(mutableHypo, DeBruijn.class);
			for (int i = 0; i < arrayText.length; i++) {
				mutableImpl.add(
					bld.application(bld.constant("IMPLIES",Types.TTT), arrayText[i], arrayHypo[i]));
			}
		
			val arrayImpl = Iterables.toArray(mutableImpl.build(), DeBruijn.class);
		
			// flatten with conjunction.
			DeBruijn joinImpl = arrayImpl[0];
			for (int i = 1; i < arrayImpl.length; i++) {
				joinImpl = bld.application(bld.constant("AND",Types.TTT), joinImpl, arrayImpl[i]);
			}

			// wrap quantifiers around.
			for (; numQuant > 0; numQuant--) {
				joinImpl = bld.application(bld.constant("FORALL",Types.ET_T), bld.abstraction(Types.E, joinImpl));
			}
			return joinImpl;
		}
		else {
			return bld.constant("TRUE", Types.T);
		}
	}
	
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	private final class AddArg implements Function<DeBruijn,DeBruijn> {

		DeBruijn arg;
		
		@Override
		public final DeBruijn apply(DeBruijn fun) {
			return stl.getDeBruijnBuilder().application(fun,arg);
		}
		
	}
	
	private final class CheckIfBoolean implements Type.Visitor<Boolean> {

		@Override
		public final Boolean constant(String arg0) {
			return arg0.equalsIgnoreCase("t");
		}

		@Override
		public final Boolean function(Type arg0, Type arg1) {
			return arg1.accept(this);
		}
	}
	
	private final class GetArgumentTypes implements Type.Visitor<ImmutableList.Builder<Type>> {

		@Override
		public final ImmutableList.Builder<Type> constant(String arg0) {
			return ImmutableList.<Type> builder();
		}

		@Override
		public final ImmutableList.Builder<Type> function(Type arg0, Type arg1) {
			return arg1.accept(this).add(arg0);
		}
		
	}
	
}
