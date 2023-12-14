/**
 * 
 */
package labb2;

/**
 * 
 */


import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@SuppressWarnings("serial")
public class MyArrayList<E> implements Serializable, Cloneable, Iterable<E>,
		Collection<E>, List<E>, RandomAccess { //Typparametern <E> gör så att man kan skapa en lista av vilka typer som helst. 
	private E[] tmp; //Data sparas internt i den generiska Arrayen tyen E, med variabel element. kapaciteten på den interna arrayen. 

	private int size; // Antalet element. Håller reda på antalet element som faktiskt finns i listan just nu. privat variabel denns aka inte ändras på. 

    	// ---------------------------------------------------------------

	public static void main(String[] args) {
		MyArrayList<String> strlist = new MyArrayList<String>();
		
		// testa metoder härifrån
		
	}

    	// ---------------------------------------------------------------
    
	public MyArrayList(int initialCapacity) { // denna gör så att den som skapar en instans av MyArrayList gör att användaren själv kan ange en initial kapacitet. 
		
		tmp = (E[]) new Object[initialCapacity];  // Hur mågna element arrayen kan innehålla.  skspar en referens till EMyArraylist initiala tillstånd med argumentet initialCapacity. dvs den initiala kapaciteten. När man skapar en ny instans så kommer den 
		// ... skapar en ny instans av MyArrayList så ska man ange storleken på arrayen. Detta gör den klassen. ett heltal (int)
		size = 0; // Elementen börjar på 0. Hur många element listan  faktisk innehåller i början. 
	}

	public MyArrayList() { //Denna gör att användaren inte själv behöver ange någon initial kapacitet. Här dock kommer det få plats med 15 element om användaren ej anger något. 
		/* ska implementeras */
		this(15);  // Återanvänder koden från den första konstruktorn och ger ett heltal 15. Initierar med 15 automatiskt, om använderen ej anger kapaciteten.  denna anropar den första konstruktorn, som accepterar ett heltal som argument. om någon skaper en instans av konstruktorn, utan att specificera den itnitala storleken ex new MyArrayList(), så kommer den alltid börja med 15 element.  
	}

	// -- 1 --

		@Override
		public int size() {// denna metod ska returnera antaleet element som just nu finns i arrayen. size är en counter

			/* ska implementeras */

		   return size; // returnerar storleken på listan. Eller MyArrayList 

		}



		@Override

		public boolean isEmpty() {// denna metod sla kontrollera om listan är tom eller inte

			if (size==0) {

				return true; // returnerar true om arrayen innehåller 0 element

			} else { // annars ska den returnera false

				return false; 

			} 


		}



		@Override

		public void clear() {// ta bort alla element från MyArrayList, eller ta bort alla referenser från listan.

			// loopa igenom alla element

			/* ska implementeras */

			for (int i = 0; i < size; i++) {

				tmp[i] = null; // Tar bort referenserna till tmp sätter element i till null. Vi tar inte bort antalet platser i arrayen utan bara att dessa platser pekar på null. 
	

			}

			size = 0; // Efter detta är antalet element 0. Dvs börjar om. Listan är tom. 

			

		}



		// -- 2 --

	/*

	 *" När arrayen inte

	räcker till byts den automatiskt ut mot en längre in i vilken allt tidigare lagrat

	kopieras så varje object fortfarande lagras på samma index. Metoden

	ensureCapacity kan för övrigt anropas för att säkerställa att den interna

	arrayen har en viss längd. "

	 */

		public void ensureCapacity(int minCapacity) { //denna metod kontrolerar så att den finns tillräckligt med kapacitet till att ta det antalet element som krävs.

			/* ska implementeras */

			if (minCapacity > tmp.length ) { //om storleken på arrayen inte räcker till så ska allt tidigare kopieras till en nt där varje objekt algras på samma index. 

				// skapa en ny array med större storlek

				E[] nytmp = (E[]) new Object[minCapacity]; // Ny array "störreArray" med storleken som behövs (minCapacity)


				// Kopiera alla element från den förgående på samma index

				System.arraycopy(tmp, 0, nytmp, 0, size); 

				tmp = nytmp; // gör att den nya interna array blir den gamla 

			}

		}


		public void trimToSize() { // Vi vill se till att arrayen inte är större än vad den aktuella storleken faktiskt är. Frigör sånt som är onödigt. 
			if (size < tmp.length) {// om det faktiskta antalet element är mindre än storleken på referensen till MyArrayList (nytmp), ska en ny array skapas och innehållet kopieras
				E[] nytmp = (E[]) new Object[size]; // Den nya arrayen ska bara vara lika stor som den faktiska storleken
				System.arraycopy(tmp, 0, nytmp, 0 , size); //kopierar innehållet igen. 
				tmp = nytmp; //interna arrayen(referensen) refererar nyu till nytmp, med den faktiska storleken. Uppdaterar interna arrayen
			
			}

		}

	 
		// -- 3 --
	
	@Override
	public void add(int index, E element) { //Denna fungerar så att man ska kunna lägga till element "E"  till något index arrayen(listan). Element är av generiska typen E. 
		/* ska implementeras */
		if(index > size || index < 0) { // Detta är rangen som måste vara: 0<index<size om man ska kunna appenda till. Första indexet börjar alltid på 0, det kan inte vara mindre än det.Index kan heller inte vara större än storleken. 
			// största indexet som finns just nu är size-1, att addera ett element till size innebär att man adderar element till höger efter det senaste elementet. (append)
			throw new IndexOutOfBoundsException(); //Exception så att programmet inte kraschar om det som ska adderas är längre än storleken på arrayen, eller att index är mindr eän 0. 
		}
		// När man lägger till ett element till arrayen så ska det också finnas plats. Dvs vi måste se till att det finns kapacitet i den interna arrayen tmp. Dvs öka med 1 när et nytt element läggs till.
		else {
		ensureCapacity(size + 1); // gör så det finns utrymme för ett nytt element.  Ökar storleken på arrayen med 1. 
		
	
		/*
		 * Måste se till att det finns plats för elementet. "Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices)." 
		 */ 
		
		for (int i = size; i>index; i--) {//loopar från slutet på size till indexet
			tmp[i] = tmp[i-1]; // Ex säg man har storlek5, på index 3 vill man sätta in något. Först: i = 5,, indexet på 4 flyttas till index 5. Sen index på 3 flyttas till 4. 
		}
		
		
		tmp[index] = element; // sägger det nya elementet på det specicierade indexplatsen. 
		size = size + 1;  //ökar storleken på arrayen med 1, eller conutern. Ser till att den har det faktiska antalet element i listan. Har man lagt till ett element så måste man också öka det med 1. 
		}
	
		
	}
	
	
	
	
	
	

	@Override
	public boolean add(E e) { //True eller false, Med att addera grejer till listan? Om elementet adderades korrekt ska den returnera true. //"Appends the specified element to the end of this list."
		ensureCapacity(size + 1); // Gör plats för nytt element
		tmp[size++]=e; 
		/* ska implementeras */
		return true; /* bara med för att Eclipse inte ska klaga */
	}

        // -- 4 --
    
	@Override
	public E get(int index) { // Hur ska man få tag på ett element i listan vid ett specifikt index?
		/* ska implementeras */
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(); //ExceptionError om indexet är utanför rangen. 
		}
		return tmp[index];  //returnera indexet i listan 
	
	}

	@Override
	public E set(int index, E element) { //Hur ska man göra så att man kan vid ett specifikt index lägga till något annat vid det indexet? 
		/* ska implementeras */
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(); //ExceptionError om indexet är utanför rangen. 
		} 
		E gamlatmp = tmp[index];
		tmp[index] = element; 
		return gamlatmp; 
		
	
	}

	// -- 5 --

	@Override
	public E remove(int index) { //Hur ska man ta bort något element vid ett specifikt index? 
		/* ska implementeras */
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(); //ExceptionError om indexet är utanför rangen. 
		}  
		// spara det specifika elementet vid ett visst index i en variabel, dvs den som ska raderas. Typ parametern är E
		E tabort = tmp[index]; //indexet i interna arraylistan oavsett dess typ (typparameter E) sparas i variabeln tabort. Dvs det som ska tas bort. 
		//Sedab shifta alla andra till vänster. och ta bort det indexet vid en viss position. 
		for (int i = index; i < size-1; i++) { //loopar igenom alla. Det sista elementet förflyttas, men kommer också att behållas. Detta måste senare tas bort, för annars blir det dtå exakt såna. 
			tmp[i] = tmp[i+1];
			
			 
		}
		tmp[size-1] = null; //Sätter det sista elementet i referenslistan till null, pga annars blir det dubbelt nästa sista indexet är samnma som det sista. 
		
		
		size = size - 1; //måste även minska countern som håller reda på antalet element med 1 pga man har tagit bort ett element
		
		
		return tabort; // Returnerar elementet som togs bort för man kanske vill veta vilket det är. 
	}
	
	
