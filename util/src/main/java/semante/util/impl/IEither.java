package semante.util.impl;

import semante.util.Either;
import lombok.experimental.Value;

public final class IEither {
	
	public static final <A,B> Either<A,B> left(final A a) {
		return new Left<A,B>(a);
	}
	
	public static final <A,B> Either<A,B> right(final B b) {
		return new Right<A,B>(b);
	}

	@Value
	private static final class Left<A,B> implements Either<A,B> {
		
		A a;

		@Override
		public final <X> X accept(final Visitor<A, B, X> v) {
			return v.left(a);
		}

		@Override
		public final boolean isLeft() {
			return true;
		}

		@Override
		public final boolean isRight() {
			return false;
		}

		@Override
		public final A getLeft() {
			return a;
		}

		@Override
		public final B getRight() {
			throw new UnsupportedOperationException("Left#getRight()");
		}
	}
	
	@Value
	private static final class Right<A,B> implements Either<A,B> {
		
		B b;

		@Override
		public final <X> X accept(final Visitor<A, B, X> v) {
			return v.right(b);
		}

		@Override
		public final boolean isLeft() {
			return false;
		}

		@Override
		public final boolean isRight() {
			return true;
		}

		@Override
		public final A getLeft() {
			throw new UnsupportedOperationException("Right#getLeft()");
		}

		@Override
		public final B getRight() {
			return b;
		}
	}
	
	private IEither() {}
}
