package Assignment1;

public class Identifier implements IdentifierInterface {

	private StringBuffer sb;

	public Identifier(char c) {
		if (Character.isAlphabetic(c)) {
			sb = new StringBuffer();
			sb.append(c);
		}
	}

	public Identifier(Identifier identifier) {
		StringBuffer sb = new StringBuffer();
		sb.append(identifier.getString());
	}

	@Override
	public boolean add(char c) {
		if (Character.isDigit(c) || Character.isAlphabetic(c)) {
			sb.append(c);
			return true;
		}

		return false;
	}

	@Override
	public String getString() {
		return sb.toString();
	}

	@Override
	public int getLength() {
		return sb.length();
	}

	@Override
	public boolean equals(Identifier identifier) {
		return getString().equals(identifier.getString());

	}

	@Override
	public void init(char c) {
		// TODO Auto-generated method stub
		
	}

}