// ta bort element från fromIndex upp till toIndex. Alla alement som är efter toIndex ska förflyttas till vänster. 
	protected void removeRange(int fromIndex, int toIndex) { //Ta bort flera element. Vars index är mellan fromIndex<index<toIndex . Shiftar alla index till vänster. Tar bort element från listan antalet: toIndex-fromIndex
		// Om toIndex==fromindex har detta ingen effekt. 
		/* ska implementeras */
		// fromIndex: indexet av första elementet som ska tas bort
		// toIndex: indexxet av siste elementet som ska tas bort. 
		
		if (fromIndex < 0 || toIndex > size || toIndex < fromIndex) { 
			throw new IndexOutOfBoundsException(); //error om utanför rangen
		}
		// Använda System.arraycopy?
		// Shifta element till vänster av dom som ska tas bort. Ta bort alla element mellan fromIndex till toIndex (dock ej inklisive toIndex). Elementen efter toIndex ska flyttas ett steg till vänster. 
		/* Arraycopy:
		 * Src: Källarrayen dvs den interna arrayen tmp (referensen)
		 * srcPos: Startpositionen i källarrayen som är "toIndex" (första elementet jag vill behålla). Vill inte kopiera de element jag vill ta bort, utan dom efter. Dvs kopiera alla element efter toIndex
		 * destination: Destioantionsarrayen, vilket är internaarrayen. tmp
		 * destPos: Startpositionen av destinations datan . Vill kopiera alla element som börjar på toIndex till var fromIndex börjar. Kopiera alla till denna position. 
		 * längden: Antalet element som ska kopieras. Detta blir ju storleken på hela faktiska arrayen "size" subtraherat med rangen (toIndex-fromIndex). Dvs: 
		 */
		
		// Det som ska tas bort: 
		int taBort = (toIndex-fromIndex);
		// längden på allt som ska kopieras :
		int längden = (size-taBort);  //dessa är alla de element som ska förflyttas (Dvs de som ska finnas kvar)
		
		// [1,2,3,4,5,6,7] , ta removerange(2,5): dvs,3,4,5 kommer gå bort. Detta blir sedan efter arraycopy. [1,2,6,7] (detta är size) faktiska storleken. 
		// tmp är detta: [1,2,6,7,?,?,?], måste sätta de sista elementen till null. 
		
	
		System.arraycopy(tmp, toIndex, tmp, fromIndex, längden);//flyttar alla från o med toIndex, alla höger om den till positionen fromIndex, dvs till vänster. 
		
		//Sätt alla element som förflyttats till vänster efter borttagning av element, sätt dessa till null. Loopa igenom lista, börja på elementet efter det "riktiga elementet". Storleken innan-det som ska tas bort. 
		//Loopen ska sluta på storleken av hela listan innan storleken på listan ändrades. vilket är slutet (size)
		for (int i = längden; i < size; i++ ) {
			tmp[i] = null;  //sätter alla i slutet till null för skräpsamling. 
			
		}
		size = size - taBort; // uppdaterar den faktiska storleken med det som ska tas bort. 
		
		
		
		
	}

	// -- 6 --

	@Override
	public int indexOf(Object o) { //Hur ska vi kunna söka efter specifika element i listan?  Returnera index av den första förekomsten av indexet i listan, -1 om listan inte innehåller elementet.   
		/* ska implementeras */ // o är elementet som man söker efter, då ska den returnera det indexet. 
		for (int i = 0; i <size; i++) {
			if(tmp[i]==o) {
				return i; 
			} 
		}
		
		return -1; /* bara med för att Eclipse inte ska klaga */
	}

	@Override
	public boolean remove(Object o) { ///Ta bort det första angivna elementet fårn listan om det finns eller inte. Om det ej finns då händer inget. 
		/* ska implementeras */
		for (int i=0; i<size; i++) {
			if(o.equals(tmp[i])){
				remove(i);
				return true;
			}
		}
		return false; /* bara med för att Eclipse inte ska klaga */
	}
    
	@Override
	public boolean contains(Object o) { //Retuenrar sant om och endast om listan innehåller minst ett element 
		return indexOf(o) >= 0; //Returnerar True om listan innehåller 1 eller fler än ett objekt (o). 
		
		
		
	}

	// -- 7 --

	@Override
	public Object clone() { //Returnerar en ytlig kopia av arrayListan, dvs elementen kopieras inte. 
		/* ska implementeras */
		try {
		MyArrayList<E> clonadLista = (MyArrayList<E>) super.clone(); 
		clonadLista.tmp = tmp.clone(); 
		clonadLista.tmp = Arrays.copyOf(tmp, tmp.length); 
		return clonadLista; 
		
	}
		catch(CloneNotSupportedException e){
			throw new AssertionError(); 
			
		
		}
		}

	@Override
	public Object[] toArray() { //Vet ej?! Denna ska returnera en array som innehåller alla element i listan i rätt ordning (från första till sista elementet) 
		/* ska implementeras */
		/*
		 * Skapa först en ny array av object typen. 
		 */
		Object[] nyArray = new Object[size]; //skapar ett ny array av oejbkt typen med samma storlek som antalet faktiska element som finns dvs size
		System.arraycopy(tmp, 0, nyArray, 0, size); // //start posotione 0, från tmp arrayen till den nya. Genom att första elementet kommer från index 0 till 0 så blir det rätt ordning. Antalet element är size. 
		
	
		return nyArray; // Returnerar arrayen
	}

	// --- Rör ej nedanstående kod -----------------------------------

	public MyArrayList(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	private class InternalIterator implements Iterator {
		int current = 0;

		@Override
		public boolean hasNext() {
			return current < size();
		}

		@Override
		public Object next() {
			return get(current++);

		}

	}

	@Override
	public Iterator<E> iterator() {
		return new InternalIterator();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void forEach(Consumer<? super E> action) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Spliterator<E> spliterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeIf(Predicate<? super E> filter) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void replaceAll(UnaryOperator<E> operator) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void sort(Comparator<? super E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

}

