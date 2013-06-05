package semante.stl.type;

import semante.stl.Type;
import semante.stl.Type.Identity;
import semante.stl.TypeError;

public final class Util {
	public static final Identity GetResultType = new Identity() {

		@Override
		public Type constant(String name) {
			throw new TypeError("GetResultType: constant type has no result");
		}

		@Override
		public Type function(Type a, Type b) {
			return b;
		}

	};
}
