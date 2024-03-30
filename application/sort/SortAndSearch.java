package application.sort;

import application.mainWindow.table.Table;
import application.products.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SortAndSearch extends Table {

	private boolean sortiereAufsteigend = true;
	

	// Bubble-sort
	public void sortMenge(ObservableList<Products> products) {

		ObservableList<Products> note = FXCollections.observableArrayList();
		sortiereAufsteigend = !sortiereAufsteigend;
		int s = sortiereAufsteigend ? 0 : 1;

		for (int i = products.size()-1; i >= 1; i--) {

			for (int j = 1; j <= i; j++) {

				if (products.get(j-1 +s).getMenge() > products.get(j - s).getMenge()) {

					note.add(products.get(j-1));
					products.set(j-1, products.get(j));
					products.set(j, note.get(0));
					note.clear();
				}
			}
		}
	}

	
	
	
	
	
	
	
	
	
	// Selection-Sort
	public void sortID(ObservableList<Products> products) {

		sortiereAufsteigend = !sortiereAufsteigend;
		ObservableList<Products> note = FXCollections.observableArrayList();
		int i, j, minpos;
		
		for (i = 0; i < products.size() - 1; i++) {

			minpos = i;

			for (j = (i + 1); j < products.size(); j++) {

				if (sortiereAufsteigend) {

					if (products.get(j).getId() < products.get(minpos).getId()) {

						minpos = j;
					}
					
				} else {
					
					if (products.get(j).getId() > products.get(minpos).getId()) {

						minpos = j;
					}
				}
			}
			note.add(products.get(minpos));
			products.set(minpos, products.get(i));
			products.set(i, note.get(0));
			note.removeAll(products);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	// Bubble-Sort
	public void sortkat(ObservableList<Products> products) {

		ObservableList<Products> buffer = FXCollections.observableArrayList();
		sortiereAufsteigend = !sortiereAufsteigend;
		int s = sortiereAufsteigend ? 0 : 1;

		for (int j = 0; j < products.size(); j++) {

			for (int i = 0; i < products.size() - 1; i++) {

				if (products.get(i + s).getKategorie().compareTo(products.get(i + 1 - s).getKategorie()) > 0) {

					buffer.add(products.get(i));
					products.set(i, products.get(i + 1));
					products.set(i + 1, buffer.get(0));
					buffer.removeAll(products);
				}
			}
		}
	}

	
	
	
	










	
	// Merge-Sort
	public void mergeSort(ObservableList<Products> products) {

		sortiereAufsteigend = !sortiereAufsteigend;
		sortPreisAbsteigend(products);
	}

	public ObservableList<Products> sortPreisAbsteigend(ObservableList<Products> products) {

		ObservableList<Products> links = FXCollections.observableArrayList();
		ObservableList<Products> rechts = FXCollections.observableArrayList();

		if (products.size() > 1 && !sortiereAufsteigend) {

			for (int i = 0; i < products.size() / 2; i++) {

				links.add(products.get(i));				
			}

			for (int i = (int) Math.ceil(products.size() / 2); i < (products.size()); i++) {

				rechts.add(products.get(i));				
			}

			links = sortPreisAbsteigend(links);
			rechts = sortPreisAbsteigend(rechts);

			merge(links, rechts, products);

			return products;

		} else {

			return products;

		}
	}
	
	

	private void merge(ObservableList<Products> links, ObservableList<Products> rechts, ObservableList<Products> products) {

		ObservableList<Products> neueListe;

		int indexLinks = 0, indexRechts = 0, ganzerIndex = 0, restIndex = 0;

		while (indexLinks < links.size() && indexRechts < rechts.size()) {

			if (links.get(indexLinks).getPreis() > rechts.get(indexRechts).getPreis()) {

				products.set(ganzerIndex, links.get(indexLinks));
				indexLinks++;

			} else {

				products.set(ganzerIndex, rechts.get(indexRechts));
				indexRechts++;
			}
			
			ganzerIndex++;
		}

		if (indexLinks >= links.size()) {

			neueListe = rechts;
			restIndex = indexRechts;

		} else {

			neueListe = links;
			restIndex = indexLinks;
		}

		for (int i = restIndex; i < neueListe.size(); i++) {

			products.set(ganzerIndex, neueListe.get(i));
			ganzerIndex++;
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	// Quick-Sort
	public void sortPreis(ObservableList<Products> products) {

		quickSort(products, 0, products.size() - 1);
	}

	private void quickSort(ObservableList<Products> products, int l, int r) {

		if (l < r) {

			int i = l - 1;
			int j = r;
			int pivot = r;

			while ((i < j) && !sortiereAufsteigend) {

				do {
					i++;
				} while (products.get(i).getPreis() < products.get(pivot).getPreis());

				do {
					j--;
				} while (j > l && products.get(j).getPreis() > products.get(pivot).getPreis());

				if (i >= j) {

					partition(products, l, i, r);

				} else {

					ObservableList<Products> note = FXCollections.observableArrayList();
					note.add(products.get(i));
					products.set(i, products.get(j));
					products.set(j, note.get(0));
					note.clear();
				}
			}
		}
	}
	
	private void partition(ObservableList<Products> products, int l, int i, int r) {

		ObservableList<Products> note = FXCollections.observableArrayList();
		note.add(products.get(i));
		products.set(i, products.get(r));
		products.set(r, note.get(0));
		note.clear();

		quickSort(products, l, i - 1);
		quickSort(products, i + 1, r);
	}

	
	
	
	
	
	
	
	
	
	
	
	// Insertion-Sort
	public void sortName(ObservableList<Products> products) {

		sortiereAufsteigend = !sortiereAufsteigend;
		ObservableList<Products> note = FXCollections.observableArrayList();
		int j;

		for (int i = 1; i < products.size(); i++) {

			note.add(products.get(i));
			j = i - 1;

			if (sortiereAufsteigend) {

				while ((j >= 0) && (products.get(j).getName().toLowerCase()
						.compareTo(note.get(0).getName().toLowerCase()) > 0)) {

					products.set(j + 1, products.get(j));
					j--;
				}

			} else {
				
				while ((j >= 0) && (products.get(j).getName().toLowerCase()
						.compareTo(note.get(0).getName().toLowerCase()) < 0)) {

					products.set(j + 1, products.get(j));
					j--;
				}
			}
			
			products.set(j + 1, note.get(0));
			note.clear();
		}
	}

	
	
	
	
	
	
		
	
	
	//Sequentielle Suche
	
	public void suche(ObservableList<Products> products, String suche) {
		
		ObservableList<Products> s = FXCollections.observableArrayList();

		for(int i = 0; i < speicher.size(); i++) {
			
			if(speicher.get(i).getName().toLowerCase().contains(suche.toLowerCase())) {
								
				s.add(speicher.get(i));			
				
			}else if(String.valueOf(speicher.get(i).getId()).toLowerCase().contains(suche.toLowerCase())) {
				
				s.add(speicher.get(i));		
				
			}else if(String.valueOf(speicher.get(i).getPreis()).toLowerCase().contains(suche.toLowerCase())) {
				
				s.add(speicher.get(i));		
				
			}else if(String.valueOf(speicher.get(i).getMenge()).toLowerCase().contains(suche.toLowerCase())) {
				
				s.add(speicher.get(i));	
				
			}else if(String.valueOf(speicher.get(i).getKategorie()).toLowerCase().contains(suche.toLowerCase())) {
				
				s.add(speicher.get(i));				
			}
		}		
		
		products.clear();
		products.addAll(s);
	}
}
