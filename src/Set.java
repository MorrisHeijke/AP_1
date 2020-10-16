package Assignment1;

public class Set implements SetInterface {

	private static int MAX_IDENTIFIERS = 20;
	private int numberOfIdentifiers;
	private Identifier[] identifiers;

	public Set() {
		numberOfIdentifiers = 0;
		identifiers = new Identifier[MAX_IDENTIFIERS];
	}

	@Override
	public boolean add(Identifier identifier) {
		if (contains(identifier) || identifiers.length > MAX_IDENTIFIERS) {
			return false;
		}

		identifiers[numberOfIdentifiers] = identifier;
		numberOfIdentifiers++;

		return true;
	}

	@Override
	public boolean contains(Identifier identifier) {
		for (int i = 0; i < numberOfIdentifiers; i++) {
			if (identifiers[i].equals(identifier)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return numberOfIdentifiers == 0;
	}

	@Override
	public SetInterface difference(SetInterface set) {
		Set difference = new Set();

		for (int i = 0; i < numberOfIdentifiers; i++) {
			if (!set.contains(identifiers[i])) {
				difference.add(identifiers[i]);
			}
		}

		return difference;
	}

	@Override
	public SetInterface intersection(SetInterface set) {
		Set intersection = new Set();

		for (int i = 0; i < numberOfIdentifiers; i++) {
			if (set.contains(identifiers[i])) {
				intersection.add(identifiers[i]);
			}
		}

		return intersection;
	}

	@Override
	public SetInterface union(SetInterface set) {
		SetInterface union = set.copy();

		for (int i = 0; i < numberOfIdentifiers; i++) {
			union.add(identifiers[i]);
		}

		return union;
	}

	@Override
	public SetInterface symmetricDifference(SetInterface set) {
		return this.union(set).difference(this.intersection(set));
	}

	@Override
	public SetInterface copy() {
		Set copy = new Set();

		for (int i = 0; i < numberOfIdentifiers; i++) {
			copy.add(identifiers[i]);
		}

		return copy;
	}

	@Override
	public void init() {
		numberOfIdentifiers = 0;
	}

	@Override
	public boolean remove(Identifier identifier) {
		for (int i = 0; i < numberOfIdentifiers; i++) {
			if (identifiers[i].equals(identifier)) {
				identifiers[i] = identifiers[numberOfIdentifiers - 1];
				numberOfIdentifiers -= 1;
				return true;
			}
		}

		return false;
	}

	@Override
	public IdentifierInterface[] toArray() {
		Identifier[] identifiers = new Identifier[numberOfIdentifiers];
		for (int i = 0; i < numberOfIdentifiers; i++) {
			identifiers[i] = this.identifiers[i];
		}
		return identifiers;
	}

	@Override
	public int size() {
		return numberOfIdentifiers;
	}

	@Override
	public Identifier get() {
		return identifiers[numberOfIdentifiers - 1];
	}
}