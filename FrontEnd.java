import java.io.FileNotFoundException;
import java.util.Scanner;

public class FrontEnd {

	private static Scanner in = new Scanner(System.in);
	private static int choice = 0;
	private static BackEndHash library = new BackEndHash();

	public static void main(String[] args) throws FileNotFoundException {
		DataWrangler.readInputFile(library, "Book_data.csv");

		while (choice < 1 && choice > 4) {
			System.out.println("Welcome to the digital library.");
			System.out.println("What would you like to do?");
			System.out.println("1. Get Book Info");
			System.out.println("2. Add Book to Library");
			System.out.println("3. Remove Book from Library");
			System.out.println("4. Quit");

			try {
				choice = in.nextInt();
				switch (choice) {
				case 1:
					getBookInfo();
					break;
				case 2:
					addBook();
					break;
				case 3:
					removeBook();
					break;
				case 4:
					System.out.println("Thank you for using the digital library!");
					System.exit(0);
				default:
					System.out.println("[INVALID INPUT]");
				}
			} catch (Exception e) {
				System.out.println("[INVALID INPUT]");
			}
		}

	}

	public static void getBookInfo() {
		choice = 0;
		while (choice < 1 && choice > 5) {
			try {
				System.out.println("Enter book ISBN: ");
				String isbn = in.nextLine();
				Book book = library.get(isbn);

				System.out.println("Please select the information about this book you would like.");
				System.out.println("1. Title");
				System.out.println("2. Author");
				System.out.println("3. Publisher");
				System.out.println("4. Publication Year");
				System.out.println("5. MLA Citation");

				choice = in.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Title: " + book.getTitle());
					break;
				case 2:
					System.out.println("Author: " + book.getAuthor());
					break;
				case 3:
					System.out.println("Publisher: " + book.getPublisher());
					break;
				case 4:
					System.out.println("Publication Year: " + book.getPublicationYear());
					System.exit(0);
				case 5:
					System.out.println("MLA Citation: " + book.getCitation());
					System.exit(0);
				default:
					System.out.println("[INVALID INPUT]");
				}
			} catch (Exception e) {
				System.out.println("[INVALID INPUT]");
			}
		}
	}

	public static void addBook() {
		try {
			System.out.println("Enter title");
			String title = in.nextLine();
			System.out.println("Enter author");
			String author = in.nextLine();
			System.out.println("Enter publisher");
			String publisher = in.nextLine();
			System.out.println("Enter publication year");
			int publication_year = in.nextInt();
			System.out.println("Enter ISBN");
			String isbn = in.nextLine();
			
			Book added = new Book(title, author, publisher, publication_year, isbn);
			library.add(added);
			System.out.println(added.getTitle() + " (ISBN : " + added.getISBN() + ") has been added.");
		} catch (Exception e) {
			System.out.println("[INVALID INPUT]");
		}
	}

	public static void removeBook() {
		try {
			String isbn = in.nextLine();
			Book removed = library.remove(isbn);
			System.out.println(removed.getTitle() + " (ISBN : " + removed.getISBN() + ") has been removed.");
		} catch (Exception e) {
			System.out.println("[INVALID INPUT]");
		}

	}

}
