import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FrontEnd {

	private static Scanner in = new Scanner(System.in);
	private static int choice = 0;
	private static BackEndHash library = new BackEndHash();

	public static void main(String[] args) throws FileNotFoundException, InputMismatchException {
		DataWrangler.readInputFile(library, "Book_data.csv");

		while (choice < 1 || choice > 4) {
			choice = 0;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Welcome to the digital library!");
			System.out.println("What would you like to do?");

			System.out.println();
			System.out.println("1. Get Book Info");
			System.out.println("2. Add Book to Library");
			System.out.println("3. Remove Book from Library");
			System.out.println("4. Quit");

			System.out.println();
			System.out.print("Selection: ");
			
			try {
				choice = in.nextInt();
				switch (choice) {
				case 1:
					getBookInfo();
					choice = 0;
					break;
				case 2:
					addBook();
					choice = 0;
					break;
				case 3:
					removeBook();
					choice = 0;
					break;
				case 4:
					System.out.println("Thank you for using the digital library!");
					System.exit(0);
				default:
					System.out.println("[INVALID INPUT]");
					choice = 0;
				}
			} catch (Exception e) {
				System.out.println("[INVALID INPUT]");
				in.nextLine();
			}
		}

	}

	public static void getBookInfo() {
		choice = 0;
		while (choice < 1 || choice > 6) {
			try {
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.print("Enter book ISBN: ");
				String isbn = in.next();
				Book book = library.get(isbn);

				System.out.println("Please select the information about this book you would like.");
				
				System.out.println();
				System.out.println("1. Title");
				System.out.println("2. Author");
				System.out.println("3. Publisher");
				System.out.println("4. Publication Year");
				System.out.println("5. MLA Citation");
				System.out.println("6. Back");
				
				System.out.println();
				System.out.print("Selection: ");


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
					break;
				case 5:
					System.out.println("MLA Citation: " + book.getCitation());
					break;
				case 6:
					break;
				default:
					System.out.println("[INVALID INPUT / BOOK NOT FOUND]");
				}
			} catch (Exception e) {
				System.out.println("[INVALID INPUT/BOOK NOT FOUND]");
				in.nextLine();
			}
		}
		choice = 0;
	}

	public static void addBook() {
		try {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			String title = in.nextLine(); //Handles the next line input from the previous selection
			System.out.print("Enter title: ");
			title = in.nextLine();
			System.out.print("Enter author: ");
			String author = in.nextLine();
			System.out.print("Enter publisher: ");
			String publisher = in.nextLine();
			System.out.print("Enter publication year: ");
			int publication_year = in.nextInt();
			System.out.print("Enter ISBN: ");
			String isbn = in.next();
			
			Book added = new Book(title, author, publisher, publication_year, isbn);
			library.add(added);
			System.out.println(added.getTitle() + " (ISBN: " + added.getISBN() + ") has been added.");
		} catch (Exception e) {
			System.out.println("[INVALID INPUT]");
			in.nextLine();
		}
	}

	public static void removeBook() {
		try {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.print("Enter ISBN: ");
			String isbn = in.next();
			Book removed = library.remove(isbn);
			System.out.println(removed.getTitle() + " (ISBN: " + removed.getISBN() + ") has been removed.");
		} catch (Exception e) {
			System.out.println("[INVALID INPUT/BOOK NOT FOUND]");
		}

	}

}
