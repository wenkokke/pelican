package semante.stl.impl.type;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;

import org.codehaus.jparsec.OperatorTable;
import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Terminals;
import org.codehaus.jparsec.functors.Binary;

import semante.stl.Type;
import semante.stl.Type.Identity;
import semante.stl.impl.IType;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
public final class IParser implements semante.stl.type.Parser {
	
	@Override
	public final Type parse(final String input) {
		return TypeParser.parse(input);
	}

	static Identity           TypeBuilder   = new IType.IIdentity();
	static Parser<Type> TypeParser    = build();
	static Terminals          TypeTerminals = Terminals.operators("e","t","(",")");
	
	private static final Parser<Void> getToken(String... names) {
		return TypeTerminals.token(names).skipTimes(1);
	}
	
	private static final Parser<Type> build() {
		val ref    = Parser.<Type> newReference();
		val typeE  = getToken("e").retn((Type) IType.E);
		val typeT  = getToken("t").retn((Type) IType.T);
		val atomic = Parsers.or(typeE,typeT,ref.lazy().between(getToken("("), getToken(")"))); 
		val parser = new OperatorTable<Type>()
				.infixr(Parsers.always().retn(new Binary<Type>() {
					@Override
					public final Type map(Type left, Type right) {
						return TypeBuilder.function(left, right);
					}
				}),10).build(atomic);
		ref.set(parser);
		val ignored = Parsers.<Void> always();
		return parser.from(TypeTerminals.tokenizer(),ignored);
	}
}
