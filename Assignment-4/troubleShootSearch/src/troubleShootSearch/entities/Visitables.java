package troubleShootSearch.entities;

import troubleShootSearch.visitor.Visitor;

public interface Visitables {
	public void accept(Visitor visitor);
}
