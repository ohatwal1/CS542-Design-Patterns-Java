package troubleShootSearch.visitor;

import troubleShootSearch.entities.MyArrayList;
import troubleShootSearch.entities.MyTree;
import troubleShootSearch.entities.Synonyms;

public interface Visitor {

	public void visit(MyArrayList arrayList);

	public void visit(MyTree tree);

	public void visit(Synonyms synonyms);
}
